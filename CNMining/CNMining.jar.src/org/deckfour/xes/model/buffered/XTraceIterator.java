/*     */ package org.deckfour.xes.model.buffered;
/*     */ 
/*     */ import java.util.ListIterator;
/*     */ import java.util.NoSuchElementException;
/*     */ import org.deckfour.xes.model.XEvent;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class XTraceIterator
/*     */   implements ListIterator<XEvent>
/*     */ {
/*  59 */   protected XTraceBufferedImpl list = null;
/*     */   
/*     */ 
/*     */ 
/*  63 */   protected int position = 0;
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public XTraceIterator(XTraceBufferedImpl aList)
/*     */   {
/*  73 */     this(aList, 0);
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
/*     */   public XTraceIterator(XTraceBufferedImpl aList, int aPosition)
/*     */   {
/*  86 */     this.list = aList;
/*  87 */     this.position = aPosition;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public boolean hasNext()
/*     */   {
/*  96 */     return this.position < this.list.size();
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public XEvent next()
/*     */   {
/* 105 */     XEvent result = null;
/*     */     try {
/* 107 */       result = this.list.get(this.position);
/*     */     } catch (IndexOutOfBoundsException e) {
/* 109 */       throw new NoSuchElementException("There is no next event in this trace");
/*     */     } finally {
/* 111 */       this.position += 1;
/*     */     }
/* 113 */     return result;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public void remove()
/*     */   {
/* 122 */     this.position -= 1;
/* 123 */     this.list.remove(this.position);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public void add(XEvent o)
/*     */   {
/* 132 */     this.list.add(this.position, o);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public boolean hasPrevious()
/*     */   {
/* 141 */     return this.position > 1;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public int nextIndex()
/*     */   {
/* 150 */     return this.position;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public XEvent previous()
/*     */   {
/* 159 */     this.position -= 1;
/* 160 */     return this.list.get(this.position);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public int previousIndex()
/*     */   {
/* 169 */     return this.position - 1;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public void set(XEvent o)
/*     */   {
/* 178 */     this.list.remove(this.position);
/* 179 */     this.list.add(this.position, o);
/*     */   }
/*     */ }


/* Location:              /home/menick/Scrivania/ProcessMining/CNMining/CNMining.jar!/org/deckfour/xes/model/buffered/XTraceIterator.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */