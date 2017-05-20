/*    */ package com.thoughtworks.xstream.io.binary;
/*    */ 
/*    */ import com.thoughtworks.xstream.io.ExtendedHierarchicalStreamWriter;
/*    */ import com.thoughtworks.xstream.io.HierarchicalStreamWriter;
/*    */ import com.thoughtworks.xstream.io.StreamException;
/*    */ import java.io.DataOutputStream;
/*    */ import java.io.IOException;
/*    */ import java.io.OutputStream;
/*    */ import java.util.HashMap;
/*    */ import java.util.Map;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class BinaryStreamWriter
/*    */   implements ExtendedHierarchicalStreamWriter
/*    */ {
/* 29 */   private final IdRegistry idRegistry = new IdRegistry(null);
/*    */   private final DataOutputStream out;
/* 31 */   private final Token.Formatter tokenFormatter = new Token.Formatter();
/*    */   
/*    */   public BinaryStreamWriter(OutputStream outputStream) {
/* 34 */     this.out = new DataOutputStream(outputStream);
/*    */   }
/*    */   
/*    */   public void startNode(String name) {
/* 38 */     write(new Token.StartNode(this.idRegistry.getId(name)));
/*    */   }
/*    */   
/*    */   public void startNode(String name, Class clazz) {
/* 42 */     startNode(name);
/*    */   }
/*    */   
/*    */   public void addAttribute(String name, String value) {
/* 46 */     write(new Token.Attribute(this.idRegistry.getId(name), value));
/*    */   }
/*    */   
/*    */   public void setValue(String text) {
/* 50 */     write(new Token.Value(text));
/*    */   }
/*    */   
/*    */   public void endNode() {
/* 54 */     write(new Token.EndNode());
/*    */   }
/*    */   
/*    */   public void flush() {
/*    */     try {
/* 59 */       this.out.flush();
/*    */     } catch (IOException e) {
/* 61 */       throw new StreamException(e);
/*    */     }
/*    */   }
/*    */   
/*    */   public void close() {
/*    */     try {
/* 67 */       this.out.close();
/*    */     } catch (IOException e) {
/* 69 */       throw new StreamException(e);
/*    */     }
/*    */   }
/*    */   
/*    */   public HierarchicalStreamWriter underlyingWriter() {
/* 74 */     return this;
/*    */   }
/*    */   
/*    */   private void write(Token token) {
/*    */     try {
/* 79 */       this.tokenFormatter.write(this.out, token);
/*    */     } catch (IOException e) {
/* 81 */       throw new StreamException(e);
/*    */     }
/*    */   }
/*    */   
/* 85 */   private class IdRegistry { IdRegistry(BinaryStreamWriter.1 x1) { this(); }
/*    */     
/* 87 */     private long nextId = 0L;
/* 88 */     private Map ids = new HashMap();
/*    */     
/*    */     public long getId(String value) {
/* 91 */       Long id = (Long)this.ids.get(value);
/* 92 */       if (id == null) {
/* 93 */         id = new Long(++this.nextId);
/* 94 */         this.ids.put(value, id);
/* 95 */         BinaryStreamWriter.this.write(new Token.MapIdToValue(id.longValue(), value));
/*    */       }
/* 97 */       return id.longValue();
/*    */     }
/*    */     
/*    */     private IdRegistry() {}
/*    */   }
/*    */ }


/* Location:              /home/menick/Scrivania/ProcessMining/CNMining/CNMining.jar!/com/thoughtworks/xstream/io/binary/BinaryStreamWriter.class
 * Java compiler version: 3 (47.0)
 * JD-Core Version:       0.7.1
 */