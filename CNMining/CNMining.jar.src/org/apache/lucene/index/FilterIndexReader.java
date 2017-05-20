/*     */ package org.apache.lucene.index;
/*     */ 
/*     */ import java.io.IOException;
/*     */ import java.util.Collection;
/*     */ import org.apache.lucene.document.Document;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class FilterIndexReader
/*     */   extends IndexReader
/*     */ {
/*     */   protected IndexReader in;
/*     */   
/*     */   public static class FilterTermDocs
/*     */     implements TermDocs
/*     */   {
/*     */     protected TermDocs in;
/*     */     
/*  39 */     public FilterTermDocs(TermDocs in) { this.in = in; }
/*     */     
/*  41 */     public void seek(Term term) throws IOException { this.in.seek(term); }
/*  42 */     public void seek(TermEnum termEnum) throws IOException { this.in.seek(termEnum); }
/*  43 */     public int doc() { return this.in.doc(); }
/*  44 */     public int freq() { return this.in.freq(); }
/*  45 */     public boolean next() throws IOException { return this.in.next(); }
/*     */     
/*  47 */     public int read(int[] docs, int[] freqs) throws IOException { return this.in.read(docs, freqs); }
/*     */     
/*  49 */     public boolean skipTo(int i) throws IOException { return this.in.skipTo(i); }
/*  50 */     public void close() throws IOException { this.in.close(); }
/*     */   }
/*     */   
/*     */   public static class FilterTermPositions extends FilterIndexReader.FilterTermDocs implements TermPositions
/*     */   {
/*     */     public FilterTermPositions(TermPositions in)
/*     */     {
/*  57 */       super();
/*     */     }
/*     */     
/*  60 */     public int nextPosition() throws IOException { return ((TermPositions)this.in).nextPosition(); }
/*     */   }
/*     */   
/*     */   public static class FilterTermEnum
/*     */     extends TermEnum
/*     */   {
/*     */     protected TermEnum in;
/*     */     
/*  68 */     public FilterTermEnum(TermEnum in) { this.in = in; }
/*     */     
/*  70 */     public boolean next() throws IOException { return this.in.next(); }
/*  71 */     public Term term() { return this.in.term(); }
/*  72 */     public int docFreq() { return this.in.docFreq(); }
/*  73 */     public void close() throws IOException { this.in.close(); }
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public FilterIndexReader(IndexReader in)
/*     */   {
/*  86 */     super(in.directory());
/*  87 */     this.in = in;
/*     */   }
/*     */   
/*     */   public TermFreqVector[] getTermFreqVectors(int docNumber) throws IOException
/*     */   {
/*  92 */     return this.in.getTermFreqVectors(docNumber);
/*     */   }
/*     */   
/*     */   public TermFreqVector getTermFreqVector(int docNumber, String field) throws IOException
/*     */   {
/*  97 */     return this.in.getTermFreqVector(docNumber, field);
/*     */   }
/*     */   
/* 100 */   public int numDocs() { return this.in.numDocs(); }
/* 101 */   public int maxDoc() { return this.in.maxDoc(); }
/*     */   
/* 103 */   public Document document(int n) throws IOException { return this.in.document(n); }
/*     */   
/* 105 */   public boolean isDeleted(int n) { return this.in.isDeleted(n); }
/* 106 */   public boolean hasDeletions() { return this.in.hasDeletions(); }
/* 107 */   protected void doUndeleteAll() throws IOException { this.in.undeleteAll(); }
/*     */   
/* 109 */   public byte[] norms(String f) throws IOException { return this.in.norms(f); }
/*     */   
/* 111 */   public void norms(String f, byte[] bytes, int offset) throws IOException { this.in.norms(f, bytes, offset); }
/*     */   
/*     */   protected void doSetNorm(int d, String f, byte b) throws IOException {
/* 114 */     this.in.setNorm(d, f, b);
/*     */   }
/*     */   
/* 117 */   public TermEnum terms() throws IOException { return this.in.terms(); }
/* 118 */   public TermEnum terms(Term t) throws IOException { return this.in.terms(t); }
/*     */   
/* 120 */   public int docFreq(Term t) throws IOException { return this.in.docFreq(t); }
/*     */   
/* 122 */   public TermDocs termDocs() throws IOException { return this.in.termDocs(); }
/*     */   
/*     */   public TermPositions termPositions() throws IOException {
/* 125 */     return this.in.termPositions();
/*     */   }
/*     */   
/* 128 */   protected void doDelete(int n) throws IOException { this.in.delete(n); }
/* 129 */   protected void doCommit() throws IOException { this.in.commit(); }
/* 130 */   protected void doClose() throws IOException { this.in.close(); }
/*     */   
/*     */   public Collection getFieldNames() throws IOException {
/* 133 */     return this.in.getFieldNames();
/*     */   }
/*     */   
/*     */   public Collection getFieldNames(boolean indexed) throws IOException {
/* 137 */     return this.in.getFieldNames(indexed);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public Collection getIndexedFieldNames(boolean storedTermVector)
/*     */   {
/* 147 */     return this.in.getIndexedFieldNames(storedTermVector);
/*     */   }
/*     */ }


/* Location:              /home/menick/Scrivania/ProcessMining/CNMining/CNMining.jar!/org/apache/lucene/index/FilterIndexReader.class
 * Java compiler version: 2 (46.0)
 * JD-Core Version:       0.7.1
 */