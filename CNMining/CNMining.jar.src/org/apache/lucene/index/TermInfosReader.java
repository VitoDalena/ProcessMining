/*     */ package org.apache.lucene.index;
/*     */ 
/*     */ import java.io.IOException;
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
/*     */ 
/*     */ 
/*     */ final class TermInfosReader
/*     */ {
/*     */   private Directory directory;
/*     */   private String segment;
/*     */   private FieldInfos fieldInfos;
/*  32 */   private ThreadLocal enumerators = new ThreadLocal();
/*     */   private SegmentTermEnum origEnum;
/*     */   private long size;
/*     */   
/*     */   TermInfosReader(Directory dir, String seg, FieldInfos fis) throws IOException
/*     */   {
/*  38 */     this.directory = dir;
/*  39 */     this.segment = seg;
/*  40 */     this.fieldInfos = fis;
/*     */     
/*  42 */     this.origEnum = new SegmentTermEnum(this.directory.openFile(this.segment + ".tis"), this.fieldInfos, false);
/*     */     
/*  44 */     this.size = this.origEnum.size;
/*  45 */     readIndex();
/*     */   }
/*     */   
/*     */   public int getSkipInterval() {
/*  49 */     return this.origEnum.skipInterval;
/*     */   }
/*     */   
/*     */   final void close() throws IOException {
/*  53 */     if (this.origEnum != null) {
/*  54 */       this.origEnum.close();
/*     */     }
/*     */   }
/*     */   
/*     */   final long size() {
/*  59 */     return this.size;
/*     */   }
/*     */   
/*     */   private SegmentTermEnum getEnum() {
/*  63 */     SegmentTermEnum termEnum = (SegmentTermEnum)this.enumerators.get();
/*  64 */     if (termEnum == null) {
/*  65 */       termEnum = terms();
/*  66 */       this.enumerators.set(termEnum);
/*     */     }
/*  68 */     return termEnum;
/*     */   }
/*     */   
/*  71 */   Term[] indexTerms = null;
/*     */   TermInfo[] indexInfos;
/*     */   long[] indexPointers;
/*     */   
/*     */   private final void readIndex() throws IOException {
/*  76 */     SegmentTermEnum indexEnum = new SegmentTermEnum(this.directory.openFile(this.segment + ".tii"), this.fieldInfos, true);
/*     */     
/*     */     try
/*     */     {
/*  80 */       int indexSize = (int)indexEnum.size;
/*     */       
/*  82 */       this.indexTerms = new Term[indexSize];
/*  83 */       this.indexInfos = new TermInfo[indexSize];
/*  84 */       this.indexPointers = new long[indexSize];
/*     */       
/*  86 */       for (int i = 0; indexEnum.next(); i++) {
/*  87 */         this.indexTerms[i] = indexEnum.term();
/*  88 */         this.indexInfos[i] = indexEnum.termInfo();
/*  89 */         this.indexPointers[i] = indexEnum.indexPointer;
/*     */       }
/*     */     } finally {
/*  92 */       indexEnum.close();
/*     */     }
/*     */   }
/*     */   
/*     */   private final int getIndexOffset(Term term) throws IOException
/*     */   {
/*  98 */     int lo = 0;
/*  99 */     int hi = this.indexTerms.length - 1;
/*     */     
/* 101 */     while (hi >= lo) {
/* 102 */       int mid = lo + hi >> 1;
/* 103 */       int delta = term.compareTo(this.indexTerms[mid]);
/* 104 */       if (delta < 0) {
/* 105 */         hi = mid - 1;
/* 106 */       } else if (delta > 0) {
/* 107 */         lo = mid + 1;
/*     */       } else
/* 109 */         return mid;
/*     */     }
/* 111 */     return hi;
/*     */   }
/*     */   
/*     */   private final void seekEnum(int indexOffset) throws IOException {
/* 115 */     getEnum().seek(this.indexPointers[indexOffset], indexOffset * getEnum().indexInterval - 1, this.indexTerms[indexOffset], this.indexInfos[indexOffset]);
/*     */   }
/*     */   
/*     */ 
/*     */   TermInfo get(Term term)
/*     */     throws IOException
/*     */   {
/* 122 */     if (this.size == 0L) { return null;
/*     */     }
/*     */     
/* 125 */     SegmentTermEnum enumerator = getEnum();
/* 126 */     if ((enumerator.term() != null) && (((enumerator.prev != null) && (term.compareTo(enumerator.prev) > 0)) || (term.compareTo(enumerator.term()) >= 0)))
/*     */     {
/*     */ 
/* 129 */       int enumOffset = (int)(enumerator.position / enumerator.indexInterval) + 1;
/* 130 */       if ((this.indexTerms.length == enumOffset) || (term.compareTo(this.indexTerms[enumOffset]) < 0))
/*     */       {
/* 132 */         return scanEnum(term);
/*     */       }
/*     */     }
/*     */     
/* 136 */     seekEnum(getIndexOffset(term));
/* 137 */     return scanEnum(term);
/*     */   }
/*     */   
/*     */   private final TermInfo scanEnum(Term term) throws IOException
/*     */   {
/* 142 */     SegmentTermEnum enumerator = getEnum();
/* 143 */     while ((term.compareTo(enumerator.term()) > 0) && (enumerator.next())) {}
/* 144 */     if ((enumerator.term() != null) && (term.compareTo(enumerator.term()) == 0)) {
/* 145 */       return enumerator.termInfo();
/*     */     }
/* 147 */     return null;
/*     */   }
/*     */   
/*     */   final Term get(int position) throws IOException
/*     */   {
/* 152 */     if (this.size == 0L) { return null;
/*     */     }
/* 154 */     SegmentTermEnum enumerator = getEnum();
/* 155 */     if ((enumerator != null) && (enumerator.term() != null) && (position >= enumerator.position) && (position < enumerator.position + enumerator.indexInterval))
/*     */     {
/*     */ 
/* 158 */       return scanEnum(position);
/*     */     }
/* 160 */     seekEnum(position / enumerator.indexInterval);
/* 161 */     return scanEnum(position);
/*     */   }
/*     */   
/*     */   private final Term scanEnum(int position) throws IOException {
/* 165 */     SegmentTermEnum enumerator = getEnum();
/* 166 */     while (enumerator.position < position) {
/* 167 */       if (!enumerator.next())
/* 168 */         return null;
/*     */     }
/* 170 */     return enumerator.term();
/*     */   }
/*     */   
/*     */   final long getPosition(Term term) throws IOException
/*     */   {
/* 175 */     if (this.size == 0L) { return -1L;
/*     */     }
/* 177 */     int indexOffset = getIndexOffset(term);
/* 178 */     seekEnum(indexOffset);
/*     */     
/* 180 */     SegmentTermEnum enumerator = getEnum();
/* 181 */     while ((term.compareTo(enumerator.term()) > 0) && (enumerator.next())) {}
/*     */     
/* 183 */     if (term.compareTo(enumerator.term()) == 0) {
/* 184 */       return enumerator.position;
/*     */     }
/* 186 */     return -1L;
/*     */   }
/*     */   
/*     */   public SegmentTermEnum terms()
/*     */   {
/* 191 */     return (SegmentTermEnum)this.origEnum.clone();
/*     */   }
/*     */   
/*     */   public SegmentTermEnum terms(Term term) throws IOException
/*     */   {
/* 196 */     get(term);
/* 197 */     return (SegmentTermEnum)getEnum().clone();
/*     */   }
/*     */ }


/* Location:              /home/menick/Scrivania/ProcessMining/CNMining/CNMining.jar!/org/apache/lucene/index/TermInfosReader.class
 * Java compiler version: 2 (46.0)
 * JD-Core Version:       0.7.1
 */