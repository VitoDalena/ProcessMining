package edu.uci.ics.jung.visualization.decorators;

import java.awt.Shape;
import org.apache.commons.collections15.Transformer;

public abstract interface SettableVertexShapeTransformer<V>
  extends Transformer<V, Shape>
{
  public abstract void setSizeTransformer(Transformer<V, Integer> paramTransformer);
  
  public abstract void setAspectRatioTransformer(Transformer<V, Float> paramTransformer);
}


/* Location:              /home/menick/Scrivania/ProcessMining/CNMining/CNMining.jar!/edu/uci/ics/jung/visualization/decorators/SettableVertexShapeTransformer.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       0.7.1
 */