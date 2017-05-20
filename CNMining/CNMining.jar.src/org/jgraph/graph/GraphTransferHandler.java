package org.jgraph.graph;

import java.awt.Point;
import java.awt.datatransfer.DataFlavor;
import java.awt.datatransfer.Transferable;
import java.awt.event.MouseEvent;
import java.awt.geom.Point2D;
import java.awt.geom.Point2D.Double;
import java.awt.geom.Rectangle2D;
import java.awt.geom.Rectangle2D.Double;
import java.util.Hashtable;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;
import javax.swing.JComponent;
import javax.swing.TransferHandler;
import org.jgraph.JGraph;
import org.jgraph.plaf.GraphUI;

public class GraphTransferHandler
  extends TransferHandler
{
  protected boolean alwaysReceiveAsCopyAction = false;
  protected Object out;
  protected Object in;
  protected int inCount = 0;
  
  public boolean canImport(JComponent paramJComponent, DataFlavor[] paramArrayOfDataFlavor)
  {
    for (int i = 0; i < paramArrayOfDataFlavor.length; i++) {
      if (paramArrayOfDataFlavor[i] == GraphTransferable.dataFlavor) {
        return true;
      }
    }
    return false;
  }
  
  public Transferable createTransferableForGraph(JGraph paramJGraph)
  {
    return createTransferable(paramJGraph);
  }
  
  protected Transferable createTransferable(JComponent paramJComponent)
  {
    if ((paramJComponent instanceof JGraph))
    {
      JGraph localJGraph = (JGraph)paramJComponent;
      if (!localJGraph.isSelectionEmpty()) {
        return createTransferable(localJGraph, localJGraph.getSelectionCells());
      }
    }
    return null;
  }
  
  protected Transferable createTransferable(JGraph paramJGraph, Object[] paramArrayOfObject)
  {
    Object[] arrayOfObject = paramJGraph.getDescendants(paramJGraph.order(paramArrayOfObject));
    ParentMap localParentMap = ParentMap.create(paramJGraph.getModel(), arrayOfObject, false, true);
    ConnectionSet localConnectionSet = ConnectionSet.create(paramJGraph.getModel(), arrayOfObject, false);
    Map localMap = GraphConstants.createAttributes(arrayOfObject, paramJGraph.getGraphLayoutCache());
    Object localObject = paramJGraph.getCellBounds(paramJGraph.getSelectionCells());
    localObject = new AttributeMap.SerializableRectangle2D(((Rectangle2D)localObject).getX(), ((Rectangle2D)localObject).getY(), ((Rectangle2D)localObject).getWidth(), ((Rectangle2D)localObject).getHeight());
    this.out = arrayOfObject;
    return create(paramJGraph, arrayOfObject, localMap, (Rectangle2D)localObject, localConnectionSet, localParentMap);
  }
  
  protected GraphTransferable create(JGraph paramJGraph, Object[] paramArrayOfObject, Map paramMap, Rectangle2D paramRectangle2D, ConnectionSet paramConnectionSet, ParentMap paramParentMap)
  {
    return new GraphTransferable(paramArrayOfObject, paramMap, paramRectangle2D, paramConnectionSet, paramParentMap);
  }
  
  protected void exportDone(JComponent paramJComponent, Transferable paramTransferable, int paramInt)
  {
    if (((paramJComponent instanceof JGraph)) && ((paramTransferable instanceof GraphTransferable)))
    {
      JGraph localJGraph = (JGraph)paramJComponent;
      if (paramInt == 2)
      {
        Object[] arrayOfObject = ((GraphTransferable)paramTransferable).getCells();
        localJGraph.getGraphLayoutCache().remove(arrayOfObject);
      }
      localJGraph.getUI().updateHandle();
      localJGraph.getUI().setInsertionLocation(null);
    }
  }
  
  public int getSourceActions(JComponent paramJComponent)
  {
    return 3;
  }
  
  public boolean importData(JComponent paramJComponent, Transferable paramTransferable)
  {
    try
    {
      if ((paramJComponent instanceof JGraph))
      {
        JGraph localJGraph = (JGraph)paramJComponent;
        GraphModel localGraphModel = localJGraph.getModel();
        GraphLayoutCache localGraphLayoutCache = localJGraph.getGraphLayoutCache();
        if ((paramTransferable.isDataFlavorSupported(GraphTransferable.dataFlavor)) && (localJGraph.isEnabled()))
        {
          Point localPoint = localJGraph.getUI().getInsertionLocation();
          Object localObject1 = paramTransferable.getTransferData(GraphTransferable.dataFlavor);
          GraphTransferable localGraphTransferable = (GraphTransferable)localObject1;
          Object[] arrayOfObject = localGraphTransferable.getCells();
          int i = 1;
          for (int j = 0; (j < arrayOfObject.length) && (i != 0); j++) {
            i = (i != 0) && (localGraphModel.contains(arrayOfObject[j])) ? 1 : 0;
          }
          if (this.in == arrayOfObject) {
            this.inCount += 1;
          } else {
            this.inCount = (i != 0 ? 1 : 0);
          }
          this.in = arrayOfObject;
          if ((localPoint != null) && (this.in == this.out) && (localJGraph.getUI().getHandle() != null))
          {
            j = localJGraph.getUI().getDropAction() == 1 ? 2 : 0;
            localJGraph.getUI().getHandle().mouseReleased(new MouseEvent(paramJComponent, 0, 0L, j, localPoint.x, localPoint.y, 1, false));
            return false;
          }
          Rectangle2D localRectangle2D = localGraphTransferable.getBounds();
          Object localObject2 = localGraphTransferable.getAttributeMap();
          ConnectionSet localConnectionSet = localGraphTransferable.getConnectionSet();
          ParentMap localParentMap = localGraphTransferable.getParentMap();
          if ((i == 0) || (localPoint == null) || (this.alwaysReceiveAsCopyAction) || (localJGraph.getUI().getDropAction() == 1))
          {
            double d1 = 0.0D;
            double d3 = 0.0D;
            if (localObject2 != null)
            {
              Point2D localPoint2D2;
              if ((localPoint != null) && (localRectangle2D != null))
              {
                localPoint2D2 = localJGraph.fromScreen(localJGraph.snap((Point2D)localPoint.clone()));
                d1 = localPoint2D2.getX() - localRectangle2D.getX();
                d3 = localPoint2D2.getY() - localRectangle2D.getY();
              }
              else
              {
                localPoint2D2 = getInsertionOffset(localJGraph, this.inCount, localRectangle2D);
                if (localPoint2D2 != null)
                {
                  d1 = localPoint2D2.getX();
                  d3 = localPoint2D2.getY();
                }
              }
            }
            handleExternalDrop(localJGraph, arrayOfObject, (Map)localObject2, localConnectionSet, localParentMap, d1, d3);
            return (localJGraph.getUI().getDropAction() == 2) && (i == 0);
          }
          if (localPoint != null)
          {
            Point2D localPoint2D1 = localJGraph.fromScreen(localJGraph.snap(new Point(localPoint)));
            double d2;
            if ((localRectangle2D != null) && (localObject2 != null))
            {
              d2 = localPoint2D1.getX() - localRectangle2D.getX();
              double d4 = localPoint2D1.getY() - localRectangle2D.getY();
              AttributeMap.translate(((Map)localObject2).values(), d2, d4);
            }
            else if (localRectangle2D == null)
            {
              d2 = 2.0D * localJGraph.getGridSize();
              localObject2 = new Hashtable();
              Hashtable localHashtable1 = new Hashtable();
              for (int k = 0; k < arrayOfObject.length; k++) {
                if ((!localGraphModel.isEdge(arrayOfObject[k])) && (!localGraphModel.isPort(arrayOfObject[k])))
                {
                  Object localObject3 = localJGraph.getCellBounds(arrayOfObject[k]);
                  if (localObject3 == null) {
                    localObject3 = GraphConstants.getBounds(localGraphModel.getAttributes(arrayOfObject[k]));
                  }
                  if (localObject3 != null) {
                    localObject3 = (Rectangle2D)((Rectangle2D)localObject3).clone();
                  }
                  Hashtable localHashtable2 = new Hashtable();
                  Object localObject4 = localGraphModel.getParent(arrayOfObject[k]);
                  if (localObject3 == null)
                  {
                    localObject3 = new Rectangle2D.Double(localPoint.getX(), localPoint.getY(), d2 / 2.0D, d2);
                    GraphConstants.setResize(localHashtable2, true);
                    localPoint.setLocation(localPoint.getX() + d2, localPoint.getY() + d2);
                    localJGraph.snap(localPoint);
                  }
                  else if ((localObject4 == null) || (!((Map)localObject2).keySet().contains(localGraphModel.getParent(arrayOfObject[k]))))
                  {
                    CellView localCellView = localJGraph.getGraphLayoutCache().getMapping(arrayOfObject[k], false);
                    if ((localCellView != null) && (!localCellView.isLeaf()))
                    {
                      double d5 = localPoint.getX() - ((Rectangle2D)localObject3).getX();
                      double d6 = localPoint.getY() - ((Rectangle2D)localObject3).getY();
                      GraphLayoutCache.translateViews(new CellView[] { localCellView }, d5, d6);
                    }
                    else
                    {
                      ((Rectangle2D)localObject3).setFrame(localPoint.getX(), localPoint.getY(), ((Rectangle2D)localObject3).getWidth(), ((Rectangle2D)localObject3).getHeight());
                    }
                    localPoint.setLocation(localPoint.getX() + d2, localPoint.getY() + d2);
                    localJGraph.snap(localPoint);
                  }
                  GraphConstants.setBounds(localHashtable2, (Rectangle2D)localObject3);
                  ((Map)localObject2).put(arrayOfObject[k], localHashtable2);
                }
                else
                {
                  ((Map)localObject2).put(arrayOfObject[k], localHashtable1);
                }
              }
            }
            localGraphLayoutCache.edit((Map)localObject2, null, null, null);
          }
          localJGraph.setSelectionCells(DefaultGraphModel.getTopmostCells(localGraphModel, arrayOfObject));
          return false;
        }
        return importDataImpl(paramJComponent, paramTransferable);
      }
    }
    catch (Exception localException)
    {
      localException.printStackTrace();
    }
    return false;
  }
  
  protected Point2D getInsertionOffset(JGraph paramJGraph, int paramInt, Rectangle2D paramRectangle2D)
  {
    Point2D.Double localDouble = null;
    if (paramJGraph != null) {
      localDouble = new Point2D.Double(paramInt * paramJGraph.getGridSize(), paramInt * paramJGraph.getGridSize());
    }
    return localDouble;
  }
  
  protected void handleExternalDrop(JGraph paramJGraph, Object[] paramArrayOfObject, Map paramMap, ConnectionSet paramConnectionSet, ParentMap paramParentMap, double paramDouble1, double paramDouble2)
  {
    Iterator localIterator = paramConnectionSet.connections();
    while (localIterator.hasNext())
    {
      localObject = (ConnectionSet.Connection)localIterator.next();
      if ((!paramParentMap.getChangedNodes().contains(((ConnectionSet.Connection)localObject).getPort())) && (!paramJGraph.getModel().contains(((ConnectionSet.Connection)localObject).getPort()))) {
        localIterator.remove();
      }
    }
    Object localObject = paramJGraph.cloneCells(paramArrayOfObject);
    paramJGraph.getGraphLayoutCache().insertClones(paramArrayOfObject, (Map)localObject, paramMap, paramConnectionSet, paramParentMap, paramDouble1, paramDouble2);
  }
  
  protected boolean importDataImpl(JComponent paramJComponent, Transferable paramTransferable)
  {
    return false;
  }
  
  public boolean isAlwaysReceiveAsCopyAction()
  {
    return this.alwaysReceiveAsCopyAction;
  }
  
  public void setAlwaysReceiveAsCopyAction(boolean paramBoolean)
  {
    this.alwaysReceiveAsCopyAction = paramBoolean;
  }
}


/* Location:              /home/menick/Scrivania/ProcessMining/CNMining/CNMining.jar!/org/jgraph/graph/GraphTransferHandler.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */