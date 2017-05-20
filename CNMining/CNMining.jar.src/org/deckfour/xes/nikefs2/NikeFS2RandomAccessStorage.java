package org.deckfour.xes.nikefs2;

import java.io.DataInput;
import java.io.DataOutput;
import java.io.IOException;

public abstract interface NikeFS2RandomAccessStorage
  extends DataOutput, DataInput
{
  public abstract void close()
    throws IOException;
  
  public abstract long getFilePointer()
    throws IOException;
  
  public abstract long length()
    throws IOException;
  
  public abstract void seek(long paramLong)
    throws IOException;
  
  public abstract int skipBytes(int paramInt)
    throws IOException;
  
  public abstract NikeFS2RandomAccessStorage copy()
    throws IOException;
}


/* Location:              /home/menick/Scrivania/ProcessMining/CNMining/CNMining.jar!/org/deckfour/xes/nikefs2/NikeFS2RandomAccessStorage.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */