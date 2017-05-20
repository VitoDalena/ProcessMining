/*      */ package org.jfree.chart.axis;
/*      */ 
/*      */ import java.awt.Graphics2D;
/*      */ import java.awt.geom.Rectangle2D;
/*      */ import java.text.DecimalFormat;
/*      */ import java.text.NumberFormat;
/*      */ import java.util.ArrayList;
/*      */ import java.util.List;
/*      */ import org.jfree.chart.plot.Plot;
/*      */ import org.jfree.chart.plot.ValueAxisPlot;
/*      */ import org.jfree.data.Range;
/*      */ import org.jfree.ui.RectangleEdge;
/*      */ import org.jfree.ui.TextAnchor;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ public class LogarithmicAxis
/*      */   extends NumberAxis
/*      */ {
/*      */   private static final long serialVersionUID = 2502918599004103054L;
/*  118 */   public static final double LOG10_VALUE = Math.log(10.0D);
/*      */   
/*      */ 
/*      */   public static final double SMALL_LOG_VALUE = 1.0E-100D;
/*      */   
/*      */ 
/*  124 */   protected boolean allowNegativesFlag = false;
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*  130 */   protected boolean strictValuesFlag = true;
/*      */   
/*      */ 
/*  133 */   protected final NumberFormat numberFormatterObj = NumberFormat.getInstance();
/*      */   
/*      */ 
/*      */ 
/*  137 */   protected boolean expTickLabelsFlag = false;
/*      */   
/*      */ 
/*  140 */   protected boolean log10TickLabelsFlag = false;
/*      */   
/*      */ 
/*  143 */   protected boolean autoRangeNextLogFlag = false;
/*      */   
/*      */ 
/*  146 */   protected boolean smallLogFlag = false;
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public LogarithmicAxis(String label)
/*      */   {
/*  154 */     super(label);
/*  155 */     setupNumberFmtObj();
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public void setAllowNegativesFlag(boolean flgVal)
/*      */   {
/*  166 */     this.allowNegativesFlag = flgVal;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public boolean getAllowNegativesFlag()
/*      */   {
/*  177 */     return this.allowNegativesFlag;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public void setStrictValuesFlag(boolean flgVal)
/*      */   {
/*  189 */     this.strictValuesFlag = flgVal;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public boolean getStrictValuesFlag()
/*      */   {
/*  201 */     return this.strictValuesFlag;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public void setExpTickLabelsFlag(boolean flgVal)
/*      */   {
/*  213 */     this.expTickLabelsFlag = flgVal;
/*  214 */     setupNumberFmtObj();
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public boolean getExpTickLabelsFlag()
/*      */   {
/*  224 */     return this.expTickLabelsFlag;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public void setLog10TickLabelsFlag(boolean flag)
/*      */   {
/*  234 */     this.log10TickLabelsFlag = flag;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public boolean getLog10TickLabelsFlag()
/*      */   {
/*  245 */     return this.log10TickLabelsFlag;
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
/*      */   public void setAutoRangeNextLogFlag(boolean flag)
/*      */   {
/*  258 */     this.autoRangeNextLogFlag = flag;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public boolean getAutoRangeNextLogFlag()
/*      */   {
/*  268 */     return this.autoRangeNextLogFlag;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public void setRange(Range range)
/*      */   {
/*  278 */     super.setRange(range);
/*  279 */     setupSmallLogFlag();
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   protected void setupSmallLogFlag()
/*      */   {
/*  289 */     double lowerVal = getRange().getLowerBound();
/*  290 */     this.smallLogFlag = ((!this.allowNegativesFlag) && (lowerVal < 10.0D) && (lowerVal > 0.0D));
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   protected void setupNumberFmtObj()
/*      */   {
/*  299 */     if ((this.numberFormatterObj instanceof DecimalFormat))
/*      */     {
/*      */ 
/*  302 */       ((DecimalFormat)this.numberFormatterObj).applyPattern(this.expTickLabelsFlag ? "0E0" : "0.###");
/*      */     }
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
/*      */   protected double switchedLog10(double val)
/*      */   {
/*  321 */     return this.smallLogFlag ? Math.log(val) / LOG10_VALUE : adjustedLog10(val);
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
/*      */   public double switchedPow10(double val)
/*      */   {
/*  340 */     return this.smallLogFlag ? Math.pow(10.0D, val) : adjustedPow10(val);
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
/*      */   public double adjustedLog10(double val)
/*      */   {
/*  358 */     boolean negFlag = val < 0.0D;
/*  359 */     if (negFlag) {
/*  360 */       val = -val;
/*      */     }
/*  362 */     if (val < 10.0D) {
/*  363 */       val += (10.0D - val) / 10.0D;
/*      */     }
/*      */     
/*  366 */     double res = Math.log(val) / LOG10_VALUE;
/*  367 */     return negFlag ? -res : res;
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
/*      */   public double adjustedPow10(double val)
/*      */   {
/*  385 */     boolean negFlag = val < 0.0D;
/*  386 */     if (negFlag)
/*  387 */       val = -val;
/*      */     double res;
/*      */     double res;
/*  390 */     if (val < 1.0D) {
/*  391 */       res = (Math.pow(10.0D, val + 1.0D) - 10.0D) / 9.0D;
/*      */     }
/*      */     else {
/*  394 */       res = Math.pow(10.0D, val);
/*      */     }
/*  396 */     return negFlag ? -res : res;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */   protected double computeLogFloor(double lower)
/*      */   {
/*      */     double logFloor;
/*      */     
/*      */ 
/*      */ 
/*      */     double logFloor;
/*      */     
/*      */ 
/*      */ 
/*  412 */     if (this.allowNegativesFlag)
/*      */     {
/*  414 */       if (lower > 10.0D)
/*      */       {
/*  416 */         double logFloor = Math.log(lower) / LOG10_VALUE;
/*  417 */         logFloor = Math.floor(logFloor);
/*  418 */         logFloor = Math.pow(10.0D, logFloor);
/*      */       }
/*  420 */       else if (lower < -10.0D)
/*      */       {
/*  422 */         double logFloor = Math.log(-lower) / LOG10_VALUE;
/*      */         
/*  424 */         logFloor = Math.floor(-logFloor);
/*      */         
/*  426 */         logFloor = -Math.pow(10.0D, -logFloor);
/*      */       }
/*      */       else
/*      */       {
/*  430 */         logFloor = Math.floor(lower);
/*      */       }
/*      */       
/*      */ 
/*      */     }
/*  435 */     else if (lower > 0.0D)
/*      */     {
/*  437 */       double logFloor = Math.log(lower) / LOG10_VALUE;
/*  438 */       logFloor = Math.floor(logFloor);
/*  439 */       logFloor = Math.pow(10.0D, logFloor);
/*      */     }
/*      */     else
/*      */     {
/*  443 */       logFloor = Math.floor(lower);
/*      */     }
/*      */     
/*  446 */     return logFloor;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */   protected double computeLogCeil(double upper)
/*      */   {
/*      */     double logCeil;
/*      */     
/*      */ 
/*      */ 
/*      */     double logCeil;
/*      */     
/*      */ 
/*      */ 
/*  462 */     if (this.allowNegativesFlag)
/*      */     {
/*  464 */       if (upper > 10.0D)
/*      */       {
/*      */ 
/*  467 */         double logCeil = Math.log(upper) / LOG10_VALUE;
/*  468 */         logCeil = Math.ceil(logCeil);
/*  469 */         logCeil = Math.pow(10.0D, logCeil);
/*      */       }
/*  471 */       else if (upper < -10.0D)
/*      */       {
/*      */ 
/*  474 */         double logCeil = Math.log(-upper) / LOG10_VALUE;
/*      */         
/*  476 */         logCeil = Math.ceil(-logCeil);
/*      */         
/*  478 */         logCeil = -Math.pow(10.0D, -logCeil);
/*      */       }
/*      */       else
/*      */       {
/*  482 */         logCeil = Math.ceil(upper);
/*      */       }
/*      */       
/*      */ 
/*      */     }
/*  487 */     else if (upper > 0.0D)
/*      */     {
/*      */ 
/*  490 */       double logCeil = Math.log(upper) / LOG10_VALUE;
/*  491 */       logCeil = Math.ceil(logCeil);
/*  492 */       logCeil = Math.pow(10.0D, logCeil);
/*      */     }
/*      */     else
/*      */     {
/*  496 */       logCeil = Math.ceil(upper);
/*      */     }
/*      */     
/*  499 */     return logCeil;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */   public void autoAdjustRange()
/*      */   {
/*  507 */     Plot plot = getPlot();
/*  508 */     if (plot == null) {
/*  509 */       return;
/*      */     }
/*      */     
/*  512 */     if ((plot instanceof ValueAxisPlot)) {
/*  513 */       ValueAxisPlot vap = (ValueAxisPlot)plot;
/*      */       
/*      */ 
/*  516 */       Range r = vap.getDataRange(this);
/*  517 */       double lower; double lower; if (r == null)
/*      */       {
/*  519 */         r = getDefaultAutoRange();
/*  520 */         lower = r.getLowerBound();
/*      */       }
/*      */       else
/*      */       {
/*  524 */         lower = r.getLowerBound();
/*  525 */         if ((this.strictValuesFlag) && (!this.allowNegativesFlag) && (lower <= 0.0D))
/*      */         {
/*      */ 
/*  528 */           throw new RuntimeException("Values less than or equal to zero not allowed with logarithmic axis");
/*      */         }
/*      */       }
/*      */       
/*      */ 
/*      */       double lowerMargin;
/*      */       
/*  535 */       if ((lower > 0.0D) && ((lowerMargin = getLowerMargin()) > 0.0D))
/*      */       {
/*  537 */         double logLower = Math.log(lower) / LOG10_VALUE;
/*      */         double logAbs;
/*  539 */         if ((logAbs = Math.abs(logLower)) < 1.0D) {
/*  540 */           logAbs = 1.0D;
/*      */         }
/*  542 */         lower = Math.pow(10.0D, logLower - logAbs * lowerMargin);
/*      */       }
/*      */       
/*      */ 
/*      */ 
/*  547 */       if (this.autoRangeNextLogFlag) {
/*  548 */         lower = computeLogFloor(lower);
/*      */       }
/*      */       
/*  551 */       if ((!this.allowNegativesFlag) && (lower >= 0.0D) && (lower < 1.0E-100D))
/*      */       {
/*      */ 
/*  554 */         lower = r.getLowerBound();
/*      */       }
/*      */       
/*  557 */       double upper = r.getUpperBound();
/*      */       
/*      */       double upperMargin;
/*      */       
/*  561 */       if ((upper > 0.0D) && ((upperMargin = getUpperMargin()) > 0.0D))
/*      */       {
/*  563 */         double logUpper = Math.log(upper) / LOG10_VALUE;
/*      */         double logAbs;
/*  565 */         if ((logAbs = Math.abs(logUpper)) < 1.0D) {
/*  566 */           logAbs = 1.0D;
/*      */         }
/*  568 */         upper = Math.pow(10.0D, logUpper + logAbs * upperMargin);
/*      */       }
/*      */       
/*  571 */       if ((!this.allowNegativesFlag) && (upper < 1.0D) && (upper > 0.0D) && (lower > 0.0D))
/*      */       {
/*      */ 
/*      */ 
/*      */ 
/*  576 */         double expVal = Math.log(upper) / LOG10_VALUE;
/*  577 */         expVal = Math.ceil(-expVal + 0.001D);
/*  578 */         expVal = Math.pow(10.0D, expVal);
/*      */         
/*  580 */         upper = expVal > 0.0D ? Math.ceil(upper * expVal) / expVal : Math.ceil(upper);
/*      */ 
/*      */ 
/*      */       }
/*      */       else
/*      */       {
/*      */ 
/*  587 */         upper = this.autoRangeNextLogFlag ? computeLogCeil(upper) : Math.ceil(upper);
/*      */       }
/*      */       
/*      */ 
/*  591 */       double minRange = getAutoRangeMinimumSize();
/*  592 */       if (upper - lower < minRange) {
/*  593 */         upper = (upper + lower + minRange) / 2.0D;
/*  594 */         lower = (upper + lower - minRange) / 2.0D;
/*      */         
/*      */ 
/*  597 */         if (upper - lower < minRange) {
/*  598 */           double absUpper = Math.abs(upper);
/*      */           
/*  600 */           double adjVal = absUpper > 1.0E-100D ? absUpper / 100.0D : 0.01D;
/*      */           
/*  602 */           upper = (upper + lower + adjVal) / 2.0D;
/*  603 */           lower = (upper + lower - adjVal) / 2.0D;
/*      */         }
/*      */       }
/*      */       
/*  607 */       setRange(new Range(lower, upper), false, false);
/*  608 */       setupSmallLogFlag();
/*      */     }
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
/*      */   public double valueToJava2D(double value, Rectangle2D plotArea, RectangleEdge edge)
/*      */   {
/*  627 */     Range range = getRange();
/*  628 */     double axisMin = switchedLog10(range.getLowerBound());
/*  629 */     double axisMax = switchedLog10(range.getUpperBound());
/*      */     
/*  631 */     double min = 0.0D;
/*  632 */     double max = 0.0D;
/*  633 */     if (RectangleEdge.isTopOrBottom(edge)) {
/*  634 */       min = plotArea.getMinX();
/*  635 */       max = plotArea.getMaxX();
/*      */     }
/*  637 */     else if (RectangleEdge.isLeftOrRight(edge)) {
/*  638 */       min = plotArea.getMaxY();
/*  639 */       max = plotArea.getMinY();
/*      */     }
/*      */     
/*  642 */     value = switchedLog10(value);
/*      */     
/*  644 */     if (isInverted()) {
/*  645 */       return max - (value - axisMin) / (axisMax - axisMin) * (max - min);
/*      */     }
/*      */     
/*      */ 
/*  649 */     return min + (value - axisMin) / (axisMax - axisMin) * (max - min);
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
/*      */   public double java2DToValue(double java2DValue, Rectangle2D plotArea, RectangleEdge edge)
/*      */   {
/*  669 */     Range range = getRange();
/*  670 */     double axisMin = switchedLog10(range.getLowerBound());
/*  671 */     double axisMax = switchedLog10(range.getUpperBound());
/*      */     
/*  673 */     double plotMin = 0.0D;
/*  674 */     double plotMax = 0.0D;
/*  675 */     if (RectangleEdge.isTopOrBottom(edge)) {
/*  676 */       plotMin = plotArea.getX();
/*  677 */       plotMax = plotArea.getMaxX();
/*      */     }
/*  679 */     else if (RectangleEdge.isLeftOrRight(edge)) {
/*  680 */       plotMin = plotArea.getMaxY();
/*  681 */       plotMax = plotArea.getMinY();
/*      */     }
/*      */     
/*  684 */     if (isInverted()) {
/*  685 */       return switchedPow10(axisMax - (java2DValue - plotMin) / (plotMax - plotMin) * (axisMax - axisMin));
/*      */     }
/*      */     
/*      */ 
/*  689 */     return switchedPow10(axisMin + (java2DValue - plotMin) / (plotMax - plotMin) * (axisMax - axisMin));
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public void zoomRange(double lowerPercent, double upperPercent)
/*      */   {
/*  701 */     double startLog = switchedLog10(getRange().getLowerBound());
/*  702 */     double lengthLog = switchedLog10(getRange().getUpperBound()) - startLog;
/*      */     Range adjusted;
/*      */     Range adjusted;
/*  705 */     if (isInverted()) {
/*  706 */       adjusted = new Range(switchedPow10(startLog + lengthLog * (1.0D - upperPercent)), switchedPow10(startLog + lengthLog * (1.0D - lowerPercent)));
/*      */ 
/*      */ 
/*      */     }
/*      */     else
/*      */     {
/*      */ 
/*  713 */       adjusted = new Range(switchedPow10(startLog + lengthLog * lowerPercent), switchedPow10(startLog + lengthLog * upperPercent));
/*      */     }
/*      */     
/*      */ 
/*      */ 
/*  718 */     setRange(adjusted);
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
/*      */   protected List refreshTicksHorizontal(Graphics2D g2, Rectangle2D dataArea, RectangleEdge edge)
/*      */   {
/*  735 */     List ticks = new ArrayList();
/*  736 */     Range range = getRange();
/*      */     
/*      */ 
/*  739 */     double lowerBoundVal = range.getLowerBound();
/*      */     
/*      */ 
/*  742 */     if ((this.smallLogFlag) && (lowerBoundVal < 1.0E-100D)) {
/*  743 */       lowerBoundVal = 1.0E-100D;
/*      */     }
/*      */     
/*      */ 
/*  747 */     double upperBoundVal = range.getUpperBound();
/*      */     
/*      */ 
/*  750 */     int iBegCount = (int)Math.rint(switchedLog10(lowerBoundVal));
/*      */     
/*  752 */     int iEndCount = (int)Math.rint(switchedLog10(upperBoundVal));
/*      */     
/*  754 */     if ((iBegCount == iEndCount) && (iBegCount > 0) && (Math.pow(10.0D, iBegCount) > lowerBoundVal))
/*      */     {
/*      */ 
/*      */ 
/*  758 */       iBegCount--;
/*      */     }
/*      */     
/*      */ 
/*      */ 
/*  763 */     boolean zeroTickFlag = false;
/*  764 */     for (int i = iBegCount; i <= iEndCount; i++)
/*      */     {
/*  766 */       for (int j = 0; j < 10; j++) { String tickLabel;
/*      */         double currentTickValue;
/*  768 */         String tickLabel; if (this.smallLogFlag)
/*      */         {
/*  770 */           double currentTickValue = Math.pow(10.0D, i) + Math.pow(10.0D, i) * j;
/*  771 */           String tickLabel; if ((this.expTickLabelsFlag) || ((i < 0) && (currentTickValue > 0.0D) && (currentTickValue < 1.0D)))
/*      */           {
/*      */             String tickLabel;
/*      */             
/*      */ 
/*  776 */             if ((j == 0) || ((i > -4) && (j < 2)) || (currentTickValue >= upperBoundVal))
/*      */             {
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*  782 */               this.numberFormatterObj.setMaximumFractionDigits(-i);
/*      */               
/*      */ 
/*  785 */               tickLabel = makeTickLabel(currentTickValue, true);
/*      */             }
/*      */             else {
/*  788 */               tickLabel = "";
/*      */             }
/*      */             
/*      */ 
/*      */           }
/*      */           else
/*      */           {
/*  795 */             tickLabel = (j < 1) || ((i < 1) && (j < 5)) || (j < 4 - i) || (currentTickValue >= upperBoundVal) ? makeTickLabel(currentTickValue) : "";
/*      */           }
/*      */           
/*      */         }
/*      */         else
/*      */         {
/*  801 */           if (zeroTickFlag) {
/*  802 */             j--;
/*      */           }
/*  804 */           currentTickValue = i >= 0 ? Math.pow(10.0D, i) + Math.pow(10.0D, i) * j : -(Math.pow(10.0D, -i) - Math.pow(10.0D, -i - 1) * j);
/*      */           
/*      */ 
/*  807 */           if (!zeroTickFlag) {
/*  808 */             if ((Math.abs(currentTickValue - 1.0D) < 1.0E-4D) && (lowerBoundVal <= 0.0D) && (upperBoundVal >= 0.0D))
/*      */             {
/*      */ 
/*  811 */               currentTickValue = 0.0D;
/*  812 */               zeroTickFlag = true;
/*      */             }
/*      */           }
/*      */           else {
/*  816 */             zeroTickFlag = false;
/*      */           }
/*      */           
/*      */ 
/*      */ 
/*      */ 
/*  822 */           tickLabel = ((this.expTickLabelsFlag) && (j < 2)) || (j < 1) || ((i < 1) && (j < 5)) || (j < 4 - i) || (currentTickValue >= upperBoundVal) ? makeTickLabel(currentTickValue) : "";
/*      */         }
/*      */         
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*  829 */         if (currentTickValue > upperBoundVal) {
/*  830 */           return ticks;
/*      */         }
/*      */         
/*      */ 
/*  834 */         if (currentTickValue >= lowerBoundVal - 1.0E-100D)
/*      */         {
/*  836 */           TextAnchor anchor = null;
/*  837 */           TextAnchor rotationAnchor = null;
/*  838 */           double angle = 0.0D;
/*  839 */           if (isVerticalTickLabels()) {
/*  840 */             anchor = TextAnchor.CENTER_RIGHT;
/*  841 */             rotationAnchor = TextAnchor.CENTER_RIGHT;
/*  842 */             if (edge == RectangleEdge.TOP) {
/*  843 */               angle = 1.5707963267948966D;
/*      */             }
/*      */             else {
/*  846 */               angle = -1.5707963267948966D;
/*      */             }
/*      */             
/*      */           }
/*  850 */           else if (edge == RectangleEdge.TOP) {
/*  851 */             anchor = TextAnchor.BOTTOM_CENTER;
/*  852 */             rotationAnchor = TextAnchor.BOTTOM_CENTER;
/*      */           }
/*      */           else {
/*  855 */             anchor = TextAnchor.TOP_CENTER;
/*  856 */             rotationAnchor = TextAnchor.TOP_CENTER;
/*      */           }
/*      */           
/*      */ 
/*  860 */           Tick tick = new NumberTick(new Double(currentTickValue), tickLabel, anchor, rotationAnchor, angle);
/*      */           
/*  862 */           ticks.add(tick);
/*      */         }
/*      */       }
/*      */     }
/*  866 */     return ticks;
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
/*      */   protected List refreshTicksVertical(Graphics2D g2, Rectangle2D dataArea, RectangleEdge edge)
/*      */   {
/*  884 */     List ticks = new ArrayList();
/*      */     
/*      */ 
/*  887 */     double lowerBoundVal = getRange().getLowerBound();
/*      */     
/*      */ 
/*  890 */     if ((this.smallLogFlag) && (lowerBoundVal < 1.0E-100D)) {
/*  891 */       lowerBoundVal = 1.0E-100D;
/*      */     }
/*      */     
/*  894 */     double upperBoundVal = getRange().getUpperBound();
/*      */     
/*      */ 
/*  897 */     int iBegCount = (int)Math.rint(switchedLog10(lowerBoundVal));
/*      */     
/*  899 */     int iEndCount = (int)Math.rint(switchedLog10(upperBoundVal));
/*      */     
/*  901 */     if ((iBegCount == iEndCount) && (iBegCount > 0) && (Math.pow(10.0D, iBegCount) > lowerBoundVal))
/*      */     {
/*      */ 
/*      */ 
/*  905 */       iBegCount--;
/*      */     }
/*      */     
/*      */ 
/*      */ 
/*  910 */     boolean zeroTickFlag = false;
/*  911 */     for (int i = iBegCount; i <= iEndCount; i++)
/*      */     {
/*  913 */       int jEndCount = 10;
/*  914 */       if (i == iEndCount) {
/*  915 */         jEndCount = 1;
/*      */       }
/*      */       
/*  918 */       for (int j = 0; j < jEndCount; j++) { String tickLabel;
/*      */         double tickVal;
/*  920 */         String tickLabel; if (this.smallLogFlag)
/*      */         {
/*  922 */           double tickVal = Math.pow(10.0D, i) + Math.pow(10.0D, i) * j;
/*  923 */           String tickLabel; if (j == 0) {
/*      */             String tickLabel;
/*  925 */             if (this.log10TickLabelsFlag)
/*      */             {
/*  927 */               tickLabel = "10^" + i;
/*      */             } else {
/*      */               String tickLabel;
/*  930 */               if (this.expTickLabelsFlag)
/*      */               {
/*  932 */                 tickLabel = "1e" + i;
/*      */               } else {
/*      */                 String tickLabel;
/*  935 */                 if (i >= 0)
/*      */                 {
/*  937 */                   NumberFormat format = getNumberFormatOverride();
/*      */                   String tickLabel;
/*  939 */                   if (format != null) {
/*  940 */                     tickLabel = format.format(tickVal);
/*      */                   }
/*      */                   else {
/*  943 */                     tickLabel = Long.toString(Math.rint(tickVal));
/*      */                   }
/*      */                   
/*      */ 
/*      */                 }
/*      */                 else
/*      */                 {
/*      */ 
/*  951 */                   this.numberFormatterObj.setMaximumFractionDigits(-i);
/*      */                   
/*      */ 
/*  954 */                   tickLabel = this.numberFormatterObj.format(tickVal);
/*      */                 }
/*      */               }
/*      */             }
/*      */           }
/*      */           else
/*      */           {
/*  961 */             tickLabel = "";
/*      */           }
/*      */         }
/*      */         else {
/*  965 */           if (zeroTickFlag) {
/*  966 */             j--;
/*      */           }
/*  968 */           tickVal = i >= 0 ? Math.pow(10.0D, i) + Math.pow(10.0D, i) * j : -(Math.pow(10.0D, -i) - Math.pow(10.0D, -i - 1) * j);
/*      */           
/*  970 */           if (j == 0) { String tickLabel;
/*  971 */             if (!zeroTickFlag) {
/*      */               String tickLabel;
/*  973 */               if ((i > iBegCount) && (i < iEndCount) && (Math.abs(tickVal - 1.0D) < 1.0E-4D))
/*      */               {
/*      */ 
/*      */ 
/*  977 */                 tickVal = 0.0D;
/*  978 */                 zeroTickFlag = true;
/*  979 */                 tickLabel = "0";
/*      */               }
/*      */               else
/*      */               {
/*      */                 String tickLabel;
/*  984 */                 if (this.log10TickLabelsFlag)
/*      */                 {
/*  986 */                   tickLabel = (i < 0 ? "-" : "") + "10^" + Math.abs(i);
/*      */                 }
/*      */                 else {
/*      */                   String tickLabel;
/*  990 */                   if (this.expTickLabelsFlag)
/*      */                   {
/*  992 */                     tickLabel = (i < 0 ? "-" : "") + "1e" + Math.abs(i);
/*      */                   }
/*      */                   else
/*      */                   {
/*  996 */                     NumberFormat format = getNumberFormatOverride();
/*      */                     String tickLabel;
/*  998 */                     if (format != null) {
/*  999 */                       tickLabel = format.format(tickVal);
/*      */                     }
/*      */                     else {
/* 1002 */                       tickLabel = Long.toString(Math.rint(tickVal));
/*      */                     }
/*      */                   }
/*      */                 }
/*      */               }
/*      */             }
/*      */             else
/*      */             {
/* 1010 */               String tickLabel = "";
/* 1011 */               zeroTickFlag = false;
/*      */             }
/*      */           }
/*      */           else {
/* 1015 */             tickLabel = "";
/* 1016 */             zeroTickFlag = false;
/*      */           }
/*      */         }
/*      */         
/* 1020 */         if (tickVal > upperBoundVal) {
/* 1021 */           return ticks;
/*      */         }
/*      */         
/* 1024 */         if (tickVal >= lowerBoundVal - 1.0E-100D)
/*      */         {
/* 1026 */           TextAnchor anchor = null;
/* 1027 */           TextAnchor rotationAnchor = null;
/* 1028 */           double angle = 0.0D;
/* 1029 */           if (isVerticalTickLabels()) {
/* 1030 */             if (edge == RectangleEdge.LEFT) {
/* 1031 */               anchor = TextAnchor.BOTTOM_CENTER;
/* 1032 */               rotationAnchor = TextAnchor.BOTTOM_CENTER;
/* 1033 */               angle = -1.5707963267948966D;
/*      */             }
/*      */             else {
/* 1036 */               anchor = TextAnchor.BOTTOM_CENTER;
/* 1037 */               rotationAnchor = TextAnchor.BOTTOM_CENTER;
/* 1038 */               angle = 1.5707963267948966D;
/*      */             }
/*      */             
/*      */           }
/* 1042 */           else if (edge == RectangleEdge.LEFT) {
/* 1043 */             anchor = TextAnchor.CENTER_RIGHT;
/* 1044 */             rotationAnchor = TextAnchor.CENTER_RIGHT;
/*      */           }
/*      */           else {
/* 1047 */             anchor = TextAnchor.CENTER_LEFT;
/* 1048 */             rotationAnchor = TextAnchor.CENTER_LEFT;
/*      */           }
/*      */           
/*      */ 
/* 1052 */           ticks.add(new NumberTick(new Double(tickVal), tickLabel, anchor, rotationAnchor, angle));
/*      */         }
/*      */       }
/*      */     }
/*      */     
/* 1057 */     return ticks;
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
/*      */   protected String makeTickLabel(double val, boolean forceFmtFlag)
/*      */   {
/* 1070 */     if ((this.expTickLabelsFlag) || (forceFmtFlag))
/*      */     {
/*      */ 
/* 1073 */       return this.numberFormatterObj.format(val).toLowerCase();
/*      */     }
/* 1075 */     return getTickUnit().valueToString(val);
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   protected String makeTickLabel(double val)
/*      */   {
/* 1085 */     return makeTickLabel(val, false);
/*      */   }
/*      */ }


/* Location:              /home/menick/Scrivania/ProcessMining/CNMining/CNMining.jar!/org/jfree/chart/axis/LogarithmicAxis.class
 * Java compiler version: 3 (47.0)
 * JD-Core Version:       0.7.1
 */