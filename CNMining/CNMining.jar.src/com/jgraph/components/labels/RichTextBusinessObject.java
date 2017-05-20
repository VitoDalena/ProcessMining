package com.jgraph.components.labels;

import java.awt.Component;
import java.io.Serializable;
import java.util.Hashtable;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

public class RichTextBusinessObject
  implements Cloneable, Serializable
{
  public static String valueKey = "value";
  protected Map properties = new Hashtable();
  
  public RichTextBusinessObject()
  {
    this("");
  }
  
  public RichTextBusinessObject(Object paramObject)
  {
    setValue(paramObject);
  }
  
  public Map getProperties()
  {
    return this.properties;
  }
  
  public void setProperties(Map paramMap)
  {
    this.properties = paramMap;
  }
  
  public void setValue(Object paramObject)
  {
    putProperty(valueKey, paramObject);
  }
  
  public Object getValue()
  {
    return getProperty(valueKey);
  }
  
  public boolean isRichText()
  {
    return getValue() instanceof RichTextValue;
  }
  
  public boolean isComponent()
  {
    return getValue() instanceof Component;
  }
  
  public Object putProperty(Object paramObject1, Object paramObject2)
  {
    if ((paramObject1 != null) && (paramObject2 != null)) {
      return this.properties.put(paramObject1, paramObject2);
    }
    return null;
  }
  
  public Object getProperty(Object paramObject)
  {
    if (paramObject != null) {
      return this.properties.get(paramObject);
    }
    return null;
  }
  
  public String getTooltip()
  {
    String str1 = "";
    String str2 = toString();
    if (str2.length() > 0) {
      str1 = str1 + "<strong>" + chopString(str2, 20) + "</strong><br>";
    }
    Iterator localIterator = getProperties().entrySet().iterator();
    while (localIterator.hasNext())
    {
      Map.Entry localEntry = (Map.Entry)localIterator.next();
      if (!localEntry.getKey().equals(valueKey)) {
        str1 = str1 + localEntry.getKey() + ": " + chopString(String.valueOf(localEntry.getValue()), 20) + "<br>";
      }
    }
    return str1;
  }
  
  protected String chopString(String paramString, int paramInt)
  {
    if ((paramString != null) && (paramString.length() > paramInt)) {
      paramString = paramString.substring(0, paramInt) + "...";
    }
    return paramString;
  }
  
  public String toString()
  {
    Object localObject = getValue();
    if (localObject != null) {
      return String.valueOf(localObject);
    }
    return "";
  }
  
  public Object clone()
  {
    RichTextBusinessObject localRichTextBusinessObject;
    try
    {
      localRichTextBusinessObject = (RichTextBusinessObject)super.clone();
    }
    catch (CloneNotSupportedException localCloneNotSupportedException)
    {
      localRichTextBusinessObject = new RichTextBusinessObject();
    }
    localRichTextBusinessObject.setProperties(new Hashtable(getProperties()));
    return localRichTextBusinessObject;
  }
}


/* Location:              /home/menick/Scrivania/ProcessMining/CNMining/CNMining.jar!/com/jgraph/components/labels/RichTextBusinessObject.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */