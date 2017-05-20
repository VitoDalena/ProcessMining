/*     */ package flanagan.optics;
/*     */ 
/*     */ import flanagan.analysis.ErrorProp;
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
/*     */ public class GratingCoupler
/*     */   extends PlanarWaveguide
/*     */ {
/*  50 */   private double[] thicknessesTE = null;
/*  51 */   private double[] anglesDegTE = null;
/*  52 */   private double[] anglesRadTE = null;
/*  53 */   private double[] errorsDegTE = null;
/*  54 */   private double[] errorsRadTE = null;
/*  55 */   private double[] modeNumbersTE = null;
/*  56 */   private double[] effectiveRefractiveIndicesTE = null;
/*  57 */   private double[] effectiveErrorsTE = null;
/*  58 */   private int numberOfTEmeasurementsGrating = 0;
/*  59 */   private boolean setMeasurementsTEgrating = false;
/*  60 */   private boolean setTEerrors = false;
/*     */   
/*  62 */   private boolean calcEffectiveDone = false;
/*     */   
/*  64 */   private double[] thicknessesTM = null;
/*  65 */   private double[] anglesDegTM = null;
/*  66 */   private double[] anglesRadTM = null;
/*  67 */   private double[] errorsDegTM = null;
/*  68 */   private double[] errorsRadTM = null;
/*  69 */   private double[] modeNumbersTM = null;
/*  70 */   private double[] effectiveRefractiveIndicesTM = null;
/*  71 */   private double[] effectiveErrorsTM = null;
/*  72 */   private int numberOfTMmeasurementsGrating = 0;
/*  73 */   private boolean setMeasurementsTMgrating = false;
/*  74 */   private boolean setTMerrors = false;
/*     */   
/*  76 */   private int numberOfMeasurementsGrating = 0;
/*  77 */   private boolean setMeasurementsGrating = false;
/*     */   
/*  79 */   private double gratingPitch = 0.0D;
/*  80 */   private boolean setGratingPitch = false;
/*  81 */   private int[] gratingOrderTE = null;
/*  82 */   private boolean setGratingOrderTE = false;
/*  83 */   private int[] gratingOrderTM = null;
/*  84 */   private boolean setGratingOrderTM = false;
/*     */   
/*  86 */   private double superstrateRI = 0.0D;
/*  87 */   private boolean setSuperstrateRI = false;
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public void setGratingPitch(double pitch)
/*     */   {
/*  97 */     this.gratingPitch = pitch;
/*  98 */     this.setGratingPitch = true;
/*  99 */     if ((this.setMeasurementsGrating) && (this.setWavelength)) calcEffectiveRefractiveIndices();
/*     */   }
/*     */   
/*     */   public void setSetTEmodeGratingOrder(int[] order)
/*     */   {
/* 104 */     this.gratingOrderTE = order;
/* 105 */     int m = order.length;
/* 106 */     if ((this.setMeasurementsTEgrating) && (m != this.numberOfTEmeasurementsGrating)) throw new IllegalArgumentException("Number of grating orders entered, " + m + ", is not equal to the number of measurements previously entered, " + this.numberOfTEmeasurementsGrating);
/* 107 */     if ((this.setMeasurementsGrating) && (this.setGratingPitch) && (this.setWavelength)) calcEffectiveRefractiveIndices();
/*     */   }
/*     */   
/*     */   public void setSetTMmodeGratingOrder(int[] order)
/*     */   {
/* 112 */     this.gratingOrderTM = order;
/* 113 */     int m = order.length;
/* 114 */     if ((this.setMeasurementsTMgrating) && (m != this.numberOfTMmeasurementsGrating)) throw new IllegalArgumentException("Number of grating orders entered, " + m + ", is not equal to the number of measurements previously entered, " + this.numberOfTEmeasurementsGrating);
/* 115 */     if ((this.setMeasurementsGrating) && (this.setGratingPitch) && (this.setWavelength)) calcEffectiveRefractiveIndices();
/*     */   }
/*     */   
/*     */   public void setSuperstrateRefractiveIndex(double index)
/*     */   {
/* 120 */     if (this.calcEffectiveDone) clearData();
/* 121 */     this.superstrateRI = index;
/* 122 */     this.superstrateRefractiveIndex = index;
/* 123 */     this.setSuperstrateRI = true;
/* 124 */     if ((this.setMeasurementsGrating) && (this.setGratingPitch) && (this.setWavelength)) { calcEffectiveRefractiveIndices();
/*     */     }
/*     */   }
/*     */   
/*     */   public double getAnalyteSolutionRefractiveIndex()
/*     */   {
/* 130 */     return super.getSuperstrateRefractiveIndex();
/*     */   }
/*     */   
/*     */   public double getStandardDeviationAnalyteSolutionRefractiveIndex()
/*     */   {
/* 135 */     return super.getStandardDeviationSuperstrateRefractiveIndex();
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */   public void enterTEmodeData(double thickness, double angle, double modeNumber)
/*     */   {
/* 142 */     if (this.setMeasurementsTEgrating) {
/* 143 */       if (this.setTEerrors) throw new IllegalArgumentException("All Entered data must either all have associated errors entered or all have no associated errors entered");
/* 144 */       int nNew = this.numberOfTEmeasurementsGrating + 1;
/* 145 */       double[] hold = new double[nNew];
/* 146 */       for (int i = 0; i < this.numberOfTEmeasurementsGrating; i++) hold[i] = this.thicknessesTE[i];
/* 147 */       hold[this.numberOfTEmeasurementsGrating] = thickness;
/* 148 */       this.thicknessesTE = hold;
/* 149 */       for (int i = 0; i < this.numberOfTEmeasurementsGrating; i++) hold[i] = this.anglesDegTE[i];
/* 150 */       hold[this.numberOfTEmeasurementsGrating] = angle;
/* 151 */       this.anglesDegTE = hold;
/* 152 */       this.anglesRadTE = hold;
/* 153 */       this.errorsDegTE = hold;
/* 154 */       this.errorsRadTE = hold;
/* 155 */       for (int i = 0; i < nNew; i++) {
/* 156 */         this.anglesRadTE[i] = Math.toRadians(this.anglesDegTE[i]);
/* 157 */         this.errorsDegTE[i] = 0.0D;
/* 158 */         this.errorsRadTE[i] = 0.0D;
/*     */       }
/* 160 */       for (int i = 0; i < this.numberOfTEmeasurementsGrating; i++) hold[i] = this.modeNumbersTE[i];
/* 161 */       hold[this.numberOfTEmeasurementsGrating] = modeNumber;
/* 162 */       this.numberOfTEmeasurementsGrating = nNew;
/*     */     }
/*     */     else {
/* 165 */       this.thicknessesTE = new double[1];
/* 166 */       this.thicknessesTE[0] = thickness;
/* 167 */       this.anglesDegTE = new double[1];
/* 168 */       this.anglesDegTE[0] = angle;
/* 169 */       this.anglesRadTE = new double[1];
/* 170 */       this.anglesRadTE[0] = Math.toRadians(angle);
/* 171 */       this.errorsDegTE = new double[1];
/* 172 */       this.errorsDegTE[0] = 0.0D;
/* 173 */       this.errorsRadTE = new double[1];
/* 174 */       this.errorsRadTE[0] = 0.0D;
/* 175 */       this.modeNumbersTE = new double[1];
/* 176 */       this.modeNumbersTE[0] = modeNumber;
/* 177 */       this.numberOfTEmeasurementsGrating = 1;
/*     */     }
/* 179 */     this.numberOfMeasurementsGrating = (this.numberOfTEmeasurementsGrating + this.numberOfTMmeasurementsGrating);
/* 180 */     this.setMeasurementsTEgrating = true;
/* 181 */     this.setMeasurementsGrating = true;
/* 182 */     if ((this.setGratingPitch) && (this.setWavelength)) { calcTEmodeEffectiveRefractiveIndices();
/*     */     }
/*     */   }
/*     */   
/*     */ 
/*     */   public void enterTMmodeData(double thickness, double angle, double modeNumber)
/*     */   {
/* 189 */     if (this.setMeasurementsTMgrating) {
/* 190 */       if (this.setTMerrors) throw new IllegalArgumentException("All Entered data must either all have associated errors entered or all have no associated errors entered");
/* 191 */       int nNew = this.numberOfTMmeasurementsGrating + 1;
/* 192 */       double[] hold = new double[nNew];
/* 193 */       for (int i = 0; i < this.numberOfTMmeasurementsGrating; i++) hold[i] = this.thicknessesTM[i];
/* 194 */       hold[this.numberOfTMmeasurementsGrating] = thickness;
/* 195 */       this.thicknessesTM = hold;
/* 196 */       for (int i = 0; i < this.numberOfTMmeasurementsGrating; i++) hold[i] = this.anglesDegTM[i];
/* 197 */       hold[this.numberOfTMmeasurementsGrating] = angle;
/* 198 */       this.anglesDegTM = hold;
/* 199 */       this.anglesRadTM = hold;
/* 200 */       this.errorsDegTM = hold;
/* 201 */       this.errorsRadTM = hold;
/* 202 */       for (int i = 0; i < nNew; i++) {
/* 203 */         this.anglesRadTM[i] = Math.toRadians(this.anglesDegTM[i]);
/* 204 */         this.errorsDegTM[i] = 0.0D;
/* 205 */         this.errorsRadTM[i] = 0.0D;
/*     */       }
/* 207 */       for (int i = 0; i < this.numberOfTMmeasurementsGrating; i++) hold[i] = this.modeNumbersTM[i];
/* 208 */       hold[this.numberOfTMmeasurementsGrating] = modeNumber;
/* 209 */       this.numberOfTMmeasurementsGrating = nNew;
/*     */     }
/*     */     else {
/* 212 */       this.thicknessesTM = new double[1];
/* 213 */       this.thicknessesTM[0] = thickness;
/* 214 */       this.anglesDegTM = new double[1];
/* 215 */       this.anglesDegTM[0] = angle;
/* 216 */       this.anglesRadTM = new double[1];
/* 217 */       this.anglesRadTM[0] = Math.toRadians(angle);
/* 218 */       this.errorsDegTM = new double[1];
/* 219 */       this.errorsDegTM[0] = 0.0D;
/* 220 */       this.errorsRadTM = new double[1];
/* 221 */       this.errorsRadTM[0] = 0.0D;
/* 222 */       this.modeNumbersTM = new double[1];
/* 223 */       this.modeNumbersTM[0] = modeNumber;
/* 224 */       this.numberOfTMmeasurementsGrating = 1;
/*     */     }
/* 226 */     this.numberOfMeasurementsGrating = (this.numberOfTEmeasurementsGrating + this.numberOfTMmeasurementsGrating);
/* 227 */     this.setMeasurementsTMgrating = true;
/* 228 */     this.setMeasurementsGrating = true;
/* 229 */     if ((this.setGratingPitch) && (this.setWavelength)) { calcTMmodeEffectiveRefractiveIndices();
/*     */     }
/*     */   }
/*     */   
/*     */   public void enterTEmodeData(double[] thicknesses, double[] angles, double[] modeNumbers)
/*     */   {
/* 235 */     int o = thicknesses.length;
/* 236 */     int n = angles.length;
/* 237 */     if (n != o) throw new IllegalArgumentException("number of thicknesses, " + o + ", does not equal the number of coupling angles, " + n);
/* 238 */     int m = modeNumbers.length;
/* 239 */     if (m != o) { throw new IllegalArgumentException("number of thicknesses, " + o + ", does not equal the number of mode numbers, " + m);
/*     */     }
/* 241 */     if (this.setMeasurementsTEgrating) {
/* 242 */       if (this.setTEerrors) throw new IllegalArgumentException("All Entered data must either all have associated errors entered or all have no associated errors entered");
/* 243 */       int nNew = this.numberOfTEmeasurementsGrating + o;
/* 244 */       double[] hold = new double[nNew];
/* 245 */       for (int i = 0; i < this.numberOfTEmeasurementsGrating; i++) hold[i] = this.thicknessesTE[i];
/* 246 */       for (int i = 0; i < o; i++) hold[(this.numberOfTEmeasurementsGrating + i)] = thicknesses[i];
/* 247 */       this.thicknessesTE = hold;
/* 248 */       for (int i = 0; i < this.numberOfTEmeasurementsGrating; i++) hold[i] = this.anglesDegTE[i];
/* 249 */       for (int i = 0; i < o; i++) hold[(this.numberOfTEmeasurementsGrating + i)] = angles[i];
/* 250 */       this.anglesDegTE = hold;
/* 251 */       this.anglesRadTE = hold;
/* 252 */       this.errorsDegTE = hold;
/* 253 */       this.errorsRadTE = hold;
/* 254 */       for (int i = 0; i < nNew; i++) {
/* 255 */         this.anglesRadTE[i] = Math.toRadians(this.anglesDegTE[i]);
/* 256 */         this.errorsDegTE[i] = 0.0D;
/* 257 */         this.errorsRadTE[i] = 0.0D;
/*     */       }
/* 259 */       for (int i = 0; i < this.numberOfTEmeasurementsGrating; i++) hold[i] = this.modeNumbersTE[i];
/* 260 */       for (int i = 0; i < o; i++) hold[(this.numberOfTEmeasurementsGrating + i)] = modeNumbers[i];
/* 261 */       this.numberOfTEmeasurementsGrating = nNew;
/*     */     }
/*     */     else {
/* 264 */       this.numberOfTEmeasurementsGrating = o;
/* 265 */       this.thicknessesTE = thicknesses;
/* 266 */       this.anglesDegTE = angles;
/* 267 */       this.anglesRadTE = new double[o];
/* 268 */       this.errorsDegTE = new double[o];
/* 269 */       this.errorsRadTE = new double[o];
/* 270 */       for (int i = 0; i < o; i++) {
/* 271 */         this.anglesRadTE[i] = Math.toRadians(angles[i]);
/* 272 */         this.errorsDegTE[i] = 0.0D;
/* 273 */         this.errorsRadTE[i] = 0.0D;
/*     */       }
/* 275 */       this.modeNumbersTE = modeNumbers;
/*     */     }
/* 277 */     this.numberOfMeasurementsGrating = (this.numberOfTEmeasurementsGrating + this.numberOfTMmeasurementsGrating);
/* 278 */     this.setMeasurementsTEgrating = true;
/* 279 */     this.setMeasurementsGrating = true;
/* 280 */     if ((this.setGratingPitch) && (this.setWavelength)) { calcTEmodeEffectiveRefractiveIndices();
/*     */     }
/*     */   }
/*     */   
/*     */   public void enterTMmodeData(double[] thicknesses, double[] angles, double[] modeNumbers)
/*     */   {
/* 286 */     int o = thicknesses.length;
/* 287 */     int n = angles.length;
/* 288 */     if (n != o) throw new IllegalArgumentException("number of thicknesses, " + o + ", does not equal the number of coupling angles, " + n);
/* 289 */     int m = modeNumbers.length;
/* 290 */     if (m != o) { throw new IllegalArgumentException("number of thicknesses, " + o + ", does not equal the number of mode numbers, " + m);
/*     */     }
/* 292 */     if (this.setMeasurementsTMgrating) {
/* 293 */       if (this.setTMerrors) throw new IllegalArgumentException("All Entered data must either all have associated errors entered or all have no associated errors entered");
/* 294 */       int nNew = this.numberOfTMmeasurementsGrating + o;
/* 295 */       double[] hold = new double[nNew];
/* 296 */       for (int i = 0; i < this.numberOfTMmeasurementsGrating; i++) hold[i] = this.thicknessesTM[i];
/* 297 */       for (int i = 0; i < o; i++) hold[(this.numberOfTMmeasurementsGrating + i)] = thicknesses[i];
/* 298 */       this.thicknessesTM = hold;
/* 299 */       for (int i = 0; i < this.numberOfTMmeasurementsGrating; i++) hold[i] = this.anglesDegTM[i];
/* 300 */       for (int i = 0; i < o; i++) hold[(this.numberOfTMmeasurementsGrating + i)] = angles[i];
/* 301 */       this.anglesDegTM = hold;
/* 302 */       this.anglesRadTM = hold;
/* 303 */       this.errorsDegTM = hold;
/* 304 */       this.errorsRadTM = hold;
/* 305 */       for (int i = 0; i < nNew; i++) {
/* 306 */         this.anglesRadTM[i] = Math.toRadians(this.anglesDegTM[i]);
/* 307 */         this.errorsDegTM[i] = 0.0D;
/* 308 */         this.errorsRadTM[i] = 0.0D;
/*     */       }
/* 310 */       for (int i = 0; i < this.numberOfTMmeasurementsGrating; i++) hold[i] = this.modeNumbersTM[i];
/* 311 */       for (int i = 0; i < o; i++) hold[(this.numberOfTMmeasurementsGrating + i)] = modeNumbers[i];
/* 312 */       this.numberOfTMmeasurementsGrating = nNew;
/*     */     }
/*     */     else {
/* 315 */       this.numberOfTMmeasurementsGrating = o;
/* 316 */       this.thicknessesTM = thicknesses;
/* 317 */       this.anglesDegTM = angles;
/* 318 */       this.anglesRadTM = new double[o];
/* 319 */       this.errorsDegTM = new double[o];
/* 320 */       this.errorsRadTM = new double[o];
/* 321 */       for (int i = 0; i < o; i++) {
/* 322 */         this.anglesRadTM[i] = Math.toRadians(angles[i]);
/* 323 */         this.errorsDegTM[i] = 0.0D;
/* 324 */         this.errorsRadTM[i] = 0.0D;
/*     */       }
/* 326 */       this.modeNumbersTM = modeNumbers;
/*     */     }
/* 328 */     this.numberOfMeasurementsGrating = (this.numberOfTEmeasurementsGrating + this.numberOfTMmeasurementsGrating);
/* 329 */     this.setMeasurementsTMgrating = true;
/* 330 */     this.setMeasurementsGrating = true;
/* 331 */     if ((this.setGratingPitch) && (this.setWavelength)) calcTMmodeEffectiveRefractiveIndices();
/*     */   }
/*     */   
/*     */   public void enterTEmodeData(double thickness, double angle, double error, double modeNumber)
/*     */   {
/* 336 */     if (this.setMeasurementsTEgrating) {
/* 337 */       if (!this.setTEerrors) throw new IllegalArgumentException("All Entered data must either all have associated errors entered or all have no associated errors entered");
/* 338 */       int nNew = this.numberOfTEmeasurementsGrating + 1;
/* 339 */       double[] hold = new double[nNew];
/* 340 */       for (int i = 0; i < this.numberOfTEmeasurementsGrating; i++) hold[i] = this.thicknessesTE[i];
/* 341 */       hold[this.numberOfTEmeasurementsGrating] = thickness;
/* 342 */       this.thicknessesTE = hold;
/* 343 */       for (int i = 0; i < this.numberOfTEmeasurementsGrating; i++) hold[i] = this.anglesDegTE[i];
/* 344 */       hold[this.numberOfTEmeasurementsGrating] = angle;
/* 345 */       this.anglesDegTE = hold;
/* 346 */       for (int i = 0; i < this.numberOfTEmeasurementsGrating; i++) hold[i] = this.errorsDegTE[i];
/* 347 */       hold[this.numberOfTEmeasurementsGrating] = error;
/* 348 */       this.errorsDegTE = hold;
/* 349 */       this.anglesRadTE = hold;
/* 350 */       this.errorsRadTE = hold;
/* 351 */       for (int i = 0; i < nNew; i++) {
/* 352 */         this.anglesRadTE[i] = Math.toRadians(this.anglesDegTE[i]);
/* 353 */         this.errorsRadTE[i] = Math.toRadians(this.errorsDegTE[i]);
/*     */       }
/* 355 */       for (int i = 0; i < this.numberOfTEmeasurementsGrating; i++) hold[i] = this.modeNumbersTE[i];
/* 356 */       hold[this.numberOfTEmeasurementsGrating] = modeNumber;
/* 357 */       this.numberOfTEmeasurementsGrating = nNew;
/*     */     }
/*     */     else {
/* 360 */       this.thicknessesTE = new double[1];
/* 361 */       this.thicknessesTE[0] = thickness;
/* 362 */       this.anglesDegTE = new double[1];
/* 363 */       this.anglesDegTE[0] = angle;
/* 364 */       this.anglesRadTE = new double[1];
/* 365 */       this.anglesRadTE[0] = Math.toRadians(angle);
/* 366 */       this.errorsDegTE = new double[1];
/* 367 */       this.errorsDegTE[0] = error;
/* 368 */       this.errorsRadTE = new double[1];
/* 369 */       this.errorsRadTE[0] = Math.toRadians(error);
/* 370 */       this.modeNumbersTE = new double[1];
/* 371 */       this.modeNumbersTE[0] = modeNumber;
/* 372 */       this.numberOfTEmeasurementsGrating = 1;
/*     */     }
/* 374 */     this.numberOfMeasurementsGrating = (this.numberOfTEmeasurementsGrating + this.numberOfTMmeasurementsGrating);
/* 375 */     this.setMeasurementsTEgrating = true;
/* 376 */     this.setTEerrors = true;
/* 377 */     this.setMeasurementsGrating = true;
/* 378 */     if ((this.setGratingPitch) && (this.setWavelength)) { calcTEmodeEffectiveRefractiveIndices();
/*     */     }
/*     */   }
/*     */   
/*     */ 
/*     */   public void enterTMmodeData(double thickness, double angle, double error, double modeNumber)
/*     */   {
/* 385 */     if (this.setMeasurementsTMgrating) {
/* 386 */       if (!this.setTMerrors) throw new IllegalArgumentException("All Entered data must either all have associated errors entered or all have no associated errors entered");
/* 387 */       int nNew = this.numberOfTMmeasurementsGrating + 1;
/* 388 */       double[] hold = new double[nNew];
/* 389 */       for (int i = 0; i < this.numberOfTMmeasurementsGrating; i++) hold[i] = this.thicknessesTM[i];
/* 390 */       hold[this.numberOfTMmeasurementsGrating] = thickness;
/* 391 */       this.thicknessesTM = hold;
/* 392 */       for (int i = 0; i < this.numberOfTMmeasurementsGrating; i++) hold[i] = this.anglesDegTM[i];
/* 393 */       hold[this.numberOfTMmeasurementsGrating] = angle;
/* 394 */       this.anglesDegTM = hold;
/* 395 */       for (int i = 0; i < this.numberOfTMmeasurementsGrating; i++) hold[i] = this.errorsDegTM[i];
/* 396 */       hold[this.numberOfTMmeasurementsGrating] = error;
/* 397 */       this.errorsDegTM = hold;
/* 398 */       this.anglesRadTM = hold;
/* 399 */       this.errorsRadTM = hold;
/* 400 */       for (int i = 0; i < nNew; i++) {
/* 401 */         this.anglesRadTM[i] = Math.toRadians(this.anglesDegTM[i]);
/* 402 */         this.errorsRadTM[i] = Math.toRadians(this.errorsDegTM[i]);
/*     */       }
/* 404 */       for (int i = 0; i < this.numberOfTMmeasurementsGrating; i++) hold[i] = this.modeNumbersTM[i];
/* 405 */       hold[this.numberOfTMmeasurementsGrating] = modeNumber;
/* 406 */       this.numberOfTMmeasurementsGrating = nNew;
/*     */     }
/*     */     else {
/* 409 */       this.thicknessesTM = new double[1];
/* 410 */       this.thicknessesTM[0] = thickness;
/* 411 */       this.anglesDegTM = new double[1];
/* 412 */       this.anglesDegTM[0] = angle;
/* 413 */       this.anglesRadTM = new double[1];
/* 414 */       this.anglesDegTM[0] = Math.toRadians(angle);
/* 415 */       this.errorsDegTM = new double[1];
/* 416 */       this.errorsDegTM[0] = error;
/* 417 */       this.errorsRadTM = new double[1];
/* 418 */       this.errorsDegTM[0] = Math.toRadians(error);
/* 419 */       this.modeNumbersTM = new double[1];
/* 420 */       this.modeNumbersTM[0] = modeNumber;
/* 421 */       this.numberOfTMmeasurementsGrating = 1;
/*     */     }
/* 423 */     this.numberOfMeasurementsGrating = (this.numberOfTEmeasurementsGrating + this.numberOfTMmeasurementsGrating);
/* 424 */     this.setMeasurementsTMgrating = true;
/* 425 */     this.setTMerrors = true;
/* 426 */     this.setMeasurementsGrating = true;
/* 427 */     if ((this.setGratingPitch) && (this.setWavelength)) calcTMmodeEffectiveRefractiveIndices();
/*     */   }
/*     */   
/*     */   public void enterTEmodeData(double[] thicknesses, double[] angles, double[] errors, double[] modeNumbers)
/*     */   {
/* 432 */     int o = thicknesses.length;
/* 433 */     int n = angles.length;
/* 434 */     if (n != o) throw new IllegalArgumentException("number of thicknesses, " + o + ", does not equal the number of coupling angles, " + n);
/* 435 */     int m = modeNumbers.length;
/* 436 */     if (m != o) { throw new IllegalArgumentException("number of thicknesses, " + o + ", does not equal the number of mode numbers, " + m);
/*     */     }
/* 438 */     if (this.setMeasurementsTEgrating) {
/* 439 */       if (!this.setTEerrors) throw new IllegalArgumentException("All Entered data must either all have associated errors entered or all have no associated errors entered");
/* 440 */       int nNew = this.numberOfTEmeasurementsGrating + o;
/* 441 */       double[] hold = new double[nNew];
/* 442 */       for (int i = 0; i < this.numberOfTEmeasurementsGrating; i++) hold[i] = this.thicknessesTE[i];
/* 443 */       for (int i = 0; i < o; i++) hold[(this.numberOfTEmeasurementsGrating + i)] = thicknesses[i];
/* 444 */       this.thicknessesTE = hold;
/* 445 */       for (int i = 0; i < this.numberOfTEmeasurementsGrating; i++) hold[i] = this.anglesDegTE[i];
/* 446 */       for (int i = 0; i < o; i++) hold[(this.numberOfTEmeasurementsGrating + i)] = angles[i];
/* 447 */       this.anglesDegTE = hold;
/* 448 */       for (int i = 0; i < this.numberOfTEmeasurementsGrating; i++) hold[i] = this.errorsDegTE[i];
/* 449 */       for (int i = 0; i < o; i++) hold[(this.numberOfTEmeasurementsGrating + i)] = errors[i];
/* 450 */       this.errorsDegTE = hold;
/* 451 */       this.anglesRadTE = hold;
/* 452 */       this.errorsRadTE = hold;
/* 453 */       for (int i = 0; i < nNew; i++) {
/* 454 */         this.anglesRadTE[i] = Math.toRadians(this.anglesDegTE[i]);
/* 455 */         this.errorsRadTE[i] = Math.toRadians(this.errorsDegTE[i]);
/*     */       }
/* 457 */       for (int i = 0; i < this.numberOfTEmeasurementsGrating; i++) hold[i] = this.modeNumbersTE[i];
/* 458 */       for (int i = 0; i < o; i++) hold[(this.numberOfTEmeasurementsGrating + i)] = modeNumbers[i];
/* 459 */       this.numberOfTEmeasurementsGrating = nNew;
/*     */     }
/*     */     else {
/* 462 */       this.numberOfTEmeasurementsGrating = o;
/* 463 */       this.thicknessesTE = thicknesses;
/* 464 */       this.anglesDegTE = angles;
/* 465 */       this.anglesRadTE = new double[o];
/* 466 */       this.errorsDegTE = errors;
/* 467 */       this.errorsRadTE = new double[o];
/* 468 */       for (int i = 0; i < o; i++) {
/* 469 */         this.anglesRadTE[i] = Math.toRadians(angles[i]);
/* 470 */         this.errorsRadTE[i] = Math.toRadians(errors[i]);
/*     */       }
/* 472 */       this.modeNumbersTE = modeNumbers;
/*     */     }
/* 474 */     this.numberOfMeasurementsGrating = (this.numberOfTEmeasurementsGrating + this.numberOfTMmeasurementsGrating);
/* 475 */     this.setMeasurementsTEgrating = true;
/* 476 */     this.setTEerrors = true;
/* 477 */     this.setMeasurementsGrating = true;
/* 478 */     if ((this.setGratingPitch) && (this.setWavelength)) calcTEmodeEffectiveRefractiveIndices();
/*     */   }
/*     */   
/*     */   public void enterTMmodeData(double[] thicknesses, double[] angles, double[] errors, double[] modeNumbers)
/*     */   {
/* 483 */     int o = thicknesses.length;
/* 484 */     int n = angles.length;
/* 485 */     if (n != o) throw new IllegalArgumentException("number of thicknesses, " + o + ", does not equal the number of coupling angles, " + n);
/* 486 */     int m = modeNumbers.length;
/* 487 */     if (m != o) { throw new IllegalArgumentException("number of thicknesses, " + o + ", does not equal the number of mode numbers, " + m);
/*     */     }
/* 489 */     if (this.setMeasurementsTMgrating) {
/* 490 */       if (!this.setTMerrors) throw new IllegalArgumentException("All Entered data must either all have associated errors entered or all have no associated errors entered");
/* 491 */       int nNew = this.numberOfTMmeasurementsGrating + o;
/* 492 */       double[] hold = new double[nNew];
/* 493 */       for (int i = 0; i < this.numberOfTMmeasurementsGrating; i++) hold[i] = this.thicknessesTM[i];
/* 494 */       for (int i = 0; i < o; i++) hold[(this.numberOfTMmeasurementsGrating + i)] = thicknesses[i];
/* 495 */       this.thicknessesTM = hold;
/* 496 */       for (int i = 0; i < this.numberOfTMmeasurementsGrating; i++) hold[i] = this.anglesDegTM[i];
/* 497 */       for (int i = 0; i < o; i++) hold[(this.numberOfTMmeasurementsGrating + i)] = angles[i];
/* 498 */       this.anglesDegTM = hold;
/* 499 */       for (int i = 0; i < this.numberOfTMmeasurementsGrating; i++) hold[i] = this.errorsDegTM[i];
/* 500 */       for (int i = 0; i < o; i++) hold[(this.numberOfTMmeasurementsGrating + i)] = errors[i];
/* 501 */       this.errorsDegTM = hold;
/* 502 */       this.anglesRadTM = hold;
/* 503 */       this.errorsRadTM = hold;
/* 504 */       for (int i = 0; i < nNew; i++) {
/* 505 */         this.anglesRadTM[i] = Math.toRadians(this.anglesDegTM[i]);
/* 506 */         this.errorsRadTM[i] = Math.toRadians(this.errorsDegTM[i]);
/*     */       }
/* 508 */       for (int i = 0; i < this.numberOfTMmeasurementsGrating; i++) hold[i] = this.modeNumbersTM[i];
/* 509 */       for (int i = 0; i < o; i++) hold[(this.numberOfTMmeasurementsGrating + i)] = modeNumbers[i];
/* 510 */       this.numberOfTMmeasurementsGrating = nNew;
/*     */     }
/*     */     else {
/* 513 */       this.numberOfTMmeasurementsGrating = o;
/* 514 */       this.thicknessesTM = thicknesses;
/* 515 */       this.anglesDegTM = angles;
/* 516 */       this.errorsDegTM = errors;
/* 517 */       this.anglesRadTM = new double[o];
/* 518 */       this.errorsRadTM = new double[o];
/* 519 */       for (int i = 0; i < o; i++) {
/* 520 */         this.anglesRadTM[i] = Math.toRadians(angles[i]);
/* 521 */         this.errorsRadTM[i] = Math.toRadians(errors[i]);
/*     */       }
/* 523 */       this.modeNumbersTM = modeNumbers;
/*     */     }
/* 525 */     this.numberOfMeasurementsGrating = (this.numberOfTEmeasurementsGrating + this.numberOfTMmeasurementsGrating);
/* 526 */     this.setMeasurementsTMgrating = true;
/* 527 */     this.setTMerrors = true;
/* 528 */     this.setMeasurementsGrating = true;
/* 529 */     if ((this.setGratingPitch) && (this.setWavelength)) { calcTMmodeEffectiveRefractiveIndices();
/*     */     }
/*     */   }
/*     */   
/*     */ 
/*     */   public void clearData()
/*     */   {
/* 536 */     this.numberOfTEmeasurementsGrating = 0;
/* 537 */     this.setMeasurementsTEgrating = false;
/*     */     
/* 539 */     this.numberOfTMmeasurementsGrating = 0;
/* 540 */     this.setMeasurementsTMgrating = false;
/*     */     
/* 542 */     this.numberOfMeasurements = 0;
/* 543 */     this.setMeasurements = false;
/* 544 */     this.setWeights = false;
/*     */     
/* 546 */     this.numberOfTEmeasurements = 0;
/* 547 */     this.setMeasurementsTE = false;
/*     */     
/* 549 */     this.numberOfTMmeasurements = 0;
/* 550 */     this.setMeasurementsTM = false;
/*     */   }
/*     */   
/*     */ 
/*     */   public void calcEffectiveRefractiveIndices()
/*     */   {
/* 556 */     if (this.setMeasurementsTEgrating) calcTEmodeEffectiveRefractiveIndices();
/* 557 */     if (this.setMeasurementsTMgrating) calcTMmodeEffectiveRefractiveIndices();
/*     */   }
/*     */   
/*     */   public void calcTEmodeEffectiveRefractiveIndices()
/*     */   {
/* 562 */     this.effectiveRefractiveIndicesTE = new double[this.numberOfTEmeasurementsGrating];
/* 563 */     this.effectiveErrorsTE = new double[this.numberOfTEmeasurementsGrating];
/*     */     
/* 565 */     if (!this.setSuperstrateRI) {
/* 566 */       this.superstrateRI = RefractiveIndex.air(this.wavelength);
/* 567 */       this.superstrateRefractiveIndex = RefractiveIndex.air(this.wavelength);
/*     */     }
/*     */     
/* 570 */     if (this.setTEerrors) {
/* 571 */       ErrorProp superRI = new ErrorProp(this.superstrateRefractiveIndex, 0.0D);
/* 572 */       ErrorProp pitch = new ErrorProp(this.gratingPitch, 0.0D);
/* 573 */       ErrorProp lambda = new ErrorProp(this.wavelength, 0.0D);
/*     */       
/* 575 */       for (int i = 0; i < this.numberOfTEmeasurementsGrating; i++) {
/* 576 */         ErrorProp theta = new ErrorProp(this.anglesRadTM[i], this.errorsRadTM[i]);
/* 577 */         ErrorProp order = new ErrorProp(this.gratingOrderTE[i], 0.0D);
/* 578 */         ErrorProp calc = ErrorProp.sin(theta);
/* 579 */         calc = calc.times(superRI);
/* 580 */         calc = calc.plus(lambda.times(order).over(pitch));
/* 581 */         this.effectiveRefractiveIndicesTE[i] = calc.getValue();
/* 582 */         this.effectiveErrorsTE[i] = calc.getError();
/*     */       }
/* 584 */       super.enterTEmodeData(this.thicknessesTE, this.effectiveRefractiveIndicesTE, this.effectiveErrorsTE, this.modeNumbersTE);
/*     */     }
/*     */     else {
/* 587 */       for (int i = 0; i < this.numberOfTEmeasurementsGrating; i++) {
/* 588 */         this.effectiveRefractiveIndicesTE[i] = (this.superstrateRI * Math.sin(this.anglesRadTE[i]) + this.wavelength * this.gratingOrderTE[i] / this.gratingPitch);
/*     */       }
/* 590 */       super.enterTEmodeData(this.thicknessesTE, this.effectiveRefractiveIndicesTE, this.modeNumbersTE);
/*     */     }
/* 592 */     this.calcEffectiveDone = true;
/*     */   }
/*     */   
/*     */   public void calcTMmodeEffectiveRefractiveIndices()
/*     */   {
/* 597 */     this.effectiveRefractiveIndicesTM = new double[this.numberOfTMmeasurementsGrating];
/* 598 */     this.effectiveErrorsTM = new double[this.numberOfTMmeasurementsGrating];
/*     */     
/* 600 */     if (!this.setSuperstrateRI) {
/* 601 */       this.superstrateRI = RefractiveIndex.air(this.wavelength);
/* 602 */       this.superstrateRefractiveIndex = RefractiveIndex.air(this.wavelength);
/*     */     }
/*     */     
/* 605 */     if (this.setTMerrors) {
/* 606 */       ErrorProp superRI = new ErrorProp(this.superstrateRefractiveIndex, 0.0D);
/* 607 */       ErrorProp pitch = new ErrorProp(this.gratingPitch, 0.0D);
/* 608 */       ErrorProp lambda = new ErrorProp(this.wavelength, 0.0D);
/*     */       
/* 610 */       for (int i = 0; i < this.numberOfTMmeasurementsGrating; i++) {
/* 611 */         ErrorProp theta = new ErrorProp(this.anglesRadTM[i], this.errorsRadTM[i]);
/* 612 */         ErrorProp order = new ErrorProp(this.gratingOrderTM[i], 0.0D);
/* 613 */         ErrorProp calc = ErrorProp.sin(theta);
/* 614 */         calc = calc.times(superRI);
/* 615 */         calc = calc.plus(lambda.times(order).over(pitch));
/* 616 */         this.effectiveRefractiveIndicesTM[i] = calc.getValue();
/* 617 */         this.effectiveErrorsTM[i] = calc.getError();
/*     */       }
/* 619 */       super.enterTMmodeData(this.thicknessesTM, this.effectiveRefractiveIndicesTM, this.effectiveErrorsTM, this.modeNumbersTM);
/*     */     }
/*     */     else {
/* 622 */       for (int i = 0; i < this.numberOfTMmeasurementsGrating; i++) {
/* 623 */         this.effectiveRefractiveIndicesTM[i] = (this.superstrateRI * Math.sin(this.anglesRadTM[i]) + this.wavelength * this.gratingOrderTM[i] / this.gratingPitch);
/*     */       }
/* 625 */       super.enterTMmodeData(this.thicknessesTM, this.effectiveRefractiveIndicesTM, this.modeNumbersTM);
/*     */     }
/* 627 */     this.calcEffectiveDone = true;
/*     */   }
/*     */ }


/* Location:              /home/menick/Scrivania/ProcessMining/CNMining/CNMining.jar!/flanagan/optics/GratingCoupler.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       0.7.1
 */