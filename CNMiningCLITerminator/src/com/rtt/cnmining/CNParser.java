package com.rtt.cnmining;

import org.processmining.models.flexiblemodel.Flex;
import org.processmining.models.flexiblemodel.FlexEdge;
import org.processmining.models.flexiblemodel.FlexNode;

/*
    Questa classe esegue una conversione dell'output del plugin di
    CNMining (in formato xml) verso un grafo Flex.
 */
public class CNParser {

    private String filename;

    public CNParser(String filename){
        this.filename = filename;
    }

    public Flex parse(){
        return null; // TODO
    }

}
