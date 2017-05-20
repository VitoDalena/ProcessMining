/*     */ package org.deckfour.xes.nikefs2;
/*     */ 
/*     */ import java.io.DataInputStream;
/*     */ import java.io.DataOutputStream;
/*     */ import java.io.IOException;
/*     */ import java.io.InputStream;
/*     */ import java.io.OutputStream;
/*     */ import java.util.ArrayList;
/*     */ import java.util.List;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class NikeFS2RandomAccessStorageImpl
/*     */   implements NikeFS2RandomAccessStorage
/*     */ {
/*     */   protected NikeFS2VirtualFileSystem vfs;
/*     */   protected List<NikeFS2Block> blocks;
/*     */   protected long size;
/*     */   protected long pointer;
/*     */   protected FS2DataOutputStream dataOutputStream;
/*     */   protected FS2DataInputStream dataInputStream;
/*     */   
/*     */   public NikeFS2RandomAccessStorageImpl(NikeFS2VirtualFileSystem virtualFileSystem)
/*     */   {
/*  96 */     this.vfs = virtualFileSystem;
/*  97 */     this.size = 0L;
/*  98 */     this.pointer = 0L;
/*  99 */     this.blocks = new ArrayList();
/* 100 */     this.dataOutputStream = new FS2DataOutputStream(new FS2BlockOutputStream());
/* 101 */     this.dataInputStream = new FS2DataInputStream(new FS2BlockInputStream());
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   protected synchronized void adjustSize()
/*     */   {
/* 109 */     if (this.pointer > this.size) {
/* 110 */       this.size = this.pointer;
/*     */     }
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
/*     */   protected int translateToBlockNumber(long offset)
/*     */   {
/* 124 */     return (int)(offset / this.vfs.blockSize());
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
/*     */   protected int translateToBlockOffset(long offset)
/*     */   {
/* 138 */     return (int)(offset % this.vfs.blockSize());
/*     */   }
/*     */   
/*     */ 
/*     */   public synchronized void close()
/*     */     throws IOException
/*     */   {
/* 145 */     for (int i = this.blocks.size() - 1; i >= 0; i--) {
/* 146 */       ((NikeFS2Block)this.blocks.remove(i)).close();
/*     */     }
/* 148 */     this.size = 0L;
/* 149 */     this.pointer = 0L;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public synchronized NikeFS2RandomAccessStorage copy()
/*     */     throws IOException
/*     */   {
/* 160 */     NikeFS2RandomAccessStorageImpl clone = new NikeFS2RandomAccessStorageImpl(this.vfs);
/* 161 */     byte[] buffer; if (this.blocks.size() > 0)
/*     */     {
/* 163 */       buffer = new byte[((NikeFS2Block)this.blocks.get(0)).size()];
/* 164 */       for (NikeFS2Block block : this.blocks) {
/* 165 */         NikeFS2Block copyBlock = this.vfs.allocateBlock();
/* 166 */         block.read(0, buffer);
/* 167 */         copyBlock.write(0, buffer);
/* 168 */         clone.blocks.add(copyBlock);
/*     */       }
/*     */     }
/*     */     
/* 172 */     clone.size = this.size;
/* 173 */     clone.pointer = 0L;
/* 174 */     return clone;
/*     */   }
/*     */   
/*     */ 
/*     */   public synchronized long getFilePointer()
/*     */     throws IOException
/*     */   {
/* 181 */     return this.pointer;
/*     */   }
/*     */   
/*     */ 
/*     */   public synchronized long length()
/*     */     throws IOException
/*     */   {
/* 188 */     return this.size;
/*     */   }
/*     */   
/*     */ 
/*     */   public synchronized void seek(long pos)
/*     */     throws IOException
/*     */   {
/* 195 */     this.pointer = pos;
/* 196 */     adjustSize();
/*     */   }
/*     */   
/*     */ 
/*     */   public synchronized int skipBytes(int n)
/*     */     throws IOException
/*     */   {
/* 203 */     this.pointer += n;
/* 204 */     adjustSize();
/* 205 */     return n;
/*     */   }
/*     */   
/*     */ 
/*     */   public synchronized void write(int b)
/*     */     throws IOException
/*     */   {
/* 212 */     this.dataOutputStream.write(b);
/* 213 */     this.dataOutputStream.flush();
/*     */   }
/*     */   
/*     */ 
/*     */   public synchronized void write(byte[] b)
/*     */     throws IOException
/*     */   {
/* 220 */     this.dataOutputStream.write(b);
/* 221 */     this.dataOutputStream.flush();
/*     */   }
/*     */   
/*     */ 
/*     */   public synchronized void write(byte[] b, int off, int len)
/*     */     throws IOException
/*     */   {
/* 228 */     this.dataOutputStream.write(b, off, len);
/* 229 */     this.dataOutputStream.flush();
/*     */   }
/*     */   
/*     */ 
/*     */   public synchronized void writeBoolean(boolean v)
/*     */     throws IOException
/*     */   {
/* 236 */     this.dataOutputStream.writeBoolean(v);
/* 237 */     this.dataOutputStream.flush();
/*     */   }
/*     */   
/*     */ 
/*     */   public synchronized void writeByte(int b)
/*     */     throws IOException
/*     */   {
/* 244 */     this.dataOutputStream.writeByte(b);
/* 245 */     this.dataOutputStream.flush();
/*     */   }
/*     */   
/*     */ 
/*     */   public synchronized void writeBytes(String str)
/*     */     throws IOException
/*     */   {
/* 252 */     this.dataOutputStream.writeBytes(str);
/* 253 */     this.dataOutputStream.flush();
/*     */   }
/*     */   
/*     */ 
/*     */   public synchronized void writeChar(int c)
/*     */     throws IOException
/*     */   {
/* 260 */     this.dataOutputStream.writeChar(c);
/* 261 */     this.dataOutputStream.flush();
/*     */   }
/*     */   
/*     */ 
/*     */   public synchronized void writeChars(String str)
/*     */     throws IOException
/*     */   {
/* 268 */     this.dataOutputStream.writeChars(str);
/* 269 */     this.dataOutputStream.flush();
/*     */   }
/*     */   
/*     */ 
/*     */   public synchronized void writeDouble(double d)
/*     */     throws IOException
/*     */   {
/* 276 */     this.dataOutputStream.writeDouble(d);
/* 277 */     this.dataOutputStream.flush();
/*     */   }
/*     */   
/*     */ 
/*     */   public synchronized void writeFloat(float f)
/*     */     throws IOException
/*     */   {
/* 284 */     this.dataOutputStream.writeFloat(f);
/* 285 */     this.dataOutputStream.flush();
/*     */   }
/*     */   
/*     */ 
/*     */   public synchronized void writeInt(int i)
/*     */     throws IOException
/*     */   {
/* 292 */     this.dataOutputStream.writeInt(i);
/* 293 */     this.dataOutputStream.flush();
/*     */   }
/*     */   
/*     */ 
/*     */   public synchronized void writeLong(long l)
/*     */     throws IOException
/*     */   {
/* 300 */     this.dataOutputStream.writeLong(l);
/* 301 */     this.dataOutputStream.flush();
/*     */   }
/*     */   
/*     */ 
/*     */   public synchronized void writeShort(int s)
/*     */     throws IOException
/*     */   {
/* 308 */     this.dataOutputStream.writeShort(s);
/* 309 */     this.dataOutputStream.flush();
/*     */   }
/*     */   
/*     */ 
/*     */   public synchronized void writeUTF(String str)
/*     */     throws IOException
/*     */   {
/* 316 */     this.dataOutputStream.writeSafeUTF(str);
/* 317 */     this.dataOutputStream.flush();
/*     */   }
/*     */   
/*     */ 
/*     */   public synchronized boolean readBoolean()
/*     */     throws IOException
/*     */   {
/* 324 */     return this.dataInputStream.readBoolean();
/*     */   }
/*     */   
/*     */ 
/*     */   public synchronized byte readByte()
/*     */     throws IOException
/*     */   {
/* 331 */     return this.dataInputStream.readByte();
/*     */   }
/*     */   
/*     */ 
/*     */   public synchronized char readChar()
/*     */     throws IOException
/*     */   {
/* 338 */     return this.dataInputStream.readChar();
/*     */   }
/*     */   
/*     */ 
/*     */   public synchronized double readDouble()
/*     */     throws IOException
/*     */   {
/* 345 */     return this.dataInputStream.readDouble();
/*     */   }
/*     */   
/*     */ 
/*     */   public synchronized float readFloat()
/*     */     throws IOException
/*     */   {
/* 352 */     return this.dataInputStream.readFloat();
/*     */   }
/*     */   
/*     */ 
/*     */   public synchronized void readFully(byte[] b)
/*     */     throws IOException
/*     */   {
/* 359 */     this.dataInputStream.readFully(b);
/*     */   }
/*     */   
/*     */ 
/*     */   public synchronized void readFully(byte[] b, int off, int len)
/*     */     throws IOException
/*     */   {
/* 366 */     this.dataInputStream.readFully(b, off, len);
/*     */   }
/*     */   
/*     */ 
/*     */   public synchronized int readInt()
/*     */     throws IOException
/*     */   {
/* 373 */     return this.dataInputStream.readInt();
/*     */   }
/*     */   
/*     */ 
/*     */   @Deprecated
/*     */   public synchronized String readLine()
/*     */     throws IOException
/*     */   {
/* 381 */     return this.dataInputStream.readLine();
/*     */   }
/*     */   
/*     */ 
/*     */   public synchronized long readLong()
/*     */     throws IOException
/*     */   {
/* 388 */     return this.dataInputStream.readLong();
/*     */   }
/*     */   
/*     */ 
/*     */   public synchronized short readShort()
/*     */     throws IOException
/*     */   {
/* 395 */     return this.dataInputStream.readShort();
/*     */   }
/*     */   
/*     */ 
/*     */   public synchronized String readUTF()
/*     */     throws IOException
/*     */   {
/* 402 */     return this.dataInputStream.readSafeUTF();
/*     */   }
/*     */   
/*     */ 
/*     */   public synchronized int readUnsignedByte()
/*     */     throws IOException
/*     */   {
/* 409 */     return this.dataInputStream.readUnsignedByte();
/*     */   }
/*     */   
/*     */ 
/*     */   public synchronized int readUnsignedShort()
/*     */     throws IOException
/*     */   {
/* 416 */     return this.dataInputStream.readUnsignedShort();
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
/*     */   protected class FS2DataOutputStream
/*     */     extends DataOutputStream
/*     */   {
/*     */     public FS2DataOutputStream(OutputStream out)
/*     */     {
/* 433 */       super();
/*     */     }
/*     */     
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     public void writeSafeUTF(String str)
/*     */       throws IOException
/*     */     {
/* 445 */       byte[] bytes = str.getBytes();
/* 446 */       super.writeInt(bytes.length);
/* 447 */       super.write(bytes);
/* 448 */       super.flush();
/*     */     }
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
/*     */   protected class FS2DataInputStream
/*     */     extends DataInputStream
/*     */   {
/*     */     public FS2DataInputStream(InputStream in)
/*     */     {
/* 466 */       super();
/*     */     }
/*     */     
/*     */ 
/*     */ 
/*     */ 
/*     */     public String readSafeUTF()
/*     */       throws IOException
/*     */     {
/* 475 */       int size = super.readInt();
/* 476 */       byte[] bytes = new byte[size];
/* 477 */       super.read(bytes);
/* 478 */       return new String(bytes);
/*     */     }
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   protected class FS2BlockInputStream
/*     */     extends InputStream
/*     */   {
/*     */     protected FS2BlockInputStream() {}
/*     */     
/*     */ 
/*     */ 
/*     */ 
/*     */     public synchronized int read()
/*     */       throws IOException
/*     */     {
/* 497 */       int blockNumber = NikeFS2RandomAccessStorageImpl.this.translateToBlockNumber(NikeFS2RandomAccessStorageImpl.this.pointer);
/* 498 */       if (blockNumber >= NikeFS2RandomAccessStorageImpl.this.blocks.size()) {
/* 499 */         throw new AssertionError("addressing invalid block for reading! (1)");
/*     */       }
/* 501 */       NikeFS2Block block = (NikeFS2Block)NikeFS2RandomAccessStorageImpl.this.blocks.get(blockNumber);
/* 502 */       int blockOffset = NikeFS2RandomAccessStorageImpl.this.translateToBlockOffset(NikeFS2RandomAccessStorageImpl.this.pointer);
/* 503 */       NikeFS2RandomAccessStorageImpl.this.pointer += 1L;
/* 504 */       return block.read(blockOffset);
/*     */     }
/*     */     
/*     */ 
/*     */ 
/*     */ 
/*     */     public synchronized int read(byte[] buffer, int offset, int length)
/*     */       throws IOException
/*     */     {
/* 513 */       int blockNumber = NikeFS2RandomAccessStorageImpl.this.translateToBlockNumber(NikeFS2RandomAccessStorageImpl.this.pointer);
/* 514 */       if (blockNumber >= NikeFS2RandomAccessStorageImpl.this.blocks.size()) {
/* 515 */         throw new AssertionError("addressing invalid block for reading! (1)");
/*     */       }
/* 517 */       int blockOffset = NikeFS2RandomAccessStorageImpl.this.translateToBlockOffset(NikeFS2RandomAccessStorageImpl.this.pointer);
/* 518 */       NikeFS2Block block = (NikeFS2Block)NikeFS2RandomAccessStorageImpl.this.blocks.get(blockNumber);
/* 519 */       int readBytes = block.read(blockOffset, buffer, offset, length);
/* 520 */       length -= readBytes;
/* 521 */       offset += readBytes;
/*     */       
/* 523 */       while (length > 0) {
/* 524 */         blockNumber++;
/* 525 */         if (blockNumber >= NikeFS2RandomAccessStorageImpl.this.blocks.size()) break;
/* 526 */         block = (NikeFS2Block)NikeFS2RandomAccessStorageImpl.this.blocks.get(blockNumber);
/* 527 */         int readNow = block.read(0, buffer, offset, length);
/* 528 */         readBytes += readNow;
/* 529 */         offset += readNow;
/* 530 */         length -= readNow;
/*     */       }
/*     */       
/*     */ 
/*     */ 
/* 535 */       NikeFS2RandomAccessStorageImpl.this.pointer += readBytes;
/* 536 */       return readBytes;
/*     */     }
/*     */     
/*     */ 
/*     */ 
/*     */     public synchronized int read(byte[] buffer)
/*     */       throws IOException
/*     */     {
/* 544 */       return read(buffer, 0, buffer.length);
/*     */     }
/*     */     
/*     */ 
/*     */ 
/*     */     public synchronized long skip(long skip)
/*     */       throws IOException
/*     */     {
/* 552 */       long nPointer = NikeFS2RandomAccessStorageImpl.this.pointer + skip;
/* 553 */       if (nPointer > NikeFS2RandomAccessStorageImpl.this.size) {
/* 554 */         long skipped = NikeFS2RandomAccessStorageImpl.this.size - NikeFS2RandomAccessStorageImpl.this.pointer;
/* 555 */         NikeFS2RandomAccessStorageImpl.this.pointer = NikeFS2RandomAccessStorageImpl.this.size;
/* 556 */         return skipped;
/*     */       }
/* 558 */       NikeFS2RandomAccessStorageImpl.this.pointer += skip;
/* 559 */       return skip;
/*     */     }
/*     */     
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     public boolean markSupported()
/*     */     {
/* 568 */       return false;
/*     */     }
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   protected class FS2BlockOutputStream
/*     */     extends OutputStream
/*     */   {
/*     */     protected FS2BlockOutputStream() {}
/*     */     
/*     */ 
/*     */ 
/*     */ 
/*     */     public synchronized void write(int value)
/*     */       throws IOException
/*     */     {
/* 587 */       int blockNumber = NikeFS2RandomAccessStorageImpl.this.translateToBlockNumber(NikeFS2RandomAccessStorageImpl.this.pointer);
/* 588 */       int blockOffset = NikeFS2RandomAccessStorageImpl.this.translateToBlockOffset(NikeFS2RandomAccessStorageImpl.this.pointer);
/*     */       
/* 590 */       while (blockNumber >= NikeFS2RandomAccessStorageImpl.this.blocks.size()) {
/* 591 */         NikeFS2RandomAccessStorageImpl.this.blocks.add(NikeFS2RandomAccessStorageImpl.this.vfs.allocateBlock());
/*     */       }
/* 593 */       NikeFS2Block block = (NikeFS2Block)NikeFS2RandomAccessStorageImpl.this.blocks.get(blockNumber);
/* 594 */       block.write(blockOffset, value);
/* 595 */       NikeFS2RandomAccessStorageImpl.this.pointer += 1L;
/* 596 */       NikeFS2RandomAccessStorageImpl.this.adjustSize();
/*     */     }
/*     */     
/*     */ 
/*     */ 
/*     */     public synchronized void write(byte[] buffer, int offset, int length)
/*     */       throws IOException
/*     */     {
/* 604 */       int blockNumber = NikeFS2RandomAccessStorageImpl.this.translateToBlockNumber(NikeFS2RandomAccessStorageImpl.this.pointer);
/* 605 */       int blockOffset = NikeFS2RandomAccessStorageImpl.this.translateToBlockOffset(NikeFS2RandomAccessStorageImpl.this.pointer);
/*     */       
/* 607 */       while (blockNumber >= NikeFS2RandomAccessStorageImpl.this.blocks.size()) {
/* 608 */         NikeFS2RandomAccessStorageImpl.this.blocks.add(NikeFS2RandomAccessStorageImpl.this.vfs.allocateBlock());
/*     */       }
/* 610 */       NikeFS2Block block = (NikeFS2Block)NikeFS2RandomAccessStorageImpl.this.blocks.get(blockNumber);
/* 611 */       int bytesToWrite = block.size() - blockOffset;
/* 612 */       if (bytesToWrite > length) {
/* 613 */         bytesToWrite = length;
/*     */       }
/* 615 */       block.write(blockOffset, buffer, offset, bytesToWrite);
/* 616 */       length -= bytesToWrite;
/* 617 */       offset += bytesToWrite;
/* 618 */       int writtenBytes = bytesToWrite;
/*     */       
/* 620 */       while (length > 0) {
/* 621 */         blockNumber++;
/*     */         
/* 623 */         if (blockNumber >= NikeFS2RandomAccessStorageImpl.this.blocks.size())
/*     */         {
/* 625 */           NikeFS2RandomAccessStorageImpl.this.blocks.add(NikeFS2RandomAccessStorageImpl.this.vfs.allocateBlock());
/*     */         }
/* 627 */         block = (NikeFS2Block)NikeFS2RandomAccessStorageImpl.this.blocks.get(blockNumber);
/*     */         
/* 629 */         bytesToWrite = block.size();
/* 630 */         if (bytesToWrite > length) {
/* 631 */           bytesToWrite = length;
/*     */         }
/*     */         
/* 634 */         block.write(0, buffer, offset, bytesToWrite);
/* 635 */         writtenBytes += bytesToWrite;
/* 636 */         length -= bytesToWrite;
/* 637 */         offset += bytesToWrite;
/*     */       }
/* 639 */       NikeFS2RandomAccessStorageImpl.this.pointer += writtenBytes;
/* 640 */       NikeFS2RandomAccessStorageImpl.this.adjustSize();
/*     */     }
/*     */     
/*     */ 
/*     */ 
/*     */     public synchronized void write(byte[] buffer)
/*     */       throws IOException
/*     */     {
/* 648 */       write(buffer, 0, buffer.length);
/*     */     }
/*     */     
/*     */ 
/*     */ 
/*     */ 
/*     */     public void flush()
/*     */       throws IOException
/*     */     {}
/*     */     
/*     */ 
/*     */ 
/*     */ 
/*     */     protected void finalize()
/*     */       throws Throwable
/*     */     {
/* 664 */       close();
/* 665 */       super.finalize();
/*     */     }
/*     */   }
/*     */ }


/* Location:              /home/menick/Scrivania/ProcessMining/CNMining/CNMining.jar!/org/deckfour/xes/nikefs2/NikeFS2RandomAccessStorageImpl.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */