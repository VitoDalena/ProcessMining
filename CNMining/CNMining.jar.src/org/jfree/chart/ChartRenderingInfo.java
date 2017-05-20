/*     */ package org.jfree.chart;
/*     */ 
/*     */ import java.awt.geom.Rectangle2D;
/*     */ import java.awt.geom.Rectangle2D.Double;
/*     */ import java.io.IOException;
/*     */ import java.io.ObjectInputStream;
/*     */ import java.io.ObjectOutputStream;
/*     */ import java.io.Serializable;
/*     */ import org.jfree.chart.entity.EntityCollection;
/*     */ import org.jfree.chart.entity.StandardEntityCollection;
/*     */ import org.jfree.chart.plot.PlotRenderingInfo;
/*     */ import org.jfree.io.SerialUtilities;
/*     */ import org.jfree.util.ObjectUtilities;
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
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class ChartRenderingInfo
/*     */   implements Cloneable, Serializable
/*     */ {
/*     */   private static final long serialVersionUID = 2751952018173406822L;
/*     */   private transient Rectangle2D chartArea;
/*     */   private PlotRenderingInfo plotInfo;
/*     */   private EntityCollection entities;
/*     */   
/*     */   public ChartRenderingInfo()
/*     */   {
/* 102 */     this(new StandardEntityCollection());
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public ChartRenderingInfo(EntityCollection entities)
/*     */   {
/* 114 */     this.chartArea = new Rectangle2D.Double();
/* 115 */     this.plotInfo = new PlotRenderingInfo(this);
/* 116 */     this.entities = entities;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public Rectangle2D getChartArea()
/*     */   {
/* 127 */     return this.chartArea;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public void setChartArea(Rectangle2D area)
/*     */   {
/* 138 */     this.chartArea.setRect(area);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public EntityCollection getEntityCollection()
/*     */   {
/* 149 */     return this.entities;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public void setEntityCollection(EntityCollection entities)
/*     */   {
/* 160 */     this.entities = entities;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */   public void clear()
/*     */   {
/* 167 */     this.chartArea.setRect(0.0D, 0.0D, 0.0D, 0.0D);
/* 168 */     this.plotInfo = new PlotRenderingInfo(this);
/* 169 */     if (this.entities != null) {
/* 170 */       this.entities.clear();
/*     */     }
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public PlotRenderingInfo getPlotInfo()
/*     */   {
/* 180 */     return this.plotInfo;
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
/* 191 */     if (obj == this) {
/* 192 */       return true;
/*     */     }
/* 194 */     if (!(obj instanceof ChartRenderingInfo)) {
/* 195 */       return false;
/*     */     }
/* 197 */     ChartRenderingInfo that = (ChartRenderingInfo)obj;
/* 198 */     if (!ObjectUtilities.equal(this.chartArea, that.chartArea)) {
/* 199 */       return false;
/*     */     }
/* 201 */     if (!ObjectUtilities.equal(this.plotInfo, that.plotInfo)) {
/* 202 */       return false;
/*     */     }
/* 204 */     if (!ObjectUtilities.equal(this.entities, that.entities)) {
/* 205 */       return false;
/*     */     }
/* 207 */     return true;
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
/* 218 */     ChartRenderingInfo clone = (ChartRenderingInfo)super.clone();
/* 219 */     if (this.chartArea != null) {
/* 220 */       clone.chartArea = ((Rectangle2D)this.chartArea.clone());
/*     */     }
/* 222 */     if ((this.entities instanceof PublicCloneable)) {
/* 223 */       PublicCloneable pc = (PublicCloneable)this.entities;
/* 224 */       clone.entities = ((EntityCollection)pc.clone());
/*     */     }
/* 226 */     return clone;
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
/* 237 */     stream.defaultWriteObject();
/* 238 */     SerialUtilities.writeShape(this.chartArea, stream);
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
/* 251 */     stream.defaultReadObject();
/* 252 */     this.chartArea = ((Rectangle2D)SerialUtilities.readShape(stream));
/*     */   }
/*     */ }


/* Location:              /home/menick/Scrivania/ProcessMining/CNMining/CNMining.jar!/org/jfree/chart/ChartRenderingInfo.class
 * Java compiler version: 3 (47.0)
 * JD-Core Version:       0.7.1
 */