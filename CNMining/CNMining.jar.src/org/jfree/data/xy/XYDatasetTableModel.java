/*     */ package org.jfree.data.xy;
/*     */ 
/*     */ import javax.swing.table.AbstractTableModel;
/*     */ import javax.swing.table.TableModel;
/*     */ import org.jfree.data.general.DatasetChangeEvent;
/*     */ import org.jfree.data.general.DatasetChangeListener;
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
/*     */ public class XYDatasetTableModel
/*     */   extends AbstractTableModel
/*     */   implements TableModel, DatasetChangeListener
/*     */ {
/*  69 */   TableXYDataset model = null;
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public XYDatasetTableModel() {}
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public XYDatasetTableModel(TableXYDataset dataset)
/*     */   {
/*  84 */     this();
/*     */     
/*  86 */     this.model.addChangeListener(this);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public void setModel(TableXYDataset dataset)
/*     */   {
/*  95 */     this.model = dataset;
/*  96 */     this.model.addChangeListener(this);
/*  97 */     fireTableDataChanged();
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public int getRowCount()
/*     */   {
/* 106 */     if (this.model == null) {
/* 107 */       return 0;
/*     */     }
/* 109 */     return this.model.getItemCount();
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public int getColumnCount()
/*     */   {
/* 118 */     if (this.model == null) {
/* 119 */       return 0;
/*     */     }
/* 121 */     return this.model.getSeriesCount() + 1;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public String getColumnName(int column)
/*     */   {
/* 132 */     if (this.model == null) {
/* 133 */       return super.getColumnName(column);
/*     */     }
/* 135 */     if (column < 1) {
/* 136 */       return "X Value";
/*     */     }
/*     */     
/* 139 */     return this.model.getSeriesKey(column - 1).toString();
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
/*     */   public Object getValueAt(int row, int column)
/*     */   {
/* 153 */     if (this.model == null) {
/* 154 */       return null;
/*     */     }
/* 156 */     if (column < 1) {
/* 157 */       return this.model.getX(0, row);
/*     */     }
/*     */     
/* 160 */     return this.model.getY(column - 1, row);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public void datasetChanged(DatasetChangeEvent event)
/*     */   {
/* 172 */     fireTableDataChanged();
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public boolean isCellEditable(int row, int column)
/*     */   {
/* 184 */     return false;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public void setValueAt(Object value, int row, int column)
/*     */   {
/* 195 */     if (isCellEditable(row, column)) {}
/*     */   }
/*     */ }


/* Location:              /home/menick/Scrivania/ProcessMining/CNMining/CNMining.jar!/org/jfree/data/xy/XYDatasetTableModel.class
 * Java compiler version: 3 (47.0)
 * JD-Core Version:       0.7.1
 */