/*    */ package hep.aida.ref;
/*    */ 
/*    */ import hep.aida.IHistogram1D;
/*    */ import hep.aida.IHistogram2D;
/*    */ import hep.aida.IHistogram3D;
/*    */ import java.io.PrintStream;
/*    */ import java.util.Random;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class Test2
/*    */ {
/*    */   public static void main(String[] paramArrayOfString)
/*    */   {
/* 16 */     Random localRandom = new Random();
/* 17 */     Histogram1D localHistogram1D = new Histogram1D("AIDA 1D Histogram", 40, -3.0D, 3.0D);
/* 18 */     for (int i = 0; i < 10000; i++) { localHistogram1D.fill(localRandom.nextGaussian());
/*    */     }
/* 20 */     Histogram2D localHistogram2D = new Histogram2D("AIDA 2D Histogram", 40, -3.0D, 3.0D, 40, -3.0D, 3.0D);
/* 21 */     for (int j = 0; j < 10000; j++) { localHistogram2D.fill(localRandom.nextGaussian(), localRandom.nextGaussian());
/*    */     }
/*    */     
/* 24 */     writeAsXML(localHistogram1D, "aida1.xml");
/* 25 */     writeAsXML(localHistogram2D, "aida2.xml");
/*    */     
/*    */ 
/*    */ 
/* 29 */     writeAsXML(localHistogram2D.projectionX(), "projectionX.xml");
/* 30 */     writeAsXML(localHistogram2D.projectionY(), "projectionY.xml");
/*    */   }
/*    */   
/*    */   public static void main2(String[] paramArrayOfString) {
/* 34 */     double[] arrayOfDouble = { -30.0D, 0.0D, 30.0D, 1000.0D };
/* 35 */     Random localRandom = new Random();
/* 36 */     Histogram1D localHistogram1D = new Histogram1D("AIDA 1D Histogram", new VariableAxis(arrayOfDouble));
/*    */     
/* 38 */     for (int i = 0; i < 10000; i++) { localHistogram1D.fill(localRandom.nextGaussian());
/*    */     }
/* 40 */     Histogram2D localHistogram2D = new Histogram2D("AIDA 2D Histogram", new VariableAxis(arrayOfDouble), new VariableAxis(arrayOfDouble));
/*    */     
/* 42 */     for (int j = 0; j < 10000; j++) { localHistogram2D.fill(localRandom.nextGaussian(), localRandom.nextGaussian());
/*    */     }
/*    */     
/* 45 */     Histogram3D localHistogram3D = new Histogram3D("AIDA 3D Histogram", 10, -2.0D, 2.0D, 5, -2.0D, 2.0D, 3, -2.0D, 2.0D);
/* 46 */     for (int k = 0; k < 10000; k++) { localHistogram3D.fill(localRandom.nextGaussian(), localRandom.nextGaussian(), localRandom.nextGaussian());
/*    */     }
/*    */     
/* 49 */     writeAsXML(localHistogram1D, "aida1.xml");
/* 50 */     writeAsXML(localHistogram2D, "aida2.xml");
/* 51 */     writeAsXML(localHistogram3D, "aida2.xml");
/*    */     
/*    */ 
/*    */ 
/* 55 */     writeAsXML(localHistogram2D.projectionX(), "projectionX.xml");
/* 56 */     writeAsXML(localHistogram2D.projectionY(), "projectionY.xml");
/*    */   }
/*    */   
/*    */   private static void writeAsXML(IHistogram1D paramIHistogram1D, String paramString) {
/* 60 */     System.out.println(new Converter().toString(paramIHistogram1D));
/*    */   }
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
/*    */   private static void writeAsXML(IHistogram2D paramIHistogram2D, String paramString)
/*    */   {
/* 74 */     System.out.println(new Converter().toString(paramIHistogram2D));
/*    */   }
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
/*    */   private static void writeAsXML(IHistogram3D paramIHistogram3D, String paramString)
/*    */   {
/* 88 */     System.out.println(new Converter().toString(paramIHistogram3D));
/*    */   }
/*    */ }


/* Location:              /home/menick/Scrivania/ProcessMining/CNMining/CNMining.jar!/hep/aida/ref/Test2.class
 * Java compiler version: 1 (45.3)
 * JD-Core Version:       0.7.1
 */