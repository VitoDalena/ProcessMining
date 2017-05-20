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
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class FieldDoc
/*    */   extends ScoreDoc
/*    */ {
/*    */   public Comparable[] fields;
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
/*    */   public FieldDoc(int doc, float score)
/*    */   {
/* 55 */     super(doc, score);
/*    */   }
/*    */   
/*    */   public FieldDoc(int doc, float score, Comparable[] fields)
/*    */   {
/* 60 */     super(doc, score);
/* 61 */     this.fields = fields;
/*    */   }
/*    */ }


/* Location:              /home/menick/Scrivania/ProcessMining/CNMining/CNMining.jar!/org/apache/lucene/search/FieldDoc.class
 * Java compiler version: 2 (46.0)
 * JD-Core Version:       0.7.1
 */