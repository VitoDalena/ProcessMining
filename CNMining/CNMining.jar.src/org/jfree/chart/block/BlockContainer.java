/*     */ package org.jfree.chart.block;
/*     */ 
/*     */ import java.awt.Graphics2D;
/*     */ import java.awt.geom.Rectangle2D;
/*     */ import java.awt.geom.Rectangle2D.Double;
/*     */ import java.io.Serializable;
/*     */ import java.util.ArrayList;
/*     */ import java.util.Collections;
/*     */ import java.util.Iterator;
/*     */ import java.util.List;
/*     */ import org.jfree.chart.entity.EntityCollection;
/*     */ import org.jfree.chart.entity.StandardEntityCollection;
/*     */ import org.jfree.ui.Size2D;
/*     */ import org.jfree.util.PublicCloneable;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class BlockContainer
/*     */   extends AbstractBlock
/*     */   implements Block, Cloneable, PublicCloneable, Serializable
/*     */ {
/*     */   private static final long serialVersionUID = 8199508075695195293L;
/*     */   private List blocks;
/*     */   private Arrangement arrangement;
/*     */   
/*     */   public BlockContainer()
/*     */   {
/*  83 */     this(new BorderArrangement());
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public BlockContainer(Arrangement arrangement)
/*     */   {
/*  93 */     if (arrangement == null) {
/*  94 */       throw new IllegalArgumentException("Null 'arrangement' argument.");
/*     */     }
/*  96 */     this.arrangement = arrangement;
/*  97 */     this.blocks = new ArrayList();
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public Arrangement getArrangement()
/*     */   {
/* 106 */     return this.arrangement;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public void setArrangement(Arrangement arrangement)
/*     */   {
/* 115 */     if (arrangement == null) {
/* 116 */       throw new IllegalArgumentException("Null 'arrangement' argument.");
/*     */     }
/* 118 */     this.arrangement = arrangement;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public boolean isEmpty()
/*     */   {
/* 128 */     return this.blocks.isEmpty();
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public List getBlocks()
/*     */   {
/* 138 */     return Collections.unmodifiableList(this.blocks);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public void add(Block block)
/*     */   {
/* 147 */     add(block, null);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public void add(Block block, Object key)
/*     */   {
/* 157 */     this.blocks.add(block);
/* 158 */     this.arrangement.add(block, key);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */   public void clear()
/*     */   {
/* 165 */     this.blocks.clear();
/* 166 */     this.arrangement.clear();
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
/*     */   public Size2D arrange(Graphics2D g2, RectangleConstraint constraint)
/*     */   {
/* 179 */     return this.arrangement.arrange(this, g2, constraint);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public void draw(Graphics2D g2, Rectangle2D area)
/*     */   {
/* 189 */     draw(g2, area, null);
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
/*     */   public Object draw(Graphics2D g2, Rectangle2D area, Object params)
/*     */   {
/* 204 */     EntityBlockParams ebp = null;
/* 205 */     StandardEntityCollection sec = null;
/* 206 */     if ((params instanceof EntityBlockParams)) {
/* 207 */       ebp = (EntityBlockParams)params;
/* 208 */       if (ebp.getGenerateEntities()) {
/* 209 */         sec = new StandardEntityCollection();
/*     */       }
/*     */     }
/* 212 */     Rectangle2D contentArea = (Rectangle2D)area.clone();
/* 213 */     contentArea = trimMargin(contentArea);
/* 214 */     drawBorder(g2, contentArea);
/* 215 */     contentArea = trimBorder(contentArea);
/* 216 */     contentArea = trimPadding(contentArea);
/* 217 */     Iterator iterator = this.blocks.iterator();
/* 218 */     while (iterator.hasNext()) {
/* 219 */       Block block = (Block)iterator.next();
/* 220 */       Rectangle2D bounds = block.getBounds();
/* 221 */       Rectangle2D drawArea = new Rectangle2D.Double(bounds.getX() + area.getX(), bounds.getY() + area.getY(), bounds.getWidth(), bounds.getHeight());
/*     */       
/*     */ 
/* 224 */       Object r = block.draw(g2, drawArea, params);
/* 225 */       if ((sec != null) && 
/* 226 */         ((r instanceof EntityBlockResult))) {
/* 227 */         EntityBlockResult ebr = (EntityBlockResult)r;
/* 228 */         EntityCollection ec = ebr.getEntityCollection();
/* 229 */         sec.addAll(ec);
/*     */       }
/*     */     }
/*     */     
/* 233 */     BlockResult result = null;
/* 234 */     if (sec != null) {
/* 235 */       result = new BlockResult();
/* 236 */       result.setEntityCollection(sec);
/*     */     }
/* 238 */     return result;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public boolean equals(Object obj)
/*     */   {
/* 249 */     if (obj == this) {
/* 250 */       return true;
/*     */     }
/* 252 */     if (!(obj instanceof BlockContainer)) {
/* 253 */       return false;
/*     */     }
/* 255 */     if (!super.equals(obj)) {
/* 256 */       return false;
/*     */     }
/* 258 */     BlockContainer that = (BlockContainer)obj;
/* 259 */     if (!this.arrangement.equals(that.arrangement)) {
/* 260 */       return false;
/*     */     }
/* 262 */     if (!this.blocks.equals(that.blocks)) {
/* 263 */       return false;
/*     */     }
/* 265 */     return true;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public Object clone()
/*     */     throws CloneNotSupportedException
/*     */   {
/* 276 */     BlockContainer clone = (BlockContainer)super.clone();
/*     */     
/* 278 */     return clone;
/*     */   }
/*     */ }


/* Location:              /home/menick/Scrivania/ProcessMining/CNMining/CNMining.jar!/org/jfree/chart/block/BlockContainer.class
 * Java compiler version: 3 (47.0)
 * JD-Core Version:       0.7.1
 */