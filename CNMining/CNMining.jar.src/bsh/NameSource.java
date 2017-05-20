package bsh;

public abstract interface NameSource
{
  public abstract String[] getAllNames();
  
  public abstract void addNameSourceListener(Listener paramListener);
  
  public static abstract interface Listener
  {
    public abstract void nameSourceChanged(NameSource paramNameSource);
  }
}


/* Location:              /home/menick/Scrivania/ProcessMining/CNMining/CNMining.jar!/bsh/NameSource.class
 * Java compiler version: 2 (46.0)
 * JD-Core Version:       0.7.1
 */