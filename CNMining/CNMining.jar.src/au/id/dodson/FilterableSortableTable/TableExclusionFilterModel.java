/*     */ package au.id.dodson.FilterableSortableTable;
/*     */ 
/*     */ import java.util.ArrayList;
/*     */ import javax.swing.event.TableModelEvent;
/*     */ import javax.swing.event.TableModelListener;
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
/*     */ public class TableExclusionFilterModel
/*     */   extends AbstractTableModel
/*     */   implements TableModelListener
/*     */ {
/*  30 */   protected ArrayList indexList = new ArrayList(0);
/*     */   
/*  32 */   protected String filter = "All";
/*     */   
/*     */   protected AbstractTableModel model;
/*     */   
/*  36 */   private int filterColumn = 0;
/*  37 */   private int noOfFilterableColumns = 0;
/*  38 */   private boolean filterAllColumns = false;
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   public AbstractTableModel getModel()
/*     */   {
/*  45 */     return this.model;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   public void setModel(AbstractTableModel model)
/*     */   {
/*  53 */     this.model = model;
/*  54 */     fillIndexList(this.filter);
/*  55 */     model.addTableModelListener(this);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public TableExclusionFilterModel(AbstractTableModel model, int filterColumn)
/*     */   {
/*  64 */     setModel(model);
/*  65 */     this.filterColumn = filterColumn;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public TableExclusionFilterModel(AbstractTableModel model, int noOfFilterableColumns, boolean filterAllColumns)
/*     */   {
/*  76 */     setModel(model);
/*  77 */     this.noOfFilterableColumns = noOfFilterableColumns;
/*  78 */     this.filterAllColumns = filterAllColumns;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   public TableExclusionFilterModel(AbstractTableModel model)
/*     */   {
/*  86 */     setModel(model);
/*  87 */     this.noOfFilterableColumns = model.getColumnCount();
/*  88 */     this.filterAllColumns = true;
/*     */   }
/*     */   
/*     */   private boolean filterInString(String checkString) {
/*  92 */     return (this.filter.equals("All")) || (checkString.toLowerCase().indexOf(this.filter.toLowerCase()) != -1);
/*     */   }
/*     */   
/*     */   private void fillIndexList(String filter)
/*     */   {
/*  97 */     this.filter = filter;
/*  98 */     int rowCount = this.model.getRowCount();
/*  99 */     this.indexList.clear();
/* 100 */     for (int z = 0; z < rowCount; z++)
/*     */     {
/*     */ 
/* 103 */       if (this.filterAllColumns) {
/* 104 */         int start = 0;
/* 105 */         int end = this.noOfFilterableColumns;
/* 106 */         for (int i = 0; i < this.noOfFilterableColumns; i++) {
/* 107 */           this.filterColumn = i;
/* 108 */           if (this.model.getValueAt(z, this.filterColumn) != null) {
/* 109 */             String myFilter = this.model.getValueAt(z, this.filterColumn).toString();
/* 110 */             if (filterInString(myFilter)) {
/* 111 */               this.indexList.add(new Integer(z));
/* 112 */               break;
/*     */             }
/*     */           }
/*     */         }
/*     */       }
/* 117 */       else if (this.model.getValueAt(z, this.filterColumn) != null) {
/* 118 */         String myFilter = this.model.getValueAt(z, this.filterColumn).toString();
/* 119 */         if (filterInString(myFilter)) {
/* 120 */           this.indexList.add(new Integer(z));
/*     */         }
/*     */       }
/*     */     }
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public synchronized void setFilter(String filter, boolean doAll)
/*     */   {
/* 133 */     fillIndexList(filter);
/*     */     
/* 135 */     fireTableDataChanged();
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   public synchronized void setFilter(String filter)
/*     */   {
/* 143 */     fillIndexList(filter);
/* 144 */     fireTableDataChanged();
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   public String getFilter()
/*     */   {
/* 152 */     return this.filter;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   public int getRowCount()
/*     */   {
/* 160 */     return this.indexList.size();
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   public int getColumnCount()
/*     */   {
/* 168 */     return this.model.getColumnCount();
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public Object getValueAt(int rowIndex, int columnIndex)
/*     */   {
/* 178 */     int newIndex = ((Integer)this.indexList.get(rowIndex)).intValue();
/* 179 */     return this.model.getValueAt(newIndex, columnIndex);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public String getColumnName(int aColumn)
/*     */   {
/* 188 */     return this.model.getColumnName(aColumn);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public Class getColumnClass(int aColumn)
/*     */   {
/* 197 */     return this.model.getColumnClass(aColumn);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public boolean isCellEditable(int row, int column)
/*     */   {
/* 207 */     return this.model.isCellEditable(row, column);
/*     */   }
/*     */   
/*     */   public synchronized void reallocateIndexes() {
/* 211 */     fillIndexList(this.filter);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   public void tableChanged(TableModelEvent e)
/*     */   {
/* 219 */     reallocateIndexes();
/* 220 */     fireTableChanged(e);
/*     */   }
/*     */ }


/* Location:              /home/menick/Scrivania/ProcessMining/CNMining/CNMining.jar!/au/id/dodson/FilterableSortableTable/TableExclusionFilterModel.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       0.7.1
 */