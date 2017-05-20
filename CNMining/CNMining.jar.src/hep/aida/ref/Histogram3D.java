/*     */ package hep.aida.ref;
/*     */ 
/*     */ import hep.aida.IAxis;
/*     */ import hep.aida.IHistogram2D;
/*     */ import hep.aida.IHistogram3D;
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
/*     */ public class Histogram3D
/*     */   extends AbstractHistogram3D
/*     */   implements IHistogram3D
/*     */ {
/*     */   private double[][][] heights;
/*     */   private double[][][] errors;
/*     */   private int[][][] entries;
/*     */   private int nEntry;
/*     */   private double sumWeight;
/*     */   private double sumWeightSquared;
/*     */   private double meanX;
/*     */   private double rmsX;
/*     */   private double meanY;
/*     */   private double rmsY;
/*     */   private double meanZ;
/*     */   private double rmsZ;
/*     */   
/*     */   public Histogram3D(String paramString, double[] paramArrayOfDouble1, double[] paramArrayOfDouble2, double[] paramArrayOfDouble3)
/*     */   {
/*  37 */     this(paramString, new VariableAxis(paramArrayOfDouble1), new VariableAxis(paramArrayOfDouble2), new VariableAxis(paramArrayOfDouble3));
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
/*     */   public Histogram3D(String paramString, int paramInt1, double paramDouble1, double paramDouble2, int paramInt2, double paramDouble3, double paramDouble4, int paramInt3, double paramDouble5, double paramDouble6)
/*     */   {
/*  57 */     this(paramString, new FixedAxis(paramInt1, paramDouble1, paramDouble2), new FixedAxis(paramInt2, paramDouble3, paramDouble4), new FixedAxis(paramInt3, paramDouble5, paramDouble6));
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public Histogram3D(String paramString, IAxis paramIAxis1, IAxis paramIAxis2, IAxis paramIAxis3)
/*     */   {
/*  69 */     super(paramString);
/*  70 */     this.xAxis = paramIAxis1;
/*  71 */     this.yAxis = paramIAxis2;
/*  72 */     this.zAxis = paramIAxis3;
/*  73 */     int i = paramIAxis1.bins();
/*  74 */     int j = paramIAxis2.bins();
/*  75 */     int k = paramIAxis3.bins();
/*     */     
/*  77 */     this.entries = new int[i + 2][j + 2][k + 2];
/*  78 */     this.heights = new double[i + 2][j + 2][k + 2];
/*  79 */     this.errors = new double[i + 2][j + 2][k + 2];
/*     */   }
/*     */   
/*     */   public int allEntries()
/*     */   {
/*  84 */     return this.nEntry;
/*     */   }
/*     */   
/*     */   public int binEntries(int paramInt1, int paramInt2, int paramInt3) {
/*  88 */     return this.entries[mapX(paramInt1)][mapY(paramInt2)][mapZ(paramInt3)];
/*     */   }
/*     */   
/*     */   public double binError(int paramInt1, int paramInt2, int paramInt3) {
/*  92 */     return Math.sqrt(this.errors[mapX(paramInt1)][mapY(paramInt2)][mapZ(paramInt3)]);
/*     */   }
/*     */   
/*     */   public double binHeight(int paramInt1, int paramInt2, int paramInt3) {
/*  96 */     return this.heights[mapX(paramInt1)][mapY(paramInt2)][mapZ(paramInt3)];
/*     */   }
/*     */   
/*     */   public double equivalentBinEntries() {
/* 100 */     return this.sumWeight * this.sumWeight / this.sumWeightSquared;
/*     */   }
/*     */   
/*     */   public void fill(double paramDouble1, double paramDouble2, double paramDouble3) {
/* 104 */     int i = mapX(this.xAxis.coordToIndex(paramDouble1));
/* 105 */     int j = mapY(this.yAxis.coordToIndex(paramDouble2));
/* 106 */     int k = mapZ(this.zAxis.coordToIndex(paramDouble3));
/* 107 */     this.entries[i][j][k] += 1;
/* 108 */     this.heights[i][j][k] += 1.0D;
/* 109 */     this.errors[i][j][k] += 1.0D;
/* 110 */     this.nEntry += 1;
/* 111 */     this.sumWeight += 1.0D;
/* 112 */     this.sumWeightSquared += 1.0D;
/* 113 */     this.meanX += paramDouble1;
/* 114 */     this.rmsX += paramDouble1;
/* 115 */     this.meanY += paramDouble2;
/* 116 */     this.rmsY += paramDouble2;
/* 117 */     this.meanZ += paramDouble3;
/* 118 */     this.rmsZ += paramDouble3;
/*     */   }
/*     */   
/*     */   public void fill(double paramDouble1, double paramDouble2, double paramDouble3, double paramDouble4) {
/* 122 */     int i = mapX(this.xAxis.coordToIndex(paramDouble1));
/* 123 */     int j = mapY(this.yAxis.coordToIndex(paramDouble2));
/* 124 */     int k = mapZ(this.zAxis.coordToIndex(paramDouble3));
/* 125 */     this.entries[i][j][k] += 1;
/* 126 */     this.heights[i][j][k] += paramDouble4;
/* 127 */     this.errors[i][j][k] += paramDouble4 * paramDouble4;
/* 128 */     this.nEntry += 1;
/* 129 */     this.sumWeight += paramDouble4;
/* 130 */     this.sumWeightSquared += paramDouble4 * paramDouble4;
/* 131 */     this.meanX += paramDouble1 * paramDouble4;
/* 132 */     this.rmsX += paramDouble1 * paramDouble4 * paramDouble4;
/* 133 */     this.meanY += paramDouble2 * paramDouble4;
/* 134 */     this.rmsY += paramDouble2 * paramDouble4 * paramDouble4;
/* 135 */     this.meanZ += paramDouble3 * paramDouble4;
/* 136 */     this.rmsZ += paramDouble3 * paramDouble4 * paramDouble4;
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
/*     */   protected IHistogram2D internalSliceXY(String paramString, int paramInt1, int paramInt2)
/*     */   {
/* 153 */     if (paramInt2 < paramInt1) { throw new IllegalArgumentException("Invalid bin range");
/*     */     }
/* 155 */     int i = this.xAxis.bins() + 2;
/* 156 */     int j = this.yAxis.bins() + 2;
/* 157 */     int[][] arrayOfInt = new int[i][j];
/* 158 */     double[][] arrayOfDouble1 = new double[i][j];
/* 159 */     double[][] arrayOfDouble2 = new double[i][j];
/*     */     
/* 161 */     for (int k = 0; k < i; k++)
/*     */     {
/* 163 */       for (int m = 0; m < j; m++)
/*     */       {
/* 165 */         for (int n = paramInt1; n <= paramInt2; n++)
/*     */         {
/* 167 */           arrayOfInt[k][m] += this.entries[k][m][n];
/* 168 */           arrayOfDouble1[k][m] += this.heights[k][m][n];
/* 169 */           arrayOfDouble2[k][m] += this.errors[k][m][n];
/*     */         }
/*     */       }
/*     */     }
/* 173 */     Histogram2D localHistogram2D = new Histogram2D(paramString, this.xAxis, this.yAxis);
/* 174 */     localHistogram2D.setContents(arrayOfInt, arrayOfDouble1, arrayOfDouble2);
/* 175 */     return localHistogram2D;
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
/*     */   protected IHistogram2D internalSliceXZ(String paramString, int paramInt1, int paramInt2)
/*     */   {
/* 192 */     if (paramInt2 < paramInt1) { throw new IllegalArgumentException("Invalid bin range");
/*     */     }
/* 194 */     int i = this.xAxis.bins() + 2;
/* 195 */     int j = this.zAxis.bins() + 2;
/* 196 */     int[][] arrayOfInt = new int[i][j];
/* 197 */     double[][] arrayOfDouble1 = new double[i][j];
/* 198 */     double[][] arrayOfDouble2 = new double[i][j];
/*     */     
/* 200 */     for (int k = 0; k < i; k++)
/*     */     {
/* 202 */       for (int m = paramInt1; m <= paramInt2; m++)
/*     */       {
/* 204 */         for (int n = 0; k < j; n++)
/*     */         {
/* 206 */           arrayOfInt[k][n] += this.entries[k][m][n];
/* 207 */           arrayOfDouble1[k][n] += this.heights[k][m][n];
/* 208 */           arrayOfDouble2[k][n] += this.errors[k][m][n];
/*     */         }
/*     */       }
/*     */     }
/* 212 */     Histogram2D localHistogram2D = new Histogram2D(paramString, this.xAxis, this.zAxis);
/* 213 */     localHistogram2D.setContents(arrayOfInt, arrayOfDouble1, arrayOfDouble2);
/* 214 */     return localHistogram2D;
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
/*     */   protected IHistogram2D internalSliceYZ(String paramString, int paramInt1, int paramInt2)
/*     */   {
/* 231 */     if (paramInt2 < paramInt1) { throw new IllegalArgumentException("Invalid bin range");
/*     */     }
/* 233 */     int i = this.yAxis.bins() + 2;
/* 234 */     int j = this.zAxis.bins() + 2;
/* 235 */     int[][] arrayOfInt = new int[i][j];
/* 236 */     double[][] arrayOfDouble1 = new double[i][j];
/* 237 */     double[][] arrayOfDouble2 = new double[i][j];
/*     */     
/* 239 */     for (int k = paramInt1; k <= paramInt2; k++)
/*     */     {
/* 241 */       for (int m = 0; m < i; m++)
/*     */       {
/* 243 */         for (int n = 0; n < j; n++)
/*     */         {
/* 245 */           arrayOfInt[m][n] += this.entries[k][m][n];
/* 246 */           arrayOfDouble1[m][n] += this.heights[k][m][n];
/* 247 */           arrayOfDouble2[m][n] += this.errors[k][m][n];
/*     */         }
/*     */       }
/*     */     }
/* 251 */     Histogram2D localHistogram2D = new Histogram2D(paramString, this.yAxis, this.zAxis);
/* 252 */     localHistogram2D.setContents(arrayOfInt, arrayOfDouble1, arrayOfDouble2);
/* 253 */     return localHistogram2D;
/*     */   }
/*     */   
/*     */   public double meanX() {
/* 257 */     return this.meanX / this.sumWeight;
/*     */   }
/*     */   
/*     */   public double meanY() {
/* 261 */     return this.meanY / this.sumWeight;
/*     */   }
/*     */   
/*     */   public double meanZ() {
/* 265 */     return this.meanZ / this.sumWeight;
/*     */   }
/*     */   
/*     */   public void reset() {
/* 269 */     for (int i = 0; i < this.entries.length; i++)
/* 270 */       for (int j = 0; j < this.entries[0].length; j++)
/* 271 */         for (int k = 0; j < this.entries[0][0].length; k++)
/*     */         {
/* 273 */           this.entries[i][j][k] = 0;
/* 274 */           this.heights[i][j][k] = 0.0D;
/* 275 */           this.errors[i][j][k] = 0.0D;
/*     */         }
/* 277 */     this.nEntry = 0;
/* 278 */     this.sumWeight = 0.0D;
/* 279 */     this.sumWeightSquared = 0.0D;
/* 280 */     this.meanX = 0.0D;
/* 281 */     this.rmsX = 0.0D;
/* 282 */     this.meanY = 0.0D;
/* 283 */     this.rmsY = 0.0D;
/* 284 */     this.meanZ = 0.0D;
/* 285 */     this.rmsZ = 0.0D;
/*     */   }
/*     */   
/*     */   public double rmsX() {
/* 289 */     return Math.sqrt(this.rmsX / this.sumWeight - this.meanX * this.meanX / this.sumWeight / this.sumWeight);
/*     */   }
/*     */   
/*     */   public double rmsY() {
/* 293 */     return Math.sqrt(this.rmsY / this.sumWeight - this.meanY * this.meanY / this.sumWeight / this.sumWeight);
/*     */   }
/*     */   
/*     */   public double rmsZ() {
/* 297 */     return Math.sqrt(this.rmsZ / this.sumWeight - this.meanZ * this.meanZ / this.sumWeight / this.sumWeight);
/*     */   }
/*     */   
/*     */   public double sumAllBinHeights() {
/* 301 */     return this.sumWeight;
/*     */   }
/*     */ }


/* Location:              /home/menick/Scrivania/ProcessMining/CNMining/CNMining.jar!/hep/aida/ref/Histogram3D.class
 * Java compiler version: 1 (45.3)
 * JD-Core Version:       0.7.1
 */