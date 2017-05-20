/*     */ package au.id.dodson.FilterableSortableTable;
/*     */ 
/*     */ import java.awt.BorderLayout;
/*     */ import java.awt.Container;
/*     */ import java.awt.Dimension;
/*     */ import java.awt.Toolkit;
/*     */ import java.awt.event.KeyAdapter;
/*     */ import java.awt.event.KeyEvent;
/*     */ import java.util.Random;
/*     */ import java.util.Vector;
/*     */ import javax.swing.JFrame;
/*     */ import javax.swing.JLabel;
/*     */ import javax.swing.JOptionPane;
/*     */ import javax.swing.JPanel;
/*     */ import javax.swing.JScrollPane;
/*     */ import javax.swing.JTextField;
/*     */ import javax.swing.table.DefaultTableModel;
/*     */ import javax.swing.table.TableModel;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class FilterableSortableTablePanel
/*     */   extends JPanel
/*     */ {
/*  46 */   private JLabel filterLabel = null;
/*  47 */   private JTextField filterField = new JTextField(20);
/*  48 */   private JPanel filterPanel = null;
/*  49 */   private JPanel scrollTablePanel = null;
/*  50 */   private FilterSortTable filterTable = new FilterSortTable();
/*  51 */   private JLabel infoLabel = new JLabel("www.filerename.co.uk");
/*  52 */   private static int filterPosition = 1;
/*  53 */   private static int width = 0;
/*  54 */   private static int height = 0;
/*     */   
/*     */ 
/*     */   private static final String version = "1.1";
/*     */   
/*     */ 
/*     */ 
/*     */   public FilterableSortableTablePanel(TableModel model)
/*     */   {
/*  63 */     setLayout(new BorderLayout());
/*     */     
/*  65 */     switch (filterPosition) {
/*     */     case 1: 
/*  67 */       add(getFilterPanel(), "North");
/*  68 */       add(getScrollTablePanel(), "Center");
/*  69 */       add(this.infoLabel, "South");
/*  70 */       break;
/*     */     case -1: 
/*  72 */       add(getScrollTablePanel(), "Center");
/*  73 */       break;
/*     */     default: 
/*  75 */       add(this.infoLabel, "North");
/*  76 */       add(getScrollTablePanel(), "Center");
/*  77 */       add(getFilterPanel(), "South");
/*     */     }
/*  79 */     getTable().setTableModel(model);
/*     */   }
/*     */   
/*     */   private JPanel getScrollTablePanel() {
/*  83 */     this.scrollTablePanel = new JPanel(new BorderLayout());
/*  84 */     JScrollPane scrollPane = new JScrollPane(this.filterTable);
/*  85 */     this.scrollTablePanel.add(scrollPane);
/*     */     
/*  87 */     if ((width > 0) && (height > 0)) {
/*  88 */       this.scrollTablePanel.setPreferredSize(new Dimension(width, height));
/*     */     }
/*     */     
/*  91 */     return this.scrollTablePanel;
/*     */   }
/*     */   
/*     */   private JPanel getFilterPanel() {
/*  95 */     this.filterPanel = new JPanel(new BorderLayout());
/*     */     
/*  97 */     this.filterLabel = new JLabel();
/*  98 */     this.filterLabel.setText("Filter Data ");
/*     */     
/* 100 */     this.filterField.addKeyListener(new KeyAdapter() {
/*     */       public void keyReleased(KeyEvent e) {
/* 102 */         FilterableSortableTablePanel.this.setFilter(FilterableSortableTablePanel.this.filterField.getText());
/*     */       }
/*     */       
/* 105 */     });
/* 106 */     Vector vy = new Vector();
/* 107 */     Vector vx = new Vector();
/* 108 */     vy.add(vx);
/*     */     
/* 110 */     vx.add(this.filterLabel);
/* 111 */     vx.add(this.filterField);
/*     */     
/* 113 */     this.filterPanel.add(GridBagUtils.layoutInGrid(vy));
/*     */     
/* 115 */     return this.filterPanel;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   public Vector getSelectedRow()
/*     */   {
/* 123 */     int selectedRow = getTable().getSelectedRow();
/* 124 */     if (selectedRow >= 0) {
/* 125 */       return getTable().getRow(selectedRow);
/*     */     }
/* 127 */     return null;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public void setFilter(String text)
/*     */   {
/* 136 */     this.filterField.setText(text);
/* 137 */     this.filterTable.setTableFilter(this.filterField.getText().toString().trim());
/* 138 */     setInfoText(this.filterTable.getRowCount() + " Rows Found");
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   public FilterSortTable getTable()
/*     */   {
/* 146 */     return this.filterTable;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   public void setInfoText(String text)
/*     */   {
/* 154 */     this.infoLabel.setText(text);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public static void main(String[] args)
/*     */   {
/* 163 */     final JFrame window = new JFrame();
/* 164 */     Vector columnNames = new Vector();
/* 165 */     Random rand = new Random();
/* 166 */     Vector iv = new Vector();
/*     */     
/* 168 */     for (int i = 0; i < 6; i++) {
/* 169 */       Vector jv = new Vector();
/* 170 */       columnNames.add("Col " + i);
/* 171 */       for (int j = 0; j < 6; j++) {
/* 172 */         String s = "Value " + rand.nextInt(10) + " " + rand.nextInt(10);
/* 173 */         jv.add(s);
/*     */       }
/* 175 */       iv.add(jv);
/*     */     }
/* 177 */     height = 200;
/* 178 */     width = 420;
/*     */     
/* 180 */     filterPosition = 1;
/*     */     
/* 182 */     DefaultTableModel model = new DefaultTableModel(iv, columnNames) {
/*     */       public boolean isCellEditable(int r, int c) {
/* 184 */         return false;
/*     */       }
/* 186 */     };
/* 187 */     FilterableSortableTablePanel panel = new FilterableSortableTablePanel(model);
/* 188 */     panel.getTable().addTableListener(new TableListenerInterface() {
/*     */       public void setRowAccepted(Vector isAccepted) {
/* 190 */         String text = "";
/* 191 */         for (int i = 0; i < isAccepted.size(); i++) {
/* 192 */           text = text + "Column " + this.val$panel.getTable().getColumnName(i) + " " + isAccepted.get(i) + "\n";
/*     */         }
/* 194 */         JOptionPane.showMessageDialog(window, "Selected row \n" + text);
/*     */       }
/* 196 */     });
/* 197 */     window.getContentPane().add(panel);
/* 198 */     window.setTitle("FilterSort v1.0");
/* 199 */     window.pack();
/* 200 */     window.setVisible(true);
/* 201 */     window.setDefaultCloseOperation(2);
/* 202 */     moveToCentre(window);
/*     */   }
/*     */   
/*     */   public static void moveToCentre(JFrame mainPanel) {
/* 206 */     Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
/* 207 */     Dimension frameSize = mainPanel.getSize();
/* 208 */     frameSize.height = (frameSize.height > screenSize.height ? screenSize.height : frameSize.height);
/*     */     
/* 210 */     frameSize.width = (frameSize.width > screenSize.width ? screenSize.width : frameSize.width);
/*     */     
/* 212 */     mainPanel.setLocation(screenSize.width / 2 - frameSize.width / 2, screenSize.height / 2 - frameSize.height / 2);
/*     */   }
/*     */ }


/* Location:              /home/menick/Scrivania/ProcessMining/CNMining/CNMining.jar!/au/id/dodson/FilterableSortableTable/FilterableSortableTablePanel.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       0.7.1
 */