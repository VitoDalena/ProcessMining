/*     */ package org.deckfour.spex;
/*     */ 
/*     */ import java.io.BufferedOutputStream;
/*     */ import java.io.File;
/*     */ import java.io.FileOutputStream;
/*     */ import java.io.IOException;
/*     */ import java.io.OutputStream;
/*     */ import java.io.OutputStreamWriter;
/*     */ import java.io.Writer;
/*     */ import java.nio.charset.Charset;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class SXDocument
/*     */   extends SXNode
/*     */ {
/*  63 */   protected String tabString = "\t";
/*     */   
/*     */ 
/*     */ 
/*  67 */   protected SXNode lastChildNode = null;
/*     */   
/*     */ 
/*     */ 
/*  71 */   protected boolean isOpen = true;
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public SXDocument(Writer writer, Charset charset)
/*     */     throws IOException
/*     */   {
/*  81 */     super(writer, 0, "\t");
/*  82 */     this.lastChildNode = null;
/*  83 */     synchronized (this) {
/*  84 */       this.isOpen = true;
/*  85 */       writer.write("<?xml version=\"1.0\" encoding=\"" + charset.name() + "\" ?>");
/*     */     }
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public SXDocument(OutputStream aStream, Charset aCharset)
/*     */     throws IOException
/*     */   {
/*  96 */     super(new OutputStreamWriter(aStream, aCharset), 0, "\t");
/*  97 */     this.lastChildNode = null;
/*  98 */     synchronized (this) {
/*  99 */       this.isOpen = true;
/* 100 */       this.writer.write("<?xml version=\"1.0\" encoding=\"" + aCharset.name() + "\" ?>");
/*     */     }
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public SXDocument(OutputStream aStream, String aCharsetName)
/*     */     throws IOException
/*     */   {
/* 111 */     this(aStream, Charset.forName(aCharsetName));
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   public SXDocument(OutputStream aStream)
/*     */     throws IOException
/*     */   {
/* 120 */     this(aStream, Charset.forName("UTF-8"));
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public SXDocument(File aFile)
/*     */     throws IOException
/*     */   {
/* 130 */     this(new BufferedOutputStream(new FileOutputStream(aFile)), Charset.forName("UTF-8"));
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   protected void prepareToAddChildNode()
/*     */     throws IOException
/*     */   {
/* 140 */     if (!this.isOpen) {
/* 141 */       throw new IOException("Attempting to write to a closed document!");
/*     */     }
/*     */     
/* 144 */     if (this.lastChildNode != null) {
/* 145 */       this.lastChildNode.close();
/* 146 */       this.lastChildNode = null;
/*     */     }
/* 148 */     this.writer.write("\n");
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public SXTag addNode(String tagName)
/*     */     throws IOException
/*     */   {
/* 159 */     prepareToAddChildNode();
/* 160 */     SXTag node = new SXTag(tagName, this.writer, 0, this.tabString);
/* 161 */     this.lastChildNode = node;
/* 162 */     return node;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public void addComment(String comment)
/*     */     throws IOException
/*     */   {
/* 172 */     prepareToAddChildNode();
/* 173 */     SXCommentNode commentNode = new SXCommentNode(comment, this.writer, 0, this.tabString);
/* 174 */     this.lastChildNode = commentNode;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public void close()
/*     */     throws IOException
/*     */   {
/* 184 */     if (this.lastChildNode != null) {
/* 185 */       this.lastChildNode.close();
/* 186 */       this.lastChildNode = null;
/*     */     }
/* 188 */     this.writer.write("\n");
/* 189 */     this.writer.flush();
/* 190 */     this.isOpen = false;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public void setTabString(String aTabString)
/*     */   {
/* 199 */     this.tabString = aTabString;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public String getTabString()
/*     */   {
/* 208 */     return this.tabString;
/*     */   }
/*     */ }


/* Location:              /home/menick/Scrivania/ProcessMining/CNMining/CNMining.jar!/org/deckfour/spex/SXDocument.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       0.7.1
 */