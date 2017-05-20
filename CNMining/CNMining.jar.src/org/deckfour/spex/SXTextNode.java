/*    */ package org.deckfour.spex;
/*    */ 
/*    */ import java.io.IOException;
/*    */ import java.io.Writer;
/*    */ import org.deckfour.spex.util.SXmlCharacterMethods;
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
/*    */ 
/*    */ public class SXTextNode
/*    */   extends SXNode
/*    */ {
/*    */   public SXTextNode(String text, Writer aWriter, int aTabLevel, String aTabString)
/*    */     throws IOException
/*    */   {
/* 64 */     super(aWriter, aTabLevel, aTabString);
/* 65 */     this.writer.write(SXmlCharacterMethods.convertCharsToXml(text));
/*    */   }
/*    */   
/*    */   public void close()
/*    */     throws IOException
/*    */   {}
/*    */ }


/* Location:              /home/menick/Scrivania/ProcessMining/CNMining/CNMining.jar!/org/deckfour/spex/SXTextNode.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       0.7.1
 */