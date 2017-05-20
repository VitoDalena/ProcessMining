package uk.ac.shef.wit.simmetrics.similaritymetrics.costfunctions;

public abstract class AbstractSubstitutionCost
  implements InterfaceSubstitutionCost
{
  public abstract String getShortDescriptionString();
  
  public abstract float getCost(String paramString1, int paramInt1, String paramString2, int paramInt2);
  
  public abstract float getMaxCost();
  
  public abstract float getMinCost();
}


/* Location:              /home/menick/Scrivania/ProcessMining/CNMining/CNMining.jar!/uk/ac/shef/wit/simmetrics/similaritymetrics/costfunctions/AbstractSubstitutionCost.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       0.7.1
 */