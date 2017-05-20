/*     */ package org.jfree.chart.title;
/*     */ 
/*     */ import java.awt.Graphics2D;
/*     */ import java.awt.Paint;
/*     */ import java.awt.geom.Rectangle2D;
/*     */ import java.io.IOException;
/*     */ import java.io.ObjectInputStream;
/*     */ import java.io.ObjectOutputStream;
/*     */ import java.io.Serializable;
/*     */ import org.jfree.chart.block.BlockContainer;
/*     */ import org.jfree.chart.block.BorderArrangement;
/*     */ import org.jfree.chart.block.RectangleConstraint;
/*     */ import org.jfree.chart.event.TitleChangeEvent;
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
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class CompositeTitle
/*     */   extends Title
/*     */   implements Cloneable, Serializable
/*     */ {
/*     */   private static final long serialVersionUID = -6770854036232562290L;
/*     */   private transient Paint backgroundPaint;
/*     */   private BlockContainer container;
/*     */   
/*     */   public CompositeTitle()
/*     */   {
/*  88 */     this(new BlockContainer(new BorderArrangement()));
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public CompositeTitle(BlockContainer container)
/*     */   {
/*  97 */     if (container == null) {
/*  98 */       throw new IllegalArgumentException("Null 'container' argument.");
/*     */     }
/* 100 */     this.container = container;
/* 101 */     this.backgroundPaint = null;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public Paint getBackgroundPaint()
/*     */   {
/* 112 */     return this.backgroundPaint;
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
/*     */   public void setBackgroundPaint(Paint paint)
/*     */   {
/* 125 */     this.backgroundPaint = paint;
/* 126 */     notifyListeners(new TitleChangeEvent(this));
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public BlockContainer getContainer()
/*     */   {
/* 135 */     return this.container;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public void setTitleContainer(BlockContainer container)
/*     */   {
/* 144 */     if (container == null) {
/* 145 */       throw new IllegalArgumentException("Null 'container' argument.");
/*     */     }
/* 147 */     this.container = container;
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
/* 160 */     RectangleConstraint contentConstraint = toContentConstraint(constraint);
/* 161 */     Size2D contentSize = this.container.arrange(g2, contentConstraint);
/* 162 */     return new Size2D(calculateTotalWidth(contentSize.getWidth()), calculateTotalHeight(contentSize.getHeight()));
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public void draw(Graphics2D g2, Rectangle2D area)
/*     */   {
/* 174 */     draw(g2, area, null);
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
/* 187 */     area = trimMargin(area);
/* 188 */     drawBorder(g2, area);
/* 189 */     area = trimBorder(area);
/* 190 */     if (this.backgroundPaint != null) {
/* 191 */       g2.setPaint(this.backgroundPaint);
/* 192 */       g2.fill(area);
/*     */     }
/* 194 */     area = trimPadding(area);
/* 195 */     return this.container.draw(g2, area, params);
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
/* 206 */     if (obj == this) {
/* 207 */       return true;
/*     */     }
/* 209 */     if (!(obj instanceof CompositeTitle)) {
/* 210 */       return false;
/*     */     }
/* 212 */     CompositeTitle that = (CompositeTitle)obj;
/* 213 */     if (!this.container.equals(that.container)) {
/* 214 */       return false;
/*     */     }
/* 216 */     if (!PaintUtilities.equal(this.backgroundPaint, that.backgroundPaint)) {
/* 217 */       return false;
/*     */     }
/* 219 */     return super.equals(obj);
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
/* 230 */     stream.defaultWriteObject();
/* 231 */     SerialUtilities.writePaint(this.backgroundPaint, stream);
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
/* 244 */     stream.defaultReadObject();
/* 245 */     this.backgroundPaint = SerialUtilities.readPaint(stream);
/*     */   }
/*     */ }


/* Location:              /home/menick/Scrivania/ProcessMining/CNMining/CNMining.jar!/org/jfree/chart/title/CompositeTitle.class
 * Java compiler version: 3 (47.0)
 * JD-Core Version:       0.7.1
 */