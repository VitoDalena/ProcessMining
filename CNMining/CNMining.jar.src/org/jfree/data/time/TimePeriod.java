package org.jfree.data.time;

import java.util.Date;

public abstract interface TimePeriod
  extends Comparable
{
  public abstract Date getStart();
  
  public abstract Date getEnd();
}


/* Location:              /home/menick/Scrivania/ProcessMining/CNMining/CNMining.jar!/org/jfree/data/time/TimePeriod.class
 * Java compiler version: 3 (47.0)
 * JD-Core Version:       0.7.1
 */