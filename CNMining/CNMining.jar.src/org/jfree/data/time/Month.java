/*     */ package org.jfree.data.time;
/*     */ 
/*     */ import java.io.Serializable;
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
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class Month
/*     */   extends RegularTimePeriod
/*     */   implements Serializable
/*     */ {
/*     */   private static final long serialVersionUID = -5090216912548722570L;
/*     */   private int month;
/*     */   private int year;
/*     */   private long firstMillisecond;
/*     */   private long lastMillisecond;
/*     */   
/*     */   public Month()
/*     */   {
/* 102 */     this(new Date());
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public Month(int month, int year)
/*     */   {
/* 112 */     if ((month < 1) || (month > 12)) {
/* 113 */       throw new IllegalArgumentException("Month outside valid range.");
/*     */     }
/* 115 */     this.month = month;
/* 116 */     this.year = year;
/* 117 */     peg(Calendar.getInstance());
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public Month(int month, Year year)
/*     */   {
/* 127 */     if ((month < 1) || (month > 12)) {
/* 128 */       throw new IllegalArgumentException("Month outside valid range.");
/*     */     }
/* 130 */     this.month = month;
/* 131 */     this.year = year.getYear();
/* 132 */     peg(Calendar.getInstance());
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public Month(Date time)
/*     */   {
/* 144 */     this(time, TimeZone.getDefault());
/*     */   }
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
/*     */   public Month(Date time, TimeZone zone)
/*     */   {
/* 159 */     this(time, zone, Locale.getDefault());
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
/*     */   public Month(Date time, TimeZone zone, Locale locale)
/*     */   {
/* 173 */     Calendar calendar = Calendar.getInstance(zone, locale);
/* 174 */     calendar.setTime(time);
/* 175 */     this.month = (calendar.get(2) + 1);
/* 176 */     this.year = calendar.get(1);
/* 177 */     peg(calendar);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public Year getYear()
/*     */   {
/* 186 */     return new Year(this.year);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public int getYearValue()
/*     */   {
/* 195 */     return this.year;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public int getMonth()
/*     */   {
/* 204 */     return this.month;
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
/* 232 */     return this.lastMillisecond;
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
/* 245 */     this.lastMillisecond = getLastMillisecond(calendar);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */   public RegularTimePeriod previous()
/*     */   {
/*     */     Month result;
/*     */     
/*     */ 
/*     */     Month result;
/*     */     
/*     */ 
/* 258 */     if (this.month != 1) {
/* 259 */       result = new Month(this.month - 1, this.year);
/*     */     } else {
/*     */       Month result;
/* 262 */       if (this.year > 1900) {
/* 263 */         result = new Month(12, this.year - 1);
/*     */       }
/*     */       else {
/* 266 */         result = null;
/*     */       }
/*     */     }
/* 269 */     return result;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */   public RegularTimePeriod next()
/*     */   {
/*     */     Month result;
/*     */     
/*     */ 
/*     */     Month result;
/*     */     
/*     */ 
/* 282 */     if (this.month != 12) {
/* 283 */       result = new Month(this.month + 1, this.year);
/*     */     } else {
/*     */       Month result;
/* 286 */       if (this.year < 9999) {
/* 287 */         result = new Month(1, this.year + 1);
/*     */       }
/*     */       else {
/* 290 */         result = null;
/*     */       }
/*     */     }
/* 293 */     return result;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public long getSerialIndex()
/*     */   {
/* 302 */     return this.year * 12L + this.month;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public String toString()
/*     */   {
/* 313 */     return SerialDate.monthCodeToString(this.month) + " " + this.year;
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
/* 327 */     if (obj == this) {
/* 328 */       return true;
/*     */     }
/* 330 */     if (!(obj instanceof Month)) {
/* 331 */       return false;
/*     */     }
/* 333 */     Month that = (Month)obj;
/* 334 */     if (this.month != that.month) {
/* 335 */       return false;
/*     */     }
/* 337 */     if (this.year != that.year) {
/* 338 */       return false;
/*     */     }
/* 340 */     return true;
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
/* 353 */     int result = 17;
/* 354 */     result = 37 * result + this.month;
/* 355 */     result = 37 * result + this.year;
/* 356 */     return result;
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
/* 374 */     if ((o1 instanceof Month)) {
/* 375 */       Month m = (Month)o1;
/* 376 */       int result = this.year - m.getYearValue();
/* 377 */       if (result == 0) {
/* 378 */         result = this.month - m.getMonth();
/*     */       }
/*     */     }
/*     */     else
/*     */     {
/*     */       int result;
/* 384 */       if ((o1 instanceof RegularTimePeriod))
/*     */       {
/* 386 */         result = 0;
/*     */ 
/*     */ 
/*     */       }
/*     */       else
/*     */       {
/*     */ 
/* 393 */         result = 1;
/*     */       }
/*     */     }
/* 396 */     return result;
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
/*     */   public long getFirstMillisecond(Calendar calendar)
/*     */   {
/* 412 */     calendar.set(this.year, this.month - 1, 1, 0, 0, 0);
/* 413 */     calendar.set(14, 0);
/*     */     
/*     */ 
/* 416 */     return calendar.getTime().getTime();
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
/* 431 */     int eom = SerialDate.lastDayOfMonth(this.month, this.year);
/* 432 */     calendar.set(this.year, this.month - 1, eom, 23, 59, 59);
/* 433 */     calendar.set(14, 999);
/*     */     
/*     */ 
/* 436 */     return calendar.getTime().getTime();
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
/*     */   public static Month parseMonth(String s)
/*     */   {
/* 450 */     Month result = null;
/* 451 */     if (s == null) {
/* 452 */       return result;
/*     */     }
/*     */     
/* 455 */     s = s.trim();
/* 456 */     int i = findSeparator(s);
/*     */     String s2;
/*     */     String s1;
/*     */     String s2;
/*     */     boolean yearIsFirst;
/* 461 */     if (i == -1) {
/* 462 */       boolean yearIsFirst = true;
/* 463 */       String s1 = s.substring(0, 5);
/* 464 */       s2 = s.substring(5);
/*     */     }
/*     */     else {
/* 467 */       s1 = s.substring(0, i).trim();
/* 468 */       s2 = s.substring(i + 1, s.length()).trim();
/*     */       
/* 470 */       Year y1 = evaluateAsYear(s1);
/* 471 */       boolean yearIsFirst; if (y1 == null) {
/* 472 */         yearIsFirst = false;
/*     */       }
/*     */       else {
/* 475 */         Year y2 = evaluateAsYear(s2);
/* 476 */         boolean yearIsFirst; if (y2 == null) {
/* 477 */           yearIsFirst = true;
/*     */         }
/*     */         else
/* 480 */           yearIsFirst = s1.length() > s2.length();
/*     */       }
/*     */     }
/*     */     int month;
/*     */     Year year;
/*     */     int month;
/* 486 */     if (yearIsFirst) {
/* 487 */       Year year = evaluateAsYear(s1);
/* 488 */       month = SerialDate.stringToMonthCode(s2);
/*     */     }
/*     */     else {
/* 491 */       year = evaluateAsYear(s2);
/* 492 */       month = SerialDate.stringToMonthCode(s1);
/*     */     }
/* 494 */     if (month == -1) {
/* 495 */       throw new TimePeriodFormatException("Can't evaluate the month.");
/*     */     }
/* 497 */     if (year == null) {
/* 498 */       throw new TimePeriodFormatException("Can't evaluate the year.");
/*     */     }
/* 500 */     result = new Month(month, year);
/* 501 */     return result;
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
/*     */   private static int findSeparator(String s)
/*     */   {
/* 514 */     int result = s.indexOf('-');
/* 515 */     if (result == -1) {
/* 516 */       result = s.indexOf(',');
/*     */     }
/* 518 */     if (result == -1) {
/* 519 */       result = s.indexOf(' ');
/*     */     }
/* 521 */     if (result == -1) {
/* 522 */       result = s.indexOf('.');
/*     */     }
/* 524 */     return result;
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
/*     */   private static Year evaluateAsYear(String s)
/*     */   {
/* 537 */     Year result = null;
/*     */     try {
/* 539 */       result = Year.parseYear(s);
/*     */     }
/*     */     catch (TimePeriodFormatException e) {}
/*     */     
/*     */ 
/* 544 */     return result;
/*     */   }
/*     */ }


/* Location:              /home/menick/Scrivania/ProcessMining/CNMining/CNMining.jar!/org/jfree/data/time/Month.class
 * Java compiler version: 3 (47.0)
 * JD-Core Version:       0.7.1
 */