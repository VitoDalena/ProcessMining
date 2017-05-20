/*     */ package org.apache.lucene.index;
/*     */ 
/*     */ import java.io.IOException;
/*     */ import java.util.Collection;
/*     */ import java.util.HashSet;
/*     */ import java.util.Hashtable;
/*     */ import java.util.Iterator;
/*     */ import java.util.Set;
/*     */ import org.apache.lucene.document.Document;
/*     */ import org.apache.lucene.store.Directory;
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
/*     */ public class MultiReader
/*     */   extends IndexReader
/*     */ {
/*     */   private IndexReader[] subReaders;
/*     */   private int[] starts;
/*  36 */   private Hashtable normsCache = new Hashtable();
/*  37 */   private int maxDoc = 0;
/*  38 */   private int numDocs = -1;
/*  39 */   private boolean hasDeletions = false;
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public MultiReader(IndexReader[] subReaders)
/*     */     throws IOException
/*     */   {
/*  50 */     super(subReaders.length == 0 ? null : subReaders[0].directory());
/*  51 */     initialize(subReaders);
/*     */   }
/*     */   
/*     */   MultiReader(Directory directory, SegmentInfos sis, boolean closeDirectory, IndexReader[] subReaders)
/*     */     throws IOException
/*     */   {
/*  57 */     super(directory, sis, closeDirectory);
/*  58 */     initialize(subReaders);
/*     */   }
/*     */   
/*     */   private void initialize(IndexReader[] subReaders) throws IOException {
/*  62 */     this.subReaders = subReaders;
/*  63 */     this.starts = new int[subReaders.length + 1];
/*  64 */     for (int i = 0; i < subReaders.length; i++) {
/*  65 */       this.starts[i] = this.maxDoc;
/*  66 */       this.maxDoc += subReaders[i].maxDoc();
/*     */       
/*  68 */       if (subReaders[i].hasDeletions())
/*  69 */         this.hasDeletions = true;
/*     */     }
/*  71 */     this.starts[subReaders.length] = this.maxDoc;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public TermFreqVector[] getTermFreqVectors(int n)
/*     */     throws IOException
/*     */   {
/*  82 */     int i = readerIndex(n);
/*  83 */     return this.subReaders[i].getTermFreqVectors(n - this.starts[i]);
/*     */   }
/*     */   
/*     */   public TermFreqVector getTermFreqVector(int n, String field) throws IOException
/*     */   {
/*  88 */     int i = readerIndex(n);
/*  89 */     return this.subReaders[i].getTermFreqVector(n - this.starts[i], field);
/*     */   }
/*     */   
/*     */   public synchronized int numDocs() {
/*  93 */     if (this.numDocs == -1) {
/*  94 */       int n = 0;
/*  95 */       for (int i = 0; i < this.subReaders.length; i++)
/*  96 */         n += this.subReaders[i].numDocs();
/*  97 */       this.numDocs = n;
/*     */     }
/*  99 */     return this.numDocs;
/*     */   }
/*     */   
/*     */   public int maxDoc() {
/* 103 */     return this.maxDoc;
/*     */   }
/*     */   
/*     */   public Document document(int n) throws IOException {
/* 107 */     int i = readerIndex(n);
/* 108 */     return this.subReaders[i].document(n - this.starts[i]);
/*     */   }
/*     */   
/*     */   public boolean isDeleted(int n) {
/* 112 */     int i = readerIndex(n);
/* 113 */     return this.subReaders[i].isDeleted(n - this.starts[i]);
/*     */   }
/*     */   
/* 116 */   public boolean hasDeletions() { return this.hasDeletions; }
/*     */   
/*     */   protected void doDelete(int n) throws IOException {
/* 119 */     this.numDocs = -1;
/* 120 */     int i = readerIndex(n);
/* 121 */     this.subReaders[i].delete(n - this.starts[i]);
/* 122 */     this.hasDeletions = true;
/*     */   }
/*     */   
/*     */   protected void doUndeleteAll() throws IOException {
/* 126 */     for (int i = 0; i < this.subReaders.length; i++)
/* 127 */       this.subReaders[i].undeleteAll();
/* 128 */     this.hasDeletions = false;
/*     */   }
/*     */   
/*     */   private int readerIndex(int n) {
/* 132 */     int lo = 0;
/* 133 */     int hi = this.subReaders.length - 1;
/*     */     
/* 135 */     while (hi >= lo) {
/* 136 */       int mid = lo + hi >> 1;
/* 137 */       int midValue = this.starts[mid];
/* 138 */       if (n < midValue) {
/* 139 */         hi = mid - 1;
/* 140 */       } else if (n > midValue) {
/* 141 */         lo = mid + 1;
/*     */       } else {
/* 143 */         while ((mid + 1 < this.subReaders.length) && (this.starts[(mid + 1)] == midValue)) {
/* 144 */           mid++;
/*     */         }
/* 146 */         return mid;
/*     */       }
/*     */     }
/* 149 */     return hi;
/*     */   }
/*     */   
/*     */   public synchronized byte[] norms(String field) throws IOException {
/* 153 */     byte[] bytes = (byte[])this.normsCache.get(field);
/* 154 */     if (bytes != null) {
/* 155 */       return bytes;
/*     */     }
/* 157 */     bytes = new byte[maxDoc()];
/* 158 */     for (int i = 0; i < this.subReaders.length; i++)
/* 159 */       this.subReaders[i].norms(field, bytes, this.starts[i]);
/* 160 */     this.normsCache.put(field, bytes);
/* 161 */     return bytes;
/*     */   }
/*     */   
/*     */   public synchronized void norms(String field, byte[] result, int offset) throws IOException
/*     */   {
/* 166 */     byte[] bytes = (byte[])this.normsCache.get(field);
/* 167 */     if (bytes != null) {
/* 168 */       System.arraycopy(bytes, 0, result, offset, maxDoc());
/*     */     }
/* 170 */     for (int i = 0; i < this.subReaders.length; i++) {
/* 171 */       this.subReaders[i].norms(field, result, offset + this.starts[i]);
/*     */     }
/*     */   }
/*     */   
/*     */   protected void doSetNorm(int n, String field, byte value) throws IOException {
/* 176 */     this.normsCache.remove(field);
/* 177 */     int i = readerIndex(n);
/* 178 */     this.subReaders[i].setNorm(n - this.starts[i], field, value);
/*     */   }
/*     */   
/*     */   public TermEnum terms() throws IOException {
/* 182 */     return new MultiTermEnum(this.subReaders, this.starts, null);
/*     */   }
/*     */   
/*     */   public TermEnum terms(Term term) throws IOException {
/* 186 */     return new MultiTermEnum(this.subReaders, this.starts, term);
/*     */   }
/*     */   
/*     */   public int docFreq(Term t) throws IOException {
/* 190 */     int total = 0;
/* 191 */     for (int i = 0; i < this.subReaders.length; i++)
/* 192 */       total += this.subReaders[i].docFreq(t);
/* 193 */     return total;
/*     */   }
/*     */   
/*     */   public TermDocs termDocs() throws IOException {
/* 197 */     return new MultiTermDocs(this.subReaders, this.starts);
/*     */   }
/*     */   
/*     */   public TermPositions termPositions() throws IOException {
/* 201 */     return new MultiTermPositions(this.subReaders, this.starts);
/*     */   }
/*     */   
/*     */   protected void doCommit() throws IOException {
/* 205 */     for (int i = 0; i < this.subReaders.length; i++)
/* 206 */       this.subReaders[i].commit();
/*     */   }
/*     */   
/*     */   protected synchronized void doClose() throws IOException {
/* 210 */     for (int i = 0; i < this.subReaders.length; i++) {
/* 211 */       this.subReaders[i].close();
/*     */     }
/*     */   }
/*     */   
/*     */ 
/*     */   public Collection getFieldNames()
/*     */     throws IOException
/*     */   {
/* 219 */     Set fieldSet = new HashSet();
/* 220 */     Iterator iterator; for (int i = 0; i < this.subReaders.length; i++) {
/* 221 */       IndexReader reader = this.subReaders[i];
/* 222 */       Collection names = reader.getFieldNames();
/*     */       
/* 224 */       for (iterator = names.iterator(); iterator.hasNext();) {
/* 225 */         String s = (String)iterator.next();
/* 226 */         fieldSet.add(s);
/*     */       }
/*     */     }
/* 229 */     return fieldSet;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */   public Collection getFieldNames(boolean indexed)
/*     */     throws IOException
/*     */   {
/* 237 */     Set fieldSet = new HashSet();
/* 238 */     for (int i = 0; i < this.subReaders.length; i++) {
/* 239 */       IndexReader reader = this.subReaders[i];
/* 240 */       Collection names = reader.getFieldNames(indexed);
/* 241 */       fieldSet.addAll(names);
/*     */     }
/* 243 */     return fieldSet;
/*     */   }
/*     */   
/*     */   public Collection getIndexedFieldNames(boolean storedTermVector)
/*     */   {
/* 248 */     Set fieldSet = new HashSet();
/* 249 */     for (int i = 0; i < this.subReaders.length; i++) {
/* 250 */       IndexReader reader = this.subReaders[i];
/* 251 */       Collection names = reader.getIndexedFieldNames(storedTermVector);
/* 252 */       fieldSet.addAll(names);
/*     */     }
/* 254 */     return fieldSet;
/*     */   }
/*     */ }


/* Location:              /home/menick/Scrivania/ProcessMining/CNMining/CNMining.jar!/org/apache/lucene/index/MultiReader.class
 * Java compiler version: 2 (46.0)
 * JD-Core Version:       0.7.1
 */