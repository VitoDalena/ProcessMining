package bsh.collection;

import bsh.BshIterator;
import bsh.CollectionManager;
import bsh.CollectionManager.BasicBshIterator;
import java.util.Collection;
import java.util.Iterator;
import java.util.Map;

public class CollectionManagerImpl
  extends CollectionManager
{
  public BshIterator getBshIterator(Object paramObject)
    throws IllegalArgumentException
  {
    if (((paramObject instanceof Collection)) || ((paramObject instanceof Iterator))) {
      return new CollectionIterator(paramObject);
    }
    return new CollectionManager.BasicBshIterator(paramObject);
  }
  
  public boolean isMap(Object paramObject)
  {
    if ((paramObject instanceof Map)) {
      return true;
    }
    return super.isMap(paramObject);
  }
  
  public Object getFromMap(Object paramObject1, Object paramObject2)
  {
    return ((Map)paramObject1).get(paramObject2);
  }
  
  public Object putInMap(Object paramObject1, Object paramObject2, Object paramObject3)
  {
    return ((Map)paramObject1).put(paramObject2, paramObject3);
  }
}


/* Location:              /home/menick/Scrivania/ProcessMining/CNMining/CNMining.jar!/bsh/collection/CollectionManagerImpl.class
 * Java compiler version: 2 (46.0)
 * JD-Core Version:       0.7.1
 */