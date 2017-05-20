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
/*     */ public class XesXmlGZIPParser
/*     */   extends XesXmlParser
/*     */ {
/*     */   public XesXmlGZIPParser(XFactory factory)
/*     */   {
/*  65 */     super(factory);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public XesXmlGZIPParser() {}
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public String author()
/*     */   {
/*  82 */     return "Christian W. Günther";
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   public boolean canParse(File file)
/*     */   {
/*  90 */     String filename = file.getName();
/*  91 */     return (endsWithIgnoreCase(filename, ".xez")) || (endsWithIgnoreCase(filename, ".xes.gz"));
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
/*     */   public String description()
/*     */   {
/* 106 */     return "Reads XES models from compressed XML serializations";
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   public String name()
/*     */   {
/* 114 */     return "XES XML Compressed";
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   public List<XLog> parse(InputStream is)
/*     */     throws Exception
/*     */   {
/* 123 */     is = new GZIPInputStream(new BufferedInputStream(is));
/* 124 */     return super.parse(is);
/*     */   }
/*     */ }


/* Location:              /home/menick/Scrivania/ProcessMining/CNMining/CNMining.jar!/org/deckfour/xes/in/XesXmlGZIPParser.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */