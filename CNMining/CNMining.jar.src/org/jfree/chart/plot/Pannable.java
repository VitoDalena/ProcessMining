package org.jfree.chart.plot;

import java.awt.geom.Point2D;

public abstract interface Pannable
{
  public abstract PlotOrientation getOrientation();
  
  public abstract boolean isDomainPannable();
  
  public abstract boolean isRangePannable();
  
  public abstract void panDomainAxes(double paramDouble, PlotRenderingInfo paramPlotRenderingInfo, Point2D paramPoint2D);
  
  public abstract void panRangeAxes(double paramDouble, PlotRenderingInfo paramPlotRenderingInfo, Point2D paramPoint2D);
}


/* Location:              /home/menick/Scrivania/ProcessMining/CNMining/CNMining.jar!/org/jfree/chart/plot/Pannable.class
 * Java compiler version: 3 (47.0)
 * JD-Core Version:       0.7.1
 */