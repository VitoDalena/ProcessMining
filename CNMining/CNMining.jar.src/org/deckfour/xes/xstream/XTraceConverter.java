/*     */ package org.deckfour.xes.xstream;
/*     */ 
/*     */ import com.thoughtworks.xstream.XStream;
/*     */ import com.thoughtworks.xstream.converters.MarshallingContext;
/*     */ import com.thoughtworks.xstream.converters.UnmarshallingContext;
/*     */ import com.thoughtworks.xstream.io.HierarchicalStreamReader;
/*     */ import com.thoughtworks.xstream.io.HierarchicalStreamWriter;
/*     */ import org.deckfour.xes.factory.XFactory;
/*     */ import org.deckfour.xes.factory.XFactoryRegistry;
/*     */ import org.deckfour.xes.model.XAttributeMap;
/*     */ import org.deckfour.xes.model.XEvent;
/*     */ import org.deckfour.xes.model.XTrace;
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
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class XTraceConverter
/*     */   extends XConverter
/*     */ {
/*     */   public void marshal(Object obj, HierarchicalStreamWriter writer, MarshallingContext context)
/*     */   {
/*  75 */     XTrace trace = (XTrace)obj;
/*  76 */     writer.startNode("XAttributeMap");
/*  77 */     context.convertAnother(trace.getAttributes(), XesXStreamPersistency.attributeMapConverter);
/*     */     
/*  79 */     writer.endNode();
/*  80 */     for (XEvent event : trace) {
/*  81 */       writer.startNode("XEvent");
/*  82 */       context.convertAnother(event, XesXStreamPersistency.eventConverter);
/*  83 */       writer.endNode();
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
/*     */ 
/*     */   public Object unmarshal(HierarchicalStreamReader reader, UnmarshallingContext context)
/*     */   {
/*  97 */     XFactory factory = (XFactory)XFactoryRegistry.instance().currentDefault();
/*  98 */     XTrace trace = factory.createTrace();
/*  99 */     reader.moveDown();
/* 100 */     XAttributeMap attributes = (XAttributeMap)context.convertAnother(trace, XAttributeMap.class, XesXStreamPersistency.attributeMapConverter);
/*     */     
/*     */ 
/* 103 */     trace.setAttributes(attributes);
/* 104 */     reader.moveUp();
/* 105 */     while (reader.hasMoreChildren()) {
/* 106 */       reader.moveDown();
/* 107 */       XEvent event = (XEvent)context.convertAnother(trace, XEvent.class, XesXStreamPersistency.eventConverter);
/*     */       
/* 109 */       trace.add(event);
/* 110 */       reader.moveUp();
/*     */     }
/* 112 */     return trace;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public boolean canConvert(Class c)
/*     */   {
/* 124 */     return XTrace.class.isAssignableFrom(c);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public void registerAliases(XStream stream)
/*     */   {
/* 136 */     stream.aliasType("XTrace", XTrace.class);
/*     */   }
/*     */ }


/* Location:              /home/menick/Scrivania/ProcessMining/CNMining/CNMining.jar!/org/deckfour/xes/xstream/XTraceConverter.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */