/*     */ package org.deckfour.xes.out;
/*     */ 
/*     */ import java.io.IOException;
/*     */ import java.io.OutputStream;
/*     */ import java.net.URI;
/*     */ import java.util.Arrays;
/*     */ import java.util.Collection;
/*     */ import java.util.Date;
/*     */ import java.util.List;
/*     */ import org.deckfour.spex.SXDocument;
/*     */ import org.deckfour.spex.SXTag;
/*     */ import org.deckfour.xes.classification.XEventAttributeClassifier;
/*     */ import org.deckfour.xes.classification.XEventClassifier;
/*     */ import org.deckfour.xes.extension.XExtension;
/*     */ import org.deckfour.xes.logging.XLogging;
/*     */ import org.deckfour.xes.logging.XLogging.Importance;
/*     */ import org.deckfour.xes.model.XAttribute;
/*     */ import org.deckfour.xes.model.XAttributeBoolean;
/*     */ import org.deckfour.xes.model.XAttributeContinuous;
/*     */ import org.deckfour.xes.model.XAttributeDiscrete;
/*     */ import org.deckfour.xes.model.XAttributeID;
/*     */ import org.deckfour.xes.model.XAttributeLiteral;
/*     */ import org.deckfour.xes.model.XAttributeMap;
/*     */ import org.deckfour.xes.model.XAttributeTimestamp;
/*     */ import org.deckfour.xes.model.XEvent;
/*     */ import org.deckfour.xes.model.XLog;
/*     */ import org.deckfour.xes.model.XTrace;
/*     */ import org.deckfour.xes.util.XTokenHelper;
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
/*     */ public class XesXmlSerializer
/*     */   implements XSerializer
/*     */ {
/*  76 */   protected XsDateTimeConversion xsDateTimeConversion = new XsDateTimeConversion();
/*     */   
/*     */ 
/*     */ 
/*     */   public String getDescription()
/*     */   {
/*  82 */     return "XES XML Serialization";
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */   public String getName()
/*     */   {
/*  89 */     return "XES XML";
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */   public String getAuthor()
/*     */   {
/*  96 */     return "Christian W. Günther";
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */   public String[] getSuffices()
/*     */   {
/* 103 */     return new String[] { "xes" };
/*     */   }
/*     */   
/*     */ 
/*     */   public void serialize(XLog log, OutputStream out)
/*     */     throws IOException
/*     */   {
/* 110 */     XLogging.log("start serializing log to XES.XML", XLogging.Importance.DEBUG);
/* 111 */     long start = System.currentTimeMillis();
/* 112 */     SXDocument doc = new SXDocument(out);
/* 113 */     doc.addComment("This file has been generated with the OpenXES library. It conforms");
/* 114 */     doc.addComment("to the XML serialization of the XES standard for log storage and");
/* 115 */     doc.addComment("management.");
/* 116 */     doc.addComment("XES standard version: 1.0");
/* 117 */     doc.addComment("OpenXES library version: 1.0RC7");
/* 118 */     doc.addComment("OpenXES is available from http://www.openxes.org/");
/* 119 */     SXTag logTag = doc.addNode("log");
/* 120 */     logTag.addAttribute("xes.version", "1.0");
/* 121 */     logTag.addAttribute("xes.features", "nested-attributes");
/* 122 */     logTag.addAttribute("openxes.version", "1.0RC7");
/* 123 */     logTag.addAttribute("xmlns", "http://www.xes-standard.org/");
/*     */     
/* 125 */     for (XExtension extension : log.getExtensions()) {
/* 126 */       SXTag extensionTag = logTag.addChildNode("extension");
/* 127 */       extensionTag.addAttribute("name", extension.getName());
/* 128 */       extensionTag.addAttribute("prefix", extension.getPrefix());
/* 129 */       extensionTag.addAttribute("uri", extension.getUri().toString());
/*     */     }
/*     */     
/* 132 */     addGlobalAttributes(logTag, "trace", log.getGlobalTraceAttributes());
/* 133 */     addGlobalAttributes(logTag, "event", log.getGlobalEventAttributes());
/*     */     
/* 135 */     for (XEventClassifier classifier : log.getClassifiers()) {
/* 136 */       if ((classifier instanceof XEventAttributeClassifier)) {
/* 137 */         XEventAttributeClassifier attrClass = (XEventAttributeClassifier)classifier;
/* 138 */         SXTag clsTag = logTag.addChildNode("classifier");
/* 139 */         clsTag.addAttribute("name", attrClass.name());
/* 140 */         clsTag.addAttribute("keys", XTokenHelper.formatTokenString(Arrays.asList(attrClass.getDefiningAttributeKeys())));
/*     */       }
/*     */     }
/*     */     
/* 144 */     addAttributes(logTag, log.getAttributes().values());
/* 145 */     for (XTrace trace : log) {
/* 146 */       traceTag = logTag.addChildNode("trace");
/* 147 */       addAttributes(traceTag, trace.getAttributes().values());
/* 148 */       for (XEvent event : trace) {
/* 149 */         SXTag eventTag = traceTag.addChildNode("event");
/* 150 */         addAttributes(eventTag, event.getAttributes().values());
/*     */       }
/*     */     }
/*     */     SXTag traceTag;
/* 154 */     doc.close();
/* 155 */     String duration = " (" + (System.currentTimeMillis() - start) + " msec.)";
/* 156 */     XLogging.log("finished serializing log" + duration, XLogging.Importance.DEBUG);
/*     */   }
/*     */   
/*     */ 
/*     */   protected void addGlobalAttributes(SXTag parent, String scope, List<XAttribute> attributes)
/*     */     throws IOException
/*     */   {
/* 163 */     if (attributes.size() > 0) {
/* 164 */       SXTag guaranteedNode = parent.addChildNode("global");
/* 165 */       guaranteedNode.addAttribute("scope", scope);
/* 166 */       addAttributes(guaranteedNode, attributes);
/*     */     }
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   protected void addAttributes(SXTag tag, Collection<XAttribute> attributes)
/*     */     throws IOException
/*     */   {
/* 178 */     for (XAttribute attribute : attributes)
/*     */     {
/* 180 */       if ((attribute instanceof XAttributeLiteral)) {
/* 181 */         SXTag attributeTag = tag.addChildNode("string");
/* 182 */         attributeTag.addAttribute("key", attribute.getKey());
/* 183 */         attributeTag.addAttribute("value", attribute.toString());
/* 184 */       } else if ((attribute instanceof XAttributeDiscrete)) {
/* 185 */         SXTag attributeTag = tag.addChildNode("int");
/* 186 */         attributeTag.addAttribute("key", attribute.getKey());
/* 187 */         attributeTag.addAttribute("value", attribute.toString());
/* 188 */       } else if ((attribute instanceof XAttributeContinuous)) {
/* 189 */         SXTag attributeTag = tag.addChildNode("float");
/* 190 */         attributeTag.addAttribute("key", attribute.getKey());
/* 191 */         attributeTag.addAttribute("value", attribute.toString());
/* 192 */       } else if ((attribute instanceof XAttributeTimestamp)) {
/* 193 */         SXTag attributeTag = tag.addChildNode("date");
/* 194 */         attributeTag.addAttribute("key", attribute.getKey());
/* 195 */         Date timestamp = ((XAttributeTimestamp)attribute).getValue();
/* 196 */         attributeTag.addAttribute("value", this.xsDateTimeConversion.format(timestamp));
/* 197 */       } else if ((attribute instanceof XAttributeBoolean)) {
/* 198 */         SXTag attributeTag = tag.addChildNode("boolean");
/* 199 */         attributeTag.addAttribute("key", attribute.getKey());
/* 200 */         attributeTag.addAttribute("value", attribute.toString());
/* 201 */       } else if ((attribute instanceof XAttributeID)) {
/* 202 */         SXTag attributeTag = tag.addChildNode("id");
/* 203 */         attributeTag.addAttribute("key", attribute.getKey());
/* 204 */         attributeTag.addAttribute("value", attribute.toString());
/*     */       } else {
/* 206 */         throw new IOException("Unknown attribute type!"); }
/*     */       SXTag attributeTag;
/* 208 */       addAttributes(attributeTag, attribute.getAttributes().values());
/*     */     }
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */   public String toString()
/*     */   {
/* 216 */     return getName();
/*     */   }
/*     */ }


/* Location:              /home/menick/Scrivania/ProcessMining/CNMining/CNMining.jar!/org/deckfour/xes/out/XesXmlSerializer.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */