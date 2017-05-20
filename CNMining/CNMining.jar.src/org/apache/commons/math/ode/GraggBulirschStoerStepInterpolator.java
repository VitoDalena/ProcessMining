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
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ class GraggBulirschStoerStepInterpolator
/*     */   extends AbstractStepInterpolator
/*     */ {
/*     */   private double[] y0Dot;
/*     */   private double[] y1;
/*     */   private double[] y1Dot;
/*     */   private double[][] yMidDots;
/*     */   private double[][] polynoms;
/*     */   private double[] errfac;
/*     */   private int currentDegree;
/*     */   private static final long serialVersionUID = 7320613236731409847L;
/*     */   
/*     */   private void resetTables(int maxDegree)
/*     */   {
/* 107 */     if (maxDegree < 0) {
/* 108 */       this.polynoms = ((double[][])null);
/* 109 */       this.errfac = null;
/* 110 */       this.currentDegree = -1;
/*     */     }
/*     */     else {
/* 113 */       double[][] newPols = new double[maxDegree + 1][];
/* 114 */       if (this.polynoms != null) {
/* 115 */         System.arraycopy(this.polynoms, 0, newPols, 0, this.polynoms.length);
/* 116 */         for (int i = this.polynoms.length; i < newPols.length; i++) {
/* 117 */           newPols[i] = new double[this.currentState.length];
/*     */         }
/*     */       } else {
/* 120 */         for (int i = 0; i < newPols.length; i++) {
/* 121 */           newPols[i] = new double[this.currentState.length];
/*     */         }
/*     */       }
/* 124 */       this.polynoms = newPols;
/*     */       
/*     */ 
/* 127 */       if (maxDegree <= 4) {
/* 128 */         this.errfac = null;
/*     */       } else {
/* 130 */         this.errfac = new double[maxDegree - 4];
/* 131 */         for (int i = 0; i < this.errfac.length; i++) {
/* 132 */           int ip5 = i + 5;
/* 133 */           this.errfac[i] = (1.0D / (ip5 * ip5));
/* 134 */           double e = 0.5D * Math.sqrt((i + 1) / ip5);
/* 135 */           for (int j = 0; j <= i; j++) {
/* 136 */             this.errfac[i] *= e / (j + 1);
/*     */           }
/*     */         }
/*     */       }
/*     */       
/* 141 */       this.currentDegree = 0;
/*     */     }
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public GraggBulirschStoerStepInterpolator()
/*     */   {
/* 152 */     this.y0Dot = null;
/* 153 */     this.y1 = null;
/* 154 */     this.y1Dot = null;
/* 155 */     this.yMidDots = ((double[][])null);
/* 156 */     resetTables(-1);
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
/*     */   public GraggBulirschStoerStepInterpolator(double[] y, double[] y0Dot, double[] y1, double[] y1Dot, double[][] yMidDots, boolean forward)
/*     */   {
/* 176 */     super(y, forward);
/* 177 */     this.y0Dot = y0Dot;
/* 178 */     this.y1 = y1;
/* 179 */     this.y1Dot = y1Dot;
/* 180 */     this.yMidDots = yMidDots;
/*     */     
/* 182 */     resetTables(yMidDots.length + 4);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public GraggBulirschStoerStepInterpolator(GraggBulirschStoerStepInterpolator interpolator)
/*     */   {
/* 194 */     super(interpolator);
/*     */     
/* 196 */     int dimension = this.currentState.length;
/*     */     
/*     */ 
/*     */ 
/* 200 */     this.y0Dot = null;
/* 201 */     this.y1 = null;
/* 202 */     this.y1Dot = null;
/* 203 */     this.yMidDots = ((double[][])null);
/*     */     
/*     */ 
/* 206 */     if (interpolator.polynoms == null) {
/* 207 */       this.polynoms = ((double[][])null);
/* 208 */       this.currentDegree = -1;
/*     */     } else {
/* 210 */       resetTables(interpolator.currentDegree);
/* 211 */       for (int i = 0; i < this.polynoms.length; i++) {
/* 212 */         this.polynoms[i] = new double[dimension];
/* 213 */         System.arraycopy(interpolator.polynoms[i], 0, this.polynoms[i], 0, dimension);
/*     */       }
/*     */       
/* 216 */       this.currentDegree = interpolator.currentDegree;
/*     */     }
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   protected StepInterpolator doCopy()
/*     */   {
/* 225 */     return new GraggBulirschStoerStepInterpolator(this);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public void computeCoefficients(int mu, double h)
/*     */   {
/* 235 */     if ((this.polynoms == null) || (this.polynoms.length <= mu + 4)) {
/* 236 */       resetTables(mu + 4);
/*     */     }
/*     */     
/* 239 */     this.currentDegree = (mu + 4);
/*     */     
/* 241 */     for (int i = 0; i < this.currentState.length; i++)
/*     */     {
/* 243 */       double yp0 = h * this.y0Dot[i];
/* 244 */       double yp1 = h * this.y1Dot[i];
/* 245 */       double ydiff = this.y1[i] - this.currentState[i];
/* 246 */       double aspl = ydiff - yp1;
/* 247 */       double bspl = yp0 - ydiff;
/*     */       
/* 249 */       this.polynoms[0][i] = this.currentState[i];
/* 250 */       this.polynoms[1][i] = ydiff;
/* 251 */       this.polynoms[2][i] = aspl;
/* 252 */       this.polynoms[3][i] = bspl;
/*     */       
/* 254 */       if (mu < 0) {
/* 255 */         return;
/*     */       }
/*     */       
/*     */ 
/* 259 */       double ph0 = 0.5D * (this.currentState[i] + this.y1[i]) + 0.125D * (aspl + bspl);
/* 260 */       this.polynoms[4][i] = (16.0D * (this.yMidDots[0][i] - ph0));
/*     */       
/* 262 */       if (mu > 0) {
/* 263 */         double ph1 = ydiff + 0.25D * (aspl - bspl);
/* 264 */         this.polynoms[5][i] = (16.0D * (this.yMidDots[1][i] - ph1));
/*     */         
/* 266 */         if (mu > 1) {
/* 267 */           double ph2 = yp1 - yp0;
/* 268 */           this.polynoms[6][i] = (16.0D * (this.yMidDots[2][i] - ph2 + this.polynoms[4][i]));
/*     */           
/* 270 */           if (mu > 2) {
/* 271 */             double ph3 = 6.0D * (bspl - aspl);
/* 272 */             this.polynoms[7][i] = (16.0D * (this.yMidDots[3][i] - ph3 + 3.0D * this.polynoms[5][i]));
/*     */             
/* 274 */             for (int j = 4; j <= mu; j++) {
/* 275 */               double fac1 = 0.5D * j * (j - 1);
/* 276 */               double fac2 = 2.0D * fac1 * (j - 2) * (j - 3);
/* 277 */               this.polynoms[(j + 4)][i] = (16.0D * (this.yMidDots[j][i] + fac1 * this.polynoms[(j + 2)][i] - fac2 * this.polynoms[j][i]));
/*     */             }
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
/*     */ 
/*     */ 
/*     */   public double estimateError(double[] scale)
/*     */   {
/* 293 */     double error = 0.0D;
/* 294 */     if (this.currentDegree >= 5) {
/* 295 */       for (int i = 0; i < this.currentState.length; i++) {
/* 296 */         double e = this.polynoms[this.currentDegree][i] / scale[i];
/* 297 */         error += e * e;
/*     */       }
/* 299 */       error = Math.sqrt(error / this.currentState.length) * this.errfac[(this.currentDegree - 5)];
/*     */     }
/* 301 */     return error;
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
/* 318 */     int dimension = this.currentState.length;
/*     */     
/* 320 */     double oneMinusTheta = 1.0D - theta;
/* 321 */     double theta05 = theta - 0.5D;
/* 322 */     double t4 = theta * oneMinusTheta;
/* 323 */     t4 *= t4;
/*     */     
/* 325 */     for (int i = 0; i < dimension; i++) {
/* 326 */       this.interpolatedState[i] = (this.polynoms[0][i] + theta * (this.polynoms[1][i] + oneMinusTheta * (this.polynoms[2][i] * theta + this.polynoms[3][i] * oneMinusTheta)));
/*     */       
/*     */ 
/*     */ 
/*     */ 
/* 331 */       if (this.currentDegree > 3) {
/* 332 */         double c = this.polynoms[this.currentDegree][i];
/* 333 */         for (int j = this.currentDegree - 1; j > 3; j--) {
/* 334 */           c = this.polynoms[j][i] + c * theta05 / (j - 3);
/*     */         }
/* 336 */         this.interpolatedState[i] += t4 * c;
/*     */       }
/*     */     }
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
/* 349 */     int dimension = this.currentState.length;
/*     */     
/*     */ 
/* 352 */     writeBaseExternal(out);
/*     */     
/*     */ 
/* 355 */     out.writeInt(this.currentDegree);
/* 356 */     for (int k = 0; k <= this.currentDegree; k++) {
/* 357 */       for (int l = 0; l < dimension; l++) {
/* 358 */         out.writeDouble(this.polynoms[k][l]);
/*     */       }
/*     */     }
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
/* 372 */     double t = readBaseExternal(in);
/* 373 */     int dimension = this.currentState.length;
/*     */     
/*     */ 
/* 376 */     int degree = in.readInt();
/* 377 */     resetTables(degree);
/* 378 */     this.currentDegree = degree;
/*     */     
/* 380 */     for (int k = 0; k <= this.currentDegree; k++) {
/* 381 */       for (int l = 0; l < dimension; l++) {
/* 382 */         this.polynoms[k][l] = in.readDouble();
/*     */       }
/*     */     }
/*     */     
/*     */     try
/*     */     {
/* 388 */       setInterpolatedTime(t);
/*     */     } catch (DerivativeException e) {
/* 390 */       throw new IOException(e.getMessage());
/*     */     }
/*     */   }
/*     */ }


/* Location:              /home/menick/Scrivania/ProcessMining/CNMining/CNMining.jar!/org/apache/commons/math/ode/GraggBulirschStoerStepInterpolator.class
 * Java compiler version: 3 (47.0)
 * JD-Core Version:       0.7.1
 */