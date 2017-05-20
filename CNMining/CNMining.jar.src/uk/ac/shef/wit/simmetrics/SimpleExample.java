/*     */ package uk.ac.shef.wit.simmetrics;
/*     */ 
/*     */ import java.io.PrintStream;
/*     */ import uk.ac.shef.wit.simmetrics.similaritymetrics.AbstractStringMetric;
/*     */ import uk.ac.shef.wit.simmetrics.similaritymetrics.MongeElkan;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class SimpleExample
/*     */ {
/*     */   public static void main(String[] args)
/*     */   {
/*  65 */     if (args.length != 2)
/*     */     {
/*     */ 
/*  68 */       usage();
/*     */     }
/*     */     else {
/*  71 */       String str1 = args[0];
/*  72 */       String str2 = args[1];
/*     */       
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*  82 */       AbstractStringMetric metric = new MongeElkan();
/*     */       
/*     */ 
/*  85 */       float result = metric.getSimilarity(str1, str2);
/*     */       
/*     */ 
/*  88 */       outputResult(result, metric, str1, str2);
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
/*     */   private static void outputResult(float result, AbstractStringMetric metric, String str1, String str2)
/*     */   {
/* 101 */     System.out.println("Using Metric " + metric.getShortDescriptionString() + " on strings \"" + str1 + "\" & \"" + str2 + "\" gives a similarity score of " + result);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   private static void usage()
/*     */   {
/* 109 */     System.out.println("Performs a rudimentary string metric comparison from the arguments given.\n\tArgs:\n\t\t1) String1 to compare\n\t\t2)String2 to compare\n\n\tReturns:\n\t\tA standard output (command line of the similarity metric with the given test strings, for more details of this simple class please see the SimpleExample.java source file)");
/*     */   }
/*     */ }


/* Location:              /home/menick/Scrivania/ProcessMining/CNMining/CNMining.jar!/uk/ac/shef/wit/simmetrics/SimpleExample.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       0.7.1
 */