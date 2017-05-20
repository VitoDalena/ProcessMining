package org.deckfour.uitopia.api.model;

import java.awt.Image;
import java.util.Date;

public abstract interface Resource
{
  public abstract String getName();
  
  public abstract void setName(String paramString);
  
  public abstract Action getSourceAction();
  
  public abstract boolean isFavorite();
  
  public abstract void setFavorite(boolean paramBoolean);
  
  public abstract Image getPreview(int paramInt1, int paramInt2);
  
  public abstract Date getCreationTime();
  
  public abstract Date getLastAccessTime();
  
  public abstract void updateLastAccessTime();
  
  public abstract void destroy();
  
  public abstract boolean isDestroyed();
  
  public abstract ResourceType getType();
}


/* Location:              /home/menick/Scrivania/ProcessMining/CNMining/CNMining.jar!/org/deckfour/uitopia/api/model/Resource.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */