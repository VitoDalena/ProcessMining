package com.jgraph.layout.hierarchical;

import com.jgraph.layout.JGraphFacade;
import com.jgraph.layout.hierarchical.model.JGraphHierarchyModel;

public class JGraphLongestPathLayering
  implements JGraphHierarchicalLayoutStep
{
  public JGraphHierarchyModel run(JGraphFacade paramJGraphFacade, JGraphHierarchyModel paramJGraphHierarchyModel)
  {
    if (paramJGraphHierarchyModel == null) {
      paramJGraphHierarchyModel = new JGraphHierarchyModel(paramJGraphFacade);
    }
    paramJGraphHierarchyModel.initialRank();
    paramJGraphHierarchyModel.fixRanks();
    return paramJGraphHierarchyModel;
  }
}


/* Location:              /home/menick/Scrivania/ProcessMining/CNMining/CNMining.jar!/com/jgraph/layout/hierarchical/JGraphLongestPathLayering.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */