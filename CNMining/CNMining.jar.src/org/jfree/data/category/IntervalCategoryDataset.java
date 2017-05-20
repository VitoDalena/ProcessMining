package org.jfree.data.category;

public abstract interface IntervalCategoryDataset
  extends CategoryDataset
{
  public abstract Number getStartValue(int paramInt1, int paramInt2);
  
  public abstract Number getStartValue(Comparable paramComparable1, Comparable paramComparable2);
  
  public abstract Number getEndValue(int paramInt1, int paramInt2);
  
  public abstract Number getEndValue(Comparable paramComparable1, Comparable paramComparable2);
}


/* Location:              /home/menick/Scrivania/ProcessMining/CNMining/CNMining.jar!/org/jfree/data/category/IntervalCategoryDataset.class
 * Java compiler version: 3 (47.0)
 * JD-Core Version:       0.7.1
 */