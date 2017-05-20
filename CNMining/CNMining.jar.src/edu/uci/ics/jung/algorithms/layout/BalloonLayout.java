/*     */ package edu.uci.ics.jung.algorithms.layout;
/*     */ 
/*     */ import edu.uci.ics.jung.graph.Forest;
/*     */ import edu.uci.ics.jung.graph.util.TreeUtils;
/*     */ import java.awt.Dimension;
/*     */ import java.awt.geom.Point2D;
/*     */ import java.awt.geom.Point2D.Double;
/*     */ import java.util.ArrayList;
/*     */ import java.util.HashMap;
/*     */ import java.util.List;
/*     */ import java.util.Map;
/*     */ import org.apache.commons.collections15.Transformer;
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
/*     */ public class BalloonLayout<V, E>
/*     */   extends TreeLayout<V, E>
/*     */ {
/*  36 */   protected Map<V, PolarPoint> polarLocations = LazyMap.decorate(new HashMap(), new Transformer()
/*     */   {
/*     */     public PolarPoint transform(V arg0)
/*     */     {
/*  40 */       return new PolarPoint();
/*     */     }
/*  36 */   });
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*  43 */   protected Map<V, Double> radii = new HashMap();
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   public BalloonLayout(Forest<V, E> g)
/*     */   {
/*  50 */     super(g);
/*     */   }
/*     */   
/*     */   protected void setRootPolars()
/*     */   {
/*  55 */     List<V> roots = TreeUtils.getRoots(this.graph);
/*  56 */     if (roots.size() == 1)
/*     */     {
/*  58 */       V root = roots.get(0);
/*  59 */       setRootPolar(root);
/*  60 */       setPolars(new ArrayList(this.graph.getChildren(root)), getCenter(), getSize().width / 2);
/*     */     }
/*  62 */     else if (roots.size() > 1)
/*     */     {
/*  64 */       setPolars(roots, getCenter(), getSize().width / 2);
/*     */     }
/*     */   }
/*     */   
/*     */   protected void setRootPolar(V root) {
/*  69 */     PolarPoint pp = new PolarPoint(0.0D, 0.0D);
/*  70 */     Point2D p = getCenter();
/*  71 */     this.polarLocations.put(root, pp);
/*  72 */     this.locations.put(root, p);
/*     */   }
/*     */   
/*     */ 
/*     */   protected void setPolars(List<V> kids, Point2D parentLocation, double parentRadius)
/*     */   {
/*  78 */     int childCount = kids.size();
/*  79 */     if (childCount == 0) { return;
/*     */     }
/*  81 */     double angle = Math.max(0.0D, 1.5707963267948966D * (1.0D - 2.0D / childCount));
/*  82 */     double childRadius = parentRadius * Math.cos(angle) / (1.0D + Math.cos(angle));
/*  83 */     double radius = parentRadius - childRadius;
/*     */     
/*  85 */     double rand = Math.random();
/*     */     
/*  87 */     for (int i = 0; i < childCount; i++) {
/*  88 */       V child = kids.get(i);
/*  89 */       double theta = i * 2 * 3.141592653589793D / childCount + rand;
/*  90 */       this.radii.put(child, Double.valueOf(childRadius));
/*     */       
/*  92 */       PolarPoint pp = new PolarPoint(theta, radius);
/*  93 */       this.polarLocations.put(child, pp);
/*     */       
/*  95 */       Point2D p = PolarPoint.polarToCartesian(pp);
/*  96 */       p.setLocation(p.getX() + parentLocation.getX(), p.getY() + parentLocation.getY());
/*  97 */       this.locations.put(child, p);
/*  98 */       setPolars(new ArrayList(this.graph.getChildren(child)), p, childRadius);
/*     */     }
/*     */   }
/*     */   
/*     */   public void setSize(Dimension size)
/*     */   {
/* 104 */     this.size = size;
/* 105 */     setRootPolars();
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   public Point2D getCenter(V v)
/*     */   {
/* 113 */     V parent = this.graph.getParent(v);
/* 114 */     if (parent == null) {
/* 115 */       return getCenter();
/*     */     }
/* 117 */     return (Point2D)this.locations.get(parent);
/*     */   }
/*     */   
/*     */   public void setLocation(V v, Point2D location)
/*     */   {
/* 122 */     Point2D c = getCenter(v);
/* 123 */     Point2D pv = new Point2D.Double(location.getX() - c.getX(), location.getY() - c.getY());
/* 124 */     PolarPoint newLocation = PolarPoint.cartesianToPolar(pv);
/* 125 */     ((PolarPoint)this.polarLocations.get(v)).setLocation(newLocation);
/*     */     
/* 127 */     Point2D center = getCenter(v);
/* 128 */     pv.setLocation(pv.getX() + center.getX(), pv.getY() + center.getY());
/* 129 */     this.locations.put(v, pv);
/*     */   }
/*     */   
/*     */   public Point2D transform(V v)
/*     */   {
/* 134 */     return (Point2D)this.locations.get(v);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */   public Map<V, Double> getRadii()
/*     */   {
/* 141 */     return this.radii;
/*     */   }
/*     */ }


/* Location:              /home/menick/Scrivania/ProcessMining/CNMining/CNMining.jar!/edu/uci/ics/jung/algorithms/layout/BalloonLayout.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       0.7.1
 */