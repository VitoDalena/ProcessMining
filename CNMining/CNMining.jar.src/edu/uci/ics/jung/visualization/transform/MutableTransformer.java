package edu.uci.ics.jung.visualization.transform;

import edu.uci.ics.jung.visualization.transform.shape.ShapeTransformer;
import edu.uci.ics.jung.visualization.util.ChangeEventSupport;
import java.awt.geom.AffineTransform;
import java.awt.geom.Point2D;

public abstract interface MutableTransformer
  extends ShapeTransformer, ChangeEventSupport
{
  public abstract void translate(double paramDouble1, double paramDouble2);
  
  public abstract void setTranslate(double paramDouble1, double paramDouble2);
  
  public abstract void scale(double paramDouble1, double paramDouble2, Point2D paramPoint2D);
  
  public abstract void setScale(double paramDouble1, double paramDouble2, Point2D paramPoint2D);
  
  public abstract void rotate(double paramDouble, Point2D paramPoint2D);
  
  public abstract void rotate(double paramDouble1, double paramDouble2, double paramDouble3);
  
  public abstract void shear(double paramDouble1, double paramDouble2, Point2D paramPoint2D);
  
  public abstract void concatenate(AffineTransform paramAffineTransform);
  
  public abstract void preConcatenate(AffineTransform paramAffineTransform);
  
  public abstract double getScaleX();
  
  public abstract double getScaleY();
  
  public abstract double getScale();
  
  public abstract double getTranslateX();
  
  public abstract double getTranslateY();
  
  public abstract double getShearX();
  
  public abstract double getShearY();
  
  public abstract AffineTransform getTransform();
  
  public abstract void setToIdentity();
  
  public abstract double getRotation();
}


/* Location:              /home/menick/Scrivania/ProcessMining/CNMining/CNMining.jar!/edu/uci/ics/jung/visualization/transform/MutableTransformer.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       0.7.1
 */