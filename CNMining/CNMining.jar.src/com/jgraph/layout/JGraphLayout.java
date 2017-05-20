package com.jgraph.layout;

public abstract interface JGraphLayout
{
  public static final String VERSION = "JGraph (v5.13.0.4)";
  
  public abstract void run(JGraphFacade paramJGraphFacade);
  
  public static abstract interface Stoppable
  {
    public abstract JGraphLayoutProgress getProgress();
  }
}


/* Location:              /home/menick/Scrivania/ProcessMining/CNMining/CNMining.jar!/com/jgraph/layout/JGraphLayout.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */