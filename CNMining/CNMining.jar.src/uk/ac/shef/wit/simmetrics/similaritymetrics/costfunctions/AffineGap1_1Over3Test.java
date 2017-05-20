/*    */ package uk.ac.shef.wit.simmetrics.similaritymetrics.costfunctions;
/*    */ 
/*    */ import junit.framework.TestCase;
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
/*    */ public class AffineGap1_1Over3Test
/*    */   extends TestCase
/*    */ {
/*    */   private InterfaceAffineGapCost costFunction;
/*    */   
/*    */   protected void setUp()
/*    */   {
/* 64 */     this.costFunction = new AffineGap1_1Over3();
/*    */   }
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
/*    */   public void testAll()
/*    */   {
/* 80 */     String testString = "hello world AAAAAAA BBB ABCDEF this is a test";
/* 81 */     assertEquals(Float.valueOf(2.6666667F), Float.valueOf(this.costFunction.getCost("hello world AAAAAAA BBB ABCDEF this is a test", 0, 6)));
/* 82 */     assertEquals(Float.valueOf(1.0F), Float.valueOf(this.costFunction.getCost("hello world AAAAAAA BBB ABCDEF this is a test", 3, 4)));
/* 83 */     assertEquals(Float.valueOf(2.0F), Float.valueOf(this.costFunction.getCost("hello world AAAAAAA BBB ABCDEF this is a test", 13, 17)));
/* 84 */     assertEquals(Float.valueOf(1.6666667F), Float.valueOf(this.costFunction.getCost("hello world AAAAAAA BBB ABCDEF this is a test", 19, 22)));
/* 85 */     assertEquals(Float.valueOf(2.6666667F), Float.valueOf(this.costFunction.getCost("hello world AAAAAAA BBB ABCDEF this is a test", 23, 29)));
/* 86 */     assertEquals(Float.valueOf(1.0F), Float.valueOf(this.costFunction.getCost("hello world AAAAAAA BBB ABCDEF this is a test", 5, 6)));
/*    */   }
/*    */ }


/* Location:              /home/menick/Scrivania/ProcessMining/CNMining/CNMining.jar!/uk/ac/shef/wit/simmetrics/similaritymetrics/costfunctions/AffineGap1_1Over3Test.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       0.7.1
 */