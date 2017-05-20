/*     */ package org.apache.commons.math.ode;
/*     */ 
/*     */ import java.io.IOException;
/*     */ import java.io.ObjectInput;
/*     */ import java.io.ObjectOutput;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ class DormandPrince853StepInterpolator
/*     */   extends RungeKuttaStepInterpolator
/*     */ {
/*     */   private double[][] yDotKLast;
/*     */   private double[][] v;
/*     */   private boolean vectorsInitialized;
/*     */   private static final double b_01 = 0.054293734116568765D;
/*     */   private static final double b_06 = 4.450312892752409D;
/*     */   private static final double b_07 = 1.8915178993145003D;
/*     */   private static final double b_08 = -5.801203960010585D;
/*     */   private static final double b_09 = 0.3111643669578199D;
/*     */   private static final double b_10 = -0.1521609496625161D;
/*     */   private static final double b_11 = 0.20136540080403034D;
/*     */   private static final double b_12 = 0.04471061572777259D;
/*     */   private static final double c14 = 0.1D;
/*     */   private static final double k14_01 = 0.0018737681664791894D;
/*     */   private static final double k14_06 = -4.450312892752409D;
/*     */   private static final double k14_07 = -1.6380176890978755D;
/*     */   private static final double k14_08 = 5.554964922539782D;
/*     */   private static final double k14_09 = -0.4353557902216363D;
/*     */   private static final double k14_10 = 0.30545274794128174D;
/*     */   private static final double k14_11 = -0.19316434850839564D;
/*     */   private static final double k14_12 = -0.03714271806722689D;
/*     */   private static final double k14_13 = -0.008298D;
/*     */   private static final double c15 = 0.2D;
/*     */   
/*     */   public DormandPrince853StepInterpolator()
/*     */   {
/*  48 */     this.yDotKLast = ((double[][])null);
/*  49 */     this.v = ((double[][])null);
/*  50 */     this.vectorsInitialized = false;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public DormandPrince853StepInterpolator(DormandPrince853StepInterpolator interpolator)
/*     */   {
/*  60 */     super(interpolator);
/*     */     
/*  62 */     if (interpolator.currentState == null)
/*     */     {
/*  64 */       this.yDotKLast = ((double[][])null);
/*  65 */       this.v = ((double[][])null);
/*  66 */       this.vectorsInitialized = false;
/*     */     }
/*     */     else
/*     */     {
/*  70 */       int dimension = interpolator.currentState.length;
/*     */       
/*  72 */       this.yDotKLast = new double[3][];
/*  73 */       for (int k = 0; k < this.yDotKLast.length; k++) {
/*  74 */         this.yDotKLast[k] = new double[dimension];
/*  75 */         System.arraycopy(interpolator.yDotKLast[k], 0, this.yDotKLast[k], 0, dimension);
/*     */       }
/*     */       
/*     */ 
/*  79 */       this.v = new double[7][];
/*  80 */       for (int k = 0; k < this.v.length; k++) {
/*  81 */         this.v[k] = new double[dimension];
/*  82 */         System.arraycopy(interpolator.v[k], 0, this.v[k], 0, dimension);
/*     */       }
/*     */       
/*  85 */       this.vectorsInitialized = interpolator.vectorsInitialized;
/*     */     }
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   protected StepInterpolator doCopy()
/*     */   {
/*  95 */     return new DormandPrince853StepInterpolator(this);
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
/*     */   public void reinitialize(FirstOrderDifferentialEquations equations, double[] y, double[][] yDotK, boolean forward)
/*     */   {
/* 122 */     super.reinitialize(equations, y, yDotK, forward);
/*     */     
/* 124 */     int dimension = this.currentState.length;
/*     */     
/* 126 */     this.yDotKLast = new double[3][];
/* 127 */     for (int k = 0; k < this.yDotKLast.length; k++) {
/* 128 */       this.yDotKLast[k] = new double[dimension];
/*     */     }
/*     */     
/* 131 */     this.v = new double[7][];
/* 132 */     for (int k = 0; k < this.v.length; k++) {
/* 133 */       this.v[k] = new double[dimension];
/*     */     }
/*     */     
/* 136 */     this.vectorsInitialized = false;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   public void storeTime(double t)
/*     */   {
/* 144 */     super.storeTime(t);
/* 145 */     this.vectorsInitialized = false;
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
/*     */   protected void computeInterpolatedState(double theta, double oneMinusThetaH)
/*     */     throws DerivativeException
/*     */   {
/* 162 */     if (!this.vectorsInitialized)
/*     */     {
/* 164 */       if (this.v == null) {
/* 165 */         this.v = new double[7][];
/* 166 */         for (int k = 0; k < 7; k++) {
/* 167 */           this.v[k] = new double[this.interpolatedState.length];
/*     */         }
/*     */       }
/*     */       
/*     */ 
/* 172 */       finalizeStep();
/*     */       
/*     */ 
/* 175 */       for (int i = 0; i < this.interpolatedState.length; i++) {
/* 176 */         this.v[0][i] = (this.h * (0.054293734116568765D * this.yDotK[0][i] + 4.450312892752409D * this.yDotK[5][i] + 1.8915178993145003D * this.yDotK[6][i] + -5.801203960010585D * this.yDotK[7][i] + 0.3111643669578199D * this.yDotK[8][i] + -0.1521609496625161D * this.yDotK[9][i] + 0.20136540080403034D * this.yDotK[10][i] + 0.04471061572777259D * this.yDotK[11][i]));
/*     */         
/*     */ 
/* 179 */         this.v[1][i] = (this.h * this.yDotK[0][i] - this.v[0][i]);
/* 180 */         this.v[2][i] = (this.v[0][i] - this.v[1][i] - this.h * this.yDotK[12][i]);
/* 181 */         for (int k = 0; k < d.length; k++) {
/* 182 */           this.v[(k + 3)][i] = (this.h * (d[k][0] * this.yDotK[0][i] + d[k][1] * this.yDotK[5][i] + d[k][2] * this.yDotK[6][i] + d[k][3] * this.yDotK[7][i] + d[k][4] * this.yDotK[8][i] + d[k][5] * this.yDotK[9][i] + d[k][6] * this.yDotK[10][i] + d[k][7] * this.yDotK[11][i] + d[k][8] * this.yDotK[12][i] + d[k][9] * this.yDotKLast[0][i] + d[k][10] * this.yDotKLast[1][i] + d[k][11] * this.yDotKLast[2][i]));
/*     */         }
/*     */       }
/*     */       
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/* 191 */       this.vectorsInitialized = true;
/*     */     }
/*     */     
/*     */ 
/* 195 */     double eta = oneMinusThetaH / this.h;
/*     */     
/* 197 */     for (int i = 0; i < this.interpolatedState.length; i++) {
/* 198 */       this.interpolatedState[i] = (this.currentState[i] - eta * (this.v[0][i] - theta * (this.v[1][i] + theta * (this.v[2][i] + eta * (this.v[3][i] + theta * (this.v[4][i] + eta * (this.v[5][i] + theta * this.v[6][i])))))));
/*     */     }
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
/*     */   protected void doFinalize()
/*     */     throws DerivativeException
/*     */   {
/* 215 */     if (this.currentState == null)
/*     */     {
/* 217 */       return;
/*     */     }
/*     */     
/*     */ 
/* 221 */     double[] yTmp = new double[this.currentState.length];
/*     */     
/*     */ 
/* 224 */     for (int j = 0; j < this.currentState.length; j++) {
/* 225 */       double s = 0.0018737681664791894D * this.yDotK[0][j] + -4.450312892752409D * this.yDotK[5][j] + -1.6380176890978755D * this.yDotK[6][j] + 5.554964922539782D * this.yDotK[7][j] + -0.4353557902216363D * this.yDotK[8][j] + 0.30545274794128174D * this.yDotK[9][j] + -0.19316434850839564D * this.yDotK[10][j] + -0.03714271806722689D * this.yDotK[11][j] + -0.008298D * this.yDotK[12][j];
/*     */       
/*     */ 
/* 228 */       yTmp[j] = (this.currentState[j] + this.h * s);
/*     */     }
/* 230 */     this.equations.computeDerivatives(this.previousTime + 0.1D * this.h, yTmp, this.yDotKLast[0]);
/*     */     
/*     */ 
/* 233 */     for (int j = 0; j < this.currentState.length; j++) {
/* 234 */       double s = -0.022459085953066622D * this.yDotK[0][j] + -4.422011983080043D * this.yDotK[5][j] + -1.8379759110070617D * this.yDotK[6][j] + 5.746280211439194D * this.yDotK[7][j] + -0.3111643669578199D * this.yDotK[8][j] + 0.1521609496625161D * this.yDotK[9][j] + -0.2014737481327276D * this.yDotK[10][j] + -0.04432804463693693D * this.yDotK[11][j] + -3.4046500868740456E-4D * this.yDotK[12][j] + 0.1413124436746325D * this.yDotKLast[0][j];
/*     */       
/*     */ 
/*     */ 
/* 238 */       yTmp[j] = (this.currentState[j] + this.h * s);
/*     */     }
/* 240 */     this.equations.computeDerivatives(this.previousTime + 0.2D * this.h, yTmp, this.yDotKLast[1]);
/*     */     
/*     */ 
/* 243 */     for (int j = 0; j < this.currentState.length; j++) {
/* 244 */       double s = -0.4831900357003607D * this.yDotK[0][j] + -9.147934308113573D * this.yDotK[5][j] + 5.791903296748099D * this.yDotK[6][j] + 9.870193778407696D * this.yDotK[7][j] + 0.04556282049746119D * this.yDotK[8][j] + 0.1521609496625161D * this.yDotK[9][j] + -0.20136540080403034D * this.yDotK[10][j] + -0.04471061572777259D * this.yDotK[11][j] + -0.0013990241651590145D * this.yDotK[12][j] + 2.9475147891527724D * this.yDotKLast[0][j] + -9.15095847217987D * this.yDotKLast[1][j];
/*     */       
/*     */ 
/*     */ 
/* 248 */       yTmp[j] = (this.currentState[j] + this.h * s);
/*     */     }
/* 250 */     this.equations.computeDerivatives(this.previousTime + 0.7777777777777778D * this.h, yTmp, this.yDotKLast[2]);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public void writeExternal(ObjectOutput out)
/*     */     throws IOException
/*     */   {
/*     */     try
/*     */     {
/* 263 */       finalizeStep();
/*     */     } catch (DerivativeException e) {
/* 265 */       throw new IOException(e.getMessage());
/*     */     }
/* 267 */     out.writeInt(this.currentState.length);
/* 268 */     for (int i = 0; i < this.currentState.length; i++) {
/* 269 */       out.writeDouble(this.yDotKLast[0][i]);
/* 270 */       out.writeDouble(this.yDotKLast[1][i]);
/* 271 */       out.writeDouble(this.yDotKLast[2][i]);
/*     */     }
/*     */     
/*     */ 
/* 275 */     super.writeExternal(out);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public void readExternal(ObjectInput in)
/*     */     throws IOException
/*     */   {
/* 287 */     this.yDotKLast = new double[3][];
/* 288 */     int dimension = in.readInt();
/* 289 */     this.yDotKLast[0] = new double[dimension];
/* 290 */     this.yDotKLast[1] = new double[dimension];
/* 291 */     this.yDotKLast[2] = new double[dimension];
/*     */     
/* 293 */     for (int i = 0; i < dimension; i++) {
/* 294 */       this.yDotKLast[0][i] = in.readDouble();
/* 295 */       this.yDotKLast[1][i] = in.readDouble();
/* 296 */       this.yDotKLast[2][i] = in.readDouble();
/*     */     }
/*     */     
/*     */ 
/* 300 */     super.readExternal(in);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   private static final double k15_01 = -0.022459085953066622D;
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   private static final double k15_06 = -4.422011983080043D;
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   private static final double k15_07 = -1.8379759110070617D;
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   private static final double k15_08 = 5.746280211439194D;
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   private static final double k15_09 = -0.3111643669578199D;
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   private static final double k15_10 = 0.1521609496625161D;
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   private static final double k15_11 = -0.2014737481327276D;
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   private static final double k15_12 = -0.04432804463693693D;
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   private static final double k15_13 = -3.4046500868740456E-4D;
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   private static final double k15_14 = 0.1413124436746325D;
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   private static final double c16 = 0.7777777777777778D;
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   private static final double k16_01 = -0.4831900357003607D;
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   private static final double k16_06 = -9.147934308113573D;
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   private static final double k16_07 = 5.791903296748099D;
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   private static final double k16_08 = 9.870193778407696D;
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   private static final double k16_09 = 0.04556282049746119D;
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   private static final double k16_10 = 0.1521609496625161D;
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   private static final double k16_11 = -0.20136540080403034D;
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   private static final double k16_12 = -0.04471061572777259D;
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   private static final double k16_13 = -0.0013990241651590145D;
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   private static final double k16_14 = 2.9475147891527724D;
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   private static final double k16_15 = -9.15095847217987D;
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/* 449 */   private static final double[][] d = { { -8.428938276109013D, 0.5667149535193777D, -3.0689499459498917D, 2.38466765651207D, 2.1170345824450285D, -0.871391583777973D, 2.2404374302607883D, 0.6315787787694688D, -0.08899033645133331D, 18.148505520854727D, -9.194632392478356D, -4.436036387594894D }, { 10.427508642579134D, 242.28349177525817D, 165.20045171727028D, -374.5467547226902D, -22.113666853125302D, 7.733432668472264D, -30.674084731089398D, -9.332130526430229D, 15.697238121770845D, -31.139403219565178D, -9.35292435884448D, 35.81684148639408D }, { 19.985053242002433D, -387.0373087493518D, -189.17813819516758D, 527.8081592054236D, -11.573902539959631D, 6.8812326946963D, -1.0006050966910838D, 0.7777137798053443D, -2.778205752353508D, -60.19669523126412D, 84.32040550667716D, 11.99229113618279D }, { -25.69393346270375D, -154.18974869023643D, -231.5293791760455D, 357.6391179106141D, 93.4053241836243D, -37.45832313645163D, 104.0996495089623D, 29.8402934266605D, -43.53345659001114D, 96.32455395918828D, -39.17726167561544D, -149.72683625798564D } };
/*     */   private static final long serialVersionUID = 7152276390558450974L;
/*     */ }


/* Location:              /home/menick/Scrivania/ProcessMining/CNMining/CNMining.jar!/org/apache/commons/math/ode/DormandPrince853StepInterpolator.class
 * Java compiler version: 3 (47.0)
 * JD-Core Version:       0.7.1
 */