/*     */ package org.jfree.ui;
/*     */ 
/*     */ import java.awt.BorderLayout;
/*     */ import java.awt.Font;
/*     */ import java.awt.GraphicsEnvironment;
/*     */ import java.awt.GridLayout;
/*     */ import java.util.ResourceBundle;
/*     */ import javax.swing.BorderFactory;
/*     */ import javax.swing.JCheckBox;
/*     */ import javax.swing.JList;
/*     */ import javax.swing.JPanel;
/*     */ import javax.swing.JScrollPane;
/*     */ import javax.swing.ListModel;
/*     */ import org.jfree.util.ResourceBundleWrapper;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class FontChooserPanel
/*     */   extends JPanel
/*     */ {
/*  74 */   public static final String[] SIZES = { "9", "10", "11", "12", "14", "16", "18", "20", "22", "24", "28", "36", "48", "72" };
/*     */   
/*     */ 
/*     */ 
/*     */   private JList fontlist;
/*     */   
/*     */ 
/*     */   private JList sizelist;
/*     */   
/*     */ 
/*     */   private JCheckBox bold;
/*     */   
/*     */ 
/*     */   private JCheckBox italic;
/*     */   
/*     */ 
/*  90 */   protected static ResourceBundle localizationResources = ResourceBundleWrapper.getBundle("org.jfree.ui.LocalizationBundle");
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public FontChooserPanel(Font font)
/*     */   {
/* 101 */     GraphicsEnvironment g = GraphicsEnvironment.getLocalGraphicsEnvironment();
/*     */     
/* 103 */     String[] fonts = g.getAvailableFontFamilyNames();
/*     */     
/* 105 */     setLayout(new BorderLayout());
/* 106 */     JPanel right = new JPanel(new BorderLayout());
/*     */     
/* 108 */     JPanel fontPanel = new JPanel(new BorderLayout());
/* 109 */     fontPanel.setBorder(BorderFactory.createTitledBorder(BorderFactory.createEtchedBorder(), localizationResources.getString("Font")));
/*     */     
/*     */ 
/* 112 */     this.fontlist = new JList(fonts);
/* 113 */     JScrollPane fontpane = new JScrollPane(this.fontlist);
/* 114 */     fontpane.setBorder(BorderFactory.createEtchedBorder());
/* 115 */     fontPanel.add(fontpane);
/* 116 */     add(fontPanel);
/*     */     
/* 118 */     JPanel sizePanel = new JPanel(new BorderLayout());
/* 119 */     sizePanel.setBorder(BorderFactory.createTitledBorder(BorderFactory.createEtchedBorder(), localizationResources.getString("Size")));
/*     */     
/*     */ 
/* 122 */     this.sizelist = new JList(SIZES);
/* 123 */     JScrollPane sizepane = new JScrollPane(this.sizelist);
/* 124 */     sizepane.setBorder(BorderFactory.createEtchedBorder());
/* 125 */     sizePanel.add(sizepane);
/*     */     
/* 127 */     JPanel attributes = new JPanel(new GridLayout(1, 2));
/* 128 */     this.bold = new JCheckBox(localizationResources.getString("Bold"));
/* 129 */     this.italic = new JCheckBox(localizationResources.getString("Italic"));
/* 130 */     attributes.add(this.bold);
/* 131 */     attributes.add(this.italic);
/* 132 */     attributes.setBorder(BorderFactory.createTitledBorder(BorderFactory.createEtchedBorder(), localizationResources.getString("Attributes")));
/*     */     
/*     */ 
/*     */ 
/* 136 */     right.add(sizePanel, "Center");
/* 137 */     right.add(attributes, "South");
/*     */     
/* 139 */     add(right, "East");
/*     */     
/* 141 */     setSelectedFont(font);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public Font getSelectedFont()
/*     */   {
/* 150 */     return new Font(getSelectedName(), getSelectedStyle(), getSelectedSize());
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public String getSelectedName()
/*     */   {
/* 160 */     return (String)this.fontlist.getSelectedValue();
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public int getSelectedStyle()
/*     */   {
/* 169 */     if ((this.bold.isSelected()) && (this.italic.isSelected())) {
/* 170 */       return 3;
/*     */     }
/* 172 */     if (this.bold.isSelected()) {
/* 173 */       return 1;
/*     */     }
/* 175 */     if (this.italic.isSelected()) {
/* 176 */       return 2;
/*     */     }
/*     */     
/* 179 */     return 0;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public int getSelectedSize()
/*     */   {
/* 189 */     String selected = (String)this.sizelist.getSelectedValue();
/* 190 */     if (selected != null) {
/* 191 */       return Integer.parseInt(selected);
/*     */     }
/*     */     
/* 194 */     return 10;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public void setSelectedFont(Font font)
/*     */   {
/* 205 */     if (font == null) {
/* 206 */       throw new NullPointerException();
/*     */     }
/* 208 */     this.bold.setSelected(font.isBold());
/* 209 */     this.italic.setSelected(font.isItalic());
/*     */     
/* 211 */     String fontName = font.getName();
/* 212 */     ListModel model = this.fontlist.getModel();
/* 213 */     this.fontlist.clearSelection();
/* 214 */     for (int i = 0; i < model.getSize(); i++) {
/* 215 */       if (fontName.equals(model.getElementAt(i))) {
/* 216 */         this.fontlist.setSelectedIndex(i);
/* 217 */         break;
/*     */       }
/*     */     }
/*     */     
/* 221 */     String fontSize = String.valueOf(font.getSize());
/* 222 */     model = this.sizelist.getModel();
/* 223 */     this.sizelist.clearSelection();
/* 224 */     for (int i = 0; i < model.getSize(); i++) {
/* 225 */       if (fontSize.equals(model.getElementAt(i))) {
/* 226 */         this.sizelist.setSelectedIndex(i);
/* 227 */         break;
/*     */       }
/*     */     }
/*     */   }
/*     */ }


/* Location:              /home/menick/Scrivania/ProcessMining/CNMining/CNMining.jar!/org/jfree/ui/FontChooserPanel.class
 * Java compiler version: 2 (46.0)
 * JD-Core Version:       0.7.1
 */