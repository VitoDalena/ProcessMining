package org.jgraph.graph;

import java.awt.Color;
import java.awt.Container;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.Shape;
import java.awt.event.MouseEvent;
import java.awt.geom.AffineTransform;
import java.awt.geom.GeneralPath;
import java.awt.geom.Line2D;
import java.awt.geom.Line2D.Double;
import java.awt.geom.Point2D;
import java.awt.geom.Point2D.Double;
import java.awt.geom.Rectangle2D;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import javax.swing.BorderFactory;
import javax.swing.JComponent;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;
import org.jgraph.JGraph;
import org.jgraph.plaf.GraphUI;
import org.jgraph.plaf.basic.BasicGraphUI;

public class EdgeView
  extends AbstractCellView
{
  public static transient EdgeRenderer renderer = new EdgeRenderer();
  protected List points;
  protected CellView source;
  protected CellView target;
  protected CellView sourceParentView;
  protected CellView targetParentView;
  protected Point2D labelPosition;
  protected Point2D[] extraLabelPositions;
  protected transient Point2D labelVector = null;
  public transient Shape beginShape;
  public transient Shape endShape;
  public transient Shape lineShape;
  public transient GeneralPath sharedPath = null;
  protected transient Rectangle2D cachedBounds = null;
  public static boolean LEGACY_DISCONNECTABLE = true;
  
  public EdgeView() {}
  
  public EdgeView(Object paramObject)
  {
    super(paramObject);
  }
  
  public void refresh(GraphLayoutCache paramGraphLayoutCache, CellMapper paramCellMapper, boolean paramBoolean)
  {
    this.points = null;
    super.refresh(paramGraphLayoutCache, paramCellMapper, paramBoolean);
    GraphModel localGraphModel = paramGraphLayoutCache.getModel();
    Object localObject1 = localGraphModel.getSource(this.cell);
    Object localObject2 = localGraphModel.getTarget(this.cell);
    setSource(paramCellMapper.getMapping(localObject1, paramBoolean));
    setTarget(paramCellMapper.getMapping(localObject2, paramBoolean));
    if ((localObject1 != null) && (getSource() == null)) {
      this.sourceParentView = getVisibleParent(localGraphModel, paramCellMapper, localObject1);
    } else {
      this.sourceParentView = null;
    }
    if ((localObject2 != null) && (getTarget() == null)) {
      this.targetParentView = getVisibleParent(localGraphModel, paramCellMapper, localObject2);
    } else {
      this.targetParentView = null;
    }
  }
  
  protected CellView getVisibleParent(GraphModel paramGraphModel, CellMapper paramCellMapper, Object paramObject)
  {
    CellView localCellView = null;
    do
    {
      localCellView = paramCellMapper.getMapping(paramObject, false);
      paramObject = paramGraphModel.getParent(paramObject);
    } while ((localCellView == null) && (paramObject != null));
    return localCellView;
  }
  
  public void update(GraphLayoutCache paramGraphLayoutCache)
  {
    super.update(paramGraphLayoutCache);
    Object localObject = GraphConstants.getPoints(this.allAttributes);
    if (localObject == null)
    {
      localObject = new ArrayList(4);
      ((List)localObject).add(this.allAttributes.createPoint(10.0D, 10.0D));
      ((List)localObject).add(this.allAttributes.createPoint(20.0D, 20.0D));
      GraphConstants.setPoints(this.allAttributes, (List)localObject);
    }
    if (this.points == null) {
      this.points = ((List)localObject);
    }
    Edge.Routing localRouting = GraphConstants.getRouting(this.allAttributes);
    List localList = null;
    if (localRouting != null) {
      localList = localRouting.route(paramGraphLayoutCache, this);
    }
    this.points = ((localList != null) && (!localList.isEmpty()) ? localList : (List)localObject);
    if (this.points == localObject)
    {
      if (this.source != null) {
        setSource(this.source);
      }
      if (this.target != null) {
        setTarget(this.target);
      }
    }
    checkDefaultLabelPosition();
    Point2D[] arrayOfPoint2D = GraphConstants.getExtraLabelPositions(this.allAttributes);
    if (arrayOfPoint2D != null)
    {
      this.extraLabelPositions = new Point2D[arrayOfPoint2D.length];
      for (int i = 0; i < arrayOfPoint2D.length; i++) {
        this.extraLabelPositions[i] = arrayOfPoint2D[i];
      }
    }
    else
    {
      this.extraLabelPositions = null;
    }
    this.beginShape = null;
    this.endShape = null;
    this.lineShape = null;
    invalidate();
  }
  
  protected void checkDefaultLabelPosition()
  {
    this.labelPosition = GraphConstants.getLabelPosition(this.allAttributes);
    String str = String.valueOf(getCell());
    if ((this.labelPosition == null) && (str != null) && (str.length() > 0))
    {
      int i = 500;
      this.labelPosition = new Point(i, 0);
      GraphConstants.setLabelPosition(this.allAttributes, this.labelPosition);
    }
  }
  
  protected void invalidate()
  {
    this.labelVector = null;
    this.sharedPath = null;
    this.cachedBounds = null;
  }
  
  public Shape getShape()
  {
    if (this.sharedPath != null) {
      return this.sharedPath;
    }
    return this.sharedPath = (GeneralPath)getEdgeRenderer().createShape();
  }
  
  public boolean intersects(JGraph paramJGraph, Rectangle2D paramRectangle2D)
  {
    boolean bool = super.intersects(paramJGraph, paramRectangle2D);
    if (!isLeaf()) {
      return bool;
    }
    if (bool)
    {
      Rectangle localRectangle = new Rectangle((int)paramRectangle2D.getX(), (int)paramRectangle2D.getY(), (int)paramRectangle2D.getWidth(), (int)paramRectangle2D.getHeight());
      return getEdgeRenderer().intersects(paramJGraph, this, localRectangle);
    }
    return false;
  }
  
  public Rectangle2D getBounds()
  {
    Rectangle2D localRectangle2D = super.getBounds();
    if (localRectangle2D == null)
    {
      if (this.cachedBounds == null) {
        this.cachedBounds = getEdgeRenderer().getBounds(this);
      }
      localRectangle2D = this.cachedBounds;
    }
    return localRectangle2D;
  }
  
  EdgeRenderer getEdgeRenderer()
  {
    return (EdgeRenderer)getRenderer();
  }
  
  public CellViewRenderer getRenderer()
  {
    return renderer;
  }
  
  public CellHandle getHandle(GraphContext paramGraphContext)
  {
    return new EdgeHandle(this, paramGraphContext);
  }
  
  public CellView getSource()
  {
    return this.source;
  }
  
  public CellView getSourceParentView()
  {
    return this.sourceParentView;
  }
  
  public void setSource(CellView paramCellView)
  {
    this.sourceParentView = null;
    this.source = paramCellView;
    if (this.source != null) {
      this.points.set(0, this.source);
    } else {
      this.points.set(0, getPoint(0));
    }
    invalidate();
  }
  
  public CellView getTarget()
  {
    return this.target;
  }
  
  public CellView getTargetParentView()
  {
    return this.targetParentView;
  }
  
  public void setTarget(CellView paramCellView)
  {
    this.target = paramCellView;
    this.targetParentView = null;
    int i = this.points.size() - 1;
    if (this.target != null) {
      this.points.set(i, this.target);
    } else {
      this.points.set(i, getPoint(i));
    }
    invalidate();
  }
  
  public Point2D getExtraLabelPosition(int paramInt)
  {
    return this.extraLabelPositions[paramInt];
  }
  
  public Point2D getLabelPosition()
  {
    return this.labelPosition;
  }
  
  public void setLabelPosition(Point2D paramPoint2D)
  {
    this.labelPosition.setLocation(paramPoint2D);
    invalidate();
  }
  
  public void setExtraLabelPosition(int paramInt, Point2D paramPoint2D)
  {
    this.extraLabelPositions[paramInt].setLocation(paramPoint2D);
    invalidate();
  }
  
  public boolean isLoop()
  {
    return ((getSource() != null) && (getSource() == getTarget())) || ((this.sourceParentView != null) && (this.sourceParentView == this.targetParentView)) || ((this.sourceParentView != null) && (getTarget() != null) && (getTarget().getParentView() == this.sourceParentView)) || ((this.targetParentView != null) && (getSource() != null) && (getSource().getParentView() == this.targetParentView));
  }
  
  public List getPoints()
  {
    return this.points;
  }
  
  public int getPointCount()
  {
    if (this.points != null) {
      return this.points.size();
    }
    return 0;
  }
  
  public Point2D getPoint(int paramInt)
  {
    Object localObject = this.points.get(paramInt);
    if ((paramInt == 0) && (this.sourceParentView != null)) {
      return this.sourceParentView.getPerimeterPoint(this, getCenterPoint(this.sourceParentView), getNearestPoint(paramInt == 0));
    }
    if ((paramInt == getPointCount() - 1) && (this.targetParentView != null)) {
      return this.targetParentView.getPerimeterPoint(this, getCenterPoint(this.targetParentView), getNearestPoint(paramInt == 0));
    }
    if ((localObject instanceof PortView)) {
      return ((PortView)localObject).getLocation(this, getNearestPoint(paramInt == 0));
    }
    if ((localObject instanceof CellView))
    {
      Rectangle2D localRectangle2D = ((CellView)localObject).getBounds();
      return new Point2D.Double(localRectangle2D.getX(), localRectangle2D.getY());
    }
    if ((localObject instanceof Point2D)) {
      return (Point2D)localObject;
    }
    return null;
  }
  
  protected Point2D getNearestPoint(boolean paramBoolean)
  {
    if (getPointCount() == 2)
    {
      if ((paramBoolean) && ((this.target instanceof PortView)) && (GraphConstants.getOffset(this.target.getAllAttributes()) != null)) {
        return ((PortView)this.target).getLocation(this);
      }
      if ((!paramBoolean) && ((this.source instanceof PortView)) && (GraphConstants.getOffset(this.source.getAllAttributes()) != null)) {
        return ((PortView)this.source).getLocation(this);
      }
      if ((paramBoolean) && (this.targetParentView != null) && (this.targetParentView.isLeaf())) {
        return getCenterPoint(this.targetParentView);
      }
      if ((!paramBoolean) && (this.sourceParentView != null) && (this.sourceParentView.isLeaf())) {
        return getCenterPoint(this.sourceParentView);
      }
    }
    return getPointLocation(paramBoolean ? 1 : getPointCount() - 2);
  }
  
  protected Point2D getPointLocation(int paramInt)
  {
    Object localObject = this.points.get(paramInt);
    if ((localObject instanceof Point2D)) {
      return (Point2D)localObject;
    }
    if ((localObject instanceof PortView))
    {
      CellView localCellView = ((CellView)localObject).getParentView();
      if (localCellView != null) {
        return getCenterPoint(localCellView);
      }
    }
    return null;
  }
  
  public void setPoint(int paramInt, Point2D paramPoint2D)
  {
    this.points.set(paramInt, paramPoint2D);
    invalidate();
  }
  
  public void addPoint(int paramInt, Point2D paramPoint2D)
  {
    this.points.add(paramInt, paramPoint2D);
    invalidate();
  }
  
  public void removePoint(int paramInt)
  {
    this.points.remove(paramInt);
    invalidate();
  }
  
  public void addExtraLabel(Point2D paramPoint2D, Object paramObject)
  {
    Object localObject1 = GraphConstants.getExtraLabels(getAllAttributes());
    Object localObject2 = GraphConstants.getExtraLabelPositions(getAllAttributes());
    if (localObject1 == null)
    {
      localObject1 = new Object[1];
      localObject2 = new Point2D[1];
    }
    else
    {
      Object[] arrayOfObject = new Object[localObject1.length + 1];
      System.arraycopy(localObject1, 0, arrayOfObject, 0, localObject1.length);
      localObject1 = arrayOfObject;
      Point2D[] arrayOfPoint2D = new Point2D[localObject2.length + 1];
      System.arraycopy(localObject2, 0, arrayOfPoint2D, 0, localObject2.length);
      localObject2 = arrayOfPoint2D;
    }
    int i = localObject1.length - 1;
    localObject1[i] = paramObject;
    localObject2[i] = paramPoint2D;
    GraphConstants.setExtraLabels(getAllAttributes(), (Object[])localObject1);
    GraphConstants.setExtraLabelPositions(getAllAttributes(), (Point2D[])localObject2);
  }
  
  public void removeExtraLabel(int paramInt)
  {
    Object[] arrayOfObject1 = GraphConstants.getExtraLabels(getAllAttributes());
    Point2D[] arrayOfPoint2D1 = GraphConstants.getExtraLabelPositions(getAllAttributes());
    if ((arrayOfObject1 == null) || (arrayOfObject1.length > 1))
    {
      Object[] arrayOfObject2 = new Object[arrayOfObject1.length - 1];
      Point2D[] arrayOfPoint2D2 = new Point2D[arrayOfPoint2D1.length - 1];
      System.arraycopy(arrayOfObject1, 0, arrayOfObject2, 0, paramInt);
      if (paramInt < arrayOfObject2.length) {
        System.arraycopy(arrayOfObject1, paramInt + 1, arrayOfObject2, paramInt, arrayOfObject2.length - paramInt);
      }
      System.arraycopy(arrayOfPoint2D1, 0, arrayOfPoint2D2, 0, paramInt);
      if (paramInt < arrayOfPoint2D2.length) {
        System.arraycopy(arrayOfPoint2D1, paramInt + 1, arrayOfPoint2D2, paramInt, arrayOfPoint2D2.length - paramInt);
      }
      GraphConstants.setExtraLabels(getAllAttributes(), arrayOfObject2);
      GraphConstants.setExtraLabelPositions(getAllAttributes(), arrayOfPoint2D2);
    }
    else
    {
      GraphConstants.setExtraLabels(getAllAttributes(), new Object[0]);
      GraphConstants.setExtraLabelPositions(getAllAttributes(), new Point2D[0]);
    }
  }
  
  public int getFirstPointOfSegment()
  {
    boolean bool = GraphConstants.isExactSegmentLabel(this.allAttributes);
    double d1 = 0.0D;
    double d2 = 0.0D;
    int i = getPointCount();
    if (bool)
    {
      Object localObject = getPoint(0);
      double d3 = 0.0D;
      for (int j = 1; j < i; j++)
      {
        Point2D localPoint2D1 = getPoint(j);
        d1 = localPoint2D1.getX() - ((Point2D)localObject).getX();
        d2 = localPoint2D1.getY() - ((Point2D)localObject).getY();
        d3 += Math.sqrt(d1 * d1 + d2 * d2);
        localObject = localPoint2D1;
      }
      double d4 = getLabelPosition().getX() / 1000.0D;
      double d5 = d4 * d3;
      d3 = 0.0D;
      localObject = getPoint(0);
      if ((d4 <= 0.0D) || (d4 >= 1.0D)) {
        return -1;
      }
      for (int k = 1; k < i; k++)
      {
        Point2D localPoint2D2 = getPoint(k);
        d1 = localPoint2D2.getX() - ((Point2D)localObject).getX();
        d2 = localPoint2D2.getY() - ((Point2D)localObject).getY();
        d3 += Math.sqrt(d1 * d1 + d2 * d2);
        if (d3 > d5) {
          return k - 1;
        }
      }
    }
    else
    {
      return -1;
    }
    return -1;
  }
  
  public Point2D getLabelVector()
  {
    if (this.labelVector == null)
    {
      Point2D localPoint2D1 = getPoint(0);
      double d1 = 0.0D;
      double d2 = 0.0D;
      int i = getPointCount();
      Object localObject;
      if (isLoop())
      {
        for (int j = 1; j < i; j++)
        {
          localObject = getPoint(j);
          d1 += ((Point2D)localObject).getX() - localPoint2D1.getX();
          d2 += ((Point2D)localObject).getY() - localPoint2D1.getY();
        }
        i /= 2;
        d1 /= i;
        d2 /= i;
        this.labelVector = new Point2D.Double(d1, d2);
      }
      else
      {
        boolean bool = GraphConstants.isExactSegmentLabel(this.allAttributes);
        if (bool)
        {
          localObject = getPoint(0);
          double d3 = 0.0D;
          for (int k = 1; k < i; k++)
          {
            Point2D localPoint2D2 = getPoint(k);
            d1 = localPoint2D2.getX() - ((Point2D)localObject).getX();
            d2 = localPoint2D2.getY() - ((Point2D)localObject).getY();
            d3 += Math.sqrt(d1 * d1 + d2 * d2);
            localObject = localPoint2D2;
          }
          double d4 = getLabelPosition().getX() / 1000.0D;
          double d5 = d4 * d3;
          d3 = 0.0D;
          localObject = getPoint(0);
          if ((d4 <= 0.0D) || (d4 >= 1.0D)) {
            bool = false;
          } else {
            for (int m = 1; m < i; m++)
            {
              Point2D localPoint2D3 = getPoint(m);
              d1 = localPoint2D3.getX() - ((Point2D)localObject).getX();
              d2 = localPoint2D3.getY() - ((Point2D)localObject).getY();
              d3 += Math.sqrt(d1 * d1 + d2 * d2);
              if (d3 > d5)
              {
                this.labelVector = new Point2D.Double(d1, d2);
                break;
              }
              localObject = localPoint2D3;
            }
          }
        }
        if ((!bool) || (this.labelVector == null))
        {
          localObject = getPoint(i - 1);
          d1 = ((Point2D)localObject).getX() - localPoint2D1.getX();
          d2 = ((Point2D)localObject).getY() - localPoint2D1.getY();
          this.labelVector = new Point2D.Double(d1, d2);
        }
      }
    }
    return this.labelVector;
  }
  
  protected Point2D getAbsoluteLabelPosition()
  {
    Point2D localPoint2D = getAbsoluteLabelPositionFromRelative(GraphConstants.getLabelPosition(getAllAttributes()));
    return localPoint2D;
  }
  
  protected Point2D getAbsoluteExtraLabelPosition(int paramInt)
  {
    Point2D[] arrayOfPoint2D = GraphConstants.getExtraLabelPositions(getAllAttributes());
    if ((arrayOfPoint2D != null) && (arrayOfPoint2D.length > paramInt))
    {
      Point2D localPoint2D = getAbsoluteLabelPositionFromRelative(arrayOfPoint2D[paramInt]);
      return localPoint2D;
    }
    return null;
  }
  
  protected Point2D getAbsoluteLabelPositionFromRelative(Point2D paramPoint2D)
  {
    Point2D localPoint2D1 = convertRelativeLabelPositionToAbsolute(paramPoint2D);
    if (localPoint2D1 != null)
    {
      double d1 = 0.0D;
      double d2 = 0.0D;
      Point2D localPoint2D2 = GraphConstants.getOffset(getAllAttributes());
      if (localPoint2D2 != null)
      {
        d1 = localPoint2D2.getX();
        d2 = localPoint2D2.getY();
      }
      double d3 = localPoint2D1.getX() + d1;
      double d4 = localPoint2D1.getY() + d2;
      return new Point2D.Double(d3, d4);
    }
    return null;
  }
  
  protected Point2D convertRelativeLabelPositionToAbsolute(Point2D paramPoint2D)
  {
    Object localObject = getPoint(0);
    if (localObject != null)
    {
      double d1 = 0.0D;
      int i = getPointCount();
      double[] arrayOfDouble = new double[i];
      for (int j = 1; j < i; j++)
      {
        Point2D localPoint2D1 = getPoint(j);
        if (localPoint2D1 != null)
        {
          d3 = ((Point2D)localObject).getX() - localPoint2D1.getX();
          d4 = ((Point2D)localObject).getY() - localPoint2D1.getY();
          double d5 = Math.sqrt(d3 * d3 + d4 * d4);
          arrayOfDouble[(j - 1)] = d5;
          d1 += d5;
          localObject = localPoint2D1;
        }
      }
      double d2 = paramPoint2D.getX() / 1000.0D;
      double d3 = paramPoint2D.getY();
      double d4 = d2 * d1;
      d1 = 0.0D;
      int k = 1;
      for (double d6 = arrayOfDouble[0]; (d4 > d1 + d6) && (k < i - 1); d6 = arrayOfDouble[(k++)]) {
        d1 += d6;
      }
      double d7 = (d4 - d1) / d6;
      Point2D localPoint2D2 = getPoint(k - 1);
      Point2D localPoint2D3 = getPoint(k);
      if ((localPoint2D2 != null) && (localPoint2D3 != null))
      {
        double d8 = localPoint2D3.getX() - localPoint2D2.getX();
        double d9 = localPoint2D3.getY() - localPoint2D2.getY();
        double d10 = d9 / d6;
        double d11 = d8 / d6;
        d2 = localPoint2D2.getX() + d8 * d7 - d10 * d3;
        d3 = localPoint2D2.getY() + d9 * d7 + d11 * d3;
        return new Point2D.Double(d2, d3);
      }
    }
    return null;
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
  
  public Point2D getPerimeterPoint(EdgeView paramEdgeView, Point2D paramPoint2D1, Point2D paramPoint2D2)
  {
    if (getPointCount() > 2) {
      return getPoint(getPointCount() / 2);
    }
    Point2D localPoint2D1 = getPoint(0);
    Point2D localPoint2D2 = getPoint(getPointCount() - 1);
    return new Point2D.Double((localPoint2D2.getX() + localPoint2D1.getX()) / 2.0D, (localPoint2D2.getY() + localPoint2D1.getY()) / 2.0D);
  }
  
  public static class EdgeHandle
    implements CellHandle, Serializable
  {
    protected JGraph graph;
    protected EdgeView edge;
    protected EdgeView orig;
    protected boolean label = false;
    protected boolean source = false;
    protected boolean target = false;
    protected int currentLabel = -1;
    protected int currentIndex = -1;
    protected Point2D currentPoint;
    protected transient Rectangle2D[] r;
    protected transient Rectangle2D loc;
    protected transient Rectangle2D[] extraLabelLocations;
    protected boolean firstOverlayCall = true;
    protected boolean isEdgeConnectable = true;
    protected EdgeView relevantEdge = null;
    protected boolean editing = false;
    protected Point2D initialLabelLocation = null;
    protected boolean edgeModified = false;
    protected JComponent highlight = new JPanel();
    
    public EdgeHandle(EdgeView paramEdgeView, GraphContext paramGraphContext)
    {
      this.graph = paramGraphContext.getGraph();
      this.edge = paramEdgeView;
      this.editing = (this.graph.getEditingCell() == paramEdgeView.getCell());
      this.loc = new Rectangle();
      Object[] arrayOfObject = GraphConstants.getExtraLabels(paramEdgeView.getAllAttributes());
      if (arrayOfObject != null)
      {
        this.extraLabelLocations = new Rectangle[arrayOfObject.length];
        for (int i = 0; i < this.extraLabelLocations.length; i++) {
          this.extraLabelLocations[i] = new Rectangle();
        }
      }
      this.orig = ((EdgeView)this.graph.getGraphLayoutCache().getMapping(paramEdgeView.getCell(), false));
      reloadPoints(this.orig);
      this.isEdgeConnectable = GraphConstants.isConnectable(paramEdgeView.getAllAttributes());
      this.highlight = createHighlight();
    }
    
    protected JComponent createHighlight()
    {
      JPanel localJPanel = new JPanel();
      localJPanel.setBorder(BorderFactory.createBevelBorder(0));
      localJPanel.setVisible(false);
      localJPanel.setOpaque(false);
      return localJPanel;
    }
    
    protected void reloadPoints(EdgeView paramEdgeView)
    {
      this.relevantEdge = paramEdgeView;
      this.r = new Rectangle[paramEdgeView.getPointCount()];
      for (int i = 0; i < this.r.length; i++) {
        this.r[i] = new Rectangle();
      }
      invalidate();
    }
    
    public void paint(Graphics paramGraphics)
    {
      invalidate();
      if (!this.edge.isLeaf()) {
        return;
      }
      for (int i = 0; i < this.r.length; i++)
      {
        if ((this.isEdgeConnectable) && (!this.editing)) {
          paramGraphics.setColor(this.graph.getHandleColor());
        } else {
          paramGraphics.setColor(this.graph.getLockedHandleColor());
        }
        paramGraphics.fill3DRect((int)this.r[i].getX(), (int)this.r[i].getY(), (int)this.r[i].getWidth(), (int)this.r[i].getHeight(), true);
        CellView localCellView = null;
        if ((i == 0) && (this.edge.getSource() != null)) {
          localCellView = this.edge.getSource();
        } else if ((i == this.r.length - 1) && (this.edge.getTarget() != null)) {
          localCellView = this.edge.getTarget();
        }
        if ((localCellView != null) || ((i == 0) && (this.edge.getSourceParentView() != null)) || ((i == this.r.length - 1) && (this.edge.getTargetParentView() != null)))
        {
          paramGraphics.setColor(this.graph.getLockedHandleColor());
          Object localObject = localCellView != null ? GraphConstants.getOffset(localCellView.getAllAttributes()) : null;
          if (localObject != null)
          {
            paramGraphics.drawLine((int)this.r[i].getX() + 1, (int)this.r[i].getY() + 1, (int)(this.r[i].getX() + this.r[i].getWidth()) - 3, (int)(this.r[i].getY() + this.r[i].getHeight()) - 3);
            paramGraphics.drawLine((int)this.r[i].getX() + 1, (int)(this.r[i].getY() + this.r[i].getHeight()) - 3, (int)(this.r[i].getX() + this.r[i].getWidth()) - 3, (int)this.r[i].getY() + 1);
          }
          else
          {
            paramGraphics.drawRect((int)this.r[i].getX() + 2, (int)this.r[i].getY() + 2, (int)this.r[i].getWidth() - 5, (int)this.r[i].getHeight() - 5);
          }
        }
      }
      if (!this.graph.isXorEnabled())
      {
        this.firstOverlayCall = false;
        overlay(paramGraphics);
      }
    }
    
    protected void highlight(JGraph paramJGraph, CellView paramCellView)
    {
      if (paramCellView != null)
      {
        this.highlight.setBounds(getHighlightBounds(paramJGraph, paramCellView));
        if (this.highlight.getParent() == null)
        {
          paramJGraph.add(this.highlight);
          this.highlight.setVisible(true);
        }
      }
      else if (this.highlight.getParent() != null)
      {
        this.highlight.setVisible(false);
        this.highlight.getParent().remove(this.highlight);
      }
    }
    
    protected Rectangle getHighlightBounds(JGraph paramJGraph, CellView paramCellView)
    {
      int i = GraphConstants.getOffset(paramCellView.getAllAttributes()) != null ? 1 : 0;
      Rectangle2D localRectangle2D = i != 0 ? paramCellView.getBounds() : paramCellView.getParentView().getBounds();
      localRectangle2D = paramJGraph.toScreen((Rectangle2D)localRectangle2D.clone());
      int j = 3;
      return new Rectangle((int)(localRectangle2D.getX() - j), (int)(localRectangle2D.getY() - j), (int)(localRectangle2D.getWidth() + 2 * j), (int)(localRectangle2D.getHeight() + 2 * j));
    }
    
    public void overlay(Graphics paramGraphics)
    {
      if ((this.edge != null) && (!this.firstOverlayCall) && (this.edge.isLeaf()))
      {
        paramGraphics.setColor(this.graph.getForeground());
        if (this.graph.isXorEnabled()) {
          paramGraphics.setXORMode(this.graph.getBackground().darker());
        }
        Graphics2D localGraphics2D = (Graphics2D)paramGraphics;
        AffineTransform localAffineTransform = localGraphics2D.getTransform();
        localGraphics2D.scale(this.graph.getScale(), this.graph.getScale());
        this.graph.getUI().paintCell(paramGraphics, this.edge, this.edge.getBounds(), true);
        localGraphics2D.setTransform(localAffineTransform);
        if (this.graph.isXorEnabled()) {
          if ((isSourceEditing()) && (this.edge.getSource() != null)) {
            paintPort(paramGraphics, this.edge.getSource());
          } else if ((isTargetEditing()) && (this.edge.getTarget() != null)) {
            paintPort(paramGraphics, this.edge.getTarget());
          }
        }
      }
      if (!this.graph.isXorEnabled()) {
        if (isSourceEditing()) {
          highlight(this.graph, this.edge.getSource());
        } else if (isTargetEditing()) {
          highlight(this.graph, this.edge.getTarget());
        }
      }
      this.firstOverlayCall = false;
    }
    
    protected void paintPort(Graphics paramGraphics, CellView paramCellView)
    {
      int i = GraphConstants.getOffset(paramCellView.getAllAttributes()) != null ? 1 : 0;
      Rectangle2D localRectangle2D = i != 0 ? paramCellView.getBounds() : paramCellView.getParentView().getBounds();
      localRectangle2D = this.graph.toScreen((Rectangle2D)localRectangle2D.clone());
      int j = 3;
      localRectangle2D.setFrame(localRectangle2D.getX() - j, localRectangle2D.getY() - j, localRectangle2D.getWidth() + 2 * j, localRectangle2D.getHeight() + 2 * j);
      this.graph.getUI().paintCell(paramGraphics, paramCellView, localRectangle2D, true);
    }
    
    protected boolean snap(boolean paramBoolean, Point2D paramPoint2D)
    {
      int i = (this.graph.isConnectable()) && (this.isEdgeConnectable) ? 1 : 0;
      Object localObject1 = this.graph.getPortForLocation(paramPoint2D.getX(), paramPoint2D.getY());
      if ((localObject1 != null) && (this.graph.getModel().getParent(localObject1) == this.edge.getCell())) {
        localObject1 = null;
      }
      if ((localObject1 != null) && (i != 0))
      {
        CellView localCellView = this.graph.getGraphLayoutCache().getMapping(localObject1, false);
        Rectangle2D localRectangle2D = this.edge.getBounds();
        localRectangle2D.add(localCellView.getParentView().getBounds());
        if (GraphConstants.isConnectable(localCellView.getParentView().getAllAttributes()))
        {
          Object localObject2 = this.edge.getCell();
          if ((paramBoolean) && (this.graph.getModel().acceptsSource(localObject2, localObject1)))
          {
            if (this.edge.getSource() != localCellView)
            {
              this.edgeModified = true;
              if (this.graph.isXorEnabled()) {
                overlay(this.graph.getGraphics());
              }
              this.edge.setSource(localCellView);
              this.edge.update(this.graph.getGraphLayoutCache());
              if (this.graph.isXorEnabled())
              {
                overlay(this.graph.getGraphics());
              }
              else
              {
                localRectangle2D.add(this.edge.getBounds());
                this.graph.repaint((int)localRectangle2D.getX(), (int)localRectangle2D.getY(), (int)localRectangle2D.getWidth(), (int)localRectangle2D.getHeight());
              }
            }
            return true;
          }
          if ((!paramBoolean) && (this.graph.getModel().acceptsTarget(localObject2, localObject1)))
          {
            if (this.edge.getTarget() != localCellView)
            {
              this.edgeModified = true;
              if (this.graph.isXorEnabled()) {
                overlay(this.graph.getGraphics());
              }
              this.edge.setTarget(localCellView);
              this.edge.update(this.graph.getGraphLayoutCache());
              if (this.graph.isXorEnabled())
              {
                overlay(this.graph.getGraphics());
              }
              else
              {
                localRectangle2D.add(this.edge.getBounds());
                this.graph.repaint((int)localRectangle2D.getX(), (int)localRectangle2D.getY(), (int)localRectangle2D.getWidth(), (int)localRectangle2D.getHeight());
              }
            }
            return true;
          }
        }
      }
      return false;
    }
    
    public boolean isConstrainedMoveEvent(MouseEvent paramMouseEvent)
    {
      GraphUI localGraphUI = this.graph.getUI();
      if ((localGraphUI instanceof BasicGraphUI)) {
        return ((BasicGraphUI)localGraphUI).isConstrainedMoveEvent(paramMouseEvent);
      }
      return false;
    }
    
    public boolean isAddPointEvent(MouseEvent paramMouseEvent)
    {
      return (paramMouseEvent.isPopupTrigger()) || (SwingUtilities.isRightMouseButton(paramMouseEvent));
    }
    
    public boolean isRemovePointEvent(MouseEvent paramMouseEvent)
    {
      return (paramMouseEvent.isPopupTrigger()) || (SwingUtilities.isRightMouseButton(paramMouseEvent));
    }
    
    protected boolean isSourceEditing()
    {
      return this.source;
    }
    
    protected boolean isTargetEditing()
    {
      return this.target;
    }
    
    protected boolean isEditing()
    {
      return (this.source) || (this.target) || (this.label) || (this.currentLabel >= 0) || (this.currentPoint != null);
    }
    
    public void mouseMoved(MouseEvent paramMouseEvent)
    {
      for (int i = 0; i < this.r.length; i++) {
        if (this.r[i].contains(paramMouseEvent.getPoint()))
        {
          this.graph.setCursor(new Cursor(1));
          paramMouseEvent.consume();
          return;
        }
      }
      if ((this.loc.contains(paramMouseEvent.getPoint())) && (this.graph.isMoveable()) && (GraphConstants.isMoveable(this.edge.getAllAttributes())))
      {
        this.graph.setCursor(new Cursor(12));
        paramMouseEvent.consume();
      }
      if ((this.extraLabelLocations != null) && (this.graph.isMoveable()) && (GraphConstants.isMoveable(this.edge.getAllAttributes()))) {
        for (i = 0; i < this.extraLabelLocations.length; i++) {
          if (this.extraLabelLocations[i].contains(paramMouseEvent.getPoint()))
          {
            this.graph.setCursor(new Cursor(12));
            paramMouseEvent.consume();
          }
        }
      }
    }
    
    public void mousePressed(MouseEvent paramMouseEvent)
    {
      if (!this.edge.isLeaf()) {
        return;
      }
      int i = (this.graph.isBendable()) && (GraphConstants.isBendable(this.edge.getAllAttributes())) ? 1 : 0;
      int j = paramMouseEvent.getX();
      int k = paramMouseEvent.getY();
      int m = 0;
      for (m = 0; m < this.r.length; m++) {
        if (this.r[m].contains(j, k))
        {
          if (EdgeView.LEGACY_DISCONNECTABLE)
          {
            this.currentPoint = this.edge.getPoint(m);
            this.currentIndex = m;
            this.source = (m == 0);
            this.target = (m == this.r.length - 1);
            break;
          }
          if (((m > 0) && (m < this.r.length - 1)) || (GraphConstants.isDisconnectable(this.edge.getAllAttributes())))
          {
            this.currentPoint = this.edge.getPoint(m);
            this.currentIndex = m;
            this.source = (m == 0);
            this.target = (m == this.r.length - 1);
            break;
          }
          paramMouseEvent.consume();
        }
      }
      int n;
      if ((!isEditing()) && (this.graph.isMoveable()) && (GraphConstants.isMoveable(this.edge.getAllAttributes())) && (this.loc != null) && (this.loc.contains(j, k)) && (!isAddPointEvent(paramMouseEvent)) && (!isRemovePointEvent(paramMouseEvent)) && (this.graph.getEdgeLabelsMovable()))
      {
        this.initialLabelLocation = ((Point2D)this.edge.getLabelPosition().clone());
        this.label = true;
      }
      else if ((this.extraLabelLocations != null) && (!isEditing()) && (this.graph.isMoveable()) && (this.graph.getEdgeLabelsMovable()) && (GraphConstants.isMoveable(this.edge.getAllAttributes())))
      {
        for (n = 0; n < this.extraLabelLocations.length; n++) {
          if ((this.extraLabelLocations[n] != null) && (this.extraLabelLocations[n].contains(j, k)))
          {
            this.currentLabel = n;
            this.initialLabelLocation = ((Point2D)this.edge.getExtraLabelPosition(this.currentLabel).clone());
            if (!isRemovePointEvent(paramMouseEvent)) {
              break;
            }
            this.edge.removeExtraLabel(n);
            this.edgeModified = true;
            mouseReleased(paramMouseEvent);
            break;
          }
        }
      }
      if ((isRemovePointEvent(paramMouseEvent)) && (this.currentPoint != null) && (!this.source) && (!this.target) && (i != 0) && ((this.edge.getSource() == null) || (this.currentIndex > 0)) && ((this.edge.getTarget() == null) || (this.currentIndex < this.edge.getPointCount() - 1)))
      {
        this.edge.removePoint(m);
        this.edgeModified = true;
        mouseReleased(paramMouseEvent);
      }
      else if ((isAddPointEvent(paramMouseEvent)) && (!isEditing()) && (i != 0))
      {
        n = this.graph.getHandleSize();
        Rectangle2D localRectangle2D = this.graph.fromScreen(new Rectangle(j - n, k - n, 2 * n, 2 * n));
        if (this.edge.intersects(this.graph, localRectangle2D))
        {
          Point2D localPoint2D1 = this.graph.fromScreen(this.graph.snap(new Point(paramMouseEvent.getPoint())));
          double d1 = Double.MAX_VALUE;
          double d2 = 0.0D;
          for (int i1 = 0; i1 < this.edge.getPointCount() - 1; i1++)
          {
            Point2D localPoint2D2 = this.edge.getPoint(i1);
            Point2D localPoint2D3 = this.edge.getPoint(i1 + 1);
            d2 = new Line2D.Double(localPoint2D2, localPoint2D3).ptSegDistSq(localPoint2D1);
            if (d2 < d1)
            {
              d1 = d2;
              m = i1 + 1;
            }
          }
          this.edge.addPoint(m, localPoint2D1);
          this.edgeModified = true;
          this.currentPoint = localPoint2D1;
          reloadPoints(this.edge);
          paint(this.graph.getGraphics());
        }
      }
      if (isEditing()) {
        paramMouseEvent.consume();
      }
    }
    
    public void mouseDragged(MouseEvent paramMouseEvent)
    {
      Rectangle2D localRectangle2D1 = this.edge.getBounds();
      Object localObject = this.graph.fromScreen(new Point(paramMouseEvent.getPoint()));
      if ((this.label) || (this.currentLabel >= 0))
      {
        Rectangle2D localRectangle2D2 = this.edge.getBounds();
        if (localRectangle2D2 != null)
        {
          this.edgeModified = true;
          if (this.graph.isXorEnabled()) {
            overlay(this.graph.getGraphics());
          }
          if (!GraphConstants.isLabelAlongEdge(this.edge.getAllAttributes()))
          {
            localObject = getRelativeLabelPosition(this.edge, (Point2D)localObject);
          }
          else
          {
            double d1 = ((Point2D)localObject).getX();
            double d2 = ((Point2D)localObject).getY();
            Point2D localPoint2D2 = this.edge.getPoint(0);
            double d4 = localPoint2D2.getX();
            double d6 = localPoint2D2.getY();
            Point2D localPoint2D3 = this.edge.getLabelVector();
            double d7 = localPoint2D3.getX();
            double d8 = localPoint2D3.getY();
            double d9 = localPoint2D2.getX() + d7;
            double d10 = localPoint2D2.getY() + d8;
            double d11 = Math.sqrt(d7 * d7 + d8 * d8);
            if (d11 > 0.0D)
            {
              double d12 = 1000.0D;
              double d13 = d11 * (-d2 * d7 + d6 * d7 + d1 * d8 - d4 * d8) / (-d10 * d8 + d6 * d8 - d7 * d9 + d7 * d4);
              double d14 = d12 * (-d2 * d10 + d2 * d6 + d6 * d10 - d6 * d6 - d9 * d1 + d9 * d4 + d4 * d1 - d4 * d4) / (-d10 * d8 + d6 * d8 - d7 * d9 + d7 * d4);
              localObject = new Point2D.Double(d14, d13);
            }
            else
            {
              localObject = new Point2D.Double(d1 - localPoint2D2.getX(), d2 - localPoint2D2.getY());
            }
          }
          if (this.label) {
            this.edge.setLabelPosition((Point2D)localObject);
          } else {
            this.edge.setExtraLabelPosition(this.currentLabel, (Point2D)localObject);
          }
          this.edge.update(this.graph.getGraphLayoutCache());
          if (this.graph.isXorEnabled()) {
            overlay(this.graph.getGraphics());
          } else {
            this.graph.repaint((int)localRectangle2D1.getX() - 1, (int)localRectangle2D1.getY() - 1, (int)localRectangle2D1.getWidth() + 2, (int)localRectangle2D1.getHeight() + 2);
          }
        }
      }
      else if ((isEditing()) && (this.currentPoint != null))
      {
        int i = ((!this.source) && (!this.target)) || ((this.graph.isDisconnectable()) && (GraphConstants.isDisconnectable(this.orig.getAllAttributes()))) ? 1 : 0;
        if (this.source) {
          i = (i != 0) && (((this.orig.getSource() == null) && (this.orig.getSourceParentView() == null)) || ((this.orig.getSource() != null) && (GraphConstants.isDisconnectable(this.orig.getSource().getParentView().getAllAttributes()))) || ((this.orig.getSourceParentView() != null) && (GraphConstants.isDisconnectable(this.orig.getSourceParentView().getAllAttributes())))) ? 1 : 0;
        }
        if (this.target) {
          i = (i != 0) && (((this.orig.getTarget() == null) && (this.orig.getTargetParentView() == null)) || ((this.orig.getTarget() != null) && (GraphConstants.isDisconnectable(this.orig.getTarget().getParentView().getAllAttributes()))) || ((this.orig.getTargetParentView() != null) && (GraphConstants.isDisconnectable(this.orig.getTargetParentView().getAllAttributes())))) ? 1 : 0;
        }
        if (((!this.source) || (!snap(true, paramMouseEvent.getPoint()))) && ((!this.target) || (!snap(false, paramMouseEvent.getPoint()))) && (i != 0))
        {
          int j = (this.source) && ((this.graph.getModel().acceptsSource(this.edge.getCell(), null)) || (this.graph.isPreviewInvalidNullPorts())) ? 1 : 0;
          int k = (this.target) && ((this.graph.getModel().acceptsTarget(this.edge.getCell(), null)) || (this.graph.isPreviewInvalidNullPorts())) ? 1 : 0;
          if ((j != 0) || (k != 0) || ((!this.source) && (!this.target)))
          {
            this.edgeModified = true;
            if (this.edge.getSource() != null) {
              localRectangle2D1.add(this.edge.getSource().getParentView().getBounds());
            }
            if (this.edge.getTarget() != null) {
              localRectangle2D1.add(this.edge.getTarget().getParentView().getBounds());
            }
            if (this.graph.isXorEnabled()) {
              overlay(this.graph.getGraphics());
            }
            localObject = this.graph.fromScreen(this.graph.snap(new Point(paramMouseEvent.getPoint())));
            if ((isConstrainedMoveEvent(paramMouseEvent)) && (this.currentIndex >= 0))
            {
              EdgeView localEdgeView = (EdgeView)this.graph.getGraphLayoutCache().getMapping(this.edge.getCell(), false);
              Point2D localPoint2D1 = localEdgeView.getPoint(this.currentIndex);
              double d3 = ((Point2D)localObject).getX() - localPoint2D1.getX();
              double d5 = ((Point2D)localObject).getY() - localPoint2D1.getY();
              if (Math.abs(d3) < Math.abs(d5)) {
                ((Point2D)localObject).setLocation(localPoint2D1.getX(), ((Point2D)localObject).getY());
              } else {
                ((Point2D)localObject).setLocation(((Point2D)localObject).getX(), localPoint2D1.getY());
              }
            }
            ((Point2D)localObject).setLocation(Math.max(0.0D, ((Point2D)localObject).getX()), Math.max(0.0D, ((Point2D)localObject).getY()));
            this.currentPoint.setLocation((Point2D)localObject);
            if (this.source)
            {
              this.edge.setPoint(0, (Point2D)localObject);
              this.edge.setSource(null);
            }
            else if (this.target)
            {
              this.edge.setPoint(this.edge.getPointCount() - 1, (Point2D)localObject);
              this.edge.setTarget(null);
            }
            this.edge.update(this.graph.getGraphLayoutCache());
            localRectangle2D1.add(this.edge.getBounds());
            if (this.graph.isXorEnabled())
            {
              overlay(this.graph.getGraphics());
            }
            else
            {
              if (this.edge.getSource() != null) {
                localRectangle2D1.add(this.edge.getSource().getParentView().getBounds());
              }
              if (this.edge.getTarget() != null) {
                localRectangle2D1.add(this.edge.getTarget().getParentView().getBounds());
              }
              localRectangle2D1 = this.graph.toScreen((Rectangle2D)localRectangle2D1.clone());
              this.graph.repaint((int)localRectangle2D1.getX(), (int)localRectangle2D1.getY(), (int)localRectangle2D1.getWidth(), (int)localRectangle2D1.getHeight());
            }
          }
        }
        else if (!this.graph.isXorEnabled())
        {
          localRectangle2D1.add(this.edge.getBounds());
          localRectangle2D1 = this.graph.toScreen((Rectangle2D)localRectangle2D1.clone());
          this.graph.repaint((int)localRectangle2D1.getX(), (int)localRectangle2D1.getY(), (int)localRectangle2D1.getWidth(), (int)localRectangle2D1.getHeight());
        }
      }
    }
    
    protected Point2D getRelativeLabelPosition(EdgeView paramEdgeView, Point2D paramPoint2D)
    {
      int i = paramEdgeView.getPointCount();
      double d1 = 0.0D;
      double[] arrayOfDouble = new double[i];
      Point2D localPoint2D1 = paramEdgeView.getPoint(0);
      Object localObject1 = localPoint2D1;
      for (int j = 1; j < i; j++)
      {
        localObject2 = paramEdgeView.getPoint(j);
        if (localObject2 != null)
        {
          d2 = ((Point2D)localObject1).getX() - ((Point2D)localObject2).getX();
          double d3 = ((Point2D)localObject1).getY() - ((Point2D)localObject2).getY();
          double d5 = Math.sqrt(d2 * d2 + d3 * d3);
          arrayOfDouble[(j - 1)] = d5;
          d1 += d5;
          localObject1 = localObject2;
        }
      }
      Point2D localPoint2D2 = paramEdgeView.getPoint(1);
      Object localObject2 = new Line2D.Double(localPoint2D1, localPoint2D2);
      double d2 = ((Line2D)localObject2).ptSegDistSq(paramPoint2D);
      int k = 0;
      double d4 = 0.0D;
      double d6 = 0.0D;
      for (int m = 2; m < i; m++)
      {
        d4 += arrayOfDouble[(m - 2)];
        localObject2 = new Line2D.Double(paramEdgeView.getPoint(m), localPoint2D2);
        double d8 = ((Line2D)localObject2).ptSegDistSq(paramPoint2D);
        if (d8 < d2)
        {
          d2 = d8;
          k = m - 1;
          d6 = d4;
        }
        localPoint2D2 = paramEdgeView.getPoint(m);
      }
      double d7 = arrayOfDouble[k];
      localObject1 = paramEdgeView.getPoint(k);
      double d9 = ((Point2D)localObject1).getX();
      double d10 = ((Point2D)localObject1).getY();
      Point2D localPoint2D3 = paramEdgeView.getPoint(k + 1);
      double d11 = localPoint2D3.getX();
      double d12 = localPoint2D3.getY();
      double d13 = paramPoint2D.getX();
      double d14 = paramPoint2D.getY();
      double d15 = d9 - d11;
      double d16 = d10 - d12;
      d13 -= d11;
      d14 -= d12;
      double d17 = 0.0D;
      d13 = d15 - d13;
      d14 = d16 - d14;
      double d18 = d13 * d15 + d14 * d16;
      if (d18 <= 0.0D) {
        d17 = 0.0D;
      } else {
        d17 = d18 * d18 / (d15 * d15 + d16 * d16);
      }
      double d19 = Math.sqrt(d17);
      if (d19 > d7) {
        d19 = d7;
      }
      double d20 = Line2D.ptLineDist(localPoint2D3.getX(), localPoint2D3.getY(), ((Point2D)localObject1).getX(), ((Point2D)localObject1).getY(), paramPoint2D.getX(), paramPoint2D.getY());
      int n = Line2D.relativeCCW(localPoint2D3.getX(), localPoint2D3.getY(), ((Point2D)localObject1).getX(), ((Point2D)localObject1).getY(), paramPoint2D.getX(), paramPoint2D.getY());
      if (n == -1) {
        d20 = -d20;
      }
      Point2D.Double localDouble1 = new Point2D.Double(((d1 / 2.0D - d6 - d19) / d1 * -2.0D + 1.0D) * 1000.0D / 2.0D, d20);
      Point2D localPoint2D4 = paramEdgeView.convertRelativeLabelPositionToAbsolute(localDouble1);
      if (paramPoint2D.equals(localPoint2D4))
      {
        GraphConstants.setRemoveAttributes(paramEdgeView.getAllAttributes(), new Object[] { "offset" });
        paramEdgeView.getAllAttributes().remove("offset");
      }
      else
      {
        Point2D.Double localDouble2 = new Point2D.Double(paramPoint2D.getX() - localPoint2D4.getX(), paramPoint2D.getY() - localPoint2D4.getY());
        GraphConstants.setOffset(paramEdgeView.getAllAttributes(), localDouble2);
      }
      return localDouble1;
    }
    
    public void mouseReleased(MouseEvent paramMouseEvent)
    {
      highlight(this.graph, null);
      boolean bool = (paramMouseEvent.isControlDown()) && (this.graph.isCloneable());
      GraphModel localGraphModel = this.graph.getModel();
      Object localObject1 = this.edge.getSource() != null ? this.edge.getSource().getCell() : null;
      Object localObject2 = this.edge.getTarget() != null ? this.edge.getTarget().getCell() : null;
      Object localObject3;
      if ((this.edgeModified) && (localGraphModel.acceptsSource(this.edge.getCell(), localObject1)) && (localGraphModel.acceptsTarget(this.edge.getCell(), localObject2)))
      {
        if ((bool) && (this.initialLabelLocation != null))
        {
          localObject3 = null;
          localObject4 = null;
          localObject5 = GraphConstants.getExtraLabels(this.edge.getAllAttributes());
          if (this.label)
          {
            localObject4 = (Point2D)this.edge.getLabelPosition().clone();
            localObject3 = this.graph.convertValueToString(this.orig);
            this.edge.setLabelPosition(this.initialLabelLocation);
          }
          else
          {
            localObject4 = (Point2D)this.edge.getExtraLabelPosition(this.currentLabel).clone();
            localObject3 = localObject5[this.currentLabel];
            this.edge.setExtraLabelPosition(this.currentLabel, this.initialLabelLocation);
          }
          this.edge.addExtraLabel((Point2D)localObject4, localObject3);
          this.edge.update(this.graph.getGraphLayoutCache());
          bool = false;
        }
        localObject3 = createConnectionSet(this.edge, bool);
        Object localObject4 = GraphConstants.createAttributes(new CellView[] { this.edge }, null);
        Object localObject5 = (Map)((Map)localObject4).get(this.edge.getCell());
        List localList1 = GraphConstants.getPoints((Map)localObject5);
        List localList2 = this.edge.getPoints();
        if (localList1 != localList2)
        {
          localList1.set(0, this.edge.getPoint(0));
          localList1.set(localList1.size() - 1, this.edge.getPoint(this.edge.getPointCount() - 1));
        }
        if (bool)
        {
          Map localMap = this.graph.cloneCells(this.graph.getDescendants(new Object[] { this.edge.getCell() }));
          processNestedMap((Map)localObject4, true);
          localObject4 = GraphConstants.replaceKeys(localMap, (Map)localObject4);
          localObject3 = ((ConnectionSet)localObject3).clone(localMap);
          Object[] arrayOfObject = localMap.values().toArray();
          this.graph.getGraphLayoutCache().insert(arrayOfObject, (Map)localObject4, (ConnectionSet)localObject3, null, null);
        }
        else
        {
          processNestedMap((Map)localObject4, false);
          this.graph.getGraphLayoutCache().edit((Map)localObject4, (ConnectionSet)localObject3, null, null);
        }
      }
      else
      {
        if (this.graph.isXorEnabled())
        {
          overlay(this.graph.getGraphics());
        }
        else
        {
          localObject3 = this.edge.getBounds();
          this.graph.repaint((int)((Rectangle2D)localObject3).getX(), (int)((Rectangle2D)localObject3).getY(), (int)((Rectangle2D)localObject3).getWidth(), (int)((Rectangle2D)localObject3).getHeight());
        }
        this.edge.refresh(this.graph.getGraphLayoutCache(), this.graph.getGraphLayoutCache(), false);
      }
      this.initialLabelLocation = null;
      this.currentPoint = null;
      this.edgeModified = false;
      this.label = false;
      this.source = false;
      this.target = false;
      this.currentLabel = -1;
      this.currentIndex = -1;
      this.firstOverlayCall = true;
      paramMouseEvent.consume();
    }
    
    protected void processNestedMap(Map paramMap, boolean paramBoolean) {}
    
    protected ConnectionSet createConnectionSet(EdgeView paramEdgeView, boolean paramBoolean)
    {
      Object localObject1 = paramEdgeView.getCell();
      GraphModel localGraphModel = this.graph.getModel();
      ConnectionSet localConnectionSet = new ConnectionSet();
      Object localObject2 = null;
      Object localObject3 = null;
      if (paramEdgeView.getSource() != null) {
        localObject2 = paramEdgeView.getSource().getCell();
      } else if (paramEdgeView.getSourceParentView() != null) {
        localObject2 = localGraphModel.getSource(localObject1);
      }
      if (paramEdgeView.getTarget() != null) {
        localObject3 = paramEdgeView.getTarget().getCell();
      } else if (paramEdgeView.getTargetParentView() != null) {
        localObject3 = localGraphModel.getTarget(localObject1);
      }
      if (paramEdgeView.getTarget() != null) {
        localObject3 = paramEdgeView.getTarget().getCell();
      }
      if ((paramBoolean) || ((localObject2 != localGraphModel.getSource(localObject1)) && (this.source))) {
        localConnectionSet.connect(localObject1, localObject2, true);
      }
      if ((paramBoolean) || ((localObject3 != localGraphModel.getTarget(localObject1)) && (this.target))) {
        localConnectionSet.connect(localObject1, localObject3, false);
      }
      return localConnectionSet;
    }
    
    protected void invalidate()
    {
      EdgeView localEdgeView = this.relevantEdge;
      int i = this.graph.getHandleSize();
      EdgeRenderer localEdgeRenderer = (EdgeRenderer)this.edge.getRenderer();
      Point2D localPoint2D1 = localEdgeRenderer.getLabelPosition(localEdgeView);
      Point2D localPoint2D2 = null;
      if (localPoint2D1 != null)
      {
        localPoint2D2 = (Point2D)localPoint2D1.clone();
        this.graph.toScreen(localPoint2D2);
      }
      Dimension localDimension = localEdgeRenderer.getLabelSize(localEdgeView, this.graph.convertValueToString(localEdgeView));
      if ((localPoint2D2 != null) && (localDimension != null))
      {
        Point2D localPoint2D3 = this.graph.toScreen(new Point2D.Double(localDimension.width, localDimension.height));
        this.loc.setFrame(localPoint2D2.getX() - localPoint2D3.getX() / 2.0D, localPoint2D2.getY() - localPoint2D3.getY() / 2.0D, localPoint2D3.getX(), localPoint2D3.getY());
      }
      for (int j = 0; j < this.r.length; j++)
      {
        localPoint2D2 = localEdgeView.getPoint(j);
        localPoint2D2 = this.graph.toScreen(new Point2D.Double(localPoint2D2.getX(), localPoint2D2.getY()));
        this.r[j].setFrame(localPoint2D2.getX() - i, localPoint2D2.getY() - i, 2 * i, 2 * i);
      }
      if (this.extraLabelLocations != null) {
        for (j = 0; j < this.extraLabelLocations.length; j++)
        {
          localPoint2D2 = localEdgeRenderer.getExtraLabelPosition(localEdgeView, j);
          if (localPoint2D2 != null)
          {
            localPoint2D2 = this.graph.toScreen((Point2D)localPoint2D2.clone());
            localDimension = localEdgeRenderer.getExtraLabelSize(this.graph, localEdgeView, j);
            if (localDimension != null)
            {
              Point2D localPoint2D4 = this.graph.toScreen(new Point2D.Double(localDimension.width, localDimension.height));
              this.extraLabelLocations[j].setFrame(localPoint2D2.getX() - localPoint2D4.getX() / 2.0D, localPoint2D2.getY() - localPoint2D4.getY() / 2.0D, localPoint2D4.getX(), localPoint2D4.getY());
            }
          }
        }
      }
    }
  }
}


/* Location:              /home/menick/Scrivania/ProcessMining/CNMining/CNMining.jar!/org/jgraph/graph/EdgeView.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */