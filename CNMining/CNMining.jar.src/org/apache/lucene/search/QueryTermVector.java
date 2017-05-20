/*     */ package org.apache.lucene.search;
/*     */ 
/*     */ import java.io.IOException;
/*     */ import java.io.StringReader;
/*     */ import java.util.ArrayList;
/*     */ import java.util.Arrays;
/*     */ import java.util.HashMap;
/*     */ import java.util.Iterator;
/*     */ import java.util.List;
/*     */ import java.util.Map;
/*     */ import org.apache.lucene.analysis.Analyzer;
/*     */ import org.apache.lucene.analysis.Token;
/*     */ import org.apache.lucene.analysis.TokenStream;
/*     */ import org.apache.lucene.index.TermFreqVector;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class QueryTermVector
/*     */   implements TermFreqVector
/*     */ {
/*  33 */   private String[] terms = new String[0];
/*  34 */   private int[] termFreqs = new int[0];
/*     */   
/*  36 */   public String getField() { return null; }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public QueryTermVector(String[] queryTerms)
/*     */   {
/*  44 */     processTerms(queryTerms);
/*     */   }
/*     */   
/*     */   public QueryTermVector(String queryString, Analyzer analyzer) {
/*  48 */     if (analyzer != null)
/*     */     {
/*  50 */       TokenStream stream = analyzer.tokenStream("", new StringReader(queryString));
/*  51 */       if (stream != null)
/*     */       {
/*  53 */         Token next = null;
/*  54 */         List terms = new ArrayList();
/*     */         try {
/*  56 */           while ((next = stream.next()) != null)
/*     */           {
/*  58 */             terms.add(next.termText());
/*     */           }
/*  60 */           processTerms((String[])terms.toArray(new String[terms.size()]));
/*     */         } catch (IOException e) {}
/*     */       }
/*     */     }
/*     */   }
/*     */   
/*     */   private void processTerms(String[] queryTerms) { int i;
/*     */     Iterator iter;
/*  68 */     if (queryTerms != null) {
/*  69 */       Arrays.sort(queryTerms);
/*  70 */       Map tmpSet = new HashMap(queryTerms.length);
/*     */       
/*  72 */       List tmpList = new ArrayList(queryTerms.length);
/*  73 */       List tmpFreqs = new ArrayList(queryTerms.length);
/*  74 */       int j = 0;
/*  75 */       for (int i = 0; i < queryTerms.length; i++) {
/*  76 */         String term = queryTerms[i];
/*  77 */         Integer position = (Integer)tmpSet.get(term);
/*  78 */         if (position == null) {
/*  79 */           tmpSet.put(term, new Integer(j++));
/*  80 */           tmpList.add(term);
/*  81 */           tmpFreqs.add(new Integer(1));
/*     */         }
/*     */         else {
/*  84 */           Integer integer = (Integer)tmpFreqs.get(position.intValue());
/*  85 */           tmpFreqs.set(position.intValue(), new Integer(integer.intValue() + 1));
/*     */         }
/*     */       }
/*  88 */       this.terms = ((String[])tmpList.toArray(this.terms));
/*     */       
/*  90 */       this.termFreqs = new int[tmpFreqs.size()];
/*  91 */       i = 0;
/*  92 */       for (iter = tmpFreqs.iterator(); iter.hasNext();) {
/*  93 */         Integer integer = (Integer)iter.next();
/*  94 */         this.termFreqs[(i++)] = integer.intValue();
/*     */       }
/*     */     }
/*     */   }
/*     */   
/*     */   public final String toString() {
/* 100 */     StringBuffer sb = new StringBuffer();
/* 101 */     sb.append('{');
/* 102 */     for (int i = 0; i < this.terms.length; i++) {
/* 103 */       if (i > 0) sb.append(", ");
/* 104 */       sb.append(this.terms[i]).append('/').append(this.termFreqs[i]);
/*     */     }
/* 106 */     sb.append('}');
/* 107 */     return sb.toString();
/*     */   }
/*     */   
/*     */   public int size()
/*     */   {
/* 112 */     return this.terms.length;
/*     */   }
/*     */   
/*     */   public String[] getTerms() {
/* 116 */     return this.terms;
/*     */   }
/*     */   
/*     */   public int[] getTermFrequencies() {
/* 120 */     return this.termFreqs;
/*     */   }
/*     */   
/*     */   public int indexOf(String term) {
/* 124 */     int res = Arrays.binarySearch(this.terms, term);
/* 125 */     return res >= 0 ? res : -1;
/*     */   }
/*     */   
/*     */   public int[] indexesOf(String[] terms, int start, int len) {
/* 129 */     int[] res = new int[len];
/*     */     
/* 131 */     for (int i = 0; i < len; i++) {
/* 132 */       res[i] = indexOf(terms[i]);
/*     */     }
/* 134 */     return res;
/*     */   }
/*     */ }


/* Location:              /home/menick/Scrivania/ProcessMining/CNMining/CNMining.jar!/org/apache/lucene/search/QueryTermVector.class
 * Java compiler version: 2 (46.0)
 * JD-Core Version:       0.7.1
 */