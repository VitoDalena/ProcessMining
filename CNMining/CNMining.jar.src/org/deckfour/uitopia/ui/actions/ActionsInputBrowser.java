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
/*     */ import java.util.Iterator;
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
/*     */ import org.deckfour.uitopia.api.model.Resource;
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
/*     */ public class ActionsInputBrowser
/*     */   extends JPanel
/*     */   implements ParameterFieldListener
/*     */ {
/*     */   private static final long serialVersionUID = 2418603893708495509L;
/*     */   
/*     */   public static enum Mode
/*     */   {
/*  76 */     CONSTRAINTS,  PARAMS;
/*     */     
/*     */     private Mode() {} }
/*  79 */   private static final Color HEADER_COLOR = new Color(30, 30, 30);
/*  80 */   private static final Color BODY_COLOR = new Color(120, 120, 120);
/*  81 */   private static final Color TITLE_COLOR = new Color(160, 160, 160);
/*     */   
/*     */   private UITopiaController controller;
/*     */   
/*     */   private ActionsView parent;
/*     */   private List<ParameterField> fields;
/*     */   private Mode mode;
/*     */   private JScrollPane scrollPane;
/*     */   
/*     */   public ActionsInputBrowser(ActionsView parent)
/*     */   {
/*  92 */     this.mode = Mode.CONSTRAINTS;
/*  93 */     this.parent = parent;
/*  94 */     this.controller = parent.getController();
/*  95 */     this.fields = new ArrayList();
/*  96 */     setupUI();
/*     */   }
/*     */   
/*     */   public boolean isConstraintsMode() {
/* 100 */     return this.mode.equals(Mode.CONSTRAINTS);
/*     */   }
/*     */   
/*     */   private void setupUI() {
/* 104 */     setLayout(new BoxLayout(this, 1));
/* 105 */     setOpaque(false);
/* 106 */     setBorder(BorderFactory.createEmptyBorder(45, 10, 45, 10));
/* 107 */     setMinimumSize(new Dimension(280, 500));
/* 108 */     setMaximumSize(new Dimension(500, 2000));
/* 109 */     setPreferredSize(new Dimension(450, 1000));
/* 110 */     JLabel header = new JLabel("Input");
/* 111 */     header.setOpaque(false);
/* 112 */     header.setForeground(TITLE_COLOR);
/* 113 */     header.setFont(header.getFont().deriveFont(18.0F).deriveFont(1));
/* 114 */     header.setAlignmentX(0.5F);
/* 115 */     this.scrollPane = new JScrollPane();
/* 116 */     this.scrollPane.setOpaque(false);
/* 117 */     this.scrollPane.getViewport().setOpaque(false);
/* 118 */     this.scrollPane.setBorder(BorderFactory.createEmptyBorder());
/* 119 */     this.scrollPane.setViewportBorder(BorderFactory.createEmptyBorder());
/* 120 */     this.scrollPane.setHorizontalScrollBarPolicy(31);
/*     */     
/* 122 */     this.scrollPane.setVerticalScrollBarPolicy(20);
/*     */     
/* 124 */     SlickerDecorator.instance().decorate(this.scrollPane.getVerticalScrollBar(), new Color(0, 0, 0, 0), new Color(40, 40, 40), new Color(80, 80, 80));
/*     */     
/*     */ 
/* 127 */     this.scrollPane.getVerticalScrollBar().setOpaque(false);
/* 128 */     add(header);
/* 129 */     add(Box.createVerticalStrut(25));
/* 130 */     add(this.scrollPane);
/* 131 */     reset();
/*     */   }
/*     */   
/*     */   public void setParameters(List<Parameter> parameters) {
/* 135 */     this.mode = Mode.PARAMS;
/*     */     
/* 137 */     List<List<Resource>> values = getCurrentValues();
/* 138 */     this.fields.clear();
/* 139 */     for (int i = 0; i < parameters.size(); i++) {
/* 140 */       Parameter param = (Parameter)parameters.get(i);
/*     */       
/* 142 */       ParameterField field = new ParameterField(ParameterField.Type.INPUT, param, this.controller, this);
/*     */       
/* 144 */       matchValue(param, field, values);
/* 145 */       this.fields.add(field);
/*     */     }
/* 147 */     updateFields();
/*     */   }
/*     */   
/*     */   public void setConstraint(Resource resource) {
/* 151 */     this.mode = Mode.CONSTRAINTS;
/* 152 */     this.fields.clear();
/*     */     
/* 154 */     ParameterField field = new ParameterField(ParameterField.Type.INPUT, this.controller, this);
/*     */     
/* 156 */     field.setResource(resource);
/* 157 */     this.fields.add(field);
/* 158 */     updateConstraintsList();
/*     */   }
/*     */   
/*     */   public void changed(ParameterField field) {
/* 162 */     if (this.mode.equals(Mode.PARAMS)) {
/* 163 */       updateFields();
/* 164 */       this.parent.inputParametersUpdated();
/*     */     } else {
/* 166 */       updateConstraintsList();
/* 167 */       this.parent.inputConstraintsUpdated();
/*     */     }
/*     */   }
/*     */   
/*     */   public void removed(ParameterField field) {
/* 172 */     if (this.mode.equals(Mode.PARAMS)) {
/* 173 */       field.setResource(null);
/* 174 */       updateFields();
/* 175 */       this.parent.inputParametersUpdated();
/*     */     } else {
/* 177 */       this.fields.remove(field);
/* 178 */       updateConstraintsList();
/* 179 */       this.parent.inputConstraintsUpdated();
/*     */     }
/*     */   }
/*     */   
/*     */   public void reset() {
/* 184 */     this.mode = Mode.CONSTRAINTS;
/* 185 */     this.fields.clear();
/* 186 */     updateConstraintsList();
/*     */   }
/*     */   
/*     */   private void updateConstraintsList() {
/* 190 */     assert (this.mode.equals(Mode.CONSTRAINTS));
/* 191 */     List<List<Resource>> values = getCurrentValues();
/* 192 */     this.fields.clear();
/* 193 */     for (int i = 0; i < values.size(); i++)
/*     */     {
/* 195 */       ParameterField field = new ParameterField(ParameterField.Type.INPUT, this.controller, this);
/*     */       
/*     */ 
/* 198 */       field.setResource((Resource)((List)values.get(i)).get(0));
/* 199 */       for (int j = 1; j < ((List)values.get(i)).size(); j++) {
/* 200 */         field.addChild().setResource((Resource)((List)values.get(i)).get(j));
/*     */       }
/* 202 */       this.fields.add(field);
/*     */     }
/*     */     
/* 205 */     ParameterField field = new ParameterField(ParameterField.Type.INPUT, this.controller, this);
/*     */     
/* 207 */     this.fields.add(field);
/* 208 */     updateFields();
/*     */   }
/*     */   
/*     */   protected void updateFields()
/*     */   {
/* 213 */     JPanel enclosure = new JPanel();
/* 214 */     enclosure.setLayout(new BoxLayout(enclosure, 1));
/* 215 */     enclosure.setOpaque(false);
/* 216 */     enclosure.setBorder(BorderFactory.createEmptyBorder());
/*     */     
/* 218 */     for (ParameterField field : this.fields) {
/* 219 */       if ((field.getResource() != null) && (field.getResource().isDestroyed()))
/*     */       {
/* 221 */         field.setResource(null);
/*     */       }
/* 223 */       enclosure.add(field);
/* 224 */       enclosure.add(Box.createVerticalStrut(8));
/* 225 */       for (ParameterField child : field.getChildren()) {
/* 226 */         if ((child.getResource() != null) && (child.getResource().isDestroyed()))
/*     */         {
/* 228 */           child.setResource(null);
/*     */         }
/* 230 */         enclosure.add(child);
/* 231 */         enclosure.add(Box.createVerticalStrut(8));
/*     */       }
/*     */     }
/* 234 */     enclosure.add(Box.createVerticalGlue());
/* 235 */     this.scrollPane.setViewportView(enclosure);
/* 236 */     invalidate();
/*     */   }
/*     */   
/*     */   public List<List<Resource>> getCurrentValues() {
/* 240 */     ArrayList<List<Resource>> values = new ArrayList();
/* 241 */     for (ParameterField field : this.fields) {
/* 242 */       List<Resource> res = field.getResources();
/* 243 */       if (!res.isEmpty()) {
/* 244 */         values.add(res);
/*     */       }
/*     */     }
/* 247 */     return values;
/*     */   }
/*     */   
/*     */   public List<ResourceType> getCurrentParameters() {
/* 251 */     ArrayList<ResourceType> types = new ArrayList();
/* 252 */     for (ParameterField field : this.fields) {
/* 253 */       List<Resource> res = field.getResources();
/* 254 */       if (!res.isEmpty()) {
/* 255 */         ResourceType type = ((Resource)res.iterator().next()).getType();
/* 256 */         if (type != null) {
/* 257 */           types.add(type);
/*     */         }
/*     */       }
/*     */     }
/* 261 */     return types;
/*     */   }
/*     */   
/*     */   private void matchValue(Parameter param, ParameterField field, List<List<Resource>> values)
/*     */   {
/* 266 */     ResourceType type = param.getType();
/* 267 */     for (int i = 0; i < values.size(); i++) {
/* 268 */       Iterator<Resource> it = ((List)values.get(i)).iterator();
/* 269 */       while (it.hasNext()) {
/* 270 */         Resource v = (Resource)it.next();
/* 271 */         if ((type.isAssignableFrom(v.getType())) && (field.getFreeParameterCount() > 0))
/*     */         {
/*     */ 
/* 274 */           if (field.getResource() == null) {
/* 275 */             field.setResource(v);
/*     */           } else {
/* 277 */             field.addChild().setResource(v);
/*     */           }
/* 279 */           it.remove();
/*     */         }
/*     */       }
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
/*     */   protected void paintComponent(Graphics g)
/*     */   {
/* 294 */     int width = getWidth();
/* 295 */     int height = getHeight();
/* 296 */     Graphics2D g2d = (Graphics2D)g;
/* 297 */     g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
/*     */     
/* 299 */     BufferedImage buffer = GraphicsUtilities.createTranslucentCompatibleImage(width, height);
/*     */     
/* 301 */     Graphics2D b2d = buffer.createGraphics();
/* 302 */     b2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
/*     */     
/* 304 */     b2d.setComposite(AlphaComposite.Clear);
/* 305 */     b2d.fillRect(0, 0, width, height);
/* 306 */     b2d.setComposite(AlphaComposite.Src);
/* 307 */     b2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
/*     */     
/* 309 */     b2d.setColor(Color.WHITE);
/* 310 */     Shape round = new RoundRectangle2D.Float(0.0F, 30.0F, width + 50, height - 60, 20.0F, 20.0F);
/*     */     
/* 312 */     b2d.fill(round);
/* 313 */     b2d.setComposite(AlphaComposite.SrcAtop);
/* 314 */     b2d.setPaint(new GradientPaint(width - 30, 0.0F, HEADER_COLOR, width, 0.0F, ColorUtils.darken(HEADER_COLOR, 30)));
/*     */     
/* 316 */     b2d.fillRect(0, 0, width, 80);
/* 317 */     b2d.setPaint(new GradientPaint(width - 30, 0.0F, BODY_COLOR, width, 0.0F, ColorUtils.darken(BODY_COLOR, 30)));
/*     */     
/* 319 */     b2d.fillRect(0, 80, width, height - 80);
/* 320 */     b2d.dispose();
/* 321 */     g2d.drawImage(buffer, 0, 0, null);
/*     */   }
/*     */ }


/* Location:              /home/menick/Scrivania/ProcessMining/CNMining/CNMining.jar!/org/deckfour/uitopia/ui/actions/ActionsInputBrowser.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */