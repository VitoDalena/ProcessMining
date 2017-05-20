/*     */ package org.jfree.chart.util;
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
/*     */ public final class XYCoordinateType
/*     */   implements Serializable
/*     */ {
/*  55 */   public static final XYCoordinateType DATA = new XYCoordinateType("XYCoordinateType.DATA");
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*  62 */   public static final XYCoordinateType RELATIVE = new XYCoordinateType("XYCoordinateType.RELATIVE");
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*  69 */   public static final XYCoordinateType INDEX = new XYCoordinateType("XYCoordinateType.INDEX");
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   private String name;
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   private XYCoordinateType(String name)
/*     */   {
/*  81 */     this.name = name;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public String toString()
/*     */   {
/*  90 */     return this.name;
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
/* 102 */     if (this == obj) {
/* 103 */       return true;
/*     */     }
/* 105 */     if (!(obj instanceof XYCoordinateType)) {
/* 106 */       return false;
/*     */     }
/* 108 */     XYCoordinateType order = (XYCoordinateType)obj;
/* 109 */     if (!this.name.equals(order.toString())) {
/* 110 */       return false;
/*     */     }
/* 112 */     return true;
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
/* 123 */     if (equals(DATA)) {
/* 124 */       return DATA;
/*     */     }
/* 126 */     if (equals(RELATIVE)) {
/* 127 */       return RELATIVE;
/*     */     }
/* 129 */     if (equals(INDEX)) {
/* 130 */       return INDEX;
/*     */     }
/* 132 */     return null;
/*     */   }
/*     */ }


/* Location:              /home/menick/Scrivania/ProcessMining/CNMining/CNMining.jar!/org/jfree/chart/util/XYCoordinateType.class
 * Java compiler version: 3 (47.0)
 * JD-Core Version:       0.7.1
 */