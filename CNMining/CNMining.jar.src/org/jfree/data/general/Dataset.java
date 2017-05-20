package org.jfree.data.general;

public abstract interface Dataset
{
  public abstract void addChangeListener(DatasetChangeListener paramDatasetChangeListener);
  
  public abstract void removeChangeListener(DatasetChangeListener paramDatasetChangeListener);
  
  public abstract DatasetGroup getGroup();
  
  public abstract void setGroup(DatasetGroup paramDatasetGroup);
}


/* Location:              /home/menick/Scrivania/ProcessMining/CNMining/CNMining.jar!/org/jfree/data/general/Dataset.class
 * Java compiler version: 3 (47.0)
 * JD-Core Version:       0.7.1
 */