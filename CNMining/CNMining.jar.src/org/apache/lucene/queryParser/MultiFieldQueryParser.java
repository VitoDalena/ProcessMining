/*     */ package org.apache.lucene.queryParser;
/*     */ 
/*     */ import org.apache.lucene.analysis.Analyzer;
/*     */ import org.apache.lucene.search.BooleanQuery;
/*     */ import org.apache.lucene.search.Query;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class MultiFieldQueryParser
/*     */   extends QueryParser
/*     */ {
/*     */   public static final int NORMAL_FIELD = 0;
/*     */   public static final int REQUIRED_FIELD = 1;
/*     */   public static final int PROHIBITED_FIELD = 2;
/*     */   
/*     */   public MultiFieldQueryParser(QueryParserTokenManager tm)
/*     */   {
/*  41 */     super(tm);
/*     */   }
/*     */   
/*     */   public MultiFieldQueryParser(CharStream stream)
/*     */   {
/*  46 */     super(stream);
/*     */   }
/*     */   
/*     */   public MultiFieldQueryParser(String f, Analyzer a)
/*     */   {
/*  51 */     super(f, a);
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
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public static Query parse(String query, String[] fields, Analyzer analyzer)
/*     */     throws ParseException
/*     */   {
/*  74 */     BooleanQuery bQuery = new BooleanQuery();
/*  75 */     for (int i = 0; i < fields.length; i++)
/*     */     {
/*  77 */       Query q = parse(query, fields[i], analyzer);
/*  78 */       bQuery.add(q, false, false);
/*     */     }
/*  80 */     return bQuery;
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
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public static Query parse(String query, String[] fields, int[] flags, Analyzer analyzer)
/*     */     throws ParseException
/*     */   {
/* 117 */     BooleanQuery bQuery = new BooleanQuery();
/* 118 */     for (int i = 0; i < fields.length; i++)
/*     */     {
/* 120 */       Query q = parse(query, fields[i], analyzer);
/* 121 */       int flag = flags[i];
/* 122 */       switch (flag)
/*     */       {
/*     */       case 1: 
/* 125 */         bQuery.add(q, true, false);
/* 126 */         break;
/*     */       case 2: 
/* 128 */         bQuery.add(q, false, true);
/* 129 */         break;
/*     */       default: 
/* 131 */         bQuery.add(q, false, false);
/*     */       }
/*     */       
/*     */     }
/* 135 */     return bQuery;
/*     */   }
/*     */ }


/* Location:              /home/menick/Scrivania/ProcessMining/CNMining/CNMining.jar!/org/apache/lucene/queryParser/MultiFieldQueryParser.class
 * Java compiler version: 2 (46.0)
 * JD-Core Version:       0.7.1
 */