/*     */ package edu.uci.ics.jung.io.graphml.parser;
/*     */ 
/*     */ import edu.uci.ics.jung.graph.Hypergraph;
/*     */ import edu.uci.ics.jung.io.GraphIOException;
/*     */ import edu.uci.ics.jung.io.graphml.DataMetadata;
/*     */ import edu.uci.ics.jung.io.graphml.EdgeMetadata;
/*     */ import edu.uci.ics.jung.io.graphml.ExceptionConverter;
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
/*     */ public class EdgeElementParser<G extends Hypergraph<V, E>, V, E>
/*     */   extends AbstractElementParser<G, V, E>
/*     */ {
/*     */   public EdgeElementParser(ParserContext<G, V, E> parserContext)
/*     */   {
/*  33 */     super(parserContext);
/*     */   }
/*     */   
/*     */ 
/*     */   public EdgeMetadata parse(XMLEventReader xmlEventReader, StartElement start)
/*     */     throws GraphIOException
/*     */   {
/*     */     try
/*     */     {
/*  42 */       EdgeMetadata edge = new EdgeMetadata();
/*     */       
/*     */ 
/*  45 */       Iterator iterator = start.getAttributes();
/*  46 */       while (iterator.hasNext()) {
/*  47 */         Attribute attribute = (Attribute)iterator.next();
/*  48 */         String name = attribute.getName().getLocalPart();
/*  49 */         String value = attribute.getValue();
/*  50 */         if ((edge.getId() == null) && ("id".equals(name))) {
/*  51 */           edge.setId(value);
/*  52 */         } else if ((edge.isDirected() == null) && ("directed".equals(name))) {
/*  53 */           edge.setDirected(Boolean.valueOf("true".equals(value)));
/*  54 */         } else if ((edge.getSource() == null) && ("source".equals(name))) {
/*  55 */           edge.setSource(value);
/*  56 */         } else if ((edge.getTarget() == null) && ("target".equals(name))) {
/*  57 */           edge.setTarget(value);
/*  58 */         } else if ((edge.getSourcePort() == null) && ("sourceport".equals(name))) {
/*  59 */           edge.setSourcePort(value);
/*  60 */         } else if ((edge.getTargetPort() == null) && ("targetport".equals(name))) {
/*  61 */           edge.setTargetPort(value);
/*     */         } else {
/*  63 */           edge.setProperty(name, value);
/*     */         }
/*     */       }
/*     */       
/*     */ 
/*  68 */       if ((edge.getSource() == null) || (edge.getTarget() == null)) {
/*  69 */         throw new GraphIOException("Element 'edge' is missing attribute 'source' or 'target'");
/*     */       }
/*     */       
/*     */ 
/*  73 */       while (xmlEventReader.hasNext())
/*     */       {
/*  75 */         XMLEvent event = xmlEventReader.nextEvent();
/*  76 */         if (event.isStartElement()) {
/*  77 */           StartElement element = (StartElement)event;
/*     */           
/*  79 */           String name = element.getName().getLocalPart();
/*  80 */           if ("desc".equals(name)) {
/*  81 */             String desc = (String)getParser(name).parse(xmlEventReader, element);
/*  82 */             edge.setDescription(desc);
/*  83 */           } else if ("data".equals(name)) {
/*  84 */             DataMetadata data = (DataMetadata)getParser(name).parse(xmlEventReader, element);
/*  85 */             edge.addData(data);
/*     */           }
/*     */           else
/*     */           {
/*  89 */             getUnknownParser().parse(xmlEventReader, element);
/*     */           }
/*     */         }
/*     */         
/*  93 */         if (event.isEndElement()) {
/*  94 */           EndElement end = (EndElement)event;
/*  95 */           verifyMatch(start, end);
/*  96 */           break;
/*     */         }
/*     */       }
/*     */       
/*     */ 
/* 101 */       applyKeys(edge);
/*     */       
/* 103 */       return edge;
/*     */     }
/*     */     catch (Exception e) {
/* 106 */       ExceptionConverter.convert(e);
/*     */     }
/*     */     
/* 109 */     return null;
/*     */   }
/*     */ }


/* Location:              /home/menick/Scrivania/ProcessMining/CNMining/CNMining.jar!/edu/uci/ics/jung/io/graphml/parser/EdgeElementParser.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       0.7.1
 */