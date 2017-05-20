/*     */ package uk.ac.shef.wit.simmetrics.tokenisers;
/*     */ 
/*     */ import junit.framework.Test;
/*     */ import junit.framework.TestCase;
/*     */ import junit.framework.TestResult;
/*     */ import junit.textui.TestRunner;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class TestSuite
/*     */   extends TestCase
/*     */ {
/*     */   public TestSuite(String s)
/*     */   {
/*  60 */     super(s);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   protected void setUp() {}
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   protected void tearDown() {}
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public static Test testAllTokenisers()
/*     */   {
/*  87 */     junit.framework.TestSuite newSuite = new junit.framework.TestSuite();
/*  88 */     newSuite.addTest(new TokeniserCSVBasicTest("testTokeniseToArrayList"));
/*  89 */     newSuite.addTest(new TokeniserQGram2ExtendedTest("testTokeniseToArrayList"));
/*  90 */     newSuite.addTest(new TokeniserQGram2Test("testTokeniseToArrayList"));
/*  91 */     newSuite.addTest(new TokeniserQGram3ExtendedTest("testTokeniseToArrayList"));
/*  92 */     newSuite.addTest(new TokeniserQGram3Test("testTokeniseToArrayList"));
/*  93 */     newSuite.addTest(new TokeniserWhitespaceTest("testTokeniseToArrayList"));
/*  94 */     return newSuite;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public static void main(String[] args)
/*     */   {
/* 103 */     TestRunner runner = new TestRunner();
/* 104 */     System.exit(TestRunner.run(runner.getTest(TestSuite.class.getName())).wasSuccessful() ? 0 : 1);
/*     */   }
/*     */ }


/* Location:              /home/menick/Scrivania/ProcessMining/CNMining/CNMining.jar!/uk/ac/shef/wit/simmetrics/tokenisers/TestSuite.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       0.7.1
 */