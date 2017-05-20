/*     */ package org.jfree.util;
/*     */ 
/*     */ import java.awt.Graphics;
/*     */ import java.awt.Image;
/*     */ import java.awt.image.BufferedImage;
/*     */ import java.awt.image.ImageObserver;
/*     */ import java.io.Serializable;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class WaitingImageObserver
/*     */   implements ImageObserver, Serializable, Cloneable
/*     */ {
/*     */   static final long serialVersionUID = -807204410581383550L;
/*     */   private boolean lock;
/*     */   private Image image;
/*     */   private boolean error;
/*     */   
/*     */   public WaitingImageObserver(Image image)
/*     */   {
/*  88 */     if (image == null) {
/*  89 */       throw new NullPointerException();
/*     */     }
/*  91 */     this.image = image;
/*  92 */     this.lock = true;
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
/*     */ 
/*     */ 
/*     */ 
/*     */   public synchronized boolean imageUpdate(Image img, int infoflags, int x, int y, int width, int height)
/*     */   {
/* 121 */     if ((infoflags & 0x20) == 32) {
/* 122 */       this.lock = false;
/* 123 */       this.error = false;
/* 124 */       notifyAll();
/* 125 */       return false;
/*     */     }
/* 127 */     if (((infoflags & 0x80) == 128) || ((infoflags & 0x40) == 64))
/*     */     {
/* 129 */       this.lock = false;
/* 130 */       this.error = true;
/* 131 */       notifyAll();
/* 132 */       return false;
/*     */     }
/*     */     
/* 135 */     return true;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public synchronized void waitImageLoaded()
/*     */   {
/* 144 */     if (!this.lock)
/*     */     {
/* 146 */       return;
/*     */     }
/*     */     
/* 149 */     BufferedImage img = new BufferedImage(1, 1, 1);
/*     */     
/*     */ 
/* 152 */     Graphics g = img.getGraphics();
/*     */     
/* 154 */     while (this.lock) {
/* 155 */       if (g.drawImage(this.image, 0, 0, img.getWidth(this), img.getHeight(this), this))
/*     */       {
/* 157 */         return;
/*     */       }
/*     */       try
/*     */       {
/* 161 */         wait(500L);
/*     */       }
/*     */       catch (InterruptedException e) {
/* 164 */         Log.info("WaitingImageObserver.waitImageLoaded(): InterruptedException thrown", e);
/*     */       }
/*     */     }
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   /**
/*     */    * @deprecated
/*     */    */
/*     */   public Object clone()
/*     */     throws CloneNotSupportedException
/*     */   {
/* 181 */     return (WaitingImageObserver)super.clone();
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public boolean isLoadingComplete()
/*     */   {
/* 191 */     return !this.lock;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public boolean isError()
/*     */   {
/* 200 */     return this.error;
/*     */   }
/*     */ }


/* Location:              /home/menick/Scrivania/ProcessMining/CNMining/CNMining.jar!/org/jfree/util/WaitingImageObserver.class
 * Java compiler version: 2 (46.0)
 * JD-Core Version:       0.7.1
 */