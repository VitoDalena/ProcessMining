/*     */ package org.jfree.chart.axis;
/*     */ 
/*     */ import java.io.PrintStream;
/*     */ import java.text.DateFormat;
/*     */ import java.text.DateFormatSymbols;
/*     */ import java.text.FieldPosition;
/*     */ import java.text.NumberFormat;
/*     */ import java.text.ParsePosition;
/*     */ import java.text.SimpleDateFormat;
/*     */ import java.util.Arrays;
/*     */ import java.util.Calendar;
/*     */ import java.util.Date;
/*     */ import java.util.GregorianCalendar;
/*     */ import java.util.Locale;
/*     */ import java.util.TimeZone;
/*     */ import org.jfree.data.time.Month;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class MonthDateFormat
/*     */   extends DateFormat
/*     */ {
/*     */   private String[] months;
/*     */   private boolean[] showYear;
/*     */   private DateFormat yearFormatter;
/*     */   
/*     */   public MonthDateFormat()
/*     */   {
/*  77 */     this(TimeZone.getDefault());
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public MonthDateFormat(TimeZone zone)
/*     */   {
/*  86 */     this(zone, Locale.getDefault(), 1, true, false);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public MonthDateFormat(Locale locale)
/*     */   {
/*  96 */     this(TimeZone.getDefault(), locale, 1, true, false);
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
/*     */   public MonthDateFormat(TimeZone zone, int chars)
/*     */   {
/* 109 */     this(zone, Locale.getDefault(), chars, true, false);
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
/*     */   public MonthDateFormat(Locale locale, int chars)
/*     */   {
/* 122 */     this(TimeZone.getDefault(), locale, chars, true, false);
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
/*     */   public MonthDateFormat(TimeZone zone, Locale locale, int chars, boolean showYearForJan, boolean showYearForDec)
/*     */   {
/* 144 */     this(zone, locale, chars, new boolean[] { showYearForJan, false, false, false, false, false, false, false, false, false, false, false, showYearForDec }, new SimpleDateFormat("yy"));
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
/*     */   public MonthDateFormat(TimeZone zone, Locale locale, int chars, boolean[] showYear, DateFormat yearFormatter)
/*     */   {
/* 165 */     if (locale == null) {
/* 166 */       throw new IllegalArgumentException("Null 'locale' argument.");
/*     */     }
/* 168 */     DateFormatSymbols dfs = new DateFormatSymbols(locale);
/* 169 */     String[] monthsFromLocale = dfs.getMonths();
/* 170 */     this.months = new String[12];
/* 171 */     for (int i = 0; i < 12; i++) {
/* 172 */       if (chars > 0) {
/* 173 */         this.months[i] = monthsFromLocale[i].substring(0, Math.min(chars, monthsFromLocale[i].length()));
/*     */       }
/*     */       else
/*     */       {
/* 177 */         this.months[i] = monthsFromLocale[i];
/*     */       }
/*     */     }
/* 180 */     this.calendar = new GregorianCalendar(zone);
/* 181 */     this.showYear = showYear;
/* 182 */     this.yearFormatter = yearFormatter;
/*     */     
/*     */ 
/*     */ 
/*     */ 
/* 187 */     this.numberFormat = NumberFormat.getNumberInstance();
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
/*     */   public StringBuffer format(Date date, StringBuffer toAppendTo, FieldPosition fieldPosition)
/*     */   {
/* 201 */     this.calendar.setTime(date);
/* 202 */     int month = this.calendar.get(2);
/* 203 */     toAppendTo.append(this.months[month]);
/* 204 */     if (this.showYear[month] != 0) {
/* 205 */       toAppendTo.append(this.yearFormatter.format(date));
/*     */     }
/* 207 */     return toAppendTo;
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
/* 219 */     return null;
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
/* 230 */     if (obj == this) {
/* 231 */       return true;
/*     */     }
/* 233 */     if (!(obj instanceof MonthDateFormat)) {
/* 234 */       return false;
/*     */     }
/* 236 */     if (!super.equals(obj)) {
/* 237 */       return false;
/*     */     }
/* 239 */     MonthDateFormat that = (MonthDateFormat)obj;
/* 240 */     if (!Arrays.equals(this.months, that.months)) {
/* 241 */       return false;
/*     */     }
/* 243 */     if (!Arrays.equals(this.showYear, that.showYear)) {
/* 244 */       return false;
/*     */     }
/* 246 */     if (!this.yearFormatter.equals(that.yearFormatter)) {
/* 247 */       return false;
/*     */     }
/* 249 */     return true;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public static void main(String[] args)
/*     */   {
/* 258 */     MonthDateFormat mdf = new MonthDateFormat(Locale.UK, 2);
/* 259 */     System.out.println("UK:");
/* 260 */     System.out.println(mdf.format(new Month(1, 2005).getStart()));
/* 261 */     System.out.println(mdf.format(new Month(2, 2005).getStart()));
/* 262 */     System.out.println(mdf.format(new Month(3, 2005).getStart()));
/* 263 */     System.out.println(mdf.format(new Month(4, 2005).getStart()));
/* 264 */     System.out.println(mdf.format(new Month(5, 2005).getStart()));
/* 265 */     System.out.println(mdf.format(new Month(6, 2005).getStart()));
/* 266 */     System.out.println(mdf.format(new Month(7, 2005).getStart()));
/* 267 */     System.out.println(mdf.format(new Month(8, 2005).getStart()));
/* 268 */     System.out.println(mdf.format(new Month(9, 2005).getStart()));
/* 269 */     System.out.println(mdf.format(new Month(10, 2005).getStart()));
/* 270 */     System.out.println(mdf.format(new Month(11, 2005).getStart()));
/* 271 */     System.out.println(mdf.format(new Month(12, 2005).getStart()));
/* 272 */     System.out.println();
/*     */     
/* 274 */     mdf = new MonthDateFormat(Locale.GERMANY, 2);
/* 275 */     System.out.println("GERMANY:");
/* 276 */     System.out.println(mdf.format(new Month(1, 2005).getStart()));
/* 277 */     System.out.println(mdf.format(new Month(2, 2005).getStart()));
/* 278 */     System.out.println(mdf.format(new Month(3, 2005).getStart()));
/* 279 */     System.out.println(mdf.format(new Month(4, 2005).getStart()));
/* 280 */     System.out.println(mdf.format(new Month(5, 2005).getStart()));
/* 281 */     System.out.println(mdf.format(new Month(6, 2005).getStart()));
/* 282 */     System.out.println(mdf.format(new Month(7, 2005).getStart()));
/* 283 */     System.out.println(mdf.format(new Month(8, 2005).getStart()));
/* 284 */     System.out.println(mdf.format(new Month(9, 2005).getStart()));
/* 285 */     System.out.println(mdf.format(new Month(10, 2005).getStart()));
/* 286 */     System.out.println(mdf.format(new Month(11, 2005).getStart()));
/* 287 */     System.out.println(mdf.format(new Month(12, 2005).getStart()));
/* 288 */     System.out.println();
/*     */     
/* 290 */     mdf = new MonthDateFormat(Locale.FRANCE, 2);
/* 291 */     System.out.println("FRANCE:");
/* 292 */     System.out.println(mdf.format(new Month(1, 2005).getStart()));
/* 293 */     System.out.println(mdf.format(new Month(2, 2005).getStart()));
/* 294 */     System.out.println(mdf.format(new Month(3, 2005).getStart()));
/* 295 */     System.out.println(mdf.format(new Month(4, 2005).getStart()));
/* 296 */     System.out.println(mdf.format(new Month(5, 2005).getStart()));
/* 297 */     System.out.println(mdf.format(new Month(6, 2005).getStart()));
/* 298 */     System.out.println(mdf.format(new Month(7, 2005).getStart()));
/* 299 */     System.out.println(mdf.format(new Month(8, 2005).getStart()));
/* 300 */     System.out.println(mdf.format(new Month(9, 2005).getStart()));
/* 301 */     System.out.println(mdf.format(new Month(10, 2005).getStart()));
/* 302 */     System.out.println(mdf.format(new Month(11, 2005).getStart()));
/* 303 */     System.out.println(mdf.format(new Month(12, 2005).getStart()));
/* 304 */     System.out.println();
/*     */     
/* 306 */     SimpleDateFormat sdf = new SimpleDateFormat("yyyy");
/* 307 */     sdf.setNumberFormat(null);
/*     */   }
/*     */ }


/* Location:              /home/menick/Scrivania/ProcessMining/CNMining/CNMining.jar!/org/jfree/chart/axis/MonthDateFormat.class
 * Java compiler version: 3 (47.0)
 * JD-Core Version:       0.7.1
 */