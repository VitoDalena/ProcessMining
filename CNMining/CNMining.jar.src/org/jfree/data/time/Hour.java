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
/*     */ public class Hour
/*     */   extends RegularTimePeriod
/*     */   implements Serializable
/*     */ {
/*     */   private static final long serialVersionUID = -835471579831937652L;
/*     */   public static final int FIRST_HOUR_IN_DAY = 0;
/*     */   public static final int LAST_HOUR_IN_DAY = 23;
/*     */   private Day day;
/*     */   private byte hour;
/*     */   private long firstMillisecond;
/*     */   private long lastMillisecond;
/*     */   
/*     */   public Hour()
/*     */   {
/* 104 */     this(new Date());
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public Hour(int hour, Day day)
/*     */   {
/* 114 */     if (day == null) {
/* 115 */       throw new IllegalArgumentException("Null 'day' argument.");
/*     */     }
/* 117 */     this.hour = ((byte)hour);
/* 118 */     this.day = day;
/* 119 */     peg(Calendar.getInstance());
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public Hour(int hour, int day, int month, int year)
/*     */   {
/* 131 */     this(hour, new Day(day, month, year));
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
/*     */   public Hour(Date time)
/*     */   {
/* 144 */     this(time, TimeZone.getDefault(), Locale.getDefault());
/*     */   }
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
/*     */   public Hour(Date time, TimeZone zone)
/*     */   {
/* 158 */     this(time, zone, Locale.getDefault());
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
/*     */   public Hour(Date time, TimeZone zone, Locale locale)
/*     */   {
/* 172 */     if (time == null) {
/* 173 */       throw new IllegalArgumentException("Null 'time' argument.");
/*     */     }
/* 175 */     if (zone == null) {
/* 176 */       throw new IllegalArgumentException("Null 'zone' argument.");
/*     */     }
/* 178 */     if (locale == null) {
/* 179 */       throw new IllegalArgumentException("Null 'locale' argument.");
/*     */     }
/* 181 */     Calendar calendar = Calendar.getInstance(zone, locale);
/* 182 */     calendar.setTime(time);
/* 183 */     this.hour = ((byte)calendar.get(11));
/* 184 */     this.day = new Day(time, zone, locale);
/* 185 */     peg(calendar);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public int getHour()
/*     */   {
/* 194 */     return this.hour;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public Day getDay()
/*     */   {
/* 203 */     return this.day;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public int getYear()
/*     */   {
/* 212 */     return this.day.getYear();
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public int getMonth()
/*     */   {
/* 221 */     return this.day.getMonth();
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public int getDayOfMonth()
/*     */   {
/* 230 */     return this.day.getDayOfMonth();
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
/* 244 */     return this.firstMillisecond;
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
/* 258 */     return this.lastMillisecond;
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
/* 270 */     this.firstMillisecond = getFirstMillisecond(calendar);
/* 271 */     this.lastMillisecond = getLastMillisecond(calendar);
/*     */   }
/*     */   
/*     */ 
/*     */   public RegularTimePeriod previous()
/*     */   {
/*     */     Hour result;
/*     */     
/*     */     Hour result;
/*     */     
/* 281 */     if (this.hour != 0) {
/* 282 */       result = new Hour(this.hour - 1, this.day);
/*     */     }
/*     */     else {
/* 285 */       Day prevDay = (Day)this.day.previous();
/* 286 */       Hour result; if (prevDay != null) {
/* 287 */         result = new Hour(23, prevDay);
/*     */       }
/*     */       else {
/* 290 */         result = null;
/*     */       }
/*     */     }
/* 293 */     return result;
/*     */   }
/*     */   
/*     */ 
/*     */   public RegularTimePeriod next()
/*     */   {
/*     */     Hour result;
/*     */     
/*     */     Hour result;
/*     */     
/* 303 */     if (this.hour != 23) {
/* 304 */       result = new Hour(this.hour + 1, this.day);
/*     */     }
/*     */     else {
/* 307 */       Day nextDay = (Day)this.day.next();
/* 308 */       Hour result; if (nextDay != null) {
/* 309 */         result = new Hour(0, nextDay);
/*     */       }
/*     */       else {
/* 312 */         result = null;
/*     */       }
/*     */     }
/* 315 */     return result;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public long getSerialIndex()
/*     */   {
/* 324 */     return this.day.getSerialIndex() * 24L + this.hour;
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
/* 338 */     int year = this.day.getYear();
/* 339 */     int month = this.day.getMonth() - 1;
/* 340 */     int dom = this.day.getDayOfMonth();
/* 341 */     calendar.set(year, month, dom, this.hour, 0, 0);
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
/*     */   public long getLastMillisecond(Calendar calendar)
/*     */   {
/* 358 */     int year = this.day.getYear();
/* 359 */     int month = this.day.getMonth() - 1;
/* 360 */     int dom = this.day.getDayOfMonth();
/* 361 */     calendar.set(year, month, dom, this.hour, 59, 59);
/* 362 */     calendar.set(14, 999);
/*     */     
/* 364 */     return calendar.getTime().getTime();
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
/* 379 */     if (obj == this) {
/* 380 */       return true;
/*     */     }
/* 382 */     if (!(obj instanceof Hour)) {
/* 383 */       return false;
/*     */     }
/* 385 */     Hour that = (Hour)obj;
/* 386 */     if (this.hour != that.hour) {
/* 387 */       return false;
/*     */     }
/* 389 */     if (!this.day.equals(that.day)) {
/* 390 */       return false;
/*     */     }
/* 392 */     return true;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public String toString()
/*     */   {
/* 402 */     return "[" + this.hour + "," + getDayOfMonth() + "/" + getMonth() + "/" + getYear() + "]";
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
/*     */   public int hashCode()
/*     */   {
/* 416 */     int result = 17;
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
/* 437 */     if ((o1 instanceof Hour)) {
/* 438 */       Hour h = (Hour)o1;
/* 439 */       int result = getDay().compareTo(h.getDay());
/* 440 */       if (result == 0) {
/* 441 */         result = this.hour - h.getHour();
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
/*     */   public static Hour parseHour(String s)
/*     */   {
/* 473 */     Hour result = null;
/* 474 */     s = s.trim();
/*     */     
/* 476 */     String daystr = s.substring(0, Math.min(10, s.length()));
/* 477 */     Day day = Day.parseDay(daystr);
/* 478 */     if (day != null) {
/* 479 */       String hourstr = s.substring(Math.min(daystr.length() + 1, s.length()), s.length());
/*     */       
/*     */ 
/* 482 */       hourstr = hourstr.trim();
/* 483 */       int hour = Integer.parseInt(hourstr);
/*     */       
/* 485 */       if ((hour >= 0) && (hour <= 23)) {
/* 486 */         result = new Hour(hour, day);
/*     */       }
/*     */     }
/*     */     
/* 490 */     return result;
/*     */   }
/*     */ }


/* Location:              /home/menick/Scrivania/ProcessMining/CNMining/CNMining.jar!/org/jfree/data/time/Hour.class
 * Java compiler version: 3 (47.0)
 * JD-Core Version:       0.7.1
 */