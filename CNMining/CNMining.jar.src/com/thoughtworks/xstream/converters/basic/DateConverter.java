/*     */ package com.thoughtworks.xstream.converters.basic;
/*     */ 
/*     */ import com.thoughtworks.xstream.converters.ConversionException;
/*     */ import com.thoughtworks.xstream.core.util.ThreadSafeSimpleDateFormat;
/*     */ import java.text.ParseException;
/*     */ import java.util.Date;
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
/*     */ public class DateConverter
/*     */   extends AbstractSingleValueConverter
/*     */ {
/*  36 */   private static final String[] DEFAULT_ACCEPTABLE_FORMATS = { "yyyy-MM-dd HH:mm:ss.S a", "yyyy-MM-dd HH:mm:ssz", "yyyy-MM-dd HH:mm:ss z", "yyyy-MM-dd HH:mm:ssa" };
/*     */   
/*     */ 
/*     */ 
/*     */   private static final String DEFAULT_PATTERN = "yyyy-MM-dd HH:mm:ss.S z";
/*     */   
/*     */ 
/*  43 */   private static final TimeZone UTC = TimeZone.getTimeZone("UTC");
/*     */   
/*     */   private final ThreadSafeSimpleDateFormat defaultFormat;
/*     */   
/*     */   private final ThreadSafeSimpleDateFormat[] acceptableFormats;
/*     */   
/*     */   public DateConverter()
/*     */   {
/*  51 */     this(false);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public DateConverter(TimeZone timeZone)
/*     */   {
/*  62 */     this("yyyy-MM-dd HH:mm:ss.S z", DEFAULT_ACCEPTABLE_FORMATS, timeZone);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public DateConverter(boolean lenient)
/*     */   {
/*  72 */     this("yyyy-MM-dd HH:mm:ss.S z", DEFAULT_ACCEPTABLE_FORMATS, lenient);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public DateConverter(String defaultFormat, String[] acceptableFormats)
/*     */   {
/*  82 */     this(defaultFormat, acceptableFormats, false);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public DateConverter(String defaultFormat, String[] acceptableFormats, TimeZone timeZone)
/*     */   {
/*  93 */     this(defaultFormat, acceptableFormats, timeZone, false);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public DateConverter(String defaultFormat, String[] acceptableFormats, boolean lenient)
/*     */   {
/* 105 */     this(defaultFormat, acceptableFormats, UTC, lenient);
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
/*     */   public DateConverter(String defaultFormat, String[] acceptableFormats, TimeZone timeZone, boolean lenient)
/*     */   {
/* 119 */     this.defaultFormat = new ThreadSafeSimpleDateFormat(defaultFormat, timeZone, 4, 20, lenient);
/*     */     
/* 121 */     this.acceptableFormats = (acceptableFormats != null ? new ThreadSafeSimpleDateFormat[acceptableFormats.length] : new ThreadSafeSimpleDateFormat[0]);
/*     */     
/*     */ 
/* 124 */     for (int i = 0; i < this.acceptableFormats.length; i++) {
/* 125 */       this.acceptableFormats[i] = new ThreadSafeSimpleDateFormat(acceptableFormats[i], timeZone, 1, 20, lenient);
/*     */     }
/*     */   }
/*     */   
/*     */   public boolean canConvert(Class type)
/*     */   {
/* 131 */     return type.equals(Date.class);
/*     */   }
/*     */   
/*     */   public Object fromString(String str) {
/*     */     try {
/* 136 */       return this.defaultFormat.parse(str);
/*     */     } catch (ParseException e) {
/* 138 */       for (int i = 0; i < this.acceptableFormats.length; i++) {
/*     */         try {
/* 140 */           return this.acceptableFormats[i].parse(str);
/*     */         }
/*     */         catch (ParseException e2) {}
/*     */       }
/*     */       
/*     */ 
/* 146 */       throw new ConversionException("Cannot parse date " + str);
/*     */     }
/*     */   }
/*     */   
/*     */   public String toString(Object obj) {
/* 151 */     return this.defaultFormat.format((Date)obj);
/*     */   }
/*     */ }


/* Location:              /home/menick/Scrivania/ProcessMining/CNMining/CNMining.jar!/com/thoughtworks/xstream/converters/basic/DateConverter.class
 * Java compiler version: 3 (47.0)
 * JD-Core Version:       0.7.1
 */