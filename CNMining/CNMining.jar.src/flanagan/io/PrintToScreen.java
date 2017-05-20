/*     */ package flanagan.io;
/*     */ 
/*     */ import flanagan.circuits.Phasor;
/*     */ import flanagan.complex.Complex;
/*     */ import flanagan.math.ArrayMaths;
/*     */ import flanagan.math.Fmath;
/*     */ import java.io.PrintStream;
/*     */ import java.math.BigDecimal;
/*     */ import java.math.BigInteger;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class PrintToScreen
/*     */ {
/*     */   public static void print(double[] aa)
/*     */   {
/*  52 */     for (int i = 0; i < aa.length; i++) {
/*  53 */       System.out.print(aa[i] + "   ");
/*     */     }
/*  55 */     System.out.println();
/*     */   }
/*     */   
/*     */ 
/*     */   public static void print(double[] aa, int trunc)
/*     */   {
/*  61 */     for (int i = 0; i < aa.length; i++) {
/*  62 */       System.out.print(Fmath.truncate(aa[i], trunc) + "   ");
/*     */     }
/*  64 */     System.out.println();
/*     */   }
/*     */   
/*     */ 
/*     */   public static void println(double[] aa)
/*     */   {
/*  70 */     for (int i = 0; i < aa.length; i++) {
/*  71 */       System.out.println(aa[i] + "   ");
/*     */     }
/*     */   }
/*     */   
/*     */ 
/*     */   public static void println(double[] aa, int trunc)
/*     */   {
/*  78 */     for (int i = 0; i < aa.length; i++) {
/*  79 */       System.out.println(Fmath.truncate(aa[i], trunc) + "   ");
/*     */     }
/*     */   }
/*     */   
/*     */ 
/*     */   public static void print(float[] aa)
/*     */   {
/*  86 */     for (int i = 0; i < aa.length; i++) {
/*  87 */       System.out.print(aa[i] + "   ");
/*     */     }
/*  89 */     System.out.println();
/*     */   }
/*     */   
/*     */ 
/*     */   public static void print(float[] aa, int trunc)
/*     */   {
/*  95 */     for (int i = 0; i < aa.length; i++) {
/*  96 */       System.out.print(Fmath.truncate(aa[i], trunc) + "   ");
/*     */     }
/*  98 */     System.out.println();
/*     */   }
/*     */   
/*     */ 
/*     */   public static void println(float[] aa)
/*     */   {
/* 104 */     for (int i = 0; i < aa.length; i++) {
/* 105 */       System.out.println(aa[i] + "   ");
/*     */     }
/*     */   }
/*     */   
/*     */ 
/*     */   public static void println(float[] aa, int trunc)
/*     */   {
/* 112 */     for (int i = 0; i < aa.length; i++) {
/* 113 */       System.out.println(Fmath.truncate(aa[i], trunc) + "   ");
/*     */     }
/*     */   }
/*     */   
/*     */ 
/*     */   public static void print(int[] aa)
/*     */   {
/* 120 */     for (int i = 0; i < aa.length; i++) {
/* 121 */       System.out.print(aa[i] + "   ");
/*     */     }
/* 123 */     System.out.println();
/*     */   }
/*     */   
/*     */ 
/*     */   public static void println(int[] aa)
/*     */   {
/* 129 */     for (int i = 0; i < aa.length; i++) {
/* 130 */       System.out.println(aa[i] + "   ");
/*     */     }
/*     */   }
/*     */   
/*     */ 
/*     */   public static void print(long[] aa)
/*     */   {
/* 137 */     for (int i = 0; i < aa.length; i++) {
/* 138 */       System.out.print(aa[i] + "   ");
/*     */     }
/* 140 */     System.out.println();
/*     */   }
/*     */   
/*     */ 
/*     */   public static void println(long[] aa)
/*     */   {
/* 146 */     for (int i = 0; i < aa.length; i++) {
/* 147 */       System.out.println(aa[i] + "   ");
/*     */     }
/*     */   }
/*     */   
/*     */ 
/*     */   public static void print(short[] aa)
/*     */   {
/* 154 */     for (int i = 0; i < aa.length; i++) {
/* 155 */       System.out.print(aa[i] + "   ");
/*     */     }
/* 157 */     System.out.println();
/*     */   }
/*     */   
/*     */ 
/*     */   public static void println(short[] aa)
/*     */   {
/* 163 */     for (int i = 0; i < aa.length; i++) {
/* 164 */       System.out.println(aa[i] + "   ");
/*     */     }
/*     */   }
/*     */   
/*     */ 
/*     */   public static void print(char[] aa)
/*     */   {
/* 171 */     for (int i = 0; i < aa.length; i++) {
/* 172 */       System.out.print(aa[i] + "   ");
/*     */     }
/* 174 */     System.out.println();
/*     */   }
/*     */   
/*     */ 
/*     */   public static void println(char[] aa)
/*     */   {
/* 180 */     for (int i = 0; i < aa.length; i++) {
/* 181 */       System.out.println(aa[i] + "   ");
/*     */     }
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */   public static void print(byte[] aa)
/*     */   {
/* 189 */     for (int i = 0; i < aa.length; i++) {
/* 190 */       System.out.print(aa[i] + "   ");
/*     */     }
/* 192 */     System.out.println();
/*     */   }
/*     */   
/*     */ 
/*     */   public static void println(byte[] aa)
/*     */   {
/* 198 */     for (int i = 0; i < aa.length; i++) {
/* 199 */       System.out.println(aa[i] + "   ");
/*     */     }
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */   public static void print(Double[] aa)
/*     */   {
/* 207 */     for (int i = 0; i < aa.length; i++) {
/* 208 */       System.out.print(aa[i] + "   ");
/*     */     }
/* 210 */     System.out.println();
/*     */   }
/*     */   
/*     */ 
/*     */   public static void print(Double[] aa, int trunc)
/*     */   {
/* 216 */     ArrayMaths am = new ArrayMaths(aa);
/* 217 */     am = am.truncate(trunc);
/* 218 */     Double[] aaa = am.array_as_Double();
/* 219 */     for (int i = 0; i < aa.length; i++) {
/* 220 */       System.out.print(aaa[i] + "   ");
/*     */     }
/* 222 */     System.out.println();
/*     */   }
/*     */   
/*     */ 
/*     */   public static void println(Double[] aa)
/*     */   {
/* 228 */     for (int i = 0; i < aa.length; i++) {
/* 229 */       System.out.println(aa[i] + "   ");
/*     */     }
/*     */   }
/*     */   
/*     */ 
/*     */   public static void println(Double[] aa, int trunc)
/*     */   {
/* 236 */     ArrayMaths am = new ArrayMaths(aa);
/* 237 */     am = am.truncate(trunc);
/* 238 */     Double[] aaa = am.array_as_Double();
/* 239 */     for (int i = 0; i < aa.length; i++) {
/* 240 */       System.out.println(aaa[i] + "   ");
/*     */     }
/*     */   }
/*     */   
/*     */ 
/*     */   public static void print(Float[] aa)
/*     */   {
/* 247 */     for (int i = 0; i < aa.length; i++) {
/* 248 */       System.out.print(aa[i] + "   ");
/*     */     }
/* 250 */     System.out.println();
/*     */   }
/*     */   
/*     */ 
/*     */   public static void print(Float[] aa, int trunc)
/*     */   {
/* 256 */     ArrayMaths am = new ArrayMaths(aa);
/* 257 */     am = am.truncate(trunc);
/* 258 */     Float[] aaa = am.array_as_Float();
/* 259 */     for (int i = 0; i < aa.length; i++) {
/* 260 */       System.out.print(aaa[i] + "   ");
/*     */     }
/* 262 */     System.out.println();
/*     */   }
/*     */   
/*     */ 
/*     */   public static void println(Float[] aa)
/*     */   {
/* 268 */     for (int i = 0; i < aa.length; i++) {
/* 269 */       System.out.println(aa[i] + "   ");
/*     */     }
/*     */   }
/*     */   
/*     */ 
/*     */   public static void println(Float[] aa, int trunc)
/*     */   {
/* 276 */     ArrayMaths am = new ArrayMaths(aa);
/* 277 */     am = am.truncate(trunc);
/* 278 */     Float[] aaa = am.array_as_Float();
/* 279 */     for (int i = 0; i < aa.length; i++) {
/* 280 */       System.out.println(aaa[i] + "   ");
/*     */     }
/*     */   }
/*     */   
/*     */ 
/*     */   public static void print(Integer[] aa)
/*     */   {
/* 287 */     for (int i = 0; i < aa.length; i++) {
/* 288 */       System.out.print(aa[i] + "   ");
/*     */     }
/* 290 */     System.out.println();
/*     */   }
/*     */   
/*     */ 
/*     */   public static void println(Integer[] aa)
/*     */   {
/* 296 */     for (int i = 0; i < aa.length; i++) {
/* 297 */       System.out.println(aa[i] + "   ");
/*     */     }
/*     */   }
/*     */   
/*     */ 
/*     */   public static void print(Long[] aa)
/*     */   {
/* 304 */     for (int i = 0; i < aa.length; i++) {
/* 305 */       System.out.print(aa[i] + "   ");
/*     */     }
/* 307 */     System.out.println();
/*     */   }
/*     */   
/*     */ 
/*     */   public static void println(Long[] aa)
/*     */   {
/* 313 */     for (int i = 0; i < aa.length; i++) {
/* 314 */       System.out.println(aa[i] + "   ");
/*     */     }
/*     */   }
/*     */   
/*     */ 
/*     */   public static void print(Short[] aa)
/*     */   {
/* 321 */     for (int i = 0; i < aa.length; i++) {
/* 322 */       System.out.print(aa[i] + "   ");
/*     */     }
/* 324 */     System.out.println();
/*     */   }
/*     */   
/*     */ 
/*     */   public static void println(Short[] aa)
/*     */   {
/* 330 */     for (int i = 0; i < aa.length; i++) {
/* 331 */       System.out.println(aa[i] + "   ");
/*     */     }
/*     */   }
/*     */   
/*     */ 
/*     */   public static void print(Character[] aa)
/*     */   {
/* 338 */     for (int i = 0; i < aa.length; i++) {
/* 339 */       System.out.print(aa[i] + "   ");
/*     */     }
/* 341 */     System.out.println();
/*     */   }
/*     */   
/*     */ 
/*     */   public static void println(Character[] aa)
/*     */   {
/* 347 */     for (int i = 0; i < aa.length; i++) {
/* 348 */       System.out.println(aa[i] + "   ");
/*     */     }
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */   public static void print(Byte[] aa)
/*     */   {
/* 356 */     for (int i = 0; i < aa.length; i++) {
/* 357 */       System.out.print(aa[i] + "   ");
/*     */     }
/* 359 */     System.out.println();
/*     */   }
/*     */   
/*     */ 
/*     */   public static void println(Byte[] aa)
/*     */   {
/* 365 */     for (int i = 0; i < aa.length; i++) {
/* 366 */       System.out.println(aa[i] + "   ");
/*     */     }
/*     */   }
/*     */   
/*     */ 
/*     */   public static void print(String[] aa)
/*     */   {
/* 373 */     for (int i = 0; i < aa.length; i++) {
/* 374 */       System.out.print(aa[i] + "   ");
/*     */     }
/* 376 */     System.out.println();
/*     */   }
/*     */   
/*     */ 
/*     */   public static void println(String[] aa)
/*     */   {
/* 382 */     for (int i = 0; i < aa.length; i++) {
/* 383 */       System.out.println(aa[i] + "   ");
/*     */     }
/*     */   }
/*     */   
/*     */ 
/*     */   public static void print(Complex[] aa)
/*     */   {
/* 390 */     for (int i = 0; i < aa.length; i++) {
/* 391 */       System.out.print(aa[i] + "   ");
/*     */     }
/* 393 */     System.out.println();
/*     */   }
/*     */   
/*     */ 
/*     */   public static void print(Complex[] aa, int trunc)
/*     */   {
/* 399 */     for (int i = 0; i < aa.length; i++) {
/* 400 */       System.out.print(Complex.truncate(aa[i], trunc) + "   ");
/*     */     }
/* 402 */     System.out.println();
/*     */   }
/*     */   
/*     */ 
/*     */   public static void println(Complex[] aa)
/*     */   {
/* 408 */     for (int i = 0; i < aa.length; i++) {
/* 409 */       System.out.println(aa[i] + "   ");
/*     */     }
/*     */   }
/*     */   
/*     */ 
/*     */   public static void println(Complex[] aa, int trunc)
/*     */   {
/* 416 */     for (int i = 0; i < aa.length; i++) {
/* 417 */       System.out.println(Complex.truncate(aa[i], trunc) + "   ");
/*     */     }
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */   public static void print(Phasor[] aa)
/*     */   {
/* 425 */     for (int i = 0; i < aa.length; i++) {
/* 426 */       System.out.print(aa[i] + "   ");
/*     */     }
/* 428 */     System.out.println();
/*     */   }
/*     */   
/*     */ 
/*     */   public static void print(Phasor[] aa, int trunc)
/*     */   {
/* 434 */     for (int i = 0; i < aa.length; i++) {
/* 435 */       System.out.print(Phasor.truncate(aa[i], trunc) + "   ");
/*     */     }
/* 437 */     System.out.println();
/*     */   }
/*     */   
/*     */ 
/*     */   public static void println(Phasor[] aa)
/*     */   {
/* 443 */     for (int i = 0; i < aa.length; i++) {
/* 444 */       System.out.println(aa[i] + "   ");
/*     */     }
/*     */   }
/*     */   
/*     */ 
/*     */   public static void println(Phasor[] aa, int trunc)
/*     */   {
/* 451 */     for (int i = 0; i < aa.length; i++) {
/* 452 */       System.out.println(Phasor.truncate(aa[i], trunc) + "   ");
/*     */     }
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */   public static void print(BigDecimal[] aa)
/*     */   {
/* 460 */     for (int i = 0; i < aa.length; i++) {
/* 461 */       System.out.print(aa[i] + "   ");
/*     */     }
/* 463 */     System.out.println();
/*     */   }
/*     */   
/*     */ 
/*     */   public static void println(BigDecimal[] aa)
/*     */   {
/* 469 */     for (int i = 0; i < aa.length; i++) {
/* 470 */       System.out.println(aa[i] + "   ");
/*     */     }
/*     */   }
/*     */   
/*     */ 
/*     */   public static void print(BigInteger[] aa)
/*     */   {
/* 477 */     for (int i = 0; i < aa.length; i++) {
/* 478 */       System.out.print(aa[i] + "   ");
/*     */     }
/* 480 */     System.out.println();
/*     */   }
/*     */   
/*     */ 
/*     */   public static void println(BigInteger[] aa)
/*     */   {
/* 486 */     for (int i = 0; i < aa.length; i++) {
/* 487 */       System.out.println(aa[i] + "   ");
/*     */     }
/*     */   }
/*     */   
/*     */ 
/*     */   public static void print(boolean[] aa)
/*     */   {
/* 494 */     for (int i = 0; i < aa.length; i++) {
/* 495 */       System.out.print(aa[i] + "   ");
/*     */     }
/* 497 */     System.out.println();
/*     */   }
/*     */   
/*     */ 
/*     */   public static void println(boolean[] aa)
/*     */   {
/* 503 */     for (int i = 0; i < aa.length; i++) {
/* 504 */       System.out.println(aa[i] + "   ");
/*     */     }
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   public static void print(double[][] aa)
/*     */   {
/* 513 */     for (int i = 0; i < aa.length; i++) {
/* 514 */       print(aa[i]);
/*     */     }
/*     */   }
/*     */   
/*     */   public static void print(double[][] aa, int trunc)
/*     */   {
/* 520 */     for (int i = 0; i < aa.length; i++) {
/* 521 */       print(aa[i], trunc);
/*     */     }
/*     */   }
/*     */   
/*     */   public static void print(float[][] aa)
/*     */   {
/* 527 */     for (int i = 0; i < aa.length; i++) {
/* 528 */       print(aa[i]);
/*     */     }
/*     */   }
/*     */   
/*     */   public static void print(float[][] aa, int trunc)
/*     */   {
/* 534 */     for (int i = 0; i < aa.length; i++) {
/* 535 */       print(aa[i], trunc);
/*     */     }
/*     */   }
/*     */   
/*     */   public static void print(int[][] aa)
/*     */   {
/* 541 */     for (int i = 0; i < aa.length; i++) {
/* 542 */       print(aa[i]);
/*     */     }
/*     */   }
/*     */   
/*     */   public static void print(long[][] aa)
/*     */   {
/* 548 */     for (int i = 0; i < aa.length; i++) {
/* 549 */       print(aa[i]);
/*     */     }
/*     */   }
/*     */   
/*     */   public static void print(char[][] aa) {
/* 554 */     for (int i = 0; i < aa.length; i++) {
/* 555 */       print(aa[i]);
/*     */     }
/*     */   }
/*     */   
/*     */   public static void print(byte[][] aa)
/*     */   {
/* 561 */     for (int i = 0; i < aa.length; i++) {
/* 562 */       print(aa[i]);
/*     */     }
/*     */   }
/*     */   
/*     */   public static void print(short[][] aa)
/*     */   {
/* 568 */     for (int i = 0; i < aa.length; i++) {
/* 569 */       print(aa[i]);
/*     */     }
/*     */   }
/*     */   
/*     */   public static void print(Double[][] aa)
/*     */   {
/* 575 */     for (int i = 0; i < aa.length; i++) {
/* 576 */       print(aa[i]);
/*     */     }
/*     */   }
/*     */   
/*     */   public static void print(Double[][] aa, int trunc)
/*     */   {
/* 582 */     for (int i = 0; i < aa.length; i++) {
/* 583 */       print(aa[i], trunc);
/*     */     }
/*     */   }
/*     */   
/*     */   public static void print(Float[][] aa)
/*     */   {
/* 589 */     for (int i = 0; i < aa.length; i++) {
/* 590 */       print(aa[i]);
/*     */     }
/*     */   }
/*     */   
/*     */   public static void print(Float[][] aa, int trunc)
/*     */   {
/* 596 */     for (int i = 0; i < aa.length; i++) {
/* 597 */       print(aa[i], trunc);
/*     */     }
/*     */   }
/*     */   
/*     */ 
/*     */   public static void print(Integer[][] aa)
/*     */   {
/* 604 */     for (int i = 0; i < aa.length; i++) {
/* 605 */       print(aa[i]);
/*     */     }
/*     */   }
/*     */   
/*     */   public static void print(Long[][] aa)
/*     */   {
/* 611 */     for (int i = 0; i < aa.length; i++) {
/* 612 */       print(aa[i]);
/*     */     }
/*     */   }
/*     */   
/*     */   public static void print(Character[][] aa) {
/* 617 */     for (int i = 0; i < aa.length; i++) {
/* 618 */       print(aa[i]);
/*     */     }
/*     */   }
/*     */   
/*     */   public static void print(Byte[][] aa)
/*     */   {
/* 624 */     for (int i = 0; i < aa.length; i++) {
/* 625 */       print(aa[i]);
/*     */     }
/*     */   }
/*     */   
/*     */   public static void print(Short[][] aa)
/*     */   {
/* 631 */     for (int i = 0; i < aa.length; i++) {
/* 632 */       print(aa[i]);
/*     */     }
/*     */   }
/*     */   
/*     */   public static void print(String[][] aa)
/*     */   {
/* 638 */     for (int i = 0; i < aa.length; i++) {
/* 639 */       print(aa[i]);
/*     */     }
/*     */   }
/*     */   
/*     */   public static void print(Complex[][] aa)
/*     */   {
/* 645 */     for (int i = 0; i < aa.length; i++) {
/* 646 */       Complex.print(aa[i]);
/*     */     }
/*     */   }
/*     */   
/*     */   public static void print(Complex[][] aa, int trunc)
/*     */   {
/* 652 */     for (int i = 0; i < aa.length; i++) {
/* 653 */       print(aa[i], trunc);
/*     */     }
/*     */   }
/*     */   
/*     */   public static void print(Phasor[][] aa)
/*     */   {
/* 659 */     for (int i = 0; i < aa.length; i++) {
/* 660 */       Phasor.print(aa[i]);
/*     */     }
/*     */   }
/*     */   
/*     */ 
/*     */   public static void print(Phasor[][] aa, int trunc)
/*     */   {
/* 667 */     Phasor[][] aam = (Phasor[][])aa.clone();
/* 668 */     for (int i = 0; i < aam.length; i++) {
/* 669 */       for (int j = 0; j < aam[i].length; j++) {
/* 670 */         aam[i][j] = Phasor.truncate(aam[i][j], trunc);
/*     */       }
/*     */     }
/* 673 */     for (int i = 0; i < aa.length; i++) {
/* 674 */       Phasor.print(aam[i]);
/*     */     }
/*     */   }
/*     */   
/*     */   public static void print(BigDecimal[][] aa)
/*     */   {
/* 680 */     for (int i = 0; i < aa.length; i++) {
/* 681 */       print(aa[i]);
/*     */     }
/*     */   }
/*     */   
/*     */   public static void print(BigInteger[][] aa)
/*     */   {
/* 687 */     for (int i = 0; i < aa.length; i++) {
/* 688 */       print(aa[i]);
/*     */     }
/*     */   }
/*     */   
/*     */   public static void print(boolean[][] aa)
/*     */   {
/* 694 */     for (int i = 0; i < aa.length; i++) {
/* 695 */       print(aa[i]);
/*     */     }
/*     */   }
/*     */ }


/* Location:              /home/menick/Scrivania/ProcessMining/CNMining/CNMining.jar!/flanagan/io/PrintToScreen.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       0.7.1
 */