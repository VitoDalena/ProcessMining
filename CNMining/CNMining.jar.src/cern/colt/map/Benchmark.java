/*     */ package cern.colt.map;
/*     */ 
/*     */ import cern.colt.Timer;
/*     */ import cern.jet.random.Uniform;
/*     */ import cern.jet.random.engine.MersenneTwister;
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
/*     */ public class Benchmark
/*     */ {
/*     */   public static void benchmark(int paramInt1, int paramInt2, String paramString)
/*     */   {
/*  28 */     System.out.println("initializing...");
/*  29 */     QuickOpenIntIntHashMap localQuickOpenIntIntHashMap = new QuickOpenIntIntHashMap();
/*     */     
/*     */ 
/*  32 */     for (int i = 0; i < paramInt2; i++) {
/*  33 */       localQuickOpenIntIntHashMap.put(i, i);
/*     */     }
/*  35 */     Runtime.getRuntime().gc();
/*  36 */     try { Thread.currentThread();Thread.sleep(1000L);
/*     */     }
/*     */     catch (InterruptedException localInterruptedException) {}
/*  39 */     System.out.println("Now benchmarking...");
/*  40 */     int j = 0;
/*  41 */     Timer localTimer1 = new Timer();
/*  42 */     Timer localTimer2 = new Timer();
/*  43 */     Timer localTimer3 = new Timer();
/*     */     
/*  45 */     int k = paramInt1;
/*  46 */     do { int m; if (paramString.equals("add")) {
/*  47 */         localQuickOpenIntIntHashMap.clear();
/*     */         
/*  49 */         localTimer1.start();
/*  50 */         m = paramInt2;
/*  51 */         do { localQuickOpenIntIntHashMap.put(m, m);m--;
/*  50 */         } while (m >= 0);
/*     */         
/*     */ 
/*  53 */         localTimer1.stop();
/*     */       }
/*  55 */       if (paramString.equals("get")) {
/*  56 */         localTimer1.start();
/*  57 */         m = paramInt2;
/*  58 */         do { j += localQuickOpenIntIntHashMap.get(m);m--;
/*  57 */         } while (m >= 0);
/*     */         
/*     */ 
/*  60 */         localTimer1.stop();
/*     */       }
/*     */       else {
/*  63 */         localTimer2.start();
/*  64 */         localQuickOpenIntIntHashMap.rehash(PrimeFinder.nextPrime(paramInt2 * 2));
/*  65 */         localTimer2.stop();
/*     */         
/*  67 */         localTimer3.start();
/*  68 */         localQuickOpenIntIntHashMap.rehash(PrimeFinder.nextPrime((int)(paramInt2 * 1.5D)));
/*  69 */         localTimer3.stop();
/*     */       }
/*  45 */       k--; } while (k >= 0);
/*     */     
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*  73 */     System.out.println("adding: " + localTimer1);
/*  74 */     System.out.println("growing: " + localTimer2);
/*  75 */     System.out.println("shrinking: " + localTimer3);
/*  76 */     System.out.println("total: " + localTimer2.plus(localTimer3));
/*     */     
/*  78 */     System.out.print(j);
/*     */   }
/*     */   
/*     */ 
/*     */   public static void main(String[] paramArrayOfString)
/*     */   {
/*  84 */     int i = Integer.parseInt(paramArrayOfString[0]);
/*  85 */     int j = Integer.parseInt(paramArrayOfString[1]);
/*     */     
/*  87 */     String str = paramArrayOfString[2];
/*  88 */     benchmark(i, j, str);
/*     */   }
/*     */   
/*     */   public static void test2(int paramInt)
/*     */   {
/*  93 */     Uniform localUniform = new Uniform(new MersenneTwister());
/*     */     
/*     */ 
/*     */ 
/*     */ 
/*  98 */     int[] arrayOfInt1 = new int[paramInt];
/*  99 */     int i = 10000000;
/* 100 */     for (int j = 0; j < paramInt; j++) {
/* 101 */       arrayOfInt1[j] = localUniform.nextIntFromTo(0, i);
/*     */     }
/* 103 */     int[] arrayOfInt2 = (int[])arrayOfInt1.clone();
/*     */     
/* 105 */     int k = arrayOfInt1.length;
/*     */     
/* 107 */     OpenIntIntHashMap localOpenIntIntHashMap = new OpenIntIntHashMap();
/*     */     
/* 109 */     for (int m = 0; m < arrayOfInt1.length; m++) {
/* 110 */       localOpenIntIntHashMap.put(arrayOfInt1[m], arrayOfInt2[m]);
/*     */     }
/*     */     
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/* 136 */     int n = 0;
/* 137 */     for (int i1 = 0; i1 < arrayOfInt1.length; i1++) {
/* 138 */       n += localOpenIntIntHashMap.get(arrayOfInt1[i1]);
/*     */     }
/*     */     
/*     */ 
/*     */ 
/* 143 */     System.out.println(localOpenIntIntHashMap);
/* 144 */     System.out.println(n);
/* 145 */     System.out.println("\n\n");
/*     */   }
/*     */ }


/* Location:              /home/menick/Scrivania/ProcessMining/CNMining/CNMining.jar!/cern/colt/map/Benchmark.class
 * Java compiler version: 1 (45.3)
 * JD-Core Version:       0.7.1
 */