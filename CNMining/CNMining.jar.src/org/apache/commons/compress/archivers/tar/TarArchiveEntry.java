/*     */ package org.apache.commons.compress.archivers.tar;
/*     */ 
/*     */ import java.io.File;
/*     */ import java.util.Date;
/*     */ import java.util.Locale;
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
/*     */ public class TarArchiveEntry
/*     */   implements TarConstants, ArchiveEntry
/*     */ {
/*     */   private String name;
/*     */   private int mode;
/*     */   private int userId;
/*     */   private int groupId;
/*     */   private long size;
/*     */   private long modTime;
/*     */   private byte linkFlag;
/*     */   private String linkName;
/*     */   private String magic;
/*     */   private String version;
/*     */   private String userName;
/*     */   private String groupName;
/*     */   private int devMajor;
/*     */   private int devMinor;
/*     */   private File file;
/*     */   public static final int MAX_NAMELEN = 31;
/*     */   public static final int DEFAULT_DIR_MODE = 16877;
/*     */   public static final int DEFAULT_FILE_MODE = 33188;
/*     */   public static final int MILLIS_PER_SECOND = 1000;
/*     */   
/*     */   private TarArchiveEntry()
/*     */   {
/* 145 */     this.magic = "ustar\000";
/* 146 */     this.version = "00";
/* 147 */     this.name = "";
/* 148 */     this.linkName = "";
/*     */     
/* 150 */     String user = System.getProperty("user.name", "");
/*     */     
/* 152 */     if (user.length() > 31) {
/* 153 */       user = user.substring(0, 31);
/*     */     }
/*     */     
/* 156 */     this.userId = 0;
/* 157 */     this.groupId = 0;
/* 158 */     this.userName = user;
/* 159 */     this.groupName = "";
/* 160 */     this.file = null;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public TarArchiveEntry(String name)
/*     */   {
/* 170 */     this();
/*     */     
/* 172 */     name = normalizeFileName(name);
/* 173 */     boolean isDir = name.endsWith("/");
/*     */     
/* 175 */     this.devMajor = 0;
/* 176 */     this.devMinor = 0;
/* 177 */     this.name = name;
/* 178 */     this.mode = (isDir ? 16877 : 33188);
/* 179 */     this.linkFlag = (isDir ? 53 : 48);
/* 180 */     this.userId = 0;
/* 181 */     this.groupId = 0;
/* 182 */     this.size = 0L;
/* 183 */     this.modTime = (new Date().getTime() / 1000L);
/* 184 */     this.linkName = "";
/* 185 */     this.userName = "";
/* 186 */     this.groupName = "";
/* 187 */     this.devMajor = 0;
/* 188 */     this.devMinor = 0;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public TarArchiveEntry(String name, byte linkFlag)
/*     */   {
/* 199 */     this(name);
/* 200 */     this.linkFlag = linkFlag;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public TarArchiveEntry(File file)
/*     */   {
/* 211 */     this(file, normalizeFileName(file.getPath()));
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public TarArchiveEntry(File file, String fileName)
/*     */   {
/* 222 */     this();
/*     */     
/* 224 */     this.file = file;
/*     */     
/* 226 */     this.linkName = "";
/*     */     
/* 228 */     if (file.isDirectory()) {
/* 229 */       this.mode = 16877;
/* 230 */       this.linkFlag = 53;
/*     */       
/* 232 */       int nameLength = fileName.length();
/* 233 */       if ((nameLength == 0) || (fileName.charAt(nameLength - 1) != '/')) {
/* 234 */         this.name = (fileName + "/");
/*     */       } else {
/* 236 */         this.name = fileName;
/*     */       }
/* 238 */       this.size = 0L;
/*     */     } else {
/* 240 */       this.mode = 33188;
/* 241 */       this.linkFlag = 48;
/* 242 */       this.size = file.length();
/* 243 */       this.name = fileName;
/*     */     }
/*     */     
/* 246 */     this.modTime = (file.lastModified() / 1000L);
/* 247 */     this.devMajor = 0;
/* 248 */     this.devMinor = 0;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public TarArchiveEntry(byte[] headerBuf)
/*     */   {
/* 258 */     this();
/* 259 */     parseTarHeader(headerBuf);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public boolean equals(TarArchiveEntry it)
/*     */   {
/* 270 */     return getName().equals(it.getName());
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public boolean equals(Object it)
/*     */   {
/* 281 */     if ((it == null) || (getClass() != it.getClass())) {
/* 282 */       return false;
/*     */     }
/* 284 */     return equals((TarArchiveEntry)it);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public int hashCode()
/*     */   {
/* 293 */     return getName().hashCode();
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public boolean isDescendent(TarArchiveEntry desc)
/*     */   {
/* 305 */     return desc.getName().startsWith(getName());
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public String getName()
/*     */   {
/* 314 */     return this.name.toString();
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public void setName(String name)
/*     */   {
/* 323 */     this.name = normalizeFileName(name);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public void setMode(int mode)
/*     */   {
/* 332 */     this.mode = mode;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public String getLinkName()
/*     */   {
/* 341 */     return this.linkName.toString();
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public int getUserId()
/*     */   {
/* 350 */     return this.userId;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public void setUserId(int userId)
/*     */   {
/* 359 */     this.userId = userId;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public int getGroupId()
/*     */   {
/* 368 */     return this.groupId;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public void setGroupId(int groupId)
/*     */   {
/* 377 */     this.groupId = groupId;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public String getUserName()
/*     */   {
/* 386 */     return this.userName.toString();
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public void setUserName(String userName)
/*     */   {
/* 395 */     this.userName = userName;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public String getGroupName()
/*     */   {
/* 404 */     return this.groupName.toString();
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public void setGroupName(String groupName)
/*     */   {
/* 413 */     this.groupName = groupName;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public void setIds(int userId, int groupId)
/*     */   {
/* 423 */     setUserId(userId);
/* 424 */     setGroupId(groupId);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public void setNames(String userName, String groupName)
/*     */   {
/* 434 */     setUserName(userName);
/* 435 */     setGroupName(groupName);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public void setModTime(long time)
/*     */   {
/* 445 */     this.modTime = (time / 1000L);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public void setModTime(Date time)
/*     */   {
/* 454 */     this.modTime = (time.getTime() / 1000L);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public Date getModTime()
/*     */   {
/* 463 */     return new Date(this.modTime * 1000L);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public File getFile()
/*     */   {
/* 472 */     return this.file;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public int getMode()
/*     */   {
/* 481 */     return this.mode;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public long getSize()
/*     */   {
/* 490 */     return this.size;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public void setSize(long size)
/*     */   {
/* 501 */     if ((size > 8589934591L) || (size < 0L)) {
/* 502 */       throw new IllegalArgumentException("Size is out of range: " + size);
/*     */     }
/* 504 */     this.size = size;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public boolean isGNULongNameEntry()
/*     */   {
/* 514 */     return (this.linkFlag == 76) && (this.name.toString().equals("././@LongLink"));
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public boolean isDirectory()
/*     */   {
/* 524 */     if (this.file != null) {
/* 525 */       return this.file.isDirectory();
/*     */     }
/*     */     
/* 528 */     if (this.linkFlag == 53) {
/* 529 */       return true;
/*     */     }
/*     */     
/* 532 */     if (getName().endsWith("/")) {
/* 533 */       return true;
/*     */     }
/*     */     
/* 536 */     return false;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public TarArchiveEntry[] getDirectoryEntries()
/*     */   {
/* 546 */     if ((this.file == null) || (!this.file.isDirectory())) {
/* 547 */       return new TarArchiveEntry[0];
/*     */     }
/*     */     
/* 550 */     String[] list = this.file.list();
/* 551 */     TarArchiveEntry[] result = new TarArchiveEntry[list.length];
/*     */     
/* 553 */     for (int i = 0; i < list.length; i++) {
/* 554 */       result[i] = new TarArchiveEntry(new File(this.file, list[i]));
/*     */     }
/*     */     
/* 557 */     return result;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public void writeEntryHeader(byte[] outbuf)
/*     */   {
/* 566 */     int offset = 0;
/*     */     
/* 568 */     offset = TarUtils.formatNameBytes(this.name, outbuf, offset, 100);
/* 569 */     offset = TarUtils.formatOctalBytes(this.mode, outbuf, offset, 8);
/* 570 */     offset = TarUtils.formatOctalBytes(this.userId, outbuf, offset, 8);
/* 571 */     offset = TarUtils.formatOctalBytes(this.groupId, outbuf, offset, 8);
/* 572 */     offset = TarUtils.formatLongOctalBytes(this.size, outbuf, offset, 12);
/* 573 */     offset = TarUtils.formatLongOctalBytes(this.modTime, outbuf, offset, 12);
/*     */     
/* 575 */     int csOffset = offset;
/*     */     
/* 577 */     for (int c = 0; c < 8; c++) {
/* 578 */       outbuf[(offset++)] = 32;
/*     */     }
/*     */     
/* 581 */     outbuf[(offset++)] = this.linkFlag;
/* 582 */     offset = TarUtils.formatNameBytes(this.linkName, outbuf, offset, 100);
/* 583 */     offset = TarUtils.formatNameBytes(this.magic, outbuf, offset, 6);
/* 584 */     offset = TarUtils.formatNameBytes(this.version, outbuf, offset, 2);
/* 585 */     offset = TarUtils.formatNameBytes(this.userName, outbuf, offset, 32);
/* 586 */     offset = TarUtils.formatNameBytes(this.groupName, outbuf, offset, 32);
/* 587 */     offset = TarUtils.formatOctalBytes(this.devMajor, outbuf, offset, 8);
/* 588 */     offset = TarUtils.formatOctalBytes(this.devMinor, outbuf, offset, 8);
/*     */     
/* 590 */     while (offset < outbuf.length) {
/* 591 */       outbuf[(offset++)] = 0;
/*     */     }
/*     */     
/* 594 */     long chk = TarUtils.computeCheckSum(outbuf);
/*     */     
/* 596 */     TarUtils.formatCheckSumOctalBytes(chk, outbuf, csOffset, 8);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public void parseTarHeader(byte[] header)
/*     */   {
/* 605 */     int offset = 0;
/*     */     
/* 607 */     this.name = TarUtils.parseName(header, offset, 100);
/* 608 */     offset += 100;
/* 609 */     this.mode = ((int)TarUtils.parseOctal(header, offset, 8));
/* 610 */     offset += 8;
/* 611 */     this.userId = ((int)TarUtils.parseOctal(header, offset, 8));
/* 612 */     offset += 8;
/* 613 */     this.groupId = ((int)TarUtils.parseOctal(header, offset, 8));
/* 614 */     offset += 8;
/* 615 */     this.size = TarUtils.parseOctal(header, offset, 12);
/* 616 */     offset += 12;
/* 617 */     this.modTime = TarUtils.parseOctal(header, offset, 12);
/* 618 */     offset += 12;
/* 619 */     offset += 8;
/* 620 */     this.linkFlag = header[(offset++)];
/* 621 */     this.linkName = TarUtils.parseName(header, offset, 100);
/* 622 */     offset += 100;
/* 623 */     this.magic = TarUtils.parseName(header, offset, 6);
/* 624 */     offset += 6;
/* 625 */     this.version = TarUtils.parseName(header, offset, 2);
/* 626 */     offset += 2;
/* 627 */     this.userName = TarUtils.parseName(header, offset, 32);
/* 628 */     offset += 32;
/* 629 */     this.groupName = TarUtils.parseName(header, offset, 32);
/* 630 */     offset += 32;
/* 631 */     this.devMajor = ((int)TarUtils.parseOctal(header, offset, 8));
/* 632 */     offset += 8;
/* 633 */     this.devMinor = ((int)TarUtils.parseOctal(header, offset, 8));
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   private static String normalizeFileName(String fileName)
/*     */   {
/* 641 */     String osname = System.getProperty("os.name").toLowerCase(Locale.US);
/*     */     
/* 643 */     if (osname != null)
/*     */     {
/*     */ 
/*     */ 
/*     */ 
/* 648 */       if (osname.startsWith("windows")) {
/* 649 */         if (fileName.length() > 2) {
/* 650 */           char ch1 = fileName.charAt(0);
/* 651 */           char ch2 = fileName.charAt(1);
/*     */           
/* 653 */           if ((ch2 == ':') && (((ch1 >= 'a') && (ch1 <= 'z')) || ((ch1 >= 'A') && (ch1 <= 'Z'))))
/*     */           {
/*     */ 
/* 656 */             fileName = fileName.substring(2);
/*     */           }
/*     */         }
/* 659 */       } else if (osname.indexOf("netware") > -1) {
/* 660 */         int colon = fileName.indexOf(':');
/* 661 */         if (colon != -1) {
/* 662 */           fileName = fileName.substring(colon + 1);
/*     */         }
/*     */       }
/*     */     }
/*     */     
/* 667 */     fileName = fileName.replace(File.separatorChar, '/');
/*     */     
/*     */ 
/*     */ 
/*     */ 
/* 672 */     while (fileName.startsWith("/")) {
/* 673 */       fileName = fileName.substring(1);
/*     */     }
/* 675 */     return fileName;
/*     */   }
/*     */ }


/* Location:              /home/menick/Scrivania/ProcessMining/CNMining/CNMining.jar!/org/apache/commons/compress/archivers/tar/TarArchiveEntry.class
 * Java compiler version: 4 (48.0)
 * JD-Core Version:       0.7.1
 */