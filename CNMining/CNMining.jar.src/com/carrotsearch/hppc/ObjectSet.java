package com.carrotsearch.hppc;

public abstract interface ObjectSet<KType>
  extends ObjectCollection<KType>
{
  public abstract boolean add(KType paramKType);
  
  public abstract boolean equals(Object paramObject);
  
  public abstract int hashCode();
}


/* Location:              /home/menick/Scrivania/ProcessMining/CNMining/CNMining.jar!/com/carrotsearch/hppc/ObjectSet.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */