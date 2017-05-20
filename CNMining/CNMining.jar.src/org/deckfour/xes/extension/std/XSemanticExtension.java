/*     */ package org.deckfour.xes.extension.std;
/*     */ 
/*     */ import java.net.URI;
/*     */ import java.util.ArrayList;
/*     */ import java.util.HashSet;
/*     */ import java.util.List;
/*     */ import org.deckfour.xes.extension.XExtension;
/*     */ import org.deckfour.xes.factory.XFactory;
/*     */ import org.deckfour.xes.factory.XFactoryRegistry;
/*     */ import org.deckfour.xes.info.XGlobalAttributeNameMap;
/*     */ import org.deckfour.xes.model.XAttributable;
/*     */ import org.deckfour.xes.model.XAttribute;
/*     */ import org.deckfour.xes.model.XAttributeLiteral;
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
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class XSemanticExtension
/*     */   extends XExtension
/*     */ {
/*     */   private static final long serialVersionUID = -8755188345751379342L;
/*  71 */   public static final URI EXTENSION_URI = URI.create("http://www.xes-standard.org/semantic.xesext");
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   public static final String KEY_MODELREFERENCE = "semantic:modelReference";
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   public static XAttributeLiteral ATTR_MODELREFERENCE;
/*     */   
/*     */ 
/*     */ 
/*  85 */   private static XSemanticExtension singleton = new XSemanticExtension();
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public static XSemanticExtension instance()
/*     */   {
/*  93 */     return singleton;
/*     */   }
/*     */   
/*     */   private Object readResolve() {
/*  97 */     return singleton;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */   private XSemanticExtension()
/*     */   {
/* 104 */     super("Semantic", "semantic", EXTENSION_URI);
/* 105 */     XFactory factory = (XFactory)XFactoryRegistry.instance().currentDefault();
/* 106 */     ATTR_MODELREFERENCE = factory.createAttributeLiteral("semantic:modelReference", "__INVALID__", this);
/*     */     
/* 108 */     this.logAttributes.add((XAttribute)ATTR_MODELREFERENCE.clone());
/* 109 */     this.traceAttributes.add((XAttribute)ATTR_MODELREFERENCE.clone());
/* 110 */     this.eventAttributes.add((XAttribute)ATTR_MODELREFERENCE.clone());
/* 111 */     this.metaAttributes.add((XAttribute)ATTR_MODELREFERENCE.clone());
/*     */     
/* 113 */     XGlobalAttributeNameMap.instance().registerMapping("EN", "semantic:modelReference", "Ontology Model Reference");
/*     */     
/*     */ 
/* 116 */     XGlobalAttributeNameMap.instance().registerMapping("DE", "semantic:modelReference", "Ontologie-Modellreferenz");
/*     */     
/*     */ 
/* 119 */     XGlobalAttributeNameMap.instance().registerMapping("FR", "semantic:modelReference", "Référence au Modèle Ontologique");
/*     */     
/*     */ 
/* 122 */     XGlobalAttributeNameMap.instance().registerMapping("ES", "semantic:modelReference", "Referencia de Modelo Ontológico");
/*     */     
/*     */ 
/* 125 */     XGlobalAttributeNameMap.instance().registerMapping("PT", "semantic:modelReference", "Referência de Modelo Ontológico");
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
/*     */   public List<String> extractModelReferences(XAttributable target)
/*     */   {
/* 141 */     ArrayList<String> modelReferences = new ArrayList();
/* 142 */     XAttributeLiteral modelReferenceAttribute = (XAttributeLiteral)target.getAttributes().get("semantic:modelReference");
/*     */     
/* 144 */     if (modelReferenceAttribute != null) {
/* 145 */       String refString = modelReferenceAttribute.getValue().trim();
/* 146 */       for (String reference : refString.split("\\s")) {
/* 147 */         modelReferences.add(reference.trim());
/*     */       }
/*     */     }
/* 150 */     return modelReferences;
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
/*     */   public List<URI> extractModelReferenceURIs(XAttributable target)
/*     */   {
/* 164 */     List<String> refStrings = extractModelReferences(target);
/* 165 */     List<URI> refURIs = new ArrayList(refStrings.size());
/* 166 */     for (String refString : refStrings) {
/* 167 */       refURIs.add(URI.create(refString));
/*     */     }
/* 169 */     return refURIs;
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
/*     */   public void assignModelReferences(XAttributable target, List<String> modelReferences)
/*     */   {
/* 185 */     StringBuilder sb = new StringBuilder();
/* 186 */     for (String ref : modelReferences) {
/* 187 */       sb.append(ref);
/* 188 */       sb.append(" ");
/*     */     }
/* 190 */     if (sb.toString().trim().length() > 0) {
/* 191 */       XAttributeLiteral attr = (XAttributeLiteral)ATTR_MODELREFERENCE.clone();
/*     */       
/* 193 */       attr.setValue(sb.toString().trim());
/* 194 */       target.getAttributes().put("semantic:modelReference", attr);
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
/*     */ 
/*     */ 
/*     */   public void assignModelReferenceUris(XAttributable target, List<URI> modelReferenceURIs)
/*     */   {
/* 211 */     StringBuilder sb = new StringBuilder();
/* 212 */     for (URI ref : modelReferenceURIs) {
/* 213 */       sb.append(ref.toString());
/* 214 */       sb.append(" ");
/*     */     }
/* 216 */     if (sb.toString().trim().length() > 0) {
/* 217 */       XAttributeLiteral attr = (XAttributeLiteral)ATTR_MODELREFERENCE.clone();
/*     */       
/* 219 */       attr.setValue(sb.toString().trim());
/* 220 */       target.getAttributes().put("semantic:modelReference", attr);
/*     */     }
/*     */   }
/*     */ }


/* Location:              /home/menick/Scrivania/ProcessMining/CNMining/CNMining.jar!/org/deckfour/xes/extension/std/XSemanticExtension.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */