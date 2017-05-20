/*      */ package flanagan.io;
/*      */ 
/*      */ import flanagan.analysis.ErrorProp;
/*      */ import flanagan.circuits.Phasor;
/*      */ import flanagan.complex.Complex;
/*      */ import flanagan.complex.ComplexErrorProp;
/*      */ import flanagan.math.Fmath;
/*      */ import java.io.PrintStream;
/*      */ import java.math.BigDecimal;
/*      */ import java.math.BigInteger;
/*      */ import javax.swing.JOptionPane;
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
/*      */ public class Db
/*      */ {
/*      */   public static final synchronized double readDouble(String mess)
/*      */   {
/*   50 */     String line = "";
/*   51 */     double d = 0.0D;
/*   52 */     boolean finish = false;
/*   53 */     System.out.flush();
/*   54 */     String mess0 = "Input type: double\n";
/*      */     
/*   56 */     while (!finish) {
/*   57 */       line = JOptionPane.showInputDialog(mess0 + mess);
/*   58 */       if (line != null) {
/*      */         try {
/*   60 */           d = Double.parseDouble(line.trim());
/*   61 */           finish = true;
/*      */         }
/*      */         catch (NumberFormatException e) {}
/*      */       }
/*      */     }
/*      */     
/*   67 */     return d;
/*      */   }
/*      */   
/*      */ 
/*      */   public static final synchronized double readDouble(String mess, double dflt)
/*      */   {
/*   73 */     String line = "";
/*   74 */     double d = 0.0D;
/*   75 */     boolean finish = false;
/*   76 */     System.out.flush();
/*   77 */     String mess0 = "Input type: double\n";
/*   78 */     mess = mess + "\n";
/*   79 */     String dfltmess = dflt + "";
/*      */     
/*   81 */     while (!finish) {
/*   82 */       line = JOptionPane.showInputDialog(mess0 + mess + " [default value = " + dflt + "] ", dfltmess);
/*      */       
/*   84 */       if (line != null) {
/*   85 */         if (line.equals("")) {
/*   86 */           d = dflt;
/*   87 */           finish = true;
/*   88 */           line = null;
/*      */         }
/*      */         else {
/*      */           try {
/*   92 */             d = Double.parseDouble(line.trim());
/*   93 */             finish = true;
/*      */           }
/*      */           catch (NumberFormatException e) {}
/*      */         }
/*      */       }
/*      */     }
/*      */     
/*  100 */     return d;
/*      */   }
/*      */   
/*      */ 
/*      */   public static final synchronized double readDouble()
/*      */   {
/*  106 */     String line = "";
/*  107 */     String mess = "Input type: double";
/*  108 */     double d = 0.0D;
/*  109 */     boolean finish = false;
/*  110 */     System.out.flush();
/*      */     
/*  112 */     while (!finish) {
/*  113 */       line = JOptionPane.showInputDialog(mess);
/*  114 */       if (line != null) {
/*      */         try {
/*  116 */           d = Double.parseDouble(line.trim());
/*  117 */           finish = true;
/*      */         }
/*      */         catch (NumberFormatException e) {}
/*      */       }
/*      */     }
/*      */     
/*  123 */     return d;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */   public static final synchronized Complex readComplex(String mess)
/*      */   {
/*  131 */     String line = "";
/*  132 */     Complex c = new Complex();
/*  133 */     boolean finish = false;
/*  134 */     String mess0 = "Input type: Complex (x + jy)\n";
/*      */     
/*  136 */     System.out.flush();
/*      */     
/*  138 */     while (!finish) {
/*  139 */       line = JOptionPane.showInputDialog(mess0 + mess);
/*  140 */       if (line != null) {
/*      */         try {
/*  142 */           c = Complex.parseComplex(line);
/*  143 */           finish = true;
/*      */         }
/*      */         catch (NumberFormatException e) {}
/*      */       }
/*      */     }
/*      */     
/*  149 */     return c;
/*      */   }
/*      */   
/*      */ 
/*      */   public static final synchronized Complex readComplex(String mess, Complex dflt)
/*      */   {
/*  155 */     String line = "";
/*  156 */     Complex c = new Complex();
/*  157 */     boolean finish = false;
/*  158 */     String mess0 = "Input type: Complex (x + jy)\n";
/*  159 */     String dfltmess = dflt + "";
/*  160 */     mess = mess + "\n";
/*      */     
/*  162 */     System.out.flush();
/*      */     
/*  164 */     while (!finish) {
/*  165 */       line = JOptionPane.showInputDialog(mess0 + mess + " [default value = " + dflt + "] ", dfltmess);
/*      */       
/*  167 */       if (line != null) {
/*  168 */         if (line.equals("")) {
/*  169 */           c = dflt;
/*  170 */           finish = true;
/*  171 */           line = null;
/*      */         }
/*      */         else {
/*      */           try {
/*  175 */             c = Complex.parseComplex(line);
/*  176 */             finish = true;
/*      */           }
/*      */           catch (NumberFormatException e) {}
/*      */         }
/*      */       }
/*      */     }
/*      */     
/*  183 */     return c;
/*      */   }
/*      */   
/*      */ 
/*      */   public static final synchronized Complex readComplex(String mess, String dflt)
/*      */   {
/*  189 */     String line = "";
/*  190 */     Complex c = new Complex();
/*  191 */     boolean finish = false;
/*  192 */     String mess0 = "Input type: Complex (x + jy)\n";
/*  193 */     String dfltmess = dflt;
/*  194 */     mess = mess + "\n";
/*      */     
/*  196 */     System.out.flush();
/*      */     
/*      */ 
/*  199 */     while (!finish) {
/*  200 */       line = JOptionPane.showInputDialog(mess0 + mess + " [default value = " + dflt + "] ", dfltmess);
/*      */       
/*  202 */       if (line != null) {
/*  203 */         if (line.equals("")) {
/*  204 */           c = Complex.parseComplex(dflt);
/*  205 */           finish = true;
/*  206 */           line = null;
/*      */         }
/*      */         else {
/*      */           try {
/*  210 */             c = Complex.parseComplex(line);
/*  211 */             finish = true;
/*      */           }
/*      */           catch (NumberFormatException e) {}
/*      */         }
/*      */       }
/*      */     }
/*      */     
/*  218 */     return c;
/*      */   }
/*      */   
/*      */ 
/*      */   public static final synchronized Complex readComplex()
/*      */   {
/*  224 */     String line = "";
/*  225 */     String mess = "Input type: Complex (x + jy)";
/*  226 */     Complex c = new Complex();
/*  227 */     boolean finish = false;
/*  228 */     System.out.flush();
/*      */     
/*  230 */     while (!finish) {
/*  231 */       line = JOptionPane.showInputDialog(mess);
/*  232 */       if (line != null) {
/*      */         try {
/*  234 */           c = Complex.parseComplex(line.trim());
/*  235 */           finish = true;
/*      */         }
/*      */         catch (NumberFormatException e) {}
/*      */       }
/*      */     }
/*      */     
/*  241 */     return c;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */   public static final synchronized Phasor readPhasor(String mess)
/*      */   {
/*  249 */     String line = "";
/*  250 */     Phasor ph = new Phasor();
/*  251 */     boolean finish = false;
/*  252 */     String mess0 = "Input type: Phasor ('mag'<'phase'deg or 'mag'<'phase'rad)\n";
/*      */     
/*  254 */     System.out.flush();
/*      */     
/*  256 */     while (!finish) {
/*  257 */       line = JOptionPane.showInputDialog(mess0 + mess);
/*  258 */       if (line != null) {
/*      */         try {
/*  260 */           ph = Phasor.parsePhasor(line);
/*  261 */           finish = true;
/*      */         }
/*      */         catch (NumberFormatException e) {}
/*      */       }
/*      */     }
/*      */     
/*  267 */     return ph;
/*      */   }
/*      */   
/*      */ 
/*      */   public static final synchronized Phasor readPhasor(String mess, Phasor dflt)
/*      */   {
/*  273 */     String line = "";
/*  274 */     Phasor ph = new Phasor();
/*  275 */     boolean finish = false;
/*  276 */     String mess0 = "Input type: Phasor ('mag'<'phase'deg or 'mag'<'phase'rad)\n";
/*  277 */     String dfltmess = dflt + "";
/*  278 */     mess = mess + "\n";
/*      */     
/*  280 */     System.out.flush();
/*      */     
/*  282 */     while (!finish) {
/*  283 */       line = JOptionPane.showInputDialog(mess0 + mess + " [default value = " + dflt + "] ", dfltmess);
/*      */       
/*  285 */       if (line != null) {
/*  286 */         if (line.equals("")) {
/*  287 */           ph = dflt;
/*  288 */           finish = true;
/*  289 */           line = null;
/*      */         }
/*      */         else {
/*      */           try {
/*  293 */             ph = Phasor.parsePhasor(line);
/*  294 */             finish = true;
/*      */           }
/*      */           catch (NumberFormatException e) {}
/*      */         }
/*      */       }
/*      */     }
/*      */     
/*  301 */     return ph;
/*      */   }
/*      */   
/*      */ 
/*      */   public static final synchronized Phasor readPhasor(String mess, String dflt)
/*      */   {
/*  307 */     String line = "";
/*  308 */     Phasor ph = new Phasor();
/*  309 */     boolean finish = false;
/*  310 */     String mess0 = "Input type: Phasor ('mag'<'phase'deg or 'mag'<'phase'rad)\n";
/*  311 */     String dfltmess = dflt;
/*  312 */     mess = mess + "\n";
/*      */     
/*  314 */     System.out.flush();
/*      */     
/*      */ 
/*  317 */     while (!finish) {
/*  318 */       line = JOptionPane.showInputDialog(mess0 + mess + " [default value = " + dflt + "] ", dfltmess);
/*      */       
/*  320 */       if (line != null) {
/*  321 */         if (line.equals("")) {
/*  322 */           ph = Phasor.parsePhasor(dflt);
/*  323 */           finish = true;
/*  324 */           line = null;
/*      */         }
/*      */         else {
/*      */           try {
/*  328 */             ph = Phasor.parsePhasor(line);
/*  329 */             finish = true;
/*      */           }
/*      */           catch (NumberFormatException e) {}
/*      */         }
/*      */       }
/*      */     }
/*      */     
/*  336 */     return ph;
/*      */   }
/*      */   
/*      */ 
/*      */   public static final synchronized Phasor readPhasor()
/*      */   {
/*  342 */     String line = "";
/*  343 */     String mess = "Input type: Phasor ('mag'<'phase'deg or 'mag'<'phase'rad)";
/*  344 */     Phasor ph = new Phasor();
/*  345 */     boolean finish = false;
/*  346 */     System.out.flush();
/*      */     
/*  348 */     while (!finish) {
/*  349 */       line = JOptionPane.showInputDialog(mess);
/*  350 */       if (line != null) {
/*      */         try {
/*  352 */           ph = Phasor.parsePhasor(line.trim());
/*  353 */           finish = true;
/*      */         }
/*      */         catch (NumberFormatException e) {}
/*      */       }
/*      */     }
/*      */     
/*  359 */     return ph;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */   public static final synchronized float readFloat(String mess)
/*      */   {
/*  367 */     String line = "";
/*  368 */     float d = 0.0F;
/*  369 */     boolean finish = false;
/*  370 */     System.out.flush();
/*  371 */     String mess0 = "Input type: float\n";
/*      */     
/*  373 */     while (!finish) {
/*  374 */       line = JOptionPane.showInputDialog(mess0 + mess);
/*  375 */       if (line != null) {
/*      */         try {
/*  377 */           d = Float.parseFloat(line.trim());
/*  378 */           finish = true;
/*      */         }
/*      */         catch (NumberFormatException e) {}
/*      */       }
/*      */     }
/*      */     
/*  384 */     return d;
/*      */   }
/*      */   
/*      */ 
/*      */   public static final synchronized float readFloat(String mess, float dflt)
/*      */   {
/*  390 */     String line = "";
/*  391 */     float d = 0.0F;
/*  392 */     boolean finish = false;
/*  393 */     System.out.flush();
/*  394 */     String mess0 = "Input type: float\n";
/*  395 */     mess = mess + "\n";
/*  396 */     String dfltmess = dflt + "";
/*      */     
/*  398 */     while (!finish) {
/*  399 */       line = JOptionPane.showInputDialog(mess0 + mess + " [default value = " + dflt + "] ", dfltmess);
/*      */       
/*  401 */       if (line != null) {
/*  402 */         if (line.equals("")) {
/*  403 */           d = dflt;
/*  404 */           finish = true;
/*  405 */           line = null;
/*      */         }
/*      */         else {
/*      */           try {
/*  409 */             d = Float.parseFloat(line.trim());
/*  410 */             finish = true;
/*      */           }
/*      */           catch (NumberFormatException e) {}
/*      */         }
/*      */       }
/*      */     }
/*      */     
/*  417 */     return d;
/*      */   }
/*      */   
/*      */ 
/*      */   public static final synchronized float readFloat()
/*      */   {
/*  423 */     String line = "";
/*  424 */     String mess = "Input type: float";
/*  425 */     float d = 0.0F;
/*  426 */     boolean finish = false;
/*  427 */     System.out.flush();
/*      */     
/*  429 */     while (!finish) {
/*  430 */       line = JOptionPane.showInputDialog(mess);
/*  431 */       if (line != null) {
/*      */         try {
/*  433 */           d = Float.parseFloat(line.trim());
/*  434 */           finish = true;
/*      */         }
/*      */         catch (NumberFormatException e) {}
/*      */       }
/*      */     }
/*      */     
/*  440 */     return d;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */   public static final synchronized int readInt(String mess)
/*      */   {
/*  448 */     String line = "";
/*  449 */     int d = 0;
/*  450 */     boolean finish = false;
/*  451 */     System.out.flush();
/*  452 */     String mess0 = "Input type: int\n";
/*      */     
/*  454 */     while (!finish) {
/*  455 */       line = JOptionPane.showInputDialog(mess0 + mess);
/*  456 */       if (line != null) {
/*      */         try {
/*  458 */           d = Integer.parseInt(line.trim());
/*  459 */           finish = true;
/*      */         }
/*      */         catch (NumberFormatException e) {}
/*      */       }
/*      */     }
/*      */     
/*  465 */     return d;
/*      */   }
/*      */   
/*      */ 
/*      */   public static final synchronized int readInt(String mess, int dflt)
/*      */   {
/*  471 */     String line = "";
/*  472 */     int d = 0;
/*  473 */     boolean finish = false;
/*  474 */     String mess0 = "Input type: int\n";
/*  475 */     mess = mess + "\n";
/*  476 */     String dfltmess = dflt + "";
/*      */     
/*  478 */     System.out.flush();
/*      */     
/*  480 */     while (!finish) {
/*  481 */       line = JOptionPane.showInputDialog(mess0 + mess + " [default value = " + dflt + "] ", dfltmess);
/*      */       
/*  483 */       if (line != null) {
/*  484 */         if (line.equals("")) {
/*  485 */           d = dflt;
/*  486 */           finish = true;
/*  487 */           line = null;
/*      */         }
/*      */         else {
/*      */           try {
/*  491 */             d = Integer.parseInt(line.trim());
/*  492 */             finish = true;
/*      */           }
/*      */           catch (NumberFormatException e) {}
/*      */         }
/*      */       }
/*      */     }
/*      */     
/*  499 */     return d;
/*      */   }
/*      */   
/*      */ 
/*      */   public static final synchronized int readInt()
/*      */   {
/*  505 */     String line = "";
/*  506 */     String mess = "Input type: int";
/*  507 */     int d = 0;
/*  508 */     boolean finish = false;
/*  509 */     System.out.flush();
/*      */     
/*  511 */     while (!finish) {
/*  512 */       line = JOptionPane.showInputDialog(mess);
/*  513 */       if (line != null) {
/*      */         try {
/*  515 */           d = Integer.parseInt(line.trim());
/*  516 */           finish = true;
/*      */         }
/*      */         catch (NumberFormatException e) {}
/*      */       }
/*      */     }
/*      */     
/*  522 */     return d;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */   public static final synchronized long readLong(String mess)
/*      */   {
/*  529 */     String line = "";
/*  530 */     long d = 0L;
/*  531 */     boolean finish = false;
/*  532 */     String mess0 = "Input type: long\n";
/*      */     
/*  534 */     System.out.flush();
/*      */     
/*  536 */     while (!finish) {
/*  537 */       line = JOptionPane.showInputDialog(mess0 + mess);
/*  538 */       if (line != null) {
/*      */         try {
/*  540 */           d = Long.parseLong(line.trim());
/*  541 */           finish = true;
/*      */         }
/*      */         catch (NumberFormatException e) {}
/*      */       }
/*      */     }
/*      */     
/*  547 */     return d;
/*      */   }
/*      */   
/*      */ 
/*      */   public static final synchronized long readLong(String mess, long dflt)
/*      */   {
/*  553 */     String line = "";
/*  554 */     long d = 0L;
/*  555 */     boolean finish = false;
/*  556 */     String mess0 = "Input type: long\n";
/*  557 */     mess = mess + "\n";
/*  558 */     String dfltmess = dflt + "";
/*      */     
/*  560 */     System.out.flush();
/*      */     
/*  562 */     while (!finish) {
/*  563 */       line = JOptionPane.showInputDialog(mess0 + mess + " [default value = " + dflt + "] ", dfltmess);
/*      */       
/*  565 */       if (line != null) {
/*  566 */         if (line.equals("")) {
/*  567 */           d = dflt;
/*  568 */           finish = true;
/*  569 */           line = null;
/*      */         }
/*      */         else {
/*      */           try {
/*  573 */             d = Long.parseLong(line.trim());
/*  574 */             finish = true;
/*      */           }
/*      */           catch (NumberFormatException e) {}
/*      */         }
/*      */       }
/*      */     }
/*      */     
/*  581 */     return d;
/*      */   }
/*      */   
/*      */ 
/*      */   public static final synchronized long readLong()
/*      */   {
/*  587 */     String line = "";
/*  588 */     String mess = "Input type: long";
/*  589 */     long d = 0L;
/*  590 */     boolean finish = false;
/*      */     
/*  592 */     System.out.flush();
/*      */     
/*  594 */     while (!finish) {
/*  595 */       line = JOptionPane.showInputDialog(mess);
/*  596 */       if (line != null) {
/*      */         try {
/*  598 */           d = Long.parseLong(line.trim());
/*  599 */           finish = true;
/*      */         }
/*      */         catch (NumberFormatException e) {}
/*      */       }
/*      */     }
/*      */     
/*  605 */     return d;
/*      */   }
/*      */   
/*      */ 
/*      */   public static final synchronized long readShort(String mess)
/*      */   {
/*  611 */     String line = "";
/*  612 */     long d = 0L;
/*  613 */     boolean finish = false;
/*  614 */     String mess0 = "Input type: short\n";
/*      */     
/*  616 */     System.out.flush();
/*      */     
/*  618 */     while (!finish) {
/*  619 */       line = JOptionPane.showInputDialog(mess0 + mess);
/*  620 */       if (line != null) {
/*      */         try {
/*  622 */           d = Short.parseShort(line.trim());
/*  623 */           finish = true;
/*      */         }
/*      */         catch (NumberFormatException e) {}
/*      */       }
/*      */     }
/*      */     
/*  629 */     return d;
/*      */   }
/*      */   
/*      */ 
/*      */   public static final synchronized short readShort(String mess, short dflt)
/*      */   {
/*  635 */     String line = "";
/*  636 */     short d = 0;
/*  637 */     boolean finish = false;
/*  638 */     String mess0 = "Input type: short\n";
/*  639 */     mess = mess + "\n";
/*  640 */     String dfltmess = dflt + "";
/*      */     
/*  642 */     System.out.flush();
/*      */     
/*  644 */     while (!finish) {
/*  645 */       line = JOptionPane.showInputDialog(mess0 + mess + " [default value = " + dflt + "] ", dfltmess);
/*      */       
/*  647 */       if (line != null) {
/*  648 */         if (line.equals("")) {
/*  649 */           d = dflt;
/*  650 */           finish = true;
/*  651 */           line = null;
/*      */         }
/*      */         else {
/*      */           try {
/*  655 */             d = Short.parseShort(line.trim());
/*  656 */             finish = true;
/*      */           }
/*      */           catch (NumberFormatException e) {}
/*      */         }
/*      */       }
/*      */     }
/*      */     
/*  663 */     return d;
/*      */   }
/*      */   
/*      */ 
/*      */   public static final synchronized short readShort()
/*      */   {
/*  669 */     String line = "";
/*  670 */     String mess = "Input type: short";
/*  671 */     short d = 0;
/*  672 */     boolean finish = false;
/*      */     
/*  674 */     System.out.flush();
/*      */     
/*  676 */     while (!finish) {
/*  677 */       line = JOptionPane.showInputDialog(mess);
/*  678 */       if (line != null) {
/*      */         try {
/*  680 */           d = Short.parseShort(line.trim());
/*  681 */           finish = true;
/*      */         }
/*      */         catch (NumberFormatException e) {}
/*      */       }
/*      */     }
/*      */     
/*  687 */     return d;
/*      */   }
/*      */   
/*      */ 
/*      */   public static final synchronized long readByte(String mess)
/*      */   {
/*  693 */     String line = "";
/*  694 */     long d = 0L;
/*  695 */     boolean finish = false;
/*  696 */     String mess0 = "Input type: short\n";
/*      */     
/*  698 */     System.out.flush();
/*      */     
/*  700 */     while (!finish) {
/*  701 */       line = JOptionPane.showInputDialog(mess0 + mess);
/*  702 */       if (line != null) {
/*      */         try {
/*  704 */           d = Byte.parseByte(line.trim());
/*  705 */           finish = true;
/*      */         }
/*      */         catch (NumberFormatException e) {}
/*      */       }
/*      */     }
/*      */     
/*  711 */     return d;
/*      */   }
/*      */   
/*      */ 
/*      */   public static final synchronized byte readByte(String mess, byte dflt)
/*      */   {
/*  717 */     String line = "";
/*  718 */     byte d = 0;
/*  719 */     boolean finish = false;
/*  720 */     String mess0 = "Input type: byte\n";
/*  721 */     mess = mess + "\n";
/*  722 */     String dfltmess = dflt + "";
/*      */     
/*  724 */     System.out.flush();
/*      */     
/*  726 */     while (!finish) {
/*  727 */       line = JOptionPane.showInputDialog(mess0 + mess + " [default value = " + dflt + "] ", dfltmess);
/*      */       
/*  729 */       if (line != null) {
/*  730 */         if (line.equals("")) {
/*  731 */           d = dflt;
/*  732 */           finish = true;
/*  733 */           line = null;
/*      */         }
/*      */         else {
/*      */           try {
/*  737 */             d = Byte.parseByte(line.trim());
/*  738 */             finish = true;
/*      */           }
/*      */           catch (NumberFormatException e) {}
/*      */         }
/*      */       }
/*      */     }
/*      */     
/*  745 */     return d;
/*      */   }
/*      */   
/*      */ 
/*      */   public static final synchronized byte readByte()
/*      */   {
/*  751 */     String line = "";
/*  752 */     String mess = "Input type: byte";
/*  753 */     byte d = 0;
/*  754 */     boolean finish = false;
/*      */     
/*  756 */     System.out.flush();
/*      */     
/*  758 */     while (!finish) {
/*  759 */       line = JOptionPane.showInputDialog(mess);
/*  760 */       if (line != null) {
/*      */         try {
/*  762 */           d = Byte.parseByte(line.trim());
/*  763 */           finish = true;
/*      */         }
/*      */         catch (NumberFormatException e) {}
/*      */       }
/*      */     }
/*      */     
/*  769 */     return d;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */   public static final synchronized char readChar(String mess)
/*      */   {
/*  776 */     String line = "";
/*  777 */     char d = ' ';
/*  778 */     boolean finish = false;
/*  779 */     String mess0 = "Input type: char\n";
/*      */     
/*  781 */     System.out.flush();
/*      */     
/*  783 */     while (!finish) {
/*  784 */       line = JOptionPane.showInputDialog(mess0 + mess);
/*  785 */       if ((line != null) && 
/*  786 */         (!line.equals("")))
/*      */       {
/*      */ 
/*      */ 
/*  790 */         d = line.charAt(0);
/*  791 */         finish = true;
/*      */       }
/*      */     }
/*      */     
/*  795 */     return d;
/*      */   }
/*      */   
/*      */ 
/*      */   public static final synchronized char readChar(String mess, char dflt)
/*      */   {
/*  801 */     String line = "";
/*  802 */     char d = ' ';
/*  803 */     boolean finish = false;
/*  804 */     String mess0 = "Input type: char\n";
/*  805 */     mess = mess + "\n";
/*  806 */     String dfltmess = dflt + "";
/*      */     
/*  808 */     System.out.flush();
/*      */     
/*  810 */     while (!finish) {
/*  811 */       line = JOptionPane.showInputDialog(mess0 + mess + " [default value = " + dflt + "] ", dfltmess);
/*  812 */       if (line != null) {
/*  813 */         if (line.equals("")) {
/*  814 */           d = dflt;
/*  815 */           finish = true;
/*  816 */           line = null;
/*      */         }
/*      */         else {
/*  819 */           d = line.charAt(0);
/*  820 */           finish = true;
/*      */         }
/*      */       }
/*      */     }
/*  824 */     return d;
/*      */   }
/*      */   
/*      */ 
/*      */   public static final synchronized char readChar()
/*      */   {
/*  830 */     String line = "";
/*  831 */     String mess = "Input type: char";
/*  832 */     char d = ' ';
/*  833 */     boolean finish = false;
/*  834 */     System.out.flush();
/*      */     
/*  836 */     while (!finish) {
/*  837 */       line = JOptionPane.showInputDialog(mess);
/*  838 */       if ((line != null) && 
/*  839 */         (!line.equals("")))
/*      */       {
/*      */ 
/*      */ 
/*  843 */         d = line.charAt(0);
/*  844 */         finish = true;
/*      */       }
/*      */     }
/*      */     
/*  848 */     return d;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public static final synchronized String readLine(String mess)
/*      */   {
/*  857 */     String line = "";
/*  858 */     boolean finish = false;
/*  859 */     String mess0 = "Input type: String [a line]\n";
/*      */     
/*  861 */     System.out.flush();
/*      */     
/*  863 */     while (!finish) {
/*  864 */       line = JOptionPane.showInputDialog(mess0 + mess);
/*  865 */       if (line != null) {
/*  866 */         finish = true;
/*      */       }
/*      */     }
/*  869 */     return line;
/*      */   }
/*      */   
/*      */ 
/*      */   public static final synchronized String readLine(String mess, String dflt)
/*      */   {
/*  875 */     String line = "";
/*  876 */     boolean finish = false;
/*  877 */     String mess0 = "Input type: String [a line]\n";
/*  878 */     mess = mess + "\n";
/*  879 */     String dfltmess = dflt + "";
/*      */     
/*  881 */     System.out.flush();
/*      */     
/*  883 */     while (!finish) {
/*  884 */       line = JOptionPane.showInputDialog(mess0 + mess + " [default value = " + dflt + "] ", dfltmess);
/*  885 */       if (line != null) {
/*  886 */         if (line.equals("")) {
/*  887 */           line = dflt;
/*  888 */           finish = true;
/*      */         }
/*      */         else {
/*  891 */           finish = true;
/*      */         }
/*      */       }
/*      */     }
/*  895 */     return line;
/*      */   }
/*      */   
/*      */ 
/*      */   public static final synchronized String readLine()
/*      */   {
/*  901 */     String line = "";
/*  902 */     String mess = "Input type: String [a line]";
/*  903 */     boolean finish = false;
/*  904 */     System.out.flush();
/*      */     
/*  906 */     while (!finish) {
/*  907 */       line = JOptionPane.showInputDialog(mess);
/*  908 */       if (line != null) {
/*  909 */         finish = true;
/*      */       }
/*      */     }
/*  912 */     return line;
/*      */   }
/*      */   
/*      */ 
/*      */   public static final synchronized boolean readBoolean(String mess, boolean dflt)
/*      */   {
/*  918 */     String line = "";
/*  919 */     boolean b = false;
/*  920 */     boolean finish = false;
/*  921 */     System.out.flush();
/*  922 */     String mess0 = "Input boolean\n";
/*  923 */     mess = mess + "\n";
/*  924 */     String dfltmess = dflt + "";
/*      */     
/*  926 */     while (!finish) {
/*  927 */       line = JOptionPane.showInputDialog(mess0 + mess + " [default value = " + dflt + "] ", dfltmess);
/*      */       
/*  929 */       if (line != null) {
/*  930 */         if (line.equals("")) {
/*  931 */           b = dflt;
/*  932 */           finish = true;
/*  933 */           line = null;
/*      */ 
/*      */         }
/*  936 */         else if ((line.equals("true")) || (line.trim().equals("TRUE"))) {
/*  937 */           b = true;
/*  938 */           finish = true;
/*      */ 
/*      */         }
/*  941 */         else if ((line.equals("false")) || (line.trim().equals("FALSE"))) {
/*  942 */           b = false;
/*  943 */           finish = true;
/*      */         }
/*      */       }
/*      */     }
/*      */     
/*      */ 
/*  949 */     return b;
/*      */   }
/*      */   
/*      */ 
/*      */   public static final synchronized boolean readBoolean(String mess)
/*      */   {
/*  955 */     String line = "";
/*  956 */     boolean b = false;
/*  957 */     boolean finish = false;
/*  958 */     System.out.flush();
/*  959 */     String mess0 = "Input type: boolean\n";
/*      */     
/*  961 */     while (!finish) {
/*  962 */       line = JOptionPane.showInputDialog(mess0 + mess);
/*  963 */       if (line != null) {
/*  964 */         if ((line.equals("true")) || (line.trim().equals("TRUE"))) {
/*  965 */           b = true;
/*  966 */           finish = true;
/*      */ 
/*      */         }
/*  969 */         else if ((line.equals("false")) || (line.trim().equals("FALSE"))) {
/*  970 */           b = false;
/*  971 */           finish = true;
/*      */         }
/*      */       }
/*      */     }
/*      */     
/*  976 */     return b;
/*      */   }
/*      */   
/*      */ 
/*      */   public static final synchronized boolean readBoolean()
/*      */   {
/*  982 */     String line = "";
/*  983 */     String mess = "Input type: boolean";
/*  984 */     boolean b = false;
/*  985 */     boolean finish = false;
/*  986 */     System.out.flush();
/*      */     
/*  988 */     while (!finish) {
/*  989 */       line = JOptionPane.showInputDialog(mess);
/*  990 */       if (line != null) {
/*  991 */         if ((line.equals("true")) || (line.trim().equals("TRUE"))) {
/*  992 */           b = true;
/*  993 */           finish = true;
/*      */ 
/*      */         }
/*  996 */         else if ((line.equals("false")) || (line.trim().equals("FALSE"))) {
/*  997 */           b = false;
/*  998 */           finish = true;
/*      */         }
/*      */       }
/*      */     }
/*      */     
/* 1003 */     return b;
/*      */   }
/*      */   
/*      */ 
/*      */   public static final synchronized BigDecimal readBigDecimal(String mess)
/*      */   {
/* 1009 */     String line = "";
/* 1010 */     BigDecimal big = null;
/* 1011 */     boolean finish = false;
/* 1012 */     String mess0 = "Input type: BigDecimal\n";
/*      */     
/* 1014 */     System.out.flush();
/*      */     
/* 1016 */     while (!finish) {
/* 1017 */       line = JOptionPane.showInputDialog(mess0 + mess);
/* 1018 */       if (line != null) {
/*      */         try {
/* 1020 */           big = new BigDecimal(line);
/* 1021 */           finish = true;
/*      */         }
/*      */         catch (NumberFormatException e) {}
/*      */       }
/*      */     }
/*      */     
/* 1027 */     return big;
/*      */   }
/*      */   
/*      */ 
/*      */   public static final synchronized BigDecimal readBigDecimal(String mess, BigDecimal dflt)
/*      */   {
/* 1033 */     String line = "";
/* 1034 */     BigDecimal big = null;
/* 1035 */     boolean finish = false;
/* 1036 */     String mess0 = "Input type: BigDecimal\n";
/* 1037 */     String dfltmess = dflt.toString() + "";
/* 1038 */     mess = mess + "\n";
/*      */     
/* 1040 */     System.out.flush();
/*      */     
/* 1042 */     while (!finish) {
/* 1043 */       line = JOptionPane.showInputDialog(mess0 + mess + " [default value = " + dflt + "] ", dfltmess);
/*      */       
/* 1045 */       if (line != null) {
/* 1046 */         if (line.equals("")) {
/* 1047 */           big = dflt;
/* 1048 */           finish = true;
/* 1049 */           line = null;
/*      */         }
/*      */         else {
/*      */           try {
/* 1053 */             big = new BigDecimal(line);
/* 1054 */             finish = true;
/*      */           }
/*      */           catch (NumberFormatException e) {}
/*      */         }
/*      */       }
/*      */     }
/*      */     
/* 1061 */     return big;
/*      */   }
/*      */   
/*      */ 
/*      */   public static final synchronized BigDecimal readBigDecimal(String mess, String dflt)
/*      */   {
/* 1067 */     String line = "";
/* 1068 */     BigDecimal big = null;
/* 1069 */     boolean finish = false;
/* 1070 */     String mess0 = "Input type: BigDecimal\n";
/* 1071 */     String dfltmess = dflt;
/* 1072 */     mess = mess + "\n";
/*      */     
/* 1074 */     System.out.flush();
/*      */     
/*      */ 
/* 1077 */     while (!finish) {
/* 1078 */       line = JOptionPane.showInputDialog(mess0 + mess + " [default value = " + dflt + "] ", dfltmess);
/*      */       
/* 1080 */       if (line != null) {
/* 1081 */         if (line.equals("")) {
/* 1082 */           big = new BigDecimal(dflt);
/* 1083 */           finish = true;
/* 1084 */           line = null;
/*      */         }
/*      */         else {
/*      */           try {
/* 1088 */             big = new BigDecimal(line);
/* 1089 */             finish = true;
/*      */           }
/*      */           catch (NumberFormatException e) {}
/*      */         }
/*      */       }
/*      */     }
/*      */     
/* 1096 */     return big;
/*      */   }
/*      */   
/*      */ 
/*      */   public static final synchronized BigDecimal readBigDecimal(String mess, double dflt)
/*      */   {
/* 1102 */     String line = "";
/* 1103 */     BigDecimal big = null;
/* 1104 */     boolean finish = false;
/* 1105 */     String mess0 = "Input type: BigDecimal\n";
/* 1106 */     Double dfltD = new Double(dflt);
/* 1107 */     String dfltmess = dfltD.toString();
/* 1108 */     mess = mess + "\n";
/*      */     
/* 1110 */     System.out.flush();
/*      */     
/*      */ 
/* 1113 */     while (!finish) {
/* 1114 */       line = JOptionPane.showInputDialog(mess0 + mess + " [default value = " + dflt + "] ", dfltmess);
/*      */       
/* 1116 */       if (line != null) {
/* 1117 */         if (line.equals("")) {
/* 1118 */           big = new BigDecimal(dfltmess);
/* 1119 */           finish = true;
/* 1120 */           line = null;
/*      */         }
/*      */         else {
/*      */           try {
/* 1124 */             big = new BigDecimal(line);
/* 1125 */             finish = true;
/*      */           }
/*      */           catch (NumberFormatException e) {}
/*      */         }
/*      */       }
/*      */     }
/*      */     
/* 1132 */     return big;
/*      */   }
/*      */   
/*      */ 
/*      */   public static final synchronized BigDecimal readBigDecimal(String mess, float dflt)
/*      */   {
/* 1138 */     String line = "";
/* 1139 */     BigDecimal big = null;
/* 1140 */     boolean finish = false;
/* 1141 */     String mess0 = "Input type: BigDecimal\n";
/* 1142 */     Float dfltF = new Float(dflt);
/* 1143 */     String dfltmess = dfltF.toString();
/* 1144 */     mess = mess + "\n";
/*      */     
/* 1146 */     System.out.flush();
/*      */     
/*      */ 
/* 1149 */     while (!finish) {
/* 1150 */       line = JOptionPane.showInputDialog(mess0 + mess + " [default value = " + dflt + "] ", dfltmess);
/*      */       
/* 1152 */       if (line != null) {
/* 1153 */         if (line.equals("")) {
/* 1154 */           big = new BigDecimal(dfltmess);
/* 1155 */           finish = true;
/* 1156 */           line = null;
/*      */         }
/*      */         else {
/*      */           try {
/* 1160 */             big = new BigDecimal(line);
/* 1161 */             finish = true;
/*      */           }
/*      */           catch (NumberFormatException e) {}
/*      */         }
/*      */       }
/*      */     }
/*      */     
/* 1168 */     return big;
/*      */   }
/*      */   
/*      */ 
/*      */   public static final synchronized BigDecimal readBigDecimal(String mess, long dflt)
/*      */   {
/* 1174 */     String line = "";
/* 1175 */     BigDecimal big = null;
/* 1176 */     boolean finish = false;
/* 1177 */     String mess0 = "Input type: BigDecimal\n";
/* 1178 */     Long dfltF = new Long(dflt);
/* 1179 */     String dfltmess = dfltF.toString();
/* 1180 */     mess = mess + "\n";
/*      */     
/* 1182 */     System.out.flush();
/*      */     
/*      */ 
/* 1185 */     while (!finish) {
/* 1186 */       line = JOptionPane.showInputDialog(mess0 + mess + " [default value = " + dflt + "] ", dfltmess);
/*      */       
/* 1188 */       if (line != null) {
/* 1189 */         if (line.equals("")) {
/* 1190 */           big = new BigDecimal(dfltmess);
/* 1191 */           finish = true;
/* 1192 */           line = null;
/*      */         }
/*      */         else {
/*      */           try {
/* 1196 */             big = new BigDecimal(line);
/* 1197 */             finish = true;
/*      */           }
/*      */           catch (NumberFormatException e) {}
/*      */         }
/*      */       }
/*      */     }
/*      */     
/* 1204 */     return big;
/*      */   }
/*      */   
/*      */ 
/*      */   public static final synchronized BigDecimal readBigDecimal(String mess, int dflt)
/*      */   {
/* 1210 */     String line = "";
/* 1211 */     BigDecimal big = null;
/* 1212 */     boolean finish = false;
/* 1213 */     String mess0 = "Input type: BigDecimal\n";
/* 1214 */     Integer dfltF = new Integer(dflt);
/* 1215 */     String dfltmess = dfltF.toString();
/* 1216 */     mess = mess + "\n";
/*      */     
/* 1218 */     System.out.flush();
/*      */     
/*      */ 
/* 1221 */     while (!finish) {
/* 1222 */       line = JOptionPane.showInputDialog(mess0 + mess + " [default value = " + dflt + "] ", dfltmess);
/*      */       
/* 1224 */       if (line != null) {
/* 1225 */         if (line.equals("")) {
/* 1226 */           big = new BigDecimal(dfltmess);
/* 1227 */           finish = true;
/* 1228 */           line = null;
/*      */         }
/*      */         else {
/*      */           try {
/* 1232 */             big = new BigDecimal(line);
/* 1233 */             finish = true;
/*      */           }
/*      */           catch (NumberFormatException e) {}
/*      */         }
/*      */       }
/*      */     }
/*      */     
/* 1240 */     return big;
/*      */   }
/*      */   
/*      */ 
/*      */   public static final synchronized BigDecimal readBigDecimal()
/*      */   {
/* 1246 */     String line = "";
/* 1247 */     String mess = "Input type: BigDecimal";
/* 1248 */     BigDecimal big = null;
/* 1249 */     boolean finish = false;
/* 1250 */     System.out.flush();
/*      */     
/* 1252 */     while (!finish) {
/* 1253 */       line = JOptionPane.showInputDialog(mess);
/* 1254 */       if (line != null) {
/*      */         try {
/* 1256 */           big = new BigDecimal(line.trim());
/* 1257 */           finish = true;
/*      */         }
/*      */         catch (NumberFormatException e) {}
/*      */       }
/*      */     }
/*      */     
/* 1263 */     return big;
/*      */   }
/*      */   
/*      */ 
/*      */   public static final synchronized BigInteger readBigInteger(String mess)
/*      */   {
/* 1269 */     String line = "";
/* 1270 */     BigInteger big = null;
/* 1271 */     boolean finish = false;
/* 1272 */     String mess0 = "Input type: BigInteger\n";
/*      */     
/* 1274 */     System.out.flush();
/*      */     
/* 1276 */     while (!finish) {
/* 1277 */       line = JOptionPane.showInputDialog(mess0 + mess);
/* 1278 */       if (line != null) {
/*      */         try {
/* 1280 */           big = new BigInteger(line);
/* 1281 */           finish = true;
/*      */         }
/*      */         catch (NumberFormatException e) {}
/*      */       }
/*      */     }
/*      */     
/* 1287 */     return big;
/*      */   }
/*      */   
/*      */ 
/*      */   public static final synchronized BigInteger readBigInteger(String mess, BigInteger dflt)
/*      */   {
/* 1293 */     String line = "";
/* 1294 */     BigInteger big = null;
/* 1295 */     boolean finish = false;
/* 1296 */     String mess0 = "Input type: BigInteger\n";
/* 1297 */     String dfltmess = dflt.toString() + "";
/* 1298 */     mess = mess + "\n";
/*      */     
/* 1300 */     System.out.flush();
/*      */     
/* 1302 */     while (!finish) {
/* 1303 */       line = JOptionPane.showInputDialog(mess0 + mess + " [default value = " + dflt + "] ", dfltmess);
/*      */       
/* 1305 */       if (line != null) {
/* 1306 */         if (line.equals("")) {
/* 1307 */           big = dflt;
/* 1308 */           finish = true;
/* 1309 */           line = null;
/*      */         }
/*      */         else {
/*      */           try {
/* 1313 */             big = new BigInteger(line);
/* 1314 */             finish = true;
/*      */           }
/*      */           catch (NumberFormatException e) {}
/*      */         }
/*      */       }
/*      */     }
/*      */     
/* 1321 */     return big;
/*      */   }
/*      */   
/*      */ 
/*      */   public static final synchronized BigInteger readBigInteger(String mess, String dflt)
/*      */   {
/* 1327 */     String line = "";
/* 1328 */     BigInteger big = null;
/* 1329 */     boolean finish = false;
/* 1330 */     String mess0 = "Input type: BigInteger\n";
/* 1331 */     String dfltmess = dflt;
/* 1332 */     mess = mess + "\n";
/*      */     
/* 1334 */     System.out.flush();
/*      */     
/*      */ 
/* 1337 */     while (!finish) {
/* 1338 */       line = JOptionPane.showInputDialog(mess0 + mess + " [default value = " + dflt + "] ", dfltmess);
/*      */       
/* 1340 */       if (line != null) {
/* 1341 */         if (line.equals("")) {
/* 1342 */           big = new BigInteger(dflt);
/* 1343 */           finish = true;
/* 1344 */           line = null;
/*      */         }
/*      */         else {
/*      */           try {
/* 1348 */             big = new BigInteger(line);
/* 1349 */             finish = true;
/*      */           }
/*      */           catch (NumberFormatException e) {}
/*      */         }
/*      */       }
/*      */     }
/*      */     
/* 1356 */     return big;
/*      */   }
/*      */   
/*      */ 
/*      */   public static final synchronized BigInteger readBigInteger(String mess, long dflt)
/*      */   {
/* 1362 */     String line = "";
/* 1363 */     BigInteger big = null;
/* 1364 */     boolean finish = false;
/* 1365 */     String mess0 = "Input type: BigInteger\n";
/* 1366 */     Long dfltF = new Long(dflt);
/* 1367 */     String dfltmess = dfltF.toString();
/* 1368 */     mess = mess + "\n";
/*      */     
/* 1370 */     System.out.flush();
/*      */     
/*      */ 
/* 1373 */     while (!finish) {
/* 1374 */       line = JOptionPane.showInputDialog(mess0 + mess + " [default value = " + dflt + "] ", dfltmess);
/*      */       
/* 1376 */       if (line != null) {
/* 1377 */         if (line.equals("")) {
/* 1378 */           big = new BigInteger(dfltmess);
/* 1379 */           finish = true;
/* 1380 */           line = null;
/*      */         }
/*      */         else {
/*      */           try {
/* 1384 */             big = new BigInteger(line);
/* 1385 */             finish = true;
/*      */           }
/*      */           catch (NumberFormatException e) {}
/*      */         }
/*      */       }
/*      */     }
/*      */     
/* 1392 */     return big;
/*      */   }
/*      */   
/*      */ 
/*      */   public static final synchronized BigInteger readBigInteger(String mess, int dflt)
/*      */   {
/* 1398 */     String line = "";
/* 1399 */     BigInteger big = null;
/* 1400 */     boolean finish = false;
/* 1401 */     String mess0 = "Input type: BigInteger\n";
/* 1402 */     Integer dfltF = new Integer(dflt);
/* 1403 */     String dfltmess = dfltF.toString();
/* 1404 */     mess = mess + "\n";
/*      */     
/* 1406 */     System.out.flush();
/*      */     
/*      */ 
/* 1409 */     while (!finish) {
/* 1410 */       line = JOptionPane.showInputDialog(mess0 + mess + " [default value = " + dflt + "] ", dfltmess);
/*      */       
/* 1412 */       if (line != null) {
/* 1413 */         if (line.equals("")) {
/* 1414 */           big = new BigInteger(dfltmess);
/* 1415 */           finish = true;
/* 1416 */           line = null;
/*      */         }
/*      */         else {
/*      */           try {
/* 1420 */             big = new BigInteger(line);
/* 1421 */             finish = true;
/*      */           }
/*      */           catch (NumberFormatException e) {}
/*      */         }
/*      */       }
/*      */     }
/*      */     
/* 1428 */     return big;
/*      */   }
/*      */   
/*      */ 
/*      */   public static final synchronized BigInteger readBigInteger()
/*      */   {
/* 1434 */     String line = "";
/* 1435 */     String mess = "Input type: BigInteger";
/* 1436 */     BigInteger big = null;
/* 1437 */     boolean finish = false;
/* 1438 */     System.out.flush();
/*      */     
/* 1440 */     while (!finish) {
/* 1441 */       line = JOptionPane.showInputDialog(mess);
/* 1442 */       if (line != null) {
/*      */         try {
/* 1444 */           big = new BigInteger(line.trim());
/* 1445 */           finish = true;
/*      */         }
/*      */         catch (NumberFormatException e) {}
/*      */       }
/*      */     }
/*      */     
/* 1451 */     return big;
/*      */   }
/*      */   
/*      */   public static final synchronized boolean yesNo(String question)
/*      */   {
/* 1456 */     int ans = JOptionPane.showConfirmDialog(null, question, "Db Class Yes or No Box", 0, 3);
/* 1457 */     boolean ansb = false;
/* 1458 */     if (ans == 0) ansb = true;
/* 1459 */     return ansb;
/*      */   }
/*      */   
/*      */   public static final synchronized boolean noYes(String question)
/*      */   {
/* 1464 */     Object[] opts = { "Yes", "No" };
/* 1465 */     int ans = JOptionPane.showOptionDialog(null, question, "Db Class Yes or No Box", 0, 3, null, opts, opts[1]);
/* 1466 */     boolean ansb = false;
/* 1467 */     if (ans == 0) ansb = true;
/* 1468 */     return ansb;
/*      */   }
/*      */   
/*      */ 
/*      */   public static final synchronized void show(String message, double output)
/*      */   {
/* 1474 */     JOptionPane.showMessageDialog(null, message + " " + output, "Db.show (double)", 1);
/*      */   }
/*      */   
/*      */   public static final synchronized void show(String message, double output, int trunc)
/*      */   {
/* 1479 */     JOptionPane.showMessageDialog(null, message + " " + Fmath.truncate(output, trunc), "Db.show (double)", 1);
/*      */   }
/*      */   
/*      */   public static final synchronized void show(String message, float output)
/*      */   {
/* 1484 */     JOptionPane.showMessageDialog(null, message + " " + output, "Db.show (float)", 1);
/*      */   }
/*      */   
/*      */   public static final synchronized void show(String message, float output, int trunc)
/*      */   {
/* 1489 */     JOptionPane.showMessageDialog(null, message + " " + Fmath.truncate(output, trunc), "Db.show (float)", 1);
/*      */   }
/*      */   
/*      */   public static final synchronized void show(String message, BigDecimal output)
/*      */   {
/* 1494 */     JOptionPane.showMessageDialog(null, message + " " + output.toString(), "Db.show (BigDecimal)", 1);
/*      */   }
/*      */   
/*      */   public static final synchronized void show(String message, BigInteger output)
/*      */   {
/* 1499 */     JOptionPane.showMessageDialog(null, message + " " + output.toString(), "Db.show (BigInteger)", 1);
/*      */   }
/*      */   
/*      */   public static final synchronized void show(String message, int output)
/*      */   {
/* 1504 */     JOptionPane.showMessageDialog(null, message + " " + output, "Db.show (int)", 1);
/*      */   }
/*      */   
/*      */   public static final synchronized void show(String message, long output)
/*      */   {
/* 1509 */     JOptionPane.showMessageDialog(null, message + " " + output, "Db.show (long)", 1);
/*      */   }
/*      */   
/*      */   public static final synchronized void show(String message, short output)
/*      */   {
/* 1514 */     JOptionPane.showMessageDialog(null, message + " " + output, "Db.show (short)", 1);
/*      */   }
/*      */   
/*      */   public static final synchronized void show(String message, byte output)
/*      */   {
/* 1519 */     JOptionPane.showMessageDialog(null, message + " " + output, "Db.show (byte)", 1);
/*      */   }
/*      */   
/*      */   public static final synchronized void show(String message, Complex output)
/*      */   {
/* 1524 */     JOptionPane.showMessageDialog(null, message + " " + output, "Db.show (Complex)", 1);
/*      */   }
/*      */   
/*      */   public static final synchronized void show(String message, Complex output, int trunc)
/*      */   {
/* 1529 */     JOptionPane.showMessageDialog(null, message + " " + Complex.truncate(output, trunc), "Db.show (Complex)", 1);
/*      */   }
/*      */   
/*      */   public static final synchronized void show(String message, Phasor output)
/*      */   {
/* 1534 */     JOptionPane.showMessageDialog(null, message + " " + output, "Db.show (Phasor)", 1);
/*      */   }
/*      */   
/*      */   public static final synchronized void show(String message, Phasor output, int trunc)
/*      */   {
/* 1539 */     JOptionPane.showMessageDialog(null, message + " " + Phasor.truncate(output, trunc), "Db.show (Phasor)", 1);
/*      */   }
/*      */   
/*      */   public static final synchronized void show(String message, ErrorProp output)
/*      */   {
/* 1544 */     JOptionPane.showMessageDialog(null, message + " " + output, "Db.show (ErrorProp)", 1);
/*      */   }
/*      */   
/*      */   public static final synchronized void show(String message, ErrorProp output, int trunc)
/*      */   {
/* 1549 */     JOptionPane.showMessageDialog(null, message + " " + ErrorProp.truncate(output, trunc), "Db.show (ErrorProp)", 1);
/*      */   }
/*      */   
/*      */   public static final synchronized void show(String message, ComplexErrorProp output)
/*      */   {
/* 1554 */     JOptionPane.showMessageDialog(null, message + " " + output, "Db.show (ComplexErrorProp)", 1);
/*      */   }
/*      */   
/*      */   public static final synchronized void show(String message, ComplexErrorProp output, int trunc)
/*      */   {
/* 1559 */     JOptionPane.showMessageDialog(null, message + " " + ComplexErrorProp.truncate(output, trunc), "Db.show (ComplexErrorProp)", 1);
/*      */   }
/*      */   
/*      */   public static final synchronized void show(String message, boolean output) {
/* 1563 */     JOptionPane.showMessageDialog(null, message + " " + output, "Db.show (boolean)", 1);
/*      */   }
/*      */   
/*      */   public static final synchronized void show(String message, char output)
/*      */   {
/* 1568 */     JOptionPane.showMessageDialog(null, message + " " + output, "Db.show (char)", 1);
/*      */   }
/*      */   
/*      */   public static final synchronized void show(String message, String output)
/*      */   {
/* 1573 */     JOptionPane.showMessageDialog(null, message + " " + output, "Db.show (String)", 1);
/*      */   }
/*      */   
/*      */   public static final synchronized void show(String message)
/*      */   {
/* 1578 */     JOptionPane.showMessageDialog(null, message, "Db.show (message only)", 1);
/*      */   }
/*      */   
/*      */   public static final synchronized int optionBox(String headerComment, String[] comments, String[] boxTitles, int defaultBox)
/*      */   {
/* 1583 */     int nChoice = boxTitles.length;
/* 1584 */     if (nChoice != comments.length) throw new IllegalArgumentException("There must be the same number of boxTitles and comments");
/* 1585 */     Object[] options = new Object[nChoice];
/* 1586 */     for (int i = 0; i < nChoice; i++) {
/* 1587 */       options[i] = ("(" + (i + 1) + ") " + boxTitles[i]);
/*      */     }
/* 1589 */     String quest = "1. " + comments[0] + "\n";
/* 1590 */     for (int i = 1; i < nChoice; i++) {
/* 1591 */       quest = quest + (i + 1) + ". " + comments[i] + "\n";
/*      */     }
/*      */     
/* 1594 */     return 1 + JOptionPane.showOptionDialog(null, quest, headerComment, 1, 3, null, options, options[(defaultBox - 1)]);
/*      */   }
/*      */   
/*      */   public static final synchronized int optionBox(String headerComment, String quest, String[] boxTitles, int defaultBox)
/*      */   {
/* 1599 */     int nChoice = boxTitles.length;
/* 1600 */     Object[] options = new Object[nChoice];
/* 1601 */     for (int i = 0; i < nChoice; i++) {
/* 1602 */       options[i] = ("(" + (i + 1) + ") " + boxTitles[i]);
/*      */     }
/*      */     
/* 1605 */     return 1 + JOptionPane.showOptionDialog(null, quest, headerComment, 1, 3, null, options, options[(defaultBox - 1)]);
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */   public static final synchronized void endProgram()
/*      */   {
/* 1612 */     int ans = JOptionPane.showConfirmDialog(null, "Do you wish to end the program", "End Program", 0, 3);
/* 1613 */     if (ans == 0) {
/* 1614 */       System.exit(0);
/*      */     }
/*      */     else {
/* 1617 */       JOptionPane.showMessageDialog(null, "Now you must press the appropriate escape key/s, e.g. Ctrl C, to exit this program");
/*      */     }
/*      */   }
/*      */ }


/* Location:              /home/menick/Scrivania/ProcessMining/CNMining/CNMining.jar!/flanagan/io/Db.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       0.7.1
 */