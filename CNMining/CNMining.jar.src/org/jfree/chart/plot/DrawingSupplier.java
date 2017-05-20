package org.jfree.chart.plot;

import java.awt.Paint;
import java.awt.Shape;
import java.awt.Stroke;

public abstract interface DrawingSupplier
{
  public abstract Paint getNextPaint();
  
  public abstract Paint getNextOutlinePaint();
  
  public abstract Paint getNextFillPaint();
  
  public abstract Stroke getNextStroke();
  
  public abstract Stroke getNextOutlineStroke();
  
  public abstract Shape getNextShape();
}


/* Location:              /home/menick/Scrivania/ProcessMining/CNMining/CNMining.jar!/org/jfree/chart/plot/DrawingSupplier.class
 * Java compiler version: 3 (47.0)
 * JD-Core Version:       0.7.1
 */