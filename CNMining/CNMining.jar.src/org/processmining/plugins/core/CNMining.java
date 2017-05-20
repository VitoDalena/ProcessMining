/*      */ package org.processmining.plugins.core;
/*      */ 
/*      */ import com.carrotsearch.hppc.IntArrayList;
/*      */ import com.carrotsearch.hppc.IntIntOpenHashMap;
/*      */ import com.carrotsearch.hppc.IntOpenHashSet;
/*      */ import com.carrotsearch.hppc.ObjectArrayList;
/*      */ import com.carrotsearch.hppc.ObjectContainer;
/*      */ import com.carrotsearch.hppc.ObjectIntOpenHashMap;
/*      */ import com.carrotsearch.hppc.ObjectLookupContainer;
/*      */ import com.carrotsearch.hppc.ObjectObjectOpenHashMap;
/*      */ import com.carrotsearch.hppc.ObjectOpenHashSet;
/*      */ import com.carrotsearch.hppc.cursors.IntCursor;
/*      */ import com.carrotsearch.hppc.cursors.ObjectCursor;
/*      */ import com.fluxicon.slickerbox.components.NiceSlider;
/*      */ import com.fluxicon.slickerbox.components.NiceSlider.Orientation;
/*      */ import com.fluxicon.slickerbox.factory.SlickerFactory;
/*      */ import com.jgraph.layout.JGraphFacade;
/*      */ import com.jgraph.layout.hierarchical.JGraphHierarchicalLayout;
/*      */ import java.awt.Color;
/*      */ import java.awt.Dimension;
/*      */ import java.io.File;
/*      */ import java.io.IOException;
/*      */ import java.io.InputStream;
/*      */ import java.io.PrintStream;
/*      */ import java.nio.file.FileSystem;
/*      */ import java.nio.file.FileSystems;
/*      */ import java.nio.file.Files;
/*      */ import java.nio.file.OpenOption;
/*      */ import java.nio.file.StandardOpenOption;
/*      */ import java.util.Collection;
/*      */ import java.util.Date;
/*      */ import java.util.HashMap;
/*      */ import java.util.Iterator;
/*      */ import java.util.LinkedList;
/*      */ import java.util.Map;
/*      */ import javax.swing.BorderFactory;
/*      */ import javax.swing.JOptionPane;
/*      */ import javax.swing.JSlider;
/*      */ import javax.swing.event.ChangeEvent;
/*      */ import javax.swing.event.ChangeListener;
/*      */ import org.deckfour.uitopia.api.event.TaskListener.InteractionResult;
/*      */ import org.deckfour.xes.extension.std.XConceptExtension;
/*      */ import org.deckfour.xes.extension.std.XLifecycleExtension;
/*      */ import org.deckfour.xes.extension.std.XTimeExtension;
/*      */ import org.deckfour.xes.factory.XFactory;
/*      */ import org.deckfour.xes.factory.XFactoryRegistry;
/*      */ import org.deckfour.xes.model.XAttribute;
/*      */ import org.deckfour.xes.model.XAttributeMap;
/*      */ import org.deckfour.xes.model.XEvent;
/*      */ import org.deckfour.xes.model.XLog;
/*      */ import org.deckfour.xes.model.XTrace;
/*      */ import org.jgraph.graph.GraphLayoutCache;
/*      */ import org.processmining.contexts.cli.CLIContext;
/*      */ import org.processmining.contexts.cli.CLIPluginContext;
/*      */ import org.processmining.contexts.uitopia.UIPluginContext;
/*      */ import org.processmining.contexts.uitopia.annotations.UITopiaVariant;
/*      */ import org.processmining.framework.plugin.ProMFuture;
/*      */ import org.processmining.framework.plugin.Progress;
/*      */ import org.processmining.framework.plugin.annotations.Plugin;
/*      */ import org.processmining.framework.plugin.annotations.PluginVariant;
/*      */ import org.processmining.framework.util.ui.widgets.ProMPropertiesPanel;
/*      */ import org.processmining.models.causalnet.CausalNetAnnotations;
/*      */ import org.processmining.models.causalnet.CausalNetAnnotationsConnection;
/*      */ import org.processmining.models.connections.GraphLayoutConnection;
/*      */ import org.processmining.models.connections.flexiblemodel.FlexEndTaskNodeConnection;
/*      */ import org.processmining.models.connections.flexiblemodel.FlexStartTaskNodeConnection;
/*      */ import org.processmining.models.flexiblemodel.EndTaskNodesSet;
/*      */ import org.processmining.models.flexiblemodel.Flex;
/*      */ import org.processmining.models.flexiblemodel.FlexFactory;
/*      */ import org.processmining.models.flexiblemodel.FlexNode;
/*      */ import org.processmining.models.flexiblemodel.SetFlex;
/*      */ import org.processmining.models.flexiblemodel.StartTaskNodesSet;
/*      */ import org.processmining.models.graphbased.ViewSpecificAttributeMap;
/*      */ import org.processmining.models.graphbased.directed.DirectedGraph;
/*      */ import org.processmining.models.graphbased.directed.petrinet.Petrinet;
/*      */ import org.processmining.models.graphbased.directed.petrinet.PetrinetNode;
/*      */ import org.processmining.models.graphbased.directed.petrinet.elements.Transition;
/*      */ import org.processmining.models.graphbased.directed.petrinet.impl.PetrinetFactory;
/*      */ import org.processmining.models.jgraph.ProMGraphModel;
/*      */ import org.processmining.models.jgraph.ProMJGraph;
/*      */ import org.processmining.models.jgraph.ProMJGraphVisualizer;
/*      */ import org.processmining.models.jgraph.visualization.ProMJGraphPanel;
/*      */ import org.processmining.models.semantics.petrinet.Marking;
/*      */ import org.processmining.plugins.pnml.Pnml;
/*      */ import org.xmlpull.v1.XmlPullParser;
/*      */ import org.xmlpull.v1.XmlPullParserException;
/*      */ import org.xmlpull.v1.XmlPullParserFactory;
/*      */ 
/*      */ @Plugin(name="CNMining", parameterLabels={"log"}, returnLabels={"CausalNet", "StartTaskNodesSet", "EndTaskNodesSet", "CausalNetAnnotations"}, returnTypes={Flex.class, StartTaskNodesSet.class, EndTaskNodesSet.class, CausalNetAnnotations.class}, userAccessible=true, help="?? ")
/*      */ public class CNMining
/*      */ {
/*   92 */   public static String attivita_iniziale = "_START_";
/*   93 */   public static String attivita_finale = "_END_";
/*      */   
/*   95 */   private double value = 0.0D;
/*   96 */   private double delta = 0.0D;
/*   97 */   private double relative_to_best = 0.0D;
/*      */   public static long time;
/*      */   
/*      */   public double[] computePrecisionRecall(boolean[][] testMatrix, boolean[][] realMatrix) {
/*  101 */     int TP = 0;int FP = 0;int TN = 0;int FN = 0;
/*      */     
/*  103 */     int r = testMatrix.length;
/*  104 */     int c = testMatrix[0].length;
/*      */     
/*  106 */     for (int i = 0; i < r; i++) {
/*  107 */       for (int j = 0; j < c; j++)
/*      */       {
/*  109 */         boolean estimated = testMatrix[i][j];
/*  110 */         boolean real = realMatrix[i][j];
/*  111 */         if (estimated) {
/*  112 */           if (real) {
/*  113 */             TP++;
/*      */           } else {
/*  115 */             FP++;
/*      */           }
/*  117 */         } else if (!real) {
/*  118 */           TN++;
/*      */         } else {
/*  120 */           FN++;
/*      */         }
/*      */       }
/*      */     }
/*      */     double prec;
/*      */     double prec;
/*  126 */     if (TP + FP > 0) {
/*  127 */       prec = TP / (TP + FP);
/*      */     } else
/*  129 */       prec = 1.0D;
/*  130 */     double rec; double rec; if (TP + FN > 0) {
/*  131 */       rec = TP / (TP + FN);
/*      */     } else
/*  133 */       rec = 0.0D;
/*  134 */     return new double[] { prec, rec };
/*      */   }
/*      */   
/*      */   @UITopiaVariant(affiliation="DIMES University of Calabria", author="F. Lupia", email="lupia@dimes.unical.it")
/*      */   @PluginVariant(variantLabel="CNMining", requiredParameterLabels={0})
/*      */   public Object[] run(UIPluginContext context, XLog log) throws Exception
/*      */   {
/*  141 */     XConceptExtension conceptExtension = XConceptExtension.instance();
/*  142 */     String logName = conceptExtension.extractName(log);
/*      */     
/*  144 */     ProMPropertiesPanel panel = new ProMPropertiesPanel("");
/*      */     
/*  146 */     PannelloConstraints pannello = new PannelloConstraints();
/*      */     
/*      */ 
/*  149 */     panel.add(pannello, 1);
/*      */     
/*      */ 
/*      */ 
/*  153 */     final NiceSlider slider = SlickerFactory.instance().createNiceIntegerSlider("T percentage", 0, 
/*  154 */       100, 5, NiceSlider.Orientation.HORIZONTAL);
/*  155 */     ChangeListener listener = new ChangeListener()
/*      */     {
/*      */       public void stateChanged(ChangeEvent e)
/*      */       {
/*  159 */         int percentage = slider.getSlider().getValue();
/*      */         
/*  161 */         CNMining.this.value = (percentage / 100.0D);
/*      */ 
/*      */       }
/*      */       
/*      */ 
/*  166 */     };
/*  167 */     slider.addChangeListener(listener);
/*  168 */     listener.stateChanged(null);
/*      */     
/*  170 */     final NiceSlider slider1 = SlickerFactory.instance().createNiceIntegerSlider("δ percentage", 0, 100, 
/*  171 */       90, NiceSlider.Orientation.HORIZONTAL);
/*  172 */     ChangeListener listener1 = new ChangeListener()
/*      */     {
/*      */       public void stateChanged(ChangeEvent e)
/*      */       {
/*  176 */         int percentage = slider1.getSlider().getValue();
/*  177 */         CNMining.this.delta = (percentage / 100.0D);
/*      */       }
/*      */       
/*      */ 
/*  181 */     };
/*  182 */     slider1.addChangeListener(listener1);
/*  183 */     listener1.stateChanged(null);
/*      */     
/*  185 */     final NiceSlider slider2 = SlickerFactory.instance().createNiceIntegerSlider("\034T_r2b percentage", 0, 
/*  186 */       100, 75, NiceSlider.Orientation.HORIZONTAL);
/*  187 */     ChangeListener listener2 = new ChangeListener()
/*      */     {
/*      */       public void stateChanged(ChangeEvent e)
/*      */       {
/*  191 */         int percentage = slider2.getSlider().getValue();
/*      */         
/*  193 */         CNMining.this.relative_to_best = (percentage / 100.0D);
/*      */ 
/*      */       }
/*      */       
/*      */ 
/*  198 */     };
/*  199 */     slider2.addChangeListener(listener2);
/*  200 */     listener2.stateChanged(null);
/*      */     
/*  202 */     slider.setBorder(BorderFactory.createEtchedBorder(Color.white, Color.gray));
/*  203 */     slider1.setBorder(BorderFactory.createEtchedBorder(Color.white, Color.gray));
/*  204 */     slider2.setBorder(BorderFactory.createEtchedBorder(Color.white, Color.gray));
/*      */     
/*  206 */     panel.add(slider, "Center");
/*  207 */     panel.add(slider1, "South");
/*  208 */     panel.add(slider2, "South");
/*      */     
/*  210 */     TaskListener.InteractionResult result = context.showConfiguration("Settings", panel);
/*      */     
/*  212 */     if (result.equals(TaskListener.InteractionResult.CANCEL)) {
/*  213 */       context.getFutureResult(0).cancel(true);
/*      */     }
/*      */     
/*  216 */     Settings s = new Settings();
/*      */     
/*  218 */     s.setConstraintsEnabled(pannello.isConstraintsEnabled());
/*  219 */     s.setConstr_file_name(pannello.getFilePath());
/*  220 */     s.setSigmaLogNoise(this.value);
/*  221 */     s.setLogName(logName);
/*  222 */     s.setFallFactor(this.delta);
/*  223 */     s.setRelativeToBest(this.relative_to_best);
/*      */     
/*  225 */     return startCNMining(context, log, s);
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   @PluginVariant(variantLabel="CNMining", requiredParameterLabels={0})
/*      */   public static Object[] startCNMining(UIPluginContext context, XLog log, Settings s)
/*      */     throws Exception
/*      */   {
/*  851 */     context.getProgress().setValue(1);
/*      */     
/*  853 */     boolean enable_constraints = s.isConstraintsEnabled();
/*      */     
/*      */ 
/*  856 */     double sigma_up_cs_diff = 0.2D;
/*      */     
/*      */ 
/*  859 */     double sigma_low_cs_constr_edges = 0.0D;
/*      */     
/*      */ 
/*  862 */     double sigma_log_noise = s.getSigmaLogNoise();
/*      */     
/*      */ 
/*  865 */     double ff = s.getFallFactor();
/*      */     
/*      */ 
/*  868 */     double relative_to_best = s.getRelativeToBest();
/*      */     
/*  870 */     System.out.println("sigma log noise " + sigma_log_noise);
/*  871 */     System.out.println("delta fall factor  " + ff);
/*  872 */     System.out.println("relative to best  " + relative_to_best);
/*      */     
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*  923 */     ObjectArrayList<Forbidden> lista_forbidden = new ObjectArrayList();
/*      */     
/*  925 */     ObjectArrayList<Constraint> vincoli_positivi = new ObjectArrayList();
/*      */     
/*  927 */     ObjectArrayList<Constraint> vincoli_negati = new ObjectArrayList();
/*      */     
/*  929 */     if (enable_constraints) {
/*  930 */       if (s.getConstr_file_name().equals("")) {
/*  931 */         JOptionPane.showMessageDialog(null, "Incorrect path to constraints file\nThe algoritm will now run without constraints...");
/*  932 */         enable_constraints = false;
/*      */       }
/*      */       else {
/*  935 */         ConstraintParser cp = new ConstraintParser(s.getConstr_file_name());
/*      */         
/*      */ 
/*      */ 
/*  939 */         boolean validFile = cp.run();
/*      */         
/*  941 */         if (!validFile) {
/*  942 */           JOptionPane.showMessageDialog(null, "Invalid constraints file\nThe algoritm will now run without constraints...");
/*  943 */           enable_constraints = false;
/*      */         }
/*      */         else {
/*  946 */           ObjectArrayList<Constraint> constraints = cp.getConstraints();
/*  947 */           if (constraints.size() == 0) {
/*  948 */             JOptionPane.showMessageDialog(null, "No constraints contained in the input file...");
/*      */           }
/*  950 */           for (int i = 0; i < constraints.size(); i++) {
/*  951 */             Constraint constr = (Constraint)constraints.get(i);
/*  952 */             if (constr.isPositiveConstraint()) {
/*  953 */               vincoli_positivi.add(constr);
/*      */             } else { Iterator localIterator2;
/*  955 */               for (Iterator localIterator1 = constr.getBodyList().iterator(); localIterator1.hasNext(); 
/*  956 */                   localIterator2.hasNext())
/*      */               {
/*  955 */                 String body = (String)localIterator1.next();
/*  956 */                 localIterator2 = constr.getHeadList().iterator(); continue;String head = (String)localIterator2.next();
/*  957 */                 lista_forbidden.add(new Forbidden(body, head));
/*      */               }
/*  959 */               vincoli_negati.add(constr);
/*      */             }
/*      */           }
/*      */         }
/*      */       }
/*      */     }
/*      */     
/*  966 */     ObjectArrayList<Forbidden> lista_forbidden_unfolded = new ObjectArrayList();
/*      */     
/*  968 */     ObjectArrayList<Constraint> vincoli_positivi_unfolded = new ObjectArrayList();
/*      */     
/*  970 */     ObjectArrayList<Constraint> vincoli_negati_unfolded = new ObjectArrayList();
/*      */     
/*  972 */     CNMining cnm = new CNMining();
/*      */     
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*  980 */     cnm.aggiungiAttivitaFittizia(log);
/*      */     
/*      */ 
/*  983 */     Object[] array = LogUnfolder.unfold(log);
/*      */     
/*  985 */     ObjectIntOpenHashMap<String> map = (ObjectIntOpenHashMap)array[0];
/*      */     
/*  987 */     Object attivita_tracce = (ObjectObjectOpenHashMap)array[1];
/*      */     
/*  989 */     ObjectObjectOpenHashMap<String, ObjectArrayList<String>> traccia_attivita = (ObjectObjectOpenHashMap)array[2];
/*      */     
/*  991 */     if (enable_constraints) {
/*  992 */       cnm.creaVincoliUnfolded(vincoli_positivi, vincoli_negati, lista_forbidden, vincoli_positivi_unfolded, 
/*  993 */         vincoli_negati_unfolded, lista_forbidden_unfolded, map);
/*      */     }
/*  995 */     context.getProgress().setValue(10);
/*      */     
/*  997 */     System.out.println("OK1");
/*      */     
/*  999 */     double[][] csm = cnm.calcoloMatriceDeiCausalScore(log, map, traccia_attivita, ff);
/*      */     
/*      */ 
/*      */ 
/* 1003 */     System.out.println("OK2");
/* 1004 */     double[][] m = cnm.buildBestNextMatrix(log, map, traccia_attivita, csm, lista_forbidden_unfolded);
/*      */     
/* 1006 */     System.out.println("OK3");
/* 1007 */     if (sigma_log_noise > 0.0D)
/*      */     {
/* 1009 */       for (int i = 0; i < m.length; i++)
/*      */       {
/* 1011 */         for (int j = 0; j < m.length; j++)
/*      */         {
/* 1013 */           if (m[i][j] <= sigma_log_noise * traccia_attivita.size())
/*      */           {
/*      */ 
/* 1016 */             m[i][j] = 0.0D; }
/*      */         }
/*      */       }
/*      */     }
/* 1020 */     System.out.println();
/*      */     
/*      */ 
/* 1023 */     System.out.println("COSTRUISCO GRAFO UNFOLDED ORIGINALE SOLO LOG... ");
/*      */     
/* 1025 */     Graph graph = new Graph();
/*      */     
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/* 1032 */     Object[] keys = map.keys;
/* 1033 */     int[] values = map.values;
/* 1034 */     boolean[] states = map.allocated;
/*      */     
/* 1036 */     for (int iii = 0; iii < states.length; iii++)
/*      */     {
/* 1038 */       if (states[iii] != 0)
/*      */       {
/* 1040 */         Node node = new Node((String)keys[iii], values[iii]);
/* 1041 */         Object[] nKeys = graph.getMap().keys;
/* 1042 */         boolean[] nStates = graph.getMap().allocated;
/*      */         
/* 1044 */         boolean found = false;
/* 1045 */         for (int jj = 0; jj < nStates.length; jj++) {
/* 1046 */           if ((nStates[jj] != 0) && 
/* 1047 */             (nKeys[jj].equals(node))) {
/* 1048 */             found = true;
/* 1049 */             break;
/*      */           }
/*      */         }
/* 1052 */         if (!found) {
/* 1053 */           graph.getMap().put(node, new ObjectOpenHashSet());
/*      */         }
/*      */       }
/*      */     }
/*      */     
/* 1058 */     for (int p = 0; p < m.length; p++) {
/* 1059 */       for (int r = 0; r < m[0].length; r++)
/* 1060 */         if (m[p][r] > 0.0D) {
/* 1061 */           Node np = graph.getNode(cnm.getKeyByValue(map, p), p);
/*      */           
/* 1063 */           Node nr = graph.getNode(cnm.getKeyByValue(map, r), r);
/*      */           
/* 1065 */           graph.addEdge(np, nr, false);
/*      */           
/* 1067 */           np.incr_Outer_degree();
/* 1068 */           nr.incr_Inner_degree();
/*      */         }
/*      */     }
/* 1071 */     System.out.println();
/* 1072 */     System.out.println();
/*      */     
/*      */ 
/*      */ 
/* 1076 */     System.out.println();
/*      */     
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/* 1088 */     System.out.println("GRAFO FOLDED ORIGINALE SOLO LOG");
/*      */     
/* 1090 */     ObjectIntOpenHashMap<String> folded_map = new ObjectIntOpenHashMap();
/* 1091 */     ObjectObjectOpenHashMap<String, ObjectArrayList<String>> folded_attivita_tracce = new ObjectObjectOpenHashMap();
/* 1092 */     ObjectObjectOpenHashMap<String, ObjectArrayList<String>> folded_traccia_attivita = new ObjectObjectOpenHashMap();
/*      */     
/* 1094 */     Graph folded_G_Ori = cnm.getGrafoAggregato(graph, log, true, folded_map, folded_attivita_tracce, 
/* 1095 */       folded_traccia_attivita);
/*      */     
/* 1097 */     System.out.println();
/*      */     
/*      */ 
/*      */ 
/* 1101 */     System.out.println();
/* 1102 */     System.out.println();
/*      */     
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/* 1108 */     boolean vincoli_consistenti = cnm.verifica_consistenza_vincoli(vincoli_positivi, vincoli_negati);
/*      */     
/* 1110 */     if (!vincoli_consistenti) {
/* 1111 */       System.out.println("FALLIMENTO VINCOLI INCONSISTENTI ");
/* 1112 */       System.exit(0);
/*      */     }
/*      */     
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/* 1121 */     if (enable_constraints) {
/* 1122 */       System.out.println("STAMPA PG0 FOLDED");
/*      */       
/* 1124 */       cnm.buildPG0(graph, m, vincoli_positivi_unfolded, vincoli_positivi, vincoli_negati_unfolded, vincoli_negati, lista_forbidden, 
/* 1125 */         lista_forbidden_unfolded, map, (ObjectObjectOpenHashMap)attivita_tracce, traccia_attivita, csm, sigma_low_cs_constr_edges, 
/* 1126 */         folded_G_Ori, folded_map);
/*      */       
/* 1128 */       Graph folded_PG0 = cnm.getGrafoAggregato(graph, log, false, folded_map, folded_attivita_tracce, 
/* 1129 */         folded_traccia_attivita);
/*      */       
/* 1131 */       System.out.println();
/*      */       
/*      */ 
/*      */ 
/* 1135 */       System.out.println();
/* 1136 */       System.out.println();
/*      */       
/* 1138 */       if (!cnm.verificaVincoliPositivi(folded_PG0, null, null, vincoli_positivi, folded_map)) {
/* 1139 */         System.out.println("FALLIMENTO PG0 NON SODDISFA I VINCOLI POSITIVI!");
/* 1140 */         System.exit(0);
/*      */       }
/*      */     }
/*      */     
/* 1144 */     context.getProgress().setValue(30);
/* 1145 */     ObjectArrayList<FakeDependency> attivita_parallele = cnm.getAttivitaParallele(m, graph, map, vincoli_positivi, 
/* 1146 */       folded_map, folded_G_Ori);
/*      */     
/* 1148 */     int counter = 1;
/*      */     
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/* 1156 */     System.out.println();
/* 1157 */     System.out.println("START ALGORITMO 2... ");
/* 1158 */     System.out.println();
/*      */     
/*      */ 
/*      */ 
/* 1162 */     cnm.algoritmo2(m, graph, map, (ObjectObjectOpenHashMap)attivita_tracce, traccia_attivita, csm, sigma_up_cs_diff, folded_map, 
/* 1163 */       lista_forbidden, vincoli_positivi, vincoli_negati);
/* 1164 */     System.out.println();
/*      */     
/* 1166 */     System.out.println("GRAFO DOPO AVER APPLICATO ALGORITMO 2");
/* 1167 */     System.out.println();
/*      */     
/*      */ 
/*      */ 
/* 1171 */     System.out.println("ATTIVITA PARALLELE RESIDUE DOPO ALGORITMO 2...");
/*      */     
/* 1173 */     System.out.println();
/*      */     
/* 1175 */     Graph folded_g = cnm.getGrafoAggregato(graph, log, false, folded_map, folded_attivita_tracce, 
/* 1176 */       folded_traccia_attivita);
/*      */     
/*      */ 
/* 1179 */     for (int ni = 0; ni < graph.listaNodi().size(); ni++) {
/* 1180 */       Node n = (Node)graph.listaNodi().get(ni);
/* 1181 */       n.setMark(false);
/*      */     }
/*      */     
/* 1184 */     ObjectArrayList<FakeDependency> attivita_parallele_residue = cnm.getAttivitaParallele(m, graph, map, 
/* 1185 */       vincoli_positivi, folded_map, folded_g);
/*      */     
/* 1187 */     counter = 1;
/*      */     
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/* 1193 */     System.out.println();
/* 1194 */     System.out.println();
/*      */     
/* 1196 */     for (int jj = 0; jj < folded_g.getLista_archi().size(); jj++)
/*      */     {
/* 1198 */       Edge e = (Edge)folded_g.getLista_archi().get(jj);
/*      */       
/* 1200 */       for (int kk = 0; kk < vincoli_positivi.size(); kk++) {
/* 1201 */         Constraint c = (Constraint)vincoli_positivi.get(kk);
/* 1202 */         if ((c.getBodyList().contains(e.getX().getNomeAttivita())) && (c.getHeadList().contains(e.getY().getNomeAttivita())))
/*      */         {
/*      */ 
/* 1205 */           e.setFlag(true);
/* 1206 */           System.out.println(e + " OK!!!!!!");
/* 1207 */           break;
/*      */         }
/* 1209 */         System.out.println("NOT OK!!!!!!!");
/*      */       }
/*      */     }
/*      */     
/* 1213 */     System.out.println("GRAFO FOLDED ");
/*      */     
/* 1215 */     System.out.println();
/*      */     
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/* 1222 */     double[][] csmOri = cnm.calcoloMatriceDeiCausalScore(log, folded_map, folded_traccia_attivita, ff);
/*      */     
/* 1224 */     System.out.println();
/*      */     
/*      */ 
/*      */ 
/* 1228 */     context.getProgress().setValue(55);
/*      */     
/*      */ 
/*      */ 
/*      */ 
/* 1233 */     System.out.println("POST-PROCESSING RIMOZIONE DIPENDENZE INDIRETTE... ");
/* 1234 */     System.out.println();
/*      */     
/* 1236 */     cnm.postProcessing_dip_indirette(folded_g, folded_map, folded_attivita_tracce, folded_traccia_attivita, csmOri, 
/* 1237 */       sigma_log_noise, vincoli_positivi);
/*      */     
/*      */ 
/*      */ 
/* 1241 */     System.out.println();
/*      */     
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/* 1258 */     Node start = new Node(attivita_iniziale, folded_map.get(attivita_iniziale));
/* 1259 */     Node end = new Node(attivita_finale, folded_map.get(attivita_finale));
/*      */     
/* 1261 */     ObjectArrayList<Node> startActivities = new ObjectArrayList();
/*      */     
/* 1263 */     ObjectArrayList<Node> endActivities = new ObjectArrayList();
/*      */     
/* 1265 */     folded_g = cnm.rimuoviAttivitaFittizie(folded_g, folded_map, folded_traccia_attivita, folded_attivita_tracce, 
/* 1266 */       start, end, log, startActivities, endActivities);
/*      */     
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/* 1280 */     cnm.computeBindings(folded_g, folded_traccia_attivita, folded_map);
/*      */     
/* 1282 */     System.out.println("PROCEDURA REMOVABLE-EDGES ");
/*      */     
/* 1284 */     csmOri = cnm.calcoloMatriceDeiCausalScore(log, folded_map, folded_traccia_attivita, ff);
/*      */     
/*      */ 
/*      */     for (;;)
/*      */     {
/* 1289 */       ObjectArrayList<Edge> removableEdges = cnm.removableEdges(folded_g, csmOri, vincoli_positivi, folded_map, 
/* 1290 */         relative_to_best);
/*      */       
/* 1292 */       if (removableEdges.size() == 0) {
/*      */         break;
/*      */       }
/* 1295 */       Edge bestRemovable = null;
/*      */       
/* 1297 */       double worst_causal_score = Double.MAX_VALUE;
/*      */       
/*      */ 
/*      */ 
/*      */ 
/* 1302 */       for (int jj = 0; jj < removableEdges.size(); jj++)
/*      */       {
/* 1304 */         Edge e = (Edge)removableEdges.get(jj);
/*      */         
/* 1306 */         double e_cs = csmOri[e.getX().getID_attivita()][e.getY().getID_attivita()];
/*      */         
/* 1308 */         if (e_cs < worst_causal_score) {
/* 1309 */           worst_causal_score = e_cs;
/* 1310 */           bestRemovable = e;
/*      */         }
/*      */       }
/*      */       
/* 1314 */       folded_g.removeEdge(bestRemovable.getX(), bestRemovable.getY());
/*      */       
/* 1316 */       if (!cnm.verificaVincoliPositivi(folded_g, null, null, vincoli_positivi, folded_map)) {
/* 1317 */         folded_g.addEdge(bestRemovable.getX(), bestRemovable.getY(), true);
/*      */       }
/*      */       else
/*      */       {
/* 1321 */         System.out.println("RIMOSSO ARCO " + bestRemovable.getX().getNomeAttivita() + " -> " + 
/* 1322 */           bestRemovable.getY().getNomeAttivita());
/*      */         
/*      */ 
/* 1325 */         ObjectIntOpenHashMap<IntOpenHashSet> obX = bestRemovable.getX().getOutput();
/*      */         
/* 1327 */         ObjectIntOpenHashMap<IntOpenHashSet> ibY = bestRemovable.getY().getInput();
/*      */         
/* 1329 */         keys = obX.keys;
/*      */         
/* 1331 */         for (int ts = 0; ts < obX.allocated.length; ts++) {
/* 1332 */           if (obX.allocated[ts] != 0) {
/* 1333 */             IntOpenHashSet tks = (IntOpenHashSet)keys[ts];
/* 1334 */             tks.remove(bestRemovable.getY().getID_attivita());
/*      */           }
/*      */         }
/* 1337 */         keys = ibY.keys;
/*      */         
/* 1339 */         for (int ts = 0; ts < ibY.allocated.length; ts++) {
/* 1340 */           if (ibY.allocated[ts] != 0) {
/* 1341 */             IntOpenHashSet tks = (IntOpenHashSet)keys[ts];
/* 1342 */             tks.remove(bestRemovable.getX().getID_attivita());
/*      */           }
/*      */         }
/*      */         
/*      */ 
/* 1347 */         ObjectIntOpenHashMap<IntArrayList> extendedObX = bestRemovable.getX().getExtendedOutput();
/*      */         
/* 1349 */         ObjectIntOpenHashMap<IntArrayList> extendedIbY = bestRemovable.getY().getExtendedInput();
/*      */         
/* 1351 */         keys = extendedObX.keys;
/*      */         
/* 1353 */         for (int ts = 0; ts < extendedObX.allocated.length; ts++) {
/* 1354 */           if (extendedObX.allocated[ts] != 0) {
/* 1355 */             IntArrayList tks = (IntArrayList)keys[ts];
/* 1356 */             tks.removeAllOccurrences(bestRemovable.getY().getID_attivita());
/*      */           }
/*      */         }
/* 1359 */         keys = extendedIbY.keys;
/*      */         
/* 1361 */         for (int ts = 0; ts < extendedIbY.allocated.length; ts++)
/* 1362 */           if (extendedIbY.allocated[ts] != 0) {
/* 1363 */             IntArrayList tks = (IntArrayList)keys[ts];
/* 1364 */             tks.removeAllOccurrences(bestRemovable.getX().getID_attivita());
/*      */           }
/* 1366 */         removableEdges.removeFirstOccurrence(bestRemovable);
/*      */       }
/*      */     }
/*      */     
/* 1370 */     ObjectArrayList<Node> removableNodes = new ObjectArrayList();
/* 1371 */     for (int jj = 0; jj < folded_g.listaNodi().size(); jj++) {
/* 1372 */       Node n = (Node)folded_g.listaNodi().get(jj);
/* 1373 */       if ((n.getInner_degree() == 0) && (n.getOuter_degree() == 0)) {
/* 1374 */         removableNodes.add(n);
/*      */       }
/*      */     }
/* 1377 */     for (int jj = 0; jj < removableNodes.size(); jj++) {
/* 1378 */       Node removableNode = (Node)removableNodes.get(jj);
/* 1379 */       folded_g.removeNode(removableNode);
/*      */     }
/*      */     
/* 1382 */     CausalNetAnnotations annotations = new CausalNetAnnotations();
/*      */     
/* 1384 */     Flex flexDiagram = FlexFactory.newFlex("Causal Net CNMining");
/*      */     
/* 1386 */     FlexNode[] nodes = new FlexNode[folded_g.listaNodi().size()];
/*      */     
/* 1388 */     System.out.println("nodes length " + nodes.length);
/* 1389 */     System.out.println("graph length " + folded_g.listaNodi().size());
/*      */     
/*      */ 
/* 1392 */     IntIntOpenHashMap flexMap = new IntIntOpenHashMap();
/*      */     
/* 1394 */     int index = 0;
/*      */     
/* 1396 */     String bindingsContent = "<?xml version=\"1.0\" encoding=\"UTF-8\"?>\n<ExtendedCausalNet name=\"" + 
/* 1397 */       ((XAttribute)log.getAttributes().get("concept:name")).toString() + "\"\n" + 
/* 1398 */       "xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\"\n" + 
/* 1399 */       "xsi:noNamespaceSchemaLocation=\"ExtendedCausalNetSchema.xsd\">\n";
/*      */     
/* 1401 */     for (int ii = 0; ii < folded_g.listaNodi().size(); ii++)
/*      */     {
/* 1403 */       Node n = (Node)folded_g.listaNodi().get(ii);
/*      */       
/* 1405 */       flexMap.put(n.getID_attivita(), index);
/*      */       
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/* 1411 */       nodes[index] = flexDiagram.addNode(n.getNomeAttivita());
/*      */       
/* 1413 */       annotations.addNodeInfo(nodes[index], "id", n.getNomeAttivita());
/* 1414 */       index++;
/*      */       
/* 1416 */       bindingsContent = bindingsContent + "<Node name=\"" + n.getNomeAttivita() + "\" id=\"" + n.getID_attivita() + "\">\n" + 
/* 1417 */         "<ExtendedInputBindings>\n";
/*      */       
/* 1419 */       ObjectIntOpenHashMap<IntArrayList> extendedObX = n.getExtendedOutput();
/*      */       
/* 1421 */       ObjectIntOpenHashMap<IntArrayList> extendedIbY = n.getExtendedInput();
/*      */       
/* 1423 */       keys = extendedIbY.keys;
/*      */       
/* 1425 */       for (int ts = 0; ts < extendedIbY.allocated.length; ts++)
/* 1426 */         if (extendedIbY.allocated[ts] != 0) {
/* 1427 */           IntArrayList tks = (IntArrayList)keys[ts];
/* 1428 */           if (tks.size() > 0) {
/* 1429 */             bindingsContent = bindingsContent + "{";
/* 1430 */             for (int i = 0; i < tks.size() - 1; i++) {
/* 1431 */               bindingsContent = bindingsContent + tks.get(i) + ", ";
/*      */             }
/* 1433 */             bindingsContent = bindingsContent + tks.get(tks.size() - 1) + "}\n";
/*      */           }
/*      */         }
/* 1436 */       bindingsContent = bindingsContent + "</ExtendedInputBindings>\n";
/* 1437 */       bindingsContent = bindingsContent + "<ExtendedOutputBindings>\n";
/*      */       
/* 1439 */       keys = extendedObX.keys;
/*      */       
/* 1441 */       for (int ts = 0; ts < extendedObX.allocated.length; ts++)
/* 1442 */         if (extendedObX.allocated[ts] != 0) {
/* 1443 */           IntArrayList tks = (IntArrayList)keys[ts];
/* 1444 */           if (tks.size() > 0) {
/* 1445 */             bindingsContent = bindingsContent + "{";
/* 1446 */             for (int i = 0; i < tks.size() - 1; i++)
/* 1447 */               bindingsContent = bindingsContent + tks.get(i) + ", ";
/* 1448 */             bindingsContent = bindingsContent + tks.get(tks.size() - 1) + "}\n";
/*      */           }
/*      */         }
/* 1451 */       bindingsContent = bindingsContent + "</ExtendedOutputBindings>\n</Node>\n";
/*      */     }
/*      */     
/*      */ 
/*      */ 
/* 1456 */     for (int ii = 0; ii < folded_g.getLista_archi().size(); ii++) {
/* 1457 */       Edge e = (Edge)folded_g.getLista_archi().get(ii);
/*      */       
/* 1459 */       flexDiagram.addArc(nodes[flexMap.get(e.getX().getID_attivita())], 
/* 1460 */         nodes[flexMap.get(e.getY().getID_attivita())]);
/* 1461 */       bindingsContent = bindingsContent + "<Edge src= \"" + e.getX().getID_attivita() + "\" dest= \"" + e.getY().getID_attivita() + "\" /> \n";
/*      */     }
/*      */     
/* 1464 */     bindingsContent = bindingsContent + "</ExtendedCausalNet>\n";
/*      */     
/* 1466 */     File ec = new File("ExtendedCausalNet.xml");
/* 1467 */     if (ec.exists())
/* 1468 */       ec.delete();
/* 1469 */     ec.createNewFile();
/*      */     try {
/* 1471 */       Files.write(FileSystems.getDefault().getPath(".", new String[] { "ExtendedCausalNet.xml" }), bindingsContent.getBytes(), new OpenOption[] {
/* 1472 */         StandardOpenOption.APPEND });
/*      */     } catch (IOException ioe) {
/* 1474 */       ioe.printStackTrace();
/*      */     }
/*      */     
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/* 1498 */     for (int ii = 0; ii < folded_g.listaNodi().size(); ii++)
/*      */     {
/* 1500 */       Node n = (Node)folded_g.listaNodi().get(ii);
/*      */       
/* 1502 */       keys = n.getOutput().keys;
/*      */       
/* 1504 */       for (int ts = 0; ts < n.getOutput().allocated.length; ts++) {
/* 1505 */         if (n.getOutput().allocated[ts] != 0) {
/* 1506 */           IntOpenHashSet se = (IntOpenHashSet)keys[ts];
/*      */           
/* 1508 */           SetFlex set = new SetFlex();
/* 1509 */           for (IntCursor o : se) {
/* 1510 */             set.add(nodes[flexMap.get(o.value)]);
/*      */           }
/* 1512 */           if ((set.size() != 0) || (endActivities.contains(n)))
/* 1513 */             nodes[flexMap.get(n.getID_attivita())].addOutputNodes(set);
/*      */         }
/*      */       }
/* 1516 */       keys = n.getInput().keys;
/*      */       
/* 1518 */       for (int ts = 0; ts < n.getInput().allocated.length; ts++) {
/* 1519 */         if (n.getInput().allocated[ts] != 0) {
/* 1520 */           IntOpenHashSet se = (IntOpenHashSet)keys[ts];
/*      */           
/* 1522 */           SetFlex set = new SetFlex();
/* 1523 */           for (IntCursor i : se) {
/* 1524 */             set.add(nodes[flexMap.get(i.value)]);
/*      */           }
/* 1526 */           if ((set.size() != 0) || (startActivities.contains(n))) {
/* 1527 */             nodes[flexMap.get(n.getID_attivita())].addInputNodes(set);
/*      */           }
/*      */         }
/*      */       }
/*      */     }
/*      */     
/* 1533 */     StartTaskNodesSet startTaskNodes = new StartTaskNodesSet();
/*      */     
/* 1535 */     for (int i = 0; i < startActivities.size(); i++) {
/* 1536 */       Node n = (Node)startActivities.get(i);
/* 1537 */       SetFlex setStart = new SetFlex();
/*      */       
/* 1539 */       setStart.add(nodes[flexMap.get(n.getID_attivita())]);
/*      */       
/* 1541 */       startTaskNodes.add(setStart);
/*      */     }
/*      */     
/*      */ 
/* 1545 */     EndTaskNodesSet endTaskNodes = new EndTaskNodesSet();
/*      */     
/* 1547 */     for (int i = 0; i < endActivities.size(); i++)
/*      */     {
/* 1549 */       Node n = (Node)startActivities.get(i);
/*      */       
/* 1551 */       SetFlex setEnd = new SetFlex();
/*      */       
/* 1553 */       setEnd.add(nodes[flexMap.get(n.getID_attivita())]);
/*      */       
/* 1555 */       endTaskNodes.add(setEnd);
/*      */     }
/*      */     
/* 1558 */     for (int i = 0; i < nodes.length; i++) {
/* 1559 */       nodes[i].commitUpdates();
/*      */     }
/*      */     
/*      */ 
/*      */ 
/* 1564 */     System.out.println();
/*      */     
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/* 1579 */     System.out.println();
/* 1580 */     System.out.println();
/*      */     
/* 1582 */     context.getProgress().setValue(85);
/*      */     
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/* 1618 */     context.getProgress().setValue(100);
/*      */     
/* 1620 */     context.getFutureResult(0).setLabel(flexDiagram.getLabel());
/* 1621 */     context.getFutureResult(1).setLabel("Start tasks node of " + flexDiagram.getLabel());
/* 1622 */     context.getFutureResult(2).setLabel("End tasks node of " + flexDiagram.getLabel());
/* 1623 */     context.getFutureResult(3).setLabel("Annotations of " + flexDiagram.getLabel());
/*      */     
/* 1625 */     context.addConnection(new FlexStartTaskNodeConnection("Start tasks node of " + flexDiagram.getLabel() + 
/* 1626 */       " connection", flexDiagram, startTaskNodes));
/* 1627 */     context.addConnection(new FlexEndTaskNodeConnection("End tasks node of " + flexDiagram.getLabel() + 
/* 1628 */       " connection", flexDiagram, endTaskNodes));
/* 1629 */     context.addConnection(new CausalNetAnnotationsConnection("Annotations of " + flexDiagram.getLabel() + 
/* 1630 */       " connection", flexDiagram, annotations));
/*      */     
/*      */ 
/* 1633 */     visualize(flexDiagram);
/*      */     
/*      */ 
/*      */ 
/* 1637 */     return new Object[] { flexDiagram, startTaskNodes, endTaskNodes, annotations };
/*      */   }
/*      */   
/*      */ 
/*      */   private static ProMJGraph buildJGraph(DirectedGraph<?, ?> causalNet)
/*      */   {
/* 1643 */     ViewSpecificAttributeMap map = new ViewSpecificAttributeMap();
/* 1644 */     GraphLayoutConnection layoutConnection = new GraphLayoutConnection(causalNet);
/*      */     
/* 1646 */     ProMGraphModel model = new ProMGraphModel(causalNet);
/* 1647 */     ProMJGraph jGraph = new ProMJGraph(model, map, layoutConnection);
/*      */     
/* 1649 */     JGraphHierarchicalLayout layout = new JGraphHierarchicalLayout();
/* 1650 */     layout.setDeterministic(false);
/* 1651 */     layout.setCompactLayout(false);
/* 1652 */     layout.setFineTuning(true);
/* 1653 */     layout.setParallelEdgeSpacing(15.0D);
/* 1654 */     layout.setFixRoots(false);
/*      */     
/* 1656 */     layout.setOrientation(((Integer)map.get(causalNet, "ProM_Vis_attr_orientation", Integer.valueOf(5))).intValue());
/*      */     
/* 1658 */     if (!layoutConnection.isLayedOut())
/*      */     {
/* 1660 */       JGraphFacade facade = new JGraphFacade(jGraph);
/*      */       
/* 1662 */       facade.setOrdered(false);
/* 1663 */       facade.setEdgePromotion(true);
/* 1664 */       facade.setIgnoresCellsInGroups(false);
/* 1665 */       facade.setIgnoresHiddenCells(false);
/* 1666 */       facade.setIgnoresUnconnectedCells(false);
/* 1667 */       facade.setDirected(true);
/* 1668 */       facade.resetControlPoints();
/* 1669 */       facade.run(layout, true);
/*      */       
/* 1671 */       Map<?, ?> nested = facade.createNestedMap(true, true);
/*      */       
/* 1673 */       jGraph.getGraphLayoutCache().edit(nested);
/* 1674 */       layoutConnection.setLayedOut(true);
/*      */     }
/*      */     
/* 1677 */     jGraph.setUpdateLayout(layout);
/*      */     
/* 1679 */     layoutConnection.updated();
/*      */     
/* 1681 */     return jGraph;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */   public Graph rimuoviAttivitaFittizie(Graph folded_g, ObjectIntOpenHashMap<String> folded_map, ObjectObjectOpenHashMap<String, ObjectArrayList<String>> traccia_attivita, ObjectObjectOpenHashMap<String, ObjectArrayList<String>> attivita_traccia, Node start, Node end, XLog log, ObjectArrayList<Node> startActivities, ObjectArrayList<Node> endActivities)
/*      */   {
/* 1689 */     ObjectArrayList<Node> startActs = new ObjectArrayList();
/* 1690 */     ObjectArrayList<Node> endActs = new ObjectArrayList();
/*      */     
/* 1692 */     for (int i = 0; i < log.size(); i++)
/*      */     {
/* 1694 */       XTrace trace = (XTrace)log.get(i);
/* 1695 */       trace.remove(0);
/* 1696 */       trace.remove(trace.size() - 1);
/*      */     }
/*      */     
/*      */ 
/* 1700 */     int startID = start.getID_attivita();
/*      */     
/* 1702 */     int endID = end.getID_attivita();
/*      */     
/* 1704 */     attivita_traccia.remove(start.getNomeAttivita());
/* 1705 */     attivita_traccia.remove(end.getNomeAttivita());
/*      */     
/*      */ 
/*      */ 
/* 1709 */     Object[] values = traccia_attivita.values;
/* 1710 */     boolean[] states = traccia_attivita.allocated;
/*      */     
/* 1712 */     for (int iii = 0; iii < states.length; iii++)
/*      */     {
/* 1714 */       if (states[iii] != 0) {
/* 1715 */         ObjectArrayList<String> vals = (ObjectArrayList)values[iii];
/* 1716 */         vals.removeFirstOccurrence(start.getNomeAttivita());
/* 1717 */         vals.removeFirstOccurrence(end.getNomeAttivita());
/*      */       }
/*      */     }
/*      */     
/* 1721 */     for (int ii = 0; ii < folded_g.getLista_archi().size(); ii++) {
/* 1722 */       Edge e = (Edge)folded_g.getLista_archi().get(ii);
/* 1723 */       if (e.getX().equals(start)) {
/* 1724 */         folded_g.getLista_archi().removeAllOccurrences(e);
/* 1725 */         startActs.add(e.getY());
/* 1726 */         folded_g.removeEdge(start, e.getY());
/* 1727 */         e.getY().decr_Inner_degree();
/* 1728 */         ii--;
/*      */       }
/* 1730 */       if (e.getY().equals(end)) {
/* 1731 */         folded_g.getLista_archi().removeAllOccurrences(e);
/* 1732 */         endActs.add(e.getX());
/* 1733 */         folded_g.removeEdge(e.getX(), end);
/*      */         
/* 1735 */         e.getX().decr_Outer_degree();
/* 1736 */         ii--;
/*      */       }
/*      */     }
/*      */     
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/* 1768 */     folded_g.getMap().remove(start);
/* 1769 */     folded_g.getMap().remove(end);
/* 1770 */     folded_g.listaNodi().removeFirstOccurrence(start);
/* 1771 */     folded_g.listaNodi().removeFirstOccurrence(end);
/* 1772 */     folded_map.remove(start.getNomeAttivita());
/* 1773 */     folded_map.remove(end.getNomeAttivita());
/*      */     
/* 1775 */     Graph cleanG = new Graph();
/*      */     Node n;
/* 1777 */     for (int ii = 0; ii < folded_g.listaNodi().size(); ii++) {
/* 1778 */       n = (Node)folded_g.listaNodi().get(ii);
/* 1779 */       if ((n.getID_attivita() > startID) && (n.getID_attivita() < endID))
/*      */       {
/* 1781 */         Node newNode = new Node(n.getNomeAttivita(), n.getID_attivita() - 1);
/*      */         
/* 1783 */         newNode.setInner_degree(n.getInner_degree());
/* 1784 */         newNode.setOuter_degree(n.getOuter_degree());
/* 1785 */         folded_map.remove(n.getNomeAttivita());
/* 1786 */         folded_map.put(newNode.getNomeAttivita(), newNode.getID_attivita());
/* 1787 */         cleanG.getMap().put(newNode, new ObjectOpenHashSet());
/* 1788 */       } else if (n.getID_attivita() > endID)
/*      */       {
/* 1790 */         Node newNode = new Node(n.getNomeAttivita(), n.getID_attivita() - 2);
/* 1791 */         newNode.setInner_degree(n.getInner_degree());
/* 1792 */         newNode.setOuter_degree(n.getOuter_degree());
/* 1793 */         folded_map.remove(n.getNomeAttivita());
/* 1794 */         folded_map.put(newNode.getNomeAttivita(), newNode.getID_attivita());
/* 1795 */         cleanG.getMap().put(newNode, new ObjectOpenHashSet());
/*      */       }
/*      */     }
/*      */     
/*      */ 
/* 1800 */     for (ObjectCursor<Edge> ee : folded_g.getLista_archi()) {
/* 1801 */       Edge e = (Edge)ee.value;
/* 1802 */       cleanG.addEdge(cleanG.getNode(e.getX().getNomeAttivita(), folded_map.get(e.getX().getNomeAttivita())), 
/* 1803 */         cleanG.getNode(e.getY().getNomeAttivita(), folded_map.get(e.getY().getNomeAttivita())), e.isFlag());
/*      */     }
/*      */     
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/* 1811 */     for (ObjectCursor<Node> n : startActs) {
/* 1812 */       if (((Node)n.value).getOuter_degree() > 0) {
/* 1813 */         Node cn = cleanG.getNode(((Node)n.value).getNomeAttivita(), folded_map.get(((Node)n.value).getNomeAttivita()));
/* 1814 */         startActivities.add(cn);
/*      */       }
/*      */     }
/*      */     
/*      */ 
/* 1819 */     for (ObjectCursor<Node> e : endActs) {
/* 1820 */       if (((Node)e.value).getInner_degree() > 0) {
/* 1821 */         Node en = cleanG.getNode(((Node)e.value).getNomeAttivita(), folded_map.get(((Node)e.value).getNomeAttivita()));
/* 1822 */         endActivities.add(en);
/*      */       }
/*      */     }
/*      */     
/* 1826 */     startActs = null;
/* 1827 */     endActs = null;
/* 1828 */     cleanG.listaNodi();
/* 1829 */     return cleanG;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public static void visualize(Flex flex)
/*      */   {
/* 1887 */     CLIContext context = new CLIContext();
/* 1888 */     CLIPluginContext pluginContext = new CLIPluginContext(context, "test");
/* 1889 */     ProMJGraphPanel mainPanel = ProMJGraphVisualizer.instance().visualizeGraph(pluginContext, flex);
/*      */     
/* 1891 */     mainPanel.setSize(new Dimension(500, 500));
/*      */     
/*      */ 
/* 1894 */     ProMJGraph graph = (ProMJGraph)mainPanel.getComponent();
/* 1895 */     graph.setSize(new Dimension(500, 500));
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   private void removeStrangeDependencies(Graph g, ObjectIntOpenHashMap<String> map, ObjectArrayList<Constraint> vincoli_positivi)
/*      */   {
/* 1922 */     for (int ii = 0; ii < g.listaNodi().size(); ii++) {
/* 1923 */       Node n = (Node)g.listaNodi().get(ii);
/* 1924 */       g.removeEdge(n, n);
/* 1925 */       n.decr_Outer_degree();
/* 1926 */       n.decr_Inner_degree();
/* 1927 */       for (int jj = 0; jj < g.adjacentNodes(n).size(); jj++) {
/* 1928 */         Node adjNode = (Node)g.listaNodi().get(jj);
/*      */         
/* 1930 */         if (n.getNomeAttivita().split("_")[1].split("\\+")[0].equals(adjNode.getNomeAttivita().split("_")[0]))
/*      */         {
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/* 1937 */           g.removeEdge(n, adjNode);
/* 1938 */           System.out.println("RIMOSSO ARCO " + n.getNomeAttivita() + " -> " + adjNode.getNomeAttivita());
/*      */           
/* 1940 */           n.decr_Outer_degree();
/* 1941 */           adjNode.decr_Inner_degree();
/*      */         }
/*      */       }
/*      */     }
/*      */     
/* 1946 */     Node pb = new Node("via panebianco_via busento (rende 1o fermata)+complete", 
/* 1947 */       map.get("via panebianco_via busento (rende 1o fermata)+complete"));
/* 1948 */     Node cmf = new Node("corso mazzini_corso fera (clinica sacro cuore)+complete", 
/* 1949 */       map.get("corso mazzini_corso fera (clinica sacro cuore)+complete"));
/* 1950 */     g.removeEdge(pb, cmf);
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public boolean[][] generaAdjacentsMatrix(Graph folded_g)
/*      */   {
/* 2005 */     boolean[][] adjacentsMatrix = new boolean[folded_g.listaNodi().size()][folded_g.listaNodi().size()];
/*      */     
/* 2007 */     for (int i = 0; i < folded_g.listaNodi().size(); i++) {
/* 2008 */       Node n = (Node)folded_g.listaNodi().get(i);
/* 2009 */       for (int j = 0; j < folded_g.adjacentNodes(n).size(); j++) {
/* 2010 */         Node adjacent = (Node)folded_g.adjacentNodes(n).get(j);
/* 2011 */         adjacentsMatrix[n.getID_attivita()][adjacent.getID_attivita()] = 1;
/*      */       }
/*      */     }
/* 2014 */     return adjacentsMatrix;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public boolean verifica_consistenza_vincoli(ObjectArrayList<Constraint> vincoli_positivi, ObjectArrayList<Constraint> vincoli_negati)
/*      */   {
/* 2047 */     for (int i = 0; i < vincoli_positivi.size(); i++) {
/* 2048 */       Constraint c = (Constraint)vincoli_positivi.get(i);
/* 2049 */       for (int j = 0; j < vincoli_negati.size(); j++) {
/* 2050 */         Constraint f = (Constraint)vincoli_negati.get(j);
/* 2051 */         if ((c.equals(f)) && (((c.isPathConstraint()) && (f.isPathConstraint())) || ((!c.isPathConstraint()) && (!f.isPathConstraint()))))
/* 2052 */           return false;
/*      */       }
/*      */     }
/* 2055 */     return true;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */   public void aggiungiAttivitaFittizia(XLog xlog)
/*      */   {
/* 2062 */     XFactory factory = (XFactory)XFactoryRegistry.instance().currentDefault();
/*      */     
/* 2064 */     for (int i = 0; i < xlog.size(); i++)
/*      */     {
/* 2066 */       XTrace trace = (XTrace)xlog.get(i);
/* 2067 */       XEvent activity_first = (XEvent)trace.get(0);
/* 2068 */       XEvent activity_last = (XEvent)trace.get(trace.size() - 1);
/*      */       
/*      */ 
/*      */ 
/* 2072 */       String concept_name = activity_first.getAttributes().get("concept:name");
/*      */       
/*      */ 
/*      */ 
/*      */ 
/* 2077 */       if (concept_name.equals("_START_")) {
/*      */         break;
/*      */       }
/*      */       
/* 2081 */       Date first_activity_ts = XTimeExtension.instance().extractTimestamp(activity_first);
/*      */       
/* 2083 */       XEvent event_first = factory.createEvent();
/*      */       
/* 2085 */       XConceptExtension.instance().assignName(event_first, "_START_");
/* 2086 */       XLifecycleExtension.instance().assignTransition(event_first, "complete");
/*      */       
/* 2088 */       if (first_activity_ts != null) {
/* 2089 */         XTimeExtension.instance().assignTimestamp(event_first, new Date(first_activity_ts.getTime() - 10L));
/*      */       }
/*      */       
/*      */ 
/*      */ 
/* 2094 */       trace.add(0, event_first);
/*      */       
/*      */ 
/*      */ 
/* 2098 */       Date last_activity_ts = XTimeExtension.instance().extractTimestamp(activity_last);
/*      */       
/* 2100 */       XEvent event_last = factory.createEvent();
/*      */       
/* 2102 */       XConceptExtension.instance().assignName(event_last, "_END_");
/* 2103 */       XLifecycleExtension.instance().assignTransition(event_last, "complete");
/*      */       
/* 2105 */       if (last_activity_ts != null) {
/* 2106 */         XTimeExtension.instance().assignTimestamp(event_last, new Date(last_activity_ts.getTime() + 10L));
/*      */       }
/* 2108 */       trace.add(event_last);
/*      */     }
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public void bestEdge(Graph unfolded_g, double[][] m, ObjectArrayList<Constraint> lista_vincoli_positivi_unfolded, ObjectArrayList<Constraint> lista_vincoli_positivi_folded, ObjectArrayList<Constraint> vincoli_negati, ObjectArrayList<Forbidden> lista_forbidden, ObjectArrayList<Forbidden> lista_forbidden_unfolded, ObjectIntOpenHashMap<String> map, ObjectObjectOpenHashMap<String, ObjectArrayList<String>> attivita_tracce, ObjectObjectOpenHashMap<String, ObjectArrayList<String>> traccia_attivita, double[][] csm, double sigma, Graph folded_g, ObjectIntOpenHashMap<String> folded_map)
/*      */   {
/* 2135 */     sigma = -100.0D;
/*      */     
/*      */ 
/*      */ 
/*      */ 
/* 2140 */     for (int i = 0; i < lista_vincoli_positivi_unfolded.size(); i++)
/*      */     {
/*      */ 
/* 2143 */       Constraint vincolo = (Constraint)lista_vincoli_positivi_unfolded.get(i);
/*      */       
/* 2145 */       if (!vincolo.isPathConstraint())
/*      */       {
/*      */ 
/*      */ 
/* 2149 */         String bestBodyNode = "";
/* 2150 */         String bestHeadNode = "";
/* 2151 */         double bestNodeCS = -1.7976931348623157E308D;
/* 2152 */         Iterator localIterator2; for (Iterator localIterator1 = vincolo.getBodyList().iterator(); localIterator1.hasNext(); 
/*      */             
/*      */ 
/*      */ 
/*      */ 
/* 2157 */             localIterator2.hasNext())
/*      */         {
/* 2152 */           String body = (String)localIterator1.next();
/*      */           
/* 2154 */           String activity_x = body;
/*      */           
/*      */ 
/* 2157 */           localIterator2 = vincolo.getHeadList().iterator(); continue;String head = (String)localIterator2.next();
/* 2158 */           String activity_a = head;
/*      */           
/* 2160 */           double currentCS = csm[map.get(activity_x)][map.get(activity_a)];
/* 2161 */           if (currentCS > bestNodeCS) {
/* 2162 */             bestBodyNode = activity_x;
/* 2163 */             bestHeadNode = activity_a;
/* 2164 */             bestNodeCS = currentCS;
/*      */           }
/*      */         }
/*      */         
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/* 2173 */         Node x = new Node(bestBodyNode, map.get(bestBodyNode));
/* 2174 */         Node a = new Node(bestHeadNode, map.get(bestHeadNode));
/*      */         
/* 2176 */         if (!unfolded_g.isConnected(x, a))
/*      */         {
/* 2178 */           if (csm[map.get(bestBodyNode)][map.get(bestHeadNode)] >= sigma) {
/* 2179 */             unfolded_g.addEdge(x, a, true);
/*      */             
/*      */ 
/*      */ 
/* 2183 */             x.incr_Outer_degree();
/* 2184 */             a.incr_Inner_degree();
/*      */           }
/*      */           else {
/* 2187 */             System.out.println("FALLIMENTO!");
/* 2188 */             System.out.println("IMPOSSIBILE AGGIUNGERE ARCO " + x.getNomeAttivita() + " => " + 
/* 2189 */               a.getNomeAttivita());
/*      */           }
/*      */         }
/*      */       }
/*      */     }
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public void bestPath(Graph unfolded_g, double[][] m, ObjectArrayList<Constraint> lista_vincoli_positivi_unfolded, ObjectArrayList<Constraint> lista_vincoli_positivi_folded, ObjectArrayList<Constraint> vincoli_negati, ObjectArrayList<Forbidden> lista_forbidden, ObjectArrayList<Forbidden> lista_forbidden_unfolded, ObjectIntOpenHashMap<String> map, ObjectObjectOpenHashMap<String, ObjectArrayList<String>> attivita_tracce, ObjectObjectOpenHashMap<String, ObjectArrayList<String>> traccia_attivita, double[][] csm, double sigma, Graph folded_g, ObjectIntOpenHashMap<String> folded_map)
/*      */   {
/* 2211 */     sigma = -100.0D;
/*      */     
/*      */ 
/*      */ 
/* 2215 */     Node x = null;
/*      */     
/* 2217 */     Node a = null;
/*      */     
/*      */ 
/* 2220 */     for (int i = 0; i < lista_vincoli_positivi_unfolded.size(); i++)
/*      */     {
/*      */ 
/* 2223 */       Constraint vincolo = (Constraint)lista_vincoli_positivi_unfolded.get(i);
/*      */       
/*      */ 
/*      */ 
/*      */ 
/* 2228 */       if (vincolo.isPathConstraint())
/*      */       {
/*      */ 
/*      */ 
/* 2232 */         String bestBodyNode = "";
/* 2233 */         String bestHeadNode = "";
/* 2234 */         String bestThroughNode = "";
/*      */         
/* 2236 */         double bestPathCS = -1.7976931348623157E308D;
/*      */         
/*      */ 
/*      */ 
/*      */         Iterator localIterator2;
/*      */         
/*      */ 
/* 2243 */         for (Iterator localIterator1 = vincolo.getBodyList().iterator(); localIterator1.hasNext(); 
/*      */             
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/* 2250 */             localIterator2.hasNext())
/*      */         {
/* 2243 */           String body = (String)localIterator1.next();
/*      */           
/* 2245 */           String activity_x = body;
/*      */           
/*      */ 
/* 2248 */           bestBodyNode = activity_x;
/*      */           
/* 2250 */           localIterator2 = vincolo.getHeadList().iterator(); continue;String head = (String)localIterator2.next();
/* 2251 */           String activity_a = head;
/* 2252 */           bestHeadNode = activity_a;
/*      */           
/* 2254 */           x = new Node(bestBodyNode, map.get(bestBodyNode));
/* 2255 */           a = new Node(bestHeadNode, map.get(bestHeadNode));
/*      */           
/* 2257 */           if (unfolded_g.isConnected(x, a)) {
/*      */             break;
/*      */           }
/*      */           
/* 2261 */           for (int ni = 0; ni < unfolded_g.listaNodi().size(); ni++) {
/* 2262 */             Node n = (Node)unfolded_g.listaNodi().get(ni);
/* 2263 */             n.setMark(false);
/*      */           }
/* 2265 */           if (bfs(unfolded_g, x, a, null, null)) {
/*      */             break;
/*      */           }
/*      */         }
/*      */         
/*      */ 
/* 2271 */         for (localIterator1 = vincolo.getBodyList().iterator(); localIterator1.hasNext(); 
/*      */             
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/* 2278 */             localIterator2.hasNext())
/*      */         {
/* 2271 */           String body = (String)localIterator1.next();
/*      */           
/* 2273 */           String activity_x = body;
/*      */           
/*      */ 
/* 2276 */           bestBodyNode = activity_x;
/*      */           
/* 2278 */           localIterator2 = vincolo.getHeadList().iterator(); continue;String head = (String)localIterator2.next();
/* 2279 */           String activity_a = head;
/* 2280 */           bestHeadNode = activity_a;
/*      */           
/* 2282 */           x = new Node(bestBodyNode, map.get(bestBodyNode));
/* 2283 */           a = new Node(bestHeadNode, map.get(bestHeadNode));
/*      */           
/* 2285 */           boolean[] states = map.allocated;
/* 2286 */           Object[] keys = map.keys;
/*      */           
/* 2288 */           for (int ii = 0; ii < states.length; ii++)
/*      */           {
/* 2290 */             if (states[ii] != 0) {
/* 2291 */               String activity_y = (String)keys[ii];
/*      */               
/*      */ 
/* 2294 */               if ((!activity_x.equals(activity_y)) && (!activity_a.equals(activity_y)) && 
/* 2295 */                 (!lista_forbidden_unfolded.contains(new Forbidden(activity_x, activity_y))) && 
/* 2296 */                 (!lista_forbidden_unfolded.contains(new Forbidden(activity_y, activity_a))) && 
/* 2297 */                 (!activity_y.equals(attivita_iniziale + "#0000")) && 
/* 2298 */                 (!activity_y.equals(attivita_finale + "#0000")))
/*      */               {
/* 2300 */                 double currentCS = -Math.log(1.1D - csm[map.get(activity_x)][map.get(activity_y)]) - 
/* 2301 */                   Math.log(1.1D - csm[map.get(activity_y)][map.get(activity_a)]);
/*      */                 
/* 2303 */                 if (currentCS > bestPathCS)
/*      */                 {
/* 2305 */                   bestThroughNode = activity_y;
/*      */                   
/* 2307 */                   bestPathCS = currentCS;
/*      */                 }
/*      */               }
/*      */             }
/*      */           }
/*      */         }
/*      */         
/*      */ 
/* 2315 */         if (bestThroughNode.equals(""))
/*      */         {
/* 2317 */           if (lista_forbidden_unfolded.contains(new Forbidden(bestBodyNode, bestHeadNode)))
/*      */           {
/*      */ 
/*      */ 
/* 2321 */             System.out.println("Impossibile soddisfare il vincolo " + vincolo);
/* 2322 */             System.out.println("Provo con il prossimo set!");
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */           }
/* 2343 */           else if (!unfolded_g.isConnected(x, a))
/*      */           {
/* 2345 */             if (csm[map.get(bestBodyNode)][map.get(bestHeadNode)] >= sigma) {
/* 2346 */               unfolded_g.addEdge(x, a, true);
/*      */               
/*      */ 
/*      */ 
/* 2350 */               x.incr_Outer_degree();
/* 2351 */               a.incr_Inner_degree();
/*      */             } else {
/* 2353 */               System.out.println("FALLIMENTO!");
/* 2354 */               System.out.println("IMPOSSIBILE AGGIUNGERE ARCO " + x.getNomeAttivita() + " => " + 
/* 2355 */                 a.getNomeAttivita());
/*      */ 
/*      */             }
/*      */             
/*      */ 
/*      */           }
/*      */           
/*      */ 
/*      */ 
/*      */         }
/*      */         else
/*      */         {
/*      */ 
/* 2368 */           Node y = new Node(bestThroughNode, map.get(bestThroughNode));
/*      */           
/* 2370 */           if (!unfolded_g.isConnected(x, a))
/*      */           {
/* 2372 */             if (csm[map.get(bestBodyNode)][map.get(bestHeadNode)] >= sigma) {
/* 2373 */               unfolded_g.addEdge(x, a, true);
/*      */               
/*      */ 
/*      */ 
/* 2377 */               x.incr_Outer_degree();
/* 2378 */               a.incr_Inner_degree();
/*      */             } else {
/* 2380 */               System.out.println("FALLIMENTO!");
/* 2381 */               System.out.println("IMPOSSIBILE AGGIUNGERE ARCO " + x.getNomeAttivita() + " => " + 
/* 2382 */                 a.getNomeAttivita());
/*      */               
/* 2384 */               continue;
/*      */             }
/*      */           }
/*      */           
/* 2388 */           if (!unfolded_g.isConnected(x, y))
/*      */           {
/* 2390 */             if (csm[map.get(bestBodyNode)][map.get(bestThroughNode)] >= sigma) {
/* 2391 */               unfolded_g.addEdge(x, y, true);
/*      */               
/*      */ 
/*      */ 
/* 2395 */               x.incr_Outer_degree();
/* 2396 */               y.incr_Inner_degree();
/*      */             } else {
/* 2398 */               System.out.println("FALLIMENTO!");
/* 2399 */               System.out.println("IMPOSSIBILE AGGIUNGERE ARCO " + x.getNomeAttivita() + " => " + 
/* 2400 */                 y.getNomeAttivita());
/* 2401 */               continue;
/*      */             }
/*      */           }
/*      */           
/* 2405 */           if (!unfolded_g.isConnected(y, a))
/*      */           {
/* 2407 */             if (csm[map.get(bestThroughNode)][map.get(bestHeadNode)] >= sigma) {
/* 2408 */               unfolded_g.addEdge(y, a, true);
/*      */               
/*      */ 
/*      */ 
/* 2412 */               y.incr_Outer_degree();
/* 2413 */               a.incr_Inner_degree();
/*      */             } else {
/* 2415 */               System.out.println("FALLIMENTO!");
/* 2416 */               System.out.println("IMPOSSIBILE AGGIUNGERE ARCO " + y.getNomeAttivita() + " => " + 
/* 2417 */                 a.getNomeAttivita());
/*      */             }
/*      */           }
/*      */         }
/*      */       }
/*      */     }
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public void algoritmo2(double[][] m, Graph graph, ObjectIntOpenHashMap<String> map, ObjectObjectOpenHashMap<String, ObjectArrayList<String>> attivita_tracce, ObjectObjectOpenHashMap<String, ObjectArrayList<String>> traccia_attivita, double[][] csm, double sigma_1, ObjectIntOpenHashMap<String> folded_map, ObjectArrayList<Forbidden> lista_forbidden, ObjectArrayList<Constraint> vincoli_positivi, ObjectArrayList<Constraint> vincoli_negati)
/*      */   {
/* 2432 */     ObjectArrayList<FakeDependency> ap_rimosse = new ObjectArrayList();
/* 2433 */     ap_rimosse.trimToSize();
/* 2434 */     int k = 1;
/*      */     
/*      */     for (;;)
/*      */     {
/* 2438 */       Graph folded_g = getGrafoAggregato(graph, null, false, folded_map, null, null);
/*      */       
/* 2440 */       ObjectArrayList<FakeDependency> attivita_parallele = getAttivitaParallele(m, graph, map, vincoli_positivi, 
/* 2441 */         folded_map, folded_g);
/*      */       
/* 2443 */       for (int i = 0; i < ap_rimosse.size(); i++) {
/* 2444 */         attivita_parallele.removeFirstOccurrence((FakeDependency)ap_rimosse.get(i));
/*      */       }
/*      */       
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/* 2458 */       if (attivita_parallele.size() == 0)
/*      */       {
/*      */ 
/* 2461 */         return;
/*      */       }
/*      */       
/*      */ 
/* 2465 */       FakeDependency best_ap = null;
/*      */       
/* 2467 */       double best_causal_score = Double.MAX_VALUE;
/*      */       
/*      */ 
/*      */ 
/*      */ 
/* 2472 */       for (int i = 0; i < attivita_parallele.size(); i++)
/*      */       {
/* 2474 */         FakeDependency current_ap = (FakeDependency)attivita_parallele.get(i);
/*      */         
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/* 2480 */         double current_ap_cs = csm[current_ap.getAttivita_x()][current_ap.getAttivita_y()];
/*      */         
/* 2482 */         if (current_ap_cs < best_causal_score) {
/* 2483 */           best_causal_score = current_ap_cs;
/* 2484 */           best_ap = current_ap;
/*      */         }
/*      */       }
/*      */       
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/* 2498 */       Node nx = graph.getNode(getKeyByValue(map, best_ap.getAttivita_x()), best_ap.getAttivita_x());
/*      */       
/* 2500 */       Node ny = graph.getNode(getKeyByValue(map, best_ap.getAttivita_y()), best_ap.getAttivita_y());
/*      */       
/*      */ 
/* 2503 */       graph.removeEdge(nx, ny);
/* 2504 */       m[best_ap.getAttivita_x()][best_ap.getAttivita_y()] = 0.0D;
/*      */       
/* 2506 */       nx.decr_Outer_degree();
/* 2507 */       ny.decr_Inner_degree();
/*      */       
/*      */ 
/*      */ 
/*      */ 
/* 2512 */       ObjectOpenHashSet<String> lista_candidati_best_pred = null;
/*      */       
/*      */ 
/* 2515 */       lista_candidati_best_pred = bestPred_Folded(ny.getID_attivita(), nx.getID_attivita(), map, attivita_tracce, 
/* 2516 */         traccia_attivita);
/*      */       
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/* 2525 */       String best_pred = attivita_iniziale + "#" + String.format("%04d", new Object[] { Integer.valueOf(0) });
/*      */       
/*      */ 
/* 2528 */       if (lista_candidati_best_pred != null)
/*      */       {
/* 2530 */         if (lista_candidati_best_pred.size() > 0)
/*      */         {
/* 2532 */           ObjectArrayList<String> lista_candidati_best_pred_unfolded = new ObjectArrayList();
/* 2533 */           Object[] keys = lista_candidati_best_pred.keys;
/*      */           
/* 2535 */           for (int i = 0; i < lista_candidati_best_pred.allocated.length; i++) {
/* 2536 */             if (lista_candidati_best_pred.allocated[i] != 0) {
/* 2537 */               String activity = (String)keys[i];
/* 2538 */               String best_unfolded_item = "";
/* 2539 */               double best_unfolded_cs = -1.0D;
/*      */               
/* 2541 */               keys = map.keys;
/* 2542 */               boolean[] values = map.allocated;
/*      */               
/* 2544 */               for (int j = 0; j < values.length; j++) {
/* 2545 */                 if (values[j] != 0) {
/* 2546 */                   String unfolded_item = (String)keys[j];
/*      */                   
/* 2548 */                   if (unfolded_item != null)
/*      */                   {
/*      */ 
/* 2551 */                     if ((unfolded_item.split("#")[0].equals(activity)) && 
/* 2552 */                       (csm[map.get(unfolded_item)][ny.getID_attivita()] > best_unfolded_cs)) {
/* 2553 */                       best_unfolded_item = unfolded_item;
/* 2554 */                       best_unfolded_cs = csm[map.get(unfolded_item)][ny.getID_attivita()];
/*      */                     } }
/*      */                 }
/*      */               }
/* 2558 */               lista_candidati_best_pred_unfolded.add(best_unfolded_item);
/*      */             }
/*      */           }
/*      */           
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/* 2566 */           best_pred = getFinalBestPred(graph, csm, ny, map, lista_candidati_best_pred_unfolded, 
/* 2567 */             vincoli_negati, lista_forbidden, folded_g, folded_map, false);
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */         }
/*      */         else
/*      */         {
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/* 2662 */           System.out.println("FALLIMENTO BEST PRED NON TROVATO!!!");
/*      */         }
/*      */       }
/*      */       
/*      */ 
/*      */ 
/* 2668 */       ObjectOpenHashSet<String> lista_candidati_best_succ = null;
/*      */       
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/* 2674 */       lista_candidati_best_succ = bestSucc_Folded(best_ap.getAttivita_x(), best_ap.getAttivita_y(), map, 
/* 2675 */         attivita_tracce, traccia_attivita);
/*      */       
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/* 2686 */       String best_succ = attivita_finale + "#" + String.format("%04d", new Object[] { Integer.valueOf(0) });
/*      */       
/*      */ 
/* 2689 */       if (lista_candidati_best_succ != null) {
/* 2690 */         if (lista_candidati_best_succ.size() > 0)
/*      */         {
/* 2692 */           ObjectArrayList<String> lista_candidati_best_succ_unfolded = new ObjectArrayList();
/*      */           
/*      */ 
/* 2695 */           Iterator<ObjectCursor<String>> it = lista_candidati_best_succ.iterator();
/* 2696 */           while (it.hasNext())
/*      */           {
/* 2698 */             String activity = (String)((ObjectCursor)it.next()).value;
/*      */             
/* 2700 */             String best_unfolded_item = "";
/* 2701 */             double best_unfolded_cs = -1.0D;
/*      */             
/* 2703 */             boolean[] states = map.allocated;
/*      */             
/* 2705 */             Object[] keys = map.keys;
/* 2706 */             for (int j = 0; j < states.length; j++)
/*      */             {
/* 2708 */               if (states[j] != 0) {
/* 2709 */                 String unfolded_item = (String)keys[j];
/* 2710 */                 if (unfolded_item != null)
/*      */                 {
/* 2712 */                   if ((unfolded_item.split("#")[0].equals(activity)) && 
/* 2713 */                     (csm[nx.getID_attivita()][map.get(unfolded_item)] > best_unfolded_cs)) {
/* 2714 */                     best_unfolded_item = unfolded_item;
/* 2715 */                     best_unfolded_cs = csm[nx.getID_attivita()][map.get(unfolded_item)];
/*      */                   } }
/*      */               }
/*      */             }
/* 2719 */             if (best_unfolded_item.equals("")) {
/* 2720 */               System.out.println(activity);
/* 2721 */               System.out.println("errore best succ ");
/* 2722 */               throw new RuntimeException("ciao");
/*      */             }
/* 2724 */             lista_candidati_best_succ_unfolded.add(best_unfolded_item);
/*      */           }
/*      */           
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/* 2734 */           best_succ = getFinalBestSucc(graph, csm, nx, map, lista_candidati_best_succ_unfolded, 
/* 2735 */             vincoli_negati, lista_forbidden, folded_g, folded_map, false);
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */         }
/*      */         else
/*      */         {
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/* 2821 */           System.out.println("FALLIMENTO BEST SUCC NON TROVATO!!!");
/*      */         }
/*      */       }
/*      */       
/*      */ 
/* 2826 */       if (!best_pred.equals(""))
/*      */       {
/*      */ 
/*      */ 
/* 2830 */         Node nz = graph.getNode(getKeyByValue(map, map.get(best_pred)), map.get(best_pred));
/*      */         
/*      */ 
/* 2833 */         if (!graph.isConnected(nz, ny))
/*      */         {
/* 2835 */           m[map.get(best_pred)][best_ap.getAttivita_y()] = 1.0D;
/* 2836 */           graph.addEdge(nz, ny, false);
/*      */           
/*      */ 
/* 2839 */           nz.incr_Outer_degree();
/* 2840 */           ny.incr_Inner_degree();
/*      */         }
/*      */       }
/*      */       
/*      */ 
/* 2845 */       if (!best_succ.equals(""))
/*      */       {
/*      */ 
/* 2848 */         Node nw = graph.getNode(getKeyByValue(map, map.get(best_succ)), map.get(best_succ));
/*      */         
/*      */ 
/* 2851 */         System.out.println();
/* 2852 */         if (!graph.isConnected(nx, nw)) {
/* 2853 */           m[best_ap.getAttivita_x()][map.get(best_succ)] = 1.0D;
/* 2854 */           graph.addEdge(nx, nw, false);
/*      */           
/*      */ 
/* 2857 */           nx.incr_Outer_degree();
/* 2858 */           nw.incr_Inner_degree();
/*      */         }
/*      */       }
/*      */       
/*      */ 
/* 2863 */       ap_rimosse.add(best_ap);
/*      */       
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/* 2870 */       if (graph.isConnected(ny, nx))
/*      */       {
/*      */ 
/*      */ 
/*      */ 
/* 2875 */         boolean soddisfa_vincoli_positivi = verificaVincoliPositivi(
/* 2876 */           folded_g, 
/* 2877 */           folded_g.getNode(ny.getNomeAttivita().split("#")[0], 
/* 2878 */           folded_map.get(ny.getNomeAttivita().split("#")[0])), 
/* 2879 */           folded_g.getNode(nx.getNomeAttivita().split("#")[0], 
/* 2880 */           folded_map.get(nx.getNomeAttivita().split("#")[0])), vincoli_positivi, folded_map);
/*      */         
/*      */ 
/*      */ 
/*      */ 
/* 2885 */         if (soddisfa_vincoli_positivi) {
/* 2886 */           System.out.println();
/* 2887 */           FakeDependency best_ap_yx = new FakeDependency(ny.getID_attivita(), nx.getID_attivita());
/*      */           
/* 2889 */           graph.removeEdge(ny, nx);
/* 2890 */           m[best_ap.getAttivita_y()][best_ap.getAttivita_x()] = 0.0D;
/*      */           
/* 2892 */           ny.decr_Outer_degree();
/* 2893 */           nx.decr_Inner_degree();
/*      */           
/*      */ 
/*      */ 
/*      */ 
/* 2898 */           ObjectOpenHashSet<String> lista_candidati_best_pred_yx = null;
/*      */           
/*      */ 
/* 2901 */           lista_candidati_best_pred_yx = bestPred_Folded(nx.getID_attivita(), ny.getID_attivita(), map, 
/* 2902 */             attivita_tracce, traccia_attivita);
/*      */           
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/* 2911 */           String best_pred_yx = attivita_iniziale + "#" + String.format("%04d", new Object[] { Integer.valueOf(0) });
/*      */           
/*      */ 
/* 2914 */           if (lista_candidati_best_pred_yx != null)
/*      */           {
/* 2916 */             if (lista_candidati_best_pred_yx.size() > 0)
/*      */             {
/* 2918 */               ObjectArrayList<String> lista_candidati_best_pred_unfolded = new ObjectArrayList();
/*      */               
/*      */ 
/*      */ 
/* 2922 */               Iterator<ObjectCursor<String>> it = lista_candidati_best_pred_yx.iterator();
/* 2923 */               while (it.hasNext())
/*      */               {
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/* 2929 */                 String activity = (String)((ObjectCursor)it.next()).value;
/* 2930 */                 String best_unfolded_item = "";
/* 2931 */                 double best_unfolded_cs = -1.0D;
/*      */                 
/* 2933 */                 boolean[] states = map.allocated;
/* 2934 */                 Object[] keys = map.keys;
/*      */                 
/* 2936 */                 for (int j = 0; j < states.length; j++) {
/* 2937 */                   if (states[j] != 0) {
/* 2938 */                     String unfolded_item = (String)keys[j];
/* 2939 */                     if (unfolded_item != null)
/*      */                     {
/* 2941 */                       if ((unfolded_item.split("#")[0].equals(activity)) && 
/* 2942 */                         (csm[map.get(unfolded_item)][nx.getID_attivita()] > best_unfolded_cs)) {
/* 2943 */                         best_unfolded_item = unfolded_item;
/* 2944 */                         best_unfolded_cs = csm[map.get(unfolded_item)][nx.getID_attivita()];
/*      */                       } }
/*      */                   }
/*      */                 }
/* 2948 */                 if (!best_unfolded_item.equals("")) {
/* 2949 */                   lista_candidati_best_pred_unfolded.add(best_unfolded_item);
/*      */                 }
/*      */               }
/*      */               
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/* 2957 */               best_pred_yx = getFinalBestPred(graph, csm, nx, map, lista_candidati_best_pred_unfolded, 
/* 2958 */                 vincoli_negati, lista_forbidden, folded_g, folded_map, false);
/*      */             }
/*      */             else {
/* 2961 */               System.out.println("FALLIMENTO BEST PRED YX NON TROVATO!!!");
/*      */             }
/*      */           }
/*      */           
/*      */ 
/* 2966 */           ObjectOpenHashSet<String> lista_candidati_best_succ_yx = null;
/*      */           
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/* 2973 */           lista_candidati_best_succ_yx = bestSucc_Folded(best_ap.getAttivita_y(), best_ap.getAttivita_x(), 
/* 2974 */             map, attivita_tracce, traccia_attivita);
/*      */           
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/* 2984 */           String best_succ_yx = attivita_finale + "#" + String.format("%04d", new Object[] { Integer.valueOf(0) });
/*      */           
/*      */ 
/* 2987 */           if (lista_candidati_best_succ_yx != null) {
/* 2988 */             if (lista_candidati_best_succ_yx.size() > 0)
/*      */             {
/* 2990 */               ObjectArrayList<String> lista_candidati_best_succ_unfolded = new ObjectArrayList();
/*      */               
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/* 2998 */               Iterator<ObjectCursor<String>> it = lista_candidati_best_succ.iterator();
/* 2999 */               while (it.hasNext())
/*      */               {
/* 3001 */                 String activity = (String)((ObjectCursor)it.next()).value;
/* 3002 */                 String best_unfolded_item = "";
/* 3003 */                 double best_unfolded_cs = -1.0D;
/*      */                 
/* 3005 */                 Object[] keys = map.keys;
/*      */                 
/* 3007 */                 boolean[] states = map.allocated;
/*      */                 
/* 3009 */                 for (int j = 0; j < states.length; j++)
/*      */                 {
/* 3011 */                   if (states[j] != 0) {
/* 3012 */                     String unfolded_item = (String)keys[j];
/*      */                     
/* 3014 */                     if (unfolded_item != null)
/*      */                     {
/*      */ 
/* 3017 */                       if ((unfolded_item.split("#")[0].equals(activity)) && 
/* 3018 */                         (csm[ny.getID_attivita()][map.get(unfolded_item)] > best_unfolded_cs)) {
/* 3019 */                         best_unfolded_item = unfolded_item;
/* 3020 */                         best_unfolded_cs = csm[ny.getID_attivita()][map.get(unfolded_item)];
/*      */                       } }
/*      */                   }
/*      */                 }
/* 3024 */                 if (!best_unfolded_item.equals("")) {
/* 3025 */                   lista_candidati_best_succ_unfolded.add(best_unfolded_item);
/*      */                 }
/*      */               }
/*      */               
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/* 3034 */               best_succ_yx = getFinalBestSucc(graph, csm, ny, map, lista_candidati_best_succ_unfolded, 
/* 3035 */                 vincoli_negati, lista_forbidden, folded_g, folded_map, false);
/*      */             }
/*      */             else {
/* 3038 */               System.out.println("FALLIMENTO BEST SUCC YX NON TROVATO!!!");
/*      */             }
/*      */           }
/*      */           
/*      */ 
/* 3043 */           if (!best_pred_yx.equals(""))
/*      */           {
/*      */ 
/*      */ 
/*      */ 
/* 3048 */             Node nz = graph.getNode(getKeyByValue(map, map.get(best_pred_yx)), map.get(best_pred_yx));
/*      */             
/*      */ 
/* 3051 */             if (!graph.isConnected(nz, nx))
/*      */             {
/* 3053 */               m[map.get(best_pred_yx)][best_ap.getAttivita_x()] = 1.0D;
/* 3054 */               graph.addEdge(nz, nx, false);
/*      */               
/*      */ 
/* 3057 */               nz.incr_Outer_degree();
/* 3058 */               nx.incr_Inner_degree();
/*      */             }
/*      */           }
/*      */           
/*      */ 
/* 3063 */           if (!best_succ_yx.equals(""))
/*      */           {
/*      */ 
/*      */ 
/*      */ 
/* 3068 */             Node nw = graph.getNode(getKeyByValue(map, map.get(best_succ_yx)), map.get(best_succ_yx));
/*      */             
/*      */ 
/*      */ 
/* 3072 */             if (!graph.isConnected(ny, nw)) {
/* 3073 */               m[best_ap.getAttivita_y()][map.get(best_succ)] = 1.0D;
/* 3074 */               graph.addEdge(ny, nw, false);
/*      */               
/*      */ 
/* 3077 */               ny.incr_Outer_degree();
/* 3078 */               nw.incr_Inner_degree();
/*      */             }
/*      */           }
/*      */           
/*      */ 
/* 3083 */           ap_rimosse.add(best_ap_yx);
/*      */         }
/*      */       }
/*      */     }
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public ObjectOpenHashSet<String> bestPred_Folded(int x, int y, ObjectIntOpenHashMap<String> map, ObjectObjectOpenHashMap<String, ObjectArrayList<String>> attivita_tracce, ObjectObjectOpenHashMap<String, ObjectArrayList<String>> traccia_attivita)
/*      */   {
/* 3095 */     String attivita_x = getKeyByValue(map, x);
/*      */     
/* 3097 */     String attivita_y = getKeyByValue(map, y);
/*      */     
/* 3099 */     ObjectArrayList<String> lista_tracce_x = new ObjectArrayList((ObjectContainer)attivita_tracce.get(attivita_x));
/*      */     
/* 3101 */     ObjectOpenHashSet<String> lista_tracce_y = new ObjectOpenHashSet((ObjectContainer)attivita_tracce.get(attivita_y));
/*      */     
/* 3103 */     lista_tracce_x.retainAll(lista_tracce_y);
/*      */     
/*      */ 
/*      */ 
/* 3107 */     ObjectOpenHashSet<String> attivita_candidate = null;
/*      */     
/* 3109 */     String trace_1 = "";
/*      */     
/* 3111 */     if (lista_tracce_x.size() > 0)
/*      */     {
/* 3113 */       trace_1 = (String)lista_tracce_x.get(0);
/* 3114 */       attivita_candidate = getPredecessors_FoldedLocal(trace_1, attivita_x, attivita_y, traccia_attivita);
/*      */     } else {
/* 3116 */       attivita_candidate = new ObjectOpenHashSet();
/* 3117 */       attivita_candidate.add(attivita_iniziale);
/*      */     }
/*      */     
/*      */ 
/*      */ 
/* 3122 */     for (int i = 1; i < lista_tracce_x.size(); i++)
/*      */     {
/* 3124 */       String trace = (String)lista_tracce_x.get(i);
/*      */       
/* 3126 */       ObjectOpenHashSet<String> predecessors = getPredecessors_FoldedLocal(trace, attivita_x, attivita_y, traccia_attivita);
/*      */       
/* 3128 */       attivita_candidate.retainAll(predecessors);
/*      */     }
/*      */     
/*      */ 
/* 3132 */     return attivita_candidate;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */   public ObjectOpenHashSet<String> bestSucc_Folded(int x, int y, ObjectIntOpenHashMap<String> map, ObjectObjectOpenHashMap<String, ObjectArrayList<String>> attivita_tracce, ObjectObjectOpenHashMap<String, ObjectArrayList<String>> traccia_attivita)
/*      */   {
/* 3139 */     String attivita_x = getKeyByValue(map, x);
/*      */     
/* 3141 */     String attivita_y = getKeyByValue(map, y);
/*      */     
/* 3143 */     ObjectArrayList<String> lista_tracce_x = new ObjectArrayList((ObjectContainer)attivita_tracce.get(attivita_x));
/*      */     
/* 3145 */     ObjectOpenHashSet<String> lista_tracce_y = new ObjectOpenHashSet((ObjectContainer)attivita_tracce.get(attivita_y));
/*      */     
/* 3147 */     lista_tracce_x.retainAll(lista_tracce_y);
/*      */     
/*      */ 
/*      */ 
/* 3151 */     ObjectOpenHashSet<String> attivita_candidate = null;
/*      */     
/* 3153 */     String trace_1 = "";
/*      */     
/* 3155 */     if (lista_tracce_x.size() > 0)
/*      */     {
/* 3157 */       trace_1 = (String)lista_tracce_x.get(0);
/* 3158 */       attivita_candidate = getSuccessors_FoldedLocal(trace_1, attivita_x, attivita_y, traccia_attivita);
/*      */     } else {
/* 3160 */       attivita_candidate = new ObjectOpenHashSet();
/* 3161 */       attivita_candidate.add(attivita_finale);
/*      */     }
/*      */     
/*      */ 
/*      */ 
/* 3166 */     int i = 1;
/* 3167 */     while (i < lista_tracce_x.size())
/*      */     {
/* 3169 */       String trace = (String)lista_tracce_x.get(i);
/*      */       
/* 3171 */       ObjectOpenHashSet<String> successors = getSuccessors_FoldedLocal(trace, attivita_x, attivita_y, traccia_attivita);
/*      */       
/* 3173 */       attivita_candidate.retainAll(successors);
/* 3174 */       i++;
/*      */     }
/*      */     
/* 3177 */     return attivita_candidate;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */   private boolean bfs(Graph graph, Node x, Node y, Node f, ObjectArrayList<Node> path)
/*      */   {
/* 3184 */     boolean atLeastOnePath = false;
/*      */     
/* 3186 */     if (x.equals(y)) {
/* 3187 */       if (graph.isConnected(x, y))
/* 3188 */         return true;
/* 3189 */       if (path == null)
/* 3190 */         path = new ObjectArrayList();
/*      */     }
/* 3192 */     ObjectArrayList<Node> nodes = new ObjectArrayList();
/* 3193 */     nodes.add(x);
/* 3194 */     x.setMark(true);
/*      */     Node t;
/* 3196 */     int i; for (; !nodes.isEmpty(); 
/*      */         
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/* 3210 */         i < graph.adjacentNodes(t).size())
/*      */     {
/* 3197 */       t = (Node)nodes.remove(0);
/* 3198 */       if (path != null) {
/* 3199 */         path.add(t);
/*      */       }
/* 3201 */       if (t.equals(y)) {
/* 3202 */         if (x.equals(y)) {
/* 3203 */           if (path.size() > 1) {
/* 3204 */             atLeastOnePath = true;
/*      */           }
/*      */         } else {
/* 3207 */           atLeastOnePath = true;
/*      */         }
/*      */       }
/* 3210 */       i = 0; continue;
/* 3211 */       Node k = (Node)graph.adjacentNodes(t).get(i);
/* 3212 */       if ((!k.isMarked()) && (!k.equals(f))) {
/* 3213 */         k.setMark(true);
/* 3214 */         nodes.add(k);
/*      */       }
/* 3210 */       i++;
/*      */     }
/*      */     
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/* 3219 */     return atLeastOnePath;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */   public double[][] buildNextMatrix(XLog log, ObjectIntOpenHashMap<String> map, ObjectObjectOpenHashMap<String, ObjectArrayList<String>> traccia_attivita)
/*      */   {
/* 3226 */     double[][] mNext = new double[map.size()][map.size()];
/*      */     
/* 3228 */     Object[] values = traccia_attivita.values;
/*      */     
/* 3230 */     for (int i = 0; i < traccia_attivita.allocated.length; i++) {
/* 3231 */       if (traccia_attivita.allocated[i] != 0)
/*      */       {
/* 3233 */         ObjectArrayList<String> value = (ObjectArrayList)values[i];
/*      */         
/* 3235 */         String activity_x = "";
/* 3236 */         if (value.size() > 0) {
/* 3237 */           activity_x = (String)value.get(0);
/*      */         }
/* 3239 */         int j = 1;
/* 3240 */         while (j < value.size())
/*      */         {
/*      */ 
/* 3243 */           String activity_y = (String)value.get(j);
/*      */           
/*      */ 
/* 3246 */           int x = map.get(activity_x);
/* 3247 */           int y = map.get(activity_y);
/* 3248 */           mNext[x][y] += 1.0D;
/*      */           
/* 3250 */           activity_x = activity_y;
/* 3251 */           j++;
/*      */         }
/*      */       }
/*      */     }
/*      */     
/* 3256 */     return mNext;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public double[][] buildBestNextMatrix(XLog log, ObjectIntOpenHashMap<String> map, ObjectObjectOpenHashMap<String, ObjectArrayList<String>> traccia_attivita, double[][] cs, ObjectArrayList<Forbidden> lista_forbidden_unfolded)
/*      */   {
/* 3265 */     double[][] mNext = new double[map.size()][map.size()];
/*      */     
/* 3267 */     Object[] values = traccia_attivita.values;
/* 3268 */     for (int i = 0; i < traccia_attivita.allocated.length; i++) {
/* 3269 */       if (traccia_attivita.allocated[i] != 0)
/*      */       {
/* 3271 */         ObjectArrayList<String> value = (ObjectArrayList)values[i];
/*      */         
/*      */ 
/* 3274 */         ObjectArrayList<String> predecessors = new ObjectArrayList();
/* 3275 */         ObjectArrayList<String> successors = new ObjectArrayList(value);
/*      */         
/* 3277 */         int count = 0;
/*      */         
/*      */ 
/* 3280 */         int j = 0;
/* 3281 */         while (j < value.size())
/*      */         {
/*      */ 
/* 3284 */           String activity_x = (String)value.get(j);
/*      */           
/* 3286 */           successors.removeFirstOccurrence(activity_x);
/*      */           
/* 3288 */           String bestPred = "";
/*      */           
/* 3290 */           String bestSucc = "";
/*      */           
/* 3292 */           double bestPredCS = Double.MIN_VALUE;
/* 3293 */           double bestSuccCS = Double.MIN_VALUE;
/*      */           
/* 3295 */           if (predecessors.size() > 0)
/*      */           {
/* 3297 */             int itPred = 0;
/* 3298 */             Object[] buffer = predecessors.buffer;
/* 3299 */             while (itPred < predecessors.size()) {
/* 3300 */               String pred = (String)buffer[itPred];
/*      */               
/* 3302 */               double predCS = cs[map.get(pred)][map.get(activity_x)];
/*      */               
/* 3304 */               if ((predCS > bestPredCS) && (!lista_forbidden_unfolded.contains(new Forbidden(pred, activity_x)))) {
/* 3305 */                 bestPred = pred;
/* 3306 */                 bestPredCS = predCS;
/*      */               }
/* 3308 */               itPred++;
/*      */             }
/*      */             
/* 3311 */             int x = map.get(bestPred);
/* 3312 */             int y = map.get(activity_x);
/* 3313 */             mNext[x][y] += 1.0D;
/*      */           }
/*      */           
/* 3316 */           if (successors.size() > 0)
/*      */           {
/* 3318 */             int itSucc = 0;
/* 3319 */             Object[] buffer = successors.buffer;
/* 3320 */             while (itSucc < successors.size()) {
/* 3321 */               String succ = (String)buffer[itSucc];
/* 3322 */               double succCS = cs[map.get(activity_x)][map.get(succ)];
/*      */               
/* 3324 */               if ((succCS > bestSuccCS) && (!lista_forbidden_unfolded.contains(new Forbidden(activity_x, succ)))) {
/* 3325 */                 bestSucc = succ;
/* 3326 */                 bestSuccCS = succCS;
/*      */               }
/* 3328 */               itSucc++;
/*      */             }
/*      */             
/* 3331 */             int x = map.get(activity_x);
/*      */             
/*      */ 
/*      */ 
/* 3335 */             int y = map.get(bestSucc);
/*      */             
/* 3337 */             mNext[x][y] += 1.0D;
/*      */           }
/*      */           
/* 3340 */           predecessors.add(activity_x);
/* 3341 */           j++;
/*      */         }
/*      */       }
/*      */     }
/*      */     
/* 3346 */     return mNext;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public void buildPG0(Graph unfolded_g, double[][] m, ObjectArrayList<Constraint> lista_vincoli_positivi_unfolded, ObjectArrayList<Constraint> lista_vincoli_positivi_folded, ObjectArrayList<Constraint> vincoli_negati_unfolded, ObjectArrayList<Constraint> vincoli_negati_folded, ObjectArrayList<Forbidden> lista_forbidden, ObjectArrayList<Forbidden> lista_forbidden_unfolded, ObjectIntOpenHashMap<String> map, ObjectObjectOpenHashMap<String, ObjectArrayList<String>> attivita_tracce, ObjectObjectOpenHashMap<String, ObjectArrayList<String>> traccia_attivita, double[][] csm, double sigma, Graph folded_g, ObjectIntOpenHashMap<String> folded_map)
/*      */   {
/* 3357 */     boolean flag = false;
/*      */     
/* 3359 */     if (lista_vincoli_positivi_folded.size() == 0) {
/* 3360 */       flag = true;
/* 3361 */       Object[] buffer = vincoli_negati_folded.buffer;
/* 3362 */       for (int i = 0; i < vincoli_negati_folded.size(); i++) {
/* 3363 */         Constraint c = (Constraint)buffer[i];
/* 3364 */         if (!c.isPathConstraint()) {
/* 3365 */           flag = false;
/* 3366 */           break;
/*      */         }
/*      */       }
/*      */     }
/*      */     
/* 3371 */     if (!flag)
/*      */     {
/*      */ 
/* 3374 */       bestEdge(unfolded_g, m, lista_vincoli_positivi_unfolded, lista_vincoli_positivi_folded, vincoli_negati_folded, 
/* 3375 */         lista_forbidden, lista_forbidden_unfolded, map, attivita_tracce, traccia_attivita, csm, sigma, 
/* 3376 */         folded_g, folded_map);
/*      */       
/* 3378 */       bestPath(unfolded_g, m, lista_vincoli_positivi_unfolded, lista_vincoli_positivi_folded, vincoli_negati_folded, 
/* 3379 */         lista_forbidden, lista_forbidden_unfolded, map, attivita_tracce, traccia_attivita, csm, sigma, 
/* 3380 */         folded_g, folded_map);
/*      */       
/*      */ 
/* 3383 */       eliminaForbidden(unfolded_g, lista_forbidden_unfolded, lista_forbidden, map, m, csm, attivita_tracce, 
/* 3384 */         traccia_attivita, lista_vincoli_positivi_folded, vincoli_negati_folded, folded_g, folded_map);
/*      */     }
/*      */     else
/*      */     {
/* 3388 */       System.out.println("SECONDO ALGORITMO ");
/* 3389 */       noPathConstraints(unfolded_g, m, lista_vincoli_positivi_unfolded, lista_vincoli_positivi_folded, vincoli_negati_unfolded, vincoli_negati_folded, 
/* 3390 */         lista_forbidden, lista_forbidden_unfolded, map, attivita_tracce, traccia_attivita, csm, sigma, 
/* 3391 */         folded_g, folded_map);
/*      */     }
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public void noPathConstraints(Graph unfolded_g, double[][] m, ObjectArrayList<Constraint> lista_vincoli_positivi_unfolded, ObjectArrayList<Constraint> lista_vincoli_positivi_folded, ObjectArrayList<Constraint> vincoli_negati_unfolded, ObjectArrayList<Constraint> vincoli_negati, ObjectArrayList<Forbidden> lista_forbidden, ObjectArrayList<Forbidden> lista_forbidden_unfolded, ObjectIntOpenHashMap<String> map, ObjectObjectOpenHashMap<String, ObjectArrayList<String>> attivita_tracce, ObjectObjectOpenHashMap<String, ObjectArrayList<String>> traccia_attivita, double[][] csm, double sigma, Graph folded_g, ObjectIntOpenHashMap<String> folded_map)
/*      */   {
/* 3415 */     Object[] buffer = lista_forbidden_unfolded.buffer;
/* 3416 */     for (int k = 0; k < lista_forbidden_unfolded.size(); k++)
/*      */     {
/* 3418 */       Forbidden f = (Forbidden)buffer[k];
/*      */       
/*      */ 
/*      */ 
/* 3422 */       Node x = new Node(f.getB(), map.get(f.getB()));
/* 3423 */       Node y = new Node(f.getA(), map.get(f.getA()));
/*      */       
/* 3425 */       if (unfolded_g.isConnected(x, y)) {
/* 3426 */         unfolded_g.removeEdge(x, y);
/*      */       }
/* 3428 */       for (int ni = 0; ni < unfolded_g.listaNodi().size(); ni++) {
/* 3429 */         Node n = (Node)unfolded_g.listaNodi().get(ni);
/* 3430 */         n.setMark(false);
/*      */       }
/*      */       
/* 3433 */       ObjectArrayList<Node> listaNodiPath = new ObjectArrayList();
/*      */       
/* 3435 */       boolean spezzaPath = bfs(unfolded_g, x, y, null, listaNodiPath);
/*      */       
/*      */ 
/* 3438 */       if (spezzaPath)
/*      */       {
/*      */ 
/* 3441 */         ObjectArrayList<Edge> archiRimossi = new ObjectArrayList();
/*      */         
/*      */         do
/*      */         {
/* 3445 */           double minCs = Double.MAX_VALUE;
/*      */           
/* 3447 */           Node z = null;
/* 3448 */           Node w = null;
/* 3449 */           Node zz = null;
/* 3450 */           Node ww = null;
/*      */           
/* 3452 */           for (int i = 0; i < listaNodiPath.size() - 1; i++) {
/* 3453 */             for (int j = i + 1; j < listaNodiPath.size(); j++)
/*      */             {
/* 3455 */               zz = (Node)listaNodiPath.get(i);
/* 3456 */               ww = (Node)listaNodiPath.get(j);
/* 3457 */               Edge e = new Edge(zz, ww);
/*      */               
/* 3459 */               if (unfolded_g.getLista_archi().contains(e))
/*      */               {
/*      */ 
/* 3462 */                 if ((!archiRimossi.contains(e)) && (csm[zz.getID_attivita()][ww.getID_attivita()] < minCs)) {
/* 3463 */                   minCs = csm[zz.getID_attivita()][ww.getID_attivita()];
/* 3464 */                   z = zz;
/* 3465 */                   w = ww;
/*      */                 } }
/*      */             }
/*      */           }
/* 3469 */           archiRimossi.add(new Edge(z, w));
/*      */           
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/* 3500 */           unfolded_g.removeEdge(z, w);
/*      */           
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/* 3513 */           System.out.println("RIMOSSO ARCO FORBIDDEN " + z.getNomeAttivita() + " => " + w.getNomeAttivita());
/* 3514 */           m[z.getID_attivita()][w.getID_attivita()] = 0.0D;
/*      */           
/* 3516 */           z.decr_Outer_degree();
/* 3517 */           w.decr_Inner_degree();
/*      */           
/* 3519 */           ObjectOpenHashSet<String> lista_candidati_best_pred = null;
/*      */           
/*      */ 
/* 3522 */           lista_candidati_best_pred = bestPred_Folded(w.getID_attivita(), z.getID_attivita(), map, attivita_tracce, traccia_attivita);
/*      */           
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/* 3532 */           String best_pred = attivita_iniziale + "#" + String.format("%04d", new Object[] { Integer.valueOf(0) });
/*      */           
/*      */ 
/* 3535 */           if (lista_candidati_best_pred != null)
/*      */           {
/* 3537 */             if (lista_candidati_best_pred.size() > 0)
/*      */             {
/* 3539 */               ObjectArrayList<String> lista_candidati_best_pred_unfolded = new ObjectArrayList();
/*      */               
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/* 3546 */               Iterator<ObjectCursor<String>> it = lista_candidati_best_pred.iterator();
/* 3547 */               while (it.hasNext())
/*      */               {
/* 3549 */                 String activity = (String)((ObjectCursor)it.next()).value;
/*      */                 
/* 3551 */                 String best_unfolded_item = "";
/* 3552 */                 double best_unfolded_cs = -1.0D;
/*      */                 
/* 3554 */                 Object[] keys2 = map.keys;
/* 3555 */                 for (int j = 0; j < map.allocated.length; j++) {
/* 3556 */                   if (map.allocated[j] != 0)
/*      */                   {
/* 3558 */                     String unfolded_item = (String)keys2[j];
/*      */                     
/* 3560 */                     if ((unfolded_item.split("#")[0].equals(activity)) && 
/* 3561 */                       (csm[map.get(unfolded_item)][w.getID_attivita()] > best_unfolded_cs)) {
/* 3562 */                       best_unfolded_item = unfolded_item;
/* 3563 */                       best_unfolded_cs = csm[map.get(unfolded_item)][w.getID_attivita()];
/*      */                     }
/*      */                   }
/*      */                 }
/* 3567 */                 lista_candidati_best_pred_unfolded.add(best_unfolded_item);
/*      */               }
/*      */               
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/* 3575 */               best_pred = getFinalBestPred(unfolded_g, csm, w, map, lista_candidati_best_pred_unfolded, 
/* 3576 */                 vincoli_negati, lista_forbidden, folded_g, folded_map, true);
/*      */             }
/*      */           }
/*      */           
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/* 3587 */           ObjectOpenHashSet<String> lista_candidati_best_succ = null;
/*      */           
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/* 3594 */           lista_candidati_best_succ = bestSucc_Folded(z.getID_attivita(), w.getID_attivita(), map, 
/* 3595 */             attivita_tracce, traccia_attivita);
/*      */           
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/* 3606 */           String best_succ = attivita_finale + "#" + String.format("%04d", new Object[] { Integer.valueOf(0) });
/*      */           
/*      */ 
/* 3609 */           if ((lista_candidati_best_succ != null) && 
/* 3610 */             (lista_candidati_best_succ.size() > 0))
/*      */           {
/* 3612 */             ObjectArrayList<String> lista_candidati_best_succ_unfolded = new ObjectArrayList();
/*      */             
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/* 3619 */             Iterator<ObjectCursor<String>> it = lista_candidati_best_succ.iterator();
/* 3620 */             while (it.hasNext())
/*      */             {
/* 3622 */               String activity = (String)((ObjectCursor)it.next()).value;
/* 3623 */               String best_unfolded_item = "";
/* 3624 */               double best_unfolded_cs = -1.0D;
/*      */               
/* 3626 */               Object[] keys2 = map.keys;
/* 3627 */               for (int j = 0; j < map.allocated.length; j++) {
/* 3628 */                 if (map.allocated[j] != 0)
/*      */                 {
/* 3630 */                   String unfolded_item = (String)keys2[j];
/* 3631 */                   if ((unfolded_item.split("#")[0].equals(activity)) && 
/* 3632 */                     (csm[z.getID_attivita()][map.get(unfolded_item)] > best_unfolded_cs)) {
/* 3633 */                     best_unfolded_item = unfolded_item;
/* 3634 */                     best_unfolded_cs = csm[z.getID_attivita()][map.get(unfolded_item)];
/*      */                   }
/*      */                 }
/*      */               }
/* 3638 */               lista_candidati_best_succ_unfolded.add(best_unfolded_item);
/*      */             }
/*      */             
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/* 3648 */             best_succ = getFinalBestSucc(unfolded_g, csm, z, map, lista_candidati_best_succ_unfolded, 
/* 3649 */               vincoli_negati, lista_forbidden, folded_g, folded_map, true);
/*      */           }
/*      */           
/*      */ 
/*      */ 
/* 3654 */           if (!best_pred.equals(""))
/*      */           {
/*      */ 
/*      */ 
/* 3658 */             Node nz = unfolded_g.getNode(getKeyByValue(map, map.get(best_pred)), map.get(best_pred));
/*      */             
/*      */ 
/* 3661 */             if (!unfolded_g.isConnected(nz, w))
/*      */             {
/* 3663 */               m[map.get(best_pred)][w.getID_attivita()] = 1.0D;
/* 3664 */               unfolded_g.addEdge(nz, w, false);
/*      */               
/*      */ 
/* 3667 */               nz.incr_Outer_degree();
/* 3668 */               w.incr_Inner_degree();
/*      */             }
/*      */           }
/*      */           
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/* 3677 */           if (!best_succ.equals(""))
/*      */           {
/*      */ 
/* 3680 */             Node nw = unfolded_g.getNode(getKeyByValue(map, map.get(best_succ)), map.get(best_succ));
/*      */             
/*      */ 
/*      */ 
/* 3684 */             if (!unfolded_g.isConnected(z, nw)) {
/* 3685 */               m[z.getID_attivita()][map.get(best_succ)] = 1.0D;
/* 3686 */               unfolded_g.addEdge(z, nw, false);
/*      */               
/*      */ 
/* 3689 */               z.incr_Outer_degree();
/* 3690 */               nw.incr_Inner_degree();
/*      */             }
/*      */           }
/*      */           
/*      */ 
/*      */ 
/* 3696 */           for (int ni = 0; ni < unfolded_g.listaNodi().size(); ni++) {
/* 3697 */             Node n = (Node)unfolded_g.listaNodi().get(ni);
/* 3698 */             n.setMark(false);
/*      */ 
/*      */           }
/*      */           
/*      */ 
/*      */         }
/* 3704 */         while (bfs(unfolded_g, x, y, null, null));
/*      */       }
/*      */     }
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */   public double[][] calcoloMatriceDeiCausalScore(XLog log, ObjectIntOpenHashMap<String> map, ObjectObjectOpenHashMap<String, ObjectArrayList<String>> traccia_attivita, double ff)
/*      */   {
/* 3714 */     ObjectArrayList<IntArrayList> vlog = new ObjectArrayList();
/*      */     
/* 3716 */     Object[] values = traccia_attivita.values;
/*      */     ObjectCursor<String> s;
/* 3718 */     for (int i = 0; i < traccia_attivita.allocated.length; i++)
/*      */     {
/* 3720 */       if (traccia_attivita.allocated[i] != 0)
/*      */       {
/* 3722 */         IntArrayList t1 = new IntArrayList();
/* 3723 */         ObjectArrayList<String> vals = (ObjectArrayList)values[i];
/*      */         
/* 3725 */         for (Iterator localIterator = vals.iterator(); localIterator.hasNext();) { s = (ObjectCursor)localIterator.next();
/* 3726 */           t1.add(map.get((String)s.value)); }
/* 3727 */         vlog.add(t1);
/*      */       }
/*      */     }
/*      */     
/*      */ 
/* 3732 */     double[][] weightMatrix = null;
/*      */     
/*      */     try
/*      */     {
/* 3736 */       WeightEstimator.CLOSEST_OCCURRENCE_ONLY = true;
/* 3737 */       WeightEstimator weightEstimator = new WeightEstimator(map.size(), -1, ff, 
/* 3738 */         1);
/*      */       
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/* 3744 */       for (ObjectCursor<IntArrayList> t : vlog) {
/* 3745 */         weightEstimator.addTraceContribution((IntArrayList)t.value);
/*      */       }
/*      */       
/*      */ 
/*      */ 
/* 3750 */       weightEstimator.computeWeigths();
/* 3751 */       weightMatrix = weightEstimator.getDependencyMatrix();
/*      */ 
/*      */     }
/*      */     catch (Exception e)
/*      */     {
/* 3756 */       e.printStackTrace();
/*      */     }
/* 3758 */     return weightMatrix;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */   private boolean checkTrueEdge_CS2(Node n, Node adjacent_i, ObjectArrayList<Node> adjacents, double[][] csmOri, Graph g, ObjectIntOpenHashMap<String> map, ObjectObjectOpenHashMap<String, ObjectArrayList<String>> attivita_tracce, ObjectObjectOpenHashMap<String, ObjectArrayList<String>> traccia_attivita, double sigma_1, double sigma_2)
/*      */   {
/* 3766 */     boolean remove_edge = false;
/*      */     
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/* 3773 */     sigma_1 = 1.0D;
/*      */     
/*      */ 
/* 3776 */     if (csmOri[n.getID_attivita()][adjacent_i.getID_attivita()] - csmOri[adjacent_i.getID_attivita()][n.getID_attivita()] <= sigma_1)
/*      */     {
/* 3778 */       remove_edge = true;
/*      */       
/* 3780 */       ObjectOpenHashSet<String> candidatiZ = new ObjectOpenHashSet();
/* 3781 */       for (ObjectCursor<Node> e : g.listaNodi()) {
/* 3782 */         if ((g.isConnected((Node)e.value, adjacent_i)) && (!((Node)e.value).getNomeAttivita().equals(n.getNomeAttivita()))) {
/* 3783 */           candidatiZ.add(((Node)e.value).getNomeAttivita());
/*      */         }
/*      */       }
/* 3786 */       ObjectOpenHashSet<String> tracce_n = new ObjectOpenHashSet((ObjectContainer)attivita_tracce.get(n.getNomeAttivita()));
/* 3787 */       Object tracce_adj = new ObjectArrayList((ObjectContainer)attivita_tracce.get(adjacent_i.getNomeAttivita()));
/*      */       
/* 3789 */       ((ObjectArrayList)tracce_adj).retainAll(tracce_n);
/*      */       
/*      */ 
/*      */ 
/* 3793 */       if (((ObjectArrayList)tracce_adj).size() == 0) {
/* 3794 */         remove_edge = false;
/*      */       }
/*      */       else {
/* 3797 */         ObjectOpenHashSet<String> candidatiW = new ObjectOpenHashSet();
/* 3798 */         for (ObjectCursor<Node> na : adjacents) {
/* 3799 */           candidatiW.add(((Node)na.value).getNomeAttivita());
/*      */         }
/* 3801 */         int counter = 0;
/*      */         
/* 3803 */         boolean autoanello_y = false;
/* 3804 */         if (g.isConnected(adjacent_i, adjacent_i)) {
/* 3805 */           autoanello_y = true;
/*      */         }
/* 3807 */         boolean autoanello_x = false;
/* 3808 */         if (g.isConnected(n, n)) {
/* 3809 */           autoanello_x = true;
/*      */         }
/* 3811 */         for (ObjectCursor<String> traccia : (ObjectArrayList)tracce_adj)
/*      */         {
/*      */ 
/* 3814 */           if ((!esisteAttivatore((String)traccia.value, adjacent_i.getNomeAttivita(), adjacent_i.getNomeAttivita(), traccia_attivita, candidatiZ, false, autoanello_y, false)) || 
/*      */           
/* 3816 */             (!esisteAttivatore((String)traccia.value, n.getNomeAttivita(), n.getNomeAttivita(), traccia_attivita, candidatiW, false, autoanello_x, true)))
/*      */           {
/* 3818 */             counter++;
/*      */             
/* 3820 */             if (counter > sigma_2 * ((ObjectArrayList)tracce_adj).size())
/*      */             {
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/* 3828 */               remove_edge = false;
/* 3829 */               System.out.println("traccia non soddisfatta " + traccia);
/* 3830 */               break;
/*      */             }
/*      */           }
/*      */         }
/*      */       }
/*      */     }
/*      */     
/*      */ 
/*      */ 
/*      */ 
/* 3840 */     return remove_edge;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */   public void creaVincoliUnfolded(ObjectArrayList<Constraint> vincoli_positivi, ObjectArrayList<Constraint> vincoli_negati, ObjectArrayList<Forbidden> lista_forbidden, ObjectArrayList<Constraint> vincoli_positivi_unfolded, ObjectArrayList<Constraint> vincoli_negati_unfolded, ObjectArrayList<Forbidden> lista_forbidden_unfolded, ObjectIntOpenHashMap<String> map)
/*      */   {
/*      */     int i;
/*      */     
/*      */ 
/* 3850 */     for (Iterator localIterator = vincoli_positivi.iterator(); localIterator.hasNext(); 
/*      */         
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/* 3856 */         i < map.allocated.length)
/*      */     {
/* 3850 */       ObjectCursor<Constraint> c = (ObjectCursor)localIterator.next();
/*      */       
/*      */ 
/*      */ 
/* 3854 */       Object[] keys = map.keys;
/*      */       
/* 3856 */       i = 0; continue;
/*      */       
/* 3858 */       if (map.allocated[i] != 0) {
/* 3859 */         String unfolded_head = (String)keys[i];
/*      */         
/* 3861 */         if (((Constraint)c.value).getHeadList().contains(unfolded_head.split("#")[0]))
/*      */         {
/*      */ 
/*      */ 
/* 3865 */           Constraint unfolded_c = new Constraint();
/*      */           
/* 3867 */           unfolded_c.setConstraintType(((Constraint)c.value).isPositiveConstraint());
/* 3868 */           unfolded_c.setPathConstraint(((Constraint)c.value).isPathConstraint());
/* 3869 */           unfolded_c.addHead(unfolded_head);
/*      */           
/*      */ 
/*      */ 
/* 3873 */           for (int j = 0; j < map.allocated.length; j++) {
/* 3874 */             if (map.allocated[j] != 0) {
/* 3875 */               String unfolded_body = (String)keys[j];
/* 3876 */               if (((Constraint)c.value).getBodyList().contains(unfolded_body.split("#")[0]))
/* 3877 */                 unfolded_c.addBody(unfolded_body);
/*      */             }
/*      */           }
/* 3880 */           vincoli_positivi_unfolded.add(unfolded_c);
/*      */         }
/*      */       }
/* 3856 */       i++;
/*      */     }
/*      */     
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     int i;
/*      */     
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/* 3885 */     for (localIterator = vincoli_negati.iterator(); localIterator.hasNext(); 
/*      */         
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/* 3891 */         i < map.allocated.length)
/*      */     {
/* 3885 */       ObjectCursor<Constraint> c = (ObjectCursor)localIterator.next();
/*      */       
/*      */ 
/*      */ 
/* 3889 */       Object[] keys2 = map.keys;
/*      */       
/* 3891 */       i = 0; continue;
/*      */       
/* 3893 */       if (map.allocated[i] != 0) {
/* 3894 */         String unfolded_head = (String)keys2[i];
/* 3895 */         if (((Constraint)c.value).getHeadList().contains(unfolded_head.split("#")[0]))
/*      */         {
/*      */ 
/* 3898 */           Constraint unfolded_c = new Constraint();
/*      */           
/* 3900 */           unfolded_c.setConstraintType(((Constraint)c.value).isPositiveConstraint());
/* 3901 */           unfolded_c.setPathConstraint(((Constraint)c.value).isPathConstraint());
/* 3902 */           unfolded_c.addHead(unfolded_head);
/*      */           
/*      */ 
/* 3905 */           for (int j = 0; j < map.allocated.length; j++) {
/* 3906 */             if (map.allocated[j] != 0) {
/* 3907 */               String unfolded_body = (String)keys2[j];
/*      */               
/* 3909 */               if (((Constraint)c.value).getBodyList().contains(unfolded_body.split("#")[0])) {
/* 3910 */                 unfolded_c.addBody(unfolded_body);
/* 3911 */                 lista_forbidden_unfolded.add(new Forbidden(unfolded_body, unfolded_head));
/*      */               }
/*      */             }
/*      */           }
/*      */           
/* 3916 */           vincoli_negati_unfolded.add(unfolded_c);
/*      */         }
/*      */       }
/* 3891 */       i++;
/*      */     }
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public void eliminaForbidden(Graph g, ObjectArrayList<Forbidden> lista_forbidden_unfolded, ObjectArrayList<Forbidden> lista_forbidden, ObjectIntOpenHashMap<String> map, double[][] m, double[][] csm, ObjectObjectOpenHashMap<String, ObjectArrayList<String>> attivita_tracce, ObjectObjectOpenHashMap<String, ObjectArrayList<String>> traccia_attivita, ObjectArrayList<Constraint> vincoli_positivi, ObjectArrayList<Constraint> vincoli_negati, Graph folded_g, ObjectIntOpenHashMap<String> folded_map)
/*      */   {
/* 3930 */     int it = 0;
/*      */     
/* 3932 */     while (it < lista_forbidden_unfolded.size())
/*      */     {
/* 3934 */       Forbidden f = (Forbidden)lista_forbidden_unfolded.get(it);
/*      */       
/* 3936 */       Node x = new Node(f.getB(), map.get(f.getB()));
/* 3937 */       Node y = new Node(f.getA(), map.get(f.getA()));
/*      */       
/* 3939 */       if (g.isConnected(x, y))
/*      */       {
/* 3941 */         boolean vincoli_soddisfatti = verificaVincoliPositivi(
/* 3942 */           folded_g, 
/* 3943 */           folded_g.getNode(x.getNomeAttivita().split("#")[0], 
/* 3944 */           folded_map.get(x.getNomeAttivita().split("#")[0])), 
/* 3945 */           folded_g.getNode(y.getNomeAttivita().split("#")[0], 
/* 3946 */           folded_map.get(y.getNomeAttivita().split("#")[0])), vincoli_positivi, folded_map);
/*      */         
/* 3948 */         if (vincoli_soddisfatti)
/*      */         {
/* 3950 */           g.removeEdge(x, y);
/*      */           
/* 3952 */           m[x.getID_attivita()][y.getID_attivita()] = 0.0D;
/* 3953 */           System.out.println("RIMOSSO ARCO FORBIDDEN " + x.getNomeAttivita() + " => " + y.getNomeAttivita());
/* 3954 */           x.decr_Outer_degree();
/* 3955 */           y.decr_Inner_degree();
/*      */           
/* 3957 */           ObjectOpenHashSet<String> lista_candidati_best_pred = null;
/*      */           
/*      */ 
/* 3960 */           lista_candidati_best_pred = bestPred_Folded(y.getID_attivita(), x.getID_attivita(), map, 
/* 3961 */             attivita_tracce, traccia_attivita);
/*      */           
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/* 3971 */           String best_pred = attivita_iniziale + "#" + String.format("%04d", new Object[] { Integer.valueOf(0) });
/*      */           
/*      */           String best_unfolded_item;
/* 3974 */           if (lista_candidati_best_pred != null)
/*      */           {
/* 3976 */             if (lista_candidati_best_pred.size() > 0)
/*      */             {
/* 3978 */               ObjectArrayList<String> lista_candidati_best_pred_unfolded = new ObjectArrayList();
/*      */               
/* 3980 */               for (ObjectCursor<String> activityCursor : lista_candidati_best_pred)
/*      */               {
/* 3982 */                 String activity = (String)activityCursor.value;
/* 3983 */                 best_unfolded_item = "";
/* 3984 */                 double best_unfolded_cs = -1.0D;
/*      */                 
/* 3986 */                 Object[] keys = map.keys;
/*      */                 
/* 3988 */                 for (int i = 0; i < map.allocated.length; i++) {
/* 3989 */                   if (map.allocated[i] != 0) {
/* 3990 */                     String unfolded_item = (String)keys[i];
/* 3991 */                     if ((unfolded_item.split("#")[0].equals(activity)) && 
/* 3992 */                       (csm[map.get(unfolded_item)][y.getID_attivita()] > best_unfolded_cs)) {
/* 3993 */                       best_unfolded_item = unfolded_item;
/* 3994 */                       best_unfolded_cs = csm[map.get(unfolded_item)][y.getID_attivita()];
/*      */                     }
/*      */                   }
/*      */                 }
/*      */                 
/* 3999 */                 lista_candidati_best_pred_unfolded.add(best_unfolded_item);
/*      */               }
/*      */               
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/* 4006 */               best_pred = getFinalBestPred(g, csm, y, map, lista_candidati_best_pred_unfolded, 
/* 4007 */                 vincoli_negati, lista_forbidden, folded_g, folded_map, false);
/*      */             }
/*      */             else {
/* 4010 */               System.out.println("FALLIMENTO BEST PRED NON TROVATO!!!");
/*      */             }
/*      */           }
/*      */           
/*      */ 
/*      */ 
/* 4016 */           ObjectOpenHashSet<String> lista_candidati_best_succ = null;
/*      */           
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/* 4023 */           lista_candidati_best_succ = bestSucc_Folded(x.getID_attivita(), y.getID_attivita(), map, 
/* 4024 */             attivita_tracce, traccia_attivita);
/*      */           
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/* 4035 */           String best_succ = attivita_finale + "#" + String.format("%04d", new Object[] { Integer.valueOf(0) });
/*      */           
/*      */ 
/* 4038 */           if (lista_candidati_best_succ != null) {
/* 4039 */             if (lista_candidati_best_succ.size() > 0)
/*      */             {
/* 4041 */               Object lista_candidati_best_succ_unfolded = new ObjectArrayList();
/*      */               
/* 4043 */               for (ObjectCursor<String> activityCursor : lista_candidati_best_succ) {
/* 4044 */                 String activity = (String)activityCursor.value;
/* 4045 */                 String best_unfolded_item = "";
/* 4046 */                 double best_unfolded_cs = -1.0D;
/*      */                 
/* 4048 */                 Object[] keys = map.keys;
/*      */                 
/* 4050 */                 for (int i = 0; i < map.allocated.length; i++) {
/* 4051 */                   if (map.allocated[i] != 0) {
/* 4052 */                     String unfolded_item = (String)keys[i];
/*      */                     
/* 4054 */                     if ((unfolded_item.split("#")[0].equals(activity)) && 
/* 4055 */                       (csm[x.getID_attivita()][map.get(unfolded_item)] > best_unfolded_cs)) {
/* 4056 */                       best_unfolded_item = unfolded_item;
/* 4057 */                       best_unfolded_cs = csm[x.getID_attivita()][map.get(unfolded_item)];
/*      */                     }
/*      */                   }
/*      */                 }
/* 4061 */                 ((ObjectArrayList)lista_candidati_best_succ_unfolded).add(best_unfolded_item);
/*      */               }
/*      */               
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/* 4070 */               best_succ = getFinalBestSucc(g, csm, x, map, (ObjectArrayList)lista_candidati_best_succ_unfolded, 
/* 4071 */                 vincoli_negati, lista_forbidden, folded_g, folded_map, false);
/*      */             }
/*      */             else {
/* 4074 */               System.out.println("FALLIMENTO BEST SUCC NON TROVATO!!!");
/*      */             }
/*      */           }
/*      */           
/*      */ 
/* 4079 */           if (!best_pred.equals(""))
/*      */           {
/*      */ 
/*      */ 
/* 4083 */             Node nz = g.getNode(getKeyByValue(map, map.get(best_pred)), map.get(best_pred));
/*      */             
/*      */ 
/* 4086 */             if (!g.isConnected(nz, y))
/*      */             {
/* 4088 */               m[map.get(best_pred)][y.getID_attivita()] = 1.0D;
/* 4089 */               g.addEdge(nz, y, false);
/*      */               
/*      */ 
/* 4092 */               nz.incr_Outer_degree();
/* 4093 */               y.incr_Inner_degree();
/*      */             }
/*      */           }
/*      */           
/*      */ 
/* 4098 */           if (!best_succ.equals(""))
/*      */           {
/*      */ 
/* 4101 */             Node nw = g.getNode(getKeyByValue(map, map.get(best_succ)), map.get(best_succ));
/*      */             
/*      */ 
/*      */ 
/* 4105 */             if (!g.isConnected(x, nw)) {
/* 4106 */               m[x.getID_attivita()][map.get(best_succ)] = 1.0D;
/* 4107 */               g.addEdge(x, nw, false);
/*      */               
/*      */ 
/* 4110 */               x.incr_Outer_degree();
/* 4111 */               nw.incr_Inner_degree();
/*      */             }
/*      */           }
/*      */         }
/*      */       }
/*      */       
/* 4117 */       it++;
/*      */     }
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   private boolean esisteAttivatore(String trace, String activity_x, String activity_y, ObjectObjectOpenHashMap<String, ObjectArrayList<String>> traccia_attivita, ObjectOpenHashSet<String> candidati_z, boolean flag, boolean autoanello_y, boolean forward)
/*      */   {
/* 4130 */     ObjectArrayList<String> attivatore_traccia = new ObjectArrayList();
/*      */     
/*      */     int iter;
/*      */     int iter;
/* 4134 */     if (!forward) {
/* 4135 */       iter = ((ObjectArrayList)traccia_attivita.get(trace)).size() - 1;
/*      */     }
/*      */     else {
/* 4138 */       iter = 0;
/*      */     }
/*      */     
/* 4141 */     boolean trovata_y = false;
/*      */     
/* 4143 */     while (((iter >= 0) && (!forward)) || ((iter < ((ObjectArrayList)traccia_attivita.get(trace)).size()) && (forward)))
/*      */     {
/* 4145 */       String activity_z = (String)((ObjectArrayList)traccia_attivita.get(trace)).get(iter);
/*      */       
/* 4147 */       if ((!trovata_y) && (!activity_z.equals(activity_y)))
/*      */       {
/* 4149 */         if (!forward) {
/* 4150 */           iter--;
/*      */         } else {
/* 4152 */           iter++;
/*      */         }
/*      */       }
/*      */       else
/*      */       {
/* 4157 */         if (!trovata_y)
/*      */         {
/* 4159 */           trovata_y = true;
/* 4160 */           if (!forward) {
/* 4161 */             iter--;
/*      */           } else
/* 4163 */             iter++;
/* 4164 */           if (((iter >= 0) && (!forward)) || ((iter < ((ObjectArrayList)traccia_attivita.get(trace)).size()) && (forward))) {
/* 4165 */             activity_z = (String)((ObjectArrayList)traccia_attivita.get(trace)).get(iter);
/*      */           }
/*      */         }
/* 4168 */         if (flag)
/*      */         {
/* 4170 */           if (!activity_z.equals(activity_x))
/*      */           {
/* 4172 */             if (!attivatore_traccia.contains(activity_z)) {
/* 4173 */               attivatore_traccia.add(activity_z);
/*      */             }
/* 4175 */             if (activity_z.equals(activity_y)) {
/* 4176 */               attivatore_traccia = new ObjectArrayList();
/*      */             }
/*      */             
/*      */ 
/*      */           }
/*      */           else
/*      */           {
/* 4183 */             attivatore_traccia.retainAll(candidati_z);
/*      */             
/* 4185 */             if (attivatore_traccia.size() == 0) {
/* 4186 */               return false;
/*      */             }
/* 4188 */             trovata_y = false;
/* 4189 */             attivatore_traccia = new ObjectArrayList();
/*      */ 
/*      */           }
/*      */           
/*      */ 
/*      */ 
/*      */         }
/* 4196 */         else if (!activity_z.equals(activity_y))
/*      */         {
/* 4198 */           if (!attivatore_traccia.contains(activity_z)) {
/* 4199 */             attivatore_traccia.add(activity_z);
/*      */           }
/*      */         }
/*      */         else {
/* 4203 */           attivatore_traccia.retainAll(candidati_z);
/*      */           
/* 4205 */           if ((attivatore_traccia.size() == 0) && (!autoanello_y)) {
/* 4206 */             return false;
/*      */           }
/*      */           
/*      */ 
/* 4210 */           attivatore_traccia = new ObjectArrayList();
/*      */         }
/*      */         
/*      */ 
/*      */ 
/* 4215 */         if (!forward) {
/* 4216 */           iter--;
/*      */         } else
/* 4218 */           iter++;
/*      */       }
/*      */     }
/* 4221 */     if (!flag)
/*      */     {
/*      */ 
/*      */ 
/* 4225 */       attivatore_traccia.retainAll(candidati_z);
/*      */       
/* 4227 */       if (attivatore_traccia.size() == 0) {
/* 4228 */         return false;
/*      */       }
/*      */     }
/* 4231 */     return true;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public boolean follows(String activity_x, String activity_y, ObjectObjectOpenHashMap<String, ObjectArrayList<String>> traccia_attivita, ObjectObjectOpenHashMap<String, ObjectArrayList<String>> attivita_tracce, double sigma_2)
/*      */   {
/* 4244 */     ObjectOpenHashSet<String> tracce_n = new ObjectOpenHashSet((ObjectContainer)attivita_tracce.get(activity_x));
/* 4245 */     ObjectArrayList<String> tracce_adj = new ObjectArrayList((ObjectContainer)attivita_tracce.get(activity_y));
/*      */     
/* 4247 */     tracce_adj.retainAll(tracce_n);
/*      */     
/* 4249 */     int counter = 0;
/*      */     
/* 4251 */     for (ObjectCursor<String> trace : tracce_adj)
/*      */     {
/* 4253 */       int it = ((ObjectArrayList)traccia_attivita.get((String)trace.value)).size() - 1;
/*      */       
/* 4255 */       while (it >= 0)
/*      */       {
/* 4257 */         String attivita_k = (String)((ObjectArrayList)traccia_attivita.get((String)trace.value)).get(it);
/*      */         
/*      */ 
/* 4260 */         if (attivita_k.equals(activity_x))
/*      */         {
/*      */ 
/* 4263 */           counter++;
/*      */           
/* 4265 */           if (counter > sigma_2 * tracce_adj.size())
/*      */           {
/*      */ 
/*      */ 
/* 4269 */             return true;
/*      */           }
/*      */         } else {
/* 4272 */           if (attivita_k.equals(activity_y)) {
/*      */             break;
/*      */           }
/*      */         }
/*      */         
/* 4277 */         it--;
/*      */       }
/*      */     }
/* 4280 */     return false;
/*      */   }
/*      */   
/*      */ 
/*      */   public ObjectArrayList<FakeDependency> getAttivitaParallele(double[][] m, Graph graph, ObjectIntOpenHashMap<String> map, ObjectArrayList<Constraint> vincoli_positivi, ObjectIntOpenHashMap<String> folded_map, Graph folded_g)
/*      */   {
/* 4286 */     ObjectArrayList<FakeDependency> lista_attivita_parallele = new ObjectArrayList();
/*      */     
/*      */ 
/*      */     Iterator localIterator2;
/*      */     
/*      */ 
/* 4292 */     for (Iterator localIterator1 = graph.listaNodi().iterator(); localIterator1.hasNext(); 
/*      */         
/* 4294 */         localIterator2.hasNext())
/*      */     {
/* 4292 */       ObjectCursor<Node> np = (ObjectCursor)localIterator1.next();
/*      */       
/* 4294 */       localIterator2 = graph.adjacentNodes((Node)np.value).iterator(); continue;ObjectCursor<Node> nr = (ObjectCursor)localIterator2.next();
/*      */       
/* 4296 */       boolean b = bfs(graph, (Node)nr.value, (Node)np.value, null, null);
/*      */       
/*      */ 
/*      */ 
/* 4300 */       if (b)
/*      */       {
/*      */ 
/*      */ 
/*      */ 
/* 4305 */         boolean vincoli_soddisfatti = verificaVincoliPositivi(
/* 4306 */           folded_g, 
/* 4307 */           folded_g.getNode(((Node)np.value).getNomeAttivita().split("#")[0], 
/* 4308 */           folded_map.get(((Node)np.value).getNomeAttivita().split("#")[0])), 
/* 4309 */           folded_g.getNode(((Node)nr.value).getNomeAttivita().split("#")[0], 
/* 4310 */           folded_map.get(((Node)nr.value).getNomeAttivita().split("#")[0])), vincoli_positivi, folded_map);
/*      */         
/* 4312 */         if (vincoli_soddisfatti)
/*      */         {
/* 4314 */           lista_attivita_parallele.add(new FakeDependency(((Node)np.value).getID_attivita(), ((Node)nr.value).getID_attivita()));
/*      */         }
/*      */       }
/*      */       
/* 4318 */       for (int ni = 0; ni < graph.listaNodi().size(); ni++) {
/* 4319 */         Node n = (Node)graph.listaNodi().get(ni);
/* 4320 */         n.setMark(false);
/*      */       }
/*      */     }
/*      */     
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/* 4365 */     return lista_attivita_parallele;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   private String getFinalBestPred(Graph graph, double[][] csm, Node ny, ObjectIntOpenHashMap<String> map, ObjectArrayList<String> lista_candidati_best_pred_unfolded, ObjectArrayList<Constraint> vincoli_negati, ObjectArrayList<Forbidden> lista_forbidden, Graph folded_g, ObjectIntOpenHashMap<String> folded_map, boolean onlyNotPath)
/*      */   {
/* 4402 */     for (ObjectCursor<Node> n : folded_g.listaNodi()) {
/* 4403 */       ((Node)n.value).setMark(false);
/*      */     }
/*      */     
/* 4406 */     String best_pred = attivita_iniziale + "#" + String.format("%04d", new Object[] { Integer.valueOf(0) });
/*      */     
/* 4408 */     double best_pred_cs = 0.0D;
/*      */     
/* 4410 */     double minZ = Double.MAX_VALUE;
/*      */     
/* 4412 */     if (onlyNotPath) {
/* 4413 */       minZ = 0.0D;
/*      */     }
/* 4415 */     for (ObjectCursor<String> attivita_zCursor : lista_candidati_best_pred_unfolded) {
/* 4416 */       String attivita_z = (String)attivita_zCursor.value;
/*      */       
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/* 4422 */       ObjectArrayList<Node> c_nodes = new ObjectArrayList();
/*      */       
/* 4424 */       int violations_counter = 0;
/*      */       
/* 4426 */       Forbidden f = new Forbidden(attivita_z.split("#")[0], ny.getNomeAttivita().split("#")[0]);
/*      */       
/* 4428 */       if (!lista_forbidden.contains(f))
/*      */       {
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/* 4437 */         for (ObjectCursor<Constraint> cpn : vincoli_negati)
/*      */         {
/* 4439 */           if (((Constraint)cpn.value).isPathConstraint())
/*      */           {
/* 4441 */             if (((Constraint)cpn.value).getBodyList().contains(attivita_z.split("#")[0])) {
/* 4442 */               for (String head : ((Constraint)cpn.value).getHeadList()) {
/* 4443 */                 c_nodes.add(new Node(head.split("#")[0], folded_map.get(head.split("#")[0])));
/*      */               }
/*      */             }
/*      */           }
/*      */         }
/*      */         Iterator localIterator5;
/* 4449 */         for (??? = c_nodes.iterator(); ???.hasNext(); 
/*      */             
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/* 4461 */             localIterator5.hasNext())
/*      */         {
/* 4449 */           ObjectCursor<Node> c = (ObjectCursor)???.next();
/*      */           
/*      */ 
/*      */ 
/* 4453 */           for (ObjectCursor<Node> n : folded_g.listaNodi()) {
/* 4454 */             ((Node)n.value).setMark(false);
/*      */           }
/* 4456 */           path_violated = bfs(folded_g, folded_g.getNode(ny.getNomeAttivita().split("#")[0], folded_map.get(ny.getNomeAttivita().split("#")[0])), (Node)c.value, null, null);
/*      */           
/* 4458 */           if (path_violated) {
/* 4459 */             violations_counter++;
/*      */           }
/* 4461 */           localIterator5 = folded_g.listaNodi().iterator(); continue;Object n = (ObjectCursor)localIterator5.next();
/* 4462 */           ((Node)((ObjectCursor)n).value).setMark(false);
/*      */         }
/*      */         
/* 4465 */         Node z = new Node(attivita_z.split("#")[0], folded_map.get(attivita_z.split("#")[0]));
/*      */         
/* 4467 */         for (boolean path_violated = folded_g.listaNodi().iterator(); path_violated.hasNext(); 
/*      */             
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/* 4478 */             localIterator5.hasNext())
/*      */         {
/* 4467 */           Object n = (ObjectCursor)path_violated.next();
/* 4468 */           if (bfs(folded_g, (Node)((ObjectCursor)n).value, z, null, null))
/*      */           {
/* 4470 */             for (Object cpn : vincoli_negati) {
/* 4471 */               if (((Constraint)((ObjectCursor)cpn).value).isPathConstraint())
/*      */               {
/* 4473 */                 if ((((Constraint)((ObjectCursor)cpn).value).getBodyList().contains(((Node)n.value).getNomeAttivita().split("#")[0])) && (((Constraint)((ObjectCursor)cpn).value).getHeadList().contains(ny.getNomeAttivita().split("#")[0])))
/*      */                 {
/* 4475 */                   violations_counter++; } }
/*      */             }
/*      */           }
/* 4478 */           localIterator5 = folded_g.listaNodi().iterator(); continue;Object nn = (ObjectCursor)localIterator5.next();
/* 4479 */           ((Node)((ObjectCursor)nn).value).setMark(false);
/*      */         }
/*      */         
/* 4482 */         if (violations_counter < minZ) {
/* 4483 */           minZ = violations_counter;
/*      */           
/* 4485 */           best_pred = attivita_z;
/* 4486 */           best_pred_cs = csm[map.get(attivita_z)][ny.getID_attivita()];
/*      */ 
/*      */         }
/* 4489 */         else if (violations_counter == minZ)
/*      */         {
/* 4491 */           if (csm[map.get(attivita_z)][ny.getID_attivita()] > best_pred_cs) {
/* 4492 */             best_pred = attivita_z;
/* 4493 */             best_pred_cs = csm[map.get(attivita_z)][ny.getID_attivita()];
/*      */           }
/*      */         }
/*      */       }
/*      */     }
/*      */     
/* 4499 */     return best_pred;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */   private String getFinalBestSucc(Graph graph, double[][] csm, Node nx, ObjectIntOpenHashMap<String> map, ObjectArrayList<String> lista_candidati_best_succ_unfolded, ObjectArrayList<Constraint> vincoli_negati, ObjectArrayList<Forbidden> lista_forbidden, Graph folded_g, ObjectIntOpenHashMap<String> folded_map, boolean notPathOnly)
/*      */   {
/* 4506 */     for (ObjectCursor<Node> n : folded_g.listaNodi()) {
/* 4507 */       ((Node)n.value).setMark(false);
/*      */     }
/* 4509 */     Node x = folded_g.getNode(nx.getNomeAttivita().split("#")[0], folded_map.get(nx.getNomeAttivita().split("#")[0]));
/*      */     
/* 4511 */     String best_succ = attivita_finale + "#" + String.format("%04d", new Object[] { Integer.valueOf(0) });
/*      */     
/*      */ 
/* 4514 */     double best_succ_cs = 0.0D;
/* 4515 */     double minW = Double.MAX_VALUE;
/*      */     
/* 4517 */     if (notPathOnly) {
/* 4518 */       minW = 0.0D;
/*      */     }
/* 4520 */     ObjectArrayList<Node> c_nodes = new ObjectArrayList();
/*      */     
/*      */ 
/* 4523 */     for (ObjectCursor<Constraint> cpn : vincoli_negati)
/*      */     {
/* 4525 */       if (((Constraint)cpn.value).isPathConstraint())
/*      */       {
/* 4527 */         if (((Constraint)cpn.value).getBodyList().contains(nx.getNomeAttivita().split("#")[0])) {
/* 4528 */           for (String head : ((Constraint)cpn.value).getHeadList()) {
/* 4529 */             c_nodes.add(new Node(head.split("#")[0], folded_map.get(head.split("#")[0])));
/*      */           }
/*      */         }
/*      */       }
/*      */     }
/* 4534 */     for (ObjectCursor<String> attivita_w : lista_candidati_best_succ_unfolded)
/*      */     {
/* 4536 */       int violations_counter = 0;
/*      */       
/* 4538 */       Forbidden f = new Forbidden(nx.getNomeAttivita().split("#")[0], ((String)attivita_w.value).split("#")[0]);
/*      */       
/*      */ 
/* 4541 */       if (!lista_forbidden.contains(f))
/*      */       {
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/* 4549 */         Node nw = folded_g.getNode(((String)attivita_w.value).split("#")[0], folded_map.get(((String)attivita_w.value).split("#")[0]));
/*      */         Iterator localIterator5;
/* 4551 */         ObjectCursor<Node> n; for (Iterator localIterator4 = c_nodes.iterator(); localIterator4.hasNext(); 
/*      */             
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/* 4562 */             localIterator5.hasNext())
/*      */         {
/* 4551 */           ObjectCursor<Node> c = (ObjectCursor)localIterator4.next();
/*      */           
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/* 4557 */           boolean path_violated = bfs(folded_g, nw, (Node)c.value, null, null);
/*      */           
/* 4559 */           if (path_violated) {
/* 4560 */             violations_counter++;
/*      */           }
/* 4562 */           localIterator5 = folded_g.listaNodi().iterator(); continue;n = (ObjectCursor)localIterator5.next();
/* 4563 */           ((Node)n.value).setMark(false);
/*      */         }
/*      */         
/*      */ 
/* 4567 */         for (localIterator4 = folded_g.listaNodi().iterator(); localIterator4.hasNext(); 
/*      */             
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/* 4577 */             n.hasNext())
/*      */         {
/* 4567 */           ObjectCursor<Node> n = (ObjectCursor)localIterator4.next();
/* 4568 */           if (bfs(folded_g, (Node)n.value, x, null, null))
/*      */           {
/* 4570 */             for (ObjectCursor<Constraint> cpn : vincoli_negati) {
/* 4571 */               if ((((Constraint)cpn.value).isPathConstraint()) && 
/* 4572 */                 (((Constraint)cpn.value).getBodyList().contains(((Node)n.value).getNomeAttivita().split("#")[0])) && (((Constraint)cpn.value).getHeadList().contains(((String)attivita_w.value).split("#")[0])))
/*      */               {
/* 4574 */                 violations_counter++; }
/*      */             }
/*      */           }
/* 4577 */           n = folded_g.listaNodi().iterator(); continue;ObjectCursor<Node> nn = (ObjectCursor)n.next();
/* 4578 */           ((Node)nn.value).setMark(false);
/*      */         }
/*      */         
/* 4581 */         if (violations_counter < minW)
/*      */         {
/* 4583 */           best_succ = (String)attivita_w.value;
/* 4584 */           best_succ_cs = csm[nx.getID_attivita()][map.get((String)attivita_w.value)];
/*      */           
/* 4586 */           minW = violations_counter;
/*      */         }
/* 4588 */         else if ((violations_counter == minW) && 
/* 4589 */           (csm[nx.getID_attivita()][map.get((String)attivita_w.value)] > best_succ_cs)) {
/* 4590 */           best_succ = (String)attivita_w.value;
/* 4591 */           best_succ_cs = csm[nx.getID_attivita()][map.get((String)attivita_w.value)];
/*      */         }
/*      */       }
/*      */     }
/*      */     
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/* 4605 */     return best_succ;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */   public Graph getGrafoAggregato(Graph g, XLog log, boolean flag, ObjectIntOpenHashMap<String> mapOri, ObjectObjectOpenHashMap<String, ObjectArrayList<String>> attivita_tracceOri, ObjectObjectOpenHashMap<String, ObjectArrayList<String>> traccia_attivitaOri)
/*      */   {
/* 4612 */     if (flag)
/*      */     {
/* 4614 */       time += System.currentTimeMillis();
/*      */       
/*      */ 
/* 4617 */       int count = 0;
/*      */       
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/* 4625 */       for (int i = 0; i < log.size(); i++) {
/* 4626 */         XTrace trace = (XTrace)log.get(i);
/* 4627 */         String traccia = trace.getAttributes().get("concept:name") + " # " + i;
/*      */         
/*      */ 
/*      */ 
/*      */ 
/* 4632 */         if (!traccia_attivitaOri.containsKey(traccia)) {
/* 4633 */           traccia_attivitaOri.put(traccia, new ObjectArrayList());
/*      */         }
/* 4635 */         for (XEvent activity : trace)
/*      */         {
/*      */ 
/*      */ 
/* 4639 */           String nome_attivita = activity.getAttributes().get("concept:name");
/*      */           
/* 4641 */           if (!mapOri.containsKey(nome_attivita)) {
/* 4642 */             mapOri.put(nome_attivita, count);
/* 4643 */             count++;
/*      */           }
/*      */           
/*      */ 
/* 4647 */           if (!attivita_tracceOri.containsKey(nome_attivita)) {
/* 4648 */             ObjectArrayList<String> lista_tracce = new ObjectArrayList();
/* 4649 */             lista_tracce.add(traccia);
/* 4650 */             attivita_tracceOri.put(nome_attivita, lista_tracce);
/*      */           }
/*      */           
/* 4653 */           ((ObjectArrayList)attivita_tracceOri.get(nome_attivita)).add(traccia);
/*      */           
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/* 4660 */           ((ObjectArrayList)traccia_attivitaOri.get(traccia)).add(nome_attivita);
/*      */         }
/*      */       }
/*      */     }
/*      */     
/*      */ 
/*      */ 
/* 4667 */     if (!flag) {
/* 4668 */       time += System.currentTimeMillis();
/*      */     }
/*      */     
/* 4671 */     ObjectArrayList<Edge> lista_archi_unfolded = g.getLista_archi();
/*      */     
/*      */ 
/* 4674 */     Graph graph = new Graph();
/*      */     
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/* 4680 */     Object[] keys = mapOri.keys;
/* 4681 */     int[] values = mapOri.values;
/*      */     
/* 4683 */     for (int i = 0; i < mapOri.allocated.length; i++) {
/* 4684 */       if (mapOri.allocated[i] != 0) {
/* 4685 */         String key = (String)keys[i];
/* 4686 */         Integer value = Integer.valueOf(values[i]);
/* 4687 */         Node node = new Node(key, value.intValue());
/*      */         
/* 4689 */         if (!graph.getMap().containsKey(node))
/* 4690 */           graph.getMap().put(node, new ObjectOpenHashSet());
/*      */       }
/*      */     }
/* 4693 */     keys = g.getMap().keys;
/*      */     
/* 4695 */     Object[] vals = g.getMap().values;
/*      */     
/* 4697 */     for (int i = 0; i < g.getMap().allocated.length; i++) {
/* 4698 */       if (g.getMap().allocated[i] != 0) {
/* 4699 */         Node n = (Node)keys[i];
/*      */         
/* 4701 */         ObjectOpenHashSet<Node> n_adjacents = (ObjectOpenHashSet)vals[i];
/*      */         
/* 4703 */         int it1 = 0;
/*      */         
/* 4705 */         while (it1 < graph.listaNodi().size())
/*      */         {
/* 4707 */           Node newnode = (Node)graph.listaNodi().get(it1);
/*      */           
/* 4709 */           if (newnode.getNomeAttivita().equals(n.getNomeAttivita().split("#")[0]))
/*      */           {
/*      */ 
/*      */ 
/* 4713 */             for (ObjectCursor<Node> n_k : n_adjacents)
/*      */             {
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/* 4720 */               int it = 0;
/*      */               
/* 4722 */               while (it < graph.listaNodi().size())
/*      */               {
/* 4724 */                 Node new_n_k = (Node)graph.listaNodi().get(it);
/* 4725 */                 if (new_n_k.getNomeAttivita().equals(((Node)n_k.value).getNomeAttivita().split("#")[0]))
/*      */                 {
/*      */ 
/*      */ 
/*      */ 
/* 4730 */                   if (((ObjectOpenHashSet)graph.getMap().get(newnode)).contains(new_n_k)) break;
/* 4731 */                   for (ObjectCursor<Edge> e : lista_archi_unfolded) {
/* 4732 */                     if (((Edge)e.value).equals(new Edge(n, (Node)n_k.value))) {
/* 4733 */                       graph.addEdge(newnode, new_n_k, ((Edge)e.value).isFlag());
/*      */                       
/* 4735 */                       newnode.incr_Outer_degree();
/* 4736 */                       new_n_k.incr_Inner_degree();
/* 4737 */                       break;
/*      */                     }
/*      */                   }
/* 4740 */                   break;
/*      */                 }
/* 4742 */                 it++;
/*      */               }
/*      */             }
/*      */           }
/*      */           
/* 4747 */           it1++;
/*      */         }
/*      */       }
/*      */     }
/*      */     
/* 4752 */     time += System.currentTimeMillis() - time;
/*      */     
/* 4754 */     return graph;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */   public String getKeyByValue(ObjectIntOpenHashMap<String> map, int value)
/*      */   {
/* 4761 */     Object[] keys = map.keys;
/*      */     
/* 4763 */     for (int i = 0; i < map.allocated.length; i++) {
/* 4764 */       if ((map.allocated[i] != 0) && 
/* 4765 */         (value == map.values[i])) {
/* 4766 */         return (String)keys[i];
/*      */       }
/*      */     }
/* 4769 */     System.out.println("Errore key non trovata per id " + value);
/* 4770 */     return null;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public Graph createGraphFromPNML(String fileName, InputStream input, ObjectIntOpenHashMap<String> folded_map)
/*      */     throws XmlPullParserException, IOException
/*      */   {
/* 4857 */     XmlPullParserFactory factory = XmlPullParserFactory.newInstance();
/* 4858 */     factory.setNamespaceAware(true);
/* 4859 */     XmlPullParser xpp = factory.newPullParser();
/*      */     
/* 4861 */     xpp.setInput(input, null);
/* 4862 */     int eventType = xpp.getEventType();
/* 4863 */     Pnml pnml = new Pnml();
/*      */     
/* 4865 */     while (eventType != 2) {
/* 4866 */       eventType = xpp.next();
/*      */     }
/*      */     
/* 4869 */     if (xpp.getName().equals("pnml")) {
/* 4870 */       pnml.importElement(xpp, pnml);
/*      */     } else {
/* 4872 */       pnml.log("pnml", xpp.getLineNumber(), "Expected pnml");
/*      */     }
/* 4874 */     if (pnml.hasErrors()) {
/* 4875 */       return null;
/*      */     }
/*      */     
/*      */ 
/*      */ 
/* 4880 */     Petrinet petrinet = PetrinetFactory.newPetrinet(fileName);
/*      */     
/* 4882 */     pnml.convertToNet(petrinet, new Marking(), new GraphLayoutConnection(petrinet));
/*      */     
/* 4884 */     Graph g = new Graph();
/*      */     
/* 4886 */     Iterator<? extends Transition> it = petrinet.getTransitions().iterator();
/*      */     
/* 4888 */     HashMap<PetrinetNode, Node> hashmap = new HashMap();
/*      */     
/* 4890 */     while (it.hasNext())
/*      */     {
/* 4892 */       Transition t = (Transition)it.next();
/* 4893 */       String s = t.getLabel();
/*      */       
/* 4895 */       if (!s.startsWith("[")) {
/* 4896 */         if (s.contains("+")) {
/* 4897 */           s = s.split("\\+")[0];
/*      */         }
/* 4899 */         if (folded_map.containsKey(s)) {
/* 4900 */           Node n = new Node(s, folded_map.get(s));
/* 4901 */           g.getMap().put(n, new ObjectOpenHashSet());
/*      */           
/* 4903 */           hashmap.put(t, n);
/*      */         }
/*      */         else {
/* 4906 */           t.setInvisible(true);
/*      */         }
/*      */       } else {
/* 4909 */         t.setInvisible(true);
/*      */       }
/*      */     }
/* 4912 */     it = petrinet.getTransitions().iterator();
/*      */     
/* 4914 */     while (it.hasNext()) {
/* 4915 */       Transition t = (Transition)it.next();
/* 4916 */       if (!t.isInvisible()) {
/* 4917 */         Node n = (Node)hashmap.get(t);
/* 4918 */         for (Transition successor : t.getVisibleSuccessors()) {
/* 4919 */           if (!successor.isInvisible()) {
/* 4920 */             Node adjacent = (Node)hashmap.get(successor);
/* 4921 */             g.addEdge(n, adjacent, false);
/*      */           }
/*      */         }
/*      */       }
/*      */     }
/* 4926 */     return g;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */   private ObjectOpenHashSet<String> getPredecessors_FoldedLocal(String trace, String activity_x, String activity_y, ObjectObjectOpenHashMap<String, ObjectArrayList<String>> traccia_attivita)
/*      */   {
/* 4933 */     ObjectOpenHashSet<String> predecessors_traccia = new ObjectOpenHashSet();
/*      */     
/*      */ 
/* 4936 */     int i = 0;
/* 4937 */     while (i < ((ObjectArrayList)traccia_attivita.get(trace)).size())
/*      */     {
/* 4939 */       String activity_Z = (String)((ObjectArrayList)traccia_attivita.get(trace)).get(i);
/*      */       
/* 4941 */       if (activity_Z.equals(activity_x))
/*      */         break;
/* 4943 */       if (!activity_Z.split("#")[0].equals(activity_y.split("#")[0]))
/*      */       {
/* 4945 */         if (!predecessors_traccia.contains(activity_Z.split("#")[0])) {
/* 4946 */           predecessors_traccia.add(activity_Z.split("#")[0]);
/*      */         }
/*      */       }
/*      */       
/*      */ 
/* 4951 */       i++;
/*      */     }
/*      */     
/* 4954 */     return predecessors_traccia;
/*      */   }
/*      */   
/*      */ 
/*      */   public ObjectArrayList<Edge> removableEdges(Graph g, double[][] cs, ObjectArrayList<Constraint> folded_vincoli_positivi, ObjectIntOpenHashMap<String> folded_map, double relative_to_best)
/*      */   {
/* 4960 */     ObjectArrayList<Edge> removableEdges = new ObjectArrayList();
/* 4961 */     ObjectArrayList<Edge> listaArchi = new ObjectArrayList(g.getLista_archi());
/*      */     
/* 4963 */     for (ObjectCursor<Edge> ee : listaArchi) {
/* 4964 */       Edge e = (Edge)ee.value;
/*      */       
/*      */ 
/* 4967 */       if ((verificaVincoliPositivi(g, e.getX(), e.getY(), folded_vincoli_positivi, folded_map)) && 
/* 4968 */         (bestScore(g, e.getX(), e.getY(), cs) > relative_to_best))
/*      */       {
/* 4970 */         ObjectIntOpenHashMap<IntOpenHashSet> obX = e.getX().getOutput();
/*      */         
/* 4972 */         ObjectIntOpenHashMap<IntOpenHashSet> ibY = e.getY().getInput();
/* 4973 */         Object[] keys = obX.keys;
/*      */         
/* 4975 */         for (int i = 0; i < obX.allocated.length; i++) {
/* 4976 */           if (obX.allocated[i] != 0) {
/* 4977 */             IntOpenHashSet ts = (IntOpenHashSet)keys[i];
/* 4978 */             if ((ts.contains(e.getY().getID_attivita())) && (ts.size() == 1))
/*      */               break;
/*      */           }
/*      */         }
/* 4982 */         keys = ibY.keys;
/* 4983 */         for (int i = 0; i < ibY.allocated.length; i++) {
/* 4984 */           if (ibY.allocated[i] != 0) {
/* 4985 */             IntOpenHashSet ts = (IntOpenHashSet)keys[i];
/* 4986 */             if ((ts.contains(e.getX().getID_attivita())) && (ts.size() == 1)) {
/*      */               break;
/*      */             }
/*      */           }
/*      */         }
/*      */         
/*      */ 
/* 4993 */         removableEdges.add(e);
/*      */       }
/*      */     }
/*      */     
/*      */ 
/* 4998 */     return removableEdges;
/*      */   }
/*      */   
/*      */   public double bestScore(Graph g, Node x, Node y, double[][] csm)
/*      */   {
/* 5003 */     double bestcsOutX = 2.2250738585072014E-308D;
/*      */     
/* 5005 */     double bestcsInY = 2.2250738585072014E-308D;
/*      */     
/* 5007 */     for (int i = 0; i < g.adjacentNodes(x).size(); i++) {
/* 5008 */       Node adjacent = (Node)g.adjacentNodes(x).get(i);
/* 5009 */       if (csm[x.getID_attivita()][adjacent.getID_attivita()] > bestcsOutX)
/* 5010 */         bestcsOutX = csm[x.getID_attivita()][adjacent.getID_attivita()];
/*      */     }
/* 5012 */     for (int i = 0; i < g.listaNodi().size(); i++)
/*      */     {
/* 5014 */       Node adjacent = (Node)g.listaNodi().get(i);
/*      */       
/*      */ 
/* 5017 */       if ((g.isConnected(adjacent, y)) && (csm[adjacent.getID_attivita()][y.getID_attivita()] > bestcsInY)) {
/* 5018 */         bestcsInY = csm[adjacent.getID_attivita()][y.getID_attivita()];
/*      */       }
/*      */     }
/* 5021 */     double bestScore = bestcsOutX < bestcsInY ? bestcsOutX : bestcsInY;
/*      */     
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/* 5027 */     return 1.0D - csm[x.getID_attivita()][y.getID_attivita()] / bestScore;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   private ObjectOpenHashSet<String> getSuccessors_FoldedLocal(String trace, String activity_x, String activity_y, ObjectObjectOpenHashMap<String, ObjectArrayList<String>> traccia_attivita)
/*      */   {
/* 5061 */     ObjectOpenHashSet<String> successors_traccia = new ObjectOpenHashSet();
/*      */     
/*      */ 
/*      */ 
/* 5065 */     int i = ((ObjectArrayList)traccia_attivita.get(trace)).size() - 1;
/*      */     
/* 5067 */     while (i >= 0)
/*      */     {
/* 5069 */       String activity_W = (String)((ObjectArrayList)traccia_attivita.get(trace)).get(i);
/*      */       
/* 5071 */       if (activity_W.equals(activity_x))
/*      */         break;
/* 5073 */       if (!activity_W.split("#")[0].equals(activity_y.split("#")[0]))
/*      */       {
/* 5075 */         if (!successors_traccia.contains(activity_W.split("#")[0])) {
/* 5076 */           successors_traccia.add(activity_W.split("#")[0]);
/*      */         }
/*      */       }
/*      */       
/*      */ 
/* 5081 */       i--;
/*      */     }
/*      */     
/* 5084 */     return successors_traccia;
/*      */   }
/*      */   
/*      */ 
/*      */   public ObjectArrayList<Constraint> getUnsatisfiedNegativeConstraints(Graph folded_g, ObjectArrayList<Constraint> vincoli_negati, ObjectIntOpenHashMap<String> folded_map)
/*      */   {
/* 5090 */     ObjectArrayList<Constraint> unsat_constr_list = new ObjectArrayList();
/* 5091 */     for (int i = 0; i < vincoli_negati.size(); i++) {
/* 5092 */       Constraint constr = (Constraint)vincoli_negati.get(i);
/* 5093 */       if (constr.isPathConstraint())
/*      */       {
/* 5095 */         for (ObjectCursor<Node> e : folded_g.listaNodi())
/* 5096 */           ((Node)e.value).setMark(false);
/*      */         Iterator localIterator2;
/* 5098 */         for (??? = constr.getHeadList().iterator(); ???.hasNext(); 
/*      */             
/*      */ 
/* 5101 */             localIterator2.hasNext())
/*      */         {
/* 5098 */           String head = (String)???.next();
/*      */           
/*      */ 
/* 5101 */           localIterator2 = constr.getBodyList().iterator(); continue;String body = (String)localIterator2.next();
/*      */           
/*      */ 
/*      */ 
/* 5105 */           if (folded_g.isConnected(folded_g.getNode(body, folded_map.get(body)), folded_g.getNode(head, folded_map.get(head)))) {
/* 5106 */             unsat_constr_list.add(constr);
/* 5107 */             break;
/*      */           }
/*      */           
/*      */ 
/*      */ 
/* 5112 */           if (bfs(folded_g, folded_g.getNode(body, folded_map.get(body)), folded_g.getNode(head, folded_map.get(head)), null, null)) {
/* 5113 */             unsat_constr_list.add(constr);
/*      */             
/*      */ 
/* 5116 */             break;
/*      */           }
/*      */           
/* 5119 */           for (ObjectCursor<Node> e : folded_g.listaNodi()) {
/* 5120 */             ((Node)e.value).setMark(false);
/*      */           }
/*      */         }
/*      */       }
/*      */     }
/*      */     
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/* 5141 */     return unsat_constr_list;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */   public void postProcessing_dip_indirette(Graph g, ObjectIntOpenHashMap<String> map, ObjectObjectOpenHashMap<String, ObjectArrayList<String>> attivita_tracce, ObjectObjectOpenHashMap<String, ObjectArrayList<String>> traccia_attivita, double[][] cs, double sigma_2, ObjectArrayList<Constraint> vincoli_positivi)
/*      */   {
/*      */     ObjectArrayList<Node> adjacents;
/*      */     
/*      */ 
/*      */     int cursor;
/*      */     
/* 5153 */     for (Iterator localIterator1 = g.listaNodi().iterator(); localIterator1.hasNext(); 
/*      */         
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/* 5164 */         cursor < adjacents.size())
/*      */     {
/* 5153 */       ObjectCursor<Node> nn = (ObjectCursor)localIterator1.next();
/*      */       
/* 5155 */       Node n = (Node)nn.value;
/*      */       
/*      */ 
/* 5158 */       adjacents = g.adjacentNodes(n);
/* 5159 */       adjacents.trimToSize();
/*      */       
/*      */ 
/* 5162 */       cursor = 0;
/*      */       
/* 5164 */       continue;
/*      */       
/* 5166 */       Node adjacent_i = (Node)adjacents.get(cursor);
/*      */       
/* 5168 */       if ((n.getOuter_degree() == 1) || (adjacent_i.getInner_degree() == 1)) {
/* 5169 */         cursor++;
/*      */       }
/*      */       else {
/* 5172 */         ObjectOpenHashSet<String> candidati = new ObjectOpenHashSet();
/*      */         
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/* 5178 */         for (ObjectCursor<Node> mm : g.listaNodi()) {
/* 5179 */           Node m = (Node)mm.value;
/*      */           
/* 5181 */           if ((!m.equals(n)) && (!m.equals(adjacent_i)))
/*      */           {
/* 5183 */             for (ObjectCursor<Node> e : g.listaNodi()) {
/* 5184 */               ((Node)e.value).setMark(false);
/*      */             }
/*      */             
/* 5187 */             boolean condizione_1 = bfs(g, n, m, adjacent_i, null);
/*      */             
/*      */ 
/* 5190 */             for (Object e : g.listaNodi()) {
/* 5191 */               ((Node)((ObjectCursor)e).value).setMark(false);
/*      */             }
/*      */             
/*      */ 
/*      */ 
/* 5196 */             boolean condizione_2 = g.isConnected(m, adjacent_i);
/*      */             
/*      */ 
/*      */ 
/* 5200 */             if ((condizione_1) && (condizione_2))
/*      */             {
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/* 5206 */               candidati.add(m.getNomeAttivita());
/*      */             }
/*      */           }
/*      */         }
/*      */         
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/* 5215 */         if (candidati.size() > 0)
/*      */         {
/*      */ 
/*      */ 
/*      */ 
/* 5220 */           if (!verificaVincoliPositivi(g, n, adjacent_i, vincoli_positivi, map))
/*      */           {
/*      */ 
/* 5223 */             cursor++;
/* 5224 */             continue;
/*      */           }
/*      */           
/*      */ 
/* 5228 */           ObjectArrayList<String> lista_tracce_n = new ObjectArrayList((ObjectContainer)attivita_tracce.get(n.getNomeAttivita()));
/* 5229 */           lista_tracce_n.trimToSize();
/*      */           
/* 5231 */           Object lista_tracce_i = new ObjectOpenHashSet((ObjectContainer)attivita_tracce.get(adjacent_i.getNomeAttivita()));
/*      */           
/*      */ 
/* 5234 */           lista_tracce_n.retainAll((ObjectLookupContainer)lista_tracce_i);
/*      */           
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/* 5243 */           boolean rimuovi_arco = true;
/*      */           
/* 5245 */           if (lista_tracce_n.size() == 0)
/*      */           {
/* 5247 */             rimuovi_arco = false;
/*      */           }
/*      */           
/* 5250 */           int counter = 0;
/*      */           
/* 5252 */           int it1 = 0;
/* 5253 */           while ((it1 < lista_tracce_n.size()) && (rimuovi_arco))
/*      */           {
/* 5255 */             String trace_1 = (String)lista_tracce_n.get(it1);
/*      */             
/*      */ 
/* 5258 */             if (!esisteAttivatore(trace_1, n.getNomeAttivita(), adjacent_i.getNomeAttivita(), traccia_attivita, candidati, true, false, false)) {
/* 5259 */               counter++;
/* 5260 */               if (counter > sigma_2 * lista_tracce_n.size())
/*      */               {
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/* 5274 */                 rimuovi_arco = false;
/*      */               }
/*      */             }
/* 5277 */             it1++;
/*      */           }
/*      */           
/*      */ 
/*      */ 
/* 5282 */           if (rimuovi_arco) {
/* 5283 */             g.removeEdge(n, adjacent_i);
/*      */             
/*      */ 
/*      */ 
/* 5287 */             n.decr_Outer_degree();
/* 5288 */             adjacent_i.decr_Inner_degree();
/*      */           }
/*      */         }
/*      */         
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/* 5298 */         cursor++;
/*      */       }
/*      */     }
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */   public void postProcessing_paralleli(Graph g, double[][] csmOri, ObjectIntOpenHashMap<String> map, ObjectObjectOpenHashMap<String, ObjectArrayList<String>> attivita_tracce, ObjectObjectOpenHashMap<String, ObjectArrayList<String>> traccia_attivita, double sigma_up_cs_diff, double sigma_log_noise, ObjectArrayList<Constraint> vincoli_positivi)
/*      */   {
/*      */     ObjectArrayList<Node> adjacents;
/*      */     
/*      */     int it;
/*      */     
/* 5311 */     for (Iterator localIterator = g.listaNodi().iterator(); localIterator.hasNext(); 
/*      */         
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/* 5318 */         it < adjacents.size())
/*      */     {
/* 5311 */       ObjectCursor<Node> nn = (ObjectCursor)localIterator.next();
/* 5312 */       Node n = (Node)nn.value;
/*      */       
/* 5314 */       adjacents = g.adjacentNodes(n);
/*      */       
/*      */ 
/* 5317 */       it = 0;
/* 5318 */       continue;
/*      */       
/*      */ 
/* 5321 */       Node adjacent_i = (Node)adjacents.get(it);
/*      */       
/*      */ 
/*      */ 
/*      */ 
/* 5326 */       if ((n.getOuter_degree() > 1) && (adjacent_i.getInner_degree() > 1)) {
/* 5327 */         if (!verificaVincoliPositivi(g, n, adjacent_i, vincoli_positivi, map))
/*      */         {
/*      */ 
/* 5330 */           it++;
/* 5331 */           continue;
/*      */         }
/*      */         
/* 5334 */         if ((checkTrueEdge_CS2(n, adjacent_i, adjacents, csmOri, g, map, attivita_tracce, traccia_attivita, sigma_up_cs_diff, sigma_log_noise)) && 
/* 5335 */           (follows(n.getNomeAttivita(), adjacent_i.getNomeAttivita(), traccia_attivita, attivita_tracce, sigma_log_noise)))
/*      */         {
/* 5337 */           g.removeEdge(n, adjacent_i);
/*      */           
/*      */ 
/*      */ 
/* 5341 */           n.decr_Outer_degree();
/* 5342 */           adjacent_i.decr_Inner_degree();
/*      */         }
/*      */       }
/*      */       
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/* 5354 */       it++;
/*      */     }
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public boolean verificaVincoliPositivi(Graph graph, Node np, Node nr, ObjectArrayList<Constraint> vincoli_positivi, ObjectIntOpenHashMap<String> map)
/*      */   {
/* 5440 */     if ((np != null) && (nr != null)) {
/* 5441 */       graph.removeEdge(np, nr);
/*      */     }
/* 5443 */     for (ObjectCursor<Constraint> cc : vincoli_positivi)
/*      */     {
/* 5445 */       Constraint c = (Constraint)cc.value;
/*      */       
/* 5447 */       boolean path_constraint = c.isPathConstraint();
/*      */       
/* 5449 */       boolean vincolo_soddisfatto = false;
/*      */       Iterator localIterator3;
/* 5451 */       for (Iterator localIterator2 = c.getHeadList().iterator(); localIterator2.hasNext(); 
/*      */           
/*      */ 
/*      */ 
/* 5455 */           localIterator3.hasNext())
/*      */       {
/* 5451 */         String head = (String)localIterator2.next();
/*      */         
/* 5453 */         Node nHead = graph.getNode(head, map.get(head));
/*      */         
/* 5455 */         localIterator3 = c.getBodyList().iterator(); continue;String body = (String)localIterator3.next();
/*      */         
/* 5457 */         Node nBody = graph.getNode(body, map.get(body));
/*      */         
/*      */ 
/*      */ 
/* 5461 */         if (graph.isConnected(nBody, nHead)) {
/* 5462 */           vincolo_soddisfatto = true;
/*      */           
/* 5464 */           break; }
/* 5465 */         if (path_constraint)
/*      */         {
/*      */ 
/* 5468 */           for (int ni = 0; ni < graph.listaNodi().size(); ni++) {
/* 5469 */             Node n = (Node)graph.listaNodi().get(ni);
/* 5470 */             n.setMark(false);
/*      */           }
/*      */           
/* 5473 */           if (bfs(graph, nBody, nHead, null, null)) {
/* 5474 */             vincolo_soddisfatto = true;
/*      */             
/* 5476 */             break;
/*      */           }
/*      */         }
/*      */       }
/*      */       
/*      */ 
/* 5482 */       if (!vincolo_soddisfatto)
/*      */       {
/*      */ 
/*      */ 
/* 5486 */         if ((np != null) && (nr != null))
/* 5487 */           graph.addEdge(np, nr, false);
/* 5488 */         return false;
/*      */       }
/*      */     }
/*      */     
/*      */ 
/*      */ 
/* 5494 */     if ((np != null) && (nr != null))
/* 5495 */       graph.addEdge(np, nr, false);
/* 5496 */     return true;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public void computeBindings(Graph g, ObjectObjectOpenHashMap<String, ObjectArrayList<String>> traccia_attivita, ObjectIntOpenHashMap<String> map)
/*      */   {
/* 5645 */     Object[] values = traccia_attivita.values;
/* 5646 */     ObjectArrayList<String> traccia; for (int it1 = 0; it1 < traccia_attivita.allocated.length; it1++) {
/* 5647 */       if (traccia_attivita.allocated[it1] != 0) {
/* 5648 */         traccia = (ObjectArrayList)values[it1];
/*      */         
/* 5650 */         IntOpenHashSet[] outputBindings = new IntOpenHashSet[traccia.size()];
/* 5651 */         IntArrayList[] outputBindingsExtended = new IntArrayList[traccia.size()];
/*      */         
/* 5653 */         IntOpenHashSet[] inputBindings = new IntOpenHashSet[traccia.size()];
/* 5654 */         IntArrayList[] inputBindingsExtended = new IntArrayList[traccia.size()];
/*      */         
/* 5656 */         for (int i = 0; i < traccia.size(); i++) {
/* 5657 */           outputBindings[i] = new IntOpenHashSet();
/* 5658 */           outputBindingsExtended[i] = new IntArrayList();
/* 5659 */           inputBindings[i] = new IntOpenHashSet();
/* 5660 */           inputBindingsExtended[i] = new IntArrayList();
/*      */         }
/*      */         
/* 5663 */         int[] activitiesIDMapping = new int[traccia.size()];
/*      */         
/* 5665 */         for (int i = 0; i < traccia.size(); i++)
/*      */         {
/* 5667 */           String activity = (String)traccia.get(i);
/* 5668 */           activitiesIDMapping[i] = map.get(activity);
/*      */           
/* 5670 */           boolean verificato = false;
/*      */           
/*      */ 
/* 5673 */           for (int j = i + 1; j < traccia.size(); j++)
/*      */           {
/* 5675 */             String successor = (String)traccia.get(j);
/*      */             
/* 5677 */             if (g.isConnected(new Node(activity, map.get(activity)), new Node(successor, map.get(successor))))
/*      */             {
/* 5679 */               if (!verificato)
/*      */               {
/* 5681 */                 if (!outputBindings[i].contains(map.get(successor)))
/* 5682 */                   outputBindings[i].add(map.get(successor));
/* 5683 */                 if (!inputBindings[j].contains(map.get(activity))) {
/* 5684 */                   inputBindings[j].add(map.get(activity));
/*      */                 }
/*      */                 
/* 5687 */                 outputBindingsExtended[i].add(map.get(successor));
/*      */                 
/* 5689 */                 inputBindingsExtended[j].add(map.get(activity));
/*      */                 
/* 5691 */                 verificato = true;
/*      */ 
/*      */               }
/*      */               else
/*      */               {
/* 5696 */                 outputBindingsExtended[i].add(map.get(successor));
/*      */                 
/* 5698 */                 inputBindingsExtended[j].add(map.get(activity));
/*      */               }
/*      */             }
/*      */           }
/*      */           
/* 5703 */           verificato = false;
/*      */           
/*      */ 
/* 5706 */           for (int j = i - 1; j >= 0; j--)
/*      */           {
/* 5708 */             String predecessor = (String)traccia.get(j);
/*      */             
/*      */ 
/* 5711 */             if (g.isConnected(new Node(predecessor, map.get(predecessor)), new Node(activity, map.get(activity))))
/*      */             {
/* 5713 */               if (!verificato)
/*      */               {
/* 5715 */                 if (!outputBindings[j].contains(map.get(activity))) {
/* 5716 */                   outputBindings[j].add(map.get(activity));
/*      */                 }
/* 5718 */                 if (!inputBindings[i].contains(map.get(predecessor))) {
/* 5719 */                   inputBindings[i].add(map.get(predecessor));
/*      */                 }
/*      */                 
/* 5722 */                 inputBindingsExtended[i].add(map.get(predecessor));
/*      */                 
/* 5724 */                 outputBindingsExtended[j].add(map.get(activity));
/*      */                 
/* 5726 */                 verificato = true;
/*      */ 
/*      */               }
/*      */               else
/*      */               {
/* 5731 */                 inputBindingsExtended[i].add(map.get(predecessor));
/*      */                 
/* 5733 */                 outputBindingsExtended[j].add(map.get(activity));
/*      */               }
/*      */             }
/*      */           }
/*      */         }
/*      */         
/*      */ 
/*      */ 
/* 5741 */         for (int k = 0; k < activitiesIDMapping.length; k++)
/*      */         {
/* 5743 */           if (!g.getNode(getKeyByValue(map, activitiesIDMapping[k]), activitiesIDMapping[k]).getOutput().containsKey(outputBindings[k])) {
/* 5744 */             g.getNode(getKeyByValue(map, activitiesIDMapping[k]), activitiesIDMapping[k]).getOutput().put(outputBindings[k], 1);
/*      */           }
/*      */           
/* 5747 */           if (!g.getNode(getKeyByValue(map, activitiesIDMapping[k]), activitiesIDMapping[k]).getInput().containsKey(inputBindings[k]))
/*      */           {
/* 5749 */             g.getNode(getKeyByValue(map, activitiesIDMapping[k]), activitiesIDMapping[k]).getInput().put(inputBindings[k], 1);
/*      */           }
/* 5751 */           if (!g.getNode(getKeyByValue(map, activitiesIDMapping[k]), activitiesIDMapping[k]).getExtendedOutput().containsKey(outputBindingsExtended[k]))
/*      */           {
/* 5753 */             g.getNode(getKeyByValue(map, activitiesIDMapping[k]), activitiesIDMapping[k]).getExtendedOutput().put(outputBindingsExtended[k], 1);
/*      */           }
/*      */           
/* 5756 */           if (!g.getNode(getKeyByValue(map, activitiesIDMapping[k]), activitiesIDMapping[k]).getExtendedInput().containsKey(inputBindingsExtended[k]))
/*      */           {
/* 5758 */             g.getNode(getKeyByValue(map, activitiesIDMapping[k]), activitiesIDMapping[k]).getExtendedInput().put(inputBindingsExtended[k], 1);
/*      */           }
/*      */         }
/*      */       }
/*      */     }
/*      */     
/* 5764 */     for (ObjectCursor<Edge> ee : g.getLista_archi()) {
/* 5765 */       Edge e = (Edge)ee.value;
/* 5766 */       if (e.isFlag())
/*      */       {
/*      */ 
/*      */ 
/*      */ 
/* 5771 */         IntOpenHashSet treeSetOut = new IntOpenHashSet();
/* 5772 */         treeSetOut.add(e.getY().getID_attivita());
/* 5773 */         if (!e.getX().getOutput().containsKey(treeSetOut)) {
/* 5774 */           e.getX().getOutput().put(treeSetOut, 1);
/* 5775 */           IntOpenHashSet treeSetIn = new IntOpenHashSet();
/* 5776 */           treeSetIn.add(e.getX().getID_attivita());
/* 5777 */           e.getY().getInput().put(treeSetIn, 1);
/*      */         }
/*      */       }
/*      */     }
/*      */   }
/*      */ }


/* Location:              /home/menick/Scrivania/ProcessMining/CNMining/CNMining.jar!/org/processmining/plugins/core/CNMining.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       0.7.1
 */