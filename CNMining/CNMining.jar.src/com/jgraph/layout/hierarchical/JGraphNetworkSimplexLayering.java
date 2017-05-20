package com.jgraph.layout.hierarchical;

import com.jgraph.layout.JGraphFacade;
import com.jgraph.layout.hierarchical.model.JGraphHierarchyModel;
import java.util.Collection;

public class JGraphNetworkSimplexLayering
  implements JGraphHierarchicalLayoutStep
{
  public JGraphHierarchyModel run(JGraphFacade paramJGraphFacade, JGraphHierarchyModel paramJGraphHierarchyModel)
  {
    feasibleTree(paramJGraphFacade, paramJGraphHierarchyModel);
    normalize();
    balance();
    return paramJGraphHierarchyModel;
  }
  
  private void balance() {}
  
  private void normalize() {}
  
  private void feasibleTree(JGraphFacade paramJGraphFacade, JGraphHierarchyModel paramJGraphHierarchyModel)
  {
    if (paramJGraphHierarchyModel == null) {
      paramJGraphHierarchyModel = new JGraphHierarchyModel(paramJGraphFacade, paramJGraphFacade.getVertices().toArray(), false, false, true);
    }
  }
}


/* Location:              /home/menick/Scrivania/ProcessMining/CNMining/CNMining.jar!/com/jgraph/layout/hierarchical/JGraphNetworkSimplexLayering.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */