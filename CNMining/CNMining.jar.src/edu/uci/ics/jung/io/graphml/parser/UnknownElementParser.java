/*    */ package edu.uci.ics.jung.io.graphml.parser;
/*    */ 
/*    */ import edu.uci.ics.jung.io.GraphIOException;
/*    */ import edu.uci.ics.jung.io.graphml.ExceptionConverter;
/*    */ import java.util.Stack;
/*    */ import javax.xml.namespace.QName;
/*    */ import javax.xml.stream.XMLEventReader;
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
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class UnknownElementParser
/*    */   implements ElementParser
/*    */ {
/*    */   public Object parse(XMLEventReader xmlEventReader, StartElement start)
/*    */     throws GraphIOException
/*    */   {
/*    */     try
/*    */     {
/* 42 */       Stack<String> skippedElements = new Stack();
/* 43 */       skippedElements.add(start.getName().getLocalPart());
/*    */       
/* 45 */       while (xmlEventReader.hasNext())
/*    */       {
/* 47 */         XMLEvent event = xmlEventReader.nextEvent();
/* 48 */         if (event.isStartElement())
/*    */         {
/* 50 */           String name = event.asStartElement().getName().getLocalPart();
/*    */           
/*    */ 
/*    */ 
/* 54 */           skippedElements.push(name);
/*    */         }
/* 56 */         if (event.isEndElement())
/*    */         {
/* 58 */           String name = event.asEndElement().getName().getLocalPart();
/*    */           
/*    */ 
/* 61 */           if ((skippedElements.size() == 0) || (!((String)skippedElements.peek()).equals(name)))
/*    */           {
/* 63 */             throw new GraphIOException("Failed parsing GraphML document - startTag/endTag mismatch");
/*    */           }
/*    */           
/*    */ 
/*    */ 
/* 68 */           skippedElements.pop();
/* 69 */           if (skippedElements.isEmpty()) {
/*    */             break;
/*    */           }
/*    */         }
/*    */       }
/*    */       
/* 75 */       return null;
/*    */     }
/*    */     catch (Exception e) {
/* 78 */       ExceptionConverter.convert(e);
/*    */     }
/*    */     
/* 81 */     return null;
/*    */   }
/*    */ }


/* Location:              /home/menick/Scrivania/ProcessMining/CNMining/CNMining.jar!/edu/uci/ics/jung/io/graphml/parser/UnknownElementParser.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       0.7.1
 */