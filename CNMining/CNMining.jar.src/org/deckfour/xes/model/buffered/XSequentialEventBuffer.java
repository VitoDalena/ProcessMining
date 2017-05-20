/*     */ package org.deckfour.xes.model.buffered;
/*     */ 
/*     */ import java.io.ByteArrayInputStream;
/*     */ import java.io.ByteArrayOutputStream;
/*     */ import java.io.DataInputStream;
/*     */ import java.io.DataOutputStream;
/*     */ import java.io.IOException;
/*     */ import java.util.Arrays;
/*     */ import org.deckfour.xes.factory.XFactory;
/*     */ import org.deckfour.xes.factory.XFactoryRegistry;
/*     */ import org.deckfour.xes.id.XID;
/*     */ import org.deckfour.xes.model.XAttributeMap;
/*     */ import org.deckfour.xes.model.XEvent;
/*     */ import org.deckfour.xes.nikefs2.NikeFS2RandomAccessStorage;
/*     */ import org.deckfour.xes.nikefs2.NikeFS2StorageProvider;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class XSequentialEventBuffer
/*     */   implements Cloneable
/*     */ {
/*     */   protected static final int EXTENSION_GENERIC = -1;
/*  98 */   private int size = 0;
/*     */   
/*     */ 
/*     */ 
/* 102 */   private int index = 0;
/*     */   
/*     */ 
/*     */ 
/*     */ 
/* 107 */   private long position = 0L;
/*     */   
/*     */ 
/*     */ 
/*     */ 
/* 112 */   private long lastInsertPosition = -1L;
/*     */   
/*     */ 
/*     */ 
/* 116 */   private NikeFS2RandomAccessStorage storage = null;
/*     */   
/*     */ 
/*     */ 
/* 120 */   private NikeFS2StorageProvider provider = null;
/*     */   
/*     */ 
/*     */ 
/* 124 */   private XAttributeMapSerializer attributeMapSerializer = null;
/*     */   
/*     */ 
/*     */ 
/* 128 */   private XFactory factory = null;
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public XSequentialEventBuffer(NikeFS2StorageProvider aProvider, XAttributeMapSerializer attributeMapSerializer)
/*     */     throws IOException
/*     */   {
/* 141 */     this.provider = aProvider;
/* 142 */     this.attributeMapSerializer = attributeMapSerializer;
/* 143 */     this.size = 0;
/* 144 */     this.index = 0;
/* 145 */     this.position = 0L;
/* 146 */     this.lastInsertPosition = -1L;
/* 147 */     this.storage = this.provider.createStorage();
/* 148 */     this.factory = ((XFactory)XFactoryRegistry.instance().currentDefault());
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */   protected XSequentialEventBuffer() {}
/*     */   
/*     */ 
/*     */ 
/*     */   public NikeFS2StorageProvider getProvider()
/*     */   {
/* 159 */     return this.provider;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */   public long position()
/*     */   {
/* 166 */     return this.position;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */   public long lastInsert()
/*     */   {
/* 173 */     return this.lastInsertPosition;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */   public NikeFS2RandomAccessStorage getStorage()
/*     */   {
/* 180 */     return this.storage;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public synchronized int size()
/*     */   {
/* 189 */     return this.size;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */   public synchronized int index()
/*     */   {
/* 196 */     return this.index;
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
/*     */   public synchronized void append(XEvent event)
/*     */     throws IOException
/*     */   {
/* 210 */     long insertPosition = this.storage.length();
/*     */     
/* 212 */     this.storage.seek(insertPosition);
/*     */     
/* 214 */     byte[] evtEnc = encode(event);
/*     */     
/*     */ 
/* 217 */     int segmentPaddingSize = evtEnc.length / 4;
/* 218 */     int segmentSize = evtEnc.length + segmentPaddingSize;
/* 219 */     byte[] segmentPadding = new byte[segmentPaddingSize];
/* 220 */     Arrays.fill(segmentPadding, (byte)0);
/*     */     
/*     */ 
/*     */ 
/* 224 */     this.storage.writeInt(segmentSize + 12);
/*     */     
/* 226 */     this.storage.writeInt((int)(insertPosition - this.lastInsertPosition));
/*     */     
/* 228 */     this.storage.writeInt(evtEnc.length);
/*     */     
/* 230 */     this.storage.write(evtEnc);
/*     */     
/* 232 */     this.storage.write(segmentPadding);
/*     */     
/* 234 */     this.lastInsertPosition = insertPosition;
/*     */     
/* 236 */     this.size += 1;
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
/*     */   public synchronized boolean replace(XEvent event, int index)
/*     */     throws IOException
/*     */   {
/* 251 */     if ((index < 0) || (index >= this.size)) {
/* 252 */       throw new IndexOutOfBoundsException();
/*     */     }
/*     */     
/* 255 */     navigateToIndex(index);
/* 256 */     this.storage.seek(this.position);
/* 257 */     long atePosition = this.position;
/*     */     
/* 259 */     int fwd = this.storage.readInt();
/*     */     
/* 261 */     this.storage.skipBytes(8);
/* 262 */     int segmentSize = fwd - 12;
/*     */     
/* 264 */     byte[] evtEnc = encode(event);
/* 265 */     boolean success = false;
/* 266 */     if (evtEnc.length <= segmentSize)
/*     */     {
/* 268 */       this.storage.seek(atePosition + 8L);
/* 269 */       this.storage.writeInt(evtEnc.length);
/*     */       
/* 271 */       byte[] segmentPadding = new byte[segmentSize - evtEnc.length];
/* 272 */       Arrays.fill(segmentPadding, (byte)0);
/* 273 */       this.storage.write(evtEnc);
/* 274 */       this.storage.write(segmentPadding);
/* 275 */       success = true;
/*     */     } else {
/* 277 */       success = false;
/*     */     }
/*     */     
/* 280 */     this.position = atePosition;
/* 281 */     this.storage.seek(this.position);
/* 282 */     return success;
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
/*     */   public synchronized XEvent get(int eventIndex)
/*     */     throws IOException, IndexOutOfBoundsException
/*     */   {
/* 296 */     if ((eventIndex < 0) || (eventIndex >= this.size)) {
/* 297 */       throw new IndexOutOfBoundsException();
/*     */     }
/*     */     
/* 300 */     navigateToIndex(eventIndex);
/*     */     
/* 302 */     return read();
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   public synchronized void cleanup()
/*     */     throws IOException
/*     */   {
/* 311 */     this.storage.close();
/* 312 */     this.size = 0;
/* 313 */     this.index = 0;
/* 314 */     this.position = 0L;
/* 315 */     this.lastInsertPosition = -1L;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   protected synchronized void navigateToIndex(int reqIndex)
/*     */     throws IOException
/*     */   {
/* 327 */     if (reqIndex != this.index)
/*     */     {
/* 329 */       if ((reqIndex < 0) || (reqIndex >= this.size)) {
/* 330 */         throw new IndexOutOfBoundsException();
/*     */       }
/*     */       
/* 333 */       if (reqIndex > this.index)
/*     */       {
/* 335 */         skipForward(reqIndex - this.index);
/*     */       }
/*     */       else {
/* 338 */         int backSkips = this.index - reqIndex;
/* 339 */         if (backSkips < this.index / 2)
/*     */         {
/* 341 */           if (this.index == this.size)
/*     */           {
/*     */ 
/* 344 */             this.index = (this.size - 1);
/* 345 */             this.position = this.lastInsertPosition;
/* 346 */             backSkips = this.index - reqIndex;
/*     */           }
/*     */           
/* 349 */           skipBackward(backSkips);
/*     */ 
/*     */         }
/*     */         else
/*     */         {
/* 354 */           resetPosition();
/* 355 */           skipForward(reqIndex);
/*     */         }
/*     */       }
/*     */     }
/* 359 */     if (reqIndex != this.index) {
/* 360 */       throw new IOException("Navigation fault! (required: " + reqIndex + ", yielded: " + this.index + ")");
/*     */     }
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   protected synchronized void resetPosition()
/*     */   {
/* 370 */     this.index = 0;
/* 371 */     this.position = 0L;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   protected synchronized void skipForward(int eventsToSkip)
/*     */     throws IOException
/*     */   {
/* 383 */     int offset = 0;
/* 384 */     for (int i = 0; i < eventsToSkip; i++)
/*     */     {
/* 386 */       this.storage.seek(this.position);
/*     */       
/* 388 */       offset = this.storage.readInt();
/*     */       
/* 390 */       this.position += offset;
/*     */       
/* 392 */       this.index += 1;
/*     */     }
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   protected synchronized void skipBackward(int eventsToSkip)
/*     */     throws IOException
/*     */   {
/* 405 */     int offset = 0;
/* 406 */     for (int i = 0; i < eventsToSkip; i++)
/*     */     {
/* 408 */       this.storage.seek(this.position + 4L);
/*     */       
/* 410 */       offset = this.storage.readInt();
/*     */       
/* 412 */       this.position -= offset;
/*     */       
/* 414 */       this.index -= 1;
/*     */     }
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   protected synchronized XEvent read()
/*     */     throws IOException
/*     */   {
/* 425 */     this.storage.seek(this.position);
/*     */     
/* 427 */     long nextPosition = this.position + this.storage.readInt();
/*     */     
/* 429 */     this.storage.skipBytes(4);
/*     */     
/* 431 */     int eventSize = this.storage.readInt();
/*     */     
/*     */ 
/* 434 */     byte[] eventData = new byte[eventSize];
/* 435 */     this.storage.readFully(eventData);
/* 436 */     DataInputStream dis = new DataInputStream(new ByteArrayInputStream(eventData));
/*     */     
/*     */ 
/* 439 */     XID id = XID.read(dis);
/*     */     
/* 441 */     XAttributeMap attributes = this.attributeMapSerializer.deserialize(dis);
/*     */     
/* 443 */     XEvent event = this.factory.createEvent(id, attributes);
/*     */     
/* 445 */     this.position = nextPosition;
/* 446 */     this.index += 1;
/* 447 */     return event;
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
/*     */   protected byte[] encode(XEvent event)
/*     */     throws IOException
/*     */   {
/* 462 */     ByteArrayOutputStream baos = new ByteArrayOutputStream();
/* 463 */     DataOutputStream dos = new DataOutputStream(baos);
/*     */     
/* 465 */     XID.write(event.getID(), dos);
/*     */     
/* 467 */     this.attributeMapSerializer.serialize(event.getAttributes(), dos);
/*     */     
/* 469 */     dos.flush();
/* 470 */     return baos.toByteArray();
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */   public Object clone()
/*     */   {
/* 477 */     XSequentialEventBuffer clone = null;
/*     */     
/* 479 */     synchronized (XSequentialEventBuffer.class) {
/*     */       try {
/* 481 */         clone = (XSequentialEventBuffer)super.clone();
/*     */       } catch (CloneNotSupportedException e) {
/* 483 */         e.printStackTrace();
/* 484 */         return null;
/*     */       }
/*     */       try {
/* 487 */         clone.storage = this.storage.copy();
/*     */       } catch (IOException e) {
/* 489 */         e.printStackTrace();
/* 490 */         clone.storage = null;
/*     */       }
/*     */     }
/* 493 */     return clone;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */   protected void finalize()
/*     */     throws Throwable
/*     */   {
/* 501 */     cleanup();
/*     */   }
/*     */ }


/* Location:              /home/menick/Scrivania/ProcessMining/CNMining/CNMining.jar!/org/deckfour/xes/model/buffered/XSequentialEventBuffer.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */