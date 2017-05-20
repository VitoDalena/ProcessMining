/*     */ package org.jfree.chart.entity;
/*     */ 
/*     */ import java.awt.Shape;
/*     */ import java.io.IOException;
/*     */ import java.io.ObjectInputStream;
/*     */ import java.io.ObjectOutputStream;
/*     */ import org.jfree.chart.HashUtilities;
/*     */ import org.jfree.chart.axis.Axis;
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
/*     */ public class AxisEntity
/*     */   extends ChartEntity
/*     */ {
/*     */   private static final long serialVersionUID = -4445994133561919083L;
/*     */   private Axis axis;
/*     */   
/*     */   public AxisEntity(Shape area, Axis axis)
/*     */   {
/*  75 */     this(area, axis, null);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public AxisEntity(Shape area, Axis axis, String toolTipText)
/*     */   {
/*  87 */     this(area, axis, toolTipText, null);
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
/*     */   public AxisEntity(Shape area, Axis axis, String toolTipText, String urlText)
/*     */   {
/* 101 */     super(area, toolTipText, urlText);
/* 102 */     if (axis == null) {
/* 103 */       throw new IllegalArgumentException("Null 'axis' argument.");
/*     */     }
/*     */     
/* 106 */     this.axis = axis;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public Axis getAxis()
/*     */   {
/* 115 */     return this.axis;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public String toString()
/*     */   {
/* 125 */     StringBuffer buf = new StringBuffer("AxisEntity: ");
/* 126 */     buf.append("tooltip = ");
/* 127 */     buf.append(getToolTipText());
/* 128 */     return buf.toString();
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
/* 139 */     if (obj == this) {
/* 140 */       return true;
/*     */     }
/* 142 */     if (!(obj instanceof AxisEntity)) {
/* 143 */       return false;
/*     */     }
/* 145 */     AxisEntity that = (AxisEntity)obj;
/* 146 */     if (!getArea().equals(that.getArea())) {
/* 147 */       return false;
/*     */     }
/* 149 */     if (!ObjectUtilities.equal(getToolTipText(), that.getToolTipText())) {
/* 150 */       return false;
/*     */     }
/* 152 */     if (!ObjectUtilities.equal(getURLText(), that.getURLText())) {
/* 153 */       return false;
/*     */     }
/* 155 */     if (!this.axis.equals(that.axis)) {
/* 156 */       return false;
/*     */     }
/* 158 */     return true;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public int hashCode()
/*     */   {
/* 167 */     int result = 39;
/* 168 */     result = HashUtilities.hashCode(result, getToolTipText());
/* 169 */     result = HashUtilities.hashCode(result, getURLText());
/* 170 */     return result;
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
/* 182 */     return super.clone();
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
/* 193 */     stream.defaultWriteObject();
/* 194 */     SerialUtilities.writeShape(getArea(), stream);
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
/* 207 */     stream.defaultReadObject();
/* 208 */     setArea(SerialUtilities.readShape(stream));
/*     */   }
/*     */ }


/* Location:              /home/menick/Scrivania/ProcessMining/CNMining/CNMining.jar!/org/jfree/chart/entity/AxisEntity.class
 * Java compiler version: 3 (47.0)
 * JD-Core Version:       0.7.1
 */