/*     */ package org.jfree.chart.axis;
/*     */ 
/*     */ import java.io.Serializable;
/*     */ import java.text.DateFormat;
/*     */ import java.text.FieldPosition;
/*     */ import java.text.NumberFormat;
/*     */ import java.text.ParsePosition;
/*     */ import java.util.Arrays;
/*     */ import java.util.Calendar;
/*     */ import java.util.Date;
/*     */ import java.util.GregorianCalendar;
/*     */ import java.util.TimeZone;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class QuarterDateFormat
/*     */   extends DateFormat
/*     */   implements Cloneable, Serializable
/*     */ {
/*     */   private static final long serialVersionUID = -6738465248529797176L;
/*  70 */   public static final String[] REGULAR_QUARTERS = { "1", "2", "3", "4" };
/*     */   
/*     */ 
/*     */ 
/*  74 */   public static final String[] ROMAN_QUARTERS = { "I", "II", "III", "IV" };
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*  82 */   public static final String[] GREEK_QUARTERS = { "Α", "Β", "Γ", "Δ" };
/*     */   
/*     */ 
/*     */ 
/*  86 */   private String[] quarters = REGULAR_QUARTERS;
/*     */   
/*     */ 
/*     */   private boolean quarterFirst;
/*     */   
/*     */ 
/*     */ 
/*     */   public QuarterDateFormat()
/*     */   {
/*  95 */     this(TimeZone.getDefault());
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public QuarterDateFormat(TimeZone zone)
/*     */   {
/* 104 */     this(zone, REGULAR_QUARTERS);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public QuarterDateFormat(TimeZone zone, String[] quarterSymbols)
/*     */   {
/* 114 */     this(zone, quarterSymbols, false);
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
/*     */   public QuarterDateFormat(TimeZone zone, String[] quarterSymbols, boolean quarterFirst)
/*     */   {
/* 129 */     if (zone == null) {
/* 130 */       throw new IllegalArgumentException("Null 'zone' argument.");
/*     */     }
/* 132 */     this.calendar = new GregorianCalendar(zone);
/* 133 */     this.quarters = quarterSymbols;
/* 134 */     this.quarterFirst = quarterFirst;
/*     */     
/*     */ 
/*     */ 
/*     */ 
/* 139 */     this.numberFormat = NumberFormat.getNumberInstance();
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
/*     */   public StringBuffer format(Date date, StringBuffer toAppendTo, FieldPosition fieldPosition)
/*     */   {
/* 154 */     this.calendar.setTime(date);
/* 155 */     int year = this.calendar.get(1);
/* 156 */     int month = this.calendar.get(2);
/* 157 */     int quarter = month / 3;
/* 158 */     if (this.quarterFirst) {
/* 159 */       toAppendTo.append(this.quarters[quarter]);
/* 160 */       toAppendTo.append(" ");
/* 161 */       toAppendTo.append(year);
/*     */     }
/*     */     else {
/* 164 */       toAppendTo.append(year);
/* 165 */       toAppendTo.append(" ");
/* 166 */       toAppendTo.append(this.quarters[quarter]);
/*     */     }
/* 168 */     return toAppendTo;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public Date parse(String source, ParsePosition pos)
/*     */   {
/* 180 */     return null;
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
/* 194 */     if (!(obj instanceof QuarterDateFormat)) {
/* 195 */       return false;
/*     */     }
/* 197 */     QuarterDateFormat that = (QuarterDateFormat)obj;
/* 198 */     if (!Arrays.equals(this.quarters, that.quarters)) {
/* 199 */       return false;
/*     */     }
/* 201 */     if (this.quarterFirst != that.quarterFirst) {
/* 202 */       return false;
/*     */     }
/* 204 */     return super.equals(obj);
/*     */   }
/*     */ }


/* Location:              /home/menick/Scrivania/ProcessMining/CNMining/CNMining.jar!/org/jfree/chart/axis/QuarterDateFormat.class
 * Java compiler version: 3 (47.0)
 * JD-Core Version:       0.7.1
 */