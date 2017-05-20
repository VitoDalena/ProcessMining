/*     */ package org.jfree.chart.editor;
/*     */ 
/*     */ import java.awt.BasicStroke;
/*     */ import java.awt.Color;
/*     */ import java.awt.event.ActionEvent;
/*     */ import java.awt.event.FocusEvent;
/*     */ import java.awt.event.FocusListener;
/*     */ import java.util.ResourceBundle;
/*     */ import javax.swing.BorderFactory;
/*     */ import javax.swing.JCheckBox;
/*     */ import javax.swing.JColorChooser;
/*     */ import javax.swing.JLabel;
/*     */ import javax.swing.JOptionPane;
/*     */ import javax.swing.JPanel;
/*     */ import javax.swing.JTabbedPane;
/*     */ import javax.swing.JTextField;
/*     */ import org.jfree.chart.axis.Axis;
/*     */ import org.jfree.chart.axis.NumberAxis;
/*     */ import org.jfree.chart.util.ResourceBundleWrapper;
/*     */ import org.jfree.layout.LCBLayout;
/*     */ import org.jfree.ui.PaintSample;
/*     */ import org.jfree.ui.StrokeChooserPanel;
/*     */ import org.jfree.ui.StrokeSample;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ class DefaultNumberAxisEditor
/*     */   extends DefaultAxisEditor
/*     */   implements FocusListener
/*     */ {
/*     */   private boolean autoRange;
/*     */   private double minimumValue;
/*     */   private double maximumValue;
/*     */   private JCheckBox autoRangeCheckBox;
/*     */   private JTextField minimumRangeValue;
/*     */   private JTextField maximumRangeValue;
/*     */   private PaintSample gridPaintSample;
/*     */   private StrokeSample gridStrokeSample;
/*     */   private StrokeSample[] availableStrokeSamples;
/* 110 */   protected static ResourceBundle localizationResources = ResourceBundleWrapper.getBundle("org.jfree.chart.editor.LocalizationBundle");
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public DefaultNumberAxisEditor(NumberAxis axis)
/*     */   {
/* 121 */     super(axis);
/*     */     
/* 123 */     this.autoRange = axis.isAutoRange();
/* 124 */     this.minimumValue = axis.getLowerBound();
/* 125 */     this.maximumValue = axis.getUpperBound();
/*     */     
/* 127 */     this.gridPaintSample = new PaintSample(Color.blue);
/* 128 */     this.gridStrokeSample = new StrokeSample(new BasicStroke(1.0F));
/*     */     
/* 130 */     this.availableStrokeSamples = new StrokeSample[3];
/* 131 */     this.availableStrokeSamples[0] = new StrokeSample(new BasicStroke(1.0F));
/*     */     
/* 133 */     this.availableStrokeSamples[1] = new StrokeSample(new BasicStroke(2.0F));
/*     */     
/* 135 */     this.availableStrokeSamples[2] = new StrokeSample(new BasicStroke(3.0F));
/*     */     
/*     */ 
/* 138 */     JTabbedPane other = getOtherTabs();
/*     */     
/* 140 */     JPanel range = new JPanel(new LCBLayout(3));
/* 141 */     range.setBorder(BorderFactory.createEmptyBorder(4, 4, 4, 4));
/*     */     
/* 143 */     range.add(new JPanel());
/* 144 */     this.autoRangeCheckBox = new JCheckBox(localizationResources.getString("Auto-adjust_range"), this.autoRange);
/*     */     
/* 146 */     this.autoRangeCheckBox.setActionCommand("AutoRangeOnOff");
/* 147 */     this.autoRangeCheckBox.addActionListener(this);
/* 148 */     range.add(this.autoRangeCheckBox);
/* 149 */     range.add(new JPanel());
/*     */     
/* 151 */     range.add(new JLabel(localizationResources.getString("Minimum_range_value")));
/*     */     
/* 153 */     this.minimumRangeValue = new JTextField(Double.toString(this.minimumValue));
/*     */     
/* 155 */     this.minimumRangeValue.setEnabled(!this.autoRange);
/* 156 */     this.minimumRangeValue.setActionCommand("MinimumRange");
/* 157 */     this.minimumRangeValue.addActionListener(this);
/* 158 */     this.minimumRangeValue.addFocusListener(this);
/* 159 */     range.add(this.minimumRangeValue);
/* 160 */     range.add(new JPanel());
/*     */     
/* 162 */     range.add(new JLabel(localizationResources.getString("Maximum_range_value")));
/*     */     
/* 164 */     this.maximumRangeValue = new JTextField(Double.toString(this.maximumValue));
/*     */     
/* 166 */     this.maximumRangeValue.setEnabled(!this.autoRange);
/* 167 */     this.maximumRangeValue.setActionCommand("MaximumRange");
/* 168 */     this.maximumRangeValue.addActionListener(this);
/* 169 */     this.maximumRangeValue.addFocusListener(this);
/* 170 */     range.add(this.maximumRangeValue);
/* 171 */     range.add(new JPanel());
/*     */     
/* 173 */     other.add(localizationResources.getString("Range"), range);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public boolean isAutoRange()
/*     */   {
/* 183 */     return this.autoRange;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public double getMinimumValue()
/*     */   {
/* 192 */     return this.minimumValue;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public double getMaximumValue()
/*     */   {
/* 201 */     return this.maximumValue;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   public void actionPerformed(ActionEvent event)
/*     */   {
/* 209 */     String command = event.getActionCommand();
/* 210 */     if (command.equals("GridStroke")) {
/* 211 */       attemptGridStrokeSelection();
/*     */     }
/* 213 */     else if (command.equals("GridPaint")) {
/* 214 */       attemptGridPaintSelection();
/*     */     }
/* 216 */     else if (command.equals("AutoRangeOnOff")) {
/* 217 */       toggleAutoRange();
/*     */     }
/* 219 */     else if (command.equals("MinimumRange")) {
/* 220 */       validateMinimum();
/*     */     }
/* 222 */     else if (command.equals("MaximumRange")) {
/* 223 */       validateMaximum();
/*     */     }
/*     */     else
/*     */     {
/* 227 */       super.actionPerformed(event);
/*     */     }
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */   private void attemptGridStrokeSelection()
/*     */   {
/* 235 */     StrokeChooserPanel panel = new StrokeChooserPanel(this.gridStrokeSample, this.availableStrokeSamples);
/*     */     
/* 237 */     int result = JOptionPane.showConfirmDialog(this, panel, localizationResources.getString("Stroke_Selection"), 2, -1);
/*     */     
/*     */ 
/*     */ 
/* 241 */     if (result == 0) {
/* 242 */       this.gridStrokeSample.setStroke(panel.getSelectedStroke());
/*     */     }
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   private void attemptGridPaintSelection()
/*     */   {
/* 251 */     Color c = JColorChooser.showDialog(this, localizationResources.getString("Grid_Color"), Color.blue);
/*     */     
/* 253 */     if (c != null) {
/* 254 */       this.gridPaintSample.setPaint(c);
/*     */     }
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public void focusGained(FocusEvent event) {}
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public void focusLost(FocusEvent event)
/*     */   {
/* 273 */     if (event.getSource() == this.minimumRangeValue) {
/* 274 */       validateMinimum();
/*     */     }
/* 276 */     else if (event.getSource() == this.maximumRangeValue) {
/* 277 */       validateMaximum();
/*     */     }
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */   public void toggleAutoRange()
/*     */   {
/* 285 */     this.autoRange = this.autoRangeCheckBox.isSelected();
/* 286 */     if (this.autoRange) {
/* 287 */       this.minimumRangeValue.setText(Double.toString(this.minimumValue));
/* 288 */       this.minimumRangeValue.setEnabled(false);
/* 289 */       this.maximumRangeValue.setText(Double.toString(this.maximumValue));
/* 290 */       this.maximumRangeValue.setEnabled(false);
/*     */     }
/*     */     else {
/* 293 */       this.minimumRangeValue.setEnabled(true);
/* 294 */       this.maximumRangeValue.setEnabled(true);
/*     */     }
/*     */   }
/*     */   
/*     */ 
/*     */   public void validateMinimum()
/*     */   {
/*     */     double newMin;
/*     */     try
/*     */     {
/* 304 */       newMin = Double.parseDouble(this.minimumRangeValue.getText());
/* 305 */       if (newMin >= this.maximumValue) {
/* 306 */         newMin = this.minimumValue;
/*     */       }
/*     */     }
/*     */     catch (NumberFormatException e) {
/* 310 */       newMin = this.minimumValue;
/*     */     }
/*     */     
/* 313 */     this.minimumValue = newMin;
/* 314 */     this.minimumRangeValue.setText(Double.toString(this.minimumValue));
/*     */   }
/*     */   
/*     */ 
/*     */   public void validateMaximum()
/*     */   {
/*     */     double newMax;
/*     */     try
/*     */     {
/* 323 */       newMax = Double.parseDouble(this.maximumRangeValue.getText());
/* 324 */       if (newMax <= this.minimumValue) {
/* 325 */         newMax = this.maximumValue;
/*     */       }
/*     */     }
/*     */     catch (NumberFormatException e) {
/* 329 */       newMax = this.maximumValue;
/*     */     }
/*     */     
/* 332 */     this.maximumValue = newMax;
/* 333 */     this.maximumRangeValue.setText(Double.toString(this.maximumValue));
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public void setAxisProperties(Axis axis)
/*     */   {
/* 343 */     super.setAxisProperties(axis);
/* 344 */     NumberAxis numberAxis = (NumberAxis)axis;
/* 345 */     numberAxis.setAutoRange(this.autoRange);
/* 346 */     if (!this.autoRange) {
/* 347 */       numberAxis.setRange(this.minimumValue, this.maximumValue);
/*     */     }
/*     */   }
/*     */ }


/* Location:              /home/menick/Scrivania/ProcessMining/CNMining/CNMining.jar!/org/jfree/chart/editor/DefaultNumberAxisEditor.class
 * Java compiler version: 3 (47.0)
 * JD-Core Version:       0.7.1
 */