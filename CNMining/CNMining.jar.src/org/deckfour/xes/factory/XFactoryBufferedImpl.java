/*     */ package org.deckfour.xes.factory;
/*     */ 
/*     */ import java.net.URI;
/*     */ import org.deckfour.xes.model.XAttributeMap;
/*     */ import org.deckfour.xes.model.XLog;
/*     */ import org.deckfour.xes.model.XTrace;
/*     */ import org.deckfour.xes.model.buffered.XAttributeMapBufferedImpl;
/*     */ import org.deckfour.xes.model.buffered.XAttributeMapSerializerImpl;
/*     */ import org.deckfour.xes.model.buffered.XTraceBufferedImpl;
/*     */ import org.deckfour.xes.model.impl.XAttributeMapLazyImpl;
/*     */ import org.deckfour.xes.model.impl.XLogImpl;
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
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class XFactoryBufferedImpl
/*     */   extends XFactoryNaiveImpl
/*     */ {
/*     */   public String getAuthor()
/*     */   {
/*  68 */     return "Christian W. Günther";
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   public String getDescription()
/*     */   {
/*  76 */     return "Creates buffered implementations for all available model hierarchy elements, i.e., the latest OpenXES standard optimizations will be employed.";
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public String getName()
/*     */   {
/*  86 */     return "Standard / buffered";
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   public URI getUri()
/*     */   {
/*  94 */     return URI.create("http://www.xes-standard.org/");
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   public String getVendor()
/*     */   {
/* 102 */     return "xes-standard.org";
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   public XAttributeMap createAttributeMap()
/*     */   {
/* 110 */     return new XAttributeMapBufferedImpl();
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */   public XLog createLog()
/*     */   {
/* 117 */     return new XLogImpl(new XAttributeMapLazyImpl(XAttributeMapBufferedImpl.class));
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   public XTrace createTrace()
/*     */   {
/* 125 */     return new XTraceBufferedImpl(new XAttributeMapLazyImpl(XAttributeMapBufferedImpl.class), new XAttributeMapSerializerImpl());
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public XTrace createTrace(XAttributeMap attributes)
/*     */   {
/* 135 */     return new XTraceBufferedImpl(attributes, new XAttributeMapSerializerImpl());
/*     */   }
/*     */ }


/* Location:              /home/menick/Scrivania/ProcessMining/CNMining/CNMining.jar!/org/deckfour/xes/factory/XFactoryBufferedImpl.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */