package org.jgraph.graph;

import java.awt.Color;
import java.awt.Component;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.EventObject;
import java.util.Vector;
import javax.swing.Icon;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;
import javax.swing.border.Border;
import javax.swing.event.CellEditorListener;
import javax.swing.plaf.FontUIResource;
import org.jgraph.JGraph;
import org.jgraph.event.GraphSelectionEvent;
import org.jgraph.event.GraphSelectionListener;

public class DefaultGraphCellEditor
  implements ActionListener, GraphCellEditor, GraphSelectionListener, Serializable
{
  protected GraphCellEditor realEditor;
  protected Container editingContainer;
  protected transient Component editingComponent;
  protected boolean canEdit;
  protected transient int offsetX;
  protected transient int offsetY;
  protected transient JGraph graph;
  protected transient Object lastCell;
  protected Color borderSelectionColor;
  protected transient Icon editingIcon;
  protected Font font;
  
  public DefaultGraphCellEditor()
  {
    this(null);
  }
  
  public DefaultGraphCellEditor(GraphCellEditor paramGraphCellEditor)
  {
    this.realEditor = paramGraphCellEditor;
    if (this.realEditor == null) {
      this.realEditor = createGraphCellEditor();
    }
    this.editingContainer = createContainer();
    setBorderSelectionColor(UIManager.getColor("Tree.editorBorderSelectionColor"));
  }
  
  public void setBorderSelectionColor(Color paramColor)
  {
    this.borderSelectionColor = paramColor;
  }
  
  public Color getBorderSelectionColor()
  {
    return this.borderSelectionColor;
  }
  
  public void setFont(Font paramFont)
  {
    this.font = paramFont;
  }
  
  public Font getFont()
  {
    return this.font;
  }
  
  public Component getGraphCellEditorComponent(JGraph paramJGraph, Object paramObject, boolean paramBoolean)
  {
    setGraph(paramJGraph);
    this.editingComponent = this.realEditor.getGraphCellEditorComponent(paramJGraph, paramObject, paramBoolean);
    determineOffset(paramJGraph, paramObject, paramBoolean);
    this.canEdit = ((this.lastCell != null) && (paramObject != null) && (this.lastCell.equals(paramObject)));
    CellView localCellView = paramJGraph.getGraphLayoutCache().getMapping(paramObject, false);
    if (localCellView != null) {
      setFont(GraphConstants.getFont(localCellView.getAllAttributes()));
    }
    this.editingContainer.setFont(this.font);
    return this.editingContainer;
  }
  
  public Object getCellEditorValue()
  {
    return this.realEditor.getCellEditorValue();
  }
  
  public boolean isCellEditable(EventObject paramEventObject)
  {
    boolean bool = false;
    if (!this.realEditor.isCellEditable(paramEventObject)) {
      return false;
    }
    if (canEditImmediately(paramEventObject)) {
      bool = true;
    }
    if (bool) {
      prepareForEditing();
    }
    return bool;
  }
  
  public boolean shouldSelectCell(EventObject paramEventObject)
  {
    return this.realEditor.shouldSelectCell(paramEventObject);
  }
  
  public boolean stopCellEditing()
  {
    if (this.realEditor.stopCellEditing())
    {
      if (this.editingComponent != null) {
        this.editingContainer.remove(this.editingComponent);
      }
      this.editingComponent = null;
      return true;
    }
    return false;
  }
  
  public void cancelCellEditing()
  {
    this.realEditor.cancelCellEditing();
    if (this.editingComponent != null) {
      this.editingContainer.remove(this.editingComponent);
    }
    this.editingComponent = null;
  }
  
  public void addCellEditorListener(CellEditorListener paramCellEditorListener)
  {
    this.realEditor.addCellEditorListener(paramCellEditorListener);
  }
  
  public void removeCellEditorListener(CellEditorListener paramCellEditorListener)
  {
    this.realEditor.removeCellEditorListener(paramCellEditorListener);
  }
  
  public void valueChanged(GraphSelectionEvent paramGraphSelectionEvent)
  {
    if (this.graph != null) {
      if (this.graph.getSelectionCount() == 1) {
        this.lastCell = this.graph.getSelectionCell();
      } else {
        this.lastCell = null;
      }
    }
  }
  
  public void actionPerformed(ActionEvent paramActionEvent)
  {
    if (this.graph != null) {
      this.graph.startEditingAtCell(this.lastCell);
    }
  }
  
  protected void setGraph(JGraph paramJGraph)
  {
    if (this.graph != paramJGraph)
    {
      if (this.graph != null) {
        this.graph.removeGraphSelectionListener(this);
      }
      this.graph = paramJGraph;
      if (this.graph != null) {
        this.graph.addGraphSelectionListener(this);
      }
    }
  }
  
  protected boolean shouldStartEditingTimer(EventObject paramEventObject)
  {
    if (((paramEventObject instanceof MouseEvent)) && (SwingUtilities.isLeftMouseButton((MouseEvent)paramEventObject)))
    {
      MouseEvent localMouseEvent = (MouseEvent)paramEventObject;
      return (localMouseEvent.getClickCount() == 1) && (inHitRegion(localMouseEvent.getX(), localMouseEvent.getY()));
    }
    return false;
  }
  
  protected boolean canEditImmediately(EventObject paramEventObject)
  {
    if (((paramEventObject instanceof MouseEvent)) && (SwingUtilities.isLeftMouseButton((MouseEvent)paramEventObject)))
    {
      MouseEvent localMouseEvent = (MouseEvent)paramEventObject;
      return inHitRegion(localMouseEvent.getX(), localMouseEvent.getY());
    }
    return paramEventObject == null;
  }
  
  protected boolean inHitRegion(double paramDouble1, double paramDouble2)
  {
    return true;
  }
  
  protected void determineOffset(JGraph paramJGraph, Object paramObject, boolean paramBoolean)
  {
    this.editingIcon = null;
    this.offsetX = paramJGraph.getHandleSize();
    this.offsetY = paramJGraph.getHandleSize();
  }
  
  protected void prepareForEditing()
  {
    this.editingContainer.add(this.editingComponent);
  }
  
  protected Container createContainer()
  {
    return new EditorContainer();
  }
  
  protected GraphCellEditor createGraphCellEditor()
  {
    Border localBorder = UIManager.getBorder("Tree.editorBorder");
    DefaultRealEditor local1 = new DefaultRealEditor(new DefaultTextField(localBorder))
    {
      public boolean shouldSelectCell(EventObject paramAnonymousEventObject)
      {
        boolean bool = super.shouldSelectCell(paramAnonymousEventObject);
        getComponent().requestFocus();
        return bool;
      }
    };
    local1.setClickCountToStart(1);
    return local1;
  }
  
  private void writeObject(ObjectOutputStream paramObjectOutputStream)
    throws IOException
  {
    Vector localVector = new Vector();
    paramObjectOutputStream.defaultWriteObject();
    if ((this.realEditor instanceof Serializable))
    {
      localVector.addElement("realEditor");
      localVector.addElement(this.realEditor);
    }
    paramObjectOutputStream.writeObject(localVector);
  }
  
  private void readObject(ObjectInputStream paramObjectInputStream)
    throws IOException, ClassNotFoundException
  {
    paramObjectInputStream.defaultReadObject();
    Vector localVector = (Vector)paramObjectInputStream.readObject();
    int i = 0;
    int j = localVector.size();
    if ((i < j) && (localVector.elementAt(i).equals("realEditor")))
    {
      this.realEditor = ((GraphCellEditor)localVector.elementAt(++i));
      i++;
    }
  }
  
  public class EditorContainer
    extends Container
  {
    public EditorContainer()
    {
      setLayout(null);
    }
    
    public void paint(Graphics paramGraphics)
    {
      Dimension localDimension = getSize();
      if (DefaultGraphCellEditor.this.editingIcon != null)
      {
        int i = 0;
        int j = 0;
        DefaultGraphCellEditor.this.editingIcon.paintIcon(this, paramGraphics, j, i);
      }
      Color localColor = DefaultGraphCellEditor.this.getBorderSelectionColor();
      if (localColor != null)
      {
        paramGraphics.setColor(localColor);
        paramGraphics.drawRect(0, 0, localDimension.width - 1, localDimension.height - 1);
      }
      super.paint(paramGraphics);
    }
    
    public void doLayout()
    {
      if (DefaultGraphCellEditor.this.editingComponent != null)
      {
        Dimension localDimension = getSize();
        int i = (int)DefaultGraphCellEditor.this.editingComponent.getPreferredSize().getHeight();
        int j = 45;
        int k = (int)DefaultGraphCellEditor.this.editingComponent.getPreferredSize().getWidth() + 5;
        int m = (int)DefaultGraphCellEditor.this.editingComponent.getMaximumSize().getWidth();
        if ((DefaultGraphCellEditor.this.editingContainer.getParent() != null) && (m > DefaultGraphCellEditor.this.editingContainer.getParent().getWidth())) {
          k = localDimension.width - DefaultGraphCellEditor.this.offsetX;
        } else {
          k = Math.max(j, Math.min(k, m));
        }
        DefaultGraphCellEditor.this.editingComponent.setBounds(DefaultGraphCellEditor.this.offsetX, DefaultGraphCellEditor.this.offsetY, k, i);
      }
    }
    
    public Dimension getPreferredSize()
    {
      if (DefaultGraphCellEditor.this.editingComponent != null)
      {
        Dimension localDimension = DefaultGraphCellEditor.this.editingComponent.getPreferredSize();
        localDimension.width += DefaultGraphCellEditor.this.offsetX + 2;
        localDimension.height += DefaultGraphCellEditor.this.offsetY + 2;
        int i = 50;
        if (DefaultGraphCellEditor.this.editingIcon != null) {
          i = Math.max(DefaultGraphCellEditor.this.editingIcon.getIconWidth(), i);
        }
        localDimension.height = Math.max(localDimension.height, 24);
        localDimension.width = Math.max(localDimension.width + 5, i);
        return localDimension;
      }
      return new Dimension(0, 0);
    }
  }
  
  public class DefaultTextField
    extends JTextField
  {
    protected Border border;
    
    public DefaultTextField(Border paramBorder)
    {
      this.border = paramBorder;
    }
    
    public Border getBorder()
    {
      return this.border;
    }
    
    public Font getFont()
    {
      Font localFont = super.getFont();
      if ((localFont instanceof FontUIResource))
      {
        Container localContainer = getParent();
        if ((localContainer != null) && (localContainer.getFont() != null)) {
          localFont = localContainer.getFont();
        }
      }
      return localFont;
    }
  }
}


/* Location:              /home/menick/Scrivania/ProcessMining/CNMining/CNMining.jar!/org/jgraph/graph/DefaultGraphCellEditor.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */