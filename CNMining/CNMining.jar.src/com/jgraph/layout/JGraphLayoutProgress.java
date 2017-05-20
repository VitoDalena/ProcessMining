package com.jgraph.layout;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;

public class JGraphLayoutProgress
{
  public static final String MAXIMUM_PROPERTY = "maximum";
  public static final String PROGRESS_PROPERTY = "progress";
  public static final String ISSTOPPED_PROPERTY = "isStopped";
  protected PropertyChangeSupport changeSupport = new PropertyChangeSupport(this);
  protected int maximum = 0;
  protected int progress = 0;
  protected boolean isStopped = false;
  
  public JGraphLayoutProgress()
  {
    this(0);
  }
  
  public JGraphLayoutProgress(int paramInt)
  {
    reset(paramInt);
  }
  
  public void reset(int paramInt)
  {
    setStopped(false);
    this.maximum = 0;
    setMaximum(paramInt);
    this.progress = 0;
    setProgress(0);
  }
  
  public PropertyChangeSupport getChangeSupport()
  {
    return this.changeSupport;
  }
  
  public void setChangeSupport(PropertyChangeSupport paramPropertyChangeSupport)
  {
    this.changeSupport = paramPropertyChangeSupport;
  }
  
  public boolean isStopped()
  {
    return this.isStopped;
  }
  
  public void setStopped(boolean paramBoolean)
  {
    boolean bool = this.isStopped;
    this.isStopped = paramBoolean;
    this.changeSupport.firePropertyChange("isStopped", bool, paramBoolean);
  }
  
  public int getMaximum()
  {
    return this.maximum;
  }
  
  public void setMaximum(int paramInt)
  {
    int i = this.maximum;
    this.maximum = paramInt;
    this.changeSupport.firePropertyChange("maximum", i, paramInt);
  }
  
  public int getProgress()
  {
    return this.progress;
  }
  
  public void setProgress(int paramInt)
  {
    int i = this.progress;
    this.progress = paramInt;
    this.changeSupport.firePropertyChange("progress", i, paramInt);
  }
  
  public void addPropertyChangeListener(PropertyChangeListener paramPropertyChangeListener)
  {
    this.changeSupport.addPropertyChangeListener(paramPropertyChangeListener);
  }
  
  public void removePropertyChangeListener(PropertyChangeListener paramPropertyChangeListener)
  {
    this.changeSupport.removePropertyChangeListener(paramPropertyChangeListener);
  }
}


/* Location:              /home/menick/Scrivania/ProcessMining/CNMining/CNMining.jar!/com/jgraph/layout/JGraphLayoutProgress.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */