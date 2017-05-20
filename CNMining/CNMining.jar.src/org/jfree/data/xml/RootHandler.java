/*     */ package org.jfree.data.xml;
/*     */ 
/*     */ import java.util.Stack;
/*     */ import org.xml.sax.SAXException;
/*     */ import org.xml.sax.helpers.DefaultHandler;
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
/*     */ public class RootHandler
/*     */   extends DefaultHandler
/*     */   implements DatasetTags
/*     */ {
/*     */   private Stack subHandlers;
/*     */   
/*     */   public RootHandler()
/*     */   {
/*  60 */     this.subHandlers = new Stack();
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public Stack getSubHandlers()
/*     */   {
/*  69 */     return this.subHandlers;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public void characters(char[] ch, int start, int length)
/*     */     throws SAXException
/*     */   {
/*  83 */     DefaultHandler handler = getCurrentHandler();
/*  84 */     if (handler != this) {
/*  85 */       handler.characters(ch, start, length);
/*     */     }
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public DefaultHandler getCurrentHandler()
/*     */   {
/*  95 */     DefaultHandler result = this;
/*  96 */     if ((this.subHandlers != null) && 
/*  97 */       (this.subHandlers.size() > 0)) {
/*  98 */       Object top = this.subHandlers.peek();
/*  99 */       if (top != null) {
/* 100 */         result = (DefaultHandler)top;
/*     */       }
/*     */     }
/*     */     
/* 104 */     return result;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public void pushSubHandler(DefaultHandler subhandler)
/*     */   {
/* 113 */     this.subHandlers.push(subhandler);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public DefaultHandler popSubHandler()
/*     */   {
/* 122 */     return (DefaultHandler)this.subHandlers.pop();
/*     */   }
/*     */ }


/* Location:              /home/menick/Scrivania/ProcessMining/CNMining/CNMining.jar!/org/jfree/data/xml/RootHandler.class
 * Java compiler version: 3 (47.0)
 * JD-Core Version:       0.7.1
 */