/*    */ package org.deckfour.uitopia.ui.util;
/*    */ 
/*    */ import java.lang.reflect.Method;
/*    */ import javax.swing.JOptionPane;
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
/*    */ public class BrowserLauncher
/*    */ {
/*    */   private static final String errMsg = "Error attempting to launch web browser";
/*    */   
/*    */   public static void openURL(String url)
/*    */   {
/* 63 */     String osName = System.getProperty("os.name");
/*    */     try {
/* 65 */       if (osName.startsWith("Mac OS")) {
/* 66 */         Class<?> fileMgr = Class.forName("com.apple.eio.FileManager");
/* 67 */         Method openURL = fileMgr.getDeclaredMethod("openURL", new Class[] { String.class });
/*    */         
/* 69 */         openURL.invoke(null, new Object[] { url });
/*    */       }
/* 71 */       else if (osName.startsWith("Windows")) {
/* 72 */         Runtime.getRuntime().exec("rundll32 url.dll,FileProtocolHandler " + url);
/*    */       } else {
/* 74 */         String[] browsers = { "firefox", "opera", "konqueror", "epiphany", "mozilla", "netscape" };
/*    */         
/* 76 */         String browser = null;
/* 77 */         for (int count = 0; (count < browsers.length) && (browser == null); count++)
/* 78 */           if (Runtime.getRuntime().exec(new String[] { "which", browsers[count] }).waitFor() == 0)
/*    */           {
/* 80 */             browser = browsers[count]; }
/* 81 */         if (browser == null) {
/* 82 */           throw new Exception("Could not find web browser");
/*    */         }
/* 84 */         Runtime.getRuntime().exec(new String[] { browser, url });
/*    */       }
/*    */     }
/*    */     catch (Exception e) {
/* 88 */       JOptionPane.showMessageDialog(null, "Error attempting to launch web browser:\n" + e.getLocalizedMessage());
/*    */     }
/*    */   }
/*    */ }


/* Location:              /home/menick/Scrivania/ProcessMining/CNMining/CNMining.jar!/org/deckfour/uitopia/ui/util/BrowserLauncher.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */