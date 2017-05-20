/*     */ package org.deckfour.xes.extension.std;
/*     */ 
/*     */ import java.net.URI;
/*     */ import java.util.HashSet;
/*     */ import org.deckfour.xes.extension.XExtension;
/*     */ import org.deckfour.xes.factory.XFactory;
/*     */ import org.deckfour.xes.factory.XFactoryRegistry;
/*     */ import org.deckfour.xes.info.XGlobalAttributeNameMap;
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
/*     */ 
/*     */ public class XOrganizationalExtension
/*     */   extends XExtension
/*     */ {
/*     */   private static final long serialVersionUID = -8578385457800103461L;
/*  74 */   public static final URI EXTENSION_URI = URI.create("http://www.xes-standard.org/org.xesext");
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   public static final String KEY_RESOURCE = "org:resource";
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   public static final String KEY_ROLE = "org:role";
/*     */   
/*     */ 
/*     */ 
/*     */   public static final String KEY_GROUP = "org:group";
/*     */   
/*     */ 
/*     */ 
/*     */   public static XAttributeLiteral ATTR_RESOURCE;
/*     */   
/*     */ 
/*     */ 
/*     */   public static XAttributeLiteral ATTR_ROLE;
/*     */   
/*     */ 
/*     */ 
/*     */   public static XAttributeLiteral ATTR_GROUP;
/*     */   
/*     */ 
/*     */ 
/* 104 */   private static XOrganizationalExtension singleton = new XOrganizationalExtension();
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public static XOrganizationalExtension instance()
/*     */   {
/* 112 */     return singleton;
/*     */   }
/*     */   
/*     */   private Object readResolve() {
/* 116 */     return singleton;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */   private XOrganizationalExtension()
/*     */   {
/* 123 */     super("Organizational", "org", EXTENSION_URI);
/* 124 */     XFactory factory = (XFactory)XFactoryRegistry.instance().currentDefault();
/* 125 */     ATTR_RESOURCE = factory.createAttributeLiteral("org:resource", "__INVALID__", this);
/*     */     
/* 127 */     ATTR_ROLE = factory.createAttributeLiteral("org:role", "__INVALID__", this);
/*     */     
/* 129 */     ATTR_GROUP = factory.createAttributeLiteral("org:group", "__INVALID__", this);
/*     */     
/* 131 */     this.eventAttributes.add((XAttribute)ATTR_RESOURCE.clone());
/* 132 */     this.eventAttributes.add((XAttribute)ATTR_ROLE.clone());
/* 133 */     this.eventAttributes.add((XAttribute)ATTR_GROUP.clone());
/*     */     
/* 135 */     XGlobalAttributeNameMap.instance().registerMapping("EN", "org:resource", "Resource");
/*     */     
/*     */ 
/* 138 */     XGlobalAttributeNameMap.instance().registerMapping("EN", "org:role", "Role");
/*     */     
/* 140 */     XGlobalAttributeNameMap.instance().registerMapping("EN", "org:group", "Group");
/*     */     
/* 142 */     XGlobalAttributeNameMap.instance().registerMapping("DE", "org:resource", "Akteur");
/*     */     
/* 144 */     XGlobalAttributeNameMap.instance().registerMapping("DE", "org:role", "Rolle");
/*     */     
/* 146 */     XGlobalAttributeNameMap.instance().registerMapping("DE", "org:group", "Gruppe");
/*     */     
/* 148 */     XGlobalAttributeNameMap.instance().registerMapping("FR", "org:resource", "Agent");
/*     */     
/* 150 */     XGlobalAttributeNameMap.instance().registerMapping("FR", "org:role", "Rôle");
/*     */     
/* 152 */     XGlobalAttributeNameMap.instance().registerMapping("FR", "org:group", "Groupe");
/*     */     
/* 154 */     XGlobalAttributeNameMap.instance().registerMapping("ES", "org:resource", "Recurso");
/*     */     
/*     */ 
/* 157 */     XGlobalAttributeNameMap.instance().registerMapping("ES", "org:role", "Papel");
/*     */     
/* 159 */     XGlobalAttributeNameMap.instance().registerMapping("ES", "org:group", "Grupo");
/*     */     
/* 161 */     XGlobalAttributeNameMap.instance().registerMapping("PT", "org:resource", "Recurso");
/*     */     
/*     */ 
/* 164 */     XGlobalAttributeNameMap.instance().registerMapping("PT", "org:role", "Papel");
/*     */     
/* 166 */     XGlobalAttributeNameMap.instance().registerMapping("PT", "org:group", "Grupo");
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
/*     */   public String extractResource(XEvent event)
/*     */   {
/* 179 */     XAttribute attribute = (XAttribute)event.getAttributes().get("org:resource");
/* 180 */     if (attribute == null) {
/* 181 */       return null;
/*     */     }
/* 183 */     return ((XAttributeLiteral)attribute).getValue();
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
/*     */   public void assignResource(XEvent event, String resource)
/*     */   {
/* 196 */     if ((resource != null) && (resource.trim().length() > 0)) {
/* 197 */       XAttributeLiteral attr = (XAttributeLiteral)ATTR_RESOURCE.clone();
/* 198 */       attr.setValue(resource.trim());
/* 199 */       event.getAttributes().put("org:resource", attr);
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
/*     */   public String extractRole(XEvent event)
/*     */   {
/* 212 */     XAttribute attribute = (XAttribute)event.getAttributes().get("org:role");
/* 213 */     if (attribute == null) {
/* 214 */       return null;
/*     */     }
/* 216 */     return ((XAttributeLiteral)attribute).getValue();
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
/*     */   public void assignRole(XEvent event, String role)
/*     */   {
/* 229 */     if ((role != null) && (role.trim().length() > 0)) {
/* 230 */       XAttributeLiteral attr = (XAttributeLiteral)ATTR_ROLE.clone();
/* 231 */       attr.setValue(role.trim());
/* 232 */       event.getAttributes().put("org:role", attr);
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
/*     */   public String extractGroup(XEvent event)
/*     */   {
/* 245 */     XAttribute attribute = (XAttribute)event.getAttributes().get("org:group");
/* 246 */     if (attribute == null) {
/* 247 */       return null;
/*     */     }
/* 249 */     return ((XAttributeLiteral)attribute).getValue();
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
/*     */   public void assignGroup(XEvent event, String group)
/*     */   {
/* 262 */     if ((group != null) && (group.trim().length() > 0)) {
/* 263 */       XAttributeLiteral attr = (XAttributeLiteral)ATTR_GROUP.clone();
/* 264 */       attr.setValue(group.trim());
/* 265 */       event.getAttributes().put("org:group", attr);
/*     */     }
/*     */   }
/*     */ }


/* Location:              /home/menick/Scrivania/ProcessMining/CNMining/CNMining.jar!/org/deckfour/xes/extension/std/XOrganizationalExtension.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */