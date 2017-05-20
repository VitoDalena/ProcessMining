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
/*     */ public class Year
/*     */   extends RegularTimePeriod
/*     */   implements Serializable
/*     */ {
/*     */   public static final int MINIMUM_YEAR = -9999;
/*     */   public static final int MAXIMUM_YEAR = 9999;
/*     */   private static final long serialVersionUID = -7659990929736074836L;
/*     */   private short year;
/*     */   private long firstMillisecond;
/*     */   private long lastMillisecond;
/*     */   
/*     */   public Year()
/*     */   {
/* 106 */     this(new Date());
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public Year(int year)
/*     */   {
/* 115 */     if ((year < 55537) || (year > 9999)) {
/* 116 */       throw new IllegalArgumentException("Year constructor: year (" + year + ") outside valid range.");
/*     */     }
/*     */     
/* 119 */     this.year = ((short)year);
/* 120 */     peg(Calendar.getInstance());
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public Year(Date time)
/*     */   {
/* 132 */     this(time, TimeZone.getDefault());
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
/*     */   public Year(Date time, TimeZone zone)
/*     */   {
/* 145 */     this(time, zone, Locale.getDefault());
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
/*     */   public Year(Date time, TimeZone zone, Locale locale)
/*     */   {
/* 159 */     Calendar calendar = Calendar.getInstance(zone, locale);
/* 160 */     calendar.setTime(time);
/* 161 */     this.year = ((short)calendar.get(1));
/* 162 */     peg(calendar);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public int getYear()
/*     */   {
/* 171 */     return this.year;
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
/* 185 */     return this.firstMillisecond;
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
/* 199 */     return this.lastMillisecond;
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
/* 211 */     this.firstMillisecond = getFirstMillisecond(calendar);
/* 212 */     this.lastMillisecond = getLastMillisecond(calendar);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public RegularTimePeriod previous()
/*     */   {
/* 222 */     if (this.year > 55537) {
/* 223 */       return new Year(this.year - 1);
/*     */     }
/*     */     
/* 226 */     return null;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public RegularTimePeriod next()
/*     */   {
/* 237 */     if (this.year < 9999) {
/* 238 */       return new Year(this.year + 1);
/*     */     }
/*     */     
/* 241 */     return null;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public long getSerialIndex()
/*     */   {
/* 253 */     return this.year;
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
/* 268 */     calendar.set(this.year, 0, 1, 0, 0, 0);
/* 269 */     calendar.set(14, 0);
/*     */     
/*     */ 
/* 272 */     return calendar.getTime().getTime();
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
/* 287 */     calendar.set(this.year, 11, 31, 23, 59, 59);
/* 288 */     calendar.set(14, 999);
/*     */     
/*     */ 
/* 291 */     return calendar.getTime().getTime();
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
/* 306 */     if (obj == this) {
/* 307 */       return true;
/*     */     }
/* 309 */     if (!(obj instanceof Year)) {
/* 310 */       return false;
/*     */     }
/* 312 */     Year that = (Year)obj;
/* 313 */     return this.year == that.year;
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
/* 326 */     int result = 17;
/* 327 */     int c = this.year;
/* 328 */     result = 37 * result + c;
/* 329 */     return result;
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
/*     */ 
/* 348 */     if ((o1 instanceof Year)) {
/* 349 */       Year y = (Year)o1;
/* 350 */       result = this.year - y.getYear();
/*     */     }
/*     */     else
/*     */     {
/*     */       int result;
/* 355 */       if ((o1 instanceof RegularTimePeriod))
/*     */       {
/* 357 */         result = 0;
/*     */ 
/*     */ 
/*     */       }
/*     */       else
/*     */       {
/*     */ 
/* 364 */         result = 1;
/*     */       }
/*     */     }
/* 367 */     return result;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public String toString()
/*     */   {
/* 377 */     return Integer.toString(this.year);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public static Year parseYear(String s)
/*     */   {
/*     */     int y;
/*     */     
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     try
/*     */     {
/* 395 */       y = Integer.parseInt(s.trim());
/*     */     }
/*     */     catch (NumberFormatException e) {
/* 398 */       throw new TimePeriodFormatException("Cannot parse string.");
/*     */     }
/*     */     
/*     */     try
/*     */     {
/* 403 */       return new Year(y);
/*     */     }
/*     */     catch (IllegalArgumentException e) {
/* 406 */       throw new TimePeriodFormatException("Year outside valid range.");
/*     */     }
/*     */   }
/*     */ }


/* Location:              /home/menick/Scrivania/ProcessMining/CNMining/CNMining.jar!/org/jfree/data/time/Year.class
 * Java compiler version: 3 (47.0)
 * JD-Core Version:       0.7.1
 */