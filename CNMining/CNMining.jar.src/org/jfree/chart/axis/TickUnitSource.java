package org.jfree.chart.axis;

public abstract interface TickUnitSource
{
  public abstract TickUnit getLargerTickUnit(TickUnit paramTickUnit);
  
  public abstract TickUnit getCeilingTickUnit(TickUnit paramTickUnit);
  
  public abstract TickUnit getCeilingTickUnit(double paramDouble);
}


/* Location:              /home/menick/Scrivania/ProcessMining/CNMining/CNMining.jar!/org/jfree/chart/axis/TickUnitSource.class
 * Java compiler version: 3 (47.0)
 * JD-Core Version:       0.7.1
 */