package edu.oswego.cs.dl.util.concurrent;

public abstract interface Barrier
{
  public abstract int parties();
  
  public abstract boolean broken();
}


/* Location:              /home/menick/Scrivania/ProcessMining/CNMining/CNMining.jar!/edu/oswego/cs/dl/util/concurrent/Barrier.class
 * Java compiler version: 1 (45.3)
 * JD-Core Version:       0.7.1
 */