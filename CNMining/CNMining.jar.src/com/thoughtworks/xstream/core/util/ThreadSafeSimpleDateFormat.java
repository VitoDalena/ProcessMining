/*    */ package com.thoughtworks.xstream.core.util;
/*    */ 
/*    */ import java.text.DateFormat;
/*    */ import java.text.ParseException;
/*    */ import java.text.SimpleDateFormat;
/*    */ import java.util.Date;
/*    */ import java.util.Locale;
/*    */ import java.util.TimeZone;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class ThreadSafeSimpleDateFormat
/*    */ {
/*    */   private final String formatString;
/*    */   private final Pool pool;
/*    */   private final TimeZone timeZone;
/*    */   
/*    */   public ThreadSafeSimpleDateFormat(String format, TimeZone timeZone, int initialPoolSize, int maxPoolSize, boolean lenient)
/*    */   {
/* 43 */     this.formatString = format;
/* 44 */     this.timeZone = timeZone;
/* 45 */     this.pool = new Pool(initialPoolSize, maxPoolSize, new Pool.Factory() { private final boolean val$lenient;
/*    */       
/* 47 */       public Object newInstance() { SimpleDateFormat dateFormat = new SimpleDateFormat(ThreadSafeSimpleDateFormat.this.formatString, Locale.ENGLISH);
/* 48 */         dateFormat.setLenient(this.val$lenient);
/* 49 */         return dateFormat;
/*    */       }
/*    */     });
/*    */   }
/*    */   
/*    */   public String format(Date date)
/*    */   {
/* 56 */     DateFormat format = fetchFromPool();
/*    */     try {
/* 58 */       return format.format(date);
/*    */     } finally {
/* 60 */       this.pool.putInPool(format);
/*    */     }
/*    */   }
/*    */   
/*    */   public Date parse(String date) throws ParseException {
/* 65 */     DateFormat format = fetchFromPool();
/*    */     try {
/* 67 */       return format.parse(date);
/*    */     } finally {
/* 69 */       this.pool.putInPool(format);
/*    */     }
/*    */   }
/*    */   
/*    */   private DateFormat fetchFromPool() {
/* 74 */     DateFormat format = (DateFormat)this.pool.fetchFromPool();
/* 75 */     TimeZone tz = this.timeZone != null ? this.timeZone : TimeZone.getDefault();
/* 76 */     if (!tz.equals(format.getTimeZone())) {
/* 77 */       format.setTimeZone(tz);
/*    */     }
/* 79 */     return format;
/*    */   }
/*    */ }


/* Location:              /home/menick/Scrivania/ProcessMining/CNMining/CNMining.jar!/com/thoughtworks/xstream/core/util/ThreadSafeSimpleDateFormat.class
 * Java compiler version: 3 (47.0)
 * JD-Core Version:       0.7.1
 */