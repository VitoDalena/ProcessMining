/*     */ package hep.aida.ref;
/*     */ 
/*     */ import hep.aida.IAxis;
/*     */ import hep.aida.IHistogram;
/*     */ import hep.aida.IHistogram1D;
/*     */ import hep.aida.IHistogram2D;
/*     */ import java.io.IOException;
/*     */ import java.io.PrintWriter;
/*     */ import java.util.Random;
/*     */ 
/*     */ public class Test
/*     */ {
/*     */   public static void main(String[] paramArrayOfString)
/*     */   {
/*  15 */     Random localRandom = new Random();
/*  16 */     Histogram1D localHistogram1D = new Histogram1D("AIDA 1D Histogram", 40, -3.0D, 3.0D);
/*  17 */     for (int i = 0; i < 10000; i++) { localHistogram1D.fill(localRandom.nextGaussian());
/*     */     }
/*  19 */     Histogram2D localHistogram2D = new Histogram2D("AIDA 2D Histogram", 40, -3.0D, 3.0D, 40, -3.0D, 3.0D);
/*  20 */     for (int j = 0; j < 10000; j++) { localHistogram2D.fill(localRandom.nextGaussian(), localRandom.nextGaussian());
/*     */     }
/*     */     
/*  23 */     writeAsXML(localHistogram1D, "aida1.xml");
/*  24 */     writeAsXML(localHistogram2D, "aida2.xml");
/*     */     
/*     */ 
/*     */ 
/*  28 */     writeAsXML(localHistogram2D.projectionX(), "projectionX.xml");
/*  29 */     writeAsXML(localHistogram2D.projectionY(), "projectionY.xml");
/*     */   }
/*     */   
/*     */   private static void writeAsXML(IHistogram1D paramIHistogram1D, String paramString)
/*     */   {
/*     */     try {
/*  35 */       PrintWriter localPrintWriter = new PrintWriter(new java.io.FileWriter(paramString));
/*  36 */       localPrintWriter.println("<?xml version=\"1.0\" encoding=\"ISO-8859-1\" ?>");
/*  37 */       localPrintWriter.println("<!DOCTYPE plotML SYSTEM \"plotML.dtd\">");
/*  38 */       localPrintWriter.println("<plotML>");
/*  39 */       localPrintWriter.println("<plot>");
/*  40 */       localPrintWriter.println("<dataArea>");
/*  41 */       localPrintWriter.println("<data1d>");
/*  42 */       localPrintWriter.println("<bins1d title=\"" + paramIHistogram1D.title() + "\">");
/*  43 */       for (int i = 0; i < paramIHistogram1D.xAxis().bins(); i++)
/*     */       {
/*  45 */         localPrintWriter.println(paramIHistogram1D.binEntries(i) + "," + paramIHistogram1D.binError(i));
/*     */       }
/*  47 */       localPrintWriter.println("</bins1d>");
/*  48 */       localPrintWriter.print("<binnedDataAxisAttributes type=\"double\" axis=\"x0\"");
/*  49 */       localPrintWriter.print(" min=\"" + paramIHistogram1D.xAxis().lowerEdge() + "\"");
/*  50 */       localPrintWriter.print(" max=\"" + paramIHistogram1D.xAxis().upperEdge() + "\"");
/*  51 */       localPrintWriter.print(" numberOfBins=\"" + paramIHistogram1D.xAxis().bins() + "\"");
/*  52 */       localPrintWriter.println("/>");
/*  53 */       localPrintWriter.println("<statistics>");
/*  54 */       localPrintWriter.println("<statistic name=\"Entries\" value=\"" + paramIHistogram1D.entries() + "\"/>");
/*  55 */       localPrintWriter.println("<statistic name=\"Underflow\" value=\"" + paramIHistogram1D.binEntries(-2) + "\"/>");
/*  56 */       localPrintWriter.println("<statistic name=\"Overflow\" value=\"" + paramIHistogram1D.binEntries(-1) + "\"/>");
/*  57 */       if (!Double.isNaN(paramIHistogram1D.mean())) localPrintWriter.println("<statistic name=\"Mean\" value=\"" + paramIHistogram1D.mean() + "\"/>");
/*  58 */       if (!Double.isNaN(paramIHistogram1D.rms())) localPrintWriter.println("<statistic name=\"RMS\" value=\"" + paramIHistogram1D.rms() + "\"/>");
/*  59 */       localPrintWriter.println("</statistics>");
/*  60 */       localPrintWriter.println("</data1d>");
/*  61 */       localPrintWriter.println("</dataArea>");
/*  62 */       localPrintWriter.println("</plot>");
/*  63 */       localPrintWriter.println("</plotML>");
/*  64 */       localPrintWriter.close();
/*     */     } catch (IOException localIOException) {
/*  66 */       localIOException.printStackTrace();
/*     */     }
/*     */   }
/*     */   
/*     */   private static void writeAsXML(IHistogram2D paramIHistogram2D, String paramString) {
/*     */     try {
/*  72 */       PrintWriter localPrintWriter = new PrintWriter(new java.io.FileWriter(paramString));
/*  73 */       localPrintWriter.println("<?xml version=\"1.0\" encoding=\"ISO-8859-1\" ?>");
/*  74 */       localPrintWriter.println("<!DOCTYPE plotML SYSTEM \"plotML.dtd\">");
/*  75 */       localPrintWriter.println("<plotML>");
/*  76 */       localPrintWriter.println("<plot>");
/*  77 */       localPrintWriter.println("<dataArea>");
/*  78 */       localPrintWriter.println("<data2d type=\"xxx\">");
/*  79 */       localPrintWriter.println("<bins2d title=\"" + paramIHistogram2D.title() + "\" xSize=\"" + paramIHistogram2D.xAxis().bins() + "\" ySize=\"" + paramIHistogram2D.yAxis().bins() + "\">");
/*  80 */       for (int i = 0; i < paramIHistogram2D.xAxis().bins(); i++) {
/*  81 */         for (int j = 0; j < paramIHistogram2D.yAxis().bins(); j++)
/*     */         {
/*  83 */           localPrintWriter.println(paramIHistogram2D.binEntries(i, j) + "," + paramIHistogram2D.binError(i, j)); }
/*     */       }
/*  85 */       localPrintWriter.println("</bins2d>");
/*  86 */       localPrintWriter.print("<binnedDataAxisAttributes type=\"double\" axis=\"x0\"");
/*  87 */       localPrintWriter.print(" min=\"" + paramIHistogram2D.xAxis().lowerEdge() + "\"");
/*  88 */       localPrintWriter.print(" max=\"" + paramIHistogram2D.xAxis().upperEdge() + "\"");
/*  89 */       localPrintWriter.print(" numberOfBins=\"" + paramIHistogram2D.xAxis().bins() + "\"");
/*  90 */       localPrintWriter.println("/>");
/*  91 */       localPrintWriter.print("<binnedDataAxisAttributes type=\"double\" axis=\"y0\"");
/*  92 */       localPrintWriter.print(" min=\"" + paramIHistogram2D.yAxis().lowerEdge() + "\"");
/*  93 */       localPrintWriter.print(" max=\"" + paramIHistogram2D.yAxis().upperEdge() + "\"");
/*  94 */       localPrintWriter.print(" numberOfBins=\"" + paramIHistogram2D.yAxis().bins() + "\"");
/*  95 */       localPrintWriter.println("/>");
/*     */       
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/* 103 */       localPrintWriter.println("</data2d>");
/* 104 */       localPrintWriter.println("</dataArea>");
/* 105 */       localPrintWriter.println("</plot>");
/* 106 */       localPrintWriter.println("</plotML>");
/* 107 */       localPrintWriter.close();
/*     */     } catch (IOException localIOException) {
/* 109 */       localIOException.printStackTrace();
/*     */     }
/*     */   }
/*     */ }


/* Location:              /home/menick/Scrivania/ProcessMining/CNMining/CNMining.jar!/hep/aida/ref/Test.class
 * Java compiler version: 1 (45.3)
 * JD-Core Version:       0.7.1
 */