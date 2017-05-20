/*     */ package org.jfree.chart.editor;
/*     */ 
/*     */ import java.awt.BorderLayout;
/*     */ import java.awt.Color;
/*     */ import java.awt.Paint;
/*     */ import java.awt.event.ActionEvent;
/*     */ import java.awt.event.ActionListener;
/*     */ import java.util.ResourceBundle;
/*     */ import javax.swing.BorderFactory;
/*     */ import javax.swing.JButton;
/*     */ import javax.swing.JCheckBox;
/*     */ import javax.swing.JColorChooser;
/*     */ import javax.swing.JLabel;
/*     */ import javax.swing.JPanel;
/*     */ import javax.swing.JTabbedPane;
/*     */ import javax.swing.JTextField;
/*     */ import org.jfree.chart.JFreeChart;
/*     */ import org.jfree.chart.plot.Plot;
/*     */ import org.jfree.chart.title.Title;
/*     */ import org.jfree.chart.util.ResourceBundleWrapper;
/*     */ import org.jfree.layout.LCBLayout;
/*     */ import org.jfree.ui.PaintSample;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ class DefaultChartEditor
/*     */   extends JPanel
/*     */   implements ActionListener, ChartEditor
/*     */ {
/*     */   private DefaultTitleEditor titleEditor;
/*     */   private DefaultPlotEditor plotEditor;
/*     */   private JCheckBox antialias;
/*     */   private PaintSample background;
/*  91 */   protected static ResourceBundle localizationResources = ResourceBundleWrapper.getBundle("org.jfree.chart.editor.LocalizationBundle");
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public DefaultChartEditor(JFreeChart chart)
/*     */   {
/* 102 */     setLayout(new BorderLayout());
/*     */     
/* 104 */     JPanel other = new JPanel(new BorderLayout());
/* 105 */     other.setBorder(BorderFactory.createEmptyBorder(2, 2, 2, 2));
/*     */     
/* 107 */     JPanel general = new JPanel(new BorderLayout());
/* 108 */     general.setBorder(BorderFactory.createTitledBorder(BorderFactory.createEtchedBorder(), localizationResources.getString("General")));
/*     */     
/*     */ 
/*     */ 
/* 112 */     JPanel interior = new JPanel(new LCBLayout(6));
/* 113 */     interior.setBorder(BorderFactory.createEmptyBorder(0, 5, 0, 5));
/*     */     
/* 115 */     this.antialias = new JCheckBox(localizationResources.getString("Draw_anti-aliased"));
/*     */     
/* 117 */     this.antialias.setSelected(chart.getAntiAlias());
/* 118 */     interior.add(this.antialias);
/* 119 */     interior.add(new JLabel(""));
/* 120 */     interior.add(new JLabel(""));
/* 121 */     interior.add(new JLabel(localizationResources.getString("Background_paint")));
/*     */     
/* 123 */     this.background = new PaintSample(chart.getBackgroundPaint());
/* 124 */     interior.add(this.background);
/* 125 */     JButton button = new JButton(localizationResources.getString("Select..."));
/*     */     
/* 127 */     button.setActionCommand("BackgroundPaint");
/* 128 */     button.addActionListener(this);
/* 129 */     interior.add(button);
/*     */     
/* 131 */     interior.add(new JLabel(localizationResources.getString("Series_Paint")));
/*     */     
/* 133 */     JTextField info = new JTextField(localizationResources.getString("No_editor_implemented"));
/*     */     
/* 135 */     info.setEnabled(false);
/* 136 */     interior.add(info);
/* 137 */     button = new JButton(localizationResources.getString("Edit..."));
/* 138 */     button.setEnabled(false);
/* 139 */     interior.add(button);
/*     */     
/* 141 */     interior.add(new JLabel(localizationResources.getString("Series_Stroke")));
/*     */     
/* 143 */     info = new JTextField(localizationResources.getString("No_editor_implemented"));
/*     */     
/* 145 */     info.setEnabled(false);
/* 146 */     interior.add(info);
/* 147 */     button = new JButton(localizationResources.getString("Edit..."));
/* 148 */     button.setEnabled(false);
/* 149 */     interior.add(button);
/*     */     
/* 151 */     interior.add(new JLabel(localizationResources.getString("Series_Outline_Paint")));
/*     */     
/* 153 */     info = new JTextField(localizationResources.getString("No_editor_implemented"));
/*     */     
/* 155 */     info.setEnabled(false);
/* 156 */     interior.add(info);
/* 157 */     button = new JButton(localizationResources.getString("Edit..."));
/* 158 */     button.setEnabled(false);
/* 159 */     interior.add(button);
/*     */     
/* 161 */     interior.add(new JLabel(localizationResources.getString("Series_Outline_Stroke")));
/*     */     
/* 163 */     info = new JTextField(localizationResources.getString("No_editor_implemented"));
/*     */     
/* 165 */     info.setEnabled(false);
/* 166 */     interior.add(info);
/* 167 */     button = new JButton(localizationResources.getString("Edit..."));
/* 168 */     button.setEnabled(false);
/* 169 */     interior.add(button);
/*     */     
/* 171 */     general.add(interior, "North");
/* 172 */     other.add(general, "North");
/*     */     
/* 174 */     JPanel parts = new JPanel(new BorderLayout());
/*     */     
/* 176 */     Title title = chart.getTitle();
/* 177 */     Plot plot = chart.getPlot();
/*     */     
/* 179 */     JTabbedPane tabs = new JTabbedPane();
/*     */     
/* 181 */     this.titleEditor = new DefaultTitleEditor(title);
/* 182 */     this.titleEditor.setBorder(BorderFactory.createEmptyBorder(2, 2, 2, 2));
/* 183 */     tabs.addTab(localizationResources.getString("Title"), this.titleEditor);
/*     */     
/* 185 */     this.plotEditor = new DefaultPlotEditor(plot);
/* 186 */     this.plotEditor.setBorder(BorderFactory.createEmptyBorder(2, 2, 2, 2));
/* 187 */     tabs.addTab(localizationResources.getString("Plot"), this.plotEditor);
/*     */     
/* 189 */     tabs.add(localizationResources.getString("Other"), other);
/* 190 */     parts.add(tabs, "North");
/* 191 */     add(parts);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public DefaultTitleEditor getTitleEditor()
/*     */   {
/* 200 */     return this.titleEditor;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public DefaultPlotEditor getPlotEditor()
/*     */   {
/* 209 */     return this.plotEditor;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public boolean getAntiAlias()
/*     */   {
/* 218 */     return this.antialias.isSelected();
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public Paint getBackgroundPaint()
/*     */   {
/* 227 */     return this.background.getPaint();
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public void actionPerformed(ActionEvent event)
/*     */   {
/* 236 */     String command = event.getActionCommand();
/* 237 */     if (command.equals("BackgroundPaint")) {
/* 238 */       attemptModifyBackgroundPaint();
/*     */     }
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   private void attemptModifyBackgroundPaint()
/*     */   {
/* 249 */     Color c = JColorChooser.showDialog(this, localizationResources.getString("Background_Color"), Color.blue);
/*     */     
/* 251 */     if (c != null) {
/* 252 */       this.background.setPaint(c);
/*     */     }
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public void updateChart(JFreeChart chart)
/*     */   {
/* 264 */     this.titleEditor.setTitleProperties(chart);
/* 265 */     this.plotEditor.updatePlotProperties(chart.getPlot());
/*     */     
/* 267 */     chart.setAntiAlias(getAntiAlias());
/* 268 */     chart.setBackgroundPaint(getBackgroundPaint());
/*     */   }
/*     */ }


/* Location:              /home/menick/Scrivania/ProcessMining/CNMining/CNMining.jar!/org/jfree/chart/editor/DefaultChartEditor.class
 * Java compiler version: 3 (47.0)
 * JD-Core Version:       0.7.1
 */