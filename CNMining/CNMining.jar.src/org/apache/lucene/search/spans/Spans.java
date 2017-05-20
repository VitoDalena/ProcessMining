package org.apache.lucene.search.spans;

import java.io.IOException;

public abstract interface Spans
{
  public abstract boolean next()
    throws IOException;
  
  public abstract boolean skipTo(int paramInt)
    throws IOException;
  
  public abstract int doc();
  
  public abstract int start();
  
  public abstract int end();
}


/* Location:              /home/menick/Scrivania/ProcessMining/CNMining/CNMining.jar!/org/apache/lucene/search/spans/Spans.class
 * Java compiler version: 2 (46.0)
 * JD-Core Version:       0.7.1
 */