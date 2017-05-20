/*    */ package org.apache.lucene.search;
/*    */ 
/*    */ import java.io.Serializable;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class ScoreDoc
/*    */   implements Serializable
/*    */ {
/*    */   public float score;
/*    */   public int doc;
/*    */   
/*    */   public ScoreDoc(int doc, float score)
/*    */   {
/* 32 */     this.doc = doc;
/* 33 */     this.score = score;
/*    */   }
/*    */ }


/* Location:              /home/menick/Scrivania/ProcessMining/CNMining/CNMining.jar!/org/apache/lucene/search/ScoreDoc.class
 * Java compiler version: 2 (46.0)
 * JD-Core Version:       0.7.1
 */