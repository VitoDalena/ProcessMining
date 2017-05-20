package org.jfree.chart.urls;

import org.jfree.data.xy.XYZDataset;

public abstract interface XYZURLGenerator
  extends XYURLGenerator
{
  public abstract String generateURL(XYZDataset paramXYZDataset, int paramInt1, int paramInt2);
}


/* Location:              /home/menick/Scrivania/ProcessMining/CNMining/CNMining.jar!/org/jfree/chart/urls/XYZURLGenerator.class
 * Java compiler version: 3 (47.0)
 * JD-Core Version:       0.7.1
 */