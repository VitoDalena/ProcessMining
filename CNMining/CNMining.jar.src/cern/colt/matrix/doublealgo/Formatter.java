/*     */ package cern.colt.matrix.doublealgo;
/*     */ 
/*     */ import cern.colt.PersistentObject;
/*     */ import cern.colt.Timer;
/*     */ import cern.colt.list.ObjectArrayList;
/*     */ import cern.colt.matrix.DoubleFactory2D;
/*     */ import cern.colt.matrix.DoubleMatrix1D;
/*     */ import cern.colt.matrix.DoubleMatrix2D;
/*     */ import cern.colt.matrix.DoubleMatrix3D;
/*     */ import cern.colt.matrix.ObjectFactory2D;
/*     */ import cern.colt.matrix.ObjectMatrix1D;
/*     */ import cern.colt.matrix.ObjectMatrix2D;
/*     */ import cern.colt.matrix.impl.AbstractFormatter;
/*     */ import cern.colt.matrix.impl.AbstractMatrix1D;
/*     */ import cern.colt.matrix.impl.AbstractMatrix2D;
/*     */ import cern.colt.matrix.impl.AbstractMatrix3D;
/*     */ import cern.colt.matrix.impl.DenseDoubleMatrix1D;
/*     */ import cern.colt.matrix.impl.Former;
/*     */ import cern.colt.matrix.impl.FormerFactory;
/*     */ import hep.aida.bin.BinFunction1D;
/*     */ import hep.aida.bin.BinFunctions1D;
/*     */ import java.io.PrintStream;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class Formatter
/*     */   extends AbstractFormatter
/*     */ {
/*     */   public Formatter()
/*     */   {
/* 269 */     this("%G");
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */   public Formatter(String paramString)
/*     */   {
/* 276 */     setFormat(paramString);
/* 277 */     setAlignment("decimal");
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */   public static void demo1()
/*     */   {
/* 284 */     double[][] arrayOfDouble = { { 3.0D, 0.0D, -3.4D, 0.0D }, { 5.1D, 0.0D, 3.0123456789D, 0.0D }, { 16.37D, 0.0D, 2.5D, 0.0D }, { -16.3D, 0.0D, -3.012345678E-4D, -1.0D }, { 1236.3456789D, 0.0D, 7.0D, -1.2D } };
/*     */     
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/* 291 */     String[] arrayOfString1 = { "%G", "%1.10G", "%f", "%1.2f", "%0.2e", null };
/*     */     
/*     */ 
/*     */ 
/* 295 */     int i = arrayOfString1.length;
/* 296 */     DoubleMatrix2D localDoubleMatrix2D = DoubleFactory2D.dense.make(arrayOfDouble);
/* 297 */     String[] arrayOfString2 = new String[i];
/* 298 */     String[] arrayOfString3 = new String[i];
/* 299 */     String[] arrayOfString4 = new String[i];
/* 300 */     String[] arrayOfString5 = new String[i];
/*     */     
/* 302 */     for (int j = 0; j < i; j++) {
/* 303 */       String str = arrayOfString1[j];
/* 304 */       arrayOfString2[j] = new Formatter(str).toString(localDoubleMatrix2D);
/* 305 */       arrayOfString3[j] = new Formatter(str).toSourceCode(localDoubleMatrix2D);
/*     */     }
/*     */     
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/* 312 */     System.out.println("original:\n" + new Formatter().toString(localDoubleMatrix2D));
/*     */     
/*     */ 
/* 315 */     for (int k = 0; k < i; k++) {}
/*     */     
/*     */ 
/*     */ 
/*     */ 
/* 320 */     for (int m = 0; m < i; m++) {
/* 321 */       System.out.println("\nstring(" + arrayOfString1[m] + "):\n" + arrayOfString2[m]);
/* 322 */       System.out.println("\nsourceCode(" + arrayOfString1[m] + "):\n" + arrayOfString3[m]);
/*     */     }
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   public static void demo2()
/*     */   {
/* 331 */     double[] arrayOfDouble = { 5.0D, 0.0D, -0.0D, NaN.0D, NaN.0D, NaN.0D, Double.MIN_VALUE, Double.MAX_VALUE, Double.NEGATIVE_INFINITY, Double.POSITIVE_INFINITY };
/*     */     
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/* 337 */     String[] arrayOfString1 = { "%G", "%1.19G" };
/*     */     
/*     */ 
/*     */ 
/* 341 */     int i = arrayOfString1.length;
/* 342 */     DenseDoubleMatrix1D localDenseDoubleMatrix1D = new DenseDoubleMatrix1D(arrayOfDouble);
/*     */     
/* 344 */     String[] arrayOfString2 = new String[i];
/*     */     
/*     */ 
/* 347 */     for (int j = 0; j < i; j++) {
/* 348 */       String str = arrayOfString1[j];
/* 349 */       arrayOfString2[j] = new Formatter(str).toString(localDenseDoubleMatrix1D);
/* 350 */       for (int m = 0; m < localDenseDoubleMatrix1D.size(); m++) {
/* 351 */         System.out.println(String.valueOf(localDenseDoubleMatrix1D.get(m)));
/*     */       }
/*     */     }
/*     */     
/* 355 */     System.out.println("original:\n" + new Formatter().toString(localDenseDoubleMatrix1D));
/*     */     
/* 357 */     for (int k = 0; k < i; k++) {
/* 358 */       System.out.println("\nstring(" + arrayOfString1[k] + "):\n" + arrayOfString2[k]);
/*     */     }
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */   public static void demo3(int paramInt, double paramDouble)
/*     */   {
/* 366 */     Timer localTimer = new Timer();
/*     */     
/*     */ 
/* 369 */     DoubleMatrix2D localDoubleMatrix2D = DoubleFactory2D.dense.make(paramInt, paramInt, paramDouble);
/*     */     
/* 371 */     localTimer.reset().start();
/* 372 */     StringBuffer localStringBuffer = new StringBuffer();
/* 373 */     int i = paramInt;
/* 374 */     do { int j = paramInt;
/* 375 */       do { localStringBuffer.append(localDoubleMatrix2D.getQuick(i, j));j--;
/* 374 */       } while (j >= 0);
/* 373 */       i--; } while (i >= 0);
/*     */     
/*     */ 
/*     */ 
/*     */ 
/* 378 */     localStringBuffer = null;
/* 379 */     localTimer.stop().display();
/*     */     
/* 381 */     localTimer.reset().start();
/* 382 */     Former localFormer = new FormerFactory().create("%G");
/* 383 */     localStringBuffer = new StringBuffer();
/* 384 */     int k = paramInt;
/* 385 */     do { int m = paramInt;
/* 386 */       do { localStringBuffer.append(localFormer.form(localDoubleMatrix2D.getQuick(k, m)));m--;
/* 385 */       } while (m >= 0);
/* 384 */       k--; } while (k >= 0);
/*     */     
/*     */ 
/*     */ 
/*     */ 
/* 389 */     localStringBuffer = null;
/* 390 */     localTimer.stop().display();
/*     */     
/* 392 */     localTimer.reset().start();
/* 393 */     String str = new Formatter(null).toString(localDoubleMatrix2D);
/*     */     
/* 395 */     str = null;
/* 396 */     localTimer.stop().display();
/*     */     
/* 398 */     localTimer.reset().start();
/* 399 */     str = new Formatter("%G").toString(localDoubleMatrix2D);
/*     */     
/* 401 */     str = null;
/* 402 */     localTimer.stop().display();
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */   public static void demo4()
/*     */   {
/* 409 */     double[][] arrayOfDouble = { { 3.0D, 0.0D, -3.4D, 0.0D }, { 5.1D, 0.0D, 3.0123456789D, 0.0D }, { 16.37D, 0.0D, 2.5D, 0.0D }, { -16.3D, 0.0D, -3.012345678E-4D, -1.0D }, { 1236.3456789D, 0.0D, 7.0D, -1.2D } };
/*     */     
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/* 424 */     String[] arrayOfString1 = { "0.1", "0.3", "0.5", "0.7" };
/* 425 */     String[] arrayOfString2 = { "SunJDK1.2.2 classic", "IBMJDK1.1.8", "SunJDK1.3 Hotspot", "other1", "other2" };
/*     */     
/*     */ 
/*     */ 
/* 429 */     DoubleMatrix2D localDoubleMatrix2D = DoubleFactory2D.dense.make(arrayOfDouble);
/* 430 */     System.out.println("\n\n" + new Formatter("%G").toTitleString(localDoubleMatrix2D, arrayOfString2, arrayOfString1, "rowAxis", "colAxis", "VM Performance: Provider vs. matrix density"));
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */   public static void demo5()
/*     */   {
/* 437 */     double[][] arrayOfDouble = { { 3.0D, 0.0D, -3.4D, 0.0D }, { 5.1D, 0.0D, 3.0123456789D, 0.0D }, { 16.37D, 0.0D, 2.5D, 0.0D }, { -16.3D, 0.0D, -3.012345678E-4D, -1.0D }, { 1236.3456789D, 0.0D, 7.0D, -1.2D } };
/*     */     
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/* 452 */     String[] arrayOfString1 = { "0.1", "0.3", "0.5", "0.7" };
/* 453 */     String[] arrayOfString2 = { "SunJDK1.2.2 classic", "IBMJDK1.1.8", "SunJDK1.3 Hotspot", "other1", "other2" };
/*     */     
/*     */ 
/*     */ 
/* 457 */     System.out.println(DoubleFactory2D.dense.make(arrayOfDouble));
/* 458 */     System.out.println(new Formatter("%G").toTitleString(DoubleFactory2D.dense.make(arrayOfDouble), arrayOfString2, arrayOfString1, "vendor", "density", "title"));
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */   public static void demo6()
/*     */   {
/* 465 */     double[][] arrayOfDouble = { { 3.0D, 0.0D, -3.4D, 0.0D }, { 5.1D, 0.0D, 3.0123456789D, 0.0D }, { 16.37D, 0.0D, 2.5D, 0.0D }, { -16.3D, 0.0D, -3.012345678E-4D, -1.0D }, { 1236.3456789D, 0.0D, 7.0D, -1.2D } };
/*     */     
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/* 481 */     String[] arrayOfString1 = { "W", "X", "Y", "Z" };
/* 482 */     String[] arrayOfString2 = { "SunJDK1.2.2 classic", "IBMJDK1.1.8", "SunJDK1.3 Hotspot", "other1", "other2" };
/*     */     
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/* 488 */     System.out.println(new Formatter().toString(DoubleFactory2D.dense.make(arrayOfDouble)));
/* 489 */     System.out.println(new Formatter().toTitleString(DoubleFactory2D.dense.make(arrayOfDouble), arrayOfString2, arrayOfString1, "vendor", "density", "title"));
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
/*     */   public static void demo7()
/*     */   {
/* 505 */     double[][] arrayOfDouble = { { 5.0D, 10.0D, 20.0D, 40.0D }, { 7.0D, 8.0D, 6.0D, 7.0D }, { 12.0D, 10.0D, 20.0D, 19.0D }, { 3.0D, 1.0D, 5.0D, 6.0D } };
/*     */     
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/* 511 */     String[] arrayOfString1 = { "1996", "1997", "1998", "1999" };
/* 512 */     String[] arrayOfString2 = { "PowerBar", "Benzol", "Mercedes", "Sparcling" };
/* 513 */     String str1 = "CPU";
/* 514 */     String str2 = "Year";
/* 515 */     String str3 = "CPU performance over time [nops/sec]";
/* 516 */     BinFunctions1D localBinFunctions1D = BinFunctions1D.functions;
/* 517 */     BinFunction1D[] arrayOfBinFunction1D = { BinFunctions1D.mean, BinFunctions1D.rms, BinFunctions1D.quantile(0.25D), BinFunctions1D.median, BinFunctions1D.quantile(0.75D), BinFunctions1D.stdDev, BinFunctions1D.min, BinFunctions1D.max };
/* 518 */     String str4 = "%1.2G";
/*     */     
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/* 528 */     System.out.println(new Formatter(str4).toTitleString(DoubleFactory2D.dense.make(arrayOfDouble), arrayOfString2, arrayOfString1, str1, str2, str3, arrayOfBinFunction1D));
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */   protected String form(DoubleMatrix1D paramDoubleMatrix1D, int paramInt, Former paramFormer)
/*     */   {
/* 535 */     return paramFormer.form(paramDoubleMatrix1D.get(paramInt));
/*     */   }
/*     */   
/*     */ 
/*     */   protected String form(AbstractMatrix1D paramAbstractMatrix1D, int paramInt, Former paramFormer)
/*     */   {
/* 541 */     return form((DoubleMatrix1D)paramAbstractMatrix1D, paramInt, paramFormer);
/*     */   }
/*     */   
/*     */ 
/*     */   public String[][] format(DoubleMatrix2D paramDoubleMatrix2D)
/*     */   {
/* 547 */     String[][] arrayOfString = new String[paramDoubleMatrix2D.rows()][paramDoubleMatrix2D.columns()];
/* 548 */     int i = paramDoubleMatrix2D.rows(); do { arrayOfString[i] = formatRow(paramDoubleMatrix2D.viewRow(i));i--; } while (i >= 0);
/* 549 */     return arrayOfString;
/*     */   }
/*     */   
/*     */ 
/*     */   protected String[][] format(AbstractMatrix2D paramAbstractMatrix2D)
/*     */   {
/* 555 */     return format((DoubleMatrix2D)paramAbstractMatrix2D);
/*     */   }
/*     */   
/*     */ 
/*     */   protected int indexOfDecimalPoint(String paramString)
/*     */   {
/* 561 */     int i = paramString.lastIndexOf('.');
/* 562 */     if (i < 0) i = paramString.lastIndexOf('e');
/* 563 */     if (i < 0) i = paramString.lastIndexOf('E');
/* 564 */     if (i < 0) i = paramString.length();
/* 565 */     return i;
/*     */   }
/*     */   
/*     */ 
/*     */   protected int lead(String paramString)
/*     */   {
/* 571 */     if (this.alignment.equals("decimal")) return indexOfDecimalPoint(paramString);
/* 572 */     return super.lead(paramString);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */   public String toSourceCode(DoubleMatrix1D paramDoubleMatrix1D)
/*     */   {
/* 579 */     Formatter localFormatter = (Formatter)clone();
/* 580 */     localFormatter.setPrintShape(false);
/* 581 */     localFormatter.setColumnSeparator(", ");
/* 582 */     String str1 = "{";
/* 583 */     String str2 = "};";
/* 584 */     return str1 + localFormatter.toString(paramDoubleMatrix1D) + str2;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */   public String toSourceCode(DoubleMatrix2D paramDoubleMatrix2D)
/*     */   {
/* 591 */     Formatter localFormatter = (Formatter)clone();
/* 592 */     String str1 = blanks(3);
/* 593 */     localFormatter.setPrintShape(false);
/* 594 */     localFormatter.setColumnSeparator(", ");
/* 595 */     localFormatter.setRowSeparator("},\n" + str1 + "{");
/* 596 */     String str2 = "{\n" + str1 + "{";
/* 597 */     String str3 = "}\n};";
/* 598 */     return str2 + localFormatter.toString(paramDoubleMatrix2D) + str3;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */   public String toSourceCode(DoubleMatrix3D paramDoubleMatrix3D)
/*     */   {
/* 605 */     Formatter localFormatter = (Formatter)clone();
/* 606 */     String str1 = blanks(3);
/* 607 */     String str2 = blanks(6);
/* 608 */     localFormatter.setPrintShape(false);
/* 609 */     localFormatter.setColumnSeparator(", ");
/* 610 */     localFormatter.setRowSeparator("},\n" + str2 + "{");
/* 611 */     localFormatter.setSliceSeparator("}\n" + str1 + "},\n" + str1 + "{\n" + str2 + "{");
/* 612 */     String str3 = "{\n" + str1 + "{\n" + str2 + "{";
/* 613 */     String str4 = "}\n" + str1 + "}\n}";
/* 614 */     return str3 + localFormatter.toString(paramDoubleMatrix3D) + str4;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */   public String toString(DoubleMatrix1D paramDoubleMatrix1D)
/*     */   {
/* 621 */     DoubleMatrix2D localDoubleMatrix2D = paramDoubleMatrix1D.like2D(1, paramDoubleMatrix1D.size());
/* 622 */     localDoubleMatrix2D.viewRow(0).assign(paramDoubleMatrix1D);
/* 623 */     return toString(localDoubleMatrix2D);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */   public String toString(DoubleMatrix2D paramDoubleMatrix2D)
/*     */   {
/* 630 */     return super.toString(paramDoubleMatrix2D);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */   public String toString(DoubleMatrix3D paramDoubleMatrix3D)
/*     */   {
/* 637 */     StringBuffer localStringBuffer = new StringBuffer();
/* 638 */     boolean bool = this.printShape;
/* 639 */     this.printShape = false;
/* 640 */     for (int i = 0; i < paramDoubleMatrix3D.slices(); i++) {
/* 641 */       if (i != 0) localStringBuffer.append(this.sliceSeparator);
/* 642 */       localStringBuffer.append(toString(paramDoubleMatrix3D.viewSlice(i)));
/*     */     }
/* 644 */     this.printShape = bool;
/* 645 */     if (this.printShape) localStringBuffer.insert(0, AbstractFormatter.shape(paramDoubleMatrix3D) + "\n");
/* 646 */     return localStringBuffer.toString();
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */   protected String toString(AbstractMatrix2D paramAbstractMatrix2D)
/*     */   {
/* 653 */     return toString((DoubleMatrix2D)paramAbstractMatrix2D);
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
/*     */   protected String toTitleString(DoubleMatrix2D paramDoubleMatrix2D, String[] paramArrayOfString1, String[] paramArrayOfString2, String paramString1, String paramString2, String paramString3)
/*     */   {
/* 668 */     if (paramDoubleMatrix2D.size() == 0) return "Empty matrix";
/* 669 */     String[][] arrayOfString = format(paramDoubleMatrix2D);
/*     */     
/*     */ 
/* 672 */     align(arrayOfString);
/*     */     
/* 674 */     return new cern.colt.matrix.objectalgo.Formatter().toTitleString(ObjectFactory2D.dense.make(arrayOfString), paramArrayOfString1, paramArrayOfString2, paramString1, paramString2, paramString3);
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
/*     */   public String toTitleString(DoubleMatrix2D paramDoubleMatrix2D, String[] paramArrayOfString1, String[] paramArrayOfString2, String paramString1, String paramString2, String paramString3, BinFunction1D[] paramArrayOfBinFunction1D)
/*     */   {
/* 692 */     if (paramDoubleMatrix2D.size() == 0) return "Empty matrix";
/* 693 */     if ((paramArrayOfBinFunction1D == null) || (paramArrayOfBinFunction1D.length == 0)) { return toTitleString(paramDoubleMatrix2D, paramArrayOfString1, paramArrayOfString2, paramString1, paramString2, paramString3);
/*     */     }
/* 695 */     DoubleMatrix2D localDoubleMatrix2D1 = paramDoubleMatrix2D.like(paramDoubleMatrix2D.rows(), paramArrayOfBinFunction1D.length);
/* 696 */     DoubleMatrix2D localDoubleMatrix2D2 = paramDoubleMatrix2D.like(paramArrayOfBinFunction1D.length, paramDoubleMatrix2D.columns());
/*     */     
/* 698 */     Statistic.aggregate(paramDoubleMatrix2D, paramArrayOfBinFunction1D, localDoubleMatrix2D2);
/* 699 */     Statistic.aggregate(paramDoubleMatrix2D.viewDice(), paramArrayOfBinFunction1D, localDoubleMatrix2D1.viewDice());
/*     */     
/*     */ 
/*     */ 
/* 703 */     DoubleMatrix2D localDoubleMatrix2D3 = paramDoubleMatrix2D.like(paramDoubleMatrix2D.rows() + paramArrayOfBinFunction1D.length, paramDoubleMatrix2D.columns());
/* 704 */     localDoubleMatrix2D3.viewPart(0, 0, paramDoubleMatrix2D.rows(), paramDoubleMatrix2D.columns()).assign(paramDoubleMatrix2D);
/* 705 */     localDoubleMatrix2D3.viewPart(paramDoubleMatrix2D.rows(), 0, paramArrayOfBinFunction1D.length, paramDoubleMatrix2D.columns()).assign(localDoubleMatrix2D2);
/* 706 */     localDoubleMatrix2D2 = null;
/*     */     
/* 708 */     String[][] arrayOfString1 = format(localDoubleMatrix2D3);align(arrayOfString1);localDoubleMatrix2D3 = null;
/* 709 */     String[][] arrayOfString2 = format(localDoubleMatrix2D1);align(arrayOfString2);localDoubleMatrix2D1 = null;
/*     */     
/*     */ 
/* 712 */     ObjectMatrix2D localObjectMatrix2D = ObjectFactory2D.dense.make(paramDoubleMatrix2D.rows() + paramArrayOfBinFunction1D.length, paramDoubleMatrix2D.columns() + paramArrayOfBinFunction1D.length + 1);
/* 713 */     localObjectMatrix2D.viewPart(0, 0, paramDoubleMatrix2D.rows() + paramArrayOfBinFunction1D.length, paramDoubleMatrix2D.columns()).assign(arrayOfString1);
/* 714 */     localObjectMatrix2D.viewColumn(paramDoubleMatrix2D.columns()).assign("|");
/* 715 */     localObjectMatrix2D.viewPart(0, paramDoubleMatrix2D.columns() + 1, paramDoubleMatrix2D.rows(), paramArrayOfBinFunction1D.length).assign(arrayOfString2);
/* 716 */     arrayOfString1 = null;arrayOfString2 = null;
/*     */     
/*     */ 
/* 719 */     if (paramArrayOfString2 != null) {
/* 720 */       localObject = new ObjectArrayList(paramArrayOfString2);
/* 721 */       ((ObjectArrayList)localObject).add("|");
/* 722 */       for (i = 0; i < paramArrayOfBinFunction1D.length; i++) ((ObjectArrayList)localObject).add(paramArrayOfBinFunction1D[i].name());
/* 723 */       paramArrayOfString2 = new String[((ObjectArrayList)localObject).size()];
/* 724 */       ((ObjectArrayList)localObject).toArray(paramArrayOfString2);
/*     */     }
/*     */     
/*     */ 
/* 728 */     if (paramArrayOfString1 != null) {
/* 729 */       localObject = new ObjectArrayList(paramArrayOfString1);
/* 730 */       for (i = 0; i < paramArrayOfBinFunction1D.length; i++) ((ObjectArrayList)localObject).add(paramArrayOfBinFunction1D[i].name());
/* 731 */       paramArrayOfString1 = new String[((ObjectArrayList)localObject).size()];
/* 732 */       ((ObjectArrayList)localObject).toArray(paramArrayOfString1);
/*     */     }
/*     */     
/*     */ 
/* 736 */     Object localObject = new cern.colt.matrix.objectalgo.Formatter().toTitleString(localObjectMatrix2D, paramArrayOfString1, paramArrayOfString2, paramString1, paramString2, paramString3);
/*     */     
/*     */ 
/*     */ 
/* 740 */     int i = ((String)localObject).length() + 1;
/* 741 */     int j = i;
/* 742 */     int k = Math.max(0, paramString1 == null ? 0 : paramString1.length() - paramDoubleMatrix2D.rows() - paramArrayOfBinFunction1D.length);
/* 743 */     for (int m = 0; m < paramArrayOfBinFunction1D.length + 1 + k; m++) {
/* 744 */       j = i;
/* 745 */       i = ((String)localObject).lastIndexOf(this.rowSeparator, i - 1);
/*     */     }
/* 747 */     StringBuffer localStringBuffer = new StringBuffer((String)localObject);
/* 748 */     localStringBuffer.insert(j, this.rowSeparator + repeat('-', j - i - 1));
/*     */     
/* 750 */     return localStringBuffer.toString();
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
/*     */   public String toTitleString(DoubleMatrix3D paramDoubleMatrix3D, String[] paramArrayOfString1, String[] paramArrayOfString2, String[] paramArrayOfString3, String paramString1, String paramString2, String paramString3, String paramString4, BinFunction1D[] paramArrayOfBinFunction1D)
/*     */   {
/* 770 */     if (paramDoubleMatrix3D.size() == 0) return "Empty matrix";
/* 771 */     StringBuffer localStringBuffer = new StringBuffer();
/* 772 */     for (int i = 0; i < paramDoubleMatrix3D.slices(); i++) {
/* 773 */       if (i != 0) localStringBuffer.append(this.sliceSeparator);
/* 774 */       localStringBuffer.append(toTitleString(paramDoubleMatrix3D.viewSlice(i), paramArrayOfString2, paramArrayOfString3, paramString2, paramString3, paramString4 + "\n" + paramString1 + "=" + paramArrayOfString1[i], paramArrayOfBinFunction1D));
/*     */     }
/* 776 */     return localStringBuffer.toString();
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
/*     */   private String xtoTitleString(DoubleMatrix3D paramDoubleMatrix3D, String[] paramArrayOfString1, String[] paramArrayOfString2, String[] paramArrayOfString3, String paramString1, String paramString2, String paramString3, String paramString4)
/*     */   {
/* 793 */     if (paramDoubleMatrix3D.size() == 0) return "Empty matrix";
/* 794 */     StringBuffer localStringBuffer = new StringBuffer();
/* 795 */     for (int i = 0; i < paramDoubleMatrix3D.slices(); i++) {
/* 796 */       if (i != 0) localStringBuffer.append(this.sliceSeparator);
/* 797 */       localStringBuffer.append(toTitleString(paramDoubleMatrix3D.viewSlice(i), paramArrayOfString2, paramArrayOfString3, paramString2, paramString3, paramString4 + "\n" + paramString1 + "=" + paramArrayOfString1[i]));
/*     */     }
/* 799 */     return localStringBuffer.toString();
/*     */   }
/*     */ }


/* Location:              /home/menick/Scrivania/ProcessMining/CNMining/CNMining.jar!/cern/colt/matrix/doublealgo/Formatter.class
 * Java compiler version: 1 (45.3)
 * JD-Core Version:       0.7.1
 */