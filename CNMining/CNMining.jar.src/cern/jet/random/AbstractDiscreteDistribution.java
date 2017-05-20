/*    */ package cern.jet.random;
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
/*    */ public abstract class AbstractDiscreteDistribution
/*    */   extends AbstractDistribution
/*    */ {
/*    */   public double nextDouble()
/*    */   {
/* 26 */     return nextInt();
/*    */   }
/*    */   
/*    */   public abstract int nextInt();
/*    */ }


/* Location:              /home/menick/Scrivania/ProcessMining/CNMining/CNMining.jar!/cern/jet/random/AbstractDiscreteDistribution.class
 * Java compiler version: 1 (45.3)
 * JD-Core Version:       0.7.1
 */