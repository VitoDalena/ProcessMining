package edu.uci.ics.jung.algorithms.filters;

import edu.uci.ics.jung.graph.Graph;
import org.apache.commons.collections15.Transformer;

public abstract interface Filter<V, E>
  extends Transformer<Graph<V, E>, Graph<V, E>>
{}


/* Location:              /home/menick/Scrivania/ProcessMining/CNMining/CNMining.jar!/edu/uci/ics/jung/algorithms/filters/Filter.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       0.7.1
 */