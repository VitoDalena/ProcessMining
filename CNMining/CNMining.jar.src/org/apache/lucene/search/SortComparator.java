/*    */ package org.apache.lucene.search;
/*    */ 
/*    */ import java.io.IOException;
/*    */ import org.apache.lucene.index.IndexReader;
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
/*    */ public abstract class SortComparator
/*    */   implements SortComparatorSource
/*    */ {
/*    */   public ScoreDocComparator newComparator(IndexReader reader, String fieldname)
/*    */     throws IOException
/*    */   {
/* 31 */     String field = fieldname.intern();
/* 32 */     Comparable[] cachedValues = FieldCache.DEFAULT.getCustom(reader, field, this);
/* 33 */     new ScoreDocComparator() {
/*    */       private final Comparable[] val$cachedValues;
/*    */       
/* 36 */       public int compare(ScoreDoc i, ScoreDoc j) { return this.val$cachedValues[i.doc].compareTo(this.val$cachedValues[j.doc]); }
/*    */       
/*    */       public Comparable sortValue(ScoreDoc i)
/*    */       {
/* 40 */         return this.val$cachedValues[i.doc];
/*    */       }
/*    */       
/*    */       public int sortType() {
/* 44 */         return 9;
/*    */       }
/*    */     };
/*    */   }
/*    */   
/*    */   protected abstract Comparable getComparable(String paramString);
/*    */ }


/* Location:              /home/menick/Scrivania/ProcessMining/CNMining/CNMining.jar!/org/apache/lucene/search/SortComparator.class
 * Java compiler version: 2 (46.0)
 * JD-Core Version:       0.7.1
 */