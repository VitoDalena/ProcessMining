/*     */ package cern.colt.matrix.impl;
/*     */ 
/*     */ import cern.colt.matrix.DoubleMatrix1D;
/*     */ import cern.colt.matrix.DoubleMatrix2D;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ class WrapperDoubleMatrix2D
/*     */   extends DoubleMatrix2D
/*     */ {
/*     */   protected DoubleMatrix2D content;
/*     */   
/*     */   public WrapperDoubleMatrix2D(DoubleMatrix2D paramDoubleMatrix2D)
/*     */   {
/*  34 */     if (paramDoubleMatrix2D != null) setUp(paramDoubleMatrix2D.rows(), paramDoubleMatrix2D.columns());
/*  35 */     this.content = paramDoubleMatrix2D;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */   protected DoubleMatrix2D getContent()
/*     */   {
/*  42 */     return this.content;
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
/*  56 */     return this.content.getQuick(paramInt1, paramInt2);
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
/*  69 */     return this.content.like(paramInt1, paramInt2);
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
/*  80 */     return this.content.like1D(paramInt);
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
/*     */   protected DoubleMatrix1D like1D(int paramInt1, int paramInt2, int paramInt3)
/*     */   {
/*  93 */     throw new InternalError();
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
/* 107 */     this.content.setQuick(paramInt1, paramInt2, paramDouble);
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
/*     */   public DoubleMatrix1D viewColumn(int paramInt)
/*     */   {
/* 132 */     return viewDice().viewRow(paramInt);
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
/*     */   public DoubleMatrix2D viewColumnFlip()
/*     */   {
/* 160 */     if (this.columns == 0) return this;
/* 161 */     WrapperDoubleMatrix2D local1 = new WrapperDoubleMatrix2D(this) {
/*     */       public double getQuick(int paramAnonymousInt1, int paramAnonymousInt2) {
/* 163 */         return this.content.get(paramAnonymousInt1, this.columns - 1 - paramAnonymousInt2);
/*     */       }
/*     */       
/* 166 */       public void setQuick(int paramAnonymousInt1, int paramAnonymousInt2, double paramAnonymousDouble) { this.content.set(paramAnonymousInt1, this.columns - 1 - paramAnonymousInt2, paramAnonymousDouble);
/*     */       }
/* 168 */     };
/* 169 */     return local1;
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
/*     */   public DoubleMatrix2D viewDice()
/*     */   {
/* 200 */     WrapperDoubleMatrix2D local2 = new WrapperDoubleMatrix2D(this) {
/*     */       public double getQuick(int paramAnonymousInt1, int paramAnonymousInt2) {
/* 202 */         return this.content.get(paramAnonymousInt2, paramAnonymousInt1);
/*     */       }
/*     */       
/* 205 */       public void setQuick(int paramAnonymousInt1, int paramAnonymousInt2, double paramAnonymousDouble) { this.content.set(paramAnonymousInt2, paramAnonymousInt1, paramAnonymousDouble);
/*     */       }
/* 207 */     };
/* 208 */     local2.rows = this.columns;
/* 209 */     local2.columns = this.rows;
/* 210 */     return local2;
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
/*     */   public DoubleMatrix2D viewPart(int paramInt1, int paramInt2, int paramInt3, int paramInt4)
/*     */   {
/* 236 */     checkBox(paramInt1, paramInt2, paramInt3, paramInt4);
/* 237 */     WrapperDoubleMatrix2D local3 = new WrapperDoubleMatrix2D(paramInt1) { private final int val$row;
/*     */       
/* 239 */       public double getQuick(int paramAnonymousInt1, int paramAnonymousInt2) { return this.content.get(this.val$row + paramAnonymousInt1, this.val$column + paramAnonymousInt2); }
/*     */       
/*     */       public void setQuick(int paramAnonymousInt1, int paramAnonymousInt2, double paramAnonymousDouble) {
/* 242 */         this.content.set(this.val$row + paramAnonymousInt1, this.val$column + paramAnonymousInt2, paramAnonymousDouble);
/*     */       }
/* 244 */     };
/* 245 */     local3.rows = paramInt3;
/* 246 */     local3.columns = paramInt4;
/*     */     
/* 248 */     return local3;
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
/*     */   public DoubleMatrix1D viewRow(int paramInt)
/*     */   {
/* 273 */     checkRow(paramInt);
/* 274 */     return new DelegateDoubleMatrix1D(this, paramInt);
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
/*     */   private final int val$column;
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public DoubleMatrix2D viewRowFlip()
/*     */   {
/* 302 */     if (this.rows == 0) return this;
/* 303 */     WrapperDoubleMatrix2D local4 = new WrapperDoubleMatrix2D(this) {
/*     */       public double getQuick(int paramAnonymousInt1, int paramAnonymousInt2) {
/* 305 */         return this.content.get(this.rows - 1 - paramAnonymousInt1, paramAnonymousInt2);
/*     */       }
/*     */       
/* 308 */       public void setQuick(int paramAnonymousInt1, int paramAnonymousInt2, double paramAnonymousDouble) { this.content.set(this.rows - 1 - paramAnonymousInt1, paramAnonymousInt2, paramAnonymousDouble);
/*     */       }
/* 310 */     };
/* 311 */     return local4;
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
/*     */   public DoubleMatrix2D viewSelection(int[] paramArrayOfInt1, int[] paramArrayOfInt2)
/*     */   {
/*     */     int i;
/*     */     
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/* 342 */     if (paramArrayOfInt1 == null) {
/* 343 */       paramArrayOfInt1 = new int[this.rows];
/* 344 */       i = this.rows; do { paramArrayOfInt1[i] = i;i--; } while (i >= 0);
/*     */     }
/* 346 */     if (paramArrayOfInt2 == null) {
/* 347 */       paramArrayOfInt2 = new int[this.columns];
/* 348 */       i = this.columns; do { paramArrayOfInt2[i] = i;i--; } while (i >= 0);
/*     */     }
/*     */     
/* 351 */     checkRowIndexes(paramArrayOfInt1);
/* 352 */     checkColumnIndexes(paramArrayOfInt2);
/* 353 */     int[] arrayOfInt1 = paramArrayOfInt1;
/* 354 */     int[] arrayOfInt2 = paramArrayOfInt2;
/*     */     
/* 356 */     WrapperDoubleMatrix2D local5 = new WrapperDoubleMatrix2D(arrayOfInt1) { private final int[] val$rix;
/*     */       
/* 358 */       public double getQuick(int paramAnonymousInt1, int paramAnonymousInt2) { return this.content.get(this.val$rix[paramAnonymousInt1], this.val$cix[paramAnonymousInt2]); }
/*     */       
/*     */       public void setQuick(int paramAnonymousInt1, int paramAnonymousInt2, double paramAnonymousDouble) {
/* 361 */         this.content.set(this.val$rix[paramAnonymousInt1], this.val$cix[paramAnonymousInt2], paramAnonymousDouble);
/*     */       }
/* 363 */     };
/* 364 */     local5.rows = paramArrayOfInt1.length;
/* 365 */     local5.columns = paramArrayOfInt2.length;
/*     */     
/* 367 */     return local5;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   protected DoubleMatrix2D viewSelectionLike(int[] paramArrayOfInt1, int[] paramArrayOfInt2)
/*     */   {
/* 377 */     throw new InternalError();
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   private final int[] val$cix;
/*     */   
/*     */ 
/*     */ 
/*     */   public DoubleMatrix2D viewStrides(int paramInt1, int paramInt2)
/*     */   {
/* 390 */     if ((paramInt1 <= 0) || (paramInt2 <= 0)) throw new IndexOutOfBoundsException("illegal stride");
/* 391 */     WrapperDoubleMatrix2D local6 = new WrapperDoubleMatrix2D(paramInt1) { private final int val$_rowStride;
/*     */       
/* 393 */       public double getQuick(int paramAnonymousInt1, int paramAnonymousInt2) { return this.content.get(this.val$_rowStride * paramAnonymousInt1, this.val$_columnStride * paramAnonymousInt2); }
/*     */       
/*     */       private final int val$_columnStride;
/* 396 */       public void setQuick(int paramAnonymousInt1, int paramAnonymousInt2, double paramAnonymousDouble) { this.content.set(this.val$_rowStride * paramAnonymousInt1, this.val$_columnStride * paramAnonymousInt2, paramAnonymousDouble);
/*     */       }
/* 398 */     };
/* 399 */     local6.rows = this.rows;
/* 400 */     local6.columns = this.columns;
/* 401 */     if (this.rows != 0) local6.rows = ((this.rows - 1) / paramInt1 + 1);
/* 402 */     if (this.columns != 0) local6.columns = ((this.columns - 1) / paramInt2 + 1);
/* 403 */     return local6;
/*     */   }
/*     */ }


/* Location:              /home/menick/Scrivania/ProcessMining/CNMining/CNMining.jar!/cern/colt/matrix/impl/WrapperDoubleMatrix2D.class
 * Java compiler version: 1 (45.3)
 * JD-Core Version:       0.7.1
 */