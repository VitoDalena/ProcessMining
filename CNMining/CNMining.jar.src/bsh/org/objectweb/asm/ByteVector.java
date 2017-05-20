package bsh.org.objectweb.asm;

final class ByteVector
{
  byte[] data;
  int length;
  
  public ByteVector()
  {
    this.data = new byte[64];
  }
  
  public ByteVector(int paramInt)
  {
    this.data = new byte[paramInt];
  }
  
  public ByteVector put1(int paramInt)
  {
    int i = this.length;
    if (i + 1 > this.data.length) {
      enlarge(1);
    }
    this.data[(i++)] = ((byte)paramInt);
    this.length = i;
    return this;
  }
  
  public ByteVector put11(int paramInt1, int paramInt2)
  {
    int i = this.length;
    if (i + 2 > this.data.length) {
      enlarge(2);
    }
    byte[] arrayOfByte = this.data;
    arrayOfByte[(i++)] = ((byte)paramInt1);
    arrayOfByte[(i++)] = ((byte)paramInt2);
    this.length = i;
    return this;
  }
  
  public ByteVector put2(int paramInt)
  {
    int i = this.length;
    if (i + 2 > this.data.length) {
      enlarge(2);
    }
    byte[] arrayOfByte = this.data;
    arrayOfByte[(i++)] = ((byte)(paramInt >>> 8));
    arrayOfByte[(i++)] = ((byte)paramInt);
    this.length = i;
    return this;
  }
  
  public ByteVector put12(int paramInt1, int paramInt2)
  {
    int i = this.length;
    if (i + 3 > this.data.length) {
      enlarge(3);
    }
    byte[] arrayOfByte = this.data;
    arrayOfByte[(i++)] = ((byte)paramInt1);
    arrayOfByte[(i++)] = ((byte)(paramInt2 >>> 8));
    arrayOfByte[(i++)] = ((byte)paramInt2);
    this.length = i;
    return this;
  }
  
  public ByteVector put4(int paramInt)
  {
    int i = this.length;
    if (i + 4 > this.data.length) {
      enlarge(4);
    }
    byte[] arrayOfByte = this.data;
    arrayOfByte[(i++)] = ((byte)(paramInt >>> 24));
    arrayOfByte[(i++)] = ((byte)(paramInt >>> 16));
    arrayOfByte[(i++)] = ((byte)(paramInt >>> 8));
    arrayOfByte[(i++)] = ((byte)paramInt);
    this.length = i;
    return this;
  }
  
  public ByteVector put8(long paramLong)
  {
    int i = this.length;
    if (i + 8 > this.data.length) {
      enlarge(8);
    }
    byte[] arrayOfByte = this.data;
    int j = (int)(paramLong >>> 32);
    arrayOfByte[(i++)] = ((byte)(j >>> 24));
    arrayOfByte[(i++)] = ((byte)(j >>> 16));
    arrayOfByte[(i++)] = ((byte)(j >>> 8));
    arrayOfByte[(i++)] = ((byte)j);
    j = (int)paramLong;
    arrayOfByte[(i++)] = ((byte)(j >>> 24));
    arrayOfByte[(i++)] = ((byte)(j >>> 16));
    arrayOfByte[(i++)] = ((byte)(j >>> 8));
    arrayOfByte[(i++)] = ((byte)j);
    this.length = i;
    return this;
  }
  
  public ByteVector putUTF(String paramString)
  {
    int i = paramString.length();
    int j = 0;
    for (int k = 0; k < i; k++)
    {
      m = paramString.charAt(k);
      if ((m >= 1) && (m <= 127)) {
        j++;
      } else if (m > 2047) {
        j += 3;
      } else {
        j += 2;
      }
    }
    if (j > 65535) {
      throw new IllegalArgumentException();
    }
    int m = this.length;
    if (m + 2 + j > this.data.length) {
      enlarge(2 + j);
    }
    byte[] arrayOfByte = this.data;
    arrayOfByte[(m++)] = ((byte)(j >>> 8));
    arrayOfByte[(m++)] = ((byte)j);
    for (int n = 0; n < i; n++)
    {
      int i1 = paramString.charAt(n);
      if ((i1 >= 1) && (i1 <= 127))
      {
        arrayOfByte[(m++)] = ((byte)i1);
      }
      else if (i1 > 2047)
      {
        arrayOfByte[(m++)] = ((byte)(0xE0 | i1 >> 12 & 0xF));
        arrayOfByte[(m++)] = ((byte)(0x80 | i1 >> 6 & 0x3F));
        arrayOfByte[(m++)] = ((byte)(0x80 | i1 & 0x3F));
      }
      else
      {
        arrayOfByte[(m++)] = ((byte)(0xC0 | i1 >> 6 & 0x1F));
        arrayOfByte[(m++)] = ((byte)(0x80 | i1 & 0x3F));
      }
    }
    this.length = m;
    return this;
  }
  
  public ByteVector putByteArray(byte[] paramArrayOfByte, int paramInt1, int paramInt2)
  {
    if (this.length + paramInt2 > this.data.length) {
      enlarge(paramInt2);
    }
    if (paramArrayOfByte != null) {
      System.arraycopy(paramArrayOfByte, paramInt1, this.data, this.length, paramInt2);
    }
    this.length += paramInt2;
    return this;
  }
  
  private void enlarge(int paramInt)
  {
    byte[] arrayOfByte = new byte[Math.max(2 * this.data.length, this.length + paramInt)];
    System.arraycopy(this.data, 0, arrayOfByte, 0, this.length);
    this.data = arrayOfByte;
  }
}


/* Location:              /home/menick/Scrivania/ProcessMining/CNMining/CNMining.jar!/bsh/org/objectweb/asm/ByteVector.class
 * Java compiler version: 2 (46.0)
 * JD-Core Version:       0.7.1
 */