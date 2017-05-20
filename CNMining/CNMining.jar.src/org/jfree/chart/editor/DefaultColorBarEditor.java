/*     */ package org.jfree.chart.editor;
/*     */ 
/*     */ import java.awt.event.ActionEvent;
/*     */ import java.util.ResourceBundle;
/*     */ import javax.swing.BorderFactory;
/*     */ import javax.swing.JButton;
/*     */ import javax.swing.JCheckBox;
/*     */ import javax.swing.JLabel;
/*     */ import javax.swing.JOptionPane;
/*     */ import javax.swing.JPanel;
/*     */ import javax.swing.JTabbedPane;
/*     */ import org.jfree.chart.axis.ColorBar;
/*     */ import org.jfree.chart.axis.NumberAxis;
/*     */ import org.jfree.chart.plot.ColorPalette;
/*     */ import org.jfree.chart.plot.GreyPalette;
/*     */ import org.jfree.chart.plot.RainbowPalette;
/*     */ import org.jfree.chart.util.ResourceBundleWrapper;
/*     */ import org.jfree.layout.LCBLayout;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ class DefaultColorBarEditor
/*     */   extends DefaultNumberAxisEditor
/*     */ {
/*     */   private JCheckBox invertPaletteCheckBox;
/*  82 */   private boolean invertPalette = false;
/*     */   
/*     */ 
/*     */   private JCheckBox stepPaletteCheckBox;
/*     */   
/*     */ 
/*  88 */   private boolean stepPalette = false;
/*     */   
/*     */ 
/*     */   private PaletteSample currentPalette;
/*     */   
/*     */ 
/*     */   private PaletteSample[] availablePaletteSamples;
/*     */   
/*     */ 
/*  97 */   protected static ResourceBundle localizationResources = ResourceBundleWrapper.getBundle("org.jfree.chart.editor.LocalizationBundle");
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public DefaultColorBarEditor(ColorBar colorBar)
/*     */   {
/* 107 */     super((NumberAxis)colorBar.getAxis());
/* 108 */     this.invertPalette = colorBar.getColorPalette().isInverse();
/* 109 */     this.stepPalette = colorBar.getColorPalette().isStepped();
/* 110 */     this.currentPalette = new PaletteSample(colorBar.getColorPalette());
/* 111 */     this.availablePaletteSamples = new PaletteSample[2];
/* 112 */     this.availablePaletteSamples[0] = new PaletteSample(new RainbowPalette());
/*     */     
/* 114 */     this.availablePaletteSamples[1] = new PaletteSample(new GreyPalette());
/*     */     
/*     */ 
/* 117 */     JTabbedPane other = getOtherTabs();
/*     */     
/* 119 */     JPanel palettePanel = new JPanel(new LCBLayout(4));
/* 120 */     palettePanel.setBorder(BorderFactory.createEmptyBorder(4, 4, 4, 4));
/*     */     
/* 122 */     palettePanel.add(new JPanel());
/* 123 */     this.invertPaletteCheckBox = new JCheckBox(localizationResources.getString("Invert_Palette"), this.invertPalette);
/*     */     
/*     */ 
/*     */ 
/* 127 */     this.invertPaletteCheckBox.setActionCommand("invertPalette");
/* 128 */     this.invertPaletteCheckBox.addActionListener(this);
/* 129 */     palettePanel.add(this.invertPaletteCheckBox);
/* 130 */     palettePanel.add(new JPanel());
/*     */     
/* 132 */     palettePanel.add(new JPanel());
/* 133 */     this.stepPaletteCheckBox = new JCheckBox(localizationResources.getString("Step_Palette"), this.stepPalette);
/*     */     
/*     */ 
/*     */ 
/* 137 */     this.stepPaletteCheckBox.setActionCommand("stepPalette");
/* 138 */     this.stepPaletteCheckBox.addActionListener(this);
/* 139 */     palettePanel.add(this.stepPaletteCheckBox);
/* 140 */     palettePanel.add(new JPanel());
/*     */     
/* 142 */     palettePanel.add(new JLabel(localizationResources.getString("Palette")));
/*     */     
/*     */ 
/* 145 */     JButton button = new JButton(localizationResources.getString("Set_palette..."));
/*     */     
/* 147 */     button.setActionCommand("PaletteChoice");
/* 148 */     button.addActionListener(this);
/* 149 */     palettePanel.add(this.currentPalette);
/* 150 */     palettePanel.add(button);
/*     */     
/* 152 */     other.add(localizationResources.getString("Palette"), palettePanel);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public void actionPerformed(ActionEvent event)
/*     */   {
/* 162 */     String command = event.getActionCommand();
/* 163 */     if (command.equals("PaletteChoice")) {
/* 164 */       attemptPaletteSelection();
/*     */     }
/* 166 */     else if (command.equals("invertPalette")) {
/* 167 */       this.invertPalette = this.invertPaletteCheckBox.isSelected();
/*     */     }
/* 169 */     else if (command.equals("stepPalette")) {
/* 170 */       this.stepPalette = this.stepPaletteCheckBox.isSelected();
/*     */     }
/*     */     else {
/* 173 */       super.actionPerformed(event);
/*     */     }
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */   private void attemptPaletteSelection()
/*     */   {
/* 181 */     PaletteChooserPanel panel = new PaletteChooserPanel(null, this.availablePaletteSamples);
/*     */     
/* 183 */     int result = JOptionPane.showConfirmDialog(this, panel, localizationResources.getString("Palette_Selection"), 2, -1);
/*     */     
/*     */ 
/*     */ 
/*     */ 
/* 188 */     if (result == 0) {
/* 189 */       double zmin = this.currentPalette.getPalette().getMinZ();
/* 190 */       double zmax = this.currentPalette.getPalette().getMaxZ();
/* 191 */       this.currentPalette.setPalette(panel.getSelectedPalette());
/* 192 */       this.currentPalette.getPalette().setMinZ(zmin);
/* 193 */       this.currentPalette.getPalette().setMaxZ(zmax);
/*     */     }
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public void setAxisProperties(ColorBar colorBar)
/*     */   {
/* 204 */     super.setAxisProperties(colorBar.getAxis());
/* 205 */     colorBar.setColorPalette(this.currentPalette.getPalette());
/* 206 */     colorBar.getColorPalette().setInverse(this.invertPalette);
/* 207 */     colorBar.getColorPalette().setStepped(this.stepPalette);
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
/*     */   public static DefaultColorBarEditor getInstance(ColorBar colorBar)
/*     */   {
/* 220 */     if (colorBar != null) {
/* 221 */       return new DefaultColorBarEditor(colorBar);
/*     */     }
/*     */     
/* 224 */     return null;
/*     */   }
/*     */ }


/* Location:              /home/menick/Scrivania/ProcessMining/CNMining/CNMining.jar!/org/jfree/chart/editor/DefaultColorBarEditor.class
 * Java compiler version: 3 (47.0)
 * JD-Core Version:       0.7.1
 */