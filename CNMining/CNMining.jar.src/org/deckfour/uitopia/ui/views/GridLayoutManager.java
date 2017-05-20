/*     */ package org.deckfour.uitopia.ui.views;
/*     */ 
/*     */ import java.awt.Component;
/*     */ import java.awt.Container;
/*     */ import java.awt.Dimension;
/*     */ import java.awt.LayoutManager;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class GridLayoutManager
/*     */   implements LayoutManager
/*     */ {
/*  46 */   private int xSpacing = 10;
/*  47 */   private int ySpacing = 10;
/*     */   
/*     */   public GridLayoutManager(int xSpacing, int ySpacing) {
/*  50 */     this.xSpacing = xSpacing;
/*  51 */     this.ySpacing = ySpacing;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public void addLayoutComponent(String name, Component comp) {}
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public void layoutContainer(Container parent)
/*     */   {
/*  66 */     int width = parent.getParent().getWidth();
/*  67 */     Component[] components = parent.getComponents();
/*  68 */     int y = this.ySpacing;
/*  69 */     int start = 0;
/*  70 */     while (start < components.length) {
/*  71 */       int space = this.xSpacing;
/*  72 */       int payloadWidth = 0;
/*  73 */       int end = start;
/*  74 */       int elements = 0;
/*  75 */       for (int i = start; i < components.length; i++) {
/*  76 */         space += components[i].getMinimumSize().width;
/*  77 */         space += this.xSpacing;
/*  78 */         if (space > width) {
/*     */           break;
/*     */         }
/*  81 */         elements++;
/*  82 */         payloadWidth += components[i].getMinimumSize().width;
/*  83 */         end++;
/*     */       }
/*     */       
/*  86 */       int x = this.xSpacing;
/*  87 */       int lineHeight = 0;
/*  88 */       for (int i = start; i < end; i++) {
/*  89 */         int w = components[i].getMinimumSize().width;
/*  90 */         int h = components[i].getMinimumSize().height;
/*  91 */         if (lineHeight < h) {
/*  92 */           lineHeight = h;
/*     */         }
/*  94 */         components[i].setBounds(x, y, w, h);
/*  95 */         x += w;
/*  96 */         x += this.xSpacing;
/*     */       }
/*  98 */       y += lineHeight;
/*  99 */       y += this.ySpacing;
/* 100 */       start = end;
/*     */     }
/* 102 */     Dimension dim = new Dimension(width, y);
/* 103 */     parent.setMinimumSize(dim);
/* 104 */     parent.setPreferredSize(dim);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */   public Dimension minimumLayoutSize(Container parent)
/*     */   {
/* 111 */     return preferredLayoutSize(parent);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */   public Dimension preferredLayoutSize(Container parent)
/*     */   {
/* 118 */     int width = parent.getWidth();
/* 119 */     if (width <= 0) {
/* 120 */       width = 600;
/*     */     }
/* 122 */     Component[] components = parent.getComponents();
/* 123 */     if (components.length == 0) {
/* 124 */       return new Dimension(100, 100);
/*     */     }
/* 126 */     int y = this.ySpacing;
/* 127 */     int lineWidth = this.xSpacing;
/* 128 */     int lineHeight = 0;
/* 129 */     for (int i = 0; i < components.length; i++) {
/* 130 */       if (lineHeight > components[i].getHeight()) {
/* 131 */         lineHeight = components[i].getHeight();
/*     */       }
/* 133 */       lineWidth += components[i].getWidth();
/* 134 */       lineWidth += this.xSpacing;
/* 135 */       if (lineWidth > width) {
/* 136 */         i--;
/* 137 */         lineWidth = 0;
/* 138 */         lineHeight += this.ySpacing;
/* 139 */         y += lineHeight;
/*     */       }
/*     */     }
/* 142 */     return new Dimension(width, y);
/*     */   }
/*     */   
/*     */   public void removeLayoutComponent(Component comp) {}
/*     */ }


/* Location:              /home/menick/Scrivania/ProcessMining/CNMining/CNMining.jar!/org/deckfour/uitopia/ui/views/GridLayoutManager.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */