/*     */ package org.deckfour.uitopia.ui.actions;
/*     */ 
/*     */ import com.fluxicon.slickerbox.factory.SlickerDecorator;
/*     */ import com.fluxicon.slickerbox.ui.SlickerDarkProgressBarUI;
/*     */ import com.fluxicon.slickerbox.util.ColorUtils;
/*     */ import java.awt.BorderLayout;
/*     */ import java.awt.Color;
/*     */ import java.awt.Dimension;
/*     */ import java.awt.FlowLayout;
/*     */ import java.awt.Font;
/*     */ import java.awt.GradientPaint;
/*     */ import java.awt.Graphics;
/*     */ import java.awt.Graphics2D;
/*     */ import java.awt.Image;
/*     */ import java.awt.RenderingHints;
/*     */ import java.awt.event.ActionEvent;
/*     */ import java.awt.event.ActionListener;
/*     */ import javax.swing.BorderFactory;
/*     */ import javax.swing.Box;
/*     */ import javax.swing.BoxLayout;
/*     */ import javax.swing.JLabel;
/*     */ import javax.swing.JPanel;
/*     */ import javax.swing.JProgressBar;
/*     */ import javax.swing.JScrollBar;
/*     */ import javax.swing.JScrollPane;
/*     */ import javax.swing.JTextPane;
/*     */ import javax.swing.JViewport;
/*     */ import javax.swing.SwingUtilities;
/*     */ import org.deckfour.uitopia.api.model.Action;
/*     */ import org.deckfour.uitopia.api.model.Task;
/*     */ import org.deckfour.uitopia.ui.components.ImageButton;
/*     */ import org.deckfour.uitopia.ui.util.ImageLoader;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class TaskView
/*     */   extends JPanel
/*     */ {
/*     */   private static final long serialVersionUID = -5068169511564265642L;
/*  75 */   private static final Color BG_ACTIVE = new Color(30, 100, 30);
/*  76 */   private static final Color BG_PASSIVE = new Color(40, 40, 40);
/*     */   private static final int MIN_HEIGHT = 80;
/*     */   private static final int MAX_HEIGHT = 180;
/*  79 */   private static final Image ICON_REMOVE = ImageLoader.load("remove_30x30_black.png");
/*     */   
/*     */   private Task<?> task;
/*     */   
/*     */   private JPanel progressPanel;
/*     */   private JProgressBar progress;
/*     */   private ImageButton cancelButton;
/*     */   private JTextPane log;
/*     */   private JScrollPane scrollPane;
/*     */   
/*     */   public TaskView(Task<?> task)
/*     */   {
/*  91 */     this.task = task;
/*     */     
/*  93 */     setBorder(BorderFactory.createEmptyBorder(5, 15, 5, 15));
/*  94 */     setOpaque(false);
/*  95 */     setLayout(new BoxLayout(this, 1));
/*     */     
/*  97 */     setupUI();
/*  98 */     setSize();
/*  99 */     revalidate();
/* 100 */     repaint();
/*     */   }
/*     */   
/*     */   private void setSize() {
/* 104 */     setMinimumSize(new Dimension(600, 80));
/* 105 */     setMaximumSize(new Dimension(600, 180));
/* 106 */     setPreferredSize(new Dimension(600, 80));
/* 107 */     setAlignmentX(0.0F);
/*     */   }
/*     */   
/*     */   private void setupUI() {
/* 111 */     removeAll();
/* 112 */     JLabel label = new JLabel(this.task.getAction().getName());
/* 113 */     label.setOpaque(false);
/* 114 */     label.setForeground(new Color(180, 180, 180));
/* 115 */     label.setFont(label.getFont().deriveFont(14.0F));
/* 116 */     JPanel labelPanel = new JPanel(new FlowLayout(0));
/* 117 */     labelPanel.add(label);
/* 118 */     labelPanel.setOpaque(false);
/* 119 */     labelPanel.setAlignmentY(0.0F);
/*     */     
/* 121 */     this.progress = new JProgressBar();
/* 122 */     this.progress.setUI(new SlickerDarkProgressBarUI());
/* 123 */     this.progress.setMinimumSize(new Dimension(300, 20));
/* 124 */     this.progress.setMaximumSize(new Dimension(300, 20));
/* 125 */     this.progress.setPreferredSize(new Dimension(300, 20));
/* 126 */     this.progress.setMinimum(0);
/* 127 */     this.progress.setMaximum(1000);
/* 128 */     this.progress.setIndeterminate(true);
/* 129 */     this.cancelButton = new ImageButton(ICON_REMOVE, new Color(140, 140, 140), new Color(240, 240, 240), 0);
/*     */     
/* 131 */     this.cancelButton.addActionListener(new ActionListener() {
/*     */       public void actionPerformed(ActionEvent e) {
/* 133 */         TaskView.this.task.destroy();
/* 134 */         TaskView.this.revalidate();
/*     */       }
/* 136 */     });
/* 137 */     this.cancelButton.setAlignmentY(0.5F);
/* 138 */     this.cancelButton.setToolTipText("Cancel task");
/*     */     
/* 140 */     this.progressPanel = new JPanel();
/* 141 */     this.progressPanel.setBorder(BorderFactory.createEmptyBorder());
/* 142 */     this.progressPanel.setLayout(new BorderLayout(10, 10));
/* 143 */     this.progressPanel.setOpaque(false);
/* 144 */     this.progressPanel.add(this.progress, "Center");
/* 145 */     this.progressPanel.add(this.cancelButton, "East");
/* 146 */     this.progressPanel.setAlignmentY(0.0F);
/*     */     
/* 148 */     this.log = new JTextPane();
/* 149 */     this.log.setOpaque(false);
/* 150 */     this.log.setForeground(new Color(180, 180, 180));
/* 151 */     this.log.setEditable(false);
/* 152 */     this.scrollPane = new JScrollPane(this.log);
/* 153 */     this.scrollPane.setOpaque(false);
/* 154 */     this.scrollPane.setForeground(new Color(180, 180, 180));
/* 155 */     this.scrollPane.getViewport().setOpaque(false);
/* 156 */     this.scrollPane.setBorder(BorderFactory.createEmptyBorder());
/* 157 */     this.scrollPane.setViewportBorder(BorderFactory.createEmptyBorder());
/* 158 */     this.scrollPane.setHorizontalScrollBarPolicy(30);
/*     */     
/* 160 */     SlickerDecorator.instance().decorate(this.scrollPane.getHorizontalScrollBar(), new Color(0, 0, 0, 0), new Color(40, 40, 40), new Color(80, 80, 80));
/*     */     
/*     */ 
/* 163 */     this.scrollPane.getHorizontalScrollBar().setOpaque(false);
/* 164 */     this.scrollPane.setVerticalScrollBarPolicy(20);
/*     */     
/* 166 */     SlickerDecorator.instance().decorate(this.scrollPane.getVerticalScrollBar(), new Color(0, 0, 0, 0), new Color(40, 40, 40), new Color(80, 80, 80));
/*     */     
/*     */ 
/* 169 */     this.scrollPane.getVerticalScrollBar().setOpaque(false);
/*     */     
/* 171 */     add(labelPanel);
/* 172 */     add(Box.createVerticalStrut(5));
/* 173 */     add(this.progressPanel);
/* 174 */     add(Box.createVerticalStrut(5));
/* 175 */     add(this.scrollPane);
/*     */   }
/*     */   
/*     */   public void updateState() {
/* 179 */     SwingUtilities.invokeLater(new Runnable() {
/*     */       public void run() {
/* 181 */         double prog = TaskView.this.task.getProgress();
/* 182 */         if (prog >= 1.0D) {
/* 183 */           TaskView.this.remove(TaskView.this.progressPanel);
/*     */ 
/*     */         }
/*     */         else
/*     */         {
/* 188 */           int value = (int)(1000.0D * prog);
/* 189 */           if (TaskView.this.progress.getValue() != value) {
/* 190 */             if (TaskView.this.progress.isIndeterminate()) {
/* 191 */               TaskView.this.progress.setIndeterminate(false);
/* 192 */               TaskView.this.progress.revalidate();
/*     */             }
/* 194 */             TaskView.this.progress.setValue(value);
/*     */           }
/*     */         }
/*     */       }
/* 198 */     });
/* 199 */     Thread.yield();
/*     */   }
/*     */   
/* 202 */   private int currentHeight = 0;
/*     */   
/*     */   public void appendLog(final String message) {
/* 205 */     SwingUtilities.invokeLater(new Runnable() {
/*     */       public void run() {
/* 207 */         TaskView.this.log.setText(TaskView.this.log.getText() + message + "\n");
/* 208 */         int height = 80 + TaskView.this.log.getPreferredSize().height + (TaskView.this.scrollPane.getHorizontalScrollBar().isVisible() ? TaskView.this.scrollPane.getHorizontalScrollBar().getPreferredSize().height : 0);
/*     */         
/*     */ 
/*     */ 
/*     */ 
/* 213 */         if (height != TaskView.this.currentHeight) {
/* 214 */           TaskView.this.currentHeight = Math.min(height, 180);
/* 215 */           TaskView.this.setPreferredSize(new Dimension(TaskView.this.getPreferredSize().width, TaskView.this.currentHeight));
/*     */           
/* 217 */           TaskView.this.revalidate();
/* 218 */           TaskView.this.repaint();
/*     */         }
/*     */       }
/* 221 */     });
/* 222 */     Thread.yield();
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   protected void paintComponent(Graphics g)
/*     */   {
/* 232 */     int width = getWidth();
/* 233 */     int height = getHeight();
/* 234 */     Graphics2D g2d = (Graphics2D)g.create();
/* 235 */     g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
/*     */     
/* 237 */     Color bg = BG_PASSIVE;
/* 238 */     if (this.task.getProgress() < 1.0D) {
/* 239 */       bg = BG_ACTIVE;
/*     */     }
/* 241 */     g2d.setPaint(new GradientPaint(0.0F, 20.0F, bg, 0.0F, height, ColorUtils.darken(bg, 30)));
/*     */     
/* 243 */     g2d.fillRect(0, 0, width, height);
/* 244 */     g2d.dispose();
/*     */   }
/*     */ }


/* Location:              /home/menick/Scrivania/ProcessMining/CNMining/CNMining.jar!/org/deckfour/uitopia/ui/actions/TaskView.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */