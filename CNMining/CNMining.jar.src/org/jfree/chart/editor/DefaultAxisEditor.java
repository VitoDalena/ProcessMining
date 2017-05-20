/*     */ package org.jfree.chart.editor;
/*     */ 
/*     */ import java.awt.BorderLayout;
/*     */ import java.awt.Color;
/*     */ import java.awt.Font;
/*     */ import java.awt.Paint;
/*     */ import java.awt.event.ActionEvent;
/*     */ import java.awt.event.ActionListener;
/*     */ import java.util.ResourceBundle;
/*     */ import javax.swing.BorderFactory;
/*     */ import javax.swing.JButton;
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
/*     */ import org.jfree.ui.FontChooserPanel;
/*     */ import org.jfree.ui.FontDisplayField;
/*     */ import org.jfree.ui.PaintSample;
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
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ class DefaultAxisEditor
/*     */   extends JPanel
/*     */   implements ActionListener
/*     */ {
/*     */   private JTextField label;
/*     */   private Font labelFont;
/*     */   private PaintSample labelPaintSample;
/*     */   private JTextField labelFontField;
/*     */   private Font tickLabelFont;
/*     */   private JTextField tickLabelFontField;
/*     */   private PaintSample tickLabelPaintSample;
/*     */   private JPanel slot1;
/*     */   private JPanel slot2;
/*     */   private JCheckBox showTickLabelsCheckBox;
/*     */   private JCheckBox showTickMarksCheckBox;
/*     */   private RectangleInsets tickLabelInsets;
/*     */   private RectangleInsets labelInsets;
/*     */   private JTabbedPane otherTabs;
/* 136 */   protected static ResourceBundle localizationResources = ResourceBundleWrapper.getBundle("org.jfree.chart.editor.LocalizationBundle");
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public static DefaultAxisEditor getInstance(Axis axis)
/*     */   {
/* 151 */     if (axis != null)
/*     */     {
/*     */ 
/* 154 */       if ((axis instanceof NumberAxis)) {
/* 155 */         return new DefaultNumberAxisEditor((NumberAxis)axis);
/*     */       }
/*     */       
/* 158 */       return new DefaultAxisEditor(axis);
/*     */     }
/*     */     
/*     */ 
/* 162 */     return null;
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
/*     */   public DefaultAxisEditor(Axis axis)
/*     */   {
/* 176 */     this.labelFont = axis.getLabelFont();
/* 177 */     this.labelPaintSample = new PaintSample(axis.getLabelPaint());
/* 178 */     this.tickLabelFont = axis.getTickLabelFont();
/* 179 */     this.tickLabelPaintSample = new PaintSample(axis.getTickLabelPaint());
/*     */     
/*     */ 
/* 182 */     this.tickLabelInsets = axis.getTickLabelInsets();
/* 183 */     this.labelInsets = axis.getLabelInsets();
/*     */     
/* 185 */     setLayout(new BorderLayout());
/*     */     
/* 187 */     JPanel general = new JPanel(new BorderLayout());
/* 188 */     general.setBorder(BorderFactory.createTitledBorder(BorderFactory.createEtchedBorder(), localizationResources.getString("General")));
/*     */     
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/* 195 */     JPanel interior = new JPanel(new LCBLayout(5));
/* 196 */     interior.setBorder(BorderFactory.createEmptyBorder(0, 5, 0, 5));
/* 197 */     interior.add(new JLabel(localizationResources.getString("Label")));
/* 198 */     this.label = new JTextField(axis.getLabel());
/* 199 */     interior.add(this.label);
/* 200 */     interior.add(new JPanel());
/*     */     
/* 202 */     interior.add(new JLabel(localizationResources.getString("Font")));
/* 203 */     this.labelFontField = new FontDisplayField(this.labelFont);
/* 204 */     interior.add(this.labelFontField);
/* 205 */     JButton b = new JButton(localizationResources.getString("Select..."));
/* 206 */     b.setActionCommand("SelectLabelFont");
/* 207 */     b.addActionListener(this);
/* 208 */     interior.add(b);
/*     */     
/* 210 */     interior.add(new JLabel(localizationResources.getString("Paint")));
/* 211 */     interior.add(this.labelPaintSample);
/* 212 */     b = new JButton(localizationResources.getString("Select..."));
/* 213 */     b.setActionCommand("SelectLabelPaint");
/* 214 */     b.addActionListener(this);
/* 215 */     interior.add(b);
/*     */     
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/* 238 */     general.add(interior);
/*     */     
/* 240 */     add(general, "North");
/*     */     
/* 242 */     this.slot1 = new JPanel(new BorderLayout());
/*     */     
/* 244 */     JPanel other = new JPanel(new BorderLayout());
/* 245 */     other.setBorder(BorderFactory.createTitledBorder(BorderFactory.createEtchedBorder(), localizationResources.getString("Other")));
/*     */     
/*     */ 
/*     */ 
/* 249 */     this.otherTabs = new JTabbedPane();
/* 250 */     this.otherTabs.setBorder(BorderFactory.createEmptyBorder(0, 5, 0, 5));
/*     */     
/* 252 */     JPanel ticks = new JPanel(new LCBLayout(3));
/* 253 */     ticks.setBorder(BorderFactory.createEmptyBorder(4, 4, 4, 4));
/*     */     
/* 255 */     this.showTickLabelsCheckBox = new JCheckBox(localizationResources.getString("Show_tick_labels"), axis.isTickLabelsVisible());
/*     */     
/*     */ 
/*     */ 
/* 259 */     ticks.add(this.showTickLabelsCheckBox);
/* 260 */     ticks.add(new JPanel());
/* 261 */     ticks.add(new JPanel());
/*     */     
/* 263 */     ticks.add(new JLabel(localizationResources.getString("Tick_label_font")));
/*     */     
/*     */ 
/* 266 */     this.tickLabelFontField = new FontDisplayField(this.tickLabelFont);
/* 267 */     ticks.add(this.tickLabelFontField);
/* 268 */     b = new JButton(localizationResources.getString("Select..."));
/* 269 */     b.setActionCommand("SelectTickLabelFont");
/* 270 */     b.addActionListener(this);
/* 271 */     ticks.add(b);
/*     */     
/* 273 */     this.showTickMarksCheckBox = new JCheckBox(localizationResources.getString("Show_tick_marks"), axis.isTickMarksVisible());
/*     */     
/*     */ 
/*     */ 
/* 277 */     ticks.add(this.showTickMarksCheckBox);
/* 278 */     ticks.add(new JPanel());
/* 279 */     ticks.add(new JPanel());
/*     */     
/* 281 */     this.otherTabs.add(localizationResources.getString("Ticks"), ticks);
/*     */     
/* 283 */     other.add(this.otherTabs);
/*     */     
/* 285 */     this.slot1.add(other);
/*     */     
/* 287 */     this.slot2 = new JPanel(new BorderLayout());
/* 288 */     this.slot2.add(this.slot1, "North");
/* 289 */     add(this.slot2);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public String getLabel()
/*     */   {
/* 299 */     return this.label.getText();
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public Font getLabelFont()
/*     */   {
/* 308 */     return this.labelFont;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public Paint getLabelPaint()
/*     */   {
/* 317 */     return this.labelPaintSample.getPaint();
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public boolean isTickLabelsVisible()
/*     */   {
/* 326 */     return this.showTickLabelsCheckBox.isSelected();
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public Font getTickLabelFont()
/*     */   {
/* 335 */     return this.tickLabelFont;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public Paint getTickLabelPaint()
/*     */   {
/* 344 */     return this.tickLabelPaintSample.getPaint();
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public boolean isTickMarksVisible()
/*     */   {
/* 354 */     return this.showTickMarksCheckBox.isSelected();
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public RectangleInsets getTickLabelInsets()
/*     */   {
/* 363 */     return this.tickLabelInsets == null ? new RectangleInsets(0.0D, 0.0D, 0.0D, 0.0D) : this.tickLabelInsets;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public RectangleInsets getLabelInsets()
/*     */   {
/* 374 */     return this.labelInsets == null ? new RectangleInsets(0.0D, 0.0D, 0.0D, 0.0D) : this.labelInsets;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public JTabbedPane getOtherTabs()
/*     */   {
/* 384 */     return this.otherTabs;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public void actionPerformed(ActionEvent event)
/*     */   {
/* 394 */     String command = event.getActionCommand();
/* 395 */     if (command.equals("SelectLabelFont")) {
/* 396 */       attemptLabelFontSelection();
/*     */     }
/* 398 */     else if (command.equals("SelectLabelPaint")) {
/* 399 */       attemptModifyLabelPaint();
/*     */     }
/* 401 */     else if (command.equals("SelectTickLabelFont")) {
/* 402 */       attemptTickLabelFontSelection();
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
/*     */   private void attemptLabelFontSelection()
/*     */   {
/* 417 */     FontChooserPanel panel = new FontChooserPanel(this.labelFont);
/* 418 */     int result = JOptionPane.showConfirmDialog(this, panel, localizationResources.getString("Font_Selection"), 2, -1);
/*     */     
/*     */ 
/*     */ 
/* 422 */     if (result == 0) {
/* 423 */       this.labelFont = panel.getSelectedFont();
/* 424 */       this.labelFontField.setText(this.labelFont.getFontName() + " " + this.labelFont.getSize());
/*     */     }
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   private void attemptModifyLabelPaint()
/*     */   {
/* 436 */     Color c = JColorChooser.showDialog(this, localizationResources.getString("Label_Color"), Color.blue);
/*     */     
/*     */ 
/* 439 */     if (c != null) {
/* 440 */       this.labelPaintSample.setPaint(c);
/*     */     }
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   public void attemptTickLabelFontSelection()
/*     */   {
/* 449 */     FontChooserPanel panel = new FontChooserPanel(this.tickLabelFont);
/* 450 */     int result = JOptionPane.showConfirmDialog(this, panel, localizationResources.getString("Font_Selection"), 2, -1);
/*     */     
/*     */ 
/*     */ 
/* 454 */     if (result == 0) {
/* 455 */       this.tickLabelFont = panel.getSelectedFont();
/* 456 */       this.tickLabelFontField.setText(this.tickLabelFont.getFontName() + " " + this.tickLabelFont.getSize());
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
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public void setAxisProperties(Axis axis)
/*     */   {
/* 508 */     axis.setLabel(getLabel());
/* 509 */     axis.setLabelFont(getLabelFont());
/* 510 */     axis.setLabelPaint(getLabelPaint());
/* 511 */     axis.setTickMarksVisible(isTickMarksVisible());
/*     */     
/* 513 */     axis.setTickLabelsVisible(isTickLabelsVisible());
/* 514 */     axis.setTickLabelFont(getTickLabelFont());
/* 515 */     axis.setTickLabelPaint(getTickLabelPaint());
/* 516 */     axis.setTickLabelInsets(getTickLabelInsets());
/* 517 */     axis.setLabelInsets(getLabelInsets());
/*     */   }
/*     */ }


/* Location:              /home/menick/Scrivania/ProcessMining/CNMining/CNMining.jar!/org/jfree/chart/editor/DefaultAxisEditor.class
 * Java compiler version: 3 (47.0)
 * JD-Core Version:       0.7.1
 */