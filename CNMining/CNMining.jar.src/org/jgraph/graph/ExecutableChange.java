package org.jgraph.graph;

import javax.swing.undo.AbstractUndoableEdit;

public abstract class ExecutableChange
  extends AbstractUndoableEdit
{
  public void undo()
  {
    execute();
  }
  
  public void redo()
  {
    execute();
  }
  
  public abstract void execute();
}


/* Location:              /home/menick/Scrivania/ProcessMining/CNMining/CNMining.jar!/org/jgraph/graph/ExecutableChange.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */