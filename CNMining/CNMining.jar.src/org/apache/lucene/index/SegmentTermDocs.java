/*     */ package org.apache.lucene.index;
/*     */ 
/*     */ import java.io.IOException;
/*     */ import org.apache.lucene.store.InputStream;
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
/*     */ class SegmentTermDocs
/*     */   implements TermDocs
/*     */ {
/*     */   protected SegmentReader parent;
/*     */   private InputStream freqStream;
/*     */   private int count;
/*     */   private int df;
/*     */   private BitVector deletedDocs;
/*  29 */   int doc = 0;
/*     */   int freq;
/*     */   private int skipInterval;
/*     */   private int numSkips;
/*     */   private int skipCount;
/*     */   private InputStream skipStream;
/*     */   private int skipDoc;
/*     */   private long freqPointer;
/*     */   private long proxPointer;
/*     */   private long skipPointer;
/*     */   private boolean haveSkipped;
/*     */   
/*     */   SegmentTermDocs(SegmentReader parent)
/*     */     throws IOException
/*     */   {
/*  44 */     this.parent = parent;
/*  45 */     this.freqStream = ((InputStream)parent.freqStream.clone());
/*  46 */     this.deletedDocs = parent.deletedDocs;
/*  47 */     this.skipInterval = parent.tis.getSkipInterval();
/*     */   }
/*     */   
/*     */   public void seek(Term term) throws IOException {
/*  51 */     TermInfo ti = this.parent.tis.get(term);
/*  52 */     seek(ti);
/*     */   }
/*     */   
/*     */   public void seek(TermEnum termEnum) throws IOException
/*     */   {
/*     */     TermInfo ti;
/*     */     TermInfo ti;
/*  59 */     if (((termEnum instanceof SegmentTermEnum)) && (((SegmentTermEnum)termEnum).fieldInfos == this.parent.fieldInfos)) {
/*  60 */       ti = ((SegmentTermEnum)termEnum).termInfo();
/*     */     } else {
/*  62 */       ti = this.parent.tis.get(termEnum.term());
/*     */     }
/*  64 */     seek(ti);
/*     */   }
/*     */   
/*     */   void seek(TermInfo ti) throws IOException {
/*  68 */     this.count = 0;
/*  69 */     if (ti == null) {
/*  70 */       this.df = 0;
/*     */     } else {
/*  72 */       this.df = ti.docFreq;
/*  73 */       this.doc = 0;
/*  74 */       this.skipDoc = 0;
/*  75 */       this.skipCount = 0;
/*  76 */       this.numSkips = (this.df / this.skipInterval);
/*  77 */       this.freqPointer = ti.freqPointer;
/*  78 */       this.proxPointer = ti.proxPointer;
/*  79 */       this.skipPointer = (this.freqPointer + ti.skipOffset);
/*  80 */       this.freqStream.seek(this.freqPointer);
/*  81 */       this.haveSkipped = false;
/*     */     }
/*     */   }
/*     */   
/*     */   public void close() throws IOException {
/*  86 */     this.freqStream.close();
/*  87 */     if (this.skipStream != null)
/*  88 */       this.skipStream.close();
/*     */   }
/*     */   
/*  91 */   public final int doc() { return this.doc; }
/*  92 */   public final int freq() { return this.freq; }
/*     */   
/*     */   protected void skippingDoc() throws IOException
/*     */   {}
/*     */   
/*     */   public boolean next() throws IOException {
/*     */     for (;;) {
/*  99 */       if (this.count == this.df) {
/* 100 */         return false;
/*     */       }
/* 102 */       int docCode = this.freqStream.readVInt();
/* 103 */       this.doc += (docCode >>> 1);
/* 104 */       if ((docCode & 0x1) != 0) {
/* 105 */         this.freq = 1;
/*     */       } else {
/* 107 */         this.freq = this.freqStream.readVInt();
/*     */       }
/* 109 */       this.count += 1;
/*     */       
/* 111 */       if ((this.deletedDocs == null) || (!this.deletedDocs.get(this.doc)))
/*     */         break;
/* 113 */       skippingDoc();
/*     */     }
/* 115 */     return true;
/*     */   }
/*     */   
/*     */   public int read(int[] docs, int[] freqs)
/*     */     throws IOException
/*     */   {
/* 121 */     int length = docs.length;
/* 122 */     int i = 0;
/* 123 */     while ((i < length) && (this.count < this.df))
/*     */     {
/*     */ 
/* 126 */       int docCode = this.freqStream.readVInt();
/* 127 */       this.doc += (docCode >>> 1);
/* 128 */       if ((docCode & 0x1) != 0) {
/* 129 */         this.freq = 1;
/*     */       } else
/* 131 */         this.freq = this.freqStream.readVInt();
/* 132 */       this.count += 1;
/*     */       
/* 134 */       if ((this.deletedDocs == null) || (!this.deletedDocs.get(this.doc))) {
/* 135 */         docs[i] = this.doc;
/* 136 */         freqs[i] = this.freq;
/* 137 */         i++;
/*     */       }
/*     */     }
/* 140 */     return i;
/*     */   }
/*     */   
/*     */   protected void skipProx(long proxPointer) throws IOException
/*     */   {}
/*     */   
/*     */   public boolean skipTo(int target) throws IOException
/*     */   {
/* 148 */     if (this.df >= this.skipInterval)
/*     */     {
/* 150 */       if (this.skipStream == null) {
/* 151 */         this.skipStream = ((InputStream)this.freqStream.clone());
/*     */       }
/* 153 */       if (!this.haveSkipped) {
/* 154 */         this.skipStream.seek(this.skipPointer);
/* 155 */         this.haveSkipped = true;
/*     */       }
/*     */       
/*     */ 
/* 159 */       int lastSkipDoc = this.skipDoc;
/* 160 */       long lastFreqPointer = this.freqStream.getFilePointer();
/* 161 */       long lastProxPointer = -1L;
/* 162 */       int numSkipped = -1 - this.count % this.skipInterval;
/*     */       
/* 164 */       while (target > this.skipDoc) {
/* 165 */         lastSkipDoc = this.skipDoc;
/* 166 */         lastFreqPointer = this.freqPointer;
/* 167 */         lastProxPointer = this.proxPointer;
/*     */         
/* 169 */         if ((this.skipDoc != 0) && (this.skipDoc >= this.doc)) {
/* 170 */           numSkipped += this.skipInterval;
/*     */         }
/* 172 */         if (this.skipCount >= this.numSkips) {
/*     */           break;
/*     */         }
/* 175 */         this.skipDoc += this.skipStream.readVInt();
/* 176 */         this.freqPointer += this.skipStream.readVInt();
/* 177 */         this.proxPointer += this.skipStream.readVInt();
/*     */         
/* 179 */         this.skipCount += 1;
/*     */       }
/*     */       
/*     */ 
/* 183 */       if (lastFreqPointer > this.freqStream.getFilePointer()) {
/* 184 */         this.freqStream.seek(lastFreqPointer);
/* 185 */         skipProx(lastProxPointer);
/*     */         
/* 187 */         this.doc = lastSkipDoc;
/* 188 */         this.count += numSkipped;
/*     */       }
/*     */     }
/*     */     
/*     */ 
/*     */     do
/*     */     {
/* 195 */       if (!next())
/* 196 */         return false;
/* 197 */     } while (target > this.doc);
/* 198 */     return true;
/*     */   }
/*     */ }


/* Location:              /home/menick/Scrivania/ProcessMining/CNMining/CNMining.jar!/org/apache/lucene/index/SegmentTermDocs.class
 * Java compiler version: 2 (46.0)
 * JD-Core Version:       0.7.1
 */