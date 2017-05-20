package org.jfree.data.statistics;

import org.jfree.data.category.CategoryDataset;

public abstract interface StatisticalCategoryDataset
  extends CategoryDataset
{
  public abstract Number getMeanValue(int paramInt1, int paramInt2);
  
  public abstract Number getMeanValue(Comparable paramComparable1, Comparable paramComparable2);
  
  public abstract Number getStdDevValue(int paramInt1, int paramInt2);
  
  public abstract Number getStdDevValue(Comparable paramComparable1, Comparable paramComparable2);
}


/* Location:              /home/menick/Scrivania/ProcessMining/CNMining/CNMining.jar!/org/jfree/data/statistics/StatisticalCategoryDataset.class
 * Java compiler version: 3 (47.0)
 * JD-Core Version:       0.7.1
 */