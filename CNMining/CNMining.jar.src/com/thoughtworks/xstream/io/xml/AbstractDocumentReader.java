/*    */ package com.thoughtworks.xstream.io.xml;
/*    */ 
/*    */ import com.thoughtworks.xstream.converters.ErrorWriter;
/*    */ import com.thoughtworks.xstream.core.util.FastStack;
/*    */ import com.thoughtworks.xstream.io.AttributeNameIterator;
/*    */ import com.thoughtworks.xstream.io.naming.NameCoder;
/*    */ import java.util.Iterator;
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
/*    */ public abstract class AbstractDocumentReader
/*    */   extends AbstractXmlReader
/*    */   implements DocumentReader
/*    */ {
/* 23 */   private FastStack pointers = new FastStack(16);
/*    */   private Object current;
/*    */   
/*    */   protected AbstractDocumentReader(Object rootElement) {
/* 27 */     this(rootElement, new XmlFriendlyNameCoder());
/*    */   }
/*    */   
/*    */ 
/*    */ 
/*    */   protected AbstractDocumentReader(Object rootElement, NameCoder nameCoder)
/*    */   {
/* 34 */     super(nameCoder);
/* 35 */     this.current = rootElement;
/* 36 */     this.pointers.push(new Pointer(null));
/* 37 */     reassignCurrentElement(this.current);
/*    */   }
/*    */   
/*    */ 
/*    */ 
/*    */   /**
/*    */    * @deprecated
/*    */    */
/* 45 */   protected AbstractDocumentReader(Object rootElement, XmlFriendlyReplacer replacer) { this(rootElement, replacer); }
/*    */   
/*    */   protected abstract void reassignCurrentElement(Object paramObject);
/*    */   
/*    */   protected abstract Object getParent();
/*    */   
/*    */   private static class Pointer { private Pointer() {}
/*    */     public int v;
/* 53 */     Pointer(AbstractDocumentReader.1 x0) { this(); }
/*    */     
/*    */      }
/*    */   protected abstract Object getChild(int paramInt);
/*    */   protected abstract int getChildCount();
/* 58 */   public boolean hasMoreChildren() { Pointer pointer = (Pointer)this.pointers.peek();
/*    */     
/* 60 */     if (pointer.v < getChildCount()) {
/* 61 */       return true;
/*    */     }
/* 63 */     return false;
/*    */   }
/*    */   
/*    */   public void moveUp()
/*    */   {
/* 68 */     this.current = getParent();
/* 69 */     this.pointers.popSilently();
/* 70 */     reassignCurrentElement(this.current);
/*    */   }
/*    */   
/*    */   public void moveDown() {
/* 74 */     Pointer pointer = (Pointer)this.pointers.peek();
/* 75 */     this.pointers.push(new Pointer(null));
/*    */     
/* 77 */     this.current = getChild(pointer.v);
/*    */     
/* 79 */     pointer.v += 1;
/* 80 */     reassignCurrentElement(this.current);
/*    */   }
/*    */   
/*    */   public Iterator getAttributeNames() {
/* 84 */     return new AttributeNameIterator(this);
/*    */   }
/*    */   
/*    */   public void appendErrors(ErrorWriter errorWriter) {}
/*    */   
/*    */   public Object getCurrent()
/*    */   {
/* 91 */     return this.current;
/*    */   }
/*    */   
/*    */   public void close() {}
/*    */ }


/* Location:              /home/menick/Scrivania/ProcessMining/CNMining/CNMining.jar!/com/thoughtworks/xstream/io/xml/AbstractDocumentReader.class
 * Java compiler version: 3 (47.0)
 * JD-Core Version:       0.7.1
 */