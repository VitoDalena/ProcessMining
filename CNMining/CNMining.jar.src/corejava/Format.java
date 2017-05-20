/*     */ package corejava;
/*     */ 
/*     */ import java.io.PrintStream;
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
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class Format
/*     */   implements Serializable
/*     */ {
/*     */   static final long serialVersionUID = 1020L;
/*     */   private int width;
/*     */   private int precision;
/*     */   private String pre;
/*     */   private String post;
/*     */   private boolean leading_zeroes;
/*     */   private boolean show_plus;
/*     */   private boolean alternate;
/*     */   private boolean show_space;
/*     */   private boolean left_align;
/*     */   private char fmt;
/*     */   
/*     */   public Format(String paramString)
/*     */   {
/*  93 */     this.width = 0;
/*     */     
/*  95 */     this.precision = -1;
/*     */     
/*  97 */     this.pre = "";
/*     */     
/*  99 */     this.post = "";
/*     */     
/* 101 */     this.leading_zeroes = false;
/*     */     
/* 103 */     this.show_plus = false;
/*     */     
/* 105 */     this.alternate = false;
/*     */     
/* 107 */     this.show_space = false;
/*     */     
/* 109 */     this.left_align = false;
/*     */     
/* 111 */     this.fmt = ' ';
/*     */     
/*     */ 
/*     */ 
/* 115 */     int i = 0;
/*     */     
/* 117 */     int j = paramString.length();
/*     */     
/* 119 */     int k = 0;
/*     */     
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/* 125 */     int m = 0;
/*     */     
/*     */ 
/*     */ 
/* 129 */     while (k == 0)
/*     */     {
/* 131 */       if (m >= j) { k = 5;
/*     */       }
/* 133 */       else if (paramString.charAt(m) == '%')
/*     */       {
/* 135 */         if (m < j - 1)
/*     */         {
/* 137 */           if (paramString.charAt(m + 1) == '%')
/*     */           {
/* 139 */             this.pre += '%';
/*     */             
/* 141 */             m++;
/*     */ 
/*     */           }
/*     */           else
/*     */           {
/*     */ 
/* 147 */             k = 1;
/*     */           }
/*     */         }
/*     */         else {
/* 151 */           throw new IllegalArgumentException();
/*     */         }
/*     */         
/*     */ 
/*     */       }
/*     */       else {
/* 157 */         this.pre += paramString.charAt(m);
/*     */       }
/* 159 */       m++;
/*     */     }
/*     */     
/*     */ 
/* 163 */     while (k == 1)
/*     */     {
/* 165 */       if (m >= j) { k = 5;
/*     */       }
/* 167 */       else if (paramString.charAt(m) == ' ') { this.show_space = true;
/*     */       }
/* 169 */       else if (paramString.charAt(m) == '-') { this.left_align = true;
/*     */       }
/* 171 */       else if (paramString.charAt(m) == '+') { this.show_plus = true;
/*     */       }
/* 173 */       else if (paramString.charAt(m) == '0') { this.leading_zeroes = true;
/*     */       }
/* 175 */       else if (paramString.charAt(m) == '#') { this.alternate = true;
/*     */       } else {
/* 177 */         k = 2;m--;
/*     */       }
/* 179 */       m++;
/*     */     }
/*     */     
/*     */ 
/* 183 */     while (k == 2)
/*     */     {
/* 185 */       if (m >= j) { k = 5;
/*     */       }
/* 187 */       else if (('0' <= paramString.charAt(m)) && (paramString.charAt(m) <= '9'))
/*     */       {
/* 189 */         this.width = (this.width * 10 + paramString.charAt(m) - 48);
/*     */         
/* 191 */         m++;
/*     */ 
/*     */ 
/*     */       }
/* 195 */       else if (paramString.charAt(m) == '.')
/*     */       {
/* 197 */         k = 3;
/*     */         
/* 199 */         this.precision = 0;
/*     */         
/* 201 */         m++;
/*     */ 
/*     */       }
/*     */       else
/*     */       {
/*     */ 
/* 207 */         k = 4;
/*     */       }
/*     */     }
/*     */     
/* 211 */     while (k == 3)
/*     */     {
/* 213 */       if (m >= j) { k = 5;
/*     */       }
/* 215 */       else if (('0' <= paramString.charAt(m)) && (paramString.charAt(m) <= '9'))
/*     */       {
/* 217 */         this.precision = (this.precision * 10 + paramString.charAt(m) - 48);
/*     */         
/* 219 */         m++;
/*     */ 
/*     */       }
/*     */       else
/*     */       {
/*     */ 
/* 225 */         k = 4;
/*     */       }
/*     */     }
/*     */     
/* 229 */     if (k == 4)
/*     */     {
/* 231 */       if (m >= j) { k = 5;
/*     */       } else {
/* 233 */         this.fmt = paramString.charAt(m);
/*     */       }
/* 235 */       m++;
/*     */     }
/*     */     
/*     */ 
/* 239 */     if (m < j)
/*     */     {
/* 241 */       this.post = paramString.substring(m, j);
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
/*     */ 
/*     */   public static double atof(String paramString)
/*     */   {
/* 256 */     int i = 0;
/*     */     
/* 258 */     int j = 1;
/*     */     
/* 260 */     double d1 = 0.0D;
/*     */     
/* 262 */     double d2 = 0.0D;
/*     */     
/* 264 */     double d3 = 1.0D;
/*     */     
/* 266 */     int k = 0;
/*     */     
/*     */ 
/*     */ 
/* 270 */     while ((i < paramString.length()) && (Character.isWhitespace(paramString.charAt(i)))) { i++;
/*     */     }
/* 272 */     if ((i < paramString.length()) && (paramString.charAt(i) == '-')) { j = -1;i++;
/*     */     }
/* 274 */     else if ((i < paramString.length()) && (paramString.charAt(i) == '+')) { i++;
/*     */     }
/* 276 */     while (i < paramString.length())
/*     */     {
/* 278 */       int m = paramString.charAt(i);
/*     */       
/* 280 */       if ((48 <= m) && (m <= 57))
/*     */       {
/* 282 */         if (k == 0)
/*     */         {
/* 284 */           d1 = d1 * 10.0D + m - 48.0D;
/*     */         }
/* 286 */         else if (k == 1)
/*     */         {
/* 288 */           d3 /= 10.0D;
/*     */           
/* 290 */           d1 += d3 * (m - 48);
/*     */ 
/*     */         }
/*     */         
/*     */ 
/*     */       }
/* 296 */       else if (m == 46)
/*     */       {
/* 298 */         if (k == 0) { k = 1;
/*     */         } else {
/* 300 */           return j * d1;
/*     */         }
/*     */       }
/*     */       else {
/* 304 */         if ((m == 101) || (m == 69))
/*     */         {
/* 306 */           long l = (int)parseLong(paramString.substring(i + 1), 10);
/*     */           
/* 308 */           return j * d1 * Math.pow(10.0D, l);
/*     */         }
/*     */         
/*     */ 
/* 312 */         return j * d1;
/*     */       }
/* 314 */       i++;
/*     */     }
/*     */     
/*     */ 
/* 318 */     return j * d1;
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
/*     */   public static int atoi(String paramString)
/*     */   {
/* 335 */     return (int)atol(paramString);
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
/*     */   public static long atol(String paramString)
/*     */   {
/* 352 */     int i = 0;
/*     */     
/*     */ 
/*     */ 
/* 356 */     while ((i < paramString.length()) && (Character.isWhitespace(paramString.charAt(i)))) { i++;
/*     */     }
/* 358 */     if ((i < paramString.length()) && (paramString.charAt(i) == '0'))
/*     */     {
/* 360 */       if ((i + 1 < paramString.length()) && ((paramString.charAt(i + 1) == 'x') || (paramString.charAt(i + 1) == 'X')))
/*     */       {
/* 362 */         return parseLong(paramString.substring(i + 2), 16);
/*     */       }
/* 364 */       return parseLong(paramString, 8);
/*     */     }
/*     */     
/*     */ 
/* 368 */     return parseLong(paramString, 10);
/*     */   }
/*     */   
/*     */   private static String convert(long paramLong, int paramInt1, int paramInt2, String paramString)
/*     */   {
/* 373 */     if (paramLong == 0L) { return "0";
/*     */     }
/* 375 */     String str = "";
/*     */     
/* 377 */     while (paramLong != 0L)
/*     */     {
/* 379 */       str = paramString.charAt((int)(paramLong & paramInt2)) + str;
/*     */       
/* 381 */       paramLong >>>= paramInt1;
/*     */     }
/*     */     
/*     */ 
/* 385 */     return str;
/*     */   }
/*     */   
/*     */   private String exp_format(double paramDouble)
/*     */   {
/* 390 */     String str1 = "";
/*     */     
/* 392 */     int i = 0;
/*     */     
/* 394 */     double d1 = paramDouble;
/*     */     
/* 396 */     double d2 = 1.0D;
/*     */     
/* 398 */     if (paramDouble != 0.0D)
/*     */     {
/* 400 */       for (; d1 > 10.0D; d1 /= 10.0D) { i++;d2 /= 10.0D;
/*     */       }
/* 402 */       for (; d1 < 1.0D; d1 *= 10.0D) { i--;d2 *= 10.0D;
/*     */       }
/*     */     }
/*     */     
/* 406 */     if (((this.fmt == 'g') || (this.fmt == 'G')) && (i >= -4) && (i < this.precision))
/*     */     {
/* 408 */       return fixed_format(paramDouble);
/*     */     }
/*     */     
/* 411 */     if (paramDouble * d2 < Double.POSITIVE_INFINITY) {
/* 412 */       paramDouble *= d2;
/*     */     }
/* 414 */     str1 = str1 + fixed_format(paramDouble);
/*     */     
/*     */ 
/*     */ 
/* 418 */     if ((this.fmt == 'e') || (this.fmt == 'g'))
/*     */     {
/* 420 */       str1 = str1 + "e";
/*     */     }
/*     */     else
/*     */     {
/* 424 */       str1 = str1 + "E";
/*     */     }
/*     */     
/*     */ 
/* 428 */     String str2 = "000";
/*     */     
/* 430 */     if (i >= 0)
/*     */     {
/* 432 */       str1 = str1 + "+";
/*     */       
/* 434 */       str2 = str2 + i;
/*     */ 
/*     */     }
/*     */     else
/*     */     {
/*     */ 
/* 440 */       str1 = str1 + "-";
/*     */       
/* 442 */       str2 = str2 + -i;
/*     */     }
/*     */     
/*     */ 
/*     */ 
/*     */ 
/* 448 */     return str1 + str2.substring(str2.length() - 3, str2.length());
/*     */   }
/*     */   
/*     */   private String fixed_format(double paramDouble)
/*     */   {
/* 453 */     int i = ((this.fmt == 'G') || (this.fmt == 'g')) && (!this.alternate) ? 1 : 0;
/*     */     
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/* 461 */     if (paramDouble > 9.223372036854776E18D) { return exp_format(paramDouble);
/*     */     }
/* 463 */     if (this.precision == 0)
/*     */     {
/* 465 */       return (paramDouble + 0.5D) + (i != 0 ? "" : ".");
/*     */     }
/*     */     
/*     */ 
/* 469 */     long l1 = paramDouble;
/*     */     
/* 471 */     double d1 = paramDouble - l1;
/*     */     
/* 473 */     if ((d1 >= 1.0D) || (d1 < 0.0D)) { return exp_format(paramDouble);
/*     */     }
/*     */     
/*     */ 
/* 477 */     double d2 = 1.0D;
/*     */     
/* 479 */     String str1 = "";
/*     */     
/* 481 */     for (int j = 1; (j <= this.precision) && (d2 <= 9.223372036854776E18D); j++)
/*     */     {
/* 483 */       d2 *= 10.0D;
/*     */       
/* 485 */       str1 = str1 + "0";
/*     */     }
/*     */     
/*     */ 
/* 489 */     long l2 = (d2 * d1 + 0.5D);
/*     */     
/* 491 */     if (l2 >= d2) { l2 = 0L;l1 += 1L;
/*     */     }
/*     */     
/*     */ 
/* 495 */     String str2 = str1 + l2;
/*     */     
/* 497 */     str2 = "." + str2.substring(str2.length() - this.precision, str2.length());
/*     */     
/*     */ 
/*     */ 
/* 501 */     if (i != 0)
/*     */     {
/* 503 */       int k = str2.length() - 1;
/*     */       
/* 505 */       while ((k >= 0) && (str2.charAt(k) == '0')) { k--;
/*     */       }
/* 507 */       if ((k >= 0) && (str2.charAt(k) == '.')) { k--;
/*     */       }
/* 509 */       str2 = str2.substring(0, k + 1);
/*     */     }
/*     */     
/*     */ 
/*     */ 
/*     */ 
/* 515 */     return l1 + str2;
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
/*     */   public String form(char paramChar)
/*     */   {
/* 532 */     if (this.fmt != 'c')
/*     */     {
/* 534 */       throw new IllegalArgumentException();
/*     */     }
/*     */     
/*     */ 
/* 538 */     String str = "" + paramChar;
/*     */     
/* 540 */     return pad(str);
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
/*     */   public String form(double paramDouble)
/*     */   {
/* 561 */     if (this.precision < 0) { this.precision = 6;
/*     */     }
/* 563 */     int i = 1;
/*     */     
/* 565 */     if (paramDouble < 0.0D) { paramDouble = -paramDouble;i = -1; }
/*     */     String str;
/* 567 */     if (this.fmt == 'f')
/*     */     {
/* 569 */       str = fixed_format(paramDouble);
/*     */     }
/* 571 */     else if ((this.fmt == 'e') || (this.fmt == 'E') || (this.fmt == 'g') || (this.fmt == 'G'))
/*     */     {
/* 573 */       str = exp_format(paramDouble);
/*     */     } else {
/* 575 */       throw new IllegalArgumentException();
/*     */     }
/*     */     
/*     */ 
/* 579 */     return pad(sign(i, str));
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
/*     */   public String form(long paramLong)
/*     */   {
/* 598 */     int i = 0;
/*     */     String str;
/* 600 */     if ((this.fmt == 'd') || (this.fmt == 'i'))
/*     */     {
/* 602 */       if (paramLong < 0L)
/*     */       {
/* 604 */         str = ("" + paramLong).substring(1);
/*     */         
/* 606 */         i = -1;
/*     */ 
/*     */       }
/*     */       else
/*     */       {
/*     */ 
/* 612 */         str = "" + paramLong;
/*     */         
/* 614 */         i = 1;
/*     */ 
/*     */       }
/*     */       
/*     */ 
/*     */     }
/* 620 */     else if (this.fmt == 'o')
/*     */     {
/* 622 */       str = convert(paramLong, 3, 7, "01234567");
/*     */     }
/* 624 */     else if (this.fmt == 'x')
/*     */     {
/* 626 */       str = convert(paramLong, 4, 15, "0123456789abcdef");
/*     */     }
/* 628 */     else if (this.fmt == 'X')
/*     */     {
/* 630 */       str = convert(paramLong, 4, 15, "0123456789ABCDEF");
/*     */     } else {
/* 632 */       throw new IllegalArgumentException();
/*     */     }
/*     */     
/*     */ 
/* 636 */     return pad(sign(i, str));
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
/*     */   public String form(String paramString)
/*     */   {
/* 653 */     if (this.fmt != 's')
/*     */     {
/* 655 */       throw new IllegalArgumentException();
/*     */     }
/* 657 */     if ((this.precision >= 0) && (this.precision < paramString.length()))
/*     */     {
/* 659 */       paramString = paramString.substring(0, this.precision);
/*     */     }
/* 661 */     return pad(paramString);
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
/*     */   public static void main(String[] paramArrayOfString)
/*     */   {
/* 674 */     double d1 = 1.23456789012D;
/*     */     
/* 676 */     double d2 = 123.0D;
/*     */     
/* 678 */     double d3 = 1.2345E30D;
/*     */     
/* 680 */     double d4 = 1.02D;
/*     */     
/* 682 */     double d5 = 1.234E-5D;
/*     */     
/* 684 */     int i = 51966;
/*     */     
/* 686 */     print(System.out, "x = |%f|\n", d1);
/*     */     
/* 688 */     print(System.out, "u = |%20f|\n", d5);
/*     */     
/* 690 */     print(System.out, "x = |% .5f|\n", d1);
/*     */     
/* 692 */     print(System.out, "w = |%20.5f|\n", d4);
/*     */     
/* 694 */     print(System.out, "x = |%020.5f|\n", d1);
/*     */     
/* 696 */     print(System.out, "x = |%+20.5f|\n", d1);
/*     */     
/* 698 */     print(System.out, "x = |%+020.5f|\n", d1);
/*     */     
/* 700 */     print(System.out, "x = |% 020.5f|\n", d1);
/*     */     
/* 702 */     print(System.out, "y = |%#+20.5f|\n", d2);
/*     */     
/* 704 */     print(System.out, "y = |%-+20.5f|\n", d2);
/*     */     
/* 706 */     print(System.out, "z = |%20.5f|\n", d3);
/*     */     
/*     */ 
/*     */ 
/* 710 */     print(System.out, "x = |%e|\n", d1);
/*     */     
/* 712 */     print(System.out, "u = |%20e|\n", d5);
/*     */     
/* 714 */     print(System.out, "x = |% .5e|\n", d1);
/*     */     
/* 716 */     print(System.out, "w = |%20.5e|\n", d4);
/*     */     
/* 718 */     print(System.out, "x = |%020.5e|\n", d1);
/*     */     
/* 720 */     print(System.out, "x = |%+20.5e|\n", d1);
/*     */     
/* 722 */     print(System.out, "x = |%+020.5e|\n", d1);
/*     */     
/* 724 */     print(System.out, "x = |% 020.5e|\n", d1);
/*     */     
/* 726 */     print(System.out, "y = |%#+20.5e|\n", d2);
/*     */     
/* 728 */     print(System.out, "y = |%-+20.5e|\n", d2);
/*     */     
/*     */ 
/*     */ 
/* 732 */     print(System.out, "x = |%g|\n", d1);
/*     */     
/* 734 */     print(System.out, "z = |%g|\n", d3);
/*     */     
/* 736 */     print(System.out, "w = |%g|\n", d4);
/*     */     
/* 738 */     print(System.out, "u = |%g|\n", d5);
/*     */     
/* 740 */     print(System.out, "y = |%.2g|\n", d2);
/*     */     
/* 742 */     print(System.out, "y = |%#.2g|\n", d2);
/*     */     
/*     */ 
/*     */ 
/* 746 */     print(System.out, "d = |%d|\n", i);
/*     */     
/* 748 */     print(System.out, "d = |%20d|\n", i);
/*     */     
/* 750 */     print(System.out, "d = |%020d|\n", i);
/*     */     
/* 752 */     print(System.out, "d = |%+20d|\n", i);
/*     */     
/* 754 */     print(System.out, "d = |% 020d|\n", i);
/*     */     
/* 756 */     print(System.out, "d = |%-20d|\n", i);
/*     */     
/* 758 */     print(System.out, "d = |%20.8d|\n", i);
/*     */     
/* 760 */     print(System.out, "d = |%x|\n", i);
/*     */     
/* 762 */     print(System.out, "d = |%20X|\n", i);
/*     */     
/* 764 */     print(System.out, "d = |%#20x|\n", i);
/*     */     
/* 766 */     print(System.out, "d = |%020X|\n", i);
/*     */     
/* 768 */     print(System.out, "d = |%20.8x|\n", i);
/*     */     
/* 770 */     print(System.out, "d = |%o|\n", i);
/*     */     
/* 772 */     print(System.out, "d = |%020o|\n", i);
/*     */     
/* 774 */     print(System.out, "d = |%#20o|\n", i);
/*     */     
/* 776 */     print(System.out, "d = |%#020o|\n", i);
/*     */     
/* 778 */     print(System.out, "d = |%20.12o|\n", i);
/*     */     
/*     */ 
/*     */ 
/* 782 */     print(System.out, "s = |%-20s|\n", "Hello");
/*     */     
/* 784 */     print(System.out, "s = |%-20c|\n", '!');
/*     */     
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/* 792 */     print(System.out, "|%i|\n", Long.MIN_VALUE);
/*     */     
/*     */ 
/*     */ 
/* 796 */     print(System.out, "|%6.2e|\n", 0.0D);
/*     */     
/* 798 */     print(System.out, "|%6.2g|\n", 0.0D);
/*     */     
/*     */ 
/*     */ 
/* 802 */     print(System.out, "|%6.2f|\n", 9.99D);
/*     */     
/* 804 */     print(System.out, "|%6.2f|\n", 9.999D);
/*     */     
/*     */ 
/*     */ 
/* 808 */     print(System.out, "|%6.0f|\n", 9.999D);
/*     */   }
/*     */   
/*     */   private String pad(String paramString)
/*     */   {
/* 813 */     String str = repeat(' ', this.width - paramString.length());
/*     */     
/* 815 */     if (this.left_align) { return this.pre + paramString + str + this.post;
/*     */     }
/* 817 */     return this.pre + str + paramString + this.post;
/*     */   }
/*     */   
/*     */   private static long parseLong(String paramString, int paramInt)
/*     */   {
/* 822 */     int i = 0;
/*     */     
/* 824 */     int j = 1;
/*     */     
/* 826 */     long l = 0L;
/*     */     
/*     */ 
/*     */ 
/* 830 */     while ((i < paramString.length()) && (Character.isWhitespace(paramString.charAt(i)))) { i++;
/*     */     }
/* 832 */     if ((i < paramString.length()) && (paramString.charAt(i) == '-')) { j = -1;i++;
/*     */     }
/* 834 */     else if ((i < paramString.length()) && (paramString.charAt(i) == '+')) { i++;
/*     */     }
/* 836 */     while (i < paramString.length())
/*     */     {
/* 838 */       int k = paramString.charAt(i);
/*     */       
/* 840 */       if ((48 <= k) && (k < 48 + paramInt))
/*     */       {
/* 842 */         l = l * paramInt + k - 48L;
/*     */       }
/* 844 */       else if ((65 <= k) && (k < 65 + paramInt - 10))
/*     */       {
/* 846 */         l = l * paramInt + k - 65L + 10L;
/*     */       }
/* 848 */       else if ((97 <= k) && (k < 97 + paramInt - 10))
/*     */       {
/* 850 */         l = l * paramInt + k - 97L + 10L;
/*     */       }
/*     */       else
/*     */       {
/* 854 */         return l * j;
/*     */       }
/* 856 */       i++;
/*     */     }
/*     */     
/*     */ 
/* 860 */     return l * j;
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
/*     */   public static void print(PrintStream paramPrintStream, String paramString, char paramChar)
/*     */   {
/* 879 */     paramPrintStream.print(new Format(paramString).form(paramChar));
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
/*     */   public static void print(PrintStream paramPrintStream, String paramString, double paramDouble)
/*     */   {
/* 898 */     paramPrintStream.print(new Format(paramString).form(paramDouble));
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
/*     */   public static void print(PrintStream paramPrintStream, String paramString, long paramLong)
/*     */   {
/* 915 */     paramPrintStream.print(new Format(paramString).form(paramLong));
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
/*     */   public static void print(PrintStream paramPrintStream, String paramString1, String paramString2)
/*     */   {
/* 932 */     paramPrintStream.print(new Format(paramString1).form(paramString2));
/*     */   }
/*     */   
/*     */   private static String repeat(char paramChar, int paramInt)
/*     */   {
/* 937 */     if (paramInt <= 0) { return "";
/*     */     }
/* 939 */     StringBuffer localStringBuffer = new StringBuffer(paramInt);
/*     */     
/* 941 */     for (int i = 0; i < paramInt; i++) { localStringBuffer.append(paramChar);
/*     */     }
/* 943 */     return localStringBuffer.toString();
/*     */   }
/*     */   
/*     */   private String sign(int paramInt, String paramString)
/*     */   {
/* 948 */     String str = "";
/*     */     
/* 950 */     if (paramInt < 0) { str = "-";
/*     */     }
/* 952 */     else if (paramInt > 0)
/*     */     {
/* 954 */       if (this.show_plus) { str = "+";
/*     */       }
/* 956 */       else if (this.show_space) { str = " ";
/*     */       }
/*     */       
/*     */ 
/*     */ 
/*     */     }
/* 962 */     else if ((this.fmt == 'o') && (this.alternate) && (paramString.length() > 0) && (paramString.charAt(0) != '0')) { str = "0";
/*     */     }
/* 964 */     else if ((this.fmt == 'x') && (this.alternate)) { str = "0x";
/*     */     }
/* 966 */     else if ((this.fmt == 'X') && (this.alternate)) { str = "0X";
/*     */     }
/*     */     
/*     */ 
/* 970 */     int i = 0;
/*     */     
/* 972 */     if (this.leading_zeroes)
/*     */     {
/* 974 */       i = this.width;
/*     */     }
/* 976 */     else if (((this.fmt == 'd') || (this.fmt == 'i') || (this.fmt == 'x') || (this.fmt == 'X') || (this.fmt == 'o')) && (this.precision > 0))
/*     */     {
/* 978 */       i = this.precision;
/*     */     }
/*     */     
/*     */ 
/* 982 */     return str + repeat('0', i - str.length() - paramString.length()) + paramString;
/*     */   }
/*     */ }


/* Location:              /home/menick/Scrivania/ProcessMining/CNMining/CNMining.jar!/corejava/Format.class
 * Java compiler version: 1 (45.3)
 * JD-Core Version:       0.7.1
 */