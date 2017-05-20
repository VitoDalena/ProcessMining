package edu.uci.ics.jung.visualization.picking;

import java.awt.ItemSelectable;
import java.util.Set;

public abstract interface PickedState<T>
  extends PickedInfo<T>, ItemSelectable
{
  public abstract boolean pick(T paramT, boolean paramBoolean);
  
  public abstract void clear();
  
  public abstract Set<T> getPicked();
  
  public abstract boolean isPicked(T paramT);
}


/* Location:              /home/menick/Scrivania/ProcessMining/CNMining/CNMining.jar!/edu/uci/ics/jung/visualization/picking/PickedState.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       0.7.1
 */