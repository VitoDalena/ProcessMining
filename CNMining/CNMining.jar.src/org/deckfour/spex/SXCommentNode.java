/*    */ package org.deckfour.spex;
/*    */ 
/*    */ import java.io.IOException;
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
/*    */ public class SXCommentNode
/*    */   extends SXNode
/*    */ {
/*    */   public SXCommentNode(String comment, Writer aWriter, int aTabLevel, String aTabString)
/*    */     throws IOException
/*    */   {
/* 63 */     super(aWriter, aTabLevel, aTabString);
/* 64 */     indentLine();
/* 65 */     this.writer.write("<!-- " + comment + " -->");
/*    */   }
/*    */   
/*    */   public void close()
/*    */     throws IOException
/*    */   {}
/*    */ }


/* Location:              /home/menick/Scrivania/ProcessMining/CNMining/CNMining.jar!/org/deckfour/spex/SXCommentNode.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       0.7.1
 */