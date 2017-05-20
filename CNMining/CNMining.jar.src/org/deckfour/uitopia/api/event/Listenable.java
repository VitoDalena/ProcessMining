package org.deckfour.uitopia.api.event;

import java.util.Collection;

public abstract interface Listenable<T>
{
  public abstract void addListener(T paramT);
  
  public abstract void removeListener(T paramT);
  
  public abstract void removeAllListeners();
  
  public abstract Collection<T> getListeners();
}


/* Location:              /home/menick/Scrivania/ProcessMining/CNMining/CNMining.jar!/org/deckfour/uitopia/api/event/Listenable.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */