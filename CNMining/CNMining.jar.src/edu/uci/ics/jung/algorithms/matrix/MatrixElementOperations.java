package edu.uci.ics.jung.algorithms.matrix;

import java.util.Map;

public abstract interface MatrixElementOperations<E>
{
  public abstract void mergePaths(E paramE, Object paramObject);
  
  public abstract Number computePathData(E paramE1, E paramE2);
  
  public abstract Map<E, Number> getEdgeData();
}


/* Location:              /home/menick/Scrivania/ProcessMining/CNMining/CNMining.jar!/edu/uci/ics/jung/algorithms/matrix/MatrixElementOperations.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       0.7.1
 */