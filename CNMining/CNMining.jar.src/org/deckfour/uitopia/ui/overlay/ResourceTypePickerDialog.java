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
/*    */ import org.deckfour.uitopia.api.model.ResourceType;
/*    */ import org.deckfour.uitopia.ui.main.MainView;
/*    */ import org.deckfour.uitopia.ui.workspace.ResourceTypeListCellRenderer;
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
/*    */ public class ResourceTypePickerDialog
/*    */   extends TwoButtonOverlayDialog
/*    */ {
/*    */   private static final long serialVersionUID = -6768985969955651121L;
/*    */   private JList typeList;
/*    */   
/*    */   public ResourceTypePickerDialog(MainView mainView, String title, List<? extends ResourceType> types)
/*    */   {
/* 62 */     super(mainView, title, "Cancel", "Select", null);
/* 63 */     this.typeList = new JList(types.toArray());
/* 64 */     this.typeList.setCellRenderer(new ResourceTypeListCellRenderer(false));
/* 65 */     this.typeList.setBackground(new Color(120, 120, 120));
/* 66 */     this.typeList.setSelectionMode(0);
/* 67 */     this.typeList.setSelectedIndex(0);
/* 68 */     this.typeList.addMouseListener(new MouseAdapter() {
/*    */       public void mouseClicked(MouseEvent e) {
/* 70 */         if ((e.getButton() == 1) && (e.getClickCount() > 1)) {
/* 71 */           ResourceTypePickerDialog.this.close(true);
/*    */         }
/*    */       }
/* 74 */     });
/* 75 */     JScrollPane scrollpane = new JScrollPane(this.typeList);
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
/*    */   public ResourceType getResourceTypeBlocking() {
/* 90 */     boolean selected = super.getResultBlocking();
/* 91 */     if ((selected) && (this.typeList.getSelectedValue() != null)) {
/* 92 */       return (ResourceType)this.typeList.getSelectedValue();
/*    */     }
/* 94 */     return null;
/*    */   }
/*    */ }


/* Location:              /home/menick/Scrivania/ProcessMining/CNMining/CNMining.jar!/org/deckfour/uitopia/ui/overlay/ResourceTypePickerDialog.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */