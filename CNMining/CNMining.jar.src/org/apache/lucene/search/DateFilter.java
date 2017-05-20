/*     */ package org.apache.lucene.search;
/*     */ 
/*     */ import java.io.IOException;
/*     */ import java.util.BitSet;
/*     */ import java.util.Date;
/*     */ import org.apache.lucene.document.DateField;
/*     */ import org.apache.lucene.index.IndexReader;
/*     */ import org.apache.lucene.index.Term;
/*     */ import org.apache.lucene.index.TermDocs;
/*     */ import org.apache.lucene.index.TermEnum;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class DateFilter
/*     */   extends Filter
/*     */ {
/*     */   String field;
/*  38 */   String start = DateField.MIN_DATE_STRING();
/*  39 */   String end = DateField.MAX_DATE_STRING();
/*     */   
/*     */   private DateFilter(String f) {
/*  42 */     this.field = f;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   public DateFilter(String f, Date from, Date to)
/*     */   {
/*  50 */     this.field = f;
/*  51 */     this.start = DateField.dateToString(from);
/*  52 */     this.end = DateField.dateToString(to);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   public DateFilter(String f, long from, long to)
/*     */   {
/*  60 */     this.field = f;
/*  61 */     this.start = DateField.timeToString(from);
/*  62 */     this.end = DateField.timeToString(to);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   public static DateFilter Before(String field, Date date)
/*     */   {
/*  70 */     DateFilter result = new DateFilter(field);
/*  71 */     result.end = DateField.dateToString(date);
/*  72 */     return result;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   public static DateFilter Before(String field, long time)
/*     */   {
/*  80 */     DateFilter result = new DateFilter(field);
/*  81 */     result.end = DateField.timeToString(time);
/*  82 */     return result;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   public static DateFilter After(String field, Date date)
/*     */   {
/*  90 */     DateFilter result = new DateFilter(field);
/*  91 */     result.start = DateField.dateToString(date);
/*  92 */     return result;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   public static DateFilter After(String field, long time)
/*     */   {
/* 100 */     DateFilter result = new DateFilter(field);
/* 101 */     result.start = DateField.timeToString(time);
/* 102 */     return result;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   public BitSet bits(IndexReader reader)
/*     */     throws IOException
/*     */   {
/* 111 */     BitSet bits = new BitSet(reader.maxDoc());
/* 112 */     TermEnum enumerator = reader.terms(new Term(this.field, this.start));
/* 113 */     TermDocs termDocs = reader.termDocs();
/* 114 */     if (enumerator.term() == null) {
/* 115 */       return bits;
/*     */     }
/*     */     try
/*     */     {
/* 119 */       Term stop = new Term(this.field, this.end);
/* 120 */       while (enumerator.term().compareTo(stop) <= 0) {
/* 121 */         termDocs.seek(enumerator.term());
/* 122 */         while (termDocs.next()) {
/* 123 */           bits.set(termDocs.doc());
/*     */         }
/* 125 */         if (!enumerator.next()) {
/*     */           break;
/*     */         }
/*     */       }
/*     */     } finally {
/* 130 */       enumerator.close();
/* 131 */       termDocs.close();
/*     */     }
/* 133 */     return bits;
/*     */   }
/*     */   
/*     */   public String toString() {
/* 137 */     StringBuffer buffer = new StringBuffer();
/* 138 */     buffer.append(this.field);
/* 139 */     buffer.append(":");
/* 140 */     buffer.append(DateField.stringToDate(this.start).toString());
/* 141 */     buffer.append("-");
/* 142 */     buffer.append(DateField.stringToDate(this.end).toString());
/* 143 */     return buffer.toString();
/*     */   }
/*     */ }


/* Location:              /home/menick/Scrivania/ProcessMining/CNMining/CNMining.jar!/org/apache/lucene/search/DateFilter.class
 * Java compiler version: 2 (46.0)
 * JD-Core Version:       0.7.1
 */