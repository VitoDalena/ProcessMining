/*    */ package com.thoughtworks.xstream.core.util;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public final class FastStack
/*    */ {
/*    */   private Object[] stack;
/*    */   
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   private int pointer;
/*    */   
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   public FastStack(int initialCapacity)
/*    */   {
/* 26 */     this.stack = new Object[initialCapacity];
/*    */   }
/*    */   
/*    */   public Object push(Object value) {
/* 30 */     if (this.pointer + 1 >= this.stack.length) {
/* 31 */       resizeStack(this.stack.length * 2);
/*    */     }
/* 33 */     this.stack[(this.pointer++)] = value;
/* 34 */     return value;
/*    */   }
/*    */   
/*    */   public void popSilently() {
/* 38 */     this.stack[(--this.pointer)] = null;
/*    */   }
/*    */   
/*    */   public Object pop() {
/* 42 */     Object result = this.stack[(--this.pointer)];
/* 43 */     this.stack[this.pointer] = null;
/* 44 */     return result;
/*    */   }
/*    */   
/*    */   public Object peek() {
/* 48 */     return this.pointer == 0 ? null : this.stack[(this.pointer - 1)];
/*    */   }
/*    */   
/*    */   public Object replace(Object value) {
/* 52 */     Object result = this.stack[(this.pointer - 1)];
/* 53 */     this.stack[(this.pointer - 1)] = value;
/* 54 */     return result;
/*    */   }
/*    */   
/*    */   public void replaceSilently(Object value) {
/* 58 */     this.stack[(this.pointer - 1)] = value;
/*    */   }
/*    */   
/*    */   public int size() {
/* 62 */     return this.pointer;
/*    */   }
/*    */   
/*    */   public boolean hasStuff() {
/* 66 */     return this.pointer > 0;
/*    */   }
/*    */   
/*    */   public Object get(int i) {
/* 70 */     return this.stack[i];
/*    */   }
/*    */   
/*    */   private void resizeStack(int newCapacity) {
/* 74 */     Object[] newStack = new Object[newCapacity];
/* 75 */     System.arraycopy(this.stack, 0, newStack, 0, Math.min(this.pointer, newCapacity));
/* 76 */     this.stack = newStack;
/*    */   }
/*    */   
/*    */   public String toString() {
/* 80 */     StringBuffer result = new StringBuffer("[");
/* 81 */     for (int i = 0; i < this.pointer; i++) {
/* 82 */       if (i > 0) {
/* 83 */         result.append(", ");
/*    */       }
/* 85 */       result.append(this.stack[i]);
/*    */     }
/* 87 */     result.append(']');
/* 88 */     return result.toString();
/*    */   }
/*    */ }


/* Location:              /home/menick/Scrivania/ProcessMining/CNMining/CNMining.jar!/com/thoughtworks/xstream/core/util/FastStack.class
 * Java compiler version: 3 (47.0)
 * JD-Core Version:       0.7.1
 */