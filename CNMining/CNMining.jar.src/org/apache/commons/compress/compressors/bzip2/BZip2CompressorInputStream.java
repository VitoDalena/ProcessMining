/*     */ package org.apache.commons.compress.compressors.bzip2;
/*     */ 
/*     */ import java.io.IOException;
/*     */ import java.io.InputStream;
/*     */ import org.apache.commons.compress.compressors.CompressorInputStream;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class BZip2CompressorInputStream
/*     */   extends CompressorInputStream
/*     */   implements BZip2Constants
/*     */ {
/*     */   private int last;
/*     */   private int origPtr;
/*     */   private int blockSize100k;
/*     */   private boolean blockRandomised;
/*     */   private int bsBuff;
/*     */   private int bsLive;
/*  60 */   private final CRC crc = new CRC();
/*     */   
/*     */   private int nInUse;
/*     */   
/*     */   private InputStream in;
/*     */   
/*  66 */   private int currentChar = -1;
/*     */   
/*     */   private static final int EOF = 0;
/*     */   
/*     */   private static final int START_BLOCK_STATE = 1;
/*     */   private static final int RAND_PART_A_STATE = 2;
/*     */   private static final int RAND_PART_B_STATE = 3;
/*     */   private static final int RAND_PART_C_STATE = 4;
/*     */   private static final int NO_RAND_PART_A_STATE = 5;
/*     */   private static final int NO_RAND_PART_B_STATE = 6;
/*     */   private static final int NO_RAND_PART_C_STATE = 7;
/*  77 */   private int currentState = 1;
/*     */   
/*     */   private int storedBlockCRC;
/*     */   
/*     */   private int storedCombinedCRC;
/*     */   
/*     */   private int computedBlockCRC;
/*     */   
/*     */   private int computedCombinedCRC;
/*     */   
/*     */   private int su_count;
/*     */   
/*     */   private int su_ch2;
/*     */   
/*     */   private int su_chPrev;
/*     */   
/*     */   private int su_i2;
/*     */   
/*     */   private int su_j2;
/*     */   
/*     */   private int su_rNToGo;
/*     */   
/*     */   private int su_rTPos;
/*     */   
/*     */   private int su_tPos;
/*     */   
/*     */   private char su_z;
/*     */   
/*     */   private Data data;
/*     */   
/*     */ 
/*     */   public BZip2CompressorInputStream(InputStream in)
/*     */     throws IOException
/*     */   {
/* 111 */     this.in = in;
/* 112 */     init();
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   public int read()
/*     */     throws IOException
/*     */   {
/* 121 */     if (this.in != null) {
/* 122 */       return read0();
/*     */     }
/* 124 */     throw new IOException("stream closed");
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public int read(byte[] dest, int offs, int len)
/*     */     throws IOException
/*     */   {
/* 135 */     if (offs < 0) {
/* 136 */       throw new IndexOutOfBoundsException("offs(" + offs + ") < 0.");
/*     */     }
/* 138 */     if (len < 0) {
/* 139 */       throw new IndexOutOfBoundsException("len(" + len + ") < 0.");
/*     */     }
/* 141 */     if (offs + len > dest.length) {
/* 142 */       throw new IndexOutOfBoundsException("offs(" + offs + ") + len(" + len + ") > dest.length(" + dest.length + ").");
/*     */     }
/*     */     
/* 145 */     if (this.in == null) {
/* 146 */       throw new IOException("stream closed");
/*     */     }
/*     */     
/* 149 */     int hi = offs + len;
/* 150 */     int destOffs = offs;
/* 151 */     int b; while ((destOffs < hi) && ((b = read0()) >= 0)) {
/* 152 */       dest[(destOffs++)] = ((byte)b);
/*     */     }
/*     */     
/* 155 */     return destOffs == offs ? -1 : destOffs - offs;
/*     */   }
/*     */   
/*     */   private void makeMaps() {
/* 159 */     boolean[] inUse = this.data.inUse;
/* 160 */     byte[] seqToUnseq = this.data.seqToUnseq;
/*     */     
/* 162 */     int nInUseShadow = 0;
/*     */     
/* 164 */     for (int i = 0; i < 256; i++) {
/* 165 */       if (inUse[i] != 0) {
/* 166 */         seqToUnseq[(nInUseShadow++)] = ((byte)i);
/*     */       }
/*     */     }
/* 169 */     this.nInUse = nInUseShadow;
/*     */   }
/*     */   
/*     */   private int read0() throws IOException {
/* 173 */     int retChar = this.currentChar;
/*     */     
/* 175 */     switch (this.currentState) {
/*     */     case 0: 
/* 177 */       return -1;
/*     */     
/*     */     case 1: 
/* 180 */       throw new IllegalStateException();
/*     */     
/*     */     case 2: 
/* 183 */       throw new IllegalStateException();
/*     */     
/*     */     case 3: 
/* 186 */       setupRandPartB();
/* 187 */       break;
/*     */     
/*     */     case 4: 
/* 190 */       setupRandPartC();
/* 191 */       break;
/*     */     
/*     */     case 5: 
/* 194 */       throw new IllegalStateException();
/*     */     
/*     */     case 6: 
/* 197 */       setupNoRandPartB();
/* 198 */       break;
/*     */     
/*     */     case 7: 
/* 201 */       setupNoRandPartC();
/* 202 */       break;
/*     */     
/*     */     default: 
/* 205 */       throw new IllegalStateException();
/*     */     }
/*     */     
/* 208 */     return retChar;
/*     */   }
/*     */   
/*     */   private void init() throws IOException {
/* 212 */     if (null == this.in) {
/* 213 */       throw new IOException("No InputStream");
/*     */     }
/* 215 */     if (this.in.available() == 0) {
/* 216 */       throw new IOException("Empty InputStream");
/*     */     }
/* 218 */     checkMagicChar('B', "first");
/* 219 */     checkMagicChar('Z', "second");
/* 220 */     checkMagicChar('h', "third");
/*     */     
/* 222 */     int blockSize = this.in.read();
/* 223 */     if ((blockSize < 49) || (blockSize > 57)) {
/* 224 */       throw new IOException("Stream is not BZip2 formatted: illegal blocksize " + (char)blockSize);
/*     */     }
/*     */     
/*     */ 
/* 228 */     this.blockSize100k = (blockSize - 48);
/*     */     
/* 230 */     initBlock();
/* 231 */     setupBlock();
/*     */   }
/*     */   
/*     */   private void checkMagicChar(char expected, String position) throws IOException
/*     */   {
/* 236 */     int magic = this.in.read();
/* 237 */     if (magic != expected) {
/* 238 */       throw new IOException("Stream is not BZip2 formatted: expected '" + expected + "' as " + position + " byte but got '" + (char)magic + "'");
/*     */     }
/*     */   }
/*     */   
/*     */   private void initBlock()
/*     */     throws IOException
/*     */   {
/* 245 */     char magic0 = bsGetUByte();
/* 246 */     char magic1 = bsGetUByte();
/* 247 */     char magic2 = bsGetUByte();
/* 248 */     char magic3 = bsGetUByte();
/* 249 */     char magic4 = bsGetUByte();
/* 250 */     char magic5 = bsGetUByte();
/*     */     
/* 252 */     if ((magic0 == '\027') && (magic1 == 'r') && (magic2 == 'E') && (magic3 == '8') && (magic4 == 'P') && (magic5 == ''))
/*     */     {
/* 254 */       complete();
/* 255 */     } else { if ((magic0 != '1') || (magic1 != 'A') || (magic2 != 'Y') || (magic3 != '&') || (magic4 != 'S') || (magic5 != 'Y'))
/*     */       {
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/* 262 */         this.currentState = 0;
/* 263 */         throw new IOException("bad block header");
/*     */       }
/* 265 */       this.storedBlockCRC = bsGetInt();
/* 266 */       this.blockRandomised = (bsR(1) == 1);
/*     */       
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/* 272 */       if (this.data == null) {
/* 273 */         this.data = new Data(this.blockSize100k);
/*     */       }
/*     */       
/*     */ 
/* 277 */       getAndMoveToFrontDecode();
/*     */       
/* 279 */       this.crc.initialiseCRC();
/* 280 */       this.currentState = 1;
/*     */     }
/*     */   }
/*     */   
/*     */   private void endBlock() throws IOException {
/* 285 */     this.computedBlockCRC = this.crc.getFinalCRC();
/*     */     
/*     */ 
/* 288 */     if (this.storedBlockCRC != this.computedBlockCRC)
/*     */     {
/*     */ 
/* 291 */       this.computedCombinedCRC = (this.storedCombinedCRC << 1 | this.storedCombinedCRC >>> 31);
/*     */       
/* 293 */       this.computedCombinedCRC ^= this.storedBlockCRC;
/*     */       
/* 295 */       throw new IOException("BZip2 CRC error");
/*     */     }
/*     */     
/* 298 */     this.computedCombinedCRC = (this.computedCombinedCRC << 1 | this.computedCombinedCRC >>> 31);
/*     */     
/* 300 */     this.computedCombinedCRC ^= this.computedBlockCRC;
/*     */   }
/*     */   
/*     */   private void complete() throws IOException {
/* 304 */     this.storedCombinedCRC = bsGetInt();
/* 305 */     this.currentState = 0;
/* 306 */     this.data = null;
/*     */     
/* 308 */     if (this.storedCombinedCRC != this.computedCombinedCRC) {
/* 309 */       throw new IOException("BZip2 CRC error");
/*     */     }
/*     */   }
/*     */   
/*     */   public void close() throws IOException {
/* 314 */     InputStream inShadow = this.in;
/* 315 */     if (inShadow != null) {
/*     */       try {
/* 317 */         if (inShadow != System.in) {
/* 318 */           inShadow.close();
/*     */         }
/*     */       } finally {
/* 321 */         this.data = null;
/* 322 */         this.in = null;
/*     */       }
/*     */     }
/*     */   }
/*     */   
/*     */   private int bsR(int n) throws IOException {
/* 328 */     int bsLiveShadow = this.bsLive;
/* 329 */     int bsBuffShadow = this.bsBuff;
/*     */     
/* 331 */     if (bsLiveShadow < n) {
/* 332 */       InputStream inShadow = this.in;
/*     */       do {
/* 334 */         int thech = inShadow.read();
/*     */         
/* 336 */         if (thech < 0) {
/* 337 */           throw new IOException("unexpected end of stream");
/*     */         }
/*     */         
/* 340 */         bsBuffShadow = bsBuffShadow << 8 | thech;
/* 341 */         bsLiveShadow += 8;
/* 342 */       } while (bsLiveShadow < n);
/*     */       
/* 344 */       this.bsBuff = bsBuffShadow;
/*     */     }
/*     */     
/* 347 */     this.bsLive = (bsLiveShadow - n);
/* 348 */     return bsBuffShadow >> bsLiveShadow - n & (1 << n) - 1;
/*     */   }
/*     */   
/*     */   private boolean bsGetBit() throws IOException {
/* 352 */     int bsLiveShadow = this.bsLive;
/* 353 */     int bsBuffShadow = this.bsBuff;
/*     */     
/* 355 */     if (bsLiveShadow < 1) {
/* 356 */       int thech = this.in.read();
/*     */       
/* 358 */       if (thech < 0) {
/* 359 */         throw new IOException("unexpected end of stream");
/*     */       }
/*     */       
/* 362 */       bsBuffShadow = bsBuffShadow << 8 | thech;
/* 363 */       bsLiveShadow += 8;
/* 364 */       this.bsBuff = bsBuffShadow;
/*     */     }
/*     */     
/* 367 */     this.bsLive = (bsLiveShadow - 1);
/* 368 */     return (bsBuffShadow >> bsLiveShadow - 1 & 0x1) != 0;
/*     */   }
/*     */   
/*     */   private char bsGetUByte() throws IOException {
/* 372 */     return (char)bsR(8);
/*     */   }
/*     */   
/*     */   private int bsGetInt() throws IOException {
/* 376 */     return ((bsR(8) << 8 | bsR(8)) << 8 | bsR(8)) << 8 | bsR(8);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   private static void hbCreateDecodeTables(int[] limit, int[] base, int[] perm, char[] length, int minLen, int maxLen, int alphaSize)
/*     */   {
/* 385 */     int i = minLen; for (int pp = 0; i <= maxLen; i++) {
/* 386 */       for (int j = 0; j < alphaSize; j++) {
/* 387 */         if (length[j] == i) {
/* 388 */           perm[(pp++)] = j;
/*     */         }
/*     */       }
/*     */     }
/*     */     
/* 393 */     int i = 23; for (;;) { i--; if (i <= 0) break;
/* 394 */       base[i] = 0;
/* 395 */       limit[i] = 0;
/*     */     }
/*     */     
/* 398 */     for (int i = 0; i < alphaSize; i++) {
/* 399 */       base[(length[i] + '\001')] += 1;
/*     */     }
/*     */     
/* 402 */     int i = 1; for (int b = base[0]; i < 23; i++) {
/* 403 */       b += base[i];
/* 404 */       base[i] = b;
/*     */     }
/*     */     
/* 407 */     int i = minLen;int vec = 0; for (int b = base[i]; i <= maxLen; i++) {
/* 408 */       int nb = base[(i + 1)];
/* 409 */       vec += nb - b;
/* 410 */       b = nb;
/* 411 */       limit[i] = (vec - 1);
/* 412 */       vec <<= 1;
/*     */     }
/*     */     
/* 415 */     for (int i = minLen + 1; i <= maxLen; i++) {
/* 416 */       base[i] = ((limit[(i - 1)] + 1 << 1) - base[i]);
/*     */     }
/*     */   }
/*     */   
/*     */   private void recvDecodingTables() throws IOException {
/* 421 */     Data dataShadow = this.data;
/* 422 */     boolean[] inUse = dataShadow.inUse;
/* 423 */     byte[] pos = dataShadow.recvDecodingTables_pos;
/* 424 */     byte[] selector = dataShadow.selector;
/* 425 */     byte[] selectorMtf = dataShadow.selectorMtf;
/*     */     
/* 427 */     int inUse16 = 0;
/*     */     
/*     */ 
/* 430 */     for (int i = 0; i < 16; i++) {
/* 431 */       if (bsGetBit()) {
/* 432 */         inUse16 |= 1 << i;
/*     */       }
/*     */     }
/*     */     
/* 436 */     int i = 256; for (;;) { i--; if (i < 0) break;
/* 437 */       inUse[i] = false;
/*     */     }
/*     */     
/* 440 */     for (int i = 0; i < 16; i++) {
/* 441 */       if ((inUse16 & 1 << i) != 0) {
/* 442 */         int i16 = i << 4;
/* 443 */         for (int j = 0; j < 16; j++) {
/* 444 */           if (bsGetBit()) {
/* 445 */             inUse[(i16 + j)] = true;
/*     */           }
/*     */         }
/*     */       }
/*     */     }
/*     */     
/* 451 */     makeMaps();
/* 452 */     int alphaSize = this.nInUse + 2;
/*     */     
/*     */ 
/* 455 */     int nGroups = bsR(3);
/* 456 */     int nSelectors = bsR(15);
/*     */     
/* 458 */     for (int i = 0; i < nSelectors; i++) {
/* 459 */       int j = 0;
/* 460 */       while (bsGetBit()) {
/* 461 */         j++;
/*     */       }
/* 463 */       selectorMtf[i] = ((byte)j);
/*     */     }
/*     */     
/*     */ 
/* 467 */     int v = nGroups; for (;;) { v--; if (v < 0) break;
/* 468 */       pos[v] = ((byte)v);
/*     */     }
/*     */     
/* 471 */     for (int i = 0; i < nSelectors; i++) {
/* 472 */       int v = selectorMtf[i] & 0xFF;
/* 473 */       byte tmp = pos[v];
/* 474 */       while (v > 0)
/*     */       {
/* 476 */         pos[v] = pos[(v - 1)];
/* 477 */         v--;
/*     */       }
/* 479 */       pos[0] = tmp;
/* 480 */       selector[i] = tmp;
/*     */     }
/*     */     
/* 483 */     char[][] len = dataShadow.temp_charArray2d;
/*     */     
/*     */ 
/* 486 */     for (int t = 0; t < nGroups; t++) {
/* 487 */       int curr = bsR(5);
/* 488 */       char[] len_t = len[t];
/* 489 */       for (int i = 0; i < alphaSize; i++) {
/* 490 */         while (bsGetBit()) {
/* 491 */           curr += (bsGetBit() ? -1 : 1);
/*     */         }
/* 493 */         len_t[i] = ((char)curr);
/*     */       }
/*     */     }
/*     */     
/*     */ 
/* 498 */     createHuffmanDecodingTables(alphaSize, nGroups);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   private void createHuffmanDecodingTables(int alphaSize, int nGroups)
/*     */   {
/* 506 */     Data dataShadow = this.data;
/* 507 */     char[][] len = dataShadow.temp_charArray2d;
/* 508 */     int[] minLens = dataShadow.minLens;
/* 509 */     int[][] limit = dataShadow.limit;
/* 510 */     int[][] base = dataShadow.base;
/* 511 */     int[][] perm = dataShadow.perm;
/*     */     
/* 513 */     for (int t = 0; t < nGroups; t++) {
/* 514 */       int minLen = 32;
/* 515 */       int maxLen = 0;
/* 516 */       char[] len_t = len[t];
/* 517 */       int i = alphaSize; for (;;) { i--; if (i < 0) break;
/* 518 */         char lent = len_t[i];
/* 519 */         if (lent > maxLen) {
/* 520 */           maxLen = lent;
/*     */         }
/* 522 */         if (lent < minLen) {
/* 523 */           minLen = lent;
/*     */         }
/*     */       }
/* 526 */       hbCreateDecodeTables(limit[t], base[t], perm[t], len[t], minLen, maxLen, alphaSize);
/*     */       
/* 528 */       minLens[t] = minLen;
/*     */     }
/*     */   }
/*     */   
/*     */   private void getAndMoveToFrontDecode() throws IOException {
/* 533 */     this.origPtr = bsR(24);
/* 534 */     recvDecodingTables();
/*     */     
/* 536 */     InputStream inShadow = this.in;
/* 537 */     Data dataShadow = this.data;
/* 538 */     byte[] ll8 = dataShadow.ll8;
/* 539 */     int[] unzftab = dataShadow.unzftab;
/* 540 */     byte[] selector = dataShadow.selector;
/* 541 */     byte[] seqToUnseq = dataShadow.seqToUnseq;
/* 542 */     char[] yy = dataShadow.getAndMoveToFrontDecode_yy;
/* 543 */     int[] minLens = dataShadow.minLens;
/* 544 */     int[][] limit = dataShadow.limit;
/* 545 */     int[][] base = dataShadow.base;
/* 546 */     int[][] perm = dataShadow.perm;
/* 547 */     int limitLast = this.blockSize100k * 100000;
/*     */     
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/* 554 */     int i = 256; for (;;) { i--; if (i < 0) break;
/* 555 */       yy[i] = ((char)i);
/* 556 */       unzftab[i] = 0;
/*     */     }
/*     */     
/* 559 */     int groupNo = 0;
/* 560 */     int groupPos = 49;
/* 561 */     int eob = this.nInUse + 1;
/* 562 */     int nextSym = getAndMoveToFrontDecode0(0);
/* 563 */     int bsBuffShadow = this.bsBuff;
/* 564 */     int bsLiveShadow = this.bsLive;
/* 565 */     int lastShadow = -1;
/* 566 */     int zt = selector[groupNo] & 0xFF;
/* 567 */     int[] base_zt = base[zt];
/* 568 */     int[] limit_zt = limit[zt];
/* 569 */     int[] perm_zt = perm[zt];
/* 570 */     int minLens_zt = minLens[zt];
/*     */     
/* 572 */     while (nextSym != eob) {
/* 573 */       if ((nextSym == 0) || (nextSym == 1)) {
/* 574 */         int s = -1;
/*     */         
/* 576 */         for (int n = 1;; n <<= 1) {
/* 577 */           if (nextSym == 0) {
/* 578 */             s += n;
/* 579 */           } else { if (nextSym != 1) break;
/* 580 */             s += (n << 1);
/*     */           }
/*     */           
/*     */ 
/*     */ 
/* 585 */           if (groupPos == 0) {
/* 586 */             groupPos = 49;
/* 587 */             zt = selector[(++groupNo)] & 0xFF;
/* 588 */             base_zt = base[zt];
/* 589 */             limit_zt = limit[zt];
/* 590 */             perm_zt = perm[zt];
/* 591 */             minLens_zt = minLens[zt];
/*     */           } else {
/* 593 */             groupPos--;
/*     */           }
/*     */           
/* 596 */           int zn = minLens_zt;
/*     */           
/*     */ 
/*     */ 
/* 600 */           while (bsLiveShadow < zn) {
/* 601 */             int thech = inShadow.read();
/* 602 */             if (thech >= 0) {
/* 603 */               bsBuffShadow = bsBuffShadow << 8 | thech;
/* 604 */               bsLiveShadow += 8;
/*     */             }
/*     */             else {
/* 607 */               throw new IOException("unexpected end of stream");
/*     */             }
/*     */           }
/* 610 */           int zvec = bsBuffShadow >> bsLiveShadow - zn & (1 << zn) - 1;
/*     */           
/* 612 */           bsLiveShadow -= zn;
/*     */           
/* 614 */           while (zvec > limit_zt[zn]) {
/* 615 */             zn++;
/* 616 */             while (bsLiveShadow < 1) {
/* 617 */               int thech = inShadow.read();
/* 618 */               if (thech >= 0) {
/* 619 */                 bsBuffShadow = bsBuffShadow << 8 | thech;
/* 620 */                 bsLiveShadow += 8;
/*     */               }
/*     */               else {
/* 623 */                 throw new IOException("unexpected end of stream");
/*     */               }
/*     */             }
/*     */             
/* 627 */             bsLiveShadow--;
/* 628 */             zvec = zvec << 1 | bsBuffShadow >> bsLiveShadow & 0x1;
/*     */           }
/*     */           
/* 631 */           nextSym = perm_zt[(zvec - base_zt[zn])];
/*     */         }
/*     */         
/* 634 */         byte ch = seqToUnseq[yy[0]];
/* 635 */         unzftab[(ch & 0xFF)] += s + 1;
/*     */         
/* 637 */         while (s-- >= 0) {
/* 638 */           ll8[(++lastShadow)] = ch;
/*     */         }
/*     */         
/* 641 */         if (lastShadow >= limitLast) {
/* 642 */           throw new IOException("block overrun");
/*     */         }
/*     */       } else {
/* 645 */         lastShadow++; if (lastShadow >= limitLast) {
/* 646 */           throw new IOException("block overrun");
/*     */         }
/*     */         
/* 649 */         char tmp = yy[(nextSym - 1)];
/* 650 */         unzftab[(seqToUnseq[tmp] & 0xFF)] += 1;
/* 651 */         ll8[lastShadow] = seqToUnseq[tmp];
/*     */         
/*     */ 
/*     */ 
/*     */         int j;
/*     */         
/*     */ 
/* 658 */         if (nextSym <= 16) {
/* 659 */           for (j = nextSym - 1; j > 0;) {
/* 660 */             yy[j] = yy[(--j)];
/*     */           }
/*     */         } else {
/* 663 */           System.arraycopy(yy, 0, yy, 1, nextSym - 1);
/*     */         }
/*     */         
/* 666 */         yy[0] = tmp;
/*     */         
/* 668 */         if (groupPos == 0) {
/* 669 */           groupPos = 49;
/* 670 */           zt = selector[(++groupNo)] & 0xFF;
/* 671 */           base_zt = base[zt];
/* 672 */           limit_zt = limit[zt];
/* 673 */           perm_zt = perm[zt];
/* 674 */           minLens_zt = minLens[zt];
/*     */         } else {
/* 676 */           groupPos--;
/*     */         }
/*     */         
/* 679 */         int zn = minLens_zt;
/*     */         
/*     */ 
/*     */ 
/* 683 */         while (bsLiveShadow < zn) {
/* 684 */           int thech = inShadow.read();
/* 685 */           if (thech >= 0) {
/* 686 */             bsBuffShadow = bsBuffShadow << 8 | thech;
/* 687 */             bsLiveShadow += 8;
/*     */           }
/*     */           else {
/* 690 */             throw new IOException("unexpected end of stream");
/*     */           }
/*     */         }
/* 693 */         int zvec = bsBuffShadow >> bsLiveShadow - zn & (1 << zn) - 1;
/*     */         
/* 695 */         bsLiveShadow -= zn;
/*     */         
/* 697 */         while (zvec > limit_zt[zn]) {
/* 698 */           zn++;
/* 699 */           while (bsLiveShadow < 1) {
/* 700 */             int thech = inShadow.read();
/* 701 */             if (thech >= 0) {
/* 702 */               bsBuffShadow = bsBuffShadow << 8 | thech;
/* 703 */               bsLiveShadow += 8;
/*     */             }
/*     */             else {
/* 706 */               throw new IOException("unexpected end of stream");
/*     */             }
/*     */           }
/* 709 */           bsLiveShadow--;
/* 710 */           zvec = zvec << 1 | bsBuffShadow >> bsLiveShadow & 0x1;
/*     */         }
/* 712 */         nextSym = perm_zt[(zvec - base_zt[zn])];
/*     */       }
/*     */     }
/*     */     
/* 716 */     this.last = lastShadow;
/* 717 */     this.bsLive = bsLiveShadow;
/* 718 */     this.bsBuff = bsBuffShadow;
/*     */   }
/*     */   
/*     */   private int getAndMoveToFrontDecode0(int groupNo) throws IOException {
/* 722 */     InputStream inShadow = this.in;
/* 723 */     Data dataShadow = this.data;
/* 724 */     int zt = dataShadow.selector[groupNo] & 0xFF;
/* 725 */     int[] limit_zt = dataShadow.limit[zt];
/* 726 */     int zn = dataShadow.minLens[zt];
/* 727 */     int zvec = bsR(zn);
/* 728 */     int bsLiveShadow = this.bsLive;
/* 729 */     int bsBuffShadow = this.bsBuff;
/*     */     
/* 731 */     while (zvec > limit_zt[zn]) {
/* 732 */       zn++;
/* 733 */       while (bsLiveShadow < 1) {
/* 734 */         int thech = inShadow.read();
/*     */         
/* 736 */         if (thech >= 0) {
/* 737 */           bsBuffShadow = bsBuffShadow << 8 | thech;
/* 738 */           bsLiveShadow += 8;
/*     */         }
/*     */         else {
/* 741 */           throw new IOException("unexpected end of stream");
/*     */         }
/*     */       }
/* 744 */       bsLiveShadow--;
/* 745 */       zvec = zvec << 1 | bsBuffShadow >> bsLiveShadow & 0x1;
/*     */     }
/*     */     
/* 748 */     this.bsLive = bsLiveShadow;
/* 749 */     this.bsBuff = bsBuffShadow;
/*     */     
/* 751 */     return dataShadow.perm[zt][(zvec - dataShadow.base[zt][zn])];
/*     */   }
/*     */   
/*     */   private void setupBlock() throws IOException {
/* 755 */     if (this.data == null) {
/* 756 */       return;
/*     */     }
/*     */     
/* 759 */     int[] cftab = this.data.cftab;
/* 760 */     int[] tt = this.data.initTT(this.last + 1);
/* 761 */     byte[] ll8 = this.data.ll8;
/* 762 */     cftab[0] = 0;
/* 763 */     System.arraycopy(this.data.unzftab, 0, cftab, 1, 256);
/*     */     
/* 765 */     int i = 1; for (int c = cftab[0]; i <= 256; i++) {
/* 766 */       c += cftab[i];
/* 767 */       cftab[i] = c;
/*     */     }
/*     */     
/* 770 */     int i = 0; for (int lastShadow = this.last; i <= lastShadow; i++) {
/* 771 */       byte tmp121_120 = (ll8[i] & 0xFF); int[] tmp121_112 = cftab; int tmp123_122 = tmp121_112[tmp121_120];tmp121_112[tmp121_120] = (tmp123_122 + 1);tt[tmp123_122] = i;
/*     */     }
/*     */     
/* 774 */     if ((this.origPtr < 0) || (this.origPtr >= tt.length)) {
/* 775 */       throw new IOException("stream corrupted");
/*     */     }
/*     */     
/* 778 */     this.su_tPos = tt[this.origPtr];
/* 779 */     this.su_count = 0;
/* 780 */     this.su_i2 = 0;
/* 781 */     this.su_ch2 = 256;
/*     */     
/* 783 */     if (this.blockRandomised) {
/* 784 */       this.su_rNToGo = 0;
/* 785 */       this.su_rTPos = 0;
/* 786 */       setupRandPartA();
/*     */     } else {
/* 788 */       setupNoRandPartA();
/*     */     }
/*     */   }
/*     */   
/*     */   private void setupRandPartA() throws IOException {
/* 793 */     if (this.su_i2 <= this.last) {
/* 794 */       this.su_chPrev = this.su_ch2;
/* 795 */       int su_ch2Shadow = this.data.ll8[this.su_tPos] & 0xFF;
/* 796 */       this.su_tPos = this.data.tt[this.su_tPos];
/* 797 */       if (this.su_rNToGo == 0) {
/* 798 */         this.su_rNToGo = (Rand.rNums(this.su_rTPos) - 1);
/* 799 */         if (++this.su_rTPos == 512) {
/* 800 */           this.su_rTPos = 0;
/*     */         }
/*     */       } else {
/* 803 */         this.su_rNToGo -= 1;
/*     */       }
/* 805 */       this.su_ch2 = (su_ch2Shadow ^= (this.su_rNToGo == 1 ? 1 : 0));
/* 806 */       this.su_i2 += 1;
/* 807 */       this.currentChar = su_ch2Shadow;
/* 808 */       this.currentState = 3;
/* 809 */       this.crc.updateCRC(su_ch2Shadow);
/*     */     } else {
/* 811 */       endBlock();
/* 812 */       initBlock();
/* 813 */       setupBlock();
/*     */     }
/*     */   }
/*     */   
/*     */   private void setupNoRandPartA() throws IOException {
/* 818 */     if (this.su_i2 <= this.last) {
/* 819 */       this.su_chPrev = this.su_ch2;
/* 820 */       int su_ch2Shadow = this.data.ll8[this.su_tPos] & 0xFF;
/* 821 */       this.su_ch2 = su_ch2Shadow;
/* 822 */       this.su_tPos = this.data.tt[this.su_tPos];
/* 823 */       this.su_i2 += 1;
/* 824 */       this.currentChar = su_ch2Shadow;
/* 825 */       this.currentState = 6;
/* 826 */       this.crc.updateCRC(su_ch2Shadow);
/*     */     } else {
/* 828 */       this.currentState = 5;
/* 829 */       endBlock();
/* 830 */       initBlock();
/* 831 */       setupBlock();
/*     */     }
/*     */   }
/*     */   
/*     */   private void setupRandPartB() throws IOException {
/* 836 */     if (this.su_ch2 != this.su_chPrev) {
/* 837 */       this.currentState = 2;
/* 838 */       this.su_count = 1;
/* 839 */       setupRandPartA();
/* 840 */     } else if (++this.su_count >= 4) {
/* 841 */       this.su_z = ((char)(this.data.ll8[this.su_tPos] & 0xFF));
/* 842 */       this.su_tPos = this.data.tt[this.su_tPos];
/* 843 */       if (this.su_rNToGo == 0) {
/* 844 */         this.su_rNToGo = (Rand.rNums(this.su_rTPos) - 1);
/* 845 */         if (++this.su_rTPos == 512) {
/* 846 */           this.su_rTPos = 0;
/*     */         }
/*     */       } else {
/* 849 */         this.su_rNToGo -= 1;
/*     */       }
/* 851 */       this.su_j2 = 0;
/* 852 */       this.currentState = 4;
/* 853 */       if (this.su_rNToGo == 1) {
/* 854 */         this.su_z = ((char)(this.su_z ^ 0x1));
/*     */       }
/* 856 */       setupRandPartC();
/*     */     } else {
/* 858 */       this.currentState = 2;
/* 859 */       setupRandPartA();
/*     */     }
/*     */   }
/*     */   
/*     */   private void setupRandPartC() throws IOException {
/* 864 */     if (this.su_j2 < this.su_z) {
/* 865 */       this.currentChar = this.su_ch2;
/* 866 */       this.crc.updateCRC(this.su_ch2);
/* 867 */       this.su_j2 += 1;
/*     */     } else {
/* 869 */       this.currentState = 2;
/* 870 */       this.su_i2 += 1;
/* 871 */       this.su_count = 0;
/* 872 */       setupRandPartA();
/*     */     }
/*     */   }
/*     */   
/*     */   private void setupNoRandPartB() throws IOException {
/* 877 */     if (this.su_ch2 != this.su_chPrev) {
/* 878 */       this.su_count = 1;
/* 879 */       setupNoRandPartA();
/* 880 */     } else if (++this.su_count >= 4) {
/* 881 */       this.su_z = ((char)(this.data.ll8[this.su_tPos] & 0xFF));
/* 882 */       this.su_tPos = this.data.tt[this.su_tPos];
/* 883 */       this.su_j2 = 0;
/* 884 */       setupNoRandPartC();
/*     */     } else {
/* 886 */       setupNoRandPartA();
/*     */     }
/*     */   }
/*     */   
/*     */   private void setupNoRandPartC() throws IOException {
/* 891 */     if (this.su_j2 < this.su_z) {
/* 892 */       int su_ch2Shadow = this.su_ch2;
/* 893 */       this.currentChar = su_ch2Shadow;
/* 894 */       this.crc.updateCRC(su_ch2Shadow);
/* 895 */       this.su_j2 += 1;
/* 896 */       this.currentState = 7;
/*     */     } else {
/* 898 */       this.su_i2 += 1;
/* 899 */       this.su_count = 0;
/* 900 */       setupNoRandPartA();
/*     */     }
/*     */   }
/*     */   
/*     */ 
/*     */   private static final class Data
/*     */   {
/* 907 */     final boolean[] inUse = new boolean['Ā'];
/*     */     
/* 909 */     final byte[] seqToUnseq = new byte['Ā'];
/* 910 */     final byte[] selector = new byte['䙒'];
/* 911 */     final byte[] selectorMtf = new byte['䙒'];
/*     */     
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/* 917 */     final int[] unzftab = new int['Ā'];
/*     */     
/* 919 */     final int[][] limit = new int[6]['Ă'];
/* 920 */     final int[][] base = new int[6]['Ă'];
/* 921 */     final int[][] perm = new int[6]['Ă'];
/* 922 */     final int[] minLens = new int[6];
/*     */     
/* 924 */     final int[] cftab = new int['ā'];
/* 925 */     final char[] getAndMoveToFrontDecode_yy = new char['Ā'];
/* 926 */     final char[][] temp_charArray2d = new char[6]['Ă'];
/*     */     
/* 928 */     final byte[] recvDecodingTables_pos = new byte[6];
/*     */     
/*     */ 
/*     */ 
/*     */     int[] tt;
/*     */     
/*     */ 
/*     */ 
/*     */     byte[] ll8;
/*     */     
/*     */ 
/*     */ 
/*     */     Data(int blockSize100k)
/*     */     {
/* 942 */       this.ll8 = new byte[blockSize100k * 100000];
/*     */     }
/*     */     
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     final int[] initTT(int length)
/*     */     {
/* 953 */       int[] ttShadow = this.tt;
/*     */       
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/* 959 */       if ((ttShadow == null) || (ttShadow.length < length)) {
/* 960 */         this.tt = (ttShadow = new int[length]);
/*     */       }
/*     */       
/* 963 */       return ttShadow;
/*     */     }
/*     */   }
/*     */ }


/* Location:              /home/menick/Scrivania/ProcessMining/CNMining/CNMining.jar!/org/apache/commons/compress/compressors/bzip2/BZip2CompressorInputStream.class
 * Java compiler version: 4 (48.0)
 * JD-Core Version:       0.7.1
 */