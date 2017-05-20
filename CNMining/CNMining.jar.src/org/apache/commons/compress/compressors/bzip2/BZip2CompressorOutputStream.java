/*      */ package org.apache.commons.compress.compressors.bzip2;
/*      */ 
/*      */ import java.io.IOException;
/*      */ import java.io.OutputStream;
/*      */ import org.apache.commons.compress.compressors.CompressorOutputStream;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ public class BZip2CompressorOutputStream
/*      */   extends CompressorOutputStream
/*      */   implements BZip2Constants
/*      */ {
/*      */   public static final int MIN_BLOCKSIZE = 1;
/*      */   public static final int MAX_BLOCKSIZE = 9;
/*      */   private static final int SETMASK = 2097152;
/*      */   private static final int CLEARMASK = -2097153;
/*      */   private static final int GREATER_ICOST = 15;
/*      */   private static final int LESSER_ICOST = 0;
/*      */   private static final int SMALL_THRESH = 20;
/*      */   private static final int DEPTH_THRESH = 10;
/*      */   private static final int WORK_FACTOR = 30;
/*      */   private static final int QSORT_STACK_SIZE = 1000;
/*  161 */   private static final int[] INCS = { 1, 4, 13, 40, 121, 364, 1093, 3280, 9841, 29524, 88573, 265720, 797161, 2391484 };
/*      */   
/*      */   private int last;
/*      */   private int origPtr;
/*      */   private final int blockSize100k;
/*      */   private boolean blockRandomised;
/*      */   private int bsBuff;
/*      */   private int bsLive;
/*      */   
/*      */   private static void hbMakeCodeLengths(byte[] len, int[] freq, Data dat, int alphaSize, int maxLen)
/*      */   {
/*  172 */     int[] heap = dat.heap;
/*  173 */     int[] weight = dat.weight;
/*  174 */     int[] parent = dat.parent;
/*      */     
/*  176 */     int i = alphaSize; for (;;) { i--; if (i < 0) break;
/*  177 */       weight[(i + 1)] = ((freq[i] == 0 ? 1 : freq[i]) << 8);
/*      */     }
/*      */     
/*  180 */     for (boolean tooLong = true; tooLong;) {
/*  181 */       tooLong = false;
/*      */       
/*  183 */       int nNodes = alphaSize;
/*  184 */       int nHeap = 0;
/*  185 */       heap[0] = 0;
/*  186 */       weight[0] = 0;
/*  187 */       parent[0] = -2;
/*      */       
/*  189 */       for (int i = 1; i <= alphaSize; i++) {
/*  190 */         parent[i] = -1;
/*  191 */         nHeap++;
/*  192 */         heap[nHeap] = i;
/*      */         
/*  194 */         int zz = nHeap;
/*  195 */         int tmp = heap[zz];
/*  196 */         while (weight[tmp] < weight[heap[(zz >> 1)]]) {
/*  197 */           heap[zz] = heap[(zz >> 1)];
/*  198 */           zz >>= 1;
/*      */         }
/*  200 */         heap[zz] = tmp;
/*      */       }
/*      */       
/*  203 */       while (nHeap > 1) {
/*  204 */         int n1 = heap[1];
/*  205 */         heap[1] = heap[nHeap];
/*  206 */         nHeap--;
/*      */         
/*  208 */         int yy = 0;
/*  209 */         int zz = 1;
/*  210 */         int tmp = heap[1];
/*      */         for (;;)
/*      */         {
/*  213 */           yy = zz << 1;
/*      */           
/*  215 */           if (yy > nHeap) {
/*      */             break;
/*      */           }
/*      */           
/*  219 */           if ((yy < nHeap) && (weight[heap[(yy + 1)]] < weight[heap[yy]]))
/*      */           {
/*  221 */             yy++;
/*      */           }
/*      */           
/*  224 */           if (weight[tmp] < weight[heap[yy]]) {
/*      */             break;
/*      */           }
/*      */           
/*  228 */           heap[zz] = heap[yy];
/*  229 */           zz = yy;
/*      */         }
/*      */         
/*  232 */         heap[zz] = tmp;
/*      */         
/*  234 */         int n2 = heap[1];
/*  235 */         heap[1] = heap[nHeap];
/*  236 */         nHeap--;
/*      */         
/*  238 */         yy = 0;
/*  239 */         zz = 1;
/*  240 */         tmp = heap[1];
/*      */         for (;;)
/*      */         {
/*  243 */           yy = zz << 1;
/*      */           
/*  245 */           if (yy > nHeap) {
/*      */             break;
/*      */           }
/*      */           
/*  249 */           if ((yy < nHeap) && (weight[heap[(yy + 1)]] < weight[heap[yy]]))
/*      */           {
/*  251 */             yy++;
/*      */           }
/*      */           
/*  254 */           if (weight[tmp] < weight[heap[yy]]) {
/*      */             break;
/*      */           }
/*      */           
/*  258 */           heap[zz] = heap[yy];
/*  259 */           zz = yy;
/*      */         }
/*      */         
/*  262 */         heap[zz] = tmp;
/*  263 */         nNodes++;
/*  264 */         parent[n1] = (parent[n2] = nNodes);
/*      */         
/*  266 */         int weight_n1 = weight[n1];
/*  267 */         int weight_n2 = weight[n2];
/*  268 */         weight[nNodes] = ((weight_n1 & 0xFF00) + (weight_n2 & 0xFF00) | 1 + ((weight_n1 & 0xFF) > (weight_n2 & 0xFF) ? weight_n1 & 0xFF : weight_n2 & 0xFF));
/*      */         
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*  275 */         parent[nNodes] = -1;
/*  276 */         nHeap++;
/*  277 */         heap[nHeap] = nNodes;
/*      */         
/*  279 */         tmp = 0;
/*  280 */         zz = nHeap;
/*  281 */         tmp = heap[zz];
/*  282 */         int weight_tmp = weight[tmp];
/*  283 */         while (weight_tmp < weight[heap[(zz >> 1)]]) {
/*  284 */           heap[zz] = heap[(zz >> 1)];
/*  285 */           zz >>= 1;
/*      */         }
/*  287 */         heap[zz] = tmp;
/*      */       }
/*      */       
/*      */ 
/*  291 */       for (int i = 1; i <= alphaSize; i++) {
/*  292 */         int j = 0;
/*  293 */         int k = i;
/*      */         int parent_k;
/*  295 */         while ((parent_k = parent[k]) >= 0) {
/*  296 */           k = parent_k;
/*  297 */           j++;
/*      */         }
/*      */         
/*  300 */         len[(i - 1)] = ((byte)j);
/*  301 */         if (j > maxLen) {
/*  302 */           tooLong = true;
/*      */         }
/*      */       }
/*      */       
/*  306 */       if (tooLong) {
/*  307 */         for (int i = 1; i < alphaSize; i++) {
/*  308 */           int j = weight[i] >> 8;
/*  309 */           j = 1 + (j >> 1);
/*  310 */           weight[i] = (j << 8);
/*      */         }
/*      */       }
/*      */     }
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*  336 */   private final CRC crc = new CRC();
/*      */   
/*      */ 
/*      */   private int nInUse;
/*      */   
/*      */ 
/*      */   private int nMTF;
/*      */   
/*      */   private int workDone;
/*      */   
/*      */   private int workLimit;
/*      */   
/*      */   private boolean firstAttempt;
/*      */   
/*  350 */   private int currentChar = -1;
/*  351 */   private int runLength = 0;
/*      */   
/*      */ 
/*      */ 
/*      */   private int blockCRC;
/*      */   
/*      */ 
/*      */ 
/*      */   private int combinedCRC;
/*      */   
/*      */ 
/*      */ 
/*      */   private int allowableBlockSize;
/*      */   
/*      */ 
/*      */ 
/*      */   private Data data;
/*      */   
/*      */ 
/*      */ 
/*      */   private OutputStream out;
/*      */   
/*      */ 
/*      */ 
/*      */   public static int chooseBlockSize(long inputLength)
/*      */   {
/*  377 */     return inputLength > 0L ? (int)Math.min(inputLength / 132000L + 1L, 9L) : 9;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public BZip2CompressorOutputStream(OutputStream out)
/*      */     throws IOException
/*      */   {
/*  394 */     this(out, 9);
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public BZip2CompressorOutputStream(OutputStream out, int blockSize)
/*      */     throws IOException
/*      */   {
/*  420 */     if (blockSize < 1) {
/*  421 */       throw new IllegalArgumentException("blockSize(" + blockSize + ") < 1");
/*      */     }
/*      */     
/*  424 */     if (blockSize > 9) {
/*  425 */       throw new IllegalArgumentException("blockSize(" + blockSize + ") > 9");
/*      */     }
/*      */     
/*      */ 
/*  429 */     this.blockSize100k = blockSize;
/*  430 */     this.out = out;
/*  431 */     init();
/*      */   }
/*      */   
/*      */   public void write(int b) throws IOException {
/*  435 */     if (this.out != null) {
/*  436 */       write0(b);
/*      */     } else {
/*  438 */       throw new IOException("closed");
/*      */     }
/*      */   }
/*      */   
/*      */   private void writeRun() throws IOException {
/*  443 */     int lastShadow = this.last;
/*      */     
/*  445 */     if (lastShadow < this.allowableBlockSize) {
/*  446 */       int currentCharShadow = this.currentChar;
/*  447 */       Data dataShadow = this.data;
/*  448 */       dataShadow.inUse[currentCharShadow] = true;
/*  449 */       byte ch = (byte)currentCharShadow;
/*      */       
/*  451 */       int runLengthShadow = this.runLength;
/*  452 */       this.crc.updateCRC(currentCharShadow, runLengthShadow);
/*      */       
/*  454 */       switch (runLengthShadow) {
/*      */       case 1: 
/*  456 */         dataShadow.block[(lastShadow + 2)] = ch;
/*  457 */         this.last = (lastShadow + 1);
/*  458 */         break;
/*      */       
/*      */       case 2: 
/*  461 */         dataShadow.block[(lastShadow + 2)] = ch;
/*  462 */         dataShadow.block[(lastShadow + 3)] = ch;
/*  463 */         this.last = (lastShadow + 2);
/*  464 */         break;
/*      */       
/*      */       case 3: 
/*  467 */         byte[] block = dataShadow.block;
/*  468 */         block[(lastShadow + 2)] = ch;
/*  469 */         block[(lastShadow + 3)] = ch;
/*  470 */         block[(lastShadow + 4)] = ch;
/*  471 */         this.last = (lastShadow + 3);
/*      */         
/*  473 */         break;
/*      */       
/*      */       default: 
/*  476 */         runLengthShadow -= 4;
/*  477 */         dataShadow.inUse[runLengthShadow] = true;
/*  478 */         byte[] block = dataShadow.block;
/*  479 */         block[(lastShadow + 2)] = ch;
/*  480 */         block[(lastShadow + 3)] = ch;
/*  481 */         block[(lastShadow + 4)] = ch;
/*  482 */         block[(lastShadow + 5)] = ch;
/*  483 */         block[(lastShadow + 6)] = ((byte)runLengthShadow);
/*  484 */         this.last = (lastShadow + 5);
/*      */       }
/*      */       
/*      */     }
/*      */     else
/*      */     {
/*  490 */       endBlock();
/*  491 */       initBlock();
/*  492 */       writeRun();
/*      */     }
/*      */   }
/*      */   
/*      */ 
/*      */   protected void finalize()
/*      */     throws Throwable
/*      */   {
/*  500 */     finish();
/*  501 */     super.finalize();
/*      */   }
/*      */   
/*      */   public void finish() throws IOException
/*      */   {
/*  506 */     if (this.out != null) {
/*      */       try {
/*  508 */         if (this.runLength > 0) {
/*  509 */           writeRun();
/*      */         }
/*  511 */         this.currentChar = -1;
/*  512 */         endBlock();
/*  513 */         endCompression();
/*      */       } finally {
/*  515 */         this.out = null;
/*  516 */         this.data = null;
/*      */       }
/*      */     }
/*      */   }
/*      */   
/*      */   public void close() throws IOException {
/*  522 */     if (this.out != null) {
/*  523 */       OutputStream outShadow = this.out;
/*  524 */       finish();
/*  525 */       outShadow.close();
/*      */     }
/*      */   }
/*      */   
/*      */   public void flush() throws IOException {
/*  530 */     OutputStream outShadow = this.out;
/*  531 */     if (outShadow != null) {
/*  532 */       outShadow.flush();
/*      */     }
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   private void init()
/*      */     throws IOException
/*      */   {
/*  543 */     bsPutUByte(66);
/*  544 */     bsPutUByte(90);
/*      */     
/*  546 */     this.data = new Data(this.blockSize100k);
/*      */     
/*      */ 
/*  549 */     bsPutUByte(104);
/*  550 */     bsPutUByte(48 + this.blockSize100k);
/*      */     
/*  552 */     this.combinedCRC = 0;
/*  553 */     initBlock();
/*      */   }
/*      */   
/*      */   private void initBlock()
/*      */   {
/*  558 */     this.crc.initialiseCRC();
/*  559 */     this.last = -1;
/*      */     
/*      */ 
/*  562 */     boolean[] inUse = this.data.inUse;
/*  563 */     int i = 256; for (;;) { i--; if (i < 0) break;
/*  564 */       inUse[i] = false;
/*      */     }
/*      */     
/*      */ 
/*  568 */     this.allowableBlockSize = (this.blockSize100k * 100000 - 20);
/*      */   }
/*      */   
/*      */   private void endBlock() throws IOException {
/*  572 */     this.blockCRC = this.crc.getFinalCRC();
/*  573 */     this.combinedCRC = (this.combinedCRC << 1 | this.combinedCRC >>> 31);
/*  574 */     this.combinedCRC ^= this.blockCRC;
/*      */     
/*      */ 
/*  577 */     if (this.last == -1) {
/*  578 */       return;
/*      */     }
/*      */     
/*      */ 
/*  582 */     blockSort();
/*      */     
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*  595 */     bsPutUByte(49);
/*  596 */     bsPutUByte(65);
/*  597 */     bsPutUByte(89);
/*  598 */     bsPutUByte(38);
/*  599 */     bsPutUByte(83);
/*  600 */     bsPutUByte(89);
/*      */     
/*      */ 
/*  603 */     bsPutInt(this.blockCRC);
/*      */     
/*      */ 
/*  606 */     if (this.blockRandomised) {
/*  607 */       bsW(1, 1);
/*      */     } else {
/*  609 */       bsW(1, 0);
/*      */     }
/*      */     
/*      */ 
/*  613 */     moveToFrontCodeAndSend();
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   private void endCompression()
/*      */     throws IOException
/*      */   {
/*  623 */     bsPutUByte(23);
/*  624 */     bsPutUByte(114);
/*  625 */     bsPutUByte(69);
/*  626 */     bsPutUByte(56);
/*  627 */     bsPutUByte(80);
/*  628 */     bsPutUByte(144);
/*      */     
/*  630 */     bsPutInt(this.combinedCRC);
/*  631 */     bsFinishedWithStream();
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */   public final int getBlockSize()
/*      */   {
/*  638 */     return this.blockSize100k;
/*      */   }
/*      */   
/*      */   public void write(byte[] buf, int offs, int len) throws IOException
/*      */   {
/*  643 */     if (offs < 0) {
/*  644 */       throw new IndexOutOfBoundsException("offs(" + offs + ") < 0.");
/*      */     }
/*  646 */     if (len < 0) {
/*  647 */       throw new IndexOutOfBoundsException("len(" + len + ") < 0.");
/*      */     }
/*  649 */     if (offs + len > buf.length) {
/*  650 */       throw new IndexOutOfBoundsException("offs(" + offs + ") + len(" + len + ") > buf.length(" + buf.length + ").");
/*      */     }
/*      */     
/*      */ 
/*  654 */     if (this.out == null) {
/*  655 */       throw new IOException("stream closed");
/*      */     }
/*      */     
/*  658 */     for (int hi = offs + len; offs < hi;) {
/*  659 */       write0(buf[(offs++)]);
/*      */     }
/*      */   }
/*      */   
/*      */   private void write0(int b) throws IOException {
/*  664 */     if (this.currentChar != -1) {
/*  665 */       b &= 0xFF;
/*  666 */       if (this.currentChar == b) {
/*  667 */         if (++this.runLength > 254) {
/*  668 */           writeRun();
/*  669 */           this.currentChar = -1;
/*  670 */           this.runLength = 0;
/*      */         }
/*      */       }
/*      */       else {
/*  674 */         writeRun();
/*  675 */         this.runLength = 1;
/*  676 */         this.currentChar = b;
/*      */       }
/*      */     } else {
/*  679 */       this.currentChar = (b & 0xFF);
/*  680 */       this.runLength += 1;
/*      */     }
/*      */   }
/*      */   
/*      */ 
/*      */   private static void hbAssignCodes(int[] code, byte[] length, int minLen, int maxLen, int alphaSize)
/*      */   {
/*  687 */     int vec = 0;
/*  688 */     for (int n = minLen; n <= maxLen; n++) {
/*  689 */       for (int i = 0; i < alphaSize; i++) {
/*  690 */         if ((length[i] & 0xFF) == n) {
/*  691 */           code[i] = vec;
/*  692 */           vec++;
/*      */         }
/*      */       }
/*  695 */       vec <<= 1;
/*      */     }
/*      */   }
/*      */   
/*      */   private void bsFinishedWithStream() throws IOException {
/*  700 */     while (this.bsLive > 0) {
/*  701 */       int ch = this.bsBuff >> 24;
/*  702 */       this.out.write(ch);
/*  703 */       this.bsBuff <<= 8;
/*  704 */       this.bsLive -= 8;
/*      */     }
/*      */   }
/*      */   
/*      */   private void bsW(int n, int v) throws IOException {
/*  709 */     OutputStream outShadow = this.out;
/*  710 */     int bsLiveShadow = this.bsLive;
/*  711 */     int bsBuffShadow = this.bsBuff;
/*      */     
/*  713 */     while (bsLiveShadow >= 8) {
/*  714 */       outShadow.write(bsBuffShadow >> 24);
/*  715 */       bsBuffShadow <<= 8;
/*  716 */       bsLiveShadow -= 8;
/*      */     }
/*      */     
/*  719 */     this.bsBuff = (bsBuffShadow | v << 32 - bsLiveShadow - n);
/*  720 */     this.bsLive = (bsLiveShadow + n);
/*      */   }
/*      */   
/*      */   private void bsPutUByte(int c) throws IOException {
/*  724 */     bsW(8, c);
/*      */   }
/*      */   
/*      */   private void bsPutInt(int u) throws IOException {
/*  728 */     bsW(8, u >> 24 & 0xFF);
/*  729 */     bsW(8, u >> 16 & 0xFF);
/*  730 */     bsW(8, u >> 8 & 0xFF);
/*  731 */     bsW(8, u & 0xFF);
/*      */   }
/*      */   
/*      */   private void sendMTFValues() throws IOException {
/*  735 */     byte[][] len = this.data.sendMTFValues_len;
/*  736 */     int alphaSize = this.nInUse + 2;
/*      */     
/*  738 */     int t = 6; for (;;) { t--; if (t < 0) break;
/*  739 */       byte[] len_t = len[t];
/*  740 */       int v = alphaSize; for (;;) { v--; if (v < 0) break;
/*  741 */         len_t[v] = 15;
/*      */       }
/*      */     }
/*      */     
/*      */ 
/*      */ 
/*  747 */     int nGroups = this.nMTF < 2400 ? 5 : this.nMTF < 1200 ? 4 : this.nMTF < 600 ? 3 : this.nMTF < 200 ? 2 : 6;
/*      */     
/*      */ 
/*      */ 
/*  751 */     sendMTFValues0(nGroups, alphaSize);
/*      */     
/*      */ 
/*      */ 
/*      */ 
/*  756 */     int nSelectors = sendMTFValues1(nGroups, alphaSize);
/*      */     
/*      */ 
/*  759 */     sendMTFValues2(nGroups, nSelectors);
/*      */     
/*      */ 
/*  762 */     sendMTFValues3(nGroups, alphaSize);
/*      */     
/*      */ 
/*  765 */     sendMTFValues4();
/*      */     
/*      */ 
/*  768 */     sendMTFValues5(nGroups, nSelectors);
/*      */     
/*      */ 
/*  771 */     sendMTFValues6(nGroups, alphaSize);
/*      */     
/*      */ 
/*  774 */     sendMTFValues7(nSelectors);
/*      */   }
/*      */   
/*      */   private void sendMTFValues0(int nGroups, int alphaSize) {
/*  778 */     byte[][] len = this.data.sendMTFValues_len;
/*  779 */     int[] mtfFreq = this.data.mtfFreq;
/*      */     
/*  781 */     int remF = this.nMTF;
/*  782 */     int gs = 0;
/*      */     
/*  784 */     for (int nPart = nGroups; nPart > 0; nPart--) {
/*  785 */       int tFreq = remF / nPart;
/*  786 */       int ge = gs - 1;
/*  787 */       int aFreq = 0;
/*      */       
/*  789 */       for (int a = alphaSize - 1; (aFreq < tFreq) && (ge < a);) {
/*  790 */         aFreq += mtfFreq[(++ge)];
/*      */       }
/*      */       
/*  793 */       if ((ge > gs) && (nPart != nGroups) && (nPart != 1) && ((nGroups - nPart & 0x1) != 0))
/*      */       {
/*  795 */         aFreq -= mtfFreq[(ge--)];
/*      */       }
/*      */       
/*  798 */       byte[] len_np = len[(nPart - 1)];
/*  799 */       int v = alphaSize; for (;;) { v--; if (v < 0) break;
/*  800 */         if ((v >= gs) && (v <= ge)) {
/*  801 */           len_np[v] = 0;
/*      */         } else {
/*  803 */           len_np[v] = 15;
/*      */         }
/*      */       }
/*      */       
/*  807 */       gs = ge + 1;
/*  808 */       remF -= aFreq;
/*      */     }
/*      */   }
/*      */   
/*      */   private int sendMTFValues1(int nGroups, int alphaSize) {
/*  813 */     Data dataShadow = this.data;
/*  814 */     int[][] rfreq = dataShadow.sendMTFValues_rfreq;
/*  815 */     int[] fave = dataShadow.sendMTFValues_fave;
/*  816 */     short[] cost = dataShadow.sendMTFValues_cost;
/*  817 */     char[] sfmap = dataShadow.sfmap;
/*  818 */     byte[] selector = dataShadow.selector;
/*  819 */     byte[][] len = dataShadow.sendMTFValues_len;
/*  820 */     byte[] len_0 = len[0];
/*  821 */     byte[] len_1 = len[1];
/*  822 */     byte[] len_2 = len[2];
/*  823 */     byte[] len_3 = len[3];
/*  824 */     byte[] len_4 = len[4];
/*  825 */     byte[] len_5 = len[5];
/*  826 */     int nMTFShadow = this.nMTF;
/*      */     
/*  828 */     int nSelectors = 0;
/*      */     
/*  830 */     for (int iter = 0; iter < 4; iter++) {
/*  831 */       int t = nGroups; for (;;) { t--; if (t < 0) break;
/*  832 */         fave[t] = 0;
/*  833 */         int[] rfreqt = rfreq[t];
/*  834 */         int i = alphaSize; for (;;) { i--; if (i < 0) break;
/*  835 */           rfreqt[i] = 0;
/*      */         }
/*      */       }
/*      */       
/*  839 */       nSelectors = 0;
/*      */       
/*  841 */       for (int gs = 0; gs < this.nMTF;)
/*      */       {
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*  849 */         int ge = Math.min(gs + 50 - 1, nMTFShadow - 1);
/*      */         
/*  851 */         if (nGroups == 6)
/*      */         {
/*      */ 
/*  854 */           short cost0 = 0;
/*  855 */           short cost1 = 0;
/*  856 */           short cost2 = 0;
/*  857 */           short cost3 = 0;
/*  858 */           short cost4 = 0;
/*  859 */           short cost5 = 0;
/*      */           
/*  861 */           for (int i = gs; i <= ge; i++) {
/*  862 */             int icv = sfmap[i];
/*  863 */             cost0 = (short)(cost0 + (len_0[icv] & 0xFF));
/*  864 */             cost1 = (short)(cost1 + (len_1[icv] & 0xFF));
/*  865 */             cost2 = (short)(cost2 + (len_2[icv] & 0xFF));
/*  866 */             cost3 = (short)(cost3 + (len_3[icv] & 0xFF));
/*  867 */             cost4 = (short)(cost4 + (len_4[icv] & 0xFF));
/*  868 */             cost5 = (short)(cost5 + (len_5[icv] & 0xFF));
/*      */           }
/*      */           
/*  871 */           cost[0] = cost0;
/*  872 */           cost[1] = cost1;
/*  873 */           cost[2] = cost2;
/*  874 */           cost[3] = cost3;
/*  875 */           cost[4] = cost4;
/*  876 */           cost[5] = cost5;
/*      */         }
/*      */         else {
/*  879 */           int t = nGroups; for (;;) { t--; if (t < 0) break;
/*  880 */             cost[t] = 0;
/*      */           }
/*      */           
/*  883 */           for (int i = gs; i <= ge; i++) {
/*  884 */             int icv = sfmap[i];
/*  885 */             int t = nGroups; for (;;) { t--; if (t < 0) break;
/*  886 */               int tmp403_401 = t; short[] tmp403_399 = cost;tmp403_399[tmp403_401] = ((short)(tmp403_399[tmp403_401] + (len[t][icv] & 0xFF)));
/*      */             }
/*      */           }
/*      */         }
/*      */         
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*  895 */         int bt = -1;
/*  896 */         int t = nGroups;int bc = 999999999; for (;;) { t--; if (t < 0) break;
/*  897 */           int cost_t = cost[t];
/*  898 */           if (cost_t < bc) {
/*  899 */             bc = cost_t;
/*  900 */             bt = t;
/*      */           }
/*      */         }
/*      */         
/*  904 */         fave[bt] += 1;
/*  905 */         selector[nSelectors] = ((byte)bt);
/*  906 */         nSelectors++;
/*      */         
/*      */ 
/*      */ 
/*      */ 
/*  911 */         int[] rfreq_bt = rfreq[bt];
/*  912 */         for (int i = gs; i <= ge; i++) {
/*  913 */           rfreq_bt[sfmap[i]] += 1;
/*      */         }
/*      */         
/*  916 */         gs = ge + 1;
/*      */       }
/*      */       
/*      */ 
/*      */ 
/*      */ 
/*  922 */       for (int t = 0; t < nGroups; t++) {
/*  923 */         hbMakeCodeLengths(len[t], rfreq[t], this.data, alphaSize, 20);
/*      */       }
/*      */     }
/*      */     
/*  927 */     return nSelectors;
/*      */   }
/*      */   
/*      */ 
/*      */   private void sendMTFValues2(int nGroups, int nSelectors)
/*      */   {
/*  933 */     Data dataShadow = this.data;
/*  934 */     byte[] pos = dataShadow.sendMTFValues2_pos;
/*      */     
/*  936 */     int i = nGroups; for (;;) { i--; if (i < 0) break;
/*  937 */       pos[i] = ((byte)i);
/*      */     }
/*      */     
/*  940 */     for (int i = 0; i < nSelectors; i++) {
/*  941 */       byte ll_i = dataShadow.selector[i];
/*  942 */       byte tmp = pos[0];
/*  943 */       int j = 0;
/*      */       
/*  945 */       while (ll_i != tmp) {
/*  946 */         j++;
/*  947 */         byte tmp2 = tmp;
/*  948 */         tmp = pos[j];
/*  949 */         pos[j] = tmp2;
/*      */       }
/*      */       
/*  952 */       pos[0] = tmp;
/*  953 */       dataShadow.selectorMtf[i] = ((byte)j);
/*      */     }
/*      */   }
/*      */   
/*      */   private void sendMTFValues3(int nGroups, int alphaSize) {
/*  958 */     int[][] code = this.data.sendMTFValues_code;
/*  959 */     byte[][] len = this.data.sendMTFValues_len;
/*      */     
/*  961 */     for (int t = 0; t < nGroups; t++) {
/*  962 */       int minLen = 32;
/*  963 */       int maxLen = 0;
/*  964 */       byte[] len_t = len[t];
/*  965 */       int i = alphaSize; for (;;) { i--; if (i < 0) break;
/*  966 */         int l = len_t[i] & 0xFF;
/*  967 */         if (l > maxLen) {
/*  968 */           maxLen = l;
/*      */         }
/*  970 */         if (l < minLen) {
/*  971 */           minLen = l;
/*      */         }
/*      */       }
/*      */       
/*      */ 
/*      */ 
/*      */ 
/*  978 */       hbAssignCodes(code[t], len[t], minLen, maxLen, alphaSize);
/*      */     }
/*      */   }
/*      */   
/*      */   private void sendMTFValues4() throws IOException {
/*  983 */     boolean[] inUse = this.data.inUse;
/*  984 */     boolean[] inUse16 = this.data.sentMTFValues4_inUse16;
/*      */     
/*  986 */     int i = 16; for (;;) { i--; if (i < 0) break;
/*  987 */       inUse16[i] = false;
/*  988 */       int i16 = i * 16;
/*  989 */       int j = 16; for (;;) { j--; if (j < 0) break;
/*  990 */         if (inUse[(i16 + j)] != 0) {
/*  991 */           inUse16[i] = true;
/*      */         }
/*      */       }
/*      */     }
/*      */     
/*  996 */     for (int i = 0; i < 16; i++) {
/*  997 */       bsW(1, inUse16[i] != 0 ? 1 : 0);
/*      */     }
/*      */     
/* 1000 */     OutputStream outShadow = this.out;
/* 1001 */     int bsLiveShadow = this.bsLive;
/* 1002 */     int bsBuffShadow = this.bsBuff;
/*      */     
/* 1004 */     for (int i = 0; i < 16; i++) {
/* 1005 */       if (inUse16[i] != 0) {
/* 1006 */         int i16 = i * 16;
/* 1007 */         for (int j = 0; j < 16; j++)
/*      */         {
/* 1009 */           while (bsLiveShadow >= 8) {
/* 1010 */             outShadow.write(bsBuffShadow >> 24);
/* 1011 */             bsBuffShadow <<= 8;
/* 1012 */             bsLiveShadow -= 8;
/*      */           }
/* 1014 */           if (inUse[(i16 + j)] != 0) {
/* 1015 */             bsBuffShadow |= 1 << 32 - bsLiveShadow - 1;
/*      */           }
/* 1017 */           bsLiveShadow++;
/*      */         }
/*      */       }
/*      */     }
/*      */     
/* 1022 */     this.bsBuff = bsBuffShadow;
/* 1023 */     this.bsLive = bsLiveShadow;
/*      */   }
/*      */   
/*      */   private void sendMTFValues5(int nGroups, int nSelectors) throws IOException
/*      */   {
/* 1028 */     bsW(3, nGroups);
/* 1029 */     bsW(15, nSelectors);
/*      */     
/* 1031 */     OutputStream outShadow = this.out;
/* 1032 */     byte[] selectorMtf = this.data.selectorMtf;
/*      */     
/* 1034 */     int bsLiveShadow = this.bsLive;
/* 1035 */     int bsBuffShadow = this.bsBuff;
/*      */     
/* 1037 */     for (int i = 0; i < nSelectors; i++) {
/* 1038 */       int j = 0; for (int hj = selectorMtf[i] & 0xFF; j < hj; j++)
/*      */       {
/* 1040 */         while (bsLiveShadow >= 8) {
/* 1041 */           outShadow.write(bsBuffShadow >> 24);
/* 1042 */           bsBuffShadow <<= 8;
/* 1043 */           bsLiveShadow -= 8;
/*      */         }
/* 1045 */         bsBuffShadow |= 1 << 32 - bsLiveShadow - 1;
/* 1046 */         bsLiveShadow++;
/*      */       }
/*      */       
/*      */ 
/* 1050 */       while (bsLiveShadow >= 8) {
/* 1051 */         outShadow.write(bsBuffShadow >> 24);
/* 1052 */         bsBuffShadow <<= 8;
/* 1053 */         bsLiveShadow -= 8;
/*      */       }
/*      */       
/* 1056 */       bsLiveShadow++;
/*      */     }
/*      */     
/* 1059 */     this.bsBuff = bsBuffShadow;
/* 1060 */     this.bsLive = bsLiveShadow;
/*      */   }
/*      */   
/*      */   private void sendMTFValues6(int nGroups, int alphaSize) throws IOException
/*      */   {
/* 1065 */     byte[][] len = this.data.sendMTFValues_len;
/* 1066 */     OutputStream outShadow = this.out;
/*      */     
/* 1068 */     int bsLiveShadow = this.bsLive;
/* 1069 */     int bsBuffShadow = this.bsBuff;
/*      */     
/* 1071 */     for (int t = 0; t < nGroups; t++) {
/* 1072 */       byte[] len_t = len[t];
/* 1073 */       int curr = len_t[0] & 0xFF;
/*      */       
/*      */ 
/* 1076 */       while (bsLiveShadow >= 8) {
/* 1077 */         outShadow.write(bsBuffShadow >> 24);
/* 1078 */         bsBuffShadow <<= 8;
/* 1079 */         bsLiveShadow -= 8;
/*      */       }
/* 1081 */       bsBuffShadow |= curr << 32 - bsLiveShadow - 5;
/* 1082 */       bsLiveShadow += 5;
/*      */       
/* 1084 */       for (int i = 0; i < alphaSize; i++) {
/* 1085 */         int lti = len_t[i] & 0xFF;
/* 1086 */         while (curr < lti)
/*      */         {
/* 1088 */           while (bsLiveShadow >= 8) {
/* 1089 */             outShadow.write(bsBuffShadow >> 24);
/* 1090 */             bsBuffShadow <<= 8;
/* 1091 */             bsLiveShadow -= 8;
/*      */           }
/* 1093 */           bsBuffShadow |= 2 << 32 - bsLiveShadow - 2;
/* 1094 */           bsLiveShadow += 2;
/*      */           
/* 1096 */           curr++;
/*      */         }
/*      */         
/* 1099 */         while (curr > lti)
/*      */         {
/* 1101 */           while (bsLiveShadow >= 8) {
/* 1102 */             outShadow.write(bsBuffShadow >> 24);
/* 1103 */             bsBuffShadow <<= 8;
/* 1104 */             bsLiveShadow -= 8;
/*      */           }
/* 1106 */           bsBuffShadow |= 3 << 32 - bsLiveShadow - 2;
/* 1107 */           bsLiveShadow += 2;
/*      */           
/* 1109 */           curr--;
/*      */         }
/*      */         
/*      */ 
/* 1113 */         while (bsLiveShadow >= 8) {
/* 1114 */           outShadow.write(bsBuffShadow >> 24);
/* 1115 */           bsBuffShadow <<= 8;
/* 1116 */           bsLiveShadow -= 8;
/*      */         }
/*      */         
/* 1119 */         bsLiveShadow++;
/*      */       }
/*      */     }
/*      */     
/* 1123 */     this.bsBuff = bsBuffShadow;
/* 1124 */     this.bsLive = bsLiveShadow;
/*      */   }
/*      */   
/*      */   private void sendMTFValues7(int nSelectors) throws IOException {
/* 1128 */     Data dataShadow = this.data;
/* 1129 */     byte[][] len = dataShadow.sendMTFValues_len;
/* 1130 */     int[][] code = dataShadow.sendMTFValues_code;
/* 1131 */     OutputStream outShadow = this.out;
/* 1132 */     byte[] selector = dataShadow.selector;
/* 1133 */     char[] sfmap = dataShadow.sfmap;
/* 1134 */     int nMTFShadow = this.nMTF;
/*      */     
/* 1136 */     int selCtr = 0;
/*      */     
/* 1138 */     int bsLiveShadow = this.bsLive;
/* 1139 */     int bsBuffShadow = this.bsBuff;
/*      */     
/* 1141 */     for (int gs = 0; gs < nMTFShadow;) {
/* 1142 */       int ge = Math.min(gs + 50 - 1, nMTFShadow - 1);
/* 1143 */       int selector_selCtr = selector[selCtr] & 0xFF;
/* 1144 */       int[] code_selCtr = code[selector_selCtr];
/* 1145 */       byte[] len_selCtr = len[selector_selCtr];
/*      */       
/* 1147 */       while (gs <= ge) {
/* 1148 */         int sfmap_i = sfmap[gs];
/*      */         
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/* 1154 */         while (bsLiveShadow >= 8) {
/* 1155 */           outShadow.write(bsBuffShadow >> 24);
/* 1156 */           bsBuffShadow <<= 8;
/* 1157 */           bsLiveShadow -= 8;
/*      */         }
/* 1159 */         int n = len_selCtr[sfmap_i] & 0xFF;
/* 1160 */         bsBuffShadow |= code_selCtr[sfmap_i] << 32 - bsLiveShadow - n;
/* 1161 */         bsLiveShadow += n;
/*      */         
/* 1163 */         gs++;
/*      */       }
/*      */       
/* 1166 */       gs = ge + 1;
/* 1167 */       selCtr++;
/*      */     }
/*      */     
/* 1170 */     this.bsBuff = bsBuffShadow;
/* 1171 */     this.bsLive = bsLiveShadow;
/*      */   }
/*      */   
/*      */   private void moveToFrontCodeAndSend() throws IOException {
/* 1175 */     bsW(24, this.origPtr);
/* 1176 */     generateMTFValues();
/* 1177 */     sendMTFValues();
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   private boolean mainSimpleSort(Data dataShadow, int lo, int hi, int d)
/*      */   {
/* 1192 */     int bigN = hi - lo + 1;
/* 1193 */     if (bigN < 2) {
/* 1194 */       return (this.firstAttempt) && (this.workDone > this.workLimit);
/*      */     }
/*      */     
/* 1197 */     int hp = 0;
/* 1198 */     while (INCS[hp] < bigN) {
/* 1199 */       hp++;
/*      */     }
/*      */     
/* 1202 */     int[] fmap = dataShadow.fmap;
/* 1203 */     char[] quadrant = dataShadow.quadrant;
/* 1204 */     byte[] block = dataShadow.block;
/* 1205 */     int lastShadow = this.last;
/* 1206 */     int lastPlus1 = lastShadow + 1;
/* 1207 */     boolean firstAttemptShadow = this.firstAttempt;
/* 1208 */     int workLimitShadow = this.workLimit;
/* 1209 */     int workDoneShadow = this.workDone;
/*      */     int h;
/*      */     int mj;
/*      */     int i;
/*      */     label542:
/* 1214 */     label570: label590: label618: label638: label666: label682: label874: for (;;) { hp--; if (hp < 0) break;
/* 1215 */       h = INCS[hp];
/* 1216 */       mj = lo + h - 1;
/*      */       
/* 1218 */       for (i = lo + h; i <= hi;)
/*      */       {
/* 1220 */         for (int k = 3; i <= hi; i++) { k--; if (k < 0) break;
/* 1221 */           int v = fmap[i];
/* 1222 */           int vd = v + d;
/* 1223 */           int j = i;
/*      */           
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/* 1235 */           boolean onceRunned = false;
/* 1236 */           int a = 0;
/*      */           int i1;
/*      */           int i2;
/* 1239 */           do { for (;;) { if (onceRunned) {
/* 1240 */                 fmap[j] = a;
/* 1241 */                 if (j -= h <= mj) {
/*      */                   break label874;
/*      */                 }
/*      */               } else {
/* 1245 */                 onceRunned = true;
/*      */               }
/*      */               
/* 1248 */               a = fmap[(j - h)];
/* 1249 */               i1 = a + d;
/* 1250 */               i2 = vd;
/*      */               
/*      */ 
/*      */ 
/* 1254 */               if (block[(i1 + 1)] != block[(i2 + 1)]) break;
/* 1255 */               if (block[(i1 + 2)] == block[(i2 + 2)]) {
/* 1256 */                 if (block[(i1 + 3)] == block[(i2 + 3)]) {
/* 1257 */                   if (block[(i1 + 4)] == block[(i2 + 4)]) {
/* 1258 */                     if (block[(i1 + 5)] == block[(i2 + 5)]) {
/* 1259 */                       i1 += 6;i2 += 6; if (block[i1] == block[i2]) {
/* 1260 */                         int x = lastShadow;
/* 1261 */                         for (;;) { if (x <= 0) break label874;
/* 1262 */                           x -= 4;
/*      */                           
/* 1264 */                           if (block[(i1 + 1)] != block[(i2 + 1)]) break label682;
/* 1265 */                           if (quadrant[i1] != quadrant[i2]) break label666;
/* 1266 */                           if (block[(i1 + 2)] != block[(i2 + 2)]) break label638;
/* 1267 */                           if (quadrant[(i1 + 1)] != quadrant[(i2 + 1)]) break label618;
/* 1268 */                           if (block[(i1 + 3)] != block[(i2 + 3)]) break label590;
/* 1269 */                           if (quadrant[(i1 + 2)] != quadrant[(i2 + 2)]) break label570;
/* 1270 */                           if (block[(i1 + 4)] != block[(i2 + 4)]) break label542;
/* 1271 */                           if (quadrant[(i1 + 3)] != quadrant[(i2 + 3)]) break;
/* 1272 */                           i1 += 4; if (i1 >= lastPlus1) {
/* 1273 */                             i1 -= lastPlus1;
/*      */                           }
/* 1275 */                           i2 += 4; if (i2 >= lastPlus1) {
/* 1276 */                             i2 -= lastPlus1;
/*      */                           }
/* 1278 */                           workDoneShadow++;
/*      */                         }
/* 1280 */                         if (quadrant[(i1 + 3)] <= quadrant[(i2 + 3)]) break label874;
/* 1281 */                         continue;
/*      */                         
/*      */ 
/*      */ 
/* 1285 */                         if ((block[(i1 + 4)] & 0xFF) <= (block[(i2 + 4)] & 0xFF)) break label874;
/* 1286 */                         continue;
/*      */                         
/*      */ 
/*      */ 
/* 1290 */                         if (quadrant[(i1 + 2)] <= quadrant[(i2 + 2)]) break label874;
/* 1291 */                         continue;
/*      */                         
/*      */ 
/*      */ 
/* 1295 */                         if ((block[(i1 + 3)] & 0xFF) <= (block[(i2 + 3)] & 0xFF)) break label874;
/* 1296 */                         continue;
/*      */                         
/*      */ 
/*      */ 
/* 1300 */                         if (quadrant[(i1 + 1)] <= quadrant[(i2 + 1)]) break label874;
/* 1301 */                         continue;
/*      */                         
/*      */ 
/*      */ 
/* 1305 */                         if ((block[(i1 + 2)] & 0xFF) <= (block[(i2 + 2)] & 0xFF)) break label874;
/* 1306 */                         continue;
/*      */                         
/*      */ 
/*      */ 
/* 1310 */                         if (quadrant[i1] <= quadrant[i2]) break label874;
/* 1311 */                         continue;
/*      */                         
/*      */ 
/*      */ 
/* 1315 */                         if ((block[(i1 + 1)] & 0xFF) <= (block[(i2 + 1)] & 0xFF))
/*      */                         {
/*      */                           break label874;
/*      */                         }
/*      */                         
/*      */ 
/*      */                       }
/*      */                       else
/*      */                       {
/*      */ 
/* 1325 */                         if ((block[i1] & 0xFF) <= (block[i2] & 0xFF)) {
/*      */                           break label874;
/*      */                         }
/*      */                       }
/*      */                     }
/*      */                     else {
/* 1331 */                       if ((block[(i1 + 5)] & 0xFF) <= (block[(i2 + 5)] & 0xFF)) {
/*      */                         break label874;
/*      */                       }
/*      */                     }
/*      */                   } else {
/* 1336 */                     if ((block[(i1 + 4)] & 0xFF) <= (block[(i2 + 4)] & 0xFF)) {
/*      */                       break label874;
/*      */                     }
/*      */                   }
/*      */                 } else {
/* 1341 */                   if ((block[(i1 + 3)] & 0xFF) <= (block[(i2 + 3)] & 0xFF)) {
/*      */                     break label874;
/*      */                   }
/*      */                 }
/*      */               } else {
/* 1346 */                 if ((block[(i1 + 2)] & 0xFF) <= (block[(i2 + 2)] & 0xFF)) {
/*      */                   break label874;
/*      */                 }
/*      */               }
/*      */             }
/* 1351 */           } while ((block[(i1 + 1)] & 0xFF) > (block[(i2 + 1)] & 0xFF));
/*      */           
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/* 1360 */           fmap[j] = v;
/*      */         }
/*      */         
/* 1363 */         if ((firstAttemptShadow) && (i <= hi) && (workDoneShadow > workLimitShadow)) {
/*      */           break label911;
/*      */         }
/*      */       }
/*      */     }
/*      */     
/*      */     label911:
/* 1370 */     this.workDone = workDoneShadow;
/* 1371 */     return (firstAttemptShadow) && (workDoneShadow > workLimitShadow);
/*      */   }
/*      */   
/*      */   private static void vswap(int[] fmap, int p1, int p2, int n) {
/* 1375 */     n += p1;
/* 1376 */     while (p1 < n) {
/* 1377 */       int t = fmap[p1];
/* 1378 */       fmap[(p1++)] = fmap[p2];
/* 1379 */       fmap[(p2++)] = t;
/*      */     }
/*      */   }
/*      */   
/*      */   private static byte med3(byte a, byte b, byte c) {
/* 1384 */     return a > c ? c : b > c ? b : a < b ? a : a < c ? c : b < c ? b : a;
/*      */   }
/*      */   
/*      */   private void blockSort()
/*      */   {
/* 1389 */     this.workLimit = (30 * this.last);
/* 1390 */     this.workDone = 0;
/* 1391 */     this.blockRandomised = false;
/* 1392 */     this.firstAttempt = true;
/* 1393 */     mainSort();
/*      */     
/* 1395 */     if ((this.firstAttempt) && (this.workDone > this.workLimit)) {
/* 1396 */       randomiseBlock();
/* 1397 */       this.workLimit = (this.workDone = 0);
/* 1398 */       this.firstAttempt = false;
/* 1399 */       mainSort();
/*      */     }
/*      */     
/* 1402 */     int[] fmap = this.data.fmap;
/* 1403 */     this.origPtr = -1;
/* 1404 */     int i = 0; for (int lastShadow = this.last; i <= lastShadow; i++) {
/* 1405 */       if (fmap[i] == 0) {
/* 1406 */         this.origPtr = i;
/* 1407 */         break;
/*      */       }
/*      */     }
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   private void mainQSort3(Data dataShadow, int loSt, int hiSt, int dSt)
/*      */   {
/* 1419 */     int[] stack_ll = dataShadow.stack_ll;
/* 1420 */     int[] stack_hh = dataShadow.stack_hh;
/* 1421 */     int[] stack_dd = dataShadow.stack_dd;
/* 1422 */     int[] fmap = dataShadow.fmap;
/* 1423 */     byte[] block = dataShadow.block;
/*      */     
/* 1425 */     stack_ll[0] = loSt;
/* 1426 */     stack_hh[0] = hiSt;
/* 1427 */     stack_dd[0] = dSt;
/*      */     
/* 1429 */     int sp = 1; for (;;) { sp--; if (sp < 0) break;
/* 1430 */       int lo = stack_ll[sp];
/* 1431 */       int hi = stack_hh[sp];
/* 1432 */       int d = stack_dd[sp];
/*      */       
/* 1434 */       if ((hi - lo < 20) || (d > 10)) {
/* 1435 */         if (!mainSimpleSort(dataShadow, lo, hi, d)) {}
/*      */       }
/*      */       else
/*      */       {
/* 1439 */         int d1 = d + 1;
/* 1440 */         int med = med3(block[(fmap[lo] + d1)], block[(fmap[hi] + d1)], block[(fmap[(lo + hi >>> 1)] + d1)]) & 0xFF;
/*      */         
/*      */ 
/* 1443 */         int unLo = lo;
/* 1444 */         int unHi = hi;
/* 1445 */         int ltLo = lo;
/* 1446 */         int gtHi = hi;
/*      */         for (;;)
/*      */         {
/* 1449 */           if (unLo <= unHi) {
/* 1450 */             int n = (block[(fmap[unLo] + d1)] & 0xFF) - med;
/*      */             
/* 1452 */             if (n == 0) {
/* 1453 */               int temp = fmap[unLo];
/* 1454 */               fmap[(unLo++)] = fmap[ltLo];
/* 1455 */               fmap[(ltLo++)] = temp;
/* 1456 */             } else { if (n >= 0) break label255;
/* 1457 */               unLo++;
/*      */             }
/*      */             
/*      */ 
/* 1461 */             continue; }
/*      */           label255:
/* 1463 */           while (unLo <= unHi) {
/* 1464 */             int n = (block[(fmap[unHi] + d1)] & 0xFF) - med;
/*      */             
/* 1466 */             if (n == 0) {
/* 1467 */               int temp = fmap[unHi];
/* 1468 */               fmap[(unHi--)] = fmap[gtHi];
/* 1469 */               fmap[(gtHi--)] = temp;
/* 1470 */             } else { if (n <= 0) break;
/* 1471 */               unHi--;
/*      */             }
/*      */           }
/*      */           
/*      */ 
/*      */ 
/* 1477 */           if (unLo > unHi) break;
/* 1478 */           int temp = fmap[unLo];
/* 1479 */           fmap[(unLo++)] = fmap[unHi];
/* 1480 */           fmap[(unHi--)] = temp;
/*      */         }
/*      */         
/*      */ 
/*      */ 
/*      */ 
/* 1486 */         if (gtHi < ltLo) {
/* 1487 */           stack_ll[sp] = lo;
/* 1488 */           stack_hh[sp] = hi;
/* 1489 */           stack_dd[sp] = d1;
/* 1490 */           sp++;
/*      */         } else {
/* 1492 */           int n = ltLo - lo < unLo - ltLo ? ltLo - lo : unLo - ltLo;
/*      */           
/* 1494 */           vswap(fmap, lo, unLo - n, n);
/* 1495 */           int m = hi - gtHi < gtHi - unHi ? hi - gtHi : gtHi - unHi;
/*      */           
/* 1497 */           vswap(fmap, unLo, hi - m + 1, m);
/*      */           
/* 1499 */           n = lo + unLo - ltLo - 1;
/* 1500 */           m = hi - (gtHi - unHi) + 1;
/*      */           
/* 1502 */           stack_ll[sp] = lo;
/* 1503 */           stack_hh[sp] = n;
/* 1504 */           stack_dd[sp] = d;
/* 1505 */           sp++;
/*      */           
/* 1507 */           stack_ll[sp] = (n + 1);
/* 1508 */           stack_hh[sp] = (m - 1);
/* 1509 */           stack_dd[sp] = d1;
/* 1510 */           sp++;
/*      */           
/* 1512 */           stack_ll[sp] = m;
/* 1513 */           stack_hh[sp] = hi;
/* 1514 */           stack_dd[sp] = d;
/* 1515 */           sp++;
/*      */         }
/*      */       }
/*      */     }
/*      */   }
/*      */   
/*      */   private void mainSort() {
/* 1522 */     Data dataShadow = this.data;
/* 1523 */     int[] runningOrder = dataShadow.mainSort_runningOrder;
/* 1524 */     int[] copy = dataShadow.mainSort_copy;
/* 1525 */     boolean[] bigDone = dataShadow.mainSort_bigDone;
/* 1526 */     int[] ftab = dataShadow.ftab;
/* 1527 */     byte[] block = dataShadow.block;
/* 1528 */     int[] fmap = dataShadow.fmap;
/* 1529 */     char[] quadrant = dataShadow.quadrant;
/* 1530 */     int lastShadow = this.last;
/* 1531 */     int workLimitShadow = this.workLimit;
/* 1532 */     boolean firstAttemptShadow = this.firstAttempt;
/*      */     
/*      */ 
/* 1535 */     int i = 65537; for (;;) { i--; if (i < 0) break;
/* 1536 */       ftab[i] = 0;
/*      */     }
/*      */     
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/* 1544 */     for (int i = 0; i < 20; i++) {
/* 1545 */       block[(lastShadow + i + 2)] = block[(i % (lastShadow + 1) + 1)];
/*      */     }
/* 1547 */     int i = lastShadow + 20 + 1; for (;;) { i--; if (i < 0) break;
/* 1548 */       quadrant[i] = '\000';
/*      */     }
/* 1550 */     block[0] = block[(lastShadow + 1)];
/*      */     
/*      */ 
/*      */ 
/* 1554 */     int c1 = block[0] & 0xFF;
/* 1555 */     for (int i = 0; i <= lastShadow; i++) {
/* 1556 */       int c2 = block[(i + 1)] & 0xFF;
/* 1557 */       ftab[((c1 << 8) + c2)] += 1;
/* 1558 */       c1 = c2;
/*      */     }
/*      */     
/* 1561 */     for (int i = 1; i <= 65536; i++) {
/* 1562 */       ftab[i] += ftab[(i - 1)];
/*      */     }
/* 1564 */     c1 = block[1] & 0xFF;
/* 1565 */     for (int i = 0; i < lastShadow; i++) {
/* 1566 */       int c2 = block[(i + 2)] & 0xFF;
/* 1567 */       fmap[(ftab[((c1 << 8) + c2)] -= 1)] = i;
/* 1568 */       c1 = c2;
/*      */     }
/*      */     
/* 1571 */     fmap[(ftab[(((block[(lastShadow + 1)] & 0xFF) << 8) + (block[1] & 0xFF))] -= 1)] = lastShadow;
/*      */     
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/* 1577 */     int i = 256; for (;;) { i--; if (i < 0) break;
/* 1578 */       bigDone[i] = false;
/* 1579 */       runningOrder[i] = i;
/*      */     }
/*      */     
/* 1582 */     for (int h = 364; h != 1;) {
/* 1583 */       h /= 3;
/* 1584 */       for (int i = h; i <= 255; i++) {
/* 1585 */         int vv = runningOrder[i];
/* 1586 */         int a = ftab[(vv + 1 << 8)] - ftab[(vv << 8)];
/* 1587 */         int b = h - 1;
/* 1588 */         int j = i;
/* 1589 */         for (int ro = runningOrder[(j - h)]; ftab[(ro + 1 << 8)] - ftab[(ro << 8)] > a; ro = runningOrder[(j - h)])
/*      */         {
/* 1591 */           runningOrder[j] = ro;
/* 1592 */           j -= h;
/* 1593 */           if (j <= b) {
/*      */             break;
/*      */           }
/*      */         }
/* 1597 */         runningOrder[j] = vv;
/*      */       }
/*      */     }
/*      */     
/*      */ 
/*      */ 
/*      */ 
/* 1604 */     for (int i = 0; i <= 255; i++)
/*      */     {
/*      */ 
/*      */ 
/* 1608 */       int ss = runningOrder[i];
/*      */       
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/* 1617 */       for (int j = 0; j <= 255; j++) {
/* 1618 */         int sb = (ss << 8) + j;
/* 1619 */         int ftab_sb = ftab[sb];
/* 1620 */         if ((ftab_sb & 0x200000) != 2097152) {
/* 1621 */           int lo = ftab_sb & 0xFFDFFFFF;
/* 1622 */           int hi = (ftab[(sb + 1)] & 0xFFDFFFFF) - 1;
/* 1623 */           if (hi > lo) {
/* 1624 */             mainQSort3(dataShadow, lo, hi, 2);
/* 1625 */             if ((firstAttemptShadow) && (this.workDone > workLimitShadow))
/*      */             {
/* 1627 */               return;
/*      */             }
/*      */           }
/* 1630 */           ftab[sb] = (ftab_sb | 0x200000);
/*      */         }
/*      */       }
/*      */       
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/* 1638 */       for (int j = 0; j <= 255; j++) {
/* 1639 */         copy[j] = (ftab[((j << 8) + ss)] & 0xFFDFFFFF);
/*      */       }
/*      */       
/* 1642 */       int j = ftab[(ss << 8)] & 0xFFDFFFFF; for (int hj = ftab[(ss + 1 << 8)] & 0xFFDFFFFF; j < hj; j++) {
/* 1643 */         int fmap_j = fmap[j];
/* 1644 */         c1 = block[fmap_j] & 0xFF;
/* 1645 */         if (bigDone[c1] == 0) {
/* 1646 */           fmap[copy[c1]] = (fmap_j == 0 ? lastShadow : fmap_j - 1);
/* 1647 */           copy[c1] += 1;
/*      */         }
/*      */       }
/*      */       
/* 1651 */       int j = 256; for (;;) { j--; if (j < 0) break;
/* 1652 */         ftab[((j << 8) + ss)] |= 0x200000;
/*      */       }
/*      */       
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/* 1662 */       bigDone[ss] = true;
/*      */       
/* 1664 */       if (i < 255) {
/* 1665 */         int bbStart = ftab[(ss << 8)] & 0xFFDFFFFF;
/* 1666 */         int bbSize = (ftab[(ss + 1 << 8)] & 0xFFDFFFFF) - bbStart;
/* 1667 */         int shifts = 0;
/*      */         
/* 1669 */         while (bbSize >> shifts > 65534) {
/* 1670 */           shifts++;
/*      */         }
/*      */         
/* 1673 */         for (int j = 0; j < bbSize; j++) {
/* 1674 */           int a2update = fmap[(bbStart + j)];
/* 1675 */           char qVal = (char)(j >> shifts);
/* 1676 */           quadrant[a2update] = qVal;
/* 1677 */           if (a2update < 20) {
/* 1678 */             quadrant[(a2update + lastShadow + 1)] = qVal;
/*      */           }
/*      */         }
/*      */       }
/*      */     }
/*      */   }
/*      */   
/*      */   private void randomiseBlock()
/*      */   {
/* 1687 */     boolean[] inUse = this.data.inUse;
/* 1688 */     byte[] block = this.data.block;
/* 1689 */     int lastShadow = this.last;
/*      */     
/* 1691 */     int i = 256; for (;;) { i--; if (i < 0) break;
/* 1692 */       inUse[i] = false;
/*      */     }
/* 1694 */     int rNToGo = 0;
/* 1695 */     int rTPos = 0;
/* 1696 */     int i = 0; for (int j = 1; i <= lastShadow; j++) {
/* 1697 */       if (rNToGo == 0) {
/* 1698 */         rNToGo = (char)Rand.rNums(rTPos);
/* 1699 */         rTPos++; if (rTPos == 512) {
/* 1700 */           rTPos = 0;
/*      */         }
/*      */       }
/*      */       
/* 1704 */       rNToGo--; int 
/* 1705 */         tmp93_91 = j; byte[] tmp93_90 = block;tmp93_90[tmp93_91] = ((byte)(tmp93_90[tmp93_91] ^ (rNToGo == 1 ? 1 : 0)));
/*      */       
/*      */ 
/* 1708 */       inUse[(block[j] & 0xFF)] = true;i = j;
/*      */     }
/*      */     
/*      */ 
/*      */ 
/*      */ 
/* 1711 */     this.blockRandomised = true;
/*      */   }
/*      */   
/*      */   private void generateMTFValues() {
/* 1715 */     int lastShadow = this.last;
/* 1716 */     Data dataShadow = this.data;
/* 1717 */     boolean[] inUse = dataShadow.inUse;
/* 1718 */     byte[] block = dataShadow.block;
/* 1719 */     int[] fmap = dataShadow.fmap;
/* 1720 */     char[] sfmap = dataShadow.sfmap;
/* 1721 */     int[] mtfFreq = dataShadow.mtfFreq;
/* 1722 */     byte[] unseqToSeq = dataShadow.unseqToSeq;
/* 1723 */     byte[] yy = dataShadow.generateMTFValues_yy;
/*      */     
/*      */ 
/* 1726 */     int nInUseShadow = 0;
/* 1727 */     for (int i = 0; i < 256; i++) {
/* 1728 */       if (inUse[i] != 0) {
/* 1729 */         unseqToSeq[i] = ((byte)nInUseShadow);
/* 1730 */         nInUseShadow++;
/*      */       }
/*      */     }
/* 1733 */     this.nInUse = nInUseShadow;
/*      */     
/* 1735 */     int eob = nInUseShadow + 1;
/*      */     
/* 1737 */     for (int i = eob; i >= 0; i--) {
/* 1738 */       mtfFreq[i] = 0;
/*      */     }
/*      */     
/* 1741 */     int i = nInUseShadow; for (;;) { i--; if (i < 0) break;
/* 1742 */       yy[i] = ((byte)i);
/*      */     }
/*      */     
/* 1745 */     int wr = 0;
/* 1746 */     int zPend = 0;
/*      */     
/* 1748 */     for (int i = 0; i <= lastShadow; i++) {
/* 1749 */       byte ll_i = unseqToSeq[(block[fmap[i]] & 0xFF)];
/* 1750 */       byte tmp = yy[0];
/* 1751 */       int j = 0;
/*      */       
/* 1753 */       while (ll_i != tmp) {
/* 1754 */         j++;
/* 1755 */         byte tmp2 = tmp;
/* 1756 */         tmp = yy[j];
/* 1757 */         yy[j] = tmp2;
/*      */       }
/* 1759 */       yy[0] = tmp;
/*      */       
/* 1761 */       if (j == 0) {
/* 1762 */         zPend++;
/*      */       } else {
/* 1764 */         if (zPend > 0) {
/* 1765 */           zPend--;
/*      */           for (;;) {
/* 1767 */             if ((zPend & 0x1) == 0) {
/* 1768 */               sfmap[wr] = '\000';
/* 1769 */               wr++;
/* 1770 */               mtfFreq[0] += 1;
/*      */             } else {
/* 1772 */               sfmap[wr] = '\001';
/* 1773 */               wr++;
/* 1774 */               mtfFreq[1] += 1;
/*      */             }
/*      */             
/* 1777 */             if (zPend < 2) break;
/* 1778 */             zPend = zPend - 2 >> 1;
/*      */           }
/*      */           
/*      */ 
/*      */ 
/* 1783 */           zPend = 0;
/*      */         }
/* 1785 */         sfmap[wr] = ((char)(j + 1));
/* 1786 */         wr++;
/* 1787 */         mtfFreq[(j + 1)] += 1;
/*      */       }
/*      */     }
/*      */     
/* 1791 */     if (zPend > 0) {
/* 1792 */       zPend--;
/*      */       for (;;) {
/* 1794 */         if ((zPend & 0x1) == 0) {
/* 1795 */           sfmap[wr] = '\000';
/* 1796 */           wr++;
/* 1797 */           mtfFreq[0] += 1;
/*      */         } else {
/* 1799 */           sfmap[wr] = '\001';
/* 1800 */           wr++;
/* 1801 */           mtfFreq[1] += 1;
/*      */         }
/*      */         
/* 1804 */         if (zPend < 2) break;
/* 1805 */         zPend = zPend - 2 >> 1;
/*      */       }
/*      */     }
/*      */     
/*      */ 
/*      */ 
/*      */ 
/* 1812 */     sfmap[wr] = ((char)eob);
/* 1813 */     mtfFreq[eob] += 1;
/* 1814 */     this.nMTF = (wr + 1);
/*      */   }
/*      */   
/*      */ 
/*      */   private static final class Data
/*      */   {
/* 1820 */     final boolean[] inUse = new boolean['Ā'];
/* 1821 */     final byte[] unseqToSeq = new byte['Ā'];
/* 1822 */     final int[] mtfFreq = new int['Ă'];
/* 1823 */     final byte[] selector = new byte['䙒'];
/* 1824 */     final byte[] selectorMtf = new byte['䙒'];
/*      */     
/* 1826 */     final byte[] generateMTFValues_yy = new byte['Ā'];
/* 1827 */     final byte[][] sendMTFValues_len = new byte[6]['Ă'];
/*      */     
/* 1829 */     final int[][] sendMTFValues_rfreq = new int[6]['Ă'];
/*      */     
/* 1831 */     final int[] sendMTFValues_fave = new int[6];
/* 1832 */     final short[] sendMTFValues_cost = new short[6];
/* 1833 */     final int[][] sendMTFValues_code = new int[6]['Ă'];
/*      */     
/* 1835 */     final byte[] sendMTFValues2_pos = new byte[6];
/* 1836 */     final boolean[] sentMTFValues4_inUse16 = new boolean[16];
/*      */     
/* 1838 */     final int[] stack_ll = new int['Ϩ'];
/* 1839 */     final int[] stack_hh = new int['Ϩ'];
/* 1840 */     final int[] stack_dd = new int['Ϩ'];
/*      */     
/* 1842 */     final int[] mainSort_runningOrder = new int['Ā'];
/* 1843 */     final int[] mainSort_copy = new int['Ā'];
/* 1844 */     final boolean[] mainSort_bigDone = new boolean['Ā'];
/*      */     
/* 1846 */     final int[] heap = new int['Ą'];
/* 1847 */     final int[] weight = new int['Ȅ'];
/* 1848 */     final int[] parent = new int['Ȅ'];
/*      */     
/* 1850 */     final int[] ftab = new int[65537];
/*      */     
/*      */ 
/*      */ 
/*      */     final byte[] block;
/*      */     
/*      */ 
/*      */ 
/*      */     final int[] fmap;
/*      */     
/*      */ 
/*      */ 
/*      */     final char[] sfmap;
/*      */     
/*      */ 
/*      */     final char[] quadrant;
/*      */     
/*      */ 
/*      */ 
/*      */     Data(int blockSize100k)
/*      */     {
/* 1871 */       int n = blockSize100k * 100000;
/* 1872 */       this.block = new byte[n + 1 + 20];
/* 1873 */       this.fmap = new int[n];
/* 1874 */       this.sfmap = new char[2 * n];
/* 1875 */       this.quadrant = this.sfmap;
/*      */     }
/*      */   }
/*      */ }


/* Location:              /home/menick/Scrivania/ProcessMining/CNMining/CNMining.jar!/org/apache/commons/compress/compressors/bzip2/BZip2CompressorOutputStream.class
 * Java compiler version: 4 (48.0)
 * JD-Core Version:       0.7.1
 */