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
/*     */ import org.deckfour.xes.model.XLog;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class XLifecycleExtension
/*     */   extends XExtension
/*     */ {
/*     */   private static final long serialVersionUID = 7368474477345685085L;
/*     */   
/*     */   public static enum StandardModel
/*     */   {
/*  77 */     SCHEDULE("schedule"),  ASSIGN("assign"),  WITHDRAW("withdraw"),  REASSIGN("reassign"), 
/*  78 */     START("start"),  SUSPEND("suspend"),  RESUME("resume"), 
/*  79 */     PI_ABORT("pi_abort"),  ATE_ABORT("ate_abort"),  COMPLETE("complete"), 
/*  80 */     AUTOSKIP("autoskip"),  MANUALSKIP("manualskip"),  UNKNOWN("unknown");
/*     */     
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     private final String encoding;
/*     */     
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     private StandardModel(String encoding)
/*     */     {
/*  95 */       this.encoding = encoding;
/*     */     }
/*     */     
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     public String getEncoding()
/*     */     {
/* 104 */       return this.encoding;
/*     */     }
/*     */     
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     public static StandardModel decode(String encoding)
/*     */     {
/* 116 */       encoding = encoding.trim().toLowerCase();
/* 117 */       for (StandardModel transition : values()) {
/* 118 */         if (transition.encoding.equals(encoding)) {
/* 119 */           return transition;
/*     */         }
/*     */       }
/* 122 */       return UNKNOWN;
/*     */     }
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/* 130 */   public static final URI EXTENSION_URI = URI.create("http://www.xes-standard.org/lifecycle.xesext");
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   public static final String KEY_MODEL = "lifecycle:model";
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   public static final String KEY_TRANSITION = "lifecycle:transition";
/*     */   
/*     */ 
/*     */ 
/*     */   public static final String VALUE_MODEL_STANDARD = "standard";
/*     */   
/*     */ 
/*     */ 
/*     */   public static XAttributeLiteral ATTR_MODEL;
/*     */   
/*     */ 
/*     */ 
/*     */   public static XAttributeLiteral ATTR_TRANSITION;
/*     */   
/*     */ 
/*     */ 
/* 156 */   private static XLifecycleExtension singleton = new XLifecycleExtension();
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public static XLifecycleExtension instance()
/*     */   {
/* 164 */     return singleton;
/*     */   }
/*     */   
/*     */   private Object readResolve() {
/* 168 */     return singleton;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */   private XLifecycleExtension()
/*     */   {
/* 175 */     super("Lifecycle", "lifecycle", EXTENSION_URI);
/* 176 */     XFactory factory = (XFactory)XFactoryRegistry.instance().currentDefault();
/* 177 */     ATTR_MODEL = factory.createAttributeLiteral("lifecycle:model", "standard", this);
/*     */     
/* 179 */     ATTR_TRANSITION = factory.createAttributeLiteral("lifecycle:transition", StandardModel.COMPLETE.getEncoding(), this);
/*     */     
/* 181 */     this.logAttributes.add((XAttributeLiteral)ATTR_MODEL.clone());
/* 182 */     this.eventAttributes.add((XAttributeLiteral)ATTR_TRANSITION.clone());
/*     */     
/* 184 */     XGlobalAttributeNameMap.instance().registerMapping("EN", "lifecycle:model", "Lifecycle Model");
/*     */     
/*     */ 
/* 187 */     XGlobalAttributeNameMap.instance().registerMapping("EN", "lifecycle:transition", "Lifecycle Transition");
/*     */     
/*     */ 
/* 190 */     XGlobalAttributeNameMap.instance().registerMapping("DE", "lifecycle:model", "Lebenszyklus-Model");
/*     */     
/*     */ 
/* 193 */     XGlobalAttributeNameMap.instance().registerMapping("DE", "lifecycle:transition", "Lebenszyklus-Transition");
/*     */     
/*     */ 
/* 196 */     XGlobalAttributeNameMap.instance().registerMapping("FR", "lifecycle:model", "Modèle du Cycle Vital");
/*     */     
/*     */ 
/* 199 */     XGlobalAttributeNameMap.instance().registerMapping("FR", "lifecycle:transition", "Transition en Cycle Vital");
/*     */     
/*     */ 
/* 202 */     XGlobalAttributeNameMap.instance().registerMapping("ES", "lifecycle:model", "Modelo de Ciclo de Vida");
/*     */     
/*     */ 
/* 205 */     XGlobalAttributeNameMap.instance().registerMapping("ES", "lifecycle:transition", "Transición en Ciclo de Vida");
/*     */     
/*     */ 
/* 208 */     XGlobalAttributeNameMap.instance().registerMapping("PT", "lifecycle:model", "Modelo do Ciclo de Vida");
/*     */     
/*     */ 
/* 211 */     XGlobalAttributeNameMap.instance().registerMapping("PT", "lifecycle:transition", "Transição do Ciclo de Vida");
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
/*     */   public String extractModel(XLog log)
/*     */   {
/* 224 */     XAttribute attribute = (XAttribute)log.getAttributes().get("lifecycle:model");
/* 225 */     if (attribute == null) {
/* 226 */       return null;
/*     */     }
/* 228 */     return ((XAttributeLiteral)attribute).getValue();
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
/*     */   public void assignModel(XLog log, String model)
/*     */   {
/* 241 */     if ((model != null) && (model.trim().length() > 0)) {
/* 242 */       XAttributeLiteral modelAttr = (XAttributeLiteral)ATTR_MODEL.clone();
/*     */       
/* 244 */       modelAttr.setValue(model.trim());
/* 245 */       log.getAttributes().put("lifecycle:model", modelAttr);
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
/*     */   public boolean usesStandardModel(XLog log)
/*     */   {
/* 259 */     String model = extractModel(log);
/* 260 */     if (model == null)
/* 261 */       return false;
/* 262 */     if (model.trim().equals("standard")) {
/* 263 */       return true;
/*     */     }
/* 265 */     return false;
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
/*     */   public String extractTransition(XEvent event)
/*     */   {
/* 278 */     XAttribute attribute = (XAttribute)event.getAttributes().get("lifecycle:transition");
/* 279 */     if (attribute == null) {
/* 280 */       return null;
/*     */     }
/* 282 */     return ((XAttributeLiteral)attribute).getValue();
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
/*     */   public StandardModel extractStandardTransition(XEvent event)
/*     */   {
/* 295 */     String transition = extractTransition(event);
/* 296 */     if (transition != null) {
/* 297 */       return StandardModel.decode(transition);
/*     */     }
/* 299 */     return null;
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
/*     */   public void assignTransition(XEvent event, String transition)
/*     */   {
/* 312 */     if ((transition != null) && (transition.trim().length() > 0)) {
/* 313 */       XAttributeLiteral transAttr = (XAttributeLiteral)ATTR_TRANSITION.clone();
/*     */       
/* 315 */       transAttr.setValue(transition.trim());
/* 316 */       event.getAttributes().put("lifecycle:transition", transAttr);
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
/*     */   public void assignStandardTransition(XEvent event, StandardModel transition)
/*     */   {
/* 329 */     assignTransition(event, transition.getEncoding());
/*     */   }
/*     */ }


/* Location:              /home/menick/Scrivania/ProcessMining/CNMining/CNMining.jar!/org/deckfour/xes/extension/std/XLifecycleExtension.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */