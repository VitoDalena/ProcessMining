/*     */ package org.deckfour.xes.in;
/*     */ 
/*     */ import java.io.File;
/*     */ import java.io.FileInputStream;
/*     */ import java.io.InputStream;
/*     */ import java.util.List;
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
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public abstract class XParser
/*     */ {
/*     */   public abstract String name();
/*     */   
/*     */   public abstract String description();
/*     */   
/*     */   public abstract String author();
/*     */   
/*     */   public abstract boolean canParse(File paramFile);
/*     */   
/*     */   public abstract List<XLog> parse(InputStream paramInputStream)
/*     */     throws Exception;
/*     */   
/*     */   public List<XLog> parse(File file)
/*     */     throws Exception
/*     */   {
/* 106 */     if (canParse(file)) {
/* 107 */       InputStream is = new FileInputStream(file);
/* 108 */       return parse(is);
/*     */     }
/* 110 */     throw new IllegalArgumentException("Parser cannot handle this file!");
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   public String toString()
/*     */   {
/* 118 */     return name();
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   protected boolean endsWithIgnoreCase(String name, String suffix)
/*     */   {
/* 128 */     if ((name == null) || (suffix == null))
/*     */     {
/*     */ 
/*     */ 
/* 132 */       return false;
/*     */     }
/* 134 */     int i = name.length() - suffix.length();
/* 135 */     if (i < 0)
/*     */     {
/*     */ 
/*     */ 
/* 139 */       return false;
/*     */     }
/* 141 */     return name.substring(i).equalsIgnoreCase(suffix);
/*     */   }
/*     */ }


/* Location:              /home/menick/Scrivania/ProcessMining/CNMining/CNMining.jar!/org/deckfour/xes/in/XParser.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */