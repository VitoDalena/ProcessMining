/*     */ package org.jfree.ui;
/*     */ 
/*     */ import java.awt.Component;
/*     */ import java.text.DateFormat;
/*     */ import javax.swing.JTable;
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
/*     */ public class DateCellRenderer
/*     */   extends DefaultTableCellRenderer
/*     */ {
/*     */   private DateFormat formatter;
/*     */   
/*     */   public DateCellRenderer()
/*     */   {
/*  65 */     this(DateFormat.getDateTimeInstance());
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public DateCellRenderer(DateFormat formatter)
/*     */   {
/*  75 */     this.formatter = formatter;
/*  76 */     setHorizontalAlignment(0);
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
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column)
/*     */   {
/*  97 */     setFont(null);
/*  98 */     if (value != null) {
/*  99 */       setText(this.formatter.format(value));
/*     */     }
/*     */     else {
/* 102 */       setText("");
/*     */     }
/* 104 */     if (isSelected) {
/* 105 */       setBackground(table.getSelectionBackground());
/*     */     }
/*     */     else {
/* 108 */       setBackground(null);
/*     */     }
/* 110 */     return this;
/*     */   }
/*     */ }


/* Location:              /home/menick/Scrivania/ProcessMining/CNMining/CNMining.jar!/org/jfree/ui/DateCellRenderer.class
 * Java compiler version: 2 (46.0)
 * JD-Core Version:       0.7.1
 */