/*     */ package org.deckfour.xes.nikefs2;
/*     */ 
/*     */ import java.io.IOException;
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
/*     */ public class NikeFS2LazyRandomAccessStorageImpl
/*     */   extends NikeFS2RandomAccessStorageImpl
/*     */ {
/*     */   protected NikeFS2LazyRandomAccessStorageImpl parent;
/*  62 */   protected boolean isSoftCopy = true;
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   protected ArrayList<NikeFS2LazyRandomAccessStorageImpl> softCopies;
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public NikeFS2LazyRandomAccessStorageImpl(NikeFS2VirtualFileSystem virtualFileSystem)
/*     */   {
/*  76 */     super(virtualFileSystem);
/*  77 */     synchronized (NikeFS2RandomAccessStorageImpl.class) {
/*  78 */       this.isSoftCopy = false;
/*  79 */       this.parent = null;
/*  80 */       this.softCopies = new ArrayList();
/*     */     }
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public NikeFS2LazyRandomAccessStorageImpl(NikeFS2LazyRandomAccessStorageImpl template)
/*     */   {
/*  92 */     super(template.vfs);
/*  93 */     synchronized (NikeFS2RandomAccessStorageImpl.class) {
/*  94 */       this.isSoftCopy = true;
/*  95 */       this.softCopies = new ArrayList();
/*  96 */       this.size = template.size;
/*  97 */       this.pointer = template.pointer;
/*  98 */       this.blocks = template.blocks;
/*  99 */       this.parent = template;
/* 100 */       template.registerSoftCopy(this);
/*     */     }
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public synchronized void registerSoftCopy(NikeFS2LazyRandomAccessStorageImpl copycat)
/*     */   {
/* 112 */     this.softCopies.add(copycat);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public synchronized void deregisterSoftCopy(NikeFS2LazyRandomAccessStorageImpl copycat)
/*     */   {
/* 123 */     this.softCopies.remove(copycat);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public synchronized void alertSoftCopies()
/*     */     throws IOException
/*     */   {
/* 134 */     NikeFS2LazyRandomAccessStorageImpl[] copies = (NikeFS2LazyRandomAccessStorageImpl[])this.softCopies.toArray(new NikeFS2LazyRandomAccessStorageImpl[this.softCopies.size()]);
/*     */     
/*     */ 
/* 137 */     for (NikeFS2LazyRandomAccessStorageImpl copy : copies) {
/* 138 */       if (copy.isSoftCopy) {
/* 139 */         copy.consolidateSoftCopy();
/* 140 */         copy.alertSoftCopies();
/*     */       }
/*     */     }
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */   public synchronized void consolidateSoftCopy()
/*     */     throws IOException
/*     */   {
/* 150 */     if (this.isSoftCopy == true) {
/* 151 */       ArrayList<NikeFS2Block> copyBlocks = new ArrayList();
/* 152 */       byte[] buffer; if (this.blocks.size() > 0)
/*     */       {
/* 154 */         buffer = new byte[((NikeFS2Block)this.blocks.get(0)).size()];
/* 155 */         for (NikeFS2Block block : this.blocks) {
/* 156 */           NikeFS2Block copyBlock = this.vfs.allocateBlock();
/* 157 */           block.read(0, buffer);
/* 158 */           copyBlock.write(0, buffer);
/* 159 */           copyBlocks.add(copyBlock);
/*     */         }
/*     */       }
/*     */       
/* 163 */       this.blocks = copyBlocks;
/* 164 */       this.isSoftCopy = false;
/*     */       
/* 166 */       this.parent.deregisterSoftCopy(this);
/* 167 */       this.parent = null;
/*     */     }
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public synchronized void close()
/*     */     throws IOException
/*     */   {
/* 179 */     alertSoftCopies();
/* 180 */     if (this.parent != null) {
/* 181 */       this.parent.deregisterSoftCopy(this);
/*     */     }
/* 183 */     if (!this.isSoftCopy)
/*     */     {
/* 185 */       super.close();
/*     */     }
/*     */     else {
/* 188 */       this.blocks = null;
/* 189 */       this.size = 0L;
/* 190 */       this.pointer = 0L;
/*     */     }
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
/* 202 */     return new NikeFS2LazyRandomAccessStorageImpl(this);
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
/*     */   public synchronized void write(byte[] b, int off, int len)
/*     */     throws IOException
/*     */   {
/* 216 */     consolidateSoftCopy();
/* 217 */     alertSoftCopies();
/* 218 */     super.write(b, off, len);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public synchronized void write(byte[] b)
/*     */     throws IOException
/*     */   {
/* 230 */     consolidateSoftCopy();
/* 231 */     alertSoftCopies();
/* 232 */     super.write(b);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public synchronized void write(int b)
/*     */     throws IOException
/*     */   {
/* 244 */     consolidateSoftCopy();
/* 245 */     alertSoftCopies();
/* 246 */     super.write(b);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public synchronized void writeBoolean(boolean v)
/*     */     throws IOException
/*     */   {
/* 258 */     consolidateSoftCopy();
/* 259 */     alertSoftCopies();
/* 260 */     super.writeBoolean(v);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public synchronized void writeByte(int b)
/*     */     throws IOException
/*     */   {
/* 272 */     consolidateSoftCopy();
/* 273 */     alertSoftCopies();
/* 274 */     super.writeByte(b);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public synchronized void writeBytes(String str)
/*     */     throws IOException
/*     */   {
/* 286 */     consolidateSoftCopy();
/* 287 */     alertSoftCopies();
/* 288 */     super.writeBytes(str);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public synchronized void writeChar(int c)
/*     */     throws IOException
/*     */   {
/* 300 */     consolidateSoftCopy();
/* 301 */     alertSoftCopies();
/* 302 */     super.writeChar(c);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public synchronized void writeChars(String str)
/*     */     throws IOException
/*     */   {
/* 314 */     consolidateSoftCopy();
/* 315 */     alertSoftCopies();
/* 316 */     super.writeChars(str);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public synchronized void writeDouble(double d)
/*     */     throws IOException
/*     */   {
/* 328 */     consolidateSoftCopy();
/* 329 */     alertSoftCopies();
/* 330 */     super.writeDouble(d);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public synchronized void writeFloat(float f)
/*     */     throws IOException
/*     */   {
/* 342 */     consolidateSoftCopy();
/* 343 */     alertSoftCopies();
/* 344 */     super.writeFloat(f);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public synchronized void writeInt(int i)
/*     */     throws IOException
/*     */   {
/* 356 */     consolidateSoftCopy();
/* 357 */     alertSoftCopies();
/* 358 */     super.writeInt(i);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public synchronized void writeLong(long l)
/*     */     throws IOException
/*     */   {
/* 370 */     consolidateSoftCopy();
/* 371 */     alertSoftCopies();
/* 372 */     super.writeLong(l);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public synchronized void writeShort(int s)
/*     */     throws IOException
/*     */   {
/* 384 */     consolidateSoftCopy();
/* 385 */     alertSoftCopies();
/* 386 */     super.writeShort(s);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public synchronized void writeUTF(String str)
/*     */     throws IOException
/*     */   {
/* 398 */     consolidateSoftCopy();
/* 399 */     alertSoftCopies();
/* 400 */     super.writeUTF(str);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   protected void finalize()
/*     */     throws Throwable
/*     */   {
/* 410 */     close();
/*     */   }
/*     */ }


/* Location:              /home/menick/Scrivania/ProcessMining/CNMining/CNMining.jar!/org/deckfour/xes/nikefs2/NikeFS2LazyRandomAccessStorageImpl.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */