package org.apache.lucene.search;

import java.io.DataInput;
import java.io.DataOutput;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;
import java.rmi.MarshalException;
import java.rmi.Remote;
import java.rmi.UnmarshalException;
import java.rmi.server.Operation;
import java.rmi.server.RemoteCall;
import java.rmi.server.Skeleton;
import java.rmi.server.SkeletonMismatchException;
import org.apache.lucene.document.Document;
import org.apache.lucene.index.Term;

public final class RemoteSearchable_Skel
  implements Skeleton
{
  private static final Operation[] operations = { new Operation("void close()"), new Operation("org.apache.lucene.document.Document doc(int)"), new Operation("int docFreq(org.apache.lucene.index.Term)"), new Operation("org.apache.lucene.search.Explanation explain(org.apache.lucene.search.Query, int)"), new Operation("int maxDoc()"), new Operation("org.apache.lucene.search.Query rewrite(org.apache.lucene.search.Query)"), new Operation("org.apache.lucene.search.TopDocs search(org.apache.lucene.search.Query, org.apache.lucene.search.Filter, int)"), new Operation("org.apache.lucene.search.TopFieldDocs search(org.apache.lucene.search.Query, org.apache.lucene.search.Filter, int, org.apache.lucene.search.Sort)"), new Operation("void search(org.apache.lucene.search.Query, org.apache.lucene.search.Filter, org.apache.lucene.search.HitCollector)") };
  private static final long interfaceHash = 9187915733717020351L;
  
  public void dispatch(Remote paramRemote, RemoteCall paramRemoteCall, int paramInt, long paramLong)
    throws Exception
  {
    if (paramInt < 0)
    {
      if (paramLong == -4742752445160157748L) {
        paramInt = 0;
      } else if (paramLong == -3205250690722925732L) {
        paramInt = 1;
      } else if (paramLong == -7822449680410044026L) {
        paramInt = 2;
      } else if (paramLong == 6631951962358117252L) {
        paramInt = 3;
      } else if (paramLong == -2054052621300804366L) {
        paramInt = 4;
      } else if (paramLong == 6327992687997160630L) {
        paramInt = 5;
      } else if (paramLong == -2779958099349270815L) {
        paramInt = 6;
      } else if (paramLong == 9040332765774971388L) {
        paramInt = 7;
      } else if (paramLong == 8210132726663281038L) {
        paramInt = 8;
      } else {
        throw new UnmarshalException("invalid method hash");
      }
    }
    else if (paramLong != 9187915733717020351L) {
      throw new SkeletonMismatchException("interface hash mismatch");
    }
    RemoteSearchable localRemoteSearchable = (RemoteSearchable)paramRemote;
    Object localObject1;
    int k;
    Query localQuery;
    Object localObject5;
    int m;
    Object localObject12;
    switch (paramInt)
    {
    case 0: 
      paramRemoteCall.releaseInputStream();
      localRemoteSearchable.close();
      try
      {
        paramRemoteCall.getResultStream(true);
      }
      catch (IOException localIOException1)
      {
        throw new MarshalException("error marshalling return", localIOException1);
      }
    case 1: 
      int i;
      try
      {
        ObjectInput localObjectInput1 = paramRemoteCall.getInputStream();
        i = localObjectInput1.readInt();
      }
      catch (IOException localIOException6)
      {
        throw new UnmarshalException("error unmarshalling arguments", localIOException6);
      }
      finally
      {
        paramRemoteCall.releaseInputStream();
      }
      Document localDocument = localRemoteSearchable.doc(i);
      try
      {
        ObjectOutput localObjectOutput2 = paramRemoteCall.getResultStream(true);
        localObjectOutput2.writeObject(localDocument);
      }
      catch (IOException localIOException3)
      {
        throw new MarshalException("error marshalling return", localIOException3);
      }
    case 2: 
      try
      {
        ObjectInput localObjectInput2 = paramRemoteCall.getInputStream();
        localObject1 = (Term)localObjectInput2.readObject();
      }
      catch (IOException localIOException7)
      {
        throw new UnmarshalException("error unmarshalling arguments", localIOException7);
      }
      catch (ClassNotFoundException localClassNotFoundException1)
      {
        throw new UnmarshalException("error unmarshalling arguments", localClassNotFoundException1);
      }
      finally
      {
        paramRemoteCall.releaseInputStream();
      }
      k = localRemoteSearchable.docFreq((Term)localObject1);
      try
      {
        ObjectOutput localObjectOutput3 = paramRemoteCall.getResultStream(true);
        localObjectOutput3.writeInt(k);
      }
      catch (IOException localIOException4)
      {
        throw new MarshalException("error marshalling return", localIOException4);
      }
    case 3: 
      try
      {
        ObjectInput localObjectInput4 = paramRemoteCall.getInputStream();
        localObject1 = (Query)localObjectInput4.readObject();
        k = localObjectInput4.readInt();
      }
      catch (IOException localIOException11)
      {
        throw new UnmarshalException("error unmarshalling arguments", localIOException11);
      }
      catch (ClassNotFoundException localClassNotFoundException3)
      {
        throw new UnmarshalException("error unmarshalling arguments", localClassNotFoundException3);
      }
      finally
      {
        paramRemoteCall.releaseInputStream();
      }
      Explanation localExplanation = localRemoteSearchable.explain((Query)localObject1, k);
      try
      {
        ObjectOutput localObjectOutput5 = paramRemoteCall.getResultStream(true);
        localObjectOutput5.writeObject(localExplanation);
      }
      catch (IOException localIOException8)
      {
        throw new MarshalException("error marshalling return", localIOException8);
      }
    case 4: 
      paramRemoteCall.releaseInputStream();
      int j = localRemoteSearchable.maxDoc();
      try
      {
        ObjectOutput localObjectOutput1 = paramRemoteCall.getResultStream(true);
        localObjectOutput1.writeInt(j);
      }
      catch (IOException localIOException2)
      {
        throw new MarshalException("error marshalling return", localIOException2);
      }
    case 5: 
      try
      {
        ObjectInput localObjectInput3 = paramRemoteCall.getInputStream();
        localQuery = (Query)localObjectInput3.readObject();
      }
      catch (IOException localIOException9)
      {
        throw new UnmarshalException("error unmarshalling arguments", localIOException9);
      }
      catch (ClassNotFoundException localClassNotFoundException2)
      {
        throw new UnmarshalException("error unmarshalling arguments", localClassNotFoundException2);
      }
      finally
      {
        paramRemoteCall.releaseInputStream();
      }
      localObject5 = localRemoteSearchable.rewrite(localQuery);
      try
      {
        ObjectOutput localObjectOutput4 = paramRemoteCall.getResultStream(true);
        localObjectOutput4.writeObject(localObject5);
      }
      catch (IOException localIOException5)
      {
        throw new MarshalException("error marshalling return", localIOException5);
      }
    case 6: 
      try
      {
        ObjectInput localObjectInput5 = paramRemoteCall.getInputStream();
        localQuery = (Query)localObjectInput5.readObject();
        localObject5 = (Filter)localObjectInput5.readObject();
        m = localObjectInput5.readInt();
      }
      catch (IOException localIOException13)
      {
        throw new UnmarshalException("error unmarshalling arguments", localIOException13);
      }
      catch (ClassNotFoundException localClassNotFoundException4)
      {
        throw new UnmarshalException("error unmarshalling arguments", localClassNotFoundException4);
      }
      finally
      {
        paramRemoteCall.releaseInputStream();
      }
      localObject12 = localRemoteSearchable.search(localQuery, (Filter)localObject5, m);
      try
      {
        ObjectOutput localObjectOutput6 = paramRemoteCall.getResultStream(true);
        localObjectOutput6.writeObject(localObject12);
      }
      catch (IOException localIOException12)
      {
        throw new MarshalException("error marshalling return", localIOException12);
      }
    case 7: 
      try
      {
        ObjectInput localObjectInput7 = paramRemoteCall.getInputStream();
        localQuery = (Query)localObjectInput7.readObject();
        localObject5 = (Filter)localObjectInput7.readObject();
        m = localObjectInput7.readInt();
        localObject12 = (Sort)localObjectInput7.readObject();
      }
      catch (IOException localIOException16)
      {
        throw new UnmarshalException("error unmarshalling arguments", localIOException16);
      }
      catch (ClassNotFoundException localClassNotFoundException6)
      {
        throw new UnmarshalException("error unmarshalling arguments", localClassNotFoundException6);
      }
      finally
      {
        paramRemoteCall.releaseInputStream();
      }
      TopFieldDocs localTopFieldDocs = localRemoteSearchable.search(localQuery, (Filter)localObject5, m, (Sort)localObject12);
      try
      {
        ObjectOutput localObjectOutput7 = paramRemoteCall.getResultStream(true);
        localObjectOutput7.writeObject(localTopFieldDocs);
      }
      catch (IOException localIOException14)
      {
        throw new MarshalException("error marshalling return", localIOException14);
      }
    case 8: 
      HitCollector localHitCollector;
      try
      {
        ObjectInput localObjectInput6 = paramRemoteCall.getInputStream();
        localQuery = (Query)localObjectInput6.readObject();
        localObject5 = (Filter)localObjectInput6.readObject();
        localHitCollector = (HitCollector)localObjectInput6.readObject();
      }
      catch (IOException localIOException15)
      {
        throw new UnmarshalException("error unmarshalling arguments", localIOException15);
      }
      catch (ClassNotFoundException localClassNotFoundException5)
      {
        throw new UnmarshalException("error unmarshalling arguments", localClassNotFoundException5);
      }
      finally
      {
        paramRemoteCall.releaseInputStream();
      }
      localRemoteSearchable.search(localQuery, (Filter)localObject5, localHitCollector);
      try
      {
        paramRemoteCall.getResultStream(true);
      }
      catch (IOException localIOException10)
      {
        throw new MarshalException("error marshalling return", localIOException10);
      }
    default: 
      throw new UnmarshalException("invalid method number");
    }
  }
  
  public Operation[] getOperations()
  {
    return (Operation[])operations.clone();
  }
}


/* Location:              /home/menick/Scrivania/ProcessMining/CNMining/CNMining.jar!/org/apache/lucene/search/RemoteSearchable_Skel.class
 * Java compiler version: 1 (45.3)
 * JD-Core Version:       0.7.1
 */