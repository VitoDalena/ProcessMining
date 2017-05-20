/*     */ package org.apache.commons.compress.archivers.zip;
/*     */ 
/*     */ import java.io.File;
/*     */ import java.io.IOException;
/*     */ import java.io.InputStream;
/*     */ import java.io.RandomAccessFile;
/*     */ import java.util.Collections;
/*     */ import java.util.Enumeration;
/*     */ import java.util.HashMap;
/*     */ import java.util.Map;
/*     */ import java.util.zip.Inflater;
/*     */ import java.util.zip.InflaterInputStream;
/*     */ import java.util.zip.ZipException;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class ZipFile
/*     */ {
/*     */   private static final int HASH_SIZE = 509;
/*     */   private static final int SHORT = 2;
/*     */   private static final int WORD = 4;
/*     */   static final int NIBLET_MASK = 15;
/*     */   static final int BYTE_SHIFT = 8;
/*     */   private static final int POS_0 = 0;
/*     */   private static final int POS_1 = 1;
/*     */   private static final int POS_2 = 2;
/*     */   private static final int POS_3 = 3;
/*  75 */   private final Map entries = new HashMap(509);
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*  80 */   private final Map nameMap = new HashMap(509);
/*     */   private static final class OffsetEntry { private OffsetEntry() {}
/*  82 */     OffsetEntry(ZipFile.1 x0) { this(); }
/*  83 */     private long headerOffset = -1L;
/*  84 */     private long dataOffset = -1L;
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
/*     */   private final String encoding;
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   private final ZipEncoding zipEncoding;
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public ZipFile(File f)
/*     */     throws IOException
/*     */   {
/* 119 */     this(f, "UTF8");
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public ZipFile(String name)
/*     */     throws IOException
/*     */   {
/* 130 */     this(new File(name), "UTF8");
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
/*     */   public ZipFile(String name, String encoding)
/*     */     throws IOException
/*     */   {
/* 144 */     this(new File(name), encoding, true);
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
/*     */   public ZipFile(File f, String encoding)
/*     */     throws IOException
/*     */   {
/* 158 */     this(f, encoding, true);
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
/*     */   public ZipFile(File f, String encoding, boolean useUnicodeExtraFields)
/*     */     throws IOException
/*     */   {
/* 175 */     this.encoding = encoding;
/* 176 */     this.zipEncoding = ZipEncodingHelper.getZipEncoding(encoding);
/* 177 */     this.useUnicodeExtraFields = useUnicodeExtraFields;
/* 178 */     this.archive = new RandomAccessFile(f, "r");
/* 179 */     boolean success = false;
/*     */     try {
/* 181 */       Map entriesWithoutEFS = populateFromCentralDirectory();
/* 182 */       resolveLocalFileHeaderData(entriesWithoutEFS);
/* 183 */       success = true; return;
/*     */     } finally {
/* 185 */       if (!success) {
/*     */         try {
/* 187 */           this.archive.close();
/*     */         }
/*     */         catch (IOException e2) {}
/*     */       }
/*     */     }
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public String getEncoding()
/*     */   {
/* 201 */     return this.encoding;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */   public void close()
/*     */     throws IOException
/*     */   {
/* 209 */     this.archive.close();
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public static void closeQuietly(ZipFile zipfile)
/*     */   {
/* 218 */     if (zipfile != null) {
/*     */       try {
/* 220 */         zipfile.close();
/*     */       }
/*     */       catch (IOException e) {}
/*     */     }
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public Enumeration getEntries()
/*     */   {
/* 232 */     return Collections.enumeration(this.entries.keySet());
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public ZipArchiveEntry getEntry(String name)
/*     */   {
/* 243 */     return (ZipArchiveEntry)this.nameMap.get(name);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public InputStream getInputStream(ZipArchiveEntry ze)
/*     */     throws IOException, ZipException
/*     */   {
/* 256 */     OffsetEntry offsetEntry = (OffsetEntry)this.entries.get(ze);
/* 257 */     if (offsetEntry == null) {
/* 258 */       return null;
/*     */     }
/* 260 */     long start = offsetEntry.dataOffset;
/* 261 */     BoundedInputStream bis = new BoundedInputStream(start, ze.getCompressedSize());
/*     */     
/* 263 */     switch (ze.getMethod()) {
/*     */     case 0: 
/* 265 */       return bis;
/*     */     case 8: 
/* 267 */       bis.addDummy();
/* 268 */       return new InflaterInputStream(bis, new Inflater(true));
/*     */     }
/* 270 */     throw new ZipException("Found unsupported compression method " + ze.getMethod());
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
/*     */   private final RandomAccessFile archive;
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   private final boolean useUnicodeExtraFields;
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   private Map populateFromCentralDirectory()
/*     */     throws IOException
/*     */   {
/* 307 */     HashMap noEFS = new HashMap();
/*     */     
/* 309 */     positionAtCentralDirectory();
/*     */     
/* 311 */     byte[] cfh = new byte[42];
/*     */     
/* 313 */     byte[] signatureBytes = new byte[4];
/* 314 */     this.archive.readFully(signatureBytes);
/* 315 */     long sig = ZipLong.getValue(signatureBytes);
/* 316 */     long cfhSig = ZipLong.getValue(ZipArchiveOutputStream.CFH_SIG);
/* 317 */     if ((sig != cfhSig) && (startsWithLocalFileHeader())) {
/* 318 */       throw new IOException("central directory is empty, can't expand corrupt archive.");
/*     */     }
/*     */     
/* 321 */     while (sig == cfhSig) {
/* 322 */       this.archive.readFully(cfh);
/* 323 */       int off = 0;
/* 324 */       ZipArchiveEntry ze = new ZipArchiveEntry();
/*     */       
/* 326 */       int versionMadeBy = ZipShort.getValue(cfh, off);
/* 327 */       off += 2;
/* 328 */       ze.setPlatform(versionMadeBy >> 8 & 0xF);
/*     */       
/* 330 */       off += 2;
/*     */       
/* 332 */       int generalPurposeFlag = ZipShort.getValue(cfh, off);
/* 333 */       boolean hasEFS = (generalPurposeFlag & 0x800) != 0;
/*     */       
/* 335 */       ZipEncoding entryEncoding = hasEFS ? ZipEncodingHelper.UTF8_ZIP_ENCODING : this.zipEncoding;
/*     */       
/*     */ 
/* 338 */       off += 2;
/*     */       
/* 340 */       ze.setMethod(ZipShort.getValue(cfh, off));
/* 341 */       off += 2;
/*     */       
/*     */ 
/*     */ 
/*     */ 
/* 346 */       long time = ZipUtil.dosToJavaTime(ZipLong.getValue(cfh, off));
/* 347 */       ze.setTime(time);
/* 348 */       off += 4;
/*     */       
/* 350 */       ze.setCrc(ZipLong.getValue(cfh, off));
/* 351 */       off += 4;
/*     */       
/* 353 */       ze.setCompressedSize(ZipLong.getValue(cfh, off));
/* 354 */       off += 4;
/*     */       
/* 356 */       ze.setSize(ZipLong.getValue(cfh, off));
/* 357 */       off += 4;
/*     */       
/* 359 */       int fileNameLen = ZipShort.getValue(cfh, off);
/* 360 */       off += 2;
/*     */       
/* 362 */       int extraLen = ZipShort.getValue(cfh, off);
/* 363 */       off += 2;
/*     */       
/* 365 */       int commentLen = ZipShort.getValue(cfh, off);
/* 366 */       off += 2;
/*     */       
/* 368 */       off += 2;
/*     */       
/* 370 */       ze.setInternalAttributes(ZipShort.getValue(cfh, off));
/* 371 */       off += 2;
/*     */       
/* 373 */       ze.setExternalAttributes(ZipLong.getValue(cfh, off));
/* 374 */       off += 4;
/*     */       
/* 376 */       byte[] fileName = new byte[fileNameLen];
/* 377 */       this.archive.readFully(fileName);
/* 378 */       ze.setName(entryEncoding.decode(fileName));
/*     */       
/*     */ 
/* 381 */       OffsetEntry offset = new OffsetEntry(null);
/* 382 */       offset.headerOffset = ZipLong.getValue(cfh, off);
/*     */       
/* 384 */       this.entries.put(ze, offset);
/*     */       
/* 386 */       this.nameMap.put(ze.getName(), ze);
/*     */       
/* 388 */       byte[] cdExtraData = new byte[extraLen];
/* 389 */       this.archive.readFully(cdExtraData);
/* 390 */       ze.setCentralDirectoryExtra(cdExtraData);
/*     */       
/* 392 */       byte[] comment = new byte[commentLen];
/* 393 */       this.archive.readFully(comment);
/* 394 */       ze.setComment(entryEncoding.decode(comment));
/*     */       
/* 396 */       this.archive.readFully(signatureBytes);
/* 397 */       sig = ZipLong.getValue(signatureBytes);
/*     */       
/* 399 */       if ((!hasEFS) && (this.useUnicodeExtraFields)) {
/* 400 */         noEFS.put(ze, new NameAndComment(fileName, comment, null));
/*     */       }
/*     */     }
/* 403 */     return noEFS;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   private static final int CFH_LEN = 42;
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   private static final int MIN_EOCD_SIZE = 22;
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   private static final int MAX_EOCD_SIZE = 65557;
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   private static final int CFD_LOCATOR_OFFSET = 16;
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   private static final long LFH_OFFSET_FOR_FILENAME_LENGTH = 26L;
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   private void positionAtCentralDirectory()
/*     */     throws IOException
/*     */   {
/* 442 */     boolean found = false;
/* 443 */     long off = this.archive.length() - 22L;
/* 444 */     long stopSearching = Math.max(0L, this.archive.length() - 65557L);
/* 445 */     if (off >= 0L) {
/* 446 */       this.archive.seek(off);
/* 447 */       byte[] sig = ZipArchiveOutputStream.EOCD_SIG;
/* 448 */       int curr = this.archive.read();
/* 449 */       while ((off >= stopSearching) && (curr != -1)) {
/* 450 */         if (curr == sig[0]) {
/* 451 */           curr = this.archive.read();
/* 452 */           if (curr == sig[1]) {
/* 453 */             curr = this.archive.read();
/* 454 */             if (curr == sig[2]) {
/* 455 */               curr = this.archive.read();
/* 456 */               if (curr == sig[3]) {
/* 457 */                 found = true;
/* 458 */                 break;
/*     */               }
/*     */             }
/*     */           }
/*     */         }
/* 463 */         this.archive.seek(--off);
/* 464 */         curr = this.archive.read();
/*     */       }
/*     */     }
/* 467 */     if (!found) {
/* 468 */       throw new ZipException("archive is not a ZIP archive");
/*     */     }
/* 470 */     this.archive.seek(off + 16L);
/* 471 */     byte[] cfdOffset = new byte[4];
/* 472 */     this.archive.readFully(cfdOffset);
/* 473 */     this.archive.seek(ZipLong.getValue(cfdOffset));
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
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   private void resolveLocalFileHeaderData(Map entriesWithoutEFS)
/*     */     throws IOException
/*     */   {
/* 500 */     Enumeration e = getEntries();
/* 501 */     while (e.hasMoreElements()) {
/* 502 */       ZipArchiveEntry ze = (ZipArchiveEntry)e.nextElement();
/* 503 */       OffsetEntry offsetEntry = (OffsetEntry)this.entries.get(ze);
/* 504 */       long offset = offsetEntry.headerOffset;
/* 505 */       this.archive.seek(offset + 26L);
/* 506 */       byte[] b = new byte[2];
/* 507 */       this.archive.readFully(b);
/* 508 */       int fileNameLen = ZipShort.getValue(b);
/* 509 */       this.archive.readFully(b);
/* 510 */       int extraFieldLen = ZipShort.getValue(b);
/* 511 */       int lenToSkip = fileNameLen;
/* 512 */       while (lenToSkip > 0) {
/* 513 */         int skipped = this.archive.skipBytes(lenToSkip);
/* 514 */         if (skipped <= 0) {
/* 515 */           throw new RuntimeException("failed to skip file name in local file header");
/*     */         }
/*     */         
/* 518 */         lenToSkip -= skipped;
/*     */       }
/* 520 */       byte[] localExtraData = new byte[extraFieldLen];
/* 521 */       this.archive.readFully(localExtraData);
/* 522 */       ze.setExtra(localExtraData);
/*     */       
/*     */ 
/*     */ 
/*     */ 
/* 527 */       offsetEntry.dataOffset = (offset + 26L + 2L + 2L + fileNameLen + extraFieldLen);
/*     */       
/*     */ 
/* 530 */       if (entriesWithoutEFS.containsKey(ze)) {
/* 531 */         String orig = ze.getName();
/* 532 */         NameAndComment nc = (NameAndComment)entriesWithoutEFS.get(ze);
/* 533 */         ZipUtil.setNameAndCommentFromExtraFields(ze, nc.name, nc.comment);
/*     */         
/* 535 */         if (!orig.equals(ze.getName())) {
/* 536 */           this.nameMap.remove(orig);
/* 537 */           this.nameMap.put(ze.getName(), ze);
/*     */         }
/*     */       }
/*     */     }
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */   private boolean startsWithLocalFileHeader()
/*     */     throws IOException
/*     */   {
/* 548 */     this.archive.seek(0L);
/* 549 */     byte[] start = new byte[4];
/* 550 */     this.archive.readFully(start);
/* 551 */     for (int i = 0; i < start.length; i++) {
/* 552 */       if (start[i] != ZipArchiveOutputStream.LFH_SIG[i]) {
/* 553 */         return false;
/*     */       }
/*     */     }
/* 556 */     return true;
/*     */   }
/*     */   
/*     */ 
/*     */   private class BoundedInputStream
/*     */     extends InputStream
/*     */   {
/*     */     private long remaining;
/*     */     
/*     */     private long loc;
/*     */     
/* 567 */     private boolean addDummyByte = false;
/*     */     
/*     */     BoundedInputStream(long start, long remaining) {
/* 570 */       this.remaining = remaining;
/* 571 */       this.loc = start;
/*     */     }
/*     */     
/*     */     public int read() throws IOException {
/* 575 */       if (this.remaining-- <= 0L) {
/* 576 */         if (this.addDummyByte) {
/* 577 */           this.addDummyByte = false;
/* 578 */           return 0;
/*     */         }
/* 580 */         return -1;
/*     */       }
/* 582 */       synchronized (ZipFile.this.archive) {
/* 583 */         ZipFile.this.archive.seek(this.loc++);
/* 584 */         return ZipFile.this.archive.read();
/*     */       }
/*     */     }
/*     */     
/*     */     public int read(byte[] b, int off, int len) throws IOException {
/* 589 */       if (this.remaining <= 0L) {
/* 590 */         if (this.addDummyByte) {
/* 591 */           this.addDummyByte = false;
/* 592 */           b[off] = 0;
/* 593 */           return 1;
/*     */         }
/* 595 */         return -1;
/*     */       }
/*     */       
/* 598 */       if (len <= 0) {
/* 599 */         return 0;
/*     */       }
/*     */       
/* 602 */       if (len > this.remaining) {
/* 603 */         len = (int)this.remaining;
/*     */       }
/* 605 */       int ret = -1;
/* 606 */       synchronized (ZipFile.this.archive) {
/* 607 */         ZipFile.this.archive.seek(this.loc);
/* 608 */         ret = ZipFile.this.archive.read(b, off, len);
/*     */       }
/* 610 */       if (ret > 0) {
/* 611 */         this.loc += ret;
/* 612 */         this.remaining -= ret;
/*     */       }
/* 614 */       return ret;
/*     */     }
/*     */     
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/* 622 */     void addDummy() { this.addDummyByte = true; } }
/*     */   
/*     */   private static final class NameAndComment { private final byte[] name;
/*     */     
/* 626 */     NameAndComment(byte[] x0, byte[] x1, ZipFile.1 x2) { this(x0, x1); }
/*     */     
/*     */     private final byte[] comment;
/*     */     private NameAndComment(byte[] name, byte[] comment) {
/* 630 */       this.name = name;
/* 631 */       this.comment = comment;
/*     */     }
/*     */   }
/*     */ }


/* Location:              /home/menick/Scrivania/ProcessMining/CNMining/CNMining.jar!/org/apache/commons/compress/archivers/zip/ZipFile.class
 * Java compiler version: 4 (48.0)
 * JD-Core Version:       0.7.1
 */