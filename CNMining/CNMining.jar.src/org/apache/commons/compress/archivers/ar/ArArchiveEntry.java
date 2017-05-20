/*     */ package org.apache.commons.compress.archivers.ar;
/*     */ 
/*     */ import java.io.File;
/*     */ import org.apache.commons.compress.archivers.ArchiveEntry;
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
/*     */ public class ArArchiveEntry
/*     */   implements ArchiveEntry
/*     */ {
/*     */   public static final String HEADER = "!<arch>\n";
/*     */   public static final String TRAILER = "`\n";
/*     */   private final String name;
/*     */   private final int userId;
/*     */   private final int groupId;
/*     */   private final int mode;
/*     */   private static final int DEFAULT_MODE = 33188;
/*     */   private final long lastModified;
/*     */   private final long length;
/*     */   
/*     */   public ArArchiveEntry(String name, long length)
/*     */   {
/*  73 */     this(name, length, 0, 0, 33188, System.currentTimeMillis());
/*     */   }
/*     */   
/*     */   public ArArchiveEntry(String name, long length, int userId, int groupId, int mode, long lastModified) {
/*  77 */     this.name = name;
/*  78 */     this.length = length;
/*  79 */     this.userId = userId;
/*  80 */     this.groupId = groupId;
/*  81 */     this.mode = mode;
/*  82 */     this.lastModified = lastModified;
/*     */   }
/*     */   
/*     */   public ArArchiveEntry(File inputFile, String entryName)
/*     */   {
/*  87 */     this(entryName, inputFile.isFile() ? inputFile.length() : 0L, 0, 0, 0, inputFile.lastModified());
/*     */   }
/*     */   
/*     */   public long getSize() {
/*  91 */     return getLength();
/*     */   }
/*     */   
/*     */   public String getName() {
/*  95 */     return this.name;
/*     */   }
/*     */   
/*     */   public int getUserId() {
/*  99 */     return this.userId;
/*     */   }
/*     */   
/*     */   public int getGroupId() {
/* 103 */     return this.groupId;
/*     */   }
/*     */   
/*     */   public int getMode() {
/* 107 */     return this.mode;
/*     */   }
/*     */   
/*     */   public long getLastModified() {
/* 111 */     return this.lastModified;
/*     */   }
/*     */   
/*     */   public long getLength() {
/* 115 */     return this.length;
/*     */   }
/*     */   
/*     */   public boolean isDirectory() {
/* 119 */     return false;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */   public int hashCode()
/*     */   {
/* 126 */     int prime = 31;
/* 127 */     int result = 1;
/* 128 */     result = 31 * result + (this.name == null ? 0 : this.name.hashCode());
/* 129 */     return result;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */   public boolean equals(Object obj)
/*     */   {
/* 136 */     if (this == obj) {
/* 137 */       return true;
/*     */     }
/* 139 */     if ((obj == null) || (getClass() != obj.getClass())) {
/* 140 */       return false;
/*     */     }
/* 142 */     ArArchiveEntry other = (ArArchiveEntry)obj;
/* 143 */     if (this.name == null) {
/* 144 */       if (other.name != null) {
/* 145 */         return false;
/*     */       }
/* 147 */     } else if (!this.name.equals(other.name)) {
/* 148 */       return false;
/*     */     }
/* 150 */     return true;
/*     */   }
/*     */ }


/* Location:              /home/menick/Scrivania/ProcessMining/CNMining/CNMining.jar!/org/apache/commons/compress/archivers/ar/ArArchiveEntry.class
 * Java compiler version: 4 (48.0)
 * JD-Core Version:       0.7.1
 */