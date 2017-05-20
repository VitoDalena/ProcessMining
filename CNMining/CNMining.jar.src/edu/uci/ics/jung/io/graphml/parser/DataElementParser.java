/*    */ package edu.uci.ics.jung.io.graphml.parser;
/*    */ 
/*    */ import edu.uci.ics.jung.graph.Hypergraph;
/*    */ import edu.uci.ics.jung.io.GraphIOException;
/*    */ import edu.uci.ics.jung.io.graphml.DataMetadata;
/*    */ import edu.uci.ics.jung.io.graphml.ExceptionConverter;
/*    */ import java.util.Iterator;
/*    */ import javax.xml.namespace.QName;
/*    */ import javax.xml.stream.XMLEventReader;
/*    */ import javax.xml.stream.events.Attribute;
/*    */ import javax.xml.stream.events.Characters;
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
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class DataElementParser<G extends Hypergraph<V, E>, V, E>
/*    */   extends AbstractElementParser<G, V, E>
/*    */ {
/*    */   public DataElementParser(ParserContext<G, V, E> parserContext)
/*    */   {
/* 36 */     super(parserContext);
/*    */   }
/*    */   
/*    */ 
/*    */   public DataMetadata parse(XMLEventReader xmlEventReader, StartElement start)
/*    */     throws GraphIOException
/*    */   {
/*    */     try
/*    */     {
/* 45 */       DataMetadata data = new DataMetadata();
/*    */       
/*    */ 
/* 48 */       Iterator iterator = start.getAttributes();
/* 49 */       while (iterator.hasNext()) {
/* 50 */         Attribute attribute = (Attribute)iterator.next();
/* 51 */         String name = attribute.getName().getLocalPart();
/* 52 */         String value = attribute.getValue();
/* 53 */         if ((data.getKey() == null) && ("key".equals(name))) {
/* 54 */           data.setKey(value);
/*    */         }
/*    */       }
/*    */       
/*    */ 
/* 59 */       if (data.getKey() == null) {
/* 60 */         throw new GraphIOException("Element 'data' is missing attribute 'key'");
/*    */       }
/*    */       
/*    */ 
/* 64 */       while (xmlEventReader.hasNext())
/*    */       {
/* 66 */         XMLEvent event = xmlEventReader.nextEvent();
/* 67 */         if (event.isStartElement()) {
/* 68 */           StartElement element = (StartElement)event;
/*    */           
/*    */ 
/* 71 */           getUnknownParser().parse(xmlEventReader, element);
/*    */         }
/* 73 */         if (event.isCharacters()) {
/* 74 */           Characters characters = (Characters)event;
/* 75 */           data.setValue(characters.getData());
/*    */         }
/* 77 */         if (event.isEndElement()) {
/* 78 */           EndElement end = (EndElement)event;
/* 79 */           verifyMatch(start, end);
/* 80 */           break;
/*    */         }
/*    */       }
/*    */       
/* 84 */       return data;
/*    */     }
/*    */     catch (Exception e) {
/* 87 */       ExceptionConverter.convert(e);
/*    */     }
/*    */     
/* 90 */     return null;
/*    */   }
/*    */ }


/* Location:              /home/menick/Scrivania/ProcessMining/CNMining/CNMining.jar!/edu/uci/ics/jung/io/graphml/parser/DataElementParser.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       0.7.1
 */