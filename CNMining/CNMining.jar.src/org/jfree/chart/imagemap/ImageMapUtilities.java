/*     */ package org.jfree.chart.imagemap;
/*     */ 
/*     */ import java.io.IOException;
/*     */ import java.io.PrintWriter;
/*     */ import org.jfree.chart.ChartRenderingInfo;
/*     */ import org.jfree.chart.entity.ChartEntity;
/*     */ import org.jfree.chart.entity.EntityCollection;
/*     */ import org.jfree.util.StringUtils;
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
/*     */ public class ImageMapUtilities
/*     */ {
/*     */   public static void writeImageMap(PrintWriter writer, String name, ChartRenderingInfo info)
/*     */     throws IOException
/*     */   {
/*  80 */     writeImageMap(writer, name, info, new StandardToolTipTagFragmentGenerator(), new StandardURLTagFragmentGenerator());
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
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public static void writeImageMap(PrintWriter writer, String name, ChartRenderingInfo info, boolean useOverLibForToolTips)
/*     */     throws IOException
/*     */   {
/* 101 */     ToolTipTagFragmentGenerator toolTipTagFragmentGenerator = null;
/* 102 */     if (useOverLibForToolTips) {
/* 103 */       toolTipTagFragmentGenerator = new OverLIBToolTipTagFragmentGenerator();
/*     */     }
/*     */     else
/*     */     {
/* 107 */       toolTipTagFragmentGenerator = new StandardToolTipTagFragmentGenerator();
/*     */     }
/*     */     
/* 110 */     writeImageMap(writer, name, info, toolTipTagFragmentGenerator, new StandardURLTagFragmentGenerator());
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
/*     */   public static void writeImageMap(PrintWriter writer, String name, ChartRenderingInfo info, ToolTipTagFragmentGenerator toolTipTagFragmentGenerator, URLTagFragmentGenerator urlTagFragmentGenerator)
/*     */     throws IOException
/*     */   {
/* 137 */     writer.println(getImageMap(name, info, toolTipTagFragmentGenerator, urlTagFragmentGenerator));
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
/*     */   public static String getImageMap(String name, ChartRenderingInfo info)
/*     */   {
/* 151 */     return getImageMap(name, info, new StandardToolTipTagFragmentGenerator(), new StandardURLTagFragmentGenerator());
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
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public static String getImageMap(String name, ChartRenderingInfo info, ToolTipTagFragmentGenerator toolTipTagFragmentGenerator, URLTagFragmentGenerator urlTagFragmentGenerator)
/*     */   {
/* 175 */     StringBuffer sb = new StringBuffer();
/* 176 */     sb.append("<map id=\"" + htmlEscape(name) + "\" name=\"" + htmlEscape(name) + "\">");
/*     */     
/* 178 */     sb.append(StringUtils.getLineSeparator());
/* 179 */     EntityCollection entities = info.getEntityCollection();
/* 180 */     if (entities != null) {
/* 181 */       int count = entities.getEntityCount();
/* 182 */       for (int i = count - 1; i >= 0; i--) {
/* 183 */         ChartEntity entity = entities.getEntity(i);
/* 184 */         if ((entity.getToolTipText() != null) || (entity.getURLText() != null))
/*     */         {
/* 186 */           String area = entity.getImageMapAreaTag(toolTipTagFragmentGenerator, urlTagFragmentGenerator);
/*     */           
/*     */ 
/* 189 */           if (area.length() > 0) {
/* 190 */             sb.append(area);
/* 191 */             sb.append(StringUtils.getLineSeparator());
/*     */           }
/*     */         }
/*     */       }
/*     */     }
/* 196 */     sb.append("</map>");
/* 197 */     return sb.toString();
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
/*     */   public static String htmlEscape(String input)
/*     */   {
/* 212 */     if (input == null) {
/* 213 */       throw new IllegalArgumentException("Null 'input' argument.");
/*     */     }
/* 215 */     StringBuffer result = new StringBuffer();
/* 216 */     int length = input.length();
/* 217 */     for (int i = 0; i < length; i++) {
/* 218 */       char c = input.charAt(i);
/* 219 */       if (c == '&') {
/* 220 */         result.append("&amp;");
/*     */       }
/* 222 */       else if (c == '"') {
/* 223 */         result.append("&quot;");
/*     */       }
/* 225 */       else if (c == '<') {
/* 226 */         result.append("&lt;");
/*     */       }
/* 228 */       else if (c == '>') {
/* 229 */         result.append("&gt;");
/*     */       }
/* 231 */       else if (c == '\'') {
/* 232 */         result.append("&#39;");
/*     */       }
/* 234 */       else if (c == '\\') {
/* 235 */         result.append("&#092;");
/*     */       }
/*     */       else {
/* 238 */         result.append(c);
/*     */       }
/*     */     }
/* 241 */     return result.toString();
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
/*     */   public static String javascriptEscape(String input)
/*     */   {
/* 255 */     if (input == null) {
/* 256 */       throw new IllegalArgumentException("Null 'input' argument.");
/*     */     }
/* 258 */     StringBuffer result = new StringBuffer();
/* 259 */     int length = input.length();
/* 260 */     for (int i = 0; i < length; i++) {
/* 261 */       char c = input.charAt(i);
/* 262 */       if (c == '"') {
/* 263 */         result.append("\\\"");
/*     */       }
/* 265 */       else if (c == '\'') {
/* 266 */         result.append("\\'");
/*     */       }
/* 268 */       else if (c == '\\') {
/* 269 */         result.append("\\\\");
/*     */       }
/*     */       else {
/* 272 */         result.append(c);
/*     */       }
/*     */     }
/* 275 */     return result.toString();
/*     */   }
/*     */ }


/* Location:              /home/menick/Scrivania/ProcessMining/CNMining/CNMining.jar!/org/jfree/chart/imagemap/ImageMapUtilities.class
 * Java compiler version: 3 (47.0)
 * JD-Core Version:       0.7.1
 */