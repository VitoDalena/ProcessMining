/*    */ package edu.uci.ics.jung.visualization.decorators;
/*    */ 
/*    */ import java.util.HashMap;
/*    */ import java.util.Map;
/*    */ import javax.swing.Icon;
/*    */ import org.apache.commons.collections15.Transformer;
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
/*    */ public class DefaultVertexIconTransformer<V>
/*    */   implements Transformer<V, Icon>
/*    */ {
/* 33 */   protected Map<V, Icon> iconMap = new HashMap();
/*    */   
/*    */ 
/*    */ 
/*    */   public Map<V, Icon> getIconMap()
/*    */   {
/* 39 */     return this.iconMap;
/*    */   }
/*    */   
/*    */ 
/*    */ 
/*    */   public void setIconMap(Map<V, Icon> iconMap)
/*    */   {
/* 46 */     this.iconMap = iconMap;
/*    */   }
/*    */   
/*    */ 
/*    */ 
/*    */   public Icon transform(V v)
/*    */   {
/* 53 */     return (Icon)this.iconMap.get(v);
/*    */   }
/*    */ }


/* Location:              /home/menick/Scrivania/ProcessMining/CNMining/CNMining.jar!/edu/uci/ics/jung/visualization/decorators/DefaultVertexIconTransformer.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       0.7.1
 */