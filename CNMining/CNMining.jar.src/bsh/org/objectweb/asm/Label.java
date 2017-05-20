package bsh.org.objectweb.asm;

public class Label
{
  CodeWriter owner;
  boolean resolved;
  int position;
  private int referenceCount;
  private int[] srcAndRefPositions;
  int beginStackSize;
  int maxStackSize;
  Edge successors;
  Label next;
  boolean pushed;
  
  void put(CodeWriter paramCodeWriter, ByteVector paramByteVector, int paramInt, boolean paramBoolean)
  {
    if (this.resolved)
    {
      if (paramBoolean) {
        paramByteVector.put4(this.position - paramInt);
      } else {
        paramByteVector.put2(this.position - paramInt);
      }
    }
    else if (paramBoolean)
    {
      addReference(-1 - paramInt, paramByteVector.length);
      paramByteVector.put4(-1);
    }
    else
    {
      addReference(paramInt, paramByteVector.length);
      paramByteVector.put2(-1);
    }
  }
  
  private void addReference(int paramInt1, int paramInt2)
  {
    if (this.srcAndRefPositions == null) {
      this.srcAndRefPositions = new int[6];
    }
    if (this.referenceCount >= this.srcAndRefPositions.length)
    {
      int[] arrayOfInt = new int[this.srcAndRefPositions.length + 6];
      System.arraycopy(this.srcAndRefPositions, 0, arrayOfInt, 0, this.srcAndRefPositions.length);
      this.srcAndRefPositions = arrayOfInt;
    }
    this.srcAndRefPositions[(this.referenceCount++)] = paramInt1;
    this.srcAndRefPositions[(this.referenceCount++)] = paramInt2;
  }
  
  boolean resolve(CodeWriter paramCodeWriter, int paramInt, byte[] paramArrayOfByte)
  {
    boolean bool = false;
    this.resolved = true;
    this.position = paramInt;
    int i = 0;
    while (i < this.referenceCount)
    {
      int j = this.srcAndRefPositions[(i++)];
      int k = this.srcAndRefPositions[(i++)];
      int m;
      if (j >= 0)
      {
        m = paramInt - j;
        if ((m < 32768) || (m > 32767))
        {
          int n = paramArrayOfByte[(k - 1)] & 0xFF;
          if (n <= 168) {
            paramArrayOfByte[(k - 1)] = ((byte)(n + 49));
          } else {
            paramArrayOfByte[(k - 1)] = ((byte)(n + 20));
          }
          bool = true;
        }
        paramArrayOfByte[(k++)] = ((byte)(m >>> 8));
        paramArrayOfByte[k] = ((byte)m);
      }
      else
      {
        m = paramInt + j + 1;
        paramArrayOfByte[(k++)] = ((byte)(m >>> 24));
        paramArrayOfByte[(k++)] = ((byte)(m >>> 16));
        paramArrayOfByte[(k++)] = ((byte)(m >>> 8));
        paramArrayOfByte[k] = ((byte)m);
      }
    }
    return bool;
  }
}


/* Location:              /home/menick/Scrivania/ProcessMining/CNMining/CNMining.jar!/bsh/org/objectweb/asm/Label.class
 * Java compiler version: 2 (46.0)
 * JD-Core Version:       0.7.1
 */