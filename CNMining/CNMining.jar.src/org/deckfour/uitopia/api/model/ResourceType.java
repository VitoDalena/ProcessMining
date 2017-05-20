package org.deckfour.uitopia.api.model;

import java.awt.Image;

public abstract interface ResourceType
{
  public abstract String getTypeName();
  
  public abstract Author getTypeAuthor();
  
  public abstract Image getTypeIcon();
  
  public abstract Class getTypeClass();
  
  public abstract boolean isAssignableFrom(ResourceType paramResourceType);
}


/* Location:              /home/menick/Scrivania/ProcessMining/CNMining/CNMining.jar!/org/deckfour/uitopia/api/model/ResourceType.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */