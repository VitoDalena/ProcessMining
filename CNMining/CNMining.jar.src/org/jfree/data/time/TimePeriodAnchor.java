/*     */ package org.jfree.data.time;
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
/*     */ public final class TimePeriodAnchor
/*     */   implements Serializable
/*     */ {
/*     */   private static final long serialVersionUID = 2011955697457548862L;
/*  57 */   public static final TimePeriodAnchor START = new TimePeriodAnchor("TimePeriodAnchor.START");
/*     */   
/*     */ 
/*     */ 
/*  61 */   public static final TimePeriodAnchor MIDDLE = new TimePeriodAnchor("TimePeriodAnchor.MIDDLE");
/*     */   
/*     */ 
/*     */ 
/*  65 */   public static final TimePeriodAnchor END = new TimePeriodAnchor("TimePeriodAnchor.END");
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   private String name;
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   private TimePeriodAnchor(String name)
/*     */   {
/*  77 */     this.name = name;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public String toString()
/*     */   {
/*  86 */     return this.name;
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
/*  99 */     if (this == obj) {
/* 100 */       return true;
/*     */     }
/* 102 */     if (!(obj instanceof TimePeriodAnchor)) {
/* 103 */       return false;
/*     */     }
/*     */     
/* 106 */     TimePeriodAnchor position = (TimePeriodAnchor)obj;
/* 107 */     if (!this.name.equals(position.name)) {
/* 108 */       return false;
/*     */     }
/*     */     
/* 111 */     return true;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public int hashCode()
/*     */   {
/* 120 */     return this.name.hashCode();
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
/* 131 */     if (equals(START)) {
/* 132 */       return START;
/*     */     }
/* 134 */     if (equals(MIDDLE)) {
/* 135 */       return MIDDLE;
/*     */     }
/* 137 */     if (equals(END)) {
/* 138 */       return END;
/*     */     }
/* 140 */     return null;
/*     */   }
/*     */ }


/* Location:              /home/menick/Scrivania/ProcessMining/CNMining/CNMining.jar!/org/jfree/data/time/TimePeriodAnchor.class
 * Java compiler version: 3 (47.0)
 * JD-Core Version:       0.7.1
 */