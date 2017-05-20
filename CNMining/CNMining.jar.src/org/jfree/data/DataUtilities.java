/*     */ package org.jfree.data;
/*     */ 
/*     */ import java.util.Arrays;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public abstract class DataUtilities
/*     */ {
/*     */   public static boolean equal(double[][] a, double[][] b)
/*     */   {
/*  73 */     if (a == null) {
/*  74 */       return b == null;
/*     */     }
/*  76 */     if (b == null) {
/*  77 */       return false;
/*     */     }
/*  79 */     if (a.length != b.length) {
/*  80 */       return false;
/*     */     }
/*  82 */     for (int i = 0; i < a.length; i++) {
/*  83 */       if (!Arrays.equals(a[i], b[i])) {
/*  84 */         return false;
/*     */       }
/*     */     }
/*  87 */     return true;
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
/*     */   public static double[][] clone(double[][] source)
/*     */   {
/* 100 */     if (source == null) {
/* 101 */       throw new IllegalArgumentException("Null 'source' argument.");
/*     */     }
/* 103 */     double[][] clone = new double[source.length][];
/* 104 */     for (int i = 0; i < source.length; i++) {
/* 105 */       if (source[i] != null) {
/* 106 */         double[] row = new double[source[i].length];
/* 107 */         System.arraycopy(source[i], 0, row, 0, source[i].length);
/* 108 */         clone[i] = row;
/*     */       }
/*     */     }
/* 111 */     return clone;
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
/*     */   public static double calculateColumnTotal(Values2D data, int column)
/*     */   {
/* 124 */     if (data == null) {
/* 125 */       throw new IllegalArgumentException("Null 'data' argument.");
/*     */     }
/* 127 */     double total = 0.0D;
/* 128 */     int rowCount = data.getRowCount();
/* 129 */     for (int r = 0; r < rowCount; r++) {
/* 130 */       Number n = data.getValue(r, column);
/* 131 */       if (n != null) {
/* 132 */         total += n.doubleValue();
/*     */       }
/*     */     }
/* 135 */     return total;
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
/*     */   public static double calculateColumnTotal(Values2D data, int column, int[] validRows)
/*     */   {
/* 152 */     if (data == null) {
/* 153 */       throw new IllegalArgumentException("Null 'data' argument.");
/*     */     }
/* 155 */     double total = 0.0D;
/* 156 */     int rowCount = data.getRowCount();
/* 157 */     for (int v = 0; v < validRows.length; v++) {
/* 158 */       int row = validRows[v];
/* 159 */       if (row < rowCount) {
/* 160 */         Number n = data.getValue(row, column);
/* 161 */         if (n != null) {
/* 162 */           total += n.doubleValue();
/*     */         }
/*     */       }
/*     */     }
/* 166 */     return total;
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
/*     */   public static double calculateRowTotal(Values2D data, int row)
/*     */   {
/* 179 */     if (data == null) {
/* 180 */       throw new IllegalArgumentException("Null 'data' argument.");
/*     */     }
/* 182 */     double total = 0.0D;
/* 183 */     int columnCount = data.getColumnCount();
/* 184 */     for (int c = 0; c < columnCount; c++) {
/* 185 */       Number n = data.getValue(row, c);
/* 186 */       if (n != null) {
/* 187 */         total += n.doubleValue();
/*     */       }
/*     */     }
/* 190 */     return total;
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
/*     */   public static double calculateRowTotal(Values2D data, int row, int[] validCols)
/*     */   {
/* 207 */     if (data == null) {
/* 208 */       throw new IllegalArgumentException("Null 'data' argument.");
/*     */     }
/* 210 */     double total = 0.0D;
/* 211 */     int colCount = data.getColumnCount();
/* 212 */     for (int v = 0; v < validCols.length; v++) {
/* 213 */       int col = validCols[v];
/* 214 */       if (col < colCount) {
/* 215 */         Number n = data.getValue(row, col);
/* 216 */         if (n != null) {
/* 217 */           total += n.doubleValue();
/*     */         }
/*     */       }
/*     */     }
/* 221 */     return total;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public static Number[] createNumberArray(double[] data)
/*     */   {
/* 233 */     if (data == null) {
/* 234 */       throw new IllegalArgumentException("Null 'data' argument.");
/*     */     }
/* 236 */     Number[] result = new Number[data.length];
/* 237 */     for (int i = 0; i < data.length; i++) {
/* 238 */       result[i] = new Double(data[i]);
/*     */     }
/* 240 */     return result;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public static Number[][] createNumberArray2D(double[][] data)
/*     */   {
/* 252 */     if (data == null) {
/* 253 */       throw new IllegalArgumentException("Null 'data' argument.");
/*     */     }
/* 255 */     int l1 = data.length;
/* 256 */     Number[][] result = new Number[l1][];
/* 257 */     for (int i = 0; i < l1; i++) {
/* 258 */       result[i] = createNumberArray(data[i]);
/*     */     }
/* 260 */     return result;
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
/*     */   public static KeyedValues getCumulativePercentages(KeyedValues data)
/*     */   {
/* 274 */     if (data == null) {
/* 275 */       throw new IllegalArgumentException("Null 'data' argument.");
/*     */     }
/* 277 */     DefaultKeyedValues result = new DefaultKeyedValues();
/* 278 */     double total = 0.0D;
/* 279 */     for (int i = 0; i < data.getItemCount(); i++) {
/* 280 */       Number v = data.getValue(i);
/* 281 */       if (v != null) {
/* 282 */         total += v.doubleValue();
/*     */       }
/*     */     }
/* 285 */     double runningTotal = 0.0D;
/* 286 */     for (int i = 0; i < data.getItemCount(); i++) {
/* 287 */       Number v = data.getValue(i);
/* 288 */       if (v != null) {
/* 289 */         runningTotal += v.doubleValue();
/*     */       }
/* 291 */       result.addValue(data.getKey(i), new Double(runningTotal / total));
/*     */     }
/* 293 */     return result;
/*     */   }
/*     */ }


/* Location:              /home/menick/Scrivania/ProcessMining/CNMining/CNMining.jar!/org/jfree/data/DataUtilities.class
 * Java compiler version: 3 (47.0)
 * JD-Core Version:       0.7.1
 */