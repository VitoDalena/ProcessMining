/*      */ package org.apache.commons.math.linear;
/*      */ 
/*      */ import java.io.Serializable;
/*      */ import org.apache.commons.math.util.MathUtils;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ public class RealMatrixImpl
/*      */   implements RealMatrix, Serializable
/*      */ {
/*      */   private static final long serialVersionUID = 4237564493130426188L;
/*   58 */   private double[][] data = (double[][])null;
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*   63 */   private double[][] lu = (double[][])null;
/*      */   
/*      */ 
/*   66 */   private int[] permutation = null;
/*      */   
/*      */ 
/*   69 */   private int parity = 1;
/*      */   
/*      */ 
/*   72 */   protected static double TOO_SMALL = 1.0E-11D;
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public RealMatrixImpl() {}
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public RealMatrixImpl(int rowDimension, int columnDimension)
/*      */   {
/*   89 */     if ((rowDimension <= 0) || (columnDimension <= 0)) {
/*   90 */       throw new IllegalArgumentException("row and column dimensions must be postive");
/*      */     }
/*      */     
/*   93 */     this.data = new double[rowDimension][columnDimension];
/*   94 */     this.lu = ((double[][])null);
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
/*      */   public RealMatrixImpl(double[][] d)
/*      */   {
/*  109 */     copyIn(d);
/*  110 */     this.lu = ((double[][])null);
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
/*      */   public RealMatrixImpl(double[] v)
/*      */   {
/*  123 */     int nRows = v.length;
/*  124 */     this.data = new double[nRows][1];
/*  125 */     for (int row = 0; row < nRows; row++) {
/*  126 */       this.data[row][0] = v[row];
/*      */     }
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public RealMatrix copy()
/*      */   {
/*  136 */     return new RealMatrixImpl(copyOut());
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public RealMatrix add(RealMatrix m)
/*      */     throws IllegalArgumentException
/*      */   {
/*  147 */     if ((getColumnDimension() != m.getColumnDimension()) || (getRowDimension() != m.getRowDimension()))
/*      */     {
/*  149 */       throw new IllegalArgumentException("matrix dimension mismatch");
/*      */     }
/*  151 */     int rowCount = getRowDimension();
/*  152 */     int columnCount = getColumnDimension();
/*  153 */     double[][] outData = new double[rowCount][columnCount];
/*  154 */     for (int row = 0; row < rowCount; row++) {
/*  155 */       for (int col = 0; col < columnCount; col++) {
/*  156 */         outData[row][col] = (this.data[row][col] + m.getEntry(row, col));
/*      */       }
/*      */     }
/*  159 */     return new RealMatrixImpl(outData);
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public RealMatrix subtract(RealMatrix m)
/*      */     throws IllegalArgumentException
/*      */   {
/*  170 */     if ((getColumnDimension() != m.getColumnDimension()) || (getRowDimension() != m.getRowDimension()))
/*      */     {
/*  172 */       throw new IllegalArgumentException("matrix dimension mismatch");
/*      */     }
/*  174 */     int rowCount = getRowDimension();
/*  175 */     int columnCount = getColumnDimension();
/*  176 */     double[][] outData = new double[rowCount][columnCount];
/*  177 */     for (int row = 0; row < rowCount; row++) {
/*  178 */       for (int col = 0; col < columnCount; col++) {
/*  179 */         outData[row][col] = (this.data[row][col] - m.getEntry(row, col));
/*      */       }
/*      */     }
/*  182 */     return new RealMatrixImpl(outData);
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public RealMatrix scalarAdd(double d)
/*      */   {
/*  192 */     int rowCount = getRowDimension();
/*  193 */     int columnCount = getColumnDimension();
/*  194 */     double[][] outData = new double[rowCount][columnCount];
/*  195 */     for (int row = 0; row < rowCount; row++) {
/*  196 */       for (int col = 0; col < columnCount; col++) {
/*  197 */         outData[row][col] = (this.data[row][col] + d);
/*      */       }
/*      */     }
/*  200 */     return new RealMatrixImpl(outData);
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public RealMatrix scalarMultiply(double d)
/*      */   {
/*  209 */     int rowCount = getRowDimension();
/*  210 */     int columnCount = getColumnDimension();
/*  211 */     double[][] outData = new double[rowCount][columnCount];
/*  212 */     for (int row = 0; row < rowCount; row++) {
/*  213 */       for (int col = 0; col < columnCount; col++) {
/*  214 */         outData[row][col] = (this.data[row][col] * d);
/*      */       }
/*      */     }
/*  217 */     return new RealMatrixImpl(outData);
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public RealMatrix multiply(RealMatrix m)
/*      */     throws IllegalArgumentException
/*      */   {
/*  228 */     if (getColumnDimension() != m.getRowDimension()) {
/*  229 */       throw new IllegalArgumentException("Matrices are not multiplication compatible.");
/*      */     }
/*  231 */     int nRows = getRowDimension();
/*  232 */     int nCols = m.getColumnDimension();
/*  233 */     int nSum = getColumnDimension();
/*  234 */     double[][] outData = new double[nRows][nCols];
/*  235 */     double sum = 0.0D;
/*  236 */     for (int row = 0; row < nRows; row++) {
/*  237 */       for (int col = 0; col < nCols; col++) {
/*  238 */         sum = 0.0D;
/*  239 */         for (int i = 0; i < nSum; i++) {
/*  240 */           sum += this.data[row][i] * m.getEntry(i, col);
/*      */         }
/*  242 */         outData[row][col] = sum;
/*      */       }
/*      */     }
/*  245 */     return new RealMatrixImpl(outData);
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public RealMatrix preMultiply(RealMatrix m)
/*      */     throws IllegalArgumentException
/*      */   {
/*  256 */     return m.multiply(this);
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public double[][] getData()
/*      */   {
/*  267 */     return copyOut();
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public double[][] getDataRef()
/*      */   {
/*  278 */     return this.data;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */   public double getNorm()
/*      */   {
/*  286 */     double maxColSum = 0.0D;
/*  287 */     for (int col = 0; col < getColumnDimension(); col++) {
/*  288 */       double sum = 0.0D;
/*  289 */       for (int row = 0; row < getRowDimension(); row++) {
/*  290 */         sum += Math.abs(this.data[row][col]);
/*      */       }
/*  292 */       maxColSum = Math.max(maxColSum, sum);
/*      */     }
/*  294 */     return maxColSum;
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
/*      */   public RealMatrix getSubMatrix(int startRow, int endRow, int startColumn, int endColumn)
/*      */     throws MatrixIndexException
/*      */   {
/*  311 */     if ((startRow < 0) || (startRow > endRow) || (endRow > this.data.length) || (startColumn < 0) || (startColumn > endColumn) || (endColumn > this.data[0].length))
/*      */     {
/*      */ 
/*  314 */       throw new MatrixIndexException("invalid row or column index selection");
/*      */     }
/*      */     
/*  317 */     RealMatrixImpl subMatrix = new RealMatrixImpl(endRow - startRow + 1, endColumn - startColumn + 1);
/*      */     
/*  319 */     double[][] subMatrixData = subMatrix.getDataRef();
/*  320 */     for (int i = startRow; i <= endRow; i++) {
/*  321 */       for (int j = startColumn; j <= endColumn; j++) {
/*  322 */         subMatrixData[(i - startRow)][(j - startColumn)] = this.data[i][j];
/*      */       }
/*      */     }
/*  325 */     return subMatrix;
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
/*      */   public RealMatrix getSubMatrix(int[] selectedRows, int[] selectedColumns)
/*      */     throws MatrixIndexException
/*      */   {
/*  341 */     if (selectedRows.length * selectedColumns.length == 0) {
/*  342 */       throw new MatrixIndexException("selected row and column index arrays must be non-empty");
/*      */     }
/*      */     
/*  345 */     RealMatrixImpl subMatrix = new RealMatrixImpl(selectedRows.length, selectedColumns.length);
/*      */     
/*  347 */     double[][] subMatrixData = subMatrix.getDataRef();
/*      */     try {
/*  349 */       for (int i = 0; i < selectedRows.length; i++) {
/*  350 */         for (int j = 0; j < selectedColumns.length; j++) {
/*  351 */           subMatrixData[i][j] = this.data[selectedRows[i]][selectedColumns[j]];
/*      */         }
/*      */       }
/*      */     }
/*      */     catch (ArrayIndexOutOfBoundsException e) {
/*  356 */       throw new MatrixIndexException("matrix dimension mismatch");
/*      */     }
/*  358 */     return subMatrix;
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
/*      */   public void setSubMatrix(double[][] subMatrix, int row, int column)
/*      */     throws MatrixIndexException
/*      */   {
/*  390 */     if ((row < 0) || (column < 0)) {
/*  391 */       throw new MatrixIndexException("invalid row or column index selection");
/*      */     }
/*      */     
/*  394 */     int nRows = subMatrix.length;
/*  395 */     if (nRows == 0) {
/*  396 */       throw new IllegalArgumentException("Matrix must have at least one row.");
/*      */     }
/*      */     
/*  399 */     int nCols = subMatrix[0].length;
/*  400 */     if (nCols == 0) {
/*  401 */       throw new IllegalArgumentException("Matrix must have at least one column.");
/*      */     }
/*      */     
/*  404 */     for (int r = 1; r < nRows; r++) {
/*  405 */       if (subMatrix[r].length != nCols) {
/*  406 */         throw new IllegalArgumentException("All input rows must have the same length.");
/*      */       }
/*      */     }
/*      */     
/*  410 */     if (this.data == null) {
/*  411 */       if ((row > 0) || (column > 0)) { throw new MatrixIndexException("matrix must be initialized to perfom this method");
/*      */       }
/*  413 */       this.data = new double[nRows][nCols];
/*  414 */       System.arraycopy(subMatrix, 0, this.data, 0, subMatrix.length);
/*      */     }
/*  416 */     if ((nRows + row > getRowDimension()) || (nCols + column > getColumnDimension()))
/*      */     {
/*  418 */       throw new MatrixIndexException("invalid row or column index selection");
/*      */     }
/*  420 */     for (int i = 0; i < nRows; i++) {
/*  421 */       System.arraycopy(subMatrix[i], 0, this.data[(row + i)], column, nCols);
/*      */     }
/*  423 */     this.lu = ((double[][])null);
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public RealMatrix getRowMatrix(int row)
/*      */     throws MatrixIndexException
/*      */   {
/*  435 */     if (!isValidCoordinate(row, 0)) {
/*  436 */       throw new MatrixIndexException("illegal row argument");
/*      */     }
/*  438 */     int ncols = getColumnDimension();
/*  439 */     double[][] out = new double[1][ncols];
/*  440 */     System.arraycopy(this.data[row], 0, out[0], 0, ncols);
/*  441 */     return new RealMatrixImpl(out);
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public RealMatrix getColumnMatrix(int column)
/*      */     throws MatrixIndexException
/*      */   {
/*  453 */     if (!isValidCoordinate(0, column)) {
/*  454 */       throw new MatrixIndexException("illegal column argument");
/*      */     }
/*  456 */     int nRows = getRowDimension();
/*  457 */     double[][] out = new double[nRows][1];
/*  458 */     for (int row = 0; row < nRows; row++) {
/*  459 */       out[row][0] = this.data[row][column];
/*      */     }
/*  461 */     return new RealMatrixImpl(out);
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
/*      */   public double[] getRow(int row)
/*      */     throws MatrixIndexException
/*      */   {
/*  475 */     if (!isValidCoordinate(row, 0)) {
/*  476 */       throw new MatrixIndexException("illegal row argument");
/*      */     }
/*  478 */     int ncols = getColumnDimension();
/*  479 */     double[] out = new double[ncols];
/*  480 */     System.arraycopy(this.data[row], 0, out, 0, ncols);
/*  481 */     return out;
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
/*      */   public double[] getColumn(int col)
/*      */     throws MatrixIndexException
/*      */   {
/*  495 */     if (!isValidCoordinate(0, col)) {
/*  496 */       throw new MatrixIndexException("illegal column argument");
/*      */     }
/*  498 */     int nRows = getRowDimension();
/*  499 */     double[] out = new double[nRows];
/*  500 */     for (int row = 0; row < nRows; row++) {
/*  501 */       out[row] = this.data[row][col];
/*      */     }
/*  503 */     return out;
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
/*      */   public double getEntry(int row, int column)
/*      */     throws MatrixIndexException
/*      */   {
/*  523 */     if (!isValidCoordinate(row, column)) {
/*  524 */       throw new MatrixIndexException("matrix entry does not exist");
/*      */     }
/*  526 */     return this.data[row][column];
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public RealMatrix transpose()
/*      */   {
/*  535 */     int nRows = getRowDimension();
/*  536 */     int nCols = getColumnDimension();
/*  537 */     RealMatrixImpl out = new RealMatrixImpl(nCols, nRows);
/*  538 */     double[][] outData = out.getDataRef();
/*  539 */     for (int row = 0; row < nRows; row++) {
/*  540 */       for (int col = 0; col < nCols; col++) {
/*  541 */         outData[col][row] = this.data[row][col];
/*      */       }
/*      */     }
/*  544 */     return out;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public RealMatrix inverse()
/*      */     throws InvalidMatrixException
/*      */   {
/*  554 */     return solve(MatrixUtils.createRealIdentityMatrix(getRowDimension()));
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */   public double getDeterminant()
/*      */     throws InvalidMatrixException
/*      */   {
/*  563 */     if (!isSquare()) {
/*  564 */       throw new InvalidMatrixException("matrix is not square");
/*      */     }
/*  566 */     if (isSingular()) {
/*  567 */       return 0.0D;
/*      */     }
/*  569 */     double det = this.parity;
/*  570 */     for (int i = 0; i < getRowDimension(); i++) {
/*  571 */       det *= this.lu[i][i];
/*      */     }
/*  573 */     return det;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */   public boolean isSquare()
/*      */   {
/*  581 */     return getColumnDimension() == getRowDimension();
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */   public boolean isSingular()
/*      */   {
/*  588 */     if (this.lu == null) {
/*      */       try {
/*  590 */         luDecompose();
/*  591 */         return false;
/*      */       } catch (InvalidMatrixException ex) {
/*  593 */         return true;
/*      */       }
/*      */     }
/*  596 */     return false;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */   public int getRowDimension()
/*      */   {
/*  604 */     return this.data.length;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */   public int getColumnDimension()
/*      */   {
/*  611 */     return this.data[0].length;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */   public double getTrace()
/*      */     throws IllegalArgumentException
/*      */   {
/*  619 */     if (!isSquare()) {
/*  620 */       throw new IllegalArgumentException("matrix is not square");
/*      */     }
/*  622 */     double trace = this.data[0][0];
/*  623 */     for (int i = 1; i < getRowDimension(); i++) {
/*  624 */       trace += this.data[i][i];
/*      */     }
/*  626 */     return trace;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */   public double[] operate(double[] v)
/*      */     throws IllegalArgumentException
/*      */   {
/*  635 */     if (v.length != getColumnDimension()) {
/*  636 */       throw new IllegalArgumentException("vector has wrong length");
/*      */     }
/*  638 */     int nRows = getRowDimension();
/*  639 */     int nCols = getColumnDimension();
/*  640 */     double[] out = new double[v.length];
/*  641 */     for (int row = 0; row < nRows; row++) {
/*  642 */       double sum = 0.0D;
/*  643 */       for (int i = 0; i < nCols; i++) {
/*  644 */         sum += this.data[row][i] * v[i];
/*      */       }
/*  646 */       out[row] = sum;
/*      */     }
/*  648 */     return out;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */   public double[] preMultiply(double[] v)
/*      */     throws IllegalArgumentException
/*      */   {
/*  657 */     int nRows = getRowDimension();
/*  658 */     if (v.length != nRows) {
/*  659 */       throw new IllegalArgumentException("vector has wrong length");
/*      */     }
/*  661 */     int nCols = getColumnDimension();
/*  662 */     double[] out = new double[nCols];
/*  663 */     for (int col = 0; col < nCols; col++) {
/*  664 */       double sum = 0.0D;
/*  665 */       for (int i = 0; i < nRows; i++) {
/*  666 */         sum += this.data[i][col] * v[i];
/*      */       }
/*  668 */       out[col] = sum;
/*      */     }
/*  670 */     return out;
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
/*      */   public double[] solve(double[] b)
/*      */     throws IllegalArgumentException, InvalidMatrixException
/*      */   {
/*  685 */     int nRows = getRowDimension();
/*  686 */     if (b.length != nRows) {
/*  687 */       throw new IllegalArgumentException("constant vector has wrong length");
/*      */     }
/*  689 */     RealMatrix bMatrix = new RealMatrixImpl(b);
/*  690 */     double[][] solution = ((RealMatrixImpl)solve(bMatrix)).getDataRef();
/*  691 */     double[] out = new double[nRows];
/*  692 */     for (int row = 0; row < nRows; row++) {
/*  693 */       out[row] = solution[row][0];
/*      */     }
/*  695 */     return out;
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
/*      */   public RealMatrix solve(RealMatrix b)
/*      */     throws IllegalArgumentException, InvalidMatrixException
/*      */   {
/*  710 */     if (b.getRowDimension() != getRowDimension()) {
/*  711 */       throw new IllegalArgumentException("Incorrect row dimension");
/*      */     }
/*  713 */     if (!isSquare()) {
/*  714 */       throw new InvalidMatrixException("coefficient matrix is not square");
/*      */     }
/*  716 */     if (isSingular()) {
/*  717 */       throw new InvalidMatrixException("Matrix is singular.");
/*      */     }
/*      */     
/*  720 */     int nCol = getColumnDimension();
/*  721 */     int nColB = b.getColumnDimension();
/*  722 */     int nRowB = b.getRowDimension();
/*      */     
/*      */ 
/*  725 */     double[][] bp = new double[nRowB][nColB];
/*  726 */     for (int row = 0; row < nRowB; row++) {
/*  727 */       for (int col = 0; col < nColB; col++) {
/*  728 */         bp[row][col] = b.getEntry(this.permutation[row], col);
/*      */       }
/*      */     }
/*      */     
/*      */ 
/*  733 */     for (int col = 0; col < nCol; col++) {
/*  734 */       for (int i = col + 1; i < nCol; i++) {
/*  735 */         for (int j = 0; j < nColB; j++) {
/*  736 */           bp[i][j] -= bp[col][j] * this.lu[i][col];
/*      */         }
/*      */       }
/*      */     }
/*      */     
/*      */ 
/*  742 */     for (int col = nCol - 1; col >= 0; col--) {
/*  743 */       for (int j = 0; j < nColB; j++) {
/*  744 */         bp[col][j] /= this.lu[col][col];
/*      */       }
/*  746 */       for (int i = 0; i < col; i++) {
/*  747 */         for (int j = 0; j < nColB; j++) {
/*  748 */           bp[i][j] -= bp[col][j] * this.lu[i][col];
/*      */         }
/*      */       }
/*      */     }
/*      */     
/*  753 */     RealMatrixImpl outMat = new RealMatrixImpl(bp);
/*  754 */     return outMat;
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
/*  777 */     int nRows = getRowDimension();
/*  778 */     int nCols = getColumnDimension();
/*  779 */     if (nRows != nCols) {
/*  780 */       throw new InvalidMatrixException("LU decomposition requires that the matrix be square.");
/*      */     }
/*  782 */     this.lu = getData();
/*      */     
/*      */ 
/*  785 */     this.permutation = new int[nRows];
/*  786 */     for (int row = 0; row < nRows; row++) {
/*  787 */       this.permutation[row] = row;
/*      */     }
/*  789 */     this.parity = 1;
/*      */     
/*      */ 
/*  792 */     for (int col = 0; col < nCols; col++)
/*      */     {
/*  794 */       double sum = 0.0D;
/*      */       
/*      */ 
/*  797 */       for (int row = 0; row < col; row++) {
/*  798 */         sum = this.lu[row][col];
/*  799 */         for (int i = 0; i < row; i++) {
/*  800 */           sum -= this.lu[row][i] * this.lu[i][col];
/*      */         }
/*  802 */         this.lu[row][col] = sum;
/*      */       }
/*      */       
/*      */ 
/*  806 */       int max = col;
/*  807 */       double largest = 0.0D;
/*  808 */       for (int row = col; row < nRows; row++) {
/*  809 */         sum = this.lu[row][col];
/*  810 */         for (int i = 0; i < col; i++) {
/*  811 */           sum -= this.lu[row][i] * this.lu[i][col];
/*      */         }
/*  813 */         this.lu[row][col] = sum;
/*      */         
/*      */ 
/*  816 */         if (Math.abs(sum) > largest) {
/*  817 */           largest = Math.abs(sum);
/*  818 */           max = row;
/*      */         }
/*      */       }
/*      */       
/*      */ 
/*  823 */       if (Math.abs(this.lu[max][col]) < TOO_SMALL) {
/*  824 */         this.lu = ((double[][])null);
/*  825 */         throw new InvalidMatrixException("matrix is singular");
/*      */       }
/*      */       
/*      */ 
/*  829 */       if (max != col) {
/*  830 */         double tmp = 0.0D;
/*  831 */         for (int i = 0; i < nCols; i++) {
/*  832 */           tmp = this.lu[max][i];
/*  833 */           this.lu[max][i] = this.lu[col][i];
/*  834 */           this.lu[col][i] = tmp;
/*      */         }
/*  836 */         int temp = this.permutation[max];
/*  837 */         this.permutation[max] = this.permutation[col];
/*  838 */         this.permutation[col] = temp;
/*  839 */         this.parity = (-this.parity);
/*      */       }
/*      */       
/*      */ 
/*  843 */       for (int row = col + 1; row < nRows; row++) {
/*  844 */         this.lu[row][col] /= this.lu[col][col];
/*      */       }
/*      */     }
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */   public String toString()
/*      */   {
/*  854 */     StringBuffer res = new StringBuffer();
/*  855 */     res.append("RealMatrixImpl{");
/*  856 */     if (this.data != null) {
/*  857 */       for (int i = 0; i < this.data.length; i++) {
/*  858 */         if (i > 0)
/*  859 */           res.append(",");
/*  860 */         res.append("{");
/*  861 */         for (int j = 0; j < this.data[0].length; j++) {
/*  862 */           if (j > 0)
/*  863 */             res.append(",");
/*  864 */           res.append(this.data[i][j]);
/*      */         }
/*  866 */         res.append("}");
/*      */       }
/*      */     }
/*  869 */     res.append("}");
/*  870 */     return res.toString();
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
/*  883 */     if (object == this) {
/*  884 */       return true;
/*      */     }
/*  886 */     if (!(object instanceof RealMatrixImpl)) {
/*  887 */       return false;
/*      */     }
/*  889 */     RealMatrix m = (RealMatrix)object;
/*  890 */     int nRows = getRowDimension();
/*  891 */     int nCols = getColumnDimension();
/*  892 */     if ((m.getColumnDimension() != nCols) || (m.getRowDimension() != nRows)) {
/*  893 */       return false;
/*      */     }
/*  895 */     for (int row = 0; row < nRows; row++) {
/*  896 */       for (int col = 0; col < nCols; col++) {
/*  897 */         if (Double.doubleToLongBits(this.data[row][col]) != Double.doubleToLongBits(m.getEntry(row, col)))
/*      */         {
/*  899 */           return false;
/*      */         }
/*      */       }
/*      */     }
/*  903 */     return true;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public int hashCode()
/*      */   {
/*  912 */     int ret = 7;
/*  913 */     int nRows = getRowDimension();
/*  914 */     int nCols = getColumnDimension();
/*  915 */     ret = ret * 31 + nRows;
/*  916 */     ret = ret * 31 + nCols;
/*  917 */     for (int row = 0; row < nRows; row++) {
/*  918 */       for (int col = 0; col < nCols; col++) {
/*  919 */         ret = ret * 31 + (11 * (row + 1) + 17 * (col + 1)) * MathUtils.hash(this.data[row][col]);
/*      */       }
/*      */     }
/*      */     
/*  923 */     return ret;
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
/*      */   protected RealMatrix getIdentity(int dimension)
/*      */   {
/*  937 */     return MatrixUtils.createRealIdentityMatrix(dimension);
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
/*      */   protected RealMatrix getLUMatrix()
/*      */     throws InvalidMatrixException
/*      */   {
/*  968 */     if (this.lu == null) {
/*  969 */       luDecompose();
/*      */     }
/*  971 */     return new RealMatrixImpl(this.lu);
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
/*  987 */     int[] out = new int[this.permutation.length];
/*  988 */     System.arraycopy(this.permutation, 0, out, 0, this.permutation.length);
/*  989 */     return out;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   private double[][] copyOut()
/*      */   {
/* 1000 */     int nRows = getRowDimension();
/* 1001 */     double[][] out = new double[nRows][getColumnDimension()];
/*      */     
/* 1003 */     for (int i = 0; i < nRows; i++) {
/* 1004 */       System.arraycopy(this.data[i], 0, out[i], 0, this.data[i].length);
/*      */     }
/* 1006 */     return out;
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
/*      */   private void copyIn(double[][] in)
/*      */   {
/* 1020 */     setSubMatrix(in, 0, 0);
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
/* 1031 */     int nRows = getRowDimension();
/* 1032 */     int nCols = getColumnDimension();
/*      */     
/* 1034 */     return (row >= 0) && (row <= nRows - 1) && (col >= 0) && (col <= nCols - 1);
/*      */   }
/*      */ }


/* Location:              /home/menick/Scrivania/ProcessMining/CNMining/CNMining.jar!/org/apache/commons/math/linear/RealMatrixImpl.class
 * Java compiler version: 3 (47.0)
 * JD-Core Version:       0.7.1
 */