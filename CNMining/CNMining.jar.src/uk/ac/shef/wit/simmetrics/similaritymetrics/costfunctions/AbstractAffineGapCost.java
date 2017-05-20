package uk.ac.shef.wit.simmetrics.similaritymetrics.costfunctions;

public abstract class AbstractAffineGapCost
  implements InterfaceAffineGapCost
{
  public abstract String getShortDescriptionString();
  
  public abstract float getCost(String paramString, int paramInt1, int paramInt2);
  
  public abstract float getMaxCost();
  
  public abstract float getMinCost();
}


/* Location:              /home/menick/Scrivania/ProcessMining/CNMining/CNMining.jar!/uk/ac/shef/wit/simmetrics/similaritymetrics/costfunctions/AbstractAffineGapCost.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       0.7.1
 */