package org.jgraph.graph;

import java.awt.Component;
import java.awt.geom.Point2D;
import java.awt.geom.Rectangle2D;
import java.util.Map;
import org.jgraph.JGraph;

public abstract interface CellView
{
  public abstract Object getCell();
  
  public abstract void refresh(GraphLayoutCache paramGraphLayoutCache, CellMapper paramCellMapper, boolean paramBoolean);
  
  public abstract void update(GraphLayoutCache paramGraphLayoutCache);
  
  public abstract void childUpdated();
  
  public abstract CellView getParentView();
  
  public abstract CellView[] getChildViews();
  
  public abstract void removeFromParent();
  
  public abstract boolean isLeaf();
  
  public abstract Rectangle2D getBounds();
  
  public abstract boolean intersects(JGraph paramJGraph, Rectangle2D paramRectangle2D);
  
  public abstract Point2D getPerimeterPoint(EdgeView paramEdgeView, Point2D paramPoint2D1, Point2D paramPoint2D2);
  
  public abstract Map changeAttributes(GraphLayoutCache paramGraphLayoutCache, Map paramMap);
  
  public abstract AttributeMap getAttributes();
  
  public abstract AttributeMap getAllAttributes();
  
  public abstract Component getRendererComponent(JGraph paramJGraph, boolean paramBoolean1, boolean paramBoolean2, boolean paramBoolean3);
  
  public abstract CellHandle getHandle(GraphContext paramGraphContext);
  
  public abstract GraphCellEditor getEditor();
}


/* Location:              /home/menick/Scrivania/ProcessMining/CNMining/CNMining.jar!/org/jgraph/graph/CellView.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */