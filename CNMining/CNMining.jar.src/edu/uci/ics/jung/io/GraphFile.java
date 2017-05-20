package edu.uci.ics.jung.io;

import edu.uci.ics.jung.graph.Graph;

public abstract interface GraphFile<V, E>
{
  public abstract Graph<V, E> load(String paramString);
  
  public abstract void save(Graph<V, E> paramGraph, String paramString);
}


/* Location:              /home/menick/Scrivania/ProcessMining/CNMining/CNMining.jar!/edu/uci/ics/jung/io/GraphFile.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       0.7.1
 */