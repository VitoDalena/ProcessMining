/*     */ package edu.uci.ics.jung.algorithms.layout;
/*     */ 
/*     */ import edu.uci.ics.jung.graph.Graph;
/*     */ import java.awt.Shape;
/*     */ import java.awt.geom.Point2D;
/*     */ import java.util.Collection;
/*     */ import java.util.ConcurrentModificationException;
/*     */ import java.util.HashSet;
/*     */ import java.util.Iterator;
/*     */ import java.util.Set;
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
/*     */ public class RadiusGraphElementAccessor<V, E>
/*     */   implements GraphElementAccessor<V, E>
/*     */ {
/*     */   protected double maxDistance;
/*     */   
/*     */   public RadiusGraphElementAccessor()
/*     */   {
/*  44 */     this(Math.sqrt(Double.MAX_VALUE));
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */   public RadiusGraphElementAccessor(double maxDistance)
/*     */   {
/*  51 */     this.maxDistance = maxDistance;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public V getVertex(Layout<V, E> layout, double x, double y)
/*     */   {
/*  61 */     return (V)getVertex(layout, x, y, this.maxDistance);
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
/*     */   public V getVertex(Layout<V, E> layout, double x, double y, double maxDistance)
/*     */   {
/*  74 */     double minDistance = maxDistance * maxDistance;
/*  75 */     V closest = null;
/*     */     for (;;) {
/*     */       try {
/*  78 */         Iterator i$ = layout.getGraph().getVertices().iterator(); if (i$.hasNext()) { V v = i$.next();
/*     */           
/*  80 */           Point2D p = (Point2D)layout.transform(v);
/*  81 */           double dx = p.getX() - x;
/*  82 */           double dy = p.getY() - y;
/*  83 */           double dist = dx * dx + dy * dy;
/*  84 */           if (dist < minDistance) {
/*  85 */             minDistance = dist;
/*  86 */             closest = v;
/*     */           }
/*     */         }
/*     */       }
/*     */       catch (ConcurrentModificationException cme) {}
/*     */     }
/*  92 */     return closest;
/*     */   }
/*     */   
/*     */   public Collection<V> getVertices(Layout<V, E> layout, Shape rectangle) {
/*  96 */     Set<V> pickedVertices = new HashSet();
/*     */     for (;;) {
/*     */       try {
/*  99 */         Iterator i$ = layout.getGraph().getVertices().iterator(); if (i$.hasNext()) { V v = i$.next();
/*     */           
/* 101 */           Point2D p = (Point2D)layout.transform(v);
/* 102 */           if (rectangle.contains(p)) {
/* 103 */             pickedVertices.add(v);
/*     */           }
/*     */         }
/*     */       }
/*     */       catch (ConcurrentModificationException cme) {}
/*     */     }
/* 109 */     return pickedVertices;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   public E getEdge(Layout<V, E> layout, double x, double y)
/*     */   {
/* 117 */     return (E)getEdge(layout, x, y, this.maxDistance);
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
/*     */   public E getEdge(Layout<V, E> layout, double x, double y, double maxDistance)
/*     */   {
/* 132 */     double minDistance = maxDistance * maxDistance;
/* 133 */     E closest = null;
/*     */     for (;;) {
/*     */       try {
/* 136 */         Iterator i$ = layout.getGraph().getEdges().iterator(); if (i$.hasNext()) { E e = i$.next();
/*     */           
/*     */ 
/* 139 */           Graph<V, E> graph = layout.getGraph();
/* 140 */           Collection<V> vertices = graph.getIncidentVertices(e);
/* 141 */           Iterator<V> vertexIterator = vertices.iterator();
/* 142 */           V v1 = vertexIterator.next();
/* 143 */           V v2 = vertexIterator.next();
/*     */           
/* 145 */           Point2D p1 = (Point2D)layout.transform(v1);
/* 146 */           Point2D p2 = (Point2D)layout.transform(v2);
/* 147 */           double x1 = p1.getX();
/* 148 */           double y1 = p1.getY();
/* 149 */           double x2 = p2.getX();
/* 150 */           double y2 = p2.getY();
/*     */           
/*     */ 
/* 153 */           if ((x1 == x2) && (y1 == y2))
/*     */             continue;
/* 155 */           double b = ((y - y1) * (y2 - y1) + (x - x1) * (x2 - x1)) / ((x2 - x1) * (x2 - x1) + (y2 - y1) * (y2 - y1));
/*     */           
/*     */           double distance2;
/*     */           
/*     */           double distance2;
/* 160 */           if (b <= 0.0D) {
/* 161 */             distance2 = (x - x1) * (x - x1) + (y - y1) * (y - y1); } else { double distance2;
/* 162 */             if (b >= 1.0D) {
/* 163 */               distance2 = (x - x2) * (x - x2) + (y - y2) * (y - y2);
/*     */             } else {
/* 165 */               double x3 = x1 + b * (x2 - x1);
/* 166 */               double y3 = y1 + b * (y2 - y1);
/* 167 */               distance2 = (x - x3) * (x - x3) + (y - y3) * (y - y3);
/*     */             }
/*     */           }
/* 170 */           if (distance2 < minDistance) {
/* 171 */             minDistance = distance2;
/* 172 */             closest = e;
/*     */           }
/*     */         }
/*     */       }
/*     */       catch (ConcurrentModificationException cme) {}
/*     */     }
/* 178 */     return closest;
/*     */   }
/*     */ }


/* Location:              /home/menick/Scrivania/ProcessMining/CNMining/CNMining.jar!/edu/uci/ics/jung/algorithms/layout/RadiusGraphElementAccessor.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       0.7.1
 */