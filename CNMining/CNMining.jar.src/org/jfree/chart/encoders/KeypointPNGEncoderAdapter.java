/*     */ package org.jfree.chart.encoders;
/*     */ 
/*     */ import com.keypoint.PngEncoder;
/*     */ import java.awt.image.BufferedImage;
/*     */ import java.io.IOException;
/*     */ import java.io.OutputStream;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class KeypointPNGEncoderAdapter
/*     */   implements ImageEncoder
/*     */ {
/*  58 */   private int quality = 9;
/*     */   
/*     */ 
/*  61 */   private boolean encodingAlpha = false;
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public float getQuality()
/*     */   {
/*  71 */     return this.quality;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public void setQuality(float quality)
/*     */   {
/*  83 */     this.quality = ((int)quality);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public boolean isEncodingAlpha()
/*     */   {
/*  92 */     return this.encodingAlpha;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public void setEncodingAlpha(boolean encodingAlpha)
/*     */   {
/* 102 */     this.encodingAlpha = encodingAlpha;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public byte[] encode(BufferedImage bufferedImage)
/*     */     throws IOException
/*     */   {
/* 113 */     if (bufferedImage == null) {
/* 114 */       throw new IllegalArgumentException("Null 'image' argument.");
/*     */     }
/* 116 */     PngEncoder encoder = new PngEncoder(bufferedImage, this.encodingAlpha, 0, this.quality);
/*     */     
/* 118 */     return encoder.pngEncode();
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public void encode(BufferedImage bufferedImage, OutputStream outputStream)
/*     */     throws IOException
/*     */   {
/* 131 */     if (bufferedImage == null) {
/* 132 */       throw new IllegalArgumentException("Null 'image' argument.");
/*     */     }
/* 134 */     if (outputStream == null) {
/* 135 */       throw new IllegalArgumentException("Null 'outputStream' argument.");
/*     */     }
/* 137 */     PngEncoder encoder = new PngEncoder(bufferedImage, this.encodingAlpha, 0, this.quality);
/*     */     
/* 139 */     outputStream.write(encoder.pngEncode());
/*     */   }
/*     */ }


/* Location:              /home/menick/Scrivania/ProcessMining/CNMining/CNMining.jar!/org/jfree/chart/encoders/KeypointPNGEncoderAdapter.class
 * Java compiler version: 3 (47.0)
 * JD-Core Version:       0.7.1
 */