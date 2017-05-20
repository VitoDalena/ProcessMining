/*    */ package org.jfree.ui;
/*    */ 
/*    */ import java.awt.Color;
/*    */ import javax.swing.UIDefaults;
/*    */ import javax.swing.UIManager;
/*    */ import javax.swing.border.EmptyBorder;
/*    */ import javax.swing.border.MatteBorder;
/*    */ import javax.swing.plaf.BorderUIResource.CompoundBorderUIResource;
/*    */ import javax.swing.plaf.BorderUIResource.EmptyBorderUIResource;
/*    */ import javax.swing.plaf.BorderUIResource.EtchedBorderUIResource;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class UIUtilities
/*    */ {
/*    */   public static void setupUI()
/*    */   {
/*    */     try
/*    */     {
/* 70 */       String classname = UIManager.getSystemLookAndFeelClassName();
/* 71 */       UIManager.setLookAndFeel(classname);
/*    */     }
/*    */     catch (Exception e) {
/* 74 */       e.printStackTrace();
/*    */     }
/*    */     
/* 77 */     UIDefaults defaults = UIManager.getDefaults();
/*    */     
/* 79 */     defaults.put("PopupMenu.border", new BorderUIResource.EtchedBorderUIResource(0, defaults.getColor("controlShadow"), defaults.getColor("controlLtHighlight")));
/*    */     
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/* 87 */     MatteBorder matteborder = new MatteBorder(1, 1, 1, 1, Color.black);
/* 88 */     EmptyBorder emptyborder = new MatteBorder(2, 2, 2, 2, defaults.getColor("control"));
/* 89 */     BorderUIResource.CompoundBorderUIResource compBorder = new BorderUIResource.CompoundBorderUIResource(emptyborder, matteborder);
/*    */     
/* 91 */     BorderUIResource.EmptyBorderUIResource emptyBorderUI = new BorderUIResource.EmptyBorderUIResource(0, 0, 0, 0);
/*    */     
/* 93 */     defaults.put("SplitPane.border", emptyBorderUI);
/* 94 */     defaults.put("Table.scrollPaneBorder", emptyBorderUI);
/* 95 */     defaults.put("ComboBox.border", compBorder);
/* 96 */     defaults.put("TextField.border", compBorder);
/* 97 */     defaults.put("TextArea.border", compBorder);
/* 98 */     defaults.put("CheckBox.border", compBorder);
/* 99 */     defaults.put("ScrollPane.border", emptyBorderUI);
/*    */   }
/*    */ }


/* Location:              /home/menick/Scrivania/ProcessMining/CNMining/CNMining.jar!/org/jfree/ui/UIUtilities.class
 * Java compiler version: 2 (46.0)
 * JD-Core Version:       0.7.1
 */