package com.carrotsearch.hppc;

public abstract interface ArraySizingStrategy
{
  public abstract int round(int paramInt);
  
  public abstract int grow(int paramInt1, int paramInt2, int paramInt3);
}


/* Location:              /home/menick/Scrivania/ProcessMining/CNMining/CNMining.jar!/com/carrotsearch/hppc/ArraySizingStrategy.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */