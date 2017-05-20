package com.thoughtworks.xstream.mapper;

import com.thoughtworks.xstream.converters.Converter;
import com.thoughtworks.xstream.converters.SingleValueConverter;

public abstract interface Mapper
{
  public abstract String serializedClass(Class paramClass);
  
  public abstract Class realClass(String paramString);
  
  public abstract String serializedMember(Class paramClass, String paramString);
  
  public abstract String realMember(Class paramClass, String paramString);
  
  public abstract boolean isImmutableValueType(Class paramClass);
  
  public abstract Class defaultImplementationOf(Class paramClass);
  
  public abstract String aliasForAttribute(String paramString);
  
  public abstract String attributeForAlias(String paramString);
  
  public abstract String aliasForSystemAttribute(String paramString);
  
  public abstract String getFieldNameForItemTypeAndName(Class paramClass1, Class paramClass2, String paramString);
  
  public abstract Class getItemTypeForItemFieldName(Class paramClass, String paramString);
  
  public abstract ImplicitCollectionMapping getImplicitCollectionDefForFieldName(Class paramClass, String paramString);
  
  public abstract boolean shouldSerializeMember(Class paramClass, String paramString);
  
  /**
   * @deprecated
   */
  public abstract SingleValueConverter getConverterFromItemType(String paramString, Class paramClass);
  
  /**
   * @deprecated
   */
  public abstract SingleValueConverter getConverterFromItemType(Class paramClass);
  
  /**
   * @deprecated
   */
  public abstract SingleValueConverter getConverterFromAttribute(String paramString);
  
  public abstract Converter getLocalConverter(Class paramClass, String paramString);
  
  public abstract Mapper lookupMapperOfType(Class paramClass);
  
  public abstract SingleValueConverter getConverterFromItemType(String paramString, Class paramClass1, Class paramClass2);
  
  /**
   * @deprecated
   */
  public abstract String aliasForAttribute(Class paramClass, String paramString);
  
  /**
   * @deprecated
   */
  public abstract String attributeForAlias(Class paramClass, String paramString);
  
  /**
   * @deprecated
   */
  public abstract SingleValueConverter getConverterFromAttribute(Class paramClass, String paramString);
  
  public abstract SingleValueConverter getConverterFromAttribute(Class paramClass1, String paramString, Class paramClass2);
  
  public static abstract interface ImplicitCollectionMapping
  {
    public abstract String getFieldName();
    
    public abstract String getItemFieldName();
    
    public abstract Class getItemType();
  }
  
  public static class Null {}
}


/* Location:              /home/menick/Scrivania/ProcessMining/CNMining/CNMining.jar!/com/thoughtworks/xstream/mapper/Mapper.class
 * Java compiler version: 3 (47.0)
 * JD-Core Version:       0.7.1
 */