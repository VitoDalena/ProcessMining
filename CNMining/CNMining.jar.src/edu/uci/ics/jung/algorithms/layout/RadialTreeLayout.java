/*     */ package edu.uci.ics.jung.algorithms.layout;
/*     */ 
/*     */ import edu.uci.ics.jung.graph.Forest;
/*     */ import java.awt.Dimension;
/*     */ import java.awt.geom.Point2D;
/*     */ import java.awt.geom.Point2D.Double;
/*     */ import java.util.HashMap;
/*     */ import java.util.Map;
/*     */ import java.util.Map.Entry;
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
/*     */ public class RadialTreeLayout<V, E>
/*     */   extends TreeLayout<V, E>
/*     */ {
/*     */   protected Map<V, PolarPoint> polarLocations;
/*     */   
/*     */   public RadialTreeLayout(Forest<V, E> g)
/*     */   {
/*  33 */     this(g, DEFAULT_DISTX, DEFAULT_DISTY);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   public RadialTreeLayout(Forest<V, E> g, int distx)
/*     */   {
/*  41 */     this(g, distx, DEFAULT_DISTY);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */   public RadialTreeLayout(Forest<V, E> g, int distx, int disty)
/*     */   {
/*  48 */     super(g, distx, disty);
/*     */   }
/*     */   
/*     */   protected void buildTree()
/*     */   {
/*  53 */     super.buildTree();
/*  54 */     this.polarLocations = new HashMap();
/*  55 */     setRadialLocations();
/*     */   }
/*     */   
/*     */   public void setSize(Dimension size)
/*     */   {
/*  60 */     this.size = size;
/*  61 */     buildTree();
/*     */   }
/*     */   
/*     */   protected void setCurrentPositionFor(V vertex)
/*     */   {
/*  66 */     ((Point2D)this.locations.get(vertex)).setLocation(this.m_currentPoint);
/*     */   }
/*     */   
/*     */ 
/*     */   public void setLocation(V v, Point2D location)
/*     */   {
/*  72 */     Point2D c = getCenter();
/*  73 */     Point2D pv = new Point2D.Double(location.getX() - c.getX(), location.getY() - c.getY());
/*     */     
/*  75 */     PolarPoint newLocation = PolarPoint.cartesianToPolar(pv);
/*  76 */     ((PolarPoint)this.polarLocations.get(v)).setLocation(newLocation);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */   public Map<V, PolarPoint> getPolarLocations()
/*     */   {
/*  83 */     return this.polarLocations;
/*     */   }
/*     */   
/*     */   public Point2D transform(V v)
/*     */   {
/*  88 */     PolarPoint pp = (PolarPoint)this.polarLocations.get(v);
/*  89 */     double centerX = getSize().getWidth() / 2.0D;
/*  90 */     double centerY = getSize().getHeight() / 2.0D;
/*  91 */     Point2D cartesian = PolarPoint.polarToCartesian(pp);
/*  92 */     cartesian.setLocation(cartesian.getX() + centerX, cartesian.getY() + centerY);
/*  93 */     return cartesian;
/*     */   }
/*     */   
/*     */   private Point2D getMaxXY() {
/*  97 */     double maxx = 0.0D;
/*  98 */     double maxy = 0.0D;
/*  99 */     for (Point2D p : this.locations.values()) {
/* 100 */       maxx = Math.max(maxx, p.getX());
/* 101 */       maxy = Math.max(maxy, p.getY());
/*     */     }
/* 103 */     return new Point2D.Double(maxx, maxy);
/*     */   }
/*     */   
/*     */   private void setRadialLocations() {
/* 107 */     Point2D max = getMaxXY();
/* 108 */     double maxx = max.getX();
/* 109 */     double maxy = max.getY();
/* 110 */     maxx = Math.max(maxx, this.size.width);
/* 111 */     double theta = 6.283185307179586D / maxx;
/*     */     
/* 113 */     double deltaRadius = this.size.width / 2 / maxy;
/* 114 */     for (Map.Entry<V, Point2D> entry : this.locations.entrySet()) {
/* 115 */       V v = entry.getKey();
/* 116 */       Point2D p = (Point2D)entry.getValue();
/* 117 */       PolarPoint polarPoint = new PolarPoint(p.getX() * theta, (p.getY() - 50.0D) * deltaRadius);
/* 118 */       this.polarLocations.put(v, polarPoint);
/*     */     }
/*     */   }
/*     */ }


/* Location:              /home/menick/Scrivania/ProcessMining/CNMining/CNMining.jar!/edu/uci/ics/jung/algorithms/layout/RadialTreeLayout.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       0.7.1
 */