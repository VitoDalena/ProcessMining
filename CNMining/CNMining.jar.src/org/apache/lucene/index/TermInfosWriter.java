/*     */ package org.apache.lucene.index;
/*     */ 
/*     */ import java.io.IOException;
/*     */ import org.apache.lucene.store.Directory;
/*     */ import org.apache.lucene.store.OutputStream;
/*     */ import org.apache.lucene.util.StringHelper;
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
/*     */ final class TermInfosWriter
/*     */ {
/*     */   public static final int FORMAT = -2;
/*     */   private FieldInfos fieldInfos;
/*     */   private OutputStream output;
/*  34 */   private Term lastTerm = new Term("", "");
/*  35 */   private TermInfo lastTi = new TermInfo();
/*  36 */   private long size = 0L;
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
/*  50 */   int indexInterval = 128;
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*  57 */   int skipInterval = 16;
/*     */   
/*  59 */   private long lastIndexPointer = 0L;
/*  60 */   private boolean isIndex = false;
/*     */   
/*  62 */   private TermInfosWriter other = null;
/*     */   
/*     */   TermInfosWriter(Directory directory, String segment, FieldInfos fis) throws IOException
/*     */   {
/*  66 */     initialize(directory, segment, fis, false);
/*  67 */     this.other = new TermInfosWriter(directory, segment, fis, true);
/*  68 */     this.other.other = this;
/*     */   }
/*     */   
/*     */   private TermInfosWriter(Directory directory, String segment, FieldInfos fis, boolean isIndex) throws IOException
/*     */   {
/*  73 */     initialize(directory, segment, fis, isIndex);
/*     */   }
/*     */   
/*     */   private void initialize(Directory directory, String segment, FieldInfos fis, boolean isi) throws IOException
/*     */   {
/*  78 */     this.fieldInfos = fis;
/*  79 */     this.isIndex = isi;
/*  80 */     this.output = directory.createFile(segment + (this.isIndex ? ".tii" : ".tis"));
/*  81 */     this.output.writeInt(-2);
/*  82 */     this.output.writeLong(0L);
/*  83 */     this.output.writeInt(this.indexInterval);
/*  84 */     this.output.writeInt(this.skipInterval);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */   final void add(Term term, TermInfo ti)
/*     */     throws IOException
/*     */   {
/*  92 */     if ((!this.isIndex) && (term.compareTo(this.lastTerm) <= 0))
/*  93 */       throw new IOException("term out of order");
/*  94 */     if (ti.freqPointer < this.lastTi.freqPointer)
/*  95 */       throw new IOException("freqPointer out of order");
/*  96 */     if (ti.proxPointer < this.lastTi.proxPointer) {
/*  97 */       throw new IOException("proxPointer out of order");
/*     */     }
/*  99 */     if ((!this.isIndex) && (this.size % this.indexInterval == 0L)) {
/* 100 */       this.other.add(this.lastTerm, this.lastTi);
/*     */     }
/* 102 */     writeTerm(term);
/* 103 */     this.output.writeVInt(ti.docFreq);
/* 104 */     this.output.writeVLong(ti.freqPointer - this.lastTi.freqPointer);
/* 105 */     this.output.writeVLong(ti.proxPointer - this.lastTi.proxPointer);
/*     */     
/* 107 */     if (ti.docFreq >= this.skipInterval) {
/* 108 */       this.output.writeVInt(ti.skipOffset);
/*     */     }
/*     */     
/* 111 */     if (this.isIndex) {
/* 112 */       this.output.writeVLong(this.other.output.getFilePointer() - this.lastIndexPointer);
/* 113 */       this.lastIndexPointer = this.other.output.getFilePointer();
/*     */     }
/*     */     
/* 116 */     this.lastTi.set(ti);
/* 117 */     this.size += 1L;
/*     */   }
/*     */   
/*     */   private final void writeTerm(Term term) throws IOException
/*     */   {
/* 122 */     int start = StringHelper.stringDifference(this.lastTerm.text, term.text);
/* 123 */     int length = term.text.length() - start;
/*     */     
/* 125 */     this.output.writeVInt(start);
/* 126 */     this.output.writeVInt(length);
/* 127 */     this.output.writeChars(term.text, start, length);
/*     */     
/* 129 */     this.output.writeVInt(this.fieldInfos.fieldNumber(term.field));
/*     */     
/* 131 */     this.lastTerm = term;
/*     */   }
/*     */   
/*     */ 
/*     */   final void close()
/*     */     throws IOException
/*     */   {
/* 138 */     this.output.seek(4L);
/* 139 */     this.output.writeLong(this.size);
/* 140 */     this.output.close();
/*     */     
/* 142 */     if (!this.isIndex) {
/* 143 */       this.other.close();
/*     */     }
/*     */   }
/*     */ }


/* Location:              /home/menick/Scrivania/ProcessMining/CNMining/CNMining.jar!/org/apache/lucene/index/TermInfosWriter.class
 * Java compiler version: 2 (46.0)
 * JD-Core Version:       0.7.1
 */