/*     */ package org.apache.commons.math.linear;
/*     */ 
/*     */ import java.math.BigDecimal;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class MatrixUtils
/*     */ {
/*     */   public static RealMatrix createRealMatrix(double[][] data)
/*     */   {
/*  47 */     return new RealMatrixImpl(data);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public static RealMatrix createRealIdentityMatrix(int dimension)
/*     */   {
/*  59 */     RealMatrixImpl out = new RealMatrixImpl(dimension, dimension);
/*  60 */     double[][] d = out.getDataRef();
/*  61 */     for (int row = 0; row < dimension; row++) {
/*  62 */       for (int col = 0; col < dimension; col++) {
/*  63 */         d[row][col] = (row == col ? 1.0D : 0.0D);
/*     */       }
/*     */     }
/*  66 */     return out;
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
/*     */   public static BigMatrix createBigMatrix(double[][] data)
/*     */   {
/*  80 */     return new BigMatrixImpl(data);
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
/*     */   public static BigMatrix createBigMatrix(BigDecimal[][] data)
/*     */   {
/*  94 */     return new BigMatrixImpl(data);
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
/*     */   public static BigMatrix createBigMatrix(String[][] data)
/*     */   {
/* 108 */     return new BigMatrixImpl(data);
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
/*     */   public static RealMatrix createRowRealMatrix(double[] rowData)
/*     */   {
/* 121 */     int nCols = rowData.length;
/* 122 */     double[][] data = new double[1][nCols];
/* 123 */     System.arraycopy(rowData, 0, data[0], 0, nCols);
/* 124 */     return new RealMatrixImpl(data);
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
/*     */   public static BigMatrix createRowBigMatrix(double[] rowData)
/*     */   {
/* 137 */     int nCols = rowData.length;
/* 138 */     double[][] data = new double[1][nCols];
/* 139 */     System.arraycopy(rowData, 0, data[0], 0, nCols);
/* 140 */     return new BigMatrixImpl(data);
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
/*     */   public static BigMatrix createRowBigMatrix(BigDecimal[] rowData)
/*     */   {
/* 153 */     int nCols = rowData.length;
/* 154 */     BigDecimal[][] data = new BigDecimal[1][nCols];
/* 155 */     System.arraycopy(rowData, 0, data[0], 0, nCols);
/* 156 */     return new BigMatrixImpl(data);
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
/*     */   public static BigMatrix createRowBigMatrix(String[] rowData)
/*     */   {
/* 169 */     int nCols = rowData.length;
/* 170 */     String[][] data = new String[1][nCols];
/* 171 */     System.arraycopy(rowData, 0, data[0], 0, nCols);
/* 172 */     return new BigMatrixImpl(data);
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
/*     */   public static RealMatrix createColumnRealMatrix(double[] columnData)
/*     */   {
/* 185 */     int nRows = columnData.length;
/* 186 */     double[][] data = new double[nRows][1];
/* 187 */     for (int row = 0; row < nRows; row++) {
/* 188 */       data[row][0] = columnData[row];
/*     */     }
/* 190 */     return new RealMatrixImpl(data);
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
/*     */   public static BigMatrix createColumnBigMatrix(double[] columnData)
/*     */   {
/* 203 */     int nRows = columnData.length;
/* 204 */     double[][] data = new double[nRows][1];
/* 205 */     for (int row = 0; row < nRows; row++) {
/* 206 */       data[row][0] = columnData[row];
/*     */     }
/* 208 */     return new BigMatrixImpl(data);
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
/*     */   public static BigMatrix createColumnBigMatrix(BigDecimal[] columnData)
/*     */   {
/* 221 */     int nRows = columnData.length;
/* 222 */     BigDecimal[][] data = new BigDecimal[nRows][1];
/* 223 */     for (int row = 0; row < nRows; row++) {
/* 224 */       data[row][0] = columnData[row];
/*     */     }
/* 226 */     return new BigMatrixImpl(data);
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
/*     */   public static BigMatrix createColumnBigMatrix(String[] columnData)
/*     */   {
/* 239 */     int nRows = columnData.length;
/* 240 */     String[][] data = new String[nRows][1];
/* 241 */     for (int row = 0; row < nRows; row++) {
/* 242 */       data[row][0] = columnData[row];
/*     */     }
/* 244 */     return new BigMatrixImpl(data);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public static BigMatrix createBigIdentityMatrix(int dimension)
/*     */   {
/* 256 */     BigMatrixImpl out = new BigMatrixImpl(dimension, dimension);
/* 257 */     BigDecimal[][] d = out.getDataRef();
/* 258 */     for (int row = 0; row < dimension; row++) {
/* 259 */       for (int col = 0; col < dimension; col++) {
/* 260 */         d[row][col] = (row == col ? BigMatrixImpl.ONE : BigMatrixImpl.ZERO);
/*     */       }
/*     */     }
/* 263 */     return out;
/*     */   }
/*     */ }


/* Location:              /home/menick/Scrivania/ProcessMining/CNMining/CNMining.jar!/org/apache/commons/math/linear/MatrixUtils.class
 * Java compiler version: 3 (47.0)
 * JD-Core Version:       0.7.1
 */