package com.thoughtworks.xstream.converters.reflection;

import java.lang.reflect.Field;

public abstract interface ReflectionProvider
{
  public abstract Object newInstance(Class paramClass);
  
  public abstract void visitSerializableFields(Object paramObject, Visitor paramVisitor);
  
  public abstract void writeField(Object paramObject1, String paramString, Object paramObject2, Class paramClass);
  
  public abstract Class getFieldType(Object paramObject, String paramString, Class paramClass);
  
  public abstract boolean fieldDefinedInClass(String paramString, Class paramClass);
  
  public abstract Field getField(Class paramClass, String paramString);
  
  public static abstract interface Visitor
  {
    public abstract void visit(String paramString, Class paramClass1, Class paramClass2, Object paramObject);
  }
}


/* Location:              /home/menick/Scrivania/ProcessMining/CNMining/CNMining.jar!/com/thoughtworks/xstream/converters/reflection/ReflectionProvider.class
 * Java compiler version: 3 (47.0)
 * JD-Core Version:       0.7.1
 */