/*     */ package org.jfree.chart.editor;
/*     */ 
/*     */ import java.awt.BasicStroke;
/*     */ import java.awt.BorderLayout;
/*     */ import java.awt.Color;
/*     */ import java.awt.Paint;
/*     */ import java.awt.Stroke;
/*     */ import java.awt.event.ActionEvent;
/*     */ import java.awt.event.ActionListener;
/*     */ import java.util.ResourceBundle;
/*     */ import javax.swing.BorderFactory;
/*     */ import javax.swing.JButton;
/*     */ import javax.swing.JCheckBox;
/*     */ import javax.swing.JColorChooser;
/*     */ import javax.swing.JComboBox;
/*     */ import javax.swing.JLabel;
/*     */ import javax.swing.JOptionPane;
/*     */ import javax.swing.JPanel;
/*     */ import javax.swing.JTabbedPane;
/*     */ import org.jfree.chart.axis.Axis;
/*     */ import org.jfree.chart.axis.ColorBar;
/*     */ import org.jfree.chart.plot.CategoryPlot;
/*     */ import org.jfree.chart.plot.ContourPlot;
/*     */ import org.jfree.chart.plot.Plot;
/*     */ import org.jfree.chart.plot.PlotOrientation;
/*     */ import org.jfree.chart.plot.XYPlot;
/*     */ import org.jfree.chart.renderer.category.CategoryItemRenderer;
/*     */ import org.jfree.chart.renderer.category.LineAndShapeRenderer;
/*     */ import org.jfree.chart.renderer.xy.StandardXYItemRenderer;
/*     */ import org.jfree.chart.renderer.xy.XYItemRenderer;
/*     */ import org.jfree.chart.util.ResourceBundleWrapper;
/*     */ import org.jfree.layout.LCBLayout;
/*     */ import org.jfree.ui.PaintSample;
/*     */ import org.jfree.ui.RectangleInsets;
/*     */ import org.jfree.ui.StrokeChooserPanel;
/*     */ import org.jfree.ui.StrokeSample;
/*     */ import org.jfree.util.BooleanUtilities;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ class DefaultPlotEditor
/*     */   extends JPanel
/*     */   implements ActionListener
/*     */ {
/*  92 */   private static final String[] orientationNames = { "Vertical", "Horizontal" };
/*     */   
/*     */ 
/*     */ 
/*     */   private static final int ORIENTATION_VERTICAL = 0;
/*     */   
/*     */ 
/*     */ 
/*     */   private static final int ORIENTATION_HORIZONTAL = 1;
/*     */   
/*     */ 
/*     */ 
/*     */   private PaintSample backgroundPaintSample;
/*     */   
/*     */ 
/*     */ 
/*     */   private StrokeSample outlineStrokeSample;
/*     */   
/*     */ 
/*     */ 
/*     */   private PaintSample outlinePaintSample;
/*     */   
/*     */ 
/*     */ 
/*     */   private DefaultAxisEditor domainAxisPropertyPanel;
/*     */   
/*     */ 
/*     */ 
/*     */   private DefaultAxisEditor rangeAxisPropertyPanel;
/*     */   
/*     */ 
/*     */ 
/*     */   private DefaultColorBarEditor colorBarAxisPropertyPanel;
/*     */   
/*     */ 
/*     */ 
/*     */   private StrokeSample[] availableStrokeSamples;
/*     */   
/*     */ 
/*     */ 
/*     */   private RectangleInsets plotInsets;
/*     */   
/*     */ 
/*     */ 
/*     */   private PlotOrientation plotOrientation;
/*     */   
/*     */ 
/*     */ 
/*     */   private JComboBox orientationCombo;
/*     */   
/*     */ 
/*     */ 
/*     */   private Boolean drawLines;
/*     */   
/*     */ 
/*     */ 
/*     */   private JCheckBox drawLinesCheckBox;
/*     */   
/*     */ 
/*     */ 
/*     */   private Boolean drawShapes;
/*     */   
/*     */ 
/*     */ 
/*     */   private JCheckBox drawShapesCheckBox;
/*     */   
/*     */ 
/*     */ 
/* 160 */   protected static ResourceBundle localizationResources = ResourceBundleWrapper.getBundle("org.jfree.chart.editor.LocalizationBundle");
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public DefaultPlotEditor(Plot plot)
/*     */   {
/* 176 */     this.plotInsets = plot.getInsets();
/* 177 */     this.backgroundPaintSample = new PaintSample(plot.getBackgroundPaint());
/* 178 */     this.outlineStrokeSample = new StrokeSample(plot.getOutlineStroke());
/* 179 */     this.outlinePaintSample = new PaintSample(plot.getOutlinePaint());
/* 180 */     if ((plot instanceof CategoryPlot)) {
/* 181 */       this.plotOrientation = ((CategoryPlot)plot).getOrientation();
/*     */     }
/* 183 */     else if ((plot instanceof XYPlot)) {
/* 184 */       this.plotOrientation = ((XYPlot)plot).getOrientation();
/*     */     }
/* 186 */     if ((plot instanceof CategoryPlot)) {
/* 187 */       CategoryItemRenderer renderer = ((CategoryPlot)plot).getRenderer();
/* 188 */       if ((renderer instanceof LineAndShapeRenderer)) {
/* 189 */         LineAndShapeRenderer r = (LineAndShapeRenderer)renderer;
/* 190 */         this.drawLines = BooleanUtilities.valueOf(r.getBaseLinesVisible());
/*     */         
/* 192 */         this.drawShapes = BooleanUtilities.valueOf(r.getBaseShapesVisible());
/*     */       }
/*     */       
/*     */     }
/* 196 */     else if ((plot instanceof XYPlot)) {
/* 197 */       XYItemRenderer renderer = ((XYPlot)plot).getRenderer();
/* 198 */       if ((renderer instanceof StandardXYItemRenderer)) {
/* 199 */         StandardXYItemRenderer r = (StandardXYItemRenderer)renderer;
/* 200 */         this.drawLines = BooleanUtilities.valueOf(r.getPlotLines());
/* 201 */         this.drawShapes = BooleanUtilities.valueOf(r.getBaseShapesVisible());
/*     */       }
/*     */     }
/*     */     
/*     */ 
/* 206 */     setLayout(new BorderLayout());
/*     */     
/* 208 */     this.availableStrokeSamples = new StrokeSample[4];
/* 209 */     this.availableStrokeSamples[0] = new StrokeSample(null);
/* 210 */     this.availableStrokeSamples[1] = new StrokeSample(new BasicStroke(1.0F));
/*     */     
/* 212 */     this.availableStrokeSamples[2] = new StrokeSample(new BasicStroke(2.0F));
/*     */     
/* 214 */     this.availableStrokeSamples[3] = new StrokeSample(new BasicStroke(3.0F));
/*     */     
/*     */ 
/*     */ 
/* 218 */     JPanel panel = new JPanel(new BorderLayout());
/* 219 */     panel.setBorder(BorderFactory.createTitledBorder(BorderFactory.createEtchedBorder(), plot.getPlotType() + localizationResources.getString(":")));
/*     */     
/*     */ 
/*     */ 
/* 223 */     JPanel general = new JPanel(new BorderLayout());
/* 224 */     general.setBorder(BorderFactory.createTitledBorder(localizationResources.getString("General")));
/*     */     
/*     */ 
/* 227 */     JPanel interior = new JPanel(new LCBLayout(7));
/* 228 */     interior.setBorder(BorderFactory.createEmptyBorder(0, 5, 0, 5));
/*     */     
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/* 242 */     interior.add(new JLabel(localizationResources.getString("Outline_stroke")));
/*     */     
/* 244 */     JButton button = new JButton(localizationResources.getString("Select..."));
/*     */     
/* 246 */     button.setActionCommand("OutlineStroke");
/* 247 */     button.addActionListener(this);
/* 248 */     interior.add(this.outlineStrokeSample);
/* 249 */     interior.add(button);
/*     */     
/* 251 */     interior.add(new JLabel(localizationResources.getString("Outline_Paint")));
/*     */     
/* 253 */     button = new JButton(localizationResources.getString("Select..."));
/* 254 */     button.setActionCommand("OutlinePaint");
/* 255 */     button.addActionListener(this);
/* 256 */     interior.add(this.outlinePaintSample);
/* 257 */     interior.add(button);
/*     */     
/* 259 */     interior.add(new JLabel(localizationResources.getString("Background_paint")));
/*     */     
/* 261 */     button = new JButton(localizationResources.getString("Select..."));
/* 262 */     button.setActionCommand("BackgroundPaint");
/* 263 */     button.addActionListener(this);
/* 264 */     interior.add(this.backgroundPaintSample);
/* 265 */     interior.add(button);
/*     */     
/* 267 */     if (this.plotOrientation != null) {
/* 268 */       boolean isVertical = this.plotOrientation.equals(PlotOrientation.VERTICAL);
/*     */       
/* 270 */       int index = isVertical ? 0 : 1;
/*     */       
/* 272 */       interior.add(new JLabel(localizationResources.getString("Orientation")));
/*     */       
/* 274 */       this.orientationCombo = new JComboBox(orientationNames);
/* 275 */       this.orientationCombo.setSelectedIndex(index);
/* 276 */       this.orientationCombo.setActionCommand("Orientation");
/* 277 */       this.orientationCombo.addActionListener(this);
/* 278 */       interior.add(new JPanel());
/* 279 */       interior.add(this.orientationCombo);
/*     */     }
/*     */     
/* 282 */     if (this.drawLines != null) {
/* 283 */       interior.add(new JLabel(localizationResources.getString("Draw_lines")));
/*     */       
/* 285 */       this.drawLinesCheckBox = new JCheckBox();
/* 286 */       this.drawLinesCheckBox.setSelected(this.drawLines.booleanValue());
/* 287 */       this.drawLinesCheckBox.setActionCommand("DrawLines");
/* 288 */       this.drawLinesCheckBox.addActionListener(this);
/* 289 */       interior.add(new JPanel());
/* 290 */       interior.add(this.drawLinesCheckBox);
/*     */     }
/*     */     
/* 293 */     if (this.drawShapes != null) {
/* 294 */       interior.add(new JLabel(localizationResources.getString("Draw_shapes")));
/*     */       
/* 296 */       this.drawShapesCheckBox = new JCheckBox();
/* 297 */       this.drawShapesCheckBox.setSelected(this.drawShapes.booleanValue());
/* 298 */       this.drawShapesCheckBox.setActionCommand("DrawShapes");
/* 299 */       this.drawShapesCheckBox.addActionListener(this);
/* 300 */       interior.add(new JPanel());
/* 301 */       interior.add(this.drawShapesCheckBox);
/*     */     }
/*     */     
/* 304 */     general.add(interior, "North");
/*     */     
/* 306 */     JPanel appearance = new JPanel(new BorderLayout());
/* 307 */     appearance.setBorder(BorderFactory.createEmptyBorder(2, 2, 2, 2));
/* 308 */     appearance.add(general, "North");
/*     */     
/* 310 */     JTabbedPane tabs = new JTabbedPane();
/* 311 */     tabs.setBorder(BorderFactory.createEmptyBorder(0, 5, 0, 5));
/*     */     
/* 313 */     Axis domainAxis = null;
/* 314 */     if ((plot instanceof CategoryPlot)) {
/* 315 */       domainAxis = ((CategoryPlot)plot).getDomainAxis();
/*     */     }
/* 317 */     else if ((plot instanceof XYPlot)) {
/* 318 */       domainAxis = ((XYPlot)plot).getDomainAxis();
/*     */     }
/* 320 */     this.domainAxisPropertyPanel = DefaultAxisEditor.getInstance(domainAxis);
/*     */     
/* 322 */     if (this.domainAxisPropertyPanel != null) {
/* 323 */       this.domainAxisPropertyPanel.setBorder(BorderFactory.createEmptyBorder(2, 2, 2, 2));
/*     */       
/* 325 */       tabs.add(localizationResources.getString("Domain_Axis"), this.domainAxisPropertyPanel);
/*     */     }
/*     */     
/*     */ 
/* 329 */     Axis rangeAxis = null;
/* 330 */     if ((plot instanceof CategoryPlot)) {
/* 331 */       rangeAxis = ((CategoryPlot)plot).getRangeAxis();
/*     */     }
/* 333 */     else if ((plot instanceof XYPlot)) {
/* 334 */       rangeAxis = ((XYPlot)plot).getRangeAxis();
/*     */     }
/*     */     
/* 337 */     this.rangeAxisPropertyPanel = DefaultAxisEditor.getInstance(rangeAxis);
/* 338 */     if (this.rangeAxisPropertyPanel != null) {
/* 339 */       this.rangeAxisPropertyPanel.setBorder(BorderFactory.createEmptyBorder(2, 2, 2, 2));
/*     */       
/* 341 */       tabs.add(localizationResources.getString("Range_Axis"), this.rangeAxisPropertyPanel);
/*     */     }
/*     */     
/*     */ 
/*     */ 
/* 346 */     ColorBar colorBar = null;
/* 347 */     if ((plot instanceof ContourPlot)) {
/* 348 */       colorBar = ((ContourPlot)plot).getColorBar();
/*     */     }
/*     */     
/* 351 */     this.colorBarAxisPropertyPanel = DefaultColorBarEditor.getInstance(colorBar);
/*     */     
/* 353 */     if (this.colorBarAxisPropertyPanel != null) {
/* 354 */       this.colorBarAxisPropertyPanel.setBorder(BorderFactory.createEmptyBorder(2, 2, 2, 2));
/*     */       
/* 356 */       tabs.add(localizationResources.getString("Color_Bar"), this.colorBarAxisPropertyPanel);
/*     */     }
/*     */     
/*     */ 
/*     */ 
/* 361 */     tabs.add(localizationResources.getString("Appearance"), appearance);
/* 362 */     panel.add(tabs);
/*     */     
/* 364 */     add(panel);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public RectangleInsets getPlotInsets()
/*     */   {
/* 373 */     if (this.plotInsets == null) {
/* 374 */       this.plotInsets = new RectangleInsets(0.0D, 0.0D, 0.0D, 0.0D);
/*     */     }
/* 376 */     return this.plotInsets;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public Paint getBackgroundPaint()
/*     */   {
/* 385 */     return this.backgroundPaintSample.getPaint();
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public Stroke getOutlineStroke()
/*     */   {
/* 394 */     return this.outlineStrokeSample.getStroke();
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public Paint getOutlinePaint()
/*     */   {
/* 403 */     return this.outlinePaintSample.getPaint();
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public DefaultAxisEditor getDomainAxisPropertyEditPanel()
/*     */   {
/* 413 */     return this.domainAxisPropertyPanel;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public DefaultAxisEditor getRangeAxisPropertyEditPanel()
/*     */   {
/* 423 */     return this.rangeAxisPropertyPanel;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   public void actionPerformed(ActionEvent event)
/*     */   {
/* 431 */     String command = event.getActionCommand();
/* 432 */     if (command.equals("BackgroundPaint")) {
/* 433 */       attemptBackgroundPaintSelection();
/*     */     }
/* 435 */     else if (command.equals("OutlineStroke")) {
/* 436 */       attemptOutlineStrokeSelection();
/*     */     }
/* 438 */     else if (command.equals("OutlinePaint")) {
/* 439 */       attemptOutlinePaintSelection();
/*     */ 
/*     */ 
/*     */ 
/*     */     }
/* 444 */     else if (command.equals("Orientation")) {
/* 445 */       attemptOrientationSelection();
/*     */     }
/* 447 */     else if (command.equals("DrawLines")) {
/* 448 */       attemptDrawLinesSelection();
/*     */     }
/* 450 */     else if (command.equals("DrawShapes")) {
/* 451 */       attemptDrawShapesSelection();
/*     */     }
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   private void attemptBackgroundPaintSelection()
/*     */   {
/* 460 */     Color c = JColorChooser.showDialog(this, localizationResources.getString("Background_Color"), Color.blue);
/*     */     
/* 462 */     if (c != null) {
/* 463 */       this.backgroundPaintSample.setPaint(c);
/*     */     }
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */   private void attemptOutlineStrokeSelection()
/*     */   {
/* 471 */     StrokeChooserPanel panel = new StrokeChooserPanel(this.outlineStrokeSample, this.availableStrokeSamples);
/*     */     
/* 473 */     int result = JOptionPane.showConfirmDialog(this, panel, localizationResources.getString("Stroke_Selection"), 2, -1);
/*     */     
/*     */ 
/*     */ 
/* 477 */     if (result == 0) {
/* 478 */       this.outlineStrokeSample.setStroke(panel.getSelectedStroke());
/*     */     }
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   private void attemptOutlinePaintSelection()
/*     */   {
/* 488 */     Color c = JColorChooser.showDialog(this, localizationResources.getString("Outline_Color"), Color.blue);
/*     */     
/* 490 */     if (c != null) {
/* 491 */       this.outlinePaintSample.setPaint(c);
/*     */     }
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
/*     */   private void attemptOrientationSelection()
/*     */   {
/* 518 */     int index = this.orientationCombo.getSelectedIndex();
/*     */     
/* 520 */     if (index == 0) {
/* 521 */       this.plotOrientation = PlotOrientation.VERTICAL;
/*     */     }
/*     */     else {
/* 524 */       this.plotOrientation = PlotOrientation.HORIZONTAL;
/*     */     }
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   private void attemptDrawLinesSelection()
/*     */   {
/* 534 */     this.drawLines = BooleanUtilities.valueOf(this.drawLinesCheckBox.isSelected());
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   private void attemptDrawShapesSelection()
/*     */   {
/* 543 */     this.drawShapes = BooleanUtilities.valueOf(this.drawShapesCheckBox.isSelected());
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public void updatePlotProperties(Plot plot)
/*     */   {
/* 555 */     plot.setOutlinePaint(getOutlinePaint());
/* 556 */     plot.setOutlineStroke(getOutlineStroke());
/* 557 */     plot.setBackgroundPaint(getBackgroundPaint());
/* 558 */     plot.setInsets(getPlotInsets());
/*     */     
/*     */ 
/* 561 */     if (this.domainAxisPropertyPanel != null) {
/* 562 */       Axis domainAxis = null;
/* 563 */       if ((plot instanceof CategoryPlot)) {
/* 564 */         CategoryPlot p = (CategoryPlot)plot;
/* 565 */         domainAxis = p.getDomainAxis();
/*     */       }
/* 567 */       else if ((plot instanceof XYPlot)) {
/* 568 */         XYPlot p = (XYPlot)plot;
/* 569 */         domainAxis = p.getDomainAxis();
/*     */       }
/* 571 */       if (domainAxis != null) {
/* 572 */         this.domainAxisPropertyPanel.setAxisProperties(domainAxis);
/*     */       }
/*     */     }
/*     */     
/* 576 */     if (this.rangeAxisPropertyPanel != null) {
/* 577 */       Axis rangeAxis = null;
/* 578 */       if ((plot instanceof CategoryPlot)) {
/* 579 */         CategoryPlot p = (CategoryPlot)plot;
/* 580 */         rangeAxis = p.getRangeAxis();
/*     */       }
/* 582 */       else if ((plot instanceof XYPlot)) {
/* 583 */         XYPlot p = (XYPlot)plot;
/* 584 */         rangeAxis = p.getRangeAxis();
/*     */       }
/* 586 */       if (rangeAxis != null) {
/* 587 */         this.rangeAxisPropertyPanel.setAxisProperties(rangeAxis);
/*     */       }
/*     */     }
/*     */     
/* 591 */     if (this.plotOrientation != null) {
/* 592 */       if ((plot instanceof CategoryPlot)) {
/* 593 */         CategoryPlot p = (CategoryPlot)plot;
/* 594 */         p.setOrientation(this.plotOrientation);
/*     */       }
/* 596 */       else if ((plot instanceof XYPlot)) {
/* 597 */         XYPlot p = (XYPlot)plot;
/* 598 */         p.setOrientation(this.plotOrientation);
/*     */       }
/*     */     }
/*     */     
/* 602 */     if (this.drawLines != null) {
/* 603 */       if ((plot instanceof CategoryPlot)) {
/* 604 */         CategoryPlot p = (CategoryPlot)plot;
/* 605 */         CategoryItemRenderer r = p.getRenderer();
/* 606 */         if ((r instanceof LineAndShapeRenderer)) {
/* 607 */           ((LineAndShapeRenderer)r).setLinesVisible(this.drawLines.booleanValue());
/*     */         }
/*     */         
/*     */       }
/* 611 */       else if ((plot instanceof XYPlot)) {
/* 612 */         XYPlot p = (XYPlot)plot;
/* 613 */         XYItemRenderer r = p.getRenderer();
/* 614 */         if ((r instanceof StandardXYItemRenderer)) {
/* 615 */           ((StandardXYItemRenderer)r).setPlotLines(this.drawLines.booleanValue());
/*     */         }
/*     */       }
/*     */     }
/*     */     
/*     */ 
/* 621 */     if (this.drawShapes != null) {
/* 622 */       if ((plot instanceof CategoryPlot)) {
/* 623 */         CategoryPlot p = (CategoryPlot)plot;
/* 624 */         CategoryItemRenderer r = p.getRenderer();
/* 625 */         if ((r instanceof LineAndShapeRenderer)) {
/* 626 */           ((LineAndShapeRenderer)r).setShapesVisible(this.drawShapes.booleanValue());
/*     */         }
/*     */         
/*     */       }
/* 630 */       else if ((plot instanceof XYPlot)) {
/* 631 */         XYPlot p = (XYPlot)plot;
/* 632 */         XYItemRenderer r = p.getRenderer();
/* 633 */         if ((r instanceof StandardXYItemRenderer)) {
/* 634 */           ((StandardXYItemRenderer)r).setBaseShapesVisible(this.drawShapes.booleanValue());
/*     */         }
/*     */       }
/*     */     }
/*     */     
/*     */ 
/*     */ 
/* 641 */     if (this.colorBarAxisPropertyPanel != null) {
/* 642 */       ColorBar colorBar = null;
/* 643 */       if ((plot instanceof ContourPlot)) {
/* 644 */         ContourPlot p = (ContourPlot)plot;
/* 645 */         colorBar = p.getColorBar();
/*     */       }
/* 647 */       if (colorBar != null) {
/* 648 */         this.colorBarAxisPropertyPanel.setAxisProperties(colorBar);
/*     */       }
/*     */     }
/*     */   }
/*     */ }


/* Location:              /home/menick/Scrivania/ProcessMining/CNMining/CNMining.jar!/org/jfree/chart/editor/DefaultPlotEditor.class
 * Java compiler version: 3 (47.0)
 * JD-Core Version:       0.7.1
 */