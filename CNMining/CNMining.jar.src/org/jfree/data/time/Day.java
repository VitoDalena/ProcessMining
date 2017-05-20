/*     */ package org.jfree.data.time;
/*     */ 
/*     */ import java.io.Serializable;
/*     */ import java.text.DateFormat;
/*     */ import java.text.ParseException;
/*     */ import java.text.SimpleDateFormat;
/*     */ import java.util.Calendar;
/*     */ import java.util.Date;
/*     */ import java.util.Locale;
/*     */ import java.util.TimeZone;
/*     */ import org.jfree.date.SerialDate;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class Day
/*     */   extends RegularTimePeriod
/*     */   implements Serializable
/*     */ {
/*     */   private static final long serialVersionUID = -7082667380758962755L;
/*  88 */   protected static final DateFormat DATE_FORMAT = new SimpleDateFormat("yyyy-MM-dd");
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*  93 */   protected static final DateFormat DATE_FORMAT_SHORT = DateFormat.getDateInstance(3);
/*     */   
/*     */ 
/*     */ 
/*  97 */   protected static final DateFormat DATE_FORMAT_MEDIUM = DateFormat.getDateInstance(2);
/*     */   
/*     */ 
/*     */ 
/* 101 */   protected static final DateFormat DATE_FORMAT_LONG = DateFormat.getDateInstance(1);
/*     */   
/*     */ 
/*     */ 
/*     */   private SerialDate serialDate;
/*     */   
/*     */ 
/*     */   private long firstMillisecond;
/*     */   
/*     */ 
/*     */   private long lastMillisecond;
/*     */   
/*     */ 
/*     */ 
/*     */   public Day()
/*     */   {
/* 117 */     this(new Date());
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public Day(int day, int month, int year)
/*     */   {
/* 128 */     this.serialDate = SerialDate.createInstance(day, month, year);
/* 129 */     peg(Calendar.getInstance());
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public Day(SerialDate serialDate)
/*     */   {
/* 138 */     if (serialDate == null) {
/* 139 */       throw new IllegalArgumentException("Null 'serialDate' argument.");
/*     */     }
/* 141 */     this.serialDate = serialDate;
/* 142 */     peg(Calendar.getInstance());
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
/*     */   public Day(Date time)
/*     */   {
/* 155 */     this(time, TimeZone.getDefault(), Locale.getDefault());
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   /**
/*     */    * @deprecated
/*     */    */
/*     */   public Day(Date time, TimeZone zone)
/*     */   {
/* 168 */     this(time, zone, Locale.getDefault());
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public Day(Date time, TimeZone zone, Locale locale)
/*     */   {
/* 179 */     if (time == null) {
/* 180 */       throw new IllegalArgumentException("Null 'time' argument.");
/*     */     }
/* 182 */     if (zone == null) {
/* 183 */       throw new IllegalArgumentException("Null 'zone' argument.");
/*     */     }
/* 185 */     if (locale == null) {
/* 186 */       throw new IllegalArgumentException("Null 'locale' argument.");
/*     */     }
/* 188 */     Calendar calendar = Calendar.getInstance(zone, locale);
/* 189 */     calendar.setTime(time);
/* 190 */     int d = calendar.get(5);
/* 191 */     int m = calendar.get(2) + 1;
/* 192 */     int y = calendar.get(1);
/* 193 */     this.serialDate = SerialDate.createInstance(d, m, y);
/* 194 */     peg(calendar);
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
/*     */   public SerialDate getSerialDate()
/*     */   {
/* 207 */     return this.serialDate;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public int getYear()
/*     */   {
/* 216 */     return this.serialDate.getYYYY();
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public int getMonth()
/*     */   {
/* 225 */     return this.serialDate.getMonth();
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public int getDayOfMonth()
/*     */   {
/* 234 */     return this.serialDate.getDayOfMonth();
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
/*     */   public long getFirstMillisecond()
/*     */   {
/* 248 */     return this.firstMillisecond;
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
/*     */   public long getLastMillisecond()
/*     */   {
/* 262 */     return this.lastMillisecond;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public void peg(Calendar calendar)
/*     */   {
/* 274 */     this.firstMillisecond = getFirstMillisecond(calendar);
/* 275 */     this.lastMillisecond = getLastMillisecond(calendar);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public RegularTimePeriod previous()
/*     */   {
/* 285 */     int serial = this.serialDate.toSerial();
/* 286 */     if (serial > 2) {
/* 287 */       SerialDate yesterday = SerialDate.createInstance(serial - 1);
/* 288 */       return new Day(yesterday);
/*     */     }
/*     */     
/* 291 */     Day result = null;
/*     */     
/* 293 */     return result;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public RegularTimePeriod next()
/*     */   {
/* 305 */     int serial = this.serialDate.toSerial();
/* 306 */     if (serial < 2958465) {
/* 307 */       SerialDate tomorrow = SerialDate.createInstance(serial + 1);
/* 308 */       return new Day(tomorrow);
/*     */     }
/*     */     
/* 311 */     Day result = null;
/*     */     
/* 313 */     return result;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public long getSerialIndex()
/*     */   {
/* 322 */     return this.serialDate.toSerial();
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
/*     */   public long getFirstMillisecond(Calendar calendar)
/*     */   {
/* 337 */     int year = this.serialDate.getYYYY();
/* 338 */     int month = this.serialDate.getMonth();
/* 339 */     int day = this.serialDate.getDayOfMonth();
/* 340 */     calendar.clear();
/* 341 */     calendar.set(year, month - 1, day, 0, 0, 0);
/* 342 */     calendar.set(14, 0);
/*     */     
/* 344 */     return calendar.getTime().getTime();
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
/*     */   public long getLastMillisecond(Calendar calendar)
/*     */   {
/* 359 */     int year = this.serialDate.getYYYY();
/* 360 */     int month = this.serialDate.getMonth();
/* 361 */     int day = this.serialDate.getDayOfMonth();
/* 362 */     calendar.clear();
/* 363 */     calendar.set(year, month - 1, day, 23, 59, 59);
/* 364 */     calendar.set(14, 999);
/*     */     
/* 366 */     return calendar.getTime().getTime();
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
/*     */   public boolean equals(Object obj)
/*     */   {
/* 380 */     if (obj == this) {
/* 381 */       return true;
/*     */     }
/* 383 */     if (!(obj instanceof Day)) {
/* 384 */       return false;
/*     */     }
/* 386 */     Day that = (Day)obj;
/* 387 */     if (!this.serialDate.equals(that.getSerialDate())) {
/* 388 */       return false;
/*     */     }
/* 390 */     return true;
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
/*     */   public int hashCode()
/*     */   {
/* 403 */     return this.serialDate.hashCode();
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public int compareTo(Object o1)
/*     */   {
/*     */     int result;
/*     */     
/*     */ 
/*     */ 
/*     */ 
/*     */     int result;
/*     */     
/*     */ 
/*     */ 
/* 421 */     if ((o1 instanceof Day)) {
/* 422 */       Day d = (Day)o1;
/* 423 */       result = -d.getSerialDate().compare(this.serialDate);
/*     */     }
/*     */     else
/*     */     {
/*     */       int result;
/* 428 */       if ((o1 instanceof RegularTimePeriod))
/*     */       {
/* 430 */         result = 0;
/*     */ 
/*     */ 
/*     */       }
/*     */       else
/*     */       {
/*     */ 
/* 437 */         result = 1;
/*     */       }
/*     */     }
/* 440 */     return result;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public String toString()
/*     */   {
/* 449 */     return this.serialDate.toString();
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
/*     */   public static Day parseDay(String s)
/*     */   {
/*     */     try
/*     */     {
/* 465 */       return new Day(DATE_FORMAT.parse(s));
/*     */     }
/*     */     catch (ParseException e1) {
/*     */       try {
/* 469 */         return new Day(DATE_FORMAT_SHORT.parse(s));
/*     */       }
/*     */       catch (ParseException e2) {}
/*     */     }
/*     */     
/*     */ 
/* 475 */     return null;
/*     */   }
/*     */ }


/* Location:              /home/menick/Scrivania/ProcessMining/CNMining/CNMining.jar!/org/jfree/data/time/Day.class
 * Java compiler version: 3 (47.0)
 * JD-Core Version:       0.7.1
 */