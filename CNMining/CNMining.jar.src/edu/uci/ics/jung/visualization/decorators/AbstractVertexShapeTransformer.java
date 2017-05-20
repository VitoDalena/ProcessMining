/*    */ package edu.uci.ics.jung.visualization.decorators;
/*    */ 
/*    */ import edu.uci.ics.jung.visualization.util.VertexShapeFactory;
/*    */ import org.apache.commons.collections15.Transformer;
/*    */ import org.apache.commons.collections15.functors.ConstantTransformer;
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
/*    */ public abstract class AbstractVertexShapeTransformer<V>
/*    */   implements SettableVertexShapeTransformer<V>
/*    */ {
/*    */   protected Transformer<V, Integer> vsf;
/*    */   protected Transformer<V, Float> varf;
/*    */   protected VertexShapeFactory<V> factory;
/*    */   public static final int DEFAULT_SIZE = 8;
/*    */   public static final float DEFAULT_ASPECT_RATIO = 1.0F;
/*    */   
/*    */   public AbstractVertexShapeTransformer(Transformer<V, Integer> vsf, Transformer<V, Float> varf)
/*    */   {
/* 35 */     this.vsf = vsf;
/* 36 */     this.varf = varf;
/* 37 */     this.factory = new VertexShapeFactory(vsf, varf);
/*    */   }
/*    */   
/*    */ 
/*    */   public AbstractVertexShapeTransformer()
/*    */   {
/* 43 */     this(new ConstantTransformer(Integer.valueOf(8)), new ConstantTransformer(Float.valueOf(1.0F)));
/*    */   }
/*    */   
/*    */ 
/*    */   public void setSizeTransformer(Transformer<V, Integer> vsf)
/*    */   {
/* 49 */     this.vsf = vsf;
/* 50 */     this.factory = new VertexShapeFactory(vsf, this.varf);
/*    */   }
/*    */   
/*    */   public void setAspectRatioTransformer(Transformer<V, Float> varf)
/*    */   {
/* 55 */     this.varf = varf;
/* 56 */     this.factory = new VertexShapeFactory(this.vsf, varf);
/*    */   }
/*    */ }


/* Location:              /home/menick/Scrivania/ProcessMining/CNMining/CNMining.jar!/edu/uci/ics/jung/visualization/decorators/AbstractVertexShapeTransformer.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       0.7.1
 */