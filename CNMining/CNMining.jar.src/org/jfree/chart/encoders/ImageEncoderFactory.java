/*     */ package org.jfree.chart.encoders;
/*     */ 
/*     */ import java.util.Hashtable;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class ImageEncoderFactory
/*     */ {
/*  56 */   private static Hashtable encoders = null;
/*     */   
/*     */   static {
/*  59 */     init();
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   private static void init()
/*     */   {
/*  67 */     encoders = new Hashtable();
/*  68 */     encoders.put("jpeg", "org.jfree.chart.encoders.SunJPEGEncoderAdapter");
/*     */     try
/*     */     {
/*  71 */       Class.forName("javax.imageio.ImageIO");
/*     */       
/*  73 */       Class.forName("org.jfree.chart.encoders.SunPNGEncoderAdapter");
/*  74 */       encoders.put("png", "org.jfree.chart.encoders.SunPNGEncoderAdapter");
/*     */       
/*  76 */       encoders.put("jpeg", "org.jfree.chart.encoders.SunJPEGEncoderAdapter");
/*     */     }
/*     */     catch (ClassNotFoundException e)
/*     */     {
/*  80 */       encoders.put("png", "org.jfree.chart.encoders.KeypointPNGEncoderAdapter");
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
/*     */   public static void setImageEncoder(String format, String imageEncoderClassName)
/*     */   {
/*  93 */     encoders.put(format, imageEncoderClassName);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public static ImageEncoder newInstance(String format)
/*     */   {
/* 104 */     ImageEncoder imageEncoder = null;
/* 105 */     String className = (String)encoders.get(format);
/* 106 */     if (className == null) {
/* 107 */       throw new IllegalArgumentException("Unsupported image format - " + format);
/*     */     }
/*     */     try
/*     */     {
/* 111 */       Class imageEncoderClass = Class.forName(className);
/* 112 */       imageEncoder = (ImageEncoder)imageEncoderClass.newInstance();
/*     */     }
/*     */     catch (Exception e) {
/* 115 */       throw new IllegalArgumentException(e.toString());
/*     */     }
/* 117 */     return imageEncoder;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public static ImageEncoder newInstance(String format, float quality)
/*     */   {
/* 129 */     ImageEncoder imageEncoder = newInstance(format);
/* 130 */     imageEncoder.setQuality(quality);
/* 131 */     return imageEncoder;
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
/*     */   public static ImageEncoder newInstance(String format, boolean encodingAlpha)
/*     */   {
/* 144 */     ImageEncoder imageEncoder = newInstance(format);
/* 145 */     imageEncoder.setEncodingAlpha(encodingAlpha);
/* 146 */     return imageEncoder;
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
/*     */   public static ImageEncoder newInstance(String format, float quality, boolean encodingAlpha)
/*     */   {
/* 160 */     ImageEncoder imageEncoder = newInstance(format);
/* 161 */     imageEncoder.setQuality(quality);
/* 162 */     imageEncoder.setEncodingAlpha(encodingAlpha);
/* 163 */     return imageEncoder;
/*     */   }
/*     */ }


/* Location:              /home/menick/Scrivania/ProcessMining/CNMining/CNMining.jar!/org/jfree/chart/encoders/ImageEncoderFactory.class
 * Java compiler version: 3 (47.0)
 * JD-Core Version:       0.7.1
 */