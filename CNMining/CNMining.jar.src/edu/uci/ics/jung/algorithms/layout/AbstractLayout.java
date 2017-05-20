/*     */ package edu.uci.ics.jung.algorithms.layout;
/*     */ 
/*     */ import edu.uci.ics.jung.graph.Graph;
/*     */ import java.awt.Dimension;
/*     */ import java.awt.geom.Point2D;
/*     */ import java.awt.geom.Point2D.Double;
/*     */ import java.util.Collection;
/*     */ import java.util.ConcurrentModificationException;
/*     */ import java.util.HashMap;
/*     */ import java.util.HashSet;
/*     */ import java.util.Iterator;
/*     */ import java.util.Map;
/*     */ import java.util.Set;
/*     */ import org.apache.commons.collections15.Transformer;
/*     */ import org.apache.commons.collections15.functors.ChainedTransformer;
/*     */ import org.apache.commons.collections15.functors.CloneTransformer;
/*     */ import org.apache.commons.collections15.map.LazyMap;
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
/*     */ public abstract class AbstractLayout<V, E>
/*     */   implements Layout<V, E>
/*     */ {
/*  44 */   private Set<V> dontmove = new HashSet();
/*     */   
/*     */   protected Dimension size;
/*     */   
/*     */   protected Graph<V, E> graph;
/*     */   protected boolean initialized;
/*  50 */   protected Map<V, Point2D> locations = LazyMap.decorate(new HashMap(), new Transformer()
/*     */   {
/*     */     public Point2D transform(V arg0)
/*     */     {
/*  54 */       return new Point2D.Double();
/*     */     }
/*  50 */   });
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
/*     */   protected AbstractLayout(Graph<V, E> graph)
/*     */   {
/*  64 */     if (graph == null)
/*     */     {
/*  66 */       throw new IllegalArgumentException("Graph must be non-null");
/*     */     }
/*  68 */     this.graph = graph;
/*     */   }
/*     */   
/*     */   protected AbstractLayout(Graph<V, E> graph, Transformer<V, Point2D> initializer)
/*     */   {
/*  73 */     this.graph = graph;
/*  74 */     Transformer<V, ? extends Object> chain = ChainedTransformer.getInstance(initializer, CloneTransformer.getInstance());
/*     */     
/*  76 */     this.locations = LazyMap.decorate(new HashMap(), chain);
/*  77 */     this.initialized = true;
/*     */   }
/*     */   
/*     */   protected AbstractLayout(Graph<V, E> graph, Dimension size) {
/*  81 */     this.graph = graph;
/*  82 */     this.size = size;
/*     */   }
/*     */   
/*     */   protected AbstractLayout(Graph<V, E> graph, Transformer<V, Point2D> initializer, Dimension size)
/*     */   {
/*  87 */     this.graph = graph;
/*  88 */     Transformer<V, ? extends Object> chain = ChainedTransformer.getInstance(initializer, CloneTransformer.getInstance());
/*     */     
/*  90 */     this.locations = LazyMap.decorate(new HashMap(), chain);
/*  91 */     this.size = size;
/*     */   }
/*     */   
/*     */   public void setGraph(Graph<V, E> graph) {
/*  95 */     this.graph = graph;
/*  96 */     if ((this.size != null) && (graph != null)) {
/*  97 */       initialize();
/*     */     }
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public void setSize(Dimension size)
/*     */   {
/* 108 */     if ((size != null) && (this.graph != null))
/*     */     {
/* 110 */       Dimension oldSize = this.size;
/* 111 */       this.size = size;
/* 112 */       initialize();
/*     */       
/* 114 */       if (oldSize != null) {
/* 115 */         adjustLocations(oldSize, size);
/*     */       }
/*     */     }
/*     */   }
/*     */   
/*     */   private void adjustLocations(Dimension oldSize, Dimension size)
/*     */   {
/* 122 */     int xOffset = (size.width - oldSize.width) / 2;
/* 123 */     int yOffset = (size.height - oldSize.height) / 2;
/*     */     for (;;)
/*     */     {
/*     */       try
/*     */       {
/* 128 */         Iterator i$ = getGraph().getVertices().iterator(); if (i$.hasNext()) { V v = i$.next();
/* 129 */           offsetVertex(v, xOffset, yOffset);
/*     */         }
/*     */       }
/*     */       catch (ConcurrentModificationException cme) {}
/*     */     }
/*     */   }
/*     */   
/*     */   public boolean isLocked(V v)
/*     */   {
/* 138 */     return this.dontmove.contains(v);
/*     */   }
/*     */   
/*     */   public void setInitializer(Transformer<V, Point2D> initializer)
/*     */   {
/* 143 */     Transformer<V, ? extends Object> chain = ChainedTransformer.getInstance(initializer, CloneTransformer.getInstance());
/*     */     
/* 145 */     this.locations = LazyMap.decorate(new HashMap(), chain);
/* 146 */     this.initialized = true;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public Dimension getSize()
/*     */   {
/* 156 */     return this.size;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   private Point2D getCoordinates(V v)
/*     */   {
/* 167 */     return (Point2D)this.locations.get(v);
/*     */   }
/*     */   
/*     */   public Point2D transform(V v) {
/* 171 */     return getCoordinates(v);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   public double getX(V v)
/*     */   {
/* 179 */     assert (getCoordinates(v) != null) : ("Cannot getX for an unmapped vertex " + v);
/* 180 */     return getCoordinates(v).getX();
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   public double getY(V v)
/*     */   {
/* 188 */     assert (getCoordinates(v) != null) : ("Cannot getY for an unmapped vertex " + v);
/* 189 */     return getCoordinates(v).getY();
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   protected void offsetVertex(V v, double xOffset, double yOffset)
/*     */   {
/* 198 */     Point2D c = getCoordinates(v);
/* 199 */     c.setLocation(c.getX() + xOffset, c.getY() + yOffset);
/* 200 */     setLocation(v, c);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public Graph<V, E> getGraph()
/*     */   {
/* 209 */     return this.graph;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public void setLocation(V picked, double x, double y)
/*     */   {
/* 219 */     Point2D coord = getCoordinates(picked);
/* 220 */     coord.setLocation(x, y);
/*     */   }
/*     */   
/*     */   public void setLocation(V picked, Point2D p) {
/* 224 */     Point2D coord = getCoordinates(picked);
/* 225 */     coord.setLocation(p);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */   public void lock(V v, boolean state)
/*     */   {
/* 232 */     if (state == true) {
/* 233 */       this.dontmove.add(v);
/*     */     } else {
/* 235 */       this.dontmove.remove(v);
/*     */     }
/*     */   }
/*     */   
/*     */ 
/*     */   public void lock(boolean lock)
/*     */   {
/* 242 */     for (V v : this.graph.getVertices()) {
/* 243 */       lock(v, lock);
/*     */     }
/*     */   }
/*     */ }


/* Location:              /home/menick/Scrivania/ProcessMining/CNMining/CNMining.jar!/edu/uci/ics/jung/algorithms/layout/AbstractLayout.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       0.7.1
 */