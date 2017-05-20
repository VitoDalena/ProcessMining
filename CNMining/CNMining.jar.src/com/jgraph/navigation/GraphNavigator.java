package com.jgraph.navigation;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.event.AdjustmentEvent;
import java.awt.event.AdjustmentListener;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.awt.event.ComponentListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.geom.Point2D.Double;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.lang.ref.WeakReference;
import javax.swing.BorderFactory;
import javax.swing.JPanel;
import javax.swing.JScrollBar;
import javax.swing.JScrollPane;
import javax.swing.JViewport;
import javax.swing.SwingUtilities;
import org.jgraph.JGraph;
import org.jgraph.event.GraphLayoutCacheEvent;
import org.jgraph.event.GraphLayoutCacheListener;
import org.jgraph.event.GraphModelEvent;
import org.jgraph.event.GraphModelListener;
import org.jgraph.graph.GraphLayoutCache;
import org.jgraph.graph.GraphModel;

public class GraphNavigator
  extends JPanel
  implements GraphLayoutCacheListener, GraphModelListener, PropertyChangeListener, AdjustmentListener
{
  protected static final Cursor CURSOR_DEFAULT = new Cursor(0);
  protected static final Cursor CURSOR_HAND = new Cursor(12);
  protected ComponentListener componentListener = new ComponentAdapter()
  {
    public void componentResized(ComponentEvent paramAnonymousComponentEvent)
    {
      GraphNavigator.this.updateScale();
    }
  };
  protected transient GraphLayoutCache initialLayoutCache;
  protected JGraph backingGraph;
  protected WeakReference currentGraph;
  protected NavigatorPane navigatorPane;
  protected double maximumScale = 0.5D;
  
  public GraphNavigator(JGraph paramJGraph)
  {
    super(new BorderLayout());
    setDoubleBuffered(true);
    setBackingGraph(paramJGraph);
    this.initialLayoutCache = paramJGraph.getGraphLayoutCache();
    paramJGraph.setOpaque(false);
    paramJGraph.setScale(this.maximumScale);
    this.navigatorPane = new NavigatorPane(paramJGraph);
    paramJGraph.addMouseListener(this.navigatorPane);
    paramJGraph.addMouseMotionListener(this.navigatorPane);
    setBorder(BorderFactory.createEmptyBorder(4, 4, 4, 4));
    add(this.navigatorPane, "Center");
    setFocusable(false);
    addComponentListener(this.componentListener);
  }
  
  public NavigatorPane getScrollPane()
  {
    return this.navigatorPane;
  }
  
  public double getMaximumScale()
  {
    return this.maximumScale;
  }
  
  public void setMaximumScale(double paramDouble)
  {
    this.maximumScale = paramDouble;
  }
  
  public JGraph getBackingGraph()
  {
    return this.backingGraph;
  }
  
  public void setBackingGraph(JGraph paramJGraph)
  {
    this.backingGraph = paramJGraph;
  }
  
  public JGraph getCurrentGraph()
  {
    return (JGraph)(this.currentGraph != null ? this.currentGraph.get() : null);
  }
  
  public void setCurrentGraph(JGraph paramJGraph)
  {
    if (((paramJGraph == null) || (getParentGraph(paramJGraph) == null)) && (paramJGraph != null))
    {
      JGraph localJGraph = getCurrentGraph();
      JScrollPane localJScrollPane;
      if ((localJGraph != null) && (paramJGraph != localJGraph))
      {
        localJGraph.getModel().removeGraphModelListener(this);
        localJGraph.getGraphLayoutCache().removeGraphLayoutCacheListener(this);
        localJGraph.removePropertyChangeListener(this);
        localJScrollPane = getParentScrollPane(localJGraph);
        if (localJScrollPane != null)
        {
          localJScrollPane.removeComponentListener(this.componentListener);
          localJScrollPane.getVerticalScrollBar().removeAdjustmentListener(this);
          localJScrollPane.getHorizontalScrollBar().removeAdjustmentListener(this);
          localJScrollPane.removePropertyChangeListener(this);
        }
        this.backingGraph.setGraphLayoutCache(this.initialLayoutCache);
      }
      this.currentGraph = new WeakReference(paramJGraph);
      if (paramJGraph != null)
      {
        paramJGraph.getModel().addGraphModelListener(this);
        paramJGraph.getGraphLayoutCache().addGraphLayoutCacheListener(this);
        paramJGraph.addPropertyChangeListener(this);
        localJScrollPane = getParentScrollPane(paramJGraph);
        if (localJScrollPane != null)
        {
          localJScrollPane.addComponentListener(this.componentListener);
          localJScrollPane.getVerticalScrollBar().addAdjustmentListener(this);
          localJScrollPane.getHorizontalScrollBar().addAdjustmentListener(this);
          localJScrollPane.addPropertyChangeListener(this);
        }
        this.backingGraph.setGraphLayoutCache(paramJGraph.getGraphLayoutCache());
      }
      updateScale();
    }
  }
  
  protected void updateScale()
  {
    SwingUtilities.invokeLater(new Runnable()
    {
      public void run()
      {
        JGraph localJGraph = GraphNavigator.this.getCurrentGraph();
        if (localJGraph != null)
        {
          Dimension localDimension1 = localJGraph.getPreferredSize();
          Dimension localDimension2 = localJGraph.getBounds().getSize();
          localDimension1.width = Math.max(localDimension1.width, localDimension2.width);
          localDimension2.height = Math.max(localDimension1.height, localDimension2.height);
          double d1 = localJGraph.getScale();
          localDimension1.setSize(localDimension1.width * 1 / d1, localDimension1.height * 1 / d1);
          Dimension localDimension3 = GraphNavigator.this.getScrollPane().getViewport().getSize();
          double d2 = localDimension3.getWidth() / localDimension1.getWidth();
          double d3 = localDimension3.getHeight() / localDimension1.getHeight();
          d1 = Math.min(Math.min(d2, d3), GraphNavigator.this.getMaximumScale());
          GraphNavigator.this.getBackingGraph().setScale(d1);
          GraphNavigator.this.repaint();
        }
      }
    });
  }
  
  public void graphLayoutCacheChanged(GraphLayoutCacheEvent paramGraphLayoutCacheEvent)
  {
    updateScale();
  }
  
  public void graphChanged(GraphModelEvent paramGraphModelEvent)
  {
    updateScale();
  }
  
  public void propertyChange(PropertyChangeEvent paramPropertyChangeEvent)
  {
    updateScale();
  }
  
  public void adjustmentValueChanged(AdjustmentEvent paramAdjustmentEvent)
  {
    this.navigatorPane.repaint();
  }
  
  public static JScrollPane getParentScrollPane(Component paramComponent)
  {
    while (paramComponent != null)
    {
      if ((paramComponent instanceof JScrollPane)) {
        return (JScrollPane)paramComponent;
      }
      paramComponent = paramComponent.getParent();
    }
    return null;
  }
  
  public static JGraph getParentGraph(Component paramComponent)
  {
    do
    {
      paramComponent = paramComponent.getParent();
      if ((paramComponent instanceof JGraph)) {
        return (JGraph)paramComponent;
      }
    } while (paramComponent != null);
    return null;
  }
  
  public static GraphNavigator createInstance(JGraph paramJGraph)
  {
    paramJGraph.setEnabled(false);
    paramJGraph.setFocusable(false);
    paramJGraph.setAntiAliased(false);
    return new GraphNavigator(paramJGraph);
  }
  
  public class NavigatorPane
    extends JScrollPane
    implements MouseListener, MouseMotionListener
  {
    protected Rectangle currentViewport = new Rectangle();
    protected Point lastPoint = null;
    
    public NavigatorPane(JGraph paramJGraph)
    {
      super();
      setOpaque(false);
      getViewport().setOpaque(false);
    }
    
    public void paint(Graphics paramGraphics)
    {
      JGraph localJGraph = GraphNavigator.this.getCurrentGraph();
      JScrollPane localJScrollPane = GraphNavigator.getParentScrollPane(localJGraph);
      paramGraphics.setColor(Color.lightGray);
      paramGraphics.fillRect(0, 0, getWidth(), getHeight());
      if ((localJScrollPane != null) && (localJGraph != null))
      {
        JViewport localJViewport = localJScrollPane.getViewport();
        Rectangle localRectangle = localJViewport.getViewRect();
        double d = GraphNavigator.this.backingGraph.getScale() / localJGraph.getScale();
        Dimension localDimension = localJGraph.getSize();
        paramGraphics.setColor(getBackground());
        paramGraphics.fillRect(0, 0, (int)(localDimension.width * d), (int)(localDimension.height * d));
        paramGraphics.setColor(Color.WHITE);
        this.currentViewport.setFrame((int)(localRectangle.getX() * d), (int)(localRectangle.getY() * d), (int)(localRectangle.getWidth() * d), (int)(localRectangle.getHeight() * d));
        paramGraphics.fillRect(this.currentViewport.x, this.currentViewport.y, this.currentViewport.width, this.currentViewport.height);
        super.paint(paramGraphics);
        paramGraphics.setColor(Color.RED);
        paramGraphics.drawRect(this.currentViewport.x, this.currentViewport.y, this.currentViewport.width, this.currentViewport.height);
      }
    }
    
    public void mouseClicked(MouseEvent paramMouseEvent) {}
    
    public void mousePressed(MouseEvent paramMouseEvent)
    {
      if (this.currentViewport.contains(paramMouseEvent.getX(), paramMouseEvent.getY())) {
        this.lastPoint = paramMouseEvent.getPoint();
      }
    }
    
    public void mouseReleased(MouseEvent paramMouseEvent)
    {
      this.lastPoint = null;
    }
    
    public void mouseEntered(MouseEvent paramMouseEvent) {}
    
    public void mouseExited(MouseEvent paramMouseEvent) {}
    
    public void mouseDragged(MouseEvent paramMouseEvent)
    {
      if (this.lastPoint != null)
      {
        JGraph localJGraph = GraphNavigator.this.getCurrentGraph();
        JScrollPane localJScrollPane = GraphNavigator.getParentScrollPane(localJGraph);
        if ((localJScrollPane != null) && (GraphNavigator.this.currentGraph != null))
        {
          JViewport localJViewport = localJScrollPane.getViewport();
          Rectangle localRectangle = localJViewport.getViewRect();
          double d1 = GraphNavigator.this.backingGraph.getScale() / localJGraph.getScale();
          double d2 = (paramMouseEvent.getX() - this.lastPoint.getX()) / d1;
          double d3 = (paramMouseEvent.getY() - this.lastPoint.getY()) / d1;
          this.lastPoint = paramMouseEvent.getPoint();
          d2 = localRectangle.getX() + (d2 > 0.0D ? localRectangle.getWidth() : 0.0D) + d2;
          d3 = localRectangle.getY() + (d3 > 0.0D ? localRectangle.getHeight() : 0.0D) + d3;
          Point2D.Double localDouble = new Point2D.Double(d2, d3);
          localJGraph.scrollPointToVisible(localDouble);
          GraphNavigator.this.navigatorPane.repaint();
        }
      }
    }
    
    public void mouseMoved(MouseEvent paramMouseEvent)
    {
      if (this.currentViewport.contains(paramMouseEvent.getPoint())) {
        setCursor(GraphNavigator.CURSOR_HAND);
      } else {
        setCursor(GraphNavigator.CURSOR_DEFAULT);
      }
    }
  }
}


/* Location:              /home/menick/Scrivania/ProcessMining/CNMining/CNMining.jar!/com/jgraph/navigation/GraphNavigator.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */