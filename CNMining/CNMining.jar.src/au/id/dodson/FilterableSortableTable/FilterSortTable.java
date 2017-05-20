/*     */ package au.id.dodson.FilterableSortableTable;
/*     */ 
/*     */ import java.awt.event.KeyAdapter;
/*     */ import java.awt.event.KeyEvent;
/*     */ import java.awt.event.MouseAdapter;
/*     */ import java.awt.event.MouseEvent;
/*     */ import java.util.Iterator;
/*     */ import java.util.Vector;
/*     */ import javax.swing.JTable;
/*     */ import javax.swing.ListSelectionModel;
/*     */ import javax.swing.event.ListSelectionEvent;
/*     */ import javax.swing.event.ListSelectionListener;
/*     */ import javax.swing.table.JTableHeader;
/*     */ import javax.swing.table.TableModel;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class FilterSortTable
/*     */   extends JTable
/*     */ {
/*     */   public TableExclusionFilterModel filterModel;
/*  43 */   public int selectedRow = 0;
/*     */   private Vector rowData;
/*  45 */   private int sourceLocation = 0;
/*  46 */   private Vector tableListeners = new Vector();
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   public void setTableFilter(String filter)
/*     */   {
/*  53 */     this.filterModel.setFilter(filter, true);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   public void setTableModel(TableModel tableModel)
/*     */   {
/*  61 */     setVisible(true);
/*  62 */     setAutoscrolls(true);
/*     */     
/*  64 */     TableSorter tableSorter = new TableSorter(tableModel);
/*  65 */     this.filterModel = new TableExclusionFilterModel(tableSorter, tableModel.getColumnCount(), true);
/*  66 */     super.setModel(this.filterModel);
/*  67 */     tableSorter.setTableHeader(getTableHeader());
/*     */     
/*     */ 
/*  70 */     getTableHeader().setToolTipText("Click to specify sorting; Control-Click to specify secondary sorting");
/*     */     
/*  72 */     setRowSelectionAllowed(true);
/*  73 */     setColumnSelectionAllowed(false);
/*     */     
/*  75 */     setSelectionMode(0);
/*     */     
/*  77 */     setAutoResizeMode(4);
/*     */     
/*  79 */     ListSelectionModel rowSM = getSelectionModel();
/*  80 */     rowSM.addListSelectionListener(new ListSelectionListener()
/*     */     {
/*     */       public void valueChanged(ListSelectionEvent e)
/*     */       {
/*  84 */         if (e.getValueIsAdjusting()) {
/*  85 */           return;
/*     */         }
/*  87 */         ListSelectionModel lsm = (ListSelectionModel)e.getSource();
/*  88 */         if (!lsm.isSelectionEmpty())
/*     */         {
/*  90 */           FilterSortTable.this.selectedRow = lsm.getMinSelectionIndex();
/*  91 */           FilterSortTable.this.setSelectedRow(FilterSortTable.this.selectedRow);
/*     */         }
/*     */         
/*     */       }
/*  95 */     });
/*  96 */     addMouseListener(new MouseAdapter()
/*     */     {
/*     */       public void mouseClicked(MouseEvent e) {
/*  99 */         if (e.getClickCount() == 2)
/*     */         {
/* 101 */           FilterSortTable.this.setRowAccepted(FilterSortTable.this.selectedRow);
/*     */         }
/*     */         
/*     */       }
/* 105 */     });
/* 106 */     addKeyListener(new KeyAdapter()
/*     */     {
/*     */       public void keyPressed(KeyEvent e)
/*     */       {
/* 110 */         if (e.getKeyCode() == 10) {
/* 111 */           FilterSortTable.this.setRowAccepted(FilterSortTable.this.selectedRow);
/*     */         }
/*     */       }
/*     */     });
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */   public int getSelectedRow()
/*     */   {
/* 121 */     return this.selectedRow;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */   public void setRowAccepted(int acceptedRow)
/*     */   {
/* 128 */     Iterator itr = this.tableListeners.iterator();
/* 129 */     while (itr.hasNext()) {
/* 130 */       TableListenerInterface listener = (TableListenerInterface)itr.next();
/* 131 */       listener.setRowAccepted(getRow(acceptedRow));
/*     */     }
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   public void addTableListener(TableListenerInterface l)
/*     */   {
/* 140 */     this.tableListeners.add(l);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   public void removeTableListener(TableListenerInterface l)
/*     */   {
/* 148 */     this.tableListeners.remove(l);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   public void setSelectedRow(int selectedRow)
/*     */   {
/* 156 */     this.selectedRow = selectedRow;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public Vector getRow(int selectedRow)
/*     */   {
/* 165 */     Vector rowValue = null;
/* 166 */     if (selectedRow >= 0) {
/* 167 */       rowValue = new Vector();
/* 168 */       for (int i = 0; i < getColumnCount(); i++) {
/* 169 */         rowValue.add(getValueAt(selectedRow, i));
/*     */       }
/*     */     }
/* 172 */     return rowValue;
/*     */   }
/*     */ }


/* Location:              /home/menick/Scrivania/ProcessMining/CNMining/CNMining.jar!/au/id/dodson/FilterableSortableTable/FilterSortTable.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       0.7.1
 */