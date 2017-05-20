/*     */ package org.jfree.chart.title;
/*     */ 
/*     */ import java.awt.FontMetrics;
/*     */ import java.awt.Graphics2D;
/*     */ import java.awt.geom.Rectangle2D;
/*     */ import org.jfree.chart.block.LengthConstraintType;
/*     */ import org.jfree.chart.block.RectangleConstraint;
/*     */ import org.jfree.data.Range;
/*     */ import org.jfree.text.TextUtilities;
/*     */ import org.jfree.ui.Size2D;
/*     */ import org.jfree.ui.TextAnchor;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class ShortTextTitle
/*     */   extends TextTitle
/*     */ {
/*     */   public ShortTextTitle(String text)
/*     */   {
/*  71 */     setText(text);
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
/*     */   public Size2D arrange(Graphics2D g2, RectangleConstraint constraint)
/*     */   {
/*  86 */     RectangleConstraint cc = toContentConstraint(constraint);
/*  87 */     LengthConstraintType w = cc.getWidthConstraintType();
/*  88 */     LengthConstraintType h = cc.getHeightConstraintType();
/*  89 */     Size2D contentSize = null;
/*  90 */     if (w == LengthConstraintType.NONE) {
/*  91 */       if (h == LengthConstraintType.NONE) {
/*  92 */         contentSize = arrangeNN(g2);
/*     */       } else {
/*  94 */         if (h == LengthConstraintType.RANGE) {
/*  95 */           throw new RuntimeException("Not yet implemented.");
/*     */         }
/*  97 */         if (h == LengthConstraintType.FIXED) {
/*  98 */           throw new RuntimeException("Not yet implemented.");
/*     */         }
/*     */       }
/* 101 */     } else if (w == LengthConstraintType.RANGE) {
/* 102 */       if (h == LengthConstraintType.NONE) {
/* 103 */         contentSize = arrangeRN(g2, cc.getWidthRange());
/*     */       }
/* 105 */       else if (h == LengthConstraintType.RANGE) {
/* 106 */         contentSize = arrangeRR(g2, cc.getWidthRange(), cc.getHeightRange());
/*     */ 
/*     */       }
/* 109 */       else if (h == LengthConstraintType.FIXED) {
/* 110 */         throw new RuntimeException("Not yet implemented.");
/*     */       }
/*     */     }
/* 113 */     else if (w == LengthConstraintType.FIXED) {
/* 114 */       if (h == LengthConstraintType.NONE) {
/* 115 */         contentSize = arrangeFN(g2, cc.getWidth());
/*     */       } else {
/* 117 */         if (h == LengthConstraintType.RANGE) {
/* 118 */           throw new RuntimeException("Not yet implemented.");
/*     */         }
/* 120 */         if (h == LengthConstraintType.FIXED)
/* 121 */           throw new RuntimeException("Not yet implemented.");
/*     */       }
/*     */     }
/* 124 */     if ((contentSize.width <= 0.0D) || (contentSize.height <= 0.0D)) {
/* 125 */       return new Size2D(0.0D, 0.0D);
/*     */     }
/*     */     
/* 128 */     return new Size2D(calculateTotalWidth(contentSize.getWidth()), calculateTotalHeight(contentSize.getHeight()));
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
/*     */   protected Size2D arrangeNN(Graphics2D g2)
/*     */   {
/* 142 */     Range max = new Range(0.0D, 3.4028234663852886E38D);
/* 143 */     return arrangeRR(g2, max, max);
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
/*     */   protected Size2D arrangeRN(Graphics2D g2, Range widthRange)
/*     */   {
/* 156 */     Size2D s = arrangeNN(g2);
/* 157 */     if (widthRange.contains(s.getWidth())) {
/* 158 */       return s;
/*     */     }
/* 160 */     double ww = widthRange.constrain(s.getWidth());
/* 161 */     return arrangeFN(g2, ww);
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
/*     */   protected Size2D arrangeFN(Graphics2D g2, double w)
/*     */   {
/* 176 */     g2.setFont(getFont());
/* 177 */     FontMetrics fm = g2.getFontMetrics(getFont());
/* 178 */     Rectangle2D bounds = TextUtilities.getTextBounds(getText(), g2, fm);
/* 179 */     if (bounds.getWidth() <= w) {
/* 180 */       return new Size2D(w, bounds.getHeight());
/*     */     }
/*     */     
/* 183 */     return new Size2D(0.0D, 0.0D);
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
/*     */   protected Size2D arrangeRR(Graphics2D g2, Range widthRange, Range heightRange)
/*     */   {
/* 199 */     g2.setFont(getFont());
/* 200 */     FontMetrics fm = g2.getFontMetrics(getFont());
/* 201 */     Rectangle2D bounds = TextUtilities.getTextBounds(getText(), g2, fm);
/* 202 */     if ((bounds.getWidth() <= widthRange.getUpperBound()) && (bounds.getHeight() <= heightRange.getUpperBound()))
/*     */     {
/* 204 */       return new Size2D(bounds.getWidth(), bounds.getHeight());
/*     */     }
/*     */     
/* 207 */     return new Size2D(0.0D, 0.0D);
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
/*     */   public Object draw(Graphics2D g2, Rectangle2D area, Object params)
/*     */   {
/* 221 */     if (area.isEmpty()) {
/* 222 */       return null;
/*     */     }
/* 224 */     area = trimMargin(area);
/* 225 */     drawBorder(g2, area);
/* 226 */     area = trimBorder(area);
/* 227 */     area = trimPadding(area);
/* 228 */     g2.setFont(getFont());
/* 229 */     g2.setPaint(getPaint());
/* 230 */     TextUtilities.drawAlignedString(getText(), g2, (float)area.getMinX(), (float)area.getMinY(), TextAnchor.TOP_LEFT);
/*     */     
/*     */ 
/* 233 */     return null;
/*     */   }
/*     */ }


/* Location:              /home/menick/Scrivania/ProcessMining/CNMining/CNMining.jar!/org/jfree/chart/title/ShortTextTitle.class
 * Java compiler version: 3 (47.0)
 * JD-Core Version:       0.7.1
 */