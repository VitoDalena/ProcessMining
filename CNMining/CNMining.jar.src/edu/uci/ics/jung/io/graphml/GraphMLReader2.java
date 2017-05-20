/*     */ package edu.uci.ics.jung.io.graphml;
/*     */ 
/*     */ import edu.uci.ics.jung.graph.Hypergraph;
/*     */ import edu.uci.ics.jung.io.GraphIOException;
/*     */ import edu.uci.ics.jung.io.GraphReader;
/*     */ import edu.uci.ics.jung.io.graphml.parser.ElementParser;
/*     */ import edu.uci.ics.jung.io.graphml.parser.ElementParserRegistry;
/*     */ import edu.uci.ics.jung.io.graphml.parser.GraphMLEventFilter;
/*     */ import java.io.IOException;
/*     */ import java.io.Reader;
/*     */ import java.util.List;
/*     */ import javax.xml.namespace.QName;
/*     */ import javax.xml.stream.XMLEventReader;
/*     */ import javax.xml.stream.XMLInputFactory;
/*     */ import javax.xml.stream.XMLStreamException;
/*     */ import javax.xml.stream.events.StartElement;
/*     */ import javax.xml.stream.events.XMLEvent;
/*     */ import org.apache.commons.collections15.Transformer;
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
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class GraphMLReader2<G extends Hypergraph<V, E>, V, E>
/*     */   implements GraphReader<G, V, E>
/*     */ {
/*     */   protected XMLEventReader xmlEventReader;
/*     */   protected Reader fileReader;
/*     */   protected Transformer<GraphMetadata, G> graphTransformer;
/*     */   protected Transformer<NodeMetadata, V> vertexTransformer;
/*     */   protected Transformer<EdgeMetadata, E> edgeTransformer;
/*     */   protected Transformer<HyperEdgeMetadata, E> hyperEdgeTransformer;
/*     */   protected boolean initialized;
/*  62 */   protected final GraphMLDocument document = new GraphMLDocument();
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
/*     */   protected final ElementParserRegistry<G, V, E> parserRegistry;
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
/*     */   public GraphMLReader2(Reader fileReader, Transformer<GraphMetadata, G> graphTransformer, Transformer<NodeMetadata, V> vertexTransformer, Transformer<EdgeMetadata, E> edgeTransformer, Transformer<HyperEdgeMetadata, E> hyperEdgeTransformer)
/*     */   {
/*  90 */     if (fileReader == null) {
/*  91 */       throw new IllegalArgumentException("Argument fileReader must be non-null");
/*     */     }
/*     */     
/*     */ 
/*  95 */     if (graphTransformer == null) {
/*  96 */       throw new IllegalArgumentException("Argument graphTransformer must be non-null");
/*     */     }
/*     */     
/*     */ 
/* 100 */     if (vertexTransformer == null) {
/* 101 */       throw new IllegalArgumentException("Argument vertexTransformer must be non-null");
/*     */     }
/*     */     
/*     */ 
/* 105 */     if (edgeTransformer == null) {
/* 106 */       throw new IllegalArgumentException("Argument edgeTransformer must be non-null");
/*     */     }
/*     */     
/*     */ 
/* 110 */     if (hyperEdgeTransformer == null) {
/* 111 */       throw new IllegalArgumentException("Argument hyperEdgeTransformer must be non-null");
/*     */     }
/*     */     
/*     */ 
/* 115 */     this.fileReader = fileReader;
/* 116 */     this.graphTransformer = graphTransformer;
/* 117 */     this.vertexTransformer = vertexTransformer;
/* 118 */     this.edgeTransformer = edgeTransformer;
/* 119 */     this.hyperEdgeTransformer = hyperEdgeTransformer;
/*     */     
/*     */ 
/* 122 */     this.parserRegistry = new ElementParserRegistry(this.document.getKeyMap(), graphTransformer, vertexTransformer, edgeTransformer, hyperEdgeTransformer);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public Transformer<GraphMetadata, G> getGraphTransformer()
/*     */   {
/* 132 */     return this.graphTransformer;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public Transformer<NodeMetadata, V> getVertexTransformer()
/*     */   {
/* 141 */     return this.vertexTransformer;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public Transformer<EdgeMetadata, E> getEdgeTransformer()
/*     */   {
/* 150 */     return this.edgeTransformer;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public Transformer<HyperEdgeMetadata, E> getHyperEdgeTransformer()
/*     */   {
/* 159 */     return this.hyperEdgeTransformer;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public void init()
/*     */     throws GraphIOException
/*     */   {
/*     */     try
/*     */     {
/* 174 */       if (!this.initialized)
/*     */       {
/*     */ 
/* 177 */         XMLInputFactory factory = XMLInputFactory.newInstance();
/* 178 */         this.xmlEventReader = factory.createXMLEventReader(this.fileReader);
/* 179 */         this.xmlEventReader = factory.createFilteredReader(this.xmlEventReader, new GraphMLEventFilter());
/*     */         
/*     */ 
/* 182 */         this.initialized = true;
/*     */       }
/*     */     }
/*     */     catch (Exception e) {
/* 186 */       ExceptionConverter.convert(e);
/*     */     }
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public void close()
/*     */     throws GraphIOException
/*     */   {
/*     */     try
/*     */     {
/* 199 */       this.document.clear();
/*     */       
/* 201 */       if (this.xmlEventReader != null) {
/* 202 */         this.xmlEventReader.close();
/*     */       }
/*     */       
/* 205 */       if (this.fileReader != null) {
/* 206 */         this.fileReader.close();
/*     */       }
/*     */     }
/*     */     catch (IOException e) {
/* 210 */       throw new GraphIOException(e);
/*     */     } catch (XMLStreamException e) {
/* 212 */       throw new GraphIOException(e);
/*     */     } finally {
/* 214 */       this.fileReader = null;
/* 215 */       this.xmlEventReader = null;
/* 216 */       this.graphTransformer = null;
/* 217 */       this.vertexTransformer = null;
/* 218 */       this.edgeTransformer = null;
/* 219 */       this.hyperEdgeTransformer = null;
/*     */     }
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public GraphMLDocument getGraphMLDocument()
/*     */   {
/* 230 */     return this.document;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public G readGraph()
/*     */     throws GraphIOException
/*     */   {
/*     */     try
/*     */     {
/* 245 */       init();
/*     */       
/* 247 */       while (this.xmlEventReader.hasNext())
/*     */       {
/* 249 */         XMLEvent event = this.xmlEventReader.nextEvent();
/* 250 */         if (event.isStartElement()) {
/* 251 */           StartElement element = (StartElement)event;
/* 252 */           String name = element.getName().getLocalPart();
/*     */           
/*     */ 
/* 255 */           if ("key".equals(name))
/*     */           {
/*     */ 
/* 258 */             Key key = (Key)this.parserRegistry.getParser(name).parse(this.xmlEventReader, element);
/*     */             
/*     */ 
/*     */ 
/* 262 */             this.document.getKeyMap().addKey(key);
/*     */           } else {
/* 264 */             if ("graph".equals(name))
/*     */             {
/*     */ 
/* 267 */               GraphMetadata graph = (GraphMetadata)this.parserRegistry.getParser(name).parse(this.xmlEventReader, element);
/*     */               
/*     */ 
/*     */ 
/* 271 */               this.document.getGraphMetadata().add(graph);
/*     */               
/*     */ 
/* 274 */               return (Hypergraph)graph.getGraph();
/*     */             }
/* 276 */             if (!"graphml".equals(name))
/*     */             {
/*     */ 
/*     */ 
/*     */ 
/* 281 */               this.parserRegistry.getUnknownElementParser().parse(this.xmlEventReader, element);
/*     */             }
/*     */           }
/*     */         } else {
/* 285 */           if (event.isEndDocument()) {
/*     */             break;
/*     */           }
/*     */         }
/*     */       }
/*     */     } catch (Exception e) {
/* 291 */       ExceptionConverter.convert(e);
/*     */     }
/*     */     
/*     */ 
/* 295 */     throw new GraphIOException("Unable to read Graph from document - the document could be empty");
/*     */   }
/*     */ }


/* Location:              /home/menick/Scrivania/ProcessMining/CNMining/CNMining.jar!/edu/uci/ics/jung/io/graphml/GraphMLReader2.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       0.7.1
 */