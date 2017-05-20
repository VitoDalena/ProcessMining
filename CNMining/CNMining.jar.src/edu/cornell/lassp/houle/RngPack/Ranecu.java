/*     */ package edu.cornell.lassp.houle.RngPack;
/*     */ 
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
/*     */ public class Ranecu
/*     */   extends RandomSeedable
/*     */ {
/*     */   int iseed1;
/*     */   int iseed2;
/*  42 */   public static int DEFSEED1 = 12345;
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*  48 */   public static int DEFSEED2 = 67890;
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public Ranecu()
/*     */   {
/*  58 */     this.iseed1 = DEFSEED1;
/*  59 */     this.iseed2 = DEFSEED2;
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
/*     */   public Ranecu(int paramInt1, int paramInt2)
/*     */   {
/*  75 */     this.iseed1 = paramInt1;
/*  76 */     this.iseed2 = paramInt2;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public Ranecu(long paramLong)
/*     */   {
/*  87 */     this.iseed1 = ((int)paramLong / Integer.MAX_VALUE);
/*  88 */     this.iseed2 = ((int)paramLong % Integer.MAX_VALUE);
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
/*     */   public Ranecu(Date paramDate)
/*     */   {
/* 107 */     this.iseed1 = ((int)paramDate.getTime() / Integer.MAX_VALUE);
/* 108 */     this.iseed2 = ((int)paramDate.getTime() % Integer.MAX_VALUE);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public long getSeed()
/*     */   {
/* 117 */     return this.iseed1 * 2147483647L + this.iseed2;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public final double raw()
/*     */   {
/* 128 */     int i = this.iseed1 / 53688;
/* 129 */     this.iseed1 = (40014 * (this.iseed1 - i * 53668) - i * 12211);
/* 130 */     if (this.iseed1 < 0) { this.iseed1 += 2147483563;
/*     */     }
/*     */     
/*     */ 
/* 134 */     i = this.iseed2 / 52774;
/* 135 */     this.iseed2 = (40692 * (this.iseed2 - i * 52774) - i * 3791);
/* 136 */     if (this.iseed2 < 0) { this.iseed2 += 2147483399;
/*     */     }
/*     */     
/* 139 */     int j = this.iseed1 - this.iseed2;
/* 140 */     if (j < 1) { j += 2147483562;
/*     */     }
/*     */     
/* 143 */     return j * 4.656613E-10D;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public final void raw(double[] paramArrayOfDouble, int paramInt)
/*     */   {
/* 154 */     for (int i = 0; i < paramInt; i++)
/*     */     {
/*     */ 
/* 157 */       int j = this.iseed1 / 53688;
/* 158 */       this.iseed1 = (40014 * (this.iseed1 - j * 53668) - j * 12211);
/* 159 */       if (this.iseed1 < 0) { this.iseed1 += 2147483563;
/*     */       }
/*     */       
/* 162 */       j = this.iseed2 / 52774;
/* 163 */       this.iseed2 = (40692 * (this.iseed2 - j * 52774) - j * 3791);
/* 164 */       if (this.iseed2 < 0) { this.iseed2 += 2147483399;
/*     */       }
/*     */       
/* 167 */       int k = this.iseed1 - this.iseed2;
/* 168 */       if (k < 1) { k += 2147483562;
/*     */       }
/*     */       
/* 171 */       paramArrayOfDouble[i] = (k * 4.656613E-10D);
/*     */     }
/*     */   }
/*     */ }


/* Location:              /home/menick/Scrivania/ProcessMining/CNMining/CNMining.jar!/edu/cornell/lassp/houle/RngPack/Ranecu.class
 * Java compiler version: 1 (45.3)
 * JD-Core Version:       0.7.1
 */