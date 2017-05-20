/*     */ package edu.uci.ics.jung.visualization.picking;
/*     */ 
/*     */ import edu.uci.ics.jung.algorithms.layout.GraphElementAccessor;
/*     */ import edu.uci.ics.jung.algorithms.layout.Layout;
/*     */ import edu.uci.ics.jung.graph.Graph;
/*     */ import edu.uci.ics.jung.visualization.Layer;
/*     */ import edu.uci.ics.jung.visualization.MultiLayerTransformer;
/*     */ import edu.uci.ics.jung.visualization.RenderContext;
/*     */ import edu.uci.ics.jung.visualization.VisualizationServer;
/*     */ import java.awt.Shape;
/*     */ import java.awt.geom.Point2D;
/*     */ import java.util.Collection;
/*     */ import java.util.ConcurrentModificationException;
/*     */ import java.util.Iterator;
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
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class ClosestShapePickSupport<V, E>
/*     */   implements GraphElementAccessor<V, E>
/*     */ {
/*     */   protected VisualizationServer<V, E> vv;
/*     */   protected float pickSize;
/*     */   
/*     */   public ClosestShapePickSupport(VisualizationServer<V, E> vv, float pickSize)
/*     */   {
/*  58 */     this.vv = vv;
/*  59 */     this.pickSize = pickSize;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public ClosestShapePickSupport(VisualizationServer<V, E> vv)
/*     */   {
/*  69 */     this.vv = vv;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   public E getEdge(Layout<V, E> layout, double x, double y)
/*     */   {
/*  77 */     return null;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public V getVertex(Layout<V, E> layout, double x, double y)
/*     */   {
/*  86 */     double minDistance = Double.MAX_VALUE;
/*  87 */     V closest = null;
/*     */     for (;;)
/*     */     {
/*     */       try
/*     */       {
/*  92 */         Iterator i$ = layout.getGraph().getVertices().iterator(); if (i$.hasNext()) { V v = i$.next();
/*     */           
/*  94 */           Point2D p = (Point2D)layout.transform(v);
/*  95 */           double dx = p.getX() - x;
/*  96 */           double dy = p.getY() - y;
/*  97 */           double dist = dx * dx + dy * dy;
/*  98 */           if (dist < minDistance)
/*     */           {
/* 100 */             minDistance = dist;
/* 101 */             closest = v;
/*     */           }
/*     */         }
/*     */       }
/*     */       catch (ConcurrentModificationException cme) {}
/*     */     }
/*     */     
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/* 112 */     Shape shape = (Shape)this.vv.getRenderContext().getVertexShapeTransformer().transform(closest);
/*     */     
/* 114 */     Point2D p = (Point2D)layout.transform(closest);
/*     */     
/* 116 */     p = this.vv.getRenderContext().getMultiLayerTransformer().transform(Layer.LAYOUT, p);
/*     */     
/* 118 */     double ox = x - p.getX();
/* 119 */     double oy = y - p.getY();
/*     */     
/* 121 */     if (shape.contains(ox, oy)) {
/* 122 */       return closest;
/*     */     }
/* 124 */     return null;
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
/*     */   public Collection<V> getVertices(Layout<V, E> layout, Shape rectangle)
/*     */   {
/* 138 */     return null;
/*     */   }
/*     */ }


/* Location:              /home/menick/Scrivania/ProcessMining/CNMining/CNMining.jar!/edu/uci/ics/jung/visualization/picking/ClosestShapePickSupport.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       0.7.1
 */