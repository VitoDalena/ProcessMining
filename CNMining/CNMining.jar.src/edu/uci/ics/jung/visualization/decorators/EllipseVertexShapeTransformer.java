/*    */ package edu.uci.ics.jung.visualization.decorators;
/*    */ 
/*    */ import edu.uci.ics.jung.visualization.util.VertexShapeFactory;
/*    */ import java.awt.Shape;
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
/*    */ public class EllipseVertexShapeTransformer<V>
/*    */   extends AbstractVertexShapeTransformer<V>
/*    */   implements Transformer<V, Shape>
/*    */ {
/*    */   public EllipseVertexShapeTransformer() {}
/*    */   
/*    */   public EllipseVertexShapeTransformer(Transformer<V, Integer> vsf, Transformer<V, Float> varf)
/*    */   {
/* 30 */     super(vsf, varf);
/*    */   }
/*    */   
/*    */   public Shape transform(V v)
/*    */   {
/* 35 */     return this.factory.getEllipse(v);
/*    */   }
/*    */ }


/* Location:              /home/menick/Scrivania/ProcessMining/CNMining/CNMining.jar!/edu/uci/ics/jung/visualization/decorators/EllipseVertexShapeTransformer.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       0.7.1
 */