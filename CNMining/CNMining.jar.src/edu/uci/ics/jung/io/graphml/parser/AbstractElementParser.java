/*    */ package edu.uci.ics.jung.io.graphml.parser;
/*    */ 
/*    */ import edu.uci.ics.jung.graph.Hypergraph;
/*    */ import edu.uci.ics.jung.io.GraphIOException;
/*    */ import edu.uci.ics.jung.io.graphml.KeyMap;
/*    */ import edu.uci.ics.jung.io.graphml.Metadata;
/*    */ import javax.xml.namespace.QName;
/*    */ import javax.xml.stream.events.EndElement;
/*    */ import javax.xml.stream.events.StartElement;
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
/*    */ public abstract class AbstractElementParser<G extends Hypergraph<V, E>, V, E>
/*    */   implements ElementParser
/*    */ {
/*    */   private final ParserContext<G, V, E> parserContext;
/*    */   
/*    */   protected AbstractElementParser(ParserContext<G, V, E> parserContext)
/*    */   {
/* 29 */     this.parserContext = parserContext;
/*    */   }
/*    */   
/*    */   public ParserContext<G, V, E> getParserContext() {
/* 33 */     return this.parserContext;
/*    */   }
/*    */   
/*    */   public ElementParser getParser(String localName) {
/* 37 */     return this.parserContext.getElementParserRegistry().getParser(localName);
/*    */   }
/*    */   
/*    */   public void applyKeys(Metadata metadata) {
/* 41 */     getParserContext().getKeyMap().applyKeys(metadata);
/*    */   }
/*    */   
/*    */   public ElementParser getUnknownParser() {
/* 45 */     return this.parserContext.getElementParserRegistry().getUnknownElementParser();
/*    */   }
/*    */   
/*    */   protected void verifyMatch(StartElement start, EndElement end)
/*    */     throws GraphIOException
/*    */   {
/* 51 */     String startName = start.getName().getLocalPart();
/* 52 */     String endName = end.getName().getLocalPart();
/* 53 */     if (!startName.equals(endName)) {
/* 54 */       throw new GraphIOException("Failed parsing document: Start/end tag mismatch! StartTag:" + startName + ", EndTag: " + endName);
/*    */     }
/*    */   }
/*    */ }


/* Location:              /home/menick/Scrivania/ProcessMining/CNMining/CNMining.jar!/edu/uci/ics/jung/io/graphml/parser/AbstractElementParser.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       0.7.1
 */