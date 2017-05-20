/*    */ package uk.ac.shef.wit.simmetrics;
/*    */ 
/*    */ import junit.framework.Test;
/*    */ import junit.framework.TestCase;
/*    */ import junit.framework.TestResult;
/*    */ import junit.textui.TestRunner;
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
/*    */ 
/*    */ public class TestSuite
/*    */   extends TestCase
/*    */ {
/*    */   protected void setUp() {}
/*    */   
/*    */   protected void tearDown() {}
/*    */   
/*    */   public static Test suite()
/*    */   {
/* 77 */     junit.framework.TestSuite newSuite = new junit.framework.TestSuite();
/* 78 */     newSuite.addTest(new uk.ac.shef.wit.simmetrics.tokenisers.TestSuite("testAllTokenisers"));
/* 79 */     newSuite.addTest(new uk.ac.shef.wit.simmetrics.similaritymetrics.TestSuite("testAllSimilarityMetrics"));
/* 80 */     newSuite.addTest(new uk.ac.shef.wit.simmetrics.arbitrators.TestSuite("testAllArbitrators"));
/* 81 */     newSuite.addTest(new uk.ac.shef.wit.simmetrics.basiccontainers.TestSuite("testAllBasicContainers"));
/* 82 */     newSuite.addTest(new uk.ac.shef.wit.simmetrics.math.TestSuite("testAllMath"));
/* 83 */     newSuite.addTest(new uk.ac.shef.wit.simmetrics.metrichandlers.TestSuite("testAllMetricHandlers"));
/* 84 */     newSuite.addTest(new uk.ac.shef.wit.simmetrics.task.TestSuite("testAllTask"));
/* 85 */     newSuite.addTest(new uk.ac.shef.wit.simmetrics.utils.TestSuite("testAllUtils"));
/* 86 */     newSuite.addTest(new uk.ac.shef.wit.simmetrics.wordhandlers.TestSuite("testAllWordHandlers"));
/* 87 */     return newSuite;
/*    */   }
/*    */   
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   public static void main(String[] args)
/*    */   {
/* 96 */     TestRunner runner = new TestRunner();
/* 97 */     System.exit(TestRunner.run(runner.getTest(TestSuite.class.getName())).wasSuccessful() ? 0 : 1);
/*    */   }
/*    */ }


/* Location:              /home/menick/Scrivania/ProcessMining/CNMining/CNMining.jar!/uk/ac/shef/wit/simmetrics/TestSuite.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       0.7.1
 */