package com.jgraph.algebra.cost;

public class JGraphConstantCostFunction
  implements JGraphCostFunction
{
  protected double cost = 0.0D;
  
  public JGraphConstantCostFunction(double paramDouble)
  {
    this.cost = paramDouble;
  }
  
  public double getCost(Object paramObject)
  {
    return this.cost;
  }
}


/* Location:              /home/menick/Scrivania/ProcessMining/CNMining/CNMining.jar!/com/jgraph/algebra/cost/JGraphConstantCostFunction.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */