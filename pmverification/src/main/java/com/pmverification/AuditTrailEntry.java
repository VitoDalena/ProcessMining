package com.pmverification;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.GregorianCalendar;

public class AuditTrailEntry {
    String workflowModelElement;
    String eventType;
    String originator;
    GregorianCalendar timestamp;
    
    
    public void setWorkflow(String w){
        this.workflowModelElement = w;
    }
    public void setEvent(String e){
        this.eventType = e;
    }
    public void setOriginator(String o){
        this.originator = o;
    }
    public void setTimestamp(String ts){
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSSXXX");
        try{
        Date date = df.parse(ts);
        this.timestamp.setTime(date);
        }catch(ParseException e){
            e.printStackTrace();
        }
    }
}

