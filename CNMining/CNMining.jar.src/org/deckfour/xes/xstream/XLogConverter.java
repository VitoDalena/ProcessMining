/*     */ package org.deckfour.xes.xstream;
/*     */ 
/*     */ import com.thoughtworks.xstream.XStream;
/*     */ import com.thoughtworks.xstream.converters.MarshallingContext;
/*     */ import com.thoughtworks.xstream.converters.UnmarshallingContext;
/*     */ import com.thoughtworks.xstream.io.HierarchicalStreamReader;
/*     */ import com.thoughtworks.xstream.io.HierarchicalStreamWriter;
/*     */ import java.util.List;
/*     */ import java.util.Set;
/*     */ import org.deckfour.xes.classification.XEventClassifier;
/*     */ import org.deckfour.xes.extension.XExtension;
/*     */ import org.deckfour.xes.factory.XFactory;
/*     */ import org.deckfour.xes.factory.XFactoryRegistry;
/*     */ import org.deckfour.xes.model.XAttribute;
/*     */ import org.deckfour.xes.model.XAttributeMap;
/*     */ import org.deckfour.xes.model.XLog;
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
/*     */ 
/*     */ 
/*     */ public class XLogConverter
/*     */   extends XConverter
/*     */ {
/*     */   public void marshal(Object obj, HierarchicalStreamWriter writer, MarshallingContext context)
/*     */   {
/*  82 */     XLog log = (XLog)obj;
/*     */     
/*  84 */     writer.startNode("XExtensions");
/*  85 */     context.convertAnother(log.getExtensions());
/*  86 */     writer.endNode();
/*     */     
/*  88 */     writer.startNode("XAttributeMap");
/*  89 */     context.convertAnother(log.getAttributes(), XesXStreamPersistency.attributeMapConverter);
/*  90 */     writer.endNode();
/*     */     
/*  92 */     writer.startNode("XGlobalTraceAttributes");
/*  93 */     context.convertAnother(log.getGlobalTraceAttributes());
/*  94 */     writer.endNode();
/*     */     
/*  96 */     writer.startNode("XGlobalEventAttributes");
/*  97 */     context.convertAnother(log.getGlobalEventAttributes());
/*  98 */     writer.endNode();
/*     */     
/* 100 */     writer.startNode("XEventClassifiers");
/* 101 */     context.convertAnother(log.getClassifiers());
/* 102 */     writer.endNode();
/*     */     
/* 104 */     for (XTrace trace : log) {
/* 105 */       writer.startNode("XTrace");
/* 106 */       context.convertAnother(trace, XesXStreamPersistency.traceConverter);
/* 107 */       writer.endNode();
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
/*     */ 
/*     */   public Object unmarshal(HierarchicalStreamReader reader, UnmarshallingContext context)
/*     */   {
/* 122 */     XFactory factory = (XFactory)XFactoryRegistry.instance().currentDefault();
/* 123 */     XLog log = factory.createLog();
/*     */     
/* 125 */     reader.moveDown();
/* 126 */     Set<XExtension> extensions = (Set)context.convertAnother(log, Set.class);
/*     */     
/* 128 */     log.getExtensions().addAll(extensions);
/* 129 */     reader.moveUp();
/*     */     
/* 131 */     reader.moveDown();
/* 132 */     XAttributeMap attributes = (XAttributeMap)context.convertAnother(log, XAttributeMap.class, XesXStreamPersistency.attributeMapConverter);
/*     */     
/* 134 */     log.setAttributes(attributes);
/* 135 */     reader.moveUp();
/*     */     
/* 137 */     reader.moveDown();
/* 138 */     List<XAttribute> traceGlobals = (List)context.convertAnother(log, List.class);
/*     */     
/* 140 */     log.getGlobalTraceAttributes().addAll(traceGlobals);
/* 141 */     reader.moveUp();
/*     */     
/* 143 */     reader.moveDown();
/* 144 */     List<XAttribute> eventGlobals = (List)context.convertAnother(log, List.class);
/*     */     
/* 146 */     log.getGlobalEventAttributes().addAll(eventGlobals);
/* 147 */     reader.moveUp();
/*     */     
/* 149 */     reader.moveDown();
/* 150 */     List<XEventClassifier> eventClassifiers = (List)context.convertAnother(log, List.class);
/*     */     
/* 152 */     log.getClassifiers().addAll(eventClassifiers);
/* 153 */     reader.moveUp();
/*     */     
/* 155 */     while (reader.hasMoreChildren()) {
/* 156 */       reader.moveDown();
/* 157 */       XTrace trace = (XTrace)context.convertAnother(log, XTrace.class, XesXStreamPersistency.traceConverter);
/* 158 */       log.add(trace);
/* 159 */       reader.moveUp();
/*     */     }
/* 161 */     return log;
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
/* 173 */     return XLog.class.isAssignableFrom(c);
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
/* 185 */     stream.aliasType("XLog", XLog.class);
/*     */   }
/*     */ }


/* Location:              /home/menick/Scrivania/ProcessMining/CNMining/CNMining.jar!/org/deckfour/xes/xstream/XLogConverter.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */