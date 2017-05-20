/*     */ package com.thoughtworks.xstream.io.binary;
/*     */ 
/*     */ import java.util.ArrayList;
/*     */ import java.util.Collections;
/*     */ import java.util.Iterator;
/*     */ import java.util.List;
/*     */ import java.util.Set;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ class ReaderDepthState
/*     */ {
/*     */   private static final String EMPTY_STRING = "";
/*     */   private State current;
/*     */   
/*     */   private static class State
/*     */   {
/*     */     String name;
/*     */     String value;
/*     */     List attributes;
/*     */     boolean hasMoreChildren;
/*     */     State parent;
/*     */     
/*     */     private State() {}
/*     */     
/*  31 */     State(ReaderDepthState.1 x0) { this(); }
/*     */   }
/*     */   
/*     */   private static class Attribute { String name;
/*     */     String value;
/*     */     
/*     */     private Attribute() {}
/*     */     
/*  39 */     Attribute(ReaderDepthState.1 x0) { this(); }
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   public void push()
/*     */   {
/*  47 */     State newState = new State(null);
/*  48 */     newState.parent = this.current;
/*  49 */     this.current = newState;
/*     */   }
/*     */   
/*     */   public void pop() {
/*  53 */     this.current = this.current.parent;
/*     */   }
/*     */   
/*     */   public String getName() {
/*  57 */     return this.current.name;
/*     */   }
/*     */   
/*     */   public void setName(String name) {
/*  61 */     this.current.name = name;
/*     */   }
/*     */   
/*     */   public String getValue() {
/*  65 */     return this.current.value == null ? "" : this.current.value;
/*     */   }
/*     */   
/*     */   public void setValue(String value) {
/*  69 */     this.current.value = value;
/*     */   }
/*     */   
/*     */   public boolean hasMoreChildren() {
/*  73 */     return this.current.hasMoreChildren;
/*     */   }
/*     */   
/*     */   public void setHasMoreChildren(boolean hasMoreChildren) {
/*  77 */     this.current.hasMoreChildren = hasMoreChildren;
/*     */   }
/*     */   
/*     */   public void addAttribute(String name, String value) {
/*  81 */     Attribute attribute = new Attribute(null);
/*  82 */     attribute.name = name;
/*  83 */     attribute.value = value;
/*  84 */     if (this.current.attributes == null) {
/*  85 */       this.current.attributes = new ArrayList();
/*     */     }
/*  87 */     this.current.attributes.add(attribute);
/*     */   }
/*     */   
/*     */   public String getAttribute(String name) {
/*  91 */     if (this.current.attributes == null) {
/*  92 */       return null;
/*     */     }
/*     */     
/*  95 */     for (Iterator iterator = this.current.attributes.iterator(); iterator.hasNext();) {
/*  96 */       Attribute attribute = (Attribute)iterator.next();
/*  97 */       if (attribute.name.equals(name)) {
/*  98 */         return attribute.value;
/*     */       }
/*     */     }
/* 101 */     return null;
/*     */   }
/*     */   
/*     */   public String getAttribute(int index)
/*     */   {
/* 106 */     if (this.current.attributes == null) {
/* 107 */       return null;
/*     */     }
/* 109 */     Attribute attribute = (Attribute)this.current.attributes.get(index);
/* 110 */     return attribute.value;
/*     */   }
/*     */   
/*     */   public String getAttributeName(int index)
/*     */   {
/* 115 */     if (this.current.attributes == null) {
/* 116 */       return null;
/*     */     }
/* 118 */     Attribute attribute = (Attribute)this.current.attributes.get(index);
/* 119 */     return attribute.name;
/*     */   }
/*     */   
/*     */   public int getAttributeCount()
/*     */   {
/* 124 */     return this.current.attributes == null ? 0 : this.current.attributes.size();
/*     */   }
/*     */   
/*     */   public Iterator getAttributeNames() {
/* 128 */     if (this.current.attributes == null) {
/* 129 */       return Collections.EMPTY_SET.iterator();
/*     */     }
/* 131 */     Iterator attributeIterator = this.current.attributes.iterator();
/* 132 */     new Iterator() { private final Iterator val$attributeIterator;
/*     */       
/* 134 */       public boolean hasNext() { return this.val$attributeIterator.hasNext(); }
/*     */       
/*     */       public Object next()
/*     */       {
/* 138 */         ReaderDepthState.Attribute attribute = (ReaderDepthState.Attribute)this.val$attributeIterator.next();
/* 139 */         return attribute.name;
/*     */       }
/*     */       
/*     */       public void remove() {
/* 143 */         throw new UnsupportedOperationException();
/*     */       }
/*     */     };
/*     */   }
/*     */ }


/* Location:              /home/menick/Scrivania/ProcessMining/CNMining/CNMining.jar!/com/thoughtworks/xstream/io/binary/ReaderDepthState.class
 * Java compiler version: 3 (47.0)
 * JD-Core Version:       0.7.1
 */