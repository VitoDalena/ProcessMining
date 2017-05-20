/*     */ package edu.uci.ics.jung.visualization.transform.shape;
/*     */ 
/*     */ import java.awt.Rectangle;
/*     */ import java.awt.geom.Line2D;
/*     */ import java.awt.geom.Line2D.Float;
/*     */ import java.awt.geom.Point2D;
/*     */ import java.awt.geom.Point2D.Float;
/*     */ import java.io.PrintStream;
/*     */ import java.util.HashSet;
/*     */ import java.util.Set;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class Intersector
/*     */ {
/*     */   protected Rectangle rectangle;
/*     */   Line2D line;
/*  21 */   Set<Point2D> points = new HashSet();
/*     */   
/*     */ 
/*     */   public Intersector(Rectangle rectangle)
/*     */   {
/*  26 */     this.rectangle = rectangle;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */   public Intersector(Rectangle rectangle, Line2D line)
/*     */   {
/*  33 */     this.rectangle = rectangle;
/*  34 */     intersectLine(line);
/*     */   }
/*     */   
/*     */   public void intersectLine(Line2D line) {
/*  38 */     this.line = line;
/*  39 */     this.points.clear();
/*  40 */     float rx0 = (float)this.rectangle.getMinX();
/*  41 */     float ry0 = (float)this.rectangle.getMinY();
/*  42 */     float rx1 = (float)this.rectangle.getMaxX();
/*  43 */     float ry1 = (float)this.rectangle.getMaxY();
/*     */     
/*  45 */     float x1 = (float)line.getX1();
/*  46 */     float y1 = (float)line.getY1();
/*  47 */     float x2 = (float)line.getX2();
/*  48 */     float y2 = (float)line.getY2();
/*     */     
/*  50 */     float dy = y2 - y1;
/*  51 */     float dx = x2 - x1;
/*     */     
/*  53 */     if (dx != 0.0F) {
/*  54 */       float m = dy / dx;
/*  55 */       float b = y1 - m * x1;
/*     */       
/*     */ 
/*  58 */       float x = (ry0 - b) / m;
/*     */       
/*  60 */       if ((rx0 <= x) && (x <= rx1)) {
/*  61 */         this.points.add(new Point2D.Float(x, ry0));
/*     */       }
/*     */       
/*     */ 
/*  65 */       x = (ry1 - b) / m;
/*  66 */       if ((rx0 <= x) && (x <= rx1)) {
/*  67 */         this.points.add(new Point2D.Float(x, ry1));
/*     */       }
/*     */       
/*     */ 
/*  71 */       float y = m * rx0 + b;
/*  72 */       if ((ry0 <= y) && (y <= ry1)) {
/*  73 */         this.points.add(new Point2D.Float(rx0, y));
/*     */       }
/*     */       
/*     */ 
/*     */ 
/*  78 */       y = m * rx1 + b;
/*  79 */       if ((ry0 <= y) && (y <= ry1)) {
/*  80 */         this.points.add(new Point2D.Float(rx1, y));
/*     */       }
/*     */       
/*     */     }
/*     */     else
/*     */     {
/*  86 */       float x = x1;
/*  87 */       if ((rx0 <= x) && (x <= rx1)) {
/*  88 */         this.points.add(new Point2D.Float(x, ry0));
/*     */       }
/*     */       
/*     */ 
/*  92 */       x = x2;
/*  93 */       if ((rx0 <= x) && (x <= rx1))
/*  94 */         this.points.add(new Point2D.Float(x, ry1));
/*     */     }
/*     */   }
/*     */   
/*     */   public Line2D getLine() {
/*  99 */     return this.line;
/*     */   }
/*     */   
/* 102 */   public Set getPoints() { return this.points; }
/*     */   
/*     */   public Rectangle getRectangle() {
/* 105 */     return this.rectangle;
/*     */   }
/*     */   
/*     */   public String toString() {
/* 109 */     return "Rectangle: " + this.rectangle + ", points:" + this.points;
/*     */   }
/*     */   
/*     */   public static void main(String[] args) {
/* 113 */     Rectangle rectangle = new Rectangle(0, 0, 10, 10);
/* 114 */     Line2D line = new Line2D.Float(4.0F, 4.0F, 5.0F, 5.0F);
/* 115 */     System.err.println("" + new Intersector(rectangle, line));
/* 116 */     System.err.println("" + new Intersector(rectangle, new Line2D.Float(9.0F, 11.0F, 11.0F, 9.0F)));
/* 117 */     System.err.println("" + new Intersector(rectangle, new Line2D.Float(1.0F, 1.0F, 3.0F, 2.0F)));
/* 118 */     System.err.println("" + new Intersector(rectangle, new Line2D.Float(4.0F, 6.0F, 6.0F, 4.0F)));
/*     */   }
/*     */ }


/* Location:              /home/menick/Scrivania/ProcessMining/CNMining/CNMining.jar!/edu/uci/ics/jung/visualization/transform/shape/Intersector.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       0.7.1
 */