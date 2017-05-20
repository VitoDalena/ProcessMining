package org.apache.lucene.index;

import java.io.IOException;

public abstract interface TermDocs
{
  public abstract void seek(Term paramTerm)
    throws IOException;
  
  public abstract void seek(TermEnum paramTermEnum)
    throws IOException;
  
  public abstract int doc();
  
  public abstract int freq();
  
  public abstract boolean next()
    throws IOException;
  
  public abstract int read(int[] paramArrayOfInt1, int[] paramArrayOfInt2)
    throws IOException;
  
  public abstract boolean skipTo(int paramInt)
    throws IOException;
  
  public abstract void close()
    throws IOException;
}


/* Location:              /home/menick/Scrivania/ProcessMining/CNMining/CNMining.jar!/org/apache/lucene/index/TermDocs.class
 * Java compiler version: 2 (46.0)
 * JD-Core Version:       0.7.1
 */