package edu.oswego.cs.dl.util.concurrent;

public abstract interface Executor
{
  public abstract void execute(Runnable paramRunnable)
    throws InterruptedException;
}


/* Location:              /home/menick/Scrivania/ProcessMining/CNMining/CNMining.jar!/edu/oswego/cs/dl/util/concurrent/Executor.class
 * Java compiler version: 1 (45.3)
 * JD-Core Version:       0.7.1
 */