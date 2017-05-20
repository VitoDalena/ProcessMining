/*      */ package edu.oswego.cs.dl.util.concurrent.misc;
/*      */ 
/*      */ import edu.oswego.cs.dl.util.concurrent.BoundedBuffer;
/*      */ import edu.oswego.cs.dl.util.concurrent.BoundedLinkedQueue;
/*      */ import edu.oswego.cs.dl.util.concurrent.BoundedPriorityQueue;
/*      */ import edu.oswego.cs.dl.util.concurrent.BrokenBarrierException;
/*      */ import edu.oswego.cs.dl.util.concurrent.Channel;
/*      */ import edu.oswego.cs.dl.util.concurrent.ClockDaemon;
/*      */ import edu.oswego.cs.dl.util.concurrent.CyclicBarrier;
/*      */ import edu.oswego.cs.dl.util.concurrent.DefaultChannelCapacity;
/*      */ import edu.oswego.cs.dl.util.concurrent.LinkedQueue;
/*      */ import edu.oswego.cs.dl.util.concurrent.PooledExecutor;
/*      */ import edu.oswego.cs.dl.util.concurrent.Slot;
/*      */ import edu.oswego.cs.dl.util.concurrent.SynchronizedBoolean;
/*      */ import edu.oswego.cs.dl.util.concurrent.SynchronizedInt;
/*      */ import edu.oswego.cs.dl.util.concurrent.SynchronizedLong;
/*      */ import edu.oswego.cs.dl.util.concurrent.SynchronizedRef;
/*      */ import edu.oswego.cs.dl.util.concurrent.SynchronousChannel;
/*      */ import edu.oswego.cs.dl.util.concurrent.WaitFreeQueue;
/*      */ import edu.oswego.cs.dl.util.concurrent.WaitableInt;
/*      */ import java.awt.Color;
/*      */ import java.awt.Component;
/*      */ import java.awt.Container;
/*      */ import java.awt.Dimension;
/*      */ import java.awt.Font;
/*      */ import java.awt.GridLayout;
/*      */ import java.awt.Window;
/*      */ import java.awt.event.ActionEvent;
/*      */ import java.awt.event.ActionListener;
/*      */ import java.awt.event.ItemEvent;
/*      */ import java.awt.event.ItemListener;
/*      */ import java.awt.event.WindowAdapter;
/*      */ import java.awt.event.WindowEvent;
/*      */ import java.io.PrintStream;
/*      */ import java.lang.reflect.InvocationTargetException;
/*      */ import javax.swing.AbstractButton;
/*      */ import javax.swing.BoxLayout;
/*      */ import javax.swing.JButton;
/*      */ import javax.swing.JCheckBox;
/*      */ import javax.swing.JComboBox;
/*      */ import javax.swing.JComponent;
/*      */ import javax.swing.JFrame;
/*      */ import javax.swing.JLabel;
/*      */ import javax.swing.JPanel;
/*      */ import javax.swing.SwingUtilities;
/*      */ import javax.swing.border.LineBorder;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ public class SynchronizationTimer
/*      */ {
/*      */   public static void main(String[] paramArrayOfString)
/*      */   {
/*  279 */     JFrame localJFrame = new JFrame("Times per call in microseconds");
/*      */     
/*  281 */     localJFrame.addWindowListener(new WindowAdapter() {
/*  282 */       public void windowClosing(WindowEvent paramAnonymousWindowEvent) { System.exit(0);
/*      */       }
/*  284 */     });
/*  285 */     localJFrame.getContentPane().add(new SynchronizationTimer().mainPanel());
/*  286 */     localJFrame.pack();
/*  287 */     localJFrame.setVisible(true);
/*      */   }
/*      */   
/*      */ 
/*      */   static class TestedClass
/*      */   {
/*      */     final String name;
/*      */     
/*      */     final Class cls;
/*      */     final boolean multipleOK;
/*      */     final boolean singleOK;
/*      */     final Class buffCls;
/*  299 */     Boolean enabled_ = new Boolean(true);
/*      */     
/*  301 */     synchronized void setEnabled(Boolean paramBoolean) { this.enabled_ = paramBoolean; }
/*  302 */     synchronized Boolean getEnabled() { return this.enabled_; }
/*      */     
/*      */     synchronized void toggleEnabled() {
/*  305 */       boolean bool = this.enabled_.booleanValue();
/*  306 */       this.enabled_ = new Boolean(!bool);
/*      */     }
/*      */     
/*      */     synchronized boolean isEnabled(int paramInt, Fraction paramFraction) {
/*  310 */       boolean bool = this.enabled_.booleanValue();
/*  311 */       if (!bool) return false;
/*  312 */       if ((!this.singleOK) && (paramInt <= 1)) return false;
/*  313 */       if ((!this.multipleOK) && (paramInt > 1) && (paramFraction.compareTo(0L) > 0)) return false;
/*  314 */       return true;
/*      */     }
/*      */     
/*      */     TestedClass(String paramString, Class paramClass, boolean paramBoolean1, boolean paramBoolean2)
/*      */     {
/*  319 */       this.name = paramString;this.cls = paramClass;this.multipleOK = paramBoolean1;this.singleOK = paramBoolean2;
/*  320 */       this.buffCls = null;
/*      */     }
/*      */     
/*      */     TestedClass(String paramString, Class paramClass1, boolean paramBoolean1, boolean paramBoolean2, Class paramClass2) {
/*  324 */       this.name = paramString;this.cls = paramClass1;this.multipleOK = paramBoolean1;this.singleOK = paramBoolean2;
/*  325 */       this.buffCls = paramClass2;
/*      */     }
/*      */     
/*  328 */     static final TestedClass dummy = new TestedClass("", null, false, false);
/*      */     
/*      */ 
/*  331 */     static final TestedClass[] classes = { new TestedClass("NoSynchronization", NoSynchRNG.class, false, true), new TestedClass("PublicSynchronization", PublicSynchRNG.class, true, true), new TestedClass("NestedSynchronization", AllSynchRNG.class, true, true), new TestedClass("SDelegated", SDelegatedRNG.class, true, true), new TestedClass("SynchLongUsingSet", SynchLongRNG.class, true, true), new TestedClass("SynchLongUsingCommit", AClongRNG.class, true, true), new TestedClass("Semaphore", SemRNG.class, true, true), new TestedClass("WaiterPrefSemaphore", WpSemRNG.class, true, true), new TestedClass("FIFOSemaphore", FifoRNG.class, true, true), new TestedClass("PrioritySemaphore", PrioritySemRNG.class, true, true), new TestedClass("Mutex", MutexRNG.class, true, true), new TestedClass("ReentrantLock", RlockRNG.class, true, true), new TestedClass("WriterPrefRWLock", WpRWlockRNG.class, true, true), new TestedClass("ReaderPrefRWLock", ReaderPrefRWlockRNG.class, true, true), new TestedClass("FIFORWLock", FIFORWlockRNG.class, true, true), new TestedClass("ReentrantRWL", ReentrantRWlockRNG.class, true, true), new TestedClass("LinkedQueue", ChanRNG.class, true, true, LinkedQueue.class), new TestedClass("WaitFreeQueue", ChanRNG.class, true, true, WaitFreeQueue.class), new TestedClass("BoundedLinkedQueue", ChanRNG.class, true, true, BoundedLinkedQueue.class), new TestedClass("BoundedBuffer", ChanRNG.class, true, true, BoundedBuffer.class), new TestedClass("CondVarBoundedBuffer", ChanRNG.class, true, true, CVBuffer.class), new TestedClass("BoundedPriorityQueue", ChanRNG.class, true, true, BoundedPriorityQueue.class), new TestedClass("Slot", ChanRNG.class, true, true, Slot.class), new TestedClass("SynchronousChannel", ChanRNG.class, true, false, SynchronousChannel.class), new TestedClass("DirectExecutor", DirectExecutorRNG.class, true, true), new TestedClass("SemaphoreLckExecutor", LockedSemRNG.class, true, true), new TestedClass("QueuedExecutor", QueuedExecutorRNG.class, true, true), new TestedClass("ThreadedExecutor", ThreadedExecutorRNG.class, true, true), new TestedClass("PooledExecutor", PooledExecutorRNG.class, true, true) };
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*  391 */   static final int[] nthreadsChoices = { 1, 2, 4, 8, 16, 32, 64, 128, 256, 512, 1024 };
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   static final int BLOCK_MODE = 0;
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   static final int TIMEOUT_MODE = 1;
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*  408 */   static final int[] syncModes = { 0, 1 };
/*      */   static final int PRECISION = 10;
/*      */   
/*      */   static String modeToString(int paramInt)
/*      */   {
/*      */     String str;
/*  414 */     if (paramInt == 0) { str = "block";
/*  415 */     } else if (paramInt == 1) str = "timeout"; else
/*  416 */       str = "No such mode";
/*  417 */     return str;
/*      */   }
/*      */   
/*      */   static String biasToString(int paramInt) {
/*      */     String str;
/*  422 */     if (paramInt < 0) { str = "slower producer";
/*  423 */     } else if (paramInt == 0) { str = "balanced prod/cons rate";
/*  424 */     } else if (paramInt > 0) str = "slower consumer"; else
/*  425 */       str = "No such bias";
/*  426 */     return str;
/*      */   }
/*      */   
/*      */   static String p2ToString(int paramInt)
/*      */   {
/*  431 */     String str = "";
/*  432 */     if (paramInt >= 1024) {
/*  433 */       paramInt /= 1024;
/*  434 */       str = "K";
/*  435 */       if (paramInt >= 1024) {
/*  436 */         paramInt /= 1024;
/*  437 */         str = "M";
/*      */       }
/*      */     }
/*  440 */     return paramInt + str;
/*      */   }
/*      */   
/*      */ 
/*      */   static String formatTime(long paramLong, boolean paramBoolean)
/*      */   {
/*  446 */     long l1 = paramLong / 10L;
/*  447 */     long l2 = paramLong % 10L;
/*  448 */     if (!paramBoolean) {
/*  449 */       if (l2 >= 5L)
/*  450 */         l1 += 1L;
/*  451 */       return Long.toString(l1);
/*      */     }
/*      */     
/*  454 */     String str1 = Long.toString(l1);
/*  455 */     String str2 = Long.toString(l2);
/*  456 */     if (l2 == 0L) {
/*  457 */       int i = 10;
/*  458 */       while (i > 10) {
/*  459 */         str2 = "0" + str2;
/*  460 */         i /= 10;
/*      */       }
/*      */     }
/*  463 */     String str3 = str1 + "." + str2;
/*  464 */     return str3;
/*      */   }
/*      */   
/*      */   static class ThreadInfo {
/*      */     final String name;
/*      */     final int number;
/*      */     Boolean enabled;
/*      */     
/*      */     ThreadInfo(int paramInt) {
/*  473 */       this.number = paramInt;
/*  474 */       this.name = SynchronizationTimer.p2ToString(paramInt);
/*  475 */       this.enabled = new Boolean(true);
/*      */     }
/*      */     
/*  478 */     synchronized Boolean getEnabled() { return this.enabled; }
/*  479 */     synchronized void setEnabled(Boolean paramBoolean) { this.enabled = paramBoolean; }
/*      */     
/*  481 */     synchronized void toggleEnabled() { this.enabled = new Boolean(!this.enabled.booleanValue()); }
/*      */   }
/*      */   
/*      */ 
/*  485 */   final ThreadInfo[] threadInfo = new ThreadInfo[nthreadsChoices.length];
/*      */   static final int headerRows = 1;
/*      */   
/*  488 */   boolean threadEnabled(int paramInt) { return this.threadInfo[paramInt].getEnabled().booleanValue(); }
/*      */   
/*      */ 
/*      */ 
/*      */   static final int classColumn = 0;
/*      */   
/*      */   static final int headerColumns = 1;
/*      */   
/*  496 */   final int tableRows = TestedClass.classes.length + 1;
/*  497 */   final int tableColumns = nthreadsChoices.length + 1;
/*      */   
/*  499 */   final JComponent[][] resultTable_ = new JComponent[this.tableRows][this.tableColumns];
/*      */   
/*      */   JPanel resultPanel()
/*      */   {
/*  503 */     JPanel[] arrayOfJPanel = new JPanel[this.tableColumns];
/*  504 */     for (int i = 0; i < this.tableColumns; i++) {
/*  505 */       arrayOfJPanel[i] = new JPanel();
/*  506 */       arrayOfJPanel[i].setLayout(new GridLayout(this.tableRows, 1));
/*  507 */       if (i != 0) {
/*  508 */         arrayOfJPanel[i].setBackground(Color.white);
/*      */       }
/*      */     }
/*  511 */     Color localColor = arrayOfJPanel[0].getBackground();
/*  512 */     LineBorder localLineBorder = new LineBorder(localColor);
/*      */     
/*  514 */     Font localFont = new Font("Dialog", 0, 12);
/*  515 */     Dimension localDimension1 = new Dimension(40, 16);
/*  516 */     Dimension localDimension2 = new Dimension(154, 16);
/*      */     
/*  518 */     JLabel localJLabel1 = new JLabel(" Classes      \\      Threads");
/*  519 */     localJLabel1.setMinimumSize(localDimension2);
/*  520 */     localJLabel1.setPreferredSize(localDimension2);
/*  521 */     localJLabel1.setFont(localFont);
/*  522 */     this.resultTable_[0][0] = localJLabel1;
/*  523 */     arrayOfJPanel[0].add(localJLabel1);
/*      */     
/*  525 */     for (int j = 1; j < this.tableColumns; j++) {
/*  526 */       k = j - 1;
/*  527 */       JCheckBox localJCheckBox1 = new JCheckBox(this.threadInfo[k].name, true);
/*  528 */       localJCheckBox1.addActionListener(new ActionListener() { private final int val$nthreads;
/*      */         
/*  530 */         public void actionPerformed(ActionEvent paramAnonymousActionEvent) { SynchronizationTimer.this.threadInfo[this.val$nthreads].toggleEnabled();
/*      */         }
/*      */ 
/*  533 */       });
/*  534 */       localJCheckBox1.setMinimumSize(localDimension1);
/*  535 */       localJCheckBox1.setPreferredSize(localDimension1);
/*  536 */       localJCheckBox1.setFont(localFont);
/*  537 */       localJCheckBox1.setBackground(localColor);
/*  538 */       this.resultTable_[0][j] = localJCheckBox1;
/*  539 */       arrayOfJPanel[j].add(localJCheckBox1);
/*      */     }
/*      */     
/*      */ 
/*  543 */     for (int k = 1; k < this.tableRows; k++) {
/*  544 */       int m = k - 1;
/*      */       
/*  546 */       JCheckBox localJCheckBox2 = new JCheckBox(TestedClass.classes[m].name, true);
/*  547 */       localJCheckBox2.addActionListener(new ActionListener() { private final int val$cls;
/*      */         
/*  549 */         public void actionPerformed(ActionEvent paramAnonymousActionEvent) { SynchronizationTimer.TestedClass.classes[this.val$cls].toggleEnabled();
/*      */         }
/*  551 */       });
/*  552 */       this.resultTable_[k][0] = localJCheckBox2;
/*  553 */       localJCheckBox2.setMinimumSize(localDimension2);
/*  554 */       localJCheckBox2.setPreferredSize(localDimension2);
/*  555 */       localJCheckBox2.setFont(localFont);
/*  556 */       arrayOfJPanel[0].add(localJCheckBox2);
/*      */       
/*  558 */       for (int i1 = 1; i1 < this.tableColumns; i1++) {
/*  559 */         int i2 = i1 - 1;
/*  560 */         JLabel localJLabel2 = new JLabel("");
/*  561 */         this.resultTable_[k][i1] = localJLabel2;
/*      */         
/*  563 */         localJLabel2.setMinimumSize(localDimension1);
/*  564 */         localJLabel2.setPreferredSize(localDimension1);
/*  565 */         localJLabel2.setBorder(localLineBorder);
/*  566 */         localJLabel2.setFont(localFont);
/*  567 */         localJLabel2.setBackground(Color.white);
/*  568 */         localJLabel2.setForeground(Color.black);
/*  569 */         localJLabel2.setHorizontalAlignment(4);
/*      */         
/*  571 */         arrayOfJPanel[i1].add(localJLabel2);
/*      */       }
/*      */     }
/*      */     
/*  575 */     JPanel localJPanel = new JPanel();
/*  576 */     localJPanel.setLayout(new BoxLayout(localJPanel, 0));
/*  577 */     for (int n = 0; n < this.tableColumns; n++) {
/*  578 */       localJPanel.add(arrayOfJPanel[n]);
/*      */     }
/*      */     
/*  581 */     return localJPanel;
/*      */   }
/*      */   
/*      */   void setTime(long paramLong, int paramInt1, int paramInt2)
/*      */   {
/*  586 */     int i = paramInt1 + 1;
/*  587 */     int j = paramInt2 + 1;
/*  588 */     JLabel localJLabel = (JLabel)this.resultTable_[i][j];
/*      */     
/*  590 */     SwingUtilities.invokeLater(new Runnable() { private final JLabel val$cell;
/*      */       
/*  592 */       public void run() { this.val$cell.setText(SynchronizationTimer.formatTime(this.val$ns, true)); }
/*      */     });
/*      */   }
/*      */   
/*      */ 
/*      */   private final long val$ns;
/*      */   void clearTable()
/*      */   {
/*  600 */     for (int i = 1; i < this.tableRows; i++) {
/*  601 */       for (int j = 1; j < this.tableColumns; j++) {
/*  602 */         ((JLabel)this.resultTable_[i][j]).setText("");
/*      */       }
/*      */     }
/*      */   }
/*      */   
/*      */   void setChecks(boolean paramBoolean) {
/*  608 */     for (int i = 0; i < TestedClass.classes.length; i++) {
/*  609 */       TestedClass.classes[i].setEnabled(new Boolean(paramBoolean));
/*  610 */       ((JCheckBox)this.resultTable_[(i + 1)][0]).setSelected(paramBoolean);
/*      */     }
/*      */   }
/*      */   
/*      */   public SynchronizationTimer()
/*      */   {
/*  616 */     for (int i = 0; i < this.threadInfo.length; i++) {
/*  617 */       this.threadInfo[i] = new ThreadInfo(nthreadsChoices[i]);
/*      */     }
/*      */   }
/*      */   
/*  621 */   final SynchronizedInt nextClassIdx_ = new SynchronizedInt(0);
/*  622 */   final SynchronizedInt nextThreadIdx_ = new SynchronizedInt(0);
/*      */   
/*      */   JPanel mainPanel()
/*      */   {
/*  626 */     new PrintStart();
/*  627 */     JPanel localJPanel1 = new JPanel();
/*  628 */     localJPanel1.setLayout(new GridLayout(5, 3));
/*      */     
/*  630 */     JPanel localJPanel2 = new JPanel();
/*  631 */     localJPanel2.setLayout(new GridLayout(1, 3));
/*      */     
/*  633 */     this.startstop_.addActionListener(new ActionListener() {
/*      */       public void actionPerformed(ActionEvent paramAnonymousActionEvent) {
/*  635 */         if (SynchronizationTimer.this.running_.get()) {
/*  636 */           SynchronizationTimer.this.cancel();
/*      */         } else {
/*      */           try {
/*  639 */             SynchronizationTimer.this.startTestSeries(new SynchronizationTimer.TestSeries(SynchronizationTimer.this));
/*      */           }
/*      */           catch (InterruptedException localInterruptedException) {
/*  642 */             SynchronizationTimer.this.endTestSeries();
/*      */           }
/*      */         }
/*      */       }
/*  646 */     });
/*  647 */     localJPanel1.add(this.startstop_);
/*      */     
/*  649 */     JPanel localJPanel3 = new JPanel();
/*  650 */     localJPanel3.setLayout(new GridLayout(1, 2));
/*      */     
/*  652 */     JButton localJButton1 = new JButton("Continue");
/*      */     
/*  654 */     localJButton1.addActionListener(new ActionListener() {
/*      */       public void actionPerformed(ActionEvent paramAnonymousActionEvent) {
/*  656 */         if (!SynchronizationTimer.this.running_.get()) {
/*      */           try {
/*  658 */             SynchronizationTimer.this.startTestSeries(new SynchronizationTimer.TestSeries(SynchronizationTimer.this, SynchronizationTimer.this.nextClassIdx_.get(), SynchronizationTimer.this.nextThreadIdx_.get()));
/*      */           }
/*      */           catch (InterruptedException localInterruptedException)
/*      */           {
/*  662 */             SynchronizationTimer.this.endTestSeries();
/*      */           }
/*      */         }
/*      */       }
/*  666 */     });
/*  667 */     localJPanel3.add(localJButton1);
/*      */     
/*  669 */     JButton localJButton2 = new JButton("Clear cells");
/*      */     
/*  671 */     localJButton2.addActionListener(new ActionListener() {
/*      */       public void actionPerformed(ActionEvent paramAnonymousActionEvent) {
/*  673 */         SynchronizationTimer.this.clearTable();
/*      */       }
/*      */       
/*  676 */     });
/*  677 */     localJPanel3.add(localJButton2);
/*      */     
/*  679 */     localJPanel1.add(localJPanel3);
/*      */     
/*  681 */     JPanel localJPanel4 = new JPanel();
/*  682 */     localJPanel4.setLayout(new GridLayout(1, 2));
/*      */     
/*  684 */     JButton localJButton3 = new JButton("All classes");
/*      */     
/*  686 */     localJButton3.addActionListener(new ActionListener() {
/*      */       public void actionPerformed(ActionEvent paramAnonymousActionEvent) {
/*  688 */         SynchronizationTimer.this.setChecks(true);
/*      */       }
/*      */       
/*  691 */     });
/*  692 */     localJPanel4.add(localJButton3);
/*      */     
/*      */ 
/*  695 */     JButton localJButton4 = new JButton("No classes");
/*      */     
/*  697 */     localJButton4.addActionListener(new ActionListener() {
/*      */       public void actionPerformed(ActionEvent paramAnonymousActionEvent) {
/*  699 */         SynchronizationTimer.this.setChecks(false);
/*      */       }
/*      */       
/*  702 */     });
/*  703 */     localJPanel4.add(localJButton4);
/*  704 */     localJPanel1.add(localJPanel4);
/*      */     
/*  706 */     JPanel localJPanel5 = new JPanel();
/*      */     
/*  708 */     localJPanel5.setLayout(new BoxLayout(localJPanel5, 0));
/*      */     
/*      */ 
/*  711 */     JCheckBox localJCheckBox = new JCheckBox("Console echo");
/*  712 */     localJCheckBox.addItemListener(new ItemListener() {
/*      */       public void itemStateChanged(ItemEvent paramAnonymousItemEvent) {
/*  714 */         SynchronizationTimer.this.echoToSystemOut.complement();
/*      */ 
/*      */       }
/*      */       
/*      */ 
/*  719 */     });
/*  720 */     JLabel localJLabel = new JLabel("Active threads:      0");
/*      */     
/*  722 */     localJPanel5.add(localJLabel);
/*  723 */     localJPanel5.add(localJCheckBox);
/*      */     
/*  725 */     localJPanel1.add(localJPanel5);
/*      */     
/*  727 */     localJPanel1.add(contentionBox());
/*  728 */     localJPanel1.add(itersBox());
/*  729 */     localJPanel1.add(cloopBox());
/*  730 */     localJPanel1.add(barrierBox());
/*  731 */     localJPanel1.add(exchangeBox());
/*  732 */     localJPanel1.add(biasBox());
/*  733 */     localJPanel1.add(capacityBox());
/*  734 */     localJPanel1.add(timeoutBox());
/*  735 */     localJPanel1.add(syncModePanel());
/*  736 */     localJPanel1.add(producerSyncModePanel());
/*  737 */     localJPanel1.add(consumerSyncModePanel());
/*      */     
/*  739 */     startPoolStatus(localJLabel);
/*      */     
/*  741 */     JPanel localJPanel6 = new JPanel();
/*  742 */     localJPanel6.setLayout(new BoxLayout(localJPanel6, 1));
/*      */     
/*  744 */     JPanel localJPanel7 = resultPanel();
/*      */     
/*  746 */     localJPanel6.add(localJPanel7);
/*  747 */     localJPanel6.add(localJPanel1);
/*  748 */     return localJPanel6;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */   JComboBox syncModePanel()
/*      */   {
/*  755 */     JComboBox localJComboBox = new JComboBox();
/*      */     
/*  757 */     for (int i = 0; i < syncModes.length; i++) {
/*  758 */       String str = "Locks: " + modeToString(syncModes[i]);
/*  759 */       localJComboBox.addItem(str);
/*      */     }
/*  761 */     localJComboBox.addItemListener(new ItemListener() {
/*      */       public void itemStateChanged(ItemEvent paramAnonymousItemEvent) {
/*  763 */         JComboBox localJComboBox = (JComboBox)paramAnonymousItemEvent.getItemSelectable();
/*  764 */         int i = localJComboBox.getSelectedIndex();
/*  765 */         RNG.syncMode.set(SynchronizationTimer.syncModes[i]);
/*      */       }
/*      */       
/*  768 */     });
/*  769 */     RNG.syncMode.set(syncModes[0]);
/*  770 */     localJComboBox.setSelectedIndex(0);
/*  771 */     return localJComboBox;
/*      */   }
/*      */   
/*      */   JComboBox producerSyncModePanel() {
/*  775 */     JComboBox localJComboBox = new JComboBox();
/*      */     
/*  777 */     for (int i = 0; i < syncModes.length; i++) {
/*  778 */       String str = "Producers: " + modeToString(syncModes[i]);
/*  779 */       localJComboBox.addItem(str);
/*      */     }
/*  781 */     localJComboBox.addItemListener(new ItemListener() {
/*      */       public void itemStateChanged(ItemEvent paramAnonymousItemEvent) {
/*  783 */         JComboBox localJComboBox = (JComboBox)paramAnonymousItemEvent.getItemSelectable();
/*  784 */         int i = localJComboBox.getSelectedIndex();
/*  785 */         RNG.producerMode.set(SynchronizationTimer.syncModes[i]);
/*      */       }
/*      */       
/*  788 */     });
/*  789 */     RNG.producerMode.set(syncModes[0]);
/*  790 */     localJComboBox.setSelectedIndex(0);
/*  791 */     return localJComboBox;
/*      */   }
/*      */   
/*      */   JComboBox consumerSyncModePanel() {
/*  795 */     JComboBox localJComboBox = new JComboBox();
/*      */     
/*  797 */     for (int i = 0; i < syncModes.length; i++) {
/*  798 */       String str = "Consumers: " + modeToString(syncModes[i]);
/*  799 */       localJComboBox.addItem(str);
/*      */     }
/*  801 */     localJComboBox.addItemListener(new ItemListener() {
/*      */       public void itemStateChanged(ItemEvent paramAnonymousItemEvent) {
/*  803 */         JComboBox localJComboBox = (JComboBox)paramAnonymousItemEvent.getItemSelectable();
/*  804 */         int i = localJComboBox.getSelectedIndex();
/*  805 */         RNG.consumerMode.set(SynchronizationTimer.syncModes[i]);
/*      */       }
/*      */       
/*  808 */     });
/*  809 */     RNG.consumerMode.set(syncModes[0]);
/*  810 */     localJComboBox.setSelectedIndex(0);
/*  811 */     return localJComboBox;
/*      */   }
/*      */   
/*      */ 
/*      */   JComboBox contentionBox()
/*      */   {
/*  817 */     Fraction[] arrayOfFraction = { new Fraction(0L, 1L), new Fraction(1L, 16L), new Fraction(1L, 8L), new Fraction(1L, 4L), new Fraction(1L, 2L), new Fraction(1L, 1L) };
/*      */     
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*  826 */     JComboBox localJComboBox = new JComboBox();
/*      */     
/*  828 */     for (int i = 0; i < arrayOfFraction.length; i++) {
/*  829 */       String str = arrayOfFraction[i].asDouble() * 100.0D + "% contention/sharing";
/*      */       
/*  831 */       localJComboBox.addItem(str);
/*      */     }
/*  833 */     localJComboBox.addItemListener(new ItemListener() { private final Fraction[] val$contentionChoices;
/*      */       
/*  835 */       public void itemStateChanged(ItemEvent paramAnonymousItemEvent) { JComboBox localJComboBox = (JComboBox)paramAnonymousItemEvent.getItemSelectable();
/*  836 */         int i = localJComboBox.getSelectedIndex();
/*  837 */         SynchronizationTimer.this.contention_.set(this.val$contentionChoices[i]);
/*      */       }
/*      */       
/*  840 */     });
/*  841 */     this.contention_.set(arrayOfFraction[3]);
/*  842 */     localJComboBox.setSelectedIndex(3);
/*  843 */     return localJComboBox;
/*      */   }
/*      */   
/*      */   JComboBox itersBox() {
/*  847 */     int[] arrayOfInt = { 1, 16, 256, 1024, 2048, 4096, 8192, 16384, 32768, 65536, 131072, 262144, 524288, 1048576 };
/*      */     
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*  864 */     JComboBox localJComboBox = new JComboBox();
/*      */     
/*  866 */     for (int i = 0; i < arrayOfInt.length; i++) {
/*  867 */       String str = p2ToString(arrayOfInt[i]) + " calls per thread per test";
/*      */       
/*  869 */       localJComboBox.addItem(str);
/*      */     }
/*  871 */     localJComboBox.addItemListener(new ItemListener() { private final int[] val$loopsPerTestChoices;
/*      */       
/*  873 */       public void itemStateChanged(ItemEvent paramAnonymousItemEvent) { JComboBox localJComboBox = (JComboBox)paramAnonymousItemEvent.getItemSelectable();
/*  874 */         int i = localJComboBox.getSelectedIndex();
/*  875 */         SynchronizationTimer.this.loopsPerTest_.set(this.val$loopsPerTestChoices[i]);
/*      */       }
/*      */       
/*  878 */     });
/*  879 */     this.loopsPerTest_.set(arrayOfInt[8]);
/*  880 */     localJComboBox.setSelectedIndex(8);
/*      */     
/*  882 */     return localJComboBox;
/*      */   }
/*      */   
/*      */   JComboBox cloopBox() {
/*  886 */     int[] arrayOfInt = { 1, 2, 4, 8, 16, 32, 64, 128, 256, 512, 1024, 2048, 4096, 8192, 16384, 32768, 65536 };
/*      */     
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*  906 */     JComboBox localJComboBox = new JComboBox();
/*      */     
/*  908 */     for (int i = 0; i < arrayOfInt.length; i++) {
/*  909 */       String str = p2ToString(arrayOfInt[i]) + " computations per call";
/*      */       
/*  911 */       localJComboBox.addItem(str);
/*      */     }
/*  913 */     localJComboBox.addItemListener(new ItemListener() { private final int[] val$computationsPerCallChoices;
/*      */       
/*  915 */       public void itemStateChanged(ItemEvent paramAnonymousItemEvent) { JComboBox localJComboBox = (JComboBox)paramAnonymousItemEvent.getItemSelectable();
/*  916 */         int i = localJComboBox.getSelectedIndex();
/*  917 */         RNG.computeLoops.set(this.val$computationsPerCallChoices[i]);
/*      */       }
/*      */       
/*  920 */     });
/*  921 */     RNG.computeLoops.set(arrayOfInt[3]);
/*  922 */     localJComboBox.setSelectedIndex(3);
/*  923 */     return localJComboBox;
/*      */   }
/*      */   
/*      */   JComboBox barrierBox() {
/*  927 */     int[] arrayOfInt = { 1, 2, 4, 8, 16, 32, 64, 128, 256, 512, 1024, 2048, 4096, 8192, 16384, 32768, 65536, 131072, 262144, 524288, 1048576 };
/*      */     
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*  951 */     JComboBox localJComboBox = new JComboBox();
/*      */     
/*  953 */     for (int i = 0; i < arrayOfInt.length; i++) {
/*  954 */       String str = p2ToString(arrayOfInt[i]) + " iterations per barrier";
/*      */       
/*  956 */       localJComboBox.addItem(str);
/*      */     }
/*  958 */     localJComboBox.addItemListener(new ItemListener() { private final int[] val$itersPerBarrierChoices;
/*      */       
/*  960 */       public void itemStateChanged(ItemEvent paramAnonymousItemEvent) { JComboBox localJComboBox = (JComboBox)paramAnonymousItemEvent.getItemSelectable();
/*  961 */         int i = localJComboBox.getSelectedIndex();
/*  962 */         RNG.itersPerBarrier.set(this.val$itersPerBarrierChoices[i]);
/*      */       }
/*      */       
/*  965 */     });
/*  966 */     RNG.itersPerBarrier.set(arrayOfInt[13]);
/*  967 */     localJComboBox.setSelectedIndex(13);
/*      */     
/*      */ 
/*      */ 
/*      */ 
/*  972 */     return localJComboBox;
/*      */   }
/*      */   
/*      */   JComboBox exchangeBox() {
/*  976 */     int[] arrayOfInt = { 1, 2, 4, 8, 16, 32, 64, 128, 256, 512, 1024 };
/*      */     
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*  990 */     JComboBox localJComboBox = new JComboBox();
/*      */     
/*  992 */     for (int i = 0; i < arrayOfInt.length; i++) {
/*  993 */       String str = p2ToString(arrayOfInt[i]) + " max threads per barrier";
/*      */       
/*  995 */       localJComboBox.addItem(str);
/*      */     }
/*  997 */     localJComboBox.addItemListener(new ItemListener() { private final int[] val$exchangerChoices;
/*      */       
/*  999 */       public void itemStateChanged(ItemEvent paramAnonymousItemEvent) { JComboBox localJComboBox = (JComboBox)paramAnonymousItemEvent.getItemSelectable();
/* 1000 */         int i = localJComboBox.getSelectedIndex();
/* 1001 */         RNG.exchangeParties.set(this.val$exchangerChoices[i]);
/*      */       }
/*      */       
/* 1004 */     });
/* 1005 */     RNG.exchangeParties.set(arrayOfInt[1]);
/* 1006 */     localJComboBox.setSelectedIndex(1);
/* 1007 */     return localJComboBox;
/*      */   }
/*      */   
/*      */   JComboBox biasBox() {
/* 1011 */     int[] arrayOfInt = { -1, 0, 1 };
/*      */     
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/* 1018 */     JComboBox localJComboBox = new JComboBox();
/*      */     
/* 1020 */     for (int i = 0; i < arrayOfInt.length; i++) {
/* 1021 */       String str = biasToString(arrayOfInt[i]);
/* 1022 */       localJComboBox.addItem(str);
/*      */     }
/* 1024 */     localJComboBox.addItemListener(new ItemListener() { private final int[] val$biasChoices;
/*      */       
/* 1026 */       public void itemStateChanged(ItemEvent paramAnonymousItemEvent) { JComboBox localJComboBox = (JComboBox)paramAnonymousItemEvent.getItemSelectable();
/* 1027 */         int i = localJComboBox.getSelectedIndex();
/* 1028 */         RNG.bias.set(this.val$biasChoices[i]);
/*      */       }
/*      */       
/* 1031 */     });
/* 1032 */     RNG.bias.set(arrayOfInt[1]);
/* 1033 */     localJComboBox.setSelectedIndex(1);
/* 1034 */     return localJComboBox;
/*      */   }
/*      */   
/*      */   JComboBox capacityBox()
/*      */   {
/* 1039 */     int[] arrayOfInt = { 1, 4, 64, 256, 1024, 4096, 16384, 65536, 262144, 1048576 };
/*      */     
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/* 1052 */     JComboBox localJComboBox = new JComboBox();
/*      */     
/* 1054 */     for (int i = 0; i < arrayOfInt.length; i++) {
/* 1055 */       String str = p2ToString(arrayOfInt[i]) + " element bounded buffers";
/*      */       
/* 1057 */       localJComboBox.addItem(str);
/*      */     }
/* 1059 */     localJComboBox.addItemListener(new ItemListener() { private final int[] val$bufferCapacityChoices;
/*      */       
/* 1061 */       public void itemStateChanged(ItemEvent paramAnonymousItemEvent) { JComboBox localJComboBox = (JComboBox)paramAnonymousItemEvent.getItemSelectable();
/* 1062 */         int i = localJComboBox.getSelectedIndex();
/* 1063 */         DefaultChannelCapacity.set(this.val$bufferCapacityChoices[i]);
/*      */       }
/*      */       
/*      */ 
/* 1067 */     });
/* 1068 */     DefaultChannelCapacity.set(arrayOfInt[3]);
/* 1069 */     localJComboBox.setSelectedIndex(3);
/* 1070 */     return localJComboBox;
/*      */   }
/*      */   
/*      */ 
/*      */   JComboBox timeoutBox()
/*      */   {
/* 1076 */     long[] arrayOfLong = { 0L, 1L, 10L, 100L, 1000L, 10000L, 100000L };
/*      */     
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/* 1087 */     JComboBox localJComboBox = new JComboBox();
/*      */     
/* 1089 */     for (int i = 0; i < arrayOfLong.length; i++) {
/* 1090 */       String str = arrayOfLong[i] + " msec timeouts";
/* 1091 */       localJComboBox.addItem(str);
/*      */     }
/* 1093 */     localJComboBox.addItemListener(new ItemListener() { private final long[] val$timeoutChoices;
/*      */       
/* 1095 */       public void itemStateChanged(ItemEvent paramAnonymousItemEvent) { JComboBox localJComboBox = (JComboBox)paramAnonymousItemEvent.getItemSelectable();
/* 1096 */         int i = localJComboBox.getSelectedIndex();
/* 1097 */         RNG.timeout.set(this.val$timeoutChoices[i]);
/*      */       }
/*      */       
/* 1100 */     });
/* 1101 */     RNG.timeout.set(arrayOfLong[3]);
/* 1102 */     localJComboBox.setSelectedIndex(3);
/* 1103 */     return localJComboBox;
/*      */   }
/*      */   
/* 1106 */   ClockDaemon timeDaemon = new ClockDaemon();
/*      */   
/*      */   void startPoolStatus(JLabel paramJLabel) {
/* 1109 */     Runnable local22 = new Runnable() { int lastps;
/*      */       private final JLabel val$status;
/*      */       
/* 1112 */       public void run() { int i = Threads.activeThreads.get();
/* 1113 */         if (this.lastps != i) {
/* 1114 */           this.lastps = i;
/* 1115 */           SwingUtilities.invokeLater(new SynchronizationTimer.23(this, i));
/*      */         }
/*      */         
/*      */       }
/*      */       
/*      */ 
/* 1121 */     };
/* 1122 */     this.timeDaemon.executePeriodically(250L, local22, false);
/*      */   }
/*      */   
/* 1125 */   private final SynchronizedRef contention_ = new SynchronizedRef(null);
/* 1126 */   private final SynchronizedInt loopsPerTest_ = new SynchronizedInt(0);
/*      */   
/* 1128 */   private final SynchronizedBoolean echoToSystemOut = new SynchronizedBoolean(false);
/*      */   
/*      */ 
/*      */ 
/* 1132 */   private final JButton startstop_ = new JButton("Start");
/*      */   
/* 1134 */   private WaitableInt testNumber_ = new WaitableInt(1);
/*      */   
/*      */   private void runOneTest(Runnable paramRunnable) throws InterruptedException {
/* 1137 */     int i = this.testNumber_.get();
/* 1138 */     Threads.pool.execute(paramRunnable);
/* 1139 */     this.testNumber_.whenNotEqual(i, null);
/*      */   }
/*      */   
/*      */   private void endOneTest() {
/* 1143 */     this.testNumber_.increment();
/*      */   }
/*      */   
/* 1146 */   private SynchronizedBoolean running_ = new SynchronizedBoolean(false);
/*      */   
/*      */   void cancel()
/*      */   {
/* 1150 */     synchronized (RNG.constructionLock) {
/*      */       try {
/* 1152 */         Threads.pool.interruptAll();
/*      */       }
/*      */       catch (Exception localException) {
/* 1155 */         System.out.println("\nException during cancel:\n" + localException);
/* 1156 */         return;
/*      */       }
/*      */     }
/*      */   }
/*      */   
/*      */   void startTestSeries(Runnable paramRunnable) throws InterruptedException
/*      */   {
/* 1163 */     this.running_.set(true);
/* 1164 */     this.startstop_.setText("Stop");
/* 1165 */     Threads.pool.execute(paramRunnable);
/*      */   }
/*      */   
/*      */   class PrintStart implements Runnable {
/*      */     PrintStart() {}
/*      */     
/* 1171 */     public void run() { SynchronizationTimer.this.startstop_.setText("Start"); }
/*      */   }
/*      */   
/*      */ 
/*      */   void endTestSeries()
/*      */   {
/* 1177 */     this.running_.set(false);
/* 1178 */     SwingUtilities.invokeLater(new PrintStart());
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */   class TestSeries
/*      */     implements Runnable
/*      */   {
/*      */     final int firstclass;
/*      */     
/*      */ 
/*      */     final int firstnthreads;
/*      */     
/*      */ 
/*      */ 
/*      */     TestSeries()
/*      */     {
/* 1196 */       this.firstclass = 0;
/* 1197 */       this.firstnthreads = 0;
/*      */     }
/*      */     
/*      */     TestSeries(int paramInt1, int paramInt2) {
/* 1201 */       this.firstclass = paramInt1;
/* 1202 */       this.firstnthreads = paramInt2;
/*      */     }
/*      */     
/*      */     public void run() {
/* 1206 */       Thread.currentThread().setPriority(5);
/*      */       try
/*      */       {
/* 1209 */         int i = this.firstnthreads;
/* 1210 */         int j = this.firstclass;
/*      */         
/* 1212 */         if ((i < SynchronizationTimer.nthreadsChoices.length) && (j < SynchronizationTimer.TestedClass.classes.length))
/*      */         {
/*      */ 
/*      */ 
/*      */ 
/*      */           for (;;)
/*      */           {
/*      */ 
/*      */ 
/*      */ 
/* 1222 */             if (SynchronizationTimer.this.threadEnabled(i))
/*      */             {
/* 1224 */               SynchronizationTimer.TestedClass localTestedClass = SynchronizationTimer.TestedClass.classes[j];
/*      */               
/* 1226 */               int k = SynchronizationTimer.nthreadsChoices[i];
/* 1227 */               int m = SynchronizationTimer.this.loopsPerTest_.get();
/* 1228 */               Fraction localFraction = (Fraction)SynchronizationTimer.this.contention_.get();
/*      */               
/* 1230 */               if (localTestedClass.isEnabled(k, localFraction))
/*      */               {
/* 1232 */                 SynchronizationTimer.this.runOneTest(new SynchronizationTimer.OneTest(SynchronizationTimer.this, j, i));
/*      */               }
/*      */             }
/*      */             
/* 1236 */             j++; if (j >= SynchronizationTimer.TestedClass.classes.length) {
/* 1237 */               j = 0;
/* 1238 */               i++; if (i >= SynchronizationTimer.nthreadsChoices.length) {
/*      */                 break;
/*      */               }
/*      */             }
/* 1242 */             SynchronizationTimer.this.nextClassIdx_.set(j);
/* 1243 */             SynchronizationTimer.this.nextThreadIdx_.set(i);
/*      */           }
/*      */           
/*      */         }
/*      */       }
/*      */       catch (InterruptedException localInterruptedException)
/*      */       {
/* 1250 */         Thread.currentThread().interrupt();
/*      */       }
/*      */       finally {
/* 1253 */         SynchronizationTimer.this.endTestSeries();
/*      */       }
/*      */     }
/*      */   }
/*      */   
/*      */   static class BarrierTimer implements Runnable {
/* 1259 */     private long startTime_ = 0L;
/* 1260 */     private long endTime_ = 0L;
/*      */     
/*      */     public synchronized long getTime() {
/* 1263 */       return this.endTime_ - this.startTime_;
/*      */     }
/*      */     
/*      */     public synchronized void run() {
/* 1267 */       long l = System.currentTimeMillis();
/* 1268 */       if (this.startTime_ == 0L) {
/* 1269 */         this.startTime_ = l;
/*      */       } else
/* 1271 */         this.endTime_ = l;
/*      */     }
/*      */   }
/*      */   
/*      */   class OneTest implements Runnable {
/*      */     final int clsIdx;
/*      */     final int nthreadsIdx;
/*      */     
/*      */     OneTest(int paramInt1, int paramInt2) {
/* 1280 */       this.clsIdx = paramInt1;
/* 1281 */       this.nthreadsIdx = paramInt2;
/*      */     }
/*      */     
/*      */     public void run() {
/* 1285 */       Thread.currentThread().setPriority(2);
/*      */       
/* 1287 */       boolean bool1 = false;
/*      */       
/* 1289 */       SynchronizationTimer.TestedClass localTestedClass = SynchronizationTimer.TestedClass.classes[this.clsIdx];
/*      */       
/* 1291 */       JLabel localJLabel = (JLabel)SynchronizationTimer.this.resultTable_[(this.clsIdx + 1)][(this.nthreadsIdx + 1)];
/* 1292 */       Color localColor = localJLabel.getForeground();
/*      */       
/*      */ 
/*      */       try
/*      */       {
/* 1297 */         if (Thread.interrupted()) return;
/* 1298 */         if (!SynchronizationTimer.this.threadEnabled(this.nthreadsIdx)) { return;
/*      */         }
/* 1300 */         int i = SynchronizationTimer.nthreadsChoices[this.nthreadsIdx];
/* 1301 */         int j = SynchronizationTimer.this.loopsPerTest_.get();
/* 1302 */         Fraction localFraction = (Fraction)SynchronizationTimer.this.contention_.get();
/*      */         
/* 1304 */         if (!localTestedClass.isEnabled(i, localFraction)) { return;
/*      */         }
/* 1306 */         SynchronizationTimer.BarrierTimer localBarrierTimer = new SynchronizationTimer.BarrierTimer();
/* 1307 */         CyclicBarrier localCyclicBarrier = new CyclicBarrier(i + 1, localBarrierTimer);
/*      */         
/* 1309 */         Class localClass1 = localTestedClass.cls;
/* 1310 */         Class localClass2 = localTestedClass.buffCls;
/*      */         try
/*      */         {
/* 1313 */           SwingUtilities.invokeAndWait(new SynchronizationTimer.24(this, localJLabel));
/*      */ 
/*      */ 
/*      */ 
/*      */         }
/*      */         catch (InvocationTargetException localInvocationTargetException)
/*      */         {
/*      */ 
/*      */ 
/* 1322 */           localInvocationTargetException.printStackTrace();
/* 1323 */           System.exit(-1);
/*      */         }
/* 1325 */         synchronized (RNG.constructionLock) {
/* 1326 */           RNG.reset(i);
/*      */           Object localObject2;
/* 1328 */           Object localObject3; Object localObject4; if (localClass2 == null) {
/* 1329 */             localObject2 = (RNG)localClass1.newInstance();
/* 1330 */             for (int k = 0; k < i; k++) {
/* 1331 */               localObject3 = (RNG)localClass1.newInstance();
/* 1332 */               localObject4 = new TestLoop((RNG)localObject2, (RNG)localObject3, localFraction, j, localCyclicBarrier);
/* 1333 */               Threads.pool.execute(((TestLoop)localObject4).testLoop());
/*      */             }
/*      */           }
/*      */           else {
/* 1337 */             localObject2 = (Channel)localClass2.newInstance();
/* 1338 */             if (i == 1) {
/* 1339 */               ChanRNG localChanRNG = (ChanRNG)localClass1.newInstance();
/* 1340 */               localChanRNG.setSingle(true);
/* 1341 */               localObject3 = new PCTestLoop(localChanRNG.getDelegate(), localChanRNG, localFraction, j, localCyclicBarrier, (Channel)localObject2, (Channel)localObject2);
/*      */               
/*      */ 
/* 1344 */               Threads.pool.execute(((PCTestLoop)localObject3).testLoop(true));
/*      */             } else {
/* 1346 */               if (i % 2 != 0) {
/* 1347 */                 throw new Error("Must have even number of threads!");
/*      */               }
/* 1349 */               int m = i / 2;
/*      */               
/* 1351 */               for (int n = 0; n < m; n++) {
/* 1352 */                 localObject4 = (ChanRNG)localClass1.newInstance();
/* 1353 */                 ((ChanRNG)localObject4).setSingle(false);
/* 1354 */                 Channel localChannel = (Channel)localClass2.newInstance();
/*      */                 
/* 1356 */                 PCTestLoop localPCTestLoop = new PCTestLoop(((DelegatedRNG)localObject4).getDelegate(), (RNG)localObject4, localFraction, j, localCyclicBarrier, (Channel)localObject2, localChannel);
/*      */                 
/*      */ 
/*      */ 
/* 1360 */                 Threads.pool.execute(localPCTestLoop.testLoop(false));
/* 1361 */                 Threads.pool.execute(localPCTestLoop.testLoop(true));
/*      */               }
/*      */             }
/*      */           }
/*      */           
/*      */ 
/* 1367 */           if (SynchronizationTimer.this.echoToSystemOut.get()) {
/* 1368 */             System.out.print(localTestedClass.name + " " + i + "T " + localFraction + "S " + RNG.computeLoops.get() + "I " + RNG.syncMode.get() + "Lm " + RNG.timeout.get() + "TO " + RNG.producerMode.get() + "Pm " + RNG.consumerMode.get() + "Cm " + RNG.bias.get() + "B " + DefaultChannelCapacity.get() + "C " + RNG.exchangeParties.get() + "Xp " + RNG.itersPerBarrier.get() + "Ib : ");
/*      */           }
/*      */         }
/*      */         
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/* 1389 */         localCyclicBarrier.barrier();
/*      */         
/* 1391 */         localCyclicBarrier.barrier();
/*      */         
/* 1393 */         long l1 = localBarrierTimer.getTime();
/* 1394 */         long l2 = i * j;
/* 1395 */         double d = l1 * 1000.0D * 10.0D / l2;
/* 1396 */         long l3 = Math.round(d);
/*      */         
/* 1398 */         SynchronizationTimer.this.setTime(l3, this.clsIdx, this.nthreadsIdx);
/*      */         
/* 1400 */         if (SynchronizationTimer.this.echoToSystemOut.get()) {
/* 1401 */           System.out.println(SynchronizationTimer.formatTime(l3, true));
/*      */         }
/*      */       }
/*      */       catch (BrokenBarrierException localBrokenBarrierException)
/*      */       {
/* 1406 */         bool1 = true;
/*      */       }
/*      */       catch (InterruptedException localInterruptedException) {
/* 1409 */         bool1 = true;
/* 1410 */         Thread.currentThread().interrupt();
/*      */       }
/*      */       catch (Exception localException) {
/* 1413 */         localException.printStackTrace();
/* 1414 */         System.out.println("Construction Exception?");
/* 1415 */         System.exit(-1);
/*      */       }
/*      */       finally {
/* 1418 */         boolean bool2 = bool1;
/* 1419 */         SwingUtilities.invokeLater(new SynchronizationTimer.25(this, bool2, localJLabel, localColor));
/*      */         
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/* 1427 */         Thread.currentThread().setPriority(5);
/* 1428 */         SynchronizationTimer.this.endOneTest();
/*      */       }
/*      */     }
/*      */   }
/*      */ }


/* Location:              /home/menick/Scrivania/ProcessMining/CNMining/CNMining.jar!/edu/oswego/cs/dl/util/concurrent/misc/SynchronizationTimer.class
 * Java compiler version: 1 (45.3)
 * JD-Core Version:       0.7.1
 */