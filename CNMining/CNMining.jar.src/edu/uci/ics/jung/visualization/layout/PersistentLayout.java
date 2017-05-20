/*    */ package edu.uci.ics.jung.visualization.layout;
/*    */ 
/*    */ import edu.uci.ics.jung.algorithms.layout.Layout;
/*    */ import java.awt.geom.Point2D;
/*    */ import java.io.IOException;
/*    */ import java.io.Serializable;
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
/*    */ 
/*    */ 
/*    */ public abstract interface PersistentLayout<V, E>
/*    */   extends Layout<V, E>
/*    */ {
/*    */   public abstract void persist(String paramString)
/*    */     throws IOException;
/*    */   
/*    */   public abstract void restore(String paramString)
/*    */     throws IOException, ClassNotFoundException;
/*    */   
/*    */   public abstract void lock(boolean paramBoolean);
/*    */   
/*    */   public static class Point
/*    */     implements Serializable
/*    */   {
/*    */     public double x;
/*    */     public double y;
/*    */     
/*    */     public Point(double x, double y)
/*    */     {
/* 42 */       this.x = x;
/* 43 */       this.y = y;
/*    */     }
/*    */     
/* 46 */     public Point(Point2D p) { this.x = p.getX();
/* 47 */       this.y = p.getY();
/*    */     }
/*    */   }
/*    */ }


/* Location:              /home/menick/Scrivania/ProcessMining/CNMining/CNMining.jar!/edu/uci/ics/jung/visualization/layout/PersistentLayout.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       0.7.1
 */