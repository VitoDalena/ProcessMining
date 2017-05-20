/*    */ package org.apache.lucene.search;
/*    */ 
/*    */ import java.io.IOException;
/*    */ import java.util.BitSet;
/*    */ import java.util.Map;
/*    */ import java.util.WeakHashMap;
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
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class CachingWrapperFilter
/*    */   extends Filter
/*    */ {
/*    */   private Filter filter;
/*    */   private transient Map cache;
/*    */   
/*    */   public CachingWrapperFilter(Filter filter)
/*    */   {
/* 44 */     this.filter = filter;
/*    */   }
/*    */   
/*    */   public BitSet bits(IndexReader reader) throws IOException {
/* 48 */     if (this.cache == null) {
/* 49 */       this.cache = new WeakHashMap();
/*    */     }
/*    */     
/* 52 */     synchronized (this.cache) {
/* 53 */       BitSet cached = (BitSet)this.cache.get(reader);
/* 54 */       if (cached != null) {
/* 55 */         return cached;
/*    */       }
/*    */     }
/*    */     
/* 59 */     BitSet bits = this.filter.bits(reader);
/*    */     
/* 61 */     synchronized (this.cache) {
/* 62 */       this.cache.put(reader, bits);
/*    */     }
/*    */     
/* 65 */     return bits;
/*    */   }
/*    */   
/*    */   public String toString() {
/* 69 */     return "CachingWrapperFilter(" + this.filter + ")";
/*    */   }
/*    */ }


/* Location:              /home/menick/Scrivania/ProcessMining/CNMining/CNMining.jar!/org/apache/lucene/search/CachingWrapperFilter.class
 * Java compiler version: 2 (46.0)
 * JD-Core Version:       0.7.1
 */