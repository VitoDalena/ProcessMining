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
/*     */ 
/*     */ public class LayoutLensShapePickSupport<V, E>
/*     */   extends ShapePickSupport<V, E>
/*     */   implements GraphElementAccessor<V, E>
/*     */ {
/*     */   public LayoutLensShapePickSupport(VisualizationServer<V, E> vv, float pickSize)
/*     */   {
/*  53 */     super(vv, pickSize);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   public LayoutLensShapePickSupport(VisualizationServer<V, E> vv)
/*     */   {
/*  61 */     this(vv, 2.0F);
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
/*  72 */     V closest = null;
/*  73 */     double minDistance = Double.MAX_VALUE;
/*     */     for (;;)
/*     */     {
/*     */       try {
/*  77 */         Iterator i$ = getFilteredVertices(layout).iterator(); if (i$.hasNext()) { V v = i$.next();
/*     */           
/*  79 */           Shape shape = (Shape)this.vv.getRenderContext().getVertexShapeTransformer().transform(v);
/*     */           
/*  81 */           Point2D p = (Point2D)layout.transform(v);
/*  82 */           if (p == null)
/*     */             continue;
/*  84 */           p = this.vv.getRenderContext().getMultiLayerTransformer().transform(p);
/*  85 */           AffineTransform xform = AffineTransform.getTranslateInstance(p.getX(), p.getY());
/*     */           
/*  87 */           shape = xform.createTransformedShape(shape);
/*     */           
/*     */ 
/*     */ 
/*  91 */           if (shape.contains(x, y))
/*     */           {
/*  93 */             if (this.style == ShapePickSupport.Style.LOWEST)
/*     */             {
/*  95 */               return v; }
/*  96 */             if (this.style == ShapePickSupport.Style.HIGHEST)
/*     */             {
/*  98 */               closest = v;
/*     */             } else {
/* 100 */               Rectangle2D bounds = shape.getBounds2D();
/* 101 */               double dx = bounds.getCenterX() - x;
/* 102 */               double dy = bounds.getCenterY() - y;
/* 103 */               double dist = dx * dx + dy * dy;
/* 104 */               if (dist < minDistance) {
/* 105 */                 minDistance = dist;
/* 106 */                 closest = v;
/*     */               }
/*     */             }
/*     */           }
/*     */         }
/*     */       }
/*     */       catch (ConcurrentModificationException cme) {}
/*     */     }
/* 114 */     return closest;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public Collection<V> getVertices(Layout<V, E> layout, Shape rectangle)
/*     */   {
/* 124 */     Set<V> pickedVertices = new HashSet();
/*     */     for (;;)
/*     */     {
/*     */       try {
/* 128 */         Iterator i$ = getFilteredVertices(layout).iterator(); if (i$.hasNext()) { V v = i$.next();
/* 129 */           Point2D p = (Point2D)layout.transform(v);
/* 130 */           if (p == null)
/*     */             continue;
/* 132 */           p = this.vv.getRenderContext().getMultiLayerTransformer().transform(p);
/* 133 */           if (rectangle.contains(p)) {
/* 134 */             pickedVertices.add(v);
/*     */           }
/*     */         }
/*     */       }
/*     */       catch (ConcurrentModificationException cme) {}
/*     */     }
/* 140 */     return pickedVertices;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   public E getEdge(Layout<V, E> layout, double x, double y)
/*     */   {
/* 148 */     Point2D ip = this.vv.getRenderContext().getMultiLayerTransformer().inverseTransform(Layer.VIEW, new Point2D.Double(x, y));
/* 149 */     x = ip.getX();
/* 150 */     y = ip.getY();
/*     */     
/*     */ 
/*     */ 
/*     */ 
/* 155 */     Rectangle2D pickArea = new Rectangle2D.Float((float)x - this.pickSize / 2.0F, (float)y - this.pickSize / 2.0F, this.pickSize, this.pickSize);
/*     */     
/* 157 */     E closest = null;
/* 158 */     double minDistance = Double.MAX_VALUE;
/*     */     for (;;) {
/*     */       try {
/* 161 */         Iterator i$ = getFilteredEdges(layout).iterator(); if (i$.hasNext()) { E e = i$.next();
/*     */           
/* 163 */           Pair<V> pair = layout.getGraph().getEndpoints(e);
/* 164 */           V v1 = pair.getFirst();
/* 165 */           V v2 = pair.getSecond();
/* 166 */           boolean isLoop = v1.equals(v2);
/* 167 */           Point2D p1 = this.vv.getRenderContext().getMultiLayerTransformer().transform(Layer.LAYOUT, (Point2D)layout.transform(v1));
/* 168 */           Point2D p2 = this.vv.getRenderContext().getMultiLayerTransformer().transform(Layer.LAYOUT, (Point2D)layout.transform(v2));
/* 169 */           if ((p1 == null) || (p2 == null)) continue;
/* 170 */           float x1 = (float)p1.getX();
/* 171 */           float y1 = (float)p1.getY();
/* 172 */           float x2 = (float)p2.getX();
/* 173 */           float y2 = (float)p2.getY();
/*     */           
/*     */ 
/* 176 */           AffineTransform xform = AffineTransform.getTranslateInstance(x1, y1);
/*     */           
/* 178 */           Shape edgeShape = (Shape)this.vv.getRenderContext().getEdgeShapeTransformer().transform(Context.getInstance(this.vv.getGraphLayout().getGraph(), e));
/*     */           
/* 180 */           if (isLoop)
/*     */           {
/* 182 */             Shape s2 = (Shape)this.vv.getRenderContext().getVertexShapeTransformer().transform(v2);
/* 183 */             Rectangle2D s2Bounds = s2.getBounds2D();
/* 184 */             xform.scale(s2Bounds.getWidth(), s2Bounds.getHeight());
/*     */             
/* 186 */             xform.translate(0.0D, -edgeShape.getBounds2D().getHeight() / 2.0D);
/*     */           } else {
/* 188 */             float dx = x2 - x1;
/* 189 */             float dy = y2 - y1;
/*     */             
/* 191 */             double theta = Math.atan2(dy, dx);
/* 192 */             xform.rotate(theta);
/*     */             
/* 194 */             float dist = (float)Math.sqrt(dx * dx + dy * dy);
/* 195 */             xform.scale(dist, 1.0D);
/*     */           }
/*     */           
/*     */ 
/* 199 */           edgeShape = xform.createTransformedShape(edgeShape);
/*     */           
/*     */ 
/*     */ 
/* 203 */           if (edgeShape.intersects(pickArea)) {
/* 204 */             float cx = 0.0F;
/* 205 */             float cy = 0.0F;
/* 206 */             float[] f = new float[6];
/* 207 */             PathIterator pi = new GeneralPath(edgeShape).getPathIterator(null);
/* 208 */             if (!pi.isDone()) {
/* 209 */               pi.next();
/* 210 */               pi.currentSegment(f);
/* 211 */               cx = f[0];
/* 212 */               cy = f[1];
/* 213 */               if (!pi.isDone()) {
/* 214 */                 pi.currentSegment(f);
/* 215 */                 cx = f[0];
/* 216 */                 cy = f[1];
/*     */               }
/*     */             }
/* 219 */             float dx = (float)(cx - x);
/* 220 */             float dy = (float)(cy - y);
/* 221 */             float dist = dx * dx + dy * dy;
/* 222 */             if (dist < minDistance) {
/* 223 */               minDistance = dist;
/* 224 */               closest = e;
/*     */             }
/*     */           }
/*     */         }
/*     */       }
/*     */       catch (ConcurrentModificationException cme) {}
/*     */     }
/* 231 */     return closest;
/*     */   }
/*     */ }


/* Location:              /home/menick/Scrivania/ProcessMining/CNMining/CNMining.jar!/edu/uci/ics/jung/visualization/picking/LayoutLensShapePickSupport.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       0.7.1
 */