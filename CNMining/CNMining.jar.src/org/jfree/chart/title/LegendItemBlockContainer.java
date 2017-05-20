/*     */ package org.jfree.chart.title;
/*     */ 
/*     */ import java.awt.Graphics2D;
/*     */ import java.awt.Shape;
/*     */ import java.awt.geom.Rectangle2D;
/*     */ import org.jfree.chart.block.Arrangement;
/*     */ import org.jfree.chart.block.BlockContainer;
/*     */ import org.jfree.chart.block.BlockResult;
/*     */ import org.jfree.chart.block.EntityBlockParams;
/*     */ import org.jfree.chart.entity.EntityCollection;
/*     */ import org.jfree.chart.entity.LegendItemEntity;
/*     */ import org.jfree.chart.entity.StandardEntityCollection;
/*     */ import org.jfree.data.general.Dataset;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class LegendItemBlockContainer
/*     */   extends BlockContainer
/*     */ {
/*     */   private Dataset dataset;
/*     */   private Comparable seriesKey;
/*     */   private int datasetIndex;
/*     */   private int series;
/*     */   private String toolTipText;
/*     */   private String urlText;
/*     */   
/*     */   /**
/*     */    * @deprecated
/*     */    */
/*     */   public LegendItemBlockContainer(Arrangement arrangement, int datasetIndex, int series)
/*     */   {
/* 103 */     super(arrangement);
/* 104 */     this.datasetIndex = datasetIndex;
/* 105 */     this.series = series;
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
/*     */   public LegendItemBlockContainer(Arrangement arrangement, Dataset dataset, Comparable seriesKey)
/*     */   {
/* 119 */     super(arrangement);
/* 120 */     this.dataset = dataset;
/* 121 */     this.seriesKey = seriesKey;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public Dataset getDataset()
/*     */   {
/* 132 */     return this.dataset;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public Comparable getSeriesKey()
/*     */   {
/* 143 */     return this.seriesKey;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   /**
/*     */    * @deprecated
/*     */    */
/*     */   public int getDatasetIndex()
/*     */   {
/* 154 */     return this.datasetIndex;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public int getSeriesIndex()
/*     */   {
/* 163 */     return this.series;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public String getToolTipText()
/*     */   {
/* 174 */     return this.toolTipText;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public void setToolTipText(String text)
/*     */   {
/* 185 */     this.toolTipText = text;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public String getURLText()
/*     */   {
/* 196 */     return this.urlText;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public void setURLText(String text)
/*     */   {
/* 207 */     this.urlText = text;
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
/*     */   public Object draw(Graphics2D g2, Rectangle2D area, Object params)
/*     */   {
/* 222 */     super.draw(g2, area, null);
/* 223 */     EntityBlockParams ebp = null;
/* 224 */     BlockResult r = new BlockResult();
/* 225 */     if ((params instanceof EntityBlockParams)) {
/* 226 */       ebp = (EntityBlockParams)params;
/* 227 */       if (ebp.getGenerateEntities()) {
/* 228 */         EntityCollection ec = new StandardEntityCollection();
/* 229 */         LegendItemEntity entity = new LegendItemEntity((Shape)area.clone());
/*     */         
/* 231 */         entity.setSeriesIndex(this.series);
/* 232 */         entity.setSeriesKey(this.seriesKey);
/* 233 */         entity.setDataset(this.dataset);
/* 234 */         entity.setToolTipText(getToolTipText());
/* 235 */         entity.setURLText(getURLText());
/* 236 */         ec.add(entity);
/* 237 */         r.setEntityCollection(ec);
/*     */       }
/*     */     }
/* 240 */     return r;
/*     */   }
/*     */ }


/* Location:              /home/menick/Scrivania/ProcessMining/CNMining/CNMining.jar!/org/jfree/chart/title/LegendItemBlockContainer.class
 * Java compiler version: 3 (47.0)
 * JD-Core Version:       0.7.1
 */