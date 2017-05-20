package com.jgraph.components.labels;

import java.awt.Component;
import java.util.Hashtable;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import org.jgraph.graph.AttributeMap;
import org.jgraph.graph.ConnectionSet;
import org.jgraph.graph.DefaultGraphModel;
import org.jgraph.graph.Edge;

public class RichTextGraphModel
  extends DefaultGraphModel
{
  public static final Object VALUE_EMPTY = new Object();
  
  public RichTextGraphModel() {}
  
  public boolean acceptsSource(Object paramObject1, Object paramObject2)
  {
    return ((Edge)paramObject1).getTarget() != paramObject2;
  }
  
  public boolean acceptsTarget(Object paramObject1, Object paramObject2)
  {
    return ((Edge)paramObject1).getSource() != paramObject2;
  }
  
  public RichTextGraphModel(List paramList, AttributeMap paramAttributeMap, ConnectionSet paramConnectionSet)
  {
    super(paramList, paramAttributeMap, paramConnectionSet);
  }
  
  protected Object cloneUserObject(Object paramObject)
  {
    if ((paramObject instanceof RichTextBusinessObject)) {
      return ((RichTextBusinessObject)paramObject).clone();
    }
    return super.cloneUserObject(paramObject);
  }
  
  public Object valueForCellChanged(Object paramObject1, Object paramObject2)
  {
    Object localObject1 = getValue(paramObject1);
    if ((localObject1 instanceof RichTextBusinessObject))
    {
      RichTextBusinessObject localRichTextBusinessObject = (RichTextBusinessObject)localObject1;
      Object localObject2;
      if (((paramObject2 instanceof String)) || ((paramObject2 instanceof RichTextValue)) || ((paramObject2 instanceof Component)))
      {
        localObject2 = localRichTextBusinessObject.getValue();
        localRichTextBusinessObject.setValue(paramObject2);
        return localObject2;
      }
      if ((paramObject2 instanceof Map))
      {
        localObject2 = localRichTextBusinessObject.getProperties();
        localRichTextBusinessObject.setProperties((Map)paramObject2);
        return localObject2;
      }
    }
    return super.valueForCellChanged(paramObject1, paramObject2);
  }
  
  protected Map handleAttributes(Map paramMap)
  {
    Object localObject1 = super.handleAttributes(paramMap);
    if (paramMap != null)
    {
      if (localObject1 == null) {
        localObject1 = new Hashtable();
      }
      Iterator localIterator1 = paramMap.entrySet().iterator();
      while (localIterator1.hasNext())
      {
        Map.Entry localEntry1 = (Map.Entry)localIterator1.next();
        Object localObject2 = localEntry1.getKey();
        Map localMap = (Map)localEntry1.getValue();
        if ((localObject2 instanceof RichTextBusinessObject))
        {
          RichTextBusinessObject localRichTextBusinessObject = (RichTextBusinessObject)localObject2;
          Hashtable localHashtable = new Hashtable();
          Iterator localIterator2 = localMap.entrySet().iterator();
          while (localIterator2.hasNext())
          {
            Map.Entry localEntry2 = (Map.Entry)localIterator2.next();
            Object localObject3 = localEntry2.getKey();
            Object localObject4 = localEntry2.getValue();
            Object localObject5 = localObject4 == VALUE_EMPTY ? localRichTextBusinessObject.getProperties().remove(localObject3) : localRichTextBusinessObject.putProperty(localObject3, localObject4);
            if (localObject5 != null) {
              localHashtable.put(localObject3, localObject5);
            } else {
              localHashtable.put(localObject3, VALUE_EMPTY);
            }
          }
          ((Map)localObject1).put(localObject2, localHashtable);
        }
      }
    }
    return (Map)localObject1;
  }
}


/* Location:              /home/menick/Scrivania/ProcessMining/CNMining/CNMining.jar!/com/jgraph/components/labels/RichTextGraphModel.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */