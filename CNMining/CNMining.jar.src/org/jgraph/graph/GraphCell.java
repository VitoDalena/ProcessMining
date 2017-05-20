package org.jgraph.graph;

import java.util.Map;

public abstract interface GraphCell
{
  public abstract AttributeMap getAttributes();
  
  /**
   * @deprecated
   */
  public abstract Map changeAttributes(Map paramMap);
  
  public abstract void setAttributes(AttributeMap paramAttributeMap);
}


/* Location:              /home/menick/Scrivania/ProcessMining/CNMining/CNMining.jar!/org/jgraph/graph/GraphCell.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */