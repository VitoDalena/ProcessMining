/*     */ package edu.uci.ics.jung.visualization;
/*     */ 
/*     */ import java.awt.Graphics;
/*     */ import java.awt.Graphics2D;
/*     */ import java.awt.Image;
/*     */ import java.awt.Shape;
/*     */ import java.awt.geom.AffineTransform;
/*     */ import java.awt.geom.GeneralPath;
/*     */ import java.awt.geom.Line2D;
/*     */ import java.awt.geom.Line2D.Float;
/*     */ import java.awt.geom.Point2D;
/*     */ import java.awt.geom.Point2D.Float;
/*     */ import java.awt.image.BufferedImage;
/*     */ import java.io.IOException;
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
/*     */ public class PivotingImageShaper
/*     */ {
/*  47 */   static int sample = 1;
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*  52 */   static int firstx = 0;
/*     */   
/*     */ 
/*  55 */   public static Shape getShape(String fileName) { return getShape(fileName, Integer.MAX_VALUE); }
/*     */   
/*     */   public static Shape getShape(String fileName, int max) {
/*  58 */     BufferedImage image = null;
/*     */     try {
/*  60 */       image = ImageIO.read(FourPassImageShaper.class.getResource(fileName));
/*     */     } catch (IOException ex) {
/*  62 */       ex.printStackTrace();
/*     */     }
/*  64 */     return getShape(image, max);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*  74 */   public static Shape getShape(Image image) { return getShape(image, Integer.MAX_VALUE); }
/*     */   
/*     */   public static Shape getShape(Image image, int max) {
/*  77 */     BufferedImage bi = new BufferedImage(image.getWidth(null), image.getHeight(null), 2);
/*     */     
/*     */ 
/*  80 */     Graphics g = bi.createGraphics();
/*  81 */     g.drawImage(image, 0, 0, null);
/*  82 */     g.dispose();
/*  83 */     return getShape(bi, max);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public static Shape getShape(BufferedImage image, int max)
/*     */   {
/*  94 */     float width = image.getWidth();
/*  95 */     float height = image.getHeight();
/*  96 */     if ((width > max) || (height > max)) {
/*  97 */       BufferedImage smaller = new BufferedImage(max, max, 2);
/*     */       
/*  99 */       Graphics g = smaller.createGraphics();
/* 100 */       AffineTransform at = AffineTransform.getScaleInstance(max / width, max / height);
/* 101 */       AffineTransform back = AffineTransform.getScaleInstance(width / max, height / max);
/* 102 */       Graphics2D g2 = (Graphics2D)g;
/* 103 */       g2.drawImage(image, at, null);
/* 104 */       g2.dispose();
/* 105 */       return back.createTransformedShape(getShape(smaller));
/*     */     }
/* 107 */     return getShape(image);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public static Shape getShape(BufferedImage image)
/*     */   {
/* 118 */     firstx = 0;
/* 119 */     return leftEdge(image, new GeneralPath());
/*     */   }
/*     */   
/*     */   private static Point2D detectLine(Point2D p1, Point2D p2, Point2D p, Line2D line, GeneralPath path)
/*     */   {
/* 124 */     if (p2 == null) {
/* 125 */       p2 = p;
/* 126 */       line.setLine(p1, p2);
/*     */ 
/*     */     }
/* 129 */     else if (line.ptLineDistSq(p) < 1.0D)
/*     */     {
/* 131 */       p2.setLocation(p);
/*     */     } else {
/* 133 */       p1.setLocation(p2);
/* 134 */       p2.setLocation(p);
/* 135 */       line.setLine(p1, p2);
/* 136 */       path.lineTo((float)p1.getX(), (float)p1.getY());
/*     */     }
/* 138 */     return p2;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   private static Shape leftEdge(BufferedImage image, GeneralPath path)
/*     */   {
/* 147 */     int lastj = 0;
/* 148 */     Point2D p1 = null;
/* 149 */     Point2D p2 = null;
/* 150 */     Line2D line = new Line2D.Float();
/* 151 */     for (int i = 0; i < image.getHeight(); i += sample) {
/* 152 */       boolean aPointExistsOnThisLine = false;
/*     */       
/* 154 */       for (int j = 0; j < image.getWidth(); j += sample) {
/* 155 */         if ((image.getRGB(j, i) & 0xFF000000) != 0)
/*     */         {
/* 157 */           Point2D p = new Point2D.Float(j, i);
/* 158 */           aPointExistsOnThisLine = true;
/* 159 */           if (path.getCurrentPoint() != null)
/*     */           {
/* 161 */             p2 = detectLine(p1, p2, p, line, path);
/*     */           }
/*     */           else {
/* 164 */             path.moveTo(j, i);
/* 165 */             firstx = j;
/* 166 */             p1 = p;
/*     */           }
/* 168 */           lastj = j;
/* 169 */           break;
/*     */         }
/*     */       }
/* 172 */       if (!aPointExistsOnThisLine) {
/*     */         break;
/*     */       }
/*     */     }
/* 176 */     return bottomEdge(image, path, lastj);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   private static Shape bottomEdge(BufferedImage image, GeneralPath path, int start)
/*     */   {
/* 187 */     int lastj = 0;
/* 188 */     Point2D p1 = path.getCurrentPoint();
/* 189 */     Point2D p2 = null;
/* 190 */     Line2D line = new Line2D.Float();
/* 191 */     for (int i = start; i < image.getWidth(); i += sample) {
/* 192 */       boolean aPointExistsOnThisLine = false;
/* 193 */       for (int j = image.getHeight() - 1; j >= 0; j -= sample) {
/* 194 */         if ((image.getRGB(i, j) & 0xFF000000) != 0)
/*     */         {
/* 196 */           Point2D p = new Point2D.Float(i, j);
/* 197 */           aPointExistsOnThisLine = true;
/* 198 */           p2 = detectLine(p1, p2, p, line, path);
/* 199 */           lastj = j;
/* 200 */           break;
/*     */         }
/*     */       }
/* 203 */       if (!aPointExistsOnThisLine) {
/*     */         break;
/*     */       }
/*     */     }
/* 207 */     return rightEdge(image, path, lastj);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   private static Shape rightEdge(BufferedImage image, GeneralPath path, int start)
/*     */   {
/* 218 */     int lastj = 0;
/* 219 */     Point2D p1 = path.getCurrentPoint();
/* 220 */     Point2D p2 = null;
/* 221 */     Line2D line = new Line2D.Float();
/* 222 */     for (int i = start; i >= 0; i -= sample) {
/* 223 */       boolean aPointExistsOnThisLine = false;
/*     */       
/* 225 */       for (int j = image.getWidth() - 1; j >= 0; j -= sample) {
/* 226 */         if ((image.getRGB(j, i) & 0xFF000000) != 0)
/*     */         {
/* 228 */           Point2D p = new Point2D.Float(j, i);
/* 229 */           aPointExistsOnThisLine = true;
/* 230 */           p2 = detectLine(p1, p2, p, line, path);
/* 231 */           lastj = j;
/* 232 */           break;
/*     */         }
/*     */       }
/* 235 */       if (!aPointExistsOnThisLine) {
/*     */         break;
/*     */       }
/*     */     }
/* 239 */     return topEdge(image, path, lastj);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   private static Shape topEdge(BufferedImage image, GeneralPath path, int start)
/*     */   {
/* 250 */     Point2D p1 = path.getCurrentPoint();
/* 251 */     Point2D p2 = null;
/* 252 */     Line2D line = new Line2D.Float();
/* 253 */     for (int i = start; i >= firstx; i -= sample) {
/* 254 */       boolean aPointExistsOnThisLine = false;
/* 255 */       for (int j = 0; j < image.getHeight(); j += sample) {
/* 256 */         if ((image.getRGB(i, j) & 0xFF000000) != 0)
/*     */         {
/* 258 */           Point2D p = new Point2D.Float(i, j);
/* 259 */           aPointExistsOnThisLine = true;
/* 260 */           p2 = detectLine(p1, p2, p, line, path);
/* 261 */           break;
/*     */         }
/*     */       }
/* 264 */       if (!aPointExistsOnThisLine) {
/*     */         break;
/*     */       }
/*     */     }
/* 268 */     path.closePath();
/* 269 */     return path;
/*     */   }
/*     */ }


/* Location:              /home/menick/Scrivania/ProcessMining/CNMining/CNMining.jar!/edu/uci/ics/jung/visualization/PivotingImageShaper.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       0.7.1
 */