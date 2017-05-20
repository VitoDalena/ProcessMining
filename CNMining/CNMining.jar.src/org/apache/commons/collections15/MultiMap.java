package org.apache.commons.collections15;

import java.util.Collection;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

public abstract interface MultiMap<K, V>
{
  public abstract V remove(Object paramObject1, Object paramObject2);
  
  public abstract int size(Object paramObject);
  
  public abstract int size();
  
  public abstract Collection<V> get(Object paramObject);
  
  public abstract boolean containsValue(Object paramObject);
  
  public abstract boolean containsValue(Object paramObject1, Object paramObject2);
  
  public abstract V put(K paramK, V paramV);
  
  public abstract Collection<V> remove(Object paramObject);
  
  public abstract Collection<V> values();
  
  public abstract boolean isEmpty();
  
  public abstract boolean containsKey(Object paramObject);
  
  public abstract void putAll(Map<? extends K, ? extends V> paramMap);
  
  public abstract void putAll(MultiMap<? extends K, ? extends V> paramMultiMap);
  
  public abstract boolean putAll(K paramK, Collection<? extends V> paramCollection);
  
  public abstract Iterator<V> iterator(Object paramObject);
  
  public abstract void clear();
  
  public abstract Set<K> keySet();
  
  public abstract Set<Map.Entry<K, Collection<V>>> entrySet();
  
  public abstract Map<K, Collection<V>> map();
  
  public abstract boolean equals(Object paramObject);
  
  public abstract int hashCode();
}


/* Location:              /home/menick/Scrivania/ProcessMining/CNMining/CNMining.jar!/org/apache/commons/collections15/MultiMap.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       0.7.1
 */