package org.apache.lucene.search;

import java.io.IOException;
import java.io.Serializable;
import org.apache.lucene.index.IndexReader;

public abstract interface SortComparatorSource
  extends Serializable
{
  public abstract ScoreDocComparator newComparator(IndexReader paramIndexReader, String paramString)
    throws IOException;
}


/* Location:              /home/menick/Scrivania/ProcessMining/CNMining/CNMining.jar!/org/apache/lucene/search/SortComparatorSource.class
 * Java compiler version: 2 (46.0)
 * JD-Core Version:       0.7.1
 */