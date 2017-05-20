package org.jgraph.graph;

import java.awt.Component;
import javax.swing.CellEditor;
import org.jgraph.JGraph;

public abstract interface GraphCellEditor
  extends CellEditor
{
  public abstract Component getGraphCellEditorComponent(JGraph paramJGraph, Object paramObject, boolean paramBoolean);
}


/* Location:              /home/menick/Scrivania/ProcessMining/CNMining/CNMining.jar!/org/jgraph/graph/GraphCellEditor.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */