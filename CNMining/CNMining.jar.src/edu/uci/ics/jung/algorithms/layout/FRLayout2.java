/*     */ package edu.uci.ics.jung.algorithms.layout;
/*     */ 
/*     */ import edu.uci.ics.jung.algorithms.layout.util.RandomLocationTransformer;
/*     */ import edu.uci.ics.jung.algorithms.util.IterativeContext;
/*     */ import edu.uci.ics.jung.graph.Graph;
/*     */ import edu.uci.ics.jung.graph.util.Pair;
/*     */ import java.awt.Dimension;
/*     */ import java.awt.geom.Point2D;
/*     */ import java.awt.geom.Point2D.Double;
/*     */ import java.awt.geom.Rectangle2D;
/*     */ import java.awt.geom.Rectangle2D.Double;
/*     */ import java.util.Collection;
/*     */ import java.util.ConcurrentModificationException;
/*     */ import java.util.HashMap;
/*     */ import java.util.Iterator;
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
/*     */ public class FRLayout2<V, E>
/*     */   extends AbstractLayout<V, E>
/*     */   implements IterativeContext
/*     */ {
/*     */   private double forceConstant;
/*     */   private double temperature;
/*     */   private int currentIteration;
/*  53 */   private int maxIterations = 700;
/*     */   
/*  55 */   private Map<V, Point2D> frVertexData = LazyMap.decorate(new HashMap(), new Factory()
/*     */   {
/*     */     public Point2D create() {
/*  58 */       return new Point2D.Double();
/*     */     }
/*  55 */   });
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*  61 */   private double attraction_multiplier = 0.75D;
/*     */   
/*     */   private double attraction_constant;
/*     */   
/*  65 */   private double repulsion_multiplier = 0.75D;
/*     */   
/*     */   private double repulsion_constant;
/*     */   
/*     */   private double max_dimension;
/*     */   
/*  71 */   private Rectangle2D innerBounds = new Rectangle2D.Double();
/*     */   
/*  73 */   private boolean checked = false;
/*     */   
/*     */ 
/*     */ 
/*     */   public FRLayout2(Graph<V, E> g)
/*     */   {
/*  79 */     super(g);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */   public FRLayout2(Graph<V, E> g, Dimension d)
/*     */   {
/*  86 */     super(g, new RandomLocationTransformer(d), d);
/*  87 */     this.max_dimension = Math.max(d.height, d.width);
/*  88 */     initialize();
/*     */   }
/*     */   
/*     */   public void setSize(Dimension size)
/*     */   {
/*  93 */     if (!this.initialized)
/*  94 */       setInitializer(new RandomLocationTransformer(size));
/*  95 */     super.setSize(size);
/*  96 */     double t = size.width / 50.0D;
/*  97 */     this.innerBounds.setFrameFromDiagonal(t, t, size.width - t, size.height - t);
/*  98 */     this.max_dimension = Math.max(size.height, size.width);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */   public void setAttractionMultiplier(double attraction)
/*     */   {
/* 105 */     this.attraction_multiplier = attraction;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */   public void setRepulsionMultiplier(double repulsion)
/*     */   {
/* 112 */     this.repulsion_multiplier = repulsion;
/*     */   }
/*     */   
/*     */   public void reset() {
/* 116 */     doInit();
/*     */   }
/*     */   
/*     */   public void initialize() {
/* 120 */     doInit();
/*     */   }
/*     */   
/*     */   private void doInit() {
/* 124 */     Graph<V, E> graph = getGraph();
/* 125 */     Dimension d = getSize();
/* 126 */     if ((graph != null) && (d != null)) {
/* 127 */       this.currentIteration = 0;
/* 128 */       this.temperature = (d.getWidth() / 10.0D);
/*     */       
/* 130 */       this.forceConstant = Math.sqrt(d.getHeight() * d.getWidth() / graph.getVertexCount());
/*     */       
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/* 136 */       this.attraction_constant = (this.attraction_multiplier * this.forceConstant);
/* 137 */       this.repulsion_constant = (this.repulsion_multiplier * this.forceConstant);
/*     */     }
/*     */   }
/*     */   
/* 141 */   private double EPSILON = 1.0E-6D;
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   public synchronized void step()
/*     */   {
/* 148 */     this.currentIteration += 1;
/*     */     
/*     */ 
/*     */ 
/*     */     for (;;)
/*     */     {
/*     */       try
/*     */       {
/* 156 */         Iterator i$ = getGraph().getVertices().iterator(); if (i$.hasNext()) { V v1 = i$.next();
/* 157 */           calcRepulsion(v1);
/*     */         }
/*     */       }
/*     */       catch (ConcurrentModificationException cme) {}
/*     */     }
/*     */     
/*     */ 
/*     */     for (;;)
/*     */     {
/*     */       try
/*     */       {
/* 168 */         Iterator i$ = getGraph().getEdges().iterator(); if (i$.hasNext()) { E e = i$.next();
/* 169 */           calcAttraction(e);
/*     */         }
/*     */       }
/*     */       catch (ConcurrentModificationException cme) {}
/*     */     }
/*     */     for (;;)
/*     */     {
/*     */       try
/*     */       {
/* 178 */         Iterator i$ = getGraph().getVertices().iterator(); if (i$.hasNext()) { V v = i$.next();
/* 179 */           if (isLocked(v)) continue;
/* 180 */           calcPositions(v);
/*     */         }
/*     */       }
/*     */       catch (ConcurrentModificationException cme) {}
/*     */     }
/* 185 */     cool();
/*     */   }
/*     */   
/*     */   protected synchronized void calcPositions(V v) {
/* 189 */     Point2D fvd = (Point2D)this.frVertexData.get(v);
/* 190 */     if (fvd == null) return;
/* 191 */     Point2D xyd = transform(v);
/* 192 */     double deltaLength = Math.max(this.EPSILON, Math.sqrt(fvd.getX() * fvd.getX() + fvd.getY() * fvd.getY()));
/*     */     
/*     */ 
/* 195 */     double newXDisp = fvd.getX() / deltaLength * Math.min(deltaLength, this.temperature);
/*     */     
/*     */ 
/* 198 */     assert (!Double.isNaN(newXDisp)) : "Unexpected mathematical result in FRLayout:calcPositions [xdisp]";
/*     */     
/* 200 */     double newYDisp = fvd.getY() / deltaLength * Math.min(deltaLength, this.temperature);
/*     */     
/* 202 */     double newX = xyd.getX() + Math.max(-5.0D, Math.min(5.0D, newXDisp));
/* 203 */     double newY = xyd.getY() + Math.max(-5.0D, Math.min(5.0D, newYDisp));
/*     */     
/* 205 */     newX = Math.max(this.innerBounds.getMinX(), Math.min(newX, this.innerBounds.getMaxX()));
/* 206 */     newY = Math.max(this.innerBounds.getMinY(), Math.min(newY, this.innerBounds.getMaxY()));
/*     */     
/* 208 */     xyd.setLocation(newX, newY);
/*     */   }
/*     */   
/*     */   protected void calcAttraction(E e)
/*     */   {
/* 213 */     Pair<V> endpoints = getGraph().getEndpoints(e);
/* 214 */     V v1 = endpoints.getFirst();
/* 215 */     V v2 = endpoints.getSecond();
/* 216 */     boolean v1_locked = isLocked(v1);
/* 217 */     boolean v2_locked = isLocked(v2);
/*     */     
/* 219 */     if ((v1_locked) && (v2_locked))
/*     */     {
/* 221 */       return;
/*     */     }
/* 223 */     Point2D p1 = transform(v1);
/* 224 */     Point2D p2 = transform(v2);
/* 225 */     if ((p1 == null) || (p2 == null)) return;
/* 226 */     double xDelta = p1.getX() - p2.getX();
/* 227 */     double yDelta = p1.getY() - p2.getY();
/*     */     
/* 229 */     double deltaLength = Math.max(this.EPSILON, p1.distance(p2));
/*     */     
/* 231 */     double force = deltaLength / this.attraction_constant;
/*     */     
/* 233 */     assert (!Double.isNaN(force)) : "Unexpected mathematical result in FRLayout:calcPositions [force]";
/*     */     
/* 235 */     double dx = xDelta * force;
/* 236 */     double dy = yDelta * force;
/* 237 */     Point2D fvd1 = (Point2D)this.frVertexData.get(v1);
/* 238 */     Point2D fvd2 = (Point2D)this.frVertexData.get(v2);
/* 239 */     if (v2_locked)
/*     */     {
/*     */ 
/* 242 */       fvd1.setLocation(fvd1.getX() - 2.0D * dx, fvd1.getY() - 2.0D * dy);
/*     */     } else {
/* 244 */       fvd1.setLocation(fvd1.getX() - dx, fvd1.getY() - dy);
/*     */     }
/* 246 */     if (v1_locked)
/*     */     {
/*     */ 
/* 249 */       fvd2.setLocation(fvd2.getX() + 2.0D * dx, fvd2.getY() + 2.0D * dy);
/*     */     } else {
/* 251 */       fvd2.setLocation(fvd2.getX() + dx, fvd2.getY() + dy);
/*     */     }
/*     */   }
/*     */   
/*     */   protected void calcRepulsion(V v1) {
/* 256 */     Point2D fvd1 = (Point2D)this.frVertexData.get(v1);
/* 257 */     if (fvd1 == null) return;
/* 258 */     fvd1.setLocation(0.0D, 0.0D);
/* 259 */     boolean v1_locked = isLocked(v1);
/*     */     try
/*     */     {
/* 262 */       for (V v2 : getGraph().getVertices())
/*     */       {
/* 264 */         boolean v2_locked = isLocked(v2);
/* 265 */         if ((!v1_locked) || (!v2_locked)) {
/* 266 */           if (v1 != v2) {
/* 267 */             Point2D p1 = transform(v1);
/* 268 */             Point2D p2 = transform(v2);
/* 269 */             if ((p1 != null) && (p2 != null)) {
/* 270 */               double xDelta = p1.getX() - p2.getX();
/* 271 */               double yDelta = p1.getY() - p2.getY();
/*     */               
/* 273 */               double deltaLength = Math.max(this.EPSILON, p1.distanceSq(p2));
/*     */               
/* 275 */               double force = this.repulsion_constant * this.repulsion_constant;
/*     */               
/* 277 */               double forceOverDeltaLength = force / deltaLength;
/*     */               
/* 279 */               assert (!Double.isNaN(force)) : "Unexpected mathematical result in FRLayout:calcPositions [repulsion]";
/*     */               
/* 281 */               if (v2_locked)
/*     */               {
/*     */ 
/* 284 */                 fvd1.setLocation(fvd1.getX() + 2.0D * xDelta * forceOverDeltaLength, fvd1.getY() + 2.0D * yDelta * forceOverDeltaLength);
/*     */               }
/*     */               else
/* 287 */                 fvd1.setLocation(fvd1.getX() + xDelta * forceOverDeltaLength, fvd1.getY() + yDelta * forceOverDeltaLength);
/*     */             }
/*     */           }
/*     */         }
/*     */       }
/*     */     } catch (ConcurrentModificationException cme) {
/* 293 */       calcRepulsion(v1);
/*     */     }
/*     */   }
/*     */   
/*     */   private void cool() {
/* 298 */     this.temperature *= (1.0D - this.currentIteration / this.maxIterations);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */   public void setMaxIterations(int maxIterations)
/*     */   {
/* 305 */     this.maxIterations = maxIterations;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */   public boolean isIncremental()
/*     */   {
/* 312 */     return true;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   public boolean done()
/*     */   {
/* 320 */     if ((this.currentIteration > this.maxIterations) || (this.temperature < 1.0D / this.max_dimension)) {
/* 321 */       if (!this.checked)
/*     */       {
/*     */ 
/*     */ 
/* 325 */         this.checked = true;
/*     */       }
/* 327 */       return true;
/*     */     }
/* 329 */     return false;
/*     */   }
/*     */ }


/* Location:              /home/menick/Scrivania/ProcessMining/CNMining/CNMining.jar!/edu/uci/ics/jung/algorithms/layout/FRLayout2.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       0.7.1
 */