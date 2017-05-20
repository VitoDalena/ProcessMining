package org.jgraph.event;

import java.util.EventListener;

public abstract interface GraphModelListener
  extends EventListener
{
  public abstract void graphChanged(GraphModelEvent paramGraphModelEvent);
}


/* Location:              /home/menick/Scrivania/ProcessMining/CNMining/CNMining.jar!/org/jgraph/event/GraphModelListener.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */