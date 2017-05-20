/*      */ package flanagan.circuits;
/*      */ 
/*      */ import flanagan.complex.Complex;
/*      */ import flanagan.complex.ComplexMatrix;
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
/*      */ public class PhasorMatrix
/*      */ {
/*   44 */   private int nrow = 0;
/*   45 */   private int ncol = 0;
/*   46 */   private Phasor[][] matrix = (Phasor[][])null;
/*   47 */   private int[] index = null;
/*   48 */   private double dswap = 1.0D;
/*      */   
/*      */ 
/*      */   private static final double TINY = 1.0E-30D;
/*      */   
/*      */ 
/*      */   public PhasorMatrix(int nrow, int ncol)
/*      */   {
/*   56 */     this.nrow = nrow;
/*   57 */     this.ncol = ncol;
/*   58 */     this.matrix = Phasor.twoDarray(nrow, ncol);
/*   59 */     this.index = new int[nrow];
/*   60 */     for (int i = 0; i < nrow; i++) this.index[i] = i;
/*   61 */     this.dswap = 1.0D;
/*      */   }
/*      */   
/*      */   public PhasorMatrix(int nrow, int ncol, Phasor constant)
/*      */   {
/*   66 */     this.nrow = nrow;
/*   67 */     this.ncol = ncol;
/*   68 */     this.matrix = Phasor.twoDarray(nrow, ncol, constant);
/*   69 */     this.index = new int[nrow];
/*   70 */     for (int i = 0; i < nrow; i++) this.index[i] = i;
/*   71 */     this.dswap = 1.0D;
/*      */   }
/*      */   
/*      */   public PhasorMatrix(Phasor[][] twoD)
/*      */   {
/*   76 */     this.nrow = twoD.length;
/*   77 */     this.ncol = twoD[0].length;
/*   78 */     for (int i = 0; i < this.nrow; i++) {
/*   79 */       if (twoD[i].length != this.ncol) throw new IllegalArgumentException("All rows must have the same length");
/*      */     }
/*   81 */     this.matrix = twoD;
/*   82 */     this.index = new int[this.nrow];
/*   83 */     for (int i = 0; i < this.nrow; i++) this.index[i] = i;
/*   84 */     this.dswap = 1.0D;
/*      */   }
/*      */   
/*      */   public PhasorMatrix(PhasorMatrix bb)
/*      */   {
/*   89 */     this.nrow = bb.nrow;
/*   90 */     this.ncol = bb.ncol;
/*   91 */     this.matrix = bb.matrix;
/*   92 */     this.index = bb.index;
/*   93 */     this.dswap = bb.dswap;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */   public void setTwoDarray(Phasor[][] aarray)
/*      */   {
/*  100 */     if (this.nrow != aarray.length) throw new IllegalArgumentException("row length of this PhasorMatrix differs from that of the 2D array argument");
/*  101 */     if (this.ncol != aarray[0].length) throw new IllegalArgumentException("column length of this PhasorMatrix differs from that of the 2D array argument");
/*  102 */     for (int i = 0; i < this.nrow; i++) {
/*  103 */       if (aarray[i].length != this.ncol) throw new IllegalArgumentException("All rows must have the same length");
/*  104 */       for (int j = 0; j < this.ncol; j++) {
/*  105 */         this.matrix[i][j] = Phasor.copy(aarray[i][j]);
/*      */       }
/*      */     }
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */   public void setElement(int i, int j, Phasor aa)
/*      */   {
/*  115 */     this.matrix[i][j] = Phasor.copy(aa);
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public void setElement(int i, int j, double aa, double bb)
/*      */   {
/*  124 */     this.matrix[i][j].reset(aa, bb);
/*      */   }
/*      */   
/*      */ 
/*      */   public void setSubMatrix(int i, int j, Phasor[][] subMatrix)
/*      */   {
/*  130 */     int k = subMatrix.length;
/*  131 */     int l = subMatrix[0].length;
/*  132 */     if (i > k) throw new IllegalArgumentException("row indices inverted");
/*  133 */     if (j > l) throw new IllegalArgumentException("column indices inverted");
/*  134 */     int n = k - i + 1;int m = l - j + 1;
/*  135 */     for (int p = 0; p < n; p++) {
/*  136 */       for (int q = 0; q < m; q++) {
/*  137 */         this.matrix[(i + p)][(j + q)] = Phasor.copy(subMatrix[p][q]);
/*      */       }
/*      */     }
/*      */   }
/*      */   
/*      */ 
/*      */   public void setSubMatrix(int i, int j, int k, int l, Phasor[][] subMatrix)
/*      */   {
/*  145 */     if (i > k) throw new IllegalArgumentException("row indices inverted");
/*  146 */     if (j > l) throw new IllegalArgumentException("column indices inverted");
/*  147 */     int n = k - i + 1;int m = l - j + 1;
/*  148 */     for (int p = 0; p < n; p++) {
/*  149 */       for (int q = 0; q < m; q++) {
/*  150 */         this.matrix[(i + p)][(j + q)] = Phasor.copy(subMatrix[p][q]);
/*      */       }
/*      */     }
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */   public void setSubMatrix(int[] row, int[] col, Phasor[][] subMatrix)
/*      */   {
/*  160 */     int n = row.length;
/*  161 */     int m = col.length;
/*  162 */     for (int p = 0; p < n; p++) {
/*  163 */       for (int q = 0; q < m; q++) {
/*  164 */         this.matrix[row[p]][col[q]] = Phasor.copy(subMatrix[p][q]);
/*      */       }
/*      */     }
/*      */   }
/*      */   
/*      */ 
/*      */   public static PhasorMatrix identityMatrix(int nrow)
/*      */   {
/*  172 */     PhasorMatrix u = new PhasorMatrix(nrow, nrow);
/*  173 */     for (int i = 0; i < nrow; i++) {
/*  174 */       u.matrix[i][i] = Phasor.plusOne();
/*      */     }
/*  176 */     return u;
/*      */   }
/*      */   
/*      */   public static PhasorMatrix scalarMatrix(int nrow, Phasor diagconst)
/*      */   {
/*  181 */     PhasorMatrix u = new PhasorMatrix(nrow, nrow);
/*  182 */     Phasor[][] uarray = u.getArrayReference();
/*  183 */     for (int i = 0; i < nrow; i++) {
/*  184 */       for (int j = i; j < nrow; j++) {
/*  185 */         if (i == j) {
/*  186 */           uarray[i][j] = Phasor.copy(diagconst);
/*      */         }
/*      */       }
/*      */     }
/*  190 */     return u;
/*      */   }
/*      */   
/*      */   public static PhasorMatrix diagonalMatrix(int nrow, Phasor[] diag)
/*      */   {
/*  195 */     if (diag.length != nrow) throw new IllegalArgumentException("matrix dimension differs from diagonal array length");
/*  196 */     PhasorMatrix u = new PhasorMatrix(nrow, nrow);
/*  197 */     Phasor[][] uarray = u.getArrayReference();
/*  198 */     for (int i = 0; i < nrow; i++) {
/*  199 */       for (int j = i; j < nrow; j++) {
/*  200 */         if (i == j) {
/*  201 */           uarray[i][j] = Phasor.copy(diag[i]);
/*      */         }
/*      */       }
/*      */     }
/*  205 */     return u;
/*      */   }
/*      */   
/*      */ 
/*      */   public static PhasorMatrix columnMatrix(Phasor[] darray)
/*      */   {
/*  211 */     int nr = darray.length;
/*  212 */     PhasorMatrix pp = new PhasorMatrix(nr, 1);
/*  213 */     for (int i = 0; i < nr; i++) pp.matrix[i][0] = darray[i];
/*  214 */     return pp;
/*      */   }
/*      */   
/*      */ 
/*      */   public static PhasorMatrix rowMatrix(Phasor[] darray)
/*      */   {
/*  220 */     int nc = darray.length;
/*  221 */     PhasorMatrix pp = new PhasorMatrix(1, nc);
/*  222 */     for (int i = 0; i < nc; i++) pp.matrix[0][i] = darray[i];
/*  223 */     return pp;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */   public int getNrow()
/*      */   {
/*  230 */     return this.nrow;
/*      */   }
/*      */   
/*      */   public int getNcol()
/*      */   {
/*  235 */     return this.ncol;
/*      */   }
/*      */   
/*      */   public Phasor[][] getArrayReference()
/*      */   {
/*  240 */     return this.matrix;
/*      */   }
/*      */   
/*      */   public Phasor[][] getArray()
/*      */   {
/*  245 */     return this.matrix;
/*      */   }
/*      */   
/*      */ 
/*      */   public Phasor[][] getArrayPointer()
/*      */   {
/*  251 */     return this.matrix;
/*      */   }
/*      */   
/*      */   public Phasor[][] getArrayCopy()
/*      */   {
/*  256 */     Phasor[][] c = new Phasor[this.nrow][this.ncol];
/*  257 */     for (int i = 0; i < this.nrow; i++) {
/*  258 */       for (int j = 0; j < this.ncol; j++) {
/*  259 */         c[i][j] = Phasor.copy(this.matrix[i][j]);
/*      */       }
/*      */     }
/*  262 */     return c;
/*      */   }
/*      */   
/*      */   public Phasor getElementReference(int i, int j)
/*      */   {
/*  267 */     return this.matrix[i][j];
/*      */   }
/*      */   
/*      */ 
/*      */   public Phasor getElementPointer(int i, int j)
/*      */   {
/*  273 */     return this.matrix[i][j];
/*      */   }
/*      */   
/*      */   public Phasor getElementCopy(int i, int j)
/*      */   {
/*  278 */     return Phasor.copy(this.matrix[i][j]);
/*      */   }
/*      */   
/*      */ 
/*      */   public PhasorMatrix getSubMatrix(int i, int j, int k, int l)
/*      */   {
/*  284 */     if (i > k) throw new IllegalArgumentException("row indices inverted");
/*  285 */     if (j > l) throw new IllegalArgumentException("column indices inverted");
/*  286 */     int n = k - i + 1;int m = l - j + 1;
/*  287 */     PhasorMatrix subMatrix = new PhasorMatrix(n, m);
/*  288 */     Phasor[][] sarray = subMatrix.getArrayReference();
/*  289 */     for (int p = 0; p < n; p++) {
/*  290 */       for (int q = 0; q < m; q++) {
/*  291 */         sarray[p][q] = Phasor.copy(this.matrix[(i + p)][(j + q)]);
/*      */       }
/*      */     }
/*  294 */     return subMatrix;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */   public PhasorMatrix getSubMatrix(int[] row, int[] col)
/*      */   {
/*  301 */     int n = row.length;
/*  302 */     int m = col.length;
/*  303 */     PhasorMatrix subMatrix = new PhasorMatrix(n, m);
/*  304 */     Phasor[][] sarray = subMatrix.getArrayReference();
/*  305 */     for (int i = 0; i < n; i++) {
/*  306 */       for (int j = 0; j < m; j++) {
/*  307 */         sarray[i][j] = Phasor.copy(this.matrix[row[i]][col[j]]);
/*      */       }
/*      */     }
/*  310 */     return subMatrix;
/*      */   }
/*      */   
/*      */   public int[] getIndexReference()
/*      */   {
/*  315 */     return this.index;
/*      */   }
/*      */   
/*      */   public int[] getIndexPointer()
/*      */   {
/*  320 */     return this.index;
/*      */   }
/*      */   
/*      */   public int[] getIndexCopy()
/*      */   {
/*  325 */     int[] indcopy = new int[this.nrow];
/*  326 */     for (int i = 0; i < this.nrow; i++) {
/*  327 */       indcopy[i] = this.index[i];
/*      */     }
/*  329 */     return indcopy;
/*      */   }
/*      */   
/*      */   public double getSwap()
/*      */   {
/*  334 */     return this.dswap;
/*      */   }
/*      */   
/*      */ 
/*      */   public static PhasorMatrix copy(PhasorMatrix a)
/*      */   {
/*  340 */     if (a == null) {
/*  341 */       return null;
/*      */     }
/*      */     
/*  344 */     int nr = a.getNrow();
/*  345 */     int nc = a.getNcol();
/*  346 */     Phasor[][] aarray = a.getArrayReference();
/*  347 */     PhasorMatrix b = new PhasorMatrix(nr, nc);
/*  348 */     b.nrow = nr;
/*  349 */     b.ncol = nc;
/*  350 */     Phasor[][] barray = b.getArrayReference();
/*  351 */     for (int i = 0; i < nr; i++) {
/*  352 */       for (int j = 0; j < nc; j++) {
/*  353 */         barray[i][j] = Phasor.copy(aarray[i][j]);
/*      */       }
/*      */     }
/*  356 */     for (int i = 0; i < nr; i++) b.index[i] = a.index[i];
/*  357 */     return b;
/*      */   }
/*      */   
/*      */ 
/*      */   public PhasorMatrix copy()
/*      */   {
/*  363 */     if (this == null) {
/*  364 */       return null;
/*      */     }
/*      */     
/*  367 */     int nr = this.nrow;
/*  368 */     int nc = this.ncol;
/*  369 */     PhasorMatrix b = new PhasorMatrix(nr, nc);
/*  370 */     Phasor[][] barray = b.getArrayReference();
/*  371 */     b.nrow = nr;
/*  372 */     b.ncol = nc;
/*  373 */     for (int i = 0; i < nr; i++) {
/*  374 */       for (int j = 0; j < nc; j++) {
/*  375 */         barray[i][j] = Phasor.copy(this.matrix[i][j]);
/*      */       }
/*      */     }
/*  378 */     for (int i = 0; i < nr; i++) b.index[i] = this.index[i];
/*  379 */     return b;
/*      */   }
/*      */   
/*      */ 
/*      */   public Object clone()
/*      */   {
/*  385 */     if (this == null) {
/*  386 */       return null;
/*      */     }
/*      */     
/*  389 */     int nr = this.nrow;
/*  390 */     int nc = this.ncol;
/*  391 */     PhasorMatrix b = new PhasorMatrix(nr, nc);
/*  392 */     Phasor[][] barray = b.getArrayReference();
/*  393 */     b.nrow = nr;
/*  394 */     b.ncol = nc;
/*  395 */     for (int i = 0; i < nr; i++) {
/*  396 */       for (int j = 0; j < nc; j++) {
/*  397 */         barray[i][j] = Phasor.copy(this.matrix[i][j]);
/*      */       }
/*      */     }
/*  400 */     for (int i = 0; i < nr; i++) b.index[i] = this.index[i];
/*  401 */     return b;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */   public static PhasorMatrix toPhasorRowMatrix(Phasor[] parray)
/*      */   {
/*  408 */     int nc = parray.length;
/*  409 */     PhasorMatrix pp = new PhasorMatrix(1, nc);
/*  410 */     for (int i = 0; i < nc; i++) pp.matrix[0][i] = parray[i].copy();
/*  411 */     return pp;
/*      */   }
/*      */   
/*      */   public static PhasorMatrix toPhasorRowMatrix(Complex[] carray)
/*      */   {
/*  416 */     int nc = carray.length;
/*  417 */     PhasorMatrix pp = new PhasorMatrix(1, nc);
/*  418 */     for (int i = 0; i < nc; i++) pp.matrix[0][i] = Phasor.toPhasor(carray[i]).copy();
/*  419 */     return pp;
/*      */   }
/*      */   
/*      */   public static PhasorMatrix toPhasorRowMatrix(double[] darray)
/*      */   {
/*  424 */     int nc = darray.length;
/*  425 */     PhasorMatrix pp = new PhasorMatrix(1, nc);
/*  426 */     for (int i = 0; i < nc; i++) pp.matrix[0][i] = new Phasor(darray[i], 0.0D);
/*  427 */     return pp;
/*      */   }
/*      */   
/*      */   public static PhasorMatrix toPhasorColumnMatrix(Phasor[] parray)
/*      */   {
/*  432 */     int nr = parray.length;
/*  433 */     PhasorMatrix pp = new PhasorMatrix(nr, 1);
/*  434 */     for (int i = 0; i < nr; i++) pp.matrix[i][0] = parray[i].copy();
/*  435 */     return pp;
/*      */   }
/*      */   
/*      */   public static PhasorMatrix toPhasorColumnMatrix(Complex[] carray)
/*      */   {
/*  440 */     int nr = carray.length;
/*  441 */     PhasorMatrix pp = new PhasorMatrix(nr, 1);
/*  442 */     for (int i = 0; i < nr; i++) pp.matrix[i][0] = Phasor.toPhasor(carray[i]).copy();
/*  443 */     return pp;
/*      */   }
/*      */   
/*      */   public static PhasorMatrix toPhasorColumnMatrix(double[] darray)
/*      */   {
/*  448 */     int nr = darray.length;
/*  449 */     PhasorMatrix pp = new PhasorMatrix(nr, 1);
/*  450 */     for (int i = 0; i < nr; i++) pp.matrix[i][0] = new Phasor(darray[i], 0.0D);
/*  451 */     return pp;
/*      */   }
/*      */   
/*      */   public static PhasorMatrix toPhasorMatrix(ComplexMatrix cc)
/*      */   {
/*  456 */     PhasorMatrix pp = new PhasorMatrix(cc.getNrow(), cc.getNcol());
/*  457 */     pp.index = cc.getIndexCopy();
/*  458 */     pp.dswap = cc.getSwap();
/*  459 */     for (int i = 0; i < pp.nrow; i++) {
/*  460 */       for (int j = 0; j < pp.ncol; i++) {
/*  461 */         pp.matrix[i][j] = Phasor.toPhasor(cc.getElementCopy(i, j));
/*      */       }
/*      */     }
/*  464 */     return pp;
/*      */   }
/*      */   
/*      */   public static PhasorMatrix toPhasorMatrix(Complex[][] carray)
/*      */   {
/*  469 */     ComplexMatrix cc = new ComplexMatrix(carray);
/*  470 */     PhasorMatrix pp = new PhasorMatrix(cc.getNrow(), cc.getNcol());
/*  471 */     for (int i = 0; i < pp.nrow; i++) {
/*  472 */       for (int j = 0; j < pp.ncol; i++) {
/*  473 */         pp.matrix[i][j] = Phasor.toPhasor(cc.getElementCopy(i, j));
/*      */       }
/*      */     }
/*  476 */     return pp;
/*      */   }
/*      */   
/*      */   public static PhasorMatrix toPhasorMatrix(Matrix marray)
/*      */   {
/*  481 */     int nr = marray.getNrow();
/*  482 */     int nc = marray.getNcol();
/*      */     
/*  484 */     PhasorMatrix pp = new PhasorMatrix(nr, nc);
/*  485 */     for (int i = 0; i < nr; i++) {
/*  486 */       for (int j = 0; j < nc; j++) {
/*  487 */         pp.matrix[i][j].reset(marray.getElementCopy(i, j), 0.0D);
/*      */       }
/*      */     }
/*  490 */     return pp;
/*      */   }
/*      */   
/*      */   public static PhasorMatrix toPhasorMatrix(double[][] darray)
/*      */   {
/*  495 */     int nr = darray.length;
/*  496 */     int nc = darray[0].length;
/*  497 */     for (int i = 1; i < nr; i++) {
/*  498 */       if (darray[i].length != nc) throw new IllegalArgumentException("All rows must have the same length");
/*      */     }
/*  500 */     PhasorMatrix pp = new PhasorMatrix(nr, nc);
/*  501 */     for (int i = 0; i < pp.nrow; i++) {
/*  502 */       for (int j = 0; j < pp.ncol; j++) {
/*  503 */         pp.matrix[i][j].reset(darray[i][j], 0.0D);
/*      */       }
/*      */     }
/*  506 */     return pp;
/*      */   }
/*      */   
/*      */   public ComplexMatrix toComplexMatrix()
/*      */   {
/*  511 */     int nr = getNrow();
/*  512 */     int nc = getNcol();
/*  513 */     ComplexMatrix cc = new ComplexMatrix(nr, nc);
/*  514 */     for (int i = 0; i < nr; i++) {
/*  515 */       for (int j = 0; j < nc; i++) {
/*  516 */         cc.setElement(i, j, this.matrix[i][j].toRectangular());
/*      */       }
/*      */     }
/*  519 */     return cc;
/*      */   }
/*      */   
/*      */   public static ComplexMatrix toComplexMatrix(PhasorMatrix pp)
/*      */   {
/*  524 */     int nr = pp.getNrow();
/*  525 */     int nc = pp.getNcol();
/*  526 */     ComplexMatrix cc = new ComplexMatrix(nr, nc);
/*  527 */     for (int i = 0; i < nr; i++) {
/*  528 */       for (int j = 0; j < nc; i++) {
/*  529 */         cc.setElement(i, j, pp.matrix[i][j].toRectangular());
/*      */       }
/*      */     }
/*  532 */     return cc;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */   public PhasorMatrix plus(PhasorMatrix bmat)
/*      */   {
/*  539 */     if ((this.nrow != bmat.nrow) || (this.ncol != bmat.ncol)) {
/*  540 */       throw new IllegalArgumentException("Array dimensions do not agree");
/*      */     }
/*  542 */     int nr = bmat.nrow;
/*  543 */     int nc = bmat.ncol;
/*  544 */     PhasorMatrix cmat = new PhasorMatrix(nr, nc);
/*  545 */     Phasor[][] carray = cmat.getArrayReference();
/*  546 */     for (int i = 0; i < nr; i++) {
/*  547 */       for (int j = 0; j < nc; j++) {
/*  548 */         carray[i][j] = this.matrix[i][j].plus(bmat.matrix[i][j]);
/*      */       }
/*      */     }
/*  551 */     return cmat;
/*      */   }
/*      */   
/*      */   public PhasorMatrix plus(Phasor[][] bmat)
/*      */   {
/*  556 */     int nr = bmat.length;
/*  557 */     int nc = bmat[0].length;
/*  558 */     if ((this.nrow != nr) || (this.ncol != nc)) {
/*  559 */       throw new IllegalArgumentException("Array dimensions do not agree");
/*      */     }
/*  561 */     PhasorMatrix cmat = new PhasorMatrix(nr, nc);
/*  562 */     Phasor[][] carray = cmat.getArrayReference();
/*  563 */     for (int i = 0; i < nr; i++) {
/*  564 */       for (int j = 0; j < nc; j++) {
/*  565 */         carray[i][j] = this.matrix[i][j].plus(bmat[i][j]);
/*      */       }
/*      */     }
/*  568 */     return cmat;
/*      */   }
/*      */   
/*      */   public PhasorMatrix plus(ComplexMatrix bmat)
/*      */   {
/*  573 */     PhasorMatrix pmat = toPhasorMatrix(bmat);
/*  574 */     return plus(pmat);
/*      */   }
/*      */   
/*      */   public PhasorMatrix plus(Complex[][] bmat)
/*      */   {
/*  579 */     PhasorMatrix pmat = toPhasorMatrix(bmat);
/*  580 */     return plus(pmat);
/*      */   }
/*      */   
/*      */   public PhasorMatrix plus(Matrix bmat)
/*      */   {
/*  585 */     PhasorMatrix pmat = toPhasorMatrix(bmat);
/*  586 */     return plus(pmat);
/*      */   }
/*      */   
/*      */   public PhasorMatrix plus(double[][] bmat)
/*      */   {
/*  591 */     PhasorMatrix pmat = toPhasorMatrix(bmat);
/*  592 */     return plus(pmat);
/*      */   }
/*      */   
/*      */   public void plusEquals(PhasorMatrix bmat)
/*      */   {
/*  597 */     if ((this.nrow != bmat.nrow) || (this.ncol != bmat.ncol)) {
/*  598 */       throw new IllegalArgumentException("Array dimensions do not agree");
/*      */     }
/*  600 */     int nr = bmat.nrow;
/*  601 */     int nc = bmat.ncol;
/*      */     
/*  603 */     for (int i = 0; i < nr; i++) {
/*  604 */       for (int j = 0; j < nc; j++) {
/*  605 */         this.matrix[i][j].plusEquals(bmat.matrix[i][j]);
/*      */       }
/*      */     }
/*      */   }
/*      */   
/*      */   public void plusEquals(Phasor[][] bmat)
/*      */   {
/*  612 */     PhasorMatrix pmat = new PhasorMatrix(bmat);
/*  613 */     plusEquals(pmat);
/*      */   }
/*      */   
/*      */   public void plusEquals(ComplexMatrix bmat)
/*      */   {
/*  618 */     PhasorMatrix pmat = toPhasorMatrix(bmat);
/*  619 */     plusEquals(pmat);
/*      */   }
/*      */   
/*      */   public void plusEquals(Complex[][] bmat)
/*      */   {
/*  624 */     PhasorMatrix pmat = toPhasorMatrix(bmat);
/*  625 */     plusEquals(pmat);
/*      */   }
/*      */   
/*      */   public void plusEquals(Matrix bmat)
/*      */   {
/*  630 */     PhasorMatrix pmat = toPhasorMatrix(bmat);
/*  631 */     plusEquals(pmat);
/*      */   }
/*      */   
/*      */   public void plusEquals(double[][] bmat)
/*      */   {
/*  636 */     PhasorMatrix pmat = toPhasorMatrix(bmat);
/*  637 */     plusEquals(pmat);
/*      */   }
/*      */   
/*      */ 
/*      */   public PhasorMatrix minus(PhasorMatrix bmat)
/*      */   {
/*  643 */     if ((this.nrow != bmat.nrow) || (this.ncol != bmat.ncol)) {
/*  644 */       throw new IllegalArgumentException("Array dimensions do not agree");
/*      */     }
/*  646 */     int nr = this.nrow;
/*  647 */     int nc = this.ncol;
/*  648 */     PhasorMatrix cmat = new PhasorMatrix(nr, nc);
/*  649 */     Phasor[][] carray = cmat.getArrayReference();
/*  650 */     for (int i = 0; i < nr; i++) {
/*  651 */       for (int j = 0; j < nc; j++) {
/*  652 */         carray[i][j] = this.matrix[i][j].minus(bmat.matrix[i][j]);
/*      */       }
/*      */     }
/*  655 */     return cmat;
/*      */   }
/*      */   
/*      */   public PhasorMatrix minus(Phasor[][] bmat)
/*      */   {
/*  660 */     int nr = bmat.length;
/*  661 */     int nc = bmat[0].length;
/*  662 */     if ((this.nrow != nr) || (this.ncol != nc)) {
/*  663 */       throw new IllegalArgumentException("Array dimensions do not agree");
/*      */     }
/*  665 */     PhasorMatrix cmat = new PhasorMatrix(nr, nc);
/*  666 */     Phasor[][] carray = cmat.getArrayReference();
/*  667 */     for (int i = 0; i < nr; i++) {
/*  668 */       for (int j = 0; j < nc; j++) {
/*  669 */         carray[i][j] = this.matrix[i][j].minus(bmat[i][j]);
/*      */       }
/*      */     }
/*  672 */     return cmat;
/*      */   }
/*      */   
/*      */   public PhasorMatrix minus(ComplexMatrix bmat)
/*      */   {
/*  677 */     PhasorMatrix pmat = toPhasorMatrix(bmat);
/*  678 */     return minus(pmat);
/*      */   }
/*      */   
/*      */   public PhasorMatrix minus(Complex[][] bmat)
/*      */   {
/*  683 */     PhasorMatrix pmat = toPhasorMatrix(bmat);
/*  684 */     return minus(pmat);
/*      */   }
/*      */   
/*      */   public PhasorMatrix minus(Matrix bmat)
/*      */   {
/*  689 */     PhasorMatrix pmat = toPhasorMatrix(bmat);
/*  690 */     return minus(pmat);
/*      */   }
/*      */   
/*      */   public PhasorMatrix minus(double[][] bmat)
/*      */   {
/*  695 */     PhasorMatrix pmat = toPhasorMatrix(bmat);
/*  696 */     return minus(pmat);
/*      */   }
/*      */   
/*      */ 
/*      */   public void minusEquals(PhasorMatrix bmat)
/*      */   {
/*  702 */     if ((this.nrow != bmat.nrow) || (this.ncol != bmat.ncol)) {
/*  703 */       throw new IllegalArgumentException("Array dimensions do not agree");
/*      */     }
/*  705 */     int nr = bmat.nrow;
/*  706 */     int nc = bmat.ncol;
/*      */     
/*  708 */     for (int i = 0; i < nr; i++) {
/*  709 */       for (int j = 0; j < nc; j++) {
/*  710 */         this.matrix[i][j].minusEquals(bmat.matrix[i][j]);
/*      */       }
/*      */     }
/*      */   }
/*      */   
/*      */   public void minusEquals(Phasor[][] bmat)
/*      */   {
/*  717 */     PhasorMatrix pmat = new PhasorMatrix(bmat);
/*  718 */     minusEquals(pmat);
/*      */   }
/*      */   
/*      */   public void minusEquals(ComplexMatrix bmat)
/*      */   {
/*  723 */     PhasorMatrix pmat = toPhasorMatrix(bmat);
/*  724 */     minusEquals(pmat);
/*      */   }
/*      */   
/*      */   public void minusEquals(Complex[][] bmat)
/*      */   {
/*  729 */     PhasorMatrix pmat = toPhasorMatrix(bmat);
/*  730 */     minusEquals(pmat);
/*      */   }
/*      */   
/*      */   public void minusEquals(Matrix bmat)
/*      */   {
/*  735 */     PhasorMatrix pmat = toPhasorMatrix(bmat);
/*  736 */     minusEquals(pmat);
/*      */   }
/*      */   
/*      */   public void minusEquals(double[][] bmat)
/*      */   {
/*  741 */     PhasorMatrix pmat = toPhasorMatrix(bmat);
/*  742 */     minusEquals(pmat);
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */   public PhasorMatrix times(PhasorMatrix bmat)
/*      */   {
/*  749 */     if (this.ncol != bmat.nrow) { throw new IllegalArgumentException("Nonconformable matrices");
/*      */     }
/*  751 */     PhasorMatrix cmat = new PhasorMatrix(this.nrow, bmat.ncol);
/*  752 */     Phasor[][] carray = cmat.getArrayReference();
/*  753 */     Phasor sum = new Phasor();
/*      */     
/*  755 */     for (int i = 0; i < this.nrow; i++) {
/*  756 */       for (int j = 0; j < bmat.ncol; j++) {
/*  757 */         sum = Phasor.zero();
/*  758 */         for (int k = 0; k < this.ncol; k++) {
/*  759 */           sum.plusEquals(this.matrix[i][k].times(bmat.matrix[k][j]));
/*      */         }
/*  761 */         carray[i][j] = Phasor.copy(sum);
/*      */       }
/*      */     }
/*  764 */     return cmat;
/*      */   }
/*      */   
/*      */   public PhasorMatrix times(Phasor[][] bmat)
/*      */   {
/*  769 */     int nr = bmat.length;
/*  770 */     int nc = bmat[0].length;
/*  771 */     if (this.ncol != nr) { throw new IllegalArgumentException("Nonconformable matrices");
/*      */     }
/*  773 */     PhasorMatrix cmat = new PhasorMatrix(this.nrow, nc);
/*  774 */     Phasor[][] carray = cmat.getArrayReference();
/*  775 */     Phasor sum = new Phasor();
/*      */     
/*  777 */     for (int i = 0; i < this.nrow; i++) {
/*  778 */       for (int j = 0; j < nc; j++) {
/*  779 */         sum = Phasor.zero();
/*  780 */         for (int k = 0; k < this.ncol; k++) {
/*  781 */           sum.plusEquals(this.matrix[i][k].times(bmat[k][j]));
/*      */         }
/*  783 */         carray[i][j] = Phasor.copy(sum);
/*      */       }
/*      */     }
/*  786 */     return cmat;
/*      */   }
/*      */   
/*      */   public PhasorMatrix times(ComplexMatrix bmat)
/*      */   {
/*  791 */     PhasorMatrix pmat = toPhasorMatrix(bmat);
/*  792 */     return times(pmat);
/*      */   }
/*      */   
/*      */   public PhasorMatrix times(Complex[][] bmat)
/*      */   {
/*  797 */     PhasorMatrix pmat = toPhasorMatrix(bmat);
/*  798 */     return times(pmat);
/*      */   }
/*      */   
/*      */   public PhasorMatrix times(Matrix bmat)
/*      */   {
/*  803 */     PhasorMatrix pmat = toPhasorMatrix(bmat);
/*  804 */     return times(pmat);
/*      */   }
/*      */   
/*      */   public PhasorMatrix times(double[][] bmat)
/*      */   {
/*  809 */     PhasorMatrix pmat = toPhasorMatrix(bmat);
/*  810 */     return times(pmat);
/*      */   }
/*      */   
/*      */ 
/*      */   public PhasorMatrix times(Phasor constant)
/*      */   {
/*  816 */     PhasorMatrix cmat = new PhasorMatrix(this.nrow, this.ncol);
/*  817 */     Phasor[][] carray = cmat.getArrayReference();
/*      */     
/*  819 */     for (int i = 0; i < this.nrow; i++) {
/*  820 */       for (int j = 0; j < this.ncol; j++) {
/*  821 */         carray[i][j] = this.matrix[i][j].times(constant);
/*      */       }
/*      */     }
/*  824 */     return cmat;
/*      */   }
/*      */   
/*      */ 
/*      */   public PhasorMatrix times(double constant)
/*      */   {
/*  830 */     PhasorMatrix cmat = new PhasorMatrix(this.nrow, this.ncol);
/*  831 */     Phasor[][] carray = cmat.getArrayReference();
/*  832 */     Phasor cconstant = new Phasor(constant, 0.0D);
/*      */     
/*  834 */     for (int i = 0; i < this.nrow; i++) {
/*  835 */       for (int j = 0; j < this.ncol; j++) {
/*  836 */         carray[i][j] = this.matrix[i][j].times(cconstant);
/*      */       }
/*      */     }
/*  839 */     return cmat;
/*      */   }
/*      */   
/*      */ 
/*      */   public void timesEquals(PhasorMatrix bmat)
/*      */   {
/*  845 */     if (this.ncol != bmat.nrow) { throw new IllegalArgumentException("Nonconformable matrices");
/*      */     }
/*  847 */     Phasor sum = new Phasor();
/*      */     
/*  849 */     for (int i = 0; i < this.nrow; i++) {
/*  850 */       for (int j = 0; j < bmat.ncol; j++) {
/*  851 */         sum = Phasor.zero();
/*  852 */         for (int k = 0; k < this.ncol; k++) {
/*  853 */           sum.plusEquals(this.matrix[i][k].times(bmat.matrix[k][j]));
/*      */         }
/*  855 */         this.matrix[i][j] = Phasor.copy(sum);
/*      */       }
/*      */     }
/*      */   }
/*      */   
/*      */   public void timesEquals(Phasor[][] bmat)
/*      */   {
/*  862 */     PhasorMatrix pmat = new PhasorMatrix(bmat);
/*  863 */     timesEquals(pmat);
/*      */   }
/*      */   
/*      */   public void timesEquals(ComplexMatrix bmat)
/*      */   {
/*  868 */     PhasorMatrix pmat = toPhasorMatrix(bmat);
/*  869 */     timesEquals(pmat);
/*      */   }
/*      */   
/*      */   public void timesEquals(Complex[][] bmat)
/*      */   {
/*  874 */     PhasorMatrix pmat = toPhasorMatrix(bmat);
/*  875 */     timesEquals(pmat);
/*      */   }
/*      */   
/*      */   public void timesEquals(Matrix bmat)
/*      */   {
/*  880 */     PhasorMatrix pmat = toPhasorMatrix(bmat);
/*  881 */     timesEquals(pmat);
/*      */   }
/*      */   
/*      */   public void timesEquals(double[][] bmat)
/*      */   {
/*  886 */     PhasorMatrix pmat = toPhasorMatrix(bmat);
/*  887 */     timesEquals(pmat);
/*      */   }
/*      */   
/*      */ 
/*      */   public void timesEquals(Phasor constant)
/*      */   {
/*  893 */     for (int i = 0; i < this.nrow; i++) {
/*  894 */       for (int j = 0; j < this.ncol; j++) {
/*  895 */         this.matrix[i][j].timesEquals(constant);
/*      */       }
/*      */     }
/*      */   }
/*      */   
/*      */ 
/*      */   public void timesEquals(Complex constant)
/*      */   {
/*  903 */     for (int i = 0; i < this.nrow; i++) {
/*  904 */       for (int j = 0; j < this.ncol; j++) {
/*  905 */         this.matrix[i][j].timesEquals(constant);
/*      */       }
/*      */     }
/*      */   }
/*      */   
/*      */   public void timesEquals(double constant)
/*      */   {
/*  912 */     Phasor cconstant = new Phasor(constant, 0.0D);
/*      */     
/*  914 */     for (int i = 0; i < this.nrow; i++) {
/*  915 */       for (int j = 0; j < this.ncol; j++) {
/*  916 */         this.matrix[i][j].timesEquals(cconstant);
/*      */       }
/*      */     }
/*      */   }
/*      */   
/*      */   public void timesEquals(int constant)
/*      */   {
/*  923 */     Phasor cconstant = new Phasor(constant, 0.0D);
/*      */     
/*  925 */     for (int i = 0; i < this.nrow; i++) {
/*  926 */       for (int j = 0; j < this.ncol; j++) {
/*  927 */         this.matrix[i][j].timesEquals(cconstant);
/*      */       }
/*      */     }
/*      */   }
/*      */   
/*      */ 
/*      */   public PhasorMatrix over(PhasorMatrix bmat)
/*      */   {
/*  935 */     if ((this.nrow != bmat.nrow) || (this.ncol != bmat.ncol)) {
/*  936 */       throw new IllegalArgumentException("Array dimensions do not agree");
/*      */     }
/*  938 */     return times(bmat.inverse());
/*      */   }
/*      */   
/*      */   public PhasorMatrix over(Phasor[][] bmat)
/*      */   {
/*  943 */     int nr = bmat.length;
/*  944 */     int nc = bmat[0].length;
/*  945 */     if ((this.nrow != nr) || (this.ncol != nc)) {
/*  946 */       throw new IllegalArgumentException("Array dimensions do not agree");
/*      */     }
/*      */     
/*  949 */     PhasorMatrix cmat = new PhasorMatrix(bmat);
/*  950 */     return times(cmat.inverse());
/*      */   }
/*      */   
/*      */   public PhasorMatrix over(ComplexMatrix bmat)
/*      */   {
/*  955 */     PhasorMatrix pmat = toPhasorMatrix(bmat);
/*  956 */     return over(pmat);
/*      */   }
/*      */   
/*      */   public PhasorMatrix over(Complex[][] bmat)
/*      */   {
/*  961 */     PhasorMatrix pmat = toPhasorMatrix(bmat);
/*  962 */     return over(pmat);
/*      */   }
/*      */   
/*      */   public PhasorMatrix over(Matrix bmat)
/*      */   {
/*  967 */     PhasorMatrix pmat = toPhasorMatrix(bmat);
/*  968 */     return over(pmat);
/*      */   }
/*      */   
/*      */   public PhasorMatrix over(double[][] bmat)
/*      */   {
/*  973 */     PhasorMatrix pmat = toPhasorMatrix(bmat);
/*  974 */     return over(pmat);
/*      */   }
/*      */   
/*      */   public void overEquals(PhasorMatrix bmat)
/*      */   {
/*  979 */     if ((this.nrow != bmat.nrow) || (this.ncol != bmat.ncol)) {
/*  980 */       throw new IllegalArgumentException("Array dimensions do not agree");
/*      */     }
/*  982 */     PhasorMatrix cmat = new PhasorMatrix(bmat);
/*  983 */     timesEquals(cmat.inverse());
/*      */   }
/*      */   
/*      */   public void overEquals(Phasor[][] bmat)
/*      */   {
/*  988 */     PhasorMatrix pmat = new PhasorMatrix(bmat);
/*  989 */     overEquals(pmat);
/*      */   }
/*      */   
/*      */   public void overEquals(ComplexMatrix bmat)
/*      */   {
/*  994 */     PhasorMatrix pmat = toPhasorMatrix(bmat);
/*  995 */     overEquals(pmat);
/*      */   }
/*      */   
/*      */   public void overEquals(Complex[][] bmat)
/*      */   {
/* 1000 */     PhasorMatrix pmat = toPhasorMatrix(bmat);
/* 1001 */     overEquals(pmat);
/*      */   }
/*      */   
/*      */   public void overEquals(Matrix bmat)
/*      */   {
/* 1006 */     PhasorMatrix pmat = toPhasorMatrix(bmat);
/* 1007 */     overEquals(pmat);
/*      */   }
/*      */   
/*      */   public void overEquals(double[][] bmat)
/*      */   {
/* 1012 */     PhasorMatrix pmat = toPhasorMatrix(bmat);
/* 1013 */     overEquals(pmat);
/*      */   }
/*      */   
/*      */ 
/*      */   public PhasorMatrix inverse()
/*      */   {
/* 1019 */     int n = this.nrow;
/* 1020 */     if (n != this.ncol) throw new IllegalArgumentException("Matrix is not square");
/* 1021 */     Phasor[] col = new Phasor[n];
/* 1022 */     Phasor[] xvec = new Phasor[n];
/* 1023 */     PhasorMatrix invmat = new PhasorMatrix(n, n);
/* 1024 */     Phasor[][] invarray = invmat.getArrayReference();
/*      */     
/*      */ 
/* 1027 */     PhasorMatrix ludmat = luDecomp();
/* 1028 */     for (int j = 0; j < n; j++) {
/* 1029 */       for (int i = 0; i < n; i++) col[i] = Phasor.zero();
/* 1030 */       col[j] = Phasor.plusOne();
/* 1031 */       xvec = ludmat.luBackSub(col);
/* 1032 */       for (int i = 0; i < n; i++) invarray[i][j] = Phasor.copy(xvec[i]);
/*      */     }
/* 1034 */     return invmat;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */   public PhasorMatrix transpose()
/*      */   {
/* 1042 */     PhasorMatrix tmat = new PhasorMatrix(this.ncol, this.nrow);
/* 1043 */     Phasor[][] tarray = tmat.getArrayReference();
/* 1044 */     for (int i = 0; i < this.ncol; i++) {
/* 1045 */       for (int j = 0; j < this.nrow; j++) {
/* 1046 */         tarray[i][j] = Phasor.copy(this.matrix[j][i]);
/*      */       }
/*      */     }
/* 1049 */     return tmat;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */   public PhasorMatrix conjugate()
/*      */   {
/* 1056 */     PhasorMatrix conj = copy(this);
/* 1057 */     for (int i = 0; i < this.nrow; i++) {
/* 1058 */       for (int j = 0; j < this.ncol; j++) {
/* 1059 */         conj.matrix[i][j] = this.matrix[i][j].conjugate();
/*      */       }
/*      */     }
/* 1062 */     return conj;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */   public PhasorMatrix adjoin()
/*      */   {
/* 1069 */     PhasorMatrix adj = copy(this);
/* 1070 */     adj = adj.transpose();
/* 1071 */     adj = adj.conjugate();
/* 1072 */     return adj;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */   public PhasorMatrix opposite()
/*      */   {
/* 1079 */     PhasorMatrix opp = copy(this);
/* 1080 */     for (int i = 0; i < this.nrow; i++) {
/* 1081 */       for (int j = 0; j < this.ncol; j++) {
/* 1082 */         opp.matrix[i][j] = this.matrix[i][j].times(Phasor.minusOne());
/*      */       }
/*      */     }
/* 1085 */     return opp;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */   public Phasor trace()
/*      */   {
/* 1092 */     Phasor trac = new Phasor(0.0D, 0.0D);
/* 1093 */     for (int i = 0; i < Math.min(this.ncol, this.ncol); i++) {
/* 1094 */       trac.plusEquals(this.matrix[i][i]);
/*      */     }
/* 1096 */     return trac;
/*      */   }
/*      */   
/*      */ 
/*      */   public Phasor determinant()
/*      */   {
/* 1102 */     int n = this.nrow;
/* 1103 */     if (n != this.ncol) throw new IllegalArgumentException("Matrix is not square");
/* 1104 */     Phasor det = new Phasor();
/*      */     
/*      */ 
/* 1107 */     PhasorMatrix ludmat = luDecomp();
/* 1108 */     det.reset(ludmat.dswap, 0.0D);
/* 1109 */     for (int j = 0; j < n; j++) {
/* 1110 */       det.timesEquals(ludmat.matrix[j][j]);
/*      */     }
/* 1112 */     return det;
/*      */   }
/*      */   
/*      */ 
/*      */   public Phasor logDeterminant()
/*      */   {
/* 1118 */     int n = this.nrow;
/* 1119 */     if (n != this.ncol) throw new IllegalArgumentException("Matrix is not square");
/* 1120 */     Phasor det = new Phasor();
/*      */     
/*      */ 
/* 1123 */     PhasorMatrix ludmat = luDecomp();
/* 1124 */     det.reset(ludmat.dswap, 0.0D);
/* 1125 */     det = Phasor.log(det);
/* 1126 */     for (int j = 0; j < n; j++) {
/* 1127 */       det.plusEquals(Phasor.log(ludmat.matrix[j][j]));
/*      */     }
/* 1129 */     return det;
/*      */   }
/*      */   
/*      */ 
/*      */   public double frobeniusNorm()
/*      */   {
/* 1135 */     double norm = 0.0D;
/* 1136 */     for (int i = 0; i < this.nrow; i++) {
/* 1137 */       for (int j = 0; j < this.ncol; j++) {
/* 1138 */         norm = Fmath.hypot(norm, this.matrix[i][j].abs());
/*      */       }
/*      */     }
/* 1141 */     return norm;
/*      */   }
/*      */   
/*      */   public double oneNorm()
/*      */   {
/* 1146 */     double norm = 0.0D;
/* 1147 */     double sum = 0.0D;
/* 1148 */     for (int i = 0; i < this.nrow; i++) {
/* 1149 */       sum = 0.0D;
/* 1150 */       for (int j = 0; j < this.ncol; j++) {
/* 1151 */         sum += this.matrix[i][j].abs();
/*      */       }
/* 1153 */       norm = Math.max(norm, sum);
/*      */     }
/* 1155 */     return norm;
/*      */   }
/*      */   
/*      */   public double infinityNorm()
/*      */   {
/* 1160 */     double norm = 0.0D;
/* 1161 */     double sum = 0.0D;
/* 1162 */     for (int i = 0; i < this.nrow; i++) {
/* 1163 */       sum = 0.0D;
/* 1164 */       for (int j = 0; j < this.ncol; j++) {
/* 1165 */         sum += this.matrix[i][j].abs();
/*      */       }
/* 1167 */       norm = Math.max(norm, sum);
/*      */     }
/* 1169 */     return norm;
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
/*      */   public PhasorMatrix luDecomp()
/*      */   {
/* 1183 */     if (this.nrow != this.ncol) throw new IllegalArgumentException("A matrix is not square");
/* 1184 */     int n = this.nrow;
/* 1185 */     int imax = 0;
/* 1186 */     double dum = 0.0D;double temp = 0.0D;double big = 0.0D;
/* 1187 */     double[] vv = new double[n];
/* 1188 */     Phasor sum = new Phasor();
/* 1189 */     Phasor dumm = new Phasor();
/*      */     
/* 1191 */     PhasorMatrix ludmat = copy(this);
/* 1192 */     Phasor[][] ludarray = ludmat.getArrayReference();
/*      */     
/* 1194 */     ludmat.dswap = 1.0D;
/* 1195 */     for (int i = 0; i < n; i++) {
/* 1196 */       big = 0.0D;
/* 1197 */       for (int j = 0; j < n; j++) {
/* 1198 */         if ((temp = ludarray[i][j].abs()) > big) big = temp;
/*      */       }
/* 1200 */       if (big == 0.0D) throw new ArithmeticException("Singular matrix");
/* 1201 */       vv[i] = (1.0D / big);
/*      */     }
/* 1203 */     for (int j = 0; j < n; j++) {
/* 1204 */       for (int i = 0; i < j; i++) {
/* 1205 */         sum = Phasor.copy(ludarray[i][j]);
/* 1206 */         for (int k = 0; k < i; k++) sum.minusEquals(ludarray[i][k].times(ludarray[k][j]));
/* 1207 */         ludarray[i][j] = Phasor.copy(sum);
/*      */       }
/* 1209 */       big = 0.0D;
/* 1210 */       for (int i = j; i < n; i++) {
/* 1211 */         sum = Phasor.copy(ludarray[i][j]);
/* 1212 */         for (int k = 0; k < j; k++) {
/* 1213 */           sum.minusEquals(ludarray[i][k].times(ludarray[k][j]));
/*      */         }
/* 1215 */         ludarray[i][j] = Phasor.copy(sum);
/* 1216 */         if ((dum = vv[i] * sum.abs()) >= big) {
/* 1217 */           big = dum;
/* 1218 */           imax = i;
/*      */         }
/*      */       }
/* 1221 */       if (j != imax) {
/* 1222 */         for (int k = 0; k < n; k++) {
/* 1223 */           dumm = Phasor.copy(ludarray[imax][k]);
/* 1224 */           ludarray[imax][k] = Phasor.copy(ludarray[j][k]);
/* 1225 */           ludarray[j][k] = Phasor.copy(dumm);
/*      */         }
/* 1227 */         ludmat.dswap = (-ludmat.dswap);
/* 1228 */         vv[imax] = vv[j];
/*      */       }
/* 1230 */       ludmat.index[j] = imax;
/*      */       
/* 1232 */       if (ludarray[j][j].isZero()) {
/* 1233 */         ludarray[j][j].reset(1.0E-30D, 0.0D);
/*      */       }
/* 1235 */       if (j != n - 1) {
/* 1236 */         dumm = ludarray[j][j].inverse();
/* 1237 */         for (int i = j + 1; i < n; i++) {
/* 1238 */           ludarray[i][j].timesEquals(dumm);
/*      */         }
/*      */       }
/*      */     }
/* 1242 */     return ludmat;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */   public Phasor[] luBackSub(Phasor[] bvec)
/*      */   {
/* 1250 */     int ii = 0;int ip = 0;
/* 1251 */     int n = bvec.length;
/* 1252 */     if (n != this.ncol) throw new IllegalArgumentException("vector length is not equal to matrix dimension");
/* 1253 */     if (this.ncol != this.nrow) throw new IllegalArgumentException("matrix is not square");
/* 1254 */     Phasor sum = new Phasor();
/* 1255 */     Phasor[] xvec = new Phasor[n];
/* 1256 */     for (int i = 0; i < n; i++) {
/* 1257 */       xvec[i] = Phasor.copy(bvec[i]);
/*      */     }
/* 1259 */     for (int i = 0; i < n; i++) {
/* 1260 */       ip = this.index[i];
/* 1261 */       sum = Phasor.copy(xvec[ip]);
/* 1262 */       xvec[ip] = Phasor.copy(xvec[i]);
/* 1263 */       if (ii == 0) {
/* 1264 */         for (int j = ii; j <= i - 1; j++) {
/* 1265 */           sum.minusEquals(this.matrix[i][j].times(xvec[j]));
/*      */         }
/*      */         
/*      */       }
/* 1269 */       else if (sum.isZero()) { ii = i;
/*      */       }
/* 1271 */       xvec[i] = Phasor.copy(sum);
/*      */     }
/* 1273 */     for (int i = n - 1; i >= 0; i--) {
/* 1274 */       sum = Phasor.copy(xvec[i]);
/* 1275 */       for (int j = i + 1; j < n; j++) {
/* 1276 */         sum.minusEquals(this.matrix[i][j].times(xvec[j]));
/*      */       }
/* 1278 */       xvec[i] = sum.over(this.matrix[i][i]);
/*      */     }
/* 1280 */     return xvec;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public Phasor[] solveLinearSet(Phasor[] bvec)
/*      */   {
/* 1289 */     PhasorMatrix ludmat = luDecomp();
/* 1290 */     return ludmat.luBackSub(bvec);
/*      */   }
/*      */ }


/* Location:              /home/menick/Scrivania/ProcessMining/CNMining/CNMining.jar!/flanagan/circuits/PhasorMatrix.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       0.7.1
 */