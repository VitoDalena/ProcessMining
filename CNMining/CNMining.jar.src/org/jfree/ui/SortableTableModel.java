/*     */ package org.jfree.ui;
/*     */ 
/*     */ import javax.swing.table.AbstractTableModel;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public abstract class SortableTableModel
/*     */   extends AbstractTableModel
/*     */ {
/*     */   private int sortingColumn;
/*     */   private boolean ascending;
/*     */   
/*     */   public SortableTableModel()
/*     */   {
/*  66 */     this.sortingColumn = -1;
/*  67 */     this.ascending = true;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public int getSortingColumn()
/*     */   {
/*  77 */     return this.sortingColumn;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public boolean isAscending()
/*     */   {
/*  88 */     return this.ascending;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public void setAscending(boolean flag)
/*     */   {
/*  98 */     this.ascending = flag;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public void sortByColumn(int column, boolean ascending)
/*     */   {
/* 108 */     if (isSortable(column)) {
/* 109 */       this.sortingColumn = column;
/*     */     }
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public boolean isSortable(int column)
/*     */   {
/* 121 */     return false;
/*     */   }
/*     */ }


/* Location:              /home/menick/Scrivania/ProcessMining/CNMining/CNMining.jar!/org/jfree/ui/SortableTableModel.class
 * Java compiler version: 2 (46.0)
 * JD-Core Version:       0.7.1
 */