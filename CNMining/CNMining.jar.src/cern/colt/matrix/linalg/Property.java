/*      */ package cern.colt.matrix.linalg;
/*      */ 
/*      */ import cern.colt.GenericSorting;
/*      */ import cern.colt.PersistentObject;
/*      */ import cern.colt.Swapper;
/*      */ import cern.colt.function.IntComparator;
/*      */ import cern.colt.list.ObjectArrayList;
/*      */ import cern.colt.matrix.DoubleFactory2D;
/*      */ import cern.colt.matrix.DoubleMatrix1D;
/*      */ import cern.colt.matrix.DoubleMatrix2D;
/*      */ import cern.colt.matrix.DoubleMatrix3D;
/*      */ import cern.colt.matrix.impl.AbstractFormatter;
/*      */ import cern.colt.matrix.impl.AbstractMatrix1D;
/*      */ import cern.colt.matrix.impl.AbstractMatrix2D;
/*      */ import cern.colt.matrix.impl.AbstractMatrix3D;
/*      */ import cern.jet.math.Functions;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ public class Property
/*      */   extends PersistentObject
/*      */ {
/*  175 */   public static final Property DEFAULT = new Property(1.0E-9D);
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*  180 */   public static final Property ZERO = new Property(0.0D);
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*  185 */   public static final Property TWELVE = new Property(1.0E-12D);
/*      */   
/*      */   protected double tolerance;
/*      */   
/*      */ 
/*      */   private Property()
/*      */   {
/*  192 */     this(1.0E-9D);
/*      */   }
/*      */   
/*      */ 
/*      */   public Property(double paramDouble)
/*      */   {
/*  198 */     this.tolerance = Math.abs(paramDouble);
/*      */   }
/*      */   
/*      */ 
/*      */   protected static String blanks(int paramInt)
/*      */   {
/*  204 */     if (paramInt < 0) paramInt = 0;
/*  205 */     StringBuffer localStringBuffer = new StringBuffer(paramInt);
/*  206 */     for (int i = 0; i < paramInt; i++) {
/*  207 */       localStringBuffer.append(' ');
/*      */     }
/*  209 */     return localStringBuffer.toString();
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */   public void checkRectangular(DoubleMatrix2D paramDoubleMatrix2D)
/*      */   {
/*  216 */     if (paramDoubleMatrix2D.rows() < paramDoubleMatrix2D.columns()) {
/*  217 */       throw new IllegalArgumentException("Matrix must be rectangular: " + AbstractFormatter.shape(paramDoubleMatrix2D));
/*      */     }
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */   public void checkSquare(DoubleMatrix2D paramDoubleMatrix2D)
/*      */   {
/*  225 */     if (paramDoubleMatrix2D.rows() != paramDoubleMatrix2D.columns()) { throw new IllegalArgumentException("Matrix must be square: " + AbstractFormatter.shape(paramDoubleMatrix2D));
/*      */     }
/*      */   }
/*      */   
/*      */   public double density(DoubleMatrix2D paramDoubleMatrix2D)
/*      */   {
/*  231 */     return paramDoubleMatrix2D.cardinality() / paramDoubleMatrix2D.size();
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public boolean equals(DoubleMatrix1D paramDoubleMatrix1D, double paramDouble)
/*      */   {
/*  243 */     if (paramDoubleMatrix1D == null) return false;
/*  244 */     double d1 = tolerance();
/*  245 */     int i = paramDoubleMatrix1D.size();
/*      */     do
/*      */     {
/*  248 */       double d2 = paramDoubleMatrix1D.getQuick(i);
/*  249 */       double d3 = Math.abs(paramDouble - d2);
/*  250 */       if ((d3 != d3) && (((paramDouble != paramDouble) && (d2 != d2)) || (paramDouble == d2))) d3 = 0.0D;
/*  251 */       if (d3 > d1) return false;
/*  245 */       i--; } while (i >= 0);
/*      */     
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*  253 */     return true;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public boolean equals(DoubleMatrix1D paramDoubleMatrix1D1, DoubleMatrix1D paramDoubleMatrix1D2)
/*      */   {
/*  267 */     if (paramDoubleMatrix1D1 == paramDoubleMatrix1D2) return true;
/*  268 */     if ((paramDoubleMatrix1D1 == null) || (paramDoubleMatrix1D2 == null)) return false;
/*  269 */     int i = paramDoubleMatrix1D1.size();
/*  270 */     if (i != paramDoubleMatrix1D2.size()) { return false;
/*      */     }
/*  272 */     double d1 = tolerance();
/*  273 */     int j = i;
/*      */     do
/*      */     {
/*  276 */       double d2 = paramDoubleMatrix1D1.getQuick(j);
/*  277 */       double d3 = paramDoubleMatrix1D2.getQuick(j);
/*  278 */       double d4 = Math.abs(d3 - d2);
/*  279 */       if ((d4 != d4) && (((d3 != d3) && (d2 != d2)) || (d3 == d2))) d4 = 0.0D;
/*  280 */       if (d4 > d1) return false;
/*  273 */       j--; } while (j >= 0);
/*      */     
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*  282 */     return true;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public boolean equals(DoubleMatrix2D paramDoubleMatrix2D, double paramDouble)
/*      */   {
/*  294 */     if (paramDoubleMatrix2D == null) return false;
/*  295 */     int i = paramDoubleMatrix2D.rows();
/*  296 */     int j = paramDoubleMatrix2D.columns();
/*      */     
/*  298 */     double d1 = tolerance();
/*  299 */     int k = i;
/*  300 */     do { int m = j;
/*      */       do
/*      */       {
/*  303 */         double d2 = paramDoubleMatrix2D.getQuick(k, m);
/*  304 */         double d3 = Math.abs(paramDouble - d2);
/*  305 */         if ((d3 != d3) && (((paramDouble != paramDouble) && (d2 != d2)) || (paramDouble == d2))) d3 = 0.0D;
/*  306 */         if (d3 > d1) return false;
/*  300 */         m--; } while (m >= 0);
/*  299 */       k--; } while (k >= 0);
/*      */     
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*  309 */     return true;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public boolean equals(DoubleMatrix2D paramDoubleMatrix2D1, DoubleMatrix2D paramDoubleMatrix2D2)
/*      */   {
/*  323 */     if (paramDoubleMatrix2D1 == paramDoubleMatrix2D2) return true;
/*  324 */     if ((paramDoubleMatrix2D1 == null) || (paramDoubleMatrix2D2 == null)) return false;
/*  325 */     int i = paramDoubleMatrix2D1.rows();
/*  326 */     int j = paramDoubleMatrix2D1.columns();
/*  327 */     if ((j != paramDoubleMatrix2D2.columns()) || (i != paramDoubleMatrix2D2.rows())) { return false;
/*      */     }
/*  329 */     double d1 = tolerance();
/*  330 */     int k = i;
/*  331 */     do { int m = j;
/*      */       do
/*      */       {
/*  334 */         double d2 = paramDoubleMatrix2D1.getQuick(k, m);
/*  335 */         double d3 = paramDoubleMatrix2D2.getQuick(k, m);
/*  336 */         double d4 = Math.abs(d3 - d2);
/*  337 */         if ((d4 != d4) && (((d3 != d3) && (d2 != d2)) || (d3 == d2))) d4 = 0.0D;
/*  338 */         if (d4 > d1) return false;
/*  331 */         m--; } while (m >= 0);
/*  330 */       k--; } while (k >= 0);
/*      */     
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*  341 */     return true;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public boolean equals(DoubleMatrix3D paramDoubleMatrix3D, double paramDouble)
/*      */   {
/*  353 */     if (paramDoubleMatrix3D == null) return false;
/*  354 */     int i = paramDoubleMatrix3D.rows();
/*  355 */     int j = paramDoubleMatrix3D.columns();
/*      */     
/*  357 */     double d1 = tolerance();
/*  358 */     int k = paramDoubleMatrix3D.slices();
/*  359 */     do { int m = i;
/*  360 */       do { int n = j;
/*      */         do
/*      */         {
/*  363 */           double d2 = paramDoubleMatrix3D.getQuick(k, m, n);
/*  364 */           double d3 = Math.abs(paramDouble - d2);
/*  365 */           if ((d3 != d3) && (((paramDouble != paramDouble) && (d2 != d2)) || (paramDouble == d2))) d3 = 0.0D;
/*  366 */           if (d3 > d1) return false;
/*  360 */           n--; } while (n >= 0);
/*  359 */         m--; } while (m >= 0);
/*  358 */       k--; } while (k >= 0);
/*      */     
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*  370 */     return true;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public boolean equals(DoubleMatrix3D paramDoubleMatrix3D1, DoubleMatrix3D paramDoubleMatrix3D2)
/*      */   {
/*  384 */     if (paramDoubleMatrix3D1 == paramDoubleMatrix3D2) return true;
/*  385 */     if ((paramDoubleMatrix3D1 == null) || (paramDoubleMatrix3D2 == null)) return false;
/*  386 */     int i = paramDoubleMatrix3D1.slices();
/*  387 */     int j = paramDoubleMatrix3D1.rows();
/*  388 */     int k = paramDoubleMatrix3D1.columns();
/*  389 */     if ((k != paramDoubleMatrix3D2.columns()) || (j != paramDoubleMatrix3D2.rows()) || (i != paramDoubleMatrix3D2.slices())) { return false;
/*      */     }
/*  391 */     double d1 = tolerance();
/*  392 */     int m = i;
/*  393 */     do { int n = j;
/*  394 */       do { int i1 = k;
/*      */         do
/*      */         {
/*  397 */           double d2 = paramDoubleMatrix3D1.getQuick(m, n, i1);
/*  398 */           double d3 = paramDoubleMatrix3D2.getQuick(m, n, i1);
/*  399 */           double d4 = Math.abs(d3 - d2);
/*  400 */           if ((d4 != d4) && (((d3 != d3) && (d2 != d2)) || (d3 == d2))) d4 = 0.0D;
/*  401 */           if (d4 > d1) return false;
/*  394 */           i1--; } while (i1 >= 0);
/*  393 */         n--; } while (n >= 0);
/*  392 */       m--; } while (m >= 0);
/*      */     
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*  405 */     return true;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public void generateNonSingular(DoubleMatrix2D paramDoubleMatrix2D)
/*      */   {
/*  414 */     checkSquare(paramDoubleMatrix2D);
/*  415 */     Functions localFunctions = Functions.functions;
/*  416 */     int i = Math.min(paramDoubleMatrix2D.rows(), paramDoubleMatrix2D.columns());
/*  417 */     int j = i;
/*  418 */     do { paramDoubleMatrix2D.setQuick(j, j, 0.0D);j--;
/*  417 */     } while (j >= 0);
/*      */     
/*      */ 
/*  420 */     int k = i;
/*  421 */     do { double d1 = paramDoubleMatrix2D.viewRow(k).aggregate(Functions.plus, Functions.abs);
/*  422 */       double d2 = paramDoubleMatrix2D.viewColumn(k).aggregate(Functions.plus, Functions.abs);
/*  423 */       paramDoubleMatrix2D.setQuick(k, k, Math.max(d1, d2) + k + 1.0D);k--;
/*  420 */     } while (k >= 0);
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   protected static String get(ObjectArrayList paramObjectArrayList, int paramInt)
/*      */   {
/*  429 */     return (String)paramObjectArrayList.get(paramInt);
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */   public boolean isDiagonal(DoubleMatrix2D paramDoubleMatrix2D)
/*      */   {
/*  436 */     double d = tolerance();
/*  437 */     int i = paramDoubleMatrix2D.rows();
/*  438 */     int j = paramDoubleMatrix2D.columns();
/*  439 */     int k = i;
/*  440 */     do { int m = j;
/*      */       do {
/*  442 */         if ((k != m) && (Math.abs(paramDoubleMatrix2D.getQuick(k, m)) > d)) return false;
/*  440 */         m--; } while (m >= 0);
/*  439 */       k--; } while (k >= 0);
/*      */     
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*  445 */     return true;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public boolean isDiagonallyDominantByColumn(DoubleMatrix2D paramDoubleMatrix2D)
/*      */   {
/*  455 */     Functions localFunctions = Functions.functions;
/*  456 */     double d1 = tolerance();
/*  457 */     int i = Math.min(paramDoubleMatrix2D.rows(), paramDoubleMatrix2D.columns());
/*  458 */     int j = i;
/*  459 */     do { double d2 = Math.abs(paramDoubleMatrix2D.getQuick(j, j));
/*  460 */       d2 += d2;
/*  461 */       if (d2 <= paramDoubleMatrix2D.viewColumn(j).aggregate(Functions.plus, Functions.abs)) return false;
/*  458 */       j--; } while (j >= 0);
/*      */     
/*      */ 
/*      */ 
/*      */ 
/*  463 */     return true;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public boolean isDiagonallyDominantByRow(DoubleMatrix2D paramDoubleMatrix2D)
/*      */   {
/*  473 */     Functions localFunctions = Functions.functions;
/*  474 */     double d1 = tolerance();
/*  475 */     int i = Math.min(paramDoubleMatrix2D.rows(), paramDoubleMatrix2D.columns());
/*  476 */     int j = i;
/*  477 */     do { double d2 = Math.abs(paramDoubleMatrix2D.getQuick(j, j));
/*  478 */       d2 += d2;
/*  479 */       if (d2 <= paramDoubleMatrix2D.viewRow(j).aggregate(Functions.plus, Functions.abs)) return false;
/*  476 */       j--; } while (j >= 0);
/*      */     
/*      */ 
/*      */ 
/*      */ 
/*  481 */     return true;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */   public boolean isIdentity(DoubleMatrix2D paramDoubleMatrix2D)
/*      */   {
/*  488 */     double d1 = tolerance();
/*  489 */     int i = paramDoubleMatrix2D.rows();
/*  490 */     int j = paramDoubleMatrix2D.columns();
/*  491 */     int k = i;
/*  492 */     do { int m = j;
/*  493 */       do { double d2 = paramDoubleMatrix2D.getQuick(k, m);
/*  494 */         if (k == m) {
/*  495 */           if (Math.abs(1.0D - d2) >= d1) return false;
/*      */         }
/*  497 */         else if (Math.abs(d2) > d1) return false;
/*  492 */         m--; } while (m >= 0);
/*  491 */       k--; } while (k >= 0);
/*      */     
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*  500 */     return true;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */   public boolean isLowerBidiagonal(DoubleMatrix2D paramDoubleMatrix2D)
/*      */   {
/*  507 */     double d = tolerance();
/*  508 */     int i = paramDoubleMatrix2D.rows();
/*  509 */     int j = paramDoubleMatrix2D.columns();
/*  510 */     int k = i;
/*  511 */     do { int m = j;
/*  512 */       do { if ((k != m) && (k != m + 1))
/*      */         {
/*  514 */           if (Math.abs(paramDoubleMatrix2D.getQuick(k, m)) > d) return false;
/*      */         }
/*  511 */         m--; } while (m >= 0);
/*  510 */       k--; } while (k >= 0);
/*      */     
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*  518 */     return true;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */   public boolean isLowerTriangular(DoubleMatrix2D paramDoubleMatrix2D)
/*      */   {
/*  525 */     double d = tolerance();
/*  526 */     int i = paramDoubleMatrix2D.rows();
/*  527 */     int j = paramDoubleMatrix2D.columns();
/*  528 */     int k = j;
/*  529 */     do { int m = Math.min(k, i);
/*      */       do {
/*  531 */         if (Math.abs(paramDoubleMatrix2D.getQuick(m, k)) > d) return false;
/*  529 */         m--; } while (m >= 0);
/*  528 */       k--; } while (k >= 0);
/*      */     
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*  534 */     return true;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */   public boolean isNonNegative(DoubleMatrix2D paramDoubleMatrix2D)
/*      */   {
/*  542 */     int i = paramDoubleMatrix2D.rows();
/*  543 */     int j = paramDoubleMatrix2D.columns();
/*  544 */     int k = i;
/*  545 */     do { int m = j;
/*  546 */       do { if (paramDoubleMatrix2D.getQuick(k, m) < 0.0D) return false;
/*  545 */         m--; } while (m >= 0);
/*  544 */       k--; } while (k >= 0);
/*      */     
/*      */ 
/*      */ 
/*      */ 
/*  549 */     return true;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */   public boolean isOrthogonal(DoubleMatrix2D paramDoubleMatrix2D)
/*      */   {
/*  556 */     checkSquare(paramDoubleMatrix2D);
/*  557 */     return equals(paramDoubleMatrix2D.zMult(paramDoubleMatrix2D, null, 1.0D, 0.0D, false, true), DoubleFactory2D.dense.identity(paramDoubleMatrix2D.rows()));
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */   public boolean isPositive(DoubleMatrix2D paramDoubleMatrix2D)
/*      */   {
/*  565 */     int i = paramDoubleMatrix2D.rows();
/*  566 */     int j = paramDoubleMatrix2D.columns();
/*  567 */     int k = i;
/*  568 */     do { int m = j;
/*  569 */       do { if (paramDoubleMatrix2D.getQuick(k, m) <= 0.0D) return false;
/*  568 */         m--; } while (m >= 0);
/*  567 */       k--; } while (k >= 0);
/*      */     
/*      */ 
/*      */ 
/*      */ 
/*  572 */     return true;
/*      */   }
/*      */   
/*      */ 
/*      */   public boolean isSingular(DoubleMatrix2D paramDoubleMatrix2D)
/*      */   {
/*  578 */     return Math.abs(Algebra.DEFAULT.det(paramDoubleMatrix2D)) < tolerance();
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */   public boolean isSkewSymmetric(DoubleMatrix2D paramDoubleMatrix2D)
/*      */   {
/*  585 */     checkSquare(paramDoubleMatrix2D);
/*  586 */     double d = tolerance();
/*  587 */     int i = paramDoubleMatrix2D.rows();
/*  588 */     int j = paramDoubleMatrix2D.columns();
/*  589 */     int k = i;
/*  590 */     do { int m = i;
/*      */       do {
/*  592 */         if (Math.abs(paramDoubleMatrix2D.getQuick(k, m) + paramDoubleMatrix2D.getQuick(m, k)) > d) return false;
/*  590 */         m--; } while (m >= 0);
/*  589 */       k--; } while (k >= 0);
/*      */     
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*  595 */     return true;
/*      */   }
/*      */   
/*      */ 
/*      */   public boolean isSquare(DoubleMatrix2D paramDoubleMatrix2D)
/*      */   {
/*  601 */     return paramDoubleMatrix2D.rows() == paramDoubleMatrix2D.columns();
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */   public boolean isStrictlyLowerTriangular(DoubleMatrix2D paramDoubleMatrix2D)
/*      */   {
/*  608 */     double d = tolerance();
/*  609 */     int i = paramDoubleMatrix2D.rows();
/*  610 */     int j = paramDoubleMatrix2D.columns();
/*  611 */     int k = j;
/*  612 */     do { int m = Math.min(i, k + 1);
/*      */       do {
/*  614 */         if (Math.abs(paramDoubleMatrix2D.getQuick(m, k)) > d) return false;
/*  612 */         m--; } while (m >= 0);
/*  611 */       k--; } while (k >= 0);
/*      */     
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*  617 */     return true;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */   public boolean isStrictlyTriangular(DoubleMatrix2D paramDoubleMatrix2D)
/*      */   {
/*  624 */     if (!isTriangular(paramDoubleMatrix2D)) { return false;
/*      */     }
/*  626 */     double d = tolerance();
/*  627 */     int i = Math.min(paramDoubleMatrix2D.rows(), paramDoubleMatrix2D.columns());
/*      */     do {
/*  629 */       if (Math.abs(paramDoubleMatrix2D.getQuick(i, i)) > d) return false;
/*  627 */       i--; } while (i >= 0);
/*      */     
/*      */ 
/*      */ 
/*  631 */     return true;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */   public boolean isStrictlyUpperTriangular(DoubleMatrix2D paramDoubleMatrix2D)
/*      */   {
/*  638 */     double d = tolerance();
/*  639 */     int i = paramDoubleMatrix2D.rows();
/*  640 */     int j = paramDoubleMatrix2D.columns();
/*  641 */     int k = j;
/*  642 */     do { int m = i;
/*      */       do {
/*  644 */         if (Math.abs(paramDoubleMatrix2D.getQuick(m, k)) > d) return false;
/*  642 */         m--; } while (m >= k);
/*  641 */       k--; } while (k >= 0);
/*      */     
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*  647 */     return true;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */   public boolean isSymmetric(DoubleMatrix2D paramDoubleMatrix2D)
/*      */   {
/*  654 */     checkSquare(paramDoubleMatrix2D);
/*  655 */     return equals(paramDoubleMatrix2D, paramDoubleMatrix2D.viewDice());
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */   public boolean isTriangular(DoubleMatrix2D paramDoubleMatrix2D)
/*      */   {
/*  662 */     return (isLowerTriangular(paramDoubleMatrix2D)) || (isUpperTriangular(paramDoubleMatrix2D));
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */   public boolean isTridiagonal(DoubleMatrix2D paramDoubleMatrix2D)
/*      */   {
/*  669 */     double d = tolerance();
/*  670 */     int i = paramDoubleMatrix2D.rows();
/*  671 */     int j = paramDoubleMatrix2D.columns();
/*  672 */     int k = i;
/*  673 */     do { int m = j;
/*  674 */       do { if (Math.abs(k - m) > 1)
/*      */         {
/*  676 */           if (Math.abs(paramDoubleMatrix2D.getQuick(k, m)) > d) return false;
/*      */         }
/*  673 */         m--; } while (m >= 0);
/*  672 */       k--; } while (k >= 0);
/*      */     
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*  680 */     return true;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */   public boolean isUnitTriangular(DoubleMatrix2D paramDoubleMatrix2D)
/*      */   {
/*  687 */     if (!isTriangular(paramDoubleMatrix2D)) { return false;
/*      */     }
/*  689 */     double d = tolerance();
/*  690 */     int i = Math.min(paramDoubleMatrix2D.rows(), paramDoubleMatrix2D.columns());
/*      */     do {
/*  692 */       if (Math.abs(1.0D - paramDoubleMatrix2D.getQuick(i, i)) > d) return false;
/*  690 */       i--; } while (i >= 0);
/*      */     
/*      */ 
/*      */ 
/*  694 */     return true;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */   public boolean isUpperBidiagonal(DoubleMatrix2D paramDoubleMatrix2D)
/*      */   {
/*  701 */     double d = tolerance();
/*  702 */     int i = paramDoubleMatrix2D.rows();
/*  703 */     int j = paramDoubleMatrix2D.columns();
/*  704 */     int k = i;
/*  705 */     do { int m = j;
/*  706 */       do { if ((k != m) && (k != m - 1))
/*      */         {
/*  708 */           if (Math.abs(paramDoubleMatrix2D.getQuick(k, m)) > d) return false;
/*      */         }
/*  705 */         m--; } while (m >= 0);
/*  704 */       k--; } while (k >= 0);
/*      */     
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*  712 */     return true;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */   public boolean isUpperTriangular(DoubleMatrix2D paramDoubleMatrix2D)
/*      */   {
/*  719 */     double d = tolerance();
/*  720 */     int i = paramDoubleMatrix2D.rows();
/*  721 */     int j = paramDoubleMatrix2D.columns();
/*  722 */     int k = j;
/*  723 */     do { int m = i;
/*      */       do {
/*  725 */         if (Math.abs(paramDoubleMatrix2D.getQuick(m, k)) > d) return false;
/*  723 */         m--; } while (m > k);
/*  722 */       k--; } while (k >= 0);
/*      */     
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*  728 */     return true;
/*      */   }
/*      */   
/*      */ 
/*      */   public boolean isZero(DoubleMatrix2D paramDoubleMatrix2D)
/*      */   {
/*  734 */     return equals(paramDoubleMatrix2D, 0.0D);
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public int lowerBandwidth(DoubleMatrix2D paramDoubleMatrix2D)
/*      */   {
/*  748 */     checkSquare(paramDoubleMatrix2D);
/*  749 */     double d = tolerance();
/*  750 */     int i = paramDoubleMatrix2D.rows();
/*      */     
/*  752 */     int j = i;
/*  753 */     do { int k = i - j;
/*  754 */       do { int m = k + j;
/*      */         
/*  756 */         if (Math.abs(paramDoubleMatrix2D.getQuick(m, k)) > d) return j;
/*  753 */         k--; } while (k >= 0);
/*  752 */       j--; } while (j >= 0);
/*      */     
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*  759 */     return 0;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public int semiBandwidth(DoubleMatrix2D paramDoubleMatrix2D)
/*      */   {
/*  905 */     checkSquare(paramDoubleMatrix2D);
/*  906 */     double d = tolerance();
/*  907 */     int i = paramDoubleMatrix2D.rows();
/*      */     
/*  909 */     int j = i;
/*  910 */     do { int k = i - j;
/*  911 */       do { int m = k + j;
/*      */         
/*      */ 
/*  914 */         if (Math.abs(paramDoubleMatrix2D.getQuick(m, k)) > d) return j + 1;
/*  915 */         if (Math.abs(paramDoubleMatrix2D.getQuick(k, m)) > d) return j + 1;
/*  910 */         k--; } while (k >= 0);
/*  909 */       j--; } while (j >= 0);
/*      */     
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*  918 */     return 1;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */   public void setTolerance(double paramDouble)
/*      */   {
/*  925 */     if ((this == DEFAULT) || (this == ZERO) || (this == TWELVE)) {
/*  926 */       throw new IllegalArgumentException("Attempted to modify immutable object.");
/*      */     }
/*      */     
/*  929 */     this.tolerance = Math.abs(paramDouble);
/*      */   }
/*      */   
/*      */ 
/*      */   public double tolerance()
/*      */   {
/*  935 */     return this.tolerance;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public String toString(DoubleMatrix2D paramDoubleMatrix2D)
/*      */   {
/*  972 */     ObjectArrayList localObjectArrayList1 = new ObjectArrayList();
/*  973 */     ObjectArrayList localObjectArrayList2 = new ObjectArrayList();
/*  974 */     String str1 = "Illegal operation or error: ";
/*      */     
/*      */ 
/*  977 */     localObjectArrayList1.add("density");
/*  978 */     try { localObjectArrayList2.add(String.valueOf(density(paramDoubleMatrix2D)));
/*  979 */     } catch (IllegalArgumentException localIllegalArgumentException1) { localObjectArrayList2.add(str1 + localIllegalArgumentException1.getMessage());
/*      */     }
/*      */     
/*  982 */     localObjectArrayList1.add("isDiagonal");
/*  983 */     try { localObjectArrayList2.add(String.valueOf(isDiagonal(paramDoubleMatrix2D)));
/*  984 */     } catch (IllegalArgumentException localIllegalArgumentException2) { localObjectArrayList2.add(str1 + localIllegalArgumentException2.getMessage());
/*      */     }
/*      */     
/*  987 */     localObjectArrayList1.add("isDiagonallyDominantByRow");
/*  988 */     try { localObjectArrayList2.add(String.valueOf(isDiagonallyDominantByRow(paramDoubleMatrix2D)));
/*  989 */     } catch (IllegalArgumentException localIllegalArgumentException3) { localObjectArrayList2.add(str1 + localIllegalArgumentException3.getMessage());
/*      */     }
/*      */     
/*  992 */     localObjectArrayList1.add("isDiagonallyDominantByColumn");
/*  993 */     try { localObjectArrayList2.add(String.valueOf(isDiagonallyDominantByColumn(paramDoubleMatrix2D)));
/*  994 */     } catch (IllegalArgumentException localIllegalArgumentException4) { localObjectArrayList2.add(str1 + localIllegalArgumentException4.getMessage());
/*      */     }
/*  996 */     localObjectArrayList1.add("isIdentity");
/*  997 */     try { localObjectArrayList2.add(String.valueOf(isIdentity(paramDoubleMatrix2D)));
/*  998 */     } catch (IllegalArgumentException localIllegalArgumentException5) { localObjectArrayList2.add(str1 + localIllegalArgumentException5.getMessage());
/*      */     }
/* 1000 */     localObjectArrayList1.add("isLowerBidiagonal");
/* 1001 */     try { localObjectArrayList2.add(String.valueOf(isLowerBidiagonal(paramDoubleMatrix2D)));
/* 1002 */     } catch (IllegalArgumentException localIllegalArgumentException6) { localObjectArrayList2.add(str1 + localIllegalArgumentException6.getMessage());
/*      */     }
/* 1004 */     localObjectArrayList1.add("isLowerTriangular");
/* 1005 */     try { localObjectArrayList2.add(String.valueOf(isLowerTriangular(paramDoubleMatrix2D)));
/* 1006 */     } catch (IllegalArgumentException localIllegalArgumentException7) { localObjectArrayList2.add(str1 + localIllegalArgumentException7.getMessage());
/*      */     }
/* 1008 */     localObjectArrayList1.add("isNonNegative");
/* 1009 */     try { localObjectArrayList2.add(String.valueOf(isNonNegative(paramDoubleMatrix2D)));
/* 1010 */     } catch (IllegalArgumentException localIllegalArgumentException8) { localObjectArrayList2.add(str1 + localIllegalArgumentException8.getMessage());
/*      */     }
/* 1012 */     localObjectArrayList1.add("isOrthogonal");
/* 1013 */     try { localObjectArrayList2.add(String.valueOf(isOrthogonal(paramDoubleMatrix2D)));
/* 1014 */     } catch (IllegalArgumentException localIllegalArgumentException9) { localObjectArrayList2.add(str1 + localIllegalArgumentException9.getMessage());
/*      */     }
/* 1016 */     localObjectArrayList1.add("isPositive");
/* 1017 */     try { localObjectArrayList2.add(String.valueOf(isPositive(paramDoubleMatrix2D)));
/* 1018 */     } catch (IllegalArgumentException localIllegalArgumentException10) { localObjectArrayList2.add(str1 + localIllegalArgumentException10.getMessage());
/*      */     }
/* 1020 */     localObjectArrayList1.add("isSingular");
/* 1021 */     try { localObjectArrayList2.add(String.valueOf(isSingular(paramDoubleMatrix2D)));
/* 1022 */     } catch (IllegalArgumentException localIllegalArgumentException11) { localObjectArrayList2.add(str1 + localIllegalArgumentException11.getMessage());
/*      */     }
/* 1024 */     localObjectArrayList1.add("isSkewSymmetric");
/* 1025 */     try { localObjectArrayList2.add(String.valueOf(isSkewSymmetric(paramDoubleMatrix2D)));
/* 1026 */     } catch (IllegalArgumentException localIllegalArgumentException12) { localObjectArrayList2.add(str1 + localIllegalArgumentException12.getMessage());
/*      */     }
/* 1028 */     localObjectArrayList1.add("isSquare");
/* 1029 */     try { localObjectArrayList2.add(String.valueOf(isSquare(paramDoubleMatrix2D)));
/* 1030 */     } catch (IllegalArgumentException localIllegalArgumentException13) { localObjectArrayList2.add(str1 + localIllegalArgumentException13.getMessage());
/*      */     }
/* 1032 */     localObjectArrayList1.add("isStrictlyLowerTriangular");
/* 1033 */     try { localObjectArrayList2.add(String.valueOf(isStrictlyLowerTriangular(paramDoubleMatrix2D)));
/* 1034 */     } catch (IllegalArgumentException localIllegalArgumentException14) { localObjectArrayList2.add(str1 + localIllegalArgumentException14.getMessage());
/*      */     }
/* 1036 */     localObjectArrayList1.add("isStrictlyTriangular");
/* 1037 */     try { localObjectArrayList2.add(String.valueOf(isStrictlyTriangular(paramDoubleMatrix2D)));
/* 1038 */     } catch (IllegalArgumentException localIllegalArgumentException15) { localObjectArrayList2.add(str1 + localIllegalArgumentException15.getMessage());
/*      */     }
/* 1040 */     localObjectArrayList1.add("isStrictlyUpperTriangular");
/* 1041 */     try { localObjectArrayList2.add(String.valueOf(isStrictlyUpperTriangular(paramDoubleMatrix2D)));
/* 1042 */     } catch (IllegalArgumentException localIllegalArgumentException16) { localObjectArrayList2.add(str1 + localIllegalArgumentException16.getMessage());
/*      */     }
/* 1044 */     localObjectArrayList1.add("isSymmetric");
/* 1045 */     try { localObjectArrayList2.add(String.valueOf(isSymmetric(paramDoubleMatrix2D)));
/* 1046 */     } catch (IllegalArgumentException localIllegalArgumentException17) { localObjectArrayList2.add(str1 + localIllegalArgumentException17.getMessage());
/*      */     }
/* 1048 */     localObjectArrayList1.add("isTriangular");
/* 1049 */     try { localObjectArrayList2.add(String.valueOf(isTriangular(paramDoubleMatrix2D)));
/* 1050 */     } catch (IllegalArgumentException localIllegalArgumentException18) { localObjectArrayList2.add(str1 + localIllegalArgumentException18.getMessage());
/*      */     }
/* 1052 */     localObjectArrayList1.add("isTridiagonal");
/* 1053 */     try { localObjectArrayList2.add(String.valueOf(isTridiagonal(paramDoubleMatrix2D)));
/* 1054 */     } catch (IllegalArgumentException localIllegalArgumentException19) { localObjectArrayList2.add(str1 + localIllegalArgumentException19.getMessage());
/*      */     }
/* 1056 */     localObjectArrayList1.add("isUnitTriangular");
/* 1057 */     try { localObjectArrayList2.add(String.valueOf(isUnitTriangular(paramDoubleMatrix2D)));
/* 1058 */     } catch (IllegalArgumentException localIllegalArgumentException20) { localObjectArrayList2.add(str1 + localIllegalArgumentException20.getMessage());
/*      */     }
/* 1060 */     localObjectArrayList1.add("isUpperBidiagonal");
/* 1061 */     try { localObjectArrayList2.add(String.valueOf(isUpperBidiagonal(paramDoubleMatrix2D)));
/* 1062 */     } catch (IllegalArgumentException localIllegalArgumentException21) { localObjectArrayList2.add(str1 + localIllegalArgumentException21.getMessage());
/*      */     }
/* 1064 */     localObjectArrayList1.add("isUpperTriangular");
/* 1065 */     try { localObjectArrayList2.add(String.valueOf(isUpperTriangular(paramDoubleMatrix2D)));
/* 1066 */     } catch (IllegalArgumentException localIllegalArgumentException22) { localObjectArrayList2.add(str1 + localIllegalArgumentException22.getMessage());
/*      */     }
/* 1068 */     localObjectArrayList1.add("isZero");
/* 1069 */     try { localObjectArrayList2.add(String.valueOf(isZero(paramDoubleMatrix2D)));
/* 1070 */     } catch (IllegalArgumentException localIllegalArgumentException23) { localObjectArrayList2.add(str1 + localIllegalArgumentException23.getMessage());
/*      */     }
/* 1072 */     localObjectArrayList1.add("lowerBandwidth");
/* 1073 */     try { localObjectArrayList2.add(String.valueOf(lowerBandwidth(paramDoubleMatrix2D)));
/* 1074 */     } catch (IllegalArgumentException localIllegalArgumentException24) { localObjectArrayList2.add(str1 + localIllegalArgumentException24.getMessage());
/*      */     }
/* 1076 */     localObjectArrayList1.add("semiBandwidth");
/* 1077 */     try { localObjectArrayList2.add(String.valueOf(semiBandwidth(paramDoubleMatrix2D)));
/* 1078 */     } catch (IllegalArgumentException localIllegalArgumentException25) { localObjectArrayList2.add(str1 + localIllegalArgumentException25.getMessage());
/*      */     }
/* 1080 */     localObjectArrayList1.add("upperBandwidth");
/* 1081 */     try { localObjectArrayList2.add(String.valueOf(upperBandwidth(paramDoubleMatrix2D)));
/* 1082 */     } catch (IllegalArgumentException localIllegalArgumentException26) { localObjectArrayList2.add(str1 + localIllegalArgumentException26.getMessage());
/*      */     }
/*      */     
/*      */ 
/* 1086 */     IntComparator local1 = new IntComparator() { private final ObjectArrayList val$names;
/*      */       
/* 1088 */       public int compare(int paramAnonymousInt1, int paramAnonymousInt2) { return Property.get(this.val$names, paramAnonymousInt1).compareTo(Property.get(this.val$names, paramAnonymousInt2));
/*      */       }
/* 1090 */     };
/* 1091 */     Swapper local2 = new Swapper() { private final ObjectArrayList val$names;
/*      */       private final ObjectArrayList val$values;
/*      */       
/* 1094 */       public void swap(int paramAnonymousInt1, int paramAnonymousInt2) { Object localObject = this.val$names.get(paramAnonymousInt1);this.val$names.set(paramAnonymousInt1, this.val$names.get(paramAnonymousInt2));this.val$names.set(paramAnonymousInt2, localObject);
/* 1095 */         localObject = this.val$values.get(paramAnonymousInt1);this.val$values.set(paramAnonymousInt1, this.val$values.get(paramAnonymousInt2));this.val$values.set(paramAnonymousInt2, localObject);
/*      */       }
/* 1097 */     };
/* 1098 */     GenericSorting.quickSort(0, localObjectArrayList1.size(), local1, local2);
/*      */     
/*      */ 
/* 1101 */     int i = 0;
/* 1102 */     for (int j = 0; j < localObjectArrayList1.size(); j++) {
/* 1103 */       int k = ((String)localObjectArrayList1.get(j)).length();
/* 1104 */       i = Math.max(k, i);
/*      */     }
/*      */     
/*      */ 
/* 1108 */     StringBuffer localStringBuffer = new StringBuffer();
/* 1109 */     for (int m = 0; m < localObjectArrayList1.size(); m++) {
/* 1110 */       String str2 = (String)localObjectArrayList1.get(m);
/* 1111 */       localStringBuffer.append(str2);
/* 1112 */       localStringBuffer.append(blanks(i - str2.length()));
/* 1113 */       localStringBuffer.append(" : ");
/* 1114 */       localStringBuffer.append(localObjectArrayList2.get(m));
/* 1115 */       if (m < localObjectArrayList1.size() - 1) {
/* 1116 */         localStringBuffer.append('\n');
/*      */       }
/*      */     }
/* 1119 */     return localStringBuffer.toString();
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public int upperBandwidth(DoubleMatrix2D paramDoubleMatrix2D)
/*      */   {
/* 1134 */     checkSquare(paramDoubleMatrix2D);
/* 1135 */     double d = tolerance();
/* 1136 */     int i = paramDoubleMatrix2D.rows();
/*      */     
/* 1138 */     int j = i;
/* 1139 */     do { int k = i - j;
/* 1140 */       do { int m = k + j;
/*      */         
/* 1142 */         if (Math.abs(paramDoubleMatrix2D.getQuick(k, m)) > d) return j;
/* 1139 */         k--; } while (k >= 0);
/* 1138 */       j--; } while (j >= 0);
/*      */     
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/* 1145 */     return 0;
/*      */   }
/*      */ }


/* Location:              /home/menick/Scrivania/ProcessMining/CNMining/CNMining.jar!/cern/colt/matrix/linalg/Property.class
 * Java compiler version: 1 (45.3)
 * JD-Core Version:       0.7.1
 */