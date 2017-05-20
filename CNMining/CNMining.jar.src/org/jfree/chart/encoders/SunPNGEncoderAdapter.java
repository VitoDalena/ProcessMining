/*     */ package org.jfree.chart.encoders;
/*     */ 
/*     */ import java.awt.image.BufferedImage;
/*     */ import java.io.ByteArrayOutputStream;
/*     */ import java.io.IOException;
/*     */ import java.io.OutputStream;
/*     */ import javax.imageio.ImageIO;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class SunPNGEncoderAdapter
/*     */   implements ImageEncoder
/*     */ {
/*     */   public float getQuality()
/*     */   {
/*  64 */     return 0.0F;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public void setQuality(float quality) {}
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public boolean isEncodingAlpha()
/*     */   {
/*  83 */     return false;
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
/*     */   public void setEncodingAlpha(boolean encodingAlpha) {}
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public byte[] encode(BufferedImage bufferedImage)
/*     */     throws IOException
/*     */   {
/* 107 */     ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
/* 108 */     encode(bufferedImage, outputStream);
/* 109 */     return outputStream.toByteArray();
/*     */   }
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
/* 121 */     if (bufferedImage == null) {
/* 122 */       throw new IllegalArgumentException("Null 'image' argument.");
/*     */     }
/* 124 */     if (outputStream == null) {
/* 125 */       throw new IllegalArgumentException("Null 'outputStream' argument.");
/*     */     }
/* 127 */     ImageIO.write(bufferedImage, "png", outputStream);
/*     */   }
/*     */ }


/* Location:              /home/menick/Scrivania/ProcessMining/CNMining/CNMining.jar!/org/jfree/chart/encoders/SunPNGEncoderAdapter.class
 * Java compiler version: 3 (47.0)
 * JD-Core Version:       0.7.1
 */