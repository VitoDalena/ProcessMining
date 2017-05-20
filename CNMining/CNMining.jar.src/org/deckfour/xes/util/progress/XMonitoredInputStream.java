/*     */ package org.deckfour.xes.util.progress;
/*     */ 
/*     */ import java.io.BufferedInputStream;
/*     */ import java.io.File;
/*     */ import java.io.FileInputStream;
/*     */ import java.io.FileNotFoundException;
/*     */ import java.io.IOException;
/*     */ import java.io.InputStream;
/*     */ import javax.swing.JProgressBar;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class XMonitoredInputStream
/*     */   extends InputStream
/*     */ {
/*  66 */   protected int stepNumber = 1000;
/*     */   
/*     */ 
/*     */ 
/*     */   protected long stepSize;
/*     */   
/*     */ 
/*     */ 
/*     */   protected int lastStep;
/*     */   
/*     */ 
/*     */ 
/*  78 */   protected long bytesRead = 0L;
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   protected InputStream stream;
/*     */   
/*     */ 
/*     */ 
/*     */   protected XProgressListener progressListener;
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   public XMonitoredInputStream(File file, XProgressListener progressListener)
/*     */     throws FileNotFoundException
/*     */   {
/*  95 */     this(new BufferedInputStream(new FileInputStream(file)), file.length(), progressListener);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public XMonitoredInputStream(InputStream stream, long size, JProgressBar progressBar)
/*     */   {
/* 106 */     this(stream, size, new XProgressBarListener(progressBar), 1000);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public XMonitoredInputStream(InputStream stream, long size, XProgressListener progressListener)
/*     */   {
/* 117 */     this(stream, size, progressListener, 1000);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public XMonitoredInputStream(InputStream stream, long size, XProgressListener progressListener, int stepNumber)
/*     */   {
/* 129 */     this.progressListener = progressListener;
/* 130 */     this.stream = stream;
/* 131 */     this.stepNumber = stepNumber;
/*     */     
/* 133 */     this.stepSize = Math.max(size / stepNumber, 1L);
/* 134 */     this.lastStep = 0;
/* 135 */     this.bytesRead = 0L;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   protected void update(long readBytes)
/*     */     throws IOException
/*     */   {
/* 147 */     if (this.progressListener.isAborted()) {
/* 148 */       throw new IOException("Reading Cancelled by ProgressListener");
/*     */     }
/* 150 */     this.bytesRead += readBytes;
/* 151 */     int step = (int)(this.bytesRead / this.stepSize);
/* 152 */     if (step > this.lastStep) {
/* 153 */       this.lastStep = step;
/* 154 */       this.progressListener.updateProgress(step, this.stepNumber);
/*     */     }
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public int getStepNumber()
/*     */   {
/* 164 */     return this.stepNumber;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */   public int read()
/*     */     throws IOException
/*     */   {
/* 172 */     int result = this.stream.read();
/* 173 */     update(1L);
/* 174 */     return result;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */   public int read(byte[] b, int off, int len)
/*     */     throws IOException
/*     */   {
/* 182 */     int result = this.stream.read(b, off, len);
/* 183 */     update(result);
/* 184 */     return result;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */   public int read(byte[] b)
/*     */     throws IOException
/*     */   {
/* 192 */     int result = this.stream.read(b);
/* 193 */     update(result);
/* 194 */     return result;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */   public long skip(long n)
/*     */     throws IOException
/*     */   {
/* 202 */     long result = this.stream.skip(n);
/* 203 */     update(result);
/* 204 */     return result;
/*     */   }
/*     */ }


/* Location:              /home/menick/Scrivania/ProcessMining/CNMining/CNMining.jar!/org/deckfour/xes/util/progress/XMonitoredInputStream.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */