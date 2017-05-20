/*     */ package org.deckfour.xes.out;
/*     */ 
/*     */ import java.io.BufferedOutputStream;
/*     */ import java.io.IOException;
/*     */ import java.io.OutputStream;
/*     */ import java.util.zip.GZIPOutputStream;
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
/*     */ public class XesXmlGZIPSerializer
/*     */   extends XesXmlSerializer
/*     */ {
/*     */   public String getDescription()
/*     */   {
/*  60 */     return "XES XML Compressed Serialization";
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */   public String getName()
/*     */   {
/*  67 */     return "XES XML Compressed";
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */   public String getAuthor()
/*     */   {
/*  74 */     return "Christian W. Günther";
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */   public String[] getSuffices()
/*     */   {
/*  81 */     return new String[] { "xez", "xes.gz" };
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */   public void serialize(XLog log, OutputStream out)
/*     */     throws IOException
/*     */   {
/*  89 */     GZIPOutputStream gzos = new GZIPOutputStream(out);
/*  90 */     BufferedOutputStream bos = new BufferedOutputStream(gzos);
/*  91 */     super.serialize(log, bos);
/*  92 */     bos.flush();
/*  93 */     gzos.flush();
/*  94 */     bos.close();
/*  95 */     gzos.close();
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */   public String toString()
/*     */   {
/* 102 */     return getName();
/*     */   }
/*     */ }


/* Location:              /home/menick/Scrivania/ProcessMining/CNMining/CNMining.jar!/org/deckfour/xes/out/XesXmlGZIPSerializer.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */