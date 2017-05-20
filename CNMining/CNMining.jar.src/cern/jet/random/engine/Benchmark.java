/*     */ package cern.jet.random.engine;
/*     */ 
/*     */ import cern.colt.PersistentObject;
/*     */ import cern.colt.Timer;
/*     */ import edu.cornell.lassp.houle.RngPack.RandomElement;
/*     */ import edu.cornell.lassp.houle.RngPack.Ranecu;
/*     */ import edu.cornell.lassp.houle.RngPack.Ranlux;
/*     */ import edu.cornell.lassp.houle.RngPack.Ranmar;
/*     */ import java.io.PrintStream;
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
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
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
/*     */   protected Benchmark()
/*     */   {
/*  87 */     throw new RuntimeException("Non instantiable");
/*     */   }
/*     */   
/*     */ 
/*     */   public static void benchmark(int paramInt)
/*     */   {
/*  93 */     Timer localTimer = new Timer();
/*     */     
/*     */ 
/*  96 */     localTimer.reset().start();
/*  97 */     int i = paramInt; do { i--; } while (i >= 0);
/*  98 */     localTimer.stop().display();
/*  99 */     float f = localTimer.elapsedTime();
/* 100 */     System.out.println("empty loop timing done.");
/*     */     
/* 102 */     Object localObject = new MersenneTwister();
/* 103 */     System.out.println("\n MersenneTwister:");
/* 104 */     localTimer.reset().start();
/* 105 */     int j = paramInt; do { ((RandomElement)localObject).raw();j--; } while (j >= 0);
/* 106 */     localTimer.stop().display();
/* 107 */     System.out.println(paramInt / (localTimer.elapsedTime() - f) + " numbers per second.");
/*     */     
/*     */ 
/* 110 */     localObject = new MersenneTwister64();
/* 111 */     System.out.println("\n MersenneTwister64:");
/* 112 */     localTimer.reset().start();
/* 113 */     int k = paramInt; do { ((RandomElement)localObject).raw();k--; } while (k >= 0);
/* 114 */     localTimer.stop().display();
/* 115 */     System.out.println(paramInt / (localTimer.elapsedTime() - f) + " numbers per second.");
/*     */     
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/* 127 */     localObject = new DRand();
/* 128 */     System.out.println("\nDRand:");
/* 129 */     localTimer.reset().start();
/* 130 */     int m = paramInt; do { ((RandomElement)localObject).raw();m--; } while (m >= 0);
/* 131 */     localTimer.stop().display();
/* 132 */     System.out.println(paramInt / (localTimer.elapsedTime() - f) + " numbers per second.");
/*     */     
/*     */ 
/* 135 */     Random localRandom = new Random();
/* 136 */     System.out.println("\njava.util.Random.nextFloat():");
/* 137 */     localTimer.reset().start();
/* 138 */     int n = paramInt; do { localRandom.nextFloat();n--; } while (n >= 0);
/* 139 */     localTimer.stop().display();
/* 140 */     System.out.println(paramInt / (localTimer.elapsedTime() - f) + " numbers per second.");
/*     */     
/* 142 */     localObject = new Ranecu();
/* 143 */     System.out.println("\nRanecu:");
/* 144 */     localTimer.reset().start();
/* 145 */     int i1 = paramInt; do { ((RandomElement)localObject).raw();i1--; } while (i1 >= 0);
/* 146 */     localTimer.stop().display();
/* 147 */     System.out.println(paramInt / (localTimer.elapsedTime() - f) + " numbers per second.");
/*     */     
/* 149 */     localObject = new Ranmar();
/* 150 */     System.out.println("\nRanmar:");
/* 151 */     localTimer.reset().start();
/* 152 */     int i2 = paramInt; do { ((RandomElement)localObject).raw();i2--; } while (i2 >= 0);
/* 153 */     localTimer.stop().display();
/* 154 */     System.out.println(paramInt / (localTimer.elapsedTime() - f) + " numbers per second.");
/*     */     
/* 156 */     localObject = new Ranlux();
/* 157 */     System.out.println("\nRanlux:");
/* 158 */     localTimer.reset().start();
/* 159 */     int i3 = paramInt; do { ((RandomElement)localObject).raw();i3--; } while (i3 >= 0);
/* 160 */     localTimer.stop().display();
/* 161 */     System.out.println(paramInt / (localTimer.elapsedTime() - f) + " numbers per second.");
/*     */     
/*     */ 
/* 164 */     System.out.println("\nGood bye.\n");
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */   public static void main(String[] paramArrayOfString)
/*     */   {
/* 171 */     long l1 = Long.parseLong(paramArrayOfString[0]);
/* 172 */     long l2 = Long.parseLong(paramArrayOfString[1]);
/* 173 */     int i = Integer.parseInt(paramArrayOfString[2]);
/* 174 */     int j = Integer.parseInt(paramArrayOfString[3]);
/*     */     
/*     */ 
/*     */ 
/* 178 */     for (int k = 0; k < j; k++) {
/* 179 */       benchmark(i);
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
/*     */   public static void test(int paramInt, RandomEngine paramRandomEngine)
/*     */   {
/* 212 */     System.out.println("\n\nint():");
/* 213 */     RandomEngine localRandomEngine = (RandomEngine)paramRandomEngine.clone();
/* 214 */     int i = 0;int j = paramInt;
/* 215 */     do { System.out.print(" " + localRandomEngine.nextInt());
/* 216 */       if (i % 8 == 7) System.out.println();
/* 214 */       i++;j--; } while (j >= 0);
/*     */     
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/* 220 */     System.out.println("\n\nGood bye.\n");
/*     */   }
/*     */   
/*     */ 
/*     */   private static void xtestRandomFromTo(long paramLong1, long paramLong2, int paramInt)
/*     */   {
/* 226 */     System.out.println("from=" + paramLong1 + ", to=" + paramLong2);
/*     */     
/*     */ 
/*     */ 
/* 230 */     Random localRandom = new Random();
/*     */     
/*     */ 
/*     */ 
/* 234 */     MersenneTwister localMersenneTwister = new MersenneTwister();
/* 235 */     int i = (int)paramLong1;int j = (int)paramLong2;
/* 236 */     Timer localTimer = new Timer().start();
/* 237 */     int k = 0;int m = paramInt;
/*     */     do
/*     */     {
/* 240 */       System.out.print(" " + localMersenneTwister.raw());
/* 241 */       if (k % 8 == 7) System.out.println();
/* 237 */       k++;m--; } while (m >= 0);
/*     */     
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/* 245 */     localTimer.stop().display();
/*     */     
/* 247 */     System.out.println("Good bye.\n");
/*     */   }
/*     */ }


/* Location:              /home/menick/Scrivania/ProcessMining/CNMining/CNMining.jar!/cern/jet/random/engine/Benchmark.class
 * Java compiler version: 1 (45.3)
 * JD-Core Version:       0.7.1
 */