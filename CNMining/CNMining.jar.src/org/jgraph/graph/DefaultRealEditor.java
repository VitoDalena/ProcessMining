package org.jgraph.graph;

import java.awt.Component;
import javax.swing.DefaultCellEditor;
import javax.swing.DefaultCellEditor.EditorDelegate;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JTextField;
import org.jgraph.JGraph;

public class DefaultRealEditor
  extends DefaultCellEditor
  implements GraphCellEditor
{
  public DefaultRealEditor(JTextField paramJTextField)
  {
    super(paramJTextField);
    setClickCountToStart(1);
  }
  
  public DefaultRealEditor(JCheckBox paramJCheckBox)
  {
    super(paramJCheckBox);
  }
  
  public DefaultRealEditor(JComboBox paramJComboBox)
  {
    super(paramJComboBox);
  }
  
  public Component getGraphCellEditorComponent(JGraph paramJGraph, Object paramObject, boolean paramBoolean)
  {
    String str = paramJGraph.convertValueToString(paramObject);
    this.delegate.setValue(str);
    if ((this.editorComponent instanceof JTextField)) {
      ((JTextField)this.editorComponent).selectAll();
    }
    return this.editorComponent;
  }
}


/* Location:              /home/menick/Scrivania/ProcessMining/CNMining/CNMining.jar!/org/jgraph/graph/DefaultRealEditor.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */