/*     */ package flanagan.control;
/*     */ 
/*     */ import flanagan.complex.Complex;
/*     */ import flanagan.complex.ComplexPoly;
/*     */ import java.io.PrintStream;
/*     */ import java.util.ArrayList;
/*     */ import java.util.Vector;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class OpenLoop
/*     */   extends BlackBox
/*     */ {
/*  42 */   private ArrayList<BlackBox> openPath = new ArrayList();
/*  43 */   private ArrayList<Object> segments = new ArrayList();
/*     */   
/*  45 */   private int nBoxes = 0;
/*  46 */   private int nSeg = 0;
/*     */   
/*  48 */   private boolean checkPath = false;
/*  49 */   private boolean checkNoMix = true;
/*  50 */   private boolean checkConsolidate = false;
/*     */   
/*     */   public OpenLoop()
/*     */   {
/*  54 */     super("OpenLoop");
/*     */   }
/*     */   
/*     */   public void addBoxToPath(BlackBox box)
/*     */   {
/*  59 */     this.openPath.add(box);
/*  60 */     this.nBoxes += 1;
/*     */   }
/*     */   
/*     */ 
/*     */   public void consolidate()
/*     */   {
/*  66 */     if (!this.segments.isEmpty()) {
/*  67 */       this.segments.clear();
/*  68 */       this.nBoxes = 0;
/*  69 */       this.nSeg = 0;
/*  70 */       this.checkNoMix = true;
/*  71 */       this.checkPath = false;
/*     */     }
/*     */     
/*     */ 
/*  75 */     segment();
/*     */     
/*  77 */     BlackBox aa = null;
/*  78 */     if (this.nSeg == 1) {
/*  79 */       aa = (BlackBox)this.segments.get(3);
/*     */     }
/*     */     else {
/*  82 */       aa = combineSegment(0, this.nBoxes);
/*     */     }
/*  84 */     this.sNumer = aa.sNumer.copy();
/*  85 */     this.sDenom = aa.sDenom.copy();
/*  86 */     this.sNumerPade = aa.sNumerPade.copy();
/*  87 */     this.sDenomPade = aa.sDenomPade.copy();
/*  88 */     this.sNumerDeg = aa.sNumerDeg;
/*  89 */     this.sDenomDeg = aa.sDenomDeg;
/*  90 */     this.sNumerDegPade = aa.sNumerDegPade;
/*  91 */     this.sDenomDegPade = aa.sDenomDegPade;
/*  92 */     this.deadTime = aa.deadTime;
/*  93 */     this.sZeros = Complex.copy(aa.sZeros);
/*  94 */     this.sPoles = Complex.copy(aa.sPoles);
/*  95 */     this.sZerosPade = Complex.copy(aa.sZerosPade);
/*  96 */     this.sPolesPade = Complex.copy(aa.sPolesPade);
/*  97 */     this.padeAdded = true;
/*     */     
/*  99 */     this.checkConsolidate = true;
/*     */   }
/*     */   
/*     */   public void segment()
/*     */   {
/* 104 */     this.checkPath = true;
/* 105 */     this.nBoxes = this.openPath.size();
/* 106 */     String thisName = " ";
/*     */     
/*     */ 
/* 109 */     int iStart1 = 0;
/* 110 */     int iEnd1 = 0;
/* 111 */     int iStart2 = 0;
/*     */     
/* 113 */     int iEnd2 = 0;
/*     */     
/* 115 */     int iNewStart = 0;
/* 116 */     int nnBoxes = 0;
/* 117 */     int nInSeg = 0;
/*     */     
/* 119 */     String name1 = " ";
/* 120 */     String name2 = " ";
/* 121 */     String lastConv = " ";
/*     */     
/* 123 */     int ii = 0;
/*     */     
/* 125 */     double deltaThold = 0.0D;
/*     */     
/* 127 */     while (ii < this.nBoxes) {
/* 128 */       nInSeg++;
/* 129 */       BlackBox bb = (BlackBox)this.openPath.get(ii);
/* 130 */       thisName = bb.fixedName;
/*     */       
/*     */ 
/*     */ 
/* 134 */       if (thisName.equals("ZeroOrderHold")) {
/* 135 */         if (!lastConv.equals(" ")) this.checkNoMix = false;
/* 136 */         if (ii < this.nBoxes - 1) {
/* 137 */           BlackBox cc = (BlackBox)this.openPath.get(ii + 1);
/*     */           
/*     */ 
/* 140 */           if (cc.fixedName.equals("AtoD")) {
/* 141 */             if (lastConv.equals("AtoD")) throw new IllegalArgumentException("Two consecutive ADCs with no intervening DAC");
/* 142 */             if (nInSeg > 1) {
/* 143 */               iEnd1 = ii - 1;
/* 144 */               name1 = "analogue";
/* 145 */               iStart2 = ii;
/* 146 */               iEnd2 = ii + 1;
/* 147 */               name2 = "AtoD";
/* 148 */               nnBoxes = 2;
/* 149 */               this.nSeg += 2;
/* 150 */               ii += 2;
/* 151 */               iNewStart = iEnd2 + 1;
/*     */             }
/*     */             else {
/* 154 */               iEnd1 = ii + 1;
/* 155 */               name1 = "AtoD";
/* 156 */               nnBoxes = 1;
/* 157 */               this.nSeg += 1;
/* 158 */               ii += 2;
/* 159 */               iNewStart = iEnd1 + 1;
/*     */             }
/* 161 */             lastConv = "AtoD";
/* 162 */             nInSeg = 0;
/*     */           }
/*     */           else {
/* 165 */             System.out.println("WARNING!! OpenLoop.checkPath: ZOH without a following ADC");
/* 166 */             if (nInSeg > 1) {
/* 167 */               iEnd1 = ii - 1;
/* 168 */               name1 = "analogue";
/* 169 */               iStart2 = ii;
/* 170 */               iEnd2 = ii;
/* 171 */               name2 = "ZOH";
/* 172 */               nnBoxes = 2;
/* 173 */               this.nSeg = 2;
/* 174 */               ii += 1;
/* 175 */               iNewStart = iEnd2 + 1;
/*     */             }
/*     */             else {
/* 178 */               iEnd1 = ii;
/* 179 */               name1 = "ZOH";
/* 180 */               nnBoxes = 1;
/* 181 */               this.nSeg = 1;
/* 182 */               ii += 1;
/* 183 */               iNewStart = iEnd1 + 1;
/*     */             }
/* 185 */             nInSeg = 0;
/* 186 */             lastConv = "ZOH";
/*     */           }
/*     */         }
/*     */         else {
/* 190 */           System.out.println("WARNING!! OpenLoop.checkPath: path ends with ZOH");
/* 191 */           if (nInSeg > 1) {
/* 192 */             iEnd1 = ii - 1;
/* 193 */             name1 = "analogue";
/* 194 */             iStart2 = ii;
/* 195 */             iEnd2 = ii;
/* 196 */             name2 = "ZOH";
/* 197 */             nnBoxes = 2;
/* 198 */             this.nSeg += 2;
/* 199 */             ii += 2;
/* 200 */             iNewStart = iEnd2 + 1;
/*     */           }
/*     */           else {
/* 203 */             iEnd1 = ii;
/* 204 */             name1 = "ZOH";
/* 205 */             nnBoxes = 1;
/* 206 */             this.nSeg = 1;
/* 207 */             ii += 1;
/* 208 */             iNewStart = iEnd1 + 1;
/*     */           }
/* 210 */           lastConv = "ZOH";
/* 211 */           nInSeg = 0;
/*     */         }
/*     */       }
/*     */       else {
/* 215 */         if (thisName.equals("AtoD")) { throw new IllegalArgumentException("ADC without preceeding ZOH");
/*     */         }
/*     */         
/* 218 */         if (thisName.equals("DtoA")) {
/* 219 */           if (lastConv.equals("DtoA")) throw new IllegalArgumentException("Two consecutive DACs with no intervening ADC");
/* 220 */           if (lastConv.equals("ZOH")) throw new IllegalArgumentException("ZOH followed by DAC");
/* 221 */           if (!lastConv.equals(" ")) this.checkNoMix = false;
/* 222 */           if (nInSeg > 1) {
/* 223 */             iEnd1 = ii - 1;
/* 224 */             name1 = "digital";
/* 225 */             iStart2 = 2;
/* 226 */             iEnd2 = ii;
/* 227 */             ii += 1;
/* 228 */             iNewStart = iEnd1 + 1;
/* 229 */             nnBoxes = 2;
/* 230 */             this.nSeg = 2;
/*     */           }
/*     */           else {
/* 233 */             iEnd1 = ii;
/* 234 */             name1 = "DtoA";
/* 235 */             ii += 1;
/* 236 */             iNewStart = iEnd1 + 1;
/* 237 */             nnBoxes = 1;
/* 238 */             this.nSeg = 1;
/*     */           }
/* 240 */           lastConv = "DtoA";
/* 241 */           nInSeg = 0;
/*     */         }
/*     */       }
/*     */       
/*     */ 
/*     */ 
/* 247 */       if (nnBoxes > 0) {
/* 248 */         this.segments.add(new Integer(iStart1));
/* 249 */         this.segments.add(new Integer(iEnd1));
/* 250 */         this.segments.add(name1);
/* 251 */         BlackBox dd = combineSegment(iStart1, iEnd1);
/* 252 */         this.segments.add(dd);
/* 253 */         if (nnBoxes == 2) {
/* 254 */           this.segments.add(new Integer(iStart2));
/* 255 */           this.segments.add(new Integer(iEnd2));
/* 256 */           this.segments.add(name2);
/* 257 */           BlackBox ee = combineSegment(iStart2, iEnd2);
/* 258 */           this.segments.add(ee);
/*     */         }
/* 260 */         iStart1 = iNewStart;
/*     */       }
/*     */       else {
/* 263 */         ii++;
/*     */       }
/* 265 */       if ((ii >= this.nBoxes) && (ii != iNewStart)) {
/* 266 */         iEnd1 = ii - 1;
/* 267 */         name1 = "analogue";
/* 268 */         if (lastConv.equals("AtoD")) name1 = "digital";
/* 269 */         this.nSeg = 1;
/* 270 */         this.segments.add(new Integer(iStart1));
/* 271 */         this.segments.add(new Integer(iEnd1));
/* 272 */         this.segments.add(name1);
/* 273 */         BlackBox ff = combineSegment(iStart1, iEnd1);
/* 274 */         this.segments.add(ff);
/*     */       }
/*     */     }
/*     */   }
/*     */   
/*     */ 
/*     */   public BlackBox combineSegment(int iLow, int iHigh)
/*     */   {
/* 282 */     BlackBox aa = new BlackBox();
/* 283 */     int nBoxSeg = iHigh - iLow + 1;
/* 284 */     int[] numDeg = new int[nBoxSeg];
/* 285 */     int[] denDeg = new int[nBoxSeg];
/* 286 */     BlackBox bb = (BlackBox)this.openPath.get(iLow);
/* 287 */     if (!bb.padeAdded) bb.transferPolesZeros();
/* 288 */     aa.sNumerPade = bb.sNumerPade.copy();
/* 289 */     aa.sDenomPade = bb.sDenomPade.copy();
/* 290 */     aa.deadTime = bb.deadTime;
/* 291 */     numDeg[0] = bb.sNumerDegPade;
/* 292 */     denDeg[0] = bb.sDenomDegPade;
/* 293 */     aa.sNumerDegPade = numDeg[0];
/* 294 */     aa.sDenomDegPade = denDeg[0];
/* 295 */     for (int i = 1; i < nBoxSeg; i++) {
/* 296 */       bb = (BlackBox)this.openPath.get(i + iLow);
/* 297 */       if (!bb.padeAdded) bb.transferPolesZeros();
/* 298 */       if (aa.sNumerPade == null) {
/* 299 */         if (bb.sNumerPade != null) {
/* 300 */           aa.sNumerPade = bb.sNumerPade.copy();
/*     */         }
/*     */         
/*     */       }
/* 304 */       else if (bb.sNumerPade != null) {
/* 305 */         aa.sNumerPade = bb.sNumerPade.times(aa.sNumerPade);
/*     */       }
/*     */       
/*     */ 
/* 309 */       if (aa.sDenomPade == null) {
/* 310 */         if (bb.sDenomPade != null) {
/* 311 */           aa.sDenomPade = bb.sDenomPade.copy();
/*     */         }
/*     */         
/*     */       }
/* 315 */       else if (bb.sDenomPade != null) {
/* 316 */         aa.sDenomPade = bb.sDenomPade.times(aa.sDenomPade);
/*     */       }
/*     */       
/*     */ 
/* 320 */       aa.deadTime += bb.deadTime;
/* 321 */       numDeg[i] = bb.sNumerDegPade;
/* 322 */       denDeg[i] = bb.sDenomDegPade;
/* 323 */       aa.sNumerDegPade += numDeg[i];
/* 324 */       aa.sDenomDegPade += denDeg[i];
/*     */     }
/* 326 */     if (aa.sNumerDegPade > 0) {
/* 327 */       aa.sZerosPade = Complex.oneDarray(aa.sNumerDegPade);
/* 328 */       int numK = 0;
/* 329 */       int denK = 0;
/* 330 */       for (int i = 0; i < nBoxSeg; i++) {
/* 331 */         bb = (BlackBox)this.openPath.get(i + iLow);
/* 332 */         if (bb.sNumerDegPade > 0) {
/* 333 */           for (int j = 0; j < numDeg[i]; j++) {
/* 334 */             aa.sZerosPade[numK] = bb.sZerosPade[j].copy();
/* 335 */             numK++;
/*     */           }
/*     */         }
/*     */       }
/*     */     }
/*     */     
/* 341 */     if (aa.sNumerDegPade > 0) {
/* 342 */       aa.sPolesPade = Complex.oneDarray(aa.sDenomDegPade);
/* 343 */       int numK = 0;
/* 344 */       int denK = 0;
/* 345 */       for (int i = 0; i < nBoxSeg; i++) {
/* 346 */         bb = (BlackBox)this.openPath.get(i + iLow);
/* 347 */         if (bb.sNumerDegPade > 0) {
/* 348 */           for (int j = 0; j < denDeg[i]; j++) {
/* 349 */             aa.sPolesPade[denK] = bb.sPolesPade[j].copy();
/* 350 */             denK++;
/*     */           }
/*     */         }
/*     */       }
/*     */     }
/*     */     
/* 356 */     aa.zeroPoleCancellation();
/* 357 */     aa.padeAdded = true;
/* 358 */     aa.sNumerDeg = aa.sNumerDegPade;
/* 359 */     aa.sDenomDeg = aa.sDenomDegPade;
/* 360 */     aa.sNumer = aa.sNumerPade.copy();
/* 361 */     aa.sNumer = aa.sNumerPade.copy();
/* 362 */     aa.sZeros = Complex.copy(aa.sZerosPade);
/* 363 */     aa.sPoles = Complex.copy(aa.sPolesPade);
/* 364 */     return aa;
/*     */   }
/*     */   
/*     */ 
/*     */   public int getNumberOfBoxes()
/*     */   {
/* 370 */     if (!this.checkConsolidate) consolidate();
/* 371 */     return this.nBoxes;
/*     */   }
/*     */   
/*     */   public ArrayList<Object> getSegmentsArrayList()
/*     */   {
/* 376 */     if (!this.checkConsolidate) consolidate();
/* 377 */     return this.segments;
/*     */   }
/*     */   
/*     */   public Vector<Object> getSegmentsVector()
/*     */   {
/* 382 */     if (!this.checkConsolidate) consolidate();
/* 383 */     ArrayList<Object> seg = this.segments;
/* 384 */     Vector<Object> ret = null;
/* 385 */     if (seg != null) {
/* 386 */       int n = seg.size();
/* 387 */       ret = new Vector(n);
/* 388 */       for (int i = 0; i < n; i++) ret.addElement(seg.get(i));
/*     */     }
/* 390 */     return ret;
/*     */   }
/*     */   
/*     */   public int getNumberOfSegments()
/*     */   {
/* 395 */     if (!this.checkConsolidate) consolidate();
/* 396 */     return this.nSeg;
/*     */   }
/*     */   
/*     */   public String getNamesOfBoxes()
/*     */   {
/* 401 */     if (!this.checkConsolidate) consolidate();
/* 402 */     String names = "";
/* 403 */     for (int i = 0; i < this.nBoxes; i++) {
/* 404 */       BlackBox bb = (BlackBox)this.openPath.get(i);
/* 405 */       names = names + i + ": " + bb.getName() + "   ";
/*     */     }
/* 407 */     return names;
/*     */   }
/*     */   
/*     */ 
/*     */   public void removeAllBoxes()
/*     */   {
/* 413 */     if (!this.openPath.isEmpty()) {
/* 414 */       this.openPath.clear();
/*     */     }
/*     */     
/*     */ 
/* 418 */     if (!this.segments.isEmpty()) {
/* 419 */       this.segments.clear();
/*     */     }
/* 421 */     this.nSeg = 0;
/* 422 */     this.checkNoMix = true;
/* 423 */     this.checkPath = false;
/*     */   }
/*     */   
/*     */   public OpenLoop copy()
/*     */   {
/* 428 */     if (this == null) {
/* 429 */       return null;
/*     */     }
/*     */     
/* 432 */     OpenLoop bb = new OpenLoop();
/*     */     
/* 434 */     bb.nBoxes = this.nBoxes;
/* 435 */     bb.nSeg = this.nSeg;
/* 436 */     bb.checkPath = this.checkPath;
/* 437 */     bb.checkNoMix = this.checkNoMix;
/* 438 */     bb.checkConsolidate = this.checkConsolidate;
/* 439 */     if (this.openPath.size() == 0) {
/* 440 */       bb.openPath = new ArrayList();
/*     */     }
/*     */     else {
/* 443 */       for (int i = 0; i < this.openPath.size(); i++) bb.openPath.add(((BlackBox)this.openPath.get(i)).copy());
/*     */     }
/* 445 */     if (this.segments.size() == 0) {
/* 446 */       bb.segments = new ArrayList();
/*     */     }
/*     */     else {
/* 449 */       int j = 0;
/* 450 */       for (int i = 0; i < this.nSeg; i++) {
/* 451 */         Integer holdI1 = (Integer)this.segments.get(j);
/* 452 */         int ii = holdI1.intValue();
/* 453 */         bb.segments.add(new Integer(ii));
/* 454 */         j++;
/* 455 */         Integer holdI2 = (Integer)this.segments.get(j);
/* 456 */         ii = holdI2.intValue();
/* 457 */         bb.segments.add(new Integer(ii));
/* 458 */         j++;
/* 459 */         String holdS = (String)this.segments.get(j);
/* 460 */         bb.segments.add(holdS);
/* 461 */         j++;
/* 462 */         bb.segments.add(((BlackBox)this.segments.get(j)).copy());
/* 463 */         j++;
/*     */       }
/*     */     }
/*     */     
/* 467 */     bb.sampLen = this.sampLen;
/* 468 */     bb.inputT = ((double[])this.inputT.clone());
/* 469 */     bb.outputT = ((double[])this.outputT.clone());
/* 470 */     bb.time = ((double[])this.time.clone());
/* 471 */     bb.forgetFactor = this.forgetFactor;
/* 472 */     bb.deltaT = this.deltaT;
/* 473 */     bb.sampFreq = this.sampFreq;
/* 474 */     bb.inputS = this.inputS.copy();
/* 475 */     bb.outputS = this.outputS.copy();
/* 476 */     bb.sValue = this.sValue.copy();
/* 477 */     bb.zValue = this.zValue.copy();
/* 478 */     bb.sNumer = this.sNumer.copy();
/* 479 */     bb.sDenom = this.sDenom.copy();
/* 480 */     bb.zNumer = this.zNumer.copy();
/* 481 */     bb.zDenom = this.zDenom.copy();
/* 482 */     bb.sPoles = Complex.copy(this.sPoles);
/* 483 */     bb.sZeros = Complex.copy(this.sZeros);
/* 484 */     bb.zPoles = Complex.copy(this.zPoles);
/* 485 */     bb.zZeros = Complex.copy(this.zZeros);
/* 486 */     bb.sNumerDeg = this.sNumerDeg;
/* 487 */     bb.sDenomDeg = this.sDenomDeg;
/* 488 */     bb.zNumerDeg = this.zNumerDeg;
/* 489 */     bb.zDenomDeg = this.zDenomDeg;
/* 490 */     bb.deadTime = this.deadTime;
/* 491 */     bb.orderPade = this.orderPade;
/* 492 */     bb.sNumerPade = this.sNumerPade.copy();
/* 493 */     bb.sDenomPade = this.sDenomPade.copy();
/* 494 */     bb.sPolesPade = Complex.copy(this.sPolesPade);
/* 495 */     bb.sZerosPade = Complex.copy(this.sZerosPade);
/* 496 */     bb.sNumerDegPade = this.sNumerDegPade;
/* 497 */     bb.sDenomDegPade = this.sDenomDegPade;
/* 498 */     bb.maptozero = this.maptozero;
/* 499 */     bb.padeAdded = this.padeAdded;
/* 500 */     bb.integrationSum = this.integrationSum;
/* 501 */     bb.integMethod = this.integMethod;
/* 502 */     bb.ztransMethod = this.ztransMethod;
/* 503 */     bb.name = this.name;
/* 504 */     bb.fixedName = this.fixedName;
/* 505 */     bb.nPlotPoints = this.nPlotPoints;
/*     */     
/* 507 */     return bb;
/*     */   }
/*     */   
/*     */ 
/*     */   public Object clone()
/*     */   {
/* 513 */     Object ret = null;
/*     */     
/* 515 */     if (this != null) {
/* 516 */       OpenLoop bb = new OpenLoop();
/*     */       
/* 518 */       bb.nBoxes = this.nBoxes;
/* 519 */       bb.nSeg = this.nSeg;
/* 520 */       bb.checkPath = this.checkPath;
/* 521 */       bb.checkNoMix = this.checkNoMix;
/* 522 */       bb.checkConsolidate = this.checkConsolidate;
/* 523 */       if (this.openPath.size() == 0) {
/* 524 */         bb.openPath = new ArrayList();
/*     */       }
/*     */       else {
/* 527 */         for (int i = 0; i < this.openPath.size(); i++) bb.openPath.add(((BlackBox)this.openPath.get(i)).copy());
/*     */       }
/* 529 */       if (this.segments.size() == 0) {
/* 530 */         bb.segments = new ArrayList();
/*     */       }
/*     */       else {
/* 533 */         int j = 0;
/* 534 */         for (int i = 0; i < this.nSeg; i++) {
/* 535 */           Integer holdI1 = (Integer)this.segments.get(j);
/* 536 */           int ii = holdI1.intValue();
/* 537 */           bb.segments.add(new Integer(ii));
/* 538 */           j++;
/* 539 */           Integer holdI2 = (Integer)this.segments.get(j);
/* 540 */           ii = holdI2.intValue();
/* 541 */           bb.segments.add(new Integer(ii));
/* 542 */           j++;
/* 543 */           String holdS = (String)this.segments.get(j);
/* 544 */           bb.segments.add(holdS);
/* 545 */           j++;
/* 546 */           bb.segments.add(((BlackBox)this.segments.get(j)).copy());
/* 547 */           j++;
/*     */         }
/*     */       }
/* 550 */       bb.sampLen = this.sampLen;
/* 551 */       bb.inputT = ((double[])this.inputT.clone());
/* 552 */       bb.outputT = ((double[])this.outputT.clone());
/* 553 */       bb.time = ((double[])this.time.clone());
/* 554 */       bb.forgetFactor = this.forgetFactor;
/* 555 */       bb.deltaT = this.deltaT;
/* 556 */       bb.sampFreq = this.sampFreq;
/* 557 */       bb.inputS = this.inputS.copy();
/* 558 */       bb.outputS = this.outputS.copy();
/* 559 */       bb.sValue = this.sValue.copy();
/* 560 */       bb.zValue = this.zValue.copy();
/* 561 */       bb.sNumer = this.sNumer.copy();
/* 562 */       bb.sDenom = this.sDenom.copy();
/* 563 */       bb.zNumer = this.zNumer.copy();
/* 564 */       bb.zDenom = this.zDenom.copy();
/* 565 */       bb.sPoles = Complex.copy(this.sPoles);
/* 566 */       bb.sZeros = Complex.copy(this.sZeros);
/* 567 */       bb.zPoles = Complex.copy(this.zPoles);
/* 568 */       bb.zZeros = Complex.copy(this.zZeros);
/* 569 */       bb.sNumerDeg = this.sNumerDeg;
/* 570 */       bb.sDenomDeg = this.sDenomDeg;
/* 571 */       bb.zNumerDeg = this.zNumerDeg;
/* 572 */       bb.zDenomDeg = this.zDenomDeg;
/* 573 */       bb.deadTime = this.deadTime;
/* 574 */       bb.orderPade = this.orderPade;
/* 575 */       bb.sNumerPade = this.sNumerPade.copy();
/* 576 */       bb.sDenomPade = this.sDenomPade.copy();
/* 577 */       bb.sPolesPade = Complex.copy(this.sPolesPade);
/* 578 */       bb.sZerosPade = Complex.copy(this.sZerosPade);
/* 579 */       bb.sNumerDegPade = this.sNumerDegPade;
/* 580 */       bb.sDenomDegPade = this.sDenomDegPade;
/* 581 */       bb.maptozero = this.maptozero;
/* 582 */       bb.padeAdded = this.padeAdded;
/* 583 */       bb.integrationSum = this.integrationSum;
/* 584 */       bb.integMethod = this.integMethod;
/* 585 */       bb.ztransMethod = this.ztransMethod;
/* 586 */       bb.name = this.name;
/* 587 */       bb.fixedName = this.fixedName;
/* 588 */       bb.nPlotPoints = this.nPlotPoints;
/*     */       
/* 590 */       ret = bb;
/*     */     }
/* 592 */     return ret;
/*     */   }
/*     */ }


/* Location:              /home/menick/Scrivania/ProcessMining/CNMining/CNMining.jar!/flanagan/control/OpenLoop.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       0.7.1
 */