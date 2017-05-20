/*     */ package cern.jet.random;
/*     */ 
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
/*     */ public class Empirical
/*     */   extends AbstractContinousDistribution
/*     */ {
/*     */   protected double[] cdf;
/*     */   protected int interpolationType;
/*     */   public static final int LINEAR_INTERPOLATION = 0;
/*     */   public static final int NO_INTERPOLATION = 1;
/*     */   
/*     */   public Empirical(double[] paramArrayOfDouble, int paramInt, RandomElement paramRandomElement)
/*     */   {
/*  57 */     setRandomGenerator(paramRandomElement);
/*  58 */     setState(paramArrayOfDouble, paramInt);
/*     */   }
/*     */   
/*     */ 
/*     */   public double cdf(int paramInt)
/*     */   {
/*  64 */     if (paramInt < 0) return 0.0D;
/*  65 */     if (paramInt >= this.cdf.length - 1) return 1.0D;
/*  66 */     return this.cdf[paramInt];
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public Object clone()
/*     */   {
/*  75 */     Empirical localEmpirical = (Empirical)super.clone();
/*  76 */     if (this.cdf != null) localEmpirical.cdf = ((double[])this.cdf.clone());
/*  77 */     return localEmpirical;
/*     */   }
/*     */   
/*     */ 
/*     */   public double nextDouble()
/*     */   {
/*  83 */     double d1 = this.randomGenerator.raw();
/*  84 */     if (this.cdf == null) { return d1;
/*     */     }
/*     */     
/*  87 */     int i = this.cdf.length - 1;
/*  88 */     int j = 0;
/*  89 */     int k = i;
/*     */     
/*  91 */     while (k > j + 1) {
/*  92 */       int m = k + j + 1 >> 1;
/*  93 */       if (d1 >= this.cdf[m]) j = m; else {
/*  94 */         k = m;
/*     */       }
/*     */     }
/*     */     
/*  98 */     if (this.interpolationType == 1) {
/*  99 */       return j / i;
/*     */     }
/* 101 */     if (this.interpolationType == 0) {
/* 102 */       double d2 = this.cdf[k] - this.cdf[j];
/*     */       
/*     */ 
/*     */ 
/* 106 */       if (d2 == 0.0D)
/*     */       {
/*     */ 
/*     */ 
/* 110 */         return (j + 0.5D) / i;
/*     */       }
/*     */       
/* 113 */       double d3 = (d1 - this.cdf[j]) / d2;
/* 114 */       return (j + d3) / i;
/*     */     }
/* 116 */     throw new InternalError();
/*     */   }
/*     */   
/*     */ 
/*     */   public double pdf(double paramDouble)
/*     */   {
/* 122 */     throw new RuntimeException("not implemented");
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public double pdf(int paramInt)
/*     */   {
/* 131 */     if ((paramInt < 0) || (paramInt >= this.cdf.length - 1)) return 0.0D;
/* 132 */     return this.cdf[(paramInt - 1)] - this.cdf[paramInt];
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
/*     */   public void setState(double[] paramArrayOfDouble, int paramInt)
/*     */   {
/* 146 */     if ((paramInt != 0) && (paramInt != 1))
/*     */     {
/* 148 */       throw new IllegalArgumentException("Illegal Interpolation Type");
/*     */     }
/* 150 */     this.interpolationType = paramInt;
/*     */     
/* 152 */     if ((paramArrayOfDouble == null) || (paramArrayOfDouble.length == 0)) {
/* 153 */       this.cdf = null;
/*     */       
/* 155 */       return;
/*     */     }
/*     */     
/*     */ 
/* 159 */     int i = paramArrayOfDouble.length;
/* 160 */     this.cdf = new double[i + 1];
/*     */     
/* 162 */     this.cdf[0] = 0.0D;
/* 163 */     for (int j = 0; j < i; j++) {
/* 164 */       double d = paramArrayOfDouble[j];
/* 165 */       if (d < 0.0D) throw new IllegalArgumentException("Negative probability");
/* 166 */       this.cdf[(j + 1)] = (this.cdf[j] + d);
/*     */     }
/* 168 */     if (this.cdf[i] <= 0.0D) throw new IllegalArgumentException("At leat one probability must be > 0.0");
/* 169 */     for (int k = 0; k < i + 1; k++) {
/* 170 */       this.cdf[k] /= this.cdf[i];
/*     */     }
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */   public String toString()
/*     */   {
/* 178 */     String str = null;
/* 179 */     if (this.interpolationType == 1) str = "NO_INTERPOLATION";
/* 180 */     if (this.interpolationType == 0) str = "LINEAR_INTERPOLATION";
/* 181 */     return getClass().getName() + "(" + (this.cdf != null ? this.cdf.length : 0) + "," + str + ")";
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */   private int xnBins()
/*     */   {
/* 188 */     return this.cdf.length - 1;
/*     */   }
/*     */ }


/* Location:              /home/menick/Scrivania/ProcessMining/CNMining/CNMining.jar!/cern/jet/random/Empirical.class
 * Java compiler version: 1 (45.3)
 * JD-Core Version:       0.7.1
 */