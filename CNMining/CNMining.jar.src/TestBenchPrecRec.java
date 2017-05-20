/*     */ import com.carrotsearch.hppc.ObjectArrayList;
/*     */ import com.carrotsearch.hppc.ObjectIntOpenHashMap;
/*     */ import com.carrotsearch.hppc.ObjectObjectOpenHashMap;
/*     */ import com.carrotsearch.hppc.ObjectOpenHashSet;
/*     */ import java.io.BufferedReader;
/*     */ import java.io.File;
/*     */ import java.io.FileInputStream;
/*     */ import java.io.FileNotFoundException;
/*     */ import java.io.FileReader;
/*     */ import java.io.IOException;
/*     */ import java.io.InputStream;
/*     */ import java.io.PrintStream;
/*     */ import java.nio.file.FileSystem;
/*     */ import java.nio.file.FileSystems;
/*     */ import java.nio.file.Files;
/*     */ import java.nio.file.OpenOption;
/*     */ import java.nio.file.StandardOpenOption;
/*     */ import java.util.Collection;
/*     */ import java.util.HashMap;
/*     */ import java.util.Iterator;
/*     */ import java.util.Map;
/*     */ import java.util.StringTokenizer;
/*     */ import java.util.TreeMap;
/*     */ import org.processmining.models.connections.GraphLayoutConnection;
/*     */ import org.processmining.models.graphbased.directed.petrinet.Petrinet;
/*     */ import org.processmining.models.graphbased.directed.petrinet.PetrinetNode;
/*     */ import org.processmining.models.graphbased.directed.petrinet.elements.Transition;
/*     */ import org.processmining.models.graphbased.directed.petrinet.impl.PetrinetFactory;
/*     */ import org.processmining.models.semantics.petrinet.Marking;
/*     */ import org.processmining.plugins.core.Graph;
/*     */ import org.processmining.plugins.core.Node;
/*     */ import org.processmining.plugins.pnml.Pnml;
/*     */ import org.xmlpull.v1.XmlPullParser;
/*     */ import org.xmlpull.v1.XmlPullParserException;
/*     */ import org.xmlpull.v1.XmlPullParserFactory;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class TestBenchPrecRec
/*     */ {
/*     */   public static void main(String[] args)
/*     */     throws Exception
/*     */   {
/*  54 */     String[] methods = { "Ours+BK", "AGNES+BK", "alpha+BK", "ILP+BK", "alpha+BK_new" };
/*     */     
/*  56 */     String filename = "benchLogsRIDOTTI-Fmeasures.csv";
/*  57 */     String content = "Log;Method;Precision;Recall;F-measure\n";
/*     */     
/*     */ 
/*  60 */     File file = new File(filename);
/*  61 */     if (file.exists())
/*  62 */       file.delete();
/*  63 */     file.createNewFile();
/*     */     try
/*     */     {
/*  66 */       Files.write(FileSystems.getDefault().getPath(".", new String[] { filename }), content.getBytes(), new OpenOption[] {
/*  67 */         StandardOpenOption.APPEND });
/*     */     } catch (IOException ioe) {
/*  69 */       ioe.printStackTrace();
/*     */     }
/*     */     
/*     */ 
/*  73 */     File dir = new File("/Users/flupia/Desktop/Esperimenti_TKDD/tkdd13/desiredHN2");
/*     */     
/*  75 */     File[] list = dir.listFiles();
/*     */     File[] arrayOfFile1;
/*  77 */     int j = (arrayOfFile1 = list).length; for (int i = 0; i < j; i++) { File f = arrayOfFile1[i];
/*     */       
/*  79 */       if (!f.getName().equals(".DS_Store"))
/*     */       {
/*     */ 
/*  82 */         String hnFileName = f.getAbsolutePath();
/*     */         
/*  84 */         ObjectIntOpenHashMap<String> taskMap = new ObjectIntOpenHashMap();
/*     */         
/*  86 */         Map<Integer, String> revTaskMap = new TreeMap();
/*     */         
/*  88 */         loadMappingFromHN(taskMap, revTaskMap, hnFileName);
/*     */         
/*  90 */         StringTokenizer st = new StringTokenizer(f.getName(), ".");
/*     */         
/*  92 */         String log = st.nextToken();
/*     */         
/*  94 */         System.out.println(log);
/*     */         
/*     */ 
/*     */ 
/*  98 */         InputStream input = new FileInputStream("/Users/flupia/Desktop/Esperimenti_TKDD/tkdd13/desiredPNML2/" + log + ".pnml");
/*     */         
/* 100 */         Graph g = null;
/*     */         
/* 102 */         g = createGraphFromPNML("trueModel", input, taskMap);
/*     */         
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/* 112 */         boolean[][] realMatrix = generaAdjacentsMatrix(g);
/*     */         
/* 114 */         for (int i1 = 0; i1 < methods.length; i1++)
/*     */         {
/* 116 */           double precision = 0.0D;
/* 117 */           double recall = 0.0D;
/*     */           
/* 119 */           System.out.println();
/* 120 */           System.out.println("method " + f.getName());
/* 121 */           System.out.println();
/*     */           
/* 123 */           InputStream input2 = null;
/*     */           try {
/* 125 */             input2 = new FileInputStream("/Users/flupia/Desktop/cartella senza titolo 2/" + log + "-red_" + methods[i1] + 
/* 126 */               ".pnml");
/*     */           }
/*     */           catch (FileNotFoundException fe)
/*     */           {
/*     */             continue;
/*     */           }
/* 132 */           Graph g2 = null;
/*     */           
/* 134 */           System.out.println(methods[i1]);
/*     */           
/* 136 */           g2 = createGraphFromPNML(methods[i1], input2, taskMap);
/*     */           
/* 138 */           boolean[][] testMatrix1 = generaAdjacentsMatrix(g2);
/*     */           
/*     */ 
/* 141 */           double[] performance2 = computePrecisionRecall(testMatrix1, realMatrix);
/*     */           
/* 143 */           precision = performance2[0];
/* 144 */           recall = performance2[1];
/*     */           
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/* 157 */           double f_measure = 2.0D * precision * recall / (precision + recall);
/* 158 */           content = log + ";" + methods[i1] + ";" + precision + ";" + recall + ";" + f_measure + "\n";
/*     */           try
/*     */           {
/* 161 */             Files.write(FileSystems.getDefault().getPath(".", new String[] { filename }), content.getBytes(), new OpenOption[] {
/* 162 */               StandardOpenOption.APPEND });
/*     */           } catch (IOException ioe) {
/* 164 */             ioe.printStackTrace();
/*     */           }
/*     */         }
/*     */       }
/*     */     }
/*     */   }
/*     */   
/*     */ 
/*     */   private static void loadMappingFromHN(ObjectIntOpenHashMap<String> taskMap, Map<Integer, String> revTaskMap, String hnFileName)
/*     */     throws Exception
/*     */   {
/* 175 */     String line = null;
/* 176 */     BufferedReader in = new BufferedReader(new FileReader(hnFileName));
/*     */     
/* 178 */     in.readLine();
/* 179 */     in.readLine();
/* 180 */     in.readLine();
/* 181 */     in.readLine();
/* 182 */     in.readLine();
/*     */     
/* 184 */     line = in.readLine();
/* 185 */     System.out.println(line);
/* 186 */     while ((line != null) && (line.length() > 0)) {
/* 187 */       StringTokenizer st = new StringTokenizer(line, ":@&");
/* 188 */       String taskLabel = st.nextToken();
/*     */       
/* 190 */       st.nextToken();
/* 191 */       String taskIDstr = st.nextToken();
/* 192 */       int taskNr = Integer.parseInt(taskIDstr);
/* 193 */       taskMap.put(taskLabel, taskNr);
/* 194 */       revTaskMap.put(Integer.valueOf(taskNr), taskLabel);
/* 195 */       line = in.readLine();
/*     */     }
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public static Graph createGraphFromPNML(String fileName, InputStream input, ObjectIntOpenHashMap<String> folded_map)
/*     */     throws XmlPullParserException, IOException
/*     */   {
/* 214 */     XmlPullParserFactory factory = XmlPullParserFactory.newInstance();
/* 215 */     factory.setNamespaceAware(true);
/* 216 */     XmlPullParser xpp = factory.newPullParser();
/*     */     
/* 218 */     xpp.setInput(input, null);
/* 219 */     int eventType = xpp.getEventType();
/* 220 */     Pnml pnml = new Pnml();
/*     */     
/* 222 */     while (eventType != 2) {
/* 223 */       eventType = xpp.next();
/*     */     }
/*     */     
/* 226 */     if (xpp.getName().equals("pnml")) {
/* 227 */       pnml.importElement(xpp, pnml);
/*     */     } else {
/* 229 */       pnml.log("pnml", xpp.getLineNumber(), "Expected pnml");
/*     */     }
/* 231 */     if (pnml.hasErrors()) {
/* 232 */       return null;
/*     */     }
/*     */     
/*     */ 
/*     */ 
/* 237 */     Petrinet petrinet = PetrinetFactory.newPetrinet(fileName);
/*     */     
/* 239 */     pnml.convertToNet(petrinet, new Marking(), new GraphLayoutConnection(petrinet));
/*     */     
/* 241 */     Graph g = new Graph();
/*     */     
/* 243 */     Iterator<? extends Transition> it = petrinet.getTransitions().iterator();
/*     */     
/* 245 */     HashMap<PetrinetNode, Node> hashmap = new HashMap();
/*     */     
/* 247 */     while (it.hasNext())
/*     */     {
/* 249 */       Transition t = (Transition)it.next();
/* 250 */       String s = t.getLabel();
/*     */       
/* 252 */       if (!s.startsWith("[")) {
/* 253 */         if (s.contains("\\n")) {
/* 254 */           s = s.split("\\n")[0];
/*     */         }
/* 256 */         if (folded_map.containsKey(s)) {
/* 257 */           Node n = new Node(s, folded_map.get(s));
/* 258 */           g.getMap().put(n, new ObjectOpenHashSet());
/*     */           
/* 260 */           hashmap.put(t, n);
/*     */         }
/*     */         else {
/* 263 */           t.setInvisible(true);
/*     */         }
/*     */       } else {
/* 266 */         t.setInvisible(true);
/*     */       }
/*     */     }
/* 269 */     it = petrinet.getTransitions().iterator();
/*     */     
/* 271 */     while (it.hasNext()) {
/* 272 */       Transition t = (Transition)it.next();
/* 273 */       if (!t.isInvisible()) {
/* 274 */         Node n = (Node)hashmap.get(t);
/* 275 */         for (Transition successor : t.getVisibleSuccessors()) {
/* 276 */           if (!successor.isInvisible()) {
/* 277 */             Node adjacent = (Node)hashmap.get(successor);
/* 278 */             g.addEdge(n, adjacent, false);
/*     */           }
/*     */         }
/*     */       }
/*     */     }
/* 283 */     return g;
/*     */   }
/*     */   
/*     */   public static double[] computePrecisionRecall(boolean[][] testMatrix, boolean[][] realMatrix) {
/* 287 */     int TP = 0;int FP = 0;int TN = 0;int FN = 0;
/*     */     
/* 289 */     int r = testMatrix.length;
/* 290 */     int c = testMatrix[0].length;
/*     */     
/* 292 */     for (int i = 0; i < r; i++) {
/* 293 */       for (int j = 0; j < c; j++)
/*     */       {
/* 295 */         boolean estimated = testMatrix[i][j];
/* 296 */         boolean real = realMatrix[i][j];
/* 297 */         if (estimated) {
/* 298 */           if (real) {
/* 299 */             TP++;
/*     */           } else {
/* 301 */             FP++;
/*     */           }
/* 303 */         } else if (!real) {
/* 304 */           TN++;
/*     */         } else {
/* 306 */           FN++;
/*     */         }
/*     */       }
/*     */     }
/*     */     double prec;
/*     */     double prec;
/* 312 */     if (TP + FP > 0) {
/* 313 */       prec = TP / (TP + FP);
/*     */     } else
/* 315 */       prec = 1.0D;
/* 316 */     double rec; double rec; if (TP + FN > 0) {
/* 317 */       rec = TP / (TP + FN);
/*     */     } else
/* 319 */       rec = 0.0D;
/* 320 */     return new double[] { prec, rec };
/*     */   }
/*     */   
/*     */ 
/*     */   public static boolean[][] generaAdjacentsMatrix(Graph folded_g)
/*     */   {
/* 326 */     boolean[][] adjacentsMatrix = new boolean[folded_g.listaNodi().size()][folded_g.listaNodi().size()];
/*     */     
/* 328 */     for (int i = 0; i < folded_g.listaNodi().size(); i++) {
/* 329 */       Node n = (Node)folded_g.listaNodi().get(i);
/* 330 */       for (int j = 0; j < folded_g.adjacentNodes(n).size(); j++) {
/* 331 */         Node adjacent = (Node)folded_g.adjacentNodes(n).get(j);
/* 332 */         adjacentsMatrix[n.getID_attivita()][adjacent.getID_attivita()] = 1;
/*     */       }
/*     */     }
/* 335 */     return adjacentsMatrix;
/*     */   }
/*     */ }


/* Location:              /home/menick/Scrivania/ProcessMining/CNMining/CNMining.jar!/TestBenchPrecRec.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       0.7.1
 */