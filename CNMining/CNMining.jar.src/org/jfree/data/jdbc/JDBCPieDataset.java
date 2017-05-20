/*     */ package org.jfree.data.jdbc;
/*     */ 
/*     */ import java.io.PrintStream;
/*     */ import java.sql.Connection;
/*     */ import java.sql.DriverManager;
/*     */ import java.sql.ResultSet;
/*     */ import java.sql.ResultSetMetaData;
/*     */ import java.sql.SQLException;
/*     */ import java.sql.Statement;
/*     */ import java.sql.Timestamp;
/*     */ import org.jfree.data.general.DefaultPieDataset;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class JDBCPieDataset
/*     */   extends DefaultPieDataset
/*     */ {
/*     */   static final long serialVersionUID = -8753216855496746108L;
/*     */   private transient Connection connection;
/*     */   
/*     */   public JDBCPieDataset(String url, String driverName, String user, String password)
/*     */     throws SQLException, ClassNotFoundException
/*     */   {
/* 105 */     Class.forName(driverName);
/* 106 */     this.connection = DriverManager.getConnection(url, user, password);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public JDBCPieDataset(Connection con)
/*     */   {
/* 117 */     if (con == null) {
/* 118 */       throw new NullPointerException("A connection must be supplied.");
/*     */     }
/* 120 */     this.connection = con;
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
/*     */   public JDBCPieDataset(Connection con, String query)
/*     */     throws SQLException
/*     */   {
/* 135 */     this(con);
/* 136 */     executeQuery(query);
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
/*     */   public void executeQuery(String query)
/*     */     throws SQLException
/*     */   {
/* 151 */     executeQuery(this.connection, query);
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
/*     */   public void executeQuery(Connection con, String query)
/*     */     throws SQLException
/*     */   {
/* 168 */     Statement statement = null;
/* 169 */     ResultSet resultSet = null;
/*     */     try
/*     */     {
/* 172 */       statement = con.createStatement();
/* 173 */       resultSet = statement.executeQuery(query);
/* 174 */       ResultSetMetaData metaData = resultSet.getMetaData();
/*     */       
/* 176 */       int columnCount = metaData.getColumnCount();
/* 177 */       if (columnCount != 2) {
/* 178 */         throw new SQLException("Invalid sql generated.  PieDataSet requires 2 columns only");
/*     */       }
/*     */       
/*     */ 
/*     */ 
/* 183 */       int columnType = metaData.getColumnType(2);
/* 184 */       double value = NaN.0D;
/* 185 */       while (resultSet.next()) {
/* 186 */         Comparable key = resultSet.getString(1);
/* 187 */         switch (columnType) {
/*     */         case -5: 
/*     */         case 2: 
/*     */         case 3: 
/*     */         case 4: 
/*     */         case 6: 
/*     */         case 7: 
/*     */         case 8: 
/* 195 */           value = resultSet.getDouble(2);
/* 196 */           setValue(key, value);
/* 197 */           break;
/*     */         
/*     */         case 91: 
/*     */         case 92: 
/*     */         case 93: 
/* 202 */           Timestamp date = resultSet.getTimestamp(2);
/* 203 */           value = date.getTime();
/* 204 */           setValue(key, value);
/* 205 */           break;
/*     */         
/*     */         default: 
/* 208 */           System.err.println("JDBCPieDataset - unknown data type");
/*     */         }
/*     */         
/*     */       }
/*     */       
/*     */ 
/*     */ 
/* 215 */       fireDatasetChanged(); return;
/*     */     }
/*     */     finally
/*     */     {
/* 219 */       if (resultSet != null) {
/*     */         try {
/* 221 */           resultSet.close();
/*     */         }
/*     */         catch (Exception e) {
/* 224 */           System.err.println("JDBCPieDataset: swallowing exception.");
/*     */         }
/*     */       }
/* 227 */       if (statement != null) {
/*     */         try {
/* 229 */           statement.close();
/*     */         }
/*     */         catch (Exception e) {
/* 232 */           System.err.println("JDBCPieDataset: swallowing exception.");
/*     */         }
/*     */       }
/*     */     }
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */   public void close()
/*     */   {
/*     */     try
/*     */     {
/* 244 */       this.connection.close();
/*     */     }
/*     */     catch (Exception e) {
/* 247 */       System.err.println("JdbcXYDataset: swallowing exception.");
/*     */     }
/*     */   }
/*     */ }


/* Location:              /home/menick/Scrivania/ProcessMining/CNMining/CNMining.jar!/org/jfree/data/jdbc/JDBCPieDataset.class
 * Java compiler version: 3 (47.0)
 * JD-Core Version:       0.7.1
 */