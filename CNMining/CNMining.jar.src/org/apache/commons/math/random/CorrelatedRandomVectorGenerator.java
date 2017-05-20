/*     */ package org.apache.commons.math.random;
/*     */ 
/*     */ import org.apache.commons.math.DimensionMismatchException;
/*     */ import org.apache.commons.math.linear.RealMatrix;
/*     */ import org.apache.commons.math.linear.RealMatrixImpl;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class CorrelatedRandomVectorGenerator
/*     */   implements RandomVectorGenerator
/*     */ {
/*     */   private double[] mean;
/*     */   private RealMatrixImpl root;
/*     */   private int rank;
/*     */   private NormalizedRandomGenerator generator;
/*     */   private double[] normalized;
/*     */   
/*     */   public CorrelatedRandomVectorGenerator(double[] mean, RealMatrix covariance, double small, NormalizedRandomGenerator generator)
/*     */     throws NotPositiveDefiniteMatrixException, DimensionMismatchException
/*     */   {
/*  85 */     int order = covariance.getRowDimension();
/*  86 */     if (mean.length != order) {
/*  87 */       throw new DimensionMismatchException(mean.length, order);
/*     */     }
/*  89 */     this.mean = ((double[])mean.clone());
/*     */     
/*  91 */     decompose(covariance, small);
/*     */     
/*  93 */     this.generator = generator;
/*  94 */     this.normalized = new double[this.rank];
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
/*     */   public CorrelatedRandomVectorGenerator(RealMatrix covariance, double small, NormalizedRandomGenerator generator)
/*     */     throws NotPositiveDefiniteMatrixException
/*     */   {
/* 113 */     int order = covariance.getRowDimension();
/* 114 */     this.mean = new double[order];
/* 115 */     for (int i = 0; i < order; i++) {
/* 116 */       this.mean[i] = 0.0D;
/*     */     }
/*     */     
/* 119 */     decompose(covariance, small);
/*     */     
/* 121 */     this.generator = generator;
/* 122 */     this.normalized = new double[this.rank];
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   public NormalizedRandomGenerator getGenerator()
/*     */   {
/* 130 */     return this.generator;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public RealMatrix getRootMatrix()
/*     */   {
/* 140 */     return this.root;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public int getRank()
/*     */   {
/* 151 */     return this.rank;
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
/*     */   private void decompose(RealMatrix covariance, double small)
/*     */     throws NotPositiveDefiniteMatrixException
/*     */   {
/* 174 */     int order = covariance.getRowDimension();
/* 175 */     double[][] c = covariance.getData();
/* 176 */     double[][] b = new double[order][order];
/*     */     
/* 178 */     int[] swap = new int[order];
/* 179 */     int[] index = new int[order];
/* 180 */     for (int i = 0; i < order; i++) {
/* 181 */       index[i] = i;
/*     */     }
/*     */     
/* 184 */     this.rank = 0;
/* 185 */     for (boolean loop = true; loop;)
/*     */     {
/*     */ 
/* 188 */       swap[this.rank] = this.rank;
/* 189 */       for (int i = this.rank + 1; i < order; i++) {
/* 190 */         int ii = index[i];
/* 191 */         int isi = index[swap[i]];
/* 192 */         if (c[ii][ii] > c[isi][isi]) {
/* 193 */           swap[this.rank] = i;
/*     */         }
/*     */       }
/*     */       
/*     */ 
/*     */ 
/* 199 */       if (swap[this.rank] != this.rank) {
/* 200 */         int tmp = index[this.rank];
/* 201 */         index[this.rank] = index[swap[this.rank]];
/* 202 */         index[swap[this.rank]] = tmp;
/*     */       }
/*     */       
/*     */ 
/* 206 */       int ir = index[this.rank];
/* 207 */       if (c[ir][ir] < small)
/*     */       {
/* 209 */         if (this.rank == 0) {
/* 210 */           throw new NotPositiveDefiniteMatrixException();
/*     */         }
/*     */         
/*     */ 
/* 214 */         for (int i = this.rank; i < order; i++) {
/* 215 */           if (c[index[i]][index[i]] < -small)
/*     */           {
/*     */ 
/* 218 */             throw new NotPositiveDefiniteMatrixException();
/*     */           }
/*     */         }
/*     */         
/*     */ 
/*     */ 
/* 224 */         this.rank += 1;
/* 225 */         loop = false;
/*     */ 
/*     */       }
/*     */       else
/*     */       {
/* 230 */         double sqrt = Math.sqrt(c[ir][ir]);
/* 231 */         b[this.rank][this.rank] = sqrt;
/* 232 */         double inverse = 1.0D / sqrt;
/* 233 */         for (int i = this.rank + 1; i < order; i++) {
/* 234 */           int ii = index[i];
/* 235 */           double e = inverse * c[ii][ir];
/* 236 */           b[i][this.rank] = e;
/* 237 */           c[ii][ii] -= e * e;
/* 238 */           for (int j = this.rank + 1; j < i; j++) {
/* 239 */             int ij = index[j];
/* 240 */             double f = c[ii][ij] - e * b[j][this.rank];
/* 241 */             c[ii][ij] = f;
/* 242 */             c[ij][ii] = f;
/*     */           }
/*     */         }
/*     */         
/*     */ 
/* 247 */         loop = ++this.rank < order;
/*     */       }
/*     */     }
/*     */     
/*     */ 
/*     */ 
/*     */ 
/* 254 */     this.root = new RealMatrixImpl(order, this.rank);
/* 255 */     for (int i = 0; i < order; i++) {
/* 256 */       System.arraycopy(b[i], 0, this.root.getDataRef()[swap[i]], 0, this.rank);
/*     */     }
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public double[] nextVector()
/*     */   {
/* 268 */     for (int i = 0; i < this.rank; i++) {
/* 269 */       this.normalized[i] = this.generator.nextNormalizedDouble();
/*     */     }
/*     */     
/*     */ 
/* 273 */     double[] correlated = new double[this.mean.length];
/* 274 */     for (int i = 0; i < correlated.length; i++) {
/* 275 */       correlated[i] = this.mean[i];
/* 276 */       for (int j = 0; j < this.rank; j++) {
/* 277 */         correlated[i] += this.root.getEntry(i, j) * this.normalized[j];
/*     */       }
/*     */     }
/*     */     
/* 281 */     return correlated;
/*     */   }
/*     */ }


/* Location:              /home/menick/Scrivania/ProcessMining/CNMining/CNMining.jar!/org/apache/commons/math/random/CorrelatedRandomVectorGenerator.class
 * Java compiler version: 3 (47.0)
 * JD-Core Version:       0.7.1
 */