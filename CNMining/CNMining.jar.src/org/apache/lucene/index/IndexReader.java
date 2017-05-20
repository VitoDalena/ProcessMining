/*     */ package org.apache.lucene.index;
/*     */ 
/*     */ import java.io.File;
/*     */ import java.io.IOException;
/*     */ import java.util.Collection;
/*     */ import org.apache.lucene.document.Document;
/*     */ import org.apache.lucene.search.Similarity;
/*     */ import org.apache.lucene.store.Directory;
/*     */ import org.apache.lucene.store.FSDirectory;
/*     */ import org.apache.lucene.store.Lock;
/*     */ import org.apache.lucene.store.Lock.With;
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
/*     */ public abstract class IndexReader
/*     */ {
/*     */   private final Directory directory;
/*     */   private final boolean directoryOwner;
/*     */   private final SegmentInfos segmentInfos;
/*     */   private Lock writeLock;
/*     */   private boolean stale;
/*     */   private boolean hasChanges;
/*     */   private final boolean closeDirectory;
/*     */   
/*     */   protected IndexReader(Directory directory)
/*     */   {
/*  55 */     this.directory = directory;
/*  56 */     this.segmentInfos = null;
/*  57 */     this.directoryOwner = false;
/*  58 */     this.closeDirectory = false;
/*  59 */     this.stale = false;
/*  60 */     this.hasChanges = false;
/*  61 */     this.writeLock = null;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   IndexReader(Directory directory, SegmentInfos segmentInfos, boolean closeDirectory)
/*     */   {
/*  73 */     this.directory = directory;
/*  74 */     this.segmentInfos = segmentInfos;
/*  75 */     this.directoryOwner = true;
/*  76 */     this.closeDirectory = closeDirectory;
/*  77 */     this.stale = false;
/*  78 */     this.hasChanges = false;
/*  79 */     this.writeLock = null;
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
/*     */   public static IndexReader open(String path)
/*     */     throws IOException
/*     */   {
/*  95 */     return open(FSDirectory.getDirectory(path, false), true);
/*     */   }
/*     */   
/*     */   public static IndexReader open(File path)
/*     */     throws IOException
/*     */   {
/* 101 */     return open(FSDirectory.getDirectory(path, false), true);
/*     */   }
/*     */   
/*     */   public static IndexReader open(Directory directory) throws IOException
/*     */   {
/* 106 */     return open(directory, false);
/*     */   }
/*     */   
/*     */   /* Error */
/*     */   private static IndexReader open(Directory directory, boolean closeDirectory)
/*     */     throws IOException
/*     */   {
/*     */     // Byte code:
/*     */     //   0: aload_0
/*     */     //   1: dup
/*     */     //   2: astore_2
/*     */     //   3: monitorenter
/*     */     //   4: new 12	org/apache/lucene/index/IndexReader$1
/*     */     //   7: dup
/*     */     //   8: aload_0
/*     */     //   9: ldc 13
/*     */     //   11: invokevirtual 14	org/apache/lucene/store/Directory:makeLock	(Ljava/lang/String;)Lorg/apache/lucene/store/Lock;
/*     */     //   14: getstatic 15	org/apache/lucene/index/IndexWriter:COMMIT_LOCK_TIMEOUT	J
/*     */     //   17: aload_0
/*     */     //   18: iload_1
/*     */     //   19: invokespecial 16	org/apache/lucene/index/IndexReader$1:<init>	(Lorg/apache/lucene/store/Lock;JLorg/apache/lucene/store/Directory;Z)V
/*     */     //   22: invokevirtual 17	org/apache/lucene/index/IndexReader$1:run	()Ljava/lang/Object;
/*     */     //   25: checkcast 18	org/apache/lucene/index/IndexReader
/*     */     //   28: aload_2
/*     */     //   29: monitorexit
/*     */     //   30: areturn
/*     */     //   31: astore_3
/*     */     //   32: aload_2
/*     */     //   33: monitorexit
/*     */     //   34: aload_3
/*     */     //   35: athrow
/*     */     // Line number table:
/*     */     //   Java source line #110	-> byte code offset #0
/*     */     //   Java source line #111	-> byte code offset #4
/*     */     //   Java source line #127	-> byte code offset #31
/*     */     // Local variable table:
/*     */     //   start	length	slot	name	signature
/*     */     //   0	36	0	directory	Directory
/*     */     //   0	36	1	closeDirectory	boolean
/*     */     //   2	31	2	Ljava/lang/Object;	Object
/*     */     //   31	4	3	localObject1	Object
/*     */     // Exception table:
/*     */     //   from	to	target	type
/*     */     //   4	30	31	finally
/*     */     //   31	34	31	finally
/*     */   }
/*     */   
/*     */   public Directory directory()
/*     */   {
/* 131 */     return this.directory;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   /**
/*     */    * @deprecated
/*     */    */
/*     */   public static long lastModified(String directory)
/*     */     throws IOException
/*     */   {
/* 145 */     return lastModified(new File(directory));
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   /**
/*     */    * @deprecated
/*     */    */
/*     */   public static long lastModified(File directory)
/*     */     throws IOException
/*     */   {
/* 160 */     return FSDirectory.fileModified(directory, "segments");
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   /**
/*     */    * @deprecated
/*     */    */
/*     */   public static long lastModified(Directory directory)
/*     */     throws IOException
/*     */   {
/* 175 */     return directory.fileModified("segments");
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public static long getCurrentVersion(String directory)
/*     */     throws IOException
/*     */   {
/* 187 */     return getCurrentVersion(new File(directory));
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public static long getCurrentVersion(File directory)
/*     */     throws IOException
/*     */   {
/* 199 */     Directory dir = FSDirectory.getDirectory(directory, false);
/* 200 */     long version = getCurrentVersion(dir);
/* 201 */     dir.close();
/* 202 */     return version;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public static long getCurrentVersion(Directory directory)
/*     */     throws IOException
/*     */   {
/* 214 */     return SegmentInfos.readCurrentVersion(directory);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public abstract TermFreqVector[] getTermFreqVectors(int paramInt)
/*     */     throws IOException;
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public abstract TermFreqVector getTermFreqVector(int paramInt, String paramString)
/*     */     throws IOException;
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public static boolean indexExists(String directory)
/*     */   {
/* 246 */     return new File(directory, "segments").exists();
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public static boolean indexExists(File directory)
/*     */   {
/* 256 */     return new File(directory, "segments").exists();
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public static boolean indexExists(Directory directory)
/*     */     throws IOException
/*     */   {
/* 267 */     return directory.fileExists("segments");
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public abstract int numDocs();
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   public abstract int maxDoc();
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   public abstract Document document(int paramInt)
/*     */     throws IOException;
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   public abstract boolean isDeleted(int paramInt);
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   public abstract boolean hasDeletions();
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   public abstract byte[] norms(String paramString)
/*     */     throws IOException;
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   public abstract void norms(String paramString, byte[] paramArrayOfByte, int paramInt)
/*     */     throws IOException;
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   public final synchronized void setNorm(int doc, String field, byte value)
/*     */     throws IOException
/*     */   {
/* 315 */     if (this.directoryOwner)
/* 316 */       aquireWriteLock();
/* 317 */     doSetNorm(doc, field, value);
/* 318 */     this.hasChanges = true;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   protected abstract void doSetNorm(int paramInt, String paramString, byte paramByte)
/*     */     throws IOException;
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   public void setNorm(int doc, String field, float value)
/*     */     throws IOException
/*     */   {
/* 333 */     setNorm(doc, field, Similarity.encodeNorm(value));
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public abstract TermEnum terms()
/*     */     throws IOException;
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public abstract TermEnum terms(Term paramTerm)
/*     */     throws IOException;
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public abstract int docFreq(Term paramTerm)
/*     */     throws IOException;
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   public TermDocs termDocs(Term term)
/*     */     throws IOException
/*     */   {
/* 363 */     TermDocs termDocs = termDocs();
/* 364 */     termDocs.seek(term);
/* 365 */     return termDocs;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public abstract TermDocs termDocs()
/*     */     throws IOException;
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public TermPositions termPositions(Term term)
/*     */     throws IOException
/*     */   {
/* 388 */     TermPositions termPositions = termPositions();
/* 389 */     termPositions.seek(term);
/* 390 */     return termPositions;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */   public abstract TermPositions termPositions()
/*     */     throws IOException;
/*     */   
/*     */ 
/*     */ 
/*     */   private void aquireWriteLock()
/*     */     throws IOException
/*     */   {
/* 403 */     if (this.stale) {
/* 404 */       throw new IOException("IndexReader out of date and no longer valid for delete, undelete, or setNorm operations");
/*     */     }
/* 406 */     if (this.writeLock == null) {
/* 407 */       Lock writeLock = this.directory.makeLock("write.lock");
/* 408 */       if (!writeLock.obtain(IndexWriter.WRITE_LOCK_TIMEOUT))
/* 409 */         throw new IOException("Index locked for write: " + writeLock);
/* 410 */       this.writeLock = writeLock;
/*     */       
/*     */ 
/*     */ 
/* 414 */       if (SegmentInfos.readCurrentVersion(this.directory) > this.segmentInfos.getVersion()) {
/* 415 */         this.stale = true;
/* 416 */         this.writeLock.release();
/* 417 */         this.writeLock = null;
/* 418 */         throw new IOException("IndexReader out of date and no longer valid for delete, undelete, or setNorm operations");
/*     */       }
/*     */     }
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public final synchronized void delete(int docNum)
/*     */     throws IOException
/*     */   {
/* 431 */     if (this.directoryOwner)
/* 432 */       aquireWriteLock();
/* 433 */     doDelete(docNum);
/* 434 */     this.hasChanges = true;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   protected abstract void doDelete(int paramInt)
/*     */     throws IOException;
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   public final int delete(Term term)
/*     */     throws IOException
/*     */   {
/* 449 */     TermDocs docs = termDocs(term);
/* 450 */     if (docs == null) return 0;
/* 451 */     int n = 0;
/*     */     try {
/* 453 */       while (docs.next()) {
/* 454 */         delete(docs.doc());
/* 455 */         n++;
/*     */       }
/*     */     } finally {
/* 458 */       docs.close();
/*     */     }
/* 460 */     return n;
/*     */   }
/*     */   
/*     */   public final synchronized void undeleteAll() throws IOException
/*     */   {
/* 465 */     if (this.directoryOwner)
/* 466 */       aquireWriteLock();
/* 467 */     doUndeleteAll();
/* 468 */     this.hasChanges = true;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */   protected abstract void doUndeleteAll()
/*     */     throws IOException;
/*     */   
/*     */ 
/*     */   protected final synchronized void commit()
/*     */     throws IOException
/*     */   {
/* 480 */     if (this.hasChanges)
/* 481 */       if (this.directoryOwner) {
/* 482 */         synchronized (this.directory) {
/* 483 */           new Lock.With(this.directory.makeLock("commit.lock"), IndexWriter.COMMIT_LOCK_TIMEOUT)
/*     */           {
/*     */             public Object doBody() throws IOException {
/* 486 */               IndexReader.this.doCommit();
/* 487 */               IndexReader.this.segmentInfos.write(IndexReader.this.directory);
/* 488 */               return null;
/*     */             }
/*     */           }.run();
/*     */         }
/* 492 */         if (this.writeLock != null) {
/* 493 */           this.writeLock.release();
/* 494 */           this.writeLock = null;
/*     */         }
/*     */       }
/*     */       else {
/* 498 */         doCommit();
/*     */       }
/* 500 */     this.hasChanges = false;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */   protected abstract void doCommit()
/*     */     throws IOException;
/*     */   
/*     */ 
/*     */   public final synchronized void close()
/*     */     throws IOException
/*     */   {
/* 512 */     commit();
/* 513 */     doClose();
/* 514 */     if (this.closeDirectory) {
/* 515 */       this.directory.close();
/*     */     }
/*     */   }
/*     */   
/*     */   protected abstract void doClose() throws IOException;
/*     */   
/*     */   protected final void finalize() throws IOException
/*     */   {
/* 523 */     if (this.writeLock != null) {
/* 524 */       this.writeLock.release();
/* 525 */       this.writeLock = null;
/*     */     }
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public abstract Collection getFieldNames()
/*     */     throws IOException;
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public abstract Collection getFieldNames(boolean paramBoolean)
/*     */     throws IOException;
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public abstract Collection getIndexedFieldNames(boolean paramBoolean);
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public static boolean isLocked(Directory directory)
/*     */     throws IOException
/*     */   {
/* 563 */     return (directory.makeLock("write.lock").isLocked()) || (directory.makeLock("commit.lock").isLocked());
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public static boolean isLocked(String directory)
/*     */     throws IOException
/*     */   {
/* 576 */     Directory dir = FSDirectory.getDirectory(directory, false);
/* 577 */     boolean result = isLocked(dir);
/* 578 */     dir.close();
/* 579 */     return result;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public static void unlock(Directory directory)
/*     */     throws IOException
/*     */   {
/* 590 */     directory.makeLock("write.lock").release();
/* 591 */     directory.makeLock("commit.lock").release();
/*     */   }
/*     */ }


/* Location:              /home/menick/Scrivania/ProcessMining/CNMining/CNMining.jar!/org/apache/lucene/index/IndexReader.class
 * Java compiler version: 2 (46.0)
 * JD-Core Version:       0.7.1
 */