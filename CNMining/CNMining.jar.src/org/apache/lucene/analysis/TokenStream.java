package org.apache.lucene.analysis;

import java.io.IOException;

public abstract class TokenStream
{
  public abstract Token next()
    throws IOException;
  
  public void close()
    throws IOException
  {}
}


/* Location:              /home/menick/Scrivania/ProcessMining/CNMining/CNMining.jar!/org/apache/lucene/analysis/TokenStream.class
 * Java compiler version: 2 (46.0)
 * JD-Core Version:       0.7.1
 */