/*     */ package org.processmining.plugins.core;
/*     */ 
/*     */ import com.carrotsearch.hppc.IntArrayList;
/*     */ import com.carrotsearch.hppc.IntOpenHashSet;
/*     */ import java.io.BufferedWriter;
/*     */ import java.io.FileWriter;
/*     */ import java.io.IOException;
/*     */ import java.io.PrintStream;
/*     */ import java.util.Vector;
/*     */ 
/*     */ 
/*     */ 
/*     */ public class WeightEstimator
/*     */ {
/*  15 */   public static boolean NORMALIZE_BY_ROW_MAX = false;
/*     */   
/*     */ 
/*     */ 
/*  19 */   public static boolean NON_NEGATIVE_WEIGHTS = false;
/*     */   
/*     */ 
/*  22 */   public static boolean CLOSEST_OCCURRENCE_ONLY = false;
/*     */   
/*     */   public static final int STRATEGY__TASK_PAIRS = 0;
/*     */   
/*     */   public static final int STRATEGY__TASK_PAIRS_NORMALIZED_BY_COUNTS = 1;
/*     */   
/*     */   public static final int STRATEGY__PER_TRACE = 2;
/*     */   
/*     */   public static final int DEFAULT_MAX_GAP = -1;
/*     */   
/*     */   public static final double DEFAULT_FALL_FACTOR = 0.2D;
/*     */   
/*     */   public static final int DEFAULT_ESTIMATION_STRATEGY = 2;
/*     */   
/*     */ 
/*     */   public static void main(String[] args)
/*     */   {
/*  39 */     Vector<IntArrayList> log = new Vector();
/*     */     
/*  41 */     IntArrayList t1 = new IntArrayList();
/*  42 */     t1.add(0);t1.add(1);t1.add(2);t1.add(3);t1.add(4);
/*  43 */     log.add(t1);
/*     */     
/*     */ 
/*  46 */     IntArrayList t2 = new IntArrayList();
/*  47 */     t2.add(0);t2.add(2);t2.add(1);t2.add(3);t2.add(4);
/*  48 */     log.add(t2);
/*     */     
/*     */ 
/*     */ 
/*     */ 
/*  53 */     double[][] weightMatrix = null;
/*     */     
/*     */     try
/*     */     {
/*  57 */       WeightEstimator weightEstimator = new WeightEstimator(5, -1, 0.5D, 2);
/*     */       
/*     */ 
/*  60 */       for (IntArrayList t : log) {
/*  61 */         weightEstimator.addTraceContribution(t);
/*     */       }
/*     */       
/*     */ 
/*  65 */       weightEstimator.computeWeigths();
/*  66 */       weightMatrix = weightEstimator.getDependencyMatrix();
/*     */       
/*  68 */       System.out.println("\n Dependency weights matrix");
/*  69 */       printMatrix(weightMatrix);
/*     */       
/*  71 */       System.out.println("\n Cost matrix (precision=2)");
/*     */       
/*     */ 
/*  74 */       System.out.println("\n Cost matrix (precision=4)");
/*     */     }
/*     */     catch (Exception e)
/*     */     {
/*  78 */       e.printStackTrace();
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
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public static void printMatrix(double[][] matrix)
/*     */   {
/* 161 */     for (int i = 0; i < matrix.length; i++) {
/* 162 */       for (int j = 0; j < matrix[0].length; j++) {
/* 163 */         System.out.print(matrix[i][j] + "\t");
/*     */       }
/* 165 */       System.out.println();
/*     */     }
/*     */   }
/*     */   
/* 169 */   private boolean computationStarted = false;
/*     */   
/*     */   private final int taskNr;
/*     */   
/* 173 */   private int estimationStrategy = 2;
/*     */   
/*     */ 
/* 176 */   private int maxGap = -1;
/*     */   
/* 178 */   private double fallFactor = 1.0D;
/*     */   
/* 180 */   private double[][] unnormDepMatrix = null;
/*     */   
/* 182 */   private double[][] countMatrix = null;
/*     */   
/*     */ 
/* 185 */   private double[][] depMatrix = null;
/*     */   
/*     */ 
/* 188 */   private int[] traceFreq = null;
/*     */   
/*     */   public WeightEstimator(int taskNr)
/*     */     throws Exception
/*     */   {
/* 193 */     this.taskNr = taskNr;
/* 194 */     this.fallFactor = 0.2D;
/* 195 */     this.maxGap = -1;
/* 196 */     this.estimationStrategy = 2;
/*     */     
/* 198 */     this.unnormDepMatrix = new double[taskNr][taskNr];
/* 199 */     this.depMatrix = new double[taskNr][taskNr];
/*     */     
/* 201 */     switch (this.estimationStrategy) {
/*     */     case 1: 
/* 203 */       this.countMatrix = new double[taskNr][taskNr];
/* 204 */       break;
/*     */     case 0: 
/*     */       break;
/*     */     case 2: 
/* 208 */       this.traceFreq = new int[taskNr];
/* 209 */       break;
/*     */     default: 
/* 211 */       throw new Exception("Unknown Estimation Strategy !!");
/*     */     }
/*     */   }
/*     */   
/*     */   public WeightEstimator(int taskNr, int maxGap, double fallFactor, int estimationStrategy)
/*     */     throws Exception
/*     */   {
/* 218 */     this.taskNr = taskNr;
/* 219 */     this.fallFactor = fallFactor;
/* 220 */     this.maxGap = maxGap;
/* 221 */     this.estimationStrategy = estimationStrategy;
/*     */     
/* 223 */     this.unnormDepMatrix = new double[taskNr][taskNr];
/* 224 */     this.depMatrix = new double[taskNr][taskNr];
/*     */     
/* 226 */     switch (estimationStrategy) {
/*     */     case 1: 
/* 228 */       this.countMatrix = new double[taskNr][taskNr];
/* 229 */       break;
/*     */     case 0: 
/*     */       break;
/*     */     case 2: 
/* 233 */       this.traceFreq = new int[taskNr];
/* 234 */       break;
/*     */     default: 
/* 236 */       throw new Exception("Unknown Estimation Strategy !!");
/*     */     }
/*     */   }
/*     */   
/*     */   public void addTraceContribution(IntArrayList trace)
/*     */   {
/* 242 */     this.computationStarted = true;
/* 243 */     int pos1 = 0;
/*     */     
/* 245 */     IntOpenHashSet visitedTasks = new IntOpenHashSet();
/* 246 */     for (int i = 0; i < trace.size(); i++) {
/* 247 */       int gap = 0;
/* 248 */       double power = 1.0D;
/* 249 */       int task1 = trace.get(i);
/* 250 */       boolean horizonReached = false;
/* 251 */       if (this.estimationStrategy == 2) {
/* 252 */         if (!visitedTasks.contains(task1)) {
/* 253 */           visitedTasks.add(task1);
/* 254 */           this.traceFreq[task1] += 1;
/*     */         }
/*     */         else
/*     */         {
/* 258 */           horizonReached = true;
/*     */         }
/*     */       }
/* 261 */       IntOpenHashSet visitedFollowers = new IntOpenHashSet();
/* 262 */       for (int j = i + 1; (!horizonReached) && ((this.maxGap < 0) || (gap <= this.maxGap)) && (j < trace.size()); j++) {
/* 263 */         int task2 = trace.get(j);
/*     */         
/*     */ 
/* 266 */         horizonReached = (CLOSEST_OCCURRENCE_ONLY) && (task2 == task1) && (gap > 0);
/* 267 */         if (!horizonReached)
/*     */         {
/*     */ 
/* 270 */           boolean nonOverlappingPairs = (CLOSEST_OCCURRENCE_ONLY) || (this.estimationStrategy == 2);
/* 271 */           if ((!nonOverlappingPairs) || (!visitedFollowers.contains(task2))) {
/* 272 */             visitedFollowers.add(task2);
/*     */             
/* 274 */             this.unnormDepMatrix[task1][task2] += power;
/* 275 */             if (this.countMatrix != null) {
/* 276 */               this.countMatrix[task1][task2] += 1.0D;
/*     */             }
/*     */           }
/* 279 */           power *= this.fallFactor;
/*     */         }
/* 281 */         gap++;
/*     */       }
/*     */     }
/*     */   }
/*     */   
/*     */   public void computeWeigths()
/*     */   {
/* 288 */     for (int i = 0; i < this.taskNr; i++) {
/* 289 */       for (int j = 0; j < this.taskNr; j++) { double numerator;
/*     */         double denominator;
/* 291 */         if (i == j)
/*     */         {
/* 293 */           double numerator = this.unnormDepMatrix[i][i];
/* 294 */           double denominator; double denominator; double denominator; switch (this.estimationStrategy) {
/*     */           case 1: 
/* 296 */             denominator = this.countMatrix[i][i] + 1.0D;
/* 297 */             break;
/*     */           case 0: 
/* 299 */             denominator = this.unnormDepMatrix[i][i] + 1.0D;
/* 300 */             break;
/*     */           default: 
/* 302 */             denominator = this.traceFreq[i];
/*     */             
/*     */ 
/* 305 */             break;
/*     */           }
/*     */         }
/*     */         else {
/* 309 */           numerator = this.unnormDepMatrix[i][j] - this.unnormDepMatrix[j][i];
/* 310 */           double denominator; double denominator; switch (this.estimationStrategy) {
/*     */           case 1: 
/* 312 */             denominator = this.countMatrix[i][j] + this.countMatrix[j][i] + 1.0D;
/* 313 */             break;
/*     */           case 0: 
/* 315 */             denominator = this.unnormDepMatrix[i][j] + this.unnormDepMatrix[j][i] + 1.0D;
/* 316 */             break;
/*     */           default: 
/* 318 */             denominator = this.traceFreq[i];
/*     */           }
/*     */           
/*     */         }
/*     */         
/* 323 */         this.depMatrix[i][j] = (numerator / denominator);
/*     */       }
/*     */     }
/* 326 */     if (NORMALIZE_BY_ROW_MAX) {
/* 327 */       normalizeByRowMax();
/*     */     }
/*     */   }
/*     */   
/*     */   public double[][] getDependencyMatrix()
/*     */   {
/* 333 */     if (this.depMatrix == null) {
/* 334 */       computeWeigths();
/*     */     }
/* 336 */     return this.depMatrix;
/*     */   }
/*     */   
/*     */   private void normalizeByRowMax()
/*     */   {
/* 341 */     for (int i = 0; i < this.taskNr; i++) {
/* 342 */       double rowMax = this.depMatrix[i][0];
/* 343 */       for (int j = 1; j < this.taskNr; j++) {
/* 344 */         if (this.depMatrix[i][j] > rowMax)
/* 345 */           rowMax = this.depMatrix[i][j];
/*     */       }
/* 347 */       for (int j = 0; j < this.taskNr; j++) {
/* 348 */         if (rowMax != 0.0D) {
/* 349 */           this.depMatrix[i][j] /= rowMax;
/*     */         }
/*     */       }
/*     */     }
/*     */   }
/*     */   
/*     */   public void saveDependencyMatrix(String fileName) {
/* 356 */     if (this.depMatrix == null) {
/* 357 */       computeWeigths();
/*     */     }
/*     */     try {
/* 360 */       BufferedWriter br = new BufferedWriter(new FileWriter(fileName));
/* 361 */       for (int i = 0; i < this.depMatrix.length; i++) {
/* 362 */         for (int j = 0; j < this.depMatrix[0].length; j++) {
/* 363 */           br.append(this.depMatrix[i][j] + "\t");
/*     */         }
/* 365 */         br.newLine();
/*     */       }
/* 367 */       br.close();
/*     */     }
/*     */     catch (IOException e)
/*     */     {
/* 371 */       e.printStackTrace();
/*     */     }
/*     */   }
/*     */   
/*     */   public void saveDependencyMatrix(String fileName, String[] taskLabels)
/*     */   {
/* 377 */     if (this.depMatrix == null) {
/* 378 */       computeWeigths();
/*     */     }
/*     */     try {
/* 381 */       BufferedWriter br = new BufferedWriter(new FileWriter(fileName));
/* 382 */       br.append("\t");
/* 383 */       for (int i = 0; i < this.depMatrix.length; i++)
/* 384 */         br.append(taskLabels[i] + "\t");
/* 385 */       br.newLine();
/* 386 */       for (int i = 0; i < this.depMatrix.length; i++) {
/* 387 */         br.append(taskLabels[i] + "\t");
/* 388 */         for (int j = 0; j < this.depMatrix[0].length; j++) {
/* 389 */           br.append(this.depMatrix[i][j] + "\t");
/*     */         }
/* 391 */         br.newLine();
/*     */       }
/* 393 */       br.close();
/*     */     }
/*     */     catch (IOException e)
/*     */     {
/* 397 */       e.printStackTrace();
/*     */     }
/*     */   }
/*     */   
/*     */   public void setEstimationStrategy(int estimationStrategy) throws Exception {
/* 402 */     if (this.computationStarted) throw new Exception("Weigth Evaluation already started!!");
/* 403 */     switch (estimationStrategy) {
/*     */     case 1: 
/* 405 */       if ((this.countMatrix == null) || (this.countMatrix.length != this.taskNr))
/* 406 */         this.countMatrix = new double[this.taskNr][this.taskNr];
/* 407 */       break;
/*     */     case 0: 
/*     */       break;
/*     */     case 2: 
/* 411 */       if ((this.traceFreq == null) || (this.traceFreq.length != this.taskNr))
/* 412 */         this.traceFreq = new int[this.taskNr];
/* 413 */       break;
/*     */     default: 
/* 415 */       throw new Exception("Unknown Estimation Strategy !!");
/*     */     }
/*     */   }
/*     */   
/*     */   public void setFallFactor(double fallFactor) throws Exception {
/* 420 */     if (this.computationStarted) throw new Exception("Weigth Evaluation already started!!");
/* 421 */     this.fallFactor = fallFactor;
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
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public void setMaxGap(int maxGap)
/*     */     throws Exception
/*     */   {
/* 503 */     if (this.computationStarted) throw new Exception("Weigth Evaluation already started!!");
/* 504 */     this.maxGap = maxGap;
/*     */   }
/*     */ }


/* Location:              /home/menick/Scrivania/ProcessMining/CNMining/CNMining.jar!/org/processmining/plugins/core/WeightEstimator.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       0.7.1
 */