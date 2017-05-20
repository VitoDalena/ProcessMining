/*    */ package org.apache.lucene.search;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class TopFieldDocs
/*    */   extends TopDocs
/*    */ {
/*    */   public SortField[] fields;
/*    */   
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   TopFieldDocs(int totalHits, ScoreDoc[] scoreDocs, SortField[] fields)
/*    */   {
/* 42 */     super(totalHits, scoreDocs);
/* 43 */     this.fields = fields;
/*    */   }
/*    */ }


/* Location:              /home/menick/Scrivania/ProcessMining/CNMining/CNMining.jar!/org/apache/lucene/search/TopFieldDocs.class
 * Java compiler version: 2 (46.0)
 * JD-Core Version:       0.7.1
 */