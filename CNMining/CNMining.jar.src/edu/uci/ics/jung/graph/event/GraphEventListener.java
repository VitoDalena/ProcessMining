package edu.uci.ics.jung.graph.event;

import java.util.EventListener;

public abstract interface GraphEventListener<V, E>
  extends EventListener
{
  public abstract void handleGraphEvent(GraphEvent<V, E> paramGraphEvent);
}


/* Location:              /home/menick/Scrivania/ProcessMining/CNMining/CNMining.jar!/edu/uci/ics/jung/graph/event/GraphEventListener.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       0.7.1
 */