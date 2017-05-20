package org.jgraph.plaf.basic;

import java.awt.Insets;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.Toolkit;
import java.awt.dnd.DropTargetContext;
import java.awt.dnd.DropTargetDragEvent;
import java.awt.dnd.DropTargetDropEvent;
import java.awt.dnd.DropTargetEvent;
import java.awt.dnd.DropTargetListener;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JComponent;
import javax.swing.Scrollable;
import javax.swing.Timer;
import javax.swing.TransferHandler;
import javax.swing.plaf.UIResource;
import org.jgraph.JGraph;

public class BasicGraphDropTargetListener
  implements DropTargetListener, UIResource, ActionListener
{
  private Timer timer;
  private Point lastPosition;
  private Rectangle outer = new Rectangle();
  private Rectangle inner = new Rectangle();
  private int hysteresis = 10;
  private boolean canImport;
  private JComponent component;
  
  protected void saveComponentState(JComponent paramJComponent) {}
  
  protected void restoreComponentState(JComponent paramJComponent) {}
  
  protected void restoreComponentStateForDrop(JComponent paramJComponent) {}
  
  protected void updateInsertionLocation(JComponent paramJComponent, Point paramPoint) {}
  
  void updateAutoscrollRegion(JComponent paramJComponent)
  {
    Rectangle localRectangle = paramJComponent.getVisibleRect();
    this.outer.setBounds(localRectangle.x, localRectangle.y, localRectangle.width, localRectangle.height);
    Insets localInsets = new Insets(0, 0, 0, 0);
    if ((paramJComponent instanceof Scrollable))
    {
      Scrollable localScrollable = (Scrollable)paramJComponent;
      localInsets.left = localScrollable.getScrollableUnitIncrement(localRectangle, 0, 1);
      localInsets.top = localScrollable.getScrollableUnitIncrement(localRectangle, 1, 1);
      localInsets.right = localScrollable.getScrollableUnitIncrement(localRectangle, 0, -1);
      localInsets.bottom = localScrollable.getScrollableUnitIncrement(localRectangle, 1, -1);
    }
    this.inner.setBounds(localRectangle.x + localInsets.left, localRectangle.y + localInsets.top, localRectangle.width - (localInsets.left + localInsets.right), localRectangle.height - (localInsets.top + localInsets.bottom));
  }
  
  void autoscroll(JComponent paramJComponent, Point paramPoint)
  {
    if ((paramJComponent instanceof JGraph)) {
      BasicGraphUI.autoscroll((JGraph)paramJComponent, paramPoint);
    }
  }
  
  private void initPropertiesIfNecessary()
  {
    if (this.timer == null)
    {
      Toolkit localToolkit = Toolkit.getDefaultToolkit();
      Integer localInteger1 = new Integer(100);
      Integer localInteger2 = new Integer(100);
      try
      {
        localInteger1 = (Integer)localToolkit.getDesktopProperty("DnD.Autoscroll.initialDelay");
      }
      catch (Exception localException1) {}
      try
      {
        localInteger2 = (Integer)localToolkit.getDesktopProperty("DnD.Autoscroll.interval");
      }
      catch (Exception localException2) {}
      this.timer = new Timer(localInteger2.intValue(), this);
      this.timer.setCoalesce(true);
      this.timer.setInitialDelay(localInteger1.intValue());
      try
      {
        this.hysteresis = ((Integer)localToolkit.getDesktopProperty("DnD.Autoscroll.cursorHysteresis")).intValue();
      }
      catch (Exception localException3) {}
    }
  }
  
  static JComponent getComponent(DropTargetEvent paramDropTargetEvent)
  {
    DropTargetContext localDropTargetContext = paramDropTargetEvent.getDropTargetContext();
    return (JComponent)localDropTargetContext.getComponent();
  }
  
  public synchronized void actionPerformed(ActionEvent paramActionEvent)
  {
    updateAutoscrollRegion(this.component);
    if ((this.outer.contains(this.lastPosition)) && (!this.inner.contains(this.lastPosition))) {
      autoscroll(this.component, this.lastPosition);
    }
  }
  
  public void dragEnter(DropTargetDragEvent paramDropTargetDragEvent)
  {
    this.component = getComponent(paramDropTargetDragEvent);
    TransferHandler localTransferHandler = this.component.getTransferHandler();
    this.canImport = localTransferHandler.canImport(this.component, paramDropTargetDragEvent.getCurrentDataFlavors());
    if (this.canImport)
    {
      saveComponentState(this.component);
      this.lastPosition = paramDropTargetDragEvent.getLocation();
      updateAutoscrollRegion(this.component);
      initPropertiesIfNecessary();
    }
  }
  
  public void dragOver(DropTargetDragEvent paramDropTargetDragEvent)
  {
    if (this.canImport)
    {
      Point localPoint = paramDropTargetDragEvent.getLocation();
      updateInsertionLocation(this.component, localPoint);
      synchronized (this)
      {
        if ((Math.abs(localPoint.x - this.lastPosition.x) > this.hysteresis) || (Math.abs(localPoint.y - this.lastPosition.y) > this.hysteresis))
        {
          if (this.timer.isRunning()) {
            this.timer.stop();
          }
        }
        else if (!this.timer.isRunning()) {
          this.timer.start();
        }
        this.lastPosition = localPoint;
      }
    }
  }
  
  public void dragExit(DropTargetEvent paramDropTargetEvent)
  {
    if (this.canImport) {
      restoreComponentState(this.component);
    }
    cleanup();
  }
  
  public void drop(DropTargetDropEvent paramDropTargetDropEvent)
  {
    if (this.canImport) {
      restoreComponentStateForDrop(this.component);
    }
    cleanup();
  }
  
  public void dropActionChanged(DropTargetDragEvent paramDropTargetDragEvent) {}
  
  private void cleanup()
  {
    if (this.timer != null) {
      this.timer.stop();
    }
    this.component = null;
    this.lastPosition = null;
  }
}


/* Location:              /home/menick/Scrivania/ProcessMining/CNMining/CNMining.jar!/org/jgraph/plaf/basic/BasicGraphDropTargetListener.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */