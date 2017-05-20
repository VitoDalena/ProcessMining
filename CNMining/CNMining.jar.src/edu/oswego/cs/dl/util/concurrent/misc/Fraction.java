/*     */ package edu.oswego.cs.dl.util.concurrent.misc;
/*     */ 
/*     */ import java.io.Serializable;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
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
/*     */   implements Cloneable, Comparable, Serializable
/*     */ {
/*     */   protected final long numerator_;
/*     */   protected final long denominator_;
/*     */   
/*     */   public final long numerator()
/*     */   {
/*  27 */     return this.numerator_;
/*     */   }
/*     */   
/*  30 */   public final long denominator() { return this.denominator_; }
/*     */   
/*     */ 
/*     */   public Fraction(long paramLong1, long paramLong2)
/*     */   {
/*  35 */     int i = paramLong1 >= 0L ? 1 : 0;
/*  36 */     int j = paramLong2 >= 0L ? 1 : 0;
/*  37 */     long l1 = i != 0 ? paramLong1 : -paramLong1;
/*  38 */     long l2 = j != 0 ? paramLong2 : -paramLong2;
/*  39 */     long l3 = gcd(l1, l2);
/*  40 */     this.numerator_ = (i == j ? l1 / l3 : -l1 / l3);
/*  41 */     this.denominator_ = (l2 / l3);
/*     */   }
/*     */   
/*     */   public Fraction(Fraction paramFraction)
/*     */   {
/*  46 */     this.numerator_ = paramFraction.numerator();
/*  47 */     this.denominator_ = paramFraction.denominator();
/*     */   }
/*     */   
/*     */   public String toString() {
/*  51 */     if (denominator() == 1L) {
/*  52 */       return "" + numerator();
/*     */     }
/*  54 */     return numerator() + "/" + denominator();
/*     */   }
/*     */   
/*  57 */   public Object clone() { return new Fraction(this); }
/*     */   
/*     */   public double asDouble()
/*     */   {
/*  61 */     return numerator() / denominator();
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public static long gcd(long paramLong1, long paramLong2)
/*     */   {
/*  73 */     if (paramLong1 < 0L) paramLong1 = -paramLong1;
/*  74 */     if (paramLong2 < 0L) paramLong2 = -paramLong2;
/*     */     long l1;
/*  76 */     long l2; if (paramLong1 >= paramLong2) { l1 = paramLong1;l2 = paramLong2;
/*  77 */     } else { l1 = paramLong2;l2 = paramLong1;
/*     */     }
/*  79 */     while (l2 != 0L) {
/*  80 */       long l3 = l1 % l2;
/*  81 */       l1 = l2;
/*  82 */       l2 = l3;
/*     */     }
/*  84 */     return l1;
/*     */   }
/*     */   
/*     */   public Fraction negative()
/*     */   {
/*  89 */     long l1 = numerator();
/*  90 */     long l2 = denominator();
/*  91 */     return new Fraction(-l1, l2);
/*     */   }
/*     */   
/*     */   public Fraction inverse()
/*     */   {
/*  96 */     long l1 = numerator();
/*  97 */     long l2 = denominator();
/*  98 */     return new Fraction(l2, l1);
/*     */   }
/*     */   
/*     */ 
/*     */   public Fraction plus(Fraction paramFraction)
/*     */   {
/* 104 */     long l1 = numerator();
/* 105 */     long l2 = denominator();
/* 106 */     long l3 = paramFraction.numerator();
/* 107 */     long l4 = paramFraction.denominator();
/* 108 */     return new Fraction(l1 * l4 + l3 * l2, l2 * l4);
/*     */   }
/*     */   
/*     */   public Fraction plus(long paramLong)
/*     */   {
/* 113 */     long l1 = numerator();
/* 114 */     long l2 = denominator();
/* 115 */     long l3 = paramLong;
/* 116 */     long l4 = 1L;
/* 117 */     return new Fraction(l1 * l4 + l3 * l2, l2 * l4);
/*     */   }
/*     */   
/*     */   public Fraction minus(Fraction paramFraction)
/*     */   {
/* 122 */     long l1 = numerator();
/* 123 */     long l2 = denominator();
/* 124 */     long l3 = paramFraction.numerator();
/* 125 */     long l4 = paramFraction.denominator();
/* 126 */     return new Fraction(l1 * l4 - l3 * l2, l2 * l4);
/*     */   }
/*     */   
/*     */   public Fraction minus(long paramLong)
/*     */   {
/* 131 */     long l1 = numerator();
/* 132 */     long l2 = denominator();
/* 133 */     long l3 = paramLong;
/* 134 */     long l4 = 1L;
/* 135 */     return new Fraction(l1 * l4 - l3 * l2, l2 * l4);
/*     */   }
/*     */   
/*     */ 
/*     */   public Fraction times(Fraction paramFraction)
/*     */   {
/* 141 */     long l1 = numerator();
/* 142 */     long l2 = denominator();
/* 143 */     long l3 = paramFraction.numerator();
/* 144 */     long l4 = paramFraction.denominator();
/* 145 */     return new Fraction(l1 * l3, l2 * l4);
/*     */   }
/*     */   
/*     */   public Fraction times(long paramLong)
/*     */   {
/* 150 */     long l1 = numerator();
/* 151 */     long l2 = denominator();
/* 152 */     long l3 = paramLong;
/* 153 */     long l4 = 1L;
/* 154 */     return new Fraction(l1 * l3, l2 * l4);
/*     */   }
/*     */   
/*     */   public Fraction dividedBy(Fraction paramFraction)
/*     */   {
/* 159 */     long l1 = numerator();
/* 160 */     long l2 = denominator();
/* 161 */     long l3 = paramFraction.numerator();
/* 162 */     long l4 = paramFraction.denominator();
/* 163 */     return new Fraction(l1 * l4, l2 * l3);
/*     */   }
/*     */   
/*     */   public Fraction dividedBy(long paramLong)
/*     */   {
/* 168 */     long l1 = numerator();
/* 169 */     long l2 = denominator();
/* 170 */     long l3 = paramLong;
/* 171 */     long l4 = 1L;
/* 172 */     return new Fraction(l1 * l4, l2 * l3);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   public int compareTo(Object paramObject)
/*     */   {
/* 180 */     Fraction localFraction = (Fraction)paramObject;
/* 181 */     long l1 = numerator();
/* 182 */     long l2 = denominator();
/* 183 */     long l3 = localFraction.numerator();
/* 184 */     long l4 = localFraction.denominator();
/* 185 */     long l5 = l1 * l4;
/* 186 */     long l6 = l3 * l2;
/* 187 */     return l5 == l6 ? 0 : l5 < l6 ? -1 : 1;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   public int compareTo(long paramLong)
/*     */   {
/* 195 */     long l1 = numerator();
/* 196 */     long l2 = denominator();
/* 197 */     long l3 = paramLong;
/* 198 */     long l4 = 1L;
/* 199 */     long l5 = l1 * l4;
/* 200 */     long l6 = l3 * l2;
/* 201 */     return l5 == l6 ? 0 : l5 < l6 ? -1 : 1;
/*     */   }
/*     */   
/*     */   public boolean equals(Object paramObject) {
/* 205 */     return compareTo((Fraction)paramObject) == 0;
/*     */   }
/*     */   
/*     */   public boolean equals(long paramLong) {
/* 209 */     return compareTo(paramLong) == 0;
/*     */   }
/*     */   
/*     */   public int hashCode() {
/* 213 */     return (int)(this.numerator_ ^ this.denominator_);
/*     */   }
/*     */ }


/* Location:              /home/menick/Scrivania/ProcessMining/CNMining/CNMining.jar!/edu/oswego/cs/dl/util/concurrent/misc/Fraction.class
 * Java compiler version: 1 (45.3)
 * JD-Core Version:       0.7.1
 */