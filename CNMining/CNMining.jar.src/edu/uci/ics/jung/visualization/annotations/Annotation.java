/*    */ package edu.uci.ics.jung.visualization.annotations;
/*    */ 
/*    */ import java.awt.Paint;
/*    */ import java.awt.geom.Point2D;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class Annotation<T>
/*    */ {
/*    */   protected T annotation;
/*    */   protected Paint paint;
/*    */   protected Point2D location;
/*    */   protected Layer layer;
/*    */   protected boolean fill;
/*    */   
/*    */   public static enum Layer
/*    */   {
/* 29 */     LOWER,  UPPER;
/*    */     
/*    */     private Layer() {}
/*    */   }
/*    */   
/*    */   public Annotation(T annotation, Layer layer, Paint paint, boolean fill, Point2D location) {
/* 35 */     this.annotation = annotation;
/* 36 */     this.layer = layer;
/* 37 */     this.paint = paint;
/* 38 */     this.fill = fill;
/* 39 */     this.location = location;
/*    */   }
/*    */   
/*    */ 
/*    */   public T getAnnotation()
/*    */   {
/* 45 */     return (T)this.annotation;
/*    */   }
/*    */   
/*    */ 
/*    */   public void setAnnotation(T annotation)
/*    */   {
/* 51 */     this.annotation = annotation;
/*    */   }
/*    */   
/*    */ 
/*    */   public Point2D getLocation()
/*    */   {
/* 57 */     return this.location;
/*    */   }
/*    */   
/*    */ 
/*    */   public Layer getLayer()
/*    */   {
/* 63 */     return this.layer;
/*    */   }
/*    */   
/*    */ 
/*    */   public void setLayer(Layer layer)
/*    */   {
/* 69 */     this.layer = layer;
/*    */   }
/*    */   
/*    */ 
/*    */   public void setLocation(Point2D location)
/*    */   {
/* 75 */     this.location = location;
/*    */   }
/*    */   
/*    */ 
/*    */   public Paint getPaint()
/*    */   {
/* 81 */     return this.paint;
/*    */   }
/*    */   
/*    */ 
/*    */   public void setPaint(Paint paint)
/*    */   {
/* 87 */     this.paint = paint;
/*    */   }
/*    */   
/*    */ 
/*    */   public boolean isFill()
/*    */   {
/* 93 */     return this.fill;
/*    */   }
/*    */   
/*    */ 
/*    */   public void setFill(boolean fill)
/*    */   {
/* 99 */     this.fill = fill;
/*    */   }
/*    */ }


/* Location:              /home/menick/Scrivania/ProcessMining/CNMining/CNMining.jar!/edu/uci/ics/jung/visualization/annotations/Annotation.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       0.7.1
 */