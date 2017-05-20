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
/*    */ public class TokeniserWhitespaceTest
/*    */   extends TestCase
/*    */ {
/* 58 */   private InterfaceTokeniser tokeniser = null;
/*    */   
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   public TokeniserWhitespaceTest(String s)
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
/* 75 */     this.tokeniser = new TokeniserWhitespace();
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
/* 91 */     ArrayList results = this.tokeniser.tokenizeToArrayList("A B  C");
/* 92 */     assertEquals(3, results.size());
/* 93 */     assertEquals("A", results.get(0));
/* 94 */     assertEquals("B", results.get(1));
/* 95 */     assertEquals("C", results.get(2));
/*    */   }
/*    */ }


/* Location:              /home/menick/Scrivania/ProcessMining/CNMining/CNMining.jar!/uk/ac/shef/wit/simmetrics/tokenisers/TokeniserWhitespaceTest.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       0.7.1
 */