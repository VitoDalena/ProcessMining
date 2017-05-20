package edu.uci.ics.jung.visualization;

import edu.uci.ics.jung.visualization.transform.BidirectionalTransformer;
import edu.uci.ics.jung.visualization.transform.MutableTransformer;
import edu.uci.ics.jung.visualization.transform.shape.ShapeTransformer;
import edu.uci.ics.jung.visualization.util.ChangeEventSupport;
import java.awt.Shape;
import java.awt.geom.Point2D;

public abstract interface MultiLayerTransformer
  extends BidirectionalTransformer, ShapeTransformer, ChangeEventSupport
{
  public abstract void setTransformer(Layer paramLayer, MutableTransformer paramMutableTransformer);
  
  public abstract MutableTransformer getTransformer(Layer paramLayer);
  
  public abstract Point2D inverseTransform(Layer paramLayer, Point2D paramPoint2D);
  
  public abstract Point2D transform(Layer paramLayer, Point2D paramPoint2D);
  
  public abstract Shape transform(Layer paramLayer, Shape paramShape);
  
  public abstract Shape inverseTransform(Layer paramLayer, Shape paramShape);
  
  public abstract void setToIdentity();
}


/* Location:              /home/menick/Scrivania/ProcessMining/CNMining/CNMining.jar!/edu/uci/ics/jung/visualization/MultiLayerTransformer.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       0.7.1
 */