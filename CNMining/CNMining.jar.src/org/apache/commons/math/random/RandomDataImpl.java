/*     */ package org.apache.commons.math.random;
/*     */ 
/*     */ import java.io.Serializable;
/*     */ import java.security.MessageDigest;
/*     */ import java.security.NoSuchAlgorithmException;
/*     */ import java.security.NoSuchProviderException;
/*     */ import java.security.SecureRandom;
/*     */ import java.util.Collection;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class RandomDataImpl
/*     */   implements RandomData, Serializable
/*     */ {
/*     */   private static final long serialVersionUID = -626730818244969716L;
/*  88 */   private RandomGenerator rand = null;
/*     */   
/*     */ 
/*  91 */   private SecureRandom secRand = null;
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public RandomDataImpl() {}
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public RandomDataImpl(RandomGenerator rand)
/*     */   {
/* 108 */     this.rand = rand;
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
/*     */   public String nextHexString(int len)
/*     */   {
/* 124 */     if (len <= 0) {
/* 125 */       throw new IllegalArgumentException("length must be positive");
/*     */     }
/*     */     
/*     */ 
/* 129 */     RandomGenerator ran = getRan();
/*     */     
/*     */ 
/* 132 */     StringBuffer outBuffer = new StringBuffer();
/*     */     
/*     */ 
/* 135 */     byte[] randomBytes = new byte[len / 2 + 1];
/* 136 */     ran.nextBytes(randomBytes);
/*     */     
/*     */ 
/* 139 */     for (int i = 0; i < randomBytes.length; i++) {
/* 140 */       Integer c = new Integer(randomBytes[i]);
/*     */       
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/* 147 */       String hex = Integer.toHexString(c.intValue() + 128);
/*     */       
/*     */ 
/* 150 */       if (hex.length() == 1) {
/* 151 */         hex = "0" + hex;
/*     */       }
/* 153 */       outBuffer.append(hex);
/*     */     }
/* 155 */     return outBuffer.toString().substring(0, len);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public int nextInt(int lower, int upper)
/*     */   {
/* 167 */     if (lower >= upper) {
/* 168 */       throw new IllegalArgumentException("upper bound must be > lower bound");
/*     */     }
/*     */     
/* 171 */     RandomGenerator rand = getRan();
/* 172 */     double r = rand.nextDouble();
/* 173 */     return (int)(r * upper + (1.0D - r) * lower + r);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public long nextLong(long lower, long upper)
/*     */   {
/* 185 */     if (lower >= upper) {
/* 186 */       throw new IllegalArgumentException("upper bound must be > lower bound");
/*     */     }
/*     */     
/* 189 */     RandomGenerator rand = getRan();
/* 190 */     double r = rand.nextDouble();
/* 191 */     return (r * upper + (1.0D - r) * lower + r);
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
/*     */   public String nextSecureHexString(int len)
/*     */   {
/* 211 */     if (len <= 0) {
/* 212 */       throw new IllegalArgumentException("length must be positive");
/*     */     }
/*     */     
/*     */ 
/* 216 */     SecureRandom secRan = getSecRan();
/* 217 */     MessageDigest alg = null;
/*     */     try {
/* 219 */       alg = MessageDigest.getInstance("SHA-1");
/*     */     } catch (NoSuchAlgorithmException ex) {
/* 221 */       return null;
/*     */     }
/* 223 */     alg.reset();
/*     */     
/*     */ 
/* 226 */     int numIter = len / 40 + 1;
/*     */     
/* 228 */     StringBuffer outBuffer = new StringBuffer();
/* 229 */     for (int iter = 1; iter < numIter + 1; iter++) {
/* 230 */       byte[] randomBytes = new byte[40];
/* 231 */       secRan.nextBytes(randomBytes);
/* 232 */       alg.update(randomBytes);
/*     */       
/*     */ 
/* 235 */       byte[] hash = alg.digest();
/*     */       
/*     */ 
/* 238 */       for (int i = 0; i < hash.length; i++) {
/* 239 */         Integer c = new Integer(hash[i]);
/*     */         
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/* 246 */         String hex = Integer.toHexString(c.intValue() + 128);
/*     */         
/*     */ 
/* 249 */         if (hex.length() == 1) {
/* 250 */           hex = "0" + hex;
/*     */         }
/* 252 */         outBuffer.append(hex);
/*     */       }
/*     */     }
/* 255 */     return outBuffer.toString().substring(0, len);
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
/*     */   public int nextSecureInt(int lower, int upper)
/*     */   {
/* 268 */     if (lower >= upper) {
/* 269 */       throw new IllegalArgumentException("lower bound must be < upper bound");
/*     */     }
/*     */     
/* 272 */     SecureRandom sec = getSecRan();
/* 273 */     return lower + (int)(sec.nextDouble() * (upper - lower + 1));
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
/*     */   public long nextSecureLong(long lower, long upper)
/*     */   {
/* 286 */     if (lower >= upper) {
/* 287 */       throw new IllegalArgumentException("lower bound must be < upper bound");
/*     */     }
/*     */     
/* 290 */     SecureRandom sec = getSecRan();
/* 291 */     return lower + (sec.nextDouble() * (upper - lower + 1L));
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
/*     */   public long nextPoisson(double mean)
/*     */   {
/* 310 */     if (mean <= 0.0D) {
/* 311 */       throw new IllegalArgumentException("Poisson mean must be > 0");
/*     */     }
/* 313 */     double p = Math.exp(-mean);
/* 314 */     long n = 0L;
/* 315 */     double r = 1.0D;
/* 316 */     double rnd = 1.0D;
/* 317 */     RandomGenerator rand = getRan();
/* 318 */     while (n < 1000.0D * mean) {
/* 319 */       rnd = rand.nextDouble();
/* 320 */       r *= rnd;
/* 321 */       if (r >= p) {
/* 322 */         n += 1L;
/*     */       } else {
/* 324 */         return n;
/*     */       }
/*     */     }
/* 327 */     return n;
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
/*     */   public double nextGaussian(double mu, double sigma)
/*     */   {
/* 340 */     if (sigma <= 0.0D) {
/* 341 */       throw new IllegalArgumentException("Gaussian std dev must be > 0");
/*     */     }
/* 343 */     RandomGenerator rand = getRan();
/* 344 */     return sigma * rand.nextGaussian() + mu;
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
/*     */   public double nextExponential(double mean)
/*     */   {
/* 360 */     if (mean < 0.0D) {
/* 361 */       throw new IllegalArgumentException("Exponential mean must be >= 0");
/*     */     }
/*     */     
/* 364 */     RandomGenerator rand = getRan();
/* 365 */     double unif = rand.nextDouble();
/* 366 */     while (unif == 0.0D) {
/* 367 */       unif = rand.nextDouble();
/*     */     }
/* 369 */     return -mean * Math.log(unif);
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
/*     */   public double nextUniform(double lower, double upper)
/*     */   {
/* 385 */     if (lower >= upper) {
/* 386 */       throw new IllegalArgumentException("lower bound must be < upper bound");
/*     */     }
/*     */     
/* 389 */     RandomGenerator rand = getRan();
/*     */     
/*     */ 
/* 392 */     double u = rand.nextDouble();
/* 393 */     while (u <= 0.0D) {
/* 394 */       u = rand.nextDouble();
/*     */     }
/*     */     
/* 397 */     return lower + u * (upper - lower);
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
/*     */   private RandomGenerator getRan()
/*     */   {
/* 410 */     if (this.rand == null) {
/* 411 */       this.rand = new JDKRandomGenerator();
/* 412 */       this.rand.setSeed(System.currentTimeMillis());
/*     */     }
/* 414 */     return this.rand;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   private SecureRandom getSecRan()
/*     */   {
/* 425 */     if (this.secRand == null) {
/* 426 */       this.secRand = new SecureRandom();
/* 427 */       this.secRand.setSeed(System.currentTimeMillis());
/*     */     }
/* 429 */     return this.secRand;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public void reSeed(long seed)
/*     */   {
/* 440 */     if (this.rand == null) {
/* 441 */       this.rand = new JDKRandomGenerator();
/*     */     }
/* 443 */     this.rand.setSeed(seed);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public void reSeedSecure()
/*     */   {
/* 453 */     if (this.secRand == null) {
/* 454 */       this.secRand = new SecureRandom();
/*     */     }
/* 456 */     this.secRand.setSeed(System.currentTimeMillis());
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public void reSeedSecure(long seed)
/*     */   {
/* 467 */     if (this.secRand == null) {
/* 468 */       this.secRand = new SecureRandom();
/*     */     }
/* 470 */     this.secRand.setSeed(seed);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   public void reSeed()
/*     */   {
/* 478 */     if (this.rand == null) {
/* 479 */       this.rand = new JDKRandomGenerator();
/*     */     }
/* 481 */     this.rand.setSeed(System.currentTimeMillis());
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
/*     */   public void setSecureAlgorithm(String algorithm, String provider)
/*     */     throws NoSuchAlgorithmException, NoSuchProviderException
/*     */   {
/* 503 */     this.secRand = SecureRandom.getInstance(algorithm, provider);
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
/*     */   public int[] nextPermutation(int n, int k)
/*     */   {
/* 516 */     if (k > n) {
/* 517 */       throw new IllegalArgumentException("permutation k exceeds n");
/*     */     }
/*     */     
/* 520 */     if (k == 0) {
/* 521 */       throw new IllegalArgumentException("permutation k must be > 0");
/*     */     }
/*     */     
/*     */ 
/* 525 */     int[] index = getNatural(n);
/* 526 */     shuffle(index, n - k);
/* 527 */     int[] result = new int[k];
/* 528 */     for (int i = 0; i < k; i++) {
/* 529 */       result[i] = index[(n - i - 1)];
/*     */     }
/*     */     
/* 532 */     return result;
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
/*     */   public Object[] nextSample(Collection c, int k)
/*     */   {
/* 549 */     int len = c.size();
/* 550 */     if (k > len) {
/* 551 */       throw new IllegalArgumentException("sample size exceeds collection size");
/*     */     }
/*     */     
/* 554 */     if (k == 0) {
/* 555 */       throw new IllegalArgumentException("sample size must be > 0");
/*     */     }
/*     */     
/*     */ 
/* 559 */     Object[] objects = c.toArray();
/* 560 */     int[] index = nextPermutation(len, k);
/* 561 */     Object[] result = new Object[k];
/* 562 */     for (int i = 0; i < k; i++) {
/* 563 */       result[i] = objects[index[i]];
/*     */     }
/* 565 */     return result;
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
/*     */   private void shuffle(int[] list, int end)
/*     */   {
/* 578 */     int target = 0;
/* 579 */     for (int i = list.length - 1; i >= end; i--) {
/* 580 */       if (i == 0) {
/* 581 */         target = 0;
/*     */       } else {
/* 583 */         target = nextInt(0, i);
/*     */       }
/* 585 */       int temp = list[target];
/* 586 */       list[target] = list[i];
/* 587 */       list[i] = temp;
/*     */     }
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   private int[] getNatural(int n)
/*     */   {
/* 598 */     int[] natural = new int[n];
/* 599 */     for (int i = 0; i < n; i++) {
/* 600 */       natural[i] = i;
/*     */     }
/* 602 */     return natural;
/*     */   }
/*     */ }


/* Location:              /home/menick/Scrivania/ProcessMining/CNMining/CNMining.jar!/org/apache/commons/math/random/RandomDataImpl.class
 * Java compiler version: 3 (47.0)
 * JD-Core Version:       0.7.1
 */