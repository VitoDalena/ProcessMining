/*    */ package edu.uci.ics.jung.io.graphml.parser;
/*    */ 
/*    */ import edu.uci.ics.jung.graph.Hypergraph;
/*    */ import edu.uci.ics.jung.io.GraphIOException;
/*    */ import edu.uci.ics.jung.io.graphml.ExceptionConverter;
/*    */ import javax.xml.stream.XMLEventReader;
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
/*    */ public class StringElementParser<G extends Hypergraph<V, E>, V, E>
/*    */   extends AbstractElementParser<G, V, E>
/*    */ {
/*    */   public StringElementParser(ParserContext<G, V, E> parserContext)
/*    */   {
/* 31 */     super(parserContext);
/*    */   }
/*    */   
/*    */   public String parse(XMLEventReader xmlEventReader, StartElement start) throws GraphIOException
/*    */   {
/*    */     try
/*    */     {
/* 38 */       String str = null;
/*    */       
/* 40 */       while (xmlEventReader.hasNext())
/*    */       {
/* 42 */         XMLEvent event = xmlEventReader.nextEvent();
/* 43 */         if (event.isStartElement())
/*    */         {
/*    */ 
/* 46 */           getUnknownParser().parse(xmlEventReader, event.asStartElement());
/*    */         } else {
/* 48 */           if (event.isEndElement()) {
/* 49 */             EndElement end = (EndElement)event;
/* 50 */             verifyMatch(start, end);
/* 51 */             break; }
/* 52 */           if (event.isCharacters()) {
/* 53 */             Characters characters = (Characters)event;
/* 54 */             str = characters.getData();
/*    */           }
/*    */         }
/*    */       }
/* 58 */       return str;
/*    */     }
/*    */     catch (Exception e) {
/* 61 */       ExceptionConverter.convert(e);
/*    */     }
/*    */     
/* 64 */     return null;
/*    */   }
/*    */ }


/* Location:              /home/menick/Scrivania/ProcessMining/CNMining/CNMining.jar!/edu/uci/ics/jung/io/graphml/parser/StringElementParser.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       0.7.1
 */