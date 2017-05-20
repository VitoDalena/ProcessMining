/*     */ package cern.colt.matrix;
/*     */ 
/*     */ import cern.colt.PersistentObject;
/*     */ import cern.colt.list.AbstractDoubleList;
/*     */ import cern.colt.list.AbstractList;
/*     */ import cern.colt.list.DoubleArrayList;
/*     */ import cern.colt.matrix.impl.AbstractMatrix1D;
/*     */ import cern.colt.matrix.impl.DenseDoubleMatrix1D;
/*     */ import cern.colt.matrix.impl.SparseDoubleMatrix1D;
/*     */ import cern.jet.math.Functions;
/*     */ import cern.jet.random.engine.MersenneTwister;
/*     */ import cern.jet.random.sampling.RandomSamplingAssistant;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class DoubleFactory1D
/*     */   extends PersistentObject
/*     */ {
/*  38 */   public static final DoubleFactory1D dense = new DoubleFactory1D();
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*  43 */   public static final DoubleFactory1D sparse = new DoubleFactory1D();
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public DoubleMatrix1D append(DoubleMatrix1D paramDoubleMatrix1D1, DoubleMatrix1D paramDoubleMatrix1D2)
/*     */   {
/*  54 */     DoubleMatrix1D localDoubleMatrix1D = make(paramDoubleMatrix1D1.size() + paramDoubleMatrix1D2.size());
/*  55 */     localDoubleMatrix1D.viewPart(0, paramDoubleMatrix1D1.size()).assign(paramDoubleMatrix1D1);
/*  56 */     localDoubleMatrix1D.viewPart(paramDoubleMatrix1D1.size(), paramDoubleMatrix1D2.size()).assign(paramDoubleMatrix1D2);
/*  57 */     return localDoubleMatrix1D;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   public DoubleMatrix1D ascending(int paramInt)
/*     */   {
/*  65 */     Functions localFunctions = Functions.functions;
/*  66 */     return descending(paramInt).assign(Functions.chain(Functions.neg, Functions.minus(paramInt)));
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   public DoubleMatrix1D descending(int paramInt)
/*     */   {
/*  74 */     DoubleMatrix1D localDoubleMatrix1D = make(paramInt);
/*  75 */     int i = 0;
/*  76 */     int j = paramInt;
/*  77 */     do { localDoubleMatrix1D.setQuick(j, i++);j--;
/*  76 */     } while (j >= 0);
/*     */     
/*     */ 
/*  79 */     return localDoubleMatrix1D;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public DoubleMatrix1D make(double[] paramArrayOfDouble)
/*     */   {
/*  88 */     if (this == sparse) return new SparseDoubleMatrix1D(paramArrayOfDouble);
/*  89 */     return new DenseDoubleMatrix1D(paramArrayOfDouble);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */   public DoubleMatrix1D make(DoubleMatrix1D[] paramArrayOfDoubleMatrix1D)
/*     */   {
/*  96 */     if (paramArrayOfDoubleMatrix1D.length == 0) { return make(0);
/*     */     }
/*  98 */     int i = 0;
/*  99 */     for (int j = 0; j < paramArrayOfDoubleMatrix1D.length; j++) { i += paramArrayOfDoubleMatrix1D[j].size();
/*     */     }
/* 101 */     DoubleMatrix1D localDoubleMatrix1D = make(i);
/* 102 */     i = 0;
/* 103 */     for (int k = 0; k < paramArrayOfDoubleMatrix1D.length; k++) {
/* 104 */       localDoubleMatrix1D.viewPart(i, paramArrayOfDoubleMatrix1D[k].size()).assign(paramArrayOfDoubleMatrix1D[k]);
/* 105 */       i += paramArrayOfDoubleMatrix1D[k].size();
/*     */     }
/*     */     
/* 108 */     return localDoubleMatrix1D;
/*     */   }
/*     */   
/*     */ 
/*     */   public DoubleMatrix1D make(int paramInt)
/*     */   {
/* 114 */     if (this == sparse) return new SparseDoubleMatrix1D(paramInt);
/* 115 */     return new DenseDoubleMatrix1D(paramInt);
/*     */   }
/*     */   
/*     */ 
/*     */   public DoubleMatrix1D make(int paramInt, double paramDouble)
/*     */   {
/* 121 */     return make(paramInt).assign(paramDouble);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public DoubleMatrix1D make(AbstractDoubleList paramAbstractDoubleList)
/*     */   {
/* 131 */     int i = paramAbstractDoubleList.size();
/* 132 */     DoubleMatrix1D localDoubleMatrix1D = make(i);
/* 133 */     int j = i; do { localDoubleMatrix1D.set(j, paramAbstractDoubleList.get(j));j--; } while (j >= 0);
/* 134 */     return localDoubleMatrix1D;
/*     */   }
/*     */   
/*     */ 
/*     */   public DoubleMatrix1D random(int paramInt)
/*     */   {
/* 140 */     return make(paramInt).assign(Functions.random());
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public DoubleMatrix1D repeat(DoubleMatrix1D paramDoubleMatrix1D, int paramInt)
/*     */   {
/* 152 */     int i = paramDoubleMatrix1D.size();
/* 153 */     DoubleMatrix1D localDoubleMatrix1D = make(paramInt * i);
/* 154 */     int j = paramInt;
/* 155 */     do { localDoubleMatrix1D.viewPart(i * j, i).assign(paramDoubleMatrix1D);j--;
/* 154 */     } while (j >= 0);
/*     */     
/*     */ 
/* 157 */     return localDoubleMatrix1D;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public DoubleMatrix1D sample(int paramInt, double paramDouble1, double paramDouble2)
/*     */   {
/* 167 */     double d = 1.0E-9D;
/* 168 */     if ((paramDouble2 < 0.0D - d) || (paramDouble2 > 1.0D + d)) throw new IllegalArgumentException();
/* 169 */     if (paramDouble2 < 0.0D) paramDouble2 = 0.0D;
/* 170 */     if (paramDouble2 > 1.0D) { paramDouble2 = 1.0D;
/*     */     }
/* 172 */     DoubleMatrix1D localDoubleMatrix1D = make(paramInt);
/*     */     
/* 174 */     int i = (int)Math.round(paramInt * paramDouble2);
/* 175 */     if (i == 0) { return localDoubleMatrix1D;
/*     */     }
/* 177 */     RandomSamplingAssistant localRandomSamplingAssistant = new RandomSamplingAssistant(i, paramInt, new MersenneTwister());
/* 178 */     int j = paramInt;
/* 179 */     do { if (localRandomSamplingAssistant.sampleNextElement()) {
/* 180 */         localDoubleMatrix1D.set(j, paramDouble1);
/*     */       }
/* 178 */       j--; } while (j >= 0);
/*     */     
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/* 184 */     return localDoubleMatrix1D;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public DoubleArrayList toList(DoubleMatrix1D paramDoubleMatrix1D)
/*     */   {
/* 194 */     int i = paramDoubleMatrix1D.size();
/* 195 */     DoubleArrayList localDoubleArrayList = new DoubleArrayList(i);
/* 196 */     localDoubleArrayList.setSize(i);
/* 197 */     int j = i; do { localDoubleArrayList.set(j, paramDoubleMatrix1D.get(j));j--; } while (j >= 0);
/* 198 */     return localDoubleArrayList;
/*     */   }
/*     */ }


/* Location:              /home/menick/Scrivania/ProcessMining/CNMining/CNMining.jar!/cern/colt/matrix/DoubleFactory1D.class
 * Java compiler version: 1 (45.3)
 * JD-Core Version:       0.7.1
 */