/*     */ package flanagan.interpolation;
/*     */ 
/*     */ import flanagan.math.Fmath;
/*     */ import java.io.PrintStream;
/*     */ import java.lang.reflect.Array;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class PolyCubicSpline
/*     */ {
/*  47 */   private int nDimensions = 0;
/*  48 */   private Object fOfX = null;
/*     */   
/*  50 */   private Object internalArray = null;
/*     */   
/*  52 */   private Object xArrays = null;
/*     */   
/*     */ 
/*  55 */   private double[][] xArray = (double[][])null;
/*  56 */   private double yValue = 0.0D;
/*  57 */   private double[] xMin = null;
/*  58 */   private double[] xMax = null;
/*  59 */   private boolean calculationDone = false;
/*  60 */   private String subMatrixIndices = "PolyCubicSpline submatrices ";
/*  61 */   private boolean averageIdenticalAbscissae = false;
/*     */   
/*  63 */   private static double potentialRoundingError = 5.0E-15D;
/*  64 */   private static boolean roundingCheck = true;
/*     */   
/*     */ 
/*     */ 
/*     */   public PolyCubicSpline(Object xArrays, Object fOfX)
/*     */   {
/*  70 */     this.fOfX = Fmath.copyObject(fOfX);
/*  71 */     this.xArrays = Fmath.copyObject(xArrays);
/*     */     
/*     */ 
/*  74 */     Object internalArray = Fmath.copyObject(fOfX);
/*  75 */     this.nDimensions = 1;
/*  76 */     while (!((internalArray = Array.get(internalArray, 0)) instanceof Double)) { this.nDimensions += 1;
/*     */     }
/*     */     
/*  79 */     if (((this.xArrays instanceof double[])) && (this.nDimensions == 1)) {
/*  80 */       double[][] xArraysTemp = new double[1][];
/*  81 */       xArraysTemp[0] = ((double[])(double[])this.xArrays);
/*  82 */       this.xArrays = xArraysTemp;
/*     */ 
/*     */     }
/*  85 */     else if (!(this.xArrays instanceof double[][])) { throw new IllegalArgumentException("xArrays should be a two dimensional array of doubles");
/*     */     }
/*     */     
/*     */ 
/*  89 */     this.xArray = ((double[][])this.xArrays);
/*  90 */     limits();
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */   public static void noRoundingErrorCheck()
/*     */   {
/*  97 */     roundingCheck = false;
/*  98 */     QuadriCubicSpline.noRoundingErrorCheck();
/*  99 */     TriCubicSpline.noRoundingErrorCheck();
/* 100 */     BiCubicSpline.noRoundingErrorCheck();
/* 101 */     CubicSpline.noRoundingErrorCheck();
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   public static void potentialRoundingError(double potentialRoundingError)
/*     */   {
/* 109 */     potentialRoundingError = potentialRoundingError;
/* 110 */     QuadriCubicSpline.potentialRoundingError(potentialRoundingError);
/* 111 */     TriCubicSpline.potentialRoundingError(potentialRoundingError);
/* 112 */     BiCubicSpline.potentialRoundingError(potentialRoundingError);
/* 113 */     CubicSpline.potentialRoundingError(potentialRoundingError);
/*     */   }
/*     */   
/*     */   private void limits()
/*     */   {
/* 118 */     this.xMin = new double[this.nDimensions];
/* 119 */     this.xMax = new double[this.nDimensions];
/* 120 */     for (int i = 0; i < this.nDimensions; i++) {
/* 121 */       this.xMin[i] = Fmath.minimum(this.xArray[i]);
/* 122 */       this.xMax[i] = Fmath.maximum(this.xArray[i]);
/*     */     }
/*     */   }
/*     */   
/*     */   public double[] getXmin()
/*     */   {
/* 128 */     return this.xMin;
/*     */   }
/*     */   
/*     */   public double[] getXmax()
/*     */   {
/* 133 */     return this.xMax;
/*     */   }
/*     */   
/*     */   public int getNumberOfDimensions()
/*     */   {
/* 138 */     return this.nDimensions;
/*     */   }
/*     */   
/*     */   public double[] getLimits()
/*     */   {
/* 143 */     double[] limits = { this.xMin[0], this.xMax[0], this.xMin[1], this.xMax[1], this.xMin[2], this.xMax[2], this.xMin[3], this.xMax[3] };
/* 144 */     return limits;
/*     */   }
/*     */   
/*     */   public void displayLimits()
/*     */   {
/* 149 */     System.out.println(" ");
/* 150 */     for (int i = 0; i < this.nDimensions; i++) {
/* 151 */       System.out.println("The limits to the x array " + i + " are " + this.xMin[i] + " and " + this.xMax[i]);
/*     */     }
/* 153 */     System.out.println(" ");
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */   public void averageIdenticalAbscissae()
/*     */   {
/* 160 */     this.averageIdenticalAbscissae = true;
/*     */   }
/*     */   
/*     */   public void setSubMatrix(String subMatrixVector)
/*     */   {
/* 165 */     this.subMatrixIndices = this.subMatrixIndices;
/*     */   }
/*     */   
/*     */ 
/*     */   public double interpolate(double[] unknownCoord)
/*     */   {
/* 171 */     int nUnknown = unknownCoord.length;
/* 172 */     if (nUnknown != this.nDimensions) { throw new IllegalArgumentException("Number of unknown value coordinates, " + nUnknown + ", does not equal the number of tabulated data dimensions, " + this.nDimensions);
/*     */     }
/* 174 */     int kk = 0;
/* 175 */     switch (this.nDimensions) {
/* 176 */     case 0:  throw new IllegalArgumentException("data array must have at least one dimension");
/*     */     case 1: 
/* 178 */       CubicSpline cs = new CubicSpline(this.xArray[0], (double[])this.fOfX);
/* 179 */       if (this.calculationDone) cs.setDeriv((double[])this.internalArray);
/* 180 */       if (this.averageIdenticalAbscissae) cs.averageIdenticalAbscissae();
/* 181 */       this.yValue = cs.interpolate(unknownCoord[0]);
/* 182 */       if (!this.calculationDone) {
/* 183 */         double[] deriv = cs.getDeriv();
/* 184 */         this.internalArray = deriv;
/* 185 */         this.calculationDone = true; }
/* 186 */       break;
/*     */     
/*     */     case 2: 
/* 189 */       BiCubicSpline bcs = new BiCubicSpline(this.xArray[0], this.xArray[1], (double[][])this.fOfX);
/* 190 */       if (this.calculationDone) bcs.setDeriv((double[][])this.internalArray);
/* 191 */       if (this.averageIdenticalAbscissae) bcs.averageIdenticalAbscissae();
/* 192 */       this.yValue = bcs.interpolate(unknownCoord[0], unknownCoord[1]);
/* 193 */       if (!this.calculationDone) {
/* 194 */         double[][] deriv = bcs.getDeriv();
/* 195 */         this.internalArray = deriv;
/* 196 */         this.calculationDone = true; }
/* 197 */       break;
/*     */     
/*     */     case 3: 
/* 200 */       TriCubicSpline tcs = new TriCubicSpline(this.xArray[0], this.xArray[1], this.xArray[2], (double[][][])this.fOfX);
/* 201 */       if (this.calculationDone) tcs.setDeriv((double[][][])this.internalArray);
/* 202 */       if (this.averageIdenticalAbscissae) tcs.averageIdenticalAbscissae();
/* 203 */       this.yValue = tcs.interpolate(unknownCoord[0], unknownCoord[1], unknownCoord[2]);
/* 204 */       if (!this.calculationDone) {
/* 205 */         double[][][] deriv = tcs.getDeriv();
/* 206 */         this.internalArray = deriv;
/* 207 */         this.calculationDone = true; }
/* 208 */       break;
/*     */     
/*     */ 
/*     */ 
/*     */     default: 
/* 213 */       Object obj = this.fOfX;
/* 214 */       int dimOne = Array.getLength(obj);
/* 215 */       double[] csArray = new double[dimOne];
/* 216 */       double[][] newXarrays = new double[this.nDimensions - 1][];
/* 217 */       double[] newCoord = new double[this.nDimensions - 1];
/* 218 */       for (int i = 0; i < this.nDimensions - 1; i++) {
/* 219 */         newXarrays[i] = this.xArray[(i + 1)];
/* 220 */         newCoord[i] = unknownCoord[(i + 1)];
/*     */       }
/* 222 */       Object[] objDeriv = new Object[dimOne];
/* 223 */       if (this.calculationDone) objDeriv = (Object[])this.internalArray;
/* 224 */       for (int i = 0; i < dimOne; i++) {
/* 225 */         Object objT = Array.get(obj, i);
/* 226 */         PolyCubicSpline pcs = new PolyCubicSpline(newXarrays, objT);
/* 227 */         if (this.averageIdenticalAbscissae) pcs.averageIdenticalAbscissae();
/* 228 */         String workingIndices = new String(this.subMatrixIndices);
/* 229 */         workingIndices = workingIndices + "" + i + ", ";
/* 230 */         pcs.setSubMatrix(workingIndices);
/* 231 */         if (this.calculationDone) pcs.setDeriv(objDeriv[i]);
/* 232 */         csArray[i] = pcs.interpolate(newCoord);
/* 233 */         if (!this.calculationDone) objDeriv[i] = pcs.getDeriv();
/*     */       }
/* 235 */       this.internalArray = objDeriv;
/* 236 */       this.calculationDone = true;
/*     */       
/*     */ 
/*     */ 
/* 240 */       CubicSpline ncs = new CubicSpline(this.xArray[0], csArray);
/* 241 */       String workingIndices = "PolyCubic Spline interpolated column: ";
/* 242 */       ncs.setSubMatrix(workingIndices);
/* 243 */       this.yValue = ncs.interpolate(unknownCoord[0]);
/*     */     }
/*     */     
/* 246 */     return this.yValue;
/*     */   }
/*     */   
/*     */   public void setDeriv(Object internalArray)
/*     */   {
/* 251 */     this.internalArray = internalArray;
/*     */   }
/*     */   
/*     */   public Object getDeriv()
/*     */   {
/* 256 */     return this.internalArray;
/*     */   }
/*     */ }


/* Location:              /home/menick/Scrivania/ProcessMining/CNMining/CNMining.jar!/flanagan/interpolation/PolyCubicSpline.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       0.7.1
 */