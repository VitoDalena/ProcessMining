/*     */ package org.jfree.chart.labels;
/*     */ 
/*     */ import java.io.Serializable;
/*     */ import java.text.AttributedString;
/*     */ import java.text.NumberFormat;
/*     */ import java.util.Locale;
/*     */ import org.jfree.data.general.PieDataset;
/*     */ import org.jfree.util.ObjectList;
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
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class StandardPieSectionLabelGenerator
/*     */   extends AbstractPieItemLabelGenerator
/*     */   implements PieSectionLabelGenerator, Cloneable, PublicCloneable, Serializable
/*     */ {
/*     */   private static final long serialVersionUID = 3064190563760203668L;
/*     */   public static final String DEFAULT_SECTION_LABEL_FORMAT = "{0}";
/*     */   private ObjectList attributedLabels;
/*     */   
/*     */   public StandardPieSectionLabelGenerator()
/*     */   {
/*  92 */     this("{0}", NumberFormat.getNumberInstance(), NumberFormat.getPercentInstance());
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public StandardPieSectionLabelGenerator(Locale locale)
/*     */   {
/* 104 */     this("{0}", locale);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public StandardPieSectionLabelGenerator(String labelFormat)
/*     */   {
/* 114 */     this(labelFormat, NumberFormat.getNumberInstance(), NumberFormat.getPercentInstance());
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
/*     */   public StandardPieSectionLabelGenerator(String labelFormat, Locale locale)
/*     */   {
/* 127 */     this(labelFormat, NumberFormat.getNumberInstance(locale), NumberFormat.getPercentInstance(locale));
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
/*     */   public StandardPieSectionLabelGenerator(String labelFormat, NumberFormat numberFormat, NumberFormat percentFormat)
/*     */   {
/* 143 */     super(labelFormat, numberFormat, percentFormat);
/* 144 */     this.attributedLabels = new ObjectList();
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public AttributedString getAttributedLabel(int section)
/*     */   {
/* 156 */     return (AttributedString)this.attributedLabels.get(section);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public void setAttributedLabel(int section, AttributedString label)
/*     */   {
/* 166 */     this.attributedLabels.set(section, label);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public String generateSectionLabel(PieDataset dataset, Comparable key)
/*     */   {
/* 178 */     return super.generateSectionLabel(dataset, key);
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
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public AttributedString generateAttributedSectionLabel(PieDataset dataset, Comparable key)
/*     */   {
/* 210 */     return getAttributedLabel(dataset.getIndex(key));
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
/* 221 */     if (obj == this) {
/* 222 */       return true;
/*     */     }
/* 224 */     if (!(obj instanceof StandardPieSectionLabelGenerator)) {
/* 225 */       return false;
/*     */     }
/* 227 */     StandardPieSectionLabelGenerator that = (StandardPieSectionLabelGenerator)obj;
/*     */     
/* 229 */     if (!this.attributedLabels.equals(that.attributedLabels)) {
/* 230 */       return false;
/*     */     }
/* 232 */     if (!super.equals(obj)) {
/* 233 */       return false;
/*     */     }
/* 235 */     return true;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public Object clone()
/*     */     throws CloneNotSupportedException
/*     */   {
/* 246 */     return super.clone();
/*     */   }
/*     */ }


/* Location:              /home/menick/Scrivania/ProcessMining/CNMining/CNMining.jar!/org/jfree/chart/labels/StandardPieSectionLabelGenerator.class
 * Java compiler version: 3 (47.0)
 * JD-Core Version:       0.7.1
 */