/*     */ package edu.uci.ics.jung.io.graphml.parser;
/*     */ 
/*     */ import edu.uci.ics.jung.graph.Hypergraph;
/*     */ import edu.uci.ics.jung.io.GraphIOException;
/*     */ import edu.uci.ics.jung.io.graphml.EndpointMetadata;
/*     */ import edu.uci.ics.jung.io.graphml.EndpointMetadata.EndpointType;
/*     */ import edu.uci.ics.jung.io.graphml.ExceptionConverter;
/*     */ import java.util.HashMap;
/*     */ import java.util.Iterator;
/*     */ import java.util.Map;
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
/*     */ 
/*     */ 
/*     */ public class EndpointElementParser<G extends Hypergraph<V, E>, V, E>
/*     */   extends AbstractElementParser<G, V, E>
/*     */ {
/*  37 */   private static final Map<String, EndpointMetadata.EndpointType> endpointTypeMap = new HashMap();
/*     */   
/*  39 */   static { endpointTypeMap.put("in", EndpointMetadata.EndpointType.IN);
/*  40 */     endpointTypeMap.put("out", EndpointMetadata.EndpointType.OUT);
/*  41 */     endpointTypeMap.put("undir", EndpointMetadata.EndpointType.UNDIR);
/*     */   }
/*     */   
/*     */   public EndpointElementParser(ParserContext<G, V, E> parserContext) {
/*  45 */     super(parserContext);
/*     */   }
/*     */   
/*     */ 
/*     */   public EndpointMetadata parse(XMLEventReader xmlEventReader, StartElement start)
/*     */     throws GraphIOException
/*     */   {
/*     */     try
/*     */     {
/*  54 */       EndpointMetadata endpoint = new EndpointMetadata();
/*     */       
/*     */ 
/*  57 */       Iterator iterator = start.getAttributes();
/*  58 */       while (iterator.hasNext()) {
/*  59 */         Attribute attribute = (Attribute)iterator.next();
/*  60 */         String name = attribute.getName().getLocalPart();
/*  61 */         String value = attribute.getValue();
/*  62 */         if ((endpoint.getId() == null) && ("id".equals(name)))
/*  63 */           endpoint.setId(value);
/*  64 */         if ((endpoint.getPort() == null) && ("port".equals(name)))
/*  65 */           endpoint.setPort(value);
/*  66 */         if ((endpoint.getNode() == null) && ("node".equals(name)))
/*  67 */           endpoint.setNode(value);
/*  68 */         if ("type".equals(name)) {
/*  69 */           EndpointMetadata.EndpointType t = (EndpointMetadata.EndpointType)endpointTypeMap.get(value);
/*  70 */           if (t == null) {
/*  71 */             t = EndpointMetadata.EndpointType.UNDIR;
/*     */           }
/*  73 */           endpoint.setEndpointType(t);
/*     */         } else {
/*  75 */           endpoint.setProperty(name, value);
/*     */         }
/*     */       }
/*     */       
/*     */ 
/*  80 */       if (endpoint.getNode() == null) {
/*  81 */         throw new GraphIOException("Element 'endpoint' is missing attribute 'node'");
/*     */       }
/*     */       
/*     */ 
/*  85 */       while (xmlEventReader.hasNext())
/*     */       {
/*  87 */         XMLEvent event = xmlEventReader.nextEvent();
/*  88 */         if (event.isStartElement()) {
/*  89 */           StartElement element = (StartElement)event;
/*     */           
/*  91 */           String name = element.getName().getLocalPart();
/*  92 */           if ("desc".equals(name)) {
/*  93 */             String desc = (String)getParser(name).parse(xmlEventReader, element);
/*  94 */             endpoint.setDescription(desc);
/*     */           }
/*     */           else
/*     */           {
/*  98 */             getUnknownParser().parse(xmlEventReader, element);
/*     */           }
/*     */         }
/*     */         
/* 102 */         if (event.isEndElement()) {
/* 103 */           EndElement end = (EndElement)event;
/* 104 */           verifyMatch(start, end);
/* 105 */           break;
/*     */         }
/*     */       }
/*     */       
/*     */ 
/* 110 */       applyKeys(endpoint);
/*     */       
/* 112 */       return endpoint;
/*     */     }
/*     */     catch (Exception e) {
/* 115 */       ExceptionConverter.convert(e);
/*     */     }
/*     */     
/* 118 */     return null;
/*     */   }
/*     */ }


/* Location:              /home/menick/Scrivania/ProcessMining/CNMining/CNMining.jar!/edu/uci/ics/jung/io/graphml/parser/EndpointElementParser.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       0.7.1
 */