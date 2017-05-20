package org.apache.lucene.search;

import java.io.IOException;
import java.rmi.Remote;
import org.apache.lucene.document.Document;
import org.apache.lucene.index.Term;

public abstract interface Searchable
  extends Remote
{
  public abstract void search(Query paramQuery, Filter paramFilter, HitCollector paramHitCollector)
    throws IOException;
  
  public abstract void close()
    throws IOException;
  
  public abstract int docFreq(Term paramTerm)
    throws IOException;
  
  public abstract int maxDoc()
    throws IOException;
  
  public abstract TopDocs search(Query paramQuery, Filter paramFilter, int paramInt)
    throws IOException;
  
  public abstract Document doc(int paramInt)
    throws IOException;
  
  public abstract Query rewrite(Query paramQuery)
    throws IOException;
  
  public abstract Explanation explain(Query paramQuery, int paramInt)
    throws IOException;
  
  public abstract TopFieldDocs search(Query paramQuery, Filter paramFilter, int paramInt, Sort paramSort)
    throws IOException;
}


/* Location:              /home/menick/Scrivania/ProcessMining/CNMining/CNMining.jar!/org/apache/lucene/search/Searchable.class
 * Java compiler version: 2 (46.0)
 * JD-Core Version:       0.7.1
 */