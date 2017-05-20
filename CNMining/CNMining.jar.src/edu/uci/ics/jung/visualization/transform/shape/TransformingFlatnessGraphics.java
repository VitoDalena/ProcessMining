/*    */ package edu.uci.ics.jung.visualization.transform.shape;
/*    */ 
/*    */ import edu.uci.ics.jung.visualization.transform.BidirectionalTransformer;
/*    */ import edu.uci.ics.jung.visualization.transform.HyperbolicTransformer;
/*    */ import java.awt.Graphics2D;
/*    */ import java.awt.Shape;
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
/*    */ public class TransformingFlatnessGraphics
/*    */   extends TransformingGraphics
/*    */ {
/* 32 */   float flatness = 0.0F;
/*    */   
/*    */   public TransformingFlatnessGraphics(BidirectionalTransformer transformer) {
/* 35 */     this(transformer, null);
/*    */   }
/*    */   
/*    */   public TransformingFlatnessGraphics(BidirectionalTransformer transformer, Graphics2D delegate) {
/* 39 */     super(transformer, delegate);
/*    */   }
/*    */   
/*    */   public void draw(Shape s, float flatness) {
/* 43 */     Shape shape = null;
/* 44 */     if ((this.transformer instanceof ShapeFlatnessTransformer)) {
/* 45 */       shape = ((ShapeFlatnessTransformer)this.transformer).transform(s, flatness);
/*    */     } else {
/* 47 */       shape = ((ShapeTransformer)this.transformer).transform(s);
/*    */     }
/* 49 */     this.delegate.draw(shape);
/*    */   }
/*    */   
/*    */   public void fill(Shape s, float flatness)
/*    */   {
/* 54 */     Shape shape = null;
/* 55 */     if ((this.transformer instanceof HyperbolicTransformer)) {
/* 56 */       shape = ((HyperbolicShapeTransformer)this.transformer).transform(s, flatness);
/*    */     } else {
/* 58 */       shape = ((ShapeTransformer)this.transformer).transform(s);
/*    */     }
/* 60 */     this.delegate.fill(shape);
/*    */   }
/*    */ }


/* Location:              /home/menick/Scrivania/ProcessMining/CNMining/CNMining.jar!/edu/uci/ics/jung/visualization/transform/shape/TransformingFlatnessGraphics.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       0.7.1
 */