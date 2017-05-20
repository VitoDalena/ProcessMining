/*     */ package uk.ac.shef.wit.simmetrics;
/*     */ 
/*     */ import java.io.PrintStream;
/*     */ import java.text.DecimalFormat;
/*     */ import java.util.Vector;
/*     */ import uk.ac.shef.wit.simmetrics.similaritymetrics.AbstractStringMetric;
/*     */ import uk.ac.shef.wit.simmetrics.similaritymetrics.BlockDistance;
/*     */ import uk.ac.shef.wit.simmetrics.similaritymetrics.ChapmanLengthDeviation;
/*     */ import uk.ac.shef.wit.simmetrics.similaritymetrics.ChapmanMatchingSoundex;
/*     */ import uk.ac.shef.wit.simmetrics.similaritymetrics.ChapmanMeanLength;
/*     */ import uk.ac.shef.wit.simmetrics.similaritymetrics.CosineSimilarity;
/*     */ import uk.ac.shef.wit.simmetrics.similaritymetrics.DiceSimilarity;
/*     */ import uk.ac.shef.wit.simmetrics.similaritymetrics.EuclideanDistance;
/*     */ import uk.ac.shef.wit.simmetrics.similaritymetrics.InterfaceStringMetric;
/*     */ import uk.ac.shef.wit.simmetrics.similaritymetrics.JaccardSimilarity;
/*     */ import uk.ac.shef.wit.simmetrics.similaritymetrics.Jaro;
/*     */ import uk.ac.shef.wit.simmetrics.similaritymetrics.JaroWinkler;
/*     */ import uk.ac.shef.wit.simmetrics.similaritymetrics.Levenshtein;
/*     */ import uk.ac.shef.wit.simmetrics.similaritymetrics.MatchingCoefficient;
/*     */ import uk.ac.shef.wit.simmetrics.similaritymetrics.MongeElkan;
/*     */ import uk.ac.shef.wit.simmetrics.similaritymetrics.NeedlemanWunch;
/*     */ import uk.ac.shef.wit.simmetrics.similaritymetrics.OverlapCoefficient;
/*     */ import uk.ac.shef.wit.simmetrics.similaritymetrics.QGramsDistance;
/*     */ import uk.ac.shef.wit.simmetrics.similaritymetrics.SmithWaterman;
/*     */ import uk.ac.shef.wit.simmetrics.similaritymetrics.SmithWatermanGotoh;
/*     */ import uk.ac.shef.wit.simmetrics.similaritymetrics.SmithWatermanGotohWindowedAffine;
/*     */ import uk.ac.shef.wit.simmetrics.similaritymetrics.Soundex;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public final class TestEstimatedTimes
/*     */ {
/*     */   private static final int TESTTIMINGMILLISECONDSPERTEST = 200;
/*     */   private static final int TESTMAXLENGTHTIMINGTEST = 2000;
/*     */   private static final int TESTMAXLENGTHTIMINGSTEPSIZE = 50;
/*     */   private static final int TESTMAXLENGTHTIMINGTERMLENGTH = 10;
/*     */   
/*     */   public static void main(String[] args)
/*     */   {
/*  87 */     Vector<InterfaceStringMetric> testMetricVector = new Vector();
/*  88 */     testMetricVector.add(new Levenshtein());
/*  89 */     testMetricVector.add(new NeedlemanWunch());
/*  90 */     testMetricVector.add(new SmithWaterman());
/*  91 */     testMetricVector.add(new ChapmanLengthDeviation());
/*  92 */     testMetricVector.add(new ChapmanMeanLength());
/*  93 */     testMetricVector.add(new SmithWatermanGotoh());
/*  94 */     testMetricVector.add(new SmithWatermanGotohWindowedAffine());
/*  95 */     testMetricVector.add(new BlockDistance());
/*  96 */     testMetricVector.add(new MongeElkan());
/*  97 */     testMetricVector.add(new Jaro());
/*  98 */     testMetricVector.add(new JaroWinkler());
/*  99 */     testMetricVector.add(new Soundex());
/* 100 */     testMetricVector.add(new ChapmanMatchingSoundex());
/* 101 */     testMetricVector.add(new MatchingCoefficient());
/* 102 */     testMetricVector.add(new DiceSimilarity());
/* 103 */     testMetricVector.add(new JaccardSimilarity());
/* 104 */     testMetricVector.add(new OverlapCoefficient());
/* 105 */     testMetricVector.add(new EuclideanDistance());
/* 106 */     testMetricVector.add(new CosineSimilarity());
/* 107 */     testMetricVector.add(new QGramsDistance());
/*     */     
/*     */ 
/* 110 */     testMethod(testMetricVector, args);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   private static void testMethod(Vector metricVector, String[] args)
/*     */   {
/* 121 */     System.out.println("Usage: testMethod ");
/* 122 */     System.out.println("AS NO INPUT - running defualt test\n");
/*     */     
/*     */ 
/* 125 */     System.out.println("Performing Tests with Following Metrics:");
/* 126 */     for (int i = 0; i < metricVector.size(); i++) {
/* 127 */       System.out.println("m" + (i + 1) + " " + ((AbstractStringMetric)metricVector.get(i)).getShortDescriptionString());
/*     */     }
/* 129 */     System.out.println();
/*     */     
/*     */ 
/* 132 */     System.out.print("\n");
/* 133 */     DecimalFormat df = new DecimalFormat("0.00");
/* 134 */     int metricTests = 0;
/* 135 */     long totalTime = System.currentTimeMillis();
/* 136 */     for (int i = 0; i < metricVector.size(); i++) {
/* 137 */       AbstractStringMetric metric = (AbstractStringMetric)metricVector.get(i);
/* 138 */       System.out.print("m" + (i + 1) + "\t");
/*     */       
/*     */ 
/* 141 */       StringBuffer testString = new StringBuffer();
/* 142 */       int termLen = 0;
/* 143 */       for (int len = 1; len < 2000; termLen++) {
/* 144 */         if (termLen < 10) {
/* 145 */           testString.append((char)(97 + (int)(Math.random() * 25.0D)));
/*     */         } else {
/* 147 */           testString.append(' ');
/* 148 */           termLen = 0;
/*     */         }
/* 143 */         len++;
/*     */       }
/*     */       
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/* 152 */       for (int len = 1; len < 2000; len += 50) {
/* 153 */         long timeTaken = 0L;
/* 154 */         int iterations = 0;
/* 155 */         String input1 = testString.substring(0, len);
/* 156 */         while (timeTaken < 200L) {
/* 157 */           timeTaken += metric.getSimilarityTimingActual(input1, input1);
/* 158 */           iterations++;
/* 159 */           metricTests++;
/*     */         }
/* 161 */         System.out.print(df.format((float)timeTaken / iterations) + "(" + metric.getSimilarityTimingEstimated(input1, input1) + ")\t");
/*     */       }
/* 163 */       System.out.print("\t(" + metric.getShortDescriptionString() + ") - testsSoFar = " + metricTests + "\n");
/*     */     }
/*     */     
/* 166 */     totalTime = System.currentTimeMillis() - totalTime;
/* 167 */     System.out.println("\nTotal Metrics Tests = " + metricTests + " in " + totalTime + "ms\t\t meaning " + df.format(metricTests / (float)totalTime) + " tests per millisecond");
/*     */   }
/*     */ }


/* Location:              /home/menick/Scrivania/ProcessMining/CNMining/CNMining.jar!/uk/ac/shef/wit/simmetrics/TestEstimatedTimes.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       0.7.1
 */