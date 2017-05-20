package com.jgraph.algebra.cost;

import java.awt.geom.Point2D;
import org.jgraph.graph.CellMapper;
import org.jgraph.graph.CellView;
import org.jgraph.graph.EdgeView;

public class JGraphDistanceCostFunction
  implements JGraphCostFunction
{
  protected CellMapper mapper = null;
  
  public JGraphDistanceCostFunction(CellMapper paramCellMapper)
  {
    this.mapper = paramCellMapper;
  }
  
  public double getCost(Object paramObject)
  {
    if (this.mapper != null)
    {
      CellView localCellView = this.mapper.getMapping(paramObject, false);
      return getLength(localCellView);
    }
    return 1.0D;
  }
  
  public static double getLength(CellView paramCellView)
  {
    double d = 1.0D;
    if ((paramCellView instanceof EdgeView))
    {
      EdgeView localEdgeView = (EdgeView)paramCellView;
      Object localObject = null;
      Point2D localPoint2D = null;
      for (int i = 0; i < localEdgeView.getPointCount(); i++)
      {
        localPoint2D = localEdgeView.getPoint(i);
        if (localObject != null) {
          d += ((Point2D)localObject).distance(localPoint2D);
        }
        localObject = localPoint2D;
      }
    }
    return d;
  }
}


/* Location:              /home/menick/Scrivania/ProcessMining/CNMining/CNMining.jar!/com/jgraph/algebra/cost/JGraphDistanceCostFunction.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */