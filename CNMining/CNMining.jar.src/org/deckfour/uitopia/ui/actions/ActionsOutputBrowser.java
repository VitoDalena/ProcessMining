/*     */ package org.deckfour.uitopia.ui.actions;
/*     */ 
/*     */ import com.fluxicon.slickerbox.factory.SlickerDecorator;
/*     */ import com.fluxicon.slickerbox.util.ColorUtils;
/*     */ import com.fluxicon.slickerbox.util.GraphicsUtilities;
/*     */ import java.awt.AlphaComposite;
/*     */ import java.awt.Color;
/*     */ import java.awt.Dimension;
/*     */ import java.awt.Font;
/*     */ import java.awt.GradientPaint;
/*     */ import java.awt.Graphics;
/*     */ import java.awt.Graphics2D;
/*     */ import java.awt.RenderingHints;
/*     */ import java.awt.Shape;
/*     */ import java.awt.geom.RoundRectangle2D.Float;
/*     */ import java.awt.image.BufferedImage;
/*     */ import java.util.ArrayList;
/*     */ import java.util.List;
/*     */ import javax.swing.BorderFactory;
/*     */ import javax.swing.Box;
/*     */ import javax.swing.BoxLayout;
/*     */ import javax.swing.JLabel;
/*     */ import javax.swing.JPanel;
/*     */ import javax.swing.JScrollBar;
/*     */ import javax.swing.JScrollPane;
/*     */ import javax.swing.JViewport;
/*     */ import org.deckfour.uitopia.api.model.Parameter;
/*     */ import org.deckfour.uitopia.api.model.ResourceType;
/*     */ import org.deckfour.uitopia.ui.UITopiaController;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class ActionsOutputBrowser
/*     */   extends JPanel
/*     */   implements ParameterFieldListener
/*     */ {
/*     */   private static final long serialVersionUID = 8012362934152847574L;
/*     */   
/*     */   public static enum Mode
/*     */   {
/*  74 */     CONSTRAINTS,  PARAMS;
/*     */     
/*     */     private Mode() {} }
/*  77 */   private static final Color HEADER_COLOR = new Color(30, 30, 30);
/*  78 */   private static final Color BODY_COLOR = new Color(120, 120, 120);
/*  79 */   private static final Color TITLE_COLOR = new Color(160, 160, 160);
/*     */   
/*     */   private UITopiaController controller;
/*     */   private ActionsView parent;
/*     */   private List<ParameterField> fields;
/*     */   private Mode mode;
/*     */   private JScrollPane scrollPane;
/*     */   
/*     */   public ActionsOutputBrowser(ActionsView parent)
/*     */   {
/*  89 */     this.parent = parent;
/*  90 */     this.controller = parent.getController();
/*  91 */     this.fields = new ArrayList();
/*  92 */     this.mode = Mode.CONSTRAINTS;
/*  93 */     setupUI();
/*  94 */     reset();
/*     */   }
/*     */   
/*     */   public boolean isConstraintsMode() {
/*  98 */     return this.mode.equals(Mode.CONSTRAINTS);
/*     */   }
/*     */   
/*     */   private void setupUI() {
/* 102 */     setLayout(new BoxLayout(this, 1));
/* 103 */     setOpaque(false);
/* 104 */     setBorder(BorderFactory.createEmptyBorder(45, 10, 45, 10));
/* 105 */     setMinimumSize(new Dimension(280, 500));
/* 106 */     setMaximumSize(new Dimension(500, 2000));
/* 107 */     setPreferredSize(new Dimension(450, 1000));
/* 108 */     JLabel header = new JLabel("Output");
/* 109 */     header.setOpaque(false);
/* 110 */     header.setForeground(TITLE_COLOR);
/* 111 */     header.setFont(header.getFont().deriveFont(18.0F).deriveFont(1));
/* 112 */     header.setAlignmentX(0.5F);
/* 113 */     this.scrollPane = new JScrollPane();
/* 114 */     this.scrollPane.setOpaque(false);
/* 115 */     this.scrollPane.getViewport().setOpaque(false);
/* 116 */     this.scrollPane.setBorder(BorderFactory.createEmptyBorder());
/* 117 */     this.scrollPane.setViewportBorder(BorderFactory.createEmptyBorder());
/* 118 */     this.scrollPane.setHorizontalScrollBarPolicy(31);
/*     */     
/* 120 */     this.scrollPane.setVerticalScrollBarPolicy(20);
/*     */     
/* 122 */     SlickerDecorator.instance().decorate(this.scrollPane.getVerticalScrollBar(), new Color(0, 0, 0, 0), new Color(40, 40, 40), new Color(80, 80, 80));
/*     */     
/*     */ 
/* 125 */     this.scrollPane.getVerticalScrollBar().setOpaque(false);
/* 126 */     add(header);
/* 127 */     add(Box.createVerticalStrut(25));
/* 128 */     add(this.scrollPane);
/* 129 */     reset();
/*     */   }
/*     */   
/*     */   public void setParameters(List<Parameter> parameters) {
/* 133 */     this.mode = Mode.PARAMS;
/* 134 */     this.fields.clear();
/* 135 */     for (int i = 0; i < parameters.size(); i++) {
/* 136 */       Parameter param = (Parameter)parameters.get(i);
/*     */       
/* 138 */       ParameterField field = new ParameterField(ParameterField.Type.OUTPUT, param, this.controller, this);
/*     */       
/* 140 */       this.fields.add(field);
/*     */     }
/* 142 */     updateFields();
/*     */   }
/*     */   
/*     */   public void reset() {
/* 146 */     this.mode = Mode.CONSTRAINTS;
/* 147 */     this.fields.clear();
/* 148 */     updateConstraintsList();
/*     */   }
/*     */   
/*     */   private void updateConstraintsList() {
/* 152 */     assert (this.mode.equals(Mode.CONSTRAINTS));
/* 153 */     List<ResourceType> types = getCurrentTypes();
/* 154 */     this.fields.clear();
/* 155 */     for (int i = 0; i < types.size(); i++) {
/* 156 */       ParameterField field = new ParameterField(ParameterField.Type.OUTPUT, this.controller, this);
/*     */       
/* 158 */       field.setResourceType((ResourceType)types.get(i));
/* 159 */       this.fields.add(field);
/*     */     }
/*     */     
/* 162 */     ParameterField field = new ParameterField(ParameterField.Type.OUTPUT, this.controller, this);
/*     */     
/* 164 */     this.fields.add(field);
/* 165 */     updateFields();
/*     */   }
/*     */   
/*     */   public List<ResourceType> getCurrentTypes() {
/* 169 */     ArrayList<ResourceType> types = new ArrayList();
/* 170 */     for (ParameterField field : this.fields) {
/* 171 */       ResourceType type = field.getResourceType();
/* 172 */       if (type != null) {
/* 173 */         types.add(type);
/*     */       }
/*     */     }
/* 176 */     return types;
/*     */   }
/*     */   
/*     */   public void changed(ParameterField field) {
/* 180 */     assert (this.mode.equals(Mode.CONSTRAINTS));
/* 181 */     updateConstraintsList();
/* 182 */     this.parent.outputConstraintsUpdated();
/*     */   }
/*     */   
/*     */   public void removed(ParameterField field) {
/* 186 */     assert (this.mode.equals(Mode.CONSTRAINTS));
/* 187 */     this.fields.remove(field);
/* 188 */     updateConstraintsList();
/* 189 */     this.parent.outputConstraintsUpdated();
/*     */   }
/*     */   
/*     */   private void updateFields()
/*     */   {
/* 194 */     JPanel enclosure = new JPanel();
/* 195 */     enclosure.setLayout(new BoxLayout(enclosure, 1));
/* 196 */     enclosure.setOpaque(false);
/* 197 */     enclosure.setBorder(BorderFactory.createEmptyBorder());
/*     */     
/* 199 */     for (ParameterField field : this.fields) {
/* 200 */       enclosure.add(field);
/* 201 */       enclosure.add(Box.createVerticalStrut(8));
/*     */     }
/* 203 */     enclosure.add(Box.createVerticalGlue());
/* 204 */     this.scrollPane.setViewportView(enclosure);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   protected void paintComponent(Graphics g)
/*     */   {
/* 214 */     int width = getWidth();
/* 215 */     int height = getHeight();
/* 216 */     Graphics2D g2d = (Graphics2D)g;
/* 217 */     g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
/*     */     
/* 219 */     BufferedImage buffer = GraphicsUtilities.createTranslucentCompatibleImage(width, height);
/*     */     
/* 221 */     Graphics2D b2d = buffer.createGraphics();
/* 222 */     b2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
/*     */     
/* 224 */     b2d.setComposite(AlphaComposite.Clear);
/* 225 */     b2d.fillRect(0, 0, width, height);
/* 226 */     b2d.setComposite(AlphaComposite.Src);
/* 227 */     b2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
/*     */     
/* 229 */     b2d.setColor(Color.WHITE);
/* 230 */     Shape round = new RoundRectangle2D.Float(-50.0F, 30.0F, width + 50, height - 60, 20.0F, 20.0F);
/*     */     
/* 232 */     b2d.fill(round);
/* 233 */     b2d.setComposite(AlphaComposite.SrcAtop);
/* 234 */     b2d.setPaint(new GradientPaint(0.0F, 0.0F, ColorUtils.darken(HEADER_COLOR, 30), 30.0F, 0.0F, HEADER_COLOR));
/*     */     
/* 236 */     b2d.fillRect(0, 0, width, 80);
/* 237 */     b2d.setPaint(new GradientPaint(0.0F, 0.0F, ColorUtils.darken(BODY_COLOR, 30), 30.0F, 0.0F, BODY_COLOR));
/*     */     
/* 239 */     b2d.fillRect(0, 80, width, height - 80);
/* 240 */     b2d.dispose();
/* 241 */     g2d.drawImage(buffer, 0, 0, null);
/*     */   }
/*     */ }


/* Location:              /home/menick/Scrivania/ProcessMining/CNMining/CNMining.jar!/org/deckfour/uitopia/ui/actions/ActionsOutputBrowser.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */