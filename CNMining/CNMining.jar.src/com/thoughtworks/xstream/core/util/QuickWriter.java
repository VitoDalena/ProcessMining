/*     */ package com.thoughtworks.xstream.core.util;
/*     */ 
/*     */ import com.thoughtworks.xstream.io.StreamException;
/*     */ import java.io.IOException;
/*     */ import java.io.Writer;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class QuickWriter
/*     */ {
/*     */   private final Writer writer;
/*     */   private char[] buffer;
/*     */   private int pointer;
/*     */   
/*     */   public QuickWriter(Writer writer)
/*     */   {
/*  26 */     this(writer, 1024);
/*     */   }
/*     */   
/*     */   public QuickWriter(Writer writer, int bufferSize) {
/*  30 */     this.writer = writer;
/*  31 */     this.buffer = new char[bufferSize];
/*     */   }
/*     */   
/*     */   public void write(String str) {
/*  35 */     int len = str.length();
/*  36 */     if (this.pointer + len >= this.buffer.length) {
/*  37 */       flush();
/*  38 */       if (len > this.buffer.length) {
/*  39 */         raw(str.toCharArray());
/*  40 */         return;
/*     */       }
/*     */     }
/*  43 */     str.getChars(0, len, this.buffer, this.pointer);
/*  44 */     this.pointer += len;
/*     */   }
/*     */   
/*     */   public void write(char c) {
/*  48 */     if (this.pointer + 1 >= this.buffer.length) {
/*  49 */       flush();
/*  50 */       if (this.buffer.length == 0) {
/*  51 */         raw(c);
/*  52 */         return;
/*     */       }
/*     */     }
/*  55 */     this.buffer[(this.pointer++)] = c;
/*     */   }
/*     */   
/*     */   public void write(char[] c) {
/*  59 */     int len = c.length;
/*  60 */     if (this.pointer + len >= this.buffer.length) {
/*  61 */       flush();
/*  62 */       if (len > this.buffer.length) {
/*  63 */         raw(c);
/*  64 */         return;
/*     */       }
/*     */     }
/*  67 */     System.arraycopy(c, 0, this.buffer, this.pointer, len);
/*  68 */     this.pointer += len;
/*     */   }
/*     */   
/*     */   public void flush() {
/*     */     try {
/*  73 */       this.writer.write(this.buffer, 0, this.pointer);
/*  74 */       this.pointer = 0;
/*  75 */       this.writer.flush();
/*     */     } catch (IOException e) {
/*  77 */       throw new StreamException(e);
/*     */     }
/*     */   }
/*     */   
/*     */   public void close() {
/*     */     try {
/*  83 */       this.writer.write(this.buffer, 0, this.pointer);
/*  84 */       this.pointer = 0;
/*  85 */       this.writer.close();
/*     */     } catch (IOException e) {
/*  87 */       throw new StreamException(e);
/*     */     }
/*     */   }
/*     */   
/*     */   private void raw(char[] c) {
/*     */     try {
/*  93 */       this.writer.write(c);
/*  94 */       this.writer.flush();
/*     */     } catch (IOException e) {
/*  96 */       throw new StreamException(e);
/*     */     }
/*     */   }
/*     */   
/*     */   private void raw(char c) {
/*     */     try {
/* 102 */       this.writer.write(c);
/* 103 */       this.writer.flush();
/*     */     } catch (IOException e) {
/* 105 */       throw new StreamException(e);
/*     */     }
/*     */   }
/*     */ }


/* Location:              /home/menick/Scrivania/ProcessMining/CNMining/CNMining.jar!/com/thoughtworks/xstream/core/util/QuickWriter.class
 * Java compiler version: 3 (47.0)
 * JD-Core Version:       0.7.1
 */