package com.jgraph.layout.hierarchical.model;

import com.jgraph.layout.JGraphFacade;
import com.jgraph.layout.JGraphFacade.CellVisitor;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.Hashtable;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.LinkedHashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class JGraphHierarchyModel
{
  protected boolean scanRanksFromSinks = true;
  public int maxRank;
  protected Map vertexMapper = null;
  protected Map edgeMapper = null;
  public Map ranks = null;
  public Object[] roots = null;
  protected int dfsCount = 0;
  protected boolean deterministic = false;
  private final int SOURCESCANSTARTRANK = 100000000;
  
  public JGraphHierarchyModel(JGraphFacade paramJGraphFacade)
  {
    this(paramJGraphFacade, paramJGraphFacade.getVertices().toArray(), false, false, true);
  }
  
  public JGraphHierarchyModel(JGraphFacade paramJGraphFacade, Object[] paramArrayOfObject, boolean paramBoolean1, boolean paramBoolean2, boolean paramBoolean3)
  {
    this.deterministic = paramBoolean2;
    this.scanRanksFromSinks = paramBoolean3;
    this.roots = paramJGraphFacade.getRoots().toArray();
    if (paramBoolean1)
    {
      formOrderedHierarchy(paramJGraphFacade, paramArrayOfObject);
    }
    else
    {
      if (paramArrayOfObject == null) {
        paramArrayOfObject = paramJGraphFacade.getVertices().toArray();
      }
      this.vertexMapper = new Hashtable(paramArrayOfObject.length);
      this.edgeMapper = new Hashtable(paramArrayOfObject.length);
      if (paramBoolean3) {
        this.maxRank = 0;
      } else {
        this.maxRank = 100000000;
      }
      JGraphHierarchyNode[] arrayOfJGraphHierarchyNode = new JGraphHierarchyNode[paramArrayOfObject.length];
      createInternalCells(paramJGraphFacade, paramArrayOfObject, arrayOfJGraphHierarchyNode);
      for (int i = 0; i < paramArrayOfObject.length; i++)
      {
        Collection localCollection = arrayOfJGraphHierarchyNode[i].connectsAsSource;
        Iterator localIterator1 = localCollection.iterator();
        while (localIterator1.hasNext())
        {
          JGraphHierarchyEdge localJGraphHierarchyEdge = (JGraphHierarchyEdge)localIterator1.next();
          List localList = localJGraphHierarchyEdge.edges;
          Iterator localIterator2 = localList.iterator();
          if (localIterator2.hasNext())
          {
            Object localObject1 = localIterator2.next();
            Object localObject2 = paramJGraphFacade.getTarget(localObject1);
            JGraphHierarchyNode localJGraphHierarchyNode = (JGraphHierarchyNode)this.vertexMapper.get(localObject2);
            if ((localJGraphHierarchyNode != null) && (arrayOfJGraphHierarchyNode[i] != localJGraphHierarchyNode))
            {
              localJGraphHierarchyEdge.target = localJGraphHierarchyNode;
              if (localJGraphHierarchyNode.connectsAsTarget.size() == 0) {
                localJGraphHierarchyNode.connectsAsTarget = new LinkedHashSet(4);
              }
              localJGraphHierarchyNode.connectsAsTarget.add(localJGraphHierarchyEdge);
            }
          }
        }
        arrayOfJGraphHierarchyNode[i].temp[0] = 1;
      }
    }
  }
  
  public void formOrderedHierarchy(JGraphFacade paramJGraphFacade, Object[] paramArrayOfObject)
  {
    if (paramArrayOfObject == null) {
      paramArrayOfObject = paramJGraphFacade.getVertices().toArray();
    }
    this.vertexMapper = new Hashtable(paramArrayOfObject.length * 2);
    this.edgeMapper = new Hashtable(paramArrayOfObject.length);
    this.maxRank = 0;
    JGraphHierarchyNode[] arrayOfJGraphHierarchyNode = new JGraphHierarchyNode[paramArrayOfObject.length];
    createInternalCells(paramJGraphFacade, paramArrayOfObject, arrayOfJGraphHierarchyNode);
    ArrayList localArrayList = new ArrayList();
    for (int i = 0; i < paramArrayOfObject.length; i++)
    {
      Collection localCollection = arrayOfJGraphHierarchyNode[i].connectsAsSource;
      Iterator localIterator1 = localCollection.iterator();
      while (localIterator1.hasNext())
      {
        localObject1 = (JGraphHierarchyEdge)localIterator1.next();
        List localList = ((JGraphHierarchyEdge)localObject1).edges;
        Iterator localIterator2 = localList.iterator();
        if (localIterator2.hasNext())
        {
          Object localObject2 = localIterator2.next();
          Object localObject3 = paramJGraphFacade.getTarget(localObject2);
          JGraphHierarchyNode localJGraphHierarchyNode = (JGraphHierarchyNode)this.vertexMapper.get(localObject3);
          if ((localJGraphHierarchyNode != null) && (arrayOfJGraphHierarchyNode[i] != localJGraphHierarchyNode))
          {
            ((JGraphHierarchyEdge)localObject1).target = localJGraphHierarchyNode;
            if (localJGraphHierarchyNode.connectsAsTarget.size() == 0) {
              localJGraphHierarchyNode.connectsAsTarget = new ArrayList(4);
            }
            if (localJGraphHierarchyNode.temp[0] == 1)
            {
              ((JGraphHierarchyEdge)localObject1).invert();
              localJGraphHierarchyNode.connectsAsSource.add(localObject1);
              localArrayList.add(localObject1);
              arrayOfJGraphHierarchyNode[i].connectsAsTarget.add(localObject1);
            }
            else
            {
              localJGraphHierarchyNode.connectsAsTarget.add(localObject1);
            }
          }
        }
      }
      Object localObject1 = localArrayList.iterator();
      while (((Iterator)localObject1).hasNext()) {
        arrayOfJGraphHierarchyNode[i].connectsAsSource.remove(((Iterator)localObject1).next());
      }
      localArrayList.clear();
      arrayOfJGraphHierarchyNode[i].temp[0] = 1;
    }
  }
  
  protected void createInternalCells(JGraphFacade paramJGraphFacade, Object[] paramArrayOfObject, JGraphHierarchyNode[] paramArrayOfJGraphHierarchyNode)
  {
    for (int i = 0; i < paramArrayOfObject.length; i++)
    {
      paramArrayOfJGraphHierarchyNode[i] = new JGraphHierarchyNode(paramArrayOfObject[i]);
      this.vertexMapper.put(paramArrayOfObject[i], paramArrayOfJGraphHierarchyNode[i]);
      List localList = paramJGraphFacade.getNeighbours(paramArrayOfObject[i], null, this.deterministic, true);
      paramArrayOfJGraphHierarchyNode[i].connectsAsSource = new LinkedHashSet(localList.size());
      Iterator localIterator1 = localList.iterator();
      while (localIterator1.hasNext())
      {
        Object localObject = localIterator1.next();
        if ((localObject != paramArrayOfObject[i]) && (paramJGraphFacade.isVertex(localObject)))
        {
          Object[] arrayOfObject = paramJGraphFacade.getEdgesBetween(paramArrayOfObject[i], localObject, true);
          if ((arrayOfObject != null) && (arrayOfObject.length > 0))
          {
            ArrayList localArrayList = new ArrayList(arrayOfObject.length);
            for (int j = 0; j < arrayOfObject.length; j++) {
              localArrayList.add(arrayOfObject[j]);
            }
            JGraphHierarchyEdge localJGraphHierarchyEdge = new JGraphHierarchyEdge(localArrayList);
            Iterator localIterator2 = localArrayList.iterator();
            while (localIterator2.hasNext()) {
              this.edgeMapper.put(localIterator2.next(), localJGraphHierarchyEdge);
            }
            localJGraphHierarchyEdge.source = paramArrayOfJGraphHierarchyNode[i];
            paramArrayOfJGraphHierarchyNode[i].connectsAsSource.add(localJGraphHierarchyEdge);
          }
        }
      }
      paramArrayOfJGraphHierarchyNode[i].temp[0] = 0;
    }
  }
  
  public void initialRank()
  {
    Collection localCollection1 = this.vertexMapper.values();
    LinkedList localLinkedList = new LinkedList();
    if ((!this.scanRanksFromSinks) && (this.roots != null)) {
      for (int i = 0; i < this.roots.length; i++)
      {
        localObject1 = this.vertexMapper.get(this.roots[i]);
        if (localObject1 != null) {
          localLinkedList.add(localObject1);
        }
      }
    }
    if (this.scanRanksFromSinks)
    {
      localIterator1 = localCollection1.iterator();
      while (localIterator1.hasNext())
      {
        localObject1 = (JGraphHierarchyNode)localIterator1.next();
        if ((((JGraphHierarchyNode)localObject1).connectsAsSource == null) || (((JGraphHierarchyNode)localObject1).connectsAsSource.isEmpty())) {
          localLinkedList.add(localObject1);
        }
      }
    }
    if (localLinkedList.isEmpty())
    {
      localIterator1 = localCollection1.iterator();
      while (localIterator1.hasNext())
      {
        localObject1 = (JGraphHierarchyNode)localIterator1.next();
        if ((((JGraphHierarchyNode)localObject1).connectsAsTarget == null) || (((JGraphHierarchyNode)localObject1).connectsAsTarget.isEmpty())) {
          localLinkedList.add(localObject1);
        }
      }
    }
    Iterator localIterator1 = localCollection1.iterator();
    while (localIterator1.hasNext())
    {
      localObject1 = (JGraphHierarchyNode)localIterator1.next();
      ((JGraphHierarchyNode)localObject1).temp[0] = -1;
    }
    Object localObject1 = new ArrayList(localLinkedList);
    Object localObject2;
    Iterator localIterator2;
    Object localObject3;
    while (!localLinkedList.isEmpty())
    {
      JGraphHierarchyNode localJGraphHierarchyNode1 = (JGraphHierarchyNode)localLinkedList.getFirst();
      Collection localCollection2;
      if (this.scanRanksFromSinks)
      {
        localObject2 = localJGraphHierarchyNode1.connectsAsSource;
        localCollection2 = localJGraphHierarchyNode1.connectsAsTarget;
      }
      else
      {
        localObject2 = localJGraphHierarchyNode1.connectsAsTarget;
        localCollection2 = localJGraphHierarchyNode1.connectsAsSource;
      }
      int m = 1;
      localIterator2 = ((Collection)localObject2).iterator();
      int n = 0;
      if (!this.scanRanksFromSinks) {
        n = 100000000;
      }
      Object localObject4;
      while ((m != 0) && (localIterator2.hasNext()))
      {
        localObject3 = (JGraphHierarchyEdge)localIterator2.next();
        if (localObject3.temp[0] == 5270620)
        {
          if (this.scanRanksFromSinks) {
            localObject4 = ((JGraphHierarchyEdge)localObject3).target;
          } else {
            localObject4 = ((JGraphHierarchyEdge)localObject3).source;
          }
          if (this.scanRanksFromSinks) {
            n = Math.max(n, localObject4.temp[0] + 1);
          } else {
            n = Math.min(n, localObject4.temp[0] - 1);
          }
        }
        else
        {
          m = 0;
        }
      }
      if (m != 0)
      {
        localJGraphHierarchyNode1.temp[0] = n;
        if (this.scanRanksFromSinks) {
          this.maxRank = Math.max(this.maxRank, n);
        } else {
          this.maxRank = Math.min(this.maxRank, n);
        }
        if (localCollection2 != null)
        {
          localObject3 = localCollection2.iterator();
          while (((Iterator)localObject3).hasNext())
          {
            localObject4 = (JGraphHierarchyEdge)((Iterator)localObject3).next();
            ((JGraphHierarchyEdge)localObject4).temp[0] = 5270620;
            JGraphHierarchyNode localJGraphHierarchyNode3;
            if (this.scanRanksFromSinks) {
              localJGraphHierarchyNode3 = ((JGraphHierarchyEdge)localObject4).source;
            } else {
              localJGraphHierarchyNode3 = ((JGraphHierarchyEdge)localObject4).target;
            }
            if (localJGraphHierarchyNode3.temp[0] == -1)
            {
              localLinkedList.addLast(localJGraphHierarchyNode3);
              localJGraphHierarchyNode3.temp[0] = -2;
            }
          }
        }
        localLinkedList.removeFirst();
      }
      else
      {
        localObject3 = localLinkedList.removeFirst();
        localLinkedList.addLast(localJGraphHierarchyNode1);
        if ((localObject3 == localJGraphHierarchyNode1) && (localLinkedList.size() == 1)) {
          break;
        }
      }
    }
    if (this.scanRanksFromSinks)
    {
      for (int j = 0; j < ((List)localObject1).size(); j++)
      {
        localObject2 = (JGraphHierarchyNode)((List)localObject1).get(j);
        int k = 1000000;
        Collection localCollection3 = ((JGraphHierarchyNode)localObject2).connectsAsTarget;
        localIterator2 = localCollection3.iterator();
        while (localIterator2.hasNext())
        {
          JGraphHierarchyEdge localJGraphHierarchyEdge = (JGraphHierarchyEdge)localIterator2.next();
          localObject3 = localJGraphHierarchyEdge.source;
          ((JGraphHierarchyNode)localObject2).temp[0] = Math.min(k, localObject3.temp[0] - 1);
          k = localObject2.temp[0];
        }
      }
    }
    else
    {
      localIterator1 = localCollection1.iterator();
      while (localIterator1.hasNext())
      {
        JGraphHierarchyNode localJGraphHierarchyNode2 = (JGraphHierarchyNode)localIterator1.next();
        localJGraphHierarchyNode2.temp[0] -= this.maxRank;
      }
      this.maxRank = (100000000 - this.maxRank);
    }
  }
  
  public void fixRanks()
  {
    final JGraphHierarchyRank[] arrayOfJGraphHierarchyRank = new JGraphHierarchyRank[this.maxRank + 1];
    this.ranks = new LinkedHashMap(this.maxRank + 1);
    for (int i = 0; i < this.maxRank + 1; i++)
    {
      arrayOfJGraphHierarchyRank[i] = new JGraphHierarchyRank();
      this.ranks.put(new Integer(i), arrayOfJGraphHierarchyRank[i]);
    }
    Object[] arrayOfObject = null;
    if (this.roots != null)
    {
      arrayOfObject = new Object[this.roots.length];
      for (int j = 0; j < this.roots.length; j++)
      {
        Object localObject = this.roots[j];
        JGraphHierarchyNode localJGraphHierarchyNode = (JGraphHierarchyNode)this.vertexMapper.get(localObject);
        arrayOfObject[j] = localJGraphHierarchyNode;
      }
    }
    dfs(new JGraphFacade.CellVisitor()
    {
      public void visit(Object paramAnonymousObject1, Object paramAnonymousObject2, Object paramAnonymousObject3, int paramAnonymousInt1, int paramAnonymousInt2)
      {
        JGraphHierarchyNode localJGraphHierarchyNode = (JGraphHierarchyNode)paramAnonymousObject2;
        if ((paramAnonymousInt2 == 0) && (localJGraphHierarchyNode.maxRank < 0) && (localJGraphHierarchyNode.minRank < 0))
        {
          arrayOfJGraphHierarchyRank[localJGraphHierarchyNode.temp[0]].add(paramAnonymousObject2);
          localJGraphHierarchyNode.maxRank = localJGraphHierarchyNode.temp[0];
          localJGraphHierarchyNode.minRank = localJGraphHierarchyNode.temp[0];
          localJGraphHierarchyNode.temp[0] = (arrayOfJGraphHierarchyRank[localJGraphHierarchyNode.maxRank].size() - 1);
        }
        if ((paramAnonymousObject1 != null) && (paramAnonymousObject3 != null))
        {
          int i = ((JGraphHierarchyNode)paramAnonymousObject1).maxRank - localJGraphHierarchyNode.maxRank;
          if (i > 1)
          {
            JGraphHierarchyEdge localJGraphHierarchyEdge = (JGraphHierarchyEdge)paramAnonymousObject3;
            localJGraphHierarchyEdge.maxRank = ((JGraphHierarchyNode)paramAnonymousObject1).maxRank;
            localJGraphHierarchyEdge.minRank = ((JGraphHierarchyNode)paramAnonymousObject2).maxRank;
            localJGraphHierarchyEdge.temp = new int[i - 1];
            localJGraphHierarchyEdge.x = new double[i - 1];
            localJGraphHierarchyEdge.y = new double[i - 1];
            for (int j = localJGraphHierarchyEdge.minRank + 1; j < localJGraphHierarchyEdge.maxRank; j++)
            {
              arrayOfJGraphHierarchyRank[j].add(localJGraphHierarchyEdge);
              localJGraphHierarchyEdge.setGeneralPurposeVariable(j, arrayOfJGraphHierarchyRank[j].size() - 1);
            }
          }
        }
      }
    }, arrayOfObject, false, null);
  }
  
  public void dfs(JGraphFacade.CellVisitor paramCellVisitor, Object[] paramArrayOfObject, boolean paramBoolean, Set paramSet)
  {
    if (paramArrayOfObject != null)
    {
      for (int i = 0; i < paramArrayOfObject.length; i++)
      {
        JGraphHierarchyNode localJGraphHierarchyNode = (JGraphHierarchyNode)paramArrayOfObject[i];
        if (localJGraphHierarchyNode != null)
        {
          if (paramSet == null) {
            paramSet = new HashSet();
          }
          if (paramBoolean)
          {
            localJGraphHierarchyNode.hashCode = new int[2];
            localJGraphHierarchyNode.hashCode[0] = this.dfsCount;
            localJGraphHierarchyNode.hashCode[1] = i;
            dfs(null, localJGraphHierarchyNode, null, paramCellVisitor, paramSet, localJGraphHierarchyNode.hashCode, i, 0);
          }
          else
          {
            dfs(null, localJGraphHierarchyNode, null, paramCellVisitor, paramSet, 0);
          }
        }
      }
      this.dfsCount += 1;
    }
  }
  
  public void dfs(JGraphHierarchyNode paramJGraphHierarchyNode1, JGraphHierarchyNode paramJGraphHierarchyNode2, JGraphHierarchyEdge paramJGraphHierarchyEdge, JGraphFacade.CellVisitor paramCellVisitor, Set paramSet, int paramInt)
  {
    if (paramJGraphHierarchyNode2 != null) {
      if (!paramSet.contains(paramJGraphHierarchyNode2))
      {
        paramCellVisitor.visit(paramJGraphHierarchyNode1, paramJGraphHierarchyNode2, paramJGraphHierarchyEdge, paramInt, 0);
        paramSet.add(paramJGraphHierarchyNode2);
        Object[] arrayOfObject = paramJGraphHierarchyNode2.connectsAsSource.toArray();
        for (int i = 0; i < arrayOfObject.length; i++)
        {
          JGraphHierarchyEdge localJGraphHierarchyEdge = (JGraphHierarchyEdge)arrayOfObject[i];
          JGraphHierarchyNode localJGraphHierarchyNode = localJGraphHierarchyEdge.target;
          dfs(paramJGraphHierarchyNode2, localJGraphHierarchyNode, localJGraphHierarchyEdge, paramCellVisitor, paramSet, paramInt + 1);
        }
      }
      else
      {
        paramCellVisitor.visit(paramJGraphHierarchyNode1, paramJGraphHierarchyNode2, paramJGraphHierarchyEdge, paramInt, 1);
      }
    }
  }
  
  public void dfs(JGraphHierarchyNode paramJGraphHierarchyNode1, JGraphHierarchyNode paramJGraphHierarchyNode2, JGraphHierarchyEdge paramJGraphHierarchyEdge, JGraphFacade.CellVisitor paramCellVisitor, Set paramSet, int[] paramArrayOfInt, int paramInt1, int paramInt2)
  {
    if (paramJGraphHierarchyNode2 != null)
    {
      if ((paramJGraphHierarchyNode1 != null) && ((paramJGraphHierarchyNode2.hashCode == null) || (paramJGraphHierarchyNode2.hashCode[0] != paramJGraphHierarchyNode1.hashCode[0])))
      {
        int i = paramJGraphHierarchyNode1.hashCode.length + 1;
        paramJGraphHierarchyNode2.hashCode = new int[i];
        System.arraycopy(paramJGraphHierarchyNode1.hashCode, 0, paramJGraphHierarchyNode2.hashCode, 0, paramJGraphHierarchyNode1.hashCode.length);
        paramJGraphHierarchyNode2.hashCode[(i - 1)] = paramInt1;
      }
      if (!paramSet.contains(paramJGraphHierarchyNode2))
      {
        paramCellVisitor.visit(paramJGraphHierarchyNode1, paramJGraphHierarchyNode2, paramJGraphHierarchyEdge, paramInt2, 0);
        paramSet.add(paramJGraphHierarchyNode2);
        Object[] arrayOfObject = paramJGraphHierarchyNode2.connectsAsSource.toArray();
        for (int j = 0; j < arrayOfObject.length; j++)
        {
          JGraphHierarchyEdge localJGraphHierarchyEdge = (JGraphHierarchyEdge)arrayOfObject[j];
          JGraphHierarchyNode localJGraphHierarchyNode = localJGraphHierarchyEdge.target;
          dfs(paramJGraphHierarchyNode2, localJGraphHierarchyNode, localJGraphHierarchyEdge, paramCellVisitor, paramSet, paramJGraphHierarchyNode2.hashCode, j, paramInt2 + 1);
        }
      }
      else
      {
        paramCellVisitor.visit(paramJGraphHierarchyNode1, paramJGraphHierarchyNode2, paramJGraphHierarchyEdge, paramInt2, 1);
      }
    }
  }
  
  public Map getVertexMapping()
  {
    if (this.vertexMapper == null) {
      this.vertexMapper = new Hashtable();
    }
    return this.vertexMapper;
  }
  
  public void setVertexMapping(Map paramMap)
  {
    this.vertexMapper = paramMap;
  }
  
  public Map getEdgeMapper()
  {
    return this.edgeMapper;
  }
  
  public void setEdgeMapper(Map paramMap)
  {
    this.edgeMapper = paramMap;
  }
  
  public int getDfsCount()
  {
    return this.dfsCount;
  }
  
  public void setDfsCount(int paramInt)
  {
    this.dfsCount = paramInt;
  }
  
  public boolean isDeterministic()
  {
    return this.deterministic;
  }
  
  public void setDeterministic(boolean paramBoolean)
  {
    this.deterministic = paramBoolean;
  }
  
  public boolean isSinksAtLayerZero()
  {
    return this.scanRanksFromSinks;
  }
  
  public void setSinksAtLayerZero(boolean paramBoolean)
  {
    this.scanRanksFromSinks = paramBoolean;
  }
}


/* Location:              /home/menick/Scrivania/ProcessMining/CNMining/CNMining.jar!/com/jgraph/layout/hierarchical/model/JGraphHierarchyModel.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */