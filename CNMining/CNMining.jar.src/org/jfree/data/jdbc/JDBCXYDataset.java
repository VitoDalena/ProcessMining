/*     */ package org.jfree.data.jdbc;
/*     */ 
/*     */ import java.io.PrintStream;
/*     */ import java.sql.Connection;
/*     */ import java.sql.DriverManager;
/*     */ import java.sql.ResultSet;
/*     */ import java.sql.ResultSetMetaData;
/*     */ import java.sql.SQLException;
/*     */ import java.sql.Statement;
/*     */ import java.util.ArrayList;
/*     */ import java.util.Date;
/*     */ import org.jfree.data.Range;
/*     */ import org.jfree.data.RangeInfo;
/*     */ import org.jfree.data.xy.AbstractXYDataset;
/*     */ import org.jfree.data.xy.TableXYDataset;
/*     */ import org.jfree.data.xy.XYDataset;
/*     */ import org.jfree.util.Log;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class JDBCXYDataset
/*     */   extends AbstractXYDataset
/*     */   implements XYDataset, TableXYDataset, RangeInfo
/*     */ {
/*     */   private transient Connection connection;
/* 108 */   private String[] columnNames = new String[0];
/*     */   
/*     */ 
/*     */   private ArrayList rows;
/*     */   
/*     */ 
/* 114 */   private double maxValue = 0.0D;
/*     */   
/*     */ 
/* 117 */   private double minValue = 0.0D;
/*     */   
/*     */ 
/* 120 */   private boolean isTimeSeries = false;
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   private JDBCXYDataset()
/*     */   {
/* 127 */     this.rows = new ArrayList();
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
/*     */   public JDBCXYDataset(String url, String driverName, String user, String password)
/*     */     throws SQLException, ClassNotFoundException
/*     */   {
/* 148 */     this();
/* 149 */     Class.forName(driverName);
/* 150 */     this.connection = DriverManager.getConnection(url, user, password);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public JDBCXYDataset(Connection con)
/*     */     throws SQLException
/*     */   {
/* 162 */     this();
/* 163 */     this.connection = con;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public JDBCXYDataset(Connection con, String query)
/*     */     throws SQLException
/*     */   {
/* 176 */     this(con);
/* 177 */     executeQuery(query);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public boolean isTimeSeries()
/*     */   {
/* 187 */     return this.isTimeSeries;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public void setTimeSeries(boolean timeSeries)
/*     */   {
/* 197 */     this.isTimeSeries = timeSeries;
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
/* 213 */     executeQuery(this.connection, query);
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
/*     */   public void executeQuery(Connection con, String query)
/*     */     throws SQLException
/*     */   {
/* 232 */     if (con == null) {
/* 233 */       throw new SQLException("There is no database to execute the query.");
/*     */     }
/*     */     
/*     */ 
/*     */ 
/* 238 */     ResultSet resultSet = null;
/* 239 */     Statement statement = null;
/*     */     try {
/* 241 */       statement = con.createStatement();
/* 242 */       resultSet = statement.executeQuery(query);
/* 243 */       ResultSetMetaData metaData = resultSet.getMetaData();
/*     */       
/* 245 */       int numberOfColumns = metaData.getColumnCount();
/* 246 */       int numberOfValidColumns = 0;
/* 247 */       int[] columnTypes = new int[numberOfColumns];
/* 248 */       for (int column = 0; column < numberOfColumns; column++) {
/*     */         try {
/* 250 */           int type = metaData.getColumnType(column + 1);
/* 251 */           switch (type)
/*     */           {
/*     */           case -7: 
/*     */           case -5: 
/*     */           case 2: 
/*     */           case 3: 
/*     */           case 4: 
/*     */           case 5: 
/*     */           case 6: 
/*     */           case 7: 
/*     */           case 8: 
/*     */           case 91: 
/*     */           case 92: 
/*     */           case 93: 
/* 265 */             numberOfValidColumns++;
/* 266 */             columnTypes[column] = type;
/* 267 */             break;
/*     */           default: 
/* 269 */             Log.warn("Unable to load column " + column + " (" + type + "," + metaData.getColumnClassName(column + 1) + ")");
/*     */             
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/* 275 */             columnTypes[column] = 0;
/*     */           }
/*     */         }
/*     */         catch (SQLException e)
/*     */         {
/* 280 */           columnTypes[column] = 0;
/* 281 */           throw e;
/*     */         }
/*     */       }
/*     */       
/*     */ 
/* 286 */       if (numberOfValidColumns <= 1) {
/* 287 */         throw new SQLException("Not enough valid columns where generated by query.");
/*     */       }
/*     */       
/*     */ 
/*     */ 
/*     */ 
/* 293 */       this.columnNames = new String[numberOfValidColumns - 1];
/*     */       
/* 295 */       int currentColumn = 0;
/* 296 */       for (int column = 1; column < numberOfColumns; column++) {
/* 297 */         if (columnTypes[column] != 0) {
/* 298 */           this.columnNames[currentColumn] = metaData.getColumnLabel(column + 1);
/*     */           
/* 300 */           currentColumn++;
/*     */         }
/*     */       }
/*     */       
/*     */ 
/* 305 */       if (this.rows != null) {
/* 306 */         for (int column = 0; column < this.rows.size(); column++) {
/* 307 */           ArrayList row = (ArrayList)this.rows.get(column);
/* 308 */           row.clear();
/*     */         }
/* 310 */         this.rows.clear();
/*     */       }
/*     */       
/*     */ 
/* 314 */       switch (columnTypes[0]) {
/*     */       case 91: 
/*     */       case 92: 
/*     */       case 93: 
/* 318 */         this.isTimeSeries = true;
/* 319 */         break;
/*     */       default: 
/* 321 */         this.isTimeSeries = false;
/*     */       }
/*     */       
/*     */       
/*     */ 
/*     */ 
/* 327 */       while (resultSet.next()) {
/* 328 */         ArrayList newRow = new ArrayList();
/* 329 */         for (int column = 0; column < numberOfColumns; column++) {
/* 330 */           Object xObject = resultSet.getObject(column + 1);
/* 331 */           switch (columnTypes[column]) {
/*     */           case -5: 
/*     */           case 2: 
/*     */           case 3: 
/*     */           case 4: 
/*     */           case 5: 
/*     */           case 6: 
/*     */           case 7: 
/*     */           case 8: 
/* 340 */             newRow.add(xObject);
/* 341 */             break;
/*     */           
/*     */           case 91: 
/*     */           case 92: 
/*     */           case 93: 
/* 346 */             newRow.add(new Long(((Date)xObject).getTime()));
/* 347 */             break;
/*     */           case 0: 
/*     */             break;
/*     */           default: 
/* 351 */             System.err.println("Unknown data");
/* 352 */             columnTypes[column] = 0;
/*     */           }
/*     */           
/*     */         }
/* 356 */         this.rows.add(newRow);
/*     */       }
/*     */       
/*     */ 
/* 360 */       if (this.rows.size() == 0) {
/* 361 */         ArrayList newRow = new ArrayList();
/* 362 */         for (int column = 0; column < numberOfColumns; column++) {
/* 363 */           if (columnTypes[column] != 0) {
/* 364 */             newRow.add(new Integer(0));
/*     */           }
/*     */         }
/* 367 */         this.rows.add(newRow);
/*     */       }
/*     */       
/*     */ 
/* 371 */       if (this.rows.size() < 1) {
/* 372 */         this.maxValue = 0.0D;
/* 373 */         this.minValue = 0.0D;
/*     */       }
/*     */       else {
/* 376 */         ArrayList row = (ArrayList)this.rows.get(0);
/* 377 */         this.maxValue = Double.NEGATIVE_INFINITY;
/* 378 */         this.minValue = Double.POSITIVE_INFINITY;
/* 379 */         for (int rowNum = 0; rowNum < this.rows.size(); rowNum++) {
/* 380 */           row = (ArrayList)this.rows.get(rowNum);
/* 381 */           for (int column = 1; column < numberOfColumns; column++) {
/* 382 */             Object testValue = row.get(column);
/* 383 */             if (testValue != null) {
/* 384 */               double test = ((Number)testValue).doubleValue();
/*     */               
/* 386 */               if (test < this.minValue) {
/* 387 */                 this.minValue = test;
/*     */               }
/* 389 */               if (test > this.maxValue) {
/* 390 */                 this.maxValue = test;
/*     */               }
/*     */             }
/*     */           }
/*     */         }
/*     */       }
/*     */       
/* 397 */       fireDatasetChanged(); return;
/*     */     }
/*     */     finally {
/* 400 */       if (resultSet != null) {
/*     */         try {
/* 402 */           resultSet.close();
/*     */         }
/*     */         catch (Exception e) {}
/*     */       }
/*     */       
/*     */ 
/* 408 */       if (statement != null) {
/*     */         try {
/* 410 */           statement.close();
/*     */         }
/*     */         catch (Exception e) {}
/*     */       }
/*     */     }
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
/*     */   public Number getX(int seriesIndex, int itemIndex)
/*     */   {
/* 433 */     ArrayList row = (ArrayList)this.rows.get(itemIndex);
/* 434 */     return (Number)row.get(0);
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
/*     */   public Number getY(int seriesIndex, int itemIndex)
/*     */   {
/* 448 */     ArrayList row = (ArrayList)this.rows.get(itemIndex);
/* 449 */     return (Number)row.get(seriesIndex + 1);
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
/*     */   public int getItemCount(int seriesIndex)
/*     */   {
/* 462 */     return this.rows.size();
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public int getItemCount()
/*     */   {
/* 472 */     return getItemCount(0);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public int getSeriesCount()
/*     */   {
/* 484 */     return this.columnNames.length;
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
/*     */   public Comparable getSeriesKey(int seriesIndex)
/*     */   {
/* 499 */     if ((seriesIndex < this.columnNames.length) && (this.columnNames[seriesIndex] != null))
/*     */     {
/* 501 */       return this.columnNames[seriesIndex];
/*     */     }
/*     */     
/* 504 */     return "";
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   /**
/*     */    * @deprecated
/*     */    */
/*     */   public int getLegendItemCount()
/*     */   {
/* 519 */     return getSeriesCount();
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   /**
/*     */    * @deprecated
/*     */    */
/*     */   public String[] getLegendItemLabels()
/*     */   {
/* 532 */     return this.columnNames;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */   public void close()
/*     */   {
/*     */     try
/*     */     {
/* 541 */       this.connection.close();
/*     */     }
/*     */     catch (Exception e) {
/* 544 */       System.err.println("JdbcXYDataset: swallowing exception.");
/*     */     }
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
/*     */   public double getRangeLowerBound(boolean includeInterval)
/*     */   {
/* 558 */     return this.minValue;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public double getRangeUpperBound(boolean includeInterval)
/*     */   {
/* 570 */     return this.maxValue;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public Range getRangeBounds(boolean includeInterval)
/*     */   {
/* 582 */     return new Range(this.minValue, this.maxValue);
/*     */   }
/*     */ }


/* Location:              /home/menick/Scrivania/ProcessMining/CNMining/CNMining.jar!/org/jfree/data/jdbc/JDBCXYDataset.class
 * Java compiler version: 3 (47.0)
 * JD-Core Version:       0.7.1
 */