package org.apache.lucene.analysis.standard;

import java.io.IOException;

public abstract interface CharStream
{
  public abstract char readChar()
    throws IOException;
  
  /**
   * @deprecated
   */
  public abstract int getColumn();
  
  /**
   * @deprecated
   */
  public abstract int getLine();
  
  public abstract int getEndColumn();
  
  public abstract int getEndLine();
  
  public abstract int getBeginColumn();
  
  public abstract int getBeginLine();
  
  public abstract void backup(int paramInt);
  
  public abstract char BeginToken()
    throws IOException;
  
  public abstract String GetImage();
  
  public abstract char[] GetSuffix(int paramInt);
  
  public abstract void Done();
}


/* Location:              /home/menick/Scrivania/ProcessMining/CNMining/CNMining.jar!/org/apache/lucene/analysis/standard/CharStream.class
 * Java compiler version: 2 (46.0)
 * JD-Core Version:       0.7.1
 */