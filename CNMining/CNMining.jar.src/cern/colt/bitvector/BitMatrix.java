/*     */ package cern.colt.bitvector;
/*     */ 
/*     */ import cern.colt.PersistentObject;
/*     */ import cern.colt.function.IntIntProcedure;
/*     */ import java.awt.Rectangle;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class BitMatrix
/*     */   extends PersistentObject
/*     */ {
/*     */   protected int columns;
/*     */   protected int rows;
/*     */   protected long[] bits;
/*     */   
/*     */   public BitMatrix(int paramInt1, int paramInt2)
/*     */   {
/*  56 */     elements(QuickBitVector.makeBitVector(paramInt1 * paramInt2, 1), paramInt1, paramInt2);
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
/*     */   public void and(BitMatrix paramBitMatrix)
/*     */   {
/*  69 */     checkDimensionCompatibility(paramBitMatrix);
/*  70 */     toBitVector().and(paramBitMatrix.toBitVector());
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public void andNot(BitMatrix paramBitMatrix)
/*     */   {
/*  81 */     checkDimensionCompatibility(paramBitMatrix);
/*  82 */     toBitVector().andNot(paramBitMatrix.toBitVector());
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */   public int cardinality()
/*     */   {
/*  89 */     return toBitVector().cardinality();
/*     */   }
/*     */   
/*     */ 
/*     */   protected void checkDimensionCompatibility(BitMatrix paramBitMatrix)
/*     */   {
/*  95 */     if ((this.columns != paramBitMatrix.columns()) || (this.rows != paramBitMatrix.rows())) { throw new IllegalArgumentException("Incompatible dimensions: (columns,rows)=(" + this.columns + "," + this.rows + "), (other.columns,other.rows)=(" + paramBitMatrix.columns() + "," + paramBitMatrix.rows() + ")");
/*     */     }
/*     */   }
/*     */   
/*     */   public void clear()
/*     */   {
/* 101 */     toBitVector().clear();
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public Object clone()
/*     */   {
/* 113 */     BitMatrix localBitMatrix = (BitMatrix)super.clone();
/* 114 */     if (this.bits != null) localBitMatrix.bits = ((long[])this.bits.clone());
/* 115 */     return localBitMatrix;
/*     */   }
/*     */   
/*     */ 
/*     */   public int columns()
/*     */   {
/* 121 */     return this.columns;
/*     */   }
/*     */   
/*     */ 
/*     */   protected void containsBox(int paramInt1, int paramInt2, int paramInt3, int paramInt4)
/*     */   {
/* 127 */     if ((paramInt1 < 0) || (paramInt1 + paramInt3 > this.columns) || (paramInt2 < 0) || (paramInt2 + paramInt4 > this.rows)) { throw new IndexOutOfBoundsException("column:" + paramInt1 + ", row:" + paramInt2 + " ,width:" + paramInt3 + ", height:" + paramInt4);
/*     */     }
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */   public BitMatrix copy()
/*     */   {
/* 135 */     return (BitMatrix)clone();
/*     */   }
/*     */   
/* 138 */   protected long[] elements() { return this.bits; }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   protected void elements(long[] paramArrayOfLong, int paramInt1, int paramInt2)
/*     */   {
/* 148 */     if ((paramInt1 < 0) || (paramInt1 < 0) || (paramInt1 * paramInt2 > paramArrayOfLong.length * 64)) throw new IllegalArgumentException();
/* 149 */     this.bits = paramArrayOfLong;
/* 150 */     this.columns = paramInt1;
/* 151 */     this.rows = paramInt2;
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
/*     */   public boolean equals(Object paramObject)
/*     */   {
/* 164 */     if ((paramObject == null) || (!(paramObject instanceof BitMatrix)))
/* 165 */       return false;
/* 166 */     if (this == paramObject) {
/* 167 */       return true;
/*     */     }
/* 169 */     BitMatrix localBitMatrix = (BitMatrix)paramObject;
/* 170 */     if ((this.columns != localBitMatrix.columns()) || (this.rows != localBitMatrix.rows())) { return false;
/*     */     }
/* 172 */     return toBitVector().equals(localBitMatrix.toBitVector());
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
/*     */   public boolean forEachCoordinateInState(boolean paramBoolean, IntIntProcedure paramIntIntProcedure)
/*     */   {
/* 203 */     if (size() == 0) return true;
/* 204 */     BitVector localBitVector = new BitVector(this.bits, size());
/*     */     
/* 206 */     long[] arrayOfLong = this.bits;
/*     */     
/* 208 */     int i = this.columns - 1;
/* 209 */     int j = this.rows - 1;
/*     */     
/*     */ 
/* 212 */     long l1 = arrayOfLong[(this.bits.length - 1)];
/* 213 */     int k = localBitVector.numberOfBitsInPartialUnit();
/* 214 */     do { long l2 = l1 & 1L << k;
/* 215 */       if (((paramBoolean) && (l2 != 0L)) || ((!paramBoolean) && (l2 == 0L) && 
/* 216 */         (!paramIntIntProcedure.apply(i, j)))) { return false;
/*     */       }
/* 218 */       i--; if (i < 0) {
/* 219 */         i = this.columns - 1;
/* 220 */         j--;
/*     */       }
/* 213 */       k--; } while (k >= 0);
/*     */     
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     long l3;
/*     */     
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/* 228 */     if (paramBoolean) l3 = 0L; else {
/* 229 */       l3 = -1L;
/*     */     }
/* 231 */     int m = localBitVector.numberOfFullUnits();
/* 232 */     do { l1 = arrayOfLong[m];
/* 233 */       int n; if (l1 != l3)
/*     */       {
/*     */ 
/* 236 */         if (paramBoolean) {
/* 237 */           n = 64;
/* 238 */           do { if (((l1 & 1L << n) != 0L) && 
/* 239 */               (!paramIntIntProcedure.apply(i, j))) { return false;
/*     */             }
/* 241 */             i--; if (i < 0) {
/* 242 */               i = this.columns - 1;
/* 243 */               j--;
/*     */             }
/* 237 */             n--; } while (n >= 0);
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */         }
/*     */         else
/*     */         {
/*     */ 
/*     */ 
/*     */ 
/* 248 */           n = 64;
/* 249 */           do { if (((l1 & 1L << n) == 0L) && 
/* 250 */               (!paramIntIntProcedure.apply(i, j))) { return false;
/*     */             }
/* 252 */             i--; if (i < 0) {
/* 253 */               i = this.columns - 1;
/* 254 */               j--;
/*     */             }
/* 248 */             n--; } while (n >= 0);
/*     */ 
/*     */ 
/*     */         }
/*     */         
/*     */ 
/*     */ 
/*     */ 
/*     */       }
/*     */       else
/*     */       {
/*     */ 
/*     */ 
/* 261 */         i -= 64;
/* 262 */         if (i < 0)
/*     */         {
/* 264 */           i += 64;
/* 265 */           n = 64;
/* 266 */           do { i--; if (i < 0) {
/* 267 */               i = this.columns - 1;
/* 268 */               j--;
/*     */             }
/* 265 */             n--; } while (n >= 0);
/*     */         }
/*     */       }
/* 231 */       m--; } while (m >= 0);
/*     */     
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/* 276 */     return true;
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
/*     */   public boolean get(int paramInt1, int paramInt2)
/*     */   {
/* 289 */     if ((paramInt1 < 0) || (paramInt1 >= this.columns) || (paramInt2 < 0) || (paramInt2 >= this.rows)) throw new IndexOutOfBoundsException("column:" + paramInt1 + ", row:" + paramInt2);
/* 290 */     return QuickBitVector.get(this.bits, paramInt2 * this.columns + paramInt1);
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
/*     */   public boolean getQuick(int paramInt1, int paramInt2)
/*     */   {
/* 305 */     return QuickBitVector.get(this.bits, paramInt2 * this.columns + paramInt1);
/*     */   }
/*     */   
/*     */ 
/*     */   public int hashCode()
/*     */   {
/* 311 */     return toBitVector().hashCode();
/*     */   }
/*     */   
/*     */ 
/*     */   public void not()
/*     */   {
/* 317 */     toBitVector().not();
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
/*     */   public void or(BitMatrix paramBitMatrix)
/*     */   {
/* 330 */     checkDimensionCompatibility(paramBitMatrix);
/* 331 */     toBitVector().or(paramBitMatrix.toBitVector());
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public BitMatrix part(int paramInt1, int paramInt2, int paramInt3, int paramInt4)
/*     */   {
/* 343 */     if ((paramInt1 < 0) || (paramInt1 + paramInt3 > this.columns) || (paramInt2 < 0) || (paramInt2 + paramInt4 > this.rows)) throw new IndexOutOfBoundsException("column:" + paramInt1 + ", row:" + paramInt2 + " ,width:" + paramInt3 + ", height:" + paramInt4);
/* 344 */     if ((paramInt3 <= 0) || (paramInt4 <= 0)) { return new BitMatrix(0, 0);
/*     */     }
/* 346 */     BitMatrix localBitMatrix = new BitMatrix(paramInt3, paramInt4);
/* 347 */     localBitMatrix.replaceBoxWith(0, 0, paramInt3, paramInt4, this, paramInt1, paramInt2);
/* 348 */     return localBitMatrix;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public void put(int paramInt1, int paramInt2, boolean paramBoolean)
/*     */   {
/* 359 */     if ((paramInt1 < 0) || (paramInt1 >= this.columns) || (paramInt2 < 0) || (paramInt2 >= this.rows)) throw new IndexOutOfBoundsException("column:" + paramInt1 + ", row:" + paramInt2);
/* 360 */     QuickBitVector.put(this.bits, paramInt2 * this.columns + paramInt1, paramBoolean);
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
/*     */   public void putQuick(int paramInt1, int paramInt2, boolean paramBoolean)
/*     */   {
/* 374 */     QuickBitVector.put(this.bits, paramInt2 * this.columns + paramInt1, paramBoolean);
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
/*     */   public void replaceBoxWith(int paramInt1, int paramInt2, int paramInt3, int paramInt4, BitMatrix paramBitMatrix, int paramInt5, int paramInt6)
/*     */   {
/* 394 */     containsBox(paramInt1, paramInt2, paramInt3, paramInt4);
/* 395 */     paramBitMatrix.containsBox(paramInt5, paramInt6, paramInt3, paramInt4);
/* 396 */     if ((paramInt3 <= 0) || (paramInt4 <= 0)) { return;
/*     */     }
/* 398 */     if (paramBitMatrix == this) {
/* 399 */       localObject1 = new Rectangle(paramInt1, paramInt2, paramInt3, paramInt4);
/* 400 */       localObject2 = new Rectangle(paramInt5, paramInt6, paramInt3, paramInt4);
/* 401 */       if (((Rectangle)localObject1).intersects((Rectangle)localObject2)) {
/* 402 */         paramBitMatrix = paramBitMatrix.copy();
/*     */       }
/*     */     }
/*     */     
/* 406 */     Object localObject1 = paramBitMatrix.toBitVector();
/* 407 */     Object localObject2 = toBitVector();
/* 408 */     int i = paramBitMatrix.columns();
/*     */     do {
/* 410 */       int j = paramInt2 * this.columns + paramInt1;
/* 411 */       int k = paramInt6 * i + paramInt5;
/* 412 */       ((BitVector)localObject2).replaceFromToWith(j, j + paramInt3 - 1, (BitVector)localObject1, k);paramInt2++;paramInt6++;paramInt4--;
/* 409 */     } while (paramInt4 >= 0);
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
/*     */   public void replaceBoxWith(int paramInt1, int paramInt2, int paramInt3, int paramInt4, boolean paramBoolean)
/*     */   {
/* 427 */     containsBox(paramInt1, paramInt2, paramInt3, paramInt4);
/* 428 */     if ((paramInt3 <= 0) || (paramInt4 <= 0)) { return;
/*     */     }
/* 430 */     BitVector localBitVector = toBitVector();
/*     */     do {
/* 432 */       int i = paramInt2 * this.columns + paramInt1;
/* 433 */       localBitVector.replaceFromToWith(i, i + paramInt3 - 1, paramBoolean);paramInt2++;paramInt4--;
/* 431 */     } while (paramInt4 >= 0);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public int rows()
/*     */   {
/* 440 */     return this.rows;
/*     */   }
/*     */   
/*     */ 
/*     */   public int size()
/*     */   {
/* 446 */     return this.columns * this.rows;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public BitVector toBitVector()
/*     */   {
/* 456 */     return new BitVector(this.bits, size());
/*     */   }
/*     */   
/*     */ 
/*     */   public String toString()
/*     */   {
/* 462 */     return toBitVector().toString();
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
/*     */   public void xor(BitMatrix paramBitMatrix)
/*     */   {
/* 479 */     checkDimensionCompatibility(paramBitMatrix);
/* 480 */     toBitVector().xor(paramBitMatrix.toBitVector());
/*     */   }
/*     */ }


/* Location:              /home/menick/Scrivania/ProcessMining/CNMining/CNMining.jar!/cern/colt/bitvector/BitMatrix.class
 * Java compiler version: 1 (45.3)
 * JD-Core Version:       0.7.1
 */