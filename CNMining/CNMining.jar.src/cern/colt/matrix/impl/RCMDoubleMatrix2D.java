/*     */ package cern.colt.matrix.impl;
/*     */ 
/*     */ import cern.colt.list.AbstractIntList;
/*     */ import cern.colt.list.AbstractList;
/*     */ import cern.colt.list.DoubleArrayList;
/*     */ import cern.colt.list.IntArrayList;
/*     */ import cern.colt.matrix.DoubleMatrix1D;
/*     */ import cern.colt.matrix.DoubleMatrix2D;
/*     */ import cern.jet.math.Functions;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ class RCMDoubleMatrix2D
/*     */   extends WrapperDoubleMatrix2D
/*     */ {
/*     */   private IntArrayList[] indexes;
/*     */   private DoubleArrayList[] values;
/*     */   
/*     */   public RCMDoubleMatrix2D(double[][] paramArrayOfDouble)
/*     */   {
/*  36 */     this(paramArrayOfDouble.length, paramArrayOfDouble.length == 0 ? 0 : paramArrayOfDouble[0].length);
/*  37 */     assign(paramArrayOfDouble);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public RCMDoubleMatrix2D(int paramInt1, int paramInt2)
/*     */   {
/*  47 */     super(null);
/*  48 */     setUp(paramInt1, paramInt2);
/*  49 */     this.indexes = new IntArrayList[paramInt1];
/*  50 */     this.values = new DoubleArrayList[paramInt1];
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public DoubleMatrix2D assign(double paramDouble)
/*     */   {
/*  59 */     if (paramDouble == 0.0D) {
/*  60 */       int i = this.rows;
/*  61 */       do { this.indexes[i] = null;
/*  62 */         this.values[i] = null;i--;
/*  60 */       } while (i >= 0);
/*     */ 
/*     */     }
/*     */     else
/*     */     {
/*  65 */       super.assign(paramDouble); }
/*  66 */     return this;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */   protected DoubleMatrix2D getContent()
/*     */   {
/*  73 */     return this;
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
/*     */   public double getQuick(int paramInt1, int paramInt2)
/*     */   {
/*  87 */     int i = -1;
/*  88 */     if (this.indexes[paramInt1] != null) i = this.indexes[paramInt1].binarySearch(paramInt2);
/*  89 */     if (i < 0) return 0.0D;
/*  90 */     return this.values[paramInt1].getQuick(i);
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
/*     */   public DoubleMatrix2D like(int paramInt1, int paramInt2)
/*     */   {
/* 103 */     return new RCMDoubleMatrix2D(paramInt1, paramInt2);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public DoubleMatrix1D like1D(int paramInt)
/*     */   {
/* 114 */     return new SparseDoubleMatrix1D(paramInt);
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
/*     */   public void setQuick(int paramInt1, int paramInt2, double paramDouble)
/*     */   {
/* 128 */     int i = paramInt1;
/* 129 */     int j = paramInt2;
/*     */     
/* 131 */     int k = -1;
/* 132 */     IntArrayList localIntArrayList = this.indexes[i];
/* 133 */     if (localIntArrayList != null) { k = localIntArrayList.binarySearch(j);
/*     */     }
/* 135 */     if (k >= 0) {
/* 136 */       if (paramDouble == 0.0D) {
/* 137 */         DoubleArrayList localDoubleArrayList = this.values[i];
/* 138 */         localIntArrayList.remove(k);
/* 139 */         localDoubleArrayList.remove(k);
/* 140 */         int m = localIntArrayList.size();
/* 141 */         if ((m > 2) && (m * 3 < localIntArrayList.elements().length)) {
/* 142 */           localIntArrayList.setSize(m * 3 / 2);
/* 143 */           localIntArrayList.trimToSize();
/* 144 */           localIntArrayList.setSize(m);
/*     */           
/* 146 */           localDoubleArrayList.setSize(m * 3 / 2);
/* 147 */           localDoubleArrayList.trimToSize();
/* 148 */           localDoubleArrayList.setSize(m);
/*     */         }
/*     */       }
/*     */       else {
/* 152 */         this.values[i].setQuick(k, paramDouble);
/*     */       }
/*     */     }
/*     */     else {
/* 156 */       if (paramDouble == 0.0D) { return;
/*     */       }
/* 158 */       k = -k - 1;
/*     */       
/* 160 */       if (localIntArrayList == null) {
/* 161 */         this.indexes[i] = new IntArrayList(3);
/* 162 */         this.values[i] = new DoubleArrayList(3);
/*     */       }
/* 164 */       this.indexes[i].beforeInsert(k, j);
/* 165 */       this.values[i].beforeInsert(k, paramDouble);
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
/*     */   protected void zMult(DoubleMatrix1D paramDoubleMatrix1D1, DoubleMatrix1D paramDoubleMatrix1D2, IntArrayList paramIntArrayList, DoubleMatrix1D[] paramArrayOfDoubleMatrix1D, double paramDouble1, double paramDouble2)
/*     */   {
/* 178 */     if ((this.columns != paramDoubleMatrix1D1.size()) || (this.rows > paramDoubleMatrix1D2.size())) {
/* 179 */       throw new IllegalArgumentException("Incompatible args: " + toStringShort() + ", " + paramDoubleMatrix1D1.toStringShort() + ", " + paramDoubleMatrix1D2.toStringShort());
/*     */     }
/* 181 */     paramDoubleMatrix1D2.assign(Functions.mult(paramDouble2 / paramDouble1));
/* 182 */     int i = this.indexes.length;
/* 183 */     do { if (this.indexes[i] != null) {
/* 184 */         int j = this.indexes[i].size();
/* 185 */         do { int k = this.indexes[i].getQuick(j);
/* 186 */           double d = this.values[i].getQuick(j);
/* 187 */           paramDoubleMatrix1D2.setQuick(i, paramDoubleMatrix1D2.getQuick(i) + d * paramDoubleMatrix1D1.getQuick(k));j--;
/* 184 */         } while (j >= 0);
/*     */       }
/* 182 */       i--; } while (i >= 0);
/*     */     
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/* 192 */     paramDoubleMatrix1D2.assign(Functions.mult(paramDouble1));
/*     */   }
/*     */ }


/* Location:              /home/menick/Scrivania/ProcessMining/CNMining/CNMining.jar!/cern/colt/matrix/impl/RCMDoubleMatrix2D.class
 * Java compiler version: 1 (45.3)
 * JD-Core Version:       0.7.1
 */