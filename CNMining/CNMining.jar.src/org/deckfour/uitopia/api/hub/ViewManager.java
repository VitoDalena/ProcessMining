package org.deckfour.uitopia.api.hub;

import java.util.List;
import org.deckfour.uitopia.api.event.Listenable;
import org.deckfour.uitopia.api.event.UpdateListener;
import org.deckfour.uitopia.api.model.Resource;
import org.deckfour.uitopia.api.model.View;
import org.deckfour.uitopia.api.model.ViewType;

public abstract interface ViewManager
  extends Listenable<UpdateListener>
{
  public abstract List<View> getViews();
  
  public abstract void addView(View paramView);
  
  public abstract void removeView(View paramView);
  
  public abstract List<ViewType> getViewTypes(Resource paramResource);
}


/* Location:              /home/menick/Scrivania/ProcessMining/CNMining/CNMining.jar!/org/deckfour/uitopia/api/hub/ViewManager.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */