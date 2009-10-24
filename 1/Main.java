import java.util.*;
import java.math.*;

public class Main
{

  public static void p(String s)
  {
    System.out.println(s);
  }

//x.compareTo(y) < 0 if x < y 

  public static void main(String args[])
  {
    Scanner in = new Scanner(System.in);
    int cases = Integer.parseInt(in.nextLine());
    String data;

    HashMap<String, Peak> peaks = new HashMap<String, Peak>();
    TreeSet<Peak> answer = new TreeSet<Peak>();
    
    //p("Vars creates");
    
    for (int i=0; i<cases; ++i)
    {
      data = in.nextLine();
      String[] tmp = data.split("\\s+");

      //p("Input data: " + data);
      
      peaks.put(tmp[0], new Peak(tmp[0], tmp[2],tmp[1], tmp[3]));
      
    }

    Iterator<String> iter = peaks.keySet().iterator();
    String key = "";
    while(iter.hasNext())
    {
      key = iter.next();
      peaks.get(key).print();
      Iterator<String> iter2 = peaks.keySet().iterator();
      String key2 = "";
      Boolean blocked = false;
      while(!blocked && iter2.hasNext())
      {
        key2 = iter2.next();
        Peak tmp = peaks.get(key2);
        //p("Cur Peak Height: " + peaks.get(key).h);
        //p("Checking against " + tmp.name + ": " + tmp.heightAtLoc(peaks.get(key)));
        
        
        if(!(key2.equals(key))
           && peaks.get(key2).heightAtLoc(peaks.get(key)) > peaks.get(key).h)
          blocked = true;
        
      }
      if (!blocked)
        answer.add(peaks.get(key));
    }
    Iterator<Peak> ansiter = answer.iterator();
    while(ansiter.hasNext())
    {
      Peak tmp = ansiter.next();
      System.out.println(tmp.name);
    }
  }

  private static class Peak implements Comparable
  {
    public String name;
    public int x;
    public int y;
    public int h;
    
    public Peak(String namep, String xp, String yp, String hp)
    {
      name = namep; 
      x = Integer.parseInt(xp);
      y = Integer.parseInt(yp);
      h = Integer.parseInt(hp);


    }

    public int distTo(Peak other)
    {
      return Math.abs(other.y - y);
    }

    public int heightAtLoc(Peak other)
    {
      int dist = Math.abs(this.distTo(other));
      return h - (2 * dist);
    }

    public int compareTo(Object other)
    {
      return this.y - ((Peak) other).y;
    }

    public void print()
    {
      //p("Peak created:");
      //p("Name: " + name);
      //p("x coord: " + x);
      // p("y coord: " + y);
      //   p("Height: " + h);
    }
    
  }
  

  
}
