/*     */ package cern.jet.stat.quantile;
/*     */ 
/*     */ import cern.colt.PersistentObject;
/*     */ import cern.colt.function.DoubleProcedure;
/*     */ import cern.colt.list.AbstractDoubleList;
/*     */ import cern.colt.list.AbstractList;
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
/*     */ class ExactDoubleQuantileFinder
/*     */   extends PersistentObject
/*     */   implements DoubleQuantileFinder
/*     */ {
/*     */   protected DoubleArrayList buffer;
/*     */   protected boolean isSorted;
/*     */   
/*     */   public ExactDoubleQuantileFinder()
/*     */   {
/*  28 */     this.buffer = new DoubleArrayList(0);
/*  29 */     clear();
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */   public void add(double paramDouble)
/*     */   {
/*  36 */     this.buffer.add(paramDouble);
/*  37 */     this.isSorted = false;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */   public void addAllOf(DoubleArrayList paramDoubleArrayList)
/*     */   {
/*  44 */     addAllOfFromTo(paramDoubleArrayList, 0, paramDoubleArrayList.size() - 1);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public void addAllOfFromTo(DoubleArrayList paramDoubleArrayList, int paramInt1, int paramInt2)
/*     */   {
/*  54 */     this.buffer.addAllOfFromTo(paramDoubleArrayList, paramInt1, paramInt2);
/*  55 */     this.isSorted = false;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */   public void clear()
/*     */   {
/*  62 */     this.buffer.clear();
/*  63 */     this.buffer.trimToSize();
/*  64 */     this.isSorted = false;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   public Object clone()
/*     */   {
/*  72 */     ExactDoubleQuantileFinder localExactDoubleQuantileFinder = (ExactDoubleQuantileFinder)super.clone();
/*  73 */     if (this.buffer != null) localExactDoubleQuantileFinder.buffer = localExactDoubleQuantileFinder.buffer.copy();
/*  74 */     return localExactDoubleQuantileFinder;
/*     */   }
/*     */   
/*     */ 
/*     */   public boolean contains(double paramDouble)
/*     */   {
/*  80 */     sort();
/*  81 */     return this.buffer.binarySearch(paramDouble) >= 0;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public boolean forEach(DoubleProcedure paramDoubleProcedure)
/*     */   {
/*  90 */     double[] arrayOfDouble = this.buffer.elements();
/*  91 */     int i = (int)size();
/*     */     
/*  93 */     for (int j = 0; j < i;) if (!paramDoubleProcedure.apply(arrayOfDouble[(j++)])) return false;
/*  94 */     return true;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */   public long memory()
/*     */   {
/* 101 */     return this.buffer.elements().length;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public double phi(double paramDouble)
/*     */   {
/* 111 */     sort();
/* 112 */     return Descriptive.rankInterpolated(this.buffer, paramDouble) / size();
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   public DoubleArrayList quantileElements(DoubleArrayList paramDoubleArrayList)
/*     */   {
/* 120 */     sort();
/* 121 */     return Descriptive.quantiles(this.buffer, paramDoubleArrayList);
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
/*     */   public long size()
/*     */   {
/* 136 */     return this.buffer.size();
/*     */   }
/*     */   
/*     */ 
/*     */   protected void sort()
/*     */   {
/* 142 */     if (!this.isSorted)
/*     */     {
/*     */ 
/* 145 */       this.buffer.sort();
/*     */       
/* 147 */       this.isSorted = true;
/*     */     }
/*     */   }
/*     */   
/*     */ 
/*     */   public String toString()
/*     */   {
/* 154 */     String str = getClass().getName();
/* 155 */     str = str.substring(str.lastIndexOf('.') + 1);
/* 156 */     return str + "(mem=" + memory() + ", size=" + size() + ")";
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */   public long totalMemory()
/*     */   {
/* 163 */     return memory();
/*     */   }
/*     */ }


/* Location:              /home/menick/Scrivania/ProcessMining/CNMining/CNMining.jar!/cern/jet/stat/quantile/ExactDoubleQuantileFinder.class
 * Java compiler version: 1 (45.3)
 * JD-Core Version:       0.7.1
 */