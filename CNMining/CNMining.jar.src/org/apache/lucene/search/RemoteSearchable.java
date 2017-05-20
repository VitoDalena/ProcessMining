/*    */ package org.apache.lucene.search;
/*    */ 
/*    */ import java.io.IOException;
/*    */ import java.rmi.Naming;
/*    */ import java.rmi.RMISecurityManager;
/*    */ import java.rmi.RemoteException;
/*    */ import java.rmi.server.UnicastRemoteObject;
/*    */ import org.apache.lucene.document.Document;
/*    */ import org.apache.lucene.index.Term;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class RemoteSearchable
/*    */   extends UnicastRemoteObject
/*    */   implements Searchable
/*    */ {
/*    */   private Searchable local;
/*    */   
/*    */   public RemoteSearchable(Searchable local)
/*    */     throws RemoteException
/*    */   {
/* 39 */     this.local = local;
/*    */   }
/*    */   
/*    */   public void search(Query query, Filter filter, HitCollector results) throws IOException
/*    */   {
/* 44 */     this.local.search(query, filter, results);
/*    */   }
/*    */   
/*    */   public void close() throws IOException {
/* 48 */     this.local.close();
/*    */   }
/*    */   
/*    */   public int docFreq(Term term) throws IOException {
/* 52 */     return this.local.docFreq(term);
/*    */   }
/*    */   
/*    */   public int maxDoc() throws IOException {
/* 56 */     return this.local.maxDoc();
/*    */   }
/*    */   
/*    */   public TopDocs search(Query query, Filter filter, int n) throws IOException {
/* 60 */     return this.local.search(query, filter, n);
/*    */   }
/*    */   
/*    */   public TopFieldDocs search(Query query, Filter filter, int n, Sort sort) throws IOException
/*    */   {
/* 65 */     return this.local.search(query, filter, n, sort);
/*    */   }
/*    */   
/*    */   public Document doc(int i) throws IOException {
/* 69 */     return this.local.doc(i);
/*    */   }
/*    */   
/*    */   public Query rewrite(Query original) throws IOException {
/* 73 */     return this.local.rewrite(original);
/*    */   }
/*    */   
/*    */   public Explanation explain(Query query, int doc) throws IOException {
/* 77 */     return this.local.explain(query, doc);
/*    */   }
/*    */   
/*    */ 
/*    */   public static void main(String[] args)
/*    */     throws Exception
/*    */   {
/* 84 */     if (System.getSecurityManager() == null) {
/* 85 */       System.setSecurityManager(new RMISecurityManager());
/*    */     }
/*    */     
/* 88 */     Searchable local = new IndexSearcher(args[0]);
/* 89 */     RemoteSearchable impl = new RemoteSearchable(local);
/*    */     
/*    */ 
/* 92 */     Naming.rebind("//localhost/Searchable", impl);
/*    */   }
/*    */ }


/* Location:              /home/menick/Scrivania/ProcessMining/CNMining/CNMining.jar!/org/apache/lucene/search/RemoteSearchable.class
 * Java compiler version: 2 (46.0)
 * JD-Core Version:       0.7.1
 */