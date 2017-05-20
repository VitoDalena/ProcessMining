/*     */ package flanagan.interpolation;
/*     */ 
/*     */ import flanagan.math.Fmath;
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
/*     */ public class BiCubicSpline
/*     */ {
/*  45 */   private int nPoints = 0;
/*  46 */   private int mPoints = 0;
/*  47 */   private double[][] y = (double[][])null;
/*  48 */   private double[] x1 = null;
/*  49 */   private double[] x2 = null;
/*  50 */   private double[] xMin = new double[2];
/*  51 */   private double[] xMax = new double[2];
/*  52 */   private double[][] d2ydx2inner = (double[][])null;
/*  53 */   private CubicSpline[] csn = null;
/*  54 */   private CubicSpline csm = null;
/*  55 */   private boolean derivCalculated = false;
/*  56 */   private String subMatrixIndices = " ";
/*  57 */   private boolean averageIdenticalAbscissae = false;
/*     */   
/*  59 */   private static double potentialRoundingError = 5.0E-15D;
/*  60 */   private static boolean roundingCheck = true;
/*     */   
/*     */ 
/*     */ 
/*     */   public BiCubicSpline(double[] x1, double[] x2, double[][] y)
/*     */   {
/*  66 */     this.nPoints = x1.length;
/*  67 */     this.mPoints = x2.length;
/*  68 */     if (this.nPoints != y.length) throw new IllegalArgumentException("Arrays x1 and y-row are of different length " + this.nPoints + " " + y.length);
/*  69 */     if (this.mPoints != y[0].length) throw new IllegalArgumentException("Arrays x2 and y-column are of different length " + this.mPoints + " " + y[0].length);
/*  70 */     if ((this.nPoints < 3) || (this.mPoints < 3)) { throw new IllegalArgumentException("The data matrix must have a minimum size of 3 X 3");
/*     */     }
/*  72 */     this.csm = new CubicSpline(this.nPoints);
/*  73 */     this.csn = CubicSpline.oneDarray(this.nPoints, this.mPoints);
/*  74 */     this.x1 = new double[this.nPoints];
/*  75 */     this.x2 = new double[this.mPoints];
/*  76 */     this.y = new double[this.nPoints][this.mPoints];
/*  77 */     this.d2ydx2inner = new double[this.nPoints][this.mPoints];
/*  78 */     for (int i = 0; i < this.nPoints; i++) {
/*  79 */       this.x1[i] = x1[i];
/*     */     }
/*  81 */     this.xMin[0] = Fmath.minimum(this.x1);
/*  82 */     this.xMax[0] = Fmath.maximum(this.x1);
/*  83 */     for (int j = 0; j < this.mPoints; j++) {
/*  84 */       this.x2[j] = x2[j];
/*     */     }
/*  86 */     this.xMin[1] = Fmath.minimum(this.x2);
/*  87 */     this.xMax[1] = Fmath.maximum(this.x2);
/*  88 */     for (int i = 0; i < this.nPoints; i++) {
/*  89 */       for (int j = 0; j < this.mPoints; j++) {
/*  90 */         this.y[i][j] = y[i][j];
/*     */       }
/*     */     }
/*     */   }
/*     */   
/*     */ 
/*     */   public BiCubicSpline(int nP, int mP)
/*     */   {
/*  98 */     this.nPoints = nP;
/*  99 */     this.mPoints = mP;
/* 100 */     if ((this.nPoints < 3) || (this.mPoints < 3)) { throw new IllegalArgumentException("The data matrix must have a minimum size of 3 X 3");
/*     */     }
/* 102 */     this.csm = new CubicSpline(this.nPoints);
/* 103 */     if (!roundingCheck) { CubicSpline.noRoundingErrorCheck();
/*     */     }
/* 105 */     this.csn = CubicSpline.oneDarray(this.nPoints, this.mPoints);
/* 106 */     this.x1 = new double[this.nPoints];
/* 107 */     this.x2 = new double[this.mPoints];
/* 108 */     this.y = new double[this.nPoints][this.mPoints];
/* 109 */     this.d2ydx2inner = new double[this.nPoints][this.mPoints];
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public static void noRoundingErrorCheck()
/*     */   {
/* 118 */     roundingCheck = false;
/* 119 */     CubicSpline.noRoundingErrorCheck();
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   public static void potentialRoundingError(double potentialRoundingError)
/*     */   {
/* 127 */     potentialRoundingError = potentialRoundingError;
/* 128 */     CubicSpline.potentialRoundingError(potentialRoundingError);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */   public void averageIdenticalAbscissae()
/*     */   {
/* 135 */     this.averageIdenticalAbscissae = true;
/* 136 */     for (int i = 0; i < this.csn.length; i++) this.csn[i].averageIdenticalAbscissae();
/* 137 */     this.csm.averageIdenticalAbscissae();
/*     */   }
/*     */   
/*     */ 
/*     */   public void resetData(double[] x1, double[] x2, double[][] y)
/*     */   {
/* 143 */     if (x1.length != y.length) throw new IllegalArgumentException("Arrays x1 and y row are of different length");
/* 144 */     if (x2.length != y[0].length) throw new IllegalArgumentException("Arrays x2 and y column are of different length");
/* 145 */     if (this.nPoints != x1.length) throw new IllegalArgumentException("Original array length not matched by new array length");
/* 146 */     if (this.mPoints != x2.length) { throw new IllegalArgumentException("Original array length not matched by new array length");
/*     */     }
/* 148 */     for (int i = 0; i < this.nPoints; i++) {
/* 149 */       this.x1[i] = x1[i];
/*     */     }
/*     */     
/* 152 */     for (int i = 0; i < this.mPoints; i++) {
/* 153 */       this.x2[i] = x2[i];
/*     */     }
/*     */     
/* 156 */     for (int i = 0; i < this.nPoints; i++) {
/* 157 */       for (int j = 0; j < this.mPoints; j++) {
/* 158 */         this.y[i][j] = y[i][j];
/*     */       }
/*     */     }
/*     */   }
/*     */   
/*     */ 
/*     */   public static BiCubicSpline zero(int nP, int mP)
/*     */   {
/* 166 */     if ((nP < 3) || (mP < 3)) throw new IllegalArgumentException("A minimum of three x three data points is needed");
/* 167 */     BiCubicSpline aa = new BiCubicSpline(nP, mP);
/* 168 */     return aa;
/*     */   }
/*     */   
/*     */ 
/*     */   public static BiCubicSpline[] oneDarray(int nP, int mP, int lP)
/*     */   {
/* 174 */     if ((mP < 3) || (lP < 3)) throw new IllegalArgumentException("A minimum of three x three data points is needed");
/* 175 */     BiCubicSpline[] a = new BiCubicSpline[nP];
/* 176 */     for (int i = 0; i < nP; i++) {
/* 177 */       a[i] = zero(mP, lP);
/*     */     }
/* 179 */     return a;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */   public double[][] getDeriv()
/*     */   {
/* 186 */     return this.d2ydx2inner;
/*     */   }
/*     */   
/*     */   public void setSubMatrix(String subMatrixVector)
/*     */   {
/* 191 */     this.subMatrixIndices = this.subMatrixIndices;
/*     */   }
/*     */   
/*     */   public double[] getXmin()
/*     */   {
/* 196 */     return this.xMin;
/*     */   }
/*     */   
/*     */   public double[] getXmax()
/*     */   {
/* 201 */     return this.xMax;
/*     */   }
/*     */   
/*     */   public double[] getLimits()
/*     */   {
/* 206 */     double[] limits = { this.xMin[0], this.xMax[0], this.xMin[1], this.xMax[1] };
/* 207 */     return limits;
/*     */   }
/*     */   
/*     */   public void displayLimits()
/*     */   {
/* 212 */     System.out.println(" ");
/* 213 */     for (int i = 0; i < 2; i++) {
/* 214 */       System.out.println("The limits to the x array " + i + " are " + this.xMin[i] + " and " + this.xMax[i]);
/*     */     }
/* 216 */     System.out.println(" ");
/*     */   }
/*     */   
/*     */ 
/*     */   public void setDeriv(double[][] d2ydx2)
/*     */   {
/* 222 */     this.d2ydx2inner = d2ydx2;
/* 223 */     this.derivCalculated = true;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */   public double interpolate(double xx1, double xx2)
/*     */   {
/* 230 */     double[] yTempn = new double[this.mPoints];
/*     */     
/* 232 */     for (int i = 0; i < this.nPoints; i++) {
/* 233 */       for (int j = 0; j < this.mPoints; j++) yTempn[j] = this.y[i][j];
/* 234 */       String workingIndices = new String(this.subMatrixIndices);
/* 235 */       workingIndices = workingIndices + "BiCubicSpline row  " + i + ": ";
/* 236 */       this.csn[i].setSubMatrix(workingIndices);
/* 237 */       this.csn[i].resetData(this.x2, yTempn);
/*     */       
/* 239 */       if (!this.derivCalculated) this.csn[i].calcDeriv();
/* 240 */       this.d2ydx2inner[i] = this.csn[i].getDeriv();
/*     */     }
/* 242 */     this.derivCalculated = true;
/*     */     
/* 244 */     double[] yTempm = new double[this.nPoints];
/*     */     
/* 246 */     for (int i = 0; i < this.nPoints; i++) {
/* 247 */       this.csn[i].setDeriv(this.d2ydx2inner[i]);
/* 248 */       yTempm[i] = this.csn[i].interpolate(xx2);
/*     */     }
/*     */     
/* 251 */     String workingIndices = new String(this.subMatrixIndices);
/* 252 */     workingIndices = workingIndices + "BiCubicSpline interpolated column:  ";
/* 253 */     this.csm.setSubMatrix(workingIndices);
/* 254 */     this.csm.resetData(this.x1, yTempm);
/* 255 */     return this.csm.interpolate(xx1);
/*     */   }
/*     */ }


/* Location:              /home/menick/Scrivania/ProcessMining/CNMining/CNMining.jar!/flanagan/interpolation/BiCubicSpline.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       0.7.1
 */