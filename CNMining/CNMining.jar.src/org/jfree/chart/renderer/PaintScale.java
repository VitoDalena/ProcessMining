package org.jfree.chart.renderer;

import java.awt.Paint;

public abstract interface PaintScale
{
  public abstract double getLowerBound();
  
  public abstract double getUpperBound();
  
  public abstract Paint getPaint(double paramDouble);
}


/* Location:              /home/menick/Scrivania/ProcessMining/CNMining/CNMining.jar!/org/jfree/chart/renderer/PaintScale.class
 * Java compiler version: 3 (47.0)
 * JD-Core Version:       0.7.1
 */