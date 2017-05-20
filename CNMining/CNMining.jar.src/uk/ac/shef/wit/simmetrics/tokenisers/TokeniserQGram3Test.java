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
/*    */ public class TokeniserQGram3Test
/*    */   extends TestCase
/*    */ {
/* 58 */   private InterfaceTokeniser tokeniser = null;
/*    */   
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   public TokeniserQGram3Test(String s)
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
/* 75 */     this.tokeniser = new TokeniserQGram3();
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
/* 92 */     assertEquals(6, results.size());
/* 93 */     assertEquals("123", results.get(0));
/* 94 */     assertEquals("234", results.get(1));
/* 95 */     assertEquals("345", results.get(2));
/* 96 */     assertEquals("456", results.get(3));
/* 97 */     assertEquals("567", results.get(4));
/* 98 */     assertEquals("678", results.get(5));
/*    */   }
/*    */ }


/* Location:              /home/menick/Scrivania/ProcessMining/CNMining/CNMining.jar!/uk/ac/shef/wit/simmetrics/tokenisers/TokeniserQGram3Test.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       0.7.1
 */