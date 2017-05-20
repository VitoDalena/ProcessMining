package org.deckfour.uitopia.api.model;

public abstract interface Parameter
{
  public static final int CARDINALITY_INFINITY = Integer.MAX_VALUE;
  
  public abstract int getMinCardinality();
  
  public abstract int getMaxCardinality();
  
  public abstract String getName();
  
  public abstract ResourceType getType();
}


/* Location:              /home/menick/Scrivania/ProcessMining/CNMining/CNMining.jar!/org/deckfour/uitopia/api/model/Parameter.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */