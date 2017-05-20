/*     */ package flanagan.io;
/*     */ 
/*     */ import flanagan.interpolation.CubicSpline;
/*     */ import flanagan.math.Fmath;
/*     */ import flanagan.plot.PlotGraph;
/*     */ import java.awt.Canvas;
/*     */ import java.awt.Color;
/*     */ import java.awt.Container;
/*     */ import java.awt.Dimension;
/*     */ import java.awt.Graphics;
/*     */ import java.awt.Image;
/*     */ import java.awt.Toolkit;
/*     */ import java.awt.event.MouseEvent;
/*     */ import java.awt.event.MouseListener;
/*     */ import java.awt.image.PixelGrabber;
/*     */ import java.io.PrintStream;
/*     */ import java.net.InetAddress;
/*     */ import java.net.UnknownHostException;
/*     */ import java.util.ArrayList;
/*     */ import javax.swing.JFrame;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class DigiGraph
/*     */   extends Canvas
/*     */   implements MouseListener
/*     */ {
/*  55 */   private Image pic = null;
/*  56 */   private String imagePath = null;
/*  57 */   private String imageName = null;
/*  58 */   private String extension = null;
/*     */   
/*  60 */   private String outputFile = null;
/*  61 */   private FileOutput fout = null;
/*  62 */   private int trunc = 16;
/*     */   
/*  64 */   private String path = "C:";
/*  65 */   private int windowWidth = 0;
/*  66 */   private int windowHeight = 0;
/*  67 */   private int closeChoice = 1;
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*  72 */   private int xPos = 0;
/*  73 */   private int yPos = 0;
/*  74 */   private int button = 0;
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*  79 */   private int sumX = 0;
/*  80 */   private int sumY = 0;
/*  81 */   private int iSum = 0;
/*  82 */   private boolean mouseEntered = false;
/*     */   
/*  84 */   private double lowYvalue = 0.0D;
/*  85 */   private double lowYaxisXpixel = 0.0D;
/*  86 */   private double lowYaxisYpixel = 0.0D;
/*  87 */   private double highYvalue = 0.0D;
/*  88 */   private double highYaxisXpixel = 0.0D;
/*  89 */   private double highYaxisYpixel = 0.0D;
/*  90 */   private double lowXvalue = 0.0D;
/*  91 */   private double lowXaxisXpixel = 0.0D;
/*  92 */   private double lowXaxisYpixel = 0.0D;
/*  93 */   private double highXvalue = 0.0D;
/*  94 */   private double highXaxisXpixel = 0.0D;
/*  95 */   private double highXaxisYpixel = 0.0D;
/*     */   
/*  97 */   private ArrayList<Integer> xAndYvalues = new ArrayList();
/*     */   
/*  99 */   private int iCounter = 0;
/* 100 */   private double angleXaxis = 0.0D;
/* 101 */   private double angleYaxis = 0.0D;
/* 102 */   private double angleMean = 0.0D;
/* 103 */   private double angleTolerance = 0.0D;
/*     */   
/* 105 */   private boolean rotationDone = false;
/*     */   
/*     */ 
/* 108 */   private double[] xPosPixel = null;
/* 109 */   private double[] yPosPixel = null;
/* 110 */   private double[] xPositions = null;
/* 111 */   private double[] yPositions = null;
/* 112 */   private int nData = 0;
/*     */   
/* 114 */   private int nInterpPoints = 0;
/* 115 */   private boolean interpOpt = false;
/* 116 */   private double[] xInterp = null;
/* 117 */   private double[] yInterp = null;
/* 118 */   private boolean plotOpt = true;
/*     */   
/* 120 */   private boolean noIdentical = true;
/*     */   
/*     */ 
/* 123 */   private int imageFormat = 0;
/*     */   
/*     */ 
/*     */ 
/*     */ 
/* 128 */   private boolean digitizationDone = false;
/*     */   
/* 130 */   private boolean noYlow = true;
/* 131 */   private boolean noXlow = true;
/* 132 */   private boolean noYhigh = true;
/* 133 */   private boolean noXhigh = true;
/*     */   
/* 135 */   private boolean resize = false;
/*     */   
/*     */ 
/* 138 */   private JFrame window = new JFrame("Michael T Flanagan's digitizing program - DigiGraph");
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public DigiGraph()
/*     */   {
/* 147 */     setWindowSize();
/*     */     
/*     */ 
/* 150 */     selectImage();
/*     */     
/*     */ 
/* 153 */     setImage();
/*     */     
/*     */ 
/* 156 */     outputFileChoice();
/*     */     
/*     */ 
/* 159 */     addMouseListener(this);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public DigiGraph(String windowPath)
/*     */   {
/* 168 */     setWindowSize();
/*     */     
/*     */ 
/* 171 */     this.path = windowPath;
/*     */     
/*     */ 
/* 174 */     selectImage();
/*     */     
/*     */ 
/* 177 */     setImage();
/*     */     
/*     */ 
/* 180 */     outputFileChoice();
/*     */     
/*     */ 
/* 183 */     addMouseListener(this);
/*     */   }
/*     */   
/*     */ 
/*     */   private void setWindowSize()
/*     */   {
/* 189 */     Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
/* 190 */     this.windowWidth = (screenSize.width - 30);
/* 191 */     this.windowHeight = (screenSize.height - 40);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */   private void selectImage()
/*     */   {
/* 198 */     String computerName = null;
/*     */     try {
/* 200 */       InetAddress localaddress = InetAddress.getLocalHost();
/* 201 */       computerName = localaddress.getHostName();
/*     */     }
/*     */     catch (UnknownHostException e) {
/* 204 */       System.err.println("Cannot detect local host : " + e);
/*     */     }
/*     */     
/*     */ 
/*     */ 
/*     */ 
/* 210 */     if (computerName.equals("name")) { this.path = "C:\\DigiGraphDirectory";
/*     */     }
/*     */     
/* 213 */     FileChooser fc = new FileChooser(this.path);
/* 214 */     this.imageName = fc.selectFile();
/* 215 */     if (!fc.fileFound()) {
/* 216 */       System.out.println("Class DigiGraph: No successful selection of an image file occurred");
/* 217 */       System.exit(0);
/*     */     }
/* 219 */     this.imagePath = fc.getPathName();
/*     */     
/* 221 */     int lastDot = this.imagePath.lastIndexOf('.');
/* 222 */     this.extension = this.imagePath.substring(lastDot + 1);
/* 223 */     if (this.extension.equalsIgnoreCase("gif")) this.imageFormat = 1;
/* 224 */     if (this.extension.equalsIgnoreCase("jpg")) this.imageFormat = 2;
/* 225 */     if (this.extension.equalsIgnoreCase("jpeg")) this.imageFormat = 2;
/* 226 */     if (this.extension.equalsIgnoreCase("jpe")) this.imageFormat = 2;
/* 227 */     if (this.extension.equalsIgnoreCase("jfif")) this.imageFormat = 2;
/* 228 */     if (this.extension.equalsIgnoreCase("png")) this.imageFormat = 3;
/*     */   }
/*     */   
/*     */   private void setImage()
/*     */   {
/* 233 */     this.pic = Toolkit.getDefaultToolkit().getImage(this.imagePath);
/*     */   }
/*     */   
/*     */   private void outputFileChoice()
/*     */   {
/* 238 */     int posdot = this.imagePath.lastIndexOf('.');
/* 239 */     this.outputFile = (this.imagePath.substring(0, posdot) + "_digitized.txt");
/* 240 */     this.outputFile = Db.readLine("Enter output file name ", this.outputFile);
/* 241 */     this.fout = new FileOutput(this.outputFile);
/* 242 */     this.trunc = Db.readInt("Enter number of decimal places required in output data ", this.trunc);
/*     */   }
/*     */   
/*     */   public void setTruncation(int trunc)
/*     */   {
/* 247 */     this.trunc = trunc;
/*     */   }
/*     */   
/*     */   public void setRotationTolerance(double tol)
/*     */   {
/* 252 */     this.angleTolerance = tol;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */   public void noPlot()
/*     */   {
/* 259 */     this.plotOpt = false;
/*     */   }
/*     */   
/*     */   public void setPath(String path)
/*     */   {
/* 264 */     this.path = path;
/*     */   }
/*     */   
/*     */   public void setWindowHeight(int windowHeight)
/*     */   {
/* 269 */     this.windowHeight = windowHeight;
/*     */   }
/*     */   
/*     */   public void setWindowWidth(int windowWidth)
/*     */   {
/* 274 */     this.windowWidth = windowWidth;
/*     */   }
/*     */   
/*     */   public void setCloseChoice(int choice)
/*     */   {
/* 279 */     this.closeChoice = choice;
/*     */   }
/*     */   
/*     */ 
/*     */   public void keepIdenticalPoints()
/*     */   {
/* 285 */     this.noIdentical = false;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */   public void paint(Graphics g)
/*     */   {
/* 292 */     graph(g);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */   public void digitize()
/*     */   {
/* 299 */     this.window.setSize(this.windowWidth, this.windowHeight);
/*     */     
/*     */ 
/* 302 */     this.window.getContentPane().setBackground(Color.white);
/*     */     
/*     */ 
/* 305 */     if (this.closeChoice == 1) {
/* 306 */       this.window.setDefaultCloseOperation(3);
/*     */     }
/*     */     else {
/* 309 */       this.window.setDefaultCloseOperation(1);
/*     */     }
/*     */     
/*     */ 
/* 313 */     this.window.getContentPane().add("Center", this);
/*     */     
/*     */ 
/* 316 */     this.window.pack();
/* 317 */     this.window.setResizable(true);
/* 318 */     this.window.toFront();
/*     */     
/*     */ 
/* 321 */     this.window.setVisible(true);
/*     */   }
/*     */   
/*     */ 
/*     */   public void digitise()
/*     */   {
/* 327 */     digitize();
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   private void graph(Graphics g)
/*     */   {
/* 335 */     g.drawImage(this.pic, 10, 30, this);
/* 336 */     if (!this.resize) {
/* 337 */       g.drawString("RIGHT click anywhere on the screen", 5, 10);
/* 338 */       int width = this.pic.getWidth(null);
/* 339 */       int height = this.pic.getHeight(null);
/* 340 */       System.out.println(width + " xxx " + height);
/* 341 */       g.drawString("  ", 5, 10);
/* 342 */       double factor = (this.windowHeight - 30) / height;
/* 343 */       if ((int)(width * factor) > this.windowWidth - 10) factor = (this.windowWidth - 10) / width;
/* 344 */       height = (int)((height - 30) * factor * 0.95D);
/* 345 */       width = (int)((width - 10) * factor + 0.95D);
/* 346 */       this.pic = this.pic.getScaledInstance(width, height, 1);
/* 347 */       g.drawImage(this.pic, 10, 30, this);
/* 348 */       this.resize = true;
/*     */     }
/*     */     
/*     */ 
/* 352 */     boolean test = true;
/* 353 */     if ((this.xPos == 0) && (this.yPos == 0)) test = false;
/* 354 */     if (test) { cursorDoneSign(g, this.xPos, this.yPos);
/*     */     }
/*     */     
/*     */ 
/* 358 */     if (!this.digitizationDone)
/* 359 */       switch (this.iCounter) {
/* 360 */       case 0:  g.drawString("RIGHT click on lower Y-axis calibration point", 5, 10);
/* 361 */         break;
/* 362 */       case 1:  if (this.noYlow) {
/* 363 */           this.lowYvalue = Db.readDouble("Enter lower Y-axis calibration value");
/* 364 */           this.noYlow = false;
/*     */         }
/* 366 */         g.drawString("RIGHT click on higher Y-axis calibration point", 5, 10);
/* 367 */         break;
/* 368 */       case 2:  if (this.noYhigh) {
/* 369 */           this.highYvalue = Db.readDouble("Enter higher Y-axis calibration value");
/* 370 */           this.noYhigh = false;
/*     */         }
/* 372 */         g.drawString("RIGHT click on lower X-axis calibration point", 5, 10);
/* 373 */         break;
/* 374 */       case 3:  if (this.noXlow) {
/* 375 */           this.lowXvalue = Db.readDouble("Enter lower X-axis calibration value");
/* 376 */           this.noXlow = false;
/*     */         }
/* 378 */         g.drawString("RIGHT click on higher X-axis calibration point", 5, 10);
/* 379 */         break;
/* 380 */       case 4:  if (this.noXhigh) {
/* 381 */           this.highXvalue = Db.readDouble("Enter higher X-axis calibration value");
/* 382 */           this.noXhigh = false;
/*     */         }
/* 384 */         g.drawString("LEFT click on points to be digitized [right click when finished digitizing]", 5, 10);
/* 385 */         break;
/* 386 */       default:  g.drawString("LEFT click on points to be digitized [right click when finished digitizing]", 5, 10);
/*     */         
/* 388 */         break;
/*     */       } else {
/* 390 */       g.drawString("You may now close this window", 5, 10);
/*     */     }
/*     */   }
/*     */   
/*     */   private void cursorDoneSign(Graphics g, int x, int y) {
/* 395 */     g.drawLine(x - 5, y, x + 5, y);
/* 396 */     g.drawLine(x, y - 5, x, y + 5);
/* 397 */     g.fillOval(x - 3, y - 3, 7, 7);
/*     */   }
/*     */   
/*     */ 
/*     */   public void mouseClicked(MouseEvent me)
/*     */   {
/* 403 */     if (!this.digitizationDone) {
/* 404 */       switch (this.iCounter) {
/*     */       case 0: 
/* 406 */         this.xPos = me.getX();
/* 407 */         this.yPos = me.getY();
/*     */         
/*     */ 
/* 410 */         this.button = me.getButton();
/*     */         
/* 412 */         if (this.button == 1) {
/* 413 */           this.sumX += this.xPos;
/* 414 */           this.sumY += this.yPos;
/* 415 */           this.iSum += 1;
/*     */         }
/* 417 */         else if (this.button == 3) {
/* 418 */           this.sumX += this.xPos;
/* 419 */           this.sumY += this.yPos;
/* 420 */           this.iSum += 1;
/* 421 */           this.lowYaxisXpixel = (this.sumX / this.iSum);
/* 422 */           this.lowYaxisYpixel = (this.windowHeight - this.sumY / this.iSum);
/* 423 */           this.iCounter += 1;
/* 424 */           this.sumX = 0;
/* 425 */           this.sumY = 0;
/* 426 */           this.iSum = 0; }
/* 427 */         break;
/*     */       
/*     */ 
/*     */       case 1: 
/* 431 */         this.xPos = me.getX();
/* 432 */         this.yPos = me.getY();
/*     */         
/*     */ 
/* 435 */         this.button = me.getButton();
/*     */         
/* 437 */         if (this.button == 1) {
/* 438 */           this.sumX += this.xPos;
/* 439 */           this.sumY += this.yPos;
/* 440 */           this.iSum += 1;
/*     */         }
/* 442 */         else if (this.button == 3) {
/* 443 */           this.sumX += this.xPos;
/* 444 */           this.sumY += this.yPos;
/* 445 */           this.iSum += 1;
/* 446 */           this.highYaxisXpixel = (this.sumX / this.iSum);
/* 447 */           this.highYaxisYpixel = (this.windowHeight - this.sumY / this.iSum);
/* 448 */           this.iCounter += 1;
/* 449 */           this.sumX = 0;
/* 450 */           this.sumY = 0;
/* 451 */           this.iSum = 0; }
/* 452 */         break;
/*     */       
/*     */       case 2: 
/* 455 */         this.xPos = me.getX();
/* 456 */         this.yPos = me.getY();
/*     */         
/*     */ 
/* 459 */         this.button = me.getButton();
/*     */         
/* 461 */         if (this.button == 1) {
/* 462 */           this.sumX += this.xPos;
/* 463 */           this.sumY += this.yPos;
/* 464 */           this.iSum += 1;
/*     */         }
/* 466 */         else if (this.button == 3) {
/* 467 */           this.sumX += this.xPos;
/* 468 */           this.sumY += this.yPos;
/* 469 */           this.iSum += 1;
/* 470 */           this.lowXaxisXpixel = (this.sumX / this.iSum);
/* 471 */           this.lowXaxisYpixel = (this.windowHeight - this.sumY / this.iSum);
/* 472 */           this.iCounter += 1;
/* 473 */           this.sumX = 0;
/* 474 */           this.sumY = 0;
/* 475 */           this.iSum = 0; }
/* 476 */         break;
/*     */       
/*     */       case 3: 
/* 479 */         this.xPos = me.getX();
/* 480 */         this.yPos = me.getY();
/*     */         
/*     */ 
/* 483 */         this.button = me.getButton();
/*     */         
/*     */ 
/* 486 */         PixelGrabber pixelGrabber = new PixelGrabber(this.pic, this.xPos, this.yPos, 1, 1, false);
/*     */         
/* 488 */         if (this.button == 1) {
/* 489 */           this.sumX += this.xPos;
/* 490 */           this.sumY += this.yPos;
/* 491 */           this.iSum += 1;
/*     */         }
/* 493 */         else if (this.button == 3) {
/* 494 */           this.sumX += this.xPos;
/* 495 */           this.sumY += this.yPos;
/* 496 */           this.iSum += 1;
/* 497 */           this.highXaxisXpixel = (this.sumX / this.iSum);
/* 498 */           this.highXaxisYpixel = (this.windowHeight - this.sumY / this.iSum);
/* 499 */           this.iCounter += 1;
/* 500 */           this.sumX = 0;
/* 501 */           this.sumY = 0;
/* 502 */           this.iSum = 0; }
/* 503 */         break;
/*     */       
/*     */ 
/*     */       default: 
/* 507 */         this.xPos = me.getX();
/* 508 */         this.yPos = me.getY();
/*     */         
/*     */ 
/* 511 */         this.button = me.getButton();
/* 512 */         if (this.button == 1) {
/* 513 */           this.xAndYvalues.add(new Integer(this.xPos));
/* 514 */           this.xAndYvalues.add(new Integer(this.yPos));
/*     */         }
/*     */         
/*     */ 
/* 518 */         if ((this.button == 3) && (this.xAndYvalues.size() / 2 != 0)) {
/* 519 */           outputData();
/* 520 */           this.digitizationDone = true;
/*     */         }
/*     */         break;
/*     */       }
/*     */       
/*     */     }
/* 526 */     repaint();
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */   private void outputData()
/*     */   {
/* 533 */     this.nData = (this.xAndYvalues.size() / 2);
/* 534 */     System.out.println("nData " + this.nData);
/* 535 */     this.xPositions = new double[this.nData];
/* 536 */     this.yPositions = new double[this.nData];
/* 537 */     this.xPosPixel = new double[this.nData];
/* 538 */     this.yPosPixel = new double[this.nData];
/*     */     
/* 540 */     int ii = 0;
/*     */     
/* 542 */     for (int i = 0; i < this.nData; i++) {
/* 543 */       int xx = ((Integer)this.xAndYvalues.get(ii)).intValue();
/* 544 */       ii++;
/* 545 */       int yy = ((Integer)this.xAndYvalues.get(ii)).intValue();
/* 546 */       ii++;
/* 547 */       this.xPosPixel[i] = xx;
/* 548 */       this.yPosPixel[i] = (this.windowHeight - yy);
/*     */     }
/*     */     
/*     */ 
/* 552 */     checkForRotation();
/*     */     
/*     */ 
/* 555 */     for (int i = 0; i < this.nData; i++) {
/* 556 */       this.xPositions[i] = (this.lowXvalue + (this.xPosPixel[i] - this.lowXaxisXpixel) * (this.highXvalue - this.lowXvalue) / (this.highXaxisXpixel - this.lowXaxisXpixel));
/* 557 */       this.yPositions[i] = (this.lowYvalue + (this.yPosPixel[i] - this.lowYaxisYpixel) * (this.highYvalue - this.lowYvalue) / (this.highYaxisYpixel - this.lowYaxisYpixel));
/*     */     }
/*     */     
/*     */ 
/* 561 */     if (this.noIdentical) { checkForIdenticalPoints();
/*     */     }
/*     */     
/* 564 */     String message = "Do you wish to increase number of data points\n";
/* 565 */     message = message + "using cubic spline interpolation?";
/* 566 */     boolean opt = Db.noYes(message);
/* 567 */     if (opt) {
/* 568 */       this.nInterpPoints = Db.readInt("Enter number of interpolation points", 200);
/* 569 */       interpolation();
/* 570 */       this.interpOpt = true;
/*     */ 
/*     */     }
/* 573 */     else if (this.plotOpt) { plotDigitisedPoints();
/*     */     }
/*     */     
/*     */ 
/* 577 */     this.fout.println("Digitization output for DigiGraph class (M. T. Flanagan Java Library)");
/* 578 */     this.fout.println();
/* 579 */     this.fout.dateAndTimeln();
/* 580 */     this.fout.println();
/* 581 */     this.fout.println("Image used in the digitization:                 " + this.imageName);
/* 582 */     this.fout.println("Location of the image used in the digitization: " + this.imagePath);
/* 583 */     this.fout.println();
/* 584 */     this.fout.println("X-axis skew angle    " + Fmath.truncate(this.angleXaxis, 4) + " degrees");
/* 585 */     this.fout.println("Y-axis skew angle    " + Fmath.truncate(this.angleYaxis, 4) + " degrees");
/* 586 */     this.fout.println("Axes mean skew angle " + Fmath.truncate(this.angleMean, 4) + " degrees");
/* 587 */     if (this.rotationDone) {
/* 588 */       this.fout.println("Axes and all points rotated to bring axes to normal position");
/*     */     }
/*     */     else {
/* 591 */       this.fout.println("No rotation of axes or points performed");
/*     */     }
/* 593 */     this.fout.println();
/* 594 */     this.fout.println("Number of digitized points: " + this.nData);
/* 595 */     this.fout.println();
/* 596 */     this.fout.printtab("X-value");
/* 597 */     this.fout.println("Y-value");
/*     */     
/* 599 */     for (int i = 0; i < this.nData; i++) {
/* 600 */       this.fout.printtab(Fmath.truncate(this.xPositions[i], this.trunc));
/* 601 */       this.fout.println(Fmath.truncate(this.yPositions[i], this.trunc));
/*     */     }
/* 603 */     this.fout.println();
/*     */     
/*     */ 
/* 606 */     if (this.interpOpt) {
/* 607 */       this.fout.println();
/* 608 */       this.fout.println("Interpolated data (cubic spline)");
/* 609 */       this.fout.println();
/* 610 */       this.fout.println("Number of interpolated points: " + this.nInterpPoints);
/* 611 */       this.fout.println();
/* 612 */       this.fout.printtab("X-value");
/* 613 */       this.fout.println("Y-value");
/* 614 */       for (int i = 0; i < this.nInterpPoints; i++) {
/* 615 */         this.fout.printtab(Fmath.truncate(this.xInterp[i], this.trunc));
/* 616 */         this.fout.println(Fmath.truncate(this.yInterp[i], this.trunc));
/*     */       }
/*     */     }
/*     */     
/* 620 */     this.fout.close();
/*     */   }
/*     */   
/*     */   private void checkForRotation()
/*     */   {
/* 625 */     double tangent = (this.highYaxisXpixel - this.lowYaxisXpixel) / (this.highYaxisYpixel - this.lowYaxisYpixel);
/* 626 */     this.angleYaxis = Math.toDegrees(Math.atan(tangent));
/* 627 */     tangent = (this.lowXaxisYpixel - this.highXaxisYpixel) / (this.highXaxisXpixel - this.lowXaxisXpixel);
/* 628 */     this.angleXaxis = Math.toDegrees(Math.atan(tangent));
/* 629 */     this.angleMean = ((this.angleXaxis + this.angleYaxis) / 2.0D);
/* 630 */     double absMean = Math.abs(this.angleMean);
/* 631 */     if ((absMean != 0.0D) && (absMean > this.angleTolerance)) { performRotation();
/*     */     }
/*     */   }
/*     */   
/*     */   private void performRotation()
/*     */   {
/* 637 */     double tangentX = (this.highXaxisYpixel - this.lowXaxisYpixel) / (this.highXaxisXpixel - this.lowXaxisXpixel);
/* 638 */     double interceptX = this.highXaxisYpixel - tangentX * this.highXaxisXpixel;
/* 639 */     double tangentY = (this.highYaxisYpixel - this.lowYaxisYpixel) / (this.highYaxisXpixel - this.lowYaxisXpixel);
/* 640 */     double interceptY = this.highYaxisYpixel - tangentY * this.highYaxisXpixel;
/* 641 */     double originX = (interceptX - interceptY) / (tangentY - tangentX);
/* 642 */     double originY = tangentY * originX + interceptY;
/*     */     
/*     */ 
/* 645 */     double angleMeanRad = Math.toRadians(this.angleMean);
/* 646 */     double cosphi = Math.cos(-angleMeanRad);
/* 647 */     double sinphi = Math.sin(-angleMeanRad);
/* 648 */     double highXaxisXpixelR = (this.highXaxisXpixel - originX) * cosphi + (this.highXaxisYpixel - originY) * sinphi + originX;
/* 649 */     double highXaxisYpixelR = -(this.highXaxisXpixel - originX) * sinphi + (this.highXaxisYpixel - originY) * cosphi + originY;
/* 650 */     double lowXaxisXpixelR = (this.lowXaxisXpixel - originX) * cosphi + (this.lowXaxisYpixel - originY) * sinphi + originX;
/* 651 */     double lowXaxisYpixelR = -(this.lowXaxisXpixel - originX) * sinphi + (this.lowXaxisYpixel - originY) * cosphi + originY;
/* 652 */     double highYaxisXpixelR = (this.highYaxisXpixel - originX) * cosphi + (this.highYaxisYpixel - originY) * sinphi + originX;
/* 653 */     double highYaxisYpixelR = -(this.highYaxisXpixel - originX) * sinphi + (this.highYaxisYpixel - originY) * cosphi + originY;
/* 654 */     double lowYaxisXpixelR = -(this.lowYaxisXpixel - originX) * cosphi + (this.lowYaxisYpixel - originY) * sinphi + originX;
/* 655 */     double lowYaxisYpixelR = (this.lowYaxisXpixel - originX) * sinphi + (this.lowYaxisYpixel - originY) * cosphi + originY;
/*     */     
/* 657 */     this.highXaxisXpixel = highXaxisXpixelR;
/* 658 */     this.highXaxisYpixel = highXaxisYpixelR;
/* 659 */     this.lowXaxisXpixel = lowXaxisXpixelR;
/* 660 */     this.lowXaxisYpixel = lowXaxisYpixelR;
/* 661 */     this.highYaxisXpixel = highYaxisXpixelR;
/* 662 */     this.highYaxisYpixel = highYaxisYpixelR;
/* 663 */     this.lowYaxisXpixel = lowYaxisXpixelR;
/* 664 */     this.lowYaxisYpixel = lowYaxisYpixelR;
/*     */     
/*     */ 
/* 667 */     for (int i = 0; i < this.nData; i++) {
/* 668 */       double xx = (this.xPosPixel[i] - originX) * cosphi + (this.yPosPixel[i] - originY) * sinphi + originX;
/* 669 */       double yy = -(this.xPosPixel[i] - originX) * sinphi + (this.yPosPixel[i] - originY) * cosphi + originY;
/* 670 */       this.xPosPixel[i] = xx;
/* 671 */       this.yPosPixel[i] = yy;
/*     */     }
/*     */     
/* 674 */     this.rotationDone = true;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */   public void mousePressed(MouseEvent me) {}
/*     */   
/*     */ 
/*     */ 
/*     */   public void mouseReleased(MouseEvent me) {}
/*     */   
/*     */ 
/*     */   public void mouseEntered(MouseEvent me)
/*     */   {
/* 688 */     this.mouseEntered = true;
/* 689 */     repaint();
/*     */   }
/*     */   
/*     */   public void mouseExited(MouseEvent me)
/*     */   {
/* 694 */     this.mouseEntered = false;
/* 695 */     repaint();
/*     */   }
/*     */   
/*     */ 
/*     */   private void interpolation()
/*     */   {
/* 701 */     this.xInterp = new double[this.nInterpPoints];
/* 702 */     this.yInterp = new double[this.nInterpPoints];
/*     */     
/*     */ 
/* 705 */     double incr = (this.xPositions[(this.nData - 1)] - this.xPositions[0]) / (this.nInterpPoints - 1);
/* 706 */     this.xInterp[0] = this.xPositions[0];
/* 707 */     for (int i = 1; i < this.nInterpPoints - 1; i++) {
/* 708 */       this.xInterp[i] = (this.xInterp[(i - 1)] + incr);
/*     */     }
/* 710 */     this.xInterp[(this.nInterpPoints - 1)] = this.xPositions[(this.nData - 1)];
/*     */     
/* 712 */     CubicSpline cs = new CubicSpline(this.xPositions, this.yPositions);
/*     */     
/*     */ 
/* 715 */     for (int i = 0; i < this.nInterpPoints; i++) { this.yInterp[i] = cs.interpolate(this.xInterp[i]);
/*     */     }
/*     */     
/* 718 */     if (this.plotOpt) {
/* 719 */       int nMax = Math.max(this.nInterpPoints, this.nData);
/* 720 */       double[][] plotData = PlotGraph.data(2, nMax);
/*     */       
/* 722 */       plotData[0] = this.xPositions;
/* 723 */       plotData[1] = this.yPositions;
/* 724 */       plotData[2] = this.xInterp;
/* 725 */       plotData[3] = this.yInterp;
/*     */       
/* 727 */       PlotGraph pg = new PlotGraph(plotData);
/*     */       
/* 729 */       pg.setGraphTitle("Cubic Spline Interpolation of Digitised Points");
/* 730 */       pg.setGraphTitle2(this.imagePath);
/*     */       
/* 732 */       pg.setXaxisLegend("x");
/* 733 */       pg.setYaxisLegend("y");
/*     */       
/* 735 */       int[] lineOpt = { 0, 3 };
/* 736 */       pg.setLine(lineOpt);
/* 737 */       int[] pointOpt = { 4, 0 };
/* 738 */       pg.setPoint(pointOpt);
/*     */       
/* 740 */       pg.plot();
/*     */     }
/*     */   }
/*     */   
/*     */ 
/*     */   public void checkForIdenticalPoints()
/*     */   {
/* 747 */     int nP = this.nData;
/* 748 */     boolean test1 = true;
/* 749 */     int ii = 0;
/* 750 */     while (test1) {
/* 751 */       boolean test2 = true;
/* 752 */       int jj = ii + 1;
/* 753 */       while (test2) {
/* 754 */         System.out.println("ii " + ii + "  jj  " + jj);
/* 755 */         if ((this.xPositions[ii] == this.xPositions[jj]) && (this.yPositions[ii] == this.yPositions[jj])) {
/* 756 */           System.out.print("Class DigiGraph: two identical points, " + this.xPositions[ii] + ", " + this.yPositions[ii]);
/* 757 */           System.out.println(", in data array at indices " + ii + " and " + jj + ", one point removed");
/*     */           
/* 759 */           for (int i = jj; i < nP; i++) {
/* 760 */             this.xPositions[(i - 1)] = this.xPositions[i];
/* 761 */             this.yPositions[(i - 1)] = this.yPositions[i];
/*     */           }
/* 763 */           nP--;
/* 764 */           if (nP - 1 == ii) test2 = false;
/*     */         }
/*     */         else {
/* 767 */           jj++;
/* 768 */           if (jj >= nP) test2 = false;
/*     */         }
/*     */       }
/* 771 */       ii++;
/* 772 */       if (ii >= nP - 1) { test1 = false;
/*     */       }
/*     */     }
/*     */     
/* 776 */     if (nP != this.nData) {
/* 777 */       double[] holdX = new double[nP];
/* 778 */       double[] holdY = new double[nP];
/* 779 */       for (int i = 0; i < nP; i++) {
/* 780 */         holdX[i] = this.xPositions[i];
/* 781 */         holdY[i] = this.yPositions[i];
/*     */       }
/* 783 */       this.xPositions = holdX;
/* 784 */       this.yPositions = holdY;
/* 785 */       this.nData = nP;
/*     */     }
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */   private void plotDigitisedPoints()
/*     */   {
/* 793 */     double[][] plotData = PlotGraph.data(1, this.nData);
/*     */     
/* 795 */     plotData[0] = this.xPositions;
/* 796 */     plotData[1] = this.yPositions;
/*     */     
/* 798 */     PlotGraph pg = new PlotGraph(plotData);
/*     */     
/* 800 */     pg.setGraphTitle("Plot of the Digitised Points");
/* 801 */     pg.setGraphTitle2(this.imagePath);
/*     */     
/* 803 */     pg.setXaxisLegend("x");
/* 804 */     pg.setYaxisLegend("y");
/*     */     
/* 806 */     int[] lineOpt = { 0 };
/* 807 */     pg.setLine(lineOpt);
/* 808 */     int[] pointOpt = { 4 };
/* 809 */     pg.setPoint(pointOpt);
/*     */     
/* 811 */     pg.plot();
/*     */   }
/*     */ }


/* Location:              /home/menick/Scrivania/ProcessMining/CNMining/CNMining.jar!/flanagan/io/DigiGraph.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       0.7.1
 */