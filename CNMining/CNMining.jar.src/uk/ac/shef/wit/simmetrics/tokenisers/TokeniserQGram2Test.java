/*    */ package uk.ac.shef.wit.simmetrics.tokenisers;
/*    */ 
/*    */ import java.util.ArrayList;
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
/*    */ public class TokeniserQGram2Test
/*    */   extends TestCase
/*    */ {
/* 58 */   private InterfaceTokeniser tokeniser = null;
/*    */   
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   public TokeniserQGram2Test(String s)
/*    */   {
/* 66 */     super(s);
/*    */   }
/*    */   
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   protected void setUp()
/*    */   {
/* 75 */     this.tokeniser = new TokeniserQGram2();
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
/*    */   public void testTokeniseToArrayList()
/*    */   {
/* 91 */     ArrayList results = this.tokeniser.tokenizeToArrayList("12345678");
/* 92 */     assertEquals(7, results.size());
/* 93 */     assertEquals("ggg12", results.get(0));
/* 94 */     assertEquals("23", results.get(1));
/* 95 */     assertEquals("34", results.get(2));
/* 96 */     assertEquals("45", results.get(3));
/* 97 */     assertEquals("56", results.get(4));
/* 98 */     assertEquals("67", results.get(5));
/* 99 */     assertEquals("78", results.get(6));
/*    */   }
/*    */ }


/* Location:              /home/menick/Scrivania/ProcessMining/CNMining/CNMining.jar!/uk/ac/shef/wit/simmetrics/tokenisers/TokeniserQGram2Test.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       0.7.1
 */