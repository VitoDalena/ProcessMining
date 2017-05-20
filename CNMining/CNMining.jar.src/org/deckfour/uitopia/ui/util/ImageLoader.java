/*    */ package org.deckfour.uitopia.ui.util;
/*    */ 
/*    */ import java.awt.Graphics2D;
/*    */ import java.awt.Image;
/*    */ import java.awt.image.BufferedImage;
/*    */ import java.awt.image.BufferedImageOp;
/*    */ import java.awt.image.LookupOp;
/*    */ import java.awt.image.ShortLookupTable;
/*    */ import java.io.IOException;
/*    */ import java.io.InputStream;
/*    */ import javax.imageio.ImageIO;
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
/*    */ public class ImageLoader
/*    */ {
/*    */   public static BufferedImage load(String name)
/*    */   {
/* 53 */     if (name == null) {
/* 54 */       return null;
/*    */     }
/* 56 */     InputStream url = Thread.currentThread().getContextClassLoader().getResourceAsStream("images/" + name);
/*    */     
/* 58 */     if (url == null) {
/* 59 */       return null;
/*    */     }
/*    */     try {
/* 62 */       return ImageIO.read(url);
/*    */     } catch (IOException e) {}
/* 64 */     return null;
/*    */   }
/*    */   
/*    */ 
/*    */ 
/*    */ 
/*    */   public static Image invert(Image positive)
/*    */   {
/* 72 */     BufferedImage buffer = new BufferedImage(positive.getWidth(null), positive.getHeight(null), 2);
/*    */     
/* 74 */     buffer.createGraphics().drawImage(positive, 0, 0, null);
/* 75 */     short[] invert = new short['Ā'];
/* 76 */     for (int i = 0; i < 256; i++)
/* 77 */       invert[i] = ((short)(255 - i));
/* 78 */     BufferedImageOp invertOp = new LookupOp(new ShortLookupTable(0, invert), null);
/*    */     
/* 80 */     return invertOp.filter(buffer, null);
/*    */   }
/*    */ }


/* Location:              /home/menick/Scrivania/ProcessMining/CNMining/CNMining.jar!/org/deckfour/uitopia/ui/util/ImageLoader.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */