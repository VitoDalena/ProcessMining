/*     */ package org.jfree.data.xml;
/*     */ 
/*     */ import java.io.File;
/*     */ import java.io.FileInputStream;
/*     */ import java.io.IOException;
/*     */ import java.io.InputStream;
/*     */ import java.io.PrintStream;
/*     */ import javax.xml.parsers.ParserConfigurationException;
/*     */ import javax.xml.parsers.SAXParser;
/*     */ import javax.xml.parsers.SAXParserFactory;
/*     */ import org.jfree.data.category.CategoryDataset;
/*     */ import org.jfree.data.general.PieDataset;
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
/*     */ public class DatasetReader
/*     */ {
/*     */   public static PieDataset readPieDatasetFromXML(File file)
/*     */     throws IOException
/*     */   {
/*  72 */     InputStream in = new FileInputStream(file);
/*  73 */     return readPieDatasetFromXML(in);
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
/*     */   public static PieDataset readPieDatasetFromXML(InputStream in)
/*     */     throws IOException
/*     */   {
/*  88 */     PieDataset result = null;
/*  89 */     SAXParserFactory factory = SAXParserFactory.newInstance();
/*     */     try {
/*  91 */       SAXParser parser = factory.newSAXParser();
/*  92 */       PieDatasetHandler handler = new PieDatasetHandler();
/*  93 */       parser.parse(in, handler);
/*  94 */       result = handler.getDataset();
/*     */     }
/*     */     catch (SAXException e) {
/*  97 */       System.out.println(e.getMessage());
/*     */     }
/*     */     catch (ParserConfigurationException e2) {
/* 100 */       System.out.println(e2.getMessage());
/*     */     }
/* 102 */     return result;
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
/*     */   public static CategoryDataset readCategoryDatasetFromXML(File file)
/*     */     throws IOException
/*     */   {
/* 117 */     InputStream in = new FileInputStream(file);
/* 118 */     return readCategoryDatasetFromXML(in);
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
/*     */   public static CategoryDataset readCategoryDatasetFromXML(InputStream in)
/*     */     throws IOException
/*     */   {
/* 133 */     CategoryDataset result = null;
/*     */     
/* 135 */     SAXParserFactory factory = SAXParserFactory.newInstance();
/*     */     try {
/* 137 */       SAXParser parser = factory.newSAXParser();
/* 138 */       CategoryDatasetHandler handler = new CategoryDatasetHandler();
/* 139 */       parser.parse(in, handler);
/* 140 */       result = handler.getDataset();
/*     */     }
/*     */     catch (SAXException e) {
/* 143 */       System.out.println(e.getMessage());
/*     */     }
/*     */     catch (ParserConfigurationException e2) {
/* 146 */       System.out.println(e2.getMessage());
/*     */     }
/* 148 */     return result;
/*     */   }
/*     */ }


/* Location:              /home/menick/Scrivania/ProcessMining/CNMining/CNMining.jar!/org/jfree/data/xml/DatasetReader.class
 * Java compiler version: 3 (47.0)
 * JD-Core Version:       0.7.1
 */