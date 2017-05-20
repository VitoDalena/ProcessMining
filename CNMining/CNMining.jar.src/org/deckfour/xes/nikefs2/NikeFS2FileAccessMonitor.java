/*     */ package org.deckfour.xes.nikefs2;
/*     */ 
/*     */ import java.io.IOException;
/*     */ import java.io.RandomAccessFile;
/*     */ import java.nio.MappedByteBuffer;
/*     */ import java.nio.channels.FileChannel;
/*     */ import java.nio.channels.FileChannel.MapMode;
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
/*     */ public class NikeFS2FileAccessMonitor
/*     */ {
/*  55 */   private static NikeFS2FileAccessMonitor singleton = null;
/*     */   
/*     */   public static synchronized NikeFS2FileAccessMonitor instance() {
/*  58 */     return instance(4);
/*     */   }
/*     */   
/*     */   public static synchronized NikeFS2FileAccessMonitor instance(int shadowSize) {
/*  62 */     if (singleton == null) {
/*  63 */       singleton = new NikeFS2FileAccessMonitor(shadowSize);
/*     */     }
/*  65 */     return singleton;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*  71 */   private int shadowSize = 4;
/*     */   
/*     */ 
/*     */ 
/*  75 */   private int currentShadowSize = 0;
/*     */   
/*     */ 
/*     */ 
/*  79 */   private int lastRequestIndex = 0;
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*  84 */   private MappedByteBuffer[] centralMaps = null;
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*  89 */   private NikeFS2BlockProvider[] currentMapOwners = null;
/*     */   
/*     */ 
/*     */ 
/*  93 */   private RandomAccessFile[] currentMapRandomAccessFiles = null;
/*     */   
/*     */ 
/*     */ 
/*  97 */   private FileChannel[] currentMapFileChannels = null;
/*     */   
/*     */   public NikeFS2FileAccessMonitor(int shadowSize)
/*     */   {
/* 101 */     this.shadowSize = shadowSize;
/* 102 */     this.centralMaps = new MappedByteBuffer[shadowSize];
/* 103 */     this.currentMapOwners = new NikeFS2BlockProvider[shadowSize];
/* 104 */     this.currentMapRandomAccessFiles = new RandomAccessFile[shadowSize];
/* 105 */     this.currentMapFileChannels = new FileChannel[shadowSize];
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
/*     */   public synchronized MappedByteBuffer requestMap(NikeFS2BlockProvider requester)
/*     */     throws IOException
/*     */   {
/* 120 */     for (int i = 0; i < this.currentShadowSize; i++) {
/* 121 */       if (this.currentMapOwners[i] == requester)
/*     */       {
/* 123 */         this.lastRequestIndex = i;
/* 124 */         return this.centralMaps[i];
/*     */       }
/*     */     }
/*     */     
/* 128 */     if (this.currentShadowSize < this.shadowSize)
/*     */     {
/* 130 */       this.currentMapOwners[this.currentShadowSize] = requester;
/* 131 */       this.currentMapRandomAccessFiles[this.currentShadowSize] = new RandomAccessFile(requester.getFile(), "rw");
/* 132 */       this.currentMapFileChannels[this.currentShadowSize] = this.currentMapRandomAccessFiles[this.currentShadowSize].getChannel();
/* 133 */       MappedByteBuffer map = this.currentMapFileChannels[this.currentShadowSize].map(FileChannel.MapMode.READ_WRITE, 0L, requester.size());
/* 134 */       this.centralMaps[this.currentShadowSize] = map;
/* 135 */       this.lastRequestIndex = this.currentShadowSize;
/* 136 */       this.currentShadowSize += 1;
/* 137 */       XLogging.log("NikeFS2: Populating shadow map " + this.currentShadowSize + " (of " + this.shadowSize + " max.)", XLogging.Importance.DEBUG);
/* 138 */       return map;
/*     */     }
/*     */     
/* 141 */     int kickIndex = this.lastRequestIndex + 1;
/* 142 */     if (kickIndex == this.shadowSize) {
/* 143 */       kickIndex = 0;
/*     */     }
/* 145 */     this.centralMaps[kickIndex].force();
/* 146 */     this.centralMaps[kickIndex] = null;
/* 147 */     this.currentMapFileChannels[kickIndex].close();
/* 148 */     this.currentMapFileChannels[kickIndex] = null;
/* 149 */     this.currentMapRandomAccessFiles[kickIndex].close();
/* 150 */     this.currentMapRandomAccessFiles[kickIndex] = null;
/* 151 */     System.gc();
/* 152 */     this.currentMapOwners[kickIndex] = requester;
/* 153 */     this.currentMapRandomAccessFiles[kickIndex] = new RandomAccessFile(requester.getFile(), "rw");
/* 154 */     this.currentMapFileChannels[kickIndex] = this.currentMapRandomAccessFiles[kickIndex].getChannel();
/* 155 */     MappedByteBuffer map = this.currentMapFileChannels[kickIndex].map(FileChannel.MapMode.READ_WRITE, 0L, requester.size());
/* 156 */     this.centralMaps[kickIndex] = map;
/* 157 */     this.lastRequestIndex = kickIndex;
/* 158 */     XLogging.log("NikeFS2: Displacing shadow map " + (kickIndex + 1), XLogging.Importance.DEBUG);
/*     */     
/* 160 */     return map;
/*     */   }
/*     */ }


/* Location:              /home/menick/Scrivania/ProcessMining/CNMining/CNMining.jar!/org/deckfour/xes/nikefs2/NikeFS2FileAccessMonitor.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */