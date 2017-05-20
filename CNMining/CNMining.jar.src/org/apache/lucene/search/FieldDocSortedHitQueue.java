/*     */ package org.apache.lucene.search;
/*     */ 
/*     */ import java.io.IOException;
/*     */ import java.text.Collator;
/*     */ import java.util.Locale;
/*     */ import org.apache.lucene.util.PriorityQueue;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ class FieldDocSortedHitQueue
/*     */   extends PriorityQueue
/*     */ {
/*     */   volatile SortField[] fields;
/*     */   volatile Collator[] collators;
/*     */   
/*     */   FieldDocSortedHitQueue(SortField[] fields, int size)
/*     */     throws IOException
/*     */   {
/*  55 */     this.fields = fields;
/*  56 */     this.collators = hasCollators(fields);
/*  57 */     initialize(size);
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
/*     */   synchronized void setFields(SortField[] fields)
/*     */   {
/*  70 */     if (this.fields == null) {
/*  71 */       this.fields = fields;
/*  72 */       this.collators = hasCollators(fields);
/*     */     }
/*     */   }
/*     */   
/*     */ 
/*     */   SortField[] getFields()
/*     */   {
/*  79 */     return this.fields;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   private Collator[] hasCollators(SortField[] fields)
/*     */   {
/*  89 */     if (fields == null) return null;
/*  90 */     Collator[] ret = new Collator[fields.length];
/*  91 */     for (int i = 0; i < fields.length; i++) {
/*  92 */       Locale locale = fields[i].getLocale();
/*  93 */       if (locale != null)
/*  94 */         ret[i] = Collator.getInstance(locale);
/*     */     }
/*  96 */     return ret;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   protected final boolean lessThan(Object a, Object b)
/*     */   {
/* 107 */     FieldDoc docA = (FieldDoc)a;
/* 108 */     FieldDoc docB = (FieldDoc)b;
/* 109 */     int n = this.fields.length;
/* 110 */     int c = 0;
/* 111 */     for (int i = 0; (i < n) && (c == 0); i++) {
/* 112 */       int type = this.fields[i].getType();
/* 113 */       if (this.fields[i].getReverse()) {
/* 114 */         switch (type) {
/*     */         case 0: 
/* 116 */           float r1 = ((Float)docA.fields[i]).floatValue();
/* 117 */           float r2 = ((Float)docB.fields[i]).floatValue();
/* 118 */           if (r1 < r2) c = -1;
/* 119 */           if (r1 <= r2) continue; c = 1; break;
/*     */         
/*     */         case 1: 
/*     */         case 4: 
/* 123 */           int i1 = ((Integer)docA.fields[i]).intValue();
/* 124 */           int i2 = ((Integer)docB.fields[i]).intValue();
/* 125 */           if (i1 > i2) c = -1;
/* 126 */           if (i1 >= i2) continue; c = 1; break;
/*     */         
/*     */         case 3: 
/* 129 */           String s1 = (String)docA.fields[i];
/* 130 */           String s2 = (String)docB.fields[i];
/* 131 */           if (s2 == null) { c = -1;
/* 132 */           } else if (s1 == null) { c = 1;
/* 133 */           } else if (this.fields[i].getLocale() == null) {
/* 134 */             c = s2.compareTo(s1);
/*     */           } else {
/* 136 */             c = this.collators[i].compare(s2, s1);
/*     */           }
/* 138 */           break;
/*     */         case 5: 
/* 140 */           float f1 = ((Float)docA.fields[i]).floatValue();
/* 141 */           float f2 = ((Float)docB.fields[i]).floatValue();
/* 142 */           if (f1 > f2) c = -1;
/* 143 */           if (f1 >= f2) continue; c = 1; break;
/*     */         
/*     */         case 9: 
/* 146 */           c = docB.fields[i].compareTo(docA.fields[i]);
/* 147 */           break;
/*     */         
/*     */ 
/*     */ 
/*     */ 
/*     */         case 2: 
/* 153 */           throw new RuntimeException("FieldDocSortedHitQueue cannot use an AUTO SortField");
/*     */         case 6: case 7: case 8: default: 
/* 155 */           throw new RuntimeException("invalid SortField type: " + type);
/*     */         }
/*     */       } else {
/* 158 */         switch (type) {
/*     */         case 0: 
/* 160 */           float r1 = ((Float)docA.fields[i]).floatValue();
/* 161 */           float r2 = ((Float)docB.fields[i]).floatValue();
/* 162 */           if (r1 > r2) c = -1;
/* 163 */           if (r1 < r2) c = 1;
/*     */           break;
/*     */         case 1: 
/*     */         case 4: 
/* 167 */           int i1 = ((Integer)docA.fields[i]).intValue();
/* 168 */           int i2 = ((Integer)docB.fields[i]).intValue();
/* 169 */           if (i1 < i2) c = -1;
/* 170 */           if (i1 > i2) c = 1;
/*     */           break;
/*     */         case 3: 
/* 173 */           String s1 = (String)docA.fields[i];
/* 174 */           String s2 = (String)docB.fields[i];
/*     */           
/*     */ 
/*     */ 
/* 178 */           if (s1 == null) { c = -1;
/* 179 */           } else if (s2 == null) { c = 1;
/* 180 */           } else if (this.fields[i].getLocale() == null) {
/* 181 */             c = s1.compareTo(s2);
/*     */           } else {
/* 183 */             c = this.collators[i].compare(s1, s2);
/*     */           }
/* 185 */           break;
/*     */         case 5: 
/* 187 */           float f1 = ((Float)docA.fields[i]).floatValue();
/* 188 */           float f2 = ((Float)docB.fields[i]).floatValue();
/* 189 */           if (f1 < f2) c = -1;
/* 190 */           if (f1 > f2) c = 1;
/*     */           break;
/*     */         case 9: 
/* 193 */           c = docA.fields[i].compareTo(docB.fields[i]);
/* 194 */           break;
/*     */         
/*     */ 
/*     */ 
/*     */ 
/*     */         case 2: 
/* 200 */           throw new RuntimeException("FieldDocSortedHitQueue cannot use an AUTO SortField");
/*     */         case 6: case 7: case 8: default: 
/* 202 */           throw new RuntimeException("invalid SortField type: " + type);
/*     */         }
/*     */       }
/*     */     }
/* 206 */     return c > 0;
/*     */   }
/*     */ }


/* Location:              /home/menick/Scrivania/ProcessMining/CNMining/CNMining.jar!/org/apache/lucene/search/FieldDocSortedHitQueue.class
 * Java compiler version: 2 (46.0)
 * JD-Core Version:       0.7.1
 */