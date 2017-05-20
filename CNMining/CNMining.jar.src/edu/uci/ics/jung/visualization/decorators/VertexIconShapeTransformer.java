/*     */ package edu.uci.ics.jung.visualization.decorators;
/*     */ 
/*     */ import edu.uci.ics.jung.visualization.FourPassImageShaper;
/*     */ import java.awt.Image;
/*     */ import java.awt.Rectangle;
/*     */ import java.awt.Shape;
/*     */ import java.awt.geom.AffineTransform;
/*     */ import java.util.HashMap;
/*     */ import java.util.Map;
/*     */ import javax.swing.Icon;
/*     */ import javax.swing.ImageIcon;
/*     */ import org.apache.commons.collections15.Transformer;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class VertexIconShapeTransformer<V>
/*     */   implements Transformer<V, Shape>
/*     */ {
/*  36 */   protected Map<Image, Shape> shapeMap = new HashMap();
/*     */   
/*     */   protected Map<V, Icon> iconMap;
/*     */   
/*     */   protected Transformer<V, Shape> delegate;
/*     */   
/*     */   public VertexIconShapeTransformer(Transformer<V, Shape> delegate)
/*     */   {
/*  44 */     this.delegate = delegate;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */   public Transformer<V, Shape> getDelegate()
/*     */   {
/*  51 */     return this.delegate;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */   public void setDelegate(Transformer<V, Shape> delegate)
/*     */   {
/*  58 */     this.delegate = delegate;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   public Shape transform(V v)
/*     */   {
/*  66 */     Icon icon = (Icon)this.iconMap.get(v);
/*  67 */     if ((icon != null) && ((icon instanceof ImageIcon))) {
/*  68 */       Image image = ((ImageIcon)icon).getImage();
/*  69 */       Shape shape = (Shape)this.shapeMap.get(image);
/*  70 */       if (shape == null) {
/*  71 */         shape = FourPassImageShaper.getShape(image, 30);
/*  72 */         if ((shape.getBounds().getWidth() > 0.0D) && (shape.getBounds().getHeight() > 0.0D))
/*     */         {
/*     */ 
/*     */ 
/*  76 */           int width = image.getWidth(null);
/*  77 */           int height = image.getHeight(null);
/*  78 */           AffineTransform transform = AffineTransform.getTranslateInstance(-width / 2, -height / 2);
/*     */           
/*  80 */           shape = transform.createTransformedShape(shape);
/*  81 */           this.shapeMap.put(image, shape);
/*     */         }
/*     */       }
/*  84 */       return shape;
/*     */     }
/*  86 */     return (Shape)this.delegate.transform(v);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   public Map<V, Icon> getIconMap()
/*     */   {
/*  94 */     return this.iconMap;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */   public void setIconMap(Map<V, Icon> iconMap)
/*     */   {
/* 101 */     this.iconMap = iconMap;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */   public Map<Image, Shape> getShapeMap()
/*     */   {
/* 108 */     return this.shapeMap;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */   public void setShapeMap(Map<Image, Shape> shapeMap)
/*     */   {
/* 115 */     this.shapeMap = shapeMap;
/*     */   }
/*     */ }


/* Location:              /home/menick/Scrivania/ProcessMining/CNMining/CNMining.jar!/edu/uci/ics/jung/visualization/decorators/VertexIconShapeTransformer.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       0.7.1
 */