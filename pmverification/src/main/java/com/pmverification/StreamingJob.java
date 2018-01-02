package com.pmverification;

import org.apache.flink.cep.CEP;
import org.apache.flink.cep.PatternSelectFunction;
import org.apache.flink.cep.PatternStream;
import org.apache.flink.cep.pattern.Pattern;
import org.apache.flink.streaming.api.datastream.DataStream;
import org.apache.flink.streaming.api.environment.StreamExecutionEnvironment;

import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * Skeleton for a Flink Streaming Job.
 *
 * For a full example of a Flink Streaming Job, see the SocketTextStreamWordCount.java
 * file in the same package/directory or have a look at the website.
 *
 * You can also generate a .jar file that you can submit on your Flink
 * cluster.
 * Just type
 * 		mvn clean package
 * in the projects root directory.
 * You will find the jar in
 * 		target/ProcessMiningVerification-pmverification.jar
 * From the CLI you can then run
 * 		./bin/flink run -c pmverification.StreamingJob target/ProcessMiningVerification-pmverification.jar
 *
 * For more information on the CLI see:
 *
 * http://flink.apache.org/docs/latest/apis/cli.html
 *
 *
 * Here, you can start creating your execution plan for Flink.
 *
 * Start with getting some data from the environment, like
 * 	env.readTextFile(textPath);
 *
 * then, transform the resulting DataStream<String> using operations
 * like
 * 	.filter()
 * 	.flatMap()
 * 	.join()
 * 	.coGroup()
 *
 * and many more.
 * Have a look at the programming guide for the Java API:
 *
 * http://flink.apache.org/docs/latest/apis/streaming/index.html
 *
 */
public class StreamingJob {

    public static void main(String[] args) throws Exception {
        // set up the streaming execution environment
        final StreamExecutionEnvironment env = StreamExecutionEnvironment.getExecutionEnvironment();

        //args[0] model;
        //args[1] log;
        //args[2] ontology;

        UMLGraph graph = Utilities.createTestGraph();
        RuleBook ruleBook = new RuleBook(graph);
        List<ProcessInstance> log = Utilities.createTestList();

        int totalEvents = 0;
        DataStream<OutOfSequence_alert> outOfSequence_alert = null;
        DataStream<Ontology_alert> sequence_alert = null;
        DataStream<Ontology_alert> resourceOccupied_alert = null;
        for (ProcessInstance processInstance : log) {
            totalEvents += processInstance.numSimilarInstances;

            DataStream<AuditTrailEntry> stream = env.fromCollection(processInstance.entryList);
            Set<Map<String, Pattern<AuditTrailEntry,?>>> rules = ruleBook.getRules(processInstance.entryList);
            Pattern<AuditTrailEntry,?> rule;
            PatternStream<AuditTrailEntry> patternStream_outofsequence;
            PatternStream<AuditTrailEntry> patternStream_sequence;
            PatternStream<AuditTrailEntry> patternStream_resourceoccupied;
            DataStream<OutOfSequence_alert> outOfSequence_temp;
            DataStream<Ontology_alert> sequence_temp;
            DataStream<Ontology_alert> resourceOccupied_temp;
            for (Map<String, Pattern<AuditTrailEntry,?>> r : rules) {
                rule = r.get("OutOfSequence");
                patternStream_outofsequence = CEP.pattern(stream,rule);
                outOfSequence_temp = patternStream_outofsequence.select(new PatternSelectFunction<AuditTrailEntry, OutOfSequence_alert>() {
                    @Override
                    public OutOfSequence_alert select(Map<String, List<AuditTrailEntry>> map){
                        AuditTrailEntry _1 = map.get("1").get(0);
                        //AuditTrailEntry _2 = map.get("2").get(0);
                        return new OutOfSequence_alert(_1.workflowModelElement, "");
                    }
                });
                if(outOfSequence_alert == null)
                    outOfSequence_alert =outOfSequence_temp;
                else
                    outOfSequence_alert.union(outOfSequence_temp);
                for(int i = 0;i<processInstance.numSimilarInstances-1;i++)
                    outOfSequence_alert.union(outOfSequence_temp);

                rule = r.get("Sequence");
                patternStream_sequence = CEP.pattern(stream,rule);
                sequence_temp = patternStream_sequence.select(new PatternSelectFunction<AuditTrailEntry, Ontology_alert>() {
                    @Override
                    public Ontology_alert select(Map<String, List<AuditTrailEntry>> map){
                        AuditTrailEntry _1 = map.get("1").get(0);
                        //AuditTrailEntry _2 = map.get("2").get(0);
                        if(_1.timestamp.before(new Date()))
                            return new Ontology_alert(_1.workflowModelElement, "", "SequenceOutOfTime");
                        else
                            return null;
                    }
                });
                if(sequence_alert == null)
                    sequence_alert = sequence_temp;
                else
                    sequence_alert.union(sequence_temp);
                for(int i = 0;i<processInstance.numSimilarInstances-1;i++)
                    sequence_alert.union(sequence_temp);

                rule = r.get("ResourceOccupied");
                patternStream_resourceoccupied = CEP.pattern(stream,rule);
                resourceOccupied_temp = patternStream_resourceoccupied.select(new PatternSelectFunction<AuditTrailEntry, Ontology_alert>() {
                    @Override
                    public Ontology_alert select(Map<String, List<AuditTrailEntry>> map){
                        AuditTrailEntry _1 = map.get("1").get(0);
                        //AuditTrailEntry _2 = map.get("2").get(0);
                        if(_1.originator.equals(""))
                            return new Ontology_alert(_1.workflowModelElement, "", "ResourceOccupied");
                        else
                            return null;
                    }
                });
                if(resourceOccupied_alert == null)
                    resourceOccupied_alert = resourceOccupied_temp;
                else
                    resourceOccupied_alert.union(resourceOccupied_temp);
                for(int i = 0;i<processInstance.numSimilarInstances-1;i++)
                    resourceOccupied_alert.union(resourceOccupied_temp);
            }
        }

        //Second level
        DataStream<Ontology_alert> ontology_alert = null;
        if (sequence_alert != null) {
            ontology_alert = sequence_alert.union(resourceOccupied_alert);
        }else{
            if(resourceOccupied_alert != null){
                ontology_alert = resourceOccupied_alert;
            }
        }
        Pattern<Ontology_alert,?> ontologyRule_global = Pattern.<Ontology_alert>begin("fault").times(totalEvents/3);
        Pattern<Ontology_alert,?> ontologyRule_local = Pattern.<Ontology_alert>begin("fault").next("fault2");
        DataStream<String> fault;
        if(ontology_alert != null){
            PatternStream<Ontology_alert> oa = CEP.pattern(ontology_alert,ontologyRule_global);
            fault = oa.select(new PatternSelectFunction<Ontology_alert, String>() {
                @Override
                public String select(Map<String, List<Ontology_alert>> map){
                    return "Many resources overlaps or activities are executed out of time-sequence. Please check the system, as it may have become faulty";
                }
            });
            fault.print();

            oa = CEP.pattern(ontology_alert,ontologyRule_local);
            fault = oa.select(new PatternSelectFunction<Ontology_alert, String>() {
                @Override
                public String select(Map<String, List<Ontology_alert>> map){
                    Ontology_alert fault = map.get("fault").get(0);
                    Ontology_alert fault2 = map.get("fault2").get(0);
                    if(fault.name1.equals(fault2.name1) && fault.type.equals(fault2.type)) {
                        return fault.type + "fault found after " + fault.name1;
                    }
                    return "";
                }
            });
            fault.print();
        }

        Pattern<OutOfSequence_alert,?> sequenceRule_global = Pattern.<OutOfSequence_alert>begin("sfault").times(totalEvents/3);
        Pattern<OutOfSequence_alert,?> sequenceRule_local = Pattern.begin("sfault");
        PatternStream<OutOfSequence_alert> oosa = CEP.pattern(outOfSequence_alert,sequenceRule_global);
        fault = oosa.select(new PatternSelectFunction<OutOfSequence_alert, String>() {
            @Override
            public String select(Map<String, List<OutOfSequence_alert>> map){
                return "More than 30% of the log does not follow the rules from the model. Is the model obsolete?";
            }
        });
        fault.print();
        oosa = CEP.pattern(outOfSequence_alert,sequenceRule_local);
        fault = oosa.select(new PatternSelectFunction<OutOfSequence_alert, String>() {
            @Override
            public String select(Map<String, List<OutOfSequence_alert>> map){
                OutOfSequence_alert sfault = map.get("sfault").get(0);
                return "Model sequence broke after " + sfault.name1;
            }
        });
        fault.print();

        // execute program
        env.execute("Flink Streaming Java API Skeleton");
    }
}

class Alert{
    String name1;
    String name2;

    Alert(String n1, String n2){
        name1 = n1;
        name2 = n2;
    }
}
class OutOfSequence_alert extends Alert{
    OutOfSequence_alert(String n1, String n2){
        super(n1,n2);
    }

    @Override
    public String toString(){
        return "Pattern out of sequence: [" + name1 + "; " + name2 + "]";
    }
}
class Ontology_alert extends Alert{
    String type;

    Ontology_alert(String n1, String n2, String t){
        super(n1,n2);
        type = t;
    }

    @Override
    public String toString(){
        switch (type) {
            case "SequenceOutOfTime":
                return "Sequence out of time: [" + name1 + "; " + name2 + "]";
            case "ResourceOccupied":
                return "Parallel processes use same resource: [" + name1 + "; " + name2 + "]";
            default:
                return "Unknown exception";
        }
    }
}