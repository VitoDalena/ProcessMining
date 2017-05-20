package org.jgraph.graph;

public abstract interface CellMapper
{
  public abstract CellView getMapping(Object paramObject, boolean paramBoolean);
  
  public abstract void putMapping(Object paramObject, CellView paramCellView);
}


/* Location:              /home/menick/Scrivania/ProcessMining/CNMining/CNMining.jar!/org/jgraph/graph/CellMapper.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */