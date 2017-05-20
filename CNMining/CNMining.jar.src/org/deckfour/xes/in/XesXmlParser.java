/*     */ package org.deckfour.xes.in;
/*     */ 
/*     */ import java.io.BufferedInputStream;
/*     */ import java.io.File;
/*     */ import java.io.InputStream;
/*     */ import java.net.URI;
/*     */ import java.util.ArrayList;
/*     */ import java.util.Date;
/*     */ import java.util.HashSet;
/*     */ import java.util.List;
/*     */ import java.util.Set;
/*     */ import java.util.Stack;
/*     */ import javax.xml.parsers.SAXParser;
/*     */ import javax.xml.parsers.SAXParserFactory;
/*     */ import org.deckfour.xes.classification.XEventAttributeClassifier;
/*     */ import org.deckfour.xes.classification.XEventClassifier;
/*     */ import org.deckfour.xes.extension.XExtension;
/*     */ import org.deckfour.xes.extension.XExtensionManager;
/*     */ import org.deckfour.xes.factory.XFactory;
/*     */ import org.deckfour.xes.factory.XFactoryRegistry;
/*     */ import org.deckfour.xes.id.XID;
/*     */ import org.deckfour.xes.model.XAttributable;
/*     */ import org.deckfour.xes.model.XAttribute;
/*     */ import org.deckfour.xes.model.XAttributeMap;
/*     */ import org.deckfour.xes.model.XEvent;
/*     */ import org.deckfour.xes.model.XLog;
/*     */ import org.deckfour.xes.model.XTrace;
/*     */ import org.deckfour.xes.model.buffered.XTraceBufferedImpl;
/*     */ import org.deckfour.xes.util.XTokenHelper;
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
/*     */ 
/*     */ public class XesXmlParser
/*     */   extends XParser
/*     */ {
/*  81 */   protected XsDateTimeConversion xsDateTimeConversion = new XsDateTimeConversion();
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*  86 */   protected static final URI XES_URI = URI.create("http://www.xes-standard.org/");
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   protected XFactory factory;
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public XesXmlParser(XFactory factory)
/*     */   {
/* 102 */     this.factory = factory;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   public XesXmlParser()
/*     */   {
/* 110 */     this((XFactory)XFactoryRegistry.instance().currentDefault());
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public String author()
/*     */   {
/* 120 */     return "Christian W. Günther";
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public boolean canParse(File file)
/*     */   {
/* 130 */     String filename = file.getName();
/* 131 */     return endsWithIgnoreCase(filename, ".xes");
/*     */   }
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
/* 143 */     return "Reads XES models from plain XML serializations";
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public String name()
/*     */   {
/* 153 */     return "XES XML";
/*     */   }
/*     */   
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
/* 166 */     BufferedInputStream bis = new BufferedInputStream(is);
/*     */     
/* 168 */     XesXmlHandler handler = new XesXmlHandler();
/*     */     
/* 170 */     SAXParserFactory parserFactory = SAXParserFactory.newInstance();
/* 171 */     parserFactory.setNamespaceAware(false);
/* 172 */     SAXParser parser = parserFactory.newSAXParser();
/* 173 */     parser.parse(bis, handler);
/* 174 */     bis.close();
/* 175 */     ArrayList<XLog> wrapper = new ArrayList();
/* 176 */     wrapper.add(handler.getLog());
/* 177 */     return wrapper;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   protected class XesXmlHandler
/*     */     extends DefaultHandler
/*     */   {
/*     */     protected XLog log;
/*     */     
/*     */ 
/*     */ 
/*     */ 
/*     */     protected XTrace trace;
/*     */     
/*     */ 
/*     */ 
/*     */ 
/*     */     protected XEvent event;
/*     */     
/*     */ 
/*     */ 
/*     */ 
/*     */     protected Stack<XAttribute> attributeStack;
/*     */     
/*     */ 
/*     */ 
/*     */     protected Stack<XAttributable> attributableStack;
/*     */     
/*     */ 
/*     */ 
/*     */     protected HashSet<XExtension> extensions;
/*     */     
/*     */ 
/*     */ 
/*     */     protected List<XAttribute> globals;
/*     */     
/*     */ 
/*     */ 
/*     */ 
/*     */     public XesXmlHandler()
/*     */     {
/* 221 */       this.log = null;
/* 222 */       this.trace = null;
/* 223 */       this.event = null;
/* 224 */       this.attributeStack = new Stack();
/* 225 */       this.attributableStack = new Stack();
/* 226 */       this.extensions = new HashSet();
/* 227 */       this.globals = null;
/*     */     }
/*     */     
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     public XLog getLog()
/*     */     {
/* 236 */       return this.log;
/*     */     }
/*     */     
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     public void startElement(String uri, String localName, String qName, Attributes attributes)
/*     */       throws SAXException
/*     */     {
/* 250 */       String tagName = localName.trim();
/* 251 */       if (tagName.length() == 0) {
/* 252 */         tagName = qName;
/*     */       }
/*     */       
/* 255 */       if ((tagName.equalsIgnoreCase("string")) || (tagName.equalsIgnoreCase("date")) || (tagName.equalsIgnoreCase("int")) || (tagName.equalsIgnoreCase("float")) || (tagName.equalsIgnoreCase("boolean")) || (tagName.equalsIgnoreCase("id")))
/*     */       {
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/* 262 */         String key = attributes.getValue("key");
/* 263 */         if (key == null)
/*     */         {
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/* 270 */           key = "";
/*     */         }
/* 272 */         String value = attributes.getValue("value");
/* 273 */         if (value != null)
/*     */         {
/* 275 */           XExtension extension = null;
/* 276 */           int colonIndex = key.indexOf(':');
/* 277 */           if ((colonIndex > 0) && (colonIndex < key.length() - 1)) {
/* 278 */             String prefix = key.substring(0, colonIndex);
/* 279 */             extension = XExtensionManager.instance().getByPrefix(prefix);
/*     */           }
/*     */           
/*     */ 
/* 283 */           XAttribute attribute = null;
/* 284 */           if (tagName.equalsIgnoreCase("string")) {
/* 285 */             attribute = XesXmlParser.this.factory.createAttributeLiteral(key, value, extension);
/*     */           }
/* 287 */           else if (tagName.equalsIgnoreCase("date")) {
/* 288 */             Date date = XesXmlParser.this.xsDateTimeConversion.parseXsDateTime(value);
/* 289 */             if (date != null) {
/* 290 */               attribute = XesXmlParser.this.factory.createAttributeTimestamp(key, date, extension);
/*     */             }
/*     */             else {
/* 293 */               return;
/*     */             }
/* 295 */           } else if (tagName.equalsIgnoreCase("int")) {
/* 296 */             attribute = XesXmlParser.this.factory.createAttributeDiscrete(key, Long.parseLong(value), extension);
/*     */           }
/* 298 */           else if (tagName.equalsIgnoreCase("float")) {
/* 299 */             attribute = XesXmlParser.this.factory.createAttributeContinuous(key, Double.parseDouble(value), extension);
/*     */           }
/* 301 */           else if (tagName.equalsIgnoreCase("boolean")) {
/* 302 */             attribute = XesXmlParser.this.factory.createAttributeBoolean(key, Boolean.parseBoolean(value), extension);
/*     */           }
/* 304 */           else if (tagName.equalsIgnoreCase("id")) {
/* 305 */             attribute = XesXmlParser.this.factory.createAttributeID(key, XID.parse(value), extension);
/*     */           }
/*     */           
/*     */ 
/* 309 */           this.attributeStack.push(attribute);
/* 310 */           this.attributableStack.push(attribute);
/*     */         }
/* 312 */       } else if (tagName.equalsIgnoreCase("event"))
/*     */       {
/* 314 */         this.event = XesXmlParser.this.factory.createEvent();
/* 315 */         this.attributableStack.push(this.event);
/* 316 */       } else if (tagName.equalsIgnoreCase("trace"))
/*     */       {
/* 318 */         this.trace = XesXmlParser.this.factory.createTrace();
/* 319 */         this.attributableStack.push(this.trace);
/* 320 */       } else if (tagName.equalsIgnoreCase("log"))
/*     */       {
/* 322 */         this.log = XesXmlParser.this.factory.createLog();
/* 323 */         this.attributableStack.push(this.log);
/* 324 */       } else if (tagName.equalsIgnoreCase("extension"))
/*     */       {
/* 326 */         XExtension extension = null;
/* 327 */         String uriString = attributes.getValue("uri");
/* 328 */         if (uriString != null) {
/* 329 */           extension = XExtensionManager.instance().getByUri(URI.create(uriString));
/*     */         }
/*     */         else {
/* 332 */           String prefixString = attributes.getValue("prefix");
/* 333 */           if (prefixString != null) {
/* 334 */             extension = XExtensionManager.instance().getByPrefix(prefixString);
/*     */           }
/*     */         }
/*     */         
/* 338 */         if (extension != null) {
/* 339 */           this.log.getExtensions().add(extension);
/*     */         }
/* 341 */       } else if (tagName.equalsIgnoreCase("global"))
/*     */       {
/* 343 */         String scope = attributes.getValue("scope");
/* 344 */         if (scope.equalsIgnoreCase("trace")) {
/* 345 */           this.globals = this.log.getGlobalTraceAttributes();
/* 346 */         } else if (scope.equalsIgnoreCase("event")) {
/* 347 */           this.globals = this.log.getGlobalEventAttributes();
/*     */         }
/* 349 */       } else if (tagName.equalsIgnoreCase("classifier"))
/*     */       {
/* 351 */         String name = attributes.getValue("name");
/* 352 */         String keys = attributes.getValue("keys");
/* 353 */         if ((name != null) && (keys != null) && (name.length() > 0) && (keys.length() > 0))
/*     */         {
/* 355 */           List<String> keysList = XesXmlParser.this.fixKeys(this.log, XTokenHelper.extractTokens(keys));
/* 356 */           String[] keysArray = new String[keysList.size()];
/* 357 */           int i = 0;
/* 358 */           for (String key : keysList) {
/* 359 */             keysArray[(i++)] = key;
/*     */           }
/* 361 */           XEventClassifier classifier = new XEventAttributeClassifier(name, keysArray);
/*     */           
/* 363 */           this.log.getClassifiers().add(classifier);
/*     */         }
/*     */       }
/*     */     }
/*     */     
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     public void endElement(String uri, String localName, String qName)
/*     */       throws SAXException
/*     */     {
/* 378 */       String tagName = localName.trim();
/* 379 */       if (tagName.length() == 0) {
/* 380 */         tagName = qName;
/*     */       }
/*     */       
/* 383 */       if (tagName.equalsIgnoreCase("global"))
/*     */       {
/* 385 */         this.globals = null;
/* 386 */       } else if ((tagName.equalsIgnoreCase("string")) || (tagName.equalsIgnoreCase("date")) || (tagName.equalsIgnoreCase("int")) || (tagName.equalsIgnoreCase("float")) || (tagName.equalsIgnoreCase("boolean")) || (tagName.equalsIgnoreCase("id")))
/*     */       {
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/* 392 */         XAttribute attribute = (XAttribute)this.attributeStack.pop();
/* 393 */         this.attributableStack.pop();
/* 394 */         if (this.globals != null) {
/* 395 */           this.globals.add(attribute);
/*     */         } else {
/* 397 */           ((XAttributable)this.attributableStack.peek()).getAttributes().put(attribute.getKey(), attribute);
/*     */         }
/*     */       }
/* 400 */       else if (tagName.equalsIgnoreCase("event")) {
/* 401 */         this.trace.add(this.event);
/* 402 */         this.event = null;
/* 403 */         this.attributableStack.pop();
/* 404 */       } else if (tagName.equalsIgnoreCase("trace")) {
/* 405 */         if ((this.trace instanceof XTraceBufferedImpl)) {
/* 406 */           ((XTraceBufferedImpl)this.trace).consolidate();
/*     */         }
/* 408 */         this.log.add(this.trace);
/* 409 */         this.trace = null;
/* 410 */         this.attributableStack.pop();
/* 411 */       } else if (tagName.equalsIgnoreCase("log"))
/*     */       {
/* 413 */         for (XExtension ext : this.extensions) {
/* 414 */           this.log.getExtensions().add(ext);
/*     */         }
/* 416 */         this.attributableStack.pop();
/*     */       }
/*     */     }
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   private List<String> fixKeys(XLog log, List<String> keys)
/*     */   {
/* 426 */     List<String> fixedKeys = fixKeys(log, keys, 0);
/* 427 */     return fixedKeys == null ? keys : fixedKeys;
/*     */   }
/*     */   
/*     */   private List<String> fixKeys(XLog log, List<String> keys, int index) {
/* 431 */     if (index >= keys.size())
/*     */     {
/*     */ 
/*     */ 
/* 435 */       return keys;
/*     */     }
/*     */     
/*     */ 
/*     */ 
/*     */ 
/* 441 */     if (findGlobalEventAttribute(log, (String)keys.get(index)))
/*     */     {
/*     */ 
/*     */ 
/*     */ 
/* 446 */       List<String> fixedKeys = fixKeys(log, keys, index + 1);
/* 447 */       if (fixedKeys != null)
/*     */       {
/*     */ 
/*     */ 
/* 451 */         return fixedKeys;
/*     */       }
/*     */     }
/*     */     
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/* 463 */     if (index + 1 == keys.size())
/*     */     {
/*     */ 
/*     */ 
/* 467 */       return null;
/*     */     }
/*     */     
/*     */ 
/*     */ 
/* 472 */     List<String> newKeys = new ArrayList(keys.size() - 1);
/* 473 */     for (int i = 0; i < index; i++) {
/* 474 */       newKeys.add(keys.get(i));
/*     */     }
/*     */     
/*     */ 
/*     */ 
/* 479 */     newKeys.add((String)keys.get(index) + " " + (String)keys.get(index + 1));
/*     */     
/*     */ 
/*     */ 
/* 483 */     for (int i = index + 2; i < keys.size(); i++) {
/* 484 */       newKeys.add(keys.get(i));
/*     */     }
/*     */     
/*     */ 
/*     */ 
/* 489 */     return fixKeys(log, newKeys, index);
/*     */   }
/*     */   
/*     */   private boolean findGlobalEventAttribute(XLog log, String key)
/*     */   {
/* 494 */     for (XAttribute attribute : log.getGlobalEventAttributes()) {
/* 495 */       if (attribute.getKey().equals(key)) {
/* 496 */         return true;
/*     */       }
/*     */     }
/*     */     
/*     */ 
/*     */ 
/* 502 */     return false;
/*     */   }
/*     */ }


/* Location:              /home/menick/Scrivania/ProcessMining/CNMining/CNMining.jar!/org/deckfour/xes/in/XesXmlParser.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */