/*      */ package org.apache.commons.math.linear;
/*      */ 
/*      */ import java.io.Serializable;
/*      */ import java.math.BigDecimal;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ public class BigMatrixImpl
/*      */   implements BigMatrix, Serializable
/*      */ {
/*      */   private static final long serialVersionUID = -1011428905656140431L;
/*   56 */   private BigDecimal[][] data = (BigDecimal[][])null;
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*   61 */   private BigDecimal[][] lu = (BigDecimal[][])null;
/*      */   
/*      */ 
/*   64 */   private int[] permutation = null;
/*      */   
/*      */ 
/*   67 */   private int parity = 1;
/*      */   
/*      */ 
/*   70 */   private int roundingMode = 4;
/*      */   
/*      */ 
/*   73 */   private int scale = 64;
/*      */   
/*      */ 
/*   76 */   protected static BigDecimal TOO_SMALL = new BigDecimal(1.0E-11D);
/*      */   
/*      */ 
/*   79 */   static final BigDecimal ZERO = new BigDecimal(0);
/*      */   
/*   81 */   static final BigDecimal ONE = new BigDecimal(1);
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public BigMatrixImpl() {}
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public BigMatrixImpl(int rowDimension, int columnDimension)
/*      */   {
/*   98 */     if ((rowDimension <= 0) || (columnDimension <= 0)) {
/*   99 */       throw new IllegalArgumentException("row and column dimensions must be positive");
/*      */     }
/*      */     
/*  102 */     this.data = new BigDecimal[rowDimension][columnDimension];
/*  103 */     this.lu = ((BigDecimal[][])null);
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
/*      */   public BigMatrixImpl(BigDecimal[][] d)
/*      */   {
/*  118 */     copyIn(d);
/*  119 */     this.lu = ((BigDecimal[][])null);
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
/*      */   public BigMatrixImpl(double[][] d)
/*      */   {
/*  134 */     int nRows = d.length;
/*  135 */     if (nRows == 0) {
/*  136 */       throw new IllegalArgumentException("Matrix must have at least one row.");
/*      */     }
/*      */     
/*  139 */     int nCols = d[0].length;
/*  140 */     if (nCols == 0) {
/*  141 */       throw new IllegalArgumentException("Matrix must have at least one column.");
/*      */     }
/*      */     
/*  144 */     for (int row = 1; row < nRows; row++) {
/*  145 */       if (d[row].length != nCols) {
/*  146 */         throw new IllegalArgumentException("All input rows must have the same length.");
/*      */       }
/*      */     }
/*      */     
/*  150 */     copyIn(d);
/*  151 */     this.lu = ((BigDecimal[][])null);
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
/*      */   public BigMatrixImpl(String[][] d)
/*      */   {
/*  164 */     int nRows = d.length;
/*  165 */     if (nRows == 0) {
/*  166 */       throw new IllegalArgumentException("Matrix must have at least one row.");
/*      */     }
/*      */     
/*  169 */     int nCols = d[0].length;
/*  170 */     if (nCols == 0) {
/*  171 */       throw new IllegalArgumentException("Matrix must have at least one column.");
/*      */     }
/*      */     
/*  174 */     for (int row = 1; row < nRows; row++) {
/*  175 */       if (d[row].length != nCols) {
/*  176 */         throw new IllegalArgumentException("All input rows must have the same length.");
/*      */       }
/*      */     }
/*      */     
/*  180 */     copyIn(d);
/*  181 */     this.lu = ((BigDecimal[][])null);
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
/*      */   public BigMatrixImpl(BigDecimal[] v)
/*      */   {
/*  194 */     int nRows = v.length;
/*  195 */     this.data = new BigDecimal[nRows][1];
/*  196 */     for (int row = 0; row < nRows; row++) {
/*  197 */       this.data[row][0] = v[row];
/*      */     }
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public BigMatrix copy()
/*      */   {
/*  207 */     return new BigMatrixImpl(copyOut());
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public BigMatrix add(BigMatrix m)
/*      */     throws IllegalArgumentException
/*      */   {
/*  218 */     if ((getColumnDimension() != m.getColumnDimension()) || (getRowDimension() != m.getRowDimension()))
/*      */     {
/*  220 */       throw new IllegalArgumentException("matrix dimension mismatch");
/*      */     }
/*  222 */     int rowCount = getRowDimension();
/*  223 */     int columnCount = getColumnDimension();
/*  224 */     BigDecimal[][] outData = new BigDecimal[rowCount][columnCount];
/*  225 */     for (int row = 0; row < rowCount; row++) {
/*  226 */       for (int col = 0; col < columnCount; col++) {
/*  227 */         outData[row][col] = this.data[row][col].add(m.getEntry(row, col));
/*      */       }
/*      */     }
/*  230 */     return new BigMatrixImpl(outData);
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public BigMatrix subtract(BigMatrix m)
/*      */     throws IllegalArgumentException
/*      */   {
/*  241 */     if ((getColumnDimension() != m.getColumnDimension()) || (getRowDimension() != m.getRowDimension()))
/*      */     {
/*  243 */       throw new IllegalArgumentException("matrix dimension mismatch");
/*      */     }
/*  245 */     int rowCount = getRowDimension();
/*  246 */     int columnCount = getColumnDimension();
/*  247 */     BigDecimal[][] outData = new BigDecimal[rowCount][columnCount];
/*  248 */     for (int row = 0; row < rowCount; row++) {
/*  249 */       for (int col = 0; col < columnCount; col++) {
/*  250 */         outData[row][col] = this.data[row][col].subtract(m.getEntry(row, col));
/*      */       }
/*      */     }
/*  253 */     return new BigMatrixImpl(outData);
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public BigMatrix scalarAdd(BigDecimal d)
/*      */   {
/*  263 */     int rowCount = getRowDimension();
/*  264 */     int columnCount = getColumnDimension();
/*  265 */     BigDecimal[][] outData = new BigDecimal[rowCount][columnCount];
/*  266 */     for (int row = 0; row < rowCount; row++) {
/*  267 */       for (int col = 0; col < columnCount; col++) {
/*  268 */         outData[row][col] = this.data[row][col].add(d);
/*      */       }
/*      */     }
/*  271 */     return new BigMatrixImpl(outData);
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public BigMatrix scalarMultiply(BigDecimal d)
/*      */   {
/*  280 */     int rowCount = getRowDimension();
/*  281 */     int columnCount = getColumnDimension();
/*  282 */     BigDecimal[][] outData = new BigDecimal[rowCount][columnCount];
/*  283 */     for (int row = 0; row < rowCount; row++) {
/*  284 */       for (int col = 0; col < columnCount; col++) {
/*  285 */         outData[row][col] = this.data[row][col].multiply(d);
/*      */       }
/*      */     }
/*  288 */     return new BigMatrixImpl(outData);
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public BigMatrix multiply(BigMatrix m)
/*      */     throws IllegalArgumentException
/*      */   {
/*  299 */     if (getColumnDimension() != m.getRowDimension()) {
/*  300 */       throw new IllegalArgumentException("Matrices are not multiplication compatible.");
/*      */     }
/*  302 */     int nRows = getRowDimension();
/*  303 */     int nCols = m.getColumnDimension();
/*  304 */     int nSum = getColumnDimension();
/*  305 */     BigDecimal[][] outData = new BigDecimal[nRows][nCols];
/*  306 */     BigDecimal sum = ZERO;
/*  307 */     for (int row = 0; row < nRows; row++) {
/*  308 */       for (int col = 0; col < nCols; col++) {
/*  309 */         sum = ZERO;
/*  310 */         for (int i = 0; i < nSum; i++) {
/*  311 */           sum = sum.add(this.data[row][i].multiply(m.getEntry(i, col)));
/*      */         }
/*  313 */         outData[row][col] = sum;
/*      */       }
/*      */     }
/*  316 */     return new BigMatrixImpl(outData);
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public BigMatrix preMultiply(BigMatrix m)
/*      */     throws IllegalArgumentException
/*      */   {
/*  327 */     return m.multiply(this);
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public BigDecimal[][] getData()
/*      */   {
/*  338 */     return copyOut();
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public double[][] getDataAsDoubleArray()
/*      */   {
/*  350 */     int nRows = getRowDimension();
/*  351 */     int nCols = getColumnDimension();
/*  352 */     double[][] d = new double[nRows][nCols];
/*  353 */     for (int i = 0; i < nRows; i++) {
/*  354 */       for (int j = 0; j < nCols; j++) {
/*  355 */         d[i][j] = this.data[i][j].doubleValue();
/*      */       }
/*      */     }
/*  358 */     return d;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public BigDecimal[][] getDataRef()
/*      */   {
/*  369 */     return this.data;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public int getRoundingMode()
/*      */   {
/*  379 */     return this.roundingMode;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public void setRoundingMode(int roundingMode)
/*      */   {
/*  388 */     this.roundingMode = roundingMode;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public int getScale()
/*      */   {
/*  398 */     return this.scale;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public void setScale(int scale)
/*      */   {
/*  407 */     this.scale = scale;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public BigDecimal getNorm()
/*      */   {
/*  417 */     BigDecimal maxColSum = ZERO;
/*  418 */     for (int col = 0; col < getColumnDimension(); col++) {
/*  419 */       BigDecimal sum = ZERO;
/*  420 */       for (int row = 0; row < getRowDimension(); row++) {
/*  421 */         sum = sum.add(this.data[row][col].abs());
/*      */       }
/*  423 */       maxColSum = maxColSum.max(sum);
/*      */     }
/*  425 */     return maxColSum;
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
/*      */   public BigMatrix getSubMatrix(int startRow, int endRow, int startColumn, int endColumn)
/*      */     throws MatrixIndexException
/*      */   {
/*  442 */     if ((startRow < 0) || (startRow > endRow) || (endRow > this.data.length) || (startColumn < 0) || (startColumn > endColumn) || (endColumn > this.data[0].length))
/*      */     {
/*      */ 
/*  445 */       throw new MatrixIndexException("invalid row or column index selection");
/*      */     }
/*      */     
/*  448 */     BigMatrixImpl subMatrix = new BigMatrixImpl(endRow - startRow + 1, endColumn - startColumn + 1);
/*      */     
/*  450 */     BigDecimal[][] subMatrixData = subMatrix.getDataRef();
/*  451 */     for (int i = startRow; i <= endRow; i++) {
/*  452 */       for (int j = startColumn; j <= endColumn; j++) {
/*  453 */         subMatrixData[(i - startRow)][(j - startColumn)] = this.data[i][j];
/*      */       }
/*      */     }
/*  456 */     return subMatrix;
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
/*      */   public BigMatrix getSubMatrix(int[] selectedRows, int[] selectedColumns)
/*      */     throws MatrixIndexException
/*      */   {
/*  472 */     if (selectedRows.length * selectedColumns.length == 0) {
/*  473 */       throw new MatrixIndexException("selected row and column index arrays must be non-empty");
/*      */     }
/*      */     
/*  476 */     BigMatrixImpl subMatrix = new BigMatrixImpl(selectedRows.length, selectedColumns.length);
/*      */     
/*  478 */     BigDecimal[][] subMatrixData = subMatrix.getDataRef();
/*      */     try {
/*  480 */       for (int i = 0; i < selectedRows.length; i++) {
/*  481 */         for (int j = 0; j < selectedColumns.length; j++) {
/*  482 */           subMatrixData[i][j] = this.data[selectedRows[i]][selectedColumns[j]];
/*      */         }
/*      */       }
/*      */     }
/*      */     catch (ArrayIndexOutOfBoundsException e) {
/*  487 */       throw new MatrixIndexException("matrix dimension mismatch");
/*      */     }
/*  489 */     return subMatrix;
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
/*      */   public void setSubMatrix(BigDecimal[][] subMatrix, int row, int column)
/*      */     throws MatrixIndexException
/*      */   {
/*  521 */     if ((row < 0) || (column < 0)) {
/*  522 */       throw new MatrixIndexException("invalid row or column index selection");
/*      */     }
/*      */     
/*  525 */     int nRows = subMatrix.length;
/*  526 */     if (nRows == 0) {
/*  527 */       throw new IllegalArgumentException("Matrix must have at least one row.");
/*      */     }
/*      */     
/*  530 */     int nCols = subMatrix[0].length;
/*  531 */     if (nCols == 0) {
/*  532 */       throw new IllegalArgumentException("Matrix must have at least one column.");
/*      */     }
/*      */     
/*  535 */     for (int r = 1; r < nRows; r++) {
/*  536 */       if (subMatrix[r].length != nCols) {
/*  537 */         throw new IllegalArgumentException("All input rows must have the same length.");
/*      */       }
/*      */     }
/*      */     
/*  541 */     if (this.data == null) {
/*  542 */       if ((row > 0) || (column > 0)) { throw new MatrixIndexException("matrix must be initialized to perfom this method");
/*      */       }
/*  544 */       this.data = new BigDecimal[nRows][nCols];
/*  545 */       System.arraycopy(subMatrix, 0, this.data, 0, subMatrix.length);
/*      */     }
/*  547 */     if ((nRows + row > getRowDimension()) || (nCols + column > getColumnDimension()))
/*      */     {
/*  549 */       throw new MatrixIndexException("invalid row or column index selection");
/*      */     }
/*  551 */     for (int i = 0; i < nRows; i++) {
/*  552 */       System.arraycopy(subMatrix[i], 0, this.data[(row + i)], column, nCols);
/*      */     }
/*  554 */     this.lu = ((BigDecimal[][])null);
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public BigMatrix getRowMatrix(int row)
/*      */     throws MatrixIndexException
/*      */   {
/*  566 */     if (!isValidCoordinate(row, 0)) {
/*  567 */       throw new MatrixIndexException("illegal row argument");
/*      */     }
/*  569 */     int ncols = getColumnDimension();
/*  570 */     BigDecimal[][] out = new BigDecimal[1][ncols];
/*  571 */     System.arraycopy(this.data[row], 0, out[0], 0, ncols);
/*  572 */     return new BigMatrixImpl(out);
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public BigMatrix getColumnMatrix(int column)
/*      */     throws MatrixIndexException
/*      */   {
/*  584 */     if (!isValidCoordinate(0, column)) {
/*  585 */       throw new MatrixIndexException("illegal column argument");
/*      */     }
/*  587 */     int nRows = getRowDimension();
/*  588 */     BigDecimal[][] out = new BigDecimal[nRows][1];
/*  589 */     for (int row = 0; row < nRows; row++) {
/*  590 */       out[row][0] = this.data[row][column];
/*      */     }
/*  592 */     return new BigMatrixImpl(out);
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
/*      */   public BigDecimal[] getRow(int row)
/*      */     throws MatrixIndexException
/*      */   {
/*  606 */     if (!isValidCoordinate(row, 0)) {
/*  607 */       throw new MatrixIndexException("illegal row argument");
/*      */     }
/*  609 */     int ncols = getColumnDimension();
/*  610 */     BigDecimal[] out = new BigDecimal[ncols];
/*  611 */     System.arraycopy(this.data[row], 0, out, 0, ncols);
/*  612 */     return out;
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
/*      */   public double[] getRowAsDoubleArray(int row)
/*      */     throws MatrixIndexException
/*      */   {
/*  627 */     if (!isValidCoordinate(row, 0)) {
/*  628 */       throw new MatrixIndexException("illegal row argument");
/*      */     }
/*  630 */     int ncols = getColumnDimension();
/*  631 */     double[] out = new double[ncols];
/*  632 */     for (int i = 0; i < ncols; i++) {
/*  633 */       out[i] = this.data[row][i].doubleValue();
/*      */     }
/*  635 */     return out;
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
/*      */   public BigDecimal[] getColumn(int col)
/*      */     throws MatrixIndexException
/*      */   {
/*  649 */     if (!isValidCoordinate(0, col)) {
/*  650 */       throw new MatrixIndexException("illegal column argument");
/*      */     }
/*  652 */     int nRows = getRowDimension();
/*  653 */     BigDecimal[] out = new BigDecimal[nRows];
/*  654 */     for (int i = 0; i < nRows; i++) {
/*  655 */       out[i] = this.data[i][col];
/*      */     }
/*  657 */     return out;
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
/*      */   public double[] getColumnAsDoubleArray(int col)
/*      */     throws MatrixIndexException
/*      */   {
/*  672 */     if (!isValidCoordinate(0, col)) {
/*  673 */       throw new MatrixIndexException("illegal column argument");
/*      */     }
/*  675 */     int nrows = getRowDimension();
/*  676 */     double[] out = new double[nrows];
/*  677 */     for (int i = 0; i < nrows; i++) {
/*  678 */       out[i] = this.data[i][col].doubleValue();
/*      */     }
/*  680 */     return out;
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
/*      */   public BigDecimal getEntry(int row, int column)
/*      */     throws MatrixIndexException
/*      */   {
/*  700 */     if (!isValidCoordinate(row, column)) {
/*  701 */       throw new MatrixIndexException("matrix entry does not exist");
/*      */     }
/*  703 */     return this.data[row][column];
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
/*      */   public double getEntryAsDouble(int row, int column)
/*      */     throws MatrixIndexException
/*      */   {
/*  723 */     return getEntry(row, column).doubleValue();
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public BigMatrix transpose()
/*      */   {
/*  732 */     int nRows = getRowDimension();
/*  733 */     int nCols = getColumnDimension();
/*  734 */     BigMatrixImpl out = new BigMatrixImpl(nCols, nRows);
/*  735 */     BigDecimal[][] outData = out.getDataRef();
/*  736 */     for (int row = 0; row < nRows; row++) {
/*  737 */       for (int col = 0; col < nCols; col++) {
/*  738 */         outData[col][row] = this.data[row][col];
/*      */       }
/*      */     }
/*  741 */     return out;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public BigMatrix inverse()
/*      */     throws InvalidMatrixException
/*      */   {
/*  751 */     return solve(MatrixUtils.createBigIdentityMatrix(getRowDimension()));
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public BigDecimal getDeterminant()
/*      */     throws InvalidMatrixException
/*      */   {
/*  762 */     if (!isSquare()) {
/*  763 */       throw new InvalidMatrixException("matrix is not square");
/*      */     }
/*  765 */     if (isSingular()) {
/*  766 */       return ZERO;
/*      */     }
/*  768 */     BigDecimal det = this.parity == 1 ? ONE : ONE.negate();
/*  769 */     for (int i = 0; i < getRowDimension(); i++) {
/*  770 */       det = det.multiply(this.lu[i][i]);
/*      */     }
/*  772 */     return det;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public boolean isSquare()
/*      */   {
/*  781 */     return getColumnDimension() == getRowDimension();
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */   public boolean isSingular()
/*      */   {
/*  789 */     if (this.lu == null) {
/*      */       try {
/*  791 */         luDecompose();
/*  792 */         return false;
/*      */       } catch (InvalidMatrixException ex) {
/*  794 */         return true;
/*      */       }
/*      */     }
/*  797 */     return false;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public int getRowDimension()
/*      */   {
/*  807 */     return this.data.length;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public int getColumnDimension()
/*      */   {
/*  816 */     return this.data[0].length;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public BigDecimal getTrace()
/*      */     throws IllegalArgumentException
/*      */   {
/*  828 */     if (!isSquare()) {
/*  829 */       throw new IllegalArgumentException("matrix is not square");
/*      */     }
/*  831 */     BigDecimal trace = this.data[0][0];
/*  832 */     for (int i = 1; i < getRowDimension(); i++) {
/*  833 */       trace = trace.add(this.data[i][i]);
/*      */     }
/*  835 */     return trace;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public BigDecimal[] operate(BigDecimal[] v)
/*      */     throws IllegalArgumentException
/*      */   {
/*  846 */     if (v.length != getColumnDimension()) {
/*  847 */       throw new IllegalArgumentException("vector has wrong length");
/*      */     }
/*  849 */     int nRows = getRowDimension();
/*  850 */     int nCols = getColumnDimension();
/*  851 */     BigDecimal[] out = new BigDecimal[v.length];
/*  852 */     for (int row = 0; row < nRows; row++) {
/*  853 */       BigDecimal sum = ZERO;
/*  854 */       for (int i = 0; i < nCols; i++) {
/*  855 */         sum = sum.add(this.data[row][i].multiply(v[i]));
/*      */       }
/*  857 */       out[row] = sum;
/*      */     }
/*  859 */     return out;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public BigDecimal[] operate(double[] v)
/*      */     throws IllegalArgumentException
/*      */   {
/*  870 */     BigDecimal[] bd = new BigDecimal[v.length];
/*  871 */     for (int i = 0; i < bd.length; i++) {
/*  872 */       bd[i] = new BigDecimal(v[i]);
/*      */     }
/*  874 */     return operate(bd);
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public BigDecimal[] preMultiply(BigDecimal[] v)
/*      */     throws IllegalArgumentException
/*      */   {
/*  885 */     int nRows = getRowDimension();
/*  886 */     if (v.length != nRows) {
/*  887 */       throw new IllegalArgumentException("vector has wrong length");
/*      */     }
/*  889 */     int nCols = getColumnDimension();
/*  890 */     BigDecimal[] out = new BigDecimal[nCols];
/*  891 */     for (int col = 0; col < nCols; col++) {
/*  892 */       BigDecimal sum = ZERO;
/*  893 */       for (int i = 0; i < nRows; i++) {
/*  894 */         sum = sum.add(this.data[i][col].multiply(v[i]));
/*      */       }
/*  896 */       out[col] = sum;
/*      */     }
/*  898 */     return out;
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
/*      */   public BigDecimal[] solve(BigDecimal[] b)
/*      */     throws IllegalArgumentException, InvalidMatrixException
/*      */   {
/*  913 */     int nRows = getRowDimension();
/*  914 */     if (b.length != nRows) {
/*  915 */       throw new IllegalArgumentException("constant vector has wrong length");
/*      */     }
/*  917 */     BigMatrix bMatrix = new BigMatrixImpl(b);
/*  918 */     BigDecimal[][] solution = ((BigMatrixImpl)solve(bMatrix)).getDataRef();
/*  919 */     BigDecimal[] out = new BigDecimal[nRows];
/*  920 */     for (int row = 0; row < nRows; row++) {
/*  921 */       out[row] = solution[row][0];
/*      */     }
/*  923 */     return out;
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
/*      */   public BigDecimal[] solve(double[] b)
/*      */     throws IllegalArgumentException, InvalidMatrixException
/*      */   {
/*  938 */     BigDecimal[] bd = new BigDecimal[b.length];
/*  939 */     for (int i = 0; i < bd.length; i++) {
/*  940 */       bd[i] = new BigDecimal(b[i]);
/*      */     }
/*  942 */     return solve(bd);
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
/*      */   public BigMatrix solve(BigMatrix b)
/*      */     throws IllegalArgumentException, InvalidMatrixException
/*      */   {
/*  957 */     if (b.getRowDimension() != getRowDimension()) {
/*  958 */       throw new IllegalArgumentException("Incorrect row dimension");
/*      */     }
/*  960 */     if (!isSquare()) {
/*  961 */       throw new InvalidMatrixException("coefficient matrix is not square");
/*      */     }
/*  963 */     if (isSingular()) {
/*  964 */       throw new InvalidMatrixException("Matrix is singular.");
/*      */     }
/*      */     
/*  967 */     int nCol = getColumnDimension();
/*  968 */     int nColB = b.getColumnDimension();
/*  969 */     int nRowB = b.getRowDimension();
/*      */     
/*      */ 
/*  972 */     BigDecimal[][] bp = new BigDecimal[nRowB][nColB];
/*  973 */     for (int row = 0; row < nRowB; row++) {
/*  974 */       for (int col = 0; col < nColB; col++) {
/*  975 */         bp[row][col] = b.getEntry(this.permutation[row], col);
/*      */       }
/*      */     }
/*      */     
/*      */ 
/*  980 */     for (int col = 0; col < nCol; col++) {
/*  981 */       for (int i = col + 1; i < nCol; i++) {
/*  982 */         for (int j = 0; j < nColB; j++) {
/*  983 */           bp[i][j] = bp[i][j].subtract(bp[col][j].multiply(this.lu[i][col]));
/*      */         }
/*      */       }
/*      */     }
/*      */     
/*      */ 
/*  989 */     for (int col = nCol - 1; col >= 0; col--) {
/*  990 */       for (int j = 0; j < nColB; j++) {
/*  991 */         bp[col][j] = bp[col][j].divide(this.lu[col][col], this.scale, this.roundingMode);
/*      */       }
/*  993 */       for (int i = 0; i < col; i++) {
/*  994 */         for (int j = 0; j < nColB; j++) {
/*  995 */           bp[i][j] = bp[i][j].subtract(bp[col][j].multiply(this.lu[i][col]));
/*      */         }
/*      */       }
/*      */     }
/*      */     
/* 1000 */     BigMatrixImpl outMat = new BigMatrixImpl(bp);
/* 1001 */     return outMat;
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
/*      */   public void luDecompose()
/*      */     throws InvalidMatrixException
/*      */   {
/* 1024 */     int nRows = getRowDimension();
/* 1025 */     int nCols = getColumnDimension();
/* 1026 */     if (nRows != nCols) {
/* 1027 */       throw new InvalidMatrixException("LU decomposition requires that the matrix be square.");
/*      */     }
/* 1029 */     this.lu = getData();
/*      */     
/*      */ 
/* 1032 */     this.permutation = new int[nRows];
/* 1033 */     for (int row = 0; row < nRows; row++) {
/* 1034 */       this.permutation[row] = row;
/*      */     }
/* 1036 */     this.parity = 1;
/*      */     
/*      */ 
/* 1039 */     for (int col = 0; col < nCols; col++)
/*      */     {
/* 1041 */       BigDecimal sum = ZERO;
/*      */       
/*      */ 
/* 1044 */       for (int row = 0; row < col; row++) {
/* 1045 */         sum = this.lu[row][col];
/* 1046 */         for (int i = 0; i < row; i++) {
/* 1047 */           sum = sum.subtract(this.lu[row][i].multiply(this.lu[i][col]));
/*      */         }
/* 1049 */         this.lu[row][col] = sum;
/*      */       }
/*      */       
/*      */ 
/* 1053 */       int max = col;
/* 1054 */       BigDecimal largest = ZERO;
/* 1055 */       for (int row = col; row < nRows; row++) {
/* 1056 */         sum = this.lu[row][col];
/* 1057 */         for (int i = 0; i < col; i++) {
/* 1058 */           sum = sum.subtract(this.lu[row][i].multiply(this.lu[i][col]));
/*      */         }
/* 1060 */         this.lu[row][col] = sum;
/*      */         
/*      */ 
/* 1063 */         if (sum.abs().compareTo(largest) == 1) {
/* 1064 */           largest = sum.abs();
/* 1065 */           max = row;
/*      */         }
/*      */       }
/*      */       
/*      */ 
/* 1070 */       if (this.lu[max][col].abs().compareTo(TOO_SMALL) <= 0) {
/* 1071 */         this.lu = ((BigDecimal[][])null);
/* 1072 */         throw new InvalidMatrixException("matrix is singular");
/*      */       }
/*      */       
/*      */ 
/* 1076 */       if (max != col) {
/* 1077 */         BigDecimal tmp = ZERO;
/* 1078 */         for (int i = 0; i < nCols; i++) {
/* 1079 */           tmp = this.lu[max][i];
/* 1080 */           this.lu[max][i] = this.lu[col][i];
/* 1081 */           this.lu[col][i] = tmp;
/*      */         }
/* 1083 */         int temp = this.permutation[max];
/* 1084 */         this.permutation[max] = this.permutation[col];
/* 1085 */         this.permutation[col] = temp;
/* 1086 */         this.parity = (-this.parity);
/*      */       }
/*      */       
/*      */ 
/* 1090 */       for (int row = col + 1; row < nRows; row++) {
/* 1091 */         this.lu[row][col] = this.lu[row][col].divide(this.lu[col][col], this.scale, this.roundingMode);
/*      */       }
/*      */     }
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public String toString()
/*      */   {
/* 1103 */     StringBuffer res = new StringBuffer();
/* 1104 */     res.append("BigMatrixImpl{");
/* 1105 */     if (this.data != null) {
/* 1106 */       for (int i = 0; i < this.data.length; i++) {
/* 1107 */         if (i > 0)
/* 1108 */           res.append(",");
/* 1109 */         res.append("{");
/* 1110 */         for (int j = 0; j < this.data[0].length; j++) {
/* 1111 */           if (j > 0)
/* 1112 */             res.append(",");
/* 1113 */           res.append(this.data[i][j]);
/*      */         }
/* 1115 */         res.append("}");
/*      */       }
/*      */     }
/* 1118 */     res.append("}");
/* 1119 */     return res.toString();
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
/*      */   public boolean equals(Object object)
/*      */   {
/* 1132 */     if (object == this) {
/* 1133 */       return true;
/*      */     }
/* 1135 */     if (!(object instanceof BigMatrixImpl)) {
/* 1136 */       return false;
/*      */     }
/* 1138 */     BigMatrix m = (BigMatrix)object;
/* 1139 */     int nRows = getRowDimension();
/* 1140 */     int nCols = getColumnDimension();
/* 1141 */     if ((m.getColumnDimension() != nCols) || (m.getRowDimension() != nRows)) {
/* 1142 */       return false;
/*      */     }
/* 1144 */     for (int row = 0; row < nRows; row++) {
/* 1145 */       for (int col = 0; col < nCols; col++) {
/* 1146 */         if (!this.data[row][col].equals(m.getEntry(row, col))) {
/* 1147 */           return false;
/*      */         }
/*      */       }
/*      */     }
/* 1151 */     return true;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public int hashCode()
/*      */   {
/* 1160 */     int ret = 7;
/* 1161 */     int nRows = getRowDimension();
/* 1162 */     int nCols = getColumnDimension();
/* 1163 */     ret = ret * 31 + nRows;
/* 1164 */     ret = ret * 31 + nCols;
/* 1165 */     for (int row = 0; row < nRows; row++) {
/* 1166 */       for (int col = 0; col < nCols; col++) {
/* 1167 */         ret = ret * 31 + (11 * (row + 1) + 17 * (col + 1)) * this.data[row][col].hashCode();
/*      */       }
/*      */     }
/*      */     
/* 1171 */     return ret;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   /**
/*      */    * @deprecated
/*      */    */
/*      */   protected BigMatrix getIdentity(int dimension)
/*      */   {
/* 1185 */     return MatrixUtils.createBigIdentityMatrix(dimension);
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
/*      */   protected BigMatrix getLUMatrix()
/*      */     throws InvalidMatrixException
/*      */   {
/* 1216 */     if (this.lu == null) {
/* 1217 */       luDecompose();
/*      */     }
/* 1219 */     return new BigMatrixImpl(this.lu);
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
/*      */   protected int[] getPermutation()
/*      */   {
/* 1235 */     int[] out = new int[this.permutation.length];
/* 1236 */     System.arraycopy(this.permutation, 0, out, 0, this.permutation.length);
/* 1237 */     return out;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   private BigDecimal[][] copyOut()
/*      */   {
/* 1248 */     int nRows = getRowDimension();
/* 1249 */     BigDecimal[][] out = new BigDecimal[nRows][getColumnDimension()];
/*      */     
/* 1251 */     for (int i = 0; i < nRows; i++) {
/* 1252 */       System.arraycopy(this.data[i], 0, out[i], 0, this.data[i].length);
/*      */     }
/* 1254 */     return out;
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
/*      */   private void copyIn(BigDecimal[][] in)
/*      */   {
/* 1268 */     setSubMatrix(in, 0, 0);
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   private void copyIn(double[][] in)
/*      */   {
/* 1277 */     int nRows = in.length;
/* 1278 */     int nCols = in[0].length;
/* 1279 */     this.data = new BigDecimal[nRows][nCols];
/* 1280 */     for (int i = 0; i < nRows; i++) {
/* 1281 */       for (int j = 0; j < nCols; j++) {
/* 1282 */         this.data[i][j] = new BigDecimal(in[i][j]);
/*      */       }
/*      */     }
/* 1285 */     this.lu = ((BigDecimal[][])null);
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   private void copyIn(String[][] in)
/*      */   {
/* 1295 */     int nRows = in.length;
/* 1296 */     int nCols = in[0].length;
/* 1297 */     this.data = new BigDecimal[nRows][nCols];
/* 1298 */     for (int i = 0; i < nRows; i++) {
/* 1299 */       for (int j = 0; j < nCols; j++) {
/* 1300 */         this.data[i][j] = new BigDecimal(in[i][j]);
/*      */       }
/*      */     }
/* 1303 */     this.lu = ((BigDecimal[][])null);
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   private boolean isValidCoordinate(int row, int col)
/*      */   {
/* 1314 */     int nRows = getRowDimension();
/* 1315 */     int nCols = getColumnDimension();
/*      */     
/* 1317 */     return (row >= 0) && (row < nRows) && (col >= 0) && (col < nCols);
/*      */   }
/*      */ }


/* Location:              /home/menick/Scrivania/ProcessMining/CNMining/CNMining.jar!/org/apache/commons/math/linear/BigMatrixImpl.class
 * Java compiler version: 3 (47.0)
 * JD-Core Version:       0.7.1
 */