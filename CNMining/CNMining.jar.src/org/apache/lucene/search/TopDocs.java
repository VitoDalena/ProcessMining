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
/*    */ public class TopDocs
/*    */   implements Serializable
/*    */ {
/*    */   public int totalHits;
/*    */   public ScoreDoc[] scoreDocs;
/*    */   
/*    */   TopDocs(int totalHits, ScoreDoc[] scoreDocs)
/*    */   {
/* 31 */     this.totalHits = totalHits;
/* 32 */     this.scoreDocs = scoreDocs;
/*    */   }
/*    */ }


/* Location:              /home/menick/Scrivania/ProcessMining/CNMining/CNMining.jar!/org/apache/lucene/search/TopDocs.class
 * Java compiler version: 2 (46.0)
 * JD-Core Version:       0.7.1
 */