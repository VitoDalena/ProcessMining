/*     */ package org.apache.commons.compress.archivers;
/*     */ 
/*     */ import java.io.IOException;
/*     */ import java.io.InputStream;
/*     */ import java.io.OutputStream;
/*     */ import org.apache.commons.compress.archivers.ar.ArArchiveInputStream;
/*     */ import org.apache.commons.compress.archivers.ar.ArArchiveOutputStream;
/*     */ import org.apache.commons.compress.archivers.cpio.CpioArchiveInputStream;
/*     */ import org.apache.commons.compress.archivers.cpio.CpioArchiveOutputStream;
/*     */ import org.apache.commons.compress.archivers.jar.JarArchiveInputStream;
/*     */ import org.apache.commons.compress.archivers.jar.JarArchiveOutputStream;
/*     */ import org.apache.commons.compress.archivers.tar.TarArchiveInputStream;
/*     */ import org.apache.commons.compress.archivers.tar.TarArchiveOutputStream;
/*     */ import org.apache.commons.compress.archivers.zip.ZipArchiveInputStream;
/*     */ import org.apache.commons.compress.archivers.zip.ZipArchiveOutputStream;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class ArchiveStreamFactory
/*     */ {
/*     */   public ArchiveInputStream createArchiveInputStream(String archiverName, InputStream in)
/*     */     throws ArchiveException
/*     */   {
/*  86 */     if ((archiverName == null) || (in == null)) {
/*  87 */       throw new IllegalArgumentException("Archivername must not be null.");
/*     */     }
/*     */     
/*  90 */     if ("ar".equalsIgnoreCase(archiverName))
/*  91 */       return new ArArchiveInputStream(in);
/*  92 */     if ("zip".equalsIgnoreCase(archiverName))
/*  93 */       return new ZipArchiveInputStream(in);
/*  94 */     if ("tar".equalsIgnoreCase(archiverName))
/*  95 */       return new TarArchiveInputStream(in);
/*  96 */     if ("jar".equalsIgnoreCase(archiverName))
/*  97 */       return new JarArchiveInputStream(in);
/*  98 */     if ("cpio".equalsIgnoreCase(archiverName)) {
/*  99 */       return new CpioArchiveInputStream(in);
/*     */     }
/* 101 */     throw new ArchiveException("Archiver: " + archiverName + " not found.");
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
/*     */   public ArchiveOutputStream createArchiveOutputStream(String archiverName, OutputStream out)
/*     */     throws ArchiveException
/*     */   {
/* 116 */     if ((archiverName == null) || (out == null)) {
/* 117 */       throw new IllegalArgumentException("Archivername and stream must not be null.");
/*     */     }
/*     */     
/*     */ 
/* 121 */     if ("ar".equalsIgnoreCase(archiverName))
/* 122 */       return new ArArchiveOutputStream(out);
/* 123 */     if ("zip".equalsIgnoreCase(archiverName))
/* 124 */       return new ZipArchiveOutputStream(out);
/* 125 */     if ("tar".equalsIgnoreCase(archiverName))
/* 126 */       return new TarArchiveOutputStream(out);
/* 127 */     if ("jar".equalsIgnoreCase(archiverName))
/* 128 */       return new JarArchiveOutputStream(out);
/* 129 */     if ("cpio".equalsIgnoreCase(archiverName)) {
/* 130 */       return new CpioArchiveOutputStream(out);
/*     */     }
/* 132 */     throw new ArchiveException("Archiver: " + archiverName + " not found.");
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
/*     */   public ArchiveInputStream createArchiveInputStream(InputStream in)
/*     */     throws ArchiveException
/*     */   {
/* 147 */     if (in == null) {
/* 148 */       throw new IllegalArgumentException("Stream must not be null.");
/*     */     }
/*     */     
/* 151 */     if (!in.markSupported()) {
/* 152 */       throw new IllegalArgumentException("Mark is not supported.");
/*     */     }
/*     */     
/* 155 */     byte[] signature = new byte[12];
/* 156 */     in.mark(signature.length);
/*     */     try {
/* 158 */       int signatureLength = in.read(signature);
/* 159 */       in.reset();
/* 160 */       if (ZipArchiveInputStream.matches(signature, signatureLength))
/* 161 */         return new ZipArchiveInputStream(in);
/* 162 */       if (JarArchiveInputStream.matches(signature, signatureLength))
/* 163 */         return new JarArchiveInputStream(in);
/* 164 */       if (ArArchiveInputStream.matches(signature, signatureLength))
/* 165 */         return new ArArchiveInputStream(in);
/* 166 */       if (CpioArchiveInputStream.matches(signature, signatureLength)) {
/* 167 */         return new CpioArchiveInputStream(in);
/*     */       }
/*     */       
/* 170 */       byte[] tarheader = new byte['Ȁ'];
/* 171 */       in.mark(tarheader.length);
/* 172 */       signatureLength = in.read(tarheader);
/* 173 */       in.reset();
/* 174 */       if (TarArchiveInputStream.matches(tarheader, signatureLength)) {
/* 175 */         return new TarArchiveInputStream(in);
/*     */       }
/*     */     } catch (IOException e) {
/* 178 */       throw new ArchiveException("Could not use reset and mark operations.", e);
/*     */     }
/*     */     
/* 181 */     throw new ArchiveException("No Archiver found for the stream signature");
/*     */   }
/*     */ }


/* Location:              /home/menick/Scrivania/ProcessMining/CNMining/CNMining.jar!/org/apache/commons/compress/archivers/ArchiveStreamFactory.class
 * Java compiler version: 4 (48.0)
 * JD-Core Version:       0.7.1
 */