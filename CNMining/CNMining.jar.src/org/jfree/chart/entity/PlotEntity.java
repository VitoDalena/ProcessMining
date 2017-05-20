/*     */ package org.jfree.chart.entity;
/*     */ 
/*     */ import java.awt.Shape;
/*     */ import java.io.IOException;
/*     */ import java.io.ObjectInputStream;
/*     */ import java.io.ObjectOutputStream;
/*     */ import org.jfree.chart.HashUtilities;
/*     */ import org.jfree.chart.plot.Plot;
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
/*     */ public class PlotEntity
/*     */   extends ChartEntity
/*     */ {
/*     */   private static final long serialVersionUID = -4445994133561919083L;
/*     */   private Plot plot;
/*     */   
/*     */   public PlotEntity(Shape area, Plot plot)
/*     */   {
/*  75 */     this(area, plot, null);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public PlotEntity(Shape area, Plot plot, String toolTipText)
/*     */   {
/*  87 */     this(area, plot, toolTipText, null);
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
/*     */   public PlotEntity(Shape area, Plot plot, String toolTipText, String urlText)
/*     */   {
/* 100 */     super(area, toolTipText, urlText);
/* 101 */     if (plot == null) {
/* 102 */       throw new IllegalArgumentException("Null 'plot' argument.");
/*     */     }
/*     */     
/* 105 */     this.plot = plot;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public Plot getPlot()
/*     */   {
/* 114 */     return this.plot;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public String toString()
/*     */   {
/* 124 */     StringBuffer buf = new StringBuffer("PlotEntity: ");
/* 125 */     buf.append("tooltip = ");
/* 126 */     buf.append(getToolTipText());
/* 127 */     return buf.toString();
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
/* 138 */     if (obj == this) {
/* 139 */       return true;
/*     */     }
/* 141 */     if (!(obj instanceof PlotEntity)) {
/* 142 */       return false;
/*     */     }
/* 144 */     PlotEntity that = (PlotEntity)obj;
/* 145 */     if (!getArea().equals(that.getArea())) {
/* 146 */       return false;
/*     */     }
/* 148 */     if (!ObjectUtilities.equal(getToolTipText(), that.getToolTipText())) {
/* 149 */       return false;
/*     */     }
/* 151 */     if (!ObjectUtilities.equal(getURLText(), that.getURLText())) {
/* 152 */       return false;
/*     */     }
/* 154 */     if (!this.plot.equals(that.plot)) {
/* 155 */       return false;
/*     */     }
/* 157 */     return true;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public int hashCode()
/*     */   {
/* 166 */     int result = 39;
/* 167 */     result = HashUtilities.hashCode(result, getToolTipText());
/* 168 */     result = HashUtilities.hashCode(result, getURLText());
/* 169 */     return result;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public Object clone()
/*     */     throws CloneNotSupportedException
/*     */   {
/* 181 */     return super.clone();
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
/* 192 */     stream.defaultWriteObject();
/* 193 */     SerialUtilities.writeShape(getArea(), stream);
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
/* 206 */     stream.defaultReadObject();
/* 207 */     setArea(SerialUtilities.readShape(stream));
/*     */   }
/*     */ }


/* Location:              /home/menick/Scrivania/ProcessMining/CNMining/CNMining.jar!/org/jfree/chart/entity/PlotEntity.class
 * Java compiler version: 3 (47.0)
 * JD-Core Version:       0.7.1
 */