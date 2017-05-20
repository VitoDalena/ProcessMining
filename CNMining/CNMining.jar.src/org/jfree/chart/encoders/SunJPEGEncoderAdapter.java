/*     */ package org.jfree.chart.encoders;
/*     */ 
/*     */ import java.awt.image.BufferedImage;
/*     */ import java.io.ByteArrayOutputStream;
/*     */ import java.io.IOException;
/*     */ import java.io.OutputStream;
/*     */ import java.util.Iterator;
/*     */ import javax.imageio.IIOImage;
/*     */ import javax.imageio.ImageIO;
/*     */ import javax.imageio.ImageWriteParam;
/*     */ import javax.imageio.ImageWriter;
/*     */ import javax.imageio.stream.ImageOutputStream;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class SunJPEGEncoderAdapter
/*     */   implements ImageEncoder
/*     */ {
/*  69 */   private float quality = 0.95F;
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public float getQuality()
/*     */   {
/*  87 */     return this.quality;
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
/*  99 */     if ((quality < 0.0F) || (quality > 1.0F)) {
/* 100 */       throw new IllegalArgumentException("The 'quality' must be in the range 0.0f to 1.0f");
/*     */     }
/*     */     
/* 103 */     this.quality = quality;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public boolean isEncodingAlpha()
/*     */   {
/* 113 */     return false;
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
/*     */ 
/*     */   public byte[] encode(BufferedImage bufferedImage)
/*     */     throws IOException
/*     */   {
/* 139 */     ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
/* 140 */     encode(bufferedImage, outputStream);
/* 141 */     return outputStream.toByteArray();
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
/*     */   public void encode(BufferedImage bufferedImage, OutputStream outputStream)
/*     */     throws IOException
/*     */   {
/* 158 */     if (bufferedImage == null) {
/* 159 */       throw new IllegalArgumentException("Null 'image' argument.");
/*     */     }
/* 161 */     if (outputStream == null) {
/* 162 */       throw new IllegalArgumentException("Null 'outputStream' argument.");
/*     */     }
/* 164 */     Iterator iterator = ImageIO.getImageWritersByFormatName("jpeg");
/* 165 */     ImageWriter writer = (ImageWriter)iterator.next();
/* 166 */     ImageWriteParam p = writer.getDefaultWriteParam();
/* 167 */     p.setCompressionMode(2);
/* 168 */     p.setCompressionQuality(this.quality);
/* 169 */     ImageOutputStream ios = ImageIO.createImageOutputStream(outputStream);
/* 170 */     writer.setOutput(ios);
/* 171 */     writer.write(null, new IIOImage(bufferedImage, null, null), p);
/* 172 */     ios.flush();
/* 173 */     writer.dispose();
/* 174 */     ios.close();
/*     */   }
/*     */ }


/* Location:              /home/menick/Scrivania/ProcessMining/CNMining/CNMining.jar!/org/jfree/chart/encoders/SunJPEGEncoderAdapter.class
 * Java compiler version: 3 (47.0)
 * JD-Core Version:       0.7.1
 */