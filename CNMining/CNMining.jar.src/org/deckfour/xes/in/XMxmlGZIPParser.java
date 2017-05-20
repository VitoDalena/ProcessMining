/*     */ package org.deckfour.xes.in;
/*     */ 
/*     */ import java.io.BufferedInputStream;
/*     */ import java.io.File;
/*     */ import java.io.InputStream;
/*     */ import java.util.List;
/*     */ import java.util.zip.GZIPInputStream;
/*     */ import org.deckfour.xes.factory.XFactory;
/*     */ import org.deckfour.xes.model.XLog;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class XMxmlGZIPParser
/*     */   extends XMxmlParser
/*     */ {
/*     */   public XMxmlGZIPParser(XFactory factory)
/*     */   {
/*  65 */     super(factory);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public XMxmlGZIPParser() {}
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public String author()
/*     */   {
/*  81 */     return "Christian W. Günther";
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   public boolean canParse(File file)
/*     */   {
/*  89 */     String filename = file.getName();
/*  90 */     return endsWithIgnoreCase(filename, ".mxml.gz");
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public String description()
/*     */   {
/* 100 */     return "Reads XES models from legacy compressed MXML serializations";
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   public String name()
/*     */   {
/* 108 */     return "MXML Compressed";
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   public List<XLog> parse(InputStream is)
/*     */     throws Exception
/*     */   {
/* 117 */     is = new GZIPInputStream(new BufferedInputStream(is));
/* 118 */     return super.parse(is);
/*     */   }
/*     */ }


/* Location:              /home/menick/Scrivania/ProcessMining/CNMining/CNMining.jar!/org/deckfour/xes/in/XMxmlGZIPParser.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */