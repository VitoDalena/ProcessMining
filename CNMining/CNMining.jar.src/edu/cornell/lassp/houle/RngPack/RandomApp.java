/*     */ package edu.cornell.lassp.houle.RngPack;
/*     */ 
/*     */ import java.io.PrintStream;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class RandomApp
/*     */ {
/*  35 */   static String[] generators = { "ranmar", "ranecu", "ranlux", "randomjava", "null" };
/*  36 */   static String[] distributions = { "flat", "gaussian" };
/*     */   
/*  38 */   static int RANMAR = 0; static int RANECU = 1; static int RANLUX = 2; static int RANJAVA = 3; static int NULL = 4;
/*  39 */   static int FLAT = 0; static int GAUSSIAN = 1;
/*     */   
/*     */   static void die(String paramString) {
/*  42 */     System.err.println(paramString);
/*  43 */     System.exit(-1);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public static void main(String[] paramArrayOfString)
/*     */   {
/*  53 */     int i2 = 0;int i3 = 0;int i4 = 0;
/*  54 */     int i5 = 0;int i6 = 0;
/*  55 */     int i7 = 0;
/*     */     
/*  57 */     int k = RANMAR;
/*  58 */     int m = FLAT;
/*  59 */     long l = RandomSeedable.ClockSeed();
/*  60 */     int i1 = 3;
/*  61 */     int n = 1;
/*     */     
/*     */ 
/*     */ 
/*     */ 
/*  66 */     for (int i = 0; i < paramArrayOfString.length; i++)
/*     */     {
/*  68 */       String str = new String(paramArrayOfString[i]);
/*  69 */       str.toLowerCase();
/*     */       
/*  71 */       if (str.compareTo("noprint") == 0) {
/*  72 */         i5 = 1;
/*     */ 
/*     */ 
/*     */       }
/*  76 */       else if (str.compareTo("seed") == 0) {
/*  77 */         if (i2 != 0)
/*  78 */           die("RandomApp: only one seed can be passed");
/*  79 */         if (i == paramArrayOfString.length - 1) {
/*  80 */           die("RandomApp: missing seed.");
/*     */         }
/*  82 */         i++;
/*  83 */         str = new String(paramArrayOfString[i]);
/*     */         try {
/*  85 */           l = Long.parseLong(str);
/*     */         } catch (NumberFormatException localNumberFormatException1) {
/*  87 */           die("RandomApp: seed is not a valid number.");
/*     */         }
/*  89 */         i2 = 1;
/*     */ 
/*     */ 
/*     */       }
/*  93 */       else if (str.compareTo("luxury") == 0) {
/*  94 */         if (i7 != 0)
/*  95 */           die("RandomApp: only one luxury level can be passed");
/*  96 */         if (i == paramArrayOfString.length - 1) {
/*  97 */           die("RandomApp: missing luxury level.");
/*     */         }
/*  99 */         i++;
/* 100 */         str = new String(paramArrayOfString[i]);
/*     */         try {
/* 102 */           i1 = Integer.parseInt(str);
/*     */         } catch (NumberFormatException localNumberFormatException2) {
/* 104 */           die("RandomApp: luxury level is not a valid number.");
/*     */         }
/* 106 */         i7 = 1;
/*     */         
/* 108 */         if ((i1 < 0) || (i1 > 4)) {
/* 109 */           die("RandomApp: luxury level must be between 0 and 4");
/*     */         }
/*     */       }
/*     */       else
/*     */       {
/* 114 */         for (int j = 0; j < generators.length; j++) {
/* 115 */           if (str.compareTo(generators[j]) == 0)
/*     */           {
/* 117 */             if (i3 != 0)
/* 118 */               die("RandomApp: only one generator can be selected.");
/* 119 */             k = j;
/* 120 */             i3 = 1;
/* 121 */             break;
/*     */           }
/*     */         }
/* 124 */         for (j = 0; j < distributions.length; j++) {
/* 125 */           if (str.compareTo(distributions[j]) == 0)
/*     */           {
/* 127 */             if (i4 != 0)
/* 128 */               die("RandomApp: only one distribution can be selected.");
/* 129 */             m = j;
/* 130 */             i4 = 1;
/* 131 */             break;
/*     */           }
/*     */         }
/*     */         
/*     */         try
/*     */         {
/* 137 */           n = Integer.parseInt(str);
/* 138 */           if (i6 != 0)
/* 139 */             die("RandomApp: only one number of random numbers can be selected.");
/* 140 */           i6 = 1;
/*     */         } catch (NumberFormatException localNumberFormatException3) {
/* 142 */           die("RandomApp: syntax error <" + str + ">");
/*     */         }
/*     */       }
/*     */     }
/* 146 */     Object localObject = null;
/*     */     
/* 148 */     if (k == RANMAR)
/*     */     {
/* 150 */       localObject = new Ranmar(l);
/* 151 */     } else if (k == RANECU)
/*     */     {
/* 153 */       localObject = new Ranecu(l);
/* 154 */     } else if (k == RANLUX)
/*     */     {
/* 156 */       localObject = new Ranlux(i1, l);
/* 157 */     } else if (k == RANJAVA)
/*     */     {
/* 159 */       localObject = new RandomJava();
/*     */     }
/*     */     
/* 162 */     for (i = 1; i <= n; i++) {
/*     */       double d;
/* 164 */       if (m == FLAT)
/*     */       {
/* 166 */         if (k != NULL) d = ((RandomElement)localObject).raw(); else {
/* 167 */           d = 0.0D;
/*     */         }
/*     */         
/*     */       }
/* 171 */       else if (k != NULL) d = ((RandomElement)localObject).gaussian(); else {
/* 172 */         d = 0.0D;
/*     */       }
/*     */       
/*     */ 
/* 176 */       if (i5 == 0) System.out.println(d);
/*     */     }
/*     */   }
/*     */   
/*     */   static double nullgen()
/*     */   {
/* 182 */     return 0.0D;
/*     */   }
/*     */ }


/* Location:              /home/menick/Scrivania/ProcessMining/CNMining/CNMining.jar!/edu/cornell/lassp/houle/RngPack/RandomApp.class
 * Java compiler version: 1 (45.3)
 * JD-Core Version:       0.7.1
 */