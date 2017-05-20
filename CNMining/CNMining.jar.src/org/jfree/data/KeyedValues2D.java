package org.jfree.data;

import java.util.List;

public abstract interface KeyedValues2D
  extends Values2D
{
  public abstract Comparable getRowKey(int paramInt);
  
  public abstract int getRowIndex(Comparable paramComparable);
  
  public abstract List getRowKeys();
  
  public abstract Comparable getColumnKey(int paramInt);
  
  public abstract int getColumnIndex(Comparable paramComparable);
  
  public abstract List getColumnKeys();
  
  public abstract Number getValue(Comparable paramComparable1, Comparable paramComparable2);
}


/* Location:              /home/menick/Scrivania/ProcessMining/CNMining/CNMining.jar!/org/jfree/data/KeyedValues2D.class
 * Java compiler version: 3 (47.0)
 * JD-Core Version:       0.7.1
 */