/*      */ package flanagan.plot;
/*      */ 
/*      */ import flanagan.interpolation.CubicSpline;
/*      */ import flanagan.math.ArrayMaths;
/*      */ import flanagan.math.Fmath;
/*      */ import java.awt.Canvas;
/*      */ import java.awt.Font;
/*      */ import java.awt.FontMetrics;
/*      */ import java.awt.Graphics;
/*      */ import java.io.PrintStream;
/*      */ import java.io.Serializable;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ public class Plot
/*      */   extends Canvas
/*      */   implements Serializable
/*      */ {
/*      */   protected static final long serialVersionUID = 1L;
/*   44 */   protected double[][] data = (double[][])null;
/*      */   
/*      */ 
/*   47 */   protected double[][] copy = (double[][])null;
/*   48 */   protected int nCurves = 0;
/*   49 */   protected int[] nPoints = null;
/*   50 */   protected int nmPoints = 0;
/*   51 */   protected int niPoints = 200;
/*   52 */   protected int[] pointOpt = null;
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*   65 */   protected int[] pointSize = null;
/*   66 */   protected int npTypes = 8;
/*   67 */   protected boolean[] errorBar = null;
/*   68 */   protected double[][] errors = (double[][])null;
/*   69 */   protected double[][] errorsCopy = (double[][])null;
/*   70 */   protected int[] lineOpt = null;
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*   77 */   protected int[] dashLength = null;
/*   78 */   protected boolean[] minMaxOpt = null;
/*   79 */   protected boolean[] trimOpt = null;
/*      */   
/*   81 */   protected int fontSize = 14;
/*   82 */   protected int xLen = 625;
/*   83 */   protected int yLen = 375;
/*   84 */   protected int xBot = 100;
/*   85 */   protected int xTop = this.xBot + this.xLen;
/*   86 */   protected int yTop = 110;
/*   87 */   protected int yBot = this.yTop + this.yLen;
/*      */   
/*   89 */   protected double xLow = 0.0D;
/*   90 */   protected double xHigh = 0.0D;
/*   91 */   protected double yLow = 0.0D;
/*   92 */   protected double yHigh = 0.0D;
/*   93 */   protected int xFac = 0;
/*   94 */   protected int yFac = 0;
/*   95 */   protected int xTicks = 0;
/*   96 */   protected int yTicks = 0;
/*      */   
/*   98 */   protected double xMin = 0.0D;
/*   99 */   protected double xMax = 0.0D;
/*  100 */   protected double yMin = 0.0D;
/*  101 */   protected double yMax = 0.0D;
/*      */   
/*  103 */   protected double xOffset = 0.0D;
/*  104 */   protected double yOffset = 0.0D;
/*  105 */   protected boolean noXoffset = false;
/*  106 */   protected boolean noYoffset = false;
/*  107 */   protected double xLowFac = 0.75D;
/*  108 */   protected double yLowFac = 0.75D;
/*      */   
/*  110 */   protected String graphTitle = "  ";
/*  111 */   protected String graphTitle2 = "  ";
/*  112 */   protected String xAxisLegend = "  ";
/*  113 */   protected String xAxisUnits = "  ";
/*  114 */   protected String yAxisLegend = "  ";
/*  115 */   protected String yAxisUnits = "  ";
/*      */   
/*  117 */   protected boolean xZero = false;
/*  118 */   protected boolean yZero = false;
/*  119 */   protected boolean noXunits = true;
/*  120 */   protected boolean noYunits = true;
/*      */   
/*  122 */   protected double[] xAxisNo = new double[50];
/*  123 */   protected double[] yAxisNo = new double[50];
/*  124 */   protected String[] xAxisChar = new String[50];
/*  125 */   protected String[] yAxisChar = new String[50];
/*  126 */   protected int[] axisTicks = new int[50];
/*      */   
/*  128 */   protected static double dataFill = 3.0E200D;
/*      */   
/*      */ 
/*      */ 
/*      */   public Plot(double[][] data)
/*      */   {
/*  134 */     initialise(data);
/*      */   }
/*      */   
/*      */ 
/*      */   public Plot(double[] xdata, double[] ydata)
/*      */   {
/*  140 */     int xl = xdata.length;
/*  141 */     int yl = ydata.length;
/*  142 */     if (xl != yl) throw new IllegalArgumentException("x-data length is not equal to the y-data length");
/*  143 */     double[][] data = new double[2][xl];
/*  144 */     for (int i = 0; i < xl; i++) {
/*  145 */       data[0][i] = xdata[i];
/*  146 */       data[1][i] = ydata[i];
/*      */     }
/*  148 */     initialise(data);
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */   private void initialise(double[][] cdata)
/*      */   {
/*  155 */     this.nCurves = (cdata.length / 2);
/*      */     
/*      */ 
/*  158 */     this.nPoints = new int[this.nCurves];
/*  159 */     this.lineOpt = new int[this.nCurves];
/*  160 */     this.dashLength = new int[this.nCurves];
/*  161 */     this.trimOpt = new boolean[this.nCurves];
/*  162 */     this.minMaxOpt = new boolean[this.nCurves];
/*  163 */     this.pointOpt = new int[this.nCurves];
/*  164 */     this.pointSize = new int[this.nCurves];
/*  165 */     this.errorBar = new boolean[this.nCurves];
/*      */     
/*      */ 
/*  168 */     this.nmPoints = 0;
/*  169 */     int ll = 0;
/*  170 */     for (int i = 0; i < 2 * this.nCurves; i++) {
/*  171 */       if ((ll = cdata[i].length) > this.nmPoints) { this.nmPoints = ll;
/*      */       }
/*      */     }
/*      */     
/*  175 */     this.data = new double[2 * this.nCurves][this.nmPoints];
/*  176 */     this.copy = new double[2 * this.nCurves][this.nmPoints];
/*  177 */     this.errors = new double[this.nCurves][this.nmPoints];
/*  178 */     this.errorsCopy = new double[this.nCurves][this.nmPoints];
/*      */     
/*      */ 
/*      */ 
/*      */ 
/*  183 */     int k = 0;int l1 = 0;int l2 = 0;
/*  184 */     boolean testlen = true;
/*  185 */     for (int i = 0; i < this.nCurves; i++) {
/*  186 */       k = 2 * i;
/*  187 */       testlen = true;
/*  188 */       l1 = cdata[k].length;
/*  189 */       l2 = cdata[(k + 1)].length;
/*  190 */       if (l1 != l2) throw new IllegalArgumentException("an x and y array length differ");
/*  191 */       this.nPoints[i] = l1;
/*      */     }
/*      */     
/*      */ 
/*      */ 
/*  196 */     k = 0;
/*  197 */     boolean testopt = true;
/*  198 */     for (int i = 0; i < this.nCurves; i++) {
/*  199 */       testlen = true;
/*  200 */       l1 = this.nPoints[i];
/*  201 */       while (testlen) {
/*  202 */         if (l1 < 0) throw new IllegalArgumentException("curve array index  " + k + ": blank array");
/*  203 */         if (cdata[k][(l1 - 1)] == dataFill) {
/*  204 */           if (cdata[(k + 1)][(l1 - 1)] == dataFill) {
/*  205 */             l1--;
/*  206 */             testopt = false;
/*      */           }
/*      */           else {
/*  209 */             testlen = false;
/*      */           }
/*      */         }
/*      */         else {
/*  213 */           testlen = false;
/*      */         }
/*      */       }
/*  216 */       this.nPoints[i] = l1;
/*  217 */       k += 2;
/*      */     }
/*      */     
/*      */ 
/*  221 */     k = 0;
/*  222 */     for (int i = 0; i < this.nCurves; i++) {
/*  223 */       double[][] xxx = new double[2][this.nPoints[i]];
/*  224 */       for (int j = 0; j < this.nPoints[i]; j++) {
/*  225 */         xxx[0][j] = cdata[k][j];
/*  226 */         xxx[1][j] = cdata[(k + 1)][j];
/*      */       }
/*  228 */       xxx = doubleSelectionSort(xxx);
/*  229 */       for (int j = 0; j < this.nPoints[i]; j++) {
/*  230 */         cdata[k][j] = xxx[0][j];
/*  231 */         cdata[(k + 1)][j] = xxx[1][j];
/*      */       }
/*  233 */       k += 2;
/*      */     }
/*      */     
/*      */ 
/*  237 */     k = 0;
/*  238 */     int kk = 1;
/*  239 */     for (int i = 0; i < this.nCurves; i++)
/*      */     {
/*      */ 
/*  242 */       int rev = 1;
/*  243 */       for (int j = 1; j < this.nPoints[i]; j++) {
/*  244 */         if (cdata[k][j] < cdata[k][(j - 1)]) rev++;
/*      */       }
/*  246 */       if (rev == this.nPoints[i]) {
/*  247 */         double[] hold = new double[this.nPoints[i]];
/*  248 */         for (int j = 0; j < this.nPoints[i]; j++) hold[j] = cdata[k][j];
/*  249 */         for (int j = 0; j < this.nPoints[i]; j++) cdata[k][j] = hold[(this.nPoints[i] - j - 1)];
/*  250 */         for (int j = 0; j < this.nPoints[i]; j++) hold[j] = cdata[(k + 1)][j];
/*  251 */         for (int j = 0; j < this.nPoints[i]; j++) { cdata[(k + 1)][j] = hold[(this.nPoints[i] - j - 1)];
/*      */         }
/*      */       }
/*      */       
/*  255 */       for (int j = 0; j < this.nPoints[i]; j++) {
/*  256 */         this.data[k][j] = cdata[k][j];
/*  257 */         this.data[(k + 1)][j] = cdata[(k + 1)][j];
/*  258 */         this.copy[k][j] = cdata[k][j];
/*  259 */         this.copy[(k + 1)][j] = cdata[(k + 1)][j];
/*      */       }
/*      */       
/*  262 */       this.lineOpt[i] = 1;
/*  263 */       this.dashLength[i] = 5;
/*  264 */       this.trimOpt[i] = false;
/*  265 */       if (this.lineOpt[i] == 1) this.trimOpt[i] = true;
/*  266 */       this.minMaxOpt[i] = true;
/*  267 */       this.pointSize[i] = 6;
/*  268 */       this.errorBar[i] = false;
/*  269 */       this.pointOpt[i] = kk;
/*  270 */       k += 2;
/*  271 */       kk++;
/*  272 */       if (kk > this.npTypes) { kk = 1;
/*      */       }
/*      */     }
/*      */   }
/*      */   
/*      */   public static double[][] doubleSelectionSort(double[][] aa)
/*      */   {
/*  279 */     int index = 0;
/*  280 */     int lastIndex = -1;
/*  281 */     int n = aa[0].length;
/*  282 */     double holdx = 0.0D;
/*  283 */     double holdy = 0.0D;
/*  284 */     double[][] bb = new double[2][n];
/*  285 */     for (int i = 0; i < n; i++) {
/*  286 */       bb[0][i] = aa[0][i];
/*  287 */       bb[1][i] = aa[1][i];
/*      */     }
/*      */     
/*      */ 
/*  291 */     while (lastIndex != n - 1) {
/*  292 */       index = lastIndex + 1;
/*  293 */       for (int i = lastIndex + 2; i < n; i++) {
/*  294 */         if (bb[0][i] < bb[0][index]) {
/*  295 */           index = i;
/*      */         }
/*      */       }
/*  298 */       lastIndex++;
/*  299 */       holdx = bb[0][index];
/*  300 */       bb[0][index] = bb[0][lastIndex];
/*  301 */       bb[0][lastIndex] = holdx;
/*  302 */       holdy = bb[1][index];
/*  303 */       bb[1][index] = bb[1][lastIndex];
/*  304 */       bb[1][lastIndex] = holdy;
/*      */     }
/*      */     
/*  307 */     return bb;
/*      */   }
/*      */   
/*      */ 
/*      */   public static double[][] data(int n, int m)
/*      */   {
/*  313 */     double[][] d = new double[2 * n][m];
/*  314 */     for (int i = 0; i < 2 * n; i++) {
/*  315 */       for (int j = 0; j < m; j++) {
/*  316 */         d[i][j] = dataFill;
/*      */       }
/*      */     }
/*  319 */     return d;
/*      */   }
/*      */   
/*      */   public static void setDataFillValue(double dataFill)
/*      */   {
/*  324 */     dataFill = dataFill;
/*      */   }
/*      */   
/*      */   public static double getDataFillValue()
/*      */   {
/*  329 */     return dataFill;
/*      */   }
/*      */   
/*      */   public void setGraphTitle(String graphTitle)
/*      */   {
/*  334 */     this.graphTitle = graphTitle;
/*      */   }
/*      */   
/*      */   public void setGraphTitle2(String graphTitle2)
/*      */   {
/*  339 */     this.graphTitle2 = graphTitle2;
/*      */   }
/*      */   
/*      */   public void setXaxisLegend(String xAxisLegend)
/*      */   {
/*  344 */     this.xAxisLegend = xAxisLegend;
/*      */   }
/*      */   
/*      */   public void setYaxisLegend(String yAxisLegend)
/*      */   {
/*  349 */     this.yAxisLegend = yAxisLegend;
/*      */   }
/*      */   
/*      */   public void setXaxisUnitsName(String xAxisUnits)
/*      */   {
/*  354 */     this.xAxisUnits = xAxisUnits;
/*  355 */     this.noXunits = false;
/*      */   }
/*      */   
/*      */   public void setYaxisUnitsName(String yAxisUnits)
/*      */   {
/*  360 */     this.yAxisUnits = yAxisUnits;
/*  361 */     this.noYunits = false;
/*      */   }
/*      */   
/*      */   public int getXaxisLen()
/*      */   {
/*  366 */     return this.xLen;
/*      */   }
/*      */   
/*      */   public int getYaxisLen()
/*      */   {
/*  371 */     return this.yLen;
/*      */   }
/*      */   
/*      */   public int getXlow()
/*      */   {
/*  376 */     return this.xBot;
/*      */   }
/*      */   
/*      */   public int getYhigh()
/*      */   {
/*  381 */     return this.yTop;
/*      */   }
/*      */   
/*      */   public int[] getPointsize()
/*      */   {
/*  386 */     return this.pointSize;
/*      */   }
/*      */   
/*      */   public int[] getDashlength()
/*      */   {
/*  391 */     return this.dashLength;
/*      */   }
/*      */   
/*      */   public double getXlowFac()
/*      */   {
/*  396 */     return 1.0D - this.xLowFac;
/*      */   }
/*      */   
/*      */   public double getYlowFac()
/*      */   {
/*  401 */     return 1.0D - this.yLowFac;
/*      */   }
/*      */   
/*      */   public double getXmin()
/*      */   {
/*  406 */     return this.xMin;
/*      */   }
/*      */   
/*      */   public double getXmax()
/*      */   {
/*  411 */     return this.xMax;
/*      */   }
/*      */   
/*      */   public double getYmin()
/*      */   {
/*  416 */     return this.yMin;
/*      */   }
/*      */   
/*      */   public double getYmax()
/*      */   {
/*  421 */     return this.yMax;
/*      */   }
/*      */   
/*      */   public int[] getLine()
/*      */   {
/*  426 */     return this.lineOpt;
/*      */   }
/*      */   
/*      */   public int[] getPoint()
/*      */   {
/*  431 */     return this.pointOpt;
/*      */   }
/*      */   
/*      */   public int getNiPoints()
/*      */   {
/*  436 */     return this.niPoints;
/*      */   }
/*      */   
/*      */   public int getFontSize()
/*      */   {
/*  441 */     return this.fontSize;
/*      */   }
/*      */   
/*      */   public void setXaxisLen(int xLen)
/*      */   {
/*  446 */     this.xLen = xLen;
/*  447 */     update();
/*      */   }
/*      */   
/*      */   public void setYaxisLen(int yLen)
/*      */   {
/*  452 */     this.yLen = yLen;
/*  453 */     update();
/*      */   }
/*      */   
/*      */   public void setXlow(int xBot)
/*      */   {
/*  458 */     this.xBot = xBot;
/*  459 */     update();
/*      */   }
/*      */   
/*      */   public void setYhigh(int yTop)
/*      */   {
/*  464 */     this.yTop = yTop;
/*  465 */     update();
/*      */   }
/*      */   
/*      */   public void setXlowFac(double xLowFac)
/*      */   {
/*  470 */     this.xLowFac = (1.0D - xLowFac);
/*      */   }
/*      */   
/*      */   public void setYlowFac(double yLowFac)
/*      */   {
/*  475 */     this.yLowFac = (1.0D - yLowFac);
/*      */   }
/*      */   
/*      */   public void setNoXoffset(boolean noXoffset)
/*      */   {
/*  480 */     this.noXoffset = noXoffset;
/*      */   }
/*      */   
/*      */   public void setNoYoffset(boolean noYoffset)
/*      */   {
/*  485 */     this.noYoffset = noYoffset;
/*      */   }
/*      */   
/*      */   public void setNoOffset(boolean nooffset)
/*      */   {
/*  490 */     this.noXoffset = nooffset;
/*  491 */     this.noYoffset = nooffset;
/*      */   }
/*      */   
/*      */   public boolean getNoXoffset()
/*      */   {
/*  496 */     return this.noXoffset;
/*      */   }
/*      */   
/*      */   public boolean getNoYoffset()
/*      */   {
/*  501 */     return this.noYoffset;
/*      */   }
/*      */   
/*      */   protected void update()
/*      */   {
/*  506 */     this.xTop = (this.xBot + this.xLen);
/*  507 */     this.yBot = (this.yTop + this.yLen);
/*      */   }
/*      */   
/*      */   public void setLine(int[] lineOpt)
/*      */   {
/*  512 */     int n = lineOpt.length;
/*  513 */     if (n != this.nCurves) throw new IllegalArgumentException("input array of wrong length");
/*  514 */     for (int i = 0; i < n; i++) if ((lineOpt[i] < 0) || (lineOpt[i] > 4)) throw new IllegalArgumentException("lineOpt must be 0, 1, 2, 3 or 4");
/*  515 */     this.lineOpt = lineOpt;
/*      */     
/*      */ 
/*  518 */     for (int i = 0; i < this.lineOpt.length; i++) {
/*  519 */       if ((this.lineOpt[i] == 1) || (this.lineOpt[i] == 2))
/*      */       {
/*  521 */         boolean test0 = false;
/*  522 */         for (int j = 1; j < this.nPoints[i]; j++) {
/*  523 */           if (this.data[i][j] < this.data[i][(j - 1)]) test0 = true;
/*      */         }
/*  525 */         if (test0)
/*      */         {
/*  527 */           int rev = 1;
/*  528 */           for (int j = 1; j < this.nPoints[i]; j++) {
/*  529 */             if (this.data[(2 * i)][j] > this.data[(2 * i)][(j - 1)]) rev++;
/*      */           }
/*  531 */           if (rev == this.nPoints[i]) {
/*  532 */             lineOpt[i] = (-lineOpt[i]);
/*      */           }
/*      */           else
/*      */           {
/*  536 */             rev = 1;
/*  537 */             for (int j = 1; j < this.nPoints[i]; j++) {
/*  538 */               if (this.data[(2 * i)][j] < this.data[(2 * i)][(j - 1)]) rev++;
/*      */             }
/*  540 */             if (rev == this.nPoints[i])
/*      */             {
/*  542 */               double[] hold = new double[this.nPoints[i]];
/*  543 */               for (int j = 0; j < this.nPoints[i]; j++) hold[j] = this.data[i][j];
/*  544 */               for (int j = 0; j < this.nPoints[i]; j++) this.data[i][j] = hold[(this.nPoints[i] - j - 1)];
/*  545 */               for (int j = 0; j < this.nPoints[i]; j++) hold[j] = this.data[(2 * i)][j];
/*  546 */               for (int j = 0; j < this.nPoints[i]; j++) this.data[(2 * i)][j] = hold[(this.nPoints[i] - j - 1)];
/*  547 */               this.lineOpt[i] = (-lineOpt[i]);
/*      */             }
/*      */             else {
/*  550 */               System.out.println("Curve " + i + " will not support interpolation");
/*  551 */               System.out.println("Straight connecting line option used");
/*  552 */               if (this.lineOpt[i] == 1) this.lineOpt[i] = 3;
/*  553 */               if (this.lineOpt[i] == 2) this.lineOpt[i] = 4;
/*      */             }
/*      */           }
/*      */         }
/*      */       }
/*      */     }
/*      */   }
/*      */   
/*      */   public void setLine(int slineOpt)
/*      */   {
/*  563 */     if ((slineOpt < 0) || (slineOpt > 3)) throw new IllegalArgumentException("lineOpt must be 0, 1, 2 or 3");
/*  564 */     for (int i = 0; i < this.nCurves; i++) this.lineOpt[i] = slineOpt;
/*      */   }
/*      */   
/*      */   public void setDashLength(int[] dashLength)
/*      */   {
/*  569 */     if (dashLength.length != this.nCurves) throw new IllegalArgumentException("input array of wrong length");
/*  570 */     this.dashLength = dashLength;
/*      */   }
/*      */   
/*      */   public void setDashLength(int sdashLength)
/*      */   {
/*  575 */     for (int i = 0; i < this.nCurves; i++) this.dashLength[i] = sdashLength;
/*      */   }
/*      */   
/*      */   public void setPoint(int[] pointOpt)
/*      */   {
/*  580 */     int n = pointOpt.length;
/*  581 */     if (n != this.nCurves) throw new IllegalArgumentException("input array of wrong length");
/*  582 */     for (int i = 0; i < n; i++) if ((pointOpt[i] < 0) || (pointOpt[i] > 8)) throw new IllegalArgumentException("pointOpt must be 0, 1, 2, 3, 4, 5, 6, 7, or 8");
/*  583 */     this.pointOpt = pointOpt;
/*      */   }
/*      */   
/*      */   public void setPoint(int spointOpt)
/*      */   {
/*  588 */     if ((spointOpt < 0) || (spointOpt > 8)) throw new IllegalArgumentException("pointOpt must be 0, 1, 2, 3, 4, 5, 6, 7, or 8");
/*  589 */     for (int i = 0; i < this.nCurves; i++) this.pointOpt[i] = spointOpt;
/*      */   }
/*      */   
/*      */   public void setPointSize(int[] mpointSize)
/*      */   {
/*  594 */     if (mpointSize.length != this.nCurves) throw new IllegalArgumentException("input array of wrong length");
/*  595 */     for (int i = 0; i < this.nCurves; i++) {
/*  596 */       if (mpointSize[i] != mpointSize[i] / 2 * 2) mpointSize[i] += 1;
/*  597 */       this.pointSize[i] = mpointSize[i];
/*      */     }
/*      */   }
/*      */   
/*      */   public void setPointSize(int spointSize)
/*      */   {
/*  603 */     if (spointSize % 2 != 0) spointSize++;
/*  604 */     for (int i = 0; i < this.nCurves; i++) { this.pointSize[i] = spointSize;
/*      */     }
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */   public void setErrorBars(int nc, double[] err)
/*      */   {
/*  612 */     if (err.length != this.nPoints[nc]) throw new IllegalArgumentException("input array of wrong length");
/*  613 */     this.errorBar[nc] = true;
/*  614 */     for (int i = 0; i < this.nPoints[nc]; i++) {
/*  615 */       this.errors[nc][i] = err[i];
/*  616 */       this.errorsCopy[nc][i] = err[i];
/*      */     }
/*      */   }
/*      */   
/*      */   public void setNiPoints(int niPoints)
/*      */   {
/*  622 */     this.niPoints = niPoints;
/*      */   }
/*      */   
/*      */   public void setFontSize(int fontSize)
/*      */   {
/*  627 */     this.fontSize = fontSize;
/*      */   }
/*      */   
/*      */   public void setTrimOpt(boolean[] trim)
/*      */   {
/*  632 */     this.trimOpt = trim;
/*      */   }
/*      */   
/*      */   public void setMinMaxOpt(boolean[] minmax)
/*      */   {
/*  637 */     this.minMaxOpt = minmax;
/*      */   }
/*      */   
/*      */   public static int scale(double mmin, double mmax)
/*      */   {
/*  642 */     int fac = 0;
/*  643 */     double big = 0.0D;
/*  644 */     boolean test = false;
/*      */     
/*  646 */     if ((mmin >= 0.0D) && (mmax > 0.0D)) {
/*  647 */       big = mmax;
/*  648 */       test = true;
/*      */ 
/*      */     }
/*  651 */     else if ((mmin < 0.0D) && (mmax <= 0.0D)) {
/*  652 */       big = -mmin;
/*  653 */       test = true;
/*      */ 
/*      */     }
/*  656 */     else if ((mmax > 0.0D) && (mmin < 0.0D)) {
/*  657 */       big = Math.max(mmax, -mmin);
/*  658 */       test = true;
/*      */     }
/*      */     
/*      */ 
/*      */ 
/*  663 */     if (test) {
/*  664 */       if (big > 100.0D) {
/*  665 */         while (big > 1.0D) {
/*  666 */           big /= 10.0D;
/*  667 */           fac--;
/*      */         }
/*      */       }
/*  670 */       if (big <= 0.01D) {
/*  671 */         while (big <= 0.1D) {
/*  672 */           big *= 10.0D;
/*  673 */           fac++;
/*      */         }
/*      */       }
/*      */     }
/*  677 */     return fac;
/*      */   }
/*      */   
/*      */ 
/*      */   public static void limits(double low, double high, double lowfac, double[] limits)
/*      */   {
/*  683 */     double facl = 1.0D;
/*  684 */     double fach = 1.0D;
/*  685 */     if (Math.abs(low) < 1.0D) facl = 10.0D;
/*  686 */     if (Math.abs(low) < 0.1D) facl = 100.0D;
/*  687 */     if (Math.abs(high) < 1.0D) fach = 10.0D;
/*  688 */     if (Math.abs(high) < 0.1D) { fach = 100.0D;
/*      */     }
/*  690 */     double ld = Math.floor(10.0D * low * facl) / facl;
/*  691 */     double hd = Math.ceil(10.0D * high * fach) / fach;
/*      */     
/*  693 */     if ((ld >= 0.0D) && (hd > 0.0D) && 
/*  694 */       (ld < lowfac * hd)) {
/*  695 */       ld = 0.0D;
/*      */     }
/*      */     
/*  698 */     if ((ld < 0.0D) && (hd <= 0.0D) && 
/*  699 */       (-hd <= -lowfac * ld)) {
/*  700 */       hd = 0.0D;
/*      */     }
/*      */     
/*  703 */     limits[0] = (ld / 10.0D);
/*  704 */     limits[1] = (hd / 10.0D);
/*      */   }
/*      */   
/*      */ 
/*      */   public static double offset(double low, double high)
/*      */   {
/*  710 */     double diff = high - low;
/*  711 */     double sh = Fmath.sign(high);
/*  712 */     double sl = Fmath.sign(low);
/*  713 */     double offset = 0.0D;
/*  714 */     int eh = 0;int ed = 0;
/*      */     
/*  716 */     if (sh == sl) {
/*  717 */       ed = (int)Math.floor(Fmath.log10(diff));
/*  718 */       if (sh == 1.0D) {
/*  719 */         eh = (int)Math.floor(Fmath.log10(high));
/*  720 */         if (eh - ed > 1) offset = Math.floor(low * Math.pow(10.0D, -ed)) * Math.pow(10.0D, ed);
/*      */       }
/*      */       else {
/*  723 */         eh = (int)Math.floor(Fmath.log10(Math.abs(low)));
/*  724 */         if (eh - ed > 1) offset = Math.floor(high * Math.pow(10.0D, -ed)) * Math.pow(10.0D, ed);
/*      */       }
/*      */     }
/*  727 */     return offset;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */   public void axesScaleOffset()
/*      */   {
/*  734 */     double[] limit = new double[2];
/*      */     
/*      */ 
/*  737 */     int k = 0;
/*  738 */     for (int i = 0; i < this.nCurves; i++) {
/*  739 */       for (int j = 0; j < this.nPoints[i]; j++) {
/*  740 */         this.data[k][j] = this.copy[k][j];
/*  741 */         this.data[(k + 1)][j] = this.copy[(k + 1)][j];
/*  742 */         this.errors[i][j] = this.errorsCopy[i][j];
/*  743 */         if (this.errorBar[i] != 0) this.errors[i][j] += this.data[(k + 1)][j];
/*      */       }
/*  745 */       k += 2;
/*      */     }
/*      */     
/*      */ 
/*  749 */     minMax();
/*      */     
/*      */ 
/*  752 */     if (!this.noXoffset) this.xOffset = offset(this.xMin, this.xMax);
/*  753 */     if (this.xOffset != 0.0D) {
/*  754 */       k = 0;
/*  755 */       for (int i = 0; i < this.nCurves; i++) {
/*  756 */         for (int j = 0; j < this.nPoints[i]; j++) {
/*  757 */           this.data[k][j] -= this.xOffset;
/*      */         }
/*  759 */         k += 2;
/*      */       }
/*  761 */       this.xMin -= this.xOffset;
/*  762 */       this.xMax -= this.xOffset;
/*      */     }
/*      */     
/*      */ 
/*  766 */     if (!this.noYoffset) this.yOffset = offset(this.yMin, this.yMax);
/*  767 */     if (this.yOffset != 0.0D) {
/*  768 */       k = 1;
/*  769 */       for (int i = 0; i < this.nCurves; i++) {
/*  770 */         for (int j = 0; j < this.nPoints[i]; j++) {
/*  771 */           this.data[k][j] -= this.yOffset;
/*  772 */           if (this.errorBar[i] != 0) this.errors[i][j] -= this.yOffset;
/*      */         }
/*  774 */         k += 2;
/*      */       }
/*  776 */       this.yMin -= this.yOffset;
/*  777 */       this.yMax -= this.yOffset;
/*      */     }
/*      */     
/*      */ 
/*  781 */     this.xFac = scale(this.xMin, this.xMax);
/*  782 */     if (this.xFac != 0) {
/*  783 */       k = 0;
/*  784 */       for (int i = 0; i < this.nCurves; i++) {
/*  785 */         for (int j = 0; j < this.nPoints[i]; j++) {
/*  786 */           this.data[k][j] *= Math.pow(10.0D, this.xFac + 1);
/*      */         }
/*  788 */         k += 2;
/*      */       }
/*  790 */       this.xMin *= Math.pow(10.0D, this.xFac + 1);
/*  791 */       this.xMax *= Math.pow(10.0D, this.xFac + 1);
/*      */     }
/*      */     
/*      */ 
/*  795 */     this.yFac = scale(this.yMin, this.yMax);
/*  796 */     if (this.yFac != 0) {
/*  797 */       k = 1;
/*  798 */       for (int i = 0; i < this.nCurves; i++) {
/*  799 */         for (int j = 0; j < this.nPoints[i]; j++) {
/*  800 */           this.data[k][j] *= Math.pow(10.0D, this.yFac + 1);
/*  801 */           if (this.errorBar[i] != 0) this.errors[i][j] *= Math.pow(10.0D, this.yFac + 1);
/*      */         }
/*  803 */         k += 2;
/*      */       }
/*  805 */       this.yMin *= Math.pow(10.0D, this.yFac + 1);
/*  806 */       this.yMax *= Math.pow(10.0D, this.yFac + 1);
/*      */     }
/*      */     
/*      */ 
/*      */ 
/*  811 */     limits(this.xMin, this.xMax, this.xLowFac, limit);
/*  812 */     this.xLow = limit[0];
/*  813 */     this.xHigh = limit[1];
/*  814 */     if ((this.xLow < 0.0D) && (this.xHigh > 0.0D)) { this.xZero = true;
/*      */     }
/*  816 */     limits(this.yMin, this.yMax, this.yLowFac, limit);
/*  817 */     this.yLow = limit[0];
/*  818 */     this.yHigh = limit[1];
/*  819 */     if ((this.yLow < 0.0D) && (this.yHigh > 0.0D)) { this.yZero = true;
/*      */     }
/*      */     
/*      */ 
/*  823 */     this.xTicks = ticks(this.xLow, this.xHigh, this.xAxisNo, this.xAxisChar);
/*  824 */     this.xHigh = this.xAxisNo[(this.xTicks - 1)];
/*  825 */     if (this.xLow != this.xAxisNo[0]) {
/*  826 */       if (this.xOffset != 0.0D) {
/*  827 */         this.xOffset = (this.xOffset - this.xLow + this.xAxisNo[0]);
/*      */       }
/*  829 */       this.xLow = this.xAxisNo[0];
/*      */     }
/*      */     
/*  832 */     this.yTicks = ticks(this.yLow, this.yHigh, this.yAxisNo, this.yAxisChar);
/*  833 */     this.yHigh = this.yAxisNo[(this.yTicks - 1)];
/*  834 */     if (this.yLow != this.yAxisNo[0]) {
/*  835 */       if (this.yOffset != 0.0D) {
/*  836 */         this.yOffset = (this.yOffset - this.yLow + this.yAxisNo[0]);
/*      */       }
/*  838 */       this.yLow = this.yAxisNo[0];
/*      */     }
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public static int ticks(double low, double high, double[] tickval, String[] tickchar)
/*      */   {
/*  848 */     int[] trunc = { 1, 1, 1, 2, 3 };
/*  849 */     double[] scfac1 = { 1.0D, 10.0D, 1.0D, 0.1D, 0.01D };
/*  850 */     double[] scfac2 = { 1.0D, 1.0D, 0.1D, 0.01D, 0.001D };
/*      */     
/*  852 */     double rmax = Math.abs(high);
/*  853 */     double temp = Math.abs(low);
/*  854 */     if (temp > rmax) rmax = temp;
/*  855 */     int range = 0;
/*  856 */     if (rmax <= 100.0D) {
/*  857 */       range = 1;
/*      */     }
/*  859 */     if (rmax <= 10.0D) {
/*  860 */       range = 2;
/*      */     }
/*  862 */     if (rmax <= 1.0D) {
/*  863 */       range = 3;
/*      */     }
/*  865 */     if (rmax <= 0.1D) {
/*  866 */       range = 4;
/*      */     }
/*  868 */     if ((rmax > 100.0D) || (rmax < 0.01D)) { range = 0;
/*      */     }
/*      */     
/*  871 */     double inc = 0.0D;
/*  872 */     double bot = 0.0D;
/*  873 */     double top = 0.0D;
/*  874 */     int sgn = 0;
/*  875 */     int dirn = 0;
/*  876 */     if ((high > 0.0D) && (low >= 0.0D)) {
/*  877 */       inc = Math.ceil((high - low) / scfac1[range]) * scfac2[range];
/*  878 */       dirn = 1;
/*  879 */       bot = low;
/*  880 */       top = high;
/*  881 */       sgn = 1;
/*      */ 
/*      */     }
/*  884 */     else if ((high <= 0.0D) && (low < 0.0D)) {
/*  885 */       inc = Math.ceil((high - low) / scfac1[range]) * scfac2[range];
/*  886 */       dirn = -1;
/*  887 */       bot = high;
/*  888 */       top = low;
/*  889 */       sgn = -1;
/*      */     }
/*      */     else {
/*  892 */       double up = Math.abs(Math.ceil(high));
/*  893 */       double down = Math.abs(Math.floor(low));
/*  894 */       int np = 0;
/*  895 */       if (up >= down) {
/*  896 */         dirn = 2;
/*  897 */         np = (int)Math.rint(10.0D * up / (up + down));
/*  898 */         inc = Math.ceil(high * 10.0D / np / scfac1[range]) * scfac2[range];
/*  899 */         bot = 0.0D;
/*  900 */         top = high;
/*  901 */         sgn = 1;
/*      */       }
/*      */       else {
/*  904 */         dirn = -2;
/*  905 */         np = (int)Math.rint(10.0D * down / (up + down));
/*  906 */         inc = Math.ceil(Math.abs(low * 10.0D / np) / scfac1[range]) * scfac2[range];
/*  907 */         bot = 0.0D;
/*  908 */         top = low;
/*  909 */         sgn = -1;
/*      */       }
/*      */     }
/*      */     
/*      */ 
/*  914 */     int nticks = 1;
/*  915 */     double sum = bot;
/*  916 */     boolean test = true;
/*  917 */     while (test) {
/*  918 */       sum += sgn * inc;
/*  919 */       nticks++;
/*  920 */       if (Math.abs(sum) >= Math.abs(top)) { test = false;
/*      */       }
/*      */     }
/*      */     
/*  924 */     int npExtra = 0;
/*  925 */     double[] ttickval = null;
/*  926 */     switch (dirn) {
/*  927 */     case 1:  ttickval = new double[nticks];
/*  928 */       tickval[0] = Fmath.truncate(low, trunc[range]);
/*  929 */       for (int i = 1; i < nticks; i++) {
/*  930 */         tickval[i] = Fmath.truncate(tickval[(i - 1)] + inc, trunc[range]);
/*      */       }
/*  932 */       break;
/*  933 */     case -1:  ttickval = new double[nticks];
/*  934 */       ttickval[0] = Fmath.truncate(high, trunc[range]);
/*  935 */       for (int i = 1; i < nticks; i++) {
/*  936 */         ttickval[i] = Fmath.truncate(ttickval[(i - 1)] - inc, trunc[range]);
/*      */       }
/*  938 */       ttickval = Fmath.reverseArray(ttickval);
/*  939 */       for (int i = 0; i < nticks; i++) tickval[i] = ttickval[i];
/*  940 */       break;
/*  941 */     case 2:  npExtra = (int)Math.ceil(-low / inc);
/*  942 */       nticks += npExtra;
/*  943 */       ttickval = new double[nticks];
/*  944 */       tickval[0] = Fmath.truncate(-npExtra * inc, trunc[range]);
/*  945 */       for (int i = 1; i < nticks; i++) {
/*  946 */         tickval[i] = Fmath.truncate(tickval[(i - 1)] + inc, trunc[range]);
/*      */       }
/*  948 */       break;
/*  949 */     case -2:  npExtra = (int)Math.ceil(high / inc);
/*  950 */       nticks += npExtra;
/*  951 */       ttickval = new double[nticks];
/*  952 */       ttickval[0] = Fmath.truncate(npExtra * inc, trunc[range]);
/*  953 */       for (int i = 1; i < nticks; i++) {
/*  954 */         ttickval[i] = Fmath.truncate(ttickval[(i - 1)] - inc, trunc[range]);
/*      */       }
/*  956 */       ttickval = Fmath.reverseArray(ttickval);
/*  957 */       for (int i = 0; i < nticks; i++) { tickval[i] = ttickval[i];
/*      */       }
/*      */     }
/*      */     
/*      */     
/*  962 */     ArrayMaths am = new ArrayMaths(tickval);
/*  963 */     double max = am.maximum();
/*  964 */     double min = Math.abs(am.minimum());
/*  965 */     boolean testZero = true;
/*  966 */     int counter = 0;
/*  967 */     while (testZero) {
/*  968 */       if ((Math.abs(tickval[counter]) < max * 1.0E-4D) || (Math.abs(tickval[counter]) < min * 1.0E-4D)) {
/*  969 */         tickval[counter] = 0.0D;
/*  970 */         testZero = false;
/*      */       }
/*      */       else {
/*  973 */         counter++;
/*  974 */         if (counter >= nticks) { testZero = false;
/*      */         }
/*      */       }
/*      */     }
/*      */     
/*  979 */     for (int i = 0; i < nticks; i++) {
/*  980 */       tickchar[i] = String.valueOf(tickval[i]);
/*  981 */       tickchar[i] = tickchar[i].trim();
/*      */     }
/*      */     
/*  984 */     return nticks;
/*      */   }
/*      */   
/*      */   public void minMax()
/*      */   {
/*  989 */     boolean test = true;
/*      */     
/*  991 */     int ii = 0;
/*  992 */     while (test) {
/*  993 */       if (this.minMaxOpt[ii] != 0) {
/*  994 */         test = false;
/*  995 */         this.xMin = this.data[(2 * ii)][0];
/*  996 */         this.xMax = this.data[(2 * ii)][0];
/*  997 */         this.yMin = this.data[(2 * ii + 1)][0];
/*  998 */         if (this.errorBar[ii] != 0) this.yMin = (2.0D * this.yMin - this.errors[ii][0]);
/*  999 */         this.yMax = this.data[(2 * ii + 1)][0];
/* 1000 */         if (this.errorBar[ii] != 0) this.yMax = this.errors[ii][0];
/*      */       }
/*      */       else {
/* 1003 */         ii++;
/* 1004 */         if (ii > this.nCurves) { throw new IllegalArgumentException("At least one curve must be included in the maximum/minimum calculation");
/*      */         }
/*      */       }
/*      */     }
/* 1008 */     int k = 0;
/* 1009 */     double yMint = 0.0D;double yMaxt = 0.0D;
/* 1010 */     for (int i = 0; i < this.nCurves; i++) {
/* 1011 */       if (this.minMaxOpt[i] != 0) {
/* 1012 */         for (int j = 0; j < this.nPoints[i]; j++) {
/* 1013 */           if (this.xMin > this.data[k][j]) this.xMin = this.data[k][j];
/* 1014 */           if (this.xMax < this.data[k][j]) this.xMax = this.data[k][j];
/* 1015 */           yMint = this.data[(k + 1)][j];
/* 1016 */           if (this.errorBar[i] != 0) yMint = 2.0D * yMint - this.errors[i][j];
/* 1017 */           if (this.yMin > yMint) this.yMin = yMint;
/* 1018 */           yMaxt = this.data[(k + 1)][j];
/* 1019 */           if (this.errorBar[i] != 0) yMaxt = this.errors[i][j];
/* 1020 */           if (this.yMax < yMaxt) this.yMax = yMaxt;
/*      */         }
/*      */       }
/* 1023 */       k += 2;
/*      */     }
/*      */     
/* 1026 */     if (this.xMin == this.xMax) {
/* 1027 */       if (this.xMin == 0.0D) {
/* 1028 */         this.xMin = 0.1D;
/* 1029 */         this.xMax = 0.1D;
/*      */ 
/*      */       }
/* 1032 */       else if (this.xMin < 0.0D) {
/* 1033 */         this.xMin *= 1.1D;
/*      */       }
/*      */       else {
/* 1036 */         this.xMax *= 1.1D;
/*      */       }
/*      */     }
/*      */     
/*      */ 
/* 1041 */     if (this.yMin == this.yMax) {
/* 1042 */       if (this.yMin == 0.0D) {
/* 1043 */         this.yMin = 0.1D;
/* 1044 */         this.yMax = 0.1D;
/*      */ 
/*      */       }
/* 1047 */       else if (this.yMin < 0.0D) {
/* 1048 */         this.yMin *= 1.1D;
/*      */       }
/*      */       else {
/* 1051 */         this.yMax *= 1.1D;
/*      */       }
/*      */     }
/*      */   }
/*      */   
/*      */ 
/*      */   protected static String offsetString(double offset)
/*      */   {
/* 1059 */     String stroffset = String.valueOf(offset);
/* 1060 */     String substr1 = "";String substr2 = "";String substr3 = "";
/* 1061 */     String zero = "0";
/* 1062 */     int posdot = stroffset.indexOf('.');
/* 1063 */     int posexp = stroffset.indexOf('E');
/*      */     
/* 1065 */     if (posexp == -1) {
/* 1066 */       return stroffset;
/*      */     }
/*      */     
/* 1069 */     substr1 = stroffset.substring(posexp + 1);
/* 1070 */     int n = Integer.parseInt(substr1);
/* 1071 */     substr1 = stroffset.substring(0, posexp);
/* 1072 */     if (n >= 0) {
/* 1073 */       for (int i = 0; i < n; i++) {
/* 1074 */         substr1 = substr1 + zero;
/*      */       }
/* 1076 */       return substr1;
/*      */     }
/*      */     
/* 1079 */     substr2 = substr1.substring(0, posdot + 1);
/* 1080 */     substr3 = substr1.substring(posdot + 1);
/* 1081 */     for (int i = 0; i < -n; i++) {
/* 1082 */       substr2 = substr1 + zero;
/*      */     }
/* 1084 */     substr2 = substr2 + substr3;
/* 1085 */     return substr2;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */   public boolean printCheck(boolean trim, int xoldpoint, int xnewpoint, int yoldpoint, int ynewpoint)
/*      */   {
/* 1093 */     boolean btest2 = true;
/*      */     
/* 1095 */     if (trim) {
/* 1096 */       if (xoldpoint < this.xBot) btest2 = false;
/* 1097 */       if (xoldpoint > this.xTop) btest2 = false;
/* 1098 */       if (xnewpoint < this.xBot) btest2 = false;
/* 1099 */       if (xnewpoint > this.xTop) btest2 = false;
/* 1100 */       if (yoldpoint > this.yBot) btest2 = false;
/* 1101 */       if (yoldpoint < this.yTop) btest2 = false;
/* 1102 */       if (ynewpoint > this.yBot) btest2 = false;
/* 1103 */       if (ynewpoint < this.yTop) { btest2 = false;
/*      */       }
/*      */     }
/* 1106 */     return btest2;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */   public void graph(Graphics g)
/*      */   {
/* 1113 */     g.setFont(new Font("serif", 0, this.fontSize));
/* 1114 */     FontMetrics fm = g.getFontMetrics();
/*      */     
/*      */ 
/* 1117 */     axesScaleOffset();
/*      */     
/*      */ 
/* 1120 */     String xoffstr = offsetString(this.xOffset);
/* 1121 */     String yoffstr = offsetString(this.yOffset);
/* 1122 */     String bunit1 = "  /( ";
/* 1123 */     String bunit2 = " )";
/* 1124 */     String bunit3 = "  / ";
/* 1125 */     String bunit4 = " ";
/* 1126 */     String bunit5 = " x 10";
/* 1127 */     String bunit6 = "10";
/* 1128 */     String nounit = " ";
/* 1129 */     String xbrack1 = bunit1;
/* 1130 */     String xbrack2 = bunit2;
/* 1131 */     String xbrack3 = bunit5;
/* 1132 */     if (this.xFac == 0) {
/* 1133 */       xbrack1 = bunit3;
/* 1134 */       xbrack2 = "";
/* 1135 */       xbrack3 = "";
/*      */     }
/* 1137 */     String ybrack1 = bunit1;
/* 1138 */     String ybrack2 = bunit2;
/* 1139 */     String ybrack3 = bunit5;
/* 1140 */     if (this.yFac == 0) {
/* 1141 */       ybrack1 = bunit3;
/* 1142 */       ybrack2 = "";
/* 1143 */       ybrack3 = "";
/*      */     }
/* 1145 */     if (this.noXunits) {
/* 1146 */       if (this.xFac == 0) {
/* 1147 */         xbrack1 = nounit;
/* 1148 */         xbrack2 = nounit;
/* 1149 */         xbrack3 = nounit;
/*      */       }
/*      */       else {
/* 1152 */         xbrack1 = bunit3;
/* 1153 */         xbrack2 = bunit4;
/* 1154 */         xbrack3 = bunit6;
/*      */       }
/*      */     }
/* 1157 */     if (this.noYunits) {
/* 1158 */       if (this.yFac == 0) {
/* 1159 */         ybrack1 = nounit;
/* 1160 */         ybrack2 = nounit;
/* 1161 */         ybrack3 = nounit;
/*      */       }
/*      */       else {
/* 1164 */         ybrack1 = bunit3;
/* 1165 */         ybrack2 = bunit4;
/* 1166 */         ybrack3 = bunit6;
/*      */       }
/*      */     }
/*      */     
/* 1170 */     double xLen = this.xTop - this.xBot;
/* 1171 */     double yLen = this.yBot - this.yTop;
/*      */     
/*      */ 
/* 1174 */     String sp = " + ";String sn = " - ";
/* 1175 */     String ss = sn;
/* 1176 */     g.drawString(this.graphTitle + " ", 15, 15);
/* 1177 */     g.drawString(this.graphTitle2 + " ", 15, 35);
/* 1178 */     if (this.xOffset < 0.0D) {
/* 1179 */       ss = sp;
/* 1180 */       this.xOffset = (-this.xOffset);
/*      */     }
/*      */     
/*      */ 
/* 1184 */     int sw = 0;
/* 1185 */     String ssx = "";String ssy = "";String sws1 = "";String sws2 = "";
/* 1186 */     if ((this.xFac == 0) && (this.xOffset == 0.0D)) {
/* 1187 */       g.drawString(this.xAxisLegend + xbrack1 + this.xAxisUnits + xbrack2, this.xBot - 4, this.yBot + 32);
/*      */ 
/*      */     }
/* 1190 */     else if (this.xOffset == 0.0D) {
/* 1191 */       ssx = this.xAxisLegend + xbrack1 + this.xAxisUnits + xbrack3;
/* 1192 */       sw = fm.stringWidth(ssx);
/* 1193 */       g.drawString(ssx, this.xBot - 4, this.yBot + 42);
/* 1194 */       sws1 = String.valueOf(-this.xFac - 1);
/* 1195 */       g.drawString(sws1, this.xBot - 4 + sw + 1, this.yBot + 32);
/* 1196 */       sw += fm.stringWidth(sws1);
/* 1197 */       g.drawString(xbrack2, this.xBot - 4 + sw + 1, this.yBot + 42);
/*      */ 
/*      */     }
/* 1200 */     else if (this.xFac == 0) {
/* 1201 */       g.drawString(this.xAxisLegend + ss + xoffstr + xbrack1 + this.xAxisUnits + xbrack2, this.xBot - 4, this.yBot + 30);
/*      */     }
/*      */     else {
/* 1204 */       ssx = this.xAxisLegend + ss + xoffstr + xbrack1 + this.xAxisUnits + xbrack3;
/* 1205 */       sw = fm.stringWidth(ssx);
/* 1206 */       g.drawString(ssx, this.xBot - 4, this.yBot + 37);
/* 1207 */       sws1 = String.valueOf(-this.xFac - 1);
/* 1208 */       g.drawString(sws1, this.xBot - 4 + sw + 1, this.yBot + 32);
/* 1209 */       sw += fm.stringWidth(sws1);
/* 1210 */       g.drawString(xbrack2, this.xBot - 4 + sw + 1, this.yBot + 37);
/*      */     }
/*      */     
/*      */ 
/*      */ 
/* 1215 */     ss = sn;
/* 1216 */     if (this.yOffset < 0.0D) {
/* 1217 */       ss = sp;
/* 1218 */       this.yOffset = (-this.yOffset);
/*      */     }
/*      */     
/* 1221 */     if ((this.yFac == 0) && (this.yOffset == 0.0D)) {
/* 1222 */       g.drawString(this.yAxisLegend + " ", 15, this.yTop - 25);
/* 1223 */       g.drawString(ybrack1 + this.yAxisUnits + ybrack2, 15, this.yTop - 10);
/*      */ 
/*      */     }
/* 1226 */     else if (this.yOffset == 0.0D) {
/* 1227 */       g.drawString(this.yAxisLegend, 15, this.yTop - 35);
/* 1228 */       sws1 = ybrack1 + this.yAxisUnits + ybrack3;
/* 1229 */       g.drawString(sws1, 15, this.yTop - 15);
/* 1230 */       sw = fm.stringWidth(sws1);
/* 1231 */       sws2 = String.valueOf(-this.yFac - 1);
/* 1232 */       g.drawString(sws2, 15 + sw + 1, this.yTop - 20);
/* 1233 */       sw += fm.stringWidth(sws2);
/* 1234 */       g.drawString(ybrack2, 15 + sw + 1, this.yTop - 15);
/*      */ 
/*      */     }
/* 1237 */     else if (this.yFac == 0) {
/* 1238 */       g.drawString(this.yAxisLegend + ss + yoffstr, 15, this.yTop - 25);
/* 1239 */       g.drawString(ybrack1 + this.yAxisUnits + ybrack2, 15, this.yTop - 10);
/*      */     }
/*      */     else {
/* 1242 */       ssy = this.yAxisLegend + ss + yoffstr;
/* 1243 */       g.drawString(ssy, 15, this.yTop - 35);
/* 1244 */       sws1 = ybrack1 + this.yAxisUnits + ybrack3;
/* 1245 */       g.drawString(sws1, 15, this.yTop - 15);
/* 1246 */       sw = fm.stringWidth(sws1);
/* 1247 */       sws2 = String.valueOf(-this.yFac - 1);
/* 1248 */       g.drawString(sws2, 15 + sw + 1, this.yTop - 20);
/* 1249 */       sw += fm.stringWidth(sws2);
/* 1250 */       g.drawString(ybrack2, 15 + sw + 1, this.yTop - 15);
/*      */     }
/*      */     
/*      */ 
/*      */ 
/*      */ 
/* 1256 */     int zdif = 0;int zold = 0;int znew = 0;int zzer = 0;
/* 1257 */     double csstep = 0.0D;
/* 1258 */     double xdenom = this.xHigh - this.xLow;
/* 1259 */     double ydenom = this.yHigh - this.yLow;
/*      */     
/* 1261 */     g.drawLine(this.xBot, this.yBot, this.xTop, this.yBot);
/* 1262 */     g.drawLine(this.xBot, this.yTop, this.xTop, this.yTop);
/* 1263 */     g.drawLine(this.xBot, this.yBot, this.xBot, this.yTop);
/* 1264 */     g.drawLine(this.xTop, this.yBot, this.xTop, this.yTop);
/*      */     
/*      */ 
/*      */ 
/* 1268 */     if (this.xZero) {
/* 1269 */       zdif = 8;
/* 1270 */       zzer = this.xBot + (int)((0.0D - this.xLow) / xdenom * xLen);
/* 1271 */       g.drawLine(zzer, this.yTop, zzer, this.yTop + 8);
/* 1272 */       g.drawLine(zzer, this.yBot, zzer, this.yBot - 8);
/* 1273 */       zold = this.yTop;
/* 1274 */       while (zold + zdif < this.yBot) {
/* 1275 */         znew = zold + zdif;
/* 1276 */         g.drawLine(zzer, zold, zzer, znew);
/* 1277 */         zold = znew + zdif;
/*      */       }
/*      */     }
/*      */     
/* 1281 */     if (this.yZero) {
/* 1282 */       zdif = 8;
/* 1283 */       zzer = this.yBot - (int)((0.0D - this.yLow) / ydenom * yLen);
/* 1284 */       g.drawLine(this.xBot, zzer, this.xBot + 8, zzer);
/* 1285 */       g.drawLine(this.xTop, zzer, this.xTop - 8, zzer);
/* 1286 */       zold = this.xBot;
/* 1287 */       while (zold + zdif < this.xTop) {
/* 1288 */         znew = zold + zdif;
/* 1289 */         g.drawLine(zold, zzer, znew, zzer);
/* 1290 */         zold = znew + zdif;
/*      */       }
/*      */     }
/*      */     
/*      */ 
/* 1295 */     int xt = 0;
/*      */     
/* 1297 */     for (int ii = 0; ii < this.xTicks; ii++)
/*      */     {
/* 1299 */       xt = this.xBot + (int)((this.xAxisNo[ii] - this.xLow) / xdenom * xLen);
/* 1300 */       g.drawLine(xt, this.yBot, xt, this.yBot - 8);
/* 1301 */       g.drawLine(xt, this.yTop, xt, this.yTop + 8);
/* 1302 */       g.drawString(this.xAxisChar[ii] + " ", xt - 4, this.yBot + 18);
/*      */     }
/*      */     
/* 1305 */     int yt = 0;
/* 1306 */     int yCharLenMax = this.yAxisChar[0].length();
/* 1307 */     for (int ii = 1; ii < this.yTicks; ii++) if (this.yAxisChar[ii].length() > yCharLenMax) yCharLenMax = this.yAxisChar[ii].length();
/* 1308 */     int shift = (yCharLenMax - 3) * 5;
/* 1309 */     double ytep = (-this.yTop + this.yBot) / (this.yTicks - 1);
/* 1310 */     for (int ii = 0; ii < this.yTicks; ii++)
/*      */     {
/* 1312 */       yt = this.yBot - (int)Math.round(ii * ytep);
/* 1313 */       yt = this.yBot - (int)((this.yAxisNo[ii] - this.yLow) / ydenom * yLen);
/* 1314 */       g.drawLine(this.xBot, yt, this.xBot + 8, yt);
/* 1315 */       g.drawLine(this.xTop, yt, this.xTop - 8, yt);
/* 1316 */       g.drawString(this.yAxisChar[ii] + " ", this.xBot - 30 - shift, yt + 4);
/*      */     }
/*      */     
/* 1319 */     int dsum = 0;
/* 1320 */     boolean dcheck = true;
/*      */     
/*      */ 
/* 1323 */     int kk = 0;
/* 1324 */     int xxp = 0;int yyp = 0;int yype = 0;
/* 1325 */     int xoldpoint = 0;int xnewpoint = 0;int yoldpoint = 0;int ynewpoint = 0;
/* 1326 */     int ps = 0;int psh = 0;int nxpoints = 0;
/* 1327 */     double[] ics = new double[this.niPoints];
/* 1328 */     boolean btest2 = true;
/*      */     
/* 1330 */     for (int i = 0; i < this.nCurves; i++)
/*      */     {
/* 1332 */       nxpoints = this.nPoints[i];
/* 1333 */       double[] xcs = new double[nxpoints];
/* 1334 */       double[] ycs = new double[nxpoints];
/*      */       
/* 1336 */       if ((this.lineOpt[i] == 1) || (this.lineOpt[i] == 2)) {
/* 1337 */         CubicSpline cs = new CubicSpline(this.nPoints[i]);
/* 1338 */         for (int ii = 0; ii < nxpoints; ii++) {
/* 1339 */           xcs[ii] = this.data[kk][ii];
/*      */         }
/* 1341 */         csstep = (xcs[(nxpoints - 1)] - xcs[0]) / (this.niPoints - 1);
/* 1342 */         ics[0] = xcs[0];
/* 1343 */         for (int ii = 1; ii < this.niPoints; ii++) {
/* 1344 */           ics[ii] = (ics[(ii - 1)] + csstep);
/*      */         }
/* 1346 */         ics[(this.niPoints - 1)] = xcs[(nxpoints - 1)];
/* 1347 */         for (int ii = 0; ii < nxpoints; ii++) {
/* 1348 */           ycs[ii] = this.data[(kk + 1)][ii];
/*      */         }
/*      */         
/* 1351 */         cs.resetData(xcs, ycs);
/* 1352 */         cs.calcDeriv();
/* 1353 */         xoldpoint = this.xBot + (int)((xcs[0] - this.xLow) / xdenom * xLen);
/* 1354 */         yoldpoint = this.yBot - (int)((ycs[0] - this.yLow) / ydenom * yLen);
/* 1355 */         for (int ii = 1; ii < this.niPoints; ii++) {
/* 1356 */           xnewpoint = this.xBot + (int)((ics[ii] - this.xLow) / xdenom * xLen);
/* 1357 */           ynewpoint = this.yBot - (int)((cs.interpolate(ics[ii]) - this.yLow) / ydenom * yLen);
/* 1358 */           btest2 = printCheck(this.trimOpt[i], xoldpoint, xnewpoint, yoldpoint, ynewpoint);
/* 1359 */           if (btest2) {
/* 1360 */             if (this.lineOpt[i] == 2) {
/* 1361 */               dsum++;
/* 1362 */               if (dsum > this.dashLength[i]) {
/* 1363 */                 dsum = 0;
/* 1364 */                 if (dcheck) {
/* 1365 */                   dcheck = false;
/*      */                 }
/*      */                 else {
/* 1368 */                   dcheck = true;
/*      */                 }
/*      */               }
/*      */             }
/* 1372 */             if (dcheck) g.drawLine(xoldpoint, yoldpoint, xnewpoint, ynewpoint);
/*      */           }
/* 1374 */           xoldpoint = xnewpoint;
/* 1375 */           yoldpoint = ynewpoint;
/*      */         }
/*      */       }
/*      */       
/* 1379 */       if ((this.lineOpt[i] == -1) || (this.lineOpt[i] == -2)) {
/* 1380 */         CubicSpline cs = new CubicSpline(this.nPoints[i]);
/* 1381 */         for (int ii = 0; ii < nxpoints; ii++) {
/* 1382 */           xcs[ii] = this.data[kk][ii];
/*      */         }
/* 1384 */         for (int ii = 0; ii < nxpoints; ii++) {
/* 1385 */           ycs[ii] = this.data[(kk + 1)][ii];
/*      */         }
/* 1387 */         csstep = (ycs[(nxpoints - 1)] - ycs[0]) / (this.niPoints - 1);
/* 1388 */         ics[0] = ycs[0];
/* 1389 */         for (int ii = 1; ii < this.niPoints; ii++) {
/* 1390 */           ics[ii] = (ics[(ii - 1)] + csstep);
/*      */         }
/* 1392 */         ics[(this.niPoints - 1)] = ycs[(nxpoints - 1)];
/*      */         
/* 1394 */         cs.resetData(ycs, xcs);
/* 1395 */         cs.calcDeriv();
/* 1396 */         xoldpoint = this.xBot + (int)((xcs[0] - this.xLow) / xdenom * xLen);
/* 1397 */         yoldpoint = this.yBot - (int)((ycs[0] - this.yLow) / ydenom * yLen);
/* 1398 */         for (int ii = 1; ii < this.niPoints; ii++) {
/* 1399 */           ynewpoint = this.yBot + (int)((ics[ii] - this.yLow) / ydenom * yLen);
/* 1400 */           xnewpoint = this.xBot - (int)((cs.interpolate(ics[ii]) - this.xLow) / xdenom * xLen);
/* 1401 */           btest2 = printCheck(this.trimOpt[i], xoldpoint, xnewpoint, yoldpoint, ynewpoint);
/* 1402 */           if (btest2) {
/* 1403 */             if (this.lineOpt[i] == 2) {
/* 1404 */               dsum++;
/* 1405 */               if (dsum > this.dashLength[i]) {
/* 1406 */                 dsum = 0;
/* 1407 */                 if (dcheck) {
/* 1408 */                   dcheck = false;
/*      */                 }
/*      */                 else {
/* 1411 */                   dcheck = true;
/*      */                 }
/*      */               }
/*      */             }
/* 1415 */             if (dcheck) g.drawLine(xoldpoint, yoldpoint, xnewpoint, ynewpoint);
/*      */           }
/* 1417 */           xoldpoint = xnewpoint;
/* 1418 */           yoldpoint = ynewpoint;
/*      */         }
/*      */       }
/*      */       
/* 1422 */       if (this.lineOpt[i] == 3)
/*      */       {
/* 1424 */         dsum = 0;
/* 1425 */         dcheck = true;
/* 1426 */         xoldpoint = this.xBot + (int)((this.data[kk][0] - this.xLow) / xdenom * xLen);
/* 1427 */         yoldpoint = this.yBot - (int)((this.data[(kk + 1)][0] - this.yLow) / ydenom * yLen);
/* 1428 */         for (int ii = 1; ii < nxpoints; ii++) {
/* 1429 */           xnewpoint = this.xBot + (int)((this.data[kk][ii] - this.xLow) / xdenom * xLen);
/* 1430 */           ynewpoint = this.yBot - (int)((this.data[(kk + 1)][ii] - this.yLow) / ydenom * yLen);
/* 1431 */           btest2 = printCheck(this.trimOpt[i], xoldpoint, xnewpoint, yoldpoint, ynewpoint);
/* 1432 */           if (btest2) g.drawLine(xoldpoint, yoldpoint, xnewpoint, ynewpoint);
/* 1433 */           xoldpoint = xnewpoint;
/* 1434 */           yoldpoint = ynewpoint;
/*      */         }
/*      */       }
/*      */       
/* 1438 */       if (this.lineOpt[i] == 4)
/*      */       {
/*      */ 
/*      */ 
/* 1442 */         int[] lengths = new int[nxpoints - 1];
/* 1443 */         double[] gradients = new double[nxpoints - 1];
/* 1444 */         double[] intercepts = new double[nxpoints - 1];
/* 1445 */         int totalLength = 0;
/* 1446 */         xoldpoint = this.xBot + (int)((this.data[kk][0] - this.xLow) / xdenom * xLen);
/* 1447 */         yoldpoint = this.yBot - (int)((this.data[(kk + 1)][0] - this.yLow) / ydenom * yLen);
/* 1448 */         for (int ii = 1; ii < nxpoints; ii++) {
/* 1449 */           xnewpoint = this.xBot + (int)((this.data[kk][ii] - this.xLow) / xdenom * xLen);
/* 1450 */           ynewpoint = this.yBot - (int)((this.data[(kk + 1)][ii] - this.yLow) / ydenom * yLen);
/* 1451 */           lengths[(ii - 1)] = ((int)Fmath.hypot(xnewpoint - xoldpoint, ynewpoint - yoldpoint));
/* 1452 */           totalLength += lengths[(ii - 1)];
/* 1453 */           gradients[(ii - 1)] = ((ynewpoint - yoldpoint) / (xnewpoint - xoldpoint));
/* 1454 */           intercepts[(ii - 1)] = (yoldpoint - gradients[(ii - 1)] * xoldpoint);
/* 1455 */           xoldpoint = xnewpoint;
/* 1456 */           yoldpoint = ynewpoint;
/*      */         }
/*      */         
/*      */ 
/* 1460 */         int incrmt = totalLength / (4 * this.niPoints - 1);
/* 1461 */         int nlpointsold = 0;
/* 1462 */         int nlpointsnew = 0;
/* 1463 */         int totalLpoints = 1;
/* 1464 */         for (int ii = 1; ii < nxpoints; ii++) {
/* 1465 */           totalLpoints++;
/* 1466 */           nlpointsnew = lengths[(ii - 1)] / incrmt;
/* 1467 */           for (int jj = nlpointsold + 1; jj < nlpointsnew + nlpointsold; jj++) totalLpoints++;
/* 1468 */           nlpointsold += nlpointsnew;
/*      */         }
/*      */         
/*      */ 
/* 1472 */         int[] xdashed = new int[totalLpoints];
/* 1473 */         int[] ydashed = new int[totalLpoints];
/* 1474 */         nlpointsold = 0;
/* 1475 */         nlpointsnew = 0;
/* 1476 */         xdashed[0] = (this.xBot + (int)((this.data[kk][0] - this.xLow) / xdenom * xLen));
/* 1477 */         ydashed[0] = (this.yBot - (int)((this.data[(kk + 1)][0] - this.yLow) / ydenom * yLen));
/* 1478 */         for (int ii = 1; ii < nxpoints; ii++) {
/* 1479 */           nlpointsnew = lengths[(ii - 1)] / incrmt;
/* 1480 */           xdashed[(nlpointsnew + nlpointsold)] = (this.xBot + (int)((this.data[kk][ii] - this.xLow) / xdenom * xLen));
/* 1481 */           ydashed[(nlpointsnew + nlpointsold)] = (this.yBot - (int)((this.data[(kk + 1)][ii] - this.yLow) / ydenom * yLen));
/* 1482 */           if (Math.abs(gradients[(ii - 1)]) > 0.5D) {
/* 1483 */             int diff = (ydashed[(nlpointsnew + nlpointsold)] - ydashed[nlpointsold]) / nlpointsnew;
/* 1484 */             for (int jj = nlpointsold + 1; jj < nlpointsnew + nlpointsold; jj++) {
/* 1485 */               ydashed[jj] = (ydashed[(jj - 1)] + diff);
/* 1486 */               if (Fmath.isInfinity(Math.abs(gradients[(ii - 1)]))) {
/* 1487 */                 xdashed[jj] = xdashed[(nlpointsnew + nlpointsold)];
/*      */               }
/*      */               else {
/* 1490 */                 xdashed[jj] = ((int)((ydashed[jj] - intercepts[(ii - 1)]) / gradients[(ii - 1)]));
/*      */               }
/*      */             }
/*      */           }
/*      */           else {
/* 1495 */             int diff = (xdashed[(nlpointsnew + nlpointsold)] - xdashed[nlpointsold]) / nlpointsnew;
/* 1496 */             for (int jj = nlpointsold + 1; jj < nlpointsnew + nlpointsold; jj++) {
/* 1497 */               xdashed[jj] = (xdashed[(jj - 1)] + diff);
/* 1498 */               ydashed[jj] = ((int)(gradients[(ii - 1)] * ydashed[jj] + intercepts[(ii - 1)]));
/*      */             }
/*      */           }
/* 1501 */           nlpointsold += nlpointsnew;
/*      */         }
/*      */         
/* 1504 */         dsum = 0;
/* 1505 */         dcheck = true;
/* 1506 */         for (int ii = 1; ii < totalLpoints; ii++) {
/* 1507 */           dsum++;
/* 1508 */           if (dsum > this.dashLength[i]) {
/* 1509 */             dsum = 0;
/* 1510 */             if (dcheck) {
/* 1511 */               dcheck = false;
/*      */             }
/*      */             else {
/* 1514 */               dcheck = true;
/*      */             }
/*      */           }
/* 1517 */           if (dcheck) { g.drawLine(xdashed[(ii - 1)], ydashed[(ii - 1)], xdashed[ii], ydashed[ii]);
/*      */           }
/*      */         }
/*      */       }
/*      */       
/*      */ 
/*      */ 
/* 1524 */       if (this.pointOpt[i] > 0) {
/* 1525 */         for (int ii = 0; ii < nxpoints; ii++) {
/* 1526 */           ps = this.pointSize[i];
/* 1527 */           psh = ps / 2;
/* 1528 */           xxp = this.xBot + (int)((this.data[kk][ii] - this.xLow) / xdenom * xLen);
/* 1529 */           yyp = this.yBot - (int)((this.data[(kk + 1)][ii] - this.yLow) / ydenom * yLen);
/* 1530 */           switch (this.pointOpt[i]) {
/* 1531 */           case 1:  g.drawOval(xxp - psh, yyp - psh, ps, ps);
/* 1532 */             break;
/* 1533 */           case 2:  g.drawRect(xxp - psh, yyp - psh, ps, ps);
/* 1534 */             break;
/* 1535 */           case 3:  g.drawLine(xxp - psh, yyp, xxp, yyp + psh);
/* 1536 */             g.drawLine(xxp, yyp + psh, xxp + psh, yyp);
/* 1537 */             g.drawLine(xxp + psh, yyp, xxp, yyp - psh);
/* 1538 */             g.drawLine(xxp, yyp - psh, xxp - psh, yyp);
/* 1539 */             break;
/* 1540 */           case 4:  g.fillOval(xxp - psh, yyp - psh, ps, ps);
/* 1541 */             break;
/* 1542 */           case 5:  g.fillRect(xxp - psh, yyp - psh, ps, ps);
/* 1543 */             break;
/* 1544 */           case 6:  for (int jj = 0; jj < psh; jj++) g.drawLine(xxp - jj, yyp - psh + jj, xxp + jj, yyp - psh + jj);
/* 1545 */             for (int jj = 0; jj <= psh; jj++) g.drawLine(xxp - psh + jj, yyp + jj, xxp + psh - jj, yyp + jj);
/* 1546 */             break;
/* 1547 */           case 7:  g.drawLine(xxp - psh, yyp - psh, xxp + psh, yyp + psh);
/* 1548 */             g.drawLine(xxp - psh, yyp + psh, xxp + psh, yyp - psh);
/* 1549 */             break;
/* 1550 */           case 8:  g.drawLine(xxp - psh, yyp, xxp + psh, yyp);
/* 1551 */             g.drawLine(xxp, yyp + psh, xxp, yyp - psh);
/* 1552 */             break;
/* 1553 */           default:  g.drawLine(xxp - psh, yyp - psh, xxp + psh, yyp + psh);
/* 1554 */             g.drawLine(xxp - psh, yyp + psh, xxp + psh, yyp - psh);
/*      */           }
/*      */           
/*      */           
/* 1558 */           if (this.errorBar[i] != 0) {
/* 1559 */             yype = this.yBot - (int)((this.errors[i][ii] - this.yLow) / ydenom * yLen);
/* 1560 */             g.drawLine(xxp, yyp, xxp, yype);
/* 1561 */             g.drawLine(xxp - 4, yype, xxp + 4, yype);
/* 1562 */             yype = 2 * yyp - yype;
/* 1563 */             g.drawLine(xxp, yyp, xxp, yype);
/* 1564 */             g.drawLine(xxp - 4, yype, xxp + 4, yype);
/*      */           }
/*      */         }
/*      */       }
/* 1568 */       kk += 2;
/*      */     }
/*      */   }
/*      */   
/*      */   public static long getSerialVersionUID()
/*      */   {
/* 1574 */     return 1L;
/*      */   }
/*      */ }


/* Location:              /home/menick/Scrivania/ProcessMining/CNMining/CNMining.jar!/flanagan/plot/Plot.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       0.7.1
 */