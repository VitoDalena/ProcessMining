/*    */ package edu.uci.ics.jung.io.graphml;
/*    */ 
/*    */ import edu.uci.ics.jung.io.GraphIOException;
/*    */ import javax.xml.stream.XMLStreamException;
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
/*    */ public class ExceptionConverter
/*    */ {
/*    */   public static void convert(Exception e)
/*    */     throws GraphIOException
/*    */   {
/* 38 */     if ((e instanceof GraphIOException)) {
/* 39 */       throw ((GraphIOException)e);
/*    */     }
/*    */     
/* 42 */     if ((e instanceof RuntimeException))
/*    */     {
/*    */ 
/* 45 */       if ((e.getCause() instanceof XMLStreamException)) {
/* 46 */         throw new GraphIOException(e.getCause());
/*    */       }
/*    */       
/* 49 */       throw ((RuntimeException)e);
/*    */     }
/*    */     
/* 52 */     throw new GraphIOException(e);
/*    */   }
/*    */ }


/* Location:              /home/menick/Scrivania/ProcessMining/CNMining/CNMining.jar!/edu/uci/ics/jung/io/graphml/ExceptionConverter.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       0.7.1
 */