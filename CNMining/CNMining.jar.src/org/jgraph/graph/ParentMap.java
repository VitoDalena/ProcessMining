package org.jgraph.graph;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Hashtable;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

public class ParentMap
  implements Serializable
{
  protected ArrayList entries = new ArrayList();
  protected Set changedNodes = new HashSet();
  protected Map childCount = new Hashtable();
  
  public ParentMap() {}
  
  public ParentMap(Object[] paramArrayOfObject, Object paramObject)
  {
    addEntries(paramArrayOfObject, paramObject);
  }
  
  public static ParentMap create(GraphModel paramGraphModel, Object[] paramArrayOfObject, boolean paramBoolean1, boolean paramBoolean2)
  {
    HashSet localHashSet = new HashSet();
    for (int i = 0; i < paramArrayOfObject.length; i++) {
      if (paramArrayOfObject[i] != null) {
        localHashSet.add(paramArrayOfObject[i]);
      }
    }
    ParentMap localParentMap = new ParentMap();
    for (int j = 0; j < paramArrayOfObject.length; j++)
    {
      Object localObject1 = paramGraphModel.getParent(paramArrayOfObject[j]);
      if ((localObject1 != null) && ((!paramBoolean2) || ((!paramBoolean1) && (localHashSet.contains(localObject1))))) {
        localParentMap.addEntry(paramArrayOfObject[j], paramBoolean1 ? null : localObject1);
      }
      if (paramBoolean1)
      {
        while (localHashSet.contains(localObject1)) {
          localObject1 = paramGraphModel.getParent(localObject1);
        }
        for (int k = 0; k < paramGraphModel.getChildCount(paramArrayOfObject[j]); k++)
        {
          Object localObject2 = paramGraphModel.getChild(paramArrayOfObject[j], k);
          if (!localHashSet.contains(localObject2)) {
            localParentMap.addEntry(localObject2, localObject1);
          }
        }
      }
    }
    return localParentMap;
  }
  
  public void addEntry(Object paramObject1, Object paramObject2)
  {
    if (paramObject1 != null)
    {
      this.entries.add(new Entry(paramObject1, paramObject2));
      this.changedNodes.add(paramObject1);
      if (paramObject2 != null) {
        this.changedNodes.add(paramObject2);
      }
    }
  }
  
  public void addEntries(Object[] paramArrayOfObject, Object paramObject)
  {
    for (int i = 0; i < paramArrayOfObject.length; i++) {
      addEntry(paramArrayOfObject[i], paramObject);
    }
  }
  
  public int size()
  {
    return this.entries.size();
  }
  
  public Iterator entries()
  {
    return this.entries.iterator();
  }
  
  public Set getChangedNodes()
  {
    return this.changedNodes;
  }
  
  public ParentMap clone(Map paramMap)
  {
    ParentMap localParentMap = new ParentMap();
    Iterator localIterator = entries();
    while (localIterator.hasNext())
    {
      Entry localEntry = (Entry)localIterator.next();
      Object localObject1 = paramMap.get(localEntry.getChild());
      Object localObject2 = paramMap.get(localEntry.getParent());
      if (localObject1 == null) {
        localObject1 = localEntry.getChild();
      }
      if (localObject2 == null) {
        localObject2 = localEntry.getParent();
      }
      if ((localObject1 != null) && (localObject2 != null)) {
        localParentMap.addEntry(localObject1, localObject2);
      }
    }
    return localParentMap;
  }
  
  public String toString()
  {
    String str = super.toString() + "\n";
    Iterator localIterator = entries();
    while (localIterator.hasNext())
    {
      Entry localEntry = (Entry)localIterator.next();
      str = str + " child=" + localEntry.getChild() + " parent=" + localEntry.getParent() + "\n";
    }
    return str;
  }
  
  public class Entry
    implements Serializable
  {
    protected Object child;
    protected Object parent;
    
    public Entry(Object paramObject1, Object paramObject2)
    {
      this.child = paramObject1;
      this.parent = paramObject2;
    }
    
    public Object getChild()
    {
      return this.child;
    }
    
    public Object getParent()
    {
      return this.parent;
    }
  }
}


/* Location:              /home/menick/Scrivania/ProcessMining/CNMining/CNMining.jar!/org/jgraph/graph/ParentMap.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */