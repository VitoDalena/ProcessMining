/*     */ package org.deckfour.uitopia.ui.util;
/*     */ 
/*     */ import java.awt.Dimension;
/*     */ import javax.swing.BorderFactory;
/*     */ import javax.swing.Box;
/*     */ import javax.swing.BoxLayout;
/*     */ import javax.swing.JComponent;
/*     */ import javax.swing.JPanel;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class ArrangementHelper
/*     */ {
/*     */   public static void setFixedSize(JComponent subject, int width, int height)
/*     */   {
/*  51 */     Dimension dim = new Dimension(width, height);
/*  52 */     subject.setMinimumSize(dim);
/*  53 */     subject.setMaximumSize(dim);
/*  54 */     subject.setPreferredSize(dim);
/*  55 */     subject.setSize(dim);
/*     */   }
/*     */   
/*     */   public static JComponent pushRight(JComponent subject) {
/*  59 */     JPanel enclosure = blankPanel(0);
/*  60 */     enclosure.add(Box.createHorizontalGlue());
/*  61 */     enclosure.add(subject);
/*  62 */     return enclosure;
/*     */   }
/*     */   
/*     */   public static JComponent pushLeft(JComponent subject) {
/*  66 */     JPanel enclosure = blankPanel(0);
/*  67 */     enclosure.add(subject);
/*  68 */     enclosure.add(Box.createHorizontalGlue());
/*  69 */     return enclosure;
/*     */   }
/*     */   
/*     */   public static JComponent centerHorizontally(JComponent subject) {
/*  73 */     JPanel enclosure = blankPanel(0);
/*  74 */     enclosure.add(Box.createHorizontalGlue());
/*  75 */     enclosure.add(subject);
/*  76 */     enclosure.add(Box.createHorizontalGlue());
/*  77 */     return enclosure;
/*     */   }
/*     */   
/*     */   public static JComponent pushDown(JComponent subject) {
/*  81 */     JPanel enclosure = blankPanel(1);
/*  82 */     enclosure.add(Box.createVerticalGlue());
/*  83 */     enclosure.add(subject);
/*  84 */     return enclosure;
/*     */   }
/*     */   
/*     */   public static JComponent pushUp(JComponent subject) {
/*  88 */     JPanel enclosure = blankPanel(1);
/*  89 */     enclosure.add(subject);
/*  90 */     enclosure.add(Box.createVerticalGlue());
/*  91 */     return enclosure;
/*     */   }
/*     */   
/*     */   public static JComponent centerVertically(JComponent subject) {
/*  95 */     JPanel enclosure = blankPanel(1);
/*  96 */     enclosure.add(Box.createVerticalGlue());
/*  97 */     enclosure.add(subject);
/*  98 */     enclosure.add(Box.createVerticalGlue());
/*  99 */     return enclosure;
/*     */   }
/*     */   
/*     */   private static JPanel blankPanel(int boxLayoutOrientation) {
/* 103 */     JPanel blank = new JPanel();
/* 104 */     blank.setOpaque(false);
/* 105 */     blank.setBorder(BorderFactory.createEmptyBorder());
/* 106 */     blank.setLayout(new BoxLayout(blank, boxLayoutOrientation));
/* 107 */     return blank;
/*     */   }
/*     */ }


/* Location:              /home/menick/Scrivania/ProcessMining/CNMining/CNMining.jar!/org/deckfour/uitopia/ui/util/ArrangementHelper.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */