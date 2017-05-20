/*     */ package org.jfree.data.time;
/*     */ 
/*     */ import java.io.PrintStream;
/*     */ import javax.swing.table.AbstractTableModel;
/*     */ import org.jfree.data.general.SeriesChangeEvent;
/*     */ import org.jfree.data.general.SeriesChangeListener;
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
/*     */ public class TimeSeriesTableModel
/*     */   extends AbstractTableModel
/*     */   implements SeriesChangeListener
/*     */ {
/*     */   private TimeSeries series;
/*     */   private boolean editable;
/*     */   private RegularTimePeriod newTimePeriod;
/*     */   private Number newValue;
/*     */   
/*     */   public TimeSeriesTableModel()
/*     */   {
/*  74 */     this(new TimeSeries("Untitled"));
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public TimeSeriesTableModel(TimeSeries series)
/*     */   {
/*  83 */     this(series, false);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public TimeSeriesTableModel(TimeSeries series, boolean editable)
/*     */   {
/*  93 */     this.series = series;
/*  94 */     this.series.addChangeListener(this);
/*  95 */     this.editable = editable;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public int getColumnCount()
/*     */   {
/* 105 */     return 2;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public Class getColumnClass(int column)
/*     */   {
/* 116 */     if (column == 0) {
/* 117 */       return String.class;
/*     */     }
/*     */     
/* 120 */     if (column == 1) {
/* 121 */       return Double.class;
/*     */     }
/*     */     
/* 124 */     return null;
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
/*     */   public String getColumnName(int column)
/*     */   {
/* 138 */     if (column == 0) {
/* 139 */       return "Period:";
/*     */     }
/*     */     
/* 142 */     if (column == 1) {
/* 143 */       return "Value:";
/*     */     }
/*     */     
/* 146 */     return null;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public int getRowCount()
/*     */   {
/* 158 */     return this.series.getItemCount();
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
/*     */   public Object getValueAt(int row, int column)
/*     */   {
/* 171 */     if (row < this.series.getItemCount()) {
/* 172 */       if (column == 0) {
/* 173 */         return this.series.getTimePeriod(row);
/*     */       }
/*     */       
/* 176 */       if (column == 1) {
/* 177 */         return this.series.getValue(row);
/*     */       }
/*     */       
/* 180 */       return null;
/*     */     }
/*     */     
/*     */ 
/*     */ 
/* 185 */     if (column == 0) {
/* 186 */       return this.newTimePeriod;
/*     */     }
/*     */     
/* 189 */     if (column == 1) {
/* 190 */       return this.newValue;
/*     */     }
/*     */     
/* 193 */     return null;
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
/*     */   public boolean isCellEditable(int row, int column)
/*     */   {
/* 209 */     if (this.editable) {
/* 210 */       if ((column == 0) || (column == 1)) {
/* 211 */         return true;
/*     */       }
/*     */       
/* 214 */       return false;
/*     */     }
/*     */     
/*     */ 
/* 218 */     return false;
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
/*     */   public void setValueAt(Object value, int row, int column)
/*     */   {
/* 231 */     if (row < this.series.getItemCount())
/*     */     {
/*     */ 
/* 234 */       if (column == 1) {
/*     */         try {
/* 236 */           Double v = Double.valueOf(value.toString());
/* 237 */           this.series.update(row, v);
/*     */         }
/*     */         catch (NumberFormatException nfe)
/*     */         {
/* 241 */           System.err.println("Number format exception");
/*     */         }
/*     */         
/*     */       }
/*     */     }
/* 246 */     else if (column == 0)
/*     */     {
/* 248 */       this.newTimePeriod = null;
/*     */     }
/* 250 */     else if (column == 1) {
/* 251 */       this.newValue = Double.valueOf(value.toString());
/*     */     }
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public void seriesChanged(SeriesChangeEvent event)
/*     */   {
/* 263 */     fireTableDataChanged();
/*     */   }
/*     */ }


/* Location:              /home/menick/Scrivania/ProcessMining/CNMining/CNMining.jar!/org/jfree/data/time/TimeSeriesTableModel.class
 * Java compiler version: 3 (47.0)
 * JD-Core Version:       0.7.1
 */