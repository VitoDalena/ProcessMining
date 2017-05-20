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
/*      */ public class Maximisation
/*      */ {
/*   45 */   protected boolean iseOption = true;
/*      */   
/*   47 */   protected int nParam = 0;
/*   48 */   protected double[] paramValue = null;
/*   49 */   protected String[] paraName = null;
/*   50 */   protected double functValue = 0.0D;
/*   51 */   protected double lastFunctValNoCnstrnt = 0.0D;
/*   52 */   protected double maximum = 0.0D;
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
/*   92 */   protected double constraintTolerance = 1.0E-4D;
/*      */   
/*   94 */   protected double[] constraints = null;
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
/*  109 */   protected int maxTest = 0;
/*      */   
/*      */ 
/*  112 */   protected double simplexSd = 0.0D;
/*      */   
/*      */ 
/*      */   public Maximisation()
/*      */   {
/*  117 */     this.iseOption = true;
/*      */   }
/*      */   
/*      */   public void suppressNoConvergenceMessage()
/*      */   {
/*  122 */     this.suppressNoConvergenceMessage = true;
/*      */   }
/*      */   
/*      */   public void nelderMead(MaximizationFunction gg, double[] start, double[] step, double fTol, int nMax)
/*      */   {
/*  127 */     nelderMead(gg, start, step, fTol, nMax);
/*      */   }
/*      */   
/*      */   public void nelderMead(MaximisationFunction gg, double[] start, double[] step, double fTol, int nMax)
/*      */   {
/*  132 */     nelderMead(gg, start, step, fTol, nMax);
/*      */   }
/*      */   
/*      */   public void nelderMead(Object g, double[] start, double[] step, double fTol, int nMax)
/*      */   {
/*  137 */     boolean testContract = false;
/*  138 */     int np = start.length;
/*  139 */     if (this.maxConstraintIndex >= np) throw new IllegalArgumentException("You have entered more constrained parameters (" + this.maxConstraintIndex + ") than minimisation parameters (" + np + ")");
/*  140 */     this.nParam = np;
/*  141 */     this.convStatus = true;
/*  142 */     int nnp = np + 1;
/*  143 */     this.lastFunctValNoCnstrnt = 0.0D;
/*      */     
/*  145 */     if (this.scaleOpt < 2) this.scale = new double[np];
/*  146 */     if ((this.scaleOpt == 2) && (this.scale.length != start.length)) throw new IllegalArgumentException("scale array and initial estimate array are of different lengths");
/*  147 */     if (step.length != start.length) { throw new IllegalArgumentException("step array length " + step.length + " and initial estimate array length " + start.length + " are of different");
/*      */     }
/*      */     
/*  150 */     for (int i = 0; i < np; i++) { if (step[i] == 0.0D) { throw new IllegalArgumentException("step " + i + " size is zero");
/*      */       }
/*      */     }
/*  153 */     this.paramValue = new double[np];
/*  154 */     this.startH = new double[np];
/*  155 */     this.step = new double[np];
/*  156 */     double[] pmax = new double[np];
/*      */     
/*  158 */     double[][] pp = new double[nnp][nnp];
/*  159 */     double[] yy = new double[nnp];
/*  160 */     double[] pbar = new double[nnp];
/*  161 */     double[] pstar = new double[nnp];
/*  162 */     double[] p2star = new double[nnp];
/*      */     
/*      */ 
/*  165 */     if (this.penalty) {
/*  166 */       Integer itemp = (Integer)this.penalties.get(1);
/*  167 */       this.nConstraints = itemp.intValue();
/*  168 */       this.penaltyParam = new int[this.nConstraints];
/*  169 */       this.penaltyCheck = new int[this.nConstraints];
/*  170 */       this.constraints = new double[this.nConstraints];
/*  171 */       Double dtemp = null;
/*  172 */       int j = 2;
/*  173 */       for (int i = 0; i < this.nConstraints; i++) {
/*  174 */         itemp = (Integer)this.penalties.get(j);
/*  175 */         this.penaltyParam[i] = itemp.intValue();
/*  176 */         j++;
/*  177 */         itemp = (Integer)this.penalties.get(j);
/*  178 */         this.penaltyCheck[i] = itemp.intValue();
/*  179 */         j++;
/*  180 */         dtemp = (Double)this.penalties.get(j);
/*  181 */         this.constraints[i] = dtemp.doubleValue();
/*  182 */         j++;
/*      */       }
/*      */     }
/*      */     
/*      */ 
/*  187 */     if (this.sumPenalty) {
/*  188 */       Integer itemp = (Integer)this.sumPenalties.get(1);
/*  189 */       this.nSumConstraints = itemp.intValue();
/*  190 */       this.sumPenaltyParam = new int[this.nSumConstraints][];
/*  191 */       this.sumPlusOrMinus = new double[this.nSumConstraints][];
/*  192 */       this.sumPenaltyCheck = new int[this.nSumConstraints];
/*  193 */       this.sumPenaltyNumber = new int[this.nSumConstraints];
/*  194 */       this.sumConstraints = new double[this.nSumConstraints];
/*  195 */       int[] itempArray = null;
/*  196 */       double[] dtempArray = null;
/*  197 */       Double dtemp = null;
/*  198 */       int j = 2;
/*  199 */       for (int i = 0; i < this.nSumConstraints; i++) {
/*  200 */         itemp = (Integer)this.sumPenalties.get(j);
/*  201 */         this.sumPenaltyNumber[i] = itemp.intValue();
/*  202 */         j++;
/*  203 */         itempArray = (int[])this.sumPenalties.get(j);
/*  204 */         this.sumPenaltyParam[i] = itempArray;
/*  205 */         j++;
/*  206 */         dtempArray = (double[])this.sumPenalties.get(j);
/*  207 */         this.sumPlusOrMinus[i] = dtempArray;
/*  208 */         j++;
/*  209 */         itemp = (Integer)this.sumPenalties.get(j);
/*  210 */         this.sumPenaltyCheck[i] = itemp.intValue();
/*  211 */         j++;
/*  212 */         dtemp = (Double)this.sumPenalties.get(j);
/*  213 */         this.sumConstraints[i] = dtemp.doubleValue();
/*  214 */         j++;
/*      */       }
/*      */     }
/*      */     
/*      */ 
/*  219 */     for (int i = 0; i < np; i++) { this.startH[i] = start[i];
/*      */     }
/*      */     
/*  222 */     if (this.scaleOpt > 0) {
/*  223 */       boolean testzero = false;
/*  224 */       for (int i = 0; i < np; i++) if (start[i] == 0.0D) testzero = true;
/*  225 */       if (testzero) {
/*  226 */         System.out.println("Neler and Mead Simplex: a start value of zero precludes scaling");
/*  227 */         System.out.println("Regression performed without scaling");
/*  228 */         this.scaleOpt = 0;
/*      */       }
/*      */     }
/*  231 */     switch (this.scaleOpt) {
/*  232 */     case 0:  for (int i = 0; i < np; i++) this.scale[i] = 1.0D;
/*  233 */       break;
/*  234 */     case 1:  for (int i = 0; i < np; i++) {
/*  235 */         this.scale[i] = (1.0D / start[i]);
/*  236 */         step[i] /= start[i];
/*  237 */         start[i] = 1.0D;
/*      */       }
/*  239 */       break;
/*  240 */     case 2:  for (int i = 0; i < np; i++) {
/*  241 */         step[i] *= this.scale[i];
/*  242 */         start[i] *= this.scale[i];
/*      */       }
/*      */     }
/*      */     
/*      */     
/*      */ 
/*  248 */     this.fTol = fTol;
/*  249 */     this.nMax = nMax;
/*  250 */     this.nIter = 0;
/*  251 */     for (int i = 0; i < np; i++) {
/*  252 */       this.step[i] = step[i];
/*  253 */       this.scale[i] = this.scale[i];
/*      */     }
/*      */     
/*      */ 
/*  257 */     double sho = 0.0D;
/*  258 */     for (int i = 0; i < np; i++) {
/*  259 */       sho = start[i];
/*  260 */       pstar[i] = sho;
/*  261 */       p2star[i] = sho;
/*  262 */       pmax[i] = sho;
/*      */     }
/*      */     
/*  265 */     int jcount = this.konvge;
/*      */     
/*  267 */     for (int i = 0; i < np; i++) {
/*  268 */       pp[i][(nnp - 1)] = start[i];
/*      */     }
/*  270 */     yy[(nnp - 1)] = functionValue(g, start);
/*  271 */     for (int j = 0; j < np; j++) {
/*  272 */       start[j] += step[j];
/*      */       
/*  274 */       for (int i = 0; i < np; i++) pp[i][j] = start[i];
/*  275 */       yy[j] = functionValue(g, start);
/*  276 */       start[j] -= step[j];
/*      */     }
/*      */     
/*      */ 
/*  280 */     double ynewlo = 0.0D;
/*  281 */     double ystar = 0.0D;
/*  282 */     double y2star = 0.0D;
/*  283 */     double ylo = 0.0D;
/*      */     
/*      */ 
/*  286 */     double curMin = 0.0D;double sumnm = 0.0D;double summnm = 0.0D;double zn = 0.0D;
/*  287 */     int ilo = 0;
/*  288 */     int ihi = 0;
/*  289 */     int ln = 0;
/*  290 */     boolean test = true;
/*      */     
/*  292 */     while (test)
/*      */     {
/*  294 */       ylo = yy[0];
/*  295 */       ynewlo = ylo;
/*  296 */       ilo = 0;
/*  297 */       ihi = 0;
/*  298 */       for (int i = 1; i < nnp; i++) {
/*  299 */         if (yy[i] < ylo) {
/*  300 */           ylo = yy[i];
/*  301 */           ilo = i;
/*      */         }
/*  303 */         if (yy[i] > ynewlo) {
/*  304 */           ynewlo = yy[i];
/*  305 */           ihi = i;
/*      */         }
/*      */       }
/*      */       
/*  309 */       for (int i = 0; i < np; i++) {
/*  310 */         zn = 0.0D;
/*  311 */         for (int j = 0; j < nnp; j++) {
/*  312 */           zn += pp[i][j];
/*      */         }
/*  314 */         zn -= pp[i][ihi];
/*  315 */         pbar[i] = (zn / np);
/*      */       }
/*      */       
/*      */ 
/*  319 */       for (int i = 0; i < np; i++) { pstar[i] = ((1.0D + this.rCoeff) * pbar[i] - this.rCoeff * pp[i][ihi]);
/*      */       }
/*      */       
/*  322 */       ystar = functionValue(g, pstar);
/*      */       
/*  324 */       this.nIter += 1;
/*      */       
/*      */ 
/*  327 */       if (ystar < ylo)
/*      */       {
/*  329 */         for (int i = 0; i < np; i++) { p2star[i] = (pstar[i] * (1.0D + this.eCoeff) - this.eCoeff * pbar[i]);
/*      */         }
/*  331 */         y2star = functionValue(g, p2star);
/*  332 */         this.nIter += 1;
/*  333 */         if (y2star < ylo)
/*      */         {
/*  335 */           for (int i = 0; i < np; i++) pp[i][ihi] = p2star[i];
/*  336 */           yy[ihi] = y2star;
/*      */         }
/*      */         else
/*      */         {
/*  340 */           for (int i = 0; i < np; i++) pp[i][ihi] = pstar[i];
/*  341 */           yy[ihi] = ystar;
/*      */         }
/*      */       }
/*      */       else
/*      */       {
/*  346 */         ln = 0;
/*  347 */         for (int i = 0; i < nnp; i++) if ((i != ihi) && (ystar > yy[i])) ln++;
/*  348 */         if (ln == np)
/*      */         {
/*  350 */           if (ystar <= yy[ihi])
/*      */           {
/*  352 */             for (int i = 0; i < np; i++) pp[i][ihi] = pstar[i];
/*  353 */             yy[ihi] = ystar;
/*      */           }
/*      */           
/*  356 */           for (int i = 0; i < np; i++) { p2star[i] = (this.cCoeff * pp[i][ihi] + (1.0D - this.cCoeff) * pbar[i]);
/*      */           }
/*  358 */           y2star = functionValue(g, p2star);
/*  359 */           this.nIter += 1;
/*      */           
/*  361 */           if (y2star > yy[ihi])
/*      */           {
/*      */ 
/*  364 */             for (int j = 0; j < nnp; j++) {
/*  365 */               for (int i = 0; i < np; i++) {
/*  366 */                 pp[i][j] = (0.5D * (pp[i][j] + pp[i][ilo]));
/*  367 */                 pmax[i] = pp[i][j];
/*      */               }
/*  369 */               yy[j] = functionValue(g, pmax);
/*      */             }
/*  371 */             this.nIter += nnp;
/*      */           }
/*      */           else
/*      */           {
/*  375 */             for (int i = 0; i < np; i++) pp[i][ihi] = p2star[i];
/*  376 */             yy[ihi] = y2star;
/*      */           }
/*      */         }
/*      */         else
/*      */         {
/*  381 */           for (int i = 0; i < np; i++) pp[i][ihi] = pstar[i];
/*  382 */           yy[ihi] = ystar;
/*      */         }
/*      */       }
/*      */       
/*      */ 
/*      */ 
/*  388 */       sumnm = 0.0D;
/*  389 */       ynewlo = yy[0];
/*  390 */       ilo = 0;
/*  391 */       for (int i = 0; i < nnp; i++) {
/*  392 */         sumnm += yy[i];
/*  393 */         if (ynewlo > yy[i]) {
/*  394 */           ynewlo = yy[i];
/*  395 */           ilo = i;
/*      */         }
/*      */       }
/*  398 */       sumnm /= nnp;
/*  399 */       summnm = 0.0D;
/*  400 */       for (int i = 0; i < nnp; i++) {
/*  401 */         zn = yy[i] - sumnm;
/*  402 */         summnm += zn * zn;
/*      */       }
/*  404 */       curMin = Math.sqrt(summnm / np);
/*      */       
/*      */ 
/*  407 */       switch (this.maxTest) {
/*      */       case 0: 
/*  409 */         if (curMin < fTol) test = false;
/*      */         break;
/*      */       }
/*  412 */       this.maximum = (-ynewlo);
/*  413 */       if (!test)
/*      */       {
/*  415 */         for (int i = 0; i < np; i++) pmax[i] = pp[i][ilo];
/*  416 */         yy[(nnp - 1)] = ynewlo;
/*      */         
/*  418 */         this.simplexSd = curMin;
/*      */         
/*  420 */         jcount--;
/*  421 */         if (jcount > 0) {
/*  422 */           test = true;
/*  423 */           for (int j = 0; j < np; j++) {
/*  424 */             pmax[j] += step[j];
/*  425 */             for (int i = 0; i < np; i++) pp[i][j] = pmax[i];
/*  426 */             yy[j] = functionValue(g, pmax);
/*  427 */             pmax[j] -= step[j];
/*      */           }
/*      */         }
/*      */       }
/*      */       
/*  432 */       if ((test) && (this.nIter > this.nMax)) {
/*  433 */         if (!this.suppressNoConvergenceMessage) {
/*  434 */           System.out.println("Maximum iteration number reached, in Maximisation.simplex(...)");
/*  435 */           System.out.println("without the convergence criterion being satisfied");
/*  436 */           System.out.println("Current parameter estimates and function value returned");
/*      */         }
/*  438 */         this.convStatus = false;
/*      */         
/*  440 */         for (int i = 0; i < np; i++) pmax[i] = pp[i][ilo];
/*  441 */         yy[(nnp - 1)] = ynewlo;
/*  442 */         test = false;
/*      */       }
/*      */     }
/*      */     
/*  446 */     for (int i = 0; i < np; i++) {
/*  447 */       pmax[i] = pp[i][ilo];
/*  448 */       this.paramValue[i] = (pmax[i] / this.scale[i]);
/*      */     }
/*  450 */     this.maximum = (-ynewlo);
/*  451 */     this.kRestart = (this.konvge - jcount);
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */   public void nelderMead(MaximizationFunction g, double[] start, double[] step, double fTol)
/*      */   {
/*  458 */     int nMaxx = this.nMax;
/*  459 */     nelderMead(g, start, step, fTol, nMaxx);
/*      */   }
/*      */   
/*      */   public void nelderMead(MaximisationFunction g, double[] start, double[] step, double fTol) {
/*  463 */     int nMaxx = this.nMax;
/*  464 */     nelderMead(g, start, step, fTol, nMaxx);
/*      */   }
/*      */   
/*      */ 
/*      */   public void nelderMead(MaximizationFunction g, double[] start, double[] step, int nMax)
/*      */   {
/*  470 */     double fToll = this.fTol;
/*  471 */     nelderMead(g, start, step, fToll, nMax);
/*      */   }
/*      */   
/*      */   public void nelderMead(MaximisationFunction g, double[] start, double[] step, int nMax) {
/*  475 */     double fToll = this.fTol;
/*  476 */     nelderMead(g, start, step, fToll, nMax);
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */   public void nelderMead(MaximizationFunction g, double[] start, double[] step)
/*      */   {
/*  483 */     double fToll = this.fTol;
/*  484 */     int nMaxx = this.nMax;
/*  485 */     nelderMead(g, start, step, fToll, nMaxx);
/*      */   }
/*      */   
/*      */   public void nelderMead(MaximisationFunction g, double[] start, double[] step) {
/*  489 */     double fToll = this.fTol;
/*  490 */     int nMaxx = this.nMax;
/*  491 */     nelderMead(g, start, step, fToll, nMaxx);
/*      */   }
/*      */   
/*      */ 
/*      */   public void nelderMead(MaximizationFunction g, double[] start, double fTol, int nMax)
/*      */   {
/*  497 */     int n = start.length;
/*  498 */     double[] stepp = new double[n];
/*  499 */     for (int i = 0; i < n; i++) stepp[i] = (this.dStep * start[i]);
/*  500 */     nelderMead(g, start, stepp, fTol, nMax);
/*      */   }
/*      */   
/*      */   public void nelderMead(MaximisationFunction g, double[] start, double fTol, int nMax) {
/*  504 */     int n = start.length;
/*  505 */     double[] stepp = new double[n];
/*  506 */     for (int i = 0; i < n; i++) stepp[i] = (this.dStep * start[i]);
/*  507 */     nelderMead(g, start, stepp, fTol, nMax);
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */   public void nelderMead(MaximizationFunction g, double[] start, double fTol)
/*      */   {
/*  514 */     int n = start.length;
/*  515 */     int nMaxx = this.nMax;
/*  516 */     double[] stepp = new double[n];
/*  517 */     for (int i = 0; i < n; i++) stepp[i] = (this.dStep * start[i]);
/*  518 */     nelderMead(g, start, stepp, fTol, nMaxx);
/*      */   }
/*      */   
/*      */   public void nelderMead(MaximisationFunction g, double[] start, double fTol) {
/*  522 */     int n = start.length;
/*  523 */     int nMaxx = this.nMax;
/*  524 */     double[] stepp = new double[n];
/*  525 */     for (int i = 0; i < n; i++) stepp[i] = (this.dStep * start[i]);
/*  526 */     nelderMead(g, start, stepp, fTol, nMaxx);
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */   public void nelderMead(MaximizationFunction g, double[] start, int nMax)
/*      */   {
/*  533 */     int n = start.length;
/*  534 */     double fToll = this.fTol;
/*  535 */     double[] stepp = new double[n];
/*  536 */     for (int i = 0; i < n; i++) stepp[i] = (this.dStep * start[i]);
/*  537 */     nelderMead(g, start, stepp, fToll, nMax);
/*      */   }
/*      */   
/*      */   public void nelderMead(MaximisationFunction g, double[] start, int nMax) {
/*  541 */     int n = start.length;
/*  542 */     double fToll = this.fTol;
/*  543 */     double[] stepp = new double[n];
/*  544 */     for (int i = 0; i < n; i++) stepp[i] = (this.dStep * start[i]);
/*  545 */     nelderMead(g, start, stepp, fToll, nMax);
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */   public void nelderMead(MaximizationFunction g, double[] start)
/*      */   {
/*  553 */     int n = start.length;
/*  554 */     int nMaxx = this.nMax;
/*  555 */     double fToll = this.fTol;
/*  556 */     double[] stepp = new double[n];
/*  557 */     for (int i = 0; i < n; i++) stepp[i] = (this.dStep * start[i]);
/*  558 */     nelderMead(g, start, stepp, fToll, nMaxx);
/*      */   }
/*      */   
/*      */   public void nelderMead(MaximisationFunction g, double[] start) {
/*  562 */     int n = start.length;
/*  563 */     int nMaxx = this.nMax;
/*  564 */     double fToll = this.fTol;
/*  565 */     double[] stepp = new double[n];
/*  566 */     for (int i = 0; i < n; i++) stepp[i] = (this.dStep * start[i]);
/*  567 */     nelderMead(g, start, stepp, fToll, nMaxx);
/*      */   }
/*      */   
/*      */   protected double functionValue(Object g, double[] x)
/*      */   {
/*  572 */     if (this.iseOption) {
/*  573 */       return functionValue((MaximisationFunction)g, x);
/*      */     }
/*      */     
/*  576 */     return functionValue((MaximizationFunction)g, x);
/*      */   }
/*      */   
/*      */ 
/*      */   protected double functionValue(MaximizationFunction g, double[] x)
/*      */   {
/*  582 */     double funcVal = -3.0D;
/*  583 */     double[] param = new double[this.nParam];
/*      */     
/*  585 */     for (int i = 0; i < this.nParam; i++) { x[i] /= this.scale[i];
/*      */     }
/*      */     
/*  588 */     double tempFunctVal = this.lastFunctValNoCnstrnt;
/*  589 */     boolean test = true;
/*  590 */     if (this.penalty) {
/*  591 */       int k = 0;
/*  592 */       for (int i = 0; i < this.nConstraints; i++) {
/*  593 */         k = this.penaltyParam[i];
/*  594 */         switch (this.penaltyCheck[i]) {
/*  595 */         case -1:  if (param[k] < this.constraints[i]) {
/*  596 */             funcVal = tempFunctVal + this.penaltyWeight * Fmath.square(this.constraints[i] - param[k]);
/*  597 */             test = false; }
/*  598 */           break;
/*      */         case 0: 
/*  600 */           if (param[k] < this.constraints[i] * (1.0D - this.constraintTolerance)) {
/*  601 */             funcVal = tempFunctVal + this.penaltyWeight * Fmath.square(this.constraints[i] * (1.0D - this.constraintTolerance) - param[k]);
/*  602 */             test = false;
/*      */           }
/*  604 */           if (param[k] > this.constraints[i] * (1.0D + this.constraintTolerance)) {
/*  605 */             funcVal = tempFunctVal + this.penaltyWeight * Fmath.square(param[k] - this.constraints[i] * (1.0D + this.constraintTolerance));
/*  606 */             test = false; }
/*  607 */           break;
/*      */         case 1: 
/*  609 */           if (param[k] > this.constraints[i]) {
/*  610 */             funcVal = tempFunctVal + this.penaltyWeight * Fmath.square(param[k] - this.constraints[i]);
/*  611 */             test = false;
/*      */           }
/*      */           
/*      */           break;
/*      */         }
/*      */         
/*      */       }
/*      */     }
/*  619 */     if (this.sumPenalty) {
/*  620 */       int kk = 0;
/*  621 */       double pSign = 0.0D;
/*  622 */       double sumPenaltySum = 0.0D;
/*  623 */       for (int i = 0; i < this.nSumConstraints; i++) {
/*  624 */         for (int j = 0; j < this.sumPenaltyNumber[i]; j++) {
/*  625 */           kk = this.sumPenaltyParam[i][j];
/*  626 */           pSign = this.sumPlusOrMinus[i][j];
/*  627 */           sumPenaltySum += param[kk] * pSign;
/*      */         }
/*  629 */         switch (this.sumPenaltyCheck[i]) {
/*  630 */         case -1:  if (sumPenaltySum < this.sumConstraints[i]) {
/*  631 */             funcVal = tempFunctVal + this.penaltyWeight * Fmath.square(this.sumConstraints[i] - sumPenaltySum);
/*  632 */             test = false; }
/*  633 */           break;
/*      */         case 0: 
/*  635 */           if (sumPenaltySum < this.sumConstraints[i] * (1.0D - this.constraintTolerance)) {
/*  636 */             funcVal = tempFunctVal + this.penaltyWeight * Fmath.square(this.sumConstraints[i] * (1.0D - this.constraintTolerance) - sumPenaltySum);
/*  637 */             test = false;
/*      */           }
/*  639 */           if (sumPenaltySum > this.sumConstraints[i] * (1.0D + this.constraintTolerance)) {
/*  640 */             funcVal = tempFunctVal + this.penaltyWeight * Fmath.square(sumPenaltySum - this.sumConstraints[i] * (1.0D + this.constraintTolerance));
/*  641 */             test = false; }
/*  642 */           break;
/*      */         case 1: 
/*  644 */           if (sumPenaltySum > this.sumConstraints[i]) {
/*  645 */             funcVal = tempFunctVal + this.penaltyWeight * Fmath.square(sumPenaltySum - this.sumConstraints[i]);
/*  646 */             test = false;
/*      */           }
/*      */           
/*      */           break;
/*      */         }
/*      */         
/*      */       }
/*      */     }
/*  654 */     if (test) {
/*  655 */       funcVal = -g.function(param);
/*  656 */       this.lastFunctValNoCnstrnt = funcVal;
/*      */     }
/*  658 */     return funcVal;
/*      */   }
/*      */   
/*      */ 
/*      */   protected double functionValue(MaximisationFunction g, double[] x)
/*      */   {
/*  664 */     double funcVal = -3.0D;
/*  665 */     double[] param = new double[this.nParam];
/*      */     
/*  667 */     for (int i = 0; i < this.nParam; i++) { x[i] /= this.scale[i];
/*      */     }
/*      */     
/*  670 */     double tempFunctVal = this.lastFunctValNoCnstrnt;
/*  671 */     boolean test = true;
/*  672 */     if (this.penalty) {
/*  673 */       int k = 0;
/*  674 */       for (int i = 0; i < this.nConstraints; i++) {
/*  675 */         k = this.penaltyParam[i];
/*  676 */         switch (this.penaltyCheck[i]) {
/*  677 */         case -1:  if (param[k] < this.constraints[i]) {
/*  678 */             funcVal = tempFunctVal + this.penaltyWeight * Fmath.square(this.constraints[i] - param[k]);
/*  679 */             test = false; }
/*  680 */           break;
/*      */         case 0: 
/*  682 */           if (param[k] < this.constraints[i] * (1.0D - this.constraintTolerance)) {
/*  683 */             funcVal = tempFunctVal + this.penaltyWeight * Fmath.square(this.constraints[i] * (1.0D - this.constraintTolerance) - param[k]);
/*  684 */             test = false;
/*      */           }
/*  686 */           if (param[k] > this.constraints[i] * (1.0D + this.constraintTolerance)) {
/*  687 */             funcVal = tempFunctVal + this.penaltyWeight * Fmath.square(param[k] - this.constraints[i] * (1.0D + this.constraintTolerance));
/*  688 */             test = false; }
/*  689 */           break;
/*      */         case 1: 
/*  691 */           if (param[k] > this.constraints[i]) {
/*  692 */             funcVal = tempFunctVal + this.penaltyWeight * Fmath.square(param[k] - this.constraints[i]);
/*  693 */             test = false;
/*      */           }
/*      */           
/*      */           break;
/*      */         }
/*      */         
/*      */       }
/*      */     }
/*  701 */     if (this.sumPenalty) {
/*  702 */       int kk = 0;
/*  703 */       double pSign = 0.0D;
/*  704 */       double sumPenaltySum = 0.0D;
/*  705 */       for (int i = 0; i < this.nSumConstraints; i++) {
/*  706 */         for (int j = 0; j < this.sumPenaltyNumber[i]; j++) {
/*  707 */           kk = this.sumPenaltyParam[i][j];
/*  708 */           pSign = this.sumPlusOrMinus[i][j];
/*  709 */           sumPenaltySum += param[kk] * pSign;
/*      */         }
/*  711 */         switch (this.sumPenaltyCheck[i]) {
/*  712 */         case -1:  if (sumPenaltySum < this.sumConstraints[i]) {
/*  713 */             funcVal = tempFunctVal + this.penaltyWeight * Fmath.square(this.sumConstraints[i] - sumPenaltySum);
/*  714 */             test = false; }
/*  715 */           break;
/*      */         case 0: 
/*  717 */           if (sumPenaltySum < this.sumConstraints[i] * (1.0D - this.constraintTolerance)) {
/*  718 */             funcVal = tempFunctVal + this.penaltyWeight * Fmath.square(this.sumConstraints[i] * (1.0D - this.constraintTolerance) - sumPenaltySum);
/*  719 */             test = false;
/*      */           }
/*  721 */           if (sumPenaltySum > this.sumConstraints[i] * (1.0D + this.constraintTolerance)) {
/*  722 */             funcVal = tempFunctVal + this.penaltyWeight * Fmath.square(sumPenaltySum - this.sumConstraints[i] * (1.0D + this.constraintTolerance));
/*  723 */             test = false; }
/*  724 */           break;
/*      */         case 1: 
/*  726 */           if (sumPenaltySum > this.sumConstraints[i]) {
/*  727 */             funcVal = tempFunctVal + this.penaltyWeight * Fmath.square(sumPenaltySum - this.sumConstraints[i]);
/*  728 */             test = false;
/*      */           }
/*      */           
/*      */           break;
/*      */         }
/*      */         
/*      */       }
/*      */     }
/*  736 */     if (test) {
/*  737 */       funcVal = -g.function(param);
/*  738 */       this.lastFunctValNoCnstrnt = funcVal;
/*      */     }
/*  740 */     return funcVal;
/*      */   }
/*      */   
/*      */   public void addConstraint(int paramIndex, int conDir, double constraint)
/*      */   {
/*  745 */     this.penalty = true;
/*      */     
/*  747 */     if (this.penalties.isEmpty()) { this.penalties.add(new Integer(this.constraintMethod));
/*      */     }
/*      */     
/*  750 */     if (this.penalties.size() == 1) {
/*  751 */       this.penalties.add(new Integer(1));
/*      */     }
/*      */     else {
/*  754 */       int nPC = ((Integer)this.penalties.get(1)).intValue();
/*  755 */       nPC++;
/*  756 */       this.penalties.set(1, new Integer(nPC));
/*      */     }
/*  758 */     this.penalties.add(new Integer(paramIndex));
/*  759 */     this.penalties.add(new Integer(conDir));
/*  760 */     this.penalties.add(new Double(constraint));
/*  761 */     if (paramIndex > this.maxConstraintIndex) this.maxConstraintIndex = paramIndex;
/*      */   }
/*      */   
/*      */   public void addConstraint(int[] paramIndices, int[] plusOrMinus, int conDir, double constraint)
/*      */   {
/*  766 */     ArrayMaths am = new ArrayMaths(plusOrMinus);
/*  767 */     double[] dpom = am.getArray_as_double();
/*  768 */     addConstraint(paramIndices, dpom, conDir, constraint);
/*      */   }
/*      */   
/*      */   public void addConstraint(int[] paramIndices, double[] plusOrMinus, int conDir, double constraint)
/*      */   {
/*  773 */     int nCon = paramIndices.length;
/*  774 */     int nPorM = plusOrMinus.length;
/*  775 */     if (nCon != nPorM) throw new IllegalArgumentException("num of parameters, " + nCon + ", does not equal number of parameter signs, " + nPorM);
/*  776 */     this.sumPenalty = true;
/*      */     
/*  778 */     if (this.sumPenalties.isEmpty()) { this.sumPenalties.add(new Integer(this.constraintMethod));
/*      */     }
/*      */     
/*  781 */     if (this.sumPenalties.size() == 1) {
/*  782 */       this.sumPenalties.add(new Integer(1));
/*      */     }
/*      */     else {
/*  785 */       int nPC = ((Integer)this.sumPenalties.get(1)).intValue();
/*  786 */       nPC++;
/*  787 */       this.sumPenalties.set(1, new Integer(nPC));
/*      */     }
/*  789 */     this.sumPenalties.add(new Integer(nCon));
/*  790 */     this.sumPenalties.add(paramIndices);
/*  791 */     this.sumPenalties.add(plusOrMinus);
/*  792 */     this.sumPenalties.add(new Integer(conDir));
/*  793 */     this.sumPenalties.add(new Double(constraint));
/*  794 */     ArrayMaths am = new ArrayMaths(paramIndices);
/*  795 */     int maxI = am.getMaximum_as_int();
/*  796 */     if (maxI > this.maxConstraintIndex) this.maxConstraintIndex = maxI;
/*      */   }
/*      */   
/*      */   public void setConstraintMethod(int conMeth)
/*      */   {
/*  801 */     this.constraintMethod = conMeth;
/*  802 */     if (!this.penalties.isEmpty()) { this.penalties.set(0, new Integer(this.constraintMethod));
/*      */     }
/*      */   }
/*      */   
/*      */ 
/*      */   public void removeConstraints()
/*      */   {
/*  809 */     if (!this.penalties.isEmpty()) {
/*  810 */       int m = this.penalties.size();
/*      */       
/*      */ 
/*  813 */       for (int i = m - 1; i >= 0; i--) {
/*  814 */         this.penalties.remove(i);
/*      */       }
/*      */     }
/*  817 */     this.penalty = false;
/*  818 */     this.nConstraints = 0;
/*      */     
/*      */ 
/*  821 */     if (!this.sumPenalties.isEmpty()) {
/*  822 */       int m = this.sumPenalties.size();
/*      */       
/*      */ 
/*  825 */       for (int i = m - 1; i >= 0; i--) {
/*  826 */         this.sumPenalties.remove(i);
/*      */       }
/*      */     }
/*  829 */     this.sumPenalty = false;
/*  830 */     this.nSumConstraints = 0;
/*  831 */     this.maxConstraintIndex = -1;
/*      */   }
/*      */   
/*      */   public void setConstraintTolerance(double tolerance)
/*      */   {
/*  836 */     this.constraintTolerance = tolerance;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */   public void print(String filename, int prec)
/*      */   {
/*  843 */     this.prec = prec;
/*  844 */     print(filename);
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */   public void print(int prec)
/*      */   {
/*  851 */     this.prec = prec;
/*  852 */     String filename = "MaximisationOutput.txt";
/*  853 */     print(filename);
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */   public void print(String filename)
/*      */   {
/*  861 */     if (filename.indexOf('.') == -1) filename = filename + ".txt";
/*  862 */     FileOutput fout = new FileOutput(filename, 'n');
/*  863 */     fout.dateAndTimeln(filename);
/*  864 */     fout.println(" ");
/*  865 */     fout.println("Simplex maximisation, using the method of Nelder and Mead,");
/*  866 */     fout.println("of the function y = f(c[0], c[1], c[2] . . .)");
/*  867 */     this.paraName = new String[this.nParam];
/*  868 */     for (int i = 0; i < this.nParam; i++) { this.paraName[i] = ("c[" + i + "]");
/*      */     }
/*  870 */     fout.println();
/*  871 */     if (!this.convStatus) {
/*  872 */       fout.println("Convergence criterion was not satisfied");
/*  873 */       fout.println("The following results are the current estimates on exiting the maximisation method");
/*  874 */       fout.println();
/*      */     }
/*      */     
/*  877 */     fout.println("Value of parameters at the maximum");
/*  878 */     fout.println(" ");
/*      */     
/*  880 */     fout.printtab(" ", this.field);
/*  881 */     fout.printtab("Value at", this.field);
/*  882 */     fout.printtab("Initial", this.field);
/*  883 */     fout.println("Initial");
/*      */     
/*  885 */     fout.printtab(" ", this.field);
/*  886 */     fout.printtab("maximium", this.field);
/*  887 */     fout.printtab("estimate", this.field);
/*  888 */     fout.println("step");
/*      */     
/*  890 */     for (int i = 0; i < this.nParam; i++) {
/*  891 */       fout.printtab(this.paraName[i], this.field);
/*  892 */       fout.printtab(Fmath.truncate(this.paramValue[i], this.prec), this.field);
/*  893 */       fout.printtab(Fmath.truncate(this.startH[i], this.prec), this.field);
/*  894 */       fout.println(Fmath.truncate(this.step[i], this.prec));
/*      */     }
/*  896 */     fout.println();
/*      */     
/*  898 */     fout.println(" ");
/*      */     
/*  900 */     fout.printtab("Number of paramaters");
/*  901 */     fout.println(this.nParam);
/*  902 */     fout.printtab("Number of iterations taken");
/*  903 */     fout.println(this.nIter);
/*  904 */     fout.printtab("Maximum number of iterations allowed");
/*  905 */     fout.println(this.nMax);
/*  906 */     fout.printtab("Number of restarts taken");
/*  907 */     fout.println(this.kRestart);
/*  908 */     fout.printtab("Maximum number of restarts allowed");
/*  909 */     fout.println(this.konvge);
/*  910 */     fout.printtab("Standard deviation of the simplex at the maximum");
/*  911 */     fout.println(Fmath.truncate(this.simplexSd, this.prec));
/*  912 */     fout.printtab("Convergence tolerance");
/*  913 */     fout.println(this.fTol);
/*  914 */     switch (this.maxTest) {
/*  915 */     case 0:  if (this.convStatus) {
/*  916 */         fout.println("simplex sd < the tolerance");
/*      */       }
/*      */       else {
/*  919 */         fout.println("NOTE!!! simplex sd > the tolerance");
/*      */       }
/*      */       break;
/*      */     }
/*  923 */     fout.println();
/*  924 */     fout.println("End of file");
/*  925 */     fout.close();
/*      */   }
/*      */   
/*      */ 
/*      */   public void print()
/*      */   {
/*  931 */     String filename = "MaximisationOutput.txt";
/*  932 */     print(filename);
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */   public boolean getConvStatus()
/*      */   {
/*  940 */     return this.convStatus;
/*      */   }
/*      */   
/*      */   public void setScale(int n)
/*      */   {
/*  945 */     if ((n < 0) || (n > 1)) throw new IllegalArgumentException("The argument must be 0 (no scaling) 1(initial estimates all scaled to unity) or the array of scaling factors");
/*  946 */     this.scaleOpt = n;
/*      */   }
/*      */   
/*      */   public void setScale(double[] sc)
/*      */   {
/*  951 */     this.scale = sc;
/*  952 */     this.scaleOpt = 2;
/*      */   }
/*      */   
/*      */   public double[] getScale()
/*      */   {
/*  957 */     return this.scale;
/*      */   }
/*      */   
/*      */   public void setMaxTest(int n)
/*      */   {
/*  962 */     if ((n < 0) || (n > 1)) throw new IllegalArgumentException("maxTest must be 0 or 1");
/*  963 */     this.maxTest = n;
/*      */   }
/*      */   
/*      */   public int getMaxTest()
/*      */   {
/*  968 */     return this.maxTest;
/*      */   }
/*      */   
/*      */   public double getSimplexSd()
/*      */   {
/*  973 */     return this.simplexSd;
/*      */   }
/*      */   
/*      */   public double[] getParamValues()
/*      */   {
/*  978 */     return this.paramValue;
/*      */   }
/*      */   
/*      */   public double getMaximum()
/*      */   {
/*  983 */     return this.maximum;
/*      */   }
/*      */   
/*      */   public int getNiter()
/*      */   {
/*  988 */     return this.nIter;
/*      */   }
/*      */   
/*      */ 
/*      */   public void setNmax(int nmax)
/*      */   {
/*  994 */     this.nMax = nmax;
/*      */   }
/*      */   
/*      */   public int getNmax()
/*      */   {
/*  999 */     return this.nMax;
/*      */   }
/*      */   
/*      */   public int getNrestarts()
/*      */   {
/* 1004 */     return this.kRestart;
/*      */   }
/*      */   
/*      */   public void setNrestartsMax(int nrs)
/*      */   {
/* 1009 */     this.konvge = nrs;
/*      */   }
/*      */   
/*      */   public int getNrestartsMax()
/*      */   {
/* 1014 */     return this.konvge;
/*      */   }
/*      */   
/*      */   public void setNMreflect(double refl)
/*      */   {
/* 1019 */     this.rCoeff = refl;
/*      */   }
/*      */   
/*      */   public double getNMreflect()
/*      */   {
/* 1024 */     return this.rCoeff;
/*      */   }
/*      */   
/*      */   public void setNMextend(double ext)
/*      */   {
/* 1029 */     this.eCoeff = ext;
/*      */   }
/*      */   
/*      */   public double getNMextend() {
/* 1033 */     return this.eCoeff;
/*      */   }
/*      */   
/*      */   public void setNMcontract(double con)
/*      */   {
/* 1038 */     this.cCoeff = con;
/*      */   }
/*      */   
/*      */   public double getNMcontract()
/*      */   {
/* 1043 */     return this.cCoeff;
/*      */   }
/*      */   
/*      */   public void setTolerance(double tol)
/*      */   {
/* 1048 */     this.fTol = tol;
/*      */   }
/*      */   
/*      */ 
/*      */   public double getTolerance()
/*      */   {
/* 1054 */     return this.fTol;
/*      */   }
/*      */ }


/* Location:              /home/menick/Scrivania/ProcessMining/CNMining/CNMining.jar!/flanagan/math/Maximisation.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       0.7.1
 */