/*    */ package cern.jet.random.engine;
/*    */ 
/*    */ import java.util.Date;
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
/*    */ public class MersenneTwister64
/*    */   extends MersenneTwister
/*    */ {
/*    */   public MersenneTwister64() {}
/*    */   
/*    */   public MersenneTwister64(int paramInt)
/*    */   {
/* 31 */     super(paramInt);
/*    */   }
/*    */   
/*    */ 
/*    */ 
/*    */ 
/*    */   public MersenneTwister64(Date paramDate)
/*    */   {
/* 39 */     super(paramDate);
/*    */   }
/*    */   
/*    */ 
/*    */   public double raw()
/*    */   {
/* 45 */     return nextDouble();
/*    */   }
/*    */ }


/* Location:              /home/menick/Scrivania/ProcessMining/CNMining/CNMining.jar!/cern/jet/random/engine/MersenneTwister64.class
 * Java compiler version: 1 (45.3)
 * JD-Core Version:       0.7.1
 */