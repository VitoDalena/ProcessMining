package org.processmining.plugins.cnmining;

import java.util.HashMap;
import org.processmining.models.graphbased.directed.DirectedGraphEdge;
import org.processmining.models.graphbased.directed.DirectedGraphNode;

public class CausalNetAnnotations
{
  public static final String counterTask = "cout";
  public static final String counterStartTask = "cous";
  public static final String counterEndTask = "coue";
  public static final String directDependency = "ddep";
  public static final String id = "id";
  public static final String longDistanceDependency = "lddep";
  public static final String longDistanceRelations = "ldrel";
  public static final String parameters = "par";
  public static final String relations = "rel";
  public static final String splitJoinPatterns = "sjpat";
  private HashMap<String, Object> infoExecution;
  private HashMap<DirectedGraphNode, HashMap<String, Object>> infoNodes;
  private HashMap<DirectedGraphEdge<?, ?>, HashMap<String, Object>> infoEdges;
  
  public CausalNetAnnotations()
  {
    this.infoExecution = new HashMap();
    this.infoNodes = new HashMap();
    this.infoEdges = new HashMap();
  }
  
  public void addExecutionInfo(String attribute, Object info)
  {
    this.infoExecution.put(attribute, info);
  }
  
  public Object getExecutionInfo(String attribute)
  {
    return this.infoExecution.get(attribute);
  }
  
  public void addNodeInfo(DirectedGraphNode node, String attribute, Object info)
  {
    HashMap<String, Object> stack = null;
    if (this.infoNodes.containsKey(node))
    {
      stack = (HashMap)this.infoNodes.get(node);
    }
    else
    {
      stack = new HashMap();
      this.infoNodes.put(node, stack);
    }
    stack.put(attribute, info);
  }
  
  public Object getNodeInfo(DirectedGraphNode node, String attribute)
  {
    if (this.infoNodes.containsKey(node)) {
      return ((HashMap)this.infoNodes.get(node)).get(attribute);
    }
    return null;
  }
  
  public void addEdgeInfo(DirectedGraphEdge<?, ?> edge, String attribute, Object info)
  {
    HashMap<String, Object> stack = null;
    if (this.infoEdges.containsKey(edge))
    {
      stack = (HashMap)this.infoEdges.get(edge);
    }
    else
    {
      stack = new HashMap();
      this.infoEdges.put(edge, stack);
    }
    stack.put(attribute, info);
  }
  
  public Object getEdgeInfo(DirectedGraphEdge<?, ?> edge, String attribute)
  {
    if (this.infoEdges.containsKey(edge)) {
      return ((HashMap)this.infoEdges.get(edge)).get(attribute);
    }
    return null;
  }
  
  public String toString()
  {
    return this.infoExecution.toString() + "\n" + this.infoNodes.toString() + "\n" + this.infoEdges.toString();
  }
}
