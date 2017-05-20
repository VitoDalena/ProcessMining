/*     */ package cern.colt.matrix.doublealgo;
/*     */ 
/*     */ import cern.colt.Timer;
/*     */ import cern.colt.function.DoubleDoubleFunction;
/*     */ import cern.colt.list.AbstractDoubleList;
/*     */ import cern.colt.list.AbstractList;
/*     */ import cern.colt.list.DoubleArrayList;
/*     */ import cern.colt.matrix.DoubleFactory1D;
/*     */ import cern.colt.matrix.DoubleFactory2D;
/*     */ import cern.colt.matrix.DoubleMatrix1D;
/*     */ import cern.colt.matrix.DoubleMatrix2D;
/*     */ import cern.colt.matrix.DoubleMatrix3D;
/*     */ import cern.colt.matrix.impl.AbstractMatrix1D;
/*     */ import cern.colt.matrix.impl.AbstractMatrix2D;
/*     */ import cern.colt.matrix.impl.AbstractMatrix3D;
/*     */ import cern.colt.matrix.impl.DenseDoubleMatrix2D;
/*     */ import cern.jet.math.Functions;
/*     */ import cern.jet.random.engine.MersenneTwister;
/*     */ import cern.jet.random.sampling.RandomSampler;
/*     */ import cern.jet.stat.Descriptive;
/*     */ import edu.cornell.lassp.houle.RngPack.RandomElement;
/*     */ import hep.aida.IHistogram1D;
/*     */ import hep.aida.IHistogram2D;
/*     */ import hep.aida.IHistogram3D;
/*     */ import hep.aida.bin.AbstractBin1D;
/*     */ import hep.aida.bin.BinFunction1D;
/*     */ import hep.aida.bin.DynamicBin1D;
/*     */ import hep.aida.ref.Histogram2D;
/*     */ import hep.aida.ref.Histogram3D;
/*     */ import hep.aida.ref.VariableAxis;
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
/*     */ public class Statistic
/*     */ {
/*  58 */   private static final Functions F = Functions.functions;
/*     */   
/*     */ 
/*     */ 
/*  62 */   public static final VectorVectorFunction EUCLID = new VectorVectorFunction() {
/*     */     public final double apply(DoubleMatrix1D paramAnonymousDoubleMatrix1D1, DoubleMatrix1D paramAnonymousDoubleMatrix1D2) {
/*  64 */       return Math.sqrt(paramAnonymousDoubleMatrix1D1.aggregate(paramAnonymousDoubleMatrix1D2, Functions.plus, Functions.chain(Functions.square, Functions.minus)));
/*     */     }
/*     */   };
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*  71 */   public static final VectorVectorFunction BRAY_CURTIS = new VectorVectorFunction() {
/*     */     public final double apply(DoubleMatrix1D paramAnonymousDoubleMatrix1D1, DoubleMatrix1D paramAnonymousDoubleMatrix1D2) {
/*  73 */       return paramAnonymousDoubleMatrix1D1.aggregate(paramAnonymousDoubleMatrix1D2, Functions.plus, Functions.chain(Functions.abs, Functions.minus)) / paramAnonymousDoubleMatrix1D1.aggregate(paramAnonymousDoubleMatrix1D2, Functions.plus, Functions.plus);
/*     */     }
/*     */   };
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*  80 */   public static final VectorVectorFunction CANBERRA = new VectorVectorFunction() {
/*  81 */     DoubleDoubleFunction fun = new Statistic.4(this);
/*     */     
/*     */ 
/*     */ 
/*     */     public final double apply(DoubleMatrix1D paramAnonymousDoubleMatrix1D1, DoubleMatrix1D paramAnonymousDoubleMatrix1D2)
/*     */     {
/*  87 */       return paramAnonymousDoubleMatrix1D1.aggregate(paramAnonymousDoubleMatrix1D2, Functions.plus, this.fun);
/*     */     }
/*     */   };
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*  94 */   public static final VectorVectorFunction MAXIMUM = new VectorVectorFunction() {
/*     */     public final double apply(DoubleMatrix1D paramAnonymousDoubleMatrix1D1, DoubleMatrix1D paramAnonymousDoubleMatrix1D2) {
/*  96 */       return paramAnonymousDoubleMatrix1D1.aggregate(paramAnonymousDoubleMatrix1D2, Functions.max, Functions.chain(Functions.abs, Functions.minus));
/*     */     }
/*     */   };
/*     */   
/*     */ 
/*     */ 
/*     */ 
/* 103 */   public static final VectorVectorFunction MANHATTAN = new VectorVectorFunction() {
/*     */     public final double apply(DoubleMatrix1D paramAnonymousDoubleMatrix1D1, DoubleMatrix1D paramAnonymousDoubleMatrix1D2) {
/* 105 */       return paramAnonymousDoubleMatrix1D1.aggregate(paramAnonymousDoubleMatrix1D2, Functions.plus, Functions.chain(Functions.abs, Functions.minus));
/*     */     }
/*     */   };
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public static DoubleMatrix2D aggregate(DoubleMatrix2D paramDoubleMatrix2D1, BinFunction1D[] paramArrayOfBinFunction1D, DoubleMatrix2D paramDoubleMatrix2D2)
/*     */   {
/* 145 */     DynamicBin1D localDynamicBin1D = new DynamicBin1D();
/* 146 */     double[] arrayOfDouble = new double[paramDoubleMatrix2D1.rows()];
/* 147 */     DoubleArrayList localDoubleArrayList = new DoubleArrayList(arrayOfDouble);
/* 148 */     int i = paramDoubleMatrix2D1.columns();
/* 149 */     do { paramDoubleMatrix2D1.viewColumn(i).toArray(arrayOfDouble);
/* 150 */       localDynamicBin1D.clear();
/* 151 */       localDynamicBin1D.addAllOf(localDoubleArrayList);
/* 152 */       int j = paramArrayOfBinFunction1D.length;
/* 153 */       do { paramDoubleMatrix2D2.set(j, i, paramArrayOfBinFunction1D[j].apply(localDynamicBin1D));j--;
/* 152 */       } while (j >= 0);
/* 148 */       i--; } while (i >= 0);
/*     */     
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/* 156 */     return paramDoubleMatrix2D2;
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
/*     */   public static DynamicBin1D bin(DoubleMatrix1D paramDoubleMatrix1D)
/*     */   {
/* 212 */     DynamicBin1D localDynamicBin1D = new DynamicBin1D();
/* 213 */     localDynamicBin1D.addAllOf(DoubleFactory1D.dense.toList(paramDoubleMatrix1D));
/* 214 */     return localDynamicBin1D;
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
/*     */   public static DoubleMatrix2D correlation(DoubleMatrix2D paramDoubleMatrix2D)
/*     */   {
/* 231 */     int i = paramDoubleMatrix2D.columns();
/* 232 */     do { j = i;
/* 233 */       do { double d1 = Math.sqrt(paramDoubleMatrix2D.getQuick(i, i));
/* 234 */         double d2 = Math.sqrt(paramDoubleMatrix2D.getQuick(j, j));
/* 235 */         double d3 = paramDoubleMatrix2D.getQuick(i, j);
/* 236 */         double d4 = d3 / (d1 * d2);
/*     */         
/* 238 */         paramDoubleMatrix2D.setQuick(i, j, d4);
/* 239 */         paramDoubleMatrix2D.setQuick(j, i, d4);j--;
/* 232 */       } while (j >= 0);
/* 231 */       i--; } while (i >= 0);
/*     */     
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/* 242 */     int j = paramDoubleMatrix2D.columns(); do { paramDoubleMatrix2D.setQuick(j, j, 1.0D);j--; } while (j >= 0);
/*     */     
/* 244 */     return paramDoubleMatrix2D;
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
/*     */   public static DoubleMatrix2D covariance(DoubleMatrix2D paramDoubleMatrix2D)
/*     */   {
/* 259 */     int i = paramDoubleMatrix2D.rows();
/* 260 */     int j = paramDoubleMatrix2D.columns();
/* 261 */     DenseDoubleMatrix2D localDenseDoubleMatrix2D = new DenseDoubleMatrix2D(j, j);
/*     */     
/* 263 */     double[] arrayOfDouble = new double[j];
/* 264 */     DoubleMatrix1D[] arrayOfDoubleMatrix1D = new DoubleMatrix1D[j];
/* 265 */     int k = j;
/* 266 */     do { arrayOfDoubleMatrix1D[k] = paramDoubleMatrix2D.viewColumn(k);
/* 267 */       arrayOfDouble[k] = arrayOfDoubleMatrix1D[k].zSum();k--;
/* 265 */     } while (k >= 0);
/*     */     
/*     */ 
/*     */ 
/*     */ 
/* 270 */     int m = j;
/* 271 */     do { int n = m + 1;
/* 272 */       do { double d1 = arrayOfDoubleMatrix1D[m].zDotProduct(arrayOfDoubleMatrix1D[n]);
/* 273 */         double d2 = (d1 - arrayOfDouble[m] * arrayOfDouble[n] / i) / i;
/* 274 */         localDenseDoubleMatrix2D.setQuick(m, n, d2);
/* 275 */         localDenseDoubleMatrix2D.setQuick(n, m, d2);n--;
/* 271 */       } while (n >= 0);
/* 270 */       m--; } while (m >= 0);
/*     */     
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/* 278 */     return localDenseDoubleMatrix2D;
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
/*     */   public static IHistogram2D cube(DoubleMatrix1D paramDoubleMatrix1D1, DoubleMatrix1D paramDoubleMatrix1D2, DoubleMatrix1D paramDoubleMatrix1D3)
/*     */   {
/* 317 */     if ((paramDoubleMatrix1D1.size() != paramDoubleMatrix1D2.size()) || (paramDoubleMatrix1D2.size() != paramDoubleMatrix1D3.size())) { throw new IllegalArgumentException("vectors must have same size");
/*     */     }
/* 319 */     double d = 1.0E-9D;
/* 320 */     DoubleArrayList localDoubleArrayList1 = new DoubleArrayList();
/* 321 */     double[] arrayOfDouble = new double[paramDoubleMatrix1D1.size()];
/* 322 */     DoubleArrayList localDoubleArrayList2 = new DoubleArrayList(arrayOfDouble);
/*     */     
/*     */ 
/* 325 */     paramDoubleMatrix1D1.toArray(arrayOfDouble);
/* 326 */     localDoubleArrayList2.sort();
/* 327 */     Descriptive.frequencies(localDoubleArrayList2, localDoubleArrayList1, null);
/*     */     
/* 329 */     if (localDoubleArrayList1.size() > 0) localDoubleArrayList1.add(localDoubleArrayList1.get(localDoubleArrayList1.size() - 1) + d);
/* 330 */     localDoubleArrayList1.trimToSize();
/* 331 */     VariableAxis localVariableAxis1 = new VariableAxis(localDoubleArrayList1.elements());
/*     */     
/*     */ 
/* 334 */     paramDoubleMatrix1D2.toArray(arrayOfDouble);
/* 335 */     localDoubleArrayList2.sort();
/* 336 */     Descriptive.frequencies(localDoubleArrayList2, localDoubleArrayList1, null);
/*     */     
/* 338 */     if (localDoubleArrayList1.size() > 0) localDoubleArrayList1.add(localDoubleArrayList1.get(localDoubleArrayList1.size() - 1) + d);
/* 339 */     localDoubleArrayList1.trimToSize();
/* 340 */     VariableAxis localVariableAxis2 = new VariableAxis(localDoubleArrayList1.elements());
/*     */     
/* 342 */     Histogram2D localHistogram2D = new Histogram2D("Cube", localVariableAxis1, localVariableAxis2);
/* 343 */     return histogram(localHistogram2D, paramDoubleMatrix1D1, paramDoubleMatrix1D2, paramDoubleMatrix1D3);
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
/*     */   public static IHistogram3D cube(DoubleMatrix1D paramDoubleMatrix1D1, DoubleMatrix1D paramDoubleMatrix1D2, DoubleMatrix1D paramDoubleMatrix1D3, DoubleMatrix1D paramDoubleMatrix1D4)
/*     */   {
/* 356 */     if ((paramDoubleMatrix1D1.size() != paramDoubleMatrix1D2.size()) || (paramDoubleMatrix1D1.size() != paramDoubleMatrix1D3.size()) || (paramDoubleMatrix1D1.size() != paramDoubleMatrix1D4.size())) { throw new IllegalArgumentException("vectors must have same size");
/*     */     }
/* 358 */     double d = 1.0E-9D;
/* 359 */     DoubleArrayList localDoubleArrayList1 = new DoubleArrayList();
/* 360 */     double[] arrayOfDouble = new double[paramDoubleMatrix1D1.size()];
/* 361 */     DoubleArrayList localDoubleArrayList2 = new DoubleArrayList(arrayOfDouble);
/*     */     
/*     */ 
/* 364 */     paramDoubleMatrix1D1.toArray(arrayOfDouble);
/* 365 */     localDoubleArrayList2.sort();
/* 366 */     Descriptive.frequencies(localDoubleArrayList2, localDoubleArrayList1, null);
/*     */     
/* 368 */     if (localDoubleArrayList1.size() > 0) localDoubleArrayList1.add(localDoubleArrayList1.get(localDoubleArrayList1.size() - 1) + d);
/* 369 */     localDoubleArrayList1.trimToSize();
/* 370 */     VariableAxis localVariableAxis1 = new VariableAxis(localDoubleArrayList1.elements());
/*     */     
/*     */ 
/* 373 */     paramDoubleMatrix1D2.toArray(arrayOfDouble);
/* 374 */     localDoubleArrayList2.sort();
/* 375 */     Descriptive.frequencies(localDoubleArrayList2, localDoubleArrayList1, null);
/*     */     
/* 377 */     if (localDoubleArrayList1.size() > 0) localDoubleArrayList1.add(localDoubleArrayList1.get(localDoubleArrayList1.size() - 1) + d);
/* 378 */     localDoubleArrayList1.trimToSize();
/* 379 */     VariableAxis localVariableAxis2 = new VariableAxis(localDoubleArrayList1.elements());
/*     */     
/*     */ 
/* 382 */     paramDoubleMatrix1D3.toArray(arrayOfDouble);
/* 383 */     localDoubleArrayList2.sort();
/* 384 */     Descriptive.frequencies(localDoubleArrayList2, localDoubleArrayList1, null);
/*     */     
/* 386 */     if (localDoubleArrayList1.size() > 0) localDoubleArrayList1.add(localDoubleArrayList1.get(localDoubleArrayList1.size() - 1) + d);
/* 387 */     localDoubleArrayList1.trimToSize();
/* 388 */     VariableAxis localVariableAxis3 = new VariableAxis(localDoubleArrayList1.elements());
/*     */     
/* 390 */     Histogram3D localHistogram3D = new Histogram3D("Cube", localVariableAxis1, localVariableAxis2, localVariableAxis3);
/* 391 */     return histogram(localHistogram3D, paramDoubleMatrix1D1, paramDoubleMatrix1D2, paramDoubleMatrix1D3, paramDoubleMatrix1D4);
/*     */   }
/*     */   
/*     */ 
/*     */   public static void demo1()
/*     */   {
/* 397 */     double[][] arrayOfDouble = { { 1.0D, 2.0D, 3.0D }, { 2.0D, 4.0D, 6.0D }, { 3.0D, 6.0D, 9.0D }, { 4.0D, -8.0D, -10.0D } };
/*     */     
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/* 403 */     DoubleFactory2D localDoubleFactory2D = DoubleFactory2D.dense;
/* 404 */     DoubleMatrix2D localDoubleMatrix2D = localDoubleFactory2D.make(arrayOfDouble);
/* 405 */     System.out.println("\n\nmatrix=" + localDoubleMatrix2D);
/* 406 */     System.out.println("\ncovar1=" + covariance(localDoubleMatrix2D));
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
/*     */   public static void demo2(int paramInt1, int paramInt2, boolean paramBoolean)
/*     */   {
/* 420 */     System.out.println("\n\ninitializing...");
/* 421 */     DoubleFactory2D localDoubleFactory2D = DoubleFactory2D.dense;
/* 422 */     DoubleMatrix2D localDoubleMatrix2D1 = localDoubleFactory2D.ascending(paramInt1, paramInt2);
/*     */     
/*     */ 
/*     */ 
/*     */ 
/* 427 */     System.out.println("benchmarking correlation...");
/*     */     
/* 429 */     Timer localTimer = new Timer().start();
/* 430 */     DoubleMatrix2D localDoubleMatrix2D2 = correlation(covariance(localDoubleMatrix2D1));
/* 431 */     localTimer.stop().display();
/*     */     
/* 433 */     if (paramBoolean) {
/* 434 */       System.out.println("printing result...");
/* 435 */       System.out.println(localDoubleMatrix2D2);
/*     */     }
/* 437 */     System.out.println("done.");
/*     */   }
/*     */   
/*     */ 
/*     */   public static void demo3(VectorVectorFunction paramVectorVectorFunction)
/*     */   {
/* 443 */     double[][] arrayOfDouble = { { -0.9611052D, -0.25421095D }, { 0.4308269D, -0.69932648D }, { -1.2071029D, 0.62030596D }, { 1.5345166D, 0.02135884D }, { -1.1341542D, 0.2038843D } };
/*     */     
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/* 451 */     System.out.println("\n\ninitializing...");
/* 452 */     DoubleFactory2D localDoubleFactory2D = DoubleFactory2D.dense;
/* 453 */     DoubleMatrix2D localDoubleMatrix2D = localDoubleFactory2D.make(arrayOfDouble).viewDice();
/*     */     
/* 455 */     System.out.println("\nA=" + localDoubleMatrix2D.viewDice());
/* 456 */     System.out.println("\ndist=" + distance(localDoubleMatrix2D, paramVectorVectorFunction).viewDice());
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
/*     */   public static DoubleMatrix2D distance(DoubleMatrix2D paramDoubleMatrix2D, VectorVectorFunction paramVectorVectorFunction)
/*     */   {
/* 470 */     int i = paramDoubleMatrix2D.columns();
/* 471 */     DenseDoubleMatrix2D localDenseDoubleMatrix2D = new DenseDoubleMatrix2D(i, i);
/*     */     
/*     */ 
/* 474 */     DoubleMatrix1D[] arrayOfDoubleMatrix1D = new DoubleMatrix1D[i];
/* 475 */     int j = i;
/* 476 */     do { arrayOfDoubleMatrix1D[j] = paramDoubleMatrix2D.viewColumn(j);j--;
/* 475 */     } while (j >= 0);
/*     */     
/*     */ 
/*     */ 
/*     */ 
/* 480 */     int k = i;
/* 481 */     do { int m = k;
/* 482 */       do { double d = paramVectorVectorFunction.apply(arrayOfDoubleMatrix1D[k], arrayOfDoubleMatrix1D[m]);
/* 483 */         localDenseDoubleMatrix2D.setQuick(k, m, d);
/* 484 */         localDenseDoubleMatrix2D.setQuick(m, k, d);m--;
/* 481 */       } while (m >= 0);
/* 480 */       k--; } while (k >= 0);
/*     */     
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/* 487 */     return localDenseDoubleMatrix2D;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */   public static IHistogram1D histogram(IHistogram1D paramIHistogram1D, DoubleMatrix1D paramDoubleMatrix1D)
/*     */   {
/* 494 */     int i = paramDoubleMatrix1D.size();
/* 495 */     do { paramIHistogram1D.fill(paramDoubleMatrix1D.getQuick(i));i--;
/* 494 */     } while (i >= 0);
/*     */     
/*     */ 
/* 497 */     return paramIHistogram1D;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   public static IHistogram2D histogram(IHistogram2D paramIHistogram2D, DoubleMatrix1D paramDoubleMatrix1D1, DoubleMatrix1D paramDoubleMatrix1D2)
/*     */   {
/* 505 */     if (paramDoubleMatrix1D1.size() != paramDoubleMatrix1D2.size()) throw new IllegalArgumentException("vectors must have same size");
/* 506 */     int i = paramDoubleMatrix1D1.size();
/* 507 */     do { paramIHistogram2D.fill(paramDoubleMatrix1D1.getQuick(i), paramDoubleMatrix1D2.getQuick(i));i--;
/* 506 */     } while (i >= 0);
/*     */     
/*     */ 
/* 509 */     return paramIHistogram2D;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   public static IHistogram2D histogram(IHistogram2D paramIHistogram2D, DoubleMatrix1D paramDoubleMatrix1D1, DoubleMatrix1D paramDoubleMatrix1D2, DoubleMatrix1D paramDoubleMatrix1D3)
/*     */   {
/* 517 */     if ((paramDoubleMatrix1D1.size() != paramDoubleMatrix1D2.size()) || (paramDoubleMatrix1D2.size() != paramDoubleMatrix1D3.size())) throw new IllegalArgumentException("vectors must have same size");
/* 518 */     int i = paramDoubleMatrix1D1.size();
/* 519 */     do { paramIHistogram2D.fill(paramDoubleMatrix1D1.getQuick(i), paramDoubleMatrix1D2.getQuick(i), paramDoubleMatrix1D3.getQuick(i));i--;
/* 518 */     } while (i >= 0);
/*     */     
/*     */ 
/* 521 */     return paramIHistogram2D;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   public static IHistogram3D histogram(IHistogram3D paramIHistogram3D, DoubleMatrix1D paramDoubleMatrix1D1, DoubleMatrix1D paramDoubleMatrix1D2, DoubleMatrix1D paramDoubleMatrix1D3, DoubleMatrix1D paramDoubleMatrix1D4)
/*     */   {
/* 529 */     if ((paramDoubleMatrix1D1.size() != paramDoubleMatrix1D2.size()) || (paramDoubleMatrix1D1.size() != paramDoubleMatrix1D3.size()) || (paramDoubleMatrix1D1.size() != paramDoubleMatrix1D4.size())) throw new IllegalArgumentException("vectors must have same size");
/* 530 */     int i = paramDoubleMatrix1D1.size();
/* 531 */     do { paramIHistogram3D.fill(paramDoubleMatrix1D1.getQuick(i), paramDoubleMatrix1D2.getQuick(i), paramDoubleMatrix1D3.getQuick(i), paramDoubleMatrix1D4.getQuick(i));i--;
/* 530 */     } while (i >= 0);
/*     */     
/*     */ 
/* 533 */     return paramIHistogram3D;
/*     */   }
/*     */   
/*     */ 
/*     */   public static void main(String[] paramArrayOfString)
/*     */   {
/* 539 */     int i = Integer.parseInt(paramArrayOfString[0]);
/* 540 */     int j = Integer.parseInt(paramArrayOfString[1]);
/* 541 */     boolean bool = paramArrayOfString[2].equals("print");
/* 542 */     demo2(i, j, bool);
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
/*     */   public static DoubleMatrix1D viewSample(DoubleMatrix1D paramDoubleMatrix1D, double paramDouble, RandomElement paramRandomElement)
/*     */   {
/* 557 */     double d = 1.0E-9D;
/* 558 */     if ((paramDouble < 0.0D - d) || (paramDouble > 1.0D + d)) throw new IllegalArgumentException();
/* 559 */     if (paramDouble < 0.0D) paramDouble = 0.0D;
/* 560 */     if (paramDouble > 1.0D) { paramDouble = 1.0D;
/*     */     }
/*     */     
/* 563 */     if (paramRandomElement == null) { paramRandomElement = new MersenneTwister((int)System.currentTimeMillis());
/*     */     }
/* 565 */     int i = (int)Math.round(paramDoubleMatrix1D.size() * paramDouble);
/* 566 */     int j = i;
/* 567 */     long[] arrayOfLong = new long[j];
/*     */     
/*     */ 
/* 570 */     int k = i;
/* 571 */     int m = paramDoubleMatrix1D.size();
/* 572 */     RandomSampler.sample(k, m, k, 0L, arrayOfLong, 0, paramRandomElement);
/* 573 */     int[] arrayOfInt = new int[k];
/* 574 */     for (int n = 0; n < k; n++) { arrayOfInt[n] = ((int)arrayOfLong[n]);
/*     */     }
/* 576 */     return paramDoubleMatrix1D.viewSelection(arrayOfInt);
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
/*     */   public static DoubleMatrix2D viewSample(DoubleMatrix2D paramDoubleMatrix2D, double paramDouble1, double paramDouble2, RandomElement paramRandomElement)
/*     */   {
/* 644 */     double d = 1.0E-9D;
/* 645 */     if ((paramDouble1 < 0.0D - d) || (paramDouble1 > 1.0D + d)) throw new IllegalArgumentException();
/* 646 */     if (paramDouble1 < 0.0D) paramDouble1 = 0.0D;
/* 647 */     if (paramDouble1 > 1.0D) { paramDouble1 = 1.0D;
/*     */     }
/* 649 */     if ((paramDouble2 < 0.0D - d) || (paramDouble2 > 1.0D + d)) throw new IllegalArgumentException();
/* 650 */     if (paramDouble2 < 0.0D) paramDouble2 = 0.0D;
/* 651 */     if (paramDouble2 > 1.0D) { paramDouble2 = 1.0D;
/*     */     }
/*     */     
/* 654 */     if (paramRandomElement == null) { paramRandomElement = new MersenneTwister((int)System.currentTimeMillis());
/*     */     }
/* 656 */     int i = (int)Math.round(paramDoubleMatrix2D.rows() * paramDouble1);
/* 657 */     int j = (int)Math.round(paramDoubleMatrix2D.columns() * paramDouble2);
/* 658 */     int k = Math.max(i, j);
/* 659 */     long[] arrayOfLong = new long[k];
/*     */     
/*     */ 
/* 662 */     int m = i;
/* 663 */     int n = paramDoubleMatrix2D.rows();
/* 664 */     RandomSampler.sample(m, n, m, 0L, arrayOfLong, 0, paramRandomElement);
/* 665 */     int[] arrayOfInt1 = new int[m];
/* 666 */     for (int i1 = 0; i1 < m; i1++) { arrayOfInt1[i1] = ((int)arrayOfLong[i1]);
/*     */     }
/*     */     
/* 669 */     m = j;
/* 670 */     n = paramDoubleMatrix2D.columns();
/* 671 */     RandomSampler.sample(m, n, m, 0L, arrayOfLong, 0, paramRandomElement);
/* 672 */     int[] arrayOfInt2 = new int[m];
/* 673 */     for (int i2 = 0; i2 < m; i2++) { arrayOfInt2[i2] = ((int)arrayOfLong[i2]);
/*     */     }
/* 675 */     return paramDoubleMatrix2D.viewSelection(arrayOfInt1, arrayOfInt2);
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
/*     */   public static DoubleMatrix3D viewSample(DoubleMatrix3D paramDoubleMatrix3D, double paramDouble1, double paramDouble2, double paramDouble3, RandomElement paramRandomElement)
/*     */   {
/* 692 */     double d = 1.0E-9D;
/* 693 */     if ((paramDouble1 < 0.0D - d) || (paramDouble1 > 1.0D + d)) throw new IllegalArgumentException();
/* 694 */     if (paramDouble1 < 0.0D) paramDouble1 = 0.0D;
/* 695 */     if (paramDouble1 > 1.0D) { paramDouble1 = 1.0D;
/*     */     }
/* 697 */     if ((paramDouble2 < 0.0D - d) || (paramDouble2 > 1.0D + d)) throw new IllegalArgumentException();
/* 698 */     if (paramDouble2 < 0.0D) paramDouble2 = 0.0D;
/* 699 */     if (paramDouble2 > 1.0D) { paramDouble2 = 1.0D;
/*     */     }
/* 701 */     if ((paramDouble3 < 0.0D - d) || (paramDouble3 > 1.0D + d)) throw new IllegalArgumentException();
/* 702 */     if (paramDouble3 < 0.0D) paramDouble3 = 0.0D;
/* 703 */     if (paramDouble3 > 1.0D) { paramDouble3 = 1.0D;
/*     */     }
/*     */     
/* 706 */     if (paramRandomElement == null) { paramRandomElement = new MersenneTwister((int)System.currentTimeMillis());
/*     */     }
/* 708 */     int i = (int)Math.round(paramDoubleMatrix3D.slices() * paramDouble1);
/* 709 */     int j = (int)Math.round(paramDoubleMatrix3D.rows() * paramDouble2);
/* 710 */     int k = (int)Math.round(paramDoubleMatrix3D.columns() * paramDouble3);
/* 711 */     int m = Math.max(i, Math.max(j, k));
/* 712 */     long[] arrayOfLong = new long[m];
/*     */     
/*     */ 
/* 715 */     int n = i;
/* 716 */     int i1 = paramDoubleMatrix3D.slices();
/* 717 */     RandomSampler.sample(n, i1, n, 0L, arrayOfLong, 0, paramRandomElement);
/* 718 */     int[] arrayOfInt1 = new int[n];
/* 719 */     for (int i2 = 0; i2 < n; i2++) { arrayOfInt1[i2] = ((int)arrayOfLong[i2]);
/*     */     }
/*     */     
/* 722 */     n = j;
/* 723 */     i1 = paramDoubleMatrix3D.rows();
/* 724 */     RandomSampler.sample(n, i1, n, 0L, arrayOfLong, 0, paramRandomElement);
/* 725 */     int[] arrayOfInt2 = new int[n];
/* 726 */     for (int i3 = 0; i3 < n; i3++) { arrayOfInt2[i3] = ((int)arrayOfLong[i3]);
/*     */     }
/*     */     
/* 729 */     n = k;
/* 730 */     i1 = paramDoubleMatrix3D.columns();
/* 731 */     RandomSampler.sample(n, i1, n, 0L, arrayOfLong, 0, paramRandomElement);
/* 732 */     int[] arrayOfInt3 = new int[n];
/* 733 */     for (int i4 = 0; i4 < n; i4++) { arrayOfInt3[i4] = ((int)arrayOfLong[i4]);
/*     */     }
/* 735 */     return paramDoubleMatrix3D.viewSelection(arrayOfInt1, arrayOfInt2, arrayOfInt3);
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
/*     */   private static DoubleMatrix2D xdistanceOld(DoubleMatrix2D paramDoubleMatrix2D, int paramInt)
/*     */   {
/* 788 */     return null;
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
/*     */   private static DoubleMatrix2D xdistanceOld2(DoubleMatrix2D paramDoubleMatrix2D, int paramInt)
/*     */   {
/* 840 */     return null;
/*     */   }
/*     */   
/*     */   public static abstract interface VectorVectorFunction
/*     */   {
/*     */     public abstract double apply(DoubleMatrix1D paramDoubleMatrix1D1, DoubleMatrix1D paramDoubleMatrix1D2);
/*     */   }
/*     */ }


/* Location:              /home/menick/Scrivania/ProcessMining/CNMining/CNMining.jar!/cern/colt/matrix/doublealgo/Statistic.class
 * Java compiler version: 1 (45.3)
 * JD-Core Version:       0.7.1
 */