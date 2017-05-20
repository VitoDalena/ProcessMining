/*     */ package flanagan.plot;
/*     */ 
/*     */ import java.awt.Color;
/*     */ import java.awt.Container;
/*     */ import java.awt.Dimension;
/*     */ import java.awt.Graphics;
/*     */ import java.io.Serializable;
/*     */ import javax.swing.JFrame;
/*     */ import javax.swing.JOptionPane;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class PlotGraph
/*     */   extends Plot
/*     */   implements Serializable
/*     */ {
/*     */   protected static final long serialVersionUID = 1L;
/*  50 */   protected int graphWidth = 800;
/*  51 */   protected int graphHeight = 600;
/*  52 */   protected int closeChoice = 1;
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*  57 */   protected JFrame window = new JFrame("Michael T Flanagan's plotting program - PlotGraph");
/*     */   
/*     */ 
/*     */   public PlotGraph(double[][] data)
/*     */   {
/*  62 */     super(data);
/*     */   }
/*     */   
/*     */ 
/*     */   public PlotGraph(double[] xData, double[] yData)
/*     */   {
/*  68 */     super(xData, yData);
/*     */   }
/*     */   
/*     */ 
/*     */   public void rescaleY(double yScaleFactor)
/*     */   {
/*  74 */     this.graphHeight = ((int)Math.round(this.graphHeight * yScaleFactor));
/*  75 */     this.yLen = ((int)Math.round(this.yLen * yScaleFactor));
/*  76 */     this.yTop = ((int)Math.round(this.yTop * yScaleFactor));
/*  77 */     this.yBot = (this.yTop + this.yLen);
/*     */   }
/*     */   
/*     */ 
/*     */   public void rescaleX(double xScaleFactor)
/*     */   {
/*  83 */     this.graphWidth = ((int)Math.round(this.graphWidth * xScaleFactor));
/*  84 */     this.xLen = ((int)Math.round(this.xLen * xScaleFactor));
/*  85 */     this.xBot = ((int)Math.round(this.xBot * xScaleFactor));
/*  86 */     this.xTop = (this.xBot + this.xLen);
/*     */   }
/*     */   
/*     */   public int getGraphWidth()
/*     */   {
/*  91 */     return this.graphWidth;
/*     */   }
/*     */   
/*     */   public int getGraphHeight()
/*     */   {
/*  96 */     return this.graphHeight;
/*     */   }
/*     */   
/*     */   public void setGraphHeight(int graphHeight)
/*     */   {
/* 101 */     this.graphHeight = graphHeight;
/*     */   }
/*     */   
/*     */   public void setGraphWidth(int graphWidth)
/*     */   {
/* 106 */     this.graphWidth = graphWidth;
/*     */   }
/*     */   
/*     */   public int getCloseChoice()
/*     */   {
/* 111 */     return this.closeChoice;
/*     */   }
/*     */   
/*     */   public void setCloseChoice(int choice)
/*     */   {
/* 116 */     this.closeChoice = choice;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */   public void paint(Graphics g)
/*     */   {
/* 123 */     double newGraphWidth = getSize().width;
/* 124 */     double newGraphHeight = getSize().height;
/* 125 */     double xScale = newGraphWidth / this.graphWidth;
/* 126 */     double yScale = newGraphHeight / this.graphHeight;
/* 127 */     rescaleX(xScale);
/* 128 */     rescaleY(yScale);
/*     */     
/*     */ 
/* 131 */     graph(g);
/*     */   }
/*     */   
/*     */ 
/*     */   public void plot()
/*     */   {
/* 137 */     setSize(this.graphWidth, this.graphHeight);
/*     */     
/*     */ 
/* 140 */     this.window.getContentPane().setBackground(Color.white);
/*     */     
/*     */ 
/* 143 */     if (this.closeChoice == 1) {
/* 144 */       this.window.setDefaultCloseOperation(3);
/*     */     }
/*     */     else {
/* 147 */       this.window.setDefaultCloseOperation(1);
/*     */     }
/*     */     
/*     */ 
/* 151 */     this.window.getContentPane().add("Center", this);
/*     */     
/*     */ 
/* 154 */     this.window.pack();
/* 155 */     this.window.setResizable(true);
/* 156 */     this.window.toFront();
/*     */     
/*     */ 
/* 159 */     this.window.setVisible(true);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */   public void endProgram()
/*     */   {
/* 166 */     int ans = JOptionPane.showConfirmDialog(null, "Do you wish to end the program\nThis will also close the graph window or windows", "End Program", 0, 3);
/* 167 */     if (ans == 0) {
/* 168 */       System.exit(0);
/*     */     }
/*     */     else {
/* 171 */       String message = "Now you must press the appropriate escape key/s, e.g. Ctrl C, to exit this program\n";
/* 172 */       if (this.closeChoice == 1) message = message + "or close a graph window";
/* 173 */       JOptionPane.showMessageDialog(null, message);
/*     */     }
/*     */   }
/*     */   
/*     */   public static long getSerialVersionUID()
/*     */   {
/* 179 */     return 1L;
/*     */   }
/*     */ }


/* Location:              /home/menick/Scrivania/ProcessMining/CNMining/CNMining.jar!/flanagan/plot/PlotGraph.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       0.7.1
 */