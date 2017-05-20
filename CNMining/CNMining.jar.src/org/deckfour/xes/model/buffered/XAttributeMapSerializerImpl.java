/*     */ package org.deckfour.xes.model.buffered;
/*     */ 
/*     */ import java.io.DataInput;
/*     */ import java.io.DataOutput;
/*     */ import java.io.IOException;
/*     */ import org.deckfour.xes.extension.XExtension;
/*     */ import org.deckfour.xes.extension.XExtensionManager;
/*     */ import org.deckfour.xes.factory.XFactory;
/*     */ import org.deckfour.xes.factory.XFactoryRegistry;
/*     */ import org.deckfour.xes.id.XID;
/*     */ import org.deckfour.xes.model.XAttribute;
/*     */ import org.deckfour.xes.model.XAttributeBoolean;
/*     */ import org.deckfour.xes.model.XAttributeContinuous;
/*     */ import org.deckfour.xes.model.XAttributeDiscrete;
/*     */ import org.deckfour.xes.model.XAttributeID;
/*     */ import org.deckfour.xes.model.XAttributeLiteral;
/*     */ import org.deckfour.xes.model.XAttributeMap;
/*     */ import org.deckfour.xes.model.XAttributeTimestamp;
/*     */ import org.deckfour.xes.model.impl.XAttributeMapImpl;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class XAttributeMapSerializerImpl
/*     */   implements XAttributeMapSerializer
/*     */ {
/*     */   public void serialize(XAttributeMap map, DataOutput out)
/*     */     throws IOException
/*     */   {
/*  73 */     out.writeInt(map.size());
/*  74 */     for (XAttribute attribute : map.values())
/*     */     {
/*  76 */       out.writeUTF(attribute.getKey());
/*     */       
/*  78 */       XExtension extension = attribute.getExtension();
/*  79 */       if (extension == null) {
/*  80 */         out.writeInt(-1);
/*     */       } else {
/*  82 */         out.writeInt(XExtensionManager.instance().getIndex(extension));
/*     */       }
/*     */       
/*  85 */       if ((attribute instanceof XAttributeBoolean)) {
/*  86 */         out.writeByte(0);
/*  87 */         out.writeBoolean(((XAttributeBoolean)attribute).getValue());
/*  88 */       } else if ((attribute instanceof XAttributeContinuous)) {
/*  89 */         out.writeByte(1);
/*  90 */         out.writeDouble(((XAttributeContinuous)attribute).getValue());
/*  91 */       } else if ((attribute instanceof XAttributeDiscrete)) {
/*  92 */         out.writeByte(2);
/*  93 */         out.writeLong(((XAttributeDiscrete)attribute).getValue());
/*  94 */       } else if ((attribute instanceof XAttributeLiteral)) {
/*  95 */         out.writeByte(3);
/*  96 */         out.writeUTF(((XAttributeLiteral)attribute).getValue());
/*  97 */       } else if ((attribute instanceof XAttributeTimestamp)) {
/*  98 */         out.writeByte(4);
/*  99 */         out.writeLong(((XAttributeTimestamp)attribute).getValueMillis());
/* 100 */       } else if ((attribute instanceof XAttributeID)) {
/* 101 */         out.writeByte(5);
/* 102 */         XID.write(((XAttributeID)attribute).getValue(), out);
/*     */       } else {
/* 104 */         throw new AssertionError("Unknown attribute type, cannot serialize!");
/*     */       }
/*     */       
/* 107 */       serialize(attribute.getAttributes(), out);
/*     */     }
/*     */   }
/*     */   
/*     */ 
/*     */   public XAttributeMap deserialize(DataInput in)
/*     */     throws IOException
/*     */   {
/* 115 */     XFactory factory = (XFactory)XFactoryRegistry.instance().currentDefault();
/* 116 */     int size = in.readInt();
/* 117 */     XAttributeMapImpl map = new XAttributeMapImpl(size * 2);
/* 118 */     for (int i = 0; i < size; i++)
/*     */     {
/* 120 */       String key = in.readUTF();
/*     */       
/* 122 */       int ext = in.readInt();
/* 123 */       XExtension extension = null;
/* 124 */       if (ext >= 0) {
/* 125 */         extension = XExtensionManager.instance().getByIndex(ext);
/*     */       }
/*     */       
/*     */ 
/* 129 */       byte type = in.readByte();
/* 130 */       XAttribute attribute; if (type == 0) {
/* 131 */         boolean value = in.readBoolean();
/* 132 */         attribute = factory.createAttributeBoolean(key, value, extension); } else { XAttribute attribute;
/* 133 */         if (type == 1) {
/* 134 */           double value = in.readDouble();
/* 135 */           attribute = factory.createAttributeContinuous(key, value, extension); } else { XAttribute attribute;
/* 136 */           if (type == 2) {
/* 137 */             long value = in.readLong();
/* 138 */             attribute = factory.createAttributeDiscrete(key, value, extension); } else { XAttribute attribute;
/* 139 */             if (type == 3) {
/* 140 */               String value = in.readUTF();
/* 141 */               attribute = factory.createAttributeLiteral(key, value, extension); } else { XAttribute attribute;
/* 142 */               if (type == 4) {
/* 143 */                 long value = in.readLong();
/* 144 */                 attribute = factory.createAttributeTimestamp(key, value, extension); } else { XAttribute attribute;
/* 145 */                 if (type == 5) {
/* 146 */                   XID value = XID.read(in);
/* 147 */                   attribute = factory.createAttributeID(key, value, extension);
/*     */                 } else {
/* 149 */                   throw new AssertionError("Unknown attribute type, cannot deserialize!");
/*     */                 } } } } } }
/*     */       XAttribute attribute;
/* 152 */       XAttributeMap metamap = deserialize(in);
/* 153 */       attribute.setAttributes(metamap);
/*     */       
/* 155 */       map.put(key, attribute);
/*     */     }
/* 157 */     return map;
/*     */   }
/*     */ }


/* Location:              /home/menick/Scrivania/ProcessMining/CNMining/CNMining.jar!/org/deckfour/xes/model/buffered/XAttributeMapSerializerImpl.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */