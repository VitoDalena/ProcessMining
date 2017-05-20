/*     */ package org.jfree.chart.block;
/*     */ 
/*     */ import java.awt.Graphics2D;
/*     */ import java.awt.Paint;
/*     */ import java.awt.geom.Rectangle2D;
/*     */ import java.io.IOException;
/*     */ import java.io.ObjectInputStream;
/*     */ import java.io.ObjectOutputStream;
/*     */ import org.jfree.io.SerialUtilities;
/*     */ import org.jfree.ui.Size2D;
/*     */ import org.jfree.util.PaintUtilities;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class ColorBlock
/*     */   extends AbstractBlock
/*     */   implements Block
/*     */ {
/*     */   static final long serialVersionUID = 3383866145634010865L;
/*     */   private transient Paint paint;
/*     */   
/*     */   public ColorBlock(Paint paint, double width, double height)
/*     */   {
/*  78 */     if (paint == null) {
/*  79 */       throw new IllegalArgumentException("Null 'paint' argument.");
/*     */     }
/*  81 */     this.paint = paint;
/*  82 */     setWidth(width);
/*  83 */     setHeight(height);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public Paint getPaint()
/*     */   {
/*  94 */     return this.paint;
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
/*     */   public Size2D arrange(Graphics2D g2, RectangleConstraint constraint)
/*     */   {
/* 107 */     return new Size2D(calculateTotalWidth(getWidth()), calculateTotalHeight(getHeight()));
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public void draw(Graphics2D g2, Rectangle2D area)
/*     */   {
/* 118 */     area = trimMargin(area);
/* 119 */     drawBorder(g2, area);
/* 120 */     area = trimBorder(area);
/* 121 */     area = trimPadding(area);
/* 122 */     g2.setPaint(this.paint);
/* 123 */     g2.fill(area);
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
/*     */   public Object draw(Graphics2D g2, Rectangle2D area, Object params)
/*     */   {
/* 136 */     draw(g2, area);
/* 137 */     return null;
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
/* 148 */     if (obj == this) {
/* 149 */       return true;
/*     */     }
/* 151 */     if (!(obj instanceof ColorBlock)) {
/* 152 */       return false;
/*     */     }
/* 154 */     ColorBlock that = (ColorBlock)obj;
/* 155 */     if (!PaintUtilities.equal(this.paint, that.paint)) {
/* 156 */       return false;
/*     */     }
/* 158 */     return super.equals(obj);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   private void writeObject(ObjectOutputStream stream)
/*     */     throws IOException
/*     */   {
/* 169 */     stream.defaultWriteObject();
/* 170 */     SerialUtilities.writePaint(this.paint, stream);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   private void readObject(ObjectInputStream stream)
/*     */     throws IOException, ClassNotFoundException
/*     */   {
/* 183 */     stream.defaultReadObject();
/* 184 */     this.paint = SerialUtilities.readPaint(stream);
/*     */   }
/*     */ }


/* Location:              /home/menick/Scrivania/ProcessMining/CNMining/CNMining.jar!/org/jfree/chart/block/ColorBlock.class
 * Java compiler version: 3 (47.0)
 * JD-Core Version:       0.7.1
 */