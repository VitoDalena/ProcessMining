/*      */ package flanagan.io;
/*      */ 
/*      */ import flanagan.circuits.Phasor;
/*      */ import flanagan.complex.Complex;
/*      */ import java.io.BufferedReader;
/*      */ import java.io.IOException;
/*      */ import java.io.InputStreamReader;
/*      */ import java.io.PrintStream;
/*      */ import java.math.BigDecimal;
/*      */ import java.math.BigInteger;
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
/*      */ public class KeyboardInput
/*      */ {
/*   46 */   private BufferedReader input = null;
/*      */   
/*      */ 
/*      */   public KeyboardInput()
/*      */   {
/*   51 */     this.input = new BufferedReader(new InputStreamReader(System.in));
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */   public final synchronized double readDouble(String mess)
/*      */   {
/*   58 */     String line = "";
/*   59 */     double d = 0.0D;
/*   60 */     boolean finish = false;
/*      */     
/*   62 */     System.out.print(mess + " ");
/*   63 */     System.out.flush();
/*      */     
/*   65 */     while (!finish) {
/*   66 */       line = enterLine();
/*      */       try {
/*   68 */         d = Double.parseDouble(line.trim());
/*   69 */         finish = true;
/*      */       } catch (NumberFormatException e) {
/*   71 */         System.out.println("You did not enter a valid double\nRe-enter the number");
/*      */       }
/*      */     }
/*      */     
/*   75 */     return d;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */   public final synchronized double readDouble(String mess, double dflt)
/*      */   {
/*   82 */     String line = "";
/*   83 */     double d = 0.0D;
/*   84 */     boolean finish = false;
/*      */     
/*   86 */     System.out.print(mess + " [default value = " + dflt + "] ");
/*   87 */     System.out.flush();
/*      */     
/*   89 */     while (!finish) {
/*   90 */       line = enterLine();
/*   91 */       if (line.length() == 0) {
/*   92 */         d = dflt;
/*   93 */         finish = true;
/*      */       }
/*      */       else {
/*      */         try {
/*   97 */           d = Double.parseDouble(line.trim());
/*   98 */           finish = true;
/*      */         } catch (NumberFormatException e) {
/*  100 */           System.out.println("You did not enter a valid double\nRe-enter the number");
/*      */         }
/*      */       }
/*      */     }
/*  104 */     return d;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */   public final synchronized double readDouble()
/*      */   {
/*  111 */     String line = "";
/*  112 */     double d = 0.0D;
/*  113 */     boolean finish = false;
/*      */     
/*  115 */     System.out.print(" ");
/*  116 */     System.out.flush();
/*      */     
/*  118 */     while (!finish) {
/*  119 */       line = enterLine();
/*      */       try {
/*  121 */         d = Double.parseDouble(line.trim());
/*  122 */         finish = true;
/*      */       } catch (NumberFormatException e) {
/*  124 */         System.out.println("You did not enter a valid double\nRe-enter the number");
/*      */       }
/*      */     }
/*      */     
/*  128 */     return d;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */   public final synchronized float readFloat(String mess)
/*      */   {
/*  135 */     String line = "";
/*  136 */     float f = 0.0F;
/*  137 */     boolean finish = false;
/*      */     
/*  139 */     System.out.print(mess + " ");
/*  140 */     System.out.flush();
/*      */     
/*  142 */     while (!finish) {
/*  143 */       line = enterLine();
/*      */       try {
/*  145 */         f = Float.parseFloat(line.trim());
/*  146 */         finish = true;
/*      */       } catch (NumberFormatException e) {
/*  148 */         System.out.println("You did not enter a valid float\nRe-enter the number");
/*      */       }
/*      */     }
/*      */     
/*  152 */     return f;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */   public final synchronized float readFloat(String mess, float dflt)
/*      */   {
/*  159 */     String line = "";
/*  160 */     float f = 0.0F;
/*  161 */     boolean finish = false;
/*      */     
/*  163 */     System.out.print(mess + " [default value = " + dflt + "] ");
/*  164 */     System.out.flush();
/*      */     
/*  166 */     while (!finish) {
/*  167 */       line = enterLine();
/*  168 */       if (line.length() == 0) {
/*  169 */         f = dflt;
/*  170 */         finish = true;
/*      */       }
/*      */       else {
/*      */         try {
/*  174 */           f = Float.parseFloat(line.trim());
/*  175 */           finish = true;
/*      */         } catch (NumberFormatException e) {
/*  177 */           System.out.println("You did not enter a valid float\nRe-enter the number");
/*      */         }
/*      */       }
/*      */     }
/*  181 */     return f;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */   public final synchronized float readFloat()
/*      */   {
/*  188 */     String line = "";
/*  189 */     float f = 0.0F;
/*  190 */     boolean finish = false;
/*      */     
/*  192 */     System.out.print(" ");
/*  193 */     System.out.flush();
/*      */     
/*  195 */     while (!finish) {
/*  196 */       line = enterLine();
/*      */       try {
/*  198 */         f = Float.parseFloat(line.trim());
/*  199 */         finish = true;
/*      */       } catch (NumberFormatException e) {
/*  201 */         System.out.println("You did not enter a valid float\nRe-enter the number");
/*      */       }
/*      */     }
/*      */     
/*  205 */     return f;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */   public final synchronized BigDecimal readBigDecimal(String mess)
/*      */   {
/*  212 */     String line = "";
/*  213 */     BigDecimal big = null;
/*  214 */     boolean finish = false;
/*      */     
/*  216 */     System.out.print(mess + " ");
/*  217 */     System.out.flush();
/*      */     
/*  219 */     while (!finish) {
/*  220 */       line = enterLine();
/*      */       try {
/*  222 */         big = new BigDecimal(line.trim());
/*  223 */         finish = true;
/*      */       } catch (NumberFormatException e) {
/*  225 */         System.out.println("You did not enter a valid BigDecimal\nRe-enter the number");
/*      */       }
/*      */     }
/*      */     
/*  229 */     return big;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */   public final synchronized BigDecimal readBigDecimal(String mess, BigDecimal dflt)
/*      */   {
/*  237 */     String line = "";
/*  238 */     BigDecimal big = null;
/*  239 */     boolean finish = false;
/*      */     
/*  241 */     System.out.print(mess + " [default value = " + dflt.toString() + "] ");
/*  242 */     System.out.flush();
/*      */     
/*  244 */     while (!finish) {
/*  245 */       line = enterLine();
/*  246 */       if (line.length() == 0) {
/*  247 */         big = dflt;
/*  248 */         finish = true;
/*      */       }
/*      */       else {
/*      */         try {
/*  252 */           big = new BigDecimal(line.trim());
/*  253 */           finish = true;
/*      */         } catch (NumberFormatException e) {
/*  255 */           System.out.println("You did not enter a valid BigDecimal\nRe-enter the number");
/*      */         }
/*      */       }
/*      */     }
/*  259 */     return big;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */   public final synchronized BigDecimal readBigDecimal(String mess, double dflt)
/*      */   {
/*  266 */     String line = "";
/*  267 */     BigDecimal big = null;
/*  268 */     boolean finish = false;
/*  269 */     Double dfltD = new Double(dflt);
/*  270 */     String dfltM = dfltD.toString();
/*      */     
/*  272 */     System.out.print(mess + " [default value = " + dfltM + "] ");
/*  273 */     System.out.flush();
/*      */     
/*  275 */     while (!finish) {
/*  276 */       line = enterLine();
/*  277 */       if (line.length() == 0) {
/*  278 */         big = new BigDecimal(dfltM);
/*  279 */         finish = true;
/*      */       }
/*      */       else {
/*      */         try {
/*  283 */           big = new BigDecimal(line.trim());
/*  284 */           finish = true;
/*      */         } catch (NumberFormatException e) {
/*  286 */           System.out.println("You did not enter a valid BigDecimal\nRe-enter the number");
/*      */         }
/*      */       }
/*      */     }
/*  290 */     return big;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */   public final synchronized BigDecimal readBigDecimal(String mess, float dflt)
/*      */   {
/*  298 */     String line = "";
/*  299 */     BigDecimal big = null;
/*  300 */     boolean finish = false;
/*  301 */     Float dfltF = new Float(dflt);
/*  302 */     String dfltM = dfltF.toString();
/*      */     
/*  304 */     System.out.print(mess + " [default value = " + dfltM + "] ");
/*  305 */     System.out.flush();
/*      */     
/*  307 */     while (!finish) {
/*  308 */       line = enterLine();
/*  309 */       if (line.length() == 0) {
/*  310 */         big = new BigDecimal(dfltM);
/*  311 */         finish = true;
/*      */       }
/*      */       else {
/*      */         try {
/*  315 */           big = new BigDecimal(line.trim());
/*  316 */           finish = true;
/*      */         } catch (NumberFormatException e) {
/*  318 */           System.out.println("You did not enter a valid BigDecimal\nRe-enter the number");
/*      */         }
/*      */       }
/*      */     }
/*  322 */     return big;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */   public final synchronized BigDecimal readBigDecimal(String mess, long dflt)
/*      */   {
/*  330 */     String line = "";
/*  331 */     BigDecimal big = null;
/*  332 */     boolean finish = false;
/*  333 */     Long dfltL = new Long(dflt);
/*  334 */     String dfltM = dfltL.toString();
/*      */     
/*  336 */     System.out.print(mess + " [default value = " + dfltM + "] ");
/*  337 */     System.out.flush();
/*      */     
/*  339 */     while (!finish) {
/*  340 */       line = enterLine();
/*  341 */       if (line.length() == 0) {
/*  342 */         big = new BigDecimal(dfltM);
/*  343 */         finish = true;
/*      */       }
/*      */       else {
/*      */         try {
/*  347 */           big = new BigDecimal(line.trim());
/*  348 */           finish = true;
/*      */         } catch (NumberFormatException e) {
/*  350 */           System.out.println("You did not enter a valid BigDecimal\nRe-enter the number");
/*      */         }
/*      */       }
/*      */     }
/*  354 */     return big;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */   public final synchronized BigDecimal readBigDecimal(String mess, int dflt)
/*      */   {
/*  362 */     String line = "";
/*  363 */     BigDecimal big = null;
/*  364 */     boolean finish = false;
/*  365 */     Integer dfltI = new Integer(dflt);
/*  366 */     String dfltM = dfltI.toString();
/*      */     
/*  368 */     System.out.print(mess + " [default value = " + dfltM + "] ");
/*  369 */     System.out.flush();
/*      */     
/*  371 */     while (!finish) {
/*  372 */       line = enterLine();
/*  373 */       if (line.length() == 0) {
/*  374 */         big = new BigDecimal(dfltM);
/*  375 */         finish = true;
/*      */       }
/*      */       else {
/*      */         try {
/*  379 */           big = new BigDecimal(line.trim());
/*  380 */           finish = true;
/*      */         } catch (NumberFormatException e) {
/*  382 */           System.out.println("You did not enter a valid BigDecimal\nRe-enter the number");
/*      */         }
/*      */       }
/*      */     }
/*  386 */     return big;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */   public final synchronized BigDecimal readBigDecimal(String mess, String dflt)
/*      */   {
/*  394 */     String line = "";
/*  395 */     BigDecimal big = null;
/*  396 */     boolean finish = false;
/*      */     
/*  398 */     System.out.print(mess + " [default value = " + dflt + "] ");
/*  399 */     System.out.flush();
/*      */     
/*  401 */     while (!finish) {
/*  402 */       line = enterLine();
/*  403 */       if (line.length() == 0) {
/*  404 */         big = new BigDecimal(dflt);
/*  405 */         finish = true;
/*      */       }
/*      */       else {
/*      */         try {
/*  409 */           big = new BigDecimal(line.trim());
/*  410 */           finish = true;
/*      */         } catch (NumberFormatException e) {
/*  412 */           System.out.println("You did not enter a valid BigDecimal\nRe-enter the number");
/*      */         }
/*      */       }
/*      */     }
/*  416 */     return big;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */   public final synchronized BigDecimal readBigDecimal()
/*      */   {
/*  423 */     String line = "";
/*  424 */     BigDecimal big = null;
/*  425 */     boolean finish = false;
/*      */     
/*  427 */     System.out.print(" ");
/*  428 */     System.out.flush();
/*      */     
/*  430 */     while (!finish) {
/*  431 */       line = enterLine();
/*      */       try {
/*  433 */         big = new BigDecimal(line.trim());
/*  434 */         finish = true;
/*      */       } catch (NumberFormatException e) {
/*  436 */         System.out.println("You did not enter a valid BigDecimal\nRe-enter the number");
/*      */       }
/*      */     }
/*      */     
/*  440 */     return big;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */   public final synchronized int readInt(String mess)
/*      */   {
/*  447 */     String line = "";
/*  448 */     int ii = 0;
/*  449 */     boolean finish = false;
/*      */     
/*  451 */     System.out.print(mess + " ");
/*  452 */     System.out.flush();
/*      */     
/*  454 */     while (!finish) {
/*  455 */       line = enterLine();
/*      */       try {
/*  457 */         ii = Integer.parseInt(line.trim());
/*  458 */         finish = true;
/*      */       } catch (NumberFormatException e) {
/*  460 */         System.out.println("You did not enter a valid int\nRe-enter the number");
/*      */       }
/*      */     }
/*      */     
/*  464 */     return ii;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */   public final synchronized int readInt(String mess, int dflt)
/*      */   {
/*  471 */     String line = "";
/*  472 */     int ii = 0;
/*  473 */     boolean finish = false;
/*      */     
/*  475 */     System.out.print(mess + " [default value = " + dflt + "] ");
/*  476 */     System.out.flush();
/*      */     
/*  478 */     while (!finish) {
/*  479 */       line = enterLine();
/*  480 */       if (line.length() == 0) {
/*  481 */         ii = dflt;
/*  482 */         finish = true;
/*      */       }
/*      */       else {
/*      */         try {
/*  486 */           ii = Integer.parseInt(line.trim());
/*  487 */           finish = true;
/*      */         } catch (NumberFormatException e) {
/*  489 */           System.out.println("You did not enter a valid int\nRe-enter the number");
/*      */         }
/*      */       }
/*      */     }
/*  493 */     return ii;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */   public final synchronized int readInt()
/*      */   {
/*  500 */     String line = "";
/*  501 */     int ii = 0;
/*  502 */     boolean finish = false;
/*      */     
/*  504 */     System.out.print(" ");
/*  505 */     System.out.flush();
/*      */     
/*  507 */     while (!finish) {
/*  508 */       line = enterLine();
/*      */       try {
/*  510 */         ii = Integer.parseInt(line.trim());
/*  511 */         finish = true;
/*      */       } catch (NumberFormatException e) {
/*  513 */         System.out.println("You did not enter a valid int\nRe-enter the number");
/*      */       }
/*      */     }
/*      */     
/*  517 */     return ii;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */   public final synchronized long readLong(String mess)
/*      */   {
/*  524 */     String line = "";
/*  525 */     long ll = 0L;
/*  526 */     boolean finish = false;
/*      */     
/*  528 */     System.out.print(mess + " ");
/*  529 */     System.out.flush();
/*      */     
/*  531 */     while (!finish) {
/*  532 */       line = enterLine();
/*      */       try {
/*  534 */         ll = Long.parseLong(line.trim());
/*  535 */         finish = true;
/*      */       } catch (NumberFormatException e) {
/*  537 */         System.out.println("You did not enter a valid long\nRe-enter the number");
/*      */       }
/*      */     }
/*      */     
/*  541 */     return ll;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */   public final synchronized long readLong(String mess, long dflt)
/*      */   {
/*  548 */     String line = "";
/*  549 */     long ll = 0L;
/*  550 */     boolean finish = false;
/*      */     
/*  552 */     System.out.print(mess + " [default value = " + dflt + "] ");
/*  553 */     System.out.flush();
/*      */     
/*  555 */     while (!finish) {
/*  556 */       line = enterLine();
/*  557 */       if (line.length() == 0) {
/*  558 */         ll = dflt;
/*  559 */         finish = true;
/*      */       }
/*      */       else {
/*      */         try {
/*  563 */           ll = Long.parseLong(line.trim());
/*  564 */           finish = true;
/*      */         } catch (NumberFormatException e) {
/*  566 */           System.out.println("You did not enter a valid long\nRe-enter the number");
/*      */         }
/*      */       }
/*      */     }
/*  570 */     return ll;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */   public final synchronized long readLong()
/*      */   {
/*  577 */     String line = "";
/*  578 */     long ll = 0L;
/*  579 */     boolean finish = false;
/*      */     
/*  581 */     System.out.print(" ");
/*  582 */     System.out.flush();
/*      */     
/*  584 */     while (!finish) {
/*  585 */       line = enterLine();
/*      */       try {
/*  587 */         ll = Long.parseLong(line.trim());
/*  588 */         finish = true;
/*      */       } catch (NumberFormatException e) {
/*  590 */         System.out.println("You did not enter a valid long\nRe-enter the number");
/*      */       }
/*      */     }
/*      */     
/*  594 */     return ll;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */   public final synchronized BigInteger readBigInteger(String mess)
/*      */   {
/*  601 */     String line = "";
/*  602 */     BigInteger big = null;
/*  603 */     boolean finish = false;
/*      */     
/*  605 */     System.out.print(mess + " ");
/*  606 */     System.out.flush();
/*      */     
/*  608 */     while (!finish) {
/*  609 */       line = enterLine();
/*      */       try {
/*  611 */         big = new BigInteger(line.trim());
/*  612 */         finish = true;
/*      */       } catch (NumberFormatException e) {
/*  614 */         System.out.println("You did not enter a valid BigInteger\nRe-enter the number");
/*      */       }
/*      */     }
/*      */     
/*  618 */     return big;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */   public final synchronized BigInteger readBigInteger(String mess, BigInteger dflt)
/*      */   {
/*  626 */     String line = "";
/*  627 */     BigInteger big = null;
/*  628 */     boolean finish = false;
/*      */     
/*  630 */     System.out.print(mess + " [default value = " + dflt.toString() + "] ");
/*  631 */     System.out.flush();
/*      */     
/*  633 */     while (!finish) {
/*  634 */       line = enterLine();
/*  635 */       if (line.length() == 0) {
/*  636 */         big = dflt;
/*  637 */         finish = true;
/*      */       }
/*      */       else {
/*      */         try {
/*  641 */           big = new BigInteger(line.trim());
/*  642 */           finish = true;
/*      */         } catch (NumberFormatException e) {
/*  644 */           System.out.println("You did not enter a valid BigInteger\nRe-enter the number");
/*      */         }
/*      */       }
/*      */     }
/*  648 */     return big;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */   public final synchronized BigInteger readBigInteger(String mess, long dflt)
/*      */   {
/*  655 */     String line = "";
/*  656 */     BigInteger big = null;
/*  657 */     boolean finish = false;
/*  658 */     Long dfltL = new Long(dflt);
/*  659 */     String dfltM = dfltL.toString();
/*      */     
/*  661 */     System.out.print(mess + " [default value = " + dfltM + "] ");
/*  662 */     System.out.flush();
/*      */     
/*  664 */     while (!finish) {
/*  665 */       line = enterLine();
/*  666 */       if (line.length() == 0) {
/*  667 */         big = new BigInteger(dfltM);
/*  668 */         finish = true;
/*      */       }
/*      */       else {
/*      */         try {
/*  672 */           big = new BigInteger(line.trim());
/*  673 */           finish = true;
/*      */         } catch (NumberFormatException e) {
/*  675 */           System.out.println("You did not enter a valid BigInteger\nRe-enter the number");
/*      */         }
/*      */       }
/*      */     }
/*  679 */     return big;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */   public final synchronized BigInteger readBigInteger(String mess, int dflt)
/*      */   {
/*  687 */     String line = "";
/*  688 */     BigInteger big = null;
/*  689 */     boolean finish = false;
/*  690 */     Integer dfltI = new Integer(dflt);
/*  691 */     String dfltM = dfltI.toString();
/*      */     
/*  693 */     System.out.print(mess + " [default value = " + dfltM + "] ");
/*  694 */     System.out.flush();
/*      */     
/*  696 */     while (!finish) {
/*  697 */       line = enterLine();
/*  698 */       if (line.length() == 0) {
/*  699 */         big = new BigInteger(dfltM);
/*  700 */         finish = true;
/*      */       }
/*      */       else {
/*      */         try {
/*  704 */           big = new BigInteger(line.trim());
/*  705 */           finish = true;
/*      */         } catch (NumberFormatException e) {
/*  707 */           System.out.println("You did not enter a valid BigInteger\nRe-enter the number");
/*      */         }
/*      */       }
/*      */     }
/*  711 */     return big;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */   public final synchronized BigInteger readBigInteger(String mess, String dflt)
/*      */   {
/*  719 */     String line = "";
/*  720 */     BigInteger big = null;
/*  721 */     boolean finish = false;
/*      */     
/*  723 */     System.out.print(mess + " [default value = " + dflt + "] ");
/*  724 */     System.out.flush();
/*      */     
/*  726 */     while (!finish) {
/*  727 */       line = enterLine();
/*  728 */       if (line.length() == 0) {
/*  729 */         big = new BigInteger(dflt);
/*  730 */         finish = true;
/*      */       }
/*      */       else {
/*      */         try {
/*  734 */           big = new BigInteger(line.trim());
/*  735 */           finish = true;
/*      */         } catch (NumberFormatException e) {
/*  737 */           System.out.println("You did not enter a valid BigInteger\nRe-enter the number");
/*      */         }
/*      */       }
/*      */     }
/*  741 */     return big;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */   public final synchronized BigInteger readBigInteger()
/*      */   {
/*  748 */     String line = "";
/*  749 */     BigInteger big = null;
/*  750 */     boolean finish = false;
/*      */     
/*  752 */     System.out.print(" ");
/*  753 */     System.out.flush();
/*      */     
/*  755 */     while (!finish) {
/*  756 */       line = enterLine();
/*      */       try {
/*  758 */         big = new BigInteger(line.trim());
/*  759 */         finish = true;
/*      */       } catch (NumberFormatException e) {
/*  761 */         System.out.println("You did not enter a valid BigInteger\nRe-enter the number");
/*      */       }
/*      */     }
/*      */     
/*  765 */     return big;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */   public final synchronized short readShort(String mess)
/*      */   {
/*  773 */     String line = "";
/*  774 */     short ss = 0;
/*  775 */     boolean finish = false;
/*      */     
/*  777 */     System.out.print(mess + " ");
/*  778 */     System.out.flush();
/*      */     
/*  780 */     while (!finish) {
/*  781 */       line = enterLine();
/*      */       try {
/*  783 */         ss = Short.parseShort(line.trim());
/*  784 */         finish = true;
/*      */       } catch (NumberFormatException e) {
/*  786 */         System.out.println("You did not enter a valid short\nRe-enter the number");
/*      */       }
/*      */     }
/*      */     
/*  790 */     return ss;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */   public final synchronized short readShort(String mess, short dflt)
/*      */   {
/*  797 */     String line = "";
/*  798 */     short ss = 0;
/*  799 */     boolean finish = false;
/*      */     
/*  801 */     System.out.print(mess + " [default value = " + dflt + "] ");
/*  802 */     System.out.flush();
/*      */     
/*  804 */     while (!finish) {
/*  805 */       line = enterLine();
/*  806 */       if (line.length() == 0) {
/*  807 */         ss = dflt;
/*  808 */         finish = true;
/*      */       }
/*      */       else {
/*      */         try {
/*  812 */           ss = Short.parseShort(line.trim());
/*  813 */           finish = true;
/*      */         } catch (NumberFormatException e) {
/*  815 */           System.out.println("You did not enter a valid short\nRe-enter the number");
/*      */         }
/*      */       }
/*      */     }
/*  819 */     return ss;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */   public final synchronized short readShort()
/*      */   {
/*  826 */     String line = "";
/*  827 */     short ss = 0;
/*  828 */     boolean finish = false;
/*      */     
/*  830 */     System.out.print(" ");
/*  831 */     System.out.flush();
/*      */     
/*  833 */     while (!finish) {
/*  834 */       line = enterLine();
/*      */       try {
/*  836 */         ss = Short.parseShort(line.trim());
/*  837 */         finish = true;
/*      */       } catch (NumberFormatException e) {
/*  839 */         System.out.println("You did not enter a valid short\nRe-enter the number");
/*      */       }
/*      */     }
/*      */     
/*  843 */     return ss;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */   public final synchronized byte readByte(String mess)
/*      */   {
/*  850 */     String line = "";
/*  851 */     byte bb = 0;
/*  852 */     boolean finish = false;
/*      */     
/*  854 */     System.out.print(mess + " ");
/*  855 */     System.out.flush();
/*      */     
/*  857 */     while (!finish) {
/*  858 */       line = enterLine();
/*      */       try {
/*  860 */         bb = Byte.parseByte(line.trim());
/*  861 */         finish = true;
/*      */       } catch (NumberFormatException e) {
/*  863 */         System.out.println("You did not enter a valid byte\nRe-enter the number");
/*      */       }
/*      */     }
/*      */     
/*  867 */     return bb;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */   public final synchronized byte readByte(String mess, byte dflt)
/*      */   {
/*  874 */     String line = "";
/*  875 */     byte bb = 0;
/*  876 */     boolean finish = false;
/*      */     
/*  878 */     System.out.print(mess + " [default value = " + dflt + "] ");
/*  879 */     System.out.flush();
/*      */     
/*  881 */     while (!finish) {
/*  882 */       line = enterLine();
/*  883 */       if (line.length() == 0) {
/*  884 */         bb = dflt;
/*  885 */         finish = true;
/*      */       }
/*      */       else {
/*      */         try {
/*  889 */           bb = Byte.parseByte(line.trim());
/*  890 */           finish = true;
/*      */         } catch (NumberFormatException e) {
/*  892 */           System.out.println("You did not enter a valid byte\nRe-enter the number");
/*      */         }
/*      */       }
/*      */     }
/*  896 */     return bb;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */   public final synchronized byte readByte()
/*      */   {
/*  903 */     String line = "";
/*  904 */     byte bb = 0;
/*  905 */     boolean finish = false;
/*      */     
/*  907 */     System.out.print(" ");
/*  908 */     System.out.flush();
/*      */     
/*  910 */     while (!finish) {
/*  911 */       line = enterLine();
/*      */       try {
/*  913 */         bb = Byte.parseByte(line.trim());
/*  914 */         finish = true;
/*      */       } catch (NumberFormatException e) {
/*  916 */         System.out.println("You did not enter a valid byte\nRe-enter the number");
/*      */       }
/*      */     }
/*      */     
/*  920 */     return bb;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */   public final synchronized char readChar(String mess)
/*      */   {
/*  927 */     String line = "";
/*  928 */     char ch = ' ';
/*  929 */     boolean finish = false;
/*      */     
/*  931 */     System.out.print(mess + " ");
/*  932 */     System.out.flush();
/*      */     
/*  934 */     line = enterLine();
/*  935 */     line = line.trim();
/*  936 */     ch = line.charAt(0);
/*      */     
/*  938 */     return ch;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */   public final synchronized char readChar(String mess, char dflt)
/*      */   {
/*  945 */     String line = "";
/*  946 */     char ch = ' ';
/*  947 */     boolean finish = false;
/*      */     
/*  949 */     System.out.print(mess + " [default value = " + dflt + "] ");
/*  950 */     System.out.flush();
/*      */     
/*  952 */     line = enterLine();
/*  953 */     line = line.trim();
/*  954 */     ch = line.charAt(0);
/*      */     
/*  956 */     return ch;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */   public final synchronized char readChar()
/*      */   {
/*  963 */     String line = "";
/*  964 */     char ch = ' ';
/*  965 */     boolean finish = false;
/*      */     
/*  967 */     System.out.print(" ");
/*  968 */     System.out.flush();
/*      */     
/*  970 */     line = enterLine();
/*  971 */     line = line.trim();
/*  972 */     ch = line.charAt(0);
/*      */     
/*  974 */     return ch;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */   public final synchronized boolean readBoolean(String mess)
/*      */   {
/*  981 */     String line = "";
/*  982 */     boolean b = false;
/*  983 */     boolean finish = false;
/*      */     
/*  985 */     System.out.print(mess + " ");
/*  986 */     System.out.flush();
/*      */     
/*  988 */     while (!finish) {
/*  989 */       line = enterLine();
/*  990 */       if ((line.trim().equals("true")) || (line.trim().equals("TRUE"))) {
/*  991 */         b = true;
/*  992 */         finish = true;
/*      */ 
/*      */       }
/*  995 */       else if ((line.trim().equals("false")) || (line.trim().equals("FALSE"))) {
/*  996 */         b = false;
/*  997 */         finish = true;
/*      */       }
/*      */       else {
/* 1000 */         System.out.println("You did not enter a valid boolean\nRe-enter the number");
/*      */       }
/*      */     }
/*      */     
/* 1004 */     return b;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */   public final synchronized boolean readBoolean(String mess, boolean dflt)
/*      */   {
/* 1011 */     String line = "";
/* 1012 */     boolean b = false;
/* 1013 */     boolean finish = false;
/* 1014 */     System.out.print(mess + " [default value = " + dflt + "] ");
/* 1015 */     System.out.flush();
/*      */     
/* 1017 */     while (!finish) {
/* 1018 */       line = enterLine();
/* 1019 */       if ((line.trim().equals("true")) || (line.trim().equals("TRUE"))) {
/* 1020 */         b = true;
/* 1021 */         finish = true;
/*      */ 
/*      */       }
/* 1024 */       else if ((line.trim().equals("false")) || (line.trim().equals("FALSE"))) {
/* 1025 */         b = false;
/* 1026 */         finish = true;
/*      */       }
/*      */       else {
/* 1029 */         System.out.println("You did not enter a valid boolean\nRe-enter the number");
/*      */       }
/*      */     }
/*      */     
/* 1033 */     return b;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */   public final synchronized boolean readBoolean()
/*      */   {
/* 1040 */     String line = "";
/* 1041 */     boolean b = false;
/* 1042 */     boolean finish = false;
/*      */     
/* 1044 */     System.out.print(" ");
/* 1045 */     System.out.flush();
/*      */     
/* 1047 */     while (!finish) {
/* 1048 */       line = enterLine();
/* 1049 */       if ((line.trim().equals("true")) || (line.trim().equals("TRUE"))) {
/* 1050 */         b = true;
/* 1051 */         finish = true;
/*      */ 
/*      */       }
/* 1054 */       else if ((line.trim().equals("false")) || (line.trim().equals("FALSE"))) {
/* 1055 */         b = false;
/* 1056 */         finish = true;
/*      */       }
/*      */       else {
/* 1059 */         System.out.println("You did not enter a valid boolean\nRe-enter the number");
/*      */       }
/*      */     }
/*      */     
/* 1063 */     return b;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */   public final synchronized Complex readComplex(String mess)
/*      */   {
/* 1070 */     return Complex.readComplex(mess);
/*      */   }
/*      */   
/*      */ 
/*      */   public final synchronized Complex readComplex(String mess, String dflt)
/*      */   {
/* 1076 */     return Complex.readComplex(mess, dflt);
/*      */   }
/*      */   
/*      */ 
/*      */   public final synchronized Complex readComplex(String mess, Complex dflt)
/*      */   {
/* 1082 */     return Complex.readComplex(mess, dflt);
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public final synchronized Complex readComplex()
/*      */   {
/* 1091 */     return Complex.readComplex();
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */   public final synchronized Phasor readPhasor(String mess)
/*      */   {
/* 1098 */     return Phasor.readPhasor(mess);
/*      */   }
/*      */   
/*      */ 
/*      */   public final synchronized Phasor readPhasor(String mess, String dflt)
/*      */   {
/* 1104 */     return Phasor.readPhasor(mess, dflt);
/*      */   }
/*      */   
/*      */ 
/*      */   public final synchronized Phasor readPhasor(String mess, Phasor dflt)
/*      */   {
/* 1110 */     return Phasor.readPhasor(mess, dflt);
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public final synchronized Phasor readPhasor()
/*      */   {
/* 1119 */     return Phasor.readPhasor();
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */   public final synchronized String readLine(String mess)
/*      */   {
/* 1126 */     System.out.print(mess + " ");
/* 1127 */     System.out.flush();
/*      */     
/* 1129 */     return enterLine();
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */   public final synchronized String readLine(String mess, String dflt)
/*      */   {
/* 1136 */     String line = "";
/* 1137 */     System.out.print(mess + " [default option = " + dflt + "] ");
/* 1138 */     System.out.flush();
/*      */     
/* 1140 */     line = enterLine();
/* 1141 */     if (line.length() == 0) { line = dflt;
/*      */     }
/* 1143 */     return line;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */   private final synchronized String readLine()
/*      */   {
/* 1151 */     return enterLine();
/*      */   }
/*      */   
/*      */ 
/*      */   private final synchronized String enterLine()
/*      */   {
/* 1157 */     String line = "";
/*      */     try
/*      */     {
/* 1160 */       line = this.input.readLine();
/*      */     } catch (IOException e) {
/* 1162 */       System.out.println(e);
/*      */     }
/*      */     
/* 1165 */     return line;
/*      */   }
/*      */ }


/* Location:              /home/menick/Scrivania/ProcessMining/CNMining/CNMining.jar!/flanagan/io/KeyboardInput.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       0.7.1
 */