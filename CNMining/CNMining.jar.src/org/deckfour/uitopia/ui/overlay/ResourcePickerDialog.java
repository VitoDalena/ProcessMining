/*    */ package org.deckfour.uitopia.ui.overlay;
/*    */ 
/*    */ import com.fluxicon.slickerbox.factory.SlickerDecorator;
/*    */ import java.awt.Color;
/*    */ import java.awt.event.MouseAdapter;
/*    */ import java.awt.event.MouseEvent;
/*    */ import java.util.List;
/*    */ import javax.swing.BorderFactory;
/*    */ import javax.swing.JList;
/*    */ import javax.swing.JScrollBar;
/*    */ import javax.swing.JScrollPane;
/*    */ import javax.swing.JViewport;
/*    */ import org.deckfour.uitopia.api.model.Resource;
/*    */ import org.deckfour.uitopia.ui.main.MainView;
/*    */ import org.deckfour.uitopia.ui.workspace.ResourceListCellRenderer;
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
/*    */ public class ResourcePickerDialog
/*    */   extends TwoButtonOverlayDialog
/*    */ {
/*    */   private static final long serialVersionUID = -6214720377265933211L;
/*    */   private JList resList;
/*    */   
/*    */   public ResourcePickerDialog(MainView mainView, String title, List<? extends Resource> resources)
/*    */   {
/* 62 */     super(mainView, title, "Cancel", "Select", null);
/* 63 */     this.resList = new JList(resources.toArray());
/* 64 */     this.resList.setCellRenderer(new ResourceListCellRenderer(false));
/* 65 */     this.resList.setBackground(new Color(120, 120, 120));
/* 66 */     this.resList.setSelectionMode(0);
/* 67 */     this.resList.setSelectedIndex(0);
/* 68 */     this.resList.addMouseListener(new MouseAdapter() {
/*    */       public void mouseClicked(MouseEvent e) {
/* 70 */         if ((e.getButton() == 1) && (e.getClickCount() > 1)) {
/* 71 */           ResourcePickerDialog.this.close(true);
/*    */         }
/*    */       }
/* 74 */     });
/* 75 */     JScrollPane scrollpane = new JScrollPane(this.resList);
/* 76 */     scrollpane.setOpaque(false);
/* 77 */     scrollpane.getViewport().setOpaque(false);
/* 78 */     scrollpane.setBorder(BorderFactory.createEmptyBorder());
/* 79 */     scrollpane.setViewportBorder(BorderFactory.createLineBorder(new Color(120, 120, 120), 2));
/* 80 */     scrollpane.setHorizontalScrollBarPolicy(31);
/* 81 */     scrollpane.setVerticalScrollBarPolicy(20);
/* 82 */     SlickerDecorator.instance().decorate(scrollpane.getVerticalScrollBar(), new Color(0, 0, 0, 0), new Color(20, 20, 20), new Color(60, 60, 60));
/*    */     
/* 84 */     scrollpane.getVerticalScrollBar().setOpaque(false);
/* 85 */     super.setPayload(scrollpane);
/* 86 */     revalidate();
/*    */   }
/*    */   
/*    */   public Resource getResourceBlocking() {
/* 90 */     boolean selected = super.getResultBlocking();
/* 91 */     if ((selected) && (this.resList.getSelectedValue() != null)) {
/* 92 */       return (Resource)this.resList.getSelectedValue();
/*    */     }
/* 94 */     return null;
/*    */   }
/*    */ }


/* Location:              /home/menick/Scrivania/ProcessMining/CNMining/CNMining.jar!/org/deckfour/uitopia/ui/overlay/ResourcePickerDialog.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */