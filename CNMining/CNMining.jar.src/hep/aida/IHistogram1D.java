package hep.aida;

public abstract interface IHistogram1D
  extends IHistogram
{
  public abstract int binEntries(int paramInt);
  
  public abstract double binError(int paramInt);
  
  public abstract double binHeight(int paramInt);
  
  public abstract void fill(double paramDouble);
  
  public abstract void fill(double paramDouble1, double paramDouble2);
  
  public abstract double mean();
  
  public abstract int[] minMaxBins();
  
  public abstract double rms();
  
  public abstract IAxis xAxis();
}


/* Location:              /home/menick/Scrivania/ProcessMining/CNMining/CNMining.jar!/hep/aida/IHistogram1D.class
 * Java compiler version: 1 (45.3)
 * JD-Core Version:       0.7.1
 */