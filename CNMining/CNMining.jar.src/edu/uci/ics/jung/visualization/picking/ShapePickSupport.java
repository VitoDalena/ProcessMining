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
/*     */ import java.util.LinkedHashSet;
/*     */ import java.util.Set;
/*     */ import org.apache.commons.collections15.Predicate;
/*     */ import org.apache.commons.collections15.Transformer;
/*     */ import org.apache.commons.collections15.functors.TruePredicate;
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
/*     */ public class ShapePickSupport<V, E>
/*     */   implements GraphElementAccessor<V, E>
/*     */ {
/*     */   protected float pickSize;
/*     */   protected VisualizationServer<V, E> vv;
/*     */   
/*     */   public static enum Style
/*     */   {
/*  62 */     LOWEST,  CENTERED,  HIGHEST;
/*     */     
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     private Style() {}
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*  82 */   protected Style style = Style.CENTERED;
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
/*     */   public ShapePickSupport(VisualizationServer<V, E> vv, float pickSize)
/*     */   {
/*  95 */     this.vv = vv;
/*  96 */     this.pickSize = pickSize;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public ShapePickSupport(VisualizationServer<V, E> vv)
/*     */   {
/* 105 */     this.vv = vv;
/* 106 */     this.pickSize = 2.0F;
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
/*     */ 
/*     */ 
/*     */   public Style getStyle()
/*     */   {
/* 130 */     return this.style;
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
/*     */ 
/*     */   public void setStyle(Style style)
/*     */   {
/* 153 */     this.style = style;
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
/* 164 */     V closest = null;
/* 165 */     double minDistance = Double.MAX_VALUE;
/* 166 */     Point2D ip = this.vv.getRenderContext().getMultiLayerTransformer().inverseTransform(Layer.VIEW, new Point2D.Double(x, y));
/*     */     
/* 168 */     x = ip.getX();
/* 169 */     y = ip.getY();
/*     */     for (;;)
/*     */     {
/*     */       try {
/* 173 */         Iterator i$ = getFilteredVertices(layout).iterator(); if (i$.hasNext()) { V v = i$.next();
/*     */           
/* 175 */           Shape shape = (Shape)this.vv.getRenderContext().getVertexShapeTransformer().transform(v);
/*     */           
/* 177 */           Point2D p = (Point2D)layout.transform(v);
/* 178 */           if (p == null)
/*     */             continue;
/* 180 */           p = this.vv.getRenderContext().getMultiLayerTransformer().transform(Layer.LAYOUT, p);
/*     */           
/* 182 */           double ox = x - p.getX();
/* 183 */           double oy = y - p.getY();
/*     */           
/* 185 */           if (shape.contains(ox, oy))
/*     */           {
/* 187 */             if (this.style == Style.LOWEST)
/*     */             {
/* 189 */               return v; }
/* 190 */             if (this.style == Style.HIGHEST)
/*     */             {
/* 192 */               closest = v;
/*     */ 
/*     */             }
/*     */             else
/*     */             {
/* 197 */               Rectangle2D bounds = shape.getBounds2D();
/* 198 */               double dx = bounds.getCenterX() - ox;
/* 199 */               double dy = bounds.getCenterY() - oy;
/* 200 */               double dist = dx * dx + dy * dy;
/* 201 */               if (dist < minDistance) {
/* 202 */                 minDistance = dist;
/* 203 */                 closest = v;
/*     */               }
/*     */             }
/*     */           }
/*     */         }
/*     */       }
/*     */       catch (ConcurrentModificationException cme) {}
/*     */     }
/* 211 */     return closest;
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
/*     */   public Collection<V> getVertices(Layout<V, E> layout, Shape shape)
/*     */   {
/* 224 */     Set<V> pickedVertices = new HashSet();
/*     */     
/*     */ 
/* 227 */     shape = this.vv.getRenderContext().getMultiLayerTransformer().inverseTransform(Layer.VIEW, shape);
/*     */     for (;;)
/*     */     {
/*     */       try {
/* 231 */         Iterator i$ = getFilteredVertices(layout).iterator(); if (i$.hasNext()) { V v = i$.next();
/* 232 */           Point2D p = (Point2D)layout.transform(v);
/* 233 */           if (p == null)
/*     */             continue;
/* 235 */           p = this.vv.getRenderContext().getMultiLayerTransformer().transform(Layer.LAYOUT, p);
/* 236 */           if (shape.contains(p)) {
/* 237 */             pickedVertices.add(v);
/*     */           }
/*     */         }
/*     */       }
/*     */       catch (ConcurrentModificationException cme) {}
/*     */     }
/* 243 */     return pickedVertices;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public E getEdge(Layout<V, E> layout, double x, double y)
/*     */   {
/* 252 */     Point2D ip = this.vv.getRenderContext().getMultiLayerTransformer().inverseTransform(Layer.VIEW, new Point2D.Double(x, y));
/* 253 */     x = ip.getX();
/* 254 */     y = ip.getY();
/*     */     
/*     */ 
/*     */ 
/*     */ 
/* 259 */     Rectangle2D pickArea = new Rectangle2D.Float((float)x - this.pickSize / 2.0F, (float)y - this.pickSize / 2.0F, this.pickSize, this.pickSize);
/*     */     
/* 261 */     E closest = null;
/* 262 */     double minDistance = Double.MAX_VALUE;
/*     */     for (;;) {
/*     */       try {
/* 265 */         Iterator i$ = getFilteredEdges(layout).iterator(); if (i$.hasNext()) { E e = i$.next();
/*     */           
/* 267 */           Shape edgeShape = getTransformedEdgeShape(layout, e);
/* 268 */           if (edgeShape == null) {
/*     */             continue;
/*     */           }
/*     */           
/*     */ 
/* 273 */           if (edgeShape.intersects(pickArea)) {
/* 274 */             float cx = 0.0F;
/* 275 */             float cy = 0.0F;
/* 276 */             float[] f = new float[6];
/* 277 */             PathIterator pi = new GeneralPath(edgeShape).getPathIterator(null);
/* 278 */             if (!pi.isDone()) {
/* 279 */               pi.next();
/* 280 */               pi.currentSegment(f);
/* 281 */               cx = f[0];
/* 282 */               cy = f[1];
/* 283 */               if (!pi.isDone()) {
/* 284 */                 pi.currentSegment(f);
/* 285 */                 cx = f[0];
/* 286 */                 cy = f[1];
/*     */               }
/*     */             }
/* 289 */             float dx = (float)(cx - x);
/* 290 */             float dy = (float)(cy - y);
/* 291 */             float dist = dx * dx + dy * dy;
/* 292 */             if (dist < minDistance) {
/* 293 */               minDistance = dist;
/* 294 */               closest = e;
/*     */             }
/*     */           }
/*     */         }
/*     */       }
/*     */       catch (ConcurrentModificationException cme) {}
/*     */     }
/* 301 */     return closest;
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
/*     */   private Shape getTransformedEdgeShape(Layout<V, E> layout, E e)
/*     */   {
/* 314 */     Pair<V> pair = layout.getGraph().getEndpoints(e);
/* 315 */     V v1 = pair.getFirst();
/* 316 */     V v2 = pair.getSecond();
/* 317 */     boolean isLoop = v1.equals(v2);
/* 318 */     Point2D p1 = this.vv.getRenderContext().getMultiLayerTransformer().transform(Layer.LAYOUT, (Point2D)layout.transform(v1));
/* 319 */     Point2D p2 = this.vv.getRenderContext().getMultiLayerTransformer().transform(Layer.LAYOUT, (Point2D)layout.transform(v2));
/* 320 */     if ((p1 == null) || (p2 == null))
/* 321 */       return null;
/* 322 */     float x1 = (float)p1.getX();
/* 323 */     float y1 = (float)p1.getY();
/* 324 */     float x2 = (float)p2.getX();
/* 325 */     float y2 = (float)p2.getY();
/*     */     
/*     */ 
/* 328 */     AffineTransform xform = AffineTransform.getTranslateInstance(x1, y1);
/*     */     
/* 330 */     Shape edgeShape = (Shape)this.vv.getRenderContext().getEdgeShapeTransformer().transform(Context.getInstance(this.vv.getGraphLayout().getGraph(), e));
/*     */     
/* 332 */     if (isLoop)
/*     */     {
/* 334 */       Shape s2 = (Shape)this.vv.getRenderContext().getVertexShapeTransformer().transform(v2);
/* 335 */       Rectangle2D s2Bounds = s2.getBounds2D();
/* 336 */       xform.scale(s2Bounds.getWidth(), s2Bounds.getHeight());
/*     */       
/* 338 */       xform.translate(0.0D, -edgeShape.getBounds2D().getHeight() / 2.0D);
/*     */     } else {
/* 340 */       float dx = x2 - x1;
/* 341 */       float dy = y2 - y1;
/*     */       
/* 343 */       double theta = Math.atan2(dy, dx);
/* 344 */       xform.rotate(theta);
/*     */       
/* 346 */       float dist = (float)Math.sqrt(dx * dx + dy * dy);
/* 347 */       xform.scale(dist, 1.0D);
/*     */     }
/*     */     
/*     */ 
/* 351 */     edgeShape = xform.createTransformedShape(edgeShape);
/* 352 */     return edgeShape;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   protected Collection<V> getFilteredVertices(Layout<V, E> layout)
/*     */   {
/* 361 */     if (verticesAreFiltered()) {
/* 362 */       Collection<V> unfiltered = layout.getGraph().getVertices();
/* 363 */       Collection<V> filtered = new LinkedHashSet();
/* 364 */       for (V v : unfiltered) {
/* 365 */         if (isVertexRendered(Context.getInstance(layout.getGraph(), v))) {
/* 366 */           filtered.add(v);
/*     */         }
/*     */       }
/* 369 */       return filtered;
/*     */     }
/* 371 */     return layout.getGraph().getVertices();
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   protected Collection<E> getFilteredEdges(Layout<V, E> layout)
/*     */   {
/* 381 */     if (edgesAreFiltered()) {
/* 382 */       Collection<E> unfiltered = layout.getGraph().getEdges();
/* 383 */       Collection<E> filtered = new LinkedHashSet();
/* 384 */       for (E e : unfiltered) {
/* 385 */         if (isEdgeRendered(Context.getInstance(layout.getGraph(), e))) {
/* 386 */           filtered.add(e);
/*     */         }
/*     */       }
/* 389 */       return filtered;
/*     */     }
/* 391 */     return layout.getGraph().getEdges();
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   protected boolean verticesAreFiltered()
/*     */   {
/* 401 */     Predicate<Context<Graph<V, E>, V>> vertexIncludePredicate = this.vv.getRenderContext().getVertexIncludePredicate();
/*     */     
/* 403 */     return (vertexIncludePredicate != null) && (!(vertexIncludePredicate instanceof TruePredicate));
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   protected boolean edgesAreFiltered()
/*     */   {
/* 413 */     Predicate<Context<Graph<V, E>, E>> edgeIncludePredicate = this.vv.getRenderContext().getEdgeIncludePredicate();
/*     */     
/* 415 */     return (edgeIncludePredicate != null) && (!(edgeIncludePredicate instanceof TruePredicate));
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
/*     */   protected boolean isVertexRendered(Context<Graph<V, E>, V> context)
/*     */   {
/* 428 */     Predicate<Context<Graph<V, E>, V>> vertexIncludePredicate = this.vv.getRenderContext().getVertexIncludePredicate();
/*     */     
/* 430 */     return (vertexIncludePredicate == null) || (vertexIncludePredicate.evaluate(context));
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
/*     */   protected boolean isEdgeRendered(Context<Graph<V, E>, E> context)
/*     */   {
/* 443 */     Predicate<Context<Graph<V, E>, V>> vertexIncludePredicate = this.vv.getRenderContext().getVertexIncludePredicate();
/*     */     
/* 445 */     Predicate<Context<Graph<V, E>, E>> edgeIncludePredicate = this.vv.getRenderContext().getEdgeIncludePredicate();
/*     */     
/* 447 */     Graph<V, E> g = (Graph)context.graph;
/* 448 */     E e = context.element;
/* 449 */     boolean edgeTest = (edgeIncludePredicate == null) || (edgeIncludePredicate.evaluate(context));
/* 450 */     Pair<V> endpoints = g.getEndpoints(e);
/* 451 */     V v1 = endpoints.getFirst();
/* 452 */     V v2 = endpoints.getSecond();
/* 453 */     boolean endpointsTest = (vertexIncludePredicate == null) || ((vertexIncludePredicate.evaluate(Context.getInstance(g, v1))) && (vertexIncludePredicate.evaluate(Context.getInstance(g, v2))));
/*     */     
/*     */ 
/* 456 */     return (edgeTest) && (endpointsTest);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public float getPickSize()
/*     */   {
/* 466 */     return this.pickSize;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   public void setPickSize(float pickSize)
/*     */   {
/* 474 */     this.pickSize = pickSize;
/*     */   }
/*     */ }


/* Location:              /home/menick/Scrivania/ProcessMining/CNMining/CNMining.jar!/edu/uci/ics/jung/visualization/picking/ShapePickSupport.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       0.7.1
 */