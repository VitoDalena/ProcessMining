/*     */ package org.jfree.data.xy;
/*     */ 
/*     */ import java.io.Serializable;
/*     */ import org.jfree.data.general.Series;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class MatrixSeries
/*     */   extends Series
/*     */   implements Serializable
/*     */ {
/*     */   private static final long serialVersionUID = 7934188527308315704L;
/*     */   protected double[][] data;
/*     */   
/*     */   public MatrixSeries(String name, int rows, int columns)
/*     */   {
/*  77 */     super(name);
/*  78 */     this.data = new double[rows][columns];
/*  79 */     zeroAll();
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public int getColumnsCount()
/*     */   {
/*  88 */     return this.data[0].length;
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
/*     */   public Number getItem(int itemIndex)
/*     */   {
/* 103 */     int i = getItemRow(itemIndex);
/* 104 */     int j = getItemColumn(itemIndex);
/*     */     
/* 106 */     Number n = new Double(get(i, j));
/*     */     
/* 108 */     return n;
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
/*     */   public int getItemColumn(int itemIndex)
/*     */   {
/* 121 */     return itemIndex % getColumnsCount();
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public int getItemCount()
/*     */   {
/* 131 */     return getRowCount() * getColumnsCount();
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
/*     */   public int getItemRow(int itemIndex)
/*     */   {
/* 144 */     return itemIndex / getColumnsCount();
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public int getRowCount()
/*     */   {
/* 154 */     return this.data.length;
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
/*     */   public double get(int i, int j)
/*     */   {
/* 170 */     return this.data[i][j];
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
/*     */   public void update(int i, int j, double mij)
/*     */   {
/* 184 */     this.data[i][j] = mij;
/* 185 */     fireSeriesChanged();
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public void zeroAll()
/*     */   {
/* 195 */     int rows = getRowCount();
/* 196 */     int columns = getColumnsCount();
/*     */     
/* 198 */     for (int row = 0; row < rows; row++) {
/* 199 */       for (int column = 0; column < columns; column++) {
/* 200 */         this.data[row][column] = 0.0D;
/*     */       }
/*     */     }
/* 203 */     fireSeriesChanged();
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public boolean equals(Object obj)
/*     */   {
/* 214 */     if (obj == this) {
/* 215 */       return true;
/*     */     }
/* 217 */     if (!(obj instanceof MatrixSeries)) {
/* 218 */       return false;
/*     */     }
/* 220 */     MatrixSeries that = (MatrixSeries)obj;
/* 221 */     if (getRowCount() != that.getRowCount()) {
/* 222 */       return false;
/*     */     }
/* 224 */     if (getColumnsCount() != that.getColumnsCount()) {
/* 225 */       return false;
/*     */     }
/* 227 */     for (int r = 0; r < getRowCount(); r++) {
/* 228 */       for (int c = 0; c < getColumnsCount(); c++) {
/* 229 */         if (get(r, c) != that.get(r, c)) {
/* 230 */           return false;
/*     */         }
/*     */       }
/*     */     }
/* 234 */     return super.equals(obj);
/*     */   }
/*     */ }


/* Location:              /home/menick/Scrivania/ProcessMining/CNMining/CNMining.jar!/org/jfree/data/xy/MatrixSeries.class
 * Java compiler version: 3 (47.0)
 * JD-Core Version:       0.7.1
 */