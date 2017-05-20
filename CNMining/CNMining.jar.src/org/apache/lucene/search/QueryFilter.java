/*    */ package org.apache.lucene.search;
/*    */ 
/*    */ import java.io.IOException;
/*    */ import java.util.BitSet;
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
/*    */ public class QueryFilter
/*    */   extends Filter
/*    */ {
/*    */   private Query query;
/* 38 */   private transient WeakHashMap cache = null;
/*    */   
/*    */ 
/*    */ 
/*    */   public QueryFilter(Query query)
/*    */   {
/* 44 */     this.query = query;
/*    */   }
/*    */   
/*    */   public BitSet bits(IndexReader reader) throws IOException
/*    */   {
/* 49 */     if (this.cache == null) {
/* 50 */       this.cache = new WeakHashMap();
/*    */     }
/*    */     
/* 53 */     synchronized (this.cache) {
/* 54 */       BitSet cached = (BitSet)this.cache.get(reader);
/* 55 */       if (cached != null) {
/* 56 */         return cached;
/*    */       }
/*    */     }
/*    */     
/* 60 */     BitSet bits = new BitSet(reader.maxDoc());
/*    */     
/* 62 */     new IndexSearcher(reader).search(this.query, new HitCollector() { private final BitSet val$bits;
/*    */       
/* 64 */       public final void collect(int doc, float score) { this.val$bits.set(doc); }
/*    */     });
/*    */     
/*    */ 
/* 68 */     synchronized (this.cache) {
/* 69 */       this.cache.put(reader, bits);
/*    */     }
/*    */     
/* 72 */     return bits;
/*    */   }
/*    */   
/*    */   public String toString() {
/* 76 */     return "QueryFilter(" + this.query + ")";
/*    */   }
/*    */ }


/* Location:              /home/menick/Scrivania/ProcessMining/CNMining/CNMining.jar!/org/apache/lucene/search/QueryFilter.class
 * Java compiler version: 2 (46.0)
 * JD-Core Version:       0.7.1
 */