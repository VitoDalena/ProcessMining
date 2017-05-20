/*     */ package hep.aida.bin;
/*     */ 
/*     */ import cern.colt.PersistentObject;
/*     */ import cern.colt.list.DoubleArrayList;
/*     */ import cern.jet.stat.Descriptive;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class MightyStaticBin1D
/*     */   extends StaticBin1D
/*     */ {
/*  15 */   protected boolean hasSumOfLogarithms = false;
/*  16 */   protected double sumOfLogarithms = 0.0D;
/*     */   
/*  18 */   protected boolean hasSumOfInversions = false;
/*  19 */   protected double sumOfInversions = 0.0D;
/*     */   
/*  21 */   protected double[] sumOfPowers = null;
/*     */   
/*     */ 
/*     */   public MightyStaticBin1D()
/*     */   {
/*  26 */     this(false, false, 4);
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
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public MightyStaticBin1D(boolean paramBoolean1, boolean paramBoolean2, int paramInt)
/*     */   {
/*  47 */     setMaxOrderForSumOfPowers(paramInt);
/*  48 */     this.hasSumOfLogarithms = paramBoolean1;
/*  49 */     this.hasSumOfInversions = paramBoolean2;
/*  50 */     clear();
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public synchronized void addAllOfFromTo(DoubleArrayList paramDoubleArrayList, int paramInt1, int paramInt2)
/*     */   {
/*  61 */     super.addAllOfFromTo(paramDoubleArrayList, paramInt1, paramInt2);
/*     */     
/*  63 */     if (this.sumOfPowers != null)
/*     */     {
/*  65 */       Descriptive.incrementalUpdateSumsOfPowers(paramDoubleArrayList, paramInt1, paramInt2, 3, getMaxOrderForSumOfPowers(), this.sumOfPowers);
/*     */     }
/*     */     
/*  68 */     if (this.hasSumOfInversions) {
/*  69 */       this.sumOfInversions += Descriptive.sumOfInversions(paramDoubleArrayList, paramInt1, paramInt2);
/*     */     }
/*     */     
/*  72 */     if (this.hasSumOfLogarithms) {
/*  73 */       this.sumOfLogarithms += Descriptive.sumOfLogarithms(paramDoubleArrayList, paramInt1, paramInt2);
/*     */     }
/*     */   }
/*     */   
/*     */ 
/*     */   protected void clearAllMeasures()
/*     */   {
/*  80 */     super.clearAllMeasures();
/*     */     
/*  82 */     this.sumOfLogarithms = 0.0D;
/*  83 */     this.sumOfInversions = 0.0D;
/*     */     
/*  85 */     if (this.sumOfPowers != null) {
/*  86 */       int i = this.sumOfPowers.length;
/*  87 */       do { this.sumOfPowers[i] = 0.0D;i--;
/*  86 */       } while (i >= 0);
/*     */     }
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public synchronized Object clone()
/*     */   {
/*  97 */     MightyStaticBin1D localMightyStaticBin1D = (MightyStaticBin1D)super.clone();
/*  98 */     if (this.sumOfPowers != null) localMightyStaticBin1D.sumOfPowers = ((double[])localMightyStaticBin1D.sumOfPowers.clone());
/*  99 */     return localMightyStaticBin1D;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   public String compareWith(AbstractBin1D paramAbstractBin1D)
/*     */   {
/* 107 */     StringBuffer localStringBuffer = new StringBuffer(super.compareWith(paramAbstractBin1D));
/* 108 */     if ((paramAbstractBin1D instanceof MightyStaticBin1D)) {
/* 109 */       MightyStaticBin1D localMightyStaticBin1D = (MightyStaticBin1D)paramAbstractBin1D;
/* 110 */       if ((hasSumOfLogarithms()) && (localMightyStaticBin1D.hasSumOfLogarithms()))
/* 111 */         localStringBuffer.append("geometric mean: " + relError(geometricMean(), localMightyStaticBin1D.geometricMean()) + " %\n");
/* 112 */       if ((hasSumOfInversions()) && (localMightyStaticBin1D.hasSumOfInversions()))
/* 113 */         localStringBuffer.append("harmonic mean: " + relError(harmonicMean(), localMightyStaticBin1D.harmonicMean()) + " %\n");
/* 114 */       if ((hasSumOfPowers(3)) && (localMightyStaticBin1D.hasSumOfPowers(3)))
/* 115 */         localStringBuffer.append("skew: " + relError(skew(), localMightyStaticBin1D.skew()) + " %\n");
/* 116 */       if ((hasSumOfPowers(4)) && (localMightyStaticBin1D.hasSumOfPowers(4)))
/* 117 */         localStringBuffer.append("kurtosis: " + relError(kurtosis(), localMightyStaticBin1D.kurtosis()) + " %\n");
/* 118 */       localStringBuffer.append("\n");
/*     */     }
/* 120 */     return localStringBuffer.toString();
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public synchronized double geometricMean()
/*     */   {
/* 131 */     return Descriptive.geometricMean(size(), sumOfLogarithms());
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
/*     */   public synchronized int getMaxOrderForSumOfPowers()
/*     */   {
/* 144 */     if (this.sumOfPowers == null) { return 2;
/*     */     }
/* 146 */     return 2 + this.sumOfPowers.length;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   public synchronized int getMinOrderForSumOfPowers()
/*     */   {
/* 154 */     int i = 0;
/* 155 */     if (hasSumOfInversions()) i = -1;
/* 156 */     return i;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public synchronized double harmonicMean()
/*     */   {
/* 165 */     return Descriptive.harmonicMean(size(), sumOfInversions());
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   public boolean hasSumOfInversions()
/*     */   {
/* 173 */     return this.hasSumOfInversions;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   public boolean hasSumOfLogarithms()
/*     */   {
/* 181 */     return this.hasSumOfLogarithms;
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
/*     */   public boolean hasSumOfPowers(int paramInt)
/*     */   {
/* 198 */     return (getMinOrderForSumOfPowers() <= paramInt) && (paramInt <= getMaxOrderForSumOfPowers());
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   public synchronized double kurtosis()
/*     */   {
/* 206 */     return Descriptive.kurtosis(moment(4, mean()), standardDeviation());
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public synchronized double moment(int paramInt, double paramDouble)
/*     */   {
/* 218 */     if (paramInt < 0) { throw new IllegalArgumentException("k must be >= 0");
/*     */     }
/* 220 */     if (!hasSumOfPowers(paramInt)) { return NaN.0D;
/*     */     }
/* 222 */     int i = Math.min(paramInt, getMaxOrderForSumOfPowers());
/* 223 */     DoubleArrayList localDoubleArrayList = new DoubleArrayList(i + 1);
/* 224 */     localDoubleArrayList.add(size());
/* 225 */     localDoubleArrayList.add(sum());
/* 226 */     localDoubleArrayList.add(sumOfSquares());
/* 227 */     for (int j = 3; j <= i; j++) { localDoubleArrayList.add(sumOfPowers(j));
/*     */     }
/* 229 */     return Descriptive.moment(paramInt, paramDouble, size(), localDoubleArrayList.elements());
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public double product()
/*     */   {
/* 238 */     return Descriptive.product(size(), sumOfLogarithms());
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
/*     */   protected void setMaxOrderForSumOfPowers(int paramInt)
/*     */   {
/* 251 */     if (paramInt <= 2) {
/* 252 */       this.sumOfPowers = null;
/*     */     }
/*     */     else {
/* 255 */       this.sumOfPowers = new double[paramInt - 2];
/*     */     }
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   public synchronized double skew()
/*     */   {
/* 264 */     return Descriptive.skew(moment(3, mean()), standardDeviation());
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   public double sumOfInversions()
/*     */   {
/* 272 */     if (!this.hasSumOfInversions) { return NaN.0D;
/*     */     }
/* 274 */     return this.sumOfInversions;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   public synchronized double sumOfLogarithms()
/*     */   {
/* 282 */     if (!this.hasSumOfLogarithms) { return NaN.0D;
/*     */     }
/* 284 */     return this.sumOfLogarithms;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public synchronized double sumOfPowers(int paramInt)
/*     */   {
/* 293 */     if (!hasSumOfPowers(paramInt)) { return NaN.0D;
/*     */     }
/* 295 */     if (paramInt == -1) return sumOfInversions();
/* 296 */     if (paramInt == 0) return size();
/* 297 */     if (paramInt == 1) return sum();
/* 298 */     if (paramInt == 2) { return sumOfSquares();
/*     */     }
/* 300 */     return this.sumOfPowers[(paramInt - 3)];
/*     */   }
/*     */   
/*     */ 
/*     */   public synchronized String toString()
/*     */   {
/* 306 */     StringBuffer localStringBuffer = new StringBuffer(super.toString());
/*     */     
/* 308 */     if (hasSumOfLogarithms()) {
/* 309 */       localStringBuffer.append("Geometric mean: " + geometricMean());
/* 310 */       localStringBuffer.append("\nProduct: " + product() + "\n");
/*     */     }
/*     */     
/* 313 */     if (hasSumOfInversions()) {
/* 314 */       localStringBuffer.append("Harmonic mean: " + harmonicMean());
/* 315 */       localStringBuffer.append("\nSum of inversions: " + sumOfInversions() + "\n");
/*     */     }
/*     */     
/* 318 */     int i = getMaxOrderForSumOfPowers();
/* 319 */     int j = Math.min(6, i);
/* 320 */     if (i > 2) {
/* 321 */       if (i >= 3) {
/* 322 */         localStringBuffer.append("Skew: " + skew() + "\n");
/*     */       }
/* 324 */       if (i >= 4) {
/* 325 */         localStringBuffer.append("Kurtosis: " + kurtosis() + "\n");
/*     */       }
/* 327 */       for (int k = 3; k <= j; k++) {
/* 328 */         localStringBuffer.append("Sum of powers(" + k + "): " + sumOfPowers(k) + "\n");
/*     */       }
/* 330 */       for (int m = 0; m <= j; m++) {
/* 331 */         localStringBuffer.append("Moment(" + m + ",0): " + moment(m, 0.0D) + "\n");
/*     */       }
/* 333 */       for (int n = 0; n <= j; n++) {
/* 334 */         localStringBuffer.append("Moment(" + n + ",mean()): " + moment(n, mean()) + "\n");
/*     */       }
/*     */     }
/* 337 */     return localStringBuffer.toString();
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   protected void xcheckOrder(int paramInt) {}
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   protected boolean xequals(Object paramObject)
/*     */   {
/* 351 */     if (!(paramObject instanceof MightyStaticBin1D)) return false;
/* 352 */     MightyStaticBin1D localMightyStaticBin1D = (MightyStaticBin1D)paramObject;
/* 353 */     return (super.equals(localMightyStaticBin1D)) && (sumOfInversions() == localMightyStaticBin1D.sumOfInversions()) && (sumOfLogarithms() == localMightyStaticBin1D.sumOfLogarithms());
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   protected boolean xhasSumOfPowers(int paramInt1, int paramInt2)
/*     */   {
/* 362 */     if (paramInt1 > paramInt2) throw new IllegalArgumentException("fromK must be less or equal to toK");
/* 363 */     return (getMinOrderForSumOfPowers() <= paramInt1) && (paramInt2 <= getMaxOrderForSumOfPowers());
/*     */   }
/*     */   
/*     */ 
/*     */   protected synchronized boolean xisLegalOrder(int paramInt)
/*     */   {
/* 369 */     return (getMinOrderForSumOfPowers() <= paramInt) && (paramInt <= getMaxOrderForSumOfPowers());
/*     */   }
/*     */ }


/* Location:              /home/menick/Scrivania/ProcessMining/CNMining/CNMining.jar!/hep/aida/bin/MightyStaticBin1D.class
 * Java compiler version: 1 (45.3)
 * JD-Core Version:       0.7.1
 */