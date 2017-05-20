/*     */ package cern.jet.random;
/*     */ 
/*     */ import cern.colt.PersistentObject;
/*     */ import cern.colt.Timer;
/*     */ import cern.colt.list.DoubleArrayList;
/*     */ import cern.colt.list.IntArrayList;
/*     */ import cern.jet.random.engine.MersenneTwister;
/*     */ import edu.cornell.lassp.houle.RngPack.RandomElement;
/*     */ import hep.aida.bin.AbstractBin1D;
/*     */ import hep.aida.bin.DynamicBin1D;
/*     */ import java.io.PrintStream;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class Benchmark
/*     */   extends PersistentObject
/*     */ {
/*     */   protected RandomElement randomGenerator;
/*     */   
/*     */   protected Benchmark()
/*     */   {
/*  25 */     throw new RuntimeException("Non instantiable");
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public static void demo1()
/*     */   {
/*  34 */     double d1 = 5.0D;
/*  35 */     double d2 = 1.5D;
/*  36 */     double d3 = d1 * d1 / d2;
/*  37 */     double d4 = 1.0D / (d2 / d1);
/*     */     
/*     */ 
/*  40 */     MersenneTwister localMersenneTwister = new MersenneTwister();
/*     */     
/*     */ 
/*  43 */     Gamma localGamma = new Gamma(d3, d4, localMersenneTwister);
/*     */     
/*     */ 
/*  46 */     int i = 100000;
/*  47 */     DoubleArrayList localDoubleArrayList = new DoubleArrayList(i);
/*  48 */     for (int j = 0; j < i; j++) { localDoubleArrayList.add(localGamma.nextDouble());
/*     */     }
/*  50 */     DynamicBin1D localDynamicBin1D = new DynamicBin1D();
/*  51 */     localDynamicBin1D.addAllOf(localDoubleArrayList);
/*  52 */     System.out.println(localDynamicBin1D);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   public static void main(String[] paramArrayOfString)
/*     */   {
/*  60 */     int i = Integer.parseInt(paramArrayOfString[0]);
/*  61 */     boolean bool = new Boolean(paramArrayOfString[1]).booleanValue();
/*  62 */     double d = new Double(paramArrayOfString[2]).doubleValue();
/*  63 */     String str = paramArrayOfString[3];
/*  64 */     random(i, bool, d, str);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public static void random(int paramInt, boolean paramBoolean, double paramDouble, String paramString)
/*     */   {
/*  73 */     System.out.println("Generating " + paramInt + " random numbers per distribution...\n");
/*     */     
/*     */ 
/*  76 */     int i = 100;
/*     */     RandomElement localRandomElement;
/*     */     try {
/*  79 */       localRandomElement = (RandomElement)Class.forName(paramString).newInstance();
/*     */     } catch (Exception localException) {
/*  81 */       throw new InternalError(localException.getMessage());
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
/* 102 */     randomInstance(paramInt, paramBoolean, new Poisson(paramDouble, (RandomElement)localRandomElement.clone()));
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
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public static void randomInstance(int paramInt, boolean paramBoolean, AbstractDistribution paramAbstractDistribution)
/*     */   {
/* 154 */     System.out.print("\n" + paramAbstractDistribution + " ...");
/* 155 */     Timer localTimer = new Timer().start();
/*     */     
/* 157 */     int i = paramInt;
/* 158 */     do { double d = paramAbstractDistribution.nextDouble();
/* 159 */       if (paramBoolean) {
/* 160 */         if ((paramInt - i - 1) % 8 == 0) System.out.println();
/* 161 */         System.out.print((float)d + ", ");
/*     */       }
/* 157 */       i--; } while (i >= 0);
/*     */     
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/* 165 */     localTimer.stop();
/* 166 */     System.out.println("\n" + localTimer);
/*     */   }
/*     */   
/*     */ 
/*     */   public static void test(int paramInt, AbstractDistribution paramAbstractDistribution)
/*     */   {
/* 172 */     int i = 0;int j = paramInt;
/* 173 */     do { System.out.print(" " + paramAbstractDistribution.nextDouble());
/* 174 */       if (i % 8 == 7) System.out.println();
/* 172 */       i++;j--; } while (j >= 0);
/*     */     
/*     */ 
/*     */ 
/* 176 */     System.out.println("\n\nGood bye.\n");
/*     */   }
/*     */   
/*     */ 
/*     */   public static void test2(int paramInt, AbstractDistribution paramAbstractDistribution)
/*     */   {
/* 182 */     DynamicBin1D localDynamicBin1D = new DynamicBin1D();
/* 183 */     int i = 0;int j = paramInt;
/* 184 */     do { localDynamicBin1D.add(paramAbstractDistribution.nextDouble());i++;j--;
/* 183 */     } while (j >= 0);
/*     */     
/*     */ 
/* 186 */     System.out.println(localDynamicBin1D);
/* 187 */     System.out.println("\n\nGood bye.\n");
/*     */   }
/*     */   
/*     */ 
/*     */   public static void test2(int paramInt, AbstractDistribution paramAbstractDistribution1, AbstractDistribution paramAbstractDistribution2)
/*     */   {
/* 193 */     DynamicBin1D localDynamicBin1D1 = new DynamicBin1D();
/* 194 */     DynamicBin1D localDynamicBin1D2 = new DynamicBin1D();
/* 195 */     int i = 0;int j = paramInt;
/* 196 */     do { localDynamicBin1D1.add(paramAbstractDistribution1.nextDouble());
/* 197 */       localDynamicBin1D2.add(paramAbstractDistribution2.nextDouble());i++;j--;
/* 195 */     } while (j >= 0);
/*     */     
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/* 203 */     System.out.println("\n\nBenchmarking frequencies...\n");
/* 204 */     IntArrayList localIntArrayList = new IntArrayList();
/* 205 */     DoubleArrayList localDoubleArrayList = new DoubleArrayList();
/* 206 */     Timer localTimer = new Timer();
/* 207 */     localTimer.reset();
/* 208 */     localTimer.start();
/* 209 */     localDynamicBin1D1.frequencies(localDoubleArrayList, localIntArrayList);
/* 210 */     localTimer.stop().display();
/*     */     
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/* 238 */     System.out.println("\n\nGood bye.\n");
/*     */   }
/*     */ }


/* Location:              /home/menick/Scrivania/ProcessMining/CNMining/CNMining.jar!/cern/jet/random/Benchmark.class
 * Java compiler version: 1 (45.3)
 * JD-Core Version:       0.7.1
 */