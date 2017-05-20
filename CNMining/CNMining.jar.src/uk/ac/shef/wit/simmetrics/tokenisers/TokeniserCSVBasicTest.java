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
/*     */ public class TokeniserCSVBasicTest
/*     */   extends TestCase
/*     */ {
/*  58 */   private InterfaceTokeniser tokeniser = null;
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public TokeniserCSVBasicTest(String s)
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
/*  75 */     this.tokeniser = new TokeniserCSVBasic();
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
/*  91 */     ArrayList results = this.tokeniser.tokenizeToArrayList("1a,2a,3a,4a\n1b,2b,3b,4b");
/*  92 */     assertEquals(1, results.size());
/*  93 */     assertEquals("1a", results.get(0));
/*  94 */     assertEquals("2a", results.get(1));
/*  95 */     assertEquals("3a", results.get(2));
/*  96 */     assertEquals("4a", results.get(3));
/*  97 */     assertEquals("1b", results.get(4));
/*  98 */     assertEquals("2b", results.get(5));
/*  99 */     assertEquals("3b", results.get(6));
/* 100 */     assertEquals("4b", results.get(7));
/*     */   }
/*     */ }


/* Location:              /home/menick/Scrivania/ProcessMining/CNMining/CNMining.jar!/uk/ac/shef/wit/simmetrics/tokenisers/TokeniserCSVBasicTest.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       0.7.1
 */