package edu.uci.ics.jung.algorithms.layout.util;

public abstract interface Relaxer
{
  public abstract void relax();
  
  public abstract void prerelax();
  
  public abstract void pause();
  
  public abstract void resume();
  
  public abstract void stop();
  
  public abstract void setSleepTime(long paramLong);
}


/* Location:              /home/menick/Scrivania/ProcessMining/CNMining/CNMining.jar!/edu/uci/ics/jung/algorithms/layout/util/Relaxer.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       0.7.1
 */