package org.jfree.data;

public abstract interface DomainInfo
{
  public abstract double getDomainLowerBound(boolean paramBoolean);
  
  public abstract double getDomainUpperBound(boolean paramBoolean);
  
  public abstract Range getDomainBounds(boolean paramBoolean);
}


/* Location:              /home/menick/Scrivania/ProcessMining/CNMining/CNMining.jar!/org/jfree/data/DomainInfo.class
 * Java compiler version: 3 (47.0)
 * JD-Core Version:       0.7.1
 */