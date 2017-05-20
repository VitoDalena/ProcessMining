/*     */ package org.deckfour.xes.xstream;
/*     */ 
/*     */ import com.thoughtworks.xstream.XStream;
/*     */ import com.thoughtworks.xstream.converters.MarshallingContext;
/*     */ import com.thoughtworks.xstream.converters.UnmarshallingContext;
/*     */ import com.thoughtworks.xstream.io.HierarchicalStreamReader;
/*     */ import com.thoughtworks.xstream.io.HierarchicalStreamWriter;
/*     */ import java.net.URI;
/*     */ import org.deckfour.xes.extension.XExtension;
/*     */ import org.deckfour.xes.extension.XExtensionManager;
/*     */ import org.deckfour.xes.factory.XFactory;
/*     */ import org.deckfour.xes.factory.XFactoryRegistry;
/*     */ import org.deckfour.xes.model.XAttribute;
/*     */ import org.deckfour.xes.model.XAttributeMap;
/*     */ import org.deckfour.xes.util.XAttributeUtils;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class XAttributeConverter
/*     */   extends XConverter
/*     */ {
/*     */   public void marshal(Object obj, HierarchicalStreamWriter writer, MarshallingContext context)
/*     */   {
/*  79 */     XAttribute attribute = (XAttribute)obj;
/*  80 */     writer.addAttribute("key", attribute.getKey());
/*  81 */     writer.addAttribute("type", XAttributeUtils.getTypeString(attribute));
/*  82 */     String value = attribute.toString();
/*  83 */     if (value == null) {
/*  84 */       throw new AssertionError("Attribute value must not be null");
/*     */     }
/*  86 */     writer.addAttribute("value", value);
/*  87 */     if (attribute.getExtension() != null) {
/*  88 */       writer.addAttribute("extension", attribute.getExtension().getUri().toString());
/*     */     }
/*     */     
/*     */ 
/*  92 */     if (attribute.getAttributes().size() > 0) {
/*  93 */       writer.startNode("XAttributeMap");
/*  94 */       context.convertAnother(attribute.getAttributes(), XesXStreamPersistency.attributeMapConverter);
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
/* 110 */     String key = reader.getAttribute("key");
/* 111 */     String type = reader.getAttribute("type");
/* 112 */     String value = reader.getAttribute("value");
/* 113 */     XExtension extension = null;
/* 114 */     String extensionString = reader.getAttribute("extension");
/* 115 */     if ((extensionString != null) && (extensionString.length() > 0)) {
/* 116 */       URI uri = URI.create(extensionString);
/* 117 */       extension = XExtensionManager.instance().getByUri(uri);
/*     */     }
/* 119 */     XFactory factory = (XFactory)XFactoryRegistry.instance().currentDefault();
/* 120 */     XAttribute attribute = XAttributeUtils.composeAttribute(factory, key, value, type, extension);
/*     */     
/* 122 */     if (reader.hasMoreChildren()) {
/* 123 */       reader.moveDown();
/* 124 */       XAttributeMap metaAttributes = (XAttributeMap)context.convertAnother(attribute, XAttributeMap.class, XesXStreamPersistency.attributeMapConverter);
/*     */       
/*     */ 
/* 127 */       reader.moveUp();
/* 128 */       attribute.setAttributes(metaAttributes);
/*     */     }
/* 130 */     return attribute;
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
/* 142 */     return XAttribute.class.isAssignableFrom(c);
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
/* 154 */     stream.aliasType("XAttribute", XAttribute.class);
/*     */   }
/*     */ }


/* Location:              /home/menick/Scrivania/ProcessMining/CNMining/CNMining.jar!/org/deckfour/xes/xstream/XAttributeConverter.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */