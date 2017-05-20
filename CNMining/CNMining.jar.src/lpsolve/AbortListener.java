package lpsolve;

public abstract interface AbortListener
{
  public abstract boolean abortfunc(LpSolve paramLpSolve, Object paramObject)
    throws LpSolveException;
}


/* Location:              /home/menick/Scrivania/ProcessMining/CNMining/CNMining.jar!/lpsolve/AbortListener.class
 * Java compiler version: 2 (46.0)
 * JD-Core Version:       0.7.1
 */