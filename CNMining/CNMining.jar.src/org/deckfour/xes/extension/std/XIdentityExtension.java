/*     */ package org.deckfour.xes.extension.std;
/*     */ 
/*     */ import java.net.URI;
/*     */ import java.util.HashSet;
/*     */ import org.deckfour.xes.extension.XExtension;
/*     */ import org.deckfour.xes.factory.XFactory;
/*     */ import org.deckfour.xes.factory.XFactoryRegistry;
/*     */ import org.deckfour.xes.id.XID;
/*     */ import org.deckfour.xes.id.XIDFactory;
/*     */ import org.deckfour.xes.info.XGlobalAttributeNameMap;
/*     */ import org.deckfour.xes.model.XAttributable;
/*     */ import org.deckfour.xes.model.XAttribute;
/*     */ import org.deckfour.xes.model.XAttributeID;
/*     */ import org.deckfour.xes.model.XAttributeMap;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class XIdentityExtension
/*     */   extends XExtension
/*     */ {
/*     */   private static final long serialVersionUID = -4908408129891998507L;
/*  66 */   public static final URI EXTENSION_URI = URI.create("http://www.xes-standard.org/identity.xesext");
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   public static final String KEY_ID = "identity:id";
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   public static XAttributeID ATTR_ID;
/*     */   
/*     */ 
/*     */ 
/*  80 */   private static transient XIdentityExtension singleton = new XIdentityExtension();
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public static XIdentityExtension instance()
/*     */   {
/*  88 */     return singleton;
/*     */   }
/*     */   
/*     */   private Object readResolve() {
/*  92 */     return singleton;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */   private XIdentityExtension()
/*     */   {
/*  99 */     super("Identity", "identity", EXTENSION_URI);
/* 100 */     XFactory factory = (XFactory)XFactoryRegistry.instance().currentDefault();
/* 101 */     ATTR_ID = factory.createAttributeID("identity:id", XIDFactory.instance().createId(), this);
/*     */     
/* 103 */     this.logAttributes.add((XAttribute)ATTR_ID.clone());
/* 104 */     this.traceAttributes.add((XAttribute)ATTR_ID.clone());
/* 105 */     this.eventAttributes.add((XAttribute)ATTR_ID.clone());
/* 106 */     this.metaAttributes.add((XAttribute)ATTR_ID.clone());
/*     */     
/* 108 */     XGlobalAttributeNameMap.instance().registerMapping("EN", "identity:id", "Identity");
/*     */     
/* 110 */     XGlobalAttributeNameMap.instance().registerMapping("DE", "identity:id", "Identität");
/*     */     
/* 112 */     XGlobalAttributeNameMap.instance().registerMapping("FR", "identity:id", "Identité");
/*     */     
/*     */ 
/* 115 */     XGlobalAttributeNameMap.instance().registerMapping("ES", "identity:id", "Identidad");
/*     */     
/* 117 */     XGlobalAttributeNameMap.instance().registerMapping("PT", "identity:id", "Identidade");
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
/*     */   public XID extractID(XAttributable element)
/*     */   {
/* 130 */     XAttribute attribute = (XAttribute)element.getAttributes().get("identity:id");
/* 131 */     if (attribute == null) {
/* 132 */       return null;
/*     */     }
/* 134 */     return ((XAttributeID)attribute).getValue();
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
/*     */   public void assignID(XAttributable element, XID id)
/*     */   {
/* 148 */     if (id != null) {
/* 149 */       XAttributeID attr = (XAttributeID)ATTR_ID.clone();
/* 150 */       attr.setValue(id);
/* 151 */       element.getAttributes().put("identity:id", attr);
/*     */     }
/*     */   }
/*     */ }


/* Location:              /home/menick/Scrivania/ProcessMining/CNMining/CNMining.jar!/org/deckfour/xes/extension/std/XIdentityExtension.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */