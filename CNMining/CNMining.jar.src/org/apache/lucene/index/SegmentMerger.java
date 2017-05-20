/*     */ package org.apache.lucene.index;
/*     */ 
/*     */ import java.io.IOException;
/*     */ import java.util.ArrayList;
/*     */ import java.util.Iterator;
/*     */ import java.util.Vector;
/*     */ import org.apache.lucene.store.Directory;
/*     */ import org.apache.lucene.store.OutputStream;
/*     */ import org.apache.lucene.store.RAMOutputStream;
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
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ final class SegmentMerger
/*     */ {
/*     */   private boolean useCompoundFile;
/*     */   private Directory directory;
/*     */   private String segment;
/*  44 */   private Vector readers = new Vector();
/*     */   
/*     */   private FieldInfos fieldInfos;
/*     */   
/*  48 */   private static final String[] COMPOUND_EXTENSIONS = { "fnm", "frq", "prx", "fdx", "fdt", "tii", "tis" };
/*     */   
/*     */ 
/*  51 */   private static final String[] VECTOR_EXTENSIONS = { "tvx", "tvd", "tvf" };
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   SegmentMerger(Directory dir, String name, boolean compoundFile)
/*     */   {
/*  62 */     this.directory = dir;
/*  63 */     this.segment = name;
/*  64 */     this.useCompoundFile = compoundFile;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   final void add(IndexReader reader)
/*     */   {
/*  72 */     this.readers.addElement(reader);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   final IndexReader segmentReader(int i)
/*     */   {
/*  81 */     return (IndexReader)this.readers.elementAt(i);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   final int merge()
/*     */     throws IOException
/*     */   {
/*  92 */     int value = mergeFields();
/*  93 */     mergeTerms();
/*  94 */     mergeNorms();
/*     */     
/*  96 */     if (this.fieldInfos.hasVectors()) {
/*  97 */       mergeVectors();
/*     */     }
/*  99 */     if (this.useCompoundFile) {
/* 100 */       createCompoundFile();
/*     */     }
/* 102 */     return value;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   final void closeReaders()
/*     */     throws IOException
/*     */   {
/* 111 */     for (int i = 0; i < this.readers.size(); i++) {
/* 112 */       IndexReader reader = (IndexReader)this.readers.elementAt(i);
/* 113 */       reader.close();
/*     */     }
/*     */   }
/*     */   
/*     */   private final void createCompoundFile() throws IOException
/*     */   {
/* 119 */     CompoundFileWriter cfsWriter = new CompoundFileWriter(this.directory, this.segment + ".cfs");
/*     */     
/*     */ 
/* 122 */     ArrayList files = new ArrayList(COMPOUND_EXTENSIONS.length + this.fieldInfos.size());
/*     */     
/*     */ 
/*     */ 
/* 126 */     for (int i = 0; i < COMPOUND_EXTENSIONS.length; i++) {
/* 127 */       files.add(this.segment + "." + COMPOUND_EXTENSIONS[i]);
/*     */     }
/*     */     
/*     */ 
/* 131 */     for (int i = 0; i < this.fieldInfos.size(); i++) {
/* 132 */       FieldInfo fi = this.fieldInfos.fieldInfo(i);
/* 133 */       if (fi.isIndexed) {
/* 134 */         files.add(this.segment + ".f" + i);
/*     */       }
/*     */     }
/*     */     
/*     */ 
/* 139 */     if (this.fieldInfos.hasVectors()) {
/* 140 */       for (int i = 0; i < VECTOR_EXTENSIONS.length; i++) {
/* 141 */         files.add(this.segment + "." + VECTOR_EXTENSIONS[i]);
/*     */       }
/*     */     }
/*     */     
/*     */ 
/* 146 */     Iterator it = files.iterator();
/* 147 */     while (it.hasNext()) {
/* 148 */       cfsWriter.addFile((String)it.next());
/*     */     }
/*     */     
/*     */ 
/* 152 */     cfsWriter.close();
/*     */     
/*     */ 
/* 155 */     it = files.iterator();
/* 156 */     while (it.hasNext()) {
/* 157 */       this.directory.deleteFile((String)it.next());
/*     */     }
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   private final int mergeFields()
/*     */     throws IOException
/*     */   {
/* 167 */     this.fieldInfos = new FieldInfos();
/* 168 */     int docCount = 0;
/* 169 */     for (int i = 0; i < this.readers.size(); i++) {
/* 170 */       IndexReader reader = (IndexReader)this.readers.elementAt(i);
/* 171 */       this.fieldInfos.addIndexed(reader.getIndexedFieldNames(true), true);
/* 172 */       this.fieldInfos.addIndexed(reader.getIndexedFieldNames(false), false);
/* 173 */       this.fieldInfos.add(reader.getFieldNames(false), false);
/*     */     }
/* 175 */     this.fieldInfos.write(this.directory, this.segment + ".fnm");
/*     */     
/* 177 */     FieldsWriter fieldsWriter = new FieldsWriter(this.directory, this.segment, this.fieldInfos);
/*     */     try
/*     */     {
/* 180 */       for (int i = 0; i < this.readers.size(); i++) {
/* 181 */         IndexReader reader = (IndexReader)this.readers.elementAt(i);
/* 182 */         int maxDoc = reader.maxDoc();
/* 183 */         for (int j = 0; j < maxDoc; j++)
/* 184 */           if (!reader.isDeleted(j)) {
/* 185 */             fieldsWriter.addDocument(reader.document(j));
/* 186 */             docCount++;
/*     */           }
/*     */       }
/*     */     } finally {
/* 190 */       fieldsWriter.close();
/*     */     }
/* 192 */     return docCount;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */   private final void mergeVectors()
/*     */     throws IOException
/*     */   {
/* 200 */     TermVectorsWriter termVectorsWriter = new TermVectorsWriter(this.directory, this.segment, this.fieldInfos);
/*     */     
/*     */     try
/*     */     {
/* 204 */       for (int r = 0; r < this.readers.size(); r++) {
/* 205 */         IndexReader reader = (IndexReader)this.readers.elementAt(r);
/* 206 */         int maxDoc = reader.maxDoc();
/* 207 */         for (int docNum = 0; docNum < maxDoc; docNum++)
/*     */         {
/* 209 */           if (!reader.isDeleted(docNum))
/*     */           {
/*     */ 
/* 212 */             termVectorsWriter.openDocument();
/*     */             
/*     */ 
/* 215 */             TermFreqVector[] sourceTermVector = reader.getTermFreqVectors(docNum);
/*     */             
/*     */ 
/* 218 */             if (sourceTermVector != null) {
/* 219 */               for (int f = 0; f < sourceTermVector.length; f++)
/*     */               {
/* 221 */                 TermFreqVector termVector = sourceTermVector[f];
/* 222 */                 termVectorsWriter.openField(termVector.getField());
/* 223 */                 String[] terms = termVector.getTerms();
/* 224 */                 int[] freqs = termVector.getTermFrequencies();
/*     */                 
/* 226 */                 for (int t = 0; t < terms.length; t++) {
/* 227 */                   termVectorsWriter.addTerm(terms[t], freqs[t]);
/*     */                 }
/*     */               }
/* 230 */               termVectorsWriter.closeDocument();
/*     */             }
/*     */           } }
/*     */       }
/*     */     } finally {
/* 235 */       termVectorsWriter.close();
/*     */     }
/*     */   }
/*     */   
/* 239 */   private OutputStream freqOutput = null;
/* 240 */   private OutputStream proxOutput = null;
/* 241 */   private TermInfosWriter termInfosWriter = null;
/*     */   private int skipInterval;
/* 243 */   private SegmentMergeQueue queue = null;
/*     */   
/*     */   private final void mergeTerms() throws IOException {
/*     */     try {
/* 247 */       this.freqOutput = this.directory.createFile(this.segment + ".frq");
/* 248 */       this.proxOutput = this.directory.createFile(this.segment + ".prx");
/* 249 */       this.termInfosWriter = new TermInfosWriter(this.directory, this.segment, this.fieldInfos);
/*     */       
/* 251 */       this.skipInterval = this.termInfosWriter.skipInterval;
/* 252 */       this.queue = new SegmentMergeQueue(this.readers.size());
/*     */       
/* 254 */       mergeTermInfos();
/*     */     }
/*     */     finally {
/* 257 */       if (this.freqOutput != null) this.freqOutput.close();
/* 258 */       if (this.proxOutput != null) this.proxOutput.close();
/* 259 */       if (this.termInfosWriter != null) this.termInfosWriter.close();
/* 260 */       if (this.queue != null) this.queue.close();
/*     */     }
/*     */   }
/*     */   
/*     */   private final void mergeTermInfos() throws IOException {
/* 265 */     int base = 0;
/* 266 */     for (int i = 0; i < this.readers.size(); i++) {
/* 267 */       IndexReader reader = (IndexReader)this.readers.elementAt(i);
/* 268 */       TermEnum termEnum = reader.terms();
/* 269 */       SegmentMergeInfo smi = new SegmentMergeInfo(base, termEnum, reader);
/* 270 */       base += reader.numDocs();
/* 271 */       if (smi.next()) {
/* 272 */         this.queue.put(smi);
/*     */       } else {
/* 274 */         smi.close();
/*     */       }
/*     */     }
/* 277 */     SegmentMergeInfo[] match = new SegmentMergeInfo[this.readers.size()];
/*     */     
/* 279 */     while (this.queue.size() > 0) {
/* 280 */       int matchSize = 0;
/* 281 */       match[(matchSize++)] = ((SegmentMergeInfo)this.queue.pop());
/* 282 */       Term term = match[0].term;
/* 283 */       SegmentMergeInfo top = (SegmentMergeInfo)this.queue.top();
/*     */       
/* 285 */       while ((top != null) && (term.compareTo(top.term) == 0)) {
/* 286 */         match[(matchSize++)] = ((SegmentMergeInfo)this.queue.pop());
/* 287 */         top = (SegmentMergeInfo)this.queue.top();
/*     */       }
/*     */       
/* 290 */       mergeTermInfo(match, matchSize);
/*     */       
/* 292 */       while (matchSize > 0) {
/* 293 */         SegmentMergeInfo smi = match[(--matchSize)];
/* 294 */         if (smi.next()) {
/* 295 */           this.queue.put(smi);
/*     */         } else
/* 297 */           smi.close();
/*     */       }
/*     */     }
/*     */   }
/*     */   
/* 302 */   private final TermInfo termInfo = new TermInfo();
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   private final void mergeTermInfo(SegmentMergeInfo[] smis, int n)
/*     */     throws IOException
/*     */   {
/* 313 */     long freqPointer = this.freqOutput.getFilePointer();
/* 314 */     long proxPointer = this.proxOutput.getFilePointer();
/*     */     
/* 316 */     int df = appendPostings(smis, n);
/*     */     
/* 318 */     long skipPointer = writeSkip();
/*     */     
/* 320 */     if (df > 0)
/*     */     {
/* 322 */       this.termInfo.set(df, freqPointer, proxPointer, (int)(skipPointer - freqPointer));
/* 323 */       this.termInfosWriter.add(smis[0].term, this.termInfo);
/*     */     }
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   private final int appendPostings(SegmentMergeInfo[] smis, int n)
/*     */     throws IOException
/*     */   {
/* 337 */     int lastDoc = 0;
/* 338 */     int df = 0;
/* 339 */     resetSkip();
/* 340 */     for (int i = 0; i < n; i++) {
/* 341 */       SegmentMergeInfo smi = smis[i];
/* 342 */       TermPositions postings = smi.postings;
/* 343 */       int base = smi.base;
/* 344 */       int[] docMap = smi.docMap;
/* 345 */       postings.seek(smi.termEnum);
/* 346 */       while (postings.next()) {
/* 347 */         int doc = postings.doc();
/* 348 */         if (docMap != null)
/* 349 */           doc = docMap[doc];
/* 350 */         doc += base;
/*     */         
/* 352 */         if (doc < lastDoc) {
/* 353 */           throw new IllegalStateException("docs out of order");
/*     */         }
/* 355 */         df++;
/*     */         
/* 357 */         if (df % this.skipInterval == 0) {
/* 358 */           bufferSkip(lastDoc);
/*     */         }
/*     */         
/* 361 */         int docCode = doc - lastDoc << 1;
/* 362 */         lastDoc = doc;
/*     */         
/* 364 */         int freq = postings.freq();
/* 365 */         if (freq == 1) {
/* 366 */           this.freqOutput.writeVInt(docCode | 0x1);
/*     */         } else {
/* 368 */           this.freqOutput.writeVInt(docCode);
/* 369 */           this.freqOutput.writeVInt(freq);
/*     */         }
/*     */         
/* 372 */         int lastPosition = 0;
/* 373 */         for (int j = 0; j < freq; j++) {
/* 374 */           int position = postings.nextPosition();
/* 375 */           this.proxOutput.writeVInt(position - lastPosition);
/* 376 */           lastPosition = position;
/*     */         }
/*     */       }
/*     */     }
/* 380 */     return df;
/*     */   }
/*     */   
/* 383 */   private RAMOutputStream skipBuffer = new RAMOutputStream();
/*     */   private int lastSkipDoc;
/*     */   private long lastSkipFreqPointer;
/*     */   private long lastSkipProxPointer;
/*     */   
/*     */   private void resetSkip() throws IOException {
/* 389 */     this.skipBuffer.reset();
/* 390 */     this.lastSkipDoc = 0;
/* 391 */     this.lastSkipFreqPointer = this.freqOutput.getFilePointer();
/* 392 */     this.lastSkipProxPointer = this.proxOutput.getFilePointer();
/*     */   }
/*     */   
/*     */   private void bufferSkip(int doc) throws IOException {
/* 396 */     long freqPointer = this.freqOutput.getFilePointer();
/* 397 */     long proxPointer = this.proxOutput.getFilePointer();
/*     */     
/* 399 */     this.skipBuffer.writeVInt(doc - this.lastSkipDoc);
/* 400 */     this.skipBuffer.writeVInt((int)(freqPointer - this.lastSkipFreqPointer));
/* 401 */     this.skipBuffer.writeVInt((int)(proxPointer - this.lastSkipProxPointer));
/*     */     
/* 403 */     this.lastSkipDoc = doc;
/* 404 */     this.lastSkipFreqPointer = freqPointer;
/* 405 */     this.lastSkipProxPointer = proxPointer;
/*     */   }
/*     */   
/*     */   private long writeSkip() throws IOException {
/* 409 */     long skipPointer = this.freqOutput.getFilePointer();
/* 410 */     this.skipBuffer.writeTo(this.freqOutput);
/* 411 */     return skipPointer;
/*     */   }
/*     */   
/*     */   private void mergeNorms() throws IOException {
/* 415 */     for (int i = 0; i < this.fieldInfos.size(); i++) {
/* 416 */       FieldInfo fi = this.fieldInfos.fieldInfo(i);
/* 417 */       if (fi.isIndexed) {
/* 418 */         OutputStream output = this.directory.createFile(this.segment + ".f" + i);
/*     */         try {
/* 420 */           for (int j = 0; j < this.readers.size(); j++) {
/* 421 */             IndexReader reader = (IndexReader)this.readers.elementAt(j);
/* 422 */             byte[] input = reader.norms(fi.name);
/* 423 */             int maxDoc = reader.maxDoc();
/* 424 */             for (int k = 0; k < maxDoc; k++) {
/* 425 */               byte norm = input != null ? input[k] : 0;
/* 426 */               if (!reader.isDeleted(k)) {
/* 427 */                 output.writeByte(norm);
/*     */               }
/*     */             }
/*     */           }
/*     */         } finally {
/* 432 */           output.close();
/*     */         }
/*     */       }
/*     */     }
/*     */   }
/*     */ }


/* Location:              /home/menick/Scrivania/ProcessMining/CNMining/CNMining.jar!/org/apache/lucene/index/SegmentMerger.class
 * Java compiler version: 2 (46.0)
 * JD-Core Version:       0.7.1
 */