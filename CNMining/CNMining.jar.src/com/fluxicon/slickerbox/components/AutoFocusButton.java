/*     */ package com.fluxicon.slickerbox.components;
/*     */ 
/*     */ import java.awt.event.HierarchyBoundsListener;
/*     */ import java.awt.event.HierarchyEvent;
/*     */ import java.awt.event.HierarchyListener;
/*     */ import java.beans.PropertyChangeEvent;
/*     */ import java.beans.PropertyChangeListener;
/*     */ import javax.swing.Action;
/*     */ import javax.swing.Icon;
/*     */ import javax.swing.event.AncestorEvent;
/*     */ import javax.swing.event.AncestorListener;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class AutoFocusButton
/*     */   extends SlickerButton
/*     */ {
/*     */   public AutoFocusButton(String text)
/*     */   {
/*  65 */     super(text);
/*  66 */     registerAutoFocusListener();
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   public AutoFocusButton()
/*     */   {
/*  74 */     registerAutoFocusListener();
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */   public AutoFocusButton(Action a)
/*     */   {
/*  81 */     super(a);
/*  82 */     registerAutoFocusListener();
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */   public AutoFocusButton(Icon icon)
/*     */   {
/*  89 */     super(icon);
/*  90 */     registerAutoFocusListener();
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   public AutoFocusButton(String text, Icon icon)
/*     */   {
/*  98 */     super(text, icon);
/*  99 */     registerAutoFocusListener();
/*     */   }
/*     */   
/*     */   protected void registerAutoFocusListener() {
/* 103 */     addAncestorListener(new AncestorListener()
/*     */     {
/* 105 */       public void ancestorAdded(AncestorEvent evt) { AutoFocusButton.this.requestFocusInWindow(); }
/*     */       
/*     */       public void ancestorMoved(AncestorEvent evt) {}
/*     */       
/* 109 */       public void ancestorRemoved(AncestorEvent evt) {} });
/* 110 */     addHierarchyListener(new HierarchyListener() {
/*     */       public void hierarchyChanged(HierarchyEvent arg0) {
/* 112 */         AutoFocusButton.this.requestFocusInWindow();
/*     */       }
/* 114 */     });
/* 115 */     addHierarchyBoundsListener(new HierarchyBoundsListener() {
/*     */       public void ancestorMoved(HierarchyEvent arg0) {
/* 117 */         AutoFocusButton.this.requestFocusInWindow();
/*     */       }
/*     */       
/* 120 */       public void ancestorResized(HierarchyEvent arg0) { AutoFocusButton.this.requestFocusInWindow();
/*     */       }
/* 122 */     });
/* 123 */     addPropertyChangeListener(new PropertyChangeListener() {
/*     */       public void propertyChange(PropertyChangeEvent arg0) {
/* 125 */         AutoFocusButton.this.requestFocusInWindow();
/*     */       }
/*     */     });
/*     */   }
/*     */ }


/* Location:              /home/menick/Scrivania/ProcessMining/CNMining/CNMining.jar!/com/fluxicon/slickerbox/components/AutoFocusButton.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       0.7.1
 */