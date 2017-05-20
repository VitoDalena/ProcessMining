/*    */ package com.thoughtworks.xstream.io.json;
/*    */ 
/*    */ import com.thoughtworks.xstream.io.HierarchicalStreamDriver;
/*    */ import com.thoughtworks.xstream.io.HierarchicalStreamReader;
/*    */ import com.thoughtworks.xstream.io.HierarchicalStreamWriter;
/*    */ import com.thoughtworks.xstream.io.StreamException;
/*    */ import java.io.InputStream;
/*    */ import java.io.OutputStream;
/*    */ import java.io.OutputStreamWriter;
/*    */ import java.io.Reader;
/*    */ import java.io.UnsupportedEncodingException;
/*    */ import java.io.Writer;
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
/*    */ 
/*    */ 
/*    */ public class JsonHierarchicalStreamDriver
/*    */   implements HierarchicalStreamDriver
/*    */ {
/*    */   public HierarchicalStreamReader createReader(Reader in)
/*    */   {
/* 35 */     throw new UnsupportedOperationException("The JsonHierarchicalStreamDriver can only write JSON");
/*    */   }
/*    */   
/*    */   public HierarchicalStreamReader createReader(InputStream in) {
/* 39 */     throw new UnsupportedOperationException("The JsonHierarchicalStreamDriver can only write JSON");
/*    */   }
/*    */   
/*    */ 
/*    */ 
/*    */   public HierarchicalStreamWriter createWriter(Writer out)
/*    */   {
/* 46 */     return new JsonWriter(out);
/*    */   }
/*    */   
/*    */   public HierarchicalStreamWriter createWriter(OutputStream out)
/*    */   {
/*    */     try {
/* 52 */       return createWriter(new OutputStreamWriter(out, "UTF-8"));
/*    */     } catch (UnsupportedEncodingException e) {
/* 54 */       throw new StreamException(e);
/*    */     }
/*    */   }
/*    */ }


/* Location:              /home/menick/Scrivania/ProcessMining/CNMining/CNMining.jar!/com/thoughtworks/xstream/io/json/JsonHierarchicalStreamDriver.class
 * Java compiler version: 3 (47.0)
 * JD-Core Version:       0.7.1
 */