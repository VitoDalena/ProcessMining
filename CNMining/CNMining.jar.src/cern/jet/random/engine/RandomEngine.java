/*     */ package cern.jet.random.engine;
/*     */ 
/*     */ import cern.colt.function.DoubleFunction;
/*     */ import cern.colt.function.IntFunction;
/*     */ import edu.cornell.lassp.houle.RngPack.RandomElement;
/*     */ import edu.cornell.lassp.houle.RngPack.RandomSeedable;
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
/*     */ public abstract class RandomEngine
/*     */   extends RandomSeedable
/*     */   implements DoubleFunction, IntFunction
/*     */ {
/*     */   public double apply(double paramDouble)
/*     */   {
/*  50 */     return raw();
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */   public int apply(int paramInt)
/*     */   {
/*  57 */     return nextInt();
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */   public static RandomElement makeDefault()
/*     */   {
/*  64 */     return new MersenneTwister((int)System.currentTimeMillis());
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */   public double nextDouble()
/*     */   {
/*     */     double d;
/*     */     
/*     */     do
/*     */     {
/*  75 */       d = (nextLong() - -9.223372036854776E18D) * 5.421010862427522E-20D;
/*     */ 
/*     */     }
/*  78 */     while ((d <= 0.0D) || (d >= 1.0D));
/*     */     
/*     */ 
/*  81 */     return d;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public float nextFloat()
/*     */   {
/*     */     float f;
/*     */     
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     do
/*     */     {
/* 104 */       f = (float)raw();
/* 105 */     } while (f >= 1.0F);
/*     */     
/*     */ 
/* 108 */     return f;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */   public abstract int nextInt();
/*     */   
/*     */ 
/*     */ 
/*     */   public long nextLong()
/*     */   {
/* 119 */     return (nextInt() & 0xFFFFFFFF) << 32 | nextInt() & 0xFFFFFFFF;
/*     */   }
/*     */   
/*     */ 
/*     */   public double raw()
/*     */   {
/*     */     int i;
/*     */     do
/*     */     {
/* 128 */       i = nextInt();
/* 129 */     } while (i == 0);
/*     */     
/*     */ 
/*     */ 
/* 133 */     return (i & 0xFFFFFFFF) * 2.3283064365386963E-10D;
/*     */   }
/*     */ }


/* Location:              /home/menick/Scrivania/ProcessMining/CNMining/CNMining.jar!/cern/jet/random/engine/RandomEngine.class
 * Java compiler version: 1 (45.3)
 * JD-Core Version:       0.7.1
 */