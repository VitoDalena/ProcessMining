/*     */ package org.jfree.chart.block;
/*     */ 
/*     */ import java.awt.Graphics2D;
/*     */ import java.awt.geom.Rectangle2D.Double;
/*     */ import java.io.Serializable;
/*     */ import org.jfree.data.Range;
/*     */ import org.jfree.ui.RectangleEdge;
/*     */ import org.jfree.ui.Size2D;
/*     */ import org.jfree.util.ObjectUtilities;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class BorderArrangement
/*     */   implements Arrangement, Serializable
/*     */ {
/*     */   private static final long serialVersionUID = 506071142274883745L;
/*     */   private Block centerBlock;
/*     */   private Block topBlock;
/*     */   private Block bottomBlock;
/*     */   private Block leftBlock;
/*     */   private Block rightBlock;
/*     */   
/*     */   public void add(Block block, Object key)
/*     */   {
/*  97 */     if (key == null) {
/*  98 */       this.centerBlock = block;
/*     */     }
/*     */     else {
/* 101 */       RectangleEdge edge = (RectangleEdge)key;
/* 102 */       if (edge == RectangleEdge.TOP) {
/* 103 */         this.topBlock = block;
/*     */       }
/* 105 */       else if (edge == RectangleEdge.BOTTOM) {
/* 106 */         this.bottomBlock = block;
/*     */       }
/* 108 */       else if (edge == RectangleEdge.LEFT) {
/* 109 */         this.leftBlock = block;
/*     */       }
/* 111 */       else if (edge == RectangleEdge.RIGHT) {
/* 112 */         this.rightBlock = block;
/*     */       }
/*     */     }
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
/*     */   public Size2D arrange(BlockContainer container, Graphics2D g2, RectangleConstraint constraint)
/*     */   {
/* 130 */     RectangleConstraint contentConstraint = container.toContentConstraint(constraint);
/*     */     
/* 132 */     Size2D contentSize = null;
/* 133 */     LengthConstraintType w = contentConstraint.getWidthConstraintType();
/* 134 */     LengthConstraintType h = contentConstraint.getHeightConstraintType();
/* 135 */     if (w == LengthConstraintType.NONE) {
/* 136 */       if (h == LengthConstraintType.NONE) {
/* 137 */         contentSize = arrangeNN(container, g2);
/*     */       } else {
/* 139 */         if (h == LengthConstraintType.FIXED) {
/* 140 */           throw new RuntimeException("Not implemented.");
/*     */         }
/* 142 */         if (h == LengthConstraintType.RANGE) {
/* 143 */           throw new RuntimeException("Not implemented.");
/*     */         }
/*     */       }
/* 146 */     } else if (w == LengthConstraintType.FIXED) {
/* 147 */       if (h == LengthConstraintType.NONE) {
/* 148 */         contentSize = arrangeFN(container, g2, constraint.getWidth());
/*     */       }
/* 150 */       else if (h == LengthConstraintType.FIXED) {
/* 151 */         contentSize = arrangeFF(container, g2, constraint);
/*     */       }
/* 153 */       else if (h == LengthConstraintType.RANGE) {
/* 154 */         contentSize = arrangeFR(container, g2, constraint);
/*     */       }
/*     */     }
/* 157 */     else if (w == LengthConstraintType.RANGE) {
/* 158 */       if (h == LengthConstraintType.NONE) {
/* 159 */         throw new RuntimeException("Not implemented.");
/*     */       }
/* 161 */       if (h == LengthConstraintType.FIXED) {
/* 162 */         throw new RuntimeException("Not implemented.");
/*     */       }
/* 164 */       if (h == LengthConstraintType.RANGE) {
/* 165 */         contentSize = arrangeRR(container, constraint.getWidthRange(), constraint.getHeightRange(), g2);
/*     */       }
/*     */     }
/*     */     
/* 169 */     return new Size2D(container.calculateTotalWidth(contentSize.getWidth()), container.calculateTotalHeight(contentSize.getHeight()));
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
/*     */   protected Size2D arrangeNN(BlockContainer container, Graphics2D g2)
/*     */   {
/* 182 */     double[] w = new double[5];
/* 183 */     double[] h = new double[5];
/* 184 */     if (this.topBlock != null) {
/* 185 */       Size2D size = this.topBlock.arrange(g2, RectangleConstraint.NONE);
/* 186 */       w[0] = size.width;
/* 187 */       h[0] = size.height;
/*     */     }
/* 189 */     if (this.bottomBlock != null) {
/* 190 */       Size2D size = this.bottomBlock.arrange(g2, RectangleConstraint.NONE);
/*     */       
/* 192 */       w[1] = size.width;
/* 193 */       h[1] = size.height;
/*     */     }
/* 195 */     if (this.leftBlock != null) {
/* 196 */       Size2D size = this.leftBlock.arrange(g2, RectangleConstraint.NONE);
/* 197 */       w[2] = size.width;
/* 198 */       h[2] = size.height;
/*     */     }
/* 200 */     if (this.rightBlock != null) {
/* 201 */       Size2D size = this.rightBlock.arrange(g2, RectangleConstraint.NONE);
/* 202 */       w[3] = size.width;
/* 203 */       h[3] = size.height;
/*     */     }
/*     */     
/* 206 */     h[2] = Math.max(h[2], h[3]);
/* 207 */     h[3] = h[2];
/*     */     
/* 209 */     if (this.centerBlock != null) {
/* 210 */       Size2D size = this.centerBlock.arrange(g2, RectangleConstraint.NONE);
/*     */       
/* 212 */       w[4] = size.width;
/* 213 */       h[4] = size.height;
/*     */     }
/* 215 */     double width = Math.max(w[0], Math.max(w[1], w[2] + w[4] + w[3]));
/* 216 */     double centerHeight = Math.max(h[2], Math.max(h[3], h[4]));
/* 217 */     double height = h[0] + h[1] + centerHeight;
/* 218 */     if (this.topBlock != null) {
/* 219 */       this.topBlock.setBounds(new Rectangle2D.Double(0.0D, 0.0D, width, h[0]));
/*     */     }
/*     */     
/* 222 */     if (this.bottomBlock != null) {
/* 223 */       this.bottomBlock.setBounds(new Rectangle2D.Double(0.0D, height - h[1], width, h[1]));
/*     */     }
/*     */     
/* 226 */     if (this.leftBlock != null) {
/* 227 */       this.leftBlock.setBounds(new Rectangle2D.Double(0.0D, h[0], w[2], centerHeight));
/*     */     }
/*     */     
/* 230 */     if (this.rightBlock != null) {
/* 231 */       this.rightBlock.setBounds(new Rectangle2D.Double(width - w[3], h[0], w[3], centerHeight));
/*     */     }
/*     */     
/*     */ 
/* 235 */     if (this.centerBlock != null) {
/* 236 */       this.centerBlock.setBounds(new Rectangle2D.Double(w[2], h[0], width - w[2] - w[3], centerHeight));
/*     */     }
/*     */     
/* 239 */     return new Size2D(width, height);
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
/*     */   protected Size2D arrangeFR(BlockContainer container, Graphics2D g2, RectangleConstraint constraint)
/*     */   {
/* 253 */     Size2D size1 = arrangeFN(container, g2, constraint.getWidth());
/* 254 */     if (constraint.getHeightRange().contains(size1.getHeight())) {
/* 255 */       return size1;
/*     */     }
/*     */     
/* 258 */     double h = constraint.getHeightRange().constrain(size1.getHeight());
/* 259 */     RectangleConstraint c2 = constraint.toFixedHeight(h);
/* 260 */     return arrange(container, g2, c2);
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
/*     */   protected Size2D arrangeFN(BlockContainer container, Graphics2D g2, double width)
/*     */   {
/* 276 */     double[] w = new double[5];
/* 277 */     double[] h = new double[5];
/* 278 */     RectangleConstraint c1 = new RectangleConstraint(width, null, LengthConstraintType.FIXED, 0.0D, null, LengthConstraintType.NONE);
/*     */     
/*     */ 
/* 281 */     if (this.topBlock != null) {
/* 282 */       Size2D size = this.topBlock.arrange(g2, c1);
/* 283 */       w[0] = size.width;
/* 284 */       h[0] = size.height;
/*     */     }
/* 286 */     if (this.bottomBlock != null) {
/* 287 */       Size2D size = this.bottomBlock.arrange(g2, c1);
/* 288 */       w[1] = size.width;
/* 289 */       h[1] = size.height;
/*     */     }
/* 291 */     RectangleConstraint c2 = new RectangleConstraint(0.0D, new Range(0.0D, width), LengthConstraintType.RANGE, 0.0D, null, LengthConstraintType.NONE);
/*     */     
/*     */ 
/* 294 */     if (this.leftBlock != null) {
/* 295 */       Size2D size = this.leftBlock.arrange(g2, c2);
/* 296 */       w[2] = size.width;
/* 297 */       h[2] = size.height;
/*     */     }
/* 299 */     if (this.rightBlock != null) {
/* 300 */       double maxW = Math.max(width - w[2], 0.0D);
/* 301 */       RectangleConstraint c3 = new RectangleConstraint(0.0D, new Range(Math.min(w[2], maxW), maxW), LengthConstraintType.RANGE, 0.0D, null, LengthConstraintType.NONE);
/*     */       
/*     */ 
/*     */ 
/* 305 */       Size2D size = this.rightBlock.arrange(g2, c3);
/* 306 */       w[3] = size.width;
/* 307 */       h[3] = size.height;
/*     */     }
/*     */     
/* 310 */     h[2] = Math.max(h[2], h[3]);
/* 311 */     h[3] = h[2];
/*     */     
/* 313 */     if (this.centerBlock != null) {
/* 314 */       RectangleConstraint c4 = new RectangleConstraint(width - w[2] - w[3], null, LengthConstraintType.FIXED, 0.0D, null, LengthConstraintType.NONE);
/*     */       
/*     */ 
/* 317 */       Size2D size = this.centerBlock.arrange(g2, c4);
/* 318 */       w[4] = size.width;
/* 319 */       h[4] = size.height;
/*     */     }
/* 321 */     double height = h[0] + h[1] + Math.max(h[2], Math.max(h[3], h[4]));
/* 322 */     return arrange(container, g2, new RectangleConstraint(width, height));
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
/*     */   protected Size2D arrangeRR(BlockContainer container, Range widthRange, Range heightRange, Graphics2D g2)
/*     */   {
/* 339 */     double[] w = new double[5];
/* 340 */     double[] h = new double[5];
/* 341 */     if (this.topBlock != null) {
/* 342 */       RectangleConstraint c1 = new RectangleConstraint(widthRange, heightRange);
/*     */       
/* 344 */       Size2D size = this.topBlock.arrange(g2, c1);
/* 345 */       w[0] = size.width;
/* 346 */       h[0] = size.height;
/*     */     }
/* 348 */     if (this.bottomBlock != null) {
/* 349 */       Range heightRange2 = Range.shift(heightRange, -h[0], false);
/* 350 */       RectangleConstraint c2 = new RectangleConstraint(widthRange, heightRange2);
/*     */       
/* 352 */       Size2D size = this.bottomBlock.arrange(g2, c2);
/* 353 */       w[1] = size.width;
/* 354 */       h[1] = size.height;
/*     */     }
/* 356 */     Range heightRange3 = Range.shift(heightRange, -(h[0] + h[1]));
/* 357 */     if (this.leftBlock != null) {
/* 358 */       RectangleConstraint c3 = new RectangleConstraint(widthRange, heightRange3);
/*     */       
/* 360 */       Size2D size = this.leftBlock.arrange(g2, c3);
/* 361 */       w[2] = size.width;
/* 362 */       h[2] = size.height;
/*     */     }
/* 364 */     Range widthRange2 = Range.shift(widthRange, -w[2], false);
/* 365 */     if (this.rightBlock != null) {
/* 366 */       RectangleConstraint c4 = new RectangleConstraint(widthRange2, heightRange3);
/*     */       
/* 368 */       Size2D size = this.rightBlock.arrange(g2, c4);
/* 369 */       w[3] = size.width;
/* 370 */       h[3] = size.height;
/*     */     }
/*     */     
/* 373 */     h[2] = Math.max(h[2], h[3]);
/* 374 */     h[3] = h[2];
/* 375 */     Range widthRange3 = Range.shift(widthRange, -(w[2] + w[3]), false);
/* 376 */     if (this.centerBlock != null) {
/* 377 */       RectangleConstraint c5 = new RectangleConstraint(widthRange3, heightRange3);
/*     */       
/* 379 */       Size2D size = this.centerBlock.arrange(g2, c5);
/* 380 */       w[4] = size.width;
/* 381 */       h[4] = size.height;
/*     */     }
/* 383 */     double width = Math.max(w[0], Math.max(w[1], w[2] + w[4] + w[3]));
/* 384 */     double height = h[0] + h[1] + Math.max(h[2], Math.max(h[3], h[4]));
/* 385 */     if (this.topBlock != null) {
/* 386 */       this.topBlock.setBounds(new Rectangle2D.Double(0.0D, 0.0D, width, h[0]));
/*     */     }
/*     */     
/* 389 */     if (this.bottomBlock != null) {
/* 390 */       this.bottomBlock.setBounds(new Rectangle2D.Double(0.0D, height - h[1], width, h[1]));
/*     */     }
/*     */     
/* 393 */     if (this.leftBlock != null) {
/* 394 */       this.leftBlock.setBounds(new Rectangle2D.Double(0.0D, h[0], w[2], h[2]));
/*     */     }
/*     */     
/* 397 */     if (this.rightBlock != null) {
/* 398 */       this.rightBlock.setBounds(new Rectangle2D.Double(width - w[3], h[0], w[3], h[3]));
/*     */     }
/*     */     
/*     */ 
/* 402 */     if (this.centerBlock != null) {
/* 403 */       this.centerBlock.setBounds(new Rectangle2D.Double(w[2], h[0], width - w[2] - w[3], height - h[0] - h[1]));
/*     */     }
/*     */     
/* 406 */     return new Size2D(width, height);
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
/* 420 */     double[] w = new double[5];
/* 421 */     double[] h = new double[5];
/* 422 */     w[0] = constraint.getWidth();
/* 423 */     if (this.topBlock != null) {
/* 424 */       RectangleConstraint c1 = new RectangleConstraint(w[0], null, LengthConstraintType.FIXED, 0.0D, new Range(0.0D, constraint.getHeight()), LengthConstraintType.RANGE);
/*     */       
/*     */ 
/*     */ 
/* 428 */       Size2D size = this.topBlock.arrange(g2, c1);
/* 429 */       h[0] = size.height;
/*     */     }
/* 431 */     w[1] = w[0];
/* 432 */     if (this.bottomBlock != null) {
/* 433 */       RectangleConstraint c2 = new RectangleConstraint(w[0], null, LengthConstraintType.FIXED, 0.0D, new Range(0.0D, constraint.getHeight() - h[0]), LengthConstraintType.RANGE);
/*     */       
/*     */ 
/* 436 */       Size2D size = this.bottomBlock.arrange(g2, c2);
/* 437 */       h[1] = size.height;
/*     */     }
/* 439 */     h[2] = (constraint.getHeight() - h[1] - h[0]);
/* 440 */     if (this.leftBlock != null) {
/* 441 */       RectangleConstraint c3 = new RectangleConstraint(0.0D, new Range(0.0D, constraint.getWidth()), LengthConstraintType.RANGE, h[2], null, LengthConstraintType.FIXED);
/*     */       
/*     */ 
/*     */ 
/* 445 */       Size2D size = this.leftBlock.arrange(g2, c3);
/* 446 */       w[2] = size.width;
/*     */     }
/* 448 */     h[3] = h[2];
/* 449 */     if (this.rightBlock != null) {
/* 450 */       RectangleConstraint c4 = new RectangleConstraint(0.0D, new Range(0.0D, Math.max(constraint.getWidth() - w[2], 0.0D)), LengthConstraintType.RANGE, h[2], null, LengthConstraintType.FIXED);
/*     */       
/*     */ 
/*     */ 
/* 454 */       Size2D size = this.rightBlock.arrange(g2, c4);
/* 455 */       w[3] = size.width;
/*     */     }
/* 457 */     h[4] = h[2];
/* 458 */     w[4] = (constraint.getWidth() - w[3] - w[2]);
/* 459 */     RectangleConstraint c5 = new RectangleConstraint(w[4], h[4]);
/* 460 */     if (this.centerBlock != null) {
/* 461 */       this.centerBlock.arrange(g2, c5);
/*     */     }
/*     */     
/* 464 */     if (this.topBlock != null) {
/* 465 */       this.topBlock.setBounds(new Rectangle2D.Double(0.0D, 0.0D, w[0], h[0]));
/*     */     }
/*     */     
/* 468 */     if (this.bottomBlock != null) {
/* 469 */       this.bottomBlock.setBounds(new Rectangle2D.Double(0.0D, h[0] + h[2], w[1], h[1]));
/*     */     }
/*     */     
/* 472 */     if (this.leftBlock != null) {
/* 473 */       this.leftBlock.setBounds(new Rectangle2D.Double(0.0D, h[0], w[2], h[2]));
/*     */     }
/*     */     
/* 476 */     if (this.rightBlock != null) {
/* 477 */       this.rightBlock.setBounds(new Rectangle2D.Double(w[2] + w[4], h[0], w[3], h[3]));
/*     */     }
/*     */     
/* 480 */     if (this.centerBlock != null) {
/* 481 */       this.centerBlock.setBounds(new Rectangle2D.Double(w[2], h[0], w[4], h[4]));
/*     */     }
/*     */     
/* 484 */     return new Size2D(constraint.getWidth(), constraint.getHeight());
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */   public void clear()
/*     */   {
/* 491 */     this.centerBlock = null;
/* 492 */     this.topBlock = null;
/* 493 */     this.bottomBlock = null;
/* 494 */     this.leftBlock = null;
/* 495 */     this.rightBlock = null;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public boolean equals(Object obj)
/*     */   {
/* 506 */     if (obj == this) {
/* 507 */       return true;
/*     */     }
/* 509 */     if (!(obj instanceof BorderArrangement)) {
/* 510 */       return false;
/*     */     }
/* 512 */     BorderArrangement that = (BorderArrangement)obj;
/* 513 */     if (!ObjectUtilities.equal(this.topBlock, that.topBlock)) {
/* 514 */       return false;
/*     */     }
/* 516 */     if (!ObjectUtilities.equal(this.bottomBlock, that.bottomBlock)) {
/* 517 */       return false;
/*     */     }
/* 519 */     if (!ObjectUtilities.equal(this.leftBlock, that.leftBlock)) {
/* 520 */       return false;
/*     */     }
/* 522 */     if (!ObjectUtilities.equal(this.rightBlock, that.rightBlock)) {
/* 523 */       return false;
/*     */     }
/* 525 */     if (!ObjectUtilities.equal(this.centerBlock, that.centerBlock)) {
/* 526 */       return false;
/*     */     }
/* 528 */     return true;
/*     */   }
/*     */ }


/* Location:              /home/menick/Scrivania/ProcessMining/CNMining/CNMining.jar!/org/jfree/chart/block/BorderArrangement.class
 * Java compiler version: 3 (47.0)
 * JD-Core Version:       0.7.1
 */