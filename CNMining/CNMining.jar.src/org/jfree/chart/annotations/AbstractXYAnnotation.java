/*     */ package org.jfree.chart.annotations;
/*     */ 
/*     */ import java.awt.Graphics2D;
/*     */ import java.awt.Shape;
/*     */ import java.awt.geom.Rectangle2D;
/*     */ import org.jfree.chart.ChartRenderingInfo;
/*     */ import org.jfree.chart.axis.ValueAxis;
/*     */ import org.jfree.chart.entity.EntityCollection;
/*     */ import org.jfree.chart.entity.XYAnnotationEntity;
/*     */ import org.jfree.chart.plot.PlotRenderingInfo;
/*     */ import org.jfree.chart.plot.XYPlot;
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
/*     */ public abstract class AbstractXYAnnotation
/*     */   implements XYAnnotation
/*     */ {
/*     */   private String toolTipText;
/*     */   private String url;
/*     */   
/*     */   protected AbstractXYAnnotation()
/*     */   {
/*  72 */     this.toolTipText = null;
/*  73 */     this.url = null;
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
/*     */   public String getToolTipText()
/*     */   {
/*  86 */     return this.toolTipText;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public void setToolTipText(String text)
/*     */   {
/*  97 */     this.toolTipText = text;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public String getURL()
/*     */   {
/* 109 */     return this.url;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public void setURL(String url)
/*     */   {
/* 120 */     this.url = url;
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
/*     */   public abstract void draw(Graphics2D paramGraphics2D, XYPlot paramXYPlot, Rectangle2D paramRectangle2D, ValueAxis paramValueAxis1, ValueAxis paramValueAxis2, int paramInt, PlotRenderingInfo paramPlotRenderingInfo);
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   protected void addEntity(PlotRenderingInfo info, Shape hotspot, int rendererIndex, String toolTipText, String urlText)
/*     */   {
/* 153 */     if (info == null) {
/* 154 */       return;
/*     */     }
/* 156 */     EntityCollection entities = info.getOwner().getEntityCollection();
/* 157 */     if (entities == null) {
/* 158 */       return;
/*     */     }
/* 160 */     XYAnnotationEntity entity = new XYAnnotationEntity(hotspot, rendererIndex, toolTipText, urlText);
/*     */     
/* 162 */     entities.add(entity);
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
/* 173 */     if (obj == this) {
/* 174 */       return true;
/*     */     }
/* 176 */     if (!(obj instanceof AbstractXYAnnotation)) {
/* 177 */       return false;
/*     */     }
/* 179 */     AbstractXYAnnotation that = (AbstractXYAnnotation)obj;
/* 180 */     if (!ObjectUtilities.equal(this.toolTipText, that.toolTipText)) {
/* 181 */       return false;
/*     */     }
/* 183 */     if (!ObjectUtilities.equal(this.url, that.url)) {
/* 184 */       return false;
/*     */     }
/* 186 */     return true;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public int hashCode()
/*     */   {
/* 195 */     int result = 193;
/* 196 */     if (this.toolTipText != null) {
/* 197 */       result = 37 * result + this.toolTipText.hashCode();
/*     */     }
/* 199 */     if (this.url != null) {
/* 200 */       result = 37 * result + this.url.hashCode();
/*     */     }
/* 202 */     return result;
/*     */   }
/*     */ }


/* Location:              /home/menick/Scrivania/ProcessMining/CNMining/CNMining.jar!/org/jfree/chart/annotations/AbstractXYAnnotation.class
 * Java compiler version: 3 (47.0)
 * JD-Core Version:       0.7.1
 */