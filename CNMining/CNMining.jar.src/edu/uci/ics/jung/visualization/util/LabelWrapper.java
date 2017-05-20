/*    */ package edu.uci.ics.jung.visualization.util;
/*    */ 
/*    */ import java.io.PrintStream;
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
/*    */ public class LabelWrapper
/*    */   implements Transformer<String, String>
/*    */ {
/*    */   int lineLength;
/*    */   public static final String breaker = "<p>";
/*    */   
/*    */   public LabelWrapper()
/*    */   {
/* 30 */     this(10);
/*    */   }
/*    */   
/*    */ 
/*    */ 
/*    */ 
/*    */   public LabelWrapper(int lineLength)
/*    */   {
/* 38 */     this.lineLength = lineLength;
/*    */   }
/*    */   
/*    */ 
/*    */ 
/*    */   public String transform(String str)
/*    */   {
/* 45 */     if (str != null) {
/* 46 */       return wrap(str);
/*    */     }
/* 48 */     return null;
/*    */   }
/*    */   
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   private String wrap(String str)
/*    */   {
/* 60 */     StringBuilder buf = new StringBuilder(str);
/* 61 */     int len = this.lineLength;
/* 62 */     while (len < buf.length()) {
/* 63 */       int idx = buf.lastIndexOf(" ", len);
/* 64 */       if (idx != -1) {
/* 65 */         buf.replace(idx, idx + 1, "<p>");
/* 66 */         len = idx + "<p>".length() + this.lineLength;
/*    */       } else {
/* 68 */         buf.insert(len, "<p>");
/* 69 */         len += "<p>".length() + this.lineLength;
/*    */       }
/*    */     }
/* 72 */     buf.insert(0, "<html>");
/* 73 */     return buf.toString();
/*    */   }
/*    */   
/*    */   public static void main(String[] args) {
/* 77 */     String[] lines = { "This is a line with many short words that I will break into shorter lines.", "thisisalinewithnobreakssowhoknowswhereitwillwrap", "short line" };
/*    */     
/*    */ 
/*    */ 
/*    */ 
/* 82 */     LabelWrapper w = new LabelWrapper(10);
/* 83 */     for (int i = 0; i < lines.length; i++) {
/* 84 */       System.err.println("from " + lines[i] + " to " + w.wrap(lines[i]));
/*    */     }
/*    */   }
/*    */ }


/* Location:              /home/menick/Scrivania/ProcessMining/CNMining/CNMining.jar!/edu/uci/ics/jung/visualization/util/LabelWrapper.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       0.7.1
 */