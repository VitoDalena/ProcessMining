/*     */ package org.apache.lucene.search;
/*     */ 
/*     */ import java.io.IOException;
/*     */ import java.text.Collator;
/*     */ import java.util.HashMap;
/*     */ import java.util.Locale;
/*     */ import java.util.Map;
/*     */ import java.util.WeakHashMap;
/*     */ import org.apache.lucene.index.IndexReader;
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
/*     */ class FieldSortedHitQueue
/*     */   extends PriorityQueue
/*     */ {
/*     */   protected ScoreDocComparator[] comparators;
/*     */   protected SortField[] fields;
/*     */   
/*     */   FieldSortedHitQueue(IndexReader reader, SortField[] fields, int size)
/*     */     throws IOException
/*     */   {
/*  53 */     int n = fields.length;
/*  54 */     this.comparators = new ScoreDocComparator[n];
/*  55 */     this.fields = new SortField[n];
/*  56 */     for (int i = 0; i < n; i++) {
/*  57 */       String fieldname = fields[i].getField();
/*  58 */       this.comparators[i] = getCachedComparator(reader, fieldname, fields[i].getType(), fields[i].getLocale(), fields[i].getFactory());
/*  59 */       this.fields[i] = new SortField(fieldname, this.comparators[i].sortType(), fields[i].getReverse());
/*     */     }
/*  61 */     initialize(size);
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
/*  74 */   protected float maxscore = 1.0F;
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   protected final boolean lessThan(Object a, Object b)
/*     */   {
/*  84 */     ScoreDoc docA = (ScoreDoc)a;
/*  85 */     ScoreDoc docB = (ScoreDoc)b;
/*     */     
/*     */ 
/*  88 */     if (docA.score > this.maxscore) this.maxscore = docA.score;
/*  89 */     if (docB.score > this.maxscore) { this.maxscore = docB.score;
/*     */     }
/*     */     
/*  92 */     int n = this.comparators.length;
/*  93 */     int c = 0;
/*  94 */     for (int i = 0; (i < n) && (c == 0); i++) {
/*  95 */       c = this.fields[i].reverse ? this.comparators[i].compare(docB, docA) : this.comparators[i].compare(docA, docB);
/*     */     }
/*     */     
/*     */ 
/*  99 */     if (c == 0)
/* 100 */       return docA.doc > docB.doc;
/* 101 */     return c > 0;
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
/*     */   FieldDoc fillFields(FieldDoc doc)
/*     */   {
/* 116 */     int n = this.comparators.length;
/* 117 */     Comparable[] fields = new Comparable[n];
/* 118 */     for (int i = 0; i < n; i++)
/* 119 */       fields[i] = this.comparators[i].sortValue(doc);
/* 120 */     doc.fields = fields;
/* 121 */     if (this.maxscore > 1.0F) doc.score /= this.maxscore;
/* 122 */     return doc;
/*     */   }
/*     */   
/*     */ 
/*     */   SortField[] getFields()
/*     */   {
/* 128 */     return this.fields;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/* 133 */   static final Map Comparators = new WeakHashMap();
/*     */   
/*     */   static ScoreDocComparator lookup(IndexReader reader, String field, int type, Object factory)
/*     */   {
/* 137 */     FieldCacheImpl.Entry entry = factory != null ? new FieldCacheImpl.Entry(field, factory) : new FieldCacheImpl.Entry(field, type);
/*     */     
/*     */ 
/* 140 */     synchronized (Comparators) {
/* 141 */       HashMap readerCache = (HashMap)Comparators.get(reader);
/* 142 */       if (readerCache == null) return null;
/* 143 */       return (ScoreDocComparator)readerCache.get(entry);
/*     */     }
/*     */   }
/*     */   
/*     */   static Object store(IndexReader reader, String field, int type, Object factory, Object value)
/*     */   {
/* 149 */     FieldCacheImpl.Entry entry = factory != null ? new FieldCacheImpl.Entry(field, factory) : new FieldCacheImpl.Entry(field, type);
/*     */     
/*     */ 
/* 152 */     synchronized (Comparators) {
/* 153 */       HashMap readerCache = (HashMap)Comparators.get(reader);
/* 154 */       if (readerCache == null) {
/* 155 */         readerCache = new HashMap();
/* 156 */         Comparators.put(reader, readerCache);
/*     */       }
/* 158 */       return readerCache.put(entry, value);
/*     */     }
/*     */   }
/*     */   
/*     */   static ScoreDocComparator getCachedComparator(IndexReader reader, String fieldname, int type, Locale locale, SortComparatorSource factory) throws IOException
/*     */   {
/* 164 */     if (type == 1) return ScoreDocComparator.INDEXORDER;
/* 165 */     if (type == 0) return ScoreDocComparator.RELEVANCE;
/* 166 */     ScoreDocComparator comparator = lookup(reader, fieldname, type, factory);
/* 167 */     if (comparator == null) {
/* 168 */       switch (type) {
/*     */       case 2: 
/* 170 */         comparator = comparatorAuto(reader, fieldname);
/* 171 */         break;
/*     */       case 4: 
/* 173 */         comparator = comparatorInt(reader, fieldname);
/* 174 */         break;
/*     */       case 5: 
/* 176 */         comparator = comparatorFloat(reader, fieldname);
/* 177 */         break;
/*     */       case 3: 
/* 179 */         if (locale != null) comparator = comparatorStringLocale(reader, fieldname, locale); else
/* 180 */           comparator = comparatorString(reader, fieldname);
/* 181 */         break;
/*     */       case 9: 
/* 183 */         comparator = factory.newComparator(reader, fieldname);
/* 184 */         break;
/*     */       case 6: case 7: case 8: default: 
/* 186 */         throw new RuntimeException("unknown field type: " + type);
/*     */       }
/* 188 */       store(reader, fieldname, type, factory, comparator);
/*     */     }
/* 190 */     return comparator;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   static ScoreDocComparator comparatorInt(IndexReader reader, String fieldname)
/*     */     throws IOException
/*     */   {
/* 202 */     String field = fieldname.intern();
/* 203 */     int[] fieldOrder = FieldCache.DEFAULT.getInts(reader, field);
/* 204 */     new ScoreDocComparator() {
/*     */       private final int[] val$fieldOrder;
/*     */       
/* 207 */       public final int compare(ScoreDoc i, ScoreDoc j) { int fi = this.val$fieldOrder[i.doc];
/* 208 */         int fj = this.val$fieldOrder[j.doc];
/* 209 */         if (fi < fj) return -1;
/* 210 */         if (fi > fj) return 1;
/* 211 */         return 0;
/*     */       }
/*     */       
/*     */       public Comparable sortValue(ScoreDoc i) {
/* 215 */         return new Integer(this.val$fieldOrder[i.doc]);
/*     */       }
/*     */       
/*     */       public int sortType() {
/* 219 */         return 4;
/*     */       }
/*     */     };
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   static ScoreDocComparator comparatorFloat(IndexReader reader, String fieldname)
/*     */     throws IOException
/*     */   {
/* 233 */     String field = fieldname.intern();
/* 234 */     float[] fieldOrder = FieldCache.DEFAULT.getFloats(reader, field);
/* 235 */     new ScoreDocComparator() {
/*     */       private final float[] val$fieldOrder;
/*     */       
/* 238 */       public final int compare(ScoreDoc i, ScoreDoc j) { float fi = this.val$fieldOrder[i.doc];
/* 239 */         float fj = this.val$fieldOrder[j.doc];
/* 240 */         if (fi < fj) return -1;
/* 241 */         if (fi > fj) return 1;
/* 242 */         return 0;
/*     */       }
/*     */       
/*     */       public Comparable sortValue(ScoreDoc i) {
/* 246 */         return new Float(this.val$fieldOrder[i.doc]);
/*     */       }
/*     */       
/*     */       public int sortType() {
/* 250 */         return 5;
/*     */       }
/*     */     };
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   static ScoreDocComparator comparatorString(IndexReader reader, String fieldname)
/*     */     throws IOException
/*     */   {
/* 264 */     String field = fieldname.intern();
/* 265 */     FieldCache.StringIndex index = FieldCache.DEFAULT.getStringIndex(reader, field);
/* 266 */     new ScoreDocComparator() {
/*     */       private final FieldCache.StringIndex val$index;
/*     */       
/* 269 */       public final int compare(ScoreDoc i, ScoreDoc j) { int fi = this.val$index.order[i.doc];
/* 270 */         int fj = this.val$index.order[j.doc];
/* 271 */         if (fi < fj) return -1;
/* 272 */         if (fi > fj) return 1;
/* 273 */         return 0;
/*     */       }
/*     */       
/*     */       public Comparable sortValue(ScoreDoc i) {
/* 277 */         return this.val$index.lookup[this.val$index.order[i.doc]];
/*     */       }
/*     */       
/*     */       public int sortType() {
/* 281 */         return 3;
/*     */       }
/*     */     };
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   static ScoreDocComparator comparatorStringLocale(IndexReader reader, String fieldname, Locale locale)
/*     */     throws IOException
/*     */   {
/* 295 */     Collator collator = Collator.getInstance(locale);
/* 296 */     String field = fieldname.intern();
/* 297 */     String[] index = FieldCache.DEFAULT.getStrings(reader, field);
/* 298 */     new ScoreDocComparator() { private final Collator val$collator;
/*     */       private final String[] val$index;
/*     */       
/* 301 */       public final int compare(ScoreDoc i, ScoreDoc j) { return this.val$collator.compare(this.val$index[i.doc], this.val$index[j.doc]); }
/*     */       
/*     */       public Comparable sortValue(ScoreDoc i)
/*     */       {
/* 305 */         return this.val$index[i.doc];
/*     */       }
/*     */       
/*     */       public int sortType() {
/* 309 */         return 3;
/*     */       }
/*     */     };
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
/*     */   static ScoreDocComparator comparatorAuto(IndexReader reader, String fieldname)
/*     */     throws IOException
/*     */   {
/* 326 */     String field = fieldname.intern();
/* 327 */     Object lookupArray = FieldCache.DEFAULT.getAuto(reader, field);
/* 328 */     if ((lookupArray instanceof FieldCache.StringIndex))
/* 329 */       return comparatorString(reader, field);
/* 330 */     if ((lookupArray instanceof int[]))
/* 331 */       return comparatorInt(reader, field);
/* 332 */     if ((lookupArray instanceof float[]))
/* 333 */       return comparatorFloat(reader, field);
/* 334 */     if ((lookupArray instanceof String[])) {
/* 335 */       return comparatorString(reader, field);
/*     */     }
/* 337 */     throw new RuntimeException("unknown data type in field '" + field + "'");
/*     */   }
/*     */ }


/* Location:              /home/menick/Scrivania/ProcessMining/CNMining/CNMining.jar!/org/apache/lucene/search/FieldSortedHitQueue.class
 * Java compiler version: 2 (46.0)
 * JD-Core Version:       0.7.1
 */