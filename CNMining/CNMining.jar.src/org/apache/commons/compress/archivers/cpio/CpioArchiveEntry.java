/*     */ package org.apache.commons.compress.archivers.cpio;
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
/*     */ public class CpioArchiveEntry
/*     */   implements CpioConstants, ArchiveEntry
/*     */ {
/*     */   private final short fileFormat;
/*     */   private final int headerSize;
/*     */   private final int alignmentBoundary;
/* 162 */   private long chksum = 0L;
/*     */   
/*     */ 
/* 165 */   private long filesize = 0L;
/*     */   
/* 167 */   private long gid = 0L;
/*     */   
/* 169 */   private long inode = 0L;
/*     */   
/* 171 */   private long maj = 0L;
/*     */   
/* 173 */   private long min = 0L;
/*     */   
/* 175 */   private long mode = 0L;
/*     */   
/* 177 */   private long mtime = 0L;
/*     */   
/*     */   private String name;
/*     */   
/* 181 */   private long nlink = 0L;
/*     */   
/* 183 */   private long rmaj = 0L;
/*     */   
/* 185 */   private long rmin = 0L;
/*     */   
/* 187 */   private long uid = 0L;
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
/*     */   public CpioArchiveEntry(short format)
/*     */   {
/* 204 */     switch (format) {
/*     */     case 1: 
/* 206 */       this.headerSize = 110;
/* 207 */       this.alignmentBoundary = 4;
/* 208 */       break;
/*     */     case 2: 
/* 210 */       this.headerSize = 110;
/* 211 */       this.alignmentBoundary = 4;
/* 212 */       break;
/*     */     case 4: 
/* 214 */       this.headerSize = 76;
/* 215 */       this.alignmentBoundary = 0;
/* 216 */       break;
/*     */     case 8: 
/* 218 */       this.headerSize = 26;
/* 219 */       this.alignmentBoundary = 2;
/* 220 */       break;
/*     */     case 3: case 5: case 6: case 7: default: 
/* 222 */       throw new IllegalArgumentException("Unknown header type");
/*     */     }
/* 224 */     this.fileFormat = format;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public CpioArchiveEntry(String name)
/*     */   {
/* 235 */     this((short)1);
/* 236 */     this.name = name;
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
/*     */   public CpioArchiveEntry(String name, long size)
/*     */   {
/* 249 */     this((short)1);
/* 250 */     this.name = name;
/* 251 */     setSize(size);
/*     */   }
/*     */   
/*     */   public CpioArchiveEntry(File inputFile, String entryName) {
/* 255 */     this(entryName, inputFile.isFile() ? inputFile.length() : 0L);
/* 256 */     long mode = 0L;
/* 257 */     if (inputFile.isDirectory()) {
/* 258 */       mode |= 0x4000;
/* 259 */     } else if (inputFile.isFile()) {
/* 260 */       mode |= 0x8000;
/*     */     } else {
/* 262 */       throw new IllegalArgumentException("Cannot determine type of file " + inputFile.getName());
/*     */     }
/*     */     
/* 265 */     setMode(mode);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */   private void checkNewFormat()
/*     */   {
/* 272 */     if ((this.fileFormat & 0x3) == 0) {
/* 273 */       throw new UnsupportedOperationException();
/*     */     }
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */   private void checkOldFormat()
/*     */   {
/* 281 */     if ((this.fileFormat & 0xC) == 0) {
/* 282 */       throw new UnsupportedOperationException();
/*     */     }
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public long getChksum()
/*     */   {
/* 294 */     checkNewFormat();
/* 295 */     return this.chksum;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public long getDevice()
/*     */   {
/* 307 */     checkOldFormat();
/* 308 */     return this.min;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public long getDeviceMaj()
/*     */   {
/* 320 */     checkNewFormat();
/* 321 */     return this.maj;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public long getDeviceMin()
/*     */   {
/* 331 */     checkNewFormat();
/* 332 */     return this.min;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public long getSize()
/*     */   {
/* 342 */     return this.filesize;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public short getFormat()
/*     */   {
/* 351 */     return this.fileFormat;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public long getGID()
/*     */   {
/* 360 */     return this.gid;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public int getHeaderSize()
/*     */   {
/* 369 */     return this.headerSize;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public int getAlignmentBoundary()
/*     */   {
/* 378 */     return this.alignmentBoundary;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public int getHeaderPadCount()
/*     */   {
/* 387 */     if (this.alignmentBoundary == 0) return 0;
/* 388 */     int size = this.headerSize + this.name.length() + 1;
/* 389 */     int remain = size % this.alignmentBoundary;
/* 390 */     if (remain > 0) {
/* 391 */       return this.alignmentBoundary - remain;
/*     */     }
/* 393 */     return 0;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public int getDataPadCount()
/*     */   {
/* 402 */     if (this.alignmentBoundary == 0) return 0;
/* 403 */     long size = this.filesize;
/* 404 */     int remain = (int)(size % this.alignmentBoundary);
/* 405 */     if (remain > 0) {
/* 406 */       return this.alignmentBoundary - remain;
/*     */     }
/* 408 */     return 0;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public long getInode()
/*     */   {
/* 417 */     return this.inode;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public long getMode()
/*     */   {
/* 426 */     return this.mode;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public String getName()
/*     */   {
/* 435 */     return this.name;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public long getNumberOfLinks()
/*     */   {
/* 444 */     return this.nlink;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public long getRemoteDevice()
/*     */   {
/* 456 */     checkOldFormat();
/* 457 */     return this.rmin;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public long getRemoteDeviceMaj()
/*     */   {
/* 469 */     checkNewFormat();
/* 470 */     return this.rmaj;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public long getRemoteDeviceMin()
/*     */   {
/* 482 */     checkNewFormat();
/* 483 */     return this.rmin;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public long getTime()
/*     */   {
/* 492 */     return this.mtime;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public long getUID()
/*     */   {
/* 501 */     return this.uid;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public boolean isBlockDevice()
/*     */   {
/* 510 */     return (this.mode & 0xF000) == 24576L;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public boolean isCharacterDevice()
/*     */   {
/* 519 */     return (this.mode & 0xF000) == 8192L;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public boolean isDirectory()
/*     */   {
/* 528 */     return (this.mode & 0xF000) == 16384L;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public boolean isNetwork()
/*     */   {
/* 537 */     return (this.mode & 0xF000) == 36864L;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public boolean isPipe()
/*     */   {
/* 546 */     return (this.mode & 0xF000) == 4096L;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public boolean isRegularFile()
/*     */   {
/* 555 */     return (this.mode & 0xF000) == 32768L;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public boolean isSocket()
/*     */   {
/* 564 */     return (this.mode & 0xF000) == 49152L;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public boolean isSymbolicLink()
/*     */   {
/* 573 */     return (this.mode & 0xF000) == 40960L;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public void setChksum(long chksum)
/*     */   {
/* 584 */     checkNewFormat();
/* 585 */     this.chksum = chksum;
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
/*     */   public void setDevice(long device)
/*     */   {
/* 598 */     checkOldFormat();
/* 599 */     this.min = device;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public void setDeviceMaj(long maj)
/*     */   {
/* 609 */     checkNewFormat();
/* 610 */     this.maj = maj;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public void setDeviceMin(long min)
/*     */   {
/* 620 */     checkNewFormat();
/* 621 */     this.min = min;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public void setSize(long size)
/*     */   {
/* 631 */     if ((size < 0L) || (size > 4294967295L)) {
/* 632 */       throw new IllegalArgumentException("invalid entry size <" + size + ">");
/*     */     }
/*     */     
/* 635 */     this.filesize = size;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public void setGID(long gid)
/*     */   {
/* 645 */     this.gid = gid;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public void setInode(long inode)
/*     */   {
/* 655 */     this.inode = inode;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public void setMode(long mode)
/*     */   {
/* 665 */     long maskedMode = mode & 0xF000;
/* 666 */     switch ((int)maskedMode) {
/*     */     case 4096: 
/*     */     case 8192: 
/*     */     case 16384: 
/*     */     case 24576: 
/*     */     case 32768: 
/*     */     case 36864: 
/*     */     case 40960: 
/*     */     case 49152: 
/*     */       break;
/*     */     default: 
/* 677 */       throw new IllegalArgumentException("Unknown mode. Full: " + Long.toHexString(mode) + " Masked: " + Long.toHexString(maskedMode));
/*     */     }
/*     */     
/*     */     
/*     */ 
/*     */ 
/* 683 */     this.mode = mode;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public void setName(String name)
/*     */   {
/* 693 */     this.name = name;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public void setNumberOfLinks(long nlink)
/*     */   {
/* 703 */     this.nlink = nlink;
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
/*     */   public void setRemoteDevice(long device)
/*     */   {
/* 716 */     checkOldFormat();
/* 717 */     this.rmin = device;
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
/*     */   public void setRemoteDeviceMaj(long rmaj)
/*     */   {
/* 730 */     checkNewFormat();
/* 731 */     this.rmaj = rmaj;
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
/*     */   public void setRemoteDeviceMin(long rmin)
/*     */   {
/* 744 */     checkNewFormat();
/* 745 */     this.rmin = rmin;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public void setTime(long time)
/*     */   {
/* 755 */     this.mtime = time;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public void setUID(long uid)
/*     */   {
/* 765 */     this.uid = uid;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */   public int hashCode()
/*     */   {
/* 772 */     int prime = 31;
/* 773 */     int result = 1;
/* 774 */     result = 31 * result + (this.name == null ? 0 : this.name.hashCode());
/* 775 */     return result;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */   public boolean equals(Object obj)
/*     */   {
/* 782 */     if (this == obj) {
/* 783 */       return true;
/*     */     }
/* 785 */     if ((obj == null) || (getClass() != obj.getClass())) {
/* 786 */       return false;
/*     */     }
/* 788 */     CpioArchiveEntry other = (CpioArchiveEntry)obj;
/* 789 */     if (this.name == null) {
/* 790 */       if (other.name != null) {
/* 791 */         return false;
/*     */       }
/* 793 */     } else if (!this.name.equals(other.name)) {
/* 794 */       return false;
/*     */     }
/* 796 */     return true;
/*     */   }
/*     */ }


/* Location:              /home/menick/Scrivania/ProcessMining/CNMining/CNMining.jar!/org/apache/commons/compress/archivers/cpio/CpioArchiveEntry.class
 * Java compiler version: 4 (48.0)
 * JD-Core Version:       0.7.1
 */