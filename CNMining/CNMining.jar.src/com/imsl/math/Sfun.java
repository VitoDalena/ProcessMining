/*     */ package com.imsl.math;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class Sfun
/*     */ {
/*     */   public static final double EPSILON_SMALL = 1.1102230246252E-16D;
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public static final double EPSILON_LARGE = 2.2204460492503E-16D;
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*  39 */   private static final double[] COT_COEF = { 0.2402591609829563D, -0.016533031601500228D, -4.299839193172402E-5D, -1.5928322332754105E-7D, -6.191093135129349E-10D, -2.430197415072646E-12D, -9.56093675880008E-15D, -3.763537981945806E-17D, -1.4816657464674657E-19D };
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*  52 */   private static final double[] SINH_COEF = { 0.1730421940471796D, 0.08759422192276048D, 0.00107947777456713D, 6.37484926075E-6D, 2.202366404E-8D, 4.98794E-11D, 7.973E-14D, 9.0E-17D };
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*  63 */   private static final double[] TANH_COEF = { -0.2582875664363471D, -0.11836106330053497D, 0.009869442648006398D, -8.35798662344582E-4D, 7.0904321198943E-5D, -6.01642431812E-6D, 5.105241908E-7D, -4.3320729077E-8D, 3.675999055E-9D, -3.11928496E-10D, 2.6468828E-11D, -2.246023E-12D, 1.90587E-13D, -1.6172E-14D, 1.372E-15D, -1.16E-16D, 9.0E-18D };
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*  83 */   private static final double[] ASINH_COEF = { -0.12820039911738187D, -0.05881176118995177D, 0.004727465432212481D, -4.938363162653618E-4D, 5.850620705855741E-5D, -7.466998328931368E-6D, 1.00116935835582E-6D, -1.3903543858708333E-7D, 1.9823169483172795E-8D, -2.8847468417848845E-9D, 4.2672965467159937E-10D, -6.397608465436636E-11D, 9.699168608906471E-12D, -1.4844276972043772E-12D, 2.290373793902745E-13D, -3.5588395132732646E-14D, 5.563969408005679E-15D, -8.746250959962468E-16D, 1.381524884452669E-16D, -2.1916688282900364E-17D, 3.490465852482756E-18D };
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/* 108 */   private static final double[] ATANH_COEF = { 0.0943951023931955D, 0.04919843705578616D, 0.002102593522455433D, 1.0735544497761166E-4D, 5.978267249293031E-6D, 3.505062030889135E-7D, 2.1263743437653402E-8D, 1.3216945357155272E-9D, 8.36587550117807E-11D, 5.370503749311002E-12D, 3.4866594701571077E-13D, 2.284549509603433E-14D, 1.508407105944793E-15D, 1.0024188168041091E-16D, 6.69867473816507E-18D, 4.497954546494931E-19D };
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/* 128 */   private static final double[] GAMMA_COEF = { 0.00857119559098933D, 0.004415381324841007D, 0.05685043681599363D, -0.00421983539641856D, 0.0013268081812124603D, -1.8930245297988805E-4D, 3.606925327441245E-5D, -6.056761904460864E-6D, 1.0558295463022833E-6D, -1.811967365542384E-7D, 3.117724964715322E-8D, -5.354219639019687E-9D, 9.193275519859589E-10D, -1.5779412802883398E-10D, 2.7079806229349544E-11D, -4.64681865382573E-12D, 7.97335019200742E-13D, -1.368078209830916E-13D, 2.3473194865638007E-14D, -4.027432614949067E-15D, 6.910051747372101E-16D, -1.185584500221993E-16D, 2.034148542496374E-17D, -3.490054341717406E-18D, 5.987993856485306E-19D, -1.027378057872228E-19D };
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/* 158 */   private static final double[] R9LGMC_COEF = { 0.16663894804518634D, -1.384948176067564E-5D, 9.81082564692473E-9D, -1.809129475572494E-11D, 6.221098041892606E-14D, -3.399615005417722E-16D, 2.683181998482699E-18D };
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/* 170 */   private static final double[] ALNRCS_COEF = { 1.037869356274377D, -0.13364301504908918D, 0.019408249135520562D, -0.0030107551127535777D, 4.869461479715485E-4D, -8.105488189317536E-5D, 1.3778847799559525E-5D, -2.380221089435897E-6D, 4.1640416213865184E-7D, -7.359582837807599E-8D, 1.3117611876241675E-8D, -2.3546709317742423E-9D, 4.2522773276035E-10D, -7.71908941348408E-11D, 1.407574648135907E-11D, -2.5769072058024682E-12D, 4.734240666629442E-13D, -8.724901267474264E-14D, 1.612461490274055E-14D, -2.9875652015665774E-15D, 5.548070120908289E-16D, -1.0324619158271569E-16D, 1.9250239203049852E-17D, -3.595507346526515E-18D, 6.726454253787686E-19D, -1.260262416873522E-19D };
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/* 200 */   private static final double[] ERFC_COEF = { -0.049046121234691806D, -0.14226120510371365D, 0.010035582187599796D, -5.768764699767485E-4D, 2.741993125219606E-5D, -1.1043175507344507E-6D, 3.8488755420345036E-8D, -1.1808582533875466E-9D, 3.2334215826050907E-11D, -7.991015947004549E-13D, 1.7990725113961456E-14D, -3.718635487818693E-16D, 7.103599003714253E-18D, -1.2612455119155226E-19D };
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/* 218 */   private static final double[] ERFC2_COEF = { -0.0696013466023095D, -0.04110133936262089D, 0.003914495866689627D, -4.906395650548979E-4D, 7.157479001377036E-5D, -1.1530716341312328E-5D, 1.9946705902019974E-6D, -3.642666471599223E-7D, 6.944372610005012E-8D, -1.371220902104366E-8D, 2.7883896610071373E-9D, -5.814164724331161E-10D, 1.2389204917527532E-10D, -2.6906391453067435E-11D, 5.942614350847911E-12D, -1.3323867357581197E-12D, 3.0280468061771323E-13D, -6.966648814941033E-14D, 1.620854541053923E-14D, -3.809934465250492E-15D, 9.040487815978831E-16D, -2.1640061950896072E-16D, 5.222102233995855E-17D, -1.2697296023645554E-17D, 3.1091455042761977E-18D, -7.663762920320386E-19D, 1.9008192513627452E-19D };
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/* 249 */   private static final double[] ERFCC_COEF = { 0.07151793102029248D, -0.026532434337606717D, 0.0017111539779208558D, -1.6375166345851787E-4D, 1.9871293500552038E-5D, -2.843712412766555E-6D, 4.6061613089631305E-7D, -8.227753025879209E-8D, 1.5921418727709012E-8D, -3.295071362252843E-9D, 7.223439760400556E-10D, -1.6648558133987297E-10D, 4.010392588237665E-11D, -1.004816214425731E-11D, 2.608275913300334E-12D, -6.991110560404025E-13D, 1.9294923332617072E-13D, -5.470131188754331E-14D, 1.5896633097626975E-14D, -4.726893980197555E-15D, 1.4358733767849847E-15D, -4.449510561817358E-16D, 1.4048108847682335E-16D, -4.5138183877642106E-17D, 1.474521541045133E-17D, -4.8926214069457765E-18D, 1.6476121414106467E-18D, -5.626817176329408E-19D, 1.9474433822320786E-19D };
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public static double acosh(double paramDouble)
/*     */   {
/*     */     double d;
/*     */     
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/* 299 */     if ((Double.isNaN(paramDouble)) || (paramDouble < 1.0D)) {
/* 300 */       d = NaN.0D;
/* 301 */     } else if (paramDouble < 9.490626562E7D)
/*     */     {
/* 303 */       d = Math.log(paramDouble + Math.sqrt(paramDouble * paramDouble - 1.0D));
/*     */     } else {
/* 305 */       d = 0.6931471805599453D + Math.log(paramDouble);
/*     */     }
/* 307 */     return d;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public static double asinh(double paramDouble)
/*     */   {
/* 318 */     double d2 = Math.abs(paramDouble);
/*     */     double d1;
/* 320 */     if (Double.isNaN(paramDouble)) {
/* 321 */       d1 = NaN.0D;
/* 322 */     } else if (d2 <= 1.05367E-8D)
/*     */     {
/* 324 */       d1 = paramDouble;
/* 325 */     } else if (d2 <= 1.0D) {
/* 326 */       d1 = paramDouble * (1.0D + csevl(2.0D * paramDouble * paramDouble - 1.0D, ASINH_COEF));
/* 327 */     } else if (d2 < 9.490626562E7D)
/*     */     {
/* 329 */       d1 = Math.log(d2 + Math.sqrt(d2 * d2 + 1.0D));
/*     */     } else {
/* 331 */       d1 = 0.6931471805599453D + Math.log(d2);
/*     */     }
/* 333 */     if (paramDouble < 0.0D) d1 = -d1;
/* 334 */     return d1;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public static double atanh(double paramDouble)
/*     */   {
/* 344 */     double d1 = Math.abs(paramDouble);
/*     */     
/*     */     double d2;
/* 347 */     if (Double.isNaN(paramDouble)) {
/* 348 */       d2 = NaN.0D;
/* 349 */     } else if (d1 < 1.82501E-8D)
/*     */     {
/* 351 */       d2 = paramDouble;
/* 352 */     } else if (d1 <= 0.5D) {
/* 353 */       d2 = paramDouble * (1.0D + csevl(8.0D * paramDouble * paramDouble - 1.0D, ATANH_COEF));
/* 354 */     } else if (d1 < 1.0D) {
/* 355 */       d2 = 0.5D * Math.log((1.0D + paramDouble) / (1.0D - paramDouble));
/* 356 */     } else if (d1 == 1.0D) {
/* 357 */       d2 = paramDouble * Double.POSITIVE_INFINITY;
/*     */     } else {
/* 359 */       d2 = NaN.0D;
/*     */     }
/* 361 */     return d2;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public static double cosh(double paramDouble)
/*     */   {
/* 372 */     double d2 = Math.exp(Math.abs(paramDouble));
/*     */     double d1;
/* 374 */     if (Double.isNaN(paramDouble)) {
/* 375 */       d1 = NaN.0D;
/* 376 */     } else if (Double.isInfinite(paramDouble)) {
/* 377 */       d1 = paramDouble;
/* 378 */     } else if (d2 < 9.490626562E7D)
/*     */     {
/* 380 */       d1 = 0.5D * (d2 + 1.0D / d2);
/*     */     } else {
/* 382 */       d1 = 0.5D * d2;
/*     */     }
/* 384 */     return d1;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public static double cot(double paramDouble)
/*     */   {
/* 395 */     double d7 = 0.011619772367581343D;
/*     */     
/* 397 */     double d5 = Math.abs(paramDouble);
/*     */     
/* 399 */     if (d5 > 4.5036E15D)
/*     */     {
/* 401 */       return NaN.0D;
/*     */     }
/*     */     
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/* 408 */     double d2 = (int)d5;
/* 409 */     double d6 = d5 - d2;
/* 410 */     double d4 = 0.625D * d2;
/* 411 */     d2 = (int)d4;
/* 412 */     d5 = d4 - d2 + 0.625D * d6 + d5 * d7;
/* 413 */     double d3 = (int)d5;
/* 414 */     d2 += d3;
/* 415 */     d5 -= d3;
/*     */     
/* 417 */     int i = (int)(d2 % 2.0D);
/* 418 */     if (i == 1) d5 = 1.0D - d5;
/*     */     double d1;
/* 420 */     if (d5 == 0.0D) {
/* 421 */       d1 = Double.POSITIVE_INFINITY;
/* 422 */     } else if (d5 <= 1.82501E-8D)
/*     */     {
/* 424 */       d1 = 1.0D / d5;
/* 425 */     } else if (d5 <= 0.25D) {
/* 426 */       d1 = (0.5D + csevl(32.0D * d5 * d5 - 1.0D, COT_COEF)) / d5;
/* 427 */     } else if (d5 <= 0.5D) {
/* 428 */       d1 = (0.5D + csevl(8.0D * d5 * d5 - 1.0D, COT_COEF)) / (0.5D * d5);
/* 429 */       d1 = (d1 * d1 - 1.0D) * 0.5D / d1;
/*     */     } else {
/* 431 */       d1 = (0.5D + csevl(2.0D * d5 * d5 - 1.0D, COT_COEF)) / (0.25D * d5);
/* 432 */       d1 = (d1 * d1 - 1.0D) * 0.5D / d1;
/* 433 */       d1 = (d1 * d1 - 1.0D) * 0.5D / d1;
/*     */     }
/* 435 */     if (paramDouble != 0.0D) d1 = sign(d1, paramDouble);
/* 436 */     if (i == 1) d1 = -d1;
/* 437 */     return d1;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   static double csevl(double paramDouble, double[] paramArrayOfDouble)
/*     */   {
/* 446 */     double d2 = 0.0D;
/* 447 */     double d1 = 0.0D;
/* 448 */     double d3 = 0.0D;
/* 449 */     double d4 = 2.0D * paramDouble;
/* 450 */     for (int i = paramArrayOfDouble.length - 1; i >= 0; i--) {
/* 451 */       d3 = d2;
/* 452 */       d2 = d1;
/* 453 */       d1 = d4 * d2 - d3 + paramArrayOfDouble[i];
/*     */     }
/* 455 */     return 0.5D * (d1 - d3);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */   private static double dlnrel(double paramDouble)
/*     */   {
/*     */     double d;
/*     */     
/* 464 */     if (paramDouble <= -1.0D) {
/* 465 */       d = NaN.0D;
/* 466 */     } else if (Math.abs(paramDouble) <= 0.375D) {
/* 467 */       d = paramDouble * (1.0D - paramDouble * csevl(paramDouble / 0.375D, ALNRCS_COEF));
/*     */     } else {
/* 469 */       d = Math.log(1.0D + paramDouble);
/*     */     }
/* 471 */     return d;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public static double erf(double paramDouble)
/*     */   {
/* 481 */     double d2 = Math.abs(paramDouble);
/*     */     double d1;
/* 483 */     if (d2 <= 1.49012E-8D)
/*     */     {
/* 485 */       d1 = 2.0D * paramDouble / 1.772453850905516D;
/* 486 */     } else if (d2 <= 1.0D) {
/* 487 */       d1 = paramDouble * (1.0D + csevl(2.0D * paramDouble * paramDouble - 1.0D, ERFC_COEF));
/* 488 */     } else if (d2 < 6.013687357D)
/*     */     {
/* 490 */       d1 = sign(1.0D - erfc(d2), paramDouble);
/*     */     } else {
/* 492 */       d1 = sign(1.0D, paramDouble);
/*     */     }
/* 494 */     return d1;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public static double erfc(double paramDouble)
/*     */   {
/* 504 */     double d2 = Math.abs(paramDouble);
/*     */     double d1;
/* 506 */     if (paramDouble <= -6.013687357D)
/*     */     {
/* 508 */       d1 = 2.0D;
/* 509 */     } else if (d2 < 1.49012E-8D)
/*     */     {
/* 511 */       d1 = 1.0D - 2.0D * paramDouble / 1.772453850905516D;
/*     */     } else {
/* 513 */       double d3 = d2 * d2;
/* 514 */       if (d2 < 1.0D) {
/* 515 */         d1 = 1.0D - paramDouble * (1.0D + csevl(2.0D * d3 - 1.0D, ERFC_COEF));
/* 516 */       } else if (d2 <= 4.0D) {
/* 517 */         d1 = Math.exp(-d3) / d2 * (0.5D + csevl((8.0D / d3 - 5.0D) / 3.0D, ERFC2_COEF));
/* 518 */         if (paramDouble < 0.0D) d1 = 2.0D - d1; if (paramDouble < 0.0D) d1 = 2.0D - d1;
/* 519 */         if (paramDouble < 0.0D) d1 = 2.0D - d1;
/*     */       } else {
/* 521 */         d1 = Math.exp(-d3) / d2 * (0.5D + csevl(8.0D / d3 - 1.0D, ERFCC_COEF));
/* 522 */         if (paramDouble < 0.0D) d1 = 2.0D - d1;
/*     */       }
/*     */     }
/* 525 */     return d1;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public static double fact(int paramInt)
/*     */   {
/* 535 */     double d = 1.0D;
/*     */     
/* 537 */     if ((Double.isNaN(paramInt)) || (paramInt < 0)) {
/* 538 */       d = NaN.0D;
/* 539 */     } else if (paramInt > 170)
/*     */     {
/* 541 */       d = Double.POSITIVE_INFINITY;
/*     */     } else {
/* 543 */       for (int i = 2; i <= paramInt; i++)
/* 544 */         d *= i;
/*     */     }
/* 546 */     return d;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public static double gamma(double paramDouble)
/*     */   {
/* 557 */     double d2 = Math.abs(paramDouble);
/*     */     double d1;
/* 559 */     if (d2 <= 10.0D)
/*     */     {
/*     */ 
/*     */ 
/*     */ 
/* 564 */       int i = (int)paramDouble;
/* 565 */       if (paramDouble < 0.0D) i--;
/* 566 */       d2 = paramDouble - i;
/* 567 */       i--;
/* 568 */       d1 = 0.9375D + csevl(2.0D * d2 - 1.0D, GAMMA_COEF);
/* 569 */       if (i != 0) {
/* 570 */         if (i < 0)
/*     */         {
/* 572 */           i = -i;
/* 573 */           if (paramDouble == 0.0D) {
/* 574 */             d1 = NaN.0D;
/* 575 */           } else if (d2 < 5.562684646268003E-309D) {
/* 576 */             d1 = Double.POSITIVE_INFINITY;
/*     */           } else {
/* 578 */             double d4 = i - 2;
/* 579 */             if ((paramDouble < 0.0D) && (paramDouble + d4 == 0.0D)) {
/* 580 */               d1 = NaN.0D;
/*     */             } else {
/* 582 */               for (int k = 0; k < i; k++) {
/* 583 */                 d1 /= (paramDouble + k);
/*     */               }
/*     */             }
/*     */           }
/*     */         } else {
/* 588 */           for (int j = 1; j <= i; j++) {
/* 589 */             d1 *= (d2 + j);
/*     */           }
/*     */         }
/*     */       }
/* 593 */     } else if (paramDouble > 171.614D) {
/* 594 */       d1 = Double.POSITIVE_INFINITY;
/* 595 */     } else if (paramDouble < -170.56D) {
/* 596 */       d1 = 0.0D;
/*     */     }
/*     */     else {
/* 599 */       d1 = Math.exp((d2 - 0.5D) * Math.log(d2) - d2 + 0.9189385332046727D + r9lgmc(d2));
/* 600 */       if (paramDouble < 0.0D) {
/* 601 */         double d3 = Math.sin(3.141592653589793D * d2);
/* 602 */         if ((d3 == 0.0D) || (Math.round(d2) == d2)) {
/* 603 */           d1 = NaN.0D;
/*     */         } else {
/* 605 */           d1 = -3.141592653589793D / (d2 * d3 * d1);
/*     */         }
/*     */       }
/*     */     }
/*     */     
/* 610 */     return d1;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public static double log10(double paramDouble)
/*     */   {
/* 620 */     return 0.4342944819032518D * Math.log(paramDouble);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public static double logBeta(double paramDouble1, double paramDouble2)
/*     */   {
/* 631 */     double d3 = Math.min(paramDouble1, paramDouble2);
/* 632 */     double d4 = Math.max(paramDouble1, paramDouble2);
/*     */     double d2;
/* 634 */     if (d3 <= 0.0D) {
/* 635 */       d2 = NaN.0D; } else { double d1;
/* 636 */       if (d3 >= 10.0D)
/*     */       {
/* 638 */         d1 = r9lgmc(d3) + r9lgmc(d4) - r9lgmc(d3 + d4);
/* 639 */         double d5 = dlnrel(-d3 / (d3 + d4));
/* 640 */         d2 = -0.5D * Math.log(d4) + 0.9189385332046728D + d1 + (d3 - 0.5D) * Math.log(d3 / (d3 + d4)) + d4 * d5;
/* 641 */       } else if (d4 >= 10.0D)
/*     */       {
/* 643 */         d1 = r9lgmc(d4) - r9lgmc(d3 + d4);
/*     */         
/* 645 */         d2 = logGamma(d3) + d1 + d3 - d3 * Math.log(d3 + d4) + (d4 - 0.5D) * dlnrel(-d3 / (d3 + d4));
/*     */       }
/*     */       else {
/* 648 */         d2 = Math.log(gamma(d3) * (gamma(d4) / gamma(d3 + d4)));
/*     */       } }
/* 650 */     return d2;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public static double logGamma(double paramDouble)
/*     */   {
/* 662 */     double d3 = Math.abs(paramDouble);
/*     */     double d1;
/* 664 */     if (d3 <= 10.0D) {
/* 665 */       d1 = Math.log(Math.abs(gamma(paramDouble)));
/* 666 */     } else if (paramDouble > 0.0D)
/*     */     {
/*     */ 
/* 669 */       d1 = 0.9189385332046727D + (paramDouble - 0.5D) * Math.log(paramDouble) - paramDouble + r9lgmc(d3);
/*     */     } else {
/* 671 */       double d2 = Math.abs(Math.sin(3.141592653589793D * d3));
/* 672 */       if ((d2 == 0.0D) || (Math.round(d3) == d3))
/*     */       {
/* 674 */         d1 = NaN.0D;
/*     */       } else {
/* 676 */         d1 = 0.22579135264472744D + (paramDouble - 0.5D) * Math.log(d3) - paramDouble - Math.log(d2) - r9lgmc(d3);
/*     */       }
/*     */     }
/* 679 */     return d1;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */   static double r9lgmc(double paramDouble)
/*     */   {
/*     */     double d1;
/*     */     
/*     */ 
/* 689 */     if (paramDouble < 10.0D) {
/* 690 */       d1 = NaN.0D;
/* 691 */     } else if (paramDouble < 9.490626562E7D)
/*     */     {
/* 693 */       double d2 = 10.0D / paramDouble;
/* 694 */       d1 = csevl(2.0D * d2 * d2 - 1.0D, R9LGMC_COEF) / paramDouble;
/* 695 */     } else if (paramDouble < 1.39118E11D)
/*     */     {
/*     */ 
/* 698 */       d1 = 1.0D / (12.0D * paramDouble);
/*     */     } else {
/* 700 */       d1 = 0.0D;
/*     */     }
/* 702 */     return d1;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */   private static double sign(double paramDouble1, double paramDouble2)
/*     */   {
/* 709 */     double d = paramDouble1 < 0.0D ? -paramDouble1 : paramDouble1;
/* 710 */     return paramDouble2 < 0.0D ? -d : d;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public static double sinh(double paramDouble)
/*     */   {
/* 721 */     double d2 = Math.abs(paramDouble);
/*     */     double d1;
/* 723 */     if (Double.isNaN(paramDouble)) {
/* 724 */       d1 = NaN.0D;
/* 725 */     } else { if (Double.isInfinite(d2))
/* 726 */         return paramDouble;
/* 727 */       if (d2 < 2.58096E-8D)
/*     */       {
/* 729 */         d1 = paramDouble;
/* 730 */       } else if (d2 <= 1.0D) {
/* 731 */         d1 = paramDouble * (1.0D + csevl(2.0D * paramDouble * paramDouble - 1.0D, SINH_COEF));
/*     */       } else {
/* 733 */         d2 = Math.exp(d2);
/* 734 */         if (d2 >= 9.490626562E7D)
/*     */         {
/* 736 */           d1 = sign(0.5D * d2, paramDouble);
/*     */         } else
/* 738 */           d1 = sign(0.5D * (d2 - 1.0D / d2), paramDouble);
/*     */       }
/*     */     }
/* 741 */     return d1;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public static double tanh(double paramDouble)
/*     */   {
/* 751 */     double d2 = Math.abs(paramDouble);
/*     */     double d1;
/* 753 */     if (Double.isNaN(paramDouble)) {
/* 754 */       d1 = NaN.0D;
/* 755 */     } else if (d2 < 1.82501E-8D)
/*     */     {
/* 757 */       d1 = paramDouble;
/* 758 */     } else if (d2 <= 1.0D) {
/* 759 */       d1 = paramDouble * (1.0D + csevl(2.0D * paramDouble * paramDouble - 1.0D, TANH_COEF));
/* 760 */     } else if (d2 < 7.977294885D)
/*     */     {
/* 762 */       d2 = Math.exp(d2);
/* 763 */       d1 = sign((d2 - 1.0D / d2) / (d2 + 1.0D / d2), paramDouble);
/*     */     } else {
/* 765 */       d1 = sign(1.0D, paramDouble);
/*     */     }
/* 767 */     return d1;
/*     */   }
/*     */ }


/* Location:              /home/menick/Scrivania/ProcessMining/CNMining/CNMining.jar!/com/imsl/math/Sfun.class
 * Java compiler version: 1 (45.3)
 * JD-Core Version:       0.7.1
 */