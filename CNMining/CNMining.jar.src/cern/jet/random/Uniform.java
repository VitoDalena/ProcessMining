/*     */ package cern.jet.random;
/*     */ 
/*     */ import cern.jet.random.engine.MersenneTwister;
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
/*     */ public class Uniform
/*     */   extends AbstractContinousDistribution
/*     */ {
/*     */   protected double min;
/*     */   protected double max;
/*  28 */   protected static Uniform shared = new Uniform(AbstractDistribution.makeDefaultGenerator());
/*     */   
/*     */ 
/*     */   public Uniform(double paramDouble1, double paramDouble2, int paramInt)
/*     */   {
/*  33 */     this(paramDouble1, paramDouble2, new MersenneTwister(paramInt));
/*     */   }
/*     */   
/*     */ 
/*     */   public Uniform(double paramDouble1, double paramDouble2, RandomElement paramRandomElement)
/*     */   {
/*  39 */     setRandomGenerator(paramRandomElement);
/*  40 */     setState(paramDouble1, paramDouble2);
/*     */   }
/*     */   
/*     */ 
/*     */   public Uniform(RandomElement paramRandomElement)
/*     */   {
/*  46 */     this(0.0D, 1.0D, paramRandomElement);
/*     */   }
/*     */   
/*     */ 
/*     */   public double cdf(double paramDouble)
/*     */   {
/*  52 */     if (paramDouble <= this.min) return 0.0D;
/*  53 */     if (paramDouble >= this.max) return 1.0D;
/*  54 */     return (paramDouble - this.min) / (this.max - this.min);
/*     */   }
/*     */   
/*     */ 
/*     */   public boolean nextBoolean()
/*     */   {
/*  60 */     return this.randomGenerator.raw() > 0.5D;
/*     */   }
/*     */   
/*     */ 
/*     */   public double nextDouble()
/*     */   {
/*  66 */     return this.min + (this.max - this.min) * this.randomGenerator.raw();
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */   public double nextDoubleFromTo(double paramDouble1, double paramDouble2)
/*     */   {
/*  73 */     return paramDouble1 + (paramDouble2 - paramDouble1) * this.randomGenerator.raw();
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */   public float nextFloatFromTo(float paramFloat1, float paramFloat2)
/*     */   {
/*  80 */     return (float)nextDoubleFromTo(paramFloat1, paramFloat2);
/*     */   }
/*     */   
/*     */ 
/*     */   public int nextInt()
/*     */   {
/*  86 */     return nextIntFromTo((int)Math.round(this.min), (int)Math.round(this.max));
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */   public int nextIntFromTo(int paramInt1, int paramInt2)
/*     */   {
/*  93 */     return (int)(paramInt1 + ((1L + paramInt2 - paramInt1) * this.randomGenerator.raw()));
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
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public long nextLongFromTo(long paramLong1, long paramLong2)
/*     */   {
/* 110 */     if ((paramLong1 >= 0L) && (paramLong2 < Long.MAX_VALUE)) {
/* 111 */       return paramLong1 + nextDoubleFromTo(0.0D, paramLong2 - paramLong1 + 1L);
/*     */     }
/*     */     
/*     */ 
/*     */ 
/* 116 */     double d = paramLong2 - paramLong1 + 1.0D;
/* 117 */     if (d <= 9.223372036854776E18D) {
/* 118 */       return paramLong1 + nextDoubleFromTo(0.0D, d);
/*     */     }
/*     */     
/*     */ 
/*     */     long l;
/*     */     
/* 124 */     if (paramLong1 == Long.MIN_VALUE) {
/* 125 */       if (paramLong2 == Long.MAX_VALUE)
/*     */       {
/* 127 */         int i = nextIntFromTo(Integer.MIN_VALUE, Integer.MAX_VALUE);
/* 128 */         int j = nextIntFromTo(Integer.MIN_VALUE, Integer.MAX_VALUE);
/* 129 */         return (i & 0xFFFFFFFF) << 32 | j & 0xFFFFFFFF;
/*     */       }
/* 131 */       l = Math.round(nextDoubleFromTo(paramLong1, paramLong2 + 1L));
/* 132 */       if (l > paramLong2) l = paramLong1;
/*     */     }
/*     */     else {
/* 135 */       l = Math.round(nextDoubleFromTo(paramLong1 - 1L, paramLong2));
/* 136 */       if (l < paramLong1) l = paramLong2;
/*     */     }
/* 138 */     return l;
/*     */   }
/*     */   
/*     */ 
/*     */   public double pdf(double paramDouble)
/*     */   {
/* 144 */     if ((paramDouble <= this.min) || (paramDouble >= this.max)) return 0.0D;
/* 145 */     return 1.0D / (this.max - this.min);
/*     */   }
/*     */   
/*     */ 
/*     */   public void setState(double paramDouble1, double paramDouble2)
/*     */   {
/* 151 */     if (paramDouble2 < paramDouble1) { setState(paramDouble2, paramDouble1);return; }
/* 152 */     this.min = paramDouble1;
/* 153 */     this.max = paramDouble2;
/*     */   }
/*     */   
/*     */ 
/*     */   public static boolean staticNextBoolean()
/*     */   {
/* 159 */     synchronized (shared) {
/* 160 */       boolean bool = shared.nextBoolean();return bool;
/*     */     }
/*     */   }
/*     */   
/*     */ 
/*     */   public static double staticNextDouble()
/*     */   {
/* 167 */     synchronized (shared) {
/* 168 */       double d = shared.nextDouble();return d;
/*     */     }
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */   public static double staticNextDoubleFromTo(double paramDouble1, double paramDouble2)
/*     */   {
/* 176 */     synchronized (shared) {
/* 177 */       double d = shared.nextDoubleFromTo(paramDouble1, paramDouble2);return d;
/*     */     }
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */   public static float staticNextFloatFromTo(float paramFloat1, float paramFloat2)
/*     */   {
/* 185 */     synchronized (shared) {
/* 186 */       float f = shared.nextFloatFromTo(paramFloat1, paramFloat2);return f;
/*     */     }
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */   public static int staticNextIntFromTo(int paramInt1, int paramInt2)
/*     */   {
/* 194 */     synchronized (shared) {
/* 195 */       int i = shared.nextIntFromTo(paramInt1, paramInt2);return i;
/*     */     }
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */   public static long staticNextLongFromTo(long paramLong1, long paramLong2)
/*     */   {
/* 203 */     synchronized (shared) {
/* 204 */       long l = shared.nextLongFromTo(paramLong1, paramLong2);return l;
/*     */     }
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */   public static void staticSetRandomEngine(RandomElement paramRandomElement)
/*     */   {
/* 212 */     synchronized (shared) {
/* 213 */       shared.setRandomGenerator(paramRandomElement);
/*     */     }
/*     */   }
/*     */   
/*     */ 
/*     */   public String toString()
/*     */   {
/* 220 */     return getClass().getName() + "(" + this.min + "," + this.max + ")";
/*     */   }
/*     */ }


/* Location:              /home/menick/Scrivania/ProcessMining/CNMining/CNMining.jar!/cern/jet/random/Uniform.class
 * Java compiler version: 1 (45.3)
 * JD-Core Version:       0.7.1
 */