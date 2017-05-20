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
/*     */ public class Quarter
/*     */   extends RegularTimePeriod
/*     */   implements Serializable
/*     */ {
/*     */   private static final long serialVersionUID = 3810061714380888671L;
/*     */   public static final int FIRST_QUARTER = 1;
/*     */   public static final int LAST_QUARTER = 4;
/*  89 */   public static final int[] FIRST_MONTH_IN_QUARTER = { 0, 1, 4, 7, 10 };
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*  95 */   public static final int[] LAST_MONTH_IN_QUARTER = { 0, 3, 6, 9, 12 };
/*     */   
/*     */ 
/*     */ 
/*     */   private short year;
/*     */   
/*     */ 
/*     */ 
/*     */   private byte quarter;
/*     */   
/*     */ 
/*     */ 
/*     */   private long firstMillisecond;
/*     */   
/*     */ 
/*     */   private long lastMillisecond;
/*     */   
/*     */ 
/*     */ 
/*     */   public Quarter()
/*     */   {
/* 116 */     this(new Date());
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public Quarter(int quarter, int year)
/*     */   {
/* 126 */     if ((quarter < 1) || (quarter > 4)) {
/* 127 */       throw new IllegalArgumentException("Quarter outside valid range.");
/*     */     }
/* 129 */     this.year = ((short)year);
/* 130 */     this.quarter = ((byte)quarter);
/* 131 */     peg(Calendar.getInstance());
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public Quarter(int quarter, Year year)
/*     */   {
/* 141 */     if ((quarter < 1) || (quarter > 4)) {
/* 142 */       throw new IllegalArgumentException("Quarter outside valid range.");
/*     */     }
/* 144 */     this.year = ((short)year.getYear());
/* 145 */     this.quarter = ((byte)quarter);
/* 146 */     peg(Calendar.getInstance());
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public Quarter(Date time)
/*     */   {
/* 158 */     this(time, TimeZone.getDefault());
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
/*     */   public Quarter(Date time, TimeZone zone)
/*     */   {
/* 171 */     this(time, zone, Locale.getDefault());
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
/*     */   public Quarter(Date time, TimeZone zone, Locale locale)
/*     */   {
/* 185 */     Calendar calendar = Calendar.getInstance(zone, locale);
/* 186 */     calendar.setTime(time);
/* 187 */     int month = calendar.get(2) + 1;
/* 188 */     this.quarter = ((byte)SerialDate.monthCodeToQuarter(month));
/* 189 */     this.year = ((short)calendar.get(1));
/* 190 */     peg(calendar);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public int getQuarter()
/*     */   {
/* 199 */     return this.quarter;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public Year getYear()
/*     */   {
/* 208 */     return new Year(this.year);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public int getYearValue()
/*     */   {
/* 219 */     return this.year;
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
/* 233 */     return this.firstMillisecond;
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
/* 247 */     return this.lastMillisecond;
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
/* 259 */     this.firstMillisecond = getFirstMillisecond(calendar);
/* 260 */     this.lastMillisecond = getLastMillisecond(calendar);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */   public RegularTimePeriod previous()
/*     */   {
/*     */     Quarter result;
/*     */     
/*     */     Quarter result;
/*     */     
/* 271 */     if (this.quarter > 1) {
/* 272 */       result = new Quarter(this.quarter - 1, this.year);
/*     */     } else {
/*     */       Quarter result;
/* 275 */       if (this.year > 1900) {
/* 276 */         result = new Quarter(4, this.year - 1);
/*     */       }
/*     */       else {
/* 279 */         result = null;
/*     */       }
/*     */     }
/* 282 */     return result;
/*     */   }
/*     */   
/*     */ 
/*     */   public RegularTimePeriod next()
/*     */   {
/*     */     Quarter result;
/*     */     
/*     */     Quarter result;
/*     */     
/* 292 */     if (this.quarter < 4) {
/* 293 */       result = new Quarter(this.quarter + 1, this.year);
/*     */     } else {
/*     */       Quarter result;
/* 296 */       if (this.year < 9999) {
/* 297 */         result = new Quarter(1, this.year + 1);
/*     */       }
/*     */       else {
/* 300 */         result = null;
/*     */       }
/*     */     }
/* 303 */     return result;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public long getSerialIndex()
/*     */   {
/* 312 */     return this.year * 4L + this.quarter;
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
/*     */   public boolean equals(Object obj)
/*     */   {
/* 328 */     if (obj != null) {
/* 329 */       if ((obj instanceof Quarter)) {
/* 330 */         Quarter target = (Quarter)obj;
/* 331 */         return (this.quarter == target.getQuarter()) && (this.year == target.getYearValue());
/*     */       }
/*     */       
/*     */ 
/* 335 */       return false;
/*     */     }
/*     */     
/*     */ 
/* 339 */     return false;
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
/*     */   public int hashCode()
/*     */   {
/* 354 */     int result = 17;
/* 355 */     result = 37 * result + this.quarter;
/* 356 */     result = 37 * result + this.year;
/* 357 */     return result;
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
/* 376 */     if ((o1 instanceof Quarter)) {
/* 377 */       Quarter q = (Quarter)o1;
/* 378 */       int result = this.year - q.getYearValue();
/* 379 */       if (result == 0) {
/* 380 */         result = this.quarter - q.getQuarter();
/*     */       }
/*     */     }
/*     */     else
/*     */     {
/*     */       int result;
/* 386 */       if ((o1 instanceof RegularTimePeriod))
/*     */       {
/* 388 */         result = 0;
/*     */ 
/*     */ 
/*     */       }
/*     */       else
/*     */       {
/*     */ 
/* 395 */         result = 1;
/*     */       }
/*     */     }
/* 398 */     return result;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public String toString()
/*     */   {
/* 408 */     return "Q" + this.quarter + "/" + this.year;
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
/* 423 */     int month = FIRST_MONTH_IN_QUARTER[this.quarter];
/* 424 */     calendar.set(this.year, month - 1, 1, 0, 0, 0);
/* 425 */     calendar.set(14, 0);
/*     */     
/*     */ 
/* 428 */     return calendar.getTime().getTime();
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
/* 443 */     int month = LAST_MONTH_IN_QUARTER[this.quarter];
/* 444 */     int eom = SerialDate.lastDayOfMonth(month, this.year);
/* 445 */     calendar.set(this.year, month - 1, eom, 23, 59, 59);
/* 446 */     calendar.set(14, 999);
/*     */     
/*     */ 
/* 449 */     return calendar.getTime().getTime();
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
/*     */   public static Quarter parseQuarter(String s)
/*     */   {
/* 465 */     int i = s.indexOf("Q");
/* 466 */     if (i == -1) {
/* 467 */       throw new TimePeriodFormatException("Missing Q.");
/*     */     }
/*     */     
/* 470 */     if (i == s.length() - 1) {
/* 471 */       throw new TimePeriodFormatException("Q found at end of string.");
/*     */     }
/*     */     
/* 474 */     String qstr = s.substring(i + 1, i + 2);
/* 475 */     int quarter = Integer.parseInt(qstr);
/* 476 */     String remaining = s.substring(0, i) + s.substring(i + 2, s.length());
/*     */     
/*     */ 
/* 479 */     remaining = remaining.replace('/', ' ');
/* 480 */     remaining = remaining.replace(',', ' ');
/* 481 */     remaining = remaining.replace('-', ' ');
/*     */     
/*     */ 
/* 484 */     Year year = Year.parseYear(remaining.trim());
/* 485 */     Quarter result = new Quarter(quarter, year);
/* 486 */     return result;
/*     */   }
/*     */ }


/* Location:              /home/menick/Scrivania/ProcessMining/CNMining/CNMining.jar!/org/jfree/data/time/Quarter.class
 * Java compiler version: 3 (47.0)
 * JD-Core Version:       0.7.1
 */