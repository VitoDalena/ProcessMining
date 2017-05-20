package com.jgraph.layout;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

public class JGraphCompoundLayout
  implements JGraphLayout
{
  protected List layouts = new ArrayList();
  
  public JGraphCompoundLayout() {}
  
  public JGraphCompoundLayout(JGraphLayout[] paramArrayOfJGraphLayout)
  {
    this.layouts.addAll(Arrays.asList(paramArrayOfJGraphLayout));
  }
  
  public void run(JGraphFacade paramJGraphFacade)
  {
    Iterator localIterator = this.layouts.iterator();
    while (localIterator.hasNext())
    {
      Object localObject = localIterator.next();
      if ((localObject instanceof JGraphLayout)) {
        ((JGraphLayout)localObject).run(paramJGraphFacade);
      }
    }
  }
  
  public void add(JGraphLayout paramJGraphLayout)
  {
    this.layouts.add(paramJGraphLayout);
  }
  
  public void remove(JGraphLayout paramJGraphLayout)
  {
    this.layouts.remove(paramJGraphLayout);
  }
  
  public List getLayouts()
  {
    return this.layouts;
  }
}


/* Location:              /home/menick/Scrivania/ProcessMining/CNMining/CNMining.jar!/com/jgraph/layout/JGraphCompoundLayout.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */