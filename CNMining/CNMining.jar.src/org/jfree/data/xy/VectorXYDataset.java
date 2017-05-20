package org.jfree.data.xy;

public abstract interface VectorXYDataset
  extends XYDataset
{
  public abstract double getVectorXValue(int paramInt1, int paramInt2);
  
  public abstract double getVectorYValue(int paramInt1, int paramInt2);
  
  public abstract Vector getVector(int paramInt1, int paramInt2);
}


/* Location:              /home/menick/Scrivania/ProcessMining/CNMining/CNMining.jar!/org/jfree/data/xy/VectorXYDataset.class
 * Java compiler version: 3 (47.0)
 * JD-Core Version:       0.7.1
 */