package org.deckfour.uitopia.api.hub;

import java.util.List;
import org.deckfour.uitopia.api.model.Action;
import org.deckfour.uitopia.api.model.ActionType;
import org.deckfour.uitopia.api.model.ResourceType;

public abstract interface ActionManager<A extends Action>
{
  public abstract List<A> getActions(List<ResourceType> paramList1, List<ResourceType> paramList2, ActionType paramActionType);
  
  public abstract List<A> getActions(List<ResourceType> paramList1, List<ResourceType> paramList2);
  
  public abstract List<A> getActions();
}


/* Location:              /home/menick/Scrivania/ProcessMining/CNMining/CNMining.jar!/org/deckfour/uitopia/api/hub/ActionManager.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */