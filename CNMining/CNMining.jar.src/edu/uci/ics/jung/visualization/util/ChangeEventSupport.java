package edu.uci.ics.jung.visualization.util;

import javax.swing.event.ChangeListener;

public abstract interface ChangeEventSupport
{
  public abstract void addChangeListener(ChangeListener paramChangeListener);
  
  public abstract void removeChangeListener(ChangeListener paramChangeListener);
  
  public abstract ChangeListener[] getChangeListeners();
  
  public abstract void fireStateChanged();
}


/* Location:              /home/menick/Scrivania/ProcessMining/CNMining/CNMining.jar!/edu/uci/ics/jung/visualization/util/ChangeEventSupport.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       0.7.1
 */