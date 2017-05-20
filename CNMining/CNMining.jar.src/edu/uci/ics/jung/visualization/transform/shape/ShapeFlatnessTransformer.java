package edu.uci.ics.jung.visualization.transform.shape;

import java.awt.Shape;

public abstract interface ShapeFlatnessTransformer
  extends ShapeTransformer
{
  public abstract Shape transform(Shape paramShape, float paramFloat);
}


/* Location:              /home/menick/Scrivania/ProcessMining/CNMining/CNMining.jar!/edu/uci/ics/jung/visualization/transform/shape/ShapeFlatnessTransformer.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       0.7.1
 */