package org.jfree.chart.labels;

import org.jfree.data.xy.XYZDataset;

public abstract interface XYZToolTipGenerator
  extends XYToolTipGenerator
{
  public abstract String generateToolTip(XYZDataset paramXYZDataset, int paramInt1, int paramInt2);
}


/* Location:              /home/menick/Scrivania/ProcessMining/CNMining/CNMining.jar!/org/jfree/chart/labels/XYZToolTipGenerator.class
 * Java compiler version: 3 (47.0)
 * JD-Core Version:       0.7.1
 */