/*     */ package edu.uci.ics.jung.visualization;
/*     */ 
/*     */ import java.awt.Graphics;
/*     */ import java.awt.Graphics2D;
/*     */ import java.awt.Image;
/*     */ import java.awt.Shape;
/*     */ import java.awt.geom.AffineTransform;
/*     */ import java.awt.geom.Area;
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
/*     */ 
/*     */ 
/*     */ public class FourPassImageShaper
/*     */ {
/*  50 */   public static Shape getShape(String fileName) { return getShape(fileName, Integer.MAX_VALUE); }
/*     */   
/*     */   public static Shape getShape(String fileName, int max) {
/*  53 */     BufferedImage image = null;
/*     */     try {
/*  55 */       image = ImageIO.read(FourPassImageShaper.class.getResource(fileName));
/*     */     } catch (IOException ex) {
/*  57 */       ex.printStackTrace();
/*     */     }
/*  59 */     return getShape(image, max);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*  69 */   public static Shape getShape(Image image) { return getShape(image, Integer.MAX_VALUE); }
/*     */   
/*     */   public static Shape getShape(Image image, int max) {
/*  72 */     BufferedImage bi = new BufferedImage(image.getWidth(null), image.getHeight(null), 2);
/*     */     
/*     */ 
/*  75 */     Graphics g = bi.createGraphics();
/*  76 */     g.drawImage(image, 0, 0, null);
/*  77 */     g.dispose();
/*  78 */     return getShape(bi, max);
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
/*     */   public static Shape getShape(BufferedImage image, int max)
/*     */   {
/*  96 */     float width = image.getWidth();
/*  97 */     float height = image.getHeight();
/*  98 */     if ((width > max) || (height > max)) {
/*  99 */       BufferedImage smaller = new BufferedImage(max, max, 2);
/*     */       
/* 101 */       Graphics g = smaller.createGraphics();
/* 102 */       AffineTransform at = AffineTransform.getScaleInstance(max / width, max / height);
/* 103 */       AffineTransform back = AffineTransform.getScaleInstance(width / max, height / max);
/* 104 */       Graphics2D g2 = (Graphics2D)g;
/* 105 */       g2.drawImage(image, at, null);
/* 106 */       g2.dispose();
/* 107 */       return back.createTransformedShape(getShape(smaller));
/*     */     }
/* 109 */     return getShape(image);
/*     */   }
/*     */   
/*     */   public static Shape getShape(BufferedImage image)
/*     */   {
/* 114 */     Area area = new Area(leftEdge(image));
/* 115 */     area.intersect(new Area(bottomEdge(image)));
/* 116 */     area.intersect(new Area(rightEdge(image)));
/* 117 */     area.intersect(new Area(topEdge(image)));
/* 118 */     return area;
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
/*     */   private static Point2D detectLine(Point2D p1, Point2D p2, Point2D p, Line2D line, GeneralPath path)
/*     */   {
/* 136 */     if (p2 == null) {
/* 137 */       p2 = p;
/* 138 */       line.setLine(p1, p2);
/*     */ 
/*     */     }
/* 141 */     else if (line.ptLineDistSq(p) < 1.0D)
/*     */     {
/* 143 */       p2.setLocation(p);
/*     */     } else {
/* 145 */       p1.setLocation(p2);
/* 146 */       p2.setLocation(p);
/* 147 */       line.setLine(p1, p2);
/* 148 */       path.lineTo((float)p1.getX(), (float)p1.getY());
/*     */     }
/* 150 */     return p2;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   private static Shape leftEdge(BufferedImage image)
/*     */   {
/* 159 */     GeneralPath path = new GeneralPath();
/* 160 */     Point2D p1 = new Point2D.Float(image.getWidth() - 1, 0.0F);
/* 161 */     Point2D p2 = null;
/* 162 */     Line2D line = new Line2D.Float();
/* 163 */     Point2D p = new Point2D.Float();
/* 164 */     path.moveTo(image.getWidth() - 1, 0.0F);
/*     */     
/* 166 */     for (int i = 0; i < image.getHeight(); i++) {
/* 167 */       p.setLocation(image.getWidth() - 1, i);
/*     */       
/* 169 */       for (int j = 0; j < image.getWidth(); j++) {
/* 170 */         if ((image.getRGB(j, i) & 0xFF000000) != 0)
/*     */         {
/* 172 */           p.setLocation(j, i);
/* 173 */           break;
/*     */         }
/*     */       }
/* 176 */       p2 = detectLine(p1, p2, p, line, path);
/*     */     }
/* 178 */     p.setLocation(image.getWidth() - 1, image.getHeight() - 1);
/* 179 */     detectLine(p1, p2, p, line, path);
/* 180 */     path.closePath();
/* 181 */     return path;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   private static Shape bottomEdge(BufferedImage image)
/*     */   {
/* 192 */     GeneralPath path = new GeneralPath();
/* 193 */     Point2D p1 = new Point2D.Float(0.0F, 0.0F);
/* 194 */     Point2D p2 = null;
/* 195 */     Line2D line = new Line2D.Float();
/* 196 */     Point2D p = new Point2D.Float();
/* 197 */     path.moveTo(0.0F, 0.0F);
/* 198 */     for (int i = 0; i < image.getWidth(); i++) {
/* 199 */       p.setLocation(i, 0.0D);
/* 200 */       for (int j = image.getHeight() - 1; j >= 0; j--) {
/* 201 */         if ((image.getRGB(i, j) & 0xFF000000) != 0)
/*     */         {
/* 203 */           p.setLocation(i, j);
/* 204 */           break;
/*     */         }
/*     */       }
/* 207 */       p2 = detectLine(p1, p2, p, line, path);
/*     */     }
/* 209 */     p.setLocation(image.getWidth() - 1, 0.0D);
/* 210 */     detectLine(p1, p2, p, line, path);
/* 211 */     path.closePath();
/* 212 */     return path;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   private static Shape rightEdge(BufferedImage image)
/*     */   {
/* 223 */     GeneralPath path = new GeneralPath();
/* 224 */     Point2D p1 = new Point2D.Float(0.0F, image.getHeight() - 1);
/* 225 */     Point2D p2 = null;
/* 226 */     Line2D line = new Line2D.Float();
/* 227 */     Point2D p = new Point2D.Float();
/* 228 */     path.moveTo(0.0F, image.getHeight() - 1);
/*     */     
/* 230 */     for (int i = image.getHeight() - 1; i >= 0; i--) {
/* 231 */       p.setLocation(0.0D, i);
/* 232 */       for (int j = image.getWidth() - 1; j >= 0; j--) {
/* 233 */         if ((image.getRGB(j, i) & 0xFF000000) != 0)
/*     */         {
/* 235 */           p.setLocation(j, i);
/* 236 */           break;
/*     */         }
/*     */       }
/* 239 */       p2 = detectLine(p1, p2, p, line, path);
/*     */     }
/* 241 */     p.setLocation(0.0D, 0.0D);
/* 242 */     detectLine(p1, p2, p, line, path);
/* 243 */     path.closePath();
/* 244 */     return path;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   private static Shape topEdge(BufferedImage image)
/*     */   {
/* 255 */     GeneralPath path = new GeneralPath();
/* 256 */     Point2D p1 = new Point2D.Float(image.getWidth() - 1, image.getHeight() - 1);
/* 257 */     Point2D p2 = null;
/* 258 */     Line2D line = new Line2D.Float();
/* 259 */     Point2D p = new Point2D.Float();
/* 260 */     path.moveTo(image.getWidth() - 1, image.getHeight() - 1);
/*     */     
/* 262 */     for (int i = image.getWidth() - 1; i >= 0; i--) {
/* 263 */       p.setLocation(i, image.getHeight() - 1);
/* 264 */       for (int j = 0; j < image.getHeight(); j++) {
/* 265 */         if ((image.getRGB(i, j) & 0xFF000000) != 0)
/*     */         {
/* 267 */           p.setLocation(i, j);
/* 268 */           break;
/*     */         }
/*     */       }
/* 271 */       p2 = detectLine(p1, p2, p, line, path);
/*     */     }
/* 273 */     p.setLocation(0.0D, image.getHeight() - 1);
/* 274 */     detectLine(p1, p2, p, line, path);
/* 275 */     path.closePath();
/* 276 */     return path;
/*     */   }
/*     */ }


/* Location:              /home/menick/Scrivania/ProcessMining/CNMining/CNMining.jar!/edu/uci/ics/jung/visualization/FourPassImageShaper.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       0.7.1
 */