/*     */ package org.jfree.chart.axis;
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
/*     */ public class DateTickUnitType
/*     */   implements Serializable
/*     */ {
/*  55 */   public static final DateTickUnitType YEAR = new DateTickUnitType("DateTickUnitType.YEAR", 1);
/*     */   
/*     */ 
/*     */ 
/*  59 */   public static final DateTickUnitType MONTH = new DateTickUnitType("DateTickUnitType.MONTH", 2);
/*     */   
/*     */ 
/*     */ 
/*  63 */   public static final DateTickUnitType DAY = new DateTickUnitType("DateTickUnitType.DAY", 5);
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*  68 */   public static final DateTickUnitType HOUR = new DateTickUnitType("DateTickUnitType.HOUR", 11);
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*  73 */   public static final DateTickUnitType MINUTE = new DateTickUnitType("DateTickUnitType.MINUTE", 12);
/*     */   
/*     */ 
/*     */ 
/*  77 */   public static final DateTickUnitType SECOND = new DateTickUnitType("DateTickUnitType.SECOND", 13);
/*     */   
/*     */ 
/*     */ 
/*  81 */   public static final DateTickUnitType MILLISECOND = new DateTickUnitType("DateTickUnitType.MILLISECOND", 14);
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   private String name;
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   private int calendarField;
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   private DateTickUnitType(String name, int calendarField)
/*     */   {
/*  98 */     this.name = name;
/*  99 */     this.calendarField = calendarField;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public int getCalendarField()
/*     */   {
/* 108 */     return this.calendarField;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public String toString()
/*     */   {
/* 117 */     return this.name;
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
/* 129 */     if (this == obj) {
/* 130 */       return true;
/*     */     }
/* 132 */     if (!(obj instanceof DateTickUnitType)) {
/* 133 */       return false;
/*     */     }
/* 135 */     DateTickUnitType t = (DateTickUnitType)obj;
/* 136 */     if (!this.name.equals(t.toString())) {
/* 137 */       return false;
/*     */     }
/* 139 */     return true;
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
/* 150 */     if (equals(YEAR)) {
/* 151 */       return YEAR;
/*     */     }
/* 153 */     if (equals(MONTH)) {
/* 154 */       return MONTH;
/*     */     }
/* 156 */     if (equals(DAY)) {
/* 157 */       return DAY;
/*     */     }
/* 159 */     if (equals(HOUR)) {
/* 160 */       return HOUR;
/*     */     }
/* 162 */     if (equals(MINUTE)) {
/* 163 */       return MINUTE;
/*     */     }
/* 165 */     if (equals(SECOND)) {
/* 166 */       return SECOND;
/*     */     }
/* 168 */     if (equals(MILLISECOND)) {
/* 169 */       return MILLISECOND;
/*     */     }
/* 171 */     return null;
/*     */   }
/*     */ }


/* Location:              /home/menick/Scrivania/ProcessMining/CNMining/CNMining.jar!/org/jfree/chart/axis/DateTickUnitType.class
 * Java compiler version: 3 (47.0)
 * JD-Core Version:       0.7.1
 */