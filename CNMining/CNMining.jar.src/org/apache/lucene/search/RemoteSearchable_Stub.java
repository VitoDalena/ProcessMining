package org.apache.lucene.search;

import java.io.DataInput;
import java.io.DataOutput;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;
import java.lang.reflect.Method;
import java.rmi.MarshalException;
import java.rmi.Remote;
import java.rmi.UnexpectedException;
import java.rmi.UnmarshalException;
import java.rmi.server.Operation;
import java.rmi.server.RemoteCall;
import java.rmi.server.RemoteObject;
import java.rmi.server.RemoteRef;
import java.rmi.server.RemoteStub;
import org.apache.lucene.document.Document;
import org.apache.lucene.index.Term;

public final class RemoteSearchable_Stub
  extends RemoteStub
  implements Searchable, Remote
{
  private static final Operation[] operations = { new Operation("void close()"), new Operation("org.apache.lucene.document.Document doc(int)"), new Operation("int docFreq(org.apache.lucene.index.Term)"), new Operation("org.apache.lucene.search.Explanation explain(org.apache.lucene.search.Query, int)"), new Operation("int maxDoc()"), new Operation("org.apache.lucene.search.Query rewrite(org.apache.lucene.search.Query)"), new Operation("org.apache.lucene.search.TopDocs search(org.apache.lucene.search.Query, org.apache.lucene.search.Filter, int)"), new Operation("org.apache.lucene.search.TopFieldDocs search(org.apache.lucene.search.Query, org.apache.lucene.search.Filter, int, org.apache.lucene.search.Sort)"), new Operation("void search(org.apache.lucene.search.Query, org.apache.lucene.search.Filter, org.apache.lucene.search.HitCollector)") };
  private static final long interfaceHash = 9187915733717020351L;
  private static final long serialVersionUID = 2L;
  private static boolean useNewInvoke;
  private static Method $method_close_0;
  private static Method $method_doc_1;
  private static Method $method_docFreq_2;
  private static Method $method_explain_3;
  private static Method $method_maxDoc_4;
  private static Method $method_rewrite_5;
  private static Method $method_search_6;
  private static Method $method_search_7;
  private static Method $method_search_8;
  
  static
  {
    try
    {
      RemoteRef.class.getMethod("invoke", new Class[] { Remote.class, Method.class, new Object[0].getClass(), Long.TYPE });
      useNewInvoke = true;
      $method_close_0 = Searchable.class.getMethod("close", new Class[0]);
      $method_doc_1 = Searchable.class.getMethod("doc", new Class[] { Integer.TYPE });
      $method_docFreq_2 = Searchable.class.getMethod("docFreq", new Class[] { Term.class });
      $method_explain_3 = Searchable.class.getMethod("explain", new Class[] { Query.class, Integer.TYPE });
      $method_maxDoc_4 = Searchable.class.getMethod("maxDoc", new Class[0]);
      $method_rewrite_5 = Searchable.class.getMethod("rewrite", new Class[] { Query.class });
      $method_search_6 = Searchable.class.getMethod("search", new Class[] { Query.class, Filter.class, Integer.TYPE });
      $method_search_7 = Searchable.class.getMethod("search", new Class[] { Query.class, Filter.class, Integer.TYPE, Sort.class });
      $method_search_8 = Searchable.class.getMethod("search", new Class[] { Query.class, Filter.class, HitCollector.class });
    }
    catch (NoSuchMethodException localNoSuchMethodException)
    {
      useNewInvoke = false;
    }
  }
  
  public RemoteSearchable_Stub() {}
  
  public RemoteSearchable_Stub(RemoteRef paramRemoteRef)
  {
    super(paramRemoteRef);
  }
  
  public void close()
    throws IOException
  {
    try
    {
      if (useNewInvoke)
      {
        this.ref.invoke(this, $method_close_0, null, -4742752445160157748L);
      }
      else
      {
        RemoteCall localRemoteCall = this.ref.newCall(this, operations, 0, 9187915733717020351L);
        this.ref.invoke(localRemoteCall);
        this.ref.done(localRemoteCall);
      }
    }
    catch (RuntimeException localRuntimeException)
    {
      throw localRuntimeException;
    }
    catch (IOException localIOException)
    {
      throw localIOException;
    }
    catch (Exception localException)
    {
      throw new UnexpectedException("undeclared checked exception", localException);
    }
  }
  
  public Document doc(int paramInt)
    throws IOException
  {
    try
    {
      if (useNewInvoke)
      {
        localObject1 = this.ref.invoke(this, $method_doc_1, new Object[] { new Integer(paramInt) }, -3205250690722925732L);
        return (Document)localObject1;
      }
      Object localObject1 = this.ref.newCall(this, operations, 1, 9187915733717020351L);
      try
      {
        ObjectOutput localObjectOutput = ((RemoteCall)localObject1).getOutputStream();
        localObjectOutput.writeInt(paramInt);
      }
      catch (IOException localIOException2)
      {
        throw new MarshalException("error marshalling arguments", localIOException2);
      }
      this.ref.invoke((RemoteCall)localObject1);
      Document localDocument;
      try
      {
        ObjectInput localObjectInput = ((RemoteCall)localObject1).getInputStream();
        localDocument = (Document)localObjectInput.readObject();
      }
      catch (IOException localIOException3)
      {
        throw new UnmarshalException("error unmarshalling return", localIOException3);
      }
      catch (ClassNotFoundException localClassNotFoundException)
      {
        throw new UnmarshalException("error unmarshalling return", localClassNotFoundException);
      }
      finally
      {
        this.ref.done((RemoteCall)localObject1);
      }
      return localDocument;
    }
    catch (RuntimeException localRuntimeException)
    {
      throw localRuntimeException;
    }
    catch (IOException localIOException1)
    {
      throw localIOException1;
    }
    catch (Exception localException)
    {
      throw new UnexpectedException("undeclared checked exception", localException);
    }
  }
  
  public int docFreq(Term paramTerm)
    throws IOException
  {
    try
    {
      if (useNewInvoke)
      {
        localObject1 = this.ref.invoke(this, $method_docFreq_2, new Object[] { paramTerm }, -7822449680410044026L);
        return ((Integer)localObject1).intValue();
      }
      Object localObject1 = this.ref.newCall(this, operations, 2, 9187915733717020351L);
      try
      {
        ObjectOutput localObjectOutput = ((RemoteCall)localObject1).getOutputStream();
        localObjectOutput.writeObject(paramTerm);
      }
      catch (IOException localIOException2)
      {
        throw new MarshalException("error marshalling arguments", localIOException2);
      }
      this.ref.invoke((RemoteCall)localObject1);
      int i;
      try
      {
        ObjectInput localObjectInput = ((RemoteCall)localObject1).getInputStream();
        i = localObjectInput.readInt();
      }
      catch (IOException localIOException3)
      {
        throw new UnmarshalException("error unmarshalling return", localIOException3);
      }
      finally
      {
        this.ref.done((RemoteCall)localObject1);
      }
      return i;
    }
    catch (RuntimeException localRuntimeException)
    {
      throw localRuntimeException;
    }
    catch (IOException localIOException1)
    {
      throw localIOException1;
    }
    catch (Exception localException)
    {
      throw new UnexpectedException("undeclared checked exception", localException);
    }
  }
  
  public Explanation explain(Query paramQuery, int paramInt)
    throws IOException
  {
    try
    {
      if (useNewInvoke)
      {
        localObject1 = this.ref.invoke(this, $method_explain_3, new Object[] { paramQuery, new Integer(paramInt) }, 6631951962358117252L);
        return (Explanation)localObject1;
      }
      Object localObject1 = this.ref.newCall(this, operations, 3, 9187915733717020351L);
      try
      {
        ObjectOutput localObjectOutput = ((RemoteCall)localObject1).getOutputStream();
        localObjectOutput.writeObject(paramQuery);
        localObjectOutput.writeInt(paramInt);
      }
      catch (IOException localIOException2)
      {
        throw new MarshalException("error marshalling arguments", localIOException2);
      }
      this.ref.invoke((RemoteCall)localObject1);
      Explanation localExplanation;
      try
      {
        ObjectInput localObjectInput = ((RemoteCall)localObject1).getInputStream();
        localExplanation = (Explanation)localObjectInput.readObject();
      }
      catch (IOException localIOException3)
      {
        throw new UnmarshalException("error unmarshalling return", localIOException3);
      }
      catch (ClassNotFoundException localClassNotFoundException)
      {
        throw new UnmarshalException("error unmarshalling return", localClassNotFoundException);
      }
      finally
      {
        this.ref.done((RemoteCall)localObject1);
      }
      return localExplanation;
    }
    catch (RuntimeException localRuntimeException)
    {
      throw localRuntimeException;
    }
    catch (IOException localIOException1)
    {
      throw localIOException1;
    }
    catch (Exception localException)
    {
      throw new UnexpectedException("undeclared checked exception", localException);
    }
  }
  
  public int maxDoc()
    throws IOException
  {
    try
    {
      if (useNewInvoke)
      {
        localObject1 = this.ref.invoke(this, $method_maxDoc_4, null, -2054052621300804366L);
        return ((Integer)localObject1).intValue();
      }
      Object localObject1 = this.ref.newCall(this, operations, 4, 9187915733717020351L);
      this.ref.invoke((RemoteCall)localObject1);
      int i;
      try
      {
        ObjectInput localObjectInput = ((RemoteCall)localObject1).getInputStream();
        i = localObjectInput.readInt();
      }
      catch (IOException localIOException2)
      {
        throw new UnmarshalException("error unmarshalling return", localIOException2);
      }
      finally
      {
        this.ref.done((RemoteCall)localObject1);
      }
      return i;
    }
    catch (RuntimeException localRuntimeException)
    {
      throw localRuntimeException;
    }
    catch (IOException localIOException1)
    {
      throw localIOException1;
    }
    catch (Exception localException)
    {
      throw new UnexpectedException("undeclared checked exception", localException);
    }
  }
  
  public Query rewrite(Query paramQuery)
    throws IOException
  {
    try
    {
      if (useNewInvoke)
      {
        localObject1 = this.ref.invoke(this, $method_rewrite_5, new Object[] { paramQuery }, 6327992687997160630L);
        return (Query)localObject1;
      }
      Object localObject1 = this.ref.newCall(this, operations, 5, 9187915733717020351L);
      try
      {
        ObjectOutput localObjectOutput = ((RemoteCall)localObject1).getOutputStream();
        localObjectOutput.writeObject(paramQuery);
      }
      catch (IOException localIOException2)
      {
        throw new MarshalException("error marshalling arguments", localIOException2);
      }
      this.ref.invoke((RemoteCall)localObject1);
      Query localQuery;
      try
      {
        ObjectInput localObjectInput = ((RemoteCall)localObject1).getInputStream();
        localQuery = (Query)localObjectInput.readObject();
      }
      catch (IOException localIOException3)
      {
        throw new UnmarshalException("error unmarshalling return", localIOException3);
      }
      catch (ClassNotFoundException localClassNotFoundException)
      {
        throw new UnmarshalException("error unmarshalling return", localClassNotFoundException);
      }
      finally
      {
        this.ref.done((RemoteCall)localObject1);
      }
      return localQuery;
    }
    catch (RuntimeException localRuntimeException)
    {
      throw localRuntimeException;
    }
    catch (IOException localIOException1)
    {
      throw localIOException1;
    }
    catch (Exception localException)
    {
      throw new UnexpectedException("undeclared checked exception", localException);
    }
  }
  
  public TopDocs search(Query paramQuery, Filter paramFilter, int paramInt)
    throws IOException
  {
    try
    {
      if (useNewInvoke)
      {
        localObject1 = this.ref.invoke(this, $method_search_6, new Object[] { paramQuery, paramFilter, new Integer(paramInt) }, -2779958099349270815L);
        return (TopDocs)localObject1;
      }
      Object localObject1 = this.ref.newCall(this, operations, 6, 9187915733717020351L);
      try
      {
        ObjectOutput localObjectOutput = ((RemoteCall)localObject1).getOutputStream();
        localObjectOutput.writeObject(paramQuery);
        localObjectOutput.writeObject(paramFilter);
        localObjectOutput.writeInt(paramInt);
      }
      catch (IOException localIOException2)
      {
        throw new MarshalException("error marshalling arguments", localIOException2);
      }
      this.ref.invoke((RemoteCall)localObject1);
      TopDocs localTopDocs;
      try
      {
        ObjectInput localObjectInput = ((RemoteCall)localObject1).getInputStream();
        localTopDocs = (TopDocs)localObjectInput.readObject();
      }
      catch (IOException localIOException3)
      {
        throw new UnmarshalException("error unmarshalling return", localIOException3);
      }
      catch (ClassNotFoundException localClassNotFoundException)
      {
        throw new UnmarshalException("error unmarshalling return", localClassNotFoundException);
      }
      finally
      {
        this.ref.done((RemoteCall)localObject1);
      }
      return localTopDocs;
    }
    catch (RuntimeException localRuntimeException)
    {
      throw localRuntimeException;
    }
    catch (IOException localIOException1)
    {
      throw localIOException1;
    }
    catch (Exception localException)
    {
      throw new UnexpectedException("undeclared checked exception", localException);
    }
  }
  
  public TopFieldDocs search(Query paramQuery, Filter paramFilter, int paramInt, Sort paramSort)
    throws IOException
  {
    try
    {
      if (useNewInvoke)
      {
        localObject1 = this.ref.invoke(this, $method_search_7, new Object[] { paramQuery, paramFilter, new Integer(paramInt), paramSort }, 9040332765774971388L);
        return (TopFieldDocs)localObject1;
      }
      Object localObject1 = this.ref.newCall(this, operations, 7, 9187915733717020351L);
      try
      {
        ObjectOutput localObjectOutput = ((RemoteCall)localObject1).getOutputStream();
        localObjectOutput.writeObject(paramQuery);
        localObjectOutput.writeObject(paramFilter);
        localObjectOutput.writeInt(paramInt);
        localObjectOutput.writeObject(paramSort);
      }
      catch (IOException localIOException2)
      {
        throw new MarshalException("error marshalling arguments", localIOException2);
      }
      this.ref.invoke((RemoteCall)localObject1);
      TopFieldDocs localTopFieldDocs;
      try
      {
        ObjectInput localObjectInput = ((RemoteCall)localObject1).getInputStream();
        localTopFieldDocs = (TopFieldDocs)localObjectInput.readObject();
      }
      catch (IOException localIOException3)
      {
        throw new UnmarshalException("error unmarshalling return", localIOException3);
      }
      catch (ClassNotFoundException localClassNotFoundException)
      {
        throw new UnmarshalException("error unmarshalling return", localClassNotFoundException);
      }
      finally
      {
        this.ref.done((RemoteCall)localObject1);
      }
      return localTopFieldDocs;
    }
    catch (RuntimeException localRuntimeException)
    {
      throw localRuntimeException;
    }
    catch (IOException localIOException1)
    {
      throw localIOException1;
    }
    catch (Exception localException)
    {
      throw new UnexpectedException("undeclared checked exception", localException);
    }
  }
  
  public void search(Query paramQuery, Filter paramFilter, HitCollector paramHitCollector)
    throws IOException
  {
    try
    {
      if (useNewInvoke)
      {
        this.ref.invoke(this, $method_search_8, new Object[] { paramQuery, paramFilter, paramHitCollector }, 8210132726663281038L);
      }
      else
      {
        RemoteCall localRemoteCall = this.ref.newCall(this, operations, 8, 9187915733717020351L);
        try
        {
          ObjectOutput localObjectOutput = localRemoteCall.getOutputStream();
          localObjectOutput.writeObject(paramQuery);
          localObjectOutput.writeObject(paramFilter);
          localObjectOutput.writeObject(paramHitCollector);
        }
        catch (IOException localIOException2)
        {
          throw new MarshalException("error marshalling arguments", localIOException2);
        }
        this.ref.invoke(localRemoteCall);
        this.ref.done(localRemoteCall);
      }
    }
    catch (RuntimeException localRuntimeException)
    {
      throw localRuntimeException;
    }
    catch (IOException localIOException1)
    {
      throw localIOException1;
    }
    catch (Exception localException)
    {
      throw new UnexpectedException("undeclared checked exception", localException);
    }
  }
}


/* Location:              /home/menick/Scrivania/ProcessMining/CNMining/CNMining.jar!/org/apache/lucene/search/RemoteSearchable_Stub.class
 * Java compiler version: 1 (45.3)
 * JD-Core Version:       0.7.1
 */