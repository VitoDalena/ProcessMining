/*     */ package org.apache.lucene.search;
/*     */ 
/*     */ import java.io.IOException;
/*     */ import java.util.HashMap;
/*     */ import java.util.Map;
/*     */ import java.util.WeakHashMap;
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
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ class FieldCacheImpl
/*     */   implements FieldCache
/*     */ {
/*     */   static class Entry
/*     */   {
/*     */     final String field;
/*     */     final int type;
/*     */     final Object custom;
/*     */     
/*     */     Entry(String field, int type)
/*     */     {
/*  50 */       this.field = field.intern();
/*  51 */       this.type = type;
/*  52 */       this.custom = null;
/*     */     }
/*     */     
/*     */     Entry(String field, Object custom)
/*     */     {
/*  57 */       this.field = field.intern();
/*  58 */       this.type = 9;
/*  59 */       this.custom = custom;
/*     */     }
/*     */     
/*     */     public boolean equals(Object o)
/*     */     {
/*  64 */       if ((o instanceof Entry)) {
/*  65 */         Entry other = (Entry)o;
/*  66 */         if ((other.field == this.field) && (other.type == this.type)) {
/*  67 */           if (other.custom == null) {
/*  68 */             if (this.custom == null) return true;
/*  69 */           } else if (other.custom.equals(this.custom)) {
/*  70 */             return true;
/*     */           }
/*     */         }
/*     */       }
/*  74 */       return false;
/*     */     }
/*     */     
/*     */     public int hashCode()
/*     */     {
/*  79 */       return this.field.hashCode() ^ this.type ^ (this.custom == null ? 0 : this.custom.hashCode());
/*     */     }
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*  85 */   final Map cache = new WeakHashMap();
/*     */   
/*     */   Object lookup(IndexReader reader, String field, int type)
/*     */   {
/*  89 */     Entry entry = new Entry(field, type);
/*  90 */     synchronized (this) {
/*  91 */       HashMap readerCache = (HashMap)this.cache.get(reader);
/*  92 */       if (readerCache == null) return null;
/*  93 */       return readerCache.get(entry);
/*     */     }
/*     */   }
/*     */   
/*     */   Object lookup(IndexReader reader, String field, Object comparer)
/*     */   {
/*  99 */     Entry entry = new Entry(field, comparer);
/* 100 */     synchronized (this) {
/* 101 */       HashMap readerCache = (HashMap)this.cache.get(reader);
/* 102 */       if (readerCache == null) return null;
/* 103 */       return readerCache.get(entry);
/*     */     }
/*     */   }
/*     */   
/*     */   Object store(IndexReader reader, String field, int type, Object value)
/*     */   {
/* 109 */     Entry entry = new Entry(field, type);
/* 110 */     synchronized (this) {
/* 111 */       HashMap readerCache = (HashMap)this.cache.get(reader);
/* 112 */       if (readerCache == null) {
/* 113 */         readerCache = new HashMap();
/* 114 */         this.cache.put(reader, readerCache);
/*     */       }
/* 116 */       return readerCache.put(entry, value);
/*     */     }
/*     */   }
/*     */   
/*     */   Object store(IndexReader reader, String field, Object comparer, Object value)
/*     */   {
/* 122 */     Entry entry = new Entry(field, comparer);
/* 123 */     synchronized (this) {
/* 124 */       HashMap readerCache = (HashMap)this.cache.get(reader);
/* 125 */       if (readerCache == null) {
/* 126 */         readerCache = new HashMap();
/* 127 */         this.cache.put(reader, readerCache);
/*     */       }
/* 129 */       return readerCache.put(entry, value);
/*     */     }
/*     */   }
/*     */   
/*     */   public int[] getInts(IndexReader reader, String field)
/*     */     throws IOException
/*     */   {
/* 136 */     field = field.intern();
/* 137 */     Object ret = lookup(reader, field, 4);
/* 138 */     if (ret == null) {
/* 139 */       int[] retArray = new int[reader.maxDoc()];
/* 140 */       if (retArray.length > 0) {
/* 141 */         TermDocs termDocs = reader.termDocs();
/* 142 */         TermEnum termEnum = reader.terms(new Term(field, ""));
/*     */         try {
/* 144 */           if (termEnum.term() == null) {
/* 145 */             throw new RuntimeException("no terms in field " + field);
/*     */           }
/*     */           do {
/* 148 */             Term term = termEnum.term();
/* 149 */             if (term.field() != field) break;
/* 150 */             int termval = Integer.parseInt(term.text());
/* 151 */             termDocs.seek(termEnum);
/* 152 */             while (termDocs.next()) {
/* 153 */               retArray[termDocs.doc()] = termval;
/*     */             }
/* 155 */           } while (termEnum.next());
/*     */         } finally {
/* 157 */           termDocs.close();
/* 158 */           termEnum.close();
/*     */         }
/*     */       }
/* 161 */       store(reader, field, 4, retArray);
/* 162 */       return retArray;
/*     */     }
/* 164 */     return (int[])ret;
/*     */   }
/*     */   
/*     */   public float[] getFloats(IndexReader reader, String field)
/*     */     throws IOException
/*     */   {
/* 170 */     field = field.intern();
/* 171 */     Object ret = lookup(reader, field, 5);
/* 172 */     if (ret == null) {
/* 173 */       float[] retArray = new float[reader.maxDoc()];
/* 174 */       if (retArray.length > 0) {
/* 175 */         TermDocs termDocs = reader.termDocs();
/* 176 */         TermEnum termEnum = reader.terms(new Term(field, ""));
/*     */         try {
/* 178 */           if (termEnum.term() == null) {
/* 179 */             throw new RuntimeException("no terms in field " + field);
/*     */           }
/*     */           do {
/* 182 */             Term term = termEnum.term();
/* 183 */             if (term.field() != field) break;
/* 184 */             float termval = Float.parseFloat(term.text());
/* 185 */             termDocs.seek(termEnum);
/* 186 */             while (termDocs.next()) {
/* 187 */               retArray[termDocs.doc()] = termval;
/*     */             }
/* 189 */           } while (termEnum.next());
/*     */         } finally {
/* 191 */           termDocs.close();
/* 192 */           termEnum.close();
/*     */         }
/*     */       }
/* 195 */       store(reader, field, 5, retArray);
/* 196 */       return retArray;
/*     */     }
/* 198 */     return (float[])ret;
/*     */   }
/*     */   
/*     */   public String[] getStrings(IndexReader reader, String field)
/*     */     throws IOException
/*     */   {
/* 204 */     field = field.intern();
/* 205 */     Object ret = lookup(reader, field, 3);
/* 206 */     if (ret == null) {
/* 207 */       String[] retArray = new String[reader.maxDoc()];
/* 208 */       if (retArray.length > 0) {
/* 209 */         TermDocs termDocs = reader.termDocs();
/* 210 */         TermEnum termEnum = reader.terms(new Term(field, ""));
/*     */         try {
/* 212 */           if (termEnum.term() == null) {
/* 213 */             throw new RuntimeException("no terms in field " + field);
/*     */           }
/*     */           do {
/* 216 */             Term term = termEnum.term();
/* 217 */             if (term.field() != field) break;
/* 218 */             String termval = term.text();
/* 219 */             termDocs.seek(termEnum);
/* 220 */             while (termDocs.next()) {
/* 221 */               retArray[termDocs.doc()] = termval;
/*     */             }
/* 223 */           } while (termEnum.next());
/*     */         } finally {
/* 225 */           termDocs.close();
/* 226 */           termEnum.close();
/*     */         }
/*     */       }
/* 229 */       store(reader, field, 3, retArray);
/* 230 */       return retArray;
/*     */     }
/* 232 */     return (String[])ret;
/*     */   }
/*     */   
/*     */   public FieldCache.StringIndex getStringIndex(IndexReader reader, String field)
/*     */     throws IOException
/*     */   {
/* 238 */     field = field.intern();
/* 239 */     Object ret = lookup(reader, field, -1);
/* 240 */     if (ret == null) {
/* 241 */       int[] retArray = new int[reader.maxDoc()];
/* 242 */       String[] mterms = new String[reader.maxDoc() + 1];
/* 243 */       if (retArray.length > 0) {
/* 244 */         TermDocs termDocs = reader.termDocs();
/* 245 */         TermEnum termEnum = reader.terms(new Term(field, ""));
/* 246 */         int t = 0;
/*     */         
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/* 252 */         mterms[(t++)] = null;
/*     */         try
/*     */         {
/* 255 */           if (termEnum.term() == null) {
/* 256 */             throw new RuntimeException("no terms in field " + field);
/*     */           }
/*     */           do {
/* 259 */             Term term = termEnum.term();
/* 260 */             if (term.field() != field) {
/*     */               break;
/*     */             }
/*     */             
/* 264 */             if (t >= mterms.length) throw new RuntimeException("there are more terms than documents in field \"" + field + "\"");
/* 265 */             mterms[t] = term.text();
/*     */             
/* 267 */             termDocs.seek(termEnum);
/* 268 */             while (termDocs.next()) {
/* 269 */               retArray[termDocs.doc()] = t;
/*     */             }
/*     */             
/* 272 */             t++;
/* 273 */           } while (termEnum.next());
/*     */         } finally {
/* 275 */           termDocs.close();
/* 276 */           termEnum.close();
/*     */         }
/*     */         
/* 279 */         if (t == 0)
/*     */         {
/*     */ 
/* 282 */           mterms = new String[1];
/* 283 */         } else if (t < mterms.length)
/*     */         {
/*     */ 
/* 286 */           String[] terms = new String[t];
/* 287 */           System.arraycopy(mterms, 0, terms, 0, t);
/* 288 */           mterms = terms;
/*     */         }
/*     */       }
/* 291 */       FieldCache.StringIndex value = new FieldCache.StringIndex(retArray, mterms);
/* 292 */       store(reader, field, -1, value);
/* 293 */       return value;
/*     */     }
/* 295 */     return (FieldCache.StringIndex)ret;
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
/*     */   public Object getAuto(IndexReader reader, String field)
/*     */     throws IOException
/*     */   {
/* 312 */     field = field.intern();
/* 313 */     Object ret = lookup(reader, field, 2);
/* 314 */     if (ret == null) {
/* 315 */       TermEnum enumerator = reader.terms(new Term(field, ""));
/*     */       try {
/* 317 */         Term term = enumerator.term();
/* 318 */         if (term == null) {
/* 319 */           throw new RuntimeException("no terms in field " + field + " - cannot determine sort type");
/*     */         }
/* 321 */         if (term.field() == field) {
/* 322 */           String termtext = term.text().trim();
/*     */           
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */           try
/*     */           {
/* 336 */             Integer.parseInt(termtext);
/* 337 */             ret = getInts(reader, field);
/*     */           } catch (NumberFormatException nfe1) {
/*     */             try {
/* 340 */               Float.parseFloat(termtext);
/* 341 */               ret = getFloats(reader, field);
/*     */             } catch (NumberFormatException nfe2) {
/* 343 */               ret = getStringIndex(reader, field);
/*     */             }
/*     */           }
/* 346 */           if (ret != null) {
/* 347 */             store(reader, field, 2, ret);
/*     */           }
/*     */         } else {
/* 350 */           throw new RuntimeException("field \"" + field + "\" does not appear to be indexed");
/*     */         }
/*     */       } finally {
/* 353 */         enumerator.close();
/*     */       }
/*     */     }
/*     */     
/* 357 */     return ret;
/*     */   }
/*     */   
/*     */   public Comparable[] getCustom(IndexReader reader, String field, SortComparator comparator)
/*     */     throws IOException
/*     */   {
/* 363 */     field = field.intern();
/* 364 */     Object ret = lookup(reader, field, comparator);
/* 365 */     if (ret == null) {
/* 366 */       Comparable[] retArray = new Comparable[reader.maxDoc()];
/* 367 */       if (retArray.length > 0) {
/* 368 */         TermDocs termDocs = reader.termDocs();
/* 369 */         TermEnum termEnum = reader.terms(new Term(field, ""));
/*     */         try {
/* 371 */           if (termEnum.term() == null) {
/* 372 */             throw new RuntimeException("no terms in field " + field);
/*     */           }
/*     */           do {
/* 375 */             Term term = termEnum.term();
/* 376 */             if (term.field() != field) break;
/* 377 */             Comparable termval = comparator.getComparable(term.text());
/* 378 */             termDocs.seek(termEnum);
/* 379 */             while (termDocs.next()) {
/* 380 */               retArray[termDocs.doc()] = termval;
/*     */             }
/* 382 */           } while (termEnum.next());
/*     */         } finally {
/* 384 */           termDocs.close();
/* 385 */           termEnum.close();
/*     */         }
/*     */       }
/* 388 */       store(reader, field, 9, retArray);
/* 389 */       return retArray;
/*     */     }
/* 391 */     return (Comparable[])ret;
/*     */   }
/*     */ }


/* Location:              /home/menick/Scrivania/ProcessMining/CNMining/CNMining.jar!/org/apache/lucene/search/FieldCacheImpl.class
 * Java compiler version: 2 (46.0)
 * JD-Core Version:       0.7.1
 */