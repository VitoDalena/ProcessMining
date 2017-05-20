package org.jfree.chart.labels;

import java.text.AttributedString;
import org.jfree.data.general.PieDataset;

public abstract interface PieSectionLabelGenerator
{
  public abstract String generateSectionLabel(PieDataset paramPieDataset, Comparable paramComparable);
  
  public abstract AttributedString generateAttributedSectionLabel(PieDataset paramPieDataset, Comparable paramComparable);
}


/* Location:              /home/menick/Scrivania/ProcessMining/CNMining/CNMining.jar!/org/jfree/chart/labels/PieSectionLabelGenerator.class
 * Java compiler version: 3 (47.0)
 * JD-Core Version:       0.7.1
 */