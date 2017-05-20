package com.carrotsearch.hppc;

import com.carrotsearch.hppc.predicates.BytePredicate;

public abstract interface ByteCollection
  extends ByteContainer
{
  public abstract int removeAllOccurrences(byte paramByte);
  
  public abstract int removeAll(ByteLookupContainer paramByteLookupContainer);
  
  public abstract int removeAll(BytePredicate paramBytePredicate);
  
  public abstract int retainAll(ByteLookupContainer paramByteLookupContainer);
  
  public abstract int retainAll(BytePredicate paramBytePredicate);
  
  public abstract void clear();
}


/* Location:              /home/menick/Scrivania/ProcessMining/CNMining/CNMining.jar!/com/carrotsearch/hppc/ByteCollection.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */