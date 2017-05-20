package org.deckfour.xes.info;

import java.util.Date;

public abstract interface XTimeBounds
{
  public abstract Date getStartDate();
  
  public abstract Date getEndDate();
  
  public abstract boolean isWithin(Date paramDate);
  
  public abstract String toString();
}


/* Location:              /home/menick/Scrivania/ProcessMining/CNMining/CNMining.jar!/org/deckfour/xes/info/XTimeBounds.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */