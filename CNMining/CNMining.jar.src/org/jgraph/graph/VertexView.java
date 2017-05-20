package org.jgraph.graph;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.event.MouseEvent;
import java.awt.geom.AffineTransform;
import java.awt.geom.Point2D;
import java.awt.geom.Rectangle2D;
import java.awt.geom.Rectangle2D.Double;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import org.jgraph.JGraph;
import org.jgraph.plaf.GraphUI;
import org.jgraph.plaf.basic.BasicGraphUI;

public class VertexView
  extends AbstractCellView
{
  public static transient VertexRenderer renderer;
  public static final Rectangle2D defaultBounds = new Rectangle2D.Double(10.0D, 10.0D, 20.0D, 20.0D);
  protected Rectangle2D bounds;
  public static transient int[] defaultCursors = { 6, 8, 7, 10, 11, 4, 9, 5 };
  public static transient int[] xCursors = { 10, 0, 11, 10, 11, 10, 0, 11 };
  public static transient int[] yCursors = { 8, 8, 8, 0, 0, 9, 9, 9 };
  
  public VertexView() {}
  
  public VertexView(Object paramObject)
  {
    super(paramObject);
  }
  
  public void update(GraphLayoutCache paramGraphLayoutCache)
  {
    super.update(paramGraphLayoutCache);
    this.bounds = GraphConstants.getBounds(this.allAttributes);
    if (this.bounds == null)
    {
      this.bounds = this.allAttributes.createRect(defaultBounds);
      GraphConstants.setBounds(this.allAttributes, this.bounds);
    }
    this.groupBounds = null;
  }
  
  public Rectangle2D getCachedBounds()
  {
    return this.bounds;
  }
  
  public void setCachedBounds(Rectangle2D paramRectangle2D)
  {
    this.bounds = paramRectangle2D;
  }
  
  public CellViewRenderer getRenderer()
  {
    return renderer;
  }
  
  public CellHandle getHandle(GraphContext paramGraphContext)
  {
    if ((GraphConstants.isSizeable(getAllAttributes())) && (!GraphConstants.isAutoSize(getAllAttributes())) && (paramGraphContext.getGraph().isSizeable())) {
      return new SizeHandle(this, paramGraphContext);
    }
    return null;
  }
  
  public Rectangle2D getBounds()
  {
    Rectangle2D localRectangle2D = super.getBounds();
    if (localRectangle2D == null) {
      localRectangle2D = this.bounds;
    }
    return localRectangle2D;
  }
  
  /**
   * @deprecated
   */
  public Point2D getCenterPoint()
  {
    return AbstractCellView.getCenterPoint(this);
  }
  
  /**
   * @deprecated
   */
  public Point2D getPerimeterPoint(Point2D paramPoint2D1, Point2D paramPoint2D2)
  {
    return AbstractCellView.getCenterPoint(this);
  }
  
  public Point2D getPerimeterPoint(EdgeView paramEdgeView, Point2D paramPoint2D1, Point2D paramPoint2D2)
  {
    if ((getRenderer() instanceof VertexRenderer)) {
      return ((VertexRenderer)getRenderer()).getPerimeterPoint(this, paramPoint2D1, paramPoint2D2);
    }
    return super.getPerimeterPoint(paramEdgeView, paramPoint2D1, paramPoint2D2);
  }
  
  static
  {
    try
    {
      renderer = new VertexRenderer();
    }
    catch (Error localError) {}
  }
  
  public static class SizeHandle
    implements CellHandle, Serializable
  {
    protected transient Graphics offgraphics;
    protected transient boolean firstDrag = true;
    protected transient JGraph graph;
    protected transient VertexView vertex;
    protected transient CellView[] portViews;
    protected transient Rectangle2D cachedBounds;
    protected transient GraphContext context;
    protected transient Rectangle2D initialBounds;
    protected transient CellView[] contextViews;
    protected transient int index = -1;
    protected transient Rectangle2D[] r = new Rectangle2D[8];
    protected boolean firstOverlayInvocation = true;
    public transient int[] cursors = null;
    protected boolean editing = false;
    
    public SizeHandle(VertexView paramVertexView, GraphContext paramGraphContext)
    {
      this.graph = paramGraphContext.getGraph();
      this.vertex = paramVertexView;
      this.editing = (this.graph.getEditingCell() == this.vertex.getCell());
      int i = GraphConstants.getSizeableAxis(this.vertex.getAllAttributes());
      if (i == 1) {
        this.cursors = VertexView.xCursors;
      } else if (i == 2) {
        this.cursors = VertexView.yCursors;
      } else {
        this.cursors = VertexView.defaultCursors;
      }
      this.portViews = paramGraphContext.createTemporaryPortViews();
      this.initialBounds = ((Rectangle2D)this.vertex.getBounds().clone());
      this.context = paramGraphContext;
      for (int j = 0; j < this.r.length; j++) {
        this.r[j] = new Rectangle2D.Double();
      }
      invalidate();
    }
    
    public boolean isConstrainedSizeEvent(MouseEvent paramMouseEvent)
    {
      GraphUI localGraphUI = this.graph.getUI();
      if ((localGraphUI instanceof BasicGraphUI)) {
        return ((BasicGraphUI)localGraphUI).isConstrainedMoveEvent(paramMouseEvent);
      }
      return false;
    }
    
    public void paint(Graphics paramGraphics)
    {
      invalidate();
      paramGraphics.setColor(this.editing ? this.graph.getLockedHandleColor() : this.graph.getHandleColor());
      for (int i = 0; i < this.r.length; i++) {
        if (this.cursors[i] != 0) {
          paramGraphics.fill3DRect((int)this.r[i].getX(), (int)this.r[i].getY(), (int)this.r[i].getWidth(), (int)this.r[i].getHeight(), true);
        }
      }
      if (!this.graph.isXorEnabled())
      {
        this.firstOverlayInvocation = false;
        overlay(paramGraphics);
      }
    }
    
    protected void initOffscreen()
    {
      if (!this.graph.isXorEnabled()) {
        return;
      }
      try
      {
        this.offgraphics = this.graph.getOffgraphics();
      }
      catch (Exception localException)
      {
        this.offgraphics = null;
      }
      catch (Error localError)
      {
        this.offgraphics = null;
      }
    }
    
    public void overlay(Graphics paramGraphics)
    {
      if (!this.firstOverlayInvocation)
      {
        Object localObject;
        if (this.cachedBounds != null)
        {
          paramGraphics.setColor(Color.black);
          localObject = this.graph.toScreen((Rectangle2D)this.cachedBounds.clone());
          paramGraphics.drawRect((int)((Rectangle2D)localObject).getX(), (int)((Rectangle2D)localObject).getY(), (int)((Rectangle2D)localObject).getWidth() - 2, (int)((Rectangle2D)localObject).getHeight() - 2);
        }
        else if (!this.initialBounds.equals(this.vertex.getBounds()))
        {
          localObject = (Graphics2D)paramGraphics;
          AffineTransform localAffineTransform = ((Graphics2D)localObject).getTransform();
          ((Graphics2D)localObject).scale(this.graph.getScale(), this.graph.getScale());
          this.graph.getUI().paintCell(paramGraphics, this.vertex, this.vertex.getBounds(), true);
          if (this.contextViews != null) {
            for (int i = 0; i < this.contextViews.length; i++) {
              this.graph.getUI().paintCell(paramGraphics, this.contextViews[i], this.contextViews[i].getBounds(), true);
            }
          }
          if (!this.graph.isPortsScaled()) {
            ((Graphics2D)localObject).setTransform(localAffineTransform);
          }
          if ((this.portViews != null) && (this.graph.isPortsVisible())) {
            this.graph.getUI().paintPorts(paramGraphics, this.portViews);
          }
          ((Graphics2D)localObject).setTransform(localAffineTransform);
        }
      }
      this.firstOverlayInvocation = false;
    }
    
    public void mouseMoved(MouseEvent paramMouseEvent)
    {
      if (this.vertex != null) {
        for (int i = 0; i < this.r.length; i++) {
          if (this.r[i].contains(paramMouseEvent.getPoint()))
          {
            this.graph.setCursor(new Cursor(this.cursors[i]));
            paramMouseEvent.consume();
            return;
          }
        }
      }
    }
    
    public void mousePressed(MouseEvent paramMouseEvent)
    {
      if (!this.graph.isSizeable()) {
        return;
      }
      for (int i = 0; i < this.r.length; i++) {
        if ((this.r[i].contains(paramMouseEvent.getPoint())) && (this.cursors[i] != 0))
        {
          HashSet localHashSet = new HashSet();
          localHashSet.add(this.vertex.getCell());
          this.contextViews = this.context.createTemporaryContextViews(localHashSet);
          CellView[] arrayOfCellView = AbstractCellView.getDescendantViews(new CellView[] { this.vertex });
          if (arrayOfCellView.length >= BasicGraphUI.MAXHANDLES) {
            this.cachedBounds = ((Rectangle2D)this.initialBounds.clone());
          }
          paramMouseEvent.consume();
          this.index = i;
          return;
        }
      }
    }
    
    public void mouseDragged(MouseEvent paramMouseEvent)
    {
      if ((this.firstDrag) && (this.graph.isDoubleBuffered()) && (this.cachedBounds == null))
      {
        initOffscreen();
        this.firstDrag = false;
      }
      Rectangle2D localRectangle2D1 = null;
      Graphics localGraphics = this.offgraphics != null ? this.offgraphics : this.graph.getGraphics();
      if (this.index == -1) {
        return;
      }
      if ((this.offgraphics != null) || (!this.graph.isXorEnabled()))
      {
        localRectangle2D1 = this.graph.toScreen((Rectangle2D)this.vertex.getBounds().clone());
        localRectangle2D2 = this.graph.toScreen(AbstractCellView.getBounds(this.contextViews));
        if (localRectangle2D2 != null) {
          localRectangle2D1.add(localRectangle2D2);
        }
      }
      Rectangle2D localRectangle2D2 = computeBounds(paramMouseEvent);
      if (this.graph.isXorEnabled())
      {
        localGraphics.setColor(this.graph.getForeground());
        localGraphics.setXORMode(this.graph.getBackground().darker());
        overlay(localGraphics);
      }
      else
      {
        this.firstOverlayInvocation = false;
      }
      Object localObject;
      int i;
      if (this.cachedBounds != null)
      {
        this.cachedBounds = localRectangle2D2;
      }
      else
      {
        localObject = AbstractCellView.getDescendantViews(new CellView[] { this.vertex });
        for (i = 0; i < localObject.length; i++)
        {
          CellView localCellView = this.graph.getGraphLayoutCache().getMapping(localObject[i].getCell(), false);
          if (localCellView != null)
          {
            AttributeMap localAttributeMap = (AttributeMap)localCellView.getAllAttributes().clone();
            localObject[i].changeAttributes(this.graph.getGraphLayoutCache(), localAttributeMap);
            localObject[i].refresh(this.graph.getGraphLayoutCache(), this.context, false);
          }
        }
        this.vertex.setBounds(localRectangle2D2);
        if (this.vertex != null) {
          this.graph.getGraphLayoutCache().update(this.vertex);
        }
        if (this.contextViews != null) {
          this.graph.getGraphLayoutCache().update(this.contextViews);
        }
      }
      if (this.graph.isXorEnabled()) {
        overlay(localGraphics);
      }
      if ((this.offgraphics != null) || (!this.graph.isXorEnabled()))
      {
        localRectangle2D1.add(this.graph.toScreen((Rectangle2D)this.vertex.getBounds().clone()));
        localObject = this.graph.toScreen(AbstractCellView.getBounds(this.contextViews));
        if (localObject != null) {
          localRectangle2D1.add((Rectangle2D)localObject);
        }
        i = PortView.SIZE + 10;
        if (this.graph.isPortsScaled()) {
          i = (int)(this.graph.getScale() * i);
        }
        int j = i / 2;
        localRectangle2D1.setFrame(localRectangle2D1.getX() - j, localRectangle2D1.getY() - j, localRectangle2D1.getWidth() + i, localRectangle2D1.getHeight() + i);
        double d1 = Math.max(0.0D, localRectangle2D1.getX());
        double d2 = Math.max(0.0D, localRectangle2D1.getY());
        double d3 = d1 + localRectangle2D1.getWidth();
        double d4 = d2 + localRectangle2D1.getHeight();
        if (this.offgraphics != null) {
          this.graph.drawImage((int)d1, (int)d2, (int)d3, (int)d4, (int)d1, (int)d2, (int)d3, (int)d4);
        } else {
          this.graph.repaint((int)localRectangle2D1.getX(), (int)localRectangle2D1.getY(), (int)localRectangle2D1.getWidth(), (int)localRectangle2D1.getHeight());
        }
      }
    }
    
    protected Rectangle2D computeBounds(MouseEvent paramMouseEvent)
    {
      double d1 = this.initialBounds.getX();
      double d2 = this.initialBounds.getX() + this.initialBounds.getWidth() - 1.0D;
      double d3 = this.initialBounds.getY();
      double d4 = this.initialBounds.getY() + this.initialBounds.getHeight() - 1.0D;
      Point2D localPoint2D = this.graph.fromScreen(this.graph.snap((Point2D)paramMouseEvent.getPoint().clone()));
      localPoint2D.setLocation(Math.max(0.0D, localPoint2D.getX()), Math.max(0.0D, localPoint2D.getY()));
      if (this.index > 4) {
        d4 = localPoint2D.getY();
      } else if (this.index < 3) {
        d3 = localPoint2D.getY();
      }
      if ((this.index == 0) || (this.index == 3) || (this.index == 5)) {
        d1 = localPoint2D.getX();
      } else if ((this.index == 2) || (this.index == 4) || (this.index == 7)) {
        d2 = localPoint2D.getX();
      }
      double d5 = d2 - d1;
      double d6 = d4 - d3;
      if ((isConstrainedSizeEvent(paramMouseEvent)) || (GraphConstants.isConstrained(this.vertex.getAllAttributes()))) {
        if ((this.index == 3) || (this.index == 4) || (this.index == 5))
        {
          d6 = d5;
        }
        else if ((this.index == 1) || (this.index == 6) || (this.index == 2) || (this.index == 7))
        {
          d5 = d6;
        }
        else
        {
          d6 = d5;
          d3 = d4 - d6;
        }
      }
      if (d5 < 0.0D)
      {
        d1 += d5;
        d5 = Math.abs(d5);
      }
      if (d6 < 0.0D)
      {
        d3 += d6;
        d6 = Math.abs(d6);
      }
      return new Rectangle2D.Double(d1, d3, d5 + 1.0D, d6 + 1.0D);
    }
    
    public void mouseReleased(MouseEvent paramMouseEvent)
    {
      if (this.index != -1)
      {
        this.cachedBounds = computeBounds(paramMouseEvent);
        this.vertex.setBounds(this.cachedBounds);
        CellView[] arrayOfCellView = AbstractCellView.getDescendantViews(new CellView[] { this.vertex });
        Map localMap = GraphConstants.createAttributes(arrayOfCellView, null);
        this.graph.getGraphLayoutCache().edit(localMap, null, null, null);
      }
      paramMouseEvent.consume();
      this.cachedBounds = null;
      this.initialBounds = null;
      this.firstDrag = true;
    }
    
    protected void invalidate()
    {
      Rectangle2D localRectangle2D = this.graph.getCellBounds(this.vertex.getCell());
      if (localRectangle2D != null)
      {
        localRectangle2D = (Rectangle2D)localRectangle2D.clone();
        this.graph.toScreen(localRectangle2D);
        int i = this.graph.getHandleSize();
        int j = 2 * i;
        double d1 = localRectangle2D.getX() - i;
        double d2 = localRectangle2D.getY() - i;
        double d3 = localRectangle2D.getX() + localRectangle2D.getWidth() / 2.0D - i;
        double d4 = localRectangle2D.getY() + localRectangle2D.getHeight() / 2.0D - i;
        double d5 = localRectangle2D.getX() + localRectangle2D.getWidth() - i;
        double d6 = localRectangle2D.getY() + localRectangle2D.getHeight() - i;
        this.r[0].setFrame(d1, d2, j, j);
        this.r[1].setFrame(d3, d2, j, j);
        this.r[2].setFrame(d5, d2, j, j);
        this.r[3].setFrame(d1, d4, j, j);
        this.r[4].setFrame(d5, d4, j, j);
        this.r[5].setFrame(d1, d6, j, j);
        this.r[6].setFrame(d3, d6, j, j);
        this.r[7].setFrame(d5, d6, j, j);
      }
    }
  }
}


/* Location:              /home/menick/Scrivania/ProcessMining/CNMining/CNMining.jar!/org/jgraph/graph/VertexView.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */