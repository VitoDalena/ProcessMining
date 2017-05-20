/*     */ package org.apache.commons.compress.archivers.zip;
/*     */ 
/*     */ import java.io.File;
/*     */ import java.util.Collection;
/*     */ import java.util.LinkedHashMap;
/*     */ import java.util.NoSuchElementException;
/*     */ import java.util.zip.ZipEntry;
/*     */ import java.util.zip.ZipException;
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
/*     */ public class ZipArchiveEntry
/*     */   extends ZipEntry
/*     */   implements ArchiveEntry, Cloneable
/*     */ {
/*     */   public static final int PLATFORM_UNIX = 3;
/*     */   public static final int PLATFORM_FAT = 0;
/*     */   private static final int SHORT_MASK = 65535;
/*     */   private static final int SHORT_SHIFT = 16;
/*  39 */   private int internalAttributes = 0;
/*  40 */   private int platform = 0;
/*  41 */   private long externalAttributes = 0L;
/*  42 */   private LinkedHashMap extraFields = null;
/*  43 */   private String name = null;
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   public ZipArchiveEntry(String name)
/*     */   {
/*  50 */     super(name);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   public ZipArchiveEntry(ZipEntry entry)
/*     */     throws ZipException
/*     */   {
/*  59 */     super(entry);
/*  60 */     setName(entry.getName());
/*  61 */     byte[] extra = entry.getExtra();
/*  62 */     if (extra != null) {
/*  63 */       setExtraFields(ExtraFieldUtils.parse(extra));
/*     */     }
/*     */     else {
/*  66 */       setExtra();
/*     */     }
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   public ZipArchiveEntry(ZipArchiveEntry entry)
/*     */     throws ZipException
/*     */   {
/*  76 */     this(entry);
/*  77 */     setInternalAttributes(entry.getInternalAttributes());
/*  78 */     setExternalAttributes(entry.getExternalAttributes());
/*  79 */     setExtraFields(entry.getExtraFields());
/*     */   }
/*     */   
/*     */ 
/*     */   protected ZipArchiveEntry()
/*     */   {
/*  85 */     super("");
/*     */   }
/*     */   
/*     */   public ZipArchiveEntry(File inputFile, String entryName) {
/*  89 */     this(entryName);
/*  90 */     if (inputFile.isFile()) {
/*  91 */       setSize(inputFile.length());
/*     */     }
/*  93 */     setTime(inputFile.lastModified());
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public Object clone()
/*     */   {
/* 102 */     ZipArchiveEntry e = (ZipArchiveEntry)super.clone();
/*     */     
/* 104 */     e.extraFields = (this.extraFields != null ? (LinkedHashMap)this.extraFields.clone() : null);
/* 105 */     e.setInternalAttributes(getInternalAttributes());
/* 106 */     e.setExternalAttributes(getExternalAttributes());
/* 107 */     e.setExtraFields(getExtraFields());
/* 108 */     return e;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public int getInternalAttributes()
/*     */   {
/* 117 */     return this.internalAttributes;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   public void setInternalAttributes(int value)
/*     */   {
/* 125 */     this.internalAttributes = value;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   public long getExternalAttributes()
/*     */   {
/* 133 */     return this.externalAttributes;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   public void setExternalAttributes(long value)
/*     */   {
/* 141 */     this.externalAttributes = value;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public void setUnixMode(int mode)
/*     */   {
/* 151 */     setExternalAttributes(mode << 16 | ((mode & 0x80) == 0 ? 1 : 0) | (isDirectory() ? 16 : 0));
/*     */     
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/* 157 */     this.platform = 3;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   public int getUnixMode()
/*     */   {
/* 165 */     return this.platform != 3 ? 0 : (int)(getExternalAttributes() >> 16 & 0xFFFF);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public int getPlatform()
/*     */   {
/* 177 */     return this.platform;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   protected void setPlatform(int platform)
/*     */   {
/* 185 */     this.platform = platform;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   public void setExtraFields(ZipExtraField[] fields)
/*     */   {
/* 193 */     this.extraFields = new LinkedHashMap();
/* 194 */     for (int i = 0; i < fields.length; i++) {
/* 195 */       this.extraFields.put(fields[i].getHeaderId(), fields[i]);
/*     */     }
/* 197 */     setExtra();
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   public ZipExtraField[] getExtraFields()
/*     */   {
/* 205 */     if (this.extraFields == null) {
/* 206 */       return new ZipExtraField[0];
/*     */     }
/* 208 */     ZipExtraField[] result = new ZipExtraField[this.extraFields.size()];
/* 209 */     return (ZipExtraField[])this.extraFields.values().toArray(result);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public void addExtraField(ZipExtraField ze)
/*     */   {
/* 221 */     if (this.extraFields == null) {
/* 222 */       this.extraFields = new LinkedHashMap();
/*     */     }
/* 224 */     this.extraFields.put(ze.getHeaderId(), ze);
/* 225 */     setExtra();
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public void addAsFirstExtraField(ZipExtraField ze)
/*     */   {
/* 236 */     LinkedHashMap copy = this.extraFields;
/* 237 */     this.extraFields = new LinkedHashMap();
/* 238 */     this.extraFields.put(ze.getHeaderId(), ze);
/* 239 */     if (copy != null) {
/* 240 */       copy.remove(ze.getHeaderId());
/* 241 */       this.extraFields.putAll(copy);
/*     */     }
/* 243 */     setExtra();
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   public void removeExtraField(ZipShort type)
/*     */   {
/* 251 */     if (this.extraFields == null) {
/* 252 */       throw new NoSuchElementException();
/*     */     }
/* 254 */     if (this.extraFields.remove(type) == null) {
/* 255 */       throw new NoSuchElementException();
/*     */     }
/* 257 */     setExtra();
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public ZipExtraField getExtraField(ZipShort type)
/*     */   {
/* 266 */     if (this.extraFields != null) {
/* 267 */       return (ZipExtraField)this.extraFields.get(type);
/*     */     }
/* 269 */     return null;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   public void setExtra(byte[] extra)
/*     */     throws RuntimeException
/*     */   {
/*     */     try
/*     */     {
/* 280 */       ZipExtraField[] local = ExtraFieldUtils.parse(extra, true);
/* 281 */       mergeExtraFields(local, true);
/*     */     } catch (ZipException e) {
/* 283 */       throw new RuntimeException(e.getMessage(), e);
/*     */     }
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   protected void setExtra()
/*     */   {
/* 294 */     super.setExtra(ExtraFieldUtils.mergeLocalFileDataData(getExtraFields()));
/*     */   }
/*     */   
/*     */ 
/*     */   public void setCentralDirectoryExtra(byte[] b)
/*     */   {
/*     */     try
/*     */     {
/* 302 */       ZipExtraField[] central = ExtraFieldUtils.parse(b, false);
/* 303 */       mergeExtraFields(central, false);
/*     */     } catch (ZipException e) {
/* 305 */       throw new RuntimeException(e.getMessage(), e);
/*     */     }
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   public byte[] getLocalFileDataExtra()
/*     */   {
/* 314 */     byte[] extra = getExtra();
/* 315 */     return extra != null ? extra : new byte[0];
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   public byte[] getCentralDirectoryExtra()
/*     */   {
/* 323 */     return ExtraFieldUtils.mergeCentralDirectoryData(getExtraFields());
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   public String getName()
/*     */   {
/* 331 */     return this.name == null ? super.getName() : this.name;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   public boolean isDirectory()
/*     */   {
/* 339 */     return getName().endsWith("/");
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   protected void setName(String name)
/*     */   {
/* 347 */     this.name = name;
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
/*     */   public int hashCode()
/*     */   {
/* 360 */     return getName().hashCode();
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   private void mergeExtraFields(ZipExtraField[] f, boolean local)
/*     */     throws ZipException
/*     */   {
/* 373 */     if (this.extraFields == null) {
/* 374 */       setExtraFields(f);
/*     */     } else {
/* 376 */       for (int i = 0; i < f.length; i++) {
/* 377 */         ZipExtraField existing = getExtraField(f[i].getHeaderId());
/* 378 */         if (existing == null) {
/* 379 */           addExtraField(f[i]);
/*     */         }
/* 381 */         else if (local) {
/* 382 */           byte[] b = f[i].getLocalFileDataData();
/* 383 */           existing.parseFromLocalFileData(b, 0, b.length);
/*     */         } else {
/* 385 */           byte[] b = f[i].getCentralDirectoryData();
/* 386 */           existing.parseFromCentralDirectoryData(b, 0, b.length);
/*     */         }
/*     */       }
/*     */       
/* 390 */       setExtra();
/*     */     }
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */   public boolean equals(Object obj)
/*     */   {
/* 398 */     if (this == obj) {
/* 399 */       return true;
/*     */     }
/* 401 */     if ((obj == null) || (getClass() != obj.getClass())) {
/* 402 */       return false;
/*     */     }
/* 404 */     ZipArchiveEntry other = (ZipArchiveEntry)obj;
/* 405 */     if (this.name == null) {
/* 406 */       if (other.name != null) {
/* 407 */         return false;
/*     */       }
/* 409 */     } else if (!this.name.equals(other.name)) {
/* 410 */       return false;
/*     */     }
/* 412 */     return true;
/*     */   }
/*     */ }


/* Location:              /home/menick/Scrivania/ProcessMining/CNMining/CNMining.jar!/org/apache/commons/compress/archivers/zip/ZipArchiveEntry.class
 * Java compiler version: 4 (48.0)
 * JD-Core Version:       0.7.1
 */