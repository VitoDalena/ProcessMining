package uk.ac.shef.wit.simmetrics.wordhandlers;

import java.io.Serializable;

public abstract interface InterfaceTermHandler
  extends Serializable
{
  public abstract void addWord(String paramString);
  
  public abstract void removeWord(String paramString);
  
  public abstract String getShortDescriptionString();
  
  public abstract int getNumberOfWords();
  
  public abstract boolean isWord(String paramString);
  
  public abstract StringBuffer getWordsAsBuffer();
}


/* Location:              /home/menick/Scrivania/ProcessMining/CNMining/CNMining.jar!/uk/ac/shef/wit/simmetrics/wordhandlers/InterfaceTermHandler.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       0.7.1
 */