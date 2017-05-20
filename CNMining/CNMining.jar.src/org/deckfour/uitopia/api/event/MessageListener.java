package org.deckfour.uitopia.api.event;

public abstract interface MessageListener
{
  public abstract void error(String paramString);
  
  public abstract void error(String paramString, Throwable paramThrowable);
  
  public abstract void warning(String paramString);
  
  public abstract void warning(String paramString, Throwable paramThrowable);
  
  public abstract void info(String paramString);
  
  public abstract void debug(String paramString);
}


/* Location:              /home/menick/Scrivania/ProcessMining/CNMining/CNMining.jar!/org/deckfour/uitopia/api/event/MessageListener.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */