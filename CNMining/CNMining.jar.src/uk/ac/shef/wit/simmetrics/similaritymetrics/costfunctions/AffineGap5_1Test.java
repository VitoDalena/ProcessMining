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
/*    */ public class AffineGap5_1Test
/*    */   extends TestCase
/*    */ {
/*    */   private InterfaceAffineGapCost costFunction;
/*    */   
/*    */   protected void setUp()
/*    */   {
/* 64 */     this.costFunction = new AffineGap5_1();
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
/* 81 */     assertEquals(Float.valueOf(10.0F), Float.valueOf(this.costFunction.getCost("hello world AAAAAAA BBB ABCDEF this is a test", 0, 6)));
/* 82 */     assertEquals(Float.valueOf(5.0F), Float.valueOf(this.costFunction.getCost("hello world AAAAAAA BBB ABCDEF this is a test", 3, 4)));
/* 83 */     assertEquals(Float.valueOf(8.0F), Float.valueOf(this.costFunction.getCost("hello world AAAAAAA BBB ABCDEF this is a test", 13, 17)));
/* 84 */     assertEquals(Float.valueOf(7.0F), Float.valueOf(this.costFunction.getCost("hello world AAAAAAA BBB ABCDEF this is a test", 19, 22)));
/* 85 */     assertEquals(Float.valueOf(10.0F), Float.valueOf(this.costFunction.getCost("hello world AAAAAAA BBB ABCDEF this is a test", 23, 29)));
/* 86 */     assertEquals(Float.valueOf(5.0F), Float.valueOf(this.costFunction.getCost("hello world AAAAAAA BBB ABCDEF this is a test", 5, 6)));
/*    */   }
/*    */ }


/* Location:              /home/menick/Scrivania/ProcessMining/CNMining/CNMining.jar!/uk/ac/shef/wit/simmetrics/similaritymetrics/costfunctions/AffineGap5_1Test.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       0.7.1
 */