/*     */ package cern.colt;
/*     */ 
/*     */ import cern.jet.math.Arithmetic;
/*     */ import cern.jet.random.Uniform;
/*     */ import cern.jet.random.engine.MersenneTwister;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class GenericPermuting
/*     */ {
/*     */   public static int[] permutation(long paramLong, int paramInt)
/*     */   {
/* 138 */     if (paramLong < 1L) throw new IllegalArgumentException("Permutations are enumerated 1 .. N!");
/* 139 */     if (paramInt < 0) { throw new IllegalArgumentException("Must satisfy N >= 0");
/*     */     }
/* 141 */     int[] arrayOfInt1 = new int[paramInt];
/*     */     
/* 143 */     if (paramInt > 20)
/*     */     {
/*     */ 
/* 146 */       int i = paramInt; do { arrayOfInt1[i] = i;i--; } while (i >= 0);
/* 147 */       Uniform localUniform = new Uniform(new MersenneTwister((int)paramLong));
/* 148 */       for (int k = 0; k < paramInt - 1; k++) {
/* 149 */         int m = localUniform.nextIntFromTo(k, paramInt - 1);
/*     */         
/*     */ 
/* 152 */         n = arrayOfInt1[m];
/* 153 */         arrayOfInt1[m] = arrayOfInt1[k];
/* 154 */         arrayOfInt1[k] = n;
/*     */       }
/*     */       
/* 157 */       return arrayOfInt1;
/*     */     }
/*     */     
/*     */ 
/* 161 */     if (paramLong > Arithmetic.longFactorial(paramInt)) { throw new IllegalArgumentException("N too large (a sequence of N elements only has N! permutations).");
/*     */     }
/* 163 */     int[] arrayOfInt2 = new int[paramInt];
/* 164 */     for (int j = 1; j <= paramInt; j++) { arrayOfInt2[(j - 1)] = j;
/*     */     }
/* 166 */     long l1 = paramLong - 1L;
/* 167 */     for (int n = paramInt - 1; n >= 1; n--) {
/* 168 */       long l2 = Arithmetic.longFactorial(n);
/* 169 */       int i2 = (int)(l1 / l2) + 1;
/* 170 */       l1 %= l2;
/* 171 */       arrayOfInt1[(paramInt - n - 1)] = arrayOfInt2[(i2 - 1)];
/*     */       
/* 173 */       for (int i3 = i2; i3 <= n; i3++) arrayOfInt2[(i3 - 1)] = arrayOfInt2[i3];
/*     */     }
/* 175 */     if (paramInt > 0) { arrayOfInt1[(paramInt - 1)] = arrayOfInt2[0];
/*     */     }
/* 177 */     int i1 = paramInt; do { arrayOfInt1[i1] -= 1;i1--; } while (i1 >= 0);
/* 178 */     return arrayOfInt1;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */   public static void permute(int[] paramArrayOfInt1, int[] paramArrayOfInt2)
/*     */   {
/* 185 */     int[] arrayOfInt = (int[])paramArrayOfInt1.clone();
/* 186 */     int i = paramArrayOfInt1.length; do { paramArrayOfInt1[i] = arrayOfInt[paramArrayOfInt2[i]];i--; } while (i >= 0);
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
/*     */   /**
/*     */    * @deprecated
/*     */    */
/*     */   public static void permute(int[] paramArrayOfInt1, Swapper paramSwapper, int[] paramArrayOfInt2)
/*     */   {
/* 212 */     permute(paramArrayOfInt1, paramSwapper, paramArrayOfInt2, null);
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
/*     */   public static void permute(int[] paramArrayOfInt1, Swapper paramSwapper, int[] paramArrayOfInt2, int[] paramArrayOfInt3)
/*     */   {
/* 244 */     int i = paramArrayOfInt1.length;
/* 245 */     int[] arrayOfInt1 = paramArrayOfInt2;
/* 246 */     int[] arrayOfInt2 = paramArrayOfInt3;
/* 247 */     if ((arrayOfInt1 == null) || (arrayOfInt1.length < i)) arrayOfInt1 = new int[i];
/* 248 */     if ((arrayOfInt2 == null) || (arrayOfInt2.length < i)) arrayOfInt2 = new int[i];
/* 249 */     int j = i;
/* 250 */     do { arrayOfInt1[j] = j;
/* 251 */       arrayOfInt2[j] = j;j--;
/* 249 */     } while (j >= 0);
/*     */     
/*     */ 
/*     */ 
/*     */ 
/* 254 */     for (int k = 0; k < i; k++) {
/* 255 */       int m = paramArrayOfInt1[k];
/* 256 */       int n = arrayOfInt1[m];
/*     */       
/* 258 */       if (k != n) {
/* 259 */         paramSwapper.swap(k, n);
/* 260 */         arrayOfInt1[m] = k;arrayOfInt1[arrayOfInt2[k]] = n;
/* 261 */         int i1 = arrayOfInt2[k];arrayOfInt2[k] = arrayOfInt2[n];arrayOfInt2[n] = i1;
/*     */       }
/*     */     }
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */   public static void permute(Object[] paramArrayOfObject, int[] paramArrayOfInt)
/*     */   {
/* 270 */     Object[] arrayOfObject = (Object[])paramArrayOfObject.clone();
/* 271 */     int i = paramArrayOfObject.length; do { paramArrayOfObject[i] = arrayOfObject[paramArrayOfInt[i]];i--; } while (i >= 0);
/*     */   }
/*     */ }


/* Location:              /home/menick/Scrivania/ProcessMining/CNMining/CNMining.jar!/cern/colt/GenericPermuting.class
 * Java compiler version: 1 (45.3)
 * JD-Core Version:       0.7.1
 */