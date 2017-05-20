package org.deckfour.uitopia.api.hub;

import java.io.IOException;
import java.util.List;
import org.deckfour.uitopia.api.event.Listenable;
import org.deckfour.uitopia.api.event.UpdateListener;
import org.deckfour.uitopia.api.model.Resource;
import org.deckfour.uitopia.api.model.ResourceFilter;
import org.deckfour.uitopia.api.model.ResourceType;

public abstract interface ResourceManager<R extends Resource>
  extends Listenable<UpdateListener>
{
  public abstract boolean importResource()
    throws IOException;
  
  public abstract boolean exportResource(Resource paramResource)
    throws IOException;
  
  public abstract List<ResourceType> getAllSupportedResourceTypes();
  
  public abstract List<R> getAllResources(ResourceFilter paramResourceFilter);
  
  public abstract List<R> getAllResources();
  
  public abstract List<R> getFavoriteResources(ResourceFilter paramResourceFilter);
  
  public abstract List<R> getFavoriteResources();
  
  public abstract List<R> getImportedResources(ResourceFilter paramResourceFilter);
  
  public abstract List<R> getImportedResources();
  
  public abstract List<R> getParentsOf(Resource paramResource, ResourceFilter paramResourceFilter);
  
  public abstract List<R> getParentsOf(Resource paramResource);
  
  public abstract List<R> getChildrenOf(Resource paramResource, ResourceFilter paramResourceFilter);
  
  public abstract List<R> getChildrenOf(Resource paramResource);
  
  public abstract List<ResourceType> getResourceTypes(List<? extends Resource> paramList);
}


/* Location:              /home/menick/Scrivania/ProcessMining/CNMining/CNMining.jar!/org/deckfour/uitopia/api/hub/ResourceManager.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */