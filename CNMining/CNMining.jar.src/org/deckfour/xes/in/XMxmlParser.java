/*     */ package org.deckfour.xes.in;
/*     */ 
/*     */ import java.io.BufferedInputStream;
/*     */ import java.io.File;
/*     */ import java.io.InputStream;
/*     */ import java.util.ArrayList;
/*     */ import java.util.Date;
/*     */ import java.util.List;
/*     */ import java.util.Set;
/*     */ import javax.xml.parsers.SAXParser;
/*     */ import javax.xml.parsers.SAXParserFactory;
/*     */ import org.deckfour.xes.classification.XEventAttributeClassifier;
/*     */ import org.deckfour.xes.classification.XEventClassifier;
/*     */ import org.deckfour.xes.classification.XEventNameClassifier;
/*     */ import org.deckfour.xes.classification.XEventResourceClassifier;
/*     */ import org.deckfour.xes.extension.std.XConceptExtension;
/*     */ import org.deckfour.xes.extension.std.XLifecycleExtension;
/*     */ import org.deckfour.xes.extension.std.XOrganizationalExtension;
/*     */ import org.deckfour.xes.extension.std.XSemanticExtension;
/*     */ import org.deckfour.xes.extension.std.XTimeExtension;
/*     */ import org.deckfour.xes.factory.XFactory;
/*     */ import org.deckfour.xes.factory.XFactoryRegistry;
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
/*     */ import org.deckfour.xes.model.buffered.XTraceBufferedImpl;
/*     */ import org.deckfour.xes.util.XsDateTimeConversion;
/*     */ import org.xml.sax.Attributes;
/*     */ import org.xml.sax.SAXException;
/*     */ import org.xml.sax.helpers.DefaultHandler;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class XMxmlParser
/*     */   extends XParser
/*     */ {
/*  84 */   protected XsDateTimeConversion xsDateTimeConversion = new XsDateTimeConversion();
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public static XEventClassifier MXML_STANDARD_CLASSIFIER;
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/* 105 */   public static XEventClassifier MXML_EVENT_NAME_CLASSIFIER = new XEventNameClassifier();
/* 106 */   public static XEventClassifier MXML_ORIGINATOR_CLASSIFIER = new XEventResourceClassifier();
/* 107 */   static { MXML_STANDARD_CLASSIFIER = new XEventAttributeClassifier("MXML Legacy Classifier", new String[] { "concept:name", "lifecycle:transition" });
/*     */     
/*     */ 
/*     */ 
/* 111 */     MXML_CLASSIFIERS = new ArrayList();
/* 112 */     MXML_CLASSIFIERS.add(MXML_STANDARD_CLASSIFIER);
/* 113 */     MXML_CLASSIFIERS.add(MXML_EVENT_NAME_CLASSIFIER);
/* 114 */     MXML_CLASSIFIERS.add(MXML_ORIGINATOR_CLASSIFIER);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public static List<XEventClassifier> MXML_CLASSIFIERS;
/*     */   
/*     */ 
/*     */ 
/*     */   private XFactory factory;
/*     */   
/*     */ 
/*     */ 
/*     */   public XMxmlParser(XFactory factory)
/*     */   {
/* 131 */     this.factory = factory;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   public XMxmlParser()
/*     */   {
/* 139 */     this((XFactory)XFactoryRegistry.instance().currentDefault());
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   public String author()
/*     */   {
/* 147 */     return "Christian W. Günther";
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   public boolean canParse(File file)
/*     */   {
/* 155 */     String filename = file.getName();
/* 156 */     return (endsWithIgnoreCase(filename, ".mxml")) || (endsWithIgnoreCase(filename, ".xml"));
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
/*     */   public String description()
/*     */   {
/* 171 */     return "Reads XES models from legacy MXML serializations";
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   public String name()
/*     */   {
/* 179 */     return "MXML";
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public List<XLog> parse(InputStream is)
/*     */     throws Exception
/*     */   {
/* 191 */     BufferedInputStream bis = new BufferedInputStream(is);
/*     */     
/* 193 */     MxmlHandler handler = new MxmlHandler();
/*     */     
/* 195 */     SAXParserFactory parserFactory = SAXParserFactory.newInstance();
/* 196 */     SAXParser parser = parserFactory.newSAXParser();
/* 197 */     parser.parse(bis, handler);
/* 198 */     bis.close();
/* 199 */     return handler.getLogs();
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
/*     */   protected class MxmlHandler
/*     */     extends DefaultHandler
/*     */   {
/* 215 */     protected StringBuffer buffer = null;
/*     */     
/*     */ 
/* 218 */     protected ArrayList<XLog> logs = new ArrayList();
/* 219 */     protected XLog currentProcess = null;
/* 220 */     protected XTrace currentInstance = null;
/* 221 */     protected XEvent entry = null;
/* 222 */     protected XAttributeLiteral sourceAttribute = null;
/* 223 */     protected XAttributeLiteral genericAttribute = null;
/* 224 */     protected XAttributeLiteral eventTypeAttribute = null;
/* 225 */     protected XAttributeLiteral originatorAttribute = null;
/* 226 */     protected boolean sourceOpen = false;
/* 227 */     protected Date timestamp = null;
/* 228 */     protected Date lastTimestamp = null;
/* 229 */     protected int numUnorderedEntries = 0;
/*     */     
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     protected MxmlHandler()
/*     */     {
/* 237 */       this.buffer = new StringBuffer();
/* 238 */       this.entry = null;
/* 239 */       this.sourceOpen = false;
/* 240 */       this.currentProcess = null;
/* 241 */       this.currentInstance = null;
/* 242 */       this.lastTimestamp = null;
/* 243 */       this.numUnorderedEntries = 0;
/*     */     }
/*     */     
/*     */     public List<XLog> getLogs() {
/* 247 */       return this.logs;
/*     */     }
/*     */     
/*     */ 
/*     */ 
/*     */     public void startElement(String uri, String localName, String qName, Attributes attributes)
/*     */       throws SAXException
/*     */     {
/* 255 */       String tagName = localName;
/* 256 */       if (tagName.equalsIgnoreCase("")) {
/* 257 */         tagName = qName;
/*     */       }
/*     */       
/* 260 */       if (!tagName.equalsIgnoreCase("WorkflowLog"))
/*     */       {
/* 262 */         if (tagName.equalsIgnoreCase("Source"))
/*     */         {
/* 264 */           this.sourceOpen = true;
/* 265 */           String program = attributes.getValue(attributes.getIndex("program"));
/* 266 */           this.sourceAttribute = XMxmlParser.this.factory.createAttributeLiteral("source", program, null);
/* 267 */           addModelReferences(attributes, this.sourceAttribute);
/* 268 */         } else if (tagName.equalsIgnoreCase("Process"))
/*     */         {
/* 270 */           String procId = attributes.getValue("id");
/* 271 */           String procDescr = attributes.getValue("description");
/* 272 */           this.currentProcess = XMxmlParser.this.factory.createLog();
/* 273 */           this.currentProcess.getExtensions().add(XConceptExtension.instance());
/* 274 */           this.currentProcess.getExtensions().add(XOrganizationalExtension.instance());
/* 275 */           this.currentProcess.getExtensions().add(XLifecycleExtension.instance());
/* 276 */           this.currentProcess.getExtensions().add(XSemanticExtension.instance());
/* 277 */           this.currentProcess.getExtensions().add(XTimeExtension.instance());
/* 278 */           if (this.sourceAttribute != null) {
/* 279 */             this.currentProcess.getAttributes().put(this.sourceAttribute.getKey(), this.sourceAttribute);
/*     */           }
/* 281 */           XConceptExtension.instance().assignName(this.currentProcess, procId);
/* 282 */           XLifecycleExtension.instance().assignModel(this.currentProcess, "standard");
/* 283 */           if ((procDescr != null) && (procDescr.trim().length() > 0)) {
/* 284 */             XAttributeLiteral description = XMxmlParser.this.factory.createAttributeLiteral("description", procDescr, null);
/* 285 */             this.currentProcess.getAttributes().put(description.getKey(), description);
/*     */           }
/* 287 */           addModelReferences(attributes, this.currentProcess);
/* 288 */         } else if (tagName.equalsIgnoreCase("ProcessInstance"))
/*     */         {
/* 290 */           this.currentInstance = XMxmlParser.this.factory.createTrace();
/* 291 */           XConceptExtension.instance().assignName(this.currentInstance, attributes.getValue("id"));
/* 292 */           String descriptionString = attributes.getValue("description");
/* 293 */           if ((descriptionString != null) && (descriptionString.trim().length() > 0)) {
/* 294 */             XAttribute description = XMxmlParser.this.factory.createAttributeLiteral("description", descriptionString, null);
/* 295 */             this.currentInstance.getAttributes().put(description.getKey(), description);
/*     */           }
/* 297 */           addModelReferences(attributes, this.currentInstance);
/* 298 */         } else if (tagName.equalsIgnoreCase("AuditTrailEntry"))
/*     */         {
/* 300 */           this.entry = XMxmlParser.this.factory.createEvent();
/* 301 */         } else if (tagName.equalsIgnoreCase("Attribute"))
/*     */         {
/* 303 */           this.genericAttribute = XMxmlParser.this.factory.createAttributeLiteral(attributes.getValue("name").trim(), "DEFAULT_VALUE", null);
/* 304 */           addModelReferences(attributes, this.genericAttribute);
/* 305 */         } else if (tagName.equalsIgnoreCase("EventType")) {
/* 306 */           this.eventTypeAttribute = ((XAttributeLiteral)XLifecycleExtension.ATTR_TRANSITION.clone());
/*     */           
/* 308 */           if (attributes.getIndex("unknowntype") >= 0) {
/* 309 */             this.eventTypeAttribute.setValue(attributes.getValue("unknowntype"));
/*     */           }
/*     */           else {
/* 312 */             this.eventTypeAttribute.setValue("__INVALID__");
/*     */           }
/* 314 */           addModelReferences(attributes, this.eventTypeAttribute);
/* 315 */         } else if (tagName.equalsIgnoreCase("WorkflowModelElement"))
/*     */         {
/* 317 */           addModelReferences(attributes, this.entry);
/* 318 */         } else if (tagName.equalsIgnoreCase("Originator"))
/*     */         {
/* 320 */           this.originatorAttribute = ((XAttributeLiteral)XOrganizationalExtension.ATTR_RESOURCE.clone());
/* 321 */           addModelReferences(attributes, this.originatorAttribute);
/*     */         }
/*     */       }
/*     */     }
/*     */     
/*     */     public void endElement(String uri, String localName, String qName)
/*     */       throws SAXException
/*     */     {
/* 329 */       String tagName = localName;
/* 330 */       if (tagName.equalsIgnoreCase("")) {
/* 331 */         tagName = qName;
/*     */       }
/*     */       
/* 334 */       if (tagName.equalsIgnoreCase("WorkflowLog"))
/*     */       {
/* 336 */         if (this.numUnorderedEntries > 0)
/*     */         {
/* 338 */           XLogging.log("LogData: Log contains " + this.numUnorderedEntries + " audit trail entries in non-natural order!", XLogging.Importance.ERROR);
/*     */           
/* 340 */           XLogging.log("LogData: The log file you have loaded is not MXML compliant! (error compensated transparently)", XLogging.Importance.ERROR);
/*     */         }
/*     */         
/*     */       }
/* 344 */       else if (tagName.equalsIgnoreCase("Process"))
/*     */       {
/* 346 */         this.currentProcess.getClassifiers().addAll(XMxmlParser.MXML_CLASSIFIERS);
/*     */         
/* 348 */         this.currentProcess.getGlobalTraceAttributes().add((XAttribute)XConceptExtension.ATTR_NAME.clone());
/* 349 */         this.currentProcess.getGlobalEventAttributes().add((XAttribute)XConceptExtension.ATTR_NAME.clone());
/* 350 */         this.currentProcess.getGlobalEventAttributes().add((XAttribute)XLifecycleExtension.ATTR_TRANSITION.clone());
/* 351 */         this.logs.add(this.currentProcess);
/* 352 */         this.currentProcess = null;
/* 353 */       } else if (tagName.equalsIgnoreCase("Source"))
/*     */       {
/* 355 */         this.sourceOpen = false;
/* 356 */       } else if (tagName.equalsIgnoreCase("ProcessInstance"))
/*     */       {
/* 358 */         if (this.currentInstance.size() > 0)
/*     */         {
/* 360 */           if ((this.currentInstance instanceof XTraceBufferedImpl)) {
/* 361 */             ((XTraceBufferedImpl)this.currentInstance).consolidate();
/*     */           }
/* 363 */           this.currentProcess.add(this.currentInstance);
/*     */         }
/* 365 */         this.currentInstance = null;
/*     */         
/* 367 */         this.lastTimestamp = null;
/* 368 */       } else if (tagName.equalsIgnoreCase("AuditTrailEntry"))
/*     */       {
/* 370 */         if (this.timestamp == null)
/*     */         {
/* 372 */           this.currentInstance.add(this.entry);
/* 373 */         } else if (this.lastTimestamp == null)
/*     */         {
/* 375 */           this.currentInstance.add(this.entry);
/* 376 */           this.lastTimestamp = this.timestamp;
/*     */ 
/*     */         }
/* 379 */         else if (this.timestamp.compareTo(this.lastTimestamp) >= 0)
/*     */         {
/*     */ 
/* 382 */           this.currentInstance.add(this.entry);
/* 383 */           this.lastTimestamp = this.timestamp;
/*     */ 
/*     */ 
/*     */         }
/* 387 */         else if ((this.currentInstance instanceof XTraceBufferedImpl)) {
/* 388 */           ((XTraceBufferedImpl)this.currentInstance).insertOrdered(this.entry);
/*     */         }
/*     */         else {
/* 391 */           this.currentInstance.add(this.entry);
/*     */         }
/*     */         
/*     */ 
/* 395 */         this.entry = null;
/* 396 */       } else if (tagName.equalsIgnoreCase("Attribute")) {
/* 397 */         String value = this.buffer.toString().trim();
/* 398 */         if (value.length() > 0)
/*     */         {
/* 400 */           this.genericAttribute.setValue(this.buffer.toString().trim());
/*     */           
/*     */ 
/* 403 */           if (this.entry != null) {
/* 404 */             this.entry.getAttributes().put(this.genericAttribute.getKey(), this.genericAttribute);
/* 405 */           } else if (this.currentInstance != null) {
/* 406 */             this.currentInstance.getAttributes().put(this.genericAttribute.getKey(), this.genericAttribute);
/* 407 */           } else if (this.currentProcess != null) {
/* 408 */             this.currentProcess.getAttributes().put(this.genericAttribute.getKey(), this.genericAttribute);
/* 409 */           } else if (this.sourceOpen == true) {
/* 410 */             this.sourceAttribute.getAttributes().put(this.genericAttribute.getKey(), this.genericAttribute);
/*     */           }
/*     */         }
/*     */         
/* 414 */         this.genericAttribute = null;
/* 415 */       } else if (tagName.equalsIgnoreCase("EventType"))
/*     */       {
/* 417 */         if (this.eventTypeAttribute.getValue().equals("__INVALID__")) {
/* 418 */           String type = this.buffer.toString().trim();
/* 419 */           if (type.length() > 0) {
/* 420 */             this.eventTypeAttribute.setValue(type);
/* 421 */             this.entry.getAttributes().put(this.eventTypeAttribute.getKey(), this.eventTypeAttribute);
/*     */           }
/*     */         } else {
/* 424 */           this.entry.getAttributes().put(this.eventTypeAttribute.getKey(), this.eventTypeAttribute);
/*     */         }
/* 426 */         this.eventTypeAttribute = null;
/* 427 */       } else if (tagName.equalsIgnoreCase("WorkflowModelElement"))
/*     */       {
/* 429 */         XConceptExtension.instance().assignName(this.entry, this.buffer.toString().trim());
/* 430 */       } else if (tagName.equalsIgnoreCase("Timestamp"))
/*     */       {
/* 432 */         String tsString = this.buffer.toString().trim();
/* 433 */         this.timestamp = XMxmlParser.this.xsDateTimeConversion.parseXsDateTime(tsString);
/* 434 */         if (this.timestamp != null) {
/* 435 */           XAttributeTimestamp timestampAttribute = (XAttributeTimestamp)XTimeExtension.ATTR_TIMESTAMP.clone();
/* 436 */           timestampAttribute.setValue(this.timestamp);
/* 437 */           this.entry.getAttributes().put(timestampAttribute.getKey(), timestampAttribute);
/*     */         }
/* 439 */       } else if (tagName.equalsIgnoreCase("Originator"))
/*     */       {
/* 441 */         String originator = this.buffer.toString().trim();
/* 442 */         if (originator.length() > 0) {
/* 443 */           this.originatorAttribute.setValue(originator);
/*     */         }
/* 445 */         this.entry.getAttributes().put(this.originatorAttribute.getKey(), this.originatorAttribute);
/* 446 */         this.originatorAttribute = null;
/*     */       }
/*     */       
/* 449 */       this.buffer.delete(0, this.buffer.length());
/*     */     }
/*     */     
/*     */     private void addModelReferences(Attributes attrs, XAttributable subject) {
/* 453 */       String refs = attrs.getValue("modelReference");
/* 454 */       if (refs != null) {
/* 455 */         XAttributeLiteral attribute = (XAttributeLiteral)XSemanticExtension.ATTR_MODELREFERENCE.clone();
/* 456 */         attribute.setValue(refs);
/* 457 */         subject.getAttributes().put(attribute.getKey(), attribute);
/*     */       }
/*     */     }
/*     */     
/*     */ 
/*     */ 
/*     */     public void characters(char[] str, int offset, int len)
/*     */       throws SAXException
/*     */     {
/* 466 */       this.buffer.append(str, offset, len);
/*     */     }
/*     */     
/*     */ 
/*     */ 
/*     */     public void ignorableWhitespace(char[] str, int offset, int len)
/*     */       throws SAXException
/*     */     {
/* 474 */       this.buffer.append(str, offset, len);
/*     */     }
/*     */   }
/*     */ }


/* Location:              /home/menick/Scrivania/ProcessMining/CNMining/CNMining.jar!/org/deckfour/xes/in/XMxmlParser.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */