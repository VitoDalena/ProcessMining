/*     */ package hep.aida.bin;
/*     */ 
/*     */ import cern.colt.list.DoubleArrayList;
/*     */ import cern.jet.stat.Descriptive;
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
/*     */ public class StaticBin1D
/*     */   extends AbstractBin1D
/*     */ {
/*  31 */   protected int size = 0;
/*     */   
/*     */ 
/*  34 */   protected double min = 0.0D;
/*  35 */   protected double max = 0.0D;
/*  36 */   protected double sum = 0.0D;
/*  37 */   protected double sum_xx = 0.0D;
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
/*  52 */   protected static transient double[] arguments = new double[20];
/*     */   
/*     */ 
/*     */   public StaticBin1D()
/*     */   {
/*  57 */     clear();
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public synchronized void add(double paramDouble)
/*     */   {
/*  66 */     addAllOf(new DoubleArrayList(new double[] { paramDouble }));
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
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public synchronized void addAllOfFromTo(DoubleArrayList paramDoubleArrayList, int paramInt1, int paramInt2)
/*     */   {
/*  94 */     synchronized (arguments)
/*     */     {
/*  96 */       arguments[0] = this.min;
/*  97 */       arguments[1] = this.max;
/*  98 */       arguments[2] = this.sum;
/*  99 */       arguments[3] = this.sum_xx;
/*     */       
/* 101 */       Descriptive.incrementalUpdate(paramDoubleArrayList, paramInt1, paramInt2, arguments);
/*     */       
/*     */ 
/* 104 */       this.min = arguments[0];
/* 105 */       this.max = arguments[1];
/* 106 */       this.sum = arguments[2];
/* 107 */       this.sum_xx = arguments[3];
/*     */       
/* 109 */       this.size += paramInt2 - paramInt1 + 1;
/*     */     }
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */   public synchronized void clear()
/*     */   {
/* 117 */     clearAllMeasures();
/* 118 */     this.size = 0;
/*     */   }
/*     */   
/*     */ 
/*     */   protected void clearAllMeasures()
/*     */   {
/* 124 */     this.min = Double.POSITIVE_INFINITY;
/* 125 */     this.max = Double.NEGATIVE_INFINITY;
/* 126 */     this.sum = 0.0D;
/* 127 */     this.sum_xx = 0.0D;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public synchronized boolean isRebinnable()
/*     */   {
/* 137 */     return false;
/*     */   }
/*     */   
/*     */ 
/*     */   public synchronized double max()
/*     */   {
/* 143 */     return this.max;
/*     */   }
/*     */   
/*     */ 
/*     */   public synchronized double min()
/*     */   {
/* 149 */     return this.min;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   public synchronized int size()
/*     */   {
/* 157 */     return this.size;
/*     */   }
/*     */   
/*     */ 
/*     */   public synchronized double sum()
/*     */   {
/* 163 */     return this.sum;
/*     */   }
/*     */   
/*     */ 
/*     */   public synchronized double sumOfSquares()
/*     */   {
/* 169 */     return this.sum_xx;
/*     */   }
/*     */ }


/* Location:              /home/menick/Scrivania/ProcessMining/CNMining/CNMining.jar!/hep/aida/bin/StaticBin1D.class
 * Java compiler version: 1 (45.3)
 * JD-Core Version:       0.7.1
 */