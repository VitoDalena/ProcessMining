package org.jfree.data.xy;

public abstract interface WindDataset
  extends XYDataset
{
  public abstract Number getWindDirection(int paramInt1, int paramInt2);
  
  public abstract Number getWindForce(int paramInt1, int paramInt2);
}


/* Location:              /home/menick/Scrivania/ProcessMining/CNMining/CNMining.jar!/org/jfree/data/xy/WindDataset.class
 * Java compiler version: 3 (47.0)
 * JD-Core Version:       0.7.1
 */