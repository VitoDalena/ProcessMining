/*     */ package uk.ac.shef.wit.simmetrics;
/*     */ 
/*     */ import java.io.PrintStream;
/*     */ import java.text.DecimalFormat;
/*     */ import java.util.ArrayList;
/*     */ import uk.ac.shef.wit.simmetrics.arbitrators.InterfaceMetricArbitrator;
/*     */ import uk.ac.shef.wit.simmetrics.arbitrators.MeanMetricArbitrator;
/*     */ import uk.ac.shef.wit.simmetrics.metrichandlers.MetricHandler;
/*     */ import uk.ac.shef.wit.simmetrics.similaritymetrics.InterfaceStringMetric;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public final class TestArbitrators
/*     */ {
/*     */   private static final String string1 = "Sam J Chapman";
/*     */   private static final String string2 = "Samuel Chapman";
/*     */   private static final String string3 = "S Chapman";
/*     */   private static final String string4 = "Samuel John Chapman";
/*     */   private static final String string5 = "John Smith";
/*     */   private static final String string6 = "Richard Smith";
/*     */   private static final String string7 = "aaaa mnop zzzz";
/*     */   private static final String string8 = "bbbb mnop yyyy";
/*     */   private static final String string9 = "aa mnop zzzzzz";
/*     */   private static final String string10 = "a";
/*     */   private static final String string11 = "aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa";
/*     */   private static final String string12 = "aaaaa bcdefgh mmmmmmmm stuvwx zzzzzz";
/*     */   private static final String string13 = "jjjjj bcdefgh qqqqqqqq stuvwx yyyyyy";
/*     */   private static final String string14 = "aaaaa bcdefgh stuvwx zzzzzz";
/*     */   private static final String string15 = "aaaaa aaaaa aaaaa zzzzzz";
/*     */   private static final String string16 = "aaaaa aaaaa";
/*  98 */   private static final String[][] testCases = { { "Sam J Chapman", "Samuel Chapman" }, { "Sam J Chapman", "S Chapman" }, { "Samuel Chapman", "S Chapman" }, { "Sam J Chapman", "Sam J Chapman" }, { "Samuel John Chapman", "John Smith" }, { "John Smith", "Richard Smith" }, { "John Smith", "Sam J Chapman" }, { "Sam J Chapman", "Richard Smith" }, { "Sam J Chapman", "Samuel John Chapman" }, { "Samuel Chapman", "Samuel John Chapman" }, { "aaaa mnop zzzz", "bbbb mnop yyyy" }, { "aaaa mnop zzzz", "aa mnop zzzzzz" }, { "bbbb mnop yyyy", "aa mnop zzzzzz" }, { "a", "a" }, { "aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa", "aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa" }, { "a", "aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa" }, { "aaaaa bcdefgh mmmmmmmm stuvwx zzzzzz", "jjjjj bcdefgh qqqqqqqq stuvwx yyyyyy" }, { "aaaaa bcdefgh mmmmmmmm stuvwx zzzzzz", "aaaaa bcdefgh stuvwx zzzzzz" }, { "aaaaa bcdefgh stuvwx zzzzzz", "aaaaa aaaaa aaaaa zzzzzz" }, { "aaaaa aaaaa", "aaaaa aaaaa" } };
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public static void main(String[] args)
/*     */   {
/* 127 */     ArrayList<String> metricStrings = MetricHandler.GetMetricsAvailable();
/*     */     
/*     */ 
/* 130 */     ArrayList<InterfaceStringMetric> testMetricArrayList = new ArrayList();
/* 131 */     for (String metricString : metricStrings) {
/* 132 */       testMetricArrayList.add(MetricHandler.createMetric(metricString));
/*     */     }
/*     */     
/* 135 */     InterfaceMetricArbitrator arbitrator = new MeanMetricArbitrator();
/* 136 */     arbitrator.setArbitrationMetrics(testMetricArrayList);
/*     */     
/*     */ 
/* 139 */     testMethod(arbitrator);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   private static void testMethod(InterfaceMetricArbitrator arbitrator)
/*     */   {
/* 150 */     System.out.println("Performing Arbitrartion with: " + arbitrator.getShortDescriptionString());
/*     */     
/* 152 */     System.out.println("Using the Following Test Cases:");
/* 153 */     for (int i = 0; i < testCases.length; i++) {
/* 154 */       System.out.println("t" + (i + 1) + " \"" + testCases[i][0] + "\" vs \"" + testCases[i][1] + "\"");
/*     */     }
/* 156 */     System.out.println();
/*     */     
/*     */ 
/* 159 */     DecimalFormat df = new DecimalFormat("0.00");
/* 160 */     for (String[] testCase : testCases) {
/* 161 */       float result = arbitrator.getArbitrationScore(testCase[0], testCase[1]);
/* 162 */       System.out.println(df.format(result) + " for \"" + testCase[0] + "\" vs \"" + testCase[1] + "\"");
/*     */     }
/*     */   }
/*     */ }


/* Location:              /home/menick/Scrivania/ProcessMining/CNMining/CNMining.jar!/uk/ac/shef/wit/simmetrics/TestArbitrators.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       0.7.1
 */