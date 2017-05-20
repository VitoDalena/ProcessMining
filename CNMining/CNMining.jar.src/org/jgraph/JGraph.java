package org.jgraph;

import java.awt.AlphaComposite;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.GraphicsConfiguration;
import java.awt.Image;
import java.awt.Rectangle;
import java.awt.event.MouseEvent;
import java.awt.geom.Dimension2D;
import java.awt.geom.Point2D;
import java.awt.geom.Point2D.Double;
import java.awt.geom.Rectangle2D;
import java.awt.geom.Rectangle2D.Double;
import java.awt.image.BufferedImage;
import java.awt.image.VolatileImage;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.PrintStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Hashtable;
import java.util.List;
import java.util.Map;
import java.util.Vector;
import javax.accessibility.Accessible;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JComponent;
import javax.swing.JViewport;
import javax.swing.Scrollable;
import javax.swing.event.EventListenerList;
import javax.swing.plaf.ComponentUI;
import org.jgraph.event.GraphLayoutCacheEvent.GraphLayoutCacheChange;
import org.jgraph.event.GraphSelectionEvent;
import org.jgraph.event.GraphSelectionListener;
import org.jgraph.graph.AbstractCellView;
import org.jgraph.graph.AttributeMap;
import org.jgraph.graph.BasicMarqueeHandler;
import org.jgraph.graph.CellView;
import org.jgraph.graph.ConnectionSet;
import org.jgraph.graph.DefaultCellViewFactory;
import org.jgraph.graph.DefaultEdge;
import org.jgraph.graph.DefaultGraphCell;
import org.jgraph.graph.DefaultGraphModel;
import org.jgraph.graph.DefaultGraphSelectionModel;
import org.jgraph.graph.GraphConstants;
import org.jgraph.graph.GraphLayoutCache;
import org.jgraph.graph.GraphModel;
import org.jgraph.graph.GraphSelectionModel;
import org.jgraph.graph.PortView;
import org.jgraph.plaf.GraphUI;
import org.jgraph.plaf.basic.BasicGraphUI;

public class JGraph
  extends JComponent
  implements Scrollable, Accessible, Serializable
{
  public static final String VERSION = "JGraph (v5.13.0.4)";
  public static final int DOT_GRID_MODE = 0;
  public static final int CROSS_GRID_MODE = 1;
  public static final int LINE_GRID_MODE = 2;
  public static boolean IS_MAC = false;
  private static final String uiClassID = "GraphUI";
  protected transient GraphSelectionRedirector selectionRedirector;
  protected transient GraphModel graphModel;
  protected transient GraphLayoutCache graphLayoutCache;
  protected transient GraphSelectionModel selectionModel;
  protected transient BasicMarqueeHandler marquee;
  protected transient Image offscreen;
  protected transient Rectangle2D offscreenBounds;
  protected transient Point2D offscreenOffset;
  protected transient Graphics offgraphics;
  protected transient Rectangle2D offscreenDirty = null;
  protected transient boolean wholeOffscreenDirty = false;
  protected transient double wholeOffscreenDirtyProportion = 0.8D;
  protected transient int offscreenBuffer = 300;
  protected boolean volatileOffscreen = false;
  protected boolean lastBufferAllocated = true;
  protected ImageIcon backgroundImage;
  protected Component backgroundComponent;
  protected boolean backgroundScaled = true;
  protected double scale = 1.0D;
  protected boolean antiAliased = false;
  protected boolean editable = true;
  protected boolean groupsEditable = false;
  protected boolean selectionEnabled = true;
  protected boolean previewInvalidNullPorts = true;
  protected boolean gridVisible = false;
  protected double gridSize = 10.0D;
  protected int gridMode = 0;
  protected boolean portsVisible = false;
  protected boolean portsScaled = true;
  protected boolean portsOnTop = true;
  protected boolean moveBelowZero = false;
  protected boolean moveBeyondGraphBounds = true;
  protected boolean edgeLabelsMovable = true;
  protected boolean autoResizeGraph = true;
  protected Color highlightColor = Color.green;
  protected Color handleColor;
  protected Color lockedHandleColor;
  protected Color marqueeColor;
  protected Color gridColor;
  protected boolean dragEnabled = false;
  protected boolean dropEnabled = true;
  protected boolean xorEnabled = !IS_MAC;
  protected int editClickCount = 2;
  protected boolean enabled = true;
  protected boolean gridEnabled = false;
  protected int handleSize = 3;
  protected int tolerance = 4;
  protected int minimumMove = 5;
  protected boolean isJumpToDefaultPort = false;
  protected boolean isMoveIntoGroups = false;
  protected boolean isMoveOutOfGroups = false;
  protected boolean disconnectOnMove = false;
  protected boolean moveable = true;
  protected boolean cloneable = false;
  protected boolean sizeable = true;
  protected boolean bendable = true;
  protected boolean connectable = true;
  protected boolean disconnectable = true;
  protected boolean invokesStopCellEditing;
  public static final String GRAPH_MODEL_PROPERTY = "model";
  public static final String GRAPH_LAYOUT_CACHE_PROPERTY = "view";
  public static final String MARQUEE_HANDLER_PROPERTY = "marquee";
  public static final String EDITABLE_PROPERTY = "editable";
  public static final String SELECTIONENABLED_PROPERTY = "selectionEnabled";
  public static final String SCALE_PROPERTY = "scale";
  public static final String ANTIALIASED_PROPERTY = "antiAliased";
  public static final String GRID_SIZE_PROPERTY = "gridSize";
  public static final String GRID_VISIBLE_PROPERTY = "gridVisible";
  public static final String GRID_COLOR_PROPERTY = "gridColor";
  public static final String HANDLE_COLOR_PROPERTY = "handleColor";
  public static final String HANDLE_SIZE_PROPERTY = "handleSize";
  public static final String LOCKED_HANDLE_COLOR_PROPERTY = "lockedHandleColor";
  public static final String PORTS_VISIBLE_PROPERTY = "portsVisible";
  public static final String PORTS_SCALED_PROPERTY = "portsScaled";
  public static final String SELECTION_MODEL_PROPERTY = "selectionModel";
  public static final String INVOKES_STOP_CELL_EDITING_PROPERTY = "invokesStopCellEditing";
  public static final String PROPERTY_BACKGROUNDIMAGE = "backgroundImage";
  
  public static void addSampleData(GraphModel paramGraphModel)
  {
    ConnectionSet localConnectionSet = new ConnectionSet();
    Hashtable localHashtable = new Hashtable();
    AttributeMap localAttributeMap1 = new AttributeMap();
    GraphConstants.setLineBegin(localAttributeMap1, 2);
    GraphConstants.setBeginSize(localAttributeMap1, 10);
    GraphConstants.setDashPattern(localAttributeMap1, new float[] { 3.0F, 3.0F });
    if (GraphConstants.DEFAULTFONT != null) {
      GraphConstants.setFont(localAttributeMap1, GraphConstants.DEFAULTFONT.deriveFont(10));
    }
    AttributeMap localAttributeMap2 = new AttributeMap();
    GraphConstants.setLineBegin(localAttributeMap2, 2);
    GraphConstants.setBeginFill(localAttributeMap2, true);
    GraphConstants.setBeginSize(localAttributeMap2, 10);
    if (GraphConstants.DEFAULTFONT != null) {
      GraphConstants.setFont(localAttributeMap2, GraphConstants.DEFAULTFONT.deriveFont(10));
    }
    AttributeMap localAttributeMap3 = new AttributeMap();
    GraphConstants.setLineBegin(localAttributeMap3, 9);
    GraphConstants.setBeginFill(localAttributeMap3, true);
    GraphConstants.setBeginSize(localAttributeMap3, 6);
    GraphConstants.setLineEnd(localAttributeMap3, 4);
    GraphConstants.setEndSize(localAttributeMap3, 8);
    GraphConstants.setLabelPosition(localAttributeMap3, new Point2D.Double(500.0D, 0.0D));
    if (GraphConstants.DEFAULTFONT != null) {
      GraphConstants.setFont(localAttributeMap3, GraphConstants.DEFAULTFONT.deriveFont(10));
    }
    DefaultGraphCell localDefaultGraphCell1 = new DefaultGraphCell("GraphModel");
    localHashtable.put(localDefaultGraphCell1, createBounds(new AttributeMap(), 20, 100, Color.blue));
    localDefaultGraphCell1.addPort(null, "GraphModel/Center");
    DefaultGraphCell localDefaultGraphCell2 = new DefaultGraphCell("DefaultGraphModel");
    localHashtable.put(localDefaultGraphCell2, createBounds(new AttributeMap(), 20, 180, Color.blue));
    localDefaultGraphCell2.addPort(null, "DefaultGraphModel/Center");
    DefaultEdge localDefaultEdge1 = new DefaultEdge("implements");
    localConnectionSet.connect(localDefaultEdge1, localDefaultGraphCell1.getChildAt(0), localDefaultGraphCell2.getChildAt(0));
    localHashtable.put(localDefaultEdge1, localAttributeMap1);
    DefaultGraphCell localDefaultGraphCell3 = new DefaultGraphCell("ModelGroup");
    localDefaultGraphCell3.add(localDefaultGraphCell1);
    localDefaultGraphCell3.add(localDefaultGraphCell2);
    localDefaultGraphCell3.add(localDefaultEdge1);
    DefaultGraphCell localDefaultGraphCell4 = new DefaultGraphCell("JComponent");
    localHashtable.put(localDefaultGraphCell4, createBounds(new AttributeMap(), 180, 20, Color.green));
    localDefaultGraphCell4.addPort(null, "JComponent/Center");
    DefaultGraphCell localDefaultGraphCell5 = new DefaultGraphCell("JGraph");
    localHashtable.put(localDefaultGraphCell5, createBounds(new AttributeMap(), 180, 100, Color.green));
    localDefaultGraphCell5.addPort(null, "JGraph/Center");
    DefaultEdge localDefaultEdge2 = new DefaultEdge("extends");
    localConnectionSet.connect(localDefaultEdge2, localDefaultGraphCell4.getChildAt(0), localDefaultGraphCell5.getChildAt(0));
    localHashtable.put(localDefaultEdge2, localAttributeMap2);
    DefaultGraphCell localDefaultGraphCell6 = new DefaultGraphCell("ComponentUI");
    localHashtable.put(localDefaultGraphCell6, createBounds(new AttributeMap(), 340, 20, Color.red));
    localDefaultGraphCell6.addPort(null, "ComponentUI/Center");
    DefaultGraphCell localDefaultGraphCell7 = new DefaultGraphCell("GraphUI");
    localHashtable.put(localDefaultGraphCell7, createBounds(new AttributeMap(), 340, 100, Color.red));
    localDefaultGraphCell7.addPort(null, "GraphUI/Center");
    DefaultGraphCell localDefaultGraphCell8 = new DefaultGraphCell("BasicGraphUI");
    localHashtable.put(localDefaultGraphCell8, createBounds(new AttributeMap(), 340, 180, Color.red));
    localDefaultGraphCell8.addPort(null, "BasicGraphUI/Center");
    DefaultEdge localDefaultEdge3 = new DefaultEdge("extends");
    localConnectionSet.connect(localDefaultEdge3, localDefaultGraphCell6.getChildAt(0), localDefaultGraphCell7.getChildAt(0));
    localHashtable.put(localDefaultEdge3, localAttributeMap2);
    DefaultEdge localDefaultEdge4 = new DefaultEdge("implements");
    localConnectionSet.connect(localDefaultEdge4, localDefaultGraphCell7.getChildAt(0), localDefaultGraphCell8.getChildAt(0));
    localHashtable.put(localDefaultEdge4, localAttributeMap1);
    DefaultGraphCell localDefaultGraphCell9 = new DefaultGraphCell("UIGroup");
    localDefaultGraphCell9.add(localDefaultGraphCell6);
    localDefaultGraphCell9.add(localDefaultGraphCell7);
    localDefaultGraphCell9.add(localDefaultGraphCell8);
    localDefaultGraphCell9.add(localDefaultEdge4);
    localDefaultGraphCell9.add(localDefaultEdge3);
    DefaultEdge localDefaultEdge5 = new DefaultEdge("model");
    localConnectionSet.connect(localDefaultEdge5, localDefaultGraphCell5.getChildAt(0), localDefaultGraphCell1.getChildAt(0));
    localHashtable.put(localDefaultEdge5, localAttributeMap3);
    DefaultEdge localDefaultEdge6 = new DefaultEdge("ui");
    localConnectionSet.connect(localDefaultEdge6, localDefaultGraphCell4.getChildAt(0), localDefaultGraphCell6.getChildAt(0));
    localHashtable.put(localDefaultEdge6, localAttributeMap3);
    Object[] arrayOfObject = { localDefaultEdge5, localDefaultEdge6, localDefaultGraphCell3, localDefaultGraphCell4, localDefaultGraphCell5, localDefaultEdge2, localDefaultGraphCell9 };
    paramGraphModel.insert(arrayOfObject, localHashtable, localConnectionSet, null, null);
  }
  
  public static Map createBounds(AttributeMap paramAttributeMap, int paramInt1, int paramInt2, Color paramColor)
  {
    GraphConstants.setBounds(paramAttributeMap, paramAttributeMap.createRect(paramInt1, paramInt2, 90.0D, 30.0D));
    GraphConstants.setBorder(paramAttributeMap, BorderFactory.createRaisedBevelBorder());
    GraphConstants.setBackground(paramAttributeMap, paramColor.darker().darker());
    GraphConstants.setGradientColor(paramAttributeMap, paramColor.brighter().brighter().brighter());
    GraphConstants.setForeground(paramAttributeMap, Color.white);
    if (GraphConstants.DEFAULTFONT != null) {
      GraphConstants.setFont(paramAttributeMap, GraphConstants.DEFAULTFONT.deriveFont(1, 12.0F));
    }
    GraphConstants.setOpaque(paramAttributeMap, true);
    return paramAttributeMap;
  }
  
  public JGraph()
  {
    this((GraphModel)null);
  }
  
  public JGraph(GraphModel paramGraphModel)
  {
    this(paramGraphModel, (GraphLayoutCache)null);
  }
  
  public JGraph(GraphLayoutCache paramGraphLayoutCache)
  {
    this(paramGraphLayoutCache != null ? paramGraphLayoutCache.getModel() : null, paramGraphLayoutCache);
  }
  
  public JGraph(GraphModel paramGraphModel, GraphLayoutCache paramGraphLayoutCache)
  {
    this(paramGraphModel, paramGraphLayoutCache, new BasicMarqueeHandler());
  }
  
  public JGraph(GraphModel paramGraphModel, BasicMarqueeHandler paramBasicMarqueeHandler)
  {
    this(paramGraphModel, null, paramBasicMarqueeHandler);
  }
  
  public JGraph(GraphModel paramGraphModel, GraphLayoutCache paramGraphLayoutCache, BasicMarqueeHandler paramBasicMarqueeHandler)
  {
    setDoubleBuffered(true);
    this.selectionModel = new DefaultGraphSelectionModel(this);
    setLayout(null);
    this.marquee = paramBasicMarqueeHandler;
    if (paramGraphModel == null)
    {
      paramGraphModel = new DefaultGraphModel();
      setModel(paramGraphModel);
      addSampleData(paramGraphModel);
    }
    else
    {
      setModel(paramGraphModel);
    }
    if (paramGraphLayoutCache == null) {
      paramGraphLayoutCache = new GraphLayoutCache(paramGraphModel, new DefaultCellViewFactory());
    }
    setGraphLayoutCache(paramGraphLayoutCache);
    updateUI();
  }
  
  public GraphUI getUI()
  {
    return (GraphUI)this.ui;
  }
  
  public void setUI(GraphUI paramGraphUI)
  {
    if ((GraphUI)this.ui != paramGraphUI) {
      super.setUI(paramGraphUI);
    }
  }
  
  public void updateUI()
  {
    setUI(new BasicGraphUI());
    invalidate();
  }
  
  public String getUIClassID()
  {
    return "GraphUI";
  }
  
  public Object[] getRoots()
  {
    return DefaultGraphModel.getRoots(this.graphModel);
  }
  
  public Object[] getRoots(Rectangle paramRectangle)
  {
    CellView[] arrayOfCellView = this.graphLayoutCache.getRoots(paramRectangle);
    Object[] arrayOfObject = new Object[arrayOfCellView.length];
    for (int i = 0; i < arrayOfCellView.length; i++) {
      arrayOfObject[i] = arrayOfCellView[i].getCell();
    }
    return arrayOfObject;
  }
  
  public Object[] getDescendants(Object[] paramArrayOfObject)
  {
    return DefaultGraphModel.getDescendants(getModel(), paramArrayOfObject).toArray();
  }
  
  public Object[] order(Object[] paramArrayOfObject)
  {
    return DefaultGraphModel.order(getModel(), paramArrayOfObject);
  }
  
  public Map cloneCells(Object[] paramArrayOfObject)
  {
    return this.graphModel.cloneCells(paramArrayOfObject);
  }
  
  public CellView getTopmostViewAt(double paramDouble1, double paramDouble2, boolean paramBoolean1, boolean paramBoolean2)
  {
    Rectangle2D.Double localDouble = new Rectangle2D.Double(paramDouble1, paramDouble2, 1.0D, 1.0D);
    Object[] arrayOfObject = getDescendants(getRoots());
    int i = paramBoolean1 ? arrayOfObject.length - 1 : 0;
    while ((i >= 0) && (i < arrayOfObject.length))
    {
      CellView localCellView = getGraphLayoutCache().getMapping(arrayOfObject[i], false);
      if ((localCellView != null) && ((!paramBoolean2) || (localCellView.isLeaf())) && (((localCellView.isLeaf()) && (localCellView.intersects(this, localDouble))) || ((!localCellView.isLeaf()) && (localCellView.getBounds().contains(paramDouble1, paramDouble2))))) {
        return localCellView;
      }
      i += (paramBoolean1 ? -1 : 1);
    }
    return null;
  }
  
  public Object getFirstCellForLocation(double paramDouble1, double paramDouble2)
  {
    return getNextCellForLocation(null, paramDouble1, paramDouble2);
  }
  
  public Object getNextCellForLocation(Object paramObject, double paramDouble1, double paramDouble2)
  {
    CellView localCellView1 = this.graphLayoutCache.getMapping(paramObject, false);
    CellView localCellView2 = getNextViewAt(localCellView1, paramDouble1, paramDouble2);
    if (localCellView2 != null) {
      return localCellView2.getCell();
    }
    return null;
  }
  
  public Rectangle2D getCellBounds(Object paramObject)
  {
    CellView localCellView = this.graphLayoutCache.getMapping(paramObject, false);
    if (localCellView != null) {
      return localCellView.getBounds();
    }
    return null;
  }
  
  public Rectangle2D getCellBounds(Object[] paramArrayOfObject)
  {
    if ((paramArrayOfObject != null) && (paramArrayOfObject.length > 0))
    {
      Rectangle2D localRectangle2D1 = getCellBounds(paramArrayOfObject[0]);
      Rectangle2D localRectangle2D2 = localRectangle2D1 != null ? (Rectangle2D)localRectangle2D1.clone() : null;
      for (int i = 1; i < paramArrayOfObject.length; i++)
      {
        localRectangle2D1 = getCellBounds(paramArrayOfObject[i]);
        if (localRectangle2D1 != null) {
          if (localRectangle2D2 == null) {
            localRectangle2D2 = localRectangle2D1 != null ? (Rectangle2D)localRectangle2D1.clone() : null;
          } else {
            Rectangle2D.union(localRectangle2D2, localRectangle2D1, localRectangle2D2);
          }
        }
      }
      return localRectangle2D2;
    }
    return null;
  }
  
  public CellView getNextViewAt(CellView paramCellView, double paramDouble1, double paramDouble2)
  {
    return getNextViewAt(paramCellView, paramDouble1, paramDouble2, false);
  }
  
  public CellView getNextViewAt(CellView paramCellView, double paramDouble1, double paramDouble2, boolean paramBoolean)
  {
    CellView[] arrayOfCellView = AbstractCellView.getDescendantViews(getGraphLayoutCache().getRoots());
    return getNextViewAt(arrayOfCellView, paramCellView, paramDouble1, paramDouble2, paramBoolean);
  }
  
  public CellView getNextSelectableViewAt(CellView paramCellView, double paramDouble1, double paramDouble2)
  {
    CellView[] arrayOfCellView = getGraphLayoutCache().getMapping(getSelectionModel().getSelectables(), false);
    return getNextViewAt(arrayOfCellView, paramCellView, paramDouble1, paramDouble2);
  }
  
  public CellView getNextViewAt(CellView[] paramArrayOfCellView, CellView paramCellView, double paramDouble1, double paramDouble2)
  {
    return getNextViewAt(paramArrayOfCellView, paramCellView, paramDouble1, paramDouble2, false);
  }
  
  public CellView getNextViewAt(CellView[] paramArrayOfCellView, CellView paramCellView, double paramDouble1, double paramDouble2, boolean paramBoolean)
  {
    if (paramArrayOfCellView != null)
    {
      Rectangle2D localRectangle2D = fromScreen(new Rectangle2D.Double(paramDouble1 - this.tolerance, paramDouble2 - this.tolerance, 2 * this.tolerance, 2 * this.tolerance));
      if (localRectangle2D.getWidth() < 1.0D) {
        localRectangle2D.setFrame(localRectangle2D.getX(), localRectangle2D.getY(), 1.0D, localRectangle2D.getHeight());
      }
      if (localRectangle2D.getHeight() < 1.0D) {
        localRectangle2D.setFrame(localRectangle2D.getX(), localRectangle2D.getY(), localRectangle2D.getWidth(), 1.0D);
      }
      CellView localCellView = null;
      int i = paramCellView == null ? 1 : 0;
      for (int j = 0; j < paramArrayOfCellView.length; j++) {
        if ((paramArrayOfCellView[j] != null) && ((!paramBoolean) || (paramArrayOfCellView[j].isLeaf())) && (paramArrayOfCellView[j].intersects(this, localRectangle2D)))
        {
          if ((i != 0) && (!this.selectionModel.isChildrenSelected(paramArrayOfCellView[j].getCell()))) {
            return paramArrayOfCellView[j];
          }
          if (localCellView == null) {
            localCellView = paramArrayOfCellView[j];
          }
          i |= (paramArrayOfCellView[j] == paramCellView ? 1 : 0);
        }
      }
      return localCellView;
    }
    return null;
  }
  
  public CellView getLeafViewAt(double paramDouble1, double paramDouble2)
  {
    return getNextViewAt(null, paramDouble1, paramDouble2, true);
  }
  
  public Object getPortForLocation(double paramDouble1, double paramDouble2)
  {
    PortView localPortView = getPortViewAt(paramDouble1, paramDouble2, this.tolerance);
    if (localPortView != null) {
      return localPortView.getCell();
    }
    return null;
  }
  
  public PortView getPortViewAt(double paramDouble1, double paramDouble2)
  {
    return getPortViewAt(paramDouble1, paramDouble2, this.tolerance);
  }
  
  public PortView getPortViewAt(double paramDouble1, double paramDouble2, int paramInt)
  {
    double d1 = paramDouble1 / this.scale;
    double d2 = paramDouble2 / this.scale;
    Rectangle2D.Double localDouble = new Rectangle2D.Double(d1 - paramInt, d2 - paramInt, 2 * paramInt, 2 * paramInt);
    PortView[] arrayOfPortView = this.graphLayoutCache.getPorts();
    if (arrayOfPortView != null)
    {
      for (int i = arrayOfPortView.length - 1; i >= 0; i--) {
        if ((arrayOfPortView[i] != null) && (arrayOfPortView[i].intersects(this, localDouble))) {
          return arrayOfPortView[i];
        }
      }
      if (isJumpToDefaultPort())
      {
        Object localObject1 = getNextViewAt(null, paramDouble1, paramDouble2, true);
        Object localObject2;
        if ((localObject1 != null) && (this.graphModel.isEdge(((CellView)localObject1).getCell())))
        {
          for (localObject2 = getNextViewAt((CellView)localObject1, paramDouble1, paramDouble2, true); (localObject2 != localObject1) && (this.graphModel.isEdge(((CellView)localObject2).getCell())); localObject2 = getNextViewAt((CellView)localObject2, paramDouble1, paramDouble2, true)) {}
          localObject1 = localObject2;
        }
        if (localObject1 != null)
        {
          localObject2 = getDefaultPortForCell(((CellView)localObject1).getCell());
          return (PortView)localObject2;
        }
      }
    }
    return null;
  }
  
  public PortView getDefaultPortForCell(Object paramObject)
  {
    if ((paramObject != null) && (!getModel().isEdge(paramObject)))
    {
      int i = getModel().getChildCount(paramObject);
      for (int j = 0; j < i; j++)
      {
        Object localObject = getModel().getChild(paramObject, j);
        CellView localCellView = getGraphLayoutCache().getMapping(localObject, false);
        if ((localCellView instanceof PortView))
        {
          Point2D localPoint2D = GraphConstants.getOffset(localCellView.getAllAttributes());
          if ((localPoint2D == null) || (i == 1)) {
            return (PortView)localCellView;
          }
        }
      }
    }
    return null;
  }
  
  public String convertValueToString(Object paramObject)
  {
    if ((paramObject instanceof CellView)) {
      paramObject = ((CellView)paramObject).getCell();
    }
    return String.valueOf(paramObject);
  }
  
  public Point2D snap(Point2D paramPoint2D)
  {
    if ((this.gridEnabled) && (paramPoint2D != null))
    {
      double d = this.gridSize * getScale();
      paramPoint2D.setLocation(Math.round(Math.round(paramPoint2D.getX() / d) * d), Math.round(Math.round(paramPoint2D.getY() / d) * d));
    }
    return paramPoint2D;
  }
  
  public Rectangle2D snap(Rectangle2D paramRectangle2D)
  {
    if ((this.gridEnabled) && (paramRectangle2D != null))
    {
      double d = this.gridSize * getScale();
      paramRectangle2D.setFrame(Math.round(Math.round(paramRectangle2D.getX() / d) * d), Math.round(Math.round(paramRectangle2D.getY() / d) * d), 1L + Math.round(Math.round(paramRectangle2D.getWidth() / d) * d), 1L + Math.round(Math.round(paramRectangle2D.getHeight() / d) * d));
    }
    return paramRectangle2D;
  }
  
  public Dimension2D snap(Dimension2D paramDimension2D)
  {
    if ((this.gridEnabled) && (paramDimension2D != null))
    {
      double d = this.gridSize * getScale();
      paramDimension2D.setSize(1L + Math.round(Math.round(paramDimension2D.getWidth() / d) * d), 1L + Math.round(Math.round(paramDimension2D.getHeight() / d) * d));
    }
    return paramDimension2D;
  }
  
  public Point2D toScreen(Point2D paramPoint2D)
  {
    if (paramPoint2D == null) {
      return null;
    }
    paramPoint2D.setLocation(Math.round(paramPoint2D.getX() * this.scale), Math.round(paramPoint2D.getY() * this.scale));
    return paramPoint2D;
  }
  
  public Point2D fromScreen(Point2D paramPoint2D)
  {
    if (paramPoint2D == null) {
      return null;
    }
    paramPoint2D.setLocation(Math.round(paramPoint2D.getX() / this.scale), Math.round(paramPoint2D.getY() / this.scale));
    return paramPoint2D;
  }
  
  public Rectangle2D toScreen(Rectangle2D paramRectangle2D)
  {
    if (paramRectangle2D == null) {
      return null;
    }
    paramRectangle2D.setFrame(paramRectangle2D.getX() * this.scale, paramRectangle2D.getY() * this.scale, paramRectangle2D.getWidth() * this.scale, paramRectangle2D.getHeight() * this.scale);
    return paramRectangle2D;
  }
  
  public Rectangle2D fromScreen(Rectangle2D paramRectangle2D)
  {
    if (paramRectangle2D == null) {
      return null;
    }
    paramRectangle2D.setFrame(paramRectangle2D.getX() / this.scale, paramRectangle2D.getY() / this.scale, paramRectangle2D.getWidth() / this.scale, paramRectangle2D.getHeight() / this.scale);
    return paramRectangle2D;
  }
  
  public void updateAutoSize(CellView paramCellView)
  {
    if ((paramCellView != null) && (!isEditing()))
    {
      Rectangle2D localRectangle2D = paramCellView.getAttributes() != null ? GraphConstants.getBounds(paramCellView.getAttributes()) : null;
      AttributeMap localAttributeMap = getModel().getAttributes(paramCellView.getCell());
      if (localRectangle2D == null) {
        localRectangle2D = GraphConstants.getBounds(localAttributeMap);
      }
      if (localRectangle2D != null)
      {
        boolean bool1 = GraphConstants.isAutoSize(paramCellView.getAllAttributes());
        boolean bool2 = GraphConstants.isResize(paramCellView.getAllAttributes());
        if ((bool1) || (bool2))
        {
          Dimension2D localDimension2D = getUI().getPreferredSize(this, paramCellView);
          localRectangle2D.setFrame(localRectangle2D.getX(), localRectangle2D.getY(), localDimension2D.getWidth(), localDimension2D.getHeight());
          snap(localRectangle2D);
          if (bool2)
          {
            if (paramCellView.getAttributes() != null) {
              paramCellView.getAttributes().remove("resize");
            }
            localAttributeMap.remove("resize");
          }
          paramCellView.refresh(getGraphLayoutCache(), getGraphLayoutCache(), false);
        }
      }
    }
  }
  
  public AttributeMap getAttributes(Object paramObject)
  {
    CellView localCellView = getGraphLayoutCache().getMapping(paramObject, false);
    AttributeMap localAttributeMap;
    if (localCellView != null) {
      localAttributeMap = localCellView.getAllAttributes();
    } else {
      localAttributeMap = getModel().getAttributes(paramObject);
    }
    return localAttributeMap;
  }
  
  public int getEditClickCount()
  {
    return this.editClickCount;
  }
  
  public void setEditClickCount(int paramInt)
  {
    this.editClickCount = paramInt;
  }
  
  public boolean isDropEnabled()
  {
    return this.dropEnabled;
  }
  
  public void setDropEnabled(boolean paramBoolean)
  {
    this.dropEnabled = paramBoolean;
  }
  
  public boolean isXorEnabled()
  {
    return (this.xorEnabled) && (isOpaque());
  }
  
  public void setXorEnabled(boolean paramBoolean)
  {
    this.xorEnabled = paramBoolean;
  }
  
  public boolean isDragEnabled()
  {
    return this.dragEnabled;
  }
  
  public void setDragEnabled(boolean paramBoolean)
  {
    this.dragEnabled = paramBoolean;
  }
  
  public boolean isMoveable()
  {
    return this.moveable;
  }
  
  public void setMoveable(boolean paramBoolean)
  {
    this.moveable = paramBoolean;
  }
  
  public boolean isBendable()
  {
    return this.bendable;
  }
  
  public void setBendable(boolean paramBoolean)
  {
    this.bendable = paramBoolean;
  }
  
  public boolean isConnectable()
  {
    return this.connectable;
  }
  
  public void setConnectable(boolean paramBoolean)
  {
    this.connectable = paramBoolean;
  }
  
  public boolean isDisconnectable()
  {
    return this.disconnectable;
  }
  
  public void setDisconnectable(boolean paramBoolean)
  {
    this.disconnectable = paramBoolean;
  }
  
  public boolean isCloneable()
  {
    return this.cloneable;
  }
  
  public void setCloneable(boolean paramBoolean)
  {
    this.cloneable = paramBoolean;
  }
  
  public boolean isSizeable()
  {
    return this.sizeable;
  }
  
  public void setSizeable(boolean paramBoolean)
  {
    this.sizeable = paramBoolean;
  }
  
  public void setDisconnectOnMove(boolean paramBoolean)
  {
    this.disconnectOnMove = paramBoolean;
  }
  
  public boolean isDisconnectOnMove()
  {
    return (this.disconnectOnMove) && (this.disconnectable);
  }
  
  public void setJumpToDefaultPort(boolean paramBoolean)
  {
    this.isJumpToDefaultPort = paramBoolean;
  }
  
  public boolean isJumpToDefaultPort()
  {
    return this.isJumpToDefaultPort;
  }
  
  public void setMoveIntoGroups(boolean paramBoolean)
  {
    this.isMoveIntoGroups = paramBoolean;
  }
  
  public boolean isMoveIntoGroups()
  {
    return this.isMoveIntoGroups;
  }
  
  public void setMoveOutOfGroups(boolean paramBoolean)
  {
    this.isMoveOutOfGroups = paramBoolean;
  }
  
  public boolean isMoveOutOfGroups()
  {
    return this.isMoveOutOfGroups;
  }
  
  public boolean isGridEnabled()
  {
    return this.gridEnabled;
  }
  
  public void setGridEnabled(boolean paramBoolean)
  {
    this.gridEnabled = paramBoolean;
  }
  
  public boolean isMoveBelowZero()
  {
    return this.moveBelowZero;
  }
  
  public void setMoveBelowZero(boolean paramBoolean)
  {
    this.moveBelowZero = paramBoolean;
  }
  
  public boolean isMoveBeyondGraphBounds()
  {
    return this.moveBeyondGraphBounds;
  }
  
  public void setMoveBeyondGraphBounds(boolean paramBoolean)
  {
    this.moveBeyondGraphBounds = paramBoolean;
  }
  
  public boolean getEdgeLabelsMovable()
  {
    return this.edgeLabelsMovable;
  }
  
  public void setEdgeLabelsMovable(boolean paramBoolean)
  {
    this.edgeLabelsMovable = paramBoolean;
  }
  
  public boolean isAutoResizeGraph()
  {
    if (!this.moveBeyondGraphBounds) {
      return false;
    }
    return this.autoResizeGraph;
  }
  
  public void setAutoResizeGraph(boolean paramBoolean)
  {
    this.autoResizeGraph = paramBoolean;
  }
  
  public int getTolerance()
  {
    return this.tolerance;
  }
  
  public void setTolerance(int paramInt)
  {
    if (paramInt < 1) {
      paramInt = 1;
    }
    this.tolerance = paramInt;
  }
  
  public int getHandleSize()
  {
    return this.handleSize;
  }
  
  public void setHandleSize(int paramInt)
  {
    int i = this.handleSize;
    this.handleSize = paramInt;
    firePropertyChange("handleSize", i, paramInt);
  }
  
  public int getMinimumMove()
  {
    return this.minimumMove;
  }
  
  public void setMinimumMove(int paramInt)
  {
    this.minimumMove = paramInt;
  }
  
  public Color getGridColor()
  {
    return this.gridColor;
  }
  
  public void setGridColor(Color paramColor)
  {
    Color localColor = this.gridColor;
    this.gridColor = paramColor;
    firePropertyChange("gridColor", localColor, paramColor);
  }
  
  public Color getHandleColor()
  {
    return this.handleColor;
  }
  
  public void setHandleColor(Color paramColor)
  {
    Color localColor = this.handleColor;
    this.handleColor = paramColor;
    firePropertyChange("handleColor", localColor, paramColor);
  }
  
  public Color getLockedHandleColor()
  {
    return this.lockedHandleColor;
  }
  
  public void setLockedHandleColor(Color paramColor)
  {
    Color localColor = this.lockedHandleColor;
    this.lockedHandleColor = paramColor;
    firePropertyChange("lockedHandleColor", localColor, paramColor);
  }
  
  public Color getMarqueeColor()
  {
    return this.marqueeColor;
  }
  
  public void setMarqueeColor(Color paramColor)
  {
    this.marqueeColor = paramColor;
  }
  
  public Color getHighlightColor()
  {
    return this.highlightColor;
  }
  
  public void setHighlightColor(Color paramColor)
  {
    this.highlightColor = paramColor;
  }
  
  public double getScale()
  {
    return this.scale;
  }
  
  public void setScale(double paramDouble)
  {
    Point2D localPoint2D = getCenterPoint();
    setScale(paramDouble, localPoint2D);
  }
  
  public void setScale(double paramDouble, Point2D paramPoint2D)
  {
    if ((paramDouble > 0.0D) && (paramDouble != this.scale))
    {
      Rectangle2D localRectangle2D = getViewPortBounds();
      double d1 = this.scale;
      this.scale = paramDouble;
      int i = 1;
      Rectangle localRectangle = null;
      clearOffscreen();
      if (localRectangle2D != null)
      {
        double d2 = paramDouble / d1;
        int j = (int)(paramPoint2D.getX() * d2);
        int k = (int)(paramPoint2D.getY() * d2);
        int m = (int)(j - localRectangle2D.getWidth() / 2.0D);
        int n = (int)(k - localRectangle2D.getHeight() / 2.0D);
        localRectangle = new Rectangle(m, n, (int)localRectangle2D.getWidth(), (int)localRectangle2D.getHeight());
        if (d2 < 1.0D)
        {
          scrollRectToVisible(localRectangle);
          i = 0;
        }
      }
      firePropertyChange("scale", d1, paramDouble);
      if ((i != 0) && (localRectangle != null)) {
        scrollRectToVisible(localRectangle);
      }
    }
  }
  
  public void clearOffscreen()
  {
    if (this.offscreen != null)
    {
      int i = this.offscreen.getHeight(this);
      int j = this.offscreen.getWidth(this);
      Rectangle2D.Double localDouble = new Rectangle2D.Double(0.0D, 0.0D, j, i);
      fromScreen(localDouble);
      addOffscreenDirty(localDouble);
    }
  }
  
  public Point2D getCenterPoint()
  {
    Object localObject = getViewPortBounds();
    if (localObject != null) {
      return new Point2D.Double(((Rectangle2D)localObject).getCenterX(), ((Rectangle2D)localObject).getCenterY());
    }
    localObject = getBounds();
    return new Point2D.Double(((Rectangle2D)localObject).getCenterX(), ((Rectangle2D)localObject).getCenterY());
  }
  
  public Rectangle2D getViewPortBounds()
  {
    if ((getParent() instanceof JViewport)) {
      return ((JViewport)getParent()).getViewRect();
    }
    return null;
  }
  
  public double getGridSize()
  {
    return this.gridSize;
  }
  
  public int getGridMode()
  {
    return this.gridMode;
  }
  
  public void setGridSize(double paramDouble)
  {
    double d = this.gridSize;
    this.gridSize = paramDouble;
    firePropertyChange("gridSize", d, paramDouble);
  }
  
  public void setGridMode(int paramInt)
  {
    if ((paramInt == 0) || (paramInt == 1) || (paramInt == 2))
    {
      this.gridMode = paramInt;
      repaint();
    }
  }
  
  public boolean isGridVisible()
  {
    return this.gridVisible;
  }
  
  public void setGridVisible(boolean paramBoolean)
  {
    boolean bool = this.gridVisible;
    this.gridVisible = paramBoolean;
    if (paramBoolean != bool) {
      clearOffscreen();
    }
    firePropertyChange("gridVisible", bool, paramBoolean);
  }
  
  public boolean isPortsVisible()
  {
    return this.portsVisible;
  }
  
  public void setPortsVisible(boolean paramBoolean)
  {
    boolean bool = this.portsVisible;
    this.portsVisible = paramBoolean;
    if (paramBoolean != bool) {
      clearOffscreen();
    }
    firePropertyChange("portsVisible", bool, paramBoolean);
  }
  
  public boolean isPortsScaled()
  {
    return this.portsScaled;
  }
  
  public void setPortsScaled(boolean paramBoolean)
  {
    boolean bool = this.portsScaled;
    this.portsScaled = paramBoolean;
    firePropertyChange("portsScaled", bool, paramBoolean);
  }
  
  public boolean isPortsOnTop()
  {
    return this.portsOnTop;
  }
  
  public void setPortsOnTop(boolean paramBoolean)
  {
    this.portsOnTop = paramBoolean;
  }
  
  public boolean isAntiAliased()
  {
    return this.antiAliased;
  }
  
  public void setAntiAliased(boolean paramBoolean)
  {
    boolean bool = this.antiAliased;
    this.antiAliased = paramBoolean;
    firePropertyChange("antiAliased", bool, paramBoolean);
  }
  
  public boolean isEditable()
  {
    return this.editable;
  }
  
  public void setEditable(boolean paramBoolean)
  {
    boolean bool = this.editable;
    this.editable = paramBoolean;
    firePropertyChange("editable", bool, paramBoolean);
  }
  
  public boolean isGroupsEditable()
  {
    return this.groupsEditable;
  }
  
  public void setGroupsEditable(boolean paramBoolean)
  {
    this.groupsEditable = paramBoolean;
  }
  
  public boolean isSelectionEnabled()
  {
    return this.selectionEnabled;
  }
  
  public void setSelectionEnabled(boolean paramBoolean)
  {
    boolean bool = this.selectionEnabled;
    this.selectionEnabled = paramBoolean;
    firePropertyChange("selectionEnabled", bool, paramBoolean);
  }
  
  public boolean isPreviewInvalidNullPorts()
  {
    return this.previewInvalidNullPorts;
  }
  
  public void setPreviewInvalidNullPorts(boolean paramBoolean)
  {
    this.previewInvalidNullPorts = paramBoolean;
  }
  
  public Graphics getOffgraphics()
  {
    if (!isDoubleBuffered()) {
      return null;
    }
    Rectangle localRectangle = getBounds();
    int i = Math.max(0, (int)localRectangle.getX());
    int j = Math.max(0, (int)localRectangle.getY());
    int k = (int)localRectangle.getWidth();
    int m = (int)localRectangle.getHeight();
    int n = 1;
    Rectangle2D.Double localDouble = new Rectangle2D.Double(0.0D, 0.0D, k, m);
    if (this.offscreenBounds != null)
    {
      n = !this.offscreenBounds.contains(localDouble) ? 1 : 0;
      if (n != 0)
      {
        k += this.offscreenBuffer;
        m += this.offscreenBuffer;
        localDouble = new Rectangle2D.Double(0.0D, 0.0D, k, m);
      }
    }
    if ((this.offscreen == null) || (this.offgraphics == null) || (this.offscreenBounds == null) || (n != 0))
    {
      if (this.offscreen != null) {
        this.offscreen.flush();
      }
      if (this.offgraphics != null) {
        this.offgraphics.dispose();
      }
      this.offscreen = null;
      this.offgraphics = null;
      Runtime localRuntime = Runtime.getRuntime();
      long l1 = localRuntime.maxMemory();
      long l2 = localRuntime.totalMemory();
      long l3 = localRuntime.freeMemory();
      long l4 = (l3 + (l1 - l2)) / 1024L;
      long l5 = k * m * 4 / 1024;
      if (l5 > l4)
      {
        if (this.lastBufferAllocated) {
          System.gc();
        }
        this.lastBufferAllocated = false;
        return null;
      }
      if ((this.offscreen == null) && (this.volatileOffscreen)) {
        try
        {
          this.offscreen = createVolatileImage(k, m);
        }
        catch (OutOfMemoryError localOutOfMemoryError2)
        {
          this.offscreen = null;
          this.offgraphics = null;
        }
      }
      if (this.offscreen == null) {
        createBufferedImage(k, m);
      }
      if (this.offscreen == null)
      {
        this.lastBufferAllocated = false;
        return null;
      }
      this.lastBufferAllocated = true;
      setupOffScreen(i, j, k, m, localDouble);
    }
    else if ((this.offscreen instanceof VolatileImage))
    {
      int i1 = ((VolatileImage)this.offscreen).validate(getGraphicsConfiguration());
      if (!this.volatileOffscreen)
      {
        this.offscreen.flush();
        this.offgraphics.dispose();
        this.offscreen = null;
        this.offgraphics = null;
        createBufferedImage(k, m);
        setupOffScreen(i, j, k, m, localDouble);
      }
      else if (i1 == 2)
      {
        this.offscreen.flush();
        this.offgraphics.dispose();
        try
        {
          this.offscreen = createVolatileImage(k, m);
        }
        catch (OutOfMemoryError localOutOfMemoryError1)
        {
          this.offscreen = null;
          this.offgraphics = null;
          return null;
        }
        setupOffScreen(i, j, k, m, localDouble);
      }
      else if (i1 == 1)
      {
        addOffscreenDirty(new Rectangle2D.Double(0.0D, 0.0D, getWidth(), getHeight()));
      }
    }
    Rectangle2D localRectangle2D = getOffscreenDirty();
    if (localRectangle2D != null)
    {
      if (isOpaque())
      {
        this.offgraphics.setColor(getBackground());
        this.offgraphics.setPaintMode();
      }
      else
      {
        ((Graphics2D)this.offgraphics).setComposite(AlphaComposite.getInstance(1, 0.0F));
      }
      toScreen(localRectangle2D);
      localRectangle2D.setRect(localRectangle2D.getX() - (getHandleSize() + 1), localRectangle2D.getY() - (getHandleSize() + 1), localRectangle2D.getWidth() + (getHandleSize() + 1) * 2, localRectangle2D.getHeight() + (getHandleSize() + 1) * 2);
      this.offgraphics.fillRect((int)localRectangle2D.getX(), (int)localRectangle2D.getY(), (int)localRectangle2D.getWidth(), (int)localRectangle2D.getHeight());
      if (!isOpaque()) {
        ((Graphics2D)this.offgraphics).setComposite(AlphaComposite.SrcOver);
      }
      ((BasicGraphUI)getUI()).drawGraph(this.offgraphics, localRectangle2D);
      clearOffscreenDirty();
    }
    return this.offgraphics;
  }
  
  protected void createBufferedImage(int paramInt1, int paramInt2)
  {
    GraphicsConfiguration localGraphicsConfiguration = getGraphicsConfiguration();
    if (localGraphicsConfiguration != null) {
      try
      {
        this.offscreen = localGraphicsConfiguration.createCompatibleImage(paramInt1, paramInt2, isOpaque() ? 1 : 3);
      }
      catch (OutOfMemoryError localOutOfMemoryError1)
      {
        this.offscreen = null;
        this.offgraphics = null;
      }
      catch (NegativeArraySizeException localNegativeArraySizeException)
      {
        this.offscreen = null;
        this.offgraphics = null;
      }
    } else {
      try
      {
        this.offscreen = new BufferedImage(paramInt1, paramInt2, isOpaque() ? 1 : 2);
      }
      catch (OutOfMemoryError localOutOfMemoryError2)
      {
        this.offscreen = null;
        this.offgraphics = null;
      }
    }
  }
  
  protected void setupOffScreen(int paramInt1, int paramInt2, int paramInt3, int paramInt4, Rectangle2D paramRectangle2D)
  {
    this.offgraphics = this.offscreen.getGraphics();
    if (isOpaque())
    {
      this.offgraphics.setColor(getBackground());
      this.offgraphics.setPaintMode();
    }
    else
    {
      ((Graphics2D)this.offgraphics).setComposite(AlphaComposite.getInstance(1, 0.0F));
    }
    this.offgraphics.fillRect(0, 0, paramInt3, paramInt4);
    if (!isOpaque()) {
      ((Graphics2D)this.offgraphics).setComposite(AlphaComposite.SrcOver);
    }
    ((BasicGraphUI)getUI()).drawGraph(this.offgraphics, null);
    this.offscreenBounds = paramRectangle2D;
    this.offscreenOffset = new Point2D.Double(paramInt1, paramInt2);
    clearOffscreenDirty();
  }
  
  public Image getOffscreen()
  {
    return this.offscreen;
  }
  
  public Rectangle2D getOffscreenDirty()
  {
    return this.offscreenDirty;
  }
  
  public void addOffscreenDirty(Rectangle2D paramRectangle2D)
  {
    if ((this.offscreenDirty == null) && (paramRectangle2D != null)) {
      this.offscreenDirty = ((Rectangle2D)paramRectangle2D.clone());
    } else if (paramRectangle2D != null) {
      this.offscreenDirty.add(paramRectangle2D);
    }
  }
  
  public void clearOffscreenDirty()
  {
    this.offscreenDirty = null;
  }
  
  public void releaseOffscreenResources()
  {
    this.offscreen.flush();
    this.offgraphics.dispose();
    this.offscreen = null;
    this.offgraphics = null;
  }
  
  public boolean drawImage(int paramInt1, int paramInt2, int paramInt3, int paramInt4, int paramInt5, int paramInt6, int paramInt7, int paramInt8)
  {
    getOffgraphics();
    return getGraphics().drawImage(this.offscreen, paramInt5, paramInt6, paramInt7, paramInt8, paramInt5, paramInt6, paramInt7, paramInt8, this);
  }
  
  public boolean drawImage(Graphics paramGraphics)
  {
    Rectangle localRectangle = getBounds();
    return getGraphics().drawImage(this.offscreen, localRectangle.x, localRectangle.y, localRectangle.x + localRectangle.width, localRectangle.y + localRectangle.height, localRectangle.x, localRectangle.y, localRectangle.x + localRectangle.width, localRectangle.y + localRectangle.height, this);
  }
  
  public ImageIcon getBackgroundImage()
  {
    return this.backgroundImage;
  }
  
  public void setBackgroundImage(ImageIcon paramImageIcon)
  {
    ImageIcon localImageIcon = this.backgroundImage;
    this.backgroundImage = paramImageIcon;
    clearOffscreen();
    firePropertyChange("backgroundImage", localImageIcon, paramImageIcon);
  }
  
  public void setBackground(Color paramColor)
  {
    clearOffscreen();
    super.setBackground(paramColor);
  }
  
  public boolean isBackgroundScaled()
  {
    return this.backgroundScaled;
  }
  
  public Point2D getOffscreenOffset()
  {
    return this.offscreenOffset;
  }
  
  public void setOffscreenOffset(Point2D paramPoint2D)
  {
    this.offscreenOffset = paramPoint2D;
  }
  
  public boolean isVolatileOffscreen()
  {
    return this.volatileOffscreen;
  }
  
  public void setVolatileOffscreen(boolean paramBoolean)
  {
    this.volatileOffscreen = paramBoolean;
  }
  
  public void setBackgroundScaled(boolean paramBoolean)
  {
    this.backgroundScaled = paramBoolean;
  }
  
  public Component getBackgroundComponent()
  {
    return this.backgroundComponent;
  }
  
  public void setBackgroundComponent(Component paramComponent)
  {
    clearOffscreen();
    this.backgroundComponent = paramComponent;
  }
  
  public void setOpaque(boolean paramBoolean)
  {
    if (!paramBoolean) {
      setXorEnabled(false);
    }
    super.setOpaque(paramBoolean);
  }
  
  public GraphModel getModel()
  {
    return this.graphModel;
  }
  
  public void setModel(GraphModel paramGraphModel)
  {
    GraphModel localGraphModel = this.graphModel;
    this.graphModel = paramGraphModel;
    clearOffscreen();
    firePropertyChange("model", localGraphModel, this.graphModel);
    if ((this.graphLayoutCache != null) && (this.graphLayoutCache.getModel() != this.graphModel)) {
      this.graphLayoutCache.setModel(this.graphModel);
    }
    clearSelection();
    invalidate();
  }
  
  public GraphLayoutCache getGraphLayoutCache()
  {
    return this.graphLayoutCache;
  }
  
  public void setGraphLayoutCache(GraphLayoutCache paramGraphLayoutCache)
  {
    if (!isSelectionEmpty()) {
      clearSelection();
    }
    GraphLayoutCache localGraphLayoutCache = this.graphLayoutCache;
    this.graphLayoutCache = paramGraphLayoutCache;
    clearOffscreen();
    firePropertyChange("view", localGraphLayoutCache, this.graphLayoutCache);
    if ((this.graphLayoutCache != null) && (this.graphLayoutCache.getModel() != getModel())) {
      setModel(this.graphLayoutCache.getModel());
    } else {
      this.graphLayoutCache.update();
    }
    invalidate();
  }
  
  public BasicMarqueeHandler getMarqueeHandler()
  {
    return this.marquee;
  }
  
  public void setMarqueeHandler(BasicMarqueeHandler paramBasicMarqueeHandler)
  {
    BasicMarqueeHandler localBasicMarqueeHandler = this.marquee;
    this.marquee = paramBasicMarqueeHandler;
    firePropertyChange("marquee", localBasicMarqueeHandler, paramBasicMarqueeHandler);
    invalidate();
  }
  
  public void setInvokesStopCellEditing(boolean paramBoolean)
  {
    boolean bool = this.invokesStopCellEditing;
    this.invokesStopCellEditing = paramBoolean;
    firePropertyChange("invokesStopCellEditing", bool, paramBoolean);
  }
  
  public boolean getInvokesStopCellEditing()
  {
    return this.invokesStopCellEditing;
  }
  
  public boolean isCellEditable(Object paramObject)
  {
    if (paramObject != null)
    {
      CellView localCellView = this.graphLayoutCache.getMapping(paramObject, false);
      if (localCellView != null) {
        return (isEditable()) && (GraphConstants.isEditable(localCellView.getAllAttributes()));
      }
    }
    return false;
  }
  
  public String getToolTipText(MouseEvent paramMouseEvent)
  {
    if (paramMouseEvent != null)
    {
      Object localObject = getFirstCellForLocation(paramMouseEvent.getX(), paramMouseEvent.getY());
      CellView localCellView = getGraphLayoutCache().getMapping(localObject, false);
      if (localCellView != null)
      {
        Component localComponent = localCellView.getRendererComponent(this, false, false, false);
        if ((localComponent instanceof JComponent))
        {
          Rectangle2D localRectangle2D = getCellBounds(localObject);
          Point2D localPoint2D = fromScreen(paramMouseEvent.getPoint());
          paramMouseEvent = new MouseEvent(localComponent, paramMouseEvent.getID(), paramMouseEvent.getWhen(), paramMouseEvent.getModifiers(), (int)(localPoint2D.getX() - localRectangle2D.getX()), (int)(localPoint2D.getY() - localRectangle2D.getY()), paramMouseEvent.getClickCount(), paramMouseEvent.isPopupTrigger());
          return ((JComponent)localComponent).getToolTipText(paramMouseEvent);
        }
      }
    }
    return super.getToolTipText(paramMouseEvent);
  }
  
  public void setSelectionModel(GraphSelectionModel paramGraphSelectionModel)
  {
    if (paramGraphSelectionModel == null) {
      paramGraphSelectionModel = EmptySelectionModel.sharedInstance();
    }
    GraphSelectionModel localGraphSelectionModel = this.selectionModel;
    if ((this.selectionModel != null) && (this.selectionRedirector != null)) {
      this.selectionModel.removeGraphSelectionListener(this.selectionRedirector);
    }
    this.selectionModel = paramGraphSelectionModel;
    if (this.selectionRedirector != null) {
      this.selectionModel.addGraphSelectionListener(this.selectionRedirector);
    }
    firePropertyChange("selectionModel", localGraphSelectionModel, this.selectionModel);
  }
  
  public GraphSelectionModel getSelectionModel()
  {
    return this.selectionModel;
  }
  
  public void clearSelection()
  {
    getSelectionModel().clearSelection();
  }
  
  public boolean isSelectionEmpty()
  {
    return getSelectionModel().isSelectionEmpty();
  }
  
  public void addGraphSelectionListener(GraphSelectionListener paramGraphSelectionListener)
  {
    this.listenerList.add(GraphSelectionListener.class, paramGraphSelectionListener);
    if ((this.listenerList.getListenerCount(GraphSelectionListener.class) != 0) && (this.selectionRedirector == null))
    {
      this.selectionRedirector = new GraphSelectionRedirector();
      this.selectionModel.addGraphSelectionListener(this.selectionRedirector);
    }
  }
  
  public void removeGraphSelectionListener(GraphSelectionListener paramGraphSelectionListener)
  {
    this.listenerList.remove(GraphSelectionListener.class, paramGraphSelectionListener);
    if ((this.listenerList.getListenerCount(GraphSelectionListener.class) == 0) && (this.selectionRedirector != null))
    {
      this.selectionModel.removeGraphSelectionListener(this.selectionRedirector);
      this.selectionRedirector = null;
    }
  }
  
  protected void fireValueChanged(GraphSelectionEvent paramGraphSelectionEvent)
  {
    Object[] arrayOfObject = this.listenerList.getListenerList();
    for (int i = arrayOfObject.length - 2; i >= 0; i -= 2) {
      if (arrayOfObject[i] == GraphSelectionListener.class) {
        ((GraphSelectionListener)arrayOfObject[(i + 1)]).valueChanged(paramGraphSelectionEvent);
      }
    }
  }
  
  public void setSelectionCell(Object paramObject)
  {
    getSelectionModel().setSelectionCell(paramObject);
  }
  
  public void setSelectionCells(Object[] paramArrayOfObject)
  {
    getSelectionModel().setSelectionCells(paramArrayOfObject);
  }
  
  public void addSelectionCell(Object paramObject)
  {
    getSelectionModel().addSelectionCell(paramObject);
  }
  
  public void addSelectionCells(Object[] paramArrayOfObject)
  {
    getSelectionModel().addSelectionCells(paramArrayOfObject);
  }
  
  public void removeSelectionCell(Object paramObject)
  {
    getSelectionModel().removeSelectionCell(paramObject);
  }
  
  public Object getSelectionCell()
  {
    return getSelectionModel().getSelectionCell();
  }
  
  public Object[] getSelectionCells()
  {
    return getSelectionModel().getSelectionCells();
  }
  
  public Object[] getSelectionCells(Object[] paramArrayOfObject)
  {
    if (paramArrayOfObject != null)
    {
      ArrayList localArrayList = new ArrayList(paramArrayOfObject.length);
      for (int i = 0; i < paramArrayOfObject.length; i++) {
        if (isCellSelected(paramArrayOfObject[i])) {
          localArrayList.add(paramArrayOfObject[i]);
        }
      }
      return localArrayList.toArray();
    }
    return null;
  }
  
  public Object getSelectionCellAt(Point2D paramPoint2D)
  {
    paramPoint2D = fromScreen((Point2D)paramPoint2D.clone());
    Object[] arrayOfObject = getSelectionCells();
    if (arrayOfObject != null) {
      for (int i = 0; i < arrayOfObject.length; i++) {
        if (getCellBounds(arrayOfObject[i]).contains(paramPoint2D.getX(), paramPoint2D.getY())) {
          return arrayOfObject[i];
        }
      }
    }
    return null;
  }
  
  public int getSelectionCount()
  {
    return getSelectionModel().getSelectionCount();
  }
  
  public boolean isCellSelected(Object paramObject)
  {
    return getSelectionModel().isCellSelected(paramObject);
  }
  
  public void scrollCellToVisible(Object paramObject)
  {
    Rectangle2D localRectangle2D1 = getCellBounds(paramObject);
    if (localRectangle2D1 != null)
    {
      Rectangle2D localRectangle2D2 = toScreen((Rectangle2D)localRectangle2D1.clone());
      scrollRectToVisible(new Rectangle((int)localRectangle2D2.getX(), (int)localRectangle2D2.getY(), (int)localRectangle2D2.getWidth(), (int)localRectangle2D2.getHeight()));
    }
  }
  
  public void scrollPointToVisible(Point2D paramPoint2D)
  {
    if (paramPoint2D != null) {
      scrollRectToVisible(new Rectangle((int)paramPoint2D.getX(), (int)paramPoint2D.getY(), 1, 1));
    }
  }
  
  public boolean isEditing()
  {
    GraphUI localGraphUI = getUI();
    if (localGraphUI != null) {
      return localGraphUI.isEditing(this);
    }
    return false;
  }
  
  public boolean stopEditing()
  {
    GraphUI localGraphUI = getUI();
    if (localGraphUI != null) {
      return localGraphUI.stopEditing(this);
    }
    return false;
  }
  
  public void cancelEditing()
  {
    GraphUI localGraphUI = getUI();
    if (localGraphUI != null) {
      localGraphUI.cancelEditing(this);
    }
  }
  
  public void startEditingAtCell(Object paramObject)
  {
    GraphUI localGraphUI = getUI();
    if (localGraphUI != null) {
      localGraphUI.startEditingAtCell(this, paramObject);
    }
  }
  
  public Object getEditingCell()
  {
    GraphUI localGraphUI = getUI();
    if (localGraphUI != null) {
      return localGraphUI.getEditingCell(this);
    }
    return null;
  }
  
  public void graphDidChange()
  {
    revalidate();
    repaint();
  }
  
  public void refresh()
  {
    clearOffscreen();
    repaint();
  }
  
  public BufferedImage getImage(Color paramColor, int paramInt)
  {
    Object[] arrayOfObject = getRoots();
    Rectangle2D localRectangle2D = getCellBounds(arrayOfObject);
    if (localRectangle2D != null)
    {
      toScreen(localRectangle2D);
      GraphicsConfiguration localGraphicsConfiguration = getGraphicsConfiguration();
      BufferedImage localBufferedImage = null;
      if (localGraphicsConfiguration != null) {
        localBufferedImage = getGraphicsConfiguration().createCompatibleImage((int)localRectangle2D.getWidth() + 2 * paramInt, (int)localRectangle2D.getHeight() + 2 * paramInt, paramColor != null ? 1 : 2);
      } else {
        localBufferedImage = new BufferedImage((int)localRectangle2D.getWidth() + 2 * paramInt, (int)localRectangle2D.getHeight() + 2 * paramInt, paramColor != null ? 1 : 2);
      }
      Graphics2D localGraphics2D = localBufferedImage.createGraphics();
      if (paramColor != null)
      {
        localGraphics2D.setColor(paramColor);
        localGraphics2D.fillRect(0, 0, localBufferedImage.getWidth(), localBufferedImage.getHeight());
      }
      else
      {
        localGraphics2D.setComposite(AlphaComposite.getInstance(1, 0.0F));
        localGraphics2D.fillRect(0, 0, localBufferedImage.getWidth(), localBufferedImage.getHeight());
        localGraphics2D.setComposite(AlphaComposite.SrcOver);
      }
      localGraphics2D.translate((int)(-localRectangle2D.getX() + paramInt), (int)(-localRectangle2D.getY() + paramInt));
      print(localGraphics2D);
      localGraphics2D.dispose();
      return localBufferedImage;
    }
    return null;
  }
  
  public Rectangle2D getClipRectangle(GraphLayoutCacheEvent.GraphLayoutCacheChange paramGraphLayoutCacheChange)
  {
    List localList1 = DefaultGraphModel.getDescendants(getModel(), paramGraphLayoutCacheChange.getRemoved());
    Object localObject1 = (localList1 != null) && (!localList1.isEmpty()) ? getCellBounds(localList1.toArray()) : null;
    List localList2 = DefaultGraphModel.getDescendants(getModel(), paramGraphLayoutCacheChange.getInserted());
    Rectangle2D localRectangle2D1 = (localList2 != null) && (!localList2.isEmpty()) ? getCellBounds(localList2.toArray()) : null;
    List localList3 = DefaultGraphModel.getDescendants(getModel(), paramGraphLayoutCacheChange.getChanged());
    Rectangle2D localRectangle2D2 = (localList3 != null) && (!localList3.isEmpty()) ? getCellBounds(localList3.toArray()) : null;
    List localList4 = DefaultGraphModel.getDescendants(getModel(), paramGraphLayoutCacheChange.getContext());
    Rectangle2D localRectangle2D3 = (localList4 != null) && (!localList4.isEmpty()) ? getCellBounds(localList4.toArray()) : null;
    Object localObject2 = localObject1;
    if (localObject2 == null) {
      localObject2 = localRectangle2D1;
    } else if (localRectangle2D1 != null) {
      ((Rectangle2D)localObject2).add(localRectangle2D1);
    }
    if (localObject2 == null) {
      localObject2 = localRectangle2D2;
    } else if (localRectangle2D2 != null) {
      ((Rectangle2D)localObject2).add(localRectangle2D2);
    }
    if (localObject2 == null) {
      localObject2 = localRectangle2D3;
    } else if (localRectangle2D3 != null) {
      ((Rectangle2D)localObject2).add(localRectangle2D3);
    }
    return (Rectangle2D)localObject2;
  }
  
  private void writeObject(ObjectOutputStream paramObjectOutputStream)
    throws IOException
  {
    Vector localVector = new Vector();
    paramObjectOutputStream.defaultWriteObject();
    if ((this.graphModel instanceof Serializable))
    {
      localVector.addElement("graphModel");
      localVector.addElement(this.graphModel);
    }
    localVector.addElement("graphLayoutCache");
    localVector.addElement(this.graphLayoutCache);
    if ((this.selectionModel instanceof Serializable))
    {
      localVector.addElement("selectionModel");
      localVector.addElement(this.selectionModel);
    }
    if ((this.marquee instanceof Serializable))
    {
      localVector.addElement("marquee");
      localVector.addElement(this.marquee);
    }
    paramObjectOutputStream.writeObject(localVector);
    if ((getUIClassID().equals("GraphUI")) && (this.ui != null)) {
      this.ui.installUI(this);
    }
  }
  
  private void readObject(ObjectInputStream paramObjectInputStream)
    throws IOException, ClassNotFoundException
  {
    paramObjectInputStream.defaultReadObject();
    Vector localVector = (Vector)paramObjectInputStream.readObject();
    int i = 0;
    int j = localVector.size();
    if ((i < j) && (localVector.elementAt(i).equals("graphModel")))
    {
      this.graphModel = ((GraphModel)localVector.elementAt(++i));
      i++;
    }
    if ((i < j) && (localVector.elementAt(i).equals("graphLayoutCache")))
    {
      this.graphLayoutCache = ((GraphLayoutCache)localVector.elementAt(++i));
      i++;
    }
    if ((i < j) && (localVector.elementAt(i).equals("selectionModel")))
    {
      this.selectionModel = ((GraphSelectionModel)localVector.elementAt(++i));
      i++;
    }
    if ((i < j) && (localVector.elementAt(i).equals("marquee")))
    {
      this.marquee = ((BasicMarqueeHandler)localVector.elementAt(++i));
      i++;
    }
    if (this.listenerList.getListenerCount(GraphSelectionListener.class) != 0)
    {
      this.selectionRedirector = new GraphSelectionRedirector();
      this.selectionModel.addGraphSelectionListener(this.selectionRedirector);
    }
  }
  
  public Dimension getPreferredScrollableViewportSize()
  {
    return getPreferredSize();
  }
  
  public int getScrollableUnitIncrement(Rectangle paramRectangle, int paramInt1, int paramInt2)
  {
    if (paramInt1 == 1) {
      return 2;
    }
    return 4;
  }
  
  public int getScrollableBlockIncrement(Rectangle paramRectangle, int paramInt1, int paramInt2)
  {
    return paramInt1 == 1 ? paramRectangle.height : paramRectangle.width;
  }
  
  public boolean getScrollableTracksViewportWidth()
  {
    if ((getParent() instanceof JViewport)) {
      return ((JViewport)getParent()).getWidth() > getPreferredSize().width;
    }
    return false;
  }
  
  public boolean getScrollableTracksViewportHeight()
  {
    if ((getParent() instanceof JViewport)) {
      return ((JViewport)getParent()).getHeight() > getPreferredSize().height;
    }
    return false;
  }
  
  protected String paramString()
  {
    String str1 = this.editable ? "true" : "false";
    String str2 = this.invokesStopCellEditing ? "true" : "false";
    return super.paramString() + ",editable=" + str1 + ",invokesStopCellEditing=" + str2;
  }
  
  public static void main(String[] paramArrayOfString)
  {
    System.out.println("JGraph (v5.13.0.4)");
  }
  
  static
  {
    try
    {
      String str1 = System.getProperty("os.name");
      if (str1 != null) {
        IS_MAC = str1.toLowerCase().startsWith("mac os x");
      }
      String str2 = System.getProperty("java.version");
      if ((!str2.startsWith("1.4")) && (str2.startsWith("1.5"))) {}
      return;
    }
    catch (Exception localException) {}
  }
  
  protected class GraphSelectionRedirector
    implements Serializable, GraphSelectionListener
  {
    protected GraphSelectionRedirector() {}
    
    public void valueChanged(GraphSelectionEvent paramGraphSelectionEvent)
    {
      GraphSelectionEvent localGraphSelectionEvent = (GraphSelectionEvent)paramGraphSelectionEvent.cloneWithSource(JGraph.this);
      JGraph.this.fireValueChanged(localGraphSelectionEvent);
    }
  }
  
  public static class EmptySelectionModel
    extends DefaultGraphSelectionModel
  {
    protected static final EmptySelectionModel sharedInstance = new EmptySelectionModel();
    
    public EmptySelectionModel()
    {
      super();
    }
    
    public static EmptySelectionModel sharedInstance()
    {
      return sharedInstance;
    }
    
    public void setSelectionCells(Object[] paramArrayOfObject) {}
    
    public void addSelectionCells(Object[] paramArrayOfObject) {}
    
    public void removeSelectionCells(Object[] paramArrayOfObject) {}
  }
}


/* Location:              /home/menick/Scrivania/ProcessMining/CNMining/CNMining.jar!/org/jgraph/JGraph.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */