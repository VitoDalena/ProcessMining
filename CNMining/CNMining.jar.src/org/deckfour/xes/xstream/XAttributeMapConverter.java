/*     */ package org.deckfour.xes.xstream;
/*     */ 
/*     */ import com.thoughtworks.xstream.XStream;
/*     */ import com.thoughtworks.xstream.converters.MarshallingContext;
/*     */ import com.thoughtworks.xstream.converters.UnmarshallingContext;
/*     */ import com.thoughtworks.xstream.io.HierarchicalStreamReader;
/*     */ import com.thoughtworks.xstream.io.HierarchicalStreamWriter;
/*     */ import org.deckfour.xes.model.XAttribute;
/*     */ import org.deckfour.xes.model.XAttributeMap;
/*     */ import org.deckfour.xes.model.buffered.XAttributeMapBufferedImpl;
/*     */ import org.deckfour.xes.model.impl.XAttributeMapImpl;
/*     */ import org.deckfour.xes.model.impl.XAttributeMapLazyImpl;
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
/*     */ public class XAttributeMapConverter
/*     */   extends XConverter
/*     */ {
/*     */   public void marshal(Object obj, HierarchicalStreamWriter writer, MarshallingContext context)
/*     */   {
/*  76 */     XAttributeMap map = (XAttributeMap)obj;
/*  77 */     String lazy = "false";
/*  78 */     String buffered = "false";
/*     */     
/*     */ 
/*  81 */     if ((map instanceof XAttributeMapLazyImpl)) {
/*  82 */       lazy = "true";
/*  83 */       if (((XAttributeMapLazyImpl)map).getBackingStoreClass().equals(XAttributeMapBufferedImpl.class))
/*     */       {
/*  85 */         buffered = "true";
/*     */       }
/*  87 */     } else if ((map instanceof XAttributeMapBufferedImpl)) {
/*  88 */       buffered = "true";
/*     */     }
/*  90 */     writer.addAttribute("lazy", lazy);
/*  91 */     writer.addAttribute("buffered", buffered);
/*  92 */     for (XAttribute attribute : map.values()) {
/*  93 */       writer.startNode("XAttribute");
/*  94 */       context.convertAnother(attribute, XesXStreamPersistency.attributeConverter);
/*     */       
/*  96 */       writer.endNode();
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
/* 110 */     XAttributeMap map = null;
/*     */     
/*     */ 
/*     */ 
/* 114 */     boolean lazy = reader.getAttribute("lazy").equals("true");
/* 115 */     boolean buffered = reader.getAttribute("buffered").equals("true");
/* 116 */     if (lazy) {
/* 117 */       if (buffered) {
/* 118 */         map = new XAttributeMapLazyImpl(XAttributeMapBufferedImpl.class);
/*     */       }
/*     */       else {
/* 121 */         map = new XAttributeMapLazyImpl(XAttributeMapImpl.class);
/*     */       }
/*     */     }
/* 124 */     else if (buffered) {
/* 125 */       map = new XAttributeMapBufferedImpl();
/*     */     } else {
/* 127 */       map = new XAttributeMapImpl();
/*     */     }
/* 129 */     while (reader.hasMoreChildren()) {
/* 130 */       reader.moveDown();
/* 131 */       XAttribute attribute = (XAttribute)context.convertAnother(map, XAttribute.class, XesXStreamPersistency.attributeConverter);
/*     */       
/* 133 */       map.put(attribute.getKey(), attribute);
/* 134 */       reader.moveUp();
/*     */     }
/* 136 */     return map;
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
/* 148 */     return XAttributeMap.class.isAssignableFrom(c);
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
/* 160 */     stream.aliasType("XAttributeMap", XAttributeMap.class);
/*     */   }
/*     */ }


/* Location:              /home/menick/Scrivania/ProcessMining/CNMining/CNMining.jar!/org/deckfour/xes/xstream/XAttributeMapConverter.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */