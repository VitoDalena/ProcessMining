package uk.ac.shef.wit.simmetrics.similaritymetrics;

public abstract interface InterfaceStringMetric
{
  public abstract String getShortDescriptionString();
  
  public abstract String getLongDescriptionString();
  
  public abstract long getSimilarityTimingActual(String paramString1, String paramString2);
  
  public abstract float getSimilarityTimingEstimated(String paramString1, String paramString2);
  
  public abstract float getSimilarity(String paramString1, String paramString2);
  
  public abstract String getSimilarityExplained(String paramString1, String paramString2);
}


/* Location:              /home/menick/Scrivania/ProcessMining/CNMining/CNMining.jar!/uk/ac/shef/wit/simmetrics/similaritymetrics/InterfaceStringMetric.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       0.7.1
 */