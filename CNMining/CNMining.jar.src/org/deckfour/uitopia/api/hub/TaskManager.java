package org.deckfour.uitopia.api.hub;

import java.util.Collection;
import java.util.List;
import org.deckfour.uitopia.api.event.TaskListener;
import org.deckfour.uitopia.api.model.Action;
import org.deckfour.uitopia.api.model.Resource;
import org.deckfour.uitopia.api.model.Task;

public abstract interface TaskManager<T extends Task<R>, R extends Resource>
{
  public abstract Task<R> execute(Action paramAction, List<Collection<? extends Resource>> paramList, TaskListener paramTaskListener)
    throws Exception;
  
  public abstract boolean isActionableResource(Resource paramResource);
  
  public abstract List<T> getActiveTasks();
  
  public abstract List<T> getAllTasks();
}


/* Location:              /home/menick/Scrivania/ProcessMining/CNMining/CNMining.jar!/org/deckfour/uitopia/api/hub/TaskManager.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */