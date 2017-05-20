/*     */ package hep.aida.ref;
/*     */ 
/*     */ import hep.aida.IAxis;
/*     */ import hep.aida.IHistogram1D;
/*     */ import hep.aida.IHistogram2D;
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
/*     */ public class Histogram2D
/*     */   extends AbstractHistogram2D
/*     */   implements IHistogram2D
/*     */ {
/*     */   private double[][] heights;
/*     */   private double[][] errors;
/*     */   private int[][] entries;
/*     */   private int nEntry;
/*     */   private double sumWeight;
/*     */   private double sumWeightSquared;
/*     */   private double meanX;
/*     */   private double rmsX;
/*     */   private double meanY;
/*     */   private double rmsY;
/*     */   
/*     */   public Histogram2D(String paramString, double[] paramArrayOfDouble1, double[] paramArrayOfDouble2)
/*     */   {
/*  34 */     this(paramString, new VariableAxis(paramArrayOfDouble1), new VariableAxis(paramArrayOfDouble2));
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
/*     */   public Histogram2D(String paramString, int paramInt1, double paramDouble1, double paramDouble2, int paramInt2, double paramDouble3, double paramDouble4)
/*     */   {
/*  50 */     this(paramString, new FixedAxis(paramInt1, paramDouble1, paramDouble2), new FixedAxis(paramInt2, paramDouble3, paramDouble4));
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public Histogram2D(String paramString, IAxis paramIAxis1, IAxis paramIAxis2)
/*     */   {
/*  61 */     super(paramString);
/*  62 */     this.xAxis = paramIAxis1;
/*  63 */     this.yAxis = paramIAxis2;
/*  64 */     int i = paramIAxis1.bins();
/*  65 */     int j = paramIAxis2.bins();
/*     */     
/*  67 */     this.entries = new int[i + 2][j + 2];
/*  68 */     this.heights = new double[i + 2][j + 2];
/*  69 */     this.errors = new double[i + 2][j + 2];
/*     */   }
/*     */   
/*     */   public int allEntries()
/*     */   {
/*  74 */     return this.nEntry;
/*     */   }
/*     */   
/*     */   public int binEntries(int paramInt1, int paramInt2)
/*     */   {
/*  79 */     return this.entries[mapX(paramInt1)][mapY(paramInt2)];
/*     */   }
/*     */   
/*     */   public double binError(int paramInt1, int paramInt2)
/*     */   {
/*  84 */     return Math.sqrt(this.errors[mapX(paramInt1)][mapY(paramInt2)]);
/*     */   }
/*     */   
/*     */   public double binHeight(int paramInt1, int paramInt2)
/*     */   {
/*  89 */     return this.heights[mapX(paramInt1)][mapY(paramInt2)];
/*     */   }
/*     */   
/*     */   public double equivalentBinEntries() {
/*  93 */     return this.sumWeight * this.sumWeight / this.sumWeightSquared;
/*     */   }
/*     */   
/*     */ 
/*     */   public void fill(double paramDouble1, double paramDouble2)
/*     */   {
/*  99 */     int i = mapX(this.xAxis.coordToIndex(paramDouble1));
/* 100 */     int j = mapY(this.yAxis.coordToIndex(paramDouble2));
/* 101 */     this.entries[i][j] += 1;
/* 102 */     this.heights[i][j] += 1.0D;
/* 103 */     this.errors[i][j] += 1.0D;
/* 104 */     this.nEntry += 1;
/* 105 */     this.sumWeight += 1.0D;
/* 106 */     this.sumWeightSquared += 1.0D;
/* 107 */     this.meanX += paramDouble1;
/* 108 */     this.rmsX += paramDouble1;
/* 109 */     this.meanY += paramDouble2;
/* 110 */     this.rmsY += paramDouble2;
/*     */   }
/*     */   
/*     */ 
/*     */   public void fill(double paramDouble1, double paramDouble2, double paramDouble3)
/*     */   {
/* 116 */     int i = mapX(this.xAxis.coordToIndex(paramDouble1));
/* 117 */     int j = mapY(this.yAxis.coordToIndex(paramDouble2));
/* 118 */     this.entries[i][j] += 1;
/* 119 */     this.heights[i][j] += paramDouble3;
/* 120 */     this.errors[i][j] += paramDouble3 * paramDouble3;
/* 121 */     this.nEntry += 1;
/* 122 */     this.sumWeight += paramDouble3;
/* 123 */     this.sumWeightSquared += paramDouble3 * paramDouble3;
/* 124 */     this.meanX += paramDouble1 * paramDouble3;
/* 125 */     this.rmsX += paramDouble1 * paramDouble3 * paramDouble3;
/* 126 */     this.meanY += paramDouble2 * paramDouble3;
/* 127 */     this.rmsY += paramDouble2 * paramDouble3 * paramDouble3;
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
/*     */   protected IHistogram1D internalSliceX(String paramString, int paramInt1, int paramInt2)
/*     */   {
/* 144 */     if (paramInt2 < paramInt1) { throw new IllegalArgumentException("Invalid bin range");
/*     */     }
/* 146 */     int i = this.xAxis.bins() + 2;
/* 147 */     int[] arrayOfInt = new int[i];
/* 148 */     double[] arrayOfDouble1 = new double[i];
/* 149 */     double[] arrayOfDouble2 = new double[i];
/*     */     
/*     */ 
/* 152 */     for (int j = 0; j < i; j++)
/*     */     {
/* 154 */       for (int k = paramInt1; k <= paramInt2; k++)
/*     */       {
/* 156 */         arrayOfInt[j] += this.entries[j][k];
/* 157 */         arrayOfDouble1[j] += this.heights[j][k];
/* 158 */         arrayOfDouble2[j] += this.errors[j][k];
/*     */       }
/*     */     }
/* 161 */     Histogram1D localHistogram1D = new Histogram1D(paramString, this.xAxis);
/* 162 */     localHistogram1D.setContents(arrayOfInt, arrayOfDouble1, arrayOfDouble2);
/* 163 */     return localHistogram1D;
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
/*     */   protected IHistogram1D internalSliceY(String paramString, int paramInt1, int paramInt2)
/*     */   {
/* 180 */     if (paramInt2 < paramInt1) { throw new IllegalArgumentException("Invalid bin range");
/*     */     }
/* 182 */     int i = this.yAxis.bins() + 2;
/* 183 */     int[] arrayOfInt = new int[i];
/* 184 */     double[] arrayOfDouble1 = new double[i];
/* 185 */     double[] arrayOfDouble2 = new double[i];
/*     */     
/* 187 */     for (int j = paramInt1; j <= paramInt2; j++)
/*     */     {
/*     */ 
/* 190 */       for (int k = 0; k < i; k++)
/*     */       {
/* 192 */         arrayOfInt[k] += this.entries[j][k];
/* 193 */         arrayOfDouble1[k] += this.heights[j][k];
/* 194 */         arrayOfDouble2[k] += this.errors[j][k];
/*     */       }
/*     */     }
/* 197 */     Histogram1D localHistogram1D = new Histogram1D(paramString, this.yAxis);
/* 198 */     localHistogram1D.setContents(arrayOfInt, arrayOfDouble1, arrayOfDouble2);
/* 199 */     return localHistogram1D;
/*     */   }
/*     */   
/*     */   public double meanX() {
/* 203 */     return this.meanX / this.sumWeight;
/*     */   }
/*     */   
/*     */   public double meanY() {
/* 207 */     return this.meanY / this.sumWeight;
/*     */   }
/*     */   
/*     */   public void reset() {
/* 211 */     for (int i = 0; i < this.entries.length; i++)
/* 212 */       for (int j = 0; j < this.entries[0].length; j++)
/*     */       {
/* 214 */         this.entries[i][j] = 0;
/* 215 */         this.heights[i][j] = 0.0D;
/* 216 */         this.errors[i][j] = 0.0D;
/*     */       }
/* 218 */     this.nEntry = 0;
/* 219 */     this.sumWeight = 0.0D;
/* 220 */     this.sumWeightSquared = 0.0D;
/* 221 */     this.meanX = 0.0D;
/* 222 */     this.rmsX = 0.0D;
/* 223 */     this.meanY = 0.0D;
/* 224 */     this.rmsY = 0.0D;
/*     */   }
/*     */   
/*     */   public double rmsX() {
/* 228 */     return Math.sqrt(this.rmsX / this.sumWeight - this.meanX * this.meanX / this.sumWeight / this.sumWeight);
/*     */   }
/*     */   
/*     */   public double rmsY() {
/* 232 */     return Math.sqrt(this.rmsY / this.sumWeight - this.meanY * this.meanY / this.sumWeight / this.sumWeight);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */   void setContents(int[][] paramArrayOfInt, double[][] paramArrayOfDouble1, double[][] paramArrayOfDouble2)
/*     */   {
/* 239 */     this.entries = paramArrayOfInt;
/* 240 */     this.heights = paramArrayOfDouble1;
/* 241 */     this.errors = paramArrayOfDouble2;
/*     */     
/* 243 */     for (int i = 0; i < paramArrayOfInt.length; i++) {
/* 244 */       for (int j = 0; j < paramArrayOfInt[0].length; j++)
/*     */       {
/* 246 */         this.nEntry += paramArrayOfInt[i][j];
/* 247 */         this.sumWeight += paramArrayOfDouble1[i][j];
/*     */       }
/*     */     }
/* 250 */     this.sumWeightSquared = NaN.0D;
/* 251 */     this.meanX = NaN.0D;
/* 252 */     this.rmsX = NaN.0D;
/* 253 */     this.meanY = NaN.0D;
/* 254 */     this.rmsY = NaN.0D;
/*     */   }
/*     */   
/*     */   public double sumAllBinHeights() {
/* 258 */     return this.sumWeight;
/*     */   }
/*     */ }


/* Location:              /home/menick/Scrivania/ProcessMining/CNMining/CNMining.jar!/hep/aida/ref/Histogram2D.class
 * Java compiler version: 1 (45.3)
 * JD-Core Version:       0.7.1
 */