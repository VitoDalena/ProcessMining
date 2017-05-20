/*    */ package cern.jet.random;
/*    */ 
/*    */ import edu.cornell.lassp.houle.RngPack.RandomElement;
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
/*    */ public class Exponential
/*    */   extends AbstractContinousDistribution
/*    */ {
/*    */   protected double lambda;
/* 30 */   protected static Exponential shared = new Exponential(1.0D, AbstractDistribution.makeDefaultGenerator());
/*    */   
/*    */ 
/*    */   public Exponential(double paramDouble, RandomElement paramRandomElement)
/*    */   {
/* 35 */     setRandomGenerator(paramRandomElement);
/* 36 */     setState(paramDouble);
/*    */   }
/*    */   
/*    */ 
/*    */   public double cdf(double paramDouble)
/*    */   {
/* 42 */     if (paramDouble <= 0.0D) return 0.0D;
/* 43 */     return 1.0D - Math.exp(-paramDouble * this.lambda);
/*    */   }
/*    */   
/*    */ 
/*    */   public double nextDouble()
/*    */   {
/* 49 */     return nextDouble(this.lambda);
/*    */   }
/*    */   
/*    */ 
/*    */   public double nextDouble(double paramDouble)
/*    */   {
/* 55 */     return -Math.log(this.randomGenerator.raw()) / paramDouble;
/*    */   }
/*    */   
/*    */ 
/*    */   public double pdf(double paramDouble)
/*    */   {
/* 61 */     if (paramDouble < 0.0D) return 0.0D;
/* 62 */     return this.lambda * Math.exp(-paramDouble * this.lambda);
/*    */   }
/*    */   
/*    */ 
/*    */   public void setState(double paramDouble)
/*    */   {
/* 68 */     this.lambda = paramDouble;
/*    */   }
/*    */   
/*    */ 
/*    */   public static double staticNextDouble(double paramDouble)
/*    */   {
/* 74 */     synchronized (shared) {
/* 75 */       double d = shared.nextDouble(paramDouble);return d;
/*    */     }
/*    */   }
/*    */   
/*    */ 
/*    */   public String toString()
/*    */   {
/* 82 */     return getClass().getName() + "(" + this.lambda + ")";
/*    */   }
/*    */   
/*    */ 
/*    */ 
/*    */   private static void xstaticSetRandomGenerator(RandomElement paramRandomElement)
/*    */   {
/* 89 */     synchronized (shared) {
/* 90 */       shared.setRandomGenerator(paramRandomElement);
/*    */     }
/*    */   }
/*    */ }


/* Location:              /home/menick/Scrivania/ProcessMining/CNMining/CNMining.jar!/cern/jet/random/Exponential.class
 * Java compiler version: 1 (45.3)
 * JD-Core Version:       0.7.1
 */