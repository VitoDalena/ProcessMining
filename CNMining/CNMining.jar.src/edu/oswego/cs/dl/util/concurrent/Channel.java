package edu.oswego.cs.dl.util.concurrent;

public abstract interface Channel
  extends Puttable, Takable
{
  public abstract void put(Object paramObject)
    throws InterruptedException;
  
  public abstract boolean offer(Object paramObject, long paramLong)
    throws InterruptedException;
  
  public abstract Object take()
    throws InterruptedException;
  
  public abstract Object poll(long paramLong)
    throws InterruptedException;
  
  public abstract Object peek();
}


/* Location:              /home/menick/Scrivania/ProcessMining/CNMining/CNMining.jar!/edu/oswego/cs/dl/util/concurrent/Channel.class
 * Java compiler version: 1 (45.3)
 * JD-Core Version:       0.7.1
 */