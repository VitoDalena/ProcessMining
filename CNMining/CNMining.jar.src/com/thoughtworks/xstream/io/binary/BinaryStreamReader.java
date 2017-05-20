/*     */ package com.thoughtworks.xstream.io.binary;
/*     */ 
/*     */ import com.thoughtworks.xstream.converters.ErrorWriter;
/*     */ import com.thoughtworks.xstream.io.HierarchicalStreamReader;
/*     */ import com.thoughtworks.xstream.io.StreamException;
/*     */ import java.io.DataInputStream;
/*     */ import java.io.IOException;
/*     */ import java.io.InputStream;
/*     */ import java.util.HashMap;
/*     */ import java.util.Iterator;
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
/*     */ public class BinaryStreamReader
/*     */   implements HierarchicalStreamReader
/*     */ {
/*     */   private final DataInputStream in;
/*  38 */   private final ReaderDepthState depthState = new ReaderDepthState();
/*  39 */   private final IdRegistry idRegistry = new IdRegistry(null);
/*     */   
/*     */   private Token pushback;
/*  42 */   private final Token.Formatter tokenFormatter = new Token.Formatter();
/*     */   
/*     */   public BinaryStreamReader(InputStream inputStream) {
/*  45 */     this.in = new DataInputStream(inputStream);
/*  46 */     moveDown();
/*     */   }
/*     */   
/*     */   public boolean hasMoreChildren() {
/*  50 */     return this.depthState.hasMoreChildren();
/*     */   }
/*     */   
/*     */   public String getNodeName() {
/*  54 */     return this.depthState.getName();
/*     */   }
/*     */   
/*     */   public String getValue() {
/*  58 */     return this.depthState.getValue();
/*     */   }
/*     */   
/*     */   public String getAttribute(String name) {
/*  62 */     return this.depthState.getAttribute(name);
/*     */   }
/*     */   
/*     */   public String getAttribute(int index) {
/*  66 */     return this.depthState.getAttribute(index);
/*     */   }
/*     */   
/*     */   public int getAttributeCount() {
/*  70 */     return this.depthState.getAttributeCount();
/*     */   }
/*     */   
/*     */   public String getAttributeName(int index) {
/*  74 */     return this.depthState.getAttributeName(index);
/*     */   }
/*     */   
/*     */   public Iterator getAttributeNames() {
/*  78 */     return this.depthState.getAttributeNames();
/*     */   }
/*     */   
/*     */   public void moveDown() {
/*  82 */     this.depthState.push();
/*  83 */     Token firstToken = readToken();
/*  84 */     switch (firstToken.getType()) {
/*     */     case 3: 
/*  86 */       this.depthState.setName(this.idRegistry.get(firstToken.getId()));
/*  87 */       break;
/*     */     default: 
/*  89 */       throw new StreamException("Expected StartNode");
/*     */     }
/*     */     for (;;) {
/*  92 */       Token nextToken = readToken();
/*  93 */       switch (nextToken.getType()) {
/*     */       case 5: 
/*  95 */         this.depthState.addAttribute(this.idRegistry.get(nextToken.getId()), nextToken.getValue());
/*  96 */         break;
/*     */       case 6: 
/*  98 */         this.depthState.setValue(nextToken.getValue());
/*  99 */         break;
/*     */       case 4: 
/* 101 */         this.depthState.setHasMoreChildren(false);
/* 102 */         pushBack(nextToken);
/* 103 */         return;
/*     */       case 3: 
/* 105 */         this.depthState.setHasMoreChildren(true);
/* 106 */         pushBack(nextToken);
/* 107 */         return;
/*     */       default: 
/* 109 */         throw new StreamException("Unexpected token " + nextToken);
/*     */       }
/*     */     }
/*     */   }
/*     */   
/*     */   public void moveUp() {
/* 115 */     this.depthState.pop();
/*     */     
/* 117 */     int depth = 0;
/*     */     for (;;)
/*     */     {
/* 120 */       Token nextToken = readToken();
/* 121 */       switch (nextToken.getType()) {
/*     */       case 4: 
/* 123 */         if (depth == 0) {
/*     */           break label66;
/*     */         }
/* 126 */         depth--;
/*     */         
/* 128 */         break;
/*     */       case 3: 
/* 130 */         depth++;
/*     */       }
/*     */       
/*     */     }
/*     */     
/*     */     label66:
/*     */     
/* 137 */     Token nextToken = readToken();
/* 138 */     switch (nextToken.getType()) {
/*     */     case 4: 
/* 140 */       this.depthState.setHasMoreChildren(false);
/* 141 */       break;
/*     */     case 3: 
/* 143 */       this.depthState.setHasMoreChildren(true);
/* 144 */       break;
/*     */     default: 
/* 146 */       throw new StreamException("Unexpected token " + nextToken);
/*     */     }
/* 148 */     pushBack(nextToken);
/*     */   }
/*     */   
/*     */   private Token readToken() {
/* 152 */     if (this.pushback == null) {
/*     */       try {
/* 154 */         Token token = this.tokenFormatter.read(this.in);
/* 155 */         switch (token.getType()) {
/*     */         case 2: 
/* 157 */           this.idRegistry.put(token.getId(), token.getValue());
/* 158 */           return readToken();
/*     */         }
/* 160 */         return token;
/*     */       }
/*     */       catch (IOException e) {
/* 163 */         throw new StreamException(e);
/*     */       }
/*     */     }
/* 166 */     Token result = this.pushback;
/* 167 */     this.pushback = null;
/* 168 */     return result;
/*     */   }
/*     */   
/*     */   public void pushBack(Token token)
/*     */   {
/* 173 */     if (this.pushback == null) {
/* 174 */       this.pushback = token;
/*     */     }
/*     */     else {
/* 177 */       throw new Error("Cannot push more than one token back");
/*     */     }
/*     */   }
/*     */   
/*     */   public void close() {
/*     */     try {
/* 183 */       this.in.close();
/*     */     } catch (IOException e) {
/* 185 */       throw new StreamException(e);
/*     */     }
/*     */   }
/*     */   
/*     */   public HierarchicalStreamReader underlyingReader() {
/* 190 */     return this;
/*     */   }
/*     */   
/*     */ 
/*     */   public void appendErrors(ErrorWriter errorWriter) {}
/*     */   
/*     */   private static class IdRegistry {
/* 197 */     IdRegistry(BinaryStreamReader.1 x0) { this(); }
/*     */     
/* 199 */     private Map map = new HashMap();
/*     */     
/*     */     public void put(long id, String value) {
/* 202 */       this.map.put(new Long(id), value);
/*     */     }
/*     */     
/*     */     public String get(long id) {
/* 206 */       String result = (String)this.map.get(new Long(id));
/* 207 */       if (result == null) {
/* 208 */         throw new StreamException("Unknown ID : " + id);
/*     */       }
/* 210 */       return result;
/*     */     }
/*     */     
/*     */     private IdRegistry() {}
/*     */   }
/*     */ }


/* Location:              /home/menick/Scrivania/ProcessMining/CNMining/CNMining.jar!/com/thoughtworks/xstream/io/binary/BinaryStreamReader.class
 * Java compiler version: 3 (47.0)
 * JD-Core Version:       0.7.1
 */