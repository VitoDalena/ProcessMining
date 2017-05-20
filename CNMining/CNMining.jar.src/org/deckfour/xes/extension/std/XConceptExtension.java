/*     */ package org.deckfour.xes.extension.std;
/*     */ 
/*     */ import java.net.URI;
/*     */ import java.util.HashSet;
/*     */ import org.deckfour.xes.extension.XExtension;
/*     */ import org.deckfour.xes.factory.XFactory;
/*     */ import org.deckfour.xes.factory.XFactoryRegistry;
/*     */ import org.deckfour.xes.info.XGlobalAttributeNameMap;
/*     */ import org.deckfour.xes.model.XAttributable;
/*     */ import org.deckfour.xes.model.XAttribute;
/*     */ import org.deckfour.xes.model.XAttributeLiteral;
/*     */ import org.deckfour.xes.model.XAttributeMap;
/*     */ import org.deckfour.xes.model.XEvent;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class XConceptExtension
/*     */   extends XExtension
/*     */ {
/*     */   private static final long serialVersionUID = 6604751608301985546L;
/*  74 */   public static final URI EXTENSION_URI = URI.create("http://www.xes-standard.org/concept.xesext");
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   public static final String KEY_NAME = "concept:name";
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   public static final String KEY_INSTANCE = "concept:instance";
/*     */   
/*     */ 
/*     */ 
/*     */   public static XAttributeLiteral ATTR_NAME;
/*     */   
/*     */ 
/*     */ 
/*     */   public static XAttributeLiteral ATTR_INSTANCE;
/*     */   
/*     */ 
/*     */ 
/*  96 */   private static transient XConceptExtension singleton = new XConceptExtension();
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public static XConceptExtension instance()
/*     */   {
/* 104 */     return singleton;
/*     */   }
/*     */   
/*     */   private Object readResolve() {
/* 108 */     return singleton;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */   private XConceptExtension()
/*     */   {
/* 115 */     super("Concept", "concept", EXTENSION_URI);
/* 116 */     XFactory factory = (XFactory)XFactoryRegistry.instance().currentDefault();
/* 117 */     ATTR_NAME = factory.createAttributeLiteral("concept:name", "__INVALID__", this);
/*     */     
/* 119 */     ATTR_INSTANCE = factory.createAttributeLiteral("concept:instance", "__INVALID__", this);
/*     */     
/* 121 */     this.logAttributes.add((XAttribute)ATTR_NAME.clone());
/* 122 */     this.traceAttributes.add((XAttribute)ATTR_NAME.clone());
/* 123 */     this.eventAttributes.add((XAttribute)ATTR_NAME.clone());
/* 124 */     this.eventAttributes.add((XAttribute)ATTR_INSTANCE.clone());
/*     */     
/* 126 */     XGlobalAttributeNameMap.instance().registerMapping("EN", "concept:name", "Name");
/*     */     
/* 128 */     XGlobalAttributeNameMap.instance().registerMapping("EN", "concept:instance", "Instance");
/*     */     
/*     */ 
/* 131 */     XGlobalAttributeNameMap.instance().registerMapping("DE", "concept:name", "Name");
/*     */     
/* 133 */     XGlobalAttributeNameMap.instance().registerMapping("DE", "concept:instance", "Instanz");
/*     */     
/*     */ 
/* 136 */     XGlobalAttributeNameMap.instance().registerMapping("FR", "concept:name", "Appellation");
/*     */     
/*     */ 
/* 139 */     XGlobalAttributeNameMap.instance().registerMapping("FR", "concept:instance", "Entité");
/*     */     
/*     */ 
/* 142 */     XGlobalAttributeNameMap.instance().registerMapping("ES", "concept:name", "Nombre");
/*     */     
/* 144 */     XGlobalAttributeNameMap.instance().registerMapping("ES", "concept:instance", "Instancia");
/*     */     
/*     */ 
/* 147 */     XGlobalAttributeNameMap.instance().registerMapping("PT", "concept:name", "Nome");
/*     */     
/* 149 */     XGlobalAttributeNameMap.instance().registerMapping("PT", "concept:instance", "Instância");
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
/*     */   public String extractName(XAttributable element)
/*     */   {
/* 163 */     XAttribute attribute = (XAttribute)element.getAttributes().get("concept:name");
/* 164 */     if (attribute == null) {
/* 165 */       return null;
/*     */     }
/* 167 */     return ((XAttributeLiteral)attribute).getValue();
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
/*     */   public void assignName(XAttributable element, String name)
/*     */   {
/* 181 */     if ((name != null) && (name.trim().length() > 0)) {
/* 182 */       XAttributeLiteral attr = (XAttributeLiteral)ATTR_NAME.clone();
/* 183 */       attr.setValue(name);
/* 184 */       element.getAttributes().put("concept:name", attr);
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
/*     */   public String extractInstance(XEvent event)
/*     */   {
/* 197 */     XAttribute attribute = (XAttribute)event.getAttributes().get("concept:instance");
/* 198 */     if (attribute == null) {
/* 199 */       return null;
/*     */     }
/* 201 */     return ((XAttributeLiteral)attribute).getValue();
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
/*     */   public void assignInstance(XEvent event, String instance)
/*     */   {
/* 215 */     if ((instance != null) && (instance.trim().length() > 0)) {
/* 216 */       XAttributeLiteral attr = (XAttributeLiteral)ATTR_INSTANCE.clone();
/* 217 */       attr.setValue(instance);
/* 218 */       event.getAttributes().put("concept:instance", attr);
/*     */     }
/*     */   }
/*     */ }


/* Location:              /home/menick/Scrivania/ProcessMining/CNMining/CNMining.jar!/org/deckfour/xes/extension/std/XConceptExtension.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */