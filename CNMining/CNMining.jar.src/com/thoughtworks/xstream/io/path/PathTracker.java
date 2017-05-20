/*     */ package com.thoughtworks.xstream.io.path;
/*     */ 
/*     */ import java.util.HashMap;
/*     */ import java.util.Map;
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
/*     */ public class PathTracker
/*     */ {
/*     */   private int pointer;
/*     */   private int capacity;
/*     */   private String[] pathStack;
/*     */   private Map[] indexMapStack;
/*     */   private Path currentPath;
/*     */   
/*     */   public PathTracker()
/*     */   {
/*  56 */     this(16);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public PathTracker(int initialCapacity)
/*     */   {
/*  65 */     this.capacity = Math.max(1, initialCapacity);
/*  66 */     this.pathStack = new String[this.capacity];
/*  67 */     this.indexMapStack = new Map[this.capacity];
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public void pushElement(String name)
/*     */   {
/*  76 */     if (this.pointer + 1 >= this.capacity) {
/*  77 */       resizeStacks(this.capacity * 2);
/*     */     }
/*  79 */     this.pathStack[this.pointer] = name;
/*  80 */     Map indexMap = this.indexMapStack[this.pointer];
/*  81 */     if (indexMap == null) {
/*  82 */       indexMap = new HashMap();
/*  83 */       this.indexMapStack[this.pointer] = indexMap;
/*     */     }
/*  85 */     if (indexMap.containsKey(name)) {
/*  86 */       indexMap.put(name, new Integer(((Integer)indexMap.get(name)).intValue() + 1));
/*     */     } else {
/*  88 */       indexMap.put(name, new Integer(1));
/*     */     }
/*  90 */     this.pointer += 1;
/*  91 */     this.currentPath = null;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */   public void popElement()
/*     */   {
/*  98 */     this.indexMapStack[this.pointer] = null;
/*  99 */     this.currentPath = null;
/* 100 */     this.pointer -= 1;
/*     */   }
/*     */   
/*     */   private void resizeStacks(int newCapacity) {
/* 104 */     String[] newPathStack = new String[newCapacity];
/* 105 */     Map[] newIndexMapStack = new Map[newCapacity];
/* 106 */     int min = Math.min(this.capacity, newCapacity);
/* 107 */     System.arraycopy(this.pathStack, 0, newPathStack, 0, min);
/* 108 */     System.arraycopy(this.indexMapStack, 0, newIndexMapStack, 0, min);
/* 109 */     this.pathStack = newPathStack;
/* 110 */     this.indexMapStack = newIndexMapStack;
/* 111 */     this.capacity = newCapacity;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */   public Path getPath()
/*     */   {
/* 118 */     if (this.currentPath == null) {
/* 119 */       String[] chunks = new String[this.pointer + 1];
/* 120 */       chunks[0] = "";
/* 121 */       for (int i = 0; i < this.pointer; i++) {
/* 122 */         Integer integer = (Integer)this.indexMapStack[i].get(this.pathStack[i]);
/* 123 */         int index = integer.intValue();
/* 124 */         if (index > 1) {
/* 125 */           StringBuffer chunk = new StringBuffer(this.pathStack[i].length() + 6);
/* 126 */           chunk.append(this.pathStack[i]).append('[').append(index).append(']');
/* 127 */           chunks[(i + 1)] = chunk.toString();
/*     */         } else {
/* 129 */           chunks[(i + 1)] = this.pathStack[i];
/*     */         }
/*     */       }
/* 132 */       this.currentPath = new Path(chunks);
/*     */     }
/* 134 */     return this.currentPath;
/*     */   }
/*     */ }


/* Location:              /home/menick/Scrivania/ProcessMining/CNMining/CNMining.jar!/com/thoughtworks/xstream/io/path/PathTracker.class
 * Java compiler version: 3 (47.0)
 * JD-Core Version:       0.7.1
 */