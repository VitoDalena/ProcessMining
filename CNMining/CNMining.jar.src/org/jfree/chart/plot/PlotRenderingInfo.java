/*     */ package org.jfree.chart.plot;
/*     */ 
/*     */ import java.awt.geom.Point2D;
/*     */ import java.awt.geom.Rectangle2D;
/*     */ import java.awt.geom.Rectangle2D.Double;
/*     */ import java.io.IOException;
/*     */ import java.io.ObjectInputStream;
/*     */ import java.io.ObjectOutputStream;
/*     */ import java.io.Serializable;
/*     */ import java.util.ArrayList;
/*     */ import java.util.List;
/*     */ import org.jfree.chart.ChartRenderingInfo;
/*     */ import org.jfree.io.SerialUtilities;
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
/*     */ public class PlotRenderingInfo
/*     */   implements Cloneable, Serializable
/*     */ {
/*     */   private static final long serialVersionUID = 8446720134379617220L;
/*     */   private ChartRenderingInfo owner;
/*     */   private transient Rectangle2D plotArea;
/*     */   private transient Rectangle2D dataArea;
/*     */   private List subplotInfo;
/*     */   
/*     */   public PlotRenderingInfo(ChartRenderingInfo owner)
/*     */   {
/*  89 */     this.owner = owner;
/*  90 */     this.dataArea = new Rectangle2D.Double();
/*  91 */     this.subplotInfo = new ArrayList();
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public ChartRenderingInfo getOwner()
/*     */   {
/* 100 */     return this.owner;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public Rectangle2D getPlotArea()
/*     */   {
/* 111 */     return this.plotArea;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public void setPlotArea(Rectangle2D area)
/*     */   {
/* 123 */     this.plotArea = area;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public Rectangle2D getDataArea()
/*     */   {
/* 134 */     return this.dataArea;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public void setDataArea(Rectangle2D area)
/*     */   {
/* 146 */     this.dataArea = area;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public int getSubplotCount()
/*     */   {
/* 155 */     return this.subplotInfo.size();
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public void addSubplotInfo(PlotRenderingInfo info)
/*     */   {
/* 166 */     this.subplotInfo.add(info);
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
/*     */   public PlotRenderingInfo getSubplotInfo(int index)
/*     */   {
/* 179 */     return (PlotRenderingInfo)this.subplotInfo.get(index);
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
/*     */   public int getSubplotIndex(Point2D source)
/*     */   {
/* 196 */     if (source == null) {
/* 197 */       throw new IllegalArgumentException("Null 'source' argument.");
/*     */     }
/* 199 */     int subplotCount = getSubplotCount();
/* 200 */     for (int i = 0; i < subplotCount; i++) {
/* 201 */       PlotRenderingInfo info = getSubplotInfo(i);
/* 202 */       Rectangle2D area = info.getDataArea();
/* 203 */       if (area.contains(source)) {
/* 204 */         return i;
/*     */       }
/*     */     }
/* 207 */     return -1;
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
/* 218 */     if (this == obj) {
/* 219 */       return true;
/*     */     }
/* 221 */     if (!(obj instanceof PlotRenderingInfo)) {
/* 222 */       return false;
/*     */     }
/* 224 */     PlotRenderingInfo that = (PlotRenderingInfo)obj;
/* 225 */     if (!ObjectUtilities.equal(this.dataArea, that.dataArea)) {
/* 226 */       return false;
/*     */     }
/* 228 */     if (!ObjectUtilities.equal(this.plotArea, that.plotArea)) {
/* 229 */       return false;
/*     */     }
/* 231 */     if (!ObjectUtilities.equal(this.subplotInfo, that.subplotInfo)) {
/* 232 */       return false;
/*     */     }
/* 234 */     return true;
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
/* 245 */     PlotRenderingInfo clone = (PlotRenderingInfo)super.clone();
/* 246 */     if (this.plotArea != null) {
/* 247 */       clone.plotArea = ((Rectangle2D)this.plotArea.clone());
/*     */     }
/* 249 */     if (this.dataArea != null) {
/* 250 */       clone.dataArea = ((Rectangle2D)this.dataArea.clone());
/*     */     }
/* 252 */     clone.subplotInfo = new ArrayList(this.subplotInfo.size());
/* 253 */     for (int i = 0; i < this.subplotInfo.size(); i++) {
/* 254 */       PlotRenderingInfo info = (PlotRenderingInfo)this.subplotInfo.get(i);
/*     */       
/* 256 */       clone.subplotInfo.add(info.clone());
/*     */     }
/* 258 */     return clone;
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
/* 269 */     stream.defaultWriteObject();
/* 270 */     SerialUtilities.writeShape(this.dataArea, stream);
/* 271 */     SerialUtilities.writeShape(this.plotArea, stream);
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
/* 284 */     stream.defaultReadObject();
/* 285 */     this.dataArea = ((Rectangle2D)SerialUtilities.readShape(stream));
/* 286 */     this.plotArea = ((Rectangle2D)SerialUtilities.readShape(stream));
/*     */   }
/*     */ }


/* Location:              /home/menick/Scrivania/ProcessMining/CNMining/CNMining.jar!/org/jfree/chart/plot/PlotRenderingInfo.class
 * Java compiler version: 3 (47.0)
 * JD-Core Version:       0.7.1
 */