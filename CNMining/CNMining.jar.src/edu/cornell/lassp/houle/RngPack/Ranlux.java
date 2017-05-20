/*     */ package edu.cornell.lassp.houle.RngPack;
/*     */ 
/*     */ import cern.colt.PersistentObject;
/*     */ import java.io.PrintStream;
/*     */ import java.util.Date;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class Ranlux
/*     */   extends RandomSeedable
/*     */ {
/*     */   public static final int maxlev = 4;
/*     */   public static final int lxdflt = 3;
/*     */   static final int igiga = 1000000000;
/*     */   static final int jsdflt = 314159265;
/*     */   static final int twop12 = 4096;
/*     */   static final int itwo24 = 16777216;
/*     */   static final int icons = 2147483563;
/* 106 */   static final int[] ndskip = { 0, 24, 73, 199, 365 };
/*     */   int[] iseeds;
/*     */   int[] isdext;
/* 109 */   int[] next; int luxlev = 3;
/* 110 */   int nskip; int inseed; int jseed; int in24 = 0; int kount = 0; int mkount = 0; int i24 = 24; int j24 = 10;
/* 111 */   float[] seeds; float carry = 0.0F;
/*     */   float twom24;
/* 113 */   float twom12; boolean diagOn = false;
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public Ranlux()
/*     */   {
/* 123 */     init_arrays();
/* 124 */     rluxdef();
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public Ranlux(int paramInt)
/*     */   {
/* 135 */     init_arrays();
/* 136 */     rluxgo(3, Math.abs(paramInt));
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
/*     */   public Ranlux(int paramInt1, int paramInt2)
/*     */   {
/* 149 */     init_arrays();
/* 150 */     rluxgo(paramInt1, Math.abs(paramInt2));
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
/*     */   public Ranlux(int paramInt, long paramLong)
/*     */   {
/* 163 */     init_arrays();
/* 164 */     rluxgo(paramInt, Math.abs((int)(paramLong % 2147483647L)));
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
/*     */   public Ranlux(int paramInt, Date paramDate)
/*     */   {
/* 182 */     init_arrays();
/* 183 */     rluxgo(paramInt, (int)(RandomSeedable.ClockSeed(paramDate) % 2147483647L));
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public Ranlux(long paramLong)
/*     */   {
/* 194 */     init_arrays();
/* 195 */     rluxgo(3, Math.abs((int)(paramLong % 2147483647L)));
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
/*     */   public Ranlux(Date paramDate)
/*     */   {
/* 212 */     init_arrays();
/* 213 */     rluxgo(3, (int)(RandomSeedable.ClockSeed(paramDate) % 2147483647L));
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public Object clone()
/*     */   {
/* 222 */     Ranlux localRanlux = (Ranlux)super.clone();
/* 223 */     localRanlux.iseeds = ((int[])this.iseeds.clone());
/* 224 */     localRanlux.isdext = ((int[])this.isdext.clone());
/* 225 */     localRanlux.next = ((int[])this.next.clone());
/* 226 */     localRanlux.seeds = ((float[])this.seeds.clone());
/* 227 */     return localRanlux;
/*     */   }
/*     */   
/*     */   private void diag(String paramString) {
/* 231 */     if (this.diagOn) {
/* 232 */       System.err.println(paramString);
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
/*     */   private void init_arrays()
/*     */   {
/* 245 */     this.iseeds = new int[25];
/* 246 */     this.isdext = new int[26];
/* 247 */     this.next = new int[25];
/* 248 */     this.seeds = new float[25];
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
/*     */   public final double raw()
/*     */   {
/* 261 */     float f1 = this.seeds[this.j24] - this.seeds[this.i24] - this.carry;
/* 262 */     if (f1 < 0.0F) {
/* 263 */       f1 += 1.0F;
/*     */       
/* 265 */       this.carry = this.twom24;
/* 266 */     } else { this.carry = 0.0F;
/*     */     }
/* 268 */     this.seeds[this.i24] = f1;
/*     */     
/* 270 */     this.i24 = this.next[this.i24];
/* 271 */     this.j24 = this.next[this.j24];
/*     */     
/* 273 */     float f2 = f1;
/*     */     
/* 275 */     if (f1 < this.twom12) {
/* 276 */       f2 += this.twom24 * this.seeds[this.j24];
/*     */     }
/*     */     
/*     */ 
/* 280 */     if (f2 == 0.0D) { f2 = this.twom24 * this.twom24;
/*     */     }
/* 282 */     this.in24 += 1;
/*     */     
/* 284 */     if (this.in24 == 24) {
/* 285 */       this.in24 = 0;
/* 286 */       this.kount += this.nskip;
/* 287 */       for (int i = 1; i <= this.nskip; i++) {
/* 288 */         f1 = this.seeds[this.j24] - this.seeds[this.i24] - this.carry;
/* 289 */         if (f1 < 0.0F) {
/* 290 */           f1 += 1.0F;
/*     */           
/* 292 */           this.carry = this.twom24;
/* 293 */         } else { this.carry = 0.0F;
/*     */         }
/* 295 */         this.seeds[this.i24] = f1;
/*     */         
/* 297 */         this.i24 = this.next[this.i24];
/* 298 */         this.j24 = this.next[this.j24];
/*     */       }
/*     */     }
/*     */     
/* 302 */     this.kount += 1;
/* 303 */     if (this.kount >= 1000000000) {
/* 304 */       this.mkount += 1;
/* 305 */       this.kount -= 1000000000;
/*     */     }
/*     */     
/* 308 */     return f2;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */   private void rluxdef()
/*     */   {
/* 315 */     this.jseed = 314159265;
/* 316 */     this.inseed = this.jseed;
/* 317 */     diag("RANLUX DEFAULT INITIALIZATION: " + this.jseed);
/* 318 */     this.luxlev = 3;
/* 319 */     this.nskip = ndskip[this.luxlev];
/* 320 */     int i = this.nskip + 24;
/* 321 */     this.in24 = 0;
/* 322 */     this.kount = 0;
/* 323 */     this.mkount = 0;
/* 324 */     diag("RANLUX DEFAULT LUXURY LEVEL =  " + this.luxlev + "    p = " + i);
/* 325 */     this.twom24 = 1.0F;
/*     */     
/* 327 */     for (int j = 1; j <= 24; j++)
/*     */     {
/* 329 */       this.twom24 *= 0.5F;
/* 330 */       int k = this.jseed / 53668;
/* 331 */       this.jseed = (40014 * (this.jseed - k * 53668) - k * 12211);
/* 332 */       if (this.jseed < 0) this.jseed += 2147483563;
/* 333 */       this.iseeds[j] = (this.jseed % 16777216);
/*     */     }
/*     */     
/* 336 */     this.twom12 = (this.twom24 * 4096.0F);
/*     */     
/* 338 */     for (j = 1; j <= 24; j++)
/*     */     {
/* 340 */       this.seeds[j] = (this.iseeds[j] * this.twom24);
/* 341 */       this.next[j] = (j - 1);
/*     */     }
/*     */     
/* 344 */     this.next[1] = 24;
/* 345 */     this.i24 = 24;
/* 346 */     this.j24 = 10;
/* 347 */     this.carry = 0.0F;
/* 348 */     if (this.seeds[24] == 0.0D) { this.carry = this.twom24;
/*     */     }
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */   private final void rluxgo(int paramInt1, int paramInt2)
/*     */   {
/* 356 */     if (paramInt1 < 0)
/*     */     {
/* 358 */       this.luxlev = 3;
/*     */     }
/* 360 */     else if (paramInt1 <= 4)
/*     */     {
/* 362 */       this.luxlev = paramInt1;
/*     */     }
/* 364 */     else if ((paramInt1 < 24) || (paramInt1 > 2000))
/*     */     {
/* 366 */       this.luxlev = 4;
/* 367 */       diag("RANLUX ILLEGAL LUXURY RLUXGO: " + paramInt1);
/*     */     }
/*     */     else {
/* 370 */       this.luxlev = paramInt1;
/* 371 */       for (int i = 0; i <= 4; i++) {
/* 372 */         if (paramInt1 == ndskip[i] + 24) {
/* 373 */           this.luxlev = i;
/*     */         }
/*     */       }
/*     */     }
/* 377 */     if (this.luxlev <= 4) {
/* 378 */       this.nskip = ndskip[this.luxlev];
/* 379 */       diag("RANLUX LUXURY LEVEL SET BY RLUXGO : " + this.luxlev + " P= " + (this.nskip + 24));
/*     */     } else {
/* 381 */       this.nskip = (this.luxlev - 24);
/* 382 */       diag("RANLUX P-VALUE SET BY RLUXGO TO: " + this.luxlev);
/*     */     }
/*     */     
/* 385 */     this.in24 = 0;
/*     */     
/* 387 */     if (paramInt2 < 0) {
/* 388 */       diag("Illegal initialization by RLUXGO, negative input seed");
/*     */     }
/* 390 */     if (paramInt2 > 0) {
/* 391 */       this.jseed = paramInt2;
/* 392 */       diag("RANLUX INITIALIZED BY RLUXGO FROM SEED " + this.jseed);
/*     */     }
/*     */     else
/*     */     {
/* 396 */       this.jseed = 314159265;
/* 397 */       diag("RANLUX INITIALIZED BY RLUXGO FROM DEFAULT SEED");
/*     */     }
/*     */     
/* 400 */     this.inseed = this.jseed;
/* 401 */     this.twom24 = 1.0F;
/*     */     
/* 403 */     for (int j = 1; j <= 24; j++) {
/* 404 */       this.twom24 *= 0.5F;
/* 405 */       int k = this.jseed / 53668;
/* 406 */       this.jseed = (40014 * (this.jseed - k * 53668) - k * 12211);
/* 407 */       if (this.jseed < 0) this.jseed += 2147483563;
/* 408 */       this.iseeds[j] = (this.jseed % 16777216);
/*     */     }
/*     */     
/* 411 */     this.twom12 = (this.twom24 * 4096.0F);
/*     */     
/* 413 */     for (j = 1; j <= 24; j++) {
/* 414 */       this.seeds[j] = (this.iseeds[j] * this.twom24);
/* 415 */       this.next[j] = (j - 1);
/*     */     }
/*     */     
/* 418 */     this.next[1] = 24;
/* 419 */     this.i24 = 24;
/* 420 */     this.j24 = 10;
/* 421 */     this.carry = 0.0F;
/*     */     
/* 423 */     if (this.seeds[24] == 0.0D) { this.carry = this.twom24;
/*     */     }
/* 425 */     this.kount = 0;
/* 426 */     this.mkount = 0;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public void setDiag(boolean paramBoolean)
/*     */   {
/* 438 */     this.diagOn = paramBoolean;
/*     */   }
/*     */ }


/* Location:              /home/menick/Scrivania/ProcessMining/CNMining/CNMining.jar!/edu/cornell/lassp/houle/RngPack/Ranlux.class
 * Java compiler version: 1 (45.3)
 * JD-Core Version:       0.7.1
 */