/*     */ package com.thoughtworks.xstream.io.xml;
/*     */ 
/*     */ import com.thoughtworks.xstream.core.util.FastStack;
/*     */ import com.thoughtworks.xstream.io.AttributeNameIterator;
/*     */ import com.thoughtworks.xstream.io.naming.NameCoder;
/*     */ import java.util.Iterator;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public abstract class AbstractPullReader
/*     */   extends AbstractXmlReader
/*     */ {
/*     */   protected static final int START_NODE = 1;
/*     */   protected static final int END_NODE = 2;
/*     */   protected static final int TEXT = 3;
/*     */   protected static final int COMMENT = 4;
/*     */   protected static final int OTHER = 0;
/*  35 */   private final FastStack elementStack = new FastStack(16);
/*  36 */   private final FastStack pool = new FastStack(16);
/*     */   
/*  38 */   private final FastStack lookahead = new FastStack(4);
/*  39 */   private final FastStack lookback = new FastStack(4);
/*     */   
/*     */   private static class Event { private Event() {}
/*  42 */     Event(AbstractPullReader.1 x0) { this(); }
/*     */     
/*     */ 
/*     */     int type;
/*     */     String value;
/*     */   }
/*     */   
/*     */   protected AbstractPullReader(NameCoder nameCoder)
/*     */   {
/*  51 */     super(nameCoder);
/*     */   }
/*     */   
/*     */   /**
/*     */    * @deprecated
/*     */    */
/*     */   protected AbstractPullReader(XmlFriendlyReplacer replacer)
/*     */   {
/*  59 */     this(replacer);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   private boolean marked;
/*     */   
/*     */ 
/*     */ 
/*     */   protected abstract int pullNextEvent();
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   protected abstract String pullElementName();
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   protected abstract String pullText();
/*     */   
/*     */ 
/*     */ 
/*     */   public boolean hasMoreChildren()
/*     */   {
/*  85 */     mark();
/*     */     for (;;) {
/*  87 */       switch (readEvent().type) {
/*     */       case 1: 
/*  89 */         reset();
/*  90 */         return true;
/*     */       case 2: 
/*  92 */         reset();
/*  93 */         return false;
/*     */       }
/*     */       
/*     */     }
/*     */   }
/*     */   
/*     */   public void moveDown()
/*     */   {
/* 101 */     int currentDepth = this.elementStack.size();
/* 102 */     while (this.elementStack.size() <= currentDepth) {
/* 103 */       move();
/* 104 */       if (this.elementStack.size() < currentDepth) {
/* 105 */         throw new RuntimeException();
/*     */       }
/*     */     }
/*     */   }
/*     */   
/*     */   public void moveUp() {
/* 111 */     int currentDepth = this.elementStack.size();
/* 112 */     while (this.elementStack.size() >= currentDepth) {
/* 113 */       move();
/*     */     }
/*     */   }
/*     */   
/*     */   private void move() {
/* 118 */     Event event = readEvent();
/* 119 */     this.pool.push(event);
/* 120 */     switch (event.type) {
/*     */     case 1: 
/* 122 */       this.elementStack.push(pullElementName());
/* 123 */       break;
/*     */     case 2: 
/* 125 */       this.elementStack.pop();
/*     */     }
/*     */   }
/*     */   
/*     */   private Event readEvent()
/*     */   {
/* 131 */     if (this.marked) {
/* 132 */       if (this.lookback.hasStuff()) {
/* 133 */         return (Event)this.lookahead.push(this.lookback.pop());
/*     */       }
/* 135 */       return (Event)this.lookahead.push(readRealEvent());
/*     */     }
/*     */     
/* 138 */     if (this.lookback.hasStuff()) {
/* 139 */       return (Event)this.lookback.pop();
/*     */     }
/* 141 */     return readRealEvent();
/*     */   }
/*     */   
/*     */ 
/*     */   private Event readRealEvent()
/*     */   {
/* 147 */     Event event = this.pool.hasStuff() ? (Event)this.pool.pop() : new Event(null);
/* 148 */     event.type = pullNextEvent();
/* 149 */     if (event.type == 3) {
/* 150 */       event.value = pullText();
/* 151 */     } else if (event.type == 1) {
/* 152 */       event.value = pullElementName();
/*     */     } else {
/* 154 */       event.value = null;
/*     */     }
/* 156 */     return event;
/*     */   }
/*     */   
/*     */   public void mark() {
/* 160 */     this.marked = true;
/*     */   }
/*     */   
/*     */   public void reset() {
/* 164 */     while (this.lookahead.hasStuff()) {
/* 165 */       this.lookback.push(this.lookahead.pop());
/*     */     }
/* 167 */     this.marked = false;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public String getValue()
/*     */   {
/* 176 */     String last = null;
/* 177 */     StringBuffer buffer = null;
/*     */     
/* 179 */     mark();
/* 180 */     Event event = readEvent();
/*     */     for (;;) {
/* 182 */       if (event.type == 3) {
/* 183 */         String text = event.value;
/* 184 */         if ((text != null) && (text.length() > 0))
/* 185 */           if (last == null) {
/* 186 */             last = text;
/*     */           } else {
/* 188 */             if (buffer == null) {
/* 189 */               buffer = new StringBuffer(last);
/*     */             }
/* 191 */             buffer.append(text);
/*     */           }
/*     */       } else {
/* 194 */         if (event.type != 4)
/*     */           break;
/*     */       }
/* 197 */       event = readEvent();
/*     */     }
/* 199 */     reset();
/* 200 */     if (buffer != null) {
/* 201 */       return buffer.toString();
/*     */     }
/* 203 */     return last == null ? "" : last;
/*     */   }
/*     */   
/*     */   public Iterator getAttributeNames()
/*     */   {
/* 208 */     return new AttributeNameIterator(this);
/*     */   }
/*     */   
/*     */   public String getNodeName() {
/* 212 */     return unescapeXmlName((String)this.elementStack.peek());
/*     */   }
/*     */ }


/* Location:              /home/menick/Scrivania/ProcessMining/CNMining/CNMining.jar!/com/thoughtworks/xstream/io/xml/AbstractPullReader.class
 * Java compiler version: 3 (47.0)
 * JD-Core Version:       0.7.1
 */