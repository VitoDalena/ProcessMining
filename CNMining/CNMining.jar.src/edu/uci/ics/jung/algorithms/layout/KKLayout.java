/*     */ package edu.uci.ics.jung.algorithms.layout;
/*     */ 
/*     */ import edu.uci.ics.jung.algorithms.layout.util.RandomLocationTransformer;
/*     */ import edu.uci.ics.jung.algorithms.shortestpath.Distance;
/*     */ import edu.uci.ics.jung.algorithms.shortestpath.DistanceStatistics;
/*     */ import edu.uci.ics.jung.algorithms.shortestpath.UnweightedShortestPath;
/*     */ import edu.uci.ics.jung.algorithms.util.IterativeContext;
/*     */ import edu.uci.ics.jung.graph.Graph;
/*     */ import java.awt.Dimension;
/*     */ import java.awt.geom.Point2D;
/*     */ import java.util.Collection;
/*     */ import java.util.ConcurrentModificationException;
/*     */ import java.util.Iterator;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class KKLayout<V, E>
/*     */   extends AbstractLayout<V, E>
/*     */   implements IterativeContext
/*     */ {
/*  38 */   private double EPSILON = 0.1D;
/*     */   
/*     */   private int currentIteration;
/*  41 */   private int maxIterations = 2000;
/*  42 */   private String status = "KKLayout";
/*     */   
/*     */   private double L;
/*  45 */   private double K = 1.0D;
/*     */   
/*     */   private double[][] dm;
/*  48 */   private boolean adjustForGravity = true;
/*  49 */   private boolean exchangeVertices = true;
/*     */   
/*     */ 
/*     */ 
/*     */   private V[] vertices;
/*     */   
/*     */ 
/*     */ 
/*     */   private Point2D[] xydata;
/*     */   
/*     */ 
/*     */ 
/*     */   protected Distance<V> distance;
/*     */   
/*     */ 
/*     */ 
/*     */   protected double diameter;
/*     */   
/*     */ 
/*  68 */   private double length_factor = 0.9D;
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*  74 */   private double disconnected_multiplier = 0.5D;
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   public KKLayout(Graph<V, E> g)
/*     */   {
/*  81 */     this(g, new UnweightedShortestPath(g));
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */   public KKLayout(Graph<V, E> g, Distance<V> distance)
/*     */   {
/*  88 */     super(g);
/*  89 */     this.distance = distance;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   public void setLengthFactor(double length_factor)
/*     */   {
/*  97 */     this.length_factor = length_factor;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   public void setDisconnectedDistanceMultiplier(double disconnected_multiplier)
/*     */   {
/* 105 */     this.disconnected_multiplier = disconnected_multiplier;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */   public String getStatus()
/*     */   {
/* 112 */     return this.status + getSize();
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */   public void setMaxIterations(int maxIterations)
/*     */   {
/* 119 */     this.maxIterations = maxIterations;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */   public boolean isIncremental()
/*     */   {
/* 126 */     return true;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */   public boolean done()
/*     */   {
/* 133 */     if (this.currentIteration > this.maxIterations) {
/* 134 */       return true;
/*     */     }
/* 136 */     return false;
/*     */   }
/*     */   
/*     */   public void initialize()
/*     */   {
/* 141 */     this.currentIteration = 0;
/*     */     
/* 143 */     if ((this.graph != null) && (this.size != null))
/*     */     {
/* 145 */       double height = this.size.getHeight();
/* 146 */       double width = this.size.getWidth();
/*     */       
/* 148 */       int n = this.graph.getVertexCount();
/* 149 */       this.dm = new double[n][n];
/* 150 */       this.vertices = ((Object[])this.graph.getVertices().toArray());
/* 151 */       this.xydata = new Point2D[n];
/*     */       for (;;)
/*     */       {
/*     */         try
/*     */         {
/* 156 */           int index = 0;
/* 157 */           Iterator i$ = this.graph.getVertices().iterator(); if (i$.hasNext()) { V v = i$.next();
/* 158 */             Point2D xyd = transform(v);
/* 159 */             this.vertices[index] = v;
/* 160 */             this.xydata[index] = xyd;
/* 161 */             index++;
/*     */           }
/*     */         }
/*     */         catch (ConcurrentModificationException cme) {}
/*     */       }
/*     */       
/* 167 */       this.diameter = DistanceStatistics.diameter(this.graph, this.distance, true);
/*     */       
/* 169 */       double L0 = Math.min(height, width);
/* 170 */       this.L = (L0 / this.diameter * this.length_factor);
/*     */       
/*     */ 
/* 173 */       for (int i = 0; i < n - 1; i++) {
/* 174 */         for (int j = i + 1; j < n; j++) {
/* 175 */           Number d_ij = this.distance.getDistance(this.vertices[i], this.vertices[j]);
/* 176 */           Number d_ji = this.distance.getDistance(this.vertices[j], this.vertices[i]);
/* 177 */           double dist = this.diameter * this.disconnected_multiplier;
/* 178 */           if (d_ij != null)
/* 179 */             dist = Math.min(d_ij.doubleValue(), dist);
/* 180 */           if (d_ji != null)
/* 181 */             dist = Math.min(d_ji.doubleValue(), dist);
/* 182 */           this.dm[i][j] = (this.dm[j][i] = dist);
/*     */         }
/*     */       }
/*     */     }
/*     */   }
/*     */   
/*     */   public void step() {
/*     */     try {
/* 190 */       this.currentIteration += 1;
/* 191 */       double energy = calcEnergy();
/* 192 */       this.status = ("Kamada-Kawai V=" + getGraph().getVertexCount() + "(" + getGraph().getVertexCount() + ")" + " IT: " + this.currentIteration + " E=" + energy);
/*     */       
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/* 198 */       int n = getGraph().getVertexCount();
/* 199 */       if (n == 0) {
/* 200 */         return;
/*     */       }
/* 202 */       double maxDeltaM = 0.0D;
/* 203 */       int pm = -1;
/* 204 */       for (int i = 0; i < n; i++)
/* 205 */         if (!isLocked(this.vertices[i]))
/*     */         {
/* 207 */           double deltam = calcDeltaM(i);
/*     */           
/* 209 */           if (maxDeltaM < deltam) {
/* 210 */             maxDeltaM = deltam;
/* 211 */             pm = i;
/*     */           }
/*     */         }
/* 214 */       if (pm == -1) {
/* 215 */         return;
/*     */       }
/* 217 */       for (int i = 0; i < 100; i++) {
/* 218 */         double[] dxy = calcDeltaXY(pm);
/* 219 */         this.xydata[pm].setLocation(this.xydata[pm].getX() + dxy[0], this.xydata[pm].getY() + dxy[1]);
/*     */         
/* 221 */         double deltam = calcDeltaM(pm);
/* 222 */         if (deltam < this.EPSILON) {
/*     */           break;
/*     */         }
/*     */       }
/* 226 */       if (this.adjustForGravity) {
/* 227 */         adjustForGravity();
/*     */       }
/* 229 */       if ((this.exchangeVertices) && (maxDeltaM < this.EPSILON)) {
/* 230 */         energy = calcEnergy();
/* 231 */         for (int i = 0; i < n - 1; i++) {
/* 232 */           if (!isLocked(this.vertices[i]))
/*     */           {
/* 234 */             for (int j = i + 1; j < n; j++) {
/* 235 */               if (!isLocked(this.vertices[j]))
/*     */               {
/* 237 */                 double xenergy = calcEnergyIfExchanged(i, j);
/* 238 */                 if (energy > xenergy) {
/* 239 */                   double sx = this.xydata[i].getX();
/* 240 */                   double sy = this.xydata[i].getY();
/* 241 */                   this.xydata[i].setLocation(this.xydata[j]);
/* 242 */                   this.xydata[j].setLocation(sx, sy);
/* 243 */                   return;
/*     */                 }
/*     */               }
/*     */             }
/*     */           }
/*     */         }
/*     */       }
/*     */     }
/*     */     finally {}
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   public void adjustForGravity()
/*     */   {
/* 259 */     Dimension d = getSize();
/* 260 */     double height = d.getHeight();
/* 261 */     double width = d.getWidth();
/* 262 */     double gx = 0.0D;
/* 263 */     double gy = 0.0D;
/* 264 */     for (int i = 0; i < this.xydata.length; i++) {
/* 265 */       gx += this.xydata[i].getX();
/* 266 */       gy += this.xydata[i].getY();
/*     */     }
/* 268 */     gx /= this.xydata.length;
/* 269 */     gy /= this.xydata.length;
/* 270 */     double diffx = width / 2.0D - gx;
/* 271 */     double diffy = height / 2.0D - gy;
/* 272 */     for (int i = 0; i < this.xydata.length; i++) {
/* 273 */       this.xydata[i].setLocation(this.xydata[i].getX() + diffx, this.xydata[i].getY() + diffy);
/*     */     }
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   public void setSize(Dimension size)
/*     */   {
/* 282 */     if (!this.initialized)
/* 283 */       setInitializer(new RandomLocationTransformer(size));
/* 284 */     super.setSize(size);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */   public void setAdjustForGravity(boolean on)
/*     */   {
/* 291 */     this.adjustForGravity = on;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */   public boolean getAdjustForGravity()
/*     */   {
/* 298 */     return this.adjustForGravity;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   public void setExchangeVertices(boolean on)
/*     */   {
/* 306 */     this.exchangeVertices = on;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   public boolean getExchangeVertices()
/*     */   {
/* 314 */     return this.exchangeVertices;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */   private double[] calcDeltaXY(int m)
/*     */   {
/* 321 */     double dE_dxm = 0.0D;
/* 322 */     double dE_dym = 0.0D;
/* 323 */     double d2E_d2xm = 0.0D;
/* 324 */     double d2E_dxmdym = 0.0D;
/* 325 */     double d2E_dymdxm = 0.0D;
/* 326 */     double d2E_d2ym = 0.0D;
/*     */     
/* 328 */     for (int i = 0; i < this.vertices.length; i++) {
/* 329 */       if (i != m)
/*     */       {
/* 331 */         double dist = this.dm[m][i];
/* 332 */         double l_mi = this.L * dist;
/* 333 */         double k_mi = this.K / (dist * dist);
/* 334 */         double dx = this.xydata[m].getX() - this.xydata[i].getX();
/* 335 */         double dy = this.xydata[m].getY() - this.xydata[i].getY();
/* 336 */         double d = Math.sqrt(dx * dx + dy * dy);
/* 337 */         double ddd = d * d * d;
/*     */         
/* 339 */         dE_dxm += k_mi * (1.0D - l_mi / d) * dx;
/* 340 */         dE_dym += k_mi * (1.0D - l_mi / d) * dy;
/* 341 */         d2E_d2xm += k_mi * (1.0D - l_mi * dy * dy / ddd);
/* 342 */         d2E_dxmdym += k_mi * l_mi * dx * dy / ddd;
/* 343 */         d2E_d2ym += k_mi * (1.0D - l_mi * dx * dx / ddd);
/*     */       }
/*     */     }
/*     */     
/* 347 */     d2E_dymdxm = d2E_dxmdym;
/*     */     
/* 349 */     double denomi = d2E_d2xm * d2E_d2ym - d2E_dxmdym * d2E_dymdxm;
/* 350 */     double deltaX = (d2E_dxmdym * dE_dym - d2E_d2ym * dE_dxm) / denomi;
/* 351 */     double deltaY = (d2E_dymdxm * dE_dxm - d2E_d2xm * dE_dym) / denomi;
/* 352 */     return new double[] { deltaX, deltaY };
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */   private double calcDeltaM(int m)
/*     */   {
/* 359 */     double dEdxm = 0.0D;
/* 360 */     double dEdym = 0.0D;
/* 361 */     for (int i = 0; i < this.vertices.length; i++) {
/* 362 */       if (i != m) {
/* 363 */         double dist = this.dm[m][i];
/* 364 */         double l_mi = this.L * dist;
/* 365 */         double k_mi = this.K / (dist * dist);
/*     */         
/* 367 */         double dx = this.xydata[m].getX() - this.xydata[i].getX();
/* 368 */         double dy = this.xydata[m].getY() - this.xydata[i].getY();
/* 369 */         double d = Math.sqrt(dx * dx + dy * dy);
/*     */         
/* 371 */         double common = k_mi * (1.0D - l_mi / d);
/* 372 */         dEdxm += common * dx;
/* 373 */         dEdym += common * dy;
/*     */       }
/*     */     }
/* 376 */     return Math.sqrt(dEdxm * dEdxm + dEdym * dEdym);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */   private double calcEnergy()
/*     */   {
/* 383 */     double energy = 0.0D;
/* 384 */     for (int i = 0; i < this.vertices.length - 1; i++) {
/* 385 */       for (int j = i + 1; j < this.vertices.length; j++) {
/* 386 */         double dist = this.dm[i][j];
/* 387 */         double l_ij = this.L * dist;
/* 388 */         double k_ij = this.K / (dist * dist);
/* 389 */         double dx = this.xydata[i].getX() - this.xydata[j].getX();
/* 390 */         double dy = this.xydata[i].getY() - this.xydata[j].getY();
/* 391 */         double d = Math.sqrt(dx * dx + dy * dy);
/*     */         
/*     */ 
/* 394 */         energy += k_ij / 2.0D * (dx * dx + dy * dy + l_ij * l_ij - 2.0D * l_ij * d);
/*     */       }
/*     */     }
/*     */     
/* 398 */     return energy;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   private double calcEnergyIfExchanged(int p, int q)
/*     */   {
/* 406 */     if (p >= q)
/* 407 */       throw new RuntimeException("p should be < q");
/* 408 */     double energy = 0.0D;
/* 409 */     for (int i = 0; i < this.vertices.length - 1; i++) {
/* 410 */       for (int j = i + 1; j < this.vertices.length; j++) {
/* 411 */         int ii = i;
/* 412 */         int jj = j;
/* 413 */         if (i == p) ii = q;
/* 414 */         if (j == q) { jj = p;
/*     */         }
/* 416 */         double dist = this.dm[i][j];
/* 417 */         double l_ij = this.L * dist;
/* 418 */         double k_ij = this.K / (dist * dist);
/* 419 */         double dx = this.xydata[ii].getX() - this.xydata[jj].getX();
/* 420 */         double dy = this.xydata[ii].getY() - this.xydata[jj].getY();
/* 421 */         double d = Math.sqrt(dx * dx + dy * dy);
/*     */         
/* 423 */         energy += k_ij / 2.0D * (dx * dx + dy * dy + l_ij * l_ij - 2.0D * l_ij * d);
/*     */       }
/*     */     }
/*     */     
/* 427 */     return energy;
/*     */   }
/*     */   
/*     */   public void reset() {
/* 431 */     this.currentIteration = 0;
/*     */   }
/*     */ }


/* Location:              /home/menick/Scrivania/ProcessMining/CNMining/CNMining.jar!/edu/uci/ics/jung/algorithms/layout/KKLayout.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       0.7.1
 */