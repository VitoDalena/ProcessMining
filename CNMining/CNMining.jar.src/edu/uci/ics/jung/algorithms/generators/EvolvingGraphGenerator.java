package edu.uci.ics.jung.algorithms.generators;

public abstract interface EvolvingGraphGenerator<V, E>
  extends GraphGenerator<V, E>
{
  public abstract void evolveGraph(int paramInt);
  
  public abstract int numIterations();
}


/* Location:              /home/menick/Scrivania/ProcessMining/CNMining/CNMining.jar!/edu/uci/ics/jung/algorithms/generators/EvolvingGraphGenerator.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       0.7.1
 */