package bsh.util;

import bsh.NameSource;
import bsh.StringUtil;
import java.util.ArrayList;
import java.util.List;

public class NameCompletionTable
  extends ArrayList
  implements NameCompletion
{
  NameCompletionTable table;
  List sources;
  
  public void add(NameCompletionTable paramNameCompletionTable)
  {
    if (this.table != null) {
      throw new RuntimeException("Unimplemented usage error");
    }
    this.table = paramNameCompletionTable;
  }
  
  public void add(NameSource paramNameSource)
  {
    if (this.sources == null) {
      this.sources = new ArrayList();
    }
    this.sources.add(paramNameSource);
  }
  
  protected void getMatchingNames(String paramString, List paramList)
  {
    for (int i = 0; i < size(); i++)
    {
      String str = (String)get(i);
      if (str.startsWith(paramString)) {
        paramList.add(str);
      }
    }
    if (this.table != null) {
      this.table.getMatchingNames(paramString, paramList);
    }
    if (this.sources != null) {
      for (int j = 0; j < this.sources.size(); j++)
      {
        NameSource localNameSource = (NameSource)this.sources.get(j);
        String[] arrayOfString = localNameSource.getAllNames();
        for (int k = 0; k < arrayOfString.length; k++) {
          if (arrayOfString[k].startsWith(paramString)) {
            paramList.add(arrayOfString[k]);
          }
        }
      }
    }
  }
  
  public String[] completeName(String paramString)
  {
    ArrayList localArrayList = new ArrayList();
    getMatchingNames(paramString, localArrayList);
    if (localArrayList.size() == 0) {
      return new String[0];
    }
    String str = (String)localArrayList.get(0);
    for (int i = 1; (i < localArrayList.size()) && (str.length() > 0); i++)
    {
      str = StringUtil.maxCommonPrefix(str, (String)localArrayList.get(i));
      if (str.equals(paramString)) {
        break;
      }
    }
    if (str.length() > paramString.length()) {
      return new String[] { str };
    }
    return (String[])localArrayList.toArray(new String[0]);
  }
}


/* Location:              /home/menick/Scrivania/ProcessMining/CNMining/CNMining.jar!/bsh/util/NameCompletionTable.class
 * Java compiler version: 2 (46.0)
 * JD-Core Version:       0.7.1
 */