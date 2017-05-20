package hep.aida;

import java.io.Serializable;

public abstract interface IAxis
  extends Serializable
{
  public static final long serialVersionUID = 1020L;
  
  public abstract double binCentre(int paramInt);
  
  public abstract double binLowerEdge(int paramInt);
  
  public abstract int bins();
  
  public abstract double binUpperEdge(int paramInt);
  
  public abstract double binWidth(int paramInt);
  
  public abstract int coordToIndex(double paramDouble);
  
  public abstract double lowerEdge();
  
  public abstract double upperEdge();
}


/* Location:              /home/menick/Scrivania/ProcessMining/CNMining/CNMining.jar!/hep/aida/IAxis.class
 * Java compiler version: 1 (45.3)
 * JD-Core Version:       0.7.1
 */