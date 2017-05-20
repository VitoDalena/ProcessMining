/*     */ package org.apache.commons.math.complex;
/*     */ 
/*     */ import java.io.Serializable;
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
/*     */ 
/*     */ 
/*     */ public class Complex
/*     */   implements Serializable
/*     */ {
/*     */   private static final long serialVersionUID = -6530173849413811929L;
/*  45 */   public static final Complex I = new Complex(0.0D, 1.0D);
/*     */   
/*     */ 
/*  48 */   public static final Complex NaN = new Complex(NaN.0D, NaN.0D);
/*     */   
/*     */ 
/*  51 */   public static final Complex INF = new Complex(Double.POSITIVE_INFINITY, Double.POSITIVE_INFINITY);
/*     */   
/*     */ 
/*  54 */   public static final Complex ONE = new Complex(1.0D, 0.0D);
/*     */   
/*     */ 
/*  57 */   public static final Complex ZERO = new Complex(0.0D, 0.0D);
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   /**
/*     */    * @deprecated
/*     */    */
/*     */   protected double imaginary;
/*     */   
/*     */ 
/*     */ 
/*     */   /**
/*     */    * @deprecated
/*     */    */
/*     */   protected double real;
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   public Complex(double real, double imaginary)
/*     */   {
/*  79 */     this.real = real;
/*  80 */     this.imaginary = imaginary;
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
/*     */   public double abs()
/*     */   {
/*  94 */     if (isNaN()) {
/*  95 */       return NaN.0D;
/*     */     }
/*     */     
/*  98 */     if (isInfinite()) {
/*  99 */       return Double.POSITIVE_INFINITY;
/*     */     }
/*     */     
/* 102 */     if (Math.abs(this.real) < Math.abs(this.imaginary)) {
/* 103 */       if (this.imaginary == 0.0D) {
/* 104 */         return Math.abs(this.real);
/*     */       }
/* 106 */       double q = this.real / this.imaginary;
/* 107 */       return Math.abs(this.imaginary) * Math.sqrt(1.0D + q * q);
/*     */     }
/* 109 */     if (this.real == 0.0D) {
/* 110 */       return Math.abs(this.imaginary);
/*     */     }
/* 112 */     double q = this.imaginary / this.real;
/* 113 */     return Math.abs(this.real) * Math.sqrt(1.0D + q * q);
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
/*     */   public Complex add(Complex rhs)
/*     */   {
/* 135 */     return createComplex(this.real + rhs.getReal(), this.imaginary + rhs.getImaginary());
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
/*     */   public Complex conjugate()
/*     */   {
/* 154 */     if (isNaN()) {
/* 155 */       return NaN;
/*     */     }
/* 157 */     return createComplex(this.real, -this.imaginary);
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
/*     */   public Complex divide(Complex rhs)
/*     */   {
/* 196 */     if ((isNaN()) || (rhs.isNaN())) {
/* 197 */       return NaN;
/*     */     }
/*     */     
/* 200 */     double c = rhs.getReal();
/* 201 */     double d = rhs.getImaginary();
/* 202 */     if ((c == 0.0D) && (d == 0.0D)) {
/* 203 */       return NaN;
/*     */     }
/*     */     
/* 206 */     if ((rhs.isInfinite()) && (!isInfinite())) {
/* 207 */       return ZERO;
/*     */     }
/*     */     
/* 210 */     if (Math.abs(c) < Math.abs(d)) {
/* 211 */       if (d == 0.0D) {
/* 212 */         return createComplex(this.real / c, this.imaginary / c);
/*     */       }
/* 214 */       double q = c / d;
/* 215 */       double denominator = c * q + d;
/* 216 */       return createComplex((this.real * q + this.imaginary) / denominator, (this.imaginary * q - this.real) / denominator);
/*     */     }
/*     */     
/* 219 */     if (c == 0.0D) {
/* 220 */       return createComplex(this.imaginary / d, -this.real / c);
/*     */     }
/* 222 */     double q = d / c;
/* 223 */     double denominator = d * q + c;
/* 224 */     return createComplex((this.imaginary * q + this.real) / denominator, (this.imaginary - this.real * q) / denominator);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
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
/*     */ 
/*     */ 
/*     */ 
/*     */     boolean ret;
/*     */     
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/* 250 */     if (this == other) {
/* 251 */       ret = true; } else { boolean ret;
/* 252 */       if (other == null) {
/* 253 */         ret = false;
/*     */       } else {
/*     */         try {
/* 256 */           Complex rhs = (Complex)other;
/* 257 */           boolean ret; if (rhs.isNaN()) {
/* 258 */             ret = isNaN();
/*     */           } else {
/* 260 */             ret = (Double.doubleToRawLongBits(this.real) == Double.doubleToRawLongBits(rhs.getReal())) && (Double.doubleToRawLongBits(this.imaginary) == Double.doubleToRawLongBits(rhs.getImaginary()));
/*     */           }
/*     */           
/*     */ 
/*     */         }
/*     */         catch (ClassCastException ex)
/*     */         {
/* 267 */           ret = false;
/*     */         }
/*     */       }
/*     */     }
/* 271 */     return ret;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public int hashCode()
/*     */   {
/* 282 */     if (isNaN()) {
/* 283 */       return 7;
/*     */     }
/* 285 */     return 37 * (17 * MathUtils.hash(this.imaginary) + MathUtils.hash(this.real));
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public double getImaginary()
/*     */   {
/* 295 */     return this.imaginary;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public double getReal()
/*     */   {
/* 304 */     return this.real;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public boolean isNaN()
/*     */   {
/* 315 */     return (Double.isNaN(this.real)) || (Double.isNaN(this.imaginary));
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
/*     */   public boolean isInfinite()
/*     */   {
/* 328 */     return (!isNaN()) && ((Double.isInfinite(this.real)) || (Double.isInfinite(this.imaginary)));
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
/*     */   public Complex multiply(Complex rhs)
/*     */   {
/* 360 */     if ((isNaN()) || (rhs.isNaN())) {
/* 361 */       return NaN;
/*     */     }
/* 363 */     if ((Double.isInfinite(this.real)) || (Double.isInfinite(this.imaginary)) || (Double.isInfinite(rhs.real)) || (Double.isInfinite(rhs.imaginary)))
/*     */     {
/*     */ 
/* 366 */       return INF;
/*     */     }
/* 368 */     return createComplex(this.real * rhs.real - this.imaginary * rhs.imaginary, this.real * rhs.imaginary + this.imaginary * rhs.real);
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
/*     */   public Complex negate()
/*     */   {
/* 381 */     if (isNaN()) {
/* 382 */       return NaN;
/*     */     }
/*     */     
/* 385 */     return createComplex(-this.real, -this.imaginary);
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
/*     */   public Complex subtract(Complex rhs)
/*     */   {
/* 407 */     if ((isNaN()) || (rhs.isNaN())) {
/* 408 */       return NaN;
/*     */     }
/*     */     
/* 411 */     return createComplex(this.real - rhs.getReal(), this.imaginary - rhs.getImaginary());
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
/*     */   public Complex acos()
/*     */   {
/* 430 */     if (isNaN()) {
/* 431 */       return NaN;
/*     */     }
/*     */     
/* 434 */     return add(sqrt1z().multiply(I)).log().multiply(I.negate());
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
/*     */   public Complex asin()
/*     */   {
/* 453 */     if (isNaN()) {
/* 454 */       return NaN;
/*     */     }
/*     */     
/* 457 */     return sqrt1z().add(multiply(I)).log().multiply(I.negate());
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
/*     */   public Complex atan()
/*     */   {
/* 476 */     if (isNaN()) {
/* 477 */       return NaN;
/*     */     }
/*     */     
/* 480 */     return add(I).divide(I.subtract(this)).log().multiply(I.divide(createComplex(2.0D, 0.0D)));
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
/*     */   public Complex cos()
/*     */   {
/* 511 */     if (isNaN()) {
/* 512 */       return NaN;
/*     */     }
/*     */     
/* 515 */     return createComplex(Math.cos(this.real) * MathUtils.cosh(this.imaginary), -Math.sin(this.real) * MathUtils.sinh(this.imaginary));
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
/*     */   public Complex cosh()
/*     */   {
/* 545 */     if (isNaN()) {
/* 546 */       return NaN;
/*     */     }
/*     */     
/* 549 */     return createComplex(MathUtils.cosh(this.real) * Math.cos(this.imaginary), MathUtils.sinh(this.real) * Math.sin(this.imaginary));
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
/*     */   public Complex exp()
/*     */   {
/* 580 */     if (isNaN()) {
/* 581 */       return NaN;
/*     */     }
/*     */     
/* 584 */     double expReal = Math.exp(this.real);
/* 585 */     return createComplex(expReal * Math.cos(this.imaginary), expReal * Math.sin(this.imaginary));
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
/*     */   public Complex log()
/*     */   {
/* 618 */     if (isNaN()) {
/* 619 */       return NaN;
/*     */     }
/*     */     
/* 622 */     return createComplex(Math.log(abs()), Math.atan2(this.imaginary, this.real));
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
/*     */   public Complex pow(Complex x)
/*     */   {
/* 644 */     if (x == null) {
/* 645 */       throw new NullPointerException();
/*     */     }
/* 647 */     return log().multiply(x).exp();
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
/*     */   public Complex sin()
/*     */   {
/* 677 */     if (isNaN()) {
/* 678 */       return NaN;
/*     */     }
/*     */     
/* 681 */     return createComplex(Math.sin(this.real) * MathUtils.cosh(this.imaginary), Math.cos(this.real) * MathUtils.sinh(this.imaginary));
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
/*     */   public Complex sinh()
/*     */   {
/* 711 */     if (isNaN()) {
/* 712 */       return NaN;
/*     */     }
/*     */     
/* 715 */     return createComplex(MathUtils.sinh(this.real) * Math.cos(this.imaginary), MathUtils.cosh(this.real) * Math.sin(this.imaginary));
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
/*     */   public Complex sqrt()
/*     */   {
/* 753 */     if (isNaN()) {
/* 754 */       return NaN;
/*     */     }
/*     */     
/* 757 */     if ((this.real == 0.0D) && (this.imaginary == 0.0D)) {
/* 758 */       return createComplex(0.0D, 0.0D);
/*     */     }
/*     */     
/* 761 */     double t = Math.sqrt((Math.abs(this.real) + abs()) / 2.0D);
/* 762 */     if (this.real >= 0.0D) {
/* 763 */       return createComplex(t, this.imaginary / (2.0D * t));
/*     */     }
/* 765 */     return createComplex(Math.abs(this.imaginary) / (2.0D * t), MathUtils.indicator(this.imaginary) * t);
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
/*     */   public Complex sqrt1z()
/*     */   {
/* 789 */     return createComplex(1.0D, 0.0D).subtract(multiply(this)).sqrt();
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
/*     */   public Complex tan()
/*     */   {
/* 819 */     if (isNaN()) {
/* 820 */       return NaN;
/*     */     }
/*     */     
/* 823 */     double real2 = 2.0D * this.real;
/* 824 */     double imaginary2 = 2.0D * this.imaginary;
/* 825 */     double d = Math.cos(real2) + MathUtils.cosh(imaginary2);
/*     */     
/* 827 */     return createComplex(Math.sin(real2) / d, MathUtils.sinh(imaginary2) / d);
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
/*     */   public Complex tanh()
/*     */   {
/* 857 */     if (isNaN()) {
/* 858 */       return NaN;
/*     */     }
/*     */     
/* 861 */     double real2 = 2.0D * this.real;
/* 862 */     double imaginary2 = 2.0D * this.imaginary;
/* 863 */     double d = MathUtils.cosh(real2) + Math.cos(imaginary2);
/*     */     
/* 865 */     return createComplex(MathUtils.sinh(real2) / d, Math.sin(imaginary2) / d);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   protected Complex createComplex(double real, double imaginary)
/*     */   {
/* 877 */     return new Complex(real, imaginary);
/*     */   }
/*     */ }


/* Location:              /home/menick/Scrivania/ProcessMining/CNMining/CNMining.jar!/org/apache/commons/math/complex/Complex.class
 * Java compiler version: 3 (47.0)
 * JD-Core Version:       0.7.1
 */