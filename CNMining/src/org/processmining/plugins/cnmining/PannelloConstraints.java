/*     */ package org.processmining.plugins.cnmining;
/*     */ 
/*     */ import java.awt.Color;
/*     */ import java.awt.Font;
/*     */ import java.awt.event.ActionEvent;
/*     */ import java.awt.event.ActionListener;
/*     */ import java.io.File;
/*     */ import java.io.PrintStream;
/*     */ import javax.swing.GroupLayout;
/*     */ import javax.swing.GroupLayout.Alignment;
/*     */ import javax.swing.GroupLayout.ParallelGroup;
/*     */ import javax.swing.GroupLayout.SequentialGroup;
/*     */ import javax.swing.JButton;
/*     */ import javax.swing.JCheckBox;
/*     */ import javax.swing.JFileChooser;
/*     */ import javax.swing.JLabel;
/*     */ import javax.swing.JPanel;
import javax.swing.LayoutStyle;
/*     */ import javax.swing.LayoutStyle.ComponentPlacement;
/*     */ import javax.swing.filechooser.FileNameExtensionFilter;
/*     */ 
/*     */ public class PannelloConstraints
/*     */   extends JPanel
/*     */ {
/*  24 */   private boolean constraintsEnabled = false;
/*  25 */   private String filePath = "";
/*     */   
/*     */ 
/*     */ 
/*     */   public PannelloConstraints()
/*     */   {
/*  31 */     setBackground(Color.GRAY);
/*     */     
/*  33 */     final JFileChooser chooser = new JFileChooser(".");
/*  34 */     FileNameExtensionFilter filter = new FileNameExtensionFilter("Constraints", new String[] { "xml" });
/*  35 */     chooser.setFileFilter(filter);
/*     */     
/*  37 */     JLabel lblEnableConstraints = new JLabel("ENABLE CONSTRAINTS");
/*  38 */     lblEnableConstraints.setFont(new Font("Lucida Grande", 1, 12));
/*     */     
/*  40 */     final JCheckBox checkBox = new JCheckBox("");
/*     */     
/*  42 */     final JButton btnSelect = new JButton("Select input file");
/*  43 */     btnSelect.setEnabled(false);
/*     */     
/*  45 */     checkBox.addActionListener(new ActionListener() {
/*     */       public void actionPerformed(ActionEvent e) {
/*  47 */         if (checkBox.isSelected()) {
/*  48 */           btnSelect.setEnabled(true);
/*  49 */           PannelloConstraints.this.constraintsEnabled = true;
/*     */         }
/*     */         else {
/*  52 */           btnSelect.setEnabled(false);
/*  53 */           PannelloConstraints.this.constraintsEnabled = false;
/*     */         }
/*     */         
/*     */       }
/*  57 */     });
/*  58 */     final JLabel label = new JLabel("");
/*     */     
/*  60 */     btnSelect.addActionListener(new ActionListener() {
/*     */       public void actionPerformed(ActionEvent e) {
/*  62 */         int returnVal = chooser.showOpenDialog(PannelloConstraints.this.getParent());
/*  63 */         if (returnVal == 0) {
/*  64 */           System.out.println("You chose to open this file: " + chooser.getSelectedFile().getName());
/*  65 */           label.setText(chooser.getSelectedFile().getAbsolutePath());
/*  66 */           PannelloConstraints.this.filePath = chooser.getSelectedFile().getAbsolutePath();
/*     */         }
/*     */         
/*     */       }
/*  70 */     });
/*  71 */     GroupLayout groupLayout = new GroupLayout(this);
/*  72 */     groupLayout.setHorizontalGroup(
/*  73 */       groupLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
/*  74 */       .addGroup(groupLayout.createSequentialGroup()
/*  75 */       .addGroup(groupLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
/*  76 */       .addGroup(groupLayout.createSequentialGroup()
/*  77 */       .addContainerGap()
/*  78 */       .addComponent(lblEnableConstraints)
/*  79 */       .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
/*  80 */       .addComponent(checkBox))
/*  81 */       .addGroup(GroupLayout.Alignment.TRAILING, groupLayout.createSequentialGroup()
/*  82 */       .addContainerGap(24, 32767)
/*  83 */       .addComponent(label, -2, 570, -2)))
/*  84 */       .addContainerGap(334, 32767))
/*  85 */       .addGroup(groupLayout.createSequentialGroup()
/*  86 */       .addGap(85)
/*  87 */       .addComponent(btnSelect)
/*  88 */       .addContainerGap(373, 32767)));
/*     */     
/*  90 */     groupLayout.setVerticalGroup(
/*  91 */       groupLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
/*  92 */       .addGroup(groupLayout.createSequentialGroup()
/*  93 */       .addGap(10)
/*  94 */       .addGroup(groupLayout.createParallelGroup(GroupLayout.Alignment.TRAILING)
/*  95 */       .addComponent(lblEnableConstraints)
/*  96 */       .addComponent(checkBox))
/*  97 */       .addGap(12)
/*  98 */       .addComponent(btnSelect)
/*  99 */       .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
/* 100 */       .addComponent(label, -2, 19, -2)
/* 101 */       .addContainerGap(23, 32767)));
/*     */     
/* 103 */     setLayout(groupLayout);
/*     */   }
/*     */   
/*     */   public boolean isConstraintsEnabled()
/*     */   {
/* 108 */     return this.constraintsEnabled;
/*     */   }
/*     */   
/*     */   public void setConstraintsEnabled(boolean constraintsEnabled) {
/* 112 */     this.constraintsEnabled = constraintsEnabled;
/*     */   }
/*     */   
/*     */   public String getFilePath() {
/* 116 */     return this.filePath;
/*     */   }
/*     */ }


/* Location:              /home/menick/Scrivania/ProcessMining/Dipendenze/CNMining.jar!/org/processmining/plugins/core/PannelloConstraints.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       0.7.1
 */