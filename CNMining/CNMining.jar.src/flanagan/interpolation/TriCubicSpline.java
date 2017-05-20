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
/*     */ public class TriCubicSpline
/*     */ {
/*  39 */   private int nPoints = 0;
/*  40 */   private int mPoints = 0;
/*  41 */   private int lPoints = 0;
/*  42 */   private double[][][] y = (double[][][])null;
/*  43 */   private double[] x1 = null;
/*  44 */   private double[] x2 = null;
/*  45 */   private double[] x3 = null;
/*  46 */   private double[] xMin = new double[3];
/*  47 */   private double[] xMax = new double[3];
/*  48 */   private BiCubicSpline[] bcsn = null;
/*  49 */   private CubicSpline csm = null;
/*  50 */   private double[][][] d2ydx2inner = (double[][][])null;
/*  51 */   private boolean derivCalculated = false;
/*  52 */   private String subMatrixIndices = " ";
/*  53 */   private boolean averageIdenticalAbscissae = false;
/*     */   
/*  55 */   private static double potentialRoundingError = 5.0E-15D;
/*  56 */   private static boolean roundingCheck = true;
/*     */   
/*     */ 
/*     */   public TriCubicSpline(double[] x1, double[] x2, double[] x3, double[][][] y)
/*     */   {
/*  61 */     this.nPoints = x1.length;
/*  62 */     this.mPoints = x2.length;
/*  63 */     this.lPoints = x3.length;
/*  64 */     if (this.nPoints != y.length) throw new IllegalArgumentException("Arrays x1 and y-row are of different length " + this.nPoints + " " + y.length);
/*  65 */     if (this.mPoints != y[0].length) throw new IllegalArgumentException("Arrays x2 and y-column are of different length " + this.mPoints + " " + y[0].length);
/*  66 */     if (this.lPoints != y[0][0].length) throw new IllegalArgumentException("Arrays x3 and y-column are of different length " + this.mPoints + " " + y[0][0].length);
/*  67 */     if ((this.nPoints < 3) || (this.mPoints < 3) || (this.lPoints < 3)) { throw new IllegalArgumentException("The tabulated 3D array must have a minimum size of 3 X 3 X 3");
/*     */     }
/*  69 */     this.csm = new CubicSpline(this.nPoints);
/*  70 */     this.bcsn = BiCubicSpline.oneDarray(this.nPoints, this.mPoints, this.lPoints);
/*  71 */     this.x1 = new double[this.nPoints];
/*  72 */     this.x2 = new double[this.mPoints];
/*  73 */     this.x3 = new double[this.lPoints];
/*  74 */     this.y = new double[this.nPoints][this.mPoints][this.lPoints];
/*  75 */     this.d2ydx2inner = new double[this.nPoints][this.mPoints][this.lPoints];
/*  76 */     for (int i = 0; i < this.nPoints; i++) {
/*  77 */       this.x1[i] = x1[i];
/*     */     }
/*  79 */     this.xMin[0] = Fmath.minimum(this.x1);
/*  80 */     this.xMax[0] = Fmath.maximum(this.x1);
/*  81 */     for (int j = 0; j < this.mPoints; j++) {
/*  82 */       this.x2[j] = x2[j];
/*     */     }
/*  84 */     this.xMin[1] = Fmath.minimum(this.x2);
/*  85 */     this.xMax[1] = Fmath.maximum(this.x2);
/*  86 */     for (int j = 0; j < this.lPoints; j++) {
/*  87 */       this.x3[j] = x3[j];
/*     */     }
/*  89 */     this.xMin[2] = Fmath.minimum(this.x3);
/*  90 */     this.xMax[2] = Fmath.maximum(this.x3);
/*  91 */     for (int i = 0; i < this.nPoints; i++) {
/*  92 */       for (int j = 0; j < this.mPoints; j++) {
/*  93 */         for (int k = 0; k < this.lPoints; k++) {
/*  94 */           this.y[i][j][k] = y[i][j][k];
/*     */         }
/*     */       }
/*     */     }
/*     */   }
/*     */   
/*     */ 
/*     */   public TriCubicSpline(int nP, int mP, int lP)
/*     */   {
/* 103 */     this.nPoints = nP;
/* 104 */     this.mPoints = mP;
/* 105 */     this.lPoints = lP;
/* 106 */     if ((this.nPoints < 3) || (this.mPoints < 3) || (this.lPoints < 3)) { throw new IllegalArgumentException("The data matrix must have a minimum size of 3 X 3 X 3");
/*     */     }
/* 108 */     this.csm = new CubicSpline(this.nPoints);
/* 109 */     this.bcsn = BiCubicSpline.oneDarray(this.nPoints, this.mPoints, this.lPoints);
/* 110 */     this.x1 = new double[this.nPoints];
/* 111 */     this.x2 = new double[this.mPoints];
/* 112 */     this.x3 = new double[this.lPoints];
/* 113 */     this.y = new double[this.nPoints][this.mPoints][this.lPoints];
/* 114 */     this.d2ydx2inner = new double[this.nPoints][this.mPoints][this.lPoints];
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public static void noRoundingErrorCheck()
/*     */   {
/* 123 */     roundingCheck = false;
/* 124 */     BiCubicSpline.noRoundingErrorCheck();
/* 125 */     CubicSpline.noRoundingErrorCheck();
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   public static void potentialRoundingError(double potentialRoundingError)
/*     */   {
/* 133 */     potentialRoundingError = potentialRoundingError;
/* 134 */     BiCubicSpline.potentialRoundingError(potentialRoundingError);
/* 135 */     CubicSpline.potentialRoundingError(potentialRoundingError);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */   public void averageIdenticalAbscissae()
/*     */   {
/* 142 */     this.averageIdenticalAbscissae = true;
/* 143 */     for (int i = 0; i < this.bcsn.length; i++) this.bcsn[i].averageIdenticalAbscissae();
/* 144 */     this.csm.averageIdenticalAbscissae();
/*     */   }
/*     */   
/*     */ 
/*     */   public static TriCubicSpline zero(int nP, int mP, int lP)
/*     */   {
/* 150 */     if ((nP < 3) || (mP < 3) || (lP < 3)) throw new IllegalArgumentException("A minimum of three x three x three data points is needed");
/* 151 */     TriCubicSpline aa = new TriCubicSpline(nP, mP, lP);
/* 152 */     return aa;
/*     */   }
/*     */   
/*     */ 
/*     */   public static TriCubicSpline[] oneDarray(int nP, int mP, int lP, int kP)
/*     */   {
/* 158 */     if ((mP < 3) || (lP < 3) || (kP < 3)) throw new IllegalArgumentException("A minimum of three x three x three data points is needed");
/* 159 */     TriCubicSpline[] a = new TriCubicSpline[nP];
/* 160 */     for (int i = 0; i < nP; i++) {
/* 161 */       a[i] = zero(mP, lP, kP);
/*     */     }
/* 163 */     return a;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */   public void resetData(double[] x1, double[] x2, double[] x3, double[][][] y)
/*     */   {
/* 170 */     if (x1.length != y.length) throw new IllegalArgumentException("Arrays x1 and y row are of different length");
/* 171 */     if (x2.length != y[0].length) throw new IllegalArgumentException("Arrays x2 and y column are of different length");
/* 172 */     if (x3.length != y[0][0].length) throw new IllegalArgumentException("Arrays x3 and y column are of different length");
/* 173 */     if (this.nPoints != x1.length) throw new IllegalArgumentException("Original array length not matched by new array length");
/* 174 */     if (this.mPoints != x2.length) throw new IllegalArgumentException("Original array length not matched by new array length");
/* 175 */     if (this.lPoints != x3.length) { throw new IllegalArgumentException("Original array length not matched by new array length");
/*     */     }
/* 177 */     for (int i = 0; i < this.nPoints; i++) {
/* 178 */       this.x1[i] = x1[i];
/*     */     }
/* 180 */     this.xMin[0] = Fmath.minimum(this.x1);
/* 181 */     this.xMax[0] = Fmath.maximum(this.x1);
/*     */     
/* 183 */     for (int i = 0; i < this.mPoints; i++) {
/* 184 */       this.x2[i] = x2[i];
/*     */     }
/* 186 */     this.xMin[1] = Fmath.minimum(this.x2);
/* 187 */     this.xMax[1] = Fmath.maximum(this.x2);
/*     */     
/* 189 */     for (int i = 0; i < this.lPoints; i++) {
/* 190 */       this.x3[i] = x3[i];
/*     */     }
/* 192 */     this.xMin[2] = Fmath.minimum(this.x3);
/* 193 */     this.xMax[2] = Fmath.maximum(this.x3);
/*     */     
/* 195 */     for (int i = 0; i < this.nPoints; i++) {
/* 196 */       for (int j = 0; j < this.mPoints; j++) {
/* 197 */         for (int k = 0; k < this.lPoints; k++) {
/* 198 */           this.y[i][j][k] = y[i][j][k];
/*     */         }
/*     */       }
/*     */     }
/*     */   }
/*     */   
/*     */   public void setSubMatrix(String subMatrixVector)
/*     */   {
/* 206 */     this.subMatrixIndices = this.subMatrixIndices;
/*     */   }
/*     */   
/*     */   public double[] getXmin()
/*     */   {
/* 211 */     return this.xMin;
/*     */   }
/*     */   
/*     */   public double[] getXmax()
/*     */   {
/* 216 */     return this.xMax;
/*     */   }
/*     */   
/*     */   public double[] getLimits()
/*     */   {
/* 221 */     double[] limits = { this.xMin[0], this.xMax[0], this.xMin[1], this.xMax[1], this.xMin[2], this.xMax[2] };
/* 222 */     return limits;
/*     */   }
/*     */   
/*     */   public void displayLimits()
/*     */   {
/* 227 */     System.out.println(" ");
/* 228 */     for (int i = 0; i < 3; i++) {
/* 229 */       System.out.println("The limits to the x array " + i + " are " + this.xMin[i] + " and " + this.xMax[i]);
/*     */     }
/* 231 */     System.out.println(" ");
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */   public double interpolate(double xx1, double xx2, double xx3)
/*     */   {
/* 238 */     double[][] yTempml = new double[this.mPoints][this.lPoints];
/* 239 */     for (int i = 0; i < this.nPoints; i++) {
/* 240 */       String workingIndices = new String(this.subMatrixIndices);
/* 241 */       workingIndices = workingIndices + "TriCubicSpline rows  " + i;
/*     */       
/* 243 */       for (int j = 0; j < this.mPoints; j++) {
/* 244 */         workingIndices = workingIndices + ",  " + j + ": ";
/* 245 */         for (int k = 0; k < this.lPoints; k++) {
/* 246 */           yTempml[j][k] = this.y[i][j][k];
/*     */         }
/*     */       }
/* 249 */       this.bcsn[i].setSubMatrix(workingIndices);
/* 250 */       this.bcsn[i].resetData(this.x2, this.x3, yTempml);
/*     */     }
/* 252 */     double[] yTempm = new double[this.nPoints];
/*     */     
/* 254 */     for (int i = 0; i < this.nPoints; i++) {
/* 255 */       if (this.derivCalculated) this.bcsn[i].setDeriv(this.d2ydx2inner[i]);
/* 256 */       yTempm[i] = this.bcsn[i].interpolate(xx2, xx3);
/* 257 */       if (!this.derivCalculated) this.d2ydx2inner[i] = this.bcsn[i].getDeriv();
/*     */     }
/* 259 */     this.derivCalculated = true;
/*     */     
/* 261 */     String workingIndices = new String(this.subMatrixIndices);
/* 262 */     workingIndices = workingIndices + "TriCubicSpline interpolated column:  ";
/* 263 */     this.csm.setSubMatrix(workingIndices);
/* 264 */     this.csm.resetData(this.x1, yTempm);
/*     */     
/* 266 */     return this.csm.interpolate(xx1);
/*     */   }
/*     */   
/*     */ 
/*     */   public double[][][] getDeriv()
/*     */   {
/* 272 */     return this.d2ydx2inner;
/*     */   }
/*     */   
/*     */ 
/*     */   public void setDeriv(double[][][] d2ydx2)
/*     */   {
/* 278 */     this.d2ydx2inner = d2ydx2;
/* 279 */     this.derivCalculated = true;
/*     */   }
/*     */ }


/* Location:              /home/menick/Scrivania/ProcessMining/CNMining/CNMining.jar!/flanagan/interpolation/TriCubicSpline.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       0.7.1
 */