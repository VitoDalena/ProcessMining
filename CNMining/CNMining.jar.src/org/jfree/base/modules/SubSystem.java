package org.jfree.base.modules;

import org.jfree.util.Configuration;
import org.jfree.util.ExtendedConfiguration;

public abstract interface SubSystem
{
  public abstract Configuration getGlobalConfig();
  
  public abstract ExtendedConfiguration getExtendedConfig();
  
  public abstract PackageManager getPackageManager();
}


/* Location:              /home/menick/Scrivania/ProcessMining/CNMining/CNMining.jar!/org/jfree/base/modules/SubSystem.class
 * Java compiler version: 2 (46.0)
 * JD-Core Version:       0.7.1
 */