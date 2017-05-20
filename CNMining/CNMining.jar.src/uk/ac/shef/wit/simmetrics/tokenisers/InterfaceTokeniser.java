package uk.ac.shef.wit.simmetrics.tokenisers;

import java.util.ArrayList;
import java.util.Set;
import uk.ac.shef.wit.simmetrics.wordhandlers.InterfaceTermHandler;

public abstract interface InterfaceTokeniser
{
  public abstract String getShortDescriptionString();
  
  public abstract String getDelimiters();
  
  public abstract InterfaceTermHandler getStopWordHandler();
  
  public abstract void setStopWordHandler(InterfaceTermHandler paramInterfaceTermHandler);
  
  public abstract ArrayList<String> tokenizeToArrayList(String paramString);
  
  public abstract Set<String> tokenizeToSet(String paramString);
}


/* Location:              /home/menick/Scrivania/ProcessMining/CNMining/CNMining.jar!/uk/ac/shef/wit/simmetrics/tokenisers/InterfaceTokeniser.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       0.7.1
 */