/*      */ package cern.colt.matrix.impl;
/*      */ 
/*      */ import cern.colt.Timer;
/*      */ import cern.colt.matrix.DoubleMatrix2D;
/*      */ import cern.colt.matrix.doublealgo.Transform;
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
/*      */ class BenchmarkMatrix2D
/*      */ {
/*      */   protected BenchmarkMatrix2D()
/*      */   {
/*  169 */     throw new RuntimeException("Non instantiable");
/*      */   }
/*      */   
/*      */ 
/*      */   public static void doubleBenchmark(int paramInt1, int paramInt2, int paramInt3, String paramString, boolean paramBoolean, int paramInt4, double paramDouble1, double paramDouble2)
/*      */   {
/*  175 */     System.out.println("benchmarking double matrix");
/*      */     
/*      */ 
/*      */ 
/*  179 */     Timer localTimer1 = new Timer();
/*  180 */     Timer localTimer2 = new Timer();
/*  181 */     Timer localTimer3 = new Timer();
/*  182 */     Timer localTimer4 = new Timer();
/*  183 */     Timer localTimer5 = new Timer();
/*  184 */     Timer localTimer6 = new Timer();
/*      */     
/*  186 */     localTimer5.start();
/*  187 */     int i = 0;
/*  188 */     for (int j = 0; j < paramInt1; j++) {
/*  189 */       for (int k = 0; k < paramInt3; k++) {
/*  190 */         for (int m = 0; m < paramInt2; m++) {
/*  191 */           i++;
/*      */         }
/*      */       }
/*      */     }
/*  195 */     localTimer5.stop();
/*  196 */     System.out.println(i);
/*      */     
/*  198 */     localTimer6.start();
/*  199 */     i = 3;
/*  200 */     double d1 = 0.0D;
/*  201 */     for (int n = 0; n < paramInt1; n++) {
/*  202 */       int i1 = 0; for (int i2 = 0; i2 < paramInt3; i2++) {
/*  203 */         for (int i3 = 0; i3 < paramInt2; i3++) {
/*  204 */           d1 += i;
/*      */         }
/*      */       }
/*      */     }
/*  208 */     localTimer6.stop();
/*  209 */     System.out.println(d1);
/*      */     
/*      */ 
/*  212 */     long l1 = Runtime.getRuntime().freeMemory();
/*  213 */     long l2 = paramInt2 * paramInt3 * paramInt1;
/*      */     
/*  215 */     Object localObject = null;
/*  216 */     if (paramString.equals("sparse")) { localObject = new SparseDoubleMatrix2D(paramInt2, paramInt3, paramInt4, paramDouble1, paramDouble2);
/*  217 */     } else if (paramString.equals("dense")) { localObject = new DenseDoubleMatrix2D(paramInt2, paramInt3);
/*      */     } else {
/*  219 */       throw new RuntimeException("unknown kind");
/*      */     }
/*  221 */     System.out.println("\nNow filling...");
/*      */     
/*  223 */     for (int i4 = 0; i4 < paramInt1; i4++) {
/*  224 */       ((DoubleMatrix2D)localObject).assign(0.0D);
/*  225 */       ((AbstractMatrix)localObject).ensureCapacity(paramInt4);
/*  226 */       if (paramString.equals("sparse")) ((SparseDoubleMatrix2D)localObject).ensureCapacity(paramInt4);
/*  227 */       localTimer1.start();
/*  228 */       int i5 = 0;
/*  229 */       for (int i6 = 0; i6 < paramInt2; i6++) {
/*  230 */         for (int i7 = 0; i7 < paramInt3; i7++) {
/*  231 */           ((DoubleMatrix2D)localObject).setQuick(i6, i7, i5++);
/*      */         }
/*      */       }
/*  234 */       localTimer1.stop();
/*      */     }
/*  236 */     localTimer1.display();
/*  237 */     localTimer1.minus(localTimer5).display();
/*  238 */     System.out.println((float)l2 / localTimer1.minus(localTimer5).seconds() + " elements / sec");
/*      */     
/*  240 */     Runtime.getRuntime().gc();
/*  241 */     try { Thread.currentThread();Thread.sleep(1000L); } catch (InterruptedException localInterruptedException1) {}
/*  242 */     long l3 = Runtime.getRuntime().freeMemory();
/*  243 */     System.out.println("KB needed=" + (l1 - l3) / 1024L);
/*  244 */     System.out.println("bytes needed per non-zero=" + (l1 - l3) / ((DoubleMatrix2D)localObject).cardinality());
/*  245 */     if (paramBoolean) {
/*  246 */       System.out.println(localObject);
/*  247 */       if (paramString.equals("sparse")) { System.out.println("map=" + ((SparseDoubleMatrix2D)localObject).elements);
/*      */       }
/*      */     }
/*      */     
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*  257 */     System.out.println("\nNow reading...");
/*      */     
/*  259 */     localTimer2.start();
/*  260 */     double d2 = 0.0D;
/*  261 */     for (int i8 = 0; i8 < paramInt1; i8++) {
/*  262 */       for (int i9 = 0; i9 < paramInt2; i9++) {
/*  263 */         for (i10 = 0; i10 < paramInt3; i10++) {
/*  264 */           d2 += ((DoubleMatrix2D)localObject).getQuick(i9, i10);
/*      */         }
/*      */       }
/*      */     }
/*  268 */     localTimer2.stop().display();
/*  269 */     localTimer2.minus(localTimer6).display();
/*  270 */     System.out.println((float)l2 / localTimer2.minus(localTimer6).seconds() + " elements / sec");
/*  271 */     if (paramBoolean) { System.out.println(localObject);
/*      */     }
/*  273 */     System.out.println(d2);
/*      */     
/*  275 */     System.out.println("\nNow reading view...");
/*  276 */     DoubleMatrix2D localDoubleMatrix2D = ((DoubleMatrix2D)localObject).viewPart(0, 0, paramInt2, paramInt3);
/*  277 */     localTimer4.start();
/*  278 */     d2 = 0.0D;
/*  279 */     int i12; for (int i10 = 0; i10 < paramInt1; i10++) {
/*  280 */       for (i11 = 0; i11 < paramInt2; i11++) {
/*  281 */         for (i12 = 0; i12 < paramInt3; i12++) {
/*  282 */           d2 += localDoubleMatrix2D.getQuick(i11, i12);
/*      */         }
/*      */       }
/*      */     }
/*  286 */     localTimer4.stop().display();
/*  287 */     localTimer4.minus(localTimer6).display();
/*  288 */     System.out.println((float)l2 / localTimer4.minus(localTimer6).seconds() + " elements / sec");
/*  289 */     if (paramBoolean) { System.out.println(localDoubleMatrix2D);
/*      */     }
/*  291 */     System.out.println(d2);
/*      */     
/*  293 */     System.out.println("\nNow removing...");
/*  294 */     l1 = Runtime.getRuntime().freeMemory();
/*      */     
/*  296 */     for (int i11 = 0; i11 < paramInt1; i11++)
/*      */     {
/*  298 */       for (i12 = 0; i12 < paramInt2; i12++) {
/*  299 */         for (i13 = 0; i13 < paramInt3; i13++) {
/*  300 */           ((DoubleMatrix2D)localObject).setQuick(i12, i13, 1.0D);
/*      */         }
/*      */       }
/*  303 */       localTimer3.start();
/*  304 */       for (int i13 = 0; i13 < paramInt2; i13++) {
/*  305 */         for (int i14 = 0; i14 < paramInt3; i14++) {
/*  306 */           ((DoubleMatrix2D)localObject).setQuick(i13, i14, 0.0D);
/*      */         }
/*      */       }
/*  309 */       localTimer3.stop();
/*      */     }
/*  311 */     localTimer3.display();
/*  312 */     localTimer3.minus(localTimer5).display();
/*  313 */     System.out.println((float)l2 / localTimer3.minus(localTimer5).seconds() + " elements / sec");
/*  314 */     Runtime.getRuntime().gc();
/*  315 */     try { Thread.currentThread();Thread.sleep(1000L); } catch (InterruptedException localInterruptedException2) {}
/*  316 */     l3 = Runtime.getRuntime().freeMemory();
/*  317 */     System.out.println("KB needed=" + (l1 - l3) / 1024L);
/*  318 */     System.out.println("KB free=" + l3 / 1024L);
/*      */     
/*  320 */     if (paramBoolean) { System.out.println(localObject);
/*      */     }
/*      */     
/*      */ 
/*  324 */     System.out.println("bye bye.");
/*      */   }
/*      */   
/*      */ 
/*      */   public static void doubleBenchmarkMult(int paramInt1, int paramInt2, int paramInt3, String paramString, boolean paramBoolean, int paramInt4, double paramDouble1, double paramDouble2)
/*      */   {
/*  330 */     System.out.println("benchmarking double matrix");
/*      */     
/*      */ 
/*      */ 
/*  334 */     Timer localTimer1 = new Timer();
/*  335 */     Timer localTimer2 = new Timer();
/*      */     
/*  337 */     long l = paramInt2 * paramInt3 * paramInt1;
/*      */     
/*  339 */     Object localObject = null;
/*  340 */     if (paramString.equals("sparse")) { localObject = new SparseDoubleMatrix2D(paramInt2, paramInt3, paramInt4, paramDouble1, paramDouble2);
/*  341 */     } else if (paramString.equals("dense")) { localObject = new DenseDoubleMatrix2D(paramInt2, paramInt3);
/*      */     } else {
/*  343 */       throw new RuntimeException("unknown kind");
/*      */     }
/*  345 */     System.out.println("\nNow multiplying...");
/*  346 */     ((DoubleMatrix2D)localObject).assign(1.0D);
/*      */     
/*  348 */     for (int i = 0; i < paramInt1; i++) {
/*  349 */       localTimer1.start();
/*  350 */       Transform.mult((DoubleMatrix2D)localObject, 3.0D);
/*  351 */       localTimer1.stop();
/*      */     }
/*  353 */     localTimer1.display();
/*  354 */     System.out.println((float)l / localTimer1.seconds() + " elements / sec");
/*      */     
/*  356 */     if (paramBoolean) {
/*  357 */       System.out.println(localObject);
/*      */     }
/*      */     
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*  367 */     System.out.println("\nNow multiplying2...");
/*  368 */     ((DoubleMatrix2D)localObject).assign(1.0D);
/*      */     
/*  370 */     for (int j = 0; j < paramInt1; j++) {
/*  371 */       localTimer2.start();
/*  372 */       Transform.mult((DoubleMatrix2D)localObject, 3.0D);
/*  373 */       localTimer2.stop();
/*      */     }
/*  375 */     localTimer2.display();
/*  376 */     System.out.println((float)l / localTimer2.seconds() + " elements / sec");
/*      */     
/*  378 */     if (paramBoolean) {
/*  379 */       System.out.println(localObject);
/*      */     }
/*      */     
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*  388 */     System.out.println("bye bye.");
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */   public static void doubleBenchmarkPrimitive(int paramInt1, int paramInt2, int paramInt3, boolean paramBoolean)
/*      */   {
/*  396 */     Timer localTimer1 = new Timer();
/*  397 */     Timer localTimer2 = new Timer();
/*  398 */     Timer localTimer3 = new Timer();
/*  399 */     Timer localTimer4 = new Timer();
/*  400 */     Timer localTimer5 = new Timer();
/*      */     
/*  402 */     localTimer4.start();
/*  403 */     int i = 0;
/*  404 */     for (int j = 0; j < paramInt1; j++) {
/*  405 */       for (int k = 0; k < paramInt3; k++) {
/*  406 */         for (int m = 0; m < paramInt2; m++) {
/*  407 */           i++;
/*      */         }
/*      */       }
/*      */     }
/*  411 */     localTimer4.stop();
/*  412 */     System.out.println(i);
/*      */     
/*  414 */     localTimer5.start();
/*  415 */     i = 3;
/*  416 */     double d1 = 0.0D;
/*  417 */     for (int n = 0; n < paramInt1; n++) {
/*  418 */       for (int i1 = 0; i1 < paramInt3; i1++) {
/*  419 */         for (int i2 = 0; i2 < paramInt2; i2++) {
/*  420 */           d1 += i;
/*      */         }
/*      */       }
/*      */     }
/*  424 */     localTimer5.stop();
/*  425 */     System.out.println(d1);
/*      */     
/*  427 */     long l1 = Runtime.getRuntime().freeMemory();
/*  428 */     long l2 = paramInt2 * paramInt3 * paramInt1;
/*      */     
/*  430 */     double[][] arrayOfDouble = new double[paramInt2][paramInt3];
/*      */     
/*  432 */     System.out.println("\nNow filling...");
/*  433 */     for (int i3 = 0; i3 < paramInt1; i3++) {
/*  434 */       localTimer1.start();
/*  435 */       int i4 = 0;
/*  436 */       for (int i5 = 0; i5 < paramInt3; i5++) {
/*  437 */         for (int i6 = 0; i6 < paramInt2; i6++) {
/*  438 */           arrayOfDouble[i6][i5] = (i4++);
/*      */         }
/*      */       }
/*  441 */       localTimer1.stop();
/*      */     }
/*  443 */     localTimer1.display();
/*  444 */     localTimer1.minus(localTimer4).display();
/*  445 */     System.out.println((float)l2 / localTimer1.minus(localTimer4).seconds() + " elements / sec");
/*      */     
/*  447 */     Runtime.getRuntime().gc();
/*  448 */     try { Thread.currentThread();Thread.sleep(1000L); } catch (InterruptedException localInterruptedException1) {}
/*  449 */     long l3 = Runtime.getRuntime().freeMemory();
/*  450 */     System.out.println("KB needed=" + (l1 - l3) / 1024L);
/*  451 */     if (paramBoolean) {
/*  452 */       DenseDoubleMatrix2D localDenseDoubleMatrix2D1 = new DenseDoubleMatrix2D(paramInt2, paramInt3);
/*  453 */       localDenseDoubleMatrix2D1.assign(arrayOfDouble);
/*  454 */       System.out.println(localDenseDoubleMatrix2D1);
/*      */     }
/*      */     
/*  457 */     System.out.println("\nNow reading...");
/*  458 */     localTimer2.start();
/*  459 */     double d2 = 0.0D;
/*  460 */     int i10; for (int i7 = 0; i7 < paramInt1; i7++) {
/*  461 */       for (int i8 = 0; i8 < paramInt3; i8++) {
/*  462 */         for (i10 = 0; i10 < paramInt2; i10++) {
/*  463 */           d2 += arrayOfDouble[i10][i8];
/*      */         }
/*      */       }
/*      */     }
/*  467 */     localTimer2.stop().display();
/*  468 */     localTimer2.minus(localTimer5).display();
/*  469 */     System.out.println((float)l2 / localTimer2.minus(localTimer5).seconds() + " elements / sec");
/*  470 */     if (paramBoolean) {
/*  471 */       DenseDoubleMatrix2D localDenseDoubleMatrix2D2 = new DenseDoubleMatrix2D(paramInt2, paramInt3);
/*  472 */       localDenseDoubleMatrix2D2.assign(arrayOfDouble);
/*  473 */       System.out.println(localDenseDoubleMatrix2D2);
/*      */     }
/*  475 */     System.out.println(d2);
/*      */     
/*  477 */     System.out.println("\nNow removing...");
/*  478 */     l1 = Runtime.getRuntime().freeMemory();
/*  479 */     for (int i9 = 0; i9 < paramInt1; i9++)
/*      */     {
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*  488 */       localTimer3.start();
/*  489 */       for (i10 = 0; i10 < paramInt3; i10++) {
/*  490 */         for (int i11 = 0; i11 < paramInt2; i11++) {
/*  491 */           arrayOfDouble[i11][i10] = 0.0D;
/*      */         }
/*      */       }
/*  494 */       localTimer3.stop();
/*      */     }
/*  496 */     localTimer3.display();
/*  497 */     localTimer3.minus(localTimer4).display();
/*  498 */     System.out.println((float)l2 / localTimer3.minus(localTimer4).seconds() + " elements / sec");
/*  499 */     Runtime.getRuntime().gc();
/*  500 */     try { Thread.currentThread();Thread.sleep(1000L); } catch (InterruptedException localInterruptedException2) {}
/*  501 */     l3 = Runtime.getRuntime().freeMemory();
/*  502 */     System.out.println("KB needed=" + (l1 - l3) / 1024L);
/*  503 */     System.out.println("KB free=" + l3 / 1024L);
/*      */     
/*  505 */     if (paramBoolean) {
/*  506 */       DenseDoubleMatrix2D localDenseDoubleMatrix2D3 = new DenseDoubleMatrix2D(paramInt2, paramInt3);
/*  507 */       localDenseDoubleMatrix2D3.assign(arrayOfDouble);
/*  508 */       System.out.println(localDenseDoubleMatrix2D3);
/*      */     }
/*      */     
/*  511 */     System.out.println("bye bye.");
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */   public static void doubleBenchmarkPrimitiveOptimized(int paramInt1, int paramInt2, int paramInt3, boolean paramBoolean)
/*      */   {
/*  519 */     Timer localTimer1 = new Timer();
/*  520 */     Timer localTimer2 = new Timer();
/*  521 */     Timer localTimer3 = new Timer();
/*  522 */     Timer localTimer4 = new Timer();
/*  523 */     Timer localTimer5 = new Timer();
/*      */     
/*  525 */     localTimer4.start();
/*  526 */     int i = 0;
/*  527 */     for (int j = 0; j < paramInt1; j++) {
/*  528 */       for (int k = 0; k < paramInt3; k++) {
/*  529 */         for (int m = 0; m < paramInt2; m++) {
/*  530 */           i++;
/*      */         }
/*      */       }
/*      */     }
/*  534 */     localTimer4.stop();
/*  535 */     System.out.println(i);
/*      */     
/*  537 */     localTimer5.start();
/*  538 */     i = 3;
/*  539 */     double d1 = 0.0D;
/*  540 */     for (int n = 0; n < paramInt1; n++) {
/*  541 */       for (int i1 = 0; i1 < paramInt3; i1++) {
/*  542 */         for (int i2 = 0; i2 < paramInt2; i2++) {
/*  543 */           d1 += i;
/*      */         }
/*      */       }
/*      */     }
/*  547 */     localTimer5.stop();
/*  548 */     System.out.println(d1);
/*      */     
/*  550 */     long l1 = Runtime.getRuntime().freeMemory();
/*  551 */     long l2 = paramInt2 * paramInt3 * paramInt1;
/*      */     
/*  553 */     double[][] arrayOfDouble = new double[paramInt2][paramInt3];
/*      */     
/*  555 */     System.out.println("\nNow filling...");
/*  556 */     Object localObject; for (int i3 = 0; i3 < paramInt1; i3++) {
/*  557 */       localTimer1.start();
/*  558 */       int i4 = 0;
/*  559 */       for (int i5 = 0; i5 < paramInt2; i5++) {
/*  560 */         localObject = arrayOfDouble[i5];
/*  561 */         for (int i6 = 0; i6 < paramInt3; i6++) {
/*  562 */           localObject[i6] = (i4++);
/*      */         }
/*      */       }
/*      */       
/*  566 */       localTimer1.stop();
/*      */     }
/*  568 */     localTimer1.display();
/*  569 */     localTimer1.minus(localTimer4).display();
/*  570 */     System.out.println((float)l2 / localTimer1.minus(localTimer4).seconds() + " elements / sec");
/*      */     
/*  572 */     Runtime.getRuntime().gc();
/*  573 */     try { Thread.currentThread();Thread.sleep(1000L); } catch (InterruptedException localInterruptedException1) {}
/*  574 */     long l3 = Runtime.getRuntime().freeMemory();
/*  575 */     System.out.println("KB needed=" + (l1 - l3) / 1024L);
/*  576 */     if (paramBoolean) {
/*  577 */       localObject = new DenseDoubleMatrix2D(paramInt2, paramInt3);
/*  578 */       ((DenseDoubleMatrix2D)localObject).assign(arrayOfDouble);
/*  579 */       System.out.println(localObject);
/*      */     }
/*      */     
/*  582 */     System.out.println("\nNow reading...");
/*  583 */     localTimer2.start();
/*  584 */     double d2 = 0.0D;
/*  585 */     for (int i7 = 0; i7 < paramInt1; i7++) {
/*  586 */       for (int i8 = 0; i8 < paramInt2; i8++) {
/*  587 */         double[] arrayOfDouble1 = arrayOfDouble[i8];
/*  588 */         for (int i11 = 0; i11 < paramInt3; i11++) {
/*  589 */           d2 += arrayOfDouble1[i11];
/*      */         }
/*      */       }
/*      */     }
/*      */     
/*  594 */     localTimer2.stop().display();
/*  595 */     localTimer2.minus(localTimer5).display();
/*  596 */     System.out.println((float)l2 / localTimer2.minus(localTimer5).seconds() + " elements / sec");
/*  597 */     if (paramBoolean) {
/*  598 */       DenseDoubleMatrix2D localDenseDoubleMatrix2D1 = new DenseDoubleMatrix2D(paramInt2, paramInt3);
/*  599 */       localDenseDoubleMatrix2D1.assign(arrayOfDouble);
/*  600 */       System.out.println(localDenseDoubleMatrix2D1);
/*      */     }
/*  602 */     System.out.println(d2);
/*      */     
/*  604 */     System.out.println("\nNow removing...");
/*  605 */     l1 = Runtime.getRuntime().freeMemory();
/*  606 */     for (int i9 = 0; i9 < paramInt1; i9++) {
/*  607 */       localTimer3.start();
/*  608 */       for (int i10 = 0; i10 < paramInt2; i10++) {
/*  609 */         double[] arrayOfDouble2 = arrayOfDouble[i10];
/*  610 */         for (int i12 = 0; i12 < paramInt3; i12++) {
/*  611 */           arrayOfDouble2[i12] = 0.0D;
/*      */         }
/*      */       }
/*      */       
/*  615 */       localTimer3.stop();
/*      */     }
/*  617 */     localTimer3.display();
/*  618 */     localTimer3.minus(localTimer4).display();
/*  619 */     System.out.println((float)l2 / localTimer3.minus(localTimer4).seconds() + " elements / sec");
/*  620 */     Runtime.getRuntime().gc();
/*  621 */     try { Thread.currentThread();Thread.sleep(1000L); } catch (InterruptedException localInterruptedException2) {}
/*  622 */     l3 = Runtime.getRuntime().freeMemory();
/*  623 */     System.out.println("KB needed=" + (l1 - l3) / 1024L);
/*  624 */     System.out.println("KB free=" + l3 / 1024L);
/*      */     
/*  626 */     if (paramBoolean) {
/*  627 */       DenseDoubleMatrix2D localDenseDoubleMatrix2D2 = new DenseDoubleMatrix2D(paramInt2, paramInt3);
/*  628 */       localDenseDoubleMatrix2D2.assign(arrayOfDouble);
/*  629 */       System.out.println(localDenseDoubleMatrix2D2);
/*      */     }
/*      */     
/*  632 */     System.out.println("bye bye.");
/*      */   }
/*      */   
/*      */ 
/*      */   public static void intBenchmark(int paramInt1, int paramInt2, int paramInt3, String paramString, boolean paramBoolean, int paramInt4, double paramDouble1, double paramDouble2)
/*      */   {
/*  638 */     throw new InternalError();
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
/*      */   public static void intBenchmarkPrimitive(int paramInt1, int paramInt2, int paramInt3, boolean paramBoolean)
/*      */   {
/*  773 */     throw new InternalError();
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
/*      */   public static void intBenchmarkPrimitiveOptimized(int paramInt1, int paramInt2, int paramInt3, boolean paramBoolean)
/*      */   {
/*  891 */     throw new InternalError();
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
/*      */   public static void main(String[] paramArrayOfString)
/*      */   {
/* 1016 */     int i = Integer.parseInt(paramArrayOfString[0]);
/* 1017 */     int j = Integer.parseInt(paramArrayOfString[1]);
/* 1018 */     int k = Integer.parseInt(paramArrayOfString[2]);
/*      */     
/*      */ 
/* 1021 */     String str1 = paramArrayOfString[3];
/* 1022 */     int m = Integer.parseInt(paramArrayOfString[4]);
/* 1023 */     double d1 = new Double(paramArrayOfString[5]).doubleValue();
/* 1024 */     double d2 = new Double(paramArrayOfString[6]).doubleValue();
/* 1025 */     boolean bool = paramArrayOfString[7].equals("print");
/* 1026 */     String str2 = paramArrayOfString[8];
/* 1027 */     String str3 = paramArrayOfString[9];
/*      */     
/* 1029 */     if (str2.equals("int")) {
/* 1030 */       if (str1.equals("primitive")) { intBenchmarkPrimitive(i, j, k, bool);
/* 1031 */       } else if (str1.equals("primitiveOpt")) intBenchmarkPrimitiveOptimized(i, j, k, bool); else {
/* 1032 */         intBenchmark(i, j, k, str1, bool, m, d1, d2);
/*      */       }
/* 1034 */     } else if (str2.equals("double")) {
/* 1035 */       if (str1.equals("primitive")) { doubleBenchmarkPrimitive(i, j, k, bool);
/* 1036 */       } else if (str1.equals("primitiveOpt")) { doubleBenchmarkPrimitiveOptimized(i, j, k, bool);
/* 1037 */       } else if (str3.equals("mult")) doubleBenchmarkMult(i, j, k, str1, bool, m, d1, d2); else {
/* 1038 */         doubleBenchmark(i, j, k, str1, bool, m, d1, d2);
/*      */       }
/*      */     }
/*      */   }
/*      */ }


/* Location:              /home/menick/Scrivania/ProcessMining/CNMining/CNMining.jar!/cern/colt/matrix/impl/BenchmarkMatrix2D.class
 * Java compiler version: 1 (45.3)
 * JD-Core Version:       0.7.1
 */