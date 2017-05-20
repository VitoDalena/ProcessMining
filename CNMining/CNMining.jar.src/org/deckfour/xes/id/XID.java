/*     */ package org.deckfour.xes.id;
/*     */ 
/*     */ import java.io.DataInput;
/*     */ import java.io.DataInputStream;
/*     */ import java.io.DataOutput;
/*     */ import java.io.DataOutputStream;
/*     */ import java.io.IOException;
/*     */ import java.util.UUID;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class XID
/*     */   implements Cloneable, Comparable<XID>
/*     */ {
/*     */   private final UUID uuid;
/*     */   
/*     */   public static XID parse(String idString)
/*     */   {
/*  64 */     UUID uuid = UUID.fromString(idString);
/*  65 */     return new XID(uuid);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public static XID read(DataInputStream dis)
/*     */     throws IOException
/*     */   {
/*  76 */     long msb = dis.readLong();
/*  77 */     long lsb = dis.readLong();
/*  78 */     return new XID(msb, lsb);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public static XID read(DataInput in)
/*     */     throws IOException
/*     */   {
/*  89 */     long msb = in.readLong();
/*  90 */     long lsb = in.readLong();
/*  91 */     return new XID(msb, lsb);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public static void write(XID id, DataOutputStream dos)
/*     */     throws IOException
/*     */   {
/* 103 */     dos.writeLong(id.uuid.getMostSignificantBits());
/* 104 */     dos.writeLong(id.uuid.getLeastSignificantBits());
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public static void write(XID id, DataOutput out)
/*     */     throws IOException
/*     */   {
/* 116 */     out.writeLong(id.uuid.getMostSignificantBits());
/* 117 */     out.writeLong(id.uuid.getLeastSignificantBits());
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public XID()
/*     */   {
/* 129 */     this.uuid = UUID.randomUUID();
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public XID(long msb, long lsb)
/*     */   {
/* 141 */     this.uuid = new UUID(msb, lsb);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public XID(UUID uuid)
/*     */   {
/* 151 */     this.uuid = uuid;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */   public boolean equals(Object obj)
/*     */   {
/* 158 */     if ((obj instanceof XID)) {
/* 159 */       XID other = (XID)obj;
/* 160 */       return this.uuid.equals(other.uuid);
/*     */     }
/* 162 */     return false;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   public String toString()
/*     */   {
/* 170 */     return this.uuid.toString().toUpperCase();
/*     */   }
/*     */   
/*     */ 
/*     */   public Object clone()
/*     */   {
/*     */     XID clone;
/*     */     try
/*     */     {
/* 179 */       clone = (XID)super.clone();
/*     */     } catch (CloneNotSupportedException e) {
/* 181 */       e.printStackTrace();
/* 182 */       clone = null;
/*     */     }
/* 184 */     return clone;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */   public int hashCode()
/*     */   {
/* 191 */     return this.uuid.hashCode();
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public int compareTo(XID o)
/*     */   {
/* 201 */     return this.uuid.compareTo(o.uuid);
/*     */   }
/*     */ }


/* Location:              /home/menick/Scrivania/ProcessMining/CNMining/CNMining.jar!/org/deckfour/xes/id/XID.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */