/*     */ package com.thoughtworks.xstream.converters.extended;
/*     */ 
/*     */ import com.thoughtworks.xstream.converters.ConversionException;
/*     */ import com.thoughtworks.xstream.converters.basic.AbstractSingleValueConverter;
/*     */ import java.util.Calendar;
/*     */ import java.util.GregorianCalendar;
/*     */ import java.util.Locale;
/*     */ import java.util.TimeZone;
/*     */ import org.joda.time.DateTime;
/*     */ import org.joda.time.DateTimeZone;
/*     */ import org.joda.time.format.DateTimeFormatter;
/*     */ import org.joda.time.format.ISODateTimeFormat;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class ISO8601GregorianCalendarConverter
/*     */   extends AbstractSingleValueConverter
/*     */ {
/*  37 */   private static final DateTimeFormatter[] formattersUTC = { ISODateTimeFormat.dateTime(), ISODateTimeFormat.dateTimeNoMillis(), ISODateTimeFormat.basicDateTime(), ISODateTimeFormat.basicOrdinalDateTime(), ISODateTimeFormat.basicOrdinalDateTimeNoMillis(), ISODateTimeFormat.basicTime(), ISODateTimeFormat.basicTimeNoMillis(), ISODateTimeFormat.basicTTime(), ISODateTimeFormat.basicTTimeNoMillis(), ISODateTimeFormat.basicWeekDateTime(), ISODateTimeFormat.basicWeekDateTimeNoMillis(), ISODateTimeFormat.ordinalDateTime(), ISODateTimeFormat.ordinalDateTimeNoMillis(), ISODateTimeFormat.time(), ISODateTimeFormat.timeNoMillis(), ISODateTimeFormat.tTime(), ISODateTimeFormat.tTimeNoMillis(), ISODateTimeFormat.weekDateTime(), ISODateTimeFormat.weekDateTimeNoMillis() };
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*  57 */   private static final DateTimeFormatter[] formattersNoUTC = { ISODateTimeFormat.basicDate(), ISODateTimeFormat.basicOrdinalDate(), ISODateTimeFormat.basicWeekDate(), ISODateTimeFormat.date(), ISODateTimeFormat.dateHour(), ISODateTimeFormat.dateHourMinute(), ISODateTimeFormat.dateHourMinuteSecond(), ISODateTimeFormat.dateHourMinuteSecondFraction(), ISODateTimeFormat.dateHourMinuteSecondMillis(), ISODateTimeFormat.hour(), ISODateTimeFormat.hourMinute(), ISODateTimeFormat.hourMinuteSecond(), ISODateTimeFormat.hourMinuteSecondFraction(), ISODateTimeFormat.hourMinuteSecondMillis(), ISODateTimeFormat.ordinalDate(), ISODateTimeFormat.weekDate(), ISODateTimeFormat.year(), ISODateTimeFormat.yearMonth(), ISODateTimeFormat.yearMonthDay(), ISODateTimeFormat.weekyear(), ISODateTimeFormat.weekyearWeek(), ISODateTimeFormat.weekyearWeekDay() };
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public boolean canConvert(Class type)
/*     */   {
/*  82 */     return type.equals(GregorianCalendar.class);
/*     */   }
/*     */   
/*     */   public Object fromString(String str) {
/*  86 */     for (int i = 0; i < formattersUTC.length; i++) {
/*  87 */       DateTimeFormatter formatter = formattersUTC[i];
/*     */       try {
/*  89 */         DateTime dt = formatter.parseDateTime(str);
/*  90 */         Calendar calendar = dt.toCalendar(Locale.getDefault());
/*  91 */         calendar.setTimeZone(TimeZone.getDefault());
/*  92 */         return calendar;
/*     */       }
/*     */       catch (IllegalArgumentException e) {}
/*     */     }
/*     */     
/*  97 */     String timeZoneID = TimeZone.getDefault().getID();
/*  98 */     for (int i = 0; i < formattersNoUTC.length; i++) {
/*     */       try {
/* 100 */         DateTimeFormatter formatter = formattersNoUTC[i].withZone(DateTimeZone.forID(timeZoneID));
/* 101 */         DateTime dt = formatter.parseDateTime(str);
/* 102 */         Calendar calendar = dt.toCalendar(Locale.getDefault());
/* 103 */         calendar.setTimeZone(TimeZone.getDefault());
/* 104 */         return calendar;
/*     */       }
/*     */       catch (IllegalArgumentException e) {}
/*     */     }
/*     */     
/* 109 */     throw new ConversionException("Cannot parse date " + str);
/*     */   }
/*     */   
/*     */   public String toString(Object obj) {
/* 113 */     DateTime dt = new DateTime(obj);
/* 114 */     return dt.toString(formattersUTC[0]);
/*     */   }
/*     */ }


/* Location:              /home/menick/Scrivania/ProcessMining/CNMining/CNMining.jar!/com/thoughtworks/xstream/converters/extended/ISO8601GregorianCalendarConverter.class
 * Java compiler version: 3 (47.0)
 * JD-Core Version:       0.7.1
 */