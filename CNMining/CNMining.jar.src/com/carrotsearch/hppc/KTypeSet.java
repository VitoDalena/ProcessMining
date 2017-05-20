package com.carrotsearch.hppc;

public abstract interface KTypeSet<KType>
  extends KTypeCollection<KType>
{
  public abstract boolean add(KType paramKType);
  
  public abstract boolean equals(Object paramObject);
  
  public abstract int hashCode();
}


/* Location:              /home/menick/Scrivania/ProcessMining/CNMining/CNMining.jar!/com/carrotsearch/hppc/KTypeSet.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */