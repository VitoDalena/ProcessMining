/*     */ package org.apache.commons.compress.archivers.ar;
/*     */ 
/*     */ import java.io.File;
/*     */ import java.io.IOException;
/*     */ import java.io.OutputStream;
/*     */ import org.apache.commons.compress.archivers.ArchiveEntry;
/*     */ import org.apache.commons.compress.archivers.ArchiveOutputStream;
/*     */ import org.apache.commons.compress.utils.ArchiveUtils;
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
/*     */ public class ArArchiveOutputStream
/*     */   extends ArchiveOutputStream
/*     */ {
/*     */   private final OutputStream out;
/*  37 */   private long archiveOffset = 0L;
/*  38 */   private long entryOffset = 0L;
/*     */   private ArArchiveEntry prevEntry;
/*  40 */   private boolean haveUnclosedEntry = false;
/*     */   
/*     */ 
/*  43 */   private boolean finished = false;
/*     */   
/*     */   public ArArchiveOutputStream(OutputStream pOut) {
/*  46 */     this.out = pOut;
/*     */   }
/*     */   
/*     */   private long writeArchiveHeader() throws IOException {
/*  50 */     byte[] header = ArchiveUtils.toAsciiBytes("!<arch>\n");
/*  51 */     this.out.write(header);
/*  52 */     return header.length;
/*     */   }
/*     */   
/*     */   public void closeArchiveEntry() throws IOException {
/*  56 */     if (this.finished) {
/*  57 */       throw new IOException("Stream has already been finished");
/*     */     }
/*  59 */     if ((this.prevEntry == null) || (!this.haveUnclosedEntry)) {
/*  60 */       throw new IOException("No current entry to close");
/*     */     }
/*  62 */     if (this.entryOffset % 2L != 0L) {
/*  63 */       this.out.write(10);
/*  64 */       this.archiveOffset += 1L;
/*     */     }
/*  66 */     this.haveUnclosedEntry = false;
/*     */   }
/*     */   
/*     */   public void putArchiveEntry(ArchiveEntry pEntry) throws IOException {
/*  70 */     if (this.finished) {
/*  71 */       throw new IOException("Stream has already been finished");
/*     */     }
/*     */     
/*  74 */     ArArchiveEntry pArEntry = (ArArchiveEntry)pEntry;
/*  75 */     if (this.prevEntry == null) {
/*  76 */       this.archiveOffset += writeArchiveHeader();
/*     */     } else {
/*  78 */       if (this.prevEntry.getLength() != this.entryOffset) {
/*  79 */         throw new IOException("length does not match entry (" + this.prevEntry.getLength() + " != " + this.entryOffset);
/*     */       }
/*     */       
/*  82 */       if (this.haveUnclosedEntry) {
/*  83 */         closeArchiveEntry();
/*     */       }
/*     */     }
/*     */     
/*  87 */     this.prevEntry = pArEntry;
/*     */     
/*  89 */     this.archiveOffset += writeEntryHeader(pArEntry);
/*     */     
/*  91 */     this.entryOffset = 0L;
/*  92 */     this.haveUnclosedEntry = true;
/*     */   }
/*     */   
/*     */   private long fill(long pOffset, long pNewOffset, char pFill) throws IOException {
/*  96 */     long diff = pNewOffset - pOffset;
/*     */     
/*  98 */     if (diff > 0L) {
/*  99 */       for (int i = 0; i < diff; i++) {
/* 100 */         write(pFill);
/*     */       }
/*     */     }
/*     */     
/* 104 */     return pNewOffset;
/*     */   }
/*     */   
/*     */   private long write(String data) throws IOException {
/* 108 */     byte[] bytes = data.getBytes("ascii");
/* 109 */     write(bytes);
/* 110 */     return bytes.length;
/*     */   }
/*     */   
/*     */   private long writeEntryHeader(ArArchiveEntry pEntry) throws IOException
/*     */   {
/* 115 */     long offset = 0L;
/*     */     
/* 117 */     String n = pEntry.getName();
/* 118 */     if (n.length() > 16) {
/* 119 */       throw new IOException("filename too long, > 16 chars: " + n);
/*     */     }
/* 121 */     offset += write(n);
/*     */     
/* 123 */     offset = fill(offset, 16L, ' ');
/* 124 */     String m = "" + pEntry.getLastModified() / 1000L;
/* 125 */     if (m.length() > 12) {
/* 126 */       throw new IOException("modified too long");
/*     */     }
/* 128 */     offset += write(m);
/*     */     
/* 130 */     offset = fill(offset, 28L, ' ');
/* 131 */     String u = "" + pEntry.getUserId();
/* 132 */     if (u.length() > 6) {
/* 133 */       throw new IOException("userid too long");
/*     */     }
/* 135 */     offset += write(u);
/*     */     
/* 137 */     offset = fill(offset, 34L, ' ');
/* 138 */     String g = "" + pEntry.getGroupId();
/* 139 */     if (g.length() > 6) {
/* 140 */       throw new IOException("groupid too long");
/*     */     }
/* 142 */     offset += write(g);
/*     */     
/* 144 */     offset = fill(offset, 40L, ' ');
/* 145 */     String fm = "" + Integer.toString(pEntry.getMode(), 8);
/* 146 */     if (fm.length() > 8) {
/* 147 */       throw new IOException("filemode too long");
/*     */     }
/* 149 */     offset += write(fm);
/*     */     
/* 151 */     offset = fill(offset, 48L, ' ');
/* 152 */     String s = "" + pEntry.getLength();
/* 153 */     if (s.length() > 10) {
/* 154 */       throw new IOException("size too long");
/*     */     }
/* 156 */     offset += write(s);
/*     */     
/* 158 */     offset = fill(offset, 58L, ' ');
/*     */     
/* 160 */     offset += write("`\n");
/*     */     
/* 162 */     return offset;
/*     */   }
/*     */   
/*     */   public void write(byte[] b, int off, int len) throws IOException {
/* 166 */     this.out.write(b, off, len);
/* 167 */     count(len);
/* 168 */     this.entryOffset += len;
/*     */   }
/*     */   
/*     */   public void close() throws IOException {
/* 172 */     if (!this.finished) {
/* 173 */       finish();
/*     */     }
/* 175 */     this.out.close();
/* 176 */     this.prevEntry = null;
/*     */   }
/*     */   
/*     */   public ArchiveEntry createArchiveEntry(File inputFile, String entryName) throws IOException
/*     */   {
/* 181 */     if (this.finished) {
/* 182 */       throw new IOException("Stream has already been finished");
/*     */     }
/* 184 */     return new ArArchiveEntry(inputFile, entryName);
/*     */   }
/*     */   
/*     */ 
/*     */   public void finish()
/*     */     throws IOException
/*     */   {
/* 191 */     if (this.haveUnclosedEntry)
/* 192 */       throw new IOException("This archive contains unclosed entries.");
/* 193 */     if (this.finished) {
/* 194 */       throw new IOException("This archive has already been finished");
/*     */     }
/* 196 */     this.finished = true;
/*     */   }
/*     */ }


/* Location:              /home/menick/Scrivania/ProcessMining/CNMining/CNMining.jar!/org/apache/commons/compress/archivers/ar/ArArchiveOutputStream.class
 * Java compiler version: 4 (48.0)
 * JD-Core Version:       0.7.1
 */