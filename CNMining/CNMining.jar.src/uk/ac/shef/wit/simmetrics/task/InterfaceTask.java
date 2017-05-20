package uk.ac.shef.wit.simmetrics.task;

public abstract interface InterfaceTask
{
  public abstract TaskResult RunTask();
  
  public abstract void ParseTask(String paramString);
  
  public abstract String toString();
}


/* Location:              /home/menick/Scrivania/ProcessMining/CNMining/CNMining.jar!/uk/ac/shef/wit/simmetrics/task/InterfaceTask.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       0.7.1
 */