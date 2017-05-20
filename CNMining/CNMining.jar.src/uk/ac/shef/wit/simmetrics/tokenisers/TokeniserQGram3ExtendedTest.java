/*     */ package uk.ac.shef.wit.simmetrics.tokenisers;
/*     */ 
/*     */ import java.util.ArrayList;
/*     */ import junit.framework.TestCase;
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
/*     */ public class TokeniserQGram3ExtendedTest
/*     */   extends TestCase
/*     */ {
/*  58 */   private InterfaceTokeniser tokeniser = null;
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public TokeniserQGram3ExtendedTest(String s)
/*     */   {
/*  66 */     super(s);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   protected void setUp()
/*     */   {
/*  75 */     this.tokeniser = new TokeniserQGram3Extended();
/*     */   }
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
/*     */   public void testTokeniseToArrayList()
/*     */   {
/*  91 */     ArrayList results = this.tokeniser.tokenizeToArrayList("12345678");
/*  92 */     assertEquals(10, results.size());
/*  93 */     assertEquals("##1", results.get(0));
/*  94 */     assertEquals("#12", results.get(1));
/*  95 */     assertEquals("123", results.get(2));
/*  96 */     assertEquals("234", results.get(3));
/*  97 */     assertEquals("345", results.get(4));
/*  98 */     assertEquals("456", results.get(5));
/*  99 */     assertEquals("567", results.get(6));
/* 100 */     assertEquals("678", results.get(7));
/* 101 */     assertEquals("78#", results.get(8));
/* 102 */     assertEquals("8##", results.get(9));
/*     */   }
/*     */ }


/* Location:              /home/menick/Scrivania/ProcessMining/CNMining/CNMining.jar!/uk/ac/shef/wit/simmetrics/tokenisers/TokeniserQGram3ExtendedTest.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       0.7.1
 */