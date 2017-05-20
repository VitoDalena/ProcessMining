/*     */ package org.jfree.chart.block;
/*     */ 
/*     */ import java.awt.Graphics2D;
/*     */ import java.awt.geom.Rectangle2D;
/*     */ import java.io.Serializable;
/*     */ import org.jfree.ui.Size2D;
/*     */ import org.jfree.util.PublicCloneable;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class EmptyBlock
/*     */   extends AbstractBlock
/*     */   implements Block, Cloneable, PublicCloneable, Serializable
/*     */ {
/*     */   private static final long serialVersionUID = -4083197869412648579L;
/*     */   
/*     */   public EmptyBlock(double width, double height)
/*     */   {
/*  71 */     setWidth(width);
/*  72 */     setHeight(height);
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
/*  85 */     Size2D base = new Size2D(calculateTotalWidth(getWidth()), calculateTotalHeight(getHeight()));
/*     */     
/*  87 */     return constraint.calculateConstrainedSize(base);
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
/*  98 */     draw(g2, area, null);
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
/* 112 */     area = trimMargin(area);
/* 113 */     drawBorder(g2, area);
/* 114 */     return null;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public Object clone()
/*     */     throws CloneNotSupportedException
/*     */   {
/* 125 */     return super.clone();
/*     */   }
/*     */ }


/* Location:              /home/menick/Scrivania/ProcessMining/CNMining/CNMining.jar!/org/jfree/chart/block/EmptyBlock.class
 * Java compiler version: 3 (47.0)
 * JD-Core Version:       0.7.1
 */