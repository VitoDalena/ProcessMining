/*     */ package org.jfree.chart.labels;
/*     */ 
/*     */ import java.io.Serializable;
/*     */ import java.text.DateFormat;
/*     */ import java.text.NumberFormat;
/*     */ import org.jfree.data.category.CategoryDataset;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class StandardCategoryToolTipGenerator
/*     */   extends AbstractCategoryItemLabelGenerator
/*     */   implements CategoryToolTipGenerator, Serializable
/*     */ {
/*     */   private static final long serialVersionUID = -6768806592218710764L;
/*     */   public static final String DEFAULT_TOOL_TIP_FORMAT_STRING = "({0}, {1}) = {2}";
/*     */   
/*     */   public StandardCategoryToolTipGenerator()
/*     */   {
/*  70 */     super("({0}, {1}) = {2}", NumberFormat.getInstance());
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public StandardCategoryToolTipGenerator(String labelFormat, NumberFormat formatter)
/*     */   {
/*  82 */     super(labelFormat, formatter);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public StandardCategoryToolTipGenerator(String labelFormat, DateFormat formatter)
/*     */   {
/*  94 */     super(labelFormat, formatter);
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
/*     */   public String generateToolTip(CategoryDataset dataset, int row, int column)
/*     */   {
/* 110 */     return generateLabelString(dataset, row, column);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public boolean equals(Object obj)
/*     */   {
/* 121 */     if (obj == this) {
/* 122 */       return true;
/*     */     }
/* 124 */     if (!(obj instanceof StandardCategoryToolTipGenerator)) {
/* 125 */       return false;
/*     */     }
/* 127 */     return super.equals(obj);
/*     */   }
/*     */ }


/* Location:              /home/menick/Scrivania/ProcessMining/CNMining/CNMining.jar!/org/jfree/chart/labels/StandardCategoryToolTipGenerator.class
 * Java compiler version: 3 (47.0)
 * JD-Core Version:       0.7.1
 */