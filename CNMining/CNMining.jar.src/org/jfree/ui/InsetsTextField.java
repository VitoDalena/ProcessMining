/*     */ package org.jfree.ui;
/*     */ 
/*     */ import java.awt.Insets;
/*     */ import java.util.ResourceBundle;
/*     */ import javax.swing.JTextField;
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
/*     */ public class InsetsTextField
/*     */   extends JTextField
/*     */ {
/*  64 */   protected static ResourceBundle localizationResources = ResourceBundleWrapper.getBundle("org.jfree.ui.LocalizationBundle");
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public InsetsTextField(Insets insets)
/*     */   {
/*  76 */     setInsets(insets);
/*  77 */     setEnabled(false);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public String formatInsetsString(Insets insets)
/*     */   {
/*  88 */     insets = insets == null ? new Insets(0, 0, 0, 0) : insets;
/*  89 */     return localizationResources.getString("T") + insets.top + ", " + localizationResources.getString("L") + insets.left + ", " + localizationResources.getString("B") + insets.bottom + ", " + localizationResources.getString("R") + insets.right;
/*     */   }
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
/*     */   public void setInsets(Insets insets)
/*     */   {
/* 104 */     setText(formatInsetsString(insets));
/*     */   }
/*     */ }


/* Location:              /home/menick/Scrivania/ProcessMining/CNMining/CNMining.jar!/org/jfree/ui/InsetsTextField.class
 * Java compiler version: 2 (46.0)
 * JD-Core Version:       0.7.1
 */