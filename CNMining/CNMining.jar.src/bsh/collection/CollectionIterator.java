package bsh.collection;

import bsh.BshIterator;
import java.util.Collection;
import java.util.Iterator;

public class CollectionIterator
  implements BshIterator
{
  private Iterator iterator = createIterator(paramObject);
  
  public CollectionIterator(Object paramObject) {}
  
  protected Iterator createIterator(Object paramObject)
  {
    if (paramObject == null) {
      throw new NullPointerException("Object arguments passed to the CollectionIterator constructor cannot be null.");
    }
    if ((paramObject instanceof Iterator)) {
      return (Iterator)paramObject;
    }
    if ((paramObject instanceof Collection)) {
      return ((Collection)paramObject).iterator();
    }
    throw new IllegalArgumentException("Cannot enumerate object of type " + paramObject.getClass());
  }
  
  public Object next()
  {
    return this.iterator.next();
  }
  
  public boolean hasNext()
  {
    return this.iterator.hasNext();
  }
}


/* Location:              /home/menick/Scrivania/ProcessMining/CNMining/CNMining.jar!/bsh/collection/CollectionIterator.class
 * Java compiler version: 2 (46.0)
 * JD-Core Version:       0.7.1
 */