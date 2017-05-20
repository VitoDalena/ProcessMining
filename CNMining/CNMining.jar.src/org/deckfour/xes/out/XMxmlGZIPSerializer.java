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
/*     */ 
/*     */ 
/*     */ public class XMxmlGZIPSerializer
/*     */   extends XMxmlSerializer
/*     */ {
/*     */   public String getDescription()
/*     */   {
/*  62 */     return "Compressed MXML serialization (legacy)";
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */   public String getName()
/*     */   {
/*  69 */     return "MXML Compressed";
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */   public String getAuthor()
/*     */   {
/*  76 */     return "Christian W. Günther";
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */   public String[] getSuffices()
/*     */   {
/*  83 */     return new String[] { "mxml.gz" };
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */   public void serialize(XLog log, OutputStream out)
/*     */     throws IOException
/*     */   {
/*  91 */     GZIPOutputStream gzos = new GZIPOutputStream(out);
/*  92 */     BufferedOutputStream bos = new BufferedOutputStream(gzos);
/*  93 */     super.serialize(log, bos);
/*  94 */     bos.flush();
/*  95 */     gzos.flush();
/*  96 */     bos.close();
/*  97 */     gzos.close();
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */   public String toString()
/*     */   {
/* 104 */     return getName();
/*     */   }
/*     */ }


/* Location:              /home/menick/Scrivania/ProcessMining/CNMining/CNMining.jar!/org/deckfour/xes/out/XMxmlGZIPSerializer.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */