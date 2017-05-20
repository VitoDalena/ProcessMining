/*    */ package uk.ac.shef.wit.simmetrics.math;
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
/*    */ public class TestSuite
/*    */   extends TestCase
/*    */ {
/*    */   public TestSuite(String s)
/*    */   {
/* 60 */     super(s);
/*    */   }
/*    */   
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   protected void setUp() {}
/*    */   
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   protected void tearDown() {}
/*    */   
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   public static Test testAllMath()
/*    */   {
/* 87 */     junit.framework.TestSuite newSuite = new junit.framework.TestSuite();
/* 88 */     newSuite.addTest(new MathFuncsTest());
/* 89 */     return newSuite;
/*    */   }
/*    */   
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   public static void main(String[] args)
/*    */   {
/* 98 */     TestRunner runner = new TestRunner();
/* 99 */     System.exit(TestRunner.run(runner.getTest(TestSuite.class.getName())).wasSuccessful() ? 0 : 1);
/*    */   }
/*    */ }


/* Location:              /home/menick/Scrivania/ProcessMining/CNMining/CNMining.jar!/uk/ac/shef/wit/simmetrics/math/TestSuite.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       0.7.1
 */