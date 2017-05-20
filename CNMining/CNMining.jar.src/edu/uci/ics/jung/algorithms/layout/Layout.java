package edu.uci.ics.jung.algorithms.layout;

import edu.uci.ics.jung.graph.Graph;
import java.awt.Dimension;
import java.awt.geom.Point2D;
import org.apache.commons.collections15.Transformer;

public abstract interface Layout<V, E>
  extends Transformer<V, Point2D>
{
  public abstract void initialize();
  
  public abstract void setInitializer(Transformer<V, Point2D> paramTransformer);
  
  public abstract void setGraph(Graph<V, E> paramGraph);
  
  public abstract Graph<V, E> getGraph();
  
  public abstract void reset();
  
  public abstract void setSize(Dimension paramDimension);
  
  public abstract Dimension getSize();
  
  public abstract void lock(V paramV, boolean paramBoolean);
  
  public abstract boolean isLocked(V paramV);
  
  public abstract void setLocation(V paramV, Point2D paramPoint2D);
}


/* Location:              /home/menick/Scrivania/ProcessMining/CNMining/CNMining.jar!/edu/uci/ics/jung/algorithms/layout/Layout.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       0.7.1
 */