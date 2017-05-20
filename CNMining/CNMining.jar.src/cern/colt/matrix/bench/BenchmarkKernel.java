/*    */ package cern.colt.matrix.bench;
/*    */ 
/*    */ import cern.colt.matrix.ObjectMatrix1D;
/*    */ import cern.colt.matrix.ObjectMatrix2D;
/*    */ import cern.colt.matrix.impl.AbstractFormatter;
/*    */ import cern.colt.matrix.impl.DenseObjectMatrix2D;
/*    */ import cern.colt.matrix.objectalgo.Formatter;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ class BenchmarkKernel
/*    */ {
/*    */   public static float run(double paramDouble, TimerProcedure paramTimerProcedure)
/*    */   {
/* 26 */     long l1 = 0L;
/* 27 */     long l2 = (paramDouble * 1000.0D);
/* 28 */     long l3 = System.currentTimeMillis();
/* 29 */     long l4 = l3 + l2;
/* 30 */     while (System.currentTimeMillis() < l4) {
/* 31 */       paramTimerProcedure.init();
/* 32 */       paramTimerProcedure.apply(null);
/* 33 */       l1 += 1L;
/*    */     }
/* 35 */     long l5 = System.currentTimeMillis();
/* 36 */     if (paramDouble / l1 < 0.1D)
/*    */     {
/*    */ 
/*    */ 
/*    */ 
/* 41 */       l3 = System.currentTimeMillis();
/* 42 */       for (l6 = l1; --l6 >= 0L;) {
/* 43 */         paramTimerProcedure.init();
/* 44 */         paramTimerProcedure.apply(null);
/*    */       }
/* 46 */       l5 = System.currentTimeMillis();
/*    */     }
/*    */     
/* 49 */     long l6 = System.currentTimeMillis();
/* 50 */     int i = 1;
/* 51 */     for (long l7 = l1; --l7 >= 0L;) {
/* 52 */       i = (int)(i * l7);
/* 53 */       paramTimerProcedure.init();
/*    */     }
/* 55 */     long l8 = System.currentTimeMillis();
/* 56 */     long l9 = l5 - l3 - (l8 - l6);
/*    */     
/*    */ 
/* 59 */     return (float)l9 / 1000.0F / (float)l1;
/*    */   }
/*    */   
/*    */ 
/*    */   public static String systemInfo()
/*    */   {
/* 65 */     String[] arrayOfString = { "java.vm.vendor", "java.vm.version", "java.vm.name", "os.name", "os.version", "os.arch", "java.version", "java.vendor", "java.vendor.url" };
/*    */     
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/* 86 */     DenseObjectMatrix2D localDenseObjectMatrix2D = new DenseObjectMatrix2D(arrayOfString.length, 2);
/* 87 */     localDenseObjectMatrix2D.viewColumn(0).assign(arrayOfString);
/*    */     
/*    */ 
/* 90 */     for (int i = 0; i < arrayOfString.length; i++) {
/* 91 */       localObject = System.getProperty(arrayOfString[i]);
/* 92 */       if (localObject == null) localObject = "?";
/* 93 */       localDenseObjectMatrix2D.set(i, 1, localObject);
/*    */     }
/*    */     
/*    */ 
/* 97 */     Object localObject = new Formatter();
/* 98 */     ((AbstractFormatter)localObject).setPrintShape(false);
/* 99 */     return ((Formatter)localObject).toString(localDenseObjectMatrix2D);
/*    */   }
/*    */ }


/* Location:              /home/menick/Scrivania/ProcessMining/CNMining/CNMining.jar!/cern/colt/matrix/bench/BenchmarkKernel.class
 * Java compiler version: 1 (45.3)
 * JD-Core Version:       0.7.1
 */