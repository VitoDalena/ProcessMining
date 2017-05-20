/*     */ package uk.ac.shef.wit.simmetrics.metrichandlers;
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
/*     */ 
/*     */ 
/*     */ 
/*     */ public class TestSuite
/*     */   extends TestCase
/*     */ {
/*     */   public TestSuite(String s)
/*     */   {
/*  64 */     super(s);
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
/*     */   public static Test testAllMetricHandlers()
/*     */   {
/*  91 */     junit.framework.TestSuite newSuite = new junit.framework.TestSuite();
/*     */     
/*  93 */     return newSuite;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public static void main(String[] args)
/*     */   {
/* 102 */     TestRunner runner = new TestRunner();
/* 103 */     System.exit(TestRunner.run(runner.getTest(TestSuite.class.getName())).wasSuccessful() ? 0 : 1);
/*     */   }
/*     */ }


/* Location:              /home/menick/Scrivania/ProcessMining/CNMining/CNMining.jar!/uk/ac/shef/wit/simmetrics/metrichandlers/TestSuite.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       0.7.1
 */