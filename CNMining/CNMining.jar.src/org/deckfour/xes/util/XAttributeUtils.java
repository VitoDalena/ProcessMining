/*     */ package org.deckfour.xes.util;
/*     */ 
/*     */ import java.text.ParseException;
/*     */ import java.util.HashSet;
/*     */ import java.util.Map;
/*     */ import java.util.Set;
/*     */ import org.deckfour.xes.extension.XExtension;
/*     */ import org.deckfour.xes.factory.XFactory;
/*     */ import org.deckfour.xes.id.XID;
/*     */ import org.deckfour.xes.id.XIDFactory;
/*     */ import org.deckfour.xes.model.XAttribute;
/*     */ import org.deckfour.xes.model.XAttributeBoolean;
/*     */ import org.deckfour.xes.model.XAttributeContinuous;
/*     */ import org.deckfour.xes.model.XAttributeDiscrete;
/*     */ import org.deckfour.xes.model.XAttributeID;
/*     */ import org.deckfour.xes.model.XAttributeLiteral;
/*     */ import org.deckfour.xes.model.XAttributeTimestamp;
/*     */ import org.deckfour.xes.model.impl.XsDateTimeFormat;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class XAttributeUtils
/*     */ {
/*     */   public static Class<? extends XAttribute> getType(XAttribute attribute)
/*     */   {
/*  74 */     if ((attribute instanceof XAttributeLiteral))
/*  75 */       return XAttributeLiteral.class;
/*  76 */     if ((attribute instanceof XAttributeBoolean))
/*  77 */       return XAttributeBoolean.class;
/*  78 */     if ((attribute instanceof XAttributeContinuous))
/*  79 */       return XAttributeContinuous.class;
/*  80 */     if ((attribute instanceof XAttributeDiscrete))
/*  81 */       return XAttributeDiscrete.class;
/*  82 */     if ((attribute instanceof XAttributeTimestamp))
/*  83 */       return XAttributeTimestamp.class;
/*  84 */     if ((attribute instanceof XAttributeID)) {
/*  85 */       return XAttributeID.class;
/*     */     }
/*  87 */     throw new AssertionError("Unexpected attribute type!");
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
/*     */   public static String getTypeString(XAttribute attribute)
/*     */   {
/* 100 */     if ((attribute instanceof XAttributeLiteral))
/* 101 */       return "LITERAL";
/* 102 */     if ((attribute instanceof XAttributeBoolean))
/* 103 */       return "BOOLEAN";
/* 104 */     if ((attribute instanceof XAttributeContinuous))
/* 105 */       return "CONTINUOUS";
/* 106 */     if ((attribute instanceof XAttributeDiscrete))
/* 107 */       return "DISCRETE";
/* 108 */     if ((attribute instanceof XAttributeTimestamp))
/* 109 */       return "TIMESTAMP";
/* 110 */     if ((attribute instanceof XAttributeID)) {
/* 111 */       return "ID";
/*     */     }
/* 113 */     throw new AssertionError("Unexpected attribute type!");
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
/*     */ 
/*     */   public static XAttribute derivePrototype(XAttribute instance)
/*     */   {
/* 128 */     XAttribute prototype = (XAttribute)instance.clone();
/* 129 */     if ((prototype instanceof XAttributeLiteral)) {
/* 130 */       ((XAttributeLiteral)prototype).setValue("DEFAULT");
/* 131 */     } else if ((prototype instanceof XAttributeBoolean)) {
/* 132 */       ((XAttributeBoolean)prototype).setValue(true);
/* 133 */     } else if ((prototype instanceof XAttributeContinuous)) {
/* 134 */       ((XAttributeContinuous)prototype).setValue(0.0D);
/* 135 */     } else if ((prototype instanceof XAttributeDiscrete)) {
/* 136 */       ((XAttributeDiscrete)prototype).setValue(0L);
/* 137 */     } else if ((prototype instanceof XAttributeTimestamp)) {
/* 138 */       ((XAttributeTimestamp)prototype).setValueMillis(0L);
/* 139 */     } else if ((prototype instanceof XAttributeID)) {
/* 140 */       ((XAttributeID)prototype).setValue(XIDFactory.instance().createId());
/*     */     } else {
/* 142 */       throw new AssertionError("Unexpected attribute type!");
/*     */     }
/* 144 */     return prototype;
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
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public static XAttribute composeAttribute(XFactory factory, String key, String value, String type, XExtension extension)
/*     */   {
/* 165 */     type = type.trim();
/* 166 */     if (type.equalsIgnoreCase("LITERAL")) {
/* 167 */       XAttributeLiteral attr = factory.createAttributeLiteral(key, value, extension);
/*     */       
/* 169 */       return attr; }
/* 170 */     if (type.equalsIgnoreCase("BOOLEAN")) {
/* 171 */       XAttributeBoolean attr = factory.createAttributeBoolean(key, Boolean.parseBoolean(value), extension);
/*     */       
/* 173 */       return attr; }
/* 174 */     if (type.equalsIgnoreCase("CONTINUOUS")) {
/* 175 */       XAttributeContinuous attr = factory.createAttributeContinuous(key, Double.parseDouble(value), extension);
/*     */       
/* 177 */       return attr; }
/* 178 */     if (type.equalsIgnoreCase("DISCRETE")) {
/* 179 */       XAttributeDiscrete attr = factory.createAttributeDiscrete(key, Long.parseLong(value), extension);
/*     */       
/* 181 */       return attr; }
/* 182 */     if (type.equalsIgnoreCase("TIMESTAMP")) {
/*     */       XAttributeTimestamp attr;
/*     */       try {
/* 185 */         synchronized (XAttributeTimestamp.FORMATTER) {
/* 186 */           attr = factory.createAttributeTimestamp(key, XAttributeTimestamp.FORMATTER.parseObject(value), extension);
/*     */         }
/*     */       }
/*     */       catch (ParseException e)
/*     */       {
/* 191 */         throw new AssertionError("OpenXES: could not parse date-time attribute. Value: " + value);
/*     */       }
/*     */       
/*     */ 
/* 195 */       return attr; }
/* 196 */     if (type.equalsIgnoreCase("ID")) {
/* 197 */       XAttributeID attr = factory.createAttributeID(key, XID.parse(value), extension);
/* 198 */       return attr;
/*     */     }
/* 200 */     throw new AssertionError("OpenXES: could not parse attribute type!");
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
/*     */   public static Set<XExtension> extractExtensions(Map<String, XAttribute> attributeMap)
/*     */   {
/* 213 */     HashSet<XExtension> extensions = new HashSet();
/* 214 */     for (XAttribute attribute : attributeMap.values()) {
/* 215 */       XExtension extension = attribute.getExtension();
/* 216 */       if (extension != null) {
/* 217 */         extensions.add(extension);
/*     */       }
/*     */     }
/* 220 */     return extensions;
/*     */   }
/*     */ }


/* Location:              /home/menick/Scrivania/ProcessMining/CNMining/CNMining.jar!/org/deckfour/xes/util/XAttributeUtils.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */