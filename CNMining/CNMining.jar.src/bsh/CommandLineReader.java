package bsh;

import java.io.FilterReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.io.Reader;

class CommandLineReader
  extends FilterReader
{
  static final int normal = 0;
  static final int lastCharNL = 1;
  static final int sentSemi = 2;
  int state = 1;
  
  public CommandLineReader(Reader paramReader)
  {
    super(paramReader);
  }
  
  public int read()
    throws IOException
  {
    if (this.state == 2)
    {
      this.state = 1;
      return 10;
    }
    int i;
    while ((i = this.in.read()) == 13) {}
    if (i == 10)
    {
      if (this.state == 1)
      {
        i = 59;
        this.state = 2;
      }
      else
      {
        this.state = 1;
      }
    }
    else {
      this.state = 0;
    }
    return i;
  }
  
  public int read(char[] paramArrayOfChar, int paramInt1, int paramInt2)
    throws IOException
  {
    int i = read();
    if (i == -1) {
      return -1;
    }
    paramArrayOfChar[paramInt1] = ((char)i);
    return 1;
  }
  
  public static void main(String[] paramArrayOfString)
    throws Exception
  {
    CommandLineReader localCommandLineReader = new CommandLineReader(new InputStreamReader(System.in));
    for (;;)
    {
      System.out.println(localCommandLineReader.read());
    }
  }
}


/* Location:              /home/menick/Scrivania/ProcessMining/CNMining/CNMining.jar!/bsh/CommandLineReader.class
 * Java compiler version: 2 (46.0)
 * JD-Core Version:       0.7.1
 */