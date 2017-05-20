/*    */ package edu.uci.ics.jung.io.graphml.parser;
/*    */ 
/*    */ import edu.uci.ics.jung.graph.Hypergraph;
/*    */ import edu.uci.ics.jung.io.graphml.EdgeMetadata;
/*    */ import edu.uci.ics.jung.io.graphml.GraphMetadata;
/*    */ import edu.uci.ics.jung.io.graphml.HyperEdgeMetadata;
/*    */ import edu.uci.ics.jung.io.graphml.KeyMap;
/*    */ import edu.uci.ics.jung.io.graphml.NodeMetadata;
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
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class ParserContext<G extends Hypergraph<V, E>, V, E>
/*    */ {
/*    */   private final KeyMap keyMap;
/*    */   private final ElementParserRegistry<G, V, E> elementParserRegistry;
/*    */   private final Transformer<GraphMetadata, G> graphTransformer;
/*    */   private final Transformer<NodeMetadata, V> vertexTransformer;
/*    */   private final Transformer<EdgeMetadata, E> edgeTransformer;
/*    */   private final Transformer<HyperEdgeMetadata, E> hyperEdgeTransformer;
/*    */   
/*    */   public ParserContext(ElementParserRegistry<G, V, E> elementParserRegistry, KeyMap keyMap, Transformer<GraphMetadata, G> graphTransformer, Transformer<NodeMetadata, V> vertexTransformer, Transformer<EdgeMetadata, E> edgeTransformer, Transformer<HyperEdgeMetadata, E> hyperEdgeTransformer)
/*    */   {
/* 46 */     this.elementParserRegistry = elementParserRegistry;
/* 47 */     this.keyMap = keyMap;
/* 48 */     this.graphTransformer = graphTransformer;
/* 49 */     this.vertexTransformer = vertexTransformer;
/* 50 */     this.edgeTransformer = edgeTransformer;
/* 51 */     this.hyperEdgeTransformer = hyperEdgeTransformer;
/*    */   }
/*    */   
/*    */   public ElementParserRegistry<G, V, E> getElementParserRegistry() {
/* 55 */     return this.elementParserRegistry;
/*    */   }
/*    */   
/*    */   public KeyMap getKeyMap() {
/* 59 */     return this.keyMap;
/*    */   }
/*    */   
/*    */   public G createGraph(GraphMetadata metadata) {
/* 63 */     return (Hypergraph)this.graphTransformer.transform(metadata);
/*    */   }
/*    */   
/*    */   public V createVertex(NodeMetadata metadata) {
/* 67 */     return (V)this.vertexTransformer.transform(metadata);
/*    */   }
/*    */   
/*    */   public E createEdge(EdgeMetadata metadata) {
/* 71 */     return (E)this.edgeTransformer.transform(metadata);
/*    */   }
/*    */   
/*    */   public E createHyperEdge(HyperEdgeMetadata metadata) {
/* 75 */     return (E)this.hyperEdgeTransformer.transform(metadata);
/*    */   }
/*    */ }


/* Location:              /home/menick/Scrivania/ProcessMining/CNMining/CNMining.jar!/edu/uci/ics/jung/io/graphml/parser/ParserContext.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       0.7.1
 */