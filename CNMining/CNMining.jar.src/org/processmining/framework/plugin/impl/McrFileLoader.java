/*     */ package org.processmining.framework.plugin.impl;
/*     */ 
/*     */ import com.thoughtworks.xstream.XStream;
/*     */ import java.io.BufferedInputStream;
/*     */ import java.io.File;
/*     */ import java.io.FileInputStream;
/*     */ import java.io.FileNotFoundException;
/*     */ import java.io.IOException;
/*     */ import java.util.ArrayList;
/*     */ import java.util.Iterator;
/*     */ import java.util.List;
/*     */ import java.util.Map;
/*     */ import java.util.Set;
/*     */ import java.util.StringTokenizer;
/*     */ import java.util.zip.GZIPInputStream;
/*     */ import javax.xml.parsers.DocumentBuilder;
/*     */ import javax.xml.parsers.DocumentBuilderFactory;
/*     */ import javax.xml.parsers.ParserConfigurationException;
/*     */ import org.deckfour.xes.xstream.XesXStreamPersistency;
/*     */ import org.processmining.framework.connections.Connection;
/*     */ import org.processmining.framework.plugin.PluginContext;
/*     */ import org.processmining.framework.plugin.PluginDescriptor;
/*     */ import org.processmining.framework.plugin.PluginManager;
/*     */ import org.processmining.framework.util.Cast;
/*     */ import org.processmining.framework.util.Pair;
/*     */ import org.w3c.dom.DOMException;
/*     */ import org.w3c.dom.Document;
/*     */ import org.w3c.dom.NamedNodeMap;
/*     */ import org.w3c.dom.Node;
/*     */ import org.w3c.dom.NodeList;
/*     */ import org.xml.sax.SAXException;
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
/*     */ class McrFileLoader
/*     */ {
/*     */   public static void loadFromFile(File f, MacroPluginDescriptorImpl macroPlugin, PluginManager manager)
/*     */     throws FileNotFoundException, SAXException, IOException, ParserConfigurationException, DOMException, ClassNotFoundException, DependsOnUnknownException
/*     */   {
/* 460 */     DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
/* 461 */     DocumentBuilder builder = factory.newDocumentBuilder();
/* 462 */     Document doc = builder.parse(new GZIPInputStream(new BufferedInputStream(new FileInputStream(f))));
/*     */     
/* 464 */     XStream stream = new XStream();
/* 465 */     stream.autodetectAnnotations(true);
/*     */     
/* 467 */     XesXStreamPersistency.register(stream);
/*     */     
/* 469 */     NodeList nodes = doc.getElementsByTagName("SerializedTypes");
/* 470 */     for (int i = 0; i < nodes.getLength(); i++) {
/* 471 */       Node node = nodes.item(i);
/*     */       
/* 473 */       Class<?> type = Class.forName(node.getAttributes().getNamedItem("type").getNodeValue());
/* 474 */       stream.processAnnotations(type);
/*     */     }
/*     */     
/* 477 */     nodes = doc.getElementsByTagName("SerializedObjects");
/* 478 */     assert (nodes.getLength() == 1);
/* 479 */     List<Object> serializedObjects = (List)Cast.cast(stream.fromXML(nodes.item(0).getTextContent()));
/*     */     
/* 481 */     nodes = doc.getElementsByTagName("PluginCell");
/* 482 */     for (int i = 0; i < nodes.getLength(); i++) {
/* 483 */       Node node = nodes.item(i);
/* 484 */       int index = Integer.parseInt(node.getAttributes().getNamedItem("index").getNodeValue());
/* 485 */       String pluginID = node.getAttributes().getNamedItem("pluginID").getNodeValue();
/*     */       
/* 487 */       PluginDescriptor plugin = manager.getPlugin(pluginID);
/* 488 */       if (plugin == null) {
/* 489 */         throw new DependsOnUnknownException(macroPlugin.getFileName() + " depends on: " + pluginID);
/*     */       }
/*     */       
/* 492 */       int selIndex = Integer.parseInt(node.getAttributes().getNamedItem("selectedMethodIndex").getNodeValue());
/* 493 */       String methods = node.getAttributes().getNamedItem("methods").getNodeValue();
/* 494 */       StringTokenizer st = new StringTokenizer(methods, ",");
/* 495 */       List<Integer> enabled = new ArrayList();
/* 496 */       while (st.hasMoreTokens()) {
/* 497 */         enabled.add(Integer.valueOf(Integer.parseInt(st.nextToken())));
/*     */       }
/* 499 */       PluginParameter selectedPlugin = new PluginParameter(plugin, Integer.valueOf(selIndex));
/* 500 */       macroPlugin.index2Objects.put(Integer.valueOf(index), selectedPlugin);
/*     */       
/* 502 */       if (plugin.getParameterNames(selIndex).isEmpty()) {
/* 503 */         macroPlugin.object2Rank.put(selectedPlugin, Integer.valueOf(0));
/*     */       }
/*     */       
/* 506 */       Class<? extends PluginContext> reqContextType = plugin.getContextType(selIndex);
/* 507 */       if (!reqContextType.isAssignableFrom(macroPlugin.contextType)) {
/* 508 */         macroPlugin.contextType = reqContextType;
/*     */       }
/*     */     }
/*     */     
/* 512 */     nodes = doc.getElementsByTagName("ProvidedObjectCell");
/* 513 */     for (int i = 0; i < nodes.getLength(); i++) {
/* 514 */       Node node = nodes.item(i);
/* 515 */       int index = Integer.parseInt(node.getAttributes().getNamedItem("index").getNodeValue());
/* 516 */       int objectIndex = Integer.parseInt(node.getAttributes().getNamedItem("objectIndex").getNodeValue());
/* 517 */       Object object = serializedObjects.get(objectIndex);
/* 518 */       macroPlugin.index2Objects.put(Integer.valueOf(index), object);
/* 519 */       macroPlugin.object2Rank.put(object, Integer.valueOf(0));
/*     */     }
/*     */     
/*     */ 
/*     */ 
/*     */ 
/* 525 */     nodes = doc.getElementsByTagName("InputPortCell");
/* 526 */     for (int i = 0; i < nodes.getLength(); i++) {
/* 527 */       Node node = nodes.item(i);
/* 528 */       int index = Integer.parseInt(node.getAttributes().getNamedItem("index").getNodeValue());
/* 529 */       String name = node.getAttributes().getNamedItem("name").getNodeValue();
/* 530 */       Class<?> type = Class.forName(node.getAttributes().getNamedItem("type").getNodeValue());
/*     */       
/*     */ 
/* 533 */       InputParameter input = new InputParameter(name, type, macroPlugin.parameterNames.size());
/* 534 */       macroPlugin.index2Objects.put(Integer.valueOf(index), input);
/*     */       
/* 536 */       macroPlugin.parameterNames.add(name);
/* 537 */       macroPlugin.parameterTypes.add(type);
/* 538 */       macroPlugin.object2Rank.put(input, Integer.valueOf(0));
/*     */     }
/*     */     
/* 541 */     nodes = doc.getElementsByTagName("OutputPortCell");
/* 542 */     for (int i = 0; i < nodes.getLength(); i++) {
/* 543 */       Node node = nodes.item(i);
/* 544 */       int index = Integer.parseInt(node.getAttributes().getNamedItem("index").getNodeValue());
/* 545 */       String name = node.getAttributes().getNamedItem("name").getNodeValue();
/* 546 */       Class<?> type = Class.forName(node.getAttributes().getNamedItem("type").getNodeValue());
/*     */       
/*     */ 
/* 549 */       OutputParameter par = new OutputParameter(name, type, macroPlugin.returnNames.size());
/* 550 */       macroPlugin.index2Objects.put(Integer.valueOf(index), par);
/*     */       
/* 552 */       macroPlugin.returnNames.add(name);
/* 553 */       macroPlugin.returnTypes.add(type);
/* 554 */       macroPlugin.returnParameters.add(par);
/*     */     }
/*     */     
/* 557 */     nodes = doc.getElementsByTagName("Connection");
/* 558 */     for (int i = 0; i < nodes.getLength(); i++) {
/* 559 */       Node node = nodes.item(i);
/* 560 */       int objectIndex = Integer.parseInt(node.getAttributes().getNamedItem("objectIndex").getNodeValue());
/* 561 */       macroPlugin.connectionsOnFirstInvoke.add((Connection)serializedObjects.get(objectIndex));
/*     */     }
/*     */     
/*     */ 
/* 565 */     nodes = doc.getElementsByTagName("Edge");
/* 566 */     ArrayList<Node> toProcess = new ArrayList(nodes.getLength());
/*     */     
/* 568 */     for (int i = 0; i < nodes.getLength(); i++) {
/* 569 */       toProcess.add(nodes.item(i));
/*     */     }
/*     */     
/* 572 */     int rank = 0;
/* 573 */     while (!toProcess.isEmpty()) {
/* 574 */       Iterator<Node> it = toProcess.iterator();
/* 575 */       while (it.hasNext()) {
/* 576 */         Node node = (Node)it.next();
/*     */         
/* 578 */         int sourceObjectIndex = Integer.parseInt(node.getAttributes().getNamedItem("sourceCellIndex").getNodeValue());
/*     */         
/* 580 */         int targetObjectIndex = Integer.parseInt(node.getAttributes().getNamedItem("targetCellIndex").getNodeValue());
/*     */         
/* 582 */         int sourceParameterIndex = Integer.parseInt(node.getAttributes().getNamedItem("sourcePortIndex").getNodeValue());
/*     */         
/* 584 */         int targetParameterIndex = Integer.parseInt(node.getAttributes().getNamedItem("targetPortIndex").getNodeValue());
/*     */         
/*     */ 
/* 587 */         Object sourceObject = macroPlugin.index2Objects.get(Integer.valueOf(sourceObjectIndex));
/*     */         
/* 589 */         assert (sourceObject != null);
/* 590 */         if (macroPlugin.object2Rank.get(sourceObject) != null)
/*     */         {
/*     */ 
/*     */ 
/* 594 */           Object targetObject = macroPlugin.index2Objects.get(Integer.valueOf(targetObjectIndex));
/* 595 */           assert (targetObject != null);
/*     */           
/* 597 */           int predRank = ((Integer)macroPlugin.object2Rank.get(sourceObject)).intValue();
/* 598 */           if (predRank < rank)
/*     */           {
/* 600 */             Pair<Object, Integer> source = new Pair(sourceObject, Integer.valueOf(sourceParameterIndex));
/* 601 */             Pair<Object, Integer> target = new Pair(targetObject, Integer.valueOf(targetParameterIndex));
/* 602 */             macroPlugin.edges.add(new Pair(source, target));
/* 603 */             it.remove();
/*     */           }
/*     */           else
/*     */           {
/* 607 */             macroPlugin.object2Rank.put(targetObject, Integer.valueOf(rank + 1));
/*     */           }
/*     */         } }
/* 610 */       rank++;
/*     */     }
/* 612 */     macroPlugin.maxRank = rank;
/*     */   }
/*     */ }


/* Location:              /home/menick/Scrivania/ProcessMining/CNMining/CNMining.jar!/org/processmining/framework/plugin/impl/McrFileLoader.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */