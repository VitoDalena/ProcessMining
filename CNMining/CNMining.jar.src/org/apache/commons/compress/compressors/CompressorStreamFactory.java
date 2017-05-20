/*     */ package org.apache.commons.compress.compressors;
/*     */ 
/*     */ import java.io.IOException;
/*     */ import java.io.InputStream;
/*     */ import java.io.OutputStream;
/*     */ import org.apache.commons.compress.compressors.bzip2.BZip2CompressorInputStream;
/*     */ import org.apache.commons.compress.compressors.bzip2.BZip2CompressorOutputStream;
/*     */ import org.apache.commons.compress.compressors.gzip.GzipCompressorInputStream;
/*     */ import org.apache.commons.compress.compressors.gzip.GzipCompressorOutputStream;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class CompressorStreamFactory
/*     */ {
/*     */   public CompressorInputStream createCompressorInputStream(String name, InputStream in)
/*     */     throws CompressorException
/*     */   {
/*  69 */     if ((name == null) || (in == null)) {
/*  70 */       throw new IllegalArgumentException("Compressor name and stream must not be null.");
/*     */     }
/*     */     
/*     */     try
/*     */     {
/*  75 */       if ("gz".equalsIgnoreCase(name))
/*  76 */         return new GzipCompressorInputStream(in);
/*  77 */       if ("bzip2".equalsIgnoreCase(name)) {
/*  78 */         return new BZip2CompressorInputStream(in);
/*     */       }
/*     */     } catch (IOException e) {
/*  81 */       throw new CompressorException("Could not create CompressorInputStream", e);
/*     */     }
/*     */     
/*  84 */     throw new CompressorException("Compressor: " + name + " not found.");
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
/*     */   public CompressorOutputStream createCompressorOutputStream(String name, OutputStream out)
/*     */     throws CompressorException
/*     */   {
/*  99 */     if ((name == null) || (out == null)) {
/* 100 */       throw new IllegalArgumentException("Compressor name and stream must not be null.");
/*     */     }
/*     */     
/*     */     try
/*     */     {
/* 105 */       if ("gz".equalsIgnoreCase(name))
/* 106 */         return new GzipCompressorOutputStream(out);
/* 107 */       if ("bzip2".equalsIgnoreCase(name)) {
/* 108 */         return new BZip2CompressorOutputStream(out);
/*     */       }
/*     */     } catch (IOException e) {
/* 111 */       throw new CompressorException("Could not create CompressorOutputStream", e);
/*     */     }
/*     */     
/* 114 */     throw new CompressorException("Compressor: " + name + " not found.");
/*     */   }
/*     */ }


/* Location:              /home/menick/Scrivania/ProcessMining/CNMining/CNMining.jar!/org/apache/commons/compress/compressors/CompressorStreamFactory.class
 * Java compiler version: 4 (48.0)
 * JD-Core Version:       0.7.1
 */