/*     */ package edu.uci.ics.jung.algorithms.layout;
/*     */ 
/*     */ import edu.uci.ics.jung.algorithms.layout.util.RandomLocationTransformer;
/*     */ import edu.uci.ics.jung.algorithms.util.IterativeContext;
/*     */ import edu.uci.ics.jung.graph.Graph;
/*     */ import java.awt.Dimension;
/*     */ import java.awt.geom.Point2D;
/*     */ import java.awt.geom.Point2D.Double;
/*     */ import java.util.ArrayList;
/*     */ import java.util.Collection;
/*     */ import java.util.ConcurrentModificationException;
/*     */ import java.util.HashMap;
/*     */ import java.util.Iterator;
/*     */ import java.util.List;
/*     */ import java.util.Map;
/*     */ import org.apache.commons.collections15.Factory;
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
/*     */ public class ISOMLayout<V, E>
/*     */   extends AbstractLayout<V, E>
/*     */   implements IterativeContext
/*     */ {
/*  35 */   Map<V, ISOMVertexData> isomVertexData = LazyMap.decorate(new HashMap(), new Factory()
/*     */   {
/*     */     public ISOMLayout.ISOMVertexData create()
/*     */     {
/*  39 */       return new ISOMLayout.ISOMVertexData();
/*     */     }
/*  35 */   });
/*     */   
/*     */   private int maxEpoch;
/*     */   
/*     */   private int epoch;
/*     */   
/*     */   private int radiusConstantTime;
/*     */   
/*     */   private int radius;
/*     */   
/*     */   private int minRadius;
/*     */   
/*     */   private double adaption;
/*     */   
/*     */   private double initialAdaption;
/*     */   
/*     */   private double minAdaption;
/*     */   
/*  53 */   protected GraphElementAccessor<V, E> elementAccessor = new RadiusGraphElementAccessor();
/*     */   
/*     */ 
/*     */   private double coolingFactor;
/*     */   
/*  58 */   private List<V> queue = new ArrayList();
/*  59 */   private String status = null;
/*     */   
/*     */ 
/*     */ 
/*     */   public String getStatus()
/*     */   {
/*  65 */     return this.status;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   public ISOMLayout(Graph<V, E> g)
/*     */   {
/*  73 */     super(g);
/*     */   }
/*     */   
/*     */   public void initialize()
/*     */   {
/*  78 */     setInitializer(new RandomLocationTransformer(getSize()));
/*  79 */     this.maxEpoch = 2000;
/*  80 */     this.epoch = 1;
/*     */     
/*  82 */     this.radiusConstantTime = 100;
/*  83 */     this.radius = 5;
/*  84 */     this.minRadius = 1;
/*     */     
/*  86 */     this.initialAdaption = 0.9D;
/*  87 */     this.adaption = this.initialAdaption;
/*  88 */     this.minAdaption = 0.0D;
/*     */     
/*     */ 
/*  91 */     this.coolingFactor = 2.0D;
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
/*     */   public void step()
/*     */   {
/* 105 */     this.status = ("epoch: " + this.epoch + "; ");
/* 106 */     if (this.epoch < this.maxEpoch) {
/* 107 */       adjust();
/* 108 */       updateParameters();
/* 109 */       this.status += " status: running";
/*     */     }
/*     */     else {
/* 112 */       this.status = (this.status + "adaption: " + this.adaption + "; ");
/* 113 */       this.status += "status: done";
/*     */     }
/*     */   }
/*     */   
/*     */ 
/*     */   private synchronized void adjust()
/*     */   {
/* 120 */     Point2D tempXYD = new Point2D.Double();
/*     */     
/*     */ 
/* 123 */     tempXYD.setLocation(10.0D + Math.random() * getSize().getWidth(), 10.0D + Math.random() * getSize().getHeight());
/*     */     
/*     */ 
/*     */ 
/* 127 */     V winner = this.elementAccessor.getVertex(this, tempXYD.getX(), tempXYD.getY());
/*     */     for (;;)
/*     */     {
/*     */       try {
/* 131 */         Iterator i$ = getGraph().getVertices().iterator(); if (i$.hasNext()) { V v = i$.next();
/* 132 */           ISOMVertexData ivd = getISOMVertexData(v);
/* 133 */           ivd.distance = 0;
/* 134 */           ivd.visited = false;
/*     */         }
/*     */       }
/*     */       catch (ConcurrentModificationException cme) {}
/*     */     }
/* 139 */     adjustVertex(winner, tempXYD);
/*     */   }
/*     */   
/*     */   private synchronized void updateParameters() {
/* 143 */     this.epoch += 1;
/* 144 */     double factor = Math.exp(-1.0D * this.coolingFactor * (1.0D * this.epoch / this.maxEpoch));
/* 145 */     this.adaption = Math.max(this.minAdaption, factor * this.initialAdaption);
/*     */     
/*     */ 
/* 148 */     if ((this.radius > this.minRadius) && (this.epoch % this.radiusConstantTime == 0)) {
/* 149 */       this.radius -= 1;
/*     */     }
/*     */   }
/*     */   
/*     */   private synchronized void adjustVertex(V v, Point2D tempXYD) {
/* 154 */     this.queue.clear();
/* 155 */     ISOMVertexData ivd = getISOMVertexData(v);
/* 156 */     ivd.distance = 0;
/* 157 */     ivd.visited = true;
/* 158 */     this.queue.add(v);
/*     */     
/*     */ 
/* 161 */     while (!this.queue.isEmpty()) {
/* 162 */       V current = this.queue.remove(0);
/* 163 */       ISOMVertexData currData = getISOMVertexData(current);
/* 164 */       Point2D currXYData = transform(current);
/*     */       
/* 166 */       double dx = tempXYD.getX() - currXYData.getX();
/* 167 */       double dy = tempXYD.getY() - currXYData.getY();
/* 168 */       double factor = this.adaption / Math.pow(2.0D, currData.distance);
/*     */       
/* 170 */       currXYData.setLocation(currXYData.getX() + factor * dx, currXYData.getY() + factor * dy);
/*     */       
/* 172 */       if (currData.distance < this.radius) {
/* 173 */         Collection<V> s = getGraph().getNeighbors(current);
/*     */         for (;;) {
/*     */           try {
/* 176 */             Iterator i$ = s.iterator(); if (i$.hasNext()) { V child = i$.next();
/* 177 */               ISOMVertexData childData = getISOMVertexData(child);
/* 178 */               if ((childData != null) && (!childData.visited)) {
/* 179 */                 childData.visited = true;
/* 180 */                 currData.distance += 1;
/* 181 */                 this.queue.add(child);
/*     */               }
/*     */             }
/*     */           }
/*     */           catch (ConcurrentModificationException cme) {}
/*     */         }
/*     */       }
/*     */     }
/*     */   }
/*     */   
/*     */   protected ISOMVertexData getISOMVertexData(V v) {
/* 192 */     return (ISOMVertexData)this.isomVertexData.get(v);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   public boolean isIncremental()
/*     */   {
/* 200 */     return true;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public boolean done()
/*     */   {
/* 211 */     return this.epoch >= this.maxEpoch;
/*     */   }
/*     */   
/*     */   protected static class ISOMVertexData {
/*     */     int distance;
/*     */     boolean visited;
/*     */     
/*     */     protected ISOMVertexData() {
/* 219 */       this.distance = 0;
/* 220 */       this.visited = false;
/*     */     }
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   public void reset()
/*     */   {
/* 229 */     this.epoch = 0;
/*     */   }
/*     */ }


/* Location:              /home/menick/Scrivania/ProcessMining/CNMining/CNMining.jar!/edu/uci/ics/jung/algorithms/layout/ISOMLayout.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       0.7.1
 */