/*     */ package com.thoughtworks.xstream.io.xml;
/*     */ 
/*     */ import com.thoughtworks.xstream.core.util.FastStack;
/*     */ import com.thoughtworks.xstream.io.naming.NameCoder;
/*     */ import java.util.ArrayList;
/*     */ import java.util.List;
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
/*     */ public abstract class AbstractDocumentWriter
/*     */   extends AbstractXmlWriter
/*     */   implements DocumentWriter
/*     */ {
/*  33 */   private final List result = new ArrayList();
/*  34 */   private final FastStack nodeStack = new FastStack(16);
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public AbstractDocumentWriter(Object container, NameCoder nameCoder)
/*     */   {
/*  45 */     super(nameCoder);
/*  46 */     if (container != null) {
/*  47 */       this.nodeStack.push(container);
/*  48 */       this.result.add(container);
/*     */     }
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   /**
/*     */    * @deprecated
/*     */    */
/*     */   public AbstractDocumentWriter(Object container, XmlFriendlyReplacer replacer)
/*     */   {
/*  64 */     this(container, replacer);
/*     */   }
/*     */   
/*     */   public final void startNode(String name) {
/*  68 */     Object node = createNode(name);
/*  69 */     this.nodeStack.push(node);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   protected abstract Object createNode(String paramString);
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   public final void endNode()
/*     */   {
/*  83 */     endNodeInternally();
/*  84 */     Object node = this.nodeStack.pop();
/*  85 */     if (this.nodeStack.size() == 0) {
/*  86 */       this.result.add(node);
/*     */     }
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public void endNodeInternally() {}
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   protected final Object getCurrent()
/*     */   {
/* 102 */     return this.nodeStack.peek();
/*     */   }
/*     */   
/*     */   public List getTopLevelNodes() {
/* 106 */     return this.result;
/*     */   }
/*     */   
/*     */   public void flush() {}
/*     */   
/*     */   public void close() {}
/*     */ }


/* Location:              /home/menick/Scrivania/ProcessMining/CNMining/CNMining.jar!/com/thoughtworks/xstream/io/xml/AbstractDocumentWriter.class
 * Java compiler version: 3 (47.0)
 * JD-Core Version:       0.7.1
 */