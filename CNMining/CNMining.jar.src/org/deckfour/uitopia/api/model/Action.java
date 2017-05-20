package org.deckfour.uitopia.api.model;

import java.util.Collection;
import java.util.List;

public abstract interface Action
  extends Cloneable
{
  public abstract String getName();
  
  public abstract String getPackage();
  
  public abstract Author getAuthor();
  
  public abstract List<Parameter> getInput();
  
  public abstract List<Parameter> getOutput();
  
  public abstract ActionStatus getStatus(List<Collection<? extends Resource>> paramList);
  
  public abstract ActionType getType();
}


/* Location:              /home/menick/Scrivania/ProcessMining/CNMining/CNMining.jar!/org/deckfour/uitopia/api/model/Action.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */