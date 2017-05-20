/*     */ package org.jfree.chart.block;
/*     */ 
/*     */ import java.awt.Graphics2D;
/*     */ import java.awt.geom.Rectangle2D.Double;
/*     */ import java.io.Serializable;
/*     */ import java.util.Iterator;
/*     */ import java.util.List;
/*     */ import org.jfree.data.Range;
/*     */ import org.jfree.ui.Size2D;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class GridArrangement
/*     */   implements Arrangement, Serializable
/*     */ {
/*     */   private static final long serialVersionUID = -2563758090144655938L;
/*     */   private int rows;
/*     */   private int columns;
/*     */   
/*     */   public GridArrangement(int rows, int columns)
/*     */   {
/*  74 */     this.rows = rows;
/*  75 */     this.columns = columns;
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
/*     */   public Size2D arrange(BlockContainer container, Graphics2D g2, RectangleConstraint constraint)
/*     */   {
/* 103 */     LengthConstraintType w = constraint.getWidthConstraintType();
/* 104 */     LengthConstraintType h = constraint.getHeightConstraintType();
/* 105 */     if (w == LengthConstraintType.NONE) {
/* 106 */       if (h == LengthConstraintType.NONE) {
/* 107 */         return arrangeNN(container, g2);
/*     */       }
/* 109 */       if (h == LengthConstraintType.FIXED) {
/* 110 */         return arrangeNF(container, g2, constraint);
/*     */       }
/* 112 */       if (h == LengthConstraintType.RANGE)
/*     */       {
/* 114 */         return arrangeNR(container, g2, constraint);
/*     */       }
/*     */     }
/* 117 */     else if (w == LengthConstraintType.FIXED) {
/* 118 */       if (h == LengthConstraintType.NONE)
/*     */       {
/* 120 */         return arrangeFN(container, g2, constraint);
/*     */       }
/* 122 */       if (h == LengthConstraintType.FIXED) {
/* 123 */         return arrangeFF(container, g2, constraint);
/*     */       }
/* 125 */       if (h == LengthConstraintType.RANGE)
/*     */       {
/* 127 */         return arrangeFR(container, g2, constraint);
/*     */       }
/*     */     }
/* 130 */     else if (w == LengthConstraintType.RANGE)
/*     */     {
/* 132 */       if (h == LengthConstraintType.NONE)
/*     */       {
/* 134 */         return arrangeRN(container, g2, constraint);
/*     */       }
/* 136 */       if (h == LengthConstraintType.FIXED)
/*     */       {
/* 138 */         return arrangeRF(container, g2, constraint);
/*     */       }
/* 140 */       if (h == LengthConstraintType.RANGE) {
/* 141 */         return arrangeRR(container, g2, constraint);
/*     */       }
/*     */     }
/* 144 */     throw new RuntimeException("Should never get to here!");
/*     */   }
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
/* 156 */     double maxW = 0.0D;
/* 157 */     double maxH = 0.0D;
/* 158 */     List blocks = container.getBlocks();
/* 159 */     Iterator iterator = blocks.iterator();
/* 160 */     while (iterator.hasNext()) {
/* 161 */       Block b = (Block)iterator.next();
/* 162 */       if (b != null) {
/* 163 */         Size2D s = b.arrange(g2, RectangleConstraint.NONE);
/* 164 */         maxW = Math.max(maxW, s.width);
/* 165 */         maxH = Math.max(maxH, s.height);
/*     */       }
/*     */     }
/* 168 */     double width = this.columns * maxW;
/* 169 */     double height = this.rows * maxH;
/* 170 */     RectangleConstraint c = new RectangleConstraint(width, height);
/* 171 */     return arrangeFF(container, g2, c);
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
/*     */   protected Size2D arrangeFF(BlockContainer container, Graphics2D g2, RectangleConstraint constraint)
/*     */   {
/* 185 */     double width = constraint.getWidth() / this.columns;
/* 186 */     double height = constraint.getHeight() / this.rows;
/* 187 */     List blocks = container.getBlocks();
/* 188 */     for (int c = 0; c < this.columns; c++) {
/* 189 */       for (int r = 0; r < this.rows; r++) {
/* 190 */         int index = r * this.columns + c;
/* 191 */         if (index >= blocks.size()) {
/*     */           break;
/*     */         }
/* 194 */         Block b = (Block)blocks.get(index);
/* 195 */         if (b != null) {
/* 196 */           b.setBounds(new Rectangle2D.Double(c * width, r * height, width, height));
/*     */         }
/*     */       }
/*     */     }
/*     */     
/* 201 */     return new Size2D(this.columns * width, this.rows * height);
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
/*     */   protected Size2D arrangeFR(BlockContainer container, Graphics2D g2, RectangleConstraint constraint)
/*     */   {
/* 216 */     RectangleConstraint c1 = constraint.toUnconstrainedHeight();
/* 217 */     Size2D size1 = arrange(container, g2, c1);
/*     */     
/* 219 */     if (constraint.getHeightRange().contains(size1.getHeight())) {
/* 220 */       return size1;
/*     */     }
/*     */     
/* 223 */     double h = constraint.getHeightRange().constrain(size1.getHeight());
/* 224 */     RectangleConstraint c2 = constraint.toFixedHeight(h);
/* 225 */     return arrange(container, g2, c2);
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
/*     */   protected Size2D arrangeRF(BlockContainer container, Graphics2D g2, RectangleConstraint constraint)
/*     */   {
/* 241 */     RectangleConstraint c1 = constraint.toUnconstrainedWidth();
/* 242 */     Size2D size1 = arrange(container, g2, c1);
/*     */     
/* 244 */     if (constraint.getWidthRange().contains(size1.getWidth())) {
/* 245 */       return size1;
/*     */     }
/*     */     
/* 248 */     double w = constraint.getWidthRange().constrain(size1.getWidth());
/* 249 */     RectangleConstraint c2 = constraint.toFixedWidth(w);
/* 250 */     return arrange(container, g2, c2);
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
/*     */   protected Size2D arrangeRN(BlockContainer container, Graphics2D g2, RectangleConstraint constraint)
/*     */   {
/* 266 */     RectangleConstraint c1 = constraint.toUnconstrainedWidth();
/* 267 */     Size2D size1 = arrange(container, g2, c1);
/*     */     
/* 269 */     if (constraint.getWidthRange().contains(size1.getWidth())) {
/* 270 */       return size1;
/*     */     }
/*     */     
/* 273 */     double w = constraint.getWidthRange().constrain(size1.getWidth());
/* 274 */     RectangleConstraint c2 = constraint.toFixedWidth(w);
/* 275 */     return arrange(container, g2, c2);
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
/*     */   protected Size2D arrangeNR(BlockContainer container, Graphics2D g2, RectangleConstraint constraint)
/*     */   {
/* 291 */     RectangleConstraint c1 = constraint.toUnconstrainedHeight();
/* 292 */     Size2D size1 = arrange(container, g2, c1);
/*     */     
/* 294 */     if (constraint.getHeightRange().contains(size1.getHeight())) {
/* 295 */       return size1;
/*     */     }
/*     */     
/* 298 */     double h = constraint.getHeightRange().constrain(size1.getHeight());
/* 299 */     RectangleConstraint c2 = constraint.toFixedHeight(h);
/* 300 */     return arrange(container, g2, c2);
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
/*     */   protected Size2D arrangeRR(BlockContainer container, Graphics2D g2, RectangleConstraint constraint)
/*     */   {
/* 316 */     Size2D size1 = arrange(container, g2, RectangleConstraint.NONE);
/*     */     
/* 318 */     if (constraint.getWidthRange().contains(size1.getWidth())) {
/* 319 */       if (constraint.getHeightRange().contains(size1.getHeight())) {
/* 320 */         return size1;
/*     */       }
/*     */       
/*     */ 
/* 324 */       double h = constraint.getHeightRange().constrain(size1.getHeight());
/*     */       
/* 326 */       RectangleConstraint cc = new RectangleConstraint(size1.getWidth(), h);
/*     */       
/* 328 */       return arrangeFF(container, g2, cc);
/*     */     }
/*     */     
/*     */ 
/* 332 */     if (constraint.getHeightRange().contains(size1.getHeight()))
/*     */     {
/* 334 */       double w = constraint.getWidthRange().constrain(size1.getWidth());
/*     */       
/* 336 */       RectangleConstraint cc = new RectangleConstraint(w, size1.getHeight());
/*     */       
/* 338 */       return arrangeFF(container, g2, cc);
/*     */     }
/*     */     
/*     */ 
/* 342 */     double w = constraint.getWidthRange().constrain(size1.getWidth());
/*     */     
/* 344 */     double h = constraint.getHeightRange().constrain(size1.getHeight());
/*     */     
/* 346 */     RectangleConstraint cc = new RectangleConstraint(w, h);
/* 347 */     return arrangeFF(container, g2, cc);
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
/* 364 */     double width = constraint.getWidth() / this.columns;
/* 365 */     RectangleConstraint bc = constraint.toFixedWidth(width);
/* 366 */     List blocks = container.getBlocks();
/* 367 */     double maxH = 0.0D;
/* 368 */     for (int r = 0; r < this.rows; r++) {
/* 369 */       for (int c = 0; c < this.columns; c++) {
/* 370 */         int index = r * this.columns + c;
/* 371 */         if (index >= blocks.size()) {
/*     */           break;
/*     */         }
/* 374 */         Block b = (Block)blocks.get(index);
/* 375 */         if (b != null) {
/* 376 */           Size2D s = b.arrange(g2, bc);
/* 377 */           maxH = Math.max(maxH, s.getHeight());
/*     */         }
/*     */       }
/*     */     }
/* 381 */     RectangleConstraint cc = constraint.toFixedHeight(maxH * this.rows);
/* 382 */     return arrange(container, g2, cc);
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
/*     */   protected Size2D arrangeNF(BlockContainer container, Graphics2D g2, RectangleConstraint constraint)
/*     */   {
/* 397 */     double height = constraint.getHeight() / this.rows;
/* 398 */     RectangleConstraint bc = constraint.toFixedHeight(height);
/* 399 */     List blocks = container.getBlocks();
/* 400 */     double maxW = 0.0D;
/* 401 */     for (int r = 0; r < this.rows; r++) {
/* 402 */       for (int c = 0; c < this.columns; c++) {
/* 403 */         int index = r * this.columns + c;
/* 404 */         if (index >= blocks.size()) {
/*     */           break;
/*     */         }
/* 407 */         Block b = (Block)blocks.get(index);
/* 408 */         if (b != null) {
/* 409 */           Size2D s = b.arrange(g2, bc);
/* 410 */           maxW = Math.max(maxW, s.getWidth());
/*     */         }
/*     */       }
/*     */     }
/* 414 */     RectangleConstraint cc = constraint.toFixedWidth(maxW * this.columns);
/* 415 */     return arrange(container, g2, cc);
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
/* 433 */     if (obj == this) {
/* 434 */       return true;
/*     */     }
/* 436 */     if (!(obj instanceof GridArrangement)) {
/* 437 */       return false;
/*     */     }
/* 439 */     GridArrangement that = (GridArrangement)obj;
/* 440 */     if (this.columns != that.columns) {
/* 441 */       return false;
/*     */     }
/* 443 */     if (this.rows != that.rows) {
/* 444 */       return false;
/*     */     }
/* 446 */     return true;
/*     */   }
/*     */ }


/* Location:              /home/menick/Scrivania/ProcessMining/CNMining/CNMining.jar!/org/jfree/chart/block/GridArrangement.class
 * Java compiler version: 3 (47.0)
 * JD-Core Version:       0.7.1
 */