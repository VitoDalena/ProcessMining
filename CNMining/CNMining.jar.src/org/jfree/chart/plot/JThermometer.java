/*     */ package org.jfree.chart.plot;
/*     */ 
/*     */ import java.awt.CardLayout;
/*     */ import java.awt.Color;
/*     */ import java.awt.Font;
/*     */ import java.awt.Paint;
/*     */ import java.io.Serializable;
/*     */ import java.text.DecimalFormat;
/*     */ import javax.swing.JPanel;
/*     */ import org.jfree.chart.ChartPanel;
/*     */ import org.jfree.chart.JFreeChart;
/*     */ import org.jfree.chart.axis.ValueAxis;
/*     */ import org.jfree.chart.title.TextTitle;
/*     */ import org.jfree.chart.title.Title;
/*     */ import org.jfree.data.general.DefaultValueDataset;
/*     */ import org.jfree.ui.RectangleInsets;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class JThermometer
/*     */   extends JPanel
/*     */   implements Serializable
/*     */ {
/*     */   private static final long serialVersionUID = 1079905665515589820L;
/*     */   private DefaultValueDataset data;
/*     */   private JFreeChart chart;
/*     */   private ChartPanel panel;
/*  85 */   private ThermometerPlot plot = new ThermometerPlot();
/*     */   
/*     */ 
/*     */ 
/*     */   public JThermometer()
/*     */   {
/*  91 */     super(new CardLayout());
/*  92 */     this.plot.setInsets(new RectangleInsets(5.0D, 5.0D, 5.0D, 5.0D));
/*  93 */     this.data = new DefaultValueDataset();
/*  94 */     this.plot.setDataset(this.data);
/*  95 */     this.chart = new JFreeChart(null, JFreeChart.DEFAULT_TITLE_FONT, this.plot, false);
/*     */     
/*  97 */     this.panel = new ChartPanel(this.chart);
/*  98 */     add(this.panel, "Panel");
/*  99 */     setBackground(getBackground());
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public void addSubtitle(Title subtitle)
/*     */   {
/* 108 */     this.chart.addSubtitle(subtitle);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public void addSubtitle(String subtitle)
/*     */   {
/* 117 */     this.chart.addSubtitle(new TextTitle(subtitle));
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public void addSubtitle(String subtitle, Font font)
/*     */   {
/* 127 */     this.chart.addSubtitle(new TextTitle(subtitle, font));
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public void setValueFormat(DecimalFormat df)
/*     */   {
/* 136 */     this.plot.setValueFormat(df);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public void setRange(double lower, double upper)
/*     */   {
/* 146 */     this.plot.setRange(lower, upper);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public void setSubrangeInfo(int range, double displayLow, double displayHigh)
/*     */   {
/* 158 */     this.plot.setSubrangeInfo(range, displayLow, displayHigh);
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
/*     */   public void setSubrangeInfo(int range, double rangeLow, double rangeHigh, double displayLow, double displayHigh)
/*     */   {
/* 174 */     this.plot.setSubrangeInfo(range, rangeLow, rangeHigh, displayLow, displayHigh);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public void setValueLocation(int loc)
/*     */   {
/* 185 */     this.plot.setValueLocation(loc);
/* 186 */     this.panel.repaint();
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public void setValuePaint(Paint paint)
/*     */   {
/* 195 */     this.plot.setValuePaint(paint);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public Number getValue()
/*     */   {
/* 204 */     if (this.data != null) {
/* 205 */       return this.data.getValue();
/*     */     }
/*     */     
/* 208 */     return null;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public void setValue(double value)
/*     */   {
/* 218 */     setValue(new Double(value));
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public void setValue(Number value)
/*     */   {
/* 227 */     if (this.data != null) {
/* 228 */       this.data.setValue(value);
/*     */     }
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public void setUnits(int i)
/*     */   {
/* 238 */     if (this.plot != null) {
/* 239 */       this.plot.setUnits(i);
/*     */     }
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public void setOutlinePaint(Paint p)
/*     */   {
/* 249 */     if (this.plot != null) {
/* 250 */       this.plot.setOutlinePaint(p);
/*     */     }
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public void setForeground(Color fg)
/*     */   {
/* 260 */     super.setForeground(fg);
/* 261 */     if (this.plot != null) {
/* 262 */       this.plot.setThermometerPaint(fg);
/*     */     }
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public void setBackground(Color bg)
/*     */   {
/* 272 */     super.setBackground(bg);
/* 273 */     if (this.plot != null) {
/* 274 */       this.plot.setBackgroundPaint(bg);
/*     */     }
/* 276 */     if (this.chart != null) {
/* 277 */       this.chart.setBackgroundPaint(bg);
/*     */     }
/* 279 */     if (this.panel != null) {
/* 280 */       this.panel.setBackground(bg);
/*     */     }
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public void setValueFont(Font f)
/*     */   {
/* 290 */     if (this.plot != null) {
/* 291 */       this.plot.setValueFont(f);
/*     */     }
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public Font getTickLabelFont()
/*     */   {
/* 301 */     ValueAxis axis = this.plot.getRangeAxis();
/* 302 */     return axis.getTickLabelFont();
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public void setTickLabelFont(Font font)
/*     */   {
/* 311 */     ValueAxis axis = this.plot.getRangeAxis();
/* 312 */     axis.setTickLabelFont(font);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public void changeTickFontSize(int delta)
/*     */   {
/* 321 */     Font f = getTickLabelFont();
/* 322 */     String fName = f.getFontName();
/* 323 */     Font newFont = new Font(fName, f.getStyle(), f.getSize() + delta);
/* 324 */     setTickLabelFont(newFont);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public void setTickFontStyle(int style)
/*     */   {
/* 333 */     Font f = getTickLabelFont();
/* 334 */     String fName = f.getFontName();
/* 335 */     Font newFont = new Font(fName, style, f.getSize());
/* 336 */     setTickLabelFont(newFont);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public void setFollowDataInSubranges(boolean flag)
/*     */   {
/* 346 */     this.plot.setFollowDataInSubranges(flag);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public void setShowValueLines(boolean b)
/*     */   {
/* 355 */     this.plot.setShowValueLines(b);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public void setShowAxisLocation(int location)
/*     */   {
/* 364 */     this.plot.setAxisLocation(location);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public int getShowAxisLocation()
/*     */   {
/* 373 */     return this.plot.getAxisLocation();
/*     */   }
/*     */ }


/* Location:              /home/menick/Scrivania/ProcessMining/CNMining/CNMining.jar!/org/jfree/chart/plot/JThermometer.class
 * Java compiler version: 3 (47.0)
 * JD-Core Version:       0.7.1
 */