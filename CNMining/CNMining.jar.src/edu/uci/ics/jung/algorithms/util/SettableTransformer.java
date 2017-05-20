package edu.uci.ics.jung.algorithms.util;

import org.apache.commons.collections15.Transformer;

public abstract interface SettableTransformer<I, O>
  extends Transformer<I, O>
{
  public abstract void set(I paramI, O paramO);
}


/* Location:              /home/menick/Scrivania/ProcessMining/CNMining/CNMining.jar!/edu/uci/ics/jung/algorithms/util/SettableTransformer.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       0.7.1
 */