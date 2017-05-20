/*     */ package org.apache.lucene.store;
/*     */ 
/*     */ import java.io.File;
/*     */ import java.io.IOException;
/*     */ import java.util.Enumeration;
/*     */ import java.util.Hashtable;
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
/*     */ public final class RAMDirectory
/*     */   extends Directory
/*     */ {
/*  34 */   Hashtable files = new Hashtable();
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public RAMDirectory() {}
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public RAMDirectory(Directory dir)
/*     */     throws IOException
/*     */   {
/*  51 */     this(dir, false);
/*     */   }
/*     */   
/*     */   private RAMDirectory(Directory dir, boolean closeDir) throws IOException {
/*  55 */     String[] files = dir.list();
/*  56 */     for (int i = 0; i < files.length; i++)
/*     */     {
/*  58 */       OutputStream os = createFile(files[i]);
/*     */       
/*  60 */       InputStream is = dir.openFile(files[i]);
/*     */       
/*  62 */       int len = (int)is.length();
/*  63 */       byte[] buf = new byte[len];
/*  64 */       is.readBytes(buf, 0, len);
/*  65 */       os.writeBytes(buf, len);
/*     */       
/*  67 */       is.close();
/*  68 */       os.close();
/*     */     }
/*  70 */     if (closeDir) {
/*  71 */       dir.close();
/*     */     }
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */   public RAMDirectory(File dir)
/*     */     throws IOException
/*     */   {
/*  80 */     this(FSDirectory.getDirectory(dir, false), true);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   public RAMDirectory(String dir)
/*     */     throws IOException
/*     */   {
/*  89 */     this(FSDirectory.getDirectory(dir, false), true);
/*     */   }
/*     */   
/*     */   public final String[] list()
/*     */   {
/*  94 */     String[] result = new String[this.files.size()];
/*  95 */     int i = 0;
/*  96 */     Enumeration names = this.files.keys();
/*  97 */     while (names.hasMoreElements())
/*  98 */       result[(i++)] = ((String)names.nextElement());
/*  99 */     return result;
/*     */   }
/*     */   
/*     */   public final boolean fileExists(String name)
/*     */   {
/* 104 */     RAMFile file = (RAMFile)this.files.get(name);
/* 105 */     return file != null;
/*     */   }
/*     */   
/*     */   public final long fileModified(String name) throws IOException
/*     */   {
/* 110 */     RAMFile file = (RAMFile)this.files.get(name);
/* 111 */     return file.lastModified;
/*     */   }
/*     */   
/*     */ 
/*     */   public void touchFile(String name)
/*     */     throws IOException
/*     */   {
/* 118 */     RAMFile file = (RAMFile)this.files.get(name);
/* 119 */     long ts1 = System.currentTimeMillis();
/*     */     long ts2;
/*     */     do {
/* 122 */       try { Thread.sleep(0L, 1);
/*     */       } catch (InterruptedException e) {}
/* 124 */       ts2 = System.currentTimeMillis();
/*     */ 
/*     */ 
/*     */     }
/* 128 */     while (ts1 == ts2);
/*     */     
/* 130 */     file.lastModified = ts2;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   public final long fileLength(String name)
/*     */   {
/* 138 */     RAMFile file = (RAMFile)this.files.get(name);
/* 139 */     return file.length;
/*     */   }
/*     */   
/*     */   public final void deleteFile(String name)
/*     */   {
/* 144 */     this.files.remove(name);
/*     */   }
/*     */   
/*     */   public final void renameFile(String from, String to)
/*     */   {
/* 149 */     RAMFile file = (RAMFile)this.files.get(from);
/* 150 */     this.files.remove(from);
/* 151 */     this.files.put(to, file);
/*     */   }
/*     */   
/*     */ 
/*     */   public final OutputStream createFile(String name)
/*     */   {
/* 157 */     RAMFile file = new RAMFile();
/* 158 */     this.files.put(name, file);
/* 159 */     return new RAMOutputStream(file);
/*     */   }
/*     */   
/*     */   public final InputStream openFile(String name)
/*     */   {
/* 164 */     RAMFile file = (RAMFile)this.files.get(name);
/* 165 */     return new RAMInputStream(file);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */   public final Lock makeLock(String name)
/*     */   {
/* 172 */     new Lock() { private final String val$name;
/*     */       
/* 174 */       public boolean obtain() throws IOException { synchronized (RAMDirectory.this.files) {
/* 175 */           if (!RAMDirectory.this.fileExists(this.val$name)) {
/* 176 */             RAMDirectory.this.createFile(this.val$name).close();
/* 177 */             return true;
/*     */           }
/* 179 */           return false;
/*     */         }
/*     */       }
/*     */       
/* 183 */       public void release() { RAMDirectory.this.deleteFile(this.val$name); }
/*     */       
/*     */       public boolean isLocked() {
/* 186 */         return RAMDirectory.this.fileExists(this.val$name);
/*     */       }
/*     */     };
/*     */   }
/*     */   
/*     */   public final void close() {}
/*     */ }


/* Location:              /home/menick/Scrivania/ProcessMining/CNMining/CNMining.jar!/org/apache/lucene/store/RAMDirectory.class
 * Java compiler version: 2 (46.0)
 * JD-Core Version:       0.7.1
 */