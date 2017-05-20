/*    */ package edu.uci.ics.jung.visualization.util;
/*    */ 
/*    */ import java.awt.geom.GeneralPath;
/*    */ import java.awt.geom.Point2D.Float;
/*    */ 
/*    */ public class GeneralPathAsString
/*    */ {
/*    */   public static String toString(GeneralPath newPath)
/*    */   {
/* 10 */     StringBuilder sb = new StringBuilder();
/* 11 */     float[] coords = new float[6];
/* 12 */     java.awt.geom.PathIterator iterator = newPath.getPathIterator(null);
/* 13 */     for (; !iterator.isDone(); 
/* 14 */         iterator.next()) {
/* 15 */       int type = iterator.currentSegment(coords);
/* 16 */       java.awt.geom.Point2D p; java.awt.geom.Point2D q; switch (type) {
/*    */       case 0: 
/* 18 */         p = new Point2D.Float(coords[0], coords[1]);
/* 19 */         sb.append("moveTo " + p + "--");
/* 20 */         break;
/*    */       
/*    */       case 1: 
/* 23 */         p = new Point2D.Float(coords[0], coords[1]);
/* 24 */         sb.append("lineTo " + p + "--");
/* 25 */         break;
/*    */       
/*    */       case 2: 
/* 28 */         p = new Point2D.Float(coords[0], coords[1]);
/* 29 */         q = new Point2D.Float(coords[2], coords[3]);
/* 30 */         sb.append("quadTo " + p + " controlled by " + q);
/* 31 */         break;
/*    */       
/*    */       case 3: 
/* 34 */         p = new Point2D.Float(coords[0], coords[1]);
/* 35 */         q = new Point2D.Float(coords[2], coords[3]);
/* 36 */         java.awt.geom.Point2D r = new Point2D.Float(coords[4], coords[5]);
/* 37 */         sb.append("cubeTo " + p + " controlled by " + q + "," + r);
/*    */         
/* 39 */         break;
/*    */       
/*    */       case 4: 
/* 42 */         newPath.closePath();
/* 43 */         sb.append("close");
/*    */       }
/*    */       
/*    */     }
/*    */     
/* 48 */     return sb.toString();
/*    */   }
/*    */ }


/* Location:              /home/menick/Scrivania/ProcessMining/CNMining/CNMining.jar!/edu/uci/ics/jung/visualization/util/GeneralPathAsString.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       0.7.1
 */