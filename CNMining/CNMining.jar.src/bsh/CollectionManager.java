package bsh;

import java.util.Enumeration;
import java.util.Hashtable;
import java.util.Vector;

public class CollectionManager
{
  private static CollectionManager manager;
  
  public static synchronized CollectionManager getCollectionManager()
  {
    if ((manager == null) && (Capabilities.classExists("java.util.Collection"))) {
      try
      {
        Class localClass = Class.forName("bsh.collection.CollectionManagerImpl");
        manager = (CollectionManager)localClass.newInstance();
      }
      catch (Exception localException)
      {
        Interpreter.debug("unable to load CollectionManagerImpl: " + localException);
      }
    }
    if (manager == null) {
      manager = new CollectionManager();
    }
    return manager;
  }
  
  public boolean isBshIterable(Object paramObject)
  {
    try
    {
      getBshIterator(paramObject);
      return true;
    }
    catch (IllegalArgumentException localIllegalArgumentException) {}
    return false;
  }
  
  public BshIterator getBshIterator(Object paramObject)
    throws IllegalArgumentException
  {
    return new BasicBshIterator(paramObject);
  }
  
  public boolean isMap(Object paramObject)
  {
    return paramObject instanceof Hashtable;
  }
  
  public Object getFromMap(Object paramObject1, Object paramObject2)
  {
    return ((Hashtable)paramObject1).get(paramObject2);
  }
  
  public Object putInMap(Object paramObject1, Object paramObject2, Object paramObject3)
  {
    return ((Hashtable)paramObject1).put(paramObject2, paramObject3);
  }
  
  public static class BasicBshIterator
    implements BshIterator
  {
    Enumeration enumeration = createEnumeration(paramObject);
    
    public BasicBshIterator(Object paramObject) {}
    
    protected Enumeration createEnumeration(Object paramObject)
    {
      if (paramObject == null) {
        throw new NullPointerException("Object arguments passed to the BasicBshIterator constructor cannot be null.");
      }
      if ((paramObject instanceof Enumeration)) {
        return (Enumeration)paramObject;
      }
      if ((paramObject instanceof Vector)) {
        return ((Vector)paramObject).elements();
      }
      if (paramObject.getClass().isArray())
      {
        Object localObject = paramObject;
        return new CollectionManager.1(this, localObject);
      }
      if ((paramObject instanceof String)) {
        return createEnumeration(((String)paramObject).toCharArray());
      }
      if ((paramObject instanceof StringBuffer)) {
        return createEnumeration(paramObject.toString().toCharArray());
      }
      throw new IllegalArgumentException("Cannot enumerate object of type " + paramObject.getClass());
    }
    
    public Object next()
    {
      return this.enumeration.nextElement();
    }
    
    public boolean hasNext()
    {
      return this.enumeration.hasMoreElements();
    }
  }
}


/* Location:              /home/menick/Scrivania/ProcessMining/CNMining/CNMining.jar!/bsh/CollectionManager.class
 * Java compiler version: 2 (46.0)
 * JD-Core Version:       0.7.1
 */