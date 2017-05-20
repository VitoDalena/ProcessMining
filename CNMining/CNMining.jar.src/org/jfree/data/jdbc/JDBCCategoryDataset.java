/*     */ package org.jfree.data.jdbc;
/*     */ 
/*     */ import java.sql.Connection;
/*     */ import java.sql.Date;
/*     */ import java.sql.DriverManager;
/*     */ import java.sql.ResultSet;
/*     */ import java.sql.ResultSetMetaData;
/*     */ import java.sql.SQLException;
/*     */ import java.sql.Statement;
/*     */ import org.jfree.data.category.DefaultCategoryDataset;
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
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class JDBCCategoryDataset
/*     */   extends DefaultCategoryDataset
/*     */ {
/*     */   static final long serialVersionUID = -3080395327918844965L;
/*     */   private transient Connection connection;
/* 106 */   private boolean transpose = true;
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
/*     */   public JDBCCategoryDataset(String url, String driverName, String user, String passwd)
/*     */     throws ClassNotFoundException, SQLException
/*     */   {
/* 127 */     Class.forName(driverName);
/* 128 */     this.connection = DriverManager.getConnection(url, user, passwd);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public JDBCCategoryDataset(Connection connection)
/*     */   {
/* 137 */     if (connection == null) {
/* 138 */       throw new NullPointerException("A connection must be supplied.");
/*     */     }
/* 140 */     this.connection = connection;
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
/*     */   public JDBCCategoryDataset(Connection connection, String query)
/*     */     throws SQLException
/*     */   {
/* 154 */     this(connection);
/* 155 */     executeQuery(query);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public boolean getTranspose()
/*     */   {
/* 165 */     return this.transpose;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public void setTranspose(boolean transpose)
/*     */   {
/* 175 */     this.transpose = transpose;
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
/*     */   public void executeQuery(String query)
/*     */     throws SQLException
/*     */   {
/* 191 */     executeQuery(this.connection, query);
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
/*     */   public void executeQuery(Connection con, String query)
/*     */     throws SQLException
/*     */   {
/* 209 */     Statement statement = null;
/* 210 */     ResultSet resultSet = null;
/*     */     try {
/* 212 */       statement = con.createStatement();
/* 213 */       resultSet = statement.executeQuery(query);
/* 214 */       ResultSetMetaData metaData = resultSet.getMetaData();
/*     */       
/* 216 */       int columnCount = metaData.getColumnCount();
/*     */       
/* 218 */       if (columnCount < 2) {
/* 219 */         throw new SQLException("JDBCCategoryDataset.executeQuery() : insufficient columns returned from the database.");
/*     */       }
/*     */       
/*     */ 
/*     */ 
/*     */ 
/* 225 */       int i = getRowCount();
/* 226 */       for (;;) { i--; if (i < 0) break;
/* 227 */         removeRow(i);
/*     */       }
/*     */       
/* 230 */       while (resultSet.next())
/*     */       {
/* 232 */         Comparable rowKey = resultSet.getString(1);
/* 233 */         for (int column = 2; column <= columnCount; column++)
/*     */         {
/* 235 */           Comparable columnKey = metaData.getColumnName(column);
/* 236 */           int columnType = metaData.getColumnType(column);
/*     */           
/* 238 */           switch (columnType) {
/*     */           case -6: 
/*     */           case -5: 
/*     */           case 2: 
/*     */           case 3: 
/*     */           case 4: 
/*     */           case 5: 
/*     */           case 6: 
/*     */           case 7: 
/*     */           case 8: 
/* 248 */             Number value = (Number)resultSet.getObject(column);
/* 249 */             if (this.transpose) {
/* 250 */               setValue(value, columnKey, rowKey);
/*     */             }
/*     */             else {
/* 253 */               setValue(value, rowKey, columnKey);
/*     */             }
/* 255 */             break;
/*     */           
/*     */           case 91: 
/*     */           case 92: 
/*     */           case 93: 
/* 260 */             Date date = (Date)resultSet.getObject(column);
/* 261 */             Number value = new Long(date.getTime());
/* 262 */             if (this.transpose) {
/* 263 */               setValue(value, columnKey, rowKey);
/*     */             }
/*     */             else {
/* 266 */               setValue(value, rowKey, columnKey);
/*     */             }
/* 268 */             break;
/*     */           
/*     */           case -1: 
/*     */           case 1: 
/*     */           case 12: 
/* 273 */             String string = (String)resultSet.getObject(column);
/*     */             try
/*     */             {
/* 276 */               Number value = Double.valueOf(string);
/* 277 */               if (this.transpose) {
/* 278 */                 setValue(value, columnKey, rowKey);
/*     */               }
/*     */               else {
/* 281 */                 setValue(value, rowKey, columnKey);
/*     */               }
/*     */             }
/*     */             catch (NumberFormatException e) {}
/*     */           }
/*     */           
/*     */         }
/*     */       }
/*     */       
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/* 296 */       fireDatasetChanged(); return;
/*     */     }
/*     */     finally {
/* 299 */       if (resultSet != null) {
/*     */         try {
/* 301 */           resultSet.close();
/*     */         }
/*     */         catch (Exception e) {}
/*     */       }
/*     */       
/*     */ 
/* 307 */       if (statement != null) {
/*     */         try {
/* 309 */           statement.close();
/*     */         }
/*     */         catch (Exception e) {}
/*     */       }
/*     */     }
/*     */   }
/*     */ }


/* Location:              /home/menick/Scrivania/ProcessMining/CNMining/CNMining.jar!/org/jfree/data/jdbc/JDBCCategoryDataset.class
 * Java compiler version: 3 (47.0)
 * JD-Core Version:       0.7.1
 */