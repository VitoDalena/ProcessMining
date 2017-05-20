/*     */ package org.jfree.data.time;
/*     */ 
/*     */ import java.lang.reflect.Constructor;
/*     */ import java.util.Calendar;
/*     */ import java.util.Date;
/*     */ import java.util.TimeZone;
/*     */ import org.jfree.date.MonthConstants;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public abstract class RegularTimePeriod
/*     */   implements TimePeriod, Comparable, MonthConstants
/*     */ {
/*     */   public static RegularTimePeriod createInstance(Class c, Date millisecond, TimeZone zone)
/*     */   {
/*  86 */     RegularTimePeriod result = null;
/*     */     try {
/*  88 */       Constructor constructor = c.getDeclaredConstructor(new Class[] { Date.class, TimeZone.class });
/*     */       
/*  90 */       result = (RegularTimePeriod)constructor.newInstance(new Object[] { millisecond, zone });
/*     */     }
/*     */     catch (Exception e) {}
/*     */     
/*     */ 
/*     */ 
/*  96 */     return result;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public static Class downsize(Class c)
/*     */   {
/* 108 */     if (c.equals(Year.class)) {
/* 109 */       return Quarter.class;
/*     */     }
/* 111 */     if (c.equals(Quarter.class)) {
/* 112 */       return Month.class;
/*     */     }
/* 114 */     if (c.equals(Month.class)) {
/* 115 */       return Day.class;
/*     */     }
/* 117 */     if (c.equals(Day.class)) {
/* 118 */       return Hour.class;
/*     */     }
/* 120 */     if (c.equals(Hour.class)) {
/* 121 */       return Minute.class;
/*     */     }
/* 123 */     if (c.equals(Minute.class)) {
/* 124 */       return Second.class;
/*     */     }
/* 126 */     if (c.equals(Second.class)) {
/* 127 */       return Millisecond.class;
/*     */     }
/*     */     
/* 130 */     return Millisecond.class;
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
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   /**
/*     */    * @deprecated
/*     */    */
/* 165 */   public static final TimeZone DEFAULT_TIME_ZONE = ;
/*     */   
/*     */ 
/*     */ 
/*     */   /**
/*     */    * @deprecated
/*     */    */
/* 172 */   public static final Calendar WORKING_CALENDAR = Calendar.getInstance(DEFAULT_TIME_ZONE);
/*     */   
/*     */ 
/*     */ 
/*     */   public abstract RegularTimePeriod previous();
/*     */   
/*     */ 
/*     */ 
/*     */   public abstract RegularTimePeriod next();
/*     */   
/*     */ 
/*     */ 
/*     */   public abstract long getSerialIndex();
/*     */   
/*     */ 
/*     */ 
/*     */   public abstract void peg(Calendar paramCalendar);
/*     */   
/*     */ 
/*     */ 
/*     */   public Date getStart()
/*     */   {
/* 194 */     return new Date(getFirstMillisecond());
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public Date getEnd()
/*     */   {
/* 206 */     return new Date(getLastMillisecond());
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
/*     */   public abstract long getFirstMillisecond();
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   /**
/*     */    * @deprecated
/*     */    */
/*     */   public long getFirstMillisecond(TimeZone zone)
/*     */   {
/* 236 */     Calendar calendar = Calendar.getInstance(zone);
/* 237 */     return getFirstMillisecond(calendar);
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
/*     */ 
/*     */   public abstract long getFirstMillisecond(Calendar paramCalendar);
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public abstract long getLastMillisecond();
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   /**
/*     */    * @deprecated
/*     */    */
/*     */   public long getLastMillisecond(TimeZone zone)
/*     */   {
/* 282 */     Calendar calendar = Calendar.getInstance(zone);
/* 283 */     return getLastMillisecond(calendar);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public abstract long getLastMillisecond(Calendar paramCalendar);
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public long getMiddleMillisecond()
/*     */   {
/* 304 */     long m1 = getFirstMillisecond();
/* 305 */     long m2 = getLastMillisecond();
/* 306 */     return m1 + (m2 - m1) / 2L;
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
/*     */   /**
/*     */    * @deprecated
/*     */    */
/*     */   public long getMiddleMillisecond(TimeZone zone)
/*     */   {
/* 322 */     Calendar calendar = Calendar.getInstance(zone);
/* 323 */     long m1 = getFirstMillisecond(calendar);
/* 324 */     long m2 = getLastMillisecond(calendar);
/* 325 */     return m1 + (m2 - m1) / 2L;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public long getMiddleMillisecond(Calendar calendar)
/*     */   {
/* 337 */     long m1 = getFirstMillisecond(calendar);
/* 338 */     long m2 = getLastMillisecond(calendar);
/* 339 */     return m1 + (m2 - m1) / 2L;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public String toString()
/*     */   {
/* 348 */     return String.valueOf(getStart());
/*     */   }
/*     */ }


/* Location:              /home/menick/Scrivania/ProcessMining/CNMining/CNMining.jar!/org/jfree/data/time/RegularTimePeriod.class
 * Java compiler version: 3 (47.0)
 * JD-Core Version:       0.7.1
 */