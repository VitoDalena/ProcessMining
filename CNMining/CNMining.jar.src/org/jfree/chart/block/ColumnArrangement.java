/*     */ package org.jfree.chart.block;
/*     */ 
/*     */ import java.awt.Graphics2D;
/*     */ import java.awt.geom.Rectangle2D.Double;
/*     */ import java.io.Serializable;
/*     */ import java.util.ArrayList;
/*     */ import java.util.List;
/*     */ import org.jfree.data.Range;
/*     */ import org.jfree.ui.HorizontalAlignment;
/*     */ import org.jfree.ui.Size2D;
/*     */ import org.jfree.ui.VerticalAlignment;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class ColumnArrangement
/*     */   implements Arrangement, Serializable
/*     */ {
/*     */   private static final long serialVersionUID = -5315388482898581555L;
/*     */   private HorizontalAlignment horizontalAlignment;
/*     */   private VerticalAlignment verticalAlignment;
/*     */   private double horizontalGap;
/*     */   private double verticalGap;
/*     */   
/*     */   public ColumnArrangement() {}
/*     */   
/*     */   public ColumnArrangement(HorizontalAlignment hAlign, VerticalAlignment vAlign, double hGap, double vGap)
/*     */   {
/*  91 */     this.horizontalAlignment = hAlign;
/*  92 */     this.verticalAlignment = vAlign;
/*  93 */     this.horizontalGap = hGap;
/*  94 */     this.verticalGap = vGap;
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
/*     */   public void add(Block block, Object key) {}
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public Size2D arrange(BlockContainer container, Graphics2D g2, RectangleConstraint constraint)
/*     */   {
/* 125 */     LengthConstraintType w = constraint.getWidthConstraintType();
/* 126 */     LengthConstraintType h = constraint.getHeightConstraintType();
/* 127 */     if (w == LengthConstraintType.NONE) {
/* 128 */       if (h == LengthConstraintType.NONE) {
/* 129 */         return arrangeNN(container, g2);
/*     */       }
/* 131 */       if (h == LengthConstraintType.FIXED) {
/* 132 */         throw new RuntimeException("Not implemented.");
/*     */       }
/* 134 */       if (h == LengthConstraintType.RANGE) {
/* 135 */         throw new RuntimeException("Not implemented.");
/*     */       }
/*     */     }
/* 138 */     else if (w == LengthConstraintType.FIXED) {
/* 139 */       if (h == LengthConstraintType.NONE) {
/* 140 */         throw new RuntimeException("Not implemented.");
/*     */       }
/* 142 */       if (h == LengthConstraintType.FIXED) {
/* 143 */         return arrangeFF(container, g2, constraint);
/*     */       }
/* 145 */       if (h == LengthConstraintType.RANGE) {
/* 146 */         throw new RuntimeException("Not implemented.");
/*     */       }
/*     */     }
/* 149 */     else if (w == LengthConstraintType.RANGE) {
/* 150 */       if (h == LengthConstraintType.NONE) {
/* 151 */         throw new RuntimeException("Not implemented.");
/*     */       }
/* 153 */       if (h == LengthConstraintType.FIXED) {
/* 154 */         return arrangeRF(container, g2, constraint);
/*     */       }
/* 156 */       if (h == LengthConstraintType.RANGE) {
/* 157 */         return arrangeRR(container, g2, constraint);
/*     */       }
/*     */     }
/* 160 */     return new Size2D();
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
/*     */   protected Size2D arrangeFF(BlockContainer container, Graphics2D g2, RectangleConstraint constraint)
/*     */   {
/* 179 */     return arrangeNF(container, g2, constraint);
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
/*     */   protected Size2D arrangeNF(BlockContainer container, Graphics2D g2, RectangleConstraint constraint)
/*     */   {
/* 197 */     List blocks = container.getBlocks();
/*     */     
/* 199 */     double height = constraint.getHeight();
/* 200 */     if (height <= 0.0D) {
/* 201 */       height = Double.POSITIVE_INFINITY;
/*     */     }
/*     */     
/* 204 */     double x = 0.0D;
/* 205 */     double y = 0.0D;
/* 206 */     double maxWidth = 0.0D;
/* 207 */     List itemsInColumn = new ArrayList();
/* 208 */     for (int i = 0; i < blocks.size(); i++) {
/* 209 */       Block block = (Block)blocks.get(i);
/* 210 */       Size2D size = block.arrange(g2, RectangleConstraint.NONE);
/* 211 */       if (y + size.height <= height) {
/* 212 */         itemsInColumn.add(block);
/* 213 */         block.setBounds(new Rectangle2D.Double(x, y, size.width, size.height));
/*     */         
/*     */ 
/* 216 */         y = y + size.height + this.verticalGap;
/* 217 */         maxWidth = Math.max(maxWidth, size.width);
/*     */ 
/*     */       }
/* 220 */       else if (itemsInColumn.isEmpty())
/*     */       {
/* 222 */         block.setBounds(new Rectangle2D.Double(x, y, size.width, Math.min(size.height, height - y)));
/*     */         
/*     */ 
/*     */ 
/*     */ 
/* 227 */         y = 0.0D;
/* 228 */         x = x + size.width + this.horizontalGap;
/*     */       }
/*     */       else
/*     */       {
/* 232 */         itemsInColumn.clear();
/* 233 */         x = x + maxWidth + this.horizontalGap;
/* 234 */         y = 0.0D;
/* 235 */         maxWidth = size.width;
/* 236 */         block.setBounds(new Rectangle2D.Double(x, y, size.width, Math.min(size.height, height)));
/*     */         
/*     */ 
/*     */ 
/*     */ 
/* 241 */         y = size.height + this.verticalGap;
/* 242 */         itemsInColumn.add(block);
/*     */       }
/*     */     }
/*     */     
/* 246 */     return new Size2D(x + maxWidth, constraint.getHeight());
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
/*     */   protected Size2D arrangeRR(BlockContainer container, Graphics2D g2, RectangleConstraint constraint)
/*     */   {
/* 264 */     Size2D s1 = arrangeNN(container, g2);
/* 265 */     if (constraint.getHeightRange().contains(s1.height)) {
/* 266 */       return s1;
/*     */     }
/*     */     
/* 269 */     RectangleConstraint c = constraint.toFixedHeight(constraint.getHeightRange().getUpperBound());
/*     */     
/*     */ 
/* 272 */     return arrangeRF(container, g2, c);
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
/*     */   protected Size2D arrangeRF(BlockContainer container, Graphics2D g2, RectangleConstraint constraint)
/*     */   {
/* 289 */     Size2D s = arrangeNF(container, g2, constraint);
/* 290 */     if (constraint.getWidthRange().contains(s.width)) {
/* 291 */       return s;
/*     */     }
/*     */     
/* 294 */     RectangleConstraint c = constraint.toFixedWidth(constraint.getWidthRange().constrain(s.getWidth()));
/*     */     
/*     */ 
/* 297 */     return arrangeFF(container, g2, c);
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
/*     */   protected Size2D arrangeNN(BlockContainer container, Graphics2D g2)
/*     */   {
/* 311 */     double y = 0.0D;
/* 312 */     double height = 0.0D;
/* 313 */     double maxWidth = 0.0D;
/* 314 */     List blocks = container.getBlocks();
/* 315 */     int blockCount = blocks.size();
/* 316 */     if (blockCount > 0) {
/* 317 */       Size2D[] sizes = new Size2D[blocks.size()];
/* 318 */       for (int i = 0; i < blocks.size(); i++) {
/* 319 */         Block block = (Block)blocks.get(i);
/* 320 */         sizes[i] = block.arrange(g2, RectangleConstraint.NONE);
/* 321 */         height += sizes[i].getHeight();
/* 322 */         maxWidth = Math.max(sizes[i].width, maxWidth);
/* 323 */         block.setBounds(new Rectangle2D.Double(0.0D, y, sizes[i].width, sizes[i].height));
/*     */         
/*     */ 
/*     */ 
/*     */ 
/* 328 */         y = y + sizes[i].height + this.verticalGap;
/*     */       }
/* 330 */       if (blockCount > 1) {
/* 331 */         height += this.verticalGap * (blockCount - 1);
/*     */       }
/* 333 */       if (this.horizontalAlignment != HorizontalAlignment.LEFT) {
/* 334 */         for (int i = 0; i < blocks.size(); i++)
/*     */         {
/* 336 */           if (this.horizontalAlignment != HorizontalAlignment.CENTER)
/*     */           {
/*     */ 
/*     */ 
/* 340 */             if (this.horizontalAlignment != HorizontalAlignment.RIGHT) {}
/*     */           }
/*     */         }
/*     */       }
/*     */     }
/*     */     
/*     */ 
/* 347 */     return new Size2D(maxWidth, height);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public void clear() {}
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public boolean equals(Object obj)
/*     */   {
/* 365 */     if (obj == this) {
/* 366 */       return true;
/*     */     }
/* 368 */     if (!(obj instanceof ColumnArrangement)) {
/* 369 */       return false;
/*     */     }
/* 371 */     ColumnArrangement that = (ColumnArrangement)obj;
/* 372 */     if (this.horizontalAlignment != that.horizontalAlignment) {
/* 373 */       return false;
/*     */     }
/* 375 */     if (this.verticalAlignment != that.verticalAlignment) {
/* 376 */       return false;
/*     */     }
/* 378 */     if (this.horizontalGap != that.horizontalGap) {
/* 379 */       return false;
/*     */     }
/* 381 */     if (this.verticalGap != that.verticalGap) {
/* 382 */       return false;
/*     */     }
/* 384 */     return true;
/*     */   }
/*     */ }


/* Location:              /home/menick/Scrivania/ProcessMining/CNMining/CNMining.jar!/org/jfree/chart/block/ColumnArrangement.class
 * Java compiler version: 3 (47.0)
 * JD-Core Version:       0.7.1
 */