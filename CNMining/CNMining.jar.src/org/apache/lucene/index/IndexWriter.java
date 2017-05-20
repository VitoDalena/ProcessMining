/*     */ package org.apache.lucene.index;
/*     */ 
/*     */ import java.io.File;
/*     */ import java.io.IOException;
/*     */ import java.io.PrintStream;
/*     */ import java.util.Vector;
/*     */ import org.apache.lucene.analysis.Analyzer;
/*     */ import org.apache.lucene.document.Document;
/*     */ import org.apache.lucene.search.Similarity;
/*     */ import org.apache.lucene.store.Directory;
/*     */ import org.apache.lucene.store.FSDirectory;
/*     */ import org.apache.lucene.store.InputStream;
/*     */ import org.apache.lucene.store.Lock;
/*     */ import org.apache.lucene.store.Lock.With;
/*     */ import org.apache.lucene.store.OutputStream;
/*     */ import org.apache.lucene.store.RAMDirectory;
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
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class IndexWriter
/*     */ {
/*  57 */   public static long WRITE_LOCK_TIMEOUT = Integer.parseInt(System.getProperty("org.apache.lucene.writeLockTimeout", "1000"));
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*  65 */   public static long COMMIT_LOCK_TIMEOUT = Integer.parseInt(System.getProperty("org.apache.lucene.commitLockTimeout", "10000"));
/*     */   
/*     */ 
/*     */ 
/*     */   public static final String WRITE_LOCK_NAME = "write.lock";
/*     */   
/*     */ 
/*     */ 
/*     */   public static final String COMMIT_LOCK_NAME = "commit.lock";
/*     */   
/*     */ 
/*  76 */   public static final int DEFAULT_MERGE_FACTOR = Integer.parseInt(System.getProperty("org.apache.lucene.mergeFactor", "10"));
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*  84 */   public static final int DEFAULT_MIN_MERGE_DOCS = Integer.parseInt(System.getProperty("org.apache.lucene.minMergeDocs", "10"));
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*  92 */   public static final int DEFAULT_MAX_MERGE_DOCS = Integer.parseInt(System.getProperty("org.apache.lucene.maxMergeDocs", String.valueOf(Integer.MAX_VALUE)));
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/* 100 */   public static final int DEFAULT_MAX_FIELD_LENGTH = Integer.parseInt(System.getProperty("org.apache.lucene.maxFieldLength", "10000"));
/*     */   
/*     */ 
/*     */   private Directory directory;
/*     */   
/*     */ 
/*     */   private Analyzer analyzer;
/*     */   
/* 108 */   private Similarity similarity = Similarity.getDefault();
/*     */   
/* 110 */   private SegmentInfos segmentInfos = new SegmentInfos();
/* 111 */   private final Directory ramDirectory = new RAMDirectory();
/*     */   
/*     */ 
/*     */ 
/*     */   private Lock writeLock;
/*     */   
/*     */ 
/*     */ 
/* 119 */   private boolean useCompoundFile = true;
/*     */   
/*     */ 
/*     */   private boolean closeDir;
/*     */   
/*     */ 
/*     */ 
/*     */   public boolean getUseCompoundFile()
/*     */   {
/* 128 */     return this.useCompoundFile;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   public void setUseCompoundFile(boolean value)
/*     */   {
/* 136 */     this.useCompoundFile = value;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public void setSimilarity(Similarity similarity)
/*     */   {
/* 145 */     this.similarity = similarity;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   public Similarity getSimilarity()
/*     */   {
/* 153 */     return this.similarity;
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
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public IndexWriter(String path, Analyzer a, boolean create)
/*     */     throws IOException
/*     */   {
/* 173 */     this(FSDirectory.getDirectory(path, create), a, create, true);
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
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public IndexWriter(File path, Analyzer a, boolean create)
/*     */     throws IOException
/*     */   {
/* 193 */     this(FSDirectory.getDirectory(path, create), a, create, true);
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
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public IndexWriter(Directory d, Analyzer a, boolean create)
/*     */     throws IOException
/*     */   {
/* 213 */     this(d, a, create, false);
/*     */   }
/*     */   
/*     */   private IndexWriter(Directory d, Analyzer a, boolean create, boolean closeDir) throws IOException
/*     */   {
/* 218 */     this.closeDir = closeDir;
/* 219 */     this.directory = d;
/* 220 */     this.analyzer = a;
/*     */     
/* 222 */     Lock writeLock = this.directory.makeLock("write.lock");
/* 223 */     if (!writeLock.obtain(WRITE_LOCK_TIMEOUT))
/* 224 */       throw new IOException("Index locked for write: " + writeLock);
/* 225 */     this.writeLock = writeLock;
/*     */     
/* 227 */     synchronized (this.directory) {
/* 228 */       new Lock.With(this.directory.makeLock("commit.lock"), COMMIT_LOCK_TIMEOUT) { private final boolean val$create;
/*     */         
/* 230 */         public Object doBody() throws IOException { if (this.val$create) {
/* 231 */             IndexWriter.this.segmentInfos.write(IndexWriter.this.directory);
/*     */           } else
/* 233 */             IndexWriter.this.segmentInfos.read(IndexWriter.this.directory);
/* 234 */           return null;
/*     */         }
/*     */       }.run();
/*     */     }
/*     */   }
/*     */   
/*     */   public synchronized void close() throws IOException
/*     */   {
/* 242 */     flushRamSegments();
/* 243 */     this.ramDirectory.close();
/* 244 */     this.writeLock.release();
/* 245 */     this.writeLock = null;
/* 246 */     if (this.closeDir) {
/* 247 */       this.directory.close();
/*     */     }
/*     */   }
/*     */   
/*     */   protected void finalize() throws IOException {
/* 252 */     if (this.writeLock != null) {
/* 253 */       this.writeLock.release();
/* 254 */       this.writeLock = null;
/*     */     }
/*     */   }
/*     */   
/*     */   public Analyzer getAnalyzer()
/*     */   {
/* 260 */     return this.analyzer;
/*     */   }
/*     */   
/*     */ 
/*     */   public synchronized int docCount()
/*     */   {
/* 266 */     int count = 0;
/* 267 */     for (int i = 0; i < this.segmentInfos.size(); i++) {
/* 268 */       SegmentInfo si = this.segmentInfos.info(i);
/* 269 */       count += si.docCount;
/*     */     }
/* 271 */     return count;
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
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/* 286 */   public int maxFieldLength = DEFAULT_MAX_FIELD_LENGTH;
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   public void addDocument(Document doc)
/*     */     throws IOException
/*     */   {
/* 294 */     addDocument(doc, this.analyzer);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public void addDocument(Document doc, Analyzer analyzer)
/*     */     throws IOException
/*     */   {
/* 304 */     DocumentWriter dw = new DocumentWriter(this.ramDirectory, analyzer, this.similarity, this.maxFieldLength);
/*     */     
/* 306 */     String segmentName = newSegmentName();
/* 307 */     dw.addDocument(segmentName, doc);
/* 308 */     synchronized (this) {
/* 309 */       this.segmentInfos.addElement(new SegmentInfo(segmentName, 1, this.ramDirectory));
/* 310 */       maybeMergeSegments();
/*     */     }
/*     */   }
/*     */   
/*     */   final int getSegmentsCounter() {
/* 315 */     return this.segmentInfos.counter;
/*     */   }
/*     */   
/*     */   private final synchronized String newSegmentName() {
/* 319 */     return "_" + Integer.toString(this.segmentInfos.counter++, 36);
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
/*     */ 
/* 331 */   public int mergeFactor = DEFAULT_MERGE_FACTOR;
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/* 340 */   public int minMergeDocs = DEFAULT_MIN_MERGE_DOCS;
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/* 349 */   public int maxMergeDocs = DEFAULT_MAX_MERGE_DOCS;
/*     */   
/*     */ 
/* 352 */   public PrintStream infoStream = null;
/*     */   
/*     */   public synchronized void optimize()
/*     */     throws IOException
/*     */   {
/* 357 */     flushRamSegments();
/* 358 */     while ((this.segmentInfos.size() > 1) || ((this.segmentInfos.size() == 1) && ((SegmentReader.hasDeletions(this.segmentInfos.info(0))) || (this.segmentInfos.info(0).dir != this.directory) || ((this.useCompoundFile) && ((!SegmentReader.usesCompoundFile(this.segmentInfos.info(0))) || (SegmentReader.hasSeparateNorms(this.segmentInfos.info(0))))))))
/*     */     {
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/* 365 */       int minSegment = this.segmentInfos.size() - this.mergeFactor;
/* 366 */       mergeSegments(minSegment < 0 ? 0 : minSegment);
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
/*     */ 
/*     */   public synchronized void addIndexes(Directory[] dirs)
/*     */     throws IOException
/*     */   {
/* 381 */     optimize();
/* 382 */     for (int i = 0; i < dirs.length; i++) {
/* 383 */       SegmentInfos sis = new SegmentInfos();
/* 384 */       sis.read(dirs[i]);
/* 385 */       for (int j = 0; j < sis.size(); j++) {
/* 386 */         this.segmentInfos.addElement(sis.info(j));
/*     */       }
/*     */     }
/* 389 */     optimize();
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public synchronized void addIndexes(IndexReader[] readers)
/*     */     throws IOException
/*     */   {
/* 399 */     optimize();
/*     */     
/* 401 */     String mergedName = newSegmentName();
/* 402 */     SegmentMerger merger = new SegmentMerger(this.directory, mergedName, false);
/*     */     
/* 404 */     if (this.segmentInfos.size() == 1) {
/* 405 */       merger.add(new SegmentReader(this.segmentInfos.info(0)));
/*     */     }
/* 407 */     for (int i = 0; i < readers.length; i++) {
/* 408 */       merger.add(readers[i]);
/*     */     }
/* 410 */     int docCount = merger.merge();
/*     */     
/* 412 */     this.segmentInfos.setSize(0);
/* 413 */     this.segmentInfos.addElement(new SegmentInfo(mergedName, docCount, this.directory));
/*     */     
/* 415 */     synchronized (this.directory) {
/* 416 */       new Lock.With(this.directory.makeLock("commit.lock"), COMMIT_LOCK_TIMEOUT) {
/*     */         public Object doBody() throws IOException {
/* 418 */           IndexWriter.this.segmentInfos.write(IndexWriter.this.directory);
/* 419 */           return null;
/*     */         }
/*     */       }.run();
/*     */     }
/*     */   }
/*     */   
/*     */   private final void flushRamSegments() throws IOException
/*     */   {
/* 427 */     int minSegment = this.segmentInfos.size() - 1;
/* 428 */     int docCount = 0;
/*     */     
/* 430 */     while ((minSegment >= 0) && (this.segmentInfos.info(minSegment).dir == this.ramDirectory)) {
/* 431 */       docCount += this.segmentInfos.info(minSegment).docCount;
/* 432 */       minSegment--;
/*     */     }
/* 434 */     if ((minSegment < 0) || (docCount + this.segmentInfos.info(minSegment).docCount > this.mergeFactor) || (this.segmentInfos.info(this.segmentInfos.size() - 1).dir != this.ramDirectory))
/*     */     {
/*     */ 
/* 437 */       minSegment++; }
/* 438 */     if (minSegment >= this.segmentInfos.size())
/* 439 */       return;
/* 440 */     mergeSegments(minSegment);
/*     */   }
/*     */   
/*     */   private final void maybeMergeSegments() throws IOException
/*     */   {
/* 445 */     long targetMergeDocs = this.minMergeDocs;
/* 446 */     while (targetMergeDocs <= this.maxMergeDocs)
/*     */     {
/* 448 */       int minSegment = this.segmentInfos.size();
/* 449 */       int mergeDocs = 0;
/* 450 */       for (;;) { minSegment--; if (minSegment < 0) break;
/* 451 */         SegmentInfo si = this.segmentInfos.info(minSegment);
/* 452 */         if (si.docCount >= targetMergeDocs)
/*     */           break;
/* 454 */         mergeDocs += si.docCount;
/*     */       }
/*     */       
/* 457 */       if (mergeDocs < targetMergeDocs) break;
/* 458 */       mergeSegments(minSegment + 1);
/*     */       
/*     */ 
/*     */ 
/* 462 */       targetMergeDocs *= this.mergeFactor;
/*     */     }
/*     */   }
/*     */   
/*     */ 
/*     */   private final void mergeSegments(int minSegment)
/*     */     throws IOException
/*     */   {
/* 470 */     String mergedName = newSegmentName();
/* 471 */     if (this.infoStream != null) this.infoStream.print("merging segments");
/* 472 */     SegmentMerger merger = new SegmentMerger(this.directory, mergedName, this.useCompoundFile);
/*     */     
/*     */ 
/* 475 */     Vector segmentsToDelete = new Vector();
/* 476 */     for (int i = minSegment; i < this.segmentInfos.size(); i++) {
/* 477 */       SegmentInfo si = this.segmentInfos.info(i);
/* 478 */       if (this.infoStream != null)
/* 479 */         this.infoStream.print(" " + si.name + " (" + si.docCount + " docs)");
/* 480 */       IndexReader reader = new SegmentReader(si);
/* 481 */       merger.add(reader);
/* 482 */       if ((reader.directory() == this.directory) || (reader.directory() == this.ramDirectory))
/*     */       {
/* 484 */         segmentsToDelete.addElement(reader);
/*     */       }
/*     */     }
/* 487 */     int mergedDocCount = merger.merge();
/*     */     
/* 489 */     if (this.infoStream != null) {
/* 490 */       this.infoStream.println(" into " + mergedName + " (" + mergedDocCount + " docs)");
/*     */     }
/*     */     
/* 493 */     this.segmentInfos.setSize(minSegment);
/* 494 */     this.segmentInfos.addElement(new SegmentInfo(mergedName, mergedDocCount, this.directory));
/*     */     
/*     */ 
/*     */ 
/* 498 */     merger.closeReaders();
/*     */     
/* 500 */     synchronized (this.directory) {
/* 501 */       new Lock.With(this.directory.makeLock("commit.lock"), COMMIT_LOCK_TIMEOUT) { private final Vector val$segmentsToDelete;
/*     */         
/* 503 */         public Object doBody() throws IOException { IndexWriter.this.segmentInfos.write(IndexWriter.this.directory);
/* 504 */           IndexWriter.this.deleteSegments(this.val$segmentsToDelete);
/* 505 */           return null;
/*     */         }
/*     */       }.run();
/*     */     }
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   private final void deleteSegments(Vector segments)
/*     */     throws IOException
/*     */   {
/* 518 */     Vector deletable = new Vector();
/*     */     
/* 520 */     deleteFiles(readDeleteableFiles(), deletable);
/*     */     
/* 522 */     for (int i = 0; i < segments.size(); i++) {
/* 523 */       SegmentReader reader = (SegmentReader)segments.elementAt(i);
/* 524 */       if (reader.directory() == this.directory) {
/* 525 */         deleteFiles(reader.files(), deletable);
/*     */       } else {
/* 527 */         deleteFiles(reader.files(), reader.directory());
/*     */       }
/*     */     }
/* 530 */     writeDeleteableFiles(deletable);
/*     */   }
/*     */   
/*     */   private final void deleteFiles(Vector files, Directory directory) throws IOException
/*     */   {
/* 535 */     for (int i = 0; i < files.size(); i++) {
/* 536 */       directory.deleteFile((String)files.elementAt(i));
/*     */     }
/*     */   }
/*     */   
/*     */   private final void deleteFiles(Vector files, Vector deletable) throws IOException {
/* 541 */     for (int i = 0; i < files.size(); i++) {
/* 542 */       String file = (String)files.elementAt(i);
/*     */       try {
/* 544 */         this.directory.deleteFile(file);
/*     */       } catch (IOException e) {
/* 546 */         if (this.directory.fileExists(file)) {
/* 547 */           if (this.infoStream != null)
/* 548 */             this.infoStream.println(e.getMessage() + "; Will re-try later.");
/* 549 */           deletable.addElement(file);
/*     */         }
/*     */       }
/*     */     }
/*     */   }
/*     */   
/*     */   private final Vector readDeleteableFiles() throws IOException {
/* 556 */     Vector result = new Vector();
/* 557 */     if (!this.directory.fileExists("deletable")) {
/* 558 */       return result;
/*     */     }
/* 560 */     InputStream input = this.directory.openFile("deletable");
/*     */     try {
/* 562 */       for (int i = input.readInt(); i > 0; i--)
/* 563 */         result.addElement(input.readString());
/*     */     } finally {
/* 565 */       input.close();
/*     */     }
/* 567 */     return result;
/*     */   }
/*     */   
/*     */   private final void writeDeleteableFiles(Vector files) throws IOException {
/* 571 */     OutputStream output = this.directory.createFile("deleteable.new");
/*     */     try {
/* 573 */       output.writeInt(files.size());
/* 574 */       for (int i = 0; i < files.size(); i++)
/* 575 */         output.writeString((String)files.elementAt(i));
/*     */     } finally {
/* 577 */       output.close();
/*     */     }
/* 579 */     this.directory.renameFile("deleteable.new", "deletable");
/*     */   }
/*     */ }


/* Location:              /home/menick/Scrivania/ProcessMining/CNMining/CNMining.jar!/org/apache/lucene/index/IndexWriter.class
 * Java compiler version: 2 (46.0)
 * JD-Core Version:       0.7.1
 */