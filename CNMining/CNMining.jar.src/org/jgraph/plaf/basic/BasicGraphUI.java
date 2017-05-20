package org.jgraph.plaf.basic;

import java.awt.Color;
import java.awt.Component;
import java.awt.Container;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.HeadlessException;
import java.awt.Image;
import java.awt.Insets;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.RenderingHints;
import java.awt.Toolkit;
import java.awt.dnd.DropTarget;
import java.awt.dnd.DropTargetDragEvent;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.awt.event.ComponentListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.geom.AffineTransform;
import java.awt.geom.Dimension2D;
import java.awt.geom.Point2D;
import java.awt.geom.Point2D.Double;
import java.awt.geom.Rectangle2D;
import java.awt.geom.Rectangle2D.Double;
import java.awt.image.VolatileImage;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.io.PrintStream;
import java.io.Serializable;
import java.util.Map;
import java.util.TooManyListenersException;
import javax.swing.AbstractAction;
import javax.swing.Action;
import javax.swing.ActionMap;
import javax.swing.CellRendererPane;
import javax.swing.ImageIcon;
import javax.swing.InputMap;
import javax.swing.JComponent;
import javax.swing.JScrollBar;
import javax.swing.JScrollPane;
import javax.swing.JViewport;
import javax.swing.KeyStroke;
import javax.swing.SwingUtilities;
import javax.swing.Timer;
import javax.swing.TransferHandler;
import javax.swing.UIManager;
import javax.swing.event.CellEditorListener;
import javax.swing.event.ChangeEvent;
import javax.swing.event.MouseInputListener;
import javax.swing.plaf.ActionMapUIResource;
import javax.swing.plaf.ComponentUI;
import javax.swing.plaf.UIResource;
import org.jgraph.JGraph;
import org.jgraph.event.GraphLayoutCacheEvent;
import org.jgraph.event.GraphLayoutCacheEvent.GraphLayoutCacheChange;
import org.jgraph.event.GraphLayoutCacheListener;
import org.jgraph.event.GraphModelEvent;
import org.jgraph.event.GraphModelEvent.GraphModelChange;
import org.jgraph.event.GraphModelListener;
import org.jgraph.event.GraphSelectionEvent;
import org.jgraph.event.GraphSelectionListener;
import org.jgraph.graph.AbstractCellView;
import org.jgraph.graph.AttributeMap;
import org.jgraph.graph.BasicMarqueeHandler;
import org.jgraph.graph.CellHandle;
import org.jgraph.graph.CellView;
import org.jgraph.graph.CellViewRenderer;
import org.jgraph.graph.ConnectionSet;
import org.jgraph.graph.DefaultGraphModel;
import org.jgraph.graph.EdgeRenderer;
import org.jgraph.graph.EdgeView;
import org.jgraph.graph.GraphCell;
import org.jgraph.graph.GraphCellEditor;
import org.jgraph.graph.GraphConstants;
import org.jgraph.graph.GraphContext;
import org.jgraph.graph.GraphLayoutCache;
import org.jgraph.graph.GraphModel;
import org.jgraph.graph.GraphSelectionModel;
import org.jgraph.graph.GraphTransferHandler;
import org.jgraph.graph.ParentMap;
import org.jgraph.graph.PortView;
import org.jgraph.plaf.GraphUI;
import org.jgraph.util.RectUtils;

public class BasicGraphUI
  extends GraphUI
  implements Serializable
{
  public static final boolean DNDPREVIEW = (System.getProperty("java.version").compareTo("1.4.0") < 0) || (System.getProperty("java.version").compareTo("1.4.0") > 0);
  public static int SCROLLBORDER = 18;
  public static float SCROLLSTEP = 0.05F;
  public static int MAXCELLS = 20;
  public static int MAXHANDLES = 20;
  public static int MAXCLIPCELLS = 20;
  protected Dimension preferredMinSize;
  protected JGraph graph;
  protected GraphLayoutCache graphLayoutCache;
  protected GraphCellEditor cellEditor;
  protected boolean stopEditingInCompleteEditing;
  protected CellRendererPane rendererPane;
  protected Dimension preferredSize;
  protected boolean validCachedPreferredSize;
  protected GraphModel graphModel;
  protected GraphSelectionModel graphSelectionModel;
  protected CellHandle handle;
  protected BasicMarqueeHandler marquee;
  protected Component editingComponent;
  protected CellView focus;
  protected CellView lastFocus;
  protected Object editingCell;
  protected boolean editorHasDifferentSize;
  protected Point insertionLocation;
  protected int dropAction = 0;
  protected boolean snapSelectedView = false;
  protected PropertyChangeListener propertyChangeListener;
  protected MouseListener mouseListener;
  protected KeyListener keyListener;
  protected ComponentListener componentListener;
  protected CellEditorListener cellEditorListener = createCellEditorListener();
  protected GraphSelectionListener graphSelectionListener;
  protected GraphModelListener graphModelListener;
  protected GraphLayoutCacheListener graphLayoutCacheListener;
  protected TransferHandler defaultTransferHandler;
  protected GraphDropTargetListener defaultDropTargetListener;
  protected DropTarget dropTarget = null;
  
  public static ComponentUI createUI(JComponent paramJComponent)
  {
    return new BasicGraphUI();
  }
  
  protected void setModel(GraphModel paramGraphModel)
  {
    cancelEditing(this.graph);
    if ((this.graphModel != null) && (this.graphModelListener != null)) {
      this.graphModel.removeGraphModelListener(this.graphModelListener);
    }
    this.graphModel = paramGraphModel;
    if ((this.graphModel != null) && (this.graphModelListener != null)) {
      this.graphModel.addGraphModelListener(this.graphModelListener);
    }
    if (this.graphModel != null) {
      updateSize();
    }
  }
  
  protected void setGraphLayoutCache(GraphLayoutCache paramGraphLayoutCache)
  {
    cancelEditing(this.graph);
    if ((this.graphLayoutCache != null) && (this.graphLayoutCacheListener != null)) {
      this.graphLayoutCache.removeGraphLayoutCacheListener(this.graphLayoutCacheListener);
    }
    this.graphLayoutCache = paramGraphLayoutCache;
    if ((this.graphLayoutCache != null) && (this.graphLayoutCacheListener != null)) {
      this.graphLayoutCache.addGraphLayoutCacheListener(this.graphLayoutCacheListener);
    }
    updateSize();
  }
  
  protected void setMarquee(BasicMarqueeHandler paramBasicMarqueeHandler)
  {
    this.marquee = paramBasicMarqueeHandler;
  }
  
  protected void setSelectionModel(GraphSelectionModel paramGraphSelectionModel)
  {
    cancelEditing(this.graph);
    if ((this.graphSelectionListener != null) && (this.graphSelectionModel != null)) {
      this.graphSelectionModel.removeGraphSelectionListener(this.graphSelectionListener);
    }
    this.graphSelectionModel = paramGraphSelectionModel;
    if ((this.graphSelectionModel != null) && (this.graphSelectionListener != null)) {
      this.graphSelectionModel.addGraphSelectionListener(this.graphSelectionListener);
    }
    if (this.graph != null) {
      this.graph.repaint();
    }
  }
  
  public CellHandle getHandle()
  {
    return this.handle;
  }
  
  public int getDropAction()
  {
    return this.dropAction;
  }
  
  protected Object getFocusedCell()
  {
    if (this.focus != null) {
      return this.focus.getCell();
    }
    return null;
  }
  
  public Dimension2D getPreferredSize(JGraph paramJGraph, CellView paramCellView)
  {
    if (paramCellView != null)
    {
      Object localObject = paramCellView.getCell();
      String str = paramJGraph.convertValueToString(localObject);
      int i = (str != null) && (str.length() > 0) ? 1 : 0;
      int j = GraphConstants.getIcon(paramCellView.getAllAttributes()) != null ? 1 : 0;
      if ((i != 0) || (j != 0))
      {
        boolean bool = (getFocusedCell() == localObject) && (paramJGraph.hasFocus());
        Component localComponent = paramCellView.getRendererComponent(paramJGraph, bool, false, false);
        if (localComponent != null)
        {
          paramJGraph.add(localComponent);
          localComponent.validate();
          Dimension localDimension = localComponent.getPreferredSize();
          int k = 2 * GraphConstants.getInset(paramCellView.getAllAttributes());
          localDimension.width += k;
          localDimension.height += k;
          return localDimension;
        }
      }
      if (paramCellView.getBounds() == null) {
        if (this.graphLayoutCache != null) {
          paramCellView.update(null);
        } else if (paramJGraph.getGraphLayoutCache() != null) {
          paramCellView.update(paramJGraph.getGraphLayoutCache());
        } else {
          paramCellView.update(null);
        }
      }
      Rectangle2D localRectangle2D = paramCellView.getBounds();
      return new Dimension((int)localRectangle2D.getWidth(), (int)localRectangle2D.getHeight());
    }
    return null;
  }
  
  public Point getInsertionLocation()
  {
    return this.insertionLocation;
  }
  
  public void setInsertionLocation(Point paramPoint)
  {
    this.insertionLocation = paramPoint;
  }
  
  public void selectCellsForEvent(JGraph paramJGraph, Object[] paramArrayOfObject, MouseEvent paramMouseEvent)
  {
    selectCellsForEvent(paramArrayOfObject, paramMouseEvent);
  }
  
  public void selectCellsForEvent(Object[] paramArrayOfObject, MouseEvent paramMouseEvent)
  {
    if ((paramArrayOfObject == null) || (!this.graph.isSelectionEnabled())) {
      return;
    }
    if (isToggleSelectionEvent(paramMouseEvent)) {
      for (int i = 0; i < paramArrayOfObject.length; i++) {
        toggleSelectionCellForEvent(paramArrayOfObject[i], paramMouseEvent);
      }
    } else if (isAddToSelectionEvent(paramMouseEvent)) {
      this.graph.addSelectionCells(paramArrayOfObject);
    } else {
      this.graph.setSelectionCells(paramArrayOfObject);
    }
  }
  
  public void selectCellForEvent(Object paramObject, MouseEvent paramMouseEvent)
  {
    if (this.graph.isSelectionEnabled()) {
      if (isToggleSelectionEvent(paramMouseEvent)) {
        toggleSelectionCellForEvent(paramObject, paramMouseEvent);
      } else if (isAddToSelectionEvent(paramMouseEvent)) {
        this.graph.addSelectionCell(paramObject);
      } else {
        this.graph.setSelectionCell(paramObject);
      }
    }
  }
  
  protected void toggleSelectionCellForEvent(Object paramObject, MouseEvent paramMouseEvent)
  {
    if (this.graph.isCellSelected(paramObject)) {
      this.graph.removeSelectionCell(paramObject);
    } else {
      this.graph.addSelectionCell(paramObject);
    }
  }
  
  public boolean isAddToSelectionEvent(MouseEvent paramMouseEvent)
  {
    return paramMouseEvent.isShiftDown();
  }
  
  public boolean isToggleSelectionEvent(MouseEvent paramMouseEvent)
  {
    switch (Toolkit.getDefaultToolkit().getMenuShortcutKeyMask())
    {
    case 2: 
      return paramMouseEvent.isControlDown();
    case 8: 
      return paramMouseEvent.isAltDown();
    case 4: 
      return paramMouseEvent.isMetaDown();
    }
    return false;
  }
  
  public boolean isForceMarqueeEvent(MouseEvent paramMouseEvent)
  {
    if (this.marquee != null) {
      return this.marquee.isForceMarqueeEvent(paramMouseEvent);
    }
    return false;
  }
  
  public boolean isConstrainedMoveEvent(MouseEvent paramMouseEvent)
  {
    if (paramMouseEvent != null) {
      return paramMouseEvent.isShiftDown();
    }
    return false;
  }
  
  public boolean isEditing(JGraph paramJGraph)
  {
    return this.editingComponent != null;
  }
  
  public boolean stopEditing(JGraph paramJGraph)
  {
    if ((this.editingComponent != null) && (this.cellEditor.stopCellEditing()))
    {
      completeEditing(false, false, true);
      return true;
    }
    return false;
  }
  
  public void cancelEditing(JGraph paramJGraph)
  {
    if (this.editingComponent != null) {
      completeEditing(false, true, false);
    }
  }
  
  public void startEditingAtCell(JGraph paramJGraph, Object paramObject)
  {
    paramJGraph.scrollCellToVisible(paramObject);
    if (paramObject != null) {
      startEditing(paramObject, null);
    }
  }
  
  public Object getEditingCell(JGraph paramJGraph)
  {
    return this.editingCell;
  }
  
  public void installUI(JComponent paramJComponent)
  {
    if (paramJComponent == null) {
      throw new NullPointerException("null component passed to BasicGraphUI.installUI()");
    }
    this.graph = ((JGraph)paramJComponent);
    this.marquee = this.graph.getMarqueeHandler();
    prepareForUIInstall();
    installDefaults();
    installListeners();
    installKeyboardActions();
    installComponents();
    completeUIInstall();
  }
  
  protected void prepareForUIInstall()
  {
    this.stopEditingInCompleteEditing = true;
    this.preferredSize = new Dimension();
    setGraphLayoutCache(this.graph.getGraphLayoutCache());
    setModel(this.graph.getModel());
  }
  
  protected void completeUIInstall()
  {
    setSelectionModel(this.graph.getSelectionModel());
    updateSize();
  }
  
  protected void installDefaults()
  {
    if ((this.graph.getBackground() == null) || ((this.graph.getBackground() instanceof UIResource))) {
      this.graph.setBackground(UIManager.getColor("Tree.background"));
    }
    if ((this.graph.getFont() == null) || ((this.graph.getFont() instanceof UIResource))) {
      try
      {
        this.graph.setFont(UIManager.getFont("Tree.font"));
      }
      catch (Error localError) {}
    }
    if (JGraph.IS_MAC) {
      this.graph.setMarqueeColor(UIManager.getColor("MenuItem.selectionBackground"));
    } else {
      this.graph.setMarqueeColor(UIManager.getColor("Table.gridColor"));
    }
    this.graph.setHandleColor(UIManager.getColor("MenuItem.selectionBackground"));
    this.graph.setLockedHandleColor(UIManager.getColor("MenuItem.background"));
    this.graph.setGridColor(UIManager.getColor("Tree.selectionBackground"));
    this.graph.setOpaque(true);
  }
  
  protected void installListeners()
  {
    TransferHandler localTransferHandler = this.graph.getTransferHandler();
    if ((localTransferHandler == null) || ((localTransferHandler instanceof UIResource)))
    {
      this.defaultTransferHandler = createTransferHandler();
      try
      {
        this.graph.setTransferHandler(this.defaultTransferHandler);
      }
      catch (Error localError) {}
    }
    if (this.graphLayoutCache != null)
    {
      this.graphLayoutCacheListener = createGraphLayoutCacheListener();
      this.graphLayoutCache.addGraphLayoutCacheListener(this.graphLayoutCacheListener);
    }
    this.dropTarget = this.graph.getDropTarget();
    try
    {
      if (this.dropTarget != null)
      {
        this.defaultDropTargetListener = new GraphDropTargetListener();
        this.dropTarget.addDropTargetListener(this.defaultDropTargetListener);
      }
    }
    catch (TooManyListenersException localTooManyListenersException) {}
    if ((this.propertyChangeListener = createPropertyChangeListener()) != null) {
      this.graph.addPropertyChangeListener(this.propertyChangeListener);
    }
    if ((this.mouseListener = createMouseListener()) != null)
    {
      this.graph.addMouseListener(this.mouseListener);
      if ((this.mouseListener instanceof MouseMotionListener)) {
        this.graph.addMouseMotionListener((MouseMotionListener)this.mouseListener);
      }
    }
    if ((this.keyListener = createKeyListener()) != null) {
      this.graph.addKeyListener(this.keyListener);
    }
    if (((this.graphModelListener = createGraphModelListener()) != null) && (this.graphModel != null)) {
      this.graphModel.addGraphModelListener(this.graphModelListener);
    }
    if (((this.graphSelectionListener = createGraphSelectionListener()) != null) && (this.graphSelectionModel != null)) {
      this.graphSelectionModel.addGraphSelectionListener(this.graphSelectionListener);
    }
  }
  
  protected void installKeyboardActions()
  {
    InputMap localInputMap = getInputMap(1);
    SwingUtilities.replaceUIInputMap(this.graph, 1, localInputMap);
    localInputMap = getInputMap(0);
    SwingUtilities.replaceUIInputMap(this.graph, 0, localInputMap);
    SwingUtilities.replaceUIActionMap(this.graph, createActionMap());
  }
  
  InputMap getInputMap(int paramInt)
  {
    if (paramInt == 1) {
      return (InputMap)UIManager.get("Tree.ancestorInputMap");
    }
    if (paramInt == 0) {
      return (InputMap)UIManager.get("Tree.focusInputMap");
    }
    return null;
  }
  
  ActionMap createActionMap()
  {
    ActionMapUIResource localActionMapUIResource = new ActionMapUIResource();
    localActionMapUIResource.put("selectPrevious", new GraphIncrementAction(1, "selectPrevious", null));
    localActionMapUIResource.put("selectPreviousChangeLead", new GraphIncrementAction(1, "selectPreviousLead", null));
    localActionMapUIResource.put("selectPreviousExtendSelection", new GraphIncrementAction(1, "selectPreviousExtendSelection", null));
    localActionMapUIResource.put("selectParent", new GraphIncrementAction(4, "selectParent", null));
    localActionMapUIResource.put("selectParentChangeLead", new GraphIncrementAction(4, "selectParentChangeLead", null));
    localActionMapUIResource.put("selectNext", new GraphIncrementAction(3, "selectNext", null));
    localActionMapUIResource.put("selectNextChangeLead", new GraphIncrementAction(3, "selectNextLead", null));
    localActionMapUIResource.put("selectNextExtendSelection", new GraphIncrementAction(3, "selectNextExtendSelection", null));
    localActionMapUIResource.put("selectChild", new GraphIncrementAction(2, "selectChild", null));
    localActionMapUIResource.put("selectChildChangeLead", new GraphIncrementAction(2, "selectChildChangeLead", null));
    localActionMapUIResource.put("cancel", new GraphCancelEditingAction("cancel"));
    localActionMapUIResource.put("startEditing", new GraphEditAction("startEditing"));
    localActionMapUIResource.put("selectAll", new GraphSelectAllAction("selectAll", true));
    localActionMapUIResource.put("clearSelection", new GraphSelectAllAction("clearSelection", false));
    return localActionMapUIResource;
  }
  
  protected void installComponents()
  {
    if ((this.rendererPane = createCellRendererPane()) != null) {
      this.graph.add(this.rendererPane);
    }
  }
  
  protected TransferHandler createTransferHandler()
  {
    return new GraphTransferHandler();
  }
  
  protected PropertyChangeListener createPropertyChangeListener()
  {
    return new PropertyChangeHandler();
  }
  
  protected MouseListener createMouseListener()
  {
    return new MouseHandler();
  }
  
  protected KeyListener createKeyListener()
  {
    return new KeyHandler();
  }
  
  protected GraphSelectionListener createGraphSelectionListener()
  {
    return new GraphSelectionHandler();
  }
  
  protected CellEditorListener createCellEditorListener()
  {
    return new CellEditorHandler();
  }
  
  protected ComponentListener createComponentListener()
  {
    return new ComponentHandler();
  }
  
  protected CellRendererPane createCellRendererPane()
  {
    return new CellRendererPane();
  }
  
  protected GraphLayoutCacheListener createGraphLayoutCacheListener()
  {
    return new GraphLayoutCacheHandler();
  }
  
  protected GraphModelListener createGraphModelListener()
  {
    return new GraphModelHandler();
  }
  
  public void uninstallUI(JComponent paramJComponent)
  {
    cancelEditing(this.graph);
    uninstallListeners();
    uninstallKeyboardActions();
    uninstallComponents();
    completeUIUninstall();
  }
  
  protected void completeUIUninstall()
  {
    this.graphLayoutCache = null;
    this.rendererPane = null;
    this.componentListener = null;
    this.propertyChangeListener = null;
    this.keyListener = null;
    setSelectionModel(null);
    this.graph = null;
    this.graphModel = null;
    this.graphSelectionModel = null;
    this.graphSelectionListener = null;
  }
  
  protected void uninstallListeners()
  {
    TransferHandler localTransferHandler = this.graph.getTransferHandler();
    if (localTransferHandler == this.defaultTransferHandler) {
      this.graph.setTransferHandler(null);
    }
    if (this.graphLayoutCacheListener != null) {
      this.graphLayoutCache.removeGraphLayoutCacheListener(this.graphLayoutCacheListener);
    }
    if ((this.dropTarget != null) && (this.defaultDropTargetListener != null)) {
      this.dropTarget.removeDropTargetListener(this.defaultDropTargetListener);
    }
    if (this.componentListener != null) {
      this.graph.removeComponentListener(this.componentListener);
    }
    if (this.propertyChangeListener != null) {
      this.graph.removePropertyChangeListener(this.propertyChangeListener);
    }
    if (this.mouseListener != null)
    {
      this.graph.removeMouseListener(this.mouseListener);
      if ((this.mouseListener instanceof MouseMotionListener)) {
        this.graph.removeMouseMotionListener((MouseMotionListener)this.mouseListener);
      }
    }
    if (this.keyListener != null) {
      this.graph.removeKeyListener(this.keyListener);
    }
    if ((this.graphModel != null) && (this.graphModelListener != null)) {
      this.graphModel.removeGraphModelListener(this.graphModelListener);
    }
    if ((this.graphSelectionListener != null) && (this.graphSelectionModel != null)) {
      this.graphSelectionModel.removeGraphSelectionListener(this.graphSelectionListener);
    }
  }
  
  protected void uninstallKeyboardActions()
  {
    SwingUtilities.replaceUIActionMap(this.graph, null);
    SwingUtilities.replaceUIInputMap(this.graph, 1, null);
    SwingUtilities.replaceUIInputMap(this.graph, 0, null);
  }
  
  protected void uninstallComponents()
  {
    if (this.rendererPane != null) {
      this.graph.remove(this.rendererPane);
    }
  }
  
  public void paint(Graphics paramGraphics, JComponent paramJComponent)
  {
    if (this.graph != paramJComponent) {
      throw new InternalError("BasicGraphUI cannot paint " + paramJComponent.toString() + "; " + this.graph + " was expected.");
    }
    Object localObject = paramGraphics.getClipBounds();
    if (localObject != null) {
      localObject = (Rectangle2D)((Rectangle2D)localObject).clone();
    }
    if (this.graph.isDoubleBuffered())
    {
      Graphics localGraphics = this.graph.getOffgraphics();
      Image localImage = this.graph.getOffscreen();
      if ((localGraphics == null) || (localImage == null))
      {
        drawGraph(paramGraphics, (Rectangle2D)localObject);
        paintOverlay(paramGraphics);
        return;
      }
      if ((localImage instanceof VolatileImage))
      {
        int i = 0;
        do
        {
          localGraphics = this.graph.getOffgraphics();
          i++;
          if (i > 10) {
            this.graph.setVolatileOffscreen(false);
          }
        } while (((VolatileImage)localImage).contentsLost());
      }
      paramGraphics.drawImage(this.graph.getOffscreen(), 0, 0, this.graph);
      if (this.handle != null) {
        this.handle.paint(paramGraphics);
      }
      if (this.marquee != null) {
        this.marquee.paint(this.graph, paramGraphics);
      }
      paintOverlay(paramGraphics);
      localGraphics.setClip(null);
    }
    else
    {
      drawGraph(paramGraphics, (Rectangle2D)localObject);
    }
  }
  
  protected void paintOverlay(Graphics paramGraphics) {}
  
  public void drawGraph(Graphics paramGraphics, Rectangle2D paramRectangle2D)
  {
    paramGraphics.setClip(paramRectangle2D);
    Rectangle2D localRectangle2D = null;
    if (paramRectangle2D != null) {
      localRectangle2D = this.graph.fromScreen(new Rectangle2D.Double(paramRectangle2D.getX(), paramRectangle2D.getY(), paramRectangle2D.getWidth(), paramRectangle2D.getHeight()));
    }
    paintBackground(paramGraphics);
    Graphics2D localGraphics2D = (Graphics2D)paramGraphics;
    AffineTransform localAffineTransform = localGraphics2D.getTransform();
    if (this.graph.isAntiAliased()) {
      localGraphics2D.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
    }
    double d = this.graph.getScale();
    localGraphics2D.scale(d, d);
    paintCells(paramGraphics, localRectangle2D);
    localGraphics2D.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_OFF);
    if (!this.graph.isPortsScaled()) {
      localGraphics2D.setTransform(localAffineTransform);
    }
    paintForeground(paramGraphics);
    localGraphics2D.setTransform(localAffineTransform);
    if (this.handle != null) {
      this.handle.paint(paramGraphics);
    }
    if (this.marquee != null) {
      this.marquee.paint(this.graph, paramGraphics);
    }
    if (this.rendererPane != null) {
      this.rendererPane.removeAll();
    }
  }
  
  protected void paintCells(Graphics paramGraphics, Rectangle2D paramRectangle2D)
  {
    CellView[] arrayOfCellView = this.graphLayoutCache.getRoots();
    for (int i = 0; i < arrayOfCellView.length; i++)
    {
      Rectangle2D localRectangle2D = arrayOfCellView[i].getBounds();
      if (localRectangle2D != null) {
        if (paramRectangle2D == null) {
          paintCell(paramGraphics, arrayOfCellView[i], localRectangle2D, false);
        } else if (localRectangle2D.intersects(paramRectangle2D)) {
          paintCell(paramGraphics, arrayOfCellView[i], localRectangle2D, false);
        }
      }
    }
  }
  
  public void paintCell(Graphics paramGraphics, CellView paramCellView, Rectangle2D paramRectangle2D, boolean paramBoolean)
  {
    int i;
    if ((paramCellView != null) && (paramRectangle2D != null))
    {
      boolean bool = paramCellView == this.focus;
      i = this.graph.isCellSelected(paramCellView.getCell());
      Component localComponent = paramCellView.getRendererComponent(this.graph, i, bool, paramBoolean);
      this.rendererPane.paintComponent(paramGraphics, localComponent, this.graph, (int)paramRectangle2D.getX(), (int)paramRectangle2D.getY(), (int)paramRectangle2D.getWidth(), (int)paramRectangle2D.getHeight(), true);
    }
    if (!paramCellView.isLeaf())
    {
      CellView[] arrayOfCellView = paramCellView.getChildViews();
      for (i = 0; i < arrayOfCellView.length; i++) {
        paintCell(paramGraphics, arrayOfCellView[i], arrayOfCellView[i].getBounds(), paramBoolean);
      }
    }
  }
  
  protected void paintBackground(Graphics paramGraphics)
  {
    Rectangle localRectangle = paramGraphics.getClipBounds();
    paintBackgroundImage(paramGraphics, localRectangle);
    if (this.graph.isGridVisible()) {
      paintGrid(this.graph.getGridSize(), paramGraphics, localRectangle);
    }
  }
  
  protected void paintBackgroundImage(Graphics paramGraphics, Rectangle paramRectangle)
  {
    Component localComponent = this.graph.getBackgroundComponent();
    if (localComponent != null) {
      paintBackgroundComponent(paramGraphics, localComponent, paramRectangle);
    }
    ImageIcon localImageIcon = this.graph.getBackgroundImage();
    if (localImageIcon == null) {
      return;
    }
    Image localImage = localImageIcon.getImage();
    if (localImage == null) {
      return;
    }
    Graphics2D localGraphics2D = (Graphics2D)paramGraphics;
    AffineTransform localAffineTransform = null;
    if (this.graph.isBackgroundScaled())
    {
      localAffineTransform = localGraphics2D.getTransform();
      localGraphics2D.scale(this.graph.getScale(), this.graph.getScale());
    }
    localGraphics2D.drawImage(localImage, 0, 0, this.graph);
    if (localAffineTransform != null) {
      localGraphics2D.setTransform(localAffineTransform);
    }
  }
  
  protected void paintBackgroundComponent(Graphics paramGraphics, Component paramComponent)
  {
    try
    {
      paramGraphics.setPaintMode();
      Dimension localDimension = paramComponent.getPreferredSize();
      this.rendererPane.paintComponent(paramGraphics, paramComponent, this.graph, 0, 0, (int)localDimension.getWidth(), (int)localDimension.getHeight(), true);
    }
    catch (Exception localException) {}catch (Error localError) {}
  }
  
  protected void paintBackgroundComponent(Graphics paramGraphics, Component paramComponent, Rectangle paramRectangle)
  {
    try
    {
      paramGraphics.setPaintMode();
      Dimension localDimension = paramComponent.getPreferredSize();
      this.rendererPane.paintComponent(paramGraphics, paramComponent, this.graph, 0, 0, (int)localDimension.getWidth(), (int)localDimension.getHeight(), true);
    }
    catch (Exception localException) {}catch (Error localError) {}
  }
  
  protected void paintGrid(double paramDouble, Graphics paramGraphics, Rectangle2D paramRectangle2D)
  {
    if (paramRectangle2D == null)
    {
      Rectangle localRectangle = this.graph.getBounds();
      paramRectangle2D = new Rectangle2D.Double(0.0D, 0.0D, localRectangle.getWidth(), localRectangle.getHeight());
    }
    double d1 = paramRectangle2D.getX();
    double d2 = paramRectangle2D.getY();
    double d3 = d1 + paramRectangle2D.getWidth();
    double d4 = d2 + paramRectangle2D.getHeight();
    double d5 = Math.max(2.0D, paramDouble * this.graph.getScale());
    int i = (int)(Math.floor(d1 / d5) * d5);
    int j = (int)(Math.ceil(d3 / d5) * d5);
    int k = (int)(Math.floor(d2 / d5) * d5);
    int m = (int)(Math.ceil(d4 / d5) * d5);
    paramGraphics.setColor(this.graph.getGridColor());
    int i3;
    double d6;
    switch (this.graph.getGridMode())
    {
    case 1: 
      int n = d5 < 8.0D ? 0 : d5 > 16.0D ? 2 : 1;
      for (double d7 = i; d7 <= j; d7 += d5) {
        for (double d9 = k; d9 <= m; d9 += d5)
        {
          i3 = (int)Math.round(d7);
          int i4 = (int)Math.round(d9);
          paramGraphics.drawLine(i3 - n, i4, i3 + n, i4);
          paramGraphics.drawLine(i3, i4 - n, i3, i4 + n);
        }
      }
      break;
    case 2: 
      j += (int)Math.ceil(d5);
      m += (int)Math.ceil(d5);
      int i1;
      for (d6 = i; d6 <= j; d6 += d5)
      {
        i1 = (int)Math.round(d6);
        paramGraphics.drawLine(i1, k, i1, m);
      }
      for (d6 = k; d6 <= m; d6 += d5)
      {
        i1 = (int)Math.round(d6);
        paramGraphics.drawLine(i, i1, j, i1);
      }
      break;
    case 0: 
    default: 
      for (d6 = i; d6 <= j; d6 += d5) {
        for (double d8 = k; d8 <= m; d8 += d5)
        {
          int i2 = (int)Math.round(d6);
          i3 = (int)Math.round(d8);
          paramGraphics.drawLine(i2, i3, i2, i3);
        }
      }
    }
  }
  
  protected void paintForeground(Graphics paramGraphics)
  {
    if ((this.graph.isPortsVisible()) && (this.graph.isPortsOnTop())) {
      paintPorts(paramGraphics, this.graphLayoutCache.getPorts());
    }
  }
  
  public void paintPorts(Graphics paramGraphics, CellView[] paramArrayOfCellView)
  {
    if (paramArrayOfCellView != null)
    {
      Rectangle localRectangle = paramGraphics.getClipBounds();
      for (int i = 0; i < paramArrayOfCellView.length; i++) {
        if (paramArrayOfCellView[i] != null)
        {
          Rectangle2D localRectangle2D = paramArrayOfCellView[i].getBounds();
          Rectangle2D.Double localDouble = new Rectangle2D.Double(localRectangle2D.getX(), localRectangle2D.getY(), localRectangle2D.getWidth(), localRectangle2D.getHeight());
          Object localObject = new Point2D.Double(localDouble.getCenterX(), localDouble.getCenterY());
          if (!this.graph.isPortsScaled()) {
            localObject = this.graph.toScreen((Point2D)localObject);
          }
          localDouble.setFrame(((Point2D)localObject).getX() - localDouble.getWidth() / 2.0D, ((Point2D)localObject).getY() - localDouble.getHeight() / 2.0D, localDouble.getWidth(), localDouble.getHeight());
          if ((localRectangle == null) || (localDouble.intersects(localRectangle))) {
            paintCell(paramGraphics, paramArrayOfCellView[i], localDouble, false);
          }
        }
      }
    }
  }
  
  public void updateHandle()
  {
    if (this.graphLayoutCache != null)
    {
      Object[] arrayOfObject = this.graphLayoutCache.getVisibleCells(this.graph.getSelectionCells());
      if ((arrayOfObject != null) && (arrayOfObject.length > 0)) {
        this.handle = createHandle(createContext(this.graph, arrayOfObject));
      } else {
        this.handle = null;
      }
    }
  }
  
  protected GraphContext createContext(JGraph paramJGraph, Object[] paramArrayOfObject)
  {
    return new GraphContext(paramJGraph, paramArrayOfObject);
  }
  
  public CellHandle createHandle(GraphContext paramGraphContext)
  {
    if ((paramGraphContext != null) && (!paramGraphContext.isEmpty()) && (this.graph.isEnabled())) {
      try
      {
        return new RootHandle(paramGraphContext);
      }
      catch (HeadlessException localHeadlessException) {}catch (RuntimeException localRuntimeException)
      {
        throw localRuntimeException;
      }
    }
    return null;
  }
  
  public void updateSize()
  {
    this.validCachedPreferredSize = false;
    this.graph.graphDidChange();
    updateHandle();
  }
  
  protected void updateCachedPreferredSize()
  {
    Object localObject = AbstractCellView.getBounds(this.graphLayoutCache.getRoots());
    if (localObject == null) {
      localObject = new Rectangle2D.Double();
    }
    Point2D.Double localDouble = new Point2D.Double(((Rectangle2D)localObject).getX() + ((Rectangle2D)localObject).getWidth(), ((Rectangle2D)localObject).getY() + ((Rectangle2D)localObject).getHeight());
    Dimension localDimension = this.graph.getMinimumSize();
    Point localPoint = localDimension != null ? this.graph.toScreen(new Point(localDimension.width, localDimension.height)) : new Point(0, 0);
    Point2D localPoint2D1 = this.graph.toScreen(localDouble);
    this.preferredSize = new Dimension((int)Math.max(localPoint.getX(), localPoint2D1.getX()), (int)Math.max(localPoint.getY(), localPoint2D1.getY()));
    ImageIcon localImageIcon = this.graph.getBackgroundImage();
    if (localImageIcon != null)
    {
      int i = localImageIcon.getIconHeight();
      int j = localImageIcon.getIconWidth();
      Point2D localPoint2D2 = this.graph.toScreen(new Point(j, i));
      this.preferredSize = new Dimension((int)Math.max(this.preferredSize.getWidth(), localPoint2D2.getX()), (int)Math.max(this.preferredSize.getHeight(), localPoint2D2.getY()));
    }
    Insets localInsets = this.graph.getInsets();
    if (localInsets != null) {
      this.preferredSize.setSize(this.preferredSize.getWidth() + localInsets.left + localInsets.right, this.preferredSize.getHeight() + localInsets.top + localInsets.bottom);
    }
    this.validCachedPreferredSize = true;
  }
  
  public void setPreferredMinSize(Dimension paramDimension)
  {
    this.preferredMinSize = paramDimension;
  }
  
  public Dimension getPreferredMinSize()
  {
    if (this.preferredMinSize == null) {
      return null;
    }
    return new Dimension(this.preferredMinSize);
  }
  
  public Dimension getPreferredSize(JComponent paramJComponent)
  {
    Dimension localDimension = getPreferredMinSize();
    if (!this.validCachedPreferredSize) {
      updateCachedPreferredSize();
    }
    if (this.graph != null)
    {
      if (localDimension != null) {
        return new Dimension(Math.max(localDimension.width, this.preferredSize.width), Math.max(localDimension.height, this.preferredSize.height));
      }
      return new Dimension(this.preferredSize.width, this.preferredSize.height);
    }
    if (localDimension != null) {
      return localDimension;
    }
    return new Dimension(0, 0);
  }
  
  public Dimension getMinimumSize(JComponent paramJComponent)
  {
    if (getPreferredMinSize() != null) {
      return getPreferredMinSize();
    }
    return new Dimension(0, 0);
  }
  
  public Dimension getMaximumSize(JComponent paramJComponent)
  {
    if (this.graph != null) {
      return getPreferredSize(this.graph);
    }
    if (getPreferredMinSize() != null) {
      return getPreferredMinSize();
    }
    return new Dimension(0, 0);
  }
  
  protected void completeEditing()
  {
    if ((this.graph.getInvokesStopCellEditing()) && (this.stopEditingInCompleteEditing) && (this.editingComponent != null)) {
      this.cellEditor.stopCellEditing();
    }
    completeEditing(false, true, false);
  }
  
  protected void completeEditing(boolean paramBoolean1, boolean paramBoolean2, boolean paramBoolean3)
  {
    if ((this.stopEditingInCompleteEditing) && (this.editingComponent != null))
    {
      Component localComponent = this.editingComponent;
      Object localObject1 = this.editingCell;
      GraphCellEditor localGraphCellEditor = this.cellEditor;
      int i = (this.graph != null) && ((this.graph.hasFocus()) || (SwingUtilities.findFocusOwner(this.editingComponent) != null)) ? 1 : 0;
      this.editingCell = null;
      this.editingComponent = null;
      if (paramBoolean1) {
        localGraphCellEditor.stopCellEditing();
      } else if (paramBoolean2) {
        localGraphCellEditor.cancelCellEditing();
      }
      this.graph.remove(localComponent);
      if (i != 0) {
        this.graph.requestFocus();
      }
      if (paramBoolean3)
      {
        Object localObject2 = localGraphCellEditor.getCellEditorValue();
        this.graphLayoutCache.valueForCellChanged(localObject1, localObject2);
      }
      updateSize();
      if ((localGraphCellEditor != null) && (this.cellEditorListener != null)) {
        localGraphCellEditor.removeCellEditorListener(this.cellEditorListener);
      }
      this.cellEditor = null;
    }
  }
  
  protected boolean startEditing(Object paramObject, MouseEvent paramMouseEvent)
  {
    completeEditing();
    if (this.graph.isCellEditable(paramObject))
    {
      CellView localCellView = this.graphLayoutCache.getMapping(paramObject, false);
      this.cellEditor = localCellView.getEditor();
      this.editingComponent = this.cellEditor.getGraphCellEditorComponent(this.graph, paramObject, this.graph.isCellSelected(paramObject));
      if (this.cellEditor.isCellEditable(paramMouseEvent))
      {
        Rectangle2D localRectangle2D = this.graph.getCellBounds(paramObject);
        this.editingCell = paramObject;
        Dimension localDimension = this.editingComponent.getPreferredSize();
        this.graph.add(this.editingComponent);
        Point2D localPoint2D = getEditorLocation(paramObject, localDimension, this.graph.toScreen(new Point2D.Double(localRectangle2D.getX(), localRectangle2D.getY())));
        this.editingComponent.setBounds((int)localPoint2D.getX(), (int)localPoint2D.getY(), (int)localDimension.getWidth(), (int)localDimension.getHeight());
        this.editingCell = paramObject;
        this.editingComponent.validate();
        if (this.cellEditorListener == null) {
          this.cellEditorListener = createCellEditorListener();
        }
        if ((this.cellEditor != null) && (this.cellEditorListener != null)) {
          this.cellEditor.addCellEditorListener(this.cellEditorListener);
        }
        Rectangle localRectangle = this.graph.getVisibleRect();
        this.graph.paintImmediately((int)localPoint2D.getX(), (int)localPoint2D.getY(), (int)(localRectangle.getWidth() + localRectangle.getX() - localRectangle2D.getX()), (int)localDimension.getHeight());
        if ((this.cellEditor.shouldSelectCell(paramMouseEvent)) && (this.graph.isSelectionEnabled()))
        {
          this.stopEditingInCompleteEditing = false;
          try
          {
            this.graph.setSelectionCell(paramObject);
          }
          catch (Exception localException)
          {
            System.err.println("Editing exception: " + localException);
          }
          this.stopEditingInCompleteEditing = true;
        }
        if ((paramMouseEvent instanceof MouseEvent))
        {
          Point localPoint = SwingUtilities.convertPoint(this.graph, new Point(paramMouseEvent.getX(), paramMouseEvent.getY()), this.editingComponent);
          Component localComponent = SwingUtilities.getDeepestComponentAt(this.editingComponent, localPoint.x, localPoint.y);
          if (localComponent != null) {
            new MouseInputHandler(this.graph, localComponent, paramMouseEvent);
          }
        }
        return true;
      }
      this.editingComponent = null;
    }
    return false;
  }
  
  protected Point2D getEditorLocation(Object paramObject, Dimension2D paramDimension2D, Point2D paramPoint2D)
  {
    CellView localCellView = this.graphLayoutCache.getMapping(paramObject, false);
    if ((localCellView instanceof EdgeView))
    {
      EdgeView localEdgeView = (EdgeView)localCellView;
      CellViewRenderer localCellViewRenderer = localEdgeView.getRenderer();
      if ((localCellViewRenderer instanceof EdgeRenderer))
      {
        Point2D localPoint2D = ((EdgeRenderer)localCellViewRenderer).getLabelPosition(localEdgeView);
        if (localPoint2D != null) {
          paramPoint2D = localPoint2D;
        } else {
          paramPoint2D = AbstractCellView.getCenterPoint(localEdgeView);
        }
        paramPoint2D.setLocation(Math.max(0.0D, paramPoint2D.getX() - paramDimension2D.getWidth() / 2.0D), Math.max(0.0D, paramPoint2D.getY() - paramDimension2D.getHeight() / 2.0D));
      }
      this.graph.toScreen(paramPoint2D);
    }
    return paramPoint2D;
  }
  
  public static void autoscroll(JGraph paramJGraph, Point paramPoint)
  {
    Rectangle localRectangle = paramJGraph.getBounds();
    if ((paramJGraph.getParent() instanceof JViewport)) {
      localRectangle = ((JViewport)paramJGraph.getParent()).getViewRect();
    }
    if (localRectangle.contains(paramPoint))
    {
      Point localPoint = new Point(paramPoint);
      int i = (int)(paramJGraph.getWidth() * SCROLLSTEP);
      int j = (int)(paramJGraph.getHeight() * SCROLLSTEP);
      if (localPoint.x - localRectangle.x < SCROLLBORDER) {
        localPoint.x -= i;
      }
      if (localPoint.y - localRectangle.y < SCROLLBORDER) {
        localPoint.y -= j;
      }
      if (localRectangle.x + localRectangle.width - localPoint.x < SCROLLBORDER) {
        localPoint.x += i;
      }
      if (localRectangle.y + localRectangle.height - localPoint.y < SCROLLBORDER) {
        localPoint.y += j;
      }
      paramJGraph.scrollPointToVisible(localPoint);
    }
  }
  
  public boolean isSnapSelectedView()
  {
    return this.snapSelectedView;
  }
  
  public void setSnapSelectedView(boolean paramBoolean)
  {
    this.snapSelectedView = paramBoolean;
  }
  
  public class GraphDropTargetListener
    extends BasicGraphDropTargetListener
    implements Serializable
  {
    public GraphDropTargetListener() {}
    
    protected void saveComponentState(JComponent paramJComponent) {}
    
    protected void restoreComponentState(JComponent paramJComponent)
    {
      if (BasicGraphUI.this.handle != null) {
        BasicGraphUI.this.handle.mouseDragged(null);
      }
    }
    
    protected void updateInsertionLocation(JComponent paramJComponent, Point paramPoint)
    {
      BasicGraphUI.this.setInsertionLocation(paramPoint);
      if (BasicGraphUI.this.handle != null)
      {
        int i = BasicGraphUI.this.dropAction == 1 ? 2 : 0;
        BasicGraphUI.this.handle.mouseDragged(new MouseEvent(paramJComponent, 0, 0L, i, paramPoint.x, paramPoint.y, 1, false));
      }
    }
    
    public void dragEnter(DropTargetDragEvent paramDropTargetDragEvent)
    {
      BasicGraphUI.this.dropAction = paramDropTargetDragEvent.getDropAction();
      super.dragEnter(paramDropTargetDragEvent);
    }
    
    public void dropActionChanged(DropTargetDragEvent paramDropTargetDragEvent)
    {
      BasicGraphUI.this.dropAction = paramDropTargetDragEvent.getDropAction();
      super.dropActionChanged(paramDropTargetDragEvent);
    }
  }
  
  public class MouseInputHandler
    implements MouseInputListener
  {
    protected Component source;
    protected Component destination;
    
    public MouseInputHandler(Component paramComponent1, Component paramComponent2, MouseEvent paramMouseEvent)
    {
      this.source = paramComponent1;
      this.destination = paramComponent2;
      this.source.addMouseListener(this);
      this.source.addMouseMotionListener(this);
      paramComponent2.dispatchEvent(SwingUtilities.convertMouseEvent(paramComponent1, paramMouseEvent, paramComponent2));
    }
    
    public void mouseClicked(MouseEvent paramMouseEvent)
    {
      if (this.destination != null) {
        this.destination.dispatchEvent(SwingUtilities.convertMouseEvent(this.source, paramMouseEvent, this.destination));
      }
    }
    
    public void mousePressed(MouseEvent paramMouseEvent) {}
    
    public void mouseReleased(MouseEvent paramMouseEvent)
    {
      if (this.destination != null) {
        this.destination.dispatchEvent(SwingUtilities.convertMouseEvent(this.source, paramMouseEvent, this.destination));
      }
      removeFromSource();
    }
    
    public void mouseEntered(MouseEvent paramMouseEvent)
    {
      if (!SwingUtilities.isLeftMouseButton(paramMouseEvent)) {
        removeFromSource();
      }
    }
    
    public void mouseExited(MouseEvent paramMouseEvent)
    {
      if (!SwingUtilities.isLeftMouseButton(paramMouseEvent)) {
        removeFromSource();
      }
    }
    
    public void mouseDragged(MouseEvent paramMouseEvent)
    {
      if (this.destination != null) {
        this.destination.dispatchEvent(SwingUtilities.convertMouseEvent(this.source, paramMouseEvent, this.destination));
      }
    }
    
    public void mouseMoved(MouseEvent paramMouseEvent)
    {
      removeFromSource();
    }
    
    protected void removeFromSource()
    {
      if (this.source != null)
      {
        this.source.removeMouseListener(this);
        this.source.removeMouseMotionListener(this);
      }
      this.source = (this.destination = null);
    }
  }
  
  private class GraphSelectAllAction
    extends AbstractAction
  {
    private boolean selectAll;
    
    public GraphSelectAllAction(String paramString, boolean paramBoolean)
    {
      this.selectAll = paramBoolean;
    }
    
    public void actionPerformed(ActionEvent paramActionEvent)
    {
      if ((BasicGraphUI.this.graph != null) && (BasicGraphUI.this.graph.isSelectionEnabled())) {
        if (this.selectAll) {
          BasicGraphUI.this.graph.setSelectionCells(BasicGraphUI.this.graph.getGraphLayoutCache().getVisibleCells(BasicGraphUI.this.graph.getRoots()));
        } else {
          BasicGraphUI.this.graph.clearSelection();
        }
      }
    }
    
    public boolean isEnabled()
    {
      return (BasicGraphUI.this.graph != null) && (BasicGraphUI.this.graph.isEnabled());
    }
  }
  
  private class GraphEditAction
    extends AbstractAction
  {
    public GraphEditAction(String paramString) {}
    
    public void actionPerformed(ActionEvent paramActionEvent)
    {
      if ((isEnabled()) && ((BasicGraphUI.this.getFocusedCell() instanceof GraphCell))) {
        BasicGraphUI.this.graph.startEditingAtCell(BasicGraphUI.this.getFocusedCell());
      }
    }
    
    public boolean isEnabled()
    {
      return (BasicGraphUI.this.graph != null) && (BasicGraphUI.this.graph.isEnabled());
    }
  }
  
  private class GraphCancelEditingAction
    extends AbstractAction
  {
    public GraphCancelEditingAction(String paramString) {}
    
    public void actionPerformed(ActionEvent paramActionEvent)
    {
      if (BasicGraphUI.this.graph != null) {
        BasicGraphUI.this.cancelEditing(BasicGraphUI.this.graph);
      }
    }
    
    public boolean isEnabled()
    {
      return (BasicGraphUI.this.graph != null) && (BasicGraphUI.this.graph.isEnabled()) && (BasicGraphUI.this.graph.isEditing());
    }
  }
  
  public class GraphIncrementAction
    extends AbstractAction
  {
    protected int direction;
    
    private GraphIncrementAction(int paramInt, String paramString)
    {
      this.direction = paramInt;
    }
    
    public void actionPerformed(ActionEvent paramActionEvent)
    {
      if (BasicGraphUI.this.graph != null)
      {
        int i = 70;
        Rectangle localRectangle = BasicGraphUI.this.graph.getVisibleRect();
        if (this.direction == 1) {
          localRectangle.translate(0, -i);
        } else if (this.direction == 2) {
          localRectangle.translate(i, 0);
        } else if (this.direction == 3) {
          localRectangle.translate(0, i);
        } else if (this.direction == 4) {
          localRectangle.translate(-i, 0);
        }
        BasicGraphUI.this.graph.scrollRectToVisible(localRectangle);
      }
    }
    
    public boolean isEnabled()
    {
      return (BasicGraphUI.this.graph != null) && (BasicGraphUI.this.graph.isEnabled());
    }
  }
  
  public class PropertyChangeHandler
    implements PropertyChangeListener, Serializable
  {
    public PropertyChangeHandler() {}
    
    public void propertyChange(PropertyChangeEvent paramPropertyChangeEvent)
    {
      if (paramPropertyChangeEvent.getSource() == BasicGraphUI.this.graph)
      {
        String str = paramPropertyChangeEvent.getPropertyName();
        if (str.equals("minimumSize"))
        {
          BasicGraphUI.this.updateCachedPreferredSize();
        }
        else if (str.equals("model"))
        {
          BasicGraphUI.this.setModel((GraphModel)paramPropertyChangeEvent.getNewValue());
        }
        else if (str.equals("view"))
        {
          BasicGraphUI.this.setGraphLayoutCache((GraphLayoutCache)paramPropertyChangeEvent.getNewValue());
          BasicGraphUI.this.graph.repaint();
        }
        else if (str.equals("marquee"))
        {
          BasicGraphUI.this.setMarquee((BasicMarqueeHandler)paramPropertyChangeEvent.getNewValue());
        }
        else if (str.equals("transferHandler"))
        {
          if (BasicGraphUI.this.dropTarget != null) {
            BasicGraphUI.this.dropTarget.removeDropTargetListener(BasicGraphUI.this.defaultDropTargetListener);
          }
          BasicGraphUI.this.dropTarget = BasicGraphUI.this.graph.getDropTarget();
          try
          {
            if (BasicGraphUI.this.dropTarget != null) {
              BasicGraphUI.this.dropTarget.addDropTargetListener(BasicGraphUI.this.defaultDropTargetListener);
            }
          }
          catch (TooManyListenersException localTooManyListenersException) {}
        }
        else if (str.equals("editable"))
        {
          boolean bool = ((Boolean)paramPropertyChangeEvent.getNewValue()).booleanValue();
          if ((!bool) && (BasicGraphUI.this.isEditing(BasicGraphUI.this.graph))) {
            BasicGraphUI.this.cancelEditing(BasicGraphUI.this.graph);
          }
        }
        else if (str.equals("selectionModel"))
        {
          BasicGraphUI.this.setSelectionModel(BasicGraphUI.this.graph.getSelectionModel());
        }
        else if ((str.equals("gridVisible")) || (str.equals("gridSize")) || (str.equals("gridColor")) || (str.equals("handleColor")) || (str.equals("lockedHandleColor")) || (str.equals("handleSize")) || (str.equals("portsVisible")) || (str.equals("antiAliased")))
        {
          BasicGraphUI.this.graph.repaint();
        }
        else if (str.equals("scale"))
        {
          BasicGraphUI.this.updateSize();
        }
        else if (str.equals("backgroundImage"))
        {
          BasicGraphUI.this.updateSize();
        }
        else if (str.equals("font"))
        {
          BasicGraphUI.this.completeEditing();
          BasicGraphUI.this.updateSize();
        }
        else if ((str.equals("componentOrientation")) && (BasicGraphUI.this.graph != null))
        {
          BasicGraphUI.this.graph.graphDidChange();
        }
      }
    }
  }
  
  public class RootHandle
    implements CellHandle, Serializable
  {
    protected transient double _mouseToViewDelta_x = 0.0D;
    protected transient double _mouseToViewDelta_y = 0.0D;
    protected transient boolean firstDrag = true;
    protected transient CellView[] views;
    protected transient CellView[] contextViews;
    protected transient CellView[] portViews;
    protected transient CellView targetGroup;
    protected transient CellView ignoreTargetGroup;
    protected transient Rectangle2D cachedBounds;
    protected transient Point2D initialLocation;
    protected transient CellHandle[] handles;
    protected transient Point2D start = null;
    protected transient Point2D last;
    protected transient Point2D snapStart;
    protected transient Point2D snapLast;
    protected transient Graphics offgraphics;
    protected boolean isMoving = false;
    protected boolean isDragging = false;
    protected transient CellHandle activeHandle = null;
    protected transient GraphContext context;
    protected boolean isContextVisible = true;
    protected boolean blockPaint = false;
    protected Point2D current;
    protected transient ConnectionSet disconnect = null;
    
    public RootHandle(GraphContext paramGraphContext)
    {
      this.context = paramGraphContext;
      if (!paramGraphContext.isEmpty())
      {
        this.views = paramGraphContext.createTemporaryCellViews();
        Rectangle2D localRectangle2D = BasicGraphUI.this.graph.toScreen(BasicGraphUI.this.graph.getCellBounds(paramGraphContext.getCells()));
        if (paramGraphContext.getDescendantCount() < BasicGraphUI.MAXCELLS)
        {
          this.contextViews = paramGraphContext.createTemporaryContextViews();
          this.initialLocation = BasicGraphUI.this.graph.toScreen(getInitialLocation(paramGraphContext.getCells()));
        }
        else
        {
          this.cachedBounds = localRectangle2D;
        }
        if ((this.initialLocation == null) && (localRectangle2D != null)) {
          this.initialLocation = new Point2D.Double(localRectangle2D.getX(), localRectangle2D.getY());
        }
        Object[] arrayOfObject = paramGraphContext.getCells();
        if (arrayOfObject.length < BasicGraphUI.MAXHANDLES)
        {
          this.handles = new CellHandle[this.views.length];
          for (int i = 0; i < this.views.length; i++) {
            this.handles[i] = this.views[i].getHandle(paramGraphContext);
          }
          this.portViews = paramGraphContext.createTemporaryPortViews();
        }
      }
    }
    
    protected Point2D getInitialLocation(Object[] paramArrayOfObject)
    {
      if ((paramArrayOfObject != null) && (paramArrayOfObject.length > 0))
      {
        Object localObject1 = null;
        for (int i = 0; i < paramArrayOfObject.length; i++)
        {
          Object localObject2;
          if ((BasicGraphUI.this.graphModel != null) && (BasicGraphUI.this.graphModel.isEdge(paramArrayOfObject[i])))
          {
            localObject2 = BasicGraphUI.this.graphLayoutCache.getMapping(paramArrayOfObject[i], false);
            if ((localObject2 instanceof EdgeView))
            {
              EdgeView localEdgeView = (EdgeView)localObject2;
              Point2D localPoint2D;
              if (localEdgeView.getSource() == null)
              {
                localPoint2D = localEdgeView.getPoint(0);
                if (localPoint2D != null) {
                  if (localObject1 == null) {
                    localObject1 = new Rectangle2D.Double(localPoint2D.getX(), localPoint2D.getY(), 0.0D, 0.0D);
                  } else {
                    Rectangle2D.union((Rectangle2D)localObject1, new Rectangle2D.Double(localPoint2D.getX(), localPoint2D.getY(), 0.0D, 0.0D), (Rectangle2D)localObject1);
                  }
                }
              }
              if (localEdgeView.getTarget() == null)
              {
                localPoint2D = localEdgeView.getPoint(localEdgeView.getPointCount() - 1);
                if (localPoint2D != null) {
                  if (localObject1 == null) {
                    localObject1 = new Rectangle2D.Double(localPoint2D.getX(), localPoint2D.getY(), 0.0D, 0.0D);
                  } else {
                    Rectangle2D.union((Rectangle2D)localObject1, new Rectangle2D.Double(localPoint2D.getX(), localPoint2D.getY(), 0.0D, 0.0D), (Rectangle2D)localObject1);
                  }
                }
              }
            }
          }
          else
          {
            localObject2 = BasicGraphUI.this.graph.getCellBounds(paramArrayOfObject[i]);
            if (localObject2 != null)
            {
              if (localObject1 == null) {
                localObject1 = (Rectangle2D)((Rectangle2D)localObject2).clone();
              }
              Rectangle2D.union((Rectangle2D)localObject1, (Rectangle2D)localObject2, (Rectangle2D)localObject1);
            }
          }
        }
        if (localObject1 != null) {
          return new Point2D.Double(((Rectangle2D)localObject1).getX(), ((Rectangle2D)localObject1).getY());
        }
      }
      return null;
    }
    
    public GraphContext getContext()
    {
      return this.context;
    }
    
    public void paint(Graphics paramGraphics)
    {
      if ((this.handles != null) && (this.handles.length < BasicGraphUI.MAXHANDLES)) {
        for (int i = 0; i < this.handles.length; i++) {
          if (this.handles[i] != null) {
            this.handles[i].paint(paramGraphics);
          }
        }
      }
      this.blockPaint = true;
      if ((!BasicGraphUI.this.graph.isXorEnabled()) && (this.current != null))
      {
        double d1 = this.current.getX() - this.start.getX();
        double d2 = this.current.getY() - this.start.getY();
        if ((d1 != 0.0D) || (d2 != 0.0D)) {
          overlay(paramGraphics);
        }
      }
      else
      {
        this.blockPaint = true;
      }
    }
    
    public void overlay(Graphics paramGraphics)
    {
      if ((this.isDragging) && (!BasicGraphUI.DNDPREVIEW)) {
        return;
      }
      Object localObject;
      if (this.cachedBounds != null)
      {
        paramGraphics.setColor(Color.black);
        paramGraphics.drawRect((int)this.cachedBounds.getX(), (int)this.cachedBounds.getY(), (int)this.cachedBounds.getWidth() - 2, (int)this.cachedBounds.getHeight() - 2);
      }
      else
      {
        localObject = (Graphics2D)paramGraphics;
        AffineTransform localAffineTransform = ((Graphics2D)localObject).getTransform();
        ((Graphics2D)localObject).scale(BasicGraphUI.this.graph.getScale(), BasicGraphUI.this.graph.getScale());
        int i;
        if (this.views != null) {
          for (i = 0; i < this.views.length; i++) {
            BasicGraphUI.this.paintCell(paramGraphics, this.views[i], this.views[i].getBounds(), true);
          }
        }
        if ((this.contextViews != null) && (this.isContextVisible)) {
          for (i = 0; i < this.contextViews.length; i++) {
            BasicGraphUI.this.paintCell(paramGraphics, this.contextViews[i], this.contextViews[i].getBounds(), true);
          }
        }
        if (!BasicGraphUI.this.graph.isPortsScaled()) {
          ((Graphics2D)localObject).setTransform(localAffineTransform);
        }
        if ((this.portViews != null) && (BasicGraphUI.this.graph.isPortsVisible())) {
          BasicGraphUI.this.paintPorts(paramGraphics, this.portViews);
        }
        ((Graphics2D)localObject).setTransform(localAffineTransform);
      }
      if (this.targetGroup != null)
      {
        localObject = BasicGraphUI.this.graph.toScreen((Rectangle2D)this.targetGroup.getBounds().clone());
        paramGraphics.setColor(BasicGraphUI.this.graph.getHandleColor());
        paramGraphics.fillRect((int)((Rectangle2D)localObject).getX() - 1, (int)((Rectangle2D)localObject).getY() - 1, (int)((Rectangle2D)localObject).getWidth() + 2, (int)((Rectangle2D)localObject).getHeight() + 2);
        paramGraphics.setColor(BasicGraphUI.this.graph.getMarqueeColor());
        paramGraphics.draw3DRect((int)((Rectangle2D)localObject).getX() - 2, (int)((Rectangle2D)localObject).getY() - 2, (int)((Rectangle2D)localObject).getWidth() + 3, (int)((Rectangle2D)localObject).getHeight() + 3, true);
      }
    }
    
    public void mouseMoved(MouseEvent paramMouseEvent)
    {
      if ((!paramMouseEvent.isConsumed()) && (this.handles != null)) {
        for (int i = this.handles.length - 1; (i >= 0) && (!paramMouseEvent.isConsumed()); i--) {
          if (this.handles[i] != null) {
            this.handles[i].mouseMoved(paramMouseEvent);
          }
        }
      }
    }
    
    public void mousePressed(MouseEvent paramMouseEvent)
    {
      if ((!paramMouseEvent.isConsumed()) && (BasicGraphUI.this.graph.isMoveable()))
      {
        if (this.handles != null) {
          for (int i = this.handles.length - 1; i >= 0; i--) {
            if (this.handles[i] != null)
            {
              this.handles[i].mousePressed(paramMouseEvent);
              if (paramMouseEvent.isConsumed())
              {
                this.activeHandle = this.handles[i];
                return;
              }
            }
          }
        }
        Object localObject1;
        Object localObject2;
        Object localObject3;
        if (this.views != null)
        {
          localObject1 = paramMouseEvent.getPoint();
          localObject2 = BasicGraphUI.this.graph.fromScreen((Point2D)((Point2D)localObject1).clone());
          CellView localCellView = findViewForPoint((Point2D)localObject2);
          if (localCellView != null)
          {
            if (BasicGraphUI.this.snapSelectedView)
            {
              localObject3 = localCellView.getBounds();
              this.start = BasicGraphUI.this.graph.toScreen(new Point2D.Double(((Rectangle2D)localObject3).getX(), ((Rectangle2D)localObject3).getY()));
              this.snapStart = BasicGraphUI.this.graph.snap((Point2D)this.start.clone());
              this._mouseToViewDelta_x = (((Point2D)localObject1).getX() - this.start.getX());
              this._mouseToViewDelta_y = (((Point2D)localObject1).getY() - this.start.getY());
            }
            else
            {
              this.snapStart = BasicGraphUI.this.graph.snap((Point2D)((Point2D)localObject1).clone());
              this._mouseToViewDelta_x = (this.snapStart.getX() - ((Point2D)localObject1).getX());
              this._mouseToViewDelta_y = (this.snapStart.getY() - ((Point2D)localObject1).getY());
              this.start = ((Point2D)this.snapStart.clone());
            }
            this.last = ((Point2D)this.start.clone());
            this.snapLast = ((Point2D)this.snapStart.clone());
            this.isContextVisible = ((this.contextViews != null) && (this.contextViews.length < BasicGraphUI.MAXCELLS) && ((!paramMouseEvent.isControlDown()) || (!BasicGraphUI.this.graph.isCloneable())));
            paramMouseEvent.consume();
          }
        }
        if ((BasicGraphUI.this.graph.isMoveIntoGroups()) || (BasicGraphUI.this.graph.isMoveOutOfGroups()))
        {
          localObject1 = this.context.getCells();
          localObject2 = BasicGraphUI.this.graph.getModel().getParent(localObject1[0]);
          for (int j = 1; j < localObject1.length; j++)
          {
            localObject3 = BasicGraphUI.this.graph.getModel().getParent(localObject1[j]);
            if (localObject2 != localObject3)
            {
              localObject2 = null;
              break;
            }
          }
          if (localObject2 != null) {
            this.ignoreTargetGroup = BasicGraphUI.this.graph.getGraphLayoutCache().getMapping(localObject2, false);
          }
        }
      }
    }
    
    protected CellView findViewForPoint(Point2D paramPoint2D)
    {
      double d = BasicGraphUI.this.graph.getTolerance();
      Rectangle2D.Double localDouble = new Rectangle2D.Double(paramPoint2D.getX() - d, paramPoint2D.getY() - d, 2.0D * d, 2.0D * d);
      for (int i = 0; i < this.views.length; i++) {
        if (this.views[i].intersects(BasicGraphUI.this.graph, localDouble)) {
          return this.views[i];
        }
      }
      return null;
    }
    
    protected CellView findUnselectedInnermostGroup(double paramDouble1, double paramDouble2)
    {
      Object[] arrayOfObject = BasicGraphUI.this.graph.getDescendants(BasicGraphUI.this.graph.getRoots());
      for (int i = arrayOfObject.length - 1; i >= 0; i--)
      {
        CellView localCellView = BasicGraphUI.this.graph.getGraphLayoutCache().getMapping(arrayOfObject[i], false);
        if ((localCellView != null) && (!localCellView.isLeaf()) && (!this.context.contains(localCellView.getCell())) && (localCellView.getBounds().contains(paramDouble1, paramDouble2))) {
          return localCellView;
        }
      }
      return null;
    }
    
    protected void startDragging(MouseEvent paramMouseEvent)
    {
      this.isDragging = true;
      if (BasicGraphUI.this.graph.isDragEnabled())
      {
        int i = (paramMouseEvent.isControlDown()) && (BasicGraphUI.this.graph.isCloneable()) ? 1 : 2;
        TransferHandler localTransferHandler = BasicGraphUI.this.graph.getTransferHandler();
        BasicGraphUI.this.setInsertionLocation(paramMouseEvent.getPoint());
        try
        {
          localTransferHandler.exportAsDrag(BasicGraphUI.this.graph, paramMouseEvent, i);
        }
        catch (Exception localException) {}
      }
    }
    
    public Component getFirstOpaqueParent(Component paramComponent)
    {
      if (paramComponent != null) {
        for (Object localObject = paramComponent; localObject != null; localObject = ((Component)localObject).getParent()) {
          if ((((Component)localObject).isOpaque()) && (!(localObject instanceof JViewport))) {
            return (Component)localObject;
          }
        }
      }
      return paramComponent;
    }
    
    protected void initOffscreen()
    {
      if (!BasicGraphUI.this.graph.isXorEnabled()) {
        return;
      }
      try
      {
        this.offgraphics = BasicGraphUI.this.graph.getOffgraphics();
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
    
    public void mouseDragged(MouseEvent paramMouseEvent)
    {
      boolean bool = BasicGraphUI.this.isConstrainedMoveEvent(paramMouseEvent);
      int i = 0;
      Object localObject1 = null;
      if ((this.firstDrag) && (BasicGraphUI.this.graph.isDoubleBuffered()) && (this.cachedBounds == null))
      {
        initOffscreen();
        this.firstDrag = false;
      }
      if ((paramMouseEvent != null) && (!paramMouseEvent.isConsumed()))
      {
        if (this.activeHandle != null)
        {
          this.activeHandle.mouseDragged(paramMouseEvent);
        }
        else if (this.start != null)
        {
          Graphics localGraphics = this.offgraphics != null ? this.offgraphics : BasicGraphUI.this.graph.getGraphics();
          Point localPoint = paramMouseEvent.getPoint();
          Point2D.Double localDouble = new Point2D.Double(localPoint.getX() - this._mouseToViewDelta_x, localPoint.getY() - this._mouseToViewDelta_y);
          Point2D localPoint2D1 = BasicGraphUI.this.graph.snap(localDouble);
          this.current = localPoint2D1;
          int j = BasicGraphUI.this.graph.getMinimumMove();
          double d1 = this.current.getX() - this.start.getX();
          double d2 = this.current.getY() - this.start.getY();
          if ((this.isMoving) || (Math.abs(d1) > j) || (Math.abs(d2) > j))
          {
            int k = 0;
            this.isMoving = true;
            if ((this.disconnect == null) && (BasicGraphUI.this.graph.isDisconnectOnMove())) {
              this.disconnect = this.context.disconnect(BasicGraphUI.this.graphLayoutCache.getAllDescendants(this.views));
            }
            double d3 = this.current.getX() - this.start.getX();
            double d4 = this.current.getY() - this.start.getY();
            d1 = this.current.getX() - this.last.getX();
            d2 = this.current.getY() - this.last.getY();
            Point2D localPoint2D2 = constrainDrag(paramMouseEvent, d3, d4, d1, d2);
            if (localPoint2D2 != null)
            {
              d1 = localPoint2D2.getX();
              d2 = localPoint2D2.getY();
            }
            double d5 = BasicGraphUI.this.graph.getScale();
            d1 /= d5;
            d2 /= d5;
            if ((BasicGraphUI.this.graph.isDragEnabled()) && (!this.isDragging)) {
              startDragging(paramMouseEvent);
            }
            if ((d1 != 0.0D) || (d2 != 0.0D))
            {
              Object localObject2;
              if ((this.offgraphics != null) || (!BasicGraphUI.this.graph.isXorEnabled()))
              {
                localObject1 = BasicGraphUI.this.graph.toScreen(AbstractCellView.getBounds(this.views));
                localObject2 = BasicGraphUI.this.graph.toScreen(AbstractCellView.getBounds(this.contextViews));
                if (localObject2 != null) {
                  ((Rectangle2D)localObject1).add((Rectangle2D)localObject2);
                }
              }
              if (BasicGraphUI.this.graph.isXorEnabled())
              {
                localGraphics.setColor(BasicGraphUI.this.graph.getForeground());
                localGraphics.setXORMode(BasicGraphUI.this.graph.getBackground().darker());
              }
              if ((!this.snapLast.equals(this.snapStart)) && ((this.offgraphics != null) || (!this.blockPaint)))
              {
                if (BasicGraphUI.this.graph.isXorEnabled()) {
                  overlay(localGraphics);
                }
                k = 1;
              }
              this.isContextVisible = (((!paramMouseEvent.isControlDown()) || (!BasicGraphUI.this.graph.isCloneable())) && (this.contextViews != null) && (this.contextViews.length < BasicGraphUI.MAXCELLS));
              this.blockPaint = false;
              if ((bool) && (this.cachedBounds == null))
              {
                localObject2 = BasicGraphUI.this.graphLayoutCache.getAllDescendants(this.views);
                for (int n = 0; n < localObject2.length; n++)
                {
                  CellView localCellView = BasicGraphUI.this.graphLayoutCache.getMapping(localObject2[n].getCell(), false);
                  AttributeMap localAttributeMap = localCellView.getAllAttributes();
                  localObject2[n].changeAttributes(BasicGraphUI.this.graph.getGraphLayoutCache(), (AttributeMap)localAttributeMap.clone());
                  localObject2[n].refresh(BasicGraphUI.this.graph.getGraphLayoutCache(), this.context, false);
                }
              }
              if (this.cachedBounds != null)
              {
                if (localObject1 != null) {
                  ((Rectangle2D)localObject1).add(this.cachedBounds);
                }
                this.cachedBounds.setFrame(this.cachedBounds.getX() + d1 * d5, this.cachedBounds.getY() + d2 * d5, this.cachedBounds.getWidth(), this.cachedBounds.getHeight());
                if (localObject1 != null) {
                  ((Rectangle2D)localObject1).add(this.cachedBounds);
                }
              }
              else
              {
                GraphLayoutCache.translateViews(this.views, d1, d2);
                if (this.views != null) {
                  BasicGraphUI.this.graphLayoutCache.update(this.views);
                }
                if (this.contextViews != null) {
                  BasicGraphUI.this.graphLayoutCache.update(this.contextViews);
                }
              }
              Object localObject4;
              if ((BasicGraphUI.this.graph.isAutoResizeGraph()) && ((paramMouseEvent.getX() > BasicGraphUI.this.graph.getWidth() - BasicGraphUI.SCROLLBORDER) || (paramMouseEvent.getY() > BasicGraphUI.this.graph.getHeight() - BasicGraphUI.SCROLLBORDER)))
              {
                int m = 25;
                localObject4 = null;
                if ((BasicGraphUI.this.graph.getParent() instanceof JViewport)) {
                  localObject4 = ((JViewport)BasicGraphUI.this.graph.getParent()).getViewRect();
                }
                if ((localObject4 != null) && (((Rectangle)localObject4).contains(paramMouseEvent.getPoint())))
                {
                  if (((Rectangle)localObject4).x + ((Rectangle)localObject4).width - paramMouseEvent.getPoint().x < BasicGraphUI.SCROLLBORDER)
                  {
                    BasicGraphUI.this.preferredSize.width = (Math.max(BasicGraphUI.this.preferredSize.width, (int)((Rectangle)localObject4).getWidth()) + m);
                    i = 1;
                  }
                  if (((Rectangle)localObject4).y + ((Rectangle)localObject4).height - paramMouseEvent.getPoint().y < BasicGraphUI.SCROLLBORDER)
                  {
                    BasicGraphUI.this.preferredSize.height = (Math.max(BasicGraphUI.this.preferredSize.height, (int)((Rectangle)localObject4).getHeight()) + m);
                    i = 1;
                  }
                  if (i != 0)
                  {
                    BasicGraphUI.this.graph.revalidate();
                    BasicGraphUI.autoscroll(BasicGraphUI.this.graph, paramMouseEvent.getPoint());
                    if (BasicGraphUI.this.graph.isDoubleBuffered()) {
                      initOffscreen();
                    }
                  }
                }
              }
              Object localObject3 = this.ignoreTargetGroup != null ? (Rectangle2D)this.ignoreTargetGroup.getBounds().clone() : null;
              if (this.targetGroup != null)
              {
                localObject4 = BasicGraphUI.this.graph.toScreen((Rectangle2D)this.targetGroup.getBounds().clone());
                if (localObject1 != null) {
                  ((Rectangle2D)localObject1).add((Rectangle2D)localObject4);
                } else {
                  localObject1 = localObject4;
                }
              }
              this.targetGroup = null;
              if ((BasicGraphUI.this.graph.isMoveIntoGroups()) && ((localObject3 == null) || (!((Rectangle2D)localObject3).intersects(AbstractCellView.getBounds(this.views)))))
              {
                this.targetGroup = (paramMouseEvent.isControlDown() ? null : findUnselectedInnermostGroup(localPoint2D1.getX() / d5, localPoint2D1.getY() / d5));
                if (this.targetGroup == this.ignoreTargetGroup) {
                  this.targetGroup = null;
                }
              }
              if ((!localPoint2D1.equals(this.snapStart)) && ((this.offgraphics != null) || (!this.blockPaint)) && (i == 0))
              {
                if (BasicGraphUI.this.graph.isXorEnabled()) {
                  overlay(localGraphics);
                }
                k = 1;
              }
              if (bool) {
                this.last = ((Point2D)this.start.clone());
              }
              this.last.setLocation(this.last.getX() + d1 * d5, this.last.getY() + d2 * d5);
              this.snapLast = localPoint2D1;
              if ((k != 0) && ((this.offgraphics != null) || (!BasicGraphUI.this.graph.isXorEnabled())))
              {
                if (localObject1 == null) {
                  localObject1 = new Rectangle2D.Double();
                }
                ((Rectangle2D)localObject1).add(BasicGraphUI.this.graph.toScreen(AbstractCellView.getBounds(this.views)));
                localObject4 = BasicGraphUI.this.graph.toScreen(AbstractCellView.getBounds(this.contextViews));
                if (localObject4 != null) {
                  ((Rectangle2D)localObject1).add((Rectangle2D)localObject4);
                }
                int i1 = PortView.SIZE + 4;
                if (BasicGraphUI.this.graph.isPortsScaled()) {
                  i1 = (int)(BasicGraphUI.this.graph.getScale() * i1);
                }
                int i2 = i1 / 2;
                ((Rectangle2D)localObject1).setFrame(((Rectangle2D)localObject1).getX() - i2, ((Rectangle2D)localObject1).getY() - i2, ((Rectangle2D)localObject1).getWidth() + i1, ((Rectangle2D)localObject1).getHeight() + i1);
                double d6 = Math.max(0.0D, ((Rectangle2D)localObject1).getX());
                double d7 = Math.max(0.0D, ((Rectangle2D)localObject1).getY());
                double d8 = d6 + ((Rectangle2D)localObject1).getWidth();
                double d9 = d7 + ((Rectangle2D)localObject1).getHeight();
                if ((this.isDragging) && (!BasicGraphUI.DNDPREVIEW)) {
                  return;
                }
                if (this.offgraphics != null) {
                  BasicGraphUI.this.graph.drawImage((int)d6, (int)d7, (int)d8, (int)d9, (int)d6, (int)d7, (int)d8, (int)d9);
                } else {
                  BasicGraphUI.this.graph.repaint((int)((Rectangle2D)localObject1).getX(), (int)((Rectangle2D)localObject1).getY(), (int)((Rectangle2D)localObject1).getWidth() + 1, (int)((Rectangle2D)localObject1).getHeight() + 1);
                }
              }
            }
          }
        }
      }
      else if (paramMouseEvent == null) {
        BasicGraphUI.this.graph.repaint();
      }
    }
    
    protected Point2D constrainDrag(MouseEvent paramMouseEvent, double paramDouble1, double paramDouble2, double paramDouble3, double paramDouble4)
    {
      boolean bool = BasicGraphUI.this.isConstrainedMoveEvent(paramMouseEvent);
      if ((bool) && (this.cachedBounds == null))
      {
        if (Math.abs(paramDouble1) < Math.abs(paramDouble2))
        {
          paramDouble3 = 0.0D;
          paramDouble4 = paramDouble2;
        }
        else
        {
          paramDouble3 = paramDouble1;
          paramDouble4 = 0.0D;
        }
      }
      else
      {
        if ((!BasicGraphUI.this.graph.isMoveBelowZero()) && (this.last != null) && (this.initialLocation != null) && (this.start != null))
        {
          if (this.initialLocation.getX() + paramDouble1 < 0.0D) {
            paramDouble3 = this.start.getX() - this.last.getX() - this.initialLocation.getX();
          }
          if (this.initialLocation.getY() + paramDouble2 < 0.0D) {
            paramDouble4 = this.start.getY() - this.last.getY() - this.initialLocation.getY();
          }
        }
        if ((!BasicGraphUI.this.graph.isMoveBeyondGraphBounds()) && (this.last != null) && (this.initialLocation != null) && (this.start != null))
        {
          Rectangle localRectangle = BasicGraphUI.this.graph.getBounds();
          Rectangle2D localRectangle2D = AbstractCellView.getBounds(this.views);
          if (this.initialLocation.getX() + paramDouble1 + localRectangle2D.getWidth() > localRectangle.getWidth()) {
            paramDouble3 = 0.0D;
          }
          if (this.initialLocation.getY() + paramDouble2 + localRectangle2D.getHeight() > localRectangle.getHeight()) {
            paramDouble4 = 0.0D;
          }
        }
      }
      return new Point2D.Double(paramDouble3, paramDouble4);
    }
    
    public void mouseReleased(MouseEvent paramMouseEvent)
    {
      try
      {
        if ((paramMouseEvent != null) && (!paramMouseEvent.isConsumed())) {
          if (this.activeHandle != null)
          {
            this.activeHandle.mouseReleased(paramMouseEvent);
            this.activeHandle = null;
          }
          else if ((this.isMoving) && (!paramMouseEvent.getPoint().equals(this.start)))
          {
            Object localObject3;
            Object localObject5;
            if (this.cachedBounds != null)
            {
              localObject1 = paramMouseEvent.getPoint();
              localObject2 = new Point2D.Double(((Point)localObject1).getX() - this._mouseToViewDelta_x, ((Point)localObject1).getY() - this._mouseToViewDelta_y);
              localObject3 = BasicGraphUI.this.graph.snap((Point2D)localObject2);
              double d1 = ((Point2D)localObject3).getX() - this.start.getX();
              double d2 = ((Point2D)localObject3).getY() - this.start.getY();
              if ((!BasicGraphUI.this.graph.isMoveBelowZero()) && (this.initialLocation.getX() + d1 < 0.0D)) {
                d1 = -1.0D * this.initialLocation.getX();
              }
              if ((!BasicGraphUI.this.graph.isMoveBelowZero()) && (this.initialLocation.getY() + d2 < 0.0D)) {
                d2 = -1.0D * this.initialLocation.getY();
              }
              localObject5 = BasicGraphUI.this.graph.fromScreen(new Point2D.Double(d1, d2));
              GraphLayoutCache.translateViews(this.views, ((Point2D)localObject5).getX(), ((Point2D)localObject5).getY());
            }
            Object localObject1 = BasicGraphUI.this.graphLayoutCache.getAllDescendants(this.views);
            Object localObject2 = GraphConstants.createAttributes((Object[])localObject1, null);
            if ((paramMouseEvent.isControlDown()) && (BasicGraphUI.this.graph.isCloneable()))
            {
              localObject3 = BasicGraphUI.this.graph.getDescendants(BasicGraphUI.this.graph.order(this.context.getCells()));
              Map localMap = BasicGraphUI.this.graphLayoutCache.getHiddenMapping();
              for (int i = 0; i < localObject3.length; i++)
              {
                localObject4 = ((Map)localObject2).get(localObject3[i]);
                if (localObject4 == null)
                {
                  CellView localCellView = (CellView)localMap.get(localObject3[i]);
                  if ((localCellView != null) && (!BasicGraphUI.this.graphModel.isPort(localCellView.getCell())))
                  {
                    localObject5 = (AttributeMap)localCellView.getAllAttributes().clone();
                    ((Map)localObject2).put(localObject3[i], ((AttributeMap)localObject5).clone());
                  }
                }
              }
              ConnectionSet localConnectionSet = ConnectionSet.create(BasicGraphUI.this.graphModel, (Object[])localObject3, false);
              Object localObject4 = ParentMap.create(BasicGraphUI.this.graphModel, (Object[])localObject3, false, true);
              localObject3 = BasicGraphUI.this.graphLayoutCache.insertClones((Object[])localObject3, BasicGraphUI.this.graph.cloneCells((Object[])localObject3), (Map)localObject2, localConnectionSet, (ParentMap)localObject4, 0.0D, 0.0D);
            }
            else if (BasicGraphUI.this.graph.isMoveable())
            {
              localObject3 = null;
              if (this.targetGroup != null) {
                localObject3 = new ParentMap(this.context.getCells(), this.targetGroup.getCell());
              } else if ((BasicGraphUI.this.graph.isMoveOutOfGroups()) && (this.ignoreTargetGroup != null) && (!this.ignoreTargetGroup.getBounds().intersects(AbstractCellView.getBounds(this.views)))) {
                localObject3 = new ParentMap(this.context.getCells(), null);
              }
              BasicGraphUI.this.graph.getGraphLayoutCache().edit((Map)localObject2, this.disconnect, (ParentMap)localObject3, null);
            }
            paramMouseEvent.consume();
          }
        }
      }
      catch (Exception localException)
      {
        localException.printStackTrace();
      }
      finally
      {
        this.ignoreTargetGroup = null;
        this.targetGroup = null;
        this.isDragging = false;
        this.disconnect = null;
        this.firstDrag = true;
        this.current = null;
        this.start = null;
      }
    }
  }
  
  public class MouseHandler
    extends MouseAdapter
    implements MouseMotionListener, Serializable
  {
    protected CellView cell;
    protected Object handler;
    protected transient Cursor previousCursor = null;
    
    public MouseHandler() {}
    
    public void mousePressed(MouseEvent paramMouseEvent)
    {
      this.handler = null;
      if ((!paramMouseEvent.isConsumed()) && (BasicGraphUI.this.graph.isEnabled()))
      {
        BasicGraphUI.this.graph.requestFocus();
        int i = BasicGraphUI.this.graph.getTolerance();
        Rectangle2D localRectangle2D = BasicGraphUI.this.graph.fromScreen(new Rectangle2D.Double(paramMouseEvent.getX() - i, paramMouseEvent.getY() - i, 2 * i, 2 * i));
        BasicGraphUI.this.lastFocus = BasicGraphUI.this.focus;
        BasicGraphUI.this.focus = ((BasicGraphUI.this.focus != null) && (BasicGraphUI.this.focus.intersects(BasicGraphUI.this.graph, localRectangle2D)) ? BasicGraphUI.this.focus : null);
        this.cell = BasicGraphUI.this.graph.getNextSelectableViewAt(BasicGraphUI.this.focus, paramMouseEvent.getX(), paramMouseEvent.getY());
        if (BasicGraphUI.this.focus == null) {
          BasicGraphUI.this.focus = this.cell;
        }
        BasicGraphUI.this.completeEditing();
        boolean bool = BasicGraphUI.this.isForceMarqueeEvent(paramMouseEvent);
        int j = (BasicGraphUI.this.graph.isGroupsEditable()) || ((BasicGraphUI.this.focus != null) && (BasicGraphUI.this.focus.isLeaf())) ? 1 : 0;
        if (!bool) {
          if ((paramMouseEvent.getClickCount() == BasicGraphUI.this.graph.getEditClickCount()) && (BasicGraphUI.this.focus != null) && (j != 0) && (BasicGraphUI.this.focus.getParentView() == null) && (BasicGraphUI.this.graph.isCellEditable(BasicGraphUI.this.focus.getCell())) && (handleEditTrigger(this.cell.getCell(), paramMouseEvent)))
          {
            paramMouseEvent.consume();
            this.cell = null;
          }
          else if (!BasicGraphUI.this.isToggleSelectionEvent(paramMouseEvent))
          {
            if (BasicGraphUI.this.handle != null)
            {
              BasicGraphUI.this.handle.mousePressed(paramMouseEvent);
              this.handler = BasicGraphUI.this.handle;
            }
            if ((!paramMouseEvent.isConsumed()) && (this.cell != null) && (!BasicGraphUI.this.graph.isCellSelected(this.cell.getCell())))
            {
              BasicGraphUI.this.selectCellForEvent(this.cell.getCell(), paramMouseEvent);
              BasicGraphUI.this.focus = this.cell;
              if (BasicGraphUI.this.handle != null)
              {
                BasicGraphUI.this.handle.mousePressed(paramMouseEvent);
                this.handler = BasicGraphUI.this.handle;
              }
              paramMouseEvent.consume();
              this.cell = null;
            }
          }
        }
        if ((!paramMouseEvent.isConsumed()) && (BasicGraphUI.this.marquee != null) && ((!BasicGraphUI.this.isToggleSelectionEvent(paramMouseEvent)) || (BasicGraphUI.this.focus == null) || (bool)))
        {
          BasicGraphUI.this.marquee.mousePressed(paramMouseEvent);
          this.handler = BasicGraphUI.this.marquee;
        }
      }
    }
    
    protected boolean handleEditTrigger(Object paramObject, MouseEvent paramMouseEvent)
    {
      BasicGraphUI.this.graph.scrollCellToVisible(paramObject);
      if (paramObject != null) {
        BasicGraphUI.this.startEditing(paramObject, paramMouseEvent);
      }
      return BasicGraphUI.this.graph.isEditing();
    }
    
    public void mouseDragged(MouseEvent paramMouseEvent)
    {
      BasicGraphUI.autoscroll(BasicGraphUI.this.graph, paramMouseEvent.getPoint());
      if (BasicGraphUI.this.graph.isEnabled())
      {
        if ((this.handler != null) && (this.handler == BasicGraphUI.this.marquee))
        {
          BasicGraphUI.this.marquee.mouseDragged(paramMouseEvent);
        }
        else if ((this.handler == null) && (!BasicGraphUI.this.isEditing(BasicGraphUI.this.graph)) && (BasicGraphUI.this.focus != null))
        {
          if (!BasicGraphUI.this.graph.isCellSelected(BasicGraphUI.this.focus.getCell()))
          {
            BasicGraphUI.this.selectCellForEvent(BasicGraphUI.this.focus.getCell(), paramMouseEvent);
            this.cell = null;
          }
          if (BasicGraphUI.this.handle != null) {
            BasicGraphUI.this.handle.mousePressed(paramMouseEvent);
          }
          this.handler = BasicGraphUI.this.handle;
        }
        if ((BasicGraphUI.this.handle != null) && (this.handler == BasicGraphUI.this.handle)) {
          BasicGraphUI.this.handle.mouseDragged(paramMouseEvent);
        }
      }
    }
    
    public void mouseMoved(MouseEvent paramMouseEvent)
    {
      if (this.previousCursor == null) {
        this.previousCursor = BasicGraphUI.this.graph.getCursor();
      }
      if ((BasicGraphUI.this.graph != null) && (BasicGraphUI.this.graph.isEnabled()))
      {
        if (BasicGraphUI.this.marquee != null) {
          BasicGraphUI.this.marquee.mouseMoved(paramMouseEvent);
        }
        if (BasicGraphUI.this.handle != null) {
          BasicGraphUI.this.handle.mouseMoved(paramMouseEvent);
        }
        if ((!paramMouseEvent.isConsumed()) && (this.previousCursor != null))
        {
          Cursor localCursor = BasicGraphUI.this.graph.getCursor();
          if (localCursor != this.previousCursor) {
            BasicGraphUI.this.graph.setCursor(this.previousCursor);
          }
          this.previousCursor = null;
        }
      }
    }
    
    public void mouseReleased(MouseEvent paramMouseEvent)
    {
      try
      {
        if ((paramMouseEvent != null) && (!paramMouseEvent.isConsumed()) && (BasicGraphUI.this.graph != null) && (BasicGraphUI.this.graph.isEnabled()))
        {
          if ((this.handler == BasicGraphUI.this.marquee) && (BasicGraphUI.this.marquee != null)) {
            BasicGraphUI.this.marquee.mouseReleased(paramMouseEvent);
          } else if ((this.handler == BasicGraphUI.this.handle) && (BasicGraphUI.this.handle != null)) {
            BasicGraphUI.this.handle.mouseReleased(paramMouseEvent);
          }
          if ((isDescendant(this.cell, BasicGraphUI.this.focus)) && (paramMouseEvent.getModifiers() != 0)) {
            this.cell = BasicGraphUI.this.focus;
          }
          if ((!paramMouseEvent.isConsumed()) && (this.cell != null))
          {
            Object localObject1 = this.cell.getCell();
            boolean bool = BasicGraphUI.this.graph.isCellSelected(localObject1);
            if ((!paramMouseEvent.isPopupTrigger()) || (!bool))
            {
              BasicGraphUI.this.selectCellForEvent(localObject1, paramMouseEvent);
              BasicGraphUI.this.focus = this.cell;
              postProcessSelection(paramMouseEvent, localObject1, bool);
            }
          }
        }
      }
      finally
      {
        this.handler = null;
        this.cell = null;
      }
    }
    
    protected void postProcessSelection(MouseEvent paramMouseEvent, Object paramObject, boolean paramBoolean)
    {
      if ((paramBoolean) && (BasicGraphUI.this.graph.isCellSelected(paramObject)) && (paramMouseEvent.getModifiers() != 0))
      {
        Object localObject1 = paramObject;
        Object localObject2 = null;
        while (((localObject2 = BasicGraphUI.this.graphModel.getParent(localObject1)) != null) && (BasicGraphUI.this.graphLayoutCache.isVisible(localObject2))) {
          localObject1 = localObject2;
        }
        BasicGraphUI.this.selectCellForEvent(localObject1, paramMouseEvent);
        BasicGraphUI.this.lastFocus = BasicGraphUI.this.focus;
        BasicGraphUI.this.focus = BasicGraphUI.this.graphLayoutCache.getMapping(localObject1, false);
      }
    }
    
    protected boolean isDescendant(CellView paramCellView1, CellView paramCellView2)
    {
      if ((paramCellView1 == null) || (paramCellView2 == null)) {
        return false;
      }
      Object localObject1 = paramCellView1.getCell();
      Object localObject2 = paramCellView2.getCell();
      Object localObject3 = localObject2;
      do
      {
        if (localObject3 == localObject1) {
          return true;
        }
      } while ((localObject3 = BasicGraphUI.this.graphModel.getParent(localObject3)) != null);
      return false;
    }
  }
  
  public class KeyHandler
    extends KeyAdapter
    implements Serializable
  {
    protected Action repeatKeyAction;
    protected boolean isKeyDown;
    
    public KeyHandler() {}
    
    public void keyPressed(KeyEvent paramKeyEvent)
    {
      if ((BasicGraphUI.this.graph != null) && (BasicGraphUI.this.graph.hasFocus()) && (BasicGraphUI.this.graph.isEnabled()))
      {
        KeyStroke localKeyStroke = KeyStroke.getKeyStroke(paramKeyEvent.getKeyCode(), paramKeyEvent.getModifiers());
        if (BasicGraphUI.this.graph.getConditionForKeyStroke(localKeyStroke) == 0)
        {
          ActionListener localActionListener = BasicGraphUI.this.graph.getActionForKeyStroke(localKeyStroke);
          if ((localActionListener instanceof Action)) {
            this.repeatKeyAction = ((Action)localActionListener);
          } else {
            this.repeatKeyAction = null;
          }
        }
        else
        {
          this.repeatKeyAction = null;
          if (localKeyStroke.getKeyCode() == 27)
          {
            if (BasicGraphUI.this.marquee != null) {
              BasicGraphUI.this.marquee.mouseReleased(null);
            }
            if (BasicGraphUI.this.mouseListener != null) {
              BasicGraphUI.this.mouseListener.mouseReleased(null);
            }
            BasicGraphUI.this.updateHandle();
            BasicGraphUI.this.graph.refresh();
          }
        }
        if ((this.isKeyDown) && (this.repeatKeyAction != null))
        {
          this.repeatKeyAction.actionPerformed(new ActionEvent(BasicGraphUI.this.graph, 1001, ""));
          paramKeyEvent.consume();
        }
        else
        {
          this.isKeyDown = true;
        }
      }
    }
    
    public void keyReleased(KeyEvent paramKeyEvent)
    {
      this.isKeyDown = false;
    }
  }
  
  public class CellEditorHandler
    implements CellEditorListener, Serializable
  {
    public CellEditorHandler() {}
    
    public void editingStopped(ChangeEvent paramChangeEvent)
    {
      BasicGraphUI.this.completeEditing(false, false, true);
    }
    
    public void editingCanceled(ChangeEvent paramChangeEvent)
    {
      BasicGraphUI.this.completeEditing(false, false, false);
    }
  }
  
  public class GraphSelectionHandler
    implements GraphSelectionListener, Serializable
  {
    public GraphSelectionHandler() {}
    
    public void valueChanged(GraphSelectionEvent paramGraphSelectionEvent)
    {
      BasicGraphUI.this.updateHandle();
      Object[] arrayOfObject = paramGraphSelectionEvent.getCells();
      Object localObject;
      if ((arrayOfObject != null) && (arrayOfObject.length <= BasicGraphUI.MAXCLIPCELLS))
      {
        localObject = BasicGraphUI.this.graph.toScreen(BasicGraphUI.this.graph.getCellBounds(arrayOfObject));
        if (BasicGraphUI.this.focus != null) {
          if (localObject != null) {
            Rectangle2D.union((Rectangle2D)localObject, BasicGraphUI.this.focus.getBounds(), (Rectangle2D)localObject);
          } else {
            localObject = BasicGraphUI.this.focus.getBounds();
          }
        }
        if (BasicGraphUI.this.lastFocus != null) {
          if (localObject != null) {
            Rectangle2D.union((Rectangle2D)localObject, BasicGraphUI.this.lastFocus.getBounds(), (Rectangle2D)localObject);
          } else {
            localObject = BasicGraphUI.this.lastFocus.getBounds();
          }
        }
        if (localObject != null)
        {
          Rectangle2D localRectangle2D = BasicGraphUI.this.graph.fromScreen((Rectangle2D)((Rectangle2D)localObject).clone());
          BasicGraphUI.this.graph.addOffscreenDirty(localRectangle2D);
          int i = BasicGraphUI.this.graph.getHandleSize() + 1;
          BasicGraphUI.this.updateHandle();
          Rectangle localRectangle = new Rectangle((int)(((Rectangle2D)localObject).getX() - i), (int)(((Rectangle2D)localObject).getY() - i), (int)(((Rectangle2D)localObject).getWidth() + 2 * i), (int)(((Rectangle2D)localObject).getHeight() + 2 * i));
          BasicGraphUI.this.graph.repaint(localRectangle);
        }
      }
      else
      {
        localObject = new Rectangle(BasicGraphUI.this.graph.getSize());
        BasicGraphUI.this.graph.addOffscreenDirty((Rectangle2D)localObject);
        BasicGraphUI.this.graph.repaint();
      }
    }
  }
  
  public class GraphLayoutCacheHandler
    implements GraphLayoutCacheListener, Serializable
  {
    public GraphLayoutCacheHandler() {}
    
    public void graphLayoutCacheChanged(GraphLayoutCacheEvent paramGraphLayoutCacheEvent)
    {
      Object[] arrayOfObject1 = paramGraphLayoutCacheEvent.getChange().getChanged();
      if ((arrayOfObject1 != null) && (arrayOfObject1.length > 0)) {
        for (int i = 0; i < arrayOfObject1.length; i++) {
          BasicGraphUI.this.graph.updateAutoSize(BasicGraphUI.this.graphLayoutCache.getMapping(arrayOfObject1[i], false));
        }
      }
      Rectangle2D localRectangle2D1 = paramGraphLayoutCacheEvent.getChange().getDirtyRegion();
      BasicGraphUI.this.graph.addOffscreenDirty(localRectangle2D1);
      Rectangle2D localRectangle2D2 = BasicGraphUI.this.graph.getClipRectangle(paramGraphLayoutCacheEvent.getChange());
      BasicGraphUI.this.graph.addOffscreenDirty(localRectangle2D2);
      Object[] arrayOfObject2 = paramGraphLayoutCacheEvent.getChange().getInserted();
      if ((arrayOfObject2 != null) && (arrayOfObject2.length > 0) && (BasicGraphUI.this.graphLayoutCache.isSelectsLocalInsertedCells()) && ((!BasicGraphUI.this.graphLayoutCache.isSelectsAllInsertedCells()) || (BasicGraphUI.this.graphLayoutCache.isPartial())) && (BasicGraphUI.this.graph.isEnabled()))
      {
        Object[] arrayOfObject3 = DefaultGraphModel.getRoots(BasicGraphUI.this.graphModel, arrayOfObject2);
        if ((arrayOfObject3 != null) && (arrayOfObject3.length > 0))
        {
          BasicGraphUI.this.lastFocus = BasicGraphUI.this.focus;
          BasicGraphUI.this.focus = BasicGraphUI.this.graphLayoutCache.getMapping(arrayOfObject3[0], false);
          BasicGraphUI.this.graph.setSelectionCells(arrayOfObject3);
        }
      }
      BasicGraphUI.this.updateSize();
    }
  }
  
  public class GraphModelHandler
    implements GraphModelListener, Serializable
  {
    public GraphModelHandler() {}
    
    public void graphChanged(GraphModelEvent paramGraphModelEvent)
    {
      Object[] arrayOfObject1 = paramGraphModelEvent.getChange().getRemoved();
      if ((arrayOfObject1 != null) && (arrayOfObject1.length > 0))
      {
        if (BasicGraphUI.this.focus != null)
        {
          localObject1 = BasicGraphUI.this.focus.getCell();
          for (int i = 0; i < arrayOfObject1.length; i++) {
            if (arrayOfObject1[i] == localObject1)
            {
              BasicGraphUI.this.lastFocus = BasicGraphUI.this.focus;
              BasicGraphUI.this.focus = null;
              break;
            }
          }
        }
        BasicGraphUI.this.graph.getSelectionModel().removeSelectionCells(arrayOfObject1);
      }
      Object localObject1 = null;
      Rectangle2D localRectangle2D = paramGraphModelEvent.getChange().getDirtyRegion();
      if (localRectangle2D == null) {
        localObject1 = BasicGraphUI.this.graph.getClipRectangle(paramGraphModelEvent.getChange());
      }
      if (BasicGraphUI.this.graphLayoutCache != null) {
        BasicGraphUI.this.graphLayoutCache.graphChanged(paramGraphModelEvent.getChange());
      }
      Object[] arrayOfObject2 = paramGraphModelEvent.getChange().getInserted();
      Object[] arrayOfObject3 = paramGraphModelEvent.getChange().getChanged();
      int j;
      if ((arrayOfObject2 != null) && (arrayOfObject2.length > 0)) {
        for (j = 0; j < arrayOfObject2.length; j++) {
          BasicGraphUI.this.graph.updateAutoSize(BasicGraphUI.this.graphLayoutCache.getMapping(arrayOfObject2[j], false));
        }
      }
      if ((arrayOfObject3 != null) && (arrayOfObject3.length > 0)) {
        for (j = 0; j < arrayOfObject3.length; j++) {
          BasicGraphUI.this.graph.updateAutoSize(BasicGraphUI.this.graphLayoutCache.getMapping(arrayOfObject3[j], false));
        }
      }
      Object localObject2;
      if (localRectangle2D == null)
      {
        localObject2 = BasicGraphUI.this.graph.getClipRectangle(paramGraphModelEvent.getChange());
        localRectangle2D = RectUtils.union((Rectangle2D)localObject1, (Rectangle2D)localObject2);
        paramGraphModelEvent.getChange().setDirtyRegion(localRectangle2D);
      }
      if (localRectangle2D != null) {
        BasicGraphUI.this.graph.addOffscreenDirty(localRectangle2D);
      }
      if ((!BasicGraphUI.this.graphLayoutCache.isPartial()) && (BasicGraphUI.this.graphLayoutCache.isSelectsAllInsertedCells()) && (BasicGraphUI.this.graph.isEnabled()))
      {
        localObject2 = DefaultGraphModel.getRoots(BasicGraphUI.this.graphModel, arrayOfObject2);
        if ((localObject2 != null) && (localObject2.length > 0))
        {
          BasicGraphUI.this.lastFocus = BasicGraphUI.this.focus;
          BasicGraphUI.this.focus = BasicGraphUI.this.graphLayoutCache.getMapping(localObject2[0], false);
          BasicGraphUI.this.graph.setSelectionCells((Object[])localObject2);
        }
      }
      BasicGraphUI.this.updateSize();
    }
  }
  
  public class ComponentHandler
    extends ComponentAdapter
    implements ActionListener
  {
    protected Timer timer;
    protected JScrollBar scrollBar;
    
    public ComponentHandler() {}
    
    public void componentMoved(ComponentEvent paramComponentEvent)
    {
      if (this.timer == null)
      {
        JScrollPane localJScrollPane = getScrollPane();
        if (localJScrollPane == null)
        {
          BasicGraphUI.this.updateSize();
        }
        else
        {
          this.scrollBar = localJScrollPane.getVerticalScrollBar();
          if ((this.scrollBar == null) || (!this.scrollBar.getValueIsAdjusting()))
          {
            if (((this.scrollBar = localJScrollPane.getHorizontalScrollBar()) != null) && (this.scrollBar.getValueIsAdjusting())) {
              startTimer();
            } else {
              BasicGraphUI.this.updateSize();
            }
          }
          else {
            startTimer();
          }
        }
      }
    }
    
    protected void startTimer()
    {
      if (this.timer == null)
      {
        this.timer = new Timer(200, this);
        this.timer.setRepeats(true);
      }
      this.timer.start();
    }
    
    protected JScrollPane getScrollPane()
    {
      for (Container localContainer = BasicGraphUI.this.graph.getParent(); (localContainer != null) && (!(localContainer instanceof JScrollPane)); localContainer = localContainer.getParent()) {}
      if ((localContainer instanceof JScrollPane)) {
        return (JScrollPane)localContainer;
      }
      return null;
    }
    
    public void actionPerformed(ActionEvent paramActionEvent)
    {
      if ((this.scrollBar == null) || (!this.scrollBar.getValueIsAdjusting()))
      {
        if (this.timer != null) {
          this.timer.stop();
        }
        BasicGraphUI.this.updateSize();
        this.timer = null;
        this.scrollBar = null;
      }
    }
  }
}


/* Location:              /home/menick/Scrivania/ProcessMining/CNMining/CNMining.jar!/org/jgraph/plaf/basic/BasicGraphUI.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */