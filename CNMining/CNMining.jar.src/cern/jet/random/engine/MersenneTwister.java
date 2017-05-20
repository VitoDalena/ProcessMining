/*     */ package cern.jet.random.engine;
/*     */ 
/*     */ import cern.colt.PersistentObject;
/*     */ import edu.cornell.lassp.houle.RngPack.RandomSeedable;
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
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class MersenneTwister
/*     */   extends RandomEngine
/*     */ {
/*     */   private int mti;
/* 118 */   private int[] mt = new int['ɰ'];
/*     */   
/*     */   private static final int N = 624;
/*     */   
/*     */   private static final int M = 397;
/*     */   
/*     */   private static final int MATRIX_A = -1727483681;
/*     */   
/*     */   private static final int UPPER_MASK = Integer.MIN_VALUE;
/*     */   
/*     */   private static final int LOWER_MASK = Integer.MAX_VALUE;
/*     */   
/*     */   private static final int TEMPERING_MASK_B = -1658038656;
/*     */   
/*     */   private static final int TEMPERING_MASK_C = -272236544;
/*     */   
/*     */   private static final int mag0 = 0;
/*     */   
/*     */   private static final int mag1 = -1727483681;
/*     */   
/*     */   public static final int DEFAULT_SEED = 4357;
/*     */   
/*     */ 
/*     */   public MersenneTwister()
/*     */   {
/* 143 */     this(4357);
/*     */   }
/*     */   
/*     */ 
/*     */   public MersenneTwister(int paramInt)
/*     */   {
/* 149 */     setSeed(paramInt);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   public MersenneTwister(Date paramDate)
/*     */   {
/* 157 */     this((int)RandomSeedable.ClockSeed(paramDate));
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public Object clone()
/*     */   {
/* 166 */     MersenneTwister localMersenneTwister = (MersenneTwister)super.clone();
/* 167 */     localMersenneTwister.mt = ((int[])this.mt.clone());
/* 168 */     return localMersenneTwister;
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
/*     */   protected void nextBlock()
/*     */   {
/* 206 */     for (int j = 0; j < 227; j++) {
/* 207 */       i = this.mt[j] & 0x80000000 | this.mt[(j + 1)] & 0x7FFFFFFF;
/* 208 */       this.mt[j] = (this.mt[(j + 397)] ^ i >>> 1 ^ ((i & 0x1) == 0 ? 0 : -1727483681));
/*     */     }
/* 210 */     for (; j < 623; j++) {
/* 211 */       i = this.mt[j] & 0x80000000 | this.mt[(j + 1)] & 0x7FFFFFFF;
/* 212 */       this.mt[j] = (this.mt[(j + 65309)] ^ i >>> 1 ^ ((i & 0x1) == 0 ? 0 : -1727483681));
/*     */     }
/* 214 */     int i = this.mt['ɯ'] & 0x80000000 | this.mt[0] & 0x7FFFFFFF;
/* 215 */     this.mt['ɯ'] = (this.mt['ƌ'] ^ i >>> 1 ^ ((i & 0x1) == 0 ? 0 : -1727483681));
/*     */     
/* 217 */     this.mti = 0;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   public int nextInt()
/*     */   {
/* 225 */     if (this.mti == 624) { nextBlock();
/*     */     }
/* 227 */     int i = this.mt[(this.mti++)];
/* 228 */     i ^= i >>> 11;
/* 229 */     i ^= i << 7 & 0x9D2C5680;
/* 230 */     i ^= i << 15 & 0xEFC60000;
/*     */     
/* 232 */     i ^= i >>> 18;
/*     */     
/* 234 */     return i;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */   protected void setSeed(int paramInt)
/*     */   {
/* 241 */     this.mt[0] = (paramInt & 0xFFFFFFFF);
/* 242 */     for (int i = 1; i < 624; i++) {
/* 243 */       this.mt[i] = (1812433253 * (this.mt[(i - 1)] ^ this.mt[(i - 1)] >> 30) + i);
/*     */       
/*     */ 
/*     */ 
/*     */ 
/* 248 */       this.mt[i] &= 0xFFFFFFFF;
/*     */     }
/*     */     
/*     */ 
/* 252 */     this.mti = 624;
/*     */   }
/*     */ }


/* Location:              /home/menick/Scrivania/ProcessMining/CNMining/CNMining.jar!/cern/jet/random/engine/MersenneTwister.class
 * Java compiler version: 1 (45.3)
 * JD-Core Version:       0.7.1
 */