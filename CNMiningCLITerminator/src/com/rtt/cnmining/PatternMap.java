package com.rtt.cnmining;

import java.util.ArrayList;

public class PatternMap {
    private LogInspector log;

    public PatternMap(LogInspector log){
        this.log = log;
    }

    // Mappatura dei pattern AND e OR

    /*
        Ritorna vero se dopo l'attività specificata si ha una diramazione parallela
        Come definito sul libro il pattern AND-split lo si ottiene se si ottiene:
        a -> b, a -> c, b || c. In tal caso si prò affermare che b e c sono parallele.
        Dopo a si ha una diramazione parallela
      */
    public BranchPattern ANDsplit(String activity){
        ArrayList<String> followers = this.log.causalFollowers(activity);

        if(followers.size() == 0)
            return null;

        BranchPattern pattern = new BranchPattern(activity);
        pattern.AND();
        pattern.split = true;

        ArrayList<String> paralleli = new ArrayList<>();
        for(int i = 0; i < followers.size(); i++){
            String follower = followers.get(i);

            for(int y = 0; y < followers.size(); y++){
                if(i == y) continue;
                String follower1 = followers.get(y);

                if(this.log.orOperator(follower, follower1) && paralleli.contains(follower) == false)
                    paralleli.add(follower);
            }
        }
        if(paralleli.size() > 0)
        {
            pattern.branches = paralleli;
            return pattern;
        }
        return null;
    }

    /*
        Ritorna vero se prima l'attività specificata si ha una diramazione parallela
        Come definito sul libro il pattern AND-join lo si ottiene se si ottiene:
        a -> c, b -> c, a || b. In tal caso si prò affermare che a e b sono parallele.
        Prima di c si ha una sincronizzazione.
    */
    public BranchPattern ANDjoin(String activity) {
        ArrayList<String> predecessors = this.log.causalPredecessors(activity);

        if(predecessors.size() == 0)
            return null;

        BranchPattern pattern = new BranchPattern(activity);
        pattern.AND();
        pattern.split = false;

        ArrayList<String> paralleli = new ArrayList<>();
        for(int i = 0; i < predecessors.size(); i++){
            String follower = predecessors.get(i);

            for(int y = 0; y < predecessors.size(); y++){
                if(i == y) continue;
                String follower1 = predecessors.get(y);

                if(this.log.orOperator(follower, follower1) && paralleli.contains(follower) == false)
                    paralleli.add(follower);
            }
        }
        if(paralleli.size() > 0)
        {
            pattern.branches = paralleli;
            return pattern;
        }
        return null;
    }

    /*
        Ritorna vero se dopo l'attività specificata si ha una diramazione parallela
        Come definito sul libro il pattern XOR-split lo si ottiene se si ottiene:
        a -> b, a -> c, b # c.
        Vuol dire che dopo a, b o c possono accadere
      */
    public BranchPattern XORsplit(String activity){
        ArrayList<String> followers = this.log.causalFollowers(activity);

        if(followers.size() == 0)
            return null;

        BranchPattern pattern = new BranchPattern(activity);
        pattern.XOR();
        pattern.split = true;

        ArrayList<String> diramazioni = new ArrayList<>();
        for(int i = 0; i < followers.size(); i++){
            String follower = followers.get(i);

            for(int y = 0; y < followers.size(); y++){
                if(i == y) continue;
                String follower1 = followers.get(y);

                if(this.log.sharpOperator(follower, follower1) && diramazioni.contains(follower) == false)
                    diramazioni.add(follower);
            }
        }
        if(diramazioni.size() > 0)
        {
            pattern.branches = diramazioni;
            return pattern;
        }
        return null;
    }

    /*
        Ritorna vero se prima l'attività specificata si ha una diramazione parallela
        Come definito sul libro il pattern XOR-join lo si ottiene se si ottiene:
        a -> c, b -> c, a # b.
        Dopo l'occorrenza di a o b, accade c.
    */
    public BranchPattern XORjoin(String activity) {
        ArrayList<String> predecessors = this.log.causalPredecessors(activity);

        if(predecessors.size() == 0)
            return null;

        BranchPattern pattern = new BranchPattern(activity);
        pattern.XOR();
        pattern.split = false;

        ArrayList<String> diramazioni = new ArrayList<>();
        for(int i = 0; i < predecessors.size(); i++){
            String follower = predecessors.get(i);

            for(int y = 0; y < predecessors.size(); y++){
                if(i == y) continue;
                String follower1 = predecessors.get(y);

                if(this.log.sharpOperator(follower, follower1) && diramazioni.contains(follower) == false)
                    diramazioni.add(follower);
            }
        }
        if(diramazioni.size() > 0)
        {
            pattern.branches = diramazioni;
            return pattern;
        }
        return null;
    }

    /*
        Verifica se dopo l'attività specificata
        si ha una diramazione OR
     */
    public BranchPattern ORsplit(String activity) {
        ArrayList<String> followers = this.log.followers(activity);

        if(followers.size() == 0)
            return null;

        BranchPattern pattern = new BranchPattern(activity);
        pattern.OR();
        pattern.split = true;

        BranchPattern andPattern = this.ANDsplit(activity);
        ArrayList<String> diramazioni = new ArrayList<>();

        if(andPattern != null){
            for(int i = 0; i < followers.size(); i++)
            {
                String name = followers.get(i);
                if(andPattern.branches.contains(name) == false && diramazioni.contains(name) == false)
                    diramazioni.add(name);
            }
            if(diramazioni.size() > 0)
                diramazioni.add("AND-split");
        }
        else {
            diramazioni = followers;
        }

        if(diramazioni.size() > 0)
        {
            pattern.branches = diramazioni;
            return pattern;
        }
        return null;
    }

    /*
        Verifica se prima dell'attività specificata
        si ha terminazione di diramazione OR
     */
    public BranchPattern ORjoin(String activity){
        ArrayList<String> predecessors = this.log.causalPredecessors(activity);

        if(predecessors.size() == 0)
            return null;

        BranchPattern pattern = new BranchPattern(activity);
        pattern.OR();
        pattern.split = false;

        BranchPattern andPattern = this.ANDjoin(activity);
        ArrayList<String> diramazioni = new ArrayList<>();

        if(andPattern != null){
            for(int i = 0; i < predecessors.size(); i++)
            {
                String name = predecessors.get(i);
                if(andPattern.branches.contains(name) == false && diramazioni.contains(name) == false)
                    diramazioni.add(name);
            }
            if(diramazioni.size() > 0)
                diramazioni.add("AND-join");
        }
        else {
            diramazioni = predecessors;
        }

        if(diramazioni.size() > 0)
        {
            pattern.branches = diramazioni;
            return pattern;
        }
        return null;
    }
}
