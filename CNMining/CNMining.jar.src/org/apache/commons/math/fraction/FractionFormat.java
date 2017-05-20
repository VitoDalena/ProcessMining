/*     */ package org.apache.commons.math.fraction;
/*     */ 
/*     */ import java.io.Serializable;
/*     */ import java.text.FieldPosition;
/*     */ import java.text.Format;
/*     */ import java.text.NumberFormat;
/*     */ import java.text.ParseException;
/*     */ import java.text.ParsePosition;
/*     */ import java.util.Locale;
/*     */ import org.apache.commons.math.ConvergenceException;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class FractionFormat
/*     */   extends Format
/*     */   implements Serializable
/*     */ {
/*     */   private static final long serialVersionUID = -6337346779577272306L;
/*     */   private NumberFormat denominatorFormat;
/*     */   private NumberFormat numeratorFormat;
/*     */   
/*     */   public FractionFormat()
/*     */   {
/*  54 */     this(getDefaultNumberFormat());
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public FractionFormat(NumberFormat format)
/*     */   {
/*  63 */     this(format, (NumberFormat)format.clone());
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
/*     */   public FractionFormat(NumberFormat numeratorFormat, NumberFormat denominatorFormat)
/*     */   {
/*  76 */     this.numeratorFormat = numeratorFormat;
/*  77 */     this.denominatorFormat = denominatorFormat;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public static String formatFraction(Fraction f)
/*     */   {
/*  88 */     return getImproperInstance().format(f);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public static Locale[] getAvailableLocales()
/*     */   {
/*  97 */     return NumberFormat.getAvailableLocales();
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   public static FractionFormat getImproperInstance()
/*     */   {
/* 105 */     return getImproperInstance(Locale.getDefault());
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public static FractionFormat getImproperInstance(Locale locale)
/*     */   {
/* 114 */     NumberFormat f = getDefaultNumberFormat(locale);
/* 115 */     return new FractionFormat(f);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   public static FractionFormat getProperInstance()
/*     */   {
/* 123 */     return getProperInstance(Locale.getDefault());
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public static FractionFormat getProperInstance(Locale locale)
/*     */   {
/* 132 */     NumberFormat f = getDefaultNumberFormat(locale);
/* 133 */     return new ProperFractionFormat(f);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   protected static NumberFormat getDefaultNumberFormat()
/*     */   {
/* 143 */     return getDefaultNumberFormat(Locale.getDefault());
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   private static NumberFormat getDefaultNumberFormat(Locale locale)
/*     */   {
/* 154 */     NumberFormat nf = NumberFormat.getNumberInstance(locale);
/* 155 */     nf.setMaximumFractionDigits(0);
/* 156 */     nf.setParseIntegerOnly(true);
/* 157 */     return nf;
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
/*     */   public StringBuffer format(Fraction fraction, StringBuffer toAppendTo, FieldPosition pos)
/*     */   {
/* 173 */     pos.setBeginIndex(0);
/* 174 */     pos.setEndIndex(0);
/*     */     
/* 176 */     getNumeratorFormat().format(fraction.getNumerator(), toAppendTo, pos);
/* 177 */     toAppendTo.append(" / ");
/* 178 */     getDenominatorFormat().format(fraction.getDenominator(), toAppendTo, pos);
/*     */     
/*     */ 
/* 181 */     return toAppendTo;
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
/*     */   public StringBuffer format(Object obj, StringBuffer toAppendTo, FieldPosition pos)
/*     */   {
/* 200 */     StringBuffer ret = null;
/*     */     
/* 202 */     if ((obj instanceof Fraction)) {
/* 203 */       ret = format((Fraction)obj, toAppendTo, pos);
/* 204 */     } else if ((obj instanceof Number)) {
/*     */       try {
/* 206 */         ret = format(new Fraction(((Number)obj).doubleValue()), toAppendTo, pos);
/*     */       }
/*     */       catch (ConvergenceException ex) {
/* 209 */         throw new IllegalArgumentException("Cannot convert given object to a fraction.");
/*     */       }
/*     */       
/*     */     } else {
/* 213 */       throw new IllegalArgumentException("Cannot format given object as a fraction");
/*     */     }
/*     */     
/*     */ 
/* 217 */     return ret;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   public NumberFormat getDenominatorFormat()
/*     */   {
/* 225 */     return this.denominatorFormat;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   public NumberFormat getNumeratorFormat()
/*     */   {
/* 233 */     return this.numeratorFormat;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public Fraction parse(String source)
/*     */     throws ParseException
/*     */   {
/* 244 */     ParsePosition parsePosition = new ParsePosition(0);
/* 245 */     Fraction result = parse(source, parsePosition);
/* 246 */     if (parsePosition.getIndex() == 0) {
/* 247 */       throw new ParseException("Unparseable fraction number: \"" + source + "\"", parsePosition.getErrorIndex());
/*     */     }
/*     */     
/* 250 */     return result;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public Fraction parse(String source, ParsePosition pos)
/*     */   {
/* 261 */     int initialIndex = pos.getIndex();
/*     */     
/*     */ 
/* 264 */     parseAndIgnoreWhitespace(source, pos);
/*     */     
/*     */ 
/* 267 */     Number num = getNumeratorFormat().parse(source, pos);
/* 268 */     if (num == null)
/*     */     {
/*     */ 
/*     */ 
/* 272 */       pos.setIndex(initialIndex);
/* 273 */       return null;
/*     */     }
/*     */     
/*     */ 
/* 277 */     int startIndex = pos.getIndex();
/* 278 */     char c = parseNextCharacter(source, pos);
/* 279 */     switch (c)
/*     */     {
/*     */ 
/*     */     case '\000': 
/* 283 */       return new Fraction(num.intValue(), 1);
/*     */     
/*     */ 
/*     */     case '/': 
/*     */       break;
/*     */     
/*     */ 
/*     */     default: 
/* 291 */       pos.setIndex(initialIndex);
/* 292 */       pos.setErrorIndex(startIndex);
/* 293 */       return null;
/*     */     }
/*     */     
/*     */     
/* 297 */     parseAndIgnoreWhitespace(source, pos);
/*     */     
/*     */ 
/* 300 */     Number den = getDenominatorFormat().parse(source, pos);
/* 301 */     if (den == null)
/*     */     {
/*     */ 
/*     */ 
/* 305 */       pos.setIndex(initialIndex);
/* 306 */       return null;
/*     */     }
/*     */     
/* 309 */     return new Fraction(num.intValue(), den.intValue());
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public Object parseObject(String source, ParsePosition pos)
/*     */   {
/* 320 */     return parse(source, pos);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public void setDenominatorFormat(NumberFormat format)
/*     */   {
/* 330 */     if (format == null) {
/* 331 */       throw new IllegalArgumentException("denominator format can not be null.");
/*     */     }
/*     */     
/* 334 */     this.denominatorFormat = format;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public void setNumeratorFormat(NumberFormat format)
/*     */   {
/* 344 */     if (format == null) {
/* 345 */       throw new IllegalArgumentException("numerator format can not be null.");
/*     */     }
/*     */     
/* 348 */     this.numeratorFormat = format;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   protected static void parseAndIgnoreWhitespace(String source, ParsePosition pos)
/*     */   {
/* 360 */     parseNextCharacter(source, pos);
/* 361 */     pos.setIndex(pos.getIndex() - 1);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   protected static char parseNextCharacter(String source, ParsePosition pos)
/*     */   {
/* 371 */     int index = pos.getIndex();
/* 372 */     int n = source.length();
/* 373 */     char ret = '\000';
/*     */     
/* 375 */     if (index < n) {
/*     */       char c;
/*     */       do {
/* 378 */         c = source.charAt(index++);
/* 379 */       } while ((Character.isWhitespace(c)) && (index < n));
/* 380 */       pos.setIndex(index);
/*     */       
/* 382 */       if (index < n) {
/* 383 */         ret = c;
/*     */       }
/*     */     }
/*     */     
/* 387 */     return ret;
/*     */   }
/*     */ }


/* Location:              /home/menick/Scrivania/ProcessMining/CNMining/CNMining.jar!/org/apache/commons/math/fraction/FractionFormat.class
 * Java compiler version: 3 (47.0)
 * JD-Core Version:       0.7.1
 */