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
/*     */ public class Second
/*     */   extends RegularTimePeriod
/*     */   implements Serializable
/*     */ {
/*     */   private static final long serialVersionUID = -6536564190712383466L;
/*     */   public static final int FIRST_SECOND_IN_MINUTE = 0;
/*     */   public static final int LAST_SECOND_IN_MINUTE = 59;
/*     */   private Day day;
/*     */   private byte hour;
/*     */   private byte minute;
/*     */   private byte second;
/*     */   private long firstMillisecond;
/*     */   
/*     */   public Second()
/*     */   {
/* 106 */     this(new Date());
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public Second(int second, Minute minute)
/*     */   {
/* 116 */     if (minute == null) {
/* 117 */       throw new IllegalArgumentException("Null 'minute' argument.");
/*     */     }
/* 119 */     this.day = minute.getDay();
/* 120 */     this.hour = ((byte)minute.getHourValue());
/* 121 */     this.minute = ((byte)minute.getMinute());
/* 122 */     this.second = ((byte)second);
/* 123 */     peg(Calendar.getInstance());
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
/*     */   public Second(int second, int minute, int hour, int day, int month, int year)
/*     */   {
/* 138 */     this(second, new Minute(minute, hour, day, month, year));
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public Second(Date time)
/*     */   {
/* 150 */     this(time, TimeZone.getDefault(), Locale.getDefault());
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
/*     */   public Second(Date time, TimeZone zone)
/*     */   {
/* 163 */     this(time, zone, Locale.getDefault());
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
/*     */   public Second(Date time, TimeZone zone, Locale locale)
/*     */   {
/* 176 */     Calendar calendar = Calendar.getInstance(zone, locale);
/* 177 */     calendar.setTime(time);
/* 178 */     this.second = ((byte)calendar.get(13));
/* 179 */     this.minute = ((byte)calendar.get(12));
/* 180 */     this.hour = ((byte)calendar.get(11));
/* 181 */     this.day = new Day(time, zone, locale);
/* 182 */     peg(calendar);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public int getSecond()
/*     */   {
/* 191 */     return this.second;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public Minute getMinute()
/*     */   {
/* 200 */     return new Minute(this.minute, new Hour(this.hour, this.day));
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
/* 214 */     return this.firstMillisecond;
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
/* 228 */     return this.firstMillisecond + 999L;
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
/* 240 */     this.firstMillisecond = getFirstMillisecond(calendar);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public RegularTimePeriod previous()
/*     */   {
/* 249 */     Second result = null;
/* 250 */     if (this.second != 0) {
/* 251 */       result = new Second(this.second - 1, getMinute());
/*     */     }
/*     */     else {
/* 254 */       Minute previous = (Minute)getMinute().previous();
/* 255 */       if (previous != null) {
/* 256 */         result = new Second(59, previous);
/*     */       }
/*     */     }
/* 259 */     return result;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public RegularTimePeriod next()
/*     */   {
/* 268 */     Second result = null;
/* 269 */     if (this.second != 59) {
/* 270 */       result = new Second(this.second + 1, getMinute());
/*     */     }
/*     */     else {
/* 273 */       Minute next = (Minute)getMinute().next();
/* 274 */       if (next != null) {
/* 275 */         result = new Second(0, next);
/*     */       }
/*     */     }
/* 278 */     return result;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public long getSerialIndex()
/*     */   {
/* 287 */     long hourIndex = this.day.getSerialIndex() * 24L + this.hour;
/* 288 */     long minuteIndex = hourIndex * 60L + this.minute;
/* 289 */     return minuteIndex * 60L + this.second;
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
/*     */   public long getFirstMillisecond(Calendar calendar)
/*     */   {
/* 303 */     int year = this.day.getYear();
/* 304 */     int month = this.day.getMonth() - 1;
/* 305 */     int day = this.day.getDayOfMonth();
/* 306 */     calendar.clear();
/* 307 */     calendar.set(year, month, day, this.hour, this.minute, this.second);
/* 308 */     calendar.set(14, 0);
/*     */     
/* 310 */     return calendar.getTime().getTime();
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
/*     */   public long getLastMillisecond(Calendar calendar)
/*     */   {
/* 324 */     return getFirstMillisecond(calendar) + 999L;
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
/* 339 */     if (obj == this) {
/* 340 */       return true;
/*     */     }
/* 342 */     if (!(obj instanceof Second)) {
/* 343 */       return false;
/*     */     }
/* 345 */     Second that = (Second)obj;
/* 346 */     if (this.second != that.second) {
/* 347 */       return false;
/*     */     }
/* 349 */     if (this.minute != that.minute) {
/* 350 */       return false;
/*     */     }
/* 352 */     if (this.hour != that.hour) {
/* 353 */       return false;
/*     */     }
/* 355 */     if (!this.day.equals(that.day)) {
/* 356 */       return false;
/*     */     }
/* 358 */     return true;
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
/* 371 */     int result = 17;
/* 372 */     result = 37 * result + this.second;
/* 373 */     result = 37 * result + this.minute;
/* 374 */     result = 37 * result + this.hour;
/* 375 */     result = 37 * result + this.day.hashCode();
/* 376 */     return result;
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
/*     */   public int compareTo(Object o1)
/*     */   {
/* 393 */     if ((o1 instanceof Second)) {
/* 394 */       Second s = (Second)o1;
/* 395 */       if (this.firstMillisecond < s.firstMillisecond) {
/* 396 */         return -1;
/*     */       }
/* 398 */       if (this.firstMillisecond > s.firstMillisecond) {
/* 399 */         return 1;
/*     */       }
/*     */       
/* 402 */       return 0;
/*     */     }
/*     */     
/*     */     int result;
/*     */     
/*     */     int result;
/* 408 */     if ((o1 instanceof RegularTimePeriod))
/*     */     {
/* 410 */       result = 0;
/*     */ 
/*     */ 
/*     */     }
/*     */     else
/*     */     {
/*     */ 
/* 417 */       result = 1;
/*     */     }
/*     */     
/* 420 */     return result;
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
/*     */   public static Second parseSecond(String s)
/*     */   {
/* 433 */     Second result = null;
/* 434 */     s = s.trim();
/* 435 */     String daystr = s.substring(0, Math.min(10, s.length()));
/* 436 */     Day day = Day.parseDay(daystr);
/* 437 */     if (day != null) {
/* 438 */       String hmsstr = s.substring(Math.min(daystr.length() + 1, s.length()), s.length());
/*     */       
/* 440 */       hmsstr = hmsstr.trim();
/*     */       
/* 442 */       int l = hmsstr.length();
/* 443 */       String hourstr = hmsstr.substring(0, Math.min(2, l));
/* 444 */       String minstr = hmsstr.substring(Math.min(3, l), Math.min(5, l));
/* 445 */       String secstr = hmsstr.substring(Math.min(6, l), Math.min(8, l));
/* 446 */       int hour = Integer.parseInt(hourstr);
/*     */       
/* 448 */       if ((hour >= 0) && (hour <= 23))
/*     */       {
/* 450 */         int minute = Integer.parseInt(minstr);
/* 451 */         if ((minute >= 0) && (minute <= 59))
/*     */         {
/* 453 */           Minute m = new Minute(minute, new Hour(hour, day));
/* 454 */           int second = Integer.parseInt(secstr);
/* 455 */           if ((second >= 0) && (second <= 59)) {
/* 456 */             result = new Second(second, m);
/*     */           }
/*     */         }
/*     */       }
/*     */     }
/* 461 */     return result;
/*     */   }
/*     */ }


/* Location:              /home/menick/Scrivania/ProcessMining/CNMining/CNMining.jar!/org/jfree/data/time/Second.class
 * Java compiler version: 3 (47.0)
 * JD-Core Version:       0.7.1
 */