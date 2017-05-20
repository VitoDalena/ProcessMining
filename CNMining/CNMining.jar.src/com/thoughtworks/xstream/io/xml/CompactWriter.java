/*    */ package com.thoughtworks.xstream.io.xml;
/*    */ 
/*    */ import com.thoughtworks.xstream.io.naming.NameCoder;
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
/*    */ public class CompactWriter
/*    */   extends PrettyPrintWriter
/*    */ {
/*    */   public CompactWriter(Writer writer)
/*    */   {
/* 21 */     super(writer);
/*    */   }
/*    */   
/*    */ 
/*    */ 
/*    */   public CompactWriter(Writer writer, int mode)
/*    */   {
/* 28 */     super(writer, mode);
/*    */   }
/*    */   
/*    */ 
/*    */ 
/*    */   public CompactWriter(Writer writer, NameCoder nameCoder)
/*    */   {
/* 35 */     super(writer, nameCoder);
/*    */   }
/*    */   
/*    */ 
/*    */ 
/*    */   public CompactWriter(Writer writer, int mode, NameCoder nameCoder)
/*    */   {
/* 42 */     super(writer, mode, nameCoder);
/*    */   }
/*    */   
/*    */   /**
/*    */    * @deprecated
/*    */    */
/*    */   public CompactWriter(Writer writer, XmlFriendlyReplacer replacer) {
/* 49 */     super(writer, replacer);
/*    */   }
/*    */   
/*    */   /**
/*    */    * @deprecated
/*    */    */
/*    */   public CompactWriter(Writer writer, int mode, XmlFriendlyReplacer replacer)
/*    */   {
/* 57 */     super(writer, mode, replacer);
/*    */   }
/*    */   
/*    */   protected void endOfLine() {}
/*    */ }


/* Location:              /home/menick/Scrivania/ProcessMining/CNMining/CNMining.jar!/com/thoughtworks/xstream/io/xml/CompactWriter.class
 * Java compiler version: 3 (47.0)
 * JD-Core Version:       0.7.1
 */