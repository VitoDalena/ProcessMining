/*     */ package uk.ac.shef.wit.simmetrics.metrichandlers;
/*     */ 
/*     */ import java.io.File;
/*     */ import java.io.IOException;
/*     */ import java.io.PrintStream;
/*     */ import java.lang.reflect.Constructor;
/*     */ import java.lang.reflect.InvocationTargetException;
/*     */ import java.net.JarURLConnection;
/*     */ import java.net.URL;
/*     */ import java.util.ArrayList;
/*     */ import java.util.Enumeration;
/*     */ import java.util.jar.JarEntry;
/*     */ import java.util.jar.JarFile;
/*     */ import java.util.zip.ZipEntry;
/*     */ import uk.ac.shef.wit.simmetrics.similaritymetrics.AbstractStringMetric;
/*     */ import uk.ac.shef.wit.simmetrics.similaritymetrics.BlockDistance;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class MetricHandler
/*     */ {
/*  72 */   private static AbstractStringMetric aMetric = new BlockDistance();
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public static ArrayList<String> GetMetricsAvailable()
/*     */   {
/*  81 */     ArrayList<String> outputVect = new ArrayList();
/*     */     
/*  83 */     Class tosubclass = null;
/*     */     try {
/*  85 */       tosubclass = Class.forName("uk.ac.shef.wit.simmetrics.similaritymetrics.AbstractStringMetric");
/*     */     } catch (ClassNotFoundException e) {
/*  87 */       e.printStackTrace();
/*     */     }
/*  89 */     String pckgname = "uk.ac.shef.wit.simmetrics.similaritymetrics";
/*     */     
/*     */ 
/*  92 */     String name = pckgname;
/*  93 */     if (!name.startsWith("/")) {
/*  94 */       name = "/" + name;
/*     */     }
/*  96 */     name = name.replace('.', '/');
/*     */     
/*     */ 
/*  99 */     URL url = aMetric.getClass().getResource(name);
/*     */     
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/* 118 */     if (url == null) { return null;
/*     */     }
/* 120 */     File directory = new File(url.getFile());
/*     */     
/*     */ 
/*     */ 
/* 124 */     if (directory.exists())
/*     */     {
/* 126 */       String[] files = directory.list();
/* 127 */       for (String file : files)
/*     */       {
/*     */ 
/* 130 */         if (file.endsWith(".class"))
/*     */         {
/* 132 */           String classname = file.substring(0, file.length() - 6);
/*     */           try
/*     */           {
/* 135 */             Object o = Class.forName(pckgname + "." + classname).newInstance();
/* 136 */             assert (tosubclass != null);
/* 137 */             if (tosubclass.isInstance(o)) {
/* 138 */               outputVect.add(classname);
/*     */             }
/*     */           } catch (ClassNotFoundException cnfex) {
/* 141 */             System.err.println(cnfex);
/*     */ 
/*     */           }
/*     */           catch (InstantiationException iex) {}catch (IllegalAccessException iaex) {}
/*     */         }
/*     */         
/*     */       }
/*     */       
/*     */ 
/*     */     }
/*     */     else
/*     */     {
/*     */       try
/*     */       {
/* 155 */         JarURLConnection conn = (JarURLConnection)url.openConnection();
/* 156 */         String starts = conn.getEntryName();
/* 157 */         JarFile jfile = conn.getJarFile();
/* 158 */         Enumeration<JarEntry> e = jfile.entries();
/* 159 */         while (e.hasMoreElements()) {
/* 160 */           ZipEntry entry = (ZipEntry)e.nextElement();
/* 161 */           String entryname = entry.getName();
/* 162 */           if ((entryname.startsWith(starts)) && (entryname.lastIndexOf('/') <= starts.length()) && (entryname.endsWith(".class")))
/*     */           {
/*     */ 
/* 165 */             String classname = entryname.substring(0, entryname.length() - 6);
/* 166 */             if (classname.startsWith("/"))
/* 167 */               classname = classname.substring(1);
/* 168 */             classname = classname.replace('/', '.');
/*     */             try
/*     */             {
/* 171 */               Object o = Class.forName(classname).newInstance();
/* 172 */               assert (tosubclass != null);
/* 173 */               if (tosubclass.isInstance(o)) {
/* 174 */                 outputVect.add(classname.substring(classname.lastIndexOf('.') + 1));
/*     */               }
/*     */             } catch (ClassNotFoundException cnfex) {
/* 177 */               System.err.println(cnfex);
/*     */ 
/*     */             }
/*     */             catch (InstantiationException iex) {}catch (IllegalAccessException iaex) {}
/*     */           }
/*     */           
/*     */         }
/*     */         
/*     */       }
/*     */       catch (IOException ioex)
/*     */       {
/* 188 */         System.err.println(ioex);
/*     */       }
/*     */     }
/*     */     
/* 192 */     return outputVect;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public static AbstractStringMetric createMetric(String metricName)
/*     */   {
/* 202 */     AbstractStringMetric aplugin = null;
/*     */     try
/*     */     {
/* 205 */       Class<?> metricDefinition = Class.forName("uk.ac.shef.wit.simmetrics.similaritymetrics." + metricName);
/* 206 */       Constructor<?> constructor = metricDefinition.getConstructor(new Class[0]);
/* 207 */       aplugin = (AbstractStringMetric)constructor.newInstance(new Object[0]);
/*     */     } catch (IllegalAccessException e) {
/* 209 */       e.printStackTrace();
/*     */     } catch (InvocationTargetException e) {
/* 211 */       e.printStackTrace();
/*     */     } catch (InstantiationException e) {
/* 213 */       e.printStackTrace();
/*     */ 
/*     */     }
/*     */     catch (ClassNotFoundException e) {}catch (NoSuchMethodException e)
/*     */     {
/* 218 */       e.printStackTrace();
/*     */     }
/* 220 */     return aplugin;
/*     */   }
/*     */ }


/* Location:              /home/menick/Scrivania/ProcessMining/CNMining/CNMining.jar!/uk/ac/shef/wit/simmetrics/metrichandlers/MetricHandler.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       0.7.1
 */