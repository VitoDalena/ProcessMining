/*    */ package org.jfree.ui;
/*    */ 
/*    */ import javax.swing.text.AttributeSet;
/*    */ import javax.swing.text.BadLocationException;
/*    */ import javax.swing.text.PlainDocument;
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
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class IntegerDocument
/*    */   extends PlainDocument
/*    */ {
/*    */   public void insertString(int i, String s, AttributeSet attributes)
/*    */     throws BadLocationException
/*    */   {
/* 69 */     super.insertString(i, s, attributes);
/* 70 */     if ((s != null) && ((!s.equals("-")) || (i != 0) || (s.length() >= 2))) {
/*    */       try {
/* 72 */         Integer.parseInt(getText(0, getLength()));
/*    */       }
/*    */       catch (NumberFormatException e) {
/* 75 */         remove(i, s.length());
/*    */       }
/*    */     }
/*    */   }
/*    */ }


/* Location:              /home/menick/Scrivania/ProcessMining/CNMining/CNMining.jar!/org/jfree/ui/IntegerDocument.class
 * Java compiler version: 2 (46.0)
 * JD-Core Version:       0.7.1
 */