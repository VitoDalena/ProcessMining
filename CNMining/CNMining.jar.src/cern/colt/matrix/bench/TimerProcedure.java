package cern.colt.matrix.bench;

import cern.colt.Timer;

abstract interface TimerProcedure
{
  public abstract void apply(Timer paramTimer);
  
  public abstract void init();
}


/* Location:              /home/menick/Scrivania/ProcessMining/CNMining/CNMining.jar!/cern/colt/matrix/bench/TimerProcedure.class
 * Java compiler version: 1 (45.3)
 * JD-Core Version:       0.7.1
 */