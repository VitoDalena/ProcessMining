/*     */ package org.jfree.chart.block;
/*     */ 
/*     */ import java.awt.Graphics2D;
/*     */ import java.awt.geom.Rectangle2D;
/*     */ import java.awt.geom.Rectangle2D.Double;
/*     */ import java.io.Serializable;
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
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class CenterArrangement
/*     */   implements Arrangement, Serializable
/*     */ {
/*     */   private static final long serialVersionUID = -353308149220382047L;
/*     */   
/*     */   public void add(Block block, Object key) {}
/*     */   
/*     */   public Size2D arrange(BlockContainer container, Graphics2D g2, RectangleConstraint constraint)
/*     */   {
/*  94 */     LengthConstraintType w = constraint.getWidthConstraintType();
/*  95 */     LengthConstraintType h = constraint.getHeightConstraintType();
/*  96 */     if (w == LengthConstraintType.NONE) {
/*  97 */       if (h == LengthConstraintType.NONE) {
/*  98 */         return arrangeNN(container, g2);
/*     */       }
/* 100 */       if (h == LengthConstraintType.FIXED) {
/* 101 */         throw new RuntimeException("Not implemented.");
/*     */       }
/* 103 */       if (h == LengthConstraintType.RANGE) {
/* 104 */         throw new RuntimeException("Not implemented.");
/*     */       }
/*     */     }
/* 107 */     else if (w == LengthConstraintType.FIXED) {
/* 108 */       if (h == LengthConstraintType.NONE) {
/* 109 */         return arrangeFN(container, g2, constraint);
/*     */       }
/* 111 */       if (h == LengthConstraintType.FIXED) {
/* 112 */         throw new RuntimeException("Not implemented.");
/*     */       }
/* 114 */       if (h == LengthConstraintType.RANGE) {
/* 115 */         throw new RuntimeException("Not implemented.");
/*     */       }
/*     */     }
/* 118 */     else if (w == LengthConstraintType.RANGE) {
/* 119 */       if (h == LengthConstraintType.NONE) {
/* 120 */         return arrangeRN(container, g2, constraint);
/*     */       }
/* 122 */       if (h == LengthConstraintType.FIXED) {
/* 123 */         return arrangeRF(container, g2, constraint);
/*     */       }
/* 125 */       if (h == LengthConstraintType.RANGE) {
/* 126 */         return arrangeRR(container, g2, constraint);
/*     */       }
/*     */     }
/* 129 */     throw new IllegalArgumentException("Unknown LengthConstraintType.");
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
/* 146 */     List blocks = container.getBlocks();
/* 147 */     Block b = (Block)blocks.get(0);
/* 148 */     Size2D s = b.arrange(g2, RectangleConstraint.NONE);
/* 149 */     double width = constraint.getWidth();
/* 150 */     Rectangle2D bounds = new Rectangle2D.Double((width - s.width) / 2.0D, 0.0D, s.width, s.height);
/*     */     
/* 152 */     b.setBounds(bounds);
/* 153 */     return new Size2D((width - s.width) / 2.0D, s.height);
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
/* 169 */     Size2D s = arrangeFN(container, g2, constraint);
/* 170 */     if (constraint.getHeightRange().contains(s.height)) {
/* 171 */       return s;
/*     */     }
/*     */     
/* 174 */     RectangleConstraint c = constraint.toFixedHeight(constraint.getHeightRange().constrain(s.getHeight()));
/*     */     
/* 176 */     return arrangeFF(container, g2, c);
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
/* 194 */     return arrangeFN(container, g2, constraint);
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
/* 212 */     Size2D s1 = arrangeNN(container, g2);
/* 213 */     if (constraint.getWidthRange().contains(s1.width)) {
/* 214 */       return s1;
/*     */     }
/*     */     
/* 217 */     RectangleConstraint c = constraint.toFixedWidth(constraint.getWidthRange().getUpperBound());
/*     */     
/* 219 */     return arrangeFR(container, g2, c);
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
/* 236 */     Size2D s = arrangeNF(container, g2, constraint);
/* 237 */     if (constraint.getWidthRange().contains(s.width)) {
/* 238 */       return s;
/*     */     }
/*     */     
/* 241 */     RectangleConstraint c = constraint.toFixedWidth(constraint.getWidthRange().constrain(s.getWidth()));
/*     */     
/* 243 */     return arrangeFF(container, g2, c);
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
/* 261 */     Size2D s1 = arrangeNN(container, g2);
/* 262 */     if (constraint.getWidthRange().contains(s1.width)) {
/* 263 */       return s1;
/*     */     }
/*     */     
/* 266 */     RectangleConstraint c = constraint.toFixedWidth(constraint.getWidthRange().getUpperBound());
/*     */     
/* 268 */     return arrangeFN(container, g2, c);
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
/* 282 */     List blocks = container.getBlocks();
/* 283 */     Block b = (Block)blocks.get(0);
/* 284 */     Size2D s = b.arrange(g2, RectangleConstraint.NONE);
/* 285 */     b.setBounds(new Rectangle2D.Double(0.0D, 0.0D, s.width, s.height));
/* 286 */     return new Size2D(s.width, s.height);
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
/* 302 */     return arrangeNN(container, g2);
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
/* 320 */     if (obj == this) {
/* 321 */       return true;
/*     */     }
/* 323 */     if (!(obj instanceof CenterArrangement)) {
/* 324 */       return false;
/*     */     }
/* 326 */     return true;
/*     */   }
/*     */ }


/* Location:              /home/menick/Scrivania/ProcessMining/CNMining/CNMining.jar!/org/jfree/chart/block/CenterArrangement.class
 * Java compiler version: 3 (47.0)
 * JD-Core Version:       0.7.1
 */