/*     */ package uk.ac.shef.wit.simmetrics.task;
/*     */ 
/*     */ import java.beans.XMLEncoder;
/*     */ import java.io.BufferedOutputStream;
/*     */ import java.io.File;
/*     */ import java.io.FileNotFoundException;
/*     */ import java.io.FileOutputStream;
/*     */ import java.util.ArrayList;
/*     */ import uk.ac.shef.wit.simmetrics.similaritymetrics.AbstractStringMetric;
/*     */ import uk.ac.shef.wit.simmetrics.similaritymetrics.Levenshtein;
/*     */ import uk.ac.shef.wit.simmetrics.similaritymetrics.MongeElkan;
/*     */ import uk.ac.shef.wit.simmetrics.utils.FileLoader;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class SimpleTask
/*     */   implements InterfaceTask
/*     */ {
/*     */   public static void main(String[] args)
/*     */   {
/*  66 */     SimpleTask task = new SimpleTask();
/*     */   }
/*     */   
/*     */ 
/*     */   public SimpleTask()
/*     */   {
/*  72 */     XMLEncoder e = null;
/*     */     try {
/*  74 */       e = new XMLEncoder(new BufferedOutputStream(new FileOutputStream("C:\\Config.xml")));
/*     */     }
/*     */     catch (FileNotFoundException e1)
/*     */     {
/*  78 */       e1.printStackTrace();
/*     */     }
/*  80 */     ArrayList<AbstractStringMetric> array = new ArrayList();
/*  81 */     AbstractStringMetric metric = new MongeElkan(new Levenshtein());
/*     */     
/*  83 */     array.add(metric);
/*  84 */     assert (e != null);
/*  85 */     e.writeObject(array);
/*     */     
/*  87 */     e.close();
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public TaskResult RunTask()
/*     */   {
/*  96 */     return null;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public void ParseTask(String path)
/*     */   {
/* 105 */     StringBuffer fileContent = FileLoader.fileToString(new File(path));
/*     */   }
/*     */ }


/* Location:              /home/menick/Scrivania/ProcessMining/CNMining/CNMining.jar!/uk/ac/shef/wit/simmetrics/task/SimpleTask.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       0.7.1
 */