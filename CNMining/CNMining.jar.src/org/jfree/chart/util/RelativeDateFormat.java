/*     */ package org.jfree.chart.util;
/*     */ 
/*     */ import java.io.PrintStream;
/*     */ import java.text.DateFormat;
/*     */ import java.text.DecimalFormat;
/*     */ import java.text.FieldPosition;
/*     */ import java.text.NumberFormat;
/*     */ import java.text.ParsePosition;
/*     */ import java.util.Date;
/*     */ import java.util.GregorianCalendar;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class RelativeDateFormat
/*     */   extends DateFormat
/*     */ {
/*     */   private long baseMillis;
/*     */   private boolean showZeroDays;
/*     */   private boolean showZeroHours;
/*     */   private NumberFormat dayFormatter;
/*     */   private String positivePrefix;
/*     */   private String daySuffix;
/*     */   private NumberFormat hourFormatter;
/*     */   private String hourSuffix;
/*     */   private NumberFormat minuteFormatter;
/*     */   private String minuteSuffix;
/*     */   private NumberFormat secondFormatter;
/*     */   private String secondSuffix;
/* 137 */   private static long MILLISECONDS_IN_ONE_HOUR = 3600000L;
/*     */   
/*     */ 
/*     */ 
/*     */ 
/* 142 */   private static long MILLISECONDS_IN_ONE_DAY = 24L * MILLISECONDS_IN_ONE_HOUR;
/*     */   
/*     */ 
/*     */ 
/*     */   public RelativeDateFormat()
/*     */   {
/* 148 */     this(0L);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public RelativeDateFormat(Date time)
/*     */   {
/* 157 */     this(time.getTime());
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public RelativeDateFormat(long baseMillis)
/*     */   {
/* 167 */     this.baseMillis = baseMillis;
/* 168 */     this.showZeroDays = false;
/* 169 */     this.showZeroHours = true;
/* 170 */     this.positivePrefix = "";
/* 171 */     this.dayFormatter = NumberFormat.getNumberInstance();
/* 172 */     this.daySuffix = "d";
/* 173 */     this.hourFormatter = NumberFormat.getNumberInstance();
/* 174 */     this.hourSuffix = "h";
/* 175 */     this.minuteFormatter = NumberFormat.getNumberInstance();
/* 176 */     this.minuteSuffix = "m";
/* 177 */     this.secondFormatter = NumberFormat.getNumberInstance();
/* 178 */     this.secondFormatter.setMaximumFractionDigits(3);
/* 179 */     this.secondFormatter.setMinimumFractionDigits(3);
/* 180 */     this.secondSuffix = "s";
/*     */     
/*     */ 
/*     */ 
/* 184 */     this.calendar = new GregorianCalendar();
/* 185 */     this.numberFormat = new DecimalFormat("0");
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public long getBaseMillis()
/*     */   {
/* 197 */     return this.baseMillis;
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
/*     */   public void setBaseMillis(long baseMillis)
/*     */   {
/* 210 */     this.baseMillis = baseMillis;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public boolean getShowZeroDays()
/*     */   {
/* 222 */     return this.showZeroDays;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public void setShowZeroDays(boolean show)
/*     */   {
/* 234 */     this.showZeroDays = show;
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
/*     */   public boolean getShowZeroHours()
/*     */   {
/* 248 */     return this.showZeroHours;
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
/*     */   public void setShowZeroHours(boolean show)
/*     */   {
/* 262 */     this.showZeroHours = show;
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
/*     */   public String getPositivePrefix()
/*     */   {
/* 276 */     return this.positivePrefix;
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
/*     */   public void setPositivePrefix(String prefix)
/*     */   {
/* 290 */     if (prefix == null) {
/* 291 */       throw new IllegalArgumentException("Null 'prefix' argument.");
/*     */     }
/* 293 */     this.positivePrefix = prefix;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public void setDayFormatter(NumberFormat formatter)
/*     */   {
/* 304 */     if (formatter == null) {
/* 305 */       throw new IllegalArgumentException("Null 'formatter' argument.");
/*     */     }
/* 307 */     this.dayFormatter = formatter;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public String getDaySuffix()
/*     */   {
/* 318 */     return this.daySuffix;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public void setDaySuffix(String suffix)
/*     */   {
/* 329 */     if (suffix == null) {
/* 330 */       throw new IllegalArgumentException("Null 'suffix' argument.");
/*     */     }
/* 332 */     this.daySuffix = suffix;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public void setHourFormatter(NumberFormat formatter)
/*     */   {
/* 343 */     if (formatter == null) {
/* 344 */       throw new IllegalArgumentException("Null 'formatter' argument.");
/*     */     }
/* 346 */     this.hourFormatter = formatter;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public String getHourSuffix()
/*     */   {
/* 357 */     return this.hourSuffix;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public void setHourSuffix(String suffix)
/*     */   {
/* 368 */     if (suffix == null) {
/* 369 */       throw new IllegalArgumentException("Null 'suffix' argument.");
/*     */     }
/* 371 */     this.hourSuffix = suffix;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public void setMinuteFormatter(NumberFormat formatter)
/*     */   {
/* 382 */     if (formatter == null) {
/* 383 */       throw new IllegalArgumentException("Null 'formatter' argument.");
/*     */     }
/* 385 */     this.minuteFormatter = formatter;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public String getMinuteSuffix()
/*     */   {
/* 396 */     return this.minuteSuffix;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public void setMinuteSuffix(String suffix)
/*     */   {
/* 407 */     if (suffix == null) {
/* 408 */       throw new IllegalArgumentException("Null 'suffix' argument.");
/*     */     }
/* 410 */     this.minuteSuffix = suffix;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public String getSecondSuffix()
/*     */   {
/* 421 */     return this.secondSuffix;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public void setSecondSuffix(String suffix)
/*     */   {
/* 432 */     if (suffix == null) {
/* 433 */       throw new IllegalArgumentException("Null 'suffix' argument.");
/*     */     }
/* 435 */     this.secondSuffix = suffix;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public void setSecondFormatter(NumberFormat formatter)
/*     */   {
/* 444 */     if (formatter == null) {
/* 445 */       throw new IllegalArgumentException("Null 'formatter' argument.");
/*     */     }
/* 447 */     this.secondFormatter = formatter;
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
/*     */   public StringBuffer format(Date date, StringBuffer toAppendTo, FieldPosition fieldPosition)
/*     */   {
/* 462 */     long currentMillis = date.getTime();
/* 463 */     long elapsed = currentMillis - this.baseMillis;
/*     */     String signPrefix;
/* 465 */     String signPrefix; if (elapsed < 0L) {
/* 466 */       elapsed *= -1L;
/* 467 */       signPrefix = "-";
/*     */     }
/*     */     else {
/* 470 */       signPrefix = this.positivePrefix;
/*     */     }
/*     */     
/* 473 */     long days = elapsed / MILLISECONDS_IN_ONE_DAY;
/* 474 */     elapsed -= days * MILLISECONDS_IN_ONE_DAY;
/* 475 */     long hours = elapsed / MILLISECONDS_IN_ONE_HOUR;
/* 476 */     elapsed -= hours * MILLISECONDS_IN_ONE_HOUR;
/* 477 */     long minutes = elapsed / 60000L;
/* 478 */     elapsed -= minutes * 60000L;
/* 479 */     double seconds = elapsed / 1000.0D;
/*     */     
/* 481 */     toAppendTo.append(signPrefix);
/* 482 */     if ((days != 0L) || (this.showZeroDays)) {
/* 483 */       toAppendTo.append(this.dayFormatter.format(days) + getDaySuffix());
/*     */     }
/* 485 */     if ((hours != 0L) || (this.showZeroHours)) {
/* 486 */       toAppendTo.append(this.hourFormatter.format(hours) + getHourSuffix());
/*     */     }
/*     */     
/* 489 */     toAppendTo.append(this.minuteFormatter.format(minutes) + getMinuteSuffix());
/*     */     
/* 491 */     toAppendTo.append(this.secondFormatter.format(seconds) + getSecondSuffix());
/*     */     
/* 493 */     return toAppendTo;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public Date parse(String source, ParsePosition pos)
/*     */   {
/* 505 */     return null;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public boolean equals(Object obj)
/*     */   {
/* 516 */     if (obj == this) {
/* 517 */       return true;
/*     */     }
/* 519 */     if (!(obj instanceof RelativeDateFormat)) {
/* 520 */       return false;
/*     */     }
/* 522 */     if (!super.equals(obj)) {
/* 523 */       return false;
/*     */     }
/* 525 */     RelativeDateFormat that = (RelativeDateFormat)obj;
/* 526 */     if (this.baseMillis != that.baseMillis) {
/* 527 */       return false;
/*     */     }
/* 529 */     if (this.showZeroDays != that.showZeroDays) {
/* 530 */       return false;
/*     */     }
/* 532 */     if (this.showZeroHours != that.showZeroHours) {
/* 533 */       return false;
/*     */     }
/* 535 */     if (!this.positivePrefix.equals(that.positivePrefix)) {
/* 536 */       return false;
/*     */     }
/* 538 */     if (!this.daySuffix.equals(that.daySuffix)) {
/* 539 */       return false;
/*     */     }
/* 541 */     if (!this.hourSuffix.equals(that.hourSuffix)) {
/* 542 */       return false;
/*     */     }
/* 544 */     if (!this.minuteSuffix.equals(that.minuteSuffix)) {
/* 545 */       return false;
/*     */     }
/* 547 */     if (!this.secondSuffix.equals(that.secondSuffix)) {
/* 548 */       return false;
/*     */     }
/* 550 */     if (!this.dayFormatter.equals(that.dayFormatter)) {
/* 551 */       return false;
/*     */     }
/* 553 */     if (!this.hourFormatter.equals(that.hourFormatter)) {
/* 554 */       return false;
/*     */     }
/* 556 */     if (!this.minuteFormatter.equals(that.minuteFormatter)) {
/* 557 */       return false;
/*     */     }
/* 559 */     if (!this.secondFormatter.equals(that.secondFormatter)) {
/* 560 */       return false;
/*     */     }
/* 562 */     return true;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public int hashCode()
/*     */   {
/* 571 */     int result = 193;
/* 572 */     result = 37 * result + (int)(this.baseMillis ^ this.baseMillis >>> 32);
/*     */     
/* 574 */     result = 37 * result + this.positivePrefix.hashCode();
/* 575 */     result = 37 * result + this.daySuffix.hashCode();
/* 576 */     result = 37 * result + this.hourSuffix.hashCode();
/* 577 */     result = 37 * result + this.minuteSuffix.hashCode();
/* 578 */     result = 37 * result + this.secondSuffix.hashCode();
/* 579 */     result = 37 * result + this.secondFormatter.hashCode();
/* 580 */     return result;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public Object clone()
/*     */   {
/* 589 */     RelativeDateFormat clone = (RelativeDateFormat)super.clone();
/* 590 */     clone.dayFormatter = ((NumberFormat)this.dayFormatter.clone());
/* 591 */     clone.secondFormatter = ((NumberFormat)this.secondFormatter.clone());
/* 592 */     return clone;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public static void main(String[] args)
/*     */   {
/* 601 */     GregorianCalendar c0 = new GregorianCalendar(2006, 10, 1, 0, 0, 0);
/* 602 */     GregorianCalendar c1 = new GregorianCalendar(2006, 10, 1, 11, 37, 43);
/* 603 */     c1.set(14, 123);
/*     */     
/* 605 */     System.out.println("Default: ");
/* 606 */     RelativeDateFormat rdf = new RelativeDateFormat(c0.getTime().getTime());
/* 607 */     System.out.println(rdf.format(c1.getTime()));
/* 608 */     System.out.println();
/*     */     
/* 610 */     System.out.println("Hide milliseconds: ");
/* 611 */     rdf.setSecondFormatter(new DecimalFormat("0"));
/* 612 */     System.out.println(rdf.format(c1.getTime()));
/* 613 */     System.out.println();
/*     */     
/* 615 */     System.out.println("Show zero day output: ");
/* 616 */     rdf.setShowZeroDays(true);
/* 617 */     System.out.println(rdf.format(c1.getTime()));
/* 618 */     System.out.println();
/*     */     
/* 620 */     System.out.println("Alternative suffixes: ");
/* 621 */     rdf.setShowZeroDays(false);
/* 622 */     rdf.setDaySuffix(":");
/* 623 */     rdf.setHourSuffix(":");
/* 624 */     rdf.setMinuteSuffix(":");
/* 625 */     rdf.setSecondSuffix("");
/* 626 */     System.out.println(rdf.format(c1.getTime()));
/* 627 */     System.out.println();
/*     */   }
/*     */ }


/* Location:              /home/menick/Scrivania/ProcessMining/CNMining/CNMining.jar!/org/jfree/chart/util/RelativeDateFormat.class
 * Java compiler version: 3 (47.0)
 * JD-Core Version:       0.7.1
 */