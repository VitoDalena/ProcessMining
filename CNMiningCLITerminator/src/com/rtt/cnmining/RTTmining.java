package com.rtt.cnmining;

import org.processmining.models.flexiblemodel.Flex;

public class RTTmining {

    private LogInspector log;
    private Flex grap;
    private PatternMap patternMap;

    public RTTmining(Flex graph, LogInspector log){
        this.grap = graph;
        this.log = log;
        this.patternMap = new PatternMap(log);
    }

    public void process(){

    }

}
