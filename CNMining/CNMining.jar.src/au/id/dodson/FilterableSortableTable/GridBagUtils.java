/*     */ package au.id.dodson.FilterableSortableTable;
/*     */ 
/*     */ import java.awt.Component;
/*     */ import java.awt.GridBagConstraints;
/*     */ import java.awt.GridBagLayout;
/*     */ import java.awt.Insets;
/*     */ import java.awt.event.WindowAdapter;
/*     */ import java.awt.event.WindowEvent;
/*     */ import java.util.Iterator;
/*     */ import java.util.Vector;
/*     */ import javax.swing.JComponent;
/*     */ import javax.swing.JFrame;
/*     */ import javax.swing.JPanel;
/*     */ import javax.swing.JScrollPane;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class GridBagUtils
/*     */   extends JFrame
/*     */ {
/*  37 */   boolean inAnApplet = true;
/*  38 */   final boolean shouldFill = true;
/*  39 */   final boolean shouldWeightX = true;
/*     */   
/*     */   public GridBagUtils() {
/*  42 */     addWindowListener(new WindowAdapter() {
/*     */       public void windowClosing(WindowEvent e) {
/*  44 */         if (GridBagUtils.this.inAnApplet) {
/*  45 */           GridBagUtils.this.dispose();
/*     */         } else {
/*  47 */           System.exit(0);
/*     */         }
/*     */       }
/*     */     });
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public static JComponent layoutInGrid(Vector rows)
/*     */   {
/*  59 */     return layoutInGrid(rows, false);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public static JComponent layoutInGrid(Vector rows, boolean scroll)
/*     */   {
/*  69 */     GridBagLayout gridbag = new GridBagLayout();
/*  70 */     GridBagConstraints c = new GridBagConstraints();
/*  71 */     c.fill = 2;
/*  72 */     c.insets = new Insets(1, 1, 1, 1);
/*  73 */     JPanel panel = new JPanel(gridbag);
/*  74 */     int row = 0;
/*  75 */     Iterator rowItr = rows.iterator();
/*  76 */     while (rowItr.hasNext()) {
/*  77 */       Vector cols = (Vector)rowItr.next();
/*  78 */       int col = 0;
/*  79 */       Component componentPrevious = null;
/*  80 */       Iterator colItr = cols.iterator();
/*  81 */       while (colItr.hasNext()) {
/*  82 */         Component component = (Component)colItr.next();
/*  83 */         if (component == null) {
/*  84 */           component = new JPanel();
/*     */         }
/*  86 */         if (component == componentPrevious) {
/*  87 */           c.gridwidth += 1;
/*     */         } else {
/*  89 */           c.gridy = row;
/*  90 */           c.gridx = col;
/*  91 */           c.gridwidth = 1;
/*  92 */           panel.add(component);
/*     */         }
/*  94 */         gridbag.setConstraints(component, c);
/*  95 */         componentPrevious = component;
/*  96 */         col++;
/*     */       }
/*  98 */       row++;
/*     */     }
/* 100 */     if (scroll) {
/* 101 */       return new JScrollPane(panel);
/*     */     }
/* 103 */     return panel;
/*     */   }
/*     */ }


/* Location:              /home/menick/Scrivania/ProcessMining/CNMining/CNMining.jar!/au/id/dodson/FilterableSortableTable/GridBagUtils.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       0.7.1
 */