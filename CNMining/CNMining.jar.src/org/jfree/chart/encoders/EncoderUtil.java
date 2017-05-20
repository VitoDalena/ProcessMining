/*     */ package org.jfree.chart.encoders;
/*     */ 
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
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class EncoderUtil
/*     */ {
/*     */   public static byte[] encode(BufferedImage image, String format)
/*     */     throws IOException
/*     */   {
/*  65 */     ImageEncoder imageEncoder = ImageEncoderFactory.newInstance(format);
/*  66 */     return imageEncoder.encode(image);
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
/*     */   public static byte[] encode(BufferedImage image, String format, boolean encodeAlpha)
/*     */     throws IOException
/*     */   {
/*  81 */     ImageEncoder imageEncoder = ImageEncoderFactory.newInstance(format, encodeAlpha);
/*     */     
/*  83 */     return imageEncoder.encode(image);
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
/*     */   public static byte[] encode(BufferedImage image, String format, float quality)
/*     */     throws IOException
/*     */   {
/*  98 */     ImageEncoder imageEncoder = ImageEncoderFactory.newInstance(format, quality);
/*     */     
/* 100 */     return imageEncoder.encode(image);
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
/*     */   public static byte[] encode(BufferedImage image, String format, float quality, boolean encodeAlpha)
/*     */     throws IOException
/*     */   {
/* 118 */     ImageEncoder imageEncoder = ImageEncoderFactory.newInstance(format, quality, encodeAlpha);
/*     */     
/* 120 */     return imageEncoder.encode(image);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public static void writeBufferedImage(BufferedImage image, String format, OutputStream outputStream)
/*     */     throws IOException
/*     */   {
/* 133 */     ImageEncoder imageEncoder = ImageEncoderFactory.newInstance(format);
/* 134 */     imageEncoder.encode(image, outputStream);
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
/*     */   public static void writeBufferedImage(BufferedImage image, String format, OutputStream outputStream, float quality)
/*     */     throws IOException
/*     */   {
/* 149 */     ImageEncoder imageEncoder = ImageEncoderFactory.newInstance(format, quality);
/*     */     
/* 151 */     imageEncoder.encode(image, outputStream);
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
/*     */   public static void writeBufferedImage(BufferedImage image, String format, OutputStream outputStream, boolean encodeAlpha)
/*     */     throws IOException
/*     */   {
/* 166 */     ImageEncoder imageEncoder = ImageEncoderFactory.newInstance(format, encodeAlpha);
/*     */     
/* 168 */     imageEncoder.encode(image, outputStream);
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
/*     */   public static void writeBufferedImage(BufferedImage image, String format, OutputStream outputStream, float quality, boolean encodeAlpha)
/*     */     throws IOException
/*     */   {
/* 186 */     ImageEncoder imageEncoder = ImageEncoderFactory.newInstance(format, quality, encodeAlpha);
/*     */     
/* 188 */     imageEncoder.encode(image, outputStream);
/*     */   }
/*     */ }


/* Location:              /home/menick/Scrivania/ProcessMining/CNMining/CNMining.jar!/org/jfree/chart/encoders/EncoderUtil.class
 * Java compiler version: 3 (47.0)
 * JD-Core Version:       0.7.1
 */