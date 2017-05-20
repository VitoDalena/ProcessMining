/*     */ package edu.uci.ics.jung.visualization.picking;
/*     */ 
/*     */ import edu.uci.ics.jung.algorithms.layout.GraphElementAccessor;
/*     */ import edu.uci.ics.jung.algorithms.layout.Layout;
/*     */ import edu.uci.ics.jung.graph.Graph;
/*     */ import edu.uci.ics.jung.graph.util.Context;
/*     */ import edu.uci.ics.jung.graph.util.Pair;
/*     */ import edu.uci.ics.jung.visualization.Layer;
/*     */ import edu.uci.ics.jung.visualization.MultiLayerTransformer;
/*     */ import edu.uci.ics.jung.visualization.RenderContext;
/*     */ import edu.uci.ics.jung.visualization.VisualizationServer;
/*     */ import edu.uci.ics.jung.visualization.transform.MutableTransformer;
/*     */ import edu.uci.ics.jung.visualization.transform.MutableTransformerDecorator;
/*     */ import java.awt.Shape;
/*     */ import java.awt.geom.AffineTransform;
/*     */ import java.awt.geom.GeneralPath;
/*     */ import java.awt.geom.PathIterator;
/*     */ import java.awt.geom.Point2D;
/*     */ import java.awt.geom.Point2D.Double;
/*     */ import java.awt.geom.Rectangle2D;
/*     */ import java.awt.geom.Rectangle2D.Float;
/*     */ import java.util.Collection;
/*     */ import java.util.ConcurrentModificationException;
/*     */ import java.util.HashSet;
/*     */ import java.util.Iterator;
/*     */ import java.util.Set;
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
/*     */ public class ViewLensShapePickSupport<V, E>
/*     */   extends ShapePickSupport<V, E>
/*     */   implements GraphElementAccessor<V, E>
/*     */ {
/*     */   public ViewLensShapePickSupport(VisualizationServer<V, E> vv, float pickSize)
/*     */   {
/*  54 */     super(vv, pickSize);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   public ViewLensShapePickSupport(VisualizationServer<V, E> vv)
/*     */   {
/*  62 */     this(vv, 2.0F);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public V getVertex(Layout<V, E> layout, double x, double y)
/*     */   {
/*  73 */     V closest = null;
/*  74 */     double minDistance = Double.MAX_VALUE;
/*  75 */     Point2D ip = ((MutableTransformerDecorator)this.vv.getRenderContext().getMultiLayerTransformer().getTransformer(Layer.VIEW)).getDelegate().inverseTransform(new Point2D.Double(x, y));
/*  76 */     x = ip.getX();
/*  77 */     y = ip.getY();
/*     */     for (;;)
/*     */     {
/*     */       try {
/*  81 */         Iterator i$ = getFilteredVertices(layout).iterator(); if (i$.hasNext()) { V v = i$.next();
/*     */           
/*  83 */           Shape shape = (Shape)this.vv.getRenderContext().getVertexShapeTransformer().transform(v);
/*     */           
/*  85 */           Point2D p = (Point2D)layout.transform(v);
/*  86 */           if (p == null) continue;
/*  87 */           AffineTransform xform = AffineTransform.getTranslateInstance(p.getX(), p.getY());
/*     */           
/*  89 */           shape = xform.createTransformedShape(shape);
/*     */           
/*     */ 
/*     */ 
/*  93 */           Point2D lp = this.vv.getRenderContext().getMultiLayerTransformer().transform(Layer.LAYOUT, p);
/*  94 */           AffineTransform xlate = AffineTransform.getTranslateInstance(lp.getX() - p.getX(), lp.getY() - p.getY());
/*     */           
/*  96 */           shape = xlate.createTransformedShape(shape);
/*     */           
/*     */ 
/*  99 */           shape = this.vv.getRenderContext().getMultiLayerTransformer().transform(Layer.VIEW, shape);
/*     */           
/*     */ 
/*     */ 
/*     */ 
/* 104 */           if (shape.contains(x, y))
/*     */           {
/* 106 */             if (this.style == ShapePickSupport.Style.LOWEST)
/*     */             {
/* 108 */               return v; }
/* 109 */             if (this.style == ShapePickSupport.Style.HIGHEST)
/*     */             {
/* 111 */               closest = v;
/*     */             } else {
/* 113 */               Rectangle2D bounds = shape.getBounds2D();
/* 114 */               double dx = bounds.getCenterX() - x;
/* 115 */               double dy = bounds.getCenterY() - y;
/* 116 */               double dist = dx * dx + dy * dy;
/* 117 */               if (dist < minDistance) {
/* 118 */                 minDistance = dist;
/* 119 */                 closest = v;
/*     */               }
/*     */             }
/*     */           }
/*     */         }
/*     */       }
/*     */       catch (ConcurrentModificationException cme) {}
/*     */     }
/* 127 */     return closest;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public Collection<V> getVertices(Layout<V, E> layout, Shape rectangle)
/*     */   {
/* 137 */     Set<V> pickedVertices = new HashSet();
/*     */     
/*     */ 
/* 140 */     rectangle = ((MutableTransformerDecorator)this.vv.getRenderContext().getMultiLayerTransformer().getTransformer(Layer.VIEW)).getDelegate().inverseTransform(rectangle);
/*     */     for (;;)
/*     */     {
/*     */       try {
/* 144 */         Iterator i$ = getFilteredVertices(layout).iterator(); if (i$.hasNext()) { V v = i$.next();
/* 145 */           Point2D p = (Point2D)layout.transform(v);
/* 146 */           if (p == null)
/*     */             continue;
/* 148 */           Shape shape = (Shape)this.vv.getRenderContext().getVertexShapeTransformer().transform(v);
/*     */           
/* 150 */           AffineTransform xform = AffineTransform.getTranslateInstance(p.getX(), p.getY());
/*     */           
/* 152 */           shape = xform.createTransformedShape(shape);
/*     */           
/* 154 */           shape = this.vv.getRenderContext().getMultiLayerTransformer().transform(shape);
/* 155 */           Rectangle2D bounds = shape.getBounds2D();
/* 156 */           p.setLocation(bounds.getCenterX(), bounds.getCenterY());
/*     */           
/* 158 */           if (rectangle.contains(p)) {
/* 159 */             pickedVertices.add(v);
/*     */           }
/*     */         }
/*     */       }
/*     */       catch (ConcurrentModificationException cme) {}
/*     */     }
/* 165 */     return pickedVertices;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   public E getEdge(Layout<V, E> layout, double x, double y)
/*     */   {
/* 173 */     Point2D ip = ((MutableTransformerDecorator)this.vv.getRenderContext().getMultiLayerTransformer().getTransformer(Layer.VIEW)).getDelegate().inverseTransform(new Point2D.Double(x, y));
/* 174 */     x = ip.getX();
/* 175 */     y = ip.getY();
/*     */     
/*     */ 
/*     */ 
/*     */ 
/* 180 */     Rectangle2D pickArea = new Rectangle2D.Float((float)x - this.pickSize / 2.0F, (float)y - this.pickSize / 2.0F, this.pickSize, this.pickSize);
/*     */     
/* 182 */     E closest = null;
/* 183 */     double minDistance = Double.MAX_VALUE;
/*     */     for (;;) {
/*     */       try {
/* 186 */         Iterator i$ = getFilteredEdges(layout).iterator(); if (i$.hasNext()) { E e = i$.next();
/*     */           
/* 188 */           Pair<V> pair = layout.getGraph().getEndpoints(e);
/* 189 */           V v1 = pair.getFirst();
/* 190 */           V v2 = pair.getSecond();
/* 191 */           boolean isLoop = v1.equals(v2);
/* 192 */           Point2D p1 = (Point2D)layout.transform(v1);
/*     */           
/* 194 */           Point2D p2 = (Point2D)layout.transform(v2);
/*     */           
/* 196 */           if ((p1 == null) || (p2 == null)) continue;
/* 197 */           float x1 = (float)p1.getX();
/* 198 */           float y1 = (float)p1.getY();
/* 199 */           float x2 = (float)p2.getX();
/* 200 */           float y2 = (float)p2.getY();
/*     */           
/*     */ 
/* 203 */           AffineTransform xform = AffineTransform.getTranslateInstance(x1, y1);
/*     */           
/* 205 */           Shape edgeShape = (Shape)this.vv.getRenderContext().getEdgeShapeTransformer().transform(Context.getInstance(this.vv.getGraphLayout().getGraph(), e));
/*     */           
/* 207 */           if (isLoop)
/*     */           {
/* 209 */             Shape s2 = (Shape)this.vv.getRenderContext().getVertexShapeTransformer().transform(v2);
/* 210 */             Rectangle2D s2Bounds = s2.getBounds2D();
/* 211 */             xform.scale(s2Bounds.getWidth(), s2Bounds.getHeight());
/*     */             
/* 213 */             xform.translate(0.0D, -edgeShape.getBounds2D().getHeight() / 2.0D);
/*     */           } else {
/* 215 */             float dx = x2 - x1;
/* 216 */             float dy = y2 - y1;
/*     */             
/* 218 */             double theta = Math.atan2(dy, dx);
/* 219 */             xform.rotate(theta);
/*     */             
/* 221 */             float dist = (float)Math.sqrt(dx * dx + dy * dy);
/* 222 */             xform.scale(dist, 1.0D);
/*     */           }
/*     */           
/*     */ 
/* 226 */           edgeShape = xform.createTransformedShape(edgeShape);
/*     */           
/* 228 */           edgeShape = this.vv.getRenderContext().getMultiLayerTransformer().transform(edgeShape);
/*     */           
/*     */ 
/*     */ 
/* 232 */           if (edgeShape.intersects(pickArea)) {
/* 233 */             float cx = 0.0F;
/* 234 */             float cy = 0.0F;
/* 235 */             float[] f = new float[6];
/* 236 */             PathIterator pi = new GeneralPath(edgeShape).getPathIterator(null);
/* 237 */             if (!pi.isDone()) {
/* 238 */               pi.next();
/* 239 */               pi.currentSegment(f);
/* 240 */               cx = f[0];
/* 241 */               cy = f[1];
/* 242 */               if (!pi.isDone()) {
/* 243 */                 pi.currentSegment(f);
/* 244 */                 cx = f[0];
/* 245 */                 cy = f[1];
/*     */               }
/*     */             }
/* 248 */             float dx = (float)(cx - x);
/* 249 */             float dy = (float)(cy - y);
/* 250 */             float dist = dx * dx + dy * dy;
/* 251 */             if (dist < minDistance) {
/* 252 */               minDistance = dist;
/* 253 */               closest = e;
/*     */             }
/*     */           }
/*     */         }
/*     */       }
/*     */       catch (ConcurrentModificationException cme) {}
/*     */     }
/* 260 */     return closest;
/*     */   }
/*     */ }


/* Location:              /home/menick/Scrivania/ProcessMining/CNMining/CNMining.jar!/edu/uci/ics/jung/visualization/picking/ViewLensShapePickSupport.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       0.7.1
 */