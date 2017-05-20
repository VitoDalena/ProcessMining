/*     */ package org.jfree.ui;
/*     */ 
/*     */ import java.awt.BorderLayout;
/*     */ import java.awt.Stroke;
/*     */ import java.awt.event.ActionEvent;
/*     */ import java.awt.event.ActionListener;
/*     */ import javax.swing.DefaultComboBoxModel;
/*     */ import javax.swing.JComboBox;
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
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class StrokeChooserPanel
/*     */   extends JPanel
/*     */ {
/*     */   private JComboBox selector;
/*     */   
/*     */   public StrokeChooserPanel(StrokeSample current, StrokeSample[] available)
/*     */   {
/*  76 */     setLayout(new BorderLayout());
/*     */     
/*     */ 
/*     */ 
/*  80 */     DefaultComboBoxModel model = new DefaultComboBoxModel();
/*  81 */     for (int i = 0; i < available.length; i++) {
/*  82 */       model.addElement(available[i].getStroke());
/*     */     }
/*  84 */     this.selector = new JComboBox(model);
/*  85 */     this.selector.setSelectedItem(current.getStroke());
/*  86 */     this.selector.setRenderer(new StrokeSample(null));
/*  87 */     add(this.selector);
/*     */     
/*  89 */     this.selector.addActionListener(new ActionListener() {
/*     */       public void actionPerformed(ActionEvent evt) {
/*  91 */         StrokeChooserPanel.this.getSelector().transferFocus();
/*     */       }
/*     */     });
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   protected final JComboBox getSelector()
/*     */   {
/* 103 */     return this.selector;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public Stroke getSelectedStroke()
/*     */   {
/* 112 */     return (Stroke)this.selector.getSelectedItem();
/*     */   }
/*     */ }


/* Location:              /home/menick/Scrivania/ProcessMining/CNMining/CNMining.jar!/org/jfree/ui/StrokeChooserPanel.class
 * Java compiler version: 2 (46.0)
 * JD-Core Version:       0.7.1
 */