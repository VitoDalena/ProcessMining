package com.pmverification;

import org.apache.flink.api.common.functions.FlatMapFunction;
import org.apache.flink.cep.CEP;
import org.apache.flink.cep.PatternSelectFunction;
import org.apache.flink.cep.PatternStream;
import org.apache.flink.cep.pattern.Pattern;
import org.apache.flink.streaming.api.datastream.DataStream;
import org.apache.flink.streaming.api.environment.StreamExecutionEnvironment;
import org.apache.flink.streaming.api.functions.sink.SinkFunction;
import org.apache.flink.util.Collector;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.util.*;

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
        File f = new File("report.txt");
        boolean sanityCheck = true;
        if(f.exists())
             sanityCheck = f.delete();
        final StreamExecutionEnvironment env = StreamExecutionEnvironment.getExecutionEnvironment();

        String modelPath = args[0];
        String logPath = args[1];
        String ontologyPath = args[2];

        UMLGraph graph = new UMLGraph(Edge.class,modelPath,ontologyPath);
        RuleBook ruleBook = new RuleBook(graph);
        List<ProcessInstance> log = Utilities.parseLog(logPath);
        final Map<String, Node> mapOfNodes = new HashMap<>();
        for (Node n : graph.vertexSet()) {
            mapOfNodes.put(n.name,n);
        }

        int totalEvents = 0;
        DataStream<AuditTrailEntry> stream;
        Set<Map<String, Pattern<AuditTrailEntry,?>>> rules;
        ArrayList<DataStream<OutOfSequence_alert>> outOfSequence_alerts = new ArrayList<>();
        ArrayList<DataStream<Ontology_alert>> sequence_alerts = new ArrayList<>();
        ArrayList<DataStream<Ontology_alert>> resourceOccupied_alerts = new ArrayList<>();
        ArrayList<DataStream<Ontology_alert>> wrongResource_alerts = new ArrayList<>();
        PatternStream<AuditTrailEntry> patternStream_outofsequence;
        PatternStream<AuditTrailEntry> patternStream_sequence;
        PatternStream<AuditTrailEntry> patternStream_resourceoccupied;
        PatternStream<AuditTrailEntry> patternStream_wrongresource;
        Pattern<AuditTrailEntry,?> rule;
        int i = 0;
        for (ProcessInstance processInstance : log) {
            totalEvents += processInstance.numSimilarInstances;
            stream = env.fromCollection(processInstance.entryList);
            rules = ruleBook.getRules(processInstance.entryList);
            final String id = processInstance.id;
            final int magnitude = processInstance.numSimilarInstances;
            for (Map<String, Pattern<AuditTrailEntry,?>> r : rules) {
                rule = r.get("OutOfSequence");
                patternStream_outofsequence = CEP.pattern(stream,rule);
                outOfSequence_alerts.add(i, patternStream_outofsequence.select(new PatternSelectFunction<AuditTrailEntry, OutOfSequence_alert>() {
                    @Override
                    public OutOfSequence_alert select(Map<String, List<AuditTrailEntry>> map){
                        AuditTrailEntry _1 = map.get("1").get(0);
                        AuditTrailEntry _2 = map.get("2").get(0);
                        return new OutOfSequence_alert(_1.workflowModelElement, _2.workflowModelElement, id, magnitude);
                    }
                }));

                rule = r.get("Sequence");
                patternStream_sequence = CEP.pattern(stream,rule);
                sequence_alerts.add(i, patternStream_sequence.select(new PatternSelectFunction<AuditTrailEntry, Ontology_alert>() {
                    @Override
                    public Ontology_alert select(Map<String, List<AuditTrailEntry>> map){
                        AuditTrailEntry _1 = map.get("1").get(0);
                        AuditTrailEntry _2 = map.get("2").get(0);
                        GregorianCalendar time = (GregorianCalendar) _1.timestamp.clone();
                        time.add(GregorianCalendar.SECOND, Integer.parseInt(mapOfNodes.get(_1.workflowModelElement).activityTime));
                        if(_2.timestamp.before(time))
                            return new Ontology_alert(_1.workflowModelElement, _2.workflowModelElement, id, magnitude, "SequenceOutOfTime");
                        else
                            return new Ontology_alert("","","", 0,"FakeAlarm");
                    }
                }));

                rule = r.get("ResourceOccupied");
                patternStream_resourceoccupied = CEP.pattern(stream,rule);
                resourceOccupied_alerts.add(i, patternStream_resourceoccupied.select(new PatternSelectFunction<AuditTrailEntry, Ontology_alert>() {
                    @Override
                    public Ontology_alert select(Map<String, List<AuditTrailEntry>> map){
                        AuditTrailEntry _1 = map.get("1").get(0);
                        AuditTrailEntry _2 = map.get("2").get(0);
                        if(_1.originator.equals(_2.originator))
                            return new Ontology_alert(_1.workflowModelElement, _2.workflowModelElement, id, magnitude, "ResourceOccupied");
                        else
                            return new Ontology_alert("","","", 0,"FakeAlarm");
                    }
                }));
            }
            rule = Pattern.begin("1");
            patternStream_wrongresource = CEP.pattern(stream,rule);
            wrongResource_alerts.add(i, patternStream_wrongresource.select(new PatternSelectFunction<AuditTrailEntry, Ontology_alert>() {
                @Override
                public Ontology_alert select(Map<String, List<AuditTrailEntry>> map){
                    AuditTrailEntry _1 = map.get("1").get(0);
                    if(!mapOfNodes.get(_1.workflowModelElement).hasResource.contains(_1.originator))
                        return new Ontology_alert(_1.workflowModelElement, "", id, magnitude, "WrongResource");
                    else
                        return new Ontology_alert("","","", 0,"FakeAlarm");
                }
            }));
            i++;
        }

        //Group results and multiply them for their occurrences
        DataStream<OutOfSequence_alert> result1;
        DataStream<Ontology_alert> result2;
        result1 = outOfSequence_alerts.get(0).flatMap(new MultiplicationFunction1());
        result2 = sequence_alerts.get(0).flatMap(new MultiplicationFunction2());
        for (int k = 1;k<outOfSequence_alerts.size();k++) {
            result1 = result1.union(outOfSequence_alerts.get(k).flatMap(new MultiplicationFunction1()));
        }
        for (int k = 1;k<sequence_alerts.size();k++) {
            result2 = result2.union(sequence_alerts.get(k).flatMap(new MultiplicationFunction2()));
        }
        for (DataStream<Ontology_alert> resourceOccupied_alert : resourceOccupied_alerts) {
            result2 = result2.union(resourceOccupied_alert.flatMap(new MultiplicationFunction2()));
        }
        for (DataStream<Ontology_alert> wrongResource_alert : wrongResource_alerts) {
            result2 = result2.union(wrongResource_alert.flatMap(new MultiplicationFunction2()));
        }

        //Second level
        Pattern<Ontology_alert,?> ontologyRule_global = Pattern.<Ontology_alert>begin("fault").times(totalEvents/3);
        Pattern<Ontology_alert,?> ontologyRule_local = Pattern.begin("fault");
        PatternStream<Ontology_alert> oag;
        PatternStream<Ontology_alert> oal = CEP.pattern(result2,ontologyRule_local);
        DataStream<String> ogfault;
        DataStream<String> olfault;
        DataStream<String> oosgfault;
        DataStream<String> ooslfault;
        oag = CEP.pattern(result2,ontologyRule_global);
        ogfault = oag.select(new PatternSelectFunction<Ontology_alert, String>() {
            @Override
            public String select(Map<String, List<Ontology_alert>> map){
                //Many resources overlaps or activities are executed out of time-sequence. Please check the system, as it may have become faulty
                if(!Ontology_alert.oagReported){
                    Ontology_alert.oagReported = true;
                    return "OAG:REPORT";
                }
                else
                    return "";
            }
        });
        olfault = oal.select(new PatternSelectFunction<Ontology_alert, String>() {
            @Override
            public String select(Map<String, List<Ontology_alert>> map){
                StringBuilder sb = new StringBuilder();
                for (Ontology_alert a : map.get("fault")) {
                    sb.append(a.toString());
                }
                return sb.toString();
            }
        });
        Pattern<OutOfSequence_alert,?> sequenceRule_global = Pattern.<OutOfSequence_alert>begin("sfault").times(totalEvents/3);
        Pattern<OutOfSequence_alert,?> sequenceRule_local = Pattern.begin("sfault");
        PatternStream<OutOfSequence_alert> oosag = CEP.pattern(result1,sequenceRule_global);
        PatternStream<OutOfSequence_alert> oosal = CEP.pattern(result1,sequenceRule_local);
        oosgfault = oosag.select(new PatternSelectFunction<OutOfSequence_alert, String>() {
            @Override
            public String select(Map<String, List<OutOfSequence_alert>> map){
                //More than 30% of the log does not follow the rules from the model. Is the model obsolete?
                if(!OutOfSequence_alert.oosagReported){
                    OutOfSequence_alert.oosagReported = true;
                    return "OOSAG:REPORT";
                }
                else
                    return "";
            }
        });
        ooslfault = oosal.select(new PatternSelectFunction<OutOfSequence_alert, String>() {
            @Override
            public String select(Map<String, List<OutOfSequence_alert>> map){
                OutOfSequence_alert sfault = map.get("sfault").get(0);
                //Local out of sequence report
                return sfault.toString();
            }
        });

        if(sanityCheck) {
            ogfault.union(ogfault).union(olfault).union(oosgfault).union(ooslfault).addSink(new SinkFunction<String>() {
                @Override
                public void invoke(String string) throws Exception {
                    if (!string.equals("") && !string.equals("OOSAG:REPORT") && !string.equals("OAG:REPORT")) {
                        PrintWriter w = new PrintWriter(new BufferedWriter(new FileWriter("report.txt", true)));
                        w.println(string);
                        w.close();
                    }else{
                        if(string.equals("OOSAG:REPORT")){
                            System.out.println(string);
                        }
                        if(string.equals("OAG:REPORT")){
                            System.out.println(string);
                        }
                    }
                }
            });
        }
        // execute program
        env.execute("Flink Process Mining Verification");
    }
}

class MultiplicationFunction1 implements FlatMapFunction<OutOfSequence_alert, OutOfSequence_alert>{
    @Override
    public void flatMap(OutOfSequence_alert outOfSequence_alert, Collector<OutOfSequence_alert> collector){
        collector.collect(outOfSequence_alert);
        OutOfSequence_alert oosa = new OutOfSequence_alert(outOfSequence_alert);
        oosa.silenced = true;
        for(int i = 1;i<outOfSequence_alert.magnitude;i++)
            collector.collect(oosa);
        }
}
class MultiplicationFunction2 implements FlatMapFunction<Ontology_alert, Ontology_alert>{
    @Override
    public void flatMap(Ontology_alert ontology_alert, Collector<Ontology_alert> collector){
        collector.collect(ontology_alert);
        Ontology_alert oa = new Ontology_alert(ontology_alert);
        oa.silenced = true;
        for(int i = 1;i<ontology_alert.magnitude;i++)
            collector.collect(oa);
    }
}

class Alert{
    String name1;
    String name2;
    String processId;
    int magnitude;
    public boolean silenced;

    Alert(String n1, String n2, String pi, int m){
        name1 = n1;
        name2 = n2;
        processId = pi;
        magnitude = m;
        silenced = false;
    }
    Alert(){
    }
}
class OutOfSequence_alert extends Alert{
    static boolean oosagReported = false;

    OutOfSequence_alert(String n1, String n2, String pi, int m){
        super(n1,n2,pi,m);
    }
    OutOfSequence_alert(OutOfSequence_alert oosa){
        this.silenced = oosa.silenced;
        this.processId = oosa.processId;
        this.name1 = oosa.name1;
        this.name2 = oosa.name2;
        this.magnitude = oosa.magnitude;
    }

    @Override
    public String toString(){
        if(silenced)
            return "";
        else
            return "Pattern out of sequence [" + name1 + " -> " + name2 + "] in " + processId + " for " + magnitude + " times";
    }
}
class Ontology_alert extends Alert{
    private String type;
    static boolean oagReported = false;

    Ontology_alert(String n1, String n2, String pi, int m, String t){
        super(n1,n2,pi,m);
        type = t;
    }
    Ontology_alert(Ontology_alert oosa){
        this.type = oosa.type;
        this.silenced = oosa.silenced;
        this.processId = oosa.processId;
        this.name1 = oosa.name1;
        this.name2 = oosa.name2;
        this.magnitude = oosa.magnitude;
    }

    @Override
    public String toString(){
        if(silenced)
            return "";
        else{
            switch (type) {
                case "SequenceOutOfTime":
                    return "Sequence out of time [" + name1 + " -> " + name2 + "] in " + processId + " for " + magnitude + " times";
                case "ResourceOccupied":
                    return "Parallel processes use same resource [" + name1 + " -> " + name2 + "] in " + processId + " for " + magnitude + " times";
                case "WrongResource":
                    return "Unexpected resource originated " + name1 + " in " + processId + " for " + magnitude + " times";
                case "FakeAlarm":
                    return "";
                default:
                    return "Unknown exception";
            }
        }
    }
}