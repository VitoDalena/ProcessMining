/*     */ package org.jfree.chart.servlet;
/*     */ 
/*     */ import java.io.BufferedInputStream;
/*     */ import java.io.BufferedOutputStream;
/*     */ import java.io.File;
/*     */ import java.io.FileInputStream;
/*     */ import java.io.FileNotFoundException;
/*     */ import java.io.IOException;
/*     */ import java.io.PrintStream;
/*     */ import java.text.SimpleDateFormat;
/*     */ import java.util.Date;
/*     */ import java.util.Locale;
/*     */ import java.util.TimeZone;
/*     */ import javax.servlet.http.HttpServletResponse;
/*     */ import javax.servlet.http.HttpSession;
/*     */ import org.jfree.chart.ChartRenderingInfo;
/*     */ import org.jfree.chart.ChartUtilities;
/*     */ import org.jfree.chart.JFreeChart;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class ServletUtilities
/*     */ {
/*  83 */   private static String tempFilePrefix = "jfreechart-";
/*     */   
/*     */ 
/*  86 */   private static String tempOneTimeFilePrefix = "jfreechart-onetime-";
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public static String getTempFilePrefix()
/*     */   {
/*  94 */     return tempFilePrefix;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public static void setTempFilePrefix(String prefix)
/*     */   {
/* 103 */     if (prefix == null) {
/* 104 */       throw new IllegalArgumentException("Null 'prefix' argument.");
/*     */     }
/* 106 */     tempFilePrefix = prefix;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public static String getTempOneTimeFilePrefix()
/*     */   {
/* 116 */     return tempOneTimeFilePrefix;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public static void setTempOneTimeFilePrefix(String prefix)
/*     */   {
/* 126 */     if (prefix == null) {
/* 127 */       throw new IllegalArgumentException("Null 'prefix' argument.");
/*     */     }
/* 129 */     tempOneTimeFilePrefix = prefix;
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
/*     */   public static String saveChartAsPNG(JFreeChart chart, int width, int height, HttpSession session)
/*     */     throws IOException
/*     */   {
/* 150 */     return saveChartAsPNG(chart, width, height, null, session);
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
/*     */   public static String saveChartAsPNG(JFreeChart chart, int width, int height, ChartRenderingInfo info, HttpSession session)
/*     */     throws IOException
/*     */   {
/* 177 */     if (chart == null) {
/* 178 */       throw new IllegalArgumentException("Null 'chart' argument.");
/*     */     }
/* 180 */     createTempDir();
/* 181 */     String prefix = tempFilePrefix;
/* 182 */     if (session == null) {
/* 183 */       prefix = tempOneTimeFilePrefix;
/*     */     }
/* 185 */     File tempFile = File.createTempFile(prefix, ".png", new File(System.getProperty("java.io.tmpdir")));
/*     */     
/* 187 */     ChartUtilities.saveChartAsPNG(tempFile, chart, width, height, info);
/* 188 */     if (session != null) {
/* 189 */       registerChartForDeletion(tempFile, session);
/*     */     }
/* 191 */     return tempFile.getName();
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
/*     */   public static String saveChartAsJPEG(JFreeChart chart, int width, int height, HttpSession session)
/*     */     throws IOException
/*     */   {
/* 219 */     return saveChartAsJPEG(chart, width, height, null, session);
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
/*     */ 
/*     */ 
/*     */ 
/*     */   public static String saveChartAsJPEG(JFreeChart chart, int width, int height, ChartRenderingInfo info, HttpSession session)
/*     */     throws IOException
/*     */   {
/* 251 */     if (chart == null) {
/* 252 */       throw new IllegalArgumentException("Null 'chart' argument.");
/*     */     }
/*     */     
/* 255 */     createTempDir();
/* 256 */     String prefix = tempFilePrefix;
/* 257 */     if (session == null) {
/* 258 */       prefix = tempOneTimeFilePrefix;
/*     */     }
/* 260 */     File tempFile = File.createTempFile(prefix, ".jpeg", new File(System.getProperty("java.io.tmpdir")));
/*     */     
/* 262 */     ChartUtilities.saveChartAsJPEG(tempFile, chart, width, height, info);
/* 263 */     if (session != null) {
/* 264 */       registerChartForDeletion(tempFile, session);
/*     */     }
/* 266 */     return tempFile.getName();
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
/*     */   protected static void createTempDir()
/*     */   {
/* 280 */     String tempDirName = System.getProperty("java.io.tmpdir");
/* 281 */     if (tempDirName == null) {
/* 282 */       throw new RuntimeException("Temporary directory system property (java.io.tmpdir) is null.");
/*     */     }
/*     */     
/*     */ 
/*     */ 
/* 287 */     File tempDir = new File(tempDirName);
/* 288 */     if (!tempDir.exists()) {
/* 289 */       tempDir.mkdirs();
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
/*     */   protected static void registerChartForDeletion(File tempFile, HttpSession session)
/*     */   {
/* 305 */     if (session != null) {
/* 306 */       ChartDeleter chartDeleter = (ChartDeleter)session.getAttribute("JFreeChart_Deleter");
/*     */       
/* 308 */       if (chartDeleter == null) {
/* 309 */         chartDeleter = new ChartDeleter();
/* 310 */         session.setAttribute("JFreeChart_Deleter", chartDeleter);
/*     */       }
/* 312 */       chartDeleter.addChart(tempFile.getName());
/*     */     }
/*     */     else {
/* 315 */       System.out.println("Session is null - chart will not be deleted");
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
/*     */   public static void sendTempFile(String filename, HttpServletResponse response)
/*     */     throws IOException
/*     */   {
/* 331 */     File file = new File(System.getProperty("java.io.tmpdir"), filename);
/* 332 */     sendTempFile(file, response);
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
/*     */   public static void sendTempFile(File file, HttpServletResponse response)
/*     */     throws IOException
/*     */   {
/* 346 */     String mimeType = null;
/* 347 */     String filename = file.getName();
/* 348 */     if (filename.length() > 5) {
/* 349 */       if (filename.substring(filename.length() - 5, filename.length()).equals(".jpeg"))
/*     */       {
/* 351 */         mimeType = "image/jpeg";
/*     */       }
/* 353 */       else if (filename.substring(filename.length() - 4, filename.length()).equals(".png"))
/*     */       {
/* 355 */         mimeType = "image/png";
/*     */       }
/*     */     }
/* 358 */     sendTempFile(file, response, mimeType);
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
/*     */   public static void sendTempFile(File file, HttpServletResponse response, String mimeType)
/*     */     throws IOException
/*     */   {
/* 373 */     if (file.exists()) {
/* 374 */       BufferedInputStream bis = new BufferedInputStream(new FileInputStream(file));
/*     */       
/*     */ 
/*     */ 
/* 378 */       if (mimeType != null) {
/* 379 */         response.setHeader("Content-Type", mimeType);
/*     */       }
/* 381 */       response.setHeader("Content-Length", String.valueOf(file.length()));
/* 382 */       SimpleDateFormat sdf = new SimpleDateFormat("EEE, dd MMM yyyy HH:mm:ss z", Locale.ENGLISH);
/*     */       
/* 384 */       sdf.setTimeZone(TimeZone.getTimeZone("GMT"));
/* 385 */       response.setHeader("Last-Modified", sdf.format(new Date(file.lastModified())));
/*     */       
/*     */ 
/* 388 */       BufferedOutputStream bos = new BufferedOutputStream(response.getOutputStream());
/*     */       
/* 390 */       byte[] input = new byte['Ѐ'];
/* 391 */       boolean eof = false;
/* 392 */       while (!eof) {
/* 393 */         int length = bis.read(input);
/* 394 */         if (length == -1) {
/* 395 */           eof = true;
/*     */         }
/*     */         else {
/* 398 */           bos.write(input, 0, length);
/*     */         }
/*     */       }
/* 401 */       bos.flush();
/* 402 */       bis.close();
/* 403 */       bos.close();
/*     */     }
/*     */     else {
/* 406 */       throw new FileNotFoundException(file.getAbsolutePath());
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
/*     */   public static String searchReplace(String inputString, String searchString, String replaceString)
/*     */   {
/* 425 */     int i = inputString.indexOf(searchString);
/* 426 */     if (i == -1) {
/* 427 */       return inputString;
/*     */     }
/*     */     
/* 430 */     String r = "";
/* 431 */     r = r + inputString.substring(0, i) + replaceString;
/* 432 */     if (i + searchString.length() < inputString.length()) {
/* 433 */       r = r + searchReplace(inputString.substring(i + searchString.length()), searchString, replaceString);
/*     */     }
/*     */     
/*     */ 
/* 437 */     return r;
/*     */   }
/*     */ }


/* Location:              /home/menick/Scrivania/ProcessMining/CNMining/CNMining.jar!/org/jfree/chart/servlet/ServletUtilities.class
 * Java compiler version: 3 (47.0)
 * JD-Core Version:       0.7.1
 */