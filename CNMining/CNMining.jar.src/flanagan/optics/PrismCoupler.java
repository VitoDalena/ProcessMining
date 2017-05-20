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
/*     */ public class PrismCoupler
/*     */   extends PlanarWaveguide
/*     */ {
/*  47 */   private double[] thicknessesTE = null;
/*  48 */   private double[] anglesDegTE = null;
/*  49 */   private double[] anglesRadTE = null;
/*  50 */   private double[] errorsDegTE = null;
/*  51 */   private double[] errorsRadTE = null;
/*  52 */   private double[] modeNumbersTE = null;
/*  53 */   private double[] effectiveRefractiveIndicesTE = null;
/*  54 */   private double[] effectiveErrorsTE = null;
/*  55 */   private int numberOfTEmeasurementsPrism = 0;
/*  56 */   private boolean setMeasurementsTEprism = false;
/*  57 */   private boolean setTEerrors = false;
/*     */   
/*  59 */   private double[] thicknessesTM = null;
/*  60 */   private double[] anglesDegTM = null;
/*  61 */   private double[] anglesRadTM = null;
/*  62 */   private double[] errorsDegTM = null;
/*  63 */   private double[] errorsRadTM = null;
/*  64 */   private double[] modeNumbersTM = null;
/*  65 */   private double[] effectiveRefractiveIndicesTM = null;
/*  66 */   private double[] effectiveErrorsTM = null;
/*  67 */   private int numberOfTMmeasurementsPrism = 0;
/*  68 */   private boolean setMeasurementsTMprism = false;
/*  69 */   private boolean setTMerrors = false;
/*     */   
/*     */ 
/*  72 */   private int numberOfMeasurementsPrism = 0;
/*  73 */   private boolean setMeasurementsPrism = false;
/*     */   
/*  75 */   private boolean setPrismRI = false;
/*  76 */   private double prismAngleAlphaDeg = 0.0D;
/*  77 */   private double prismAngleAlphaRad = 0.0D;
/*  78 */   private boolean setPrismAlpha = false;
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public void setPrismRefractiveIndex(double refInd)
/*     */   {
/*  88 */     this.prismRefractiveIndex = refInd;
/*  89 */     this.prismRefractiveIndex2 = (refInd * refInd);
/*  90 */     this.setPrismRI = true;
/*  91 */     if ((this.setMeasurementsPrism) && (this.setPrismAlpha)) calcEffectiveRefractiveIndices();
/*     */   }
/*     */   
/*     */   public void setPrismAngleAlpha(double angle)
/*     */   {
/*  96 */     this.prismAngleAlphaDeg = angle;
/*  97 */     this.prismAngleAlphaRad = Math.toRadians(angle);
/*  98 */     this.setPrismAlpha = true;
/*  99 */     if ((this.setMeasurementsPrism) && (this.setPrismRI)) calcEffectiveRefractiveIndices();
/*     */   }
/*     */   
/*     */   public void setPrismToWaveguideGap(double gap)
/*     */   {
/* 104 */     this.prismToWaveguideGap = gap;
/* 105 */     this.setPrismToWaveguideGap = true;
/*     */   }
/*     */   
/*     */ 
/*     */   public void enterTEmodeData(double thickness, double angle, double modeNumber)
/*     */   {
/* 111 */     if (this.setMeasurementsTEprism) {
/* 112 */       if (this.setErrorsTE) throw new IllegalArgumentException("All Entered data must either all have associated errors entered or all have no associated errors entered");
/* 113 */       int nNew = this.numberOfTEmeasurementsPrism + 1;
/* 114 */       double[] hold = new double[nNew];
/* 115 */       for (int i = 0; i < this.numberOfTEmeasurementsPrism; i++) hold[i] = this.thicknessesTE[i];
/* 116 */       hold[this.numberOfTEmeasurementsPrism] = thickness;
/* 117 */       this.thicknessesTE = hold;
/* 118 */       for (int i = 0; i < this.numberOfTEmeasurementsPrism; i++) hold[i] = this.anglesDegTE[i];
/* 119 */       hold[this.numberOfTEmeasurementsPrism] = angle;
/* 120 */       this.anglesDegTE = hold;
/* 121 */       this.anglesRadTE = hold;
/* 122 */       this.errorsDegTE = hold;
/* 123 */       this.errorsRadTE = hold;
/* 124 */       for (int i = 0; i < nNew; i++) {
/* 125 */         this.anglesRadTE[i] = Math.toRadians(this.anglesDegTE[i]);
/* 126 */         this.errorsDegTE[i] = 0.0D;
/* 127 */         this.errorsRadTE[i] = 0.0D;
/*     */       }
/* 129 */       for (int i = 0; i < this.numberOfTEmeasurementsPrism; i++) hold[i] = this.modeNumbersTE[i];
/* 130 */       hold[this.numberOfTEmeasurementsPrism] = modeNumber;
/* 131 */       this.numberOfTEmeasurementsPrism = nNew;
/*     */     }
/*     */     else {
/* 134 */       this.thicknessesTE = new double[1];
/* 135 */       this.thicknessesTE[0] = thickness;
/* 136 */       this.anglesDegTE = new double[1];
/* 137 */       this.anglesDegTE[0] = angle;
/* 138 */       this.anglesRadTE = new double[1];
/* 139 */       this.anglesRadTE[0] = Math.toRadians(angle);
/* 140 */       this.errorsDegTE = new double[1];
/* 141 */       this.errorsDegTE[0] = 0.0D;
/* 142 */       this.errorsRadTE = new double[1];
/* 143 */       this.errorsRadTE[0] = 0.0D;
/* 144 */       this.modeNumbersTE = new double[1];
/* 145 */       this.modeNumbersTE[0] = modeNumber;
/* 146 */       this.numberOfTEmeasurementsPrism = 1;
/*     */     }
/* 148 */     this.numberOfMeasurementsPrism = (this.numberOfTEmeasurementsPrism + this.numberOfTMmeasurementsPrism);
/* 149 */     this.setMeasurementsTEprism = true;
/* 150 */     this.setMeasurementsPrism = true;
/* 151 */     if ((this.setPrismAlpha) && (this.setPrismAlpha)) { calcTEmodeEffectiveRefractiveIndices();
/*     */     }
/*     */   }
/*     */   
/*     */ 
/*     */   public void enterTMmodeData(double thickness, double angle, double modeNumber)
/*     */   {
/* 158 */     if (this.setMeasurementsTMprism) {
/* 159 */       if (this.setTMerrors) throw new IllegalArgumentException("All Entered data must either all have associated errors entered or all have no associated errors entered");
/* 160 */       int nNew = this.numberOfTMmeasurementsPrism + 1;
/* 161 */       double[] hold = new double[nNew];
/* 162 */       for (int i = 0; i < this.numberOfTMmeasurementsPrism; i++) hold[i] = this.thicknessesTM[i];
/* 163 */       hold[this.numberOfTMmeasurementsPrism] = thickness;
/* 164 */       this.thicknessesTM = hold;
/* 165 */       for (int i = 0; i < this.numberOfTMmeasurementsPrism; i++) hold[i] = this.anglesDegTM[i];
/* 166 */       hold[this.numberOfTMmeasurementsPrism] = angle;
/* 167 */       this.anglesDegTM = hold;
/* 168 */       this.anglesRadTM = hold;
/* 169 */       this.errorsDegTM = hold;
/* 170 */       this.errorsRadTM = hold;
/* 171 */       for (int i = 0; i < nNew; i++) {
/* 172 */         this.anglesRadTM[i] = Math.toRadians(this.anglesDegTM[i]);
/* 173 */         this.errorsDegTM[i] = 0.0D;
/* 174 */         this.errorsRadTM[i] = 0.0D;
/*     */       }
/* 176 */       for (int i = 0; i < this.numberOfTMmeasurementsPrism; i++) hold[i] = this.modeNumbersTM[i];
/* 177 */       hold[this.numberOfTMmeasurementsPrism] = modeNumber;
/* 178 */       this.numberOfTMmeasurementsPrism = nNew;
/*     */     }
/*     */     else {
/* 181 */       this.thicknessesTM = new double[1];
/* 182 */       this.thicknessesTM[0] = thickness;
/* 183 */       this.anglesDegTM = new double[1];
/* 184 */       this.anglesDegTM[0] = angle;
/* 185 */       this.anglesRadTM = new double[1];
/* 186 */       this.anglesRadTM[0] = Math.toRadians(angle);
/* 187 */       this.errorsDegTM = new double[1];
/* 188 */       this.errorsDegTM[0] = 0.0D;
/* 189 */       this.errorsRadTM = new double[1];
/* 190 */       this.errorsRadTM[0] = 0.0D;
/* 191 */       this.modeNumbersTM = new double[1];
/* 192 */       this.modeNumbersTM[0] = modeNumber;
/* 193 */       this.numberOfTMmeasurementsPrism = 1;
/*     */     }
/* 195 */     this.numberOfMeasurementsPrism = (this.numberOfTEmeasurementsPrism + this.numberOfTMmeasurementsPrism);
/* 196 */     this.setMeasurementsTMprism = true;
/* 197 */     this.setMeasurementsPrism = true;
/* 198 */     if ((this.setPrismAlpha) && (this.setPrismRI)) calcTMmodeEffectiveRefractiveIndices();
/*     */   }
/*     */   
/*     */   public void enterTEmodeData(double[] thicknesses, double[] angles, double[] modeNumbers)
/*     */   {
/* 203 */     int o = thicknesses.length;
/* 204 */     int n = angles.length;
/* 205 */     if (n != o) throw new IllegalArgumentException("number of thicknesses, " + o + ", does not equal the number of coupling angles, " + n);
/* 206 */     int m = modeNumbers.length;
/* 207 */     if (m != o) { throw new IllegalArgumentException("number of thicknesses, " + o + ", does not equal the number of mode numbers, " + m);
/*     */     }
/* 209 */     if (this.setMeasurementsTEprism) {
/* 210 */       if (this.setTEerrors) throw new IllegalArgumentException("All Entered data must either all have associated errors entered or all have no associated errors entered");
/* 211 */       int nNew = this.numberOfTEmeasurementsPrism + o;
/* 212 */       double[] hold = new double[nNew];
/* 213 */       for (int i = 0; i < this.numberOfTEmeasurementsPrism; i++) hold[i] = this.thicknessesTE[i];
/* 214 */       for (int i = 0; i < o; i++) hold[(this.numberOfTEmeasurementsPrism + i)] = thicknesses[i];
/* 215 */       this.thicknessesTE = hold;
/* 216 */       for (int i = 0; i < this.numberOfTEmeasurementsPrism; i++) hold[i] = this.anglesDegTE[i];
/* 217 */       for (int i = 0; i < o; i++) hold[(this.numberOfTEmeasurementsPrism + i)] = angles[i];
/* 218 */       this.anglesDegTE = hold;
/* 219 */       this.anglesRadTE = hold;
/* 220 */       this.errorsDegTE = hold;
/* 221 */       this.errorsRadTE = hold;
/* 222 */       for (int i = 0; i < nNew; i++) {
/* 223 */         this.anglesRadTE[i] = Math.toRadians(this.anglesDegTE[i]);
/* 224 */         this.errorsDegTE[i] = 0.0D;
/* 225 */         this.errorsRadTE[i] = 0.0D;
/*     */       }
/* 227 */       for (int i = 0; i < this.numberOfTEmeasurementsPrism; i++) hold[i] = this.modeNumbersTE[i];
/* 228 */       for (int i = 0; i < o; i++) hold[(this.numberOfTEmeasurementsPrism + i)] = modeNumbers[i];
/* 229 */       this.numberOfTEmeasurementsPrism = nNew;
/*     */     }
/*     */     else {
/* 232 */       this.numberOfTEmeasurementsPrism = o;
/* 233 */       this.thicknessesTE = thicknesses;
/* 234 */       this.anglesDegTE = angles;
/* 235 */       this.anglesRadTE = new double[o];
/* 236 */       this.errorsDegTE = new double[o];
/* 237 */       this.errorsRadTE = new double[o];
/* 238 */       for (int i = 0; i < o; i++) {
/* 239 */         this.anglesRadTE[i] = Math.toRadians(angles[i]);
/* 240 */         this.errorsDegTE[i] = 0.0D;
/* 241 */         this.errorsRadTE[i] = 0.0D;
/*     */       }
/* 243 */       this.modeNumbersTE = modeNumbers;
/*     */     }
/* 245 */     this.numberOfMeasurementsPrism = (this.numberOfTEmeasurementsPrism + this.numberOfTMmeasurementsPrism);
/* 246 */     this.setMeasurementsTEprism = true;
/* 247 */     this.setMeasurementsPrism = true;
/* 248 */     if ((this.setPrismAlpha) && (this.setPrismRI)) calcTEmodeEffectiveRefractiveIndices();
/*     */   }
/*     */   
/*     */   public void enterTMmodeData(double[] thicknesses, double[] angles, double[] modeNumbers)
/*     */   {
/* 253 */     int o = thicknesses.length;
/* 254 */     int n = angles.length;
/* 255 */     if (n != o) throw new IllegalArgumentException("number of thicknesses, " + o + ", does not equal the number of coupling angles, " + n);
/* 256 */     int m = modeNumbers.length;
/* 257 */     if (m != o) { throw new IllegalArgumentException("number of thicknesses, " + o + ", does not equal the number of mode numbers, " + m);
/*     */     }
/* 259 */     if (this.setMeasurementsTMprism) {
/* 260 */       if (this.setTMerrors) throw new IllegalArgumentException("All Entered data must either all have associated errors entered or all have no associated errors entered");
/* 261 */       int nNew = this.numberOfTMmeasurementsPrism + o;
/* 262 */       double[] hold = new double[nNew];
/* 263 */       for (int i = 0; i < this.numberOfTMmeasurementsPrism; i++) hold[i] = this.thicknessesTM[i];
/* 264 */       for (int i = 0; i < o; i++) hold[(this.numberOfTMmeasurementsPrism + i)] = thicknesses[i];
/* 265 */       this.thicknessesTM = hold;
/* 266 */       for (int i = 0; i < this.numberOfTMmeasurementsPrism; i++) hold[i] = this.anglesDegTM[i];
/* 267 */       for (int i = 0; i < o; i++) hold[(this.numberOfTMmeasurementsPrism + i)] = angles[i];
/* 268 */       this.anglesDegTM = hold;
/* 269 */       this.anglesRadTM = hold;
/* 270 */       this.errorsDegTM = hold;
/* 271 */       this.errorsRadTM = hold;
/* 272 */       for (int i = 0; i < nNew; i++) {
/* 273 */         this.anglesRadTM[i] = Math.toRadians(this.anglesDegTM[i]);
/* 274 */         this.errorsDegTM[i] = 0.0D;
/* 275 */         this.errorsRadTM[i] = 0.0D;
/*     */       }
/* 277 */       for (int i = 0; i < this.numberOfTMmeasurementsPrism; i++) hold[i] = this.modeNumbersTM[i];
/* 278 */       for (int i = 0; i < o; i++) hold[(this.numberOfTMmeasurementsPrism + i)] = modeNumbers[i];
/* 279 */       this.numberOfTMmeasurementsPrism = nNew;
/*     */     }
/*     */     else {
/* 282 */       this.numberOfTMmeasurementsPrism = o;
/* 283 */       this.thicknessesTM = thicknesses;
/* 284 */       this.anglesDegTM = angles;
/* 285 */       this.anglesRadTM = new double[o];
/* 286 */       this.errorsDegTM = new double[o];
/* 287 */       this.errorsRadTM = new double[o];
/* 288 */       for (int i = 0; i < o; i++) {
/* 289 */         this.anglesRadTM[i] = Math.toRadians(angles[i]);
/* 290 */         this.errorsDegTM[i] = 0.0D;
/* 291 */         this.errorsRadTM[i] = 0.0D;
/*     */       }
/* 293 */       this.modeNumbersTM = modeNumbers;
/*     */     }
/* 295 */     this.numberOfMeasurementsPrism = (this.numberOfTEmeasurementsPrism + this.numberOfTMmeasurementsPrism);
/* 296 */     this.setMeasurementsTMprism = true;
/* 297 */     this.setMeasurementsPrism = true;
/* 298 */     if ((this.setPrismAlpha) && (this.setPrismRI)) calcTMmodeEffectiveRefractiveIndices();
/*     */   }
/*     */   
/*     */   public void enterTEmodeData(double thickness, double angle, double error, double modeNumber)
/*     */   {
/* 303 */     if (this.setMeasurementsTEprism) {
/* 304 */       if (!this.setTEerrors) throw new IllegalArgumentException("All Entered data must either all have associated errors entered or all have no associated errors entered");
/* 305 */       int nNew = this.numberOfTEmeasurementsPrism + 1;
/* 306 */       double[] hold = new double[nNew];
/* 307 */       for (int i = 0; i < this.numberOfTEmeasurementsPrism; i++) hold[i] = this.thicknessesTE[i];
/* 308 */       hold[this.numberOfTEmeasurementsPrism] = thickness;
/* 309 */       this.thicknessesTE = hold;
/* 310 */       for (int i = 0; i < this.numberOfTEmeasurementsPrism; i++) hold[i] = this.anglesDegTE[i];
/* 311 */       hold[this.numberOfTEmeasurementsPrism] = angle;
/* 312 */       this.anglesDegTE = hold;
/* 313 */       for (int i = 0; i < this.numberOfTEmeasurementsPrism; i++) hold[i] = this.errorsDegTE[i];
/* 314 */       hold[this.numberOfTEmeasurementsPrism] = error;
/* 315 */       this.errorsDegTE = hold;
/* 316 */       this.anglesRadTE = hold;
/* 317 */       this.errorsRadTE = hold;
/* 318 */       for (int i = 0; i < nNew; i++) {
/* 319 */         this.anglesRadTE[i] = Math.toRadians(this.anglesDegTE[i]);
/* 320 */         this.errorsRadTE[i] = Math.toRadians(this.errorsDegTE[i]);
/*     */       }
/* 322 */       for (int i = 0; i < this.numberOfTEmeasurementsPrism; i++) hold[i] = this.modeNumbersTE[i];
/* 323 */       hold[this.numberOfTEmeasurementsPrism] = modeNumber;
/* 324 */       this.numberOfTEmeasurementsPrism = nNew;
/*     */     }
/*     */     else {
/* 327 */       this.thicknessesTE = new double[1];
/* 328 */       this.thicknessesTE[0] = thickness;
/* 329 */       this.anglesDegTE = new double[1];
/* 330 */       this.anglesDegTE[0] = angle;
/* 331 */       this.anglesRadTE = new double[1];
/* 332 */       this.anglesRadTE[0] = Math.toRadians(angle);
/* 333 */       this.errorsDegTE = new double[1];
/* 334 */       this.errorsDegTE[0] = error;
/* 335 */       this.errorsRadTE = new double[1];
/* 336 */       this.errorsRadTE[0] = Math.toRadians(error);
/* 337 */       this.modeNumbersTE = new double[1];
/* 338 */       this.modeNumbersTE[0] = modeNumber;
/* 339 */       this.numberOfTEmeasurementsPrism = 1;
/*     */     }
/* 341 */     this.numberOfMeasurementsPrism = (this.numberOfTEmeasurementsPrism + this.numberOfTMmeasurementsPrism);
/* 342 */     this.setMeasurementsTEprism = true;
/* 343 */     this.setTEerrors = true;
/* 344 */     this.setMeasurementsPrism = true;
/* 345 */     if ((this.setPrismAlpha) && (this.setPrismRI)) { calcTEmodeEffectiveRefractiveIndices();
/*     */     }
/*     */   }
/*     */   
/*     */ 
/*     */   public void enterTMmodeData(double thickness, double angle, double error, double modeNumber)
/*     */   {
/* 352 */     if (this.setMeasurementsTMprism) {
/* 353 */       if (!this.setTMerrors) throw new IllegalArgumentException("All Entered data must either all have associated errors entered or all have no associated errors entered");
/* 354 */       int nNew = this.numberOfTMmeasurementsPrism + 1;
/* 355 */       double[] hold = new double[nNew];
/* 356 */       for (int i = 0; i < this.numberOfTMmeasurementsPrism; i++) hold[i] = this.thicknessesTM[i];
/* 357 */       hold[this.numberOfTMmeasurementsPrism] = thickness;
/* 358 */       this.thicknessesTM = hold;
/* 359 */       for (int i = 0; i < this.numberOfTMmeasurementsPrism; i++) hold[i] = this.anglesDegTM[i];
/* 360 */       hold[this.numberOfTMmeasurementsPrism] = angle;
/* 361 */       this.anglesDegTM = hold;
/* 362 */       for (int i = 0; i < this.numberOfTMmeasurementsPrism; i++) hold[i] = this.errorsDegTM[i];
/* 363 */       hold[this.numberOfTMmeasurementsPrism] = error;
/* 364 */       this.errorsDegTM = hold;
/* 365 */       this.anglesRadTM = hold;
/* 366 */       this.errorsRadTM = hold;
/* 367 */       for (int i = 0; i < nNew; i++) {
/* 368 */         this.anglesRadTM[i] = Math.toRadians(this.anglesDegTM[i]);
/* 369 */         this.errorsRadTM[i] = Math.toRadians(this.errorsDegTM[i]);
/*     */       }
/* 371 */       for (int i = 0; i < this.numberOfTMmeasurementsPrism; i++) hold[i] = this.modeNumbersTM[i];
/* 372 */       hold[this.numberOfTMmeasurementsPrism] = modeNumber;
/* 373 */       this.numberOfTMmeasurementsPrism = nNew;
/*     */     }
/*     */     else {
/* 376 */       this.thicknessesTM = new double[1];
/* 377 */       this.thicknessesTM[0] = thickness;
/* 378 */       this.anglesDegTM = new double[1];
/* 379 */       this.anglesDegTM[0] = angle;
/* 380 */       this.anglesRadTM = new double[1];
/* 381 */       this.anglesDegTM[0] = Math.toRadians(angle);
/* 382 */       this.errorsDegTM = new double[1];
/* 383 */       this.errorsDegTM[0] = error;
/* 384 */       this.errorsRadTM = new double[1];
/* 385 */       this.errorsDegTM[0] = Math.toRadians(error);
/* 386 */       this.modeNumbersTM = new double[1];
/* 387 */       this.modeNumbersTM[0] = modeNumber;
/* 388 */       this.numberOfTMmeasurementsPrism = 1;
/*     */     }
/* 390 */     this.numberOfMeasurementsPrism = (this.numberOfTEmeasurementsPrism + this.numberOfTMmeasurementsPrism);
/* 391 */     this.setMeasurementsTMprism = true;
/* 392 */     this.setTMerrors = true;
/* 393 */     this.setMeasurementsPrism = true;
/* 394 */     if ((this.setPrismAlpha) && (this.setPrismRI)) calcTMmodeEffectiveRefractiveIndices();
/*     */   }
/*     */   
/*     */   public void enterTEmodeData(double[] thicknesses, double[] angles, double[] errors, double[] modeNumbers)
/*     */   {
/* 399 */     int o = thicknesses.length;
/* 400 */     int n = angles.length;
/* 401 */     if (n != o) throw new IllegalArgumentException("number of thicknesses, " + o + ", does not equal the number of coupling angles, " + n);
/* 402 */     int m = modeNumbers.length;
/* 403 */     if (m != o) { throw new IllegalArgumentException("number of thicknesses, " + o + ", does not equal the number of mode numbers, " + m);
/*     */     }
/* 405 */     if (this.setMeasurementsTEprism) {
/* 406 */       if (!this.setTEerrors) throw new IllegalArgumentException("All Entered data must either all have associated errors entered or all have no associated errors entered");
/* 407 */       int nNew = this.numberOfTEmeasurementsPrism + o;
/* 408 */       double[] hold = new double[nNew];
/* 409 */       for (int i = 0; i < this.numberOfTEmeasurementsPrism; i++) hold[i] = this.thicknessesTE[i];
/* 410 */       for (int i = 0; i < o; i++) hold[(this.numberOfTEmeasurementsPrism + i)] = thicknesses[i];
/* 411 */       this.thicknessesTE = hold;
/* 412 */       for (int i = 0; i < this.numberOfTEmeasurementsPrism; i++) hold[i] = this.anglesDegTE[i];
/* 413 */       for (int i = 0; i < o; i++) hold[(this.numberOfTEmeasurementsPrism + i)] = angles[i];
/* 414 */       this.anglesDegTE = hold;
/* 415 */       for (int i = 0; i < this.numberOfTEmeasurementsPrism; i++) hold[i] = this.errorsDegTE[i];
/* 416 */       for (int i = 0; i < o; i++) hold[(this.numberOfTEmeasurementsPrism + i)] = errors[i];
/* 417 */       this.errorsDegTE = hold;
/* 418 */       this.anglesRadTE = hold;
/* 419 */       this.errorsRadTE = hold;
/* 420 */       for (int i = 0; i < nNew; i++) {
/* 421 */         this.anglesRadTE[i] = Math.toRadians(this.anglesDegTE[i]);
/* 422 */         this.errorsRadTE[i] = Math.toRadians(this.errorsDegTE[i]);
/*     */       }
/* 424 */       for (int i = 0; i < this.numberOfTEmeasurementsPrism; i++) hold[i] = this.modeNumbersTE[i];
/* 425 */       for (int i = 0; i < o; i++) hold[(this.numberOfTEmeasurementsPrism + i)] = modeNumbers[i];
/* 426 */       this.numberOfTEmeasurementsPrism = nNew;
/*     */     }
/*     */     else {
/* 429 */       this.numberOfTEmeasurementsPrism = o;
/* 430 */       this.thicknessesTE = thicknesses;
/* 431 */       this.anglesDegTE = angles;
/* 432 */       this.anglesRadTE = new double[o];
/* 433 */       this.errorsDegTE = errors;
/* 434 */       this.errorsRadTE = new double[o];
/* 435 */       for (int i = 0; i < o; i++) {
/* 436 */         this.anglesRadTE[i] = Math.toRadians(angles[i]);
/* 437 */         this.errorsRadTE[i] = Math.toRadians(errors[i]);
/*     */       }
/* 439 */       this.modeNumbersTE = modeNumbers;
/*     */     }
/* 441 */     this.numberOfMeasurementsPrism = (this.numberOfTEmeasurementsPrism + this.numberOfTMmeasurementsPrism);
/* 442 */     this.setMeasurementsTEprism = true;
/* 443 */     this.setTEerrors = true;
/* 444 */     this.setMeasurementsPrism = true;
/* 445 */     if ((this.setPrismAlpha) && (this.setPrismRI)) calcTEmodeEffectiveRefractiveIndices();
/*     */   }
/*     */   
/*     */   public void enterTMmodeData(double[] thicknesses, double[] angles, double[] errors, double[] modeNumbers)
/*     */   {
/* 450 */     int o = thicknesses.length;
/* 451 */     int n = angles.length;
/* 452 */     if (n != o) throw new IllegalArgumentException("number of thicknesses, " + o + ", does not equal the number of coupling angles, " + n);
/* 453 */     int m = modeNumbers.length;
/* 454 */     if (m != o) { throw new IllegalArgumentException("number of thicknesses, " + o + ", does not equal the number of mode numbers, " + m);
/*     */     }
/* 456 */     if (this.setMeasurementsTMprism) {
/* 457 */       if (!this.setTMerrors) throw new IllegalArgumentException("All Entered data must either all have associated errors entered or all have no associated errors entered");
/* 458 */       int nNew = this.numberOfTMmeasurementsPrism + o;
/* 459 */       double[] hold = new double[nNew];
/* 460 */       for (int i = 0; i < this.numberOfTMmeasurementsPrism; i++) hold[i] = this.thicknessesTM[i];
/* 461 */       for (int i = 0; i < o; i++) hold[(this.numberOfTMmeasurementsPrism + i)] = thicknesses[i];
/* 462 */       this.thicknessesTM = hold;
/* 463 */       for (int i = 0; i < this.numberOfTMmeasurementsPrism; i++) hold[i] = this.anglesDegTM[i];
/* 464 */       for (int i = 0; i < o; i++) hold[(this.numberOfTMmeasurementsPrism + i)] = angles[i];
/* 465 */       this.anglesDegTM = hold;
/* 466 */       for (int i = 0; i < this.numberOfTMmeasurementsPrism; i++) hold[i] = this.errorsDegTM[i];
/* 467 */       for (int i = 0; i < o; i++) hold[(this.numberOfTMmeasurementsPrism + i)] = errors[i];
/* 468 */       this.errorsDegTM = hold;
/* 469 */       this.anglesRadTM = hold;
/* 470 */       this.errorsRadTM = hold;
/* 471 */       for (int i = 0; i < nNew; i++) {
/* 472 */         this.anglesRadTM[i] = Math.toRadians(this.anglesDegTM[i]);
/* 473 */         this.errorsRadTM[i] = Math.toRadians(this.errorsDegTM[i]);
/*     */       }
/* 475 */       for (int i = 0; i < this.numberOfTMmeasurementsPrism; i++) hold[i] = this.modeNumbersTM[i];
/* 476 */       for (int i = 0; i < o; i++) hold[(this.numberOfTMmeasurementsPrism + i)] = modeNumbers[i];
/* 477 */       this.numberOfTMmeasurementsPrism = nNew;
/*     */     }
/*     */     else {
/* 480 */       this.numberOfTMmeasurementsPrism = o;
/* 481 */       this.thicknessesTM = thicknesses;
/* 482 */       this.anglesDegTM = angles;
/* 483 */       this.errorsDegTM = errors;
/* 484 */       this.anglesRadTM = new double[o];
/* 485 */       this.errorsRadTM = new double[o];
/* 486 */       for (int i = 0; i < o; i++) {
/* 487 */         this.anglesRadTM[i] = Math.toRadians(angles[i]);
/* 488 */         this.errorsRadTM[i] = Math.toRadians(errors[i]);
/*     */       }
/* 490 */       this.modeNumbersTM = modeNumbers;
/*     */     }
/* 492 */     this.numberOfMeasurementsPrism = (this.numberOfTEmeasurementsPrism + this.numberOfTMmeasurementsPrism);
/* 493 */     this.setMeasurementsTMprism = true;
/* 494 */     this.setTMerrors = true;
/* 495 */     this.setMeasurementsPrism = true;
/* 496 */     if ((this.setPrismAlpha) && (this.setPrismRI)) { calcTMmodeEffectiveRefractiveIndices();
/*     */     }
/*     */   }
/*     */   
/*     */ 
/*     */   public void clearData()
/*     */   {
/* 503 */     this.numberOfTEmeasurementsPrism = 0;
/* 504 */     this.setMeasurementsTEprism = false;
/*     */     
/* 506 */     this.numberOfTMmeasurementsPrism = 0;
/* 507 */     this.setMeasurementsTMprism = false;
/*     */     
/* 509 */     this.numberOfMeasurements = 0;
/* 510 */     this.setMeasurements = false;
/* 511 */     this.setWeights = false;
/*     */     
/* 513 */     this.numberOfTEmeasurements = 0;
/* 514 */     this.setMeasurementsTE = false;
/*     */     
/* 516 */     this.numberOfTMmeasurements = 0;
/* 517 */     this.setMeasurementsTM = false;
/*     */   }
/*     */   
/*     */ 
/*     */   public void calcEffectiveRefractiveIndices()
/*     */   {
/* 523 */     if (this.setMeasurementsTEprism) calcTEmodeEffectiveRefractiveIndices();
/* 524 */     if (this.setMeasurementsTMprism) calcTMmodeEffectiveRefractiveIndices();
/*     */   }
/*     */   
/*     */   public void calcTEmodeEffectiveRefractiveIndices()
/*     */   {
/* 529 */     this.effectiveRefractiveIndicesTE = new double[this.numberOfTEmeasurementsPrism];
/* 530 */     this.effectiveErrorsTE = new double[this.numberOfTEmeasurementsPrism];
/*     */     
/* 532 */     if (this.setTEerrors) {
/* 533 */       ErrorProp alpha = new ErrorProp(this.prismAngleAlphaRad, 0.0D);
/* 534 */       ErrorProp prismRI = new ErrorProp(this.prismRefractiveIndex, 0.0D);
/* 535 */       ErrorProp airRI = new ErrorProp(RefractiveIndex.air(this.wavelength), 0.0D);
/* 536 */       ErrorProp phi = new ErrorProp();
/* 537 */       ErrorProp angle = new ErrorProp();
/* 538 */       for (int i = 0; i < this.numberOfTEmeasurementsPrism; i++) {
/* 539 */         angle.reset(this.anglesRadTE[i], this.errorsRadTE[i]);
/* 540 */         phi = angle.over(prismRI).times(airRI);
/* 541 */         phi = ErrorProp.asin(phi);
/* 542 */         phi = alpha.plus(phi);
/* 543 */         phi = prismRI.times(ErrorProp.sin(phi));
/* 544 */         this.effectiveRefractiveIndicesTE[i] = phi.getValue();
/* 545 */         this.effectiveErrorsTE[i] = phi.getError();
/*     */       }
/* 547 */       super.enterTEmodeData(this.thicknessesTE, this.effectiveRefractiveIndicesTE, this.effectiveErrorsTE, this.modeNumbersTE);
/*     */     }
/*     */     else {
/* 550 */       for (int i = 0; i < this.numberOfTEmeasurementsPrism; i++) {
/* 551 */         double phi = this.prismAngleAlphaRad + Math.asin(RefractiveIndex.air(this.wavelength) * this.anglesRadTE[i] / this.prismRefractiveIndex);
/* 552 */         this.effectiveRefractiveIndicesTE[i] = (this.prismRefractiveIndex * Math.sin(phi));
/*     */       }
/* 554 */       super.enterTEmodeData(this.thicknessesTE, this.effectiveRefractiveIndicesTE, this.modeNumbersTE);
/*     */     }
/*     */   }
/*     */   
/*     */   public void calcTMmodeEffectiveRefractiveIndices()
/*     */   {
/* 560 */     this.effectiveRefractiveIndicesTM = new double[this.numberOfTMmeasurementsPrism];
/* 561 */     this.effectiveErrorsTM = new double[this.numberOfTMmeasurementsPrism];
/*     */     
/* 563 */     if (this.setTMerrors) {
/* 564 */       ErrorProp alpha = new ErrorProp(this.prismAngleAlphaRad, 0.0D);
/* 565 */       ErrorProp prismRI = new ErrorProp(this.prismRefractiveIndex, 0.0D);
/* 566 */       ErrorProp airRI = new ErrorProp(RefractiveIndex.air(this.wavelength), 0.0D);
/* 567 */       ErrorProp phi = new ErrorProp();
/* 568 */       ErrorProp angle = new ErrorProp();
/* 569 */       for (int i = 0; i < this.numberOfTMmeasurementsPrism; i++) {
/* 570 */         angle.reset(this.anglesRadTM[i], this.errorsRadTM[i]);
/* 571 */         phi = angle.over(prismRI).times(airRI);
/* 572 */         phi = ErrorProp.asin(phi);
/* 573 */         phi = alpha.plus(phi);
/* 574 */         phi = prismRI.times(ErrorProp.sin(phi));
/* 575 */         this.effectiveRefractiveIndicesTM[i] = phi.getValue();
/* 576 */         this.effectiveErrorsTM[i] = phi.getError();
/*     */       }
/* 578 */       super.enterTMmodeData(this.thicknessesTM, this.effectiveRefractiveIndicesTM, this.effectiveErrorsTM, this.modeNumbersTM);
/*     */     }
/*     */     else {
/* 581 */       for (int i = 0; i < this.numberOfTMmeasurementsPrism; i++) {
/* 582 */         double phi = this.prismAngleAlphaRad + Math.asin(RefractiveIndex.air(this.wavelength) * this.anglesRadTM[i] / this.prismRefractiveIndex);
/* 583 */         this.effectiveRefractiveIndicesTM[i] = (this.prismRefractiveIndex * Math.sin(phi));
/*     */       }
/* 585 */       super.enterTMmodeData(this.thicknessesTM, this.effectiveRefractiveIndicesTM, this.modeNumbersTM);
/*     */     }
/*     */   }
/*     */ }


/* Location:              /home/menick/Scrivania/ProcessMining/CNMining/CNMining.jar!/flanagan/optics/PrismCoupler.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       0.7.1
 */