/*     */ package cern.jet.stat.quantile;
/*     */ 
/*     */ import cern.colt.PersistentObject;
/*     */ import cern.colt.function.DoubleProcedure;
/*     */ import cern.colt.list.AbstractDoubleList;
/*     */ import cern.colt.list.AbstractList;
/*     */ import cern.colt.list.DoubleArrayList;
/*     */ import cern.colt.list.ObjectArrayList;
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
/*     */ abstract class DoubleQuantileEstimator
/*     */   extends PersistentObject
/*     */   implements DoubleQuantileFinder
/*     */ {
/*     */   protected DoubleBufferSet bufferSet;
/*     */   protected DoubleBuffer currentBufferToFill;
/*     */   protected int totalElementsFilled;
/*     */   
/*     */   public void add(double paramDouble)
/*     */   {
/*  29 */     this.totalElementsFilled += 1;
/*  30 */     if (!sampleNextElement()) { return;
/*     */     }
/*     */     
/*     */ 
/*  34 */     if (this.currentBufferToFill == null) {
/*  35 */       if (this.bufferSet._getFirstEmptyBuffer() == null) collapse();
/*  36 */       newBuffer();
/*     */     }
/*     */     
/*  39 */     this.currentBufferToFill.add(paramDouble);
/*  40 */     if (this.currentBufferToFill.isFull()) { this.currentBufferToFill = null;
/*     */     }
/*     */   }
/*     */   
/*     */ 
/*     */   public void addAllOf(DoubleArrayList paramDoubleArrayList)
/*     */   {
/*  47 */     addAllOfFromTo(paramDoubleArrayList, 0, paramDoubleArrayList.size() - 1);
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
/*     */   public void addAllOfFromTo(DoubleArrayList paramDoubleArrayList, int paramInt1, int paramInt2)
/*     */   {
/*  64 */     double[] arrayOfDouble1 = paramDoubleArrayList.elements();
/*  65 */     int i = this.bufferSet.k();
/*  66 */     int j = i;
/*  67 */     double[] arrayOfDouble2 = null;
/*  68 */     if (this.currentBufferToFill != null) {
/*  69 */       arrayOfDouble2 = this.currentBufferToFill.values.elements();
/*  70 */       j = this.currentBufferToFill.size();
/*     */     }
/*     */     
/*  73 */     int k = paramInt1 - 1;
/*  74 */     do { if (sampleNextElement()) {
/*  75 */         if (j == i) {
/*  76 */           if (this.bufferSet._getFirstEmptyBuffer() == null) collapse();
/*  77 */           newBuffer();
/*  78 */           if (!this.currentBufferToFill.isAllocated) this.currentBufferToFill.allocate();
/*  79 */           this.currentBufferToFill.isSorted = false;
/*  80 */           arrayOfDouble2 = this.currentBufferToFill.values.elements();
/*  81 */           j = 0;
/*     */         }
/*     */         
/*  84 */         arrayOfDouble2[(j++)] = arrayOfDouble1[k];
/*  85 */         if (j == i) {
/*  86 */           this.currentBufferToFill.values.setSize(j);
/*  87 */           this.currentBufferToFill = null;
/*     */         }
/*     */       }
/*  73 */       k++; } while (k <= paramInt2);
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
/*  91 */     if (this.currentBufferToFill != null) {
/*  92 */       this.currentBufferToFill.values.setSize(j);
/*     */     }
/*     */     
/*  95 */     this.totalElementsFilled += paramInt2 - paramInt1 + 1;
/*     */   }
/*     */   
/*     */ 
/*     */   protected DoubleBuffer[] buffersToCollapse()
/*     */   {
/* 101 */     int i = this.bufferSet._getMinLevelOfFullOrPartialBuffers();
/* 102 */     return this.bufferSet._getFullOrPartialBuffersWithLevel(i);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */   public void clear()
/*     */   {
/* 109 */     this.totalElementsFilled = 0;
/* 110 */     this.currentBufferToFill = null;
/* 111 */     this.bufferSet.clear();
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   public Object clone()
/*     */   {
/* 119 */     DoubleQuantileEstimator localDoubleQuantileEstimator = (DoubleQuantileEstimator)super.clone();
/* 120 */     if (this.bufferSet != null) {
/* 121 */       localDoubleQuantileEstimator.bufferSet = ((DoubleBufferSet)localDoubleQuantileEstimator.bufferSet.clone());
/* 122 */       if (this.currentBufferToFill != null) {
/* 123 */         int i = new ObjectArrayList(this.bufferSet.buffers).indexOf(this.currentBufferToFill, true);
/* 124 */         localDoubleQuantileEstimator.currentBufferToFill = localDoubleQuantileEstimator.bufferSet.buffers[i];
/*     */       }
/*     */     }
/* 127 */     return localDoubleQuantileEstimator;
/*     */   }
/*     */   
/*     */ 
/*     */   protected void collapse()
/*     */   {
/* 133 */     DoubleBuffer[] arrayOfDoubleBuffer = buffersToCollapse();
/* 134 */     DoubleBuffer localDoubleBuffer = this.bufferSet.collapse(arrayOfDoubleBuffer);
/*     */     
/* 136 */     int i = arrayOfDoubleBuffer[0].level();
/* 137 */     localDoubleBuffer.level(i + 1);
/*     */     
/* 139 */     postCollapse(arrayOfDoubleBuffer);
/*     */   }
/*     */   
/*     */ 
/*     */   public boolean contains(double paramDouble)
/*     */   {
/* 145 */     return this.bufferSet.contains(paramDouble);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public boolean forEach(DoubleProcedure paramDoubleProcedure)
/*     */   {
/* 154 */     return this.bufferSet.forEach(paramDoubleProcedure);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */   public long memory()
/*     */   {
/* 161 */     return this.bufferSet.memory();
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   protected abstract void newBuffer();
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   public double phi(double paramDouble)
/*     */   {
/* 175 */     return this.bufferSet.phi(paramDouble);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */   protected abstract void postCollapse(DoubleBuffer[] paramArrayOfDoubleBuffer);
/*     */   
/*     */ 
/*     */   protected DoubleArrayList preProcessPhis(DoubleArrayList paramDoubleArrayList)
/*     */   {
/* 185 */     return paramDoubleArrayList;
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
/*     */   public DoubleArrayList quantileElements(DoubleArrayList paramDoubleArrayList)
/*     */   {
/* 204 */     paramDoubleArrayList = preProcessPhis(paramDoubleArrayList);
/*     */     
/* 206 */     long[] arrayOfLong = new long[paramDoubleArrayList.size()];
/* 207 */     long l = this.bufferSet.totalSize();
/* 208 */     int i = paramDoubleArrayList.size();
/* 209 */     do { arrayOfLong[i] = (Utils.epsilonCeiling(paramDoubleArrayList.get(i) * l) - 1L);i--;
/* 208 */     } while (i >= 0);
/*     */     
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/* 216 */     DoubleBuffer[] arrayOfDoubleBuffer = this.bufferSet._getFullOrPartialBuffers();
/* 217 */     double[] arrayOfDouble = new double[paramDoubleArrayList.size()];
/*     */     
/*     */ 
/* 220 */     return new DoubleArrayList(this.bufferSet.getValuesAtPositions(arrayOfDoubleBuffer, arrayOfLong));
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */   protected abstract boolean sampleNextElement();
/*     */   
/*     */ 
/*     */   protected void setUp(int paramInt1, int paramInt2)
/*     */   {
/* 230 */     if ((paramInt1 < 2) || (paramInt2 < 1)) {
/* 231 */       throw new IllegalArgumentException("Assertion: b>=2 && k>=1");
/*     */     }
/* 233 */     this.bufferSet = new DoubleBufferSet(paramInt1, paramInt2);
/* 234 */     clear();
/*     */   }
/*     */   
/*     */ 
/*     */   public long size()
/*     */   {
/* 240 */     return this.totalElementsFilled;
/*     */   }
/*     */   
/*     */ 
/*     */   public String toString()
/*     */   {
/* 246 */     String str = getClass().getName();
/* 247 */     str = str.substring(str.lastIndexOf('.') + 1);
/* 248 */     int i = this.bufferSet.b();
/* 249 */     int j = this.bufferSet.k();
/* 250 */     return str + "(mem=" + memory() + ", b=" + i + ", k=" + j + ", size=" + size() + ", totalSize=" + this.bufferSet.totalSize() + ")";
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */   public long totalMemory()
/*     */   {
/* 257 */     return this.bufferSet.b() * this.bufferSet.k();
/*     */   }
/*     */ }


/* Location:              /home/menick/Scrivania/ProcessMining/CNMining/CNMining.jar!/cern/jet/stat/quantile/DoubleQuantileEstimator.class
 * Java compiler version: 1 (45.3)
 * JD-Core Version:       0.7.1
 */