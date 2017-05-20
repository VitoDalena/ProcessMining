package org.apache.commons.collections15.functors;

import org.apache.commons.collections15.Predicate;

public abstract interface PredicateDecorator<T>
  extends Predicate<T>
{
  public abstract Predicate<? super T>[] getPredicates();
}


/* Location:              /home/menick/Scrivania/ProcessMining/CNMining/CNMining.jar!/org/apache/commons/collections15/functors/PredicateDecorator.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       0.7.1
 */