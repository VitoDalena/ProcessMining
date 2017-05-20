/*     */ package cern.jet.random;
/*     */ 
/*     */ import cern.colt.PersistentObject;
/*     */ import cern.colt.function.DoubleFunction;
/*     */ import cern.colt.function.IntFunction;
/*     */ import cern.jet.random.engine.RandomEngine;
/*     */ import edu.cornell.lassp.houle.RngPack.RandomElement;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public abstract class AbstractDistribution
/*     */   extends PersistentObject
/*     */   implements DoubleFunction, IntFunction
/*     */ {
/*     */   protected RandomElement randomGenerator;
/*     */   
/*     */   public double apply(double paramDouble)
/*     */   {
/*  62 */     return nextDouble();
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */   public int apply(int paramInt)
/*     */   {
/*  69 */     return nextInt();
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public Object clone()
/*     */   {
/*  78 */     AbstractDistribution localAbstractDistribution = (AbstractDistribution)super.clone();
/*  79 */     if (this.randomGenerator != null) localAbstractDistribution.randomGenerator = ((RandomElement)this.randomGenerator.clone());
/*  80 */     return localAbstractDistribution;
/*     */   }
/*     */   
/*     */ 
/*     */   protected RandomElement getRandomGenerator()
/*     */   {
/*  86 */     return this.randomGenerator;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */   public static RandomElement makeDefaultGenerator()
/*     */   {
/*  93 */     return RandomEngine.makeDefault();
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */   public abstract double nextDouble();
/*     */   
/*     */ 
/*     */ 
/*     */   public int nextInt()
/*     */   {
/* 104 */     return (int)Math.round(nextDouble());
/*     */   }
/*     */   
/*     */ 
/*     */   protected void setRandomGenerator(RandomElement paramRandomElement)
/*     */   {
/* 110 */     this.randomGenerator = paramRandomElement;
/*     */   }
/*     */ }


/* Location:              /home/menick/Scrivania/ProcessMining/CNMining/CNMining.jar!/cern/jet/random/AbstractDistribution.class
 * Java compiler version: 1 (45.3)
 * JD-Core Version:       0.7.1
 */