/*     */ package org.deckfour.xes.nikefs2;
/*     */ 
/*     */ import java.io.File;
/*     */ import java.io.IOException;
/*     */ import java.util.ArrayList;
/*     */ import java.util.List;
/*     */ import org.deckfour.xes.logging.XLogging;
/*     */ import org.deckfour.xes.logging.XLogging.Importance;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class NikeFS2VirtualFileSystem
/*     */   implements NikeFS2StorageProvider
/*     */ {
/*  66 */   protected static NikeFS2VirtualFileSystem instance = null;
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public static synchronized NikeFS2VirtualFileSystem instance()
/*     */   {
/*  74 */     if (instance == null) {
/*  75 */       instance = new NikeFS2VirtualFileSystem();
/*     */     }
/*  77 */     return instance;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*  84 */   protected int blockSize = 2048;
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*  89 */   protected int swapFileSize = 67108864;
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*  95 */   protected boolean useLazyCopies = true;
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   protected List<NikeFS2BlockProvider> blockProviders;
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   private NikeFS2VirtualFileSystem()
/*     */   {
/* 107 */     this.blockProviders = new ArrayList();
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public synchronized void setUseLazyCopies(boolean useLazyCopies)
/*     */   {
/* 118 */     this.useLazyCopies = useLazyCopies;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public synchronized void setSwapFileSize(int bytes)
/*     */   {
/* 129 */     this.swapFileSize = bytes;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public synchronized void setBlockSize(int bytes)
/*     */   {
/* 140 */     this.blockSize = bytes;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public NikeFS2RandomAccessStorage createStorage()
/*     */     throws IOException
/*     */   {
/* 150 */     if (this.useLazyCopies == true) {
/* 151 */       return new NikeFS2LazyRandomAccessStorageImpl(this);
/*     */     }
/* 153 */     return new NikeFS2RandomAccessStorageImpl(this);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public int blockSize()
/*     */   {
/* 163 */     return this.blockSize;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public NikeFS2Block allocateBlock()
/*     */     throws IOException
/*     */   {
/* 174 */     synchronized (this)
/*     */     {
/* 176 */       for (NikeFS2BlockProvider provider : this.blockProviders) {
/* 177 */         if (provider.numberOfFreeBlocks() > 0) {
/* 178 */           NikeFS2Block block = provider.allocateBlock();
/* 179 */           if (block != null) {
/* 180 */             return block;
/*     */           }
/*     */         }
/*     */       }
/*     */     }
/*     */     
/*     */ 
/*     */ 
/* 188 */     System.gc();
/* 189 */     System.runFinalization();
/* 190 */     Thread.yield();
/* 191 */     synchronized (this) {
/* 192 */       for (NikeFS2BlockProvider provider : this.blockProviders) {
/* 193 */         if (provider.numberOfFreeBlocks() > 0) {
/* 194 */           NikeFS2Block block = provider.allocateBlock();
/* 195 */           if (block != null) {
/* 196 */             return block;
/*     */           }
/*     */         }
/*     */       }
/*     */       
/*     */ 
/* 202 */       XLogging.log("NikeFS2: Allocating new swap file. (#" + (this.blockProviders.size() + 1) + ": " + this.swapFileSize + " bytes)", XLogging.Importance.DEBUG);
/*     */       
/*     */ 
/* 205 */       File swapFile = NikeFS2SwapFileManager.createSwapFile();
/* 206 */       NikeFS2BlockProvider addedProvider = new NikeFS2BlockProvider(swapFile, this.swapFileSize, this.blockSize, true);
/*     */       
/* 208 */       this.blockProviders.add(addedProvider);
/* 209 */       return addedProvider.allocateBlock();
/*     */     }
/*     */   }
/*     */ }


/* Location:              /home/menick/Scrivania/ProcessMining/CNMining/CNMining.jar!/org/deckfour/xes/nikefs2/NikeFS2VirtualFileSystem.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */