package com.jgraph.components.labels;

import javax.swing.tree.DefaultMutableTreeNode;
import org.jgraph.graph.CellViewRenderer;
import org.jgraph.graph.GraphCellEditor;
import org.jgraph.graph.VertexView;

public class MultiLineVertexView
  extends VertexView
{
  public static RichTextEditor editor = new RichTextEditor();
  public static RedirectingEditor redirector = new RedirectingEditor();
  public static MultiLineVertexRenderer renderer = new MultiLineVertexRenderer();
  
  public MultiLineVertexView() {}
  
  public MultiLineVertexView(Object paramObject)
  {
    super(paramObject);
  }
  
  public GraphCellEditor getEditor()
  {
    Object localObject = ((DefaultMutableTreeNode)getCell()).getUserObject();
    if ((localObject instanceof RichTextBusinessObject))
    {
      RichTextBusinessObject localRichTextBusinessObject = (RichTextBusinessObject)localObject;
      if (localRichTextBusinessObject.isRichText()) {
        return editor;
      }
      if (localRichTextBusinessObject.isComponent()) {
        return redirector;
      }
    }
    return super.getEditor();
  }
  
  public CellViewRenderer getRenderer()
  {
    return renderer;
  }
}


/* Location:              /home/menick/Scrivania/ProcessMining/CNMining/CNMining.jar!/com/jgraph/components/labels/MultiLineVertexView.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */