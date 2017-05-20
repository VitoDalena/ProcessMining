/*     */ package edu.uci.ics.jung.algorithms.layout;
/*     */ 
/*     */ import edu.uci.ics.jung.algorithms.layout.util.RandomLocationTransformer;
/*     */ import edu.uci.ics.jung.algorithms.util.IterativeContext;
/*     */ import edu.uci.ics.jung.graph.Graph;
/*     */ import edu.uci.ics.jung.graph.util.Pair;
/*     */ import java.awt.Dimension;
/*     */ import java.awt.geom.Point2D;
/*     */ import java.awt.geom.Point2D.Double;
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
/*     */ public class FRLayout<V, E>
/*     */   extends AbstractLayout<V, E>
/*     */   implements IterativeContext
/*     */ {
/*     */   private double forceConstant;
/*     */   private double temperature;
/*     */   private int currentIteration;
/*  47 */   private int mMaxIterations = 700;
/*     */   
/*  49 */   private Map<V, FRVertexData> frVertexData = LazyMap.decorate(new HashMap(), new Factory()
/*     */   {
/*     */     public FRLayout.FRVertexData create() {
/*  52 */       return new FRLayout.FRVertexData();
/*     */     }
/*  49 */   });
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*  55 */   private double attraction_multiplier = 0.75D;
/*     */   
/*     */   private double attraction_constant;
/*     */   
/*  59 */   private double repulsion_multiplier = 0.75D;
/*     */   
/*     */ 
/*     */   private double repulsion_constant;
/*     */   
/*     */   private double max_dimension;
/*     */   
/*     */ 
/*     */   public FRLayout(Graph<V, E> g)
/*     */   {
/*  69 */     super(g);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */   public FRLayout(Graph<V, E> g, Dimension d)
/*     */   {
/*  76 */     super(g, new RandomLocationTransformer(d), d);
/*  77 */     initialize();
/*  78 */     this.max_dimension = Math.max(d.height, d.width);
/*     */   }
/*     */   
/*     */   public void setSize(Dimension size)
/*     */   {
/*  83 */     if (!this.initialized) {
/*  84 */       setInitializer(new RandomLocationTransformer(size));
/*     */     }
/*  86 */     super.setSize(size);
/*  87 */     this.max_dimension = Math.max(size.height, size.width);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */   public void setAttractionMultiplier(double attraction)
/*     */   {
/*  94 */     this.attraction_multiplier = attraction;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */   public void setRepulsionMultiplier(double repulsion)
/*     */   {
/* 101 */     this.repulsion_multiplier = repulsion;
/*     */   }
/*     */   
/*     */   public void reset() {
/* 105 */     doInit();
/*     */   }
/*     */   
/*     */   public void initialize() {
/* 109 */     doInit();
/*     */   }
/*     */   
/*     */   private void doInit() {
/* 113 */     Graph<V, E> graph = getGraph();
/* 114 */     Dimension d = getSize();
/* 115 */     if ((graph != null) && (d != null)) {
/* 116 */       this.currentIteration = 0;
/* 117 */       this.temperature = (d.getWidth() / 10.0D);
/*     */       
/* 119 */       this.forceConstant = Math.sqrt(d.getHeight() * d.getWidth() / graph.getVertexCount());
/*     */       
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/* 125 */       this.attraction_constant = (this.attraction_multiplier * this.forceConstant);
/* 126 */       this.repulsion_constant = (this.repulsion_multiplier * this.forceConstant);
/*     */     }
/*     */   }
/*     */   
/* 130 */   private double EPSILON = 1.0E-6D;
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   public synchronized void step()
/*     */   {
/* 137 */     this.currentIteration += 1;
/*     */     
/*     */ 
/*     */ 
/*     */     for (;;)
/*     */     {
/*     */       try
/*     */       {
/* 145 */         Iterator i$ = getGraph().getVertices().iterator(); if (i$.hasNext()) { V v1 = i$.next();
/* 146 */           calcRepulsion(v1);
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
/* 157 */         Iterator i$ = getGraph().getEdges().iterator(); if (i$.hasNext()) { E e = i$.next();
/*     */           
/* 159 */           calcAttraction(e);
/*     */         }
/*     */       }
/*     */       catch (ConcurrentModificationException cme) {}
/*     */     }
/*     */     for (;;)
/*     */     {
/*     */       try
/*     */       {
/* 168 */         Iterator i$ = getGraph().getVertices().iterator(); if (i$.hasNext()) { V v = i$.next();
/* 169 */           if (isLocked(v)) continue;
/* 170 */           calcPositions(v);
/*     */         }
/*     */       }
/*     */       catch (ConcurrentModificationException cme) {}
/*     */     }
/* 175 */     cool();
/*     */   }
/*     */   
/*     */   protected synchronized void calcPositions(V v) {
/* 179 */     FRVertexData fvd = getFRData(v);
/* 180 */     if (fvd == null) return;
/* 181 */     Point2D xyd = transform(v);
/* 182 */     double deltaLength = Math.max(this.EPSILON, fvd.norm());
/*     */     
/* 184 */     double newXDisp = fvd.getX() / deltaLength * Math.min(deltaLength, this.temperature);
/*     */     
/*     */ 
/* 187 */     if (Double.isNaN(newXDisp)) {
/* 188 */       throw new IllegalArgumentException("Unexpected mathematical result in FRLayout:calcPositions [xdisp]");
/*     */     }
/*     */     
/* 191 */     double newYDisp = fvd.getY() / deltaLength * Math.min(deltaLength, this.temperature);
/*     */     
/* 193 */     xyd.setLocation(xyd.getX() + newXDisp, xyd.getY() + newYDisp);
/*     */     
/* 195 */     double borderWidth = getSize().getWidth() / 50.0D;
/* 196 */     double newXPos = xyd.getX();
/* 197 */     if (newXPos < borderWidth) {
/* 198 */       newXPos = borderWidth + Math.random() * borderWidth * 2.0D;
/* 199 */     } else if (newXPos > getSize().getWidth() - borderWidth) {
/* 200 */       newXPos = getSize().getWidth() - borderWidth - Math.random() * borderWidth * 2.0D;
/*     */     }
/*     */     
/*     */ 
/* 204 */     double newYPos = xyd.getY();
/* 205 */     if (newYPos < borderWidth) {
/* 206 */       newYPos = borderWidth + Math.random() * borderWidth * 2.0D;
/* 207 */     } else if (newYPos > getSize().getHeight() - borderWidth) {
/* 208 */       newYPos = getSize().getHeight() - borderWidth - Math.random() * borderWidth * 2.0D;
/*     */     }
/*     */     
/*     */ 
/* 212 */     xyd.setLocation(newXPos, newYPos);
/*     */   }
/*     */   
/*     */   protected void calcAttraction(E e) {
/* 216 */     Pair<V> endpoints = getGraph().getEndpoints(e);
/* 217 */     V v1 = endpoints.getFirst();
/* 218 */     V v2 = endpoints.getSecond();
/* 219 */     boolean v1_locked = isLocked(v1);
/* 220 */     boolean v2_locked = isLocked(v2);
/*     */     
/* 222 */     if ((v1_locked) && (v2_locked))
/*     */     {
/* 224 */       return;
/*     */     }
/* 226 */     Point2D p1 = transform(v1);
/* 227 */     Point2D p2 = transform(v2);
/* 228 */     if ((p1 == null) || (p2 == null)) return;
/* 229 */     double xDelta = p1.getX() - p2.getX();
/* 230 */     double yDelta = p1.getY() - p2.getY();
/*     */     
/* 232 */     double deltaLength = Math.max(this.EPSILON, Math.sqrt(xDelta * xDelta + yDelta * yDelta));
/*     */     
/*     */ 
/* 235 */     double force = deltaLength * deltaLength / this.attraction_constant;
/*     */     
/* 237 */     if (Double.isNaN(force)) { throw new IllegalArgumentException("Unexpected mathematical result in FRLayout:calcPositions [force]");
/*     */     }
/*     */     
/* 240 */     double dx = xDelta / deltaLength * force;
/* 241 */     double dy = yDelta / deltaLength * force;
/* 242 */     if (!v1_locked) {
/* 243 */       FRVertexData fvd1 = getFRData(v1);
/* 244 */       fvd1.offset(-dx, -dy);
/*     */     }
/* 246 */     if (!v2_locked) {
/* 247 */       FRVertexData fvd2 = getFRData(v2);
/* 248 */       fvd2.offset(dx, dy);
/*     */     }
/*     */   }
/*     */   
/*     */   protected void calcRepulsion(V v1) {
/* 253 */     FRVertexData fvd1 = getFRData(v1);
/* 254 */     if (fvd1 == null)
/* 255 */       return;
/* 256 */     fvd1.setLocation(0.0D, 0.0D);
/*     */     try
/*     */     {
/* 259 */       for (V v2 : getGraph().getVertices())
/*     */       {
/*     */ 
/* 262 */         if (v1 != v2) {
/* 263 */           Point2D p1 = transform(v1);
/* 264 */           Point2D p2 = transform(v2);
/* 265 */           if ((p1 != null) && (p2 != null)) {
/* 266 */             double xDelta = p1.getX() - p2.getX();
/* 267 */             double yDelta = p1.getY() - p2.getY();
/*     */             
/* 269 */             double deltaLength = Math.max(this.EPSILON, Math.sqrt(xDelta * xDelta + yDelta * yDelta));
/*     */             
/*     */ 
/* 272 */             double force = this.repulsion_constant * this.repulsion_constant / deltaLength;
/*     */             
/* 274 */             if (Double.isNaN(force)) { throw new RuntimeException("Unexpected mathematical result in FRLayout:calcPositions [repulsion]");
/*     */             }
/*     */             
/* 277 */             fvd1.offset(xDelta / deltaLength * force, yDelta / deltaLength * force);
/*     */           }
/*     */         }
/*     */       }
/*     */     } catch (ConcurrentModificationException cme) {
/* 282 */       calcRepulsion(v1);
/*     */     }
/*     */   }
/*     */   
/*     */   private void cool() {
/* 287 */     this.temperature *= (1.0D - this.currentIteration / this.mMaxIterations);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */   public void setMaxIterations(int maxIterations)
/*     */   {
/* 294 */     this.mMaxIterations = maxIterations;
/*     */   }
/*     */   
/*     */   protected FRVertexData getFRData(V v) {
/* 298 */     return (FRVertexData)this.frVertexData.get(v);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */   public boolean isIncremental()
/*     */   {
/* 305 */     return true;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   public boolean done()
/*     */   {
/* 313 */     if ((this.currentIteration > this.mMaxIterations) || (this.temperature < 1.0D / this.max_dimension))
/*     */     {
/* 315 */       return true;
/*     */     }
/* 317 */     return false;
/*     */   }
/*     */   
/*     */   protected static class FRVertexData extends Point2D.Double
/*     */   {
/*     */     protected void offset(double x, double y)
/*     */     {
/* 324 */       this.x += x;
/* 325 */       this.y += y;
/*     */     }
/*     */     
/*     */     protected double norm()
/*     */     {
/* 330 */       return Math.sqrt(this.x * this.x + this.y * this.y);
/*     */     }
/*     */   }
/*     */ }


/* Location:              /home/menick/Scrivania/ProcessMining/CNMining/CNMining.jar!/edu/uci/ics/jung/algorithms/layout/FRLayout.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       0.7.1
 */