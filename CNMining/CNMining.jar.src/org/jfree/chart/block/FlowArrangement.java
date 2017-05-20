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
/*     */ public class FlowArrangement
/*     */   implements Arrangement, Serializable
/*     */ {
/*     */   private static final long serialVersionUID = 4543632485478613800L;
/*     */   private HorizontalAlignment horizontalAlignment;
/*     */   private VerticalAlignment verticalAlignment;
/*     */   private double horizontalGap;
/*     */   private double verticalGap;
/*     */   
/*     */   public FlowArrangement()
/*     */   {
/*  79 */     this(HorizontalAlignment.CENTER, VerticalAlignment.CENTER, 2.0D, 2.0D);
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
/*     */   public FlowArrangement(HorizontalAlignment hAlign, VerticalAlignment vAlign, double hGap, double vGap)
/*     */   {
/*  92 */     this.horizontalAlignment = hAlign;
/*  93 */     this.verticalAlignment = vAlign;
/*  94 */     this.horizontalGap = hGap;
/*  95 */     this.verticalGap = vGap;
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
/* 126 */     LengthConstraintType w = constraint.getWidthConstraintType();
/* 127 */     LengthConstraintType h = constraint.getHeightConstraintType();
/* 128 */     if (w == LengthConstraintType.NONE) {
/* 129 */       if (h == LengthConstraintType.NONE) {
/* 130 */         return arrangeNN(container, g2);
/*     */       }
/* 132 */       if (h == LengthConstraintType.FIXED) {
/* 133 */         return arrangeNF(container, g2, constraint);
/*     */       }
/* 135 */       if (h == LengthConstraintType.RANGE) {
/* 136 */         throw new RuntimeException("Not implemented.");
/*     */       }
/*     */     }
/* 139 */     else if (w == LengthConstraintType.FIXED) {
/* 140 */       if (h == LengthConstraintType.NONE) {
/* 141 */         return arrangeFN(container, g2, constraint);
/*     */       }
/* 143 */       if (h == LengthConstraintType.FIXED) {
/* 144 */         return arrangeFF(container, g2, constraint);
/*     */       }
/* 146 */       if (h == LengthConstraintType.RANGE) {
/* 147 */         return arrangeFR(container, g2, constraint);
/*     */       }
/*     */     }
/* 150 */     else if (w == LengthConstraintType.RANGE) {
/* 151 */       if (h == LengthConstraintType.NONE) {
/* 152 */         return arrangeRN(container, g2, constraint);
/*     */       }
/* 154 */       if (h == LengthConstraintType.FIXED) {
/* 155 */         return arrangeRF(container, g2, constraint);
/*     */       }
/* 157 */       if (h == LengthConstraintType.RANGE) {
/* 158 */         return arrangeRR(container, g2, constraint);
/*     */       }
/*     */     }
/* 161 */     throw new RuntimeException("Unrecognised constraint type.");
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
/*     */   protected Size2D arrangeFN(BlockContainer container, Graphics2D g2, RectangleConstraint constraint)
/*     */   {
/* 178 */     List blocks = container.getBlocks();
/* 179 */     double width = constraint.getWidth();
/*     */     
/* 181 */     double x = 0.0D;
/* 182 */     double y = 0.0D;
/* 183 */     double maxHeight = 0.0D;
/* 184 */     List itemsInRow = new ArrayList();
/* 185 */     for (int i = 0; i < blocks.size(); i++) {
/* 186 */       Block block = (Block)blocks.get(i);
/* 187 */       Size2D size = block.arrange(g2, RectangleConstraint.NONE);
/* 188 */       if (x + size.width <= width) {
/* 189 */         itemsInRow.add(block);
/* 190 */         block.setBounds(new Rectangle2D.Double(x, y, size.width, size.height));
/*     */         
/*     */ 
/* 193 */         x = x + size.width + this.horizontalGap;
/* 194 */         maxHeight = Math.max(maxHeight, size.height);
/*     */ 
/*     */       }
/* 197 */       else if (itemsInRow.isEmpty())
/*     */       {
/* 199 */         block.setBounds(new Rectangle2D.Double(x, y, Math.min(size.width, width - x), size.height));
/*     */         
/*     */ 
/*     */ 
/*     */ 
/* 204 */         x = 0.0D;
/* 205 */         y = y + size.height + this.verticalGap;
/*     */       }
/*     */       else
/*     */       {
/* 209 */         itemsInRow.clear();
/* 210 */         x = 0.0D;
/* 211 */         y = y + maxHeight + this.verticalGap;
/* 212 */         maxHeight = size.height;
/* 213 */         block.setBounds(new Rectangle2D.Double(x, y, Math.min(size.width, width), size.height));
/*     */         
/*     */ 
/*     */ 
/*     */ 
/* 218 */         x = size.width + this.horizontalGap;
/* 219 */         itemsInRow.add(block);
/*     */       }
/*     */     }
/*     */     
/* 223 */     return new Size2D(constraint.getWidth(), y + maxHeight);
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
/*     */   protected Size2D arrangeFR(BlockContainer container, Graphics2D g2, RectangleConstraint constraint)
/*     */   {
/* 239 */     Size2D s = arrangeFN(container, g2, constraint);
/* 240 */     if (constraint.getHeightRange().contains(s.height)) {
/* 241 */       return s;
/*     */     }
/*     */     
/* 244 */     RectangleConstraint c = constraint.toFixedHeight(constraint.getHeightRange().constrain(s.getHeight()));
/*     */     
/*     */ 
/* 247 */     return arrangeFF(container, g2, c);
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
/*     */   protected Size2D arrangeFF(BlockContainer container, Graphics2D g2, RectangleConstraint constraint)
/*     */   {
/* 265 */     return arrangeFN(container, g2, constraint);
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
/* 283 */     Size2D s1 = arrangeNN(container, g2);
/* 284 */     if (constraint.getWidthRange().contains(s1.width)) {
/* 285 */       return s1;
/*     */     }
/*     */     
/* 288 */     RectangleConstraint c = constraint.toFixedWidth(constraint.getWidthRange().getUpperBound());
/*     */     
/*     */ 
/* 291 */     return arrangeFR(container, g2, c);
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
/* 308 */     Size2D s = arrangeNF(container, g2, constraint);
/* 309 */     if (constraint.getWidthRange().contains(s.width)) {
/* 310 */       return s;
/*     */     }
/*     */     
/* 313 */     RectangleConstraint c = constraint.toFixedWidth(constraint.getWidthRange().constrain(s.getWidth()));
/*     */     
/*     */ 
/* 316 */     return arrangeFF(container, g2, c);
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
/*     */   protected Size2D arrangeRN(BlockContainer container, Graphics2D g2, RectangleConstraint constraint)
/*     */   {
/* 334 */     Size2D s1 = arrangeNN(container, g2);
/* 335 */     if (constraint.getWidthRange().contains(s1.width)) {
/* 336 */       return s1;
/*     */     }
/*     */     
/* 339 */     RectangleConstraint c = constraint.toFixedWidth(constraint.getWidthRange().getUpperBound());
/*     */     
/*     */ 
/* 342 */     return arrangeFN(container, g2, c);
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
/* 356 */     double x = 0.0D;
/* 357 */     double width = 0.0D;
/* 358 */     double maxHeight = 0.0D;
/* 359 */     List blocks = container.getBlocks();
/* 360 */     int blockCount = blocks.size();
/* 361 */     if (blockCount > 0) {
/* 362 */       Size2D[] sizes = new Size2D[blocks.size()];
/* 363 */       for (int i = 0; i < blocks.size(); i++) {
/* 364 */         Block block = (Block)blocks.get(i);
/* 365 */         sizes[i] = block.arrange(g2, RectangleConstraint.NONE);
/* 366 */         width += sizes[i].getWidth();
/* 367 */         maxHeight = Math.max(sizes[i].height, maxHeight);
/* 368 */         block.setBounds(new Rectangle2D.Double(x, 0.0D, sizes[i].width, sizes[i].height));
/*     */         
/*     */ 
/*     */ 
/*     */ 
/* 373 */         x = x + sizes[i].width + this.horizontalGap;
/*     */       }
/* 375 */       if (blockCount > 1) {
/* 376 */         width += this.horizontalGap * (blockCount - 1);
/*     */       }
/* 378 */       if (this.verticalAlignment != VerticalAlignment.TOP) {
/* 379 */         for (int i = 0; i < blocks.size(); i++)
/*     */         {
/* 381 */           if (this.verticalAlignment != VerticalAlignment.CENTER)
/*     */           {
/*     */ 
/* 384 */             if (this.verticalAlignment != VerticalAlignment.BOTTOM) {}
/*     */           }
/*     */         }
/*     */       }
/*     */     }
/*     */     
/*     */ 
/* 391 */     return new Size2D(width, maxHeight);
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
/*     */   protected Size2D arrangeNF(BlockContainer container, Graphics2D g2, RectangleConstraint constraint)
/*     */   {
/* 407 */     return arrangeNN(container, g2);
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
/* 425 */     if (obj == this) {
/* 426 */       return true;
/*     */     }
/* 428 */     if (!(obj instanceof FlowArrangement)) {
/* 429 */       return false;
/*     */     }
/* 431 */     FlowArrangement that = (FlowArrangement)obj;
/* 432 */     if (this.horizontalAlignment != that.horizontalAlignment) {
/* 433 */       return false;
/*     */     }
/* 435 */     if (this.verticalAlignment != that.verticalAlignment) {
/* 436 */       return false;
/*     */     }
/* 438 */     if (this.horizontalGap != that.horizontalGap) {
/* 439 */       return false;
/*     */     }
/* 441 */     if (this.verticalGap != that.verticalGap) {
/* 442 */       return false;
/*     */     }
/* 444 */     return true;
/*     */   }
/*     */ }


/* Location:              /home/menick/Scrivania/ProcessMining/CNMining/CNMining.jar!/org/jfree/chart/block/FlowArrangement.class
 * Java compiler version: 3 (47.0)
 * JD-Core Version:       0.7.1
 */