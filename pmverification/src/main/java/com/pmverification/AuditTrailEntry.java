package com.pmverification;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.GregorianCalendar;

public class AuditTrailEntry {
    private String workflowModelElement;
    private String eventType;
    private String originator;
    private GregorianCalendar timestamp;

    
    public String getWorkflowModelElement(){ return workflowModelElement; }
    public void setWorkflow(String w){
        this.workflowModelElement = w;
    }

    public String getEventType() { if(eventType != null) return eventType; else return ""; }
    public void setEvent(String e){
        this.eventType = e;
    }

    public String getOriginator(){ if(originator != null) return originator; else return ""; }
    public void setOriginator(String o){
        this.originator = o;
    }

    public GregorianCalendar getTimestamp() { if(timestamp != null) return timestamp; else return new GregorianCalendar(); }
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

