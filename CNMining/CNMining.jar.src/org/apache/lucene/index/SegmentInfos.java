/*     */ package org.apache.lucene.index;
/*     */ 
/*     */ import java.io.IOException;
/*     */ import java.util.Vector;
/*     */ import org.apache.lucene.store.Directory;
/*     */ import org.apache.lucene.store.InputStream;
/*     */ import org.apache.lucene.store.OutputStream;
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
/*     */ final class SegmentInfos
/*     */   extends Vector
/*     */ {
/*     */   public static final int FORMAT = -1;
/*  31 */   public int counter = 0;
/*  32 */   private long version = 0L;
/*     */   
/*     */   public final SegmentInfo info(int i) {
/*  35 */     return (SegmentInfo)elementAt(i);
/*     */   }
/*     */   
/*     */   public final void read(Directory directory) throws IOException
/*     */   {
/*  40 */     InputStream input = directory.openFile("segments");
/*     */     try {
/*  42 */       int format = input.readInt();
/*  43 */       if (format < 0)
/*     */       {
/*  45 */         if (format < -1)
/*  46 */           throw new IOException("Unknown format version: " + format);
/*  47 */         this.version = input.readLong();
/*  48 */         this.counter = input.readInt();
/*     */       }
/*     */       else {
/*  51 */         this.counter = format;
/*     */       }
/*     */       
/*  54 */       for (int i = input.readInt(); i > 0; i--) {
/*  55 */         SegmentInfo si = new SegmentInfo(input.readString(), input.readInt(), directory);
/*     */         
/*  57 */         addElement(si);
/*     */       }
/*     */       
/*  60 */       if (format >= 0) {
/*  61 */         if (input.getFilePointer() >= input.length()) {
/*  62 */           this.version = 0L;
/*     */         } else {
/*  64 */           this.version = input.readLong();
/*     */         }
/*     */       }
/*     */     } finally {
/*  68 */       input.close();
/*     */     }
/*     */   }
/*     */   
/*     */   public final void write(Directory directory) throws IOException {
/*  73 */     OutputStream output = directory.createFile("segments.new");
/*     */     try {
/*  75 */       output.writeInt(-1);
/*  76 */       output.writeLong(++this.version);
/*  77 */       output.writeInt(this.counter);
/*  78 */       output.writeInt(size());
/*  79 */       for (int i = 0; i < size(); i++) {
/*  80 */         SegmentInfo si = info(i);
/*  81 */         output.writeString(si.name);
/*  82 */         output.writeInt(si.docCount);
/*     */       }
/*     */     }
/*     */     finally {
/*  86 */       output.close();
/*     */     }
/*     */     
/*     */ 
/*  90 */     directory.renameFile("segments.new", "segments");
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */   public long getVersion()
/*     */   {
/*  97 */     return this.version;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   public static long readCurrentVersion(Directory directory)
/*     */     throws IOException
/*     */   {
/* 106 */     InputStream input = directory.openFile("segments");
/* 107 */     int format = 0;
/* 108 */     long version = 0L;
/*     */     try {
/* 110 */       format = input.readInt();
/* 111 */       if (format < 0) {
/* 112 */         if (format < -1)
/* 113 */           throw new IOException("Unknown format version: " + format);
/* 114 */         version = input.readLong();
/*     */       }
/*     */     }
/*     */     finally {
/* 118 */       input.close();
/*     */     }
/*     */     
/* 121 */     if (format < 0) {
/* 122 */       return version;
/*     */     }
/*     */     
/*     */ 
/*     */ 
/* 127 */     SegmentInfos sis = new SegmentInfos();
/* 128 */     sis.read(directory);
/* 129 */     return sis.getVersion();
/*     */   }
/*     */ }


/* Location:              /home/menick/Scrivania/ProcessMining/CNMining/CNMining.jar!/org/apache/lucene/index/SegmentInfos.class
 * Java compiler version: 2 (46.0)
 * JD-Core Version:       0.7.1
 */