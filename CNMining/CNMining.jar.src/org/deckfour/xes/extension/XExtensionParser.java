/*     */ package org.deckfour.xes.extension;
/*     */ 
/*     */ import java.io.BufferedInputStream;
/*     */ import java.io.File;
/*     */ import java.io.FileInputStream;
/*     */ import java.io.IOException;
/*     */ import java.net.URI;
/*     */ import java.net.URISyntaxException;
/*     */ import java.net.URL;
/*     */ import java.util.Collection;
/*     */ import javax.xml.parsers.ParserConfigurationException;
/*     */ import javax.xml.parsers.SAXParser;
/*     */ import javax.xml.parsers.SAXParserFactory;
/*     */ import org.deckfour.xes.factory.XFactory;
/*     */ import org.deckfour.xes.factory.XFactoryRegistry;
/*     */ import org.deckfour.xes.id.XIDFactory;
/*     */ import org.deckfour.xes.info.XGlobalAttributeNameMap;
/*     */ import org.deckfour.xes.model.XAttribute;
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
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class XExtensionParser
/*     */ {
/*  73 */   private static XExtensionParser singleton = null;
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   public static synchronized XExtensionParser instance()
/*     */   {
/*  80 */     if (singleton == null) {
/*  81 */       singleton = new XExtensionParser();
/*     */     }
/*  83 */     return singleton;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public XExtension parse(File file)
/*     */     throws IOException, ParserConfigurationException, SAXException
/*     */   {
/*  93 */     BufferedInputStream is = new BufferedInputStream(new FileInputStream(file));
/*     */     
/*  95 */     XExtensionHandler handler = new XExtensionHandler();
/*     */     
/*  97 */     SAXParserFactory parserFactory = SAXParserFactory.newInstance();
/*  98 */     SAXParser parser = parserFactory.newSAXParser();
/*  99 */     parser.parse(is, handler);
/* 100 */     is.close();
/* 101 */     return handler.getExtension();
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public XExtension parse(URI uri)
/*     */     throws IOException, ParserConfigurationException, SAXException
/*     */   {
/* 112 */     BufferedInputStream is = new BufferedInputStream(uri.toURL().openStream());
/*     */     
/* 114 */     XExtensionHandler handler = new XExtensionHandler();
/*     */     
/* 116 */     SAXParserFactory parserFactory = SAXParserFactory.newInstance();
/* 117 */     SAXParser parser = parserFactory.newSAXParser();
/* 118 */     parser.parse(is, handler);
/* 119 */     is.close();
/* 120 */     return handler.getExtension();
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   protected class XExtensionHandler
/*     */     extends DefaultHandler
/*     */   {
/*     */     protected XExtension extension;
/*     */     
/*     */ 
/*     */ 
/*     */ 
/*     */     protected XAttribute currentAttribute;
/*     */     
/*     */ 
/*     */ 
/*     */ 
/*     */     protected Collection<XAttribute> xAttributes;
/*     */     
/*     */ 
/*     */ 
/*     */ 
/*     */     protected XFactory factory;
/*     */     
/*     */ 
/*     */ 
/*     */ 
/*     */     public XExtensionHandler()
/*     */     {
/* 152 */       reset();
/*     */     }
/*     */     
/*     */ 
/*     */ 
/*     */     public void reset()
/*     */     {
/* 159 */       this.extension = null;
/* 160 */       this.currentAttribute = null;
/* 161 */       this.xAttributes = null;
/* 162 */       this.factory = ((XFactory)XFactoryRegistry.instance().currentDefault());
/*     */     }
/*     */     
/*     */ 
/*     */ 
/*     */ 
/*     */     public XExtension getExtension()
/*     */     {
/* 170 */       return this.extension;
/*     */     }
/*     */     
/*     */ 
/*     */ 
/*     */ 
/*     */     public void startElement(String uri, String localName, String qName, Attributes attributes)
/*     */       throws SAXException
/*     */     {
/* 179 */       String tagName = localName;
/* 180 */       if (tagName.equalsIgnoreCase("")) {
/* 181 */         tagName = qName;
/*     */       }
/*     */       
/* 184 */       if (tagName.equalsIgnoreCase("xesextension")) {
/* 185 */         String xName = attributes.getValue("name");
/* 186 */         String xPrefix = attributes.getValue("prefix");
/* 187 */         URI xUri = null;
/*     */         try {
/* 189 */           xUri = new URI(attributes.getValue("uri"));
/*     */         } catch (URISyntaxException e) {
/* 191 */           e.printStackTrace();
/* 192 */           return;
/*     */         }
/* 194 */         this.extension = new XExtension(xName, xPrefix, xUri);
/* 195 */       } else if (tagName.equalsIgnoreCase("log")) {
/* 196 */         this.xAttributes = this.extension.getLogAttributes();
/* 197 */       } else if (tagName.equalsIgnoreCase("trace")) {
/* 198 */         this.xAttributes = this.extension.getTraceAttributes();
/* 199 */       } else if (tagName.equalsIgnoreCase("event")) {
/* 200 */         this.xAttributes = this.extension.getEventAttributes();
/* 201 */       } else if (tagName.equalsIgnoreCase("meta")) {
/* 202 */         this.xAttributes = this.extension.getMetaAttributes();
/* 203 */       } else if (tagName.equalsIgnoreCase("string")) {
/* 204 */         String key = this.extension.getPrefix() + ':' + attributes.getValue("key");
/* 205 */         this.currentAttribute = this.factory.createAttributeLiteral(key, "DEFAULT", this.extension);
/* 206 */         this.xAttributes.add(this.currentAttribute);
/* 207 */       } else if (tagName.equalsIgnoreCase("date")) {
/* 208 */         String key = this.extension.getPrefix() + ':' + attributes.getValue("key");
/* 209 */         this.currentAttribute = this.factory.createAttributeTimestamp(key, 0L, this.extension);
/* 210 */         this.xAttributes.add(this.currentAttribute);
/* 211 */       } else if (tagName.equalsIgnoreCase("int")) {
/* 212 */         String key = this.extension.getPrefix() + ':' + attributes.getValue("key");
/* 213 */         this.currentAttribute = this.factory.createAttributeDiscrete(key, 0L, this.extension);
/* 214 */         this.xAttributes.add(this.currentAttribute);
/* 215 */       } else if (tagName.equalsIgnoreCase("float")) {
/* 216 */         String key = this.extension.getPrefix() + ':' + attributes.getValue("key");
/* 217 */         this.currentAttribute = this.factory.createAttributeContinuous(key, 0.0D, this.extension);
/* 218 */         this.xAttributes.add(this.currentAttribute);
/* 219 */       } else if (tagName.equalsIgnoreCase("boolean")) {
/* 220 */         String key = this.extension.getPrefix() + ':' + attributes.getValue("key");
/* 221 */         this.currentAttribute = this.factory.createAttributeBoolean(key, false, this.extension);
/* 222 */         this.xAttributes.add(this.currentAttribute);
/* 223 */       } else if (tagName.equalsIgnoreCase("id")) {
/* 224 */         String key = this.extension.getPrefix() + ':' + attributes.getValue("key");
/* 225 */         this.currentAttribute = this.factory.createAttributeID(key, XIDFactory.instance().createId(), this.extension);
/* 226 */         this.xAttributes.add(this.currentAttribute);
/* 227 */       } else if ((this.currentAttribute != null) && (tagName.equalsIgnoreCase("alias")))
/*     */       {
/* 229 */         String mapping = attributes.getValue("mapping");
/* 230 */         String name = attributes.getValue("name");
/* 231 */         XGlobalAttributeNameMap.instance().registerMapping(mapping, this.currentAttribute.getKey(), name);
/*     */       }
/*     */     }
/*     */     
/*     */ 
/*     */ 
/*     */ 
/*     */     public void endElement(String uri, String localName, String qName)
/*     */       throws SAXException
/*     */     {
/* 241 */       String tagName = localName;
/* 242 */       if (tagName.equalsIgnoreCase("")) {
/* 243 */         tagName = qName;
/*     */       }
/*     */       
/* 246 */       if ((tagName.equalsIgnoreCase("string")) || (tagName.equalsIgnoreCase("date")) || (tagName.equalsIgnoreCase("int")) || (tagName.equalsIgnoreCase("float")) || (tagName.equalsIgnoreCase("boolean")) || (tagName.equalsIgnoreCase("id")))
/*     */       {
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/* 252 */         this.currentAttribute = null;
/*     */       }
/*     */     }
/*     */   }
/*     */ }


/* Location:              /home/menick/Scrivania/ProcessMining/CNMining/CNMining.jar!/org/deckfour/xes/extension/XExtensionParser.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */