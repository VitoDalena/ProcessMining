package com.thoughtworks.xstream.core;

import com.thoughtworks.xstream.converters.MarshallingContext;
import com.thoughtworks.xstream.io.path.Path;

public abstract interface ReferencingMarshallingContext
  extends MarshallingContext
{
  public abstract Path currentPath();
  
  public abstract Object lookupReference(Object paramObject);
  
  public abstract void replace(Object paramObject1, Object paramObject2);
  
  public abstract void registerImplicit(Object paramObject);
}


/* Location:              /home/menick/Scrivania/ProcessMining/CNMining/CNMining.jar!/com/thoughtworks/xstream/core/ReferencingMarshallingContext.class
 * Java compiler version: 3 (47.0)
 * JD-Core Version:       0.7.1
 */