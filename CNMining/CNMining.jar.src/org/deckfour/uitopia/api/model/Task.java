package org.deckfour.uitopia.api.model;

import java.util.Collection;
import java.util.List;

public abstract interface Task<R extends Resource>
{
  public abstract Action getAction();
  
  public abstract List<Collection<R>> getParameterValues();
  
  public abstract double getProgress();
  
  public abstract void destroy();
}


/* Location:              /home/menick/Scrivania/ProcessMining/CNMining/CNMining.jar!/org/deckfour/uitopia/api/model/Task.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */