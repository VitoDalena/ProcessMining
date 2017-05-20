package org.apache.lucene.search;

import java.io.IOException;
import java.io.Serializable;
import java.util.BitSet;
import org.apache.lucene.index.IndexReader;

public abstract class Filter
  implements Serializable
{
  public abstract BitSet bits(IndexReader paramIndexReader)
    throws IOException;
}


/* Location:              /home/menick/Scrivania/ProcessMining/CNMining/CNMining.jar!/org/apache/lucene/search/Filter.class
 * Java compiler version: 2 (46.0)
 * JD-Core Version:       0.7.1
 */