/*     */ package edu.uci.ics.jung.algorithms.layout;
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
/*     */ public class PolarPoint
/*     */ {
/*     */   double theta;
/*     */   double radius;
/*     */   
/*     */   public PolarPoint()
/*     */   {
/*  30 */     this(0.0D, 0.0D);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */   public PolarPoint(double theta, double radius)
/*     */   {
/*  37 */     this.theta = theta;
/*  38 */     this.radius = radius;
/*     */   }
/*     */   
/*     */ 
/*     */   public double getTheta()
/*     */   {
/*  44 */     return this.theta;
/*     */   }
/*     */   
/*     */   public double getRadius()
/*     */   {
/*  49 */     return this.radius;
/*     */   }
/*     */   
/*     */   public void setTheta(double theta)
/*     */   {
/*  54 */     this.theta = theta;
/*     */   }
/*     */   
/*     */   public void setRadius(double radius)
/*     */   {
/*  59 */     this.radius = radius;
/*     */   }
/*     */   
/*     */ 
/*     */   public static Point2D polarToCartesian(PolarPoint polar)
/*     */   {
/*  65 */     return polarToCartesian(polar.getTheta(), polar.getRadius());
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */   public static Point2D polarToCartesian(double theta, double radius)
/*     */   {
/*  72 */     return new Point2D.Double(radius * Math.cos(theta), radius * Math.sin(theta));
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */   public static PolarPoint cartesianToPolar(Point2D point)
/*     */   {
/*  79 */     return cartesianToPolar(point.getX(), point.getY());
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */   public static PolarPoint cartesianToPolar(double x, double y)
/*     */   {
/*  86 */     double theta = Math.atan2(y, x);
/*  87 */     double radius = Math.sqrt(x * x + y * y);
/*  88 */     return new PolarPoint(theta, radius);
/*     */   }
/*     */   
/*     */   public String toString()
/*     */   {
/*  93 */     return "PolarPoint[" + this.radius + "," + this.theta + "]";
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */   public void setLocation(PolarPoint p)
/*     */   {
/* 100 */     this.theta = p.getTheta();
/* 101 */     this.radius = p.getRadius();
/*     */   }
/*     */ }


/* Location:              /home/menick/Scrivania/ProcessMining/CNMining/CNMining.jar!/edu/uci/ics/jung/algorithms/layout/PolarPoint.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       0.7.1
 */