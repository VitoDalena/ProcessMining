/*     */ package hep.aida.ref;
/*     */ 
/*     */ import hep.aida.IAxis;
/*     */ import hep.aida.IHistogram1D;
/*     */ import hep.aida.IHistogram2D;
/*     */ 
/*     */ abstract class AbstractHistogram2D
/*     */   extends Histogram implements IHistogram2D
/*     */ {
/*     */   protected IAxis xAxis;
/*     */   protected IAxis yAxis;
/*     */   
/*     */   AbstractHistogram2D(String paramString)
/*     */   {
/*  15 */     super(paramString);
/*     */   }
/*     */   
/*     */   public int allEntries() {
/*  19 */     int i = 0;
/*  20 */     int j = this.xAxis.bins();
/*  21 */     do { int k = this.yAxis.bins();
/*     */       do {
/*  23 */         i += binEntries(j, k);k--;
/*  21 */       } while (k >= -2);
/*  20 */       j--; } while (j >= -2);
/*     */     
/*     */ 
/*     */ 
/*     */ 
/*  25 */     return i;
/*     */   }
/*     */   
/*     */   public int binEntriesX(int paramInt) {
/*  29 */     return projectionX().binEntries(paramInt);
/*     */   }
/*     */   
/*     */   public int binEntriesY(int paramInt) {
/*  33 */     return projectionY().binEntries(paramInt);
/*     */   }
/*     */   
/*     */   public double binHeightX(int paramInt) {
/*  37 */     return projectionX().binHeight(paramInt);
/*     */   }
/*     */   
/*     */   public double binHeightY(int paramInt) {
/*  41 */     return projectionY().binHeight(paramInt);
/*     */   }
/*     */   
/*     */   public int dimensions() {
/*  45 */     return 2;
/*     */   }
/*     */   
/*     */   public int entries() {
/*  49 */     int i = 0;
/*  50 */     for (int j = 0; j < this.xAxis.bins(); j++) {
/*  51 */       for (int k = 0; k < this.yAxis.bins(); k++)
/*     */       {
/*  53 */         i += binEntries(j, k); }
/*     */     }
/*  55 */     return i;
/*     */   }
/*     */   
/*     */   public int extraEntries() {
/*  59 */     return allEntries() - entries();
/*     */   }
/*     */   
/*     */   public void fill(double paramDouble1, double paramDouble2) {
/*  63 */     fill(paramDouble1, paramDouble2, 1.0D);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   protected abstract IHistogram1D internalSliceX(String paramString, int paramInt1, int paramInt2);
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   protected abstract IHistogram1D internalSliceY(String paramString, int paramInt1, int paramInt2);
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   int mapX(int paramInt)
/*     */   {
/*  93 */     int i = this.xAxis.bins() + 2;
/*  94 */     if (paramInt >= i) throw new IllegalArgumentException("bin=" + paramInt);
/*  95 */     if (paramInt >= 0) return paramInt + 1;
/*  96 */     if (paramInt == -2) return 0;
/*  97 */     if (paramInt == -1) return i - 1;
/*  98 */     throw new IllegalArgumentException("bin=" + paramInt);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   int mapY(int paramInt)
/*     */   {
/* 106 */     int i = this.yAxis.bins() + 2;
/* 107 */     if (paramInt >= i) throw new IllegalArgumentException("bin=" + paramInt);
/* 108 */     if (paramInt >= 0) return paramInt + 1;
/* 109 */     if (paramInt == -2) return 0;
/* 110 */     if (paramInt == -1) return i - 1;
/* 111 */     throw new IllegalArgumentException("bin=" + paramInt);
/*     */   }
/*     */   
/*     */   public int[] minMaxBins() {
/* 115 */     double d1 = Double.MAX_VALUE;
/* 116 */     double d2 = Double.MIN_VALUE;
/* 117 */     int i = -1;
/* 118 */     int j = -1;
/* 119 */     int k = -1;
/* 120 */     int m = -1;
/* 121 */     int n = this.xAxis.bins();
/* 122 */     do { int i1 = this.yAxis.bins();
/* 123 */       do { double d3 = binHeight(n, i1);
/* 124 */         if (d3 < d1) {
/* 125 */           d1 = d3;
/* 126 */           i = n;
/* 127 */           j = i1;
/*     */         }
/* 129 */         if (d3 > d2) {
/* 130 */           d2 = d3;
/* 131 */           k = n;
/* 132 */           m = i1;
/*     */         }
/* 122 */         i1--; } while (i1 >= 0);
/* 121 */       n--; } while (n >= 0);
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
/* 136 */     int[] arrayOfInt = { i, j, k, m };
/* 137 */     return arrayOfInt;
/*     */   }
/*     */   
/*     */   public IHistogram1D projectionX() {
/* 141 */     String str = title() + " (projectionX)";
/*     */     
/* 143 */     return internalSliceX(str, mapY(-2), mapY(-1));
/*     */   }
/*     */   
/*     */   public IHistogram1D projectionY() {
/* 147 */     String str = title() + " (projectionY)";
/*     */     
/* 149 */     return internalSliceY(str, mapX(-2), mapX(-1));
/*     */   }
/*     */   
/*     */   public IHistogram1D sliceX(int paramInt)
/*     */   {
/* 154 */     int i = mapY(paramInt);
/* 155 */     String str = title() + " (sliceX [" + paramInt + "])";
/* 156 */     return internalSliceX(str, i, i);
/*     */   }
/*     */   
/*     */ 
/*     */   public IHistogram1D sliceX(int paramInt1, int paramInt2)
/*     */   {
/* 162 */     int i = mapY(paramInt1);
/* 163 */     int j = mapY(paramInt2);
/* 164 */     String str = title() + " (sliceX [" + paramInt1 + ":" + paramInt2 + "])";
/* 165 */     return internalSliceX(str, i, j);
/*     */   }
/*     */   
/*     */   public IHistogram1D sliceY(int paramInt)
/*     */   {
/* 170 */     int i = mapX(paramInt);
/* 171 */     String str = title() + " (sliceY [" + paramInt + "])";
/* 172 */     return internalSliceY(str, i, i);
/*     */   }
/*     */   
/*     */ 
/*     */   public IHistogram1D sliceY(int paramInt1, int paramInt2)
/*     */   {
/* 178 */     int i = mapX(paramInt1);
/* 179 */     int j = mapX(paramInt2);
/* 180 */     String str = title() + " (slicey [" + paramInt1 + ":" + paramInt2 + "])";
/* 181 */     return internalSliceY(str, i, j);
/*     */   }
/*     */   
/*     */   public double sumAllBinHeights() {
/* 185 */     double d = 0.0D;
/* 186 */     int i = this.xAxis.bins();
/* 187 */     do { int j = this.yAxis.bins();
/*     */       do {
/* 189 */         d += binHeight(i, j);j--;
/* 187 */       } while (j >= -2);
/* 186 */       i--; } while (i >= -2);
/*     */     
/*     */ 
/*     */ 
/*     */ 
/* 191 */     return d;
/*     */   }
/*     */   
/*     */   public double sumBinHeights() {
/* 195 */     double d = 0.0D;
/* 196 */     for (int i = 0; i < this.xAxis.bins(); i++) {
/* 197 */       for (int j = 0; j < this.yAxis.bins(); j++)
/*     */       {
/* 199 */         d += binHeight(i, j); }
/*     */     }
/* 201 */     return d;
/*     */   }
/*     */   
/*     */   public double sumExtraBinHeights() {
/* 205 */     return sumAllBinHeights() - sumBinHeights();
/*     */   }
/*     */   
/*     */   public IAxis xAxis() {
/* 209 */     return this.xAxis;
/*     */   }
/*     */   
/*     */   public IAxis yAxis() {
/* 213 */     return this.yAxis;
/*     */   }
/*     */   
/*     */   public abstract double rmsY();
/*     */   
/*     */   public abstract double rmsX();
/*     */   
/*     */   public abstract double meanY();
/*     */   
/*     */   public abstract double meanX();
/*     */   
/*     */   public abstract void fill(double paramDouble1, double paramDouble2, double paramDouble3);
/*     */   
/*     */   public abstract double binHeight(int paramInt1, int paramInt2);
/*     */   
/*     */   public abstract double binError(int paramInt1, int paramInt2);
/*     */   
/*     */   public abstract int binEntries(int paramInt1, int paramInt2);
/*     */ }


/* Location:              /home/menick/Scrivania/ProcessMining/CNMining/CNMining.jar!/hep/aida/ref/AbstractHistogram2D.class
 * Java compiler version: 1 (45.3)
 * JD-Core Version:       0.7.1
 */