/*     */ package edu.uci.ics.jung.algorithms.flows;
/*     */ 
/*     */ import edu.uci.ics.jung.algorithms.util.IterativeProcess;
/*     */ import edu.uci.ics.jung.graph.DirectedGraph;
/*     */ import edu.uci.ics.jung.graph.util.EdgeType;
/*     */ import java.util.ArrayList;
/*     */ import java.util.Collection;
/*     */ import java.util.HashMap;
/*     */ import java.util.HashSet;
/*     */ import java.util.List;
/*     */ import java.util.Map;
/*     */ import java.util.Set;
/*     */ import org.apache.commons.collections15.Buffer;
/*     */ import org.apache.commons.collections15.Factory;
/*     */ import org.apache.commons.collections15.Transformer;
/*     */ import org.apache.commons.collections15.buffer.UnboundedFifoBuffer;
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
/*     */ public class EdmondsKarpMaxFlow<V, E>
/*     */   extends IterativeProcess
/*     */ {
/*     */   private DirectedGraph<V, E> mFlowGraph;
/*     */   private DirectedGraph<V, E> mOriginalGraph;
/*     */   private V source;
/*     */   private V target;
/*     */   private int mMaxFlow;
/*     */   private Set<V> mSourcePartitionNodes;
/*     */   private Set<V> mSinkPartitionNodes;
/*     */   private Set<E> mMinCutEdges;
/*  59 */   private Map<E, Number> residualCapacityMap = new HashMap();
/*  60 */   private Map<V, V> parentMap = new HashMap();
/*  61 */   private Map<V, Number> parentCapacityMap = new HashMap();
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   private Transformer<E, Number> edgeCapacityTransformer;
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   private Map<E, Number> edgeFlowMap;
/*     */   
/*     */ 
/*     */ 
/*     */   private Factory<E> edgeFactory;
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   public EdmondsKarpMaxFlow(DirectedGraph<V, E> directedGraph, V source, V sink, Transformer<E, Number> edgeCapacityTransformer, Map<E, Number> edgeFlowMap, Factory<E> edgeFactory)
/*     */   {
/*  82 */     if ((!directedGraph.getVertices().contains(source)) || (!directedGraph.getVertices().contains(sink)))
/*     */     {
/*  84 */       throw new IllegalArgumentException("source and sink vertices must be elements of the specified graph");
/*     */     }
/*  86 */     if (source.equals(sink)) {
/*  87 */       throw new IllegalArgumentException("source and sink vertices must be distinct");
/*     */     }
/*  89 */     this.mOriginalGraph = directedGraph;
/*     */     
/*  91 */     this.source = source;
/*  92 */     this.target = sink;
/*  93 */     this.edgeFlowMap = edgeFlowMap;
/*  94 */     this.edgeCapacityTransformer = edgeCapacityTransformer;
/*  95 */     this.edgeFactory = edgeFactory;
/*     */     try {
/*  97 */       this.mFlowGraph = ((DirectedGraph)directedGraph.getClass().newInstance());
/*  98 */       for (E e : this.mOriginalGraph.getEdges()) {
/*  99 */         this.mFlowGraph.addEdge(e, this.mOriginalGraph.getSource(e), this.mOriginalGraph.getDest(e), EdgeType.DIRECTED);
/*     */       }
/*     */       
/* 102 */       for (V v : this.mOriginalGraph.getVertices()) {
/* 103 */         this.mFlowGraph.addVertex(v);
/*     */       }
/*     */     }
/*     */     catch (InstantiationException e) {
/* 107 */       e.printStackTrace();
/*     */     } catch (IllegalAccessException e) {
/* 109 */       e.printStackTrace();
/*     */     }
/* 111 */     this.mMaxFlow = 0;
/* 112 */     this.mSinkPartitionNodes = new HashSet();
/* 113 */     this.mSourcePartitionNodes = new HashSet();
/* 114 */     this.mMinCutEdges = new HashSet();
/*     */   }
/*     */   
/*     */   private void clearParentValues() {
/* 118 */     this.parentMap.clear();
/* 119 */     this.parentCapacityMap.clear();
/* 120 */     this.parentCapacityMap.put(this.source, Integer.valueOf(Integer.MAX_VALUE));
/* 121 */     this.parentMap.put(this.source, this.source);
/*     */   }
/*     */   
/*     */   protected boolean hasAugmentingPath()
/*     */   {
/* 126 */     this.mSinkPartitionNodes.clear();
/* 127 */     this.mSourcePartitionNodes.clear();
/* 128 */     this.mSinkPartitionNodes.addAll(this.mFlowGraph.getVertices());
/*     */     
/* 130 */     Set<E> visitedEdgesMap = new HashSet();
/* 131 */     Buffer<V> queue = new UnboundedFifoBuffer();
/* 132 */     queue.add(this.source);
/*     */     V currentVertex;
/* 134 */     Number currentCapacity; while (!queue.isEmpty()) {
/* 135 */       currentVertex = queue.remove();
/* 136 */       this.mSinkPartitionNodes.remove(currentVertex);
/* 137 */       this.mSourcePartitionNodes.add(currentVertex);
/* 138 */       currentCapacity = (Number)this.parentCapacityMap.get(currentVertex);
/*     */       
/* 140 */       Collection<E> neighboringEdges = this.mFlowGraph.getOutEdges(currentVertex);
/*     */       
/* 142 */       for (E neighboringEdge : neighboringEdges)
/*     */       {
/* 144 */         V neighboringVertex = this.mFlowGraph.getDest(neighboringEdge);
/*     */         
/* 146 */         Number residualCapacity = (Number)this.residualCapacityMap.get(neighboringEdge);
/* 147 */         if ((residualCapacity.intValue() > 0) && (!visitedEdgesMap.contains(neighboringEdge)))
/*     */         {
/*     */ 
/* 150 */           V neighborsParent = this.parentMap.get(neighboringVertex);
/* 151 */           Number neighborCapacity = (Number)this.parentCapacityMap.get(neighboringVertex);
/* 152 */           int newCapacity = Math.min(residualCapacity.intValue(), currentCapacity.intValue());
/*     */           
/* 154 */           if ((neighborsParent == null) || (newCapacity > neighborCapacity.intValue())) {
/* 155 */             this.parentMap.put(neighboringVertex, currentVertex);
/* 156 */             this.parentCapacityMap.put(neighboringVertex, new Integer(newCapacity));
/* 157 */             visitedEdgesMap.add(neighboringEdge);
/* 158 */             if (neighboringVertex != this.target) {
/* 159 */               queue.add(neighboringVertex);
/*     */             }
/*     */           }
/*     */         }
/*     */       }
/*     */     }
/* 165 */     boolean hasAugmentingPath = false;
/* 166 */     Number targetsParentCapacity = (Number)this.parentCapacityMap.get(this.target);
/* 167 */     if ((targetsParentCapacity != null) && (targetsParentCapacity.intValue() > 0)) {
/* 168 */       updateResidualCapacities();
/* 169 */       hasAugmentingPath = true;
/*     */     }
/* 171 */     clearParentValues();
/* 172 */     return hasAugmentingPath;
/*     */   }
/*     */   
/*     */   public void step()
/*     */   {
/* 177 */     while (hasAugmentingPath()) {}
/*     */     
/* 179 */     computeMinCut();
/*     */   }
/*     */   
/*     */ 
/*     */   private void computeMinCut()
/*     */   {
/* 185 */     for (E e : this.mOriginalGraph.getEdges())
/*     */     {
/* 187 */       V source = this.mOriginalGraph.getSource(e);
/* 188 */       V destination = this.mOriginalGraph.getDest(e);
/* 189 */       if (((!this.mSinkPartitionNodes.contains(source)) || (!this.mSinkPartitionNodes.contains(destination))) && 
/*     */       
/*     */ 
/* 192 */         ((!this.mSourcePartitionNodes.contains(source)) || (!this.mSourcePartitionNodes.contains(destination))) && (
/*     */         
/*     */ 
/* 195 */         (!this.mSinkPartitionNodes.contains(source)) || (!this.mSourcePartitionNodes.contains(destination))))
/*     */       {
/*     */ 
/* 198 */         this.mMinCutEdges.add(e);
/*     */       }
/*     */     }
/*     */   }
/*     */   
/*     */ 
/*     */   public int getMaxFlow()
/*     */   {
/* 206 */     return this.mMaxFlow;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   public Set<V> getNodesInSinkPartition()
/*     */   {
/* 214 */     return this.mSinkPartitionNodes;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   public Set<V> getNodesInSourcePartition()
/*     */   {
/* 222 */     return this.mSourcePartitionNodes;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */   public Set<E> getMinCutEdges()
/*     */   {
/* 229 */     return this.mMinCutEdges;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */   public DirectedGraph<V, E> getFlowGraph()
/*     */   {
/* 236 */     return this.mFlowGraph;
/*     */   }
/*     */   
/*     */   protected void initializeIterations()
/*     */   {
/* 241 */     this.parentCapacityMap.put(this.source, Integer.valueOf(Integer.MAX_VALUE));
/* 242 */     this.parentMap.put(this.source, this.source);
/*     */     
/* 244 */     List<E> edgeList = new ArrayList(this.mFlowGraph.getEdges());
/*     */     
/* 246 */     for (int eIdx = 0; eIdx < edgeList.size(); eIdx++) {
/* 247 */       E edge = edgeList.get(eIdx);
/* 248 */       Number capacity = (Number)this.edgeCapacityTransformer.transform(edge);
/*     */       
/* 250 */       if (capacity == null) {
/* 251 */         throw new IllegalArgumentException("Edge capacities must be provided in Transformer passed to constructor");
/*     */       }
/* 253 */       this.residualCapacityMap.put(edge, capacity);
/*     */       
/* 255 */       V source = this.mFlowGraph.getSource(edge);
/* 256 */       V destination = this.mFlowGraph.getDest(edge);
/*     */       
/* 258 */       if (!this.mFlowGraph.isPredecessor(source, destination)) {
/* 259 */         E backEdge = this.edgeFactory.create();
/* 260 */         this.mFlowGraph.addEdge(backEdge, destination, source, EdgeType.DIRECTED);
/* 261 */         this.residualCapacityMap.put(backEdge, Integer.valueOf(0));
/*     */       }
/*     */     }
/*     */   }
/*     */   
/*     */ 
/*     */   protected void finalizeIterations()
/*     */   {
/* 269 */     for (E currentEdge : this.mFlowGraph.getEdges()) {
/* 270 */       Number capacity = (Number)this.edgeCapacityTransformer.transform(currentEdge);
/*     */       
/* 272 */       Number residualCapacity = (Number)this.residualCapacityMap.get(currentEdge);
/* 273 */       if (capacity != null) {
/* 274 */         Integer flowValue = new Integer(capacity.intValue() - residualCapacity.intValue());
/* 275 */         this.edgeFlowMap.put(currentEdge, flowValue);
/*     */       }
/*     */     }
/*     */     
/* 279 */     Set<E> backEdges = new HashSet();
/* 280 */     for (E currentEdge : this.mFlowGraph.getEdges())
/*     */     {
/* 282 */       if (this.edgeCapacityTransformer.transform(currentEdge) == null) {
/* 283 */         backEdges.add(currentEdge);
/*     */       } else {
/* 285 */         this.residualCapacityMap.remove(currentEdge);
/*     */       }
/*     */     }
/* 288 */     for (E e : backEdges) {
/* 289 */       this.mFlowGraph.removeEdge(e);
/*     */     }
/*     */   }
/*     */   
/*     */   private void updateResidualCapacities()
/*     */   {
/* 295 */     Number augmentingPathCapacity = (Number)this.parentCapacityMap.get(this.target);
/* 296 */     this.mMaxFlow += augmentingPathCapacity.intValue();
/* 297 */     V currentVertex = this.target;
/* 298 */     V parentVertex = null;
/* 299 */     while ((parentVertex = this.parentMap.get(currentVertex)) != currentVertex) {
/* 300 */       E currentEdge = this.mFlowGraph.findEdge(parentVertex, currentVertex);
/*     */       
/* 302 */       Number residualCapacity = (Number)this.residualCapacityMap.get(currentEdge);
/*     */       
/* 304 */       residualCapacity = Integer.valueOf(residualCapacity.intValue() - augmentingPathCapacity.intValue());
/* 305 */       this.residualCapacityMap.put(currentEdge, residualCapacity);
/*     */       
/* 307 */       E backEdge = this.mFlowGraph.findEdge(currentVertex, parentVertex);
/* 308 */       residualCapacity = (Number)this.residualCapacityMap.get(backEdge);
/* 309 */       residualCapacity = Integer.valueOf(residualCapacity.intValue() + augmentingPathCapacity.intValue());
/* 310 */       this.residualCapacityMap.put(backEdge, residualCapacity);
/* 311 */       currentVertex = parentVertex;
/*     */     }
/*     */   }
/*     */ }


/* Location:              /home/menick/Scrivania/ProcessMining/CNMining/CNMining.jar!/edu/uci/ics/jung/algorithms/flows/EdmondsKarpMaxFlow.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       0.7.1
 */