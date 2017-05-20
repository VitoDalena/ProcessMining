/*     */ package uk.ac.shef.wit.simmetrics.arbitrators;
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
/*     */ 
/*     */ public class TestSuite
/*     */   extends TestCase
/*     */ {
/*     */   public TestSuite(String s)
/*     */   {
/*  61 */     super(s);
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
/*     */   public static Test testAllArbitrators()
/*     */   {
/*  88 */     junit.framework.TestSuite newSuite = new junit.framework.TestSuite();
/*     */     
/*  90 */     return newSuite;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public static void main(String[] args)
/*     */   {
/*  99 */     TestRunner runner = new TestRunner();
/* 100 */     System.exit(TestRunner.run(runner.getTest(TestSuite.class.getName())).wasSuccessful() ? 0 : 1);
/*     */   }
/*     */ }


/* Location:              /home/menick/Scrivania/ProcessMining/CNMining/CNMining.jar!/uk/ac/shef/wit/simmetrics/arbitrators/TestSuite.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       0.7.1
 */