package edu.oswego.cs.dl.util.concurrent;

public abstract interface Puttable
{
  public abstract void put(Object paramObject)
    throws InterruptedException;
  
  public abstract boolean offer(Object paramObject, long paramLong)
    throws InterruptedException;
}


/* Location:              /home/menick/Scrivania/ProcessMining/CNMining/CNMining.jar!/edu/oswego/cs/dl/util/concurrent/Puttable.class
 * Java compiler version: 1 (45.3)
 * JD-Core Version:       0.7.1
 */