/*     */ package com.thoughtworks.xstream.io;
/*     */ 
/*     */ import com.thoughtworks.xstream.core.util.FastStack;
/*     */ import java.io.IOException;
/*     */ import java.util.HashSet;
/*     */ import java.util.Set;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class StatefulWriter
/*     */   extends WriterWrapper
/*     */ {
/*  37 */   public static int STATE_OPEN = 0;
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*  43 */   public static int STATE_NODE_START = 1;
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*  49 */   public static int STATE_VALUE = 2;
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*  55 */   public static int STATE_NODE_END = 3;
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*  61 */   public static int STATE_CLOSED = 4;
/*     */   
/*  63 */   private transient int state = STATE_OPEN;
/*     */   
/*     */ 
/*     */   private transient int balance;
/*     */   
/*     */ 
/*     */   private transient FastStack attributes;
/*     */   
/*     */ 
/*     */   public StatefulWriter(HierarchicalStreamWriter wrapped)
/*     */   {
/*  74 */     super(wrapped);
/*  75 */     this.attributes = new FastStack(16);
/*     */   }
/*     */   
/*     */   public void startNode(String name) {
/*  79 */     startNodeCommon();
/*  80 */     super.startNode(name);
/*     */   }
/*     */   
/*     */   public void startNode(String name, Class clazz) {
/*  84 */     startNodeCommon();
/*  85 */     super.startNode(name, clazz);
/*     */   }
/*     */   
/*     */   private void startNodeCommon() {
/*  89 */     checkClosed();
/*  90 */     if (this.state == STATE_VALUE)
/*     */     {
/*  92 */       throw new StreamException(new IllegalStateException("Opening node after writing text"));
/*     */     }
/*  94 */     this.state = STATE_NODE_START;
/*  95 */     this.balance += 1;
/*  96 */     this.attributes.push(new HashSet());
/*     */   }
/*     */   
/*     */   public void addAttribute(String name, String value) {
/* 100 */     checkClosed();
/* 101 */     if (this.state != STATE_NODE_START) {
/* 102 */       throw new StreamException(new IllegalStateException("Writing attribute '" + name + "' without an opened node"));
/*     */     }
/*     */     
/*     */ 
/* 106 */     Set currentAttributes = (Set)this.attributes.peek();
/* 107 */     if (currentAttributes.contains(name)) {
/* 108 */       throw new StreamException(new IllegalStateException("Writing attribute '" + name + "' twice"));
/*     */     }
/*     */     
/*     */ 
/* 112 */     currentAttributes.add(name);
/* 113 */     super.addAttribute(name, value);
/*     */   }
/*     */   
/*     */   public void setValue(String text) {
/* 117 */     checkClosed();
/* 118 */     if (this.state != STATE_NODE_START)
/*     */     {
/* 120 */       throw new StreamException(new IllegalStateException("Writing text without an opened node"));
/*     */     }
/*     */     
/* 123 */     this.state = STATE_VALUE;
/* 124 */     super.setValue(text);
/*     */   }
/*     */   
/*     */   public void endNode() {
/* 128 */     checkClosed();
/* 129 */     if (this.balance-- == 0) {
/* 130 */       throw new StreamException(new IllegalStateException("Unbalanced node"));
/*     */     }
/* 132 */     this.attributes.popSilently();
/* 133 */     this.state = STATE_NODE_END;
/* 134 */     super.endNode();
/*     */   }
/*     */   
/*     */   public void flush() {
/* 138 */     checkClosed();
/* 139 */     super.flush();
/*     */   }
/*     */   
/*     */   public void close() {
/* 143 */     if ((this.state != STATE_NODE_END) && (this.state != STATE_OPEN)) {}
/*     */     
/*     */ 
/*     */ 
/* 147 */     this.state = STATE_CLOSED;
/* 148 */     super.close();
/*     */   }
/*     */   
/*     */   private void checkClosed() {
/* 152 */     if (this.state == STATE_CLOSED) {
/* 153 */       throw new StreamException(new IOException("Writing on a closed stream"));
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
/*     */ 
/*     */ 
/*     */ 
/*     */   public int state()
/*     */   {
/* 169 */     return this.state;
/*     */   }
/*     */   
/*     */   private Object readResolve() {
/* 173 */     this.attributes = new FastStack(16);
/* 174 */     return this;
/*     */   }
/*     */ }


/* Location:              /home/menick/Scrivania/ProcessMining/CNMining/CNMining.jar!/com/thoughtworks/xstream/io/StatefulWriter.class
 * Java compiler version: 3 (47.0)
 * JD-Core Version:       0.7.1
 */