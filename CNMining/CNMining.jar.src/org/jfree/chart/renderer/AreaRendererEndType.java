/*     */ package org.jfree.chart.renderer;
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
/*     */ 
/*     */ public final class AreaRendererEndType
/*     */   implements Serializable
/*     */ {
/*     */   private static final long serialVersionUID = -1774146392916359839L;
/*  57 */   public static final AreaRendererEndType TAPER = new AreaRendererEndType("AreaRendererEndType.TAPER");
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*  63 */   public static final AreaRendererEndType TRUNCATE = new AreaRendererEndType("AreaRendererEndType.TRUNCATE");
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*  69 */   public static final AreaRendererEndType LEVEL = new AreaRendererEndType("AreaRendererEndType.LEVEL");
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   private String name;
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   private AreaRendererEndType(String name)
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
/*     */ 
/*     */   public boolean equals(Object obj)
/*     */   {
/* 103 */     if (this == obj) {
/* 104 */       return true;
/*     */     }
/* 106 */     if (!(obj instanceof AreaRendererEndType)) {
/* 107 */       return false;
/*     */     }
/*     */     
/* 110 */     AreaRendererEndType t = (AreaRendererEndType)obj;
/* 111 */     if (!this.name.equals(t.toString())) {
/* 112 */       return false;
/*     */     }
/*     */     
/* 115 */     return true;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   private Object readResolve()
/*     */     throws ObjectStreamException
/*     */   {
/* 127 */     Object result = null;
/* 128 */     if (equals(LEVEL)) {
/* 129 */       result = LEVEL;
/*     */     }
/* 131 */     else if (equals(TAPER)) {
/* 132 */       result = TAPER;
/*     */     }
/* 134 */     else if (equals(TRUNCATE)) {
/* 135 */       result = TRUNCATE;
/*     */     }
/* 137 */     return result;
/*     */   }
/*     */ }


/* Location:              /home/menick/Scrivania/ProcessMining/CNMining/CNMining.jar!/org/jfree/chart/renderer/AreaRendererEndType.class
 * Java compiler version: 3 (47.0)
 * JD-Core Version:       0.7.1
 */