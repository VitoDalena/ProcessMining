package edu.uci.ics.jung.visualization;

import edu.uci.ics.jung.algorithms.layout.Layout;
import edu.uci.ics.jung.algorithms.layout.util.Relaxer;
import edu.uci.ics.jung.visualization.util.ChangeEventSupport;
import java.awt.Dimension;
import javax.swing.event.ChangeListener;

public abstract interface VisualizationModel<V, E>
  extends ChangeEventSupport
{
  public abstract Relaxer getRelaxer();
  
  public abstract void setGraphLayout(Layout<V, E> paramLayout);
  
  public abstract void setGraphLayout(Layout<V, E> paramLayout, Dimension paramDimension);
  
  public abstract Layout<V, E> getGraphLayout();
  
  public abstract void addChangeListener(ChangeListener paramChangeListener);
  
  public abstract void removeChangeListener(ChangeListener paramChangeListener);
  
  public abstract ChangeListener[] getChangeListeners();
}


/* Location:              /home/menick/Scrivania/ProcessMining/CNMining/CNMining.jar!/edu/uci/ics/jung/visualization/VisualizationModel.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       0.7.1
 */