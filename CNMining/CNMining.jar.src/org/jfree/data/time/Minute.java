/*     */ package org.jfree.data.time;
/*     */ 
/*     */ import java.io.Serializable;
/*     */ import java.util.Calendar;
/*     */ import java.util.Date;
/*     */ import java.util.Locale;
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
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class Minute
/*     */   extends RegularTimePeriod
/*     */   implements Serializable
/*     */ {
/*     */   private static final long serialVersionUID = 2144572840034842871L;
/*     */   public static final int FIRST_MINUTE_IN_HOUR = 0;
/*     */   public static final int LAST_MINUTE_IN_HOUR = 59;
/*     */   private Day day;
/*     */   private byte hour;
/*     */   private byte minute;
/*     */   private long firstMillisecond;
/*     */   private long lastMillisecond;
/*     */   
/*     */   public Minute()
/*     */   {
/* 108 */     this(new Date());
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public Minute(int minute, Hour hour)
/*     */   {
/* 118 */     if (hour == null) {
/* 119 */       throw new IllegalArgumentException("Null 'hour' argument.");
/*     */     }
/* 121 */     this.minute = ((byte)minute);
/* 122 */     this.hour = ((byte)hour.getHour());
/* 123 */     this.day = hour.getDay();
/* 124 */     peg(Calendar.getInstance());
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
/*     */   public Minute(Date time)
/*     */   {
/* 137 */     this(time, TimeZone.getDefault(), Locale.getDefault());
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
/*     */   public Minute(Date time, TimeZone zone)
/*     */   {
/* 150 */     this(time, zone, Locale.getDefault());
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
/*     */   public Minute(Date time, TimeZone zone, Locale locale)
/*     */   {
/* 163 */     if (time == null) {
/* 164 */       throw new IllegalArgumentException("Null 'time' argument.");
/*     */     }
/* 166 */     if (zone == null) {
/* 167 */       throw new IllegalArgumentException("Null 'zone' argument.");
/*     */     }
/* 169 */     if (locale == null) {
/* 170 */       throw new IllegalArgumentException("Null 'locale' argument.");
/*     */     }
/* 172 */     Calendar calendar = Calendar.getInstance(zone, locale);
/* 173 */     calendar.setTime(time);
/* 174 */     int min = calendar.get(12);
/* 175 */     this.minute = ((byte)min);
/* 176 */     this.hour = ((byte)calendar.get(11));
/* 177 */     this.day = new Day(time, zone, locale);
/* 178 */     peg(calendar);
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
/*     */   public Minute(int minute, int hour, int day, int month, int year)
/*     */   {
/* 191 */     this(minute, new Hour(hour, new Day(day, month, year)));
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public Day getDay()
/*     */   {
/* 202 */     return this.day;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public Hour getHour()
/*     */   {
/* 211 */     return new Hour(this.hour, this.day);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public int getHourValue()
/*     */   {
/* 222 */     return this.hour;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public int getMinute()
/*     */   {
/* 231 */     return this.minute;
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
/* 245 */     return this.firstMillisecond;
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
/* 259 */     return this.lastMillisecond;
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
/* 271 */     this.firstMillisecond = getFirstMillisecond(calendar);
/* 272 */     this.lastMillisecond = getLastMillisecond(calendar);
/*     */   }
/*     */   
/*     */ 
/*     */   public RegularTimePeriod previous()
/*     */   {
/*     */     Minute result;
/*     */     
/*     */     Minute result;
/*     */     
/* 282 */     if (this.minute != 0) {
/* 283 */       result = new Minute(this.minute - 1, getHour());
/*     */     }
/*     */     else {
/* 286 */       Hour h = (Hour)getHour().previous();
/* 287 */       Minute result; if (h != null) {
/* 288 */         result = new Minute(59, h);
/*     */       }
/*     */       else {
/* 291 */         result = null;
/*     */       }
/*     */     }
/* 294 */     return result;
/*     */   }
/*     */   
/*     */ 
/*     */   public RegularTimePeriod next()
/*     */   {
/*     */     Minute result;
/*     */     
/*     */     Minute result;
/*     */     
/* 304 */     if (this.minute != 59) {
/* 305 */       result = new Minute(this.minute + 1, getHour());
/*     */     }
/*     */     else {
/* 308 */       Hour nextHour = (Hour)getHour().next();
/* 309 */       Minute result; if (nextHour != null) {
/* 310 */         result = new Minute(0, nextHour);
/*     */       }
/*     */       else {
/* 313 */         result = null;
/*     */       }
/*     */     }
/* 316 */     return result;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public long getSerialIndex()
/*     */   {
/* 325 */     long hourIndex = this.day.getSerialIndex() * 24L + this.hour;
/* 326 */     return hourIndex * 60L + this.minute;
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
/* 341 */     int year = this.day.getYear();
/* 342 */     int month = this.day.getMonth() - 1;
/* 343 */     int day = this.day.getDayOfMonth();
/*     */     
/* 345 */     calendar.clear();
/* 346 */     calendar.set(year, month, day, this.hour, this.minute, 0);
/* 347 */     calendar.set(14, 0);
/*     */     
/*     */ 
/* 350 */     return calendar.getTime().getTime();
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
/* 365 */     int year = this.day.getYear();
/* 366 */     int month = this.day.getMonth() - 1;
/* 367 */     int day = this.day.getDayOfMonth();
/*     */     
/* 369 */     calendar.clear();
/* 370 */     calendar.set(year, month, day, this.hour, this.minute, 59);
/* 371 */     calendar.set(14, 999);
/*     */     
/*     */ 
/* 374 */     return calendar.getTime().getTime();
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
/*     */   public boolean equals(Object obj)
/*     */   {
/* 389 */     if (obj == this) {
/* 390 */       return true;
/*     */     }
/* 392 */     if (!(obj instanceof Minute)) {
/* 393 */       return false;
/*     */     }
/* 395 */     Minute that = (Minute)obj;
/* 396 */     if (this.minute != that.minute) {
/* 397 */       return false;
/*     */     }
/* 399 */     if (this.hour != that.hour) {
/* 400 */       return false;
/*     */     }
/* 402 */     return true;
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
/* 415 */     int result = 17;
/* 416 */     result = 37 * result + this.minute;
/* 417 */     result = 37 * result + this.hour;
/* 418 */     result = 37 * result + this.day.hashCode();
/* 419 */     return result;
/*     */   }
/*     */   
/*     */ 
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
/*     */ 
/*     */ 
/* 437 */     if ((o1 instanceof Minute)) {
/* 438 */       Minute m = (Minute)o1;
/* 439 */       int result = getHour().compareTo(m.getHour());
/* 440 */       if (result == 0) {
/* 441 */         result = this.minute - m.getMinute();
/*     */       }
/*     */     }
/*     */     else
/*     */     {
/*     */       int result;
/* 447 */       if ((o1 instanceof RegularTimePeriod))
/*     */       {
/* 449 */         result = 0;
/*     */ 
/*     */ 
/*     */       }
/*     */       else
/*     */       {
/*     */ 
/* 456 */         result = 1;
/*     */       }
/*     */     }
/* 459 */     return result;
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
/*     */   public static Minute parseMinute(String s)
/*     */   {
/* 473 */     Minute result = null;
/* 474 */     s = s.trim();
/*     */     
/* 476 */     String daystr = s.substring(0, Math.min(10, s.length()));
/* 477 */     Day day = Day.parseDay(daystr);
/* 478 */     if (day != null) {
/* 479 */       String hmstr = s.substring(Math.min(daystr.length() + 1, s.length()), s.length());
/*     */       
/*     */ 
/* 482 */       hmstr = hmstr.trim();
/*     */       
/* 484 */       String hourstr = hmstr.substring(0, Math.min(2, hmstr.length()));
/* 485 */       int hour = Integer.parseInt(hourstr);
/*     */       
/* 487 */       if ((hour >= 0) && (hour <= 23)) {
/* 488 */         String minstr = hmstr.substring(Math.min(hourstr.length() + 1, hmstr.length()), hmstr.length());
/*     */         
/*     */ 
/*     */ 
/* 492 */         int minute = Integer.parseInt(minstr);
/* 493 */         if ((minute >= 0) && (minute <= 59)) {
/* 494 */           result = new Minute(minute, new Hour(hour, day));
/*     */         }
/*     */       }
/*     */     }
/* 498 */     return result;
/*     */   }
/*     */ }


/* Location:              /home/menick/Scrivania/ProcessMining/CNMining/CNMining.jar!/org/jfree/data/time/Minute.class
 * Java compiler version: 3 (47.0)
 * JD-Core Version:       0.7.1
 */