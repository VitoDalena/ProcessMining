/*     */ package org.jfree.data.statistics;
/*     */ 
/*     */ import java.io.ObjectStreamException;
/*     */ import java.io.Serializable;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class HistogramType
/*     */   implements Serializable
/*     */ {
/*     */   private static final long serialVersionUID = 2618927186251997727L;
/*  56 */   public static final HistogramType FREQUENCY = new HistogramType("FREQUENCY");
/*     */   
/*     */ 
/*     */ 
/*  60 */   public static final HistogramType RELATIVE_FREQUENCY = new HistogramType("RELATIVE_FREQUENCY");
/*     */   
/*     */ 
/*     */ 
/*  64 */   public static final HistogramType SCALE_AREA_TO_1 = new HistogramType("SCALE_AREA_TO_1");
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   private String name;
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   private HistogramType(String name)
/*     */   {
/*  76 */     this.name = name;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public String toString()
/*     */   {
/*  85 */     return this.name;
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
/*  97 */     if (obj == null) {
/*  98 */       return false;
/*     */     }
/*     */     
/* 101 */     if (obj == this) {
/* 102 */       return true;
/*     */     }
/*     */     
/* 105 */     if (!(obj instanceof HistogramType)) {
/* 106 */       return false;
/*     */     }
/*     */     
/* 109 */     HistogramType t = (HistogramType)obj;
/* 110 */     if (!this.name.equals(t.name)) {
/* 111 */       return false;
/*     */     }
/*     */     
/* 114 */     return true;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public int hashCode()
/*     */   {
/* 124 */     return this.name.hashCode();
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   private Object readResolve()
/*     */     throws ObjectStreamException
/*     */   {
/* 135 */     if (equals(FREQUENCY)) {
/* 136 */       return FREQUENCY;
/*     */     }
/* 138 */     if (equals(RELATIVE_FREQUENCY)) {
/* 139 */       return RELATIVE_FREQUENCY;
/*     */     }
/* 141 */     if (equals(SCALE_AREA_TO_1)) {
/* 142 */       return SCALE_AREA_TO_1;
/*     */     }
/* 144 */     return null;
/*     */   }
/*     */ }


/* Location:              /home/menick/Scrivania/ProcessMining/CNMining/CNMining.jar!/org/jfree/data/statistics/HistogramType.class
 * Java compiler version: 3 (47.0)
 * JD-Core Version:       0.7.1
 */