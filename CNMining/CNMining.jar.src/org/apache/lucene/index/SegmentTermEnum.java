/*     */ package org.apache.lucene.index;
/*     */ 
/*     */ import java.io.IOException;
/*     */ import org.apache.lucene.store.InputStream;
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
/*     */ final class SegmentTermEnum
/*     */   extends TermEnum
/*     */   implements Cloneable
/*     */ {
/*     */   private InputStream input;
/*     */   FieldInfos fieldInfos;
/*     */   long size;
/*  26 */   long position = -1L;
/*     */   
/*  28 */   private Term term = new Term("", "");
/*  29 */   private TermInfo termInfo = new TermInfo();
/*     */   
/*     */   private int format;
/*  32 */   private boolean isIndex = false;
/*  33 */   long indexPointer = 0L;
/*     */   
/*     */   int indexInterval;
/*     */   int skipInterval;
/*     */   private int formatM1SkipInterval;
/*     */   Term prev;
/*  39 */   private char[] buffer = new char[0];
/*     */   
/*     */   SegmentTermEnum(InputStream i, FieldInfos fis, boolean isi) throws IOException
/*     */   {
/*  43 */     this.input = i;
/*  44 */     this.fieldInfos = fis;
/*  45 */     this.isIndex = isi;
/*     */     
/*  47 */     int firstInt = this.input.readInt();
/*  48 */     if (firstInt >= 0)
/*     */     {
/*  50 */       this.format = 0;
/*  51 */       this.size = firstInt;
/*     */       
/*     */ 
/*  54 */       this.indexInterval = 128;
/*  55 */       this.skipInterval = Integer.MAX_VALUE;
/*     */     }
/*     */     else
/*     */     {
/*  59 */       this.format = firstInt;
/*     */       
/*     */ 
/*  62 */       if (this.format < -2) {
/*  63 */         throw new IOException("Unknown format version:" + this.format);
/*     */       }
/*  65 */       this.size = this.input.readLong();
/*     */       
/*  67 */       if (this.format == -1) {
/*  68 */         if (!this.isIndex) {
/*  69 */           this.indexInterval = this.input.readInt();
/*  70 */           this.formatM1SkipInterval = this.input.readInt();
/*     */         }
/*     */         
/*     */ 
/*  74 */         this.skipInterval = Integer.MAX_VALUE;
/*     */       }
/*     */       else {
/*  77 */         this.indexInterval = this.input.readInt();
/*  78 */         this.skipInterval = this.input.readInt();
/*     */       }
/*     */     }
/*     */   }
/*     */   
/*     */   protected Object clone()
/*     */   {
/*  85 */     SegmentTermEnum clone = null;
/*     */     try {
/*  87 */       clone = (SegmentTermEnum)super.clone();
/*     */     }
/*     */     catch (CloneNotSupportedException e) {}
/*  90 */     clone.input = ((InputStream)this.input.clone());
/*  91 */     clone.termInfo = new TermInfo(this.termInfo);
/*  92 */     if (this.term != null) { clone.growBuffer(this.term.text.length());
/*     */     }
/*  94 */     return clone;
/*     */   }
/*     */   
/*     */   final void seek(long pointer, int p, Term t, TermInfo ti) throws IOException
/*     */   {
/*  99 */     this.input.seek(pointer);
/* 100 */     this.position = p;
/* 101 */     this.term = t;
/* 102 */     this.prev = null;
/* 103 */     this.termInfo.set(ti);
/* 104 */     growBuffer(this.term.text.length());
/*     */   }
/*     */   
/*     */   public final boolean next() throws IOException
/*     */   {
/* 109 */     if (this.position++ >= this.size - 1L) {
/* 110 */       this.term = null;
/* 111 */       return false;
/*     */     }
/*     */     
/* 114 */     this.prev = this.term;
/* 115 */     this.term = readTerm();
/*     */     
/* 117 */     this.termInfo.docFreq = this.input.readVInt();
/* 118 */     this.termInfo.freqPointer += this.input.readVLong();
/* 119 */     this.termInfo.proxPointer += this.input.readVLong();
/*     */     
/* 121 */     if (this.format == -1)
/*     */     {
/*     */ 
/* 124 */       if ((!this.isIndex) && 
/* 125 */         (this.termInfo.docFreq > this.formatM1SkipInterval)) {
/* 126 */         this.termInfo.skipOffset = this.input.readVInt();
/*     */       }
/*     */       
/*     */ 
/*     */     }
/* 131 */     else if (this.termInfo.docFreq >= this.skipInterval) {
/* 132 */       this.termInfo.skipOffset = this.input.readVInt();
/*     */     }
/*     */     
/* 135 */     if (this.isIndex) {
/* 136 */       this.indexPointer += this.input.readVLong();
/*     */     }
/* 138 */     return true;
/*     */   }
/*     */   
/*     */   private final Term readTerm() throws IOException {
/* 142 */     int start = this.input.readVInt();
/* 143 */     int length = this.input.readVInt();
/* 144 */     int totalLength = start + length;
/* 145 */     if (this.buffer.length < totalLength) {
/* 146 */       growBuffer(totalLength);
/*     */     }
/* 148 */     this.input.readChars(this.buffer, start, length);
/* 149 */     return new Term(this.fieldInfos.fieldName(this.input.readVInt()), new String(this.buffer, 0, totalLength), false);
/*     */   }
/*     */   
/*     */   private final void growBuffer(int length)
/*     */   {
/* 154 */     this.buffer = new char[length];
/* 155 */     for (int i = 0; i < this.term.text.length(); i++) {
/* 156 */       this.buffer[i] = this.term.text.charAt(i);
/*     */     }
/*     */   }
/*     */   
/*     */   public final Term term()
/*     */   {
/* 162 */     return this.term;
/*     */   }
/*     */   
/*     */ 
/*     */   final TermInfo termInfo()
/*     */   {
/* 168 */     return new TermInfo(this.termInfo);
/*     */   }
/*     */   
/*     */ 
/*     */   final void termInfo(TermInfo ti)
/*     */   {
/* 174 */     ti.set(this.termInfo);
/*     */   }
/*     */   
/*     */ 
/*     */   public final int docFreq()
/*     */   {
/* 180 */     return this.termInfo.docFreq;
/*     */   }
/*     */   
/*     */ 
/*     */   final long freqPointer()
/*     */   {
/* 186 */     return this.termInfo.freqPointer;
/*     */   }
/*     */   
/*     */ 
/*     */   final long proxPointer()
/*     */   {
/* 192 */     return this.termInfo.proxPointer;
/*     */   }
/*     */   
/*     */   public final void close() throws IOException
/*     */   {
/* 197 */     this.input.close();
/*     */   }
/*     */ }


/* Location:              /home/menick/Scrivania/ProcessMining/CNMining/CNMining.jar!/org/apache/lucene/index/SegmentTermEnum.class
 * Java compiler version: 2 (46.0)
 * JD-Core Version:       0.7.1
 */