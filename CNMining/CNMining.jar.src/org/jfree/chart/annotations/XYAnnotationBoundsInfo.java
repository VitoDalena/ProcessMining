package org.jfree.chart.annotations;

import org.jfree.data.Range;

public abstract interface XYAnnotationBoundsInfo
{
  public abstract boolean getIncludeInDataBounds();
  
  public abstract Range getXRange();
  
  public abstract Range getYRange();
}


/* Location:              /home/menick/Scrivania/ProcessMining/CNMining/CNMining.jar!/org/jfree/chart/annotations/XYAnnotationBoundsInfo.class
 * Java compiler version: 3 (47.0)
 * JD-Core Version:       0.7.1
 */