/*     */ package org.apache.commons.math.complex;
/*     */ 
/*     */ import java.io.Serializable;
/*     */ import java.text.FieldPosition;
/*     */ import java.text.Format;
/*     */ import java.text.NumberFormat;
/*     */ import java.text.ParseException;
/*     */ import java.text.ParsePosition;
/*     */ import java.util.Locale;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class ComplexFormat
/*     */   extends Format
/*     */   implements Serializable
/*     */ {
/*     */   private static final long serialVersionUID = -6337346779577272306L;
/*     */   private static final String DEFAULT_IMAGINARY_CHARACTER = "i";
/*     */   private String imaginaryCharacter;
/*     */   private NumberFormat imaginaryFormat;
/*     */   private NumberFormat realFormat;
/*     */   
/*     */   public ComplexFormat()
/*     */   {
/*  58 */     this("i", getDefaultNumberFormat());
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public ComplexFormat(NumberFormat format)
/*     */   {
/*  67 */     this("i", format);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public ComplexFormat(NumberFormat realFormat, NumberFormat imaginaryFormat)
/*     */   {
/*  78 */     this("i", realFormat, imaginaryFormat);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public ComplexFormat(String imaginaryCharacter)
/*     */   {
/*  87 */     this(imaginaryCharacter, getDefaultNumberFormat());
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public ComplexFormat(String imaginaryCharacter, NumberFormat format)
/*     */   {
/*  97 */     this(imaginaryCharacter, format, (NumberFormat)format.clone());
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
/*     */   public ComplexFormat(String imaginaryCharacter, NumberFormat realFormat, NumberFormat imaginaryFormat)
/*     */   {
/* 111 */     setImaginaryCharacter(imaginaryCharacter);
/* 112 */     setImaginaryFormat(imaginaryFormat);
/* 113 */     setRealFormat(realFormat);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public static String formatComplex(Complex c)
/*     */   {
/* 124 */     return getInstance().format(c);
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
/*     */   public StringBuffer format(Complex complex, StringBuffer toAppendTo, FieldPosition pos)
/*     */   {
/* 139 */     pos.setBeginIndex(0);
/* 140 */     pos.setEndIndex(0);
/*     */     
/*     */ 
/* 143 */     double re = complex.getReal();
/* 144 */     formatDouble(re, getRealFormat(), toAppendTo, pos);
/*     */     
/*     */ 
/* 147 */     double im = complex.getImaginary();
/* 148 */     if (im < 0.0D) {
/* 149 */       toAppendTo.append(" - ");
/* 150 */       formatDouble(-im, getImaginaryFormat(), toAppendTo, pos);
/* 151 */       toAppendTo.append(getImaginaryCharacter());
/* 152 */     } else if ((im > 0.0D) || (Double.isNaN(im))) {
/* 153 */       toAppendTo.append(" + ");
/* 154 */       formatDouble(im, getImaginaryFormat(), toAppendTo, pos);
/* 155 */       toAppendTo.append(getImaginaryCharacter());
/*     */     }
/*     */     
/* 158 */     return toAppendTo;
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
/* 177 */     StringBuffer ret = null;
/*     */     
/* 179 */     if ((obj instanceof Complex)) {
/* 180 */       ret = format((Complex)obj, toAppendTo, pos);
/* 181 */     } else if ((obj instanceof Number)) {
/* 182 */       ret = format(new Complex(((Number)obj).doubleValue(), 0.0D), toAppendTo, pos);
/*     */     }
/*     */     else {
/* 185 */       throw new IllegalArgumentException("Cannot format given Object as a Date");
/*     */     }
/*     */     
/*     */ 
/* 189 */     return ret;
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
/*     */   private StringBuffer formatDouble(double value, NumberFormat format, StringBuffer toAppendTo, FieldPosition pos)
/*     */   {
/* 211 */     if ((Double.isNaN(value)) || (Double.isInfinite(value))) {
/* 212 */       toAppendTo.append('(');
/* 213 */       toAppendTo.append(value);
/* 214 */       toAppendTo.append(')');
/*     */     } else {
/* 216 */       format.format(value, toAppendTo, pos);
/*     */     }
/* 218 */     return toAppendTo;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public static Locale[] getAvailableLocales()
/*     */   {
/* 227 */     return NumberFormat.getAvailableLocales();
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   private static NumberFormat getDefaultNumberFormat()
/*     */   {
/* 237 */     return getDefaultNumberFormat(Locale.getDefault());
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
/* 248 */     NumberFormat nf = NumberFormat.getInstance(locale);
/* 249 */     nf.setMaximumFractionDigits(2);
/* 250 */     return nf;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   public String getImaginaryCharacter()
/*     */   {
/* 258 */     return this.imaginaryCharacter;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   public NumberFormat getImaginaryFormat()
/*     */   {
/* 266 */     return this.imaginaryFormat;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   public static ComplexFormat getInstance()
/*     */   {
/* 274 */     return getInstance(Locale.getDefault());
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public static ComplexFormat getInstance(Locale locale)
/*     */   {
/* 283 */     NumberFormat f = getDefaultNumberFormat(locale);
/* 284 */     return new ComplexFormat(f);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   public NumberFormat getRealFormat()
/*     */   {
/* 292 */     return this.realFormat;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public Complex parse(String source)
/*     */     throws ParseException
/*     */   {
/* 304 */     ParsePosition parsePosition = new ParsePosition(0);
/* 305 */     Complex result = parse(source, parsePosition);
/* 306 */     if (parsePosition.getIndex() == 0) {
/* 307 */       throw new ParseException("Unparseable complex number: \"" + source + "\"", parsePosition.getErrorIndex());
/*     */     }
/*     */     
/* 310 */     return result;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public Complex parse(String source, ParsePosition pos)
/*     */   {
/* 321 */     int initialIndex = pos.getIndex();
/*     */     
/*     */ 
/* 324 */     parseAndIgnoreWhitespace(source, pos);
/*     */     
/*     */ 
/* 327 */     Number re = parseNumber(source, getRealFormat(), pos);
/* 328 */     if (re == null)
/*     */     {
/*     */ 
/*     */ 
/* 332 */       pos.setIndex(initialIndex);
/* 333 */       return null;
/*     */     }
/*     */     
/*     */ 
/* 337 */     int startIndex = pos.getIndex();
/* 338 */     char c = parseNextCharacter(source, pos);
/* 339 */     int sign = 0;
/* 340 */     switch (c)
/*     */     {
/*     */ 
/*     */     case '\000': 
/* 344 */       return new Complex(re.doubleValue(), 0.0D);
/*     */     case '-': 
/* 346 */       sign = -1;
/* 347 */       break;
/*     */     case '+': 
/* 349 */       sign = 1;
/* 350 */       break;
/*     */     
/*     */ 
/*     */ 
/*     */     default: 
/* 355 */       pos.setIndex(initialIndex);
/* 356 */       pos.setErrorIndex(startIndex);
/* 357 */       return null;
/*     */     }
/*     */     
/*     */     
/* 361 */     parseAndIgnoreWhitespace(source, pos);
/*     */     
/*     */ 
/* 364 */     Number im = parseNumber(source, getRealFormat(), pos);
/* 365 */     if (im == null)
/*     */     {
/*     */ 
/*     */ 
/* 369 */       pos.setIndex(initialIndex);
/* 370 */       return null;
/*     */     }
/*     */     
/*     */ 
/* 374 */     int n = getImaginaryCharacter().length();
/* 375 */     startIndex = pos.getIndex();
/* 376 */     int endIndex = startIndex + n;
/* 377 */     if (source.substring(startIndex, endIndex).compareTo(getImaginaryCharacter()) != 0)
/*     */     {
/*     */ 
/*     */ 
/* 381 */       pos.setIndex(initialIndex);
/* 382 */       pos.setErrorIndex(startIndex);
/* 383 */       return null;
/*     */     }
/* 385 */     pos.setIndex(endIndex);
/*     */     
/* 387 */     return new Complex(re.doubleValue(), im.doubleValue() * sign);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   private void parseAndIgnoreWhitespace(String source, ParsePosition pos)
/*     */   {
/* 398 */     parseNextCharacter(source, pos);
/* 399 */     pos.setIndex(pos.getIndex() - 1);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   private char parseNextCharacter(String source, ParsePosition pos)
/*     */   {
/* 410 */     int index = pos.getIndex();
/* 411 */     int n = source.length();
/* 412 */     char ret = '\000';
/*     */     
/* 414 */     if (index < n) {
/*     */       char c;
/*     */       do {
/* 417 */         c = source.charAt(index++);
/* 418 */       } while ((Character.isWhitespace(c)) && (index < n));
/* 419 */       pos.setIndex(index);
/*     */       
/* 421 */       if (index < n) {
/* 422 */         ret = c;
/*     */       }
/*     */     }
/*     */     
/* 426 */     return ret;
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
/*     */   private Number parseNumber(String source, double value, ParsePosition pos)
/*     */   {
/* 439 */     Number ret = null;
/*     */     
/* 441 */     StringBuffer sb = new StringBuffer();
/* 442 */     sb.append('(');
/* 443 */     sb.append(value);
/* 444 */     sb.append(')');
/*     */     
/* 446 */     int n = sb.length();
/* 447 */     int startIndex = pos.getIndex();
/* 448 */     int endIndex = startIndex + n;
/* 449 */     if ((endIndex < source.length()) && 
/* 450 */       (source.substring(startIndex, endIndex).compareTo(sb.toString()) == 0)) {
/* 451 */       ret = new Double(value);
/* 452 */       pos.setIndex(endIndex);
/*     */     }
/*     */     
/*     */ 
/* 456 */     return ret;
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
/*     */   private Number parseNumber(String source, NumberFormat format, ParsePosition pos)
/*     */   {
/* 470 */     int startIndex = pos.getIndex();
/* 471 */     Number number = format.parse(source, pos);
/* 472 */     int endIndex = pos.getIndex();
/*     */     
/*     */ 
/* 475 */     if (startIndex == endIndex)
/*     */     {
/* 477 */       double[] special = { NaN.0D, Double.POSITIVE_INFINITY, Double.NEGATIVE_INFINITY };
/* 478 */       for (int i = 0; i < special.length; i++) {
/* 479 */         number = parseNumber(source, special[i], pos);
/* 480 */         if (number != null) {
/*     */           break;
/*     */         }
/*     */       }
/*     */     }
/*     */     
/* 486 */     return number;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public Object parseObject(String source, ParsePosition pos)
/*     */   {
/* 498 */     return parse(source, pos);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public void setImaginaryCharacter(String imaginaryCharacter)
/*     */   {
/* 507 */     if ((imaginaryCharacter == null) || (imaginaryCharacter.length() == 0)) {
/* 508 */       throw new IllegalArgumentException("imaginaryCharacter must be a non-empty string.");
/*     */     }
/*     */     
/* 511 */     this.imaginaryCharacter = imaginaryCharacter;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public void setImaginaryFormat(NumberFormat imaginaryFormat)
/*     */   {
/* 521 */     if (imaginaryFormat == null) {
/* 522 */       throw new IllegalArgumentException("imaginaryFormat can not be null.");
/*     */     }
/*     */     
/* 525 */     this.imaginaryFormat = imaginaryFormat;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public void setRealFormat(NumberFormat realFormat)
/*     */   {
/* 535 */     if (realFormat == null) {
/* 536 */       throw new IllegalArgumentException("realFormat can not be null.");
/*     */     }
/*     */     
/* 539 */     this.realFormat = realFormat;
/*     */   }
/*     */ }


/* Location:              /home/menick/Scrivania/ProcessMining/CNMining/CNMining.jar!/org/apache/commons/math/complex/ComplexFormat.class
 * Java compiler version: 3 (47.0)
 * JD-Core Version:       0.7.1
 */