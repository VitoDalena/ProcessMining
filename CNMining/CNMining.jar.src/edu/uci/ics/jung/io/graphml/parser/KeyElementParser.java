/*     */ package edu.uci.ics.jung.io.graphml.parser;
/*     */ 
/*     */ import edu.uci.ics.jung.graph.Hypergraph;
/*     */ import edu.uci.ics.jung.io.GraphIOException;
/*     */ import edu.uci.ics.jung.io.graphml.ExceptionConverter;
/*     */ import edu.uci.ics.jung.io.graphml.Key;
/*     */ import edu.uci.ics.jung.io.graphml.Key.ForType;
/*     */ import java.util.Iterator;
/*     */ import javax.xml.namespace.QName;
/*     */ import javax.xml.stream.XMLEventReader;
/*     */ import javax.xml.stream.events.Attribute;
/*     */ import javax.xml.stream.events.EndElement;
/*     */ import javax.xml.stream.events.StartElement;
/*     */ import javax.xml.stream.events.XMLEvent;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class KeyElementParser<G extends Hypergraph<V, E>, V, E>
/*     */   extends AbstractElementParser<G, V, E>
/*     */ {
/*     */   public KeyElementParser(ParserContext<G, V, E> parserContext)
/*     */   {
/*  35 */     super(parserContext);
/*     */   }
/*     */   
/*     */ 
/*     */   public Key parse(XMLEventReader xmlEventReader, StartElement start)
/*     */     throws GraphIOException
/*     */   {
/*     */     try
/*     */     {
/*  44 */       Key key = new Key();
/*     */       
/*     */ 
/*  47 */       Iterator iterator = start.getAttributes();
/*  48 */       while (iterator.hasNext()) {
/*  49 */         Attribute attribute = (Attribute)iterator.next();
/*  50 */         String name = attribute.getName().getLocalPart();
/*  51 */         String value = attribute.getValue();
/*  52 */         if ((key.getId() == null) && ("id".equals(name))) {
/*  53 */           key.setId(value);
/*  54 */         } else if ((key.getAttributeName() == null) && ("attr.name".equals(name)))
/*     */         {
/*  56 */           key.setAttributeName(value);
/*  57 */         } else if ((key.getAttributeType() == null) && ("attr.type".equals(name)))
/*     */         {
/*  59 */           key.setAttributeType(value);
/*  60 */         } else if ("for".equals(name)) {
/*  61 */           key.setForType(convertFor(value));
/*     */         }
/*     */       }
/*     */       
/*     */ 
/*  66 */       if (key.getId() == null) {
/*  67 */         throw new GraphIOException("Element 'key' is missing attribute 'id'");
/*     */       }
/*     */       
/*     */ 
/*  71 */       while (xmlEventReader.hasNext())
/*     */       {
/*  73 */         XMLEvent event = xmlEventReader.nextEvent();
/*  74 */         if (event.isStartElement()) {
/*  75 */           StartElement element = (StartElement)event;
/*     */           
/*  77 */           String name = element.getName().getLocalPart();
/*  78 */           if ("desc".equals(name)) {
/*  79 */             String desc = (String)getParser(name).parse(xmlEventReader, element);
/*  80 */             key.setDescription(desc);
/*  81 */           } else if ("default".equals(name)) {
/*  82 */             String defaultValue = (String)getParser(name).parse(xmlEventReader, element);
/*  83 */             key.setDefaultValue(defaultValue);
/*     */           }
/*     */           else
/*     */           {
/*  87 */             getUnknownParser().parse(xmlEventReader, element);
/*     */           }
/*     */         }
/*     */         
/*  91 */         if (event.isEndElement()) {
/*  92 */           EndElement end = (EndElement)event;
/*  93 */           verifyMatch(start, end);
/*  94 */           break;
/*     */         }
/*     */       }
/*     */       
/*  98 */       return key;
/*     */     }
/*     */     catch (Exception e) {
/* 101 */       ExceptionConverter.convert(e);
/*     */     }
/*     */     
/* 104 */     return null;
/*     */   }
/*     */   
/*     */   public static Key.ForType convertFor(String value)
/*     */   {
/* 109 */     if (value != null)
/*     */     {
/* 111 */       if ("graph".equals(value)) {
/* 112 */         return Key.ForType.GRAPH;
/*     */       }
/* 114 */       if ("edge".equals(value)) {
/* 115 */         return Key.ForType.EDGE;
/*     */       }
/* 117 */       if ("endpoint".equals(value)) {
/* 118 */         return Key.ForType.ENDPOINT;
/*     */       }
/* 120 */       if ("hyperedge".equals(value)) {
/* 121 */         return Key.ForType.HYPEREDGE;
/*     */       }
/* 123 */       if ("node".equals(value)) {
/* 124 */         return Key.ForType.NODE;
/*     */       }
/* 126 */       if ("port".equals(value)) {
/* 127 */         return Key.ForType.PORT;
/*     */       }
/*     */     }
/*     */     
/* 131 */     return Key.ForType.ALL;
/*     */   }
/*     */ }


/* Location:              /home/menick/Scrivania/ProcessMining/CNMining/CNMining.jar!/edu/uci/ics/jung/io/graphml/parser/KeyElementParser.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       0.7.1
 */