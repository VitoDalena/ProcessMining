package org.jgraph.graph;

import java.io.Serializable;

public class DefaultCellViewFactory
  implements CellViewFactory, Serializable
{
  public CellView createView(GraphModel paramGraphModel, Object paramObject)
  {
    Object localObject = null;
    if (paramGraphModel.isPort(paramObject)) {
      localObject = createPortView(paramObject);
    } else if (paramGraphModel.isEdge(paramObject)) {
      localObject = createEdgeView(paramObject);
    } else {
      localObject = createVertexView(paramObject);
    }
    return (CellView)localObject;
  }
  
  protected VertexView createVertexView(Object paramObject)
  {
    return new VertexView(paramObject);
  }
  
  protected EdgeView createEdgeView(Object paramObject)
  {
    if ((paramObject instanceof Edge)) {
      return createEdgeView((Edge)paramObject);
    }
    return new EdgeView(paramObject);
  }
  
  protected PortView createPortView(Object paramObject)
  {
    if ((paramObject instanceof Port)) {
      return createPortView((Port)paramObject);
    }
    return new PortView(paramObject);
  }
  
  /**
   * @deprecated
   */
  protected EdgeView createEdgeView(Edge paramEdge)
  {
    return new EdgeView(paramEdge);
  }
  
  /**
   * @deprecated
   */
  protected PortView createPortView(Port paramPort)
  {
    return new PortView(paramPort);
  }
}


/* Location:              /home/menick/Scrivania/ProcessMining/CNMining/CNMining.jar!/org/jgraph/graph/DefaultCellViewFactory.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */