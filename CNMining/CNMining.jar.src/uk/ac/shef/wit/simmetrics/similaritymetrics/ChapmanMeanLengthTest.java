/*    */ package uk.ac.shef.wit.simmetrics.similaritymetrics;
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
/*    */ public class ChapmanMeanLengthTest
/*    */   extends TestCase
/*    */ {
/*    */   private AbstractStringMetric metric;
/*    */   
/*    */   protected void setUp()
/*    */   {
/* 60 */     this.metric = new ChapmanMeanLength();
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
/*    */ 
/*    */   public void testGetSimilarity()
/*    */   {
/* 77 */     float result = this.metric.getSimilarity("Test String1", "Test String2");
/*    */     
/* 79 */     assertEquals(Float.valueOf(0.17861295F), Float.valueOf(result));
/*    */   }
/*    */ }


/* Location:              /home/menick/Scrivania/ProcessMining/CNMining/CNMining.jar!/uk/ac/shef/wit/simmetrics/similaritymetrics/ChapmanMeanLengthTest.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       0.7.1
 */