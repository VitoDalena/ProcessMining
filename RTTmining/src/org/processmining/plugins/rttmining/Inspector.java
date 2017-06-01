package org.processmining.plugins.rttmining;

import java.util.ArrayList;

public abstract class Inspector {

    public abstract ArrayList<String> startActivities();
    public abstract ArrayList<String> endActivities();

    public abstract ArrayList<String> activities();

    public abstract ArrayList<String> followers(String activity);
    public abstract ArrayList<String> predecessors(String activity);

}
