package com.jgraph.components.labels;

import java.awt.Component;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.geom.Rectangle2D;
import java.util.EventObject;
import javax.swing.AbstractAction;
import javax.swing.AbstractCellEditor;
import javax.swing.ActionMap;
import javax.swing.InputMap;
import javax.swing.JComponent;
import javax.swing.JTree;
import javax.swing.KeyStroke;
import org.jgraph.JGraph;
import org.jgraph.graph.DefaultGraphCellEditor;
import org.jgraph.graph.GraphCellEditor;
import org.jgraph.graph.GraphModel;

public class RedirectingEditor
  extends DefaultGraphCellEditor
{
  protected GraphCellEditor createGraphCellEditor()
  {
    return new RealCellEditor();
  }
  
  class RealCellEditor
    extends AbstractCellEditor
    implements GraphCellEditor
  {
    Component componentValue = null;
    
    RealCellEditor() {}
    
    public Component getGraphCellEditorComponent(JGraph paramJGraph, Object paramObject, boolean paramBoolean)
    {
      Rectangle2D localRectangle2D = paramJGraph.getCellBounds(paramObject);
      paramObject = paramJGraph.getModel().getValue(paramObject);
      if (((paramObject instanceof RichTextBusinessObject)) && (((RichTextBusinessObject)paramObject).isComponent()))
      {
        paramObject = ((RichTextBusinessObject)paramObject).clone();
        Dimension localDimension = new Dimension((int)localRectangle2D.getWidth() - 6, (int)localRectangle2D.getHeight());
        this.componentValue = ((Component)((RichTextBusinessObject)paramObject).getValue());
        this.componentValue.invalidate();
        Object localObject;
        if ((this.componentValue instanceof JComponent))
        {
          localObject = (JComponent)this.componentValue;
          ((JComponent)localObject).setMaximumSize(localDimension);
          ((JComponent)localObject).setPreferredSize(localDimension);
          ((JComponent)localObject).setMinimumSize(localDimension);
        }
        if ((this.componentValue instanceof JTree))
        {
          localObject = (JTree)this.componentValue;
          ((JTree)localObject).setSelectionInterval(0, 0);
        }
        if ((this.componentValue instanceof JComponent))
        {
          localObject = (JComponent)this.componentValue;
          ((JComponent)localObject).getInputMap(0).put(KeyStroke.getKeyStroke(10, 0), "enter");
          ((JComponent)localObject).getInputMap(1).put(KeyStroke.getKeyStroke(10, 0), "enter");
          ((JComponent)localObject).getActionMap().put("enter", new AbstractAction()
          {
            public void actionPerformed(ActionEvent paramAnonymousActionEvent)
            {
              RedirectingEditor.RealCellEditor.this.stopCellEditing();
            }
          });
        }
        return this.componentValue;
      }
      return null;
    }
    
    public Object getCellEditorValue()
    {
      return this.componentValue;
    }
    
    public boolean stopCellEditing()
    {
      if ((this.componentValue instanceof JTree)) {
        ((JTree)this.componentValue).clearSelection();
      }
      return super.stopCellEditing();
    }
    
    public void cancelCellEditing()
    {
      if ((this.componentValue instanceof JTree)) {
        ((JTree)this.componentValue).clearSelection();
      }
      super.cancelCellEditing();
    }
    
    public boolean shouldSelectCell(EventObject paramEventObject)
    {
      this.componentValue.requestFocus();
      return super.shouldSelectCell(paramEventObject);
    }
  }
}


/* Location:              /home/menick/Scrivania/ProcessMining/CNMining/CNMining.jar!/com/jgraph/components/labels/RedirectingEditor.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */