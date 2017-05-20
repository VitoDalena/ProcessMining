package org.deckfour.uitopia.api.hub;

import java.util.Date;
import java.util.List;
import org.deckfour.uitopia.api.event.TaskListener;
import org.deckfour.uitopia.api.model.Action;
import org.deckfour.uitopia.api.model.Author;
import org.deckfour.uitopia.api.model.Resource;
import org.deckfour.uitopia.api.model.Task;

public abstract interface FrameworkHub<A extends Action, T extends Task<TR>, R extends Resource, TR extends R>
{
  public abstract ResourceManager<R> getResourceManager();
  
  public abstract ActionManager<A> getActionManager();
  
  public abstract TaskManager<T, TR> getTaskManager();
  
  public abstract ViewManager getViewManager();
  
  public abstract void shutdown(TaskListener paramTaskListener);
  
  public abstract String getFrameworkVersion();
  
  public abstract Date getFrameworkReleaseDate();
  
  public abstract List<Author> getFrameworkAuthors();
}


/* Location:              /home/menick/Scrivania/ProcessMining/CNMining/CNMining.jar!/org/deckfour/uitopia/api/hub/FrameworkHub.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */