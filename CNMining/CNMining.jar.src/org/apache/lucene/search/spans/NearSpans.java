/*     */ package org.apache.lucene.search.spans;
/*     */ 
/*     */ import java.io.IOException;
/*     */ import java.util.ArrayList;
/*     */ import java.util.List;
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
/*     */ class NearSpans
/*     */   implements Spans
/*     */ {
/*     */   private SpanNearQuery query;
/*  30 */   private List ordered = new ArrayList();
/*     */   
/*     */   private int slop;
/*     */   
/*     */   private boolean inOrder;
/*     */   
/*     */   private SpansCell first;
/*     */   
/*     */   private SpansCell last;
/*     */   private int totalLength;
/*     */   private CellQueue queue;
/*     */   private SpansCell max;
/*  42 */   private boolean more = true;
/*  43 */   private boolean firstTime = true;
/*     */   
/*     */   private class CellQueue extends PriorityQueue {
/*     */     public CellQueue(int size) {
/*  47 */       initialize(size);
/*     */     }
/*     */     
/*     */     protected final boolean lessThan(Object o1, Object o2) {
/*  51 */       NearSpans.SpansCell spans1 = (NearSpans.SpansCell)o1;
/*  52 */       NearSpans.SpansCell spans2 = (NearSpans.SpansCell)o2;
/*  53 */       if (spans1.doc() == spans2.doc()) {
/*  54 */         if (spans1.start() == spans2.start()) {
/*  55 */           if (spans1.end() == spans2.end()) {
/*  56 */             return spans1.index > spans2.index;
/*     */           }
/*  58 */           return spans1.end() < spans2.end();
/*     */         }
/*     */         
/*  61 */         return spans1.start() < spans2.start();
/*     */       }
/*     */       
/*  64 */       return spans1.doc() < spans2.doc();
/*     */     }
/*     */   }
/*     */   
/*     */ 
/*     */   private class SpansCell
/*     */     implements Spans
/*     */   {
/*     */     private Spans spans;
/*     */     private SpansCell next;
/*  74 */     private int length = -1;
/*     */     private int index;
/*     */     
/*     */     public SpansCell(Spans spans, int index) {
/*  78 */       this.spans = spans;
/*  79 */       this.index = index;
/*     */     }
/*     */     
/*     */     public boolean next() throws IOException {
/*  83 */       if (this.length != -1) {
/*  84 */         NearSpans.access$120(NearSpans.this, this.length);
/*     */       }
/*  86 */       boolean more = this.spans.next();
/*     */       
/*  88 */       if (more) {
/*  89 */         this.length = (end() - start());
/*  90 */         NearSpans.access$112(NearSpans.this, this.length);
/*     */         
/*  92 */         if ((NearSpans.this.max == null) || (doc() > NearSpans.this.max.doc()) || ((doc() == NearSpans.this.max.doc()) && (end() > NearSpans.this.max.end())))
/*     */         {
/*  94 */           NearSpans.this.max = this;
/*     */         }
/*     */       }
/*  97 */       return more;
/*     */     }
/*     */     
/*     */     public boolean skipTo(int target) throws IOException {
/* 101 */       if (this.length != -1) {
/* 102 */         NearSpans.access$120(NearSpans.this, this.length);
/*     */       }
/* 104 */       boolean more = this.spans.skipTo(target);
/*     */       
/* 106 */       if (more) {
/* 107 */         this.length = (end() - start());
/* 108 */         NearSpans.access$112(NearSpans.this, this.length);
/*     */         
/* 110 */         if ((NearSpans.this.max == null) || (doc() > NearSpans.this.max.doc()) || ((doc() == NearSpans.this.max.doc()) && (end() > NearSpans.this.max.end())))
/*     */         {
/* 112 */           NearSpans.this.max = this;
/*     */         }
/*     */       }
/* 115 */       return more;
/*     */     }
/*     */     
/* 118 */     public int doc() { return this.spans.doc(); }
/* 119 */     public int start() { return this.spans.start(); }
/* 120 */     public int end() { return this.spans.end(); }
/*     */     
/* 122 */     public String toString() { return this.spans.toString() + "#" + this.index; }
/*     */   }
/*     */   
/*     */   public NearSpans(SpanNearQuery query, IndexReader reader) throws IOException
/*     */   {
/* 127 */     this.query = query;
/* 128 */     this.slop = query.getSlop();
/* 129 */     this.inOrder = query.isInOrder();
/*     */     
/* 131 */     SpanQuery[] clauses = query.getClauses();
/* 132 */     this.queue = new CellQueue(clauses.length);
/* 133 */     for (int i = 0; i < clauses.length; i++) {
/* 134 */       SpansCell cell = new SpansCell(clauses[i].getSpans(reader), i);
/*     */       
/* 136 */       this.ordered.add(cell);
/*     */     }
/*     */   }
/*     */   
/*     */   public boolean next() throws IOException {
/* 141 */     if (this.firstTime) {
/* 142 */       initList(true);
/* 143 */       listToQueue();
/* 144 */       this.firstTime = false;
/* 145 */     } else if (this.more) {
/* 146 */       this.more = min().next();
/* 147 */       if (this.more) {
/* 148 */         this.queue.adjustTop();
/*     */       }
/*     */     }
/* 151 */     while (this.more)
/*     */     {
/* 153 */       boolean queueStale = false;
/*     */       
/* 155 */       if (min().doc() != this.max.doc()) {
/* 156 */         queueToList();
/* 157 */         queueStale = true;
/*     */       }
/*     */       
/*     */ 
/*     */ 
/* 162 */       while ((this.more) && (this.first.doc() < this.last.doc())) {
/* 163 */         this.more = this.first.skipTo(this.last.doc());
/* 164 */         firstToLast();
/* 165 */         queueStale = true;
/*     */       }
/*     */       
/* 168 */       if (!this.more) { return false;
/*     */       }
/*     */       
/*     */ 
/* 172 */       if (queueStale) {
/* 173 */         listToQueue();
/* 174 */         queueStale = false;
/*     */       }
/*     */       
/* 177 */       if (atMatch()) {
/* 178 */         return true;
/*     */       }
/*     */       
/* 181 */       if ((this.inOrder) && (checkSlop()))
/*     */       {
/* 183 */         this.more = firstNonOrderedNextToPartialList();
/* 184 */         if (this.more) {
/* 185 */           partialListToQueue();
/*     */         }
/*     */       } else {
/* 188 */         this.more = min().next();
/* 189 */         if (this.more) {
/* 190 */           this.queue.adjustTop();
/*     */         }
/*     */       }
/*     */     }
/* 194 */     return false;
/*     */   }
/*     */   
/*     */   public boolean skipTo(int target) throws IOException {
/* 198 */     if (this.firstTime) {
/* 199 */       initList(false);
/* 200 */       for (SpansCell cell = this.first; (this.more) && (cell != null); cell = cell.next) {
/* 201 */         this.more = cell.skipTo(target);
/*     */       }
/* 203 */       if (this.more) {
/* 204 */         listToQueue();
/*     */       }
/* 206 */       this.firstTime = false;
/*     */     } else {
/* 208 */       while ((this.more) && (min().doc() < target)) {
/* 209 */         this.more = min().skipTo(target);
/* 210 */         if (this.more)
/* 211 */           this.queue.adjustTop();
/*     */       }
/*     */     }
/* 214 */     if (this.more)
/*     */     {
/* 216 */       if (atMatch()) {
/* 217 */         return true;
/*     */       }
/* 219 */       return next();
/*     */     }
/*     */     
/* 222 */     return false;
/*     */   }
/*     */   
/* 225 */   private SpansCell min() { return (SpansCell)this.queue.top(); }
/*     */   
/* 227 */   public int doc() { return min().doc(); }
/* 228 */   public int start() { return min().start(); }
/* 229 */   public int end() { return this.max.end(); }
/*     */   
/*     */   public String toString()
/*     */   {
/* 233 */     return "spans(" + this.query.toString() + ")@" + (this.more ? doc() + ":" + start() + "-" + end() : this.firstTime ? "START" : "END");
/*     */   }
/*     */   
/*     */   private void initList(boolean next) throws IOException
/*     */   {
/* 238 */     for (int i = 0; (this.more) && (i < this.ordered.size()); i++) {
/* 239 */       SpansCell cell = (SpansCell)this.ordered.get(i);
/* 240 */       if (next)
/* 241 */         this.more = cell.next();
/* 242 */       if (this.more) {
/* 243 */         addToList(cell);
/*     */       }
/*     */     }
/*     */   }
/*     */   
/*     */   private void addToList(SpansCell cell) {
/* 249 */     if (this.last != null) {
/* 250 */       this.last.next = cell;
/*     */     } else
/* 252 */       this.first = cell;
/* 253 */     this.last = cell;
/* 254 */     cell.next = null;
/*     */   }
/*     */   
/*     */   private void firstToLast() {
/* 258 */     this.last.next = this.first;
/* 259 */     this.last = this.first;
/* 260 */     this.first = this.first.next;
/* 261 */     this.last.next = null;
/*     */   }
/*     */   
/*     */   private void queueToList() {
/* 265 */     this.last = (this.first = null);
/* 266 */     while (this.queue.top() != null) {
/* 267 */       addToList((SpansCell)this.queue.pop());
/*     */     }
/*     */   }
/*     */   
/*     */ 
/*     */   private boolean firstNonOrderedNextToPartialList()
/*     */     throws IOException
/*     */   {
/* 275 */     this.last = (this.first = null);
/* 276 */     int orderedIndex = 0;
/* 277 */     while (this.queue.top() != null) {
/* 278 */       SpansCell cell = (SpansCell)this.queue.pop();
/* 279 */       addToList(cell);
/* 280 */       if (cell.index == orderedIndex) {
/* 281 */         orderedIndex++;
/*     */       } else {
/* 283 */         return cell.next();
/*     */       }
/*     */     }
/*     */     
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/* 291 */     throw new RuntimeException("Unexpected: ordered");
/*     */   }
/*     */   
/*     */   private void listToQueue() {
/* 295 */     this.queue.clear();
/* 296 */     partialListToQueue();
/*     */   }
/*     */   
/*     */   private void partialListToQueue() {
/* 300 */     for (SpansCell cell = this.first; cell != null; cell = cell.next) {
/* 301 */       this.queue.put(cell);
/*     */     }
/*     */   }
/*     */   
/*     */   private boolean atMatch() {
/* 306 */     return (min().doc() == this.max.doc()) && (checkSlop()) && ((!this.inOrder) || (matchIsOrdered()));
/*     */   }
/*     */   
/*     */ 
/*     */   private boolean checkSlop()
/*     */   {
/* 312 */     int matchLength = this.max.end() - min().start();
/* 313 */     return matchLength - this.totalLength <= this.slop;
/*     */   }
/*     */   
/*     */   private boolean matchIsOrdered() {
/* 317 */     int lastStart = -1;
/* 318 */     for (int i = 0; i < this.ordered.size(); i++) {
/* 319 */       int start = ((SpansCell)this.ordered.get(i)).start();
/* 320 */       if (start <= lastStart)
/* 321 */         return false;
/* 322 */       lastStart = start;
/*     */     }
/* 324 */     return true;
/*     */   }
/*     */ }


/* Location:              /home/menick/Scrivania/ProcessMining/CNMining/CNMining.jar!/org/apache/lucene/search/spans/NearSpans.class
 * Java compiler version: 2 (46.0)
 * JD-Core Version:       0.7.1
 */