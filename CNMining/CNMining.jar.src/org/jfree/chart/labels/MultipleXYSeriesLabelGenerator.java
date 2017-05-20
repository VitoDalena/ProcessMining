/*     */ package org.jfree.chart.labels;
/*     */ 
/*     */ import java.io.Serializable;
/*     */ import java.text.MessageFormat;
/*     */ import java.util.ArrayList;
/*     */ import java.util.HashMap;
/*     */ import java.util.Iterator;
/*     */ import java.util.List;
/*     */ import java.util.Map;
/*     */ import java.util.Set;
/*     */ import org.jfree.chart.HashUtilities;
/*     */ import org.jfree.data.xy.XYDataset;
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
/*     */ public class MultipleXYSeriesLabelGenerator
/*     */   implements XYSeriesLabelGenerator, Cloneable, PublicCloneable, Serializable
/*     */ {
/*     */   private static final long serialVersionUID = 138976236941898560L;
/*     */   public static final String DEFAULT_LABEL_FORMAT = "{0}";
/*     */   private String formatPattern;
/*     */   private String additionalFormatPattern;
/*     */   private Map seriesLabelLists;
/*     */   
/*     */   public MultipleXYSeriesLabelGenerator()
/*     */   {
/*  84 */     this("{0}");
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public MultipleXYSeriesLabelGenerator(String format)
/*     */   {
/*  93 */     if (format == null) {
/*  94 */       throw new IllegalArgumentException("Null 'format' argument.");
/*     */     }
/*  96 */     this.formatPattern = format;
/*  97 */     this.additionalFormatPattern = "\n{0}";
/*  98 */     this.seriesLabelLists = new HashMap();
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public void addSeriesLabel(int series, String label)
/*     */   {
/* 108 */     Integer key = new Integer(series);
/* 109 */     List labelList = (List)this.seriesLabelLists.get(key);
/* 110 */     if (labelList == null) {
/* 111 */       labelList = new ArrayList();
/* 112 */       this.seriesLabelLists.put(key, labelList);
/*     */     }
/* 114 */     labelList.add(label);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public void clearSeriesLabels(int series)
/*     */   {
/* 123 */     Integer key = new Integer(series);
/* 124 */     this.seriesLabelLists.put(key, null);
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
/*     */   public String generateLabel(XYDataset dataset, int series)
/*     */   {
/* 137 */     if (dataset == null) {
/* 138 */       throw new IllegalArgumentException("Null 'dataset' argument.");
/*     */     }
/* 140 */     StringBuffer label = new StringBuffer();
/* 141 */     label.append(MessageFormat.format(this.formatPattern, createItemArray(dataset, series)));
/*     */     
/* 143 */     Integer key = new Integer(series);
/* 144 */     List extraLabels = (List)this.seriesLabelLists.get(key);
/* 145 */     if (extraLabels != null) {
/* 146 */       Object[] temp = new Object[1];
/* 147 */       for (int i = 0; i < extraLabels.size(); i++) {
/* 148 */         temp[0] = extraLabels.get(i);
/* 149 */         String labelAddition = MessageFormat.format(this.additionalFormatPattern, temp);
/*     */         
/* 151 */         label.append(labelAddition);
/*     */       }
/*     */     }
/* 154 */     return label.toString();
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
/*     */   protected Object[] createItemArray(XYDataset dataset, int series)
/*     */   {
/* 167 */     Object[] result = new Object[1];
/* 168 */     result[0] = dataset.getSeriesKey(series).toString();
/* 169 */     return result;
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
/* 180 */     MultipleXYSeriesLabelGenerator clone = (MultipleXYSeriesLabelGenerator)super.clone();
/*     */     
/* 182 */     clone.seriesLabelLists = new HashMap();
/* 183 */     Set keys = this.seriesLabelLists.keySet();
/* 184 */     Iterator iterator = keys.iterator();
/* 185 */     while (iterator.hasNext()) {
/* 186 */       Object key = iterator.next();
/* 187 */       Object entry = this.seriesLabelLists.get(key);
/* 188 */       Object toAdd = entry;
/* 189 */       if ((entry instanceof PublicCloneable)) {
/* 190 */         PublicCloneable pc = (PublicCloneable)entry;
/* 191 */         toAdd = pc.clone();
/*     */       }
/* 193 */       clone.seriesLabelLists.put(key, toAdd);
/*     */     }
/* 195 */     return clone;
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
/* 206 */     if (obj == this) {
/* 207 */       return true;
/*     */     }
/* 209 */     if (!(obj instanceof MultipleXYSeriesLabelGenerator)) {
/* 210 */       return false;
/*     */     }
/* 212 */     MultipleXYSeriesLabelGenerator that = (MultipleXYSeriesLabelGenerator)obj;
/*     */     
/* 214 */     if (!this.formatPattern.equals(that.formatPattern)) {
/* 215 */       return false;
/*     */     }
/* 217 */     if (!this.additionalFormatPattern.equals(that.additionalFormatPattern))
/*     */     {
/* 219 */       return false;
/*     */     }
/* 221 */     if (!this.seriesLabelLists.equals(that.seriesLabelLists)) {
/* 222 */       return false;
/*     */     }
/* 224 */     return true;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public int hashCode()
/*     */   {
/* 233 */     int result = 127;
/* 234 */     result = HashUtilities.hashCode(result, this.formatPattern);
/* 235 */     result = HashUtilities.hashCode(result, this.additionalFormatPattern);
/* 236 */     result = HashUtilities.hashCode(result, this.seriesLabelLists);
/* 237 */     return result;
/*     */   }
/*     */ }


/* Location:              /home/menick/Scrivania/ProcessMining/CNMining/CNMining.jar!/org/jfree/chart/labels/MultipleXYSeriesLabelGenerator.class
 * Java compiler version: 3 (47.0)
 * JD-Core Version:       0.7.1
 */