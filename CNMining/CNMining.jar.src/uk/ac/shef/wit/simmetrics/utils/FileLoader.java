/*     */ package uk.ac.shef.wit.simmetrics.utils;
/*     */ 
/*     */ import java.io.BufferedReader;
/*     */ import java.io.BufferedWriter;
/*     */ import java.io.File;
/*     */ import java.io.FileInputStream;
/*     */ import java.io.FileOutputStream;
/*     */ import java.io.FileReader;
/*     */ import java.io.FileWriter;
/*     */ import java.io.ObjectInputStream;
/*     */ import java.io.ObjectOutputStream;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public final class FileLoader
/*     */ {
/*     */   public static StringBuffer fileToStringBuffer(String file)
/*     */   {
/*  70 */     return fileToString(new File(file));
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public static Object fileToObject(String file)
/*     */   {
/*  80 */     return fileToObject(new File(file));
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public static StringBuffer fileToString(File f)
/*     */   {
/*  91 */     StringBuffer fileString = new StringBuffer("");
/*     */     try
/*     */     {
/*  94 */       FileReader fileR = new FileReader(f);
/*  95 */       BufferedReader bfr = new BufferedReader(fileR);
/*     */       
/*     */       for (;;)
/*     */       {
/*  99 */         String line = bfr.readLine();
/* 100 */         if (line == null) {
/*     */           break;
/*     */         }
/*     */         
/* 104 */         if (line.length() != 0) {
/* 105 */           fileString.append(line);
/* 106 */           fileString.append("\n");
/*     */         }
/*     */       }
/*     */     }
/*     */     catch (Exception e) {}
/*     */     
/*     */ 
/*     */ 
/* 114 */     return fileString;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public static Object fileToObject(File f)
/*     */   {
/* 125 */     if (f == null) {
/* 126 */       return null;
/*     */     }
/* 128 */     if (!f.exists()) {
/* 129 */       return null;
/*     */     }
/* 131 */     Object fileObject = null;
/*     */     try {
/* 133 */       FileInputStream fis = new FileInputStream(f);
/*     */       
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/* 140 */       ObjectInputStream ois = new ObjectInputStream(fis);
/*     */       
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/* 147 */       fileObject = ois.readObject();
/* 148 */       ois.close();
/*     */     } catch (Exception e) {
/* 150 */       e.printStackTrace();
/*     */     }
/* 152 */     return fileObject;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public static boolean objectToFile(Object content, String fname)
/*     */   {
/*     */     try
/*     */     {
/* 166 */       FileOutputStream fos = new FileOutputStream(fname);
/* 167 */       ObjectOutputStream oos = new ObjectOutputStream(fos);
/* 168 */       oos.writeObject(content);
/* 169 */       oos.flush();
/* 170 */       oos.close();
/*     */     } catch (Exception e) {
/* 172 */       e.printStackTrace();
/* 173 */       return false;
/*     */     }
/*     */     
/* 176 */     return true;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public static boolean stringToFile(StringBuffer content, String fname)
/*     */   {
/*     */     try
/*     */     {
/* 188 */       FileWriter fileW = new FileWriter(new File(fname));
/* 189 */       BufferedWriter bfw = new BufferedWriter(fileW);
/* 190 */       bfw.write(content.toString());
/* 191 */       bfw.flush();
/* 192 */       bfw.close();
/*     */     }
/*     */     catch (Exception e) {
/* 195 */       e.printStackTrace();
/* 196 */       return false;
/*     */     }
/*     */     
/* 199 */     return true;
/*     */   }
/*     */ }


/* Location:              /home/menick/Scrivania/ProcessMining/CNMining/CNMining.jar!/uk/ac/shef/wit/simmetrics/utils/FileLoader.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       0.7.1
 */