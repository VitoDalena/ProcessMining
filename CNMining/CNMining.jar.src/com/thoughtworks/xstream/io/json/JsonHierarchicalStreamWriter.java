/*    */ package com.thoughtworks.xstream.io.json;
/*    */ 
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
/*    */ /**
/*    */  * @deprecated
/*    */  */
/*    */ public class JsonHierarchicalStreamWriter
/*    */   extends JsonWriter
/*    */ {
/*    */   /**
/*    */    * @deprecated
/*    */    */
/*    */   public JsonHierarchicalStreamWriter(Writer writer, char[] lineIndenter, String newLine)
/*    */   {
/* 32 */     super(writer, lineIndenter, newLine);
/*    */   }
/*    */   
/*    */   /**
/*    */    * @deprecated
/*    */    */
/*    */   public JsonHierarchicalStreamWriter(Writer writer, char[] lineIndenter) {
/* 39 */     this(writer, lineIndenter, "\n");
/*    */   }
/*    */   
/*    */   /**
/*    */    * @deprecated
/*    */    */
/*    */   public JsonHierarchicalStreamWriter(Writer writer, String lineIndenter, String newLine) {
/* 46 */     this(writer, lineIndenter.toCharArray(), newLine);
/*    */   }
/*    */   
/*    */   /**
/*    */    * @deprecated
/*    */    */
/*    */   public JsonHierarchicalStreamWriter(Writer writer, String lineIndenter) {
/* 53 */     this(writer, lineIndenter.toCharArray());
/*    */   }
/*    */   
/*    */   /**
/*    */    * @deprecated
/*    */    */
/*    */   public JsonHierarchicalStreamWriter(Writer writer) {
/* 60 */     this(writer, new char[] { ' ', ' ' });
/*    */   }
/*    */ }


/* Location:              /home/menick/Scrivania/ProcessMining/CNMining/CNMining.jar!/com/thoughtworks/xstream/io/json/JsonHierarchicalStreamWriter.class
 * Java compiler version: 3 (47.0)
 * JD-Core Version:       0.7.1
 */