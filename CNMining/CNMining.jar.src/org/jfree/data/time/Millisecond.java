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
/*     */ public class Millisecond
/*     */   extends RegularTimePeriod
/*     */   implements Serializable
/*     */ {
/*     */   static final long serialVersionUID = -5316836467277638485L;
/*     */   public static final int FIRST_MILLISECOND_IN_SECOND = 0;
/*     */   public static final int LAST_MILLISECOND_IN_SECOND = 999;
/*     */   private Day day;
/*     */   private byte hour;
/*     */   private byte minute;
/*     */   private byte second;
/*     */   private int millisecond;
/*     */   private long firstMillisecond;
/*     */   
/*     */   public Millisecond()
/*     */   {
/* 107 */     this(new Date());
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public Millisecond(int millisecond, Second second)
/*     */   {
/* 117 */     this.millisecond = millisecond;
/* 118 */     this.second = ((byte)second.getSecond());
/* 119 */     this.minute = ((byte)second.getMinute().getMinute());
/* 120 */     this.hour = ((byte)second.getMinute().getHourValue());
/* 121 */     this.day = second.getMinute().getDay();
/* 122 */     peg(Calendar.getInstance());
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
/*     */   public Millisecond(int millisecond, int second, int minute, int hour, int day, int month, int year)
/*     */   {
/* 139 */     this(millisecond, new Second(second, minute, hour, day, month, year));
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public Millisecond(Date time)
/*     */   {
/* 151 */     this(time, TimeZone.getDefault(), Locale.getDefault());
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
/*     */   public Millisecond(Date time, TimeZone zone)
/*     */   {
/* 164 */     this(time, zone, Locale.getDefault());
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
/*     */   public Millisecond(Date time, TimeZone zone, Locale locale)
/*     */   {
/* 177 */     Calendar calendar = Calendar.getInstance(zone, locale);
/* 178 */     calendar.setTime(time);
/* 179 */     this.millisecond = calendar.get(14);
/* 180 */     this.second = ((byte)calendar.get(13));
/* 181 */     this.minute = ((byte)calendar.get(12));
/* 182 */     this.hour = ((byte)calendar.get(11));
/* 183 */     this.day = new Day(time, zone, locale);
/* 184 */     peg(calendar);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public Second getSecond()
/*     */   {
/* 193 */     return new Second(this.second, this.minute, this.hour, this.day.getDayOfMonth(), this.day.getMonth(), this.day.getYear());
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public long getMillisecond()
/*     */   {
/* 204 */     return this.millisecond;
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
/* 218 */     return this.firstMillisecond;
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
/* 232 */     return this.firstMillisecond;
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
/* 244 */     this.firstMillisecond = getFirstMillisecond(calendar);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public RegularTimePeriod previous()
/*     */   {
/* 253 */     RegularTimePeriod result = null;
/* 254 */     if (this.millisecond != 0) {
/* 255 */       result = new Millisecond(this.millisecond - 1, getSecond());
/*     */     }
/*     */     else {
/* 258 */       Second previous = (Second)getSecond().previous();
/* 259 */       if (previous != null) {
/* 260 */         result = new Millisecond(999, previous);
/*     */       }
/*     */     }
/* 263 */     return result;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public RegularTimePeriod next()
/*     */   {
/* 272 */     RegularTimePeriod result = null;
/* 273 */     if (this.millisecond != 999) {
/* 274 */       result = new Millisecond(this.millisecond + 1, getSecond());
/*     */     }
/*     */     else {
/* 277 */       Second next = (Second)getSecond().next();
/* 278 */       if (next != null) {
/* 279 */         result = new Millisecond(0, next);
/*     */       }
/*     */     }
/* 282 */     return result;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public long getSerialIndex()
/*     */   {
/* 291 */     long hourIndex = this.day.getSerialIndex() * 24L + this.hour;
/* 292 */     long minuteIndex = hourIndex * 60L + this.minute;
/* 293 */     long secondIndex = minuteIndex * 60L + this.second;
/* 294 */     return secondIndex * 1000L + this.millisecond;
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
/* 309 */     if (obj == this) {
/* 310 */       return true;
/*     */     }
/* 312 */     if (!(obj instanceof Millisecond)) {
/* 313 */       return false;
/*     */     }
/* 315 */     Millisecond that = (Millisecond)obj;
/* 316 */     if (this.millisecond != that.millisecond) {
/* 317 */       return false;
/*     */     }
/* 319 */     if (this.second != that.second) {
/* 320 */       return false;
/*     */     }
/* 322 */     if (this.minute != that.minute) {
/* 323 */       return false;
/*     */     }
/* 325 */     if (this.hour != that.hour) {
/* 326 */       return false;
/*     */     }
/* 328 */     if (!this.day.equals(that.day)) {
/* 329 */       return false;
/*     */     }
/* 331 */     return true;
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
/* 344 */     int result = 17;
/* 345 */     result = 37 * result + this.millisecond;
/* 346 */     result = 37 * result + getSecond().hashCode();
/* 347 */     return result;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public int compareTo(Object obj)
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
/*     */ 
/* 366 */     if ((obj instanceof Millisecond)) {
/* 367 */       Millisecond ms = (Millisecond)obj;
/* 368 */       long difference = getFirstMillisecond() - ms.getFirstMillisecond();
/* 369 */       int result; if (difference > 0L) {
/* 370 */         result = 1;
/*     */       } else {
/*     */         int result;
/* 373 */         if (difference < 0L) {
/* 374 */           result = -1;
/*     */         }
/*     */         else {
/* 377 */           result = 0;
/*     */         }
/*     */       }
/*     */     }
/*     */     else
/*     */     {
/*     */       int result;
/* 384 */       if ((obj instanceof RegularTimePeriod)) {
/* 385 */         RegularTimePeriod rtp = (RegularTimePeriod)obj;
/* 386 */         long thisVal = getFirstMillisecond();
/* 387 */         long anotherVal = rtp.getFirstMillisecond();
/* 388 */         result = thisVal == anotherVal ? 0 : thisVal < anotherVal ? -1 : 1;
/*     */ 
/*     */ 
/*     */       }
/*     */       else
/*     */       {
/*     */ 
/*     */ 
/* 396 */         result = 1;
/*     */       }
/*     */     }
/* 399 */     return result;
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
/* 413 */     int year = this.day.getYear();
/* 414 */     int month = this.day.getMonth() - 1;
/* 415 */     int day = this.day.getDayOfMonth();
/* 416 */     calendar.clear();
/* 417 */     calendar.set(year, month, day, this.hour, this.minute, this.second);
/* 418 */     calendar.set(14, this.millisecond);
/*     */     
/* 420 */     return calendar.getTime().getTime();
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
/* 434 */     return getFirstMillisecond(calendar);
/*     */   }
/*     */ }


/* Location:              /home/menick/Scrivania/ProcessMining/CNMining/CNMining.jar!/org/jfree/data/time/Millisecond.class
 * Java compiler version: 3 (47.0)
 * JD-Core Version:       0.7.1
 */