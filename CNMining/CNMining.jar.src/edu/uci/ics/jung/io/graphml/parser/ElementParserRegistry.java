/*    */ package edu.uci.ics.jung.io.graphml.parser;
/*    */ 
/*    */ import edu.uci.ics.jung.graph.Hypergraph;
/*    */ import edu.uci.ics.jung.io.graphml.EdgeMetadata;
/*    */ import edu.uci.ics.jung.io.graphml.GraphMetadata;
/*    */ import edu.uci.ics.jung.io.graphml.HyperEdgeMetadata;
/*    */ import edu.uci.ics.jung.io.graphml.KeyMap;
/*    */ import edu.uci.ics.jung.io.graphml.NodeMetadata;
/*    */ import java.util.HashMap;
/*    */ import java.util.Map;
/*    */ import org.apache.commons.collections15.Transformer;
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
/*    */ public class ElementParserRegistry<G extends Hypergraph<V, E>, V, E>
/*    */ {
/* 33 */   private final Map<String, ElementParser> parserMap = new HashMap();
/*    */   
/* 35 */   private final ElementParser unknownElementParser = new UnknownElementParser();
/*    */   
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   public ElementParserRegistry(KeyMap keyMap, Transformer<GraphMetadata, G> graphTransformer, Transformer<NodeMetadata, V> vertexTransformer, Transformer<EdgeMetadata, E> edgeTransformer, Transformer<HyperEdgeMetadata, E> hyperEdgeTransformer)
/*    */   {
/* 44 */     ParserContext<G, V, E> context = new ParserContext(this, keyMap, graphTransformer, vertexTransformer, edgeTransformer, hyperEdgeTransformer);
/*    */     
/*    */ 
/* 47 */     this.parserMap.put("default", new StringElementParser(context));
/* 48 */     this.parserMap.put("desc", new StringElementParser(context));
/* 49 */     this.parserMap.put("key", new KeyElementParser(context));
/* 50 */     this.parserMap.put("data", new DataElementParser(context));
/* 51 */     this.parserMap.put("port", new PortElementParser(context));
/* 52 */     this.parserMap.put("node", new NodeElementParser(context));
/* 53 */     this.parserMap.put("graph", new GraphElementParser(context));
/* 54 */     this.parserMap.put("endpoint", new EndpointElementParser(context));
/* 55 */     this.parserMap.put("edge", new EdgeElementParser(context));
/* 56 */     this.parserMap.put("hyperedge", new HyperEdgeElementParser(context));
/*    */   }
/*    */   
/*    */   public ElementParser getUnknownElementParser() {
/* 60 */     return this.unknownElementParser;
/*    */   }
/*    */   
/*    */   public ElementParser getParser(String localName) {
/* 64 */     ElementParser parser = (ElementParser)this.parserMap.get(localName);
/* 65 */     if (parser == null) {
/* 66 */       parser = this.unknownElementParser;
/*    */     }
/*    */     
/* 69 */     return parser;
/*    */   }
/*    */ }


/* Location:              /home/menick/Scrivania/ProcessMining/CNMining/CNMining.jar!/edu/uci/ics/jung/io/graphml/parser/ElementParserRegistry.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       0.7.1
 */