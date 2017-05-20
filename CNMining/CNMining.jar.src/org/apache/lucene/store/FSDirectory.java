/*     */ package org.apache.lucene.store;
/*     */ 
/*     */ import java.io.File;
/*     */ import java.io.FileInputStream;
/*     */ import java.io.FileOutputStream;
/*     */ import java.io.IOException;
/*     */ import java.security.MessageDigest;
/*     */ import java.security.NoSuchAlgorithmException;
/*     */ import java.util.Hashtable;
/*     */ import org.apache.lucene.util.Constants;
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
/*     */ 
/*     */ public final class FSDirectory
/*     */   extends Directory
/*     */ {
/*  46 */   private static final Hashtable DIRECTORIES = new Hashtable();
/*     */   
/*  48 */   private static final boolean DISABLE_LOCKS = (Boolean.getBoolean("disableLuceneLocks")) || (Constants.JAVA_1_1);
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*  55 */   public static final String LOCK_DIR = System.getProperty("org.apache.lucene.lockdir", System.getProperty("java.io.tmpdir"));
/*     */   
/*     */   private static MessageDigest DIGESTER;
/*     */   
/*     */   static
/*     */   {
/*     */     try
/*     */     {
/*  63 */       DIGESTER = MessageDigest.getInstance("MD5");
/*     */     } catch (NoSuchAlgorithmException e) {
/*  65 */       throw new RuntimeException(e.toString());
/*     */     }
/*     */   }
/*     */   
/*     */ 
/*  70 */   private byte[] buffer = null;
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public static FSDirectory getDirectory(String path, boolean create)
/*     */     throws IOException
/*     */   {
/*  83 */     return getDirectory(new File(path), create);
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
/*     */   public static FSDirectory getDirectory(File file, boolean create)
/*     */     throws IOException
/*     */   {
/*  97 */     file = new File(file.getCanonicalPath());
/*     */     
/*  99 */     synchronized (DIRECTORIES) {
/* 100 */       FSDirectory dir = (FSDirectory)DIRECTORIES.get(file);
/* 101 */       if (dir == null) {
/* 102 */         dir = new FSDirectory(file, create);
/* 103 */         DIRECTORIES.put(file, dir);
/* 104 */       } else if (create) {
/* 105 */         dir.create();
/*     */       } }
/*     */     FSDirectory dir;
/* 108 */     synchronized (dir) {
/* 109 */       dir.refCount += 1;
/*     */     }
/* 111 */     return dir;
/*     */   }
/*     */   
/* 114 */   private File directory = null;
/*     */   private int refCount;
/*     */   private File lockDir;
/*     */   
/*     */   private FSDirectory(File path, boolean create) throws IOException {
/* 119 */     this.directory = path;
/*     */     
/* 121 */     if (LOCK_DIR == null) {
/* 122 */       this.lockDir = this.directory;
/*     */     }
/*     */     else {
/* 125 */       this.lockDir = new File(LOCK_DIR);
/*     */     }
/* 127 */     if (create) {
/* 128 */       create();
/*     */     }
/*     */     
/* 131 */     if (!this.directory.isDirectory())
/* 132 */       throw new IOException(path + " not a directory");
/*     */   }
/*     */   
/*     */   private synchronized void create() throws IOException {
/* 136 */     if ((!this.directory.exists()) && 
/* 137 */       (!this.directory.mkdirs())) {
/* 138 */       throw new IOException("Cannot create directory: " + this.directory);
/*     */     }
/* 140 */     String[] files = this.directory.list();
/* 141 */     for (int i = 0; i < files.length; i++) {
/* 142 */       File file = new File(this.directory, files[i]);
/* 143 */       if (!file.delete()) {
/* 144 */         throw new IOException("Cannot delete " + files[i]);
/*     */       }
/*     */     }
/* 147 */     String lockPrefix = getLockPrefix().toString();
/* 148 */     files = this.lockDir.list();
/* 149 */     for (int i = 0; i < files.length; i++) {
/* 150 */       if (files[i].startsWith(lockPrefix))
/*     */       {
/* 152 */         File lockFile = new File(this.lockDir, files[i]);
/* 153 */         if (!lockFile.delete())
/* 154 */           throw new IOException("Cannot delete " + files[i]);
/*     */       }
/*     */     }
/*     */   }
/*     */   
/*     */   public final String[] list() throws IOException {
/* 160 */     return this.directory.list();
/*     */   }
/*     */   
/*     */   public final boolean fileExists(String name) throws IOException
/*     */   {
/* 165 */     File file = new File(this.directory, name);
/* 166 */     return file.exists();
/*     */   }
/*     */   
/*     */   public final long fileModified(String name) throws IOException
/*     */   {
/* 171 */     File file = new File(this.directory, name);
/* 172 */     return file.lastModified();
/*     */   }
/*     */   
/*     */   public static final long fileModified(File directory, String name)
/*     */     throws IOException
/*     */   {
/* 178 */     File file = new File(directory, name);
/* 179 */     return file.lastModified();
/*     */   }
/*     */   
/*     */   public void touchFile(String name) throws IOException
/*     */   {
/* 184 */     File file = new File(this.directory, name);
/* 185 */     file.setLastModified(System.currentTimeMillis());
/*     */   }
/*     */   
/*     */   public final long fileLength(String name) throws IOException
/*     */   {
/* 190 */     File file = new File(this.directory, name);
/* 191 */     return file.length();
/*     */   }
/*     */   
/*     */   public final void deleteFile(String name) throws IOException
/*     */   {
/* 196 */     File file = new File(this.directory, name);
/* 197 */     if (!file.delete()) {
/* 198 */       throw new IOException("Cannot delete " + name);
/*     */     }
/*     */   }
/*     */   
/*     */   public final synchronized void renameFile(String from, String to) throws IOException
/*     */   {
/* 204 */     File old = new File(this.directory, from);
/* 205 */     File nu = new File(this.directory, to);
/*     */     
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/* 211 */     if ((nu.exists()) && 
/* 212 */       (!nu.delete())) {
/* 213 */       throw new IOException("Cannot delete " + to);
/*     */     }
/*     */     
/*     */ 
/*     */ 
/* 218 */     if (!old.renameTo(nu)) {
/* 219 */       java.io.InputStream in = null;
/* 220 */       java.io.OutputStream out = null;
/*     */       try {
/* 222 */         in = new FileInputStream(old);
/* 223 */         out = new FileOutputStream(nu);
/*     */         
/*     */ 
/*     */ 
/* 227 */         if (this.buffer == null) {
/* 228 */           this.buffer = new byte['Ѐ'];
/*     */         }
/*     */         int len;
/* 231 */         while ((len = in.read(this.buffer)) >= 0) {
/* 232 */           out.write(this.buffer, 0, len);
/*     */         }
/*     */         
/*     */ 
/* 236 */         old.delete();
/*     */       }
/*     */       catch (IOException ioe) {
/* 239 */         throw new IOException("Cannot rename " + from + " to " + to);
/*     */       }
/*     */       finally {
/* 242 */         if (in != null) {
/*     */           try {
/* 244 */             in.close();
/*     */           } catch (IOException e) {
/* 246 */             throw new RuntimeException("Cannot close input stream: " + e.getMessage());
/*     */           }
/*     */         }
/* 249 */         if (out != null) {
/*     */           try {
/* 251 */             out.close();
/*     */           } catch (IOException e) {
/* 253 */             throw new RuntimeException("Cannot close output stream: " + e.getMessage());
/*     */           }
/*     */         }
/*     */       }
/*     */     }
/*     */   }
/*     */   
/*     */   public final OutputStream createFile(String name)
/*     */     throws IOException
/*     */   {
/* 263 */     return new FSOutputStream(new File(this.directory, name));
/*     */   }
/*     */   
/*     */   public final InputStream openFile(String name) throws IOException
/*     */   {
/* 268 */     return new FSInputStream(new File(this.directory, name));
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/* 274 */   private static final char[] HEX_DIGITS = { '0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'a', 'b', 'c', 'd', 'e', 'f' };
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
/*     */   public final Lock makeLock(String name)
/*     */   {
/* 289 */     StringBuffer buf = getLockPrefix();
/* 290 */     buf.append("-");
/* 291 */     buf.append(name);
/*     */     
/*     */ 
/* 294 */     File lockFile = new File(this.lockDir, buf.toString());
/*     */     
/* 296 */     new Lock() { private final File val$lockFile;
/*     */       
/* 298 */       public boolean obtain() throws IOException { if (FSDirectory.DISABLE_LOCKS) {
/* 299 */           return true;
/*     */         }
/* 301 */         if ((!FSDirectory.this.lockDir.exists()) && 
/* 302 */           (!FSDirectory.this.lockDir.mkdirs())) {
/* 303 */           throw new IOException("Cannot create lock directory: " + FSDirectory.this.lockDir);
/*     */         }
/*     */         
/*     */ 
/* 307 */         return this.val$lockFile.createNewFile();
/*     */       }
/*     */       
/* 310 */       public void release() { if (FSDirectory.DISABLE_LOCKS)
/* 311 */           return;
/* 312 */         this.val$lockFile.delete();
/*     */       }
/*     */       
/* 315 */       public boolean isLocked() { if (FSDirectory.DISABLE_LOCKS)
/* 316 */           return false;
/* 317 */         return this.val$lockFile.exists();
/*     */       }
/*     */       
/*     */       public String toString() {
/* 321 */         return "Lock@" + this.val$lockFile;
/*     */       }
/*     */     };
/*     */   }
/*     */   
/*     */   private StringBuffer getLockPrefix()
/*     */   {
/*     */     try {
/* 329 */       dirName = this.directory.getCanonicalPath();
/*     */     } catch (IOException e) { String dirName;
/* 331 */       throw new RuntimeException(e.toString());
/*     */     }
/*     */     
/*     */     byte[] digest;
/* 335 */     synchronized (DIGESTER) { String dirName;
/* 336 */       digest = DIGESTER.digest(dirName.getBytes()); }
/*     */     byte[] digest;
/* 338 */     StringBuffer buf = new StringBuffer();
/* 339 */     buf.append("lucene-");
/* 340 */     for (int i = 0; i < digest.length; i++) {
/* 341 */       int b = digest[i];
/* 342 */       buf.append(HEX_DIGITS[(b >> 4 & 0xF)]);
/* 343 */       buf.append(HEX_DIGITS[(b & 0xF)]);
/*     */     }
/*     */     
/* 346 */     return buf;
/*     */   }
/*     */   
/*     */   public final synchronized void close() throws IOException
/*     */   {
/* 351 */     if (--this.refCount <= 0) {
/* 352 */       synchronized (DIRECTORIES) {
/* 353 */         DIRECTORIES.remove(this.directory);
/*     */       }
/*     */     }
/*     */   }
/*     */   
/*     */   public File getFile() {
/* 359 */     return this.directory;
/*     */   }
/*     */   
/*     */   public String toString()
/*     */   {
/* 364 */     return "FSDirectory@" + this.directory;
/*     */   }
/*     */ }


/* Location:              /home/menick/Scrivania/ProcessMining/CNMining/CNMining.jar!/org/apache/lucene/store/FSDirectory.class
 * Java compiler version: 2 (46.0)
 * JD-Core Version:       0.7.1
 */