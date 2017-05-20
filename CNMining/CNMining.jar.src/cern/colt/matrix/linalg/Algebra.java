/*     */ package cern.colt.matrix.linalg;
/*     */ 
/*     */ import cern.colt.GenericPermuting;
/*     */ import cern.colt.GenericSorting;
/*     */ import cern.colt.PersistentObject;
/*     */ import cern.colt.Swapper;
/*     */ import cern.colt.bitvector.QuickBitVector;
/*     */ import cern.colt.function.DoubleDoubleFunction;
/*     */ import cern.colt.function.IntComparator;
/*     */ import cern.colt.list.ObjectArrayList;
/*     */ import cern.colt.matrix.DoubleFactory2D;
/*     */ import cern.colt.matrix.DoubleMatrix1D;
/*     */ import cern.colt.matrix.DoubleMatrix2D;
/*     */ import cern.colt.matrix.impl.AbstractMatrix1D;
/*     */ import cern.colt.matrix.impl.AbstractMatrix2D;
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
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class Algebra
/*     */   extends PersistentObject
/*     */ {
/*  47 */   public static final Algebra DEFAULT = new Algebra();
/*  48 */   static { DEFAULT.property = Property.DEFAULT;
/*     */     
/*  50 */     ZERO = new Algebra();
/*  51 */     ZERO.property = Property.ZERO;
/*     */   }
/*     */   
/*     */ 
/*     */   public Algebra()
/*     */   {
/*  57 */     this(Property.DEFAULT.tolerance());
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */   public Algebra(double paramDouble)
/*     */   {
/*  64 */     setProperty(new Property(paramDouble));
/*     */   }
/*     */   
/*     */ 
/*     */   private CholeskyDecomposition chol(DoubleMatrix2D paramDoubleMatrix2D)
/*     */   {
/*  70 */     return new CholeskyDecomposition(paramDoubleMatrix2D);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public Object clone()
/*     */   {
/*  79 */     return new Algebra(this.property.tolerance());
/*     */   }
/*     */   
/*     */ 
/*     */   public double cond(DoubleMatrix2D paramDoubleMatrix2D)
/*     */   {
/*  85 */     return svd(paramDoubleMatrix2D).cond();
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */   public double det(DoubleMatrix2D paramDoubleMatrix2D)
/*     */   {
/*  92 */     return lu(paramDoubleMatrix2D).det();
/*     */   }
/*     */   
/*     */ 
/*     */   private EigenvalueDecomposition eig(DoubleMatrix2D paramDoubleMatrix2D)
/*     */   {
/*  98 */     return new EigenvalueDecomposition(paramDoubleMatrix2D);
/*     */   }
/*     */   
/*     */ 
/*     */   protected static double hypot(double paramDouble1, double paramDouble2)
/*     */   {
/*     */     double d;
/* 105 */     if (Math.abs(paramDouble1) > Math.abs(paramDouble2)) {
/* 106 */       d = paramDouble2 / paramDouble1;
/* 107 */       d = Math.abs(paramDouble1) * Math.sqrt(1.0D + d * d);
/* 108 */     } else if (paramDouble2 != 0.0D) {
/* 109 */       d = paramDouble1 / paramDouble2;
/* 110 */       d = Math.abs(paramDouble2) * Math.sqrt(1.0D + d * d);
/*     */     } else {
/* 112 */       d = 0.0D;
/*     */     }
/* 114 */     return d;
/*     */   }
/*     */   
/*     */ 
/*     */   protected static DoubleDoubleFunction hypotFunction()
/*     */   {
/* 120 */     new DoubleDoubleFunction() {
/*     */       public final double apply(double paramAnonymousDouble1, double paramAnonymousDouble2) {
/* 122 */         return Algebra.hypot(paramAnonymousDouble1, paramAnonymousDouble2);
/*     */       }
/*     */     };
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */   public DoubleMatrix2D inverse(DoubleMatrix2D paramDoubleMatrix2D)
/*     */   {
/* 131 */     if ((this.property.isSquare(paramDoubleMatrix2D)) && (this.property.isDiagonal(paramDoubleMatrix2D))) {
/* 132 */       DoubleMatrix2D localDoubleMatrix2D = paramDoubleMatrix2D.copy();
/* 133 */       boolean bool = Diagonal.inverse(localDoubleMatrix2D);
/* 134 */       if (!bool) throw new IllegalArgumentException("A is singular.");
/* 135 */       return localDoubleMatrix2D;
/*     */     }
/* 137 */     return solve(paramDoubleMatrix2D, DoubleFactory2D.dense.identity(paramDoubleMatrix2D.rows()));
/*     */   }
/*     */   
/*     */ 
/*     */   private LUDecomposition lu(DoubleMatrix2D paramDoubleMatrix2D)
/*     */   {
/* 143 */     return new LUDecomposition(paramDoubleMatrix2D);
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
/*     */   public double mult(DoubleMatrix1D paramDoubleMatrix1D1, DoubleMatrix1D paramDoubleMatrix1D2)
/*     */   {
/* 158 */     return paramDoubleMatrix1D1.zDotProduct(paramDoubleMatrix1D2);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public DoubleMatrix1D mult(DoubleMatrix2D paramDoubleMatrix2D, DoubleMatrix1D paramDoubleMatrix1D)
/*     */   {
/* 170 */     return paramDoubleMatrix2D.zMult(paramDoubleMatrix1D, null);
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
/*     */   public DoubleMatrix2D mult(DoubleMatrix2D paramDoubleMatrix2D1, DoubleMatrix2D paramDoubleMatrix2D2)
/*     */   {
/* 185 */     return paramDoubleMatrix2D1.zMult(paramDoubleMatrix2D2, null);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public DoubleMatrix2D multOuter(DoubleMatrix1D paramDoubleMatrix1D1, DoubleMatrix1D paramDoubleMatrix1D2, DoubleMatrix2D paramDoubleMatrix2D)
/*     */   {
/* 197 */     int i = paramDoubleMatrix1D1.size();
/* 198 */     int j = paramDoubleMatrix1D2.size();
/* 199 */     if (paramDoubleMatrix2D == null) paramDoubleMatrix2D = paramDoubleMatrix1D1.like2D(i, j);
/* 200 */     if ((paramDoubleMatrix2D.rows() != i) || (paramDoubleMatrix2D.columns() != j)) { throw new IllegalArgumentException();
/*     */     }
/* 202 */     int k = i; do { paramDoubleMatrix2D.viewRow(k).assign(paramDoubleMatrix1D2);k--; } while (k >= 0);
/*     */     
/* 204 */     int m = j; do { paramDoubleMatrix2D.viewColumn(m).assign(paramDoubleMatrix1D1, Functions.mult);m--; } while (m >= 0);
/* 205 */     return paramDoubleMatrix2D;
/*     */   }
/*     */   
/*     */ 
/*     */   public double norm1(DoubleMatrix1D paramDoubleMatrix1D)
/*     */   {
/* 211 */     if (paramDoubleMatrix1D.size() == 0) return 0.0D;
/* 212 */     return paramDoubleMatrix1D.aggregate(Functions.plus, Functions.abs);
/*     */   }
/*     */   
/*     */ 
/*     */   public double norm1(DoubleMatrix2D paramDoubleMatrix2D)
/*     */   {
/* 218 */     double d = 0.0D;
/* 219 */     int i = paramDoubleMatrix2D.columns();
/* 220 */     do { d = Math.max(d, norm1(paramDoubleMatrix2D.viewColumn(i)));i--;
/* 219 */     } while (i >= 0);
/*     */     
/*     */ 
/* 222 */     return d;
/*     */   }
/*     */   
/*     */ 
/*     */   public double norm2(DoubleMatrix1D paramDoubleMatrix1D)
/*     */   {
/* 228 */     return mult(paramDoubleMatrix1D, paramDoubleMatrix1D);
/*     */   }
/*     */   
/*     */ 
/*     */   public double norm2(DoubleMatrix2D paramDoubleMatrix2D)
/*     */   {
/* 234 */     return svd(paramDoubleMatrix2D).norm2();
/*     */   }
/*     */   
/*     */ 
/*     */   public double normF(DoubleMatrix2D paramDoubleMatrix2D)
/*     */   {
/* 240 */     if (paramDoubleMatrix2D.size() == 0) return 0.0D;
/* 241 */     return paramDoubleMatrix2D.aggregate(hypotFunction(), Functions.identity);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   public double normInfinity(DoubleMatrix1D paramDoubleMatrix1D)
/*     */   {
/* 249 */     double d = 0.0D;
/* 250 */     int i = paramDoubleMatrix1D.size();
/* 251 */     do { d = Math.max(d, paramDoubleMatrix1D.getQuick(i));i--;
/* 250 */     } while (i >= 0);
/*     */     
/*     */ 
/* 253 */     return d;
/*     */   }
/*     */   
/*     */ 
/*     */   public double normInfinity(DoubleMatrix2D paramDoubleMatrix2D)
/*     */   {
/* 259 */     double d = 0.0D;
/* 260 */     int i = paramDoubleMatrix2D.rows();
/*     */     do {
/* 262 */       d = Math.max(d, norm1(paramDoubleMatrix2D.viewRow(i)));i--;
/* 260 */     } while (i >= 0);
/*     */     
/*     */ 
/*     */ 
/* 264 */     return d;
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
/*     */   public DoubleMatrix1D permute(DoubleMatrix1D paramDoubleMatrix1D, int[] paramArrayOfInt, double[] paramArrayOfDouble)
/*     */   {
/* 291 */     int i = paramDoubleMatrix1D.size();
/* 292 */     if (paramArrayOfInt.length != i) { throw new IndexOutOfBoundsException("invalid permutation");
/*     */     }
/*     */     
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/* 301 */     if ((paramArrayOfDouble == null) || (i > paramArrayOfDouble.length)) {
/* 302 */       paramArrayOfDouble = paramDoubleMatrix1D.toArray();
/*     */     }
/*     */     else {
/* 305 */       paramDoubleMatrix1D.toArray(paramArrayOfDouble);
/*     */     }
/* 307 */     int j = i; do { paramDoubleMatrix1D.setQuick(j, paramArrayOfDouble[paramArrayOfInt[j]]);j--; } while (j >= 0);
/* 308 */     return paramDoubleMatrix1D;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public DoubleMatrix2D permute(DoubleMatrix2D paramDoubleMatrix2D, int[] paramArrayOfInt1, int[] paramArrayOfInt2)
/*     */   {
/* 317 */     return paramDoubleMatrix2D.viewSelection(paramArrayOfInt1, paramArrayOfInt2);
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
/*     */   public DoubleMatrix2D permuteColumns(DoubleMatrix2D paramDoubleMatrix2D, int[] paramArrayOfInt1, int[] paramArrayOfInt2)
/*     */   {
/* 330 */     return permuteRows(paramDoubleMatrix2D.viewDice(), paramArrayOfInt1, paramArrayOfInt2);
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
/*     */   public DoubleMatrix2D permuteRows(DoubleMatrix2D paramDoubleMatrix2D, int[] paramArrayOfInt1, int[] paramArrayOfInt2)
/*     */   {
/* 357 */     int i = paramDoubleMatrix2D.rows();
/* 358 */     if (paramArrayOfInt1.length != i) { throw new IndexOutOfBoundsException("invalid permutation");
/*     */     }
/*     */     
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/* 367 */     int j = paramDoubleMatrix2D.columns();
/* 368 */     if (j < i / 10) {
/* 369 */       localObject = new double[i];
/* 370 */       int k = paramDoubleMatrix2D.columns(); do { permute(paramDoubleMatrix2D.viewColumn(k), paramArrayOfInt1, (double[])localObject);k--; } while (k >= 0);
/* 371 */       return paramDoubleMatrix2D;
/*     */     }
/*     */     
/* 374 */     Object localObject = new Swapper() { private final DoubleMatrix2D val$A;
/*     */       
/* 376 */       public void swap(int paramAnonymousInt1, int paramAnonymousInt2) { this.val$A.viewRow(paramAnonymousInt1).swap(this.val$A.viewRow(paramAnonymousInt2));
/*     */       }
/*     */ 
/* 379 */     };
/* 380 */     GenericPermuting.permute(paramArrayOfInt1, (Swapper)localObject, paramArrayOfInt2, null);
/* 381 */     return paramDoubleMatrix2D;
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
/*     */   public DoubleMatrix2D pow(DoubleMatrix2D paramDoubleMatrix2D, int paramInt)
/*     */   {
/* 401 */     Blas localBlas = SmpBlas.smpBlas;
/* 402 */     Property.DEFAULT.checkSquare(paramDoubleMatrix2D);
/* 403 */     if (paramInt < 0) {
/* 404 */       paramDoubleMatrix2D = inverse(paramDoubleMatrix2D);
/* 405 */       paramInt = -paramInt;
/*     */     }
/* 407 */     if (paramInt == 0) return DoubleFactory2D.dense.identity(paramDoubleMatrix2D.rows());
/* 408 */     Object localObject1 = paramDoubleMatrix2D.like();
/* 409 */     if (paramInt == 1) return ((DoubleMatrix2D)localObject1).assign(paramDoubleMatrix2D);
/* 410 */     if (paramInt == 2) {
/* 411 */       localBlas.dgemm(false, false, 1.0D, paramDoubleMatrix2D, paramDoubleMatrix2D, 0.0D, (DoubleMatrix2D)localObject1);
/* 412 */       return (DoubleMatrix2D)localObject1;
/*     */     }
/*     */     
/* 415 */     int i = QuickBitVector.mostSignificantBit(paramInt);
/*     */     
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/* 429 */     int j = 0;
/* 430 */     while ((j <= i) && ((paramInt & 1 << j) == 0))
/*     */     {
/* 432 */       localBlas.dgemm(false, false, 1.0D, paramDoubleMatrix2D, paramDoubleMatrix2D, 0.0D, (DoubleMatrix2D)localObject1);
/* 433 */       localObject2 = paramDoubleMatrix2D;paramDoubleMatrix2D = (DoubleMatrix2D)localObject1;localObject1 = localObject2;
/* 434 */       j++;
/*     */     }
/*     */     
/* 437 */     Object localObject2 = paramDoubleMatrix2D.copy();
/* 438 */     j++;
/* 439 */     for (; j <= i; j++)
/*     */     {
/* 441 */       localBlas.dgemm(false, false, 1.0D, paramDoubleMatrix2D, paramDoubleMatrix2D, 0.0D, (DoubleMatrix2D)localObject1);
/* 442 */       Object localObject3 = paramDoubleMatrix2D;paramDoubleMatrix2D = (DoubleMatrix2D)localObject1;localObject1 = localObject3;
/*     */       
/* 444 */       if ((paramInt & 1 << j) != 0)
/*     */       {
/* 446 */         localBlas.dgemm(false, false, 1.0D, (DoubleMatrix2D)localObject2, paramDoubleMatrix2D, 0.0D, (DoubleMatrix2D)localObject1);
/* 447 */         localObject3 = localObject2;localObject2 = localObject1;localObject1 = localObject3;
/*     */       }
/*     */     }
/*     */     
/* 451 */     return (DoubleMatrix2D)localObject2;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   public Property property()
/*     */   {
/* 459 */     return this.property;
/*     */   }
/*     */   
/*     */ 
/*     */   private QRDecomposition qr(DoubleMatrix2D paramDoubleMatrix2D)
/*     */   {
/* 465 */     return new QRDecomposition(paramDoubleMatrix2D);
/*     */   }
/*     */   
/*     */ 
/*     */   public int rank(DoubleMatrix2D paramDoubleMatrix2D)
/*     */   {
/* 471 */     return svd(paramDoubleMatrix2D).rank();
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public void setProperty(Property paramProperty)
/*     */   {
/* 481 */     if ((this == DEFAULT) && (paramProperty != this.property)) throw new IllegalArgumentException("Attempted to modify immutable object.");
/* 482 */     if ((this == ZERO) && (paramProperty != this.property)) throw new IllegalArgumentException("Attempted to modify immutable object.");
/* 483 */     this.property = paramProperty;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */   public DoubleMatrix2D solve(DoubleMatrix2D paramDoubleMatrix2D1, DoubleMatrix2D paramDoubleMatrix2D2)
/*     */   {
/* 490 */     return paramDoubleMatrix2D1.rows() == paramDoubleMatrix2D1.columns() ? lu(paramDoubleMatrix2D1).solve(paramDoubleMatrix2D2) : qr(paramDoubleMatrix2D1).solve(paramDoubleMatrix2D2);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */   public DoubleMatrix2D solveTranspose(DoubleMatrix2D paramDoubleMatrix2D1, DoubleMatrix2D paramDoubleMatrix2D2)
/*     */   {
/* 497 */     return solve(transpose(paramDoubleMatrix2D1), transpose(paramDoubleMatrix2D2));
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
/*     */   private DoubleMatrix2D subMatrix(DoubleMatrix2D paramDoubleMatrix2D, int[] paramArrayOfInt, int paramInt1, int paramInt2)
/*     */   {
/* 512 */     int i = paramInt2 - paramInt1 + 1;
/* 513 */     int j = paramDoubleMatrix2D.rows();
/* 514 */     paramDoubleMatrix2D = paramDoubleMatrix2D.viewPart(0, paramInt1, j, i);
/* 515 */     DoubleMatrix2D localDoubleMatrix2D = paramDoubleMatrix2D.like(paramArrayOfInt.length, i);
/*     */     
/* 517 */     int k = paramArrayOfInt.length;
/* 518 */     do { int m = paramArrayOfInt[k];
/* 519 */       if ((m < 0) || (m >= j))
/* 520 */         throw new IndexOutOfBoundsException("Illegal Index");
/* 521 */       localDoubleMatrix2D.viewRow(k).assign(paramDoubleMatrix2D.viewRow(m));k--;
/* 517 */     } while (k >= 0);
/*     */     
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/* 523 */     return localDoubleMatrix2D;
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
/*     */   private DoubleMatrix2D subMatrix(DoubleMatrix2D paramDoubleMatrix2D, int paramInt1, int paramInt2, int[] paramArrayOfInt)
/*     */   {
/* 538 */     if (paramInt2 - paramInt1 >= paramDoubleMatrix2D.rows()) throw new IndexOutOfBoundsException("Too many rows");
/* 539 */     int i = paramInt2 - paramInt1 + 1;
/* 540 */     int j = paramDoubleMatrix2D.columns();
/* 541 */     paramDoubleMatrix2D = paramDoubleMatrix2D.viewPart(paramInt1, 0, i, j);
/* 542 */     DoubleMatrix2D localDoubleMatrix2D = paramDoubleMatrix2D.like(i, paramArrayOfInt.length);
/*     */     
/* 544 */     int k = paramArrayOfInt.length;
/* 545 */     do { int m = paramArrayOfInt[k];
/* 546 */       if ((m < 0) || (m >= j))
/* 547 */         throw new IndexOutOfBoundsException("Illegal Index");
/* 548 */       localDoubleMatrix2D.viewColumn(k).assign(paramDoubleMatrix2D.viewColumn(m));k--;
/* 544 */     } while (k >= 0);
/*     */     
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/* 550 */     return localDoubleMatrix2D;
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
/*     */   public DoubleMatrix2D subMatrix(DoubleMatrix2D paramDoubleMatrix2D, int paramInt1, int paramInt2, int paramInt3, int paramInt4)
/*     */   {
/* 566 */     return paramDoubleMatrix2D.viewPart(paramInt1, paramInt3, paramInt2 - paramInt1 + 1, paramInt4 - paramInt3 + 1);
/*     */   }
/*     */   
/*     */ 
/*     */   private SingularValueDecomposition svd(DoubleMatrix2D paramDoubleMatrix2D)
/*     */   {
/* 572 */     return new SingularValueDecomposition(paramDoubleMatrix2D);
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
/*     */   public String toString(DoubleMatrix2D paramDoubleMatrix2D)
/*     */   {
/* 590 */     ObjectArrayList localObjectArrayList1 = new ObjectArrayList();
/* 591 */     ObjectArrayList localObjectArrayList2 = new ObjectArrayList();
/* 592 */     String str1 = "Illegal operation or error: ";
/*     */     
/*     */ 
/* 595 */     localObjectArrayList1.add("cond");
/* 596 */     try { localObjectArrayList2.add(String.valueOf(cond(paramDoubleMatrix2D)));
/* 597 */     } catch (IllegalArgumentException localIllegalArgumentException1) { localObjectArrayList2.add(str1 + localIllegalArgumentException1.getMessage());
/*     */     }
/* 599 */     localObjectArrayList1.add("det");
/* 600 */     try { localObjectArrayList2.add(String.valueOf(det(paramDoubleMatrix2D)));
/* 601 */     } catch (IllegalArgumentException localIllegalArgumentException2) { localObjectArrayList2.add(str1 + localIllegalArgumentException2.getMessage());
/*     */     }
/* 603 */     localObjectArrayList1.add("norm1");
/* 604 */     try { localObjectArrayList2.add(String.valueOf(norm1(paramDoubleMatrix2D)));
/* 605 */     } catch (IllegalArgumentException localIllegalArgumentException3) { localObjectArrayList2.add(str1 + localIllegalArgumentException3.getMessage());
/*     */     }
/* 607 */     localObjectArrayList1.add("norm2");
/* 608 */     try { localObjectArrayList2.add(String.valueOf(norm2(paramDoubleMatrix2D)));
/* 609 */     } catch (IllegalArgumentException localIllegalArgumentException4) { localObjectArrayList2.add(str1 + localIllegalArgumentException4.getMessage());
/*     */     }
/* 611 */     localObjectArrayList1.add("normF");
/* 612 */     try { localObjectArrayList2.add(String.valueOf(normF(paramDoubleMatrix2D)));
/* 613 */     } catch (IllegalArgumentException localIllegalArgumentException5) { localObjectArrayList2.add(str1 + localIllegalArgumentException5.getMessage());
/*     */     }
/* 615 */     localObjectArrayList1.add("normInfinity");
/* 616 */     try { localObjectArrayList2.add(String.valueOf(normInfinity(paramDoubleMatrix2D)));
/* 617 */     } catch (IllegalArgumentException localIllegalArgumentException6) { localObjectArrayList2.add(str1 + localIllegalArgumentException6.getMessage());
/*     */     }
/* 619 */     localObjectArrayList1.add("rank");
/* 620 */     try { localObjectArrayList2.add(String.valueOf(rank(paramDoubleMatrix2D)));
/* 621 */     } catch (IllegalArgumentException localIllegalArgumentException7) { localObjectArrayList2.add(str1 + localIllegalArgumentException7.getMessage());
/*     */     }
/* 623 */     localObjectArrayList1.add("trace");
/* 624 */     try { localObjectArrayList2.add(String.valueOf(trace(paramDoubleMatrix2D)));
/* 625 */     } catch (IllegalArgumentException localIllegalArgumentException8) { localObjectArrayList2.add(str1 + localIllegalArgumentException8.getMessage());
/*     */     }
/*     */     
/*     */ 
/* 629 */     IntComparator local3 = new IntComparator() { private final ObjectArrayList val$names;
/*     */       
/* 631 */       public int compare(int paramAnonymousInt1, int paramAnonymousInt2) { return Property.get(this.val$names, paramAnonymousInt1).compareTo(Property.get(this.val$names, paramAnonymousInt2));
/*     */       }
/* 633 */     };
/* 634 */     Swapper local4 = new Swapper() { private final ObjectArrayList val$names;
/*     */       private final ObjectArrayList val$values;
/*     */       
/* 637 */       public void swap(int paramAnonymousInt1, int paramAnonymousInt2) { Object localObject = this.val$names.get(paramAnonymousInt1);this.val$names.set(paramAnonymousInt1, this.val$names.get(paramAnonymousInt2));this.val$names.set(paramAnonymousInt2, localObject);
/* 638 */         localObject = this.val$values.get(paramAnonymousInt1);this.val$values.set(paramAnonymousInt1, this.val$values.get(paramAnonymousInt2));this.val$values.set(paramAnonymousInt2, localObject);
/*     */       }
/* 640 */     };
/* 641 */     GenericSorting.quickSort(0, localObjectArrayList1.size(), local3, local4);
/*     */     
/*     */ 
/* 644 */     int i = 0;
/* 645 */     for (int j = 0; j < localObjectArrayList1.size(); j++) {
/* 646 */       int k = ((String)localObjectArrayList1.get(j)).length();
/* 647 */       i = Math.max(k, i);
/*     */     }
/*     */     
/*     */ 
/* 651 */     StringBuffer localStringBuffer = new StringBuffer();
/* 652 */     for (int m = 0; m < localObjectArrayList1.size(); m++) {
/* 653 */       String str2 = (String)localObjectArrayList1.get(m);
/* 654 */       localStringBuffer.append(str2);
/* 655 */       localStringBuffer.append(Property.blanks(i - str2.length()));
/* 656 */       localStringBuffer.append(" : ");
/* 657 */       localStringBuffer.append(localObjectArrayList2.get(m));
/* 658 */       if (m < localObjectArrayList1.size() - 1) {
/* 659 */         localStringBuffer.append('\n');
/*     */       }
/*     */     }
/* 662 */     return localStringBuffer.toString();
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
/*     */   public static final Algebra ZERO;
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   protected Property property;
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public String toVerboseString(DoubleMatrix2D paramDoubleMatrix2D)
/*     */   {
/* 821 */     String str = "Illegal operation or error upon construction of ";
/* 822 */     StringBuffer localStringBuffer = new StringBuffer();
/*     */     
/* 824 */     localStringBuffer.append("A = ");
/* 825 */     localStringBuffer.append(paramDoubleMatrix2D);
/*     */     
/* 827 */     localStringBuffer.append("\n\n" + toString(paramDoubleMatrix2D));
/* 828 */     localStringBuffer.append("\n\n" + Property.DEFAULT.toString(paramDoubleMatrix2D));
/*     */     
/* 830 */     LUDecomposition localLUDecomposition = null;
/* 831 */     try { localLUDecomposition = new LUDecomposition(paramDoubleMatrix2D);
/*     */     } catch (IllegalArgumentException localIllegalArgumentException1) {
/* 833 */       localStringBuffer.append("\n\n" + str + " LUDecomposition: " + localIllegalArgumentException1.getMessage());
/*     */     }
/* 835 */     if (localLUDecomposition != null) { localStringBuffer.append("\n\n" + localLUDecomposition.toString());
/*     */     }
/* 837 */     QRDecomposition localQRDecomposition = null;
/* 838 */     try { localQRDecomposition = new QRDecomposition(paramDoubleMatrix2D);
/*     */     } catch (IllegalArgumentException localIllegalArgumentException2) {
/* 840 */       localStringBuffer.append("\n\n" + str + " QRDecomposition: " + localIllegalArgumentException2.getMessage());
/*     */     }
/* 842 */     if (localQRDecomposition != null) { localStringBuffer.append("\n\n" + localQRDecomposition.toString());
/*     */     }
/* 844 */     CholeskyDecomposition localCholeskyDecomposition = null;
/* 845 */     try { localCholeskyDecomposition = new CholeskyDecomposition(paramDoubleMatrix2D);
/*     */     } catch (IllegalArgumentException localIllegalArgumentException3) {
/* 847 */       localStringBuffer.append("\n\n" + str + " CholeskyDecomposition: " + localIllegalArgumentException3.getMessage());
/*     */     }
/* 849 */     if (localCholeskyDecomposition != null) { localStringBuffer.append("\n\n" + localCholeskyDecomposition.toString());
/*     */     }
/* 851 */     EigenvalueDecomposition localEigenvalueDecomposition = null;
/* 852 */     try { localEigenvalueDecomposition = new EigenvalueDecomposition(paramDoubleMatrix2D);
/*     */     } catch (IllegalArgumentException localIllegalArgumentException4) {
/* 854 */       localStringBuffer.append("\n\n" + str + " EigenvalueDecomposition: " + localIllegalArgumentException4.getMessage());
/*     */     }
/* 856 */     if (localEigenvalueDecomposition != null) { localStringBuffer.append("\n\n" + localEigenvalueDecomposition.toString());
/*     */     }
/* 858 */     SingularValueDecomposition localSingularValueDecomposition = null;
/* 859 */     try { localSingularValueDecomposition = new SingularValueDecomposition(paramDoubleMatrix2D);
/*     */     } catch (IllegalArgumentException localIllegalArgumentException5) {
/* 861 */       localStringBuffer.append("\n\n" + str + " SingularValueDecomposition: " + localIllegalArgumentException5.getMessage());
/*     */     }
/* 863 */     if (localSingularValueDecomposition != null) { localStringBuffer.append("\n\n" + localSingularValueDecomposition.toString());
/*     */     }
/* 865 */     return localStringBuffer.toString();
/*     */   }
/*     */   
/*     */ 
/*     */   public double trace(DoubleMatrix2D paramDoubleMatrix2D)
/*     */   {
/* 871 */     double d = 0.0D;
/* 872 */     int i = Math.min(paramDoubleMatrix2D.rows(), paramDoubleMatrix2D.columns());
/* 873 */     do { d += paramDoubleMatrix2D.getQuick(i, i);i--;
/* 872 */     } while (i >= 0);
/*     */     
/*     */ 
/* 875 */     return d;
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
/*     */   public DoubleMatrix2D transpose(DoubleMatrix2D paramDoubleMatrix2D)
/*     */   {
/* 904 */     return paramDoubleMatrix2D.viewDice();
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   protected DoubleMatrix2D trapezoidalLower(DoubleMatrix2D paramDoubleMatrix2D)
/*     */   {
/* 912 */     int i = paramDoubleMatrix2D.rows();
/* 913 */     int j = paramDoubleMatrix2D.columns();
/* 914 */     int k = i;
/* 915 */     do { int m = j;
/* 916 */       do { if (k < m) { paramDoubleMatrix2D.setQuick(k, m, 0.0D);
/*     */         }
/* 915 */         m--; } while (m >= 0);
/* 914 */       k--; } while (k >= 0);
/*     */     
/*     */ 
/*     */ 
/*     */ 
/* 919 */     return paramDoubleMatrix2D;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   private DoubleMatrix2D xmultOuter(DoubleMatrix1D paramDoubleMatrix1D1, DoubleMatrix1D paramDoubleMatrix1D2)
/*     */   {
/* 929 */     DoubleMatrix2D localDoubleMatrix2D = paramDoubleMatrix1D1.like2D(paramDoubleMatrix1D1.size(), paramDoubleMatrix1D2.size());
/* 930 */     multOuter(paramDoubleMatrix1D1, paramDoubleMatrix1D2, localDoubleMatrix2D);
/* 931 */     return localDoubleMatrix2D;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   private DoubleMatrix2D xpowSlow(DoubleMatrix2D paramDoubleMatrix2D, int paramInt)
/*     */   {
/* 943 */     DoubleMatrix2D localDoubleMatrix2D = paramDoubleMatrix2D.copy();
/* 944 */     for (int i = 0; i < paramInt - 1; i++) {
/* 945 */       localDoubleMatrix2D = mult(localDoubleMatrix2D, paramDoubleMatrix2D);
/*     */     }
/*     */     
/* 948 */     return localDoubleMatrix2D;
/*     */   }
/*     */ }


/* Location:              /home/menick/Scrivania/ProcessMining/CNMining/CNMining.jar!/cern/colt/matrix/linalg/Algebra.class
 * Java compiler version: 1 (45.3)
 * JD-Core Version:       0.7.1
 */