/*     */ package org.apache.lucene.index;
/*     */ 
/*     */ import java.io.IOException;
/*     */ import java.util.Collection;
/*     */ import java.util.Enumeration;
/*     */ import java.util.HashSet;
/*     */ import java.util.Hashtable;
/*     */ import java.util.Set;
/*     */ import java.util.Vector;
/*     */ import org.apache.lucene.document.Document;
/*     */ import org.apache.lucene.store.Directory;
/*     */ import org.apache.lucene.store.InputStream;
/*     */ import org.apache.lucene.store.OutputStream;
/*     */ import org.apache.lucene.util.BitVector;
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
/*     */ 
/*     */ final class SegmentReader
/*     */   extends IndexReader
/*     */ {
/*     */   private String segment;
/*     */   FieldInfos fieldInfos;
/*     */   private FieldsReader fieldsReader;
/*     */   TermInfosReader tis;
/*     */   TermVectorsReader termVectorsReader;
/*  47 */   BitVector deletedDocs = null;
/*  48 */   private boolean deletedDocsDirty = false;
/*  49 */   private boolean normsDirty = false;
/*  50 */   private boolean undeleteAll = false;
/*     */   InputStream freqStream;
/*     */   InputStream proxStream;
/*     */   CompoundFileReader cfsReader;
/*     */   
/*     */   private class Norm {
/*     */     private InputStream in;
/*     */     private byte[] bytes;
/*     */     private boolean dirty;
/*     */     private int number;
/*     */     
/*  61 */     public Norm(InputStream in, int number) { this.in = in;
/*  62 */       this.number = number;
/*     */     }
/*     */     
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     private void reWrite()
/*     */       throws IOException
/*     */     {
/*  72 */       OutputStream out = SegmentReader.this.directory().createFile(SegmentReader.this.segment + ".tmp");
/*     */       try {
/*  74 */         out.writeBytes(this.bytes, SegmentReader.this.maxDoc());
/*     */       } finally {
/*  76 */         out.close();
/*     */       }
/*  78 */       String fileName = SegmentReader.this.segment + ".f" + this.number;
/*  79 */       SegmentReader.this.directory().renameFile(SegmentReader.this.segment + ".tmp", fileName);
/*  80 */       this.dirty = false;
/*     */     }
/*     */   }
/*     */   
/*  84 */   private Hashtable norms = new Hashtable();
/*     */   
/*     */   SegmentReader(SegmentInfos sis, SegmentInfo si, boolean closeDir) throws IOException
/*     */   {
/*  88 */     super(si.dir, sis, closeDir);
/*  89 */     initialize(si);
/*     */   }
/*     */   
/*     */   SegmentReader(SegmentInfo si) throws IOException {
/*  93 */     super(si.dir);
/*  94 */     initialize(si);
/*     */   }
/*     */   
/*     */   private void initialize(SegmentInfo si) throws IOException
/*     */   {
/*  99 */     this.segment = si.name;
/*     */     
/*     */ 
/* 102 */     Directory cfsDir = directory();
/* 103 */     if (directory().fileExists(this.segment + ".cfs")) {
/* 104 */       this.cfsReader = new CompoundFileReader(directory(), this.segment + ".cfs");
/* 105 */       cfsDir = this.cfsReader;
/*     */     }
/*     */     
/*     */ 
/* 109 */     this.fieldInfos = new FieldInfos(cfsDir, this.segment + ".fnm");
/* 110 */     this.fieldsReader = new FieldsReader(cfsDir, this.segment, this.fieldInfos);
/*     */     
/* 112 */     this.tis = new TermInfosReader(cfsDir, this.segment, this.fieldInfos);
/*     */     
/*     */ 
/* 115 */     if (hasDeletions(si)) {
/* 116 */       this.deletedDocs = new BitVector(directory(), this.segment + ".del");
/*     */     }
/*     */     
/*     */ 
/* 120 */     this.freqStream = cfsDir.openFile(this.segment + ".frq");
/* 121 */     this.proxStream = cfsDir.openFile(this.segment + ".prx");
/* 122 */     openNorms(cfsDir);
/*     */     
/* 124 */     if (this.fieldInfos.hasVectors()) {
/* 125 */       this.termVectorsReader = new TermVectorsReader(cfsDir, this.segment, this.fieldInfos);
/*     */     }
/*     */   }
/*     */   
/*     */   protected final void doCommit() throws IOException {
/* 130 */     if (this.deletedDocsDirty) {
/* 131 */       this.deletedDocs.write(directory(), this.segment + ".tmp");
/* 132 */       directory().renameFile(this.segment + ".tmp", this.segment + ".del");
/*     */     }
/* 134 */     if ((this.undeleteAll) && (directory().fileExists(this.segment + ".del"))) {
/* 135 */       directory().deleteFile(this.segment + ".del");
/*     */     }
/* 137 */     if (this.normsDirty) {
/* 138 */       Enumeration values = this.norms.elements();
/* 139 */       while (values.hasMoreElements()) {
/* 140 */         Norm norm = (Norm)values.nextElement();
/* 141 */         if (norm.dirty) {
/* 142 */           norm.reWrite();
/*     */         }
/*     */       }
/*     */     }
/* 146 */     this.deletedDocsDirty = false;
/* 147 */     this.normsDirty = false;
/* 148 */     this.undeleteAll = false;
/*     */   }
/*     */   
/*     */   protected final void doClose() throws IOException {
/* 152 */     this.fieldsReader.close();
/* 153 */     this.tis.close();
/*     */     
/* 155 */     if (this.freqStream != null)
/* 156 */       this.freqStream.close();
/* 157 */     if (this.proxStream != null) {
/* 158 */       this.proxStream.close();
/*     */     }
/* 160 */     closeNorms();
/* 161 */     if (this.termVectorsReader != null) { this.termVectorsReader.close();
/*     */     }
/* 163 */     if (this.cfsReader != null)
/* 164 */       this.cfsReader.close();
/*     */   }
/*     */   
/*     */   static final boolean hasDeletions(SegmentInfo si) throws IOException {
/* 168 */     return si.dir.fileExists(si.name + ".del");
/*     */   }
/*     */   
/*     */   public boolean hasDeletions() {
/* 172 */     return this.deletedDocs != null;
/*     */   }
/*     */   
/*     */   static final boolean usesCompoundFile(SegmentInfo si) throws IOException
/*     */   {
/* 177 */     return si.dir.fileExists(si.name + ".cfs");
/*     */   }
/*     */   
/*     */   static final boolean hasSeparateNorms(SegmentInfo si) throws IOException {
/* 181 */     String[] result = si.dir.list();
/* 182 */     String pattern = si.name + ".f";
/* 183 */     int patternLength = pattern.length();
/* 184 */     for (int i = 0; i < 0; i++) {
/* 185 */       if ((result[i].startsWith(pattern)) && (Character.isDigit(result[i].charAt(patternLength))))
/* 186 */         return true;
/*     */     }
/* 188 */     return false;
/*     */   }
/*     */   
/*     */   protected final void doDelete(int docNum) throws IOException {
/* 192 */     if (this.deletedDocs == null)
/* 193 */       this.deletedDocs = new BitVector(maxDoc());
/* 194 */     this.deletedDocsDirty = true;
/* 195 */     this.undeleteAll = false;
/* 196 */     this.deletedDocs.set(docNum);
/*     */   }
/*     */   
/*     */   protected final void doUndeleteAll() throws IOException {
/* 200 */     this.deletedDocs = null;
/* 201 */     this.deletedDocsDirty = false;
/* 202 */     this.undeleteAll = true;
/*     */   }
/*     */   
/*     */   final Vector files() throws IOException {
/* 206 */     Vector files = new Vector(16);
/* 207 */     String[] ext = { "cfs", "fnm", "fdx", "fdt", "tii", "tis", "frq", "prx", "del", "tvx", "tvd", "tvf", "tvp" };
/*     */     
/*     */ 
/*     */ 
/* 211 */     for (int i = 0; i < ext.length; i++) {
/* 212 */       String name = this.segment + "." + ext[i];
/* 213 */       if (directory().fileExists(name)) {
/* 214 */         files.addElement(name);
/*     */       }
/*     */     }
/* 217 */     for (int i = 0; i < this.fieldInfos.size(); i++) {
/* 218 */       FieldInfo fi = this.fieldInfos.fieldInfo(i);
/* 219 */       if (fi.isIndexed)
/* 220 */         files.addElement(this.segment + ".f" + i);
/*     */     }
/* 222 */     return files;
/*     */   }
/*     */   
/*     */   public final TermEnum terms() throws IOException {
/* 226 */     return this.tis.terms();
/*     */   }
/*     */   
/*     */   public final TermEnum terms(Term t) throws IOException {
/* 230 */     return this.tis.terms(t);
/*     */   }
/*     */   
/*     */   public final synchronized Document document(int n) throws IOException {
/* 234 */     if (isDeleted(n)) {
/* 235 */       throw new IllegalArgumentException("attempt to access a deleted document");
/*     */     }
/* 237 */     return this.fieldsReader.doc(n);
/*     */   }
/*     */   
/*     */   public final synchronized boolean isDeleted(int n) {
/* 241 */     return (this.deletedDocs != null) && (this.deletedDocs.get(n));
/*     */   }
/*     */   
/*     */   public final TermDocs termDocs() throws IOException {
/* 245 */     return new SegmentTermDocs(this);
/*     */   }
/*     */   
/*     */   public final TermPositions termPositions() throws IOException {
/* 249 */     return new SegmentTermPositions(this);
/*     */   }
/*     */   
/*     */   public final int docFreq(Term t) throws IOException {
/* 253 */     TermInfo ti = this.tis.get(t);
/* 254 */     if (ti != null) {
/* 255 */       return ti.docFreq;
/*     */     }
/* 257 */     return 0;
/*     */   }
/*     */   
/*     */   public final int numDocs() {
/* 261 */     int n = maxDoc();
/* 262 */     if (this.deletedDocs != null)
/* 263 */       n -= this.deletedDocs.count();
/* 264 */     return n;
/*     */   }
/*     */   
/*     */   public final int maxDoc() {
/* 268 */     return this.fieldsReader.size();
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */   public Collection getFieldNames()
/*     */     throws IOException
/*     */   {
/* 276 */     Set fieldSet = new HashSet();
/* 277 */     for (int i = 0; i < this.fieldInfos.size(); i++) {
/* 278 */       FieldInfo fi = this.fieldInfos.fieldInfo(i);
/* 279 */       fieldSet.add(fi.name);
/*     */     }
/* 281 */     return fieldSet;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */   public Collection getFieldNames(boolean indexed)
/*     */     throws IOException
/*     */   {
/* 289 */     Set fieldSet = new HashSet();
/* 290 */     for (int i = 0; i < this.fieldInfos.size(); i++) {
/* 291 */       FieldInfo fi = this.fieldInfos.fieldInfo(i);
/* 292 */       if (fi.isIndexed == indexed)
/* 293 */         fieldSet.add(fi.name);
/*     */     }
/* 295 */     return fieldSet;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public Collection getIndexedFieldNames(boolean storedTermVector)
/*     */   {
/* 306 */     Set fieldSet = new HashSet();
/* 307 */     for (int i = 0; i < this.fieldInfos.size(); i++) {
/* 308 */       FieldInfo fi = this.fieldInfos.fieldInfo(i);
/* 309 */       if ((fi.isIndexed == true) && (fi.storeTermVector == storedTermVector)) {
/* 310 */         fieldSet.add(fi.name);
/*     */       }
/*     */     }
/* 313 */     return fieldSet;
/*     */   }
/*     */   
/*     */   public synchronized byte[] norms(String field) throws IOException
/*     */   {
/* 318 */     Norm norm = (Norm)this.norms.get(field);
/* 319 */     if (norm == null)
/* 320 */       return null;
/* 321 */     if (norm.bytes == null) {
/* 322 */       byte[] bytes = new byte[maxDoc()];
/* 323 */       norms(field, bytes, 0);
/* 324 */       norm.bytes = bytes;
/*     */     }
/* 326 */     return norm.bytes;
/*     */   }
/*     */   
/*     */   protected final void doSetNorm(int doc, String field, byte value) throws IOException
/*     */   {
/* 331 */     Norm norm = (Norm)this.norms.get(field);
/* 332 */     if (norm == null)
/* 333 */       return;
/* 334 */     norm.dirty = true;
/* 335 */     this.normsDirty = true;
/*     */     
/* 337 */     norms(field)[doc] = value;
/*     */   }
/*     */   
/*     */ 
/*     */   public synchronized void norms(String field, byte[] bytes, int offset)
/*     */     throws IOException
/*     */   {
/* 344 */     Norm norm = (Norm)this.norms.get(field);
/* 345 */     if (norm == null) {
/* 346 */       return;
/*     */     }
/* 348 */     if (norm.bytes != null) {
/* 349 */       System.arraycopy(norm.bytes, 0, bytes, offset, maxDoc());
/* 350 */       return;
/*     */     }
/*     */     
/* 353 */     InputStream normStream = (InputStream)norm.in.clone();
/*     */     try {
/* 355 */       normStream.seek(0L);
/* 356 */       normStream.readBytes(bytes, offset, maxDoc());
/*     */     } finally {
/* 358 */       normStream.close();
/*     */     }
/*     */   }
/*     */   
/*     */   private final void openNorms(Directory cfsDir) throws IOException {
/* 363 */     for (int i = 0; i < this.fieldInfos.size(); i++) {
/* 364 */       FieldInfo fi = this.fieldInfos.fieldInfo(i);
/* 365 */       if (fi.isIndexed) {
/* 366 */         String fileName = this.segment + ".f" + fi.number;
/*     */         
/* 368 */         Directory d = directory().fileExists(fileName) ? directory() : cfsDir;
/* 369 */         this.norms.put(fi.name, new Norm(d.openFile(fileName), fi.number));
/*     */       }
/*     */     }
/*     */   }
/*     */   
/*     */   private final void closeNorms() throws IOException {
/* 375 */     synchronized (this.norms) {
/* 376 */       Enumeration enumerator = this.norms.elements();
/* 377 */       while (enumerator.hasMoreElements()) {
/* 378 */         Norm norm = (Norm)enumerator.nextElement();
/* 379 */         norm.in.close();
/*     */       }
/*     */     }
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public TermFreqVector getTermFreqVector(int docNumber, String field)
/*     */     throws IOException
/*     */   {
/* 392 */     FieldInfo fi = this.fieldInfos.fieldInfo(field);
/* 393 */     if ((fi == null) || (!fi.storeTermVector)) { return null;
/*     */     }
/* 395 */     return this.termVectorsReader.get(docNumber, field);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public TermFreqVector[] getTermFreqVectors(int docNumber)
/*     */     throws IOException
/*     */   {
/* 407 */     if (this.termVectorsReader == null) {
/* 408 */       return null;
/*     */     }
/* 410 */     return this.termVectorsReader.get(docNumber);
/*     */   }
/*     */ }


/* Location:              /home/menick/Scrivania/ProcessMining/CNMining/CNMining.jar!/org/apache/lucene/index/SegmentReader.class
 * Java compiler version: 2 (46.0)
 * JD-Core Version:       0.7.1
 */