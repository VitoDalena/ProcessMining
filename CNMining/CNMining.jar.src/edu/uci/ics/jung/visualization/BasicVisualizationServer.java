/*     */ package edu.uci.ics.jung.visualization;
/*     */ 
/*     */ import edu.uci.ics.jung.algorithms.layout.GraphElementAccessor;
/*     */ import edu.uci.ics.jung.algorithms.layout.Layout;
/*     */ import edu.uci.ics.jung.visualization.control.ScalingControl;
/*     */ import edu.uci.ics.jung.visualization.decorators.PickableEdgePaintTransformer;
/*     */ import edu.uci.ics.jung.visualization.decorators.PickableVertexPaintTransformer;
/*     */ import edu.uci.ics.jung.visualization.picking.MultiPickedState;
/*     */ import edu.uci.ics.jung.visualization.picking.PickedState;
/*     */ import edu.uci.ics.jung.visualization.picking.ShapePickSupport;
/*     */ import edu.uci.ics.jung.visualization.renderers.BasicRenderer;
/*     */ import edu.uci.ics.jung.visualization.renderers.Renderer;
/*     */ import edu.uci.ics.jung.visualization.transform.MutableTransformer;
/*     */ import edu.uci.ics.jung.visualization.transform.shape.GraphicsDecorator;
/*     */ import edu.uci.ics.jung.visualization.util.Caching;
/*     */ import edu.uci.ics.jung.visualization.util.ChangeEventSupport;
/*     */ import edu.uci.ics.jung.visualization.util.DefaultChangeEventSupport;
/*     */ import java.awt.Color;
/*     */ import java.awt.Dimension;
/*     */ import java.awt.Graphics;
/*     */ import java.awt.Graphics2D;
/*     */ import java.awt.RenderingHints;
/*     */ import java.awt.RenderingHints.Key;
/*     */ import java.awt.event.ComponentAdapter;
/*     */ import java.awt.event.ComponentEvent;
/*     */ import java.awt.event.ItemEvent;
/*     */ import java.awt.event.ItemListener;
/*     */ import java.awt.geom.AffineTransform;
/*     */ import java.awt.geom.Point2D;
/*     */ import java.awt.geom.Point2D.Double;
/*     */ import java.awt.geom.Point2D.Float;
/*     */ import java.awt.image.BufferedImage;
/*     */ import java.util.ArrayList;
/*     */ import java.util.HashMap;
/*     */ import java.util.List;
/*     */ import java.util.Map;
/*     */ import javax.swing.JPanel;
/*     */ import javax.swing.event.ChangeEvent;
/*     */ import javax.swing.event.ChangeListener;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class BasicVisualizationServer<V, E>
/*     */   extends JPanel
/*     */   implements ChangeListener, ChangeEventSupport, VisualizationServer<V, E>
/*     */ {
/*  63 */   protected ChangeEventSupport changeSupport = new DefaultChangeEventSupport(this);
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   protected VisualizationModel<V, E> model;
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*  74 */   protected Renderer<V, E> renderer = new BasicRenderer();
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*  80 */   protected Map<RenderingHints.Key, Object> renderingHints = new HashMap();
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   protected PickedState<V> pickedVertexState;
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   protected PickedState<E> pickedEdgeState;
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   protected ItemListener pickEventListener;
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   protected BufferedImage offscreen;
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   protected Graphics2D offscreenG2d;
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   protected boolean doubleBuffered;
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/* 122 */   protected List<VisualizationServer.Paintable> preRenderers = new ArrayList();
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/* 128 */   protected List<VisualizationServer.Paintable> postRenderers = new ArrayList();
/*     */   
/* 130 */   protected RenderContext<V, E> renderContext = new PluggableRenderContext();
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public BasicVisualizationServer(Layout<V, E> layout)
/*     */   {
/* 139 */     this(new DefaultVisualizationModel(layout));
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public BasicVisualizationServer(Layout<V, E> layout, Dimension preferredSize)
/*     */   {
/* 150 */     this(new DefaultVisualizationModel(layout, preferredSize), preferredSize);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public BasicVisualizationServer(VisualizationModel<V, E> model)
/*     */   {
/* 160 */     this(model, new Dimension(600, 600));
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public BasicVisualizationServer(VisualizationModel<V, E> model, Dimension preferredSize)
/*     */   {
/* 172 */     this.model = model;
/*     */     
/* 174 */     model.addChangeListener(this);
/* 175 */     setDoubleBuffered(false);
/* 176 */     addComponentListener(new VisualizationListener(this));
/*     */     
/* 178 */     setPickSupport(new ShapePickSupport(this));
/* 179 */     setPickedVertexState(new MultiPickedState());
/* 180 */     setPickedEdgeState(new MultiPickedState());
/*     */     
/* 182 */     this.renderContext.setEdgeDrawPaintTransformer(new PickableEdgePaintTransformer(getPickedEdgeState(), Color.black, Color.cyan));
/* 183 */     this.renderContext.setVertexFillPaintTransformer(new PickableVertexPaintTransformer(getPickedVertexState(), Color.red, Color.yellow));
/*     */     
/*     */ 
/* 186 */     setPreferredSize(preferredSize);
/* 187 */     this.renderingHints.put(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
/*     */     
/* 189 */     this.renderContext.getMultiLayerTransformer().addChangeListener(this);
/*     */   }
/*     */   
/*     */   public void setDoubleBuffered(boolean doubleBuffered)
/*     */   {
/* 194 */     this.doubleBuffered = doubleBuffered;
/*     */   }
/*     */   
/*     */   public boolean isDoubleBuffered()
/*     */   {
/* 199 */     return this.doubleBuffered;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public Dimension getSize()
/*     */   {
/* 209 */     Dimension d = super.getSize();
/* 210 */     if ((d.width <= 0) || (d.height <= 0)) {
/* 211 */       d = getPreferredSize();
/*     */     }
/* 213 */     return d;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   protected void checkOffscreenImage(Dimension d)
/*     */   {
/* 222 */     if ((this.doubleBuffered) && (
/* 223 */       (this.offscreen == null) || (this.offscreen.getWidth() != d.width) || (this.offscreen.getHeight() != d.height))) {
/* 224 */       this.offscreen = new BufferedImage(d.width, d.height, 2);
/* 225 */       this.offscreenG2d = this.offscreen.createGraphics();
/*     */     }
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   public VisualizationModel<V, E> getModel()
/*     */   {
/* 234 */     return this.model;
/*     */   }
/*     */   
/*     */ 
/*     */   public void setModel(VisualizationModel<V, E> model)
/*     */   {
/* 240 */     this.model = model;
/*     */   }
/*     */   
/*     */ 
/*     */   public void stateChanged(ChangeEvent e)
/*     */   {
/* 246 */     repaint();
/* 247 */     fireStateChanged();
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */   public void setRenderer(Renderer<V, E> r)
/*     */   {
/* 254 */     this.renderer = r;
/* 255 */     repaint();
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */   public Renderer<V, E> getRenderer()
/*     */   {
/* 262 */     return this.renderer;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */   public void setGraphLayout(Layout<V, E> layout)
/*     */   {
/* 269 */     Dimension viewSize = getPreferredSize();
/* 270 */     if (isShowing()) {
/* 271 */       viewSize = getSize();
/*     */     }
/* 273 */     this.model.setGraphLayout(layout, viewSize);
/*     */   }
/*     */   
/*     */   public void scaleToLayout(ScalingControl scaler) {
/* 277 */     Dimension vd = getPreferredSize();
/* 278 */     if (isShowing()) {
/* 279 */       vd = getSize();
/*     */     }
/* 281 */     Dimension ld = getGraphLayout().getSize();
/* 282 */     if (!vd.equals(ld)) {
/* 283 */       scaler.scale(this, (float)(vd.getWidth() / ld.getWidth()), new Point2D.Double());
/*     */     }
/*     */   }
/*     */   
/*     */   public Layout<V, E> getGraphLayout() {
/* 288 */     return this.model.getGraphLayout();
/*     */   }
/*     */   
/*     */   public void setVisible(boolean aFlag)
/*     */   {
/* 293 */     super.setVisible(aFlag);
/* 294 */     if (aFlag == true) {
/* 295 */       Dimension d = getSize();
/* 296 */       if ((d.width <= 0) || (d.height <= 0)) {
/* 297 */         d = getPreferredSize();
/*     */       }
/* 299 */       this.model.getGraphLayout().setSize(d);
/*     */     }
/*     */   }
/*     */   
/*     */   public Map<RenderingHints.Key, Object> getRenderingHints() {
/* 304 */     return this.renderingHints;
/*     */   }
/*     */   
/*     */   public void setRenderingHints(Map<RenderingHints.Key, Object> renderingHints) {
/* 308 */     this.renderingHints = renderingHints;
/*     */   }
/*     */   
/*     */   protected void paintComponent(Graphics g)
/*     */   {
/* 313 */     super.paintComponent(g);
/*     */     
/* 315 */     Graphics2D g2d = (Graphics2D)g;
/* 316 */     if (this.doubleBuffered) {
/* 317 */       checkOffscreenImage(getSize());
/* 318 */       renderGraph(this.offscreenG2d);
/* 319 */       g2d.drawImage(this.offscreen, null, 0, 0);
/*     */     } else {
/* 321 */       renderGraph(g2d);
/*     */     }
/*     */   }
/*     */   
/*     */   protected void renderGraph(Graphics2D g2d) {
/* 326 */     if (this.renderContext.getGraphicsContext() == null) {
/* 327 */       this.renderContext.setGraphicsContext(new GraphicsDecorator(g2d));
/*     */     } else {
/* 329 */       this.renderContext.getGraphicsContext().setDelegate(g2d);
/*     */     }
/* 331 */     this.renderContext.setScreenDevice(this);
/* 332 */     Layout<V, E> layout = this.model.getGraphLayout();
/*     */     
/* 334 */     g2d.setRenderingHints(this.renderingHints);
/*     */     
/*     */ 
/* 337 */     Dimension d = getSize();
/*     */     
/*     */ 
/* 340 */     g2d.setColor(getBackground());
/* 341 */     g2d.fillRect(0, 0, d.width, d.height);
/*     */     
/* 343 */     AffineTransform oldXform = g2d.getTransform();
/* 344 */     AffineTransform newXform = new AffineTransform(oldXform);
/* 345 */     newXform.concatenate(this.renderContext.getMultiLayerTransformer().getTransformer(Layer.VIEW).getTransform());
/*     */     
/*     */ 
/*     */ 
/* 349 */     g2d.setTransform(newXform);
/*     */     
/*     */ 
/* 352 */     for (VisualizationServer.Paintable paintable : this.preRenderers)
/*     */     {
/* 354 */       if (paintable.useTransform()) {
/* 355 */         paintable.paint(g2d);
/*     */       } else {
/* 357 */         g2d.setTransform(oldXform);
/* 358 */         paintable.paint(g2d);
/* 359 */         g2d.setTransform(newXform);
/*     */       }
/*     */     }
/*     */     
/* 363 */     if ((layout instanceof Caching)) {
/* 364 */       ((Caching)layout).clear();
/*     */     }
/*     */     
/* 367 */     this.renderer.render(this.renderContext, layout);
/*     */     
/*     */ 
/* 370 */     for (VisualizationServer.Paintable paintable : this.postRenderers)
/*     */     {
/* 372 */       if (paintable.useTransform()) {
/* 373 */         paintable.paint(g2d);
/*     */       } else {
/* 375 */         g2d.setTransform(oldXform);
/* 376 */         paintable.paint(g2d);
/* 377 */         g2d.setTransform(newXform);
/*     */       }
/*     */     }
/* 380 */     g2d.setTransform(oldXform);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   protected class VisualizationListener
/*     */     extends ComponentAdapter
/*     */   {
/*     */     protected BasicVisualizationServer<V, E> vv;
/*     */     
/*     */ 
/*     */ 
/*     */     public VisualizationListener()
/*     */     {
/* 395 */       this.vv = vv;
/*     */     }
/*     */     
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     public void componentResized(ComponentEvent e)
/*     */     {
/* 404 */       Dimension d = this.vv.getSize();
/* 405 */       if ((d.width <= 0) || (d.height <= 0)) return;
/* 406 */       BasicVisualizationServer.this.checkOffscreenImage(d);
/* 407 */       BasicVisualizationServer.this.repaint();
/*     */     }
/*     */   }
/*     */   
/*     */   public void addPreRenderPaintable(VisualizationServer.Paintable paintable) {
/* 412 */     if (this.preRenderers == null) {
/* 413 */       this.preRenderers = new ArrayList();
/*     */     }
/* 415 */     this.preRenderers.add(paintable);
/*     */   }
/*     */   
/*     */   public void prependPreRenderPaintable(VisualizationServer.Paintable paintable) {
/* 419 */     if (this.preRenderers == null) {
/* 420 */       this.preRenderers = new ArrayList();
/*     */     }
/* 422 */     this.preRenderers.add(0, paintable);
/*     */   }
/*     */   
/*     */   public void removePreRenderPaintable(VisualizationServer.Paintable paintable) {
/* 426 */     if (this.preRenderers != null) {
/* 427 */       this.preRenderers.remove(paintable);
/*     */     }
/*     */   }
/*     */   
/*     */   public void addPostRenderPaintable(VisualizationServer.Paintable paintable) {
/* 432 */     if (this.postRenderers == null) {
/* 433 */       this.postRenderers = new ArrayList();
/*     */     }
/* 435 */     this.postRenderers.add(paintable);
/*     */   }
/*     */   
/*     */   public void prependPostRenderPaintable(VisualizationServer.Paintable paintable) {
/* 439 */     if (this.postRenderers == null) {
/* 440 */       this.postRenderers = new ArrayList();
/*     */     }
/* 442 */     this.postRenderers.add(0, paintable);
/*     */   }
/*     */   
/*     */   public void removePostRenderPaintable(VisualizationServer.Paintable paintable) {
/* 446 */     if (this.postRenderers != null) {
/* 447 */       this.postRenderers.remove(paintable);
/*     */     }
/*     */   }
/*     */   
/*     */   public void addChangeListener(ChangeListener l) {
/* 452 */     this.changeSupport.addChangeListener(l);
/*     */   }
/*     */   
/*     */   public void removeChangeListener(ChangeListener l) {
/* 456 */     this.changeSupport.removeChangeListener(l);
/*     */   }
/*     */   
/*     */   public ChangeListener[] getChangeListeners() {
/* 460 */     return this.changeSupport.getChangeListeners();
/*     */   }
/*     */   
/*     */   public void fireStateChanged() {
/* 464 */     this.changeSupport.fireStateChanged();
/*     */   }
/*     */   
/*     */   public PickedState<V> getPickedVertexState() {
/* 468 */     return this.pickedVertexState;
/*     */   }
/*     */   
/*     */   public PickedState<E> getPickedEdgeState() {
/* 472 */     return this.pickedEdgeState;
/*     */   }
/*     */   
/*     */   public void setPickedVertexState(PickedState<V> pickedVertexState) {
/* 476 */     if ((this.pickEventListener != null) && (this.pickedVertexState != null)) {
/* 477 */       this.pickedVertexState.removeItemListener(this.pickEventListener);
/*     */     }
/* 479 */     this.pickedVertexState = pickedVertexState;
/* 480 */     this.renderContext.setPickedVertexState(pickedVertexState);
/* 481 */     if (this.pickEventListener == null) {
/* 482 */       this.pickEventListener = new ItemListener()
/*     */       {
/*     */         public void itemStateChanged(ItemEvent e) {
/* 485 */           BasicVisualizationServer.this.repaint();
/*     */         }
/*     */       };
/*     */     }
/* 489 */     pickedVertexState.addItemListener(this.pickEventListener);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */   public void setPickedEdgeState(PickedState<E> pickedEdgeState)
/*     */   {
/* 496 */     if ((this.pickEventListener != null) && (this.pickedEdgeState != null)) {
/* 497 */       this.pickedEdgeState.removeItemListener(this.pickEventListener);
/*     */     }
/* 499 */     this.pickedEdgeState = pickedEdgeState;
/* 500 */     this.renderContext.setPickedEdgeState(pickedEdgeState);
/* 501 */     if (this.pickEventListener == null) {
/* 502 */       this.pickEventListener = new ItemListener()
/*     */       {
/*     */         public void itemStateChanged(ItemEvent e) {
/* 505 */           BasicVisualizationServer.this.repaint();
/*     */         }
/*     */       };
/*     */     }
/* 509 */     pickedEdgeState.addItemListener(this.pickEventListener);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */   public GraphElementAccessor<V, E> getPickSupport()
/*     */   {
/* 516 */     return this.renderContext.getPickSupport();
/*     */   }
/*     */   
/*     */ 
/*     */   public void setPickSupport(GraphElementAccessor<V, E> pickSupport)
/*     */   {
/* 522 */     this.renderContext.setPickSupport(pickSupport);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */   public Point2D getCenter()
/*     */   {
/* 529 */     Dimension d = getSize();
/* 530 */     return new Point2D.Float(d.width / 2, d.height / 2);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */   public RenderContext<V, E> getRenderContext()
/*     */   {
/* 537 */     return this.renderContext;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */   public void setRenderContext(RenderContext<V, E> renderContext)
/*     */   {
/* 544 */     this.renderContext = renderContext;
/*     */   }
/*     */ }


/* Location:              /home/menick/Scrivania/ProcessMining/CNMining/CNMining.jar!/edu/uci/ics/jung/visualization/BasicVisualizationServer.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       0.7.1
 */