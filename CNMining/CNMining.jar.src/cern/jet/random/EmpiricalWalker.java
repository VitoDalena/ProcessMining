/*     */ package cern.jet.random;
/*     */ 
/*     */ import edu.cornell.lassp.houle.RngPack.RandomElement;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class EmpiricalWalker
/*     */   extends AbstractDiscreteDistribution
/*     */ {
/*     */   protected int K;
/*     */   protected int[] A;
/*     */   protected double[] F;
/*     */   protected double[] cdf;
/*     */   
/*     */   public EmpiricalWalker(double[] paramArrayOfDouble, int paramInt, RandomElement paramRandomElement)
/*     */   {
/* 168 */     setRandomGenerator(paramRandomElement);
/* 169 */     setState(paramArrayOfDouble, paramInt);
/* 170 */     setState2(paramArrayOfDouble);
/*     */   }
/*     */   
/*     */ 
/*     */   public double cdf(int paramInt)
/*     */   {
/* 176 */     if (paramInt < 0) return 0.0D;
/* 177 */     if (paramInt >= this.cdf.length - 1) return 1.0D;
/* 178 */     return this.cdf[paramInt];
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public Object clone()
/*     */   {
/* 187 */     EmpiricalWalker localEmpiricalWalker = (EmpiricalWalker)super.clone();
/* 188 */     if (this.cdf != null) localEmpiricalWalker.cdf = ((double[])this.cdf.clone());
/* 189 */     if (this.A != null) localEmpiricalWalker.A = ((int[])this.A.clone());
/* 190 */     if (this.F != null) localEmpiricalWalker.F = ((double[])this.F.clone());
/* 191 */     return localEmpiricalWalker;
/*     */   }
/*     */   
/*     */ 
/*     */   public int nextInt()
/*     */   {
/* 197 */     int i = 0;
/*     */     
/* 199 */     double d1 = this.randomGenerator.raw();
/*     */     
/*     */ 
/*     */ 
/* 203 */     d1 *= this.K;
/* 204 */     i = (int)d1;
/* 205 */     d1 -= i;
/*     */     
/* 207 */     double d2 = this.F[i];
/*     */     
/* 209 */     if (d2 == 1.0D) return i;
/* 210 */     if (d1 < d2) {
/* 211 */       return i;
/*     */     }
/*     */     
/* 214 */     return this.A[i];
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */   public double pdf(int paramInt)
/*     */   {
/* 221 */     if ((paramInt < 0) || (paramInt >= this.cdf.length - 1)) return 0.0D;
/* 222 */     return this.cdf[(paramInt - 1)] - this.cdf[paramInt];
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
/*     */   public void setState(double[] paramArrayOfDouble, int paramInt)
/*     */   {
/* 236 */     if ((paramArrayOfDouble == null) || (paramArrayOfDouble.length == 0)) {
/* 237 */       throw new IllegalArgumentException("Non-existing pdf");
/*     */     }
/*     */     
/*     */ 
/* 241 */     int i = paramArrayOfDouble.length;
/* 242 */     this.cdf = new double[i + 1];
/*     */     
/* 244 */     this.cdf[0] = 0.0D;
/* 245 */     for (int j = 0; j < i; j++) {
/* 246 */       if (paramArrayOfDouble[j] < 0.0D) throw new IllegalArgumentException("Negative probability");
/* 247 */       this.cdf[(j + 1)] = (this.cdf[j] + paramArrayOfDouble[j]);
/*     */     }
/* 249 */     if (this.cdf[i] <= 0.0D) { throw new IllegalArgumentException("At leat one probability must be > 0.0");
/*     */     }
/* 251 */     for (int k = 0; k < i + 1; k++) {
/* 252 */       this.cdf[k] /= this.cdf[i];
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
/*     */   public void setState2(double[] paramArrayOfDouble)
/*     */   {
/* 267 */     int i = paramArrayOfDouble.length;
/*     */     
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/* 273 */     double d1 = 0.0D;
/*     */     
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/* 282 */     for (int j = 0; j < i; j++)
/*     */     {
/*     */ 
/*     */ 
/* 286 */       d1 += paramArrayOfDouble[j];
/*     */     }
/*     */     
/*     */ 
/* 290 */     this.K = i;
/* 291 */     this.F = new double[i];
/* 292 */     this.A = new int[i];
/*     */     
/*     */ 
/* 295 */     double[] arrayOfDouble = new double[i];
/* 296 */     for (j = 0; j < i; j++) {
/* 297 */       paramArrayOfDouble[j] /= d1;
/*     */     }
/*     */     
/*     */ 
/* 301 */     double d2 = 1.0D / i;
/* 302 */     int i1 = 0;
/* 303 */     int n = 0;
/* 304 */     for (j = 0; j < i; j++) {
/* 305 */       if (arrayOfDouble[j] < d2) i1++; else
/* 306 */         n++;
/*     */     }
/* 308 */     Stack localStack1 = new Stack(n);
/* 309 */     Stack localStack2 = new Stack(i1);
/* 310 */     for (j = 0; j < i; j++) {
/* 311 */       if (arrayOfDouble[j] < d2) {
/* 312 */         localStack2.push(j);
/*     */       }
/*     */       else {
/* 315 */         localStack1.push(j);
/*     */       }
/*     */     }
/*     */     int m;
/* 319 */     while (localStack2.size() > 0) {
/* 320 */       int k = localStack2.pop();
/* 321 */       if (localStack1.size() == 0)
/*     */       {
/* 323 */         this.A[k] = k;
/* 324 */         this.F[k] = 1.0D;
/* 325 */         break;
/*     */       }
/* 327 */       m = localStack1.pop();
/* 328 */       this.A[k] = m;
/* 329 */       this.F[k] = (i * arrayOfDouble[k]);
/*     */       
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/* 335 */       double d3 = d2 - arrayOfDouble[k];
/* 336 */       arrayOfDouble[k] += d3;
/* 337 */       arrayOfDouble[m] -= d3;
/* 338 */       if (arrayOfDouble[m] < d2) {
/* 339 */         localStack2.push(m);
/*     */       }
/* 341 */       else if (arrayOfDouble[m] > d2) {
/* 342 */         localStack1.push(m);
/*     */       }
/*     */       else
/*     */       {
/* 346 */         this.A[m] = m;
/* 347 */         this.F[m] = 1.0D;
/*     */       }
/*     */     }
/* 350 */     while (localStack1.size() > 0) {
/* 351 */       m = localStack1.pop();
/* 352 */       this.A[m] = m;
/* 353 */       this.F[m] = 1.0D;
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
/*     */   public String toString()
/*     */   {
/* 396 */     Object localObject = null;
/* 397 */     return getClass().getName() + "(" + (this.cdf != null ? this.cdf.length : 0) + ")";
/*     */   }
/*     */ }


/* Location:              /home/menick/Scrivania/ProcessMining/CNMining/CNMining.jar!/cern/jet/random/EmpiricalWalker.class
 * Java compiler version: 1 (45.3)
 * JD-Core Version:       0.7.1
 */