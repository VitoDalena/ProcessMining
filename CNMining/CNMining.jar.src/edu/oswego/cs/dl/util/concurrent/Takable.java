package edu.oswego.cs.dl.util.concurrent;

public abstract interface Takable
{
  public abstract Object take()
    throws InterruptedException;
  
  public abstract Object poll(long paramLong)
    throws InterruptedException;
}


/* Location:              /home/menick/Scrivania/ProcessMining/CNMining/CNMining.jar!/edu/oswego/cs/dl/util/concurrent/Takable.class
 * Java compiler version: 1 (45.3)
 * JD-Core Version:       0.7.1
 */