package org.jgraph.graph;

import java.awt.Component;
import java.awt.geom.Point2D;
import java.awt.geom.Point2D.Double;
import java.awt.geom.Rectangle2D;
import java.awt.geom.Rectangle2D.Double;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Stack;
import javax.swing.tree.DefaultMutableTreeNode;
import org.jgraph.JGraph;

public abstract class AbstractCellView
  implements CellView, Serializable
{
  public static transient GraphCellEditor cellEditor;
  protected Object cell = null;
  protected CellView parent = null;
  protected List childViews = new ArrayList(0);
  protected AttributeMap allAttributes = createAttributeMap();
  protected AttributeMap attributes = this.allAttributes;
  protected transient Rectangle2D groupBounds = VertexView.defaultBounds;
  
  public AbstractCellView() {}
  
  public AbstractCellView(Object paramObject)
  {
    setCell(paramObject);
  }
  
  protected AttributeMap createAttributeMap()
  {
    return new AttributeMap();
  }
  
  public Object getCell()
  {
    return this.cell;
  }
  
  public void setCell(Object paramObject)
  {
    this.cell = paramObject;
  }
  
  public void refresh(GraphLayoutCache paramGraphLayoutCache, CellMapper paramCellMapper, boolean paramBoolean)
  {
    GraphModel localGraphModel = paramGraphLayoutCache.getModel();
    this.allAttributes = getCellAttributes(localGraphModel);
    Object localObject2;
    if ((paramCellMapper != null) && (localGraphModel != null))
    {
      Object localObject1 = localGraphModel.getParent(this.cell);
      localObject2 = paramCellMapper.getMapping(localObject1, paramBoolean);
      if (localObject2 != this.parent) {
        removeFromParent();
      }
      this.parent = ((CellView)localObject2);
    }
    update(paramGraphLayoutCache);
    this.childViews.clear();
    for (int i = 0; i < localGraphModel.getChildCount(this.cell); i++)
    {
      localObject2 = localGraphModel.getChild(this.cell, i);
      CellView localCellView = paramCellMapper.getMapping(localObject2, paramBoolean);
      if ((!localGraphModel.isPort(localObject2)) && (localCellView != null)) {
        this.childViews.add(localCellView);
      }
    }
  }
  
  protected AttributeMap getCellAttributes(GraphModel paramGraphModel)
  {
    return (AttributeMap)paramGraphModel.getAttributes(this.cell).clone();
  }
  
  public void update(GraphLayoutCache paramGraphLayoutCache)
  {
    mergeAttributes();
    this.groupBounds = null;
    childUpdated();
  }
  
  protected void mergeAttributes()
  {
    this.allAttributes.putAll(this.attributes);
  }
  
  public void childUpdated()
  {
    if (this.parent != null) {
      this.parent.childUpdated();
    }
    this.groupBounds = null;
  }
  
  public CellView getParentView()
  {
    return this.parent;
  }
  
  public CellView[] getChildViews()
  {
    CellView[] arrayOfCellView = new CellView[this.childViews.size()];
    this.childViews.toArray(arrayOfCellView);
    return arrayOfCellView;
  }
  
  public static CellView[] getDescendantViews(CellView[] paramArrayOfCellView)
  {
    Stack localStack = new Stack();
    for (int i = 0; i < paramArrayOfCellView.length; i++) {
      localStack.add(paramArrayOfCellView[i]);
    }
    ArrayList localArrayList = new ArrayList();
    while (!localStack.isEmpty())
    {
      localObject = (CellView)localStack.pop();
      CellView[] arrayOfCellView = ((CellView)localObject).getChildViews();
      for (int j = 0; j < arrayOfCellView.length; j++) {
        localStack.add(arrayOfCellView[j]);
      }
      localArrayList.add(localObject);
    }
    Object localObject = new CellView[localArrayList.size()];
    localArrayList.toArray((Object[])localObject);
    return (CellView[])localObject;
  }
  
  public void removeFromParent()
  {
    if ((this.parent instanceof AbstractCellView))
    {
      List localList = ((AbstractCellView)this.parent).childViews;
      localList.remove(this);
    }
  }
  
  public boolean isLeaf()
  {
    return this.childViews.isEmpty();
  }
  
  public AttributeMap getAttributes()
  {
    return this.attributes;
  }
  
  public void setAttributes(AttributeMap paramAttributeMap)
  {
    this.attributes = paramAttributeMap;
  }
  
  public AttributeMap getAllAttributes()
  {
    return this.allAttributes;
  }
  
  public Map changeAttributes(GraphLayoutCache paramGraphLayoutCache, Map paramMap)
  {
    if (paramMap != null)
    {
      AttributeMap localAttributeMap = this.attributes.applyMap(paramMap);
      update(paramGraphLayoutCache);
      return localAttributeMap;
    }
    return null;
  }
  
  public Rectangle2D getBounds()
  {
    if (!isLeaf())
    {
      if (this.groupBounds == null) {
        updateGroupBounds();
      }
      return this.groupBounds;
    }
    return null;
  }
  
  public static Rectangle2D getBounds(CellView[] paramArrayOfCellView)
  {
    if ((paramArrayOfCellView != null) && (paramArrayOfCellView.length > 0))
    {
      Rectangle2D.Double localDouble = null;
      for (int i = 0; i < paramArrayOfCellView.length; i++) {
        if (paramArrayOfCellView[i] != null)
        {
          Rectangle2D localRectangle2D = paramArrayOfCellView[i].getBounds();
          if (localRectangle2D != null) {
            if (localDouble == null) {
              localDouble = new Rectangle2D.Double(localRectangle2D.getX(), localRectangle2D.getY(), localRectangle2D.getWidth(), localRectangle2D.getHeight());
            } else {
              Rectangle2D.union(localDouble, localRectangle2D, localDouble);
            }
          }
        }
      }
      return localDouble;
    }
    return null;
  }
  
  public void setBounds(Rectangle2D paramRectangle2D)
  {
    Object localObject = getBounds();
    if (localObject == null) {
      localObject = new Rectangle2D.Double();
    }
    Point2D.Double localDouble1 = new Point2D.Double(((Rectangle2D)localObject).getX(), ((Rectangle2D)localObject).getY());
    Point2D.Double localDouble2 = new Point2D.Double(paramRectangle2D.getX(), paramRectangle2D.getY());
    Rectangle2D.Double localDouble = new Rectangle2D.Double(paramRectangle2D.getX(), paramRectangle2D.getY(), paramRectangle2D.getWidth(), paramRectangle2D.getHeight());
    if ((GraphConstants.isMoveable(getAllAttributes())) && (!localDouble2.equals(localDouble1))) {
      translate(localDouble2.getX() - localDouble1.getX(), localDouble2.getY() - localDouble1.getY());
    } else {
      localDouble.setFrame(localDouble.getX(), localDouble.getY(), paramRectangle2D.getWidth() - localDouble2.getX() + localDouble1.getX(), paramRectangle2D.getHeight() - localDouble2.getY() + localDouble1.getY());
    }
    double d1 = localDouble.getWidth();
    double d2 = localDouble.getHeight();
    double d3 = ((Rectangle2D)localObject).getWidth();
    double d4 = ((Rectangle2D)localObject).getHeight();
    if (((d1 != d3) || (d2 != d4)) && (d3 > 0.0D) && (d4 > 0.0D)) {
      scale(d1 / d3, d2 / d4, localDouble2);
    }
  }
  
  protected void updateGroupBounds()
  {
    CellView[] arrayOfCellView = getChildViews();
    LinkedList localLinkedList = new LinkedList();
    for (int i = 0; i < arrayOfCellView.length; i++) {
      if (includeInGroupBounds(arrayOfCellView[i])) {
        localLinkedList.add(arrayOfCellView[i]);
      }
    }
    arrayOfCellView = new CellView[localLinkedList.size()];
    localLinkedList.toArray(arrayOfCellView);
    Rectangle2D localRectangle2D = getBounds(arrayOfCellView);
    int j = GraphConstants.getInset(getAllAttributes());
    if (localRectangle2D != null) {
      localRectangle2D.setFrame(localRectangle2D.getX() - j, localRectangle2D.getY() - j, localRectangle2D.getWidth() + 2 * j, localRectangle2D.getHeight() + 2 * j);
    }
    this.groupBounds = localRectangle2D;
  }
  
  protected boolean includeInGroupBounds(CellView paramCellView)
  {
    if (((paramCellView instanceof EdgeView)) && ((getCell() instanceof DefaultMutableTreeNode)))
    {
      EdgeView localEdgeView = (EdgeView)paramCellView;
      if ((localEdgeView.getCell() instanceof DefaultMutableTreeNode))
      {
        DefaultMutableTreeNode localDefaultMutableTreeNode1 = (DefaultMutableTreeNode)localEdgeView.getCell();
        Object localObject1 = null;
        if ((localEdgeView.getSource() != null) && (localEdgeView.getSource().getParentView() != null)) {
          localObject1 = localEdgeView.getSource().getParentView().getCell();
        } else if (localEdgeView.getSourceParentView() != null) {
          localObject1 = localEdgeView.getSourceParentView().getCell();
        }
        if ((localObject1 instanceof DefaultMutableTreeNode))
        {
          localObject2 = (DefaultMutableTreeNode)localObject1;
          if (((DefaultMutableTreeNode)localObject2).isNodeDescendant(localDefaultMutableTreeNode1)) {
            return false;
          }
        }
        Object localObject2 = null;
        if ((localEdgeView.getTarget() != null) && (localEdgeView.getTarget().getParentView() != null)) {
          localObject2 = localEdgeView.getTarget().getParentView().getCell();
        } else if (localEdgeView.getTargetParentView() != null) {
          localObject2 = localEdgeView.getTargetParentView().getCell();
        }
        if ((localObject2 instanceof DefaultMutableTreeNode))
        {
          DefaultMutableTreeNode localDefaultMutableTreeNode2 = (DefaultMutableTreeNode)localObject2;
          if (localDefaultMutableTreeNode2.isNodeDescendant(localDefaultMutableTreeNode1)) {
            return false;
          }
        }
      }
    }
    return true;
  }
  
  public void translate(double paramDouble1, double paramDouble2)
  {
    if (isLeaf())
    {
      getAllAttributes().translate(paramDouble1, paramDouble2);
    }
    else
    {
      int i = GraphConstants.getMoveableAxis(getAllAttributes());
      if (i == 1) {
        paramDouble2 = 0.0D;
      } else if (i == 2) {
        paramDouble1 = 0.0D;
      }
      Iterator localIterator = this.childViews.iterator();
      while (localIterator.hasNext())
      {
        Object localObject = localIterator.next();
        if ((localObject instanceof AbstractCellView))
        {
          AbstractCellView localAbstractCellView = (AbstractCellView)localObject;
          localAbstractCellView.translate(paramDouble1, paramDouble2);
        }
      }
    }
  }
  
  public void scale(double paramDouble1, double paramDouble2, Point2D paramPoint2D)
  {
    if (isLeaf())
    {
      getAttributes().scale(paramDouble1, paramDouble2, paramPoint2D);
    }
    else
    {
      int i = GraphConstants.getSizeableAxis(getAllAttributes());
      if (i == 1) {
        paramDouble2 = 1.0D;
      } else if (i == 2) {
        paramDouble1 = 1.0D;
      }
      Iterator localIterator = this.childViews.iterator();
      while (localIterator.hasNext())
      {
        Object localObject = localIterator.next();
        if ((localObject instanceof AbstractCellView))
        {
          AbstractCellView localAbstractCellView = (AbstractCellView)localObject;
          AttributeMap localAttributeMap = localAbstractCellView.getAttributes();
          if ((GraphConstants.isSizeable(localAttributeMap)) || (GraphConstants.isAutoSize(localAttributeMap))) {
            localAbstractCellView.scale(paramDouble1, paramDouble2, paramPoint2D);
          }
        }
      }
    }
  }
  
  public boolean intersects(JGraph paramJGraph, Rectangle2D paramRectangle2D)
  {
    Object localObject;
    if ((isLeaf()) || (GraphConstants.isGroupOpaque(getAllAttributes())))
    {
      localObject = getBounds();
      if (localObject != null) {
        return ((Rectangle2D)localObject).intersects(paramRectangle2D);
      }
    }
    else
    {
      localObject = this.childViews.iterator();
      while (((Iterator)localObject).hasNext()) {
        if (((CellView)((Iterator)localObject).next()).intersects(paramJGraph, paramRectangle2D)) {
          return true;
        }
      }
    }
    return false;
  }
  
  public Component getRendererComponent(JGraph paramJGraph, boolean paramBoolean1, boolean paramBoolean2, boolean paramBoolean3)
  {
    CellViewRenderer localCellViewRenderer = getRenderer();
    if (localCellViewRenderer != null) {
      return localCellViewRenderer.getRendererComponent(paramJGraph, this, paramBoolean1, paramBoolean2, paramBoolean3);
    }
    return null;
  }
  
  public abstract CellViewRenderer getRenderer();
  
  public abstract CellHandle getHandle(GraphContext paramGraphContext);
  
  public GraphCellEditor getEditor()
  {
    return cellEditor;
  }
  
  public static Point2D getCenterPoint(CellView paramCellView)
  {
    Rectangle2D localRectangle2D = paramCellView.getBounds();
    if (localRectangle2D != null) {
      return new Point2D.Double(localRectangle2D.getCenterX(), localRectangle2D.getCenterY());
    }
    return null;
  }
  
  public Point2D getPerimeterPoint(EdgeView paramEdgeView, Point2D paramPoint2D1, Point2D paramPoint2D2)
  {
    return getCenterPoint(this);
  }
  
  static
  {
    try
    {
      cellEditor = new DefaultGraphCellEditor();
    }
    catch (Error localError) {}
  }
}


/* Location:              /home/menick/Scrivania/ProcessMining/CNMining/CNMining.jar!/org/jgraph/graph/AbstractCellView.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */