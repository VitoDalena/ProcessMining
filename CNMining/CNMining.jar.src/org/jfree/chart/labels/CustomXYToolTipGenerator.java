/*     */ package org.jfree.chart.labels;
/*     */ 
/*     */ import java.io.Serializable;
/*     */ import java.util.ArrayList;
/*     */ import java.util.List;
/*     */ import org.jfree.data.xy.XYDataset;
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
/*     */ public class CustomXYToolTipGenerator
/*     */   implements XYToolTipGenerator, Cloneable, PublicCloneable, Serializable
/*     */ {
/*     */   private static final long serialVersionUID = 8636030004670141362L;
/*  66 */   private List toolTipSeries = new ArrayList();
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public int getListCount()
/*     */   {
/*  81 */     return this.toolTipSeries.size();
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public int getToolTipCount(int list)
/*     */   {
/*  93 */     int result = 0;
/*  94 */     List tooltips = (List)this.toolTipSeries.get(list);
/*  95 */     if (tooltips != null) {
/*  96 */       result = tooltips.size();
/*     */     }
/*  98 */     return result;
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
/*     */   public String getToolTipText(int series, int item)
/*     */   {
/* 111 */     String result = null;
/*     */     
/* 113 */     if (series < getListCount()) {
/* 114 */       List tooltips = (List)this.toolTipSeries.get(series);
/* 115 */       if ((tooltips != null) && 
/* 116 */         (item < tooltips.size())) {
/* 117 */         result = (String)tooltips.get(item);
/*     */       }
/*     */     }
/*     */     
/*     */ 
/* 122 */     return result;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public void addToolTipSeries(List toolTips)
/*     */   {
/* 131 */     this.toolTipSeries.add(toolTips);
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
/*     */   public String generateToolTip(XYDataset data, int series, int item)
/*     */   {
/* 145 */     return getToolTipText(series, item);
/*     */   }
/*     */   
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
/* 158 */     CustomXYToolTipGenerator clone = (CustomXYToolTipGenerator)super.clone();
/*     */     
/* 160 */     if (this.toolTipSeries != null) {
/* 161 */       clone.toolTipSeries = new ArrayList(this.toolTipSeries);
/*     */     }
/* 163 */     return clone;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public boolean equals(Object obj)
/*     */   {
/* 175 */     if (obj == this) {
/* 176 */       return true;
/*     */     }
/*     */     
/* 179 */     if ((obj instanceof CustomXYToolTipGenerator)) {
/* 180 */       CustomXYToolTipGenerator generator = (CustomXYToolTipGenerator)obj;
/* 181 */       boolean result = true;
/* 182 */       for (int series = 0; series < getListCount(); series++) {
/* 183 */         for (int item = 0; item < getToolTipCount(series); item++) {
/* 184 */           String t1 = getToolTipText(series, item);
/* 185 */           String t2 = generator.getToolTipText(series, item);
/* 186 */           if (t1 != null) {
/* 187 */             result = (result) && (t1.equals(t2));
/*     */           }
/*     */           else {
/* 190 */             result = (result) && (t2 == null);
/*     */           }
/*     */         }
/*     */       }
/* 194 */       return result;
/*     */     }
/*     */     
/* 197 */     return false;
/*     */   }
/*     */ }


/* Location:              /home/menick/Scrivania/ProcessMining/CNMining/CNMining.jar!/org/jfree/chart/labels/CustomXYToolTipGenerator.class
 * Java compiler version: 3 (47.0)
 * JD-Core Version:       0.7.1
 */