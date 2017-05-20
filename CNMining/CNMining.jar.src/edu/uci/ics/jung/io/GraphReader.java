package edu.uci.ics.jung.io;

import edu.uci.ics.jung.graph.Hypergraph;

public abstract interface GraphReader<G extends Hypergraph<V, E>, V, E>
{
  public abstract G readGraph()
    throws GraphIOException;
  
  public abstract void close()
    throws GraphIOException;
}


/* Location:              /home/menick/Scrivania/ProcessMining/CNMining/CNMining.jar!/edu/uci/ics/jung/io/GraphReader.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       0.7.1
 */