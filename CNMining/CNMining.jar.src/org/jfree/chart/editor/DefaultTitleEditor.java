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
/*     */ import javax.swing.JTextField;
/*     */ import org.jfree.chart.JFreeChart;
/*     */ import org.jfree.chart.title.TextTitle;
/*     */ import org.jfree.chart.title.Title;
/*     */ import org.jfree.chart.util.ResourceBundleWrapper;
/*     */ import org.jfree.layout.LCBLayout;
/*     */ import org.jfree.ui.FontChooserPanel;
/*     */ import org.jfree.ui.FontDisplayField;
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
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ class DefaultTitleEditor
/*     */   extends JPanel
/*     */   implements ActionListener
/*     */ {
/*     */   private boolean showTitle;
/*     */   private JCheckBox showTitleCheckBox;
/*     */   private JTextField titleField;
/*     */   private Font titleFont;
/*     */   private JTextField fontfield;
/*     */   private JButton selectFontButton;
/*     */   private PaintSample titlePaint;
/*     */   private JButton selectPaintButton;
/* 102 */   protected static ResourceBundle localizationResources = ResourceBundleWrapper.getBundle("org.jfree.chart.editor.LocalizationBundle");
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public DefaultTitleEditor(Title title)
/*     */   {
/* 114 */     TextTitle t = title != null ? (TextTitle)title : new TextTitle(localizationResources.getString("Title"));
/*     */     
/* 116 */     this.showTitle = (title != null);
/* 117 */     this.titleFont = t.getFont();
/* 118 */     this.titleField = new JTextField(t.getText());
/* 119 */     this.titlePaint = new PaintSample(t.getPaint());
/*     */     
/* 121 */     setLayout(new BorderLayout());
/*     */     
/* 123 */     JPanel general = new JPanel(new BorderLayout());
/* 124 */     general.setBorder(BorderFactory.createTitledBorder(BorderFactory.createEtchedBorder(), localizationResources.getString("General")));
/*     */     
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/* 131 */     JPanel interior = new JPanel(new LCBLayout(4));
/* 132 */     interior.setBorder(BorderFactory.createEmptyBorder(0, 5, 0, 5));
/*     */     
/* 134 */     interior.add(new JLabel(localizationResources.getString("Show_Title")));
/* 135 */     this.showTitleCheckBox = new JCheckBox();
/* 136 */     this.showTitleCheckBox.setSelected(this.showTitle);
/* 137 */     this.showTitleCheckBox.setActionCommand("ShowTitle");
/* 138 */     this.showTitleCheckBox.addActionListener(this);
/* 139 */     interior.add(new JPanel());
/* 140 */     interior.add(this.showTitleCheckBox);
/*     */     
/* 142 */     JLabel titleLabel = new JLabel(localizationResources.getString("Text"));
/* 143 */     interior.add(titleLabel);
/* 144 */     interior.add(this.titleField);
/* 145 */     interior.add(new JPanel());
/*     */     
/* 147 */     JLabel fontLabel = new JLabel(localizationResources.getString("Font"));
/* 148 */     this.fontfield = new FontDisplayField(this.titleFont);
/* 149 */     this.selectFontButton = new JButton(localizationResources.getString("Select..."));
/*     */     
/*     */ 
/* 152 */     this.selectFontButton.setActionCommand("SelectFont");
/* 153 */     this.selectFontButton.addActionListener(this);
/* 154 */     interior.add(fontLabel);
/* 155 */     interior.add(this.fontfield);
/* 156 */     interior.add(this.selectFontButton);
/*     */     
/* 158 */     JLabel colorLabel = new JLabel(localizationResources.getString("Color"));
/*     */     
/*     */ 
/* 161 */     this.selectPaintButton = new JButton(localizationResources.getString("Select..."));
/*     */     
/*     */ 
/* 164 */     this.selectPaintButton.setActionCommand("SelectPaint");
/* 165 */     this.selectPaintButton.addActionListener(this);
/* 166 */     interior.add(colorLabel);
/* 167 */     interior.add(this.titlePaint);
/* 168 */     interior.add(this.selectPaintButton);
/*     */     
/* 170 */     enableOrDisableControls();
/*     */     
/* 172 */     general.add(interior);
/* 173 */     add(general, "North");
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public String getTitleText()
/*     */   {
/* 182 */     return this.titleField.getText();
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public Font getTitleFont()
/*     */   {
/* 191 */     return this.titleFont;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public Paint getTitlePaint()
/*     */   {
/* 200 */     return this.titlePaint.getPaint();
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public void actionPerformed(ActionEvent event)
/*     */   {
/* 211 */     String command = event.getActionCommand();
/*     */     
/* 213 */     if (command.equals("SelectFont")) {
/* 214 */       attemptFontSelection();
/*     */     }
/* 216 */     else if (command.equals("SelectPaint")) {
/* 217 */       attemptPaintSelection();
/*     */     }
/* 219 */     else if (command.equals("ShowTitle")) {
/* 220 */       attemptModifyShowTitle();
/*     */     }
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   public void attemptFontSelection()
/*     */   {
/* 229 */     FontChooserPanel panel = new FontChooserPanel(this.titleFont);
/* 230 */     int result = JOptionPane.showConfirmDialog(this, panel, localizationResources.getString("Font_Selection"), 2, -1);
/*     */     
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/* 236 */     if (result == 0) {
/* 237 */       this.titleFont = panel.getSelectedFont();
/* 238 */       this.fontfield.setText(this.titleFont.getFontName() + " " + this.titleFont.getSize());
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
/*     */   public void attemptPaintSelection()
/*     */   {
/* 251 */     Paint p = this.titlePaint.getPaint();
/* 252 */     Color defaultColor = (p instanceof Color) ? (Color)p : Color.blue;
/* 253 */     Color c = JColorChooser.showDialog(this, localizationResources.getString("Title_Color"), defaultColor);
/*     */     
/*     */ 
/* 256 */     if (c != null) {
/* 257 */       this.titlePaint.setPaint(c);
/*     */     }
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   private void attemptModifyShowTitle()
/*     */   {
/* 266 */     this.showTitle = this.showTitleCheckBox.isSelected();
/* 267 */     enableOrDisableControls();
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   private void enableOrDisableControls()
/*     */   {
/* 275 */     boolean enabled = this.showTitle == true;
/* 276 */     this.titleField.setEnabled(enabled);
/* 277 */     this.selectFontButton.setEnabled(enabled);
/* 278 */     this.selectPaintButton.setEnabled(enabled);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public void setTitleProperties(JFreeChart chart)
/*     */   {
/* 288 */     if (this.showTitle) {
/* 289 */       TextTitle title = chart.getTitle();
/* 290 */       if (title == null) {
/* 291 */         title = new TextTitle();
/* 292 */         chart.setTitle(title);
/*     */       }
/* 294 */       title.setText(getTitleText());
/* 295 */       title.setFont(getTitleFont());
/* 296 */       title.setPaint(getTitlePaint());
/*     */     }
/*     */     else {
/* 299 */       chart.setTitle((TextTitle)null);
/*     */     }
/*     */   }
/*     */ }


/* Location:              /home/menick/Scrivania/ProcessMining/CNMining/CNMining.jar!/org/jfree/chart/editor/DefaultTitleEditor.class
 * Java compiler version: 3 (47.0)
 * JD-Core Version:       0.7.1
 */