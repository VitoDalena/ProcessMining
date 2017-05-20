package org.jgraph.graph;

import java.awt.geom.Point2D;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.MutableTreeNode;

public class DefaultGraphCell
  extends DefaultMutableTreeNode
  implements GraphCell, Cloneable
{
  protected AttributeMap attributes = null;
  
  public DefaultGraphCell()
  {
    this(null);
  }
  
  public DefaultGraphCell(Object paramObject)
  {
    this(paramObject, null);
  }
  
  public DefaultGraphCell(Object paramObject, AttributeMap paramAttributeMap)
  {
    this(paramObject, paramAttributeMap, null);
  }
  
  public DefaultGraphCell(Object paramObject, AttributeMap paramAttributeMap, MutableTreeNode[] paramArrayOfMutableTreeNode)
  {
    super(paramObject, true);
    setAttributes(paramAttributeMap);
    if (paramArrayOfMutableTreeNode != null) {
      for (int i = 0; i < paramArrayOfMutableTreeNode.length; i++) {
        add(paramArrayOfMutableTreeNode[i]);
      }
    }
  }
  
  public List getChildren()
  {
    if (this.children == null) {
      return Collections.EMPTY_LIST;
    }
    return this.children;
  }
  
  public AttributeMap getAttributes()
  {
    return this.attributes;
  }
  
  /**
   * @deprecated
   */
  public Map changeAttributes(Map paramMap)
  {
    return getAttributes().applyMap(paramMap);
  }
  
  public void setAttributes(AttributeMap paramAttributeMap)
  {
    if (paramAttributeMap == null) {
      paramAttributeMap = new AttributeMap();
    }
    this.attributes = paramAttributeMap;
  }
  
  public Object addPort()
  {
    return addPort(null);
  }
  
  public Object addPort(Point2D paramPoint2D)
  {
    return addPort(paramPoint2D, null);
  }
  
  public Object addPort(Point2D paramPoint2D, Object paramObject)
  {
    DefaultPort localDefaultPort = new DefaultPort(paramObject);
    if (paramPoint2D == null)
    {
      add(localDefaultPort);
    }
    else
    {
      GraphConstants.setOffset(localDefaultPort.getAttributes(), paramPoint2D);
      add(localDefaultPort);
    }
    return localDefaultPort;
  }
  
  public Object clone()
  {
    DefaultGraphCell localDefaultGraphCell = (DefaultGraphCell)super.clone();
    localDefaultGraphCell.attributes = ((AttributeMap)this.attributes.clone());
    return localDefaultGraphCell;
  }
}


/* Location:              /home/menick/Scrivania/ProcessMining/CNMining/CNMining.jar!/org/jgraph/graph/DefaultGraphCell.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */