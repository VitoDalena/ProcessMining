/*    */ package org.apache.lucene.index;
/*    */ 
/*    */ import java.util.Arrays;
/*    */ 
/*    */ class SegmentTermVector implements TermFreqVector
/*    */ {
/*    */   private String field;
/*    */   private String[] terms;
/*    */   private int[] termFreqs;
/*    */   
/*    */   SegmentTermVector(String field, String[] terms, int[] termFreqs) {
/* 12 */     this.field = field;
/* 13 */     this.terms = terms;
/* 14 */     this.termFreqs = termFreqs;
/*    */   }
/*    */   
/*    */ 
/*    */ 
/*    */ 
/*    */   public String getField()
/*    */   {
/* 22 */     return this.field;
/*    */   }
/*    */   
/*    */   public String toString() {
/* 26 */     StringBuffer sb = new StringBuffer();
/* 27 */     sb.append('{');
/* 28 */     sb.append(this.field).append(": ");
/* 29 */     for (int i = 0; i < this.terms.length; i++) {
/* 30 */       if (i > 0) sb.append(", ");
/* 31 */       sb.append(this.terms[i]).append('/').append(this.termFreqs[i]);
/*    */     }
/* 33 */     sb.append('}');
/* 34 */     return sb.toString();
/*    */   }
/*    */   
/*    */   public int size() {
/* 38 */     return this.terms == null ? 0 : this.terms.length;
/*    */   }
/*    */   
/*    */   public String[] getTerms() {
/* 42 */     return this.terms;
/*    */   }
/*    */   
/*    */   public int[] getTermFrequencies() {
/* 46 */     return this.termFreqs;
/*    */   }
/*    */   
/*    */   public int indexOf(String termText) {
/* 50 */     int res = Arrays.binarySearch(this.terms, termText);
/* 51 */     return res >= 0 ? res : -1;
/*    */   }
/*    */   
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   public int[] indexesOf(String[] termNumbers, int start, int len)
/*    */   {
/* 60 */     int[] res = new int[len];
/*    */     
/* 62 */     for (int i = 0; i < len; i++) {
/* 63 */       res[i] = indexOf(termNumbers[i]);
/*    */     }
/* 65 */     return res;
/*    */   }
/*    */ }


/* Location:              /home/menick/Scrivania/ProcessMining/CNMining/CNMining.jar!/org/apache/lucene/index/SegmentTermVector.class
 * Java compiler version: 2 (46.0)
 * JD-Core Version:       0.7.1
 */