/*     */ package org.jfree.chart.servlet;
/*     */ 
/*     */ import java.io.File;
/*     */ import java.io.IOException;
/*     */ import javax.servlet.ServletException;
/*     */ import javax.servlet.http.HttpServlet;
/*     */ import javax.servlet.http.HttpServletRequest;
/*     */ import javax.servlet.http.HttpServletResponse;
/*     */ import javax.servlet.http.HttpSession;
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
/*     */ public class DisplayChart
/*     */   extends HttpServlet
/*     */ {
/*     */   public void init()
/*     */     throws ServletException
/*     */   {}
/*     */   
/*     */   public void service(HttpServletRequest request, HttpServletResponse response)
/*     */     throws ServletException, IOException
/*     */   {
/* 102 */     HttpSession session = request.getSession();
/* 103 */     String filename = request.getParameter("filename");
/*     */     
/* 105 */     if (filename == null) {
/* 106 */       throw new ServletException("Parameter 'filename' must be supplied");
/*     */     }
/*     */     
/*     */ 
/*     */ 
/* 111 */     filename = ServletUtilities.searchReplace(filename, "..", "");
/*     */     
/*     */ 
/* 114 */     File file = new File(System.getProperty("java.io.tmpdir"), filename);
/* 115 */     if (!file.exists()) {
/* 116 */       throw new ServletException("File '" + file.getAbsolutePath() + "' does not exist");
/*     */     }
/*     */     
/*     */ 
/*     */ 
/*     */ 
/* 122 */     boolean isChartInUserList = false;
/* 123 */     ChartDeleter chartDeleter = (ChartDeleter)session.getAttribute("JFreeChart_Deleter");
/*     */     
/* 125 */     if (chartDeleter != null) {
/* 126 */       isChartInUserList = chartDeleter.isChartAvailable(filename);
/*     */     }
/*     */     
/* 129 */     boolean isChartPublic = false;
/* 130 */     if ((filename.length() >= 6) && 
/* 131 */       (filename.substring(0, 6).equals("public"))) {
/* 132 */       isChartPublic = true;
/*     */     }
/*     */     
/*     */ 
/* 136 */     boolean isOneTimeChart = false;
/* 137 */     if (filename.startsWith(ServletUtilities.getTempOneTimeFilePrefix())) {
/* 138 */       isOneTimeChart = true;
/*     */     }
/*     */     
/* 141 */     if ((isChartInUserList) || (isChartPublic) || (isOneTimeChart))
/*     */     {
/* 143 */       ServletUtilities.sendTempFile(file, response);
/* 144 */       if (isOneTimeChart) {
/* 145 */         file.delete();
/*     */       }
/*     */     }
/*     */     else {
/* 149 */       throw new ServletException("Chart image not found");
/*     */     }
/*     */   }
/*     */ }


/* Location:              /home/menick/Scrivania/ProcessMining/CNMining/CNMining.jar!/org/jfree/chart/servlet/DisplayChart.class
 * Java compiler version: 3 (47.0)
 * JD-Core Version:       0.7.1
 */