package com.jgraph.layout.hierarchical;

import com.jgraph.layout.JGraphFacade;
import com.jgraph.layout.JGraphFacade.CellVisitor;
import com.jgraph.layout.hierarchical.model.JGraphHierarchyEdge;
import com.jgraph.layout.hierarchical.model.JGraphHierarchyModel;
import com.jgraph.layout.hierarchical.model.JGraphHierarchyNode;
import java.util.Collection;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class JGraphMinimumCycleRemover
  implements JGraphHierarchicalLayoutStep
{
  public JGraphHierarchyModel run(JGraphFacade paramJGraphFacade, JGraphHierarchyModel paramJGraphHierarchyModel)
  {
    if (paramJGraphHierarchyModel == null) {
      paramJGraphHierarchyModel = new JGraphHierarchyModel(paramJGraphFacade);
    }
    final HashSet localHashSet1 = new HashSet();
    final HashSet localHashSet2 = new HashSet(paramJGraphHierarchyModel.getVertexMapping().values());
    Object[] arrayOfObject = null;
    Object localObject2;
    if (paramJGraphHierarchyModel.roots != null)
    {
      arrayOfObject = new Object[paramJGraphHierarchyModel.roots.length];
      for (int i = 0; i < paramJGraphHierarchyModel.roots.length; i++)
      {
        localObject1 = paramJGraphHierarchyModel.roots[i];
        localObject2 = (JGraphHierarchyNode)paramJGraphHierarchyModel.getVertexMapping().get(localObject1);
        arrayOfObject[i] = localObject2;
      }
    }
    paramJGraphHierarchyModel.dfs(new JGraphFacade.CellVisitor()
    {
      public void visit(Object paramAnonymousObject1, Object paramAnonymousObject2, Object paramAnonymousObject3, int paramAnonymousInt1, int paramAnonymousInt2)
      {
        if (((JGraphHierarchyNode)paramAnonymousObject2).isAncestor((JGraphHierarchyNode)paramAnonymousObject1))
        {
          ((JGraphHierarchyEdge)paramAnonymousObject3).invert();
          ((JGraphHierarchyNode)paramAnonymousObject1).connectsAsSource.remove(paramAnonymousObject3);
          ((JGraphHierarchyNode)paramAnonymousObject1).connectsAsTarget.add(paramAnonymousObject3);
          ((JGraphHierarchyNode)paramAnonymousObject2).connectsAsTarget.remove(paramAnonymousObject3);
          ((JGraphHierarchyNode)paramAnonymousObject2).connectsAsSource.add(paramAnonymousObject3);
        }
        localHashSet1.add(paramAnonymousObject2);
        localHashSet2.remove(paramAnonymousObject2);
      }
    }, arrayOfObject, true, null);
    HashSet localHashSet3 = null;
    if (localHashSet2.size() > 0) {
      localHashSet3 = new HashSet(localHashSet2);
    }
    Object localObject1 = new HashSet(localHashSet1);
    paramJGraphHierarchyModel.dfs(new JGraphFacade.CellVisitor()
    {
      public void visit(Object paramAnonymousObject1, Object paramAnonymousObject2, Object paramAnonymousObject3, int paramAnonymousInt1, int paramAnonymousInt2)
      {
        if (((JGraphHierarchyNode)paramAnonymousObject2).isAncestor((JGraphHierarchyNode)paramAnonymousObject1))
        {
          ((JGraphHierarchyEdge)paramAnonymousObject3).invert();
          ((JGraphHierarchyNode)paramAnonymousObject1).connectsAsSource.remove(paramAnonymousObject3);
          ((JGraphHierarchyNode)paramAnonymousObject1).connectsAsTarget.add(paramAnonymousObject3);
          ((JGraphHierarchyNode)paramAnonymousObject2).connectsAsTarget.remove(paramAnonymousObject3);
          ((JGraphHierarchyNode)paramAnonymousObject2).connectsAsSource.add(paramAnonymousObject3);
        }
        localHashSet1.add(paramAnonymousObject2);
        localHashSet2.remove(paramAnonymousObject2);
      }
    }, localHashSet2.toArray(), true, (Set)localObject1);
    if ((localHashSet3 != null) && (localHashSet3.size() > 0))
    {
      localObject2 = localHashSet3.iterator();
      List localList = paramJGraphFacade.getRoots();
      while (((Iterator)localObject2).hasNext())
      {
        JGraphHierarchyNode localJGraphHierarchyNode = (JGraphHierarchyNode)((Iterator)localObject2).next();
        Object localObject3 = localJGraphHierarchyNode.cell;
        int j = paramJGraphFacade.getIncomingEdges(localObject3, null, true, false).size();
        if (j == 0) {
          localList.add(localObject3);
        }
      }
    }
    return paramJGraphHierarchyModel;
  }
}


/* Location:              /home/menick/Scrivania/ProcessMining/CNMining/CNMining.jar!/com/jgraph/layout/hierarchical/JGraphMinimumCycleRemover.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */