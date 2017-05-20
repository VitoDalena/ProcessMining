package com.jgraph.algebra;

import com.jgraph.algebra.cost.JGraphCostFunction;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.Hashtable;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import org.jgraph.graph.DefaultGraphModel;
import org.jgraph.graph.GraphModel;

public class JGraphAlgebra
{
  protected static JGraphAlgebra sharedInstance = new JGraphAlgebra();
  
  public static JGraphAlgebra getSharedInstance()
  {
    return sharedInstance;
  }
  
  public static void setSharedInstance(JGraphAlgebra paramJGraphAlgebra)
  {
    sharedInstance = paramJGraphAlgebra;
  }
  
  public Object[] getShortestPath(GraphModel paramGraphModel, Object paramObject1, Object paramObject2, JGraphCostFunction paramJGraphCostFunction, int paramInt, boolean paramBoolean)
  {
    JGraphFibonacciHeap localJGraphFibonacciHeap = createPriorityQueue();
    Hashtable localHashtable = new Hashtable();
    localJGraphFibonacciHeap.decreaseKey(localJGraphFibonacciHeap.getNode(paramObject1, true), 0.0D);
    for (int i = 0; i < paramInt; i++)
    {
      localObject1 = localJGraphFibonacciHeap.removeMin();
      double d1 = ((JGraphFibonacciHeap.Node)localObject1).getKey();
      Object localObject3 = ((JGraphFibonacciHeap.Node)localObject1).getUserObject();
      if (localObject3 == paramObject2) {
        break;
      }
      Object[] arrayOfObject = paramBoolean ? DefaultGraphModel.getOutgoingEdges(paramGraphModel, localObject3) : DefaultGraphModel.getEdges(paramGraphModel, new Object[] { localObject3 }).toArray();
      if (arrayOfObject != null) {
        for (int j = 0; j < arrayOfObject.length; j++)
        {
          Object localObject4 = DefaultGraphModel.getOpposite(paramGraphModel, arrayOfObject[j], localObject3);
          if ((localObject4 != null) && (localObject4 != localObject3) && (localObject4 != paramObject1))
          {
            double d2 = d1 + (paramJGraphCostFunction != null ? paramJGraphCostFunction.getCost(arrayOfObject[j]) : 1.0D);
            localObject1 = localJGraphFibonacciHeap.getNode(localObject4, true);
            double d3 = ((JGraphFibonacciHeap.Node)localObject1).getKey();
            if (d2 < d3)
            {
              localHashtable.put(localObject4, arrayOfObject[j]);
              localJGraphFibonacciHeap.decreaseKey((JGraphFibonacciHeap.Node)localObject1, d2);
            }
          }
        }
      }
      if (localJGraphFibonacciHeap.isEmpty()) {
        break;
      }
    }
    ArrayList localArrayList = new ArrayList(paramInt);
    Object localObject1 = paramObject2;
    for (Object localObject2 = localHashtable.get(localObject1); localObject2 != null; localObject2 = localHashtable.get(localObject1))
    {
      localArrayList.add(0, localObject2);
      localObject1 = DefaultGraphModel.getOpposite(paramGraphModel, localObject2, localObject1);
    }
    return localArrayList.toArray();
  }
  
  public Object[] getMinimumSpanningTree(GraphModel paramGraphModel, Object[] paramArrayOfObject, JGraphCostFunction paramJGraphCostFunction, boolean paramBoolean)
  {
    ArrayList localArrayList = new ArrayList(paramArrayOfObject.length);
    JGraphFibonacciHeap localJGraphFibonacciHeap = createPriorityQueue();
    Hashtable localHashtable = new Hashtable();
    Object localObject1 = paramArrayOfObject[0];
    localJGraphFibonacciHeap.decreaseKey(localJGraphFibonacciHeap.getNode(localObject1, true), 0.0D);
    for (int i = 1; i < paramArrayOfObject.length; i++) {
      localJGraphFibonacciHeap.getNode(paramArrayOfObject[i], true);
    }
    while (!localJGraphFibonacciHeap.isEmpty())
    {
      JGraphFibonacciHeap.Node localNode = localJGraphFibonacciHeap.removeMin();
      localObject1 = localNode.getUserObject();
      Object localObject2 = localHashtable.get(localObject1);
      if (localObject2 != null) {
        localArrayList.add(localObject2);
      }
      Object[] arrayOfObject = paramBoolean ? DefaultGraphModel.getOutgoingEdges(paramGraphModel, localObject1) : DefaultGraphModel.getEdges(paramGraphModel, new Object[] { localObject1 }).toArray();
      if (arrayOfObject != null) {
        for (int j = 0; j < arrayOfObject.length; j++)
        {
          Object localObject3 = DefaultGraphModel.getOpposite(paramGraphModel, arrayOfObject[j], localObject1);
          if ((localObject3 != null) && (localObject3 != localObject1))
          {
            localNode = localJGraphFibonacciHeap.getNode(localObject3, false);
            if (localNode != null)
            {
              double d1 = paramJGraphCostFunction.getCost(arrayOfObject[j]);
              double d2 = localNode.getKey();
              if (d1 < d2)
              {
                localHashtable.put(localObject3, arrayOfObject[j]);
                localJGraphFibonacciHeap.decreaseKey(localNode, d1);
              }
            }
          }
        }
      }
    }
    return localArrayList.toArray();
  }
  
  public Object[] getMinimumSpanningTree(GraphModel paramGraphModel, Object[] paramArrayOfObject1, Object[] paramArrayOfObject2, JGraphCostFunction paramJGraphCostFunction)
  {
    JGraphUnionFind localJGraphUnionFind = createUnionFind(paramArrayOfObject1);
    Iterator localIterator = sort(paramArrayOfObject2, paramJGraphCostFunction).iterator();
    ArrayList localArrayList = new ArrayList(paramArrayOfObject2.length);
    while (localIterator.hasNext())
    {
      Object localObject1 = localIterator.next();
      Object localObject2 = DefaultGraphModel.getSourceVertex(paramGraphModel, localObject1);
      Object localObject3 = DefaultGraphModel.getTargetVertex(paramGraphModel, localObject1);
      JGraphUnionFind.Node localNode1 = localJGraphUnionFind.find(localJGraphUnionFind.getNode(localObject2));
      JGraphUnionFind.Node localNode2 = localJGraphUnionFind.find(localJGraphUnionFind.getNode(localObject3));
      if ((localNode1 == null) || (localNode2 == null) || (localNode1 != localNode2))
      {
        localJGraphUnionFind.union(localNode1, localNode2);
        localArrayList.add(localObject1);
      }
    }
    return localArrayList.toArray();
  }
  
  public JGraphUnionFind getConnectionComponents(GraphModel paramGraphModel, Object[] paramArrayOfObject1, Object[] paramArrayOfObject2)
  {
    JGraphUnionFind localJGraphUnionFind = createUnionFind(paramArrayOfObject1);
    for (int i = 0; i < paramArrayOfObject2.length; i++)
    {
      Object localObject1 = DefaultGraphModel.getSourceVertex(paramGraphModel, paramArrayOfObject2[i]);
      Object localObject2 = DefaultGraphModel.getTargetVertex(paramGraphModel, paramArrayOfObject2[i]);
      localJGraphUnionFind.union(localJGraphUnionFind.find(localJGraphUnionFind.getNode(localObject1)), localJGraphUnionFind.find(localJGraphUnionFind.getNode(localObject2)));
    }
    return localJGraphUnionFind;
  }
  
  public List sort(Object[] paramArrayOfObject, final JGraphCostFunction paramJGraphCostFunction)
  {
    List localList = Arrays.asList(paramArrayOfObject);
    Collections.sort(localList, new Comparator()
    {
      public int compare(Object paramAnonymousObject1, Object paramAnonymousObject2)
      {
        Double localDouble1 = new Double(paramJGraphCostFunction.getCost(paramAnonymousObject1));
        Double localDouble2 = new Double(paramJGraphCostFunction.getCost(paramAnonymousObject2));
        return localDouble1.compareTo(localDouble2);
      }
    });
    return localList;
  }
  
  public double sum(Object[] paramArrayOfObject, JGraphCostFunction paramJGraphCostFunction)
  {
    double d = 0.0D;
    for (int i = 0; i < paramArrayOfObject.length; i++) {
      d += paramJGraphCostFunction.getCost(paramArrayOfObject[i]);
    }
    return d;
  }
  
  protected JGraphUnionFind createUnionFind(Object[] paramArrayOfObject)
  {
    return new JGraphUnionFind(paramArrayOfObject);
  }
  
  protected JGraphFibonacciHeap createPriorityQueue()
  {
    return new JGraphFibonacciHeap();
  }
}


/* Location:              /home/menick/Scrivania/ProcessMining/CNMining/CNMining.jar!/com/jgraph/algebra/JGraphAlgebra.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */