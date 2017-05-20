/*     */ package uk.ac.shef.wit.simmetrics;
/*     */ 
/*     */ import java.io.PrintStream;
/*     */ import java.text.DecimalFormat;
/*     */ import java.util.ArrayList;
/*     */ import uk.ac.shef.wit.simmetrics.metrichandlers.MetricHandler;
/*     */ import uk.ac.shef.wit.simmetrics.similaritymetrics.AbstractStringMetric;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public final class TestMetrics
/*     */ {
/*     */   private static final int TESTTIMINGMILLISECONDSPERTEST = 200;
/*     */   private static final int TESTMAXLENGTHTIMINGTEST = 3000;
/*     */   private static final int TESTMAXLENGTHTIMINGSTEPSIZE = 50;
/*     */   private static final int TESTMAXLENGTHTIMINGTERMLENGTH = 10;
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
/* 115 */   private static final String[][] testCases = { { "Sam J Chapman", "Samuel Chapman" }, { "Sam J Chapman", "S Chapman" }, { "Samuel Chapman", "S Chapman" }, { "Sam J Chapman", "Sam J Chapman" }, { "Samuel John Chapman", "John Smith" }, { "John Smith", "Richard Smith" }, { "John Smith", "Sam J Chapman" }, { "Sam J Chapman", "Richard Smith" }, { "Sam J Chapman", "Samuel John Chapman" }, { "Samuel Chapman", "Samuel John Chapman" }, { "aaaa mnop zzzz", "bbbb mnop yyyy" }, { "aaaa mnop zzzz", "aa mnop zzzzzz" }, { "bbbb mnop yyyy", "aa mnop zzzzzz" }, { "a", "a" }, { "aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa", "aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa" }, { "a", "aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa" }, { "aaaaa bcdefgh mmmmmmmm stuvwx zzzzzz", "jjjjj bcdefgh qqqqqqqq stuvwx yyyyyy" }, { "aaaaa bcdefgh mmmmmmmm stuvwx zzzzzz", "aaaaa bcdefgh stuvwx zzzzzz" }, { "aaaaa bcdefgh stuvwx zzzzzz", "aaaaa aaaaa aaaaa zzzzzz" }, { "aaaaa aaaaa", "aaaaa aaaaa" } };
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
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
/* 144 */     ArrayList<String> metricStrings = MetricHandler.GetMetricsAvailable();
/*     */     
/*     */ 
/* 147 */     ArrayList<AbstractStringMetric> testMetricArrayList = new ArrayList();
/* 148 */     for (String metricString : metricStrings) {
/* 149 */       testMetricArrayList.add(MetricHandler.createMetric(metricString));
/*     */     }
/*     */     
/* 152 */     testMethod(testMetricArrayList, args);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */   private static void Usage()
/*     */   {
/* 159 */     System.out.println("Usage: testMethod \"String1 to Test\" \"String2 to test\"");
/* 160 */     System.out.println("or");
/* 161 */     System.out.println("Usage: testMethod \"timing");
/* 162 */     System.out.println("AS NO INPUT - running defualt test cases\n");
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   private static void testMethod(ArrayList<AbstractStringMetric> metricVector, String[] args)
/*     */   {
/* 173 */     boolean useCmdArgs = false;
/* 174 */     boolean testTimingComplexity = false;
/* 175 */     if (args.length == 1) {
/* 176 */       testTimingComplexity = true;
/* 177 */     } else if (args.length == 2) {
/* 178 */       useCmdArgs = true;
/*     */     } else {
/* 180 */       Usage();
/*     */     }
/*     */     
/*     */ 
/* 184 */     System.out.println("Performing Tests with Following Metrics:");
/* 185 */     for (int i = 0; i < metricVector.size(); i++) {
/* 186 */       System.out.println("m" + (i + 1) + " " + ((AbstractStringMetric)metricVector.get(i)).getShortDescriptionString());
/*     */     }
/* 188 */     System.out.println();
/*     */     
/*     */ 
/* 191 */     if (!useCmdArgs) {
/* 192 */       System.out.println("Using the Following Test Cases:");
/* 193 */       for (int i = 0; i < testCases.length; i++) {
/* 194 */         System.out.println("t" + (i + 1) + " \"" + testCases[i][0] + "\" vs \"" + testCases[i][1] + "\"");
/*     */       }
/* 196 */       System.out.println();
/*     */     } else {
/* 198 */       System.out.println("Using the Input Test Case:");
/* 199 */       System.out.println("t1 \"" + args[0] + "\" vs \"" + args[1] + "\"");
/* 200 */       System.out.println();
/*     */     }
/*     */     
/*     */ 
/* 204 */     System.out.print("  \t");
/* 205 */     if (!useCmdArgs) {
/* 206 */       for (int j = 0; j < testCases.length; j++) {
/* 207 */         if (j < 9) {
/* 208 */           System.out.print("t" + (j + 1) + "=\t (t" + (j + 1) + "ms)\t");
/*     */         } else {
/* 210 */           System.out.print("t" + (j + 1) + "= (t" + (j + 1) + "ms)");
/*     */         }
/*     */       }
/*     */     } else {
/* 214 */       System.out.print("t1");
/*     */     }
/* 216 */     System.out.print("\n");
/* 217 */     DecimalFormat df = new DecimalFormat("0.00");
/* 218 */     int metricTests = 0;
/* 219 */     long totalTime = System.currentTimeMillis();
/* 220 */     for (int i = 0; i < metricVector.size(); i++) {
/* 221 */       AbstractStringMetric metric = (AbstractStringMetric)metricVector.get(i);
/* 222 */       System.out.print("m" + (i + 1) + "\t");
/* 223 */       if (testTimingComplexity)
/*     */       {
/*     */ 
/* 226 */         StringBuffer testString = new StringBuffer();
/* 227 */         int termLen = 0;
/* 228 */         for (int len = 1; len < 3000; termLen++) {
/* 229 */           if (termLen < 10) {
/* 230 */             testString.append((char)(97 + (int)(Math.random() * 25.0D)));
/*     */           } else {
/* 232 */             testString.append(' ');
/* 233 */             termLen = 0;
/*     */           }
/* 228 */           len++;
/*     */         }
/*     */         
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/* 237 */         for (int len = 1; len < 3000; len += 50) {
/* 238 */           long timeTaken = 0L;
/* 239 */           int iterations = 0;
/* 240 */           String input1 = testString.substring(0, len);
/* 241 */           while (timeTaken < 200L) {
/* 242 */             timeTaken += metric.getSimilarityTimingActual(input1, input1);
/* 243 */             iterations++;
/* 244 */             metricTests++;
/*     */           }
/* 246 */           System.out.print(df.format((float)timeTaken / iterations) + "\t");
/*     */         }
/* 248 */       } else if (!useCmdArgs)
/*     */       {
/* 250 */         for (String[] testCase : testCases) {
/* 251 */           float result = metric.getSimilarity(testCase[0], testCase[1]);
/* 252 */           metricTests++;
/* 253 */           long timeTaken = 0L;
/* 254 */           int iterations = 0;
/* 255 */           while (timeTaken < 200L) {
/* 256 */             timeTaken += metric.getSimilarityTimingActual(testCase[0], testCase[1]);
/* 257 */             iterations++;
/* 258 */             metricTests++;
/*     */           }
/* 260 */           System.out.print(df.format(result) + " (" + df.format((float)timeTaken / iterations) + ")\t");
/*     */         }
/*     */       }
/*     */       else {
/* 264 */         float result = metric.getSimilarity(args[0], args[1]);
/* 265 */         metricTests++;
/* 266 */         long timeTaken = 0L;
/* 267 */         int iterations = 0;
/* 268 */         while (timeTaken < 250L) {
/* 269 */           timeTaken += metric.getSimilarityTimingActual(args[0], args[1]);
/* 270 */           iterations++;
/* 271 */           metricTests++;
/*     */         }
/* 273 */         System.out.print(df.format(result) + " (" + df.format((float)timeTaken / iterations) + ")\t");
/*     */       }
/* 275 */       System.out.print("\t(" + metric.getShortDescriptionString() + ") - testsSoFar = " + metricTests + "\n");
/*     */     }
/*     */     
/* 278 */     totalTime = System.currentTimeMillis() - totalTime;
/* 279 */     System.out.println("\nTotal Metrics Tests = " + metricTests + " in " + totalTime + "ms\t\t meaning " + df.format(metricTests / (float)totalTime) + " tests per millisecond");
/*     */   }
/*     */ }


/* Location:              /home/menick/Scrivania/ProcessMining/CNMining/CNMining.jar!/uk/ac/shef/wit/simmetrics/TestMetrics.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       0.7.1
 */