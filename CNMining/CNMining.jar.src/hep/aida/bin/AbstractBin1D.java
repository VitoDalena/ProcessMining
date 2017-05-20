/*     */ package hep.aida.bin;
/*     */ 
/*     */ import cern.colt.buffer.DoubleBuffer;
/*     */ import cern.colt.buffer.DoubleBufferConsumer;
/*     */ import cern.colt.list.AbstractDoubleList;
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
/*     */ public abstract class AbstractBin1D
/*     */   extends AbstractBin
/*     */   implements DoubleBufferConsumer
/*     */ {
/*     */   public abstract void add(double paramDouble);
/*     */   
/*     */   public final synchronized void addAllOf(DoubleArrayList paramDoubleArrayList)
/*     */   {
/*  33 */     addAllOfFromTo(paramDoubleArrayList, 0, paramDoubleArrayList.size() - 1);
/*     */   }
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
/*  45 */     for (int i = paramInt1; i <= paramInt2; i++) { add(paramDoubleArrayList.getQuick(i));
/*     */     }
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public synchronized DoubleBuffer buffered(int paramInt)
/*     */   {
/*  55 */     return new DoubleBuffer(this, paramInt);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   public String compareWith(AbstractBin1D paramAbstractBin1D)
/*     */   {
/*  63 */     StringBuffer localStringBuffer = new StringBuffer();
/*  64 */     localStringBuffer.append("\nDifferences [percent]");
/*  65 */     localStringBuffer.append("\nSize: " + relError(size(), paramAbstractBin1D.size()) + " %");
/*  66 */     localStringBuffer.append("\nSum: " + relError(sum(), paramAbstractBin1D.sum()) + " %");
/*  67 */     localStringBuffer.append("\nSumOfSquares: " + relError(sumOfSquares(), paramAbstractBin1D.sumOfSquares()) + " %");
/*  68 */     localStringBuffer.append("\nMin: " + relError(min(), paramAbstractBin1D.min()) + " %");
/*  69 */     localStringBuffer.append("\nMax: " + relError(max(), paramAbstractBin1D.max()) + " %");
/*  70 */     localStringBuffer.append("\nMean: " + relError(mean(), paramAbstractBin1D.mean()) + " %");
/*  71 */     localStringBuffer.append("\nRMS: " + relError(rms(), paramAbstractBin1D.rms()) + " %");
/*  72 */     localStringBuffer.append("\nVariance: " + relError(variance(), paramAbstractBin1D.variance()) + " %");
/*  73 */     localStringBuffer.append("\nStandard deviation: " + relError(standardDeviation(), paramAbstractBin1D.standardDeviation()) + " %");
/*  74 */     localStringBuffer.append("\nStandard error: " + relError(standardError(), paramAbstractBin1D.standardError()) + " %");
/*  75 */     localStringBuffer.append("\n");
/*  76 */     return localStringBuffer.toString();
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */   public boolean equals(Object paramObject)
/*     */   {
/*  83 */     if (!(paramObject instanceof AbstractBin1D)) return false;
/*  84 */     AbstractBin1D localAbstractBin1D = (AbstractBin1D)paramObject;
/*  85 */     return (size() == localAbstractBin1D.size()) && (min() == localAbstractBin1D.min()) && (max() == localAbstractBin1D.max()) && (sum() == localAbstractBin1D.sum()) && (sumOfSquares() == localAbstractBin1D.sumOfSquares());
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */   public abstract double max();
/*     */   
/*     */ 
/*     */ 
/*     */   public synchronized double mean()
/*     */   {
/*  96 */     return sum() / size();
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */   public abstract double min();
/*     */   
/*     */ 
/*     */   protected double relError(double paramDouble1, double paramDouble2)
/*     */   {
/* 106 */     return 100.0D * (1.0D - paramDouble1 / paramDouble2);
/*     */   }
/*     */   
/*     */ 
/*     */   public synchronized double rms()
/*     */   {
/* 112 */     return Descriptive.rms(size(), sumOfSquares());
/*     */   }
/*     */   
/*     */ 
/*     */   public synchronized double standardDeviation()
/*     */   {
/* 118 */     return Math.sqrt(variance());
/*     */   }
/*     */   
/*     */ 
/*     */   public synchronized double standardError()
/*     */   {
/* 124 */     return Descriptive.standardError(size(), variance());
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */   public abstract double sum();
/*     */   
/*     */ 
/*     */ 
/*     */   public abstract double sumOfSquares();
/*     */   
/*     */ 
/*     */   public synchronized String toString()
/*     */   {
/* 138 */     StringBuffer localStringBuffer = new StringBuffer();
/* 139 */     localStringBuffer.append(getClass().getName());
/* 140 */     localStringBuffer.append("\n-------------");
/* 141 */     localStringBuffer.append("\nSize: " + size());
/* 142 */     localStringBuffer.append("\nSum: " + sum());
/* 143 */     localStringBuffer.append("\nSumOfSquares: " + sumOfSquares());
/* 144 */     localStringBuffer.append("\nMin: " + min());
/* 145 */     localStringBuffer.append("\nMax: " + max());
/* 146 */     localStringBuffer.append("\nMean: " + mean());
/* 147 */     localStringBuffer.append("\nRMS: " + rms());
/* 148 */     localStringBuffer.append("\nVariance: " + variance());
/* 149 */     localStringBuffer.append("\nStandard deviation: " + standardDeviation());
/* 150 */     localStringBuffer.append("\nStandard error: " + standardError());
/*     */     
/*     */ 
/*     */ 
/*     */ 
/* 155 */     localStringBuffer.append("\n");
/* 156 */     return localStringBuffer.toString();
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   public synchronized void trimToSize() {}
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   public synchronized double variance()
/*     */   {
/* 169 */     return Descriptive.sampleVariance(size(), sum(), sumOfSquares());
/*     */   }
/*     */ }


/* Location:              /home/menick/Scrivania/ProcessMining/CNMining/CNMining.jar!/hep/aida/bin/AbstractBin1D.class
 * Java compiler version: 1 (45.3)
 * JD-Core Version:       0.7.1
 */