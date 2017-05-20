/*     */ package hep.aida.ref;
/*     */ 
/*     */ import hep.aida.IAxis;
/*     */ import hep.aida.IHistogram1D;
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
/*     */ public class Histogram1D
/*     */   extends AbstractHistogram1D
/*     */   implements IHistogram1D
/*     */ {
/*     */   private double[] errors;
/*     */   private double[] heights;
/*     */   private int[] entries;
/*     */   private int nEntry;
/*     */   private double sumWeight;
/*     */   private double sumWeightSquared;
/*     */   private double mean;
/*     */   private double rms;
/*     */   
/*     */   public Histogram1D(String paramString, double[] paramArrayOfDouble)
/*     */   {
/*  31 */     this(paramString, new VariableAxis(paramArrayOfDouble));
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public Histogram1D(String paramString, int paramInt, double paramDouble1, double paramDouble2)
/*     */   {
/*  43 */     this(paramString, new FixedAxis(paramInt, paramDouble1, paramDouble2));
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public Histogram1D(String paramString, IAxis paramIAxis)
/*     */   {
/*  53 */     super(paramString);
/*  54 */     this.xAxis = paramIAxis;
/*  55 */     int i = paramIAxis.bins();
/*  56 */     this.entries = new int[i + 2];
/*  57 */     this.heights = new double[i + 2];
/*  58 */     this.errors = new double[i + 2];
/*     */   }
/*     */   
/*     */   public int allEntries() {
/*  62 */     return this.nEntry;
/*     */   }
/*     */   
/*     */   public int binEntries(int paramInt)
/*     */   {
/*  67 */     return this.entries[map(paramInt)];
/*     */   }
/*     */   
/*     */   public double binError(int paramInt)
/*     */   {
/*  72 */     return Math.sqrt(this.errors[map(paramInt)]);
/*     */   }
/*     */   
/*     */   public double binHeight(int paramInt)
/*     */   {
/*  77 */     return this.heights[map(paramInt)];
/*     */   }
/*     */   
/*     */   public double equivalentBinEntries() {
/*  81 */     return this.sumWeight * this.sumWeight / this.sumWeightSquared;
/*     */   }
/*     */   
/*     */   public void fill(double paramDouble)
/*     */   {
/*  86 */     int i = map(this.xAxis.coordToIndex(paramDouble));
/*  87 */     this.entries[i] += 1;
/*  88 */     this.heights[i] += 1.0D;
/*  89 */     this.errors[i] += 1.0D;
/*  90 */     this.nEntry += 1;
/*  91 */     this.sumWeight += 1.0D;
/*  92 */     this.sumWeightSquared += 1.0D;
/*  93 */     this.mean += paramDouble;
/*  94 */     this.rms += paramDouble * paramDouble;
/*     */   }
/*     */   
/*     */   public void fill(double paramDouble1, double paramDouble2)
/*     */   {
/*  99 */     int i = map(this.xAxis.coordToIndex(paramDouble1));
/* 100 */     this.entries[i] += 1;
/* 101 */     this.heights[i] += paramDouble2;
/* 102 */     this.errors[i] += paramDouble2 * paramDouble2;
/* 103 */     this.nEntry += 1;
/* 104 */     this.sumWeight += paramDouble2;
/* 105 */     this.sumWeightSquared += paramDouble2 * paramDouble2;
/* 106 */     this.mean += paramDouble1 * paramDouble2;
/* 107 */     this.rms += paramDouble1 * paramDouble2 * paramDouble2;
/*     */   }
/*     */   
/*     */   public double mean() {
/* 111 */     return this.mean / this.sumWeight;
/*     */   }
/*     */   
/*     */   public void reset() {
/* 115 */     for (int i = 0; i < this.entries.length; i++)
/*     */     {
/* 117 */       this.entries[i] = 0;
/* 118 */       this.heights[i] = 0.0D;
/* 119 */       this.errors[i] = 0.0D;
/*     */     }
/* 121 */     this.nEntry = 0;
/* 122 */     this.sumWeight = 0.0D;
/* 123 */     this.sumWeightSquared = 0.0D;
/* 124 */     this.mean = 0.0D;
/* 125 */     this.rms = 0.0D;
/*     */   }
/*     */   
/*     */   public double rms() {
/* 129 */     return Math.sqrt(this.rms / this.sumWeight - this.mean * this.mean / this.sumWeight / this.sumWeight);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */   void setContents(int[] paramArrayOfInt, double[] paramArrayOfDouble1, double[] paramArrayOfDouble2)
/*     */   {
/* 136 */     this.entries = paramArrayOfInt;
/* 137 */     this.heights = paramArrayOfDouble1;
/* 138 */     this.errors = paramArrayOfDouble2;
/*     */     
/* 140 */     for (int i = 0; i < paramArrayOfInt.length; i++)
/*     */     {
/* 142 */       this.nEntry += paramArrayOfInt[i];
/* 143 */       this.sumWeight += paramArrayOfDouble1[i];
/*     */     }
/*     */     
/* 146 */     this.sumWeightSquared = NaN.0D;
/* 147 */     this.mean = NaN.0D;
/* 148 */     this.rms = NaN.0D;
/*     */   }
/*     */ }


/* Location:              /home/menick/Scrivania/ProcessMining/CNMining/CNMining.jar!/hep/aida/ref/Histogram1D.class
 * Java compiler version: 1 (45.3)
 * JD-Core Version:       0.7.1
 */