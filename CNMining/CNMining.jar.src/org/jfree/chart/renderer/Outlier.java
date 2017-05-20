/*     */ package org.jfree.chart.renderer;
/*     */ 
/*     */ import java.awt.geom.Point2D;
/*     */ import java.awt.geom.Point2D.Double;
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
/*     */ public class Outlier
/*     */   implements Comparable
/*     */ {
/*     */   private Point2D point;
/*     */   private double radius;
/*     */   
/*     */   public Outlier(double xCoord, double yCoord, double radius)
/*     */   {
/*  74 */     this.point = new Point2D.Double(xCoord - radius, yCoord - radius);
/*  75 */     this.radius = radius;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public Point2D getPoint()
/*     */   {
/*  85 */     return this.point;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public void setPoint(Point2D point)
/*     */   {
/*  95 */     this.point = point;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public double getX()
/*     */   {
/* 105 */     return getPoint().getX();
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public double getY()
/*     */   {
/* 115 */     return getPoint().getY();
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public double getRadius()
/*     */   {
/* 124 */     return this.radius;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public void setRadius(double radius)
/*     */   {
/* 133 */     this.radius = radius;
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
/*     */   public int compareTo(Object o)
/*     */   {
/* 146 */     Outlier outlier = (Outlier)o;
/* 147 */     Point2D p1 = getPoint();
/* 148 */     Point2D p2 = outlier.getPoint();
/* 149 */     if (p1.equals(p2)) {
/* 150 */       return 0;
/*     */     }
/* 152 */     if ((p1.getX() < p2.getX()) || (p1.getY() < p2.getY())) {
/* 153 */       return -1;
/*     */     }
/*     */     
/* 156 */     return 1;
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
/*     */   public boolean overlaps(Outlier other)
/*     */   {
/* 171 */     return (other.getX() >= getX() - this.radius * 1.1D) && (other.getX() <= getX() + this.radius * 1.1D) && (other.getY() >= getY() - this.radius * 1.1D) && (other.getY() <= getY() + this.radius * 1.1D);
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
/*     */   public boolean equals(Object obj)
/*     */   {
/* 185 */     if (obj == this) {
/* 186 */       return true;
/*     */     }
/* 188 */     if (!(obj instanceof Outlier)) {
/* 189 */       return false;
/*     */     }
/* 191 */     Outlier that = (Outlier)obj;
/* 192 */     if (!this.point.equals(that.point)) {
/* 193 */       return false;
/*     */     }
/* 195 */     if (this.radius != that.radius) {
/* 196 */       return false;
/*     */     }
/* 198 */     return true;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public String toString()
/*     */   {
/* 207 */     return "{" + getX() + "," + getY() + "}";
/*     */   }
/*     */ }


/* Location:              /home/menick/Scrivania/ProcessMining/CNMining/CNMining.jar!/org/jfree/chart/renderer/Outlier.class
 * Java compiler version: 3 (47.0)
 * JD-Core Version:       0.7.1
 */