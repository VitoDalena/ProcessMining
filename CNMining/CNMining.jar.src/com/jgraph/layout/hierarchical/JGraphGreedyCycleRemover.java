package com.jgraph.layout.hierarchical;

import com.jgraph.layout.JGraphFacade;
import com.jgraph.layout.hierarchical.model.JGraphHierarchyModel;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Hashtable;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

public class JGraphGreedyCycleRemover
  implements JGraphHierarchicalLayoutStep
{
  protected List sources = null;
  protected List sinks = null;
  protected Object[] roots = null;
  
  public JGraphGreedyCycleRemover(Object[] paramArrayOfObject)
  {
    this.roots = paramArrayOfObject;
  }
  
  public JGraphHierarchyModel run(JGraphFacade paramJGraphFacade, JGraphHierarchyModel paramJGraphHierarchyModel)
  {
    if (paramJGraphHierarchyModel == null)
    {
      Object[] arrayOfObject = getOrderedVertices(paramJGraphFacade);
      paramJGraphHierarchyModel = new JGraphHierarchyModel(paramJGraphFacade, arrayOfObject, true, false, true);
    }
    else
    {
      return null;
    }
    return paramJGraphHierarchyModel;
  }
  
  protected Object[] getOrderedVertices(JGraphFacade paramJGraphFacade)
  {
    Object[] arrayOfObject1 = paramJGraphFacade.getVertices().toArray();
    int i;
    if (this.roots == null)
    {
      this.sources = new ArrayList();
      for (i = 0; i < arrayOfObject1.length; i++)
      {
        int j = paramJGraphFacade.getIncomingEdges(arrayOfObject1[i], null, true, false).size();
        if (j == 0) {
          this.sources.add(arrayOfObject1[i]);
        }
      }
    }
    else
    {
      this.sources = new ArrayList(this.roots.length);
      for (i = 0; i < this.roots.length; i++) {
        this.sources.add(this.roots[i]);
      }
    }
    this.sinks = new ArrayList();
    Hashtable localHashtable = new Hashtable();
    ArrayList localArrayList = new ArrayList();
    for (int k = 0; k < arrayOfObject1.length; k++)
    {
      int m = paramJGraphFacade.getOutgoingEdges(arrayOfObject1[k], null, true, false).size();
      int n = paramJGraphFacade.getIncomingEdges(arrayOfObject1[k], null, true, false).size();
      if (m == 0)
      {
        this.sinks.add(arrayOfObject1[k]);
      }
      else
      {
        Integer localInteger = new Integer(m - n);
        Object localObject;
        if (localHashtable.containsKey(localInteger))
        {
          localObject = (List)localHashtable.get(localInteger);
          ((List)localObject).add(arrayOfObject1[k]);
        }
        else
        {
          localObject = new LinkedList();
          ((List)localObject).add(arrayOfObject1[k]);
          localHashtable.put(localInteger, localObject);
          localArrayList.add(localInteger);
        }
      }
    }
    Object[] arrayOfObject2 = this.sources.toArray();
    Object[] arrayOfObject3 = this.sinks.toArray();
    Object[] arrayOfObject4 = localArrayList.toArray();
    Arrays.sort(arrayOfObject4);
    System.arraycopy(arrayOfObject2, 0, arrayOfObject1, 0, arrayOfObject2.length);
    int i1 = arrayOfObject2.length;
    for (int i2 = arrayOfObject4.length - 1; i2 >= 0; i2--)
    {
      List localList = (List)localHashtable.get(arrayOfObject4[i2]);
      Object[] arrayOfObject5 = localList.toArray();
      System.arraycopy(arrayOfObject5, 0, arrayOfObject1, i1, arrayOfObject5.length);
      i1 += arrayOfObject5.length;
    }
    System.arraycopy(arrayOfObject3, 0, arrayOfObject1, i1, arrayOfObject3.length);
    return arrayOfObject1;
  }
}


/* Location:              /home/menick/Scrivania/ProcessMining/CNMining/CNMining.jar!/com/jgraph/layout/hierarchical/JGraphGreedyCycleRemover.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */