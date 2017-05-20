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
/*    */ public class SubCost5_3_Minus3Test
/*    */   extends TestCase
/*    */ {
/*    */   private InterfaceSubstitutionCost costFunction;
/*    */   
/*    */   protected void setUp()
/*    */   {
/* 64 */     this.costFunction = new SubCost5_3_Minus3();
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
/* 80 */     String testString1 = "hello world AAAAAAA BBB ABCDEF this is a test";
/* 81 */     String testString2 = "jello wrd AAAAAAA BBB ABCDEF this is a test";
/* 82 */     assertEquals(Float.valueOf(-3.0F), Float.valueOf(this.costFunction.getCost("hello world AAAAAAA BBB ABCDEF this is a test", 0, "jello wrd AAAAAAA BBB ABCDEF this is a test", 0)));
/* 83 */     assertEquals(Float.valueOf(5.0F), Float.valueOf(this.costFunction.getCost("hello world AAAAAAA BBB ABCDEF this is a test", 2, "jello wrd AAAAAAA BBB ABCDEF this is a test", 2)));
/* 84 */     assertEquals(Float.valueOf(-3.0F), Float.valueOf(this.costFunction.getCost("hello world AAAAAAA BBB ABCDEF this is a test", 7, "jello wrd AAAAAAA BBB ABCDEF this is a test", 7)));
/* 85 */     assertEquals(Float.valueOf(-3.0F), Float.valueOf(this.costFunction.getCost("hello world AAAAAAA BBB ABCDEF this is a test", 10, "jello wrd AAAAAAA BBB ABCDEF this is a test", 10)));
/* 86 */     assertEquals(Float.valueOf(-3.0F), Float.valueOf(this.costFunction.getCost("hello world AAAAAAA BBB ABCDEF this is a test", 22, "jello wrd AAAAAAA BBB ABCDEF this is a test", 3)));
/*    */   }
/*    */ }


/* Location:              /home/menick/Scrivania/ProcessMining/CNMining/CNMining.jar!/uk/ac/shef/wit/simmetrics/similaritymetrics/costfunctions/SubCost5_3_Minus3Test.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       0.7.1
 */