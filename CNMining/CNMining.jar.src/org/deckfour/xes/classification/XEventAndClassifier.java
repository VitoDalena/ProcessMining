/*    */ package org.deckfour.xes.classification;
/*    */ 
/*    */ import java.util.Arrays;
/*    */ import java.util.Collection;
/*    */ import java.util.TreeSet;
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
/*    */ 
/*    */ 
/*    */ 
/*    */ public class XEventAndClassifier
/*    */   extends XEventAttributeClassifier
/*    */ {
/*    */   public XEventAndClassifier(XEventClassifier... comparators)
/*    */   {
/* 67 */     super("", new String[0]);
/*    */     
/* 69 */     Collection<String> keys = new TreeSet();
/*    */     
/* 71 */     Arrays.sort(comparators);
/* 72 */     StringBuilder sb = new StringBuilder();
/* 73 */     sb.append("(");
/* 74 */     sb.append(comparators[0].name());
/*    */     
/* 76 */     keys.addAll(Arrays.asList(comparators[0].getDefiningAttributeKeys()));
/*    */     
/* 78 */     for (int i = 1; i < comparators.length; i++) {
/* 79 */       sb.append(" AND ");
/* 80 */       sb.append(comparators[i].name());
/* 81 */       keys.addAll(Arrays.asList(comparators[i].getDefiningAttributeKeys()));
/*    */     }
/*    */     
/* 84 */     sb.append(")");
/* 85 */     this.name = sb.toString();
/* 86 */     this.keys = ((String[])keys.toArray(new String[0]));
/*    */   }
/*    */ }


/* Location:              /home/menick/Scrivania/ProcessMining/CNMining/CNMining.jar!/org/deckfour/xes/classification/XEventAndClassifier.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */