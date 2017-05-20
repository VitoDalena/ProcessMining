/*     */ package org.apache.lucene.index;
/*     */ 
/*     */ import java.io.IOException;
/*     */ import java.util.Arrays;
/*     */ import java.util.Iterator;
/*     */ import java.util.LinkedList;
/*     */ import java.util.List;
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
/*     */ public class MultipleTermPositions
/*     */   implements TermPositions
/*     */ {
/*     */   private int _doc;
/*     */   private int _freq;
/*     */   private TermPositionsQueue _termPositionsQueue;
/*     */   private IntQueue _posList;
/*     */   
/*     */   private static final class TermPositionsQueue
/*     */     extends PriorityQueue
/*     */   {
/*     */     TermPositionsQueue(List termPositions)
/*     */       throws IOException
/*     */     {
/*  43 */       initialize(termPositions.size());
/*     */       
/*  45 */       Iterator i = termPositions.iterator();
/*  46 */       while (i.hasNext())
/*     */       {
/*  48 */         TermPositions tp = (TermPositions)i.next();
/*  49 */         if (tp.next()) {
/*  50 */           put(tp);
/*     */         }
/*     */       }
/*     */     }
/*     */     
/*     */     final TermPositions peek() {
/*  56 */       return (TermPositions)top();
/*     */     }
/*     */     
/*     */ 
/*     */ 
/*  61 */     public final boolean lessThan(Object a, Object b) { return ((TermPositions)a).doc() < ((TermPositions)b).doc(); }
/*     */   }
/*     */   
/*     */   private static final class IntQueue {
/*  65 */     IntQueue(MultipleTermPositions.1 x0) { this(); }
/*     */     
/*  67 */     private int _arraySize = 16;
/*     */     
/*  69 */     private int _index = 0;
/*  70 */     private int _lastIndex = 0;
/*     */     
/*  72 */     private int[] _array = new int[this._arraySize];
/*     */     
/*     */     final void add(int i)
/*     */     {
/*  76 */       if (this._lastIndex == this._arraySize) {
/*  77 */         growArray();
/*     */       }
/*  79 */       this._array[(this._lastIndex++)] = i;
/*     */     }
/*     */     
/*     */     final int next()
/*     */     {
/*  84 */       return this._array[(this._index++)];
/*     */     }
/*     */     
/*     */     final void sort()
/*     */     {
/*  89 */       Arrays.sort(this._array, this._index, this._lastIndex);
/*     */     }
/*     */     
/*     */     final void clear()
/*     */     {
/*  94 */       this._index = 0;
/*  95 */       this._lastIndex = 0;
/*     */     }
/*     */     
/*     */     final int size()
/*     */     {
/* 100 */       return this._lastIndex - this._index;
/*     */     }
/*     */     
/*     */     private void growArray()
/*     */     {
/* 105 */       int[] newArray = new int[this._arraySize * 2];
/* 106 */       System.arraycopy(this._array, 0, newArray, 0, this._arraySize);
/* 107 */       this._array = newArray;
/* 108 */       this._arraySize *= 2;
/*     */     }
/*     */     
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     private IntQueue() {}
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public MultipleTermPositions(IndexReader indexReader, Term[] terms)
/*     */     throws IOException
/*     */   {
/* 128 */     List termPositions = new LinkedList();
/*     */     
/* 130 */     for (int i = 0; i < terms.length; i++) {
/* 131 */       termPositions.add(indexReader.termPositions(terms[i]));
/*     */     }
/* 133 */     this._termPositionsQueue = new TermPositionsQueue(termPositions);
/* 134 */     this._posList = new IntQueue(null);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public final boolean next()
/*     */     throws IOException
/*     */   {
/* 147 */     if (this._termPositionsQueue.size() == 0) {
/* 148 */       return false;
/*     */     }
/* 150 */     this._posList.clear();
/* 151 */     this._doc = this._termPositionsQueue.peek().doc();
/*     */     
/*     */ 
/*     */     do
/*     */     {
/* 156 */       TermPositions tp = this._termPositionsQueue.peek();
/*     */       
/* 158 */       for (int i = 0; i < tp.freq(); i++) {
/* 159 */         this._posList.add(tp.nextPosition());
/*     */       }
/* 161 */       if (tp.next()) {
/* 162 */         this._termPositionsQueue.adjustTop();
/*     */       }
/*     */       else {
/* 165 */         this._termPositionsQueue.pop();
/* 166 */         tp.close();
/*     */       }
/*     */       
/* 169 */     } while ((this._termPositionsQueue.size() > 0) && (this._termPositionsQueue.peek().doc() == this._doc));
/*     */     
/* 171 */     this._posList.sort();
/* 172 */     this._freq = this._posList.size();
/*     */     
/* 174 */     return true;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public final int nextPosition()
/*     */     throws IOException
/*     */   {
/* 187 */     return this._posList.next();
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
/*     */   public final boolean skipTo(int target)
/*     */     throws IOException
/*     */   {
/* 201 */     while (target > this._termPositionsQueue.peek().doc())
/*     */     {
/* 203 */       TermPositions tp = (TermPositions)this._termPositionsQueue.pop();
/*     */       
/* 205 */       if (tp.skipTo(target)) {
/* 206 */         this._termPositionsQueue.put(tp);
/*     */       } else {
/* 208 */         tp.close();
/*     */       }
/*     */     }
/* 211 */     return next();
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public final int doc()
/*     */   {
/* 222 */     return this._doc;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public final int freq()
/*     */   {
/* 233 */     return this._freq;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public final void close()
/*     */     throws IOException
/*     */   {
/* 245 */     while (this._termPositionsQueue.size() > 0) {
/* 246 */       ((TermPositions)this._termPositionsQueue.pop()).close();
/*     */     }
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public void seek(Term arg0)
/*     */     throws IOException
/*     */   {
/* 259 */     throw new UnsupportedOperationException();
/*     */   }
/*     */   
/*     */   public void seek(TermEnum termEnum) throws IOException {
/* 263 */     throw new UnsupportedOperationException();
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
/*     */   public int read(int[] arg0, int[] arg1)
/*     */     throws IOException
/*     */   {
/* 279 */     throw new UnsupportedOperationException();
/*     */   }
/*     */ }


/* Location:              /home/menick/Scrivania/ProcessMining/CNMining/CNMining.jar!/org/apache/lucene/index/MultipleTermPositions.class
 * Java compiler version: 2 (46.0)
 * JD-Core Version:       0.7.1
 */