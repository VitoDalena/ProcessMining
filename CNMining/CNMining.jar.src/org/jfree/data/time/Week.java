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
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class Week
/*     */   extends RegularTimePeriod
/*     */   implements Serializable
/*     */ {
/*     */   private static final long serialVersionUID = 1856387786939865061L;
/*     */   public static final int FIRST_WEEK_IN_YEAR = 1;
/*     */   public static final int LAST_WEEK_IN_YEAR = 53;
/*     */   private short year;
/*     */   private byte week;
/*     */   private long firstMillisecond;
/*     */   private long lastMillisecond;
/*     */   
/*     */   public Week()
/*     */   {
/* 119 */     this(new Date());
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public Week(int week, int year)
/*     */   {
/* 129 */     if ((week < 1) && (week > 53)) {
/* 130 */       throw new IllegalArgumentException("The 'week' argument must be in the range 1 - 53.");
/*     */     }
/*     */     
/* 133 */     this.week = ((byte)week);
/* 134 */     this.year = ((short)year);
/* 135 */     peg(Calendar.getInstance());
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public Week(int week, Year year)
/*     */   {
/* 145 */     if ((week < 1) && (week > 53)) {
/* 146 */       throw new IllegalArgumentException("The 'week' argument must be in the range 1 - 53.");
/*     */     }
/*     */     
/* 149 */     this.week = ((byte)week);
/* 150 */     this.year = ((short)year.getYear());
/* 151 */     peg(Calendar.getInstance());
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
/*     */   public Week(Date time)
/*     */   {
/* 166 */     this(time, TimeZone.getDefault(), Locale.getDefault());
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
/*     */   public Week(Date time, TimeZone zone)
/*     */   {
/* 180 */     this(time, zone, Locale.getDefault());
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
/*     */   public Week(Date time, TimeZone zone, Locale locale)
/*     */   {
/* 194 */     if (time == null) {
/* 195 */       throw new IllegalArgumentException("Null 'time' argument.");
/*     */     }
/* 197 */     if (zone == null) {
/* 198 */       throw new IllegalArgumentException("Null 'zone' argument.");
/*     */     }
/* 200 */     if (locale == null) {
/* 201 */       throw new IllegalArgumentException("Null 'locale' argument.");
/*     */     }
/* 203 */     Calendar calendar = Calendar.getInstance(zone, locale);
/* 204 */     calendar.setTime(time);
/*     */     
/*     */ 
/*     */ 
/*     */ 
/* 209 */     int tempWeek = calendar.get(3);
/* 210 */     if ((tempWeek == 1) && (calendar.get(2) == 11))
/*     */     {
/* 212 */       this.week = 1;
/* 213 */       this.year = ((short)(calendar.get(1) + 1));
/*     */     }
/*     */     else {
/* 216 */       this.week = ((byte)Math.min(tempWeek, 53));
/* 217 */       int yyyy = calendar.get(1);
/*     */       
/*     */ 
/* 220 */       if ((calendar.get(2) == 0) && (this.week >= 52))
/*     */       {
/* 222 */         yyyy--;
/*     */       }
/* 224 */       this.year = ((short)yyyy);
/*     */     }
/* 226 */     peg(calendar);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public Year getYear()
/*     */   {
/* 235 */     return new Year(this.year);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public int getYearValue()
/*     */   {
/* 244 */     return this.year;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public int getWeek()
/*     */   {
/* 253 */     return this.week;
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
/* 267 */     return this.firstMillisecond;
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
/* 281 */     return this.lastMillisecond;
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
/* 293 */     this.firstMillisecond = getFirstMillisecond(calendar);
/* 294 */     this.lastMillisecond = getLastMillisecond(calendar);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   public RegularTimePeriod previous()
/*     */   {
/*     */     Week result;
/*     */     
/*     */ 
/*     */     Week result;
/*     */     
/*     */ 
/* 308 */     if (this.week != 1) {
/* 309 */       result = new Week(this.week - 1, this.year);
/*     */     }
/*     */     else {
/*     */       Week result;
/* 313 */       if (this.year > 1900) {
/* 314 */         int yy = this.year - 1;
/* 315 */         Calendar prevYearCalendar = Calendar.getInstance();
/* 316 */         prevYearCalendar.set(yy, 11, 31);
/* 317 */         result = new Week(prevYearCalendar.getActualMaximum(3), yy);
/*     */       }
/*     */       else
/*     */       {
/* 321 */         result = null;
/*     */       }
/*     */     }
/* 324 */     return result;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   public RegularTimePeriod next()
/*     */   {
/*     */     Week result;
/*     */     
/*     */ 
/*     */ 
/*     */     Week result;
/*     */     
/*     */ 
/*     */ 
/* 340 */     if (this.week < 52) {
/* 341 */       result = new Week(this.week + 1, this.year);
/*     */     }
/*     */     else {
/* 344 */       Calendar calendar = Calendar.getInstance();
/* 345 */       calendar.set(this.year, 11, 31);
/* 346 */       int actualMaxWeek = calendar.getActualMaximum(3);
/*     */       Week result;
/* 348 */       if (this.week < actualMaxWeek) {
/* 349 */         result = new Week(this.week + 1, this.year);
/*     */       } else {
/*     */         Week result;
/* 352 */         if (this.year < 9999) {
/* 353 */           result = new Week(1, this.year + 1);
/*     */         }
/*     */         else {
/* 356 */           result = null;
/*     */         }
/*     */       }
/*     */     }
/* 360 */     return result;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public long getSerialIndex()
/*     */   {
/* 370 */     return this.year * 53L + this.week;
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
/* 385 */     Calendar c = (Calendar)calendar.clone();
/* 386 */     c.clear();
/* 387 */     c.set(1, this.year);
/* 388 */     c.set(3, this.week);
/* 389 */     c.set(7, c.getFirstDayOfWeek());
/* 390 */     c.set(10, 0);
/* 391 */     c.set(12, 0);
/* 392 */     c.set(13, 0);
/* 393 */     c.set(14, 0);
/*     */     
/* 395 */     return c.getTime().getTime();
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
/* 410 */     Calendar c = (Calendar)calendar.clone();
/* 411 */     c.clear();
/* 412 */     c.set(1, this.year);
/* 413 */     c.set(3, this.week + 1);
/* 414 */     c.set(7, c.getFirstDayOfWeek());
/* 415 */     c.set(10, 0);
/* 416 */     c.set(12, 0);
/* 417 */     c.set(13, 0);
/* 418 */     c.set(14, 0);
/*     */     
/* 420 */     return c.getTime().getTime() - 1L;
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
/* 431 */     return "Week " + this.week + ", " + this.year;
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
/* 446 */     if (obj == this) {
/* 447 */       return true;
/*     */     }
/* 449 */     if (!(obj instanceof Week)) {
/* 450 */       return false;
/*     */     }
/* 452 */     Week that = (Week)obj;
/* 453 */     if (this.week != that.week) {
/* 454 */       return false;
/*     */     }
/* 456 */     if (this.year != that.year) {
/* 457 */       return false;
/*     */     }
/* 459 */     return true;
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
/* 473 */     int result = 17;
/* 474 */     result = 37 * result + this.week;
/* 475 */     result = 37 * result + this.year;
/* 476 */     return result;
/*     */   }
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
/*     */     int result;
/*     */     
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/* 495 */     if ((o1 instanceof Week)) {
/* 496 */       Week w = (Week)o1;
/* 497 */       int result = this.year - w.getYear().getYear();
/* 498 */       if (result == 0) {
/* 499 */         result = this.week - w.getWeek();
/*     */       }
/*     */     }
/*     */     else
/*     */     {
/*     */       int result;
/* 505 */       if ((o1 instanceof RegularTimePeriod))
/*     */       {
/* 507 */         result = 0;
/*     */ 
/*     */ 
/*     */       }
/*     */       else
/*     */       {
/*     */ 
/* 514 */         result = 1;
/*     */       }
/*     */     }
/* 517 */     return result;
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
/*     */   public static Week parseWeek(String s)
/*     */   {
/* 534 */     Week result = null;
/* 535 */     if (s != null)
/*     */     {
/*     */ 
/* 538 */       s = s.trim();
/*     */       
/* 540 */       int i = findSeparator(s);
/* 541 */       if (i != -1) {
/* 542 */         String s1 = s.substring(0, i).trim();
/* 543 */         String s2 = s.substring(i + 1, s.length()).trim();
/*     */         
/* 545 */         Year y = evaluateAsYear(s1);
/*     */         
/* 547 */         if (y != null) {
/* 548 */           int w = stringToWeek(s2);
/* 549 */           if (w == -1) {
/* 550 */             throw new TimePeriodFormatException("Can't evaluate the week.");
/*     */           }
/*     */           
/* 553 */           result = new Week(w, y);
/*     */         }
/*     */         else {
/* 556 */           y = evaluateAsYear(s2);
/* 557 */           if (y != null) {
/* 558 */             int w = stringToWeek(s1);
/* 559 */             if (w == -1) {
/* 560 */               throw new TimePeriodFormatException("Can't evaluate the week.");
/*     */             }
/*     */             
/* 563 */             result = new Week(w, y);
/*     */           }
/*     */           else {
/* 566 */             throw new TimePeriodFormatException("Can't evaluate the year.");
/*     */           }
/*     */           
/*     */         }
/*     */       }
/*     */       else
/*     */       {
/* 573 */         throw new TimePeriodFormatException("Could not find separator.");
/*     */       }
/*     */     }
/*     */     
/*     */ 
/* 578 */     return result;
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
/*     */   private static int findSeparator(String s)
/*     */   {
/* 592 */     int result = s.indexOf('-');
/* 593 */     if (result == -1) {
/* 594 */       result = s.indexOf(',');
/*     */     }
/* 596 */     if (result == -1) {
/* 597 */       result = s.indexOf(' ');
/*     */     }
/* 599 */     if (result == -1) {
/* 600 */       result = s.indexOf('.');
/*     */     }
/* 602 */     return result;
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
/*     */   private static Year evaluateAsYear(String s)
/*     */   {
/* 616 */     Year result = null;
/*     */     try {
/* 618 */       result = Year.parseYear(s);
/*     */     }
/*     */     catch (TimePeriodFormatException e) {}
/*     */     
/*     */ 
/* 623 */     return result;
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
/*     */   private static int stringToWeek(String s)
/*     */   {
/* 636 */     int result = -1;
/* 637 */     s = s.replace('W', ' ');
/* 638 */     s = s.trim();
/*     */     try {
/* 640 */       result = Integer.parseInt(s);
/* 641 */       if ((result < 1) || (result > 53)) {
/* 642 */         result = -1;
/*     */       }
/*     */     }
/*     */     catch (NumberFormatException e) {}
/*     */     
/*     */ 
/* 648 */     return result;
/*     */   }
/*     */ }


/* Location:              /home/menick/Scrivania/ProcessMining/CNMining/CNMining.jar!/org/jfree/data/time/Week.class
 * Java compiler version: 3 (47.0)
 * JD-Core Version:       0.7.1
 */