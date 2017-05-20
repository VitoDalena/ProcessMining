package edu.uci.ics.jung.visualization.transform.shape;

import edu.uci.ics.jung.visualization.transform.BidirectionalTransformer;
import java.awt.Shape;

public abstract interface ShapeTransformer
  extends BidirectionalTransformer
{
  public abstract Shape transform(Shape paramShape);
  
  public abstract Shape inverseTransform(Shape paramShape);
}


/* Location:              /home/menick/Scrivania/ProcessMining/CNMining/CNMining.jar!/edu/uci/ics/jung/visualization/transform/shape/ShapeTransformer.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       0.7.1
 */