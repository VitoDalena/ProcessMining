/*     */ package org.processmining.plugins.core;
/*     */ 
/*     */ import java.io.File;
/*     */ import java.io.FileInputStream;
/*     */ import java.io.PrintStream;
/*     */ import java.util.Collection;
/*     */ import java.util.Iterator;
/*     */ import java.util.Set;
/*     */ import org.deckfour.xes.extension.std.XConceptExtension;
/*     */ import org.deckfour.xes.factory.XFactory;
/*     */ import org.deckfour.xes.in.XMxmlParser;
/*     */ import org.deckfour.xes.in.XParser;
/*     */ import org.deckfour.xes.in.XParserRegistry;
/*     */ import org.deckfour.xes.in.XesXmlParser;
/*     */ import org.deckfour.xes.model.XAttribute;
/*     */ import org.deckfour.xes.model.XAttributeMap;
/*     */ import org.deckfour.xes.model.XElement;
/*     */ import org.deckfour.xes.model.XLog;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class Utils
/*     */ {
/*     */   public static XLog parseLog(String filename, XFactory factory)
/*     */     throws Exception
/*     */   {
/*     */     XParser parser;
/*     */     XParser parser;
/*  34 */     if ((filename.toLowerCase().endsWith(".xes")) || 
/*  35 */       (filename.toLowerCase().endsWith(".xez")) || 
/*  36 */       (filename.toLowerCase().endsWith(".xes.gz"))) {
/*  37 */       parser = new XesXmlParser(factory);
/*     */     } else {
/*  39 */       parser = new XMxmlParser(factory);
/*     */     }
/*  41 */     Collection<XLog> logs = null;
/*     */     try {
/*  43 */       logs = parser.parse(new FileInputStream(new File(filename)));
/*     */     } catch (Exception e) {
/*  45 */       logs = null; }
/*     */     Iterator localIterator;
/*  47 */     if (logs == null)
/*     */     {
/*  49 */       localIterator = XParserRegistry.instance().getAvailable().iterator(); break label164; for (;;) { XParser p = (XParser)localIterator.next();
/*  50 */         if (p != parser)
/*     */         {
/*     */           try
/*     */           {
/*  54 */             logs = p.parse(new FileInputStream(new File(filename)));
/*  55 */             if (logs.size() <= 0) {
/*  49 */               if (localIterator.hasNext())
/*     */               {
/*     */                 continue;
/*     */               }
/*     */               
/*     */             }
/*     */             
/*     */ 
/*     */           }
/*     */           catch (Exception e1)
/*     */           {
/*  60 */             logs = null;
/*     */           }
/*     */         }
/*     */       }
/*     */     }
/*     */     
/*     */     label164:
/*  67 */     if ((logs == null) || (logs.size() == 0)) {
/*  68 */       throw new Exception("No processes contained in log!");
/*     */     }
/*     */     
/*  71 */     XLog log = (XLog)logs.iterator().next();
/*  72 */     if (XConceptExtension.instance().extractName(log) == null)
/*     */     {
/*     */ 
/*     */ 
/*  76 */       XConceptExtension.instance().assignName(log, 
/*  77 */         "Anonymous log imported from " + filename);
/*     */     }
/*     */     
/*  80 */     if (log.isEmpty()) {
/*  81 */       throw new Exception("No process instances contained in log!");
/*     */     }
/*     */     
/*  84 */     return log;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public static void printAttributes(XElement element)
/*     */   {
/*  95 */     XAttributeMap attributes = element.getAttributes();
/*  96 */     System.out.println("Element Attributes:");
/*  97 */     Iterator<String> iterator = attributes.keySet().iterator();
/*  98 */     while (iterator.hasNext()) {
/*  99 */       String key = (String)iterator.next();
/* 100 */       System.out.println("key ext - " + key);
/* 101 */       System.out.println("key att - " + ((XAttribute)attributes.get(key)).getKey());
/* 102 */       XAttribute xAttribute = (XAttribute)attributes.get(key);
/* 103 */       System.out.println("Attribute - " + xAttribute + " di tipo: " + 
/* 104 */         xAttribute.getClass());
/* 105 */       System.out.println("Extension of Attribute - " + 
/* 106 */         xAttribute.getExtension());
/*     */     }
/*     */   }
/*     */ }


/* Location:              /home/menick/Scrivania/ProcessMining/CNMining/CNMining.jar!/org/processmining/plugins/core/Utils.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       0.7.1
 */