package com.rtt.cnmining;

/*
    Questa classe implementa parte dell'algoritmo alfa per Petri net
    in modo tale da estrarre informazioni contestuali sulle varie attività del log
    Pagina 168 del libro
 */

import org.deckfour.xes.model.XEvent;
import org.deckfour.xes.model.XLog;
import org.deckfour.xes.model.XTrace;

import java.util.ArrayList;

public class LogInspector extends Inspector {

    private XLog log;

    public LogInspector(XLog log){
        this.log = log;
    }

    // Ritorna la lista contenente il nome di tutte le attività
    public ArrayList<String> activities(){
        ArrayList<String> result = new ArrayList<>();

        for (int i = 0; i < this.log.size(); i++) {
            XTrace trace = this.log.get(i);

            for (XEvent activity : trace){
                String name = activity.getAttributes().get("concept:name").toString();

                if(result.contains(name) == false)
                    result.add(name);
            }
        }

        return result;
    }

    // Ritorna la lista delle attività iniziali
    public ArrayList<String> startActivities(){
        ArrayList<String> result = new ArrayList<>();

        for(String activity: this.activities()){
            if(this.predecessors(activity).size() == 0 && result.contains(activity) == false)
                result.add(activity);
        }

        return result;
    }

    // Ritorna la lista delle attività finali
    public ArrayList<String> endActivities(){
        ArrayList<String> result = new ArrayList<>();

        for(String activity: this.activities()){
            if(this.followers(activity).size() == 0 && result.contains(activity) == false)
                result.add(activity);
        }

        return result;
    }

    // Ritorna la lista delle attività t(i), tali che a > t(i)
    public ArrayList<String> followers(String activity){
        ArrayList<String> result = new ArrayList<>();

        ArrayList<String> attivita = this.activities();
        for(int i = 0; i < attivita.size(); i++){
            String nome = attivita.get(i);

            if(nome.equals(activity)) {
                continue;
            }

            if(this.directlyFollows(activity, nome))
                result.add(nome);
        }

        return result;
    }

    // Ritorna la lista delle attività t(i), tali che t(i) > a
    public ArrayList<String> predecessors(String activity){
        ArrayList<String> result = new ArrayList<>();

        ArrayList<String> attivita = this.activities();
        for(int i = 0; i < attivita.size(); i++){
            String nome = attivita.get(i);

            if(nome.equals(activity)) {
                continue;
            }

            if(this.directlyFollows(nome, activity))
                result.add(nome);
        }

        return result;
    }

    //Ritorna la lista delle attività t(i), tali che a -> t(i)
    public ArrayList<String> causalFollowers(String activity){
        ArrayList<String> result = new ArrayList<>();

        ArrayList<String> attivita = this.activities();
        for(int i = 0; i < attivita.size(); i++){
            String nome = attivita.get(i);

            if(nome.equals(activity)) {
                continue;
            }

            if(this.causalityRelation(activity, nome))
                result.add(nome);
        }

        return result;
    }

    //Ritorna la lista delle attività t(i), tali che t(i) -> a
    public ArrayList<String> causalPredecessors(String activity){
        ArrayList<String> result = new ArrayList<>();

        ArrayList<String> attivita = this.activities();
        for(int i = 0; i < attivita.size(); i++){
            String nome = attivita.get(i);

            if(nome.equals(activity)) {
                continue;
            }

            if(this.causalityRelation(nome, activity))
                result.add(nome);
        }

        return result;
    }

    // Mappatura degli operatori di Aalst. Pagina 169 del libro

    // a > b:
    // è valida se esiste una traccia t di L per cui si ricava che
    // t[i] = a e t[i+1] = b
    public boolean directlyFollows(String activity1, String activity2){
        for (int i = 0; i < this.log.size(); i++) {
            XTrace trace = this.log.get(i);

            for(int y = 0; y < trace.size() - 1; y++){
                XEvent logActivity1 = trace.get(y);
                String name1 = logActivity1.getAttributes().get("concept:name").toString();

                if(name1.equals(activity1) == false)
                    continue;

                XEvent logActivity2 = trace.get(y + 1);
                String name2 = logActivity2.getAttributes().get("concept:name").toString();

                if( name2.equals(activity2))
                    return true;
            }
        }
        return false;
    }

    // a -> b:
    // valida se a > b e NOT(b > a)
    public boolean causalityRelation(String activity1, String activity2){
        return this.directlyFollows(activity1, activity2) &&
                this.directlyFollows(activity2, activity1) == false;
    }

    // a # b:
    // valida se NOT(a > b) e NOT(b > a)
    public boolean sharpOperator(String activity1, String activity2){
        return this.directlyFollows(activity1, activity2) == false &&
                this.directlyFollows(activity2, activity1) == false;
    }

    // a || b:
    // se sono entrambe vere le relazioni: a > b, b > a
    public boolean orOperator(String activity1, String activity2){
        return this.directlyFollows(activity1, activity2) &&
                this.directlyFollows(activity2, activity1);
    }

}
