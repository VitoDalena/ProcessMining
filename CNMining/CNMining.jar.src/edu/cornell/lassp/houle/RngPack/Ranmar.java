/*     */ package edu.cornell.lassp.houle.RngPack;
/*     */ 
/*     */ import cern.colt.PersistentObject;
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
/*     */ public class Ranmar
/*     */   extends RandomSeedable
/*     */ {
/*     */   double c;
/*     */   double cd;
/*     */   double cm;
/*     */   double[] u;
/*     */   double[] uvec;
/*     */   int i97;
/*     */   int j97;
/*  46 */   public static int DEFSEED = 54217137;
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*  56 */   public static int BIG_PRIME = 899999963;
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public Ranmar()
/*     */   {
/*  67 */     ranmarin(DEFSEED);
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
/*     */   public Ranmar(int paramInt)
/*     */   {
/*  80 */     ranmarin(Math.abs(paramInt % BIG_PRIME));
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
/*     */   public Ranmar(long paramLong)
/*     */   {
/*  93 */     ranmarin((int)Math.abs(paramLong % BIG_PRIME));
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
/*     */   public Ranmar(Date paramDate)
/*     */   {
/* 109 */     ranmarin((int)RandomSeedable.ClockSeed(paramDate) % BIG_PRIME);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public Object clone()
/*     */   {
/* 118 */     Ranmar localRanmar = (Ranmar)super.clone();
/* 119 */     localRanmar.u = ((double[])this.u.clone());
/* 120 */     localRanmar.uvec = ((double[])this.uvec.clone());
/* 121 */     return localRanmar;
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
/*     */   void ranmarin(int paramInt)
/*     */   {
/* 137 */     this.u = new double[97];
/* 138 */     this.uvec = new double[97];
/*     */     
/*     */ 
/* 141 */     int i = paramInt / 30082;
/* 142 */     int j = paramInt - 30082 * i;
/*     */     
/* 144 */     int k = i / 177 % 177 + 2;
/* 145 */     int n = i % 177 + 2;
/* 146 */     int i2 = j / 169 % 178 + 1;
/* 147 */     int i3 = j % 169;
/* 148 */     for (int m = 0; m < 97; m++)
/*     */     {
/* 150 */       double d1 = 0.0D;
/* 151 */       double d2 = 0.5D;
/* 152 */       for (int i1 = 0; i1 < 24; i1++)
/*     */       {
/* 154 */         int i4 = k * n % 179 * i2 % 179;
/* 155 */         k = n;
/* 156 */         n = i2;
/* 157 */         i2 = i4;
/* 158 */         i3 = (53 * i3 + 1) % 169;
/* 159 */         if (i3 * i4 % 64 >= 32) d1 += d2;
/* 160 */         d2 *= 0.5D;
/*     */       }
/* 162 */       this.u[m] = d1;
/*     */     }
/* 164 */     this.c = 0.021602869033813477D;
/* 165 */     this.cd = 0.45623308420181274D;
/* 166 */     this.cm = 0.9999998211860657D;
/* 167 */     this.i97 = 96;
/* 168 */     this.j97 = 32;
/*     */   }
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
/* 180 */     double d = this.u[this.i97] - this.u[this.j97];
/* 181 */     if (d < 0.0D) { d += 1.0D;
/*     */     }
/* 183 */     this.u[this.i97] = d;
/* 184 */     if (--this.i97 < 0) this.i97 = 96;
/* 185 */     if (--this.j97 < 0) this.j97 = 96;
/* 186 */     this.c -= this.cd;
/* 187 */     if (this.c < 0.0D) this.c += this.cm;
/* 188 */     d -= this.c;
/* 189 */     if (d < 0.0D) { d += 1.0D;
/*     */     }
/* 191 */     return d;
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
/*     */   public final void raw(double[] paramArrayOfDouble, int paramInt)
/*     */   {
/* 206 */     for (int i = 0; i < paramInt; i++)
/*     */     {
/* 208 */       double d = this.u[this.i97] - this.u[this.j97];
/* 209 */       if (d < 0.0D) { d += 1.0D;
/*     */       }
/* 211 */       this.u[this.i97] = d;
/* 212 */       if (--this.i97 < 0) this.i97 = 96;
/* 213 */       if (--this.j97 < 0) this.j97 = 96;
/* 214 */       this.c -= this.cd;
/* 215 */       if (this.c < 0.0D) this.c += this.cm;
/* 216 */       d -= this.c;
/* 217 */       if (d < 0.0D) { d += 1.0D;
/*     */       }
/* 219 */       paramArrayOfDouble[i] = d;
/*     */     }
/*     */   }
/*     */ }


/* Location:              /home/menick/Scrivania/ProcessMining/CNMining/CNMining.jar!/edu/cornell/lassp/houle/RngPack/Ranmar.class
 * Java compiler version: 1 (45.3)
 * JD-Core Version:       0.7.1
 */