/*     */ package org.apache.lucene.index;
/*     */ 
/*     */ import java.io.IOException;
/*     */ import java.util.HashMap;
/*     */ import java.util.Set;
/*     */ import org.apache.lucene.store.Directory;
/*     */ import org.apache.lucene.store.InputStream;
/*     */ import org.apache.lucene.store.Lock;
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
/*     */ class CompoundFileReader
/*     */   extends Directory
/*     */ {
/*     */   private Directory directory;
/*     */   private String fileName;
/*     */   private boolean open;
/*     */   private InputStream stream;
/*     */   
/*     */   private static final class FileEntry
/*     */   {
/*     */     long offset;
/*     */     long length;
/*     */     
/*     */     private FileEntry() {}
/*     */     
/*     */     FileEntry(CompoundFileReader.1 x0)
/*     */     {
/*  37 */       this();
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
/*  51 */   private HashMap entries = new HashMap();
/*     */   
/*     */ 
/*     */   public CompoundFileReader(Directory dir, String name)
/*     */     throws IOException
/*     */   {
/*  57 */     this.directory = dir;
/*  58 */     this.fileName = name;
/*     */     
/*  60 */     boolean success = false;
/*     */     try
/*     */     {
/*  63 */       this.stream = dir.openFile(name);
/*     */       
/*     */ 
/*  66 */       int count = this.stream.readVInt();
/*  67 */       FileEntry entry = null;
/*  68 */       for (int i = 0; i < count; i++) {
/*  69 */         long offset = this.stream.readLong();
/*  70 */         String id = this.stream.readString();
/*     */         
/*  72 */         if (entry != null)
/*     */         {
/*  74 */           entry.length = (offset - entry.offset);
/*     */         }
/*     */         
/*  77 */         entry = new FileEntry(null);
/*  78 */         entry.offset = offset;
/*  79 */         this.entries.put(id, entry);
/*     */       }
/*     */       
/*     */ 
/*  83 */       if (entry != null) {
/*  84 */         entry.length = (this.stream.length() - entry.offset);
/*     */       }
/*     */       
/*  87 */       success = true;
/*     */     }
/*     */     finally {
/*  90 */       if ((!success) && (this.stream != null)) {
/*     */         try {
/*  92 */           this.stream.close();
/*     */         } catch (IOException e) {}
/*     */       }
/*     */     }
/*     */   }
/*     */   
/*     */   public Directory getDirectory() {
/*  99 */     return this.directory;
/*     */   }
/*     */   
/*     */   public String getName() {
/* 103 */     return this.fileName;
/*     */   }
/*     */   
/*     */   public synchronized void close() throws IOException {
/* 107 */     if (this.stream == null) {
/* 108 */       throw new IOException("Already closed");
/*     */     }
/* 110 */     this.entries.clear();
/* 111 */     this.stream.close();
/* 112 */     this.stream = null;
/*     */   }
/*     */   
/*     */   public synchronized InputStream openFile(String id)
/*     */     throws IOException
/*     */   {
/* 118 */     if (this.stream == null) {
/* 119 */       throw new IOException("Stream closed");
/*     */     }
/* 121 */     FileEntry entry = (FileEntry)this.entries.get(id);
/* 122 */     if (entry == null) {
/* 123 */       throw new IOException("No sub-file with id " + id + " found");
/*     */     }
/* 125 */     return new CSInputStream(this.stream, entry.offset, entry.length);
/*     */   }
/*     */   
/*     */   public String[] list()
/*     */   {
/* 130 */     String[] res = new String[this.entries.size()];
/* 131 */     return (String[])this.entries.keySet().toArray(res);
/*     */   }
/*     */   
/*     */   public boolean fileExists(String name)
/*     */   {
/* 136 */     return this.entries.containsKey(name);
/*     */   }
/*     */   
/*     */   public long fileModified(String name) throws IOException
/*     */   {
/* 141 */     return this.directory.fileModified(this.fileName);
/*     */   }
/*     */   
/*     */   public void touchFile(String name) throws IOException
/*     */   {
/* 146 */     this.directory.touchFile(this.fileName);
/*     */   }
/*     */   
/*     */ 
/*     */   public void deleteFile(String name)
/*     */   {
/* 152 */     throw new UnsupportedOperationException();
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   public void renameFile(String from, String to)
/*     */   {
/* 160 */     throw new UnsupportedOperationException();
/*     */   }
/*     */   
/*     */ 
/*     */   public long fileLength(String name)
/*     */     throws IOException
/*     */   {
/* 167 */     FileEntry e = (FileEntry)this.entries.get(name);
/* 168 */     if (e == null)
/* 169 */       throw new IOException("File " + name + " does not exist");
/* 170 */     return e.length;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */   public OutputStream createFile(String name)
/*     */   {
/* 177 */     throw new UnsupportedOperationException();
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   public Lock makeLock(String name)
/*     */   {
/* 185 */     throw new UnsupportedOperationException();
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */   static final class CSInputStream
/*     */     extends InputStream
/*     */   {
/*     */     InputStream base;
/*     */     
/*     */     long fileOffset;
/*     */     
/*     */ 
/*     */     CSInputStream(InputStream base, long fileOffset, long length)
/*     */       throws IOException
/*     */     {
/* 201 */       this.base = base;
/* 202 */       this.fileOffset = fileOffset;
/* 203 */       this.length = length;
/*     */     }
/*     */     
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     protected void readInternal(byte[] b, int offset, int len)
/*     */       throws IOException
/*     */     {
/* 215 */       synchronized (this.base) {
/* 216 */         long start = getFilePointer();
/* 217 */         if (start + len > this.length)
/* 218 */           throw new IOException("read past EOF");
/* 219 */         this.base.seek(this.fileOffset + start);
/* 220 */         this.base.readBytes(b, offset, len);
/*     */       }
/*     */     }
/*     */     
/*     */     protected void seekInternal(long pos)
/*     */       throws IOException
/*     */     {}
/*     */     
/*     */     public void close()
/*     */       throws IOException
/*     */     {}
/*     */   }
/*     */ }


/* Location:              /home/menick/Scrivania/ProcessMining/CNMining/CNMining.jar!/org/apache/lucene/index/CompoundFileReader.class
 * Java compiler version: 2 (46.0)
 * JD-Core Version:       0.7.1
 */