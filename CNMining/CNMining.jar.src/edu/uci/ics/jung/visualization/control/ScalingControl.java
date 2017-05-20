package edu.uci.ics.jung.visualization.control;

import edu.uci.ics.jung.visualization.VisualizationServer;
import java.awt.geom.Point2D;

public abstract interface ScalingControl
{
  public abstract void scale(VisualizationServer<?, ?> paramVisualizationServer, float paramFloat, Point2D paramPoint2D);
}


/* Location:              /home/menick/Scrivania/ProcessMining/CNMining/CNMining.jar!/edu/uci/ics/jung/visualization/control/ScalingControl.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       0.7.1
 */