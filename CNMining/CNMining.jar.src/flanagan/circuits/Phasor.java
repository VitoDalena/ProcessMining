/*      */ package flanagan.circuits;
/*      */ 
/*      */ import flanagan.complex.Complex;
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
/*      */ 
/*      */ 
/*      */ public class Phasor
/*      */ {
/*   46 */   private double magnitude = 0.0D;
/*   47 */   private double phaseInDeg = 0.0D;
/*   48 */   private double phaseInRad = 0.0D;
/*   49 */   private Complex rectangular = new Complex(0.0D, 0.0D);
/*      */   
/*      */ 
/*   52 */   private static double frequency = NaN.0D;
/*   53 */   private static double omega = NaN.0D;
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */   public Phasor() {}
/*      */   
/*      */ 
/*      */ 
/*      */   public Phasor(double magnitude, double phase)
/*      */   {
/*   64 */     this.magnitude = magnitude;
/*   65 */     this.phaseInDeg = phase;
/*   66 */     this.phaseInRad = Math.toRadians(phase);
/*   67 */     this.rectangular.polar(this.magnitude, this.phaseInRad);
/*      */   }
/*      */   
/*      */   public Phasor(double magnitude)
/*      */   {
/*   72 */     this.magnitude = magnitude;
/*   73 */     this.rectangular.polar(this.magnitude, this.phaseInRad);
/*      */   }
/*      */   
/*      */ 
/*      */   public static void setFrequency(double freq)
/*      */   {
/*   79 */     if (Fmath.isNaN(frequency)) {
/*   80 */       frequency = freq;
/*   81 */       omega = 6.283185307179586D * freq;
/*      */     }
/*      */     else
/*      */     {
/*   85 */       throw new IllegalArgumentException("You have already entered a value for the frequency, " + frequency + ", that differs from the one you are now attempting to enter, " + freq);
/*      */     }
/*      */   }
/*      */   
/*      */   public static void setRadialFrequency(double omega)
/*      */   {
/*   91 */     if (Fmath.isNaN(omega)) {
/*   92 */       omega = omega;
/*   93 */       frequency = omega / 6.283185307179586D;
/*      */     }
/*      */     else {
/*   96 */       throw new IllegalArgumentException("You have already entered a value for the radial frequency, omega, " + omega + ", that differs from the one you are now attempting to enter, " + omega);
/*      */     }
/*      */   }
/*      */   
/*      */   public void setMagnitude(double magnitude)
/*      */   {
/*  102 */     this.magnitude = magnitude;
/*  103 */     this.rectangular.polar(this.magnitude, this.phaseInRad);
/*      */   }
/*      */   
/*      */   public void setPhaseInDegrees(double phase)
/*      */   {
/*  108 */     this.phaseInDeg = phase;
/*  109 */     this.phaseInRad = Math.toRadians(phase);
/*  110 */     this.rectangular.polar(this.magnitude, this.phaseInRad);
/*      */   }
/*      */   
/*      */ 
/*      */   public void reset(double magnitude, double phaseInDegrees)
/*      */   {
/*  116 */     this.magnitude = magnitude;
/*  117 */     this.phaseInDeg = phaseInDegrees;
/*  118 */     this.phaseInRad = Math.toRadians(phaseInDegrees);
/*  119 */     this.rectangular.polar(this.magnitude, this.phaseInRad);
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */   public static double getFrequency()
/*      */   {
/*  126 */     return frequency;
/*      */   }
/*      */   
/*      */   public static double setRadialFrequency()
/*      */   {
/*  131 */     return omega;
/*      */   }
/*      */   
/*      */   public double getMagnitude()
/*      */   {
/*  136 */     return this.magnitude;
/*      */   }
/*      */   
/*      */   public double getPhaseInDegrees()
/*      */   {
/*  141 */     return this.phaseInDeg;
/*      */   }
/*      */   
/*      */   public double getPhaseInRadians()
/*      */   {
/*  146 */     return this.phaseInRad;
/*      */   }
/*      */   
/*      */   public double getReal()
/*      */   {
/*  151 */     return this.magnitude * Math.cos(this.phaseInRad);
/*      */   }
/*      */   
/*      */   public double getImag()
/*      */   {
/*  156 */     return this.magnitude * Math.sin(this.phaseInRad);
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */   public static Phasor toPhasor(Complex cc)
/*      */   {
/*  163 */     Phasor ph = new Phasor();
/*  164 */     ph.magnitude = cc.abs();
/*  165 */     ph.phaseInRad = cc.argRad();
/*  166 */     ph.phaseInDeg = cc.argDeg();
/*  167 */     ph.rectangular = cc;
/*  168 */     return ph;
/*      */   }
/*      */   
/*      */   public Complex toRectangular()
/*      */   {
/*  173 */     Complex cc = new Complex();
/*  174 */     cc.polar(this.magnitude, this.phaseInRad);
/*  175 */     return cc;
/*      */   }
/*      */   
/*      */   public static Complex toRectangular(Phasor ph)
/*      */   {
/*  180 */     Complex cc = new Complex();
/*  181 */     cc.polar(ph.magnitude, ph.phaseInRad);
/*  182 */     return cc;
/*      */   }
/*      */   
/*      */   public Complex toComplex()
/*      */   {
/*  187 */     Complex cc = new Complex();
/*  188 */     cc.polar(this.magnitude, this.phaseInRad);
/*  189 */     return cc;
/*      */   }
/*      */   
/*      */   public static Complex toComplex(Phasor ph)
/*      */   {
/*  194 */     Complex cc = new Complex();
/*  195 */     cc.polar(ph.magnitude, ph.phaseInRad);
/*  196 */     return cc;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */   public String toString()
/*      */   {
/*  203 */     return this.magnitude + "<" + this.phaseInDeg + "deg";
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */   public static String toString(Phasor ph)
/*      */   {
/*  210 */     return ph.magnitude + "<" + ph.phaseInDeg + "deg";
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public static Phasor parsePhasor(String ss)
/*      */   {
/*  223 */     Phasor ph = new Phasor();
/*  224 */     ss = ss.trim();
/*  225 */     int anglePos = ss.indexOf('<');
/*  226 */     if (anglePos == -1) {
/*  227 */       anglePos = ss.indexOf('L');
/*  228 */       if (anglePos == -1) throw new IllegalArgumentException("no angle symbol, <, in the string, ss");
/*      */     }
/*  230 */     int degPos = ss.indexOf('d');
/*  231 */     if (degPos == -1) degPos = ss.indexOf('D');
/*  232 */     int radPos = ss.indexOf('r');
/*  233 */     if (radPos == -1) degPos = ss.indexOf('R');
/*  234 */     String mag = ss.substring(0, anglePos);
/*  235 */     ph.magnitude = Double.parseDouble(mag);
/*  236 */     String phas = null;
/*  237 */     if (degPos != -1) {
/*  238 */       phas = ss.substring(anglePos + 1, degPos);
/*  239 */       ph.phaseInDeg = Double.parseDouble(mag);
/*  240 */       ph.phaseInRad = Math.toRadians(ph.phaseInDeg);
/*      */     }
/*  242 */     if ((degPos == -1) && (radPos == -1)) {
/*  243 */       phas = ss.substring(anglePos + 1);
/*  244 */       ph.phaseInDeg = Double.parseDouble(phas);
/*  245 */       ph.phaseInRad = Math.toRadians(ph.phaseInDeg);
/*      */     }
/*  247 */     if (radPos != -1) {
/*  248 */       phas = ss.substring(anglePos + 1, radPos);
/*  249 */       ph.phaseInRad = Double.parseDouble(phas);
/*  250 */       ph.phaseInDeg = Math.toDegrees(ph.phaseInRad);
/*      */     }
/*  252 */     ph.rectangular.polar(ph.magnitude, ph.phaseInRad);
/*      */     
/*  254 */     return ph;
/*      */   }
/*      */   
/*      */ 
/*      */   public static Phasor valueOf(String ss)
/*      */   {
/*  260 */     return parsePhasor(ss);
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public static final synchronized Phasor readPhasor(String prompt)
/*      */   {
/*  272 */     int ch = 32;
/*  273 */     String phstring = "";
/*  274 */     boolean done = false;
/*      */     
/*  276 */     System.out.print(prompt + " ");
/*  277 */     System.out.flush();
/*      */     
/*  279 */     while (!done) {
/*      */       try {
/*  281 */         ch = System.in.read();
/*  282 */         if ((ch < 0) || ((char)ch == '\n')) {
/*  283 */           done = true;
/*      */         } else {
/*  285 */           phstring = phstring + (char)ch;
/*      */         }
/*      */       } catch (IOException e) {
/*  288 */         done = true;
/*      */       }
/*      */     }
/*  291 */     return parsePhasor(phstring);
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public static final synchronized Phasor readPhasor(String prompt, String dflt)
/*      */   {
/*  301 */     int ch = 32;
/*  302 */     String phstring = "";
/*  303 */     boolean done = false;
/*      */     
/*  305 */     System.out.print(prompt + " [default value = " + dflt + "]  ");
/*  306 */     System.out.flush();
/*      */     
/*  308 */     int i = 0;
/*  309 */     while (!done) {
/*      */       try {
/*  311 */         ch = System.in.read();
/*  312 */         if ((ch < 0) || ((char)ch == '\n') || ((char)ch == '\r')) {
/*  313 */           if (i == 0) {
/*  314 */             phstring = dflt;
/*  315 */             if ((char)ch == '\r') ch = System.in.read();
/*      */           }
/*  317 */           done = true;
/*      */         }
/*      */         else {
/*  320 */           phstring = phstring + (char)ch;
/*  321 */           i++;
/*      */         }
/*      */       }
/*      */       catch (IOException e) {
/*  325 */         done = true;
/*      */       }
/*      */     }
/*  328 */     return parsePhasor(phstring);
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public static final synchronized Phasor readPhasor(String prompt, Phasor dflt)
/*      */   {
/*  339 */     int ch = 32;
/*  340 */     String phstring = "";
/*  341 */     boolean done = false;
/*      */     
/*  343 */     System.out.print(prompt + " [default value = " + dflt + "]  ");
/*  344 */     System.out.flush();
/*      */     
/*  346 */     int i = 0;
/*  347 */     while (!done) {
/*      */       try {
/*  349 */         ch = System.in.read();
/*  350 */         if ((ch < 0) || ((char)ch == '\n') || ((char)ch == '\r')) {
/*  351 */           if (i == 0) {
/*  352 */             if ((char)ch == '\r') ch = System.in.read();
/*  353 */             return dflt;
/*      */           }
/*  355 */           done = true;
/*      */         }
/*      */         else {
/*  358 */           phstring = phstring + (char)ch;
/*  359 */           i++;
/*      */         }
/*      */       }
/*      */       catch (IOException e) {
/*  363 */         done = true;
/*      */       }
/*      */     }
/*  366 */     return parsePhasor(phstring);
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */   public static final synchronized Phasor readPhasor()
/*      */   {
/*  374 */     int ch = 32;
/*  375 */     String phstring = "";
/*  376 */     boolean done = false;
/*      */     
/*  378 */     System.out.print(" ");
/*  379 */     System.out.flush();
/*      */     
/*  381 */     while (!done) {
/*      */       try {
/*  383 */         ch = System.in.read();
/*  384 */         if ((ch < 0) || ((char)ch == '\n')) {
/*  385 */           done = true;
/*      */         } else {
/*  387 */           phstring = phstring + (char)ch;
/*      */         }
/*      */       } catch (IOException e) {
/*  390 */         done = true;
/*      */       }
/*      */     }
/*  393 */     return parsePhasor(phstring);
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */   public void println(String message)
/*      */   {
/*  400 */     System.out.println(message + " " + toString());
/*      */   }
/*      */   
/*      */   public void println()
/*      */   {
/*  405 */     System.out.println(" " + toString());
/*      */   }
/*      */   
/*      */   public void print(String message)
/*      */   {
/*  410 */     System.out.print(message + " " + toString());
/*      */   }
/*      */   
/*      */   public void print()
/*      */   {
/*  415 */     System.out.print(" " + toString());
/*      */   }
/*      */   
/*      */ 
/*      */   public static void println(String message, Phasor[] aa)
/*      */   {
/*  421 */     System.out.println(message);
/*  422 */     for (int i = 0; i < aa.length; i++) {
/*  423 */       System.out.println(aa[i].toString() + "  ");
/*      */     }
/*      */   }
/*      */   
/*      */   public static void println(Phasor[] aa)
/*      */   {
/*  429 */     for (int i = 0; i < aa.length; i++) {
/*  430 */       System.out.println(aa[i].toString() + "  ");
/*      */     }
/*      */   }
/*      */   
/*      */   public static void print(String message, Phasor[] aa)
/*      */   {
/*  436 */     System.out.print(message + " ");
/*  437 */     for (int i = 0; i < aa.length; i++) {
/*  438 */       System.out.print(aa[i].toString() + "   ");
/*      */     }
/*  440 */     System.out.println();
/*      */   }
/*      */   
/*      */   public static void print(Phasor[] aa)
/*      */   {
/*  445 */     for (int i = 0; i < aa.length; i++) {
/*  446 */       System.out.print(aa[i].toString() + "  ");
/*      */     }
/*  448 */     System.out.println();
/*      */   }
/*      */   
/*      */ 
/*      */   public Phasor truncate(int prec)
/*      */   {
/*  454 */     if (prec < 0) { return this;
/*      */     }
/*  456 */     double xMa = this.magnitude;
/*  457 */     double xPd = this.phaseInDeg;
/*  458 */     double xPr = this.phaseInRad;
/*  459 */     Complex xRect = this.rectangular;
/*      */     
/*  461 */     Phasor y = new Phasor();
/*      */     
/*  463 */     y.magnitude = Fmath.truncate(xMa, prec);
/*  464 */     y.phaseInDeg = Fmath.truncate(xPd, prec);
/*  465 */     y.phaseInRad = Fmath.truncate(xPr, prec);
/*  466 */     y.rectangular = Complex.truncate(xRect, prec);
/*      */     
/*  468 */     return y;
/*      */   }
/*      */   
/*      */   public static Phasor truncate(Phasor ph, int prec)
/*      */   {
/*  473 */     if (prec < 0) { return ph;
/*      */     }
/*  475 */     double xMa = ph.magnitude;
/*  476 */     double xPd = ph.phaseInDeg;
/*  477 */     double xPr = ph.phaseInRad;
/*  478 */     Complex xRect = ph.rectangular;
/*      */     
/*  480 */     Phasor y = new Phasor();
/*      */     
/*  482 */     y.magnitude = Fmath.truncate(xMa, prec);
/*  483 */     y.phaseInDeg = Fmath.truncate(xPd, prec);
/*  484 */     y.phaseInRad = Fmath.truncate(xPr, prec);
/*  485 */     y.rectangular = Complex.truncate(xRect, prec);
/*      */     
/*  487 */     return y;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public int hashCode()
/*      */   {
/*  496 */     long lmagnt = Double.doubleToLongBits(this.magnitude);
/*  497 */     long lphase = Double.doubleToLongBits(this.phaseInDeg);
/*  498 */     int hmagnt = (int)(lmagnt ^ lmagnt >>> 32);
/*  499 */     int hphase = (int)(lphase ^ lphase >>> 32);
/*  500 */     return 6 * (hmagnt / 10) + 4 * (hphase / 10);
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public static Phasor[] oneDarray(int n)
/*      */   {
/*  509 */     Phasor[] a = new Phasor[n];
/*  510 */     Phasor b = new Phasor();
/*  511 */     b.reset(1.0D, 0.0D);
/*  512 */     for (int i = 0; i < n; i++) {
/*  513 */       a[i] = b;
/*      */     }
/*  515 */     return a;
/*      */   }
/*      */   
/*      */ 
/*      */   public static Phasor[] oneDarray(int n, double a, double b)
/*      */   {
/*  521 */     Phasor[] phArray = new Phasor[n];
/*  522 */     Phasor ph = new Phasor();
/*  523 */     ph.reset(a, b);
/*  524 */     for (int i = 0; i < n; i++) {
/*  525 */       phArray[i] = ph;
/*      */     }
/*  527 */     return phArray;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */   public static Phasor[] oneDarray(int n, Phasor constant)
/*      */   {
/*  534 */     Phasor[] ph = new Phasor[n];
/*  535 */     for (int i = 0; i < n; i++) {
/*  536 */       ph[i] = constant.copy();
/*      */     }
/*  538 */     return ph;
/*      */   }
/*      */   
/*      */ 
/*      */   public static Phasor[][] twoDarray(int n, int m)
/*      */   {
/*  544 */     Phasor[][] phArray = new Phasor[n][m];
/*  545 */     Phasor ph = new Phasor();
/*  546 */     ph.reset(1.0D, 0.0D);
/*  547 */     for (int i = 0; i < n; i++) {
/*  548 */       for (int j = 0; j < m; j++) {
/*  549 */         phArray[i][j] = ph;
/*      */       }
/*      */     }
/*  552 */     return phArray;
/*      */   }
/*      */   
/*      */ 
/*      */   public static Phasor[][] twoDarray(int n, int m, double a, double b)
/*      */   {
/*  558 */     Phasor[][] phArray = new Phasor[n][m];
/*  559 */     Phasor ph = new Phasor();
/*  560 */     ph.reset(a, b);
/*  561 */     for (int i = 0; i < n; i++) {
/*  562 */       for (int j = 0; j < m; j++) {
/*  563 */         phArray[i][j] = ph;
/*      */       }
/*      */     }
/*  566 */     return phArray;
/*      */   }
/*      */   
/*      */ 
/*      */   public static Phasor[][] twoDarray(int n, int m, Phasor constant)
/*      */   {
/*  572 */     Phasor[][] phArray = new Phasor[n][m];
/*  573 */     for (int i = 0; i < n; i++) {
/*  574 */       for (int j = 0; j < m; j++) {
/*  575 */         phArray[i][j] = constant.copy();
/*      */       }
/*      */     }
/*  578 */     return phArray;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */   public static Phasor[][][] threeDarray(int n, int m, int l)
/*      */   {
/*  585 */     Phasor[][][] phArray = new Phasor[n][m][l];
/*  586 */     Phasor ph = new Phasor();
/*  587 */     ph.reset(1.0D, 0.0D);
/*  588 */     for (int i = 0; i < n; i++) {
/*  589 */       for (int j = 0; j < m; j++) {
/*  590 */         for (int k = 0; k < l; k++) {
/*  591 */           phArray[i][j][k] = ph;
/*      */         }
/*      */       }
/*      */     }
/*  595 */     return phArray;
/*      */   }
/*      */   
/*      */ 
/*      */   public static Phasor[][][] threeDarray(int n, int m, int l, double a, double b)
/*      */   {
/*  601 */     Phasor[][][] phArray = new Phasor[n][m][l];
/*  602 */     Phasor ph = new Phasor();
/*  603 */     ph.reset(a, b);
/*  604 */     for (int i = 0; i < n; i++) {
/*  605 */       for (int j = 0; j < m; j++) {
/*  606 */         for (int k = 0; k < l; k++) {
/*  607 */           phArray[i][j][k] = ph;
/*      */         }
/*      */       }
/*      */     }
/*  611 */     return phArray;
/*      */   }
/*      */   
/*      */ 
/*      */   public static Phasor[][][] threeDarray(int n, int m, int l, Phasor constant)
/*      */   {
/*  617 */     Phasor[][][] phArray = new Phasor[n][m][l];
/*  618 */     for (int i = 0; i < n; i++) {
/*  619 */       for (int j = 0; j < m; j++) {
/*  620 */         for (int k = 0; k < l; k++) {
/*  621 */           phArray[i][j][k] = constant.copy();
/*      */         }
/*      */       }
/*      */     }
/*  625 */     return phArray;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */   public Phasor copy()
/*      */   {
/*  632 */     if (this == null) {
/*  633 */       return null;
/*      */     }
/*      */     
/*  636 */     Phasor b = new Phasor();
/*  637 */     b.magnitude = this.magnitude;
/*  638 */     b.phaseInDeg = this.phaseInDeg;
/*  639 */     b.phaseInRad = this.phaseInRad;
/*  640 */     return b;
/*      */   }
/*      */   
/*      */ 
/*      */   public static Phasor copy(Phasor ph)
/*      */   {
/*  646 */     if (ph == null) {
/*  647 */       return null;
/*      */     }
/*      */     
/*  650 */     Phasor b = new Phasor();
/*  651 */     b.magnitude = ph.magnitude;
/*  652 */     b.phaseInDeg = ph.phaseInDeg;
/*  653 */     b.phaseInRad = ph.phaseInRad;
/*  654 */     return b;
/*      */   }
/*      */   
/*      */ 
/*      */   public static Phasor[] copy(Phasor[] a)
/*      */   {
/*  660 */     if (a == null) {
/*  661 */       return null;
/*      */     }
/*      */     
/*  664 */     int n = a.length;
/*  665 */     Phasor[] b = oneDarray(n);
/*  666 */     for (int i = 0; i < n; i++) {
/*  667 */       b[i] = a[i].copy();
/*      */     }
/*  669 */     return b;
/*      */   }
/*      */   
/*      */ 
/*      */   public static Phasor[][] copy(Phasor[][] a)
/*      */   {
/*  675 */     if (a == null) {
/*  676 */       return (Phasor[][])null;
/*      */     }
/*      */     
/*  679 */     int n = a.length;
/*  680 */     int m = a[0].length;
/*  681 */     Phasor[][] b = twoDarray(n, m);
/*  682 */     for (int i = 0; i < n; i++) {
/*  683 */       for (int j = 0; j < m; j++) {
/*  684 */         b[i][j] = a[i][j].copy();
/*      */       }
/*      */     }
/*  687 */     return b;
/*      */   }
/*      */   
/*      */ 
/*      */   public static Phasor[][][] copy(Phasor[][][] a)
/*      */   {
/*  693 */     if (a == null) {
/*  694 */       return (Phasor[][][])null;
/*      */     }
/*      */     
/*  697 */     int n = a.length;
/*  698 */     int m = a[0].length;
/*  699 */     int l = a[0][0].length;
/*  700 */     Phasor[][][] b = threeDarray(n, m, l);
/*  701 */     for (int i = 0; i < n; i++) {
/*  702 */       for (int j = 0; j < m; j++) {
/*  703 */         for (int k = 0; k < l; k++) {
/*  704 */           b[i][j][k] = a[i][j][k].copy();
/*      */         }
/*      */       }
/*      */     }
/*  708 */     return b;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */   public Object clone()
/*      */   {
/*  716 */     Object ret = null;
/*      */     
/*  718 */     if (this != null) {
/*  719 */       Phasor b = new Phasor();
/*  720 */       b.magnitude = this.magnitude;
/*  721 */       b.phaseInDeg = this.phaseInDeg;
/*  722 */       b.phaseInRad = this.phaseInRad;
/*  723 */       ret = b;
/*      */     }
/*      */     
/*  726 */     return ret;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */   public Phasor plus(Phasor ph)
/*      */   {
/*  734 */     Complex com1 = toRectangular();
/*  735 */     Complex com2 = ph.toRectangular();
/*  736 */     Complex com3 = com1.plus(com2);
/*  737 */     return toPhasor(com3);
/*      */   }
/*      */   
/*      */ 
/*      */   public Phasor plus(Complex com1)
/*      */   {
/*  743 */     Phasor ph = new Phasor();
/*  744 */     Complex com2 = toRectangular();
/*  745 */     Complex com3 = com1.plus(com2);
/*  746 */     return toPhasor(com3);
/*      */   }
/*      */   
/*      */   public void plusEquals(Phasor ph1)
/*      */   {
/*  751 */     Complex com1 = toRectangular();
/*  752 */     Complex com2 = ph1.toRectangular();
/*  753 */     Complex com3 = com1.plus(com2);
/*  754 */     Phasor ph2 = toPhasor(com3);
/*  755 */     this.magnitude = ph2.magnitude;
/*  756 */     this.phaseInDeg = ph2.phaseInDeg;
/*  757 */     this.phaseInRad = ph2.phaseInRad;
/*      */   }
/*      */   
/*      */   public void plusEquals(Complex com1)
/*      */   {
/*  762 */     Complex com2 = toRectangular();
/*  763 */     Complex com3 = com1.plus(com2);
/*  764 */     Phasor ph2 = toPhasor(com3);
/*  765 */     this.magnitude += ph2.magnitude;
/*  766 */     this.phaseInDeg += ph2.phaseInDeg;
/*  767 */     this.phaseInRad += ph2.phaseInRad;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */   public Phasor minus(Phasor ph)
/*      */   {
/*  775 */     Complex com1 = toRectangular();
/*  776 */     Complex com2 = ph.toRectangular();
/*  777 */     Complex com3 = com1.minus(com2);
/*  778 */     return toPhasor(com3);
/*      */   }
/*      */   
/*      */ 
/*      */   public Phasor minus(Complex com1)
/*      */   {
/*  784 */     Phasor ph = new Phasor();
/*  785 */     Complex com2 = toRectangular();
/*  786 */     Complex com3 = com1.minus(com2);
/*  787 */     return toPhasor(com3);
/*      */   }
/*      */   
/*      */   public void minusEquals(Phasor ph1)
/*      */   {
/*  792 */     Complex com1 = toRectangular();
/*  793 */     Complex com2 = ph1.toRectangular();
/*  794 */     Complex com3 = com1.plus(com2);
/*  795 */     Phasor ph2 = toPhasor(com3);
/*  796 */     this.magnitude = ph2.magnitude;
/*  797 */     this.phaseInDeg = ph2.phaseInDeg;
/*  798 */     this.phaseInRad = ph2.phaseInRad;
/*      */   }
/*      */   
/*      */   public void minusEquals(Complex com1)
/*      */   {
/*  803 */     Complex com2 = toRectangular();
/*  804 */     Complex com3 = com1.plus(com2);
/*  805 */     Phasor ph2 = toPhasor(com3);
/*  806 */     this.magnitude = ph2.magnitude;
/*  807 */     this.phaseInDeg = ph2.phaseInDeg;
/*  808 */     this.phaseInRad = ph2.phaseInRad;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */   public Phasor times(Phasor ph1)
/*      */   {
/*  816 */     Phasor ph2 = new Phasor();
/*  817 */     double mag = this.magnitude * ph1.magnitude;
/*  818 */     double pha = this.phaseInDeg + ph1.phaseInDeg;
/*  819 */     ph2.reset(mag, pha);
/*  820 */     return ph2;
/*      */   }
/*      */   
/*      */ 
/*      */   public Phasor times(Complex com1)
/*      */   {
/*  826 */     Phasor ph1 = toPhasor(com1);
/*  827 */     Phasor ph2 = new Phasor();
/*  828 */     double mag = this.magnitude * ph1.magnitude;
/*  829 */     double pha = this.phaseInDeg + ph1.phaseInDeg;
/*  830 */     ph2.reset(mag, pha);
/*  831 */     return ph2;
/*      */   }
/*      */   
/*      */ 
/*      */   public Phasor times(double constant)
/*      */   {
/*  837 */     Phasor ph2 = new Phasor();
/*  838 */     double mag = this.magnitude * constant;
/*  839 */     double pha = this.phaseInDeg;
/*  840 */     ph2.reset(mag, pha);
/*  841 */     return ph2;
/*      */   }
/*      */   
/*      */ 
/*      */   public Phasor times(int constant)
/*      */   {
/*  847 */     Phasor ph2 = new Phasor();
/*  848 */     double mag = this.magnitude * constant;
/*  849 */     double pha = this.phaseInDeg;
/*  850 */     ph2.reset(mag, pha);
/*  851 */     return ph2;
/*      */   }
/*      */   
/*      */ 
/*      */   public Phasor timesExpOmegaTime(double omega, double time)
/*      */   {
/*  857 */     if (Fmath.isNaN(omega)) {
/*  858 */       omega = omega;
/*  859 */       frequency = omega / 6.283185307179586D;
/*      */     }
/*      */     else {
/*  862 */       throw new IllegalArgumentException("You have already entered a value for the radial frequency, omega, " + omega + ", that differs from the one you are now attempting to enter, " + omega);
/*      */     }
/*  864 */     Phasor ph2 = new Phasor();
/*  865 */     ph2.reset(this.magnitude, this.phaseInDeg + Math.toDegrees(omega * time));
/*  866 */     return ph2;
/*      */   }
/*      */   
/*      */ 
/*      */   public Phasor timesExpTwoPiFreqTime(double frequency, double time)
/*      */   {
/*  872 */     if (Fmath.isNaN(frequency)) {
/*  873 */       frequency = frequency;
/*  874 */       omega = frequency * 2.0D * 3.141592653589793D;
/*      */     }
/*      */     else {
/*  877 */       throw new IllegalArgumentException("You have already entered a value for the frequency, " + frequency + ", that differs from the one you are now attempting to enter, " + frequency);
/*      */     }
/*  879 */     Phasor ph2 = new Phasor();
/*  880 */     ph2.reset(this.magnitude, this.phaseInDeg + Math.toDegrees(6.283185307179586D * frequency * time));
/*  881 */     return ph2;
/*      */   }
/*      */   
/*      */   public void timesEquals(Phasor ph1)
/*      */   {
/*  886 */     this.magnitude *= ph1.magnitude;
/*  887 */     this.phaseInDeg += ph1.phaseInDeg;
/*  888 */     this.phaseInRad += ph1.phaseInRad;
/*      */   }
/*      */   
/*      */   public void timesEquals(Complex com1)
/*      */   {
/*  893 */     Phasor ph1 = toPhasor(com1);
/*  894 */     this.magnitude *= ph1.magnitude;
/*  895 */     this.phaseInDeg += ph1.phaseInDeg;
/*  896 */     this.phaseInRad += ph1.phaseInRad;
/*      */   }
/*      */   
/*      */   public void timesEquals(double constant)
/*      */   {
/*  901 */     this.magnitude *= constant;
/*      */   }
/*      */   
/*      */   public void timesEquals(int constant)
/*      */   {
/*  906 */     this.magnitude *= constant;
/*      */   }
/*      */   
/*      */   public void timesEqualsOmegaTime(double omega, double time)
/*      */   {
/*  911 */     if (Fmath.isNaN(omega)) {
/*  912 */       omega = omega;
/*  913 */       frequency = omega / 6.283185307179586D;
/*      */     }
/*      */     else {
/*  916 */       throw new IllegalArgumentException("You have already entered a value for radial frequency, omega, " + omega + ", that differs from the one you are now attempting to enter, " + omega);
/*      */     }
/*  918 */     this.phaseInRad += omega * time;
/*  919 */     this.phaseInDeg = Math.toDegrees(this.phaseInRad);
/*      */   }
/*      */   
/*      */   public void timesEqualsTwoPiFreqTime(double frequency, double time)
/*      */   {
/*  924 */     if (Fmath.isNaN(frequency)) {
/*  925 */       frequency = frequency;
/*  926 */       omega = frequency * 2.0D * 3.141592653589793D;
/*      */     }
/*      */     else {
/*  929 */       throw new IllegalArgumentException("You have already entered a value for the frequency, " + frequency + ", that differs from the one you are now attempting to enter, " + frequency);
/*      */     }
/*  931 */     this.phaseInRad += 6.283185307179586D * frequency * time;
/*  932 */     this.phaseInDeg = Math.toDegrees(this.phaseInRad);
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */   public Phasor over(Phasor ph1)
/*      */   {
/*  940 */     Phasor ph2 = new Phasor();
/*  941 */     double mag = this.magnitude / ph1.magnitude;
/*  942 */     double pha = this.phaseInDeg - ph1.phaseInDeg;
/*  943 */     ph2.reset(mag, pha);
/*  944 */     return ph2;
/*      */   }
/*      */   
/*      */ 
/*      */   public Phasor over(Complex com1)
/*      */   {
/*  950 */     Phasor ph1 = toPhasor(com1);
/*  951 */     Phasor ph2 = new Phasor();
/*  952 */     double mag = this.magnitude / ph1.magnitude;
/*  953 */     double pha = this.phaseInDeg - ph1.phaseInDeg;
/*  954 */     ph2.reset(mag, pha);
/*  955 */     return ph2;
/*      */   }
/*      */   
/*      */ 
/*      */   public Phasor over(double constant)
/*      */   {
/*  961 */     Phasor ph2 = new Phasor();
/*  962 */     double mag = this.magnitude / constant;
/*  963 */     double pha = this.phaseInDeg;
/*  964 */     ph2.reset(mag, pha);
/*  965 */     return ph2;
/*      */   }
/*      */   
/*      */ 
/*      */   public Phasor over(int constant)
/*      */   {
/*  971 */     Phasor ph2 = new Phasor();
/*  972 */     double mag = this.magnitude / constant;
/*  973 */     double pha = this.phaseInDeg;
/*  974 */     ph2.reset(mag, pha);
/*  975 */     return ph2;
/*      */   }
/*      */   
/*      */   public void overEquals(Phasor ph1)
/*      */   {
/*  980 */     this.magnitude /= ph1.magnitude;
/*  981 */     this.phaseInDeg -= ph1.phaseInDeg;
/*  982 */     this.phaseInRad -= ph1.phaseInRad;
/*      */   }
/*      */   
/*      */   public void overEquals(Complex com1)
/*      */   {
/*  987 */     Phasor ph1 = toPhasor(com1);
/*  988 */     this.magnitude /= ph1.magnitude;
/*  989 */     this.phaseInDeg -= ph1.phaseInDeg;
/*  990 */     this.phaseInRad -= ph1.phaseInRad;
/*      */   }
/*      */   
/*      */   public void overEquals(double constant)
/*      */   {
/*  995 */     this.magnitude /= constant;
/*      */   }
/*      */   
/*      */   public void overEquals(int constant)
/*      */   {
/* 1000 */     this.magnitude /= constant;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */   public double abs()
/*      */   {
/* 1008 */     return Math.abs(this.magnitude);
/*      */   }
/*      */   
/*      */ 
/*      */   public double argInRadians()
/*      */   {
/* 1014 */     return this.phaseInRad;
/*      */   }
/*      */   
/*      */ 
/*      */   public double argInDegrees()
/*      */   {
/* 1020 */     return this.phaseInDeg;
/*      */   }
/*      */   
/*      */ 
/*      */   public Phasor negate()
/*      */   {
/* 1026 */     Phasor ph = new Phasor();
/* 1027 */     ph.reset(-this.magnitude, this.phaseInDeg);
/* 1028 */     return ph;
/*      */   }
/*      */   
/*      */   public Phasor conjugate()
/*      */   {
/* 1033 */     Phasor ph = new Phasor();
/* 1034 */     ph.reset(this.magnitude, -this.phaseInDeg);
/* 1035 */     return ph;
/*      */   }
/*      */   
/*      */   public Phasor inverse()
/*      */   {
/* 1040 */     Phasor ph = new Phasor();
/* 1041 */     ph.reset(1.0D / this.magnitude, -this.phaseInDeg);
/* 1042 */     return ph;
/*      */   }
/*      */   
/*      */ 
/*      */   public static Phasor sqrt(Phasor ph1)
/*      */   {
/* 1048 */     Phasor ph2 = new Phasor();
/* 1049 */     ph2.reset(Math.sqrt(ph1.magnitude), ph1.phaseInDeg / 2.0D);
/* 1050 */     return ph2;
/*      */   }
/*      */   
/*      */   public static Phasor nthRoot(Phasor ph1, int n)
/*      */   {
/* 1055 */     if (n <= 0) throw new IllegalArgumentException("The root, " + n + ", must be greater than zero");
/* 1056 */     Phasor ph2 = new Phasor();
/* 1057 */     ph2.reset(Math.pow(ph1.magnitude, 1.0D / n), ph1.phaseInDeg / n);
/* 1058 */     return ph2;
/*      */   }
/*      */   
/*      */ 
/*      */   public static Phasor square(Phasor ph1)
/*      */   {
/* 1064 */     Phasor ph2 = new Phasor();
/* 1065 */     ph2.reset(Fmath.square(ph1.magnitude), 2.0D * ph1.phaseInDeg);
/* 1066 */     return ph2;
/*      */   }
/*      */   
/*      */   public static Phasor pow(Phasor ph1, int n)
/*      */   {
/* 1071 */     Phasor ph2 = new Phasor();
/* 1072 */     ph2.reset(Math.pow(ph1.magnitude, n), n * ph1.phaseInDeg);
/* 1073 */     return ph2;
/*      */   }
/*      */   
/*      */   public static Phasor pow(Phasor ph1, double n)
/*      */   {
/* 1078 */     Phasor ph2 = new Phasor();
/* 1079 */     ph2.reset(Math.pow(ph1.magnitude, n), n * ph1.phaseInDeg);
/* 1080 */     return ph2;
/*      */   }
/*      */   
/*      */   public static Phasor pow(Phasor ph1, Complex n)
/*      */   {
/* 1085 */     Complex com1 = ph1.toRectangular();
/* 1086 */     Complex com2 = Complex.pow(com1, n);
/* 1087 */     Phasor ph2 = toPhasor(com2);
/* 1088 */     return ph2;
/*      */   }
/*      */   
/*      */   public static Phasor pow(Phasor ph1, Phasor n)
/*      */   {
/* 1093 */     Complex com1 = ph1.toRectangular();
/* 1094 */     Complex comn = n.toRectangular();
/* 1095 */     Complex com2 = Complex.pow(com1, comn);
/* 1096 */     Phasor ph2 = toPhasor(com2);
/* 1097 */     return ph2;
/*      */   }
/*      */   
/*      */ 
/*      */   public static Phasor exp(Phasor ph1)
/*      */   {
/* 1103 */     Complex com = ph1.toRectangular();
/* 1104 */     com = Complex.exp(com);
/* 1105 */     Phasor ph2 = toPhasor(com);
/* 1106 */     return ph2;
/*      */   }
/*      */   
/*      */   public static Phasor log(Phasor ph1)
/*      */   {
/* 1111 */     Complex com = new Complex(Math.log(ph1.magnitude), ph1.phaseInDeg);
/* 1112 */     Phasor ph2 = toPhasor(com);
/* 1113 */     return ph2;
/*      */   }
/*      */   
/*      */ 
/*      */   public Phasor sin(Phasor ph1)
/*      */   {
/* 1119 */     Phasor ph2 = new Phasor();
/* 1120 */     if (ph1.phaseInDeg == 0.0D) {
/* 1121 */       ph2.reset(Math.sin(ph1.magnitude), 0.0D);
/*      */     }
/*      */     else {
/* 1124 */       Complex com = ph1.toRectangular();
/* 1125 */       com = Complex.sin(com);
/* 1126 */       ph2 = toPhasor(com);
/*      */     }
/*      */     
/* 1129 */     return ph2;
/*      */   }
/*      */   
/*      */   public Phasor cos(Phasor ph1)
/*      */   {
/* 1134 */     Phasor ph2 = new Phasor();
/* 1135 */     if (ph1.phaseInDeg == 0.0D) {
/* 1136 */       ph2.reset(Math.cos(ph1.magnitude), 0.0D);
/*      */     }
/*      */     else {
/* 1139 */       Complex com = ph1.toRectangular();
/* 1140 */       com = Complex.cos(com);
/* 1141 */       ph2 = toPhasor(com);
/*      */     }
/*      */     
/* 1144 */     return ph2;
/*      */   }
/*      */   
/*      */   public Phasor tan(Phasor ph1)
/*      */   {
/* 1149 */     Phasor ph2 = new Phasor();
/* 1150 */     if (ph1.phaseInDeg == 0.0D) {
/* 1151 */       ph2.reset(Math.tan(ph1.magnitude), 0.0D);
/*      */     }
/*      */     else {
/* 1154 */       Complex com = ph1.toRectangular();
/* 1155 */       com = Complex.tan(com);
/* 1156 */       ph2 = toPhasor(com);
/*      */     }
/*      */     
/* 1159 */     return ph2;
/*      */   }
/*      */   
/*      */   public Phasor cot(Phasor ph1)
/*      */   {
/* 1164 */     Phasor ph2 = new Phasor();
/* 1165 */     if (ph1.phaseInDeg == 0.0D) {
/* 1166 */       ph2.reset(Fmath.cot(ph1.magnitude), 0.0D);
/*      */     }
/*      */     else {
/* 1169 */       Complex com = ph1.toRectangular();
/* 1170 */       com = Complex.cot(com);
/* 1171 */       ph2 = toPhasor(com);
/*      */     }
/*      */     
/* 1174 */     return ph2;
/*      */   }
/*      */   
/*      */   public Phasor sec(Phasor ph1)
/*      */   {
/* 1179 */     Phasor ph2 = new Phasor();
/* 1180 */     if (ph1.phaseInDeg == 0.0D) {
/* 1181 */       ph2.reset(Fmath.sec(ph1.magnitude), 0.0D);
/*      */     }
/*      */     else {
/* 1184 */       Complex com = ph1.toRectangular();
/* 1185 */       com = Complex.sec(com);
/* 1186 */       ph2 = toPhasor(com);
/*      */     }
/*      */     
/* 1189 */     return ph2;
/*      */   }
/*      */   
/*      */   public Phasor csc(Phasor ph1)
/*      */   {
/* 1194 */     Phasor ph2 = new Phasor();
/* 1195 */     if (ph1.phaseInDeg == 0.0D) {
/* 1196 */       ph2.reset(Fmath.csc(ph1.magnitude), 0.0D);
/*      */     }
/*      */     else {
/* 1199 */       Complex com = ph1.toRectangular();
/* 1200 */       com = Complex.csc(com);
/* 1201 */       ph2 = toPhasor(com);
/*      */     }
/*      */     
/* 1204 */     return ph2;
/*      */   }
/*      */   
/*      */   public Phasor exsec(Phasor ph1)
/*      */   {
/* 1209 */     Phasor ph2 = new Phasor();
/* 1210 */     if (ph1.phaseInDeg == 0.0D) {
/* 1211 */       ph2.reset(Fmath.exsec(ph1.magnitude), 0.0D);
/*      */     }
/*      */     else {
/* 1214 */       Complex com = ph1.toRectangular();
/* 1215 */       com = Complex.exsec(com);
/* 1216 */       ph2 = toPhasor(com);
/*      */     }
/*      */     
/* 1219 */     return ph2;
/*      */   }
/*      */   
/*      */   public Phasor vers(Phasor ph1)
/*      */   {
/* 1224 */     Phasor ph2 = new Phasor();
/* 1225 */     if (ph1.phaseInDeg == 0.0D) {
/* 1226 */       ph2.reset(Fmath.vers(ph1.magnitude), 0.0D);
/*      */     }
/*      */     else {
/* 1229 */       Complex com = ph1.toRectangular();
/* 1230 */       com = Complex.vers(com);
/* 1231 */       ph2 = toPhasor(com);
/*      */     }
/*      */     
/* 1234 */     return ph2;
/*      */   }
/*      */   
/*      */   public Phasor covers(Phasor ph1)
/*      */   {
/* 1239 */     Phasor ph2 = new Phasor();
/* 1240 */     if (ph1.phaseInDeg == 0.0D) {
/* 1241 */       ph2.reset(Fmath.covers(ph1.magnitude), 0.0D);
/*      */     }
/*      */     else {
/* 1244 */       Complex com = ph1.toRectangular();
/* 1245 */       com = Complex.covers(com);
/* 1246 */       ph2 = toPhasor(com);
/*      */     }
/*      */     
/* 1249 */     return ph2;
/*      */   }
/*      */   
/*      */   public Phasor hav(Phasor ph1)
/*      */   {
/* 1254 */     Phasor ph2 = new Phasor();
/* 1255 */     if (ph1.phaseInDeg == 0.0D) {
/* 1256 */       ph2.reset(Fmath.hav(ph1.magnitude), 0.0D);
/*      */     }
/*      */     else {
/* 1259 */       Complex com = ph1.toRectangular();
/* 1260 */       com = Complex.hav(com);
/* 1261 */       ph2 = toPhasor(com);
/*      */     }
/*      */     
/* 1264 */     return ph2;
/*      */   }
/*      */   
/*      */   public Phasor sinh(Phasor ph1)
/*      */   {
/* 1269 */     Phasor ph2 = new Phasor();
/* 1270 */     if (ph1.phaseInDeg == 0.0D) {
/* 1271 */       ph2.reset(Fmath.sinh(ph1.magnitude), 0.0D);
/*      */     }
/*      */     else {
/* 1274 */       Complex com = ph1.toRectangular();
/* 1275 */       com = Complex.sinh(com);
/* 1276 */       ph2 = toPhasor(com);
/*      */     }
/*      */     
/* 1279 */     return ph2;
/*      */   }
/*      */   
/*      */   public Phasor cosh(Phasor ph1)
/*      */   {
/* 1284 */     Phasor ph2 = new Phasor();
/* 1285 */     if (ph1.phaseInDeg == 0.0D) {
/* 1286 */       ph2.reset(Fmath.cosh(ph1.magnitude), 0.0D);
/*      */     }
/*      */     else {
/* 1289 */       Complex com = ph1.toRectangular();
/* 1290 */       com = Complex.cosh(com);
/* 1291 */       ph2 = toPhasor(com);
/*      */     }
/*      */     
/* 1294 */     return ph2;
/*      */   }
/*      */   
/*      */   public Phasor sech(Phasor ph1)
/*      */   {
/* 1299 */     Phasor ph2 = new Phasor();
/* 1300 */     if (ph1.phaseInDeg == 0.0D) {
/* 1301 */       ph2.reset(Fmath.sech(ph1.magnitude), 0.0D);
/*      */     }
/*      */     else {
/* 1304 */       Complex com = ph1.toRectangular();
/* 1305 */       com = Complex.sech(com);
/* 1306 */       ph2 = toPhasor(com);
/*      */     }
/*      */     
/* 1309 */     return ph2;
/*      */   }
/*      */   
/*      */   public Phasor csch(Phasor ph1)
/*      */   {
/* 1314 */     Phasor ph2 = new Phasor();
/* 1315 */     if (ph1.phaseInDeg == 0.0D) {
/* 1316 */       ph2.reset(Fmath.csch(ph1.magnitude), 0.0D);
/*      */     }
/*      */     else {
/* 1319 */       Complex com = ph1.toRectangular();
/* 1320 */       com = Complex.csch(com);
/* 1321 */       ph2 = toPhasor(com);
/*      */     }
/*      */     
/* 1324 */     return ph2;
/*      */   }
/*      */   
/*      */   public Phasor asin(Phasor ph1)
/*      */   {
/* 1329 */     Phasor ph2 = new Phasor();
/* 1330 */     if (ph1.phaseInDeg == 0.0D) {
/* 1331 */       ph2.reset(Math.asin(ph1.getMagnitude()), 0.0D);
/*      */     }
/*      */     else {
/* 1334 */       Complex com = ph1.toRectangular();
/* 1335 */       com = Complex.asin(com);
/* 1336 */       ph2 = toPhasor(com);
/*      */     }
/*      */     
/* 1339 */     return ph2;
/*      */   }
/*      */   
/*      */   public Phasor acos(Phasor ph1)
/*      */   {
/* 1344 */     Phasor ph2 = new Phasor();
/* 1345 */     if (ph1.phaseInDeg == 0.0D) {
/* 1346 */       ph2.reset(Math.acos(ph1.magnitude), 0.0D);
/*      */     }
/*      */     else {
/* 1349 */       Complex com = ph1.toRectangular();
/* 1350 */       com = Complex.acos(com);
/* 1351 */       ph2 = toPhasor(com);
/*      */     }
/*      */     
/* 1354 */     return ph2;
/*      */   }
/*      */   
/*      */   public Phasor atan(Phasor ph1)
/*      */   {
/* 1359 */     Phasor ph2 = new Phasor();
/* 1360 */     if (ph1.phaseInDeg == 0.0D) {
/* 1361 */       ph2.reset(Math.atan(ph1.magnitude), 0.0D);
/*      */     }
/*      */     else {
/* 1364 */       Complex com = ph1.toRectangular();
/* 1365 */       com = Complex.atan(com);
/* 1366 */       ph2 = toPhasor(com);
/*      */     }
/*      */     
/* 1369 */     return ph2;
/*      */   }
/*      */   
/*      */   public Phasor acot(Phasor ph1)
/*      */   {
/* 1374 */     Phasor ph2 = new Phasor();
/* 1375 */     if (ph1.phaseInDeg == 0.0D) {
/* 1376 */       ph2.reset(Fmath.acot(ph1.magnitude), 0.0D);
/*      */     }
/*      */     else {
/* 1379 */       Complex com = ph1.toRectangular();
/* 1380 */       com = Complex.acot(com);
/* 1381 */       ph2 = toPhasor(com);
/*      */     }
/*      */     
/* 1384 */     return ph2;
/*      */   }
/*      */   
/*      */   public Phasor asec(Phasor ph1)
/*      */   {
/* 1389 */     Phasor ph2 = new Phasor();
/* 1390 */     if (ph1.phaseInDeg == 0.0D) {
/* 1391 */       ph2.reset(Fmath.asec(ph1.magnitude), 0.0D);
/*      */     }
/*      */     else {
/* 1394 */       Complex com = ph1.toRectangular();
/* 1395 */       com = Complex.asec(com);
/* 1396 */       ph2 = toPhasor(com);
/*      */     }
/*      */     
/* 1399 */     return ph2;
/*      */   }
/*      */   
/*      */   public Phasor acsc(Phasor ph1)
/*      */   {
/* 1404 */     Phasor ph2 = new Phasor();
/* 1405 */     if (ph1.phaseInDeg == 0.0D) {
/* 1406 */       ph2.reset(Fmath.acsc(ph1.magnitude), 0.0D);
/*      */     }
/*      */     else {
/* 1409 */       Complex com = ph1.toRectangular();
/* 1410 */       com = Complex.acsc(com);
/* 1411 */       ph2 = toPhasor(com);
/*      */     }
/*      */     
/* 1414 */     return ph2;
/*      */   }
/*      */   
/*      */   public Phasor aexsec(Phasor ph1)
/*      */   {
/* 1419 */     Phasor ph2 = new Phasor();
/* 1420 */     if (ph1.phaseInDeg == 0.0D) {
/* 1421 */       ph2.reset(Fmath.aexsec(ph1.magnitude), 0.0D);
/*      */     }
/*      */     else {
/* 1424 */       Complex com = ph1.toRectangular();
/* 1425 */       com = Complex.aexsec(com);
/* 1426 */       ph2 = toPhasor(com);
/*      */     }
/*      */     
/* 1429 */     return ph2;
/*      */   }
/*      */   
/*      */   public Phasor avers(Phasor ph1)
/*      */   {
/* 1434 */     Phasor ph2 = new Phasor();
/* 1435 */     if (ph1.phaseInDeg == 0.0D) {
/* 1436 */       ph2.reset(Fmath.avers(ph1.magnitude), 0.0D);
/*      */     }
/*      */     else {
/* 1439 */       Complex com = ph1.toRectangular();
/* 1440 */       com = Complex.avers(com);
/* 1441 */       ph2 = toPhasor(com);
/*      */     }
/*      */     
/* 1444 */     return ph2;
/*      */   }
/*      */   
/*      */   public Phasor acovers(Phasor ph1)
/*      */   {
/* 1449 */     Phasor ph2 = new Phasor();
/* 1450 */     if (ph1.phaseInDeg == 0.0D) {
/* 1451 */       ph2.reset(Fmath.acovers(ph1.magnitude), 0.0D);
/*      */     }
/*      */     else {
/* 1454 */       Complex com = ph1.toRectangular();
/* 1455 */       com = Complex.acovers(com);
/* 1456 */       ph2 = toPhasor(com);
/*      */     }
/*      */     
/* 1459 */     return ph2;
/*      */   }
/*      */   
/*      */   public Phasor ahav(Phasor ph1)
/*      */   {
/* 1464 */     Phasor ph2 = new Phasor();
/* 1465 */     if (ph1.phaseInDeg == 0.0D) {
/* 1466 */       ph2.reset(Fmath.ahav(ph1.magnitude), 0.0D);
/*      */     }
/*      */     else {
/* 1469 */       Complex com = ph1.toRectangular();
/* 1470 */       com = Complex.ahav(com);
/* 1471 */       ph2 = toPhasor(com);
/*      */     }
/*      */     
/* 1474 */     return ph2;
/*      */   }
/*      */   
/*      */   public Phasor asinh(Phasor ph1)
/*      */   {
/* 1479 */     Phasor ph2 = new Phasor();
/* 1480 */     if (ph1.phaseInDeg == 0.0D) {
/* 1481 */       ph2.reset(Fmath.asinh(ph1.magnitude), 0.0D);
/*      */     }
/*      */     else {
/* 1484 */       Complex com = ph1.toRectangular();
/* 1485 */       com = Complex.asinh(com);
/* 1486 */       ph2 = toPhasor(com);
/*      */     }
/*      */     
/* 1489 */     return ph2;
/*      */   }
/*      */   
/*      */   public Phasor acosh(Phasor ph1)
/*      */   {
/* 1494 */     Phasor ph2 = new Phasor();
/* 1495 */     if (ph1.phaseInDeg == 0.0D) {
/* 1496 */       ph2.reset(Fmath.acosh(ph1.magnitude), 0.0D);
/*      */     }
/*      */     else {
/* 1499 */       Complex com = ph1.toRectangular();
/* 1500 */       com = Complex.acosh(com);
/* 1501 */       ph2 = toPhasor(com);
/*      */     }
/*      */     
/* 1504 */     return ph2;
/*      */   }
/*      */   
/*      */   public Phasor asech(Phasor ph1)
/*      */   {
/* 1509 */     Phasor ph2 = new Phasor();
/* 1510 */     if (ph1.phaseInDeg == 0.0D) {
/* 1511 */       ph2.reset(Fmath.asech(ph1.magnitude), 0.0D);
/*      */     }
/*      */     else {
/* 1514 */       Complex com = ph1.toRectangular();
/* 1515 */       com = Complex.asech(com);
/* 1516 */       ph2 = toPhasor(com);
/*      */     }
/*      */     
/* 1519 */     return ph2;
/*      */   }
/*      */   
/*      */   public Phasor acsch(Phasor ph1)
/*      */   {
/* 1524 */     Phasor ph2 = new Phasor();
/* 1525 */     if (ph1.phaseInDeg == 0.0D) {
/* 1526 */       ph2.reset(Fmath.acsch(ph1.magnitude), 0.0D);
/*      */     }
/*      */     else {
/* 1529 */       Complex com = ph1.toRectangular();
/* 1530 */       com = Complex.acsch(com);
/* 1531 */       ph2 = toPhasor(com);
/*      */     }
/*      */     
/* 1534 */     return ph2;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */   public boolean isReal()
/*      */   {
/* 1541 */     boolean test = false;
/* 1542 */     if (Math.abs(this.phaseInDeg) == 0.0D) test = true;
/* 1543 */     return test;
/*      */   }
/*      */   
/*      */ 
/*      */   public boolean isZero()
/*      */   {
/* 1549 */     boolean test = false;
/* 1550 */     if ((Math.abs(this.magnitude) == 0.0D) || (this.phaseInDeg == Double.NEGATIVE_INFINITY)) test = true;
/* 1551 */     return test;
/*      */   }
/*      */   
/*      */ 
/*      */   public boolean isPlusInfinity()
/*      */   {
/* 1557 */     boolean test = false;
/* 1558 */     if ((this.magnitude == Double.POSITIVE_INFINITY) || (this.phaseInDeg == Double.POSITIVE_INFINITY)) test = true;
/* 1559 */     return test;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */   public boolean isMinusInfinity()
/*      */   {
/* 1566 */     boolean test = false;
/* 1567 */     if (this.magnitude == Double.NEGATIVE_INFINITY) test = true;
/* 1568 */     return test;
/*      */   }
/*      */   
/*      */ 
/*      */   public boolean isNaN()
/*      */   {
/* 1574 */     boolean test = false;
/* 1575 */     if ((this.magnitude != this.magnitude) || (this.phaseInDeg != this.phaseInDeg)) test = true;
/* 1576 */     return test;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */   public boolean equals(Phasor a)
/*      */   {
/* 1584 */     boolean test = false;
/* 1585 */     if ((isNaN()) && (a.isNaN())) {
/* 1586 */       test = true;
/*      */ 
/*      */     }
/* 1589 */     else if ((this.magnitude == a.magnitude) && (this.phaseInDeg == a.phaseInDeg)) { test = true;
/*      */     }
/* 1591 */     return test;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public boolean equalsWithinLimits(Phasor a, double fract)
/*      */   {
/* 1600 */     boolean test = false;
/*      */     
/* 1602 */     double mt = this.magnitude;
/* 1603 */     double ma = a.magnitude;
/* 1604 */     double pt = this.phaseInDeg;
/* 1605 */     double pa = a.phaseInDeg;
/* 1606 */     double mdn = 0.0D;
/* 1607 */     double pdn = 0.0D;
/* 1608 */     double mtest = 0.0D;
/* 1609 */     double ptest = 0.0D;
/*      */     
/* 1611 */     if ((mt == 0.0D) && (pt == 0.0D) && (ma == 0.0D) && (pa == 0.0D)) test = true;
/* 1612 */     if (!test) {
/* 1613 */       mdn = Math.abs(mt);
/* 1614 */       if (Math.abs(ma) > mdn) mdn = Math.abs(ma);
/* 1615 */       if (mdn == 0.0D) {
/* 1616 */         mtest = 0.0D;
/*      */       }
/*      */       else {
/* 1619 */         mtest = Math.abs(ma - mt) / mdn;
/*      */       }
/* 1621 */       pdn = Math.abs(pt);
/* 1622 */       if (Math.abs(pa) > pdn) pdn = Math.abs(pa);
/* 1623 */       if (pdn == 0.0D) {
/* 1624 */         ptest = 0.0D;
/*      */       }
/*      */       else {
/* 1627 */         ptest = Math.abs(pa - pt) / pdn;
/*      */       }
/* 1629 */       if ((mtest < fract) && (ptest < fract)) { test = true;
/*      */       }
/*      */     }
/* 1632 */     return test;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */   public static Phasor zero()
/*      */   {
/* 1639 */     Phasor ph = new Phasor();
/* 1640 */     ph.magnitude = 0.0D;
/* 1641 */     ph.phaseInDeg = 0.0D;
/* 1642 */     ph.phaseInRad = 0.0D;
/* 1643 */     ph.rectangular.polar(ph.magnitude, ph.phaseInRad);
/* 1644 */     return ph;
/*      */   }
/*      */   
/*      */ 
/*      */   public static Phasor plusOne()
/*      */   {
/* 1650 */     Phasor ph = new Phasor();
/* 1651 */     ph.magnitude = 1.0D;
/* 1652 */     ph.phaseInDeg = 0.0D;
/* 1653 */     ph.phaseInRad = 0.0D;
/* 1654 */     ph.rectangular.polar(ph.magnitude, ph.phaseInRad);
/* 1655 */     return ph;
/*      */   }
/*      */   
/*      */ 
/*      */   public static Phasor minusOne()
/*      */   {
/* 1661 */     Phasor ph = new Phasor();
/* 1662 */     ph.magnitude = -1.0D;
/* 1663 */     ph.phaseInDeg = 0.0D;
/* 1664 */     ph.phaseInRad = 0.0D;
/* 1665 */     ph.rectangular.polar(ph.magnitude, ph.phaseInRad);
/* 1666 */     return ph;
/*      */   }
/*      */   
/*      */ 
/*      */   public static Phasor magnitudeZeroPhase(double mag)
/*      */   {
/* 1672 */     Phasor ph = new Phasor();
/* 1673 */     ph.magnitude = mag;
/* 1674 */     ph.phaseInDeg = 0.0D;
/* 1675 */     ph.phaseInRad = 0.0D;
/* 1676 */     ph.rectangular.polar(ph.magnitude, ph.phaseInRad);
/* 1677 */     return ph;
/*      */   }
/*      */   
/*      */ 
/*      */   public static Phasor plusInfinity()
/*      */   {
/* 1683 */     Phasor ph = new Phasor();
/* 1684 */     ph.magnitude = Double.POSITIVE_INFINITY;
/* 1685 */     ph.phaseInDeg = 0.0D;
/* 1686 */     ph.phaseInRad = 0.0D;
/* 1687 */     ph.rectangular = new Complex(Double.POSITIVE_INFINITY, Double.POSITIVE_INFINITY);
/* 1688 */     return ph;
/*      */   }
/*      */   
/*      */ 
/*      */   public static Phasor minusInfinity()
/*      */   {
/* 1694 */     Phasor ph = new Phasor();
/* 1695 */     ph.magnitude = Double.NEGATIVE_INFINITY;
/* 1696 */     ph.phaseInDeg = 0.0D;
/* 1697 */     ph.phaseInRad = 0.0D;
/* 1698 */     ph.rectangular = new Complex(Double.NEGATIVE_INFINITY, Double.NEGATIVE_INFINITY);
/* 1699 */     return ph;
/*      */   }
/*      */   
/*      */   public static Phasor resistancePhasor(double resistance)
/*      */   {
/* 1704 */     Phasor ph = new Phasor(resistance);
/* 1705 */     return ph;
/*      */   }
/*      */   
/*      */   public static Phasor inductancePhasor(double inductance, double frequency)
/*      */   {
/* 1710 */     if (Fmath.isNaN(frequency)) {
/* 1711 */       frequency = frequency;
/* 1712 */       omega = frequency * 2.0D * 3.141592653589793D;
/*      */     }
/*      */     else {
/* 1715 */       throw new IllegalArgumentException("You have already entered a value for the frequency, " + frequency + ", that differs from the one you are now attempting to enter, " + frequency);
/*      */     }
/* 1717 */     Complex com = Impedance.inductanceImpedance(inductance, omega);
/* 1718 */     Phasor ph = new Phasor();
/* 1719 */     return toPhasor(com);
/*      */   }
/*      */   
/*      */   public static Phasor capacitancePhasor(double capacitance, double frequency)
/*      */   {
/* 1724 */     if (Fmath.isNaN(frequency)) {
/* 1725 */       frequency = frequency;
/* 1726 */       omega = frequency * 2.0D * 3.141592653589793D;
/*      */     }
/*      */     else {
/* 1729 */       throw new IllegalArgumentException("You have already entered a value for the frequency, " + frequency + ", that differs from the one you are now attempting to enter, " + frequency);
/*      */     }
/* 1731 */     Complex com = Impedance.capacitanceImpedance(capacitance, omega);
/* 1732 */     Phasor ph = new Phasor();
/* 1733 */     return toPhasor(com);
/*      */   }
/*      */   
/*      */   public static Phasor infiniteWarburgPhasor(double sigma, double frequency)
/*      */   {
/* 1738 */     if (Fmath.isNaN(frequency)) {
/* 1739 */       frequency = frequency;
/* 1740 */       omega = frequency * 2.0D * 3.141592653589793D;
/*      */     }
/*      */     else {
/* 1743 */       throw new IllegalArgumentException("You have already entered a value for the frequency, " + frequency + ", that differs from the one you are now attempting to enter, " + frequency);
/*      */     }
/* 1745 */     Complex com = Impedance.infiniteWarburgImpedance(sigma, omega);
/* 1746 */     Phasor ph = new Phasor();
/* 1747 */     return toPhasor(com);
/*      */   }
/*      */   
/*      */   public static Phasor finiteWarburgPhasor(double sigma, double delta, double frequency)
/*      */   {
/* 1752 */     if (Fmath.isNaN(frequency)) {
/* 1753 */       frequency = frequency;
/* 1754 */       omega = frequency * 2.0D * 3.141592653589793D;
/*      */     }
/*      */     else {
/* 1757 */       throw new IllegalArgumentException("You have already entered a value for the frequency, " + frequency + ", that differs from the one you are now attempting to enter, " + frequency);
/*      */     }
/* 1759 */     Complex com = Impedance.finiteWarburgImpedance(sigma, delta, omega);
/* 1760 */     Phasor ph = new Phasor();
/* 1761 */     return toPhasor(com);
/*      */   }
/*      */   
/*      */   public static Phasor constantPhaseElementPhasor(double sigma, double alpha, double frequency)
/*      */   {
/* 1766 */     if (Fmath.isNaN(frequency)) {
/* 1767 */       frequency = frequency;
/* 1768 */       omega = frequency * 2.0D * 3.141592653589793D;
/*      */     }
/*      */     else {
/* 1771 */       throw new IllegalArgumentException("You have already entered a value for the frequency, " + frequency + ", that differs from the one you are now attempting to enter, " + frequency);
/*      */     }
/* 1773 */     Complex com = Impedance.constantPhaseElementImpedance(sigma, alpha, omega);
/* 1774 */     Phasor ph = new Phasor();
/* 1775 */     return toPhasor(com);
/*      */   }
/*      */ }


/* Location:              /home/menick/Scrivania/ProcessMining/CNMining/CNMining.jar!/flanagan/circuits/Phasor.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       0.7.1
 */