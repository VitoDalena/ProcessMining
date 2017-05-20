/*     */ package org.deckfour.uitopia.ui.actions;
/*     */ 
/*     */ import com.fluxicon.slickerbox.components.RoundedPanel;
/*     */ import com.fluxicon.slickerbox.factory.SlickerDecorator;
/*     */ import java.awt.BorderLayout;
/*     */ import java.awt.Color;
/*     */ import java.awt.Dimension;
/*     */ import java.awt.Font;
/*     */ import java.awt.Rectangle;
/*     */ import java.awt.event.ActionEvent;
/*     */ import java.awt.event.ActionListener;
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
/*     */ import javax.swing.SwingUtilities;
/*     */ import org.deckfour.uitopia.api.hub.TaskManager;
/*     */ import org.deckfour.uitopia.api.model.Task;
/*     */ import org.deckfour.uitopia.ui.components.ImageButton;
/*     */ import org.deckfour.uitopia.ui.components.TiledPanel;
/*     */ import org.deckfour.uitopia.ui.components.ViewHeaderBar;
/*     */ import org.deckfour.uitopia.ui.util.ArrangementHelper;
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
/*     */ public class ActivityView
/*     */   extends JPanel
/*     */ {
/*     */   private static final long serialVersionUID = -8449309433547207349L;
/*     */   private static final int BEZEL_WIDTH = 700;
/*     */   private final TaskManager<?, ?> manager;
/*     */   private final List<Task<?>> tasks;
/*     */   private final List<TaskView> taskViews;
/*     */   private final ActionListener listener;
/*     */   private final JPanel listPanel;
/*     */   private final JScrollPane scrollPane;
/*     */   
/*     */   public ActivityView(TaskManager<?, ?> manager, ActionListener closeListener)
/*     */   {
/*  66 */     this.manager = manager;
/*  67 */     this.tasks = new ArrayList();
/*  68 */     this.taskViews = new ArrayList();
/*  69 */     this.tasks.addAll(this.manager.getActiveTasks());
/*  70 */     this.listener = closeListener;
/*  71 */     setLayout(new BorderLayout());
/*  72 */     setOpaque(true);
/*  73 */     setBorder(BorderFactory.createEmptyBorder());
/*  74 */     ImageButton removeButton = new ImageButton(ImageLoader.load("remove_30x30_black.png"), new Color(80, 80, 80), new Color(140, 140, 140), 0);
/*     */     
/*  76 */     removeButton.addActionListener(new ActionListener() {
/*     */       public void actionPerformed(ActionEvent e) {
/*  78 */         ActivityView.this.listener.actionPerformed(new ActionEvent(this, 1001, "closed"));
/*     */       }
/*  80 */     });
/*  81 */     removeButton.setToolTipText("Close activity view");
/*  82 */     ViewHeaderBar header = new ViewHeaderBar("Activity");
/*  83 */     header.addComponent(removeButton);
/*  84 */     add(header, "North");
/*  85 */     JPanel contents = new TiledPanel(ImageLoader.load("tile_metal.jpg"));
/*  86 */     contents.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
/*  87 */     contents.setLayout(new BorderLayout());
/*  88 */     RoundedPanel bezel = new RoundedPanel(20, 10, 20);
/*  89 */     bezel.setBackground(new Color(30, 30, 30));
/*  90 */     bezel.setLayout(new BorderLayout());
/*  91 */     bezel.setMinimumSize(new Dimension(700, 500));
/*  92 */     bezel.setMaximumSize(new Dimension(700, 1000));
/*  93 */     bezel.setPreferredSize(new Dimension(700, 800));
/*  94 */     JLabel label = new JLabel("Tasks");
/*  95 */     label.setAlignmentX(0.5F);
/*  96 */     label.setOpaque(false);
/*  97 */     label.setForeground(new Color(180, 180, 180));
/*  98 */     label.setFont(label.getFont().deriveFont(18.0F).deriveFont(1));
/*  99 */     label.setBorder(BorderFactory.createEmptyBorder(0, 0, 20, 0));
/* 100 */     this.listPanel = new JPanel();
/* 101 */     this.listPanel.setOpaque(false);
/* 102 */     this.listPanel.setBorder(BorderFactory.createEmptyBorder());
/* 103 */     this.listPanel.setLayout(new BoxLayout(this.listPanel, 1));
/* 104 */     this.listPanel.add(Box.createVerticalGlue());
/* 105 */     this.scrollPane = new JScrollPane(this.listPanel);
/* 106 */     this.scrollPane.setOpaque(false);
/* 107 */     this.scrollPane.getViewport().setOpaque(false);
/* 108 */     this.scrollPane.setBorder(BorderFactory.createEmptyBorder());
/* 109 */     this.scrollPane.setViewportBorder(BorderFactory.createEmptyBorder());
/* 110 */     this.scrollPane.setHorizontalScrollBarPolicy(31);
/* 111 */     this.scrollPane.setVerticalScrollBarPolicy(20);
/* 112 */     SlickerDecorator.instance().decorate(this.scrollPane.getVerticalScrollBar(), new Color(0, 0, 0, 0), new Color(200, 200, 200), new Color(120, 120, 120));
/*     */     
/* 114 */     this.scrollPane.getVerticalScrollBar().setOpaque(false);
/* 115 */     bezel.add(ArrangementHelper.centerHorizontally(label), "North");
/* 116 */     bezel.add(this.scrollPane, "Center");
/* 117 */     contents.add(ArrangementHelper.centerHorizontally(ArrangementHelper.centerVertically(bezel)));
/* 118 */     add(contents, "Center");
/* 119 */     update();
/*     */   }
/*     */   
/*     */   public synchronized void update() {
/* 123 */     List<? extends Task<?>> activeTasks = this.manager.getActiveTasks();
/* 124 */     for (TaskView view : this.taskViews) {
/* 125 */       view.updateState();
/*     */     }
/* 127 */     for (int i = activeTasks.size() - 1; i >= 0; i--) {
/* 128 */       final Task<?> current = (Task)activeTasks.get(i);
/* 129 */       if (!this.tasks.contains(current)) {
/* 130 */         SwingUtilities.invokeLater(new Runnable()
/*     */         {
/*     */           public void run() {
/* 133 */             ActivityView.this.createView(current);
/*     */           }
/*     */         });
/*     */       }
/*     */     }
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */   private synchronized TaskView createView(Task<?> task)
/*     */   {
/* 144 */     if (this.tasks.contains(task))
/*     */     {
/*     */ 
/*     */ 
/* 148 */       return (TaskView)this.taskViews.get(this.tasks.indexOf(task));
/*     */     }
/*     */     
/*     */ 
/*     */ 
/* 153 */     this.tasks.add(0, task);
/* 154 */     TaskView view = new TaskView(task);
/* 155 */     this.taskViews.add(0, view);
/* 156 */     this.listPanel.add(view, 0);
/* 157 */     this.scrollPane.scrollRectToVisible(new Rectangle(0, 0, 10, 10));
/* 158 */     return view;
/*     */   }
/*     */   
/*     */   public synchronized void log(final Task<?> task, final String message) {
/* 162 */     if (!this.tasks.contains(task)) {
/* 163 */       update();
/*     */     }
/* 165 */     int i = this.tasks.indexOf(task);
/* 166 */     if (i < 0)
/*     */     {
/* 168 */       SwingUtilities.invokeLater(new Runnable()
/*     */       {
/*     */         public void run() {
/* 171 */           TaskView view = ActivityView.this.createView(task);
/* 172 */           view.appendLog(message);
/*     */         }
/* 174 */       });
/* 175 */       i = 0;
/*     */     } else {
/* 177 */       TaskView view = (TaskView)this.taskViews.get(i);
/* 178 */       view.appendLog(message);
/*     */     }
/*     */   }
/*     */ }


/* Location:              /home/menick/Scrivania/ProcessMining/CNMining/CNMining.jar!/org/deckfour/uitopia/ui/actions/ActivityView.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */