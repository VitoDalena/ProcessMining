/*     */ package org.apache.commons.math.fraction;
/*     */ 
/*     */ import java.math.BigInteger;
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
/*     */ public class Fraction
/*     */   extends Number
/*     */   implements Comparable
/*     */ {
/*  31 */   public static final Fraction ONE = new Fraction(1, 1);
/*     */   
/*     */ 
/*  34 */   public static final Fraction ZERO = new Fraction(0, 1);
/*     */   
/*     */ 
/*     */ 
/*     */   private static final long serialVersionUID = -8958519416450949235L;
/*     */   
/*     */ 
/*     */ 
/*     */   private final int denominator;
/*     */   
/*     */ 
/*     */   private final int numerator;
/*     */   
/*     */ 
/*     */ 
/*     */   public Fraction(double value)
/*     */     throws FractionConversionException
/*     */   {
/*  52 */     this(value, 1.0E-5D, 100);
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
/*     */   public Fraction(double value, double epsilon, int maxIterations)
/*     */     throws FractionConversionException
/*     */   {
/*  74 */     this(value, epsilon, Integer.MAX_VALUE, maxIterations);
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
/*     */   public Fraction(double value, int maxDenominator)
/*     */     throws FractionConversionException
/*     */   {
/*  94 */     this(value, 0.0D, maxDenominator, 100);
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
/*     */   private Fraction(double value, double epsilon, int maxDenominator, int maxIterations)
/*     */     throws FractionConversionException
/*     */   {
/* 131 */     long overflow = 2147483647L;
/* 132 */     double r0 = value;
/* 133 */     long a0 = Math.floor(r0);
/* 134 */     if (a0 > overflow) {
/* 135 */       throw new FractionConversionException(value, a0, 1L);
/*     */     }
/*     */     
/*     */ 
/*     */ 
/* 140 */     if (Math.abs(a0 - value) < epsilon) {
/* 141 */       this.numerator = ((int)a0);
/* 142 */       this.denominator = 1;
/* 143 */       return;
/*     */     }
/*     */     
/* 146 */     long p0 = 1L;
/* 147 */     long q0 = 0L;
/* 148 */     long p1 = a0;
/* 149 */     long q1 = 1L;
/*     */     
/* 151 */     long p2 = 0L;
/* 152 */     long q2 = 1L;
/*     */     
/* 154 */     int n = 0;
/* 155 */     boolean stop = false;
/*     */     do {
/* 157 */       n++;
/* 158 */       double r1 = 1.0D / (r0 - a0);
/* 159 */       long a1 = Math.floor(r1);
/* 160 */       p2 = a1 * p1 + p0;
/* 161 */       q2 = a1 * q1 + q0;
/* 162 */       if ((p2 > overflow) || (q2 > overflow)) {
/* 163 */         throw new FractionConversionException(value, p2, q2);
/*     */       }
/*     */       
/* 166 */       double convergent = p2 / q2;
/* 167 */       if ((n < maxIterations) && (Math.abs(convergent - value) > epsilon) && (q2 < maxDenominator)) {
/* 168 */         p0 = p1;
/* 169 */         p1 = p2;
/* 170 */         q0 = q1;
/* 171 */         q1 = q2;
/* 172 */         a0 = a1;
/* 173 */         r0 = r1;
/*     */       } else {
/* 175 */         stop = true;
/*     */       }
/* 177 */     } while (!stop);
/*     */     
/* 179 */     if (n >= maxIterations) {
/* 180 */       throw new FractionConversionException(value, maxIterations);
/*     */     }
/*     */     
/* 183 */     if (q2 < maxDenominator) {
/* 184 */       this.numerator = ((int)p2);
/* 185 */       this.denominator = ((int)q2);
/*     */     } else {
/* 187 */       this.numerator = ((int)p1);
/* 188 */       this.denominator = ((int)q1);
/*     */     }
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
/*     */   public Fraction(int num, int den)
/*     */   {
/* 202 */     if (den == 0) {
/* 203 */       throw new ArithmeticException("The denominator must not be zero");
/*     */     }
/* 205 */     if (den < 0) {
/* 206 */       if ((num == Integer.MIN_VALUE) || (den == Integer.MIN_VALUE))
/*     */       {
/* 208 */         throw new ArithmeticException("overflow: can't negate");
/*     */       }
/* 210 */       num = -num;
/* 211 */       den = -den;
/*     */     }
/*     */     
/* 214 */     int d = MathUtils.gcd(num, den);
/* 215 */     if (d > 1) {
/* 216 */       num /= d;
/* 217 */       den /= d;
/*     */     }
/*     */     
/*     */ 
/* 221 */     if (den < 0) {
/* 222 */       num *= -1;
/* 223 */       den *= -1;
/*     */     }
/* 225 */     this.numerator = num;
/* 226 */     this.denominator = den;
/*     */   }
/*     */   
/*     */ 
/*     */   public Fraction abs()
/*     */   {
/*     */     Fraction ret;
/*     */     
/*     */     Fraction ret;
/* 235 */     if (this.numerator >= 0) {
/* 236 */       ret = this;
/*     */     } else {
/* 238 */       ret = negate();
/*     */     }
/* 240 */     return ret;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public int compareTo(Object object)
/*     */   {
/* 250 */     int ret = 0;
/*     */     
/* 252 */     if (this != object) {
/* 253 */       Fraction other = (Fraction)object;
/* 254 */       double first = doubleValue();
/* 255 */       double second = other.doubleValue();
/*     */       
/* 257 */       if (first < second) {
/* 258 */         ret = -1;
/* 259 */       } else if (first > second) {
/* 260 */         ret = 1;
/*     */       }
/*     */     }
/*     */     
/* 264 */     return ret;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public double doubleValue()
/*     */   {
/* 273 */     return this.numerator / this.denominator;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   public boolean equals(Object other)
/*     */   {
/*     */     boolean ret;
/*     */     
/*     */ 
/*     */ 
/*     */     boolean ret;
/*     */     
/*     */ 
/* 288 */     if (this == other) {
/* 289 */       ret = true; } else { boolean ret;
/* 290 */       if (other == null) {
/* 291 */         ret = false;
/*     */       }
/*     */       else {
/*     */         try
/*     */         {
/* 296 */           Fraction rhs = (Fraction)other;
/* 297 */           ret = (this.numerator == rhs.numerator) && (this.denominator == rhs.denominator);
/*     */         }
/*     */         catch (ClassCastException ex)
/*     */         {
/* 301 */           ret = false;
/*     */         }
/*     */       }
/*     */     }
/* 305 */     return ret;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public float floatValue()
/*     */   {
/* 314 */     return (float)doubleValue();
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   public int getDenominator()
/*     */   {
/* 322 */     return this.denominator;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   public int getNumerator()
/*     */   {
/* 330 */     return this.numerator;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   public int hashCode()
/*     */   {
/* 338 */     return 37 * (629 + getNumerator()) + getDenominator();
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public int intValue()
/*     */   {
/* 347 */     return (int)doubleValue();
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public long longValue()
/*     */   {
/* 356 */     return doubleValue();
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   public Fraction negate()
/*     */   {
/* 364 */     if (this.numerator == Integer.MIN_VALUE) {
/* 365 */       throw new ArithmeticException("overflow: too large to negate");
/*     */     }
/* 367 */     return new Fraction(-this.numerator, this.denominator);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   public Fraction reciprocal()
/*     */   {
/* 375 */     return new Fraction(this.denominator, this.numerator);
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
/*     */   public Fraction add(Fraction fraction)
/*     */   {
/* 389 */     return addSub(fraction, true);
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
/*     */   public Fraction subtract(Fraction fraction)
/*     */   {
/* 403 */     return addSub(fraction, false);
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
/*     */   private Fraction addSub(Fraction fraction, boolean isAdd)
/*     */   {
/* 417 */     if (fraction == null) {
/* 418 */       throw new IllegalArgumentException("The fraction must not be null");
/*     */     }
/*     */     
/* 421 */     if (this.numerator == 0) {
/* 422 */       return isAdd ? fraction : fraction.negate();
/*     */     }
/* 424 */     if (fraction.numerator == 0) {
/* 425 */       return this;
/*     */     }
/*     */     
/*     */ 
/* 429 */     int d1 = MathUtils.gcd(this.denominator, fraction.denominator);
/* 430 */     if (d1 == 1)
/*     */     {
/* 432 */       int uvp = MathUtils.mulAndCheck(this.numerator, fraction.denominator);
/* 433 */       int upv = MathUtils.mulAndCheck(fraction.numerator, this.denominator);
/* 434 */       return new Fraction(isAdd ? MathUtils.addAndCheck(uvp, upv) : MathUtils.subAndCheck(uvp, upv), MathUtils.mulAndCheck(this.denominator, fraction.denominator));
/*     */     }
/*     */     
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/* 442 */     BigInteger uvp = BigInteger.valueOf(this.numerator).multiply(BigInteger.valueOf(fraction.denominator / d1));
/*     */     
/* 444 */     BigInteger upv = BigInteger.valueOf(fraction.numerator).multiply(BigInteger.valueOf(this.denominator / d1));
/*     */     
/* 446 */     BigInteger t = isAdd ? uvp.add(upv) : uvp.subtract(upv);
/*     */     
/*     */ 
/* 449 */     int tmodd1 = t.mod(BigInteger.valueOf(d1)).intValue();
/* 450 */     int d2 = tmodd1 == 0 ? d1 : MathUtils.gcd(tmodd1, d1);
/*     */     
/*     */ 
/* 453 */     BigInteger w = t.divide(BigInteger.valueOf(d2));
/* 454 */     if (w.bitLength() > 31) {
/* 455 */       throw new ArithmeticException("overflow: numerator too large after multiply");
/*     */     }
/*     */     
/* 458 */     return new Fraction(w.intValue(), MathUtils.mulAndCheck(this.denominator / d1, fraction.denominator / d2));
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
/*     */   public Fraction multiply(Fraction fraction)
/*     */   {
/* 474 */     if (fraction == null) {
/* 475 */       throw new IllegalArgumentException("The fraction must not be null");
/*     */     }
/* 477 */     if ((this.numerator == 0) || (fraction.numerator == 0)) {
/* 478 */       return ZERO;
/*     */     }
/*     */     
/*     */ 
/* 482 */     int d1 = MathUtils.gcd(this.numerator, fraction.denominator);
/* 483 */     int d2 = MathUtils.gcd(fraction.numerator, this.denominator);
/* 484 */     return getReducedFraction(MathUtils.mulAndCheck(this.numerator / d1, fraction.numerator / d2), MathUtils.mulAndCheck(this.denominator / d2, fraction.denominator / d1));
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
/*     */   public Fraction divide(Fraction fraction)
/*     */   {
/* 500 */     if (fraction == null) {
/* 501 */       throw new IllegalArgumentException("The fraction must not be null");
/*     */     }
/* 503 */     if (fraction.numerator == 0) {
/* 504 */       throw new ArithmeticException("The fraction to divide by must not be zero");
/*     */     }
/* 506 */     return multiply(fraction.reciprocal());
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
/*     */   public static Fraction getReducedFraction(int numerator, int denominator)
/*     */   {
/* 521 */     if (denominator == 0) {
/* 522 */       throw new ArithmeticException("The denominator must not be zero");
/*     */     }
/* 524 */     if (numerator == 0) {
/* 525 */       return ZERO;
/*     */     }
/*     */     
/* 528 */     if ((denominator == Integer.MIN_VALUE) && ((numerator & 0x1) == 0)) {
/* 529 */       numerator /= 2;denominator /= 2;
/*     */     }
/* 531 */     if (denominator < 0) {
/* 532 */       if ((numerator == Integer.MIN_VALUE) || (denominator == Integer.MIN_VALUE))
/*     */       {
/* 534 */         throw new ArithmeticException("overflow: can't negate");
/*     */       }
/* 536 */       numerator = -numerator;
/* 537 */       denominator = -denominator;
/*     */     }
/*     */     
/* 540 */     int gcd = MathUtils.gcd(numerator, denominator);
/* 541 */     numerator /= gcd;
/* 542 */     denominator /= gcd;
/* 543 */     return new Fraction(numerator, denominator);
/*     */   }
/*     */ }


/* Location:              /home/menick/Scrivania/ProcessMining/CNMining/CNMining.jar!/org/apache/commons/math/fraction/Fraction.class
 * Java compiler version: 3 (47.0)
 * JD-Core Version:       0.7.1
 */