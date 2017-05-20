/*     */ package org.apache.commons.math.fraction;
/*     */ 
/*     */ import java.text.FieldPosition;
/*     */ import java.text.NumberFormat;
/*     */ import java.text.ParsePosition;
/*     */ import org.apache.commons.math.util.MathUtils;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class ProperFractionFormat
/*     */   extends FractionFormat
/*     */ {
/*     */   private static final long serialVersionUID = -6337346779577272307L;
/*     */   private NumberFormat wholeFormat;
/*     */   
/*     */   public ProperFractionFormat()
/*     */   {
/*  49 */     this(getDefaultNumberFormat());
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public ProperFractionFormat(NumberFormat format)
/*     */   {
/*  59 */     this(format, (NumberFormat)format.clone(), (NumberFormat)format.clone());
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
/*     */   public ProperFractionFormat(NumberFormat wholeFormat, NumberFormat numeratorFormat, NumberFormat denominatorFormat)
/*     */   {
/*  73 */     super(numeratorFormat, denominatorFormat);
/*  74 */     setWholeFormat(wholeFormat);
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
/*  90 */     pos.setBeginIndex(0);
/*  91 */     pos.setEndIndex(0);
/*     */     
/*  93 */     int num = fraction.getNumerator();
/*  94 */     int den = fraction.getDenominator();
/*  95 */     int whole = num / den;
/*  96 */     num %= den;
/*     */     
/*  98 */     if (whole != 0) {
/*  99 */       getWholeFormat().format(whole, toAppendTo, pos);
/* 100 */       toAppendTo.append(' ');
/* 101 */       num = Math.abs(num);
/*     */     }
/* 103 */     getNumeratorFormat().format(num, toAppendTo, pos);
/* 104 */     toAppendTo.append(" / ");
/* 105 */     getDenominatorFormat().format(den, toAppendTo, pos);
/*     */     
/*     */ 
/* 108 */     return toAppendTo;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   public NumberFormat getWholeFormat()
/*     */   {
/* 116 */     return this.wholeFormat;
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
/*     */   public Fraction parse(String source, ParsePosition pos)
/*     */   {
/* 133 */     Fraction ret = super.parse(source, pos);
/* 134 */     if (ret != null) {
/* 135 */       return ret;
/*     */     }
/*     */     
/* 138 */     int initialIndex = pos.getIndex();
/*     */     
/*     */ 
/* 141 */     parseAndIgnoreWhitespace(source, pos);
/*     */     
/*     */ 
/* 144 */     Number whole = getWholeFormat().parse(source, pos);
/* 145 */     if (whole == null)
/*     */     {
/*     */ 
/*     */ 
/* 149 */       pos.setIndex(initialIndex);
/* 150 */       return null;
/*     */     }
/*     */     
/*     */ 
/* 154 */     parseAndIgnoreWhitespace(source, pos);
/*     */     
/*     */ 
/* 157 */     Number num = getNumeratorFormat().parse(source, pos);
/* 158 */     if (num == null)
/*     */     {
/*     */ 
/*     */ 
/* 162 */       pos.setIndex(initialIndex);
/* 163 */       return null;
/*     */     }
/*     */     
/* 166 */     if (num.intValue() < 0)
/*     */     {
/* 168 */       pos.setIndex(initialIndex);
/* 169 */       return null;
/*     */     }
/*     */     
/*     */ 
/* 173 */     int startIndex = pos.getIndex();
/* 174 */     char c = parseNextCharacter(source, pos);
/* 175 */     switch (c)
/*     */     {
/*     */ 
/*     */     case '\000': 
/* 179 */       return new Fraction(num.intValue(), 1);
/*     */     
/*     */ 
/*     */     case '/': 
/*     */       break;
/*     */     
/*     */ 
/*     */     default: 
/* 187 */       pos.setIndex(initialIndex);
/* 188 */       pos.setErrorIndex(startIndex);
/* 189 */       return null;
/*     */     }
/*     */     
/*     */     
/* 193 */     parseAndIgnoreWhitespace(source, pos);
/*     */     
/*     */ 
/* 196 */     Number den = getDenominatorFormat().parse(source, pos);
/* 197 */     if (den == null)
/*     */     {
/*     */ 
/*     */ 
/* 201 */       pos.setIndex(initialIndex);
/* 202 */       return null;
/*     */     }
/*     */     
/* 205 */     if (den.intValue() < 0)
/*     */     {
/* 207 */       pos.setIndex(initialIndex);
/* 208 */       return null;
/*     */     }
/*     */     
/* 211 */     int w = whole.intValue();
/* 212 */     int n = num.intValue();
/* 213 */     int d = den.intValue();
/* 214 */     return new Fraction((Math.abs(w) * d + n) * MathUtils.sign(w), d);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public void setWholeFormat(NumberFormat format)
/*     */   {
/* 224 */     if (format == null) {
/* 225 */       throw new IllegalArgumentException("whole format can not be null.");
/*     */     }
/*     */     
/* 228 */     this.wholeFormat = format;
/*     */   }
/*     */ }


/* Location:              /home/menick/Scrivania/ProcessMining/CNMining/CNMining.jar!/org/apache/commons/math/fraction/ProperFractionFormat.class
 * Java compiler version: 3 (47.0)
 * JD-Core Version:       0.7.1
 */