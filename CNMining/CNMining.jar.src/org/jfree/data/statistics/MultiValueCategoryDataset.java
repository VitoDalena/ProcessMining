package org.jfree.data.statistics;

import java.util.List;
import org.jfree.data.category.CategoryDataset;

public abstract interface MultiValueCategoryDataset
  extends CategoryDataset
{
  public abstract List getValues(int paramInt1, int paramInt2);
  
  public abstract List getValues(Comparable paramComparable1, Comparable paramComparable2);
}


/* Location:              /home/menick/Scrivania/ProcessMining/CNMining/CNMining.jar!/org/jfree/data/statistics/MultiValueCategoryDataset.class
 * Java compiler version: 3 (47.0)
 * JD-Core Version:       0.7.1
 */