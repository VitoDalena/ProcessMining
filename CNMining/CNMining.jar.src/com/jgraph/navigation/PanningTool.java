package com.jgraph.navigation;

import java.awt.Component;
import java.awt.Container;
import java.awt.Cursor;
import java.awt.Point;
import java.awt.event.MouseEvent;
import java.awt.geom.Point2D;
import javax.swing.JPanel;
import javax.swing.JViewport;
import org.jgraph.JGraph;
import org.jgraph.graph.BasicMarqueeHandler;

public class PanningTool
  extends BasicMarqueeHandler
{
  protected int m_XDifference;
  protected int m_YDifference;
  protected int dx;
  protected int dy;
  protected Cursor panningCursor;
  
  public boolean isForceMarqueeEvent(MouseEvent paramMouseEvent)
  {
    return true;
  }
  
  public void mousePressed(MouseEvent paramMouseEvent)
  {
    JGraph localJGraph = getGraphForEvent(paramMouseEvent);
    this.startPoint = paramMouseEvent.getPoint();
    this.previousCursor = localJGraph.getCursor();
    localJGraph.setCursor(this.panningCursor);
    this.marqueeBounds = null;
    this.m_XDifference = paramMouseEvent.getX();
    this.m_YDifference = paramMouseEvent.getY();
    this.dx = 0;
    this.dy = 0;
    paramMouseEvent.consume();
  }
  
  protected void processMouseDraggedEvent(MouseEvent paramMouseEvent)
  {
    JGraph localJGraph = getGraphForEvent(paramMouseEvent);
    Container localContainer = localJGraph.getParent();
    if ((localContainer instanceof JPanel)) {
      localContainer = localContainer.getParent();
    }
    if ((localContainer instanceof JViewport))
    {
      JViewport localJViewport = (JViewport)localContainer;
      Point localPoint = localJViewport.getViewPosition();
      int i = localPoint.x - (paramMouseEvent.getX() - this.m_XDifference);
      int j = localPoint.y - (paramMouseEvent.getY() - this.m_YDifference);
      this.dx = ((int)(this.dx + (paramMouseEvent.getX() - this.startPoint.getX())));
      this.dy += paramMouseEvent.getY() - this.m_YDifference;
      int k = localJGraph.getWidth() - localJViewport.getWidth();
      int m = localJGraph.getHeight() - localJViewport.getHeight();
      if (i < 0) {
        i = 0;
      }
      if (i > k) {
        i = k;
      }
      if (j < 0) {
        j = 0;
      }
      if (j > m) {
        j = m;
      }
      localJViewport.setViewPosition(new Point(i, j));
      paramMouseEvent.consume();
    }
  }
  
  public void mouseDragged(MouseEvent paramMouseEvent)
  {
    processMouseDraggedEvent(paramMouseEvent);
  }
  
  public void mouseReleased(MouseEvent paramMouseEvent)
  {
    JGraph localJGraph = getGraphForEvent(paramMouseEvent);
    if (this.previousCursor != null) {
      localJGraph.setCursor(this.previousCursor);
    }
    paramMouseEvent.consume();
  }
  
  public Cursor getPanningCursor()
  {
    return this.panningCursor;
  }
  
  public void setPanningCursor(Cursor paramCursor)
  {
    this.panningCursor = paramCursor;
  }
}


/* Location:              /home/menick/Scrivania/ProcessMining/CNMining/CNMining.jar!/com/jgraph/navigation/PanningTool.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */