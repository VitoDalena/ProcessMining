/*     */ package org.jfree.chart.labels;
/*     */ 
/*     */ import java.io.Serializable;
/*     */ import java.text.DateFormat;
/*     */ import java.text.NumberFormat;
/*     */ import org.jfree.data.category.CategoryDataset;
/*     */ import org.jfree.util.PublicCloneable;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class StandardCategoryItemLabelGenerator
/*     */   extends AbstractCategoryItemLabelGenerator
/*     */   implements CategoryItemLabelGenerator, Cloneable, PublicCloneable, Serializable
/*     */ {
/*     */   private static final long serialVersionUID = 3499701401211412882L;
/*     */   public static final String DEFAULT_LABEL_FORMAT_STRING = "{2}";
/*     */   
/*     */   public StandardCategoryItemLabelGenerator()
/*     */   {
/*  72 */     super("{2}", NumberFormat.getInstance());
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public StandardCategoryItemLabelGenerator(String labelFormat, NumberFormat formatter)
/*     */   {
/*  84 */     super(labelFormat, formatter);
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
/*     */   public StandardCategoryItemLabelGenerator(String labelFormat, NumberFormat formatter, NumberFormat percentFormatter)
/*     */   {
/* 100 */     super(labelFormat, formatter, percentFormatter);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public StandardCategoryItemLabelGenerator(String labelFormat, DateFormat formatter)
/*     */   {
/* 112 */     super(labelFormat, formatter);
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
/*     */   public String generateLabel(CategoryDataset dataset, int row, int column)
/*     */   {
/* 127 */     return generateLabelString(dataset, row, column);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public boolean equals(Object obj)
/*     */   {
/* 139 */     if (obj == this) {
/* 140 */       return true;
/*     */     }
/* 142 */     if (!(obj instanceof StandardCategoryItemLabelGenerator)) {
/* 143 */       return false;
/*     */     }
/* 145 */     return super.equals(obj);
/*     */   }
/*     */ }


/* Location:              /home/menick/Scrivania/ProcessMining/CNMining/CNMining.jar!/org/jfree/chart/labels/StandardCategoryItemLabelGenerator.class
 * Java compiler version: 3 (47.0)
 * JD-Core Version:       0.7.1
 */