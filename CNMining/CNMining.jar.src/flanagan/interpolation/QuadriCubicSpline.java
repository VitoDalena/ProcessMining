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
/*     */ public class QuadriCubicSpline
/*     */ {
/*  45 */   private int nPoints = 0;
/*  46 */   private int mPoints = 0;
/*  47 */   private int lPoints = 0;
/*  48 */   private int kPoints = 0;
/*     */   
/*  50 */   private double[][][][] y = (double[][][][])null;
/*  51 */   private double[] x1 = null;
/*  52 */   private double[] x2 = null;
/*  53 */   private double[] x3 = null;
/*  54 */   private double[] x4 = null;
/*  55 */   private double[] xMin = new double[4];
/*  56 */   private double[] xMax = new double[4];
/*     */   
/*  58 */   private TriCubicSpline[] tcsn = null;
/*  59 */   private CubicSpline csm = null;
/*  60 */   private double[][][][] d2ydx2 = (double[][][][])null;
/*  61 */   private boolean derivCalculated = false;
/*  62 */   private String subMatrixIndices = " ";
/*  63 */   private boolean averageIdenticalAbscissae = false;
/*     */   
/*  65 */   private static double potentialRoundingError = 5.0E-15D;
/*  66 */   private static boolean roundingCheck = true;
/*     */   
/*     */ 
/*     */   public QuadriCubicSpline(double[] x1, double[] x2, double[] x3, double[] x4, double[][][][] y)
/*     */   {
/*  71 */     this.nPoints = x1.length;
/*  72 */     this.mPoints = x2.length;
/*  73 */     this.lPoints = x3.length;
/*  74 */     this.kPoints = x4.length;
/*  75 */     if (this.nPoints != y.length) throw new IllegalArgumentException("Arrays x1 and y-row are of different length " + this.nPoints + " " + y.length);
/*  76 */     if (this.mPoints != y[0].length) throw new IllegalArgumentException("Arrays x2 and y-column are of different length " + this.mPoints + " " + y[0].length);
/*  77 */     if (this.lPoints != y[0][0].length) throw new IllegalArgumentException("Arrays x3 and y-column are of different length " + this.mPoints + " " + y[0][0].length);
/*  78 */     if (this.kPoints != y[0][0][0].length) throw new IllegalArgumentException("Arrays x4 and y-column are of different length " + this.kPoints + " " + y[0][0][0].length);
/*  79 */     if ((this.nPoints < 3) || (this.mPoints < 3) || (this.lPoints < 3) || (this.kPoints < 3)) { throw new IllegalArgumentException("The tabulated 4D array must have a minimum size of 3 X 3 X 3 X 3");
/*     */     }
/*  81 */     this.csm = new CubicSpline(this.nPoints);
/*  82 */     this.tcsn = TriCubicSpline.oneDarray(this.nPoints, this.mPoints, this.lPoints, this.kPoints);
/*  83 */     this.x1 = new double[this.nPoints];
/*  84 */     this.x2 = new double[this.mPoints];
/*  85 */     this.x3 = new double[this.lPoints];
/*  86 */     this.x4 = new double[this.kPoints];
/*     */     
/*  88 */     this.y = new double[this.nPoints][this.mPoints][this.lPoints][this.kPoints];
/*  89 */     this.d2ydx2 = new double[this.nPoints][this.mPoints][this.lPoints][this.kPoints];
/*  90 */     for (int i = 0; i < this.nPoints; i++) {
/*  91 */       this.x1[i] = x1[i];
/*     */     }
/*  93 */     this.xMin[0] = Fmath.minimum(this.x1);
/*  94 */     this.xMax[0] = Fmath.maximum(this.x1);
/*     */     
/*  96 */     for (int j = 0; j < this.mPoints; j++) {
/*  97 */       this.x2[j] = x2[j];
/*     */     }
/*  99 */     this.xMin[1] = Fmath.minimum(this.x2);
/* 100 */     this.xMax[1] = Fmath.maximum(this.x2);
/*     */     
/* 102 */     for (int j = 0; j < this.lPoints; j++) {
/* 103 */       this.x3[j] = x3[j];
/*     */     }
/* 105 */     this.xMin[2] = Fmath.minimum(this.x3);
/* 106 */     this.xMax[2] = Fmath.maximum(this.x3);
/*     */     
/* 108 */     for (int j = 0; j < this.kPoints; j++) {
/* 109 */       this.x4[j] = x4[j];
/*     */     }
/* 111 */     this.xMin[3] = Fmath.minimum(this.x4);
/* 112 */     this.xMax[3] = Fmath.maximum(this.x4);
/*     */     
/* 114 */     for (int i = 0; i < this.nPoints; i++) {
/* 115 */       for (int j = 0; j < this.mPoints; j++) {
/* 116 */         for (int k = 0; k < this.lPoints; k++) {
/* 117 */           for (int l = 0; l < this.kPoints; l++) {
/* 118 */             this.y[i][j][k][l] = y[i][j][k][l];
/*     */           }
/*     */         }
/*     */       }
/*     */     }
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public static void noRoundingErrorCheck()
/*     */   {
/* 131 */     roundingCheck = false;
/* 132 */     TriCubicSpline.noRoundingErrorCheck();
/* 133 */     BiCubicSpline.noRoundingErrorCheck();
/* 134 */     CubicSpline.noRoundingErrorCheck();
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   public static void potentialRoundingError(double potentialRoundingError)
/*     */   {
/* 142 */     potentialRoundingError = potentialRoundingError;
/* 143 */     TriCubicSpline.potentialRoundingError(potentialRoundingError);
/* 144 */     BiCubicSpline.potentialRoundingError(potentialRoundingError);
/* 145 */     CubicSpline.potentialRoundingError(potentialRoundingError);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */   public void averageIdenticalAbscissae()
/*     */   {
/* 152 */     this.averageIdenticalAbscissae = true;
/* 153 */     for (int i = 0; i < this.tcsn.length; i++) this.tcsn[i].averageIdenticalAbscissae();
/* 154 */     this.csm.averageIdenticalAbscissae();
/*     */   }
/*     */   
/*     */   public double[] getXmin()
/*     */   {
/* 159 */     return this.xMin;
/*     */   }
/*     */   
/*     */   public double[] getXmax()
/*     */   {
/* 164 */     return this.xMax;
/*     */   }
/*     */   
/*     */   public double[] getLimits()
/*     */   {
/* 169 */     double[] limits = { this.xMin[0], this.xMax[0], this.xMin[1], this.xMax[1], this.xMin[2], this.xMax[2], this.xMin[3], this.xMax[3] };
/* 170 */     return limits;
/*     */   }
/*     */   
/*     */   public void displayLimits()
/*     */   {
/* 175 */     System.out.println(" ");
/* 176 */     for (int i = 0; i < 2; i++) {
/* 177 */       System.out.println("The limits to the x array " + i + " are " + this.xMin[i] + " and " + this.xMax[i]);
/*     */     }
/* 179 */     System.out.println(" ");
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */   public double interpolate(double xx1, double xx2, double xx3, double xx4)
/*     */   {
/* 186 */     double[][][] yTempml = new double[this.mPoints][this.lPoints][this.kPoints];
/* 187 */     for (int i = 0; i < this.nPoints; i++) {
/* 188 */       String workingIndices = new String(this.subMatrixIndices);
/* 189 */       workingIndices = workingIndices + "QuadriCubicSpline rows  " + i;
/* 190 */       for (int j = 0; j < this.mPoints; j++) {
/* 191 */         workingIndices = workingIndices + ",  " + j;
/* 192 */         for (int k = 0; k < this.lPoints; k++) {
/* 193 */           workingIndices = workingIndices + ",  " + k + ": ";
/* 194 */           for (int l = 0; l < this.kPoints; l++) {
/* 195 */             yTempml[j][k][l] = this.y[i][j][k][l];
/*     */           }
/*     */         }
/*     */       }
/* 199 */       this.tcsn[i].setSubMatrix(workingIndices);
/* 200 */       this.tcsn[i].resetData(this.x2, this.x3, this.x4, yTempml);
/*     */     }
/* 202 */     double[] yTempm = new double[this.nPoints];
/*     */     
/* 204 */     for (int i = 0; i < this.nPoints; i++) {
/* 205 */       if (this.derivCalculated) this.tcsn[i].setDeriv(this.d2ydx2[i]);
/* 206 */       yTempm[i] = this.tcsn[i].interpolate(xx2, xx3, xx4);
/* 207 */       if (!this.derivCalculated) this.d2ydx2[i] = this.tcsn[i].getDeriv();
/*     */     }
/* 209 */     this.derivCalculated = true;
/*     */     
/* 211 */     String workingIndices = new String(this.subMatrixIndices);
/* 212 */     workingIndices = workingIndices + "QuadriCubicSpline interpolated column:  ";
/* 213 */     this.csm.setSubMatrix(workingIndices);
/* 214 */     this.csm.resetData(this.x1, yTempm);
/* 215 */     return this.csm.interpolate(xx1);
/*     */   }
/*     */   
/*     */   public double[][][][] getDeriv()
/*     */   {
/* 220 */     return this.d2ydx2;
/*     */   }
/*     */ }


/* Location:              /home/menick/Scrivania/ProcessMining/CNMining/CNMining.jar!/flanagan/interpolation/QuadriCubicSpline.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       0.7.1
 */