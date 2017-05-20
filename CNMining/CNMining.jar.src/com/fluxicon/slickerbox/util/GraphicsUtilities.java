/*     */ package com.fluxicon.slickerbox.util;
/*     */ 
/*     */ import java.awt.Graphics;
/*     */ import java.awt.Graphics2D;
/*     */ import java.awt.GraphicsConfiguration;
/*     */ import java.awt.GraphicsDevice;
/*     */ import java.awt.GraphicsEnvironment;
/*     */ import java.awt.RenderingHints;
/*     */ import java.awt.image.BufferedImage;
/*     */ import java.awt.image.ColorModel;
/*     */ import java.awt.image.Raster;
/*     */ import java.awt.image.WritableRaster;
/*     */ import java.io.IOException;
/*     */ import java.net.URL;
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
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class GraphicsUtilities
/*     */ {
/*  79 */   private static final GraphicsConfiguration CONFIGURATION = GraphicsEnvironment.getLocalGraphicsEnvironment()
/*  80 */     .getDefaultScreenDevice().getDefaultConfiguration();
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public static BufferedImage createColorModelCompatibleImage(BufferedImage image)
/*     */   {
/*  97 */     ColorModel cm = image.getColorModel();
/*  98 */     return new BufferedImage(cm, 
/*  99 */       cm.createCompatibleWritableRaster(image.getWidth(), 
/* 100 */       image.getHeight()), 
/* 101 */       cm.isAlphaPremultiplied(), null);
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
/*     */   public static BufferedImage createCompatibleImage(BufferedImage image)
/*     */   {
/* 120 */     return createCompatibleImage(image, image.getWidth(), image.getHeight());
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
/*     */   public static BufferedImage createCompatibleImage(BufferedImage image, int width, int height)
/*     */   {
/* 142 */     return CONFIGURATION.createCompatibleImage(width, height, 
/* 143 */       image.getTransparency());
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
/*     */   public static BufferedImage createCompatibleImage(int width, int height)
/*     */   {
/* 161 */     return CONFIGURATION.createCompatibleImage(width, height);
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
/*     */   public static BufferedImage createTranslucentCompatibleImage(int width, int height)
/*     */   {
/* 180 */     return CONFIGURATION.createCompatibleImage(width, height, 
/* 181 */       3);
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
/*     */   public static BufferedImage loadCompatibleImage(URL resource)
/*     */     throws IOException
/*     */   {
/* 201 */     BufferedImage image = ImageIO.read(resource);
/* 202 */     return toCompatibleImage(image);
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
/*     */   public static BufferedImage toCompatibleImage(BufferedImage image)
/*     */   {
/* 220 */     if (image.getColorModel().equals(CONFIGURATION.getColorModel())) {
/* 221 */       return image;
/*     */     }
/*     */     
/* 224 */     BufferedImage compatibleImage = CONFIGURATION.createCompatibleImage(
/* 225 */       image.getWidth(), image.getHeight(), image.getTransparency());
/* 226 */     Graphics g = compatibleImage.getGraphics();
/* 227 */     g.drawImage(image, 0, 0, null);
/* 228 */     g.dispose();
/*     */     
/* 230 */     return compatibleImage;
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
/*     */   public static BufferedImage createThumbnailFast(BufferedImage image, int newSize)
/*     */   {
/* 258 */     int width = image.getWidth();
/* 259 */     int height = image.getHeight();
/*     */     
/* 261 */     if (width > height) {
/* 262 */       if (newSize >= width) {
/* 263 */         throw new IllegalArgumentException("newSize must be lower than the image width");
/*     */       }
/* 265 */       if (newSize <= 0) {
/* 266 */         throw new IllegalArgumentException("newSize must be greater than 0");
/*     */       }
/*     */       
/*     */ 
/* 270 */       float ratio = width / height;
/* 271 */       width = newSize;
/* 272 */       height = (int)(newSize / ratio);
/*     */     } else {
/* 274 */       if (newSize >= height) {
/* 275 */         throw new IllegalArgumentException("newSize must be lower than the image height");
/*     */       }
/* 277 */       if (newSize <= 0) {
/* 278 */         throw new IllegalArgumentException("newSize must be greater than 0");
/*     */       }
/*     */       
/*     */ 
/* 282 */       float ratio = height / width;
/* 283 */       height = newSize;
/* 284 */       width = (int)(newSize / ratio);
/*     */     }
/*     */     
/* 287 */     BufferedImage temp = createCompatibleImage(image, width, height);
/* 288 */     Graphics2D g2 = temp.createGraphics();
/* 289 */     g2.setRenderingHint(RenderingHints.KEY_INTERPOLATION, 
/* 290 */       RenderingHints.VALUE_INTERPOLATION_BILINEAR);
/* 291 */     g2.drawImage(image, 0, 0, temp.getWidth(), temp.getHeight(), null);
/* 292 */     g2.dispose();
/*     */     
/* 294 */     return temp;
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
/*     */   public static BufferedImage createThumbnailFast(BufferedImage image, int newWidth, int newHeight)
/*     */   {
/* 321 */     if ((newWidth >= image.getWidth()) || 
/* 322 */       (newHeight >= image.getHeight())) {
/* 323 */       throw new IllegalArgumentException("newWidth and newHeight cannot be greater than the image dimensions");
/*     */     }
/*     */     
/* 326 */     if ((newWidth <= 0) || (newHeight <= 0)) {
/* 327 */       throw new IllegalArgumentException("newWidth and newHeight must be greater than 0");
/*     */     }
/*     */     
/*     */ 
/* 331 */     BufferedImage temp = createCompatibleImage(image, newWidth, newHeight);
/* 332 */     Graphics2D g2 = temp.createGraphics();
/* 333 */     g2.setRenderingHint(RenderingHints.KEY_INTERPOLATION, 
/* 334 */       RenderingHints.VALUE_INTERPOLATION_BILINEAR);
/* 335 */     g2.drawImage(image, 0, 0, temp.getWidth(), temp.getHeight(), null);
/* 336 */     g2.dispose();
/*     */     
/* 338 */     return temp;
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
/*     */   public static BufferedImage createThumbnail(BufferedImage image, int newSize)
/*     */   {
/* 364 */     int width = image.getWidth();
/* 365 */     int height = image.getHeight();
/*     */     
/* 367 */     boolean isWidthGreater = width > height;
/*     */     
/* 369 */     if (isWidthGreater) {
/* 370 */       if (newSize >= width) {
/* 371 */         throw new IllegalArgumentException("newSize must be lower than the image width");
/*     */       }
/*     */     }
/* 374 */     else if (newSize >= height) {
/* 375 */       throw new IllegalArgumentException("newSize must be lower than the image height");
/*     */     }
/*     */     
/*     */ 
/* 379 */     if (newSize <= 0) {
/* 380 */       throw new IllegalArgumentException("newSize must be greater than 0");
/*     */     }
/*     */     
/*     */ 
/* 384 */     float ratioWH = width / height;
/* 385 */     float ratioHW = height / width;
/*     */     
/* 387 */     BufferedImage thumb = image;
/*     */     do
/*     */     {
/* 390 */       if (isWidthGreater) {
/* 391 */         width /= 2;
/* 392 */         if (width < newSize) {
/* 393 */           width = newSize;
/*     */         }
/* 395 */         height = (int)(width / ratioWH);
/*     */       } else {
/* 397 */         height /= 2;
/* 398 */         if (height < newSize) {
/* 399 */           height = newSize;
/*     */         }
/* 401 */         width = (int)(height / ratioHW);
/*     */       }
/*     */       
/*     */ 
/* 405 */       BufferedImage temp = createCompatibleImage(image, width, height);
/* 406 */       Graphics2D g2 = temp.createGraphics();
/* 407 */       g2.setRenderingHint(RenderingHints.KEY_INTERPOLATION, 
/* 408 */         RenderingHints.VALUE_INTERPOLATION_BILINEAR);
/* 409 */       g2.drawImage(thumb, 0, 0, temp.getWidth(), temp.getHeight(), null);
/* 410 */       g2.dispose();
/*     */       
/* 412 */       thumb = temp;
/* 413 */     } while (newSize != (isWidthGreater ? width : height));
/*     */     
/* 415 */     return thumb;
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
/*     */   public static BufferedImage createThumbnail(BufferedImage image, int newWidth, int newHeight)
/*     */   {
/* 440 */     int width = image.getWidth();
/* 441 */     int height = image.getHeight();
/*     */     
/* 443 */     if ((newWidth >= width) || (newHeight >= height)) {
/* 444 */       throw new IllegalArgumentException("newWidth and newHeight cannot be greater than the image dimensions");
/*     */     }
/*     */     
/* 447 */     if ((newWidth <= 0) || (newHeight <= 0)) {
/* 448 */       throw new IllegalArgumentException("newWidth and newHeight must be greater than 0");
/*     */     }
/*     */     
/*     */ 
/* 452 */     BufferedImage thumb = image;
/*     */     do
/*     */     {
/* 455 */       if (width > newWidth) {
/* 456 */         width /= 2;
/* 457 */         if (width < newWidth) {
/* 458 */           width = newWidth;
/*     */         }
/*     */       }
/*     */       
/* 462 */       if (height > newHeight) {
/* 463 */         height /= 2;
/* 464 */         if (height < newHeight) {
/* 465 */           height = newHeight;
/*     */         }
/*     */       }
/*     */       
/* 469 */       BufferedImage temp = createCompatibleImage(image, width, height);
/* 470 */       Graphics2D g2 = temp.createGraphics();
/* 471 */       g2.setRenderingHint(RenderingHints.KEY_INTERPOLATION, 
/* 472 */         RenderingHints.VALUE_INTERPOLATION_BILINEAR);
/* 473 */       g2.drawImage(thumb, 0, 0, temp.getWidth(), temp.getHeight(), null);
/* 474 */       g2.dispose();
/*     */       
/* 476 */       thumb = temp;
/* 477 */     } while ((width != newWidth) || (height != newHeight));
/*     */     
/* 479 */     return thumb;
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
/*     */   public static int[] getPixels(BufferedImage img, int x, int y, int w, int h, int[] pixels)
/*     */   {
/* 502 */     if ((w == 0) || (h == 0)) {
/* 503 */       return new int[0];
/*     */     }
/*     */     
/* 506 */     if (pixels == null) {
/* 507 */       pixels = new int[w * h];
/* 508 */     } else if (pixels.length < w * h) {
/* 509 */       throw new IllegalArgumentException("pixels array must have a length >= w*h");
/*     */     }
/*     */     
/*     */ 
/* 513 */     int imageType = img.getType();
/* 514 */     if ((imageType == 2) || 
/* 515 */       (imageType == 1)) {
/* 516 */       Raster raster = img.getRaster();
/* 517 */       return (int[])raster.getDataElements(x, y, w, h, pixels);
/*     */     }
/*     */     
/*     */ 
/* 521 */     return img.getRGB(x, y, w, h, pixels, 0, w);
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
/*     */   public static void setPixels(BufferedImage img, int x, int y, int w, int h, int[] pixels)
/*     */   {
/* 541 */     if ((pixels == null) || (w == 0) || (h == 0))
/* 542 */       return;
/* 543 */     if (pixels.length < w * h) {
/* 544 */       throw new IllegalArgumentException("pixels array must have a length >= w*h");
/*     */     }
/*     */     
/*     */ 
/* 548 */     int imageType = img.getType();
/* 549 */     if ((imageType == 2) || 
/* 550 */       (imageType == 1)) {
/* 551 */       WritableRaster raster = img.getRaster();
/* 552 */       raster.setDataElements(x, y, w, h, pixels);
/*     */     }
/*     */     else {
/* 555 */       img.setRGB(x, y, w, h, pixels, 0, w);
/*     */     }
/*     */   }
/*     */ }


/* Location:              /home/menick/Scrivania/ProcessMining/CNMining/CNMining.jar!/com/fluxicon/slickerbox/util/GraphicsUtilities.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       0.7.1
 */