package org.processmining.plugins.cnmining;

import org.processmining.framework.connections.impl.AbstractConnection;
import org.processmining.models.flexiblemodel.Flex;

public class CausalNetAnnotationsConnection
  extends AbstractConnection
{
  public static final String FLEX = "CausalNet";
  public static final String ANNOTATIONS = "CausalNetAnnotations";
  
  public CausalNetAnnotationsConnection(String label, Flex flex, CausalNetAnnotations annotations)
  {
    super(label);
    put("CausalNet", flex);
    put("CausalNetAnnotations", annotations);
  }
}