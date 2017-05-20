/*     */ package edu.uci.ics.jung.io.graphml.parser;
/*     */ 
/*     */ import edu.uci.ics.jung.graph.Hypergraph;
/*     */ import edu.uci.ics.jung.io.GraphIOException;
/*     */ import edu.uci.ics.jung.io.graphml.DataMetadata;
/*     */ import edu.uci.ics.jung.io.graphml.ExceptionConverter;
/*     */ import edu.uci.ics.jung.io.graphml.NodeMetadata;
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
/*     */ public class NodeElementParser<G extends Hypergraph<V, E>, V, E>
/*     */   extends AbstractElementParser<G, V, E>
/*     */ {
/*     */   public NodeElementParser(ParserContext<G, V, E> parserContext)
/*     */   {
/*  33 */     super(parserContext);
/*     */   }
/*     */   
/*     */ 
/*     */   public NodeMetadata parse(XMLEventReader xmlEventReader, StartElement start)
/*     */     throws GraphIOException
/*     */   {
/*     */     try
/*     */     {
/*  42 */       NodeMetadata node = new NodeMetadata();
/*     */       
/*     */ 
/*  45 */       Iterator iterator = start.getAttributes();
/*  46 */       while (iterator.hasNext()) {
/*  47 */         Attribute attribute = (Attribute)iterator.next();
/*  48 */         String name = attribute.getName().getLocalPart();
/*  49 */         String value = attribute.getValue();
/*  50 */         if ((node.getId() == null) && ("id".equals(name))) {
/*  51 */           node.setId(value);
/*     */         } else {
/*  53 */           node.setProperty(name, value);
/*     */         }
/*     */       }
/*     */       
/*     */ 
/*  58 */       if (node.getId() == null) {
/*  59 */         throw new GraphIOException("Element 'node' is missing attribute 'id'");
/*     */       }
/*     */       
/*     */ 
/*  63 */       while (xmlEventReader.hasNext())
/*     */       {
/*  65 */         XMLEvent event = xmlEventReader.nextEvent();
/*  66 */         if (event.isStartElement()) {
/*  67 */           StartElement element = (StartElement)event;
/*     */           
/*  69 */           String name = element.getName().getLocalPart();
/*  70 */           if ("desc".equals(name)) {
/*  71 */             String desc = (String)getParser(name).parse(xmlEventReader, element);
/*  72 */             node.setDescription(desc);
/*  73 */           } else if ("data".equals(name)) {
/*  74 */             DataMetadata data = (DataMetadata)getParser(name).parse(xmlEventReader, element);
/*  75 */             node.addData(data);
/*  76 */           } else if ("port".equals(name)) {
/*  77 */             PortMetadata port = (PortMetadata)getParser(name).parse(xmlEventReader, element);
/*  78 */             node.addPort(port);
/*     */           }
/*     */           else
/*     */           {
/*  82 */             getUnknownParser().parse(xmlEventReader, element);
/*     */           }
/*     */         }
/*  85 */         else if (event.isEndElement()) {
/*  86 */           EndElement end = (EndElement)event;
/*  87 */           verifyMatch(start, end);
/*  88 */           break;
/*     */         }
/*     */       }
/*     */       
/*     */ 
/*  93 */       applyKeys(node);
/*     */       
/*  95 */       return node;
/*     */     }
/*     */     catch (Exception e) {
/*  98 */       ExceptionConverter.convert(e);
/*     */     }
/*     */     
/* 101 */     return null;
/*     */   }
/*     */ }


/* Location:              /home/menick/Scrivania/ProcessMining/CNMining/CNMining.jar!/edu/uci/ics/jung/io/graphml/parser/NodeElementParser.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       0.7.1
 */