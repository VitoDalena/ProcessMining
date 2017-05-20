/*    */ package cern.jet.stat.quantile;
/*    */ 
/*    */ import cern.jet.random.engine.DRand;
/*    */ import edu.cornell.lassp.houle.RngPack.RandomElement;
/*    */ import hep.aida.bin.DynamicBin1D;
/*    */ import hep.aida.bin.QuantileBin1D;
/*    */ import hep.aida.bin.StaticBin1D;
/*    */ import java.io.PrintStream;
/*    */ import java.text.DecimalFormat;
/*    */ import java.text.NumberFormat;
/*    */ import java.util.Date;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class Quantile1Test
/*    */ {
/*    */   public static void main(String[] paramArrayOfString)
/*    */   {
/* 25 */     int i = 0;
/*    */     try {
/* 27 */       i = Integer.parseInt(paramArrayOfString[0]);
/*    */     }
/*    */     catch (Exception localException1) {
/* 30 */       System.err.println("Unable to parse input line count argument");
/* 31 */       System.err.println(localException1.getMessage());
/* 32 */       System.exit(1);
/*    */     }
/* 34 */     System.out.println("Got numExamples=" + i);
/*    */     
/*    */ 
/*    */ 
/*    */ 
/* 39 */     long l = 0L;
/*    */     try {
/* 41 */       if (paramArrayOfString[1].equals("L")) {
/* 42 */         l = Long.MAX_VALUE;
/* 43 */       } else if (paramArrayOfString[1].equals("I")) {
/* 44 */         l = 2147483647L;
/*    */       } else {
/* 46 */         l = Long.parseLong(paramArrayOfString[1]);
/*    */       }
/*    */     }
/*    */     catch (Exception localException2) {
/* 50 */       System.err.println("Error parsing flag for N");
/* 51 */       System.err.println(localException2.getMessage());
/* 52 */       System.exit(1);
/*    */     }
/* 54 */     System.out.println("Got N=" + l);
/*    */     
/*    */ 
/*    */ 
/*    */ 
/* 59 */     DRand localDRand1 = new DRand(new Date());
/* 60 */     QuantileBin1D localQuantileBin1D = new QuantileBin1D(false, l, 1.0E-4D, 0.001D, 200, localDRand1, false, false, 2);
/*    */     
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/* 70 */     DynamicBin1D localDynamicBin1D = new DynamicBin1D();
/*    */     
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/* 76 */     DRand localDRand2 = new DRand(7757);
/* 77 */     for (int j = 1; j <= i; j++) {
/* 78 */       double d1 = localDRand2.gaussian();
/* 79 */       localQuantileBin1D.add(d1);
/* 80 */       localDynamicBin1D.add(d1);
/*    */     }
/*    */     
/*    */ 
/*    */ 
/*    */ 
/* 86 */     DecimalFormat localDecimalFormat = new DecimalFormat("0.00");
/* 87 */     System.out.println();
/*    */     
/* 89 */     int k = 10;
/* 90 */     for (int m = 1; m < 100;) {
/* 91 */       double d2 = m * 0.01D;
/* 92 */       double d3 = localQuantileBin1D.quantile(d2);
/* 93 */       System.out.println(localDecimalFormat.format(d2) + "  " + d3 + ",  " + localDynamicBin1D.quantile(d2) + ",  " + (localDynamicBin1D.quantile(d2) - d3));
/* 94 */       m += k;
/*    */     }
/*    */   }
/*    */ }


/* Location:              /home/menick/Scrivania/ProcessMining/CNMining/CNMining.jar!/cern/jet/stat/quantile/Quantile1Test.class
 * Java compiler version: 1 (45.3)
 * JD-Core Version:       0.7.1
 */