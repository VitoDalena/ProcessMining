package org.deckfour.uitopia.api.model;

public abstract interface ViewType
{
  public abstract String getTypeName();
  
  public abstract Author getAuthor();
  
  public abstract View createView(Resource paramResource)
    throws IllegalArgumentException;
  
  public abstract ResourceType getViewableType();
}


/* Location:              /home/menick/Scrivania/ProcessMining/CNMining/CNMining.jar!/org/deckfour/uitopia/api/model/ViewType.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */