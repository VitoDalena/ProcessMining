package edu.oswego.cs.dl.util.concurrent;

public abstract interface ReadWriteLock
{
  public abstract Sync readLock();
  
  public abstract Sync writeLock();
}


/* Location:              /home/menick/Scrivania/ProcessMining/CNMining/CNMining.jar!/edu/oswego/cs/dl/util/concurrent/ReadWriteLock.class
 * Java compiler version: 1 (45.3)
 * JD-Core Version:       0.7.1
 */