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
/*     */ class WrapperDoubleMatrix1D
/*     */   extends DoubleMatrix1D
/*     */ {
/*     */   protected DoubleMatrix1D content;
/*     */   
/*     */   public WrapperDoubleMatrix1D(DoubleMatrix1D paramDoubleMatrix1D)
/*     */   {
/*  26 */     if (paramDoubleMatrix1D != null) setUp(paramDoubleMatrix1D.size());
/*  27 */     this.content = paramDoubleMatrix1D;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */   protected DoubleMatrix1D getContent()
/*     */   {
/*  34 */     return this.content;
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
/*     */   public double getQuick(int paramInt)
/*     */   {
/*  47 */     return this.content.getQuick(paramInt);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public DoubleMatrix1D like(int paramInt)
/*     */   {
/*  59 */     return this.content.like(paramInt);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public DoubleMatrix2D like2D(int paramInt1, int paramInt2)
/*     */   {
/*  71 */     return this.content.like2D(paramInt1, paramInt2);
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
/*     */   public void setQuick(int paramInt, double paramDouble)
/*     */   {
/*  84 */     this.content.setQuick(paramInt, paramDouble);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public DoubleMatrix1D viewFlip()
/*     */   {
/*  94 */     WrapperDoubleMatrix1D local1 = new WrapperDoubleMatrix1D(this) {
/*     */       public double getQuick(int paramAnonymousInt) {
/*  96 */         return this.content.get(this.size - 1 - paramAnonymousInt);
/*     */       }
/*     */       
/*  99 */       public void setQuick(int paramAnonymousInt, double paramAnonymousDouble) { this.content.set(this.size - 1 - paramAnonymousInt, paramAnonymousDouble);
/*     */       }
/* 101 */     };
/* 102 */     return local1;
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
/*     */   public DoubleMatrix1D viewPart(int paramInt1, int paramInt2)
/*     */   {
/* 126 */     checkRange(paramInt1, paramInt2);
/* 127 */     WrapperDoubleMatrix1D local2 = new WrapperDoubleMatrix1D(paramInt1) { private final int val$index;
/*     */       
/* 129 */       public double getQuick(int paramAnonymousInt) { return this.content.get(this.val$index + paramAnonymousInt); }
/*     */       
/*     */       public void setQuick(int paramAnonymousInt, double paramAnonymousDouble) {
/* 132 */         this.content.set(this.val$index + paramAnonymousInt, paramAnonymousDouble);
/*     */       }
/* 134 */     };
/* 135 */     local2.size = paramInt2;
/* 136 */     return local2;
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
/*     */   public DoubleMatrix1D viewSelection(int[] paramArrayOfInt)
/*     */   {
/* 160 */     if (paramArrayOfInt == null) {
/* 161 */       paramArrayOfInt = new int[this.size];
/* 162 */       int i = this.size; do { paramArrayOfInt[i] = i;i--; } while (i >= 0);
/*     */     }
/*     */     
/* 165 */     checkIndexes(paramArrayOfInt);
/* 166 */     int[] arrayOfInt = paramArrayOfInt;
/*     */     
/* 168 */     WrapperDoubleMatrix1D local3 = new WrapperDoubleMatrix1D(arrayOfInt) { private final int[] val$idx;
/*     */       
/* 170 */       public double getQuick(int paramAnonymousInt) { return this.content.get(this.val$idx[paramAnonymousInt]); }
/*     */       
/*     */       public void setQuick(int paramAnonymousInt, double paramAnonymousDouble) {
/* 173 */         this.content.set(this.val$idx[paramAnonymousInt], paramAnonymousDouble);
/*     */       }
/* 175 */     };
/* 176 */     local3.size = paramArrayOfInt.length;
/* 177 */     return local3;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   protected DoubleMatrix1D viewSelectionLike(int[] paramArrayOfInt)
/*     */   {
/* 186 */     throw new InternalError();
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public DoubleMatrix1D viewStrides(int paramInt)
/*     */   {
/* 198 */     if (this.stride <= 0) throw new IndexOutOfBoundsException("illegal stride: " + this.stride);
/* 199 */     WrapperDoubleMatrix1D local4 = new WrapperDoubleMatrix1D(paramInt) { private final int val$_stride;
/*     */       
/* 201 */       public double getQuick(int paramAnonymousInt) { return this.content.get(paramAnonymousInt * this.val$_stride); }
/*     */       
/*     */       public void setQuick(int paramAnonymousInt, double paramAnonymousDouble) {
/* 204 */         this.content.set(paramAnonymousInt * this.val$_stride, paramAnonymousDouble);
/*     */       }
/* 206 */     };
/* 207 */     local4.size = this.size;
/* 208 */     if (this.size != 0) local4.size = ((this.size - 1) / paramInt + 1);
/* 209 */     return local4;
/*     */   }
/*     */ }


/* Location:              /home/menick/Scrivania/ProcessMining/CNMining/CNMining.jar!/cern/colt/matrix/impl/WrapperDoubleMatrix1D.class
 * Java compiler version: 1 (45.3)
 * JD-Core Version:       0.7.1
 */