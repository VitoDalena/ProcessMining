package edu.uci.ics.jung.visualization;

import edu.uci.ics.jung.algorithms.layout.GraphElementAccessor;
import edu.uci.ics.jung.algorithms.layout.Layout;
import edu.uci.ics.jung.visualization.picking.PickedState;
import edu.uci.ics.jung.visualization.renderers.Renderer;
import java.awt.Graphics;
import java.awt.RenderingHints.Key;
import java.awt.geom.Point2D;
import java.util.Map;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

public abstract interface VisualizationServer<V, E>
{
  public abstract void setDoubleBuffered(boolean paramBoolean);
  
  public abstract boolean isDoubleBuffered();
  
  public abstract VisualizationModel<V, E> getModel();
  
  public abstract void setModel(VisualizationModel<V, E> paramVisualizationModel);
  
  public abstract void stateChanged(ChangeEvent paramChangeEvent);
  
  public abstract void setRenderer(Renderer<V, E> paramRenderer);
  
  public abstract Renderer<V, E> getRenderer();
  
  public abstract void setGraphLayout(Layout<V, E> paramLayout);
  
  public abstract Layout<V, E> getGraphLayout();
  
  public abstract void setVisible(boolean paramBoolean);
  
  public abstract Map<RenderingHints.Key, Object> getRenderingHints();
  
  public abstract void setRenderingHints(Map<RenderingHints.Key, Object> paramMap);
  
  public abstract void addPreRenderPaintable(Paintable paramPaintable);
  
  public abstract void removePreRenderPaintable(Paintable paramPaintable);
  
  public abstract void addPostRenderPaintable(Paintable paramPaintable);
  
  public abstract void removePostRenderPaintable(Paintable paramPaintable);
  
  public abstract void addChangeListener(ChangeListener paramChangeListener);
  
  public abstract void removeChangeListener(ChangeListener paramChangeListener);
  
  public abstract ChangeListener[] getChangeListeners();
  
  public abstract void fireStateChanged();
  
  public abstract PickedState<V> getPickedVertexState();
  
  public abstract PickedState<E> getPickedEdgeState();
  
  public abstract void setPickedVertexState(PickedState<V> paramPickedState);
  
  public abstract void setPickedEdgeState(PickedState<E> paramPickedState);
  
  public abstract GraphElementAccessor<V, E> getPickSupport();
  
  public abstract void setPickSupport(GraphElementAccessor<V, E> paramGraphElementAccessor);
  
  public abstract Point2D getCenter();
  
  public abstract RenderContext<V, E> getRenderContext();
  
  public abstract void setRenderContext(RenderContext<V, E> paramRenderContext);
  
  public abstract void repaint();
  
  public static abstract interface Paintable
  {
    public abstract void paint(Graphics paramGraphics);
    
    public abstract boolean useTransform();
  }
}


/* Location:              /home/menick/Scrivania/ProcessMining/CNMining/CNMining.jar!/edu/uci/ics/jung/visualization/VisualizationServer.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       0.7.1
 */