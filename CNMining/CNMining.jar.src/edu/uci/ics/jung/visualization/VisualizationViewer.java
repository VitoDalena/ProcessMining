/*     */ package edu.uci.ics.jung.visualization;
/*     */ 
/*     */ import edu.uci.ics.jung.algorithms.layout.GraphElementAccessor;
/*     */ import edu.uci.ics.jung.algorithms.layout.Layout;
/*     */ import edu.uci.ics.jung.visualization.control.GraphMouseListener;
/*     */ import edu.uci.ics.jung.visualization.control.MouseListenerTranslator;
/*     */ import java.awt.Dimension;
/*     */ import java.awt.event.KeyListener;
/*     */ import java.awt.event.MouseAdapter;
/*     */ import java.awt.event.MouseEvent;
/*     */ import java.awt.event.MouseListener;
/*     */ import java.awt.event.MouseMotionListener;
/*     */ import java.awt.event.MouseWheelListener;
/*     */ import java.awt.geom.Point2D;
/*     */ import javax.swing.ToolTipManager;
/*     */ import org.apache.commons.collections15.Transformer;
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
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class VisualizationViewer<V, E>
/*     */   extends BasicVisualizationServer<V, E>
/*     */ {
/*     */   protected Transformer<V, String> vertexToolTipTransformer;
/*     */   protected Transformer<E, String> edgeToolTipTransformer;
/*     */   protected Transformer<MouseEvent, String> mouseEventToolTipTransformer;
/*     */   protected GraphMouse graphMouse;
/*  49 */   protected MouseListener requestFocusListener = new MouseAdapter() {
/*     */     public void mouseClicked(MouseEvent e) {
/*  51 */       VisualizationViewer.this.requestFocusInWindow();
/*     */     }
/*     */   };
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public VisualizationViewer(Layout<V, E> layout)
/*     */   {
/*  63 */     this(new DefaultVisualizationModel(layout));
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public VisualizationViewer(Layout<V, E> layout, Dimension preferredSize)
/*     */   {
/*  74 */     this(new DefaultVisualizationModel(layout, preferredSize), preferredSize);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public VisualizationViewer(VisualizationModel<V, E> model)
/*     */   {
/*  84 */     this(model, new Dimension(600, 600));
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public VisualizationViewer(VisualizationModel<V, E> model, Dimension preferredSize)
/*     */   {
/*  96 */     super(model, preferredSize);
/*  97 */     setFocusable(true);
/*  98 */     addMouseListener(this.requestFocusListener);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public void setGraphMouse(GraphMouse graphMouse)
/*     */   {
/* 108 */     this.graphMouse = graphMouse;
/* 109 */     MouseListener[] ml = getMouseListeners();
/* 110 */     for (int i = 0; i < ml.length; i++) {
/* 111 */       if ((ml[i] instanceof GraphMouse)) {
/* 112 */         removeMouseListener(ml[i]);
/*     */       }
/*     */     }
/* 115 */     MouseMotionListener[] mml = getMouseMotionListeners();
/* 116 */     for (int i = 0; i < mml.length; i++) {
/* 117 */       if ((mml[i] instanceof GraphMouse)) {
/* 118 */         removeMouseMotionListener(mml[i]);
/*     */       }
/*     */     }
/* 121 */     MouseWheelListener[] mwl = getMouseWheelListeners();
/* 122 */     for (int i = 0; i < mwl.length; i++) {
/* 123 */       if ((mwl[i] instanceof GraphMouse)) {
/* 124 */         removeMouseWheelListener(mwl[i]);
/*     */       }
/*     */     }
/* 127 */     addMouseListener(graphMouse);
/* 128 */     addMouseMotionListener(graphMouse);
/* 129 */     addMouseWheelListener(graphMouse);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */   public GraphMouse getGraphMouse()
/*     */   {
/* 136 */     return this.graphMouse;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public void addGraphMouseListener(GraphMouseListener<V> gel)
/*     */   {
/* 145 */     addMouseListener(new MouseListenerTranslator(gel, this));
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public synchronized void addKeyListener(KeyListener l)
/*     */   {
/* 154 */     super.addKeyListener(l);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public void setEdgeToolTipTransformer(Transformer<E, String> edgeToolTipTransformer)
/*     */   {
/* 164 */     this.edgeToolTipTransformer = edgeToolTipTransformer;
/* 165 */     ToolTipManager.sharedInstance().registerComponent(this);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   public void setMouseEventToolTipTransformer(Transformer<MouseEvent, String> mouseEventToolTipTransformer)
/*     */   {
/* 173 */     this.mouseEventToolTipTransformer = mouseEventToolTipTransformer;
/* 174 */     ToolTipManager.sharedInstance().registerComponent(this);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   public void setVertexToolTipTransformer(Transformer<V, String> vertexToolTipTransformer)
/*     */   {
/* 182 */     this.vertexToolTipTransformer = vertexToolTipTransformer;
/* 183 */     ToolTipManager.sharedInstance().registerComponent(this);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */   public String getToolTipText(MouseEvent event)
/*     */   {
/* 190 */     Layout<V, E> layout = getGraphLayout();
/* 191 */     Point2D p = null;
/* 192 */     if (this.vertexToolTipTransformer != null) {
/* 193 */       p = event.getPoint();
/*     */       
/* 195 */       V vertex = getPickSupport().getVertex(layout, p.getX(), p.getY());
/* 196 */       if (vertex != null) {
/* 197 */         return (String)this.vertexToolTipTransformer.transform(vertex);
/*     */       }
/*     */     }
/* 200 */     if (this.edgeToolTipTransformer != null) {
/* 201 */       if (p == null) p = this.renderContext.getMultiLayerTransformer().inverseTransform(Layer.VIEW, event.getPoint());
/* 202 */       E edge = getPickSupport().getEdge(layout, p.getX(), p.getY());
/* 203 */       if (edge != null) {
/* 204 */         return (String)this.edgeToolTipTransformer.transform(edge);
/*     */       }
/*     */     }
/* 207 */     if (this.mouseEventToolTipTransformer != null) {
/* 208 */       return (String)this.mouseEventToolTipTransformer.transform(event);
/*     */     }
/* 210 */     return super.getToolTipText(event);
/*     */   }
/*     */   
/*     */   public static abstract interface GraphMouse
/*     */     extends MouseListener, MouseMotionListener, MouseWheelListener
/*     */   {}
/*     */ }


/* Location:              /home/menick/Scrivania/ProcessMining/CNMining/CNMining.jar!/edu/uci/ics/jung/visualization/VisualizationViewer.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       0.7.1
 */