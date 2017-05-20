/*     */ package org.apache.commons.compress.archivers.zip;
/*     */ 
/*     */ import java.io.File;
/*     */ import java.io.FileOutputStream;
/*     */ import java.io.IOException;
/*     */ import java.io.OutputStream;
/*     */ import java.io.RandomAccessFile;
/*     */ import java.nio.ByteBuffer;
/*     */ import java.util.HashMap;
/*     */ import java.util.Iterator;
/*     */ import java.util.LinkedList;
/*     */ import java.util.List;
/*     */ import java.util.Map;
/*     */ import java.util.zip.CRC32;
/*     */ import java.util.zip.Deflater;
/*     */ import java.util.zip.ZipException;
/*     */ import org.apache.commons.compress.archivers.ArchiveEntry;
/*     */ import org.apache.commons.compress.archivers.ArchiveOutputStream;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class ZipArchiveOutputStream
/*     */   extends ArchiveOutputStream
/*     */ {
/*     */   static final int BYTE_MASK = 255;
/*     */   private static final int SHORT = 2;
/*     */   private static final int WORD = 4;
/*     */   static final int BUFFER_SIZE = 512;
/*  66 */   protected boolean finished = false;
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   private static final int DEFLATER_BLOCK_SIZE = 8192;
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public static final int DEFLATED = 8;
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public static final int DEFAULT_COMPRESSION = -1;
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public static final int STORED = 0;
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   static final String DEFAULT_ENCODING = "UTF8";
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   public static final int EFS_FLAG = 2048;
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   private ZipArchiveEntry entry;
/*     */   
/*     */ 
/*     */ 
/*     */ 
/* 111 */   private String comment = "";
/*     */   
/*     */ 
/*     */ 
/*     */ 
/* 116 */   private int level = -1;
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/* 122 */   private boolean hasCompressionLevelChanged = false;
/*     */   
/*     */ 
/*     */ 
/*     */ 
/* 127 */   private int method = 8;
/*     */   
/*     */ 
/*     */ 
/*     */ 
/* 132 */   private final List entries = new LinkedList();
/*     */   
/*     */ 
/*     */ 
/*     */ 
/* 137 */   private final CRC32 crc = new CRC32();
/*     */   
/*     */ 
/*     */ 
/*     */ 
/* 142 */   private long written = 0L;
/*     */   
/*     */ 
/*     */ 
/*     */ 
/* 147 */   private long dataStart = 0L;
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/* 153 */   private long localDataStart = 0L;
/*     */   
/*     */ 
/*     */ 
/*     */ 
/* 158 */   private long cdOffset = 0L;
/*     */   
/*     */ 
/*     */ 
/*     */ 
/* 163 */   private long cdLength = 0L;
/*     */   
/*     */ 
/*     */ 
/*     */ 
/* 168 */   private static final byte[] ZERO = { 0, 0 };
/*     */   
/*     */ 
/*     */ 
/*     */ 
/* 173 */   private static final byte[] LZERO = { 0, 0, 0, 0 };
/*     */   
/*     */ 
/*     */ 
/*     */ 
/* 178 */   private final Map offsets = new HashMap();
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/* 187 */   private String encoding = "UTF8";
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/* 195 */   private ZipEncoding zipEncoding = ZipEncodingHelper.getZipEncoding("UTF8");
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/* 202 */   protected final Deflater def = new Deflater(this.level, true);
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/* 208 */   private final byte[] buf = new byte['Ȁ'];
/*     */   
/*     */ 
/*     */ 
/*     */   private final RandomAccessFile raf;
/*     */   
/*     */ 
/*     */ 
/*     */   private final OutputStream out;
/*     */   
/*     */ 
/*     */ 
/* 220 */   private boolean useEFS = true;
/*     */   
/*     */ 
/*     */ 
/*     */ 
/* 225 */   private boolean fallbackToUTF8 = false;
/*     */   
/*     */ 
/*     */ 
/*     */ 
/* 230 */   private UnicodeExtraFieldPolicy createUnicodeExtraFields = UnicodeExtraFieldPolicy.NEVER;
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   public ZipArchiveOutputStream(OutputStream out)
/*     */   {
/* 237 */     this.out = out;
/* 238 */     this.raf = null;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public ZipArchiveOutputStream(File file)
/*     */     throws IOException
/*     */   {
/* 248 */     OutputStream o = null;
/* 249 */     RandomAccessFile _raf = null;
/*     */     try {
/* 251 */       _raf = new RandomAccessFile(file, "rw");
/* 252 */       _raf.setLength(0L);
/*     */     } catch (IOException e) {
/* 254 */       if (_raf != null) {
/*     */         try {
/* 256 */           _raf.close();
/*     */         }
/*     */         catch (IOException inner) {}
/*     */         
/* 260 */         _raf = null;
/*     */       }
/* 262 */       o = new FileOutputStream(file);
/*     */     }
/* 264 */     this.out = o;
/* 265 */     this.raf = _raf;
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
/*     */   public boolean isSeekable()
/*     */   {
/* 278 */     return this.raf != null;
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
/*     */   public void setEncoding(String encoding)
/*     */   {
/* 291 */     this.encoding = encoding;
/* 292 */     this.zipEncoding = ZipEncodingHelper.getZipEncoding(encoding);
/* 293 */     this.useEFS &= ZipEncodingHelper.isUTF8(encoding);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public String getEncoding()
/*     */   {
/* 302 */     return this.encoding;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public void setUseLanguageEncodingFlag(boolean b)
/*     */   {
/* 312 */     this.useEFS = ((b) && (ZipEncodingHelper.isUTF8(this.encoding)));
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public void setCreateUnicodeExtraFields(UnicodeExtraFieldPolicy b)
/*     */   {
/* 321 */     this.createUnicodeExtraFields = b;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public void setFallbackToUTF8(boolean b)
/*     */   {
/* 331 */     this.fallbackToUTF8 = b;
/*     */   }
/*     */   
/*     */ 
/*     */   public void finish()
/*     */     throws IOException
/*     */   {
/* 338 */     if (this.finished) {
/* 339 */       throw new IOException("This archive has already been finished");
/*     */     }
/*     */     
/* 342 */     if (this.entry != null) {
/* 343 */       throw new IOException("This archives contains unclosed entries.");
/*     */     }
/*     */     
/* 346 */     this.cdOffset = this.written;
/* 347 */     for (Iterator i = this.entries.iterator(); i.hasNext();) {
/* 348 */       writeCentralFileHeader((ZipArchiveEntry)i.next());
/*     */     }
/* 350 */     this.cdLength = (this.written - this.cdOffset);
/* 351 */     writeCentralDirectoryEnd();
/* 352 */     this.offsets.clear();
/* 353 */     this.entries.clear();
/* 354 */     this.finished = true;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */   public void closeArchiveEntry()
/*     */     throws IOException
/*     */   {
/* 362 */     if (this.finished) {
/* 363 */       throw new IOException("Stream has already been finished");
/*     */     }
/*     */     
/* 366 */     if (this.entry == null) {
/* 367 */       throw new IOException("No current entry to close");
/*     */     }
/*     */     
/* 370 */     long realCrc = this.crc.getValue();
/* 371 */     this.crc.reset();
/*     */     
/* 373 */     if (this.entry.getMethod() == 8) {
/* 374 */       this.def.finish();
/* 375 */       while (!this.def.finished()) {
/* 376 */         deflate();
/*     */       }
/*     */       
/* 379 */       this.entry.setSize(ZipUtil.adjustToLong(this.def.getTotalIn()));
/* 380 */       this.entry.setCompressedSize(ZipUtil.adjustToLong(this.def.getTotalOut()));
/* 381 */       this.entry.setCrc(realCrc);
/*     */       
/* 383 */       this.def.reset();
/*     */       
/* 385 */       this.written += this.entry.getCompressedSize();
/* 386 */     } else if (this.raf == null) {
/* 387 */       if (this.entry.getCrc() != realCrc) {
/* 388 */         throw new ZipException("bad CRC checksum for entry " + this.entry.getName() + ": " + Long.toHexString(this.entry.getCrc()) + " instead of " + Long.toHexString(realCrc));
/*     */       }
/*     */       
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/* 395 */       if (this.entry.getSize() != this.written - this.dataStart) {
/* 396 */         throw new ZipException("bad size for entry " + this.entry.getName() + ": " + this.entry.getSize() + " instead of " + (this.written - this.dataStart));
/*     */       }
/*     */       
/*     */ 
/*     */     }
/*     */     else
/*     */     {
/* 403 */       long size = this.written - this.dataStart;
/*     */       
/* 405 */       this.entry.setSize(size);
/* 406 */       this.entry.setCompressedSize(size);
/* 407 */       this.entry.setCrc(realCrc);
/*     */     }
/*     */     
/*     */ 
/*     */ 
/* 412 */     if (this.raf != null) {
/* 413 */       long save = this.raf.getFilePointer();
/*     */       
/* 415 */       this.raf.seek(this.localDataStart);
/* 416 */       writeOut(ZipLong.getBytes(this.entry.getCrc()));
/* 417 */       writeOut(ZipLong.getBytes(this.entry.getCompressedSize()));
/* 418 */       writeOut(ZipLong.getBytes(this.entry.getSize()));
/* 419 */       this.raf.seek(save);
/*     */     }
/*     */     
/* 422 */     writeDataDescriptor(this.entry);
/* 423 */     this.entry = null;
/*     */   }
/*     */   
/*     */   public void putArchiveEntry(ArchiveEntry archiveEntry)
/*     */     throws IOException
/*     */   {
/* 429 */     if (this.finished) {
/* 430 */       throw new IOException("Stream has already been finished");
/*     */     }
/*     */     
/* 433 */     if (this.entry != null) {
/* 434 */       closeArchiveEntry();
/*     */     }
/*     */     
/* 437 */     this.entry = ((ZipArchiveEntry)archiveEntry);
/* 438 */     this.entries.add(this.entry);
/*     */     
/* 440 */     if (this.entry.getMethod() == -1) {
/* 441 */       this.entry.setMethod(this.method);
/*     */     }
/*     */     
/* 444 */     if (this.entry.getTime() == -1L) {
/* 445 */       this.entry.setTime(System.currentTimeMillis());
/*     */     }
/*     */     
/*     */ 
/* 449 */     if ((this.entry.getMethod() == 0) && (this.raf == null)) {
/* 450 */       if (this.entry.getSize() == -1L) {
/* 451 */         throw new ZipException("uncompressed size is required for STORED method when not writing to a file");
/*     */       }
/*     */       
/*     */ 
/* 455 */       if (this.entry.getCrc() == -1L) {
/* 456 */         throw new ZipException("crc checksum is required for STORED method when not writing to a file");
/*     */       }
/*     */       
/* 459 */       this.entry.setCompressedSize(this.entry.getSize());
/*     */     }
/*     */     
/* 462 */     if ((this.entry.getMethod() == 8) && (this.hasCompressionLevelChanged)) {
/* 463 */       this.def.setLevel(this.level);
/* 464 */       this.hasCompressionLevelChanged = false;
/*     */     }
/* 466 */     writeLocalFileHeader(this.entry);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   public void setComment(String comment)
/*     */   {
/* 474 */     this.comment = comment;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public void setLevel(int level)
/*     */   {
/* 486 */     if ((level < -1) || (level > 9))
/*     */     {
/* 488 */       throw new IllegalArgumentException("Invalid compression level: " + level);
/*     */     }
/*     */     
/* 491 */     this.hasCompressionLevelChanged = (this.level != level);
/* 492 */     this.level = level;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public void setMethod(int method)
/*     */   {
/* 502 */     this.method = method;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public void write(byte[] b, int offset, int length)
/*     */     throws IOException
/*     */   {
/* 513 */     if (this.entry.getMethod() == 8) {
/* 514 */       if ((length > 0) && 
/* 515 */         (!this.def.finished())) {
/* 516 */         if (length <= 8192) {
/* 517 */           this.def.setInput(b, offset, length);
/* 518 */           deflateUntilInputIsNeeded();
/*     */         } else {
/* 520 */           int fullblocks = length / 8192;
/* 521 */           for (int i = 0; i < fullblocks; i++) {
/* 522 */             this.def.setInput(b, offset + i * 8192, 8192);
/*     */             
/* 524 */             deflateUntilInputIsNeeded();
/*     */           }
/* 526 */           int done = fullblocks * 8192;
/* 527 */           if (done < length) {
/* 528 */             this.def.setInput(b, offset + done, length - done);
/* 529 */             deflateUntilInputIsNeeded();
/*     */           }
/*     */         }
/*     */       }
/*     */     }
/*     */     else {
/* 535 */       writeOut(b, offset, length);
/* 536 */       this.written += length;
/*     */     }
/* 538 */     this.crc.update(b, offset, length);
/* 539 */     count(length);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public void close()
/*     */     throws IOException
/*     */   {
/* 549 */     if (!this.finished) {
/* 550 */       finish();
/*     */     }
/*     */     
/* 553 */     if (this.raf != null) {
/* 554 */       this.raf.close();
/*     */     }
/* 556 */     if (this.out != null) {
/* 557 */       this.out.close();
/*     */     }
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public void flush()
/*     */     throws IOException
/*     */   {
/* 568 */     if (this.out != null) {
/* 569 */       this.out.flush();
/*     */     }
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/* 579 */   static final byte[] LFH_SIG = ZipLong.LFH_SIG.getBytes();
/*     */   
/*     */ 
/*     */ 
/* 583 */   static final byte[] DD_SIG = ZipLong.getBytes(134695760L);
/*     */   
/*     */ 
/*     */ 
/* 587 */   static final byte[] CFH_SIG = ZipLong.CFH_SIG.getBytes();
/*     */   
/*     */ 
/*     */ 
/* 591 */   static final byte[] EOCD_SIG = ZipLong.getBytes(101010256L);
/*     */   
/*     */ 
/*     */ 
/*     */   protected final void deflate()
/*     */     throws IOException
/*     */   {
/* 598 */     int len = this.def.deflate(this.buf, 0, this.buf.length);
/* 599 */     if (len > 0) {
/* 600 */       writeOut(this.buf, 0, len);
/*     */     }
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   protected void writeLocalFileHeader(ZipArchiveEntry ze)
/*     */     throws IOException
/*     */   {
/* 611 */     boolean encodable = this.zipEncoding.canEncode(ze.getName());
/*     */     
/*     */     ZipEncoding entryEncoding;
/*     */     ZipEncoding entryEncoding;
/* 615 */     if ((!encodable) && (this.fallbackToUTF8)) {
/* 616 */       entryEncoding = ZipEncodingHelper.UTF8_ZIP_ENCODING;
/*     */     } else {
/* 618 */       entryEncoding = this.zipEncoding;
/*     */     }
/*     */     
/* 621 */     ByteBuffer name = entryEncoding.encode(ze.getName());
/*     */     
/* 623 */     if (this.createUnicodeExtraFields != UnicodeExtraFieldPolicy.NEVER)
/*     */     {
/* 625 */       if ((this.createUnicodeExtraFields == UnicodeExtraFieldPolicy.ALWAYS) || (!encodable))
/*     */       {
/* 627 */         ze.addExtraField(new UnicodePathExtraField(ze.getName(), name.array(), name.arrayOffset(), name.limit()));
/*     */       }
/*     */       
/*     */ 
/*     */ 
/*     */ 
/* 633 */       String comm = ze.getComment();
/* 634 */       if ((comm != null) && (!"".equals(comm)))
/*     */       {
/* 636 */         boolean commentEncodable = this.zipEncoding.canEncode(comm);
/*     */         
/* 638 */         if ((this.createUnicodeExtraFields == UnicodeExtraFieldPolicy.ALWAYS) || (!commentEncodable))
/*     */         {
/* 640 */           ByteBuffer commentB = entryEncoding.encode(comm);
/* 641 */           ze.addExtraField(new UnicodeCommentExtraField(comm, commentB.array(), commentB.arrayOffset(), commentB.limit()));
/*     */         }
/*     */       }
/*     */     }
/*     */     
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/* 650 */     this.offsets.put(ze, ZipLong.getBytes(this.written));
/*     */     
/* 652 */     writeOut(LFH_SIG);
/* 653 */     this.written += 4L;
/*     */     
/*     */ 
/* 656 */     int zipMethod = ze.getMethod();
/*     */     
/* 658 */     writeVersionNeededToExtractAndGeneralPurposeBits(zipMethod, (!encodable) && (this.fallbackToUTF8));
/*     */     
/*     */ 
/* 661 */     this.written += 4L;
/*     */     
/*     */ 
/* 664 */     writeOut(ZipShort.getBytes(zipMethod));
/* 665 */     this.written += 2L;
/*     */     
/*     */ 
/* 668 */     writeOut(ZipUtil.toDosTime(ze.getTime()));
/* 669 */     this.written += 4L;
/*     */     
/*     */ 
/*     */ 
/*     */ 
/* 674 */     this.localDataStart = this.written;
/* 675 */     if ((zipMethod == 8) || (this.raf != null)) {
/* 676 */       writeOut(LZERO);
/* 677 */       writeOut(LZERO);
/* 678 */       writeOut(LZERO);
/*     */     } else {
/* 680 */       writeOut(ZipLong.getBytes(ze.getCrc()));
/* 681 */       writeOut(ZipLong.getBytes(ze.getSize()));
/* 682 */       writeOut(ZipLong.getBytes(ze.getSize()));
/*     */     }
/*     */     
/* 685 */     this.written += 12L;
/*     */     
/*     */ 
/*     */ 
/* 689 */     writeOut(ZipShort.getBytes(name.limit()));
/* 690 */     this.written += 2L;
/*     */     
/*     */ 
/* 693 */     byte[] extra = ze.getLocalFileDataExtra();
/* 694 */     writeOut(ZipShort.getBytes(extra.length));
/* 695 */     this.written += 2L;
/*     */     
/*     */ 
/* 698 */     writeOut(name.array(), name.arrayOffset(), name.limit());
/* 699 */     this.written += name.limit();
/*     */     
/*     */ 
/* 702 */     writeOut(extra);
/* 703 */     this.written += extra.length;
/*     */     
/* 705 */     this.dataStart = this.written;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   protected void writeDataDescriptor(ZipArchiveEntry ze)
/*     */     throws IOException
/*     */   {
/* 714 */     if ((ze.getMethod() != 8) || (this.raf != null)) {
/* 715 */       return;
/*     */     }
/* 717 */     writeOut(DD_SIG);
/* 718 */     writeOut(ZipLong.getBytes(this.entry.getCrc()));
/* 719 */     writeOut(ZipLong.getBytes(this.entry.getCompressedSize()));
/* 720 */     writeOut(ZipLong.getBytes(this.entry.getSize()));
/*     */     
/* 722 */     this.written += 16L;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   protected void writeCentralFileHeader(ZipArchiveEntry ze)
/*     */     throws IOException
/*     */   {
/* 732 */     writeOut(CFH_SIG);
/* 733 */     this.written += 4L;
/*     */     
/*     */ 
/*     */ 
/* 737 */     writeOut(ZipShort.getBytes(ze.getPlatform() << 8 | 0x14));
/* 738 */     this.written += 2L;
/*     */     
/* 740 */     int zipMethod = ze.getMethod();
/* 741 */     boolean encodable = this.zipEncoding.canEncode(ze.getName());
/* 742 */     writeVersionNeededToExtractAndGeneralPurposeBits(zipMethod, (!encodable) && (this.fallbackToUTF8));
/*     */     
/*     */ 
/* 745 */     this.written += 4L;
/*     */     
/*     */ 
/* 748 */     writeOut(ZipShort.getBytes(zipMethod));
/* 749 */     this.written += 2L;
/*     */     
/*     */ 
/* 752 */     writeOut(ZipUtil.toDosTime(ze.getTime()));
/* 753 */     this.written += 4L;
/*     */     
/*     */ 
/*     */ 
/*     */ 
/* 758 */     writeOut(ZipLong.getBytes(ze.getCrc()));
/* 759 */     writeOut(ZipLong.getBytes(ze.getCompressedSize()));
/* 760 */     writeOut(ZipLong.getBytes(ze.getSize()));
/*     */     
/* 762 */     this.written += 12L;
/*     */     
/*     */     ZipEncoding entryEncoding;
/*     */     
/*     */     ZipEncoding entryEncoding;
/*     */     
/* 768 */     if ((!encodable) && (this.fallbackToUTF8)) {
/* 769 */       entryEncoding = ZipEncodingHelper.UTF8_ZIP_ENCODING;
/*     */     } else {
/* 771 */       entryEncoding = this.zipEncoding;
/*     */     }
/*     */     
/* 774 */     ByteBuffer name = entryEncoding.encode(ze.getName());
/*     */     
/* 776 */     writeOut(ZipShort.getBytes(name.limit()));
/* 777 */     this.written += 2L;
/*     */     
/*     */ 
/* 780 */     byte[] extra = ze.getCentralDirectoryExtra();
/* 781 */     writeOut(ZipShort.getBytes(extra.length));
/* 782 */     this.written += 2L;
/*     */     
/*     */ 
/* 785 */     String comm = ze.getComment();
/* 786 */     if (comm == null) {
/* 787 */       comm = "";
/*     */     }
/*     */     
/* 790 */     ByteBuffer commentB = entryEncoding.encode(comm);
/*     */     
/* 792 */     writeOut(ZipShort.getBytes(commentB.limit()));
/* 793 */     this.written += 2L;
/*     */     
/*     */ 
/* 796 */     writeOut(ZERO);
/* 797 */     this.written += 2L;
/*     */     
/*     */ 
/* 800 */     writeOut(ZipShort.getBytes(ze.getInternalAttributes()));
/* 801 */     this.written += 2L;
/*     */     
/*     */ 
/* 804 */     writeOut(ZipLong.getBytes(ze.getExternalAttributes()));
/* 805 */     this.written += 4L;
/*     */     
/*     */ 
/* 808 */     writeOut((byte[])this.offsets.get(ze));
/* 809 */     this.written += 4L;
/*     */     
/*     */ 
/* 812 */     writeOut(name.array(), name.arrayOffset(), name.limit());
/* 813 */     this.written += name.limit();
/*     */     
/*     */ 
/* 816 */     writeOut(extra);
/* 817 */     this.written += extra.length;
/*     */     
/*     */ 
/* 820 */     writeOut(commentB.array(), commentB.arrayOffset(), commentB.limit());
/* 821 */     this.written += commentB.limit();
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */   protected void writeCentralDirectoryEnd()
/*     */     throws IOException
/*     */   {
/* 829 */     writeOut(EOCD_SIG);
/*     */     
/*     */ 
/* 832 */     writeOut(ZERO);
/* 833 */     writeOut(ZERO);
/*     */     
/*     */ 
/* 836 */     byte[] num = ZipShort.getBytes(this.entries.size());
/* 837 */     writeOut(num);
/* 838 */     writeOut(num);
/*     */     
/*     */ 
/* 841 */     writeOut(ZipLong.getBytes(this.cdLength));
/* 842 */     writeOut(ZipLong.getBytes(this.cdOffset));
/*     */     
/*     */ 
/* 845 */     ByteBuffer data = this.zipEncoding.encode(this.comment);
/* 846 */     writeOut(ZipShort.getBytes(data.limit()));
/* 847 */     writeOut(data.array(), data.arrayOffset(), data.limit());
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   protected final void writeOut(byte[] data)
/*     */     throws IOException
/*     */   {
/* 856 */     writeOut(data, 0, data.length);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   protected final void writeOut(byte[] data, int offset, int length)
/*     */     throws IOException
/*     */   {
/* 868 */     if (this.raf != null) {
/* 869 */       this.raf.write(data, offset, length);
/*     */     } else {
/* 871 */       this.out.write(data, offset, length);
/*     */     }
/*     */   }
/*     */   
/*     */   private void deflateUntilInputIsNeeded() throws IOException {
/* 876 */     while (!this.def.needsInput()) {
/* 877 */       deflate();
/*     */     }
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   private void writeVersionNeededToExtractAndGeneralPurposeBits(int zipMethod, boolean utfFallback)
/*     */     throws IOException
/*     */   {
/* 888 */     int versionNeededToExtract = 10;
/* 889 */     int generalPurposeFlag = (this.useEFS) || (utfFallback) ? 2048 : 0;
/* 890 */     if ((zipMethod == 8) && (this.raf == null))
/*     */     {
/*     */ 
/* 893 */       versionNeededToExtract = 20;
/*     */       
/* 895 */       generalPurposeFlag |= 0x8;
/*     */     }
/*     */     
/*     */ 
/*     */ 
/* 900 */     writeOut(ZipShort.getBytes(versionNeededToExtract));
/*     */     
/* 902 */     writeOut(ZipShort.getBytes(generalPurposeFlag));
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public static final class UnicodeExtraFieldPolicy
/*     */   {
/* 913 */     public static final UnicodeExtraFieldPolicy ALWAYS = new UnicodeExtraFieldPolicy("always");
/*     */     
/*     */ 
/*     */ 
/* 917 */     public static final UnicodeExtraFieldPolicy NEVER = new UnicodeExtraFieldPolicy("never");
/*     */     
/*     */ 
/*     */ 
/*     */ 
/* 922 */     public static final UnicodeExtraFieldPolicy NOT_ENCODEABLE = new UnicodeExtraFieldPolicy("not encodeable");
/*     */     private final String name;
/*     */     
/*     */     private UnicodeExtraFieldPolicy(String n)
/*     */     {
/* 927 */       this.name = n;
/*     */     }
/*     */     
/* 930 */     public String toString() { return this.name; }
/*     */   }
/*     */   
/*     */   public ArchiveEntry createArchiveEntry(File inputFile, String entryName)
/*     */     throws IOException
/*     */   {
/* 936 */     if (this.finished) {
/* 937 */       throw new IOException("Stream has already been finished");
/*     */     }
/* 939 */     return new ZipArchiveEntry(inputFile, entryName);
/*     */   }
/*     */ }


/* Location:              /home/menick/Scrivania/ProcessMining/CNMining/CNMining.jar!/org/apache/commons/compress/archivers/zip/ZipArchiveOutputStream.class
 * Java compiler version: 4 (48.0)
 * JD-Core Version:       0.7.1
 */