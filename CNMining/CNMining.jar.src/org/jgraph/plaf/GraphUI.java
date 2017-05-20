package org.jgraph.plaf;

import java.awt.Graphics;
import java.awt.Point;
import java.awt.event.MouseEvent;
import java.awt.geom.Dimension2D;
import java.awt.geom.Rectangle2D;
import javax.swing.plaf.ComponentUI;
import org.jgraph.JGraph;
import org.jgraph.graph.CellHandle;
import org.jgraph.graph.CellView;

public abstract class GraphUI
  extends ComponentUI
{
  public abstract void paintCell(Graphics paramGraphics, CellView paramCellView, Rectangle2D paramRectangle2D, boolean paramBoolean);
  
  public abstract void paintPorts(Graphics paramGraphics, CellView[] paramArrayOfCellView);
  
  public abstract void selectCellsForEvent(JGraph paramJGraph, Object[] paramArrayOfObject, MouseEvent paramMouseEvent);
  
  public abstract Dimension2D getPreferredSize(JGraph paramJGraph, CellView paramCellView);
  
  public abstract CellHandle getHandle();
  
  public abstract boolean isEditing(JGraph paramJGraph);
  
  public abstract boolean stopEditing(JGraph paramJGraph);
  
  public abstract void cancelEditing(JGraph paramJGraph);
  
  public abstract void startEditingAtCell(JGraph paramJGraph, Object paramObject);
  
  public abstract Object getEditingCell(JGraph paramJGraph);
  
  public abstract void setInsertionLocation(Point paramPoint);
  
  public abstract Point getInsertionLocation();
  
  public abstract void updateHandle();
  
  public abstract int getDropAction();
}


/* Location:              /home/menick/Scrivania/ProcessMining/CNMining/CNMining.jar!/org/jgraph/plaf/GraphUI.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */