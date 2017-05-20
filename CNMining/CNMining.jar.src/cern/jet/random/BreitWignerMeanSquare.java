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
/*    */ public class BreitWignerMeanSquare
/*    */   extends BreitWigner
/*    */ {
/*    */   protected Uniform uniform;
/* 28 */   protected static BreitWigner shared = new BreitWignerMeanSquare(1.0D, 0.2D, 1.0D, AbstractDistribution.makeDefaultGenerator());
/*    */   
/*    */ 
/*    */ 
/*    */   public BreitWignerMeanSquare(double paramDouble1, double paramDouble2, double paramDouble3, RandomElement paramRandomElement)
/*    */   {
/* 34 */     super(paramDouble1, paramDouble2, paramDouble3, paramRandomElement);
/* 35 */     this.uniform = new Uniform(paramRandomElement);
/*    */   }
/*    */   
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   public Object clone()
/*    */   {
/* 44 */     BreitWignerMeanSquare localBreitWignerMeanSquare = (BreitWignerMeanSquare)super.clone();
/* 45 */     if (this.uniform != null) localBreitWignerMeanSquare.uniform = new Uniform(localBreitWignerMeanSquare.randomGenerator);
/* 46 */     return localBreitWignerMeanSquare;
/*    */   }
/*    */   
/*    */ 
/*    */ 
/*    */   public double nextDouble(double paramDouble1, double paramDouble2, double paramDouble3)
/*    */   {
/* 53 */     if (paramDouble2 == 0.0D) return paramDouble1;
/* 54 */     if (paramDouble3 == Double.NEGATIVE_INFINITY) {
/* 55 */       d1 = Math.atan(-paramDouble1 / paramDouble2);
/* 56 */       d2 = this.uniform.nextDoubleFromTo(d1, 1.5707963267948966D);
/* 57 */       d3 = paramDouble2 * Math.tan(d2);
/* 58 */       return Math.sqrt(paramDouble1 * paramDouble1 + paramDouble1 * d3);
/*    */     }
/*    */     
/* 61 */     double d1 = Math.max(0.0D, paramDouble1 - paramDouble3);
/* 62 */     double d2 = Math.atan((d1 * d1 - paramDouble1 * paramDouble1) / (paramDouble1 * paramDouble2));
/* 63 */     double d3 = Math.atan(((paramDouble1 + paramDouble3) * (paramDouble1 + paramDouble3) - paramDouble1 * paramDouble1) / (paramDouble1 * paramDouble2));
/* 64 */     double d4 = this.uniform.nextDoubleFromTo(d2, d3);
/*    */     
/* 66 */     double d5 = paramDouble2 * Math.tan(d4);
/* 67 */     return Math.sqrt(Math.max(0.0D, paramDouble1 * paramDouble1 + paramDouble1 * d5));
/*    */   }
/*    */   
/*    */ 
/*    */ 
/*    */ 
/*    */   public static double staticNextDouble(double paramDouble1, double paramDouble2, double paramDouble3)
/*    */   {
/* 75 */     synchronized (shared) {
/* 76 */       double d = shared.nextDouble(paramDouble1, paramDouble2, paramDouble3);return d;
/*    */     }
/*    */   }
/*    */   
/*    */ 
/*    */ 
/*    */   private static void xstaticSetRandomGenerator(RandomElement paramRandomElement)
/*    */   {
/* 84 */     synchronized (shared) {
/* 85 */       shared.setRandomGenerator(paramRandomElement);
/*    */     }
/*    */   }
/*    */ }


/* Location:              /home/menick/Scrivania/ProcessMining/CNMining/CNMining.jar!/cern/jet/random/BreitWignerMeanSquare.class
 * Java compiler version: 1 (45.3)
 * JD-Core Version:       0.7.1
 */