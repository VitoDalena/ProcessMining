/*      */ package flanagan.math;
/*      */ 
/*      */ import flanagan.analysis.Regression;
/*      */ import flanagan.analysis.Stat;
/*      */ import java.io.PrintStream;
/*      */ import java.math.BigDecimal;
/*      */ import java.math.BigInteger;
/*      */ import java.util.ArrayList;
/*      */ import java.util.Vector;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ public class Matrix
/*      */ {
/*   66 */   private int numberOfRows = 0;
/*   67 */   private int numberOfColumns = 0;
/*   68 */   private double[][] matrix = (double[][])null;
/*   69 */   private double[][] hessenberg = (double[][])null;
/*   70 */   private boolean hessenbergDone = false;
/*   71 */   private int[] permutationIndex = null;
/*   72 */   private double rowSwapIndex = 1.0D;
/*   73 */   private double[] eigenValues = null;
/*   74 */   private double[][] eigenVector = (double[][])null;
/*   75 */   private double[] sortedEigenValues = null;
/*   76 */   private double[][] sortedEigenVector = (double[][])null;
/*   77 */   private int numberOfRotations = 0;
/*   78 */   private int[] eigenIndices = null;
/*   79 */   private int maximumJacobiIterations = 100;
/*   80 */   private boolean eigenDone = false;
/*   81 */   private boolean matrixCheck = true;
/*      */   
/*      */ 
/*      */ 
/*   85 */   private boolean supressErrorMessage = false;
/*      */   
/*   87 */   private double tiny = 1.0E-100D;
/*      */   
/*      */ 
/*      */   public Matrix(int numberOfRows, int numberOfColumns)
/*      */   {
/*   92 */     this.numberOfRows = numberOfRows;
/*   93 */     this.numberOfColumns = numberOfColumns;
/*   94 */     this.matrix = new double[numberOfRows][numberOfColumns];
/*   95 */     this.permutationIndex = new int[numberOfRows];
/*   96 */     for (int i = 0; i < numberOfRows; i++) this.permutationIndex[i] = i;
/*      */   }
/*      */   
/*      */   public Matrix(int numberOfRows, int numberOfColumns, double constant)
/*      */   {
/*  101 */     this.numberOfRows = numberOfRows;
/*  102 */     this.numberOfColumns = numberOfColumns;
/*  103 */     this.matrix = new double[numberOfRows][numberOfColumns];
/*  104 */     for (int i = 0; i < numberOfRows; i++) {
/*  105 */       for (int j = 0; j < numberOfColumns; j++) this.matrix[i][j] = constant;
/*      */     }
/*  107 */     this.permutationIndex = new int[numberOfRows];
/*  108 */     for (int i = 0; i < numberOfRows; i++) this.permutationIndex[i] = i;
/*      */   }
/*      */   
/*      */   public Matrix(double[][] twoD)
/*      */   {
/*  113 */     this.numberOfRows = twoD.length;
/*  114 */     this.numberOfColumns = twoD[0].length;
/*  115 */     for (int i = 0; i < this.numberOfRows; i++) {
/*  116 */       if (twoD[i].length != this.numberOfColumns) throw new IllegalArgumentException("All rows must have the same length");
/*      */     }
/*  118 */     this.matrix = ((double[][])twoD.clone());
/*  119 */     this.permutationIndex = new int[this.numberOfRows];
/*  120 */     for (int i = 0; i < this.numberOfRows; i++) this.permutationIndex[i] = i;
/*      */   }
/*      */   
/*      */   public Matrix(float[][] twoD)
/*      */   {
/*  125 */     this.numberOfRows = twoD.length;
/*  126 */     this.numberOfColumns = twoD[0].length;
/*  127 */     for (int i = 1; i < this.numberOfRows; i++) {
/*  128 */       if (twoD[i].length != this.numberOfColumns) throw new IllegalArgumentException("All rows must have the same length");
/*      */     }
/*  130 */     this.matrix = new double[this.numberOfRows][this.numberOfColumns];
/*  131 */     for (int i = 0; i < this.numberOfRows; i++) {
/*  132 */       for (int j = 0; j < this.numberOfColumns; j++) {
/*  133 */         this.matrix[i][j] = twoD[i][j];
/*      */       }
/*      */     }
/*  136 */     this.permutationIndex = new int[this.numberOfRows];
/*  137 */     for (int i = 0; i < this.numberOfRows; i++) this.permutationIndex[i] = i;
/*      */   }
/*      */   
/*      */   public Matrix(long[][] twoD)
/*      */   {
/*  142 */     this.numberOfRows = twoD.length;
/*  143 */     this.numberOfColumns = twoD[0].length;
/*  144 */     for (int i = 1; i < this.numberOfRows; i++) {
/*  145 */       if (twoD[i].length != this.numberOfColumns) throw new IllegalArgumentException("All rows must have the same length");
/*      */     }
/*  147 */     this.matrix = new double[this.numberOfRows][this.numberOfColumns];
/*  148 */     for (int i = 0; i < this.numberOfRows; i++) {
/*  149 */       for (int j = 0; j < this.numberOfColumns; j++) {
/*  150 */         this.matrix[i][j] = twoD[i][j];
/*      */       }
/*      */     }
/*  153 */     this.permutationIndex = new int[this.numberOfRows];
/*  154 */     for (int i = 0; i < this.numberOfRows; i++) { this.permutationIndex[i] = i;
/*      */     }
/*      */   }
/*      */   
/*      */   public Matrix(int[][] twoD)
/*      */   {
/*  160 */     this.numberOfRows = twoD.length;
/*  161 */     this.numberOfColumns = twoD[0].length;
/*  162 */     for (int i = 1; i < this.numberOfRows; i++) {
/*  163 */       if (twoD[i].length != this.numberOfColumns) throw new IllegalArgumentException("All rows must have the same length");
/*      */     }
/*  165 */     this.matrix = new double[this.numberOfRows][this.numberOfColumns];
/*  166 */     for (int i = 0; i < this.numberOfRows; i++) {
/*  167 */       for (int j = 0; j < this.numberOfColumns; j++) {
/*  168 */         this.matrix[i][j] = twoD[i][j];
/*      */       }
/*      */     }
/*  171 */     this.permutationIndex = new int[this.numberOfRows];
/*  172 */     for (int i = 0; i < this.numberOfRows; i++) this.permutationIndex[i] = i;
/*      */   }
/*      */   
/*      */   public Matrix(ArrayMaths[] twoD)
/*      */   {
/*  177 */     this.numberOfRows = twoD.length;
/*  178 */     this.numberOfColumns = twoD[0].length();
/*  179 */     this.matrix = new double[this.numberOfRows][this.numberOfColumns];
/*  180 */     for (int i = 0; i < this.numberOfRows; i++) {
/*  181 */       double[] arrayh = twoD[i].array();
/*  182 */       if (arrayh.length != this.numberOfColumns) throw new IllegalArgumentException("All rows must have the same length");
/*  183 */       this.matrix[i] = arrayh;
/*      */     }
/*  185 */     this.permutationIndex = new int[this.numberOfRows];
/*  186 */     for (int i = 0; i < this.numberOfRows; i++) this.permutationIndex[i] = i;
/*      */   }
/*      */   
/*      */   public Matrix(ArrayList<Object>[] twoDal)
/*      */   {
/*  191 */     this.numberOfRows = twoDal.length;
/*  192 */     ArrayMaths[] twoD = new ArrayMaths[this.numberOfRows];
/*  193 */     for (int i = 0; i < this.numberOfRows; i++) {
/*  194 */       twoD[i] = new ArrayMaths(twoDal[i]);
/*      */     }
/*      */     
/*  197 */     this.numberOfColumns = twoD[0].length();
/*  198 */     this.matrix = new double[this.numberOfRows][this.numberOfColumns];
/*  199 */     for (int i = 0; i < this.numberOfRows; i++) {
/*  200 */       double[] arrayh = twoD[i].array();
/*  201 */       if (arrayh.length != this.numberOfColumns) throw new IllegalArgumentException("All rows must have the same length");
/*  202 */       this.matrix[i] = arrayh;
/*      */     }
/*  204 */     this.permutationIndex = new int[this.numberOfRows];
/*  205 */     for (int i = 0; i < this.numberOfRows; i++) this.permutationIndex[i] = i;
/*      */   }
/*      */   
/*      */   public Matrix(Vector<Object>[] twoDv)
/*      */   {
/*  210 */     this.numberOfRows = twoDv.length;
/*  211 */     ArrayMaths[] twoD = new ArrayMaths[this.numberOfRows];
/*  212 */     for (int i = 0; i < this.numberOfRows; i++) {
/*  213 */       twoD[i] = new ArrayMaths(twoDv[i]);
/*      */     }
/*      */     
/*  216 */     this.numberOfColumns = twoD[0].length();
/*  217 */     this.matrix = new double[this.numberOfRows][this.numberOfColumns];
/*  218 */     for (int i = 0; i < this.numberOfRows; i++) {
/*  219 */       double[] arrayh = twoD[i].array();
/*  220 */       if (arrayh.length != this.numberOfColumns) throw new IllegalArgumentException("All rows must have the same length");
/*  221 */       this.matrix[i] = arrayh;
/*      */     }
/*  223 */     this.permutationIndex = new int[this.numberOfRows];
/*  224 */     for (int i = 0; i < this.numberOfRows; i++) this.permutationIndex[i] = i;
/*      */   }
/*      */   
/*      */   public Matrix(BigDecimal[][] twoD)
/*      */   {
/*  229 */     this.numberOfRows = twoD.length;
/*  230 */     this.numberOfColumns = twoD[0].length;
/*  231 */     for (int i = 1; i < this.numberOfRows; i++) {
/*  232 */       if (twoD[i].length != this.numberOfColumns) throw new IllegalArgumentException("All rows must have the same length");
/*      */     }
/*  234 */     this.matrix = new double[this.numberOfRows][this.numberOfColumns];
/*  235 */     for (int i = 0; i < this.numberOfRows; i++) {
/*  236 */       for (int j = 0; j < this.numberOfColumns; j++) {
/*  237 */         this.matrix[i][j] = twoD[i][j].doubleValue();
/*      */       }
/*      */     }
/*  240 */     this.permutationIndex = new int[this.numberOfRows];
/*  241 */     for (int i = 0; i < this.numberOfRows; i++) this.permutationIndex[i] = i;
/*      */   }
/*      */   
/*      */   public Matrix(BigInteger[][] twoD)
/*      */   {
/*  246 */     this.numberOfRows = twoD.length;
/*  247 */     this.numberOfColumns = twoD[0].length;
/*  248 */     for (int i = 1; i < this.numberOfRows; i++) {
/*  249 */       if (twoD[i].length != this.numberOfColumns) throw new IllegalArgumentException("All rows must have the same length");
/*      */     }
/*  251 */     this.matrix = new double[this.numberOfRows][this.numberOfColumns];
/*  252 */     for (int i = 0; i < this.numberOfRows; i++) {
/*  253 */       for (int j = 0; j < this.numberOfColumns; j++) {
/*  254 */         this.matrix[i][j] = twoD[i][j].doubleValue();
/*      */       }
/*      */     }
/*  257 */     this.permutationIndex = new int[this.numberOfRows];
/*  258 */     for (int i = 0; i < this.numberOfRows; i++) { this.permutationIndex[i] = i;
/*      */     }
/*      */   }
/*      */   
/*      */ 
/*      */   public Matrix(Matrix bb)
/*      */   {
/*  265 */     this.numberOfRows = bb.numberOfRows;
/*  266 */     this.numberOfColumns = bb.numberOfColumns;
/*  267 */     this.matrix = ((double[][])bb.matrix.clone());
/*  268 */     this.permutationIndex = ((int[])bb.permutationIndex.clone());
/*  269 */     this.rowSwapIndex = bb.rowSwapIndex;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public void resetLUzero(double zeroValue)
/*      */   {
/*  278 */     this.tiny = zeroValue;
/*      */   }
/*      */   
/*      */   public void setTwoDarray(double[][] aarray)
/*      */   {
/*  283 */     if (this.numberOfRows != aarray.length) throw new IllegalArgumentException("row length of this Matrix differs from that of the 2D array argument");
/*  284 */     if (this.numberOfColumns != aarray[0].length) throw new IllegalArgumentException("column length of this Matrix differs from that of the 2D array argument");
/*  285 */     for (int i = 0; i < this.numberOfRows; i++) {
/*  286 */       if (aarray[i].length != this.numberOfColumns) throw new IllegalArgumentException("All rows must have the same length");
/*  287 */       for (int j = 0; j < this.numberOfColumns; j++) {
/*  288 */         this.matrix[i][j] = aarray[i][j];
/*      */       }
/*      */     }
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */   public void setElement(int i, int j, double aa)
/*      */   {
/*  298 */     this.matrix[i][j] = aa;
/*      */   }
/*      */   
/*      */   public void setSubMatrix(int i, int j, double[][] subMatrix)
/*      */   {
/*  303 */     int k = subMatrix.length;
/*  304 */     int l = subMatrix[0].length;
/*  305 */     if (i > k) throw new IllegalArgumentException("row indices inverted");
/*  306 */     if (j > l) throw new IllegalArgumentException("column indices inverted");
/*  307 */     int n = k - i + 1;int m = l - j + 1;
/*  308 */     for (int p = 0; p < n; p++) {
/*  309 */       for (int q = 0; q < m; q++) {
/*  310 */         this.matrix[(i + p)][(j + q)] = subMatrix[p][q];
/*      */       }
/*      */     }
/*      */   }
/*      */   
/*      */ 
/*      */   public void setSubMatrix(int i, int j, int k, int l, double[][] subMatrix)
/*      */   {
/*  318 */     if (i > k) throw new IllegalArgumentException("row indices inverted");
/*  319 */     if (j > l) throw new IllegalArgumentException("column indices inverted");
/*  320 */     int n = k - i + 1;int m = l - j + 1;
/*  321 */     for (int p = 0; p < n; p++) {
/*  322 */       for (int q = 0; q < m; q++) {
/*  323 */         this.matrix[(i + p)][(j + q)] = subMatrix[p][q];
/*      */       }
/*      */     }
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */   public void setSubMatrix(int[] row, int[] col, double[][] subMatrix)
/*      */   {
/*  332 */     int n = row.length;
/*  333 */     int m = col.length;
/*  334 */     for (int p = 0; p < n; p++) {
/*  335 */       for (int q = 0; q < m; q++) {
/*  336 */         this.matrix[row[p]][col[q]] = subMatrix[p][q];
/*      */       }
/*      */     }
/*      */   }
/*      */   
/*      */   public boolean getMatrixCheck()
/*      */   {
/*  343 */     return this.matrixCheck;
/*      */   }
/*      */   
/*      */ 
/*      */   public static Matrix identityMatrix(int numberOfRows)
/*      */   {
/*  349 */     Matrix special = new Matrix(numberOfRows, numberOfRows);
/*  350 */     for (int i = 0; i < numberOfRows; i++) {
/*  351 */       special.matrix[i][i] = 1.0D;
/*      */     }
/*  353 */     return special;
/*      */   }
/*      */   
/*      */   public static Matrix unitMatrix(int numberOfRows)
/*      */   {
/*  358 */     Matrix special = new Matrix(numberOfRows, numberOfRows);
/*  359 */     for (int i = 0; i < numberOfRows; i++) {
/*  360 */       for (int j = 0; j < numberOfRows; j++) {
/*  361 */         special.matrix[i][j] = 1.0D;
/*      */       }
/*      */     }
/*  364 */     return special;
/*      */   }
/*      */   
/*      */   public static Matrix unitMatrix(int numberOfRows, int numberOfColumns)
/*      */   {
/*  369 */     Matrix special = new Matrix(numberOfRows, numberOfColumns);
/*  370 */     for (int i = 0; i < numberOfRows; i++) {
/*  371 */       for (int j = 0; j < numberOfColumns; j++) {
/*  372 */         special.matrix[i][j] = 1.0D;
/*      */       }
/*      */     }
/*  375 */     return special;
/*      */   }
/*      */   
/*      */   public static Matrix scalarMatrix(int numberOfRows, double diagconst)
/*      */   {
/*  380 */     Matrix special = new Matrix(numberOfRows, numberOfRows);
/*  381 */     double[][] specialArray = special.getArrayReference();
/*  382 */     for (int i = 0; i < numberOfRows; i++) {
/*  383 */       for (int j = i; j < numberOfRows; j++) {
/*  384 */         if (i == j) {
/*  385 */           specialArray[i][j] = diagconst;
/*      */         }
/*      */       }
/*      */     }
/*  389 */     return special;
/*      */   }
/*      */   
/*      */   public static Matrix scalarMatrix(int numberOfRows, int numberOfColumns, double diagconst)
/*      */   {
/*  394 */     Matrix special = new Matrix(numberOfRows, numberOfColumns);
/*  395 */     double[][] specialArray = special.getArrayReference();
/*  396 */     for (int i = 0; i < numberOfRows; i++) {
/*  397 */       for (int j = i; j < numberOfColumns; j++) {
/*  398 */         if (i == j) {
/*  399 */           specialArray[i][j] = diagconst;
/*      */         }
/*      */       }
/*      */     }
/*  403 */     return special;
/*      */   }
/*      */   
/*      */   public static Matrix diagonalMatrix(int numberOfRows, double[] diag)
/*      */   {
/*  408 */     if (diag.length != numberOfRows) throw new IllegalArgumentException("matrix dimension differs from diagonal array length");
/*  409 */     Matrix special = new Matrix(numberOfRows, numberOfRows);
/*  410 */     double[][] specialArray = special.getArrayReference();
/*  411 */     for (int i = 0; i < numberOfRows; i++) {
/*  412 */       specialArray[i][i] = diag[i];
/*      */     }
/*  414 */     return special;
/*      */   }
/*      */   
/*      */   public static Matrix diagonalMatrix(int numberOfRows, int numberOfColumns, double[] diag)
/*      */   {
/*  419 */     if (diag.length != numberOfRows) throw new IllegalArgumentException("matrix dimension differs from diagonal array length");
/*  420 */     Matrix special = new Matrix(numberOfRows, numberOfColumns);
/*  421 */     double[][] specialArray = special.getArrayReference();
/*  422 */     for (int i = 0; i < numberOfRows; i++) {
/*  423 */       for (int j = i; j < numberOfColumns; j++) {
/*  424 */         if (i == j) {
/*  425 */           specialArray[i][j] = diag[i];
/*      */         }
/*      */       }
/*      */     }
/*  429 */     return special;
/*      */   }
/*      */   
/*      */ 
/*      */   public int getNumberOfRows()
/*      */   {
/*  435 */     return this.numberOfRows;
/*      */   }
/*      */   
/*      */   public int getNrow()
/*      */   {
/*  440 */     return this.numberOfRows;
/*      */   }
/*      */   
/*      */   public int getNumberOfColumns()
/*      */   {
/*  445 */     return this.numberOfColumns;
/*      */   }
/*      */   
/*      */   public int getNcol()
/*      */   {
/*  450 */     return this.numberOfColumns;
/*      */   }
/*      */   
/*      */   public double[][] getArrayReference()
/*      */   {
/*  455 */     return this.matrix;
/*      */   }
/*      */   
/*      */ 
/*      */   public double[][] getArrayPointer()
/*      */   {
/*  461 */     return this.matrix;
/*      */   }
/*      */   
/*      */   public double[][] getArrayCopy()
/*      */   {
/*  466 */     double[][] c = new double[this.numberOfRows][this.numberOfColumns];
/*  467 */     for (int i = 0; i < this.numberOfRows; i++) {
/*  468 */       for (int j = 0; j < this.numberOfColumns; j++) {
/*  469 */         c[i][j] = this.matrix[i][j];
/*      */       }
/*      */     }
/*  472 */     return c;
/*      */   }
/*      */   
/*      */   public double[] getRowCopy(int i)
/*      */   {
/*  477 */     if (i >= this.numberOfRows) throw new IllegalArgumentException("Row index, " + i + ", must be less than the number of rows, " + this.numberOfRows);
/*  478 */     if (i < 0) throw new IllegalArgumentException("Row index, " + i + ", must be zero or positive");
/*  479 */     return (double[])this.matrix[i].clone();
/*      */   }
/*      */   
/*      */   public double[] getColumnCopy(int ii)
/*      */   {
/*  484 */     if (ii >= this.numberOfColumns) throw new IllegalArgumentException("Column index, " + ii + ", must be less than the number of columns, " + this.numberOfColumns);
/*  485 */     if (ii < 0) throw new IllegalArgumentException("column index, " + ii + ", must be zero or positive");
/*  486 */     double[] col = new double[this.numberOfRows];
/*  487 */     for (int i = 0; i < this.numberOfRows; i++) {
/*  488 */       col[i] = this.matrix[i][ii];
/*      */     }
/*  490 */     return col;
/*      */   }
/*      */   
/*      */ 
/*      */   public double getElement(int i, int j)
/*      */   {
/*  496 */     return this.matrix[i][j];
/*      */   }
/*      */   
/*      */ 
/*      */   public double getElementCopy(int i, int j)
/*      */   {
/*  502 */     return this.matrix[i][j];
/*      */   }
/*      */   
/*      */ 
/*      */   public double getElementPointer(int i, int j)
/*      */   {
/*  508 */     return this.matrix[i][j];
/*      */   }
/*      */   
/*      */ 
/*      */   public Matrix getSubMatrix(int i, int j, int k, int l)
/*      */   {
/*  514 */     if (i > k) throw new IllegalArgumentException("row indices inverted");
/*  515 */     if (j > l) throw new IllegalArgumentException("column indices inverted");
/*  516 */     int n = k - i + 1;int m = l - j + 1;
/*  517 */     Matrix subMatrix = new Matrix(n, m);
/*  518 */     double[][] sarray = subMatrix.getArrayReference();
/*  519 */     for (int p = 0; p < n; p++) {
/*  520 */       for (int q = 0; q < m; q++) {
/*  521 */         sarray[p][q] = this.matrix[(i + p)][(j + q)];
/*      */       }
/*      */     }
/*  524 */     return subMatrix;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */   public Matrix getSubMatrix(int[] row, int[] col)
/*      */   {
/*  531 */     int n = row.length;
/*  532 */     int m = col.length;
/*  533 */     Matrix subMatrix = new Matrix(n, m);
/*  534 */     double[][] sarray = subMatrix.getArrayReference();
/*  535 */     for (int i = 0; i < n; i++) {
/*  536 */       for (int j = 0; j < m; j++) {
/*  537 */         sarray[i][j] = this.matrix[row[i]][col[j]];
/*      */       }
/*      */     }
/*  540 */     return subMatrix;
/*      */   }
/*      */   
/*      */   public int[] getIndexReference()
/*      */   {
/*  545 */     return this.permutationIndex;
/*      */   }
/*      */   
/*      */ 
/*      */   public int[] getIndexPointer()
/*      */   {
/*  551 */     return this.permutationIndex;
/*      */   }
/*      */   
/*      */   public int[] getIndexCopy()
/*      */   {
/*  556 */     int[] indcopy = new int[this.numberOfRows];
/*  557 */     for (int i = 0; i < this.numberOfRows; i++) {
/*  558 */       indcopy[i] = this.permutationIndex[i];
/*      */     }
/*  560 */     return indcopy;
/*      */   }
/*      */   
/*      */   public double getSwap()
/*      */   {
/*  565 */     return this.rowSwapIndex;
/*      */   }
/*      */   
/*      */ 
/*      */   public static Matrix copy(Matrix a)
/*      */   {
/*  571 */     if (a == null) {
/*  572 */       return null;
/*      */     }
/*      */     
/*  575 */     int nr = a.getNumberOfRows();
/*  576 */     int nc = a.getNumberOfColumns();
/*  577 */     double[][] aarray = a.getArrayReference();
/*  578 */     Matrix b = new Matrix(nr, nc);
/*  579 */     b.numberOfRows = nr;
/*  580 */     b.numberOfColumns = nc;
/*  581 */     double[][] barray = b.getArrayReference();
/*  582 */     for (int i = 0; i < nr; i++) {
/*  583 */       for (int j = 0; j < nc; j++) {
/*  584 */         barray[i][j] = aarray[i][j];
/*      */       }
/*      */     }
/*  587 */     for (int i = 0; i < nr; i++) b.permutationIndex[i] = a.permutationIndex[i];
/*  588 */     return b;
/*      */   }
/*      */   
/*      */ 
/*      */   public Matrix copy()
/*      */   {
/*  594 */     if (this == null) {
/*  595 */       return null;
/*      */     }
/*      */     
/*  598 */     int nr = this.numberOfRows;
/*  599 */     int nc = this.numberOfColumns;
/*  600 */     Matrix b = new Matrix(nr, nc);
/*  601 */     double[][] barray = b.getArrayReference();
/*  602 */     b.numberOfRows = nr;
/*  603 */     b.numberOfColumns = nc;
/*  604 */     for (int i = 0; i < nr; i++) {
/*  605 */       for (int j = 0; j < nc; j++) {
/*  606 */         barray[i][j] = this.matrix[i][j];
/*      */       }
/*      */     }
/*  609 */     for (int i = 0; i < nr; i++) b.permutationIndex[i] = this.permutationIndex[i];
/*  610 */     return b;
/*      */   }
/*      */   
/*      */ 
/*      */   public Object clone()
/*      */   {
/*  616 */     if (this == null) {
/*  617 */       return null;
/*      */     }
/*      */     
/*  620 */     int nr = this.numberOfRows;
/*  621 */     int nc = this.numberOfColumns;
/*  622 */     Matrix b = new Matrix(nr, nc);
/*  623 */     double[][] barray = b.getArrayReference();
/*  624 */     b.numberOfRows = nr;
/*  625 */     b.numberOfColumns = nc;
/*  626 */     for (int i = 0; i < nr; i++) {
/*  627 */       for (int j = 0; j < nc; j++) {
/*  628 */         barray[i][j] = this.matrix[i][j];
/*      */       }
/*      */     }
/*  631 */     for (int i = 0; i < nr; i++) b.permutationIndex[i] = this.permutationIndex[i];
/*  632 */     return b;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */   public static Matrix columnMatrix(double[] darray)
/*      */   {
/*  639 */     int nr = darray.length;
/*  640 */     Matrix pp = new Matrix(nr, 1);
/*  641 */     for (int i = 0; i < nr; i++) pp.matrix[i][0] = darray[i];
/*  642 */     return pp;
/*      */   }
/*      */   
/*      */ 
/*      */   public static Matrix rowMatrix(double[] darray)
/*      */   {
/*  648 */     int nc = darray.length;
/*  649 */     Matrix pp = new Matrix(1, nc);
/*  650 */     for (int i = 0; i < nc; i++) pp.matrix[0][i] = darray[i];
/*  651 */     return pp;
/*      */   }
/*      */   
/*      */ 
/*      */   public Matrix plus(Matrix bmat)
/*      */   {
/*  657 */     if ((this.numberOfRows != bmat.numberOfRows) || (this.numberOfColumns != bmat.numberOfColumns)) {
/*  658 */       throw new IllegalArgumentException("Array dimensions do not agree");
/*      */     }
/*  660 */     int nr = bmat.numberOfRows;
/*  661 */     int nc = bmat.numberOfColumns;
/*  662 */     Matrix cmat = new Matrix(nr, nc);
/*  663 */     double[][] carray = cmat.getArrayReference();
/*  664 */     for (int i = 0; i < nr; i++) {
/*  665 */       for (int j = 0; j < nc; j++) {
/*  666 */         carray[i][j] = (this.matrix[i][j] + bmat.matrix[i][j]);
/*      */       }
/*      */     }
/*  669 */     return cmat;
/*      */   }
/*      */   
/*      */   public Matrix plus(double[][] bmat)
/*      */   {
/*  674 */     int nr = bmat.length;
/*  675 */     int nc = bmat[0].length;
/*  676 */     if ((this.numberOfRows != nr) || (this.numberOfColumns != nc)) {
/*  677 */       throw new IllegalArgumentException("Array dimensions do not agree");
/*      */     }
/*      */     
/*  680 */     Matrix cmat = new Matrix(nr, nc);
/*  681 */     double[][] carray = cmat.getArrayReference();
/*  682 */     for (int i = 0; i < nr; i++) {
/*  683 */       for (int j = 0; j < nc; j++) {
/*  684 */         carray[i][j] = (this.matrix[i][j] + bmat[i][j]);
/*      */       }
/*      */     }
/*  687 */     return cmat;
/*      */   }
/*      */   
/*      */ 
/*      */   public static Matrix plus(Matrix amat, Matrix bmat)
/*      */   {
/*  693 */     if ((amat.numberOfRows != bmat.numberOfRows) || (amat.numberOfColumns != bmat.numberOfColumns)) {
/*  694 */       throw new IllegalArgumentException("Array dimensions do not agree");
/*      */     }
/*  696 */     int nr = amat.numberOfRows;
/*  697 */     int nc = amat.numberOfColumns;
/*  698 */     Matrix cmat = new Matrix(nr, nc);
/*  699 */     double[][] carray = cmat.getArrayReference();
/*  700 */     for (int i = 0; i < nr; i++) {
/*  701 */       for (int j = 0; j < nc; j++) {
/*  702 */         carray[i][j] = (amat.matrix[i][j] + bmat.matrix[i][j]);
/*      */       }
/*      */     }
/*  705 */     return cmat;
/*      */   }
/*      */   
/*      */   public void plusEquals(Matrix bmat)
/*      */   {
/*  710 */     if ((this.numberOfRows != bmat.numberOfRows) || (this.numberOfColumns != bmat.numberOfColumns)) {
/*  711 */       throw new IllegalArgumentException("Array dimensions do not agree");
/*      */     }
/*  713 */     int nr = bmat.numberOfRows;
/*  714 */     int nc = bmat.numberOfColumns;
/*      */     
/*  716 */     for (int i = 0; i < nr; i++) {
/*  717 */       for (int j = 0; j < nc; j++) {
/*  718 */         this.matrix[i][j] += bmat.matrix[i][j];
/*      */       }
/*      */     }
/*      */   }
/*      */   
/*      */ 
/*      */   public Matrix minus(Matrix bmat)
/*      */   {
/*  726 */     if ((this.numberOfRows != bmat.numberOfRows) || (this.numberOfColumns != bmat.numberOfColumns)) {
/*  727 */       throw new IllegalArgumentException("Array dimensions do not agree");
/*      */     }
/*  729 */     int nr = this.numberOfRows;
/*  730 */     int nc = this.numberOfColumns;
/*  731 */     Matrix cmat = new Matrix(nr, nc);
/*  732 */     double[][] carray = cmat.getArrayReference();
/*  733 */     for (int i = 0; i < nr; i++) {
/*  734 */       for (int j = 0; j < nc; j++) {
/*  735 */         carray[i][j] = (this.matrix[i][j] - bmat.matrix[i][j]);
/*      */       }
/*      */     }
/*  738 */     return cmat;
/*      */   }
/*      */   
/*      */   public Matrix minus(double[][] bmat)
/*      */   {
/*  743 */     int nr = bmat.length;
/*  744 */     int nc = bmat[0].length;
/*  745 */     if ((this.numberOfRows != nr) || (this.numberOfColumns != nc)) {
/*  746 */       throw new IllegalArgumentException("Array dimensions do not agree");
/*      */     }
/*      */     
/*  749 */     Matrix cmat = new Matrix(nr, nc);
/*  750 */     double[][] carray = cmat.getArrayReference();
/*  751 */     for (int i = 0; i < nr; i++) {
/*  752 */       for (int j = 0; j < nc; j++) {
/*  753 */         carray[i][j] = (this.matrix[i][j] - bmat[i][j]);
/*      */       }
/*      */     }
/*  756 */     return cmat;
/*      */   }
/*      */   
/*      */ 
/*      */   public static Matrix minus(Matrix amat, Matrix bmat)
/*      */   {
/*  762 */     if ((amat.numberOfRows != bmat.numberOfRows) || (amat.numberOfColumns != bmat.numberOfColumns)) {
/*  763 */       throw new IllegalArgumentException("Array dimensions do not agree");
/*      */     }
/*  765 */     int nr = amat.numberOfRows;
/*  766 */     int nc = amat.numberOfColumns;
/*  767 */     Matrix cmat = new Matrix(nr, nc);
/*  768 */     double[][] carray = cmat.getArrayReference();
/*  769 */     for (int i = 0; i < nr; i++) {
/*  770 */       for (int j = 0; j < nc; j++) {
/*  771 */         carray[i][j] = (amat.matrix[i][j] - bmat.matrix[i][j]);
/*      */       }
/*      */     }
/*  774 */     return cmat;
/*      */   }
/*      */   
/*      */   public void minusEquals(Matrix bmat)
/*      */   {
/*  779 */     if ((this.numberOfRows != bmat.numberOfRows) || (this.numberOfColumns != bmat.numberOfColumns)) {
/*  780 */       throw new IllegalArgumentException("Array dimensions do not agree");
/*      */     }
/*  782 */     int nr = bmat.numberOfRows;
/*  783 */     int nc = bmat.numberOfColumns;
/*      */     
/*  785 */     for (int i = 0; i < nr; i++) {
/*  786 */       for (int j = 0; j < nc; j++) {
/*  787 */         this.matrix[i][j] -= bmat.matrix[i][j];
/*      */       }
/*      */     }
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */   public Matrix times(Matrix bmat)
/*      */   {
/*  796 */     if (this.numberOfColumns != bmat.numberOfRows) { throw new IllegalArgumentException("Nonconformable matrices");
/*      */     }
/*  798 */     Matrix cmat = new Matrix(this.numberOfRows, bmat.numberOfColumns);
/*  799 */     double[][] carray = cmat.getArrayReference();
/*  800 */     double sum = 0.0D;
/*      */     
/*  802 */     for (int i = 0; i < this.numberOfRows; i++) {
/*  803 */       for (int j = 0; j < bmat.numberOfColumns; j++) {
/*  804 */         sum = 0.0D;
/*  805 */         for (int k = 0; k < this.numberOfColumns; k++) {
/*  806 */           sum += this.matrix[i][k] * bmat.matrix[k][j];
/*      */         }
/*  808 */         carray[i][j] = sum;
/*      */       }
/*      */     }
/*  811 */     return cmat;
/*      */   }
/*      */   
/*      */ 
/*      */   public Matrix times(double[][] bmat)
/*      */   {
/*  817 */     int nr = bmat.length;
/*  818 */     int nc = bmat[0].length;
/*      */     
/*  820 */     if (this.numberOfColumns != nr) { throw new IllegalArgumentException("Nonconformable matrices");
/*      */     }
/*  822 */     Matrix cmat = new Matrix(this.numberOfRows, nc);
/*  823 */     double[][] carray = cmat.getArrayReference();
/*  824 */     double sum = 0.0D;
/*      */     
/*  826 */     for (int i = 0; i < this.numberOfRows; i++) {
/*  827 */       for (int j = 0; j < nc; j++) {
/*  828 */         sum = 0.0D;
/*  829 */         for (int k = 0; k < this.numberOfColumns; k++) {
/*  830 */           sum += this.matrix[i][k] * bmat[k][j];
/*      */         }
/*  832 */         carray[i][j] = sum;
/*      */       }
/*      */     }
/*  835 */     return cmat;
/*      */   }
/*      */   
/*      */ 
/*      */   public Matrix times(double constant)
/*      */   {
/*  841 */     Matrix cmat = new Matrix(this.numberOfRows, this.numberOfColumns);
/*  842 */     double[][] carray = cmat.getArrayReference();
/*      */     
/*  844 */     for (int i = 0; i < this.numberOfRows; i++) {
/*  845 */       for (int j = 0; j < this.numberOfColumns; j++) {
/*  846 */         carray[i][j] = (this.matrix[i][j] * constant);
/*      */       }
/*      */     }
/*  849 */     return cmat;
/*      */   }
/*      */   
/*      */   public static Matrix times(Matrix amat, Matrix bmat)
/*      */   {
/*  854 */     if (amat.numberOfColumns != bmat.numberOfRows) { throw new IllegalArgumentException("Nonconformable matrices");
/*      */     }
/*  856 */     Matrix cmat = new Matrix(amat.numberOfRows, bmat.numberOfColumns);
/*  857 */     double[][] carray = cmat.getArrayReference();
/*  858 */     double sum = 0.0D;
/*      */     
/*  860 */     for (int i = 0; i < amat.numberOfRows; i++) {
/*  861 */       for (int j = 0; j < bmat.numberOfColumns; j++) {
/*  862 */         sum = 0.0D;
/*  863 */         for (int k = 0; k < amat.numberOfColumns; k++) {
/*  864 */           sum += amat.matrix[i][k] * bmat.matrix[k][j];
/*      */         }
/*  866 */         carray[i][j] = sum;
/*      */       }
/*      */     }
/*  869 */     return cmat;
/*      */   }
/*      */   
/*      */   public static Matrix times(Matrix amat, double[][] bmat)
/*      */   {
/*  874 */     if (amat.numberOfColumns != bmat.length) { throw new IllegalArgumentException("Nonconformable matrices");
/*      */     }
/*  876 */     Matrix cmat = new Matrix(amat.numberOfRows, bmat[0].length);
/*  877 */     Matrix dmat = new Matrix(bmat);
/*  878 */     double[][] carray = cmat.getArrayReference();
/*  879 */     double sum = 0.0D;
/*      */     
/*  881 */     for (int i = 0; i < amat.numberOfRows; i++) {
/*  882 */       for (int j = 0; j < dmat.numberOfColumns; j++) {
/*  883 */         sum = 0.0D;
/*  884 */         for (int k = 0; k < amat.numberOfColumns; k++) {
/*  885 */           sum += amat.matrix[i][k] * dmat.matrix[k][j];
/*      */         }
/*  887 */         carray[i][j] = sum;
/*      */       }
/*      */     }
/*  890 */     return cmat;
/*      */   }
/*      */   
/*      */   public static Matrix times(Matrix amat, double constant)
/*      */   {
/*  895 */     Matrix cmat = new Matrix(amat.numberOfRows, amat.numberOfColumns);
/*  896 */     double[][] carray = cmat.getArrayReference();
/*      */     
/*  898 */     for (int i = 0; i < amat.numberOfRows; i++) {
/*  899 */       for (int j = 0; j < amat.numberOfColumns; j++) {
/*  900 */         carray[i][j] = (amat.matrix[i][j] * constant);
/*      */       }
/*      */     }
/*  903 */     return cmat;
/*      */   }
/*      */   
/*      */   public void timesEquals(Matrix bmat)
/*      */   {
/*  908 */     if (this.numberOfColumns != bmat.numberOfRows) { throw new IllegalArgumentException("Nonconformable matrices");
/*      */     }
/*  910 */     double sum = 0.0D;
/*      */     
/*  912 */     for (int i = 0; i < this.numberOfRows; i++) {
/*  913 */       for (int j = 0; j < bmat.numberOfColumns; j++) {
/*  914 */         sum = 0.0D;
/*  915 */         for (int k = 0; k < this.numberOfColumns; k++) {
/*  916 */           sum += this.matrix[i][k] * bmat.matrix[k][j];
/*      */         }
/*  918 */         this.matrix[i][j] = sum;
/*      */       }
/*      */     }
/*      */   }
/*      */   
/*      */ 
/*      */   public void timesEquals(double constant)
/*      */   {
/*  926 */     for (int i = 0; i < this.numberOfRows; i++) {
/*  927 */       for (int j = 0; j < this.numberOfColumns; j++) {
/*  928 */         this.matrix[i][j] *= constant;
/*      */       }
/*      */     }
/*      */   }
/*      */   
/*      */ 
/*      */   public Matrix over(Matrix bmat)
/*      */   {
/*  936 */     if ((this.numberOfRows != bmat.numberOfRows) || (this.numberOfColumns != bmat.numberOfColumns)) {
/*  937 */       throw new IllegalArgumentException("Array dimensions do not agree");
/*      */     }
/*  939 */     return times(bmat.inverse());
/*      */   }
/*      */   
/*      */   public Matrix over(Matrix amat, Matrix bmat)
/*      */   {
/*  944 */     if ((amat.numberOfRows != bmat.numberOfRows) || (amat.numberOfColumns != bmat.numberOfColumns)) {
/*  945 */       throw new IllegalArgumentException("Array dimensions do not agree");
/*      */     }
/*  947 */     return amat.times(bmat.inverse());
/*      */   }
/*      */   
/*      */ 
/*      */   public Matrix over(double[][] bmat)
/*      */   {
/*  953 */     int nr = bmat.length;
/*  954 */     int nc = bmat[0].length;
/*  955 */     if ((this.numberOfRows != nr) || (this.numberOfColumns != nc)) {
/*  956 */       throw new IllegalArgumentException("Array dimensions do not agree");
/*      */     }
/*      */     
/*  959 */     Matrix cmat = new Matrix(bmat);
/*  960 */     return times(cmat.inverse());
/*      */   }
/*      */   
/*      */   public Matrix over(Matrix amat, double[][] bmat)
/*      */   {
/*  965 */     int nr = bmat.length;
/*  966 */     int nc = bmat[0].length;
/*  967 */     if ((amat.numberOfRows != nr) || (amat.numberOfColumns != nc)) {
/*  968 */       throw new IllegalArgumentException("Array dimensions do not agree");
/*      */     }
/*      */     
/*  971 */     Matrix cmat = new Matrix(bmat);
/*  972 */     return amat.times(cmat.inverse());
/*      */   }
/*      */   
/*      */   public Matrix over(double[][] amat, Matrix bmat)
/*      */   {
/*  977 */     int nr = amat.length;
/*  978 */     int nc = amat[0].length;
/*  979 */     if ((bmat.numberOfRows != nr) || (bmat.numberOfColumns != nc)) {
/*  980 */       throw new IllegalArgumentException("Array dimensions do not agree");
/*      */     }
/*      */     
/*  983 */     Matrix cmat = new Matrix(amat);
/*  984 */     return cmat.times(bmat.inverse());
/*      */   }
/*      */   
/*      */   public Matrix over(double[][] amat, double[][] bmat)
/*      */   {
/*  989 */     int nr = amat.length;
/*  990 */     int nc = amat[0].length;
/*  991 */     if ((bmat.length != nr) || (bmat[0].length != nc)) {
/*  992 */       throw new IllegalArgumentException("Array dimensions do not agree");
/*      */     }
/*      */     
/*  995 */     Matrix cmat = new Matrix(amat);
/*  996 */     Matrix dmat = new Matrix(bmat);
/*  997 */     return cmat.times(dmat.inverse());
/*      */   }
/*      */   
/*      */   public void overEquals(Matrix bmat)
/*      */   {
/* 1002 */     if ((this.numberOfRows != bmat.numberOfRows) || (this.numberOfColumns != bmat.numberOfColumns)) {
/* 1003 */       throw new IllegalArgumentException("Array dimensions do not agree");
/*      */     }
/* 1005 */     Matrix cmat = new Matrix(bmat);
/* 1006 */     timesEquals(cmat.inverse());
/*      */   }
/*      */   
/*      */   public void overEquals(double[][] bmat)
/*      */   {
/* 1011 */     Matrix pmat = new Matrix(bmat);
/* 1012 */     overEquals(pmat);
/*      */   }
/*      */   
/*      */ 
/*      */   public Matrix inverse()
/*      */   {
/* 1018 */     int n = this.numberOfRows;
/* 1019 */     if (n != this.numberOfColumns) throw new IllegalArgumentException("Matrix is not square");
/* 1020 */     Matrix invmat = new Matrix(n, n);
/*      */     
/* 1022 */     if (n == 1) {
/* 1023 */       double[][] hold = getArrayCopy();
/* 1024 */       if (hold[0][0] == 0.0D) throw new IllegalArgumentException("Matrix is singular");
/* 1025 */       hold[0][0] = (1.0D / hold[0][0]);
/* 1026 */       invmat = new Matrix(hold);
/*      */ 
/*      */     }
/* 1029 */     else if (n == 2) {
/* 1030 */       double[][] hold = getArrayCopy();
/* 1031 */       double det = hold[0][0] * hold[1][1] - hold[0][1] * hold[1][0];
/* 1032 */       if (det == 0.0D) throw new IllegalArgumentException("Matrix is singular");
/* 1033 */       double[][] hold2 = new double[2][2];
/* 1034 */       hold2[0][0] = (hold[1][1] / det);
/* 1035 */       hold2[1][1] = (hold[0][0] / det);
/* 1036 */       hold2[1][0] = (-hold[1][0] / det);
/* 1037 */       hold2[0][1] = (-hold[0][1] / det);
/* 1038 */       invmat = new Matrix(hold2);
/*      */     }
/*      */     else {
/* 1041 */       double[] col = new double[n];
/* 1042 */       double[] xvec = new double[n];
/* 1043 */       double[][] invarray = invmat.getArrayReference();
/*      */       
/*      */ 
/* 1046 */       Matrix ludmat = luDecomp();
/* 1047 */       for (int j = 0; j < n; j++) {
/* 1048 */         for (int i = 0; i < n; i++) col[i] = 0.0D;
/* 1049 */         col[j] = 1.0D;
/* 1050 */         xvec = ludmat.luBackSub(col);
/* 1051 */         for (int i = 0; i < n; i++) { invarray[i][j] = xvec[i];
/*      */         }
/*      */       }
/*      */     }
/* 1055 */     return invmat;
/*      */   }
/*      */   
/*      */   public static Matrix inverse(Matrix amat)
/*      */   {
/* 1060 */     int n = amat.numberOfRows;
/* 1061 */     if (n != amat.numberOfColumns) throw new IllegalArgumentException("Matrix is not square");
/* 1062 */     Matrix invmat = new Matrix(n, n);
/*      */     
/* 1064 */     if (n == 1) {
/* 1065 */       double[][] hold = amat.getArrayCopy();
/* 1066 */       if (hold[0][0] == 0.0D) throw new IllegalArgumentException("Matrix is singular");
/* 1067 */       hold[0][0] = (1.0D / hold[0][0]);
/* 1068 */       invmat = new Matrix(hold);
/*      */ 
/*      */     }
/* 1071 */     else if (n == 2) {
/* 1072 */       double[][] hold = amat.getArrayCopy();
/* 1073 */       double det = hold[0][0] * hold[1][1] - hold[0][1] * hold[1][0];
/* 1074 */       if (det == 0.0D) throw new IllegalArgumentException("Matrix is singular");
/* 1075 */       double[][] hold2 = new double[2][2];
/* 1076 */       hold2[0][0] = (hold[1][1] / det);
/* 1077 */       hold2[1][1] = (hold[0][0] / det);
/* 1078 */       hold2[1][0] = (-hold[1][0] / det);
/* 1079 */       hold2[0][1] = (-hold[0][1] / det);
/* 1080 */       invmat = new Matrix(hold2);
/*      */     }
/*      */     else {
/* 1083 */       double[] col = new double[n];
/* 1084 */       double[] xvec = new double[n];
/* 1085 */       double[][] invarray = invmat.getArrayReference();
/*      */       
/*      */ 
/* 1088 */       Matrix ludmat = amat.luDecomp();
/* 1089 */       for (int j = 0; j < n; j++) {
/* 1090 */         for (int i = 0; i < n; i++) col[i] = 0.0D;
/* 1091 */         col[j] = 1.0D;
/* 1092 */         xvec = ludmat.luBackSub(col);
/* 1093 */         for (int i = 0; i < n; i++) { invarray[i][j] = xvec[i];
/*      */         }
/*      */       }
/*      */     }
/* 1097 */     return invmat;
/*      */   }
/*      */   
/*      */ 
/*      */   public Matrix transpose()
/*      */   {
/* 1103 */     Matrix tmat = new Matrix(this.numberOfColumns, this.numberOfRows);
/* 1104 */     double[][] tarray = tmat.getArrayReference();
/* 1105 */     for (int i = 0; i < this.numberOfColumns; i++) {
/* 1106 */       for (int j = 0; j < this.numberOfRows; j++) {
/* 1107 */         tarray[i][j] = this.matrix[j][i];
/*      */       }
/*      */     }
/* 1110 */     return tmat;
/*      */   }
/*      */   
/*      */   public static Matrix transpose(Matrix amat)
/*      */   {
/* 1115 */     Matrix tmat = new Matrix(amat.numberOfColumns, amat.numberOfRows);
/* 1116 */     double[][] tarray = tmat.getArrayReference();
/* 1117 */     for (int i = 0; i < amat.numberOfColumns; i++) {
/* 1118 */       for (int j = 0; j < amat.numberOfRows; j++) {
/* 1119 */         tarray[i][j] = amat.matrix[j][i];
/*      */       }
/*      */     }
/* 1122 */     return tmat;
/*      */   }
/*      */   
/*      */ 
/*      */   public Matrix opposite()
/*      */   {
/* 1128 */     Matrix opp = copy(this);
/* 1129 */     for (int i = 0; i < this.numberOfRows; i++) {
/* 1130 */       for (int j = 0; j < this.numberOfColumns; j++) {
/* 1131 */         opp.matrix[i][j] = (-this.matrix[i][j]);
/*      */       }
/*      */     }
/* 1134 */     return opp;
/*      */   }
/*      */   
/*      */   public static Matrix opposite(Matrix amat)
/*      */   {
/* 1139 */     Matrix opp = copy(amat);
/* 1140 */     for (int i = 0; i < amat.numberOfRows; i++) {
/* 1141 */       for (int j = 0; j < amat.numberOfColumns; j++) {
/* 1142 */         opp.matrix[i][j] = (-amat.matrix[i][j]);
/*      */       }
/*      */     }
/* 1145 */     return opp;
/*      */   }
/*      */   
/*      */ 
/*      */   public double trace()
/*      */   {
/* 1151 */     double trac = 0.0D;
/* 1152 */     for (int i = 0; i < Math.min(this.numberOfColumns, this.numberOfColumns); i++) {
/* 1153 */       trac += this.matrix[i][i];
/*      */     }
/* 1155 */     return trac;
/*      */   }
/*      */   
/*      */   public static double trace(Matrix amat)
/*      */   {
/* 1160 */     double trac = 0.0D;
/* 1161 */     for (int i = 0; i < Math.min(amat.numberOfColumns, amat.numberOfColumns); i++) {
/* 1162 */       trac += amat.matrix[i][i];
/*      */     }
/* 1164 */     return trac;
/*      */   }
/*      */   
/*      */ 
/*      */   public double determinant()
/*      */   {
/* 1170 */     int n = this.numberOfRows;
/* 1171 */     if (n != this.numberOfColumns) throw new IllegalArgumentException("Matrix is not square");
/* 1172 */     double det = 0.0D;
/* 1173 */     Matrix ludmat = luDecomp();
/*      */     
/* 1175 */     det = ludmat.rowSwapIndex;
/* 1176 */     for (int j = 0; j < n; j++) {
/* 1177 */       det *= ludmat.matrix[j][j];
/*      */     }
/* 1179 */     return det;
/*      */   }
/*      */   
/*      */   public static double determinant(Matrix amat)
/*      */   {
/* 1184 */     int n = amat.numberOfRows;
/* 1185 */     if (n != amat.numberOfColumns) throw new IllegalArgumentException("Matrix is not square");
/* 1186 */     double det = 0.0D;
/* 1187 */     Matrix ludmat = amat.luDecomp();
/*      */     
/* 1189 */     det = ludmat.rowSwapIndex;
/* 1190 */     for (int j = 0; j < n; j++) {
/* 1191 */       det *= ludmat.matrix[j][j];
/*      */     }
/* 1193 */     return det;
/*      */   }
/*      */   
/*      */ 
/*      */   public double logDeterminant()
/*      */   {
/* 1199 */     int n = this.numberOfRows;
/* 1200 */     if (n != this.numberOfColumns) throw new IllegalArgumentException("Matrix is not square");
/* 1201 */     double det = 0.0D;
/* 1202 */     Matrix ludmat = luDecomp();
/*      */     
/* 1204 */     det = ludmat.rowSwapIndex;
/* 1205 */     det = Math.log(det);
/* 1206 */     for (int j = 0; j < n; j++) {
/* 1207 */       det += Math.log(ludmat.matrix[j][j]);
/*      */     }
/* 1209 */     return det;
/*      */   }
/*      */   
/*      */ 
/*      */   public static double logDeterminant(Matrix amat)
/*      */   {
/* 1215 */     int n = amat.numberOfRows;
/* 1216 */     if (n != amat.numberOfColumns) throw new IllegalArgumentException("Matrix is not square");
/* 1217 */     double det = 0.0D;
/* 1218 */     Matrix ludmat = amat.luDecomp();
/*      */     
/* 1220 */     det = ludmat.rowSwapIndex;
/* 1221 */     det = Math.log(det);
/* 1222 */     for (int j = 0; j < n; j++) {
/* 1223 */       det += Math.log(ludmat.matrix[j][j]);
/*      */     }
/* 1225 */     return det;
/*      */   }
/*      */   
/*      */ 
/*      */   public double frobeniusNorm()
/*      */   {
/* 1231 */     double norm = 0.0D;
/* 1232 */     for (int i = 0; i < this.numberOfRows; i++) {
/* 1233 */       for (int j = 0; j < this.numberOfColumns; j++) {
/* 1234 */         norm = hypot(norm, Math.abs(this.matrix[i][j]));
/*      */       }
/*      */     }
/* 1237 */     return norm;
/*      */   }
/*      */   
/*      */   public double oneNorm()
/*      */   {
/* 1242 */     double norm = 0.0D;
/* 1243 */     double sum = 0.0D;
/* 1244 */     for (int i = 0; i < this.numberOfRows; i++) {
/* 1245 */       sum = 0.0D;
/* 1246 */       for (int j = 0; j < this.numberOfColumns; j++) {
/* 1247 */         sum += Math.abs(this.matrix[i][j]);
/*      */       }
/* 1249 */       norm = Math.max(norm, sum);
/*      */     }
/* 1251 */     return norm;
/*      */   }
/*      */   
/*      */   public double infinityNorm()
/*      */   {
/* 1256 */     double norm = 0.0D;
/* 1257 */     double sum = 0.0D;
/* 1258 */     for (int i = 0; i < this.numberOfRows; i++) {
/* 1259 */       sum = 0.0D;
/* 1260 */       for (int j = 0; j < this.numberOfColumns; j++) {
/* 1261 */         sum += Math.abs(this.matrix[i][j]);
/*      */       }
/* 1263 */       norm = Math.max(norm, sum);
/*      */     }
/* 1265 */     return norm;
/*      */   }
/*      */   
/*      */ 
/*      */   public double sum()
/*      */   {
/* 1271 */     double sum = 0.0D;
/* 1272 */     for (int i = 0; i < this.numberOfRows; i++) {
/* 1273 */       for (int j = 0; j < this.numberOfColumns; j++) {
/* 1274 */         sum += this.matrix[i][j];
/*      */       }
/*      */     }
/* 1277 */     return sum;
/*      */   }
/*      */   
/*      */   public double[] rowSums()
/*      */   {
/* 1282 */     double[] sums = new double[this.numberOfRows];
/* 1283 */     for (int i = 0; i < this.numberOfRows; i++) {
/* 1284 */       sums[i] = 0.0D;
/* 1285 */       for (int j = 0; j < this.numberOfColumns; j++) {
/* 1286 */         sums[i] += this.matrix[i][j];
/*      */       }
/*      */     }
/* 1289 */     return sums;
/*      */   }
/*      */   
/*      */   public double[] columnSums()
/*      */   {
/* 1294 */     double[] sums = new double[this.numberOfColumns];
/* 1295 */     for (int i = 0; i < this.numberOfColumns; i++) {
/* 1296 */       sums[i] = 0.0D;
/* 1297 */       for (int j = 0; j < this.numberOfRows; j++) {
/* 1298 */         sums[i] += this.matrix[j][i];
/*      */       }
/*      */     }
/* 1301 */     return sums;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */   public double mean()
/*      */   {
/* 1309 */     double mean = 0.0D;
/* 1310 */     for (int i = 0; i < this.numberOfRows; i++) {
/* 1311 */       for (int j = 0; j < this.numberOfColumns; j++) {
/* 1312 */         mean += this.matrix[i][j];
/*      */       }
/*      */     }
/* 1315 */     mean /= this.numberOfRows * this.numberOfColumns;
/* 1316 */     return mean;
/*      */   }
/*      */   
/*      */   public double[] rowMeans()
/*      */   {
/* 1321 */     double[] means = new double[this.numberOfRows];
/* 1322 */     for (int i = 0; i < this.numberOfRows; i++) {
/* 1323 */       means[i] = 0.0D;
/* 1324 */       for (int j = 0; j < this.numberOfColumns; j++) {
/* 1325 */         means[i] += this.matrix[i][j];
/*      */       }
/* 1327 */       means[i] /= this.numberOfColumns;
/*      */     }
/* 1329 */     return means;
/*      */   }
/*      */   
/*      */   public double[] columnMeans()
/*      */   {
/* 1334 */     double[] means = new double[this.numberOfColumns];
/* 1335 */     for (int i = 0; i < this.numberOfColumns; i++) {
/* 1336 */       means[i] = 0.0D;
/* 1337 */       for (int j = 0; j < this.numberOfRows; j++) {
/* 1338 */         means[i] += this.matrix[j][i];
/*      */       }
/* 1340 */       means[i] /= this.numberOfRows;
/*      */     }
/* 1342 */     return means;
/*      */   }
/*      */   
/*      */ 
/*      */   public Matrix subtractMean()
/*      */   {
/* 1348 */     Matrix mat = new Matrix(this.numberOfRows, this.numberOfColumns);
/*      */     
/* 1350 */     double mean = 0.0D;
/* 1351 */     for (int i = 0; i < this.numberOfRows; i++) {
/* 1352 */       for (int j = 0; j < this.numberOfColumns; j++) {
/* 1353 */         mean += this.matrix[i][j];
/*      */       }
/*      */     }
/* 1356 */     mean /= this.numberOfRows * this.numberOfColumns;
/* 1357 */     for (int i = 0; i < this.numberOfRows; i++) {
/* 1358 */       for (int j = 0; j < this.numberOfColumns; j++) {
/* 1359 */         this.matrix[i][j] -= mean;
/*      */       }
/*      */     }
/* 1362 */     return mat;
/*      */   }
/*      */   
/*      */   public Matrix subtractRowMeans()
/*      */   {
/* 1367 */     Matrix mat = new Matrix(this.numberOfRows, this.numberOfColumns);
/*      */     
/* 1369 */     for (int i = 0; i < this.numberOfRows; i++) {
/* 1370 */       double mean = 0.0D;
/* 1371 */       for (int j = 0; j < this.numberOfColumns; j++) {
/* 1372 */         mean += this.matrix[i][j];
/*      */       }
/* 1374 */       mean /= this.numberOfColumns;
/* 1375 */       for (int j = 0; j < this.numberOfColumns; j++) {
/* 1376 */         this.matrix[i][j] -= mean;
/*      */       }
/*      */     }
/* 1379 */     return mat;
/*      */   }
/*      */   
/*      */   public Matrix subtractColumnMeans()
/*      */   {
/* 1384 */     Matrix mat = new Matrix(this.numberOfRows, this.numberOfColumns);
/*      */     
/* 1386 */     for (int i = 0; i < this.numberOfColumns; i++) {
/* 1387 */       double mean = 0.0D;
/* 1388 */       for (int j = 0; j < this.numberOfRows; j++) {
/* 1389 */         mean += this.matrix[j][i];
/*      */       }
/* 1391 */       mean /= this.numberOfRows;
/* 1392 */       for (int j = 0; j < this.numberOfRows; j++) {
/* 1393 */         mat.matrix[j][i] = (this.matrix[j][i] = mean);
/*      */       }
/*      */     }
/* 1396 */     return mat;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */   public double median()
/*      */   {
/* 1404 */     Stat st = new Stat(this.matrix[0]);
/*      */     
/* 1406 */     for (int i = 1; i < this.numberOfRows; i++) {
/* 1407 */       st.concatenate(this.matrix[i]);
/*      */     }
/*      */     
/* 1410 */     return st.median();
/*      */   }
/*      */   
/*      */   public double[] rowMedians()
/*      */   {
/* 1415 */     double[] medians = new double[this.numberOfRows];
/* 1416 */     for (int i = 0; i < this.numberOfRows; i++) {
/* 1417 */       Stat st = new Stat(this.matrix[i]);
/* 1418 */       medians[i] = st.median();
/*      */     }
/*      */     
/* 1421 */     return medians;
/*      */   }
/*      */   
/*      */   public double[] columnMedians()
/*      */   {
/* 1426 */     double[] medians = new double[this.numberOfRows];
/* 1427 */     for (int i = 0; i < this.numberOfColumns; i++) {
/* 1428 */       double[] hold = new double[this.numberOfRows];
/* 1429 */       for (int j = 0; j < this.numberOfRows; j++) {
/* 1430 */         hold[i] = this.matrix[j][i];
/*      */       }
/* 1432 */       Stat st = new Stat(hold);
/* 1433 */       medians[i] = st.median();
/*      */     }
/*      */     
/* 1436 */     return medians;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */   public void setDenominatorToN() {}
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */   public double variance()
/*      */   {
/* 1449 */     Stat st = new Stat(this.matrix[0]);
/*      */     
/* 1451 */     for (int i = 1; i < this.numberOfRows; i++) {
/* 1452 */       st.concatenate(this.matrix[i]);
/*      */     }
/*      */     
/* 1455 */     return st.variance();
/*      */   }
/*      */   
/*      */   public double[] rowVariances()
/*      */   {
/* 1460 */     double[] variances = new double[this.numberOfRows];
/* 1461 */     for (int i = 0; i < this.numberOfRows; i++) {
/* 1462 */       Stat st = new Stat(this.matrix[i]);
/* 1463 */       variances[i] = st.variance();
/*      */     }
/*      */     
/* 1466 */     return variances;
/*      */   }
/*      */   
/*      */   public double[] columnVariances()
/*      */   {
/* 1471 */     double[] variances = new double[this.numberOfColumns];
/* 1472 */     for (int i = 0; i < this.numberOfColumns; i++) {
/* 1473 */       double[] hold = new double[this.numberOfRows];
/* 1474 */       for (int j = 0; j < this.numberOfRows; j++) {
/* 1475 */         hold[i] = this.matrix[j][i];
/*      */       }
/* 1477 */       Stat st = new Stat(hold);
/* 1478 */       variances[i] = st.variance();
/*      */     }
/*      */     
/* 1481 */     return variances;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */   public double standardDeviation()
/*      */   {
/* 1489 */     Stat st = new Stat(this.matrix[0]);
/*      */     
/* 1491 */     for (int i = 1; i < this.numberOfRows; i++) {
/* 1492 */       st.concatenate(this.matrix[i]);
/*      */     }
/*      */     
/* 1495 */     return st.standardDeviation();
/*      */   }
/*      */   
/*      */   public double[] rowStandardDeviations()
/*      */   {
/* 1500 */     double[] standardDeviations = new double[this.numberOfRows];
/* 1501 */     for (int i = 0; i < this.numberOfRows; i++) {
/* 1502 */       Stat st = new Stat(this.matrix[i]);
/* 1503 */       standardDeviations[i] = st.standardDeviation();
/*      */     }
/*      */     
/* 1506 */     return standardDeviations;
/*      */   }
/*      */   
/*      */   public double[] columnStandardDeviations()
/*      */   {
/* 1511 */     double[] standardDeviations = new double[this.numberOfColumns];
/* 1512 */     for (int i = 0; i < this.numberOfColumns; i++) {
/* 1513 */       double[] hold = new double[this.numberOfRows];
/* 1514 */       for (int j = 0; j < this.numberOfRows; j++) {
/* 1515 */         hold[i] = this.matrix[j][i];
/*      */       }
/* 1517 */       Stat st = new Stat(hold);
/* 1518 */       standardDeviations[i] = st.standardDeviation();
/*      */     }
/*      */     
/* 1521 */     return standardDeviations;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */   public double stanadardError()
/*      */   {
/* 1528 */     Stat st = new Stat(this.matrix[0]);
/*      */     
/* 1530 */     for (int i = 1; i < this.numberOfRows; i++) {
/* 1531 */       st.concatenate(this.matrix[i]);
/*      */     }
/*      */     
/* 1534 */     return st.standardError();
/*      */   }
/*      */   
/*      */   public double[] rowStandardErrors()
/*      */   {
/* 1539 */     double[] standardErrors = new double[this.numberOfRows];
/* 1540 */     for (int i = 0; i < this.numberOfRows; i++) {
/* 1541 */       Stat st = new Stat(this.matrix[i]);
/* 1542 */       standardErrors[i] = st.standardError();
/*      */     }
/*      */     
/* 1545 */     return standardErrors;
/*      */   }
/*      */   
/*      */   public double[] columnStandardErrors()
/*      */   {
/* 1550 */     double[] standardErrors = new double[this.numberOfRows];
/* 1551 */     for (int i = 0; i < this.numberOfColumns; i++) {
/* 1552 */       double[] hold = new double[this.numberOfRows];
/* 1553 */       for (int j = 0; j < this.numberOfRows; j++) {
/* 1554 */         hold[i] = this.matrix[j][i];
/*      */       }
/* 1556 */       Stat st = new Stat(hold);
/* 1557 */       standardErrors[i] = st.standardError();
/*      */     }
/*      */     
/* 1560 */     return standardErrors;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */   public double[] maximumElement()
/*      */   {
/* 1568 */     double[] ret = new double[3];
/* 1569 */     double[] holdD = new double[this.numberOfRows];
/* 1570 */     ArrayMaths am = null;
/* 1571 */     int[] holdI = new int[this.numberOfRows];
/* 1572 */     for (int i = 0; i < this.numberOfRows; i++) {
/* 1573 */       am = new ArrayMaths(this.matrix[i]);
/* 1574 */       holdD[i] = am.maximum();
/* 1575 */       holdI[i] = am.maximumIndex();
/*      */     }
/* 1577 */     am = new ArrayMaths(holdD);
/* 1578 */     ret[0] = am.maximum();
/* 1579 */     int maxI = am.maximumIndex();
/* 1580 */     ret[1] = maxI;
/* 1581 */     ret[2] = holdI[maxI];
/*      */     
/* 1583 */     return ret;
/*      */   }
/*      */   
/*      */   public double[] rowMaxima()
/*      */   {
/* 1588 */     double[] maxima = new double[this.numberOfRows];
/* 1589 */     for (int i = 0; i < this.numberOfRows; i++) {
/* 1590 */       Stat st = new Stat(this.matrix[i]);
/* 1591 */       maxima[i] = st.maximum();
/*      */     }
/*      */     
/* 1594 */     return maxima;
/*      */   }
/*      */   
/*      */   public double[] columnMaxima()
/*      */   {
/* 1599 */     double[] maxima = new double[this.numberOfRows];
/* 1600 */     for (int i = 0; i < this.numberOfColumns; i++) {
/* 1601 */       double[] hold = new double[this.numberOfRows];
/* 1602 */       for (int j = 0; j < this.numberOfRows; j++) {
/* 1603 */         hold[i] = this.matrix[j][i];
/*      */       }
/* 1605 */       Stat st = new Stat(hold);
/* 1606 */       maxima[i] = st.maximum();
/*      */     }
/*      */     
/* 1609 */     return maxima;
/*      */   }
/*      */   
/*      */ 
/*      */   public double[] minimumElement()
/*      */   {
/* 1615 */     double[] ret = new double[3];
/* 1616 */     double[] holdD = new double[this.numberOfRows];
/* 1617 */     ArrayMaths am = null;
/* 1618 */     int[] holdI = new int[this.numberOfRows];
/* 1619 */     for (int i = 0; i < this.numberOfRows; i++) {
/* 1620 */       am = new ArrayMaths(this.matrix[i]);
/* 1621 */       holdD[i] = am.minimum();
/* 1622 */       holdI[i] = am.minimumIndex();
/*      */     }
/* 1624 */     am = new ArrayMaths(holdD);
/* 1625 */     ret[0] = am.minimum();
/* 1626 */     int minI = am.minimumIndex();
/* 1627 */     ret[1] = minI;
/* 1628 */     ret[2] = holdI[minI];
/*      */     
/* 1630 */     return ret;
/*      */   }
/*      */   
/*      */   public double[] rowMinima()
/*      */   {
/* 1635 */     double[] minima = new double[this.numberOfRows];
/* 1636 */     for (int i = 0; i < this.numberOfRows; i++) {
/* 1637 */       Stat st = new Stat(this.matrix[i]);
/* 1638 */       minima[i] = st.minimum();
/*      */     }
/*      */     
/* 1641 */     return minima;
/*      */   }
/*      */   
/*      */   public double[] columnMinima()
/*      */   {
/* 1646 */     double[] minima = new double[this.numberOfRows];
/* 1647 */     for (int i = 0; i < this.numberOfColumns; i++) {
/* 1648 */       double[] hold = new double[this.numberOfRows];
/* 1649 */       for (int j = 0; j < this.numberOfRows; j++) {
/* 1650 */         hold[i] = this.matrix[j][i];
/*      */       }
/* 1652 */       Stat st = new Stat(hold);
/* 1653 */       minima[i] = st.minimum();
/*      */     }
/*      */     
/* 1656 */     return minima;
/*      */   }
/*      */   
/*      */ 
/*      */   public double range()
/*      */   {
/* 1662 */     return maximumElement()[0] - minimumElement()[0];
/*      */   }
/*      */   
/*      */   public double[] rowRanges()
/*      */   {
/* 1667 */     double[] ranges = new double[this.numberOfRows];
/* 1668 */     for (int i = 0; i < this.numberOfRows; i++) {
/* 1669 */       Stat st = new Stat(this.matrix[i]);
/* 1670 */       ranges[i] = (st.maximum() - st.minimum());
/*      */     }
/*      */     
/* 1673 */     return ranges;
/*      */   }
/*      */   
/*      */   public double[] columnRanges()
/*      */   {
/* 1678 */     double[] ranges = new double[this.numberOfRows];
/* 1679 */     for (int i = 0; i < this.numberOfColumns; i++) {
/* 1680 */       double[] hold = new double[this.numberOfRows];
/* 1681 */       for (int j = 0; j < this.numberOfRows; j++) {
/* 1682 */         hold[i] = this.matrix[j][i];
/*      */       }
/* 1684 */       Stat st = new Stat(hold);
/* 1685 */       ranges[i] = (st.maximum() - st.minimum());
/*      */     }
/*      */     
/* 1688 */     return ranges;
/*      */   }
/*      */   
/*      */ 
/*      */   public int[] pivot()
/*      */   {
/* 1694 */     double[] max = maximumElement();
/* 1695 */     int maxI = (int)max[1];
/* 1696 */     int maxJ = (int)max[2];
/* 1697 */     double[] min = minimumElement();
/* 1698 */     int minI = (int)min[1];
/* 1699 */     int minJ = (int)min[2];
/* 1700 */     if (Math.abs(min[0]) > Math.abs(max[0])) {
/* 1701 */       maxI = minI;
/* 1702 */       maxJ = minJ;
/*      */     }
/* 1704 */     int[] ret = { maxI, maxJ };
/*      */     
/* 1706 */     double[] hold1 = this.matrix[0];
/* 1707 */     this.matrix[0] = this.matrix[maxI];
/* 1708 */     this.matrix[maxI] = hold1;
/* 1709 */     double hold2 = 0.0D;
/* 1710 */     for (int i = 0; i < this.numberOfRows; i++) {
/* 1711 */       hold2 = this.matrix[i][0];
/* 1712 */       this.matrix[i][0] = this.matrix[i][maxJ];
/* 1713 */       this.matrix[i][maxJ] = hold2;
/*      */     }
/*      */     
/* 1716 */     return ret;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */   public boolean isSquare()
/*      */   {
/* 1723 */     boolean test = false;
/* 1724 */     if (this.numberOfRows == this.numberOfColumns) test = true;
/* 1725 */     return test;
/*      */   }
/*      */   
/*      */   public boolean isSymmetric()
/*      */   {
/* 1730 */     boolean test = true;
/* 1731 */     if (this.numberOfRows == this.numberOfColumns) {
/* 1732 */       for (int i = 0; i < this.numberOfRows; i++) {
/* 1733 */         for (int j = i + 1; j < this.numberOfColumns; j++) {
/* 1734 */           if (this.matrix[i][j] != this.matrix[j][i]) { test = false;
/*      */           }
/*      */         }
/*      */       }
/*      */     } else {
/* 1739 */       test = false;
/*      */     }
/* 1741 */     return test;
/*      */   }
/*      */   
/*      */   public boolean isZero()
/*      */   {
/* 1746 */     boolean test = true;
/* 1747 */     for (int i = 0; i < this.numberOfRows; i++) {
/* 1748 */       for (int j = 0; j < this.numberOfColumns; j++) {
/* 1749 */         if (this.matrix[i][j] != 0.0D) test = false;
/*      */       }
/*      */     }
/* 1752 */     return test;
/*      */   }
/*      */   
/*      */   public boolean isUnit()
/*      */   {
/* 1757 */     boolean test = true;
/* 1758 */     for (int i = 0; i < this.numberOfRows; i++) {
/* 1759 */       for (int j = 0; j < this.numberOfColumns; j++) {
/* 1760 */         if (this.matrix[i][j] != 1.0D) test = false;
/*      */       }
/*      */     }
/* 1763 */     return test;
/*      */   }
/*      */   
/*      */   public boolean isDiagonal()
/*      */   {
/* 1768 */     boolean test = true;
/* 1769 */     for (int i = 0; i < this.numberOfRows; i++) {
/* 1770 */       for (int j = 0; j < this.numberOfColumns; j++) {
/* 1771 */         if ((i != j) && (this.matrix[i][j] != 0.0D)) test = false;
/*      */       }
/*      */     }
/* 1774 */     return test;
/*      */   }
/*      */   
/*      */   public boolean isUpperTriagonal()
/*      */   {
/* 1779 */     boolean test = true;
/* 1780 */     for (int i = 0; i < this.numberOfRows; i++) {
/* 1781 */       for (int j = 0; j < this.numberOfColumns; j++) {
/* 1782 */         if ((j < i) && (this.matrix[i][j] != 0.0D)) test = false;
/*      */       }
/*      */     }
/* 1785 */     return test;
/*      */   }
/*      */   
/*      */   public boolean isLowerTriagonal()
/*      */   {
/* 1790 */     boolean test = true;
/* 1791 */     for (int i = 0; i < this.numberOfRows; i++) {
/* 1792 */       for (int j = 0; j < this.numberOfColumns; j++) {
/* 1793 */         if ((i > j) && (this.matrix[i][j] != 0.0D)) test = false;
/*      */       }
/*      */     }
/* 1796 */     return test;
/*      */   }
/*      */   
/*      */   public boolean isTridiagonal()
/*      */   {
/* 1801 */     boolean test = true;
/* 1802 */     for (int i = 0; i < this.numberOfRows; i++) {
/* 1803 */       for (int j = 0; j < this.numberOfColumns; j++) {
/* 1804 */         if ((i < j + 1) && (this.matrix[i][j] != 0.0D)) test = false;
/* 1805 */         if ((j > i + 1) && (this.matrix[i][j] != 0.0D)) test = false;
/*      */       }
/*      */     }
/* 1808 */     return test;
/*      */   }
/*      */   
/*      */   public boolean isUpperHessenberg()
/*      */   {
/* 1813 */     boolean test = true;
/* 1814 */     for (int i = 0; i < this.numberOfRows; i++) {
/* 1815 */       for (int j = 0; j < this.numberOfColumns; j++) {
/* 1816 */         if ((j < i + 1) && (this.matrix[i][j] != 0.0D)) test = false;
/*      */       }
/*      */     }
/* 1819 */     return test;
/*      */   }
/*      */   
/*      */   public boolean isLowerHessenberg()
/*      */   {
/* 1824 */     boolean test = true;
/* 1825 */     for (int i = 0; i < this.numberOfRows; i++) {
/* 1826 */       for (int j = 0; j < this.numberOfColumns; j++) {
/* 1827 */         if ((i > j + 1) && (this.matrix[i][j] != 0.0D)) test = false;
/*      */       }
/*      */     }
/* 1830 */     return test;
/*      */   }
/*      */   
/*      */   public boolean isIdentity()
/*      */   {
/* 1835 */     boolean test = true;
/* 1836 */     if (this.numberOfRows == this.numberOfColumns) {
/* 1837 */       for (int i = 0; i < this.numberOfRows; i++) {
/* 1838 */         if (this.matrix[i][i] != 1.0D) test = false;
/* 1839 */         for (int j = i + 1; j < this.numberOfColumns; j++) {
/* 1840 */           if (this.matrix[i][j] != 0.0D) test = false;
/* 1841 */           if (this.matrix[j][i] != 0.0D) { test = false;
/*      */           }
/*      */         }
/*      */       }
/*      */     } else {
/* 1846 */       test = false;
/*      */     }
/* 1848 */     return test;
/*      */   }
/*      */   
/*      */   public boolean isNearlySymmetric(double tolerance)
/*      */   {
/* 1853 */     boolean test = true;
/* 1854 */     if (this.numberOfRows == this.numberOfColumns) {
/* 1855 */       for (int i = 0; i < this.numberOfRows; i++) {
/* 1856 */         for (int j = i + 1; j < this.numberOfColumns; j++) {
/* 1857 */           if (Math.abs(this.matrix[i][j] - this.matrix[j][i]) > Math.abs(tolerance)) { test = false;
/*      */           }
/*      */         }
/*      */       }
/*      */     } else {
/* 1862 */       test = false;
/*      */     }
/* 1864 */     return test;
/*      */   }
/*      */   
/*      */   public boolean isNearlyZero(double tolerance)
/*      */   {
/* 1869 */     boolean test = true;
/* 1870 */     for (int i = 0; i < this.numberOfRows; i++) {
/* 1871 */       for (int j = 0; j < this.numberOfColumns; j++) {
/* 1872 */         if (Math.abs(this.matrix[i][j]) > Math.abs(tolerance)) test = false;
/*      */       }
/*      */     }
/* 1875 */     return test;
/*      */   }
/*      */   
/*      */   public boolean isNearlyUnit(double tolerance)
/*      */   {
/* 1880 */     boolean test = true;
/* 1881 */     for (int i = 0; i < this.numberOfRows; i++) {
/* 1882 */       for (int j = 0; j < this.numberOfColumns; j++) {
/* 1883 */         if (Math.abs(this.matrix[i][j] - 1.0D) > Math.abs(tolerance)) test = false;
/*      */       }
/*      */     }
/* 1886 */     return test;
/*      */   }
/*      */   
/*      */ 
/*      */   public boolean isNearlyUpperTriagonal(double tolerance)
/*      */   {
/* 1892 */     boolean test = true;
/* 1893 */     for (int i = 0; i < this.numberOfRows; i++) {
/* 1894 */       for (int j = 0; j < this.numberOfColumns; j++) {
/* 1895 */         if ((j < i) && (this.matrix[i][j] > Math.abs(tolerance))) test = false;
/*      */       }
/*      */     }
/* 1898 */     return test;
/*      */   }
/*      */   
/*      */   public boolean isNearlyLowerTriagonal(double tolerance)
/*      */   {
/* 1903 */     boolean test = true;
/* 1904 */     for (int i = 0; i < this.numberOfRows; i++) {
/* 1905 */       for (int j = 0; j < this.numberOfColumns; j++) {
/* 1906 */         if ((i > j) && (this.matrix[i][j] > Math.abs(tolerance))) test = false;
/*      */       }
/*      */     }
/* 1909 */     return test;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */   public boolean isNearlyIdenty(double tolerance)
/*      */   {
/* 1916 */     boolean test = true;
/* 1917 */     if (this.numberOfRows == this.numberOfColumns) {
/* 1918 */       for (int i = 0; i < this.numberOfRows; i++) {
/* 1919 */         if (Math.abs(this.matrix[i][i] - 1.0D) > Math.abs(tolerance)) test = false;
/* 1920 */         for (int j = i + 1; j < this.numberOfColumns; j++) {
/* 1921 */           if (this.matrix[i][j] > Math.abs(tolerance)) test = false;
/* 1922 */           if (this.matrix[j][i] > Math.abs(tolerance)) { test = false;
/*      */           }
/*      */         }
/*      */       }
/*      */     } else {
/* 1927 */       test = false;
/*      */     }
/* 1929 */     return test;
/*      */   }
/*      */   
/*      */   public boolean isTridiagonal(double tolerance)
/*      */   {
/* 1934 */     boolean test = true;
/* 1935 */     for (int i = 0; i < this.numberOfRows; i++) {
/* 1936 */       for (int j = 0; j < this.numberOfColumns; j++) {
/* 1937 */         if ((i < j + 1) && (this.matrix[i][j] > Math.abs(tolerance))) test = false;
/* 1938 */         if ((j > i + 1) && (this.matrix[i][j] > Math.abs(tolerance))) test = false;
/*      */       }
/*      */     }
/* 1941 */     return test;
/*      */   }
/*      */   
/*      */   public boolean isNearlyUpperHessenberg(double tolerance)
/*      */   {
/* 1946 */     boolean test = true;
/* 1947 */     for (int i = 0; i < this.numberOfRows; i++) {
/* 1948 */       for (int j = 0; j < this.numberOfColumns; j++) {
/* 1949 */         if ((j < i + 1) && (this.matrix[i][j] > Math.abs(tolerance))) test = false;
/*      */       }
/*      */     }
/* 1952 */     return test;
/*      */   }
/*      */   
/*      */   public boolean isNearlyLowerHessenberg(double tolerance)
/*      */   {
/* 1957 */     boolean test = true;
/* 1958 */     for (int i = 0; i < this.numberOfRows; i++) {
/* 1959 */       for (int j = 0; j < this.numberOfColumns; j++) {
/* 1960 */         if ((i > j + 1) && (this.matrix[i][j] > Math.abs(tolerance))) test = false;
/*      */       }
/*      */     }
/* 1963 */     return test;
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
/*      */   public Matrix luDecomp()
/*      */   {
/* 1977 */     if (this.numberOfRows != this.numberOfColumns) throw new IllegalArgumentException("A matrix is not square");
/* 1978 */     int n = this.numberOfRows;
/* 1979 */     int imax = 0;
/* 1980 */     double dum = 0.0D;double temp = 0.0D;double big = 0.0D;
/* 1981 */     double[] vv = new double[n];
/* 1982 */     double sum = 0.0D;
/* 1983 */     double dumm = 0.0D;
/*      */     
/* 1985 */     this.matrixCheck = true;
/*      */     
/* 1987 */     Matrix ludmat = copy(this);
/* 1988 */     double[][] ludarray = ludmat.getArrayReference();
/*      */     
/* 1990 */     ludmat.rowSwapIndex = 1.0D;
/* 1991 */     for (int i = 0; i < n; i++) {
/* 1992 */       big = 0.0D;
/* 1993 */       for (int j = 0; j < n; j++) if ((temp = Math.abs(ludarray[i][j])) > big) big = temp;
/* 1994 */       if (big == 0.0D) {
/* 1995 */         if (!this.supressErrorMessage) {
/* 1996 */           System.out.println("Attempted LU Decomposition of a singular matrix in Matrix.luDecomp()");
/* 1997 */           System.out.println("NaN matrix returned and matrixCheck set to false");
/*      */         }
/* 1999 */         this.matrixCheck = false;
/* 2000 */         for (int k = 0; k < n; k++) for (int j = 0; j < n; j++) ludarray[k][j] = NaN.0D;
/* 2001 */         return ludmat;
/*      */       }
/* 2003 */       vv[i] = (1.0D / big);
/*      */     }
/* 2005 */     for (int j = 0; j < n; j++) {
/* 2006 */       for (int i = 0; i < j; i++) {
/* 2007 */         sum = ludarray[i][j];
/* 2008 */         for (int k = 0; k < i; k++) sum -= ludarray[i][k] * ludarray[k][j];
/* 2009 */         ludarray[i][j] = sum;
/*      */       }
/* 2011 */       big = 0.0D;
/* 2012 */       for (int i = j; i < n; i++) {
/* 2013 */         sum = ludarray[i][j];
/* 2014 */         for (int k = 0; k < j; k++)
/* 2015 */           sum -= ludarray[i][k] * ludarray[k][j];
/* 2016 */         ludarray[i][j] = sum;
/* 2017 */         if ((dum = vv[i] * Math.abs(sum)) >= big) {
/* 2018 */           big = dum;
/* 2019 */           imax = i;
/*      */         }
/*      */       }
/* 2022 */       if (j != imax) {
/* 2023 */         for (int k = 0; k < n; k++) {
/* 2024 */           dumm = ludarray[imax][k];
/* 2025 */           ludarray[imax][k] = ludarray[j][k];
/* 2026 */           ludarray[j][k] = dumm;
/*      */         }
/* 2028 */         ludmat.rowSwapIndex = (-ludmat.rowSwapIndex);
/* 2029 */         vv[imax] = vv[j];
/*      */       }
/* 2031 */       ludmat.permutationIndex[j] = imax;
/*      */       
/* 2033 */       if (ludarray[j][j] == 0.0D) {
/* 2034 */         ludarray[j][j] = this.tiny;
/*      */       }
/* 2036 */       if (j != n - 1) {
/* 2037 */         dumm = 1.0D / ludarray[j][j];
/* 2038 */         for (int i = j + 1; i < n; i++) {
/* 2039 */           ludarray[i][j] *= dumm;
/*      */         }
/*      */       }
/*      */     }
/* 2043 */     return ludmat;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */   public double[] luBackSub(double[] bvec)
/*      */   {
/* 2051 */     int ii = 0;int ip = 0;
/* 2052 */     int n = bvec.length;
/* 2053 */     if (n != this.numberOfColumns) throw new IllegalArgumentException("vector length is not equal to matrix dimension");
/* 2054 */     if (this.numberOfColumns != this.numberOfRows) throw new IllegalArgumentException("matrix is not square");
/* 2055 */     double sum = 0.0D;
/* 2056 */     double[] xvec = new double[n];
/* 2057 */     for (int i = 0; i < n; i++) {
/* 2058 */       xvec[i] = bvec[i];
/*      */     }
/* 2060 */     for (int i = 0; i < n; i++) {
/* 2061 */       ip = this.permutationIndex[i];
/* 2062 */       sum = xvec[ip];
/* 2063 */       xvec[ip] = xvec[i];
/* 2064 */       if (ii == 0) {
/* 2065 */         for (int j = ii; j <= i - 1; j++) {
/* 2066 */           sum -= this.matrix[i][j] * xvec[j];
/*      */         }
/*      */         
/*      */       }
/* 2070 */       else if (sum == 0.0D) { ii = i;
/*      */       }
/* 2072 */       xvec[i] = sum;
/*      */     }
/* 2074 */     for (int i = n - 1; i >= 0; i--) {
/* 2075 */       sum = xvec[i];
/* 2076 */       for (int j = i + 1; j < n; j++) {
/* 2077 */         sum -= this.matrix[i][j] * xvec[j];
/*      */       }
/* 2079 */       xvec[i] = (sum / this.matrix[i][i]);
/*      */     }
/* 2081 */     return xvec;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */   public double[] solveLinearSet(double[] bvec)
/*      */   {
/* 2088 */     double[] xvec = null;
/* 2089 */     if (this.numberOfRows == this.numberOfColumns)
/*      */     {
/* 2091 */       Matrix ludmat = luDecomp();
/* 2092 */       xvec = ludmat.luBackSub(bvec);
/*      */ 
/*      */     }
/* 2095 */     else if (this.numberOfRows > this.numberOfColumns)
/*      */     {
/* 2097 */       int n = bvec.length;
/* 2098 */       if (this.numberOfRows != n) throw new IllegalArgumentException("Overdetermined equation solution - vector length is not equal to matrix column length");
/* 2099 */       Matrix avecT = transpose();
/* 2100 */       double[][] avec = avecT.getArrayCopy();
/* 2101 */       Regression reg = new Regression(avec, bvec);
/* 2102 */       reg.linearGeneral();
/* 2103 */       xvec = reg.getCoeff();
/*      */     }
/*      */     else {
/* 2106 */       throw new IllegalArgumentException("This class does not handle underdetermined equations");
/*      */     }
/*      */     
/* 2109 */     return xvec;
/*      */   }
/*      */   
/*      */   public void supressErrorMessage()
/*      */   {
/* 2114 */     this.supressErrorMessage = true;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public void hessenbergMatrix()
/*      */   {
/* 2123 */     this.hessenberg = getArrayCopy();
/* 2124 */     double pivot = 0.0D;
/* 2125 */     int pivotIndex = 0;
/* 2126 */     double hold = 0.0D;
/*      */     
/* 2128 */     for (int i = 1; i < this.numberOfRows - 1; i++)
/*      */     {
/* 2130 */       pivot = 0.0D;
/* 2131 */       pivotIndex = i;
/* 2132 */       for (int j = i; j < this.numberOfRows; j++) {
/* 2133 */         if (Math.abs(this.hessenberg[j][(i - 1)]) > Math.abs(pivot)) {
/* 2134 */           pivot = this.hessenberg[j][(i - 1)];
/* 2135 */           pivotIndex = j;
/*      */         }
/*      */       }
/*      */       
/*      */ 
/* 2140 */       if (pivotIndex != i) {
/* 2141 */         for (int j = i - 1; j < this.numberOfRows; j++) {
/* 2142 */           hold = this.hessenberg[pivotIndex][j];
/* 2143 */           this.hessenberg[pivotIndex][j] = this.hessenberg[i][j];
/* 2144 */           this.hessenberg[i][j] = hold;
/*      */         }
/* 2146 */         for (int j = 0; j < this.numberOfRows; j++) {
/* 2147 */           hold = this.hessenberg[j][pivotIndex];
/* 2148 */           this.hessenberg[j][pivotIndex] = this.hessenberg[j][i];
/* 2149 */           this.hessenberg[j][i] = hold;
/*      */         }
/*      */         
/*      */ 
/* 2153 */         if (pivot != 0.0D) {
/* 2154 */           for (int j = i + 1; j < this.numberOfRows; j++) {
/* 2155 */             hold = this.hessenberg[j][(i - 1)];
/* 2156 */             if (hold != 0.0D) {
/* 2157 */               hold /= pivot;
/* 2158 */               this.hessenberg[j][(i - 1)] = hold;
/* 2159 */               for (int k = i; k < this.numberOfRows; k++) {
/* 2160 */                 this.hessenberg[j][k] -= hold * this.hessenberg[i][k];
/*      */               }
/* 2162 */               for (int k = 0; k < this.numberOfRows; k++) {
/* 2163 */                 this.hessenberg[k][i] += hold * this.hessenberg[k][j];
/*      */               }
/*      */             }
/*      */           }
/*      */         }
/*      */       }
/*      */     }
/* 2170 */     for (int i = 2; i < this.numberOfRows; i++) {
/* 2171 */       for (int j = 0; j < i - 1; j++) {
/* 2172 */         this.hessenberg[i][j] = 0.0D;
/*      */       }
/*      */     }
/* 2175 */     this.hessenbergDone = true;
/*      */   }
/*      */   
/*      */   public double[][] getHessenbergMatrix()
/*      */   {
/* 2180 */     if (!this.hessenbergDone) hessenbergMatrix();
/* 2181 */     return this.hessenberg;
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
/*      */   public double[] getEigenValues()
/*      */   {
/* 2194 */     if (!this.eigenDone) symmetricEigen();
/* 2195 */     return this.eigenValues;
/*      */   }
/*      */   
/*      */   public double[] getSortedEigenValues()
/*      */   {
/* 2200 */     if (!this.eigenDone) symmetricEigen();
/* 2201 */     return this.sortedEigenValues;
/*      */   }
/*      */   
/*      */ 
/*      */   public double[][] getEigenVectorsAsColumns()
/*      */   {
/* 2207 */     if (!this.eigenDone) symmetricEigen();
/* 2208 */     return this.eigenVector;
/*      */   }
/*      */   
/*      */   public double[][] getEigenVector()
/*      */   {
/* 2213 */     if (!this.eigenDone) symmetricEigen();
/* 2214 */     return this.eigenVector;
/*      */   }
/*      */   
/*      */ 
/*      */   public double[][] getEigenVectorsAsRows()
/*      */   {
/* 2220 */     if (!this.eigenDone) symmetricEigen();
/* 2221 */     double[][] ret = new double[this.numberOfRows][this.numberOfRows];
/* 2222 */     for (int i = 0; i < this.numberOfRows; i++) {
/* 2223 */       for (int j = 0; j < this.numberOfRows; j++) {
/* 2224 */         ret[i][j] = this.eigenVector[j][i];
/*      */       }
/*      */     }
/* 2227 */     return ret;
/*      */   }
/*      */   
/*      */ 
/*      */   public double[][] getSortedEigenVectorsAsColumns()
/*      */   {
/* 2233 */     if (!this.eigenDone) symmetricEigen();
/* 2234 */     return this.sortedEigenVector;
/*      */   }
/*      */   
/*      */ 
/*      */   public double[][] getSortedEigenVector()
/*      */   {
/* 2240 */     if (!this.eigenDone) symmetricEigen();
/* 2241 */     return this.sortedEigenVector;
/*      */   }
/*      */   
/*      */ 
/*      */   public double[][] getSortedEigenVectorsAsRows()
/*      */   {
/* 2247 */     if (!this.eigenDone) symmetricEigen();
/* 2248 */     double[][] ret = new double[this.numberOfRows][this.numberOfRows];
/* 2249 */     for (int i = 0; i < this.numberOfRows; i++) {
/* 2250 */       for (int j = 0; j < this.numberOfRows; j++) {
/* 2251 */         ret[i][j] = this.sortedEigenVector[j][i];
/*      */       }
/*      */     }
/* 2254 */     return ret;
/*      */   }
/*      */   
/*      */   public int getNumberOfJacobiRotations()
/*      */   {
/* 2259 */     return this.numberOfRotations;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */   private void symmetricEigen()
/*      */   {
/* 2266 */     if (!isSymmetric()) throw new IllegalArgumentException("matrix is not symmetric");
/* 2267 */     double[][] amat = getArrayCopy();
/* 2268 */     this.eigenVector = new double[this.numberOfRows][this.numberOfRows];
/* 2269 */     this.eigenValues = new double[this.numberOfRows];
/* 2270 */     double threshold = 0.0D;
/* 2271 */     double cot2rotationAngle = 0.0D;
/* 2272 */     double tanHalfRotationAngle = 0.0D;
/* 2273 */     double offDiagonalSum = 0.0D;
/* 2274 */     double scaledOffDiagonal = 0.0D;
/* 2275 */     double sElement = 0.0D;
/* 2276 */     double cElement = 0.0D;
/* 2277 */     double sOverC = 0.0D;
/* 2278 */     double vectorDifference = 0.0D;
/* 2279 */     double[] holdingVector1 = new double[this.numberOfRows];
/* 2280 */     double[] holdingVector2 = new double[this.numberOfRows];
/*      */     
/* 2282 */     for (int p = 0; p < this.numberOfRows; p++) {
/* 2283 */       for (int q = 0; q < this.numberOfRows; q++) this.eigenVector[p][q] = 0.0D;
/* 2284 */       this.eigenVector[p][p] = 1.0D;
/*      */     }
/* 2286 */     for (int p = 0; p < this.numberOfRows; p++) {
/* 2287 */       holdingVector1[p] = amat[p][p];
/* 2288 */       this.eigenValues[p] = amat[p][p];
/* 2289 */       holdingVector2[p] = 0.0D;
/*      */     }
/* 2291 */     this.numberOfRotations = 0;
/* 2292 */     for (int i = 1; i <= this.maximumJacobiIterations; i++) {
/* 2293 */       offDiagonalSum = 0.0D;
/* 2294 */       for (int p = 0; p < this.numberOfRows - 1; p++) {
/* 2295 */         for (int q = p + 1; q < this.numberOfRows; q++) {
/* 2296 */           offDiagonalSum += Math.abs(amat[p][q]);
/*      */         }
/*      */       }
/* 2299 */       if (offDiagonalSum == 0.0D) {
/* 2300 */         this.eigenDone = true;
/* 2301 */         eigenSort();
/* 2302 */         return;
/*      */       }
/* 2304 */       if (i < 4) {
/* 2305 */         threshold = 0.2D * offDiagonalSum / (this.numberOfRows * this.numberOfRows);
/*      */       }
/*      */       else {
/* 2308 */         threshold = 0.0D;
/*      */       }
/* 2310 */       for (int p = 0; p < this.numberOfRows - 1; p++) {
/* 2311 */         for (int q = p + 1; q < this.numberOfRows; q++) {
/* 2312 */           scaledOffDiagonal = 100.0D * Math.abs(amat[p][q]);
/* 2313 */           if ((i > 4) && (Math.abs(this.eigenValues[p]) + scaledOffDiagonal == Math.abs(this.eigenValues[p])) && (Math.abs(this.eigenValues[q]) + scaledOffDiagonal == Math.abs(this.eigenValues[q]))) {
/* 2314 */             amat[p][q] = 0.0D;
/*      */           }
/* 2316 */           else if (Math.abs(amat[p][q]) > threshold) {
/* 2317 */             vectorDifference = this.eigenValues[q] - this.eigenValues[p];
/* 2318 */             if (Math.abs(vectorDifference) + scaledOffDiagonal == Math.abs(vectorDifference)) {
/* 2319 */               sOverC = amat[p][q] / vectorDifference;
/*      */             } else {
/* 2321 */               cot2rotationAngle = 0.5D * vectorDifference / amat[p][q];
/* 2322 */               sOverC = 1.0D / (Math.abs(cot2rotationAngle) + Math.sqrt(1.0D + cot2rotationAngle * cot2rotationAngle));
/* 2323 */               if (cot2rotationAngle < 0.0D) sOverC = -sOverC;
/*      */             }
/* 2325 */             cElement = 1.0D / Math.sqrt(1.0D + sOverC * sOverC);
/* 2326 */             sElement = sOverC * cElement;
/* 2327 */             tanHalfRotationAngle = sElement / (1.0D + cElement);
/* 2328 */             vectorDifference = sOverC * amat[p][q];
/* 2329 */             holdingVector2[p] -= vectorDifference;
/* 2330 */             holdingVector2[q] += vectorDifference;
/* 2331 */             this.eigenValues[p] -= vectorDifference;
/* 2332 */             this.eigenValues[q] += vectorDifference;
/* 2333 */             amat[p][q] = 0.0D;
/* 2334 */             for (int j = 0; j <= p - 1; j++) rotation(amat, tanHalfRotationAngle, sElement, j, p, j, q);
/* 2335 */             for (int j = p + 1; j <= q - 1; j++) rotation(amat, tanHalfRotationAngle, sElement, p, j, j, q);
/* 2336 */             for (int j = q + 1; j < this.numberOfRows; j++) rotation(amat, tanHalfRotationAngle, sElement, p, j, q, j);
/* 2337 */             for (int j = 0; j < this.numberOfRows; j++) rotation(this.eigenVector, tanHalfRotationAngle, sElement, j, p, j, q);
/* 2338 */             this.numberOfRotations += 1;
/*      */           }
/*      */         }
/*      */       }
/* 2342 */       for (int p = 0; p < this.numberOfRows; p++) {
/* 2343 */         holdingVector1[p] += holdingVector2[p];
/* 2344 */         this.eigenValues[p] = holdingVector1[p];
/* 2345 */         holdingVector2[p] = 0.0D;
/*      */       }
/*      */     }
/* 2348 */     System.out.println("Maximum iterations, " + this.maximumJacobiIterations + ", reached - values at this point returned");
/* 2349 */     this.eigenDone = true;
/* 2350 */     eigenSort();
/*      */   }
/*      */   
/*      */   private void rotation(double[][] a, double tau, double sElement, int i, int j, int k, int l)
/*      */   {
/* 2355 */     double aHold1 = a[i][j];
/* 2356 */     double aHold2 = a[k][l];
/* 2357 */     a[i][j] = (aHold1 - sElement * (aHold2 + aHold1 * tau));
/* 2358 */     a[k][l] = (aHold2 + sElement * (aHold1 - aHold2 * tau));
/*      */   }
/*      */   
/*      */ 
/*      */   private void eigenSort()
/*      */   {
/* 2364 */     int k = 0;
/*      */     
/* 2366 */     this.sortedEigenValues = ((double[])this.eigenValues.clone());
/* 2367 */     this.sortedEigenVector = ((double[][])this.eigenVector.clone());
/* 2368 */     this.eigenIndices = new int[this.numberOfRows];
/*      */     
/* 2370 */     for (int i = 0; i < this.numberOfRows - 1; i++) {
/* 2371 */       double holdingElement = this.sortedEigenValues[i];
/* 2372 */       k = i;
/* 2373 */       for (int j = i + 1; j < this.numberOfRows; j++) {
/* 2374 */         if (this.sortedEigenValues[j] >= holdingElement) {
/* 2375 */           holdingElement = this.sortedEigenValues[j];
/* 2376 */           k = j;
/*      */         }
/*      */       }
/* 2379 */       if (k != i) {
/* 2380 */         this.sortedEigenValues[k] = this.sortedEigenValues[i];
/* 2381 */         this.sortedEigenValues[i] = holdingElement;
/*      */         
/* 2383 */         for (int j = 0; j < this.numberOfRows; j++) {
/* 2384 */           holdingElement = this.sortedEigenVector[j][i];
/* 2385 */           this.sortedEigenVector[j][i] = this.sortedEigenVector[j][k];
/* 2386 */           this.sortedEigenVector[j][k] = holdingElement;
/*      */         }
/*      */       }
/*      */     }
/* 2390 */     this.eigenIndices = new int[this.numberOfRows];
/* 2391 */     for (int i = 0; i < this.numberOfRows; i++) {
/* 2392 */       boolean test = true;
/* 2393 */       int j = 0;
/* 2394 */       while (test) {
/* 2395 */         if (this.sortedEigenValues[i] == this.eigenValues[j]) {
/* 2396 */           this.eigenIndices[i] = j;
/* 2397 */           test = false;
/*      */         }
/*      */         else {
/* 2400 */           j++;
/*      */         }
/*      */       }
/*      */     }
/*      */   }
/*      */   
/*      */   public int[] eigenValueIndices()
/*      */   {
/* 2408 */     if (!this.eigenDone) symmetricEigen();
/* 2409 */     return this.eigenIndices;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */   private static double hypot(double aa, double bb)
/*      */   {
/* 2416 */     double cc = 0.0D;double ratio = 0.0D;
/* 2417 */     double amod = Math.abs(aa);
/* 2418 */     double bmod = Math.abs(bb);
/*      */     
/* 2420 */     if (amod == 0.0D) {
/* 2421 */       cc = bmod;
/*      */ 
/*      */     }
/* 2424 */     else if (bmod == 0.0D) {
/* 2425 */       cc = amod;
/*      */ 
/*      */     }
/* 2428 */     else if (amod <= bmod) {
/* 2429 */       ratio = amod / bmod;
/* 2430 */       cc = bmod * Math.sqrt(1.0D + ratio * ratio);
/*      */     }
/*      */     else {
/* 2433 */       ratio = bmod / amod;
/* 2434 */       cc = amod * Math.sqrt(1.0D + ratio * ratio);
/*      */     }
/*      */     
/*      */ 
/* 2438 */     return cc;
/*      */   }
/*      */ }


/* Location:              /home/menick/Scrivania/ProcessMining/CNMining/CNMining.jar!/flanagan/math/Matrix.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       0.7.1
 */