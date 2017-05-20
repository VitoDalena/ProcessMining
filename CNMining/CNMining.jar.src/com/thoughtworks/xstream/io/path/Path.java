/*     */ package com.thoughtworks.xstream.io.path;
/*     */ 
/*     */ import com.thoughtworks.xstream.core.util.FastStack;
/*     */ import java.util.ArrayList;
/*     */ import java.util.List;
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
/*     */ public class Path
/*     */ {
/*     */   private final String[] chunks;
/*     */   private transient String pathAsString;
/*     */   private transient String pathExplicit;
/*  66 */   private static final Path DOT = new Path(new String[] { "." });
/*     */   
/*     */   public Path(String pathAsString)
/*     */   {
/*  70 */     List result = new ArrayList();
/*  71 */     int currentIndex = 0;
/*     */     
/*  73 */     this.pathAsString = pathAsString;
/*  74 */     int nextSeparator; while ((nextSeparator = pathAsString.indexOf('/', currentIndex)) != -1)
/*     */     {
/*  76 */       result.add(normalize(pathAsString, currentIndex, nextSeparator));
/*  77 */       currentIndex = nextSeparator + 1;
/*     */     }
/*  79 */     result.add(normalize(pathAsString, currentIndex, pathAsString.length()));
/*  80 */     String[] arr = new String[result.size()];
/*  81 */     result.toArray(arr);
/*  82 */     this.chunks = arr;
/*     */   }
/*     */   
/*     */   private String normalize(String s, int start, int end) {
/*  86 */     if ((end - start > 3) && (s.charAt(end - 3) == '[') && (s.charAt(end - 2) == '1') && (s.charAt(end - 1) == ']'))
/*     */     {
/*     */ 
/*     */ 
/*  90 */       this.pathAsString = null;
/*  91 */       return s.substring(start, end - 3);
/*     */     }
/*  93 */     return s.substring(start, end);
/*     */   }
/*     */   
/*     */ 
/*     */   public Path(String[] chunks)
/*     */   {
/*  99 */     this.chunks = chunks;
/*     */   }
/*     */   
/*     */   public String toString() {
/* 103 */     if (this.pathAsString == null) {
/* 104 */       StringBuffer buffer = new StringBuffer();
/* 105 */       for (int i = 0; i < this.chunks.length; i++) {
/* 106 */         if (i > 0) buffer.append('/');
/* 107 */         buffer.append(this.chunks[i]);
/*     */       }
/* 109 */       this.pathAsString = buffer.toString();
/*     */     }
/* 111 */     return this.pathAsString;
/*     */   }
/*     */   
/*     */   public String explicit() {
/* 115 */     if (this.pathExplicit == null) {
/* 116 */       StringBuffer buffer = new StringBuffer();
/* 117 */       for (int i = 0; i < this.chunks.length; i++) {
/* 118 */         if (i > 0) buffer.append('/');
/* 119 */         String chunk = this.chunks[i];
/* 120 */         buffer.append(chunk);
/* 121 */         int length = chunk.length();
/* 122 */         if (length > 0) {
/* 123 */           char c = chunk.charAt(length - 1);
/* 124 */           if ((c != ']') && (c != '.')) {
/* 125 */             buffer.append("[1]");
/*     */           }
/*     */         }
/*     */       }
/* 129 */       this.pathExplicit = buffer.toString();
/*     */     }
/* 131 */     return this.pathExplicit;
/*     */   }
/*     */   
/*     */   public boolean equals(Object o) {
/* 135 */     if (this == o) return true;
/* 136 */     if (!(o instanceof Path)) { return false;
/*     */     }
/* 138 */     Path other = (Path)o;
/* 139 */     if (this.chunks.length != other.chunks.length) return false;
/* 140 */     for (int i = 0; i < this.chunks.length; i++) {
/* 141 */       if (!this.chunks[i].equals(other.chunks[i])) { return false;
/*     */       }
/*     */     }
/* 144 */     return true;
/*     */   }
/*     */   
/*     */   public int hashCode() {
/* 148 */     int result = 543645643;
/* 149 */     for (int i = 0; i < this.chunks.length; i++) {
/* 150 */       result = 29 * result + this.chunks[i].hashCode();
/*     */     }
/* 152 */     return result;
/*     */   }
/*     */   
/*     */   public Path relativeTo(Path that) {
/* 156 */     int depthOfPathDivergence = depthOfPathDivergence(this.chunks, that.chunks);
/* 157 */     String[] result = new String[this.chunks.length + that.chunks.length - 2 * depthOfPathDivergence];
/* 158 */     int count = 0;
/*     */     
/* 160 */     for (int i = depthOfPathDivergence; i < this.chunks.length; i++) {
/* 161 */       result[(count++)] = "..";
/*     */     }
/* 163 */     for (int j = depthOfPathDivergence; j < that.chunks.length; j++) {
/* 164 */       result[(count++)] = that.chunks[j];
/*     */     }
/*     */     
/* 167 */     if (count == 0) {
/* 168 */       return DOT;
/*     */     }
/* 170 */     return new Path(result);
/*     */   }
/*     */   
/*     */   private int depthOfPathDivergence(String[] path1, String[] path2)
/*     */   {
/* 175 */     int minLength = Math.min(path1.length, path2.length);
/* 176 */     for (int i = 0; i < minLength; i++) {
/* 177 */       if (!path1[i].equals(path2[i])) {
/* 178 */         return i;
/*     */       }
/*     */     }
/* 181 */     return minLength;
/*     */   }
/*     */   
/*     */   public Path apply(Path relativePath) {
/* 185 */     FastStack absoluteStack = new FastStack(16);
/*     */     
/* 187 */     for (int i = 0; i < this.chunks.length; i++) {
/* 188 */       absoluteStack.push(this.chunks[i]);
/*     */     }
/*     */     
/* 191 */     for (int i = 0; i < relativePath.chunks.length; i++) {
/* 192 */       String relativeChunk = relativePath.chunks[i];
/* 193 */       if (relativeChunk.equals("..")) {
/* 194 */         absoluteStack.pop();
/* 195 */       } else if (!relativeChunk.equals(".")) {
/* 196 */         absoluteStack.push(relativeChunk);
/*     */       }
/*     */     }
/*     */     
/* 200 */     String[] result = new String[absoluteStack.size()];
/* 201 */     for (int i = 0; i < result.length; i++) {
/* 202 */       result[i] = ((String)absoluteStack.get(i));
/*     */     }
/*     */     
/* 205 */     return new Path(result);
/*     */   }
/*     */   
/*     */   public boolean isAncestor(Path child) {
/* 209 */     if ((child == null) || (child.chunks.length < this.chunks.length)) {
/* 210 */       return false;
/*     */     }
/* 212 */     for (int i = 0; i < this.chunks.length; i++) {
/* 213 */       if (!this.chunks[i].equals(child.chunks[i])) {
/* 214 */         return false;
/*     */       }
/*     */     }
/* 217 */     return true;
/*     */   }
/*     */ }


/* Location:              /home/menick/Scrivania/ProcessMining/CNMining/CNMining.jar!/com/thoughtworks/xstream/io/path/Path.class
 * Java compiler version: 3 (47.0)
 * JD-Core Version:       0.7.1
 */