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
/*    */ public class BreitWigner
/*    */   extends AbstractContinousDistribution
/*    */ {
/*    */   protected double mean;
/*    */   protected double gamma;
/*    */   protected double cut;
/* 31 */   protected static BreitWigner shared = new BreitWigner(1.0D, 0.2D, 1.0D, AbstractDistribution.makeDefaultGenerator());
/*    */   
/*    */ 
/*    */ 
/*    */   public BreitWigner(double paramDouble1, double paramDouble2, double paramDouble3, RandomElement paramRandomElement)
/*    */   {
/* 37 */     setRandomGenerator(paramRandomElement);
/* 38 */     setState(paramDouble1, paramDouble2, paramDouble3);
/*    */   }
/*    */   
/*    */ 
/*    */   public double nextDouble()
/*    */   {
/* 44 */     return nextDouble(this.mean, this.gamma, this.cut);
/*    */   }
/*    */   
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   public double nextDouble(double paramDouble1, double paramDouble2, double paramDouble3)
/*    */   {
/* 53 */     if (paramDouble2 == 0.0D) return paramDouble1;
/* 54 */     if (paramDouble3 == Double.NEGATIVE_INFINITY) {
/* 55 */       d2 = 2.0D * this.randomGenerator.raw() - 1.0D;
/* 56 */       d3 = 0.5D * paramDouble2 * Math.tan(d2 * 1.5707963267948966D);
/* 57 */       return paramDouble1 + d3;
/*    */     }
/*    */     
/* 60 */     double d1 = Math.atan(2.0D * paramDouble3 / paramDouble2);
/* 61 */     double d2 = 2.0D * this.randomGenerator.raw() - 1.0D;
/* 62 */     double d3 = 0.5D * paramDouble2 * Math.tan(d2 * d1);
/*    */     
/* 64 */     return paramDouble1 + d3;
/*    */   }
/*    */   
/*    */ 
/*    */ 
/*    */ 
/*    */   public void setState(double paramDouble1, double paramDouble2, double paramDouble3)
/*    */   {
/* 72 */     this.mean = paramDouble1;
/* 73 */     this.gamma = paramDouble2;
/* 74 */     this.cut = paramDouble3;
/*    */   }
/*    */   
/*    */ 
/*    */ 
/*    */   public static double staticNextDouble(double paramDouble1, double paramDouble2, double paramDouble3)
/*    */   {
/* 81 */     synchronized (shared) {
/* 82 */       double d = shared.nextDouble(paramDouble1, paramDouble2, paramDouble3);return d;
/*    */     }
/*    */   }
/*    */   
/*    */ 
/*    */   public String toString()
/*    */   {
/* 89 */     return getClass().getName() + "(" + this.mean + "," + this.gamma + "," + this.cut + ")";
/*    */   }
/*    */   
/*    */ 
/*    */ 
/*    */   private static void xstaticSetRandomGenerator(RandomElement paramRandomElement)
/*    */   {
/* 96 */     synchronized (shared) {
/* 97 */       shared.setRandomGenerator(paramRandomElement);
/*    */     }
/*    */   }
/*    */ }


/* Location:              /home/menick/Scrivania/ProcessMining/CNMining/CNMining.jar!/cern/jet/random/BreitWigner.class
 * Java compiler version: 1 (45.3)
 * JD-Core Version:       0.7.1
 */