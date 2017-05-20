package org.apache.commons.collections15;

import java.util.Collection;
import java.util.Iterator;
import java.util.Set;

public abstract interface Bag<E>
  extends Collection<E>
{
  public abstract int getCount(E paramE);
  
  public abstract boolean add(E paramE);
  
  public abstract boolean add(E paramE, int paramInt);
  
  public abstract boolean remove(Object paramObject);
  
  public abstract boolean remove(E paramE, int paramInt);
  
  public abstract Set<E> uniqueSet();
  
  public abstract int size();
  
  public abstract boolean containsAll(Collection<?> paramCollection);
  
  public abstract boolean removeAll(Collection<?> paramCollection);
  
  public abstract boolean retainAll(Collection<?> paramCollection);
  
  public abstract Iterator<E> iterator();
}


/* Location:              /home/menick/Scrivania/ProcessMining/CNMining/CNMining.jar!/org/apache/commons/collections15/Bag.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       0.7.1
 */