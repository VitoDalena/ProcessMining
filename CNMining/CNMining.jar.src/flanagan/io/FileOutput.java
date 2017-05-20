/*      */ package flanagan.io;
/*      */ 
/*      */ import flanagan.analysis.ErrorProp;
/*      */ import flanagan.circuits.Phasor;
/*      */ import flanagan.complex.Complex;
/*      */ import flanagan.complex.ComplexErrorProp;
/*      */ import java.io.BufferedReader;
/*      */ import java.io.BufferedWriter;
/*      */ import java.io.FileNotFoundException;
/*      */ import java.io.FileReader;
/*      */ import java.io.FileWriter;
/*      */ import java.io.IOException;
/*      */ import java.io.PrintStream;
/*      */ import java.io.PrintWriter;
/*      */ import java.math.BigDecimal;
/*      */ import java.math.BigInteger;
/*      */ import java.text.DateFormat;
/*      */ import java.util.Date;
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
/*      */ public class FileOutput
/*      */ {
/*   56 */   private String filename = "";
/*   57 */   private FileWriter fwoutput = null;
/*   58 */   private PrintWriter output = null;
/*   59 */   private boolean append = false;
/*   60 */   private char app = 'w';
/*      */   
/*      */ 
/*   63 */   private boolean fileExists = true;
/*      */   
/*      */ 
/*      */   public FileOutput(String filename, char app)
/*      */   {
/*   68 */     this.filename = filename;
/*   69 */     this.app = app;
/*   70 */     setFilenames(filename, app);
/*      */   }
/*      */   
/*      */   public FileOutput(String filename, String apps) {
/*   74 */     this.filename = filename;
/*   75 */     this.app = apps.charAt(0);
/*   76 */     setFilenames(filename, this.app);
/*      */   }
/*      */   
/*      */   public FileOutput(String filename) {
/*   80 */     this.filename = filename;
/*   81 */     this.app = 'w';
/*   82 */     setFilenames(filename, this.app);
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */   private void setFilenames(String filename, char app)
/*      */   {
/*      */     try
/*      */     {
/*   91 */       input0 = new BufferedReader(new FileReader(filename));
/*      */     } catch (FileNotFoundException e) { BufferedReader input0;
/*   93 */       this.fileExists = false;
/*      */     }
/*      */     
/*   96 */     if (this.app == 'n') {
/*   97 */       boolean test = true;
/*   98 */       int i = 0;
/*      */       
/*  100 */       String ext = "";
/*  101 */       String filename0 = "";
/*      */       
/*  103 */       int idot = filename.indexOf('.');
/*  104 */       if (idot != -1) {
/*  105 */         ext = ext + filename.substring(idot);
/*  106 */         filename0 = filename0 + filename.substring(0, idot);
/*      */       }
/*      */       else {
/*  109 */         filename0 = filename0 + filename;
/*      */       }
/*      */       
/*  112 */       while (test) {
/*  113 */         i++;
/*  114 */         filename = filename0 + String.valueOf(i) + ext;
/*      */         try {
/*  116 */           input = new BufferedReader(new FileReader(filename));
/*      */         } catch (FileNotFoundException e) { BufferedReader input;
/*  118 */           test = false;
/*  119 */           this.filename = filename;
/*      */         }
/*      */       }
/*      */     }
/*      */     
/*  124 */     if (this.app == 'a') {
/*  125 */       this.append = true;
/*      */     }
/*      */     else {
/*  128 */       this.append = false;
/*      */     }
/*      */     try {
/*  131 */       this.fwoutput = new FileWriter(filename, this.append);
/*      */     }
/*      */     catch (IOException e) {
/*  134 */       System.out.println(e);
/*      */     }
/*      */     
/*  137 */     this.output = new PrintWriter(new BufferedWriter(this.fwoutput));
/*      */   }
/*      */   
/*      */   public String getFilename()
/*      */   {
/*  142 */     return this.filename;
/*      */   }
/*      */   
/*      */ 
/*      */   public boolean checkFileAlreadyExists()
/*      */   {
/*  148 */     return this.fileExists;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */   public final synchronized void print(char ch)
/*      */   {
/*  156 */     this.output.print(ch);
/*      */   }
/*      */   
/*      */   public final synchronized void print(char ch, int f)
/*      */   {
/*  161 */     String ss = "";
/*  162 */     ss = ss + ch;
/*  163 */     ss = setField(ss, f);
/*  164 */     this.output.print(ss);
/*      */   }
/*      */   
/*      */   public final synchronized void print(String word)
/*      */   {
/*  169 */     this.output.print(word);
/*      */   }
/*      */   
/*      */ 
/*      */   public final synchronized void print(String word, int f)
/*      */   {
/*  175 */     String ss = "";
/*  176 */     ss = ss + word;
/*  177 */     ss = setField(ss, f);
/*  178 */     this.output.print(ss);
/*      */   }
/*      */   
/*      */   public final synchronized void print(double dd) {
/*  182 */     this.output.print(dd);
/*      */   }
/*      */   
/*      */   public final synchronized void print(double dd, int f)
/*      */   {
/*  187 */     String ss = "";
/*  188 */     ss = ss + dd;
/*  189 */     ss = setField(ss, f);
/*  190 */     this.output.print(ss);
/*      */   }
/*      */   
/*      */   public final synchronized void print(float ff)
/*      */   {
/*  195 */     this.output.print(ff);
/*      */   }
/*      */   
/*      */   public final synchronized void print(float ff, int f)
/*      */   {
/*  200 */     String ss = "";
/*  201 */     ss = ss + ff;
/*  202 */     ss = setField(ss, f);
/*  203 */     this.output.print(ss);
/*      */   }
/*      */   
/*      */   public final synchronized void print(BigDecimal big)
/*      */   {
/*  208 */     this.output.print(big.toString());
/*      */   }
/*      */   
/*      */   public final synchronized void print(BigDecimal big, int f)
/*      */   {
/*  213 */     String ss = "";
/*  214 */     ss = ss + big.toString();
/*  215 */     ss = setField(ss, f);
/*  216 */     this.output.print(ss);
/*      */   }
/*      */   
/*      */   public final synchronized void print(BigInteger big)
/*      */   {
/*  221 */     this.output.print(big.toString());
/*      */   }
/*      */   
/*      */   public final synchronized void print(BigInteger big, int f)
/*      */   {
/*  226 */     String ss = "";
/*  227 */     ss = ss + big.toString();
/*  228 */     ss = setField(ss, f);
/*  229 */     this.output.print(ss);
/*      */   }
/*      */   
/*      */   public final synchronized void print(Complex ff)
/*      */   {
/*  234 */     this.output.print(ff.toString());
/*      */   }
/*      */   
/*      */   public final synchronized void print(Complex ff, int f)
/*      */   {
/*  239 */     String ss = "";
/*  240 */     ss = ss + ff;
/*  241 */     ss = setField(ss, f);
/*  242 */     this.output.print(ss);
/*      */   }
/*      */   
/*      */   public final synchronized void print(Phasor ff)
/*      */   {
/*  247 */     this.output.print(ff.toString());
/*      */   }
/*      */   
/*      */   public final synchronized void print(Phasor ff, int f)
/*      */   {
/*  252 */     String ss = "";
/*  253 */     ss = ss + ff;
/*  254 */     ss = setField(ss, f);
/*  255 */     this.output.print(ss);
/*      */   }
/*      */   
/*      */   public final synchronized void print(ErrorProp ff)
/*      */   {
/*  260 */     this.output.print(ff.toString());
/*      */   }
/*      */   
/*      */   public final synchronized void print(ErrorProp ff, int f)
/*      */   {
/*  265 */     String ss = "";
/*  266 */     ss = ss + ff;
/*  267 */     ss = setField(ss, f);
/*  268 */     this.output.print(ss);
/*      */   }
/*      */   
/*      */   public final synchronized void print(ComplexErrorProp ff)
/*      */   {
/*  273 */     this.output.print(ff.toString());
/*      */   }
/*      */   
/*      */   public final synchronized void print(ComplexErrorProp ff, int f)
/*      */   {
/*  278 */     String ss = "";
/*  279 */     ss = ss + ff;
/*  280 */     ss = setField(ss, f);
/*  281 */     this.output.print(ss);
/*      */   }
/*      */   
/*      */   public final synchronized void print(int ii)
/*      */   {
/*  286 */     this.output.print(ii);
/*      */   }
/*      */   
/*      */   public final synchronized void print(int ii, int f)
/*      */   {
/*  291 */     String ss = "";
/*  292 */     ss = ss + ii;
/*  293 */     ss = setField(ss, f);
/*  294 */     this.output.print(ss);
/*      */   }
/*      */   
/*      */   public final synchronized void print(long ll)
/*      */   {
/*  299 */     this.output.print(ll);
/*      */   }
/*      */   
/*      */   public final synchronized void print(long ll, int f)
/*      */   {
/*  304 */     String ss = "";
/*  305 */     ss = ss + ll;
/*  306 */     ss = setField(ss, f);
/*  307 */     this.output.print(ss);
/*      */   }
/*      */   
/*      */   public final synchronized void print(short ss)
/*      */   {
/*  312 */     this.output.print(ss);
/*      */   }
/*      */   
/*      */   public final synchronized void print(short si, int f)
/*      */   {
/*  317 */     String ss = "";
/*  318 */     ss = ss + si;
/*  319 */     ss = setField(ss, f);
/*  320 */     this.output.print(ss);
/*      */   }
/*      */   
/*      */   public final synchronized void print(byte by)
/*      */   {
/*  325 */     this.output.print(by);
/*      */   }
/*      */   
/*      */   public final synchronized void print(byte by, int f)
/*      */   {
/*  330 */     String ss = "";
/*  331 */     ss = ss + by;
/*  332 */     ss = setField(ss, f);
/*  333 */     this.output.print(ss);
/*      */   }
/*      */   
/*      */   public final synchronized void print(boolean bb)
/*      */   {
/*  338 */     this.output.print(bb);
/*      */   }
/*      */   
/*      */   public final synchronized void print(boolean bb, int f)
/*      */   {
/*  343 */     String ss = "";
/*  344 */     ss = ss + bb;
/*  345 */     ss = setField(ss, f);
/*  346 */     this.output.print(ss);
/*      */   }
/*      */   
/*      */   public final synchronized void print(double[] array)
/*      */   {
/*  351 */     int n = array.length;
/*  352 */     for (int i = 0; i < n; i++) {
/*  353 */       this.output.print(array[i]);
/*      */     }
/*      */   }
/*      */   
/*      */   public final synchronized void print(float[] array)
/*      */   {
/*  359 */     int n = array.length;
/*  360 */     for (int i = 0; i < n; i++) {
/*  361 */       this.output.print(array[i]);
/*      */     }
/*      */   }
/*      */   
/*      */   public final synchronized void print(BigDecimal[] array)
/*      */   {
/*  367 */     int n = array.length;
/*  368 */     for (int i = 0; i < n; i++) {
/*  369 */       this.output.print(array[i]);
/*      */     }
/*      */   }
/*      */   
/*      */   public final synchronized void print(BigInteger[] array)
/*      */   {
/*  375 */     int n = array.length;
/*  376 */     for (int i = 0; i < n; i++) {
/*  377 */       this.output.print(array[i]);
/*      */     }
/*      */   }
/*      */   
/*      */   public final synchronized void print(int[] array)
/*      */   {
/*  383 */     int n = array.length;
/*  384 */     for (int i = 0; i < n; i++) {
/*  385 */       this.output.print(array[i]);
/*      */     }
/*      */   }
/*      */   
/*      */   public final synchronized void print(short[] array)
/*      */   {
/*  391 */     int n = array.length;
/*  392 */     for (int i = 0; i < n; i++) {
/*  393 */       this.output.print(array[i]);
/*      */     }
/*      */   }
/*      */   
/*      */   public final synchronized void print(byte[] array)
/*      */   {
/*  399 */     int n = array.length;
/*  400 */     for (int i = 0; i < n; i++) {
/*  401 */       this.output.print(array[i]);
/*      */     }
/*      */   }
/*      */   
/*      */   public final synchronized void print(char[] array)
/*      */   {
/*  407 */     int n = array.length;
/*  408 */     for (int i = 0; i < n; i++) {
/*  409 */       this.output.print(array[i]);
/*      */     }
/*      */   }
/*      */   
/*      */   public final synchronized void print(boolean[] array)
/*      */   {
/*  415 */     int n = array.length;
/*  416 */     for (int i = 0; i < n; i++) {
/*  417 */       this.output.print(array[i]);
/*      */     }
/*      */   }
/*      */   
/*      */   public final synchronized void print(String[] array)
/*      */   {
/*  423 */     int n = array.length;
/*  424 */     for (int i = 0; i < n; i++) {
/*  425 */       this.output.print(array[i]);
/*      */     }
/*      */   }
/*      */   
/*      */   public final synchronized void print(Complex[] array)
/*      */   {
/*  431 */     int n = array.length;
/*  432 */     for (int i = 0; i < n; i++) {
/*  433 */       this.output.print(array[i]);
/*      */     }
/*      */   }
/*      */   
/*      */   public final synchronized void print(Phasor[] array)
/*      */   {
/*  439 */     int n = array.length;
/*  440 */     for (int i = 0; i < n; i++) {
/*  441 */       this.output.print(array[i]);
/*      */     }
/*      */   }
/*      */   
/*      */   public final synchronized void print(ErrorProp[] array)
/*      */   {
/*  447 */     int n = array.length;
/*  448 */     for (int i = 0; i < n; i++) {
/*  449 */       this.output.print(array[i]);
/*      */     }
/*      */   }
/*      */   
/*      */   public final synchronized void print(ComplexErrorProp[] array)
/*      */   {
/*  455 */     int n = array.length;
/*  456 */     for (int i = 0; i < n; i++) {
/*  457 */       this.output.print(array[i]);
/*      */     }
/*      */   }
/*      */   
/*      */ 
/*      */   public final synchronized void print(double[] array, int f)
/*      */   {
/*  464 */     int n = array.length;
/*  465 */     for (int i = 0; i < n; i++) {
/*  466 */       String ss = "";
/*  467 */       ss = ss + array[i];
/*  468 */       ss = setField(ss, f);
/*  469 */       this.output.print(ss);
/*      */     }
/*      */   }
/*      */   
/*      */   public final synchronized void print(float[] array, int f)
/*      */   {
/*  475 */     int n = array.length;
/*  476 */     for (int i = 0; i < n; i++) {
/*  477 */       String ss = "";
/*  478 */       ss = ss + array[i];
/*  479 */       ss = setField(ss, f);
/*  480 */       this.output.print(ss);
/*      */     }
/*      */   }
/*      */   
/*      */   public final synchronized void print(BigDecimal[] array, int f)
/*      */   {
/*  486 */     int n = array.length;
/*  487 */     for (int i = 0; i < n; i++) {
/*  488 */       String ss = "";
/*  489 */       ss = ss + array[i];
/*  490 */       ss = setField(ss, f);
/*  491 */       this.output.print(ss);
/*      */     }
/*      */   }
/*      */   
/*      */   public final synchronized void print(BigInteger[] array, int f)
/*      */   {
/*  497 */     int n = array.length;
/*  498 */     for (int i = 0; i < n; i++) {
/*  499 */       String ss = "";
/*  500 */       ss = ss + array[i];
/*  501 */       ss = setField(ss, f);
/*  502 */       this.output.print(ss);
/*      */     }
/*      */   }
/*      */   
/*      */   public final synchronized void print(long[] array, int f)
/*      */   {
/*  508 */     int n = array.length;
/*  509 */     for (int i = 0; i < n; i++) {
/*  510 */       String ss = "";
/*  511 */       ss = ss + array[i];
/*  512 */       ss = setField(ss, f);
/*  513 */       this.output.print(ss);
/*      */     }
/*      */   }
/*      */   
/*      */   public final synchronized void print(int[] array, int f)
/*      */   {
/*  519 */     int n = array.length;
/*  520 */     for (int i = 0; i < n; i++) {
/*  521 */       String ss = "";
/*  522 */       ss = ss + array[i];
/*  523 */       ss = setField(ss, f);
/*  524 */       this.output.print(ss);
/*      */     }
/*      */   }
/*      */   
/*      */   public final synchronized void print(short[] array, int f)
/*      */   {
/*  530 */     int n = array.length;
/*  531 */     for (int i = 0; i < n; i++) {
/*  532 */       String ss = "";
/*  533 */       ss = ss + array[i];
/*  534 */       ss = setField(ss, f);
/*  535 */       this.output.print(ss);
/*      */     }
/*      */   }
/*      */   
/*      */   public final synchronized void print(byte[] array, int f)
/*      */   {
/*  541 */     int n = array.length;
/*  542 */     for (int i = 0; i < n; i++) {
/*  543 */       String ss = "";
/*  544 */       ss = ss + array[i];
/*  545 */       ss = setField(ss, f);
/*  546 */       this.output.print(ss);
/*      */     }
/*      */   }
/*      */   
/*      */   public final synchronized void print(char[] array, int f)
/*      */   {
/*  552 */     int n = array.length;
/*  553 */     for (int i = 0; i < n; i++) {
/*  554 */       String ss = "";
/*  555 */       ss = ss + array[i];
/*  556 */       ss = setField(ss, f);
/*  557 */       this.output.print(ss);
/*      */     }
/*      */   }
/*      */   
/*      */   public final synchronized void print(boolean[] array, int f)
/*      */   {
/*  563 */     int n = array.length;
/*  564 */     for (int i = 0; i < n; i++) {
/*  565 */       String ss = "";
/*  566 */       ss = ss + array[i];
/*  567 */       ss = setField(ss, f);
/*  568 */       this.output.print(ss);
/*      */     }
/*      */   }
/*      */   
/*      */   public final synchronized void print(String[] array, int f)
/*      */   {
/*  574 */     int n = array.length;
/*  575 */     for (int i = 0; i < n; i++) {
/*  576 */       String ss = "";
/*  577 */       ss = ss + array[i];
/*  578 */       ss = setField(ss, f);
/*  579 */       this.output.print(ss);
/*      */     }
/*      */   }
/*      */   
/*      */   public final synchronized void print(Complex[] array, int f)
/*      */   {
/*  585 */     int n = array.length;
/*  586 */     for (int i = 0; i < n; i++) {
/*  587 */       String ss = "";
/*  588 */       ss = ss + array[i];
/*  589 */       ss = setField(ss, f);
/*  590 */       this.output.print(ss);
/*      */     }
/*      */   }
/*      */   
/*      */   public final synchronized void print(Phasor[] array, int f)
/*      */   {
/*  596 */     int n = array.length;
/*  597 */     for (int i = 0; i < n; i++) {
/*  598 */       String ss = "";
/*  599 */       ss = ss + array[i];
/*  600 */       ss = setField(ss, f);
/*  601 */       this.output.print(ss);
/*      */     }
/*      */   }
/*      */   
/*      */   public final synchronized void print(ErrorProp[] array, int f)
/*      */   {
/*  607 */     int n = array.length;
/*  608 */     for (int i = 0; i < n; i++) {
/*  609 */       String ss = "";
/*  610 */       ss = ss + array[i];
/*  611 */       ss = setField(ss, f);
/*  612 */       this.output.print(ss);
/*      */     }
/*      */   }
/*      */   
/*      */   public final synchronized void print(ComplexErrorProp[] array, int f)
/*      */   {
/*  618 */     int n = array.length;
/*  619 */     for (int i = 0; i < n; i++) {
/*  620 */       String ss = "";
/*  621 */       ss = ss + array[i];
/*  622 */       ss = setField(ss, f);
/*  623 */       this.output.print(ss);
/*      */     }
/*      */   }
/*      */   
/*      */   public final synchronized void dateAndTime()
/*      */   {
/*  629 */     Date d = new Date();
/*  630 */     String day = DateFormat.getDateInstance().format(d);
/*  631 */     String tim = DateFormat.getTimeInstance().format(d);
/*      */     
/*  633 */     this.output.print("This file was created at ");
/*  634 */     this.output.print(tim);
/*  635 */     this.output.print(" on ");
/*  636 */     this.output.print(day);
/*      */   }
/*      */   
/*      */   public final synchronized void dateAndTime(String title)
/*      */   {
/*  641 */     Date d = new Date();
/*  642 */     String day = DateFormat.getDateInstance().format(d);
/*  643 */     String tim = DateFormat.getTimeInstance().format(d);
/*      */     
/*  645 */     this.output.print("This file, " + title + ", was created at ");
/*  646 */     this.output.print(tim);
/*  647 */     this.output.print(" on ");
/*  648 */     this.output.print(day);
/*      */   }
/*      */   
/*      */ 
/*      */   public final synchronized void printsp(char ch)
/*      */   {
/*  654 */     this.output.print(ch);
/*  655 */     this.output.print(" ");
/*      */   }
/*      */   
/*      */   public final synchronized void printsp(String word)
/*      */   {
/*  660 */     this.output.print(word + " ");
/*      */   }
/*      */   
/*      */   public final synchronized void printsp(double dd)
/*      */   {
/*  665 */     this.output.print(dd);
/*  666 */     this.output.print(" ");
/*      */   }
/*      */   
/*      */   public final synchronized void printsp(float ff)
/*      */   {
/*  671 */     this.output.print(ff);
/*  672 */     this.output.print(" ");
/*      */   }
/*      */   
/*      */   public final synchronized void printsp(BigDecimal big)
/*      */   {
/*  677 */     this.output.print(big.toString());
/*  678 */     this.output.print(" ");
/*      */   }
/*      */   
/*      */   public final synchronized void printsp(BigInteger big)
/*      */   {
/*  683 */     this.output.print(big.toString());
/*  684 */     this.output.print(" ");
/*      */   }
/*      */   
/*      */   public final synchronized void printsp(Complex ff)
/*      */   {
/*  689 */     this.output.print(ff.toString());
/*  690 */     this.output.print(" ");
/*      */   }
/*      */   
/*      */   public final synchronized void printsp(Phasor ff)
/*      */   {
/*  695 */     this.output.print(ff.toString());
/*  696 */     this.output.print(" ");
/*      */   }
/*      */   
/*      */   public final synchronized void printsp(ErrorProp ff)
/*      */   {
/*  701 */     this.output.print(ff.toString());
/*  702 */     this.output.print(" ");
/*      */   }
/*      */   
/*      */   public final synchronized void printsp(ComplexErrorProp ff)
/*      */   {
/*  707 */     this.output.print(ff.toString());
/*  708 */     this.output.print(" ");
/*      */   }
/*      */   
/*      */   public final synchronized void printsp(int ii)
/*      */   {
/*  713 */     this.output.print(ii);
/*  714 */     this.output.print(" ");
/*      */   }
/*      */   
/*      */   public final synchronized void printsp(long ll)
/*      */   {
/*  719 */     this.output.print(ll);
/*  720 */     this.output.print(" ");
/*      */   }
/*      */   
/*      */   public final synchronized void printsp(short ss)
/*      */   {
/*  725 */     this.output.print(ss);
/*  726 */     this.output.print(" ");
/*      */   }
/*      */   
/*      */   public final synchronized void printsp(byte by)
/*      */   {
/*  731 */     this.output.print(by);
/*  732 */     this.output.print(" ");
/*      */   }
/*      */   
/*      */   public final synchronized void printsp(boolean bb)
/*      */   {
/*  737 */     this.output.print(bb);
/*  738 */     this.output.print(" ");
/*      */   }
/*      */   
/*      */   public final synchronized void printsp()
/*      */   {
/*  743 */     this.output.print(" ");
/*      */   }
/*      */   
/*      */   public final synchronized void printsp(double[] array)
/*      */   {
/*  748 */     int n = array.length;
/*  749 */     for (int i = 0; i < n; i++) {
/*  750 */       this.output.print(array[i]);
/*  751 */       this.output.print(" ");
/*      */     }
/*      */   }
/*      */   
/*      */   public final synchronized void printsp(float[] array)
/*      */   {
/*  757 */     int n = array.length;
/*  758 */     for (int i = 0; i < n; i++) {
/*  759 */       this.output.print(array[i]);
/*  760 */       this.output.print(" ");
/*      */     }
/*      */   }
/*      */   
/*      */   public final synchronized void printsp(BigDecimal[] array)
/*      */   {
/*  766 */     int n = array.length;
/*  767 */     for (int i = 0; i < n; i++) {
/*  768 */       this.output.print(array[i]);
/*  769 */       this.output.print(" ");
/*      */     }
/*      */   }
/*      */   
/*      */   public final synchronized void printsp(BigInteger[] array)
/*      */   {
/*  775 */     int n = array.length;
/*  776 */     for (int i = 0; i < n; i++) {
/*  777 */       this.output.print(array[i]);
/*  778 */       this.output.print(" ");
/*      */     }
/*      */   }
/*      */   
/*      */   public final synchronized void printsp(long[] array)
/*      */   {
/*  784 */     int n = array.length;
/*  785 */     for (int i = 0; i < n; i++) {
/*  786 */       this.output.print(array[i]);
/*  787 */       this.output.print(" ");
/*      */     }
/*      */   }
/*      */   
/*      */   public final synchronized void printsp(int[] array)
/*      */   {
/*  793 */     int n = array.length;
/*  794 */     for (int i = 0; i < n; i++) {
/*  795 */       this.output.print(array[i]);
/*  796 */       this.output.print(" ");
/*      */     }
/*      */   }
/*      */   
/*      */   public final synchronized void printsp(char[] array)
/*      */   {
/*  802 */     int n = array.length;
/*  803 */     for (int i = 0; i < n; i++) {
/*  804 */       this.output.print(array[i]);
/*  805 */       this.output.print(" ");
/*      */     }
/*      */   }
/*      */   
/*      */   public final synchronized void printsp(short[] array)
/*      */   {
/*  811 */     int n = array.length;
/*  812 */     for (int i = 0; i < n; i++) {
/*  813 */       this.output.print(array[i]);
/*  814 */       this.output.print(" ");
/*      */     }
/*      */   }
/*      */   
/*      */   public final synchronized void printsp(byte[] array)
/*      */   {
/*  820 */     int n = array.length;
/*  821 */     for (int i = 0; i < n; i++) {
/*  822 */       this.output.print(array[i]);
/*  823 */       this.output.print(" ");
/*      */     }
/*      */   }
/*      */   
/*      */   public final synchronized void printsp(boolean[] array)
/*      */   {
/*  829 */     int n = array.length;
/*  830 */     for (int i = 0; i < n; i++) {
/*  831 */       this.output.print(array[i]);
/*  832 */       this.output.print(" ");
/*      */     }
/*      */   }
/*      */   
/*      */   public final synchronized void printsp(String[] array)
/*      */   {
/*  838 */     int n = array.length;
/*  839 */     for (int i = 0; i < n; i++) {
/*  840 */       this.output.print(array[i]);
/*  841 */       this.output.print(" ");
/*      */     }
/*      */   }
/*      */   
/*      */   public final synchronized void printsp(Complex[] array)
/*      */   {
/*  847 */     int n = array.length;
/*  848 */     for (int i = 0; i < n; i++) {
/*  849 */       this.output.print(array[i]);
/*  850 */       this.output.print(" ");
/*      */     }
/*      */   }
/*      */   
/*      */   public final synchronized void printsp(Phasor[] array)
/*      */   {
/*  856 */     int n = array.length;
/*  857 */     for (int i = 0; i < n; i++) {
/*  858 */       this.output.print(array[i]);
/*  859 */       this.output.print(" ");
/*      */     }
/*      */   }
/*      */   
/*      */   public final synchronized void printsp(ErrorProp[] array)
/*      */   {
/*  865 */     int n = array.length;
/*  866 */     for (int i = 0; i < n; i++) {
/*  867 */       this.output.print(array[i]);
/*  868 */       this.output.print(" ");
/*      */     }
/*      */   }
/*      */   
/*      */   public final synchronized void printsp(ComplexErrorProp[] array)
/*      */   {
/*  874 */     int n = array.length;
/*  875 */     for (int i = 0; i < n; i++) {
/*  876 */       this.output.print(array[i]);
/*  877 */       this.output.print(" ");
/*      */     }
/*      */   }
/*      */   
/*      */ 
/*      */   public final synchronized void dateAndTimesp()
/*      */   {
/*  884 */     Date d = new Date();
/*  885 */     String day = DateFormat.getDateInstance().format(d);
/*  886 */     String tim = DateFormat.getTimeInstance().format(d);
/*      */     
/*  888 */     this.output.print("This file was created at ");
/*  889 */     this.output.print(tim);
/*  890 */     this.output.print(" on ");
/*  891 */     this.output.print(day);
/*  892 */     this.output.print(" ");
/*      */   }
/*      */   
/*      */   public final synchronized void dateAndTimesp(String title)
/*      */   {
/*  897 */     Date d = new Date();
/*  898 */     String day = DateFormat.getDateInstance().format(d);
/*  899 */     String tim = DateFormat.getTimeInstance().format(d);
/*      */     
/*  901 */     this.output.print("This file, " + title + ", was created at ");
/*  902 */     this.output.print(tim);
/*  903 */     this.output.print(" on ");
/*  904 */     this.output.print(day);
/*  905 */     this.output.print(" ");
/*      */   }
/*      */   
/*      */ 
/*      */   public final synchronized void println(char ch)
/*      */   {
/*  911 */     this.output.println(ch);
/*      */   }
/*      */   
/*      */   public final synchronized void println(String word)
/*      */   {
/*  916 */     this.output.println(word);
/*      */   }
/*      */   
/*      */   public final synchronized void println(double dd)
/*      */   {
/*  921 */     this.output.println(dd);
/*      */   }
/*      */   
/*      */   public final synchronized void println(float ff)
/*      */   {
/*  926 */     this.output.println(ff);
/*      */   }
/*      */   
/*      */   public final synchronized void println(BigDecimal big)
/*      */   {
/*  931 */     this.output.println(big.toString());
/*      */   }
/*      */   
/*      */   public final synchronized void println(BigInteger big)
/*      */   {
/*  936 */     this.output.println(big.toString());
/*      */   }
/*      */   
/*      */   public final synchronized void println(Complex ff)
/*      */   {
/*  941 */     this.output.println(ff.toString());
/*      */   }
/*      */   
/*      */   public final synchronized void println(Phasor ff)
/*      */   {
/*  946 */     this.output.println(ff.toString());
/*      */   }
/*      */   
/*      */   public final synchronized void println(ErrorProp ff)
/*      */   {
/*  951 */     this.output.println(ff.toString());
/*      */   }
/*      */   
/*      */   public final synchronized void println(ComplexErrorProp ff)
/*      */   {
/*  956 */     this.output.println(ff.toString());
/*      */   }
/*      */   
/*      */   public final synchronized void println(int ii)
/*      */   {
/*  961 */     this.output.println(ii);
/*      */   }
/*      */   
/*      */   public final synchronized void println(long ll)
/*      */   {
/*  966 */     this.output.println(ll);
/*      */   }
/*      */   
/*      */   public final synchronized void println(short ss)
/*      */   {
/*  971 */     this.output.println(ss);
/*      */   }
/*      */   
/*      */   public final synchronized void println(byte by)
/*      */   {
/*  976 */     this.output.println(by);
/*      */   }
/*      */   
/*      */   public final synchronized void println(boolean bb)
/*      */   {
/*  981 */     this.output.println(bb);
/*      */   }
/*      */   
/*      */   public final synchronized void println()
/*      */   {
/*  986 */     this.output.println("");
/*      */   }
/*      */   
/*      */   public final synchronized void println(double[] array)
/*      */   {
/*  991 */     int n = array.length;
/*  992 */     for (int i = 0; i < n; i++) {
/*  993 */       this.output.println(array[i]);
/*      */     }
/*      */   }
/*      */   
/*      */   public final synchronized void println(float[] array)
/*      */   {
/*  999 */     int n = array.length;
/* 1000 */     for (int i = 0; i < n; i++) {
/* 1001 */       this.output.println(array[i]);
/*      */     }
/*      */   }
/*      */   
/*      */   public final synchronized void println(BigDecimal[] array)
/*      */   {
/* 1007 */     int n = array.length;
/* 1008 */     for (int i = 0; i < n; i++) {
/* 1009 */       this.output.println(array[i]);
/*      */     }
/*      */   }
/*      */   
/*      */   public final synchronized void println(BigInteger[] array)
/*      */   {
/* 1015 */     int n = array.length;
/* 1016 */     for (int i = 0; i < n; i++) {
/* 1017 */       this.output.println(array[i]);
/*      */     }
/*      */   }
/*      */   
/*      */   public final synchronized void println(long[] array)
/*      */   {
/* 1023 */     int n = array.length;
/* 1024 */     for (int i = 0; i < n; i++) {
/* 1025 */       this.output.println(array[i]);
/*      */     }
/*      */   }
/*      */   
/*      */   public final synchronized void println(int[] array)
/*      */   {
/* 1031 */     int n = array.length;
/* 1032 */     for (int i = 0; i < n; i++) {
/* 1033 */       this.output.println(array[i]);
/*      */     }
/*      */   }
/*      */   
/*      */   public final synchronized void println(short[] array)
/*      */   {
/* 1039 */     int n = array.length;
/* 1040 */     for (int i = 0; i < n; i++) {
/* 1041 */       this.output.println(array[i]);
/*      */     }
/*      */   }
/*      */   
/*      */   public final synchronized void println(byte[] array)
/*      */   {
/* 1047 */     int n = array.length;
/* 1048 */     for (int i = 0; i < n; i++) {
/* 1049 */       this.output.println(array[i]);
/*      */     }
/*      */   }
/*      */   
/*      */   public final synchronized void println(char[] array)
/*      */   {
/* 1055 */     int n = array.length;
/* 1056 */     for (int i = 0; i < n; i++) {
/* 1057 */       this.output.println(array[i]);
/*      */     }
/*      */   }
/*      */   
/*      */   public final synchronized void println(boolean[] array)
/*      */   {
/* 1063 */     int n = array.length;
/* 1064 */     for (int i = 0; i < n; i++) {
/* 1065 */       this.output.println(array[i]);
/*      */     }
/*      */   }
/*      */   
/*      */   public final synchronized void println(String[] array)
/*      */   {
/* 1071 */     int n = array.length;
/* 1072 */     for (int i = 0; i < n; i++) {
/* 1073 */       this.output.println(array[i]);
/*      */     }
/*      */   }
/*      */   
/*      */   public final synchronized void println(Complex[] array)
/*      */   {
/* 1079 */     int n = array.length;
/* 1080 */     for (int i = 0; i < n; i++) {
/* 1081 */       this.output.println(array[i]);
/*      */     }
/*      */   }
/*      */   
/*      */   public final synchronized void println(Phasor[] array)
/*      */   {
/* 1087 */     int n = array.length;
/* 1088 */     for (int i = 0; i < n; i++) {
/* 1089 */       this.output.println(array[i]);
/*      */     }
/*      */   }
/*      */   
/*      */   public final synchronized void println(ErrorProp[] array)
/*      */   {
/* 1095 */     int n = array.length;
/* 1096 */     for (int i = 0; i < n; i++) {
/* 1097 */       this.output.println(array[i]);
/*      */     }
/*      */   }
/*      */   
/*      */   public final synchronized void println(ComplexErrorProp[] array)
/*      */   {
/* 1103 */     int n = array.length;
/* 1104 */     for (int i = 0; i < n; i++) {
/* 1105 */       this.output.println(array[i]);
/*      */     }
/*      */   }
/*      */   
/*      */ 
/*      */   public final synchronized void dateAndTimeln()
/*      */   {
/* 1112 */     Date d = new Date();
/* 1113 */     String day = DateFormat.getDateInstance().format(d);
/* 1114 */     String tim = DateFormat.getTimeInstance().format(d);
/*      */     
/* 1116 */     this.output.print("This file, " + this.filename + ", was created at ");
/* 1117 */     this.output.print(tim);
/* 1118 */     this.output.print(" on ");
/* 1119 */     this.output.println(day);
/*      */   }
/*      */   
/*      */   public final synchronized void dateAndTimeln(String title)
/*      */   {
/* 1124 */     Date d = new Date();
/* 1125 */     String day = DateFormat.getDateInstance().format(d);
/* 1126 */     String tim = DateFormat.getTimeInstance().format(d);
/*      */     
/* 1128 */     this.output.print("This file, " + title + ", was created at ");
/* 1129 */     this.output.print(tim);
/* 1130 */     this.output.print(" on ");
/* 1131 */     this.output.println(day);
/*      */   }
/*      */   
/*      */ 
/*      */   public final synchronized void printtab(char ch)
/*      */   {
/* 1137 */     this.output.print(ch);
/* 1138 */     this.output.print("\t");
/*      */   }
/*      */   
/*      */   public final synchronized void printtab(char ch, int f)
/*      */   {
/* 1143 */     String ss = "";
/* 1144 */     ss = ss + ch;
/* 1145 */     ss = setField(ss, f);
/* 1146 */     this.output.print(ss);
/* 1147 */     this.output.print("\t");
/*      */   }
/*      */   
/*      */   public final synchronized void printtab(String word)
/*      */   {
/* 1152 */     this.output.print(word + "\t");
/*      */   }
/*      */   
/*      */   public final synchronized void printtab(String word, int f)
/*      */   {
/* 1157 */     String ss = "";
/* 1158 */     ss = ss + word;
/* 1159 */     ss = setField(ss, f);
/* 1160 */     this.output.print(ss);
/* 1161 */     this.output.print("\t");
/*      */   }
/*      */   
/*      */   public final synchronized void printtab(double dd)
/*      */   {
/* 1166 */     this.output.print(dd);
/* 1167 */     this.output.print("\t");
/*      */   }
/*      */   
/*      */   public final synchronized void printtab(double dd, int f)
/*      */   {
/* 1172 */     String ss = "";
/* 1173 */     ss = ss + dd;
/* 1174 */     ss = setField(ss, f);
/* 1175 */     this.output.print(ss);
/* 1176 */     this.output.print("\t");
/*      */   }
/*      */   
/*      */   public final synchronized void printtab(float ff)
/*      */   {
/* 1181 */     this.output.print(ff);
/* 1182 */     this.output.print("\t");
/*      */   }
/*      */   
/*      */   public final synchronized void printtab(float ff, int f)
/*      */   {
/* 1187 */     String ss = "";
/* 1188 */     ss = ss + ff;
/* 1189 */     ss = setField(ss, f);
/* 1190 */     this.output.print(ss);
/* 1191 */     this.output.print("\t");
/*      */   }
/*      */   
/*      */   public final synchronized void printtab(BigDecimal big)
/*      */   {
/* 1196 */     this.output.print(big.toString());
/* 1197 */     this.output.print("\t");
/*      */   }
/*      */   
/*      */   public final synchronized void printtab(BigDecimal big, int f)
/*      */   {
/* 1202 */     String ss = "";
/* 1203 */     ss = ss + big.toString();
/* 1204 */     ss = setField(ss, f);
/* 1205 */     this.output.print(ss);
/* 1206 */     this.output.print("\t");
/*      */   }
/*      */   
/*      */   public final synchronized void printtab(BigInteger big)
/*      */   {
/* 1211 */     this.output.print(big.toString());
/* 1212 */     this.output.print("\t");
/*      */   }
/*      */   
/*      */   public final synchronized void printtab(BigInteger big, int f)
/*      */   {
/* 1217 */     String ss = "";
/* 1218 */     ss = ss + big.toString();
/* 1219 */     ss = setField(ss, f);
/* 1220 */     this.output.print(ss);
/* 1221 */     this.output.print("\t");
/*      */   }
/*      */   
/*      */   public final synchronized void printtab(Complex ff)
/*      */   {
/* 1226 */     this.output.print(ff.toString());
/* 1227 */     this.output.print("\t");
/*      */   }
/*      */   
/*      */   public final synchronized void printtab(Phasor ff)
/*      */   {
/* 1232 */     this.output.print(ff.toString());
/* 1233 */     this.output.print("\t");
/*      */   }
/*      */   
/*      */   public final synchronized void printtab(Complex ff, int f)
/*      */   {
/* 1238 */     String ss = "";
/* 1239 */     ss = ss + ff;
/* 1240 */     ss = setField(ss, f);
/* 1241 */     this.output.print(ss);
/* 1242 */     this.output.print("\t");
/*      */   }
/*      */   
/*      */   public final synchronized void printtab(Phasor ff, int f)
/*      */   {
/* 1247 */     String ss = "";
/* 1248 */     ss = ss + ff;
/* 1249 */     ss = setField(ss, f);
/* 1250 */     this.output.print(ss);
/* 1251 */     this.output.print("\t");
/*      */   }
/*      */   
/*      */   public final synchronized void printtab(ErrorProp ff)
/*      */   {
/* 1256 */     this.output.print(ff.toString());
/* 1257 */     this.output.print("\t");
/*      */   }
/*      */   
/*      */   public final synchronized void printtab(ErrorProp ff, int f)
/*      */   {
/* 1262 */     String ss = "";
/* 1263 */     ss = ss + ff;
/* 1264 */     ss = setField(ss, f);
/* 1265 */     this.output.print(ss);
/* 1266 */     this.output.print("\t");
/*      */   }
/*      */   
/*      */   public final synchronized void printtab(ComplexErrorProp ff)
/*      */   {
/* 1271 */     this.output.print(ff.toString());
/* 1272 */     this.output.print("\t");
/*      */   }
/*      */   
/*      */   public final synchronized void printtab(ComplexErrorProp ff, int f)
/*      */   {
/* 1277 */     String ss = "";
/* 1278 */     ss = ss + ff;
/* 1279 */     ss = setField(ss, f);
/* 1280 */     this.output.print(ss);
/* 1281 */     this.output.print("\t");
/*      */   }
/*      */   
/*      */   public final synchronized void printtab(int ii)
/*      */   {
/* 1286 */     this.output.print(ii);
/* 1287 */     this.output.print("\t");
/*      */   }
/*      */   
/*      */   public final synchronized void printtab(int ii, int f)
/*      */   {
/* 1292 */     String ss = "";
/* 1293 */     ss = ss + ii;
/* 1294 */     ss = setField(ss, f);
/* 1295 */     this.output.print(ss);
/* 1296 */     this.output.print("\t");
/*      */   }
/*      */   
/*      */   public final synchronized void printtab(long ll)
/*      */   {
/* 1301 */     this.output.print(ll);
/* 1302 */     this.output.print("\t");
/*      */   }
/*      */   
/*      */   public final synchronized void printtab(long ll, int f)
/*      */   {
/* 1307 */     String ss = "";
/* 1308 */     ss = ss + ll;
/* 1309 */     ss = setField(ss, f);
/* 1310 */     this.output.print(ss);
/* 1311 */     this.output.print("\t");
/*      */   }
/*      */   
/*      */   public final synchronized void printtab(short ss)
/*      */   {
/* 1316 */     this.output.print(ss);
/* 1317 */     this.output.print("\t");
/*      */   }
/*      */   
/*      */   public final synchronized void printtab(short si, int f)
/*      */   {
/* 1322 */     String ss = "";
/* 1323 */     ss = ss + si;
/* 1324 */     ss = setField(ss, f);
/* 1325 */     this.output.print(ss);
/* 1326 */     this.output.print("\t");
/*      */   }
/*      */   
/*      */   public final synchronized void printtab(byte by)
/*      */   {
/* 1331 */     this.output.print(by);
/* 1332 */     this.output.print("\t");
/*      */   }
/*      */   
/*      */   public final synchronized void printtab(byte by, int f)
/*      */   {
/* 1337 */     String ss = "";
/* 1338 */     ss = ss + by;
/* 1339 */     ss = setField(ss, f);
/* 1340 */     this.output.print(ss);
/* 1341 */     this.output.print("\t");
/*      */   }
/*      */   
/*      */   public final synchronized void printtab(boolean bb)
/*      */   {
/* 1346 */     this.output.print(bb);
/* 1347 */     this.output.print("\t");
/*      */   }
/*      */   
/*      */   public final synchronized void printtab(boolean bb, int f)
/*      */   {
/* 1352 */     String ss = "";
/* 1353 */     ss = ss + bb;
/* 1354 */     ss = setField(ss, f);
/* 1355 */     this.output.print(ss);
/* 1356 */     this.output.print("\t");
/*      */   }
/*      */   
/*      */   public final synchronized void printtab()
/*      */   {
/* 1361 */     this.output.print("\t");
/*      */   }
/*      */   
/*      */   public final synchronized void printtab(double[] array, int f)
/*      */   {
/* 1366 */     int n = array.length;
/* 1367 */     for (int i = 0; i < n; i++) {
/* 1368 */       String ss = "";
/* 1369 */       ss = ss + array[i];
/* 1370 */       ss = setField(ss, f);
/* 1371 */       this.output.print(ss);
/* 1372 */       this.output.print("\t");
/*      */     }
/*      */   }
/*      */   
/*      */   public final synchronized void printtab(float[] array, int f)
/*      */   {
/* 1378 */     int n = array.length;
/* 1379 */     for (int i = 0; i < n; i++) {
/* 1380 */       String ss = "";
/* 1381 */       ss = ss + array[i];
/* 1382 */       ss = setField(ss, f);
/* 1383 */       this.output.print(ss);
/* 1384 */       this.output.print("\t");
/*      */     }
/*      */   }
/*      */   
/*      */   public final synchronized void printtab(BigDecimal[] array, int f)
/*      */   {
/* 1390 */     int n = array.length;
/* 1391 */     for (int i = 0; i < n; i++) {
/* 1392 */       String ss = "";
/* 1393 */       ss = ss + array[i].toString();
/* 1394 */       ss = setField(ss, f);
/* 1395 */       this.output.print(ss);
/* 1396 */       this.output.print("\t");
/*      */     }
/*      */   }
/*      */   
/*      */   public final synchronized void printtab(BigInteger[] array, int f)
/*      */   {
/* 1402 */     int n = array.length;
/* 1403 */     for (int i = 0; i < n; i++) {
/* 1404 */       String ss = "";
/* 1405 */       ss = ss + array[i].toString();
/* 1406 */       ss = setField(ss, f);
/* 1407 */       this.output.print(ss);
/* 1408 */       this.output.print("\t");
/*      */     }
/*      */   }
/*      */   
/*      */ 
/*      */   public final synchronized void printtab(long[] array, int f)
/*      */   {
/* 1415 */     int n = array.length;
/* 1416 */     for (int i = 0; i < n; i++) {
/* 1417 */       String ss = "";
/* 1418 */       ss = ss + array[i];
/* 1419 */       ss = setField(ss, f);
/* 1420 */       this.output.print(ss);
/* 1421 */       this.output.print("\t");
/*      */     }
/*      */   }
/*      */   
/*      */   public final synchronized void printtab(int[] array, int f)
/*      */   {
/* 1427 */     int n = array.length;
/* 1428 */     for (int i = 0; i < n; i++) {
/* 1429 */       String ss = "";
/* 1430 */       ss = ss + array[i];
/* 1431 */       ss = setField(ss, f);
/* 1432 */       this.output.print(ss);
/* 1433 */       this.output.print("\t");
/*      */     }
/*      */   }
/*      */   
/*      */   public final synchronized void printtab(short[] array, int f)
/*      */   {
/* 1439 */     int n = array.length;
/* 1440 */     for (int i = 0; i < n; i++) {
/* 1441 */       String ss = "";
/* 1442 */       ss = ss + array[i];
/* 1443 */       ss = setField(ss, f);
/* 1444 */       this.output.print(ss);
/* 1445 */       this.output.print("\t");
/*      */     }
/*      */   }
/*      */   
/*      */   public final synchronized void printtab(byte[] array, int f)
/*      */   {
/* 1451 */     int n = array.length;
/* 1452 */     for (int i = 0; i < n; i++) {
/* 1453 */       String ss = "";
/* 1454 */       ss = ss + array[i];
/* 1455 */       ss = setField(ss, f);
/* 1456 */       this.output.print(ss);
/* 1457 */       this.output.print("\t");
/*      */     }
/*      */   }
/*      */   
/*      */   public final synchronized void printtab(char[] array, int f)
/*      */   {
/* 1463 */     int n = array.length;
/* 1464 */     for (int i = 0; i < n; i++) {
/* 1465 */       String ss = "";
/* 1466 */       ss = ss + array[i];
/* 1467 */       ss = setField(ss, f);
/* 1468 */       this.output.print(ss);
/* 1469 */       this.output.print("\t");
/*      */     }
/*      */   }
/*      */   
/*      */   public final synchronized void printtab(boolean[] array, int f)
/*      */   {
/* 1475 */     int n = array.length;
/* 1476 */     for (int i = 0; i < n; i++) {
/* 1477 */       String ss = "";
/* 1478 */       ss = ss + array[i];
/* 1479 */       ss = setField(ss, f);
/* 1480 */       this.output.print(ss);
/* 1481 */       this.output.print("\t");
/*      */     }
/*      */   }
/*      */   
/*      */   public final synchronized void printtab(String[] array, int f)
/*      */   {
/* 1487 */     int n = array.length;
/* 1488 */     for (int i = 0; i < n; i++) {
/* 1489 */       String ss = "";
/* 1490 */       ss = ss + array[i];
/* 1491 */       ss = setField(ss, f);
/* 1492 */       this.output.print(ss);
/* 1493 */       this.output.print("\t");
/*      */     }
/*      */   }
/*      */   
/*      */   public final synchronized void printtab(Complex[] array, int f)
/*      */   {
/* 1499 */     int n = array.length;
/* 1500 */     for (int i = 0; i < n; i++) {
/* 1501 */       String ss = "";
/* 1502 */       ss = ss + array[i];
/* 1503 */       ss = setField(ss, f);
/* 1504 */       this.output.print(ss);
/* 1505 */       this.output.print("\t");
/*      */     }
/*      */   }
/*      */   
/*      */   public final synchronized void printtab(Phasor[] array, int f)
/*      */   {
/* 1511 */     int n = array.length;
/* 1512 */     for (int i = 0; i < n; i++) {
/* 1513 */       String ss = "";
/* 1514 */       ss = ss + array[i];
/* 1515 */       ss = setField(ss, f);
/* 1516 */       this.output.print(ss);
/* 1517 */       this.output.print("\t");
/*      */     }
/*      */   }
/*      */   
/*      */   public final synchronized void printtab(ErrorProp[] array, int f)
/*      */   {
/* 1523 */     int n = array.length;
/* 1524 */     for (int i = 0; i < n; i++) {
/* 1525 */       String ss = "";
/* 1526 */       ss = ss + array[i];
/* 1527 */       ss = setField(ss, f);
/* 1528 */       this.output.print(ss);
/* 1529 */       this.output.print("\t");
/*      */     }
/*      */   }
/*      */   
/*      */   public final synchronized void printtab(ComplexErrorProp[] array, int f)
/*      */   {
/* 1535 */     int n = array.length;
/* 1536 */     for (int i = 0; i < n; i++) {
/* 1537 */       String ss = "";
/* 1538 */       ss = ss + array[i];
/* 1539 */       ss = setField(ss, f);
/* 1540 */       this.output.print(ss);
/* 1541 */       this.output.print("\t");
/*      */     }
/*      */   }
/*      */   
/*      */   public final synchronized void printtab(double[] array)
/*      */   {
/* 1547 */     int n = array.length;
/* 1548 */     for (int i = 0; i < n; i++) {
/* 1549 */       this.output.print(array[i]);
/* 1550 */       this.output.print("\t");
/*      */     }
/*      */   }
/*      */   
/*      */   public final synchronized void printtab(float[] array)
/*      */   {
/* 1556 */     int n = array.length;
/* 1557 */     for (int i = 0; i < n; i++) {
/* 1558 */       this.output.print(array[i]);
/* 1559 */       this.output.print("\t");
/*      */     }
/*      */   }
/*      */   
/*      */   public final synchronized void printtab(BigDecimal[] array)
/*      */   {
/* 1565 */     int n = array.length;
/* 1566 */     for (int i = 0; i < n; i++) {
/* 1567 */       this.output.print(array[i].toString());
/* 1568 */       this.output.print("\t");
/*      */     }
/*      */   }
/*      */   
/*      */   public final synchronized void printtab(BigInteger[] array)
/*      */   {
/* 1574 */     int n = array.length;
/* 1575 */     for (int i = 0; i < n; i++) {
/* 1576 */       this.output.print(array[i].toString());
/* 1577 */       this.output.print("\t");
/*      */     }
/*      */   }
/*      */   
/*      */   public final synchronized void printtab(long[] array)
/*      */   {
/* 1583 */     int n = array.length;
/* 1584 */     for (int i = 0; i < n; i++) {
/* 1585 */       this.output.print(array[i]);
/* 1586 */       this.output.print("\t");
/*      */     }
/*      */   }
/*      */   
/*      */   public final synchronized void printtab(int[] array)
/*      */   {
/* 1592 */     int n = array.length;
/* 1593 */     for (int i = 0; i < n; i++) {
/* 1594 */       this.output.print(array[i]);
/* 1595 */       this.output.print("\t");
/*      */     }
/*      */   }
/*      */   
/*      */   public final synchronized void printtab(char[] array)
/*      */   {
/* 1601 */     int n = array.length;
/* 1602 */     for (int i = 0; i < n; i++) {
/* 1603 */       this.output.print(array[i]);
/* 1604 */       this.output.print("\t");
/*      */     }
/*      */   }
/*      */   
/*      */   public final synchronized void printtab(boolean[] array)
/*      */   {
/* 1610 */     int n = array.length;
/* 1611 */     for (int i = 0; i < n; i++) {
/* 1612 */       this.output.print(array[i]);
/* 1613 */       this.output.print("\t");
/*      */     }
/*      */   }
/*      */   
/*      */   public final synchronized void printtab(String[] array)
/*      */   {
/* 1619 */     int n = array.length;
/* 1620 */     for (int i = 0; i < n; i++) {
/* 1621 */       this.output.print(array[i]);
/* 1622 */       this.output.print("\t");
/*      */     }
/*      */   }
/*      */   
/*      */   public final synchronized void printtab(Complex[] array)
/*      */   {
/* 1628 */     int n = array.length;
/* 1629 */     for (int i = 0; i < n; i++) {
/* 1630 */       this.output.print(array[i]);
/* 1631 */       this.output.print("\t");
/*      */     }
/*      */   }
/*      */   
/*      */   public final synchronized void printtab(Phasor[] array)
/*      */   {
/* 1637 */     int n = array.length;
/* 1638 */     for (int i = 0; i < n; i++) {
/* 1639 */       this.output.print(array[i]);
/* 1640 */       this.output.print("\t");
/*      */     }
/*      */   }
/*      */   
/*      */   public final synchronized void printtab(ErrorProp[] array)
/*      */   {
/* 1646 */     int n = array.length;
/* 1647 */     for (int i = 0; i < n; i++) {
/* 1648 */       this.output.print(array[i]);
/* 1649 */       this.output.print("\t");
/*      */     }
/*      */   }
/*      */   
/*      */   public final synchronized void printtab(ComplexErrorProp[] array)
/*      */   {
/* 1655 */     int n = array.length;
/* 1656 */     for (int i = 0; i < n; i++) {
/* 1657 */       this.output.print(array[i]);
/* 1658 */       this.output.print("\t");
/*      */     }
/*      */   }
/*      */   
/*      */ 
/*      */   public final synchronized void dateAndTimetab()
/*      */   {
/* 1665 */     Date d = new Date();
/* 1666 */     String day = DateFormat.getDateInstance().format(d);
/* 1667 */     String tim = DateFormat.getTimeInstance().format(d);
/*      */     
/* 1669 */     this.output.print("This file was created at ");
/* 1670 */     this.output.print(tim);
/* 1671 */     this.output.print(" on ");
/* 1672 */     this.output.print(day);
/* 1673 */     this.output.print("\t");
/*      */   }
/*      */   
/*      */   public final synchronized void dateAndTimetab(String title)
/*      */   {
/* 1678 */     Date d = new Date();
/* 1679 */     String day = DateFormat.getDateInstance().format(d);
/* 1680 */     String tim = DateFormat.getTimeInstance().format(d);
/*      */     
/* 1682 */     this.output.print("This file, " + title + ", was created at ");
/* 1683 */     this.output.print(tim);
/* 1684 */     this.output.print(" on ");
/* 1685 */     this.output.print(day);
/* 1686 */     this.output.print("\t");
/*      */   }
/*      */   
/*      */ 
/*      */   public final synchronized void printcomma(char ch)
/*      */   {
/* 1692 */     this.output.print(ch);
/* 1693 */     this.output.print(",");
/*      */   }
/*      */   
/*      */   public final synchronized void printcomma(String word)
/*      */   {
/* 1698 */     this.output.print(word + ",");
/*      */   }
/*      */   
/*      */   public final synchronized void printcomma(double dd)
/*      */   {
/* 1703 */     this.output.print(dd);
/* 1704 */     this.output.print(",");
/*      */   }
/*      */   
/*      */   public final synchronized void printcomma(float ff)
/*      */   {
/* 1709 */     this.output.print(ff);
/* 1710 */     this.output.print(",");
/*      */   }
/*      */   
/*      */   public final synchronized void printcomma(BigDecimal big)
/*      */   {
/* 1715 */     this.output.print(big.toString());
/* 1716 */     this.output.print(",");
/*      */   }
/*      */   
/*      */   public final synchronized void printcomma(BigInteger big)
/*      */   {
/* 1721 */     this.output.print(big.toString());
/* 1722 */     this.output.print(",");
/*      */   }
/*      */   
/*      */   public final synchronized void printcomma(Complex ff)
/*      */   {
/* 1727 */     this.output.print(ff.toString());
/* 1728 */     this.output.print(",");
/*      */   }
/*      */   
/*      */   public final synchronized void printcomma(Phasor ff)
/*      */   {
/* 1733 */     this.output.print(ff.toString());
/* 1734 */     this.output.print(",");
/*      */   }
/*      */   
/*      */   public final synchronized void printcomma(ErrorProp ff)
/*      */   {
/* 1739 */     this.output.print(ff.toString());
/* 1740 */     this.output.print(",");
/*      */   }
/*      */   
/*      */   public final synchronized void printcomma(ComplexErrorProp ff)
/*      */   {
/* 1745 */     this.output.print(ff.toString());
/* 1746 */     this.output.print(",");
/*      */   }
/*      */   
/*      */   public final synchronized void printcomma(int ii)
/*      */   {
/* 1751 */     this.output.print(ii);
/* 1752 */     this.output.print(",");
/*      */   }
/*      */   
/*      */   public final synchronized void printcomma(long ll)
/*      */   {
/* 1757 */     this.output.print(ll);
/* 1758 */     this.output.print(",");
/*      */   }
/*      */   
/*      */   public final synchronized void printcomma(boolean bb)
/*      */   {
/* 1763 */     this.output.print(bb);
/* 1764 */     this.output.print(",");
/*      */   }
/*      */   
/*      */   public final synchronized void printcomma(short ss)
/*      */   {
/* 1769 */     this.output.print(ss);
/* 1770 */     this.output.print(",");
/*      */   }
/*      */   
/*      */   public final synchronized void printcomma(byte by)
/*      */   {
/* 1775 */     this.output.print(by);
/* 1776 */     this.output.print(",");
/*      */   }
/*      */   
/*      */   public final synchronized void printcomma()
/*      */   {
/* 1781 */     this.output.print(",");
/*      */   }
/*      */   
/*      */   public final synchronized void printcomma(double[] array)
/*      */   {
/* 1786 */     int n = array.length;
/* 1787 */     for (int i = 0; i < n; i++) {
/* 1788 */       this.output.print(array[i]);
/* 1789 */       this.output.print(",");
/*      */     }
/*      */   }
/*      */   
/*      */   public final synchronized void printcomma(float[] array)
/*      */   {
/* 1795 */     int n = array.length;
/* 1796 */     for (int i = 0; i < n; i++) {
/* 1797 */       this.output.print(array[i]);
/* 1798 */       this.output.print(",");
/*      */     }
/*      */   }
/*      */   
/*      */   public final synchronized void printcomma(BigDecimal[] array)
/*      */   {
/* 1804 */     int n = array.length;
/* 1805 */     for (int i = 0; i < n; i++) {
/* 1806 */       this.output.print(array[i].toString());
/* 1807 */       this.output.print(",");
/*      */     }
/*      */   }
/*      */   
/*      */   public final synchronized void printcomma(BigInteger[] array)
/*      */   {
/* 1813 */     int n = array.length;
/* 1814 */     for (int i = 0; i < n; i++) {
/* 1815 */       this.output.print(array[i].toString());
/* 1816 */       this.output.print(",");
/*      */     }
/*      */   }
/*      */   
/*      */   public final synchronized void printcomma(long[] array)
/*      */   {
/* 1822 */     int n = array.length;
/* 1823 */     for (int i = 0; i < n; i++) {
/* 1824 */       this.output.print(array[i]);
/* 1825 */       this.output.print(",");
/*      */     }
/*      */   }
/*      */   
/*      */   public final synchronized void printcomma(int[] array)
/*      */   {
/* 1831 */     int n = array.length;
/* 1832 */     for (int i = 0; i < n; i++) {
/* 1833 */       this.output.print(array[i]);
/* 1834 */       this.output.print(",");
/*      */     }
/*      */   }
/*      */   
/*      */   public final synchronized void printcomma(short[] array)
/*      */   {
/* 1840 */     int n = array.length;
/* 1841 */     for (int i = 0; i < n; i++) {
/* 1842 */       this.output.print(array[i]);
/* 1843 */       this.output.print(",");
/*      */     }
/*      */   }
/*      */   
/*      */   public final synchronized void printcomma(byte[] array)
/*      */   {
/* 1849 */     int n = array.length;
/* 1850 */     for (int i = 0; i < n; i++) {
/* 1851 */       this.output.print(array[i]);
/* 1852 */       this.output.print(",");
/*      */     }
/*      */   }
/*      */   
/*      */   public final synchronized void printcomma(char[] array)
/*      */   {
/* 1858 */     int n = array.length;
/* 1859 */     for (int i = 0; i < n; i++) {
/* 1860 */       this.output.print(array[i]);
/* 1861 */       this.output.print(",");
/*      */     }
/*      */   }
/*      */   
/*      */   public final synchronized void printcomma(boolean[] array)
/*      */   {
/* 1867 */     int n = array.length;
/* 1868 */     for (int i = 0; i < n; i++) {
/* 1869 */       this.output.print(array[i]);
/* 1870 */       this.output.print(",");
/*      */     }
/*      */   }
/*      */   
/*      */   public final synchronized void printcomma(String[] array)
/*      */   {
/* 1876 */     int n = array.length;
/* 1877 */     for (int i = 0; i < n; i++) {
/* 1878 */       this.output.print(array[i]);
/* 1879 */       this.output.print(",");
/*      */     }
/*      */   }
/*      */   
/*      */   public final synchronized void printcomma(Complex[] array)
/*      */   {
/* 1885 */     int n = array.length;
/* 1886 */     for (int i = 0; i < n; i++) {
/* 1887 */       this.output.print(array[i]);
/* 1888 */       this.output.print(",");
/*      */     }
/*      */   }
/*      */   
/*      */   public final synchronized void printcomma(Phasor[] array)
/*      */   {
/* 1894 */     int n = array.length;
/* 1895 */     for (int i = 0; i < n; i++) {
/* 1896 */       this.output.print(array[i]);
/* 1897 */       this.output.print(",");
/*      */     }
/*      */   }
/*      */   
/*      */   public final synchronized void printcomma(ErrorProp[] array)
/*      */   {
/* 1903 */     int n = array.length;
/* 1904 */     for (int i = 0; i < n; i++) {
/* 1905 */       this.output.print(array[i]);
/* 1906 */       this.output.print(",");
/*      */     }
/*      */   }
/*      */   
/*      */   public final synchronized void printcomma(ComplexErrorProp[] array)
/*      */   {
/* 1912 */     int n = array.length;
/* 1913 */     for (int i = 0; i < n; i++) {
/* 1914 */       this.output.print(array[i]);
/* 1915 */       this.output.print(",");
/*      */     }
/*      */   }
/*      */   
/*      */ 
/*      */   public final synchronized void dateAndTimecomma()
/*      */   {
/* 1922 */     Date d = new Date();
/* 1923 */     String day = DateFormat.getDateInstance().format(d);
/* 1924 */     String tim = DateFormat.getTimeInstance().format(d);
/*      */     
/* 1926 */     this.output.print("This file was created at ");
/* 1927 */     this.output.print(tim);
/* 1928 */     this.output.print(" on ");
/* 1929 */     this.output.print(day);
/* 1930 */     this.output.print(",");
/*      */   }
/*      */   
/*      */   public final synchronized void dateAndTimecomma(String title)
/*      */   {
/* 1935 */     Date d = new Date();
/* 1936 */     String day = DateFormat.getDateInstance().format(d);
/* 1937 */     String tim = DateFormat.getTimeInstance().format(d);
/*      */     
/* 1939 */     this.output.print("This file, " + title + ", was created at ");
/* 1940 */     this.output.print(tim);
/* 1941 */     this.output.print(" on ");
/* 1942 */     this.output.print(day);
/* 1943 */     this.output.print(",");
/*      */   }
/*      */   
/*      */ 
/*      */   public final synchronized void printsc(char ch)
/*      */   {
/* 1949 */     this.output.print(ch);
/* 1950 */     this.output.print(";");
/*      */   }
/*      */   
/*      */   public final synchronized void printsc(String word)
/*      */   {
/* 1955 */     this.output.print(word + ";");
/*      */   }
/*      */   
/*      */   public final synchronized void printsc(double dd)
/*      */   {
/* 1960 */     this.output.print(dd);
/* 1961 */     this.output.print(";");
/*      */   }
/*      */   
/*      */   public final synchronized void printsc(float ff)
/*      */   {
/* 1966 */     this.output.print(ff);
/* 1967 */     this.output.print(";");
/*      */   }
/*      */   
/*      */   public final synchronized void printsc(BigDecimal big)
/*      */   {
/* 1972 */     this.output.print(big.toString());
/* 1973 */     this.output.print(";");
/*      */   }
/*      */   
/*      */   public final synchronized void printsc(BigInteger big)
/*      */   {
/* 1978 */     this.output.print(big.toString());
/* 1979 */     this.output.print(";");
/*      */   }
/*      */   
/*      */   public final synchronized void printsc(Complex ff)
/*      */   {
/* 1984 */     this.output.print(ff.toString());
/* 1985 */     this.output.print(";");
/*      */   }
/*      */   
/*      */   public final synchronized void printsc(Phasor ff)
/*      */   {
/* 1990 */     this.output.print(ff.toString());
/* 1991 */     this.output.print(";");
/*      */   }
/*      */   
/*      */   public final synchronized void printsc(ErrorProp ff)
/*      */   {
/* 1996 */     this.output.print(ff.toString());
/* 1997 */     this.output.print(";");
/*      */   }
/*      */   
/*      */   public final synchronized void printsc(ComplexErrorProp ff)
/*      */   {
/* 2002 */     this.output.print(ff.toString());
/* 2003 */     this.output.print(";");
/*      */   }
/*      */   
/*      */   public final synchronized void printsc(int ii)
/*      */   {
/* 2008 */     this.output.print(ii);
/* 2009 */     this.output.print(";");
/*      */   }
/*      */   
/*      */   public final synchronized void printsc(long ll)
/*      */   {
/* 2014 */     this.output.print(ll);
/* 2015 */     this.output.print(";");
/*      */   }
/*      */   
/*      */   public final synchronized void printsc(short ss)
/*      */   {
/* 2020 */     this.output.print(ss);
/* 2021 */     this.output.print(";");
/*      */   }
/*      */   
/*      */   public final synchronized void printsc(byte by)
/*      */   {
/* 2026 */     this.output.print(by);
/* 2027 */     this.output.print(";");
/*      */   }
/*      */   
/*      */   public final synchronized void printsc(boolean bb)
/*      */   {
/* 2032 */     this.output.print(bb);
/* 2033 */     this.output.print(";");
/*      */   }
/*      */   
/*      */   public final synchronized void printsc()
/*      */   {
/* 2038 */     this.output.print(";");
/*      */   }
/*      */   
/*      */   public final synchronized void printsc(double[] array)
/*      */   {
/* 2043 */     int n = array.length;
/* 2044 */     for (int i = 0; i < n; i++) {
/* 2045 */       this.output.print(array[i]);
/* 2046 */       this.output.print(";");
/*      */     }
/*      */   }
/*      */   
/*      */   public final synchronized void printsc(float[] array)
/*      */   {
/* 2052 */     int n = array.length;
/* 2053 */     for (int i = 0; i < n; i++) {
/* 2054 */       this.output.print(array[i]);
/* 2055 */       this.output.print(";");
/*      */     }
/*      */   }
/*      */   
/*      */   public final synchronized void printsc(BigDecimal[] array)
/*      */   {
/* 2061 */     int n = array.length;
/* 2062 */     for (int i = 0; i < n; i++) {
/* 2063 */       this.output.print(array[i].toString());
/* 2064 */       this.output.print(";");
/*      */     }
/*      */   }
/*      */   
/*      */   public final synchronized void printsc(BigInteger[] array)
/*      */   {
/* 2070 */     int n = array.length;
/* 2071 */     for (int i = 0; i < n; i++) {
/* 2072 */       this.output.print(array[i].toString());
/* 2073 */       this.output.print(";");
/*      */     }
/*      */   }
/*      */   
/*      */   public final synchronized void printsc(long[] array)
/*      */   {
/* 2079 */     int n = array.length;
/* 2080 */     for (int i = 0; i < n; i++) {
/* 2081 */       this.output.print(array[i]);
/* 2082 */       this.output.print(";");
/*      */     }
/*      */   }
/*      */   
/*      */   public final synchronized void printsc(short[] array)
/*      */   {
/* 2088 */     int n = array.length;
/* 2089 */     for (int i = 0; i < n; i++) {
/* 2090 */       this.output.print(array[i]);
/* 2091 */       this.output.print(";");
/*      */     }
/*      */   }
/*      */   
/*      */   public final synchronized void printsc(byte[] array)
/*      */   {
/* 2097 */     int n = array.length;
/* 2098 */     for (int i = 0; i < n; i++) {
/* 2099 */       this.output.print(array[i]);
/* 2100 */       this.output.print(";");
/*      */     }
/*      */   }
/*      */   
/*      */   public final synchronized void printsc(int[] array)
/*      */   {
/* 2106 */     int n = array.length;
/* 2107 */     for (int i = 0; i < n; i++) {
/* 2108 */       this.output.print(array[i]);
/* 2109 */       this.output.print(";");
/*      */     }
/*      */   }
/*      */   
/*      */   public final synchronized void printsc(char[] array)
/*      */   {
/* 2115 */     int n = array.length;
/* 2116 */     for (int i = 0; i < n; i++) {
/* 2117 */       this.output.print(array[i]);
/* 2118 */       this.output.print(";");
/*      */     }
/*      */   }
/*      */   
/*      */   public final synchronized void printsc(boolean[] array)
/*      */   {
/* 2124 */     int n = array.length;
/* 2125 */     for (int i = 0; i < n; i++) {
/* 2126 */       this.output.print(array[i]);
/* 2127 */       this.output.print(";");
/*      */     }
/*      */   }
/*      */   
/*      */   public final synchronized void printsc(String[] array)
/*      */   {
/* 2133 */     int n = array.length;
/* 2134 */     for (int i = 0; i < n; i++) {
/* 2135 */       this.output.print(array[i]);
/* 2136 */       this.output.print(";");
/*      */     }
/*      */   }
/*      */   
/*      */   public final synchronized void printsc(Complex[] array)
/*      */   {
/* 2142 */     int n = array.length;
/* 2143 */     for (int i = 0; i < n; i++) {
/* 2144 */       this.output.print(array[i]);
/* 2145 */       this.output.print(";");
/*      */     }
/*      */   }
/*      */   
/*      */   public final synchronized void printsc(Phasor[] array)
/*      */   {
/* 2151 */     int n = array.length;
/* 2152 */     for (int i = 0; i < n; i++) {
/* 2153 */       this.output.print(array[i]);
/* 2154 */       this.output.print(";");
/*      */     }
/*      */   }
/*      */   
/*      */   public final synchronized void printsc(ErrorProp[] array)
/*      */   {
/* 2160 */     int n = array.length;
/* 2161 */     for (int i = 0; i < n; i++) {
/* 2162 */       this.output.print(array[i]);
/* 2163 */       this.output.print(";");
/*      */     }
/*      */   }
/*      */   
/*      */   public final synchronized void printsc(ComplexErrorProp[] array)
/*      */   {
/* 2169 */     int n = array.length;
/* 2170 */     for (int i = 0; i < n; i++) {
/* 2171 */       this.output.print(array[i]);
/* 2172 */       this.output.print(";");
/*      */     }
/*      */   }
/*      */   
/*      */ 
/*      */   public final synchronized void dateAndTimesc()
/*      */   {
/* 2179 */     Date d = new Date();
/* 2180 */     String day = DateFormat.getDateInstance().format(d);
/* 2181 */     String tim = DateFormat.getTimeInstance().format(d);
/*      */     
/* 2183 */     this.output.print("This file was created at ");
/* 2184 */     this.output.print(tim);
/* 2185 */     this.output.print(" on ");
/* 2186 */     this.output.print(day);
/* 2187 */     this.output.print(";");
/*      */   }
/*      */   
/*      */   public final synchronized void dateAndTimesc(String title)
/*      */   {
/* 2192 */     Date d = new Date();
/* 2193 */     String day = DateFormat.getDateInstance().format(d);
/* 2194 */     String tim = DateFormat.getTimeInstance().format(d);
/*      */     
/* 2196 */     this.output.print("This file, " + title + ", was created at ");
/* 2197 */     this.output.print(tim);
/* 2198 */     this.output.print(" on ");
/* 2199 */     this.output.print(day);
/* 2200 */     this.output.print(";");
/*      */   }
/*      */   
/*      */   public final synchronized void close()
/*      */   {
/* 2205 */     this.output.close();
/*      */   }
/*      */   
/*      */   public static void printArrayToText(double[][] array)
/*      */   {
/* 2210 */     String title = "ArrayToText.txt";
/* 2211 */     printArrayToText(title, array);
/*      */   }
/*      */   
/*      */ 
/*      */   public static void printArrayToText(String title, double[][] array)
/*      */   {
/* 2217 */     FileOutput fo = new FileOutput(title, 'n');
/* 2218 */     fo.dateAndTimeln(title);
/* 2219 */     int nrow = array.length;
/* 2220 */     int ncol = 0;
/* 2221 */     for (int i = 0; i < nrow; i++) {
/* 2222 */       ncol = array[i].length;
/* 2223 */       for (int j = 0; j < ncol; j++) {
/* 2224 */         fo.printtab(array[i][j]);
/*      */       }
/* 2226 */       fo.println();
/*      */     }
/* 2228 */     fo.println("End of file.");
/* 2229 */     fo.close();
/*      */   }
/*      */   
/*      */   public static void printArrayToText(double[] array)
/*      */   {
/* 2234 */     String title = "ArrayToText.txt";
/* 2235 */     printArrayToText(title, array);
/*      */   }
/*      */   
/*      */ 
/*      */   public static void printArrayToText(String title, double[] array)
/*      */   {
/* 2241 */     FileOutput fo = new FileOutput(title, 'n');
/* 2242 */     fo.dateAndTimeln(title);
/* 2243 */     int nrow = array.length;
/* 2244 */     for (int i = 0; i < nrow; i++) {
/* 2245 */       fo.printtab(array[i]);
/*      */     }
/* 2247 */     fo.println();
/* 2248 */     fo.println("End of file.");
/* 2249 */     fo.close();
/*      */   }
/*      */   
/*      */   public static void printArrayToText(float[][] array)
/*      */   {
/* 2254 */     String title = "ArrayToText.txt";
/* 2255 */     printArrayToText(title, array);
/*      */   }
/*      */   
/*      */ 
/*      */   public static void printArrayToText(String title, float[][] array)
/*      */   {
/* 2261 */     FileOutput fo = new FileOutput(title, 'n');
/* 2262 */     fo.dateAndTimeln(title);
/* 2263 */     int nrow = array.length;
/* 2264 */     int ncol = 0;
/* 2265 */     for (int i = 0; i < nrow; i++) {
/* 2266 */       ncol = array[i].length;
/* 2267 */       for (int j = 0; j < ncol; j++) {
/* 2268 */         fo.printtab(array[i][j]);
/*      */       }
/* 2270 */       fo.println();
/*      */     }
/* 2272 */     fo.println("End of file.");
/* 2273 */     fo.close();
/*      */   }
/*      */   
/*      */ 
/*      */   public static void printArrayToText(float[] array)
/*      */   {
/* 2279 */     String title = "ArrayToText.txt";
/* 2280 */     printArrayToText(title, array);
/*      */   }
/*      */   
/*      */ 
/*      */   public static void printArrayToText(String title, float[] array)
/*      */   {
/* 2286 */     FileOutput fo = new FileOutput(title, 'n');
/* 2287 */     fo.dateAndTimeln(title);
/* 2288 */     int nrow = array.length;
/* 2289 */     for (int i = 0; i < nrow; i++) {
/* 2290 */       fo.printtab(array[i]);
/*      */     }
/* 2292 */     fo.println();
/* 2293 */     fo.println("End of file.");
/* 2294 */     fo.close();
/*      */   }
/*      */   
/*      */   public static void printArrayToText(BigDecimal[][] array)
/*      */   {
/* 2299 */     String title = "ArrayToText.txt";
/* 2300 */     printArrayToText(title, array);
/*      */   }
/*      */   
/*      */ 
/*      */   public static void printArrayToText(String title, BigDecimal[][] array)
/*      */   {
/* 2306 */     FileOutput fo = new FileOutput(title, 'n');
/* 2307 */     fo.dateAndTimeln(title);
/* 2308 */     int nrow = array.length;
/* 2309 */     int ncol = 0;
/* 2310 */     for (int i = 0; i < nrow; i++) {
/* 2311 */       ncol = array[i].length;
/* 2312 */       for (int j = 0; j < ncol; j++) {
/* 2313 */         fo.printtab(array[i][j].toString());
/*      */       }
/* 2315 */       fo.println();
/*      */     }
/* 2317 */     fo.println("End of file.");
/* 2318 */     fo.close();
/*      */   }
/*      */   
/*      */   public static void printArrayToText(BigInteger[][] array)
/*      */   {
/* 2323 */     String title = "ArrayToText.txt";
/* 2324 */     printArrayToText(title, array);
/*      */   }
/*      */   
/*      */   public static void printArrayToText(String title, BigInteger[][] array)
/*      */   {
/* 2329 */     FileOutput fo = new FileOutput(title, 'n');
/* 2330 */     fo.dateAndTimeln(title);
/* 2331 */     int nrow = array.length;
/* 2332 */     int ncol = 0;
/* 2333 */     for (int i = 0; i < nrow; i++) {
/* 2334 */       ncol = array[i].length;
/* 2335 */       for (int j = 0; j < ncol; j++) {
/* 2336 */         fo.printtab(array[i][j].toString());
/*      */       }
/* 2338 */       fo.println();
/*      */     }
/* 2340 */     fo.println("End of file.");
/* 2341 */     fo.close();
/*      */   }
/*      */   
/*      */ 
/*      */   public static void printArrayToText(BigDecimal[] array)
/*      */   {
/* 2347 */     String title = "ArrayToText.txt";
/* 2348 */     printArrayToText(title, array);
/*      */   }
/*      */   
/*      */ 
/*      */   public static void printArrayToText(String title, BigDecimal[] array)
/*      */   {
/* 2354 */     FileOutput fo = new FileOutput(title, 'n');
/* 2355 */     fo.dateAndTimeln(title);
/* 2356 */     int nrow = array.length;
/* 2357 */     for (int i = 0; i < nrow; i++) {
/* 2358 */       fo.printtab(array[i].toString());
/*      */     }
/* 2360 */     fo.println();
/* 2361 */     fo.println("End of file.");
/* 2362 */     fo.close();
/*      */   }
/*      */   
/*      */   public static void printArrayToText(BigInteger[] array)
/*      */   {
/* 2367 */     String title = "ArrayToText.txt";
/* 2368 */     printArrayToText(title, array);
/*      */   }
/*      */   
/*      */ 
/*      */   public static void printArrayToText(String title, BigInteger[] array)
/*      */   {
/* 2374 */     FileOutput fo = new FileOutput(title, 'n');
/* 2375 */     fo.dateAndTimeln(title);
/* 2376 */     int nrow = array.length;
/* 2377 */     for (int i = 0; i < nrow; i++) {
/* 2378 */       fo.printtab(array[i].toString());
/*      */     }
/* 2380 */     fo.println();
/* 2381 */     fo.println("End of file.");
/* 2382 */     fo.close();
/*      */   }
/*      */   
/*      */   public static void printArrayToText(int[][] array)
/*      */   {
/* 2387 */     String title = "ArrayToText.txt";
/* 2388 */     printArrayToText(title, array);
/*      */   }
/*      */   
/*      */ 
/*      */   public static void printArrayToText(String title, int[][] array)
/*      */   {
/* 2394 */     FileOutput fo = new FileOutput(title, 'n');
/* 2395 */     fo.dateAndTimeln(title);
/* 2396 */     int nrow = array.length;
/* 2397 */     int ncol = 0;
/* 2398 */     for (int i = 0; i < nrow; i++) {
/* 2399 */       ncol = array[i].length;
/* 2400 */       for (int j = 0; j < ncol; j++) {
/* 2401 */         fo.printtab(array[i][j]);
/*      */       }
/* 2403 */       fo.println();
/*      */     }
/* 2405 */     fo.println("End of file.");
/* 2406 */     fo.close();
/*      */   }
/*      */   
/*      */ 
/*      */   public static void printArrayToText(int[] array)
/*      */   {
/* 2412 */     String title = "ArrayToText.txt";
/* 2413 */     printArrayToText(title, array);
/*      */   }
/*      */   
/*      */ 
/*      */   public static void printArrayToText(String title, int[] array)
/*      */   {
/* 2419 */     FileOutput fo = new FileOutput(title, 'n');
/* 2420 */     fo.dateAndTimeln(title);
/* 2421 */     int nrow = array.length;
/* 2422 */     for (int i = 0; i < nrow; i++) {
/* 2423 */       fo.printtab(array[i]);
/*      */     }
/* 2425 */     fo.println();
/* 2426 */     fo.println("End of file.");
/* 2427 */     fo.close();
/*      */   }
/*      */   
/*      */   public static void printArrayToText(long[][] array)
/*      */   {
/* 2432 */     String title = "ArrayToText.txt";
/* 2433 */     printArrayToText(title, array);
/*      */   }
/*      */   
/*      */ 
/*      */   public static void printArrayToText(String title, long[][] array)
/*      */   {
/* 2439 */     FileOutput fo = new FileOutput(title, 'n');
/* 2440 */     fo.dateAndTimeln(title);
/* 2441 */     int nrow = array.length;
/* 2442 */     int ncol = 0;
/* 2443 */     for (int i = 0; i < nrow; i++) {
/* 2444 */       ncol = array[i].length;
/* 2445 */       for (int j = 0; j < ncol; j++) {
/* 2446 */         fo.printtab(array[i][j]);
/*      */       }
/* 2448 */       fo.println();
/*      */     }
/* 2450 */     fo.println("End of file.");
/* 2451 */     fo.close();
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */   public static void printArrayToText(long[] array)
/*      */   {
/* 2459 */     String title = "ArrayToText.txt";
/* 2460 */     printArrayToText(title, array);
/*      */   }
/*      */   
/*      */ 
/*      */   public static void printArrayToText(String title, long[] array)
/*      */   {
/* 2466 */     FileOutput fo = new FileOutput(title, 'n');
/* 2467 */     fo.dateAndTimeln(title);
/* 2468 */     int nrow = array.length;
/* 2469 */     for (int i = 0; i < nrow; i++) {
/* 2470 */       fo.printtab(array[i]);
/*      */     }
/* 2472 */     fo.println();
/* 2473 */     fo.println("End of file.");
/* 2474 */     fo.close();
/*      */   }
/*      */   
/*      */   public static void printArrayToText(short[][] array)
/*      */   {
/* 2479 */     String title = "ArrayToText.txt";
/* 2480 */     printArrayToText(title, array);
/*      */   }
/*      */   
/*      */ 
/*      */   public static void printArrayToText(String title, short[][] array)
/*      */   {
/* 2486 */     FileOutput fo = new FileOutput(title, 'n');
/* 2487 */     fo.dateAndTimeln(title);
/* 2488 */     int nrow = array.length;
/* 2489 */     int ncol = 0;
/* 2490 */     for (int i = 0; i < nrow; i++) {
/* 2491 */       ncol = array[i].length;
/* 2492 */       for (int j = 0; j < ncol; j++) {
/* 2493 */         fo.printtab(array[i][j]);
/*      */       }
/* 2495 */       fo.println();
/*      */     }
/* 2497 */     fo.println("End of file.");
/* 2498 */     fo.close();
/*      */   }
/*      */   
/*      */   public static void printArrayToText(short[] array)
/*      */   {
/* 2503 */     String title = "ArrayToText.txt";
/* 2504 */     printArrayToText(title, array);
/*      */   }
/*      */   
/*      */ 
/*      */   public static void printArrayToText(String title, short[] array)
/*      */   {
/* 2510 */     FileOutput fo = new FileOutput(title, 'n');
/* 2511 */     fo.dateAndTimeln(title);
/* 2512 */     int nrow = array.length;
/* 2513 */     for (int i = 0; i < nrow; i++) {
/* 2514 */       fo.printtab(array[i]);
/*      */     }
/* 2516 */     fo.println();
/* 2517 */     fo.println("End of file.");
/* 2518 */     fo.close();
/*      */   }
/*      */   
/*      */ 
/*      */   public static void printArrayToText(byte[][] array)
/*      */   {
/* 2524 */     String title = "ArrayToText.txt";
/* 2525 */     printArrayToText(title, array);
/*      */   }
/*      */   
/*      */ 
/*      */   public static void printArrayToText(String title, byte[][] array)
/*      */   {
/* 2531 */     FileOutput fo = new FileOutput(title, 'n');
/* 2532 */     fo.dateAndTimeln(title);
/* 2533 */     int nrow = array.length;
/* 2534 */     int ncol = 0;
/* 2535 */     for (int i = 0; i < nrow; i++) {
/* 2536 */       ncol = array[i].length;
/* 2537 */       for (int j = 0; j < ncol; j++) {
/* 2538 */         fo.printtab(array[i][j]);
/*      */       }
/* 2540 */       fo.println();
/*      */     }
/* 2542 */     fo.println("End of file.");
/* 2543 */     fo.close();
/*      */   }
/*      */   
/*      */ 
/*      */   public static void printArrayToText(byte[] array)
/*      */   {
/* 2549 */     String title = "ArrayToText.txt";
/* 2550 */     printArrayToText(title, array);
/*      */   }
/*      */   
/*      */ 
/*      */   public static void printArrayToText(String title, byte[] array)
/*      */   {
/* 2556 */     FileOutput fo = new FileOutput(title, 'n');
/* 2557 */     fo.dateAndTimeln(title);
/* 2558 */     int nrow = array.length;
/* 2559 */     for (int i = 0; i < nrow; i++) {
/* 2560 */       fo.printtab(array[i]);
/*      */     }
/* 2562 */     fo.println();
/* 2563 */     fo.println("End of file.");
/* 2564 */     fo.close();
/*      */   }
/*      */   
/*      */ 
/*      */   public static void printArrayToText(String[][] array)
/*      */   {
/* 2570 */     String title = "ArrayToText.txt";
/* 2571 */     printArrayToText(title, array);
/*      */   }
/*      */   
/*      */ 
/*      */   public static void printArrayToText(String title, String[][] array)
/*      */   {
/* 2577 */     FileOutput fo = new FileOutput(title, 'n');
/* 2578 */     fo.dateAndTimeln(title);
/* 2579 */     int nrow = array.length;
/* 2580 */     int ncol = 0;
/* 2581 */     for (int i = 0; i < nrow; i++) {
/* 2582 */       ncol = array[i].length;
/* 2583 */       for (int j = 0; j < ncol; j++) {
/* 2584 */         fo.printtab(array[i][j]);
/*      */       }
/* 2586 */       fo.println();
/*      */     }
/* 2588 */     fo.println("End of file.");
/* 2589 */     fo.close();
/*      */   }
/*      */   
/*      */ 
/*      */   public static void printArrayToText(String[] array)
/*      */   {
/* 2595 */     String title = "ArrayToText.txt";
/* 2596 */     printArrayToText(title, array);
/*      */   }
/*      */   
/*      */ 
/*      */   public static void printArrayToText(String title, String[] array)
/*      */   {
/* 2602 */     FileOutput fo = new FileOutput(title, 'n');
/* 2603 */     fo.dateAndTimeln(title);
/* 2604 */     int nrow = array.length;
/* 2605 */     for (int i = 0; i < nrow; i++) {
/* 2606 */       fo.printtab(array[i]);
/*      */     }
/* 2608 */     fo.println();
/* 2609 */     fo.println("End of file.");
/* 2610 */     fo.close();
/*      */   }
/*      */   
/*      */   public static void printArrayToText(char[][] array) {
/* 2614 */     String title = "ArrayToText.txt";
/* 2615 */     printArrayToText(title, array);
/*      */   }
/*      */   
/*      */ 
/*      */   public static void printArrayToText(String title, char[][] array)
/*      */   {
/* 2621 */     FileOutput fo = new FileOutput(title, 'n');
/* 2622 */     fo.dateAndTimeln(title);
/* 2623 */     int nrow = array.length;
/* 2624 */     int ncol = 0;
/* 2625 */     for (int i = 0; i < nrow; i++) {
/* 2626 */       ncol = array[i].length;
/* 2627 */       for (int j = 0; j < ncol; j++) {
/* 2628 */         fo.printtab(array[i][j]);
/*      */       }
/* 2630 */       fo.println();
/*      */     }
/* 2632 */     fo.println("End of file.");
/* 2633 */     fo.close();
/*      */   }
/*      */   
/*      */ 
/*      */   public static void printArrayToText(char[] array)
/*      */   {
/* 2639 */     String title = "ArrayToText.txt";
/* 2640 */     printArrayToText(title, array);
/*      */   }
/*      */   
/*      */ 
/*      */   public static void printArrayToText(String title, char[] array)
/*      */   {
/* 2646 */     FileOutput fo = new FileOutput(title, 'n');
/* 2647 */     fo.dateAndTimeln(title);
/* 2648 */     int nrow = array.length;
/* 2649 */     for (int i = 0; i < nrow; i++) {
/* 2650 */       fo.printtab(array[i]);
/*      */     }
/* 2652 */     fo.println();
/* 2653 */     fo.println("End of file.");
/* 2654 */     fo.close();
/*      */   }
/*      */   
/*      */   public static void printArrayToText(boolean[][] array) {
/* 2658 */     String title = "ArrayToText.txt";
/* 2659 */     printArrayToText(title, array);
/*      */   }
/*      */   
/*      */ 
/*      */   public static void printArrayToText(String title, boolean[][] array)
/*      */   {
/* 2665 */     FileOutput fo = new FileOutput(title, 'n');
/* 2666 */     fo.dateAndTimeln(title);
/* 2667 */     int nrow = array.length;
/* 2668 */     int ncol = 0;
/* 2669 */     for (int i = 0; i < nrow; i++) {
/* 2670 */       ncol = array[i].length;
/* 2671 */       for (int j = 0; j < ncol; j++) {
/* 2672 */         fo.printtab(array[i][j]);
/*      */       }
/* 2674 */       fo.println();
/*      */     }
/* 2676 */     fo.println("End of file.");
/* 2677 */     fo.close();
/*      */   }
/*      */   
/*      */ 
/*      */   public static void printArrayToText(boolean[] array)
/*      */   {
/* 2683 */     String title = "ArrayToText.txt";
/* 2684 */     printArrayToText(title, array);
/*      */   }
/*      */   
/*      */ 
/*      */   public static void printArrayToText(String title, boolean[] array)
/*      */   {
/* 2690 */     FileOutput fo = new FileOutput(title, 'n');
/* 2691 */     fo.dateAndTimeln(title);
/* 2692 */     int nrow = array.length;
/* 2693 */     for (int i = 0; i < nrow; i++) {
/* 2694 */       fo.printtab(array[i]);
/*      */     }
/* 2696 */     fo.println();
/* 2697 */     fo.println("End of file.");
/* 2698 */     fo.close();
/*      */   }
/*      */   
/*      */   public static void printArrayToText(Complex[][] array)
/*      */   {
/* 2703 */     String title = "ArrayToText.txt";
/* 2704 */     printArrayToText(title, array);
/*      */   }
/*      */   
/*      */   public static void printArrayToText(String title, Complex[][] array)
/*      */   {
/* 2709 */     FileOutput fo = new FileOutput(title, 'n');
/* 2710 */     fo.dateAndTimeln(title);
/* 2711 */     int nrow = array.length;
/* 2712 */     int ncol = 0;
/* 2713 */     for (int i = 0; i < nrow; i++) {
/* 2714 */       ncol = array[i].length;
/* 2715 */       for (int j = 0; j < ncol; j++) {
/* 2716 */         fo.printtab(array[i][j]);
/*      */       }
/* 2718 */       fo.println();
/*      */     }
/* 2720 */     fo.println("End of file.");
/* 2721 */     fo.close();
/*      */   }
/*      */   
/*      */ 
/*      */   public static void printArrayToText(Complex[] array)
/*      */   {
/* 2727 */     String title = "ArrayToText.txt";
/* 2728 */     printArrayToText(title, array);
/*      */   }
/*      */   
/*      */ 
/*      */   public static void printArrayToText(String title, Complex[] array)
/*      */   {
/* 2734 */     FileOutput fo = new FileOutput(title, 'n');
/* 2735 */     fo.dateAndTimeln(title);
/* 2736 */     int nrow = array.length;
/* 2737 */     for (int i = 0; i < nrow; i++) {
/* 2738 */       fo.printtab(array[i]);
/*      */     }
/* 2740 */     fo.println();
/* 2741 */     fo.println("End of file.");
/* 2742 */     fo.close();
/*      */   }
/*      */   
/*      */   public static void printArrayToText(Phasor[][] array)
/*      */   {
/* 2747 */     String title = "ArrayToText.txt";
/* 2748 */     printArrayToText(title, array);
/*      */   }
/*      */   
/*      */   public static void printArrayToText(String title, Phasor[][] array)
/*      */   {
/* 2753 */     FileOutput fo = new FileOutput(title, 'n');
/* 2754 */     fo.dateAndTimeln(title);
/* 2755 */     int nrow = array.length;
/* 2756 */     int ncol = 0;
/* 2757 */     for (int i = 0; i < nrow; i++) {
/* 2758 */       ncol = array[i].length;
/* 2759 */       for (int j = 0; j < ncol; j++) {
/* 2760 */         fo.printtab(array[i][j]);
/*      */       }
/* 2762 */       fo.println();
/*      */     }
/* 2764 */     fo.println("End of file.");
/* 2765 */     fo.close();
/*      */   }
/*      */   
/*      */ 
/*      */   public static void printArrayToText(Phasor[] array)
/*      */   {
/* 2771 */     String title = "ArrayToText.txt";
/* 2772 */     printArrayToText(title, array);
/*      */   }
/*      */   
/*      */ 
/*      */   public static void printArrayToText(String title, Phasor[] array)
/*      */   {
/* 2778 */     FileOutput fo = new FileOutput(title, 'n');
/* 2779 */     fo.dateAndTimeln(title);
/* 2780 */     int nrow = array.length;
/* 2781 */     for (int i = 0; i < nrow; i++) {
/* 2782 */       fo.printtab(array[i]);
/*      */     }
/* 2784 */     fo.println();
/* 2785 */     fo.println("End of file.");
/* 2786 */     fo.close();
/*      */   }
/*      */   
/*      */   public static void printArrayToText(ErrorProp[][] array)
/*      */   {
/* 2791 */     String title = "ArrayToText.txt";
/* 2792 */     printArrayToText(title, array);
/*      */   }
/*      */   
/*      */ 
/*      */   public static void printArrayToText(String title, ErrorProp[][] array)
/*      */   {
/* 2798 */     FileOutput fo = new FileOutput(title, 'n');
/* 2799 */     fo.dateAndTimeln(title);
/* 2800 */     int nrow = array.length;
/* 2801 */     int ncol = 0;
/* 2802 */     for (int i = 0; i < nrow; i++) {
/* 2803 */       ncol = array[i].length;
/* 2804 */       for (int j = 0; j < ncol; j++) {
/* 2805 */         fo.printtab(array[i][j]);
/*      */       }
/* 2807 */       fo.println();
/*      */     }
/* 2809 */     fo.println("End of file.");
/* 2810 */     fo.close();
/*      */   }
/*      */   
/*      */ 
/*      */   public static void printArrayToText(ErrorProp[] array)
/*      */   {
/* 2816 */     String title = "ArrayToText.txt";
/* 2817 */     printArrayToText(title, array);
/*      */   }
/*      */   
/*      */ 
/*      */   public static void printArrayToText(String title, ErrorProp[] array)
/*      */   {
/* 2823 */     FileOutput fo = new FileOutput(title, 'n');
/* 2824 */     fo.dateAndTimeln(title);
/* 2825 */     int nrow = array.length;
/* 2826 */     for (int i = 0; i < nrow; i++) {
/* 2827 */       fo.printtab(array[i]);
/*      */     }
/* 2829 */     fo.println();
/* 2830 */     fo.println("End of file.");
/* 2831 */     fo.close();
/*      */   }
/*      */   
/*      */ 
/*      */   public static void printArrayToText(ComplexErrorProp[][] array)
/*      */   {
/* 2837 */     String title = "ArrayToText.txt";
/* 2838 */     printArrayToText(title, array);
/*      */   }
/*      */   
/*      */ 
/*      */   public static void printArrayToText(String title, ComplexErrorProp[][] array)
/*      */   {
/* 2844 */     FileOutput fo = new FileOutput(title, 'n');
/* 2845 */     fo.dateAndTimeln(title);
/* 2846 */     int nrow = array.length;
/* 2847 */     int ncol = 0;
/* 2848 */     for (int i = 0; i < nrow; i++) {
/* 2849 */       ncol = array[i].length;
/* 2850 */       for (int j = 0; j < ncol; j++) {
/* 2851 */         fo.printtab(array[i][j]);
/*      */       }
/* 2853 */       fo.println();
/*      */     }
/* 2855 */     fo.println("End of file.");
/* 2856 */     fo.close();
/*      */   }
/*      */   
/*      */ 
/*      */   public static void printArrayToText(ComplexErrorProp[] array)
/*      */   {
/* 2862 */     String title = "ArrayToText.txt";
/* 2863 */     printArrayToText(title, array);
/*      */   }
/*      */   
/*      */ 
/*      */   public static void printArrayToText(String title, ComplexErrorProp[] array)
/*      */   {
/* 2869 */     FileOutput fo = new FileOutput(title, 'n');
/* 2870 */     fo.dateAndTimeln(title);
/* 2871 */     int nrow = array.length;
/* 2872 */     for (int i = 0; i < nrow; i++) {
/* 2873 */       fo.printtab(array[i]);
/*      */     }
/* 2875 */     fo.println();
/* 2876 */     fo.println("End of file.");
/* 2877 */     fo.close();
/*      */   }
/*      */   
/*      */   private static String setField(String ss, int f) {
/* 2881 */     char sp = ' ';
/* 2882 */     int n = ss.length();
/* 2883 */     if (f > n) {
/* 2884 */       for (int i = n + 1; i <= f; i++) {
/* 2885 */         ss = ss + sp;
/*      */       }
/*      */     }
/* 2888 */     return ss;
/*      */   }
/*      */ }


/* Location:              /home/menick/Scrivania/ProcessMining/CNMining/CNMining.jar!/flanagan/io/FileOutput.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       0.7.1
 */