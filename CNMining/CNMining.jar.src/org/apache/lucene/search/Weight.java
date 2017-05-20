package org.apache.lucene.search;

import java.io.IOException;
import java.io.Serializable;
import org.apache.lucene.index.IndexReader;

public abstract interface Weight
  extends Serializable
{
  public abstract Query getQuery();
  
  public abstract float getValue();
  
  public abstract float sumOfSquaredWeights()
    throws IOException;
  
  public abstract void normalize(float paramFloat);
  
  public abstract Scorer scorer(IndexReader paramIndexReader)
    throws IOException;
  
  public abstract Explanation explain(IndexReader paramIndexReader, int paramInt)
    throws IOException;
}


/* Location:              /home/menick/Scrivania/ProcessMining/CNMining/CNMining.jar!/org/apache/lucene/search/Weight.class
 * Java compiler version: 2 (46.0)
 * JD-Core Version:       0.7.1
 */