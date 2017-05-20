/*    */ package edu.uci.ics.jung.io.graphml.parser;
/*    */ 
/*    */ import edu.uci.ics.jung.graph.Hypergraph;
/*    */ import edu.uci.ics.jung.io.GraphIOException;
/*    */ import edu.uci.ics.jung.io.graphml.DataMetadata;
/*    */ import edu.uci.ics.jung.io.graphml.EndpointMetadata;
/*    */ import edu.uci.ics.jung.io.graphml.ExceptionConverter;
/*    */ import edu.uci.ics.jung.io.graphml.HyperEdgeMetadata;
/*    */ import java.util.Iterator;
/*    */ import javax.xml.namespace.QName;
/*    */ import javax.xml.stream.XMLEventReader;
/*    */ import javax.xml.stream.events.Attribute;
/*    */ import javax.xml.stream.events.EndElement;
/*    */ import javax.xml.stream.events.StartElement;
/*    */ import javax.xml.stream.events.XMLEvent;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class HyperEdgeElementParser<G extends Hypergraph<V, E>, V, E>
/*    */   extends AbstractElementParser<G, V, E>
/*    */ {
/*    */   public HyperEdgeElementParser(ParserContext<G, V, E> parserContext)
/*    */   {
/* 33 */     super(parserContext);
/*    */   }
/*    */   
/*    */ 
/*    */   public HyperEdgeMetadata parse(XMLEventReader xmlEventReader, StartElement start)
/*    */     throws GraphIOException
/*    */   {
/*    */     try
/*    */     {
/* 42 */       HyperEdgeMetadata edge = new HyperEdgeMetadata();
/*    */       
/*    */ 
/* 45 */       Iterator iterator = start.getAttributes();
/* 46 */       while (iterator.hasNext()) {
/* 47 */         Attribute attribute = (Attribute)iterator.next();
/* 48 */         String name = attribute.getName().getLocalPart();
/* 49 */         String value = attribute.getValue();
/* 50 */         if ((edge.getId() == null) && ("id".equals(name))) {
/* 51 */           edge.setId(value);
/*    */         } else {
/* 53 */           edge.setProperty(name, value);
/*    */         }
/*    */       }
/*    */       
/* 57 */       while (xmlEventReader.hasNext())
/*    */       {
/* 59 */         XMLEvent event = xmlEventReader.nextEvent();
/* 60 */         if (event.isStartElement()) {
/* 61 */           StartElement element = (StartElement)event;
/*    */           
/* 63 */           String name = element.getName().getLocalPart();
/* 64 */           if ("desc".equals(name)) {
/* 65 */             String desc = (String)getParser(name).parse(xmlEventReader, element);
/* 66 */             edge.setDescription(desc);
/* 67 */           } else if ("data".equals(name)) {
/* 68 */             DataMetadata data = (DataMetadata)getParser(name).parse(xmlEventReader, element);
/* 69 */             edge.addData(data);
/* 70 */           } else if ("endpoint".equals(name)) {
/* 71 */             EndpointMetadata ep = (EndpointMetadata)getParser(name).parse(xmlEventReader, element);
/* 72 */             edge.addEndpoint(ep);
/*    */           }
/*    */           else
/*    */           {
/* 76 */             getUnknownParser().parse(xmlEventReader, element);
/*    */           }
/*    */         }
/*    */         
/* 80 */         if (event.isEndElement()) {
/* 81 */           EndElement end = (EndElement)event;
/* 82 */           verifyMatch(start, end);
/* 83 */           break;
/*    */         }
/*    */       }
/*    */       
/*    */ 
/* 88 */       applyKeys(edge);
/*    */       
/* 90 */       return edge;
/*    */     }
/*    */     catch (Exception e) {
/* 93 */       ExceptionConverter.convert(e);
/*    */     }
/*    */     
/* 96 */     return null;
/*    */   }
/*    */ }


/* Location:              /home/menick/Scrivania/ProcessMining/CNMining/CNMining.jar!/edu/uci/ics/jung/io/graphml/parser/HyperEdgeElementParser.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       0.7.1
 */