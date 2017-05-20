/*      */ package org.jfree.data.general;
/*      */ 
/*      */ import java.util.ArrayList;
/*      */ import java.util.Iterator;
/*      */ import java.util.List;
/*      */ import org.jfree.data.DomainInfo;
/*      */ import org.jfree.data.KeyToGroupMap;
/*      */ import org.jfree.data.KeyedValues;
/*      */ import org.jfree.data.Range;
/*      */ import org.jfree.data.RangeInfo;
/*      */ import org.jfree.data.category.CategoryDataset;
/*      */ import org.jfree.data.category.CategoryRangeInfo;
/*      */ import org.jfree.data.category.DefaultCategoryDataset;
/*      */ import org.jfree.data.category.IntervalCategoryDataset;
/*      */ import org.jfree.data.function.Function2D;
/*      */ import org.jfree.data.statistics.BoxAndWhiskerCategoryDataset;
/*      */ import org.jfree.data.statistics.BoxAndWhiskerXYDataset;
/*      */ import org.jfree.data.statistics.StatisticalCategoryDataset;
/*      */ import org.jfree.data.xy.IntervalXYDataset;
/*      */ import org.jfree.data.xy.OHLCDataset;
/*      */ import org.jfree.data.xy.TableXYDataset;
/*      */ import org.jfree.data.xy.XYDataset;
/*      */ import org.jfree.data.xy.XYDomainInfo;
/*      */ import org.jfree.data.xy.XYRangeInfo;
/*      */ import org.jfree.data.xy.XYSeries;
/*      */ import org.jfree.data.xy.XYSeriesCollection;
/*      */ import org.jfree.util.ArrayUtilities;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ public final class DatasetUtilities
/*      */ {
/*      */   public static double calculatePieDatasetTotal(PieDataset dataset)
/*      */   {
/*  173 */     if (dataset == null) {
/*  174 */       throw new IllegalArgumentException("Null 'dataset' argument.");
/*      */     }
/*  176 */     List keys = dataset.getKeys();
/*  177 */     double totalValue = 0.0D;
/*  178 */     Iterator iterator = keys.iterator();
/*  179 */     while (iterator.hasNext()) {
/*  180 */       Comparable current = (Comparable)iterator.next();
/*  181 */       if (current != null) {
/*  182 */         Number value = dataset.getValue(current);
/*  183 */         double v = 0.0D;
/*  184 */         if (value != null) {
/*  185 */           v = value.doubleValue();
/*      */         }
/*  187 */         if (v > 0.0D) {
/*  188 */           totalValue += v;
/*      */         }
/*      */       }
/*      */     }
/*  192 */     return totalValue;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public static PieDataset createPieDatasetForRow(CategoryDataset dataset, Comparable rowKey)
/*      */   {
/*  206 */     int row = dataset.getRowIndex(rowKey);
/*  207 */     return createPieDatasetForRow(dataset, row);
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public static PieDataset createPieDatasetForRow(CategoryDataset dataset, int row)
/*      */   {
/*  221 */     DefaultPieDataset result = new DefaultPieDataset();
/*  222 */     int columnCount = dataset.getColumnCount();
/*  223 */     for (int current = 0; current < columnCount; current++) {
/*  224 */       Comparable columnKey = dataset.getColumnKey(current);
/*  225 */       result.setValue(columnKey, dataset.getValue(row, current));
/*      */     }
/*  227 */     return result;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public static PieDataset createPieDatasetForColumn(CategoryDataset dataset, Comparable columnKey)
/*      */   {
/*  241 */     int column = dataset.getColumnIndex(columnKey);
/*  242 */     return createPieDatasetForColumn(dataset, column);
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public static PieDataset createPieDatasetForColumn(CategoryDataset dataset, int column)
/*      */   {
/*  256 */     DefaultPieDataset result = new DefaultPieDataset();
/*  257 */     int rowCount = dataset.getRowCount();
/*  258 */     for (int i = 0; i < rowCount; i++) {
/*  259 */       Comparable rowKey = dataset.getRowKey(i);
/*  260 */       result.setValue(rowKey, dataset.getValue(i, column));
/*      */     }
/*  262 */     return result;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public static PieDataset createConsolidatedPieDataset(PieDataset source, Comparable key, double minimumPercent)
/*      */   {
/*  280 */     return createConsolidatedPieDataset(source, key, minimumPercent, 2);
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public static PieDataset createConsolidatedPieDataset(PieDataset source, Comparable key, double minimumPercent, int minItems)
/*      */   {
/*  302 */     DefaultPieDataset result = new DefaultPieDataset();
/*  303 */     double total = calculatePieDatasetTotal(source);
/*      */     
/*      */ 
/*  306 */     List keys = source.getKeys();
/*  307 */     ArrayList otherKeys = new ArrayList();
/*  308 */     Iterator iterator = keys.iterator();
/*  309 */     while (iterator.hasNext()) {
/*  310 */       Comparable currentKey = (Comparable)iterator.next();
/*  311 */       Number dataValue = source.getValue(currentKey);
/*  312 */       if (dataValue != null) {
/*  313 */         double value = dataValue.doubleValue();
/*  314 */         if (value / total < minimumPercent) {
/*  315 */           otherKeys.add(currentKey);
/*      */         }
/*      */       }
/*      */     }
/*      */     
/*      */ 
/*  321 */     iterator = keys.iterator();
/*  322 */     double otherValue = 0.0D;
/*  323 */     while (iterator.hasNext()) {
/*  324 */       Comparable currentKey = (Comparable)iterator.next();
/*  325 */       Number dataValue = source.getValue(currentKey);
/*  326 */       if (dataValue != null) {
/*  327 */         if ((otherKeys.contains(currentKey)) && (otherKeys.size() >= minItems))
/*      */         {
/*      */ 
/*  330 */           otherValue += dataValue.doubleValue();
/*      */         }
/*      */         else
/*      */         {
/*  334 */           result.setValue(currentKey, dataValue);
/*      */         }
/*      */       }
/*      */     }
/*      */     
/*  339 */     if (otherKeys.size() >= minItems) {
/*  340 */       result.setValue(key, otherValue);
/*      */     }
/*  342 */     return result;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public static CategoryDataset createCategoryDataset(String rowKeyPrefix, String columnKeyPrefix, double[][] data)
/*      */   {
/*  362 */     DefaultCategoryDataset result = new DefaultCategoryDataset();
/*  363 */     for (int r = 0; r < data.length; r++) {
/*  364 */       String rowKey = rowKeyPrefix + (r + 1);
/*  365 */       for (int c = 0; c < data[r].length; c++) {
/*  366 */         String columnKey = columnKeyPrefix + (c + 1);
/*  367 */         result.addValue(new Double(data[r][c]), rowKey, columnKey);
/*      */       }
/*      */     }
/*  370 */     return result;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public static CategoryDataset createCategoryDataset(String rowKeyPrefix, String columnKeyPrefix, Number[][] data)
/*      */   {
/*  390 */     DefaultCategoryDataset result = new DefaultCategoryDataset();
/*  391 */     for (int r = 0; r < data.length; r++) {
/*  392 */       String rowKey = rowKeyPrefix + (r + 1);
/*  393 */       for (int c = 0; c < data[r].length; c++) {
/*  394 */         String columnKey = columnKeyPrefix + (c + 1);
/*  395 */         result.addValue(data[r][c], rowKey, columnKey);
/*      */       }
/*      */     }
/*  398 */     return result;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public static CategoryDataset createCategoryDataset(Comparable[] rowKeys, Comparable[] columnKeys, double[][] data)
/*      */   {
/*  419 */     if (rowKeys == null) {
/*  420 */       throw new IllegalArgumentException("Null 'rowKeys' argument.");
/*      */     }
/*  422 */     if (columnKeys == null) {
/*  423 */       throw new IllegalArgumentException("Null 'columnKeys' argument.");
/*      */     }
/*  425 */     if (ArrayUtilities.hasDuplicateItems(rowKeys)) {
/*  426 */       throw new IllegalArgumentException("Duplicate items in 'rowKeys'.");
/*      */     }
/*  428 */     if (ArrayUtilities.hasDuplicateItems(columnKeys)) {
/*  429 */       throw new IllegalArgumentException("Duplicate items in 'columnKeys'.");
/*      */     }
/*      */     
/*  432 */     if (rowKeys.length != data.length) {
/*  433 */       throw new IllegalArgumentException("The number of row keys does not match the number of rows in the data array.");
/*      */     }
/*      */     
/*      */ 
/*  437 */     int columnCount = 0;
/*  438 */     for (int r = 0; r < data.length; r++) {
/*  439 */       columnCount = Math.max(columnCount, data[r].length);
/*      */     }
/*  441 */     if (columnKeys.length != columnCount) {
/*  442 */       throw new IllegalArgumentException("The number of column keys does not match the number of columns in the data array.");
/*      */     }
/*      */     
/*      */ 
/*      */ 
/*      */ 
/*  448 */     DefaultCategoryDataset result = new DefaultCategoryDataset();
/*  449 */     for (int r = 0; r < data.length; r++) {
/*  450 */       Comparable rowKey = rowKeys[r];
/*  451 */       for (int c = 0; c < data[r].length; c++) {
/*  452 */         Comparable columnKey = columnKeys[c];
/*  453 */         result.addValue(new Double(data[r][c]), rowKey, columnKey);
/*      */       }
/*      */     }
/*  456 */     return result;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public static CategoryDataset createCategoryDataset(Comparable rowKey, KeyedValues rowData)
/*      */   {
/*  472 */     if (rowKey == null) {
/*  473 */       throw new IllegalArgumentException("Null 'rowKey' argument.");
/*      */     }
/*  475 */     if (rowData == null) {
/*  476 */       throw new IllegalArgumentException("Null 'rowData' argument.");
/*      */     }
/*  478 */     DefaultCategoryDataset result = new DefaultCategoryDataset();
/*  479 */     for (int i = 0; i < rowData.getItemCount(); i++) {
/*  480 */       result.addValue(rowData.getValue(i), rowKey, rowData.getKey(i));
/*      */     }
/*  482 */     return result;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public static XYDataset sampleFunction2D(Function2D f, double start, double end, int samples, Comparable seriesKey)
/*      */   {
/*  503 */     XYSeries series = sampleFunction2DToSeries(f, start, end, samples, seriesKey);
/*      */     
/*  505 */     XYSeriesCollection collection = new XYSeriesCollection(series);
/*  506 */     return collection;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public static XYSeries sampleFunction2DToSeries(Function2D f, double start, double end, int samples, Comparable seriesKey)
/*      */   {
/*  527 */     if (f == null) {
/*  528 */       throw new IllegalArgumentException("Null 'f' argument.");
/*      */     }
/*  530 */     if (seriesKey == null) {
/*  531 */       throw new IllegalArgumentException("Null 'seriesKey' argument.");
/*      */     }
/*  533 */     if (start >= end) {
/*  534 */       throw new IllegalArgumentException("Requires 'start' < 'end'.");
/*      */     }
/*  536 */     if (samples < 2) {
/*  537 */       throw new IllegalArgumentException("Requires 'samples' > 1");
/*      */     }
/*      */     
/*  540 */     XYSeries series = new XYSeries(seriesKey);
/*  541 */     double step = (end - start) / (samples - 1);
/*  542 */     for (int i = 0; i < samples; i++) {
/*  543 */       double x = start + step * i;
/*  544 */       series.add(x, f.getValue(x));
/*      */     }
/*  546 */     return series;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public static boolean isEmptyOrNull(PieDataset dataset)
/*      */   {
/*  559 */     if (dataset == null) {
/*  560 */       return true;
/*      */     }
/*      */     
/*  563 */     int itemCount = dataset.getItemCount();
/*  564 */     if (itemCount == 0) {
/*  565 */       return true;
/*      */     }
/*      */     
/*  568 */     for (int item = 0; item < itemCount; item++) {
/*  569 */       Number y = dataset.getValue(item);
/*  570 */       if (y != null) {
/*  571 */         double yy = y.doubleValue();
/*  572 */         if (yy > 0.0D) {
/*  573 */           return false;
/*      */         }
/*      */       }
/*      */     }
/*      */     
/*  578 */     return true;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public static boolean isEmptyOrNull(CategoryDataset dataset)
/*      */   {
/*  592 */     if (dataset == null) {
/*  593 */       return true;
/*      */     }
/*      */     
/*  596 */     int rowCount = dataset.getRowCount();
/*  597 */     int columnCount = dataset.getColumnCount();
/*  598 */     if ((rowCount == 0) || (columnCount == 0)) {
/*  599 */       return true;
/*      */     }
/*      */     
/*  602 */     for (int r = 0; r < rowCount; r++) {
/*  603 */       for (int c = 0; c < columnCount; c++) {
/*  604 */         if (dataset.getValue(r, c) != null) {
/*  605 */           return false;
/*      */         }
/*      */       }
/*      */     }
/*      */     
/*      */ 
/*  611 */     return true;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public static boolean isEmptyOrNull(XYDataset dataset)
/*      */   {
/*  624 */     if (dataset != null) {
/*  625 */       for (int s = 0; s < dataset.getSeriesCount(); s++) {
/*  626 */         if (dataset.getItemCount(s) > 0) {
/*  627 */           return false;
/*      */         }
/*      */       }
/*      */     }
/*  631 */     return true;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public static Range findDomainBounds(XYDataset dataset)
/*      */   {
/*  642 */     return findDomainBounds(dataset, true);
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public static Range findDomainBounds(XYDataset dataset, boolean includeInterval)
/*      */   {
/*  658 */     if (dataset == null) {
/*  659 */       throw new IllegalArgumentException("Null 'dataset' argument.");
/*      */     }
/*      */     
/*  662 */     Range result = null;
/*      */     
/*  664 */     if ((dataset instanceof DomainInfo)) {
/*  665 */       DomainInfo info = (DomainInfo)dataset;
/*  666 */       result = info.getDomainBounds(includeInterval);
/*      */     }
/*      */     else {
/*  669 */       result = iterateDomainBounds(dataset, includeInterval);
/*      */     }
/*  671 */     return result;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public static Range findDomainBounds(XYDataset dataset, List visibleSeriesKeys, boolean includeInterval)
/*      */   {
/*  692 */     if (dataset == null) {
/*  693 */       throw new IllegalArgumentException("Null 'dataset' argument.");
/*      */     }
/*  695 */     Range result = null;
/*  696 */     if ((dataset instanceof XYDomainInfo)) {
/*  697 */       XYDomainInfo info = (XYDomainInfo)dataset;
/*  698 */       result = info.getDomainBounds(visibleSeriesKeys, includeInterval);
/*      */     }
/*      */     else {
/*  701 */       result = iterateToFindDomainBounds(dataset, visibleSeriesKeys, includeInterval);
/*      */     }
/*      */     
/*  704 */     return result;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public static Range iterateDomainBounds(XYDataset dataset)
/*      */   {
/*  718 */     return iterateDomainBounds(dataset, true);
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public static Range iterateDomainBounds(XYDataset dataset, boolean includeInterval)
/*      */   {
/*  734 */     if (dataset == null) {
/*  735 */       throw new IllegalArgumentException("Null 'dataset' argument.");
/*      */     }
/*  737 */     double minimum = Double.POSITIVE_INFINITY;
/*  738 */     double maximum = Double.NEGATIVE_INFINITY;
/*  739 */     int seriesCount = dataset.getSeriesCount();
/*      */     
/*      */ 
/*  742 */     if ((includeInterval) && ((dataset instanceof IntervalXYDataset))) {
/*  743 */       IntervalXYDataset intervalXYData = (IntervalXYDataset)dataset;
/*  744 */       for (int series = 0; series < seriesCount; series++) {
/*  745 */         int itemCount = dataset.getItemCount(series);
/*  746 */         for (int item = 0; item < itemCount; item++) {
/*  747 */           double lvalue = intervalXYData.getStartXValue(series, item);
/*  748 */           double uvalue = intervalXYData.getEndXValue(series, item);
/*  749 */           if (!Double.isNaN(lvalue)) {
/*  750 */             minimum = Math.min(minimum, lvalue);
/*      */           }
/*  752 */           if (!Double.isNaN(uvalue)) {
/*  753 */             maximum = Math.max(maximum, uvalue);
/*      */           }
/*      */         }
/*      */       }
/*      */     }
/*      */     else {
/*  759 */       for (int series = 0; series < seriesCount; series++) {
/*  760 */         int itemCount = dataset.getItemCount(series);
/*  761 */         for (int item = 0; item < itemCount; item++) {
/*  762 */           double lvalue = dataset.getXValue(series, item);
/*  763 */           double uvalue = lvalue;
/*  764 */           if (!Double.isNaN(lvalue)) {
/*  765 */             minimum = Math.min(minimum, lvalue);
/*  766 */             maximum = Math.max(maximum, uvalue);
/*      */           }
/*      */         }
/*      */       }
/*      */     }
/*  771 */     if (minimum > maximum) {
/*  772 */       return null;
/*      */     }
/*      */     
/*  775 */     return new Range(minimum, maximum);
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public static Range findRangeBounds(CategoryDataset dataset)
/*      */   {
/*  787 */     return findRangeBounds(dataset, true);
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public static Range findRangeBounds(CategoryDataset dataset, boolean includeInterval)
/*      */   {
/*  801 */     if (dataset == null) {
/*  802 */       throw new IllegalArgumentException("Null 'dataset' argument.");
/*      */     }
/*  804 */     Range result = null;
/*  805 */     if ((dataset instanceof RangeInfo)) {
/*  806 */       RangeInfo info = (RangeInfo)dataset;
/*  807 */       result = info.getRangeBounds(includeInterval);
/*      */     }
/*      */     else {
/*  810 */       result = iterateRangeBounds(dataset, includeInterval);
/*      */     }
/*  812 */     return result;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public static Range findRangeBounds(CategoryDataset dataset, List visibleSeriesKeys, boolean includeInterval)
/*      */   {
/*  831 */     if (dataset == null) {
/*  832 */       throw new IllegalArgumentException("Null 'dataset' argument.");
/*      */     }
/*  834 */     Range result = null;
/*  835 */     if ((dataset instanceof CategoryRangeInfo)) {
/*  836 */       CategoryRangeInfo info = (CategoryRangeInfo)dataset;
/*  837 */       result = info.getRangeBounds(visibleSeriesKeys, includeInterval);
/*      */     }
/*      */     else {
/*  840 */       result = iterateToFindRangeBounds(dataset, visibleSeriesKeys, includeInterval);
/*      */     }
/*      */     
/*  843 */     return result;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public static Range findRangeBounds(XYDataset dataset)
/*      */   {
/*  855 */     return findRangeBounds(dataset, true);
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public static Range findRangeBounds(XYDataset dataset, boolean includeInterval)
/*      */   {
/*  871 */     if (dataset == null) {
/*  872 */       throw new IllegalArgumentException("Null 'dataset' argument.");
/*      */     }
/*  874 */     Range result = null;
/*  875 */     if ((dataset instanceof RangeInfo)) {
/*  876 */       RangeInfo info = (RangeInfo)dataset;
/*  877 */       result = info.getRangeBounds(includeInterval);
/*      */     }
/*      */     else {
/*  880 */       result = iterateRangeBounds(dataset, includeInterval);
/*      */     }
/*  882 */     return result;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public static Range findRangeBounds(XYDataset dataset, List visibleSeriesKeys, Range xRange, boolean includeInterval)
/*      */   {
/*  903 */     if (dataset == null) {
/*  904 */       throw new IllegalArgumentException("Null 'dataset' argument.");
/*      */     }
/*  906 */     Range result = null;
/*  907 */     if ((dataset instanceof XYRangeInfo)) {
/*  908 */       XYRangeInfo info = (XYRangeInfo)dataset;
/*  909 */       result = info.getRangeBounds(visibleSeriesKeys, xRange, includeInterval);
/*      */     }
/*      */     else
/*      */     {
/*  913 */       result = iterateToFindRangeBounds(dataset, visibleSeriesKeys, xRange, includeInterval);
/*      */     }
/*      */     
/*  916 */     return result;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   /**
/*      */    * @deprecated
/*      */    */
/*      */   public static Range iterateCategoryRangeBounds(CategoryDataset dataset, boolean includeInterval)
/*      */   {
/*  934 */     return iterateRangeBounds(dataset, includeInterval);
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public static Range iterateRangeBounds(CategoryDataset dataset)
/*      */   {
/*  948 */     return iterateRangeBounds(dataset, true);
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public static Range iterateRangeBounds(CategoryDataset dataset, boolean includeInterval)
/*      */   {
/*  965 */     double minimum = Double.POSITIVE_INFINITY;
/*  966 */     double maximum = Double.NEGATIVE_INFINITY;
/*  967 */     int rowCount = dataset.getRowCount();
/*  968 */     int columnCount = dataset.getColumnCount();
/*  969 */     if ((includeInterval) && ((dataset instanceof IntervalCategoryDataset)))
/*      */     {
/*      */ 
/*  972 */       IntervalCategoryDataset icd = (IntervalCategoryDataset)dataset;
/*      */       
/*  974 */       for (int row = 0; row < rowCount; row++) {
/*  975 */         for (int column = 0; column < columnCount; column++) {
/*  976 */           Number lvalue = icd.getStartValue(row, column);
/*  977 */           Number uvalue = icd.getEndValue(row, column);
/*  978 */           if ((lvalue != null) && (!Double.isNaN(lvalue.doubleValue()))) {
/*  979 */             minimum = Math.min(minimum, lvalue.doubleValue());
/*      */           }
/*  981 */           if ((uvalue != null) && (!Double.isNaN(uvalue.doubleValue()))) {
/*  982 */             maximum = Math.max(maximum, uvalue.doubleValue());
/*      */           }
/*      */         }
/*      */       }
/*      */     }
/*      */     else
/*      */     {
/*  989 */       for (int row = 0; row < rowCount; row++) {
/*  990 */         for (int column = 0; column < columnCount; column++) {
/*  991 */           Number value = dataset.getValue(row, column);
/*  992 */           if (value != null) {
/*  993 */             double v = value.doubleValue();
/*  994 */             if (!Double.isNaN(v)) {
/*  995 */               minimum = Math.min(minimum, v);
/*  996 */               maximum = Math.max(maximum, v);
/*      */             }
/*      */           }
/*      */         }
/*      */       }
/*      */     }
/* 1002 */     if (minimum == Double.POSITIVE_INFINITY) {
/* 1003 */       return null;
/*      */     }
/*      */     
/* 1006 */     return new Range(minimum, maximum);
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public static Range iterateToFindRangeBounds(CategoryDataset dataset, List visibleSeriesKeys, boolean includeInterval)
/*      */   {
/* 1026 */     if (dataset == null) {
/* 1027 */       throw new IllegalArgumentException("Null 'dataset' argument.");
/*      */     }
/* 1029 */     if (visibleSeriesKeys == null) {
/* 1030 */       throw new IllegalArgumentException("Null 'visibleSeriesKeys' argument.");
/*      */     }
/*      */     
/*      */ 
/* 1034 */     double minimum = Double.POSITIVE_INFINITY;
/* 1035 */     double maximum = Double.NEGATIVE_INFINITY;
/* 1036 */     int columnCount = dataset.getColumnCount();
/* 1037 */     if ((includeInterval) && ((dataset instanceof BoxAndWhiskerCategoryDataset)))
/*      */     {
/*      */ 
/* 1040 */       BoxAndWhiskerCategoryDataset bx = (BoxAndWhiskerCategoryDataset)dataset;
/*      */       
/* 1042 */       Iterator iterator = visibleSeriesKeys.iterator();
/* 1043 */       while (iterator.hasNext()) {
/* 1044 */         Comparable seriesKey = (Comparable)iterator.next();
/* 1045 */         int series = dataset.getRowIndex(seriesKey);
/* 1046 */         int itemCount = dataset.getColumnCount();
/* 1047 */         for (int item = 0; item < itemCount; item++) {
/* 1048 */           Number lvalue = bx.getMinRegularValue(series, item);
/* 1049 */           if (lvalue == null) {
/* 1050 */             lvalue = bx.getValue(series, item);
/*      */           }
/* 1052 */           Number uvalue = bx.getMaxRegularValue(series, item);
/* 1053 */           if (uvalue == null) {
/* 1054 */             uvalue = bx.getValue(series, item);
/*      */           }
/* 1056 */           if (lvalue != null) {
/* 1057 */             minimum = Math.min(minimum, lvalue.doubleValue());
/*      */           }
/* 1059 */           if (uvalue != null) {
/* 1060 */             maximum = Math.max(maximum, uvalue.doubleValue());
/*      */           }
/*      */         }
/*      */       }
/*      */     }
/* 1065 */     else if ((includeInterval) && ((dataset instanceof IntervalCategoryDataset)))
/*      */     {
/*      */ 
/*      */ 
/* 1069 */       IntervalCategoryDataset icd = (IntervalCategoryDataset)dataset;
/*      */       
/* 1071 */       Iterator iterator = visibleSeriesKeys.iterator();
/* 1072 */       while (iterator.hasNext()) {
/* 1073 */         Comparable seriesKey = (Comparable)iterator.next();
/* 1074 */         int series = dataset.getRowIndex(seriesKey);
/* 1075 */         for (int column = 0; column < columnCount; column++) {
/* 1076 */           Number lvalue = icd.getStartValue(series, column);
/* 1077 */           Number uvalue = icd.getEndValue(series, column);
/* 1078 */           if ((lvalue != null) && (!Double.isNaN(lvalue.doubleValue()))) {
/* 1079 */             minimum = Math.min(minimum, lvalue.doubleValue());
/*      */           }
/* 1081 */           if ((uvalue != null) && (!Double.isNaN(uvalue.doubleValue()))) {
/* 1082 */             maximum = Math.max(maximum, uvalue.doubleValue());
/*      */           }
/*      */         }
/*      */       }
/*      */     }
/* 1087 */     else if ((includeInterval) && ((dataset instanceof StatisticalCategoryDataset)))
/*      */     {
/*      */ 
/*      */ 
/* 1091 */       StatisticalCategoryDataset scd = (StatisticalCategoryDataset)dataset;
/*      */       
/* 1093 */       Iterator iterator = visibleSeriesKeys.iterator();
/* 1094 */       while (iterator.hasNext()) {
/* 1095 */         Comparable seriesKey = (Comparable)iterator.next();
/* 1096 */         int series = dataset.getRowIndex(seriesKey);
/* 1097 */         for (int column = 0; column < columnCount; column++) {
/* 1098 */           Number meanN = scd.getMeanValue(series, column);
/* 1099 */           if (meanN != null) {
/* 1100 */             double std = 0.0D;
/* 1101 */             Number stdN = scd.getStdDevValue(series, column);
/* 1102 */             if (stdN != null) {
/* 1103 */               std = stdN.doubleValue();
/* 1104 */               if (Double.isNaN(std)) {
/* 1105 */                 std = 0.0D;
/*      */               }
/*      */             }
/* 1108 */             double mean = meanN.doubleValue();
/* 1109 */             if (!Double.isNaN(mean)) {
/* 1110 */               minimum = Math.min(minimum, mean - std);
/* 1111 */               maximum = Math.max(maximum, mean + std);
/*      */             }
/*      */           }
/*      */         }
/*      */       }
/*      */     }
/*      */     else
/*      */     {
/* 1119 */       Iterator iterator = visibleSeriesKeys.iterator();
/* 1120 */       while (iterator.hasNext()) {
/* 1121 */         Comparable seriesKey = (Comparable)iterator.next();
/* 1122 */         int series = dataset.getRowIndex(seriesKey);
/* 1123 */         for (int column = 0; column < columnCount; column++) {
/* 1124 */           Number value = dataset.getValue(series, column);
/* 1125 */           if (value != null) {
/* 1126 */             double v = value.doubleValue();
/* 1127 */             if (!Double.isNaN(v)) {
/* 1128 */               minimum = Math.min(minimum, v);
/* 1129 */               maximum = Math.max(maximum, v);
/*      */             }
/*      */           }
/*      */         }
/*      */       }
/*      */     }
/* 1135 */     if (minimum == Double.POSITIVE_INFINITY) {
/* 1136 */       return null;
/*      */     }
/*      */     
/* 1139 */     return new Range(minimum, maximum);
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   /**
/*      */    * @deprecated
/*      */    */
/*      */   public static Range iterateXYRangeBounds(XYDataset dataset)
/*      */   {
/* 1154 */     return iterateRangeBounds(dataset);
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public static Range iterateRangeBounds(XYDataset dataset)
/*      */   {
/* 1168 */     return iterateRangeBounds(dataset, true);
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public static Range iterateRangeBounds(XYDataset dataset, boolean includeInterval)
/*      */   {
/* 1186 */     double minimum = Double.POSITIVE_INFINITY;
/* 1187 */     double maximum = Double.NEGATIVE_INFINITY;
/* 1188 */     int seriesCount = dataset.getSeriesCount();
/*      */     
/*      */ 
/* 1191 */     if ((includeInterval) && ((dataset instanceof IntervalXYDataset)))
/*      */     {
/* 1193 */       IntervalXYDataset ixyd = (IntervalXYDataset)dataset;
/* 1194 */       for (int series = 0; series < seriesCount; series++) {
/* 1195 */         int itemCount = dataset.getItemCount(series);
/* 1196 */         for (int item = 0; item < itemCount; item++) {
/* 1197 */           double lvalue = ixyd.getStartYValue(series, item);
/* 1198 */           double uvalue = ixyd.getEndYValue(series, item);
/* 1199 */           if (!Double.isNaN(lvalue)) {
/* 1200 */             minimum = Math.min(minimum, lvalue);
/*      */           }
/* 1202 */           if (!Double.isNaN(uvalue)) {
/* 1203 */             maximum = Math.max(maximum, uvalue);
/*      */           }
/*      */         }
/*      */       }
/*      */     }
/* 1208 */     else if ((includeInterval) && ((dataset instanceof OHLCDataset)))
/*      */     {
/* 1210 */       OHLCDataset ohlc = (OHLCDataset)dataset;
/* 1211 */       for (int series = 0; series < seriesCount; series++) {
/* 1212 */         int itemCount = dataset.getItemCount(series);
/* 1213 */         for (int item = 0; item < itemCount; item++) {
/* 1214 */           double lvalue = ohlc.getLowValue(series, item);
/* 1215 */           double uvalue = ohlc.getHighValue(series, item);
/* 1216 */           if (!Double.isNaN(lvalue)) {
/* 1217 */             minimum = Math.min(minimum, lvalue);
/*      */           }
/* 1219 */           if (!Double.isNaN(uvalue)) {
/* 1220 */             maximum = Math.max(maximum, uvalue);
/*      */           }
/*      */         }
/*      */       }
/*      */     }
/*      */     else
/*      */     {
/* 1227 */       for (int series = 0; series < seriesCount; series++) {
/* 1228 */         int itemCount = dataset.getItemCount(series);
/* 1229 */         for (int item = 0; item < itemCount; item++) {
/* 1230 */           double value = dataset.getYValue(series, item);
/* 1231 */           if (!Double.isNaN(value)) {
/* 1232 */             minimum = Math.min(minimum, value);
/* 1233 */             maximum = Math.max(maximum, value);
/*      */           }
/*      */         }
/*      */       }
/*      */     }
/* 1238 */     if (minimum == Double.POSITIVE_INFINITY) {
/* 1239 */       return null;
/*      */     }
/*      */     
/* 1242 */     return new Range(minimum, maximum);
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public static Range iterateToFindDomainBounds(XYDataset dataset, List visibleSeriesKeys, boolean includeInterval)
/*      */   {
/* 1264 */     if (dataset == null) {
/* 1265 */       throw new IllegalArgumentException("Null 'dataset' argument.");
/*      */     }
/* 1267 */     if (visibleSeriesKeys == null) {
/* 1268 */       throw new IllegalArgumentException("Null 'visibleSeriesKeys' argument.");
/*      */     }
/*      */     
/*      */ 
/* 1272 */     double minimum = Double.POSITIVE_INFINITY;
/* 1273 */     double maximum = Double.NEGATIVE_INFINITY;
/*      */     
/* 1275 */     if ((includeInterval) && ((dataset instanceof IntervalXYDataset)))
/*      */     {
/* 1277 */       IntervalXYDataset ixyd = (IntervalXYDataset)dataset;
/* 1278 */       Iterator iterator = visibleSeriesKeys.iterator();
/* 1279 */       while (iterator.hasNext()) {
/* 1280 */         Comparable seriesKey = (Comparable)iterator.next();
/* 1281 */         int series = dataset.indexOf(seriesKey);
/* 1282 */         int itemCount = dataset.getItemCount(series);
/* 1283 */         for (int item = 0; item < itemCount; item++) {
/* 1284 */           double lvalue = ixyd.getStartXValue(series, item);
/* 1285 */           double uvalue = ixyd.getEndXValue(series, item);
/* 1286 */           if (!Double.isNaN(lvalue)) {
/* 1287 */             minimum = Math.min(minimum, lvalue);
/*      */           }
/* 1289 */           if (!Double.isNaN(uvalue)) {
/* 1290 */             maximum = Math.max(maximum, uvalue);
/*      */           }
/*      */         }
/*      */       }
/*      */     }
/*      */     else
/*      */     {
/* 1297 */       Iterator iterator = visibleSeriesKeys.iterator();
/* 1298 */       while (iterator.hasNext()) {
/* 1299 */         Comparable seriesKey = (Comparable)iterator.next();
/* 1300 */         int series = dataset.indexOf(seriesKey);
/* 1301 */         int itemCount = dataset.getItemCount(series);
/* 1302 */         for (int item = 0; item < itemCount; item++) {
/* 1303 */           double x = dataset.getXValue(series, item);
/* 1304 */           if (!Double.isNaN(x)) {
/* 1305 */             minimum = Math.min(minimum, x);
/* 1306 */             maximum = Math.max(maximum, x);
/*      */           }
/*      */         }
/*      */       }
/*      */     }
/*      */     
/* 1312 */     if (minimum == Double.POSITIVE_INFINITY) {
/* 1313 */       return null;
/*      */     }
/*      */     
/* 1316 */     return new Range(minimum, maximum);
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public static Range iterateToFindRangeBounds(XYDataset dataset, List visibleSeriesKeys, Range xRange, boolean includeInterval)
/*      */   {
/* 1340 */     if (dataset == null) {
/* 1341 */       throw new IllegalArgumentException("Null 'dataset' argument.");
/*      */     }
/* 1343 */     if (visibleSeriesKeys == null) {
/* 1344 */       throw new IllegalArgumentException("Null 'visibleSeriesKeys' argument.");
/*      */     }
/*      */     
/* 1347 */     if (xRange == null) {
/* 1348 */       throw new IllegalArgumentException("Null 'xRange' argument");
/*      */     }
/*      */     
/* 1351 */     double minimum = Double.POSITIVE_INFINITY;
/* 1352 */     double maximum = Double.NEGATIVE_INFINITY;
/*      */     
/*      */ 
/* 1355 */     if ((includeInterval) && ((dataset instanceof OHLCDataset)))
/*      */     {
/* 1357 */       OHLCDataset ohlc = (OHLCDataset)dataset;
/* 1358 */       Iterator iterator = visibleSeriesKeys.iterator();
/* 1359 */       while (iterator.hasNext()) {
/* 1360 */         Comparable seriesKey = (Comparable)iterator.next();
/* 1361 */         int series = dataset.indexOf(seriesKey);
/* 1362 */         int itemCount = dataset.getItemCount(series);
/* 1363 */         for (int item = 0; item < itemCount; item++) {
/* 1364 */           double x = ohlc.getXValue(series, item);
/* 1365 */           if (xRange.contains(x)) {
/* 1366 */             double lvalue = ohlc.getLowValue(series, item);
/* 1367 */             double uvalue = ohlc.getHighValue(series, item);
/* 1368 */             if (!Double.isNaN(lvalue)) {
/* 1369 */               minimum = Math.min(minimum, lvalue);
/*      */             }
/* 1371 */             if (!Double.isNaN(uvalue)) {
/* 1372 */               maximum = Math.max(maximum, uvalue);
/*      */             }
/*      */           }
/*      */         }
/*      */       }
/*      */     }
/* 1378 */     else if ((includeInterval) && ((dataset instanceof BoxAndWhiskerXYDataset)))
/*      */     {
/* 1380 */       BoxAndWhiskerXYDataset bx = (BoxAndWhiskerXYDataset)dataset;
/* 1381 */       Iterator iterator = visibleSeriesKeys.iterator();
/* 1382 */       while (iterator.hasNext()) {
/* 1383 */         Comparable seriesKey = (Comparable)iterator.next();
/* 1384 */         int series = dataset.indexOf(seriesKey);
/* 1385 */         int itemCount = dataset.getItemCount(series);
/* 1386 */         for (int item = 0; item < itemCount; item++) {
/* 1387 */           double x = bx.getXValue(series, item);
/* 1388 */           if (xRange.contains(x)) {
/* 1389 */             Number lvalue = bx.getMinRegularValue(series, item);
/* 1390 */             Number uvalue = bx.getMaxRegularValue(series, item);
/* 1391 */             if (lvalue != null) {
/* 1392 */               minimum = Math.min(minimum, lvalue.doubleValue());
/*      */             }
/* 1394 */             if (uvalue != null) {
/* 1395 */               maximum = Math.max(maximum, uvalue.doubleValue());
/*      */             }
/*      */           }
/*      */         }
/*      */       }
/*      */     }
/* 1401 */     else if ((includeInterval) && ((dataset instanceof IntervalXYDataset)))
/*      */     {
/* 1403 */       IntervalXYDataset ixyd = (IntervalXYDataset)dataset;
/* 1404 */       Iterator iterator = visibleSeriesKeys.iterator();
/* 1405 */       while (iterator.hasNext()) {
/* 1406 */         Comparable seriesKey = (Comparable)iterator.next();
/* 1407 */         int series = dataset.indexOf(seriesKey);
/* 1408 */         int itemCount = dataset.getItemCount(series);
/* 1409 */         for (int item = 0; item < itemCount; item++) {
/* 1410 */           double x = ixyd.getXValue(series, item);
/* 1411 */           if (xRange.contains(x)) {
/* 1412 */             double lvalue = ixyd.getStartYValue(series, item);
/* 1413 */             double uvalue = ixyd.getEndYValue(series, item);
/* 1414 */             if (!Double.isNaN(lvalue)) {
/* 1415 */               minimum = Math.min(minimum, lvalue);
/*      */             }
/* 1417 */             if (!Double.isNaN(uvalue)) {
/* 1418 */               maximum = Math.max(maximum, uvalue);
/*      */             }
/*      */           }
/*      */         }
/*      */       }
/*      */     }
/*      */     else
/*      */     {
/* 1426 */       Iterator iterator = visibleSeriesKeys.iterator();
/* 1427 */       while (iterator.hasNext()) {
/* 1428 */         Comparable seriesKey = (Comparable)iterator.next();
/* 1429 */         int series = dataset.indexOf(seriesKey);
/* 1430 */         int itemCount = dataset.getItemCount(series);
/* 1431 */         for (int item = 0; item < itemCount; item++) {
/* 1432 */           double x = dataset.getXValue(series, item);
/* 1433 */           double y = dataset.getYValue(series, item);
/* 1434 */           if ((xRange.contains(x)) && 
/* 1435 */             (!Double.isNaN(y))) {
/* 1436 */             minimum = Math.min(minimum, y);
/* 1437 */             maximum = Math.max(maximum, y);
/*      */           }
/*      */         }
/*      */       }
/*      */     }
/*      */     
/* 1443 */     if (minimum == Double.POSITIVE_INFINITY) {
/* 1444 */       return null;
/*      */     }
/*      */     
/* 1447 */     return new Range(minimum, maximum);
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public static Number findMinimumDomainValue(XYDataset dataset)
/*      */   {
/* 1465 */     if (dataset == null) {
/* 1466 */       throw new IllegalArgumentException("Null 'dataset' argument.");
/*      */     }
/* 1468 */     Number result = null;
/*      */     
/* 1470 */     if ((dataset instanceof DomainInfo)) {
/* 1471 */       DomainInfo info = (DomainInfo)dataset;
/* 1472 */       return new Double(info.getDomainLowerBound(true));
/*      */     }
/*      */     
/* 1475 */     double minimum = Double.POSITIVE_INFINITY;
/* 1476 */     int seriesCount = dataset.getSeriesCount();
/* 1477 */     for (int series = 0; series < seriesCount; series++) {
/* 1478 */       int itemCount = dataset.getItemCount(series);
/* 1479 */       for (int item = 0; item < itemCount; item++) {
/*      */         double value;
/*      */         double value;
/* 1482 */         if ((dataset instanceof IntervalXYDataset)) {
/* 1483 */           IntervalXYDataset intervalXYData = (IntervalXYDataset)dataset;
/*      */           
/* 1485 */           value = intervalXYData.getStartXValue(series, item);
/*      */         }
/*      */         else {
/* 1488 */           value = dataset.getXValue(series, item);
/*      */         }
/* 1490 */         if (!Double.isNaN(value)) {
/* 1491 */           minimum = Math.min(minimum, value);
/*      */         }
/*      */       }
/*      */     }
/*      */     
/* 1496 */     if (minimum == Double.POSITIVE_INFINITY) {
/* 1497 */       result = null;
/*      */     }
/*      */     else {
/* 1500 */       result = new Double(minimum);
/*      */     }
/*      */     
/*      */ 
/* 1504 */     return result;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public static Number findMaximumDomainValue(XYDataset dataset)
/*      */   {
/* 1520 */     if (dataset == null) {
/* 1521 */       throw new IllegalArgumentException("Null 'dataset' argument.");
/*      */     }
/* 1523 */     Number result = null;
/*      */     
/* 1525 */     if ((dataset instanceof DomainInfo)) {
/* 1526 */       DomainInfo info = (DomainInfo)dataset;
/* 1527 */       return new Double(info.getDomainUpperBound(true));
/*      */     }
/*      */     
/*      */ 
/*      */ 
/* 1532 */     double maximum = Double.NEGATIVE_INFINITY;
/* 1533 */     int seriesCount = dataset.getSeriesCount();
/* 1534 */     for (int series = 0; series < seriesCount; series++) {
/* 1535 */       int itemCount = dataset.getItemCount(series);
/* 1536 */       for (int item = 0; item < itemCount; item++) {
/*      */         double value;
/*      */         double value;
/* 1539 */         if ((dataset instanceof IntervalXYDataset)) {
/* 1540 */           IntervalXYDataset intervalXYData = (IntervalXYDataset)dataset;
/*      */           
/* 1542 */           value = intervalXYData.getEndXValue(series, item);
/*      */         }
/*      */         else {
/* 1545 */           value = dataset.getXValue(series, item);
/*      */         }
/* 1547 */         if (!Double.isNaN(value)) {
/* 1548 */           maximum = Math.max(maximum, value);
/*      */         }
/*      */       }
/*      */     }
/* 1552 */     if (maximum == Double.NEGATIVE_INFINITY) {
/* 1553 */       result = null;
/*      */     }
/*      */     else {
/* 1556 */       result = new Double(maximum);
/*      */     }
/*      */     
/*      */ 
/*      */ 
/* 1561 */     return result;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public static Number findMinimumRangeValue(CategoryDataset dataset)
/*      */   {
/* 1578 */     if (dataset == null) {
/* 1579 */       throw new IllegalArgumentException("Null 'dataset' argument.");
/*      */     }
/*      */     
/* 1582 */     if ((dataset instanceof RangeInfo)) {
/* 1583 */       RangeInfo info = (RangeInfo)dataset;
/* 1584 */       return new Double(info.getRangeLowerBound(true));
/*      */     }
/*      */     
/*      */ 
/*      */ 
/* 1589 */     double minimum = Double.POSITIVE_INFINITY;
/* 1590 */     int seriesCount = dataset.getRowCount();
/* 1591 */     int itemCount = dataset.getColumnCount();
/* 1592 */     for (int series = 0; series < seriesCount; series++) {
/* 1593 */       for (int item = 0; item < itemCount; item++) { Number value;
/*      */         Number value;
/* 1595 */         if ((dataset instanceof IntervalCategoryDataset)) {
/* 1596 */           IntervalCategoryDataset icd = (IntervalCategoryDataset)dataset;
/*      */           
/* 1598 */           value = icd.getStartValue(series, item);
/*      */         }
/*      */         else {
/* 1601 */           value = dataset.getValue(series, item);
/*      */         }
/* 1603 */         if (value != null) {
/* 1604 */           minimum = Math.min(minimum, value.doubleValue());
/*      */         }
/*      */       }
/*      */     }
/* 1608 */     if (minimum == Double.POSITIVE_INFINITY) {
/* 1609 */       return null;
/*      */     }
/*      */     
/* 1612 */     return new Double(minimum);
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public static Number findMinimumRangeValue(XYDataset dataset)
/*      */   {
/* 1633 */     if (dataset == null) {
/* 1634 */       throw new IllegalArgumentException("Null 'dataset' argument.");
/*      */     }
/*      */     
/*      */ 
/* 1638 */     if ((dataset instanceof RangeInfo)) {
/* 1639 */       RangeInfo info = (RangeInfo)dataset;
/* 1640 */       return new Double(info.getRangeLowerBound(true));
/*      */     }
/*      */     
/*      */ 
/*      */ 
/* 1645 */     double minimum = Double.POSITIVE_INFINITY;
/* 1646 */     int seriesCount = dataset.getSeriesCount();
/* 1647 */     for (int series = 0; series < seriesCount; series++) {
/* 1648 */       int itemCount = dataset.getItemCount(series);
/* 1649 */       for (int item = 0; item < itemCount; item++) {
/*      */         double value;
/*      */         double value;
/* 1652 */         if ((dataset instanceof IntervalXYDataset)) {
/* 1653 */           IntervalXYDataset intervalXYData = (IntervalXYDataset)dataset;
/*      */           
/* 1655 */           value = intervalXYData.getStartYValue(series, item);
/*      */         } else { double value;
/* 1657 */           if ((dataset instanceof OHLCDataset)) {
/* 1658 */             OHLCDataset highLowData = (OHLCDataset)dataset;
/* 1659 */             value = highLowData.getLowValue(series, item);
/*      */           }
/*      */           else {
/* 1662 */             value = dataset.getYValue(series, item);
/*      */           } }
/* 1664 */         if (!Double.isNaN(value)) {
/* 1665 */           minimum = Math.min(minimum, value);
/*      */         }
/*      */       }
/*      */     }
/*      */     
/* 1670 */     if (minimum == Double.POSITIVE_INFINITY) {
/* 1671 */       return null;
/*      */     }
/*      */     
/* 1674 */     return new Double(minimum);
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public static Number findMaximumRangeValue(CategoryDataset dataset)
/*      */   {
/* 1694 */     if (dataset == null) {
/* 1695 */       throw new IllegalArgumentException("Null 'dataset' argument.");
/*      */     }
/*      */     
/*      */ 
/* 1699 */     if ((dataset instanceof RangeInfo)) {
/* 1700 */       RangeInfo info = (RangeInfo)dataset;
/* 1701 */       return new Double(info.getRangeUpperBound(true));
/*      */     }
/*      */     
/*      */ 
/*      */ 
/*      */ 
/* 1707 */     double maximum = Double.NEGATIVE_INFINITY;
/* 1708 */     int seriesCount = dataset.getRowCount();
/* 1709 */     int itemCount = dataset.getColumnCount();
/* 1710 */     for (int series = 0; series < seriesCount; series++) {
/* 1711 */       for (int item = 0; item < itemCount; item++) { Number value;
/*      */         Number value;
/* 1713 */         if ((dataset instanceof IntervalCategoryDataset)) {
/* 1714 */           IntervalCategoryDataset icd = (IntervalCategoryDataset)dataset;
/*      */           
/* 1716 */           value = icd.getEndValue(series, item);
/*      */         }
/*      */         else {
/* 1719 */           value = dataset.getValue(series, item);
/*      */         }
/* 1721 */         if (value != null) {
/* 1722 */           maximum = Math.max(maximum, value.doubleValue());
/*      */         }
/*      */       }
/*      */     }
/* 1726 */     if (maximum == Double.NEGATIVE_INFINITY) {
/* 1727 */       return null;
/*      */     }
/*      */     
/* 1730 */     return new Double(maximum);
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public static Number findMaximumRangeValue(XYDataset dataset)
/*      */   {
/* 1750 */     if (dataset == null) {
/* 1751 */       throw new IllegalArgumentException("Null 'dataset' argument.");
/*      */     }
/*      */     
/*      */ 
/* 1755 */     if ((dataset instanceof RangeInfo)) {
/* 1756 */       RangeInfo info = (RangeInfo)dataset;
/* 1757 */       return new Double(info.getRangeUpperBound(true));
/*      */     }
/*      */     
/*      */ 
/*      */ 
/*      */ 
/* 1763 */     double maximum = Double.NEGATIVE_INFINITY;
/* 1764 */     int seriesCount = dataset.getSeriesCount();
/* 1765 */     for (int series = 0; series < seriesCount; series++) {
/* 1766 */       int itemCount = dataset.getItemCount(series);
/* 1767 */       for (int item = 0; item < itemCount; item++) { double value;
/*      */         double value;
/* 1769 */         if ((dataset instanceof IntervalXYDataset)) {
/* 1770 */           IntervalXYDataset intervalXYData = (IntervalXYDataset)dataset;
/*      */           
/* 1772 */           value = intervalXYData.getEndYValue(series, item);
/*      */         } else { double value;
/* 1774 */           if ((dataset instanceof OHLCDataset)) {
/* 1775 */             OHLCDataset highLowData = (OHLCDataset)dataset;
/* 1776 */             value = highLowData.getHighValue(series, item);
/*      */           }
/*      */           else {
/* 1779 */             value = dataset.getYValue(series, item);
/*      */           } }
/* 1781 */         if (!Double.isNaN(value)) {
/* 1782 */           maximum = Math.max(maximum, value);
/*      */         }
/*      */       }
/*      */     }
/* 1786 */     if (maximum == Double.NEGATIVE_INFINITY) {
/* 1787 */       return null;
/*      */     }
/*      */     
/* 1790 */     return new Double(maximum);
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public static Range findStackedRangeBounds(CategoryDataset dataset)
/*      */   {
/* 1806 */     return findStackedRangeBounds(dataset, 0.0D);
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public static Range findStackedRangeBounds(CategoryDataset dataset, double base)
/*      */   {
/* 1820 */     if (dataset == null) {
/* 1821 */       throw new IllegalArgumentException("Null 'dataset' argument.");
/*      */     }
/* 1823 */     Range result = null;
/* 1824 */     double minimum = Double.POSITIVE_INFINITY;
/* 1825 */     double maximum = Double.NEGATIVE_INFINITY;
/* 1826 */     int categoryCount = dataset.getColumnCount();
/* 1827 */     for (int item = 0; item < categoryCount; item++) {
/* 1828 */       double positive = base;
/* 1829 */       double negative = base;
/* 1830 */       int seriesCount = dataset.getRowCount();
/* 1831 */       for (int series = 0; series < seriesCount; series++) {
/* 1832 */         Number number = dataset.getValue(series, item);
/* 1833 */         if (number != null) {
/* 1834 */           double value = number.doubleValue();
/* 1835 */           if (value > 0.0D) {
/* 1836 */             positive += value;
/*      */           }
/* 1838 */           if (value < 0.0D) {
/* 1839 */             negative += value;
/*      */           }
/*      */         }
/*      */       }
/*      */       
/* 1844 */       minimum = Math.min(minimum, negative);
/* 1845 */       maximum = Math.max(maximum, positive);
/*      */     }
/* 1847 */     if (minimum <= maximum) {
/* 1848 */       result = new Range(minimum, maximum);
/*      */     }
/* 1850 */     return result;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public static Range findStackedRangeBounds(CategoryDataset dataset, KeyToGroupMap map)
/*      */   {
/* 1866 */     if (dataset == null) {
/* 1867 */       throw new IllegalArgumentException("Null 'dataset' argument.");
/*      */     }
/* 1869 */     boolean hasValidData = false;
/* 1870 */     Range result = null;
/*      */     
/*      */ 
/* 1873 */     int[] groupIndex = new int[dataset.getRowCount()];
/* 1874 */     for (int i = 0; i < dataset.getRowCount(); i++) {
/* 1875 */       groupIndex[i] = map.getGroupIndex(map.getGroup(dataset.getRowKey(i)));
/*      */     }
/*      */     
/*      */ 
/*      */ 
/* 1880 */     int groupCount = map.getGroupCount();
/* 1881 */     double[] minimum = new double[groupCount];
/* 1882 */     double[] maximum = new double[groupCount];
/*      */     
/* 1884 */     int categoryCount = dataset.getColumnCount();
/* 1885 */     for (int item = 0; item < categoryCount; item++) {
/* 1886 */       double[] positive = new double[groupCount];
/* 1887 */       double[] negative = new double[groupCount];
/* 1888 */       int seriesCount = dataset.getRowCount();
/* 1889 */       for (int series = 0; series < seriesCount; series++) {
/* 1890 */         Number number = dataset.getValue(series, item);
/* 1891 */         if (number != null) {
/* 1892 */           hasValidData = true;
/* 1893 */           double value = number.doubleValue();
/* 1894 */           if (value > 0.0D) {
/* 1895 */             positive[groupIndex[series]] += value;
/*      */           }
/*      */           
/* 1898 */           if (value < 0.0D) {
/* 1899 */             negative[groupIndex[series]] += value;
/*      */           }
/*      */         }
/*      */       }
/*      */       
/*      */ 
/* 1905 */       for (int g = 0; g < groupCount; g++) {
/* 1906 */         minimum[g] = Math.min(minimum[g], negative[g]);
/* 1907 */         maximum[g] = Math.max(maximum[g], positive[g]);
/*      */       }
/*      */     }
/* 1910 */     if (hasValidData) {
/* 1911 */       for (int j = 0; j < groupCount; j++) {
/* 1912 */         result = Range.combine(result, new Range(minimum[j], maximum[j]));
/*      */       }
/*      */     }
/*      */     
/* 1916 */     return result;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public static Number findMinimumStackedRangeValue(CategoryDataset dataset)
/*      */   {
/* 1930 */     if (dataset == null) {
/* 1931 */       throw new IllegalArgumentException("Null 'dataset' argument.");
/*      */     }
/* 1933 */     Number result = null;
/* 1934 */     boolean hasValidData = false;
/* 1935 */     double minimum = 0.0D;
/* 1936 */     int categoryCount = dataset.getColumnCount();
/* 1937 */     for (int item = 0; item < categoryCount; item++) {
/* 1938 */       double total = 0.0D;
/* 1939 */       int seriesCount = dataset.getRowCount();
/* 1940 */       for (int series = 0; series < seriesCount; series++) {
/* 1941 */         Number number = dataset.getValue(series, item);
/* 1942 */         if (number != null) {
/* 1943 */           hasValidData = true;
/* 1944 */           double value = number.doubleValue();
/* 1945 */           if (value < 0.0D) {
/* 1946 */             total += value;
/*      */           }
/*      */         }
/*      */       }
/*      */       
/* 1951 */       minimum = Math.min(minimum, total);
/*      */     }
/* 1953 */     if (hasValidData) {
/* 1954 */       result = new Double(minimum);
/*      */     }
/* 1956 */     return result;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public static Number findMaximumStackedRangeValue(CategoryDataset dataset)
/*      */   {
/* 1970 */     if (dataset == null) {
/* 1971 */       throw new IllegalArgumentException("Null 'dataset' argument.");
/*      */     }
/* 1973 */     Number result = null;
/* 1974 */     boolean hasValidData = false;
/* 1975 */     double maximum = 0.0D;
/* 1976 */     int categoryCount = dataset.getColumnCount();
/* 1977 */     for (int item = 0; item < categoryCount; item++) {
/* 1978 */       double total = 0.0D;
/* 1979 */       int seriesCount = dataset.getRowCount();
/* 1980 */       for (int series = 0; series < seriesCount; series++) {
/* 1981 */         Number number = dataset.getValue(series, item);
/* 1982 */         if (number != null) {
/* 1983 */           hasValidData = true;
/* 1984 */           double value = number.doubleValue();
/* 1985 */           if (value > 0.0D) {
/* 1986 */             total += value;
/*      */           }
/*      */         }
/*      */       }
/* 1990 */       maximum = Math.max(maximum, total);
/*      */     }
/* 1992 */     if (hasValidData) {
/* 1993 */       result = new Double(maximum);
/*      */     }
/* 1995 */     return result;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public static Range findStackedRangeBounds(TableXYDataset dataset)
/*      */   {
/* 2007 */     return findStackedRangeBounds(dataset, 0.0D);
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public static Range findStackedRangeBounds(TableXYDataset dataset, double base)
/*      */   {
/* 2021 */     if (dataset == null) {
/* 2022 */       throw new IllegalArgumentException("Null 'dataset' argument.");
/*      */     }
/* 2024 */     double minimum = base;
/* 2025 */     double maximum = base;
/* 2026 */     for (int itemNo = 0; itemNo < dataset.getItemCount(); itemNo++) {
/* 2027 */       double positive = base;
/* 2028 */       double negative = base;
/* 2029 */       int seriesCount = dataset.getSeriesCount();
/* 2030 */       for (int seriesNo = 0; seriesNo < seriesCount; seriesNo++) {
/* 2031 */         double y = dataset.getYValue(seriesNo, itemNo);
/* 2032 */         if (!Double.isNaN(y)) {
/* 2033 */           if (y > 0.0D) {
/* 2034 */             positive += y;
/*      */           }
/*      */           else {
/* 2037 */             negative += y;
/*      */           }
/*      */         }
/*      */       }
/* 2041 */       if (positive > maximum) {
/* 2042 */         maximum = positive;
/*      */       }
/* 2044 */       if (negative < minimum) {
/* 2045 */         minimum = negative;
/*      */       }
/*      */     }
/* 2048 */     if (minimum <= maximum) {
/* 2049 */       return new Range(minimum, maximum);
/*      */     }
/*      */     
/* 2052 */     return null;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public static double calculateStackTotal(TableXYDataset dataset, int item)
/*      */   {
/* 2068 */     double total = 0.0D;
/* 2069 */     int seriesCount = dataset.getSeriesCount();
/* 2070 */     for (int s = 0; s < seriesCount; s++) {
/* 2071 */       double value = dataset.getYValue(s, item);
/* 2072 */       if (!Double.isNaN(value)) {
/* 2073 */         total += value;
/*      */       }
/*      */     }
/* 2076 */     return total;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public static Range findCumulativeRangeBounds(CategoryDataset dataset)
/*      */   {
/* 2090 */     if (dataset == null) {
/* 2091 */       throw new IllegalArgumentException("Null 'dataset' argument.");
/*      */     }
/* 2093 */     boolean allItemsNull = true;
/*      */     
/* 2095 */     double minimum = 0.0D;
/* 2096 */     double maximum = 0.0D;
/* 2097 */     for (int row = 0; row < dataset.getRowCount(); row++) {
/* 2098 */       double runningTotal = 0.0D;
/* 2099 */       for (int column = 0; column <= dataset.getColumnCount() - 1; 
/* 2100 */           column++) {
/* 2101 */         Number n = dataset.getValue(row, column);
/* 2102 */         if (n != null) {
/* 2103 */           allItemsNull = false;
/* 2104 */           double value = n.doubleValue();
/* 2105 */           if (!Double.isNaN(value)) {
/* 2106 */             runningTotal += value;
/* 2107 */             minimum = Math.min(minimum, runningTotal);
/* 2108 */             maximum = Math.max(maximum, runningTotal);
/*      */           }
/*      */         }
/*      */       }
/*      */     }
/* 2113 */     if (!allItemsNull) {
/* 2114 */       return new Range(minimum, maximum);
/*      */     }
/*      */     
/* 2117 */     return null;
/*      */   }
/*      */ }


/* Location:              /home/menick/Scrivania/ProcessMining/CNMining/CNMining.jar!/org/jfree/data/general/DatasetUtilities.class
 * Java compiler version: 3 (47.0)
 * JD-Core Version:       0.7.1
 */