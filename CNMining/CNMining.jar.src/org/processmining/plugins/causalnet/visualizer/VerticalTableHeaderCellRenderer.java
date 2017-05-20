/*     */ package org.processmining.plugins.causalnet.visualizer;
/*     */ 
/*     */ import java.awt.Component;
/*     */ import java.awt.Graphics;
/*     */ import java.awt.Graphics2D;
/*     */ import java.util.List;
/*     */ import javax.swing.Icon;
/*     */ import javax.swing.JTable;
/*     */ import javax.swing.RowSorter;
/*     */ import javax.swing.RowSorter.SortKey;
/*     */ import javax.swing.SortOrder;
/*     */ import javax.swing.UIManager;
/*     */ import javax.swing.table.DefaultTableCellRenderer;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ class VerticalTableHeaderCellRenderer
/*     */   extends DefaultTableCellRenderer
/*     */ {
/*     */   private static final long serialVersionUID = -5792846837172592507L;
/*     */   
/*     */   public VerticalTableHeaderCellRenderer()
/*     */   {
/* 472 */     setOpaque(false);
/*     */     
/* 474 */     setHorizontalAlignment(2);
/* 475 */     setHorizontalTextPosition(0);
/* 476 */     setVerticalAlignment(0);
/* 477 */     setVerticalTextPosition(1);
/* 478 */     setUI(new VerticalLabelUI(false));
/*     */   }
/*     */   
/*     */   protected Icon getIcon(JTable table, int column)
/*     */   {
/* 483 */     RowSorter.SortKey sortKey = getSortKey(table, column);
/* 484 */     if ((sortKey != null) && (sortKey.getColumn() == column)) {
/* 485 */       SortOrder sortOrder = sortKey.getSortOrder();
/* 486 */       switch (sortOrder) {
/*     */       case ASCENDING: 
/* 488 */         return VerticalSortIcon.ASCENDING;
/*     */       case DESCENDING: 
/* 490 */         return VerticalSortIcon.DESCENDING;
/*     */       }
/*     */     }
/* 493 */     return null;
/*     */   }
/*     */   
/*     */   private static enum VerticalSortIcon implements Icon
/*     */   {
/* 498 */     ASCENDING,  DESCENDING;
/* 499 */     private Icon icon = UIManager.getIcon("Table.ascendingSortIcon");
/*     */     
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     private VerticalSortIcon() {}
/*     */     
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     public void paintIcon(Component c, Graphics g, int x, int y)
/*     */     {
/* 521 */       switch (VerticalTableHeaderCellRenderer.1.$SwitchMap$org$processmining$plugins$causalnet$visualizer$VerticalTableHeaderCellRenderer$VerticalSortIcon[ordinal()]) {
/*     */       case 1: 
/* 523 */         this.icon = UIManager.getIcon("Table.ascendingSortIcon");
/* 524 */         break;
/*     */       case 2: 
/* 526 */         this.icon = UIManager.getIcon("Table.descendingSortIcon");
/*     */       }
/*     */       
/*     */       
/* 530 */       int maxSide = Math.max(getIconWidth(), getIconHeight());
/* 531 */       Graphics2D g2 = (Graphics2D)g.create(x, y, maxSide, maxSide);
/* 532 */       g2.rotate(1.5707963267948966D);
/* 533 */       g2.translate(0, -maxSide);
/* 534 */       this.icon.paintIcon(c, g2, 0, 0);
/* 535 */       g2.dispose();
/*     */     }
/*     */     
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     public int getIconWidth()
/*     */     {
/* 544 */       return this.icon.getIconHeight();
/*     */     }
/*     */     
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     public int getIconHeight()
/*     */     {
/* 553 */       return this.icon.getIconWidth();
/*     */     }
/*     */   }
/*     */   
/*     */   protected RowSorter.SortKey getSortKey(JTable table, int column) {
/* 558 */     RowSorter<?> rowSorter = table.getRowSorter();
/* 559 */     if (rowSorter == null) {
/* 560 */       return null;
/*     */     }
/*     */     
/* 563 */     List<?> sortedColumns = rowSorter.getSortKeys();
/* 564 */     if (sortedColumns.size() > 0) {
/* 565 */       return (RowSorter.SortKey)sortedColumns.get(0);
/*     */     }
/* 567 */     return null;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */   public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column)
/*     */   {
/* 574 */     super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
/*     */     
/* 576 */     setIcon(getIcon(table, column));
/* 577 */     setBorder(null);
/* 578 */     return this;
/*     */   }
/*     */ }


/* Location:              /home/menick/Scrivania/ProcessMining/CNMining/CNMining.jar!/org/processmining/plugins/causalnet/visualizer/VerticalTableHeaderCellRenderer.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */