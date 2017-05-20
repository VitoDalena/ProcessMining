/*     */ package org.deckfour.xes.xstream;
/*     */ 
/*     */ import com.thoughtworks.xstream.XStream;
/*     */ import com.thoughtworks.xstream.converters.MarshallingContext;
/*     */ import com.thoughtworks.xstream.converters.UnmarshallingContext;
/*     */ import com.thoughtworks.xstream.io.HierarchicalStreamReader;
/*     */ import com.thoughtworks.xstream.io.HierarchicalStreamWriter;
/*     */ import org.deckfour.xes.id.XID;
/*     */ import org.deckfour.xes.model.XAttributeMap;
/*     */ import org.deckfour.xes.model.XEvent;
/*     */ import org.deckfour.xes.model.impl.XEventImpl;
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
/*     */ public class XEventConverter
/*     */   extends XConverter
/*     */ {
/*     */   public void marshal(Object obj, HierarchicalStreamWriter writer, MarshallingContext context)
/*     */   {
/*  74 */     XEvent event = (XEvent)obj;
/*  75 */     writer.addAttribute("xid", event.getID().toString());
/*  76 */     if (event.getAttributes().size() > 0) {
/*  77 */       writer.startNode("XAttributeMap");
/*  78 */       context.convertAnother(event.getAttributes(), XesXStreamPersistency.attributeMapConverter);
/*  79 */       writer.endNode();
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
/*  94 */     XEventImpl event = new XEventImpl();
/*     */     
/*  96 */     String id = reader.getAttribute("xid");
/*  97 */     event.setID(XID.parse(id));
/*     */     
/*  99 */     if (reader.hasMoreChildren()) {
/* 100 */       reader.moveDown();
/* 101 */       XAttributeMap attributes = (XAttributeMap)context.convertAnother(event, XAttributeMap.class, XesXStreamPersistency.attributeMapConverter);
/*     */       
/* 103 */       event.setAttributes(attributes);
/* 104 */       reader.moveUp();
/*     */     }
/* 106 */     return event;
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
/* 118 */     return XEvent.class.isAssignableFrom(c);
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
/* 130 */     stream.aliasType("XEvent", XEvent.class);
/*     */   }
/*     */ }


/* Location:              /home/menick/Scrivania/ProcessMining/CNMining/CNMining.jar!/org/deckfour/xes/xstream/XEventConverter.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */