/*     */ package cern.jet.stat.quantile;
/*     */ 
/*     */ import cern.colt.PersistentObject;
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
/*     */ class DoubleBuffer
/*     */   extends Buffer
/*     */ {
/*     */   protected DoubleArrayList values;
/*     */   protected boolean isSorted;
/*     */   
/*     */   public DoubleBuffer(int paramInt)
/*     */   {
/*  23 */     super(paramInt);
/*  24 */     this.values = new DoubleArrayList(0);
/*  25 */     this.isSorted = false;
/*     */   }
/*     */   
/*     */ 
/*     */   public void add(double paramDouble)
/*     */   {
/*  31 */     if (!this.isAllocated) allocate();
/*  32 */     this.values.add(paramDouble);
/*  33 */     this.isSorted = false;
/*     */   }
/*     */   
/*     */ 
/*     */   public void addAllOfFromTo(DoubleArrayList paramDoubleArrayList, int paramInt1, int paramInt2)
/*     */   {
/*  39 */     if (!this.isAllocated) allocate();
/*  40 */     this.values.addAllOfFromTo(paramDoubleArrayList, paramInt1, paramInt2);
/*  41 */     this.isSorted = false;
/*     */   }
/*     */   
/*     */ 
/*     */   protected void allocate()
/*     */   {
/*  47 */     this.isAllocated = true;
/*  48 */     this.values.ensureCapacity(this.k);
/*     */   }
/*     */   
/*     */ 
/*     */   public void clear()
/*     */   {
/*  54 */     this.values.clear();
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   public Object clone()
/*     */   {
/*  62 */     DoubleBuffer localDoubleBuffer = (DoubleBuffer)super.clone();
/*  63 */     if (this.values != null) localDoubleBuffer.values = localDoubleBuffer.values.copy();
/*  64 */     return localDoubleBuffer;
/*     */   }
/*     */   
/*     */ 
/*     */   public boolean contains(double paramDouble)
/*     */   {
/*  70 */     sort();
/*  71 */     return this.values.contains(paramDouble);
/*     */   }
/*     */   
/*     */ 
/*     */   public boolean isEmpty()
/*     */   {
/*  77 */     return this.values.size() == 0;
/*     */   }
/*     */   
/*     */ 
/*     */   public boolean isFull()
/*     */   {
/*  83 */     return this.values.size() == this.k;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */   public int memory()
/*     */   {
/*  90 */     return this.values.elements().length;
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
/*     */   public double rank(double paramDouble)
/*     */   {
/* 103 */     sort();
/* 104 */     return Descriptive.rankInterpolated(this.values, paramDouble);
/*     */   }
/*     */   
/*     */ 
/*     */   public int size()
/*     */   {
/* 110 */     return this.values.size();
/*     */   }
/*     */   
/*     */ 
/*     */   public void sort()
/*     */   {
/* 116 */     if (!this.isSorted)
/*     */     {
/*     */ 
/* 119 */       this.values.sort();
/*     */       
/* 121 */       this.isSorted = true;
/*     */     }
/*     */   }
/*     */   
/*     */ 
/*     */   public String toString()
/*     */   {
/* 128 */     return "k=" + this.k + ", w=" + Long.toString(weight()) + ", l=" + Integer.toString(level()) + ", size=" + this.values.size();
/*     */   }
/*     */ }


/* Location:              /home/menick/Scrivania/ProcessMining/CNMining/CNMining.jar!/cern/jet/stat/quantile/DoubleBuffer.class
 * Java compiler version: 1 (45.3)
 * JD-Core Version:       0.7.1
 */