/*      */ package flanagan.complex;
/*      */ 
/*      */ import flanagan.math.Fmath;
/*      */ import java.io.IOException;
/*      */ import java.io.InputStream;
/*      */ import java.io.PrintStream;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ public class Complex
/*      */ {
/*   43 */   private double real = 0.0D;
/*   44 */   private double imag = 0.0D;
/*   45 */   private static char jori = 'j';
/*      */   
/*   47 */   private static boolean infOption = true;
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public Complex()
/*      */   {
/*   63 */     this.real = 0.0D;
/*   64 */     this.imag = 0.0D;
/*      */   }
/*      */   
/*      */ 
/*      */   public Complex(double real, double imag)
/*      */   {
/*   70 */     this.real = real;
/*   71 */     this.imag = imag;
/*      */   }
/*      */   
/*      */ 
/*      */   public Complex(double real)
/*      */   {
/*   77 */     this.real = real;
/*   78 */     this.imag = 0.0D;
/*      */   }
/*      */   
/*      */ 
/*      */   public Complex(Complex c)
/*      */   {
/*   84 */     this.real = c.real;
/*   85 */     this.imag = c.imag;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public void setReal(double real)
/*      */   {
/*   95 */     this.real = real;
/*      */   }
/*      */   
/*      */   public void setImag(double imag) {
/*   99 */     this.imag = imag;
/*      */   }
/*      */   
/*      */   public void reset(double real, double imag)
/*      */   {
/*  104 */     this.real = real;
/*  105 */     this.imag = imag;
/*      */   }
/*      */   
/*      */   public void polarRad(double mod, double arg)
/*      */   {
/*  110 */     this.real = (mod * Math.cos(arg));
/*  111 */     this.imag = (mod * Math.sin(arg));
/*      */   }
/*      */   
/*      */ 
/*      */   public void polar(double mod, double arg)
/*      */   {
/*  117 */     this.real = (mod * Math.cos(arg));
/*  118 */     this.imag = (mod * Math.sin(arg));
/*      */   }
/*      */   
/*      */   public void polarDeg(double mod, double arg)
/*      */   {
/*  123 */     arg = Math.toRadians(arg);
/*  124 */     this.real = (mod * Math.cos(arg));
/*  125 */     this.imag = (mod * Math.sin(arg));
/*      */   }
/*      */   
/*      */ 
/*      */   public double getReal()
/*      */   {
/*  131 */     return this.real;
/*      */   }
/*      */   
/*      */   public double getImag()
/*      */   {
/*  136 */     return this.imag;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public static final synchronized Complex readComplex(String prompt)
/*      */   {
/*  148 */     int ch = 32;
/*  149 */     String cstring = "";
/*  150 */     boolean done = false;
/*      */     
/*  152 */     System.out.print(prompt + " ");
/*  153 */     System.out.flush();
/*      */     
/*  155 */     while (!done) {
/*      */       try {
/*  157 */         ch = System.in.read();
/*  158 */         if ((ch < 0) || ((char)ch == '\n')) {
/*  159 */           done = true;
/*      */         } else {
/*  161 */           cstring = cstring + (char)ch;
/*      */         }
/*      */       } catch (IOException e) {
/*  164 */         done = true;
/*      */       }
/*      */     }
/*  167 */     return parseComplex(cstring);
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public static final synchronized Complex readComplex(String prompt, String dflt)
/*      */   {
/*  177 */     int ch = 32;
/*  178 */     String cstring = "";
/*  179 */     boolean done = false;
/*      */     
/*  181 */     System.out.print(prompt + " [default value = " + dflt + "]  ");
/*  182 */     System.out.flush();
/*      */     
/*  184 */     int i = 0;
/*  185 */     while (!done) {
/*      */       try {
/*  187 */         ch = System.in.read();
/*  188 */         if ((ch < 0) || ((char)ch == '\n') || ((char)ch == '\r')) {
/*  189 */           if (i == 0) {
/*  190 */             cstring = dflt;
/*  191 */             if ((char)ch == '\r') ch = System.in.read();
/*      */           }
/*  193 */           done = true;
/*      */         }
/*      */         else {
/*  196 */           cstring = cstring + (char)ch;
/*  197 */           i++;
/*      */         }
/*      */       }
/*      */       catch (IOException e) {
/*  201 */         done = true;
/*      */       }
/*      */     }
/*  204 */     return parseComplex(cstring);
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public static final synchronized Complex readComplex(String prompt, Complex dflt)
/*      */   {
/*  214 */     int ch = 32;
/*  215 */     String cstring = "";
/*  216 */     boolean done = false;
/*      */     
/*  218 */     System.out.print(prompt + " [default value = " + dflt + "]  ");
/*  219 */     System.out.flush();
/*      */     
/*  221 */     int i = 0;
/*  222 */     while (!done) {
/*      */       try {
/*  224 */         ch = System.in.read();
/*  225 */         if ((ch < 0) || ((char)ch == '\n') || ((char)ch == '\r')) {
/*  226 */           if (i == 0) {
/*  227 */             if ((char)ch == '\r') ch = System.in.read();
/*  228 */             return dflt;
/*      */           }
/*  230 */           done = true;
/*      */         }
/*      */         else {
/*  233 */           cstring = cstring + (char)ch;
/*  234 */           i++;
/*      */         }
/*      */       }
/*      */       catch (IOException e) {
/*  238 */         done = true;
/*      */       }
/*      */     }
/*  241 */     return parseComplex(cstring);
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public static final synchronized Complex readComplex()
/*      */   {
/*  252 */     int ch = 32;
/*  253 */     String cstring = "";
/*  254 */     boolean done = false;
/*      */     
/*  256 */     System.out.print(" ");
/*  257 */     System.out.flush();
/*      */     
/*  259 */     while (!done) {
/*      */       try {
/*  261 */         ch = System.in.read();
/*  262 */         if ((ch < 0) || ((char)ch == '\n')) {
/*  263 */           done = true;
/*      */         } else {
/*  265 */           cstring = cstring + (char)ch;
/*      */         }
/*      */       } catch (IOException e) {
/*  268 */         done = true;
/*      */       }
/*      */     }
/*  271 */     return parseComplex(cstring);
/*      */   }
/*      */   
/*      */ 
/*      */   public void println(String message)
/*      */   {
/*  277 */     System.out.println(message + " " + toString());
/*      */   }
/*      */   
/*      */   public void println()
/*      */   {
/*  282 */     System.out.println(" " + toString());
/*      */   }
/*      */   
/*      */   public void print(String message)
/*      */   {
/*  287 */     System.out.print(message + " " + toString());
/*      */   }
/*      */   
/*      */   public void print()
/*      */   {
/*  292 */     System.out.print(" " + toString());
/*      */   }
/*      */   
/*      */ 
/*      */   public static void println(String message, Complex[] aa)
/*      */   {
/*  298 */     System.out.println(message);
/*  299 */     for (int i = 0; i < aa.length; i++) {
/*  300 */       System.out.println(aa[i].toString() + "  ");
/*      */     }
/*      */   }
/*      */   
/*      */   public static void println(Complex[] aa)
/*      */   {
/*  306 */     for (int i = 0; i < aa.length; i++) {
/*  307 */       System.out.println(aa[i].toString() + "  ");
/*      */     }
/*      */   }
/*      */   
/*      */   public static void print(String message, Complex[] aa)
/*      */   {
/*  313 */     System.out.print(message + " ");
/*  314 */     for (int i = 0; i < aa.length; i++) {
/*  315 */       System.out.print(aa[i].toString() + "   ");
/*      */     }
/*  317 */     System.out.println();
/*      */   }
/*      */   
/*      */   public static void print(Complex[] aa)
/*      */   {
/*  322 */     for (int i = 0; i < aa.length; i++) {
/*  323 */       System.out.print(aa[i].toString() + "  ");
/*      */     }
/*  325 */     System.out.println();
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */   public static Complex truncate(Complex x, int prec)
/*      */   {
/*  332 */     if (prec < 0) { return x;
/*      */     }
/*  334 */     double xR = x.getReal();
/*  335 */     double xI = x.getImag();
/*  336 */     Complex y = new Complex();
/*      */     
/*  338 */     xR = Fmath.truncate(xR, prec);
/*  339 */     xI = Fmath.truncate(xI, prec);
/*      */     
/*  341 */     y.reset(xR, xI);
/*      */     
/*  343 */     return y;
/*      */   }
/*      */   
/*      */   public Complex truncate(int prec)
/*      */   {
/*  348 */     if (prec < 0) { return this;
/*      */     }
/*  350 */     double xR = getReal();
/*  351 */     double xI = getImag();
/*  352 */     Complex y = new Complex();
/*      */     
/*  354 */     xR = Fmath.truncate(xR, prec);
/*  355 */     xI = Fmath.truncate(xI, prec);
/*      */     
/*  357 */     y.reset(xR, xI);
/*      */     
/*  359 */     return y;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public String toString()
/*      */   {
/*  370 */     char ch = '+';
/*  371 */     if (this.imag < 0.0D) ch = '-';
/*  372 */     return this.real + " " + ch + " " + jori + Math.abs(this.imag);
/*      */   }
/*      */   
/*      */ 
/*      */   public static String toString(Complex aa)
/*      */   {
/*  378 */     char ch = '+';
/*  379 */     if (aa.imag < 0.0D) ch = '-';
/*  380 */     return aa.real + " " + ch + jori + Math.abs(aa.imag);
/*      */   }
/*      */   
/*      */   public static void setj()
/*      */   {
/*  385 */     jori = 'j';
/*      */   }
/*      */   
/*      */   public static void seti()
/*      */   {
/*  390 */     jori = 'i';
/*      */   }
/*      */   
/*      */   public static char getjori()
/*      */   {
/*  395 */     return jori;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public static Complex parseComplex(String ss)
/*      */   {
/*  404 */     Complex aa = new Complex();
/*  405 */     ss = ss.trim();
/*  406 */     double first = 1.0D;
/*  407 */     if (ss.charAt(0) == '-') {
/*  408 */       first = -1.0D;
/*  409 */       ss = ss.substring(1);
/*      */     }
/*      */     
/*  412 */     int i = ss.indexOf('j');
/*  413 */     if (i == -1) {
/*  414 */       i = ss.indexOf('i');
/*      */     }
/*  416 */     if (i == -1) { throw new NumberFormatException("no i or j found");
/*      */     }
/*  418 */     int imagSign = 1;
/*  419 */     int j = ss.indexOf('+');
/*      */     
/*  421 */     if (j == -1) {
/*  422 */       j = ss.indexOf('-');
/*  423 */       if (j > -1) imagSign = -1;
/*      */     }
/*  425 */     if (j == -1) { throw new NumberFormatException("no + or - found");
/*      */     }
/*  427 */     int r0 = 0;
/*  428 */     int r1 = j;
/*  429 */     int i0 = i + 1;
/*  430 */     int i1 = ss.length();
/*  431 */     String sreal = ss.substring(r0, r1);
/*  432 */     String simag = ss.substring(i0, i1);
/*  433 */     aa.real = (first * Double.parseDouble(sreal));
/*  434 */     aa.imag = (imagSign * Double.parseDouble(simag));
/*  435 */     return aa;
/*      */   }
/*      */   
/*      */ 
/*      */   public static Complex valueOf(String ss)
/*      */   {
/*  441 */     return parseComplex(ss);
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */   public int hashCode()
/*      */   {
/*  448 */     long lreal = Double.doubleToLongBits(this.real);
/*  449 */     long limag = Double.doubleToLongBits(this.imag);
/*  450 */     int hreal = (int)(lreal ^ lreal >>> 32);
/*  451 */     int himag = (int)(limag ^ limag >>> 32);
/*  452 */     return 7 * (hreal / 10) + 3 * (himag / 10);
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */   public static Complex[] oneDarray(int n)
/*      */   {
/*  460 */     Complex[] a = new Complex[n];
/*  461 */     for (int i = 0; i < n; i++) {
/*  462 */       a[i] = zero();
/*      */     }
/*  464 */     return a;
/*      */   }
/*      */   
/*      */ 
/*      */   public static Complex[] oneDarray(int n, double a, double b)
/*      */   {
/*  470 */     Complex[] c = new Complex[n];
/*  471 */     for (int i = 0; i < n; i++) {
/*  472 */       c[i] = zero();
/*  473 */       c[i].reset(a, b);
/*      */     }
/*  475 */     return c;
/*      */   }
/*      */   
/*      */   public static Complex mean(Complex[] aa)
/*      */   {
/*  480 */     int n = aa.length;
/*  481 */     Complex sum = new Complex(0.0D, 0.0D);
/*  482 */     for (int i = 0; i < n; i++) {
/*  483 */       sum = sum.plus(aa[i]);
/*      */     }
/*  485 */     return sum.over(n);
/*      */   }
/*      */   
/*      */ 
/*      */   public static Complex[] oneDarray(int n, Complex constant)
/*      */   {
/*  491 */     Complex[] c = new Complex[n];
/*  492 */     for (int i = 0; i < n; i++) {
/*  493 */       c[i] = copy(constant);
/*      */     }
/*  495 */     return c;
/*      */   }
/*      */   
/*      */ 
/*      */   public static Complex[][] twoDarray(int n, int m)
/*      */   {
/*  501 */     Complex[][] a = new Complex[n][m];
/*  502 */     for (int i = 0; i < n; i++) {
/*  503 */       for (int j = 0; j < m; j++) {
/*  504 */         a[i][j] = zero();
/*      */       }
/*      */     }
/*  507 */     return a;
/*      */   }
/*      */   
/*      */ 
/*      */   public static Complex[][] twoDarray(int n, int m, double a, double b)
/*      */   {
/*  513 */     Complex[][] c = new Complex[n][m];
/*  514 */     for (int i = 0; i < n; i++) {
/*  515 */       for (int j = 0; j < m; j++) {
/*  516 */         c[i][j] = zero();
/*  517 */         c[i][j].reset(a, b);
/*      */       }
/*      */     }
/*  520 */     return c;
/*      */   }
/*      */   
/*      */ 
/*      */   public static Complex[][] twoDarray(int n, int m, Complex constant)
/*      */   {
/*  526 */     Complex[][] c = new Complex[n][m];
/*  527 */     for (int i = 0; i < n; i++) {
/*  528 */       for (int j = 0; j < m; j++) {
/*  529 */         c[i][j] = copy(constant);
/*      */       }
/*      */     }
/*  532 */     return c;
/*      */   }
/*      */   
/*      */ 
/*      */   public static Complex[][][] threeDarray(int n, int m, int l)
/*      */   {
/*  538 */     Complex[][][] a = new Complex[n][m][l];
/*  539 */     for (int i = 0; i < n; i++) {
/*  540 */       for (int j = 0; j < m; j++) {
/*  541 */         for (int k = 0; k < l; k++) {
/*  542 */           a[i][j][k] = zero();
/*      */         }
/*      */       }
/*      */     }
/*  546 */     return a;
/*      */   }
/*      */   
/*      */ 
/*      */   public static Complex[][][] threeDarray(int n, int m, int l, double a, double b)
/*      */   {
/*  552 */     Complex[][][] c = new Complex[n][m][l];
/*  553 */     for (int i = 0; i < n; i++) {
/*  554 */       for (int j = 0; j < m; j++) {
/*  555 */         for (int k = 0; k < l; k++) {
/*  556 */           c[i][j][k] = zero();
/*  557 */           c[i][j][k].reset(a, b);
/*      */         }
/*      */       }
/*      */     }
/*  561 */     return c;
/*      */   }
/*      */   
/*      */ 
/*      */   public static Complex[][][] threeDarray(int n, int m, int l, Complex constant)
/*      */   {
/*  567 */     Complex[][][] c = new Complex[n][m][l];
/*  568 */     for (int i = 0; i < n; i++) {
/*  569 */       for (int j = 0; j < m; j++) {
/*  570 */         for (int k = 0; k < l; k++) {
/*  571 */           c[i][j][k] = copy(constant);
/*      */         }
/*      */       }
/*      */     }
/*  575 */     return c;
/*      */   }
/*      */   
/*      */ 
/*      */   public static Complex copy(Complex a)
/*      */   {
/*  581 */     if (a == null) {
/*  582 */       return null;
/*      */     }
/*      */     
/*  585 */     Complex b = new Complex();
/*  586 */     b.real = a.real;
/*  587 */     b.imag = a.imag;
/*  588 */     return b;
/*      */   }
/*      */   
/*      */ 
/*      */   public Complex copy()
/*      */   {
/*  594 */     if (this == null) {
/*  595 */       return null;
/*      */     }
/*      */     
/*  598 */     Complex b = new Complex();
/*  599 */     b.real = this.real;
/*  600 */     b.imag = this.imag;
/*  601 */     return b;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */   public static Complex[] copy(Complex[] a)
/*      */   {
/*  609 */     if (a == null) {
/*  610 */       return null;
/*      */     }
/*      */     
/*  613 */     int n = a.length;
/*  614 */     Complex[] b = oneDarray(n);
/*  615 */     for (int i = 0; i < n; i++) {
/*  616 */       b[i] = copy(a[i]);
/*      */     }
/*  618 */     return b;
/*      */   }
/*      */   
/*      */ 
/*      */   public static Complex[][] copy(Complex[][] a)
/*      */   {
/*  624 */     if (a == null) {
/*  625 */       return (Complex[][])null;
/*      */     }
/*      */     
/*  628 */     int n = a.length;
/*  629 */     int m = a[0].length;
/*  630 */     Complex[][] b = twoDarray(n, m);
/*  631 */     for (int i = 0; i < n; i++) {
/*  632 */       for (int j = 0; j < m; j++) {
/*  633 */         b[i][j] = copy(a[i][j]);
/*      */       }
/*      */     }
/*  636 */     return b;
/*      */   }
/*      */   
/*      */ 
/*      */   public static Complex[][][] copy(Complex[][][] a)
/*      */   {
/*  642 */     if (a == null) {
/*  643 */       return (Complex[][][])null;
/*      */     }
/*      */     
/*  646 */     int n = a.length;
/*  647 */     int m = a[0].length;
/*  648 */     int l = a[0][0].length;
/*  649 */     Complex[][][] b = threeDarray(n, m, l);
/*  650 */     for (int i = 0; i < n; i++) {
/*  651 */       for (int j = 0; j < m; j++) {
/*  652 */         for (int k = 0; k < l; k++) {
/*  653 */           b[i][j][k] = copy(a[i][j][k]);
/*      */         }
/*      */       }
/*      */     }
/*  657 */     return b;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */   public Object clone()
/*      */   {
/*  665 */     Object ret = null;
/*      */     
/*  667 */     if (this != null) {
/*  668 */       Complex b = new Complex();
/*  669 */       b.real = this.real;
/*  670 */       b.imag = this.imag;
/*  671 */       ret = b;
/*      */     }
/*      */     
/*  674 */     return ret;
/*      */   }
/*      */   
/*      */ 
/*      */   public static Complex plus(Complex a, Complex b)
/*      */   {
/*  680 */     Complex c = new Complex();
/*  681 */     a.real += b.real;
/*  682 */     a.imag += b.imag;
/*  683 */     return c;
/*      */   }
/*      */   
/*      */   public static Complex plus(Complex a, double b)
/*      */   {
/*  688 */     Complex c = new Complex();
/*  689 */     a.real += b;
/*  690 */     c.imag = a.imag;
/*  691 */     return c;
/*      */   }
/*      */   
/*      */   public static Complex plus(double a, Complex b)
/*      */   {
/*  696 */     Complex c = new Complex();
/*  697 */     c.real = (a + b.real);
/*  698 */     c.imag = b.imag;
/*  699 */     return c;
/*      */   }
/*      */   
/*      */   public static Complex plus(double a, double b)
/*      */   {
/*  704 */     Complex c = new Complex();
/*  705 */     c.real = (a + b);
/*  706 */     c.imag = 0.0D;
/*  707 */     return c;
/*      */   }
/*      */   
/*      */ 
/*      */   public Complex plus(Complex a)
/*      */   {
/*  713 */     Complex b = new Complex();
/*  714 */     this.real += a.real;
/*  715 */     this.imag += a.imag;
/*  716 */     return b;
/*      */   }
/*      */   
/*      */ 
/*      */   public Complex plus(double a)
/*      */   {
/*  722 */     Complex b = new Complex();
/*  723 */     this.real += a;
/*  724 */     b.imag = this.imag;
/*  725 */     return b;
/*      */   }
/*      */   
/*      */   public void plusEquals(Complex a)
/*      */   {
/*  730 */     this.real += a.real;
/*  731 */     this.imag += a.imag;
/*      */   }
/*      */   
/*      */   public void plusEquals(double a)
/*      */   {
/*  736 */     this.real += a;
/*  737 */     this.imag = this.imag;
/*      */   }
/*      */   
/*      */ 
/*      */   public static Complex minus(Complex a, Complex b)
/*      */   {
/*  743 */     Complex c = new Complex();
/*  744 */     a.real -= b.real;
/*  745 */     a.imag -= b.imag;
/*  746 */     return c;
/*      */   }
/*      */   
/*      */   public static Complex minus(Complex a, double b)
/*      */   {
/*  751 */     Complex c = new Complex();
/*  752 */     a.real -= b;
/*  753 */     c.imag = a.imag;
/*  754 */     return c;
/*      */   }
/*      */   
/*      */   public static Complex minus(double a, Complex b)
/*      */   {
/*  759 */     Complex c = new Complex();
/*  760 */     c.real = (a - b.real);
/*  761 */     c.imag = (-b.imag);
/*  762 */     return c;
/*      */   }
/*      */   
/*      */   public static Complex minus(double a, double b)
/*      */   {
/*  767 */     Complex c = new Complex();
/*  768 */     c.real = (a - b);
/*  769 */     c.imag = 0.0D;
/*  770 */     return c;
/*      */   }
/*      */   
/*      */ 
/*      */   public Complex minus(Complex a)
/*      */   {
/*  776 */     Complex b = new Complex();
/*  777 */     this.real -= a.real;
/*  778 */     this.imag -= a.imag;
/*  779 */     return b;
/*      */   }
/*      */   
/*      */ 
/*      */   public Complex minus(double a)
/*      */   {
/*  785 */     Complex b = new Complex();
/*  786 */     this.real -= a;
/*  787 */     b.imag = this.imag;
/*  788 */     return b;
/*      */   }
/*      */   
/*      */ 
/*      */   public Complex transposedMinus(double a)
/*      */   {
/*  794 */     Complex b = new Complex();
/*  795 */     b.real = (a - this.real);
/*  796 */     b.imag = this.imag;
/*  797 */     return b;
/*      */   }
/*      */   
/*      */   public void minusEquals(Complex a)
/*      */   {
/*  802 */     this.real -= a.real;
/*  803 */     this.imag -= a.imag;
/*      */   }
/*      */   
/*      */   public void minusEquals(double a)
/*      */   {
/*  808 */     this.real -= a;
/*  809 */     this.imag = this.imag;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */   public static void setInfOption(boolean infOpt)
/*      */   {
/*  817 */     infOption = infOpt;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */   public static void setInfOption(int opt)
/*      */   {
/*  824 */     if ((opt < 0) || (opt > 1)) throw new IllegalArgumentException("opt must be 0 or 1");
/*  825 */     infOption = true;
/*  826 */     if (opt == 1) { infOption = false;
/*      */     }
/*      */   }
/*      */   
/*      */ 
/*      */   public static boolean getInfOption()
/*      */   {
/*  833 */     return infOption;
/*      */   }
/*      */   
/*      */   public static Complex times(Complex a, Complex b)
/*      */   {
/*  838 */     Complex c = new Complex(0.0D, 0.0D);
/*  839 */     if (infOption) {
/*  840 */       if ((a.isInfinite()) && (!b.isZero())) {
/*  841 */         c.reset(Double.POSITIVE_INFINITY, Double.POSITIVE_INFINITY);
/*  842 */         return c;
/*      */       }
/*  844 */       if ((b.isInfinite()) && (!a.isZero())) {
/*  845 */         c.reset(Double.POSITIVE_INFINITY, Double.POSITIVE_INFINITY);
/*  846 */         return c;
/*      */       }
/*      */     }
/*      */     
/*  850 */     c.real = (a.real * b.real - a.imag * b.imag);
/*  851 */     c.imag = (a.real * b.imag + a.imag * b.real);
/*  852 */     return c;
/*      */   }
/*      */   
/*      */   public static Complex times(Complex a, double b)
/*      */   {
/*  857 */     Complex c = new Complex();
/*  858 */     if (infOption) {
/*  859 */       if ((a.isInfinite()) && (b != 0.0D)) {
/*  860 */         c.reset(Double.POSITIVE_INFINITY, Double.POSITIVE_INFINITY);
/*  861 */         return c;
/*      */       }
/*  863 */       if ((Fmath.isInfinity(b)) && (!a.isZero())) {
/*  864 */         c.reset(Double.POSITIVE_INFINITY, Double.POSITIVE_INFINITY);
/*  865 */         return c;
/*      */       }
/*      */     }
/*  868 */     a.real *= b;
/*  869 */     a.imag *= b;
/*  870 */     return c;
/*      */   }
/*      */   
/*      */   public static Complex times(double a, Complex b)
/*      */   {
/*  875 */     Complex c = new Complex();
/*  876 */     if (infOption) {
/*  877 */       if ((b.isInfinite()) && (a != 0.0D)) {
/*  878 */         c.reset(Double.POSITIVE_INFINITY, Double.POSITIVE_INFINITY);
/*  879 */         return c;
/*      */       }
/*  881 */       if ((Fmath.isInfinity(a)) && (!b.isZero())) {
/*  882 */         c.reset(Double.POSITIVE_INFINITY, Double.POSITIVE_INFINITY);
/*  883 */         return c;
/*      */       }
/*      */     }
/*      */     
/*  887 */     c.real = (a * b.real);
/*  888 */     c.imag = (a * b.imag);
/*  889 */     return c;
/*      */   }
/*      */   
/*      */   public static Complex times(double a, double b)
/*      */   {
/*  894 */     Complex c = new Complex();
/*  895 */     c.real = (a * b);
/*  896 */     c.imag = 0.0D;
/*  897 */     return c;
/*      */   }
/*      */   
/*      */ 
/*      */   public Complex times(Complex a)
/*      */   {
/*  903 */     Complex b = new Complex();
/*  904 */     if (infOption) {
/*  905 */       if ((isInfinite()) && (!a.isZero())) {
/*  906 */         b.reset(Double.POSITIVE_INFINITY, Double.POSITIVE_INFINITY);
/*  907 */         return b;
/*      */       }
/*  909 */       if ((a.isInfinite()) && (!isZero())) {
/*  910 */         b.reset(Double.POSITIVE_INFINITY, Double.POSITIVE_INFINITY);
/*  911 */         return b;
/*      */       }
/*      */     }
/*      */     
/*  915 */     b.real = (this.real * a.real - this.imag * a.imag);
/*  916 */     b.imag = (this.real * a.imag + this.imag * a.real);
/*  917 */     return b;
/*      */   }
/*      */   
/*      */ 
/*      */   public Complex times(double a)
/*      */   {
/*  923 */     Complex b = new Complex();
/*  924 */     if (infOption) {
/*  925 */       if ((isInfinite()) && (a != 0.0D)) {
/*  926 */         b.reset(Double.POSITIVE_INFINITY, Double.POSITIVE_INFINITY);
/*  927 */         return b;
/*      */       }
/*  929 */       if ((Fmath.isInfinity(a)) && (!isZero())) {
/*  930 */         b.reset(Double.POSITIVE_INFINITY, Double.POSITIVE_INFINITY);
/*  931 */         return b;
/*      */       }
/*      */     }
/*      */     
/*  935 */     this.real *= a;
/*  936 */     this.imag *= a;
/*  937 */     return b;
/*      */   }
/*      */   
/*      */   public void timesEquals(Complex a)
/*      */   {
/*  942 */     Complex b = new Complex();
/*  943 */     boolean test = true;
/*  944 */     if ((infOption) && (
/*  945 */       ((isInfinite()) && (!a.isZero())) || ((a.isInfinite()) && (!isZero())))) {
/*  946 */       this.real = Double.POSITIVE_INFINITY;
/*  947 */       this.imag = Double.POSITIVE_INFINITY;
/*  948 */       test = false;
/*      */     }
/*      */     
/*  951 */     if (test) {
/*  952 */       b.real = (a.real * this.real - a.imag * this.imag);
/*  953 */       b.imag = (a.real * this.imag + a.imag * this.real);
/*  954 */       this.real = b.real;
/*  955 */       this.imag = b.imag;
/*      */     }
/*      */   }
/*      */   
/*      */   public void timesEquals(double a)
/*      */   {
/*  961 */     boolean test = true;
/*  962 */     if ((infOption) && (
/*  963 */       ((isInfinite()) && (a != 0.0D)) || ((Fmath.isInfinity(a)) && (!isZero())))) {
/*  964 */       this.real = Double.POSITIVE_INFINITY;
/*  965 */       this.imag = Double.POSITIVE_INFINITY;
/*  966 */       test = false;
/*      */     }
/*      */     
/*  969 */     if (test) {
/*  970 */       this.real *= a;
/*  971 */       this.imag *= a;
/*      */     }
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */   public static Complex over(Complex a, Complex b)
/*      */   {
/*  979 */     Complex c = new Complex(0.0D, 0.0D);
/*  980 */     if ((infOption) && (!a.isInfinite()) && (b.isInfinite())) { return c;
/*      */     }
/*  982 */     double denom = 0.0D;double ratio = 0.0D;
/*  983 */     if (a.isZero()) {
/*  984 */       if (b.isZero()) {
/*  985 */         c.real = NaN.0D;
/*  986 */         c.imag = NaN.0D;
/*      */       }
/*      */       else {
/*  989 */         c.real = 0.0D;
/*  990 */         c.imag = 0.0D;
/*      */       }
/*      */       
/*      */     }
/*  994 */     else if (Math.abs(b.real) >= Math.abs(b.imag)) {
/*  995 */       ratio = b.imag / b.real;
/*  996 */       denom = b.real + b.imag * ratio;
/*  997 */       c.real = ((a.real + a.imag * ratio) / denom);
/*  998 */       c.imag = ((a.imag - a.real * ratio) / denom);
/*      */     }
/*      */     else {
/* 1001 */       ratio = b.real / b.imag;
/* 1002 */       denom = b.real * ratio + b.imag;
/* 1003 */       c.real = ((a.real * ratio + a.imag) / denom);
/* 1004 */       c.imag = ((a.imag * ratio - a.real) / denom);
/*      */     }
/*      */     
/* 1007 */     return c;
/*      */   }
/*      */   
/*      */   public static Complex over(Complex a, double b)
/*      */   {
/* 1012 */     Complex c = new Complex(0.0D, 0.0D);
/* 1013 */     if ((infOption) && (Fmath.isInfinity(b))) { return c;
/*      */     }
/* 1015 */     a.real /= b;
/* 1016 */     a.imag /= b;
/* 1017 */     return c;
/*      */   }
/*      */   
/*      */   public static Complex over(double a, Complex b)
/*      */   {
/* 1022 */     Complex c = new Complex();
/* 1023 */     if ((infOption) && (!Fmath.isInfinity(a)) && (b.isInfinite())) { return c;
/*      */     }
/*      */     
/*      */ 
/* 1027 */     if (a == 0.0D) {
/* 1028 */       if (b.isZero()) {
/* 1029 */         c.real = NaN.0D;
/* 1030 */         c.imag = NaN.0D;
/*      */       }
/*      */       else {
/* 1033 */         c.real = 0.0D;
/* 1034 */         c.imag = 0.0D;
/*      */       }
/*      */       
/*      */     }
/* 1038 */     else if (Math.abs(b.real) >= Math.abs(b.imag)) {
/* 1039 */       double ratio = b.imag / b.real;
/* 1040 */       double denom = b.real + b.imag * ratio;
/* 1041 */       c.real = (a / denom);
/* 1042 */       c.imag = (-a * ratio / denom);
/*      */     }
/*      */     else {
/* 1045 */       double ratio = b.real / b.imag;
/* 1046 */       double denom = b.real * ratio + b.imag;
/* 1047 */       c.real = (a * ratio / denom);
/* 1048 */       c.imag = (-a / denom);
/*      */     }
/*      */     
/* 1051 */     return c;
/*      */   }
/*      */   
/*      */   public static Complex over(double a, double b)
/*      */   {
/* 1056 */     Complex c = new Complex();
/* 1057 */     c.real = (a / b);
/* 1058 */     c.imag = 0.0D;
/* 1059 */     return c;
/*      */   }
/*      */   
/*      */ 
/*      */   public Complex over(Complex a)
/*      */   {
/* 1065 */     Complex b = new Complex(0.0D, 0.0D);
/* 1066 */     if ((infOption) && (!isInfinite()) && (a.isInfinite())) { return b;
/*      */     }
/* 1068 */     double denom = 0.0D;double ratio = 0.0D;
/* 1069 */     if (Math.abs(a.real) >= Math.abs(a.imag)) {
/* 1070 */       ratio = a.imag / a.real;
/* 1071 */       denom = a.real + a.imag * ratio;
/* 1072 */       b.real = ((this.real + this.imag * ratio) / denom);
/* 1073 */       b.imag = ((this.imag - this.real * ratio) / denom);
/*      */     }
/*      */     else
/*      */     {
/* 1077 */       ratio = a.real / a.imag;
/* 1078 */       denom = a.real * ratio + a.imag;
/* 1079 */       b.real = ((this.real * ratio + this.imag) / denom);
/* 1080 */       b.imag = ((this.imag * ratio - this.real) / denom);
/*      */     }
/* 1082 */     return b;
/*      */   }
/*      */   
/*      */ 
/*      */   public Complex over(double a)
/*      */   {
/* 1088 */     Complex b = new Complex(0.0D, 0.0D);
/*      */     
/* 1090 */     this.real /= a;
/* 1091 */     this.imag /= a;
/* 1092 */     return b;
/*      */   }
/*      */   
/*      */ 
/*      */   public Complex transposedOver(double a)
/*      */   {
/* 1098 */     Complex c = new Complex(0.0D, 0.0D);
/* 1099 */     if ((infOption) && (!Fmath.isInfinity(a)) && (isInfinite())) { return c;
/*      */     }
/* 1101 */     double denom = 0.0D;double ratio = 0.0D;
/* 1102 */     if (Math.abs(this.real) >= Math.abs(this.imag)) {
/* 1103 */       ratio = this.imag / this.real;
/* 1104 */       denom = this.real + this.imag * ratio;
/* 1105 */       c.real = (a / denom);
/* 1106 */       c.imag = (-a * ratio / denom);
/*      */     }
/*      */     else
/*      */     {
/* 1110 */       ratio = this.real / this.imag;
/* 1111 */       denom = this.real * ratio + this.imag;
/* 1112 */       c.real = (a * ratio / denom);
/* 1113 */       c.imag = (-a / denom);
/*      */     }
/* 1115 */     return c;
/*      */   }
/*      */   
/*      */   public void overEquals(Complex b)
/*      */   {
/* 1120 */     Complex c = new Complex(0.0D, 0.0D);
/*      */     
/* 1122 */     boolean test = true;
/* 1123 */     if ((infOption) && (!isInfinite()) && (b.isInfinite())) {
/* 1124 */       this.real = 0.0D;
/* 1125 */       this.imag = 0.0D;
/* 1126 */       test = false;
/*      */     }
/* 1128 */     if (test) {
/* 1129 */       double denom = 0.0D;double ratio = 0.0D;
/* 1130 */       if (Math.abs(b.real) >= Math.abs(b.imag)) {
/* 1131 */         ratio = b.imag / b.real;
/* 1132 */         denom = b.real + b.imag * ratio;
/* 1133 */         c.real = ((this.real + this.imag * ratio) / denom);
/* 1134 */         c.imag = ((this.imag - this.real * ratio) / denom);
/*      */       }
/*      */       else
/*      */       {
/* 1138 */         ratio = b.real / b.imag;
/* 1139 */         denom = b.real * ratio + b.imag;
/* 1140 */         c.real = ((this.real * ratio + this.imag) / denom);
/* 1141 */         c.imag = ((this.imag * ratio - this.real) / denom);
/*      */       }
/* 1143 */       this.real = c.real;
/* 1144 */       this.imag = c.imag;
/*      */     }
/*      */   }
/*      */   
/*      */   public void overEquals(double a)
/*      */   {
/* 1150 */     this.real /= a;
/* 1151 */     this.imag /= a;
/*      */   }
/*      */   
/*      */ 
/*      */   public static Complex inverse(Complex a)
/*      */   {
/* 1157 */     Complex b = new Complex(0.0D, 0.0D);
/* 1158 */     if ((infOption) && (a.isInfinite())) { return b;
/*      */     }
/* 1160 */     b = over(1.0D, a);
/* 1161 */     return b;
/*      */   }
/*      */   
/*      */   public Complex inverse()
/*      */   {
/* 1166 */     Complex b = new Complex(0.0D, 0.0D);
/* 1167 */     b = over(1.0D, this);
/* 1168 */     return b;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */   public static Complex negate(Complex a)
/*      */   {
/* 1175 */     Complex c = new Complex();
/* 1176 */     c.real = (-a.real);
/* 1177 */     c.imag = (-a.imag);
/* 1178 */     return c;
/*      */   }
/*      */   
/*      */   public Complex negate()
/*      */   {
/* 1183 */     Complex c = new Complex();
/* 1184 */     c.real = (-this.real);
/* 1185 */     c.imag = (-this.imag);
/* 1186 */     return c;
/*      */   }
/*      */   
/*      */   public static double abs(Complex a)
/*      */   {
/* 1191 */     double rmod = Math.abs(a.real);
/* 1192 */     double imod = Math.abs(a.imag);
/* 1193 */     double ratio = 0.0D;
/* 1194 */     double res = 0.0D;
/*      */     
/* 1196 */     if (rmod == 0.0D) {
/* 1197 */       res = imod;
/*      */     }
/*      */     else {
/* 1200 */       if (imod == 0.0D) {
/* 1201 */         res = rmod;
/*      */       }
/* 1203 */       if (rmod >= imod) {
/* 1204 */         ratio = a.imag / a.real;
/* 1205 */         res = rmod * Math.sqrt(1.0D + ratio * ratio);
/*      */       }
/*      */       else {
/* 1208 */         ratio = a.real / a.imag;
/* 1209 */         res = imod * Math.sqrt(1.0D + ratio * ratio);
/*      */       }
/*      */     }
/* 1212 */     return res;
/*      */   }
/*      */   
/*      */   public double abs()
/*      */   {
/* 1217 */     double rmod = Math.abs(this.real);
/* 1218 */     double imod = Math.abs(this.imag);
/* 1219 */     double ratio = 0.0D;
/* 1220 */     double res = 0.0D;
/*      */     
/* 1222 */     if (rmod == 0.0D) {
/* 1223 */       res = imod;
/*      */     }
/*      */     else {
/* 1226 */       if (imod == 0.0D) {
/* 1227 */         res = rmod;
/*      */       }
/* 1229 */       if (rmod >= imod) {
/* 1230 */         ratio = this.imag / this.real;
/* 1231 */         res = rmod * Math.sqrt(1.0D + ratio * ratio);
/*      */       }
/*      */       else
/*      */       {
/* 1235 */         ratio = this.real / this.imag;
/* 1236 */         res = imod * Math.sqrt(1.0D + ratio * ratio);
/*      */       }
/*      */     }
/* 1239 */     return res;
/*      */   }
/*      */   
/*      */ 
/*      */   public static double squareAbs(Complex a)
/*      */   {
/* 1245 */     return a.real * a.real + a.imag * a.imag;
/*      */   }
/*      */   
/*      */   public double squareAbs()
/*      */   {
/* 1250 */     return this.real * this.real + this.imag * this.imag;
/*      */   }
/*      */   
/*      */   public static double arg(Complex a)
/*      */   {
/* 1255 */     return Math.atan2(a.imag, a.real);
/*      */   }
/*      */   
/*      */   public double arg()
/*      */   {
/* 1260 */     return Math.atan2(this.imag, this.real);
/*      */   }
/*      */   
/*      */   public static double argRad(Complex a)
/*      */   {
/* 1265 */     return Math.atan2(a.imag, a.real);
/*      */   }
/*      */   
/*      */   public double argRad()
/*      */   {
/* 1270 */     return Math.atan2(this.imag, this.real);
/*      */   }
/*      */   
/*      */   public static double argDeg(Complex a)
/*      */   {
/* 1275 */     return Math.toDegrees(Math.atan2(a.imag, a.real));
/*      */   }
/*      */   
/*      */   public double argDeg()
/*      */   {
/* 1280 */     return Math.toDegrees(Math.atan2(this.imag, this.real));
/*      */   }
/*      */   
/*      */   public static Complex conjugate(Complex a)
/*      */   {
/* 1285 */     Complex c = new Complex();
/* 1286 */     c.real = a.real;
/* 1287 */     c.imag = (-a.imag);
/* 1288 */     return c;
/*      */   }
/*      */   
/*      */   public Complex conjugate()
/*      */   {
/* 1293 */     Complex c = new Complex();
/* 1294 */     c.real = this.real;
/* 1295 */     c.imag = (-this.imag);
/* 1296 */     return c;
/*      */   }
/*      */   
/*      */ 
/*      */   public static double hypot(Complex aa, Complex bb)
/*      */   {
/* 1302 */     double amod = abs(aa);
/* 1303 */     double bmod = abs(bb);
/* 1304 */     double cc = 0.0D;double ratio = 0.0D;
/*      */     
/* 1306 */     if (amod == 0.0D) {
/* 1307 */       cc = bmod;
/*      */ 
/*      */     }
/* 1310 */     else if (bmod == 0.0D) {
/* 1311 */       cc = amod;
/*      */ 
/*      */     }
/* 1314 */     else if (amod >= bmod) {
/* 1315 */       ratio = bmod / amod;
/* 1316 */       cc = amod * Math.sqrt(1.0D + ratio * ratio);
/*      */     }
/*      */     else {
/* 1319 */       ratio = amod / bmod;
/* 1320 */       cc = bmod * Math.sqrt(1.0D + ratio * ratio);
/*      */     }
/*      */     
/*      */ 
/* 1324 */     return cc;
/*      */   }
/*      */   
/*      */   public Complex exp()
/*      */   {
/* 1329 */     return exp(this);
/*      */   }
/*      */   
/*      */   public static Complex exp(Complex aa)
/*      */   {
/* 1334 */     Complex z = new Complex();
/*      */     
/* 1336 */     double a = aa.real;
/* 1337 */     double b = aa.imag;
/*      */     
/* 1339 */     if (b == 0.0D) {
/* 1340 */       z.real = Math.exp(a);
/* 1341 */       z.imag = 0.0D;
/*      */ 
/*      */     }
/* 1344 */     else if (a == 0.0D) {
/* 1345 */       z.real = Math.cos(b);
/* 1346 */       z.imag = Math.sin(b);
/*      */     }
/*      */     else {
/* 1349 */       double c = Math.exp(a);
/* 1350 */       z.real = (c * Math.cos(b));
/* 1351 */       z.imag = (c * Math.sin(b));
/*      */     }
/*      */     
/* 1354 */     return z;
/*      */   }
/*      */   
/*      */   public static Complex exp(double aa)
/*      */   {
/* 1359 */     Complex bb = new Complex(aa, 0.0D);
/* 1360 */     return exp(bb);
/*      */   }
/*      */   
/*      */   public static Complex expPlusJayArg(double arg)
/*      */   {
/* 1365 */     Complex argc = new Complex(0.0D, arg);
/* 1366 */     return exp(argc);
/*      */   }
/*      */   
/*      */   public static Complex expMinusJayArg(double arg)
/*      */   {
/* 1371 */     Complex argc = new Complex(0.0D, -arg);
/* 1372 */     return exp(argc);
/*      */   }
/*      */   
/*      */ 
/*      */   public Complex log()
/*      */   {
/* 1378 */     double a = this.real;
/* 1379 */     double b = this.imag;
/* 1380 */     Complex c = new Complex();
/*      */     
/* 1382 */     c.real = Math.log(abs(this));
/* 1383 */     c.imag = Math.atan2(b, a);
/*      */     
/* 1385 */     return c;
/*      */   }
/*      */   
/*      */ 
/*      */   public static Complex log(Complex aa)
/*      */   {
/* 1391 */     double a = aa.real;
/* 1392 */     double b = aa.imag;
/* 1393 */     Complex c = new Complex();
/*      */     
/* 1395 */     c.real = Math.log(abs(aa));
/* 1396 */     c.imag = Math.atan2(b, a);
/*      */     
/* 1398 */     return c;
/*      */   }
/*      */   
/*      */ 
/*      */   public Complex sqrt()
/*      */   {
/* 1404 */     return sqrt(this);
/*      */   }
/*      */   
/*      */ 
/*      */   public static Complex sqrt(Complex aa)
/*      */   {
/* 1410 */     double a = aa.real;
/* 1411 */     double b = aa.imag;
/* 1412 */     Complex c = new Complex();
/*      */     
/* 1414 */     if (b == 0.0D) {
/* 1415 */       if (a >= 0.0D) {
/* 1416 */         c.real = Math.sqrt(a);
/* 1417 */         c.imag = 0.0D;
/*      */       }
/*      */       else {
/* 1420 */         c.real = 0.0D;
/* 1421 */         c.imag = Math.sqrt(-a);
/*      */       }
/*      */     }
/*      */     else
/*      */     {
/* 1426 */       double amod = Math.abs(a);
/* 1427 */       double bmod = Math.abs(b);
/* 1428 */       double w; double w; if (amod >= bmod) {
/* 1429 */         double ratio = b / a;
/* 1430 */         w = Math.sqrt(amod) * Math.sqrt(0.5D * (1.0D + Math.sqrt(1.0D + ratio * ratio)));
/*      */       }
/*      */       else {
/* 1433 */         double ratio = a / b;
/* 1434 */         w = Math.sqrt(bmod) * Math.sqrt(0.5D * (Math.abs(ratio) + Math.sqrt(1.0D + ratio * ratio)));
/*      */       }
/* 1436 */       if (a >= 0.0D) {
/* 1437 */         c.real = w;
/* 1438 */         c.imag = (b / (2.0D * w));
/*      */ 
/*      */       }
/* 1441 */       else if (b >= 0.0D) {
/* 1442 */         c.imag = w;
/* 1443 */         c.real = (b / (2.0D * c.imag));
/*      */       }
/*      */       else {
/* 1446 */         c.imag = (-w);
/* 1447 */         c.real = (b / (2.0D * c.imag));
/*      */       }
/*      */     }
/*      */     
/* 1451 */     return c;
/*      */   }
/*      */   
/*      */   public Complex nthRoot(int n)
/*      */   {
/* 1456 */     return nthRoot(this, n);
/*      */   }
/*      */   
/*      */ 
/*      */   public static Complex nthRoot(Complex aa, int n)
/*      */   {
/* 1462 */     Complex c = new Complex();
/* 1463 */     if (n == 0) {
/* 1464 */       c = new Complex(Double.POSITIVE_INFINITY, 0.0D);
/*      */ 
/*      */     }
/* 1467 */     else if (n == 1) {
/* 1468 */       c = aa;
/*      */     }
/*      */     else {
/* 1471 */       c = exp(log(aa).over(n));
/*      */     }
/*      */     
/*      */ 
/* 1475 */     return c;
/*      */   }
/*      */   
/*      */ 
/*      */   public static Complex square(Complex aa)
/*      */   {
/* 1481 */     Complex c = new Complex();
/* 1482 */     c.real = (aa.real * aa.real - aa.imag * aa.imag);
/* 1483 */     c.imag = (2.0D * aa.real * aa.imag);
/* 1484 */     return c;
/*      */   }
/*      */   
/*      */   public Complex square()
/*      */   {
/* 1489 */     return times(this);
/*      */   }
/*      */   
/*      */   public Complex pow(Complex b)
/*      */   {
/* 1494 */     Complex c = new Complex();
/* 1495 */     if (isZero()) {
/* 1496 */       if (b.imag == 0.0D) {
/* 1497 */         if (b.real == 0.0D) {
/* 1498 */           c = new Complex(1.0D, 0.0D);
/*      */ 
/*      */         }
/* 1501 */         else if (b.real > 0.0D) {
/* 1502 */           c = new Complex(0.0D, 0.0D);
/*      */ 
/*      */         }
/* 1505 */         else if (b.real < 0.0D) {
/* 1506 */           c = new Complex(Double.POSITIVE_INFINITY, 0.0D);
/*      */         }
/*      */         
/*      */ 
/*      */       }
/*      */       else {
/* 1512 */         c = exp(b.times(log(this)));
/*      */       }
/*      */     }
/*      */     else {
/* 1516 */       c = exp(b.times(log(this)));
/*      */     }
/*      */     
/* 1519 */     return c;
/*      */   }
/*      */   
/*      */   public static Complex pow(Complex a, Complex b)
/*      */   {
/* 1524 */     Complex c = new Complex();
/* 1525 */     if (a.isZero()) {
/* 1526 */       if (b.imag == 0.0D) {
/* 1527 */         if (b.real == 0.0D) {
/* 1528 */           c = new Complex(1.0D, 0.0D);
/*      */ 
/*      */         }
/* 1531 */         else if (a.real > 0.0D) {
/* 1532 */           c = new Complex(0.0D, 0.0D);
/*      */ 
/*      */         }
/* 1535 */         else if (a.real < 0.0D) {
/* 1536 */           c = new Complex(Double.POSITIVE_INFINITY, 0.0D);
/*      */         }
/*      */         
/*      */ 
/*      */       }
/*      */       else {
/* 1542 */         c = exp(b.times(log(a)));
/*      */       }
/*      */     }
/*      */     else {
/* 1546 */       c = exp(b.times(log(a)));
/*      */     }
/*      */     
/* 1549 */     return c;
/*      */   }
/*      */   
/*      */   public Complex pow(double b)
/*      */   {
/* 1554 */     return powDouble(this, b);
/*      */   }
/*      */   
/*      */   public static Complex pow(Complex a, double b)
/*      */   {
/* 1559 */     return powDouble(a, b);
/*      */   }
/*      */   
/*      */   public Complex pow(int n)
/*      */   {
/* 1564 */     double b = n;
/* 1565 */     return powDouble(this, b);
/*      */   }
/*      */   
/*      */   public static Complex pow(Complex a, int n)
/*      */   {
/* 1570 */     double b = n;
/* 1571 */     return powDouble(a, b);
/*      */   }
/*      */   
/*      */   public static Complex pow(double a, Complex b)
/*      */   {
/* 1576 */     Complex c = new Complex();
/* 1577 */     if (a == 0.0D) {
/* 1578 */       if (b.imag == 0.0D) {
/* 1579 */         if (b.real == 0.0D) {
/* 1580 */           c = new Complex(1.0D, 0.0D);
/*      */ 
/*      */         }
/* 1583 */         else if (b.real > 0.0D) {
/* 1584 */           c = new Complex(0.0D, 0.0D);
/*      */ 
/*      */         }
/* 1587 */         else if (b.real < 0.0D) {
/* 1588 */           c = new Complex(Double.POSITIVE_INFINITY, 0.0D);
/*      */         }
/*      */         
/*      */       }
/*      */       else
/*      */       {
/* 1594 */         double z = Math.pow(a, b.real);
/* 1595 */         c = exp(times(plusJay(), b.imag * Math.log(a)));
/* 1596 */         c = times(z, c);
/*      */       }
/*      */     }
/*      */     else {
/* 1600 */       double z = Math.pow(a, b.real);
/* 1601 */       c = exp(times(plusJay(), b.imag * Math.log(a)));
/* 1602 */       c = times(z, c);
/*      */     }
/*      */     
/* 1605 */     return c;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */   public Complex sin()
/*      */   {
/* 1613 */     return sin(this);
/*      */   }
/*      */   
/*      */   public static Complex sin(Complex aa) {
/* 1617 */     Complex c = new Complex();
/* 1618 */     double a = aa.real;
/* 1619 */     double b = aa.imag;
/* 1620 */     c.real = (Math.sin(a) * Fmath.cosh(b));
/* 1621 */     c.imag = (Math.cos(a) * Fmath.sinh(b));
/* 1622 */     return c;
/*      */   }
/*      */   
/*      */   public Complex cos()
/*      */   {
/* 1627 */     return cos(this);
/*      */   }
/*      */   
/*      */   public static Complex cos(Complex aa) {
/* 1631 */     Complex c = new Complex();
/* 1632 */     double a = aa.real;
/* 1633 */     double b = aa.imag;
/* 1634 */     c.real = (Math.cos(a) * Fmath.cosh(b));
/* 1635 */     c.imag = (-Math.sin(a) * Fmath.sinh(b));
/* 1636 */     return c;
/*      */   }
/*      */   
/*      */   public Complex sec()
/*      */   {
/* 1641 */     return sec(this);
/*      */   }
/*      */   
/*      */   public static Complex sec(Complex aa) {
/* 1645 */     Complex c = new Complex();
/* 1646 */     double a = aa.real;
/* 1647 */     double b = aa.imag;
/* 1648 */     c.real = (Math.cos(a) * Fmath.cosh(b));
/* 1649 */     c.imag = (-Math.sin(a) * Fmath.sinh(b));
/* 1650 */     return c.inverse();
/*      */   }
/*      */   
/*      */   public Complex csc()
/*      */   {
/* 1655 */     return csc(this);
/*      */   }
/*      */   
/*      */   public static Complex csc(Complex aa) {
/* 1659 */     Complex c = new Complex();
/* 1660 */     double a = aa.real;
/* 1661 */     double b = aa.imag;
/* 1662 */     c.real = (Math.sin(a) * Fmath.cosh(b));
/* 1663 */     c.imag = (Math.cos(a) * Fmath.sinh(b));
/* 1664 */     return c.inverse();
/*      */   }
/*      */   
/*      */   public Complex tan()
/*      */   {
/* 1669 */     return tan(this);
/*      */   }
/*      */   
/*      */   public static Complex tan(Complex aa) {
/* 1673 */     Complex c = new Complex();
/* 1674 */     double denom = 0.0D;
/* 1675 */     double a = aa.real;
/* 1676 */     double b = aa.imag;
/*      */     
/* 1678 */     Complex x = new Complex(Math.sin(a) * Fmath.cosh(b), Math.cos(a) * Fmath.sinh(b));
/* 1679 */     Complex y = new Complex(Math.cos(a) * Fmath.cosh(b), -Math.sin(a) * Fmath.sinh(b));
/* 1680 */     c = over(x, y);
/* 1681 */     return c;
/*      */   }
/*      */   
/*      */   public Complex cot()
/*      */   {
/* 1686 */     return cot(this);
/*      */   }
/*      */   
/*      */   public static Complex cot(Complex aa) {
/* 1690 */     Complex c = new Complex();
/* 1691 */     double denom = 0.0D;
/* 1692 */     double a = aa.real;
/* 1693 */     double b = aa.imag;
/*      */     
/* 1695 */     Complex x = new Complex(Math.sin(a) * Fmath.cosh(b), Math.cos(a) * Fmath.sinh(b));
/* 1696 */     Complex y = new Complex(Math.cos(a) * Fmath.cosh(b), -Math.sin(a) * Fmath.sinh(b));
/* 1697 */     c = over(y, x);
/* 1698 */     return c;
/*      */   }
/*      */   
/*      */   public Complex exsec()
/*      */   {
/* 1703 */     return exsec(this);
/*      */   }
/*      */   
/*      */   public static Complex exsec(Complex aa) {
/* 1707 */     return sec(aa).minus(1.0D);
/*      */   }
/*      */   
/*      */   public Complex vers()
/*      */   {
/* 1712 */     return vers(this);
/*      */   }
/*      */   
/*      */   public static Complex vers(Complex aa) {
/* 1716 */     return plusOne().minus(cos(aa));
/*      */   }
/*      */   
/*      */   public Complex covers()
/*      */   {
/* 1721 */     return covers(this);
/*      */   }
/*      */   
/*      */   public static Complex covers(Complex aa) {
/* 1725 */     return plusOne().minus(sin(aa));
/*      */   }
/*      */   
/*      */   public Complex hav()
/*      */   {
/* 1730 */     return hav(this);
/*      */   }
/*      */   
/*      */   public static Complex hav(Complex aa) {
/* 1734 */     return vers(aa).over(2.0D);
/*      */   }
/*      */   
/*      */   public Complex sinh()
/*      */   {
/* 1739 */     return sinh(this);
/*      */   }
/*      */   
/*      */   public static Complex sinh(Complex a) {
/* 1743 */     Complex c = new Complex();
/* 1744 */     c = a.times(plusJay());
/* 1745 */     c = minusJay().times(sin(c));
/* 1746 */     return c;
/*      */   }
/*      */   
/*      */   public Complex cosh()
/*      */   {
/* 1751 */     return cosh(this);
/*      */   }
/*      */   
/*      */   public static Complex cosh(Complex a) {
/* 1755 */     Complex c = new Complex();
/* 1756 */     c = a.times(plusJay());
/* 1757 */     c = cos(c);
/* 1758 */     return c;
/*      */   }
/*      */   
/*      */   public Complex tanh()
/*      */   {
/* 1763 */     return tanh(this);
/*      */   }
/*      */   
/*      */   public static Complex tanh(Complex a) {
/* 1767 */     Complex c = new Complex();
/* 1768 */     c = sinh(a).over(cosh(a));
/* 1769 */     return c;
/*      */   }
/*      */   
/*      */   public Complex coth()
/*      */   {
/* 1774 */     return coth(this);
/*      */   }
/*      */   
/*      */   public static Complex coth(Complex a) {
/* 1778 */     Complex c = new Complex();
/* 1779 */     c = cosh(a).over(sinh(a));
/* 1780 */     return c;
/*      */   }
/*      */   
/*      */   public Complex sech()
/*      */   {
/* 1785 */     return sech(this);
/*      */   }
/*      */   
/*      */   public static Complex sech(Complex a) {
/* 1789 */     Complex c = new Complex();
/* 1790 */     c = cosh(a).inverse();
/* 1791 */     return c;
/*      */   }
/*      */   
/*      */   public Complex csch()
/*      */   {
/* 1796 */     return csch(this);
/*      */   }
/*      */   
/*      */   public static Complex csch(Complex a) {
/* 1800 */     Complex c = new Complex();
/* 1801 */     c = sinh(a).inverse();
/* 1802 */     return c;
/*      */   }
/*      */   
/*      */ 
/*      */   public Complex asin()
/*      */   {
/* 1808 */     return asin(this);
/*      */   }
/*      */   
/*      */   public static Complex asin(Complex a) {
/* 1812 */     Complex c = new Complex();
/* 1813 */     c = sqrt(minus(1.0D, square(a)));
/* 1814 */     c = plusJay().times(a).plus(c);
/* 1815 */     c = minusJay().times(log(c));
/* 1816 */     return c;
/*      */   }
/*      */   
/*      */   public Complex acos()
/*      */   {
/* 1821 */     return acos(this);
/*      */   }
/*      */   
/*      */   public static Complex acos(Complex a) {
/* 1825 */     Complex c = new Complex();
/* 1826 */     c = sqrt(minus(square(a), 1.0D));
/* 1827 */     c = a.plus(c);
/* 1828 */     c = minusJay().times(log(c));
/* 1829 */     return c;
/*      */   }
/*      */   
/*      */   public Complex atan()
/*      */   {
/* 1834 */     return atan(this);
/*      */   }
/*      */   
/*      */   public static Complex atan(Complex a) {
/* 1838 */     Complex c = new Complex();
/* 1839 */     Complex d = new Complex();
/*      */     
/* 1841 */     c = plusJay().plus(a);
/* 1842 */     d = plusJay().minus(a);
/* 1843 */     c = c.over(d);
/* 1844 */     c = log(c);
/* 1845 */     c = plusJay().times(c);
/* 1846 */     c = c.over(2.0D);
/* 1847 */     return c;
/*      */   }
/*      */   
/*      */   public Complex acot()
/*      */   {
/* 1852 */     return acot(this);
/*      */   }
/*      */   
/*      */   public static Complex acot(Complex a) {
/* 1856 */     return atan(a.inverse());
/*      */   }
/*      */   
/*      */   public Complex asec()
/*      */   {
/* 1861 */     return asec(this);
/*      */   }
/*      */   
/*      */   public static Complex asec(Complex a) {
/* 1865 */     return acos(a.inverse());
/*      */   }
/*      */   
/*      */   public Complex acsc()
/*      */   {
/* 1870 */     return acsc(this);
/*      */   }
/*      */   
/*      */   public static Complex acsc(Complex a) {
/* 1874 */     return asin(a.inverse());
/*      */   }
/*      */   
/*      */   public Complex aexsec()
/*      */   {
/* 1879 */     return aexsec(this);
/*      */   }
/*      */   
/*      */   public static Complex aexsec(Complex a) {
/* 1883 */     Complex c = a.plus(1.0D);
/* 1884 */     return asin(c.inverse());
/*      */   }
/*      */   
/*      */   public Complex avers()
/*      */   {
/* 1889 */     return avers(this);
/*      */   }
/*      */   
/*      */   public static Complex avers(Complex a) {
/* 1893 */     Complex c = plusOne().plus(a);
/* 1894 */     return acos(c);
/*      */   }
/*      */   
/*      */   public Complex acovers()
/*      */   {
/* 1899 */     return acovers(this);
/*      */   }
/*      */   
/*      */   public static Complex acovers(Complex a) {
/* 1903 */     Complex c = plusOne().plus(a);
/* 1904 */     return asin(c);
/*      */   }
/*      */   
/*      */   public Complex ahav()
/*      */   {
/* 1909 */     return ahav(this);
/*      */   }
/*      */   
/*      */   public static Complex ahav(Complex a) {
/* 1913 */     Complex c = plusOne().minus(a.times(2.0D));
/* 1914 */     return acos(c);
/*      */   }
/*      */   
/*      */   public Complex asinh()
/*      */   {
/* 1919 */     return asinh(this);
/*      */   }
/*      */   
/*      */   public static Complex asinh(Complex a) {
/* 1923 */     Complex c = new Complex(0.0D, 0.0D);
/* 1924 */     c = sqrt(square(a).plus(1.0D));
/* 1925 */     c = a.plus(c);
/* 1926 */     c = log(c);
/*      */     
/* 1928 */     return c;
/*      */   }
/*      */   
/*      */   public Complex acosh()
/*      */   {
/* 1933 */     return acosh(this);
/*      */   }
/*      */   
/*      */   public static Complex acosh(Complex a) {
/* 1937 */     Complex c = new Complex();
/* 1938 */     c = sqrt(square(a).minus(1.0D));
/* 1939 */     c = a.plus(c);
/* 1940 */     c = log(c);
/* 1941 */     return c;
/*      */   }
/*      */   
/*      */   public Complex atanh()
/*      */   {
/* 1946 */     return atanh(this);
/*      */   }
/*      */   
/*      */   public static Complex atanh(Complex a) {
/* 1950 */     Complex c = new Complex();
/* 1951 */     Complex d = new Complex();
/* 1952 */     c = plusOne().plus(a);
/* 1953 */     d = plusOne().minus(a);
/* 1954 */     c = c.over(d);
/* 1955 */     c = log(c);
/* 1956 */     c = c.over(2.0D);
/* 1957 */     return c;
/*      */   }
/*      */   
/*      */   public Complex acoth()
/*      */   {
/* 1962 */     return acoth(this);
/*      */   }
/*      */   
/*      */   public static Complex acoth(Complex a) {
/* 1966 */     Complex c = new Complex();
/* 1967 */     Complex d = new Complex();
/* 1968 */     c = plusOne().plus(a);
/* 1969 */     d = a.plus(1.0D);
/* 1970 */     c = c.over(d);
/* 1971 */     c = log(c);
/* 1972 */     c = c.over(2.0D);
/* 1973 */     return c;
/*      */   }
/*      */   
/*      */   public Complex asech()
/*      */   {
/* 1978 */     return asech(this);
/*      */   }
/*      */   
/*      */   public static Complex asech(Complex a) {
/* 1982 */     Complex c = a.inverse();
/* 1983 */     Complex d = square(a).minus(1.0D);
/* 1984 */     return log(c.plus(sqrt(d)));
/*      */   }
/*      */   
/*      */   public Complex acsch()
/*      */   {
/* 1989 */     return acsch(this);
/*      */   }
/*      */   
/*      */   public static Complex acsch(Complex a) {
/* 1993 */     Complex c = a.inverse();
/* 1994 */     Complex d = square(a).plus(1.0D);
/* 1995 */     return log(c.plus(sqrt(d)));
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public static boolean isReal(Complex a)
/*      */   {
/* 2004 */     boolean test = false;
/* 2005 */     if (a.imag == 0.0D) test = true;
/* 2006 */     return test;
/*      */   }
/*      */   
/*      */   public boolean isReal() {
/* 2010 */     boolean test = false;
/* 2011 */     if (Math.abs(this.imag) == 0.0D) test = true;
/* 2012 */     return test;
/*      */   }
/*      */   
/*      */ 
/*      */   public static boolean isZero(Complex a)
/*      */   {
/* 2018 */     boolean test = false;
/* 2019 */     if ((Math.abs(a.real) == 0.0D) && (Math.abs(a.imag) == 0.0D)) test = true;
/* 2020 */     return test;
/*      */   }
/*      */   
/*      */   public boolean isZero() {
/* 2024 */     boolean test = false;
/* 2025 */     if ((Math.abs(this.real) == 0.0D) && (Math.abs(this.imag) == 0.0D)) test = true;
/* 2026 */     return test;
/*      */   }
/*      */   
/*      */ 
/*      */   public boolean isPlusInfinity()
/*      */   {
/* 2032 */     boolean test = false;
/* 2033 */     if ((this.real == Double.POSITIVE_INFINITY) || (this.imag == Double.POSITIVE_INFINITY)) test = true;
/* 2034 */     return test;
/*      */   }
/*      */   
/*      */   public static boolean isPlusInfinity(Complex a) {
/* 2038 */     boolean test = false;
/* 2039 */     if ((a.real == Double.POSITIVE_INFINITY) || (a.imag == Double.POSITIVE_INFINITY)) test = true;
/* 2040 */     return test;
/*      */   }
/*      */   
/*      */ 
/*      */   public boolean isMinusInfinity()
/*      */   {
/* 2046 */     boolean test = false;
/* 2047 */     if ((this.real == Double.NEGATIVE_INFINITY) || (this.imag == Double.NEGATIVE_INFINITY)) test = true;
/* 2048 */     return test;
/*      */   }
/*      */   
/*      */   public static boolean isMinusInfinity(Complex a) {
/* 2052 */     boolean test = false;
/* 2053 */     if ((a.real == Double.NEGATIVE_INFINITY) || (a.imag == Double.NEGATIVE_INFINITY)) test = true;
/* 2054 */     return test;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */   public static boolean isInfinite(Complex a)
/*      */   {
/* 2061 */     boolean test = false;
/* 2062 */     if ((a.real == Double.POSITIVE_INFINITY) || (a.imag == Double.POSITIVE_INFINITY)) test = true;
/* 2063 */     if ((a.real == Double.NEGATIVE_INFINITY) || (a.imag == Double.NEGATIVE_INFINITY)) test = true;
/* 2064 */     return test;
/*      */   }
/*      */   
/*      */   public boolean isInfinite() {
/* 2068 */     boolean test = false;
/* 2069 */     if ((this.real == Double.POSITIVE_INFINITY) || (this.imag == Double.POSITIVE_INFINITY)) test = true;
/* 2070 */     if ((this.real == Double.NEGATIVE_INFINITY) || (this.imag == Double.NEGATIVE_INFINITY)) test = true;
/* 2071 */     return test;
/*      */   }
/*      */   
/*      */ 
/*      */   public static boolean isNaN(Complex a)
/*      */   {
/* 2077 */     boolean test = false;
/* 2078 */     if ((a.real != a.real) || (a.imag != a.imag)) test = true;
/* 2079 */     return test;
/*      */   }
/*      */   
/*      */   public boolean isNaN() {
/* 2083 */     boolean test = false;
/* 2084 */     if ((this.real != this.real) || (this.imag != this.imag)) test = true;
/* 2085 */     return test;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */   public boolean equals(Complex a)
/*      */   {
/* 2093 */     boolean test = false;
/* 2094 */     if ((isNaN()) && (a.isNaN())) {
/* 2095 */       test = true;
/*      */ 
/*      */     }
/* 2098 */     else if ((this.real == a.real) && (this.imag == a.imag)) { test = true;
/*      */     }
/* 2100 */     return test;
/*      */   }
/*      */   
/*      */   public boolean isEqual(Complex a) {
/* 2104 */     boolean test = false;
/* 2105 */     if ((isNaN()) && (a.isNaN())) {
/* 2106 */       test = true;
/*      */ 
/*      */     }
/* 2109 */     else if ((this.real == a.real) && (this.imag == a.imag)) { test = true;
/*      */     }
/* 2111 */     return test;
/*      */   }
/*      */   
/*      */   public static boolean isEqual(Complex a, Complex b)
/*      */   {
/* 2116 */     boolean test = false;
/* 2117 */     if ((isNaN(a)) && (isNaN(b))) {
/* 2118 */       test = true;
/*      */ 
/*      */     }
/* 2121 */     else if ((a.real == b.real) && (a.imag == b.imag)) { test = true;
/*      */     }
/* 2123 */     return test;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */   public boolean equalsWithinLimits(Complex a, double fract)
/*      */   {
/* 2131 */     return isEqualWithinLimits(a, fract);
/*      */   }
/*      */   
/*      */   public boolean isEqualWithinLimits(Complex a, double fract) {
/* 2135 */     boolean test = false;
/*      */     
/* 2137 */     double rt = getReal();
/* 2138 */     double ra = a.getReal();
/* 2139 */     double it = getImag();
/* 2140 */     double ia = a.getImag();
/* 2141 */     double rdn = 0.0D;
/* 2142 */     double idn = 0.0D;
/* 2143 */     double rtest = 0.0D;
/* 2144 */     double itest = 0.0D;
/*      */     
/* 2146 */     if ((rt == 0.0D) && (it == 0.0D) && (ra == 0.0D) && (ia == 0.0D)) test = true;
/* 2147 */     if (!test) {
/* 2148 */       rdn = Math.abs(rt);
/* 2149 */       if (Math.abs(ra) > rdn) rdn = Math.abs(ra);
/* 2150 */       if (rdn == 0.0D) {
/* 2151 */         rtest = 0.0D;
/*      */       }
/*      */       else {
/* 2154 */         rtest = Math.abs(ra - rt) / rdn;
/*      */       }
/* 2156 */       idn = Math.abs(it);
/* 2157 */       if (Math.abs(ia) > idn) idn = Math.abs(ia);
/* 2158 */       if (idn == 0.0D) {
/* 2159 */         itest = 0.0D;
/*      */       }
/*      */       else {
/* 2162 */         itest = Math.abs(ia - it) / idn;
/*      */       }
/* 2164 */       if ((rtest < fract) && (itest < fract)) { test = true;
/*      */       }
/*      */     }
/* 2167 */     return test;
/*      */   }
/*      */   
/*      */   public static boolean isEqualWithinLimits(Complex a, Complex b, double fract) {
/* 2171 */     boolean test = false;
/*      */     
/* 2173 */     double rb = b.getReal();
/* 2174 */     double ra = a.getReal();
/* 2175 */     double ib = b.getImag();
/* 2176 */     double ia = a.getImag();
/* 2177 */     double rdn = 0.0D;
/* 2178 */     double idn = 0.0D;
/*      */     
/* 2180 */     if ((ra == 0.0D) && (ia == 0.0D) && (rb == 0.0D) && (ib == 0.0D)) test = true;
/* 2181 */     if (!test) {
/* 2182 */       rdn = Math.abs(rb);
/* 2183 */       if (Math.abs(ra) > rdn) rdn = Math.abs(ra);
/* 2184 */       idn = Math.abs(ib);
/* 2185 */       if (Math.abs(ia) > idn) idn = Math.abs(ia);
/* 2186 */       if ((Math.abs(ra - rb) / rdn < fract) && (Math.abs(ia - ia) / idn < fract)) { test = true;
/*      */       }
/*      */     }
/* 2189 */     return test;
/*      */   }
/*      */   
/*      */ 
/*      */   public static Complex zero()
/*      */   {
/* 2195 */     Complex c = new Complex();
/* 2196 */     c.real = 0.0D;
/* 2197 */     c.imag = 0.0D;
/* 2198 */     return c;
/*      */   }
/*      */   
/*      */   public static Complex plusOne()
/*      */   {
/* 2203 */     Complex c = new Complex();
/* 2204 */     c.real = 1.0D;
/* 2205 */     c.imag = 0.0D;
/* 2206 */     return c;
/*      */   }
/*      */   
/*      */   public static Complex minusOne()
/*      */   {
/* 2211 */     Complex c = new Complex();
/* 2212 */     c.real = -1.0D;
/* 2213 */     c.imag = 0.0D;
/* 2214 */     return c;
/*      */   }
/*      */   
/*      */   public static Complex plusJay()
/*      */   {
/* 2219 */     Complex c = new Complex();
/* 2220 */     c.real = 0.0D;
/* 2221 */     c.imag = 1.0D;
/* 2222 */     return c;
/*      */   }
/*      */   
/*      */   public static Complex minusJay()
/*      */   {
/* 2227 */     Complex c = new Complex();
/* 2228 */     c.real = 0.0D;
/* 2229 */     c.imag = -1.0D;
/* 2230 */     return c;
/*      */   }
/*      */   
/*      */   public static Complex pi()
/*      */   {
/* 2235 */     Complex c = new Complex();
/* 2236 */     c.real = 3.141592653589793D;
/* 2237 */     c.imag = 0.0D;
/* 2238 */     return c;
/*      */   }
/*      */   
/*      */   public static Complex twoPiJay()
/*      */   {
/* 2243 */     Complex c = new Complex();
/* 2244 */     c.real = 0.0D;
/* 2245 */     c.imag = 6.283185307179586D;
/* 2246 */     return c;
/*      */   }
/*      */   
/*      */   public static Complex plusInfinity()
/*      */   {
/* 2251 */     Complex c = new Complex();
/* 2252 */     c.real = Double.POSITIVE_INFINITY;
/* 2253 */     c.imag = Double.POSITIVE_INFINITY;
/* 2254 */     return c;
/*      */   }
/*      */   
/*      */   public static Complex minusInfinity()
/*      */   {
/* 2259 */     Complex c = new Complex();
/* 2260 */     c.real = Double.NEGATIVE_INFINITY;
/* 2261 */     c.imag = Double.NEGATIVE_INFINITY;
/* 2262 */     return c;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */   private static Complex powDouble(Complex a, double b)
/*      */   {
/* 2270 */     Complex z = new Complex();
/* 2271 */     double re = a.real;
/* 2272 */     double im = a.imag;
/*      */     
/* 2274 */     if (a.isZero()) {
/* 2275 */       if (b == 0.0D) {
/* 2276 */         z = new Complex(1.0D, 0.0D);
/*      */ 
/*      */       }
/* 2279 */       else if (b > 0.0D) {
/* 2280 */         z = new Complex(0.0D, 0.0D);
/*      */ 
/*      */       }
/* 2283 */       else if (b < 0.0D) {
/* 2284 */         z = new Complex(Double.POSITIVE_INFINITY, 0.0D);
/*      */ 
/*      */       }
/*      */       
/*      */ 
/*      */     }
/* 2290 */     else if ((im == 0.0D) && (re > 0.0D)) {
/* 2291 */       z.real = Math.pow(re, b);
/* 2292 */       z.imag = 0.0D;
/*      */ 
/*      */     }
/* 2295 */     else if (re == 0.0D) {
/* 2296 */       z = exp(times(b, log(a)));
/*      */     }
/*      */     else {
/* 2299 */       double c = Math.pow(re * re + im * im, b / 2.0D);
/* 2300 */       double th = Math.atan2(im, re);
/* 2301 */       z.real = (c * Math.cos(b * th));
/* 2302 */       z.imag = (c * Math.sin(b * th));
/*      */     }
/*      */     
/*      */ 
/* 2306 */     return z;
/*      */   }
/*      */ }


/* Location:              /home/menick/Scrivania/ProcessMining/CNMining/CNMining.jar!/flanagan/complex/Complex.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       0.7.1
 */