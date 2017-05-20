/*     */ package edu.uci.ics.jung.visualization.control;
/*     */ 
/*     */ import edu.uci.ics.jung.algorithms.layout.GraphElementAccessor;
/*     */ import edu.uci.ics.jung.algorithms.layout.Layout;
/*     */ import edu.uci.ics.jung.graph.DirectedGraph;
/*     */ import edu.uci.ics.jung.graph.Graph;
/*     */ import edu.uci.ics.jung.graph.UndirectedGraph;
/*     */ import edu.uci.ics.jung.graph.util.EdgeType;
/*     */ import edu.uci.ics.jung.visualization.MultiLayerTransformer;
/*     */ import edu.uci.ics.jung.visualization.RenderContext;
/*     */ import edu.uci.ics.jung.visualization.VisualizationModel;
/*     */ import edu.uci.ics.jung.visualization.VisualizationServer.Paintable;
/*     */ import edu.uci.ics.jung.visualization.VisualizationViewer;
/*     */ import edu.uci.ics.jung.visualization.util.ArrowFactory;
/*     */ import java.awt.Color;
/*     */ import java.awt.Cursor;
/*     */ import java.awt.Graphics;
/*     */ import java.awt.Graphics2D;
/*     */ import java.awt.Rectangle;
/*     */ import java.awt.Shape;
/*     */ import java.awt.event.MouseEvent;
/*     */ import java.awt.event.MouseListener;
/*     */ import java.awt.event.MouseMotionListener;
/*     */ import java.awt.geom.AffineTransform;
/*     */ import java.awt.geom.CubicCurve2D;
/*     */ import java.awt.geom.CubicCurve2D.Float;
/*     */ import java.awt.geom.Point2D;
/*     */ import javax.swing.JComponent;
/*     */ import org.apache.commons.collections15.Factory;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class EditingGraphMousePlugin<V, E>
/*     */   extends AbstractGraphMousePlugin
/*     */   implements MouseListener, MouseMotionListener
/*     */ {
/*     */   protected V startVertex;
/*     */   protected Point2D down;
/*  42 */   protected CubicCurve2D rawEdge = new CubicCurve2D.Float();
/*     */   protected Shape edgeShape;
/*     */   protected Shape rawArrowShape;
/*     */   protected Shape arrowShape;
/*     */   protected VisualizationServer.Paintable edgePaintable;
/*     */   protected VisualizationServer.Paintable arrowPaintable;
/*     */   protected EdgeType edgeIsDirected;
/*     */   protected Factory<V> vertexFactory;
/*     */   protected Factory<E> edgeFactory;
/*     */   
/*     */   public EditingGraphMousePlugin(Factory<V> vertexFactory, Factory<E> edgeFactory) {
/*  53 */     this(16, vertexFactory, edgeFactory);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   public EditingGraphMousePlugin(int modifiers, Factory<V> vertexFactory, Factory<E> edgeFactory)
/*     */   {
/*  61 */     super(modifiers);
/*  62 */     this.vertexFactory = vertexFactory;
/*  63 */     this.edgeFactory = edgeFactory;
/*  64 */     this.rawEdge.setCurve(0.0D, 0.0D, 0.33000001311302185D, 100.0D, 0.6600000262260437D, -50.0D, 1.0D, 0.0D);
/*     */     
/*  66 */     this.rawArrowShape = ArrowFactory.getNotchedArrow(20.0F, 16.0F, 8.0F);
/*  67 */     this.edgePaintable = new EdgePaintable();
/*  68 */     this.arrowPaintable = new ArrowPaintable();
/*  69 */     this.cursor = Cursor.getPredefinedCursor(1);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public boolean checkModifiers(MouseEvent e)
/*     */   {
/*  79 */     return (e.getModifiers() & this.modifiers) != 0;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public void mousePressed(MouseEvent e)
/*     */   {
/*  89 */     if (checkModifiers(e)) {
/*  90 */       VisualizationViewer<V, E> vv = (VisualizationViewer)e.getSource();
/*     */       
/*  92 */       Point2D p = e.getPoint();
/*  93 */       GraphElementAccessor<V, E> pickSupport = vv.getPickSupport();
/*  94 */       if (pickSupport != null) {
/*  95 */         Graph<V, E> graph = vv.getModel().getGraphLayout().getGraph();
/*     */         
/*  97 */         if ((graph instanceof DirectedGraph)) {
/*  98 */           this.edgeIsDirected = EdgeType.DIRECTED;
/*     */         } else {
/* 100 */           this.edgeIsDirected = EdgeType.UNDIRECTED;
/*     */         }
/*     */         
/* 103 */         V vertex = pickSupport.getVertex(vv.getModel().getGraphLayout(), p.getX(), p.getY());
/* 104 */         if (vertex != null) {
/* 105 */           this.startVertex = vertex;
/* 106 */           this.down = e.getPoint();
/* 107 */           transformEdgeShape(this.down, this.down);
/* 108 */           vv.addPostRenderPaintable(this.edgePaintable);
/* 109 */           if (((e.getModifiers() & 0x1) != 0) && (!(vv.getModel().getGraphLayout().getGraph() instanceof UndirectedGraph)))
/*     */           {
/* 111 */             this.edgeIsDirected = EdgeType.DIRECTED;
/*     */           }
/* 113 */           if (this.edgeIsDirected == EdgeType.DIRECTED) {
/* 114 */             transformArrowShape(this.down, e.getPoint());
/* 115 */             vv.addPostRenderPaintable(this.arrowPaintable);
/*     */           }
/*     */         }
/*     */         else {
/* 119 */           V newVertex = this.vertexFactory.create();
/* 120 */           Layout<V, E> layout = vv.getModel().getGraphLayout();
/* 121 */           graph.addVertex(newVertex);
/* 122 */           layout.setLocation(newVertex, vv.getRenderContext().getMultiLayerTransformer().inverseTransform(e.getPoint()));
/*     */         }
/*     */       }
/* 125 */       vv.repaint();
/*     */     }
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public void mouseReleased(MouseEvent e)
/*     */   {
/* 137 */     if (checkModifiers(e)) {
/* 138 */       VisualizationViewer<V, E> vv = (VisualizationViewer)e.getSource();
/*     */       
/* 140 */       Point2D p = e.getPoint();
/* 141 */       Layout<V, E> layout = vv.getModel().getGraphLayout();
/* 142 */       GraphElementAccessor<V, E> pickSupport = vv.getPickSupport();
/* 143 */       if (pickSupport != null) {
/* 144 */         V vertex = pickSupport.getVertex(layout, p.getX(), p.getY());
/* 145 */         if ((vertex != null) && (this.startVertex != null)) {
/* 146 */           Graph<V, E> graph = vv.getGraphLayout().getGraph();
/*     */           
/* 148 */           graph.addEdge(this.edgeFactory.create(), this.startVertex, vertex, this.edgeIsDirected);
/*     */           
/* 150 */           vv.repaint();
/*     */         }
/*     */       }
/* 153 */       this.startVertex = null;
/* 154 */       this.down = null;
/* 155 */       this.edgeIsDirected = EdgeType.UNDIRECTED;
/* 156 */       vv.removePostRenderPaintable(this.edgePaintable);
/* 157 */       vv.removePostRenderPaintable(this.arrowPaintable);
/*     */     }
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public void mouseDragged(MouseEvent e)
/*     */   {
/* 167 */     if (checkModifiers(e)) {
/* 168 */       if (this.startVertex != null) {
/* 169 */         transformEdgeShape(this.down, e.getPoint());
/* 170 */         if (this.edgeIsDirected == EdgeType.DIRECTED) {
/* 171 */           transformArrowShape(this.down, e.getPoint());
/*     */         }
/*     */       }
/* 174 */       VisualizationViewer<V, E> vv = (VisualizationViewer)e.getSource();
/*     */       
/* 176 */       vv.repaint();
/*     */     }
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   private void transformEdgeShape(Point2D down, Point2D out)
/*     */   {
/* 185 */     float x1 = (float)down.getX();
/* 186 */     float y1 = (float)down.getY();
/* 187 */     float x2 = (float)out.getX();
/* 188 */     float y2 = (float)out.getY();
/*     */     
/* 190 */     AffineTransform xform = AffineTransform.getTranslateInstance(x1, y1);
/*     */     
/* 192 */     float dx = x2 - x1;
/* 193 */     float dy = y2 - y1;
/* 194 */     float thetaRadians = (float)Math.atan2(dy, dx);
/* 195 */     xform.rotate(thetaRadians);
/* 196 */     float dist = (float)Math.sqrt(dx * dx + dy * dy);
/* 197 */     xform.scale(dist / this.rawEdge.getBounds().getWidth(), 1.0D);
/* 198 */     this.edgeShape = xform.createTransformedShape(this.rawEdge);
/*     */   }
/*     */   
/*     */   private void transformArrowShape(Point2D down, Point2D out) {
/* 202 */     float x1 = (float)down.getX();
/* 203 */     float y1 = (float)down.getY();
/* 204 */     float x2 = (float)out.getX();
/* 205 */     float y2 = (float)out.getY();
/*     */     
/* 207 */     AffineTransform xform = AffineTransform.getTranslateInstance(x2, y2);
/*     */     
/* 209 */     float dx = x2 - x1;
/* 210 */     float dy = y2 - y1;
/* 211 */     float thetaRadians = (float)Math.atan2(dy, dx);
/* 212 */     xform.rotate(thetaRadians);
/* 213 */     this.arrowShape = xform.createTransformedShape(this.rawArrowShape);
/*     */   }
/*     */   
/*     */   public void mouseClicked(MouseEvent e) {}
/*     */   
/*     */   class EdgePaintable implements VisualizationServer.Paintable {
/*     */     EdgePaintable() {}
/*     */     
/*     */     public void paint(Graphics g) {
/* 222 */       if (EditingGraphMousePlugin.this.edgeShape != null) {
/* 223 */         Color oldColor = g.getColor();
/* 224 */         g.setColor(Color.black);
/* 225 */         ((Graphics2D)g).draw(EditingGraphMousePlugin.this.edgeShape);
/* 226 */         g.setColor(oldColor);
/*     */       }
/*     */     }
/*     */     
/*     */     public boolean useTransform() {
/* 231 */       return false;
/*     */     }
/*     */   }
/*     */   
/*     */   class ArrowPaintable implements VisualizationServer.Paintable
/*     */   {
/*     */     ArrowPaintable() {}
/*     */     
/*     */     public void paint(Graphics g)
/*     */     {
/* 241 */       if (EditingGraphMousePlugin.this.arrowShape != null) {
/* 242 */         Color oldColor = g.getColor();
/* 243 */         g.setColor(Color.black);
/* 244 */         ((Graphics2D)g).fill(EditingGraphMousePlugin.this.arrowShape);
/* 245 */         g.setColor(oldColor);
/*     */       }
/*     */     }
/*     */     
/*     */     public boolean useTransform() {
/* 250 */       return false;
/*     */     }
/*     */   }
/*     */   
/*     */   public void mouseEntered(MouseEvent e) {
/* 255 */     JComponent c = (JComponent)e.getSource();
/* 256 */     c.setCursor(this.cursor);
/*     */   }
/*     */   
/* 259 */   public void mouseExited(MouseEvent e) { JComponent c = (JComponent)e.getSource();
/* 260 */     c.setCursor(Cursor.getPredefinedCursor(0));
/*     */   }
/*     */   
/*     */   public void mouseMoved(MouseEvent e) {}
/*     */ }


/* Location:              /home/menick/Scrivania/ProcessMining/CNMining/CNMining.jar!/edu/uci/ics/jung/visualization/control/EditingGraphMousePlugin.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       0.7.1
 */