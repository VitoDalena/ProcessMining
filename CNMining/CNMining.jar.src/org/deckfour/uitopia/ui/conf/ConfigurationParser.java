/*     */ package org.deckfour.uitopia.ui.conf;
/*     */ 
/*     */ import java.io.BufferedInputStream;
/*     */ import java.io.File;
/*     */ import java.io.FileInputStream;
/*     */ import java.io.IOException;
/*     */ import java.io.InputStream;
/*     */ import java.util.Stack;
/*     */ import javax.xml.parsers.ParserConfigurationException;
/*     */ import javax.xml.parsers.SAXParser;
/*     */ import javax.xml.parsers.SAXParserFactory;
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
/*     */ public class ConfigurationParser
/*     */ {
/*     */   public static ConfigurationSet parse(File file)
/*     */     throws ParserConfigurationException, SAXException, IOException
/*     */   {
/*  59 */     InputStream is = new FileInputStream(file);
/*  60 */     return parse(is);
/*     */   }
/*     */   
/*     */   public static ConfigurationSet parse(InputStream is) throws ParserConfigurationException, SAXException, IOException
/*     */   {
/*  65 */     BufferedInputStream bis = new BufferedInputStream(is); void 
/*     */     
/*  67 */       tmp20_17 = new ConfigurationParser();tmp20_17.getClass();ConfigurationHandler handler = new ConfigurationHandler(tmp20_17);
/*     */     
/*  69 */     SAXParserFactory parserFactory = SAXParserFactory.newInstance();
/*  70 */     parserFactory.setNamespaceAware(false);
/*  71 */     SAXParser parser = parserFactory.newSAXParser();
/*  72 */     parser.parse(bis, handler);
/*  73 */     bis.close();
/*  74 */     return handler.getResult();
/*     */   }
/*     */   
/*     */   protected class ConfigurationHandler extends DefaultHandler
/*     */   {
/*     */     private Stack<ConfigurationSet> stack;
/*     */     private ConfigurationSet master;
/*     */     
/*     */     protected ConfigurationHandler() {
/*  83 */       this.stack = new Stack();
/*  84 */       this.master = null;
/*     */     }
/*     */     
/*     */     public ConfigurationSet getResult() {
/*  88 */       return this.master;
/*     */     }
/*     */     
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     public void startElement(String uri, String localName, String qName, Attributes attributes)
/*     */       throws SAXException
/*     */     {
/*  98 */       String tagName = localName.trim();
/*  99 */       if (tagName.length() == 0) {
/* 100 */         tagName = qName;
/*     */       }
/*     */       
/* 103 */       if (tagName.equalsIgnoreCase("set")) {
/* 104 */         String name = attributes.getValue("name");
/* 105 */         ConfigurationSet set = new ConfigurationSet(name);
/* 106 */         if (this.master == null) {
/* 107 */           this.master = set;
/*     */         } else {
/* 109 */           ((ConfigurationSet)this.stack.peek()).addChild(set);
/*     */         }
/* 111 */         this.stack.push(set);
/* 112 */       } else if (tagName.equalsIgnoreCase("attribute")) {
/* 113 */         String key = attributes.getValue("key");
/* 114 */         String value = attributes.getValue("value");
/* 115 */         ((ConfigurationSet)this.stack.peek()).put(key, value);
/*     */       }
/*     */     }
/*     */     
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     public void endElement(String uri, String localName, String qName)
/*     */       throws SAXException
/*     */     {
/* 126 */       String tagName = localName.trim();
/* 127 */       if (tagName.length() == 0) {
/* 128 */         tagName = qName;
/*     */       }
/*     */       
/* 131 */       if (tagName.equalsIgnoreCase("set")) {
/* 132 */         this.stack.pop();
/*     */       }
/*     */     }
/*     */   }
/*     */ }


/* Location:              /home/menick/Scrivania/ProcessMining/CNMining/CNMining.jar!/org/deckfour/uitopia/ui/conf/ConfigurationParser.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */