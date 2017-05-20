/*     */ package cern.jet.math;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class Bessel
/*     */   extends Constants
/*     */ {
/*  25 */   protected static final double[] A_i0 = { -4.4153416464793395E-18D, 3.3307945188222384E-17D, -2.431279846547955E-16D, 1.715391285555133E-15D, -1.1685332877993451E-14D, 7.676185498604936E-14D, -4.856446783111929E-13D, 2.95505266312964E-12D, -1.726826291441556E-11D, 9.675809035373237E-11D, -5.189795601635263E-10D, 2.6598237246823866E-9D, -1.300025009986248E-8D, 6.046995022541919E-8D, -2.670793853940612E-7D, 1.1173875391201037E-6D, -4.4167383584587505E-6D, 1.6448448070728896E-5D, -5.754195010082104E-5D, 1.8850288509584165E-4D, -5.763755745385824E-4D, 0.0016394756169413357D, -0.004324309995050576D, 0.010546460394594998D, -0.02373741480589947D, 0.04930528423967071D, -0.09490109704804764D, 0.17162090152220877D, -0.3046826723431984D, 0.6767952744094761D };
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*  66 */   protected static final double[] B_i0 = { -7.233180487874754E-18D, -4.830504485944182E-18D, 4.46562142029676E-17D, 3.461222867697461E-17D, -2.8276239805165836E-16D, -3.425485619677219E-16D, 1.7725601330565263E-15D, 3.8116806693526224E-15D, -9.554846698828307E-15D, -4.150569347287222E-14D, 1.54008621752141E-14D, 3.8527783827421426E-13D, 7.180124451383666E-13D, -1.7941785315068062E-12D, -1.3215811840447713E-11D, -3.1499165279632416E-11D, 1.1889147107846439E-11D, 4.94060238822497E-10D, 3.3962320257083865E-9D, 2.266668990498178E-8D, 2.0489185894690638E-7D, 2.8913705208347567E-6D, 6.889758346916825E-5D, 0.0033691164782556943D, 0.8044904110141088D };
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/* 105 */   protected static final double[] A_i1 = { 2.7779141127610464E-18D, -2.111421214358166E-17D, 1.5536319577362005E-16D, -1.1055969477353862E-15D, 7.600684294735408E-15D, -5.042185504727912E-14D, 3.223793365945575E-13D, -1.9839743977649436E-12D, 1.1736186298890901E-11D, -6.663489723502027E-11D, 3.625590281552117E-10D, -1.8872497517228294E-9D, 9.381537386495773E-9D, -4.445059128796328E-8D, 2.0032947535521353E-7D, -8.568720264695455E-7D, 3.4702513081376785E-6D, -1.3273163656039436E-5D, 4.781565107550054E-5D, -1.6176081582589674E-4D, 5.122859561685758E-4D, -0.0015135724506312532D, 0.004156422944312888D, -0.010564084894626197D, 0.024726449030626516D, -0.05294598120809499D, 0.1026436586898471D, -0.17641651835783406D, 0.25258718644363365D };
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/* 143 */   protected static final double[] B_i1 = { 7.517296310842105E-18D, 4.414348323071708E-18D, -4.6503053684893586E-17D, -3.209525921993424E-17D, 2.96262899764595E-16D, 3.3082023109209285E-16D, -1.8803547755107825E-15D, -3.8144030724370075E-15D, 1.0420276984128802E-14D, 4.272440016711951E-14D, -2.1015418427726643E-14D, -4.0835511110921974E-13D, -7.198551776245908E-13D, 2.0356285441470896E-12D, 1.4125807436613782E-11D, 3.2526035830154884E-11D, -1.8974958123505413E-11D, -5.589743462196584E-10D, -3.835380385964237E-9D, -2.6314688468895196E-8D, -2.512236237870209E-7D, -3.882564808877691E-6D, -1.1058893876262371E-4D, -0.009761097491361469D, 0.7785762350182801D };
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/* 183 */   protected static final double[] A_k0 = { 1.374465435613523E-16D, 4.25981614279661E-14D, 1.0349695257633842E-11D, 1.904516377220209E-9D, 2.5347910790261494E-7D, 2.286212103119452E-5D, 0.001264615411446926D, 0.0359799365153615D, 0.3442898999246285D, -0.5353273932339028D };
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/* 201 */   protected static final double[] B_k0 = { 5.300433772686263E-18D, -1.6475804301524212E-17D, 5.2103915050390274E-17D, -1.678231096805412E-16D, 5.512055978524319E-16D, -1.848593377343779E-15D, 6.3400764774050706E-15D, -2.2275133269916698E-14D, 8.032890775363575E-14D, -2.9800969231727303E-13D, 1.140340588208475E-12D, -4.514597883373944E-12D, 1.8559491149547177E-11D, -7.957489244477107E-11D, 3.577397281400301E-10D, -1.69753450938906E-9D, 8.574034017414225E-9D, -4.660489897687948E-8D, 2.766813639445015E-7D, -1.8317555227191195E-6D, 1.39498137188765E-5D, -1.2849549581627802E-4D, 0.0015698838857300533D, -0.0314481013119645D, 2.4403030820659555D };
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/* 240 */   protected static final double[] A_k1 = { -7.023863479386288E-18D, -2.427449850519366E-15D, -6.666901694199329E-13D, -1.4114883926335278E-10D, -2.213387630734726E-8D, -2.4334061415659684E-6D, -1.730288957513052E-4D, -0.006975723859639864D, -0.12261118082265715D, -0.3531559607765449D, 1.5253002273389478D };
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/* 259 */   protected static final double[] B_k1 = { -5.756744483665017E-18D, 1.7940508731475592E-17D, -5.689462558442859E-17D, 1.838093544366639E-16D, -6.057047248373319E-16D, 2.038703165624334E-15D, -7.019837090418314E-15D, 2.4771544244813043E-14D, -8.976705182324994E-14D, 3.3484196660784293E-13D, -1.2891739609510289E-12D, 5.13963967348173E-12D, -2.1299678384275683E-11D, 9.218315187605006E-11D, -4.1903547593418965E-10D, 2.015049755197033E-9D, -1.0345762465678097E-8D, 5.7410841254500495E-8D, -3.5019606030878126E-7D, 2.406484947837217E-6D, -1.936197974166083E-5D, 1.9521551847135162E-4D, -0.002857816859622779D, 0.10392373657681724D, 2.7206261904844427D };
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public static double i0(double paramDouble)
/*     */     throws ArithmeticException
/*     */   {
/* 305 */     if (paramDouble < 0.0D) paramDouble = -paramDouble;
/* 306 */     if (paramDouble <= 8.0D) {
/* 307 */       double d = paramDouble / 2.0D - 2.0D;
/* 308 */       return Math.exp(paramDouble) * Arithmetic.chbevl(d, A_i0, 30);
/*     */     }
/*     */     
/* 311 */     return Math.exp(paramDouble) * Arithmetic.chbevl(32.0D / paramDouble - 2.0D, B_i0, 25) / Math.sqrt(paramDouble);
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
/*     */   public static double i0e(double paramDouble)
/*     */     throws ArithmeticException
/*     */   {
/* 325 */     if (paramDouble < 0.0D) paramDouble = -paramDouble;
/* 326 */     if (paramDouble <= 8.0D) {
/* 327 */       double d = paramDouble / 2.0D - 2.0D;
/* 328 */       return Arithmetic.chbevl(d, A_i0, 30);
/*     */     }
/*     */     
/* 331 */     return Arithmetic.chbevl(32.0D / paramDouble - 2.0D, B_i0, 25) / Math.sqrt(paramDouble);
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
/*     */   public static double i1(double paramDouble)
/*     */     throws ArithmeticException
/*     */   {
/* 348 */     double d2 = Math.abs(paramDouble);
/* 349 */     if (d2 <= 8.0D)
/*     */     {
/* 351 */       double d1 = d2 / 2.0D - 2.0D;
/* 352 */       d2 = Arithmetic.chbevl(d1, A_i1, 29) * d2 * Math.exp(d2);
/*     */     }
/*     */     else
/*     */     {
/* 356 */       d2 = Math.exp(d2) * Arithmetic.chbevl(32.0D / d2 - 2.0D, B_i1, 25) / Math.sqrt(d2);
/*     */     }
/* 358 */     if (paramDouble < 0.0D)
/* 359 */       d2 = -d2;
/* 360 */     return d2;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public static double i1e(double paramDouble)
/*     */     throws ArithmeticException
/*     */   {
/* 373 */     double d2 = Math.abs(paramDouble);
/* 374 */     if (d2 <= 8.0D)
/*     */     {
/* 376 */       double d1 = d2 / 2.0D - 2.0D;
/* 377 */       d2 = Arithmetic.chbevl(d1, A_i1, 29) * d2;
/*     */     }
/*     */     else
/*     */     {
/* 381 */       d2 = Arithmetic.chbevl(32.0D / d2 - 2.0D, B_i1, 25) / Math.sqrt(d2);
/*     */     }
/* 383 */     if (paramDouble < 0.0D)
/* 384 */       d2 = -d2;
/* 385 */     return d2;
/*     */   }
/*     */   
/*     */ 
/*     */   public static double j0(double paramDouble)
/*     */     throws ArithmeticException
/*     */   {
/*     */     double d1;
/*     */     
/* 394 */     if ((d1 = Math.abs(paramDouble)) < 8.0D) {
/* 395 */       d2 = paramDouble * paramDouble;
/* 396 */       d3 = 5.7568490574E10D + d2 * (-1.3362590354E10D + d2 * (6.516196407E8D + d2 * (-1.121442418E7D + d2 * (77392.33017D + d2 * -184.9052456D))));
/*     */       
/* 398 */       d4 = 5.7568490411E10D + d2 * (1.029532985E9D + d2 * (9494680.718D + d2 * (59272.64853D + d2 * (267.8532712D + d2 * 1.0D))));
/*     */       
/*     */ 
/* 401 */       return d3 / d4;
/*     */     }
/*     */     
/*     */ 
/* 405 */     double d2 = 8.0D / d1;
/* 406 */     double d3 = d2 * d2;
/* 407 */     double d4 = d1 - 0.785398164D;
/* 408 */     double d5 = 1.0D + d3 * (-0.001098628627D + d3 * (2.734510407E-5D + d3 * (-2.073370639E-6D + d3 * 2.093887211E-7D)));
/*     */     
/* 410 */     double d6 = -0.01562499995D + d3 * (1.430488765E-4D + d3 * (-6.911147651E-6D + d3 * (7.621095161E-7D - d3 * 9.34935152E-8D)));
/*     */     
/*     */ 
/*     */ 
/* 414 */     return Math.sqrt(0.636619772D / d1) * (Math.cos(d4) * d5 - d2 * Math.sin(d4) * d6);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   public static double j1(double paramDouble)
/*     */     throws ArithmeticException
/*     */   {
/*     */     double d1;
/*     */     
/*     */ 
/*     */ 
/* 427 */     if ((d1 = Math.abs(paramDouble)) < 8.0D) {
/* 428 */       d2 = paramDouble * paramDouble;
/* 429 */       d3 = paramDouble * (7.2362614232E10D + d2 * (-7.895059235E9D + d2 * (2.423968531E8D + d2 * (-2972611.439D + d2 * (15704.4826D + d2 * -30.16036606D)))));
/*     */       
/* 431 */       d4 = 1.44725228442E11D + d2 * (2.300535178E9D + d2 * (1.858330474E7D + d2 * (99447.43394D + d2 * (376.9991397D + d2 * 1.0D))));
/*     */       
/* 433 */       return d3 / d4;
/*     */     }
/*     */     
/* 436 */     double d5 = 8.0D / d1;
/* 437 */     double d6 = d1 - 2.356194491D;
/* 438 */     double d2 = d5 * d5;
/*     */     
/* 440 */     double d3 = 1.0D + d2 * (0.00183105D + d2 * (-3.516396496E-5D + d2 * (2.457520174E-6D + d2 * -2.40337019E-7D)));
/*     */     
/* 442 */     double d4 = 0.04687499995D + d2 * (-2.002690873E-4D + d2 * (8.449199096E-6D + d2 * (-8.8228987E-7D + d2 * 1.05787412E-7D)));
/*     */     
/*     */ 
/* 445 */     double d7 = Math.sqrt(0.636619772D / d1) * (Math.cos(d6) * d3 - d5 * Math.sin(d6) * d4);
/*     */     
/* 447 */     if (paramDouble < 0.0D) d7 = -d7;
/* 448 */     return d7;
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
/*     */   public static double jn(int paramInt, double paramDouble)
/*     */     throws ArithmeticException
/*     */   {
/* 465 */     if (paramInt == 0) return j0(paramDouble);
/* 466 */     if (paramInt == 1) { return j1(paramDouble);
/*     */     }
/* 468 */     double d1 = Math.abs(paramDouble);
/* 469 */     if (d1 == 0.0D) return 0.0D;
/*     */     double d6;
/* 471 */     double d3; double d2; int i; double d4; double d7; if (d1 > paramInt) {
/* 472 */       d6 = 2.0D / d1;
/* 473 */       d3 = j0(d1);
/* 474 */       d2 = j1(d1);
/* 475 */       for (i = 1; i < paramInt; i++) {
/* 476 */         d4 = i * d6 * d2 - d3;
/* 477 */         d3 = d2;
/* 478 */         d2 = d4;
/*     */       }
/* 480 */       d7 = d2;
/*     */     }
/*     */     else {
/* 483 */       d6 = 2.0D / d1;
/* 484 */       int j = 2 * ((paramInt + (int)Math.sqrt(40.0D * paramInt)) / 2);
/* 485 */       int k = 0;
/* 486 */       d4 = d7 = d5 = 0.0D;
/* 487 */       d2 = 1.0D;
/* 488 */       for (i = j; i > 0; i--) {
/* 489 */         d3 = i * d6 * d2 - d4;
/* 490 */         d4 = d2;
/* 491 */         d2 = d3;
/* 492 */         if (Math.abs(d2) > 1.0E10D) {
/* 493 */           d2 *= 1.0E-10D;
/* 494 */           d4 *= 1.0E-10D;
/* 495 */           d7 *= 1.0E-10D;
/* 496 */           d5 *= 1.0E-10D;
/*     */         }
/* 498 */         if (k != 0) d5 += d2;
/* 499 */         k = k == 0 ? 1 : 0;
/* 500 */         if (i == paramInt) d7 = d4;
/*     */       }
/* 502 */       double d5 = 2.0D * d5 - d2;
/* 503 */       d7 /= d5;
/*     */     }
/* 505 */     return (paramDouble < 0.0D) && (paramInt % 2 == 1) ? -d7 : d7;
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
/*     */   public static double k0(double paramDouble)
/*     */     throws ArithmeticException
/*     */   {
/* 520 */     if (paramDouble <= 0.0D) throw new ArithmeticException();
/* 521 */     if (paramDouble <= 2.0D) {
/* 522 */       d1 = paramDouble * paramDouble - 2.0D;
/* 523 */       d1 = Arithmetic.chbevl(d1, A_k0, 10) - Math.log(0.5D * paramDouble) * i0(paramDouble);
/* 524 */       return d1;
/*     */     }
/*     */     
/* 527 */     double d2 = 8.0D / paramDouble - 2.0D;
/* 528 */     double d1 = Math.exp(-paramDouble) * Arithmetic.chbevl(d2, B_k0, 25) / Math.sqrt(paramDouble);
/* 529 */     return d1;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public static double k0e(double paramDouble)
/*     */     throws ArithmeticException
/*     */   {
/* 540 */     if (paramDouble <= 0.0D) throw new ArithmeticException();
/* 541 */     if (paramDouble <= 2.0D) {
/* 542 */       d = paramDouble * paramDouble - 2.0D;
/* 543 */       d = Arithmetic.chbevl(d, A_k0, 10) - Math.log(0.5D * paramDouble) * i0(paramDouble);
/* 544 */       return d * Math.exp(paramDouble);
/*     */     }
/*     */     
/* 547 */     double d = Arithmetic.chbevl(8.0D / paramDouble - 2.0D, B_k0, 25) / Math.sqrt(paramDouble);
/* 548 */     return d;
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
/*     */   public static double k1(double paramDouble)
/*     */     throws ArithmeticException
/*     */   {
/* 563 */     double d2 = 0.5D * paramDouble;
/* 564 */     if (d2 <= 0.0D) throw new ArithmeticException();
/* 565 */     if (paramDouble <= 2.0D) {
/* 566 */       double d1 = paramDouble * paramDouble - 2.0D;
/* 567 */       d1 = Math.log(d2) * i1(paramDouble) + Arithmetic.chbevl(d1, A_k1, 11) / paramDouble;
/* 568 */       return d1;
/*     */     }
/*     */     
/* 571 */     return Math.exp(-paramDouble) * Arithmetic.chbevl(8.0D / paramDouble - 2.0D, B_k1, 25) / Math.sqrt(paramDouble);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public static double k1e(double paramDouble)
/*     */     throws ArithmeticException
/*     */   {
/* 584 */     if (paramDouble <= 0.0D) throw new ArithmeticException();
/* 585 */     if (paramDouble <= 2.0D) {
/* 586 */       double d = paramDouble * paramDouble - 2.0D;
/* 587 */       d = Math.log(0.5D * paramDouble) * i1(paramDouble) + Arithmetic.chbevl(d, A_k1, 11) / paramDouble;
/* 588 */       return d * Math.exp(paramDouble);
/*     */     }
/*     */     
/* 591 */     return Arithmetic.chbevl(8.0D / paramDouble - 2.0D, B_k1, 25) / Math.sqrt(paramDouble);
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
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public static double kn(int paramInt, double paramDouble)
/*     */     throws ArithmeticException
/*     */   {
/*     */     int j;
/*     */     
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/* 648 */     if (paramInt < 0) {
/* 649 */       j = -paramInt;
/*     */     } else {
/* 651 */       j = paramInt;
/*     */     }
/* 653 */     if (j > 31) throw new ArithmeticException("Overflow");
/* 654 */     if (paramDouble <= 0.0D) throw new IllegalArgumentException();
/*     */     double d3;
/* 656 */     double d9; if (paramDouble <= 9.55D) {
/* 657 */       d10 = 0.0D;
/* 658 */       d8 = 0.25D * paramDouble * paramDouble;
/* 659 */       d11 = 1.0D;
/* 660 */       d12 = 0.0D;
/* 661 */       double d14 = 1.0D;
/* 662 */       double d16 = 2.0D / paramDouble;
/*     */       
/* 664 */       if (j > 0)
/*     */       {
/* 666 */         d12 = -0.5772156649015329D;
/* 667 */         d1 = 1.0D;
/* 668 */         for (i = 1; i < j; i++) {
/* 669 */           d12 += 1.0D / d1;
/* 670 */           d1 += 1.0D;
/* 671 */           d11 *= d1;
/*     */         }
/*     */         
/* 674 */         d14 = d16;
/*     */         
/* 676 */         if (j == 1) {
/* 677 */           d10 = 1.0D / paramDouble;
/*     */         }
/*     */         else {
/* 680 */           d3 = d11 / j;
/* 681 */           double d2 = 1.0D;
/* 682 */           d7 = d3;
/* 683 */           d9 = -d8;
/* 684 */           double d5 = 1.0D;
/* 685 */           for (i = 1; i < j; i++) {
/* 686 */             d3 /= (j - i);
/* 687 */             d2 *= i;
/* 688 */             d5 *= d9;
/* 689 */             d6 = d3 * d5 / d2;
/* 690 */             d7 += d6;
/* 691 */             if (Double.MAX_VALUE - Math.abs(d6) < Math.abs(d7)) throw new ArithmeticException("Overflow");
/* 692 */             if ((d16 > 1.0D) && (Double.MAX_VALUE / d16 < d14)) throw new ArithmeticException("Overflow");
/* 693 */             d14 *= d16;
/*     */           }
/* 695 */           d7 *= 0.5D;
/* 696 */           d6 = Math.abs(d7);
/* 697 */           if ((d14 > 1.0D) && (Double.MAX_VALUE / d14 < d6)) throw new ArithmeticException("Overflow");
/* 698 */           if ((d6 > 1.0D) && (Double.MAX_VALUE / d6 < d14)) throw new ArithmeticException("Overflow");
/* 699 */           d10 = d7 * d14;
/*     */         }
/*     */       }
/*     */       
/*     */ 
/* 704 */       double d15 = 2.0D * Math.log(0.5D * paramDouble);
/* 705 */       d13 = -0.5772156649015329D;
/* 706 */       if (j == 0) {
/* 707 */         d12 = d13;
/* 708 */         d6 = 1.0D;
/*     */       }
/*     */       else {
/* 711 */         d12 += 1.0D / j;
/* 712 */         d6 = 1.0D / d11;
/*     */       }
/* 714 */       d7 = (d13 + d12 - d15) * d6;
/* 715 */       d1 = 1.0D;
/*     */       do {
/* 717 */         d6 *= d8 / (d1 * (d1 + j));
/* 718 */         d13 += 1.0D / d1;
/* 719 */         d12 += 1.0D / (d1 + j);
/* 720 */         d7 += (d13 + d12 - d15) * d6;
/* 721 */         d1 += 1.0D;
/*     */       }
/* 723 */       while (Math.abs(d6 / d7) > 1.1102230246251565E-16D);
/*     */       
/* 725 */       d7 = 0.5D * d7 / d14;
/* 726 */       if ((j & 0x1) > 0)
/* 727 */         d7 = -d7;
/* 728 */       d10 += d7;
/*     */       
/* 730 */       return d10;
/*     */     }
/*     */     
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/* 737 */     if (paramDouble > 709.782712893384D) throw new ArithmeticException("Underflow");
/* 738 */     double d1 = j;
/* 739 */     double d12 = 4.0D * d1 * d1;
/* 740 */     double d13 = 1.0D;
/* 741 */     double d8 = 8.0D * paramDouble;
/* 742 */     double d11 = 1.0D;
/* 743 */     double d6 = 1.0D;
/* 744 */     double d7 = d6;
/* 745 */     double d4 = Double.MAX_VALUE;
/* 746 */     int i = 0;
/*     */     do {
/* 748 */       d9 = d12 - d13 * d13;
/* 749 */       d6 = d6 * d9 / (d11 * d8);
/* 750 */       d3 = Math.abs(d6);
/* 751 */       if ((i >= j) && (d3 > d4)) {
/* 752 */         d10 = Math.exp(-paramDouble) * Math.sqrt(3.141592653589793D / (2.0D * paramDouble)) * d7;
/* 753 */         return d10;
/*     */       }
/* 755 */       d4 = d3;
/* 756 */       d7 += d6;
/* 757 */       d11 += 1.0D;
/* 758 */       d13 += 2.0D;
/* 759 */       i++;
/* 760 */     } while (Math.abs(d6 / d7) > 1.1102230246251565E-16D);
/*     */     
/*     */ 
/* 763 */     double d10 = Math.exp(-paramDouble) * Math.sqrt(3.141592653589793D / (2.0D * paramDouble)) * d7;
/* 764 */     return d10;
/*     */   }
/*     */   
/*     */ 
/*     */   public static double y0(double paramDouble)
/*     */     throws ArithmeticException
/*     */   {
/* 771 */     if (paramDouble < 8.0D) {
/* 772 */       d1 = paramDouble * paramDouble;
/* 773 */       d2 = -2.957821389E9D + d1 * (7.062834065E9D + d1 * (-5.123598036E8D + d1 * (1.087988129E7D + d1 * (-86327.92757D + d1 * 228.4622733D))));
/*     */       
/* 775 */       d3 = 4.0076544269E10D + d1 * (7.452499648E8D + d1 * (7189466.438D + d1 * (47447.2647D + d1 * (226.1030244D + d1 * 1.0D))));
/*     */       
/*     */ 
/* 778 */       return d2 / d3 + 0.636619772D * j0(paramDouble) * Math.log(paramDouble);
/*     */     }
/*     */     
/* 781 */     double d1 = 8.0D / paramDouble;
/* 782 */     double d2 = d1 * d1;
/* 783 */     double d3 = paramDouble - 0.785398164D;
/*     */     
/* 785 */     double d4 = 1.0D + d2 * (-0.001098628627D + d2 * (2.734510407E-5D + d2 * (-2.073370639E-6D + d2 * 2.093887211E-7D)));
/*     */     
/* 787 */     double d5 = -0.01562499995D + d2 * (1.430488765E-4D + d2 * (-6.911147651E-6D + d2 * (7.621095161E-7D + d2 * -9.34945152E-8D)));
/*     */     
/*     */ 
/* 790 */     return Math.sqrt(0.636619772D / paramDouble) * (Math.sin(d3) * d4 + d1 * Math.cos(d3) * d5);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   public static double y1(double paramDouble)
/*     */     throws ArithmeticException
/*     */   {
/* 799 */     if (paramDouble < 8.0D) {
/* 800 */       d1 = paramDouble * paramDouble;
/* 801 */       d2 = paramDouble * (-4.900604943E12D + d1 * (1.27527439E12D + d1 * (-5.153438139E10D + d1 * (7.349264551E8D + d1 * (-4237922.726D + d1 * 8511.937935D)))));
/*     */       
/*     */ 
/* 804 */       d3 = 2.49958057E13D + d1 * (4.244419664E11D + d1 * (3.733650367E9D + d1 * (2.245904002E7D + d1 * (102042.605D + d1 * (354.9632885D + d1)))));
/*     */       
/*     */ 
/* 807 */       return d2 / d3 + 0.636619772D * (j1(paramDouble) * Math.log(paramDouble) - 1.0D / paramDouble);
/*     */     }
/*     */     
/* 810 */     double d1 = 8.0D / paramDouble;
/* 811 */     double d2 = d1 * d1;
/* 812 */     double d3 = paramDouble - 2.356194491D;
/* 813 */     double d4 = 1.0D + d2 * (0.00183105D + d2 * (-3.516396496E-5D + d2 * (2.457520174E-6D + d2 * -2.40337019E-7D)));
/*     */     
/* 815 */     double d5 = 0.04687499995D + d2 * (-2.002690873E-4D + d2 * (8.449199096E-6D + d2 * (-8.8228987E-7D + d2 * 1.05787412E-7D)));
/*     */     
/*     */ 
/* 818 */     return Math.sqrt(0.636619772D / paramDouble) * (Math.sin(d3) * d4 + d1 * Math.cos(d3) * d5);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public static double yn(int paramInt, double paramDouble)
/*     */     throws ArithmeticException
/*     */   {
/* 830 */     if (paramInt == 0) return y0(paramDouble);
/* 831 */     if (paramInt == 1) { return y1(paramDouble);
/*     */     }
/* 833 */     double d4 = 2.0D / paramDouble;
/* 834 */     double d1 = y1(paramDouble);
/* 835 */     double d2 = y0(paramDouble);
/* 836 */     for (int i = 1; i < paramInt; i++) {
/* 837 */       double d3 = i * d4 * d1 - d2;
/* 838 */       d2 = d1;
/* 839 */       d1 = d3;
/*     */     }
/* 841 */     return d1;
/*     */   }
/*     */ }


/* Location:              /home/menick/Scrivania/ProcessMining/CNMining/CNMining.jar!/cern/jet/math/Bessel.class
 * Java compiler version: 1 (45.3)
 * JD-Core Version:       0.7.1
 */