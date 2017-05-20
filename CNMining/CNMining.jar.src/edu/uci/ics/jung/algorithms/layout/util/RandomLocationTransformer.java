/*    */ package edu.uci.ics.jung.algorithms.layout.util;
/*    */ 
/*    */ import java.awt.Dimension;
/*    */ import java.awt.geom.Point2D;
/*    */ import java.awt.geom.Point2D.Double;
/*    */ import java.util.Date;
/*    */ import java.util.Random;
/*    */ import org.apache.commons.collections15.Transformer;
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
/*    */ public class RandomLocationTransformer<V>
/*    */   implements Transformer<V, Point2D>
/*    */ {
/*    */   Dimension d;
/*    */   Random random;
/*    */   
/*    */   public RandomLocationTransformer(Dimension d)
/*    */   {
/* 43 */     this(d, new Date().getTime());
/*    */   }
/*    */   
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   public RandomLocationTransformer(Dimension d, long seed)
/*    */   {
/* 52 */     this.d = d;
/* 53 */     this.random = new Random(seed);
/*    */   }
/*    */   
/*    */   public Point2D transform(V v) {
/* 57 */     return new Point2D.Double(this.random.nextDouble() * this.d.width, this.random.nextDouble() * this.d.height);
/*    */   }
/*    */ }


/* Location:              /home/menick/Scrivania/ProcessMining/CNMining/CNMining.jar!/edu/uci/ics/jung/algorithms/layout/util/RandomLocationTransformer.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       0.7.1
 */