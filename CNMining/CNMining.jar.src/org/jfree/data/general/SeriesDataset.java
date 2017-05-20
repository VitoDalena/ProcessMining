package org.jfree.data.general;

public abstract interface SeriesDataset
  extends Dataset
{
  public abstract int getSeriesCount();
  
  public abstract Comparable getSeriesKey(int paramInt);
  
  public abstract int indexOf(Comparable paramComparable);
}


/* Location:              /home/menick/Scrivania/ProcessMining/CNMining/CNMining.jar!/org/jfree/data/general/SeriesDataset.class
 * Java compiler version: 3 (47.0)
 * JD-Core Version:       0.7.1
 */