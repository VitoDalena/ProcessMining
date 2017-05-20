package org.deckfour.xes.util.progress;

public abstract interface XProgressListener
{
  public abstract void updateProgress(int paramInt1, int paramInt2);
  
  public abstract boolean isAborted();
}


/* Location:              /home/menick/Scrivania/ProcessMining/CNMining/CNMining.jar!/org/deckfour/xes/util/progress/XProgressListener.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */