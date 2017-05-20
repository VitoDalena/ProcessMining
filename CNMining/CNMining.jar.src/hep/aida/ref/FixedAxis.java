/*     */ package hep.aida.ref;
/*     */ 
/*     */ import hep.aida.IAxis;
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
/*     */ public class FixedAxis
/*     */   implements IAxis
/*     */ {
/*     */   private int bins;
/*     */   private double min;
/*     */   private double binWidth;
/*     */   private int xunder;
/*     */   private int xover;
/*     */   
/*     */   public FixedAxis(int paramInt, double paramDouble1, double paramDouble2)
/*     */   {
/*  25 */     if (paramInt < 1) throw new IllegalArgumentException("bins=" + paramInt);
/*  26 */     if (paramDouble2 <= paramDouble1) { throw new IllegalArgumentException("max <= min");
/*     */     }
/*     */     
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*  33 */     this.bins = paramInt;
/*  34 */     this.min = paramDouble1;
/*  35 */     this.binWidth = ((paramDouble2 - paramDouble1) / paramInt);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public double binCentre(int paramInt)
/*     */   {
/*  45 */     return this.min + this.binWidth * paramInt + this.binWidth / 2.0D;
/*     */   }
/*     */   
/*     */   public double binLowerEdge(int paramInt) {
/*  49 */     if (paramInt == -2) return Double.NEGATIVE_INFINITY;
/*  50 */     if (paramInt == -1) return upperEdge();
/*  51 */     return this.min + this.binWidth * paramInt;
/*     */   }
/*     */   
/*     */   public int bins() {
/*  55 */     return this.bins;
/*     */   }
/*     */   
/*     */   public double binUpperEdge(int paramInt) {
/*  59 */     if (paramInt == -2) return this.min;
/*  60 */     if (paramInt == -1) return Double.POSITIVE_INFINITY;
/*  61 */     return this.min + this.binWidth * (paramInt + 1);
/*     */   }
/*     */   
/*     */   public double binWidth(int paramInt) {
/*  65 */     return this.binWidth;
/*     */   }
/*     */   
/*     */   public int coordToIndex(double paramDouble) {
/*  69 */     if (paramDouble < this.min) return -2;
/*  70 */     int i = (int)Math.floor((paramDouble - this.min) / this.binWidth);
/*  71 */     if (i >= this.bins) { return -1;
/*     */     }
/*  73 */     return i;
/*     */   }
/*     */   
/*     */   public double lowerEdge() {
/*  77 */     return this.min;
/*     */   }
/*     */   
/*     */   public double upperEdge() {
/*  81 */     return this.min + this.binWidth * this.bins;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   int xgetBin(double paramDouble)
/*     */   {
/*  89 */     if (paramDouble < this.min) return this.xunder;
/*  90 */     int i = (int)Math.floor((paramDouble - this.min) / this.binWidth);
/*  91 */     if (i > this.bins) return this.xover;
/*  92 */     return i + 1;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   int xmap(int paramInt)
/*     */   {
/* 100 */     if (paramInt >= this.bins) throw new IllegalArgumentException("bin=" + paramInt);
/* 101 */     if (paramInt >= 0) return paramInt + 1;
/* 102 */     if (paramInt == -2) return this.xunder;
/* 103 */     if (paramInt == -1) return this.xover;
/* 104 */     throw new IllegalArgumentException("bin=" + paramInt);
/*     */   }
/*     */ }


/* Location:              /home/menick/Scrivania/ProcessMining/CNMining/CNMining.jar!/hep/aida/ref/FixedAxis.class
 * Java compiler version: 1 (45.3)
 * JD-Core Version:       0.7.1
 */