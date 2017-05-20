package uk.ac.shef.wit.simmetrics.arbitrators;

import java.util.ArrayList;
import uk.ac.shef.wit.simmetrics.similaritymetrics.InterfaceStringMetric;

public abstract interface InterfaceMetricArbitrator
{
  public abstract ArrayList<InterfaceStringMetric> getArbitrationMetrics();
  
  public abstract void setArbitrationMetrics(ArrayList<InterfaceStringMetric> paramArrayList);
  
  public abstract void addArbitrationMetric(InterfaceStringMetric paramInterfaceStringMetric);
  
  public abstract void addArbitrationMetrics(ArrayList<InterfaceStringMetric> paramArrayList);
  
  public abstract void clearArbitrationMetrics();
  
  public abstract String getShortDescriptionString();
  
  public abstract String getLongDescriptionString();
  
  public abstract long getArbitrationTimingActual(String paramString1, String paramString2);
  
  public abstract float getArbitrationTimingEstimated(String paramString1, String paramString2);
  
  public abstract float getArbitrationScore(String paramString1, String paramString2);
}


/* Location:              /home/menick/Scrivania/ProcessMining/CNMining/CNMining.jar!/uk/ac/shef/wit/simmetrics/arbitrators/InterfaceMetricArbitrator.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       0.7.1
 */