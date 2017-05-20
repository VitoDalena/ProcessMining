package org.jgraph.graph;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.event.MouseEvent;
import java.awt.geom.Point2D;
import java.awt.geom.Rectangle2D;
import java.awt.geom.Rectangle2D.Double;
import java.util.ArrayList;
import org.jgraph.JGraph;
import org.jgraph.plaf.GraphUI;

public class BasicMarqueeHandler
{
  protected transient Cursor previousCursor = null;
  protected Rectangle2D marqueeBounds;
  protected Point2D startPoint;
  protected Point2D currentPoint;
  
  public boolean isForceMarqueeEvent(MouseEvent paramMouseEvent)
  {
    return paramMouseEvent.isAltDown();
  }
  
  public void mouseReleased(MouseEvent paramMouseEvent)
  {
    try
    {
      if ((paramMouseEvent != null) && (this.marqueeBounds != null))
      {
        if (!(paramMouseEvent.getSource() instanceof JGraph)) {
          throw new IllegalArgumentException("MarqueeHandler cannot handle event from unknown source: " + paramMouseEvent);
        }
        JGraph localJGraph = (JGraph)paramMouseEvent.getSource();
        Rectangle2D localRectangle2D = localJGraph.fromScreen((Rectangle2D)this.marqueeBounds.clone());
        handleMarqueeEvent(paramMouseEvent, localJGraph, localRectangle2D);
        localJGraph.setCursor(this.previousCursor);
        Rectangle localRectangle = new Rectangle((int)this.marqueeBounds.getX(), (int)this.marqueeBounds.getY(), (int)this.marqueeBounds.getWidth() + 1, (int)this.marqueeBounds.getHeight() + 1);
        localRectangle.width += 1;
        localRectangle.height += 1;
        localJGraph.repaint(localRectangle);
      }
    }
    finally
    {
      this.currentPoint = null;
      this.startPoint = null;
      this.marqueeBounds = null;
      this.previousCursor = null;
    }
  }
  
  public void handleMarqueeEvent(MouseEvent paramMouseEvent, JGraph paramJGraph, Rectangle2D paramRectangle2D)
  {
    CellView[] arrayOfCellView = paramJGraph.getGraphLayoutCache().getRoots(paramRectangle2D);
    ArrayList localArrayList = new ArrayList();
    for (int i = 0; i < arrayOfCellView.length; i++) {
      if (paramRectangle2D.contains(arrayOfCellView[i].getBounds())) {
        localArrayList.add(arrayOfCellView[i].getCell());
      }
    }
    Object[] arrayOfObject = localArrayList.toArray();
    paramJGraph.getUI().selectCellsForEvent(paramJGraph, arrayOfObject, paramMouseEvent);
  }
  
  public void mouseDragged(MouseEvent paramMouseEvent)
  {
    if (this.startPoint != null)
    {
      if (!(paramMouseEvent.getSource() instanceof JGraph)) {
        throw new IllegalArgumentException("MarqueeHandler cannot handle event from unknown source: " + paramMouseEvent);
      }
      JGraph localJGraph = (JGraph)paramMouseEvent.getSource();
      Graphics localGraphics = localJGraph.getGraphics();
      Color localColor1 = localJGraph.getBackground();
      Color localColor2 = localJGraph.getMarqueeColor();
      if (localJGraph.isXorEnabled())
      {
        localGraphics.setColor(localColor2);
        localGraphics.setXORMode(localColor1);
        overlay(localJGraph, localGraphics, true);
      }
      Rectangle2D localRectangle2D = (Rectangle2D)this.marqueeBounds.clone();
      processMouseDraggedEvent(paramMouseEvent);
      if (localJGraph.isXorEnabled())
      {
        localGraphics.setColor(localColor1);
        localGraphics.setXORMode(localColor2);
        overlay(localJGraph, localGraphics, false);
      }
      else
      {
        localRectangle2D.add(this.marqueeBounds);
        localJGraph.repaint((int)localRectangle2D.getX() - 1, (int)localRectangle2D.getY() - 1, (int)localRectangle2D.getWidth() + 2, (int)localRectangle2D.getHeight() + 2);
      }
    }
  }
  
  protected void processMouseDraggedEvent(MouseEvent paramMouseEvent)
  {
    if (this.startPoint != null)
    {
      this.currentPoint = paramMouseEvent.getPoint();
      this.marqueeBounds = new Rectangle2D.Double(this.startPoint.getX(), this.startPoint.getY(), 0.0D, 0.0D);
      this.marqueeBounds.add(this.currentPoint);
    }
  }
  
  public void paint(JGraph paramJGraph, Graphics paramGraphics)
  {
    if (this.marqueeBounds != null)
    {
      if (paramJGraph.isXorEnabled())
      {
        Color localColor1 = paramJGraph.getBackground();
        Color localColor2 = paramJGraph.getMarqueeColor();
        paramGraphics.setColor(localColor2);
        paramGraphics.setXORMode(localColor1);
      }
      overlay(paramJGraph, paramGraphics, false);
    }
  }
  
  public void overlay(JGraph paramJGraph, Graphics paramGraphics, boolean paramBoolean)
  {
    if (this.marqueeBounds != null)
    {
      if (!paramJGraph.isXorEnabled()) {
        paramGraphics.setColor(paramJGraph.getMarqueeColor());
      }
      paramGraphics.drawRect((int)this.marqueeBounds.getX(), (int)this.marqueeBounds.getY(), (int)this.marqueeBounds.getWidth(), (int)this.marqueeBounds.getHeight());
    }
  }
  
  public void mousePressed(MouseEvent paramMouseEvent)
  {
    this.startPoint = paramMouseEvent.getPoint();
    this.marqueeBounds = new Rectangle2D.Double(this.startPoint.getX(), this.startPoint.getY(), 0.0D, 0.0D);
    if (!(paramMouseEvent.getSource() instanceof JGraph)) {
      throw new IllegalArgumentException("MarqueeHandler cannot handle event from unknown source: " + paramMouseEvent);
    }
    JGraph localJGraph = (JGraph)paramMouseEvent.getSource();
    if (isMarqueeTriggerEvent(paramMouseEvent, localJGraph))
    {
      this.previousCursor = localJGraph.getCursor();
      localJGraph.setCursor(new Cursor(1));
    }
  }
  
  public boolean isMarqueeTriggerEvent(MouseEvent paramMouseEvent, JGraph paramJGraph)
  {
    return paramJGraph.isSelectionEnabled();
  }
  
  public void mouseMoved(MouseEvent paramMouseEvent) {}
  
  public Point2D getCurrentPoint()
  {
    return this.currentPoint;
  }
  
  public Rectangle2D getMarqueeBounds()
  {
    return this.marqueeBounds;
  }
  
  public Cursor getPreviousCursor()
  {
    return this.previousCursor;
  }
  
  public Point2D getStartPoint()
  {
    return this.startPoint;
  }
  
  public void setCurrentPoint(Point2D paramPoint2D)
  {
    this.currentPoint = paramPoint2D;
  }
  
  public void setMarqueeBounds(Rectangle2D paramRectangle2D)
  {
    this.marqueeBounds = paramRectangle2D;
  }
  
  public void setPreviousCursor(Cursor paramCursor)
  {
    this.previousCursor = paramCursor;
  }
  
  public void setStartPoint(Point2D paramPoint2D)
  {
    this.startPoint = paramPoint2D;
  }
  
  public static JGraph getGraphForEvent(MouseEvent paramMouseEvent)
  {
    if ((paramMouseEvent.getSource() instanceof JGraph)) {
      return (JGraph)paramMouseEvent.getSource();
    }
    return null;
  }
}


/* Location:              /home/menick/Scrivania/ProcessMining/CNMining/CNMining.jar!/org/jgraph/graph/BasicMarqueeHandler.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */