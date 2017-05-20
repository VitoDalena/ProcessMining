/*     */ package org.deckfour.xes.model.buffered;
/*     */ 
/*     */ import java.io.IOException;
/*     */ import java.util.BitSet;
/*     */ import java.util.Date;
/*     */ import org.deckfour.xes.model.XAttribute;
/*     */ import org.deckfour.xes.model.XAttributeMap;
/*     */ import org.deckfour.xes.model.XAttributeTimestamp;
/*     */ import org.deckfour.xes.model.XEvent;
/*     */ import org.deckfour.xes.nikefs2.NikeFS2VirtualFileSystem;
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
/*     */ 
/*     */ 
/*     */ public class XFastEventList
/*     */   implements Cloneable
/*     */ {
/*  66 */   public static int OVERFLOW_LIMIT = 100;
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*  71 */   protected int size = 0;
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   protected XSequentialEventBuffer buffer;
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   protected XAttributeMapSerializer attributeMapSerializer;
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   protected BitSet holeFlags;
/*     */   
/*     */ 
/*     */ 
/*     */   protected int[] overflowIndices;
/*     */   
/*     */ 
/*     */ 
/*     */   protected XEvent[] overflowEntries;
/*     */   
/*     */ 
/*     */ 
/*     */   protected int overflowSize;
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   public XFastEventList(XAttributeMapSerializer attributeMapSerializer)
/*     */     throws IOException
/*     */   {
/* 106 */     this.size = 0;
/* 107 */     this.attributeMapSerializer = attributeMapSerializer;
/* 108 */     this.buffer = new XSequentialEventBuffer(NikeFS2VirtualFileSystem.instance(), attributeMapSerializer);
/*     */     
/* 110 */     this.holeFlags = new BitSet();
/* 111 */     this.overflowIndices = new int[OVERFLOW_LIMIT];
/* 112 */     this.overflowEntries = new XEvent[OVERFLOW_LIMIT];
/* 113 */     this.overflowSize = 0;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public synchronized int append(XEvent event)
/*     */     throws IOException
/*     */   {
/* 124 */     this.buffer.append(event);
/* 125 */     this.size += 1;
/* 126 */     return this.size - 1;
/*     */   }
/*     */   
/*     */ 
/*     */   public synchronized void cleanup()
/*     */     throws IOException
/*     */   {
/* 133 */     this.buffer.cleanup();
/* 134 */     this.holeFlags = null;
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
/*     */   public synchronized boolean consolidate()
/*     */     throws IOException
/*     */   {
/* 149 */     if (isTainted())
/*     */     {
/* 151 */       XSequentialEventBuffer nBuffer = new XSequentialEventBuffer(this.buffer.getProvider(), this.attributeMapSerializer);
/*     */       
/* 153 */       int overflowIndex = 0;
/* 154 */       int fileBufferIndex = 0;
/* 155 */       for (int i = 0; i < this.size; i++) {
/* 156 */         if ((overflowIndex < this.overflowSize) && (this.overflowIndices[overflowIndex] == i))
/*     */         {
/* 158 */           nBuffer.append(this.overflowEntries[overflowIndex]);
/* 159 */           overflowIndex++;
/*     */         } else {
/* 161 */           while (this.holeFlags.get(fileBufferIndex) == true) {
/* 162 */             fileBufferIndex++;
/*     */           }
/* 164 */           nBuffer.append(this.buffer.get(fileBufferIndex));
/* 165 */           fileBufferIndex++;
/*     */         }
/*     */       }
/* 168 */       this.buffer.cleanup();
/* 169 */       this.buffer = nBuffer;
/* 170 */       this.overflowSize = 0;
/* 171 */       this.holeFlags.clear();
/* 172 */       return true;
/*     */     }
/* 174 */     return false;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public synchronized XEvent get(int index)
/*     */     throws IndexOutOfBoundsException, IOException
/*     */   {
/* 187 */     if ((index < 0) || (index >= this.size)) {
/* 188 */       throw new IndexOutOfBoundsException();
/*     */     }
/* 190 */     int bufferIndex = index;
/*     */     
/* 192 */     for (int i = 0; i < this.overflowSize; i++) {
/* 193 */       if (this.overflowIndices[i] == index)
/* 194 */         return this.overflowEntries[i];
/* 195 */       if (this.overflowIndices[i] >= index) break;
/* 196 */       bufferIndex--;
/*     */     }
/*     */     
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/* 204 */     for (int hole = this.holeFlags.nextSetBit(0); 
/* 205 */         (hole >= 0) && (hole <= bufferIndex); hole = this.holeFlags.nextSetBit(hole + 1)) {
/* 206 */       bufferIndex++;
/*     */     }
/*     */     
/*     */ 
/* 210 */     return this.buffer.get(bufferIndex);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public synchronized void insert(XEvent event, int index)
/*     */     throws IndexOutOfBoundsException, IOException
/*     */   {
/* 223 */     if ((index < 0) || (index > this.size)) {
/* 224 */       throw new IndexOutOfBoundsException();
/*     */     }
/*     */     
/* 227 */     if (index == this.size) {
/* 228 */       append(event);
/* 229 */       return;
/*     */     }
/*     */     
/* 232 */     this.size += 1;
/* 233 */     this.overflowSize += 1;
/*     */     
/* 235 */     for (int i = this.overflowSize - 2; i >= 0; i--) {
/* 236 */       if (this.overflowIndices[i] >= index) {
/* 237 */         this.overflowIndices[(i + 1)] = (this.overflowIndices[i] + 1);
/* 238 */         this.overflowEntries[(i + 1)] = this.overflowEntries[i];
/*     */       } else {
/* 240 */         this.overflowIndices[(i + 1)] = index;
/* 241 */         this.overflowEntries[(i + 1)] = event;
/* 242 */         if (this.overflowSize == this.overflowIndices.length) {
/* 243 */           consolidate();
/*     */         }
/* 245 */         return;
/*     */       }
/*     */     }
/*     */     
/* 249 */     this.overflowIndices[0] = index;
/* 250 */     this.overflowEntries[0] = event;
/* 251 */     if (this.overflowSize == this.overflowIndices.length) {
/* 252 */       consolidate();
/*     */     }
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public synchronized int insertOrdered(XEvent event)
/*     */     throws IOException
/*     */   {
/* 266 */     if (size() == 0)
/*     */     {
/* 268 */       append(event);
/* 269 */       return 0;
/*     */     }
/* 271 */     XAttribute insTsAttr = (XAttribute)event.getAttributes().get("time:timestamp");
/*     */     
/* 273 */     if (insTsAttr == null)
/*     */     {
/* 275 */       append(event);
/* 276 */       return size() - 1;
/*     */     }
/* 278 */     Date insTs = ((XAttributeTimestamp)insTsAttr).getValue();
/* 279 */     for (int i = size() - 1; i >= 0; i--) {
/* 280 */       XAttribute refTsAttr = (XAttribute)get(i).getAttributes().get("time:timestamp");
/*     */       
/* 282 */       if (refTsAttr == null)
/*     */       {
/* 284 */         append(event);
/* 285 */         return size() - 1;
/*     */       }
/* 287 */       Date refTs = ((XAttributeTimestamp)refTsAttr).getValue();
/* 288 */       if (!insTs.before(refTs))
/*     */       {
/* 290 */         insert(event, i + 1);
/* 291 */         return i + 1;
/*     */       }
/*     */     }
/*     */     
/* 295 */     insert(event, 0);
/* 296 */     return 0;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public synchronized boolean isTainted()
/*     */   {
/* 306 */     return (this.overflowSize > 0) || (this.holeFlags.cardinality() > 0);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public synchronized XEvent remove(int index)
/*     */     throws IndexOutOfBoundsException, IOException
/*     */   {
/* 319 */     XEvent removed = null;
/* 320 */     int smallerOverflow = 0;
/* 321 */     for (int i = 0; i < this.overflowSize; i++) {
/* 322 */       if (this.overflowIndices[i] == index) {
/* 323 */         removed = this.overflowEntries[i];
/* 324 */       } else if (this.overflowIndices[i] > index) {
/* 325 */         this.overflowIndices[i] -= 1;
/* 326 */         if (removed != null)
/*     */         {
/* 328 */           this.overflowIndices[(i - 1)] = this.overflowIndices[i];
/* 329 */           this.overflowEntries[(i - 1)] = this.overflowEntries[i];
/*     */         }
/* 331 */       } else if (this.overflowIndices[i] < index) {
/* 332 */         smallerOverflow++;
/*     */       }
/*     */     }
/* 335 */     if (removed != null)
/*     */     {
/* 337 */       this.overflowSize -= 1;
/*     */       
/* 339 */       this.overflowIndices[this.overflowSize] = -1;
/* 340 */       this.overflowEntries[this.overflowSize] = null;
/*     */     } else {
/* 342 */       int bufferIndex = index - smallerOverflow;
/* 343 */       for (int hole = this.holeFlags.nextSetBit(0); 
/* 344 */           (hole >= 0) && (hole <= bufferIndex); hole = this.holeFlags.nextSetBit(hole + 1))
/*     */       {
/* 346 */         bufferIndex++;
/*     */       }
/* 348 */       removed = this.buffer.get(bufferIndex);
/* 349 */       this.holeFlags.set(bufferIndex, true);
/*     */     }
/* 351 */     this.size -= 1;
/* 352 */     return removed;
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
/*     */   public synchronized XEvent replace(XEvent event, int index)
/*     */     throws IndexOutOfBoundsException, IOException
/*     */   {
/* 367 */     XEvent replaced = null;
/* 368 */     int smallerOverflow = 0;
/* 369 */     for (int i = 0; i < this.overflowSize; i++) {
/* 370 */       if (this.overflowIndices[i] == index) {
/* 371 */         replaced = this.overflowEntries[i];
/* 372 */         this.overflowEntries[i] = event;
/* 373 */         return replaced; }
/* 374 */       if (this.overflowIndices[i] > index) {
/*     */         break;
/*     */       }
/* 377 */       if (this.overflowIndices[i] < index) {
/* 378 */         smallerOverflow++;
/*     */       }
/*     */     }
/*     */     
/* 382 */     int bufferIndex = index - smallerOverflow;
/* 383 */     for (int hole = this.holeFlags.nextSetBit(0); 
/* 384 */         (hole >= 0) && (hole <= bufferIndex); hole = this.holeFlags.nextSetBit(hole + 1)) {
/* 385 */       bufferIndex++;
/*     */     }
/* 387 */     replaced = this.buffer.get(bufferIndex);
/* 388 */     if (!this.buffer.replace(event, bufferIndex)) {
/* 389 */       remove(index);
/* 390 */       insert(event, index);
/*     */     }
/* 392 */     return replaced;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public synchronized int size()
/*     */   {
/* 401 */     return this.size;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */   public synchronized Object clone()
/*     */   {
/*     */     try
/*     */     {
/* 410 */       consolidate();
/*     */     } catch (IOException e) {
/* 412 */       e.printStackTrace();
/* 413 */       return null;
/*     */     }
/*     */     
/* 416 */     XFastEventList clone = null;
/*     */     try {
/* 418 */       clone = (XFastEventList)super.clone();
/*     */     } catch (CloneNotSupportedException e) {
/* 420 */       e.printStackTrace();
/* 421 */       return null;
/*     */     }
/*     */     
/* 424 */     clone.buffer = ((XSequentialEventBuffer)this.buffer.clone());
/* 425 */     clone.holeFlags = ((BitSet)this.holeFlags.clone());
/* 426 */     clone.overflowEntries = ((XEvent[])this.overflowEntries.clone());
/* 427 */     clone.overflowIndices = ((int[])this.overflowIndices.clone());
/* 428 */     return clone;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   protected void finalize()
/*     */     throws Throwable
/*     */   {
/* 438 */     super.finalize();
/* 439 */     cleanup();
/*     */   }
/*     */ }


/* Location:              /home/menick/Scrivania/ProcessMining/CNMining/CNMining.jar!/org/deckfour/xes/model/buffered/XFastEventList.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */