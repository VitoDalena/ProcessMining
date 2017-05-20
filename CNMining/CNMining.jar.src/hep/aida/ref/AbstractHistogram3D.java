/*     */ package hep.aida.ref;
/*     */ 
/*     */ import hep.aida.IAxis;
/*     */ import hep.aida.IHistogram2D;
/*     */ import hep.aida.IHistogram3D;
/*     */ 
/*     */ abstract class AbstractHistogram3D extends Histogram implements IHistogram3D
/*     */ {
/*     */   protected IAxis xAxis;
/*     */   protected IAxis yAxis;
/*     */   protected IAxis zAxis;
/*     */   
/*     */   AbstractHistogram3D(String paramString)
/*     */   {
/*  15 */     super(paramString);
/*     */   }
/*     */   
/*     */   public int allEntries() {
/*  19 */     int i = 0;
/*  20 */     int j = this.xAxis.bins();
/*  21 */     do { int k = this.yAxis.bins();
/*  22 */       do { int m = this.zAxis.bins();
/*     */         do {
/*  24 */           i += binEntries(j, k, m);m--;
/*  22 */         } while (m >= -2);
/*  21 */         k--; } while (k >= -2);
/*  20 */       j--; } while (j >= -2);
/*     */     
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*  26 */     return i;
/*     */   }
/*     */   
/*     */   public int dimensions() {
/*  30 */     return 3;
/*     */   }
/*     */   
/*     */   public int entries() {
/*  34 */     int i = 0;
/*  35 */     for (int j = 0; j < this.xAxis.bins(); j++) {
/*  36 */       for (int k = 0; k < this.yAxis.bins(); k++)
/*  37 */         for (int m = 0; m < this.zAxis.bins(); m++)
/*     */         {
/*  39 */           i += binEntries(j, k, m); }
/*     */     }
/*  41 */     return i;
/*     */   }
/*     */   
/*     */   public int extraEntries() {
/*  45 */     return allEntries() - entries();
/*     */   }
/*     */   
/*     */   public void fill(double paramDouble1, double paramDouble2, double paramDouble3) {
/*  49 */     fill(paramDouble1, paramDouble2, paramDouble3, 1.0D);
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
/*     */   protected abstract IHistogram2D internalSliceXY(String paramString, int paramInt1, int paramInt2);
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   protected abstract IHistogram2D internalSliceXZ(String paramString, int paramInt1, int paramInt2);
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   protected abstract IHistogram2D internalSliceYZ(String paramString, int paramInt1, int paramInt2);
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
/*  90 */     int i = this.xAxis.bins() + 2;
/*  91 */     if (paramInt >= i) throw new IllegalArgumentException("bin=" + paramInt);
/*  92 */     if (paramInt >= 0) return paramInt + 1;
/*  93 */     if (paramInt == -2) return 0;
/*  94 */     if (paramInt == -1) return i - 1;
/*  95 */     throw new IllegalArgumentException("bin=" + paramInt);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   int mapY(int paramInt)
/*     */   {
/* 103 */     int i = this.yAxis.bins() + 2;
/* 104 */     if (paramInt >= i) throw new IllegalArgumentException("bin=" + paramInt);
/* 105 */     if (paramInt >= 0) return paramInt + 1;
/* 106 */     if (paramInt == -2) return 0;
/* 107 */     if (paramInt == -1) return i - 1;
/* 108 */     throw new IllegalArgumentException("bin=" + paramInt);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   int mapZ(int paramInt)
/*     */   {
/* 116 */     int i = this.zAxis.bins() + 2;
/* 117 */     if (paramInt >= i) throw new IllegalArgumentException("bin=" + paramInt);
/* 118 */     if (paramInt >= 0) return paramInt + 1;
/* 119 */     if (paramInt == -2) return 0;
/* 120 */     if (paramInt == -1) return i - 1;
/* 121 */     throw new IllegalArgumentException("bin=" + paramInt);
/*     */   }
/*     */   
/*     */   public int[] minMaxBins() {
/* 125 */     double d1 = Double.MAX_VALUE;
/* 126 */     double d2 = Double.MIN_VALUE;
/* 127 */     int i = -1;
/* 128 */     int j = -1;
/* 129 */     int k = -1;
/* 130 */     int m = -1;
/* 131 */     int n = -1;
/* 132 */     int i1 = -1;
/* 133 */     int i2 = this.xAxis.bins();
/* 134 */     do { int i3 = this.yAxis.bins();
/* 135 */       do { int i4 = this.zAxis.bins();
/* 136 */         do { double d3 = binHeight(i2, i3, i4);
/* 137 */           if (d3 < d1) {
/* 138 */             d1 = d3;
/* 139 */             i = i2;
/* 140 */             j = i3;
/* 141 */             k = i4;
/*     */           }
/* 143 */           if (d3 > d2) {
/* 144 */             d2 = d3;
/* 145 */             m = i2;
/* 146 */             n = i3;
/* 147 */             i1 = i4;
/*     */           }
/* 135 */           i4--; } while (i4 >= 0);
/* 134 */         i3--; } while (i3 >= 0);
/* 133 */       i2--; } while (i2 >= 0);
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
/* 152 */     int[] arrayOfInt = { i, j, k, m, n, i1 };
/* 153 */     return arrayOfInt;
/*     */   }
/*     */   
/*     */   public IHistogram2D projectionXY() {
/* 157 */     String str = title() + " (projectionXY)";
/* 158 */     return internalSliceXY(str, mapZ(-2), mapZ(-1));
/*     */   }
/*     */   
/*     */   public IHistogram2D projectionXZ() {
/* 162 */     String str = title() + " (projectionXZ)";
/* 163 */     return internalSliceXZ(str, mapY(-2), mapY(-1));
/*     */   }
/*     */   
/*     */   public IHistogram2D projectionYZ() {
/* 167 */     String str = title() + " (projectionYZ)";
/* 168 */     return internalSliceYZ(str, mapX(-2), mapX(-1));
/*     */   }
/*     */   
/*     */   public IHistogram2D sliceXY(int paramInt) {
/* 172 */     return sliceXY(paramInt, paramInt);
/*     */   }
/*     */   
/*     */   public IHistogram2D sliceXY(int paramInt1, int paramInt2) {
/* 176 */     int i = mapZ(paramInt1);
/* 177 */     int j = mapZ(paramInt2);
/* 178 */     String str = title() + " (sliceXY [" + paramInt1 + ":" + paramInt2 + "])";
/* 179 */     return internalSliceXY(str, i, j);
/*     */   }
/*     */   
/*     */   public IHistogram2D sliceXZ(int paramInt) {
/* 183 */     return sliceXZ(paramInt, paramInt);
/*     */   }
/*     */   
/*     */   public IHistogram2D sliceXZ(int paramInt1, int paramInt2) {
/* 187 */     int i = mapY(paramInt1);
/* 188 */     int j = mapY(paramInt2);
/* 189 */     String str = title() + " (sliceXZ [" + paramInt1 + ":" + paramInt2 + "])";
/* 190 */     return internalSliceXY(str, i, j);
/*     */   }
/*     */   
/*     */   public IHistogram2D sliceYZ(int paramInt) {
/* 194 */     return sliceYZ(paramInt, paramInt);
/*     */   }
/*     */   
/*     */   public IHistogram2D sliceYZ(int paramInt1, int paramInt2) {
/* 198 */     int i = mapX(paramInt1);
/* 199 */     int j = mapX(paramInt2);
/* 200 */     String str = title() + " (sliceYZ [" + paramInt1 + ":" + paramInt2 + "])";
/* 201 */     return internalSliceYZ(str, i, j);
/*     */   }
/*     */   
/*     */   public double sumAllBinHeights() {
/* 205 */     double d = 0.0D;
/* 206 */     int i = this.xAxis.bins();
/* 207 */     do { int j = this.yAxis.bins();
/* 208 */       do { int k = this.zAxis.bins();
/*     */         do {
/* 210 */           d += binHeight(i, j, k);k--;
/* 208 */         } while (k >= -2);
/* 207 */         j--; } while (j >= -2);
/* 206 */       i--; } while (i >= -2);
/*     */     
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/* 212 */     return d;
/*     */   }
/*     */   
/*     */   public double sumBinHeights() {
/* 216 */     double d = 0.0D;
/* 217 */     for (int i = 0; i < this.xAxis.bins(); i++) {
/* 218 */       for (int j = 0; j < this.yAxis.bins(); j++)
/* 219 */         for (int k = 0; k < this.zAxis.bins(); k++)
/*     */         {
/* 221 */           d += binHeight(i, j, k); }
/*     */     }
/* 223 */     return d;
/*     */   }
/*     */   
/*     */   public double sumExtraBinHeights() {
/* 227 */     return sumAllBinHeights() - sumBinHeights();
/*     */   }
/*     */   
/*     */   public IAxis xAxis() {
/* 231 */     return this.xAxis;
/*     */   }
/*     */   
/*     */   public IAxis yAxis() {
/* 235 */     return this.yAxis;
/*     */   }
/*     */   
/*     */   public IAxis zAxis() {
/* 239 */     return this.zAxis;
/*     */   }
/*     */   
/*     */   public abstract double rmsZ();
/*     */   
/*     */   public abstract double rmsY();
/*     */   
/*     */   public abstract double rmsX();
/*     */   
/*     */   public abstract double meanZ();
/*     */   
/*     */   public abstract double meanY();
/*     */   
/*     */   public abstract double meanX();
/*     */   
/*     */   public abstract void fill(double paramDouble1, double paramDouble2, double paramDouble3, double paramDouble4);
/*     */   
/*     */   public abstract double binHeight(int paramInt1, int paramInt2, int paramInt3);
/*     */   
/*     */   public abstract double binError(int paramInt1, int paramInt2, int paramInt3);
/*     */   
/*     */   public abstract int binEntries(int paramInt1, int paramInt2, int paramInt3);
/*     */ }


/* Location:              /home/menick/Scrivania/ProcessMining/CNMining/CNMining.jar!/hep/aida/ref/AbstractHistogram3D.class
 * Java compiler version: 1 (45.3)
 * JD-Core Version:       0.7.1
 */