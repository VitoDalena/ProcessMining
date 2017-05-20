package com.jgraph.components.labels;

import java.awt.Component;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.geom.Rectangle2D;
import java.util.EventObject;
import javax.swing.AbstractAction;
import javax.swing.AbstractCellEditor;
import javax.swing.ActionMap;
import javax.swing.InputMap;
import javax.swing.JTextPane;
import javax.swing.KeyStroke;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.text.BadLocationException;
import javax.swing.text.Document;
import javax.swing.text.SimpleAttributeSet;
import javax.swing.text.StyleConstants;
import javax.swing.text.StyledDocument;
import org.jgraph.JGraph;
import org.jgraph.graph.AttributeMap;
import org.jgraph.graph.CellView;
import org.jgraph.graph.DefaultGraphCellEditor;
import org.jgraph.graph.DefaultGraphCellEditor.EditorContainer;
import org.jgraph.graph.GraphCellEditor;
import org.jgraph.graph.GraphConstants;
import org.jgraph.graph.GraphLayoutCache;
import org.jgraph.graph.GraphModel;

public class RichTextEditor
  extends DefaultGraphCellEditor
{
  public Component getGraphCellEditorComponent(JGraph paramJGraph, Object paramObject, boolean paramBoolean)
  {
    Component localComponent = super.getGraphCellEditorComponent(paramJGraph, paramObject, paramBoolean);
    CellView localCellView = paramJGraph.getGraphLayoutCache().getMapping(paramObject, false);
    Rectangle2D localRectangle2D = localCellView.getBounds();
    this.editingComponent.setBounds((int)localRectangle2D.getX(), (int)localRectangle2D.getY(), (int)localRectangle2D.getWidth(), (int)localRectangle2D.getHeight());
    Font localFont = GraphConstants.getFont(localCellView.getAllAttributes());
    this.editingComponent.setFont(localFont != null ? localFont : paramJGraph.getFont());
    return localComponent;
  }
  
  protected GraphCellEditor createGraphCellEditor()
  {
    return new RealCellEditor();
  }
  
  protected Container createContainer()
  {
    return new ModifiedEditorContainer();
  }
  
  class ModifiedEditorContainer
    extends DefaultGraphCellEditor.EditorContainer
  {
    ModifiedEditorContainer()
    {
      super();
    }
    
    public void doLayout()
    {
      super.doLayout();
      Dimension localDimension1 = getSize();
      Dimension localDimension2 = RichTextEditor.this.editingComponent.getSize();
      RichTextEditor.this.editingComponent.setSize(localDimension2.width - 2, localDimension2.height);
      setSize(localDimension1.width, getPreferredSize().height);
    }
  }
  
  class RealCellEditor
    extends AbstractCellEditor
    implements GraphCellEditor
  {
    JTextPane editorComponent = new JTextPane();
    
    public RealCellEditor()
    {
      this.editorComponent.setBorder(UIManager.getBorder("Tree.editorBorder"));
      this.editorComponent.getInputMap(0).put(KeyStroke.getKeyStroke(10, 0), "enter");
      this.editorComponent.getInputMap(0).put(KeyStroke.getKeyStroke(10, 64), "metaEnter");
      this.editorComponent.getInputMap(0).put(KeyStroke.getKeyStroke(10, 128), "metaEnter");
      this.editorComponent.getActionMap().put("enter", new AbstractAction()
      {
        public void actionPerformed(ActionEvent paramAnonymousActionEvent)
        {
          RichTextEditor.RealCellEditor.this.stopCellEditing();
        }
      });
      this.editorComponent.getActionMap().put("metaEnter", new AbstractAction()
      {
        public void actionPerformed(ActionEvent paramAnonymousActionEvent)
        {
          Document localDocument = RichTextEditor.RealCellEditor.this.editorComponent.getDocument();
          try
          {
            localDocument.insertString(RichTextEditor.RealCellEditor.this.editorComponent.getCaretPosition(), "\n", null);
          }
          catch (BadLocationException localBadLocationException)
          {
            localBadLocationException.printStackTrace();
          }
        }
      });
    }
    
    public Component getGraphCellEditorComponent(JGraph paramJGraph, Object paramObject, boolean paramBoolean)
    {
      Rectangle2D localRectangle2D = paramJGraph.getCellBounds(paramObject);
      if (localRectangle2D != null)
      {
        localObject = new Dimension((int)localRectangle2D.getWidth(), (int)localRectangle2D.getHeight());
        this.editorComponent.setMaximumSize((Dimension)localObject);
      }
      Object localObject = paramObject;
      paramObject = paramJGraph.getModel().getValue(paramObject);
      if (((paramObject instanceof RichTextBusinessObject)) && (((RichTextBusinessObject)paramObject).isRichText())) {
        try
        {
          StyledDocument localStyledDocument = (StyledDocument)this.editorComponent.getDocument();
          ((RichTextValue)((RichTextBusinessObject)paramObject).getValue()).insertInto(localStyledDocument);
          if (localStyledDocument.getLength() > 0) {
            localStyledDocument.remove(localStyledDocument.getLength() - 1, 1);
          }
          CellView localCellView = paramJGraph.getGraphLayoutCache().getMapping(localObject, false);
          if (localCellView != null)
          {
            AttributeMap localAttributeMap = localCellView.getAllAttributes();
            int i = GraphConstants.getHorizontalAlignment(localAttributeMap);
            SimpleAttributeSet localSimpleAttributeSet = new SimpleAttributeSet();
            i = i == 4 ? 2 : i == 0 ? 1 : 0;
            StyleConstants.setAlignment(localSimpleAttributeSet, i);
            localStyledDocument.setParagraphAttributes(0, localStyledDocument.getLength(), localSimpleAttributeSet, true);
          }
        }
        catch (Exception localException)
        {
          localException.printStackTrace();
        }
      } else {
        this.editorComponent.setText(paramObject.toString());
      }
      this.editorComponent.selectAll();
      this.editorComponent.getDocument().addDocumentListener(new DocumentListener()
      {
        public void updateSize()
        {
          SwingUtilities.invokeLater(new Runnable()
          {
            public void run()
            {
              Container localContainer = RichTextEditor.RealCellEditor.this.editorComponent.getParent();
              if (localContainer != null)
              {
                localContainer.doLayout();
                localContainer.setSize(RichTextEditor.this.editingContainer.getPreferredSize());
                localContainer.invalidate();
              }
            }
          });
        }
        
        public void insertUpdate(DocumentEvent paramAnonymousDocumentEvent)
        {
          updateSize();
        }
        
        public void removeUpdate(DocumentEvent paramAnonymousDocumentEvent)
        {
          updateSize();
        }
        
        public void changedUpdate(DocumentEvent paramAnonymousDocumentEvent)
        {
          updateSize();
        }
      });
      return this.editorComponent;
    }
    
    public Object getCellEditorValue()
    {
      return new RichTextValue(this.editorComponent.getDocument());
    }
    
    public boolean shouldSelectCell(EventObject paramEventObject)
    {
      this.editorComponent.requestFocus();
      return super.shouldSelectCell(paramEventObject);
    }
  }
}


/* Location:              /home/menick/Scrivania/ProcessMining/CNMining/CNMining.jar!/com/jgraph/components/labels/RichTextEditor.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */