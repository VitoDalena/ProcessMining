/*     */ package au.id.dodson.FilterableSortableTable;
/*     */ 
/*     */ import java.awt.Color;
/*     */ import java.awt.Component;
/*     */ import java.awt.Font;
/*     */ import java.awt.Graphics;
/*     */ import java.awt.event.MouseAdapter;
/*     */ import java.awt.event.MouseEvent;
/*     */ import java.awt.event.MouseListener;
/*     */ import java.util.ArrayList;
/*     */ import java.util.Arrays;
/*     */ import java.util.Comparator;
/*     */ import java.util.HashMap;
/*     */ import java.util.Iterator;
/*     */ import java.util.List;
/*     */ import java.util.Map;
/*     */ import javax.swing.Icon;
/*     */ import javax.swing.JLabel;
/*     */ import javax.swing.JTable;
/*     */ import javax.swing.event.TableModelEvent;
/*     */ import javax.swing.event.TableModelListener;
/*     */ import javax.swing.table.AbstractTableModel;
/*     */ import javax.swing.table.JTableHeader;
/*     */ import javax.swing.table.TableCellRenderer;
/*     */ import javax.swing.table.TableColumn;
/*     */ import javax.swing.table.TableColumnModel;
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
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class TableSorter
/*     */   extends AbstractTableModel
/*     */ {
/*     */   protected TableModel tableModel;
/*     */   public static final int DESCENDING = -1;
/*     */   public static final int NOT_SORTED = 0;
/*     */   public static final int ASCENDING = 1;
/*  82 */   private static Directive EMPTY_DIRECTIVE = new Directive(-1, 0);
/*     */   
/*  84 */   public static final Comparator COMPARABLE_COMAPRATOR = new Comparator() {
/*     */     public int compare(Object o1, Object o2) {
/*  86 */       return ((Comparable)o1).compareTo(o2);
/*     */     }
/*     */   };
/*  89 */   public static final Comparator LEXICAL_COMPARATOR = new Comparator() {
/*     */     public int compare(Object o1, Object o2) {
/*  91 */       return o1.toString().compareTo(o2.toString());
/*     */     }
/*     */   };
/*     */   
/*     */   private Row[] viewToModel;
/*     */   
/*     */   private int[] modelToView;
/*     */   private JTableHeader tableHeader;
/*     */   private MouseListener mouseListener;
/*     */   private TableModelListener tableModelListener;
/* 101 */   private Map columnComparators = new HashMap();
/* 102 */   private List sortingColumns = new ArrayList();
/*     */   
/*     */   public TableSorter() {
/* 105 */     this.mouseListener = new MouseHandler(null);
/* 106 */     this.tableModelListener = new TableModelHandler(null);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   public TableSorter(TableModel tableModel)
/*     */   {
/* 114 */     this();
/* 115 */     setTableModel(tableModel);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public TableSorter(TableModel tableModel, JTableHeader tableHeader)
/*     */   {
/* 124 */     this();
/* 125 */     setTableHeader(tableHeader);
/* 126 */     setTableModel(tableModel);
/*     */   }
/*     */   
/*     */   private void clearSortingState() {
/* 130 */     this.viewToModel = null;
/* 131 */     this.modelToView = null;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   public TableModel getTableModel()
/*     */   {
/* 139 */     return this.tableModel;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   public void setTableModel(TableModel tableModel)
/*     */   {
/* 147 */     if (this.tableModel != null) {
/* 148 */       this.tableModel.removeTableModelListener(this.tableModelListener);
/*     */     }
/*     */     
/* 151 */     this.tableModel = tableModel;
/* 152 */     if (this.tableModel != null) {
/* 153 */       this.tableModel.addTableModelListener(this.tableModelListener);
/*     */     }
/*     */     
/* 156 */     clearSortingState();
/* 157 */     fireTableStructureChanged();
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   public JTableHeader getTableHeader()
/*     */   {
/* 165 */     return this.tableHeader;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   public void setTableHeader(JTableHeader tableHeader)
/*     */   {
/* 173 */     if (this.tableHeader != null) {
/* 174 */       this.tableHeader.removeMouseListener(this.mouseListener);
/* 175 */       TableCellRenderer defaultRenderer = this.tableHeader.getDefaultRenderer();
/*     */       
/* 177 */       if ((defaultRenderer instanceof SortableHeaderRenderer)) {
/* 178 */         this.tableHeader.setDefaultRenderer(((SortableHeaderRenderer)defaultRenderer).tableCellRenderer);
/*     */       }
/*     */     }
/*     */     
/* 182 */     this.tableHeader = tableHeader;
/* 183 */     if (this.tableHeader != null) {
/* 184 */       this.tableHeader.addMouseListener(this.mouseListener);
/* 185 */       this.tableHeader.setDefaultRenderer(new SortableHeaderRenderer(this.tableHeader.getDefaultRenderer()));
/*     */     }
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public boolean isSorting()
/*     */   {
/* 195 */     return this.sortingColumns.size() != 0;
/*     */   }
/*     */   
/*     */   private Directive getDirective(int column) {
/* 199 */     for (int i = 0; i < this.sortingColumns.size(); i++) {
/* 200 */       Directive directive = (Directive)this.sortingColumns.get(i);
/* 201 */       if (directive.column == column) {
/* 202 */         return directive;
/*     */       }
/*     */     }
/* 205 */     return EMPTY_DIRECTIVE;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public int getSortingStatus(int column)
/*     */   {
/* 214 */     return getDirective(column).direction;
/*     */   }
/*     */   
/*     */   public void removeSortingMouseListener() {
/* 218 */     this.tableHeader.removeMouseListener(this.mouseListener);
/*     */   }
/*     */   
/*     */   private void sortingStatusChanged() {
/* 222 */     clearSortingState();
/* 223 */     fireTableDataChanged();
/* 224 */     if (this.tableHeader != null) {
/* 225 */       this.tableHeader.repaint();
/*     */     }
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public void setSortingStatus(int column, int status)
/*     */   {
/* 235 */     Directive directive = getDirective(column);
/* 236 */     if (directive != EMPTY_DIRECTIVE) {
/* 237 */       this.sortingColumns.remove(directive);
/*     */     }
/* 239 */     if (status != 0) {
/* 240 */       this.sortingColumns.add(new Directive(column, status));
/*     */     }
/* 242 */     sortingStatusChanged();
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   protected Icon getHeaderRendererIcon(int column, int size)
/*     */   {
/* 252 */     Directive directive = getDirective(column);
/* 253 */     if (directive == EMPTY_DIRECTIVE) {
/* 254 */       return null;
/*     */     }
/* 256 */     return new Arrow(directive.direction == -1, size, this.sortingColumns.indexOf(directive));
/*     */   }
/*     */   
/*     */   private void cancelSorting()
/*     */   {
/* 261 */     this.sortingColumns.clear();
/* 262 */     sortingStatusChanged();
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public void setColumnComparator(Class type, Comparator comparator)
/*     */   {
/* 271 */     if (comparator == null) {
/* 272 */       this.columnComparators.remove(type);
/*     */     } else {
/* 274 */       this.columnComparators.put(type, comparator);
/*     */     }
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   protected Comparator getComparator(int column)
/*     */   {
/* 284 */     Class columnType = this.tableModel.getColumnClass(column);
/* 285 */     Comparator comparator = (Comparator)this.columnComparators.get(columnType);
/* 286 */     if (comparator != null) {
/* 287 */       return comparator;
/*     */     }
/* 289 */     if (Comparable.class.isAssignableFrom(columnType)) {
/* 290 */       return COMPARABLE_COMAPRATOR;
/*     */     }
/* 292 */     return LEXICAL_COMPARATOR;
/*     */   }
/*     */   
/*     */   private Row[] getViewToModel() {
/* 296 */     if (this.viewToModel == null) {
/* 297 */       int tableModelRowCount = this.tableModel.getRowCount();
/* 298 */       this.viewToModel = new Row[tableModelRowCount];
/* 299 */       for (int row = 0; row < tableModelRowCount; row++) {
/* 300 */         this.viewToModel[row] = new Row(row);
/*     */       }
/*     */       
/* 303 */       if (isSorting()) {
/* 304 */         Arrays.sort(this.viewToModel);
/*     */       }
/*     */     }
/* 307 */     return this.viewToModel;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public int modelIndex(int viewIndex)
/*     */   {
/* 316 */     return getViewToModel()[viewIndex].modelIndex;
/*     */   }
/*     */   
/*     */   private int[] getModelToView() {
/* 320 */     if (this.modelToView == null) {
/* 321 */       int n = getViewToModel().length;
/* 322 */       this.modelToView = new int[n];
/* 323 */       for (int i = 0; i < n; i++) {
/* 324 */         this.modelToView[modelIndex(i)] = i;
/*     */       }
/*     */     }
/* 327 */     return this.modelToView;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public int getRowCount()
/*     */   {
/* 337 */     return this.tableModel == null ? 0 : this.tableModel.getRowCount();
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   public int getColumnCount()
/*     */   {
/* 345 */     return this.tableModel == null ? 0 : this.tableModel.getColumnCount();
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public String getColumnName(int column)
/*     */   {
/* 354 */     return this.tableModel.getColumnName(column);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public Class getColumnClass(int column)
/*     */   {
/* 363 */     return this.tableModel.getColumnClass(column);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public boolean isCellEditable(int row, int column)
/*     */   {
/* 373 */     return this.tableModel.isCellEditable(modelIndex(row), column);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public Object getValueAt(int row, int column)
/*     */   {
/* 383 */     return this.tableModel.getValueAt(modelIndex(row), column);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public void setValueAt(Object aValue, int row, int column)
/*     */   {
/* 393 */     this.tableModel.setValueAt(aValue, modelIndex(row), column);
/*     */   }
/*     */   
/*     */   private class Row implements Comparable
/*     */   {
/*     */     private int modelIndex;
/*     */     
/*     */     public Row(int index)
/*     */     {
/* 402 */       this.modelIndex = index;
/*     */     }
/*     */     
/*     */     public int compareTo(Object o) {
/* 406 */       int row1 = this.modelIndex;
/* 407 */       int row2 = ((Row)o).modelIndex;
/*     */       
/* 409 */       for (Iterator it = TableSorter.this.sortingColumns.iterator(); it.hasNext();) {
/* 410 */         TableSorter.Directive directive = (TableSorter.Directive)it.next();
/* 411 */         int column = directive.column;
/* 412 */         Object o1 = TableSorter.this.tableModel.getValueAt(row1, column);
/* 413 */         Object o2 = TableSorter.this.tableModel.getValueAt(row2, column);
/*     */         
/* 415 */         int comparison = 0;
/*     */         
/* 417 */         if ((o1 == null) && (o2 == null)) {
/* 418 */           comparison = 0;
/* 419 */         } else if (o1 == null) {
/* 420 */           comparison = -1;
/* 421 */         } else if (o2 == null) {
/* 422 */           comparison = 1;
/*     */         } else {
/* 424 */           comparison = TableSorter.this.getComparator(column).compare(o1, o2);
/*     */         }
/* 426 */         if (comparison != 0) {
/* 427 */           return directive.direction == -1 ? -comparison : comparison;
/*     */         }
/*     */       }
/*     */       
/* 431 */       return 0;
/*     */     }
/*     */   }
/*     */   
/*     */   private class TableModelHandler implements TableModelListener {
/*     */     private TableModelHandler() {}
/*     */     
/* 438 */     public void tableChanged(TableModelEvent e) { if (!TableSorter.this.isSorting()) {
/* 439 */         TableSorter.this.clearSortingState();
/* 440 */         TableSorter.this.fireTableChanged(e);
/* 441 */         return;
/*     */       }
/*     */       
/*     */ 
/*     */ 
/*     */ 
/* 447 */       if (e.getFirstRow() == -1) {
/* 448 */         TableSorter.this.cancelSorting();
/* 449 */         TableSorter.this.fireTableChanged(e);
/* 450 */         return;
/*     */       }
/*     */       
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/* 477 */       int column = e.getColumn();
/* 478 */       if ((e.getFirstRow() == e.getLastRow()) && (column != -1) && (TableSorter.this.getSortingStatus(column) == 0) && (TableSorter.this.modelToView != null))
/*     */       {
/*     */ 
/*     */ 
/* 482 */         int viewIndex = TableSorter.this.getModelToView()[e.getFirstRow()];
/* 483 */         TableSorter.this.fireTableChanged(new TableModelEvent(TableSorter.this, viewIndex, viewIndex, column, e.getType()));
/*     */         
/* 485 */         return;
/*     */       }
/*     */       
/*     */ 
/*     */ 
/* 490 */       TableSorter.this.clearSortingState();
/* 491 */       TableSorter.this.fireTableDataChanged();
/*     */     }
/*     */   }
/*     */   
/*     */   private class MouseHandler extends MouseAdapter {
/*     */     private MouseHandler() {}
/*     */     
/* 498 */     public void mouseClicked(MouseEvent e) { JTableHeader h = (JTableHeader)e.getSource();
/* 499 */       TableColumnModel columnModel = h.getColumnModel();
/* 500 */       int viewColumn = columnModel.getColumnIndexAtX(e.getX());
/* 501 */       int column = columnModel.getColumn(viewColumn).getModelIndex();
/* 502 */       if (column != -1) {
/* 503 */         int status = TableSorter.this.getSortingStatus(column);
/* 504 */         if (!e.isControlDown()) {
/* 505 */           TableSorter.this.cancelSorting();
/*     */         }
/*     */         
/*     */ 
/*     */ 
/*     */ 
/* 511 */         status += (e.isShiftDown() ? -1 : 1);
/* 512 */         status = (status + 4) % 3 - 1;
/*     */         
/* 514 */         TableSorter.this.setSortingStatus(column, status);
/*     */       }
/*     */     }
/*     */   }
/*     */   
/*     */   private static class Arrow implements Icon {
/*     */     private boolean descending;
/*     */     private int size;
/*     */     private int priority;
/*     */     
/*     */     public Arrow(boolean descending, int size, int priority) {
/* 525 */       this.descending = descending;
/* 526 */       this.size = size;
/* 527 */       this.priority = priority;
/*     */     }
/*     */     
/*     */     public void paintIcon(Component c, Graphics g, int x, int y) {
/* 531 */       Color color = c == null ? Color.GRAY : c.getBackground();
/*     */       
/*     */ 
/* 534 */       int dx = (int)(this.size / 2 * Math.pow(0.8D, this.priority));
/* 535 */       int dy = this.descending ? dx : -dx;
/*     */       
/* 537 */       y = y + 5 * this.size / 6 + (this.descending ? -dy : 0);
/* 538 */       int shift = this.descending ? 1 : -1;
/* 539 */       g.translate(x, y);
/*     */       
/*     */ 
/* 542 */       g.setColor(color.darker());
/* 543 */       g.drawLine(dx / 2, dy, 0, 0);
/* 544 */       g.drawLine(dx / 2, dy + shift, 0, shift);
/*     */       
/*     */ 
/* 547 */       g.setColor(color.brighter());
/* 548 */       g.drawLine(dx / 2, dy, dx, 0);
/* 549 */       g.drawLine(dx / 2, dy + shift, dx, shift);
/*     */       
/*     */ 
/* 552 */       if (this.descending) {
/* 553 */         g.setColor(color.darker().darker());
/*     */       } else {
/* 555 */         g.setColor(color.brighter().brighter());
/*     */       }
/* 557 */       g.drawLine(dx, 0, 0, 0);
/*     */       
/* 559 */       g.setColor(color);
/* 560 */       g.translate(-x, -y);
/*     */     }
/*     */     
/*     */     public int getIconWidth() {
/* 564 */       return this.size;
/*     */     }
/*     */     
/*     */     public int getIconHeight() {
/* 568 */       return this.size;
/*     */     }
/*     */   }
/*     */   
/*     */   private class SortableHeaderRenderer implements TableCellRenderer {
/*     */     private TableCellRenderer tableCellRenderer;
/*     */     
/*     */     public SortableHeaderRenderer(TableCellRenderer tableCellRenderer) {
/* 576 */       this.tableCellRenderer = tableCellRenderer;
/*     */     }
/*     */     
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column)
/*     */     {
/* 592 */       Component c = this.tableCellRenderer.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
/*     */       
/* 594 */       if ((c instanceof JLabel)) {
/* 595 */         JLabel l = (JLabel)c;
/* 596 */         l.setHorizontalTextPosition(2);
/* 597 */         int modelColumn = table.convertColumnIndexToModel(column);
/* 598 */         l.setIcon(TableSorter.this.getHeaderRendererIcon(modelColumn, l.getFont().getSize()));
/*     */       }
/*     */       
/* 601 */       return c;
/*     */     }
/*     */   }
/*     */   
/*     */   private static class Directive {
/*     */     private int column;
/*     */     private int direction;
/*     */     
/*     */     public Directive(int column, int direction) {
/* 610 */       this.column = column;
/* 611 */       this.direction = direction;
/*     */     }
/*     */   }
/*     */ }


/* Location:              /home/menick/Scrivania/ProcessMining/CNMining/CNMining.jar!/au/id/dodson/FilterableSortableTable/TableSorter.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       0.7.1
 */