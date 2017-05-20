/*    */ package com.fluxicon.slickerbox.util;
/*    */ 
/*    */ import java.awt.image.BufferedImage;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class ImageUtilities
/*    */ {
/*    */   public static BufferedImage getMonochromeInstance(BufferedImage original)
/*    */   {
/* 53 */     int width = original.getWidth();
/* 54 */     int height = original.getHeight();
/* 55 */     BufferedImage target = GraphicsUtilities.createTranslucentCompatibleImage(width, height);
/* 56 */     for (int x = 0; x < width; x++) {
/* 57 */       for (int y = 0; y < height; y++) {
/* 58 */         target.setRGB(x, y, pixelToGreyscale(original.getRGB(x, y)));
/*    */       }
/*    */     }
/* 61 */     return target;
/*    */   }
/*    */   
/*    */   protected static int pixelToGreyscale(int rgb) {
/* 65 */     int a = rgb & 0xFF000000;
/* 66 */     int r = rgb >> 16 & 0xFF;
/* 67 */     int g = rgb >> 8 & 0xFF;
/* 68 */     int b = rgb & 0xFF;
/* 69 */     rgb = r * 77 + g * 151 + b * 28 >> 8;
/* 70 */     return a | rgb << 16 | rgb << 8 | rgb;
/*    */   }
/*    */ }


/* Location:              /home/menick/Scrivania/ProcessMining/CNMining/CNMining.jar!/com/fluxicon/slickerbox/util/ImageUtilities.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       0.7.1
 */