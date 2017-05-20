/*     */ package org.processmining.plugins.core;
/*     */ 
/*     */ import com.carrotsearch.hppc.ObjectArrayList;
/*     */ import java.io.IOException;
/*     */ import java.io.PrintStream;
/*     */ import javax.xml.parsers.ParserConfigurationException;
/*     */ import javax.xml.parsers.SAXParser;
/*     */ import javax.xml.parsers.SAXParserFactory;
/*     */ import org.xml.sax.Attributes;
/*     */ import org.xml.sax.SAXException;
/*     */ import org.xml.sax.helpers.DefaultHandler;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class ConstraintParser
/*     */   extends DefaultHandler
/*     */ {
/*     */   private int nrP;
/*     */   private int nrE;
/*     */   private int nrNP;
/*     */   private int nrNE;
/*     */   private final ObjectArrayList<Constraint> constraints;
/*     */   private String tempVal;
/*     */   private Constraint tempConstraint;
/*     */   private final String pathToConstraints;
/*     */   
/*     */   public static void main(String[] args)
/*     */   {
/*  33 */     ConstraintParser cp = new ConstraintParser("C:\\Users\\flupia\\Desktop\\Constraints.xml");
/*  34 */     cp.run();
/*     */   }
/*     */   
/*     */   public int getNrP() {
/*  38 */     return this.nrP;
/*     */   }
/*     */   
/*     */   public int getNrE() {
/*  42 */     return this.nrE;
/*     */   }
/*     */   
/*     */   public int getNrNP() {
/*  46 */     return this.nrNP;
/*     */   }
/*     */   
/*     */   public int getNrNE() {
/*  50 */     return this.nrNE;
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
/*     */   public ConstraintParser(String pathToConstraints)
/*     */   {
/*  64 */     this.pathToConstraints = pathToConstraints;
/*  65 */     this.constraints = new ObjectArrayList();
/*     */   }
/*     */   
/*     */   public void characters(char[] ch, int start, int length)
/*     */     throws SAXException
/*     */   {
/*  71 */     this.tempVal = new String(ch, start, length);
/*     */   }
/*     */   
/*     */ 
/*     */   public void endElement(String uri, String localName, String qName)
/*     */     throws SAXException
/*     */   {
/*  78 */     if (qName.equalsIgnoreCase("Constraint"))
/*     */     {
/*  80 */       this.constraints.add(this.tempConstraint);
/*     */     }
/*  82 */     else if (qName.equalsIgnoreCase("Head")) {
/*  83 */       this.tempConstraint.addHead(this.tempVal);
/*  84 */     } else if (qName.equalsIgnoreCase("Body")) {
/*  85 */       this.tempConstraint.addBody(this.tempVal);
/*     */     }
/*     */   }
/*     */   
/*     */   public ObjectArrayList<Constraint> getConstraints() {
/*  90 */     return this.constraints;
/*     */   }
/*     */   
/*     */ 
/*     */   private boolean parseDocument()
/*     */   {
/*  96 */     boolean validDocument = true;
/*  97 */     SAXParserFactory spf = SAXParserFactory.newInstance();
/*     */     
/*     */     try
/*     */     {
/* 101 */       SAXParser sp = spf.newSAXParser();
/*     */       
/*     */ 
/* 104 */       sp.parse(this.pathToConstraints, this);
/*     */     }
/*     */     catch (SAXException se) {
/* 107 */       se.printStackTrace();
/* 108 */       validDocument = false;
/*     */     } catch (ParserConfigurationException pce) {
/* 110 */       pce.printStackTrace();
/* 111 */       validDocument = false;
/*     */     } catch (IOException ie) {
/* 113 */       ie.printStackTrace();
/* 114 */       validDocument = false;
/*     */     }
/* 116 */     return validDocument;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   private void printData()
/*     */   {
/* 124 */     System.out.println("No of Constraints '" + this.constraints.size() + "'.");
/* 125 */     for (int i = 0; i < this.constraints.size(); i++) {
/* 126 */       Constraint it = (Constraint)this.constraints.get(i);
/* 127 */       System.out.println(it.toString());
/*     */     }
/*     */   }
/*     */   
/*     */   public boolean run() {
/* 132 */     return parseDocument();
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   public void startElement(String uri, String localName, String qName, Attributes attributes)
/*     */     throws SAXException
/*     */   {
/* 141 */     this.tempVal = "";
/* 142 */     if (qName.equalsIgnoreCase("Constraint"))
/*     */     {
/* 144 */       this.tempConstraint = new Constraint();
/* 145 */       String type = attributes.getValue("type");
/*     */       
/* 147 */       if (type.equalsIgnoreCase("edge")) {
/* 148 */         this.tempConstraint.setPathConstraint(false);
/* 149 */         this.tempConstraint.setConstraintType(true);
/* 150 */         this.nrE += 1;
/* 151 */       } else if (type.equalsIgnoreCase("notEdge")) {
/* 152 */         this.tempConstraint.setPathConstraint(false);
/* 153 */         this.tempConstraint.setConstraintType(false);
/* 154 */         this.nrNE += 1;
/* 155 */       } else if (type.equalsIgnoreCase("path")) {
/* 156 */         this.tempConstraint.setPathConstraint(true);
/* 157 */         this.tempConstraint.setConstraintType(true);
/* 158 */         this.nrP += 1;
/* 159 */       } else if (type.equalsIgnoreCase("notPath")) {
/* 160 */         this.nrNP += 1;
/* 161 */         this.tempConstraint.setPathConstraint(true);
/* 162 */         this.tempConstraint.setConstraintType(false);
/*     */       }
/*     */     }
/*     */   }
/*     */ }


/* Location:              /home/menick/Scrivania/ProcessMining/CNMining/CNMining.jar!/org/processmining/plugins/core/ConstraintParser.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       0.7.1
 */