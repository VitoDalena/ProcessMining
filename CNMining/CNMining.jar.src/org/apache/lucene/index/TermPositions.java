package org.apache.lucene.index;

import java.io.IOException;

public abstract interface TermPositions
  extends TermDocs
{
  public abstract int nextPosition()
    throws IOException;
}


/* Location:              /home/menick/Scrivania/ProcessMining/CNMining/CNMining.jar!/org/apache/lucene/index/TermPositions.class
 * Java compiler version: 2 (46.0)
 * JD-Core Version:       0.7.1
 */