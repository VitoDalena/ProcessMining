/*     */ package org.apache.lucene.analysis.de;
/*     */ 
/*     */ import java.io.File;
/*     */ import java.io.FileReader;
/*     */ import java.io.IOException;
/*     */ import java.io.LineNumberReader;
/*     */ import java.util.HashSet;
/*     */ import java.util.Hashtable;
/*     */ import java.util.Iterator;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class WordlistLoader
/*     */ {
/*     */   public static HashSet getWordSet(File wordfile)
/*     */     throws IOException
/*     */   {
/*  47 */     HashSet result = new HashSet();
/*  48 */     FileReader freader = null;
/*  49 */     LineNumberReader lnr = null;
/*     */     try {
/*  51 */       freader = new FileReader(wordfile);
/*  52 */       lnr = new LineNumberReader(freader);
/*  53 */       String word = null;
/*  54 */       while ((word = lnr.readLine()) != null) {
/*  55 */         result.add(word.trim());
/*     */       }
/*     */     }
/*     */     finally {
/*  59 */       if (lnr != null)
/*  60 */         lnr.close();
/*  61 */       if (freader != null)
/*  62 */         freader.close();
/*     */     }
/*  64 */     return result;
/*     */   }
/*     */   
/*     */ 
/*     */   /**
/*     */    * @deprecated
/*     */    */
/*     */   public static Hashtable getWordtable(String path, String wordfile)
/*     */     throws IOException
/*     */   {
/*  74 */     return getWordtable(new File(path, wordfile));
/*     */   }
/*     */   
/*     */   /**
/*     */    * @deprecated
/*     */    */
/*     */   public static Hashtable getWordtable(String wordfile)
/*     */     throws IOException
/*     */   {
/*  83 */     return getWordtable(new File(wordfile));
/*     */   }
/*     */   
/*     */   /**
/*     */    * @deprecated
/*     */    */
/*     */   public static Hashtable getWordtable(File wordfile)
/*     */     throws IOException
/*     */   {
/*  92 */     HashSet wordSet = getWordSet(wordfile);
/*  93 */     Hashtable result = makeWordTable(wordSet);
/*  94 */     return result;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   private static Hashtable makeWordTable(HashSet wordSet)
/*     */   {
/* 104 */     Hashtable table = new Hashtable();
/* 105 */     for (Iterator iter = wordSet.iterator(); iter.hasNext();) {
/* 106 */       String word = (String)iter.next();
/* 107 */       table.put(word, word);
/*     */     }
/* 109 */     return table;
/*     */   }
/*     */ }


/* Location:              /home/menick/Scrivania/ProcessMining/CNMining/CNMining.jar!/org/apache/lucene/analysis/de/WordlistLoader.class
 * Java compiler version: 2 (46.0)
 * JD-Core Version:       0.7.1
 */