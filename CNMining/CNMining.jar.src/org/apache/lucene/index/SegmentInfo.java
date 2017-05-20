/*    */ package org.apache.lucene.index;
/*    */ 
/*    */ import org.apache.lucene.store.Directory;
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
/*    */ final class SegmentInfo
/*    */ {
/*    */   public String name;
/*    */   public int docCount;
/*    */   public Directory dir;
/*    */   
/*    */   public SegmentInfo(String name, int docCount, Directory dir)
/*    */   {
/* 27 */     this.name = name;
/* 28 */     this.docCount = docCount;
/* 29 */     this.dir = dir;
/*    */   }
/*    */ }


/* Location:              /home/menick/Scrivania/ProcessMining/CNMining/CNMining.jar!/org/apache/lucene/index/SegmentInfo.class
 * Java compiler version: 2 (46.0)
 * JD-Core Version:       0.7.1
 */