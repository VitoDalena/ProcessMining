/*     */ package edu.uci.ics.jung.visualization.control;
/*     */ 
/*     */ import edu.uci.ics.jung.algorithms.layout.GraphElementAccessor;
/*     */ import edu.uci.ics.jung.algorithms.layout.Layout;
/*     */ import edu.uci.ics.jung.visualization.Layer;
/*     */ import edu.uci.ics.jung.visualization.MultiLayerTransformer;
/*     */ import edu.uci.ics.jung.visualization.RenderContext;
/*     */ import edu.uci.ics.jung.visualization.VisualizationServer.Paintable;
/*     */ import edu.uci.ics.jung.visualization.VisualizationViewer;
/*     */ import edu.uci.ics.jung.visualization.picking.PickedState;
/*     */ import java.awt.Color;
/*     */ import java.awt.Cursor;
/*     */ import java.awt.Graphics;
/*     */ import java.awt.Graphics2D;
/*     */ import java.awt.Point;
/*     */ import java.awt.event.MouseEvent;
/*     */ import java.awt.event.MouseListener;
/*     */ import java.awt.event.MouseMotionListener;
/*     */ import java.awt.geom.Point2D;
/*     */ import java.awt.geom.Rectangle2D;
/*     */ import java.awt.geom.Rectangle2D.Double;
/*     */ import java.awt.geom.Rectangle2D.Float;
/*     */ import java.util.Collection;
/*     */ import javax.swing.JComponent;
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
/*     */ public class PickingGraphMousePlugin<V, E>
/*     */   extends AbstractGraphMousePlugin
/*     */   implements MouseListener, MouseMotionListener
/*     */ {
/*     */   protected V vertex;
/*     */   protected E edge;
/*     */   protected double offsetx;
/*     */   protected double offsety;
/*     */   protected boolean locked;
/*     */   protected int addToSelectionModifiers;
/*  83 */   protected Rectangle2D rect = new Rectangle2D.Float();
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   protected VisualizationServer.Paintable lensPaintable;
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*  93 */   protected Color lensColor = Color.cyan;
/*     */   
/*     */ 
/*     */ 
/*     */   public PickingGraphMousePlugin()
/*     */   {
/*  99 */     this(16, 17);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public PickingGraphMousePlugin(int selectionModifiers, int addToSelectionModifiers)
/*     */   {
/* 108 */     super(selectionModifiers);
/* 109 */     this.addToSelectionModifiers = addToSelectionModifiers;
/* 110 */     this.lensPaintable = new LensPaintable();
/* 111 */     this.cursor = Cursor.getPredefinedCursor(12);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */   public Color getLensColor()
/*     */   {
/* 118 */     return this.lensColor;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */   public void setLensColor(Color lensColor)
/*     */   {
/* 125 */     this.lensColor = lensColor;
/*     */   }
/*     */   
/*     */ 
/*     */   class LensPaintable
/*     */     implements VisualizationServer.Paintable
/*     */   {
/*     */     LensPaintable() {}
/*     */     
/*     */ 
/*     */     public void paint(Graphics g)
/*     */     {
/* 137 */       Color oldColor = g.getColor();
/* 138 */       g.setColor(PickingGraphMousePlugin.this.lensColor);
/* 139 */       ((Graphics2D)g).draw(PickingGraphMousePlugin.this.rect);
/* 140 */       g.setColor(oldColor);
/*     */     }
/*     */     
/*     */     public boolean useTransform() {
/* 144 */       return false;
/*     */     }
/*     */   }
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
/*     */   public void mousePressed(MouseEvent e)
/*     */   {
/* 167 */     this.down = e.getPoint();
/* 168 */     VisualizationViewer<V, E> vv = (VisualizationViewer)e.getSource();
/* 169 */     GraphElementAccessor<V, E> pickSupport = vv.getPickSupport();
/* 170 */     PickedState<V> pickedVertexState = vv.getPickedVertexState();
/* 171 */     PickedState<E> pickedEdgeState = vv.getPickedEdgeState();
/* 172 */     if ((pickSupport != null) && (pickedVertexState != null)) {
/* 173 */       Layout<V, E> layout = vv.getGraphLayout();
/* 174 */       if (e.getModifiers() == this.modifiers) {
/* 175 */         this.rect.setFrameFromDiagonal(this.down, this.down);
/*     */         
/* 177 */         Point2D ip = e.getPoint();
/*     */         
/* 179 */         this.vertex = pickSupport.getVertex(layout, ip.getX(), ip.getY());
/* 180 */         if (this.vertex != null) {
/* 181 */           if (!pickedVertexState.isPicked(this.vertex)) {
/* 182 */             pickedVertexState.clear();
/* 183 */             pickedVertexState.pick(this.vertex, true);
/*     */           }
/*     */           
/*     */ 
/* 187 */           Point2D q = (Point2D)layout.transform(this.vertex);
/*     */           
/* 189 */           Point2D gp = vv.getRenderContext().getMultiLayerTransformer().inverseTransform(Layer.LAYOUT, ip);
/*     */           
/* 191 */           this.offsetx = ((float)(gp.getX() - q.getX()));
/* 192 */           this.offsety = ((float)(gp.getY() - q.getY()));
/* 193 */         } else if ((this.edge = pickSupport.getEdge(layout, ip.getX(), ip.getY())) != null) {
/* 194 */           pickedEdgeState.clear();
/* 195 */           pickedEdgeState.pick(this.edge, true);
/*     */         } else {
/* 197 */           vv.addPostRenderPaintable(this.lensPaintable);
/* 198 */           pickedEdgeState.clear();
/* 199 */           pickedVertexState.clear();
/*     */         }
/*     */       }
/* 202 */       else if (e.getModifiers() == this.addToSelectionModifiers) {
/* 203 */         vv.addPostRenderPaintable(this.lensPaintable);
/* 204 */         this.rect.setFrameFromDiagonal(this.down, this.down);
/* 205 */         Point2D ip = e.getPoint();
/* 206 */         this.vertex = pickSupport.getVertex(layout, ip.getX(), ip.getY());
/* 207 */         if (this.vertex != null) {
/* 208 */           boolean wasThere = pickedVertexState.pick(this.vertex, !pickedVertexState.isPicked(this.vertex));
/* 209 */           if (wasThere) {
/* 210 */             this.vertex = null;
/*     */ 
/*     */           }
/*     */           else
/*     */           {
/* 215 */             Point2D q = (Point2D)layout.transform(this.vertex);
/*     */             
/* 217 */             Point2D gp = vv.getRenderContext().getMultiLayerTransformer().inverseTransform(Layer.LAYOUT, ip);
/*     */             
/* 219 */             this.offsetx = ((float)(gp.getX() - q.getX()));
/* 220 */             this.offsety = ((float)(gp.getY() - q.getY()));
/*     */           }
/* 222 */         } else if ((this.edge = pickSupport.getEdge(layout, ip.getX(), ip.getY())) != null) {
/* 223 */           pickedEdgeState.pick(this.edge, !pickedEdgeState.isPicked(this.edge));
/*     */         }
/*     */       }
/*     */     }
/* 227 */     if (this.vertex != null) { e.consume();
/*     */     }
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public void mouseReleased(MouseEvent e)
/*     */   {
/* 238 */     VisualizationViewer<V, E> vv = (VisualizationViewer)e.getSource();
/* 239 */     if (e.getModifiers() == this.modifiers) {
/* 240 */       if (this.down != null) {
/* 241 */         Point2D out = e.getPoint();
/*     */         
/* 243 */         if ((this.vertex == null) && (!heyThatsTooClose(this.down, out, 5.0D))) {
/* 244 */           pickContainedVertices(vv, this.down, out, true);
/*     */         }
/*     */       }
/* 247 */     } else if ((e.getModifiers() == this.addToSelectionModifiers) && 
/* 248 */       (this.down != null)) {
/* 249 */       Point2D out = e.getPoint();
/*     */       
/* 251 */       if ((this.vertex == null) && (!heyThatsTooClose(this.down, out, 5.0D))) {
/* 252 */         pickContainedVertices(vv, this.down, out, false);
/*     */       }
/*     */     }
/*     */     
/* 256 */     this.down = null;
/* 257 */     this.vertex = null;
/* 258 */     this.edge = null;
/* 259 */     this.rect.setFrame(0.0D, 0.0D, 0.0D, 0.0D);
/* 260 */     vv.removePostRenderPaintable(this.lensPaintable);
/* 261 */     vv.repaint();
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public void mouseDragged(MouseEvent e)
/*     */   {
/* 273 */     if (!this.locked) {
/* 274 */       VisualizationViewer<V, E> vv = (VisualizationViewer)e.getSource();
/* 275 */       if (this.vertex != null) {
/* 276 */         Point p = e.getPoint();
/* 277 */         Point2D graphPoint = vv.getRenderContext().getMultiLayerTransformer().inverseTransform(p);
/* 278 */         Point2D graphDown = vv.getRenderContext().getMultiLayerTransformer().inverseTransform(this.down);
/* 279 */         Layout<V, E> layout = vv.getGraphLayout();
/* 280 */         double dx = graphPoint.getX() - graphDown.getX();
/* 281 */         double dy = graphPoint.getY() - graphDown.getY();
/* 282 */         PickedState<V> ps = vv.getPickedVertexState();
/*     */         
/* 284 */         for (V v : ps.getPicked()) {
/* 285 */           Point2D vp = (Point2D)layout.transform(v);
/* 286 */           vp.setLocation(vp.getX() + dx, vp.getY() + dy);
/* 287 */           layout.setLocation(v, vp);
/*     */         }
/* 289 */         this.down = p;
/*     */       }
/*     */       else {
/* 292 */         Point2D out = e.getPoint();
/* 293 */         if ((e.getModifiers() == this.addToSelectionModifiers) || (e.getModifiers() == this.modifiers))
/*     */         {
/* 295 */           this.rect.setFrameFromDiagonal(this.down, out);
/*     */         }
/*     */       }
/* 298 */       if (this.vertex != null) e.consume();
/* 299 */       vv.repaint();
/*     */     }
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   private boolean heyThatsTooClose(Point2D p, Point2D q, double min)
/*     */   {
/* 313 */     return (Math.abs(p.getX() - q.getX()) < min) && (Math.abs(p.getY() - q.getY()) < min);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   protected void pickContainedVertices(VisualizationViewer<V, E> vv, Point2D down, Point2D out, boolean clear)
/*     */   {
/* 324 */     Layout<V, E> layout = vv.getGraphLayout();
/* 325 */     PickedState<V> pickedVertexState = vv.getPickedVertexState();
/*     */     
/* 327 */     Rectangle2D pickRectangle = new Rectangle2D.Double();
/* 328 */     pickRectangle.setFrameFromDiagonal(down, out);
/*     */     
/* 330 */     if (pickedVertexState != null) {
/* 331 */       if (clear) {
/* 332 */         pickedVertexState.clear();
/*     */       }
/* 334 */       GraphElementAccessor<V, E> pickSupport = vv.getPickSupport();
/*     */       
/* 336 */       Collection<V> picked = pickSupport.getVertices(layout, pickRectangle);
/* 337 */       for (V v : picked) {
/* 338 */         pickedVertexState.pick(v, true);
/*     */       }
/*     */     }
/*     */   }
/*     */   
/*     */   public void mouseClicked(MouseEvent e) {}
/*     */   
/*     */   public void mouseEntered(MouseEvent e)
/*     */   {
/* 347 */     JComponent c = (JComponent)e.getSource();
/* 348 */     c.setCursor(this.cursor);
/*     */   }
/*     */   
/*     */   public void mouseExited(MouseEvent e) {
/* 352 */     JComponent c = (JComponent)e.getSource();
/* 353 */     c.setCursor(Cursor.getPredefinedCursor(0));
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */   public void mouseMoved(MouseEvent e) {}
/*     */   
/*     */ 
/*     */   public boolean isLocked()
/*     */   {
/* 363 */     return this.locked;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */   public void setLocked(boolean locked)
/*     */   {
/* 370 */     this.locked = locked;
/*     */   }
/*     */ }


/* Location:              /home/menick/Scrivania/ProcessMining/CNMining/CNMining.jar!/edu/uci/ics/jung/visualization/control/PickingGraphMousePlugin.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       0.7.1
 */