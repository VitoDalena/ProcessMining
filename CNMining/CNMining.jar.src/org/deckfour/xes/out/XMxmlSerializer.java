/*     */ package org.deckfour.xes.out;
/*     */ 
/*     */ import java.io.IOException;
/*     */ import java.io.OutputStream;
/*     */ import java.util.Collection;
/*     */ import java.util.Date;
/*     */ import java.util.HashSet;
/*     */ import org.deckfour.spex.SXDocument;
/*     */ import org.deckfour.spex.SXTag;
/*     */ import org.deckfour.xes.extension.std.XConceptExtension;
/*     */ import org.deckfour.xes.logging.XLogging;
/*     */ import org.deckfour.xes.logging.XLogging.Importance;
/*     */ import org.deckfour.xes.model.XAttributable;
/*     */ import org.deckfour.xes.model.XAttribute;
/*     */ import org.deckfour.xes.model.XAttributeLiteral;
/*     */ import org.deckfour.xes.model.XAttributeMap;
/*     */ import org.deckfour.xes.model.XAttributeTimestamp;
/*     */ import org.deckfour.xes.model.XEvent;
/*     */ import org.deckfour.xes.model.XLog;
/*     */ import org.deckfour.xes.model.XTrace;
/*     */ import org.deckfour.xes.util.XsDateTimeConversion;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class XMxmlSerializer
/*     */   implements XSerializer
/*     */ {
/*     */   protected HashSet<String> knownTypes;
/*  79 */   protected XsDateTimeConversion xsDateTimeConversion = new XsDateTimeConversion();
/*     */   
/*     */   public XMxmlSerializer() {
/*  82 */     this.knownTypes = new HashSet();
/*  83 */     this.knownTypes.add("schedule");
/*  84 */     this.knownTypes.add("assign");
/*  85 */     this.knownTypes.add("withdraw");
/*  86 */     this.knownTypes.add("reassign");
/*  87 */     this.knownTypes.add("start");
/*  88 */     this.knownTypes.add("suspend");
/*  89 */     this.knownTypes.add("resume");
/*  90 */     this.knownTypes.add("pi_abort");
/*  91 */     this.knownTypes.add("ate_abort");
/*  92 */     this.knownTypes.add("complete");
/*  93 */     this.knownTypes.add("autoskip");
/*  94 */     this.knownTypes.add("manualskip");
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public String getDescription()
/*     */   {
/* 103 */     return "MXML serialization (legacy)";
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public String getName()
/*     */   {
/* 112 */     return "MXML";
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public String getAuthor()
/*     */   {
/* 121 */     return "Christian W. Günther";
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public String[] getSuffices()
/*     */   {
/* 130 */     return new String[] { "mxml" };
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public void serialize(XLog log, OutputStream out)
/*     */     throws IOException
/*     */   {
/* 141 */     XLogging.log("start serializing log to MXML", XLogging.Importance.DEBUG);
/* 142 */     long start = System.currentTimeMillis();
/* 143 */     SXDocument doc = new SXDocument(out);
/* 144 */     doc.addComment("This file has been generated with the OpenXES library. It conforms");
/* 145 */     doc.addComment("to the legacy MXML standard for log storage and management.");
/* 146 */     doc.addComment("OpenXES library version: 1.0RC7");
/*     */     
/* 148 */     doc.addComment("OpenXES is available from http://www.xes-standard.org/");
/* 149 */     SXTag root = doc.addNode("WorkflowLog");
/* 150 */     SXTag source = root.addChildNode("Source");
/* 151 */     source.addAttribute("program", "XES MXML serialization");
/* 152 */     source.addAttribute("openxes.version", "1.0RC7");
/* 153 */     SXTag process = root.addChildNode("Process");
/* 154 */     String id = XConceptExtension.instance().extractName(log);
/* 155 */     process.addAttribute("id", id == null ? "none" : id);
/* 156 */     process.addAttribute("description", "process with id " + XConceptExtension.instance().extractName(log));
/*     */     
/* 158 */     addModelReference(log, process);
/* 159 */     addAttributes(process, log.getAttributes().values());
/* 160 */     for (XTrace trace : log) {
/* 161 */       instance = process.addChildNode("ProcessInstance");
/* 162 */       instance.addAttribute("id", XConceptExtension.instance().extractName(trace));
/*     */       
/* 164 */       instance.addAttribute("description", "instance with id " + XConceptExtension.instance().extractName(trace));
/*     */       
/* 166 */       addModelReference(trace, instance);
/* 167 */       addAttributes(instance, trace.getAttributes().values());
/* 168 */       for (XEvent event : trace) {
/* 169 */         SXTag ate = instance.addChildNode("AuditTrailEntry");
/* 170 */         addAttributes(ate, event.getAttributes().values());
/* 171 */         SXTag wfme = ate.addChildNode("WorkflowModelElement");
/* 172 */         addModelReference(event, wfme);
/* 173 */         wfme.addTextNode(XConceptExtension.instance().extractName(event));
/*     */         
/* 175 */         SXTag type = ate.addChildNode("EventType");
/* 176 */         XAttributeLiteral typeAttr = (XAttributeLiteral)event.getAttributes().get("lifecycle:transition");
/*     */         
/*     */ 
/* 179 */         if (typeAttr != null) {
/* 180 */           addModelReference(typeAttr, type);
/* 181 */           String typeStr = typeAttr.getValue().trim().toLowerCase();
/* 182 */           if (this.knownTypes.contains(typeStr)) {
/* 183 */             type.addTextNode(typeStr);
/*     */           } else {
/* 185 */             type.addAttribute("unknownType", typeAttr.getValue());
/* 186 */             type.addTextNode("unknown");
/*     */           }
/*     */         } else {
/* 189 */           type.addTextNode("complete");
/*     */         }
/* 191 */         XAttributeLiteral originatorAttr = (XAttributeLiteral)event.getAttributes().get("org:resource");
/*     */         
/*     */ 
/* 194 */         if (originatorAttr == null) {
/* 195 */           originatorAttr = (XAttributeLiteral)event.getAttributes().get("org:role");
/*     */         }
/*     */         
/* 198 */         if (originatorAttr == null) {
/* 199 */           originatorAttr = (XAttributeLiteral)event.getAttributes().get("org:group");
/*     */         }
/*     */         
/* 202 */         if (originatorAttr != null) {
/* 203 */           SXTag originator = ate.addChildNode("originator");
/* 204 */           addModelReference(originatorAttr, originator);
/* 205 */           originator.addTextNode(originatorAttr.getValue());
/*     */         }
/* 207 */         XAttributeTimestamp timestampAttr = (XAttributeTimestamp)event.getAttributes().get("time:timestamp");
/*     */         
/* 209 */         if (timestampAttr != null) {
/* 210 */           SXTag timestamp = ate.addChildNode("timestamp");
/* 211 */           addModelReference(timestampAttr, timestamp);
/* 212 */           Date date = timestampAttr.getValue();
/* 213 */           timestamp.addTextNode(this.xsDateTimeConversion.format(date));
/*     */         }
/*     */       } }
/*     */     SXTag instance;
/* 217 */     doc.close();
/* 218 */     String duration = " (" + (System.currentTimeMillis() - start) + " msec.)";
/*     */     
/* 220 */     XLogging.log("finished serializing log" + duration, XLogging.Importance.DEBUG);
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
/*     */   protected void addAttributes(SXTag node, Collection<XAttribute> attributes)
/*     */     throws IOException
/*     */   {
/* 234 */     SXTag data = node.addChildNode("Data");
/* 235 */     addAttributes(data, "", attributes);
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
/*     */   protected void addAttributes(SXTag dataNode, String keyPrefix, Collection<XAttribute> attributes)
/*     */     throws IOException
/*     */   {
/* 250 */     for (XAttribute attribute : attributes)
/*     */     {
/*     */ 
/* 253 */       if (!attribute.getKey().equals("semantic:modelReference"))
/*     */       {
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/* 271 */         SXTag attributeTag = dataNode.addChildNode("attribute");
/* 272 */         attributeTag.addAttribute("name", keyPrefix + attribute.getKey());
/* 273 */         addModelReference(attribute, attributeTag);
/* 274 */         attributeTag.addTextNode(attribute.toString());
/* 275 */         Collection<XAttribute> subAttributes = attribute.getAttributes().values();
/*     */         
/* 277 */         if (subAttributes.size() > 0) {
/* 278 */           String subKeyPrefix = attribute.getKey();
/* 279 */           if (keyPrefix.length() > 0) {
/* 280 */             subKeyPrefix = keyPrefix + ":" + subKeyPrefix;
/*     */           }
/* 282 */           addAttributes(dataNode, subKeyPrefix, subAttributes);
/*     */         }
/*     */       }
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
/*     */   protected void addModelReference(XAttributable object, SXTag target)
/*     */     throws IOException
/*     */   {
/* 298 */     XAttributeLiteral modelRefAttr = (XAttributeLiteral)object.getAttributes().get("semantic:modelReference");
/*     */     
/* 300 */     if (modelRefAttr != null) {
/* 301 */       target.addAttribute("modelReference", modelRefAttr.getValue());
/*     */     }
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */   public String toString()
/*     */   {
/* 309 */     return getName();
/*     */   }
/*     */ }


/* Location:              /home/menick/Scrivania/ProcessMining/CNMining/CNMining.jar!/org/deckfour/xes/out/XMxmlSerializer.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */