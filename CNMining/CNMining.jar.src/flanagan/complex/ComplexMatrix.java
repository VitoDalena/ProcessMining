/*      */ package flanagan.complex;
/*      */ 
/*      */ import flanagan.math.Fmath;
/*      */ import flanagan.math.Matrix;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ public class ComplexMatrix
/*      */ {
/*   50 */   private int nrow = 0;
/*   51 */   private int ncol = 0;
/*   52 */   private Complex[][] matrix = (Complex[][])null;
/*   53 */   private int[] index = null;
/*   54 */   private double dswap = 1.0D;
/*      */   
/*      */ 
/*      */   private static final double TINY = 1.0E-30D;
/*      */   
/*      */ 
/*      */   public ComplexMatrix(int nrow, int ncol)
/*      */   {
/*   62 */     this.nrow = nrow;
/*   63 */     this.ncol = ncol;
/*   64 */     this.matrix = Complex.twoDarray(nrow, ncol);
/*   65 */     this.index = new int[nrow];
/*   66 */     for (int i = 0; i < nrow; i++) this.index[i] = i;
/*   67 */     this.dswap = 1.0D;
/*      */   }
/*      */   
/*      */   public ComplexMatrix(int nrow, int ncol, Complex constant)
/*      */   {
/*   72 */     this.nrow = nrow;
/*   73 */     this.ncol = ncol;
/*   74 */     this.matrix = Complex.twoDarray(nrow, ncol, constant);
/*   75 */     this.index = new int[nrow];
/*   76 */     for (int i = 0; i < nrow; i++) this.index[i] = i;
/*   77 */     this.dswap = 1.0D;
/*      */   }
/*      */   
/*      */   public ComplexMatrix(Complex[][] twoD)
/*      */   {
/*   82 */     this.nrow = twoD.length;
/*   83 */     this.ncol = twoD[0].length;
/*   84 */     for (int i = 0; i < this.nrow; i++) {
/*   85 */       if (twoD[i].length != this.ncol) throw new IllegalArgumentException("All rows must have the same length");
/*      */     }
/*   87 */     this.matrix = twoD;
/*   88 */     this.index = new int[this.nrow];
/*   89 */     for (int i = 0; i < this.nrow; i++) this.index[i] = i;
/*   90 */     this.dswap = 1.0D;
/*      */   }
/*      */   
/*      */   public ComplexMatrix(ComplexMatrix bb)
/*      */   {
/*   95 */     this.nrow = bb.nrow;
/*   96 */     this.ncol = bb.ncol;
/*   97 */     this.matrix = bb.matrix;
/*   98 */     this.index = bb.index;
/*   99 */     this.dswap = bb.dswap;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */   public void setTwoDarray(Complex[][] aarray)
/*      */   {
/*  106 */     if (this.nrow != aarray.length) throw new IllegalArgumentException("row length of this ComplexMatrix differs from that of the 2D array argument");
/*  107 */     if (this.ncol != aarray[0].length) throw new IllegalArgumentException("column length of this ComplexMatrix differs from that of the 2D array argument");
/*  108 */     for (int i = 0; i < this.nrow; i++) {
/*  109 */       if (aarray[i].length != this.ncol) throw new IllegalArgumentException("All rows must have the same length");
/*  110 */       for (int j = 0; j < this.ncol; j++) {
/*  111 */         this.matrix[i][j] = Complex.copy(aarray[i][j]);
/*      */       }
/*      */     }
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */   public void setElement(int i, int j, Complex aa)
/*      */   {
/*  121 */     this.matrix[i][j] = Complex.copy(aa);
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public void setElement(int i, int j, double aa, double bb)
/*      */   {
/*  130 */     this.matrix[i][j].reset(aa, bb);
/*      */   }
/*      */   
/*      */ 
/*      */   public void setSubMatrix(int i, int j, Complex[][] subMatrix)
/*      */   {
/*  136 */     int k = subMatrix.length;
/*  137 */     int l = subMatrix[0].length;
/*  138 */     if (i > k) throw new IllegalArgumentException("row indices inverted");
/*  139 */     if (j > l) throw new IllegalArgumentException("column indices inverted");
/*  140 */     int n = k - i + 1;int m = l - j + 1;
/*  141 */     for (int p = 0; p < n; p++) {
/*  142 */       for (int q = 0; q < m; q++) {
/*  143 */         this.matrix[(i + p)][(j + q)] = Complex.copy(subMatrix[p][q]);
/*      */       }
/*      */     }
/*      */   }
/*      */   
/*      */ 
/*      */   public void setSubMatrix(int i, int j, int k, int l, Complex[][] subMatrix)
/*      */   {
/*  151 */     if (i > k) throw new IllegalArgumentException("row indices inverted");
/*  152 */     if (j > l) throw new IllegalArgumentException("column indices inverted");
/*  153 */     int n = k - i + 1;int m = l - j + 1;
/*  154 */     for (int p = 0; p < n; p++) {
/*  155 */       for (int q = 0; q < m; q++) {
/*  156 */         this.matrix[(i + p)][(j + q)] = Complex.copy(subMatrix[p][q]);
/*      */       }
/*      */     }
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */   public void setSubMatrix(int[] row, int[] col, Complex[][] subMatrix)
/*      */   {
/*  166 */     int n = row.length;
/*  167 */     int m = col.length;
/*  168 */     for (int p = 0; p < n; p++) {
/*  169 */       for (int q = 0; q < m; q++) {
/*  170 */         this.matrix[row[p]][col[q]] = Complex.copy(subMatrix[p][q]);
/*      */       }
/*      */     }
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */   public static ComplexMatrix identityMatrix(int nrow)
/*      */   {
/*  179 */     ComplexMatrix u = new ComplexMatrix(nrow, nrow);
/*  180 */     for (int i = 0; i < nrow; i++) {
/*  181 */       u.matrix[i][i] = Complex.plusOne();
/*      */     }
/*  183 */     return u;
/*      */   }
/*      */   
/*      */   public static ComplexMatrix scalarMatrix(int nrow, Complex diagconst)
/*      */   {
/*  188 */     ComplexMatrix u = new ComplexMatrix(nrow, nrow);
/*  189 */     Complex[][] uarray = u.getArrayReference();
/*  190 */     for (int i = 0; i < nrow; i++) {
/*  191 */       for (int j = i; j < nrow; j++) {
/*  192 */         if (i == j) {
/*  193 */           uarray[i][j] = Complex.copy(diagconst);
/*      */         }
/*      */       }
/*      */     }
/*  197 */     return u;
/*      */   }
/*      */   
/*      */   public static ComplexMatrix diagonalMatrix(int nrow, Complex[] diag)
/*      */   {
/*  202 */     if (diag.length != nrow) throw new IllegalArgumentException("matrix dimension differs from diagonal array length");
/*  203 */     ComplexMatrix u = new ComplexMatrix(nrow, nrow);
/*  204 */     Complex[][] uarray = u.getArrayReference();
/*  205 */     for (int i = 0; i < nrow; i++) {
/*  206 */       for (int j = i; j < nrow; j++) {
/*  207 */         if (i == j) {
/*  208 */           uarray[i][j] = Complex.copy(diag[i]);
/*      */         }
/*      */       }
/*      */     }
/*  212 */     return u;
/*      */   }
/*      */   
/*      */ 
/*      */   public static ComplexMatrix columnMatrix(Complex[] darray)
/*      */   {
/*  218 */     int nr = darray.length;
/*  219 */     ComplexMatrix pp = new ComplexMatrix(nr, 1);
/*  220 */     for (int i = 0; i < nr; i++) pp.matrix[i][0] = darray[i];
/*  221 */     return pp;
/*      */   }
/*      */   
/*      */ 
/*      */   public static ComplexMatrix rowMatrix(Complex[] darray)
/*      */   {
/*  227 */     int nc = darray.length;
/*  228 */     ComplexMatrix pp = new ComplexMatrix(1, nc);
/*  229 */     for (int i = 0; i < nc; i++) pp.matrix[0][i] = darray[i];
/*  230 */     return pp;
/*      */   }
/*      */   
/*      */ 
/*      */   public static ComplexMatrix toComplexColumnMatrix(Complex[] carray)
/*      */   {
/*  236 */     int nr = carray.length;
/*  237 */     ComplexMatrix cc = new ComplexMatrix(nr, 1);
/*  238 */     for (int i = 0; i < nr; i++) cc.matrix[i][0] = carray[i].copy();
/*  239 */     return cc;
/*      */   }
/*      */   
/*      */   public static ComplexMatrix toComplexColumnMatrix(double[] darray)
/*      */   {
/*  244 */     int nr = darray.length;
/*  245 */     ComplexMatrix cc = new ComplexMatrix(nr, 1);
/*  246 */     for (int i = 0; i < nr; i++) cc.matrix[i][0].reset(darray[i], 0.0D);
/*  247 */     return cc;
/*      */   }
/*      */   
/*      */   public static ComplexMatrix toComplexRowMatrix(Complex[] carray)
/*      */   {
/*  252 */     int nc = carray.length;
/*  253 */     ComplexMatrix cc = new ComplexMatrix(1, nc);
/*  254 */     for (int i = 0; i < nc; i++) cc.matrix[0][i] = carray[i].copy();
/*  255 */     return cc;
/*      */   }
/*      */   
/*      */   public static ComplexMatrix toComplexRowMatrix(double[] darray)
/*      */   {
/*  260 */     int nc = darray.length;
/*  261 */     ComplexMatrix cc = new ComplexMatrix(1, nc);
/*  262 */     for (int i = 0; i < nc; i++) cc.matrix[0][i].reset(darray[i], 0.0D);
/*  263 */     return cc;
/*      */   }
/*      */   
/*      */   public static ComplexMatrix toComplexMatrix(Matrix marray)
/*      */   {
/*  268 */     int nr = marray.getNrow();
/*  269 */     int nc = marray.getNcol();
/*      */     
/*  271 */     ComplexMatrix pp = new ComplexMatrix(nr, nc);
/*  272 */     for (int i = 0; i < nr; i++) {
/*  273 */       for (int j = 0; j < nc; j++) {
/*  274 */         pp.matrix[i][j].reset(marray.getElementCopy(i, j), 0.0D);
/*      */       }
/*      */     }
/*  277 */     return pp;
/*      */   }
/*      */   
/*      */   public static ComplexMatrix toComplexMatrix(double[][] darray)
/*      */   {
/*  282 */     int nr = darray.length;
/*  283 */     int nc = darray[0].length;
/*  284 */     for (int i = 1; i < nr; i++) {
/*  285 */       if (darray[i].length != nc) throw new IllegalArgumentException("All rows must have the same length");
/*      */     }
/*  287 */     ComplexMatrix pp = new ComplexMatrix(nr, nc);
/*  288 */     for (int i = 0; i < pp.nrow; i++) {
/*  289 */       for (int j = 0; j < pp.ncol; j++) {
/*  290 */         pp.matrix[i][j].reset(darray[i][j], 0.0D);
/*      */       }
/*      */     }
/*  293 */     return pp;
/*      */   }
/*      */   
/*      */ 
/*      */   public int getNrow()
/*      */   {
/*  299 */     return this.nrow;
/*      */   }
/*      */   
/*      */   public int getNcol()
/*      */   {
/*  304 */     return this.ncol;
/*      */   }
/*      */   
/*      */   public Complex[][] getArrayReference()
/*      */   {
/*  309 */     return this.matrix;
/*      */   }
/*      */   
/*      */   public Complex[][] getArray()
/*      */   {
/*  314 */     return this.matrix;
/*      */   }
/*      */   
/*      */ 
/*      */   public Complex[][] getArrayPointer()
/*      */   {
/*  320 */     return this.matrix;
/*      */   }
/*      */   
/*      */   public Complex[][] getArrayCopy()
/*      */   {
/*  325 */     Complex[][] c = new Complex[this.nrow][this.ncol];
/*  326 */     for (int i = 0; i < this.nrow; i++) {
/*  327 */       for (int j = 0; j < this.ncol; j++) {
/*  328 */         c[i][j] = Complex.copy(this.matrix[i][j]);
/*      */       }
/*      */     }
/*  331 */     return c;
/*      */   }
/*      */   
/*      */   public Complex getElementReference(int i, int j)
/*      */   {
/*  336 */     return this.matrix[i][j];
/*      */   }
/*      */   
/*      */ 
/*      */   public Complex getElementPointer(int i, int j)
/*      */   {
/*  342 */     return this.matrix[i][j];
/*      */   }
/*      */   
/*      */   public Complex getElementCopy(int i, int j)
/*      */   {
/*  347 */     return Complex.copy(this.matrix[i][j]);
/*      */   }
/*      */   
/*      */ 
/*      */   public ComplexMatrix getSubMatrix(int i, int j, int k, int l)
/*      */   {
/*  353 */     if (i > k) throw new IllegalArgumentException("row indices inverted");
/*  354 */     if (j > l) throw new IllegalArgumentException("column indices inverted");
/*  355 */     int n = k - i + 1;int m = l - j + 1;
/*  356 */     ComplexMatrix subMatrix = new ComplexMatrix(n, m);
/*  357 */     Complex[][] sarray = subMatrix.getArrayReference();
/*  358 */     for (int p = 0; p < n; p++) {
/*  359 */       for (int q = 0; q < m; q++) {
/*  360 */         sarray[p][q] = Complex.copy(this.matrix[(i + p)][(j + q)]);
/*      */       }
/*      */     }
/*  363 */     return subMatrix;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */   public ComplexMatrix getSubMatrix(int[] row, int[] col)
/*      */   {
/*  370 */     int n = row.length;
/*  371 */     int m = col.length;
/*  372 */     ComplexMatrix subMatrix = new ComplexMatrix(n, m);
/*  373 */     Complex[][] sarray = subMatrix.getArrayReference();
/*  374 */     for (int i = 0; i < n; i++) {
/*  375 */       for (int j = 0; j < m; j++) {
/*  376 */         sarray[i][j] = Complex.copy(this.matrix[row[i]][col[j]]);
/*      */       }
/*      */     }
/*  379 */     return subMatrix;
/*      */   }
/*      */   
/*      */   public int[] getIndexReference()
/*      */   {
/*  384 */     return this.index;
/*      */   }
/*      */   
/*      */   public int[] getIndexPointer()
/*      */   {
/*  389 */     return this.index;
/*      */   }
/*      */   
/*      */   public int[] getIndexCopy()
/*      */   {
/*  394 */     int[] indcopy = new int[this.nrow];
/*  395 */     for (int i = 0; i < this.nrow; i++) {
/*  396 */       indcopy[i] = this.index[i];
/*      */     }
/*  398 */     return indcopy;
/*      */   }
/*      */   
/*      */   public double getSwap()
/*      */   {
/*  403 */     return this.dswap;
/*      */   }
/*      */   
/*      */ 
/*      */   public static ComplexMatrix copy(ComplexMatrix a)
/*      */   {
/*  409 */     if (a == null) {
/*  410 */       return null;
/*      */     }
/*      */     
/*  413 */     int nr = a.getNrow();
/*  414 */     int nc = a.getNcol();
/*  415 */     Complex[][] aarray = a.getArrayReference();
/*  416 */     ComplexMatrix b = new ComplexMatrix(nr, nc);
/*  417 */     b.nrow = nr;
/*  418 */     b.ncol = nc;
/*  419 */     Complex[][] barray = b.getArrayReference();
/*  420 */     for (int i = 0; i < nr; i++) {
/*  421 */       for (int j = 0; j < nc; j++) {
/*  422 */         barray[i][j] = Complex.copy(aarray[i][j]);
/*      */       }
/*      */     }
/*  425 */     for (int i = 0; i < nr; i++) b.index[i] = a.index[i];
/*  426 */     return b;
/*      */   }
/*      */   
/*      */ 
/*      */   public ComplexMatrix copy()
/*      */   {
/*  432 */     if (this == null) {
/*  433 */       return null;
/*      */     }
/*      */     
/*  436 */     int nr = this.nrow;
/*  437 */     int nc = this.ncol;
/*  438 */     ComplexMatrix b = new ComplexMatrix(nr, nc);
/*  439 */     Complex[][] barray = b.getArrayReference();
/*  440 */     b.nrow = nr;
/*  441 */     b.ncol = nc;
/*  442 */     for (int i = 0; i < nr; i++) {
/*  443 */       for (int j = 0; j < nc; j++) {
/*  444 */         barray[i][j] = Complex.copy(this.matrix[i][j]);
/*      */       }
/*      */     }
/*  447 */     for (int i = 0; i < nr; i++) b.index[i] = this.index[i];
/*  448 */     return b;
/*      */   }
/*      */   
/*      */ 
/*      */   public Object clone()
/*      */   {
/*  454 */     if (this == null) {
/*  455 */       return null;
/*      */     }
/*      */     
/*  458 */     int nr = this.nrow;
/*  459 */     int nc = this.ncol;
/*  460 */     ComplexMatrix b = new ComplexMatrix(nr, nc);
/*  461 */     Complex[][] barray = b.getArrayReference();
/*  462 */     b.nrow = nr;
/*  463 */     b.ncol = nc;
/*  464 */     for (int i = 0; i < nr; i++) {
/*  465 */       for (int j = 0; j < nc; j++) {
/*  466 */         barray[i][j] = Complex.copy(this.matrix[i][j]);
/*      */       }
/*      */     }
/*  469 */     for (int i = 0; i < nr; i++) b.index[i] = this.index[i];
/*  470 */     return b;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */   public ComplexMatrix plus(ComplexMatrix bmat)
/*      */   {
/*  477 */     if ((this.nrow != bmat.nrow) || (this.ncol != bmat.ncol)) {
/*  478 */       throw new IllegalArgumentException("Array dimensions do not agree");
/*      */     }
/*  480 */     int nr = bmat.nrow;
/*  481 */     int nc = bmat.ncol;
/*  482 */     ComplexMatrix cmat = new ComplexMatrix(nr, nc);
/*  483 */     Complex[][] carray = cmat.getArrayReference();
/*  484 */     for (int i = 0; i < nr; i++) {
/*  485 */       for (int j = 0; j < nc; j++) {
/*  486 */         carray[i][j] = this.matrix[i][j].plus(bmat.matrix[i][j]);
/*      */       }
/*      */     }
/*  489 */     return cmat;
/*      */   }
/*      */   
/*      */   public ComplexMatrix plus(Complex[][] bmat)
/*      */   {
/*  494 */     int nr = bmat.length;
/*  495 */     int nc = bmat[0].length;
/*  496 */     if ((this.nrow != nr) || (this.ncol != nc)) {
/*  497 */       throw new IllegalArgumentException("Array dimensions do not agree");
/*      */     }
/*  499 */     ComplexMatrix cmat = new ComplexMatrix(nr, nc);
/*  500 */     Complex[][] carray = cmat.getArrayReference();
/*  501 */     for (int i = 0; i < nr; i++) {
/*  502 */       for (int j = 0; j < nc; j++) {
/*  503 */         carray[i][j] = this.matrix[i][j].plus(bmat[i][j]);
/*      */       }
/*      */     }
/*  506 */     return cmat;
/*      */   }
/*      */   
/*      */   public ComplexMatrix plus(Matrix bmat)
/*      */   {
/*  511 */     int nr = bmat.getNrow();
/*  512 */     int nc = bmat.getNcol();
/*  513 */     if ((this.nrow != nr) || (this.ncol != nc)) {
/*  514 */       throw new IllegalArgumentException("Array dimensions do not agree");
/*      */     }
/*      */     
/*  517 */     ComplexMatrix cmat = new ComplexMatrix(nr, nc);
/*  518 */     Complex[][] carray = cmat.getArrayReference();
/*  519 */     for (int i = 0; i < nr; i++) {
/*  520 */       for (int j = 0; j < nc; j++) {
/*  521 */         carray[i][j] = this.matrix[i][j].plus(bmat.getElement(i, j));
/*      */       }
/*      */     }
/*  524 */     return cmat;
/*      */   }
/*      */   
/*      */   public ComplexMatrix plus(double[][] bmat)
/*      */   {
/*  529 */     int nr = bmat.length;
/*  530 */     int nc = bmat[0].length;
/*  531 */     if ((this.nrow != nr) || (this.ncol != nc)) {
/*  532 */       throw new IllegalArgumentException("Array dimensions do not agree");
/*      */     }
/*      */     
/*  535 */     ComplexMatrix cmat = new ComplexMatrix(nr, nc);
/*  536 */     Complex[][] carray = cmat.getArrayReference();
/*  537 */     for (int i = 0; i < nr; i++) {
/*  538 */       for (int j = 0; j < nc; j++) {
/*  539 */         carray[i][j] = this.matrix[i][j].plus(bmat[i][j]);
/*      */       }
/*      */     }
/*  542 */     return cmat;
/*      */   }
/*      */   
/*      */   public static ComplexMatrix plus(ComplexMatrix amat, ComplexMatrix bmat)
/*      */   {
/*  547 */     if ((amat.nrow != bmat.nrow) || (amat.ncol != bmat.ncol)) {
/*  548 */       throw new IllegalArgumentException("Array dimensions do not agree");
/*      */     }
/*  550 */     int nr = amat.nrow;
/*  551 */     int nc = amat.ncol;
/*  552 */     ComplexMatrix cmat = new ComplexMatrix(nr, nc);
/*  553 */     Complex[][] carray = cmat.getArrayReference();
/*  554 */     for (int i = 0; i < nr; i++) {
/*  555 */       for (int j = 0; j < nc; j++) {
/*  556 */         carray[i][j] = amat.matrix[i][j].plus(bmat.matrix[i][j]);
/*      */       }
/*      */     }
/*  559 */     return cmat;
/*      */   }
/*      */   
/*      */   public void plusEquals(ComplexMatrix bmat)
/*      */   {
/*  564 */     if ((this.nrow != bmat.nrow) || (this.ncol != bmat.ncol)) {
/*  565 */       throw new IllegalArgumentException("Array dimensions do not agree");
/*      */     }
/*  567 */     int nr = bmat.nrow;
/*  568 */     int nc = bmat.ncol;
/*      */     
/*  570 */     for (int i = 0; i < nr; i++) {
/*  571 */       for (int j = 0; j < nc; j++) {
/*  572 */         this.matrix[i][j].plusEquals(bmat.matrix[i][j]);
/*      */       }
/*      */     }
/*      */   }
/*      */   
/*      */ 
/*      */   public ComplexMatrix minus(ComplexMatrix bmat)
/*      */   {
/*  580 */     if ((this.nrow != bmat.nrow) || (this.ncol != bmat.ncol)) {
/*  581 */       throw new IllegalArgumentException("Array dimensions do not agree");
/*      */     }
/*  583 */     int nr = this.nrow;
/*  584 */     int nc = this.ncol;
/*  585 */     ComplexMatrix cmat = new ComplexMatrix(nr, nc);
/*  586 */     Complex[][] carray = cmat.getArrayReference();
/*  587 */     for (int i = 0; i < nr; i++) {
/*  588 */       for (int j = 0; j < nc; j++) {
/*  589 */         carray[i][j] = this.matrix[i][j].minus(bmat.matrix[i][j]);
/*      */       }
/*      */     }
/*  592 */     return cmat;
/*      */   }
/*      */   
/*      */   public ComplexMatrix minus(Complex[][] bmat)
/*      */   {
/*  597 */     int nr = bmat.length;
/*  598 */     int nc = bmat[0].length;
/*  599 */     if ((this.nrow != nr) || (this.ncol != nc)) {
/*  600 */       throw new IllegalArgumentException("Array dimensions do not agree");
/*      */     }
/*  602 */     ComplexMatrix cmat = new ComplexMatrix(nr, nc);
/*  603 */     Complex[][] carray = cmat.getArrayReference();
/*  604 */     for (int i = 0; i < nr; i++) {
/*  605 */       for (int j = 0; j < nc; j++) {
/*  606 */         carray[i][j] = this.matrix[i][j].minus(bmat[i][j]);
/*      */       }
/*      */     }
/*  609 */     return cmat;
/*      */   }
/*      */   
/*      */   public ComplexMatrix minus(Matrix bmat)
/*      */   {
/*  614 */     int nr = bmat.getNrow();
/*  615 */     int nc = bmat.getNcol();
/*  616 */     if ((this.nrow != nr) || (this.ncol != nc)) {
/*  617 */       throw new IllegalArgumentException("Array dimensions do not agree");
/*      */     }
/*      */     
/*  620 */     ComplexMatrix cmat = new ComplexMatrix(nr, nc);
/*  621 */     Complex[][] carray = cmat.getArrayReference();
/*  622 */     for (int i = 0; i < nr; i++) {
/*  623 */       for (int j = 0; j < nc; j++) {
/*  624 */         carray[i][j] = this.matrix[i][j].minus(bmat.getElement(i, j));
/*      */       }
/*      */     }
/*  627 */     return cmat;
/*      */   }
/*      */   
/*      */   public ComplexMatrix minus(double[][] bmat)
/*      */   {
/*  632 */     int nr = bmat.length;
/*  633 */     int nc = bmat[0].length;
/*  634 */     if ((this.nrow != nr) || (this.ncol != nc)) {
/*  635 */       throw new IllegalArgumentException("Array dimensions do not agree");
/*      */     }
/*      */     
/*  638 */     ComplexMatrix cmat = new ComplexMatrix(nr, nc);
/*  639 */     Complex[][] carray = cmat.getArrayReference();
/*  640 */     for (int i = 0; i < nr; i++) {
/*  641 */       for (int j = 0; j < nc; j++) {
/*  642 */         carray[i][j] = this.matrix[i][j].minus(bmat[i][j]);
/*      */       }
/*      */     }
/*  645 */     return cmat;
/*      */   }
/*      */   
/*      */ 
/*      */   public static ComplexMatrix minus(ComplexMatrix amat, ComplexMatrix bmat)
/*      */   {
/*  651 */     if ((amat.nrow != bmat.nrow) || (amat.ncol != bmat.ncol)) {
/*  652 */       throw new IllegalArgumentException("Array dimensions do not agree");
/*      */     }
/*  654 */     int nr = amat.nrow;
/*  655 */     int nc = amat.ncol;
/*  656 */     ComplexMatrix cmat = new ComplexMatrix(nr, nc);
/*  657 */     Complex[][] carray = cmat.getArrayReference();
/*  658 */     for (int i = 0; i < nr; i++) {
/*  659 */       for (int j = 0; j < nc; j++) {
/*  660 */         carray[i][j] = amat.matrix[i][j].minus(bmat.matrix[i][j]);
/*      */       }
/*      */     }
/*  663 */     return cmat;
/*      */   }
/*      */   
/*      */   public void minusEquals(ComplexMatrix bmat)
/*      */   {
/*  668 */     if ((this.nrow != bmat.nrow) || (this.ncol != bmat.ncol)) {
/*  669 */       throw new IllegalArgumentException("Array dimensions do not agree");
/*      */     }
/*  671 */     int nr = bmat.nrow;
/*  672 */     int nc = bmat.ncol;
/*      */     
/*  674 */     for (int i = 0; i < nr; i++) {
/*  675 */       for (int j = 0; j < nc; j++) {
/*  676 */         this.matrix[i][j].minusEquals(bmat.matrix[i][j]);
/*      */       }
/*      */     }
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */   public ComplexMatrix times(ComplexMatrix bmat)
/*      */   {
/*  685 */     if (this.ncol != bmat.nrow) { throw new IllegalArgumentException("Nonconformable matrices");
/*      */     }
/*  687 */     ComplexMatrix cmat = new ComplexMatrix(this.nrow, bmat.ncol);
/*  688 */     Complex[][] carray = cmat.getArrayReference();
/*  689 */     Complex sum = new Complex();
/*      */     
/*  691 */     for (int i = 0; i < this.nrow; i++) {
/*  692 */       for (int j = 0; j < bmat.ncol; j++) {
/*  693 */         sum = Complex.zero();
/*  694 */         for (int k = 0; k < this.ncol; k++) {
/*  695 */           sum.plusEquals(this.matrix[i][k].times(bmat.matrix[k][j]));
/*      */         }
/*  697 */         carray[i][j] = Complex.copy(sum);
/*      */       }
/*      */     }
/*  700 */     return cmat;
/*      */   }
/*      */   
/*      */   public ComplexMatrix times(Complex[][] bmat)
/*      */   {
/*  705 */     int nr = bmat.length;
/*  706 */     int nc = bmat[0].length;
/*  707 */     if (this.ncol != nr) { throw new IllegalArgumentException("Nonconformable matrices");
/*      */     }
/*  709 */     ComplexMatrix cmat = new ComplexMatrix(this.nrow, nc);
/*  710 */     Complex[][] carray = cmat.getArrayReference();
/*  711 */     Complex sum = new Complex();
/*      */     
/*  713 */     for (int i = 0; i < this.nrow; i++) {
/*  714 */       for (int j = 0; j < nc; j++) {
/*  715 */         sum = Complex.zero();
/*  716 */         for (int k = 0; k < this.ncol; k++) {
/*  717 */           sum.plusEquals(this.matrix[i][k].times(bmat[k][j]));
/*      */         }
/*  719 */         carray[i][j] = Complex.copy(sum);
/*      */       }
/*      */     }
/*  722 */     return cmat;
/*      */   }
/*      */   
/*      */ 
/*      */   public ComplexMatrix times(Matrix bmat)
/*      */   {
/*  728 */     int nr = bmat.getNrow();
/*  729 */     int nc = bmat.getNcol();
/*      */     
/*  731 */     if (this.ncol != nr) { throw new IllegalArgumentException("Nonconformable matrices");
/*      */     }
/*  733 */     ComplexMatrix cmat = new ComplexMatrix(this.nrow, nc);
/*  734 */     Complex[][] carray = cmat.getArrayReference();
/*  735 */     Complex sum = new Complex();
/*      */     
/*  737 */     for (int i = 0; i < this.nrow; i++) {
/*  738 */       for (int j = 0; j < nc; j++) {
/*  739 */         sum = Complex.zero();
/*  740 */         for (int k = 0; k < this.ncol; k++) {
/*  741 */           sum.plusEquals(this.matrix[i][k].times(bmat.getElement(k, j)));
/*      */         }
/*  743 */         carray[i][j] = Complex.copy(sum);
/*      */       }
/*      */     }
/*  746 */     return cmat;
/*      */   }
/*      */   
/*      */   public ComplexMatrix times(double[][] bmat)
/*      */   {
/*  751 */     int nr = bmat.length;
/*  752 */     int nc = bmat[0].length;
/*  753 */     if (this.ncol != nr) { throw new IllegalArgumentException("Nonconformable matrices");
/*      */     }
/*  755 */     ComplexMatrix cmat = new ComplexMatrix(this.nrow, nc);
/*  756 */     Complex[][] carray = cmat.getArrayReference();
/*  757 */     Complex sum = new Complex();
/*      */     
/*  759 */     for (int i = 0; i < this.nrow; i++) {
/*  760 */       for (int j = 0; j < nc; j++) {
/*  761 */         sum = Complex.zero();
/*  762 */         for (int k = 0; k < this.ncol; k++) {
/*  763 */           sum.plusEquals(this.matrix[i][k].times(bmat[k][j]));
/*      */         }
/*  765 */         carray[i][j] = Complex.copy(sum);
/*      */       }
/*      */     }
/*  768 */     return cmat;
/*      */   }
/*      */   
/*      */ 
/*      */   public ComplexMatrix times(Complex constant)
/*      */   {
/*  774 */     ComplexMatrix cmat = new ComplexMatrix(this.nrow, this.ncol);
/*  775 */     Complex[][] carray = cmat.getArrayReference();
/*      */     
/*  777 */     for (int i = 0; i < this.nrow; i++) {
/*  778 */       for (int j = 0; j < this.ncol; j++) {
/*  779 */         carray[i][j] = this.matrix[i][j].times(constant);
/*      */       }
/*      */     }
/*  782 */     return cmat;
/*      */   }
/*      */   
/*      */ 
/*      */   public ComplexMatrix times(double constant)
/*      */   {
/*  788 */     ComplexMatrix cmat = new ComplexMatrix(this.nrow, this.ncol);
/*  789 */     Complex[][] carray = cmat.getArrayReference();
/*  790 */     Complex cconstant = new Complex(constant, 0.0D);
/*      */     
/*  792 */     for (int i = 0; i < this.nrow; i++) {
/*  793 */       for (int j = 0; j < this.ncol; j++) {
/*  794 */         carray[i][j] = this.matrix[i][j].times(cconstant);
/*      */       }
/*      */     }
/*  797 */     return cmat;
/*      */   }
/*      */   
/*      */   public static ComplexMatrix times(ComplexMatrix amat, ComplexMatrix bmat)
/*      */   {
/*  802 */     if (amat.ncol != bmat.nrow) { throw new IllegalArgumentException("Nonconformable matrices");
/*      */     }
/*  804 */     ComplexMatrix cmat = new ComplexMatrix(amat.nrow, bmat.ncol);
/*  805 */     Complex[][] carray = cmat.getArrayReference();
/*  806 */     Complex sum = new Complex();
/*      */     
/*  808 */     for (int i = 0; i < amat.nrow; i++) {
/*  809 */       for (int j = 0; j < bmat.ncol; j++) {
/*  810 */         sum = Complex.zero();
/*  811 */         for (int k = 0; k < amat.ncol; k++) {
/*  812 */           sum.plusEquals(amat.matrix[i][k].times(bmat.matrix[k][j]));
/*      */         }
/*  814 */         carray[i][j] = Complex.copy(sum);
/*      */       }
/*      */     }
/*  817 */     return cmat;
/*      */   }
/*      */   
/*      */ 
/*      */   public static ComplexMatrix times(ComplexMatrix amat, Complex constant)
/*      */   {
/*  823 */     ComplexMatrix cmat = new ComplexMatrix(amat.nrow, amat.ncol);
/*  824 */     Complex[][] carray = cmat.getArrayReference();
/*      */     
/*  826 */     for (int i = 0; i < amat.nrow; i++) {
/*  827 */       for (int j = 0; j < amat.ncol; j++) {
/*  828 */         carray[i][j] = amat.matrix[i][j].times(constant);
/*      */       }
/*      */     }
/*  831 */     return cmat;
/*      */   }
/*      */   
/*      */   public static ComplexMatrix times(ComplexMatrix amat, double constant)
/*      */   {
/*  836 */     ComplexMatrix cmat = new ComplexMatrix(amat.nrow, amat.ncol);
/*  837 */     Complex[][] carray = cmat.getArrayReference();
/*  838 */     Complex cconstant = new Complex(constant, 0.0D);
/*      */     
/*  840 */     for (int i = 0; i < amat.nrow; i++) {
/*  841 */       for (int j = 0; j < amat.ncol; j++) {
/*  842 */         carray[i][j] = amat.matrix[i][j].times(cconstant);
/*      */       }
/*      */     }
/*  845 */     return cmat;
/*      */   }
/*      */   
/*      */   public void timesEquals(ComplexMatrix bmat)
/*      */   {
/*  850 */     if (this.ncol != bmat.nrow) { throw new IllegalArgumentException("Nonconformable matrices");
/*      */     }
/*  852 */     Complex sum = new Complex();
/*      */     
/*  854 */     for (int i = 0; i < this.nrow; i++) {
/*  855 */       for (int j = 0; j < bmat.ncol; j++) {
/*  856 */         sum = Complex.zero();
/*  857 */         for (int k = 0; k < this.ncol; k++) {
/*  858 */           sum.plusEquals(this.matrix[i][k].times(bmat.matrix[k][j]));
/*      */         }
/*  860 */         this.matrix[i][j] = Complex.copy(sum);
/*      */       }
/*      */     }
/*      */   }
/*      */   
/*      */ 
/*      */   public void timesEquals(Complex constant)
/*      */   {
/*  868 */     for (int i = 0; i < this.nrow; i++) {
/*  869 */       for (int j = 0; j < this.ncol; j++) {
/*  870 */         this.matrix[i][j].timesEquals(constant);
/*      */       }
/*      */     }
/*      */   }
/*      */   
/*      */   public void timesEquals(double constant)
/*      */   {
/*  877 */     Complex cconstant = new Complex(constant, 0.0D);
/*      */     
/*  879 */     for (int i = 0; i < this.nrow; i++) {
/*  880 */       for (int j = 0; j < this.ncol; j++) {
/*  881 */         this.matrix[i][j].timesEquals(cconstant);
/*      */       }
/*      */     }
/*      */   }
/*      */   
/*      */ 
/*      */   public ComplexMatrix over(ComplexMatrix bmat)
/*      */   {
/*  889 */     if ((this.nrow != bmat.nrow) || (this.ncol != bmat.ncol)) {
/*  890 */       throw new IllegalArgumentException("Array dimensions do not agree");
/*      */     }
/*  892 */     return times(bmat.inverse());
/*      */   }
/*      */   
/*      */   public ComplexMatrix over(Complex[][] bmat)
/*      */   {
/*  897 */     int nr = bmat.length;
/*  898 */     int nc = bmat[0].length;
/*  899 */     if ((this.nrow != nr) || (this.ncol != nc)) {
/*  900 */       throw new IllegalArgumentException("Array dimensions do not agree");
/*      */     }
/*      */     
/*  903 */     ComplexMatrix cmat = new ComplexMatrix(bmat);
/*  904 */     return times(cmat.inverse());
/*      */   }
/*      */   
/*      */   public ComplexMatrix over(Matrix bmat)
/*      */   {
/*  909 */     ComplexMatrix pmat = toComplexMatrix(bmat);
/*  910 */     return over(pmat);
/*      */   }
/*      */   
/*      */   public ComplexMatrix over(double[][] bmat)
/*      */   {
/*  915 */     ComplexMatrix pmat = toComplexMatrix(bmat);
/*  916 */     return over(pmat);
/*      */   }
/*      */   
/*      */   public ComplexMatrix over(ComplexMatrix amat, ComplexMatrix bmat)
/*      */   {
/*  921 */     if ((amat.nrow != bmat.nrow) || (amat.ncol != bmat.ncol)) {
/*  922 */       throw new IllegalArgumentException("Array dimensions do not agree");
/*      */     }
/*  924 */     return amat.times(bmat.inverse());
/*      */   }
/*      */   
/*      */   public void overEquals(ComplexMatrix bmat)
/*      */   {
/*  929 */     if ((this.nrow != bmat.nrow) || (this.ncol != bmat.ncol)) {
/*  930 */       throw new IllegalArgumentException("Array dimensions do not agree");
/*      */     }
/*  932 */     ComplexMatrix cmat = new ComplexMatrix(bmat);
/*  933 */     timesEquals(cmat.inverse());
/*      */   }
/*      */   
/*      */ 
/*      */   public ComplexMatrix inverse()
/*      */   {
/*  939 */     int n = this.nrow;
/*  940 */     if (n != this.ncol) throw new IllegalArgumentException("Matrix is not square");
/*  941 */     ComplexMatrix invmat = new ComplexMatrix(n, n);
/*      */     
/*  943 */     if (n == 1) {
/*  944 */       Complex[][] hold = getArrayCopy();
/*  945 */       if (hold[0][0].isZero()) throw new IllegalArgumentException("Matrix is singular");
/*  946 */       hold[0][0] = Complex.plusOne().over(hold[0][0]);
/*  947 */       invmat = new ComplexMatrix(hold);
/*      */ 
/*      */     }
/*  950 */     else if (n == 2) {
/*  951 */       Complex[][] hold = getArrayCopy();
/*  952 */       Complex det = hold[0][0].times(hold[1][1]).minus(hold[0][1].times(hold[1][0]));
/*  953 */       if (det.isZero()) { throw new IllegalArgumentException("Matrix is singular");
/*      */       }
/*  955 */       Complex[][] hold2 = Complex.twoDarray(2, 2);
/*  956 */       hold2[0][0] = hold[1][1].over(det);
/*  957 */       hold2[1][1] = hold[0][0].over(det);
/*  958 */       hold2[1][0] = hold[1][0].negate().over(det);
/*  959 */       hold2[0][1] = hold[0][1].negate().over(det);
/*  960 */       invmat = new ComplexMatrix(hold2);
/*      */     }
/*      */     else {
/*  963 */       Complex[] col = new Complex[n];
/*  964 */       Complex[] xvec = new Complex[n];
/*  965 */       Complex[][] invarray = invmat.getArrayReference();
/*      */       
/*      */ 
/*  968 */       ComplexMatrix ludmat = luDecomp();
/*  969 */       for (int j = 0; j < n; j++) {
/*  970 */         for (int i = 0; i < n; i++) col[i] = Complex.zero();
/*  971 */         col[j] = Complex.plusOne();
/*  972 */         xvec = ludmat.luBackSub(col);
/*  973 */         for (int i = 0; i < n; i++) { invarray[i][j] = Complex.copy(xvec[i]);
/*      */         }
/*      */       }
/*      */     }
/*  977 */     return invmat;
/*      */   }
/*      */   
/*      */   public static ComplexMatrix inverse(ComplexMatrix amat)
/*      */   {
/*  982 */     int n = amat.nrow;
/*  983 */     if (n != amat.ncol) { throw new IllegalArgumentException("Matrix is not square");
/*      */     }
/*  985 */     ComplexMatrix invmat = new ComplexMatrix(n, n);
/*      */     
/*  987 */     if (n == 1) {
/*  988 */       Complex[][] hold = amat.getArrayCopy();
/*  989 */       if (hold[0][0].isZero()) throw new IllegalArgumentException("Matrix is singular");
/*  990 */       hold[0][0] = Complex.plusOne().over(hold[0][0]);
/*  991 */       invmat = new ComplexMatrix(hold);
/*      */ 
/*      */     }
/*  994 */     else if (n == 2) {
/*  995 */       Complex[][] hold = amat.getArrayCopy();
/*  996 */       Complex det = hold[0][0].times(hold[1][1]).minus(hold[0][1].times(hold[1][0]));
/*  997 */       if (det.isZero()) { throw new IllegalArgumentException("Matrix is singular");
/*      */       }
/*  999 */       Complex[][] hold2 = Complex.twoDarray(2, 2);
/* 1000 */       hold2[0][0] = hold[1][1].over(det);
/* 1001 */       hold2[1][1] = hold[0][0].over(det);
/* 1002 */       hold2[1][0] = hold[1][0].negate().over(det);
/* 1003 */       hold2[0][1] = hold[0][1].negate().over(det);
/* 1004 */       invmat = new ComplexMatrix(hold2);
/*      */     }
/*      */     else {
/* 1007 */       Complex[] col = new Complex[n];
/* 1008 */       Complex[] xvec = new Complex[n];
/* 1009 */       Complex[][] invarray = invmat.getArrayReference();
/*      */       
/*      */ 
/* 1012 */       ComplexMatrix ludmat = amat.luDecomp();
/* 1013 */       for (int j = 0; j < n; j++) {
/* 1014 */         for (int i = 0; i < n; i++) col[i] = Complex.zero();
/* 1015 */         col[j] = Complex.plusOne();
/* 1016 */         xvec = ludmat.luBackSub(col);
/* 1017 */         for (int i = 0; i < n; i++) { invarray[i][j] = Complex.copy(xvec[i]);
/*      */         }
/*      */       }
/*      */     }
/* 1021 */     return invmat;
/*      */   }
/*      */   
/*      */ 
/*      */   public ComplexMatrix transpose()
/*      */   {
/* 1027 */     ComplexMatrix tmat = new ComplexMatrix(this.ncol, this.nrow);
/* 1028 */     Complex[][] tarray = tmat.getArrayReference();
/* 1029 */     for (int i = 0; i < this.ncol; i++) {
/* 1030 */       for (int j = 0; j < this.nrow; j++) {
/* 1031 */         tarray[i][j] = Complex.copy(this.matrix[j][i]);
/*      */       }
/*      */     }
/* 1034 */     return tmat;
/*      */   }
/*      */   
/*      */   public static ComplexMatrix transpose(ComplexMatrix amat)
/*      */   {
/* 1039 */     ComplexMatrix tmat = new ComplexMatrix(amat.ncol, amat.nrow);
/* 1040 */     Complex[][] tarray = tmat.getArrayReference();
/* 1041 */     for (int i = 0; i < amat.ncol; i++) {
/* 1042 */       for (int j = 0; j < amat.nrow; j++) {
/* 1043 */         tarray[i][j] = Complex.copy(amat.matrix[j][i]);
/*      */       }
/*      */     }
/* 1046 */     return tmat;
/*      */   }
/*      */   
/*      */ 
/*      */   public ComplexMatrix conjugate()
/*      */   {
/* 1052 */     ComplexMatrix conj = copy(this);
/* 1053 */     for (int i = 0; i < this.nrow; i++) {
/* 1054 */       for (int j = 0; j < this.ncol; j++) {
/* 1055 */         conj.matrix[i][j] = this.matrix[i][j].conjugate();
/*      */       }
/*      */     }
/* 1058 */     return conj;
/*      */   }
/*      */   
/*      */   public static ComplexMatrix conjugate(ComplexMatrix amat)
/*      */   {
/* 1063 */     ComplexMatrix conj = copy(amat);
/* 1064 */     for (int i = 0; i < amat.nrow; i++) {
/* 1065 */       for (int j = 0; j < amat.ncol; j++) {
/* 1066 */         conj.matrix[i][j] = amat.matrix[i][j].conjugate();
/*      */       }
/*      */     }
/* 1069 */     return conj;
/*      */   }
/*      */   
/*      */ 
/*      */   public ComplexMatrix adjoin()
/*      */   {
/* 1075 */     ComplexMatrix adj = copy(this);
/* 1076 */     adj = adj.transpose();
/* 1077 */     adj = adj.conjugate();
/* 1078 */     return adj;
/*      */   }
/*      */   
/*      */   public ComplexMatrix adjoin(ComplexMatrix amat)
/*      */   {
/* 1083 */     ComplexMatrix adj = copy(amat);
/* 1084 */     adj = adj.transpose();
/* 1085 */     adj = adj.conjugate();
/* 1086 */     return adj;
/*      */   }
/*      */   
/*      */ 
/*      */   public ComplexMatrix opposite()
/*      */   {
/* 1092 */     ComplexMatrix opp = copy(this);
/* 1093 */     for (int i = 0; i < this.nrow; i++) {
/* 1094 */       for (int j = 0; j < this.ncol; j++) {
/* 1095 */         opp.matrix[i][j] = this.matrix[i][j].times(Complex.minusOne());
/*      */       }
/*      */     }
/* 1098 */     return opp;
/*      */   }
/*      */   
/*      */   public static ComplexMatrix opposite(ComplexMatrix amat)
/*      */   {
/* 1103 */     ComplexMatrix opp = copy(amat);
/* 1104 */     for (int i = 0; i < amat.nrow; i++) {
/* 1105 */       for (int j = 0; j < amat.ncol; j++) {
/* 1106 */         opp.matrix[i][j] = amat.matrix[i][j].times(Complex.minusOne());
/*      */       }
/*      */     }
/* 1109 */     return opp;
/*      */   }
/*      */   
/*      */ 
/*      */   public Complex trace()
/*      */   {
/* 1115 */     Complex trac = new Complex(0.0D, 0.0D);
/* 1116 */     for (int i = 0; i < Math.min(this.ncol, this.ncol); i++) {
/* 1117 */       trac.plusEquals(this.matrix[i][i]);
/*      */     }
/* 1119 */     return trac;
/*      */   }
/*      */   
/*      */   public static Complex trace(ComplexMatrix amat)
/*      */   {
/* 1124 */     Complex trac = new Complex(0.0D, 0.0D);
/* 1125 */     for (int i = 0; i < Math.min(amat.ncol, amat.ncol); i++) {
/* 1126 */       trac.plusEquals(amat.matrix[i][i]);
/*      */     }
/* 1128 */     return trac;
/*      */   }
/*      */   
/*      */ 
/*      */   public Complex determinant()
/*      */   {
/* 1134 */     int n = this.nrow;
/* 1135 */     if (n != this.ncol) throw new IllegalArgumentException("Matrix is not square");
/* 1136 */     Complex det = new Complex();
/*      */     
/*      */ 
/* 1139 */     ComplexMatrix ludmat = luDecomp();
/* 1140 */     det.reset(ludmat.dswap, 0.0D);
/* 1141 */     for (int j = 0; j < n; j++) {
/* 1142 */       det.timesEquals(ludmat.matrix[j][j]);
/*      */     }
/* 1144 */     return det;
/*      */   }
/*      */   
/*      */   public static Complex determinant(ComplexMatrix amat)
/*      */   {
/* 1149 */     int n = amat.nrow;
/* 1150 */     if (n != amat.ncol) throw new IllegalArgumentException("Matrix is not square");
/* 1151 */     Complex det = new Complex();
/*      */     
/*      */ 
/* 1154 */     ComplexMatrix ludmat = amat.luDecomp();
/* 1155 */     det.reset(ludmat.dswap, 0.0D);
/* 1156 */     for (int j = 0; j < n; j++) {
/* 1157 */       det.timesEquals(ludmat.matrix[j][j]);
/*      */     }
/* 1159 */     return det;
/*      */   }
/*      */   
/*      */ 
/*      */   public Complex logDeterminant()
/*      */   {
/* 1165 */     int n = this.nrow;
/* 1166 */     if (n != this.ncol) throw new IllegalArgumentException("Matrix is not square");
/* 1167 */     Complex det = new Complex();
/*      */     
/*      */ 
/* 1170 */     ComplexMatrix ludmat = luDecomp();
/* 1171 */     det.reset(ludmat.dswap, 0.0D);
/* 1172 */     det = Complex.log(det);
/* 1173 */     for (int j = 0; j < n; j++) {
/* 1174 */       det.plusEquals(Complex.log(ludmat.matrix[j][j]));
/*      */     }
/* 1176 */     return det;
/*      */   }
/*      */   
/*      */ 
/*      */   public static Complex logDeterminant(ComplexMatrix amat)
/*      */   {
/* 1182 */     int n = amat.nrow;
/* 1183 */     if (n != amat.ncol) throw new IllegalArgumentException("Matrix is not square");
/* 1184 */     Complex det = new Complex();
/*      */     
/*      */ 
/* 1187 */     ComplexMatrix ludmat = amat.luDecomp();
/* 1188 */     det.reset(ludmat.dswap, 0.0D);
/* 1189 */     det = Complex.log(det);
/* 1190 */     for (int j = 0; j < n; j++) {
/* 1191 */       det.plusEquals(Complex.log(ludmat.matrix[j][j]));
/*      */     }
/* 1193 */     return det;
/*      */   }
/*      */   
/*      */   public double frobeniusNorm()
/*      */   {
/* 1198 */     double norm = 0.0D;
/* 1199 */     for (int i = 0; i < this.nrow; i++) {
/* 1200 */       for (int j = 0; j < this.ncol; j++) {
/* 1201 */         norm = Fmath.hypot(norm, Complex.abs(this.matrix[i][j]));
/*      */       }
/*      */     }
/* 1204 */     return norm;
/*      */   }
/*      */   
/*      */   public double oneNorm()
/*      */   {
/* 1209 */     double norm = 0.0D;
/* 1210 */     double sum = 0.0D;
/* 1211 */     for (int i = 0; i < this.nrow; i++) {
/* 1212 */       sum = 0.0D;
/* 1213 */       for (int j = 0; j < this.ncol; j++) {
/* 1214 */         sum += Complex.abs(this.matrix[i][j]);
/*      */       }
/* 1216 */       norm = Math.max(norm, sum);
/*      */     }
/* 1218 */     return norm;
/*      */   }
/*      */   
/*      */   public double infinityNorm()
/*      */   {
/* 1223 */     double norm = 0.0D;
/* 1224 */     double sum = 0.0D;
/* 1225 */     for (int i = 0; i < this.nrow; i++) {
/* 1226 */       sum = 0.0D;
/* 1227 */       for (int j = 0; j < this.ncol; j++) {
/* 1228 */         sum += Complex.abs(this.matrix[i][j]);
/*      */       }
/* 1230 */       norm = Math.max(norm, sum);
/*      */     }
/* 1232 */     return norm;
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
/*      */   public ComplexMatrix luDecomp()
/*      */   {
/* 1246 */     if (this.nrow != this.ncol) throw new IllegalArgumentException("A matrix is not square");
/* 1247 */     int n = this.nrow;
/* 1248 */     int imax = 0;
/* 1249 */     double dum = 0.0D;double temp = 0.0D;double big = 0.0D;
/* 1250 */     double[] vv = new double[n];
/* 1251 */     Complex sum = new Complex();
/* 1252 */     Complex dumm = new Complex();
/*      */     
/* 1254 */     ComplexMatrix ludmat = copy(this);
/* 1255 */     Complex[][] ludarray = ludmat.getArrayReference();
/*      */     
/* 1257 */     ludmat.dswap = 1.0D;
/* 1258 */     for (int i = 0; i < n; i++) {
/* 1259 */       big = 0.0D;
/* 1260 */       for (int j = 0; j < n; j++) {
/* 1261 */         if ((temp = Complex.abs(ludarray[i][j])) > big) big = temp;
/*      */       }
/* 1263 */       if (big == 0.0D) throw new ArithmeticException("Singular matrix");
/* 1264 */       vv[i] = (1.0D / big);
/*      */     }
/* 1266 */     for (int j = 0; j < n; j++) {
/* 1267 */       for (int i = 0; i < j; i++) {
/* 1268 */         sum = Complex.copy(ludarray[i][j]);
/* 1269 */         for (int k = 0; k < i; k++) sum.minusEquals(ludarray[i][k].times(ludarray[k][j]));
/* 1270 */         ludarray[i][j] = Complex.copy(sum);
/*      */       }
/* 1272 */       big = 0.0D;
/* 1273 */       for (int i = j; i < n; i++) {
/* 1274 */         sum = Complex.copy(ludarray[i][j]);
/* 1275 */         for (int k = 0; k < j; k++) {
/* 1276 */           sum.minusEquals(ludarray[i][k].times(ludarray[k][j]));
/*      */         }
/* 1278 */         ludarray[i][j] = Complex.copy(sum);
/* 1279 */         if ((dum = vv[i] * Complex.abs(sum)) >= big) {
/* 1280 */           big = dum;
/* 1281 */           imax = i;
/*      */         }
/*      */       }
/* 1284 */       if (j != imax) {
/* 1285 */         for (int k = 0; k < n; k++) {
/* 1286 */           dumm = Complex.copy(ludarray[imax][k]);
/* 1287 */           ludarray[imax][k] = Complex.copy(ludarray[j][k]);
/* 1288 */           ludarray[j][k] = Complex.copy(dumm);
/*      */         }
/* 1290 */         ludmat.dswap = (-ludmat.dswap);
/* 1291 */         vv[imax] = vv[j];
/*      */       }
/* 1293 */       ludmat.index[j] = imax;
/*      */       
/* 1295 */       if (ludarray[j][j].isZero()) {
/* 1296 */         ludarray[j][j].reset(1.0E-30D, 1.0E-30D);
/*      */       }
/* 1298 */       if (j != n - 1) {
/* 1299 */         dumm = Complex.over(1.0D, ludarray[j][j]);
/* 1300 */         for (int i = j + 1; i < n; i++) {
/* 1301 */           ludarray[i][j].timesEquals(dumm);
/*      */         }
/*      */       }
/*      */     }
/* 1305 */     return ludmat;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */   public Complex[] luBackSub(Complex[] bvec)
/*      */   {
/* 1313 */     int ii = 0;int ip = 0;
/* 1314 */     int n = bvec.length;
/* 1315 */     if (n != this.ncol) throw new IllegalArgumentException("vector length is not equal to matrix dimension");
/* 1316 */     if (this.ncol != this.nrow) throw new IllegalArgumentException("matrix is not square");
/* 1317 */     Complex sum = new Complex();
/* 1318 */     Complex[] xvec = new Complex[n];
/* 1319 */     for (int i = 0; i < n; i++) {
/* 1320 */       xvec[i] = Complex.copy(bvec[i]);
/*      */     }
/* 1322 */     for (int i = 0; i < n; i++) {
/* 1323 */       ip = this.index[i];
/* 1324 */       sum = Complex.copy(xvec[ip]);
/* 1325 */       xvec[ip] = Complex.copy(xvec[i]);
/* 1326 */       if (ii == 0) {
/* 1327 */         for (int j = ii; j <= i - 1; j++) {
/* 1328 */           sum.minusEquals(this.matrix[i][j].times(xvec[j]));
/*      */         }
/*      */         
/*      */       }
/* 1332 */       else if (sum.isZero()) { ii = i;
/*      */       }
/* 1334 */       xvec[i] = Complex.copy(sum);
/*      */     }
/* 1336 */     for (int i = n - 1; i >= 0; i--) {
/* 1337 */       sum = Complex.copy(xvec[i]);
/* 1338 */       for (int j = i + 1; j < n; j++) {
/* 1339 */         sum.minusEquals(this.matrix[i][j].times(xvec[j]));
/*      */       }
/* 1341 */       xvec[i] = sum.over(this.matrix[i][i]);
/*      */     }
/* 1343 */     return xvec;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public Complex[] solveLinearSet(Complex[] bvec)
/*      */   {
/* 1352 */     ComplexMatrix ludmat = luDecomp();
/* 1353 */     return ludmat.luBackSub(bvec);
/*      */   }
/*      */ }


/* Location:              /home/menick/Scrivania/ProcessMining/CNMining/CNMining.jar!/flanagan/complex/ComplexMatrix.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       0.7.1
 */