/*     */ package cern.jet.stat.quantile;
/*     */ 
/*     */ import cern.colt.list.AbstractDoubleList;
/*     */ import cern.colt.list.DoubleArrayList;
/*     */ import cern.jet.math.Arithmetic;
/*     */ import cern.jet.random.sampling.RandomSamplingAssistant;
/*     */ import edu.cornell.lassp.houle.RngPack.RandomElement;
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
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ class KnownDoubleQuantileEstimator
/*     */   extends DoubleQuantileEstimator
/*     */ {
/*     */   protected double beta;
/*     */   protected boolean weHadMoreThanOneEmptyBuffer;
/*     */   protected RandomSamplingAssistant samplingAssistant;
/*     */   protected double samplingRate;
/*     */   protected long N;
/*     */   
/*     */   public KnownDoubleQuantileEstimator(int paramInt1, int paramInt2, long paramLong, double paramDouble, RandomElement paramRandomElement)
/*     */   {
/*  57 */     this.samplingRate = paramDouble;
/*  58 */     this.N = paramLong;
/*     */     
/*  60 */     if (this.samplingRate <= 1.0D) {
/*  61 */       this.samplingAssistant = null;
/*     */     }
/*     */     else {
/*  64 */       this.samplingAssistant = new RandomSamplingAssistant(Arithmetic.floor(paramLong / paramDouble), paramLong, paramRandomElement);
/*     */     }
/*     */     
/*  67 */     setUp(paramInt1, paramInt2);
/*  68 */     clear();
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */   protected void addInfinities(int paramInt, DoubleBuffer paramDoubleBuffer)
/*     */   {
/*  75 */     RandomSamplingAssistant localRandomSamplingAssistant = this.samplingAssistant;
/*  76 */     this.samplingAssistant = null;
/*     */     
/*     */ 
/*  79 */     int i = 1;
/*  80 */     for (int j = 0; j < paramInt; j++) {
/*  81 */       if (i != 0) paramDoubleBuffer.values.add(Double.MAX_VALUE); else {
/*  82 */         paramDoubleBuffer.values.add(-1.7976931348623157E308D);
/*     */       }
/*     */       
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*  89 */       i = i == 0 ? 1 : 0;
/*     */     }
/*     */     
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*  96 */     this.samplingAssistant = localRandomSamplingAssistant;
/*     */   }
/*     */   
/*     */ 
/*     */   protected DoubleBuffer[] buffersToCollapse()
/*     */   {
/* 102 */     int i = this.bufferSet._getMinLevelOfFullOrPartialBuffers();
/* 103 */     return this.bufferSet._getFullOrPartialBuffersWithLevel(i);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */   public void clear()
/*     */   {
/* 110 */     super.clear();
/* 111 */     this.beta = 1.0D;
/* 112 */     this.weHadMoreThanOneEmptyBuffer = false;
/*     */     
/*     */ 
/* 115 */     RandomSamplingAssistant localRandomSamplingAssistant = this.samplingAssistant;
/* 116 */     if (localRandomSamplingAssistant != null) {
/* 117 */       this.samplingAssistant = new RandomSamplingAssistant(Arithmetic.floor(this.N / this.samplingRate), this.N, localRandomSamplingAssistant.getRandomGenerator());
/*     */     }
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   public Object clone()
/*     */   {
/* 126 */     KnownDoubleQuantileEstimator localKnownDoubleQuantileEstimator = (KnownDoubleQuantileEstimator)super.clone();
/* 127 */     if (this.samplingAssistant != null) localKnownDoubleQuantileEstimator.samplingAssistant = ((RandomSamplingAssistant)localKnownDoubleQuantileEstimator.samplingAssistant.clone());
/* 128 */     return localKnownDoubleQuantileEstimator;
/*     */   }
/*     */   
/*     */ 
/*     */   protected void newBuffer()
/*     */   {
/* 134 */     int i = this.bufferSet._getNumberOfEmptyBuffers();
/*     */     
/* 136 */     if (i == 0) { throw new RuntimeException("Oops, no empty buffer.");
/*     */     }
/* 138 */     this.currentBufferToFill = this.bufferSet._getFirstEmptyBuffer();
/* 139 */     if ((i == 1) && (!this.weHadMoreThanOneEmptyBuffer)) {
/* 140 */       this.currentBufferToFill.level(this.bufferSet._getMinLevelOfFullOrPartialBuffers());
/*     */     }
/*     */     else {
/* 143 */       this.weHadMoreThanOneEmptyBuffer = true;
/* 144 */       this.currentBufferToFill.level(0);
/*     */     }
/*     */     
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/* 152 */     this.currentBufferToFill.weight(1);
/*     */   }
/*     */   
/*     */ 
/*     */   protected void postCollapse(DoubleBuffer[] paramArrayOfDoubleBuffer)
/*     */   {
/* 158 */     this.weHadMoreThanOneEmptyBuffer = false;
/*     */   }
/*     */   
/*     */   protected DoubleArrayList preProcessPhis(DoubleArrayList paramDoubleArrayList)
/*     */   {
/* 163 */     if (this.beta > 1.0D) {
/* 164 */       paramDoubleArrayList = paramDoubleArrayList.copy();
/* 165 */       int i = paramDoubleArrayList.size();
/* 166 */       do { paramDoubleArrayList.set(i, (2.0D * paramDoubleArrayList.get(i) + this.beta - 1.0D) / (2.0D * this.beta));i--;
/* 165 */       } while (i >= 0);
/*     */     }
/*     */     
/*     */ 
/* 169 */     return paramDoubleArrayList;
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
/* 188 */     DoubleBuffer localDoubleBuffer = this.bufferSet._getPartialBuffer();
/* 189 */     int i = 0;
/* 190 */     if (localDoubleBuffer != null) {
/* 191 */       i = this.bufferSet.k() - localDoubleBuffer.size();
/* 192 */       if (i <= 0) { throw new RuntimeException("Oops! illegal missing values.");
/*     */       }
/*     */       
/* 195 */       addInfinities(i, localDoubleBuffer);
/*     */       
/*     */ 
/* 198 */       this.beta = ((this.totalElementsFilled + i) / this.totalElementsFilled);
/*     */     }
/*     */     else {
/* 201 */       this.beta = 1.0D;
/*     */     }
/*     */     
/* 204 */     DoubleArrayList localDoubleArrayList = super.quantileElements(paramDoubleArrayList);
/*     */     
/*     */ 
/*     */ 
/* 208 */     if (localDoubleBuffer != null) { removeInfinitiesFrom(i, localDoubleBuffer);
/*     */     }
/*     */     
/* 211 */     return localDoubleArrayList;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   protected void removeInfinitiesFrom(int paramInt, DoubleBuffer paramDoubleBuffer)
/*     */   {
/* 223 */     int i = 0;
/* 224 */     int j = 0;
/*     */     
/* 226 */     int k = 1;
/* 227 */     for (int m = 0; m < paramInt; m++) {
/* 228 */       if (k != 0) i++; else
/* 229 */         j++;
/* 230 */       k = k == 0 ? 1 : 0;
/*     */     }
/*     */     
/* 233 */     paramDoubleBuffer.values.removeFromTo(paramDoubleBuffer.size() - i, paramDoubleBuffer.size() - 1);
/* 234 */     paramDoubleBuffer.values.removeFromTo(0, j - 1);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */   protected boolean sampleNextElement()
/*     */   {
/* 241 */     if (this.samplingAssistant == null) { return true;
/*     */     }
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
/* 259 */     return this.samplingAssistant.sampleNextElement();
/*     */   }
/*     */ }


/* Location:              /home/menick/Scrivania/ProcessMining/CNMining/CNMining.jar!/cern/jet/stat/quantile/KnownDoubleQuantileEstimator.class
 * Java compiler version: 1 (45.3)
 * JD-Core Version:       0.7.1
 */