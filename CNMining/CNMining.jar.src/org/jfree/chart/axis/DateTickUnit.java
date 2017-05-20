/*     */ package org.jfree.chart.axis;
/*     */ 
/*     */ import java.io.Serializable;
/*     */ import java.text.DateFormat;
/*     */ import java.util.Calendar;
/*     */ import java.util.Date;
/*     */ import java.util.TimeZone;
/*     */ import org.jfree.util.ObjectUtilities;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class DateTickUnit
/*     */   extends TickUnit
/*     */   implements Serializable
/*     */ {
/*     */   private static final long serialVersionUID = -7289292157229621901L;
/*     */   private DateTickUnitType unitType;
/*     */   private int count;
/*     */   private DateTickUnitType rollUnitType;
/*     */   private int rollCount;
/*     */   private DateFormat formatter;
/*     */   /**
/*     */    * @deprecated
/*     */    */
/*     */   public static final int YEAR = 0;
/*     */   /**
/*     */    * @deprecated
/*     */    */
/*     */   public static final int MONTH = 1;
/*     */   /**
/*     */    * @deprecated
/*     */    */
/*     */   public static final int DAY = 2;
/*     */   /**
/*     */    * @deprecated
/*     */    */
/*     */   public static final int HOUR = 3;
/*     */   /**
/*     */    * @deprecated
/*     */    */
/*     */   public static final int MINUTE = 4;
/*     */   /**
/*     */    * @deprecated
/*     */    */
/*     */   public static final int SECOND = 5;
/*     */   /**
/*     */    * @deprecated
/*     */    */
/*     */   public static final int MILLISECOND = 6;
/*     */   /**
/*     */    * @deprecated
/*     */    */
/*     */   private int unit;
/*     */   /**
/*     */    * @deprecated
/*     */    */
/*     */   private int rollUnit;
/*     */   
/*     */   public DateTickUnit(DateTickUnitType unitType, int multiple)
/*     */   {
/* 107 */     this(unitType, multiple, DateFormat.getDateInstance(3));
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
/*     */   public DateTickUnit(DateTickUnitType unitType, int multiple, DateFormat formatter)
/*     */   {
/* 121 */     this(unitType, multiple, unitType, multiple, formatter);
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
/*     */   public DateTickUnit(DateTickUnitType unitType, int multiple, DateTickUnitType rollUnitType, int rollMultiple, DateFormat formatter)
/*     */   {
/* 138 */     super(getMillisecondCount(unitType, multiple));
/* 139 */     if (formatter == null) {
/* 140 */       throw new IllegalArgumentException("Null 'formatter' argument.");
/*     */     }
/* 142 */     if (multiple <= 0) {
/* 143 */       throw new IllegalArgumentException("Requires 'multiple' > 0.");
/*     */     }
/* 145 */     if (rollMultiple <= 0) {
/* 146 */       throw new IllegalArgumentException("Requires 'rollMultiple' > 0.");
/*     */     }
/* 148 */     this.unitType = unitType;
/* 149 */     this.count = multiple;
/* 150 */     this.rollUnitType = rollUnitType;
/* 151 */     this.rollCount = rollMultiple;
/* 152 */     this.formatter = formatter;
/*     */     
/*     */ 
/* 155 */     this.unit = unitTypeToInt(unitType);
/* 156 */     this.rollUnit = unitTypeToInt(rollUnitType);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public DateTickUnitType getUnitType()
/*     */   {
/* 167 */     return this.unitType;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public int getMultiple()
/*     */   {
/* 176 */     return this.count;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public DateTickUnitType getRollUnitType()
/*     */   {
/* 187 */     return this.rollUnitType;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public int getRollMultiple()
/*     */   {
/* 198 */     return this.rollCount;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public String valueToString(double milliseconds)
/*     */   {
/* 209 */     return this.formatter.format(new Date(milliseconds));
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public String dateToString(Date date)
/*     */   {
/* 220 */     return this.formatter.format(date);
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
/*     */ 
/*     */   public Date addToDate(Date base, TimeZone zone)
/*     */   {
/* 238 */     Calendar calendar = Calendar.getInstance(zone);
/* 239 */     calendar.setTime(base);
/* 240 */     calendar.add(this.unitType.getCalendarField(), this.count);
/* 241 */     return calendar.getTime();
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
/*     */   public Date rollDate(Date base)
/*     */   {
/* 255 */     return rollDate(base, TimeZone.getDefault());
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
/*     */ 
/*     */ 
/*     */   public Date rollDate(Date base, TimeZone zone)
/*     */   {
/* 274 */     Calendar calendar = Calendar.getInstance(zone);
/* 275 */     calendar.setTime(base);
/* 276 */     calendar.add(this.rollUnitType.getCalendarField(), this.rollCount);
/* 277 */     return calendar.getTime();
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public int getCalendarField()
/*     */   {
/* 287 */     return this.unitType.getCalendarField();
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
/*     */ 
/*     */ 
/*     */   private static long getMillisecondCount(DateTickUnitType unit, int count)
/*     */   {
/* 306 */     if (unit.equals(DateTickUnitType.YEAR)) {
/* 307 */       return 31536000000L * count;
/*     */     }
/* 309 */     if (unit.equals(DateTickUnitType.MONTH)) {
/* 310 */       return 2678400000L * count;
/*     */     }
/* 312 */     if (unit.equals(DateTickUnitType.DAY)) {
/* 313 */       return 86400000L * count;
/*     */     }
/* 315 */     if (unit.equals(DateTickUnitType.HOUR)) {
/* 316 */       return 3600000L * count;
/*     */     }
/* 318 */     if (unit.equals(DateTickUnitType.MINUTE)) {
/* 319 */       return 60000L * count;
/*     */     }
/* 321 */     if (unit.equals(DateTickUnitType.SECOND)) {
/* 322 */       return 1000L * count;
/*     */     }
/* 324 */     if (unit.equals(DateTickUnitType.MILLISECOND)) {
/* 325 */       return count;
/*     */     }
/*     */     
/* 328 */     throw new IllegalArgumentException("The 'unit' argument has a value that is not recognised.");
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
/*     */   private static DateTickUnitType intToUnitType(int unit)
/*     */   {
/* 345 */     switch (unit) {
/* 346 */     case 0:  return DateTickUnitType.YEAR;
/* 347 */     case 1:  return DateTickUnitType.MONTH;
/* 348 */     case 2:  return DateTickUnitType.DAY;
/* 349 */     case 3:  return DateTickUnitType.HOUR;
/* 350 */     case 4:  return DateTickUnitType.MINUTE;
/* 351 */     case 5:  return DateTickUnitType.SECOND;
/* 352 */     case 6:  return DateTickUnitType.MILLISECOND; }
/* 353 */     throw new IllegalArgumentException("Unrecognised 'unit' value " + unit + ".");
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
/*     */   private static int unitTypeToInt(DateTickUnitType unitType)
/*     */   {
/* 368 */     if (unitType == null) {
/* 369 */       throw new IllegalArgumentException("Null 'unitType' argument.");
/*     */     }
/* 371 */     if (unitType.equals(DateTickUnitType.YEAR)) {
/* 372 */       return 0;
/*     */     }
/* 374 */     if (unitType.equals(DateTickUnitType.MONTH)) {
/* 375 */       return 1;
/*     */     }
/* 377 */     if (unitType.equals(DateTickUnitType.DAY)) {
/* 378 */       return 2;
/*     */     }
/* 380 */     if (unitType.equals(DateTickUnitType.HOUR)) {
/* 381 */       return 3;
/*     */     }
/* 383 */     if (unitType.equals(DateTickUnitType.MINUTE)) {
/* 384 */       return 4;
/*     */     }
/* 386 */     if (unitType.equals(DateTickUnitType.SECOND)) {
/* 387 */       return 5;
/*     */     }
/* 389 */     if (unitType.equals(DateTickUnitType.MILLISECOND)) {
/* 390 */       return 6;
/*     */     }
/*     */     
/* 393 */     throw new IllegalArgumentException("The 'unitType' is not recognised");
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
/*     */   private static DateFormat notNull(DateFormat formatter)
/*     */   {
/* 407 */     if (formatter == null) {
/* 408 */       return DateFormat.getDateInstance(3);
/*     */     }
/*     */     
/* 411 */     return formatter;
/*     */   }
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
/* 423 */     if (obj == this) {
/* 424 */       return true;
/*     */     }
/* 426 */     if (!(obj instanceof DateTickUnit)) {
/* 427 */       return false;
/*     */     }
/* 429 */     if (!super.equals(obj)) {
/* 430 */       return false;
/*     */     }
/* 432 */     DateTickUnit that = (DateTickUnit)obj;
/* 433 */     if (!this.unitType.equals(that.unitType)) {
/* 434 */       return false;
/*     */     }
/* 436 */     if (this.count != that.count) {
/* 437 */       return false;
/*     */     }
/* 439 */     if (!ObjectUtilities.equal(this.formatter, that.formatter)) {
/* 440 */       return false;
/*     */     }
/* 442 */     return true;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public int hashCode()
/*     */   {
/* 451 */     int result = 19;
/* 452 */     result = 37 * result + this.unitType.hashCode();
/* 453 */     result = 37 * result + this.count;
/* 454 */     result = 37 * result + this.formatter.hashCode();
/* 455 */     return result;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public String toString()
/*     */   {
/* 465 */     return "DateTickUnit[" + this.unitType.toString() + ", " + this.count + "]";
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
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
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
/*     */   public DateTickUnit(int unit, int count, DateFormat formatter)
/*     */   {
/* 545 */     this(unit, count, unit, count, formatter);
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
/*     */   public DateTickUnit(int unit, int count)
/*     */   {
/* 559 */     this(unit, count, null);
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
/*     */   /**
/*     */    * @deprecated
/*     */    */
/*     */   public DateTickUnit(int unit, int count, int rollUnit, int rollCount, DateFormat formatter)
/*     */   {
/* 576 */     this(intToUnitType(unit), count, intToUnitType(rollUnit), rollCount, notNull(formatter));
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
/*     */   /**
/*     */    * @deprecated
/*     */    */
/*     */   public int getUnit()
/*     */   {
/* 593 */     return this.unit;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   /**
/*     */    * @deprecated
/*     */    */
/*     */   public int getCount()
/*     */   {
/* 604 */     return this.count;
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
/*     */   public int getRollUnit()
/*     */   {
/* 618 */     return this.rollUnit;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   /**
/*     */    * @deprecated
/*     */    */
/*     */   public int getRollCount()
/*     */   {
/* 630 */     return this.rollCount;
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
/*     */   /**
/*     */    * @deprecated
/*     */    */
/*     */   public Date addToDate(Date base)
/*     */   {
/* 647 */     return addToDate(base, TimeZone.getDefault());
/*     */   }
/*     */ }


/* Location:              /home/menick/Scrivania/ProcessMining/CNMining/CNMining.jar!/org/jfree/chart/axis/DateTickUnit.class
 * Java compiler version: 3 (47.0)
 * JD-Core Version:       0.7.1
 */