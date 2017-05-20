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
/*    */ public abstract interface FieldCache
/*    */ {
/*    */   public static final int STRING_INDEX = -1;
/*    */   
/*    */   public abstract int[] getInts(IndexReader paramIndexReader, String paramString)
/*    */     throws IOException;
/*    */   
/*    */   public abstract float[] getFloats(IndexReader paramIndexReader, String paramString)
/*    */     throws IOException;
/*    */   
/*    */   public abstract String[] getStrings(IndexReader paramIndexReader, String paramString)
/*    */     throws IOException;
/*    */   
/*    */   public abstract StringIndex getStringIndex(IndexReader paramIndexReader, String paramString)
/*    */     throws IOException;
/*    */   
/*    */   public abstract Object getAuto(IndexReader paramIndexReader, String paramString)
/*    */     throws IOException;
/*    */   
/*    */   public abstract Comparable[] getCustom(IndexReader paramIndexReader, String paramString, SortComparator paramSortComparator)
/*    */     throws IOException;
/*    */   
/*    */   public static class StringIndex
/*    */   {
/*    */     public final String[] lookup;
/*    */     public final int[] order;
/*    */     
/*    */     public StringIndex(int[] values, String[] lookup)
/*    */     {
/* 50 */       this.order = values;
/* 51 */       this.lookup = lookup;
/*    */     }
/*    */   }
/*    */   
/*    */ 
/*    */ 
/* 57 */   public static final FieldCache DEFAULT = new FieldCacheImpl();
/*    */ }


/* Location:              /home/menick/Scrivania/ProcessMining/CNMining/CNMining.jar!/org/apache/lucene/search/FieldCache.class
 * Java compiler version: 2 (46.0)
 * JD-Core Version:       0.7.1
 */