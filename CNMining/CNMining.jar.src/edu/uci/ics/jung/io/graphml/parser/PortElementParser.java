/*     */ package edu.uci.ics.jung.io.graphml.parser;
/*     */ 
/*     */ import edu.uci.ics.jung.graph.Hypergraph;
/*     */ import edu.uci.ics.jung.io.GraphIOException;
/*     */ import edu.uci.ics.jung.io.graphml.DataMetadata;
/*     */ import edu.uci.ics.jung.io.graphml.ExceptionConverter;
/*     */ import edu.uci.ics.jung.io.graphml.PortMetadata;
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
/*     */ public class PortElementParser<G extends Hypergraph<V, E>, V, E>
/*     */   extends AbstractElementParser<G, V, E>
/*     */ {
/*     */   public PortElementParser(ParserContext<G, V, E> parserContext)
/*     */   {
/*  33 */     super(parserContext);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */   public PortMetadata parse(XMLEventReader xmlEventReader, StartElement start)
/*     */     throws GraphIOException
/*     */   {
/*     */     try
/*     */     {
/*  43 */       PortMetadata port = new PortMetadata();
/*     */       
/*     */ 
/*  46 */       Iterator iterator = start.getAttributes();
/*  47 */       while (iterator.hasNext()) {
/*  48 */         Attribute attribute = (Attribute)iterator.next();
/*  49 */         String name = attribute.getName().getLocalPart();
/*  50 */         String value = attribute.getValue();
/*  51 */         if ((port.getName() == null) && ("name".equals(name))) {
/*  52 */           port.setName(value);
/*     */         } else {
/*  54 */           port.setProperty(name, value);
/*     */         }
/*     */       }
/*     */       
/*     */ 
/*  59 */       if (port.getName() == null) {
/*  60 */         throw new GraphIOException("Element 'port' is missing attribute 'name'");
/*     */       }
/*     */       
/*     */ 
/*  64 */       while (xmlEventReader.hasNext())
/*     */       {
/*  66 */         XMLEvent event = xmlEventReader.nextEvent();
/*  67 */         if (event.isStartElement()) {
/*  68 */           StartElement element = (StartElement)event;
/*     */           
/*  70 */           String name = element.getName().getLocalPart();
/*  71 */           if ("desc".equals(name)) {
/*  72 */             String desc = (String)getParser(name).parse(xmlEventReader, element);
/*  73 */             port.setDescription(desc);
/*  74 */           } else if ("data".equals(name)) {
/*  75 */             DataMetadata data = (DataMetadata)getParser(name).parse(xmlEventReader, element);
/*  76 */             port.addData(data);
/*     */           }
/*     */           else
/*     */           {
/*  80 */             getUnknownParser().parse(xmlEventReader, element);
/*     */           }
/*     */         }
/*     */         
/*  84 */         if (event.isEndElement()) {
/*  85 */           EndElement end = (EndElement)event;
/*  86 */           verifyMatch(start, end);
/*  87 */           break;
/*     */         }
/*     */       }
/*     */       
/*     */ 
/*  92 */       applyKeys(port);
/*     */       
/*  94 */       return port;
/*     */     }
/*     */     catch (Exception e) {
/*  97 */       ExceptionConverter.convert(e);
/*     */     }
/*     */     
/* 100 */     return null;
/*     */   }
/*     */ }


/* Location:              /home/menick/Scrivania/ProcessMining/CNMining/CNMining.jar!/edu/uci/ics/jung/io/graphml/parser/PortElementParser.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       0.7.1
 */