/*     */ package org.jfree.chart;
/*     */ 
/*     */ import java.awt.Graphics2D;
/*     */ import java.awt.geom.AffineTransform;
/*     */ import java.awt.geom.Rectangle2D.Double;
/*     */ import java.awt.image.BufferedImage;
/*     */ import java.io.BufferedOutputStream;
/*     */ import java.io.File;
/*     */ import java.io.FileOutputStream;
/*     */ import java.io.IOException;
/*     */ import java.io.OutputStream;
/*     */ import java.io.PrintWriter;
/*     */ import org.jfree.chart.encoders.EncoderUtil;
/*     */ import org.jfree.chart.imagemap.ImageMapUtilities;
/*     */ import org.jfree.chart.imagemap.OverLIBToolTipTagFragmentGenerator;
/*     */ import org.jfree.chart.imagemap.StandardToolTipTagFragmentGenerator;
/*     */ import org.jfree.chart.imagemap.StandardURLTagFragmentGenerator;
/*     */ import org.jfree.chart.imagemap.ToolTipTagFragmentGenerator;
/*     */ import org.jfree.chart.imagemap.URLTagFragmentGenerator;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public abstract class ChartUtilities
/*     */ {
/*     */   public static void applyCurrentTheme(JFreeChart chart)
/*     */   {
/* 119 */     ChartFactory.getChartTheme().apply(chart);
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
/*     */   public static void writeChartAsPNG(OutputStream out, JFreeChart chart, int width, int height)
/*     */     throws IOException
/*     */   {
/* 136 */     writeChartAsPNG(out, chart, width, height, null);
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
/*     */   public static void writeChartAsPNG(OutputStream out, JFreeChart chart, int width, int height, boolean encodeAlpha, int compression)
/*     */     throws IOException
/*     */   {
/* 157 */     writeChartAsPNG(out, chart, width, height, null, encodeAlpha, compression);
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
/*     */   public static void writeChartAsPNG(OutputStream out, JFreeChart chart, int width, int height, ChartRenderingInfo info)
/*     */     throws IOException
/*     */   {
/* 180 */     if (chart == null) {
/* 181 */       throw new IllegalArgumentException("Null 'chart' argument.");
/*     */     }
/* 183 */     BufferedImage bufferedImage = chart.createBufferedImage(width, height, info);
/*     */     
/* 185 */     EncoderUtil.writeBufferedImage(bufferedImage, "png", out);
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
/*     */   public static void writeChartAsPNG(OutputStream out, JFreeChart chart, int width, int height, ChartRenderingInfo info, boolean encodeAlpha, int compression)
/*     */     throws IOException
/*     */   {
/* 209 */     if (out == null) {
/* 210 */       throw new IllegalArgumentException("Null 'out' argument.");
/*     */     }
/* 212 */     if (chart == null) {
/* 213 */       throw new IllegalArgumentException("Null 'chart' argument.");
/*     */     }
/* 215 */     BufferedImage chartImage = chart.createBufferedImage(width, height, 2, info);
/*     */     
/* 217 */     writeBufferedImageAsPNG(out, chartImage, encodeAlpha, compression);
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
/*     */   public static void writeScaledChartAsPNG(OutputStream out, JFreeChart chart, int width, int height, int widthScaleFactor, int heightScaleFactor)
/*     */     throws IOException
/*     */   {
/* 238 */     if (out == null) {
/* 239 */       throw new IllegalArgumentException("Null 'out' argument.");
/*     */     }
/* 241 */     if (chart == null) {
/* 242 */       throw new IllegalArgumentException("Null 'chart' argument.");
/*     */     }
/*     */     
/* 245 */     double desiredWidth = width * widthScaleFactor;
/* 246 */     double desiredHeight = height * heightScaleFactor;
/* 247 */     double defaultWidth = width;
/* 248 */     double defaultHeight = height;
/* 249 */     boolean scale = false;
/*     */     
/*     */ 
/* 252 */     if ((widthScaleFactor != 1) || (heightScaleFactor != 1)) {
/* 253 */       scale = true;
/*     */     }
/*     */     
/* 256 */     double scaleX = desiredWidth / defaultWidth;
/* 257 */     double scaleY = desiredHeight / defaultHeight;
/*     */     
/* 259 */     BufferedImage image = new BufferedImage((int)desiredWidth, (int)desiredHeight, 2);
/*     */     
/* 261 */     Graphics2D g2 = image.createGraphics();
/*     */     
/* 263 */     if (scale) {
/* 264 */       AffineTransform saved = g2.getTransform();
/* 265 */       g2.transform(AffineTransform.getScaleInstance(scaleX, scaleY));
/* 266 */       chart.draw(g2, new Rectangle2D.Double(0.0D, 0.0D, defaultWidth, defaultHeight), null, null);
/*     */       
/* 268 */       g2.setTransform(saved);
/* 269 */       g2.dispose();
/*     */     }
/*     */     else {
/* 272 */       chart.draw(g2, new Rectangle2D.Double(0.0D, 0.0D, defaultWidth, defaultHeight), null, null);
/*     */     }
/*     */     
/* 275 */     out.write(encodeAsPNG(image));
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
/*     */   public static void saveChartAsPNG(File file, JFreeChart chart, int width, int height)
/*     */     throws IOException
/*     */   {
/* 293 */     saveChartAsPNG(file, chart, width, height, null);
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
/*     */   public static void saveChartAsPNG(File file, JFreeChart chart, int width, int height, ChartRenderingInfo info)
/*     */     throws IOException
/*     */   {
/* 315 */     if (file == null) {
/* 316 */       throw new IllegalArgumentException("Null 'file' argument.");
/*     */     }
/* 318 */     OutputStream out = new BufferedOutputStream(new FileOutputStream(file));
/*     */     try {
/* 320 */       writeChartAsPNG(out, chart, width, height, info);
/*     */     }
/*     */     finally {
/* 323 */       out.close();
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
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public static void saveChartAsPNG(File file, JFreeChart chart, int width, int height, ChartRenderingInfo info, boolean encodeAlpha, int compression)
/*     */     throws IOException
/*     */   {
/* 347 */     if (file == null) {
/* 348 */       throw new IllegalArgumentException("Null 'file' argument.");
/*     */     }
/* 350 */     if (chart == null) {
/* 351 */       throw new IllegalArgumentException("Null 'chart' argument.");
/*     */     }
/*     */     
/* 354 */     OutputStream out = new BufferedOutputStream(new FileOutputStream(file));
/*     */     try {
/* 356 */       writeChartAsPNG(out, chart, width, height, info, encodeAlpha, compression);
/*     */     }
/*     */     finally
/*     */     {
/* 360 */       out.close();
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
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public static void writeChartAsJPEG(OutputStream out, JFreeChart chart, int width, int height)
/*     */     throws IOException
/*     */   {
/* 380 */     writeChartAsJPEG(out, chart, width, height, null);
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
/*     */   public static void writeChartAsJPEG(OutputStream out, float quality, JFreeChart chart, int width, int height)
/*     */     throws IOException
/*     */   {
/* 400 */     writeChartAsJPEG(out, quality, chart, width, height, null);
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
/*     */   public static void writeChartAsJPEG(OutputStream out, JFreeChart chart, int width, int height, ChartRenderingInfo info)
/*     */     throws IOException
/*     */   {
/* 423 */     if (chart == null) {
/* 424 */       throw new IllegalArgumentException("Null 'chart' argument.");
/*     */     }
/* 426 */     BufferedImage image = chart.createBufferedImage(width, height, 1, info);
/*     */     
/* 428 */     EncoderUtil.writeBufferedImage(image, "jpeg", out);
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
/*     */   public static void writeChartAsJPEG(OutputStream out, float quality, JFreeChart chart, int width, int height, ChartRenderingInfo info)
/*     */     throws IOException
/*     */   {
/* 451 */     if (chart == null) {
/* 452 */       throw new IllegalArgumentException("Null 'chart' argument.");
/*     */     }
/* 454 */     BufferedImage image = chart.createBufferedImage(width, height, 1, info);
/*     */     
/* 456 */     EncoderUtil.writeBufferedImage(image, "jpeg", out, quality);
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
/*     */   public static void saveChartAsJPEG(File file, JFreeChart chart, int width, int height)
/*     */     throws IOException
/*     */   {
/* 474 */     saveChartAsJPEG(file, chart, width, height, null);
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
/*     */   public static void saveChartAsJPEG(File file, float quality, JFreeChart chart, int width, int height)
/*     */     throws IOException
/*     */   {
/* 493 */     saveChartAsJPEG(file, quality, chart, width, height, null);
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
/*     */   public static void saveChartAsJPEG(File file, JFreeChart chart, int width, int height, ChartRenderingInfo info)
/*     */     throws IOException
/*     */   {
/* 514 */     if (file == null) {
/* 515 */       throw new IllegalArgumentException("Null 'file' argument.");
/*     */     }
/* 517 */     if (chart == null) {
/* 518 */       throw new IllegalArgumentException("Null 'chart' argument.");
/*     */     }
/* 520 */     OutputStream out = new BufferedOutputStream(new FileOutputStream(file));
/*     */     try {
/* 522 */       writeChartAsJPEG(out, chart, width, height, info);
/*     */     }
/*     */     finally {
/* 525 */       out.close();
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
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public static void saveChartAsJPEG(File file, float quality, JFreeChart chart, int width, int height, ChartRenderingInfo info)
/*     */     throws IOException
/*     */   {
/* 549 */     if (file == null) {
/* 550 */       throw new IllegalArgumentException("Null 'file' argument.");
/*     */     }
/* 552 */     if (chart == null) {
/* 553 */       throw new IllegalArgumentException("Null 'chart' argument.");
/*     */     }
/*     */     
/* 556 */     OutputStream out = new BufferedOutputStream(new FileOutputStream(file));
/*     */     try
/*     */     {
/* 559 */       writeChartAsJPEG(out, quality, chart, width, height, info);
/*     */     }
/*     */     finally {
/* 562 */       out.close();
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
/*     */ 
/*     */ 
/*     */ 
/*     */   public static void writeBufferedImageAsJPEG(OutputStream out, BufferedImage image)
/*     */     throws IOException
/*     */   {
/* 579 */     writeBufferedImageAsJPEG(out, 0.75F, image);
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
/*     */   public static void writeBufferedImageAsJPEG(OutputStream out, float quality, BufferedImage image)
/*     */     throws IOException
/*     */   {
/* 595 */     EncoderUtil.writeBufferedImage(image, "jpeg", out, quality);
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
/*     */   public static void writeBufferedImageAsPNG(OutputStream out, BufferedImage image)
/*     */     throws IOException
/*     */   {
/* 610 */     EncoderUtil.writeBufferedImage(image, "png", out);
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
/*     */   public static void writeBufferedImageAsPNG(OutputStream out, BufferedImage image, boolean encodeAlpha, int compression)
/*     */     throws IOException
/*     */   {
/* 628 */     EncoderUtil.writeBufferedImage(image, "png", out, compression, encodeAlpha);
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
/*     */   public static byte[] encodeAsPNG(BufferedImage image)
/*     */     throws IOException
/*     */   {
/* 642 */     return EncoderUtil.encode(image, "png");
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
/*     */   public static byte[] encodeAsPNG(BufferedImage image, boolean encodeAlpha, int compression)
/*     */     throws IOException
/*     */   {
/* 659 */     return EncoderUtil.encode(image, "png", compression, encodeAlpha);
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
/*     */   public static void writeImageMap(PrintWriter writer, String name, ChartRenderingInfo info, boolean useOverLibForToolTips)
/*     */     throws IOException
/*     */   {
/* 680 */     ToolTipTagFragmentGenerator toolTipTagFragmentGenerator = null;
/* 681 */     if (useOverLibForToolTips) {
/* 682 */       toolTipTagFragmentGenerator = new OverLIBToolTipTagFragmentGenerator();
/*     */     }
/*     */     else
/*     */     {
/* 686 */       toolTipTagFragmentGenerator = new StandardToolTipTagFragmentGenerator();
/*     */     }
/*     */     
/* 689 */     ImageMapUtilities.writeImageMap(writer, name, info, toolTipTagFragmentGenerator, new StandardURLTagFragmentGenerator());
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
/*     */   public static void writeImageMap(PrintWriter writer, String name, ChartRenderingInfo info, ToolTipTagFragmentGenerator toolTipTagFragmentGenerator, URLTagFragmentGenerator urlTagFragmentGenerator)
/*     */     throws IOException
/*     */   {
/* 716 */     writer.println(ImageMapUtilities.getImageMap(name, info, toolTipTagFragmentGenerator, urlTagFragmentGenerator));
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
/*     */   public static String getImageMap(String name, ChartRenderingInfo info)
/*     */   {
/* 732 */     return ImageMapUtilities.getImageMap(name, info, new StandardToolTipTagFragmentGenerator(), new StandardURLTagFragmentGenerator());
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
/*     */   public static String getImageMap(String name, ChartRenderingInfo info, ToolTipTagFragmentGenerator toolTipTagFragmentGenerator, URLTagFragmentGenerator urlTagFragmentGenerator)
/*     */   {
/* 757 */     return ImageMapUtilities.getImageMap(name, info, toolTipTagFragmentGenerator, urlTagFragmentGenerator);
/*     */   }
/*     */ }


/* Location:              /home/menick/Scrivania/ProcessMining/CNMining/CNMining.jar!/org/jfree/chart/ChartUtilities.class
 * Java compiler version: 3 (47.0)
 * JD-Core Version:       0.7.1
 */