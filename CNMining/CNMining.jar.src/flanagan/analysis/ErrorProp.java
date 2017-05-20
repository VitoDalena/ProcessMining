/*      */ package flanagan.analysis;
/*      */ 
/*      */ import flanagan.math.Fmath;
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
/*      */ public class ErrorProp
/*      */ {
/*   51 */   private double value = 0.0D;
/*   52 */   private double error = 0.0D;
/*      */   
/*      */ 
/*      */ 
/*      */   public ErrorProp()
/*      */   {
/*   58 */     this.value = 0.0D;
/*   59 */     this.error = 0.0D;
/*      */   }
/*      */   
/*      */   public ErrorProp(double value, double error)
/*      */   {
/*   64 */     this.value = value;
/*   65 */     this.error = error;
/*      */   }
/*      */   
/*      */   public ErrorProp(double value)
/*      */   {
/*   70 */     this.value = value;
/*   71 */     this.error = 0.0D;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */   public void setValue(double value)
/*      */   {
/*   79 */     this.value = value;
/*      */   }
/*      */   
/*      */   public void setError(double error)
/*      */   {
/*   84 */     this.error = error;
/*      */   }
/*      */   
/*      */   public void reset(double value, double error)
/*      */   {
/*   89 */     this.value = value;
/*   90 */     this.error = error;
/*      */   }
/*      */   
/*      */ 
/*      */   public double getValue()
/*      */   {
/*   96 */     return this.value;
/*      */   }
/*      */   
/*      */   public double getError()
/*      */   {
/*  101 */     return this.error;
/*      */   }
/*      */   
/*      */ 
/*      */   public void println(String message)
/*      */   {
/*  107 */     System.out.println(message + " " + toString());
/*      */   }
/*      */   
/*      */   public void println()
/*      */   {
/*  112 */     System.out.println(" " + toString());
/*      */   }
/*      */   
/*      */   public void print(String message)
/*      */   {
/*  117 */     System.out.print(message + " " + toString());
/*      */   }
/*      */   
/*      */   public void print()
/*      */   {
/*  122 */     System.out.print(" " + toString());
/*      */   }
/*      */   
/*      */ 
/*      */   public static ErrorProp truncate(ErrorProp x, int prec)
/*      */   {
/*  128 */     if (prec < 0) { return x;
/*      */     }
/*  130 */     double xV = x.getValue();
/*  131 */     double xS = x.getError();
/*  132 */     ErrorProp y = new ErrorProp();
/*      */     
/*  134 */     xV = Fmath.truncate(xV, prec);
/*  135 */     xS = Fmath.truncate(xS, prec);
/*      */     
/*  137 */     y.reset(xV, xS);
/*      */     
/*  139 */     return y;
/*      */   }
/*      */   
/*      */   public ErrorProp truncate(int prec)
/*      */   {
/*  144 */     if (prec < 0) { return this;
/*      */     }
/*  146 */     double xV = getValue();
/*  147 */     double xS = getError();
/*  148 */     ErrorProp y = new ErrorProp();
/*      */     
/*  150 */     xV = Fmath.truncate(xV, prec);
/*  151 */     xS = Fmath.truncate(xS, prec);
/*      */     
/*  153 */     y.reset(xV, xS);
/*      */     
/*  155 */     return y;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */   public String toString()
/*      */   {
/*  162 */     return this.value + ", error = " + this.error;
/*      */   }
/*      */   
/*      */ 
/*      */   public static String toString(ErrorProp aa)
/*      */   {
/*  168 */     return aa.value + ", error = " + aa.error;
/*      */   }
/*      */   
/*      */ 
/*      */   public int hashCode()
/*      */   {
/*  174 */     long lvalue = Double.doubleToLongBits(this.value);
/*  175 */     long lerror = Double.doubleToLongBits(this.error);
/*  176 */     int hvalue = (int)(lvalue ^ lvalue >>> 32);
/*  177 */     int herror = (int)(lerror ^ lerror >>> 32);
/*  178 */     return 7 * (hvalue / 10) + 3 * (herror / 10);
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public static ErrorProp[] oneDarray(int n)
/*      */   {
/*  187 */     ErrorProp[] a = new ErrorProp[n];
/*  188 */     for (int i = 0; i < n; i++) {
/*  189 */       a[i] = zero();
/*      */     }
/*  191 */     return a;
/*      */   }
/*      */   
/*      */ 
/*      */   public static ErrorProp[] oneDarray(int n, double a, double b)
/*      */   {
/*  197 */     ErrorProp[] c = new ErrorProp[n];
/*  198 */     for (int i = 0; i < n; i++) {
/*  199 */       c[i] = zero();
/*  200 */       c[i].reset(a, b);
/*      */     }
/*  202 */     return c;
/*      */   }
/*      */   
/*      */ 
/*      */   public static ErrorProp[] oneDarray(int n, ErrorProp constant)
/*      */   {
/*  208 */     ErrorProp[] c = new ErrorProp[n];
/*  209 */     for (int i = 0; i < n; i++) {
/*  210 */       c[i] = copy(constant);
/*      */     }
/*  212 */     return c;
/*      */   }
/*      */   
/*      */ 
/*      */   public static ErrorProp[][] twoDarray(int n, int m)
/*      */   {
/*  218 */     ErrorProp[][] a = new ErrorProp[n][m];
/*  219 */     for (int i = 0; i < n; i++) {
/*  220 */       for (int j = 0; j < m; j++) {
/*  221 */         a[i][j] = zero();
/*      */       }
/*      */     }
/*  224 */     return a;
/*      */   }
/*      */   
/*      */ 
/*      */   public static ErrorProp[][] twoDarray(int n, int m, double a, double b)
/*      */   {
/*  230 */     ErrorProp[][] c = new ErrorProp[n][m];
/*  231 */     for (int i = 0; i < n; i++) {
/*  232 */       for (int j = 0; j < m; j++) {
/*  233 */         c[i][j] = zero();
/*  234 */         c[i][j].reset(a, b);
/*      */       }
/*      */     }
/*  237 */     return c;
/*      */   }
/*      */   
/*      */ 
/*      */   public static ErrorProp[][] twoDarray(int n, int m, ErrorProp constant)
/*      */   {
/*  243 */     ErrorProp[][] c = new ErrorProp[n][m];
/*  244 */     for (int i = 0; i < n; i++) {
/*  245 */       for (int j = 0; j < m; j++) {
/*  246 */         c[i][j] = copy(constant);
/*      */       }
/*      */     }
/*  249 */     return c;
/*      */   }
/*      */   
/*      */ 
/*      */   public static ErrorProp copy(ErrorProp a)
/*      */   {
/*  255 */     ErrorProp b = new ErrorProp();
/*  256 */     b.value = a.value;
/*  257 */     b.error = a.error;
/*  258 */     return b;
/*      */   }
/*      */   
/*      */   public ErrorProp copy()
/*      */   {
/*  263 */     ErrorProp b = new ErrorProp();
/*  264 */     b.value = this.value;
/*  265 */     b.error = this.error;
/*  266 */     return b;
/*      */   }
/*      */   
/*      */   public Object clone()
/*      */   {
/*  271 */     ErrorProp b = new ErrorProp();
/*  272 */     b.value = this.value;
/*  273 */     b.error = this.error;
/*  274 */     return b;
/*      */   }
/*      */   
/*      */ 
/*      */   public static ErrorProp[] copy(ErrorProp[] a)
/*      */   {
/*  280 */     int n = a.length;
/*  281 */     ErrorProp[] b = oneDarray(n);
/*  282 */     for (int i = 0; i < n; i++) {
/*  283 */       b[i] = copy(a[i]);
/*      */     }
/*  285 */     return b;
/*      */   }
/*      */   
/*      */   public static ErrorProp[][] copy(ErrorProp[][] a)
/*      */   {
/*  290 */     int n = a.length;
/*  291 */     int m = a[0].length;
/*  292 */     ErrorProp[][] b = twoDarray(n, m);
/*  293 */     for (int i = 0; i < n; i++) {
/*  294 */       for (int j = 0; j < m; j++) {
/*  295 */         b[i][j] = copy(a[i][j]);
/*      */       }
/*      */     }
/*  298 */     return b;
/*      */   }
/*      */   
/*      */ 
/*      */   public ErrorProp plus(ErrorProp a, double corrCoeff)
/*      */   {
/*  304 */     ErrorProp c = new ErrorProp();
/*  305 */     a.value += this.value;
/*  306 */     c.error = hypotWithCov(a.error, this.error, corrCoeff);
/*  307 */     return c;
/*      */   }
/*      */   
/*      */   public static ErrorProp plus(ErrorProp a, ErrorProp b, double corrCoeff)
/*      */   {
/*  312 */     ErrorProp c = new ErrorProp();
/*  313 */     a.value += b.value;
/*  314 */     c.error = hypotWithCov(a.error, b.error, corrCoeff);
/*  315 */     return c;
/*      */   }
/*      */   
/*      */ 
/*      */   public ErrorProp plus(ErrorProp a)
/*      */   {
/*  321 */     ErrorProp b = new ErrorProp();
/*  322 */     this.value += a.value;
/*  323 */     b.error = hypotWithCov(a.error, this.error, 0.0D);
/*  324 */     return b;
/*      */   }
/*      */   
/*      */   public static ErrorProp plus(ErrorProp a, ErrorProp b)
/*      */   {
/*  329 */     ErrorProp c = new ErrorProp();
/*  330 */     a.value += b.value;
/*  331 */     c.error = hypotWithCov(a.error, b.error, 0.0D);
/*  332 */     return c;
/*      */   }
/*      */   
/*      */ 
/*      */   public ErrorProp plus(double a)
/*      */   {
/*  338 */     ErrorProp b = new ErrorProp();
/*  339 */     this.value += a;
/*  340 */     b.error = Math.abs(this.error);
/*  341 */     return b;
/*      */   }
/*      */   
/*      */   public static ErrorProp plus(double a, ErrorProp b)
/*      */   {
/*  346 */     ErrorProp c = new ErrorProp();
/*  347 */     c.value = (a + b.value);
/*  348 */     c.error = Math.abs(b.error);
/*  349 */     return c;
/*      */   }
/*      */   
/*      */   public static ErrorProp plus(double a, double b)
/*      */   {
/*  354 */     ErrorProp c = new ErrorProp();
/*  355 */     c.value = (a + b);
/*  356 */     c.error = 0.0D;
/*  357 */     return c;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */   public void plusEquals(ErrorProp a, double corrCoeff)
/*      */   {
/*  364 */     this.value += a.value;
/*  365 */     this.error = hypotWithCov(a.error, this.error, corrCoeff);
/*      */   }
/*      */   
/*      */ 
/*      */   public void plusEquals(ErrorProp a)
/*      */   {
/*  371 */     this.value += a.value;
/*  372 */     this.error = Math.sqrt(a.error * a.error + this.error * this.error);
/*  373 */     this.error = hypotWithCov(a.error, this.error, 0.0D);
/*      */   }
/*      */   
/*      */   public void plusEquals(double a)
/*      */   {
/*  378 */     this.value += a;
/*  379 */     this.error = Math.abs(this.error);
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */   public ErrorProp minus(ErrorProp a, double corrCoeff)
/*      */   {
/*  386 */     ErrorProp c = new ErrorProp();
/*  387 */     this.value -= a.value;
/*  388 */     c.error = hypotWithCov(this.error, a.error, -corrCoeff);
/*  389 */     return c;
/*      */   }
/*      */   
/*      */   public static ErrorProp minus(ErrorProp a, ErrorProp b, double corrCoeff)
/*      */   {
/*  394 */     ErrorProp c = new ErrorProp();
/*  395 */     a.value -= b.value;
/*  396 */     c.error = hypotWithCov(a.error, b.error, -corrCoeff);
/*  397 */     return c;
/*      */   }
/*      */   
/*      */ 
/*      */   public ErrorProp minus(ErrorProp a)
/*      */   {
/*  403 */     ErrorProp b = new ErrorProp();
/*  404 */     this.value -= a.value;
/*  405 */     b.error = hypotWithCov(a.error, this.error, 0.0D);
/*  406 */     return b;
/*      */   }
/*      */   
/*      */   public static ErrorProp minus(ErrorProp a, ErrorProp b)
/*      */   {
/*  411 */     ErrorProp c = new ErrorProp();
/*  412 */     a.value -= b.value;
/*  413 */     c.error = hypotWithCov(a.error, b.error, 0.0D);
/*  414 */     return c;
/*      */   }
/*      */   
/*      */ 
/*      */   public ErrorProp minus(double a)
/*      */   {
/*  420 */     ErrorProp b = new ErrorProp();
/*  421 */     this.value -= a;
/*  422 */     b.error = Math.abs(this.error);
/*  423 */     return b;
/*      */   }
/*      */   
/*      */   public static ErrorProp minus(double a, ErrorProp b)
/*      */   {
/*  428 */     ErrorProp c = new ErrorProp();
/*  429 */     c.value = (a - b.value);
/*  430 */     c.error = Math.abs(b.error);
/*  431 */     return c;
/*      */   }
/*      */   
/*      */   public static ErrorProp minus(double a, double b)
/*      */   {
/*  436 */     ErrorProp c = new ErrorProp();
/*  437 */     c.value = (a - b);
/*  438 */     c.error = 0.0D;
/*  439 */     return c;
/*      */   }
/*      */   
/*      */ 
/*      */   public void minusEquals(ErrorProp a, double corrCoeff)
/*      */   {
/*  445 */     this.value -= a.value;
/*  446 */     this.error = hypotWithCov(a.error, this.error, -corrCoeff);
/*      */   }
/*      */   
/*      */ 
/*      */   public void minusEquals(ErrorProp a)
/*      */   {
/*  452 */     this.value -= a.value;
/*  453 */     this.error = hypotWithCov(a.error, this.error, 0.0D);
/*      */   }
/*      */   
/*      */   public void minusEquals(double a)
/*      */   {
/*  458 */     this.value -= a;
/*  459 */     this.error = Math.abs(this.error);
/*      */   }
/*      */   
/*      */ 
/*      */   public ErrorProp times(ErrorProp a, double corrCoeff)
/*      */   {
/*  465 */     ErrorProp c = new ErrorProp();
/*  466 */     double cov = corrCoeff * a.error * this.error;
/*  467 */     a.value *= this.value;
/*  468 */     if (a.value == 0.0D) {
/*  469 */       a.error *= this.value;
/*      */ 
/*      */     }
/*  472 */     else if (this.value == 0.0D) {
/*  473 */       this.error *= a.value;
/*      */     }
/*      */     else {
/*  476 */       c.error = (Math.abs(c.value) * hypotWithCov(a.error / a.value, this.error / this.value, corrCoeff));
/*      */     }
/*      */     
/*  479 */     return c;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */   public ErrorProp times(ErrorProp a)
/*      */   {
/*  486 */     ErrorProp b = new ErrorProp();
/*  487 */     this.value *= a.value;
/*  488 */     if (a.value == 0.0D) {
/*  489 */       a.error *= this.value;
/*      */ 
/*      */     }
/*  492 */     else if (this.value == 0.0D) {
/*  493 */       this.error *= a.value;
/*      */     }
/*      */     else {
/*  496 */       b.error = (Math.abs(b.value) * hypotWithCov(a.error / a.value, this.error / this.value, 0.0D));
/*      */     }
/*      */     
/*  499 */     return b;
/*      */   }
/*      */   
/*      */ 
/*      */   public ErrorProp times(double a)
/*      */   {
/*  505 */     ErrorProp b = new ErrorProp();
/*  506 */     this.value *= a;
/*  507 */     b.error = Math.abs(this.error * a);
/*  508 */     return b;
/*      */   }
/*      */   
/*      */ 
/*      */   public static ErrorProp times(ErrorProp a, ErrorProp b, double corrCoeff)
/*      */   {
/*  514 */     ErrorProp c = new ErrorProp();
/*  515 */     double cov = corrCoeff * a.error * b.error;
/*  516 */     a.value *= b.value;
/*  517 */     if (a.value == 0.0D) {
/*  518 */       a.error *= b.value;
/*      */ 
/*      */     }
/*  521 */     else if (b.value == 0.0D) {
/*  522 */       b.error *= a.value;
/*      */     }
/*      */     else {
/*  525 */       c.error = (Math.abs(c.value) * hypotWithCov(a.error / a.value, b.error / b.value, corrCoeff));
/*      */     }
/*      */     
/*  528 */     return c;
/*      */   }
/*      */   
/*      */   public static ErrorProp times(ErrorProp a, ErrorProp b)
/*      */   {
/*  533 */     ErrorProp c = new ErrorProp();
/*  534 */     a.value *= b.value;
/*  535 */     if (a.value == 0.0D) {
/*  536 */       a.error *= b.value;
/*      */ 
/*      */     }
/*  539 */     else if (b.value == 0.0D) {
/*  540 */       b.error *= a.value;
/*      */     }
/*      */     else {
/*  543 */       c.error = (Math.abs(c.value) * hypotWithCov(a.error / a.value, b.error / b.value, 0.0D));
/*      */     }
/*      */     
/*  546 */     return c;
/*      */   }
/*      */   
/*      */   public static ErrorProp times(double a, ErrorProp b)
/*      */   {
/*  551 */     ErrorProp c = new ErrorProp();
/*  552 */     c.value = (a * b.value);
/*  553 */     c.error = Math.abs(a * b.error);
/*  554 */     return c;
/*      */   }
/*      */   
/*      */   public static ErrorProp times(double a, double b)
/*      */   {
/*  559 */     ErrorProp c = new ErrorProp();
/*  560 */     c.value = (a * b);
/*  561 */     c.error = 0.0D;
/*  562 */     return c;
/*      */   }
/*      */   
/*      */ 
/*      */   public void timesEquals(ErrorProp a, double corrCoeff)
/*      */   {
/*  568 */     ErrorProp b = new ErrorProp();
/*  569 */     double cov = corrCoeff * this.error * a.error;
/*  570 */     this.value *= a.value;
/*  571 */     if (a.value == 0.0D) {
/*  572 */       a.error *= this.value;
/*      */ 
/*      */     }
/*  575 */     else if (this.value == 0.0D) {
/*  576 */       this.error *= a.value;
/*      */     }
/*      */     else {
/*  579 */       b.error = (Math.abs(b.value) * hypotWithCov(a.error / a.value, this.error / this.value, corrCoeff));
/*      */     }
/*      */     
/*      */ 
/*  583 */     this.value = b.value;
/*  584 */     this.error = b.error;
/*      */   }
/*      */   
/*      */ 
/*      */   public void timesEquals(ErrorProp a)
/*      */   {
/*  590 */     ErrorProp b = new ErrorProp();
/*  591 */     this.value *= a.value;
/*  592 */     if (a.value == 0.0D) {
/*  593 */       a.error *= this.value;
/*      */ 
/*      */     }
/*  596 */     else if (this.value == 0.0D) {
/*  597 */       this.error *= a.value;
/*      */     }
/*      */     else {
/*  600 */       b.error = (Math.abs(b.value) * hypotWithCov(a.error / a.value, this.error / this.value, 0.0D));
/*      */     }
/*      */     
/*      */ 
/*  604 */     this.value = b.value;
/*  605 */     this.error = b.error;
/*      */   }
/*      */   
/*      */   public void timesEquals(double a)
/*      */   {
/*  610 */     this.value *= a;
/*  611 */     this.error = Math.abs(this.error * a);
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */   public ErrorProp over(ErrorProp a, double corrCoeff)
/*      */   {
/*  619 */     ErrorProp c = new ErrorProp();
/*  620 */     this.value /= a.value;
/*  621 */     if (this.value == 0.0D) {
/*  622 */       this.error *= a.value;
/*      */     }
/*      */     else {
/*  625 */       c.error = (Math.abs(c.value) * hypotWithCov(this.error / this.value, a.error / a.value, -corrCoeff));
/*      */     }
/*  627 */     return c;
/*      */   }
/*      */   
/*      */ 
/*      */   public static ErrorProp over(ErrorProp a, ErrorProp b, double corrCoeff)
/*      */   {
/*  633 */     ErrorProp c = new ErrorProp();
/*  634 */     a.value /= b.value;
/*  635 */     if (a.value == 0.0D) {
/*  636 */       a.error *= b.value;
/*      */     }
/*      */     else {
/*  639 */       c.error = (Math.abs(c.value) * hypotWithCov(a.error / a.value, b.error / b.value, -corrCoeff));
/*      */     }
/*  641 */     return c;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */   public ErrorProp over(ErrorProp a)
/*      */   {
/*  648 */     ErrorProp b = new ErrorProp();
/*  649 */     this.value /= a.value;
/*  650 */     b.error = (Math.abs(b.value) * hypotWithCov(a.error / a.value, this.error / this.value, 0.0D));
/*  651 */     if (this.value == 0.0D) {
/*  652 */       this.error *= b.value;
/*      */     }
/*      */     else {
/*  655 */       b.error = (Math.abs(b.value) * hypotWithCov(a.error / a.value, this.error / this.value, 0.0D));
/*      */     }
/*  657 */     return b;
/*      */   }
/*      */   
/*      */ 
/*      */   public static ErrorProp over(ErrorProp a, ErrorProp b)
/*      */   {
/*  663 */     ErrorProp c = new ErrorProp();
/*  664 */     a.value /= b.value;
/*  665 */     if (a.value == 0.0D) {
/*  666 */       a.error *= b.value;
/*      */     }
/*      */     else {
/*  669 */       c.error = (Math.abs(c.value) * hypotWithCov(a.error / a.value, b.error / b.value, 0.0D));
/*      */     }
/*      */     
/*  672 */     return c;
/*      */   }
/*      */   
/*      */ 
/*      */   public ErrorProp over(double a)
/*      */   {
/*  678 */     ErrorProp b = new ErrorProp();
/*  679 */     this.value /= a;
/*  680 */     b.error = Math.abs(this.error / a);
/*  681 */     return b;
/*      */   }
/*      */   
/*      */ 
/*      */   public static ErrorProp over(double a, ErrorProp b)
/*      */   {
/*  687 */     ErrorProp c = new ErrorProp();
/*  688 */     c.value = (a / b.value);
/*  689 */     c.error = Math.abs(a * b.error / (b.value * b.value));
/*  690 */     return c;
/*      */   }
/*      */   
/*      */   public static ErrorProp over(double a, double b)
/*      */   {
/*  695 */     ErrorProp c = new ErrorProp();
/*  696 */     c.value = (a / b);
/*  697 */     c.error = 0.0D;
/*  698 */     return c;
/*      */   }
/*      */   
/*      */ 
/*      */   public void overEquals(ErrorProp b)
/*      */   {
/*  704 */     ErrorProp c = new ErrorProp();
/*  705 */     this.value /= b.value;
/*  706 */     if (this.value == 0.0D) {
/*  707 */       this.error *= b.value;
/*      */     }
/*      */     else {
/*  710 */       c.error = (Math.abs(c.value) * hypotWithCov(this.error / this.value, b.error / b.value, 0.0D));
/*      */     }
/*  712 */     this.value = c.value;
/*  713 */     this.error = c.error;
/*      */   }
/*      */   
/*      */ 
/*      */   public void overEquals(ErrorProp b, double corrCoeff)
/*      */   {
/*  719 */     ErrorProp c = new ErrorProp();
/*  720 */     this.value /= b.value;
/*  721 */     if (this.value == 0.0D) {
/*  722 */       this.error *= b.value;
/*      */     }
/*      */     else {
/*  725 */       c.error = (Math.abs(c.value) * hypotWithCov(this.error / this.value, b.error / b.value, -corrCoeff));
/*      */     }
/*  727 */     this.value = c.value;
/*  728 */     this.error = c.error;
/*      */   }
/*      */   
/*      */   public void overEquals(double a)
/*      */   {
/*  733 */     this.value /= a;
/*  734 */     this.error = Math.abs(this.error / a);
/*      */   }
/*      */   
/*      */ 
/*      */   public ErrorProp inverse()
/*      */   {
/*  740 */     ErrorProp b = over(1.0D, this);
/*  741 */     return b;
/*      */   }
/*      */   
/*      */   public static ErrorProp inverse(ErrorProp a)
/*      */   {
/*  746 */     ErrorProp b = over(1.0D, a);
/*  747 */     return b;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public static ErrorProp hypot(ErrorProp a, ErrorProp b, double corrCoeff)
/*      */   {
/*  756 */     ErrorProp c = new ErrorProp();
/*  757 */     c.value = Fmath.hypot(a.value, b.value);
/*  758 */     c.error = Math.abs(hypotWithCov(a.error * a.value, b.error * b.value, corrCoeff) / c.value);
/*  759 */     return c;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */   public static ErrorProp hypot(ErrorProp a, ErrorProp b)
/*      */   {
/*  766 */     ErrorProp c = new ErrorProp();
/*  767 */     c.value = Fmath.hypot(a.value, b.value);
/*  768 */     c.error = Math.abs(hypotWithCov(a.error * a.value, b.error * b.value, 0.0D) / c.value);
/*  769 */     return c;
/*      */   }
/*      */   
/*      */   public static ErrorProp abs(ErrorProp a)
/*      */   {
/*  774 */     ErrorProp b = new ErrorProp();
/*  775 */     b.value = Math.abs(a.value);
/*  776 */     b.error = Math.abs(a.error);
/*  777 */     return b;
/*      */   }
/*      */   
/*      */   public ErrorProp abs()
/*      */   {
/*  782 */     ErrorProp b = new ErrorProp();
/*  783 */     b.value = Math.abs(this.value);
/*  784 */     b.error = Math.abs(this.error);
/*  785 */     return b;
/*      */   }
/*      */   
/*      */   public static ErrorProp exp(ErrorProp a)
/*      */   {
/*  790 */     ErrorProp b = new ErrorProp();
/*  791 */     b.value = Math.exp(a.value);
/*  792 */     b.error = Math.abs(b.value * a.error);
/*  793 */     return b;
/*      */   }
/*      */   
/*      */   public static ErrorProp log(ErrorProp a)
/*      */   {
/*  798 */     ErrorProp b = new ErrorProp();
/*  799 */     b.value = Math.log(a.value);
/*  800 */     b.error = Math.abs(a.error / a.value);
/*  801 */     return b;
/*      */   }
/*      */   
/*      */   public static ErrorProp log10(ErrorProp a)
/*      */   {
/*  806 */     ErrorProp b = new ErrorProp();
/*  807 */     b.value = Fmath.log10(a.value);
/*  808 */     b.error = Math.abs(a.error / (a.value * Math.log(10.0D)));
/*  809 */     return b;
/*      */   }
/*      */   
/*      */ 
/*      */   public static ErrorProp sqrt(ErrorProp a)
/*      */   {
/*  815 */     ErrorProp b = new ErrorProp();
/*  816 */     b.value = Math.sqrt(a.value);
/*  817 */     b.error = Math.abs(a.error / (2.0D * a.value));
/*  818 */     return b;
/*      */   }
/*      */   
/*      */   public static ErrorProp nthRoot(ErrorProp a, int n)
/*      */   {
/*  823 */     if (n == 0) throw new ArithmeticException("Division by zero (n = 0 - infinite root) attempted in ErrorProp.nthRoot");
/*  824 */     ErrorProp b = new ErrorProp();
/*  825 */     b.value = Math.pow(a.value, 1 / n);
/*  826 */     b.error = Math.abs(a.error * Math.pow(a.value, 1 / n - 1) / n);
/*  827 */     return b;
/*      */   }
/*      */   
/*      */ 
/*      */   public ErrorProp square()
/*      */   {
/*  833 */     ErrorProp a = new ErrorProp(this.value, this.error);
/*  834 */     return a.times(a, 1.0D);
/*      */   }
/*      */   
/*      */   public static ErrorProp square(ErrorProp a)
/*      */   {
/*  839 */     return a.times(a, 1.0D);
/*      */   }
/*      */   
/*      */   public static ErrorProp pow(ErrorProp a, double b)
/*      */   {
/*  844 */     ErrorProp c = new ErrorProp();
/*  845 */     c.value = Math.pow(a.value, b);
/*  846 */     c.error = Math.abs(b * Math.pow(a.value, b - 1.0D));
/*  847 */     return c;
/*      */   }
/*      */   
/*      */   public static ErrorProp pow(double a, ErrorProp b)
/*      */   {
/*  852 */     ErrorProp c = new ErrorProp();
/*  853 */     c.value = Math.pow(a, b.value);
/*  854 */     c.error = Math.abs(c.value * Math.log(a) * b.error);
/*  855 */     return c;
/*      */   }
/*      */   
/*      */ 
/*      */   public static ErrorProp pow(ErrorProp a, ErrorProp b, double corrCoeff)
/*      */   {
/*  861 */     ErrorProp c = new ErrorProp();
/*  862 */     c.value = Math.pow(a.value, b.value);
/*  863 */     c.error = hypotWithCov(a.error * b.value * Math.pow(a.value, b.value - 1.0D), b.error * Math.log(a.value) * Math.pow(a.value, b.value), corrCoeff);
/*  864 */     return c;
/*      */   }
/*      */   
/*      */ 
/*      */   public static ErrorProp pow(ErrorProp a, ErrorProp b)
/*      */   {
/*  870 */     ErrorProp c = new ErrorProp();
/*  871 */     c.value = Math.pow(a.value, b.value);
/*  872 */     c.error = hypotWithCov(a.error * b.value * Math.pow(a.value, b.value - 1.0D), b.error * Math.log(a.value) * Math.pow(a.value, b.value), 0.0D);
/*  873 */     return c;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */   public static ErrorProp sin(ErrorProp a)
/*      */   {
/*  880 */     ErrorProp b = new ErrorProp();
/*  881 */     b.value = Math.sin(a.value);
/*  882 */     b.error = Math.abs(a.error * Math.cos(a.value));
/*  883 */     return b;
/*      */   }
/*      */   
/*      */   public static ErrorProp cos(ErrorProp a)
/*      */   {
/*  888 */     ErrorProp b = new ErrorProp();
/*  889 */     b.value = Math.cos(a.value);
/*  890 */     b.error = Math.abs(a.error * Math.sin(a.value));
/*  891 */     return b;
/*      */   }
/*      */   
/*      */   public static ErrorProp tan(ErrorProp a)
/*      */   {
/*  896 */     ErrorProp b = new ErrorProp();
/*  897 */     b.value = Math.tan(a.value);
/*  898 */     b.error = Math.abs(a.error * Fmath.square(Fmath.sec(a.value)));
/*  899 */     return b;
/*      */   }
/*      */   
/*      */   public static ErrorProp sinh(ErrorProp a)
/*      */   {
/*  904 */     ErrorProp b = new ErrorProp();
/*  905 */     b.value = Fmath.sinh(a.value);
/*  906 */     b.error = Math.abs(a.error * Fmath.cosh(a.value));
/*  907 */     return b;
/*      */   }
/*      */   
/*      */   public static ErrorProp cosh(ErrorProp a)
/*      */   {
/*  912 */     ErrorProp b = new ErrorProp();
/*  913 */     b.value = Fmath.cosh(a.value);
/*  914 */     b.error = Math.abs(a.error * Fmath.sinh(a.value));
/*  915 */     return b;
/*      */   }
/*      */   
/*      */   public static ErrorProp tanh(ErrorProp a)
/*      */   {
/*  920 */     ErrorProp b = new ErrorProp();
/*  921 */     b.value = Fmath.tanh(a.value);
/*  922 */     b.error = Math.abs(a.error * Fmath.square(Fmath.sech(a.value)));
/*  923 */     return b;
/*      */   }
/*      */   
/*      */   public static ErrorProp asin(ErrorProp a)
/*      */   {
/*  928 */     ErrorProp b = new ErrorProp();
/*  929 */     b.value = Math.asin(a.value);
/*  930 */     b.error = Math.abs(a.error / Math.sqrt(1.0D - a.value * a.value));
/*  931 */     return b;
/*      */   }
/*      */   
/*      */   public static ErrorProp acos(ErrorProp a)
/*      */   {
/*  936 */     ErrorProp b = new ErrorProp();
/*  937 */     b.value = Math.acos(a.value);
/*  938 */     b.error = Math.abs(a.error / Math.sqrt(1.0D - a.value * a.value));
/*  939 */     return b;
/*      */   }
/*      */   
/*      */   public static ErrorProp atan(ErrorProp a)
/*      */   {
/*  944 */     ErrorProp b = new ErrorProp();
/*  945 */     b.value = Math.atan(a.value);
/*  946 */     b.error = Math.abs(a.error / (1.0D + a.value * a.value));
/*  947 */     return b;
/*      */   }
/*      */   
/*      */   public static ErrorProp atan2(ErrorProp a, ErrorProp b)
/*      */   {
/*  952 */     ErrorProp c = new ErrorProp();
/*  953 */     ErrorProp d = a.over(b);
/*  954 */     c.value = Math.atan2(a.value, b.value);
/*  955 */     c.error = Math.abs(d.error / (1.0D + d.value * d.value));
/*  956 */     return c;
/*      */   }
/*      */   
/*      */   public static ErrorProp atan2(ErrorProp a, ErrorProp b, double rho) {
/*  960 */     ErrorProp c = new ErrorProp();
/*  961 */     ErrorProp d = a.over(b, rho);
/*  962 */     c.value = Math.atan2(a.value, b.value);
/*  963 */     c.error = Math.abs(d.error / (1.0D + d.value * d.value));
/*  964 */     return c;
/*      */   }
/*      */   
/*      */   public static ErrorProp asinh(ErrorProp a)
/*      */   {
/*  969 */     ErrorProp b = new ErrorProp();
/*  970 */     b.value = Fmath.asinh(a.value);
/*  971 */     b.error = Math.abs(a.error / Math.sqrt(a.value * a.value + 1.0D));
/*  972 */     return b;
/*      */   }
/*      */   
/*      */   public static ErrorProp acosh(ErrorProp a)
/*      */   {
/*  977 */     ErrorProp b = new ErrorProp();
/*  978 */     b.value = Fmath.acosh(a.value);
/*  979 */     b.error = Math.abs(a.error / Math.sqrt(a.value * a.value - 1.0D));
/*  980 */     return b;
/*      */   }
/*      */   
/*      */   public static ErrorProp atanh(ErrorProp a)
/*      */   {
/*  985 */     ErrorProp b = new ErrorProp();
/*  986 */     b.value = Fmath.atanh(a.value);
/*  987 */     b.error = Math.abs(a.error / (1.0D - a.value * a.value));
/*  988 */     return b;
/*      */   }
/*      */   
/*      */ 
/*      */   public static ErrorProp zero()
/*      */   {
/*  994 */     ErrorProp c = new ErrorProp();
/*  995 */     c.value = 0.0D;
/*  996 */     c.error = 0.0D;
/*  997 */     return c;
/*      */   }
/*      */   
/*      */   public static ErrorProp plusOne()
/*      */   {
/* 1002 */     ErrorProp c = new ErrorProp();
/* 1003 */     c.value = 1.0D;
/* 1004 */     c.error = 0.0D;
/* 1005 */     return c;
/*      */   }
/*      */   
/*      */   public static ErrorProp minusOne()
/*      */   {
/* 1010 */     ErrorProp c = new ErrorProp();
/* 1011 */     c.value = -1.0D;
/* 1012 */     c.error = 0.0D;
/* 1013 */     return c;
/*      */   }
/*      */   
/*      */ 
/*      */   private static double hypotWithCov(double a, double b, double r)
/*      */   {
/* 1019 */     double pre = 0.0D;double ratio = 0.0D;double sgn = 0.0D;
/*      */     
/* 1021 */     if ((a == 0.0D) && (b == 0.0D)) return 0.0D;
/* 1022 */     if (Math.abs(a) > Math.abs(b)) {
/* 1023 */       pre = Math.abs(a);
/* 1024 */       ratio = b / a;
/* 1025 */       sgn = Fmath.sign(a);
/*      */     }
/*      */     else {
/* 1028 */       pre = Math.abs(b);
/* 1029 */       ratio = a / b;
/* 1030 */       sgn = Fmath.sign(b);
/*      */     }
/* 1032 */     return pre * Math.sqrt(1.0D + ratio * (ratio + 2.0D * r * sgn));
/*      */   }
/*      */ }


/* Location:              /home/menick/Scrivania/ProcessMining/CNMining/CNMining.jar!/flanagan/analysis/ErrorProp.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       0.7.1
 */