/*     */ package hep.aida.bin;
/*     */ 
/*     */ import cern.colt.buffer.DoubleBuffer;
/*     */ import cern.colt.function.DoubleDoubleFunction;
/*     */ import cern.colt.function.DoubleFunction;
/*     */ import cern.colt.list.AbstractDoubleList;
/*     */ import cern.colt.list.AbstractList;
/*     */ import cern.colt.list.DoubleArrayList;
/*     */ import cern.colt.list.IntArrayList;
/*     */ import cern.colt.map.AbstractDoubleIntMap;
/*     */ import cern.colt.map.OpenDoubleIntHashMap;
/*     */ import cern.jet.random.AbstractDistribution;
/*     */ import cern.jet.random.Uniform;
/*     */ import cern.jet.random.sampling.RandomSamplingAssistant;
/*     */ import cern.jet.stat.Descriptive;
/*     */ import edu.cornell.lassp.houle.RngPack.RandomElement;
/*     */ import jal.DOUBLE.Sorting;
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
/*     */ public class DynamicBin1D
/*     */   extends QuantileBin1D
/*     */ {
/*  40 */   protected DoubleArrayList elements = null;
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*  45 */   protected DoubleArrayList sortedElements = null;
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*  50 */   protected boolean fixedOrder = false;
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*  58 */   protected boolean isSorted = true;
/*  59 */   protected boolean isIncrementalStatValid = true;
/*     */   
/*     */ 
/*     */ 
/*  63 */   protected boolean isSumOfInversionsValid = true;
/*  64 */   protected boolean isSumOfLogarithmsValid = true;
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public DynamicBin1D()
/*     */   {
/*  72 */     clear();
/*  73 */     this.elements = new DoubleArrayList();
/*  74 */     this.sortedElements = new DoubleArrayList(0);
/*  75 */     this.fixedOrder = false;
/*  76 */     this.hasSumOfLogarithms = true;
/*  77 */     this.hasSumOfInversions = true;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   public synchronized void add(double paramDouble)
/*     */   {
/*  85 */     this.elements.add(paramDouble);
/*  86 */     invalidateAll();
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public synchronized void addAllOfFromTo(DoubleArrayList paramDoubleArrayList, int paramInt1, int paramInt2)
/*     */   {
/*  97 */     this.elements.addAllOfFromTo(paramDoubleArrayList, paramInt1, paramInt2);
/*  98 */     invalidateAll();
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
/*     */   public synchronized double aggregate(DoubleDoubleFunction paramDoubleDoubleFunction, DoubleFunction paramDoubleFunction)
/*     */   {
/* 121 */     int i = size();
/* 122 */     if (i == 0) return NaN.0D;
/* 123 */     double d = paramDoubleFunction.apply(this.elements.getQuick(i - 1));
/* 124 */     int j = i - 1;
/* 125 */     do { d = paramDoubleDoubleFunction.apply(d, paramDoubleFunction.apply(this.elements.getQuick(j)));j--;
/* 124 */     } while (j >= 0);
/*     */     
/*     */ 
/* 127 */     return d;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */   public synchronized void clear()
/*     */   {
/* 134 */     super.clear();
/*     */     
/* 136 */     if (this.elements != null) this.elements.clear();
/* 137 */     if (this.sortedElements != null) { this.sortedElements.clear();
/*     */     }
/* 139 */     validateAll();
/*     */   }
/*     */   
/*     */ 
/*     */   protected void clearAllMeasures()
/*     */   {
/* 145 */     super.clearAllMeasures();
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public synchronized Object clone()
/*     */   {
/* 156 */     DynamicBin1D localDynamicBin1D = (DynamicBin1D)super.clone();
/* 157 */     if (this.elements != null) localDynamicBin1D.elements = localDynamicBin1D.elements.copy();
/* 158 */     if (this.sortedElements != null) localDynamicBin1D.sortedElements = localDynamicBin1D.sortedElements.copy();
/* 159 */     return localDynamicBin1D;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public synchronized double correlation(DynamicBin1D paramDynamicBin1D)
/*     */   {
/* 171 */     synchronized (paramDynamicBin1D) {
/* 172 */       double d = covariance(paramDynamicBin1D) / (standardDeviation() * paramDynamicBin1D.standardDeviation());return d;
/*     */     }
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public synchronized double covariance(DynamicBin1D paramDynamicBin1D)
/*     */   {
/* 183 */     synchronized (paramDynamicBin1D) {
/* 184 */       if (size() != paramDynamicBin1D.size()) throw new IllegalArgumentException("both bins must have same size");
/* 185 */       double d1 = 0.0D;
/* 186 */       int i = size();
/* 187 */       do { d1 += this.elements.getQuick(i) * paramDynamicBin1D.elements.getQuick(i);i--;
/* 186 */       } while (i >= 0);
/*     */       
/*     */ 
/*     */ 
/* 190 */       double d2 = (d1 - sum() * paramDynamicBin1D.sum() / size()) / size();
/* 191 */       double d3 = d2;return d3;
/*     */     }
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public synchronized DoubleArrayList elements()
/*     */   {
/* 201 */     return elements_unsafe().copy();
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
/*     */   protected synchronized DoubleArrayList elements_unsafe()
/*     */   {
/* 229 */     return this.elements;
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
/*     */   public synchronized boolean equals(Object paramObject)
/*     */   {
/* 242 */     if (!(paramObject instanceof DynamicBin1D)) return false;
/* 243 */     if (!super.equals(paramObject)) { return false;
/*     */     }
/* 245 */     DynamicBin1D localDynamicBin1D1 = (DynamicBin1D)paramObject;
/* 246 */     double[] arrayOfDouble1 = sortedElements_unsafe().elements();
/* 247 */     synchronized (localDynamicBin1D1) {
/* 248 */       double[] arrayOfDouble2 = localDynamicBin1D1.sortedElements_unsafe().elements();
/* 249 */       int i = size();
/* 250 */       boolean bool = (Sorting.includes(arrayOfDouble1, arrayOfDouble2, 0, i, 0, i)) && (Sorting.includes(arrayOfDouble2, arrayOfDouble1, 0, i, 0, i));return bool;
/*     */     }
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
/*     */   public synchronized void frequencies(DoubleArrayList paramDoubleArrayList, IntArrayList paramIntArrayList)
/*     */   {
/* 271 */     Descriptive.frequencies(sortedElements_unsafe(), paramDoubleArrayList, paramIntArrayList);
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
/*     */   private synchronized AbstractDoubleIntMap frequencyMap()
/*     */   {
/* 286 */     OpenDoubleIntHashMap localOpenDoubleIntHashMap = new OpenDoubleIntHashMap();
/*     */     
/* 288 */     int i = size();
/* 289 */     do { double d = this.elements.getQuick(i);
/*     */       
/*     */ 
/* 292 */       localOpenDoubleIntHashMap.put(d, 1 + localOpenDoubleIntHashMap.get(d));i--;
/* 288 */     } while (i >= 0);
/*     */     
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/* 298 */     return localOpenDoubleIntHashMap;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   public int getMaxOrderForSumOfPowers()
/*     */   {
/* 306 */     return Integer.MAX_VALUE;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   public int getMinOrderForSumOfPowers()
/*     */   {
/* 314 */     return Integer.MIN_VALUE;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   protected void invalidateAll()
/*     */   {
/* 322 */     this.isSorted = false;
/* 323 */     this.isIncrementalStatValid = false;
/*     */     
/*     */ 
/*     */ 
/*     */ 
/* 328 */     this.isSumOfInversionsValid = false;
/* 329 */     this.isSumOfLogarithmsValid = false;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public synchronized boolean isRebinnable()
/*     */   {
/* 339 */     return true;
/*     */   }
/*     */   
/*     */ 
/*     */   public synchronized double max()
/*     */   {
/* 345 */     if (!this.isIncrementalStatValid) updateIncrementalStats();
/* 346 */     return this.max;
/*     */   }
/*     */   
/*     */ 
/*     */   public synchronized double min()
/*     */   {
/* 352 */     if (!this.isIncrementalStatValid) updateIncrementalStats();
/* 353 */     return this.min;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public synchronized double moment(int paramInt, double paramDouble)
/*     */   {
/* 363 */     return Descriptive.moment(this.elements, paramInt, paramDouble);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */   public synchronized double quantile(double paramDouble)
/*     */   {
/* 370 */     return Descriptive.quantile(sortedElements_unsafe(), paramDouble);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public synchronized double quantileInverse(double paramDouble)
/*     */   {
/* 380 */     return Descriptive.quantileInverse(sortedElements_unsafe(), paramDouble);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public DoubleArrayList quantiles(DoubleArrayList paramDoubleArrayList)
/*     */   {
/* 389 */     return Descriptive.quantiles(sortedElements_unsafe(), paramDoubleArrayList);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public synchronized boolean removeAllOf(DoubleArrayList paramDoubleArrayList)
/*     */   {
/* 398 */     boolean bool = this.elements.removeAll(paramDoubleArrayList);
/* 399 */     if (bool) {
/* 400 */       clearAllMeasures();
/* 401 */       invalidateAll();
/* 402 */       this.size = 0;
/* 403 */       if (this.fixedOrder) {
/* 404 */         this.sortedElements.removeAll(paramDoubleArrayList);
/* 405 */         this.isSorted = true;
/*     */       }
/*     */     }
/* 408 */     return bool;
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
/*     */   public synchronized void sample(int paramInt, boolean paramBoolean, RandomElement paramRandomElement, DoubleBuffer paramDoubleBuffer)
/*     */   {
/* 423 */     if (paramRandomElement == null) paramRandomElement = AbstractDistribution.makeDefaultGenerator();
/* 424 */     paramDoubleBuffer.clear();
/*     */     Object localObject;
/* 426 */     int i; if (!paramBoolean) {
/* 427 */       if (paramInt > size()) throw new IllegalArgumentException("n must be less than or equal to size()");
/* 428 */       localObject = new RandomSamplingAssistant(paramInt, size(), paramRandomElement);
/* 429 */       i = paramInt;
/* 430 */       do { if (((RandomSamplingAssistant)localObject).sampleNextElement()) paramDoubleBuffer.add(this.elements.getQuick(i));
/* 429 */         i--; } while (i >= 0);
/*     */ 
/*     */     }
/*     */     else
/*     */     {
/* 434 */       localObject = new Uniform(paramRandomElement);
/* 435 */       i = size();
/* 436 */       int j = paramInt;
/* 437 */       do { paramDoubleBuffer.add(this.elements.getQuick(((Uniform)localObject).nextIntFromTo(0, i - 1)));j--;
/* 436 */       } while (j >= 0);
/*     */       
/*     */ 
/* 439 */       paramDoubleBuffer.flush();
/*     */     }
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
/*     */   public synchronized DynamicBin1D sampleBootstrap(DynamicBin1D paramDynamicBin1D, int paramInt, RandomElement paramRandomElement, BinBinFunction1D paramBinBinFunction1D)
/*     */   {
/* 566 */     if (paramRandomElement == null) { paramRandomElement = AbstractDistribution.makeDefaultGenerator();
/*     */     }
/*     */     
/* 569 */     int i = 1000;
/* 570 */     int j = size();
/* 571 */     int k = paramDynamicBin1D.size();
/*     */     
/*     */ 
/* 574 */     DynamicBin1D localDynamicBin1D1 = new DynamicBin1D();
/* 575 */     DoubleBuffer localDoubleBuffer1 = localDynamicBin1D1.buffered(Math.min(i, j));
/*     */     
/* 577 */     DynamicBin1D localDynamicBin1D2 = new DynamicBin1D();
/* 578 */     DoubleBuffer localDoubleBuffer2 = localDynamicBin1D2.buffered(Math.min(i, k));
/*     */     
/* 580 */     DynamicBin1D localDynamicBin1D3 = new DynamicBin1D();
/* 581 */     DoubleBuffer localDoubleBuffer3 = localDynamicBin1D3.buffered(Math.min(i, paramInt));
/*     */     
/*     */ 
/* 584 */     int m = paramInt;
/* 585 */     do { localDynamicBin1D1.clear();
/* 586 */       localDynamicBin1D2.clear();
/*     */       
/* 588 */       sample(j, true, paramRandomElement, localDoubleBuffer1);
/* 589 */       paramDynamicBin1D.sample(k, true, paramRandomElement, localDoubleBuffer2);
/*     */       
/* 591 */       localDoubleBuffer3.add(paramBinBinFunction1D.apply(localDynamicBin1D1, localDynamicBin1D2));m--;
/* 584 */     } while (m >= 0);
/*     */     
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/* 593 */     localDoubleBuffer3.flush();
/* 594 */     return localDynamicBin1D3;
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
/*     */   public void setFixedOrder(boolean paramBoolean)
/*     */   {
/* 609 */     this.fixedOrder = paramBoolean;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   public synchronized int size()
/*     */   {
/* 617 */     return this.elements.size();
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   protected void sort()
/*     */   {
/* 626 */     if (!this.isSorted) {
/* 627 */       if (this.fixedOrder) {
/* 628 */         this.sortedElements.clear();
/* 629 */         this.sortedElements.addAllOfFromTo(this.elements, 0, this.elements.size() - 1);
/* 630 */         this.sortedElements.sort();
/*     */ 
/*     */ 
/*     */       }
/*     */       else
/*     */       {
/*     */ 
/*     */ 
/* 638 */         updateIncrementalStats();
/* 639 */         invalidateAll();
/*     */         
/* 641 */         this.elements.sort();
/* 642 */         this.isIncrementalStatValid = true;
/*     */       }
/* 644 */       this.isSorted = true;
/*     */     }
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public synchronized DoubleArrayList sortedElements()
/*     */   {
/* 655 */     return sortedElements_unsafe().copy();
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
/*     */   protected synchronized DoubleArrayList sortedElements_unsafe()
/*     */   {
/* 681 */     sort();
/* 682 */     if (this.fixedOrder) return this.sortedElements;
/* 683 */     return this.elements;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */   public synchronized void standardize(double paramDouble1, double paramDouble2)
/*     */   {
/* 690 */     Descriptive.standardize(this.elements, paramDouble1, paramDouble2);
/* 691 */     clearAllMeasures();
/* 692 */     invalidateAll();
/* 693 */     this.size = 0;
/*     */   }
/*     */   
/*     */ 
/*     */   public synchronized double sum()
/*     */   {
/* 699 */     if (!this.isIncrementalStatValid) updateIncrementalStats();
/* 700 */     return this.sum;
/*     */   }
/*     */   
/*     */ 
/*     */   public synchronized double sumOfInversions()
/*     */   {
/* 706 */     if (!this.isSumOfInversionsValid) updateSumOfInversions();
/* 707 */     return this.sumOfInversions;
/*     */   }
/*     */   
/*     */ 
/*     */   public synchronized double sumOfLogarithms()
/*     */   {
/* 713 */     if (!this.isSumOfLogarithmsValid) updateSumOfLogarithms();
/* 714 */     return this.sumOfLogarithms;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public synchronized double sumOfPowers(int paramInt)
/*     */   {
/* 723 */     if ((paramInt >= -1) && (paramInt <= 2)) { return super.sumOfPowers(paramInt);
/*     */     }
/* 725 */     return Descriptive.sumOfPowers(this.elements, paramInt);
/*     */   }
/*     */   
/*     */ 
/*     */   public synchronized double sumOfSquares()
/*     */   {
/* 731 */     if (!this.isIncrementalStatValid) updateIncrementalStats();
/* 732 */     return this.sum_xx;
/*     */   }
/*     */   
/*     */ 
/*     */   public synchronized String toString()
/*     */   {
/* 738 */     StringBuffer localStringBuffer = new StringBuffer(super.toString());
/* 739 */     DoubleArrayList localDoubleArrayList = new DoubleArrayList();
/* 740 */     IntArrayList localIntArrayList = new IntArrayList();
/* 741 */     frequencies(localDoubleArrayList, localIntArrayList);
/* 742 */     if (localDoubleArrayList.size() < 100) {
/* 743 */       localStringBuffer.append("Distinct elements: " + localDoubleArrayList + "\n");
/* 744 */       localStringBuffer.append("Frequencies: " + localIntArrayList + "\n");
/*     */     }
/*     */     else {
/* 747 */       localStringBuffer.append("Distinct elements & frequencies not printed (too many).");
/*     */     }
/* 749 */     return localStringBuffer.toString();
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public synchronized void trim(int paramInt1, int paramInt2)
/*     */   {
/* 759 */     DoubleArrayList localDoubleArrayList = sortedElements();
/* 760 */     clear();
/* 761 */     addAllOfFromTo(localDoubleArrayList, paramInt1, localDoubleArrayList.size() - 1 - paramInt2);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public synchronized double trimmedMean(int paramInt1, int paramInt2)
/*     */   {
/* 773 */     return Descriptive.trimmedMean(sortedElements_unsafe(), mean(), paramInt1, paramInt2);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public synchronized void trimToSize()
/*     */   {
/* 784 */     this.elements.trimToSize();
/*     */     
/* 786 */     this.sortedElements.clear();
/* 787 */     this.sortedElements.trimToSize();
/* 788 */     if (this.fixedOrder) { this.isSorted = false;
/*     */     }
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */   protected void updateIncrementalStats()
/*     */   {
/* 796 */     double[] arrayOfDouble = new double[4];
/* 797 */     arrayOfDouble[0] = this.min;
/* 798 */     arrayOfDouble[1] = this.max;
/* 799 */     arrayOfDouble[2] = this.sum;
/* 800 */     arrayOfDouble[3] = this.sum_xx;
/*     */     
/* 802 */     Descriptive.incrementalUpdate(this.elements, this.size, this.elements.size() - 1, arrayOfDouble);
/*     */     
/*     */ 
/* 805 */     this.min = arrayOfDouble[0];
/* 806 */     this.max = arrayOfDouble[1];
/* 807 */     this.sum = arrayOfDouble[2];
/* 808 */     this.sum_xx = arrayOfDouble[3];
/*     */     
/* 810 */     this.isIncrementalStatValid = true;
/* 811 */     this.size = this.elements.size();
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */   protected void updateSumOfInversions()
/*     */   {
/* 818 */     this.sumOfInversions = Descriptive.sumOfInversions(this.elements, 0, size() - 1);
/* 819 */     this.isSumOfInversionsValid = true;
/*     */   }
/*     */   
/*     */ 
/*     */   protected void updateSumOfLogarithms()
/*     */   {
/* 825 */     this.sumOfLogarithms = Descriptive.sumOfLogarithms(this.elements, 0, size() - 1);
/* 826 */     this.isSumOfLogarithmsValid = true;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   protected void validateAll()
/*     */   {
/* 834 */     this.isSorted = true;
/* 835 */     this.isIncrementalStatValid = true;
/*     */     
/*     */ 
/*     */ 
/*     */ 
/* 840 */     this.isSumOfInversionsValid = true;
/* 841 */     this.isSumOfLogarithmsValid = true;
/*     */   }
/*     */ }


/* Location:              /home/menick/Scrivania/ProcessMining/CNMining/CNMining.jar!/hep/aida/bin/DynamicBin1D.class
 * Java compiler version: 1 (45.3)
 * JD-Core Version:       0.7.1
 */