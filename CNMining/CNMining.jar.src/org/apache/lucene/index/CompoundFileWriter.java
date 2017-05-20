/*     */ package org.apache.lucene.index;
/*     */ 
/*     */ import java.io.IOException;
/*     */ import java.util.HashSet;
/*     */ import java.util.Iterator;
/*     */ import java.util.LinkedList;
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
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ final class CompoundFileWriter
/*     */ {
/*     */   private Directory directory;
/*     */   private String fileName;
/*     */   private HashSet ids;
/*     */   private LinkedList entries;
/*     */   
/*     */   private static final class FileEntry
/*     */   {
/*     */     String file;
/*     */     long directoryOffset;
/*     */     long dataOffset;
/*     */     
/*     */     private FileEntry() {}
/*     */     
/*     */     FileEntry(CompoundFileWriter.1 x0)
/*     */     {
/*  53 */       this();
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
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*  69 */   private boolean merged = false;
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   public CompoundFileWriter(Directory dir, String name)
/*     */   {
/*  76 */     if (dir == null)
/*  77 */       throw new IllegalArgumentException("Missing directory");
/*  78 */     if (name == null) {
/*  79 */       throw new IllegalArgumentException("Missing name");
/*     */     }
/*  81 */     this.directory = dir;
/*  82 */     this.fileName = name;
/*  83 */     this.ids = new HashSet();
/*  84 */     this.entries = new LinkedList();
/*     */   }
/*     */   
/*     */   public Directory getDirectory()
/*     */   {
/*  89 */     return this.directory;
/*     */   }
/*     */   
/*     */   public String getName()
/*     */   {
/*  94 */     return this.fileName;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public void addFile(String file)
/*     */   {
/* 104 */     if (this.merged) {
/* 105 */       throw new IllegalStateException("Can't add extensions after merge has been called");
/*     */     }
/*     */     
/* 108 */     if (file == null) {
/* 109 */       throw new IllegalArgumentException("Missing source file");
/*     */     }
/*     */     
/* 112 */     if (!this.ids.add(file)) {
/* 113 */       throw new IllegalArgumentException("File " + file + " already added");
/*     */     }
/*     */     
/* 116 */     FileEntry entry = new FileEntry(null);
/* 117 */     entry.file = file;
/* 118 */     this.entries.add(entry);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   public void close()
/*     */     throws IOException
/*     */   {
/* 127 */     if (this.merged) {
/* 128 */       throw new IllegalStateException("Merge already performed");
/*     */     }
/*     */     
/* 131 */     if (this.entries.isEmpty()) {
/* 132 */       throw new IllegalStateException("No entries to merge have been defined");
/*     */     }
/*     */     
/* 135 */     this.merged = true;
/*     */     
/*     */ 
/* 138 */     OutputStream os = null;
/*     */     try {
/* 140 */       os = this.directory.createFile(this.fileName);
/*     */       
/*     */ 
/* 143 */       os.writeVInt(this.entries.size());
/*     */       
/*     */ 
/*     */ 
/*     */ 
/* 148 */       Iterator it = this.entries.iterator();
/* 149 */       while (it.hasNext()) {
/* 150 */         FileEntry fe = (FileEntry)it.next();
/* 151 */         fe.directoryOffset = os.getFilePointer();
/* 152 */         os.writeLong(0L);
/* 153 */         os.writeString(fe.file);
/*     */       }
/*     */       
/*     */ 
/*     */ 
/* 158 */       byte[] buffer = new byte['Ѐ'];
/* 159 */       it = this.entries.iterator();
/* 160 */       while (it.hasNext()) {
/* 161 */         FileEntry fe = (FileEntry)it.next();
/* 162 */         fe.dataOffset = os.getFilePointer();
/* 163 */         copyFile(fe, os, buffer);
/*     */       }
/*     */       
/*     */ 
/* 167 */       it = this.entries.iterator();
/* 168 */       while (it.hasNext()) {
/* 169 */         FileEntry fe = (FileEntry)it.next();
/* 170 */         os.seek(fe.directoryOffset);
/* 171 */         os.writeLong(fe.dataOffset);
/*     */       }
/*     */       
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/* 178 */       OutputStream tmp = os;
/* 179 */       os = null;
/* 180 */       tmp.close();
/*     */     }
/*     */     finally {
/* 183 */       if (os != null) { try { os.close();
/*     */         }
/*     */         catch (IOException e) {}
/*     */       }
/*     */     }
/*     */   }
/*     */   
/*     */ 
/*     */   private void copyFile(FileEntry source, OutputStream os, byte[] buffer)
/*     */     throws IOException
/*     */   {
/* 194 */     InputStream is = null;
/*     */     try {
/* 196 */       long startPtr = os.getFilePointer();
/*     */       
/* 198 */       is = this.directory.openFile(source.file);
/* 199 */       long length = is.length();
/* 200 */       long remainder = length;
/* 201 */       int chunk = buffer.length;
/*     */       
/* 203 */       while (remainder > 0L) {
/* 204 */         int len = (int)Math.min(chunk, remainder);
/* 205 */         is.readBytes(buffer, 0, len);
/* 206 */         os.writeBytes(buffer, len);
/* 207 */         remainder -= len;
/*     */       }
/*     */       
/*     */ 
/* 211 */       if (remainder != 0L) {
/* 212 */         throw new IOException("Non-zero remainder length after copying: " + remainder + " (id: " + source.file + ", length: " + length + ", buffer size: " + chunk + ")");
/*     */       }
/*     */       
/*     */ 
/*     */ 
/*     */ 
/* 218 */       long endPtr = os.getFilePointer();
/* 219 */       long diff = endPtr - startPtr;
/* 220 */       if (diff != length) {
/* 221 */         throw new IOException("Difference in the output file offsets " + diff + " does not match the original file length " + length);
/*     */       }
/*     */     }
/*     */     finally
/*     */     {
/* 226 */       if (is != null) is.close();
/*     */     }
/*     */   }
/*     */ }


/* Location:              /home/menick/Scrivania/ProcessMining/CNMining/CNMining.jar!/org/apache/lucene/index/CompoundFileWriter.class
 * Java compiler version: 2 (46.0)
 * JD-Core Version:       0.7.1
 */