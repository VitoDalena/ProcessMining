package org.jfree.chart.panel;

import java.awt.Graphics2D;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.event.OverlayChangeListener;

public abstract interface Overlay
{
  public abstract void paintOverlay(Graphics2D paramGraphics2D, ChartPanel paramChartPanel);
  
  public abstract void addChangeListener(OverlayChangeListener paramOverlayChangeListener);
  
  public abstract void removeChangeListener(OverlayChangeListener paramOverlayChangeListener);
}


/* Location:              /home/menick/Scrivania/ProcessMining/CNMining/CNMining.jar!/org/jfree/chart/panel/Overlay.class
 * Java compiler version: 3 (47.0)
 * JD-Core Version:       0.7.1
 */