package edu.uci.ics.jung.visualization.transform;

import java.awt.Shape;
import java.awt.geom.Point2D;

public abstract interface BidirectionalTransformer
{
  public abstract Point2D transform(Point2D paramPoint2D);
  
  public abstract Point2D inverseTransform(Point2D paramPoint2D);
  
  public abstract Shape transform(Shape paramShape);
  
  public abstract Shape inverseTransform(Shape paramShape);
}


/* Location:              /home/menick/Scrivania/ProcessMining/CNMining/CNMining.jar!/edu/uci/ics/jung/visualization/transform/BidirectionalTransformer.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       0.7.1
 */