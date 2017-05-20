/*     */ package flanagan.analysis;
/*     */ 
/*     */ import flanagan.io.FileOutput;
/*     */ import flanagan.math.ArrayMaths;
/*     */ import flanagan.math.Fmath;
/*     */ import flanagan.math.Matrix;
/*     */ import java.io.PrintStream;
/*     */ import java.math.BigDecimal;
/*     */ import java.math.BigInteger;
/*     */ import java.text.DateFormat;
/*     */ import java.util.ArrayList;
/*     */ import java.util.Date;
/*     */ import java.util.Vector;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class RankAnalysis
/*     */ {
/*  54 */   private double[][] values = (double[][])null;
/*  55 */   private double[][] errors = (double[][])null;
/*     */   
/*  57 */   private double[] valuesDiagonal = null;
/*  58 */   private double[] errorsDiagonal = null;
/*     */   
/*  60 */   private double[][] reducedValues = (double[][])null;
/*  61 */   private double[][] reducedErrors = (double[][])null;
/*     */   
/*  63 */   private double[] reducedValuesDiagonal = null;
/*  64 */   private double[] reducedErrorsDiagonal = null;
/*  65 */   private double[] reducedValueOverError = null;
/*  66 */   private double[] probabilityValues = null;
/*  67 */   private double[] mcMullen = null;
/*     */   
/*  69 */   private int numberOfRows = 0;
/*  70 */   private int numberOfColumns = 0;
/*  71 */   private int diagonalLength = 0;
/*     */   
/*  73 */   private int errorType = 3;
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*  78 */   private double[] errorRowMeans = null;
/*  79 */   private double[] errorColumnMeans = null;
/*     */   
/*  81 */   private int numberOfMissingErrors = 0;
/*  82 */   private boolean rowOption = true;
/*     */   
/*     */ 
/*  85 */   private boolean rankAnalysisDone = false;
/*     */   
/*     */ 
/*     */ 
/*     */   public RankAnalysis(double[][] values, double[][] errors)
/*     */   {
/*  91 */     this.values = values;
/*  92 */     this.errors = errors;
/*  93 */     this.errorType = 0;
/*  94 */     preprocessDataOne();
/*     */   }
/*     */   
/*     */   public RankAnalysis(float[][] values, float[][] errors) {
/*  98 */     Matrix matv = new Matrix(values);
/*  99 */     this.values = matv.getArrayCopy();
/* 100 */     Matrix mate = new Matrix(errors);
/* 101 */     this.errors = mate.getArrayCopy();
/* 102 */     this.errorType = 0;
/* 103 */     preprocessDataOne();
/*     */   }
/*     */   
/*     */   public RankAnalysis(long[][] values, long[][] errors) {
/* 107 */     Matrix matv = new Matrix(values);
/* 108 */     this.values = matv.getArrayCopy();
/* 109 */     Matrix mate = new Matrix(errors);
/* 110 */     this.errors = mate.getArrayCopy();
/* 111 */     this.errorType = 0;
/* 112 */     preprocessDataOne();
/*     */   }
/*     */   
/*     */   public RankAnalysis(int[][] values, int[][] errors) {
/* 116 */     Matrix matv = new Matrix(values);
/* 117 */     this.values = matv.getArrayCopy();
/* 118 */     Matrix mate = new Matrix(errors);
/* 119 */     this.errors = mate.getArrayCopy();
/* 120 */     this.errorType = 0;
/* 121 */     preprocessDataOne();
/*     */   }
/*     */   
/*     */   public RankAnalysis(BigDecimal[][] values, BigDecimal[][] errors) {
/* 125 */     Matrix matv = new Matrix(values);
/* 126 */     this.values = matv.getArrayCopy();
/* 127 */     Matrix mate = new Matrix(errors);
/* 128 */     this.errors = mate.getArrayCopy();
/* 129 */     this.errorType = 0;
/* 130 */     preprocessDataOne();
/*     */   }
/*     */   
/*     */   public RankAnalysis(BigInteger[][] values, BigInteger[][] errors) {
/* 134 */     Matrix matv = new Matrix(values);
/* 135 */     this.values = matv.getArrayCopy();
/* 136 */     Matrix mate = new Matrix(errors);
/* 137 */     this.errors = mate.getArrayCopy();
/* 138 */     this.errorType = 0;
/* 139 */     preprocessDataOne();
/*     */   }
/*     */   
/*     */   public RankAnalysis(ArrayMaths[] values, ArrayMaths[] errors) {
/* 143 */     Matrix matv = new Matrix(values);
/* 144 */     this.values = matv.getArrayCopy();
/* 145 */     Matrix mate = new Matrix(errors);
/* 146 */     this.errors = mate.getArrayCopy();
/* 147 */     this.errorType = 0;
/* 148 */     preprocessDataOne();
/*     */   }
/*     */   
/*     */   public RankAnalysis(ArrayList<Object>[] values, ArrayList<Object>[] errors) {
/* 152 */     Matrix matv = new Matrix(values);
/* 153 */     this.values = matv.getArrayCopy();
/* 154 */     Matrix mate = new Matrix(errors);
/* 155 */     this.errors = mate.getArrayCopy();
/* 156 */     this.errorType = 0;
/* 157 */     preprocessDataOne();
/*     */   }
/*     */   
/*     */   public RankAnalysis(Vector<Object>[] values, Vector<Object>[] errors) {
/* 161 */     Matrix matv = new Matrix(values);
/* 162 */     this.values = matv.getArrayCopy();
/* 163 */     Matrix mate = new Matrix(errors);
/* 164 */     this.errors = mate.getArrayCopy();
/* 165 */     this.errorType = 0;
/* 166 */     preprocessDataOne();
/*     */   }
/*     */   
/*     */   public RankAnalysis(Matrix values, Matrix errors) {
/* 170 */     this.values = values.getArrayCopy();
/* 171 */     this.errors = errors.getArrayCopy();
/* 172 */     this.errorType = 0;
/* 173 */     preprocessDataOne();
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */   public RankAnalysis(double[][] values, double[] errors)
/*     */   {
/* 180 */     this.values = values;
/* 181 */     this.errors = oneToTwo(errors, this.values[0].length);
/* 182 */     this.errorType = 1;
/* 183 */     preprocessDataOne();
/*     */   }
/*     */   
/*     */   public RankAnalysis(float[][] values, float[] errors) {
/* 187 */     Matrix matv = new Matrix(values);
/* 188 */     this.values = matv.getArrayCopy();
/* 189 */     ArrayMaths ame = new ArrayMaths(errors);
/* 190 */     this.errors = oneToTwo(ame.array(), this.values[0].length);
/* 191 */     this.errorType = 1;
/* 192 */     preprocessDataOne();
/*     */   }
/*     */   
/*     */   public RankAnalysis(long[][] values, long[] errors)
/*     */   {
/* 197 */     Matrix matv = new Matrix(values);
/* 198 */     this.values = matv.getArrayCopy();
/* 199 */     ArrayMaths ame = new ArrayMaths(errors);
/* 200 */     this.errors = oneToTwo(ame.array(), this.values[0].length);
/* 201 */     this.errorType = 1;
/* 202 */     preprocessDataOne();
/*     */   }
/*     */   
/*     */   public RankAnalysis(int[][] values, int[] errors) {
/* 206 */     Matrix matv = new Matrix(values);
/* 207 */     this.values = matv.getArrayCopy();
/* 208 */     ArrayMaths ame = new ArrayMaths(errors);
/* 209 */     this.errors = oneToTwo(ame.array(), this.values[0].length);
/* 210 */     this.errorType = 1;
/* 211 */     preprocessDataOne();
/*     */   }
/*     */   
/*     */   public RankAnalysis(BigDecimal[][] values, BigDecimal[] errors) {
/* 215 */     Matrix matv = new Matrix(values);
/* 216 */     this.values = matv.getArrayCopy();
/* 217 */     ArrayMaths ame = new ArrayMaths(errors);
/* 218 */     this.errors = oneToTwo(ame.array(), this.values[0].length);
/* 219 */     this.errorType = 1;
/* 220 */     preprocessDataOne();
/*     */   }
/*     */   
/*     */   public RankAnalysis(BigInteger[][] values, BigInteger[] errors) {
/* 224 */     Matrix matv = new Matrix(values);
/* 225 */     this.values = matv.getArrayCopy();
/* 226 */     ArrayMaths ame = new ArrayMaths(errors);
/* 227 */     this.errors = oneToTwo(ame.array(), this.values[0].length);
/* 228 */     this.errorType = 1;
/* 229 */     preprocessDataOne();
/*     */   }
/*     */   
/*     */   public RankAnalysis(ArrayMaths[] values, ArrayMaths errors) {
/* 233 */     Matrix matv = new Matrix(values);
/* 234 */     this.values = matv.getArrayCopy();
/* 235 */     this.errors = oneToTwo(errors.array(), this.values[0].length);
/* 236 */     this.errorType = 1;
/* 237 */     preprocessDataOne();
/*     */   }
/*     */   
/*     */   public RankAnalysis(ArrayList<Object>[] values, ArrayList<Object> errors) {
/* 241 */     Matrix matv = new Matrix(values);
/* 242 */     this.values = matv.getArrayCopy();
/* 243 */     ArrayMaths ame = new ArrayMaths(errors);
/* 244 */     this.errors = oneToTwo(ame.array(), this.values[0].length);
/* 245 */     this.errorType = 1;
/* 246 */     preprocessDataOne();
/*     */   }
/*     */   
/*     */   public RankAnalysis(Vector<Object>[] values, Vector<Object> errors) {
/* 250 */     Matrix matv = new Matrix(values);
/* 251 */     this.values = matv.getArrayCopy();
/* 252 */     ArrayMaths ame = new ArrayMaths(errors);
/* 253 */     this.errors = oneToTwo(ame.array(), this.values[0].length);
/* 254 */     this.errorType = 1;
/* 255 */     preprocessDataOne();
/*     */   }
/*     */   
/*     */   public RankAnalysis(Scores values) {
/* 259 */     this.values = values.usedScoresAsRowPerItem();
/* 260 */     Matrix mat = new Matrix(this.values);
/* 261 */     double[] errors = mat.rowStandardDeviations();
/* 262 */     ArrayMaths ame = new ArrayMaths(errors);
/* 263 */     this.errors = oneToTwo(ame.array(), this.values[0].length);
/* 264 */     this.errorType = 1;
/* 265 */     preprocessDataOne();
/*     */   }
/*     */   
/*     */   public RankAnalysis(Cronbach values) {
/* 269 */     this.values = values.usedScoresAsRowPerItem();
/* 270 */     Matrix mat = new Matrix(this.values);
/* 271 */     double[] errors = mat.rowStandardDeviations();
/* 272 */     ArrayMaths ame = new ArrayMaths(errors);
/* 273 */     this.errors = oneToTwo(ame.array(), this.values[0].length);
/* 274 */     this.errorType = 1;
/* 275 */     preprocessDataOne();
/*     */   }
/*     */   
/*     */   public RankAnalysis(PCA values) {
/* 279 */     this.values = values.usedScoresAsRowPerItem();
/* 280 */     Matrix mat = new Matrix(this.values);
/* 281 */     double[] errors = mat.rowStandardDeviations();
/* 282 */     ArrayMaths ame = new ArrayMaths(errors);
/* 283 */     this.errors = oneToTwo(ame.array(), this.values[0].length);
/* 284 */     this.errorType = 1;
/* 285 */     preprocessDataOne();
/*     */   }
/*     */   
/*     */   public RankAnalysis(double[][] values, double commonError)
/*     */   {
/* 290 */     this.values = values;
/* 291 */     this.errorType = 2;
/* 292 */     preprocessDataTwo(commonError);
/*     */   }
/*     */   
/*     */   public RankAnalysis(float[][] values, float commonError) {
/* 296 */     Matrix matv = new Matrix(values);
/* 297 */     this.values = matv.getArrayCopy();
/* 298 */     this.errorType = 2;
/* 299 */     preprocessDataTwo(commonError);
/*     */   }
/*     */   
/*     */   public RankAnalysis(long[][] values, long commonError) {
/* 303 */     Matrix matv = new Matrix(values);
/* 304 */     this.values = matv.getArrayCopy();
/* 305 */     this.errorType = 2;
/* 306 */     preprocessDataTwo(commonError);
/*     */   }
/*     */   
/*     */   public RankAnalysis(int[][] values, int commonError) {
/* 310 */     Matrix matv = new Matrix(values);
/* 311 */     this.values = matv.getArrayCopy();
/* 312 */     this.errorType = 2;
/* 313 */     preprocessDataTwo(commonError);
/*     */   }
/*     */   
/*     */   public RankAnalysis(BigDecimal[][] values, BigDecimal commonError) {
/* 317 */     Matrix matv = new Matrix(values);
/* 318 */     this.values = matv.getArrayCopy();
/* 319 */     this.errorType = 2;
/* 320 */     preprocessDataTwo(commonError.doubleValue());
/*     */   }
/*     */   
/*     */   public RankAnalysis(BigInteger[][] values, BigInteger commonError) {
/* 324 */     Matrix matv = new Matrix(values);
/* 325 */     this.values = matv.getArrayCopy();
/* 326 */     this.errorType = 2;
/* 327 */     preprocessDataTwo(commonError.doubleValue());
/*     */   }
/*     */   
/*     */   public RankAnalysis(ArrayMaths[] values, double commonError) {
/* 331 */     Matrix matv = new Matrix(values);
/* 332 */     this.values = matv.getArrayCopy();
/* 333 */     this.errorType = 2;
/* 334 */     preprocessDataTwo(commonError);
/*     */   }
/*     */   
/*     */   public RankAnalysis(ArrayList<Object>[] values, double commonError) {
/* 338 */     Matrix matv = new Matrix(values);
/* 339 */     this.values = matv.getArrayCopy();
/* 340 */     this.errorType = 2;
/* 341 */     preprocessDataTwo(commonError);
/*     */   }
/*     */   
/*     */   public RankAnalysis(Vector<Object>[] values, double commonError) {
/* 345 */     Matrix matv = new Matrix(values);
/* 346 */     this.values = matv.getArrayCopy();
/* 347 */     this.errorType = 2;
/* 348 */     preprocessDataTwo(commonError);
/*     */   }
/*     */   
/*     */   public RankAnalysis(Matrix values, double commonError) {
/* 352 */     this.values = values.getArrayCopy();
/* 353 */     this.errorType = 2;
/* 354 */     preprocessDataTwo(commonError);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */   public RankAnalysis(double[][] values)
/*     */   {
/* 361 */     this.values = values;
/* 362 */     this.errorType = 3;
/* 363 */     preprocessDataThree();
/*     */   }
/*     */   
/*     */   public RankAnalysis(float[][] values) {
/* 367 */     Matrix matv = new Matrix(values);
/* 368 */     this.values = matv.getArrayCopy();
/* 369 */     this.errorType = 3;
/* 370 */     preprocessDataThree();
/*     */   }
/*     */   
/*     */   public RankAnalysis(long[][] values) {
/* 374 */     Matrix matv = new Matrix(values);
/* 375 */     this.values = matv.getArrayCopy();
/* 376 */     this.errorType = 3;
/* 377 */     preprocessDataThree();
/*     */   }
/*     */   
/*     */   public RankAnalysis(int[][] values) {
/* 381 */     Matrix matv = new Matrix(values);
/* 382 */     this.values = matv.getArrayCopy();
/* 383 */     this.errorType = 3;
/* 384 */     preprocessDataThree();
/*     */   }
/*     */   
/*     */   public RankAnalysis(BigDecimal[][] values) {
/* 388 */     Matrix matv = new Matrix(values);
/* 389 */     this.values = matv.getArrayCopy();
/* 390 */     this.errorType = 3;
/* 391 */     preprocessDataThree();
/*     */   }
/*     */   
/*     */   public RankAnalysis(BigInteger[][] values) {
/* 395 */     Matrix matv = new Matrix(values);
/* 396 */     this.values = matv.getArrayCopy();
/* 397 */     this.errorType = 3;
/* 398 */     preprocessDataThree();
/*     */   }
/*     */   
/*     */   public RankAnalysis(ArrayMaths[] values) {
/* 402 */     Matrix matv = new Matrix(values);
/* 403 */     this.values = matv.getArrayCopy();
/* 404 */     this.errorType = 3;
/* 405 */     preprocessDataThree();
/*     */   }
/*     */   
/*     */   public RankAnalysis(ArrayList<Object>[] values) {
/* 409 */     Matrix matv = new Matrix(values);
/* 410 */     this.values = matv.getArrayCopy();
/* 411 */     this.errorType = 3;
/* 412 */     preprocessDataThree();
/*     */   }
/*     */   
/*     */   public RankAnalysis(Vector<Object>[] values) {
/* 416 */     Matrix matv = new Matrix(values);
/* 417 */     this.values = matv.getArrayCopy();
/* 418 */     this.errorType = 3;
/* 419 */     preprocessDataThree();
/*     */   }
/*     */   
/*     */   public RankAnalysis(Matrix values) {
/* 423 */     this.values = values.getArrayCopy();
/* 424 */     this.errorType = 3;
/* 425 */     preprocessDataThree();
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   private double[][] oneToTwo(double[] errors, int nCols)
/*     */   {
/* 433 */     int nRows = errors.length;
/* 434 */     double[][] ret = new double[nRows][nCols];
/* 435 */     for (int i = 0; i < nRows; i++) {
/* 436 */       for (int j = 0; j < nCols; j++) {
/* 437 */         ret[i][j] = errors[i];
/*     */       }
/*     */     }
/* 440 */     return ret;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   private void preprocessDataOne()
/*     */   {
/* 448 */     this.numberOfRows = this.values.length;
/* 449 */     this.numberOfColumns = this.values[0].length;
/* 450 */     for (int i = 1; i < this.numberOfRows; i++) {
/* 451 */       if (this.values[i].length != this.numberOfColumns) throw new IllegalArgumentException("All rows of the value matrix must be of the same length");
/*     */     }
/* 453 */     for (int i = 0; i < this.numberOfRows; i++) {
/* 454 */       if (this.errors[i].length != this.numberOfColumns) throw new IllegalArgumentException("All rows of the error matrix must be of the same length as those of the value matrix");
/*     */     }
/* 456 */     this.diagonalLength = this.numberOfRows;
/* 457 */     if (this.numberOfRows > this.numberOfColumns) { this.diagonalLength = this.numberOfColumns;
/*     */     }
/*     */     
/* 460 */     for (int i = 0; i < this.numberOfRows; i++) {
/* 461 */       for (int j = 0; j < this.numberOfColumns; j++) {
/* 462 */         this.errors[i][j] *= this.errors[i][j];
/*     */       }
/*     */     }
/*     */   }
/*     */   
/*     */ 
/*     */   private void preprocessDataTwo(double commonError)
/*     */   {
/* 470 */     this.numberOfRows = this.values.length;
/* 471 */     this.numberOfColumns = this.values[0].length;
/* 472 */     for (int i = 1; i < this.numberOfRows; i++) {
/* 473 */       if (this.values[i].length != this.numberOfColumns) throw new IllegalArgumentException("All rows of the value matrix must be of the same length");
/*     */     }
/* 475 */     this.diagonalLength = this.numberOfRows;
/* 476 */     if (this.numberOfRows > this.numberOfColumns) { this.diagonalLength = this.numberOfColumns;
/*     */     }
/*     */     
/* 479 */     this.errors = new double[this.numberOfRows][this.numberOfColumns];
/* 480 */     for (int i = 0; i < this.numberOfRows; i++) {
/* 481 */       for (int j = 0; j < this.numberOfColumns; j++) {
/* 482 */         this.errors[i][j] = (commonError * commonError);
/*     */       }
/*     */     }
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */   private void preprocessDataThree()
/*     */   {
/* 491 */     this.numberOfRows = this.values.length;
/* 492 */     this.numberOfColumns = this.values[0].length;
/* 493 */     for (int i = 1; i < this.numberOfRows; i++) {
/* 494 */       if (this.values[i].length != this.numberOfColumns) throw new IllegalArgumentException("All rows of the value matrix must be of the same length");
/*     */     }
/* 496 */     this.diagonalLength = this.numberOfRows;
/* 497 */     if (this.numberOfRows > this.numberOfColumns) { this.diagonalLength = this.numberOfColumns;
/*     */     }
/*     */     
/* 500 */     this.errors = new double[this.numberOfRows][this.numberOfColumns];
/* 501 */     double error = 0.0D;
/* 502 */     for (int i = 0; i < this.numberOfRows; i++) {
/* 503 */       for (int j = 0; j < this.numberOfColumns; j++) {
/* 504 */         error = Math.pow(10.0D, Math.floor(Math.log10(Math.abs(this.values[i][j])))) * 5.0E-16D;
/* 505 */         this.errors[i][j] = (error * error);
/*     */       }
/*     */     }
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */   public void useErrorRowMean()
/*     */   {
/* 514 */     this.rowOption = true;
/*     */   }
/*     */   
/*     */   public void useErrorColumnMean()
/*     */   {
/* 519 */     this.rowOption = false;
/*     */   }
/*     */   
/*     */   public int nMissingErrors()
/*     */   {
/* 524 */     return this.numberOfMissingErrors;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   private void rankAnalysis()
/*     */   {
/* 534 */     this.errorRowMeans = new double[this.numberOfRows];
/* 535 */     this.errorColumnMeans = new double[this.numberOfColumns];
/* 536 */     this.numberOfMissingErrors = 0;
/* 537 */     for (int i = 0; i < this.numberOfRows; i++) {
/* 538 */       int counter = 0;
/* 539 */       for (int j = 0; j < this.numberOfColumns; j++) {
/* 540 */         if (!Double.isNaN(this.errors[i][j])) {
/* 541 */           if (this.errors[i][j] < 0.0D) this.errors[i][j] *= -1.0D;
/* 542 */           this.errorRowMeans[i] += this.errors[i][j];
/* 543 */           counter++;
/*     */         }
/*     */         else {
/* 546 */           this.numberOfMissingErrors += 1;
/*     */         }
/*     */       }
/* 549 */       this.errorRowMeans[i] /= counter;
/*     */     }
/*     */     
/*     */ 
/* 553 */     for (int i = 0; i < this.numberOfColumns; i++) {
/* 554 */       int counter = 0;
/* 555 */       for (int j = 0; j < this.numberOfRows; j++) {
/* 556 */         if (!Double.isNaN(this.errors[j][i])) {
/* 557 */           this.errorColumnMeans[i] += this.errors[j][i];
/* 558 */           counter++;
/*     */         }
/*     */       }
/* 561 */       this.errorColumnMeans[i] /= counter;
/*     */     }
/*     */     
/*     */ 
/* 565 */     if (this.numberOfMissingErrors > 0) {
/* 566 */       for (int i = 0; i < this.numberOfRows; i++) {
/* 567 */         for (int j = 0; j < this.numberOfColumns; j++) {
/* 568 */           if (Double.isNaN(this.errors[i][j])) {
/* 569 */             if (this.rowOption) {
/* 570 */               this.errors[i][j] = this.errorRowMeans[i];
/*     */             }
/*     */             else {
/* 573 */               this.errors[i][j] = this.errorColumnMeans[i];
/*     */             }
/*     */           }
/*     */         }
/*     */       }
/*     */     }
/*     */     
/*     */ 
/*     */ 
/* 582 */     this.reducedValues = this.values;
/* 583 */     this.reducedErrors = this.errors;
/* 584 */     Matrix matv0 = new Matrix(this.reducedValues);
/* 585 */     Matrix mate0 = new Matrix(this.reducedErrors);
/* 586 */     int nn = this.diagonalLength - 1;
/*     */     
/* 588 */     for (int i = 0; i < nn; i++) {
/* 589 */       matv0 = new Matrix(this.reducedValues);
/*     */       
/*     */ 
/* 592 */       int nrow = this.numberOfRows - i;
/* 593 */       int ncol = this.numberOfColumns - i;
/* 594 */       Matrix mat1 = matv0.getSubMatrix(i, i, this.numberOfRows - 1, this.numberOfColumns - 1);
/* 595 */       double[][] subv = mat1.getArrayCopy();
/*     */       
/*     */ 
/* 598 */       int[] max = mat1.pivot();
/* 599 */       int pivotI = max[0] + i;
/* 600 */       int pivotJ = max[1] + i;
/*     */       
/*     */ 
/* 603 */       double[] holdv1 = this.reducedValues[i];
/* 604 */       double[] holde1 = this.reducedErrors[i];
/* 605 */       this.reducedValues[i] = this.reducedValues[pivotI];
/* 606 */       this.reducedErrors[i] = this.reducedErrors[pivotI];
/* 607 */       this.reducedValues[pivotI] = holdv1;
/* 608 */       this.reducedErrors[pivotI] = holde1;
/*     */       
/*     */ 
/* 611 */       double holdv2 = 0.0D;
/* 612 */       double holde2 = 0.0D;
/* 613 */       for (int j = 0; j < this.numberOfRows; j++) {
/* 614 */         holdv2 = this.reducedValues[j][i];
/* 615 */         holde2 = this.reducedErrors[j][i];
/* 616 */         this.reducedValues[j][i] = this.reducedValues[j][pivotJ];
/* 617 */         this.reducedErrors[j][i] = this.reducedErrors[j][pivotJ];
/* 618 */         this.reducedValues[j][pivotJ] = holdv2;
/* 619 */         this.reducedErrors[j][pivotJ] = holde2;
/*     */       }
/*     */       
/*     */ 
/* 623 */       Matrix matValueHold = new Matrix(this.reducedValues);
/* 624 */       Matrix matErrorHold = new Matrix(this.reducedErrors);
/* 625 */       double[][] valueHold = matValueHold.getArrayCopy();
/* 626 */       double[][] errorHold = matErrorHold.getArrayCopy();
/*     */       
/* 628 */       for (int j = i + 1; j < this.numberOfRows; j++) {
/* 629 */         for (int k = i; k < this.numberOfColumns; k++) {
/* 630 */           double ratio1 = 1.0D;
/* 631 */           if (this.reducedValues[j][i] != this.reducedValues[i][i]) ratio1 = this.reducedValues[j][i] / this.reducedValues[i][i];
/* 632 */           valueHold[j][k] = (this.reducedValues[j][k] - ratio1 * this.reducedValues[i][k]);
/* 633 */           double hold = this.reducedErrors[j][k] + this.reducedErrors[i][k] * ratio1 * ratio1;
/* 634 */           double ratio2 = 1.0D;
/* 635 */           if (this.reducedValues[i][k] != this.reducedValues[i][i]) ratio2 = this.reducedValues[i][k] / this.reducedValues[i][i];
/* 636 */           hold += this.reducedErrors[j][i] * ratio2 * ratio2;
/* 637 */           errorHold[j][k] = (hold + this.reducedErrors[i][i] * ratio1 * ratio1 * ratio2 * ratio2);
/*     */         }
/*     */       }
/* 640 */       matValueHold = new Matrix(valueHold);
/* 641 */       matErrorHold = new Matrix(errorHold);
/* 642 */       this.reducedValues = matValueHold.getArrayCopy();
/* 643 */       this.reducedErrors = matErrorHold.getArrayCopy();
/*     */     }
/*     */     
/*     */ 
/* 647 */     for (int i = 0; i < this.numberOfRows; i++) {
/* 648 */       for (int j = 0; j < this.numberOfColumns; j++) {
/* 649 */         this.reducedErrors[i][j] = Math.sqrt(this.reducedErrors[i][j]);
/*     */       }
/*     */     }
/*     */     
/*     */ 
/* 654 */     for (int i = 1; i < this.diagonalLength; i++) {
/* 655 */       for (int j = 0; j < i; j++) {
/* 656 */         this.reducedValues[i][j] = 0.0D;
/* 657 */         this.reducedErrors[i][j] = 0.0D;
/*     */       }
/*     */     }
/*     */     
/* 661 */     if (this.diagonalLength < this.numberOfRows) {
/* 662 */       for (int i = this.diagonalLength; i < this.numberOfRows; i++) {
/* 663 */         for (int j = 0; j < this.numberOfColumns; j++) {
/* 664 */           this.reducedValues[i][j] = 0.0D;
/* 665 */           this.reducedErrors[i][j] = 0.0D;
/*     */         }
/*     */       }
/*     */     }
/*     */     
/*     */ 
/* 671 */     this.reducedValuesDiagonal = new double[this.diagonalLength];
/* 672 */     this.reducedErrorsDiagonal = new double[this.diagonalLength];
/* 673 */     this.reducedValueOverError = new double[this.diagonalLength];
/* 674 */     this.probabilityValues = new double[this.diagonalLength];
/* 675 */     this.mcMullen = new double[this.diagonalLength];
/*     */     
/* 677 */     for (int i = 0; i < this.diagonalLength; i++) {
/* 678 */       this.reducedValuesDiagonal[i] = this.reducedValues[i][i];
/* 679 */       this.reducedErrorsDiagonal[i] = this.reducedErrors[i][i];
/* 680 */       this.reducedValueOverError[i] = Math.abs(this.reducedValuesDiagonal[i] / this.reducedErrorsDiagonal[i]);
/* 681 */       this.probabilityValues[i] = (1.0D - Stat.gaussianCDF(0.0D, 1.0D, -this.reducedValueOverError[i], this.reducedValueOverError[i]));
/*     */     }
/*     */     
/*     */ 
/* 685 */     for (int i = 0; i < this.numberOfRows; i++) {
/* 686 */       double sum = 0.0D;
/* 687 */       for (int j = i; j < this.numberOfColumns; j++) {
/* 688 */         sum += this.reducedValues[i][j] * this.reducedValues[i][j];
/*     */       }
/* 690 */       this.mcMullen[i] = (Math.sqrt(sum) / (this.numberOfColumns - i));
/*     */     }
/*     */     
/* 693 */     this.rankAnalysisDone = true;
/*     */   }
/*     */   
/*     */   public void analysis()
/*     */   {
/* 698 */     analysis("RankAnalysisOutput.txt");
/*     */   }
/*     */   
/*     */   public void analysis(String fileName) {
/* 702 */     if (!this.rankAnalysisDone) { rankAnalysis();
/*     */     }
/* 704 */     int posdot = fileName.indexOf(".");
/* 705 */     if (posdot == -1) { fileName = fileName + ".txt";
/*     */     }
/* 707 */     FileOutput fout = new FileOutput(fileName);
/* 708 */     fout.println("Rank Analysis");
/* 709 */     fout.println("File name:   " + fileName);
/* 710 */     Date d = new Date();
/* 711 */     String day = DateFormat.getDateInstance().format(d);
/* 712 */     String tim = DateFormat.getTimeInstance().format(d);
/* 713 */     fout.println("Program executed at " + tim + " on " + day);
/* 714 */     fout.println();
/*     */     
/* 716 */     fout.println("Number of rows    " + this.numberOfRows);
/* 717 */     fout.println("Number of columns " + this.numberOfColumns);
/* 718 */     if (this.numberOfMissingErrors > 0) {
/* 719 */       fout.println("Number of substituted missing errors" + this.numberOfMissingErrors);
/* 720 */       if (this.rowOption) {
/* 721 */         fout.println("Row means used as the substituted value/s");
/*     */       }
/*     */       else {
/* 724 */         fout.println("Column means used as the substituted value/s");
/*     */       }
/*     */     }
/* 727 */     fout.println();
/*     */     
/* 729 */     switch (this.errorType) {
/* 730 */     case 0:  fout.println("Matrix of individual errors supplied");
/* 731 */       break;
/* 732 */     case 1:  fout.println("Common error for all elements in each each row supplied");
/* 733 */       break;
/* 734 */     case 2:  fout.println("Single common error for all elements in the matrix supplied");
/* 735 */       break;
/* 736 */     case 3:  fout.println("No errors supplied - estimate of the rounding errors used");
/*     */     }
/* 738 */     fout.println();
/*     */     
/* 740 */     int field1 = 30;
/* 741 */     int field2 = 15;
/* 742 */     int trunc = 4;
/* 743 */     if (this.errorType != 3) {
/* 744 */       fout.print("Reduced", field2);
/* 745 */       fout.print("Reduced", field2);
/* 746 */       fout.print("V/E Ratio", field2);
/* 747 */       fout.print("P-value", field2);
/* 748 */       fout.println("McMullen");
/*     */       
/* 750 */       fout.print("Value", field2);
/* 751 */       fout.print("Error", field2);
/* 752 */       fout.print("    ", field2);
/* 753 */       fout.print("    ", field2);
/* 754 */       fout.println("rms");
/*     */       
/* 756 */       fout.print("Diagonal (V)", field2);
/* 757 */       fout.print("Diagonal (E)", field2);
/* 758 */       fout.print("   ", field2);
/* 759 */       fout.print("   ", field2);
/* 760 */       fout.println("   ");
/*     */     }
/*     */     else {
/* 763 */       fout.print("Reduced", field2);
/* 764 */       fout.print("Reduced", field2);
/* 765 */       fout.print("V/E Ratio", field2);
/* 766 */       fout.print("P-value", field2);
/* 767 */       fout.println("McMullen");
/*     */       
/* 769 */       fout.print("Value", field2);
/* 770 */       fout.print("Estimated", field2);
/* 771 */       fout.print("    ", field2);
/* 772 */       fout.print("    ", field2);
/* 773 */       fout.println("rms");
/*     */       
/* 775 */       fout.print("Diagonal (V)", field2);
/* 776 */       fout.print("Rounding", field2);
/* 777 */       fout.print("   ", field2);
/* 778 */       fout.print("   ", field2);
/* 779 */       fout.println("   ");
/*     */       
/* 781 */       fout.print("   ", field2);
/* 782 */       fout.print("Error (E)", field2);
/* 783 */       fout.print("   ", field2);
/* 784 */       fout.print("   ", field2);
/* 785 */       fout.println("   ");
/*     */     }
/*     */     
/* 788 */     for (int i = 0; i < this.diagonalLength; i++) {
/* 789 */       fout.print(Fmath.truncate(this.reducedValuesDiagonal[i], trunc), field2);
/* 790 */       fout.print(Fmath.truncate(this.reducedErrorsDiagonal[i], trunc), field2);
/* 791 */       fout.print(Fmath.truncate(this.reducedValueOverError[i], trunc), field2);
/* 792 */       fout.print(Fmath.truncate(this.probabilityValues[i], trunc), field2);
/* 793 */       fout.println(Fmath.truncate(this.mcMullen[i], trunc));
/*     */     }
/*     */     
/* 796 */     System.out.println("Analysis written to text file " + fileName);
/*     */     
/* 798 */     fout.close();
/*     */   }
/*     */   
/*     */ 
/*     */   public double[][] originalValues()
/*     */   {
/* 804 */     if (!this.rankAnalysisDone) rankAnalysis();
/* 805 */     return this.values;
/*     */   }
/*     */   
/*     */   public double[][] originalErrors()
/*     */   {
/* 810 */     if (!this.rankAnalysisDone) rankAnalysis();
/* 811 */     return this.errors;
/*     */   }
/*     */   
/*     */   public double[][] reducedValues()
/*     */   {
/* 816 */     if (!this.rankAnalysisDone) rankAnalysis();
/* 817 */     return this.reducedValues;
/*     */   }
/*     */   
/*     */   public double[][] reducedErrors()
/*     */   {
/* 822 */     if (!this.rankAnalysisDone) rankAnalysis();
/* 823 */     return this.reducedErrors;
/*     */   }
/*     */   
/*     */   public double[] reducedValuesDiagonal()
/*     */   {
/* 828 */     if (!this.rankAnalysisDone) rankAnalysis();
/* 829 */     return this.reducedValuesDiagonal;
/*     */   }
/*     */   
/*     */   public double[] reducedErrorsDiagonal()
/*     */   {
/* 834 */     if (!this.rankAnalysisDone) rankAnalysis();
/* 835 */     return this.reducedErrorsDiagonal;
/*     */   }
/*     */   
/*     */   public double[] reducedRatiosDiagonal()
/*     */   {
/* 840 */     if (!this.rankAnalysisDone) rankAnalysis();
/* 841 */     return this.reducedValueOverError;
/*     */   }
/*     */   
/*     */   public double[] probabilityValues()
/*     */   {
/* 846 */     if (!this.rankAnalysisDone) rankAnalysis();
/* 847 */     return this.probabilityValues;
/*     */   }
/*     */   
/*     */   public double[] mcMullenValues()
/*     */   {
/* 852 */     if (!this.rankAnalysisDone) rankAnalysis();
/* 853 */     return this.mcMullen;
/*     */   }
/*     */   
/*     */   public int nRows()
/*     */   {
/* 858 */     if (!this.rankAnalysisDone) rankAnalysis();
/* 859 */     return this.numberOfRows;
/*     */   }
/*     */   
/*     */   public int nColumns()
/*     */   {
/* 864 */     if (!this.rankAnalysisDone) rankAnalysis();
/* 865 */     return this.numberOfColumns;
/*     */   }
/*     */   
/*     */   public int nDiagonalElements()
/*     */   {
/* 870 */     if (!this.rankAnalysisDone) rankAnalysis();
/* 871 */     return this.diagonalLength;
/*     */   }
/*     */ }


/* Location:              /home/menick/Scrivania/ProcessMining/CNMining/CNMining.jar!/flanagan/analysis/RankAnalysis.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       0.7.1
 */