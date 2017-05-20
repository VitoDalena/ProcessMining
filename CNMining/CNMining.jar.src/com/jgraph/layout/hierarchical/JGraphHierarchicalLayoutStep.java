package com.jgraph.layout.hierarchical;

import com.jgraph.layout.JGraphFacade;
import com.jgraph.layout.hierarchical.model.JGraphHierarchyModel;

public abstract interface JGraphHierarchicalLayoutStep
{
  public abstract JGraphHierarchyModel run(JGraphFacade paramJGraphFacade, JGraphHierarchyModel paramJGraphHierarchyModel);
}


/* Location:              /home/menick/Scrivania/ProcessMining/CNMining/CNMining.jar!/com/jgraph/layout/hierarchical/JGraphHierarchicalLayoutStep.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */