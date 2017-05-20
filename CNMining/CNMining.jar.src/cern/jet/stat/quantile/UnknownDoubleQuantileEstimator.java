/*     */ package cern.jet.stat.quantile;
/*     */ 
/*     */ import cern.colt.list.AbstractDoubleList;
/*     */ import cern.colt.list.DoubleArrayList;
/*     */ import cern.colt.list.ObjectArrayList;
/*     */ import cern.jet.random.sampling.WeightedRandomSampler;
/*     */ import edu.cornell.lassp.houle.RngPack.RandomElement;
/*     */ import java.util.Comparator;
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
/*     */ class UnknownDoubleQuantileEstimator
/*     */   extends DoubleQuantileEstimator
/*     */ {
/*     */   protected int currentTreeHeight;
/*     */   protected final int treeHeightStartingSampling;
/*     */   protected WeightedRandomSampler sampler;
/*     */   protected double precomputeEpsilon;
/*     */   
/*     */   public UnknownDoubleQuantileEstimator(int paramInt1, int paramInt2, int paramInt3, double paramDouble, RandomElement paramRandomElement)
/*     */   {
/*  53 */     this.sampler = new WeightedRandomSampler(1, paramRandomElement);
/*  54 */     setUp(paramInt1, paramInt2);
/*  55 */     this.treeHeightStartingSampling = paramInt3;
/*  56 */     this.precomputeEpsilon = paramDouble;
/*  57 */     clear();
/*     */   }
/*     */   
/*     */ 
/*     */   protected DoubleBuffer[] buffersToCollapse()
/*     */   {
/*  63 */     DoubleBuffer[] arrayOfDoubleBuffer = this.bufferSet._getFullOrPartialBuffers();
/*     */     
/*  65 */     sortAscendingByLevel(arrayOfDoubleBuffer);
/*     */     
/*     */ 
/*  68 */     int i = arrayOfDoubleBuffer[1].level();
/*  69 */     if (arrayOfDoubleBuffer[0].level() < i) {
/*  70 */       arrayOfDoubleBuffer[0].level(i);
/*     */     }
/*     */     
/*  73 */     return this.bufferSet._getFullOrPartialBuffersWithLevel(i);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */   public synchronized void clear()
/*     */   {
/*  80 */     super.clear();
/*  81 */     this.currentTreeHeight = 1;
/*  82 */     this.sampler.setWeight(1);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   public Object clone()
/*     */   {
/*  90 */     UnknownDoubleQuantileEstimator localUnknownDoubleQuantileEstimator = (UnknownDoubleQuantileEstimator)super.clone();
/*  91 */     if (this.sampler != null) localUnknownDoubleQuantileEstimator.sampler = ((WeightedRandomSampler)localUnknownDoubleQuantileEstimator.sampler.clone());
/*  92 */     return localUnknownDoubleQuantileEstimator;
/*     */   }
/*     */   
/*     */ 
/*     */   protected void newBuffer()
/*     */   {
/*  98 */     this.currentBufferToFill = this.bufferSet._getFirstEmptyBuffer();
/*  99 */     if (this.currentBufferToFill == null) { throw new RuntimeException("Oops, no empty buffer.");
/*     */     }
/* 101 */     this.currentBufferToFill.level(this.currentTreeHeight - 1);
/* 102 */     this.currentBufferToFill.weight(this.sampler.getWeight());
/*     */   }
/*     */   
/*     */ 
/*     */   protected void postCollapse(DoubleBuffer[] paramArrayOfDoubleBuffer)
/*     */   {
/* 108 */     if (paramArrayOfDoubleBuffer.length == this.bufferSet.b()) {
/* 109 */       this.currentTreeHeight += 1;
/* 110 */       if (this.currentTreeHeight >= this.treeHeightStartingSampling) {
/* 111 */         this.sampler.setWeight(this.sampler.getWeight() * 2);
/*     */       }
/*     */     }
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   public DoubleArrayList quantileElements(DoubleArrayList paramDoubleArrayList)
/*     */   {
/* 121 */     if (this.precomputeEpsilon <= 0.0D) { return super.quantileElements(paramDoubleArrayList);
/*     */     }
/* 123 */     int i = (int)Utils.epsilonCeiling(1.0D / this.precomputeEpsilon);
/*     */     
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/* 133 */     paramDoubleArrayList = paramDoubleArrayList.copy();
/* 134 */     double d1 = this.precomputeEpsilon;
/* 135 */     int j = paramDoubleArrayList.size();
/* 136 */     do { double d2 = paramDoubleArrayList.get(j);
/* 137 */       int k = (int)Math.round((2.0D * d2 / d1 - 1.0D) / 2.0D);
/* 138 */       k = Math.min(i - 1, Math.max(0, k));
/* 139 */       double d3 = d1 / 2.0D * (1 + 2 * k);
/* 140 */       paramDoubleArrayList.set(j, d3);j--;
/* 135 */     } while (j >= 0);
/*     */     
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/* 143 */     return super.quantileElements(paramDoubleArrayList);
/*     */   }
/*     */   
/*     */ 
/*     */   protected boolean sampleNextElement()
/*     */   {
/* 149 */     return this.sampler.sampleNextElement();
/*     */   }
/*     */   
/*     */ 
/*     */   protected static void sortAscendingByLevel(DoubleBuffer[] paramArrayOfDoubleBuffer)
/*     */   {
/* 155 */     new ObjectArrayList(paramArrayOfDoubleBuffer).quickSortFromTo(0, paramArrayOfDoubleBuffer.length - 1, new Comparator()
/*     */     {
/*     */       public int compare(Object paramAnonymousObject1, Object paramAnonymousObject2) {
/* 158 */         int i = ((DoubleBuffer)paramAnonymousObject1).level();
/* 159 */         int j = ((DoubleBuffer)paramAnonymousObject2).level();
/* 160 */         return i == j ? 0 : i < j ? -1 : 1;
/*     */       }
/*     */     });
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */   public String toString()
/*     */   {
/* 169 */     StringBuffer localStringBuffer = new StringBuffer(super.toString());
/* 170 */     localStringBuffer.setLength(localStringBuffer.length() - 1);
/* 171 */     return localStringBuffer + ", h=" + this.currentTreeHeight + ", hStartSampling=" + this.treeHeightStartingSampling + ", precomputeEpsilon=" + this.precomputeEpsilon + ")";
/*     */   }
/*     */ }


/* Location:              /home/menick/Scrivania/ProcessMining/CNMining/CNMining.jar!/cern/jet/stat/quantile/UnknownDoubleQuantileEstimator.class
 * Java compiler version: 1 (45.3)
 * JD-Core Version:       0.7.1
 */