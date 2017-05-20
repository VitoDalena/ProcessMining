package org.jfree.chart;

import java.util.EventListener;

public abstract interface ChartMouseListener
  extends EventListener
{
  public abstract void chartMouseClicked(ChartMouseEvent paramChartMouseEvent);
  
  public abstract void chartMouseMoved(ChartMouseEvent paramChartMouseEvent);
}


/* Location:              /home/menick/Scrivania/ProcessMining/CNMining/CNMining.jar!/org/jfree/chart/ChartMouseListener.class
 * Java compiler version: 3 (47.0)
 * JD-Core Version:       0.7.1
 */