/*     */ package jal.BYTE;
/*     */ 
/*     */ import java.util.Random;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public final class Modification
/*     */ {
/*     */   public static void copy(byte[] paramArrayOfByte1, byte[] paramArrayOfByte2, int paramInt1, int paramInt2, int paramInt3)
/*     */   {
/*  63 */     if (paramInt2 > paramInt1) {
/*  64 */       System.arraycopy(paramArrayOfByte1, paramInt1, paramArrayOfByte2, paramInt3, paramInt2 - paramInt1);
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
/*     */   public static void swap_ranges(byte[] paramArrayOfByte1, byte[] paramArrayOfByte2, int paramInt1, int paramInt2, int paramInt3)
/*     */   {
/*  82 */     while (paramInt1 < paramInt2) {
/*  83 */       int i = paramArrayOfByte2[paramInt3];
/*  84 */       paramArrayOfByte2[paramInt3] = paramArrayOfByte1[paramInt1];
/*  85 */       paramArrayOfByte1[paramInt1] = i;
/*  86 */       paramInt1++;
/*  87 */       paramInt3++;
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
/*     */   public static void transform(byte[] paramArrayOfByte1, byte[] paramArrayOfByte2, int paramInt1, int paramInt2, int paramInt3, UnaryOperator paramUnaryOperator)
/*     */   {
/* 111 */     while (paramInt1 < paramInt2) {
/* 112 */       paramArrayOfByte2[(paramInt3++)] = paramUnaryOperator.apply(paramArrayOfByte1[(paramInt1++)]);
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
/*     */   public static void transform(byte[] paramArrayOfByte1, byte[] paramArrayOfByte2, byte[] paramArrayOfByte3, int paramInt1, int paramInt2, int paramInt3, int paramInt4, BinaryOperator paramBinaryOperator)
/*     */   {
/* 139 */     while (paramInt1 < paramInt2) {
/* 140 */       paramArrayOfByte3[(paramInt4++)] = paramBinaryOperator.apply(paramArrayOfByte1[(paramInt1++)], paramArrayOfByte2[(paramInt3++)]);
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
/*     */   public static void replace(byte[] paramArrayOfByte, int paramInt1, int paramInt2, byte paramByte1, byte paramByte2)
/*     */   {
/* 155 */     while (paramInt1 < paramInt2) {
/* 156 */       if (paramArrayOfByte[paramInt1] == paramByte1)
/* 157 */         paramArrayOfByte[paramInt1] = paramByte2;
/* 158 */       paramInt1++;
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
/*     */   public static void replace_if(byte[] paramArrayOfByte, int paramInt1, int paramInt2, Predicate paramPredicate, byte paramByte)
/*     */   {
/* 175 */     while (paramInt1 < paramInt2) {
/* 176 */       if (paramPredicate.apply(paramArrayOfByte[paramInt1]))
/* 177 */         paramArrayOfByte[paramInt1] = paramByte;
/* 178 */       paramInt1++;
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
/*     */   public static void replace_copy(byte[] paramArrayOfByte1, byte[] paramArrayOfByte2, int paramInt1, int paramInt2, int paramInt3, byte paramByte1, byte paramByte2)
/*     */   {
/* 201 */     while (paramInt1 < paramInt2) {
/* 202 */       byte b = paramArrayOfByte1[(paramInt1++)];
/* 203 */       paramArrayOfByte2[(paramInt3++)] = (b == paramByte1 ? paramByte2 : b);
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
/*     */   public static void replace_copy_if(byte[] paramArrayOfByte1, byte[] paramArrayOfByte2, int paramInt1, int paramInt2, int paramInt3, Predicate paramPredicate, byte paramByte)
/*     */   {
/* 226 */     while (paramInt1 < paramInt2) {
/* 227 */       byte b = paramArrayOfByte1[(paramInt1++)];
/* 228 */       paramArrayOfByte2[(paramInt3++)] = (paramPredicate.apply(b) ? paramByte : b);
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
/*     */   public static void fill(byte[] paramArrayOfByte, int paramInt1, int paramInt2, byte paramByte)
/*     */   {
/* 243 */     while (paramInt1 < paramInt2) {
/* 244 */       paramArrayOfByte[(paramInt1++)] = paramByte;
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
/*     */   public static void generate(byte[] paramArrayOfByte, int paramInt1, int paramInt2, Generator paramGenerator)
/*     */   {
/* 261 */     while (paramInt1 < paramInt2) {
/* 262 */       paramArrayOfByte[(paramInt1++)] = paramGenerator.apply();
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
/*     */   public static int remove_if(byte[] paramArrayOfByte, int paramInt1, int paramInt2, byte paramByte)
/*     */   {
/* 279 */     int i = paramInt2;
/* 280 */     paramInt1--;
/*     */     for (;;) {
/* 282 */       paramInt1++; if (paramInt1 < paramInt2) { if (paramArrayOfByte[paramInt1] != paramByte) {}
/* 283 */       } else { while ((paramInt1 < --paramInt2) && (paramArrayOfByte[paramInt2] == paramByte)) {}
/* 284 */         if (paramInt1 >= paramInt2) {
/* 285 */           return paramInt1;
/*     */         }
/* 287 */         paramArrayOfByte[paramInt1] = paramArrayOfByte[paramInt2];
/*     */       }
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
/*     */   public static int remove_if(byte[] paramArrayOfByte, int paramInt1, int paramInt2, Predicate paramPredicate)
/*     */   {
/* 306 */     int i = paramInt2;
/* 307 */     paramInt1--;
/*     */     for (;;) {
/* 309 */       paramInt1++; if (paramInt1 < paramInt2) { if (!paramPredicate.apply(paramArrayOfByte[paramInt1])) {}
/* 310 */       } else { while ((paramInt1 < --paramInt2) && (paramPredicate.apply(paramArrayOfByte[paramInt2]))) {}
/* 311 */         if (paramInt1 >= paramInt2) {
/* 312 */           return paramInt1;
/*     */         }
/* 314 */         paramArrayOfByte[paramInt1] = paramArrayOfByte[paramInt2];
/*     */       }
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
/*     */   public static int stable_remove(byte[] paramArrayOfByte, int paramInt1, int paramInt2, byte paramByte)
/*     */   {
/* 332 */     paramInt1 = Inspection.find(paramArrayOfByte, paramInt1, paramInt2, paramByte);
/* 333 */     int i = Inspection.find_not(paramArrayOfByte, paramInt1, paramInt2, paramByte);
/* 334 */     while (i < paramInt2) {
/* 335 */       paramArrayOfByte[(paramInt1++)] = paramArrayOfByte[i];
/* 336 */       i = Inspection.find_not(paramArrayOfByte, ++i, paramInt2, paramByte);
/*     */     }
/* 338 */     return paramInt1;
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
/*     */   public static int stable_remove_if(byte[] paramArrayOfByte, int paramInt1, int paramInt2, Predicate paramPredicate)
/*     */   {
/* 355 */     paramInt1 = Inspection.find_if(paramArrayOfByte, paramInt1, paramInt2, paramPredicate);
/* 356 */     int i = Inspection.find_if_not(paramArrayOfByte, paramInt1, paramInt2, paramPredicate);
/* 357 */     while (i < paramInt2) {
/* 358 */       paramArrayOfByte[(paramInt1++)] = paramArrayOfByte[i];
/* 359 */       i = Inspection.find_if_not(paramArrayOfByte, ++i, paramInt2, paramPredicate);
/*     */     }
/* 361 */     return paramInt1;
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
/*     */   public static int remove_copy(byte[] paramArrayOfByte1, byte[] paramArrayOfByte2, int paramInt1, int paramInt2, int paramInt3, byte paramByte)
/*     */   {
/* 383 */     while (paramInt1 < paramInt2) {
/* 384 */       byte b = paramArrayOfByte1[(paramInt1++)];
/* 385 */       if (b != paramByte)
/* 386 */         paramArrayOfByte2[(paramInt3++)] = b;
/*     */     }
/* 388 */     return paramInt3;
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
/*     */   public static int remove_copy_if(byte[] paramArrayOfByte1, byte[] paramArrayOfByte2, int paramInt1, int paramInt2, int paramInt3, Predicate paramPredicate)
/*     */   {
/* 410 */     while (paramInt1 < paramInt2) {
/* 411 */       byte b = paramArrayOfByte1[(paramInt1++)];
/* 412 */       if (!paramPredicate.apply(b))
/* 413 */         paramArrayOfByte2[(paramInt3++)] = b;
/*     */     }
/* 415 */     return paramInt3;
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
/*     */   public static int unique(byte[] paramArrayOfByte, int paramInt1, int paramInt2)
/*     */   {
/* 431 */     paramInt1 = Inspection.adjacent_find(paramArrayOfByte, paramInt1, paramInt2);
/* 432 */     return unique_copy(paramArrayOfByte, paramArrayOfByte, paramInt1, paramInt2, paramInt1);
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
/*     */   public static int unique(byte[] paramArrayOfByte, int paramInt1, int paramInt2, BinaryPredicate paramBinaryPredicate)
/*     */   {
/* 451 */     paramInt1 = Inspection.adjacent_find(paramArrayOfByte, paramInt1, paramInt2, paramBinaryPredicate);
/* 452 */     return unique_copy(paramArrayOfByte, paramArrayOfByte, paramInt1, paramInt2, paramInt1, paramBinaryPredicate);
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
/*     */   public static int unique_copy(byte[] paramArrayOfByte1, byte[] paramArrayOfByte2, int paramInt1, int paramInt2, int paramInt3)
/*     */   {
/* 474 */     if (paramInt1 >= paramInt2) {
/* 475 */       return paramInt3;
/*     */     }
/* 477 */     paramArrayOfByte2[paramInt3] = paramArrayOfByte1[paramInt1];
/*     */     do
/*     */     {
/* 480 */       if (paramArrayOfByte2[paramInt3] != paramArrayOfByte1[paramInt1]) {
/* 481 */         paramArrayOfByte2[(++paramInt3)] = paramArrayOfByte1[paramInt1];
/*     */       }
/* 479 */       paramInt1++; } while (paramInt1 < paramInt2);
/*     */     
/*     */ 
/*     */ 
/*     */ 
/* 484 */     return paramInt3 + 1;
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
/*     */   public static int unique_copy(byte[] paramArrayOfByte1, byte[] paramArrayOfByte2, int paramInt1, int paramInt2, int paramInt3, BinaryPredicate paramBinaryPredicate)
/*     */   {
/* 509 */     if (paramInt1 >= paramInt2) {
/* 510 */       return paramInt3;
/*     */     }
/* 512 */     paramArrayOfByte2[paramInt3] = paramArrayOfByte1[paramInt1];
/*     */     do
/*     */     {
/* 515 */       if (!paramBinaryPredicate.apply(paramArrayOfByte2[paramInt3], paramArrayOfByte1[paramInt1])) {
/* 516 */         paramArrayOfByte2[(++paramInt3)] = paramArrayOfByte1[paramInt1];
/*     */       }
/* 514 */       paramInt1++; } while (paramInt1 < paramInt2);
/*     */     
/*     */ 
/*     */ 
/*     */ 
/* 519 */     return paramInt3 + 1;
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
/*     */   public static void reverse(byte[] paramArrayOfByte, int paramInt1, int paramInt2)
/*     */   {
/* 532 */     while (paramInt1 < --paramInt2) {
/* 533 */       int i = paramArrayOfByte[paramInt1];
/* 534 */       paramArrayOfByte[(paramInt1++)] = paramArrayOfByte[paramInt2];
/* 535 */       paramArrayOfByte[paramInt2] = i;
/*     */     }
/*     */   }
/*     */   
/*     */   public static void reverse_copy(byte[] paramArrayOfByte, int paramInt1, int paramInt2, int paramInt3)
/*     */   {
/* 541 */     while (paramInt2 > paramInt1) {
/* 542 */       paramArrayOfByte[(paramInt3++)] = paramArrayOfByte[(--paramInt2)];
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
/*     */   public static void reverse_copy(byte[] paramArrayOfByte1, byte[] paramArrayOfByte2, int paramInt1, int paramInt2, int paramInt3)
/*     */   {
/* 561 */     while (paramInt2 > paramInt1) {
/* 562 */       paramArrayOfByte2[(paramInt3++)] = paramArrayOfByte1[(--paramInt2)];
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
/*     */   public static void rotate(byte[] paramArrayOfByte, int paramInt1, int paramInt2, int paramInt3)
/*     */   {
/* 580 */     if ((paramInt2 != paramInt1) && (paramInt2 != paramInt3)) {
/* 581 */       reverse(paramArrayOfByte, paramInt1, paramInt2);
/* 582 */       reverse(paramArrayOfByte, paramInt2, paramInt3);
/* 583 */       reverse(paramArrayOfByte, paramInt1, paramInt3);
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
/*     */   public static void rotate_copy(byte[] paramArrayOfByte1, byte[] paramArrayOfByte2, int paramInt1, int paramInt2, int paramInt3, int paramInt4)
/*     */   {
/* 606 */     copy(paramArrayOfByte1, paramArrayOfByte2, paramInt2, paramInt3, paramInt4);
/* 607 */     copy(paramArrayOfByte1, paramArrayOfByte2, paramInt1, paramInt2, paramInt4 + (paramInt3 - paramInt2));
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
/*     */   public static void random_shuffle(byte[] paramArrayOfByte, int paramInt1, int paramInt2, Random paramRandom)
/*     */   {
/* 621 */     for (int i = paramInt1 + 1; i < paramInt2; i++) {
/* 622 */       int j = Math.abs(paramRandom.nextInt()) % (i - paramInt1 + 1);
/*     */       
/* 624 */       int k = paramArrayOfByte[j];
/* 625 */       paramArrayOfByte[j] = paramArrayOfByte[i];
/* 626 */       paramArrayOfByte[i] = k;
/*     */     }
/*     */   }
/*     */   
/* 630 */   private static Random default_RNG = new Random();
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public static void random_shuffle(byte[] paramArrayOfByte, int paramInt1, int paramInt2)
/*     */   {
/* 641 */     random_shuffle(paramArrayOfByte, paramInt1, paramInt2, default_RNG);
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
/*     */   public static int partition(byte[] paramArrayOfByte, int paramInt1, int paramInt2, Predicate paramPredicate)
/*     */   {
/*     */     
/*     */     
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     for (;;)
/*     */     {
/* 664 */       paramInt1++; if (paramInt1 < paramInt2) { if (paramPredicate.apply(paramArrayOfByte[paramInt1])) {}
/* 665 */       } else { while ((paramInt1 < --paramInt2) && (!paramPredicate.apply(paramArrayOfByte[paramInt2]))) {}
/* 666 */         if (paramInt1 >= paramInt2) return paramInt1;
/* 667 */         int i = paramArrayOfByte[paramInt1];
/* 668 */         paramArrayOfByte[paramInt1] = paramArrayOfByte[paramInt2];
/* 669 */         paramArrayOfByte[paramInt2] = i;
/*     */       }
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
/*     */   public static int stable_partition(byte[] paramArrayOfByte, int paramInt1, int paramInt2, Predicate paramPredicate)
/*     */   {
/* 693 */     if (paramInt1 + 1 < paramInt2) {
/* 694 */       int i = paramInt1 + (paramInt2 - paramInt1) / 2;
/* 695 */       int j = stable_partition(paramArrayOfByte, paramInt1, i, paramPredicate);
/* 696 */       int k = stable_partition(paramArrayOfByte, i, paramInt2, paramPredicate);
/* 697 */       rotate(paramArrayOfByte, j, i, k);
/* 698 */       return j + (k - i);
/*     */     }
/* 700 */     if ((paramInt1 >= paramInt2) || (!paramPredicate.apply(paramArrayOfByte[paramInt1]))) {
/* 701 */       return paramInt1;
/*     */     }
/* 703 */     return paramInt2;
/*     */   }
/*     */ }


/* Location:              /home/menick/Scrivania/ProcessMining/CNMining/CNMining.jar!/jal/BYTE/Modification.class
 * Java compiler version: 1 (45.3)
 * JD-Core Version:       0.7.1
 */