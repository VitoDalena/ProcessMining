/*      */ package flanagan.math;
/*      */ 
/*      */ import flanagan.io.FileOutput;
/*      */ import java.io.PrintStream;
/*      */ import java.util.ArrayList;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ public class Minimisation
/*      */ {
/*   45 */   protected boolean iseOption = true;
/*      */   
/*   47 */   protected int nParam = 0;
/*   48 */   protected double[] paramValue = null;
/*   49 */   protected String[] paraName = null;
/*   50 */   protected double functValue = 0.0D;
/*   51 */   protected double lastFunctValNoCnstrnt = 0.0D;
/*   52 */   protected double minimum = 0.0D;
/*   53 */   protected int prec = 4;
/*   54 */   protected int field = 13;
/*   55 */   protected boolean convStatus = false;
/*      */   
/*      */ 
/*   58 */   protected boolean suppressNoConvergenceMessage = false;
/*   59 */   protected int scaleOpt = 0;
/*      */   
/*      */ 
/*      */ 
/*   63 */   protected double[] scale = null;
/*   64 */   protected boolean penalty = false;
/*   65 */   protected boolean sumPenalty = false;
/*   66 */   protected int nConstraints = 0;
/*   67 */   protected int nSumConstraints = 0;
/*   68 */   protected int maxConstraintIndex = -1;
/*   69 */   protected ArrayList<Object> penalties = new ArrayList();
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*   75 */   protected ArrayList<Object> sumPenalties = new ArrayList();
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*   83 */   protected int[] penaltyCheck = null;
/*      */   
/*   85 */   protected int[] sumPenaltyCheck = null;
/*      */   
/*   87 */   protected double penaltyWeight = 1.0E30D;
/*   88 */   protected int[] penaltyParam = null;
/*   89 */   protected int[][] sumPenaltyParam = (int[][])null;
/*   90 */   protected double[][] sumPlusOrMinus = (double[][])null;
/*   91 */   protected int[] sumPenaltyNumber = null;
/*      */   
/*   93 */   protected double[] constraints = null;
/*   94 */   protected double constraintTolerance = 1.0E-4D;
/*   95 */   protected double[] sumConstraints = null;
/*   96 */   protected int constraintMethod = 0;
/*      */   
/*   98 */   protected int nMax = 3000;
/*   99 */   protected int nIter = 0;
/*  100 */   protected int konvge = 3;
/*  101 */   protected int kRestart = 0;
/*  102 */   protected double fTol = 1.0E-13D;
/*  103 */   protected double rCoeff = 1.0D;
/*  104 */   protected double eCoeff = 2.0D;
/*  105 */   protected double cCoeff = 0.5D;
/*  106 */   protected double[] startH = null;
/*  107 */   protected double[] step = null;
/*  108 */   protected double dStep = 0.5D;
/*  109 */   protected int minTest = 0;
/*      */   
/*      */ 
/*  112 */   protected double simplexSd = 0.0D;
/*      */   
/*      */ 
/*      */   public Minimisation()
/*      */   {
/*  117 */     this.iseOption = true;
/*      */   }
/*      */   
/*      */   public void suppressNoConvergenceMessage()
/*      */   {
/*  122 */     this.suppressNoConvergenceMessage = true;
/*      */   }
/*      */   
/*      */   public void supressNoConvergenceMessage()
/*      */   {
/*  127 */     this.suppressNoConvergenceMessage = true;
/*      */   }
/*      */   
/*      */   public void nelderMead(MinimisationFunction gg, double[] start, double[] step, double fTol, int nMax)
/*      */   {
/*  132 */     Object g = gg;
/*  133 */     nelderMead(g, start, step, fTol, nMax);
/*      */   }
/*      */   
/*      */   public void nelderMead(MinimizationFunction gg, double[] start, double[] step, double fTol, int nMax)
/*      */   {
/*  138 */     Object g = gg;
/*  139 */     nelderMead(g, start, step, fTol, nMax);
/*      */   }
/*      */   
/*      */ 
/*      */   public void nelderMead(Object g, double[] start, double[] step, double fTol, int nMax)
/*      */   {
/*  145 */     boolean testContract = false;
/*  146 */     int np = start.length;
/*  147 */     if (this.maxConstraintIndex >= np) throw new IllegalArgumentException("You have entered more constrained parameters (" + this.maxConstraintIndex + ") than minimisation parameters (" + np + ")");
/*  148 */     this.nParam = np;
/*  149 */     this.convStatus = true;
/*  150 */     int nnp = np + 1;
/*  151 */     this.lastFunctValNoCnstrnt = 0.0D;
/*      */     
/*  153 */     if (this.scaleOpt < 2) this.scale = new double[np];
/*  154 */     if ((this.scaleOpt == 2) && (this.scale.length != start.length)) throw new IllegalArgumentException("scale array and initial estimate array are of different lengths");
/*  155 */     if (step.length != start.length) { throw new IllegalArgumentException("step array length " + step.length + " and initial estimate array length " + start.length + " are of different");
/*      */     }
/*      */     
/*  158 */     for (int i = 0; i < np; i++) { if (step[i] == 0.0D) { throw new IllegalArgumentException("step " + i + " size is zero");
/*      */       }
/*      */     }
/*  161 */     this.paramValue = new double[np];
/*  162 */     this.startH = new double[np];
/*  163 */     this.step = new double[np];
/*  164 */     double[] pmin = new double[np];
/*      */     
/*  166 */     double[][] pp = new double[nnp][nnp];
/*  167 */     double[] yy = new double[nnp];
/*  168 */     double[] pbar = new double[nnp];
/*  169 */     double[] pstar = new double[nnp];
/*  170 */     double[] p2star = new double[nnp];
/*      */     
/*      */ 
/*  173 */     if (this.penalty) {
/*  174 */       Integer itemp = (Integer)this.penalties.get(1);
/*  175 */       this.nConstraints = itemp.intValue();
/*  176 */       this.penaltyParam = new int[this.nConstraints];
/*  177 */       this.penaltyCheck = new int[this.nConstraints];
/*  178 */       this.constraints = new double[this.nConstraints];
/*  179 */       Double dtemp = null;
/*  180 */       int j = 2;
/*  181 */       for (int i = 0; i < this.nConstraints; i++) {
/*  182 */         itemp = (Integer)this.penalties.get(j);
/*  183 */         this.penaltyParam[i] = itemp.intValue();
/*  184 */         j++;
/*  185 */         itemp = (Integer)this.penalties.get(j);
/*  186 */         this.penaltyCheck[i] = itemp.intValue();
/*  187 */         j++;
/*  188 */         dtemp = (Double)this.penalties.get(j);
/*  189 */         this.constraints[i] = dtemp.doubleValue();
/*  190 */         j++;
/*      */       }
/*      */     }
/*      */     
/*      */ 
/*  195 */     if (this.sumPenalty) {
/*  196 */       Integer itemp = (Integer)this.sumPenalties.get(1);
/*  197 */       this.nSumConstraints = itemp.intValue();
/*  198 */       this.sumPenaltyParam = new int[this.nSumConstraints][];
/*  199 */       this.sumPlusOrMinus = new double[this.nSumConstraints][];
/*  200 */       this.sumPenaltyCheck = new int[this.nSumConstraints];
/*  201 */       this.sumPenaltyNumber = new int[this.nSumConstraints];
/*  202 */       this.sumConstraints = new double[this.nSumConstraints];
/*  203 */       int[] itempArray = null;
/*  204 */       double[] dtempArray = null;
/*  205 */       Double dtemp = null;
/*  206 */       int j = 2;
/*  207 */       for (int i = 0; i < this.nSumConstraints; i++) {
/*  208 */         itemp = (Integer)this.sumPenalties.get(j);
/*  209 */         this.sumPenaltyNumber[i] = itemp.intValue();
/*  210 */         j++;
/*  211 */         itempArray = (int[])this.sumPenalties.get(j);
/*  212 */         this.sumPenaltyParam[i] = itempArray;
/*  213 */         j++;
/*  214 */         dtempArray = (double[])this.sumPenalties.get(j);
/*  215 */         this.sumPlusOrMinus[i] = dtempArray;
/*  216 */         j++;
/*  217 */         itemp = (Integer)this.sumPenalties.get(j);
/*  218 */         this.sumPenaltyCheck[i] = itemp.intValue();
/*  219 */         j++;
/*  220 */         dtemp = (Double)this.sumPenalties.get(j);
/*  221 */         this.sumConstraints[i] = dtemp.doubleValue();
/*  222 */         j++;
/*      */       }
/*      */     }
/*      */     
/*      */ 
/*  227 */     for (int i = 0; i < np; i++) { this.startH[i] = start[i];
/*      */     }
/*      */     
/*  230 */     if (this.scaleOpt > 0) {
/*  231 */       boolean testzero = false;
/*  232 */       for (int i = 0; i < np; i++) if (start[i] == 0.0D) testzero = true;
/*  233 */       if (testzero) {
/*  234 */         System.out.println("Neler and Mead Simplex: a start value of zero precludes scaling");
/*  235 */         System.out.println("Regression performed without scaling");
/*  236 */         this.scaleOpt = 0;
/*      */       }
/*      */     }
/*  239 */     switch (this.scaleOpt) {
/*  240 */     case 0:  for (int i = 0; i < np; i++) this.scale[i] = 1.0D;
/*  241 */       break;
/*  242 */     case 1:  for (int i = 0; i < np; i++) {
/*  243 */         this.scale[i] = (1.0D / start[i]);
/*  244 */         step[i] /= start[i];
/*  245 */         start[i] = 1.0D;
/*      */       }
/*  247 */       break;
/*  248 */     case 2:  for (int i = 0; i < np; i++) {
/*  249 */         step[i] *= this.scale[i];
/*  250 */         start[i] *= this.scale[i];
/*      */       }
/*      */     }
/*      */     
/*      */     
/*      */ 
/*  256 */     this.fTol = fTol;
/*  257 */     this.nMax = nMax;
/*  258 */     this.nIter = 0;
/*  259 */     for (int i = 0; i < np; i++) {
/*  260 */       this.step[i] = step[i];
/*  261 */       this.scale[i] = this.scale[i];
/*      */     }
/*      */     
/*      */ 
/*  265 */     double sho = 0.0D;
/*  266 */     for (int i = 0; i < np; i++) {
/*  267 */       sho = start[i];
/*  268 */       pstar[i] = sho;
/*  269 */       p2star[i] = sho;
/*  270 */       pmin[i] = sho;
/*      */     }
/*      */     
/*  273 */     int jcount = this.konvge;
/*      */     
/*  275 */     for (int i = 0; i < np; i++) {
/*  276 */       pp[i][(nnp - 1)] = start[i];
/*      */     }
/*  278 */     yy[(nnp - 1)] = functionValue(g, start);
/*  279 */     for (int j = 0; j < np; j++) {
/*  280 */       start[j] += step[j];
/*      */       
/*  282 */       for (int i = 0; i < np; i++) pp[i][j] = start[i];
/*  283 */       yy[j] = functionValue(g, start);
/*  284 */       start[j] -= step[j];
/*      */     }
/*      */     
/*      */ 
/*  288 */     double ynewlo = 0.0D;
/*  289 */     double ystar = 0.0D;
/*  290 */     double y2star = 0.0D;
/*  291 */     double ylo = 0.0D;
/*      */     
/*      */ 
/*  294 */     double curMin = 0.0D;double sumnm = 0.0D;double summnm = 0.0D;double zn = 0.0D;
/*  295 */     int ilo = 0;
/*  296 */     int ihi = 0;
/*  297 */     int ln = 0;
/*  298 */     boolean test = true;
/*      */     
/*  300 */     while (test)
/*      */     {
/*  302 */       ylo = yy[0];
/*  303 */       ynewlo = ylo;
/*  304 */       ilo = 0;
/*  305 */       ihi = 0;
/*  306 */       for (int i = 1; i < nnp; i++) {
/*  307 */         if (yy[i] < ylo) {
/*  308 */           ylo = yy[i];
/*  309 */           ilo = i;
/*      */         }
/*  311 */         if (yy[i] > ynewlo) {
/*  312 */           ynewlo = yy[i];
/*  313 */           ihi = i;
/*      */         }
/*      */       }
/*      */       
/*  317 */       for (int i = 0; i < np; i++) {
/*  318 */         zn = 0.0D;
/*  319 */         for (int j = 0; j < nnp; j++) {
/*  320 */           zn += pp[i][j];
/*      */         }
/*  322 */         zn -= pp[i][ihi];
/*  323 */         pbar[i] = (zn / np);
/*      */       }
/*      */       
/*      */ 
/*  327 */       for (int i = 0; i < np; i++) { pstar[i] = ((1.0D + this.rCoeff) * pbar[i] - this.rCoeff * pp[i][ihi]);
/*      */       }
/*      */       
/*  330 */       ystar = functionValue(g, pstar);
/*      */       
/*  332 */       this.nIter += 1;
/*      */       
/*      */ 
/*  335 */       if (ystar < ylo)
/*      */       {
/*  337 */         for (int i = 0; i < np; i++) { p2star[i] = (pstar[i] * (1.0D + this.eCoeff) - this.eCoeff * pbar[i]);
/*      */         }
/*  339 */         y2star = functionValue(g, p2star);
/*  340 */         this.nIter += 1;
/*  341 */         if (y2star < ylo)
/*      */         {
/*  343 */           for (int i = 0; i < np; i++) pp[i][ihi] = p2star[i];
/*  344 */           yy[ihi] = y2star;
/*      */         }
/*      */         else
/*      */         {
/*  348 */           for (int i = 0; i < np; i++) pp[i][ihi] = pstar[i];
/*  349 */           yy[ihi] = ystar;
/*      */         }
/*      */       }
/*      */       else
/*      */       {
/*  354 */         ln = 0;
/*  355 */         for (int i = 0; i < nnp; i++) if ((i != ihi) && (ystar > yy[i])) ln++;
/*  356 */         if (ln == np)
/*      */         {
/*  358 */           if (ystar <= yy[ihi])
/*      */           {
/*  360 */             for (int i = 0; i < np; i++) pp[i][ihi] = pstar[i];
/*  361 */             yy[ihi] = ystar;
/*      */           }
/*      */           
/*  364 */           for (int i = 0; i < np; i++) { p2star[i] = (this.cCoeff * pp[i][ihi] + (1.0D - this.cCoeff) * pbar[i]);
/*      */           }
/*  366 */           y2star = functionValue(g, p2star);
/*  367 */           this.nIter += 1;
/*      */           
/*  369 */           if (y2star > yy[ihi])
/*      */           {
/*      */ 
/*  372 */             for (int j = 0; j < nnp; j++) {
/*  373 */               for (int i = 0; i < np; i++) {
/*  374 */                 pp[i][j] = (0.5D * (pp[i][j] + pp[i][ilo]));
/*  375 */                 pmin[i] = pp[i][j];
/*      */               }
/*  377 */               yy[j] = functionValue(g, pmin);
/*      */             }
/*  379 */             this.nIter += nnp;
/*      */           }
/*      */           else
/*      */           {
/*  383 */             for (int i = 0; i < np; i++) pp[i][ihi] = p2star[i];
/*  384 */             yy[ihi] = y2star;
/*      */           }
/*      */         }
/*      */         else
/*      */         {
/*  389 */           for (int i = 0; i < np; i++) pp[i][ihi] = pstar[i];
/*  390 */           yy[ihi] = ystar;
/*      */         }
/*      */       }
/*      */       
/*      */ 
/*      */ 
/*  396 */       sumnm = 0.0D;
/*  397 */       ynewlo = yy[0];
/*  398 */       ilo = 0;
/*  399 */       for (int i = 0; i < nnp; i++) {
/*  400 */         sumnm += yy[i];
/*  401 */         if (ynewlo > yy[i]) {
/*  402 */           ynewlo = yy[i];
/*  403 */           ilo = i;
/*      */         }
/*      */       }
/*  406 */       sumnm /= nnp;
/*  407 */       summnm = 0.0D;
/*  408 */       for (int i = 0; i < nnp; i++) {
/*  409 */         zn = yy[i] - sumnm;
/*  410 */         summnm += zn * zn;
/*      */       }
/*  412 */       curMin = Math.sqrt(summnm / np);
/*      */       
/*      */ 
/*  415 */       switch (this.minTest) {
/*      */       case 0: 
/*  417 */         if (curMin < fTol) test = false;
/*      */         break;
/*      */       }
/*  420 */       this.minimum = ynewlo;
/*  421 */       if (!test)
/*      */       {
/*  423 */         for (int i = 0; i < np; i++) pmin[i] = pp[i][ilo];
/*  424 */         yy[(nnp - 1)] = ynewlo;
/*      */         
/*  426 */         this.simplexSd = curMin;
/*      */         
/*  428 */         jcount--;
/*  429 */         if (jcount > 0) {
/*  430 */           test = true;
/*  431 */           for (int j = 0; j < np; j++) {
/*  432 */             pmin[j] += step[j];
/*  433 */             for (int i = 0; i < np; i++) pp[i][j] = pmin[i];
/*  434 */             yy[j] = functionValue(g, pmin);
/*  435 */             pmin[j] -= step[j];
/*      */           }
/*      */         }
/*      */       }
/*      */       
/*  440 */       if ((test) && (this.nIter > this.nMax)) {
/*  441 */         if (!this.suppressNoConvergenceMessage) {
/*  442 */           System.out.println("Maximum iteration number reached, in Minimisation.simplex(...)");
/*  443 */           System.out.println("without the convergence criterion being satisfied");
/*  444 */           System.out.println("Current parameter estimates and function value returned");
/*      */         }
/*  446 */         this.convStatus = false;
/*      */         
/*  448 */         for (int i = 0; i < np; i++) pmin[i] = pp[i][ilo];
/*  449 */         yy[(nnp - 1)] = ynewlo;
/*  450 */         test = false;
/*      */       }
/*      */     }
/*      */     
/*  454 */     for (int i = 0; i < np; i++) {
/*  455 */       pmin[i] = pp[i][ilo];
/*  456 */       this.paramValue[i] = (pmin[i] / this.scale[i]);
/*      */     }
/*  458 */     this.minimum = ynewlo;
/*  459 */     this.kRestart = (this.konvge - jcount);
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */   public void nelderMead(MinimisationFunction g, double[] start, double[] step, double fTol)
/*      */   {
/*  466 */     int nMaxx = this.nMax;
/*  467 */     nelderMead(g, start, step, fTol, nMaxx);
/*      */   }
/*      */   
/*      */   public void nelderMead(MinimizationFunction g, double[] start, double[] step, double fTol) {
/*  471 */     int nMaxx = this.nMax;
/*  472 */     nelderMead(g, start, step, fTol, nMaxx);
/*      */   }
/*      */   
/*      */ 
/*      */   public void nelderMead(MinimisationFunction g, double[] start, double[] step, int nMax)
/*      */   {
/*  478 */     double fToll = this.fTol;
/*  479 */     nelderMead(g, start, step, fToll, nMax);
/*      */   }
/*      */   
/*      */   public void nelderMead(MinimizationFunction g, double[] start, double[] step, int nMax) {
/*  483 */     double fToll = this.fTol;
/*  484 */     nelderMead(g, start, step, fToll, nMax);
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */   public void nelderMead(MinimisationFunction g, double[] start, double[] step)
/*      */   {
/*  491 */     double fToll = this.fTol;
/*  492 */     int nMaxx = this.nMax;
/*  493 */     nelderMead(g, start, step, fToll, nMaxx);
/*      */   }
/*      */   
/*      */   public void nelderMead(MinimizationFunction g, double[] start, double[] step) {
/*  497 */     double fToll = this.fTol;
/*  498 */     int nMaxx = this.nMax;
/*  499 */     nelderMead(g, start, step, fToll, nMaxx);
/*      */   }
/*      */   
/*      */ 
/*      */   public void nelderMead(MinimisationFunction g, double[] start, double fTol, int nMax)
/*      */   {
/*  505 */     int n = start.length;
/*  506 */     double[] stepp = new double[n];
/*  507 */     for (int i = 0; i < n; i++) stepp[i] = (this.dStep * start[i]);
/*  508 */     nelderMead(g, start, stepp, fTol, nMax);
/*      */   }
/*      */   
/*      */   public void nelderMead(MinimizationFunction g, double[] start, double fTol, int nMax) {
/*  512 */     int n = start.length;
/*  513 */     double[] stepp = new double[n];
/*  514 */     for (int i = 0; i < n; i++) stepp[i] = (this.dStep * start[i]);
/*  515 */     nelderMead(g, start, stepp, fTol, nMax);
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */   public void nelderMead(MinimisationFunction g, double[] start, double fTol)
/*      */   {
/*  522 */     int n = start.length;
/*  523 */     int nMaxx = this.nMax;
/*  524 */     double[] stepp = new double[n];
/*  525 */     for (int i = 0; i < n; i++) stepp[i] = (this.dStep * start[i]);
/*  526 */     nelderMead(g, start, stepp, fTol, nMaxx);
/*      */   }
/*      */   
/*      */   public void nelderMead(MinimizationFunction g, double[] start, double fTol) {
/*  530 */     int n = start.length;
/*  531 */     int nMaxx = this.nMax;
/*  532 */     double[] stepp = new double[n];
/*  533 */     for (int i = 0; i < n; i++) stepp[i] = (this.dStep * start[i]);
/*  534 */     nelderMead(g, start, stepp, fTol, nMaxx);
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */   public void nelderMead(MinimisationFunction g, double[] start, int nMax)
/*      */   {
/*  541 */     int n = start.length;
/*  542 */     double fToll = this.fTol;
/*  543 */     double[] stepp = new double[n];
/*  544 */     for (int i = 0; i < n; i++) stepp[i] = (this.dStep * start[i]);
/*  545 */     nelderMead(g, start, stepp, fToll, nMax);
/*      */   }
/*      */   
/*      */   public void nelderMead(MinimizationFunction g, double[] start, int nMax) {
/*  549 */     int n = start.length;
/*  550 */     double fToll = this.fTol;
/*  551 */     double[] stepp = new double[n];
/*  552 */     for (int i = 0; i < n; i++) stepp[i] = (this.dStep * start[i]);
/*  553 */     nelderMead(g, start, stepp, fToll, nMax);
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */   public void nelderMead(MinimisationFunction g, double[] start)
/*      */   {
/*  561 */     int n = start.length;
/*  562 */     int nMaxx = this.nMax;
/*  563 */     double fToll = this.fTol;
/*  564 */     double[] stepp = new double[n];
/*  565 */     for (int i = 0; i < n; i++) stepp[i] = (this.dStep * start[i]);
/*  566 */     nelderMead(g, start, stepp, fToll, nMaxx);
/*      */   }
/*      */   
/*      */   public void nelderMead(MinimizationFunction g, double[] start) {
/*  570 */     int n = start.length;
/*  571 */     int nMaxx = this.nMax;
/*  572 */     double fToll = this.fTol;
/*  573 */     double[] stepp = new double[n];
/*  574 */     for (int i = 0; i < n; i++) stepp[i] = (this.dStep * start[i]);
/*  575 */     nelderMead(g, start, stepp, fToll, nMaxx);
/*      */   }
/*      */   
/*      */ 
/*      */   protected double functionValue(Object g, double[] x)
/*      */   {
/*  581 */     if (this.iseOption) {
/*  582 */       return functionValue((MinimisationFunction)g, x);
/*      */     }
/*      */     
/*  585 */     return functionValue((MinimizationFunction)g, x);
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */   protected double functionValue(MinimisationFunction g, double[] x)
/*      */   {
/*  592 */     double funcVal = -3.0D;
/*  593 */     double[] param = new double[this.nParam];
/*      */     
/*  595 */     for (int i = 0; i < this.nParam; i++) { x[i] /= this.scale[i];
/*      */     }
/*      */     
/*  598 */     double tempFunctVal = this.lastFunctValNoCnstrnt;
/*  599 */     boolean test = true;
/*  600 */     if (this.penalty) {
/*  601 */       int k = 0;
/*  602 */       for (int i = 0; i < this.nConstraints; i++) {
/*  603 */         k = this.penaltyParam[i];
/*  604 */         switch (this.penaltyCheck[i]) {
/*  605 */         case -1:  if (param[k] < this.constraints[i]) {
/*  606 */             funcVal = tempFunctVal + this.penaltyWeight * Fmath.square(this.constraints[i] - param[k]);
/*  607 */             test = false; }
/*  608 */           break;
/*      */         case 0: 
/*  610 */           if (param[k] < this.constraints[i] * (1.0D - this.constraintTolerance)) {
/*  611 */             funcVal = tempFunctVal + this.penaltyWeight * Fmath.square(this.constraints[i] * (1.0D - this.constraintTolerance) - param[k]);
/*  612 */             test = false;
/*      */           }
/*  614 */           if (param[k] > this.constraints[i] * (1.0D + this.constraintTolerance)) {
/*  615 */             funcVal = tempFunctVal + this.penaltyWeight * Fmath.square(param[k] - this.constraints[i] * (1.0D + this.constraintTolerance));
/*  616 */             test = false; }
/*  617 */           break;
/*      */         case 1: 
/*  619 */           if (param[k] > this.constraints[i]) {
/*  620 */             funcVal = tempFunctVal + this.penaltyWeight * Fmath.square(param[k] - this.constraints[i]);
/*  621 */             test = false;
/*      */           }
/*      */           
/*      */           break;
/*      */         }
/*      */         
/*      */       }
/*      */     }
/*  629 */     if (this.sumPenalty) {
/*  630 */       int kk = 0;
/*  631 */       double pSign = 0.0D;
/*  632 */       double sumPenaltySum = 0.0D;
/*  633 */       for (int i = 0; i < this.nSumConstraints; i++) {
/*  634 */         for (int j = 0; j < this.sumPenaltyNumber[i]; j++) {
/*  635 */           kk = this.sumPenaltyParam[i][j];
/*  636 */           pSign = this.sumPlusOrMinus[i][j];
/*  637 */           sumPenaltySum += param[kk] * pSign;
/*      */         }
/*  639 */         switch (this.sumPenaltyCheck[i]) {
/*  640 */         case -1:  if (sumPenaltySum < this.sumConstraints[i]) {
/*  641 */             funcVal = tempFunctVal + this.penaltyWeight * Fmath.square(this.sumConstraints[i] - sumPenaltySum);
/*  642 */             test = false; }
/*  643 */           break;
/*      */         case 0: 
/*  645 */           if (sumPenaltySum < this.sumConstraints[i] * (1.0D - this.constraintTolerance)) {
/*  646 */             funcVal = tempFunctVal + this.penaltyWeight * Fmath.square(this.sumConstraints[i] * (1.0D - this.constraintTolerance) - sumPenaltySum);
/*  647 */             test = false;
/*      */           }
/*  649 */           if (sumPenaltySum > this.sumConstraints[i] * (1.0D + this.constraintTolerance)) {
/*  650 */             funcVal = tempFunctVal + this.penaltyWeight * Fmath.square(sumPenaltySum - this.sumConstraints[i] * (1.0D + this.constraintTolerance));
/*  651 */             test = false; }
/*  652 */           break;
/*      */         case 1: 
/*  654 */           if (sumPenaltySum > this.sumConstraints[i]) {
/*  655 */             funcVal = tempFunctVal + this.penaltyWeight * Fmath.square(sumPenaltySum - this.sumConstraints[i]);
/*  656 */             test = false;
/*      */           }
/*      */           break;
/*      */         }
/*      */         
/*      */       }
/*      */     }
/*  663 */     if (test) {
/*  664 */       funcVal = g.function(param);
/*  665 */       this.lastFunctValNoCnstrnt = funcVal;
/*      */     }
/*  667 */     return funcVal;
/*      */   }
/*      */   
/*      */ 
/*      */   protected double functionValue(MinimizationFunction g, double[] x)
/*      */   {
/*  673 */     double funcVal = -3.0D;
/*  674 */     double[] param = new double[this.nParam];
/*      */     
/*  676 */     for (int i = 0; i < this.nParam; i++) { x[i] /= this.scale[i];
/*      */     }
/*      */     
/*  679 */     double tempFunctVal = this.lastFunctValNoCnstrnt;
/*  680 */     boolean test = true;
/*  681 */     if (this.penalty) {
/*  682 */       int k = 0;
/*  683 */       for (int i = 0; i < this.nConstraints; i++) {
/*  684 */         k = this.penaltyParam[i];
/*  685 */         switch (this.penaltyCheck[i]) {
/*  686 */         case -1:  if (param[k] < this.constraints[i]) {
/*  687 */             funcVal = tempFunctVal + this.penaltyWeight * Fmath.square(this.constraints[i] - param[k]);
/*  688 */             test = false; }
/*  689 */           break;
/*      */         case 0: 
/*  691 */           if (param[k] < this.constraints[i] * (1.0D - this.constraintTolerance)) {
/*  692 */             funcVal = tempFunctVal + this.penaltyWeight * Fmath.square(this.constraints[i] * (1.0D - this.constraintTolerance) - param[k]);
/*  693 */             test = false;
/*      */           }
/*  695 */           if (param[k] > this.constraints[i] * (1.0D + this.constraintTolerance)) {
/*  696 */             funcVal = tempFunctVal + this.penaltyWeight * Fmath.square(param[k] - this.constraints[i] * (1.0D + this.constraintTolerance));
/*  697 */             test = false; }
/*  698 */           break;
/*      */         case 1: 
/*  700 */           if (param[k] > this.constraints[i]) {
/*  701 */             funcVal = tempFunctVal + this.penaltyWeight * Fmath.square(param[k] - this.constraints[i]);
/*  702 */             test = false;
/*      */           }
/*      */           
/*      */           break;
/*      */         }
/*      */         
/*      */       }
/*      */     }
/*  710 */     if (this.sumPenalty) {
/*  711 */       int kk = 0;
/*  712 */       double pSign = 0.0D;
/*  713 */       double sumPenaltySum = 0.0D;
/*  714 */       for (int i = 0; i < this.nSumConstraints; i++) {
/*  715 */         for (int j = 0; j < this.sumPenaltyNumber[i]; j++) {
/*  716 */           kk = this.sumPenaltyParam[i][j];
/*  717 */           pSign = this.sumPlusOrMinus[i][j];
/*  718 */           sumPenaltySum += param[kk] * pSign;
/*      */         }
/*  720 */         switch (this.sumPenaltyCheck[i]) {
/*  721 */         case -1:  if (sumPenaltySum < this.sumConstraints[i]) {
/*  722 */             funcVal = tempFunctVal + this.penaltyWeight * Fmath.square(this.sumConstraints[i] - sumPenaltySum);
/*  723 */             test = false; }
/*  724 */           break;
/*      */         case 0: 
/*  726 */           if (sumPenaltySum < this.sumConstraints[i] * (1.0D - this.constraintTolerance)) {
/*  727 */             funcVal = tempFunctVal + this.penaltyWeight * Fmath.square(this.sumConstraints[i] * (1.0D - this.constraintTolerance) - sumPenaltySum);
/*  728 */             test = false;
/*      */           }
/*  730 */           if (sumPenaltySum > this.sumConstraints[i] * (1.0D + this.constraintTolerance)) {
/*  731 */             funcVal = tempFunctVal + this.penaltyWeight * Fmath.square(sumPenaltySum - this.sumConstraints[i] * (1.0D + this.constraintTolerance));
/*  732 */             test = false; }
/*  733 */           break;
/*      */         case 1: 
/*  735 */           if (sumPenaltySum > this.sumConstraints[i]) {
/*  736 */             funcVal = tempFunctVal + this.penaltyWeight * Fmath.square(sumPenaltySum - this.sumConstraints[i]);
/*  737 */             test = false;
/*      */           }
/*      */           break;
/*      */         }
/*      */         
/*      */       }
/*      */     }
/*  744 */     if (test) {
/*  745 */       funcVal = g.function(param);
/*  746 */       this.lastFunctValNoCnstrnt = funcVal;
/*      */     }
/*  748 */     return funcVal;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */   public void addConstraint(int paramIndex, int conDir, double constraint)
/*      */   {
/*  755 */     this.penalty = true;
/*      */     
/*  757 */     if (this.penalties.isEmpty()) { this.penalties.add(new Integer(this.constraintMethod));
/*      */     }
/*      */     
/*  760 */     if (this.penalties.size() == 1) {
/*  761 */       this.penalties.add(new Integer(1));
/*      */     }
/*      */     else {
/*  764 */       int nPC = ((Integer)this.penalties.get(1)).intValue();
/*  765 */       nPC++;
/*  766 */       this.penalties.set(1, new Integer(nPC));
/*      */     }
/*  768 */     this.penalties.add(new Integer(paramIndex));
/*  769 */     this.penalties.add(new Integer(conDir));
/*  770 */     this.penalties.add(new Double(constraint));
/*  771 */     if (paramIndex > this.maxConstraintIndex) { this.maxConstraintIndex = paramIndex;
/*      */     }
/*      */   }
/*      */   
/*      */   public void addConstraint(int[] paramIndices, int[] plusOrMinus, int conDir, double constraint)
/*      */   {
/*  777 */     ArrayMaths am = new ArrayMaths(plusOrMinus);
/*  778 */     double[] dpom = am.getArray_as_double();
/*  779 */     addConstraint(paramIndices, dpom, conDir, constraint);
/*      */   }
/*      */   
/*      */   public void addConstraint(int[] paramIndices, double[] plusOrMinus, int conDir, double constraint)
/*      */   {
/*  784 */     int nCon = paramIndices.length;
/*  785 */     int nPorM = plusOrMinus.length;
/*  786 */     if (nCon != nPorM) throw new IllegalArgumentException("num of parameters, " + nCon + ", does not equal number of parameter signs, " + nPorM);
/*  787 */     this.sumPenalty = true;
/*      */     
/*  789 */     if (this.sumPenalties.isEmpty()) { this.sumPenalties.add(new Integer(this.constraintMethod));
/*      */     }
/*      */     
/*  792 */     if (this.sumPenalties.size() == 1) {
/*  793 */       this.sumPenalties.add(new Integer(1));
/*      */     }
/*      */     else {
/*  796 */       int nPC = ((Integer)this.sumPenalties.get(1)).intValue();
/*  797 */       nPC++;
/*  798 */       this.sumPenalties.set(1, new Integer(nPC));
/*      */     }
/*  800 */     this.sumPenalties.add(new Integer(nCon));
/*  801 */     this.sumPenalties.add(paramIndices);
/*  802 */     this.sumPenalties.add(plusOrMinus);
/*  803 */     this.sumPenalties.add(new Integer(conDir));
/*  804 */     this.sumPenalties.add(new Double(constraint));
/*  805 */     ArrayMaths am = new ArrayMaths(paramIndices);
/*  806 */     int maxI = am.getMaximum_as_int();
/*  807 */     if (maxI > this.maxConstraintIndex) this.maxConstraintIndex = maxI;
/*      */   }
/*      */   
/*      */   public void setConstraintMethod(int conMeth)
/*      */   {
/*  812 */     this.constraintMethod = conMeth;
/*  813 */     if (!this.penalties.isEmpty()) { this.penalties.set(0, new Integer(this.constraintMethod));
/*      */     }
/*      */   }
/*      */   
/*      */ 
/*      */   public void removeConstraints()
/*      */   {
/*  820 */     if (!this.penalties.isEmpty()) {
/*  821 */       int m = this.penalties.size();
/*      */       
/*      */ 
/*  824 */       for (int i = m - 1; i >= 0; i--) {
/*  825 */         this.penalties.remove(i);
/*      */       }
/*      */     }
/*  828 */     this.penalty = false;
/*  829 */     this.nConstraints = 0;
/*      */     
/*      */ 
/*  832 */     if (!this.sumPenalties.isEmpty()) {
/*  833 */       int m = this.sumPenalties.size();
/*      */       
/*      */ 
/*  836 */       for (int i = m - 1; i >= 0; i--) {
/*  837 */         this.sumPenalties.remove(i);
/*      */       }
/*      */     }
/*  840 */     this.sumPenalty = false;
/*  841 */     this.nSumConstraints = 0;
/*  842 */     this.maxConstraintIndex = -1;
/*      */   }
/*      */   
/*      */   public void setConstraintTolerance(double tolerance)
/*      */   {
/*  847 */     this.constraintTolerance = tolerance;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */   public void print(String filename, int prec)
/*      */   {
/*  854 */     this.prec = prec;
/*  855 */     print(filename);
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */   public void print(int prec)
/*      */   {
/*  862 */     this.prec = prec;
/*  863 */     String filename = "MinimisationOutput.txt";
/*  864 */     print(filename);
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */   public void print(String filename)
/*      */   {
/*  872 */     if (filename.indexOf('.') == -1) filename = filename + ".txt";
/*  873 */     FileOutput fout = new FileOutput(filename, 'n');
/*  874 */     fout.dateAndTimeln(filename);
/*  875 */     fout.println(" ");
/*  876 */     fout.println("Simplex minimisation, using the method of Nelder and Mead,");
/*  877 */     fout.println("of the function y = f(c[0], c[1], c[2] . . .)");
/*  878 */     this.paraName = new String[this.nParam];
/*  879 */     for (int i = 0; i < this.nParam; i++) { this.paraName[i] = ("c[" + i + "]");
/*      */     }
/*  881 */     fout.println();
/*  882 */     if (!this.convStatus) {
/*  883 */       fout.println("Convergence criterion was not satisfied");
/*  884 */       fout.println("The following results are the current estimates on exiting the minimisation method");
/*  885 */       fout.println();
/*      */     }
/*      */     
/*  888 */     fout.println("Value of parameters at the minimum");
/*  889 */     fout.println(" ");
/*      */     
/*  891 */     fout.printtab(" ", this.field);
/*  892 */     fout.printtab("Value at", this.field);
/*  893 */     fout.printtab("Initial", this.field);
/*  894 */     fout.println("Initial");
/*      */     
/*  896 */     fout.printtab(" ", this.field);
/*  897 */     fout.printtab("mimium", this.field);
/*  898 */     fout.printtab("estimate", this.field);
/*  899 */     fout.println("step");
/*      */     
/*  901 */     for (int i = 0; i < this.nParam; i++) {
/*  902 */       fout.printtab(this.paraName[i], this.field);
/*  903 */       fout.printtab(Fmath.truncate(this.paramValue[i], this.prec), this.field);
/*  904 */       fout.printtab(Fmath.truncate(this.startH[i], this.prec), this.field);
/*  905 */       fout.println(Fmath.truncate(this.step[i], this.prec));
/*      */     }
/*  907 */     fout.println();
/*      */     
/*  909 */     fout.println(" ");
/*      */     
/*  911 */     fout.printtab("Number of paramaters");
/*  912 */     fout.println(this.nParam);
/*  913 */     fout.printtab("Number of iterations taken");
/*  914 */     fout.println(this.nIter);
/*  915 */     fout.printtab("Maximum number of iterations allowed");
/*  916 */     fout.println(this.nMax);
/*  917 */     fout.printtab("Number of restarts taken");
/*  918 */     fout.println(this.kRestart);
/*  919 */     fout.printtab("Maximum number of restarts allowed");
/*  920 */     fout.println(this.konvge);
/*  921 */     fout.printtab("Standard deviation of the simplex at the minimum");
/*  922 */     fout.println(Fmath.truncate(this.simplexSd, this.prec));
/*  923 */     fout.printtab("Convergence tolerance");
/*  924 */     fout.println(this.fTol);
/*  925 */     switch (this.minTest) {
/*  926 */     case 0:  if (this.convStatus) {
/*  927 */         fout.println("simplex sd < the tolerance");
/*      */       }
/*      */       else {
/*  930 */         fout.println("NOTE!!! simplex sd > the tolerance");
/*      */       }
/*      */       break;
/*      */     }
/*  934 */     fout.println();
/*  935 */     fout.println("End of file");
/*  936 */     fout.close();
/*      */   }
/*      */   
/*      */ 
/*      */   public void print()
/*      */   {
/*  942 */     String filename = "MinimisationOutput.txt";
/*  943 */     print(filename);
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */   public boolean getConvStatus()
/*      */   {
/*  951 */     return this.convStatus;
/*      */   }
/*      */   
/*      */   public void setScale(int n)
/*      */   {
/*  956 */     if ((n < 0) || (n > 1)) throw new IllegalArgumentException("The argument must be 0 (no scaling) 1(initial estimates all scaled to unity) or the array of scaling factors");
/*  957 */     this.scaleOpt = n;
/*      */   }
/*      */   
/*      */   public void setScale(double[] sc)
/*      */   {
/*  962 */     this.scale = sc;
/*  963 */     this.scaleOpt = 2;
/*      */   }
/*      */   
/*      */   public double[] getScale()
/*      */   {
/*  968 */     return this.scale;
/*      */   }
/*      */   
/*      */   public void setMinTest(int n)
/*      */   {
/*  973 */     if ((n < 0) || (n > 1)) throw new IllegalArgumentException("minTest must be 0 or 1");
/*  974 */     this.minTest = n;
/*      */   }
/*      */   
/*      */   public int getMinTest()
/*      */   {
/*  979 */     return this.minTest;
/*      */   }
/*      */   
/*      */   public double getSimplexSd()
/*      */   {
/*  984 */     return this.simplexSd;
/*      */   }
/*      */   
/*      */   public double[] getParamValues()
/*      */   {
/*  989 */     return this.paramValue;
/*      */   }
/*      */   
/*      */   public double getMinimum()
/*      */   {
/*  994 */     return this.minimum;
/*      */   }
/*      */   
/*      */   public int getNiter()
/*      */   {
/*  999 */     return this.nIter;
/*      */   }
/*      */   
/*      */ 
/*      */   public void setNmax(int nmax)
/*      */   {
/* 1005 */     this.nMax = nmax;
/*      */   }
/*      */   
/*      */   public int getNmax()
/*      */   {
/* 1010 */     return this.nMax;
/*      */   }
/*      */   
/*      */   public int getNrestarts()
/*      */   {
/* 1015 */     return this.kRestart;
/*      */   }
/*      */   
/*      */   public void setNrestartsMax(int nrs)
/*      */   {
/* 1020 */     this.konvge = nrs;
/*      */   }
/*      */   
/*      */   public int getNrestartsMax()
/*      */   {
/* 1025 */     return this.konvge;
/*      */   }
/*      */   
/*      */   public void setNMreflect(double refl)
/*      */   {
/* 1030 */     this.rCoeff = refl;
/*      */   }
/*      */   
/*      */   public double getNMreflect()
/*      */   {
/* 1035 */     return this.rCoeff;
/*      */   }
/*      */   
/*      */   public void setNMextend(double ext)
/*      */   {
/* 1040 */     this.eCoeff = ext;
/*      */   }
/*      */   
/*      */   public double getNMextend() {
/* 1044 */     return this.eCoeff;
/*      */   }
/*      */   
/*      */   public void setNMcontract(double con)
/*      */   {
/* 1049 */     this.cCoeff = con;
/*      */   }
/*      */   
/*      */   public double getNMcontract()
/*      */   {
/* 1054 */     return this.cCoeff;
/*      */   }
/*      */   
/*      */   public void setTolerance(double tol)
/*      */   {
/* 1059 */     this.fTol = tol;
/*      */   }
/*      */   
/*      */ 
/*      */   public double getTolerance()
/*      */   {
/* 1065 */     return this.fTol;
/*      */   }
/*      */ }


/* Location:              /home/menick/Scrivania/ProcessMining/CNMining/CNMining.jar!/flanagan/math/Minimisation.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       0.7.1
 */