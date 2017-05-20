/*     */ package cern.colt.matrix.impl;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public abstract class AbstractMatrix1D
/*     */   extends AbstractMatrix
/*     */ {
/*     */   protected int size;
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   protected int zero;
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   protected int stride;
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   protected int _offset(int paramInt)
/*     */   {
/*  56 */     return paramInt;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   protected int _rank(int paramInt)
/*     */   {
/*  65 */     return this.zero + paramInt * this.stride;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   protected void checkIndex(int paramInt)
/*     */   {
/*  74 */     if ((paramInt < 0) || (paramInt >= this.size)) { throw new IndexOutOfBoundsException("Attempted to access " + toStringShort() + " at index=" + paramInt);
/*     */     }
/*     */   }
/*     */   
/*     */ 
/*     */   protected void checkIndexes(int[] paramArrayOfInt)
/*     */   {
/*  81 */     int i = paramArrayOfInt.length;
/*  82 */     do { int j = paramArrayOfInt[i];
/*  83 */       if ((j < 0) || (j >= this.size)) checkIndex(j);
/*  81 */       i--; } while (i >= 0);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   protected void checkRange(int paramInt1, int paramInt2)
/*     */   {
/*  91 */     if ((paramInt1 < 0) || (paramInt1 + paramInt2 > this.size)) {
/*  92 */       throw new IndexOutOfBoundsException("index: " + paramInt1 + ", width: " + paramInt2 + ", size=" + this.size);
/*     */     }
/*     */   }
/*     */   
/*     */ 
/*     */   protected void checkSize(double[] paramArrayOfDouble)
/*     */   {
/*  99 */     if (this.size != paramArrayOfDouble.length) { throw new IllegalArgumentException("Incompatible sizes: " + toStringShort() + " and " + paramArrayOfDouble.length);
/*     */     }
/*     */   }
/*     */   
/*     */ 
/*     */   public void checkSize(AbstractMatrix1D paramAbstractMatrix1D)
/*     */   {
/* 106 */     if (this.size != paramAbstractMatrix1D.size) { throw new IllegalArgumentException("Incompatible sizes: " + toStringShort() + " and " + paramAbstractMatrix1D.toStringShort());
/*     */     }
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   protected int index(int paramInt)
/*     */   {
/* 115 */     return _offset(_rank(paramInt));
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   protected void setUp(int paramInt)
/*     */   {
/* 123 */     setUp(paramInt, 0, 1);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   protected void setUp(int paramInt1, int paramInt2, int paramInt3)
/*     */   {
/* 133 */     if (paramInt1 < 0) { throw new IllegalArgumentException("negative size");
/*     */     }
/* 135 */     this.size = paramInt1;
/* 136 */     this.zero = paramInt2;
/* 137 */     this.stride = paramInt3;
/* 138 */     this.isNoView = true;
/*     */   }
/*     */   
/*     */ 
/*     */   public int size()
/*     */   {
/* 144 */     return this.size;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   protected int stride(int paramInt)
/*     */   {
/* 154 */     if (paramInt != 0) throw new IllegalArgumentException("invalid dimension: " + paramInt + "used to access" + toStringShort());
/* 155 */     return this.stride;
/*     */   }
/*     */   
/*     */ 
/*     */   public String toStringShort()
/*     */   {
/* 161 */     return AbstractFormatter.shape(this);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */   protected AbstractMatrix1D vFlip()
/*     */   {
/* 168 */     if (this.size > 0) {
/* 169 */       this.zero += (this.size - 1) * this.stride;
/* 170 */       this.stride = (-this.stride);
/* 171 */       this.isNoView = false;
/*     */     }
/* 173 */     return this;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */   protected AbstractMatrix1D vPart(int paramInt1, int paramInt2)
/*     */   {
/* 180 */     checkRange(paramInt1, paramInt2);
/* 181 */     this.zero += this.stride * paramInt1;
/* 182 */     this.size = paramInt2;
/* 183 */     this.isNoView = false;
/* 184 */     return this;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */   protected AbstractMatrix1D vStrides(int paramInt)
/*     */   {
/* 191 */     if (paramInt <= 0) throw new IndexOutOfBoundsException("illegal stride: " + paramInt);
/* 192 */     this.stride *= paramInt;
/* 193 */     if (this.size != 0) this.size = ((this.size - 1) / paramInt + 1);
/* 194 */     this.isNoView = false;
/* 195 */     return this;
/*     */   }
/*     */ }


/* Location:              /home/menick/Scrivania/ProcessMining/CNMining/CNMining.jar!/cern/colt/matrix/impl/AbstractMatrix1D.class
 * Java compiler version: 1 (45.3)
 * JD-Core Version:       0.7.1
 */